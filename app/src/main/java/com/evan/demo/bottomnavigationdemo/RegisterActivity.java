package com.evan.demo.bottomnavigationdemo;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jdbcmysql.jdbcc;

import java.util.ArrayList;

import static com.jdbcmysql.jdbcc.user_add;

public class RegisterActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    TextView look;
    Button bt;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        this.look=(TextView) super.findViewById(R.id.look);
        this.ed1 =(EditText) super.findViewById(R.id.username);
        this.ed2 =(EditText) super.findViewById(R.id.password);
        this.ed3 =(EditText) super.findViewById(R.id.goaldays);
        this.ed4 =(EditText) super.findViewById(R.id.daily);
        this.bt=(Button)super.findViewById(R.id.re_bt);
        this.bt.setOnClickListener(new OnClickListener1());
    }
    private class OnClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            final String us=ed1.getText().toString(),pa=ed2.getText().toString(),goal=ed3.getText().toString(),dailys=ed4.getText().toString() ;
            final boolean[] flag = {false};
            new Thread(new Runnable() {
                @Override
                public void run() {
                    flag[0] = user_add(us,pa,goal,dailys);
                }
            }).start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(flag[0] ==false){
                look.setVisibility(View.VISIBLE);
            }
            else {
                showMessage("注册成功！");
                RegisterActivity.this.finish();
            }
        }
    }
    public void showMessage(String str){
        Toast toast=Toast.makeText(this,str,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,220);
        toast.show();
    }
}
