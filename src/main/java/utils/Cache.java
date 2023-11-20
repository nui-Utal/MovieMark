package utils;

import javax.servlet.ServletContext;
import java.io.*;

/**
 * created by Xu on 2023/11/18 17:01.
 */
public class Cache {


    private static String projectPath = Cache.class.getResource("/").getPath();
    private static String savePath = "CodeCache";

    private static String fullPath = projectPath + File.separator + savePath + File.separator;

    /**
     * 邮箱去除 . 作为文件名
     * 创建json文件，分行写入邮箱和验证码
     * @param email
     * @param code
     */
    public static String writeCache(String email, String code) throws IOException {

        String replace = email.replace(".", "");

        File file = new File(fullPath + replace + ".txt");
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        writer.write(code);
        writer.flush();
        writer.close();
        return file.getPath();
    }

    /**
     * 根据邮箱名查找对应文件
     * @param email fileName
     * @return String code
     */
    public static String readCache(String email) throws IOException {
        File file = new File(fullPath + email.replace(".", "") + ".txt");
        if(!file.exists()) {
            return "";
        }
        // 读文件，得到验证码
        FileReader reader = new FileReader(file);
        char[] ch = new char[6];
        reader.read(ch);
        for(char c:ch) {
            System.out.print(c);
        }
        reader.close();
        return ch.toString();
    }
}
