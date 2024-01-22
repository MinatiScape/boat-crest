package com.coveiot.android.leonardo.dashboard.vitals.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentVitalPeriodicStressBinding;
import com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.dashboard.feedback.ContractGenericFeedBackQuestionsList;
import com.coveiot.android.leonardo.dashboard.feedback.PagerAdapterFeedbackGeneric;
import com.coveiot.android.leonardo.dashboard.feedback.eventdata.StressFeedbackEventData;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicStressDashboard;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.dashboard.health.model.StressPercentage;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStressPeriodicViewModel;
import com.coveiot.android.leonardo.dashboard.model.StressInsightModel;
import com.coveiot.android.leonardo.dashboard.vitals.utils.MyBarDataset;
import com.coveiot.android.leonardo.more.viewmodel.AutoStressSettingsViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.RoundedBarChart;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.sleepenergyscore.energymeter.model.FeedbackQuetionnarieModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.adapter.TipsAdapter;
import com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarRangeDialog;
import com.coveiot.android.theme.compundview.CustomMarkerViewVitals;
import com.coveiot.android.theme.model.TipsModel;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.coveiot.covepreferences.data.AutoStressSettingsData;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.squareup.otto.Subscribe;
import com.viewpagerindicator.CirclePageIndicator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class VitalsPeriodicStressFragment extends BaseFragment implements ContractPeriodicStressDashboard, ContractGenericFeedBackQuestionsList, CalendarRangeDialog.OnCalendarRangeSelector {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public AutoStressSettingsData autoStressSettingsDataCommon;
    public AutoStressSettingsViewModel autoStressSettingsViewModel;
    public DatePickerDialog datePickerDialog;
    @Nullable
    public FragmentVitalPeriodicStressBinding m;
    public FragmentStressPeriodicViewModel n;
    @NotNull
    public Calendar o;
    public int p;
    public final int q;
    public final int r;
    public final int s;
    public SimpleDateFormat simpleDateFormat;
    public Pair<? extends Calendar, ? extends Calendar> t;
    public CalendarRangeDialog u;
    @NotNull
    public final int[] v;
    @NotNull
    public final int[] w;
    public boolean x;

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final VitalsPeriodicStressFragment newInstance() {
            return new VitalsPeriodicStressFragment();
        }
    }

    /* loaded from: classes4.dex */
    public static final class a extends Lambda implements Function1<StressInsightModel, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(StressInsightModel stressInsightModel) {
            invoke2(stressInsightModel);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(StressInsightModel it) {
            VitalsPeriodicStressFragment vitalsPeriodicStressFragment = VitalsPeriodicStressFragment.this;
            FragmentVitalPeriodicStressBinding v = vitalsPeriodicStressFragment.v();
            Intrinsics.checkNotNullExpressionValue(it, "it");
            vitalsPeriodicStressFragment.T(v, it);
        }
    }

    public VitalsPeriodicStressFragment() {
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this.o = calendar;
        this.r = 1;
        this.s = 2;
        this.v = new int[]{2131233536, 2131233537, 2131233538, 2131233539};
        this.w = new int[]{R.string.stress_reduce_tip1, R.string.stress_reduce_tip2, R.string.stress_reduce_tip3, R.string.stress_reduce_tip4};
    }

    public static final void A(final FragmentVitalPeriodicStressBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this_apply.nestedScrollStress.post(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.i1
            @Override // java.lang.Runnable
            public final void run() {
                VitalsPeriodicStressFragment.B(FragmentVitalPeriodicStressBinding.this);
            }
        });
    }

    public static final void B(FragmentVitalPeriodicStressBinding this_apply) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this_apply.nestedScrollStress.fullScroll(130);
    }

    public static final void E(VitalsPeriodicStressFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentStressPeriodicViewModel fragmentStressPeriodicViewModel = this$0.n;
        if (fragmentStressPeriodicViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStressPeriodicViewModel = null;
        }
        fragmentStressPeriodicViewModel.setIsWeekSelected(false);
        this$0.p = this$0.s;
        Calendar startDate = Calendar.getInstance();
        startDate.add(5, -30);
        Calendar endDate = Calendar.getInstance();
        this$0.S();
        this$0.t = new Pair<>(startDate, endDate);
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        Intrinsics.checkNotNullExpressionValue(endDate, "endDate");
        this$0.y(startDate, endDate);
    }

    public static final void F(VitalsPeriodicStressFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.p;
        if (i == this$0.q) {
            this$0.M();
            this$0.getDatePickerDialog().getDatePicker().setMaxDate(System.currentTimeMillis());
            this$0.getDatePickerDialog().show();
        } else if (i == this$0.r) {
            this$0.x = true;
            this$0.R();
        } else {
            this$0.x = false;
            this$0.R();
        }
    }

    public static final void G(VitalsPeriodicStressFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.p;
        if (i == this$0.q) {
            this$0.z(PayUtils.INSTANCE.getPreviousDayCalendar(this$0.o));
            return;
        }
        Pair<? extends Calendar, ? extends Calendar> pair = null;
        if (i == this$0.r) {
            if (this$0.t == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            }
            Pair<? extends Calendar, ? extends Calendar> pair2 = this$0.t;
            if (pair2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair2 = null;
            }
            Object clone = pair2.getFirst().clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar = (Calendar) clone;
            calendar.add(5, -6);
            Pair<? extends Calendar, ? extends Calendar> pair3 = this$0.t;
            if (pair3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair3;
            }
            Object clone2 = pair.getFirst().clone();
            Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar2 = (Calendar) clone2;
            this$0.t = new Pair<>(calendar, calendar2);
            this$0.y(calendar, calendar2);
            this$0.S();
        } else if (i == this$0.s) {
            Pair<? extends Calendar, ? extends Calendar> pair4 = this$0.t;
            if (pair4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair4 = null;
            }
            Object clone3 = pair4.getFirst().clone();
            Intrinsics.checkNotNull(clone3, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar3 = (Calendar) clone3;
            calendar3.add(5, -30);
            Pair<? extends Calendar, ? extends Calendar> pair5 = this$0.t;
            if (pair5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair5;
            }
            Object clone4 = pair.getFirst().clone();
            Intrinsics.checkNotNull(clone4, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar4 = (Calendar) clone4;
            this$0.t = new Pair<>(calendar3, calendar4);
            this$0.y(calendar3, calendar4);
            this$0.S();
        }
    }

    public static final void H(VitalsPeriodicStressFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.p;
        if (i == this$0.q) {
            if (DateUtils.isToday(this$0.o.getTimeInMillis())) {
                return;
            }
            this$0.z(PayUtils.INSTANCE.getNextDayCalendar(this$0.o));
            return;
        }
        Pair<? extends Calendar, ? extends Calendar> pair = null;
        if (i == this$0.r) {
            Pair<? extends Calendar, ? extends Calendar> pair2 = this$0.t;
            if (pair2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair2 = null;
            }
            if (DateUtils.isToday(pair2.getSecond().getTimeInMillis())) {
                return;
            }
            Pair<? extends Calendar, ? extends Calendar> pair3 = this$0.t;
            if (pair3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair3 = null;
            }
            Object clone = pair3.getSecond().clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar = (Calendar) clone;
            Pair<? extends Calendar, ? extends Calendar> pair4 = this$0.t;
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
                this$0.t = new Pair<>(calendar, calendar2);
                this$0.y(calendar, calendar2);
                this$0.S();
                return;
            }
            int findDateDifference = AppUtils.findDateDifference(Calendar.getInstance().getTime(), calendar2.getTime());
            Object clone3 = calendar.clone();
            Intrinsics.checkNotNull(clone3, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar3 = (Calendar) clone3;
            calendar3.add(5, 6 - findDateDifference);
            this$0.t = new Pair<>(calendar, calendar3);
            this$0.y(calendar, calendar3);
            this$0.S();
        } else if (i == this$0.s) {
            Pair<? extends Calendar, ? extends Calendar> pair5 = this$0.t;
            if (pair5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair5 = null;
            }
            if (DateUtils.isToday(pair5.getSecond().getTimeInMillis())) {
                return;
            }
            Pair<? extends Calendar, ? extends Calendar> pair6 = this$0.t;
            if (pair6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair6 = null;
            }
            Object clone4 = pair6.getSecond().clone();
            Intrinsics.checkNotNull(clone4, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar4 = (Calendar) clone4;
            Pair<? extends Calendar, ? extends Calendar> pair7 = this$0.t;
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
                this$0.t = new Pair<>(calendar4, calendar5);
                this$0.y(calendar4, calendar5);
                this$0.S();
                return;
            }
            int findDateDifference2 = AppUtils.findDateDifference(Calendar.getInstance().getTime(), calendar5.getTime());
            Object clone6 = calendar4.clone();
            Intrinsics.checkNotNull(clone6, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar6 = (Calendar) clone6;
            calendar6.add(5, 30 - findDateDifference2);
            this$0.t = new Pair<>(calendar4, calendar6);
            this$0.y(calendar4, calendar6);
            this$0.S();
        }
    }

    public static final void I(VitalsPeriodicStressFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.share();
    }

    public static final void J(VitalsPeriodicStressFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.p = this$0.q;
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this$0.z(calendar);
        this$0.S();
    }

    public static final void K(VitalsPeriodicStressFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentStressPeriodicViewModel fragmentStressPeriodicViewModel = this$0.n;
        if (fragmentStressPeriodicViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStressPeriodicViewModel = null;
        }
        fragmentStressPeriodicViewModel.setIsWeekSelected(true);
        this$0.p = this$0.r;
        Calendar startDate = Calendar.getInstance();
        startDate.add(5, -6);
        Calendar endDate = Calendar.getInstance();
        this$0.S();
        this$0.t = new Pair<>(startDate, endDate);
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        Intrinsics.checkNotNullExpressionValue(endDate, "endDate");
        this$0.y(startDate, endDate);
    }

    public static final void N(VitalsPeriodicStressFragment this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i, i2, i3);
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        this$0.z(newDate);
    }

    public static final void P(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void C(ArrayList<FeedbackQuetionnarieModel> arrayList) {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        PagerAdapterFeedbackGeneric pagerAdapterFeedbackGeneric = new PagerAdapterFeedbackGeneric(requireContext, childFragmentManager, arrayList);
        ViewPager viewPager = v().feedbackPager;
        if (viewPager != null) {
            viewPager.setAdapter(pagerAdapterFeedbackGeneric);
        }
        if (arrayList.size() > 1) {
            v().circlePageIndicatorFeedback.setViewPager(v().feedbackPager);
            return;
        }
        CirclePageIndicator circlePageIndicator = v().circlePageIndicatorFeedback;
        Intrinsics.checkNotNullExpressionValue(circlePageIndicator, "binding.circlePageIndicatorFeedback");
        gone(circlePageIndicator);
    }

    public final void D() {
        FragmentVitalPeriodicStressBinding v = v();
        v.vitalsMainData.tvSelectedTypeValue.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.n1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsPeriodicStressFragment.F(VitalsPeriodicStressFragment.this, view);
            }
        });
        v.vitalsMainData.ibPrevious.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.o1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsPeriodicStressFragment.G(VitalsPeriodicStressFragment.this, view);
            }
        });
        v.vitalsMainData.ibForward.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.m1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsPeriodicStressFragment.H(VitalsPeriodicStressFragment.this, view);
            }
        });
        v.vitalsMainData.ibShareVitals.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.k1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsPeriodicStressFragment.I(VitalsPeriodicStressFragment.this, view);
            }
        });
        v.vitalsMainData.tvDay.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.p1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsPeriodicStressFragment.J(VitalsPeriodicStressFragment.this, view);
            }
        });
        v.vitalsMainData.tvWeek.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.q1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsPeriodicStressFragment.K(VitalsPeriodicStressFragment.this, view);
            }
        });
        v.vitalsMainData.tvMonth.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.l1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsPeriodicStressFragment.E(VitalsPeriodicStressFragment.this, view);
            }
        });
    }

    public final void L(ArrayList<BarEntry> arrayList, ArrayList<String> arrayList2) {
        RoundedBarChart roundedBarChart = v().stressGraph;
        Intrinsics.checkNotNullExpressionValue(roundedBarChart, "binding.stressGraph");
        ArrayList arrayList3 = new ArrayList();
        Intrinsics.checkNotNull(arrayList);
        Iterator<BarEntry> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList3.add(Integer.valueOf((int) it.next().getY()));
        }
        MyBarDataset myBarDataset = new MyBarDataset(arrayList, "", arrayList3, requireContext());
        myBarDataset.setColors(ContextCompat.getColor(requireContext(), R.color.relaxed_stress_color), ContextCompat.getColor(requireContext(), R.color.mild_stress_color), ContextCompat.getColor(requireContext(), R.color.moderate_stress_color), ContextCompat.getColor(requireContext(), R.color.high_stress_color));
        new ArrayList().add(myBarDataset);
        BarData barData = new BarData(myBarDataset);
        roundedBarChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(arrayList2));
        barData.setDrawValues(false);
        roundedBarChart.setData(barData);
        roundedBarChart.setDrawGridBackground(false);
        roundedBarChart.getDescription().setEnabled(false);
        barData.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        roundedBarChart.getPaint(7).setColor(getResources().getColor(R.color.secondary_text_color));
        roundedBarChart.setDrawBorders(false);
        roundedBarChart.setAutoScaleMinMaxEnabled(false);
        roundedBarChart.setPinchZoom(false);
        YAxis axisLeft = roundedBarChart.getAxisLeft();
        axisLeft.setEnabled(true);
        axisLeft.setDrawAxisLine(true);
        axisLeft.setDrawGridLines(false);
        axisLeft.mAxisMaximum = 100.0f;
        axisLeft.setAxisLineWidth(1.0f);
        axisLeft.setAxisLineColor(getResources().getColor(R.color.secondary_text_color));
        roundedBarChart.getAxisRight().setEnabled(false);
        XAxis xAxis = roundedBarChart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineWidth(1.0f);
        xAxis.setAxisLineColor(getResources().getColor(R.color.secondary_text_color));
        roundedBarChart.getAxisLeft().setStartAtZero(true);
        roundedBarChart.getAxisRight().setStartAtZero(true);
        roundedBarChart.getAxisLeft().setTextColor(getResources().getColor(R.color.secondary_text_color));
        roundedBarChart.getXAxis().setTextColor(getResources().getColor(R.color.secondary_text_color));
        roundedBarChart.getLegend().setTextColor(getResources().getColor(R.color.secondary_text_color));
        roundedBarChart.getLegend().setEnabled(false);
        roundedBarChart.setVisibleXRangeMinimum(5.0f);
        Context context = getContext();
        String string = getString(R.string.stress);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.stress)");
        CustomMarkerViewVitals customMarkerViewVitals = new CustomMarkerViewVitals(context, R.layout.custom_marker_view_steps_hr, string, this.p, arrayList2);
        customMarkerViewVitals.setChartView(roundedBarChart);
        roundedBarChart.setMarker(customMarkerViewVitals);
        roundedBarChart.setPinchZoom(false);
        roundedBarChart.setAutoScaleMinMaxEnabled(false);
        roundedBarChart.setScaleEnabled(false);
        roundedBarChart.setDoubleTapToZoomEnabled(false);
        roundedBarChart.invalidate();
    }

    public final void M() {
        Calendar calendar = this.o;
        setDatePickerDialog(new DatePickerDialog(requireActivity(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.h1
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                VitalsPeriodicStressFragment.N(VitalsPeriodicStressFragment.this, datePicker, i, i2, i3);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)));
    }

    public final void O() {
        FragmentStressPeriodicViewModel fragmentStressPeriodicViewModel = this.n;
        if (fragmentStressPeriodicViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStressPeriodicViewModel = null;
        }
        MutableLiveData<StressInsightModel> stressInsightData = fragmentStressPeriodicViewModel.getStressInsightData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final a aVar = new a();
        stressInsightData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.r1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VitalsPeriodicStressFragment.P(Function1.this, obj);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0028, code lost:
        if (com.coveiot.android.respiratoryrate.utils.Utils.isCYDevice(r1) != false) goto L7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void Q() {
        /*
            r3 = this;
            com.coveiot.android.boat.databinding.FragmentVitalPeriodicStressBinding r0 = r3.v()
            com.coveiot.covepreferences.data.AutoStressSettingsData r1 = r3.getAutoStressSettingsDataCommon()
            boolean r1 = r1.getAutoStress()
            if (r1 != 0) goto L35
            android.content.Context r1 = r3.requireContext()
            java.lang.String r2 = "requireContext()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            boolean r1 = com.coveiot.android.respiratoryrate.utils.Utils.isCADevice(r1)
            if (r1 != 0) goto L2a
            android.content.Context r1 = r3.requireContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            boolean r1 = com.coveiot.android.respiratoryrate.utils.Utils.isCYDevice(r1)
            if (r1 == 0) goto L35
        L2a:
            com.coveiot.android.boat.databinding.FragmentVitalPeriodicStressBinding r1 = r3.v()
            android.widget.TextView r1 = r1.tvEnableHrvSettings
            r2 = 0
            r1.setVisibility(r2)
            goto L40
        L35:
            com.coveiot.android.boat.databinding.FragmentVitalPeriodicStressBinding r1 = r3.v()
            android.widget.TextView r1 = r1.tvEnableHrvSettings
            r2 = 8
            r1.setVisibility(r2)
        L40:
            androidx.constraintlayout.widget.ConstraintLayout r1 = r0.legendLayout
            java.lang.String r2 = "legendLayout"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r3.gone(r1)
            com.coveiot.android.leonardo.utils.RoundedBarChart r1 = r0.stressGraph
            java.lang.String r2 = "stressGraph"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r3.gone(r1)
            android.widget.TextView r1 = r0.tvNoDataFound
            java.lang.String r2 = "tvNoDataFound"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r3.visible(r1)
            androidx.recyclerview.widget.RecyclerView r1 = r0.stressTipsRecycler
            java.lang.String r2 = "stressTipsRecycler"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r3.gone(r1)
            android.widget.TextView r1 = r0.tvTipsToReduce
            java.lang.String r2 = "tvTipsToReduce"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r3.gone(r1)
            android.view.View r0 = r0.divider1
            java.lang.String r1 = "divider1"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r3.gone(r0)
            com.coveiot.android.boat.databinding.FragmentVitalPeriodicStressBinding r0 = r3.v()
            com.coveiot.android.leonardo.utils.RoundedBarChart r0 = r0.stressGraph
            r0.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsPeriodicStressFragment.Q():void");
    }

    public final void R() {
        if (isAdded()) {
            boolean z = this.x;
            Pair<? extends Calendar, ? extends Calendar> pair = this.t;
            if (pair == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair = null;
            }
            CalendarRangeDialog calendarRangeDialog = new CalendarRangeDialog(z, pair, this);
            this.u = calendarRangeDialog;
            calendarRangeDialog.show(getChildFragmentManager(), "");
        }
    }

    public final void S() {
        if (isAdded()) {
            int i = this.p;
            if (i == this.q) {
                v().vitalsMainData.tvDay.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                v().vitalsMainData.tvDay.setTextColor(requireContext().getColor(R.color.main_text_color));
                v().vitalsMainData.tvWeek.setBackground(null);
                v().vitalsMainData.tvWeek.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                v().vitalsMainData.tvMonth.setBackground(null);
                v().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                v().vitalsMainData.tvDay.setTextAppearance(R.style.bold);
                v().vitalsMainData.tvWeek.setTextAppearance(R.style.regular);
                v().vitalsMainData.tvMonth.setTextAppearance(R.style.regular);
            } else if (i == this.r) {
                v().vitalsMainData.tvDay.setBackground(null);
                v().vitalsMainData.tvDay.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                v().vitalsMainData.tvWeek.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                v().vitalsMainData.tvWeek.setTextColor(requireContext().getColor(R.color.main_text_color));
                v().vitalsMainData.tvMonth.setBackground(null);
                v().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                v().vitalsMainData.tvDay.setTextAppearance(R.style.regular);
                v().vitalsMainData.tvWeek.setTextAppearance(R.style.bold);
                v().vitalsMainData.tvMonth.setTextAppearance(R.style.regular);
            } else {
                v().vitalsMainData.tvDay.setBackground(null);
                v().vitalsMainData.tvDay.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                v().vitalsMainData.tvWeek.setBackground(null);
                v().vitalsMainData.tvWeek.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                v().vitalsMainData.tvMonth.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                v().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(R.color.main_text_color));
                v().vitalsMainData.tvDay.setTextAppearance(R.style.regular);
                v().vitalsMainData.tvWeek.setTextAppearance(R.style.regular);
                v().vitalsMainData.tvMonth.setTextAppearance(R.style.bold);
            }
        }
    }

    public final void T(FragmentVitalPeriodicStressBinding fragmentVitalPeriodicStressBinding, StressInsightModel stressInsightModel) {
        Integer stressDifference = stressInsightModel.getStressDifference();
        if (stressDifference == null || stressDifference.intValue() != 0) {
            fragmentVitalPeriodicStressBinding.tvStressPercentage.setTextAppearance(R.style.bold);
            fragmentVitalPeriodicStressBinding.tvStressPercentage.setTextSize(2, 24.0f);
            fragmentVitalPeriodicStressBinding.tvStressPercentage.setText(String.valueOf(stressInsightModel.getStressDifference()));
            TextView tvStressIncreasedDecreased = fragmentVitalPeriodicStressBinding.tvStressIncreasedDecreased;
            Intrinsics.checkNotNullExpressionValue(tvStressIncreasedDecreased, "tvStressIncreasedDecreased");
            visible(tvStressIncreasedDecreased);
            ImageView ivStressIncreaseDecrease = fragmentVitalPeriodicStressBinding.ivStressIncreaseDecrease;
            Intrinsics.checkNotNullExpressionValue(ivStressIncreaseDecrease, "ivStressIncreaseDecrease");
            visible(ivStressIncreaseDecrease);
            fragmentVitalPeriodicStressBinding.tvStressIncreasedDecreased.setText(stressInsightModel.isStressIncreased() ? getString(R.string.increased) : getString(R.string.decreased));
            if (stressInsightModel.isStressIncreased()) {
                fragmentVitalPeriodicStressBinding.ivStressIncreaseDecrease.setBackgroundResource(R.drawable.ic_small_green_arrow_up);
            } else {
                fragmentVitalPeriodicStressBinding.ivStressIncreaseDecrease.setBackgroundResource(R.drawable.ic_small_red_arrow_down);
            }
        } else {
            fragmentVitalPeriodicStressBinding.tvStressPercentage.setTextAppearance(R.style.semi_bold);
            fragmentVitalPeriodicStressBinding.tvStressPercentage.setTextSize(2, 16.0f);
            fragmentVitalPeriodicStressBinding.tvStressPercentage.setText(getString(R.string.no_change));
            TextView tvStressIncreasedDecreased2 = fragmentVitalPeriodicStressBinding.tvStressIncreasedDecreased;
            Intrinsics.checkNotNullExpressionValue(tvStressIncreasedDecreased2, "tvStressIncreasedDecreased");
            gone(tvStressIncreasedDecreased2);
            ImageView ivStressIncreaseDecrease2 = fragmentVitalPeriodicStressBinding.ivStressIncreaseDecrease;
            Intrinsics.checkNotNullExpressionValue(ivStressIncreaseDecrease2, "ivStressIncreaseDecrease");
            gone(ivStressIncreaseDecrease2);
        }
        TextView textView = fragmentVitalPeriodicStressBinding.tvStressInsightInfo;
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.your_stress_level_compared_to));
        sb.append(' ');
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        int i = this.p;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        sb.append(themesUtils.getYestWeekMonthText(i, requireContext));
        textView.setText(sb.toString());
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
    public final AutoStressSettingsData getAutoStressSettingsDataCommon() {
        AutoStressSettingsData autoStressSettingsData = this.autoStressSettingsDataCommon;
        if (autoStressSettingsData != null) {
            return autoStressSettingsData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("autoStressSettingsDataCommon");
        return null;
    }

    @NotNull
    public final AutoStressSettingsViewModel getAutoStressSettingsViewModel() {
        AutoStressSettingsViewModel autoStressSettingsViewModel = this.autoStressSettingsViewModel;
        if (autoStressSettingsViewModel != null) {
            return autoStressSettingsViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("autoStressSettingsViewModel");
        return null;
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
        FragmentStressPeriodicViewModel fragmentStressPeriodicViewModel = (FragmentStressPeriodicViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FragmentStressPeriodicViewModel.class);
        this.n = fragmentStressPeriodicViewModel;
        if (fragmentStressPeriodicViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStressPeriodicViewModel = null;
        }
        fragmentStressPeriodicViewModel.setContractPeriodicStressDashboard$app_prodRelease(this);
        FragmentStressPeriodicViewModel fragmentStressPeriodicViewModel2 = this.n;
        if (fragmentStressPeriodicViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStressPeriodicViewModel2 = null;
        }
        fragmentStressPeriodicViewModel2.setContractGenericFeedBackQuestionsList$app_prodRelease(this);
        FragmentStressPeriodicViewModel fragmentStressPeriodicViewModel3 = this.n;
        if (fragmentStressPeriodicViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStressPeriodicViewModel3 = null;
        }
        fragmentStressPeriodicViewModel3.setMLifecycleOwner(this);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        setAutoStressSettingsViewModel((AutoStressSettingsViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext2)).get(AutoStressSettingsViewModel.class));
        setAutoStressSettingsDataCommon(getAutoStressSettingsViewModel().getStressSettingsDataFromPref());
        String string = getString(R.string.enable_auto_stress_amp_hrv_monitoring_under_settings_to_measure_your_values);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.enabl…s_to_measure_your_values)");
        SpannableString spannableString = new SpannableString(string);
        Context context = getContext();
        Integer valueOf = context != null ? Integer.valueOf(context.getColor(R.color.colorAccent)) : null;
        Intrinsics.checkNotNull(valueOf);
        spannableString.setSpan(new ForegroundColorSpan(valueOf.intValue()), StringsKt__StringsKt.indexOf$default((CharSequence) string, "Watch features", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) string, "Watch features", 0, false, 6, (Object) null) + 14, 34);
        spannableString.setSpan(new UnderlineSpan(), StringsKt__StringsKt.indexOf$default((CharSequence) string, "Watch features", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) string, "Watch features", 0, false, 6, (Object) null) + 14, 34);
        spannableString.setSpan(new ClickableSpan() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsPeriodicStressFragment$initData$clickableSpan$1
            @Override // android.text.style.ClickableSpan
            public void onClick(@NotNull View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                AppNavigator.Companion companion = AppNavigator.Companion;
                Context context2 = VitalsPeriodicStressFragment.this.getContext();
                Intrinsics.checkNotNull(context2);
                companion.navigateToAutoStressHRVSettings(context2);
            }
        }, StringsKt__StringsKt.indexOf$default((CharSequence) string, "Watch features", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) string, "Watch features", 0, false, 6, (Object) null) + 14, 34);
        v().tvEnableHrvSettings.setText(spannableString, TextView.BufferType.SPANNABLE);
        v().tvEnableHrvSettings.setMovementMethod(LinkMovementMethod.getInstance());
        this.p = this.q;
        final FragmentVitalPeriodicStressBinding v = v();
        v.vitalsMainData.tvVitalName.setText(getString(R.string.stress_level));
        ConstraintLayout constraintLayout = v.vitalsMainData.clCenterDataEnergyAndStress;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "vitalsMainData.clCenterDataEnergyAndStress");
        visible(constraintLayout);
        ConstraintLayout constraintLayout2 = v.vitalsMainData.clCenterDataStepsSleep;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "vitalsMainData.clCenterDataStepsSleep");
        gone(constraintLayout2);
        v.vitalsMainData.ivMin.setImageResource(R.drawable.ic_stress_min);
        v.vitalsMainData.ivMax.setImageResource(R.drawable.ic_stress_max);
        v.vitalsMainData.ivCenterVital.setImageResource(R.drawable.ic_stress);
        v.vitalsMainData.tvAvgType.setText(getString(R.string.avg_stress_level));
        TextView textView = v.vitalsMainData.tvLastSyncTime;
        PayUtils payUtils = PayUtils.INSTANCE;
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        textView.setText(payUtils.getLastSyncTime(requireContext3));
        ConstraintLayout legendLayout = v.legendLayout;
        Intrinsics.checkNotNullExpressionValue(legendLayout, "legendLayout");
        visible(legendLayout);
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        TextView tvstressdescription = v.tvstressdescription;
        Intrinsics.checkNotNullExpressionValue(tvstressdescription, "tvstressdescription");
        themesUtils.makeTextViewResizable(tvstressdescription, 3, "... Read More", true);
        v.stressTipsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        TipsAdapter tipsAdapter = new TipsAdapter();
        tipsAdapter.setTipsList(x());
        v.stressTipsRecycler.setAdapter(tipsAdapter);
        v.vitalsMainData.tvVitalName.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.j1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsPeriodicStressFragment.A(FragmentVitalPeriodicStressBinding.this, view);
            }
        });
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
        if (companion.isSmaDevice(requireContext4)) {
            Context requireContext5 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
            if (companion.isSmaJieieDevice(requireContext5)) {
                v.stressRangeBar.setImageResource(R.drawable.ic_stress_range_bar_3);
                v.relaxed.setText(getString(R.string.relax));
                v.mild.setText(getString(R.string.normal));
                v.moderate.setText(getString(R.string.moderate));
                v.high.setText(getString(R.string.overstress));
            } else {
                v.stressRangeBar.setImageResource(R.drawable.stress_range_bar_2);
                v.relaxed.setText(getString(R.string.relaxed));
                v.mild.setText(getString(R.string.mild));
                v.moderate.setText(getString(R.string.moderate));
                v.high.setText(getString(R.string.high));
            }
        } else {
            Context requireContext6 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
            if (!companion.isCADevice(requireContext6)) {
                Context requireContext7 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext7, "requireContext()");
                if (!companion.isCYDevice(requireContext7)) {
                    Context requireContext8 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext8, "requireContext()");
                    if (!companion.isPS1Device(requireContext8)) {
                        Context requireContext9 = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext9, "requireContext()");
                        if (!companion.isBESDevice(requireContext9)) {
                            v.stressRangeBar.setImageResource(R.drawable.stress_range_bar_1);
                            v.relaxed.setText(getString(R.string.relax));
                            v.mild.setText(getString(R.string.normal));
                            v.moderate.setText(getString(R.string.medium));
                            v.high.setText(getString(R.string.high));
                        }
                    }
                }
            }
            v.stressRangeBar.setImageResource(R.drawable.stress_range_bar_2);
            v.relaxed.setText(getString(R.string.relaxed));
            v.mild.setText(getString(R.string.mild));
            v.moderate.setText(getString(R.string.moderate));
            v.high.setText(getString(R.string.high));
        }
        String string2 = getString(R.string.medical_disclaimer_info);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.medical_disclaimer_info)");
        SpannableString spannableString2 = new SpannableString(string2);
        Context context2 = getContext();
        Integer valueOf2 = context2 != null ? Integer.valueOf(context2.getColor(R.color.color_b3b3b3)) : null;
        Intrinsics.checkNotNull(valueOf2);
        spannableString2.setSpan(new ForegroundColorSpan(valueOf2.intValue()), 0, 12, 18);
        v.tvdisclaimer.setText(spannableString2);
        D();
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        z(calendar);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicStressDashboard
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
        this.t = new Pair<>(fromDate, toDate);
        y(fromDate, toDate);
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentVitalPeriodicStressBinding.inflate(inflater, viewGroup, false);
        View root = v().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.leonardo.dashboard.feedback.ContractGenericFeedBackQuestionsList
    public void onReceiveQuestionList(@Nullable ArrayList<FeedbackQuetionnarieModel> arrayList) {
        if (isAdded() && isVisible()) {
            if (arrayList != null && arrayList.size() > 0) {
                LinearLayout linearLayout = v().viewPagerLayout;
                Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.viewPagerLayout");
                visible(linearLayout);
                View view = v().divider2;
                Intrinsics.checkNotNullExpressionValue(view, "binding.divider2");
                visible(view);
                C(arrayList);
            } else {
                LinearLayout linearLayout2 = v().viewPagerLayout;
                Intrinsics.checkNotNullExpressionValue(linearLayout2, "binding.viewPagerLayout");
                gone(linearLayout2);
                View view2 = v().divider2;
                Intrinsics.checkNotNullExpressionValue(view2, "binding.divider2");
                gone(view2);
            }
        }
        if (getActivity() instanceof BaseActivity) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.theme.BaseActivity");
            ((BaseActivity) activity).dismissProgress();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setAutoStressSettingsDataCommon(getAutoStressSettingsViewModel().getStressSettingsDataFromPref());
        try {
            CoveEventBusManager.getInstance().getEventBus().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public final void onSleepScoreFeedbackUiUpdate(@NotNull StressFeedbackEventData stressFeedbackEventData) {
        Intrinsics.checkNotNullParameter(stressFeedbackEventData, "stressFeedbackEventData");
        FragmentStressPeriodicViewModel fragmentStressPeriodicViewModel = this.n;
        if (fragmentStressPeriodicViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStressPeriodicViewModel = null;
        }
        fragmentStressPeriodicViewModel.getFeedbackQuestionnarieList();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        try {
            CoveEventBusManager.getInstance().getEventBus().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initData();
        O();
    }

    public final void setAutoStressSettingsDataCommon(@NotNull AutoStressSettingsData autoStressSettingsData) {
        Intrinsics.checkNotNullParameter(autoStressSettingsData, "<set-?>");
        this.autoStressSettingsDataCommon = autoStressSettingsData;
    }

    public final void setAutoStressSettingsViewModel(@NotNull AutoStressSettingsViewModel autoStressSettingsViewModel) {
        Intrinsics.checkNotNullParameter(autoStressSettingsViewModel, "<set-?>");
        this.autoStressSettingsViewModel = autoStressSettingsViewModel;
    }

    public final void setDatePickerDialog(@NotNull DatePickerDialog datePickerDialog) {
        Intrinsics.checkNotNullParameter(datePickerDialog, "<set-?>");
        this.datePickerDialog = datePickerDialog;
    }

    public final void setSimpleDateFormat(@NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
        this.simpleDateFormat = simpleDateFormat;
    }

    public final void share() {
        ShareData shareData = new ShareData();
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM, yyyy");
        String obj = v().vitalsMainData.tvVitalMinValue.getText().toString();
        AppConstants appConstants = AppConstants.ZERO;
        if (!kotlin.text.m.equals(obj, appConstants.getValue(), true) && !kotlin.text.m.equals(v().vitalsMainData.tvVitalMaxValue.getText().toString(), appConstants.getValue(), true)) {
            shareData.setStress_min(StringsKt__StringsKt.trim(v().vitalsMainData.tvVitalMinValue.getText().toString()).toString());
            shareData.setStress_max(StringsKt__StringsKt.trim(v().vitalsMainData.tvVitalMaxValue.getText().toString()).toString());
        } else {
            shareData.setStress_min(appConstants.getValue());
            shareData.setStress_max(appConstants.getValue());
        }
        int i = this.p;
        if (i == this.q) {
            shareData.setGraphType(getString(R.string.share_daily));
            shareData.setDwmValue(simpleDateFormat.format(this.o.getTime()));
        } else {
            Pair<? extends Calendar, ? extends Calendar> pair = null;
            if (i == this.r) {
                shareData.setGraphType(getString(R.string.share_weekly));
                StringBuilder sb = new StringBuilder();
                Pair<? extends Calendar, ? extends Calendar> pair2 = this.t;
                if (pair2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                    pair2 = null;
                }
                sb.append(simpleDateFormat.format(pair2.getFirst().getTime()));
                sb.append(Soundex.SILENT_MARKER);
                Pair<? extends Calendar, ? extends Calendar> pair3 = this.t;
                if (pair3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                } else {
                    pair = pair3;
                }
                sb.append(simpleDateFormat.format(pair.getSecond().getTime()));
                shareData.setDwmValue(sb.toString());
            } else if (i == this.s) {
                shareData.setGraphType(getString(R.string.share_monthly));
                StringBuilder sb2 = new StringBuilder();
                Pair<? extends Calendar, ? extends Calendar> pair4 = this.t;
                if (pair4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                    pair4 = null;
                }
                sb2.append(simpleDateFormat.format(pair4.getFirst().getTime()));
                sb2.append(Soundex.SILENT_MARKER);
                Pair<? extends Calendar, ? extends Calendar> pair5 = this.t;
                if (pair5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                } else {
                    pair = pair5;
                }
                sb2.append(simpleDateFormat.format(pair.getSecond().getTime()));
                shareData.setDwmValue(sb2.toString());
            }
        }
        shareData.setStressType(ShareData.StressType.PERIODIC);
        AppNavigator.Companion companion = AppNavigator.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getResources().getString(R.string.stress);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.stress)");
        companion.navigateToShareScreen(requireContext, shareData, string);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicStressDashboard
    public void updateDailyLevelData(@Nullable DailyStress dailyStress) {
        if (dailyStress != null && dailyStress.stress_high > 0 && dailyStress.stress_low > 0) {
            FragmentVitalPeriodicStressBinding v = v();
            TextView textView = v.vitalsMainData.tvVitalMinValue;
            StringBuilder sb = new StringBuilder();
            sb.append(dailyStress.stress_low);
            sb.append(' ');
            textView.setText(sb.toString());
            TextView textView2 = v.vitalsMainData.tvVitalMaxValue;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(dailyStress.stress_high);
            sb2.append(' ');
            textView2.setText(sb2.toString());
            TextView textView3 = v.vitalsMainData.tvAvgVitalValue;
            StringBuilder sb3 = new StringBuilder();
            sb3.append((int) dailyStress.stress_avg);
            sb3.append(' ');
            textView3.setText(sb3.toString());
            v.vitalsMainData.tvVitalInfo.setText(w((int) dailyStress.stress_avg));
            TextView textView4 = v.vitalsMainData.tvAvgValueLevel;
            int i = (int) dailyStress.stress_avg;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            textView4.setText(PayUtils.getStressRange(i, requireContext));
            if (AppUtils.isNetConnected(getContext())) {
                if (Intrinsics.areEqual(RepositoryUtils.formatDate(this.o.getTime(), "yyyy-MM-dd"), RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"))) {
                    FragmentStressPeriodicViewModel fragmentStressPeriodicViewModel = this.n;
                    if (fragmentStressPeriodicViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        fragmentStressPeriodicViewModel = null;
                    }
                    fragmentStressPeriodicViewModel.getFeedbackQuestionnarieList();
                    return;
                }
                LinearLayout linearLayout = v().viewPagerLayout;
                Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.viewPagerLayout");
                gone(linearLayout);
                View view = v().divider2;
                Intrinsics.checkNotNullExpressionValue(view, "binding.divider2");
                gone(view);
                return;
            }
            Toast.makeText(requireContext(), getResources().getString(R.string.please_check_network), 0).show();
            return;
        }
        TextView textView5 = v().vitalsMainData.tvVitalMinValue;
        AppConstants appConstants = AppConstants.TWO_DASH_STRING;
        textView5.setText(String.valueOf(appConstants.getValue()));
        v().vitalsMainData.tvVitalMaxValue.setText(String.valueOf(appConstants.getValue()));
        v().vitalsMainData.tvAvgVitalValue.setText(String.valueOf(appConstants.getValue()));
        v().vitalsMainData.tvAvgValueLevel.setText("-");
        LinearLayout linearLayout2 = v().viewPagerLayout;
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "binding.viewPagerLayout");
        gone(linearLayout2);
        View view2 = v().divider2;
        Intrinsics.checkNotNullExpressionValue(view2, "binding.divider2");
        gone(view2);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicStressDashboard
    public void updateHourlyLevelData(@Nullable ArrayList<BarEntry> arrayList, @Nullable ArrayList<String> arrayList2) {
        if (!AppUtils.isEmpty(arrayList) && !AppUtils.isEmpty(arrayList2)) {
            boolean z = false;
            Intrinsics.checkNotNull(arrayList);
            Iterator<BarEntry> it = arrayList.iterator();
            while (it.hasNext()) {
                if (it.next().getY() > 0.0f) {
                    z = true;
                }
            }
            if (z) {
                v().tvEnableHrvSettings.setVisibility(8);
                Intrinsics.checkNotNull(arrayList2);
                L(arrayList, arrayList2);
                FragmentVitalPeriodicStressBinding v = v();
                ConstraintLayout legendLayout = v.legendLayout;
                Intrinsics.checkNotNullExpressionValue(legendLayout, "legendLayout");
                visible(legendLayout);
                RoundedBarChart stressGraph = v.stressGraph;
                Intrinsics.checkNotNullExpressionValue(stressGraph, "stressGraph");
                visible(stressGraph);
                TextView tvNoDataFound = v.tvNoDataFound;
                Intrinsics.checkNotNullExpressionValue(tvNoDataFound, "tvNoDataFound");
                gone(tvNoDataFound);
                TextView tvTipsToReduce = v.tvTipsToReduce;
                Intrinsics.checkNotNullExpressionValue(tvTipsToReduce, "tvTipsToReduce");
                visible(tvTipsToReduce);
                RecyclerView stressTipsRecycler = v.stressTipsRecycler;
                Intrinsics.checkNotNullExpressionValue(stressTipsRecycler, "stressTipsRecycler");
                visible(stressTipsRecycler);
                return;
            }
            Q();
            return;
        }
        Q();
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicStressDashboard
    public void updateRangeLevelData(@NotNull List<? extends DailyStress> dailyStressList) {
        Intrinsics.checkNotNullParameter(dailyStressList, "dailyStressList");
        if (!dailyStressList.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            Iterator<? extends DailyStress> it = dailyStressList.iterator();
            while (it.hasNext()) {
                DailyStress next = it.next();
                if ((next != null ? Integer.valueOf(next.stress_low) : null) != null) {
                    Integer valueOf = next != null ? Integer.valueOf(next.stress_low) : null;
                    Intrinsics.checkNotNull(valueOf);
                    if (valueOf.intValue() > 0) {
                        Integer valueOf2 = next != null ? Integer.valueOf(next.stress_low) : null;
                        Intrinsics.checkNotNull(valueOf2);
                        arrayList.add(valueOf2);
                    }
                }
                if ((next != null ? Integer.valueOf(next.stress_high) : null) != null) {
                    Integer valueOf3 = next != null ? Integer.valueOf(next.stress_high) : null;
                    Intrinsics.checkNotNull(valueOf3);
                    if (valueOf3.intValue() > 0) {
                        Integer valueOf4 = next != null ? Integer.valueOf(next.stress_high) : null;
                        Intrinsics.checkNotNull(valueOf4);
                        arrayList2.add(valueOf4);
                    }
                }
                if ((next != null ? Double.valueOf(next.stress_avg) : null) != null) {
                    Double valueOf5 = next != null ? Double.valueOf(next.stress_avg) : null;
                    Intrinsics.checkNotNull(valueOf5);
                    if (valueOf5.doubleValue() > 0.0d) {
                        Double valueOf6 = next != null ? Double.valueOf(next.stress_avg) : null;
                        Intrinsics.checkNotNull(valueOf6);
                        arrayList3.add(Integer.valueOf(kotlin.math.c.roundToInt(valueOf6.doubleValue())));
                    }
                }
            }
            if (arrayList3.isEmpty()) {
                TextView textView = v().vitalsMainData.tvVitalMinValue;
                AppConstants appConstants = AppConstants.TWO_DASH_STRING;
                textView.setText(String.valueOf(appConstants.getValue()));
                v().vitalsMainData.tvVitalMaxValue.setText(String.valueOf(appConstants.getValue()));
                v().vitalsMainData.tvAvgVitalValue.setText(String.valueOf(appConstants.getValue()));
                v().vitalsMainData.tvAvgValueLevel.setText("-");
                LinearLayout linearLayout = v().viewPagerLayout;
                Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.viewPagerLayout");
                gone(linearLayout);
                View view = v().divider2;
                Intrinsics.checkNotNullExpressionValue(view, "binding.divider2");
                gone(view);
                return;
            }
            FragmentVitalPeriodicStressBinding v = v();
            if (arrayList.size() > 0) {
                TextView textView2 = v.vitalsMainData.tvVitalMinValue;
                StringBuilder sb = new StringBuilder();
                sb.append(BleApiUtils.INSTANCE.getMinValueFromList(arrayList));
                sb.append(' ');
                textView2.setText(sb.toString());
            }
            if (arrayList2.size() > 0) {
                v.vitalsMainData.tvVitalMaxValue.setText(String.valueOf(BleApiUtils.INSTANCE.getMaxValue(arrayList2)));
            }
            if (arrayList3.size() > 0) {
                TextView textView3 = v.vitalsMainData.tvAvgVitalValue;
                StringBuilder sb2 = new StringBuilder();
                BleApiUtils bleApiUtils = BleApiUtils.INSTANCE;
                sb2.append(bleApiUtils.getAverageValue(arrayList3));
                sb2.append(' ');
                textView3.setText(sb2.toString());
                v.vitalsMainData.tvVitalInfo.setText(w(bleApiUtils.getAverageValue(arrayList3)));
                TextView textView4 = v.vitalsMainData.tvAvgValueLevel;
                int averageValue = bleApiUtils.getAverageValue(arrayList3);
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                textView4.setText(PayUtils.getStressRange(averageValue, requireContext));
                return;
            }
            return;
        }
        TextView textView5 = v().vitalsMainData.tvVitalMinValue;
        AppConstants appConstants2 = AppConstants.TWO_DASH_STRING;
        textView5.setText(String.valueOf(appConstants2.getValue()));
        v().vitalsMainData.tvVitalMaxValue.setText(String.valueOf(appConstants2.getValue()));
        v().vitalsMainData.tvAvgVitalValue.setText(String.valueOf(appConstants2.getValue()));
        v().vitalsMainData.tvAvgValueLevel.setText("-");
        LinearLayout linearLayout2 = v().viewPagerLayout;
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "binding.viewPagerLayout");
        gone(linearLayout2);
        View view2 = v().divider2;
        Intrinsics.checkNotNullExpressionValue(view2, "binding.divider2");
        gone(view2);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicStressDashboard
    public void updateStressPercentage(@Nullable StressPercentage stressPercentage) {
    }

    public final FragmentVitalPeriodicStressBinding v() {
        FragmentVitalPeriodicStressBinding fragmentVitalPeriodicStressBinding = this.m;
        Intrinsics.checkNotNull(fragmentVitalPeriodicStressBinding);
        return fragmentVitalPeriodicStressBinding;
    }

    public final String w(int i) {
        boolean z = true;
        if (1 <= i && i < 26) {
            String string = getString(R.string.stress_dynamic_txt_relaxed);
            Intrinsics.checkNotNullExpressionValue(string, "{\n                getStr…xt_relaxed)\n            }");
            return string;
        }
        if (26 <= i && i < 51) {
            String string2 = getString(R.string.stress_dynamic_txt_mild);
            Intrinsics.checkNotNullExpressionValue(string2, "{\n                getStr…c_txt_mild)\n            }");
            return string2;
        }
        if (51 <= i && i < 76) {
            String string3 = getString(R.string.stress_dynamic_txt_moderate);
            Intrinsics.checkNotNullExpressionValue(string3, "{\n                getStr…t_moderate)\n            }");
            return string3;
        }
        if (75 > i || i >= 101) {
            z = false;
        }
        if (z) {
            String string4 = getString(R.string.stress_dynamic_txt_high);
            Intrinsics.checkNotNullExpressionValue(string4, "{\n                getStr…c_txt_high)\n            }");
            return string4;
        }
        return "";
    }

    public final List<TipsModel> x() {
        ArrayList arrayList = new ArrayList();
        int length = this.w.length;
        for (int i = 0; i < length; i++) {
            int i2 = this.v[i];
            String string = getString(this.w[i]);
            Intrinsics.checkNotNullExpressionValue(string, "getString(description[i])");
            arrayList.add(new TipsModel(i2, string));
        }
        return arrayList;
    }

    public final void y(Calendar calendar, Calendar calendar2) {
        if (isAdded()) {
            if (DateUtils.isToday(calendar2.getTimeInMillis())) {
                v().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                v().vitalsMainData.ibForward.setEnabled(false);
            } else {
                v().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                v().vitalsMainData.ibForward.setEnabled(true);
            }
            TextView textView = v().vitalsMainData.tvSelectedTypeValue;
            StringBuilder sb = new StringBuilder();
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            sb.append(themesUtils.getDateFormatDDMMMYYYY(calendar));
            sb.append(" - ");
            sb.append(themesUtils.getDateFormatDDMMMYYYY(calendar2));
            textView.setText(sb.toString());
            FragmentStressPeriodicViewModel fragmentStressPeriodicViewModel = this.n;
            if (fragmentStressPeriodicViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentStressPeriodicViewModel = null;
            }
            fragmentStressPeriodicViewModel.selectWeeklyView(calendar, calendar2);
        }
    }

    public final void z(Calendar calendar) {
        if (isAdded()) {
            TextView textView = v().vitalsMainData.tvSelectedTypeValue;
            long timeInMillis = calendar.getTimeInMillis();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            textView.setText(PayUtils.getTodayYesterdayStringFromTimeStamp(timeInMillis, requireContext));
            if (DateUtils.isToday(calendar.getTimeInMillis())) {
                v().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                v().vitalsMainData.ibForward.setEnabled(false);
            } else {
                v().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                v().vitalsMainData.ibForward.setEnabled(true);
            }
            FragmentStressPeriodicViewModel fragmentStressPeriodicViewModel = this.n;
            FragmentStressPeriodicViewModel fragmentStressPeriodicViewModel2 = null;
            if (fragmentStressPeriodicViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentStressPeriodicViewModel = null;
            }
            fragmentStressPeriodicViewModel.loadHourlyStressData(calendar);
            FragmentStressPeriodicViewModel fragmentStressPeriodicViewModel3 = this.n;
            if (fragmentStressPeriodicViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                fragmentStressPeriodicViewModel2 = fragmentStressPeriodicViewModel3;
            }
            fragmentStressPeriodicViewModel2.loadDailyData(calendar);
            this.o = calendar;
        }
    }
}
