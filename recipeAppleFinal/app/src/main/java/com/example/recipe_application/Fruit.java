package com.example.recipe_application;

import android.graphics.Bitmap;

/*
class KakaoAdapter extends BaseAdapter {
    Context context;     // 현재 화면의 제어권자
    int layout;              // 한행을 그려줄 layout
    ArrayList<Fruit> al;     // 다량의 데이터
    LayoutInflater inf; // 화면을 그려줄 때 필요
    public KakaoAdapter(Context context, int layout, ArrayList<Fruit> al) {
        this.context = context;
        this.layout = layout;
        this.al = al;
        this.inf = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() { // 총 데이터의 개수를 리턴
        return al.size();
    }
    @Override
    public Object getItem(int position) { // 해당번째의 데이터 값
        return al.get(position);
    }
    @Override
    public long getItemId(int position) { // 해당번째의 고유한 id 값
        return position;
    }
    @Override // 해당번째의 행에 내용을 셋팅(데이터와 레이아웃의 연결관계 정의)
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inf.inflate(layout, null);


        TextView tvName=(TextView)convertView.findViewById(R.id.tvNum);
        TextView tvSex =(TextView)convertView.findViewById(R.id.tvName);
        TextView tvBirthDay=(TextView)convertView.findViewById(R.id.tvChef);

        Fruit m = al.get(position);


        tvName.setText(m.name);
        tvSex.setText(m.isDomestic);
        tvBirthDay.setText(m.shipDate);
        return convertView;
    }
}
*/
class Fruit { // 자바 빈 (java Bean)

    String num = "";
    String name = "";
    String chef = "";
    Bitmap img;

    // 생성자가 있으면 객체 생성시 편리하다
    public Fruit(String num, String name, String chef, Bitmap img) {
        this.img = img;
        this.num = num;
        this.name = name;
        this.chef = chef;
    }
    public Fruit() {}// 기존 코드와 호환을 위해서 생성자 작업시 기본생성자도 추가
}


