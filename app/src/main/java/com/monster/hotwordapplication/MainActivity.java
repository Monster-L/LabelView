package com.monster.hotwordapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.monster.library.HotWordListView;

import java.util.ArrayList;
import java.util.List;

import static com.monster.hotwordapplication.HotWordBean.NORMAL;
import static com.monster.hotwordapplication.HotWordBean.RED;

public class MainActivity extends AppCompatActivity implements HotWordListView.OnItemClickListener{

    private HotWordListView mHotWordView;
    private HotWordAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        List<HotWordBean> testData = createTestData();
        mHotWordView = findViewById(R.id.search_hot_word_view);
        mAdapter = new HotWordAdapter(this, testData);
        mHotWordView.setAdapter(mAdapter);
        mHotWordView.setOnItemClickListener(this);
    }

    private List<HotWordBean> createTestData() {
        List<HotWordBean> list = new ArrayList<>();
        list.add(new HotWordBean("Amazon", RED));
        list.add(new HotWordBean("Google", RED));
        list.add(new HotWordBean("Facebook", RED));
        list.add(new HotWordBean("Cool Keyboard", RED));
        list.add(new HotWordBean("GitHub", RED));
        list.add(new HotWordBean("Hello"));
        list.add(new HotWordBean("If You Like"));
        list.add(new HotWordBean("Please"));
        list.add(new HotWordBean("Start"));
        list.add(new HotWordBean("and"));
        list.add(new HotWordBean("Focus"));
        list.add(new HotWordBean("Monster-L"));
        list.add(new HotWordBean("Thank you"));
        return list;
    }

    @Override
    public void onItemClick(View view, int position) {
        if (mAdapter == null) {
            return;
        }
        HotWordBean hotWordBean = (HotWordBean) mAdapter.getItem(position);
        if (hotWordBean == null) {
            return;
        }
        Toast.makeText(this, hotWordBean.getWord(), Toast.LENGTH_SHORT).show();
    }

    static class HotWordAdapter extends BaseAdapter {

        private Context mContext;
        private List<HotWordBean> mList;

        public HotWordAdapter(Context context, List<HotWordBean> list) {
            mContext = context;
            mList = list;
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList == null ? null : mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            return mList.get(position) == null ? 0 : mList.get(position).getType();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            TextView textView;
            switch (getItemViewType(position)) {
                case RED:
                    textView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_hot_word_red, null);
                    break;
                case NORMAL:
                default:
                    textView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_hot_word, null);
                    break;
            }
            HotWordBean bean = mList.get(position);
            textView.setText(bean.getWord());
            return textView;
        }
    }
}
