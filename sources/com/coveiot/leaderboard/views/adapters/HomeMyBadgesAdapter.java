package com.coveiot.leaderboard.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.base.BaseAdapter;
import com.coveiot.leaderboard.base.BaseViewHolder;
import com.coveiot.leaderboard.callbacks.IMyBadgeItemClick;
/* loaded from: classes9.dex */
public class HomeMyBadgesAdapter extends BaseAdapter<MyBadgesModel.DataBean.BadgesBean, HomeMyBadgesHolder> {

    /* renamed from: a  reason: collision with root package name */
    public IMyBadgeItemClick f7233a;

    /* loaded from: classes9.dex */
    public class HomeMyBadgesHolder extends BaseViewHolder<MyBadgesModel.DataBean.BadgesBean> {

        /* renamed from: a  reason: collision with root package name */
        public ConstraintLayout f7234a;
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
                HomeMyBadgesAdapter.this.f7233a.onMyBadgeItemClick(this.h);
            }
        }

        public HomeMyBadgesHolder(View view) {
            super(view);
            this.f7234a = (ConstraintLayout) view.findViewById(R.id.baseLayout);
            this.mBadgeNameTv = (TextView) view.findViewById(R.id.tv_badge_name);
            this.mBadgeIv = (ImageView) view.findViewById(R.id.iv_achievements);
        }

        @Override // com.coveiot.leaderboard.base.BaseViewHolder
        public void onBindView(Context context, MyBadgesModel.DataBean.BadgesBean badgesBean, int i) {
            Glide.with(context).m30load(badgesBean.getBadgeLevels().get(0).getLevelImageUrl()).placeholder(R.drawable.ic_rank_loader_badge).into(this.mBadgeIv);
            this.mBadgeNameTv.setText(badgesBean.getBadgeName());
            this.f7234a.setOnClickListener(new a(badgesBean));
        }
    }

    public HomeMyBadgesAdapter(Context context, IMyBadgeItemClick iMyBadgeItemClick) {
        super(context);
        this.f7233a = iMyBadgeItemClick;
    }

    @Override // com.coveiot.leaderboard.base.BaseAdapter
    public HomeMyBadgesHolder getViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return new HomeMyBadgesHolder(layoutInflater.inflate(R.layout.leader_home_my_badges_item, viewGroup, false));
    }
}
