package com.diffey.view.rxzhihu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.bean.MenuItem;

import java.util.List;

/**
 * Created by diff on 2016/2/3.
 */
public class MainMenuAdapter extends BaseAdapter {
    private Context mContext;
    private List<MenuItem> menuItemList;

    public MainMenuAdapter(Context context, List<MenuItem> menuItemList) {
        mContext = context;
        this.menuItemList = menuItemList;
    }

    @Override
    public int getCount() {
        return menuItemList == null ? 0 : menuItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_main_menu, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.item_main_menu_img);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.item_main_menu_txt);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(menuItemList.get(position).getResId());
        viewHolder.textView.setText(menuItemList.get(position).getTitle());

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
