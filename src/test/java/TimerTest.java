import org.junit.Test;
import utils.Cache;
import utils.CacheFileDeleteTask;

import java.io.File;
import java.util.Date;
import java.util.Timer;

/**
 * created by Xu on 2023/11/20 11:16.
 */
public class TimerTest {

    public static String projectPath = Cache.class.getResource("/").getPath();
    public static String savePath = "CodeCache";

    @Test
    public void deleteTest() {
        StringBuilder stringBuilder = new StringBuilder(projectPath);
        stringBuilder.append(savePath);
        stringBuilder.append(File.separator);

        Timer timer = new Timer();
        System.out.println(System.currentTimeMillis());
        long time = System.currentTimeMillis() + 30 * 60 * 1000;
        System.out.println(time);
        Date date = new Date(time);
        System.out.println(stringBuilder);
        timer.schedule(new CacheFileDeleteTask(stringBuilder + "emailNameAndSource.txt"), date);
    }
}
