package com.evan.demo.bottomnavigationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jdbcmysql.jdbcc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyActivity extends AppCompatActivity {
    private ResultSet rs=null;
    private Button known,unknown;
    private TextView word,mean,invisbtxt;
    private RelativeLayout touch;
    private ImageButton back;
    private  Connection conn=null;
    private  PreparedStatement stmt=null;
    private List<String[]> bk=null;
    private ProgressBar process =null;
    private int id=-1,finised=0,num=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study);

        bk =  new ArrayList<>();;
        back=(ImageButton) super.findViewById(R.id.back);
        known=(Button) super.findViewById(R.id.know);
        unknown=(Button) super.findViewById(R.id.unknow);
        word=(TextView) super.findViewById(R.id.word);
        invisbtxt=(TextView) super.findViewById(R.id.clicktxt);
        mean= (TextView) super.findViewById(R.id.transition);
        touch=(RelativeLayout) super.findViewById(R.id.toucharea);
        process=(ProgressBar) super.findViewById(R.id.progressBar2) ;
        this.touch.setOnClickListener(new OnClickListener3());
        this.known.setOnClickListener(new OnClickListener1());
        this.unknown.setOnClickListener(new OnClickListener2());
        this.back.setOnClickListener(new OnClickListener4());
        new Thread(new Runnable() {
            @Override
            public void run() {
                bk=jdbcc.getwords(num);
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        init();
    }
    private void  init(){
        ++id;
        int doen=(finised*100/num);
        System.out.println(doen);
        process.setProgress(doen);
        invisbtxt.setVisibility(View.VISIBLE);
        mean.setVisibility(View.INVISIBLE);
        word.setText(bk.get(id)[0]);
        mean.setText(bk.get(id)[1]);
    }
    private class OnClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            ++finised;
            if(finised>=num){
                showMessage("完成了！");
                StudyActivity.this.finish();
                return ;
            }
            init();
        }
    }
    private class OnClickListener2 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            init();
            String map1[]=bk.get(id);
            bk.add(map1);

        }
    }
    private class OnClickListener3 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            invisbtxt.setVisibility(View.INVISIBLE);
            mean.setVisibility(View.VISIBLE);
        }
    }
    private class OnClickListener4 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            StudyActivity.this.finish();
        }
    }
    public void showMessage(String str){
        Toast toast=Toast.makeText(this,str,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,220);
        toast.show();
    }
}
