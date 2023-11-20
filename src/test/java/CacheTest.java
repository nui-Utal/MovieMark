import org.junit.Test;
import utils.Cache;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * created by Xu on 2023/11/19 0:53.
 */

public class CacheTest {

    public static String projectPath = Cache.class.getResource("/").getPath();
    public static String savePath = "CodeCache";

    @Test
    public void WriteCacheTest() throws IOException {
        StringBuilder stringBuilder = new StringBuilder(projectPath);
        stringBuilder.append(savePath);
        stringBuilder.append(File.separator);

        System.out.println(stringBuilder);

        File file = new File(stringBuilder + "emailNameAndSource.txt");
        // create dir and file

        // 先得到文件的上级目录，并创建上级目录，再创建文件
        File parentFile = new File(stringBuilder.toString());
        if(!parentFile.exists()) {
            parentFile.mkdir();
        }
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);

        writer.write("code");
        writer.flush();
        writer.close();

        System.out.println(file.getPath());

//        if (file.exists()) {
//            // 文件存在，执行相关操作
//            // 比如读取文件内容、删除文件等
//        } else {
//            // 文件不存在，执行相关操作
//            // 比如创建文件、创建文件夹等
//        }
    }

}
