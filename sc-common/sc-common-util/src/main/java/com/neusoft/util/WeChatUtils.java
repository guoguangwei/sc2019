package com.neusoft.util;

import com.google.gson.Gson;
import com.neusoft.util.entity.WeChatUser;
import org.apache.commons.lang3.StringUtils;

public class WeChatUtils {

    public static String SECRET = "49d609eaf36ab0a0df9b6ce10b610f7e";

    public static String APPID = "wxd9651d12b908cb20";

    public static WeChatUser getOauthUser(String code){
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID
                + "&secret="+SECRET
                + "&code=" + code + "&grant_type=authorization_code";
        String respText = HttpUtils.HttpGet(url);
        WeChatUser obj = new Gson().fromJson(respText, WeChatUser.class);
        if (StringUtils.isNotBlank(obj.getErrorcode())) {
            obj = new WeChatUser();
        }
        return obj;
    }
}
