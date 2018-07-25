package com.example.recipe_application;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2015-12-13.
 */
public class my_innerContent_item {
    private String innercontent_text; //timestart ~ timelast
    private Bitmap innercontent_image;
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    String path;


    public String getInnercontent_text() { return innercontent_text; }
    public Bitmap getInnercontent_image() { return innercontent_image; }


    public void setInnercontent_text(String innercontent_text){this.innercontent_text = innercontent_text;}
    public void setInnercontent_image(Bitmap innercontent_image){this.innercontent_image = innercontent_image;}


    public my_innerContent_item(Bitmap innercontent_image, String innercontent_text){
        this.innercontent_text = innercontent_text;
        this.innercontent_image = innercontent_image;

    }public my_innerContent_item(Bitmap innercontent_image, String innercontent_text,String path){
        this.innercontent_text = innercontent_text;
        this.innercontent_image = innercontent_image;
        this.path = path;

    }
}
