package com.evan.demo.bottomnavigationdemo.utils;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.evan.demo.bottomnavigationdemo.MessageFragment;
import com.evan.demo.bottomnavigationdemo.R;

public class ReadArticle  extends AppCompatActivity {
    TextView textView =null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_layout);
        textView=(TextView)findViewById(R.id.articel);
        Bundle bundle=this.getIntent().getExtras();
        String ac=bundle.getString("ac");
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        System.out.println("articles: "+ac);
        textView.setText(ac);
    }
}
