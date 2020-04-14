package ch04;

import io.reactivex.Flowable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TimerSample {

    public static void main(String[] args) throws Exception {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss.SSS");

        System.out.println("시작 시각: " + LocalTime.now().format(formatter));

        Flowable<Long> flowable = Flowable.timer(1000L, TimeUnit.MILLISECONDS);

        flowable.subscribe(
                data -> {
                    String threadName = Thread.currentThread().getName();
                    String time = LocalTime.now().format(formatter);
                    System.out.println(threadName + ": " + time + ": data= " + data);
                    )
                }
        )

    }

}
