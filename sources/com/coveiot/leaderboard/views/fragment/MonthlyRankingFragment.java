package com.coveiot.leaderboard.views.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.leaderboard.CoveLeaderboardApi;
import com.coveiot.coveaccess.leaderboard.model.FilterType;
import com.coveiot.coveaccess.leaderboard.model.RankHistoryModel;
import com.coveiot.coveaccess.leaderboard.model.RankType;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.callbacks.IRankItemClick;
import com.coveiot.leaderboard.eventbus.CloveEventBusManager;
import com.coveiot.leaderboard.model.FilterEvent;
import com.coveiot.leaderboard.views.activities.RankingHistoryDetailsActivity;
import com.coveiot.leaderboard.views.adapters.DailyRankingAdapter;
import com.squareup.otto.Subscribe;
/* loaded from: classes9.dex */
public class MonthlyRankingFragment extends Fragment implements IRankItemClick {
    public RecyclerView h;
    public TextView i;
    public RecyclerView.LayoutManager j;
    public DailyRankingAdapter k;
    public OnFragmentInteractionListener l;

    /* loaded from: classes9.dex */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    /* loaded from: classes9.dex */
    public class a implements CoveApiListener<RankHistoryModel, CoveApiErrorModel> {
        public a() {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: a */
        public void onError(CoveApiErrorModel coveApiErrorModel) {
            MonthlyRankingFragment.this.h.setVisibility(8);
            MonthlyRankingFragment.this.i.setVisibility(0);
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: b */
        public void onSuccess(RankHistoryModel rankHistoryModel) {
            if (rankHistoryModel.getData().getRanks() != null && rankHistoryModel.getData().getRanks().size() > 0) {
                MonthlyRankingFragment.this.k.setData(rankHistoryModel.getData().getRanks());
                MonthlyRankingFragment monthlyRankingFragment = MonthlyRankingFragment.this;
                monthlyRankingFragment.h.setAdapter(monthlyRankingFragment.k);
                MonthlyRankingFragment.this.h.setVisibility(0);
                MonthlyRankingFragment.this.i.setVisibility(8);
                return;
            }
            MonthlyRankingFragment.this.h.setVisibility(8);
            MonthlyRankingFragment.this.i.setVisibility(0);
        }
    }

    public static MonthlyRankingFragment newInstance(String str, String str2) {
        MonthlyRankingFragment monthlyRankingFragment = new MonthlyRankingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("param1", str);
        bundle.putString("param2", str2);
        monthlyRankingFragment.setArguments(bundle);
        return monthlyRankingFragment;
    }

    public final void a(FilterType filterType) {
        CoveLeaderboardApi.getRankHistory(RankType.MONTH, filterType, new a());
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onButtonPressed(Uri uri) {
        OnFragmentInteractionListener onFragmentInteractionListener = this.l;
        if (onFragmentInteractionListener != null) {
            onFragmentInteractionListener.onFragmentInteraction(uri);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            getArguments().getString("param1");
            getArguments().getString("param2");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_monthly_ranking, viewGroup, false);
        this.h = (RecyclerView) inflate.findViewById(R.id.monthlyRecyclerview);
        this.i = (TextView) inflate.findViewById(R.id.no_data_tv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.j = linearLayoutManager;
        this.h.setLayoutManager(linearLayoutManager);
        this.k = new DailyRankingAdapter(getActivity(), this);
        a(FilterType.CITY);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.l = null;
    }

    @Override // com.coveiot.leaderboard.callbacks.IRankItemClick
    public void onRankItemClick(RankHistoryModel.DataBean.RanksBean ranksBean) {
        startActivity(new Intent(getActivity(), RankingHistoryDetailsActivity.class));
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        CloveEventBusManager.getInstance().getEventBus().register(this);
        super.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        CloveEventBusManager.getInstance().getEventBus().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void updateFilteredData(FilterEvent filterEvent) {
        a(filterEvent.getFilterType());
    }
}
