package com.sjtfreaks.jet.sharesapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sjtfreaks.jet.sharesapp.R;
import com.sjtfreaks.jet.sharesapp.bean.GuPiao;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddActivity extends BaseActivity implements View.OnClickListener {
    private EditText goods_name;
    private EditText goods_desc;
    private EditText goods_price;
    private EditText et_icon;
    private EditText et_phone;
    private Button goods_add;

    @Overrid
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        findView();
    }

    private void findView() {
        goods_name = (EditText) findViewById(R.id.goods_name);
        goods_price = (EditText) findViewById(R.id.goods_price);
        goods_desc = (EditText) findViewById(R.id.goods_desc);
        et_icon = findViewById(R.id.et_icon);
        et_phone = findViewById(R.id.et_phone);
        goods_add = (Button) findViewById(R.id.goods_add);
        goods_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.goods_add:
                String name = goods_name.getText().toString().trim();
                String price = goods_price.getText().toString().trim();
                String desc = goods_desc.getText().toString().trim();
                String icon = et_icon.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();
                //不得为空
                if (!TextUtils.isEmpty(name)&!TextUtils.isEmpty(price)&!TextUtils.isEmpty(desc)&!TextUtils.isEmpty(icon)&!TextUtils.isEmpty(phone)){
                    GuPiao gupiao = new GuPiao();
                    gupiao.setTitle(name);
                    gupiao.setDescribe(desc);
                    gupiao.setPhone(phone);
                    gupiao.setIcon(icon);
                    gupiao.setPrice(price);
                    gupiao.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null){
                                Toast.makeText(AddActivity.this,"发布成功！期待交易吧",Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(AddActivity.this,"发布失败！失败原因："+e.toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(this,"内容不得为空",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}

