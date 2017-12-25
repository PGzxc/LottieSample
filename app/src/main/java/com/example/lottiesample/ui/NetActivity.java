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
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 2017/12/25.
 */

public class NetActivity extends AppCompatActivity {
    LottieAnimationView animation_view_network;
    private OkHttpClient client;
    JSONObject json;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        animation_view_network = (LottieAnimationView) findViewById(R.id.lottie_anim_net);
        loadUrl("http://www.chenailing.cn/EmptyState.json");
    }
    private void loadUrl(String url) {
        Request request;
        try {
            request = new Request.Builder()
                    .url(url)
                    .build();
        } catch (IllegalArgumentException e) {
            return;
        }


        if (client == null) {
            client = new OkHttpClient();
        }

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                try {
                    //InputStream in=getClass().getResourceAsStream("/assets/EmptyState.json");
                    InputStream in= getResources().getAssets().open("EmptyState.json");
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

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (!response.isSuccessful()) {

                }
                try {
                    json = new JSONObject(response.body().string());
                    LottieComposition
                            .fromJson(getResources(), json, new LottieComposition.OnCompositionLoadedListener() {
                                @Override
                                public void onCompositionLoaded(LottieComposition composition) {
                                    setComposition(composition);
                                }
                            });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setComposition(LottieComposition composition) {
        animation_view_network.setProgress(0);
        animation_view_network.loop(true);
        animation_view_network.setComposition(composition);
        animation_view_network.playAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        animation_view_network.cancelAnimation();
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

}
