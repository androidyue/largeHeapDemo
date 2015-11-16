package com.droidyue.largeheapdemo;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<byte[]> mLeakyContainer = new ArrayList<>();
    private static final String LOGTAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.testBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] b = new byte[100 * 1000 * 1000];
                mLeakyContainer.add(b);
            }
        });
        testMemoryInfo();
    }

    public void testMemoryInfo() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        int largeMemoryClass = activityManager.getLargeMemoryClass();
        int memoryClass = activityManager.getMemoryClass();

        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);

        Log.d(LOGTAG, "largeMemoryClass = " + largeMemoryClass);
        Log.d(LOGTAG, "memoryClass = " + memoryClass);

        Log.d(LOGTAG, "availMem = " + (info.availMem / 1024 / 1024) + "M");
        Log.d(LOGTAG, "totalMem = " + (info.totalMem / 1024 / 1024) + "M");
        Log.d(LOGTAG, "lowMemory = " + info.lowMemory);
    }
}
