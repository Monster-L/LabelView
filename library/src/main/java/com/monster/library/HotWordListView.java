package com.monster.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ScrollView;

/**
 * <pre>
 *     @author : monster
 *     time   : 2017/11/15
 *     desc   : 热词列表view
 *     version: 1.0
 * </pre>
 */


public class HotWordListView extends FrameLayout {

    private HotWordView mHotWordView;
    private OnItemClickListener mOnItemClickListener;
    private int mHorizontalGap;
    private int mVerticalGap;

    public HotWordListView(Context context) {
        this(context, null);
    }

    public HotWordListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HotWordListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.HotWordListView, defStyle, 0);
        mHorizontalGap = a.getDimensionPixelSize(R.styleable.HotWordListView_horizontal_gap, PxUtils.dp2px(getContext(),16));
        mVerticalGap = a.getDimensionPixelSize(R.styleable.HotWordListView_vertical_gap, PxUtils.dp2px(getContext(), 16));
        a.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ScrollView scrollView = new ScrollView(getContext());
        mHotWordView = new HotWordView(getContext());
        mHotWordView.setHorizontalGap(mHorizontalGap);
        mHotWordView.setVerticalGap(mVerticalGap);
        scrollView.addView(mHotWordView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(scrollView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void setAdapter(BaseAdapter adapter) {
        if (adapter == null || adapter.getCount() <= 0) {
            return;
        }
        for (int i = 0, count = adapter.getCount(); i < count; i++) {
            final View item = adapter.getView(i, null, this);
            if (item == null) {
                continue;
            }
            if (mOnItemClickListener != null) {
                final int finalI = i;
                item.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(item, finalI);
                    }
                });
            }
            mHotWordView.addItemView(item);
        }
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        if (mHotWordView == null || mHotWordView.getChildCount() <= 0) {
            return;
        }
        for (int i = 0, count = mHotWordView.getChildCount(); i < count; i++) {
            final View view = mHotWordView.getChildAt(i);
            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(view, finalI);
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}
