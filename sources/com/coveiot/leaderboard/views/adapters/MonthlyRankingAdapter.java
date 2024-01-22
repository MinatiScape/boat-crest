package com.coveiot.leaderboard.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coveiot.coveaccess.leaderboard.model.RankHistoryModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.base.BaseAdapter;
import com.coveiot.leaderboard.base.BaseViewHolder;
import com.coveiot.leaderboard.callbacks.IRankItemClick;
/* loaded from: classes9.dex */
public class MonthlyRankingAdapter extends BaseAdapter<RankHistoryModel.DataBean.RanksBean, AllBadgesHolder> {

    /* renamed from: a  reason: collision with root package name */
    public IRankItemClick f7237a;

    /* loaded from: classes9.dex */
    public class AllBadgesHolder extends BaseViewHolder<RankHistoryModel.DataBean.RanksBean> {

        /* renamed from: a  reason: collision with root package name */
        public TextView f7238a;
        public RelativeLayout b;

        /* loaded from: classes9.dex */
        public class a implements View.OnClickListener {
            public a(AllBadgesHolder allBadgesHolder) {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        }

        public AllBadgesHolder(MonthlyRankingAdapter monthlyRankingAdapter, View view) {
            super(view);
            int i = R.id.max;
            TextView textView = (TextView) view.findViewById(i);
            TextView textView2 = (TextView) view.findViewById(R.id.min);
            this.b = (RelativeLayout) view.findViewById(R.id.baseLayout);
            this.f7238a = (TextView) view.findViewById(i);
        }

        @Override // com.coveiot.leaderboard.base.BaseViewHolder
        public void onBindView(Context context, RankHistoryModel.DataBean.RanksBean ranksBean, int i) {
            this.f7238a.setText(ranksBean.getRankDate());
            this.b.setOnClickListener(new a(this));
        }
    }

    public MonthlyRankingAdapter(Context context, IRankItemClick iRankItemClick) {
        super(context);
        this.f7237a = iRankItemClick;
    }

    @Override // com.coveiot.leaderboard.base.BaseAdapter
    public AllBadgesHolder getViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return new AllBadgesHolder(this, layoutInflater.inflate(R.layout.daily_ranking_list_item, viewGroup, false));
    }
}
