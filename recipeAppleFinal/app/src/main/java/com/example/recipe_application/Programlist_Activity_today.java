package com.example.recipe_application;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Programlist_Activity_today extends ActionBarActivity implements AdapterView.OnItemClickListener {//몇화 요리명 셰프이름보여주는
    ArrayList<Fruit> al = new ArrayList<Fruit>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheff_program_click_list);

        // ListView 로 카카오톡 만들기
        //    1. 다량의 데이터
        //    2. Adapter (데이터와 view의 연결 관계를 정의)
        //    3. AdapterView (ListView)
        Fruit m1 = new Fruit();
        BitmapDrawable drawable1 = (BitmapDrawable) getResources().getDrawable(R.drawable.to1);
        Bitmap bitmap1 = drawable1.getBitmap();
        BitmapDrawable drawable2 = (BitmapDrawable) getResources().getDrawable(R.drawable.to2_main);
        Bitmap bitmap2 = drawable2.getBitmap();
        al.add(new Fruit("[1]","","얼큰홍합탕",bitmap1));
        al.add(new Fruit("[2]","","토마토왕돈까스",bitmap2));


        // adapter
        KakaoAdapter adapter = new KakaoAdapter(
                getApplicationContext(), // 현재 화면의 제어권자
                R.layout.activity_cheff_program_click_list_row,             // 한행을 그려줄 layout
                al);                     // 다량의 데이터

        ListView lv = (ListView)findViewById(R.id.program_click_list);
        lv.setAdapter(adapter);

        // 이벤트 처리
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d("test", "아이템클릭, postion : " + position +
                        ", id : " + id);
            }
        });
        lv.setOnItemClickListener(this);

    } // end of onCreate

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position ==0 ) {
            Intent intent = new Intent(Programlist_Activity_today.this, Programlist_Click_Activity_today_00.class);
            startActivity(intent);
        }
        else if(position ==1 ) {
            Intent intent = new Intent(Programlist_Activity_today.this, Programlist_Click_Activity_ref_01.class);
            startActivity(intent);
        }
    }
} // end of class


