package com.example.recipe_application;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class Programlist_Click_Activity_my_00 extends ActionBarActivity {
    ArrayList<Bitmap> al = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheff_program_click_selectcontent_explain);

        // ListView 로 카카오톡 만들기
        //    1. 다량의 데이터
        //    2. Adapter (데이터와 view의 연결 관계를 정의)
        //    3. AdapterView (ListView)
        Fruit m1 = new Fruit();

        BitmapDrawable drawable1 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm1);
        Bitmap bitmap1 = drawable1.getBitmap();
        BitmapDrawable drawable2 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm2);
        Bitmap bitmap2 = drawable2.getBitmap();
        BitmapDrawable drawable3 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm3);
        Bitmap bitmap3 = drawable3.getBitmap();
        BitmapDrawable drawable4 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm4);
        Bitmap bitmap4 = drawable4.getBitmap();
        BitmapDrawable drawable5 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm5);
        Bitmap bitmap5 = drawable5.getBitmap();
        BitmapDrawable drawable6 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm6);
        Bitmap bitmap6 = drawable6.getBitmap();
        BitmapDrawable drawable7 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm7);
        Bitmap bitmap7 = drawable7.getBitmap();
        BitmapDrawable drawable8 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm8);
        Bitmap bitmap8 = drawable8.getBitmap();
        BitmapDrawable drawable9 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm9);
        Bitmap bitmap9 = drawable9.getBitmap();
        BitmapDrawable drawable10 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm10);
        Bitmap bitmap10 = drawable10.getBitmap();
        BitmapDrawable drawable11 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm11);
        Bitmap bitmap11 = drawable11.getBitmap();
        BitmapDrawable drawable12 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm12);
        Bitmap bitmap12 = drawable12.getBitmap();
        BitmapDrawable drawable13 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm13);
        Bitmap bitmap13 = drawable13.getBitmap();
        BitmapDrawable drawable14 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm14);
        Bitmap bitmap14 = drawable14.getBitmap();
        BitmapDrawable drawable15 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm15);
        Bitmap bitmap15 = drawable15.getBitmap();
        BitmapDrawable drawable16 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm16);
        Bitmap bitmap16 = drawable16.getBitmap();
        BitmapDrawable drawable17 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm17);
        Bitmap bitmap17 = drawable17.getBitmap();
        BitmapDrawable drawable18 = (BitmapDrawable) getResources().getDrawable(R.drawable.mm18);
        Bitmap bitmap18 = drawable18.getBitmap();
        al.add(bitmap1);
        al.add(bitmap2);
        al.add(bitmap3);
        al.add(bitmap4);
        al.add(bitmap5);
        al.add(bitmap6);
        al.add(bitmap7);
        al.add(bitmap8);
        al.add(bitmap9);
        al.add(bitmap10);
        al.add(bitmap11);
        al.add(bitmap12);
        al.add(bitmap13);
        al.add(bitmap14);
        al.add(bitmap15);
        al.add(bitmap16);
        al.add(bitmap17);
        al.add(bitmap18);

        // adapter
        KakaoAdapter1 adapter = new KakaoAdapter1(
                getApplicationContext(), // 현재 화면의 제어권자
                R.layout.activity_cheff_program_click_selectcontent_explain_row,             // 한행을 그려줄 layout
                al);                     // 다량의 데이터

        ListView lv = (ListView)findViewById(R.id.program_click_list_click_list);
        lv.setAdapter(adapter);

    } // end of onCreate
} // end of class

