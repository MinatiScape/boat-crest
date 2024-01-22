package com.coveiot.android.leonardo.dashboard.health.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.ViewModelFactory;
import com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractHeartRateDashboard;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentHeartRateViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.watchfaceui.constants.WatchfaceConstants;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.c;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentHeartRate extends Fragment implements View.OnClickListener, ContractHeartRateDashboard {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public DatePickerDialog datePickerDialog;
    public View[] dateSelectionView;
    @Nullable
    public Calendar i;
    public FragmentHeartRateViewModel j;
    public SimpleDateFormat simpleDateFormat;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final SimpleDateFormat h = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
    public final int k = 1;
    public boolean l = true;

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentHeartRate newInstance() {
            return new FragmentHeartRate();
        }
    }

    public static final void c(FragmentHeartRate this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i, i2, i3);
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        this$0.e(newDate);
        TextView tv_date1 = (TextView) this$0._$_findCachedViewById(R.id.tv_date1);
        Intrinsics.checkNotNullExpressionValue(tv_date1, "tv_date1");
        this$0.f(tv_date1);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    public final void b() {
        if (((TextView) _$_findCachedViewById(R.id.tv_date1)) != null) {
            Calendar calendar = Calendar.getInstance();
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity);
            setDatePickerDialog(new DatePickerDialog(activity, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.leonardo.dashboard.health.fragments.b
                @Override // android.app.DatePickerDialog.OnDateSetListener
                public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                    FragmentHeartRate.c(FragmentHeartRate.this, datePicker, i, i2, i3);
                }
            }, calendar.get(1), calendar.get(2), calendar.get(5)));
        }
    }

    public final void d(ArrayList<CandleEntry> arrayList, final ArrayList<String> arrayList2) {
        int i = R.id.candlestickchart;
        ((CandleStickChart) _$_findCachedViewById(i)).setVisibility(0);
        ((CandleStickChart) _$_findCachedViewById(i)).clear();
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
        candleDataSet.setBarSpace(0.3f);
        candleDataSet.setHighlightLineWidth(0.0f);
        candleDataSet.setDrawHorizontalHighlightIndicator(false);
        candleDataSet.setHighlightEnabled(true);
        candleDataSet.setDrawVerticalHighlightIndicator(false);
        XAxis xAxis = ((CandleStickChart) _$_findCachedViewById(i)).getXAxis();
        xAxis.setTextColor(getResources().getColor(R.color.secondary_text_color));
        xAxis.setDrawGridLines(false);
        YAxis axisLeft = ((CandleStickChart) _$_findCachedViewById(i)).getAxisLeft();
        axisLeft.setTextColor(getResources().getColor(R.color.secondary_text_color));
        axisLeft.setDrawGridLines(false);
        axisLeft.setAxisMinimum(0.0f);
        axisLeft.setEnabled(true);
        axisLeft.setAxisMaximum(200.0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() { // from class: com.coveiot.android.leonardo.dashboard.health.fragments.FragmentHeartRate$setGrapValues$1
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
        CustomMarkerView customMarkerView = new CustomMarkerView(getContext(), R.layout.custom_marker_view);
        customMarkerView.setChartView((CandleStickChart) _$_findCachedViewById(i));
        ((CandleStickChart) _$_findCachedViewById(i)).setMarker(customMarkerView);
        ((CandleStickChart) _$_findCachedViewById(i)).getDescription().setEnabled(false);
        ((CandleStickChart) _$_findCachedViewById(i)).setDrawGridBackground(false);
        ((CandleStickChart) _$_findCachedViewById(i)).setDrawBorders(false);
        ((CandleStickChart) _$_findCachedViewById(i)).setVisibleXRangeMinimum(5.0f);
        ((CandleStickChart) _$_findCachedViewById(i)).setPinchZoom(false);
        ((CandleStickChart) _$_findCachedViewById(i)).setAutoScaleMinMaxEnabled(false);
        ((CandleStickChart) _$_findCachedViewById(i)).setScaleEnabled(false);
        ((CandleStickChart) _$_findCachedViewById(i)).getAxisRight().setEnabled(false);
        ((CandleStickChart) _$_findCachedViewById(i)).invalidate();
        ((CandleStickChart) _$_findCachedViewById(i)).setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.coveiot.android.leonardo.dashboard.health.fragments.FragmentHeartRate$setGrapValues$2
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

    public final void e(Calendar calendar) {
        if (isAdded()) {
            int i = R.id.tv_date1;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            ((TextView) _$_findCachedViewById(i)).setText(format);
            ((TextView) _$_findCachedViewById(i)).setTag(calendar.clone());
            calendar.add(5, -this.k);
            int i2 = R.id.tv_date2;
            String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            ((TextView) _$_findCachedViewById(i2)).setText(format2);
            ((TextView) _$_findCachedViewById(i2)).setTag(calendar.clone());
            calendar.add(5, -this.k);
            int i3 = R.id.tv_date3;
            String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
            Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
            ((TextView) _$_findCachedViewById(i3)).setText(format3);
            ((TextView) _$_findCachedViewById(i3)).setTag(calendar.clone());
            calendar.add(5, -this.k);
            int i4 = R.id.tv_date4;
            String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
            Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
            ((TextView) _$_findCachedViewById(i4)).setText(format4);
            ((TextView) _$_findCachedViewById(i4)).setTag(calendar.clone());
            calendar.add(5, -this.k);
            int i5 = R.id.tv_date5;
            String format5 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
            Intrinsics.checkNotNullExpressionValue(format5, "format(locale, format, *args)");
            ((TextView) _$_findCachedViewById(i5)).setText(format5);
            ((TextView) _$_findCachedViewById(i5)).setTag(calendar.clone());
        }
    }

    public final void f(TextView textView) {
        if (isAdded()) {
            int i = R.id.tv_date1;
            ((TextView) _$_findCachedViewById(i)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            int i2 = R.id.tv_date2;
            ((TextView) _$_findCachedViewById(i2)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            int i3 = R.id.tv_date3;
            ((TextView) _$_findCachedViewById(i3)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            int i4 = R.id.tv_date4;
            ((TextView) _$_findCachedViewById(i4)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            int i5 = R.id.tv_date5;
            ((TextView) _$_findCachedViewById(i5)).setTextColor(getResources().getColor(R.color.secondary_text_color));
            ((TextView) _$_findCachedViewById(i)).setBackgroundResource(R.drawable.date_unselected_health);
            ((TextView) _$_findCachedViewById(i2)).setBackgroundResource(R.drawable.date_unselected_health);
            ((TextView) _$_findCachedViewById(i3)).setBackgroundResource(R.drawable.date_unselected_health);
            ((TextView) _$_findCachedViewById(i4)).setBackgroundResource(R.drawable.date_unselected_health);
            ((TextView) _$_findCachedViewById(i5)).setBackgroundResource(R.drawable.date_unselected_health);
            textView.setTextColor(getResources().getColor(R.color.secondary_text_color));
            textView.setBackgroundResource(R.drawable.circle_with_primary_color_solid);
            FragmentHeartRateViewModel fragmentHeartRateViewModel = null;
            if (textView.getTag() == null) {
                FragmentHeartRateViewModel fragmentHeartRateViewModel2 = this.j;
                if (fragmentHeartRateViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fragmentHeartRateViewModel2 = null;
                }
                Calendar calendar = Calendar.getInstance();
                Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
                fragmentHeartRateViewModel2.loadBpHourlyData(calendar);
                FragmentHeartRateViewModel fragmentHeartRateViewModel3 = this.j;
                if (fragmentHeartRateViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    fragmentHeartRateViewModel = fragmentHeartRateViewModel3;
                }
                Calendar calendar2 = Calendar.getInstance();
                Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
                fragmentHeartRateViewModel.loadDailyData(calendar2);
                this.i = Calendar.getInstance();
                return;
            }
            FragmentHeartRateViewModel fragmentHeartRateViewModel4 = this.j;
            if (fragmentHeartRateViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentHeartRateViewModel4 = null;
            }
            Object tag = textView.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type java.util.Calendar");
            fragmentHeartRateViewModel4.loadBpHourlyData((Calendar) tag);
            FragmentHeartRateViewModel fragmentHeartRateViewModel5 = this.j;
            if (fragmentHeartRateViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                fragmentHeartRateViewModel = fragmentHeartRateViewModel5;
            }
            Object tag2 = textView.getTag();
            Intrinsics.checkNotNull(tag2, "null cannot be cast to non-null type java.util.Calendar");
            fragmentHeartRateViewModel.loadDailyData((Calendar) tag2);
            Object tag3 = textView.getTag();
            Intrinsics.checkNotNull(tag3, "null cannot be cast to non-null type java.util.Calendar");
            this.i = (Calendar) tag3;
        }
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
    public final View[] getDateSelectionView() {
        View[] viewArr = this.dateSelectionView;
        if (viewArr != null) {
            return viewArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dateSelectionView");
        return null;
    }

    public final int getPosition1() {
        return this.k;
    }

    public final boolean getResponsechange() {
        return this.l;
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

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractHeartRateDashboard
    public boolean isSyncInProgress() {
        FragmentActivity requireActivity = requireActivity();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        return ((DataSyncViewModel) ViewModelProviders.of(requireActivity, new ViewModelFactory(requireActivity2)).get(DataSyncViewModel.class)).checkIsSyncing();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        FragmentHeartRateViewModel fragmentHeartRateViewModel = (FragmentHeartRateViewModel) ViewModelProviders.of(this, new com.coveiot.android.leonardo.utils.ViewModelFactory(context)).get(FragmentHeartRateViewModel.class);
        this.j = fragmentHeartRateViewModel;
        FragmentHeartRateViewModel fragmentHeartRateViewModel2 = null;
        if (fragmentHeartRateViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentHeartRateViewModel = null;
        }
        fragmentHeartRateViewModel.setContractHeartRateDashboard$app_prodRelease(this);
        FragmentHeartRateViewModel fragmentHeartRateViewModel3 = this.j;
        if (fragmentHeartRateViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentHeartRateViewModel3 = null;
        }
        fragmentHeartRateViewModel3.setMLifecycleOwner(this);
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
        Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"dd MMMM yyyy\")");
        setSimpleDateFormat(simpleDateFormat);
        TextView tv_date1 = (TextView) _$_findCachedViewById(R.id.tv_date1);
        Intrinsics.checkNotNullExpressionValue(tv_date1, "tv_date1");
        TextView tv_date2 = (TextView) _$_findCachedViewById(R.id.tv_date2);
        Intrinsics.checkNotNullExpressionValue(tv_date2, "tv_date2");
        TextView tv_date3 = (TextView) _$_findCachedViewById(R.id.tv_date3);
        Intrinsics.checkNotNullExpressionValue(tv_date3, "tv_date3");
        TextView tv_date4 = (TextView) _$_findCachedViewById(R.id.tv_date4);
        Intrinsics.checkNotNullExpressionValue(tv_date4, "tv_date4");
        TextView tv_date5 = (TextView) _$_findCachedViewById(R.id.tv_date5);
        Intrinsics.checkNotNullExpressionValue(tv_date5, "tv_date5");
        setDateSelectionView(new View[]{tv_date1, tv_date2, tv_date3, tv_date4, tv_date5});
        int length = getDateSelectionView().length;
        for (int i = 0; i < length; i++) {
            getDateSelectionView()[i].setOnClickListener(this);
        }
        AppConstants appConstants = AppConstants.HR_EMPTY;
        ((TextView) _$_findCachedViewById(R.id.tv_heart_rate)).setText(appConstants.getValue());
        ((TextView) _$_findCachedViewById(R.id.tv_minimum_bpm)).setText(String.valueOf(appConstants.getValue()));
        ((TextView) _$_findCachedViewById(R.id.tv_maximum_bpm)).setText(String.valueOf(appConstants.getValue()));
        setDefaultDateUi();
        ((ImageView) _$_findCachedViewById(R.id.calendar_image)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.tv_date1)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.tv_date2)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.tv_date3)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.tv_date4)).setOnClickListener(this);
        ((TextView) _$_findCachedViewById(R.id.tv_date5)).setOnClickListener(this);
        ((ImageView) _$_findCachedViewById(R.id.share_image)).setOnClickListener(this);
        ((ImageView) _$_findCachedViewById(R.id.ic_history_image)).setOnClickListener(this);
        FragmentHeartRateViewModel fragmentHeartRateViewModel4 = this.j;
        if (fragmentHeartRateViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentHeartRateViewModel4 = null;
        }
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        fragmentHeartRateViewModel4.loadBpHourlyData(calendar);
        FragmentHeartRateViewModel fragmentHeartRateViewModel5 = this.j;
        if (fragmentHeartRateViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentHeartRateViewModel2 = fragmentHeartRateViewModel5;
        }
        Calendar calendar2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
        fragmentHeartRateViewModel2.loadDailyData(calendar2);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.HEART_RATE_CURRENT_DAY_DASHBOARD_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        Intrinsics.checkNotNull(view);
        if (view.getId() == R.id.calendar_image) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.HEART_RATE_CURRENT_DAY_DASHBOARD_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_CALENDAR.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.HEART_RATE_CALENDAR_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            b();
            getDatePickerDialog().getDatePicker().setMaxDate(System.currentTimeMillis());
            getDatePickerDialog().show();
        } else if (view.getId() == R.id.ic_history_image) {
            AnalyticsLog analyticsLog2 = new AnalyticsLog();
            analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.HEART_RATE_CURRENT_DAY_DASHBOARD_SCREEN.getValue());
            analyticsLog2.setDescription(FirebaseEventParams.Description.OPEN_HEART_RATE_HISTORY_SCREEN.getValue());
            analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.HEART_RATE_HISTORY_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
            AppNavigator.Companion companion = AppNavigator.Companion;
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            companion.navigateToHRHistory(context);
        } else if (view.getId() == R.id.share_image) {
            AnalyticsLog analyticsLog3 = new AnalyticsLog();
            analyticsLog3.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog3.setScreenName(FirebaseEventParams.ScreenName.HEART_RATE_CURRENT_DAY_DASHBOARD_SCREEN.getValue());
            analyticsLog3.setDescription(FirebaseEventParams.Description.OPEN_SHARE.getValue());
            analyticsLog3.setUiElementName(FirebaseEventParams.UiElementName.HEART_RATE_SHARE_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog3);
            share();
        } else {
            AnalyticsLog analyticsLog4 = new AnalyticsLog();
            analyticsLog4.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog4.setScreenName(FirebaseEventParams.ScreenName.HEART_RATE_CURRENT_DAY_DASHBOARD_SCREEN.getValue());
            analyticsLog4.setDescription(FirebaseEventParams.Description.OPEN_DAYS_GRAPH.getValue());
            analyticsLog4.setUiElementName(FirebaseEventParams.UiElementName.DAYS_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog4);
            this.l = view.getId() == R.id.tv_date5;
            f((TextView) view);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_heart_rate, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void setDatePickerDialog(@NotNull DatePickerDialog datePickerDialog) {
        Intrinsics.checkNotNullParameter(datePickerDialog, "<set-?>");
        this.datePickerDialog = datePickerDialog;
    }

    public final void setDateSelectionView(@NotNull View[] viewArr) {
        Intrinsics.checkNotNullParameter(viewArr, "<set-?>");
        this.dateSelectionView = viewArr;
    }

    public final void setDefaultDateUi() {
        Calendar newDate = Calendar.getInstance();
        newDate.set(newDate.get(1), newDate.get(2), newDate.get(5));
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        e(newDate);
        TextView tv_date1 = (TextView) _$_findCachedViewById(R.id.tv_date1);
        Intrinsics.checkNotNullExpressionValue(tv_date1, "tv_date1");
        f(tv_date1);
    }

    public final void setResponsechange(boolean z) {
        this.l = z;
    }

    public final void setSimpleDateFormat(@NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
        this.simpleDateFormat = simpleDateFormat;
    }

    public final void share() {
        ShareData shareData = new ShareData();
        shareData.setHeartRate(((TextView) _$_findCachedViewById(R.id.tv_heart_rate)).getText().toString());
        shareData.setMin_bpm(((TextView) _$_findCachedViewById(R.id.tv_minimum_bpm)).getText().toString());
        shareData.setMax_bpm(((TextView) _$_findCachedViewById(R.id.tv_maximum_bpm)).getText().toString());
        shareData.setGraphType(getResources().getString(R.string.share_daily));
        shareData.setDwmValue(((TextView) _$_findCachedViewById(R.id.tv_date)).getText().toString());
        AppNavigator.Companion companion = AppNavigator.Companion;
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        String string = getResources().getString(R.string.heart_rate);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.heart_rate)");
        companion.navigateToShareScreen(context, shareData, string);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractHeartRateDashboard
    public void updateDailyLevelData(@Nullable EntityDailyHeartRateData entityDailyHeartRateData) {
        if (entityDailyHeartRateData != null && entityDailyHeartRateData.getMinHeartRate() > 0 && entityDailyHeartRateData.getMaxHeartRate() > 0) {
            int i = R.id.tv_heart_rate;
            ((TextView) _$_findCachedViewById(i)).setText(String.valueOf(entityDailyHeartRateData.getAvgHeartRate()));
            int i2 = R.id.tv_minimum_bpm;
            ((TextView) _$_findCachedViewById(i2)).setText(entityDailyHeartRateData.getMinHeartRate() + ' ' + getString(R.string.bpm));
            int i3 = R.id.tv_maximum_bpm;
            ((TextView) _$_findCachedViewById(i3)).setText(entityDailyHeartRateData.getMaxHeartRate() + ' ' + getString(R.string.bpm));
            SpannableString spannableString = new SpannableString(((TextView) _$_findCachedViewById(i3)).getText().toString());
            RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(0.5f);
            String obj = ((TextView) _$_findCachedViewById(i3)).getText().toString();
            String string = getString(R.string.bpm);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bpm)");
            spannableString.setSpan(relativeSizeSpan, StringsKt__StringsKt.indexOf$default((CharSequence) obj, string, 0, false, 6, (Object) null), ((TextView) _$_findCachedViewById(i3)).getText().toString().length(), 0);
            ((TextView) _$_findCachedViewById(i3)).setText(spannableString);
            SpannableString spannableString2 = new SpannableString(((TextView) _$_findCachedViewById(i2)).getText().toString());
            RelativeSizeSpan relativeSizeSpan2 = new RelativeSizeSpan(0.5f);
            String obj2 = ((TextView) _$_findCachedViewById(i2)).getText().toString();
            String string2 = getString(R.string.bpm);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.bpm)");
            spannableString2.setSpan(relativeSizeSpan2, StringsKt__StringsKt.indexOf$default((CharSequence) obj2, string2, 0, false, 6, (Object) null), ((TextView) _$_findCachedViewById(i2)).getText().toString().length(), 0);
            ((TextView) _$_findCachedViewById(i2)).setText(spannableString2);
            int i4 = R.id.tv_date;
            TextView textView = (TextView) _$_findCachedViewById(i4);
            FragmentActivity activity = getActivity();
            Context baseContext = activity != null ? activity.getBaseContext() : null;
            AppConstants appConstants = AppConstants.DAY;
            String str = appConstants.toString();
            String date = entityDailyHeartRateData.getDate();
            Intrinsics.checkNotNullExpressionValue(date, "dailyHr.date");
            textView.setText(PayUtils.getTodayYesterdayDate$default(baseContext, str, date, 0, 8, null));
            ((TextView) _$_findCachedViewById(i4)).setVisibility(0);
            if (m.equals("prod", "qa", true)) {
                FragmentActivity activity2 = getActivity();
                Context baseContext2 = activity2 != null ? activity2.getBaseContext() : null;
                String str2 = appConstants.toString();
                String date2 = entityDailyHeartRateData.getDate();
                Intrinsics.checkNotNullExpressionValue(date2, "dailyHr.date");
                if (m.equals(PayUtils.getTodayYesterdayDate$default(baseContext2, str2, date2, 0, 8, null), "Today", true)) {
                    ((TextView) _$_findCachedViewById(i)).setVisibility(8);
                    ((TextView) _$_findCachedViewById(R.id.tv_bpm)).setVisibility(8);
                    return;
                }
                ((TextView) _$_findCachedViewById(i)).setVisibility(8);
                ((TextView) _$_findCachedViewById(R.id.tv_bpm)).setVisibility(8);
                return;
            }
            ((TextView) _$_findCachedViewById(i)).setVisibility(8);
            ((TextView) _$_findCachedViewById(R.id.tv_bpm)).setVisibility(8);
            return;
        }
        int i5 = R.id.tv_date;
        ((TextView) _$_findCachedViewById(i5)).setVisibility(0);
        SimpleDateFormat simpleDateFormat = this.h;
        PayUtils payUtils = PayUtils.INSTANCE;
        Calendar calendar = this.i;
        Intrinsics.checkNotNull(calendar);
        Date parse = simpleDateFormat.parse(payUtils.getDateFormatValue(calendar));
        if (PayUtils.currentDayString().equals(getSimpleDateFormat().format(parse))) {
            ((TextView) _$_findCachedViewById(i5)).setText(getResources().getString(R.string.today));
        } else if (PayUtils.previousDayString().equals(getSimpleDateFormat().format(parse))) {
            ((TextView) _$_findCachedViewById(i5)).setText(getResources().getString(R.string.yesterday));
        } else {
            ((TextView) _$_findCachedViewById(i5)).setText(getSimpleDateFormat().format(parse));
        }
        AppConstants appConstants2 = AppConstants.HR_EMPTY;
        ((TextView) _$_findCachedViewById(R.id.tv_heart_rate)).setText(appConstants2.getValue());
        int i6 = R.id.tv_minimum_bpm;
        ((TextView) _$_findCachedViewById(i6)).setText(appConstants2.getValue() + ' ' + getString(R.string.bpm));
        int i7 = R.id.tv_maximum_bpm;
        ((TextView) _$_findCachedViewById(i7)).setText(appConstants2.getValue() + ' ' + getString(R.string.bpm));
        SpannableString spannableString3 = new SpannableString(((TextView) _$_findCachedViewById(i7)).getText().toString());
        RelativeSizeSpan relativeSizeSpan3 = new RelativeSizeSpan(0.5f);
        String obj3 = ((TextView) _$_findCachedViewById(i7)).getText().toString();
        String string3 = getString(R.string.bpm);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.bpm)");
        spannableString3.setSpan(relativeSizeSpan3, StringsKt__StringsKt.indexOf$default((CharSequence) obj3, string3, 0, false, 6, (Object) null), ((TextView) _$_findCachedViewById(i7)).getText().toString().length(), 0);
        ((TextView) _$_findCachedViewById(i7)).setText(spannableString3);
        SpannableString spannableString4 = new SpannableString(((TextView) _$_findCachedViewById(i6)).getText().toString());
        RelativeSizeSpan relativeSizeSpan4 = new RelativeSizeSpan(0.5f);
        String obj4 = ((TextView) _$_findCachedViewById(i6)).getText().toString();
        String string4 = getString(R.string.bpm);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.bpm)");
        spannableString4.setSpan(relativeSizeSpan4, StringsKt__StringsKt.indexOf$default((CharSequence) obj4, string4, 0, false, 6, (Object) null), ((TextView) _$_findCachedViewById(i6)).getText().toString().length(), 0);
        ((TextView) _$_findCachedViewById(i6)).setText(spannableString4);
        ((TextView) _$_findCachedViewById(R.id.tv_bpm)).setVisibility(8);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractHeartRateDashboard
    public void updateHourlyLevelData(@Nullable ArrayList<CandleEntry> arrayList, @Nullable ArrayList<String> arrayList2) {
        if (arrayList != null) {
            ((CandleStickChart) _$_findCachedViewById(R.id.candlestickchart)).setVisibility(0);
            ((TextView) _$_findCachedViewById(R.id.no_data_tv)).setVisibility(8);
            if (arrayList2 != null) {
                d(arrayList, arrayList2);
                return;
            }
            return;
        }
        ((CandleStickChart) _$_findCachedViewById(R.id.candlestickchart)).setVisibility(8);
        ((TextView) _$_findCachedViewById(R.id.no_data_tv)).setVisibility(0);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractHeartRateDashboard
    public void updateRangeLevelData(@NotNull List<? extends EntityDailyHeartRateData> dailyHrList) {
        Intrinsics.checkNotNullParameter(dailyHrList, "dailyHrList");
    }
}
