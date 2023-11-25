package utils;

import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * created by Xu on 2023/11/16 14:40.
 */
public class EMailUtil {

    private static final Logger logger =
            LogManager.getLogger(EMailUtil.class);

    private static Properties properties;
    static {
        try {
            InputStream is = DruidUtil.class.getClassLoader().getResourceAsStream("email.properties");
            properties = new Properties();
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送邮件
     * @param sendTo
     * @param code
     */
    public static void send(String sendTo, String code){
        HtmlEmail email = new HtmlEmail();
        try{
            // 使用qq进行邮箱发送，使用163只需要将qq改为163
            email.setHostName(properties.getProperty("host"));
            email.setCharset(properties.getProperty("default-encoding"));
            email.addTo(sendTo);
            email.setFrom(properties.getProperty("username"),"My Fiview");
            email.setAuthentication(properties.getProperty("username") ,properties.getProperty("password"));
            email.setSubject("My Fiview 验证码");
            email.setMsg("欢迎您使用 My Fiview!\n验证码：" + code
                    + "，请在五分钟内进行验证。\n如果该验证码不为您本人申请，请忽视。");
            email.send();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
