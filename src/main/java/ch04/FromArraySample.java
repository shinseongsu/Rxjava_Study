package ch04;

import io.reactivex.Flowable;

public class FromArraySample {

    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.fromArray("A", "B", "C", "D", "E");

        flowable.subscribe(new DebugSubscriber<>());
    }

}
