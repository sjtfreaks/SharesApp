package com.sjtfreaks.jet.sharesapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.sjtfreaks.jet.sharesapp.R;
import com.sjtfreaks.jet.sharesapp.adapter.SharesSearchAdapter;
import com.sjtfreaks.jet.sharesapp.bean.ShareData;
import com.sjtfreaks.jet.sharesapp.util.PicassoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jet on 2018-10-23.
 */

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_shares;
    private Button bt_shares;
    private ImageView iv_1;
    private ImageView iv_2;
    private WindowManager windowManager;
    private int width;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
    }

    private void init() {
        ;//get系统服务
        windowManager = (WindowManager) SearchActivity.this.getSystemService(Context.WINDOW_SERVICE);
        //获取屏幕宽度
        width = windowManager.getDefaultDisplay().getWidth();
        et_shares = findViewById(R.id.et_shares);
        bt_shares = findViewById(R.id.bt_shares);
        bt_shares.setOnClickListener(this);
        iv_1 = findViewById(R.id.iv_1);
        iv_2 = findViewById(R.id.iv_2);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_shares:
                String number = et_shares.getText().toString().trim();
                String url = "http://image.sinajs.cn/newchart/daily/n/" + number + ".gif";
                String url_2 = "http://image.sinajs.cn/newchart/min/n/" + number + ".gif";
                if(!TextUtils.isEmpty(number)){

                    //指定大小
                    PicassoUtils.loadImageViewSize(SearchActivity.this,url,iv_1,width,500);
                    PicassoUtils.loadImageViewSize(SearchActivity.this,url_2,iv_2,width,500);
                }else {
                    Toast.makeText(this,"输入框不得为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


}
