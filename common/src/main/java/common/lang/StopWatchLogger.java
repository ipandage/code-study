package common.lang;

import java.text.NumberFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;

/**
 * 把方法执行时间写日志文件
 *
 * 用法：
 *  MethodTimeLogger first = new MethodTimeLogger("任务名称");
 first.start("A");
 Thread.sleep(200);
 first.stop();
 first.start("B");
 Thread.sleep(200);
 first.stop();
 first.start("C");
 Thread.sleep(120);
 first.stop();
 first.writeLog();
 *
 * @author 徐良永
 * @created 2014年1月13日 上午10:52:25
 */
public class StopWatchLogger {
    private static final Log log = LogFactory.getLog(StopWatchLogger.class);

    private StopWatch clock;

    /**
     * 任务名称
     */
    private String taskName;


    /**
     * 创建一个即时任务
     */
    public StopWatchLogger(String taskName) {
        if(StringUtils.isBlank(taskName)){
            throw new NullPointerException();
        }
        this.taskName = taskName;
        clock = new StopWatch();
    }

    /**
     * 开始一个子任务计时
     * @param taskName： 任务名
     */
    public void start(String subTaskName){
        clock.start(subTaskName);
    }

    /**
     * 停止计时
     */
    public void stop(){
        clock.stop();
    }

    /**
     * 输出执行时间
     */
    public void writeLog(){
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumIntegerDigits(5);
        nf.setGroupingUsed(false);

        log.info(">>>>  " + nf.format(clock.getTotalTimeMillis()) + "  " + taskName);

        for (TaskInfo task : clock.getTaskInfo()) {
            StringBuilder sb = new StringBuilder();
            sb.append(nf.format(task.getTimeMillis())).append("  ");
            sb.append(task.getTaskName());
            log.info(sb);
        }
    }


    public static void main(String[] args) throws InterruptedException{
        StopWatchLogger first = new StopWatchLogger("test");
        first.start("方法一");
        Thread.sleep(200);
        first.stop();
        first.start("方法二");
        Thread.sleep(200);
        first.stop();
        first.start("方法三");
        Thread.sleep(120);
        first.stop();
        first.writeLog();

    }

}
