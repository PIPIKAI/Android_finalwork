package com.evan.demo.bottomnavigationdemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import com.jdbcmysql.jdbcc;
import com.data.USER;
import com.evan.demo.bottomnavigationdemo.utils.BottomNavigationViewHelper;
import com.jdbcmysql.jdbcc;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class MainActivity extends AppCompatActivity {
    private ResultSet rs=null;
    private Button bt1;
    private TextView username,password;
    private Connection conn=null;
    private String us="";
    private String pa= "";
    boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__main);
        bt1=(Button) super.findViewById(R.id.login_bt1);
        username=(TextView) super.findViewById(R.id.username);
        password= (TextView) super.findViewById(R.id.password);
        this.bt1.setOnClickListener(new OnClickListener1());

    }
    private class OnClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
//            BlockingQueue queue = new ArrayBlockingQueue(1);//数组型队列，长度为1
            Thread t =new Thread(new Runnable() {
                @Override
                public void run() {
                    us=username.getText().toString();
                    pa=password.getText().toString();
                    flag=jdbcc.linkMysql(us,pa);
                }
            });
            t.start();
            // 子线程太慢了
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (flag) {
                showMessage("登陆成功！");
                Intent it = new Intent(MainActivity.this, MenuActivity.class);
                MainActivity.this.finish();
                MainActivity.this.startActivity(it);
            }
            else{
                showMessage("账号或者密码错误！");
                password.setText("");
                username.setText("");
            }

        }
    }
    public void showMessage(String str){
        Toast toast=Toast.makeText(this,str,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,220);
        toast.show();
    }


}
