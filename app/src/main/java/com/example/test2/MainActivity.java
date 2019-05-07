package com.example.test2;

//import java.io.IOException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
        import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;


        import android.content.res.Resources;


        import android.view.View.OnClickListener;

import java.util.List;


public class MainActivity<dbHelper> extends AppCompatActivity implements AdapterView.OnClickListener {

    MyDatabaseHelper dbHelper;
    Button mButton1;
    Button mButton2;
    Button mButton3;
    TextView tx;
    private Spinner 餐馆;
    private Spinner 肉类;
    private Spinner 素类;
    public String str;
    public String str2;
    public String str3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);//生成数据库
        dbHelper = new MyDatabaseHelper(this, "List.db", null, 1);

        tx = findViewById(R.id.显示);
        mButton1 = findViewById(R.id.生成);
        mButton2 = findViewById(R.id.随机);
        mButton3 = findViewById(R.id.插入);
        餐馆 = findViewById(R.id.餐馆);
        肉类 = findViewById(R.id.肉类);
        素类 = findViewById(R.id.素类);
        str = (String) 餐馆.getSelectedItem();
        str2 = (String) 肉类.getSelectedItem();
        str3 = (String) 素类.getSelectedItem();
        餐馆.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                //拿到被选择项的值
                str = (String) 餐馆.getSelectedItem();
                //把该值传给 TextView
            }

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        肉类.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                //拿到被选择项的值
                str2 = (String) 肉类.getSelectedItem();
                //把该值传给 TextView
            }

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        素类.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                //拿到被选择项的值
                str3 = (String) 素类.getSelectedItem();
                //把该值传给 TextView
            }

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        mButton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {//产生菜品按钮设置
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.rawQuery("SELECT * from List where 餐馆=? and 肉类=? and 素类=?", new String[]{str,str2,str3});
                while(cursor.moveToNext()){
                        String 餐馆 = cursor.getString(cursor.getColumnIndex("餐馆"));
                        String 肉类 = cursor.getString(cursor.getColumnIndex("肉类"));
                        String 素类 = cursor.getString(cursor.getColumnIndex("素类"));
                        String 菜名 = cursor.getString(cursor.getColumnIndex("菜名"));
                        tx.setText("你想吃的是："+菜名);
                }
                cursor.close();
                db.close();
            }

        });
        mButton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM List order by newid()",null);
                while(cursor.moveToNext()) {
                    String 菜名 = cursor.getString(cursor.getColumnIndex("菜名"));
                    tx.setText("已经为你随机一个菜：" + 菜名);
                }
                cursor.close();
                db.close();
            }//按钮随机产生菜品设置

//            @Override
//            public void onClick(View v) {
//                String[] xiaochuArrays;//成员变量
//// 获得欢迎语资源
//                    String xiaochuTips = null;
//                    Resources suiji2 =getResources();
//                    xiaochuArrays = suiji2.getStringArray(R.array.小厨菜单);
//                    int id = (int) (Math.random()*(xiaochuArrays.length-1));//随机产生一个index索引
//                    xiaochuTips = xiaochuArrays[id];
            });
//        });
//    }


        Button createDatabase=(Button)findViewById(R.id.sql生成);
        createDatabase.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("餐馆","农家小厨");
                values.put("素类","青椒");
                values.put("肉类","牛肉");
                values.put("菜名","青椒炒牛肉");
                db.insert("List",null,values);
                values.clear();
                values.put("餐馆","农家小厨");
                values.put("素类","青菜");
                values.put("肉类","鸡肉");
                values.put("菜名","手撕鸡");
                db.insert("List",null,values);
                values.clear();
                values.put("餐馆","农家小厨");
                values.put("素类","支竹");
                values.put("肉类","猪肉");
                values.put("菜名","支竹肉片");
                db.insert("List",null,values);
                values.clear();
                values.put("餐馆","农家小厨");
                values.put("素类","辣椒");
                values.put("肉类","鸡肉");
                values.put("菜名","辣子鸡");
                db.insert("List",null,values);
                values.clear();
                values.put("餐馆","农家小厨");
                values.put("素类","凉瓜");
                values.put("肉类","牛肉");
                values.put("菜名","凉瓜炒牛肉");
                db.insert("List",null,values);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

}