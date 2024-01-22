package com.coveiot.android.leonardo.dashboard.vitals.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentVitalTemperatureBinding;
import com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractTemperatureDashboard;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentTemperatureViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.compundview.CustomMarkerViewVitals;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.szabh.smable3.entity.BleNewsFeed;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class VitalTemperatureFragment extends BaseFragment implements ContractTemperatureDashboard {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public DatePickerDialog datePickerDialog;
    @Nullable
    public FragmentVitalTemperatureBinding m;
    public FragmentTemperatureViewModel n;
    @NotNull
    public Calendar o;
    public SimpleDateFormat simpleDateFormat;

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final VitalTemperatureFragment newInstance() {
            return new VitalTemperatureFragment();
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalTemperatureFragment$getLatestTemperatureValueWithTimestamp$2", f = "VitalTemperatureFragment.kt", i = {}, l = {419, 452, 463, BleNewsFeed.CONTENT_MAX_LENGTH}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Calendar $calendar;
        public final /* synthetic */ String $serialNo;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalTemperatureFragment$getLatestTemperatureValueWithTimestamp$2$1", f = "VitalTemperatureFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalTemperatureFragment$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        public static final class C0277a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ double $temperatureVal;
            public int label;
            public final /* synthetic */ VitalTemperatureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0277a(VitalTemperatureFragment vitalTemperatureFragment, double d, Continuation<? super C0277a> continuation) {
                super(2, continuation);
                this.this$0 = vitalTemperatureFragment;
                this.$temperatureVal = d;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0277a(this.this$0, this.$temperatureVal, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0277a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    TextView textView = this.this$0.r().vitalsMainData.tvAvgStepsSleepValue;
                    textView.setText(this.$temperatureVal + " °C /" + PayUtils.getTemperatureInFahrenheit(this.$temperatureVal) + " °F");
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalTemperatureFragment$getLatestTemperatureValueWithTimestamp$2$2", f = "VitalTemperatureFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes4.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Ref.DoubleRef $temperature;
            public int label;
            public final /* synthetic */ VitalTemperatureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(VitalTemperatureFragment vitalTemperatureFragment, Ref.DoubleRef doubleRef, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = vitalTemperatureFragment;
                this.$temperature = doubleRef;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.this$0, this.$temperature, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    TextView textView = this.this$0.r().vitalsMainData.tvAvgStepsSleepValue;
                    textView.setText(this.$temperature.element + " °C /" + PayUtils.getTemperatureInFahrenheit(this.$temperature.element) + " °F");
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalTemperatureFragment$getLatestTemperatureValueWithTimestamp$2$3", f = "VitalTemperatureFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes4.dex */
        public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ VitalTemperatureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(VitalTemperatureFragment vitalTemperatureFragment, Continuation<? super c> continuation) {
                super(2, continuation);
                this.this$0 = vitalTemperatureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new c(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.r().vitalsMainData.tvAvgStepsSleepValue.setText(String.valueOf(AppConstants.TWO_DASH_STRING.getValue()));
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalTemperatureFragment$getLatestTemperatureValueWithTimestamp$2$4", f = "VitalTemperatureFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes4.dex */
        public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ VitalTemperatureFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public d(VitalTemperatureFragment vitalTemperatureFragment, Continuation<? super d> continuation) {
                super(2, continuation);
                this.this$0 = vitalTemperatureFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new d(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.this$0.r().vitalsMainData.tvAvgStepsSleepValue != null) {
                        this.this$0.r().vitalsMainData.tvAvgStepsSleepValue.setText(String.valueOf(AppConstants.TWO_DASH_STRING.getValue()));
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Calendar calendar, String str, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$calendar = calendar;
            this.$serialNo = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$calendar, this.$serialNo, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:25:0x007c, code lost:
            if (r15.isBESDevice(r3) != false) goto L43;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r15) {
            /*
                Method dump skipped, instructions count: 426
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalTemperatureFragment.a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalTemperatureFragment$handleSelectedDate$1", f = "VitalTemperatureFragment.kt", i = {}, l = {184}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                VitalTemperatureFragment vitalTemperatureFragment = VitalTemperatureFragment.this;
                Calendar calendar = vitalTemperatureFragment.o;
                Intrinsics.checkNotNull(calendar);
                String macAddress = BleApiManager.getInstance(VitalTemperatureFragment.this.getContext()).getBleApi().getMacAddress();
                this.label = 1;
                if (vitalTemperatureFragment.s(calendar, macAddress, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public VitalTemperatureFragment() {
        AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this.o = calendar;
    }

    public static final void A(VitalTemperatureFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.share();
    }

    public static final void C(VitalTemperatureFragment this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i, i2, i3);
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        this$0.t(newDate);
    }

    public static final void u(final FragmentVitalTemperatureBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this_apply.nestedScrollViewTemperature.post(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.p0
            @Override // java.lang.Runnable
            public final void run() {
                VitalTemperatureFragment.v(FragmentVitalTemperatureBinding.this);
            }
        });
    }

    public static final void v(FragmentVitalTemperatureBinding this_apply) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this_apply.nestedScrollViewTemperature.fullScroll(130);
    }

    public static final void x(VitalTemperatureFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.B();
        this$0.getDatePickerDialog().getDatePicker().setMaxDate(System.currentTimeMillis());
        this$0.getDatePickerDialog().show();
    }

    public static final void y(VitalTemperatureFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.t(PayUtils.INSTANCE.getPreviousDayCalendar(this$0.o));
    }

    public static final void z(VitalTemperatureFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (DateUtils.isToday(this$0.o.getTimeInMillis())) {
            return;
        }
        this$0.t(PayUtils.INSTANCE.getNextDayCalendar(this$0.o));
    }

    public final void B() {
        Calendar calendar = this.o;
        setDatePickerDialog(new DatePickerDialog(requireActivity(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.j0
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                VitalTemperatureFragment.C(VitalTemperatureFragment.this, datePicker, i, i2, i3);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)));
    }

    public final void D(ArrayList<Entry> arrayList, ArrayList<String> arrayList2) {
        FragmentVitalTemperatureBinding r = r();
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
        lineDataSet.setCircleColor(getResources().getColor(R.color.white));
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        lineDataSet.setColor(getResources().getColor(R.color.steps_graph_color));
        lineDataSet.setHighLightColor(getResources().getColor(17170445));
        if (lineDataSet.getEntryCount() > 0) {
            int entryCount = lineDataSet.getEntryCount();
            int[] iArr = new int[entryCount];
            for (int i = 0; i < entryCount; i++) {
                iArr[i] = getResources().getColor(R.color.steps_graph_color);
            }
            iArr[entryCount - 1] = getResources().getColor(R.color.steps_graph_color);
            lineDataSet.setColors(Arrays.copyOf(iArr, entryCount));
        }
        LineDataSet lineDataSet2 = new LineDataSet(arrayList3, "");
        lineDataSet2.setCircleColor(getResources().getColor(17170445));
        lineDataSet2.setDrawValues(false);
        lineDataSet2.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        lineDataSet2.setColor(getResources().getColor(R.color.secondary_text_color));
        lineDataSet2.setHighLightColor(getResources().getColor(17170445));
        if (lineDataSet2.getEntryCount() > 0) {
            int entryCount2 = lineDataSet2.getEntryCount();
            int[] iArr2 = new int[entryCount2];
            for (int i2 = 0; i2 < entryCount2; i2++) {
                iArr2[i2] = getResources().getColor(17170445);
            }
            iArr2[entryCount2 - 1] = getResources().getColor(17170445);
            lineDataSet2.setColors(Arrays.copyOf(iArr2, entryCount2));
        }
        LineData lineData = new LineData(lineDataSet, lineDataSet2);
        r.lineChartTemperature.getXAxis().setValueFormatter(new IndexAxisValueFormatter(arrayList2));
        lineData.setDrawValues(false);
        r.lineChartTemperature.setData(lineData);
        r.lineChartTemperature.setDrawGridBackground(false);
        r.lineChartTemperature.getDescription().setEnabled(false);
        lineData.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        r.lineChartTemperature.getPaint(7).setColor(getResources().getColor(R.color.secondary_text_color));
        r.lineChartTemperature.setDrawBorders(false);
        r.lineChartTemperature.setAutoScaleMinMaxEnabled(false);
        r.lineChartTemperature.setPinchZoom(false);
        YAxis axisLeft = r.lineChartTemperature.getAxisLeft();
        axisLeft.setEnabled(true);
        axisLeft.setDrawAxisLine(true);
        axisLeft.setDrawGridLines(false);
        Boolean isTemperatureUnitInFahrenheit = UserDataManager.getInstance(getContext()).isTemperatureUnitInFahrenheit();
        Intrinsics.checkNotNullExpressionValue(isTemperatureUnitInFahrenheit, "getInstance(context).isTemperatureUnitInFahrenheit");
        if (isTemperatureUnitInFahrenheit.booleanValue()) {
            axisLeft.mAxisMaximum = 150.0f;
        } else {
            axisLeft.mAxisMaximum = 60.0f;
        }
        axisLeft.setAxisLineColor(getResources().getColor(R.color.colorSecondaryGrey));
        r.lineChartTemperature.getAxisRight().setEnabled(false);
        XAxis xAxis = r.lineChartTemperature.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineColor(getResources().getColor(R.color.colorSecondaryGrey));
        r.lineChartTemperature.getAxisLeft().setStartAtZero(true);
        r.lineChartTemperature.getAxisRight().setStartAtZero(true);
        r.lineChartTemperature.getAxisLeft().setTextColor(getResources().getColor(R.color.secondary_text_color));
        r.lineChartTemperature.getXAxis().setTextColor(getResources().getColor(R.color.secondary_text_color));
        r.lineChartTemperature.getLegend().setTextColor(getResources().getColor(R.color.secondary_text_color));
        r.lineChartTemperature.getLegend().setEnabled(false);
        r.lineChartTemperature.setVisibleXRangeMinimum(5.0f);
        Context context = getContext();
        String string = getString(R.string.temperature);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.temperature)");
        CustomMarkerViewVitals customMarkerViewVitals = new CustomMarkerViewVitals(context, R.layout.custom_marker_view_steps_hr, string, 0, arrayList2);
        customMarkerViewVitals.setChartView(r.lineChartTemperature);
        r.lineChartTemperature.setMarker(customMarkerViewVitals);
        r.lineChartTemperature.setPinchZoom(false);
        r.lineChartTemperature.setAutoScaleMinMaxEnabled(false);
        r.lineChartTemperature.setScaleEnabled(false);
        r.lineChartTemperature.setDoubleTapToZoomEnabled(false);
        r.lineChartTemperature.invalidate();
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
        FragmentTemperatureViewModel fragmentTemperatureViewModel = (FragmentTemperatureViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FragmentTemperatureViewModel.class);
        this.n = fragmentTemperatureViewModel;
        if (fragmentTemperatureViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentTemperatureViewModel = null;
        }
        fragmentTemperatureViewModel.setContractTemperatureDashboard$app_prodRelease(this);
        FragmentTemperatureViewModel fragmentTemperatureViewModel2 = this.n;
        if (fragmentTemperatureViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentTemperatureViewModel2 = null;
        }
        fragmentTemperatureViewModel2.setMLifecycleOwner(this);
        final FragmentVitalTemperatureBinding r = r();
        TextView textView = r.vitalsMainData.tvVitalInfo;
        Intrinsics.checkNotNullExpressionValue(textView, "vitalsMainData.tvVitalInfo");
        gone(textView);
        TextView textView2 = r.vitalsMainData.tvVitalMax;
        Intrinsics.checkNotNullExpressionValue(textView2, "vitalsMainData.tvVitalMax");
        inVisible(textView2);
        TextView textView3 = r.vitalsMainData.tvVitalMin;
        Intrinsics.checkNotNullExpressionValue(textView3, "vitalsMainData.tvVitalMin");
        inVisible(textView3);
        TextView textView4 = r.vitalsMainData.tvVitalMaxValue;
        Intrinsics.checkNotNullExpressionValue(textView4, "vitalsMainData.tvVitalMaxValue");
        inVisible(textView4);
        TextView textView5 = r.vitalsMainData.tvVitalMinValue;
        Intrinsics.checkNotNullExpressionValue(textView5, "vitalsMainData.tvVitalMinValue");
        inVisible(textView5);
        ImageView imageView = r.vitalsMainData.ivMin;
        Intrinsics.checkNotNullExpressionValue(imageView, "vitalsMainData.ivMin");
        inVisible(imageView);
        ImageView imageView2 = r.vitalsMainData.ivMax;
        Intrinsics.checkNotNullExpressionValue(imageView2, "vitalsMainData.ivMax");
        inVisible(imageView2);
        ImageView imageView3 = r.vitalsMainData.ivMinBg;
        Intrinsics.checkNotNullExpressionValue(imageView3, "vitalsMainData.ivMinBg");
        inVisible(imageView3);
        ImageView imageView4 = r.vitalsMainData.ivMaxBg;
        Intrinsics.checkNotNullExpressionValue(imageView4, "vitalsMainData.ivMaxBg");
        inVisible(imageView4);
        ConstraintLayout constraintLayout = r.vitalsMainData.clTopSelector;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "vitalsMainData.clTopSelector");
        gone(constraintLayout);
        ConstraintLayout constraintLayout2 = r.vitalsMainData.clCenterDataStepsSleep;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "vitalsMainData.clCenterDataStepsSleep");
        visible(constraintLayout2);
        r.vitalsMainData.tvVitalName.setText(getString(R.string.temperature));
        r.vitalsMainData.tvVitalName.setCompoundDrawables(null, null, null, null);
        r.vitalsMainData.ivCenterVital.setImageResource(R.drawable.ic_temperature);
        TextView textView6 = r.vitalsMainData.tvOutOfStepsSleepValue;
        Intrinsics.checkNotNullExpressionValue(textView6, "vitalsMainData.tvOutOfStepsSleepValue");
        gone(textView6);
        r.vitalsMainData.tvVitalName.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.k0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalTemperatureFragment.u(FragmentVitalTemperatureBinding.this, view);
            }
        });
        TextView textView7 = r.vitalsMainData.tvLastSyncTime;
        PayUtils payUtils = PayUtils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        textView7.setText(payUtils.getLastSyncTime(requireContext2));
        String string = getString(R.string.medical_disclaimer_info);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.medical_disclaimer_info)");
        SpannableString spannableString = new SpannableString(string);
        Context context = getContext();
        Integer valueOf = context != null ? Integer.valueOf(context.getColor(R.color.color_b3b3b3)) : null;
        Intrinsics.checkNotNull(valueOf);
        spannableString.setSpan(new ForegroundColorSpan(valueOf.intValue()), 0, 12, 18);
        r.tvdisclaimer.setText(spannableString);
        w();
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        t(calendar);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractTemperatureDashboard
    public boolean isSyncInProgress() {
        FragmentActivity requireActivity = requireActivity();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        return ((DataSyncViewModel) ViewModelProviders.of(requireActivity, new com.coveiot.android.dashboard2.ViewModelFactory(requireActivity2)).get(DataSyncViewModel.class)).checkIsSyncing();
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentVitalTemperatureBinding.inflate(inflater, viewGroup, false);
        View root = r().getRoot();
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

    public final FragmentVitalTemperatureBinding r() {
        FragmentVitalTemperatureBinding fragmentVitalTemperatureBinding = this.m;
        Intrinsics.checkNotNull(fragmentVitalTemperatureBinding);
        return fragmentVitalTemperatureBinding;
    }

    public final Object s(Calendar calendar, String str, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new a(calendar, str, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
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
        shareData.setHigh_temp(r().vitalsMainData.tvAvgStepsSleepValue.getText().toString());
        shareData.setGraphType(getResources().getString(R.string.share_daily));
        shareData.setDwmValue(simpleDateFormat.format(this.o.getTime()));
        AppNavigator.Companion companion = AppNavigator.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getResources().getString(R.string.temperature);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.temperature)");
        companion.navigateToShareScreen(requireContext, shareData, string);
    }

    public final void t(Calendar calendar) {
        if (isAdded()) {
            TextView textView = r().vitalsMainData.tvSelectedTypeValue;
            long timeInMillis = calendar.getTimeInMillis();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            textView.setText(PayUtils.getTodayYesterdayStringFromTimeStamp(timeInMillis, requireContext));
            if (DateUtils.isToday(calendar.getTimeInMillis())) {
                r().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                r().vitalsMainData.ibForward.setEnabled(false);
            } else {
                r().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                r().vitalsMainData.ibForward.setEnabled(true);
            }
            FragmentTemperatureViewModel fragmentTemperatureViewModel = null;
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, null, null, new b(null), 3, null);
            FragmentTemperatureViewModel fragmentTemperatureViewModel2 = this.n;
            if (fragmentTemperatureViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentTemperatureViewModel2 = null;
            }
            fragmentTemperatureViewModel2.loadHourlyTemperatureData(calendar);
            FragmentTemperatureViewModel fragmentTemperatureViewModel3 = this.n;
            if (fragmentTemperatureViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                fragmentTemperatureViewModel = fragmentTemperatureViewModel3;
            }
            fragmentTemperatureViewModel.loadDailyData(calendar);
            this.o = calendar;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0057, code lost:
        if (r0.isBESDevice(r1) != false) goto L19;
     */
    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractTemperatureDashboard
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateDailyLevelData(@org.jetbrains.annotations.Nullable com.coveiot.covedb.temperature.entity.DailyTemperature r12) {
        /*
            r11 = this;
            if (r12 == 0) goto Lf2
            double r0 = r12.temperature_low
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto Lf2
            double r0 = r12.temperature_high
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto Lf2
            com.coveiot.android.devicemodels.DeviceUtils$Companion r0 = com.coveiot.android.devicemodels.DeviceUtils.Companion
            android.content.Context r1 = r11.requireContext()
            java.lang.String r2 = "requireContext()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            boolean r1 = r0.isCZDevice(r1)
            java.lang.String r3 = " °F"
            java.lang.String r4 = " °C /"
            if (r1 != 0) goto L59
            android.content.Context r1 = r11.requireContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            boolean r1 = r0.isCADevice(r1)
            if (r1 != 0) goto L59
            android.content.Context r1 = r11.requireContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            boolean r1 = r0.isCYDevice(r1)
            if (r1 != 0) goto L59
            android.content.Context r1 = r11.requireContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            boolean r1 = r0.isPS1Device(r1)
            if (r1 != 0) goto L59
            android.content.Context r1 = r11.requireContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            boolean r0 = r0.isBESDevice(r1)
            if (r0 == 0) goto Lc9
        L59:
            androidx.fragment.app.FragmentActivity r0 = r11.getActivity()
            if (r0 == 0) goto L64
            android.content.Context r0 = r0.getBaseContext()
            goto L65
        L64:
            r0 = 0
        L65:
            r5 = r0
            com.coveiot.android.leonardo.utils.AppConstants r0 = com.coveiot.android.leonardo.utils.AppConstants.DAY
            java.lang.String r6 = r0.toString()
            java.lang.String r7 = r12.mDate
            java.lang.String r0 = "dailyTemperature.mDate"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)
            r8 = 0
            r9 = 8
            r10 = 0
            java.lang.String r0 = com.coveiot.android.leonardo.utils.PayUtils.getTodayYesterdayDate$default(r5, r6, r7, r8, r9, r10)
            r1 = 1
            java.lang.String r2 = "Today"
            boolean r0 = kotlin.text.m.equals(r0, r2, r1)
            if (r0 == 0) goto Lc9
            android.content.Context r12 = r11.requireContext()
            com.coveiot.covepreferences.UserDataManager r12 = com.coveiot.covepreferences.UserDataManager.getInstance(r12)
            com.coveiot.covepreferences.data.LatestHealthDataModel r12 = r12.getLatestTemperatureValueFromPref()
            if (r12 == 0) goto L107
            com.coveiot.android.leonardo.utils.PayUtils r0 = com.coveiot.android.leonardo.utils.PayUtils.INSTANCE
            int r12 = r12.getValue()
            java.lang.String r12 = java.lang.String.valueOf(r12)
            java.lang.String r12 = r0.getWithDecimalPointAfterTwoDigit(r12)
            double r0 = java.lang.Double.parseDouble(r12)
            com.coveiot.android.boat.databinding.FragmentVitalTemperatureBinding r12 = r11.r()
            com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding r12 = r12.vitalsMainData
            android.widget.TextView r12 = r12.tvAvgStepsSleepValue
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            r2.append(r4)
            double r0 = com.coveiot.android.leonardo.utils.PayUtils.getTemperatureInFahrenheit(r0)
            r2.append(r0)
            r2.append(r3)
            java.lang.String r0 = r2.toString()
            r12.setText(r0)
            goto L107
        Lc9:
            com.coveiot.android.boat.databinding.FragmentVitalTemperatureBinding r0 = r11.r()
            com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding r0 = r0.vitalsMainData
            android.widget.TextView r0 = r0.tvAvgStepsSleepValue
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            double r5 = r12.temperature_high
            r1.append(r5)
            r1.append(r4)
            double r4 = r12.temperature_high
            double r4 = com.coveiot.android.leonardo.utils.PayUtils.getTemperatureInFahrenheit(r4)
            r1.append(r4)
            r1.append(r3)
            java.lang.String r12 = r1.toString()
            r0.setText(r12)
            goto L107
        Lf2:
            com.coveiot.android.boat.databinding.FragmentVitalTemperatureBinding r12 = r11.r()
            com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding r12 = r12.vitalsMainData
            android.widget.TextView r12 = r12.tvAvgStepsSleepValue
            com.coveiot.android.leonardo.utils.AppConstants r0 = com.coveiot.android.leonardo.utils.AppConstants.TWO_DASH_STRING
            java.lang.String r0 = r0.getValue()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r12.setText(r0)
        L107:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalTemperatureFragment.updateDailyLevelData(com.coveiot.covedb.temperature.entity.DailyTemperature):void");
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractTemperatureDashboard
    public void updateHourlyLevelData(@Nullable ArrayList<Entry> arrayList, @Nullable ArrayList<String> arrayList2) {
        if (arrayList != null) {
            LineChart lineChart = r().lineChartTemperature;
            Intrinsics.checkNotNullExpressionValue(lineChart, "binding.lineChartTemperature");
            visible(lineChart);
            TextView textView = r().tvNoDataFound;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvNoDataFound");
            gone(textView);
            if (arrayList2 != null) {
                D(arrayList, arrayList2);
                return;
            }
            return;
        }
        LineChart lineChart2 = r().lineChartTemperature;
        Intrinsics.checkNotNullExpressionValue(lineChart2, "binding.lineChartTemperature");
        gone(lineChart2);
        TextView textView2 = r().tvNoDataFound;
        Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvNoDataFound");
        visible(textView2);
        r().vitalsMainData.tvAvgStepsSleepValue.setText(String.valueOf(AppConstants.TWO_DASH_STRING.getValue()));
    }

    public final void w() {
        FragmentVitalTemperatureBinding r = r();
        r.vitalsMainData.tvSelectedTypeValue.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.n0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalTemperatureFragment.x(VitalTemperatureFragment.this, view);
            }
        });
        r.vitalsMainData.ibPrevious.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.o0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalTemperatureFragment.y(VitalTemperatureFragment.this, view);
            }
        });
        r.vitalsMainData.ibForward.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.l0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalTemperatureFragment.z(VitalTemperatureFragment.this, view);
            }
        });
        r.vitalsMainData.ibShareVitals.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.m0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalTemperatureFragment.A(VitalTemperatureFragment.this, view);
            }
        });
    }
}
