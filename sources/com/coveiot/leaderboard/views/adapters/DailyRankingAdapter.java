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
public class DailyRankingAdapter extends BaseAdapter<RankHistoryModel.DataBean.RanksBean, AllBadgesHolder> {

    /* renamed from: a  reason: collision with root package name */
    public IRankItemClick f7231a;

    /* loaded from: classes9.dex */
    public class AllBadgesHolder extends BaseViewHolder<RankHistoryModel.DataBean.RanksBean> {

        /* renamed from: a  reason: collision with root package name */
        public TextView f7232a;
        public RelativeLayout b;
        public TextView c;
        public TextView d;

        /* loaded from: classes9.dex */
        public class a implements View.OnClickListener {
            public final /* synthetic */ RankHistoryModel.DataBean.RanksBean h;

            public a(RankHistoryModel.DataBean.RanksBean ranksBean) {
                this.h = ranksBean;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DailyRankingAdapter.this.f7231a.onRankItemClick(this.h);
            }
        }

        public AllBadgesHolder(View view) {
            super(view);
            this.d = (TextView) view.findViewById(R.id.location);
            this.c = (TextView) view.findViewById(R.id.max);
            TextView textView = (TextView) view.findViewById(R.id.min);
            this.b = (RelativeLayout) view.findViewById(R.id.baseLayout);
            this.f7232a = (TextView) view.findViewById(R.id.rankObtainedDate);
        }

        @Override // com.coveiot.leaderboard.base.BaseViewHolder
        public void onBindView(Context context, RankHistoryModel.DataBean.RanksBean ranksBean, int i) {
            this.f7232a.setText(ranksBean.getRankDate());
            TextView textView = this.c;
            textView.setText("" + ranksBean.getTotalUsers());
            this.d.setText(ranksBean.getUserLocation().getLocality());
            this.b.setOnClickListener(new a(ranksBean));
        }
    }

    public DailyRankingAdapter(Context context, IRankItemClick iRankItemClick) {
        super(context);
        this.f7231a = iRankItemClick;
    }

    @Override // com.coveiot.leaderboard.base.BaseAdapter
    public AllBadgesHolder getViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        return new AllBadgesHolder(layoutInflater.inflate(R.layout.daily_ranking_list_item, viewGroup, false));
    }
}
