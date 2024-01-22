package com.coveiot.leaderboard.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.base.BaseAdapter;
import com.coveiot.leaderboard.base.BaseViewHolder;
import com.coveiot.leaderboard.callbacks.IMyBadgeItemClick;
/* loaded from: classes9.dex */
public class HomeMyDailyBadgesAdapter extends BaseAdapter<MyBadgesModel.DataBean.BadgesBean, HomeMyBadgesHolder> {

    /* renamed from: a  reason: collision with root package name */
    public IMyBadgeItemClick f7235a;
    public Boolean b;

    /* loaded from: classes9.dex */
    public class HomeMyBadgesHolder extends BaseViewHolder<MyBadgesModel.DataBean.BadgesBean> {
        public TextView mBadgeDescTv;
        public ImageView mBadgeIv;
        public TextView mBadgeNameTv;

        /* loaded from: classes9.dex */
        public class a implements View.OnClickListener {
            public final /* synthetic */ MyBadgesModel.DataBean.BadgesBean h;

            public a(MyBadgesModel.DataBean.BadgesBean badgesBean) {
                this.h = badgesBean;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeMyDailyBadgesAdapter.this.f7235a.onMyBadgeItemClick(this.h);
            }
        }

        public HomeMyBadgesHolder(View view) {
            super(view);
            this.mBadgeNameTv = (TextView) view.findViewById(R.id.tv_badge_name);
            this.mBadgeDescTv = (TextView) view.findViewById(R.id.tv_badge_desc);
            this.mBadgeIv = (ImageView) view.findViewById(R.id.iv_badge);
        }

        @Override // com.coveiot.leaderboard.base.BaseViewHolder
        public void onBindView(Context context, MyBadgesModel.DataBean.BadgesBean badgesBean, int i) {
            Glide.with(context).m30load(badgesBean.getBadgeLevels().get(0).getLevelImageUrl()).placeholder(R.drawable.ic_rank_loader_badge).into(this.mBadgeIv);
            this.mBadgeNameTv.setText(badgesBean.getBadgeName());
            this.itemView.setOnClickListener(new a(badgesBean));
        }
    }

    public HomeMyDailyBadgesAdapter(Context context, IMyBadgeItemClick iMyBadgeItemClick, Boolean bool) {
        super(context);
        this.b = Boolean.FALSE;
        this.f7235a = iMyBadgeItemClick;
        this.b = bool;
    }

    @Override // com.coveiot.leaderboard.base.BaseAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (!this.b.booleanValue() || this.mData.size() <= 3) {
            return this.mData.size();
        }
        return 3;
    }

    @Override // com.coveiot.leaderboard.base.BaseAdapter
    public HomeMyBadgesHolder getViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return new HomeMyBadgesHolder(layoutInflater.inflate(R.layout.item_badge, viewGroup, false));
    }
}
