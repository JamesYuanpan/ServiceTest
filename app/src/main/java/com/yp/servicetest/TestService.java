package com.yp.servicetest;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class TestService extends Service {

    private MyBinder myBinder = new MyBinder();
    private int progress = 0;

    public TestService() {
    }

    //是啊，真的是非常的有趣
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public void startDownload(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress < 100){
                    progress++;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public int getProgress(){
        return progress;
    }

    class MyBinder extends Binder {

        public TestService getTestService(){

            return TestService.this;
        }
    }
}
