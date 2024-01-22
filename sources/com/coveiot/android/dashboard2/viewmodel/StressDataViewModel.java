package com.coveiot.android.dashboard2.viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.dashboard2.model.StressData;
import com.coveiot.android.dashboard2.util.StressDataHelper;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.covedb.stress.entity.HourlyStress;
import com.coveiot.repository.manualdata.ManualDataRepository;
import com.coveiot.repository.stress.StressRepository;
import java.util.Calendar;
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
public final class StressDataViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4287a;
    @NotNull
    public final MutableLiveData<StressData> b;
    @Nullable
    public LiveData<EntityManualData> c;
    @Nullable
    public LiveData<HourlyStress> d;
    @NotNull
    public final Observer<EntityManualData> e;
    @NotNull
    public final Observer<HourlyStress> f;

    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.StressDataViewModel$getStressData$1", f = "StressDataViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                LiveData liveData = StressDataViewModel.this.c;
                if (liveData != null) {
                    liveData.observeForever(StressDataViewModel.this.e);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.StressDataViewModel$getStressData$2", f = "StressDataViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                LiveData liveData = StressDataViewModel.this.d;
                if (liveData != null) {
                    liveData.observeForever(StressDataViewModel.this.f);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public StressDataViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4287a = context;
        this.b = new MutableLiveData<>();
        this.e = new Observer() { // from class: com.coveiot.android.dashboard2.viewmodel.o
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                StressDataViewModel.c(StressDataViewModel.this, (EntityManualData) obj);
            }
        };
        this.f = new Observer() { // from class: com.coveiot.android.dashboard2.viewmodel.p
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                StressDataViewModel.d(StressDataViewModel.this, (HourlyStress) obj);
            }
        };
    }

    public static final void c(StressDataViewModel this$0, EntityManualData entityManualData) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (entityManualData != null && entityManualData.getStress() > 0) {
            StressData stressData = new StressData(entityManualData.getStress(), StressDataHelper.INSTANCE.getStressRange(entityManualData.getStress(), this$0.f4287a), entityManualData.getTimeStamp());
            MutableLiveData<StressData> mutableLiveData = this$0.b;
            if (mutableLiveData != null) {
                mutableLiveData.postValue(stressData);
                return;
            }
            return;
        }
        MutableLiveData<StressData> mutableLiveData2 = this$0.b;
        if (mutableLiveData2 != null) {
            mutableLiveData2.postValue(new StressData(0, null, 0L, 7, null));
        }
    }

    public static final void d(StressDataViewModel this$0, HourlyStress hourlyStress) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (hourlyStress != null && hourlyStress.stress_avg > 0.0d) {
            StressDataHelper stressDataHelper = StressDataHelper.INSTANCE;
            Pair<Integer, Calendar> stressBy = stressDataHelper.getStressBy(hourlyStress);
            StressData stressData = new StressData(stressBy.getFirst().intValue(), stressDataHelper.getStressRange(stressBy.getFirst().intValue(), this$0.f4287a), stressBy.getSecond().getTimeInMillis());
            MutableLiveData<StressData> mutableLiveData = this$0.b;
            if (mutableLiveData != null) {
                mutableLiveData.postValue(stressData);
                return;
            }
            return;
        }
        MutableLiveData<StressData> mutableLiveData2 = this$0.b;
        if (mutableLiveData2 != null) {
            mutableLiveData2.postValue(new StressData(0, null, 0L, 7, null));
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f4287a;
    }

    public final void getStressData() {
        if (BleApiManager.getInstance(this.f4287a).getBleApi().getDeviceSupportedFeatures().isManualStressMeasurementSupported()) {
            this.c = ManualDataRepository.Companion.getInstance(this.f4287a).getLastMeasuredStress(BleApiManager.getInstance(this.f4287a).getBleApi().getMacAddress(), null);
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
        } else if (BleApiManager.getInstance(this.f4287a).getBleApi().getDeviceSupportedFeatures().isStressHistorySupported()) {
            StressRepository.Companion companion = StressRepository.Companion;
            Context context = this.f4287a;
            Intrinsics.checkNotNull(context);
            this.d = companion.getInstance(context).getLatestRecordHourlyLiveData(BleApiManager.getInstance(this.f4287a).getBleApi().getMacAddress());
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new b(null), 2, null);
        }
    }

    @NotNull
    public final MutableLiveData<StressData> getStressLiveData() {
        return this.b;
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        LiveData<EntityManualData> liveData = this.c;
        if (liveData != null) {
            liveData.removeObserver(this.e);
        }
        LiveData<HourlyStress> liveData2 = this.d;
        if (liveData2 != null) {
            liveData2.removeObserver(this.f);
        }
    }
}
