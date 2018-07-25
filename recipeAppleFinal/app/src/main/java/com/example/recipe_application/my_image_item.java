package com.example.recipe_application;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2015-12-01.
 */
public class my_image_item {

        private String image_name;
        private String image_id; //timestart ~ timelast
        private Bitmap image_image;


        public String getImage_name() { return image_name; }
        public String getImage_id() { return image_id; }
        public Bitmap getImage_image() { return image_image; }


        public void setImage_name(String image_name){this.image_name = image_name;}
        public void setImage_id(String image_id){this.image_id = image_id;}
        public void setImage_image() { this.image_image = image_image; }


    public my_image_item( String image_name, String image_id,Bitmap image_image){
            this.image_name = image_name;
            this.image_id = image_id;
            this.image_image =image_image;
        }



}
