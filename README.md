# HotWordView
a ui widget for hot word

## How it looks
![](https://github.com/Monster-L/HotWordView/blob/master/device-2018-03-23-151110.png) 

## How to use

```HotWordListView``` It's easy to use like Listview.

you can set custom attributes as shown below:

```xml
<declare-styleable name="HotWordListView">
        <attr name="vertical_gap" format="dimension"></attr>
        <attr name="horizontal_gap" format="dimension"></attr>
</declare-styleable>
```
a example:

activity_main.xml:
```xml
<com.monster.library.HotWordListView
        android:id="@+id/search_hot_word_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:horizontal_gap="16dp"
        app:vertical_gap="16dp"
        android:padding="16dp"/>
```

MainActivity.java
```java
public class MainActivity extends AppCompatActivity implements HotWordListView.OnItemClickListener{

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
}
```

Adapter
```java
class HotWordAdapter extends BaseAdapter {

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
```
For more, please turn to the source code.
