package com.example.recipe_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class CheffRecipe_Activity extends Activity implements View.OnClickListener {

    ImageButton imageref;
    ImageButton image3dae;
    ImageButton imagehome;
    ImageButton imagetoday;
    ImageButton imagewed;
    ImageButton imagemy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_program);
        imageref = (ImageButton) findViewById(R.id.imageView_ref);
        imageref.setOnClickListener(this);
        image3dae = (ImageButton) findViewById(R.id.imageView_3dae);
        image3dae.setOnClickListener(this);
        imagehome = (ImageButton) findViewById(R.id.imageView_home);
        imagehome.setOnClickListener(this);
        imagetoday = (ImageButton) findViewById(R.id.imageView_today);
        imagetoday.setOnClickListener(this);
        imagewed = (ImageButton) findViewById(R.id.imageView_wed);
        imagewed.setOnClickListener(this);
        imagemy = (ImageButton) findViewById(R.id.imageView_my);
        imagemy.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imageView_ref :{
                Intent intent = new Intent(CheffRecipe_Activity.this,Programlist_Activity_ref.class);
                startActivity(intent);
                break;
            } case R.id.imageView_home :{
                Intent intent = new Intent(CheffRecipe_Activity.this,Programlist_Activity_home.class);
                startActivity(intent);
                break;
            } case R.id.imageView_my :{
                Intent intent = new Intent(CheffRecipe_Activity.this,Programlist_Activity_my.class);
                startActivity(intent);
                break;
            } case R.id.imageView_wed :{
                Intent intent = new Intent(CheffRecipe_Activity.this,Programlist_Activity_wed.class);
                startActivity(intent);
                break;
            }case R.id.imageView_3dae :{
                Intent intent = new Intent(CheffRecipe_Activity.this,Programlist_Activity_3dae.class);
                startActivity(intent);
                break;
            }
        }
    }
}
