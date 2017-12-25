package com.example.lottiesample.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.model.LottieComposition;
import com.example.lottiesample.R;

/**
 * Created by admin on 2017/12/25.
 */

public class CodeActivity extends AppCompatActivity {

    private LottieAnimationView lottieAnim;
    private TextView tvProgress;
    private Button btnStart;
    private Button btnEnd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        initView();
        initData();
        setListener();

    }


    private void initView() {
        lottieAnim = findViewById(R.id.lottie_anim);
        tvProgress = findViewById(R.id.tv_progress);
        btnStart = findViewById(R.id.btn_start);
        btnEnd = findViewById(R.id.btn_end);
    }

    private void initData() {
        LottieComposition.fromAssetFileName(this, "LottieLogo1.json", composition -> {
            lottieAnim.setComposition(composition);
            lottieAnim.setProgress(0);
            lottieAnim.playAnimation();
        });
    }

    private void setListener() {
        btnStart.setOnClickListener(view->startAnimal());
        btnEnd.setOnClickListener(view->stopAnimal());
        lottieAnim.addAnimatorUpdateListener(animation -> {tvProgress.setText(" 动画进度" +(int) (animation.getAnimatedFraction()*100) +"%");});
    }

    /**
     * 开始动画
     */
    private void startAnimal() {
        boolean isPlaying = lottieAnim.isAnimating();
        if (!isPlaying) {
            lottieAnim.setProgress(0f);
            lottieAnim.playAnimation();
        }
    }

    /**
     * 结束动画
     */
    private void stopAnimal() {
        boolean isPlaying = lottieAnim.isAnimating();
        if (isPlaying) {
            lottieAnim.cancelAnimation();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        lottieAnim.cancelAnimation();
    }
}
