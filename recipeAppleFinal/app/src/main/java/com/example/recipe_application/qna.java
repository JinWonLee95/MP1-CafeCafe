package com.example.recipe_application;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;


public class qna extends ActionBarActivity {
    private ArrayList<String> mGroupList = null;
    private ArrayList<ArrayList<String>> mChildList = null;
    private ArrayList<String> mChildListContent = null;
    private ExpandableListView mListView;

    private ArrayList<String> parentItems = new ArrayList<String>();
    private ArrayList<ArrayList<String>> childItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna);
        setLayout();

        mGroupList = new ArrayList<String>();
        mChildList = new ArrayList<ArrayList<String>>();
        mChildListContent = new ArrayList<String>();

        setmGroupList();
        setmChildList();



        mChildList.add(mChildListContent);
        mChildList.add(mChildListContent);

        mListView.setAdapter(new BaseExpandableAdapter(this, parentItems, childItems));


        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_qna, menu);
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

    private void setLayout(){
        mListView = (ExpandableListView)findViewById(R.id.qna_list);
    }

    public void setmGroupList() {
        parentItems.add("글을 작성할 때 칸 추가는 어떻게 하나요?");
        parentItems.add("개발자 정보");

    }

    public void setmChildList() {
        // Android
        ArrayList<String> child = new ArrayList<String>();
        child.add("화면 오른쪽 하단에 있는 +버튼을 누르면 누른만큼 작성할 칸이 추가됩니다.\n");
        childItems.add(child);

        child = new ArrayList<String>();
        child.add("손준택 전소향 윤승원 이진원 박찬일");
        childItems.add(child);
    }

}
