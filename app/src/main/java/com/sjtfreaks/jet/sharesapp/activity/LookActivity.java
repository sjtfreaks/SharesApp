package com.sjtfreaks.jet.sharesapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sjtfreaks.jet.sharesapp.R;
import com.sjtfreaks.jet.sharesapp.bean.GuPiao;
import com.sjtfreaks.jet.sharesapp.util.StaticClass;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LookActivity extends BaseActivity {
    private ListView listView;
    private GuPiao date;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look);
        //连接Bmob id
        Bmob.initialize(this, StaticClass.BMOB_ID);
        findView();
        loadDate();
    }

    private void loadDate() {
        StaticClass.data.clear();
        BmobQuery<GuPiao> bmobQuery = new BmobQuery<GuPiao>();
        bmobQuery.findObjects(new FindListener<GuPiao>() {
            @Override
            public void done(List<GuPiao> list, BmobException e) {
                if (e == null){
                    Toast.makeText(LookActivity.this,"连接成功！",Toast.LENGTH_SHORT).show();
                    int n = list.size();
                    //遍历所有数据
                    for (int i = 0; i< n ;i++){
                        StaticClass.data.add(list.get(i));
                    }
                    Collections.sort(StaticClass.data,c);
                    for (int i = 0; i <StaticClass.data.size();i++){
                        System.out.println(StaticClass.data.get(i).getTitle() + " " +
                                StaticClass.data.get(i).getDescribe() + StaticClass.data.get(i).getPhone());
                    }
                    initData();
                }else {
                    Toast.makeText(LookActivity.this, "连接失败！失败原因：" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData() {
        listView.setAdapter(new ItemListAdapter());
    }
    Comparator c = new Comparator<GuPiao>() {
        @Override
        public int compare(GuPiao o1, GuPiao o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };
    //适配器
    class ItemListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return StaticClass.data.size();
        }

        @Override
        public Object getItem(int position) {
            return StaticClass.data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_gupian,null);

                viewHolder = new ViewHolder();
                viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_title);
                viewHolder.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
                viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
                viewHolder.tv_icon = (TextView) convertView.findViewById(R.id.tv_icon);
                viewHolder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            date = StaticClass.data.get(position);
            viewHolder.tv_name.setText(date.getTitle());
            viewHolder.tv_price.setText(date.getPrice());
            viewHolder.tv_desc.setText(date.getDescribe());
            viewHolder.tv_icon.setText(date.getIcon());
            viewHolder.tv_phone.setText(date.getPhone());


            return convertView;
        }
        public class ViewHolder{
            public TextView tv_name;
            public TextView tv_price;
            public TextView tv_desc;
            public TextView tv_icon;
            public TextView tv_phone;

        }
    }
    private void findView() {
        listView = (ListView) findViewById(R.id.listView);
    }
}
