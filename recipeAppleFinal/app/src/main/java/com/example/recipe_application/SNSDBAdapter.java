package com.example.recipe_application;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SNSDBAdapter {
    private static final String DATABASE_NAME = "ITEM";
    private static final int DATABASE_VERSION = 1;
    //기본으로 주어지는 것들
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private final Context context;


    public SNSDBAdapter(Context ctx) {
        context = ctx;
        dbHelper = new DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SNSDBAdapter open() throws SQLException {//SQLException 통해서 디비어뎁터 열기
        db = dbHelper.getWritableDatabase();
        // 읽고 쓰기 위해 DB 연다. 권한이 없거나 디스크가 가득 차면 실패
        return this;
    }

    public void close() {
        db.close();
    }//디비 닫는 메소드

    private static final String ADRESS_KEY_USERID = "userid";
    private static final String ADRESS_KEY_TITLE = "title";
    private static final String ADRESS_KEY_TIP = "tip";
    private static final String ADRESS_KEY_INGRE= "ingre";
    private static final String ADRESS_KEY_PHOTO1= "phto1";
    private static final String ADRESS_KEY_CONTENT1= "content1";
    private static final String ADRESS_KEY_PHOTO2= "photo2";
    private static final String ADRESS_KEY_CONTENT2= "content2";
    private static final String ADRESS_KEY_PHOTO3= "photo3";
    private static final String ADRESS_KEY_CONTENT3= "content3";
    private static final String ADRESS_KEY_PHOTO4= "photo4";
    private static final String ADRESS_KEY_CONTENT4= "content4";
    private static final String ADRESS_KEY_PHOTONUM= "photonum";

    private static final int ADRESS_KEY_COLUMN_USERID = 1;
    private static final int ADRESS_KEY_COLUMN_TITLE = 2;
    private static final int ADRESS_KEY_COLUMN_TIP = 3;
    private static final int ADRESS_KEY_COLUMN_INGRE= 4;
    private static final int ADRESS_KEY_COLUMN_PHOTO1= 5;
    private static final int ADRESS_KEY_COLUMN_CONTENT1= 6;
    private static final int ADRESS_KEY_COLUMN_PHOTO2= 7;
    private static final int ADRESS_KEY_COLUMN_CONTENT2= 8;
    private static final int ADRESS_KEY_COLUMN_PHOTO3= 9;
    private static final int ADRESS_KEY_COLUMN_CONTENT3= 10;
    private static final int ADRESS_KEY_COLUMN_PHOTO4= 11;
    private static final int ADRESS_KEY_COLUMN_CONTENT4= 12;
    private static final int ADRESS_KEY_COLUMN_PHOTONUM= 13;

    private static final String ADRESS_DATABASE_TABLE = "memotable";
    private static final String ADRESS_DATABASE_CREATE = "create table "
            + ADRESS_DATABASE_TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT," + ADRESS_KEY_USERID + " TEXT, "
            + ADRESS_KEY_TITLE  + " TEXT, "+ ADRESS_KEY_TIP  + " TEXT, "+ ADRESS_KEY_INGRE  + " TEXT, "+
            ADRESS_KEY_PHOTO1  + " TEXT, "+ ADRESS_KEY_CONTENT1  + " TEXT, "+ ADRESS_KEY_PHOTO2  + " TEXT, "+
            ADRESS_KEY_CONTENT2  + " TEXT, "+ ADRESS_KEY_PHOTO3  +
            " TEXT, "+ ADRESS_KEY_CONTENT3  +" TEXT, "+ ADRESS_KEY_PHOTO4  +" TEXT, "+ ADRESS_KEY_CONTENT4  +" TEXT, "+ ADRESS_KEY_PHOTONUM + " TEXT);";

    public void insertAddress(SNS_Item object) {//입력
        ContentValues newValues = new ContentValues();

        Cursor c = db.query(ADRESS_DATABASE_TABLE, null, null, null, null,
                null, null);
        //if(c.get)
        c.moveToNext();
        c.moveToLast();


        newValues.put(ADRESS_KEY_USERID, object.getUserid());
        newValues.put(ADRESS_KEY_TITLE, object.getTitle());
        newValues.put(ADRESS_KEY_TIP, object.getTip());
        newValues.put(ADRESS_KEY_INGRE, object.getIngre());
        newValues.put(ADRESS_KEY_PHOTO1, object.getPhoto1());
        newValues.put(ADRESS_KEY_CONTENT1, object.getContent1());
        newValues.put(ADRESS_KEY_PHOTO2, object.getPhoto2());
        newValues.put(ADRESS_KEY_CONTENT2, object.getContent2());
        newValues.put(ADRESS_KEY_PHOTO3, object.getPhoto3());
        newValues.put(ADRESS_KEY_CONTENT3, object.getContent3());
        newValues.put(ADRESS_KEY_PHOTO4, object.getPhoto4());
        newValues.put(ADRESS_KEY_CONTENT4, object.getContent4());
        newValues.put(ADRESS_KEY_PHOTONUM, object.getPhotonum());



        db.insert(ADRESS_DATABASE_TABLE, null, newValues);

    }
    public SNS_Item find(String title,String userid) {//입력
       /* Cursor c = db.query(ADRESS_DATABASE_TABLE+" where title = "+"title", null, null, null, null,
                null, null);*/
        Cursor c = db.rawQuery( "select * from memotable where title=" + "'" + title + "'"+"and userid="+ "'" +userid+ "'"  , null);
        SNS_Item temp =null;
        if(c.moveToFirst()) {           //테이블 내에서 _id와 같은 id위치 찾기
            temp = new SNS_Item(
                    c.getString(ADRESS_KEY_COLUMN_USERID)
                    , c.getString(ADRESS_KEY_COLUMN_TITLE), c.getString(ADRESS_KEY_COLUMN_TIP), c.getString(ADRESS_KEY_COLUMN_INGRE)
                    , c.getString(ADRESS_KEY_COLUMN_PHOTO1), c.getString(ADRESS_KEY_COLUMN_CONTENT1), c.getString(ADRESS_KEY_COLUMN_PHOTO2), c.getString(ADRESS_KEY_COLUMN_CONTENT2)
                    , c.getString(ADRESS_KEY_COLUMN_PHOTO3), c.getString(ADRESS_KEY_COLUMN_CONTENT3), c.getString(ADRESS_KEY_COLUMN_PHOTO4), c.getString(ADRESS_KEY_COLUMN_CONTENT4),c.getInt(ADRESS_KEY_COLUMN_PHOTONUM));
            temp.setId(c.getInt(0));
        }
        c.close();

        return temp;
    }

    public void editAddress(SNS_Item object, int id) {
        ContentValues newValues = new ContentValues();

        Cursor c = db.query(ADRESS_DATABASE_TABLE, null, null, null, null,
                null, null);
        c.moveToPosition(id);//입력된 id로 이동

        newValues.put(ADRESS_KEY_USERID, object.getUserid());
        newValues.put(ADRESS_KEY_TITLE, object.getTitle());
        newValues.put(ADRESS_KEY_TIP, object.getTip());
        newValues.put(ADRESS_KEY_INGRE, object.getIngre());
        newValues.put(ADRESS_KEY_PHOTO1, object.getPhoto1());
        newValues.put(ADRESS_KEY_CONTENT1, object.getContent1());
        newValues.put(ADRESS_KEY_PHOTO2, object.getPhoto2());
        newValues.put(ADRESS_KEY_CONTENT2, object.getContent2());
        newValues.put(ADRESS_KEY_PHOTO3, object.getPhoto3());
        newValues.put(ADRESS_KEY_CONTENT3, object.getContent3());
        newValues.put(ADRESS_KEY_PHOTO4, object.getPhoto4());
        newValues.put(ADRESS_KEY_CONTENT4, object.getContent4());
        newValues.put(ADRESS_KEY_PHOTONUM, object.getPhotonum());


        db.update(ADRESS_DATABASE_TABLE, newValues, "_id = "+id, null);
    }
    public SNS_Item MoveLast(){
        Cursor c = db.query(ADRESS_DATABASE_TABLE, null, null, null, null,
                null, null);
        c.moveToLast();
        SNS_Item temp =null;
        temp = new SNS_Item(
                c.getString(ADRESS_KEY_COLUMN_USERID)
                , c.getString(ADRESS_KEY_COLUMN_TITLE), c.getString(ADRESS_KEY_COLUMN_TIP), c.getString(ADRESS_KEY_COLUMN_INGRE)
                , c.getString(ADRESS_KEY_COLUMN_PHOTO1), c.getString(ADRESS_KEY_COLUMN_CONTENT1), c.getString(ADRESS_KEY_COLUMN_PHOTO2), c.getString(ADRESS_KEY_COLUMN_CONTENT2)
                , c.getString(ADRESS_KEY_COLUMN_PHOTO3), c.getString(ADRESS_KEY_COLUMN_CONTENT3), c.getString(ADRESS_KEY_COLUMN_PHOTO4), c.getString(ADRESS_KEY_COLUMN_CONTENT4),c.getInt(ADRESS_KEY_COLUMN_PHOTONUM));
        temp.setId(c.getInt(0));
        c.close();
       return temp;
    }

    public void delAddress(int id) {


        db.delete(ADRESS_DATABASE_TABLE,"_id = "+id, null);

    }
    public SNS_Item select(int id) {
        Cursor c = db.query(ADRESS_DATABASE_TABLE+" where _id = "+id, null, null, null, null,
                null, null);
        SNS_Item temp =null;
        if(c.moveToFirst()) {           //테이블 내에서 _id와 같은 id위치 찾기
            temp = new SNS_Item(
                    c.getString(ADRESS_KEY_COLUMN_USERID)
                    , c.getString(ADRESS_KEY_COLUMN_TITLE), c.getString(ADRESS_KEY_COLUMN_TIP), c.getString(ADRESS_KEY_COLUMN_INGRE)
                    , c.getString(ADRESS_KEY_COLUMN_PHOTO1), c.getString(ADRESS_KEY_COLUMN_CONTENT1), c.getString(ADRESS_KEY_COLUMN_PHOTO2), c.getString(ADRESS_KEY_COLUMN_CONTENT2)
                    , c.getString(ADRESS_KEY_COLUMN_PHOTO3), c.getString(ADRESS_KEY_COLUMN_CONTENT3), c.getString(ADRESS_KEY_COLUMN_PHOTO4), c.getString(ADRESS_KEY_COLUMN_CONTENT4),c.getInt(ADRESS_KEY_COLUMN_PHOTONUM));
            temp.setId(c.getInt(0));
        }
        c.close();

        return temp;
    }

    public ArrayList<SNS_Item> selectAllPersonList() {
        ArrayList<SNS_Item> returnValue = new ArrayList<>();

        Cursor c = db.query(ADRESS_DATABASE_TABLE, null, null, null, null,
                null, null);

        if ((c.getCount() == 0) || !c.moveToFirst()) {

        } else if (c.moveToFirst()) {
            do {
                SNS_Item temp = new SNS_Item(
                        c.getString(ADRESS_KEY_COLUMN_USERID)
                        , c.getString(ADRESS_KEY_COLUMN_TITLE), c.getString(ADRESS_KEY_COLUMN_TIP), c.getString(ADRESS_KEY_COLUMN_INGRE)
                        , c.getString(ADRESS_KEY_COLUMN_PHOTO1), c.getString(ADRESS_KEY_COLUMN_CONTENT1), c.getString(ADRESS_KEY_COLUMN_PHOTO2), c.getString(ADRESS_KEY_COLUMN_CONTENT2)
                        , c.getString(ADRESS_KEY_COLUMN_PHOTO3), c.getString(ADRESS_KEY_COLUMN_CONTENT3), c.getString(ADRESS_KEY_COLUMN_PHOTO4), c.getString(ADRESS_KEY_COLUMN_CONTENT4),c.getInt(ADRESS_KEY_COLUMN_PHOTONUM));
                temp.setId(c.getInt(0));
                returnValue.add(temp);

            } while (c.moveToNext());
        }
        c.close();

        return returnValue;
    }

    private static class DBHelper extends SQLiteOpenHelper {//디비 헬퍼

        public DBHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory,
                        int version) {//생성자
            super(context, dbName, factory, version);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ADRESS_DATABASE_CREATE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + ADRESS_DATABASE_TABLE);

        }
    }
}
