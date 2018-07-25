package com.example.recipe_application;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Join_Activity extends Activity implements View.OnClickListener {
   // Button dubbutton;
    Button joinbutton;
    private EditText editTextName;
    private EditText editTextID;
    private EditText editTextPW;
    private EditText editTextPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
       // dubbutton = (Button) findViewById(R.id.join_dubli);
        joinbutton = (Button) findViewById(R.id.join_joinbuttont);
      //  dubbutton.setOnClickListener(this);
        joinbutton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        editTextName = (EditText) findViewById(R.id.join_name);
        editTextID = (EditText) findViewById(R.id.join_id);
        editTextPW = (EditText) findViewById(R.id.join_password);
        editTextPhoneNumber = (EditText) findViewById(R.id.join_phonenumber);

        switch(v.getId()){
            case R.id.join_joinbuttont : {//가입완료하면 로그인화면으로
                //Intent intent2 = new Intent(Join_Activity.this,Login_Activity.class);
                insert();
                //startActivity(intent2);
                //break;
            }
        }

    }

    public void insert(){
        String name = editTextName.getText().toString();
        String id = editTextID.getText().toString();
        String password = editTextPW.getText().toString();
        String phonenumber = editTextPhoneNumber.getText().toString();

        insertToDatabase(name,id,password,phonenumber);
    }

    private void insertToDatabase(String name, String id, String password, String phonenumber) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramUsername = params[0];
                String paramID = params[1];
                String paramPassword = params[2];
                String paramPhoneNumber = params[3];

                String name = editTextName.getText().toString();
                String id = editTextID.getText().toString();
                String password = editTextPW.getText().toString();
                String phonenumber = editTextPhoneNumber.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("id", id));
                nameValuePairs.add(new BasicNameValuePair("password", password));
                nameValuePairs.add(new BasicNameValuePair("phonenumber", phonenumber));
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://14.63.222.0/Register.php");
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
                Intent intent2 = new Intent(Join_Activity.this,Login_Activity.class);
                startActivity(intent2);
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name, id, password, phonenumber);
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
}
