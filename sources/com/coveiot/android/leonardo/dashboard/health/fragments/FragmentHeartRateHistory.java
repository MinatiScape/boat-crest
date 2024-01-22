package com.coveiot.android.leonardo.dashboard.health.fragments;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.activities.ActivityHeartRateHistory;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractFragmentHeartRateHistory;
import com.coveiot.android.leonardo.dashboard.health.model.HeartRateData;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentHeartRateHistoryViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.watchfaceui.constants.WatchfaceConstants;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.c;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentHeartRateHistory extends BaseFragment implements View.OnClickListener, ContractFragmentHeartRateHistory {
    public int m;
    public ActivityHeartRateHistory mActivity;
    public final int n;
    @Nullable
    public HeartRateData q;
    public FragmentHeartRateHistoryViewModel viewModel;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int o = 1;
    public final int p = 2;

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Nullable
    public final HeartRateData getHeartRateData() {
        return this.q;
    }

    @NotNull
    public final ActivityHeartRateHistory getMActivity() {
        ActivityHeartRateHistory activityHeartRateHistory = this.mActivity;
        if (activityHeartRateHistory != null) {
            return activityHeartRateHistory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mActivity");
        return null;
    }

    @NotNull
    public final FragmentHeartRateHistoryViewModel getViewModel() {
        FragmentHeartRateHistoryViewModel fragmentHeartRateHistoryViewModel = this.viewModel;
        if (fragmentHeartRateHistoryViewModel != null) {
            return fragmentHeartRateHistoryViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void k(ArrayList<CandleEntry> arrayList, final ArrayList<String> arrayList2) {
        int i = R.id.candlestickchart;
        ((CandleStickChart) _$_findCachedViewById(i)).setVisibility(0);
        ((CandleStickChart) _$_findCachedViewById(i)).getAxisLeft().setStartAtZero(true);
        ((CandleStickChart) _$_findCachedViewById(i)).setPinchZoom(false);
        CandleDataSet candleDataSet = new CandleDataSet(arrayList, WatchfaceConstants.HEART_RATE);
        candleDataSet.setColor(getResources().getColor(R.color.secondary_text_color));
        candleDataSet.setShadowColor(getResources().getColor(17170445));
        candleDataSet.setShadowWidth(0.0f);
        candleDataSet.setDrawIcons(false);
        candleDataSet.setDrawValues(true);
        candleDataSet.setIncreasingColor(getResources().getColor(R.color.color_E51C23));
        candleDataSet.setIncreasingPaintStyle(Paint.Style.FILL);
        candleDataSet.setNeutralColor(getResources().getColor(R.color.color_E51C23));
        candleDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        candleDataSet.setBarSpace(0.45f);
        candleDataSet.setHighlightLineWidth(0.0f);
        candleDataSet.setDrawHorizontalHighlightIndicator(false);
        candleDataSet.setHighlightEnabled(true);
        candleDataSet.setDrawVerticalHighlightIndicator(false);
        XAxis xAxis = ((CandleStickChart) _$_findCachedViewById(i)).getXAxis();
        xAxis.setEnabled(true);
        xAxis.setTextColor(getResources().getColor(R.color.secondary_text_color));
        xAxis.setDrawGridLines(false);
        YAxis axisLeft = ((CandleStickChart) _$_findCachedViewById(i)).getAxisLeft();
        axisLeft.setTextColor(getResources().getColor(R.color.secondary_text_color));
        axisLeft.setDrawGridLines(false);
        axisLeft.setAxisMinimum(0.0f);
        axisLeft.setEnabled(true);
        axisLeft.setAxisMaximum(200.0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(6);
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() { // from class: com.coveiot.android.leonardo.dashboard.health.fragments.FragmentHeartRateHistory$setGraphValues$1
            @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
            @NotNull
            public String getFormattedValue(float f, @Nullable AxisBase axisBase) {
                int roundToInt;
                if (f >= 0.0f && (roundToInt = c.roundToInt(f)) >= 0 && roundToInt < arrayList2.size() && roundToInt == ((int) f)) {
                    String str = arrayList2.get(roundToInt);
                    Intrinsics.checkNotNullExpressionValue(str, "labels[index]");
                    return str;
                }
                return "";
            }
        });
        CandleData candleData = new CandleData(candleDataSet);
        candleData.setValueTextColor(-1);
        candleData.setDrawValues(false);
        ((CandleStickChart) _$_findCachedViewById(i)).getLegend().setEnabled(false);
        ((CandleStickChart) _$_findCachedViewById(i)).setData(candleData);
        ((CandleStickChart) _$_findCachedViewById(i)).setDrawMarkers(true);
        ((CandleStickChart) _$_findCachedViewById(i)).setVisibleXRangeMinimum(5.0f);
        ((CandleStickChart) _$_findCachedViewById(i)).setVisibleXRangeMaximum(6.0f);
        ((CandleStickChart) _$_findCachedViewById(i)).getDescription().setEnabled(false);
        ((CandleStickChart) _$_findCachedViewById(i)).setDrawGridBackground(false);
        ((CandleStickChart) _$_findCachedViewById(i)).setDrawBorders(false);
        ((CandleStickChart) _$_findCachedViewById(i)).setHorizontalScrollBarEnabled(true);
        ((CandleStickChart) _$_findCachedViewById(i)).setPinchZoom(false);
        ((CandleStickChart) _$_findCachedViewById(i)).setAutoScaleMinMaxEnabled(false);
        ((CandleStickChart) _$_findCachedViewById(i)).setScaleEnabled(false);
        ((CandleStickChart) _$_findCachedViewById(i)).moveViewToX(arrayList.size());
        ((CandleStickChart) _$_findCachedViewById(i)).getAxisRight().setEnabled(false);
        ((CandleStickChart) _$_findCachedViewById(i)).invalidate();
        ((CandleStickChart) _$_findCachedViewById(i)).setNoDataText(getResources().getString(R.string.no_data_available));
        ((CandleStickChart) _$_findCachedViewById(i)).moveViewToX(arrayList.size());
        ((CandleStickChart) _$_findCachedViewById(i)).setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.coveiot.android.leonardo.dashboard.health.fragments.FragmentHeartRateHistory$setGraphValues$2
            @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
            public void onNothingSelected() {
            }

            @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
            public void onValueSelected(@NotNull Entry e, @NotNull Highlight h) {
                Intrinsics.checkNotNullParameter(e, "e");
                Intrinsics.checkNotNullParameter(h, "h");
                FragmentHeartRateHistory fragmentHeartRateHistory = FragmentHeartRateHistory.this;
                Object data = e.getData();
                Intrinsics.checkNotNull(data, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.health.model.HeartRateData");
                fragmentHeartRateHistory.setHeartRateData((HeartRateData) data);
                FragmentHeartRateHistory fragmentHeartRateHistory2 = FragmentHeartRateHistory.this;
                HeartRateData heartRateData = fragmentHeartRateHistory2.getHeartRateData();
                Intrinsics.checkNotNull(heartRateData);
                fragmentHeartRateHistory2.l(heartRateData);
            }
        });
    }

    public final void l(HeartRateData heartRateData) {
        TextView textView = (TextView) _$_findCachedViewById(R.id.date_tv);
        FragmentActivity activity = getActivity();
        Context baseContext = activity != null ? activity.getBaseContext() : null;
        String type = heartRateData.getType();
        Intrinsics.checkNotNullExpressionValue(type, "heartRateData.type");
        String dwmValue = heartRateData.getDwmValue();
        Intrinsics.checkNotNullExpressionValue(dwmValue, "heartRateData.dwmValue");
        textView.setText(PayUtils.getTodayYesterdayDate(baseContext, type, dwmValue, heartRateData.getYear()));
        if (heartRateData.getMaxHr() > 0 && heartRateData.getMinHr() > 0) {
            ((TextView) _$_findCachedViewById(R.id.minimum_value)).setText(heartRateData.getMinHr() + ' ' + getString(R.string.bpm));
            ((TextView) _$_findCachedViewById(R.id.maximum_value)).setText(heartRateData.getMaxHr() + ' ' + getString(R.string.bpm));
            return;
        }
        StringBuilder sb = new StringBuilder();
        AppConstants appConstants = AppConstants.HR_EMPTY;
        sb.append(appConstants.getValue());
        sb.append(' ');
        sb.append(getString(R.string.bpm));
        ((TextView) _$_findCachedViewById(R.id.minimum_value)).setText(sb.toString());
        ((TextView) _$_findCachedViewById(R.id.maximum_value)).setText(appConstants.getValue() + ' ' + getString(R.string.bpm));
    }

    public final void m(TextView textView) {
        if (isAdded()) {
            int i = R.id.daily_tv;
            ((TextView) _$_findCachedViewById(i)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            int i2 = R.id.weekly_tv;
            ((TextView) _$_findCachedViewById(i2)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            int i3 = R.id.monthly_tv;
            ((TextView) _$_findCachedViewById(i3)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            ((TextView) _$_findCachedViewById(i2)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            ((TextView) _$_findCachedViewById(i3)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            ((TextView) _$_findCachedViewById(i)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            textView.setTextColor(getResources().getColor(R.color.color_E51C23));
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_rectangle_hr);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof ActivityHeartRateHistory) {
            setMActivity((ActivityHeartRateHistory) context);
        }
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractFragmentHeartRateHistory
    public void onCandleChartDataLoaded(@Nullable ArrayList<CandleEntry> arrayList, @Nullable ArrayList<String> arrayList2) {
        if (!AppUtils.isEmpty(arrayList)) {
            Intrinsics.checkNotNull(arrayList);
            if (arrayList.get(arrayList.size() - 1).getData() instanceof HeartRateData) {
                Object data = arrayList.get(arrayList.size() - 1).getData();
                Intrinsics.checkNotNull(data, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.health.model.HeartRateData");
                this.q = (HeartRateData) data;
                Object data2 = arrayList.get(arrayList.size() - 1).getData();
                Intrinsics.checkNotNull(data2, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.health.model.HeartRateData");
                l((HeartRateData) data2);
            }
            Intrinsics.checkNotNull(arrayList2);
            k(arrayList, arrayList2);
            ((CandleStickChart) _$_findCachedViewById(R.id.candlestickchart)).setVisibility(0);
            ((TextView) _$_findCachedViewById(R.id.no_data_tv)).setVisibility(8);
            return;
        }
        ((CandleStickChart) _$_findCachedViewById(R.id.candlestickchart)).setVisibility(8);
        ((TextView) _$_findCachedViewById(R.id.no_data_tv)).setVisibility(0);
        StringBuilder sb = new StringBuilder();
        AppConstants appConstants = AppConstants.HR_EMPTY;
        sb.append(appConstants.getValue());
        sb.append(' ');
        sb.append(getString(R.string.bpm));
        ((TextView) _$_findCachedViewById(R.id.minimum_value)).setText(sb.toString());
        ((TextView) _$_findCachedViewById(R.id.maximum_value)).setText(appConstants.getValue() + ' ' + getString(R.string.bpm));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        switch (view.getId()) {
            case R.id.back /* 2131362054 */:
                AnalyticsLog analyticsLog = new AnalyticsLog();
                analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog.setScreenName(FirebaseEventParams.ScreenName.HEART_RATE_HISTORY_SCREEN.getValue());
                analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_HEART_RATE_CURRENT_DAY_DASHBOARD_SCREEN.getValue());
                analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                if (this.mActivity == null || getMActivity().isFinishing()) {
                    return;
                }
                getMActivity().finish();
                return;
            case R.id.daily_tv /* 2131363118 */:
                AnalyticsLog analyticsLog2 = new AnalyticsLog();
                analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.HEART_RATE_HISTORY_SCREEN.getValue());
                analyticsLog2.setDescription(FirebaseEventParams.Description.OPEN_DAY_GRAPH.getValue());
                analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.DAY_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
                this.m = this.n;
                TextView daily_tv = (TextView) _$_findCachedViewById(R.id.daily_tv);
                Intrinsics.checkNotNullExpressionValue(daily_tv, "daily_tv");
                m(daily_tv);
                getViewModel().selectDayView();
                return;
            case R.id.monthly_tv /* 2131364641 */:
                AnalyticsLog analyticsLog3 = new AnalyticsLog();
                analyticsLog3.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog3.setScreenName(FirebaseEventParams.ScreenName.HEART_RATE_HISTORY_SCREEN.getValue());
                analyticsLog3.setDescription(FirebaseEventParams.Description.OPEN_MONTH_GRAPH.getValue());
                analyticsLog3.setUiElementName(FirebaseEventParams.UiElementName.MONTH_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog3);
                this.m = this.p;
                TextView monthly_tv = (TextView) _$_findCachedViewById(R.id.monthly_tv);
                Intrinsics.checkNotNullExpressionValue(monthly_tv, "monthly_tv");
                m(monthly_tv);
                getViewModel().selectMonthView();
                return;
            case R.id.share_iv /* 2131365453 */:
                AnalyticsLog analyticsLog4 = new AnalyticsLog();
                analyticsLog4.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog4.setScreenName(FirebaseEventParams.ScreenName.HEART_RATE_HISTORY_SCREEN.getValue());
                analyticsLog4.setDescription(FirebaseEventParams.Description.OPEN_SHARE.getValue());
                analyticsLog4.setUiElementName(FirebaseEventParams.UiElementName.HEART_RATE_HISTORY_SHARE_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog4);
                share();
                return;
            case R.id.weekly_tv /* 2131367464 */:
                AnalyticsLog analyticsLog5 = new AnalyticsLog();
                analyticsLog5.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog5.setScreenName(FirebaseEventParams.ScreenName.HEART_RATE_HISTORY_SCREEN.getValue());
                analyticsLog5.setDescription(FirebaseEventParams.Description.OPEN_WEEK_GRAPH.getValue());
                analyticsLog5.setUiElementName(FirebaseEventParams.UiElementName.WEEK_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog5);
                this.m = this.o;
                TextView weekly_tv = (TextView) _$_findCachedViewById(R.id.weekly_tv);
                Intrinsics.checkNotNullExpressionValue(weekly_tv, "weekly_tv");
                m(weekly_tv);
                getViewModel().selectWeekView();
                return;
            default:
                return;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_heart_rate_history, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        setViewModel((FragmentHeartRateHistoryViewModel) ViewModelProviders.of(this, new ViewModelFactory(context)).get(FragmentHeartRateHistoryViewModel.class));
        getViewModel().setContractFragmentHeartRateHistory$app_prodRelease(this);
        getViewModel().setMLifecycleOwner(this);
        this.m = this.n;
        StringBuilder sb = new StringBuilder();
        AppConstants appConstants = AppConstants.HR_EMPTY;
        sb.append(appConstants.getValue());
        sb.append(' ');
        sb.append(getString(R.string.bpm));
        ((TextView) _$_findCachedViewById(R.id.minimum_value)).setText(sb.toString());
        ((TextView) _$_findCachedViewById(R.id.maximum_value)).setText(appConstants.getValue() + ' ' + getString(R.string.bpm));
        int i = R.id.daily_tv;
        TextView daily_tv = (TextView) _$_findCachedViewById(i);
        Intrinsics.checkNotNullExpressionValue(daily_tv, "daily_tv");
        m(daily_tv);
        ((TextView) _$_findCachedViewById(i)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.weekly_tv)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.monthly_tv)).setOnClickListener(this);
        ((ImageButton) _$_findCachedViewById(R.id.back)).setOnClickListener(this);
        ((ImageView) _$_findCachedViewById(R.id.share_iv)).setOnClickListener(this);
        getViewModel().selectDayView();
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.HEART_RATE_HISTORY_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }

    public final void setHeartRateData(@Nullable HeartRateData heartRateData) {
        this.q = heartRateData;
    }

    public final void setMActivity(@NotNull ActivityHeartRateHistory activityHeartRateHistory) {
        Intrinsics.checkNotNullParameter(activityHeartRateHistory, "<set-?>");
        this.mActivity = activityHeartRateHistory;
    }

    public final void setViewModel(@NotNull FragmentHeartRateHistoryViewModel fragmentHeartRateHistoryViewModel) {
        Intrinsics.checkNotNullParameter(fragmentHeartRateHistoryViewModel, "<set-?>");
        this.viewModel = fragmentHeartRateHistoryViewModel;
    }

    public final void share() {
        ShareData shareData = new ShareData();
        shareData.setMin_bpm(((TextView) _$_findCachedViewById(R.id.minimum_value)).getText().toString());
        shareData.setMax_bpm(((TextView) _$_findCachedViewById(R.id.maximum_value)).getText().toString());
        HeartRateData heartRateData = this.q;
        if (heartRateData != null) {
            Intrinsics.checkNotNull(heartRateData);
            shareData.setHeartRate(String.valueOf((int) heartRateData.getAvgHr()));
        }
        int i = this.m;
        if (i == this.n) {
            shareData.setGraphType(getResources().getString(R.string.share_daily));
        } else if (i == this.o) {
            shareData.setGraphType(getResources().getString(R.string.share_weekly));
        } else if (i == this.p) {
            shareData.setGraphType(getResources().getString(R.string.share_monthly));
        }
        shareData.setDwmValue(((TextView) _$_findCachedViewById(R.id.date_tv)).getText().toString());
        AppNavigator.Companion companion = AppNavigator.Companion;
        ActivityHeartRateHistory mActivity = getMActivity();
        String string = getResources().getString(R.string.heart_rate);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.heart_rate)");
        companion.navigateToShareScreen(mActivity, shareData, string);
    }
}
