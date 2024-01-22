package com.coveiot.android.leonardo.dashboard.health.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.activities.ActivityAmbientSoundHistory;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractFragmentAmbientSoundHistory;
import com.coveiot.android.leonardo.dashboard.health.model.AmbientSoundLevelData;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentAmbientSoundHistoryViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentAmbientSoundLevelHistory extends BaseFragment implements View.OnClickListener, ContractFragmentAmbientSoundHistory {
    public int m;
    public ActivityAmbientSoundHistory mActivity;
    public final int n;
    @Nullable
    public AmbientSoundLevelData q;
    public FragmentAmbientSoundHistoryViewModel viewModel;
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
    public final AmbientSoundLevelData getAmbientSoundLevelData() {
        return this.q;
    }

    @NotNull
    public final ActivityAmbientSoundHistory getMActivity() {
        ActivityAmbientSoundHistory activityAmbientSoundHistory = this.mActivity;
        if (activityAmbientSoundHistory != null) {
            return activityAmbientSoundHistory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mActivity");
        return null;
    }

    @NotNull
    public final FragmentAmbientSoundHistoryViewModel getViewModel() {
        FragmentAmbientSoundHistoryViewModel fragmentAmbientSoundHistoryViewModel = this.viewModel;
        if (fragmentAmbientSoundHistoryViewModel != null) {
            return fragmentAmbientSoundHistoryViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void k(ArrayList<Entry> arrayList, ArrayList<String> arrayList2) {
        ArrayList arrayList3 = new ArrayList();
        Intrinsics.checkNotNull(arrayList);
        Iterator<Entry> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Entry next = it.next();
            if (next.getY() == 0.0f) {
                arrayList3.add(next);
            }
        }
        arrayList.removeAll(arrayList3);
        LineDataSet lineDataSet = new LineDataSet(arrayList, "");
        lineDataSet.setCircleColor(getResources().getColor(R.color.ambient_sound_graph_color));
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        lineDataSet.setColor(getResources().getColor(R.color.ambient_sound_graph_color));
        lineDataSet.setHighLightColor(getResources().getColor(17170445));
        if (lineDataSet.getEntryCount() > 0) {
            int entryCount = lineDataSet.getEntryCount();
            int[] iArr = new int[entryCount];
            for (int i = 0; i < entryCount; i++) {
                iArr[i] = getResources().getColor(R.color.ambient_sound_graph_color);
            }
            iArr[entryCount - 1] = getResources().getColor(R.color.ambient_sound_graph_color);
            lineDataSet.setColors(Arrays.copyOf(iArr, entryCount));
        }
        LineData lineData = new LineData(lineDataSet);
        int i2 = R.id.lineChartAmbientHist;
        ((LineChart) _$_findCachedViewById(i2)).getXAxis().setValueFormatter(new IndexAxisValueFormatter(arrayList2));
        lineData.setDrawValues(false);
        ((LineChart) _$_findCachedViewById(i2)).setData(lineData);
        ((LineChart) _$_findCachedViewById(i2)).setDrawGridBackground(false);
        ((LineChart) _$_findCachedViewById(i2)).getDescription().setEnabled(false);
        lineData.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        ((LineChart) _$_findCachedViewById(i2)).getPaint(7).setColor(getResources().getColor(R.color.secondary_text_color));
        ((LineChart) _$_findCachedViewById(i2)).setDrawBorders(false);
        ((LineChart) _$_findCachedViewById(i2)).setAutoScaleMinMaxEnabled(false);
        YAxis axisLeft = ((LineChart) _$_findCachedViewById(i2)).getAxisLeft();
        axisLeft.setEnabled(true);
        axisLeft.setDrawAxisLine(true);
        axisLeft.setDrawGridLines(false);
        axisLeft.mAxisMaximum = 100.0f;
        axisLeft.setAxisLineColor(getResources().getColor(R.color.secondary_text_color));
        ((LineChart) _$_findCachedViewById(i2)).getAxisRight().setEnabled(false);
        XAxis xAxis = ((LineChart) _$_findCachedViewById(i2)).getXAxis();
        xAxis.setEnabled(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineColor(getResources().getColor(R.color.secondary_text_color));
        ((LineChart) _$_findCachedViewById(i2)).getAxisLeft().setStartAtZero(true);
        ((LineChart) _$_findCachedViewById(i2)).getAxisRight().setStartAtZero(true);
        ((LineChart) _$_findCachedViewById(i2)).getAxisLeft().setTextColor(getResources().getColor(R.color.secondary_text_color));
        ((LineChart) _$_findCachedViewById(i2)).getXAxis().setTextColor(getResources().getColor(R.color.secondary_text_color));
        ((LineChart) _$_findCachedViewById(i2)).getLegend().setTextColor(getResources().getColor(R.color.secondary_text_color));
        ((LineChart) _$_findCachedViewById(i2)).getLegend().setEnabled(false);
        ((LineChart) _$_findCachedViewById(i2)).animateY(2000);
        ((LineChart) _$_findCachedViewById(i2)).setVisibleXRangeMinimum(5.0f);
        ((LineChart) _$_findCachedViewById(i2)).setVisibleXRangeMaximum(6.0f);
        ((LineChart) _$_findCachedViewById(i2)).setPinchZoom(false);
        ((LineChart) _$_findCachedViewById(i2)).setAutoScaleMinMaxEnabled(false);
        ((LineChart) _$_findCachedViewById(i2)).setScaleEnabled(false);
        ((LineChart) _$_findCachedViewById(i2)).setDoubleTapToZoomEnabled(false);
        ((LineChart) _$_findCachedViewById(i2)).moveViewToX(arrayList.size() - 1);
        ((LineChart) _$_findCachedViewById(i2)).invalidate();
        ((LineChart) _$_findCachedViewById(i2)).setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.coveiot.android.leonardo.dashboard.health.fragments.FragmentAmbientSoundLevelHistory$setLineGraphValues$1
            @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
            public void onNothingSelected() {
            }

            @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
            public void onValueSelected(@NotNull Entry e, @NotNull Highlight h) {
                Intrinsics.checkNotNullParameter(e, "e");
                Intrinsics.checkNotNullParameter(h, "h");
                FragmentAmbientSoundLevelHistory fragmentAmbientSoundLevelHistory = FragmentAmbientSoundLevelHistory.this;
                Object data = e.getData();
                Intrinsics.checkNotNull(data, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.health.model.AmbientSoundLevelData");
                fragmentAmbientSoundLevelHistory.setAmbientSoundLevelData((AmbientSoundLevelData) data);
                FragmentAmbientSoundLevelHistory fragmentAmbientSoundLevelHistory2 = FragmentAmbientSoundLevelHistory.this;
                AmbientSoundLevelData ambientSoundLevelData = fragmentAmbientSoundLevelHistory2.getAmbientSoundLevelData();
                Intrinsics.checkNotNull(ambientSoundLevelData);
                fragmentAmbientSoundLevelHistory2.l(ambientSoundLevelData);
            }
        });
    }

    public final void l(AmbientSoundLevelData ambientSoundLevelData) {
        TextView textView = (TextView) _$_findCachedViewById(R.id.date_tv);
        FragmentActivity activity = getActivity();
        Context baseContext = activity != null ? activity.getBaseContext() : null;
        String type = ambientSoundLevelData.getType();
        Intrinsics.checkNotNullExpressionValue(type, "ambientSoundData.type");
        String dwmValue = ambientSoundLevelData.getDwmValue();
        Intrinsics.checkNotNullExpressionValue(dwmValue, "ambientSoundData.dwmValue");
        textView.setText(PayUtils.getTodayYesterdayDate(baseContext, type, dwmValue, ambientSoundLevelData.getYear()));
        if (ambientSoundLevelData.getAvgAmbientSound() > 0) {
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.ambient_avg_value);
            if (textView2 != null) {
                textView2.setText(ambientSoundLevelData.getAvgAmbientSound() + ' ' + getString(R.string.decible_unit));
            }
            int avgAmbientSound = ambientSoundLevelData.getAvgAmbientSound();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String ambientSoundInfo = PayUtils.getAmbientSoundInfo(avgAmbientSound, requireContext);
            int i = R.id.tv_ambient_sound_status;
            TextView textView3 = (TextView) _$_findCachedViewById(i);
            if (textView3 != null) {
                textView3.setText(ambientSoundInfo);
            }
            if (ambientSoundInfo.equals(getString(R.string.normal))) {
                TextView textView4 = (TextView) _$_findCachedViewById(i);
                if (textView4 != null) {
                    textView4.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_normal, 0, 0, 0);
                    return;
                }
                return;
            }
            TextView textView5 = (TextView) _$_findCachedViewById(i);
            if (textView5 != null) {
                textView5.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_high, 0, 0, 0);
                return;
            }
            return;
        }
        TextView textView6 = (TextView) _$_findCachedViewById(R.id.ambient_avg_value);
        if (textView6 != null) {
            textView6.setText(AppConstants.AMBIENT_SOUND_EMPTY.getValue() + ' ' + getString(R.string.decible_unit));
        }
        TextView textView7 = (TextView) _$_findCachedViewById(R.id.tv_ambient_sound_status);
        if (textView7 == null) {
            return;
        }
        textView7.setText(String.valueOf(AppConstants.AMBIENT_SOUND_EMPTY.getValue()));
    }

    public final void m(TextView textView) {
        if (isAdded()) {
            int i = R.id.daily_tv;
            ((TextView) _$_findCachedViewById(i)).setTextColor(getResources().getColor(R.color.color_8e8e93));
            int i2 = R.id.weekly_tv;
            ((TextView) _$_findCachedViewById(i2)).setTextColor(getResources().getColor(R.color.color_8e8e93));
            int i3 = R.id.monthly_tv;
            ((TextView) _$_findCachedViewById(i3)).setTextColor(getResources().getColor(R.color.color_8e8e93));
            ((TextView) _$_findCachedViewById(i2)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            ((TextView) _$_findCachedViewById(i3)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            ((TextView) _$_findCachedViewById(i)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            textView.setTextColor(getResources().getColor(R.color.color_e02020));
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_rectangle_steps_temp);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof ActivityAmbientSoundHistory) {
            setMActivity((ActivityAmbientSoundHistory) context);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        switch (view.getId()) {
            case R.id.back /* 2131362054 */:
                if (this.mActivity == null || getMActivity().isFinishing()) {
                    return;
                }
                getMActivity().finish();
                return;
            case R.id.daily_tv /* 2131363118 */:
                this.m = this.n;
                TextView daily_tv = (TextView) _$_findCachedViewById(R.id.daily_tv);
                Intrinsics.checkNotNullExpressionValue(daily_tv, "daily_tv");
                m(daily_tv);
                getViewModel().selectDayView();
                return;
            case R.id.monthly_tv /* 2131364641 */:
                this.m = this.p;
                TextView monthly_tv = (TextView) _$_findCachedViewById(R.id.monthly_tv);
                Intrinsics.checkNotNullExpressionValue(monthly_tv, "monthly_tv");
                m(monthly_tv);
                getViewModel().selectMonthView();
                return;
            case R.id.share_iv /* 2131365453 */:
                share();
                return;
            case R.id.weekly_tv /* 2131367464 */:
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
        return inflater.inflate(R.layout.fragment_ambient_sound_history, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractFragmentAmbientSoundHistory
    public void onLineChartDataLoaded(@Nullable ArrayList<Entry> arrayList, @Nullable ArrayList<String> arrayList2) {
        if (!AppUtils.isEmpty(arrayList)) {
            Intrinsics.checkNotNull(arrayList);
            if (arrayList.get(arrayList.size() - 1).getData() instanceof AmbientSoundLevelData) {
                Object data = arrayList.get(arrayList.size() - 1).getData();
                Intrinsics.checkNotNull(data, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.health.model.AmbientSoundLevelData");
                this.q = (AmbientSoundLevelData) data;
                Object data2 = arrayList.get(arrayList.size() - 1).getData();
                Intrinsics.checkNotNull(data2, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.health.model.AmbientSoundLevelData");
                l((AmbientSoundLevelData) data2);
            }
            Intrinsics.checkNotNull(arrayList2);
            k(arrayList, arrayList2);
            ((LineChart) _$_findCachedViewById(R.id.lineChartAmbientHist)).setVisibility(0);
            ((TextView) _$_findCachedViewById(R.id.no_data_tv)).setVisibility(8);
            return;
        }
        ((LineChart) _$_findCachedViewById(R.id.lineChartAmbientHist)).setVisibility(8);
        ((TextView) _$_findCachedViewById(R.id.no_data_tv)).setVisibility(0);
        TextView textView = (TextView) _$_findCachedViewById(R.id.ambient_avg_value);
        if (textView == null) {
            return;
        }
        textView.setText(AppConstants.AMBIENT_SOUND_EMPTY.getValue() + ' ' + getString(R.string.decible_unit));
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ((TextView) _$_findCachedViewById(R.id.daily_tv)).performClick();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        setViewModel((FragmentAmbientSoundHistoryViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FragmentAmbientSoundHistoryViewModel.class));
        getViewModel().setContractFragmentAmbientSoundHistory$app_prodRelease(this);
        getViewModel().setMLifecycleOwner(this);
        this.m = this.n;
        TextView textView = (TextView) _$_findCachedViewById(R.id.ambient_avg_value);
        if (textView != null) {
            textView.setText(AppConstants.AMBIENT_SOUND_EMPTY.getValue() + ' ' + getString(R.string.decible_unit));
        }
        int i = R.id.daily_tv;
        TextView daily_tv = (TextView) _$_findCachedViewById(i);
        Intrinsics.checkNotNullExpressionValue(daily_tv, "daily_tv");
        m(daily_tv);
        ((TextView) _$_findCachedViewById(i)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.weekly_tv)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.monthly_tv)).setOnClickListener(this);
        ((ImageButton) _$_findCachedViewById(R.id.back)).setOnClickListener(this);
        ((ImageView) _$_findCachedViewById(R.id.share_iv)).setOnClickListener(this);
    }

    public final void setAmbientSoundLevelData(@Nullable AmbientSoundLevelData ambientSoundLevelData) {
        this.q = ambientSoundLevelData;
    }

    public final void setMActivity(@NotNull ActivityAmbientSoundHistory activityAmbientSoundHistory) {
        Intrinsics.checkNotNullParameter(activityAmbientSoundHistory, "<set-?>");
        this.mActivity = activityAmbientSoundHistory;
    }

    public final void setViewModel(@NotNull FragmentAmbientSoundHistoryViewModel fragmentAmbientSoundHistoryViewModel) {
        Intrinsics.checkNotNullParameter(fragmentAmbientSoundHistoryViewModel, "<set-?>");
        this.viewModel = fragmentAmbientSoundHistoryViewModel;
    }

    public final void share() {
        ShareData shareData = new ShareData();
        TextView textView = (TextView) _$_findCachedViewById(R.id.ambient_avg_value);
        shareData.setAvg_ambient_sound(String.valueOf(textView != null ? textView.getText() : null));
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
        ActivityAmbientSoundHistory mActivity = getMActivity();
        String string = getResources().getString(R.string.ambient_sound_level_txt);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st….ambient_sound_level_txt)");
        companion.navigateToShareScreen(mActivity, shareData, string);
    }
}
