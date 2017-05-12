package com.shar2wy.andtask.mainView.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shar2wy.andtask.R;
import com.shar2wy.andtask.models.NavItem;
import com.shar2wy.andtask.models.News;

import java.util.List;

/**
 * Created by shar2wy on 5/10/17.
 */

public class NavigationItemsAdapter extends RecyclerView.Adapter<NavigationItemsAdapter.NavViewHolder> {

    Context mContext;
    List<NavItem> navItemsList;
    OnNavItemClickListener mOnNavClickListener;

    public NavigationItemsAdapter(Context mContext, List<NavItem> navItemsList) {
        this.mContext = mContext;
        this.navItemsList = navItemsList;
    }

    @Override
    public NavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nav_layout, parent, false);
        return new NavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NavViewHolder holder, int position) {

        final NavItem navItem = navItemsList.get(position);

        holder.navTitle.setText(navItem.getTitle());
        holder.navImage.setImageResource(navItem.getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnNavClickListener != null) {
                    mOnNavClickListener.OnNavItemClick(navItem);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return navItemsList.size();
    }

    public void setmOnNavItemClickListener(OnNavItemClickListener listener) {
        mOnNavClickListener = listener;
    }

    public interface OnNavItemClickListener {

        void OnNavItemClick(NavItem navItem);
    }

    public static class NavViewHolder extends RecyclerView.ViewHolder {
        TextView navTitle;
        ImageView navImage;

        public NavViewHolder(View v) {
            super(v);
            navTitle = (TextView) v.findViewById(R.id.nav_item_title);
            navImage = (ImageView) v.findViewById(R.id.nav_item_image);
        }
    }
}
