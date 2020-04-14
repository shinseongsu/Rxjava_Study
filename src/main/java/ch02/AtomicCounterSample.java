package ch02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AtomicCounterSample {

    public static void main(String[] args) throws Exception {

        final AtomicCounter counter = new AtomicCounter();

        Runnable task = () -> {
            for(int i = 0 ; i < 10000 ; i++) {
                counter.increment();
            }
        };

        ExecutorService excutorService = Executors.newCachedThreadPool();

        Future<Boolean> future1 = excutorService.submit(task, true);
        Future<Boolean> future2 = excutorService.submit(task, true);

        if(future1.get() && future2.get()) {
            System.out.println(counter.get());
        } else {
            System.out.println("실패");
        }

        excutorService.shutdown();

    }

}
