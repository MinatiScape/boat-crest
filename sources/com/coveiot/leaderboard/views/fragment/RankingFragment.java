package com.coveiot.leaderboard.views.fragment;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.leaderboard.CoveLeaderboardApi;
import com.coveiot.coveaccess.leaderboard.model.FilterType;
import com.coveiot.coveaccess.leaderboard.model.RankHistoryModel;
import com.coveiot.coveaccess.leaderboard.model.RankType;
import com.coveiot.coveaccess.leaderboard.model.TopRankModel;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.eventbus.CloveEventBusManager;
import com.coveiot.leaderboard.model.FilterEvent;
import com.coveiot.leaderboard.model.MyMarkerView;
import com.coveiot.leaderboard.model.WalkDataChartModel;
import com.coveiot.leaderboard.utils.LeaderboardUtils;
import com.coveiot.leaderboard.views.adapters.TopRankersAdapter;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes9.dex */
public class RankingFragment extends BaseFragment {
    public static final String TAG = RankingFragment.class.getSimpleName();
    public RecyclerView.LayoutManager m;
    public TopRankersAdapter n;
    public RecyclerView o;
    public TextView p;
    public LineChart q;
    public TextView r;
    public TextView s;
    public TextView t;
    public TextView u;
    public TextView v;
    public ImageView w;

    /* loaded from: classes9.dex */
    public class a implements CoveApiListener<TopRankModel, CoveApiErrorModel> {
        public a() {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: a */
        public void onError(CoveApiErrorModel coveApiErrorModel) {
            if (RankingFragment.this.getActivity() == null || RankingFragment.this.getView() == null) {
                return;
            }
            RankingFragment.this.dismissProgress();
            FragmentActivity activity = RankingFragment.this.getActivity();
            Toast.makeText(activity, "" + coveApiErrorModel.getMsg(), 0).show();
            RankingFragment.this.o.setVisibility(8);
            RankingFragment.this.p.setVisibility(0);
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: b */
        public void onSuccess(TopRankModel topRankModel) {
            if (topRankModel != null) {
                if (topRankModel.getData().getTopRanks() != null && topRankModel.getData().getTopRanks().size() > 0) {
                    RankingFragment.this.q.setVisibility(0);
                    RankingFragment.this.o.setVisibility(0);
                    RankingFragment.this.p.setVisibility(8);
                    RankingFragment.this.t.setVisibility(0);
                    RankingFragment.this.v.setVisibility(0);
                    RankingFragment.this.w.setVisibility(0);
                    RankingFragment rankingFragment = RankingFragment.this;
                    rankingFragment.n = new TopRankersAdapter(rankingFragment.getActivity());
                    RankingFragment.this.n.setData(topRankModel.getData().getTopRanks());
                    RankingFragment rankingFragment2 = RankingFragment.this;
                    rankingFragment2.o.setAdapter(rankingFragment2.n);
                } else {
                    RankingFragment.this.o.setVisibility(8);
                    RankingFragment.this.p.setVisibility(0);
                    RankingFragment.this.q.setVisibility(8);
                    RankingFragment.this.t.setVisibility(8);
                    RankingFragment.this.v.setVisibility(8);
                    RankingFragment.this.w.setVisibility(8);
                }
            }
            RankingFragment.this.dismissProgress();
        }
    }

    /* loaded from: classes9.dex */
    public class b implements CoveApiListener<RankHistoryModel, CoveApiErrorModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ FilterType f7263a;

        public b(FilterType filterType) {
            this.f7263a = filterType;
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: a */
        public void onError(CoveApiErrorModel coveApiErrorModel) {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: b */
        public void onSuccess(RankHistoryModel rankHistoryModel) {
            if (RankingFragment.this.isAdded() && rankHistoryModel != null) {
                if (rankHistoryModel.getData().getRanks() != null && rankHistoryModel.getData().getRanks().size() > 0) {
                    if (rankHistoryModel.getData().getRanks().get(0).getRank() >= 1000) {
                        TextView textView = RankingFragment.this.r;
                        textView.setText("" + String.format(Locale.ENGLISH, "%.1f", Double.valueOf(rankHistoryModel.getData().getRanks().get(0).getRank() / 1000.0d)) + RankingFragment.this.getResources().getString(R.string.k));
                    } else {
                        RankingFragment.this.r.setText(String.valueOf(rankHistoryModel.getData().getRanks().get(0).getRank()));
                    }
                    TextView textView2 = RankingFragment.this.s;
                    textView2.setText(String.valueOf(rankHistoryModel.getData().getRanks().get(0).getSteps() + RankingFragment.this.getString(R.string.steps_with_space)));
                    if (rankHistoryModel.getData().getRanks().get(0).getTotalUsers() >= 1000) {
                        rankHistoryModel.getData().getRanks().get(0).getRank();
                        TextView textView3 = RankingFragment.this.u;
                        textView3.setText(" / " + String.valueOf(rankHistoryModel.getData().getRanks().get(0).getTotalUsers() / 1000) + RankingFragment.this.getResources().getString(R.string.k));
                    } else {
                        TextView textView4 = RankingFragment.this.u;
                        textView4.setText(" / " + String.valueOf(rankHistoryModel.getData().getRanks().get(0).getTotalUsers()));
                    }
                    if (rankHistoryModel.getData().getRanks().get(0).getUserLocation() != null) {
                        c(this.f7263a, rankHistoryModel.getData().getRanks().get(0).getUserLocation());
                    }
                    RankingFragment.this.p(String.valueOf(rankHistoryModel.getData().getRanks().get(0).getId()));
                    RankingFragment rankingFragment = RankingFragment.this;
                    rankingFragment.q(rankingFragment.q, rankHistoryModel.getData().getRanks());
                    return;
                }
                Toast.makeText(RankingFragment.this.getActivity(), RankingFragment.this.getString(R.string.no_data_available), 0).show();
            }
        }

        public final void c(FilterType filterType, RankHistoryModel.DataBean.RanksBean.UserLocationBean userLocationBean) {
            int i = f.f7266a[filterType.ordinal()];
            if (i == 1) {
                RankingFragment.this.t.setText(userLocationBean.getLocality());
            } else if (i == 2) {
                RankingFragment.this.t.setText(userLocationBean.getCity());
            } else if (i == 3) {
                RankingFragment.this.t.setText(userLocationBean.getAdministrativeArea());
            } else if (i != 4) {
            } else {
                RankingFragment.this.t.setText(userLocationBean.getCountry());
            }
        }
    }

    /* loaded from: classes9.dex */
    public class c implements IAxisValueFormatter {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ List f7264a;

        public c(RankingFragment rankingFragment, List list) {
            this.f7264a = list;
        }

        @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
        public String getFormattedValue(float f, AxisBase axisBase) {
            return f < ((float) this.f7264a.size()) ? LeaderboardUtils.formattedRankGraphDate(((RankHistoryModel.DataBean.RanksBean) this.f7264a.get((int) f)).getRankDate()) : "";
        }
    }

    /* loaded from: classes9.dex */
    public class d implements OnChartValueSelectedListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ List f7265a;

        public d(List list) {
            this.f7265a = list;
        }

        @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
        public void onNothingSelected() {
        }

        @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
        public void onValueSelected(Entry entry, Highlight highlight) {
            int x = (int) highlight.getX();
            if (((RankHistoryModel.DataBean.RanksBean) this.f7265a.get(x)).getRank() >= 1000) {
                TextView textView = RankingFragment.this.r;
                textView.setText(String.valueOf(((RankHistoryModel.DataBean.RanksBean) this.f7265a.get(x)).getRank() / 1000) + RankingFragment.this.getResources().getString(R.string.k));
            } else {
                RankingFragment.this.r.setText(String.valueOf(((RankHistoryModel.DataBean.RanksBean) this.f7265a.get(x)).getRank()));
            }
            String str = RankingFragment.TAG;
            LogHelper.d(str, "steps " + String.valueOf(((RankHistoryModel.DataBean.RanksBean) this.f7265a.get(x)).getSteps()));
            TextView textView2 = RankingFragment.this.s;
            textView2.setText(String.valueOf(((RankHistoryModel.DataBean.RanksBean) this.f7265a.get(x)).getSteps()) + RankingFragment.this.getString(R.string.steps_with_space));
            if (((RankHistoryModel.DataBean.RanksBean) this.f7265a.get(x)).getTotalUsers() >= 1000) {
                TextView textView3 = RankingFragment.this.u;
                textView3.setText(MqttTopic.TOPIC_LEVEL_SEPARATOR + String.valueOf(((RankHistoryModel.DataBean.RanksBean) this.f7265a.get(x)).getTotalUsers() / 1000) + RankingFragment.this.getResources().getString(R.string.k));
            } else {
                TextView textView4 = RankingFragment.this.u;
                textView4.setText(MqttTopic.TOPIC_LEVEL_SEPARATOR + String.valueOf(((RankHistoryModel.DataBean.RanksBean) this.f7265a.get(x)).getTotalUsers()));
            }
            if (((RankHistoryModel.DataBean.RanksBean) this.f7265a.get(x)).getUserLocation() != null) {
                RankingFragment.this.t.setText(((RankHistoryModel.DataBean.RanksBean) this.f7265a.get(x)).getUserLocation().getLocality());
            }
            RankingFragment.this.p(String.valueOf(((RankHistoryModel.DataBean.RanksBean) this.f7265a.get(x)).getId()));
        }
    }

    /* loaded from: classes9.dex */
    public class e implements Runnable {
        public final /* synthetic */ LineChart h;
        public final /* synthetic */ List i;

        public e(RankingFragment rankingFragment, LineChart lineChart, List list) {
            this.h = lineChart;
            this.i = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.moveViewToX(this.i.size() - 1);
        }
    }

    /* loaded from: classes9.dex */
    public static /* synthetic */ class f {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7266a;

        static {
            int[] iArr = new int[FilterType.values().length];
            f7266a = iArr;
            try {
                iArr[FilterType.LOCALITY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7266a[FilterType.CITY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7266a[FilterType.STATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7266a[FilterType.COUNTRY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static RankingFragment newInstance(String str, String str2) {
        RankingFragment rankingFragment = new RankingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("param1", str);
        bundle.putString("param2", str2);
        rankingFragment.setArguments(bundle);
        return rankingFragment;
    }

    public final void m(RankType rankType, FilterType filterType) {
        if (AppUtils.isNetConnected(getActivity())) {
            showProgresswithMsg(getString(R.string.please_wait));
            CoveLeaderboardApi.getRankHistory(rankType, filterType, new b(filterType));
            return;
        }
        this.o.setVisibility(8);
        this.p.setVisibility(0);
        this.q.setVisibility(8);
        this.t.setVisibility(8);
        this.v.setVisibility(8);
        this.w.setVisibility(8);
        Toast.makeText(getActivity(), getResources().getString(R.string.please_check_network), 0).show();
    }

    public final int n(List<RankHistoryModel.DataBean.RanksBean> list) {
        int i = 0;
        for (RankHistoryModel.DataBean.RanksBean ranksBean : list) {
            if (ranksBean.getRank() > i) {
                i = ranksBean.getRank();
            }
        }
        return i;
    }

    public final int o(List<RankHistoryModel.DataBean.RanksBean> list) {
        int i = 0;
        for (RankHistoryModel.DataBean.RanksBean ranksBean : list) {
            if (i == 0 || ranksBean.getRank() < i) {
                i = ranksBean.getRank();
            }
        }
        return i;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            getArguments().getString("param1");
            getArguments().getString("param2");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_ranking_test, viewGroup, false);
        this.o = (RecyclerView) inflate.findViewById(R.id.toppersRecyclerview);
        this.p = (TextView) inflate.findViewById(R.id.no_data_tv);
        this.q = (LineChart) inflate.findViewById(R.id.rankChart);
        this.r = (TextView) inflate.findViewById(R.id.myRank);
        this.s = (TextView) inflate.findViewById(R.id.mySteps);
        TextView textView = (TextView) inflate.findViewById(R.id.myName);
        this.t = (TextView) inflate.findViewById(R.id.location);
        ConstraintLayout constraintLayout = (ConstraintLayout) inflate.findViewById(R.id.location_layout);
        this.u = (TextView) inflate.findViewById(R.id.totalUsers);
        this.v = (TextView) inflate.findViewById(R.id.rank_textview);
        this.w = (ImageView) inflate.findViewById(R.id.location_image);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.m = linearLayoutManager;
        this.o.setLayoutManager(linearLayoutManager);
        m(RankType.DAY, FilterType.CITY);
        return inflate;
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

    public final void p(String str) {
        if (AppUtils.isNetConnected(getActivity())) {
            showProgresswithMsg(getString(R.string.please_wait));
            CoveLeaderboardApi.getTopRank(str, new a());
            return;
        }
        Toast.makeText(getActivity(), getResources().getString(R.string.please_check_network), 0).show();
    }

    public final void q(LineChart lineChart, List<RankHistoryModel.DataBean.RanksBean> list) {
        Collections.reverse(list);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(new WalkDataChartModel(i, list.get(i).getRank()));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList2.add(new Entry(((WalkDataChartModel) arrayList.get(i2)).getXAxisDayData(), ((WalkDataChartModel) arrayList.get(i2)).getYAxisWalkData()));
        }
        if (Build.VERSION.SDK_INT < 21) {
            lineChart.setHardwareAccelerationEnabled(false);
        }
        LineDataSet lineDataSet = new LineDataSet(arrayList2, "");
        Resources resources = getResources();
        int i3 = R.color.colorPrimary;
        lineDataSet.setCircleColor(resources.getColor(i3));
        lineDataSet.setCircleRadius(4.5f);
        lineDataSet.setCircleHoleRadius(3.0f);
        lineDataSet.setColor(getResources().getColor(i3));
        lineDataSet.setLineWidth(1.0f);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setValueTextColor(getResources().getColor(i3));
        lineDataSet.setHighLightColor(getResources().getColor(i3));
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawHorizontalHighlightIndicator(true);
        lineDataSet.setDrawVerticalHighlightIndicator(true);
        lineDataSet.setDrawFilled(false);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.setNoDataTextColor(getResources().getColor(i3));
        lineData.setValueTextColor(getResources().getColor(i3));
        c cVar = new c(this, list);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setLabelCount(list.size());
        xAxis.setGranularity(1.0f);
        xAxis.setValueFormatter(cVar);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineWidth(1.0f);
        xAxis.setSpaceMax(0.3f);
        Resources resources2 = getResources();
        int i4 = R.color.color_757575;
        xAxis.setTextColor(resources2.getColor(i4));
        xAxis.setAxisLineColor(getResources().getColor(i4));
        YAxis axisRight = lineChart.getAxisRight();
        axisRight.setXOffset(10.0f);
        axisRight.setAxisLineWidth(1.0f);
        axisRight.setDrawAxisLine(false);
        axisRight.setAxisMinimum(0.0f);
        axisRight.setStartAtZero(true);
        axisRight.setTextColor(getResources().getColor(i4));
        axisRight.setAxisLineColor(getResources().getColor(i4));
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getXAxis().setAxisLineColor(getResources().getColor(i4));
        lineChart.getAxisLeft().setTextColor(getResources().getColor(i4));
        lineChart.getAxisLeft().setAxisMaximum(n(list));
        lineChart.getAxisLeft().setAxisMinimum(o(list));
        lineChart.getAxisLeft().setLabelCount(10);
        lineChart.getAxisLeft().setGranularity(1.0f);
        lineChart.getAxisLeft().setGranularityEnabled(true);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.setBorderColor(getResources().getColor(i4));
        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.setOnChartValueSelectedListener(new d(list));
        new MyMarkerView(getActivity(), R.layout.custom_marker_view).setChartView(lineChart);
        lineChart.setVisibleXRange(0.0f, 6.0f);
        lineChart.setPinchZoom(false);
        lineChart.setDoubleTapToZoomEnabled(true);
        lineChart.invalidate();
        lineChart.postDelayed(new e(this, lineChart, list), 500L);
    }

    @Subscribe
    public void updateFilteredData(FilterEvent filterEvent) {
        m(filterEvent.getRankType(), filterEvent.getFilterType());
    }
}
