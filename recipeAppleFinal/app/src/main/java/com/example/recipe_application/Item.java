package com.example.recipe_application;

/**
 * Created by 나라사랑 on 2015-12-13.
 */
public class Item {
    private String[] mData;

    public Item(String[] data){


        mData = data;
    }

    public Item(String Member_Phonenumber, String Member_Password, String Member_ID, String Member_Name){

        mData = new String[4];
        mData[0] = Member_Phonenumber;
        mData[1] = Member_Password;
        mData[2] = Member_ID;
        mData[3] = Member_Name;

    }

    public String[] getData(){
        return mData;
    }

    public String getData(int index){
        return mData[index];
    }

    public void setData(String[] data){
        mData = data;
    }

}
