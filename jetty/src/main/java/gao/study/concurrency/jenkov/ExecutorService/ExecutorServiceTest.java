package gao.study.concurrency.jenkov.ExecutorService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class ExecutorServiceTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        // execute(Runnable)
          ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(new Runnable() {
//            public void run() {
//                System.out.println("Asynchronous task");
//            }
//        });
//        executorService.shutdown();

          // submit(Runnable)
//        Future future = executorService.submit(new Runnable() {
//            public void run() {
//                System.out.println("Asynchronous task");
//            }
//        });
//
//        //returns null if the task has finished correctly.
//        System.out.println(future.get());

        // submit(Callable)
//        Future future = executorService.submit(new Callable(){
//            public Object call() throws Exception {
//                System.out.println("Asynchronous Callable");
//                return "Callable Result";
//            }
//        });
//
//        System.out.println("future.get() = " + future.get());

//        Set<Callable<String>> callables = new HashSet<Callable<String>>();
//
//        callables.add(new Callable<String>() {
//            public String call() throws Exception {
//                return "Task 1";
//            }
//        });
//        callables.add(new Callable<String>() {
//            public String call() throws Exception {
//                return "Task 2";
//            }
//        });
//        callables.add(new Callable<String>() {
//            public String call() throws Exception {
//                return "Task 3";
//            }
//        });
//
//        String result = executorService.invokeAny(callables);
//
//        System.out.println("result = " + result);
//
//        executorService.shutdown();

        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 3";
            }
        });

        List<Future<String>> futures = executorService.invokeAll(callables);

        for(Future<String> future : futures){
            System.out.println("future.get = " + future.get());
        }

        executorService.shutdown();

    }
}
