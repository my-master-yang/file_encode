package sample.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorUtil {
    /**
     * 文件上传线程池
     */
    public static final ExecutorService UP_FILE_EXECUTOR = Executors.newCachedThreadPool();
}
