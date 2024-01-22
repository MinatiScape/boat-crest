package com.coveiot.android.respiratoryrate.viewmodel;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.respiratoryrate.database.RespiratoryRateRepository;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity;
import com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateData;
import com.coveiot.android.respiratoryrate.listener.ContractRespiratoryRateHistory;
import com.coveiot.android.respiratoryrate.model.MonthlyRespiratoryRateData;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateBean;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateListBean;
import com.coveiot.android.respiratoryrate.model.WeeklyRespiratoryRateData;
import com.coveiot.android.respiratoryrate.utils.Constants;
import com.coveiot.android.respiratoryrate.utils.RespiratoryRateHistoryWeekComparator;
import com.coveiot.android.respiratoryrate.utils.Utils;
import com.coveiot.covepreferences.SessionManager;
import com.github.mikephil.charting.data.Entry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.h;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class RespiratoryRateHistoryViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5679a;
    public ContractRespiratoryRateHistory contractRespiratoryRateHistory;
    public LifecycleOwner mLifecycleOwner;

    /* loaded from: classes6.dex */
    public static final class a extends Lambda implements Function1<List<? extends DailyRespiratoryRateEntity>, Unit> {

        @DebugMetadata(c = "com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateHistoryViewModel$selectDayView$1$1", f = "RespiratoryRateHistoryViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateHistoryViewModel$a$a  reason: collision with other inner class name */
        /* loaded from: classes6.dex */
        public static final class C0302a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ List<DailyRespiratoryRateEntity> $dailyRespiratoryRateList;
            public int label;
            public final /* synthetic */ RespiratoryRateHistoryViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0302a(List<DailyRespiratoryRateEntity> list, RespiratoryRateHistoryViewModel respiratoryRateHistoryViewModel, Continuation<? super C0302a> continuation) {
                super(2, continuation);
                this.$dailyRespiratoryRateList = list;
                this.this$0 = respiratoryRateHistoryViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0302a(this.$dailyRespiratoryRateList, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0302a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                float intValue;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    List<DailyRespiratoryRateEntity> list = this.$dailyRespiratoryRateList;
                    if (!(list == null || list.isEmpty())) {
                        ArrayList<Entry> arrayList = new ArrayList<>();
                        ArrayList arrayList2 = new ArrayList();
                        List<DailyRespiratoryRateEntity> list2 = this.$dailyRespiratoryRateList;
                        if (list2 != null) {
                            int size = list2.size();
                            for (int i = 0; i < size; i++) {
                                DailyRespiratoryRateEntity dailyRespiratoryRateEntity = this.$dailyRespiratoryRateList.get(i);
                                RespiratoryRateData respiratoryRateData = dailyRespiratoryRateEntity.data;
                                if (respiratoryRateData != null) {
                                    Intrinsics.checkNotNull(respiratoryRateData);
                                    if (respiratoryRateData.getMin() != null) {
                                        RespiratoryRateData respiratoryRateData2 = dailyRespiratoryRateEntity.data;
                                        Intrinsics.checkNotNull(respiratoryRateData2);
                                        Integer min = respiratoryRateData2.getMin();
                                        Intrinsics.checkNotNull(min);
                                        if (min.intValue() > 0) {
                                            RespiratoryRateData respiratoryRateData3 = dailyRespiratoryRateEntity.data;
                                            Intrinsics.checkNotNull(respiratoryRateData3);
                                            if (respiratoryRateData3.getMax() != null) {
                                                RespiratoryRateData respiratoryRateData4 = dailyRespiratoryRateEntity.data;
                                                Intrinsics.checkNotNull(respiratoryRateData4);
                                                Integer max = respiratoryRateData4.getMax();
                                                Intrinsics.checkNotNull(max);
                                                if (max.intValue() > 0) {
                                                    RespiratoryRateBean respiratoryRateBean = new RespiratoryRateBean();
                                                    respiratoryRateBean.setStartDate(dailyRespiratoryRateEntity.getMDate());
                                                    respiratoryRateBean.setEndDate(dailyRespiratoryRateEntity.getMDate());
                                                    RespiratoryRateData respiratoryRateData5 = dailyRespiratoryRateEntity.data;
                                                    respiratoryRateBean.setMax(respiratoryRateData5 != null ? respiratoryRateData5.getMax() : null);
                                                    RespiratoryRateData respiratoryRateData6 = dailyRespiratoryRateEntity.data;
                                                    respiratoryRateBean.setMin(respiratoryRateData6 != null ? respiratoryRateData6.getMin() : null);
                                                    RespiratoryRateData respiratoryRateData7 = dailyRespiratoryRateEntity.data;
                                                    respiratoryRateBean.setAvg(respiratoryRateData7 != null ? respiratoryRateData7.getAvg() : null);
                                                    respiratoryRateBean.setType(Constants.DAY.getValue());
                                                    if (respiratoryRateBean.getAvg() == null) {
                                                        intValue = 0.0f;
                                                    } else {
                                                        Integer avg = respiratoryRateBean.getAvg();
                                                        Intrinsics.checkNotNull(avg);
                                                        intValue = avg.intValue();
                                                    }
                                                    Entry entry = new Entry(i, intValue);
                                                    entry.setData(respiratoryRateBean);
                                                    arrayList.add(entry);
                                                    arrayList2.add(Utils.getDayMonthFormatDate(respiratoryRateBean.getStartDate()));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            this.this$0.getContractRespiratoryRateHistory$respiratoryrate_prodRelease().onLineChartDataLoaded(arrayList, arrayList2, Constants.DAY.getValue());
                        }
                    } else {
                        this.this$0.getContractRespiratoryRateHistory$respiratoryrate_prodRelease().onLineChartDataLoaded(null, null, Constants.DAY.getValue());
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends DailyRespiratoryRateEntity> list) {
            invoke2((List<DailyRespiratoryRateEntity>) list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable List<DailyRespiratoryRateEntity> list) {
            e.e(LifecycleOwnerKt.getLifecycleScope(RespiratoryRateHistoryViewModel.this.getMLifecycleOwner()), Dispatchers.getIO(), null, new C0302a(list, RespiratoryRateHistoryViewModel.this, null), 2, null);
        }
    }

    /* loaded from: classes6.dex */
    public static final class b extends Lambda implements Function1<List<? extends WeeklyRespiratoryRateData>, Unit> {

        @DebugMetadata(c = "com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateHistoryViewModel$selectWeekView$1$1", f = "RespiratoryRateHistoryViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes6.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ List<WeeklyRespiratoryRateData> $weeklyRespiratoryRateDataList;
            public int label;
            public final /* synthetic */ RespiratoryRateHistoryViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(List<WeeklyRespiratoryRateData> list, RespiratoryRateHistoryViewModel respiratoryRateHistoryViewModel, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$weeklyRespiratoryRateDataList = list;
                this.this$0 = respiratoryRateHistoryViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$weeklyRespiratoryRateDataList, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                float intValue;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    List<WeeklyRespiratoryRateData> list = this.$weeklyRespiratoryRateDataList;
                    if (!(list == null || list.isEmpty())) {
                        Collections.sort(this.$weeklyRespiratoryRateDataList, new RespiratoryRateHistoryWeekComparator());
                        ArrayList<Entry> arrayList = new ArrayList<>();
                        ArrayList arrayList2 = new ArrayList();
                        int size = this.$weeklyRespiratoryRateDataList.size();
                        for (int i = 0; i < size; i++) {
                            WeeklyRespiratoryRateData weeklyRespiratoryRateData = this.$weeklyRespiratoryRateDataList.get(i);
                            if (weeklyRespiratoryRateData != null && weeklyRespiratoryRateData.getMin() != null) {
                                Integer min = weeklyRespiratoryRateData.getMin();
                                Intrinsics.checkNotNull(min);
                                if (min.intValue() > 0 && weeklyRespiratoryRateData.getMax() != null) {
                                    Integer max = weeklyRespiratoryRateData.getMax();
                                    Intrinsics.checkNotNull(max);
                                    if (max.intValue() > 0) {
                                        RespiratoryRateBean respiratoryRateBean = new RespiratoryRateBean();
                                        respiratoryRateBean.setStartDate(weeklyRespiratoryRateData.getStartDate());
                                        respiratoryRateBean.setEndDate(weeklyRespiratoryRateData.getEndDate());
                                        respiratoryRateBean.setAvg(weeklyRespiratoryRateData.getAvg());
                                        respiratoryRateBean.setMax(weeklyRespiratoryRateData.getMax());
                                        respiratoryRateBean.setMin(weeklyRespiratoryRateData.getMin());
                                        respiratoryRateBean.setWeek(weeklyRespiratoryRateData.getWeek());
                                        respiratoryRateBean.setYear(weeklyRespiratoryRateData.getYear());
                                        respiratoryRateBean.setType(Constants.WEEK.getValue());
                                        String startDate = respiratoryRateBean.getStartDate();
                                        Intrinsics.checkNotNull(startDate);
                                        String endDate = respiratoryRateBean.getEndDate();
                                        Intrinsics.checkNotNull(endDate);
                                        String connectedDeviceMacAddress = SessionManager.getInstance(this.this$0.getContext()).getConnectedDeviceMacAddress();
                                        Intrinsics.checkNotNull(connectedDeviceMacAddress);
                                        List<RespiratoryRateListBean> dailyRespiratoryRateDateBetweenDates = RespiratoryRateRepository.Companion.getInstance(this.this$0.getContext()).getDailyRespiratoryRateDateBetweenDates(startDate, endDate, connectedDeviceMacAddress);
                                        respiratoryRateBean.setDailyRespiratoryRateBeanItemList(dailyRespiratoryRateDateBetweenDates != null ? CollectionsKt___CollectionsKt.toMutableList((Collection) dailyRespiratoryRateDateBetweenDates) : null);
                                        if (respiratoryRateBean.getAvg() == null) {
                                            intValue = 0.0f;
                                        } else {
                                            Integer avg = respiratoryRateBean.getAvg();
                                            Intrinsics.checkNotNull(avg);
                                            intValue = avg.intValue();
                                        }
                                        Entry entry = new Entry(i, intValue);
                                        entry.setData(respiratoryRateBean);
                                        arrayList.add(entry);
                                        arrayList2.add('W' + weeklyRespiratoryRateData.getWeek());
                                    }
                                }
                            }
                        }
                        this.this$0.getContractRespiratoryRateHistory$respiratoryrate_prodRelease().onLineChartDataLoaded(arrayList, arrayList2, Constants.WEEK.getValue());
                    } else {
                        this.this$0.getContractRespiratoryRateHistory$respiratoryrate_prodRelease().onLineChartDataLoaded(null, null, Constants.WEEK.getValue());
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends WeeklyRespiratoryRateData> list) {
            invoke2((List<WeeklyRespiratoryRateData>) list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable List<WeeklyRespiratoryRateData> list) {
            e.e(LifecycleOwnerKt.getLifecycleScope(RespiratoryRateHistoryViewModel.this.getMLifecycleOwner()), Dispatchers.getIO(), null, new a(list, RespiratoryRateHistoryViewModel.this, null), 2, null);
        }
    }

    public RespiratoryRateHistoryViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5679a = context;
    }

    public static final void d(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void e(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void f(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @NotNull
    public final Context getContext() {
        return this.f5679a;
    }

    @NotNull
    public final ContractRespiratoryRateHistory getContractRespiratoryRateHistory$respiratoryrate_prodRelease() {
        ContractRespiratoryRateHistory contractRespiratoryRateHistory = this.contractRespiratoryRateHistory;
        if (contractRespiratoryRateHistory != null) {
            return contractRespiratoryRateHistory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contractRespiratoryRateHistory");
        return null;
    }

    @NotNull
    public final LifecycleOwner getMLifecycleOwner() {
        LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
        if (lifecycleOwner != null) {
            return lifecycleOwner;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mLifecycleOwner");
        return null;
    }

    public final void selectDayView() {
        String connectedDeviceMacAddress = SessionManager.getInstance(this.f5679a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        LiveData<List<DailyRespiratoryRateEntity>> liveDayWiseRespiratoryRateData = RespiratoryRateRepository.Companion.getInstance(this.f5679a).getLiveDayWiseRespiratoryRateData(connectedDeviceMacAddress);
        if (liveDayWiseRespiratoryRateData != null) {
            LifecycleOwner mLifecycleOwner = getMLifecycleOwner();
            final a aVar = new a();
            liveDayWiseRespiratoryRateData.observe(mLifecycleOwner, new Observer() { // from class: com.coveiot.android.respiratoryrate.viewmodel.a
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    RespiratoryRateHistoryViewModel.d(Function1.this, obj);
                }
            });
        }
    }

    public final void selectMonthView() {
        String connectedDeviceMacAddress = SessionManager.getInstance(this.f5679a).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
        LiveData<List<MonthlyRespiratoryRateData>> liveMonthWiseRespiratoryRateData = RespiratoryRateRepository.Companion.getInstance(this.f5679a).getLiveMonthWiseRespiratoryRateData(connectedDeviceMacAddress);
        if (liveMonthWiseRespiratoryRateData != null) {
            LifecycleOwner mLifecycleOwner = getMLifecycleOwner();
            final Function1<List<? extends MonthlyRespiratoryRateData>, Unit> function1 = new Function1<List<? extends MonthlyRespiratoryRateData>, Unit>() { // from class: com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateHistoryViewModel$selectMonthView$1

                @DebugMetadata(c = "com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateHistoryViewModel$selectMonthView$1$1", f = "RespiratoryRateHistoryViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateHistoryViewModel$selectMonthView$1$1  reason: invalid class name */
                /* loaded from: classes6.dex */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ List<MonthlyRespiratoryRateData> $monthlyRespiratoryRateDataList;
                    public int label;
                    public final /* synthetic */ RespiratoryRateHistoryViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public AnonymousClass1(List<MonthlyRespiratoryRateData> list, RespiratoryRateHistoryViewModel respiratoryRateHistoryViewModel, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$monthlyRespiratoryRateDataList = list;
                        this.this$0 = respiratoryRateHistoryViewModel;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new AnonymousClass1(this.$monthlyRespiratoryRateDataList, this.this$0, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        float intValue;
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            List<MonthlyRespiratoryRateData> list = this.$monthlyRespiratoryRateDataList;
                            if (!(list == null || list.isEmpty())) {
                                ArrayList<Entry> arrayList = new ArrayList<>();
                                ArrayList arrayList2 = new ArrayList();
                                List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) this.$monthlyRespiratoryRateDataList);
                                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM yyyy", Locale.ENGLISH);
                                if (mutableList.size() > 1) {
                                    h.sortWith(mutableList, 
                                    /*  JADX ERROR: Method code generation error
                                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0041: INVOKE  
                                          (r4v1 'mutableList' java.util.List)
                                          (wrap: java.util.Comparator : 0x003e: CONSTRUCTOR  (r1v4 java.util.Comparator A[REMOVE]) = (r5v0 'simpleDateFormat' java.text.SimpleDateFormat A[DONT_INLINE]) call: com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateHistoryViewModel$selectMonthView$1$1$invokeSuspend$$inlined$sortBy$1.<init>(java.text.SimpleDateFormat):void type: CONSTRUCTOR)
                                         type: STATIC call: kotlin.collections.h.sortWith(java.util.List, java.util.Comparator):void in method: com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateHistoryViewModel$selectMonthView$1.1.invokeSuspend(java.lang.Object):java.lang.Object, file: classes6.dex
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:296)
                                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:275)
                                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:377)
                                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
                                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:272)
                                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateHistoryViewModel$selectMonthView$1$1$invokeSuspend$$inlined$sortBy$1, state: NOT_LOADED
                                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:302)
                                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:769)
                                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1097)
                                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                                        	... 35 more
                                        */
                                    /*
                                        Method dump skipped, instructions count: 335
                                        To view this dump add '--comments-level debug' option
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateHistoryViewModel$selectMonthView$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                                }
                            }

                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(List<? extends MonthlyRespiratoryRateData> list) {
                                invoke2((List<MonthlyRespiratoryRateData>) list);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke  reason: avoid collision after fix types in other method */
                            public final void invoke2(@Nullable List<MonthlyRespiratoryRateData> list) {
                                e.e(LifecycleOwnerKt.getLifecycleScope(RespiratoryRateHistoryViewModel.this.getMLifecycleOwner()), Dispatchers.getIO(), null, new AnonymousClass1(list, RespiratoryRateHistoryViewModel.this, null), 2, null);
                            }
                        };
                        liveMonthWiseRespiratoryRateData.observe(mLifecycleOwner, new Observer() { // from class: com.coveiot.android.respiratoryrate.viewmodel.c
                            @Override // androidx.lifecycle.Observer
                            public final void onChanged(Object obj) {
                                RespiratoryRateHistoryViewModel.e(Function1.this, obj);
                            }
                        });
                    }
                }

                public final void selectWeekView() {
                    String connectedDeviceMacAddress = SessionManager.getInstance(this.f5679a).getConnectedDeviceMacAddress();
                    Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(context).connectedDeviceMacAddress");
                    LiveData<List<WeeklyRespiratoryRateData>> liveWeekWiseRespiratoryRateData = RespiratoryRateRepository.Companion.getInstance(this.f5679a).getLiveWeekWiseRespiratoryRateData(connectedDeviceMacAddress);
                    if (liveWeekWiseRespiratoryRateData != null) {
                        LifecycleOwner mLifecycleOwner = getMLifecycleOwner();
                        final b bVar = new b();
                        liveWeekWiseRespiratoryRateData.observe(mLifecycleOwner, new Observer() { // from class: com.coveiot.android.respiratoryrate.viewmodel.b
                            @Override // androidx.lifecycle.Observer
                            public final void onChanged(Object obj) {
                                RespiratoryRateHistoryViewModel.f(Function1.this, obj);
                            }
                        });
                    }
                }

                public final void setContractRespiratoryRateHistory$respiratoryrate_prodRelease(@NotNull ContractRespiratoryRateHistory contractRespiratoryRateHistory) {
                    Intrinsics.checkNotNullParameter(contractRespiratoryRateHistory, "<set-?>");
                    this.contractRespiratoryRateHistory = contractRespiratoryRateHistory;
                }

                public final void setMLifecycleOwner(@NotNull LifecycleOwner lifecycleOwner) {
                    Intrinsics.checkNotNullParameter(lifecycleOwner, "<set-?>");
                    this.mLifecycleOwner = lifecycleOwner;
                }
            }
