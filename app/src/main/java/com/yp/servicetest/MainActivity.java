package com.yp.servicetest;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TestService testService;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this,TestService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testService.startDownload();
                Log.d("||||||||####从服务端获取数据！！！",testService.getProgress()+"");
            }
        });
    }

    //创建进度条对话框
    private void createDialog(){
        dialog = new ProgressDialog(this);
        //设置进度条风格，风格为圆形，旋转的
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //设置ProgressDialog 标题
        dialog.setTitle("进度对话框");
        //设置ProgressDialog 提示信息
        dialog.setMessage("圆形进度条");
        //设置ProgressDialog 标题图标
        dialog.setIcon(android.R.drawable.ic_dialog_map);
        //设置ProgressDialog 的进度条是否不明确
        dialog.setIndeterminate(false);
        dialog.setMax(100);
        //设置ProgressDialog 是否可以按退回按键取消
        dialog.setCancelable(true);

        dialog.setProgress(testService.getProgress());
        //显示
        dialog.show();
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TestService.MyBinder myBinder = (TestService.MyBinder) service;
            testService = myBinder.getTestService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
