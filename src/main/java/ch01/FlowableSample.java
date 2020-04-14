package ch01;

import io.reactivex.*;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FlowableSample {

    public static void main(String[] args) throws Exception {

        Flowable<String> flowable =
                Flowable.create(new FlowableOnSubscribe<String>() {

                    @Override
                    public void subscribe(FlowableEmitter<String> emitter) throws Exception {

                        String[] datas = { "Hello, World!", "안녕, Rxjava" };

                        for(String data : datas) {
                            if(emitter.isCancelled()) {
                                return;
                            }

                            emitter.onNext(data);
                        }

                        emitter.onComplete();
                    }
                }, BackpressureStrategy.BUFFER);

        flowable
                .observeOn(Schedulers.computation())
                .subscribe(new Subscriber<String>() {

                    private Subscription subscription;

                    // 구독 시작 시 처리
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.subscription = subscription;
                        this.subscription.request(1L);
                    }

                    @Override
                    public void onNext(String data) {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": " + data);
                        this.subscription.request(1L);
                    }

                    @Override
                    public void onError(Throwable error) {
                        error.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": 완료");
                    }
                });

        Thread.sleep(500L);

    }

}
