package com.example.recipe_application;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EveryRecipe_Activity extends Activity{
    ArrayList<my_image_item> array_right;
    ArrayList<my_image_item> array_left;
    ArrayAdapter<my_image_item> listAdapterleft;
    ArrayAdapter<my_image_item> listAdapterright;
    ListView left_listView;
    ListView right_listView;
    SNSDBAdapter db;
    ArrayList<SNS_Item> dbarraylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        left_listView = (ListView)findViewById(R.id.left_list);//왼쪽 listview선언 //얘는 홀수
        right_listView = (ListView)findViewById(R.id.right_list);//왼쪽 listview선언 //얘는 짝수

        db = new SNSDBAdapter(this);
        db.open();
        dbarraylist = db.selectAllPersonList();
        db.close();
        array_left = new ArrayList<my_image_item>();//arraylist 선언
        array_right = new ArrayList<my_image_item>();//arraylist 선언

        for(int i = 0 ; i < dbarraylist.size(); i++) {
            if(i%2 == 0) {
                SNS_Item snsitem = dbarraylist.get(i);
                String save = snsitem.getPhoto1();
                Bitmap bit =  StringToBitMap(save);
                my_image_item it1 = new my_image_item(snsitem.getTitle(), snsitem.getUserid(), bit);
                array_left.add(it1);
            }else{
                SNS_Item snsitem = dbarraylist.get(i);
                String save = snsitem.getPhoto1();
                Bitmap bit =  StringToBitMap(save);
                my_image_item it1 = new my_image_item(snsitem.getTitle(), snsitem.getUserid(), bit);
                array_right.add(it1);
            }
        }
        listAdapterleft = new Image_listAdapter(this,R.layout.activity_image_list_item,array_left);//listadapter에 적용
        listAdapterright = new Image_listAdapter(this,R.layout.activity_image_list_item,array_right);//listadapter에 적용
        left_listView.setAdapter(listAdapterleft);//리스트어뎁터
        right_listView.setAdapter(listAdapterright);//리스트어뎁터
        left_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int realnum = position*2;
                String title = dbarraylist.get(realnum).getTitle();
                Log.e("title: ",title);
                String userid = dbarraylist.get(realnum).getUserid();
                Log.e("userid: ",userid);
                Intent intent = new Intent(EveryRecipe_Activity.this , innerContent_Acitivity.class);
                intent.putExtra("title",title);
                intent.putExtra("userid",userid);
                startActivity(intent);
            }
        });
        right_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int realnum = position*2+1;
                String title = dbarraylist.get(realnum).getTitle();
                Log.e("title: ",title);
                String userid = dbarraylist.get(realnum).getUserid();
                Log.e("userid: ",userid);
                Intent intent = new Intent(EveryRecipe_Activity.this , innerContent_Acitivity.class);
                intent.putExtra("title",title);
                intent.putExtra("userid",userid);
                startActivity(intent);
            }
        });
        setListViewHeightBasedOnChildren(left_listView);
       // setListViewHeightBasedOnChildren(right_listView);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_, menu);
        return true;
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


 /*   @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(EveryRecipe_Activity.this , innerContent_Acitivity.class);
        startActivity(intent);
    }*/
    private static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }//스크롤 뷰 내의 리스트뷰 높이 갱신해주는 메소드

}
