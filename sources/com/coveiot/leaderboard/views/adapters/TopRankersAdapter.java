package com.coveiot.leaderboard.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.coveiot.coveaccess.leaderboard.model.TopRankModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.base.BaseAdapter;
import com.coveiot.leaderboard.base.BaseViewHolder;
/* loaded from: classes9.dex */
public class TopRankersAdapter extends BaseAdapter<TopRankModel.DataBean.TopRanksBean, AllBadgesHolder> {

    /* loaded from: classes9.dex */
    public class AllBadgesHolder extends BaseViewHolder<TopRankModel.DataBean.TopRanksBean> {
        public ConstraintLayout mBadgeBaseLayout;
        public TextView mRank;
        public TextView mRankerNameTv;
        public TextView mRankerSteps;
        public View mView;

        public AllBadgesHolder(View view) {
            super(view);
            this.mBadgeBaseLayout = (ConstraintLayout) view.findViewById(R.id.badge_base_layout);
            this.mRankerSteps = (TextView) view.findViewById(R.id.rankerSteps);
            this.mRankerNameTv = (TextView) view.findViewById(R.id.rankerName);
            this.mRank = (TextView) view.findViewById(R.id.tv_rank);
            this.mView = view.findViewById(R.id.view);
        }

        @Override // com.coveiot.leaderboard.base.BaseViewHolder
        public void onBindView(Context context, TopRankModel.DataBean.TopRanksBean topRanksBean, int i) {
            this.mRankerNameTv.setText(topRanksBean.getUserName());
            TextView textView = this.mRankerSteps;
            textView.setText("" + topRanksBean.getSteps() + TopRankersAdapter.this.mContext.getString(R.string.steps_with_space));
            if (topRanksBean.getRank() != null) {
                if (topRanksBean.getRank().intValue() >= 1000) {
                    TextView textView2 = this.mRank;
                    textView2.setText("" + (topRanksBean.getRank().intValue() / 1000) + TopRankersAdapter.this.mContext.getString(R.string.k));
                } else {
                    TextView textView3 = this.mRank;
                    textView3.setText("" + topRanksBean.getRank());
                }
            }
            if (i == TopRankersAdapter.this.getItemCount() - 1) {
                this.mView.setVisibility(8);
            } else {
                this.mView.setVisibility(0);
            }
        }
    }

    public TopRankersAdapter(Context context) {
        super(context);
    }

    @Override // com.coveiot.leaderboard.base.BaseAdapter
    public AllBadgesHolder getViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return new AllBadgesHolder(layoutInflater.inflate(R.layout.top_rankers_list_item, viewGroup, false));
    }
}
