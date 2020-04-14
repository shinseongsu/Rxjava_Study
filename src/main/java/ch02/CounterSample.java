package ch02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CounterSample {

    public static void main(String[] args) throws Exception {

        final Counter counter = new Counter();

        Runnable task = () -> {
            for(int i = 0 ; i < 10000 ; i++ ) {
                counter.increment();
            }
        };

        // 비동기 처리 작업 생성을 준비한다.
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 새로운 스레드로 시작한다.
        Future<Boolean> future1 = executorService.submit(task, true);
        // 새로운 스레드로 시작한다.
        Future<Boolean> future2 = executorService.submit(task, true);

        // 결과가 반환될때 까지 기다린다.
        if(future1.get() && future2.get()) {
            System.out.println(counter.get());
        } else {
            System.out.println("실패");
        }

        executorService.shutdown();

    }

}
