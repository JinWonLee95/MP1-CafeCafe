package com.example.recipe_application;


/**
 * Created by Administrator on 2015-12-14.
 */
public class My_innerContent_review_item {

    private String review_id; //timestart ~ timelast
    private String review_content;
    private String review_time;


    public String getReview_id() { return review_id; }
    public String getReview_content() { return review_content; }
    public String getReview_time() { return review_time; }


    public void setReview_id(String review_id){this.review_id = review_id;}
    public void setReview_content(String review_content){this.review_content = review_content;}
    public void setReview_time(String review_time){this.review_time = review_time;}



    public My_innerContent_review_item(String review_id, String review_content,String review_time){
        this.review_id = review_id;
        this.review_content = review_content;
        this.review_time = review_time;
    }
}
