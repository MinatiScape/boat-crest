package com.coveiot.leaderboard.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.base.BaseAdapter;
import com.coveiot.leaderboard.base.BaseViewHolder;
import com.coveiot.leaderboard.callbacks.IMyBadgeItemClick;
import com.coveiot.leaderboard.utils.LeaderboardUtils;
/* loaded from: classes9.dex */
public class MyBadgesAdapter extends BaseAdapter<MyBadgesModel.DataBean.BadgesBean, MyBadgesHolder> {

    /* renamed from: a  reason: collision with root package name */
    public IMyBadgeItemClick f7241a;

    /* loaded from: classes9.dex */
    public class MyBadgesHolder extends BaseViewHolder<MyBadgesModel.DataBean.BadgesBean> {
        public RelativeLayout mBadgeBaseLayout;
        public ImageView mBadgeInfo;
        public ImageView mBadgeIv;
        public TextView mBadgeNameTv;
        public TextView mObtainedDate;
        public View mView;

        /* loaded from: classes9.dex */
        public class a implements View.OnClickListener {
            public final /* synthetic */ MyBadgesModel.DataBean.BadgesBean h;

            public a(MyBadgesModel.DataBean.BadgesBean badgesBean) {
                this.h = badgesBean;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MyBadgesAdapter.this.f7241a.onMyBadgeItemClick(this.h);
            }
        }

        public MyBadgesHolder(View view) {
            super(view);
            this.mObtainedDate = (TextView) view.findViewById(R.id.obtainedDate);
            this.mBadgeBaseLayout = (RelativeLayout) view.findViewById(R.id.badge_base_layout);
            this.mBadgeInfo = (ImageView) view.findViewById(R.id.badgeInfo);
            this.mBadgeNameTv = (TextView) view.findViewById(R.id.badgeName);
            this.mBadgeIv = (ImageView) view.findViewById(R.id.badgeIv);
            this.mView = view.findViewById(R.id.view);
        }

        @Override // com.coveiot.leaderboard.base.BaseViewHolder
        public void onBindView(Context context, MyBadgesModel.DataBean.BadgesBean badgesBean, int i) {
            Glide.with(context).m30load(badgesBean.getBadgeLevels().get(0).getLevelImageUrl()).into(this.mBadgeIv);
            this.mBadgeNameTv.setText(badgesBean.getBadgeName());
            if (badgesBean.getBadgeLevels().get(0).getObtainedDate() != null) {
                this.mObtainedDate.setText(LeaderboardUtils.getLocalDate(badgesBean.getBadgeLevels().get(0).getObtainedDate()));
            }
            this.mBadgeBaseLayout.setOnClickListener(new a(badgesBean));
            if (i == MyBadgesAdapter.this.getItemCount() - 1) {
                this.mView.setVisibility(8);
            } else {
                this.mView.setVisibility(0);
            }
        }
    }

    public MyBadgesAdapter(Context context, IMyBadgeItemClick iMyBadgeItemClick) {
        super(context);
        this.f7241a = iMyBadgeItemClick;
    }

    @Override // com.coveiot.leaderboard.base.BaseAdapter
    public MyBadgesHolder getViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return new MyBadgesHolder(layoutInflater.inflate(R.layout.my_badge_list_item, viewGroup, false));
    }
}
