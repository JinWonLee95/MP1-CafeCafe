package com.example.recipe_application;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mypage_Activity extends Activity implements View.OnClickListener {
    Button everyrecipe;
    Button cheffrecipe;
    Button recorecipe;
    Button myrecipe;
    Button wirtebutton;
    Button logoutbutton;
    Button qnabutton;
    Button infoedit;
    TextView userid;
    TextView username;
    TextView userphone;;
    EditText editname;
    EditText editphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        everyrecipe = (Button) findViewById(R.id.mypage_every);
        cheffrecipe = (Button) findViewById(R.id.mypage_my);
        recorecipe = (Button) findViewById(R.id.mypage_recom);
        infoedit = (Button) findViewById(R.id.infoedit);
        myrecipe = (Button) findViewById(R.id.mypage_myrecipe);
        wirtebutton = (Button) findViewById(R.id.mypage_write);
        logoutbutton = (Button) findViewById(R.id.mypage_logout);
        qnabutton = (Button) findViewById(R.id.mypage_QNA);


        userid = (TextView) findViewById(R.id.userid);
        username = (TextView) findViewById(R.id.username);
        userphone = (TextView) findViewById(R.id.userphonenum);

        SharedPreferences setting;
        setting = getSharedPreferences("setting", 0);
        userid.setText(setting.getString("userid", "thswnsxor123"));
        username.setText(setting.getString("username", "thswnsxor123"));
        userphone.setText(setting.getString("userphone", "thswnsxor123"));

        everyrecipe.setOnClickListener(this);
        cheffrecipe.setOnClickListener(this);
        recorecipe.setOnClickListener(this);

        myrecipe.setOnClickListener(this);
        wirtebutton.setOnClickListener(this);
        logoutbutton.setOnClickListener(this);
        qnabutton.setOnClickListener(this);
        infoedit.setOnClickListener(this);

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

    public void update(){
        String name =editname.getText().toString();
        String phonenumber = editphone.getText().toString();

        updateData(name, phonenumber);
    }

    private void updateData(String name, String phonenumber){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramUsername = params[0];
                String paramPhoneNumber = params[1];

                String name =editname.getText().toString();
                String phonenumber = editphone.getText().toString();
                String id = userid.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("id",id));
                Log.e("넘어간 id : ", id);
                nameValuePairs.add(new BasicNameValuePair("phonenumber", phonenumber));
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://14.63.222.0/Update.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name, phonenumber);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.mypage_every : {//모두의 레피시 클릭
                Intent intent1 = new Intent(Mypage_Activity.this,EveryRecipe_Activity.class);
                startActivity(intent1);
                break;
            }
            case R.id.mypage_my : {//셰프 레피시 클릭
                Intent intent2 = new Intent(Mypage_Activity.this,CheffRecipe_Activity.class);
                startActivity(intent2);
                break;
            }
            case R.id.mypage_recom : {//추천클릭
                Intent intent3 = new Intent(Mypage_Activity.this,RecommandRecipe_Activity.class);
                startActivity(intent3);
                break;
            }
            case R.id.mypage_myrecipe : {//내가쓴글
                Intent intent4 = new Intent(Mypage_Activity.this,MyRecipe_Activity.class);
                startActivity(intent4);
                break;
            }  case R.id.mypage_write : {//글쓰기
                Intent intent5 = new Intent(Mypage_Activity.this,Write_Activity.class);
                startActivity(intent5);
                break;
            }  case R.id.mypage_logout : {//로그아웃
                Intent intent6 = new Intent(Mypage_Activity.this,Login_Activity.class);
                startActivity(intent6);
                break;
            }  case R.id.mypage_QNA : {//QNa
                Intent intent7 = new Intent(Mypage_Activity.this,qna.class);
                startActivity(intent7);
                break;
            }
            case R.id.infoedit : {//정보수정화면
           /*     Intent intent8 = new Intent(Mypage_Activity.this,InfoEdit_Acitivity.class);
                startActivity(intent8);*/
                Context mContext = getApplicationContext();
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.activity_infoedit,(ViewGroup) findViewById(R.id.layout_root));

                AlertDialog.Builder aDialog = new AlertDialog.Builder(Mypage_Activity.this);
                aDialog.setTitle("정보수정");
                aDialog.setView(layout);
                editname = (EditText) layout.findViewById(R.id.editname);
                editphone = (EditText) layout.findViewById(R.id.editphone);

                aDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        update();

                        SharedPreferences setting;
                        setting = getSharedPreferences("setting", 0);
                        //String saveid = setting.getString("userid","못받음");
                        SharedPreferences.Editor editor;
                        editor= setting.edit();
                        editor.putString("username",editname.getText().toString());
                        editor.putString("userphone", editphone.getText().toString());
                        editor.commit();

                        username.setText(setting.getString("username", "몰라임마"));
                        userphone.setText(setting.getString("userphone", "몰라임마"));


                    }
                });
                aDialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog ad = aDialog.create();
                ad.show();

                break;
            }



        }
    }
}
