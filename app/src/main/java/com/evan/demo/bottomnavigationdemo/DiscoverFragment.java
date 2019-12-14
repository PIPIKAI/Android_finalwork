package com.evan.demo.bottomnavigationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.data.Word;
import com.data.WordAdapter;
import com.jdbcmysql.jdbcc;

import java.util.ArrayList;
import java.util.List;


public class DiscoverFragment extends Fragment {
    @Nullable
    private Button bt;
    private EditText editText;
    private ListView listView;
    private List<Word> data=new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview=inflater.inflate(R.layout.fragment_discover, container, false);
        bt=(Button) rootview.findViewById(R.id.search_bt);
        listView=(ListView) rootview.findViewById(R.id.list_word);
        editText=(EditText)rootview.findViewById(R.id.search);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String message=editText.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        data= jdbcc.searchwords(message);
                    }
                }).start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                WordAdapter adapter=new WordAdapter(getActivity(),R.layout.word_item,data);
                listView.setAdapter(adapter);

            }
        });


        return  rootview;
    }
}
