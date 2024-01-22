package com.coveiot.android.sleepenergyscore.energymeter.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.sleepenergyscore.R;
import com.coveiot.android.sleepenergyscore.energymeter.activities.ActivityEnergyMeterHistory;
import com.coveiot.android.sleepenergyscore.energymeter.activities.ActivityShare;
import com.coveiot.android.sleepenergyscore.energymeter.listener.ContractEnergyMeterHistory;
import com.coveiot.android.sleepenergyscore.energymeter.model.EnergyMeterDataModel;
import com.coveiot.android.sleepenergyscore.energymeter.model.ShareEnergyMeterData;
import com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterHistoryViewModel;
import com.coveiot.android.sleepenergyscore.sleepscore.eventdata.EnergyMeterHistoryData;
import com.coveiot.android.sleepenergyscore.utils.Constants;
import com.coveiot.android.sleepenergyscore.utils.Utils;
import com.coveiot.android.sleepenergyscore.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.CoveEventBusManager;
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
import com.squareup.otto.Subscribe;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class FragmentEnergyMeterHistory extends BaseFragment implements View.OnClickListener, ContractEnergyMeterHistory {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityEnergyMeterHistory mActivity;
    public FragmentEnergyMeterHistoryViewModel viewModel;

    public static final void l(FragmentEnergyMeterHistory this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        List<String> listOfDates = this$0.getViewModel().getListOfDates(list, this$0.getViewModel().getListOfDatesFromWalkData());
        List mutableList = listOfDates != null ? CollectionsKt___CollectionsKt.toMutableList((Collection) listOfDates) : null;
        FragmentEnergyMeterHistoryViewModel viewModel = this$0.getViewModel();
        Intrinsics.checkNotNull(mutableList, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.String> }");
        viewModel.callSleepScoreApi((ArrayList) mutableList);
    }

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

    @Subscribe
    public final void callEnergyMeterApiCall(@NotNull EnergyMeterHistoryData energyMeterHistoryData) {
        Intrinsics.checkNotNullParameter(energyMeterHistoryData, "energyMeterHistoryData");
        if (Intrinsics.areEqual(energyMeterHistoryData.getType(), getResources().getString(R.string.sleep_score))) {
            FragmentEnergyMeterHistoryViewModel viewModel = getViewModel();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            viewModel.callEnergyMeterApi(requireContext);
            return;
        }
        getViewModel().loadGraph();
    }

    @NotNull
    public final ActivityEnergyMeterHistory getMActivity() {
        ActivityEnergyMeterHistory activityEnergyMeterHistory = this.mActivity;
        if (activityEnergyMeterHistory != null) {
            return activityEnergyMeterHistory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mActivity");
        return null;
    }

    @NotNull
    public final FragmentEnergyMeterHistoryViewModel getViewModel() {
        FragmentEnergyMeterHistoryViewModel fragmentEnergyMeterHistoryViewModel = this.viewModel;
        if (fragmentEnergyMeterHistoryViewModel != null) {
            return fragmentEnergyMeterHistoryViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void initObserver() {
        MutableLiveData<List<String>> sleepDataDatesListLiveData = getViewModel().getSleepDataDatesListLiveData();
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type androidx.lifecycle.LifecycleOwner");
        sleepDataDatesListLiveData.observe((LifecycleOwner) context, new Observer() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.i
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentEnergyMeterHistory.l(FragmentEnergyMeterHistory.this, (List) obj);
            }
        });
    }

    public final void m(ArrayList<CandleEntry> arrayList, final ArrayList<String> arrayList2) {
        YAxis axisLeft;
        if (isAdded()) {
            int i = R.id.candlestickchart;
            CandleStickChart candleStickChart = (CandleStickChart) _$_findCachedViewById(i);
            if (candleStickChart != null) {
                candleStickChart.setVisibility(0);
            }
            CandleStickChart candleStickChart2 = (CandleStickChart) _$_findCachedViewById(i);
            if (candleStickChart2 != null) {
                candleStickChart2.clear();
            }
            CandleStickChart candleStickChart3 = (CandleStickChart) _$_findCachedViewById(i);
            if (candleStickChart3 != null && (axisLeft = candleStickChart3.getAxisLeft()) != null) {
                axisLeft.setStartAtZero(true);
            }
            CandleStickChart candleStickChart4 = (CandleStickChart) _$_findCachedViewById(i);
            if (candleStickChart4 != null) {
                candleStickChart4.setPinchZoom(false);
            }
            CandleDataSet candleDataSet = new CandleDataSet(arrayList, "");
            Resources resources = getResources();
            int i2 = R.color.secondary_text_color;
            candleDataSet.setColor(resources.getColor(i2));
            candleDataSet.setShadowColor(getResources().getColor(17170445));
            candleDataSet.setShadowWidth(0.0f);
            candleDataSet.setDrawIcons(false);
            candleDataSet.setDrawValues(true);
            Resources resources2 = getResources();
            int i3 = R.color.color_E51C23;
            candleDataSet.setIncreasingColor(resources2.getColor(i3));
            candleDataSet.setIncreasingPaintStyle(Paint.Style.FILL);
            candleDataSet.setNeutralColor(getResources().getColor(i3));
            candleDataSet.setValueTextColor(getResources().getColor(i2));
            candleDataSet.setBarSpace(0.45f);
            candleDataSet.setHighlightLineWidth(0.0f);
            candleDataSet.setDrawHorizontalHighlightIndicator(false);
            candleDataSet.setHighlightEnabled(true);
            candleDataSet.setDrawVerticalHighlightIndicator(false);
            CandleStickChart candleStickChart5 = (CandleStickChart) _$_findCachedViewById(i);
            XAxis xAxis = candleStickChart5 != null ? candleStickChart5.getXAxis() : null;
            Intrinsics.checkNotNull(xAxis);
            xAxis.setEnabled(true);
            xAxis.setTextColor(getResources().getColor(i2));
            xAxis.setDrawGridLines(false);
            Resources resources3 = getResources();
            int i4 = R.color.color_757575;
            xAxis.setGridColor(resources3.getColor(i4));
            xAxis.setAxisLineColor(getResources().getColor(i4));
            CandleStickChart candleStickChart6 = (CandleStickChart) _$_findCachedViewById(i);
            YAxis axisLeft2 = candleStickChart6 != null ? candleStickChart6.getAxisLeft() : null;
            Intrinsics.checkNotNull(axisLeft2);
            axisLeft2.setTextColor(getResources().getColor(i2));
            axisLeft2.setDrawGridLines(false);
            axisLeft2.setAxisMinimum(0.0f);
            axisLeft2.setEnabled(true);
            axisLeft2.setAxisLineColor(getResources().getColor(i4));
            axisLeft2.setAxisMaximum(100.0f);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setLabelCount(6);
            xAxis.setGranularityEnabled(true);
            xAxis.setValueFormatter(new IAxisValueFormatter() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.FragmentEnergyMeterHistory$setGraphValues$1
                @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
                @NotNull
                public String getFormattedValue(float f, @Nullable AxisBase axisBase) {
                    int roundToInt;
                    if (f >= 0.0f && (roundToInt = kotlin.math.c.roundToInt(f)) >= 0 && roundToInt < arrayList2.size() && roundToInt == ((int) f)) {
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
            ((CandleStickChart) _$_findCachedViewById(i)).setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.coveiot.android.sleepenergyscore.energymeter.fragments.FragmentEnergyMeterHistory$setGraphValues$2
                @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
                public void onNothingSelected() {
                }

                @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
                public void onValueSelected(@NotNull Entry e, @NotNull Highlight h) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    Intrinsics.checkNotNullParameter(h, "h");
                    Object data = e.getData();
                    Intrinsics.checkNotNull(data, "null cannot be cast to non-null type com.coveiot.android.sleepenergyscore.energymeter.model.EnergyMeterDataModel");
                    FragmentEnergyMeterHistory.this.n((EnergyMeterDataModel) data);
                }
            });
        }
    }

    public final void n(EnergyMeterDataModel energyMeterDataModel) {
        TextView textView = (TextView) _$_findCachedViewById(R.id.date_tv);
        if (textView != null) {
            FragmentActivity activity = getActivity();
            Context baseContext = activity != null ? activity.getBaseContext() : null;
            String value = Constants.DAY.getValue();
            String dwmValue = energyMeterDataModel.getDwmValue();
            Intrinsics.checkNotNull(dwmValue);
            textView.setText(Utils.getTodayYesterdayDate(baseContext, value, dwmValue, 0));
        }
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.end_score_val);
        if (textView2 != null) {
            textView2.setText(String.valueOf(energyMeterDataModel.getEnergyScoreMin()));
        }
        TextView textView3 = (TextView) _$_findCachedViewById(R.id.start_score_val);
        if (textView3 == null) {
            return;
        }
        textView3.setText(String.valueOf(energyMeterDataModel.getEnergyScoreMax()));
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof ActivityEnergyMeterHistory) {
            setMActivity((ActivityEnergyMeterHistory) context);
        }
    }

    @Override // com.coveiot.android.sleepenergyscore.energymeter.listener.ContractEnergyMeterHistory
    public void onCandleChartDataLoaded(@Nullable ArrayList<CandleEntry> arrayList, @Nullable ArrayList<String> arrayList2) {
        if (!AppUtils.isEmpty(arrayList)) {
            Intrinsics.checkNotNull(arrayList);
            if (arrayList.get(arrayList.size() - 1).getData() instanceof EnergyMeterDataModel) {
                Object data = arrayList.get(arrayList.size() - 1).getData();
                Intrinsics.checkNotNull(data, "null cannot be cast to non-null type com.coveiot.android.sleepenergyscore.energymeter.model.EnergyMeterDataModel");
                n((EnergyMeterDataModel) data);
            }
            Intrinsics.checkNotNull(arrayList2);
            m(arrayList, arrayList2);
            CandleStickChart candleStickChart = (CandleStickChart) _$_findCachedViewById(R.id.candlestickchart);
            if (candleStickChart != null) {
                candleStickChart.setVisibility(0);
            }
            TextView textView = (TextView) _$_findCachedViewById(R.id.no_data_tv);
            if (textView != null) {
                textView.setVisibility(8);
            }
            ImageView imageView = (ImageView) _$_findCachedViewById(R.id.share_iv);
            if (imageView == null) {
                return;
            }
            imageView.setVisibility(0);
            return;
        }
        CandleStickChart candleStickChart2 = (CandleStickChart) _$_findCachedViewById(R.id.candlestickchart);
        if (candleStickChart2 != null) {
            candleStickChart2.setVisibility(8);
        }
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.no_data_tv);
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
        ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.share_iv);
        if (imageView2 == null) {
            return;
        }
        imageView2.setVisibility(8);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        int id = view.getId();
        if (id == R.id.back) {
            if (this.mActivity == null || getMActivity().isFinishing()) {
                return;
            }
            getMActivity().finish();
        } else if (id == R.id.share_iv) {
            share();
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_energy_meter_history, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        CoveEventBusManager.getInstance().getEventBus().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FragmentEnergyMeterHistoryViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…del::class.java\n        )");
        setViewModel((FragmentEnergyMeterHistoryViewModel) viewModel);
        getViewModel().setContractEnergyMeterHistory$sleepenergyscore_prodRelease(this);
        getViewModel().setMLifecycleOwner(this);
        ((TextView) _$_findCachedViewById(R.id.daily_tv)).setOnClickListener(this);
        ((ImageButton) _$_findCachedViewById(R.id.back)).setOnClickListener(this);
        ((ImageView) _$_findCachedViewById(R.id.share_iv)).setOnClickListener(this);
        if (UserDataManager.getInstance(getContext()).getLastUpdateEnergyMeterHistoryTimeStamp(BleApiManager.getInstance(requireContext()).getBleApi().getMacAddress()) == 0) {
            getViewModel().getListOfDatesFromSleepData();
            initObserver();
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        List<Date> listOfNotSyncDates = getViewModel().getListOfNotSyncDates();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int size = listOfNotSyncDates.size();
        for (int i = 0; i < size; i++) {
            String format = simpleDateFormat.format(listOfNotSyncDates.get(i));
            Intrinsics.checkNotNullExpressionValue(format, "formatter.format(listOfNotSyncedDates[i])");
            arrayList.add(format);
        }
        getViewModel().callSleepScoreApi(arrayList);
    }

    public final void setMActivity(@NotNull ActivityEnergyMeterHistory activityEnergyMeterHistory) {
        Intrinsics.checkNotNullParameter(activityEnergyMeterHistory, "<set-?>");
        this.mActivity = activityEnergyMeterHistory;
    }

    public final void setViewModel(@NotNull FragmentEnergyMeterHistoryViewModel fragmentEnergyMeterHistoryViewModel) {
        Intrinsics.checkNotNullParameter(fragmentEnergyMeterHistoryViewModel, "<set-?>");
        this.viewModel = fragmentEnergyMeterHistoryViewModel;
    }

    public final void share() {
        ShareEnergyMeterData shareEnergyMeterData = new ShareEnergyMeterData();
        shareEnergyMeterData.setStartEnergyScore(Integer.parseInt(((TextView) _$_findCachedViewById(R.id.start_score_val)).getText().toString()));
        shareEnergyMeterData.setEndEnergyScore(Integer.parseInt(((TextView) _$_findCachedViewById(R.id.end_score_val)).getText().toString()));
        shareEnergyMeterData.setGraphType(getResources().getString(R.string.share_daily));
        shareEnergyMeterData.setDwmValue(((TextView) _$_findCachedViewById(R.id.date_tv)).getText().toString());
        Intent intent = new Intent(getContext(), ActivityShare.class);
        intent.putExtra(Constants.SHARE_DATA.getValue(), shareEnergyMeterData);
        Context context = getContext();
        if (context != null) {
            context.startActivity(intent);
        }
    }
}
