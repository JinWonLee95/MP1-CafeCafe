package com.example.recipe_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015-12-01.
 */
public class Image_listAdapter extends ArrayAdapter<my_image_item> {
    private ViewHolder viewHolder=null;
    private LayoutInflater inflater=null;


    public Image_listAdapter(Context context,int resource,ArrayList<my_image_item> arrayList){
            super(context,resource,arrayList);
         inflater=LayoutInflater.from(context);
        }


    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        View v=convertView;

        if(v==null){
        viewHolder=new ViewHolder();
        v=inflater.inflate(R.layout.activity_image_list_item,null);
        viewHolder.image_name=(TextView)v.findViewById(R.id.image_list_name);
        viewHolder.image_id=(TextView)v.findViewById(R.id.image_list_id);
        viewHolder.image =(ImageView) v.findViewById(R.id.image_list_image);


        v.setTag(viewHolder);

        }else{
            viewHolder=(ViewHolder)v.getTag();
        }
        viewHolder.image_name.setText(getItem(position).getImage_name());
        viewHolder.image_id.setText(getItem(position).getImage_id());
        viewHolder.image.setImageBitmap(getItem(position).getImage_image());
        return v;

        }


    class ViewHolder {
       TextView image_name = null;
        TextView image_id = null;
        ImageView image = null;

    }
}