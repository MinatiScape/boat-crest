package com.coveiot.leaderboard.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.coveiot.coveaccess.leaderboard.model.AllBadgesModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.base.BaseAdapter;
import com.coveiot.leaderboard.base.BaseViewHolder;
import com.coveiot.leaderboard.callbacks.IAllBadgeItemClick;
/* loaded from: classes9.dex */
public class AllBadgesAdapter extends BaseAdapter<AllBadgesModel.DataBean.BadgesBean, AllBadgesHolder> {

    /* renamed from: a  reason: collision with root package name */
    public IAllBadgeItemClick f7227a;

    /* loaded from: classes9.dex */
    public class AllBadgesHolder extends BaseViewHolder<AllBadgesModel.DataBean.BadgesBean> {
        public RelativeLayout mBadgeBaseLayout;
        public ImageView mBadgeInfo;
        public ImageView mBadgeIv;
        public TextView mBadgeNameTv;
        public View mView;

        /* loaded from: classes9.dex */
        public class a implements View.OnClickListener {
            public final /* synthetic */ AllBadgesModel.DataBean.BadgesBean h;

            public a(AllBadgesModel.DataBean.BadgesBean badgesBean) {
                this.h = badgesBean;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AllBadgesAdapter.this.f7227a.onBadgeInfoClick(this.h);
            }
        }

        public AllBadgesHolder(View view) {
            super(view);
            this.mBadgeIv = (ImageView) view.findViewById(R.id.badgeIv);
            this.mBadgeNameTv = (TextView) view.findViewById(R.id.badgeName);
            this.mBadgeInfo = (ImageView) view.findViewById(R.id.badgeInfo);
            this.mBadgeBaseLayout = (RelativeLayout) view.findViewById(R.id.badge_base_layout);
            this.mView = view.findViewById(R.id.view);
        }

        @Override // com.coveiot.leaderboard.base.BaseViewHolder
        public void onBindView(Context context, AllBadgesModel.DataBean.BadgesBean badgesBean, int i) {
            Glide.with(context).m30load(badgesBean.getBadgeImageUrl()).into(this.mBadgeIv);
            this.mBadgeNameTv.setText(badgesBean.getBadgeName());
            this.mBadgeBaseLayout.setOnClickListener(new a(badgesBean));
            if (i == AllBadgesAdapter.this.getItemCount() - 1) {
                this.mView.setVisibility(8);
            } else {
                this.mView.setVisibility(0);
            }
        }
    }

    public AllBadgesAdapter(Context context, IAllBadgeItemClick iAllBadgeItemClick) {
        super(context);
        this.f7227a = iAllBadgeItemClick;
    }

    @Override // com.coveiot.leaderboard.base.BaseAdapter
    public AllBadgesHolder getViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return new AllBadgesHolder(layoutInflater.inflate(R.layout.my_badge_list_item, viewGroup, false));
    }
}
