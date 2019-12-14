package com.evan.demo.bottomnavigationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.evan.demo.bottomnavigationdemo.utils.ReadArticle;
import com.jdbcmysql.jdbcc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Evan_zch
 * @date 2018/8/23 20:38
 */
public class MessageFragment extends Fragment {
    @Nullable
    private ListView listView;
    private View rootview;
    private List<String> data = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview= inflater.inflate(R.layout.fragment_read, container, false);
        listView=(ListView) rootview.findViewById(R.id.list_artical);
        new Thread(new Runnable() {
            @Override
            public void run() {
                data= jdbcc.searchTitle();
            }
        }).start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(getActivity(),R.layout.title_style,data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                final String tp = data.get(position);
                System.out.println("title: "+tp);
                final String[] article = {""};
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        article[0] = jdbcc.searchArticle(tp);
                    }
                }).start();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent it;
                it = new Intent();
                it.putExtra("ac",article[0]);
                it.setClass(getActivity(), ReadArticle.class);
                startActivity(it);
            }


        });
        return rootview;
    }
}
