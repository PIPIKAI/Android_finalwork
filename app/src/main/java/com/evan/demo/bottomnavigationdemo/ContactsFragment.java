package com.evan.demo.bottomnavigationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * @author Evan_zch
 * @date 2018/8/23 20:40
 */
public class ContactsFragment extends Fragment{


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.fragment_study, container, false);

        Button bt=(Button) rootview.findViewById(R.id.startdy);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it;
                it = new Intent(getActivity(), StudyActivity.class);
//                ContactsFragment.this.finish();
                startActivity(it);
                //处理监听事件
                    ;
            }
        });
        return  rootview;
    }
}
