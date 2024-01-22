package com.coveiot.android.respiratoryrate.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.respiratoryrate.R;
import com.coveiot.android.respiratoryrate.activities.ActivityRespiratoryRateShare;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity;
import com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateData;
import com.coveiot.android.respiratoryrate.databinding.FragmentVitalRespiratoryRateBinding;
import com.coveiot.android.respiratoryrate.listener.ContractRespiratoryRateDashBoard;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateListBean;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateShareData;
import com.coveiot.android.respiratoryrate.utils.Constants;
import com.coveiot.android.respiratoryrate.utils.Utils;
import com.coveiot.android.respiratoryrate.utils.ViewModelFactory;
import com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarRangeDialog;
import com.coveiot.android.theme.compundview.CustomMarkerViewVitals;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class VitalRespiratoryRateFragment extends BaseFragment implements ContractRespiratoryRateDashBoard, CalendarRangeDialog.OnCalendarRangeSelector {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public int A;
    public int B;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public DatePickerDialog datePickerDialog;
    @Nullable
    public FragmentVitalRespiratoryRateBinding m;
    public RespiratoryRateViewModel n;
    @NotNull
    public Calendar o;
    public boolean p;
    public boolean q;
    public int r;
    public final int s;
    public SimpleDateFormat simpleDateFormat;
    public final int t;
    public final int u;
    public Pair<? extends Calendar, ? extends Calendar> v;
    @Nullable
    public DailyRespiratoryRateEntity w;
    public boolean x;
    public CalendarRangeDialog y;
    @Nullable
    public Calendar z;

    /* loaded from: classes6.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ VitalRespiratoryRateFragment newInstance$default(Companion companion, Calendar calendar, int i, Object obj) {
            if ((i & 1) != 0) {
                calendar = null;
            }
            return companion.newInstance(calendar);
        }

        @NotNull
        public final VitalRespiratoryRateFragment newInstance(@Nullable Calendar calendar) {
            VitalRespiratoryRateFragment vitalRespiratoryRateFragment = new VitalRespiratoryRateFragment();
            if (calendar != null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("calender", calendar);
                vitalRespiratoryRateFragment.setArguments(bundle);
            }
            return vitalRespiratoryRateFragment;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.respiratoryrate.fragments.VitalRespiratoryRateFragment$updateDailyLevelData$1", f = "VitalRespiratoryRateFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes6.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ DailyRespiratoryRateEntity $dailyRespiratoryRateEntity;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(DailyRespiratoryRateEntity dailyRespiratoryRateEntity, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$dailyRespiratoryRateEntity = dailyRespiratoryRateEntity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$dailyRespiratoryRateEntity, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (VitalRespiratoryRateFragment.this.m != null) {
                    VitalRespiratoryRateFragment.this.w = this.$dailyRespiratoryRateEntity;
                    TextView textView = VitalRespiratoryRateFragment.this.u().vitalsMainData.tvVitalMinValue;
                    Constants constants = Constants.EMPTY_RESPIRATORY_RATE;
                    textView.setText(String.valueOf(constants.getValue()));
                    VitalRespiratoryRateFragment.this.u().vitalsMainData.tvVitalMaxValue.setText(String.valueOf(constants.getValue()));
                    DailyRespiratoryRateEntity dailyRespiratoryRateEntity = this.$dailyRespiratoryRateEntity;
                    if (dailyRespiratoryRateEntity != null) {
                        VitalRespiratoryRateFragment vitalRespiratoryRateFragment = VitalRespiratoryRateFragment.this;
                        RespiratoryRateData respiratoryRateData = dailyRespiratoryRateEntity.data;
                        if (respiratoryRateData != null) {
                            if (respiratoryRateData.getMin() != null) {
                                Integer min = respiratoryRateData.getMin();
                                Intrinsics.checkNotNull(min);
                                if (min.intValue() > 0 && respiratoryRateData.getMax() != null) {
                                    Integer max = respiratoryRateData.getMax();
                                    Intrinsics.checkNotNull(max);
                                    if (max.intValue() > 0) {
                                        Integer min2 = respiratoryRateData.getMin();
                                        Intrinsics.checkNotNull(min2);
                                        vitalRespiratoryRateFragment.A = min2.intValue();
                                        Integer max2 = respiratoryRateData.getMax();
                                        Intrinsics.checkNotNull(max2);
                                        vitalRespiratoryRateFragment.B = max2.intValue();
                                        TextView textView2 = vitalRespiratoryRateFragment.u().vitalsMainData.tvVitalMinValue;
                                        StringBuilder sb = new StringBuilder();
                                        sb.append(respiratoryRateData.getMin());
                                        sb.append(' ');
                                        Context requireContext = vitalRespiratoryRateFragment.requireContext();
                                        int i = R.string.brpm;
                                        sb.append(requireContext.getString(i));
                                        textView2.setText(sb.toString());
                                        TextView textView3 = vitalRespiratoryRateFragment.u().vitalsMainData.tvVitalMaxValue;
                                        textView3.setText(respiratoryRateData.getMax() + ' ' + vitalRespiratoryRateFragment.requireContext().getString(i));
                                        SpannableString spannableString = new SpannableString(vitalRespiratoryRateFragment.u().vitalsMainData.tvVitalMaxValue.getText().toString());
                                        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(0.5f);
                                        String obj2 = vitalRespiratoryRateFragment.u().vitalsMainData.tvVitalMaxValue.getText().toString();
                                        String string = vitalRespiratoryRateFragment.getString(i);
                                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.brpm)");
                                        spannableString.setSpan(relativeSizeSpan, StringsKt__StringsKt.indexOf$default((CharSequence) obj2, string, 0, false, 6, (Object) null), vitalRespiratoryRateFragment.u().vitalsMainData.tvVitalMaxValue.getText().toString().length(), 0);
                                        vitalRespiratoryRateFragment.u().vitalsMainData.tvVitalMaxValue.setText(spannableString);
                                        SpannableString spannableString2 = new SpannableString(vitalRespiratoryRateFragment.u().vitalsMainData.tvVitalMinValue.getText().toString());
                                        RelativeSizeSpan relativeSizeSpan2 = new RelativeSizeSpan(0.5f);
                                        String obj3 = vitalRespiratoryRateFragment.u().vitalsMainData.tvVitalMinValue.getText().toString();
                                        String string2 = vitalRespiratoryRateFragment.getString(i);
                                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.brpm)");
                                        spannableString2.setSpan(relativeSizeSpan2, StringsKt__StringsKt.indexOf$default((CharSequence) obj3, string2, 0, false, 6, (Object) null), vitalRespiratoryRateFragment.u().vitalsMainData.tvVitalMinValue.getText().toString().length(), 0);
                                        vitalRespiratoryRateFragment.u().vitalsMainData.tvVitalMinValue.setText(spannableString2);
                                    }
                                }
                            }
                            vitalRespiratoryRateFragment.u().vitalsMainData.tvVitalMinValue.setText(String.valueOf(constants.getValue()));
                            vitalRespiratoryRateFragment.u().vitalsMainData.tvVitalMaxValue.setText(String.valueOf(constants.getValue()));
                            vitalRespiratoryRateFragment.A = 0;
                            vitalRespiratoryRateFragment.B = 0;
                        }
                    }
                    VitalRespiratoryRateFragment.this.N();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public VitalRespiratoryRateFragment() {
        AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this.o = calendar;
        this.t = 1;
        this.u = 2;
    }

    public static final void A(VitalRespiratoryRateFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.r;
        if (i == this$0.s) {
            this$0.H();
            this$0.getDatePickerDialog().getDatePicker().setMaxDate(System.currentTimeMillis());
            this$0.getDatePickerDialog().show();
        } else if (i == this$0.t) {
            this$0.x = true;
            this$0.L();
        } else {
            this$0.x = false;
            this$0.L();
        }
    }

    public static final void B(VitalRespiratoryRateFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.r;
        if (i == this$0.s) {
            this$0.w(Utils.INSTANCE.getPreviousDayCalendar(this$0.o));
            return;
        }
        Pair<? extends Calendar, ? extends Calendar> pair = null;
        if (i == this$0.t) {
            if (this$0.v == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            }
            Pair<? extends Calendar, ? extends Calendar> pair2 = this$0.v;
            if (pair2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair2 = null;
            }
            Object clone = pair2.getFirst().clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar = (Calendar) clone;
            calendar.add(5, -6);
            Pair<? extends Calendar, ? extends Calendar> pair3 = this$0.v;
            if (pair3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair3;
            }
            Object clone2 = pair.getFirst().clone();
            Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar2 = (Calendar) clone2;
            this$0.v = new Pair<>(calendar, calendar2);
            this$0.v(calendar, calendar2);
            this$0.M();
        } else if (i == this$0.u) {
            Pair<? extends Calendar, ? extends Calendar> pair4 = this$0.v;
            if (pair4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair4 = null;
            }
            Object clone3 = pair4.getFirst().clone();
            Intrinsics.checkNotNull(clone3, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar3 = (Calendar) clone3;
            calendar3.add(5, -30);
            Pair<? extends Calendar, ? extends Calendar> pair5 = this$0.v;
            if (pair5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair5;
            }
            Object clone4 = pair.getFirst().clone();
            Intrinsics.checkNotNull(clone4, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar4 = (Calendar) clone4;
            this$0.v = new Pair<>(calendar3, calendar4);
            this$0.v(calendar3, calendar4);
            this$0.M();
        }
    }

    public static final void C(VitalRespiratoryRateFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.r;
        if (i == this$0.s) {
            if (DateUtils.isToday(this$0.o.getTimeInMillis())) {
                return;
            }
            this$0.w(Utils.INSTANCE.getNextDayCalendar(this$0.o));
            return;
        }
        Pair<? extends Calendar, ? extends Calendar> pair = null;
        if (i == this$0.t) {
            Pair<? extends Calendar, ? extends Calendar> pair2 = this$0.v;
            if (pair2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair2 = null;
            }
            if (DateUtils.isToday(pair2.getSecond().getTimeInMillis())) {
                return;
            }
            Pair<? extends Calendar, ? extends Calendar> pair3 = this$0.v;
            if (pair3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair3 = null;
            }
            Object clone = pair3.getSecond().clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar = (Calendar) clone;
            Pair<? extends Calendar, ? extends Calendar> pair4 = this$0.v;
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
                this$0.v = new Pair<>(calendar, calendar2);
                this$0.v(calendar, calendar2);
                this$0.M();
                return;
            }
            int findDateDifference = AppUtils.findDateDifference(Calendar.getInstance().getTime(), calendar2.getTime());
            Object clone3 = calendar.clone();
            Intrinsics.checkNotNull(clone3, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar3 = (Calendar) clone3;
            calendar3.add(5, 6 - findDateDifference);
            this$0.v = new Pair<>(calendar, calendar3);
            this$0.v(calendar, calendar3);
            this$0.M();
        } else if (i == this$0.u) {
            Pair<? extends Calendar, ? extends Calendar> pair5 = this$0.v;
            if (pair5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair5 = null;
            }
            if (DateUtils.isToday(pair5.getSecond().getTimeInMillis())) {
                return;
            }
            Pair<? extends Calendar, ? extends Calendar> pair6 = this$0.v;
            if (pair6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair6 = null;
            }
            Object clone4 = pair6.getSecond().clone();
            Intrinsics.checkNotNull(clone4, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar4 = (Calendar) clone4;
            Pair<? extends Calendar, ? extends Calendar> pair7 = this$0.v;
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
                this$0.v = new Pair<>(calendar4, calendar5);
                this$0.v(calendar4, calendar5);
                this$0.M();
                return;
            }
            int findDateDifference2 = AppUtils.findDateDifference(Calendar.getInstance().getTime(), calendar5.getTime());
            Object clone6 = calendar4.clone();
            Intrinsics.checkNotNull(clone6, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar6 = (Calendar) clone6;
            calendar6.add(5, 30 - findDateDifference2);
            this$0.v = new Pair<>(calendar4, calendar6);
            this$0.v(calendar4, calendar6);
            this$0.M();
        }
    }

    public static final void D(VitalRespiratoryRateFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.share();
    }

    public static final void E(VitalRespiratoryRateFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.r = this$0.s;
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this$0.w(calendar);
        this$0.M();
        this$0.setCandleChartVisible(false);
        this$0.setLineGraphVisible(true);
    }

    public static final void F(VitalRespiratoryRateFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.r = this$0.t;
        Calendar startDate = Calendar.getInstance();
        startDate.add(5, -6);
        Calendar endDate = Calendar.getInstance();
        this$0.M();
        this$0.v = new Pair<>(startDate, endDate);
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        Intrinsics.checkNotNullExpressionValue(endDate, "endDate");
        this$0.v(startDate, endDate);
    }

    public static final void G(VitalRespiratoryRateFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.r = this$0.u;
        Calendar startDate = Calendar.getInstance();
        startDate.add(5, -30);
        Calendar endDate = Calendar.getInstance();
        this$0.M();
        this$0.v = new Pair<>(startDate, endDate);
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        Intrinsics.checkNotNullExpressionValue(endDate, "endDate");
        this$0.v(startDate, endDate);
    }

    public static final void I(VitalRespiratoryRateFragment this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i, i2, i3);
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        this$0.w(newDate);
    }

    public static final void x(final FragmentVitalRespiratoryRateBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this_apply.nestedScrollViewNBR.post(new Runnable() { // from class: com.coveiot.android.respiratoryrate.fragments.l
            @Override // java.lang.Runnable
            public final void run() {
                VitalRespiratoryRateFragment.y(FragmentVitalRespiratoryRateBinding.this);
            }
        });
    }

    public static final void y(FragmentVitalRespiratoryRateBinding this_apply) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this_apply.nestedScrollViewNBR.fullScroll(130);
    }

    public final void H() {
        Calendar calendar = this.o;
        setDatePickerDialog(new DatePickerDialog(requireActivity(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.respiratoryrate.fragments.c
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                VitalRespiratoryRateFragment.I(VitalRespiratoryRateFragment.this, datePicker, i, i2, i3);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)));
    }

    public final void J(ArrayList<CandleEntry> arrayList, final ArrayList<String> arrayList2) {
        TextView textView = u().tvNoDataFound;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvNoDataFound");
        gone(textView);
        u().candleChartRespiratoryRate.clear();
        CandleDataSet candleDataSet = new CandleDataSet(arrayList, "");
        Resources resources = getResources();
        int i = R.color.secondary_text_color;
        candleDataSet.setColor(resources.getColor(i));
        candleDataSet.setShadowColor(getResources().getColor(17170445));
        candleDataSet.setShadowWidth(0.0f);
        candleDataSet.setDrawIcons(false);
        candleDataSet.setDrawValues(true);
        Resources resources2 = getResources();
        int i2 = R.color.steps_graph_color;
        candleDataSet.setIncreasingColor(resources2.getColor(i2));
        candleDataSet.setIncreasingPaintStyle(Paint.Style.FILL);
        candleDataSet.setNeutralColor(getResources().getColor(i2));
        candleDataSet.setValueTextColor(getResources().getColor(i));
        candleDataSet.setBarSpace(0.1f);
        candleDataSet.setHighlightLineWidth(0.0f);
        candleDataSet.setDrawHorizontalHighlightIndicator(false);
        candleDataSet.setHighlightEnabled(true);
        candleDataSet.setDrawVerticalHighlightIndicator(false);
        XAxis xAxis = u().candleChartRespiratoryRate.getXAxis();
        xAxis.setTextColor(getResources().getColor(i));
        xAxis.setDrawGridLines(false);
        YAxis axisLeft = u().candleChartRespiratoryRate.getAxisLeft();
        axisLeft.setTextColor(getResources().getColor(i));
        axisLeft.setDrawGridLines(false);
        axisLeft.setAxisMinimum(1.0f);
        axisLeft.setEnabled(true);
        axisLeft.setAxisMaximum(30.0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() { // from class: com.coveiot.android.respiratoryrate.fragments.VitalRespiratoryRateFragment$setDayCandleChartValues$1
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
        u().candleChartRespiratoryRate.getLegend().setEnabled(false);
        u().candleChartRespiratoryRate.setData(candleData);
        Context context = getContext();
        int i3 = R.layout.custom_marker_view_steps_hr;
        String string = getString(R.string.nightly_breathing_rate);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.nightly_breathing_rate)");
        CustomMarkerViewVitals customMarkerViewVitals = new CustomMarkerViewVitals(context, i3, string, this.r, arrayList2);
        customMarkerViewVitals.setChartView(u().candleChartRespiratoryRate);
        u().candleChartRespiratoryRate.setMarker(customMarkerViewVitals);
        u().candleChartRespiratoryRate.getDescription().setEnabled(false);
        u().candleChartRespiratoryRate.setDrawGridBackground(false);
        u().candleChartRespiratoryRate.setDrawBorders(false);
        u().candleChartRespiratoryRate.setVisibleXRangeMaximum(24.0f);
        u().candleChartRespiratoryRate.setVisibleXRangeMinimum(7.0f);
        u().candleChartRespiratoryRate.setPinchZoom(false);
        u().candleChartRespiratoryRate.setAutoScaleMinMaxEnabled(false);
        u().candleChartRespiratoryRate.setScaleEnabled(false);
        u().candleChartRespiratoryRate.getAxisRight().setEnabled(false);
        u().candleChartRespiratoryRate.invalidate();
        u().candleChartRespiratoryRate.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.coveiot.android.respiratoryrate.fragments.VitalRespiratoryRateFragment$setDayCandleChartValues$2
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

    public final void K(ArrayList<Entry> arrayList, ArrayList<String> arrayList2) {
        TextView textView = u().tvNoDataFound;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvNoDataFound");
        gone(textView);
        u().lineChartRespiratoryRate.clear();
        ArrayList arrayList3 = new ArrayList();
        Intrinsics.checkNotNull(arrayList);
        Iterator<Entry> it = arrayList.iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            if (next.getY() <= 0.0f) {
                arrayList3.add(next);
            }
        }
        arrayList.removeAll(arrayList3);
        LineDataSet lineDataSet = new LineDataSet(arrayList, "");
        lineDataSet.setCircleColor(getResources().getColor(R.color.white));
        lineDataSet.setCircleSize(0.5f);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        lineDataSet.setColor(getResources().getColor(R.color.steps_graph_color));
        lineDataSet.setHighLightColor(getResources().getColor(17170445));
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillDrawable(getResources().getDrawable(R.drawable.line_chart_background_gradiant_bg));
        if (lineDataSet.getEntryCount() > 0) {
            int entryCount = lineDataSet.getEntryCount();
            int[] iArr = new int[entryCount];
            for (int i = 0; i < entryCount; i++) {
                iArr[i] = getResources().getColor(R.color.steps_graph_color);
            }
            iArr[entryCount - 1] = getResources().getColor(R.color.steps_graph_color);
            lineDataSet.setColors(Arrays.copyOf(iArr, entryCount));
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setCircleColors(Arrays.copyOf(iArr, entryCount));
        }
        LineDataSet lineDataSet2 = new LineDataSet(arrayList3, "");
        lineDataSet2.setDrawValues(false);
        lineDataSet2.setDrawCircles(false);
        Resources resources = getResources();
        int i2 = R.color.transparent;
        lineDataSet2.setValueTextColor(resources.getColor(i2));
        lineDataSet2.setColor(getResources().getColor(i2));
        lineDataSet2.setCircleColor(getResources().getColor(i2));
        lineDataSet2.setHighLightColor(getResources().getColor(17170445));
        if (lineDataSet2.getEntryCount() > 0) {
            int entryCount2 = lineDataSet2.getEntryCount();
            int[] iArr2 = new int[entryCount2];
            for (int i3 = 0; i3 < entryCount2; i3++) {
                iArr2[i3] = getResources().getColor(17170445);
            }
            iArr2[entryCount2 - 1] = getResources().getColor(17170445);
            lineDataSet2.setColors(Arrays.copyOf(iArr2, entryCount2));
            lineDataSet2.setCircleColors(Arrays.copyOf(iArr2, entryCount2));
        }
        lineDataSet2.setCircleRadius(0.0f);
        u().lineChartRespiratoryRate.getAxisLeft().setEnabled(true);
        u().lineChartRespiratoryRate.getAxisLeft().setDrawAxisLine(true);
        u().lineChartRespiratoryRate.getAxisLeft().setDrawGridLines(false);
        u().lineChartRespiratoryRate.getAxisLeft().setAxisMaximum(40.0f);
        u().lineChartRespiratoryRate.getAxisLeft().setAxisLineWidth(1.0f);
        YAxis axisLeft = u().lineChartRespiratoryRate.getAxisLeft();
        Resources resources2 = getResources();
        int i4 = R.color.secondary_text_color;
        axisLeft.setAxisLineColor(resources2.getColor(i4));
        LineData lineData = new LineData(lineDataSet, lineDataSet2);
        u().lineChartRespiratoryRate.getXAxis().setValueFormatter(new IndexAxisValueFormatter(arrayList2));
        lineData.setDrawValues(false);
        u().lineChartRespiratoryRate.setData(lineData);
        u().lineChartRespiratoryRate.setDrawGridBackground(false);
        u().lineChartRespiratoryRate.getDescription().setEnabled(false);
        lineData.setValueTextColor(getResources().getColor(i4));
        u().lineChartRespiratoryRate.getPaint(7).setColor(getResources().getColor(i4));
        u().lineChartRespiratoryRate.setDrawBorders(false);
        u().lineChartRespiratoryRate.setAutoScaleMinMaxEnabled(false);
        u().lineChartRespiratoryRate.setPinchZoom(false);
        u().lineChartRespiratoryRate.getAxisRight().setEnabled(false);
        XAxis xAxis = u().lineChartRespiratoryRate.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineWidth(1.0f);
        xAxis.setAxisLineColor(getResources().getColor(i4));
        u().lineChartRespiratoryRate.getAxisLeft().setStartAtZero(true);
        u().lineChartRespiratoryRate.getAxisRight().setStartAtZero(true);
        u().lineChartRespiratoryRate.getAxisLeft().setTextColor(getResources().getColor(i4));
        u().lineChartRespiratoryRate.getXAxis().setTextColor(getResources().getColor(i4));
        u().lineChartRespiratoryRate.getLegend().setTextColor(getResources().getColor(i4));
        u().lineChartRespiratoryRate.getLegend().setEnabled(false);
        u().lineChartRespiratoryRate.setVisibleXRangeMaximum(24.0f);
        u().lineChartRespiratoryRate.setVisibleXRangeMinimum(7.0f);
        Context context = getContext();
        int i5 = R.layout.custom_marker_view_steps_hr;
        String string = getString(R.string.nightly_breathing_rate);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.nightly_breathing_rate)");
        CustomMarkerViewVitals customMarkerViewVitals = new CustomMarkerViewVitals(context, i5, string, this.r, arrayList2);
        customMarkerViewVitals.setChartView(u().lineChartRespiratoryRate);
        u().lineChartRespiratoryRate.setMarker(customMarkerViewVitals);
        u().lineChartRespiratoryRate.setPinchZoom(false);
        u().lineChartRespiratoryRate.setAutoScaleMinMaxEnabled(false);
        u().lineChartRespiratoryRate.setScaleEnabled(false);
        u().lineChartRespiratoryRate.setDoubleTapToZoomEnabled(false);
        u().lineChartRespiratoryRate.invalidate();
    }

    public final void L() {
        if (isAdded()) {
            boolean z = this.x;
            Pair<? extends Calendar, ? extends Calendar> pair = this.v;
            if (pair == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair = null;
            }
            CalendarRangeDialog calendarRangeDialog = new CalendarRangeDialog(z, pair, this);
            this.y = calendarRangeDialog;
            calendarRangeDialog.show(getChildFragmentManager(), "");
        }
    }

    public final void M() {
        if (isAdded()) {
            int i = this.r;
            if (i == this.s) {
                u().vitalsMainData.tvDay.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                u().vitalsMainData.tvDay.setTextColor(requireContext().getColor(R.color.main_text_color));
                u().vitalsMainData.tvWeek.setBackground(null);
                TextView textView = u().vitalsMainData.tvWeek;
                Context requireContext = requireContext();
                int i2 = R.color.secondary_text_color;
                textView.setTextColor(requireContext.getColor(i2));
                u().vitalsMainData.tvMonth.setBackground(null);
                u().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(i2));
                u().vitalsMainData.tvDay.setTextAppearance(R.style.bold);
                TextView textView2 = u().vitalsMainData.tvWeek;
                int i3 = R.style.regular;
                textView2.setTextAppearance(i3);
                u().vitalsMainData.tvMonth.setTextAppearance(i3);
            } else if (i == this.t) {
                u().vitalsMainData.tvDay.setBackground(null);
                TextView textView3 = u().vitalsMainData.tvDay;
                Context requireContext2 = requireContext();
                int i4 = R.color.secondary_text_color;
                textView3.setTextColor(requireContext2.getColor(i4));
                u().vitalsMainData.tvWeek.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                u().vitalsMainData.tvWeek.setTextColor(requireContext().getColor(R.color.main_text_color));
                u().vitalsMainData.tvMonth.setBackground(null);
                u().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(i4));
                TextView textView4 = u().vitalsMainData.tvDay;
                int i5 = R.style.regular;
                textView4.setTextAppearance(i5);
                u().vitalsMainData.tvWeek.setTextAppearance(R.style.bold);
                u().vitalsMainData.tvMonth.setTextAppearance(i5);
            } else {
                u().vitalsMainData.tvDay.setBackground(null);
                TextView textView5 = u().vitalsMainData.tvDay;
                Context requireContext3 = requireContext();
                int i6 = R.color.secondary_text_color;
                textView5.setTextColor(requireContext3.getColor(i6));
                u().vitalsMainData.tvWeek.setBackground(null);
                u().vitalsMainData.tvWeek.setTextColor(requireContext().getColor(i6));
                u().vitalsMainData.tvMonth.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                u().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(R.color.main_text_color));
                TextView textView6 = u().vitalsMainData.tvDay;
                int i7 = R.style.regular;
                textView6.setTextAppearance(i7);
                u().vitalsMainData.tvWeek.setTextAppearance(i7);
                u().vitalsMainData.tvMonth.setTextAppearance(R.style.bold);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x010c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void N() {
        /*
            Method dump skipped, instructions count: 579
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.respiratoryrate.fragments.VitalRespiratoryRateFragment.N():void");
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
        this.p = UserDataManager.getInstance(getContext()).isRespiratoryRateFeatureEnabled(requireActivity());
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
        Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"dd MMMM yyyy\")");
        setSimpleDateFormat(simpleDateFormat);
        Long lastPPGSyncTimestamp = UserDataManager.getInstance(getContext()).getLastPPGSyncTimestamp(BleApiManager.getInstance(getContext()).getBleApi().getMacAddress());
        Intrinsics.checkNotNullExpressionValue(lastPPGSyncTimestamp, "getInstance(context)\n   …).bleApi.getMacAddress())");
        this.q = DateUtils.isToday(lastPPGSyncTimestamp.longValue());
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(RespiratoryRateViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…ateViewModel::class.java)");
        RespiratoryRateViewModel respiratoryRateViewModel = (RespiratoryRateViewModel) viewModel;
        this.n = respiratoryRateViewModel;
        if (respiratoryRateViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            respiratoryRateViewModel = null;
        }
        respiratoryRateViewModel.setContractRespiratoryRateDashboard(this);
        RespiratoryRateViewModel respiratoryRateViewModel2 = this.n;
        if (respiratoryRateViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            respiratoryRateViewModel2 = null;
        }
        respiratoryRateViewModel2.setMLifecycleOwner(this);
        final FragmentVitalRespiratoryRateBinding u = u();
        TextView textView = u.vitalsMainData.tvVitalInfo;
        Intrinsics.checkNotNullExpressionValue(textView, "vitalsMainData.tvVitalInfo");
        gone(textView);
        ConstraintLayout constraintLayout = u.vitalsMainData.clCenterDataStepsSleep;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "vitalsMainData.clCenterDataStepsSleep");
        visible(constraintLayout);
        u.vitalsMainData.tvVitalMax.setText(getString(R.string.max));
        u.vitalsMainData.tvVitalMin.setText(getString(R.string.min));
        u.vitalsMainData.tvVitalName.setText(getString(R.string.nightly_breathing_rate));
        u.vitalsMainData.ivMin.setImageResource(R.drawable.ic_respiratory_rate_min);
        u.vitalsMainData.ivMax.setImageResource(R.drawable.ic_respiratory_rate_max);
        u.vitalsMainData.ivCenterVital.setImageResource(R.drawable.ic_nightly_breathing_rate);
        TextView textView2 = u.vitalsMainData.tvOutOfStepsSleepValue;
        Intrinsics.checkNotNullExpressionValue(textView2, "vitalsMainData.tvOutOfStepsSleepValue");
        gone(textView2);
        TextView textView3 = u.vitalsMainData.tvAvgStepsSleepValue;
        Intrinsics.checkNotNullExpressionValue(textView3, "vitalsMainData.tvAvgStepsSleepValue");
        gone(textView3);
        u.vitalsMainData.tvVitalName.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.fragments.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalRespiratoryRateFragment.x(FragmentVitalRespiratoryRateBinding.this, view);
            }
        });
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        TextView tvHRdescription = u.tvHRdescription;
        Intrinsics.checkNotNullExpressionValue(tvHRdescription, "tvHRdescription");
        themesUtils.makeTextViewResizable(tvHRdescription, 3, "... Read More", true);
        TextView textView4 = u.vitalsMainData.tvLastSyncTime;
        Utils utils = Utils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        textView4.setText(utils.getLastSyncTime(requireContext2));
        String string = getString(R.string.medical_disclaimer_info);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.medical_disclaimer_info)");
        SpannableString spannableString = new SpannableString(string);
        Context context = getContext();
        Integer valueOf = context != null ? Integer.valueOf(context.getColor(R.color.color_b3b3b3)) : null;
        Intrinsics.checkNotNull(valueOf);
        spannableString.setSpan(new ForegroundColorSpan(valueOf.intValue()), 0, 12, 18);
        u.tvdisclaimer.setText(spannableString);
        z();
        Calendar calendar = this.z;
        if (calendar != null) {
            Intrinsics.checkNotNull(calendar);
            w(calendar);
            return;
        }
        Calendar calendar2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
        w(calendar2);
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarRangeDialog.OnCalendarRangeSelector
    public void onCalendarRangeSelected(@NotNull Calendar fromDate, @NotNull Calendar toDate) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        this.v = new Pair<>(fromDate, toDate);
        v(fromDate, toDate);
    }

    @Override // com.coveiot.android.respiratoryrate.listener.ContractRespiratoryRateDashBoard
    public void onCandleChartDataLoaded(@Nullable ArrayList<CandleEntry> arrayList, @Nullable ArrayList<String> arrayList2) {
        if (!AppUtils.isEmpty(arrayList) && !AppUtils.isEmpty(arrayList2)) {
            setCandleChartVisible(true);
            if (arrayList2 != null) {
                Intrinsics.checkNotNull(arrayList);
                J(arrayList, arrayList2);
                return;
            }
            return;
        }
        setCandleChartVisible(false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            if ((arguments != null ? arguments.getSerializable("calender") : null) != null) {
                Bundle arguments2 = getArguments();
                Serializable serializable = arguments2 != null ? arguments2.getSerializable("calender") : null;
                Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type java.util.Calendar");
                this.z = (Calendar) serializable;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentVitalRespiratoryRateBinding.inflate(inflater, viewGroup, false);
        View root = u().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Long lastPPGSyncTimestamp = UserDataManager.getInstance(getContext()).getLastPPGSyncTimestamp(BleApiManager.getInstance(getContext()).getBleApi().getMacAddress());
        Intrinsics.checkNotNullExpressionValue(lastPPGSyncTimestamp, "getInstance(context).get…acAddress()\n            )");
        this.q = DateUtils.isToday(lastPPGSyncTimestamp.longValue());
        if (UserDataManager.getInstance(getContext()).isRespiratoryRateFeatureEnabled(requireActivity()) != this.p) {
            this.p = UserDataManager.getInstance(getContext()).isRespiratoryRateFeatureEnabled(requireActivity());
            if (this.r == this.s) {
                N();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initData();
    }

    public final void setCandleChartVisible(boolean z) {
        if (z) {
            CandleStickChart candleStickChart = u().candleChartRespiratoryRate;
            Intrinsics.checkNotNullExpressionValue(candleStickChart, "binding.candleChartRespiratoryRate");
            visible(candleStickChart);
            LineChart lineChart = u().lineChartRespiratoryRate;
            Intrinsics.checkNotNullExpressionValue(lineChart, "binding.lineChartRespiratoryRate");
            gone(lineChart);
            return;
        }
        CandleStickChart candleStickChart2 = u().candleChartRespiratoryRate;
        Intrinsics.checkNotNullExpressionValue(candleStickChart2, "binding.candleChartRespiratoryRate");
        gone(candleStickChart2);
        u().candleChartRespiratoryRate.clear();
    }

    public final void setDatePickerDialog(@NotNull DatePickerDialog datePickerDialog) {
        Intrinsics.checkNotNullParameter(datePickerDialog, "<set-?>");
        this.datePickerDialog = datePickerDialog;
    }

    public final void setLineGraphVisible(boolean z) {
        if (z) {
            LineChart lineChart = u().lineChartRespiratoryRate;
            Intrinsics.checkNotNullExpressionValue(lineChart, "binding.lineChartRespiratoryRate");
            visible(lineChart);
            CandleStickChart candleStickChart = u().candleChartRespiratoryRate;
            Intrinsics.checkNotNullExpressionValue(candleStickChart, "binding.candleChartRespiratoryRate");
            gone(candleStickChart);
            return;
        }
        LineChart lineChart2 = u().lineChartRespiratoryRate;
        Intrinsics.checkNotNullExpressionValue(lineChart2, "binding.lineChartRespiratoryRate");
        gone(lineChart2);
        u().lineChartRespiratoryRate.clear();
    }

    public final void setSimpleDateFormat(@NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
        this.simpleDateFormat = simpleDateFormat;
    }

    public final void share() {
        RespiratoryRateShareData respiratoryRateShareData = new RespiratoryRateShareData();
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM, yyyy");
        respiratoryRateShareData.setMin(Integer.valueOf(this.A));
        respiratoryRateShareData.setMax(Integer.valueOf(this.B));
        int i = this.r;
        if (i == this.s) {
            respiratoryRateShareData.setTitle(simpleDateFormat.format(this.o.getTime()));
        } else {
            Pair<? extends Calendar, ? extends Calendar> pair = null;
            if (i == this.t) {
                StringBuilder sb = new StringBuilder();
                Pair<? extends Calendar, ? extends Calendar> pair2 = this.v;
                if (pair2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                    pair2 = null;
                }
                sb.append(simpleDateFormat.format(pair2.getFirst().getTime()));
                sb.append(Soundex.SILENT_MARKER);
                Pair<? extends Calendar, ? extends Calendar> pair3 = this.v;
                if (pair3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                } else {
                    pair = pair3;
                }
                sb.append(simpleDateFormat.format(pair.getSecond().getTime()));
                respiratoryRateShareData.setTitle(sb.toString());
            } else if (i == this.u) {
                StringBuilder sb2 = new StringBuilder();
                Pair<? extends Calendar, ? extends Calendar> pair4 = this.v;
                if (pair4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                    pair4 = null;
                }
                sb2.append(simpleDateFormat.format(pair4.getFirst().getTime()));
                sb2.append(Soundex.SILENT_MARKER);
                Pair<? extends Calendar, ? extends Calendar> pair5 = this.v;
                if (pair5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                } else {
                    pair = pair5;
                }
                sb2.append(simpleDateFormat.format(pair.getSecond().getTime()));
                respiratoryRateShareData.setTitle(sb2.toString());
            }
        }
        Intent intent = new Intent(getContext(), ActivityRespiratoryRateShare.class);
        intent.putExtra(Constants.SHARE_DATA.getValue(), respiratoryRateShareData);
        requireActivity().startActivity(intent);
    }

    public final FragmentVitalRespiratoryRateBinding u() {
        FragmentVitalRespiratoryRateBinding fragmentVitalRespiratoryRateBinding = this.m;
        Intrinsics.checkNotNull(fragmentVitalRespiratoryRateBinding);
        return fragmentVitalRespiratoryRateBinding;
    }

    @Override // com.coveiot.android.respiratoryrate.listener.ContractRespiratoryRateDashBoard
    public void updateDailyLevelData(@Nullable DailyRespiratoryRateEntity dailyRespiratoryRateEntity) {
        if (this.r == this.s) {
            LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), Dispatchers.getMain(), null, new a(dailyRespiratoryRateEntity, null), 2, null);
        }
    }

    @Override // com.coveiot.android.respiratoryrate.listener.ContractRespiratoryRateDashBoard
    public void updateRangeLevelData(@NotNull List<RespiratoryRateListBean> dailyRespiratoryRate) {
        Intrinsics.checkNotNullParameter(dailyRespiratoryRate, "dailyRespiratoryRate");
        if (!dailyRespiratoryRate.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Iterator<RespiratoryRateListBean> it = dailyRespiratoryRate.iterator();
            while (it.hasNext()) {
                RespiratoryRateListBean next = it.next();
                if ((next != null ? next.getMin() : null) != null) {
                    Integer min = next.getMin();
                    Intrinsics.checkNotNull(min);
                    if (min.intValue() > 0) {
                        Integer min2 = next.getMin();
                        Intrinsics.checkNotNull(min2);
                        arrayList.add(min2);
                    }
                }
                if ((next != null ? next.getMax() : null) != null) {
                    Integer max = next.getMax();
                    Intrinsics.checkNotNull(max);
                    if (max.intValue() > 0) {
                        Integer max2 = next.getMax();
                        Intrinsics.checkNotNull(max2);
                        arrayList2.add(max2);
                    }
                }
            }
            if (arrayList.size() > 0) {
                BleApiUtils bleApiUtils = BleApiUtils.INSTANCE;
                Integer minValueFromList = bleApiUtils.getMinValueFromList(arrayList);
                Intrinsics.checkNotNull(minValueFromList);
                this.A = minValueFromList.intValue();
                TextView textView = u().vitalsMainData.tvVitalMinValue;
                StringBuilder sb = new StringBuilder();
                sb.append(bleApiUtils.getMinValueFromList(arrayList));
                sb.append(' ');
                int i = R.string.brpm;
                sb.append(getString(i));
                textView.setText(sb.toString());
                TextView textView2 = u().vitalsMainData.tvVitalMaxValue;
                textView2.setText(bleApiUtils.getMaxValue(arrayList2) + ' ' + getString(i));
                SpannableString spannableString = new SpannableString(u().vitalsMainData.tvVitalMaxValue.getText().toString());
                RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(0.5f);
                String obj = u().vitalsMainData.tvVitalMaxValue.getText().toString();
                String string = getString(i);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.brpm)");
                spannableString.setSpan(relativeSizeSpan, StringsKt__StringsKt.indexOf$default((CharSequence) obj, string, 0, false, 6, (Object) null), u().vitalsMainData.tvVitalMaxValue.getText().toString().length(), 0);
                u().vitalsMainData.tvVitalMaxValue.setText(spannableString);
            }
            if (arrayList2.size() > 0) {
                this.B = BleApiUtils.INSTANCE.getMaxValue(arrayList2);
                SpannableString spannableString2 = new SpannableString(u().vitalsMainData.tvVitalMinValue.getText().toString());
                RelativeSizeSpan relativeSizeSpan2 = new RelativeSizeSpan(0.5f);
                String obj2 = u().vitalsMainData.tvVitalMinValue.getText().toString();
                String string2 = getString(R.string.brpm);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.brpm)");
                spannableString2.setSpan(relativeSizeSpan2, StringsKt__StringsKt.indexOf$default((CharSequence) obj2, string2, 0, false, 6, (Object) null), u().vitalsMainData.tvVitalMinValue.getText().toString().length(), 0);
                u().vitalsMainData.tvVitalMinValue.setText(spannableString2);
                return;
            }
            return;
        }
        TextView textView3 = u().vitalsMainData.tvVitalMinValue;
        StringBuilder sb2 = new StringBuilder();
        Constants constants = Constants.EMPTY_RESPIRATORY_RATE;
        sb2.append(constants.getValue());
        sb2.append(' ');
        int i2 = R.string.brpm;
        sb2.append(getString(i2));
        textView3.setText(sb2.toString());
        TextView textView4 = u().vitalsMainData.tvVitalMaxValue;
        textView4.setText(constants.getValue() + ' ' + getString(i2));
        SpannableString spannableString3 = new SpannableString(u().vitalsMainData.tvVitalMaxValue.getText().toString());
        RelativeSizeSpan relativeSizeSpan3 = new RelativeSizeSpan(0.5f);
        String obj3 = u().vitalsMainData.tvVitalMaxValue.getText().toString();
        String string3 = getString(i2);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.brpm)");
        spannableString3.setSpan(relativeSizeSpan3, StringsKt__StringsKt.indexOf$default((CharSequence) obj3, string3, 0, false, 6, (Object) null), u().vitalsMainData.tvVitalMaxValue.getText().toString().length(), 0);
        u().vitalsMainData.tvVitalMaxValue.setText(spannableString3);
        SpannableString spannableString4 = new SpannableString(u().vitalsMainData.tvVitalMinValue.getText().toString());
        RelativeSizeSpan relativeSizeSpan4 = new RelativeSizeSpan(0.5f);
        String obj4 = u().vitalsMainData.tvVitalMinValue.getText().toString();
        String string4 = getString(i2);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.brpm)");
        spannableString4.setSpan(relativeSizeSpan4, StringsKt__StringsKt.indexOf$default((CharSequence) obj4, string4, 0, false, 6, (Object) null), u().vitalsMainData.tvVitalMinValue.getText().toString().length(), 0);
        u().vitalsMainData.tvVitalMinValue.setText(spannableString4);
        CandleStickChart candleStickChart = u().candleChartRespiratoryRate;
        Intrinsics.checkNotNullExpressionValue(candleStickChart, "binding.candleChartRespiratoryRate");
        gone(candleStickChart);
        TextView textView5 = u().tvNoDataFound;
        Intrinsics.checkNotNullExpressionValue(textView5, "binding.tvNoDataFound");
        visible(textView5);
        this.A = 0;
        this.B = 0;
    }

    public final void v(Calendar calendar, Calendar calendar2) {
        if (isAdded()) {
            if (DateUtils.isToday(calendar2.getTimeInMillis())) {
                u().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                u().vitalsMainData.ibForward.setEnabled(false);
            } else {
                u().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                u().vitalsMainData.ibForward.setEnabled(true);
            }
            setCandleChartVisible(true);
            setLineGraphVisible(false);
            TextView textView = u().vitalsMainData.tvSelectedTypeValue;
            StringBuilder sb = new StringBuilder();
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            sb.append(themesUtils.getDateFormatDDMMMYYYY(calendar));
            sb.append(" - ");
            sb.append(themesUtils.getDateFormatDDMMMYYYY(calendar2));
            textView.setText(sb.toString());
            RespiratoryRateViewModel respiratoryRateViewModel = this.n;
            if (respiratoryRateViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                respiratoryRateViewModel = null;
            }
            respiratoryRateViewModel.selectDateRangeView(calendar, calendar2);
        }
    }

    public final void w(Calendar calendar) {
        if (isAdded()) {
            TextView textView = u().vitalsMainData.tvSelectedTypeValue;
            Utils utils = Utils.INSTANCE;
            long timeInMillis = calendar.getTimeInMillis();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            textView.setText(utils.getTodayYesterdayStringFromTimeStamp(timeInMillis, requireContext));
            if (DateUtils.isToday(calendar.getTimeInMillis())) {
                u().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                u().vitalsMainData.ibForward.setEnabled(false);
            } else {
                u().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                u().vitalsMainData.ibForward.setEnabled(true);
            }
            RespiratoryRateViewModel respiratoryRateViewModel = this.n;
            if (respiratoryRateViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                respiratoryRateViewModel = null;
            }
            respiratoryRateViewModel.loadDailyData(calendar);
            this.o = calendar;
        }
    }

    public final void z() {
        FragmentVitalRespiratoryRateBinding u = u();
        u.vitalsMainData.tvSelectedTypeValue.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.fragments.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalRespiratoryRateFragment.A(VitalRespiratoryRateFragment.this, view);
            }
        });
        u.vitalsMainData.ibPrevious.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.fragments.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalRespiratoryRateFragment.B(VitalRespiratoryRateFragment.this, view);
            }
        });
        u.vitalsMainData.ibForward.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.fragments.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalRespiratoryRateFragment.C(VitalRespiratoryRateFragment.this, view);
            }
        });
        u.vitalsMainData.ibShareVitals.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.fragments.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalRespiratoryRateFragment.D(VitalRespiratoryRateFragment.this, view);
            }
        });
        u.vitalsMainData.tvDay.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.fragments.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalRespiratoryRateFragment.E(VitalRespiratoryRateFragment.this, view);
            }
        });
        u.vitalsMainData.tvWeek.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.fragments.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalRespiratoryRateFragment.F(VitalRespiratoryRateFragment.this, view);
            }
        });
        u.vitalsMainData.tvMonth.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.fragments.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalRespiratoryRateFragment.G(VitalRespiratoryRateFragment.this, view);
            }
        });
    }
}
