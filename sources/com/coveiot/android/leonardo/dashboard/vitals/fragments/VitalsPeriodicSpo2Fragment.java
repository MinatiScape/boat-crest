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
import com.coveiot.android.boat.databinding.FragmentVitalPeriodicSpo2Binding;
import com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicSpo2;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentPeriodicSpo2ViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.compundview.CustomMarkerViewVitals;
import com.coveiot.covedb.spo2.entity.DailyPeriodicSpo2;
import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.LatestHealthDataModel;
import com.coveiot.repository.periodicspo2.PeriodicSpo2Repository;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
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
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class VitalsPeriodicSpo2Fragment extends BaseFragment implements ContractPeriodicSpo2 {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public DatePickerDialog datePickerDialog;
    @Nullable
    public FragmentVitalPeriodicSpo2Binding m;
    public FragmentPeriodicSpo2ViewModel n;
    @NotNull
    public Calendar o;
    public float p;
    public SimpleDateFormat simpleDateFormat;

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final VitalsPeriodicSpo2Fragment newInstance() {
            return new VitalsPeriodicSpo2Fragment();
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsPeriodicSpo2Fragment$getLatestPeriodicSpo2ValueWithTimestamp$2", f = "VitalsPeriodicSpo2Fragment.kt", i = {}, l = {com.veryfit.multi.nativeprotocol.b.t1, 369, 376, 380, 396, 401, com.veryfit.multi.nativeprotocol.b.C1}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Calendar $calendar;
        public final /* synthetic */ String $serialNo;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsPeriodicSpo2Fragment$getLatestPeriodicSpo2ValueWithTimestamp$2$1", f = "VitalsPeriodicSpo2Fragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsPeriodicSpo2Fragment$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        public static final class C0278a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Ref.ObjectRef<LatestHealthDataModel> $latestSpo2DataModel;
            public int label;
            public final /* synthetic */ VitalsPeriodicSpo2Fragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0278a(VitalsPeriodicSpo2Fragment vitalsPeriodicSpo2Fragment, Ref.ObjectRef<LatestHealthDataModel> objectRef, Continuation<? super C0278a> continuation) {
                super(2, continuation);
                this.this$0 = vitalsPeriodicSpo2Fragment;
                this.$latestSpo2DataModel = objectRef;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0278a(this.this$0, this.$latestSpo2DataModel, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0278a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.p().vitalsMainData.tvAvgHRVSpo2Value.setText(String.valueOf(this.$latestSpo2DataModel.element.getValue()));
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsPeriodicSpo2Fragment$getLatestPeriodicSpo2ValueWithTimestamp$2$2", f = "VitalsPeriodicSpo2Fragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes4.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Ref.IntRef $spo2Val;
            public int label;
            public final /* synthetic */ VitalsPeriodicSpo2Fragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(VitalsPeriodicSpo2Fragment vitalsPeriodicSpo2Fragment, Ref.IntRef intRef, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = vitalsPeriodicSpo2Fragment;
                this.$spo2Val = intRef;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.this$0, this.$spo2Val, continuation);
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
                    this.this$0.p().vitalsMainData.tvAvgHRVSpo2Value.setText(String.valueOf(this.$spo2Val.element));
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsPeriodicSpo2Fragment$getLatestPeriodicSpo2ValueWithTimestamp$2$3", f = "VitalsPeriodicSpo2Fragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes4.dex */
        public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Ref.IntRef $spo2Val;
            public int label;
            public final /* synthetic */ VitalsPeriodicSpo2Fragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(VitalsPeriodicSpo2Fragment vitalsPeriodicSpo2Fragment, Ref.IntRef intRef, Continuation<? super c> continuation) {
                super(2, continuation);
                this.this$0 = vitalsPeriodicSpo2Fragment;
                this.$spo2Val = intRef;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new c(this.this$0, this.$spo2Val, continuation);
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
                    this.this$0.p().vitalsMainData.tvAvgHRVSpo2Value.setText(String.valueOf(this.$spo2Val.element));
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

        /* JADX WARN: Type inference failed for: r2v4, types: [com.coveiot.covepreferences.data.LatestHealthDataModel, T] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        Ref.ObjectRef objectRef = new Ref.ObjectRef();
                        objectRef.element = UserDataManager.getInstance(VitalsPeriodicSpo2Fragment.this.requireContext()).getLatestSpo2FromPref();
                        DeviceUtils.Companion companion = DeviceUtils.Companion;
                        Context requireContext = VitalsPeriodicSpo2Fragment.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        if (!companion.isTouchELXDevice(requireContext) && objectRef.element != 0) {
                            Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
                            calendar.setTimeInMillis(((LatestHealthDataModel) objectRef.element).getTimestamp());
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                            if (simpleDateFormat.format(this.$calendar.getTime()).equals(simpleDateFormat.format(calendar.getTime()))) {
                                MainCoroutineDispatcher main = Dispatchers.getMain();
                                C0278a c0278a = new C0278a(VitalsPeriodicSpo2Fragment.this, objectRef, null);
                                this.label = 1;
                                if (BuildersKt.withContext(main, c0278a, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                String format = simpleDateFormat.format(this.$calendar.getTime());
                                String format2 = simpleDateFormat.format(calendar.getTime());
                                Intrinsics.checkNotNullExpressionValue(format2, "fmt.format(prefCal.time)");
                                if (format.compareTo(format2) < 0) {
                                    PeriodicSpo2Repository.Companion companion2 = PeriodicSpo2Repository.Companion;
                                    Context requireContext2 = VitalsPeriodicSpo2Fragment.this.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                                    EntityHourlySpo2 latestSpo2RecordHourly = companion2.getInstance(requireContext2).getLatestSpo2RecordHourly(this.$calendar, this.$serialNo);
                                    if (latestSpo2RecordHourly != null) {
                                        Ref.IntRef intRef = new Ref.IntRef();
                                        int size = latestSpo2RecordHourly.codevalue.size() - 1;
                                        if (size >= 0) {
                                            while (true) {
                                                int i = size - 1;
                                                Integer num = latestSpo2RecordHourly.codevalue.get(size);
                                                Intrinsics.checkNotNullExpressionValue(num, "hourlyPeriodicSpo2.codevalue[i]");
                                                if (num.intValue() > 0) {
                                                    Integer num2 = latestSpo2RecordHourly.codevalue.get(size);
                                                    Intrinsics.checkNotNullExpressionValue(num2, "hourlyPeriodicSpo2.codevalue[i]");
                                                    intRef.element = num2.intValue();
                                                } else if (i >= 0) {
                                                    size = i;
                                                }
                                            }
                                        }
                                        if (intRef.element > 0) {
                                            MainCoroutineDispatcher main2 = Dispatchers.getMain();
                                            b bVar = new b(VitalsPeriodicSpo2Fragment.this, intRef, null);
                                            this.label = 2;
                                            if (BuildersKt.withContext(main2, bVar, this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                        }
                                        VitalsPeriodicSpo2Fragment.this.p().spo2Graph.setVisibility(0);
                                        VitalsPeriodicSpo2Fragment.this.p().tvNoDataFound.setVisibility(8);
                                        break;
                                    } else {
                                        VitalsPeriodicSpo2Fragment vitalsPeriodicSpo2Fragment = VitalsPeriodicSpo2Fragment.this;
                                        this.label = 3;
                                        if (vitalsPeriodicSpo2Fragment.A(this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    }
                                } else {
                                    VitalsPeriodicSpo2Fragment vitalsPeriodicSpo2Fragment2 = VitalsPeriodicSpo2Fragment.this;
                                    this.label = 4;
                                    if (vitalsPeriodicSpo2Fragment2.A(this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                }
                            }
                        } else {
                            Context requireContext3 = VitalsPeriodicSpo2Fragment.this.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                            if (companion.isTouchELXDevice(requireContext3)) {
                                PeriodicSpo2Repository.Companion companion3 = PeriodicSpo2Repository.Companion;
                                Context requireContext4 = VitalsPeriodicSpo2Fragment.this.requireContext();
                                Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                                EntityHourlySpo2 latestSpo2RecordHourly2 = companion3.getInstance(requireContext4).getLatestSpo2RecordHourly(this.$calendar, this.$serialNo);
                                if (latestSpo2RecordHourly2 != null) {
                                    Ref.IntRef intRef2 = new Ref.IntRef();
                                    int size2 = latestSpo2RecordHourly2.codevalue.size() - 1;
                                    if (size2 >= 0) {
                                        while (true) {
                                            int i2 = size2 - 1;
                                            Integer num3 = latestSpo2RecordHourly2.codevalue.get(size2);
                                            Intrinsics.checkNotNullExpressionValue(num3, "hourlyPeriodicSpo2.codevalue[i]");
                                            if (num3.intValue() > 0) {
                                                Integer num4 = latestSpo2RecordHourly2.codevalue.get(size2);
                                                Intrinsics.checkNotNullExpressionValue(num4, "hourlyPeriodicSpo2.codevalue[i]");
                                                intRef2.element = num4.intValue();
                                            } else if (i2 >= 0) {
                                                size2 = i2;
                                            }
                                        }
                                    }
                                    if (intRef2.element > 0) {
                                        MainCoroutineDispatcher main3 = Dispatchers.getMain();
                                        c cVar = new c(VitalsPeriodicSpo2Fragment.this, intRef2, null);
                                        this.label = 5;
                                        if (BuildersKt.withContext(main3, cVar, this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    }
                                } else {
                                    VitalsPeriodicSpo2Fragment vitalsPeriodicSpo2Fragment3 = VitalsPeriodicSpo2Fragment.this;
                                    this.label = 6;
                                    if (vitalsPeriodicSpo2Fragment3.A(this) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                }
                            }
                        }
                        break;
                    case 1:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        ResultKt.throwOnFailure(obj);
                        break;
                    case 2:
                        ResultKt.throwOnFailure(obj);
                        VitalsPeriodicSpo2Fragment.this.p().spo2Graph.setVisibility(0);
                        VitalsPeriodicSpo2Fragment.this.p().tvNoDataFound.setVisibility(8);
                        break;
                    case 7:
                        ResultKt.throwOnFailure(obj);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Exception e) {
                e.printStackTrace();
                VitalsPeriodicSpo2Fragment vitalsPeriodicSpo2Fragment4 = VitalsPeriodicSpo2Fragment.this;
                this.label = 7;
                if (vitalsPeriodicSpo2Fragment4.A(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsPeriodicSpo2Fragment$handleSelectedDate$1", f = "VitalsPeriodicSpo2Fragment.kt", i = {}, l = {184}, m = "invokeSuspend", n = {}, s = {})
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
                VitalsPeriodicSpo2Fragment vitalsPeriodicSpo2Fragment = VitalsPeriodicSpo2Fragment.this;
                Calendar calendar = vitalsPeriodicSpo2Fragment.o;
                String macAddress = BleApiManager.getInstance(VitalsPeriodicSpo2Fragment.this.getContext()).getBleApi().getMacAddress();
                this.label = 1;
                if (vitalsPeriodicSpo2Fragment.q(calendar, macAddress, this) == coroutine_suspended) {
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

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsPeriodicSpo2Fragment$setTextValueEmpty$2", f = "VitalsPeriodicSpo2Fragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                VitalsPeriodicSpo2Fragment.this.p().vitalsMainData.tvAvgHRVSpo2Value.setText(String.valueOf(AppConstants.TWO_DASH_STRING.getValue()));
                VitalsPeriodicSpo2Fragment.this.p().spo2Graph.setVisibility(8);
                VitalsPeriodicSpo2Fragment.this.p().tvNoDataFound.setVisibility(0);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public VitalsPeriodicSpo2Fragment() {
        AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this.o = calendar;
    }

    public static final void t(VitalsPeriodicSpo2Fragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.y();
        this$0.getDatePickerDialog().getDatePicker().setMaxDate(System.currentTimeMillis());
        this$0.getDatePickerDialog().show();
    }

    public static final void u(VitalsPeriodicSpo2Fragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.p = 0.0f;
        this$0.r(PayUtils.INSTANCE.getPreviousDayCalendar(this$0.o));
    }

    public static final void v(VitalsPeriodicSpo2Fragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (DateUtils.isToday(this$0.o.getTimeInMillis())) {
            return;
        }
        this$0.r(PayUtils.INSTANCE.getNextDayCalendar(this$0.o));
        this$0.p = 0.0f;
    }

    public static final void w(VitalsPeriodicSpo2Fragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.share();
    }

    public static final void z(VitalsPeriodicSpo2Fragment this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i, i2, i3);
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        this$0.r(newDate);
    }

    public final Object A(Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getMain(), new c(null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
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

    public final float getLatestAvgspo2value() {
        return this.p;
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
        FragmentPeriodicSpo2ViewModel fragmentPeriodicSpo2ViewModel = (FragmentPeriodicSpo2ViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FragmentPeriodicSpo2ViewModel.class);
        this.n = fragmentPeriodicSpo2ViewModel;
        if (fragmentPeriodicSpo2ViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentPeriodicSpo2ViewModel = null;
        }
        fragmentPeriodicSpo2ViewModel.setContractPeriodicSpo2$app_prodRelease(this);
        FragmentPeriodicSpo2ViewModel fragmentPeriodicSpo2ViewModel2 = this.n;
        if (fragmentPeriodicSpo2ViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentPeriodicSpo2ViewModel2 = null;
        }
        fragmentPeriodicSpo2ViewModel2.setMLifecycleOwner(this);
        FragmentVitalPeriodicSpo2Binding p = p();
        ConstraintLayout constraintLayout = p.vitalsMainData.clCenterDataStepsSleep;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "vitalsMainData.clCenterDataStepsSleep");
        gone(constraintLayout);
        TextView textView = p.vitalsMainData.tvVitalInfo;
        Intrinsics.checkNotNullExpressionValue(textView, "vitalsMainData.tvVitalInfo");
        gone(textView);
        TextView textView2 = p.vitalsMainData.tvVitalMax;
        Intrinsics.checkNotNullExpressionValue(textView2, "vitalsMainData.tvVitalMax");
        inVisible(textView2);
        TextView textView3 = p.vitalsMainData.tvVitalMin;
        Intrinsics.checkNotNullExpressionValue(textView3, "vitalsMainData.tvVitalMin");
        inVisible(textView3);
        TextView textView4 = p.vitalsMainData.tvVitalMaxValue;
        Intrinsics.checkNotNullExpressionValue(textView4, "vitalsMainData.tvVitalMaxValue");
        inVisible(textView4);
        TextView textView5 = p.vitalsMainData.tvVitalMinValue;
        Intrinsics.checkNotNullExpressionValue(textView5, "vitalsMainData.tvVitalMinValue");
        inVisible(textView5);
        ImageView imageView = p.vitalsMainData.ivMin;
        Intrinsics.checkNotNullExpressionValue(imageView, "vitalsMainData.ivMin");
        inVisible(imageView);
        ImageView imageView2 = p.vitalsMainData.ivMax;
        Intrinsics.checkNotNullExpressionValue(imageView2, "vitalsMainData.ivMax");
        inVisible(imageView2);
        ImageView imageView3 = p.vitalsMainData.ivMinBg;
        Intrinsics.checkNotNullExpressionValue(imageView3, "vitalsMainData.ivMinBg");
        inVisible(imageView3);
        ImageView imageView4 = p.vitalsMainData.ivMaxBg;
        Intrinsics.checkNotNullExpressionValue(imageView4, "vitalsMainData.ivMaxBg");
        inVisible(imageView4);
        ConstraintLayout constraintLayout2 = p.vitalsMainData.clTopSelector;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "vitalsMainData.clTopSelector");
        gone(constraintLayout2);
        p.vitalsMainData.ivCenterVital.setImageResource(R.drawable.ic_spo2);
        ConstraintLayout constraintLayout3 = p.vitalsMainData.clCenterDataHRVSpo2;
        Intrinsics.checkNotNullExpressionValue(constraintLayout3, "vitalsMainData.clCenterDataHRVSpo2");
        visible(constraintLayout3);
        p.vitalsMainData.tvVitalName.setText(getString(R.string.spo2));
        p.vitalsMainData.tvVitalName.setCompoundDrawables(null, null, null, null);
        p.vitalsMainData.tvAvgHRVSpo2ValueUnit.setText("%");
        TextView textView6 = p.vitalsMainData.tvLastSyncTime;
        PayUtils payUtils = PayUtils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        textView6.setText(payUtils.getLastSyncTime(requireContext2));
        String string = getString(R.string.medical_disclaimer_info);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.medical_disclaimer_info)");
        SpannableString spannableString = new SpannableString(string);
        Context context = getContext();
        Integer valueOf = context != null ? Integer.valueOf(context.getColor(R.color.color_b3b3b3)) : null;
        Intrinsics.checkNotNull(valueOf);
        spannableString.setSpan(new ForegroundColorSpan(valueOf.intValue()), 0, 12, 18);
        p.tvdisclaimer.setText(spannableString);
        s();
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        r(calendar);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicSpo2
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
        this.m = FragmentVitalPeriodicSpo2Binding.inflate(inflater, viewGroup, false);
        View root = p().getRoot();
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

    public final FragmentVitalPeriodicSpo2Binding p() {
        FragmentVitalPeriodicSpo2Binding fragmentVitalPeriodicSpo2Binding = this.m;
        Intrinsics.checkNotNull(fragmentVitalPeriodicSpo2Binding);
        return fragmentVitalPeriodicSpo2Binding;
    }

    public final Object q(Calendar calendar, String str, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new a(calendar, str, null), continuation);
        return withContext == kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final void r(Calendar calendar) {
        if (isAdded()) {
            TextView textView = p().vitalsMainData.tvSelectedTypeValue;
            long timeInMillis = calendar.getTimeInMillis();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            textView.setText(PayUtils.getTodayYesterdayStringFromTimeStamp(timeInMillis, requireContext));
            if (DateUtils.isToday(calendar.getTimeInMillis())) {
                p().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                p().vitalsMainData.ibForward.setEnabled(false);
            } else {
                p().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                p().vitalsMainData.ibForward.setEnabled(true);
            }
            FragmentPeriodicSpo2ViewModel fragmentPeriodicSpo2ViewModel = this.n;
            if (fragmentPeriodicSpo2ViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentPeriodicSpo2ViewModel = null;
            }
            fragmentPeriodicSpo2ViewModel.loadHourlyPeriodicSpo2Data(calendar);
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, null, null, new b(null), 3, null);
            this.o = calendar;
        }
    }

    public final void s() {
        FragmentVitalPeriodicSpo2Binding p = p();
        p.vitalsMainData.tvSelectedTypeValue.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.e1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsPeriodicSpo2Fragment.t(VitalsPeriodicSpo2Fragment.this, view);
            }
        });
        p.vitalsMainData.ibPrevious.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.d1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsPeriodicSpo2Fragment.u(VitalsPeriodicSpo2Fragment.this, view);
            }
        });
        p.vitalsMainData.ibForward.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.f1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsPeriodicSpo2Fragment.v(VitalsPeriodicSpo2Fragment.this, view);
            }
        });
        p.vitalsMainData.ibShareVitals.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.g1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsPeriodicSpo2Fragment.w(VitalsPeriodicSpo2Fragment.this, view);
            }
        });
    }

    public final void setDatePickerDialog(@NotNull DatePickerDialog datePickerDialog) {
        Intrinsics.checkNotNullParameter(datePickerDialog, "<set-?>");
        this.datePickerDialog = datePickerDialog;
    }

    public final void setLatestAvgspo2value(float f) {
        this.p = f;
    }

    public final void setSimpleDateFormat(@NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
        this.simpleDateFormat = simpleDateFormat;
    }

    public final void share() {
        ShareData shareData = new ShareData();
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM, yyyy");
        shareData.setSpo2_max(p().vitalsMainData.tvAvgHRVSpo2Value.getText().toString());
        shareData.setData(p().vitalsMainData.tvAvgHRVSpo2Value.getText().toString());
        shareData.setGraphType(getResources().getString(R.string.share_daily));
        shareData.setDwmValue(simpleDateFormat.format(this.o.getTime()));
        AppNavigator.Companion companion = AppNavigator.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getResources().getString(R.string.periodic_spo2);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.periodic_spo2)");
        companion.navigateToShareScreen(requireContext, shareData, string);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicSpo2
    public void updateDailyLevelData(@Nullable DailyPeriodicSpo2 dailyPeriodicSpo2) {
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractPeriodicSpo2
    public void updateHourlyLevelData(@Nullable ArrayList<BarEntry> arrayList, @Nullable ArrayList<String> arrayList2) {
        if (!AppUtils.isEmpty(arrayList)) {
            p().spo2Graph.setVisibility(0);
            p().tvNoDataFound.setVisibility(8);
            Intrinsics.checkNotNull(arrayList);
            Intrinsics.checkNotNull(arrayList2);
            x(arrayList, arrayList2);
            return;
        }
        p().spo2Graph.setVisibility(8);
        p().tvNoDataFound.setVisibility(0);
        p().spo2Graph.clear();
    }

    public final void x(ArrayList<BarEntry> arrayList, ArrayList<String> arrayList2) {
        ArrayList arrayList3 = new ArrayList();
        Intrinsics.checkNotNull(arrayList);
        Iterator<BarEntry> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            BarEntry next = it.next();
            if (next.getY() == 0.0f) {
                arrayList3.add(next);
            } else {
                float y = next.getY();
                this.p = y;
                this.p = y;
            }
        }
        if (!(this.p == 0.0f)) {
            p().vitalsMainData.tvAvgHRVSpo2Value.setText(String.valueOf((int) this.p));
        } else {
            p().vitalsMainData.tvAvgHRVSpo2Value.setText("--");
        }
        BarDataSet barDataSet = new BarDataSet(arrayList, AppConstants.EMPTY_STRING.getValue());
        barDataSet.setDrawValues(false);
        barDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        barDataSet.setColor(getResources().getColor(R.color.steps_graph_color));
        barDataSet.setHighLightColor(getResources().getColor(17170445));
        if (barDataSet.getEntryCount() > 0) {
            int entryCount = barDataSet.getEntryCount();
            int[] iArr = new int[entryCount];
            for (int i = 0; i < entryCount; i++) {
                iArr[i] = getResources().getColor(R.color.steps_graph_color);
            }
            iArr[entryCount - 1] = getResources().getColor(R.color.steps_graph_color);
            barDataSet.setColors(Arrays.copyOf(iArr, entryCount));
        }
        BarData barData = new BarData(barDataSet);
        p().spo2Graph.getXAxis().setValueFormatter(new IndexAxisValueFormatter(arrayList2));
        barData.setDrawValues(false);
        p().spo2Graph.setData(barData);
        p().spo2Graph.setDrawGridBackground(false);
        p().spo2Graph.getDescription().setEnabled(false);
        barData.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        p().spo2Graph.getPaint(7).setColor(getResources().getColor(R.color.secondary_text_color));
        p().spo2Graph.setDrawBorders(false);
        p().spo2Graph.setAutoScaleMinMaxEnabled(false);
        p().spo2Graph.setPinchZoom(false);
        YAxis axisLeft = p().spo2Graph.getAxisLeft();
        axisLeft.setEnabled(true);
        axisLeft.setDrawAxisLine(true);
        axisLeft.setDrawGridLines(false);
        axisLeft.mAxisMaximum = 110.0f;
        axisLeft.setAxisLineColor(getResources().getColor(R.color.colorSecondaryGrey));
        p().spo2Graph.getAxisRight().setEnabled(false);
        XAxis xAxis = p().spo2Graph.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineColor(getResources().getColor(R.color.colorSecondaryGrey));
        p().spo2Graph.getAxisLeft().setStartAtZero(true);
        p().spo2Graph.getAxisRight().setStartAtZero(true);
        p().spo2Graph.getAxisLeft().setTextColor(getResources().getColor(R.color.secondary_text_color));
        p().spo2Graph.getXAxis().setTextColor(getResources().getColor(R.color.secondary_text_color));
        p().spo2Graph.getLegend().setTextColor(getResources().getColor(R.color.secondary_text_color));
        p().spo2Graph.getLegend().setEnabled(false);
        p().spo2Graph.setVisibleXRangeMinimum(5.0f);
        Context context = getContext();
        String string = getString(R.string.spo2);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.spo2)");
        CustomMarkerViewVitals customMarkerViewVitals = new CustomMarkerViewVitals(context, R.layout.custom_marker_view_steps_hr, string, 0, arrayList2);
        customMarkerViewVitals.setChartView(p().spo2Graph);
        p().spo2Graph.setMarker(customMarkerViewVitals);
        p().spo2Graph.setPinchZoom(false);
        p().spo2Graph.setAutoScaleMinMaxEnabled(false);
        p().spo2Graph.setScaleEnabled(false);
        p().spo2Graph.setDoubleTapToZoomEnabled(false);
        p().spo2Graph.invalidate();
    }

    public final void y() {
        Calendar calendar = this.o;
        setDatePickerDialog(new DatePickerDialog(requireActivity(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.c1
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                VitalsPeriodicSpo2Fragment.z(VitalsPeriodicSpo2Fragment.this, datePicker, i, i2, i3);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)));
        this.p = 0.0f;
    }
}
