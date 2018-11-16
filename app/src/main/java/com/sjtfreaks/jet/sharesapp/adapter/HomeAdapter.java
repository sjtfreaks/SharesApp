package com.sjtfreaks.jet.sharesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjtfreaks.jet.sharesapp.R;
import com.sjtfreaks.jet.sharesapp.bean.HomeData;
import com.sjtfreaks.jet.sharesapp.util.PicassoUtils;

import java.util.List;

/**
 * Created by jet on 2018-10-24.
 */

public class HomeAdapter extends BaseAdapter {

    private Context mContext;
    private List<HomeData> mList;
    private HomeData data;
    private LayoutInflater inflater;

    private WindowManager windowManager;
    private int width;
    public HomeAdapter (Context mContext, List<HomeData> mList){
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //get系统服务
        windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        //获取屏幕宽度
        width = windowManager.getDefaultDisplay().getWidth();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_new_home,null);

            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.iv = (ImageView)convertView.findViewById(R.id.iv_news);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        data = mList.get(position);

        String iv = data.getThumb();
        viewHolder.tv_name.setText(data.getTitle());

        //指定大小
        PicassoUtils.loadImageViewSize(mContext,iv,viewHolder.iv,width,500);
        return convertView;
    }

    class ViewHolder{
        private TextView tv_name;//标题
        private ImageView iv;//图片
    }
}
