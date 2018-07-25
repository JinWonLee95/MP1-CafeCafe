package com.example.recipe_application;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015-12-13.
 */
public class innerContent_ListAdapter extends ArrayAdapter<my_innerContent_item> {
private ViewHolder viewHolder=null;
private LayoutInflater inflater=null;
        private Context context;


public innerContent_ListAdapter(Context context, int resource, ArrayList<my_innerContent_item> arrayList){
        super(context,resource,arrayList);
        inflater=LayoutInflater.from(context);
        this.context= context;
        }


@Override
public View getView(int position,View convertView,ViewGroup parent) {
        View v = convertView;

        if (v == null) {
                viewHolder = new ViewHolder();
                v = inflater.inflate(R.layout.activity_alllist_inner_list_item, null);
                viewHolder.innercontent_text = (TextView) v.findViewById(R.id.innercontent_text);
                viewHolder.innercontent_image = (ImageView) v.findViewById(R.id.innercontent_image);


                v.setTag(viewHolder);

        } else {
                viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.innercontent_text.setText(getItem(position).getInnercontent_text());
        String save = getItem(position).getPath();
        Bitmap bit =  StringToBitMap(save);
        viewHolder.innercontent_image.setImageBitmap(bit);
        return v;
}
        public Bitmap StringToBitMap(String encodedString){
                try {
                        byte [] encodeByte= Base64.decode(encodedString, Base64.DEFAULT);
                        Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                        return bitmap;
                } catch(Exception e) {
                        e.getMessage();
                        return null;
                }
        }

        public Uri getUriFromPath(String path){
        Uri fileUri = Uri.parse( path );
        String filePath = fileUri.getPath();
        Cursor cursor = context.getContentResolver().query( MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, "_data = '" + filePath + "'", null, null );
        cursor.moveToNext();
        int id = cursor.getInt( cursor.getColumnIndex( "_id" ) );
        Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);

        return uri;
}

class ViewHolder {
    TextView innercontent_text = null;
    ImageView innercontent_image = null;

}
}
