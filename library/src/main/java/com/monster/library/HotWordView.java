package com.monster.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * <pre>
 *     @author : monster
 *     time   : 2017/11/09
 *     desc   : 热词view
 *     version: 1.0
 * </pre>
 */


public class HotWordView extends ViewGroup {
    private int mHorizontalGap = PxUtils.dp2px(getContext(), 16);
    private int mVerticalGap = PxUtils.dp2px(getContext(), 16);
    private LayoutParams mLayoutParams;

    public HotWordView(Context context) {
        this(context, null);
    }

    public HotWordView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HotWordView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mLayoutParams = new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    public void addItemView(View view) {
        addView(view, mLayoutParams);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        MarginLayoutParams params = (MarginLayoutParams) getLayoutParams();
        int leftMargin = params.leftMargin;
        int topMargin = params.topMargin;

        int tempY = topMargin;
        int tempX = leftMargin;
        // 最后一行高度
        int lastLineHeight = 0;
        for (int i = 0, count = getChildCount(); i < count; i++) {
            final View child = getChildAt(i);
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            if (i == count - 1) {
                lastLineHeight = childHeight;
            }
            if (tempX + childWidth >= widthSize) {
                tempY = tempY + childHeight + mVerticalGap;
                tempX = leftMargin;
            }
            tempX = tempX + childWidth + mHorizontalGap;
        }
        // 最终高度为最后一行y坐标 + 行高 + padding + 底部margin
        int height = tempY + lastLineHeight + mVerticalGap + params.bottomMargin;
        setMeasuredDimension(widthSize, Math.max(heightSize, height));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        if (count <= 0) {
            return;
        }
        int tempX = l;
        int tempY = t;
        int width = r - l;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            if (tempX + childWidth >= width) {
                tempX = l;
                tempY = tempY + childHeight + mVerticalGap;
            }
            child.layout(tempX, tempY, tempX + childWidth, tempY + childHeight);
            tempX = tempX + childWidth + mHorizontalGap;
        }
    }

    public void setHorizontalGap(int horizontalGap) {
        mHorizontalGap = horizontalGap;
    }

    public void setVerticalGap(int verticalGap) {
        mVerticalGap = verticalGap;
    }
}
