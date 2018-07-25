package com.example.recipe_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 나라사랑 on 2015-12-01.
 */
public class innerContent_Acitivity extends Activity {//모두의레시피 글화면
    ArrayList<my_innerContent_item> array;
    ArrayAdapter<my_innerContent_item> listAdapter;
    ListView listView;
    ArrayList<My_innerContent_review_item> review_array;
    ArrayAdapter<My_innerContent_review_item> review_listAdapter;
    ListView review_listView;

    Button reviewButton;
    EditText reviewEdittext;
    String edittext;
    SNSDBAdapter db;
    TextView title;
    TextView tip;
    TextView ingre;
    int photonum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_content);

        title = (TextView) findViewById(R.id.inner_title);
        tip = (TextView) findViewById(R.id.inner_tip);
        ingre = (TextView) findViewById(R.id.inner_ingre);
        listView = (ListView)findViewById(R.id.innercontent_listview);//레시피내용 리스트뷰
        review_listView = (ListView)findViewById(R.id.inner_review_listview);//댓글 내용 리스트뷰
        db = new SNSDBAdapter(this);
        Intent intent = getIntent();
        String savetitle = intent.getExtras().getString("title");
        Log.e("inner savetitle : ", savetitle);
        String saveuserid = intent.getExtras().getString("userid");
        Log.e("inner savetitle : ", saveuserid);
        db.open();
        SNS_Item finditem = db.find(savetitle, saveuserid);
        db.close();

        title.setText(finditem.getTitle());
        Log.e("set title : ", finditem.getTitle());
        tip.setText(finditem.getTip());
        Log.e("set tip : ", finditem.getTip());
        ingre.setText(finditem.getIngre());
        Log.e("set ingre : ", finditem.getIngre());
        photonum = finditem.getPhotonum();
        Log.e("photonum : ",photonum+"");
        array = new ArrayList<my_innerContent_item>();//레시피 내용 arraylist 선언
        if(photonum == 1) {
            my_innerContent_item it1 = new my_innerContent_item(null, finditem.getContent1(),finditem.getPhoto1());
            array.add(it1);
        }else if(photonum == 2){
            my_innerContent_item it1 = new my_innerContent_item(null, finditem.getContent1(),finditem.getPhoto1());
            array.add(it1);
            my_innerContent_item it2 = new my_innerContent_item(null, finditem.getContent2(),finditem.getPhoto2());
            array.add(it2);
        }else if(photonum == 3 ){
            my_innerContent_item it1 = new my_innerContent_item(null, finditem.getContent1(),finditem.getPhoto1());
            array.add(it1);
            my_innerContent_item it2 = new my_innerContent_item(null, finditem.getContent2(),finditem.getPhoto2());
            array.add(it2);
            my_innerContent_item it3 = new my_innerContent_item(null, finditem.getContent3(),finditem.getPhoto3());
            array.add(it3);
        }else if(photonum == 4 ){
            my_innerContent_item it1 = new my_innerContent_item(null, finditem.getContent1(),finditem.getPhoto1());
            array.add(it1);
            my_innerContent_item it2 = new my_innerContent_item(null, finditem.getContent2(),finditem.getPhoto2());
            array.add(it2);
            my_innerContent_item it3 = new my_innerContent_item(null, finditem.getContent3(),finditem.getPhoto3());
            array.add(it3);
            my_innerContent_item it4 = new my_innerContent_item(null, finditem.getContent4(),finditem.getPhoto4());
            array.add(it4);
        }

        listAdapter = new innerContent_ListAdapter(this, R.layout.activity_alllist_inner_list_item,array);//레시피 listadapter에 적용
        listView.setAdapter(listAdapter);//레시피 리스트어뎁터


        review_array = new ArrayList<My_innerContent_review_item>();//댓글 내용 arraylist선언
        My_innerContent_review_item r1 = new My_innerContent_review_item("thswnsxor","정말 맛있어 보여요 ","15-12-14 2:53");
        My_innerContent_review_item r2 = new My_innerContent_review_item("thswnsxor","정말 맛있어 보여요 ","15-12-14 2:53");

        review_array.add(r1);
        review_array.add(r2);


        reviewEdittext = (EditText) findViewById(R.id.inner_review_edittext);
        reviewButton = (Button)findViewById(R.id.review_button);
        reviewButton.setOnClickListener(new View.OnClickListener() {//댓글 추가 버튼 누르면
            @Override
            public void onClick(View v) {
                edittext = reviewEdittext.getText().toString();//입력한 값 가져옴

                if(edittext.length()!=0 ) {//비어있지않으면
                SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yy-MM-dd  HH:mm:ss");
                Date currentTime = new Date ( );
                String mTime = mSimpleDateFormat.format(currentTime);

                //    My_innerContent_review_item item = new My_innerContent_review_item("사용자아이디", edittext, mTime);
                    review_array.add(new My_innerContent_review_item("사용자아이디", edittext, mTime));
                    review_listAdapter.notifyDataSetChanged();
                    // EditText의 내용을 지운다.
                    reviewEdittext.setText("");
                    // 데이터가 추가된 위치(리스트뷰의 마지막)으로 포커스를 이동시킨다.
                   review_listView.setSelection(review_array.size());

                    setListViewHeightBasedOnChildren(review_listView);

                }
            }
        });

        review_listAdapter = new innerContent_review_ListAdapter(this, R.layout.activity_alllist_inner_list_review_item,review_array);
        review_listView.setAdapter(review_listAdapter);//댓글 리스트 어뎁터 적용

        setListViewHeightBasedOnChildren(listView);
        setListViewHeightBasedOnChildren(review_listView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_, menu);
        return true;
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
