package com.example.recipe_application;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


public class Write_Activity extends Activity implements View.OnClickListener {
    ArrayList<my_innerContent_item> writearray;
    innerContent_write_ListAdapter write_listAdapter;
    ListView write_listView;
    EditText title;
    EditText tip;
    EditText ingre;
    Button addbutton;
    Button finishbutton;
    SNSDBAdapter db;
    private static final String TEMP_PHOTO_FILE = "temp.jpg";       // 임시 저장파일
    private static final int REQ_CODE_PICK_IMAGE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_click);
        db = new SNSDBAdapter(this);
        write_listView = (ListView)findViewById(R.id.write_click_listview);//리스트뷰
        title= (EditText) findViewById(R.id.write_title);
        tip= (EditText) findViewById(R.id.write_tip);
        ingre= (EditText) findViewById(R.id.write_ingre);
        addbutton=(Button) findViewById(R.id.write_addphoto);
        finishbutton=(Button) findViewById(R.id.write_finish);

        writearray = new ArrayList<my_innerContent_item>();//레시피 내용 arraylist 선언
        /*BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.bae1);
        Bitmap bitmap = drawable.getBitmap();
        my_innerContent_item item1 = new my_innerContent_item(bitmap,"요리설명을 해주세요.");
        writearray.add(item1);*/
        addbutton.setOnClickListener(this);
        finishbutton.setOnClickListener(this);

        write_listAdapter = new innerContent_write_ListAdapter(this, R.layout.activity_alllist_inner_list_item_edit,writearray);//레시피 listadapter에 적용
        write_listView.setAdapter(write_listAdapter);//레시피 리스트어뎁터
        setListViewHeightBasedOnChildren(write_listView);


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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.write_addphoto: {
              //  if(writearray.size()==0){
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT,      // 또는 ACTION_PICK
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");              // 모든 이미지
                    intent.putExtra("crop", "true");        // Crop기능 활성화
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());     // 임시파일 생성
                    intent.putExtra("outputFormat",         // 포맷방식
                            Bitmap.CompressFormat.JPEG.toString());

                    startActivityForResult(intent, REQ_CODE_PICK_IMAGE);
                    break;
             //   }
                //write_listAdapter.getItem(0).getInnercontent_text()
            /*    if (writearray.size() == 9) {
                    Toast.makeText(this, "최대 사진은 8개까지 가능합니다.", Toast.LENGTH_LONG);
                    return;
                } else {
                    BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.bae1);
                    Bitmap bitmap = drawable.getBitmap();
                    my_innerContent_item item2 = new my_innerContent_item(bitmap, "요리설명을 해주세요");
                    writearray.add(item2);
                    write_listAdapter.notifyDataSetChanged();
                    setListViewHeightBasedOnChildren(write_listView);
                    Log.e("높이:", writearray.size() + " 입니다");
                }
                break;*/
            }
            case R.id.write_finish : {
                SNS_Item inputitem = new SNS_Item();
                SharedPreferences setting;
                setting = getSharedPreferences("setting", 0);
                inputitem.setUserid(setting.getString("userid", "오류값"));
                inputitem.setTitle(title.getText().toString());
                Log.e("들어간 title : ", title.getText().toString());
                inputitem.setTip(tip.getText().toString());
                Log.e("들어간 tip : ", tip.getText().toString());
                inputitem.setIngre(ingre.getText().toString());
                Log.e("들어간 ingre : ", ingre.getText().toString());
                inputitem.setPhotonum(writearray.size());
                if(writearray.size()== 1){
                inputitem.setContent1(write_listAdapter.getItem(0).getInnercontent_text());
                    inputitem.setPhoto1(write_listAdapter.getItem(0).getPath());
                } else if(writearray.size() == 2){
                    inputitem.setContent1(write_listAdapter.getItem(0).getInnercontent_text());
                    inputitem.setPhoto1(write_listAdapter.getItem(0).getPath());
                    inputitem.setContent2(write_listAdapter.getItem(1).getInnercontent_text());
                    inputitem.setPhoto2(write_listAdapter.getItem(1).getPath());
                } else if(writearray.size() == 3){
                    inputitem.setContent1(write_listAdapter.getItem(0).getInnercontent_text());
                    inputitem.setPhoto1(write_listAdapter.getItem(0).getPath());
                    inputitem.setContent2(write_listAdapter.getItem(1).getInnercontent_text());
                    inputitem.setPhoto2(write_listAdapter.getItem(1).getPath());
                    inputitem.setContent3(write_listAdapter.getItem(2).getInnercontent_text());
                    inputitem.setPhoto3(write_listAdapter.getItem(2).getPath());}
                else if(writearray.size() == 4){
                    inputitem.setContent1(write_listAdapter.getItem(0).getInnercontent_text());
                    inputitem.setPhoto1(write_listAdapter.getItem(0).getPath());
                    inputitem.setContent2(write_listAdapter.getItem(1).getInnercontent_text());
                    inputitem.setPhoto2(write_listAdapter.getItem(1).getPath());
                    inputitem.setContent3(write_listAdapter.getItem(2).getInnercontent_text());
                    inputitem.setPhoto3(write_listAdapter.getItem(2).getPath());
                    inputitem.setContent4(write_listAdapter.getItem(3).getInnercontent_text());
                    inputitem.setPhoto4(write_listAdapter.getItem(3).getPath());}
             //   inputitem.setContent3();
                //   inputitem.setContent4();
                db.open();
                db.insertAddress(inputitem);
                db.close();
                finish();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      /*  if(writearray.size() != 0) {
           write_listAdapter.onActivityResult(requestCode, resultCode, data);
        }else{*/

            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
               //String ImagePath = uri.getPath();
                String ImagePath = getPath(uri);
               // String ImagePath = uri.toString();
                Bitmap selectedImage = null;
                try {
                    selectedImage = MediaStore.Images.Media.getBitmap(getContentResolver(), uri );
                } catch (IOException e) {
                    e.printStackTrace();
                }
             //   write_listAdapter.viewHolder.write_click_image.setImageBitmap(selectedImage);
                String bitstring= BitMapToString(selectedImage);
                my_innerContent_item item3 = new my_innerContent_item(selectedImage,"요리설명을 해주세요.",bitstring);
                writearray.add(item3);
                Write_Activity w = new Write_Activity();
                write_listAdapter.notifyDataSetChanged();
                setListViewHeightBasedOnChildren(write_listView);
                return;
            }
        }
        /*}
        // 갤러리의 선택 결과(경로)를 받아서 썸네일 이미지로 변환 후,
        // 이미지 뷰에 출력한다.
        Log.v("&","&^%");
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String ImagePath = getPath(uri);
            Bitmap selectedImage = null;
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(getContentResolver(), uri );
            } catch (IOException e) {
                e.printStackTrace();
            }
            write_listAdapter.viewHolder.write_click_image.setImageBitmap(selectedImage);

            my_innerContent_item item3 = new my_innerContent_item(selectedImage,"요리설명을 해주세요.");
            writearray.add(item3);
            Write_Activity w = new Write_Activity();
            write_listAdapter.notifyDataSetChanged();
        }*/


    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;}
    //저 메소드는 참고로 비트맵을 파일로 저장해주는 메소드
    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(columnIndex);
    }
}


/*
                String filename = "/sdcard/data/"+a;
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeFile(filename, bmOptions);
                write_click_image.setImageBitmap(bitmap);
                //불러낼 때

                SaveBitmapToFileCache(bmOut,"/sdcard/data/",filename); //파일 생성 (비트맵 , 파일 저장될 주소, 파일이름)
*/


/*
SaveBitmapToFileCache(bmOut,"/sdcard/data/",filename); //파일 생성 (비트맵 , 파일 저장될 주소, 파일이름)
//니핸드폰 파일 매니저에 들어가면 내장메모리라고 써져잇는부분이 sdcard고  data는 디렉토리 이름
만약에 data라는 디렉토리가 없을시에는 그게 자동 생성되니까 걱정하지말고 니가 원하는 디렉토리 이름써도 된단다
filed이름 정할때는 사진이니까 .png같이 형식을 붙여줘야하고
저 메소드는 참고로 비트맵을 파일로 저장해주는 메소드야


String filename = "/sdcard/data/"+a;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeFile(filename,bmOptions);
            imageView.setImageBitmap(bitmap);
            //불러낼 때
            이런식으로 filename에 일단 내가 설정한 주소를 써주고
            a는 아까 디비에 저장한 파일네임이야
           그후 비트맵으로 decodefile해서 만든후
이미지뷰에 set해서 보여줬어

 */