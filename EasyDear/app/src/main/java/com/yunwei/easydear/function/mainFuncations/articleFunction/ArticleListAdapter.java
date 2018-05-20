package com.yunwei.easydear.function.mainFuncations.articleFunction;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yunwei.easydear.R;

import java.util.List;

/**
 * Created by LJH on 2017/1/14.
 */

public class ArticleListAdapter extends BaseAdapter {

    private Context mContext;
    private List<ArticleItemEntity> mArticleItemList;

    public ArticleListAdapter(Activity context) {
        mContext = context;
    }

    public void setArticleItemList(List<ArticleItemEntity> list) {
        mArticleItemList = list;
    }

    @Override
    public int getCount() {
        return mArticleItemList == null ? 0 : mArticleItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return mArticleItemList == null ? null : mArticleItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.article_list_item, null);
            holder.typeTextView = (TextView) convertView.findViewById(R.id.article_list_type);
            holder.titleTextView = (TextView) convertView.findViewById(R.id.article_list_title);
            holder.dateTextView = (TextView) convertView.findViewById(R.id.article_list_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        holder.typeTextView.setText(mArticleItemList.get(position).getType());
//        holder.titleTextView.setText(mArticleItemList.get(position).getTitle());
//        holder.dateTextView.setText(mArticleItemList.get(position).getPubTime());
        return convertView;
    }

    private class ViewHolder {
        TextView typeTextView;
        TextView titleTextView;
        TextView dateTextView;
    }
}
