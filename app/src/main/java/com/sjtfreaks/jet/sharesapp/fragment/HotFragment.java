package com.sjtfreaks.jet.sharesapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sjtfreaks.jet.sharesapp.R;
import com.sjtfreaks.jet.sharesapp.activity.AddActivity;
import com.sjtfreaks.jet.sharesapp.activity.LookActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jet on 2018-10-23.
 */

public class HotFragment extends Fragment implements View.OnClickListener {
    Banner banner;
    private Button bt_add;
    private Button bt_look;

    public static HotFragment newInstance(String name){
        Bundle args = new Bundle();
        args.putString("name", name);
        HotFragment fragment = new HotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot,null);
        initView(view);
        return view;

    }

    private void initView(View view) {
        bt_add = view.findViewById(R.id.bt_add);
        bt_add.setOnClickListener(this);
        bt_look = view.findViewById(R.id.bt_look);
        bt_look.setOnClickListener(this);


        //轮播图
        banner = (Banner) view.findViewById(R.id.banner1);
        //本地图片数据（资源文件）
        List<Integer> list=new ArrayList<>();
        list.add(R.mipmap.m1);
        list.add(R.mipmap.m2);
        list.add(R.mipmap.m3);
        list.add(R.mipmap.m4);
        banner.setImages(list)
                .setImageLoader(new GlideImageLoader())
                .start();
    }
    //加载本地图片
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_add:
                startActivity(new Intent(getActivity(),AddActivity.class));
                break;
            case R.id.bt_look:
                startActivity(new Intent(getActivity(),LookActivity.class));
                break;
        }
    }

}
