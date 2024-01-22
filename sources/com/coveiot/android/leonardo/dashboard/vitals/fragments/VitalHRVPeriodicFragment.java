package com.coveiot.android.leonardo.dashboard.vitals.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
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
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentVitalHrvBinding;
import com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicHRVDashboard;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentHRVPeriodicViewModel;
import com.coveiot.android.leonardo.more.viewmodel.AutoStressSettingsViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarRangeDialog;
import com.coveiot.android.theme.compundview.CustomMarkerViewVitals;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covedb.hrv.entity.DailyHRV;
import com.coveiot.covedb.hrv.entity.HourlyHRV;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.coveiot.covepreferences.data.AutoStressSettingsData;
import com.coveiot.repository.hrv.datasource.HRVRepository;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.jstyle.blesdk1860.constant.BleConst;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class VitalHRVPeriodicFragment extends BaseFragment implements ContractPeriodicHRVDashboard, CalendarRangeDialog.OnCalendarRangeSelector {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public AutoStressSettingsData autoStressSettingsDataCommon;
    public AutoStressSettingsViewModel autoStressSettingsViewModel;
    public DatePickerDialog datePickerDialog;
    @Nullable
    public FragmentVitalHrvBinding m;
    public FragmentHRVPeriodicViewModel n;
    @NotNull
    public Calendar o;
    public Pair<? extends Calendar, ? extends Calendar> p;
    public CalendarRangeDialog q;
    public int r;
    public final int s;
    public SimpleDateFormat simpleDateFormat;
    public final int t;
    public final int u;
    public boolean v;
    @Nullable
    public DailyHRV w;

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final VitalHRVPeriodicFragment newInstance() {
            return new VitalHRVPeriodicFragment();
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalHRVPeriodicFragment$getLatestStressValueWithTimestamp$2", f = "VitalsHRVPeriodicFragment.kt", i = {}, l = {869, 887, 891}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Calendar $calendar;
        public final /* synthetic */ String $serialNo;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalHRVPeriodicFragment$getLatestStressValueWithTimestamp$2$1", f = "VitalsHRVPeriodicFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalHRVPeriodicFragment$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        public static final class C0276a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Ref.DoubleRef $hrv;
            public int label;
            public final /* synthetic */ VitalHRVPeriodicFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0276a(VitalHRVPeriodicFragment vitalHRVPeriodicFragment, Ref.DoubleRef doubleRef, Continuation<? super C0276a> continuation) {
                super(2, continuation);
                this.this$0 = vitalHRVPeriodicFragment;
                this.$hrv = doubleRef;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0276a(this.this$0, this.$hrv, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0276a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    TextView textView = this.this$0.u().vitalsMainData.tvAvgStepsSleepValue;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    Locale locale = Locale.ENGLISH;
                    String string = this.this$0.getString(R.string.hrv_spannable);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.hrv_spannable)");
                    String format = String.format("%.02f", Arrays.copyOf(new Object[]{Boxing.boxDouble(PayUtils.INSTANCE.calculationFormulaHRV(this.$hrv.element))}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    String format2 = String.format(locale, string, Arrays.copyOf(new Object[]{format}, 1));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                    textView.setText(Html.fromHtml(format2), TextView.BufferType.SPANNABLE);
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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    HRVRepository.Companion companion = HRVRepository.Companion;
                    Context requireContext = VitalHRVPeriodicFragment.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    HourlyHRV latestHRVRecordHourly = companion.getInstance(requireContext).getLatestHRVRecordHourly(this.$calendar, this.$serialNo);
                    if (latestHRVRecordHourly != null) {
                        int size = 60 / latestHRVRecordHourly.getCodevalue().size();
                        Ref.DoubleRef doubleRef = new Ref.DoubleRef();
                        int size2 = latestHRVRecordHourly.getCodevalue().size();
                        int i2 = 0;
                        for (int i3 = 0; i3 < size2; i3++) {
                            Double d = latestHRVRecordHourly.getCodevalue().get(i3);
                            Intrinsics.checkNotNullExpressionValue(d, "hourlyHRV.codevalue.get(i)");
                            if (d.doubleValue() > 0.0d) {
                                Double d2 = latestHRVRecordHourly.getCodevalue().get(i3);
                                Intrinsics.checkNotNullExpressionValue(d2, "hourlyHRV.codevalue.get(i)");
                                doubleRef.element = d2.doubleValue();
                                i2 = i3;
                            }
                        }
                        Date parseDate = AppUtils.parseDate(latestHRVRecordHourly.getmDate() + ' ' + latestHRVRecordHourly.getStartTime(), "yyyy-MM-dd HH:mm:ss");
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(parseDate);
                        calendar.add(12, size * i2);
                        MainCoroutineDispatcher main = Dispatchers.getMain();
                        C0276a c0276a = new C0276a(VitalHRVPeriodicFragment.this, doubleRef, null);
                        this.label = 1;
                        if (BuildersKt.withContext(main, c0276a, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        VitalHRVPeriodicFragment vitalHRVPeriodicFragment = VitalHRVPeriodicFragment.this;
                        this.label = 2;
                        if (vitalHRVPeriodicFragment.M(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else if (i == 1 || i == 2) {
                    ResultKt.throwOnFailure(obj);
                } else if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
                VitalHRVPeriodicFragment vitalHRVPeriodicFragment2 = VitalHRVPeriodicFragment.this;
                this.label = 3;
                if (vitalHRVPeriodicFragment2.M(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalHRVPeriodicFragment$handleSelectedDate$1", f = "VitalsHRVPeriodicFragment.kt", i = {}, l = {379}, m = "invokeSuspend", n = {}, s = {})
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
                VitalHRVPeriodicFragment vitalHRVPeriodicFragment = VitalHRVPeriodicFragment.this;
                Calendar calendar = vitalHRVPeriodicFragment.o;
                String macAddress = BleApiManager.getInstance(VitalHRVPeriodicFragment.this.getContext()).getBleApi().getMacAddress();
                this.label = 1;
                if (vitalHRVPeriodicFragment.v(calendar, macAddress, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalHRVPeriodicFragment$setTextValueEmpty$2", f = "VitalsHRVPeriodicFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public c(Continuation<? super c> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(continuation);
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
                if (VitalHRVPeriodicFragment.this.u().vitalsMainData.tvAvgStepsSleepValue != null) {
                    VitalHRVPeriodicFragment.this.u().vitalsMainData.tvAvgStepsSleepValue.setText(String.valueOf(AppConstants.TWO_DASH_STRING.getValue()));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public VitalHRVPeriodicFragment() {
        AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this.o = calendar;
        this.t = 1;
        this.u = 2;
    }

    public static final void B(VitalHRVPeriodicFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.r;
        if (i == this$0.s) {
            this$0.J();
            this$0.getDatePickerDialog().getDatePicker().setMaxDate(System.currentTimeMillis());
            this$0.getDatePickerDialog().show();
        } else if (i == this$0.t) {
            this$0.v = true;
            this$0.N();
        } else {
            this$0.v = false;
            this$0.N();
        }
    }

    public static final void C(VitalHRVPeriodicFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.r;
        if (i == this$0.s) {
            this$0.x(PayUtils.INSTANCE.getPreviousDayCalendar(this$0.o));
            return;
        }
        Pair<? extends Calendar, ? extends Calendar> pair = null;
        if (i == this$0.t) {
            if (this$0.p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            }
            Pair<? extends Calendar, ? extends Calendar> pair2 = this$0.p;
            if (pair2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair2 = null;
            }
            Object clone = pair2.getFirst().clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar = (Calendar) clone;
            calendar.add(5, -6);
            Pair<? extends Calendar, ? extends Calendar> pair3 = this$0.p;
            if (pair3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair3;
            }
            Object clone2 = pair.getFirst().clone();
            Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar2 = (Calendar) clone2;
            this$0.p = new Pair<>(calendar, calendar2);
            this$0.w(calendar, calendar2);
            this$0.O();
        } else if (i == this$0.u) {
            Pair<? extends Calendar, ? extends Calendar> pair4 = this$0.p;
            if (pair4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair4 = null;
            }
            Object clone3 = pair4.getFirst().clone();
            Intrinsics.checkNotNull(clone3, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar3 = (Calendar) clone3;
            calendar3.add(5, -30);
            Pair<? extends Calendar, ? extends Calendar> pair5 = this$0.p;
            if (pair5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair5;
            }
            Object clone4 = pair.getFirst().clone();
            Intrinsics.checkNotNull(clone4, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar4 = (Calendar) clone4;
            this$0.p = new Pair<>(calendar3, calendar4);
            this$0.w(calendar3, calendar4);
            this$0.O();
        }
    }

    public static final void D(VitalHRVPeriodicFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.r;
        if (i == this$0.s) {
            if (DateUtils.isToday(this$0.o.getTimeInMillis())) {
                return;
            }
            this$0.x(PayUtils.INSTANCE.getNextDayCalendar(this$0.o));
            return;
        }
        Pair<? extends Calendar, ? extends Calendar> pair = null;
        if (i == this$0.t) {
            Pair<? extends Calendar, ? extends Calendar> pair2 = this$0.p;
            if (pair2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair2 = null;
            }
            if (DateUtils.isToday(pair2.getSecond().getTimeInMillis())) {
                return;
            }
            Pair<? extends Calendar, ? extends Calendar> pair3 = this$0.p;
            if (pair3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair3 = null;
            }
            Object clone = pair3.getSecond().clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar = (Calendar) clone;
            Pair<? extends Calendar, ? extends Calendar> pair4 = this$0.p;
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
                this$0.p = new Pair<>(calendar, calendar2);
                this$0.w(calendar, calendar2);
                this$0.O();
                return;
            }
            int findDateDifference = AppUtils.findDateDifference(Calendar.getInstance().getTime(), calendar2.getTime());
            Object clone3 = calendar.clone();
            Intrinsics.checkNotNull(clone3, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar3 = (Calendar) clone3;
            calendar3.add(5, 6 - findDateDifference);
            this$0.p = new Pair<>(calendar, calendar3);
            this$0.w(calendar, calendar3);
            this$0.O();
        } else if (i == this$0.u) {
            Pair<? extends Calendar, ? extends Calendar> pair5 = this$0.p;
            if (pair5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair5 = null;
            }
            if (DateUtils.isToday(pair5.getSecond().getTimeInMillis())) {
                return;
            }
            Pair<? extends Calendar, ? extends Calendar> pair6 = this$0.p;
            if (pair6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair6 = null;
            }
            Object clone4 = pair6.getSecond().clone();
            Intrinsics.checkNotNull(clone4, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar4 = (Calendar) clone4;
            Pair<? extends Calendar, ? extends Calendar> pair7 = this$0.p;
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
                this$0.p = new Pair<>(calendar4, calendar5);
                this$0.w(calendar4, calendar5);
                this$0.O();
                return;
            }
            int findDateDifference2 = AppUtils.findDateDifference(Calendar.getInstance().getTime(), calendar5.getTime());
            Object clone6 = calendar4.clone();
            Intrinsics.checkNotNull(clone6, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar6 = (Calendar) clone6;
            calendar6.add(5, 30 - findDateDifference2);
            this$0.p = new Pair<>(calendar4, calendar6);
            this$0.w(calendar4, calendar6);
            this$0.O();
        }
    }

    public static final void E(VitalHRVPeriodicFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.share();
    }

    public static final void F(VitalHRVPeriodicFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.r = this$0.s;
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this$0.x(calendar);
        this$0.O();
    }

    public static final void G(VitalHRVPeriodicFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.r = this$0.t;
        Calendar startDate = Calendar.getInstance();
        startDate.add(5, -6);
        Calendar endDate = Calendar.getInstance();
        this$0.O();
        this$0.p = new Pair<>(startDate, endDate);
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        Intrinsics.checkNotNullExpressionValue(endDate, "endDate");
        this$0.w(startDate, endDate);
    }

    public static final void H(VitalHRVPeriodicFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.r = this$0.u;
        Calendar startDate = Calendar.getInstance();
        startDate.add(5, -30);
        Calendar endDate = Calendar.getInstance();
        this$0.O();
        this$0.p = new Pair<>(startDate, endDate);
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        Intrinsics.checkNotNullExpressionValue(endDate, "endDate");
        this$0.w(startDate, endDate);
    }

    public static final void K(VitalHRVPeriodicFragment this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i, i2, i3);
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        this$0.x(newDate);
    }

    public static final void y(final FragmentVitalHrvBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this_apply.nestedScrollStress.post(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.j
            @Override // java.lang.Runnable
            public final void run() {
                VitalHRVPeriodicFragment.z(FragmentVitalHrvBinding.this);
            }
        });
    }

    public static final void z(FragmentVitalHrvBinding this_apply) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this_apply.nestedScrollStress.fullScroll(130);
    }

    public final void A() {
        FragmentVitalHrvBinding u = u();
        u.vitalsMainData.tvSelectedTypeValue.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalHRVPeriodicFragment.B(VitalHRVPeriodicFragment.this, view);
            }
        });
        u.vitalsMainData.ibPrevious.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalHRVPeriodicFragment.C(VitalHRVPeriodicFragment.this, view);
            }
        });
        u.vitalsMainData.ibForward.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalHRVPeriodicFragment.D(VitalHRVPeriodicFragment.this, view);
            }
        });
        u.vitalsMainData.ibShareVitals.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalHRVPeriodicFragment.E(VitalHRVPeriodicFragment.this, view);
            }
        });
        u.vitalsMainData.tvDay.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalHRVPeriodicFragment.F(VitalHRVPeriodicFragment.this, view);
            }
        });
        u.vitalsMainData.tvWeek.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalHRVPeriodicFragment.G(VitalHRVPeriodicFragment.this, view);
            }
        });
        u.vitalsMainData.tvMonth.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalHRVPeriodicFragment.H(VitalHRVPeriodicFragment.this, view);
            }
        });
    }

    public final void I(ArrayList<Entry> arrayList, ArrayList<String> arrayList2) {
        ArrayList arrayList3 = new ArrayList();
        Intrinsics.checkNotNull(arrayList);
        Iterator<Entry> it = arrayList.iterator();
        float f = -1.0f;
        while (it.hasNext()) {
            Entry next = it.next();
            if (next.getY() < 1.0f) {
                arrayList3.add(next);
            } else if (f < next.getY()) {
                f = next.getY();
            }
        }
        arrayList.removeAll(arrayList3);
        LineDataSet lineDataSet = new LineDataSet(arrayList, "");
        lineDataSet.setDrawValues(false);
        lineDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        lineDataSet.setColor(getResources().getColor(R.color.main_text_color));
        lineDataSet.setHighLightColor(getResources().getColor(17170445));
        if (lineDataSet.getEntryCount() > 0) {
            int entryCount = lineDataSet.getEntryCount();
            int[] iArr = new int[entryCount];
            for (int i = 0; i < entryCount; i++) {
                iArr[i] = getResources().getColor(R.color.high_stress_color);
            }
            iArr[entryCount - 1] = getResources().getColor(R.color.high_stress_color);
            lineDataSet.setColors(Arrays.copyOf(iArr, entryCount));
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setCircleColors(Arrays.copyOf(iArr, entryCount));
        }
        LineDataSet lineDataSet2 = new LineDataSet(arrayList3, "");
        lineDataSet2.setDrawValues(false);
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setValueTextColor(getResources().getColor(R.color.transparent));
        lineDataSet2.setColor(getResources().getColor(R.color.transparent));
        lineDataSet2.setCircleColor(getResources().getColor(R.color.transparent));
        lineDataSet2.setHighLightColor(getResources().getColor(17170445));
        if (lineDataSet2.getEntryCount() > 0) {
            int entryCount2 = lineDataSet2.getEntryCount();
            int[] iArr2 = new int[entryCount2];
            for (int i2 = 0; i2 < entryCount2; i2++) {
                iArr2[i2] = getResources().getColor(R.color.main_text_color);
            }
            iArr2[entryCount2 - 1] = getResources().getColor(17170445);
            lineDataSet2.setColors(Arrays.copyOf(iArr2, entryCount2));
            lineDataSet2.setCircleColors(Arrays.copyOf(iArr2, entryCount2));
        }
        lineDataSet2.setCircleRadius(0.0f);
        LineData lineData = new LineData(lineDataSet, lineDataSet2);
        u().lineChartHRV.getXAxis().setValueFormatter(new IndexAxisValueFormatter(arrayList2));
        lineData.setDrawValues(false);
        u().lineChartHRV.setData(lineData);
        u().lineChartHRV.setDrawGridBackground(false);
        u().lineChartHRV.getDescription().setEnabled(false);
        lineData.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        u().lineChartHRV.getPaint(7).setColor(getResources().getColor(R.color.secondary_text_color));
        u().lineChartHRV.setDrawBorders(false);
        u().lineChartHRV.setAutoScaleMinMaxEnabled(false);
        u().lineChartHRV.setPinchZoom(false);
        YAxis axisLeft = u().lineChartHRV.getAxisLeft();
        axisLeft.setEnabled(true);
        axisLeft.setDrawAxisLine(true);
        axisLeft.setDrawGridLines(false);
        axisLeft.mAxisMaximum = 150.0f;
        axisLeft.setAxisLineWidth(1.0f);
        axisLeft.setAxisLineColor(getResources().getColor(R.color.secondary_text_color));
        u().lineChartHRV.getAxisRight().setEnabled(false);
        XAxis xAxis = u().lineChartHRV.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineWidth(1.0f);
        xAxis.setAxisLineColor(getResources().getColor(R.color.secondary_text_color));
        u().lineChartHRV.getAxisLeft().setStartAtZero(true);
        u().lineChartHRV.getAxisRight().setStartAtZero(true);
        u().lineChartHRV.getAxisLeft().setTextColor(getResources().getColor(R.color.secondary_text_color));
        u().lineChartHRV.getXAxis().setTextColor(getResources().getColor(R.color.secondary_text_color));
        u().lineChartHRV.getLegend().setTextColor(getResources().getColor(R.color.secondary_text_color));
        u().lineChartHRV.getLegend().setEnabled(false);
        u().lineChartHRV.setVisibleXRangeMinimum(5.0f);
        Context context = getContext();
        String string = getString(R.string.hrv);
        Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…energyscore.R.string.hrv)");
        CustomMarkerViewVitals customMarkerViewVitals = new CustomMarkerViewVitals(context, R.layout.custom_marker_view_steps_hr, string, 0, arrayList2);
        customMarkerViewVitals.setChartView(u().lineChartHRV);
        u().lineChartHRV.setMarker(customMarkerViewVitals);
        u().lineChartHRV.setPinchZoom(false);
        u().lineChartHRV.setAutoScaleMinMaxEnabled(false);
        u().lineChartHRV.setScaleEnabled(false);
        u().lineChartHRV.setDoubleTapToZoomEnabled(false);
        u().lineChartHRV.invalidate();
    }

    public final void J() {
        Calendar calendar = this.o;
        setDatePickerDialog(new DatePickerDialog(requireActivity(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.a
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                VitalHRVPeriodicFragment.K(VitalHRVPeriodicFragment.this, datePicker, i, i2, i3);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)));
    }

    public final void L(ArrayList<Entry> arrayList, ArrayList<Entry> arrayList2, ArrayList<String> arrayList3) {
        ArrayList arrayList4 = new ArrayList();
        Intrinsics.checkNotNull(arrayList);
        Iterator<Entry> it = arrayList.iterator();
        float f = -1.0f;
        while (it.hasNext()) {
            Entry next = it.next();
            if (next.getY() < 1.0f) {
                arrayList4.add(next);
            } else if (f < next.getY()) {
                f = next.getY();
            }
        }
        arrayList.removeAll(arrayList4);
        if (!AppUtils.isEmpty(arrayList2)) {
            ArrayList arrayList5 = new ArrayList();
            Intrinsics.checkNotNull(arrayList2);
            Iterator<Entry> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                Entry next2 = it2.next();
                if (next2.getY() <= 1.0f) {
                    arrayList5.add(next2);
                } else if (f < next2.getY()) {
                    f = next2.getY();
                }
            }
            arrayList2.removeAll(arrayList5);
        }
        LineDataSet lineDataSet = new LineDataSet(arrayList, "");
        lineDataSet.setDrawValues(false);
        lineDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        lineDataSet.setColor(getResources().getColor(R.color.high_stress_color));
        lineDataSet.setHighLightColor(getResources().getColor(17170445));
        if (lineDataSet.getEntryCount() > 0) {
            int entryCount = lineDataSet.getEntryCount();
            int[] iArr = new int[entryCount];
            for (int i = 0; i < entryCount; i++) {
                iArr[i] = getResources().getColor(R.color.high_stress_color);
            }
            iArr[entryCount - 1] = getResources().getColor(R.color.high_stress_color);
            lineDataSet.setColors(Arrays.copyOf(iArr, entryCount));
            lineDataSet.setCircleColors(Arrays.copyOf(iArr, entryCount));
            lineDataSet.setDrawCircleHole(false);
        }
        LineDataSet lineDataSet2 = new LineDataSet(arrayList2, "");
        lineDataSet2.setDrawValues(false);
        lineDataSet2.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        lineDataSet2.setColor(getResources().getColor(R.color.relaxed_stress_color));
        lineDataSet2.setHighLightColor(getResources().getColor(17170445));
        if (lineDataSet2.getEntryCount() > 0) {
            int entryCount2 = lineDataSet2.getEntryCount();
            int[] iArr2 = new int[entryCount2];
            for (int i2 = 0; i2 < entryCount2; i2++) {
                iArr2[i2] = getResources().getColor(R.color.color_29ffffff);
            }
            iArr2[entryCount2 - 1] = getResources().getColor(R.color.relaxed_stress_color);
            lineDataSet2.setColors(Arrays.copyOf(iArr2, entryCount2));
        }
        LineData lineData = new LineData(lineDataSet, lineDataSet2);
        u().lineChartHRV.getXAxis().setValueFormatter(new IndexAxisValueFormatter(arrayList3));
        lineData.setDrawValues(false);
        u().lineChartHRV.setData(lineData);
        u().lineChartHRV.setDrawGridBackground(false);
        u().lineChartHRV.getDescription().setEnabled(false);
        lineData.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        u().lineChartHRV.getPaint(7).setColor(getResources().getColor(R.color.text_color_dark));
        u().lineChartHRV.setDrawBorders(false);
        u().lineChartHRV.setAutoScaleMinMaxEnabled(false);
        YAxis axisLeft = u().lineChartHRV.getAxisLeft();
        axisLeft.setEnabled(true);
        axisLeft.setDrawAxisLine(true);
        axisLeft.setDrawGridLines(false);
        axisLeft.mAxisMaximum = 150.0f + 1;
        axisLeft.setAxisLineWidth(1.0f);
        axisLeft.setAxisLineColor(getResources().getColor(R.color.secondary_text_color));
        u().lineChartHRV.getAxisRight().setEnabled(false);
        XAxis xAxis = u().lineChartHRV.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineWidth(1.0f);
        xAxis.setAxisLineColor(getResources().getColor(R.color.secondary_text_color));
        u().lineChartHRV.getAxisLeft().setStartAtZero(true);
        u().lineChartHRV.getAxisRight().setStartAtZero(true);
        u().lineChartHRV.getAxisLeft().setTextColor(getResources().getColor(R.color.secondary_text_color));
        u().lineChartHRV.getXAxis().setTextColor(getResources().getColor(R.color.secondary_text_color));
        u().lineChartHRV.getLegend().setTextColor(getResources().getColor(R.color.secondary_text_color));
        u().lineChartHRV.getLegend().setEnabled(false);
        u().lineChartHRV.setVisibleXRangeMinimum(5.0f);
        u().lineChartHRV.setVisibleXRangeMaximum(6.0f);
        u().lineChartHRV.setPinchZoom(false);
        u().lineChartHRV.setAutoScaleMinMaxEnabled(false);
        u().lineChartHRV.setScaleEnabled(false);
        u().lineChartHRV.setDoubleTapToZoomEnabled(false);
        u().lineChartHRV.moveViewToX(arrayList.size() - 1);
        u().lineChartHRV.getAxisLeft().removeAllLimitLines();
        Context context = getContext();
        String string = getString(R.string.hrv);
        Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…energyscore.R.string.hrv)");
        CustomMarkerViewVitals customMarkerViewVitals = new CustomMarkerViewVitals(context, R.layout.custom_marker_view_steps_hr, string, this.r, arrayList3);
        customMarkerViewVitals.setChartView(u().lineChartHRV);
        u().lineChartHRV.setMarker(customMarkerViewVitals);
        u().lineChartHRV.invalidate();
        u().lineChartHRV.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalHRVPeriodicFragment$setRangeBarGraphValues$1
            @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
            public void onNothingSelected() {
            }

            @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
            public void onValueSelected(@NotNull Entry e, @NotNull Highlight h) {
                Intrinsics.checkNotNullParameter(e, "e");
                Intrinsics.checkNotNullParameter(h, "h");
            }
        });
        u().lineChartHRV.setVisibleXRangeMaximum(5.0f);
        u().lineChartHRV.setVisibleXRangeMinimum(5.0f);
    }

    public final Object M(Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getMain(), new c(null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final void N() {
        if (isAdded()) {
            boolean z = this.v;
            Pair<? extends Calendar, ? extends Calendar> pair = this.p;
            if (pair == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair = null;
            }
            CalendarRangeDialog calendarRangeDialog = new CalendarRangeDialog(z, pair, this);
            this.q = calendarRangeDialog;
            calendarRangeDialog.show(getChildFragmentManager(), "");
        }
    }

    public final void O() {
        if (isAdded()) {
            int i = this.r;
            if (i == this.s) {
                u().vitalsMainData.tvDay.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                u().vitalsMainData.tvDay.setTextColor(requireContext().getColor(R.color.main_text_color));
                u().vitalsMainData.tvWeek.setBackground(null);
                u().vitalsMainData.tvWeek.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                u().vitalsMainData.tvMonth.setBackground(null);
                u().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                u().vitalsMainData.tvDay.setTextAppearance(R.style.bold);
                u().vitalsMainData.tvWeek.setTextAppearance(R.style.regular);
                u().vitalsMainData.tvMonth.setTextAppearance(R.style.regular);
            } else if (i == this.t) {
                u().vitalsMainData.tvDay.setBackground(null);
                u().vitalsMainData.tvDay.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                u().vitalsMainData.tvWeek.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                u().vitalsMainData.tvWeek.setTextColor(requireContext().getColor(R.color.main_text_color));
                u().vitalsMainData.tvMonth.setBackground(null);
                u().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                u().vitalsMainData.tvDay.setTextAppearance(R.style.regular);
                u().vitalsMainData.tvWeek.setTextAppearance(R.style.bold);
                u().vitalsMainData.tvMonth.setTextAppearance(R.style.regular);
            } else {
                u().vitalsMainData.tvDay.setBackground(null);
                u().vitalsMainData.tvDay.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                u().vitalsMainData.tvWeek.setBackground(null);
                u().vitalsMainData.tvWeek.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                u().vitalsMainData.tvMonth.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                u().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(R.color.main_text_color));
                u().vitalsMainData.tvDay.setTextAppearance(R.style.regular);
                u().vitalsMainData.tvWeek.setTextAppearance(R.style.regular);
                u().vitalsMainData.tvMonth.setTextAppearance(R.style.bold);
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

    @Nullable
    public final DailyHRV getMDailyHRV() {
        return this.w;
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
        FragmentHRVPeriodicViewModel fragmentHRVPeriodicViewModel = (FragmentHRVPeriodicViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FragmentHRVPeriodicViewModel.class);
        this.n = fragmentHRVPeriodicViewModel;
        if (fragmentHRVPeriodicViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentHRVPeriodicViewModel = null;
        }
        fragmentHRVPeriodicViewModel.setContractPeriodicHRVDashboard$app_prodRelease(this);
        FragmentHRVPeriodicViewModel fragmentHRVPeriodicViewModel2 = this.n;
        if (fragmentHRVPeriodicViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentHRVPeriodicViewModel2 = null;
        }
        fragmentHRVPeriodicViewModel2.setMLifecycleOwner(this);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        setAutoStressSettingsViewModel((AutoStressSettingsViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext2)).get(AutoStressSettingsViewModel.class));
        setAutoStressSettingsDataCommon(getAutoStressSettingsViewModel().getStressSettingsDataFromPref());
        SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
        Intrinsics.checkNotNullExpressionValue(simpleDateFormat2, "getSimpleDateFormat(\"dd MMMM yyyy\")");
        setSimpleDateFormat(simpleDateFormat2);
        String string = getString(R.string.enable_auto_stress_amp_hrv_monitoring_under_settings_to_measure_your_values);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.enabl…s_to_measure_your_values)");
        SpannableString spannableString = new SpannableString(string);
        Context context = getContext();
        Integer valueOf = context != null ? Integer.valueOf(context.getColor(R.color.colorAccent)) : null;
        Intrinsics.checkNotNull(valueOf);
        spannableString.setSpan(new ForegroundColorSpan(valueOf.intValue()), StringsKt__StringsKt.indexOf$default((CharSequence) string, "Watch features", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) string, "Watch features", 0, false, 6, (Object) null) + 14, 34);
        spannableString.setSpan(new UnderlineSpan(), StringsKt__StringsKt.indexOf$default((CharSequence) string, "Watch features", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) string, "Watch features", 0, false, 6, (Object) null) + 14, 34);
        spannableString.setSpan(new ClickableSpan() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalHRVPeriodicFragment$initData$clickableSpan$1
            @Override // android.text.style.ClickableSpan
            public void onClick(@NotNull View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                AppNavigator.Companion companion = AppNavigator.Companion;
                Context context2 = VitalHRVPeriodicFragment.this.getContext();
                Intrinsics.checkNotNull(context2);
                companion.navigateToAutoStressHRVSettings(context2);
            }
        }, StringsKt__StringsKt.indexOf$default((CharSequence) string, "Watch features", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) string, "Watch features", 0, false, 6, (Object) null) + 14, 34);
        u().tvEnableHrvSettings.setText(spannableString, TextView.BufferType.SPANNABLE);
        u().tvEnableHrvSettings.setMovementMethod(LinkMovementMethod.getInstance());
        this.r = this.s;
        final FragmentVitalHrvBinding u = u();
        u.vitalsMainData.tvVitalInfo.setVisibility(8);
        u.vitalsMainData.clCenterDataStepsSleep.setVisibility(0);
        u.vitalsMainData.tvVitalName.setText(getString(R.string.heart_rate_variability));
        u.vitalsMainData.clMinData.setVisibility(4);
        u.vitalsMainData.clMaxData.setVisibility(4);
        u.vitalsMainData.ivCenterVital.setImageResource(R.drawable.ic_hrv);
        TextView textView = u.vitalsMainData.tvLastSyncTime;
        PayUtils payUtils = PayUtils.INSTANCE;
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        textView.setText(payUtils.getLastSyncTime(requireContext3));
        String string2 = getString(R.string.medical_disclaimer_info);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.medical_disclaimer_info)");
        SpannableString spannableString2 = new SpannableString(string2);
        Context context2 = getContext();
        Integer valueOf2 = context2 != null ? Integer.valueOf(context2.getColor(R.color.color_b3b3b3)) : null;
        Intrinsics.checkNotNull(valueOf2);
        spannableString2.setSpan(new ForegroundColorSpan(valueOf2.intValue()), 0, 12, 18);
        u.tvdisclaimer.setText(spannableString2);
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        TextView tvhrvdescription = u.tvhrvdescription;
        Intrinsics.checkNotNullExpressionValue(tvhrvdescription, "tvhrvdescription");
        themesUtils.makeTextViewResizable(tvhrvdescription, 3, "... Read More", true);
        u.vitalsMainData.tvVitalName.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalHRVPeriodicFragment.y(FragmentVitalHrvBinding.this, view);
            }
        });
        A();
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        x(calendar);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicHRVDashboard
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
        this.p = new Pair<>(fromDate, toDate);
        w(fromDate, toDate);
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentVitalHrvBinding.inflate(inflater, viewGroup, false);
        View root = u().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicHRVDashboard
    public void onRangeBarChartLoaded(@Nullable ArrayList<Entry> arrayList, @Nullable ArrayList<Entry> arrayList2, @Nullable ArrayList<String> arrayList3, double d) {
        if (!AppUtils.isEmpty(arrayList)) {
            if (!(d == 0.0d)) {
                TextView textView = u().vitalsMainData.tvAvgStepsSleepValue;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.ENGLISH;
                String string = getString(R.string.hrv_spannable);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.hrv_spannable)");
                String format = String.format("%.02f", Arrays.copyOf(new Object[]{Double.valueOf(PayUtils.INSTANCE.calculationFormulaHRV(d))}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                String format2 = String.format(locale, string, Arrays.copyOf(new Object[]{format}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                textView.setText(Html.fromHtml(format2), TextView.BufferType.SPANNABLE);
            } else {
                u().vitalsMainData.tvAvgStepsSleepValue.setText(String.valueOf(AppConstants.TWO_DASH_STRING.getValue()));
            }
            Intrinsics.checkNotNull(arrayList3);
            L(arrayList, arrayList2, arrayList3);
            u().lineChartHRV.setVisibility(0);
            u().legendLayout.setVisibility(0);
            u().tvNoDataFound.setVisibility(8);
            return;
        }
        u().lineChartHRV.setVisibility(4);
        u().tvNoDataFound.setVisibility(0);
        u().legendLayout.setVisibility(8);
        u().vitalsMainData.tvAvgStepsSleepValue.setText(String.valueOf(AppConstants.TWO_DASH_STRING.getValue()));
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initData();
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

    public final void setMDailyHRV(@Nullable DailyHRV dailyHRV) {
        this.w = dailyHRV;
    }

    public final void setSimpleDateFormat(@NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
        this.simpleDateFormat = simpleDateFormat;
    }

    public final void share() {
        ShareData shareData = new ShareData();
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM, yyyy");
        shareData.setData(u().vitalsMainData.tvAvgStepsSleepValue.getText().toString());
        int i = this.r;
        if (i == this.s) {
            shareData.setGraphType(getString(R.string.share_daily));
            shareData.setDwmValue(simpleDateFormat.format(this.o.getTime()));
        } else {
            Pair<? extends Calendar, ? extends Calendar> pair = null;
            if (i == this.t) {
                shareData.setGraphType(getString(R.string.share_weekly));
                StringBuilder sb = new StringBuilder();
                Pair<? extends Calendar, ? extends Calendar> pair2 = this.p;
                if (pair2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                    pair2 = null;
                }
                sb.append(simpleDateFormat.format(pair2.getFirst().getTime()));
                sb.append(Soundex.SILENT_MARKER);
                Pair<? extends Calendar, ? extends Calendar> pair3 = this.p;
                if (pair3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                } else {
                    pair = pair3;
                }
                sb.append(simpleDateFormat.format(pair.getSecond().getTime()));
                shareData.setDwmValue(sb.toString());
            } else if (i == this.u) {
                shareData.setGraphType(getString(R.string.share_monthly));
                StringBuilder sb2 = new StringBuilder();
                Pair<? extends Calendar, ? extends Calendar> pair4 = this.p;
                if (pair4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                    pair4 = null;
                }
                sb2.append(simpleDateFormat.format(pair4.getFirst().getTime()));
                sb2.append(Soundex.SILENT_MARKER);
                Pair<? extends Calendar, ? extends Calendar> pair5 = this.p;
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
        String string = getResources().getString(R.string.hrv);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.hrv)");
        companion.navigateToShareScreen(requireContext, shareData, string);
    }

    public final FragmentVitalHrvBinding u() {
        FragmentVitalHrvBinding fragmentVitalHrvBinding = this.m;
        Intrinsics.checkNotNull(fragmentVitalHrvBinding);
        return fragmentVitalHrvBinding;
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicHRVDashboard
    public void updateDailyLevelData(@Nullable DailyHRV dailyHRV) {
        this.w = dailyHRV;
        u().lineChartHRV.getAxisLeft().removeAllLimitLines();
        if (dailyHRV == null || dailyHRV.baselinne <= 0.0d || !isAdded()) {
            return;
        }
        LimitLine limitLine = new LimitLine((float) PayUtils.INSTANCE.calculationFormulaHRV(dailyHRV.baselinne), "Baseline");
        limitLine.setLineColor(Color.parseColor("#29ffffff"));
        Context context = getContext();
        Integer valueOf = context != null ? Integer.valueOf(context.getColor(R.color.secondary_text_color)) : null;
        Intrinsics.checkNotNull(valueOf);
        limitLine.setTextColor(valueOf.intValue());
        limitLine.enableDashedLine(30.0f, 5.0f, 2.0f);
        u().lineChartHRV.getAxisLeft().addLimitLine(limitLine);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicHRVDashboard
    public void updateDailyLevelStressData(@Nullable DailyStress dailyStress) {
        if (dailyStress != null && dailyStress.readiness > 0.0d) {
            u().readinessScoreBar.setCircleProgressColorWithoutGlow(getResources().getColor(R.color.gradient_green_end_color));
            int i = (int) dailyStress.readiness;
            FragmentHRVPeriodicViewModel fragmentHRVPeriodicViewModel = this.n;
            if (fragmentHRVPeriodicViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentHRVPeriodicViewModel = null;
            }
            u().tvMorningReadinessDynamicTxt.setText(fragmentHRVPeriodicViewModel.getReadinessDynamicText(i).getSecond());
            u().readinessScoreBar.setProgress((int) ((i / 10.0f) * 100));
            u().tvReadinessValue.setText(String.valueOf(i));
            u().readinessLayout.setVisibility(0);
            return;
        }
        u().tvMorningReadinessDynamicTxt.setText(requireContext().getString(R.string.morning_readiness_desc));
        u().tvReadinessValue.setText(BleConst.GetDeviceTime);
        u().readinessScoreBar.setProgress(0);
        u().readinessLayout.setVisibility(0);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicHRVDashboard
    public void updateHourlyLevelData(@Nullable ArrayList<Entry> arrayList, @Nullable ArrayList<String> arrayList2) {
        if (arrayList != null) {
            Iterator<Entry> it = arrayList.iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (it.next().getY() > 0.0f) {
                    z = true;
                }
            }
            if (z) {
                u().tvEnableHrvSettings.setVisibility(8);
                u().lineChartHRV.setVisibility(0);
                u().tvNoDataFound.setVisibility(8);
                u().legendLayout.setVisibility(0);
                if (arrayList2 != null) {
                    I(arrayList, arrayList2);
                    return;
                }
                return;
            }
            if (!getAutoStressSettingsDataCommon().getAutoStress()) {
                u().tvEnableHrvSettings.setVisibility(0);
            } else {
                u().tvEnableHrvSettings.setVisibility(8);
            }
            u().lineChartHRV.setVisibility(8);
            u().tvNoDataFound.setVisibility(0);
            u().legendLayout.setVisibility(8);
            return;
        }
        if (!getAutoStressSettingsDataCommon().getAutoStress()) {
            u().tvEnableHrvSettings.setVisibility(0);
        } else {
            u().tvEnableHrvSettings.setVisibility(8);
        }
        u().lineChartHRV.setVisibility(8);
        u().tvNoDataFound.setVisibility(0);
        u().legendLayout.setVisibility(8);
    }

    public final Object v(Calendar calendar, String str, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new a(calendar, str, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final void w(Calendar calendar, Calendar calendar2) {
        if (isAdded()) {
            if (DateUtils.isToday(calendar2.getTimeInMillis())) {
                u().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                u().vitalsMainData.ibForward.setEnabled(false);
            } else {
                u().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                u().vitalsMainData.ibForward.setEnabled(true);
            }
            TextView textView = u().vitalsMainData.tvSelectedTypeValue;
            StringBuilder sb = new StringBuilder();
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            sb.append(themesUtils.getDateFormatDDMMMYYYY(calendar));
            sb.append(" - ");
            sb.append(themesUtils.getDateFormatDDMMMYYYY(calendar2));
            textView.setText(sb.toString());
            FragmentHRVPeriodicViewModel fragmentHRVPeriodicViewModel = this.n;
            if (fragmentHRVPeriodicViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentHRVPeriodicViewModel = null;
            }
            fragmentHRVPeriodicViewModel.selectRangeView(calendar, calendar2);
        }
    }

    public final void x(Calendar calendar) {
        if (isAdded()) {
            TextView textView = u().vitalsMainData.tvSelectedTypeValue;
            long timeInMillis = calendar.getTimeInMillis();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            textView.setText(PayUtils.getTodayYesterdayStringFromTimeStamp(timeInMillis, requireContext));
            if (DateUtils.isToday(calendar.getTimeInMillis())) {
                u().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                u().vitalsMainData.ibForward.setEnabled(false);
            } else {
                u().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                u().vitalsMainData.ibForward.setEnabled(true);
            }
            FragmentHRVPeriodicViewModel fragmentHRVPeriodicViewModel = this.n;
            if (fragmentHRVPeriodicViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentHRVPeriodicViewModel = null;
            }
            fragmentHRVPeriodicViewModel.loadHourlyHRVData(calendar);
            FragmentHRVPeriodicViewModel fragmentHRVPeriodicViewModel2 = this.n;
            if (fragmentHRVPeriodicViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentHRVPeriodicViewModel2 = null;
            }
            fragmentHRVPeriodicViewModel2.loadDailyData(calendar);
            FragmentHRVPeriodicViewModel fragmentHRVPeriodicViewModel3 = this.n;
            if (fragmentHRVPeriodicViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentHRVPeriodicViewModel3 = null;
            }
            fragmentHRVPeriodicViewModel3.loadDailyStressData(calendar);
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, null, null, new b(null), 3, null);
            this.o = calendar;
        }
    }
}
