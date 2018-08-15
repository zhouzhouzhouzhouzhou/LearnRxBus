package com.example.admin.learnrxbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Subscription;
import rx.functions.Action1;

/**
* @author zhou.jn
*
* @create_at 2018/8/15 14:41
*
*/
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Subscription mRxSubscription;
    private RxBus rxBus;
    private Button btnClick;
    private TextView tvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnClick = findViewById(R.id.btnBus);
        tvShow = findViewById(R.id.tvShow);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rxBus = RxBus.getInstance();
                rxBus.post(new SubEvent("事件1", 1));
            }
        });
        onObserver();

    }

    private void onObserver() {
        mRxSubscription = RxBus.getInstance().toObservable(SubEvent.class)
                .subscribe(new Action1<SubEvent>() {
                               @Override
                               public void call(SubEvent subEvent) {
                                   String name = subEvent.getEventName();
                                   int id = subEvent.getEventId();
                                   tvShow.setText(id + "\n"+name);
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.i(TAG, "call: throwable " + throwable.toString());
                            }
                        }
                );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mRxSubscription.isUnsubscribed()) {
            mRxSubscription.unsubscribe();
        }
    }
}
