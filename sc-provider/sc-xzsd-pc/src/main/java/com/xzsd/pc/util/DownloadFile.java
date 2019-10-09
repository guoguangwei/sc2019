package com.xzsd.pc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Author: wwb
 * @Date: 2019/5/22 17:35
 * @Description: 下载文件用
 */
public class DownloadFile {

    private static final Logger LOGGER = LoggerFactory.getLogger(DownloadFile.class);

    public static void download(HttpServletRequest request,
                                HttpServletResponse response, String filePath, String backFilname) {
        File file = null;

        FileInputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            //Linux系统用
            if (filePath.lastIndexOf("/") > 0) {
                file = new File(filePath);
            } else {
                //windows系统用
                file = new File(System.getProperty("java.io.tmpdir"), filePath);
            }

            inputStream = new FileInputStream(file);
            outputStream = response.getOutputStream();

            response.setCharacterEncoding("UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("application/x-download;charset=UTF-8");

            setContentDisposition(request, response, backFilname);

            byte[] buffer = new byte[1024];
            int total;

            while ((total = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, total);
            }

            outputStream.flush();
        } catch (FileNotFoundException e) {
            LOGGER.error("download", e);
        } catch (IOException e) {
            LOGGER.error("download", e);
        } catch (Exception e) {
            LOGGER.error("download", e);
        } finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }

                if (null != inputStream) {
                    inputStream.close();
                }

            } catch (IOException e) {
                LOGGER.error("download", e);
            }
            file.delete();
        }
    }

    public static void setContentDisposition(HttpServletRequest request,
                                             HttpServletResponse response, String showName)
            throws UnsupportedEncodingException {
        String userAgent = request.getHeader("USER-AGENT").toLowerCase();
        String contentDisposition = "";
        if (userAgent != null) {
            if (userAgent.indexOf("msie") >= 0) {
                contentDisposition = "attachment; filename="
                        + URLEncoder.encode(showName, "UTF-8").replace("+",
                        "%20");

            } else if (userAgent.indexOf("mozilla") >= 0) {
                contentDisposition = "attachment; filename*=UTF-8''"
                        + URLEncoder.encode(showName, "UTF-8").replace("+",
                        "%20");

            } else if (userAgent.indexOf("applewebkit") >= 0) {
                contentDisposition = "attachment; filename="
                        + URLEncoder.encode(showName, "UTF-8").replace("+",
                        "%20");
            } else if (userAgent.indexOf("safari") >= 0) {
                contentDisposition = "attachment; filename="
                        + new String(showName.getBytes("UTF-8"), "ISO8859-1");

            } else if (userAgent.indexOf("opera") >= 0) {
                contentDisposition = "attachment; filename*=UTF-8''"
                        + URLEncoder.encode(showName, "UTF-8").replace("+",
                        "%20");

            } else {
                contentDisposition = "attachment; filename*=UTF-8''"
                        + URLEncoder.encode(showName, "UTF-8").replace("+",
                        "%20");
            }
        }

        response.setHeader("Content-Disposition", contentDisposition);
    }

}
