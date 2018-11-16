package com.sjtfreaks.jet.sharesapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.sjtfreaks.jet.sharesapp.R;
import com.sjtfreaks.jet.sharesapp.activity.WebActivity;
import com.sjtfreaks.jet.sharesapp.adapter.HomeAdapter;
import com.sjtfreaks.jet.sharesapp.bean.HomeData;
import com.sjtfreaks.jet.sharesapp.util.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jet on 2018-10-23.
 */

public class NewsFragment extends Fragment{
    private ListView mListView;
    private List<HomeData> mList = new ArrayList<>();
    private List<String> mListTitle = new ArrayList<>();
    private List<String> mListUrl = new ArrayList<>();
    public static NewsFragment newInstance(String name){
        Bundle args = new Bundle();
        args.putString("name", name);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,null);
        initView(view);
        return view;

    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.mListView);
        mList.clear();
        //
        String url = "http://cre.dp.sina.cn/api/v3/get?";
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                //Toast.makeText(getActivity(),t, Toast.LENGTH_SHORT).show();
                parsingJson(t);
                L.i("json:"+t);
            }
        });
        //点击
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                L.i("position:"+position);
                Intent intent = new Intent(getActivity(), WebActivity.class);
                //2 way chuan zhi BUNdle
                intent.putExtra("title",mListTitle.get(position));
                intent.putExtra("url",mListUrl.get(position));
                startActivity(intent);
            }
        });
        //setListViewHeightBasedOnChildren(mListView);

    }

    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonList = jsonObject.getJSONArray("data");

            for (int i = 0; i<jsonList.length();i++){
                JSONObject json = (JSONObject) jsonList.get(i);

                HomeData data = new HomeData();

                String url = json.getString("surl") + "?http=fromhttp";
                String title = json.getString("title");

                data.setTitle(json.getString("title"));
                data.setThumb(json.getString("thumb"));

                mList.add(data);
                mListTitle.add(title);
                mListUrl.add(url);
            }
            HomeAdapter adapter = new HomeAdapter(getActivity(),mList);
            mListView.setAdapter(adapter);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
