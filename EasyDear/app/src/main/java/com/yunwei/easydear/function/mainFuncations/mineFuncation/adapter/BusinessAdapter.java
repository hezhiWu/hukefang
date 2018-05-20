package com.yunwei.easydear.function.mainFuncations.mineFuncation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easydear.BuildConfig;
import com.yunwei.easydear.R;
import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.data.BusinessEntity;
import com.yunwei.easydear.view.RoundedBitmapImageViewTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-01-08
 * Time: 12:45
 * Version:1.0
 */

public class BusinessAdapter extends BaseAdapter {

    private Context context;

    private String[] total = new String[4];
    List<BusinessEntity> entities;

    public BusinessAdapter(Context context) {
        this.context = context;
        entities = new ArrayList<>();
    }

    public void addData(List<BusinessEntity> list) {
        entities.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (entities.size()<4){
            return entities.size();
        }
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return total[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_business_layout, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.ItemBusiness_icon_iv);
            holder.textView = (TextView) convertView.findViewById(R.id.ItemBusiness_name_textView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (entities != null && entities.size() > 0) {
            holder.textView.setText(entities.get(position).getBusinessName());
            Glide.with(context).load(BuildConfig.DOMAI + entities.get(position).getLogo()).asBitmap().centerCrop().into(new RoundedBitmapImageViewTarget(holder.imageView));
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
