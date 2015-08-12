package com.michaelcarrano.learn_colors;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by michaelcarrano on 1/11/14.
 */
public class GridViewAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater = null;

    private List<ColorsModel> mColors;

    private Activity mContext;

    public GridViewAdapter(Activity ctx, List<ColorsModel> colors) {
        mLayoutInflater = ctx.getLayoutInflater();
        mColors = colors;
        mContext = ctx;
    }

    @Override
    public int getCount() {
        return mColors.size();
    }

    @Override
    public Object getItem(int position) {
        return mColors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        ColorsModel myColors = (ColorsModel) getItem(position);

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.gridview_adapter, parent, false);

            holder = new ViewHolder();
            holder.color = (TextView) convertView.findViewById(R.id.color);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // TODO: move this calculation out of getView();
        int main = mContext.findViewById(R.id.main_layout).getHeight();
        int question = mContext.findViewById(R.id.question_text).getHeight();

        holder.color.setHeight((main - question) / 3);
        holder.color.setBackgroundColor(Color.parseColor(myColors.getHex()));
        holder.color.setTextColor(Color.parseColor(myColors.getHex()));
        holder.color.setText(myColors.getName());

        return convertView;
    }

    public static class ViewHolder {
        public TextView color;
    }
}
