package utils;

import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * created by Xu on 2023/11/20 10:45.
 */
public class CacheFileDeleteTask extends TimerTask {

    private String path;

    public CacheFileDeleteTask(String path) {
        this.path = path;
    }


    /**
     * 会开启一个新的线程，不“阻碍”当前线程的运行
     * 进行文件的删除
     */
    @Override
    public void run() {
        File file = new File(path);
        if (file.exists()) {
            System.out.println(file.delete());
        }
    }

    /**
     * 开启删除传入文件的定时任务
     * @param filePath
     */
    public static void startTask(String filePath) {
        Timer timer = new Timer();
        Date date = new Date(System.currentTimeMillis() + 15 * 60 * 1000);
        timer.schedule(new CacheFileDeleteTask(filePath), date);
    }
}
