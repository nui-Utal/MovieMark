package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

/**
 * created by Xu on 2023/11/11 22:54.
 */
public class Generator {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String DIGITS = "0123456789";
    private static final String ALLOWED_CHARACTERS = CHAR_LOWER + CHAR_UPPER + DIGITS;

    /**
     * 生成盐
     * @param length 指定盐的长度
     * @return 返回生成的盐，包含大小写字母和数字
     */
    public static String generateSalt(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            sb.append(ALLOWED_CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

    /**
     * 将密码和盐组成的字符串进行md5加密。
     * @param context password + salt
     * @return md5
     */
    public static String HashOfPwdWithSalt(String context) {
        try {
            // 获取一个MD5消息摘要实例
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 更新消息摘要，将输入的文本内容转换为字节数组并进行处理
            md.update(context.getBytes());

            // 计算消息摘要，得到MD5散列值
            byte[] encryContext = md.digest();

            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < encryContext.length; offset++) {
                // 将字节值转换为无符号整数
                i = encryContext[offset];
                if (i < 0) i += 256;  // 处理负值
                if (i < 16) buf.append("0");  // 补充前导0，以保证每个字节都被表示为两位十六进制数
                buf.append(Integer.toHexString(i));  // 将字节值转换为十六进制字符串并追加到结果字符串
            }

            // 返回MD5散列值的十六进制表示
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            // 处理NoSuchAlgorithmException异常，通常是因为指定的MD5算法不可用
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成6位数的验证码
     * @return string类型的六位数验证码
     */
    public static String generateVerificationCode() {
        Random random = new Random();
        int randomInt = random.nextInt(999999);
        return String.format("%06d", randomInt);
    }

    /**
     * 得到当前时间
     * @return
     */
    public static String getCurrentTime() {
        // 设置时区为北京时间
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");

        // 获取当前时间并转换为北京时间
        Date now = new Date();
        Date beijingTime = new Date(now.getTime() + timeZone.getRawOffset());

        // 使用 SimpleDateFormat 格式化日期
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(timeZone);
        return formatter.format(beijingTime);
    }

}


