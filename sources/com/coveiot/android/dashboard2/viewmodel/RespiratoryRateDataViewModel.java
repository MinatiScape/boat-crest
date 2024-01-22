package com.coveiot.android.dashboard2.viewmodel;

import android.content.Context;
import android.icu.util.Calendar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.dashboard2.model.RespiratoryRateData;
import com.coveiot.android.dashboard2.util.RespiratoryRateDataHelper;
import com.coveiot.android.respiratoryrate.database.RespiratoryRateRepository;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity;
import com.coveiot.utils.utility.AppUtils;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class RespiratoryRateDataViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4282a;
    @NotNull
    public final MutableLiveData<RespiratoryRateData> b;
    @Nullable
    public LiveData<DailyRespiratoryRateEntity> c;
    @NotNull
    public final Observer<DailyRespiratoryRateEntity> d;

    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.RespiratoryRateDataViewModel$getRespiratoryRateData$1", f = "RespiratoryRateDataViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
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
                LiveData liveData = RespiratoryRateDataViewModel.this.c;
                if (liveData != null) {
                    liveData.observeForever(RespiratoryRateDataViewModel.this.d);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.RespiratoryRateDataViewModel$lastDataSyncObserver$1$1$1", f = "RespiratoryRateDataViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ DailyRespiratoryRateEntity $dailyRespiratoryRateData;
        public int label;
        public final /* synthetic */ RespiratoryRateDataViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(DailyRespiratoryRateEntity dailyRespiratoryRateEntity, RespiratoryRateDataViewModel respiratoryRateDataViewModel, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$dailyRespiratoryRateData = dailyRespiratoryRateEntity;
            this.this$0 = respiratoryRateDataViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$dailyRespiratoryRateData, this.this$0, continuation);
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
                DailyRespiratoryRateEntity dailyRespiratoryRateEntity = this.$dailyRespiratoryRateData;
                if ((dailyRespiratoryRateEntity != null ? dailyRespiratoryRateEntity.data : null) != null) {
                    com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateData respiratoryRateData = dailyRespiratoryRateEntity != null ? dailyRespiratoryRateEntity.data : null;
                    Intrinsics.checkNotNull(respiratoryRateData);
                    if (respiratoryRateData.getAvg() != null) {
                        DailyRespiratoryRateEntity dailyRespiratoryRateEntity2 = this.$dailyRespiratoryRateData;
                        com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateData respiratoryRateData2 = dailyRespiratoryRateEntity2 != null ? dailyRespiratoryRateEntity2.data : null;
                        Intrinsics.checkNotNull(respiratoryRateData2);
                        Integer avg = respiratoryRateData2.getAvg();
                        Intrinsics.checkNotNull(avg);
                        if (avg.intValue() > 0) {
                            RespiratoryRateDataHelper respiratoryRateDataHelper = RespiratoryRateDataHelper.INSTANCE;
                            Context context = this.this$0.getContext();
                            com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateData respiratoryRateData3 = this.$dailyRespiratoryRateData.data;
                            Intrinsics.checkNotNull(respiratoryRateData3);
                            Pair<Integer, Integer> lastReadValueWithHour = respiratoryRateDataHelper.getLastReadValueWithHour(context, respiratoryRateData3);
                            int intValue = lastReadValueWithHour.getFirst().intValue();
                            int intValue2 = lastReadValueWithHour.getSecond().intValue();
                            if (intValue2 > 0) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(AppUtils.parseDate(this.$dailyRespiratoryRateData.getMDate(), "yyyy-MM-dd"));
                                calendar.set(11, intValue);
                                RespiratoryRateData respiratoryRateData4 = new RespiratoryRateData(0, 0L, 3, null);
                                respiratoryRateData4.setValue(intValue2);
                                respiratoryRateData4.setTimestamp(calendar.getTimeInMillis());
                                MutableLiveData mutableLiveData = this.this$0.b;
                                if (mutableLiveData != null) {
                                    mutableLiveData.postValue(respiratoryRateData4);
                                }
                            }
                            return Unit.INSTANCE;
                        }
                    }
                }
                MutableLiveData mutableLiveData2 = this.this$0.b;
                if (mutableLiveData2 != null) {
                    mutableLiveData2.postValue(new RespiratoryRateData(0, 0L, 3, null));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public RespiratoryRateDataViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4282a = context;
        this.b = new MutableLiveData<>();
        this.d = new Observer() { // from class: com.coveiot.android.dashboard2.viewmodel.j
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                RespiratoryRateDataViewModel.b(RespiratoryRateDataViewModel.this, (DailyRespiratoryRateEntity) obj);
            }
        };
    }

    public static final void b(RespiratoryRateDataViewModel this$0, DailyRespiratoryRateEntity dailyRespiratoryRateEntity) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (dailyRespiratoryRateEntity != null) {
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this$0), Dispatchers.getIO(), null, new b(dailyRespiratoryRateEntity, this$0, null), 2, null);
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f4282a;
    }

    @NotNull
    public final MutableLiveData<RespiratoryRateData> getRespiratoryLiveData() {
        return this.b;
    }

    public final void getRespiratoryRateData() {
        if (BleApiManager.getInstance(this.f4282a).getBleApi().getDeviceSupportedFeatures().isRespiratoryRateByPPGSupported()) {
            this.c = RespiratoryRateRepository.Companion.getInstance(this.f4282a).getLatestRespiratoryRateData(BleApiManager.getInstance(this.f4282a).getBleApi().getMacAddress());
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
            LiveData<DailyRespiratoryRateEntity> liveData = this.c;
            if (liveData != null) {
                if ((liveData != null ? liveData.getValue() : null) != null) {
                    return;
                }
            }
            this.b.postValue(new RespiratoryRateData(0, 0L, 3, null));
        }
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        LiveData<DailyRespiratoryRateEntity> liveData = this.c;
        if (liveData != null) {
            liveData.removeObserver(this.d);
        }
    }
}
