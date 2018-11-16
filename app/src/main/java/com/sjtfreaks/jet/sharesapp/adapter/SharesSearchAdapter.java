package com.sjtfreaks.jet.sharesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.sjtfreaks.jet.sharesapp.R;
import com.sjtfreaks.jet.sharesapp.bean.ShareData;
import com.sjtfreaks.jet.sharesapp.util.PicassoUtils;

import java.util.List;

public class SharesSearchAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShareData> mList;
    private ShareData data;
    private LayoutInflater inflater;
    private WindowManager windowManager;
    private int width;

    public SharesSearchAdapter(Context mContext,List<ShareData> mList){
        this.mContext = mContext;
        this.mList = mList;
        //获取系统服务
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
            viewHolder = new SharesSearchAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.item_search,null);
            viewHolder.iv_RK =(ImageView) convertView.findViewById(R.id.iv_RK);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String iv_RK = data.getRK();
        data = mList.get(position);
        //指定大小
        PicassoUtils.loadImageViewSize(mContext,iv_RK,viewHolder.iv_RK,width,500);
        return convertView;
    }

    class ViewHolder{
        private ImageView iv_RK;//图片

    }
}
