package com.evan.demo.bottomnavigationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

import circle.StepArcView;


public class ContactsFragment extends Fragment{
    private View rootview=null;

    private  boolean isGetData = false;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_study, container, false);
        final StepArcView arcView =(StepArcView) rootview.findViewById(R.id.progressBar) ;
        Button bt=(Button) rootview.findViewById(R.id.startdy);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it;
                it = new Intent(getActivity(), StudyActivity.class);
//                ContactsFragment.this.finish();
                startActivity(it);
                //处理监听事件

            }
        });
        return  rootview;
    }
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //   进入当前Fragment
        if (enter && !isGetData) {
            isGetData = true;
            //   这里可以做网络请求或者需要的数据刷新操作
//            GetData();
        } else {
            isGetData = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(rootview !=null && ! hidden){
            Log.e("ContactsFragment", "onHiddenChanged");
        }
    }

    @Override
    public void onStart() {
        Log.e("ContactsFragment", "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.e("ContactsFragment", "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;
    }

    @Override
    public void onStop() {
        Log.e("ContactsFragment", "onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.e("ContactsFragment", "onDestroy");
        super.onDestroy();
    }
}
