package com.coveiot.android.leonardo.dashboard.vitals.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentVitalHeartrateBinding;
import com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractHeartRateDashboard;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentHeartRateViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarRangeDialog;
import com.coveiot.android.theme.compundview.CustomMarkerViewVitals;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.android.watchfaceui.constants.WatchfaceConstants;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import com.coveiot.utils.utility.AppUtils;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class VitalHeartRateFragment extends BaseFragment implements ContractHeartRateDashboard, CalendarRangeDialog.OnCalendarRangeSelector {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public DatePickerDialog datePickerDialog;
    @Nullable
    public FragmentVitalHeartrateBinding m;
    public FragmentHeartRateViewModel n;
    @NotNull
    public Calendar p;
    public int q;
    public final int r;
    public final int s;
    public SimpleDateFormat simpleDateFormat;
    public final int t;
    public Pair<? extends Calendar, ? extends Calendar> u;
    public CalendarRangeDialog v;
    public boolean w;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final SimpleDateFormat o = AppUtils.getSimpleDateFormat("yyyy-MM-dd");

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final VitalHeartRateFragment newInstance() {
            return new VitalHeartRateFragment();
        }
    }

    public VitalHeartRateFragment() {
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this.p = calendar;
        this.s = 1;
        this.t = 2;
    }

    public static final void A(VitalHeartRateFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.q = this$0.r;
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this$0.u(calendar);
        this$0.I();
    }

    public static final void B(VitalHeartRateFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.q = this$0.s;
        Calendar startDate = Calendar.getInstance();
        startDate.add(5, -6);
        Calendar endDate = Calendar.getInstance();
        this$0.I();
        this$0.u = new Pair<>(startDate, endDate);
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        Intrinsics.checkNotNullExpressionValue(endDate, "endDate");
        this$0.t(startDate, endDate);
    }

    public static final void C(VitalHeartRateFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.q = this$0.t;
        Calendar startDate = Calendar.getInstance();
        startDate.add(5, -30);
        Calendar endDate = Calendar.getInstance();
        this$0.I();
        this$0.u = new Pair<>(startDate, endDate);
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        Intrinsics.checkNotNullExpressionValue(endDate, "endDate");
        this$0.t(startDate, endDate);
    }

    public static final void E(VitalHeartRateFragment this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i, i2, i3);
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        this$0.u(newDate);
    }

    public static final void w(VitalHeartRateFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.q;
        if (i == this$0.r) {
            this$0.D();
            this$0.getDatePickerDialog().getDatePicker().setMaxDate(System.currentTimeMillis());
            this$0.getDatePickerDialog().show();
        } else if (i == this$0.s) {
            this$0.w = true;
            this$0.H();
        } else {
            this$0.w = false;
            this$0.H();
        }
    }

    public static final void x(VitalHeartRateFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.q;
        if (i == this$0.r) {
            this$0.u(PayUtils.INSTANCE.getPreviousDayCalendar(this$0.p));
            return;
        }
        Pair<? extends Calendar, ? extends Calendar> pair = null;
        if (i == this$0.s) {
            if (this$0.u == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            }
            Pair<? extends Calendar, ? extends Calendar> pair2 = this$0.u;
            if (pair2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair2 = null;
            }
            Object clone = pair2.getFirst().clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar = (Calendar) clone;
            calendar.add(5, -6);
            Pair<? extends Calendar, ? extends Calendar> pair3 = this$0.u;
            if (pair3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair3;
            }
            Object clone2 = pair.getFirst().clone();
            Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar2 = (Calendar) clone2;
            this$0.u = new Pair<>(calendar, calendar2);
            this$0.t(calendar, calendar2);
            this$0.I();
        } else if (i == this$0.t) {
            Pair<? extends Calendar, ? extends Calendar> pair4 = this$0.u;
            if (pair4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair4 = null;
            }
            Object clone3 = pair4.getFirst().clone();
            Intrinsics.checkNotNull(clone3, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar3 = (Calendar) clone3;
            calendar3.add(5, -30);
            Pair<? extends Calendar, ? extends Calendar> pair5 = this$0.u;
            if (pair5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair5;
            }
            Object clone4 = pair.getFirst().clone();
            Intrinsics.checkNotNull(clone4, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar4 = (Calendar) clone4;
            this$0.u = new Pair<>(calendar3, calendar4);
            this$0.t(calendar3, calendar4);
            this$0.I();
        }
    }

    public static final void y(VitalHeartRateFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.q;
        if (i == this$0.r) {
            if (DateUtils.isToday(this$0.p.getTimeInMillis())) {
                return;
            }
            this$0.u(PayUtils.INSTANCE.getNextDayCalendar(this$0.p));
            return;
        }
        Pair<? extends Calendar, ? extends Calendar> pair = null;
        if (i == this$0.s) {
            Pair<? extends Calendar, ? extends Calendar> pair2 = this$0.u;
            if (pair2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair2 = null;
            }
            if (DateUtils.isToday(pair2.getSecond().getTimeInMillis())) {
                return;
            }
            Pair<? extends Calendar, ? extends Calendar> pair3 = this$0.u;
            if (pair3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair3 = null;
            }
            Object clone = pair3.getSecond().clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar = (Calendar) clone;
            Pair<? extends Calendar, ? extends Calendar> pair4 = this$0.u;
            if (pair4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair4;
            }
            Object clone2 = pair.getSecond().clone();
            Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar2 = (Calendar) clone2;
            calendar2.add(5, 6);
            if (calendar2.getTimeInMillis() <= Calendar.getInstance().getTimeInMillis()) {
                this$0.u = new Pair<>(calendar, calendar2);
                this$0.t(calendar, calendar2);
                this$0.I();
                return;
            }
            int findDateDifference = AppUtils.findDateDifference(Calendar.getInstance().getTime(), calendar2.getTime());
            Object clone3 = calendar.clone();
            Intrinsics.checkNotNull(clone3, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar3 = (Calendar) clone3;
            calendar3.add(5, 6 - findDateDifference);
            this$0.u = new Pair<>(calendar, calendar3);
            this$0.t(calendar, calendar3);
            this$0.I();
        } else if (i == this$0.t) {
            Pair<? extends Calendar, ? extends Calendar> pair5 = this$0.u;
            if (pair5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair5 = null;
            }
            if (DateUtils.isToday(pair5.getSecond().getTimeInMillis())) {
                return;
            }
            Pair<? extends Calendar, ? extends Calendar> pair6 = this$0.u;
            if (pair6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair6 = null;
            }
            Object clone4 = pair6.getSecond().clone();
            Intrinsics.checkNotNull(clone4, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar4 = (Calendar) clone4;
            Pair<? extends Calendar, ? extends Calendar> pair7 = this$0.u;
            if (pair7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair7;
            }
            Object clone5 = pair.getSecond().clone();
            Intrinsics.checkNotNull(clone5, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar5 = (Calendar) clone5;
            calendar5.add(5, 30);
            if (calendar5.getTimeInMillis() <= Calendar.getInstance().getTimeInMillis()) {
                this$0.u = new Pair<>(calendar4, calendar5);
                this$0.t(calendar4, calendar5);
                this$0.I();
                return;
            }
            int findDateDifference2 = AppUtils.findDateDifference(Calendar.getInstance().getTime(), calendar5.getTime());
            Object clone6 = calendar4.clone();
            Intrinsics.checkNotNull(clone6, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar6 = (Calendar) clone6;
            calendar6.add(5, 30 - findDateDifference2);
            this$0.u = new Pair<>(calendar4, calendar6);
            this$0.t(calendar4, calendar6);
            this$0.I();
        }
    }

    public static final void z(VitalHeartRateFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.share();
    }

    public final void D() {
        Calendar calendar = this.p;
        setDatePickerDialog(new DatePickerDialog(requireActivity(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.k
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                VitalHeartRateFragment.E(VitalHeartRateFragment.this, datePicker, i, i2, i3);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)));
    }

    public final void F() {
        TextView textView = s().vitalsMainData.tvVitalMinValue;
        StringBuilder sb = new StringBuilder();
        AppConstants appConstants = AppConstants.TWO_DASH_STRING;
        sb.append(appConstants.getValue());
        sb.append(' ');
        sb.append(getString(R.string.bpm));
        textView.setText(sb.toString());
        TextView textView2 = s().vitalsMainData.tvVitalMaxValue;
        textView2.setText(appConstants.getValue() + ' ' + getString(R.string.bpm));
        SpannableString spannableString = new SpannableString(s().vitalsMainData.tvVitalMaxValue.getText().toString());
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(0.5f);
        String obj = s().vitalsMainData.tvVitalMaxValue.getText().toString();
        String string = getString(R.string.bpm);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bpm)");
        spannableString.setSpan(relativeSizeSpan, StringsKt__StringsKt.indexOf$default((CharSequence) obj, string, 0, false, 6, (Object) null), s().vitalsMainData.tvVitalMaxValue.getText().toString().length(), 0);
        s().vitalsMainData.tvVitalMaxValue.setText(spannableString);
        SpannableString spannableString2 = new SpannableString(s().vitalsMainData.tvVitalMinValue.getText().toString());
        RelativeSizeSpan relativeSizeSpan2 = new RelativeSizeSpan(0.5f);
        String obj2 = s().vitalsMainData.tvVitalMinValue.getText().toString();
        String string2 = getString(R.string.bpm);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.bpm)");
        spannableString2.setSpan(relativeSizeSpan2, StringsKt__StringsKt.indexOf$default((CharSequence) obj2, string2, 0, false, 6, (Object) null), s().vitalsMainData.tvVitalMinValue.getText().toString().length(), 0);
        s().vitalsMainData.tvVitalMinValue.setText(spannableString2);
    }

    public final void G(ArrayList<CandleEntry> arrayList, final ArrayList<String> arrayList2) {
        s().hrGraph.clear();
        CandleDataSet candleDataSet = new CandleDataSet(arrayList, WatchfaceConstants.HEART_RATE);
        candleDataSet.setColor(getResources().getColor(R.color.secondary_text_color));
        candleDataSet.setShadowColor(getResources().getColor(17170445));
        candleDataSet.setShadowWidth(0.0f);
        candleDataSet.setDrawIcons(false);
        candleDataSet.setDrawValues(true);
        candleDataSet.setIncreasingColor(getResources().getColor(R.color.steps_graph_color));
        candleDataSet.setIncreasingPaintStyle(Paint.Style.FILL);
        candleDataSet.setNeutralColor(getResources().getColor(R.color.steps_graph_color));
        candleDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        candleDataSet.setBarSpace(0.1f);
        candleDataSet.setHighlightLineWidth(0.0f);
        candleDataSet.setDrawHorizontalHighlightIndicator(false);
        candleDataSet.setHighlightEnabled(true);
        candleDataSet.setDrawVerticalHighlightIndicator(false);
        XAxis xAxis = s().hrGraph.getXAxis();
        xAxis.setTextColor(getResources().getColor(R.color.secondary_text_color));
        xAxis.setDrawGridLines(false);
        YAxis axisLeft = s().hrGraph.getAxisLeft();
        axisLeft.setTextColor(getResources().getColor(R.color.secondary_text_color));
        axisLeft.setDrawGridLines(false);
        axisLeft.setAxisMinimum(40.0f);
        axisLeft.setEnabled(true);
        axisLeft.setGranularity(30.0f);
        axisLeft.setAxisMaximum(180.0f);
        axisLeft.setLabelCount(6);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalHeartRateFragment$setGraphValues$1
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
        s().hrGraph.getLegend().setEnabled(false);
        s().hrGraph.setData(candleData);
        Context context = getContext();
        String string = getString(R.string.heart_rate);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.heart_rate)");
        CustomMarkerViewVitals customMarkerViewVitals = new CustomMarkerViewVitals(context, R.layout.custom_marker_view_steps_hr, string, this.q, arrayList2);
        customMarkerViewVitals.setChartView(s().hrGraph);
        s().hrGraph.setMarker(customMarkerViewVitals);
        s().hrGraph.getDescription().setEnabled(false);
        s().hrGraph.setDrawGridBackground(false);
        s().hrGraph.setDrawBorders(false);
        s().hrGraph.setVisibleXRangeMaximum(24.0f);
        s().hrGraph.setVisibleXRangeMinimum(7.0f);
        s().hrGraph.setPinchZoom(false);
        s().hrGraph.setAutoScaleMinMaxEnabled(false);
        s().hrGraph.setScaleEnabled(false);
        s().hrGraph.getAxisRight().setEnabled(false);
        s().hrGraph.invalidate();
        s().hrGraph.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalHeartRateFragment$setGraphValues$2
            @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
            public void onNothingSelected() {
            }

            @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
            public void onValueSelected(@NotNull Entry e, @NotNull Highlight h) {
                Intrinsics.checkNotNullParameter(e, "e");
                Intrinsics.checkNotNullParameter(h, "h");
            }
        });
    }

    public final void H() {
        if (isAdded()) {
            boolean z = this.w;
            Pair<? extends Calendar, ? extends Calendar> pair = this.u;
            if (pair == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair = null;
            }
            CalendarRangeDialog calendarRangeDialog = new CalendarRangeDialog(z, pair, this);
            this.v = calendarRangeDialog;
            calendarRangeDialog.show(getChildFragmentManager(), "");
        }
    }

    public final void I() {
        if (isAdded()) {
            int i = this.q;
            if (i == this.r) {
                s().vitalsMainData.tvDay.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                s().vitalsMainData.tvDay.setTextColor(requireContext().getColor(R.color.main_text_color));
                s().vitalsMainData.tvWeek.setBackground(null);
                s().vitalsMainData.tvWeek.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                s().vitalsMainData.tvMonth.setBackground(null);
                s().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                s().vitalsMainData.tvDay.setTextAppearance(R.style.bold);
                s().vitalsMainData.tvWeek.setTextAppearance(R.style.regular);
                s().vitalsMainData.tvMonth.setTextAppearance(R.style.regular);
            } else if (i == this.s) {
                s().vitalsMainData.tvDay.setBackground(null);
                s().vitalsMainData.tvDay.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                s().vitalsMainData.tvWeek.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                s().vitalsMainData.tvWeek.setTextColor(requireContext().getColor(R.color.main_text_color));
                s().vitalsMainData.tvMonth.setBackground(null);
                s().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                s().vitalsMainData.tvDay.setTextAppearance(R.style.regular);
                s().vitalsMainData.tvWeek.setTextAppearance(R.style.bold);
                s().vitalsMainData.tvMonth.setTextAppearance(R.style.regular);
            } else {
                s().vitalsMainData.tvDay.setBackground(null);
                s().vitalsMainData.tvDay.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                s().vitalsMainData.tvWeek.setBackground(null);
                s().vitalsMainData.tvWeek.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                s().vitalsMainData.tvMonth.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                s().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(R.color.main_text_color));
                s().vitalsMainData.tvDay.setTextAppearance(R.style.regular);
                s().vitalsMainData.tvWeek.setTextAppearance(R.style.regular);
                s().vitalsMainData.tvMonth.setTextAppearance(R.style.bold);
            }
        }
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

    @NotNull
    public final DatePickerDialog getDatePickerDialog() {
        DatePickerDialog datePickerDialog = this.datePickerDialog;
        if (datePickerDialog != null) {
            return datePickerDialog;
        }
        Intrinsics.throwUninitializedPropertyAccessException("datePickerDialog");
        return null;
    }

    @NotNull
    public final SimpleDateFormat getSimpleDateFormat() {
        SimpleDateFormat simpleDateFormat = this.simpleDateFormat;
        if (simpleDateFormat != null) {
            return simpleDateFormat;
        }
        Intrinsics.throwUninitializedPropertyAccessException("simpleDateFormat");
        return null;
    }

    public final void initData() {
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
        Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"dd MMMM yyyy\")");
        setSimpleDateFormat(simpleDateFormat);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FragmentHeartRateViewModel fragmentHeartRateViewModel = (FragmentHeartRateViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FragmentHeartRateViewModel.class);
        this.n = fragmentHeartRateViewModel;
        if (fragmentHeartRateViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentHeartRateViewModel = null;
        }
        fragmentHeartRateViewModel.setContractHeartRateDashboard$app_prodRelease(this);
        FragmentHeartRateViewModel fragmentHeartRateViewModel2 = this.n;
        if (fragmentHeartRateViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentHeartRateViewModel2 = null;
        }
        fragmentHeartRateViewModel2.setMLifecycleOwner(this);
        FragmentVitalHeartrateBinding s = s();
        s.vitalsMainData.tvVitalInfo.setVisibility(8);
        s.vitalsMainData.clCenterDataStepsSleep.setVisibility(0);
        s.vitalsMainData.tvVitalMax.setText(getString(R.string.max));
        s.vitalsMainData.tvVitalMin.setText(getString(R.string.min));
        s.vitalsMainData.tvVitalName.setText(getString(R.string.heart_rate));
        s.vitalsMainData.tvVitalName.setCompoundDrawables(null, null, null, null);
        s.vitalsMainData.ivMin.setImageResource(R.drawable.hr_minimum);
        s.vitalsMainData.ivMax.setImageResource(R.drawable.hr_maximum);
        s.vitalsMainData.ivCenterVital.setImageResource(R.drawable.ic_heart);
        s.vitalsMainData.tvOutOfStepsSleepValue.setVisibility(8);
        s.vitalsMainData.tvAvgStepsSleepValue.setVisibility(8);
        TextView textView = s.vitalsMainData.tvLastSyncTime;
        PayUtils payUtils = PayUtils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        textView.setText(payUtils.getLastSyncTime(requireContext2));
        String string = getString(R.string.medical_disclaimer_info);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.medical_disclaimer_info)");
        SpannableString spannableString = new SpannableString(string);
        Context context = getContext();
        Integer valueOf = context != null ? Integer.valueOf(context.getColor(R.color.color_b3b3b3)) : null;
        Intrinsics.checkNotNull(valueOf);
        spannableString.setSpan(new ForegroundColorSpan(valueOf.intValue()), 0, 12, 18);
        s.tvdisclaimer.setText(spannableString);
        v();
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        u(calendar);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractHeartRateDashboard
    public boolean isSyncInProgress() {
        FragmentActivity requireActivity = requireActivity();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        return ((DataSyncViewModel) ViewModelProviders.of(requireActivity, new com.coveiot.android.dashboard2.ViewModelFactory(requireActivity2)).get(DataSyncViewModel.class)).checkIsSyncing();
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarRangeDialog.OnCalendarRangeSelector
    public void onCalendarRangeSelected(@NotNull Calendar fromDate, @NotNull Calendar toDate) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        this.u = new Pair<>(fromDate, toDate);
        t(fromDate, toDate);
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentVitalHeartrateBinding.inflate(inflater, viewGroup, false);
        View root = s().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
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
        initData();
    }

    public final FragmentVitalHeartrateBinding s() {
        FragmentVitalHeartrateBinding fragmentVitalHeartrateBinding = this.m;
        Intrinsics.checkNotNull(fragmentVitalHeartrateBinding);
        return fragmentVitalHeartrateBinding;
    }

    public final void setDatePickerDialog(@NotNull DatePickerDialog datePickerDialog) {
        Intrinsics.checkNotNullParameter(datePickerDialog, "<set-?>");
        this.datePickerDialog = datePickerDialog;
    }

    public final void setGraphVisible(boolean z) {
        if (z) {
            s().hrGraph.setVisibility(0);
            s().tvNoDataFound.setVisibility(8);
            return;
        }
        s().hrGraph.setVisibility(8);
        s().tvNoDataFound.setVisibility(0);
        s().hrGraph.clear();
    }

    public final void setSimpleDateFormat(@NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
        this.simpleDateFormat = simpleDateFormat;
    }

    public final void share() {
        ShareData shareData = new ShareData();
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM, yyyy");
        shareData.setMin_bpm(s().vitalsMainData.tvVitalMinValue.getText().toString());
        shareData.setMax_bpm(s().vitalsMainData.tvVitalMaxValue.getText().toString());
        int i = this.q;
        if (i == this.r) {
            shareData.setGraphType(getString(R.string.share_daily));
            shareData.setDwmValue(simpleDateFormat.format(this.p.getTime()));
        } else {
            Pair<? extends Calendar, ? extends Calendar> pair = null;
            if (i == this.s) {
                shareData.setGraphType(getString(R.string.share_weekly));
                StringBuilder sb = new StringBuilder();
                Pair<? extends Calendar, ? extends Calendar> pair2 = this.u;
                if (pair2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                    pair2 = null;
                }
                sb.append(simpleDateFormat.format(pair2.getFirst().getTime()));
                sb.append(Soundex.SILENT_MARKER);
                Pair<? extends Calendar, ? extends Calendar> pair3 = this.u;
                if (pair3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                } else {
                    pair = pair3;
                }
                sb.append(simpleDateFormat.format(pair.getSecond().getTime()));
                shareData.setDwmValue(sb.toString());
            } else if (i == this.t) {
                shareData.setGraphType(getString(R.string.share_monthly));
                StringBuilder sb2 = new StringBuilder();
                Pair<? extends Calendar, ? extends Calendar> pair4 = this.u;
                if (pair4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                    pair4 = null;
                }
                sb2.append(simpleDateFormat.format(pair4.getFirst().getTime()));
                sb2.append(Soundex.SILENT_MARKER);
                Pair<? extends Calendar, ? extends Calendar> pair5 = this.u;
                if (pair5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                } else {
                    pair = pair5;
                }
                sb2.append(simpleDateFormat.format(pair.getSecond().getTime()));
                shareData.setDwmValue(sb2.toString());
            }
        }
        AppNavigator.Companion companion = AppNavigator.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getResources().getString(R.string.heart_rate);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.heart_rate)");
        companion.navigateToShareScreen(requireContext, shareData, string);
    }

    public final void t(Calendar calendar, Calendar calendar2) {
        if (isAdded()) {
            if (DateUtils.isToday(calendar2.getTimeInMillis())) {
                s().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                s().vitalsMainData.ibForward.setEnabled(false);
            } else {
                s().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                s().vitalsMainData.ibForward.setEnabled(true);
            }
            TextView textView = s().vitalsMainData.tvSelectedTypeValue;
            StringBuilder sb = new StringBuilder();
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            sb.append(themesUtils.getDateFormatDDMMMYYYY(calendar));
            sb.append(" - ");
            sb.append(themesUtils.getDateFormatDDMMMYYYY(calendar2));
            textView.setText(sb.toString());
            FragmentHeartRateViewModel fragmentHeartRateViewModel = this.n;
            if (fragmentHeartRateViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentHeartRateViewModel = null;
            }
            fragmentHeartRateViewModel.selectWeeklyView(calendar, calendar2);
        }
    }

    public final void u(Calendar calendar) {
        if (isAdded()) {
            TextView textView = s().vitalsMainData.tvSelectedTypeValue;
            long timeInMillis = calendar.getTimeInMillis();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            textView.setText(PayUtils.getTodayYesterdayStringFromTimeStamp(timeInMillis, requireContext));
            if (DateUtils.isToday(calendar.getTimeInMillis())) {
                s().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                s().vitalsMainData.ibForward.setEnabled(false);
            } else {
                s().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                s().vitalsMainData.ibForward.setEnabled(true);
            }
            FragmentHeartRateViewModel fragmentHeartRateViewModel = this.n;
            FragmentHeartRateViewModel fragmentHeartRateViewModel2 = null;
            if (fragmentHeartRateViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentHeartRateViewModel = null;
            }
            fragmentHeartRateViewModel.loadBpHourlyData(calendar);
            FragmentHeartRateViewModel fragmentHeartRateViewModel3 = this.n;
            if (fragmentHeartRateViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                fragmentHeartRateViewModel2 = fragmentHeartRateViewModel3;
            }
            fragmentHeartRateViewModel2.loadDailyData(calendar);
            this.p = calendar;
        }
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractHeartRateDashboard
    public void updateDailyLevelData(@Nullable EntityDailyHeartRateData entityDailyHeartRateData) {
        if (entityDailyHeartRateData != null && entityDailyHeartRateData.getMinHeartRate() > 0 && entityDailyHeartRateData.getMaxHeartRate() > 0) {
            setGraphVisible(true);
            TextView textView = s().vitalsMainData.tvVitalMinValue;
            textView.setText(entityDailyHeartRateData.getMinHeartRate() + ' ' + getString(R.string.bpm));
            TextView textView2 = s().vitalsMainData.tvVitalMaxValue;
            textView2.setText(entityDailyHeartRateData.getMaxHeartRate() + ' ' + getString(R.string.bpm));
            SpannableString spannableString = new SpannableString(s().vitalsMainData.tvVitalMaxValue.getText().toString());
            RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(0.5f);
            String obj = s().vitalsMainData.tvVitalMaxValue.getText().toString();
            String string = getString(R.string.bpm);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bpm)");
            spannableString.setSpan(relativeSizeSpan, StringsKt__StringsKt.indexOf$default((CharSequence) obj, string, 0, false, 6, (Object) null), s().vitalsMainData.tvVitalMaxValue.getText().toString().length(), 0);
            s().vitalsMainData.tvVitalMaxValue.setText(spannableString);
            SpannableString spannableString2 = new SpannableString(s().vitalsMainData.tvVitalMinValue.getText().toString());
            RelativeSizeSpan relativeSizeSpan2 = new RelativeSizeSpan(0.5f);
            String obj2 = s().vitalsMainData.tvVitalMinValue.getText().toString();
            String string2 = getString(R.string.bpm);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.bpm)");
            spannableString2.setSpan(relativeSizeSpan2, StringsKt__StringsKt.indexOf$default((CharSequence) obj2, string2, 0, false, 6, (Object) null), s().vitalsMainData.tvVitalMinValue.getText().toString().length(), 0);
            s().vitalsMainData.tvVitalMinValue.setText(spannableString2);
            s().vitalsMainData.tvSelectedTypeValue.setVisibility(0);
            Date parse = this.o.parse(PayUtils.INSTANCE.getDateFormatValue(this.p));
            if (PayUtils.currentDayString().equals(getSimpleDateFormat().format(parse))) {
                s().vitalsMainData.tvSelectedTypeValue.setText(getResources().getString(R.string.today));
                return;
            } else if (PayUtils.previousDayString().equals(getSimpleDateFormat().format(parse))) {
                s().vitalsMainData.tvSelectedTypeValue.setText(getResources().getString(R.string.yesterday));
                return;
            } else {
                s().vitalsMainData.tvSelectedTypeValue.setText(getSimpleDateFormat().format(parse));
                return;
            }
        }
        Date parse2 = this.o.parse(PayUtils.INSTANCE.getDateFormatValue(this.p));
        if (PayUtils.currentDayString().equals(getSimpleDateFormat().format(parse2))) {
            s().vitalsMainData.tvSelectedTypeValue.setText(getResources().getString(R.string.today));
        } else if (PayUtils.previousDayString().equals(getSimpleDateFormat().format(parse2))) {
            s().vitalsMainData.tvSelectedTypeValue.setText(getResources().getString(R.string.yesterday));
        } else {
            s().vitalsMainData.tvSelectedTypeValue.setText(getSimpleDateFormat().format(parse2));
        }
        TextView textView3 = s().vitalsMainData.tvVitalMinValue;
        StringBuilder sb = new StringBuilder();
        AppConstants appConstants = AppConstants.TWO_DASH_STRING;
        sb.append(appConstants.getValue());
        sb.append(' ');
        sb.append(getString(R.string.bpm));
        textView3.setText(sb.toString());
        TextView textView4 = s().vitalsMainData.tvVitalMaxValue;
        textView4.setText(appConstants.getValue() + ' ' + getString(R.string.bpm));
        SpannableString spannableString3 = new SpannableString(s().vitalsMainData.tvVitalMaxValue.getText().toString());
        RelativeSizeSpan relativeSizeSpan3 = new RelativeSizeSpan(0.5f);
        String obj3 = s().vitalsMainData.tvVitalMaxValue.getText().toString();
        String string3 = getString(R.string.bpm);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.bpm)");
        spannableString3.setSpan(relativeSizeSpan3, StringsKt__StringsKt.indexOf$default((CharSequence) obj3, string3, 0, false, 6, (Object) null), s().vitalsMainData.tvVitalMaxValue.getText().toString().length(), 0);
        s().vitalsMainData.tvVitalMaxValue.setText(spannableString3);
        SpannableString spannableString4 = new SpannableString(s().vitalsMainData.tvVitalMinValue.getText().toString());
        RelativeSizeSpan relativeSizeSpan4 = new RelativeSizeSpan(0.5f);
        String obj4 = s().vitalsMainData.tvVitalMinValue.getText().toString();
        String string4 = getString(R.string.bpm);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.bpm)");
        spannableString4.setSpan(relativeSizeSpan4, StringsKt__StringsKt.indexOf$default((CharSequence) obj4, string4, 0, false, 6, (Object) null), s().vitalsMainData.tvVitalMinValue.getText().toString().length(), 0);
        s().vitalsMainData.tvVitalMinValue.setText(spannableString4);
        setGraphVisible(false);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractHeartRateDashboard
    public void updateHourlyLevelData(@Nullable ArrayList<CandleEntry> arrayList, @Nullable ArrayList<String> arrayList2) {
        if (!AppUtils.isEmpty(arrayList) && !AppUtils.isEmpty(arrayList2)) {
            setGraphVisible(true);
            if (arrayList2 != null) {
                Intrinsics.checkNotNull(arrayList);
                G(arrayList, arrayList2);
                return;
            }
            return;
        }
        setGraphVisible(false);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractHeartRateDashboard
    public void updateRangeLevelData(@NotNull List<? extends EntityDailyHeartRateData> dailyHrList) {
        Intrinsics.checkNotNullParameter(dailyHrList, "dailyHrList");
        if (!dailyHrList.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Iterator<? extends EntityDailyHeartRateData> it = dailyHrList.iterator();
            while (it.hasNext()) {
                EntityDailyHeartRateData next = it.next();
                if ((next != null ? Integer.valueOf(next.getMinHeartRate()) : null) != null) {
                    Integer valueOf = next != null ? Integer.valueOf(next.getMinHeartRate()) : null;
                    Intrinsics.checkNotNull(valueOf);
                    if (valueOf.intValue() > 0) {
                        Integer valueOf2 = next != null ? Integer.valueOf(next.getMinHeartRate()) : null;
                        Intrinsics.checkNotNull(valueOf2);
                        arrayList.add(valueOf2);
                    }
                }
                if ((next != null ? Integer.valueOf(next.getMaxHeartRate()) : null) != null) {
                    Integer valueOf3 = next != null ? Integer.valueOf(next.getMaxHeartRate()) : null;
                    Intrinsics.checkNotNull(valueOf3);
                    if (valueOf3.intValue() > 0) {
                        Integer valueOf4 = next != null ? Integer.valueOf(next.getMaxHeartRate()) : null;
                        Intrinsics.checkNotNull(valueOf4);
                        arrayList2.add(valueOf4);
                    }
                }
            }
            if (arrayList.size() > 0) {
                TextView textView = s().vitalsMainData.tvVitalMinValue;
                textView.setText(BleApiUtils.INSTANCE.getMinValueFromList(arrayList) + ' ' + getString(R.string.bpm));
                SpannableString spannableString = new SpannableString(s().vitalsMainData.tvVitalMaxValue.getText().toString());
                RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(0.5f);
                String obj = s().vitalsMainData.tvVitalMaxValue.getText().toString();
                String string = getString(R.string.bpm);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bpm)");
                spannableString.setSpan(relativeSizeSpan, StringsKt__StringsKt.indexOf$default((CharSequence) obj, string, 0, false, 6, (Object) null), s().vitalsMainData.tvVitalMaxValue.getText().toString().length(), 0);
                s().vitalsMainData.tvVitalMaxValue.setText(spannableString);
            } else {
                F();
            }
            if (arrayList2.size() > 0) {
                TextView textView2 = s().vitalsMainData.tvVitalMaxValue;
                textView2.setText(BleApiUtils.INSTANCE.getMaxValue(arrayList2) + ' ' + getString(R.string.bpm));
                SpannableString spannableString2 = new SpannableString(s().vitalsMainData.tvVitalMinValue.getText().toString());
                RelativeSizeSpan relativeSizeSpan2 = new RelativeSizeSpan(0.5f);
                String obj2 = s().vitalsMainData.tvVitalMinValue.getText().toString();
                String string2 = getString(R.string.bpm);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.bpm)");
                spannableString2.setSpan(relativeSizeSpan2, StringsKt__StringsKt.indexOf$default((CharSequence) obj2, string2, 0, false, 6, (Object) null), s().vitalsMainData.tvVitalMinValue.getText().toString().length(), 0);
                s().vitalsMainData.tvVitalMinValue.setText(spannableString2);
                return;
            }
            F();
            return;
        }
        F();
    }

    public final void v() {
        FragmentVitalHeartrateBinding s = s();
        s.vitalsMainData.tvSelectedTypeValue.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalHeartRateFragment.w(VitalHeartRateFragment.this, view);
            }
        });
        s.vitalsMainData.ibPrevious.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalHeartRateFragment.x(VitalHeartRateFragment.this, view);
            }
        });
        s.vitalsMainData.ibForward.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalHeartRateFragment.y(VitalHeartRateFragment.this, view);
            }
        });
        s.vitalsMainData.ibShareVitals.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalHeartRateFragment.z(VitalHeartRateFragment.this, view);
            }
        });
        s.vitalsMainData.tvDay.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalHeartRateFragment.A(VitalHeartRateFragment.this, view);
            }
        });
        s.vitalsMainData.tvWeek.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalHeartRateFragment.B(VitalHeartRateFragment.this, view);
            }
        });
        s.vitalsMainData.tvMonth.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalHeartRateFragment.C(VitalHeartRateFragment.this, view);
            }
        });
    }
}
