package com.coveiot.leaderboard.views.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.leaderboard.CoveLeaderboardApi;
import com.coveiot.coveaccess.leaderboard.model.RankHistoryModel;
import com.coveiot.coveaccess.leaderboard.model.TopRankModel;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.model.MyMarkerView;
import com.coveiot.leaderboard.model.WalkDataChartModel;
import com.coveiot.leaderboard.views.adapters.TopRankersAdapter;
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
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class RankingHistoryDetailsActivity extends BaseActivity {
    public RecyclerView p;
    public TextView q;
    public TextView r;
    public LineChart s;
    public RecyclerView.LayoutManager t;
    public RankHistoryModel.DataBean.RanksBean u;
    public TopRankersAdapter v;

    /* loaded from: classes9.dex */
    public class a implements CoveApiListener<TopRankModel, CoveApiErrorModel> {
        public a() {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: a */
        public void onError(CoveApiErrorModel coveApiErrorModel) {
            RankingHistoryDetailsActivity rankingHistoryDetailsActivity = RankingHistoryDetailsActivity.this;
            Toast.makeText(rankingHistoryDetailsActivity, "" + coveApiErrorModel.getMsg(), 0).show();
            RankingHistoryDetailsActivity.this.p.setVisibility(8);
            RankingHistoryDetailsActivity.this.q.setVisibility(0);
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: b */
        public void onSuccess(TopRankModel topRankModel) {
            if (topRankModel != null && topRankModel.getData().getTopRanks() != null && topRankModel.getData().getTopRanks().size() > 0) {
                RankingHistoryDetailsActivity.this.p.setVisibility(0);
                RankingHistoryDetailsActivity.this.q.setVisibility(8);
                RankingHistoryDetailsActivity rankingHistoryDetailsActivity = RankingHistoryDetailsActivity.this;
                rankingHistoryDetailsActivity.v = new TopRankersAdapter(rankingHistoryDetailsActivity);
                RankingHistoryDetailsActivity.this.v.setData(topRankModel.getData().getTopRanks());
                RankingHistoryDetailsActivity rankingHistoryDetailsActivity2 = RankingHistoryDetailsActivity.this;
                rankingHistoryDetailsActivity2.p.setAdapter(rankingHistoryDetailsActivity2.v);
                return;
            }
            RankingHistoryDetailsActivity.this.p.setVisibility(8);
            RankingHistoryDetailsActivity.this.q.setVisibility(0);
        }
    }

    /* loaded from: classes9.dex */
    public class b implements IAxisValueFormatter {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String[] f7225a;

        public b(RankingHistoryDetailsActivity rankingHistoryDetailsActivity, String[] strArr) {
            this.f7225a = strArr;
        }

        @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
        public String getFormattedValue(float f, AxisBase axisBase) {
            return this.f7225a[(int) f];
        }
    }

    /* loaded from: classes9.dex */
    public class c implements OnChartValueSelectedListener {
        public c(RankingHistoryDetailsActivity rankingHistoryDetailsActivity) {
        }

        @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
        public void onNothingSelected() {
        }

        @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
        public void onValueSelected(Entry entry, Highlight highlight) {
            highlight.getX();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ranking_history_details);
        r();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            RankHistoryModel.DataBean.RanksBean ranksBean = (RankHistoryModel.DataBean.RanksBean) extras.getSerializable("data");
            this.u = ranksBean;
            if (ranksBean != null) {
                this.r.setText(ranksBean.getRankDate());
                q("" + this.u.getId());
            }
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.t = linearLayoutManager;
        this.p.setLayoutManager(linearLayoutManager);
        s(this.s);
    }

    public final void q(String str) {
        CoveLeaderboardApi.getTopRank(str, new a());
    }

    public final void r() {
        this.p = (RecyclerView) findViewById(R.id.toppersRecyclerview);
        this.q = (TextView) findViewById(R.id.no_data_tv);
        this.r = (TextView) findViewById(R.id.date);
        this.s = (LineChart) findViewById(R.id.rankChart);
    }

    public final void s(LineChart lineChart) {
        WalkDataChartModel walkDataChartModel = new WalkDataChartModel(0, 1800);
        WalkDataChartModel walkDataChartModel2 = new WalkDataChartModel(1, 500);
        WalkDataChartModel walkDataChartModel3 = new WalkDataChartModel(2, 0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(walkDataChartModel);
        arrayList.add(walkDataChartModel2);
        arrayList.add(walkDataChartModel3);
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList2.add(new Entry(((WalkDataChartModel) arrayList.get(i)).getXAxisDayData(), ((WalkDataChartModel) arrayList.get(i)).getYAxisWalkData()));
        }
        LineDataSet lineDataSet = new LineDataSet(arrayList2, "");
        int i2 = R.color.gray_line_color;
        lineDataSet.setCircleColor(ContextCompat.getColor(this, i2));
        lineDataSet.setCircleRadius(4.5f);
        lineDataSet.setCircleHoleRadius(3.0f);
        lineDataSet.setColor(ContextCompat.getColor(this, i2));
        lineDataSet.setLineWidth(2.0f);
        lineDataSet.setDrawFilled(false);
        lineDataSet.setHighLightColor(ContextCompat.getColor(this, i2));
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(true);
        lineChart.setData(new LineData(lineDataSet));
        b bVar = new b(this, new String[]{BleConst.GetActivityModeData, BleConst.EnterActivityMode, BleConst.DeviceSendDataToAPP, "1", "2", "3", BleConst.GetDeviceInfo, BleConst.SetDeviceInfo, BleConst.CMD_Set_Mac, BleConst.GetStepGoal, BleConst.SetStepGoal, BleConst.GetDeviceBatteryLevel, BleConst.GetDeviceMacAddress, BleConst.GetDeviceVersion});
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1.0f);
        xAxis.setValueFormatter(bVar);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineWidth(1.0f);
        xAxis.setDrawLabels(false);
        xAxis.setAxisMinimum(0.0f);
        xAxis.setSpaceMax(0.3f);
        YAxis axisLeft = lineChart.getAxisLeft();
        axisLeft.setXOffset(10.0f);
        axisLeft.setAxisLineWidth(2.0f);
        axisLeft.setDrawAxisLine(false);
        axisLeft.setAxisMinimum(0.0f);
        axisLeft.setStartAtZero(true);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.setBorderColor(ContextCompat.getColor(this, i2));
        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.setOnChartValueSelectedListener(new c(this));
        new MyMarkerView(this, R.layout.custom_marker_view);
        lineChart.setVisibleXRange(0.0f, 3.0f);
        lineChart.setPinchZoom(false);
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.invalidate();
    }
}
