package com.example.recipe_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015-12-14.
 */
public class innerContent_review_ListAdapter extends ArrayAdapter<My_innerContent_review_item> {
    private ViewHolder viewHolder=null;
    private LayoutInflater inflater=null;


    public innerContent_review_ListAdapter(Context context, int resource, ArrayList<My_innerContent_review_item> arrayList){
        super(context,resource,arrayList);
        inflater=LayoutInflater.from(context);
    }


    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        View v=convertView;

        if(v==null){
            viewHolder=new ViewHolder();
            v=inflater.inflate(R.layout.activity_alllist_inner_list_review_item,null);
            viewHolder.review_id=(TextView)v.findViewById(R.id.review_id);
            viewHolder.review_content =(TextView) v.findViewById(R.id.review_content);
            viewHolder.review_time =(TextView) v.findViewById(R.id.review_time);

            v.setTag(viewHolder);

        }else{
            viewHolder=(ViewHolder)v.getTag();
        }
        viewHolder.review_id.setText(getItem(position).getReview_id());
        viewHolder.review_content.setText(getItem(position).getReview_content());
        viewHolder.review_time.setText(getItem(position).getReview_time());
        return v;

    }


    class ViewHolder {
        TextView review_id = null;
        TextView review_content = null;
        TextView review_time = null;

    }
}
