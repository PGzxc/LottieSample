package com.example.lottiesample;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lottiesample.ui.CodeActivity;
import com.example.lottiesample.ui.LocalActivity;
import com.example.lottiesample.ui.NetActivity;
import com.example.lottiesample.ui.SampleActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRx();
        setListener();
    }

    private void setListener() {
        findViewById(R.id.btn_sample).setOnClickListener(view ->startActivity(SampleActivity.class));
        findViewById(R.id.btn_code).setOnClickListener(view -> startActivity(CodeActivity.class));
        findViewById(R.id.btn_net).setOnClickListener(view -> startActivity(NetActivity.class));
        findViewById(R.id.btn_local).setOnClickListener(view->startActivity(LocalActivity.class));
    }

    public void startActivity(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    private void setRx() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET)
                .subscribe(granted -> {
                    if (granted) {
                        // All requested permissions are granted
                    } else {
                        // At least one permission is denied
                    }
                });
    }

}
