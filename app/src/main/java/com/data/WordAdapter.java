package com.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.evan.demo.bottomnavigationdemo.R;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {
    private int resourceId;
    public WordAdapter(@NonNull Context context, int resource, @NonNull List<Word> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Word word = getItem(position);//获取当前项的实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ((TextView) view.findViewById(R.id.word)).setText(word.getWord());
        ((TextView) view.findViewById(R.id.transition)).setText(word.getTranslation());
        return view;
    }
}
