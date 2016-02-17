package com.diffey.view.rxzhihu.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diffey.view.rxzhihu.R;
import com.diffey.view.rxzhihu.bean.StoriesEntity;
import com.diffey.view.rxzhihu.db.bean.NewBean;
import com.diffey.view.rxzhihu.db.dao.NewDao;
import com.diffey.view.rxzhihu.util.IntentUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by diff on 2016/2/4.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHodler> {

    private Context mContext;
    private ArrayList<StoriesEntity> storiesList;
    private long lastPos = -1;
    private NewDao newDao;

    public MainAdapter(Context mContext) {
        this.mContext = mContext;
        this.newDao = new NewDao(mContext);
    }

    @Override
    public MainHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new MainHodler(view);
    }

    @Override
    public void onBindViewHolder(final MainHodler holder, final int position) {
        final StoriesEntity sto = storiesList.get(position);
        if (sto == null) {
            return;
        }

        if (sto.isRead()) {
            setTextColor(holder.itemMainTxtTitle, R.color.colorddd);
        } else {
            setTextColor(holder.itemMainTxtTitle, R.color.color666);
        }
        holder.itemMainTxtTitle.setText(sto.getTitle());

        List<String> imgs = sto.getImages();
        if (imgs != null && imgs.size() > 0) {
            holder.itemMainImg.setImageURI(Uri.parse(imgs.get(0)));
        }

        holder.itemMainRl.setOnClickListener(v -> {
            if (!sto.isRead()) {
                setTextColor(holder.itemMainTxtTitle, R.color.colorddd);
                sto.setRead(true);
                new Thread(() -> newDao.addNewBean(new NewBean(sto.getId()))).start();
            }
            IntentUtils.toStoryActivity(mContext, position, storiesList);
        });
        startAnimator(holder.itemMainCard, position);
    }

    private void setTextColor(TextView textView, int color) {
        textView.setTextColor(ContextCompat.getColor(mContext, color));
    }

    private void startAnimator(View view, long position) {
        if (position > lastPos) {
            view.startAnimation(AnimationUtils.loadAnimation(this.mContext, R.anim.item_bottom_in));
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return storiesList == null ? 0 : storiesList.size();
    }

    @Override
    public void onViewDetachedFromWindow(MainAdapter.MainHodler holder) {
        holder.itemMainCard.clearAnimation();
    }


    public void changeData(ArrayList<StoriesEntity> list) {
        storiesList = list;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<StoriesEntity> list) {
        if (storiesList == null) {
            changeData(list);
        } else {
            storiesList.addAll(list);
            notifyDataSetChanged();
        }
    }

    class MainHodler extends RecyclerView.ViewHolder {
        @Bind(R.id.item_main_txt_title)
        TextView itemMainTxtTitle;

        @Bind(R.id.item_main_img)
        SimpleDraweeView itemMainImg;

        @Bind(R.id.item_main_rl)
        RelativeLayout itemMainRl;

        @Bind(R.id.item_main_card)
        CardView itemMainCard;


        public MainHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
