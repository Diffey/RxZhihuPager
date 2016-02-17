package com.diffey.view.rxzhihu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.bean.ChatBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diff on 2016/2/15.
 */
public class TRClientAdapter extends BaseAdapter {
    private static final int VIEW_TYPE = 2;
    public static final int TYPE_USER = 0;
    public static final int TYPE_ROBOT = 1;

    private List<ChatBean> list;
    private Context mContext;

    public TRClientAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public TRClientAdapter(Context mContext, List<ChatBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public void addData(ChatBean chatBean) {
        if (list == null) {
            list = new ArrayList<>();
        }

        list.add(chatBean);
        notifyDataSetChanged();
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            if (getItemViewType(position) == TYPE_USER) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_trc, parent, false);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.item_trc_txt);
            } else {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_trc_robot, parent, false);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.item_trc_robot_txt);
            }

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(list.get(position).getInfo());

        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }
}
