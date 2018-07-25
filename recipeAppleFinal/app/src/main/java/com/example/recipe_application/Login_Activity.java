package com.example.recipe_application;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Login_Activity extends Activity implements View.OnClickListener {
    Button loginbutton;
    Button joinbutton;
    EditText loginid;
    EditText loginpass;

    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;


    phpDown task;
    ArrayList<Item> listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        task = new phpDown();
        listItem= new ArrayList<Item>();
        loginid = (EditText) findViewById(R.id.login_id);
        loginpass = (EditText) findViewById(R.id.login_password);
        loginbutton = (Button) findViewById(R.id.login_button);
        joinbutton = (Button) findViewById(R.id.login_join);
        loginbutton.setOnClickListener(this);
        joinbutton.setOnClickListener(this);
    }

    void login(){
        try{
            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://14.63.222.0/Login.php"); // make sure the url is correct.
            //add your data
            nameValuePairs = new ArrayList<NameValuePair>(2);
            // Always use the same variable name for posting i.e the android side variable name and php side variable name should be similar,
            nameValuePairs.add(new BasicNameValuePair("username",loginid.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
            nameValuePairs.add(new BasicNameValuePair("password",loginpass.getText().toString().trim()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            //Execute HTTP Post Request
            response=httpclient.execute(httppost);

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            System.out.println("Response : " + response);
            runOnUiThread(new Runnable() {
                public void run() {
                    dialog.dismiss();
                }
            });

            if(response.equalsIgnoreCase("User Found")){
                task.execute("http://14.63.222.0/LoginSJT.php");
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(Login_Activity.this, "Login Success", Toast.LENGTH_SHORT).show();

                        }
                    });
//데이터 가져오기 시작



//

            }else{
                showAlert();
            }

        }catch(Exception e){
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }

    public void showAlert(){
        Login_Activity.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login_Activity.this);
                builder.setTitle("Login Error.");
                builder.setMessage("User not Found.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login_button : {//로그인
                dialog = ProgressDialog.show(Login_Activity.this, "",
                        "Validating user...", true);
                new Thread(new Runnable() {
                    public void run() {
                        login();
                    }
                }).start();

                break;

            }
            case R.id.login_join : {//가입하기
                //사용자 이름
                //사용자 휴대폰번호

                Intent intent2 = new Intent(Login_Activity.this,Join_Activity.class);
                startActivity(intent2);
                break;
            }
        }

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

//여기부터 폰넘버 네임
private class phpDown extends AsyncTask<String, Integer,String> {


    @Override
    protected String doInBackground(String... urls) {
        StringBuilder jsonHtml = new StringBuilder();
        try{
            // 연결 url 설정
            URL url = new URL(urls[0]);
            // 커넥션 객체 생성
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            // 연결되었으면.
            if(conn != null){
                conn.setConnectTimeout(10000);
                conn.setUseCaches(false);
                // 연결되었음 코드가 리턴되면.
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    for(;;){
                        // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                        String line = br.readLine();
                        if(line == null) break;
                        // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                        jsonHtml.append(line + "\n");
                    }
                    br.close();
                }
                conn.disconnect();
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return jsonHtml.toString();

    }

    protected void onPostExecute(String str){

        String Member_Phonenumber;
        String Member_Password;
        String Member_ID;
        String Member_Name;
        try{

            JSONObject root = new JSONObject(str);
            JSONArray ja = root.getJSONArray("results");
            for(int i=0; i<ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);
                Member_Phonenumber = jo.getString("Member_Phonenumber");
                Member_Password = jo.getString("Member_Password");
                Member_ID = jo.getString("Member_ID");
                Member_Name = jo.getString("Member_Name");
                if (Member_ID.equals(loginid.getText().toString())){
                    listItem.add(new Item(Member_Phonenumber, Member_Password, Member_ID, Member_Name));
                    Log.e("여기", Member_Phonenumber + Member_Password + Member_ID + Member_Name);
                }
            }
        }catch(JSONException e) {
            e.printStackTrace();
        }
       /* Log.e("시발 : " , "Member_Phonenumber :"+listItem.get(0).getData(0)+
                "\nMember_Password:"+ listItem.get(0).getData(1)+
                "\nMember_ID:"+ listItem.get(0).getData(2)+
                "\nMember_Name:"+ listItem.get(0).getData(3));
*/
        SharedPreferences setting;
        setting = getSharedPreferences("setting", 0);
        SharedPreferences.Editor editor;
        editor= setting.edit();
        editor.putString("userid", loginid.getText().toString());
        editor.putString("userpw", loginpass.getText().toString());
        editor.putString("username", listItem.get(0).getData(3));
        editor.putString("userphone", listItem.get(0).getData(0));
        editor.commit();
        startActivity(new Intent(Login_Activity.this, Mypage_Activity.class));
        finish();
    }


}
}
