package com.example.lottiesample.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.model.LottieComposition;
import com.example.lottiesample.R;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by admin on 2017/12/25.
 */

public class LocalActivity extends AppCompatActivity {
    LottieAnimationView animation_view_local;
    JSONObject json;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        initView();
        initData();
    }

    private void initData() {
        try {
            //InputStream in=getClass().getResourceAsStream("/assets/EmptyState.json");
            InputStream in= getResources().getAssets().open("HamburgerArrow.json");
            String path = new String(InputStreamToByte(in ));
            json = new JSONObject(path);
            LottieComposition
                    .fromJson(getResources(), json, new LottieComposition.OnCompositionLoadedListener() {
                        @Override
                        public void onCompositionLoaded(LottieComposition composition) {
                            setComposition(composition);
                        }
                    });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initView() {
        animation_view_local=findViewById(R.id.lottie_anim_local);
    }
    private byte[] InputStreamToByte(InputStream is) throws IOException {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        int ch;
        while ((ch = is.read()) != -1) {
            bytestream.write(ch);
        }
        byte imgdata[] = bytestream.toByteArray();
        bytestream.close();
        return imgdata;
    }
    private void setComposition(LottieComposition composition) {
        animation_view_local.setProgress(0);
        animation_view_local.loop(true);
        animation_view_local.setComposition(composition);
        animation_view_local.playAnimation();
    }
}
