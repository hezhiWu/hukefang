package com.yunwei.easydear.function.mainFuncations.articleFunction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunwei.easydear.R;

import java.util.List;

/**
 * Created by LJH on 2017/2/26.
 */
public class ShareAppAdapter extends BaseAdapter {

    private Context mContext;
    private List<AppInfo> mAppInfoList;

    public ShareAppAdapter(Context context, List<AppInfo> appInfoList) {
        mContext = context;
        mAppInfoList = appInfoList;
    }

    @Override
    public int getCount() {
        return mAppInfoList == null ? 0 : mAppInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return mAppInfoList == null ? null : mAppInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_share_app_layout, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_share_icon_iv);
            holder.textView = (TextView) convertView.findViewById(R.id.item_share_name_tv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (mAppInfoList != null && mAppInfoList.size() > 0) {
            holder.textView.setText(mAppInfoList.get(position).getLoadLabel());
            holder.imageView.setImageDrawable(mAppInfoList.get(position).getLoadIcon());
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

}
