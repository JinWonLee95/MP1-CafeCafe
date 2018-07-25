package com.example.recipe_application;

import java.io.Serializable;

public class SNS_Item implements Serializable {


        private String userid = "";
        private String title = "";
        private String tip= "";
        private String ingre= "";
        private String photo1= "";
        private String content1= "";
        private String photo2= "";
        private String content2= "";
        private String photo3= "";
        private String content3= "";
        private String photo4= "";
        private String content4= "";
        private int photonum=0;
    private int id;

    public int getPhotonum() {
        return photonum;
    }

    public void setPhotonum(int photonum) {
        this.photonum = photonum;
    }

        public SNS_Item(){

        }
        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        public String getIngre() {
            return ingre;
        }

        public void setIngre(String ingre) {
            this.ingre = ingre;
        }

        public String getPhoto1() {
            return photo1;
        }

        public void setPhoto1(String photo1) {
            this.photo1 = photo1;
        }

        public String getContent1() {
            return content1;
        }

        public void setContent1(String content1) {
            this.content1 = content1;
        }

        public String getPhoto2() {
            return photo2;
        }

        public void setPhoto2(String photo2) {
            this.photo2 = photo2;
        }

        public String getContent2() {
            return content2;
        }

        public void setContent2(String content2) {
            this.content2 = content2;
        }

        public String getPhoto3() {
            return photo3;
        }

        public void setPhoto3(String photo3) {
            this.photo3 = photo3;
        }

        public String getContent3() {
            return content3;
        }

        public void setContent3(String content3) {
            this.content3 = content3;
        }

        public String getPhoto4() {
            return photo4;
        }

        public void setPhoto4(String photo4) {
            this.photo4 = photo4;
        }

        public String getContent4() {
            return content4;
        }

        public void setContent4(String content4) {
            this.content4 = content4;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        public SNS_Item(String userid, String title, String tip, String ingre, String photo1, String content1){
            this.userid = userid;
            this.title = title;
            this.tip = tip;
            this.ingre = ingre;
            this.photo1 = photo1;
            this.content1 = content1;

        }public SNS_Item(String userid, String title, String tip, String ingre, String photo1, String content1,
                         String photo2, String content2){
            this.userid = userid;
            this.title = title;
            this.tip = tip;
            this.ingre = ingre;
            this.photo1 = photo1;
            this.content1 = content1;
            this.photo2 = photo2;
            this.content2 = content2;

        }public SNS_Item(String userid, String title, String tip, String ingre, String photo1, String content1,
                         String photo2, String content2, String photo3){
            this.userid = userid;
            this.title = title;
            this.tip = tip;
            this.ingre = ingre;
            this.photo1 = photo1;
            this.content1 = content1;
            this.photo2 = photo2;
            this.content2 = content2;
            this.photo3 = photo3;

        }
        public SNS_Item(String userid, String title, String tip, String ingre, String photo1, String content1,
                        String photo2, String content2, String photo3, String content3, String photo4, String content4){
            this.userid = userid;
            this.title = title;
            this.tip = tip;
            this.ingre = ingre;
            this.photo1 = photo1;
            this.content1 = content1;
            this.photo2 = photo2;
            this.content2 = content2;
            this.photo3 = photo3;
            this.content3 = content3;
            this.photo4 = photo4;
            this.content4 = content4;

        }
    public SNS_Item(String userid, String title, String tip, String ingre, String photo1, String content1,
                    String photo2, String content2, String photo3, String content3, String photo4, String content4,int num){
        this.userid = userid;
        this.title = title;
        this.tip = tip;
        this.ingre = ingre;
        this.photo1 = photo1;
        this.content1 = content1;
        this.photo2 = photo2;
        this.content2 = content2;
        this.photo3 = photo3;
        this.content3 = content3;
        this.photo4 = photo4;
        this.content4 = content4;
        this.photonum = num;
    }
}

