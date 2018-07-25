package com.example.recipe_application;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015-12-14.
 */
public class innerContent_write_ListAdapter extends ArrayAdapter<my_innerContent_item> {
        static public ViewHolder viewHolder=null;
        private LayoutInflater inflater=null;
        static Context context;
        private static final String TEMP_PHOTO_FILE = "temp.jpg";       // 임시 저장파일
        private static final int REQ_CODE_PICK_IMAGE = 0;
        View v;
        int myposition ;

        public innerContent_write_ListAdapter(Context context, int resource, ArrayList<my_innerContent_item> arrayList){
                super(context,resource,arrayList);
                inflater=LayoutInflater.from(context);
                this.context = context;
        }


        @Override
        public View getView(int position,View convertView,ViewGroup parent){
                v=convertView;
                myposition= position;
                if(v==null){
                        viewHolder=new ViewHolder();
                        v=inflater.inflate(R.layout.activity_alllist_inner_list_item_edit,null);
                        viewHolder.write_click_edittext=(TextView)v.findViewById(R.id.write_click_edittext);
                        viewHolder.write_click_image =(ImageView) v.findViewById(R.id.write_click_image1);
                        viewHolder.write_click_image.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                        Toast.makeText(context, "여기리스너", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT,      // 또는 ACTION_PICK
                                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        intent.setType("image/*");              // 모든 이미지
                                        intent.putExtra("crop", "true");        // Crop기능 활성화
                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());     // 임시파일 생성
                                        intent.putExtra("outputFormat",         // 포맷방식
                                                Bitmap.CompressFormat.JPEG.toString());

                                        ((Activity) context).startActivityForResult(intent, REQ_CODE_PICK_IMAGE);
                                        // REQ_CODE_PICK_IMAGE == requestCode

                                }
                        });

                        v.setTag(viewHolder);

                }else{
                        viewHolder=(ViewHolder)v.getTag();
                }
                viewHolder.write_click_edittext.setText(getItem(position).getInnercontent_text());

                viewHolder.write_click_image.setImageBitmap(getItem(position).getInnercontent_image());
                return v;

        }

        /** 임시 저장 파일의 경로를 반환 */
        private Uri getTempUri() {
                return Uri.fromFile(getTempFile());
        }

        /** 외장메모리에 임시 이미지 파일을 생성하여 그 파일의 경로를 반환  */
        private File getTempFile() {
                if (isSDCARDMOUNTED()) {
                        File f = new File(Environment.getExternalStorageDirectory(), // 외장메모리 경로
                                TEMP_PHOTO_FILE);
                        try {
                                f.createNewFile();      // 외장메모리에 temp.jpg 파일 생성
                        } catch (IOException e) {
                        }

                        return f;
                } else
                        return null;
        }

        /** SD카드가 마운트 되어 있는지 확인 */
        private boolean isSDCARDMOUNTED() {
                String status = Environment.getExternalStorageState();
                if (status.equals(Environment.MEDIA_MOUNTED))
                        return true;

                return false;
        }

        /** 다시 액티비티로 복귀하였을때 이미지를 셋팅 */
        static void onActivityResult(int requestCode, int resultCode, Intent data) {
                Write_Activity w = new Write_Activity();
                if (resultCode == Activity.RESULT_OK) {
                        Uri uri = data.getData();
                        //String ImagePath = getPath(uri);
                        Bitmap selectedImage = null;
                        try {
                                selectedImage = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri );
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        w.write_listAdapter.viewHolder.write_click_image.setImageBitmap(selectedImage);
                        //Write_Activity w = new Write_Activity();
                        w.write_listAdapter.notifyDataSetChanged();
                }
        }

        public String getPath(Uri uri) {
                String[] projection = {MediaStore.Images.Media.DATA};
                Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(columnIndex);
        }




        class ViewHolder {
                TextView write_click_edittext = null;
                ImageView write_click_image = null;

        }



        public  void SaveBitmapToFileCache(Bitmap bitmap, String strFilePath,
                                           String filename) {

                File file = new File(strFilePath);

                // If no folders
                if (!file.exists()) {
                        file.mkdirs();
                        // Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                }

                File fileCacheItem = new File(strFilePath + filename);
                OutputStream out = null;

                try {
                        fileCacheItem.createNewFile();
                        out = new FileOutputStream(fileCacheItem);

                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        try {
                                out.close();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        } //파일 생성하는 메소드
        //저 메소드는 참고로 비트맵을 파일로 저장해주는 메소드




}