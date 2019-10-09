package com.xzsd.pc.log.aspect;

import com.alibaba.fastjson.JSONObject;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.JsonUtils;
import com.neusoft.util.UUIDUtils;
import com.xzsd.pc.log.annotation.OperationControllerLog;
import com.xzsd.pc.log.annotation.OperationServiceLog;
import com.xzsd.pc.log.dao.LogDao;
import com.xzsd.pc.log.entity.ActionDto;
import com.xzsd.pc.log.iputils.IpUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Title: SystemControllerLog
 *
 * @version V1.0
 * Description: 切点类
 * @date 2018年5月30日
 */
@Aspect
@Component
@SuppressWarnings("all")
public class SystemAspectLog {
    //本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(SystemAspectLog.class);

    @Autowired
    private LogDao logMapper;


    //Service层切点
    @Pointcut("@annotation(com.xzsd.pc.log.annotation.OperationServiceLog)")
    public void serviceAspect() {
    }

    //Controller层切点
    @Pointcut("@annotation(com.xzsd.pc.log.annotation.OperationControllerLog)")
    public void controllerAspect() {
    }

    /**
     * @Description 前置通知  用于拦截Controller层记录用户的操作
     * @date 2019年5月30日
     */


    @Before("serviceAspect()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            String ip = IpUtils.getIpAddr(request);
            String params = "";
            if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
                for (int i = 0; i < joinPoint.getArgs().length; i++) {
                    params += JsonUtils.toJson(joinPoint.getArgs()[i]);
                }
            }
            ActionDto action = new ActionDto();
            action.setId(UUIDUtils.getUUID());
            // 操作者
            action.setCreateBy(SecurityUtils.getCurrentUserId());
            action.setUserNo(SecurityUtils.getCurrentUserId());
            action.setFunDesc(getServiceMethodDescription(joinPoint));
            // 1家长2司机3门店4订单
            String role = getServiceMethodRole(joinPoint);
            action.setRole(role);
            if (!StringUtils.isEmpty(params)) {
                String code = (String) JSONObject.parseObject(params).get("orderNo");
                if (!StringUtils.isEmpty(code)) {
                    action.setOrderNo(code);
                }
            }
            //参数
            action.setFunParam(params);
            //请求方法
            action.setFunName((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
            //操作状态
            action.setStatus("0");
            //操作详情
//            action.setDetail();
            // ip
            action.setIp(ip);
            action.setOpType(1);
            //保存数据库
            logMapper.insertLog(action);

        } catch (Exception e) {
            //记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息：{}", e.getMessage());
        }
    }

    @Around("controllerAspect()")
    public Object doAroundService(ProceedingJoinPoint proceedingJoinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println("环绕通知的目标方法名为 ： " + proceedingJoinPoint.getSignature().getName());
        try {
            String ip = IpUtils.getIpAddr(request);
            String params = "";
            if (proceedingJoinPoint.getArgs() != null && proceedingJoinPoint.getArgs().length > 0) {
                for (int i = 0; i < proceedingJoinPoint.getArgs().length; i++) {
                    params += JsonUtils.toJson(proceedingJoinPoint.getArgs()[i]);
                }
            }
            AppResponse appResponse = (AppResponse) proceedingJoinPoint.proceed();
            //拿到订单编号
           // String data = (String) appResponse.getData();
            ActionDto action = new ActionDto();
//            if (!StringUtils.isEmpty(data)) {
//                action.setOrderNo(data);
//            }
            action.setId(UUIDUtils.getUUID());
            // 操作者
            action.setCreateBy(SecurityUtils.getCurrentUserId());
            action.setUserNo(SecurityUtils.getCurrentUserId());
            action.setFunDesc(getControllerMethodDescription(proceedingJoinPoint));
            // 1家长2司机3门店4订单
            String role = getControllerMethodRole(proceedingJoinPoint);
            action.setRole(role);
            /*if (!StringUtils.isEmpty(params)) {
                String code = (String) JSONObject.parseObject(params).get("orderNo");
                if (!StringUtils.isEmpty(code)) {
                    action.setOrderNo(code);
                }
            }*/
            //参数
            action.setFunParam(params);
            //请求方法
            action.setFunName((proceedingJoinPoint.getTarget().getClass().getName() + "." + proceedingJoinPoint.getSignature().getName()));
            //操作状态
            if(appResponse!=null){
                if(appResponse.getCode()==0){
                    action.setStatus("0");
                }else {
                    action.setStatus("1");
                }
            }

            //操作详情
            action.setDetail(appResponse.toString());
            // ip
            action.setIp(ip);
            action.setOpType(1);
            //保存数据库
            logMapper.insertLog(action);
            return appResponse;
        } catch (Throwable throwable) {
            logger.error("==环绕通知异常==");
            logger.error("异常信息：{}", throwable.getMessage());
        }
        return null;
    }


    /**
     * @Description 异常通知 用于拦截service层记录异常日志
     * @date 2019年5月31日
     */
//    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();
        //获取请求ip
        String ip = IpUtils.getIpAddr(request);
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params += joinPoint.getArgs()[i];
            }
        }
        try {
            /*========控制台输出=========*/
//            System.out.println("=====异常通知开始=====");
//            System.out.println("异常代码:" + e.getClass().getName());
//            System.out.println("异常信息:" + e.getMessage());
//            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//            System.out.println("方法描述:" + getServiceMethodDescription(joinPoint));
//            System.out.println("请求IP:" + ip);
//            System.out.println("请求参数:" + params);
            /*==========数据库日志=========*/
            ActionDto action = new ActionDto();
            action.setId(UUIDUtils.getUUID());
            // 操作者
            action.setCreateBy(SecurityUtils.getCurrentUserId());
            action.setUserNo(SecurityUtils.getCurrentUserId());
            // 1家长2司机3门店4订单
            action.setFunDesc(getServiceMethodDescription(joinPoint));
            String role = getServiceMethodRole(joinPoint);
            action.setRole(role);
            if (!StringUtils.isEmpty(params)) {
                String code = (String) JSONObject.parseObject(params).get("orderNo");
                if (!StringUtils.isEmpty(code)) {
                    action.setOrderNo(code);
                }
            }
            //参数
            action.setFunParam(params);
            //请求方法
            action.setFunName((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
            //操作状态
            action.setStatus("1");
            //异常信息 异常代码
            action.setDetail(e.getClass().getName());
            //异常日志
            action.setOpType(0);
            action.setDetail(e.getMessage());
            // ip
            action.setIp(ip);
            //保存到数据库
            logMapper.insertLog(action);
        } catch (Exception ex) {
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
    }


    /**
     * @author dxl
     * @Description 获取注解中对方法的描述信息 用于service层注解
     * @date 2019年5月30日
     */
    public static String getServiceMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(OperationServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * @author dxl
     * @Description 获取注解中对方法的role信息 用于service层注解
     * @date 2019年5月30日
     */
    public static String getServiceMethodRole(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String role = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    role = method.getAnnotation(OperationServiceLog.class).role();
                    break;
                }
            }
        }
        return role;
    }


    /**
     * @author dxl
     * @Description 获取注解中对方法的描述信息 用于Controller层注解
     * @date 2019年5月30日
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(OperationControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }


    /**
     * @author dxl
     * @Description 获取注解中对方法的描述信息 用于Controller层注解
     * @date 2019年5月30日
     */
    public static String getControllerMethodRole(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String role = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    role = method.getAnnotation(OperationControllerLog.class).role();
                    break;
                }
            }
        }
        return role;
    }
}
