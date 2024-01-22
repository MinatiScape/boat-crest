package com.coveiot.android.dashboard2.viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.HealthVitalsType;
import com.coveiot.android.dashboard2.model.HeartRateData;
import com.coveiot.android.dashboard2.util.HeartRateDataHelper;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.LatestHealthDataModel;
import com.coveiot.covepreferences.listener.LatestHealthDataUpdateListener;
import com.coveiot.repository.heartrate.HeartRateRepository;
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
public final class HeartRateDataViewModel extends ViewModel implements LatestHealthDataUpdateListener {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4279a;
    @NotNull
    public final MutableLiveData<HeartRateData> b;
    @Nullable
    public LiveData<EntityHourlyHeartRateData> c;
    @NotNull
    public final Observer<EntityHourlyHeartRateData> d;

    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.HeartRateDataViewModel$getLastReadHeartRate$1", f = "HeartRateDataViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                if (HeartRateDataViewModel.this.c != null) {
                    LiveData liveData = HeartRateDataViewModel.this.c;
                    if ((liveData != null ? (EntityHourlyHeartRateData) liveData.getValue() : null) != null) {
                        LiveData liveData2 = HeartRateDataViewModel.this.c;
                        EntityHourlyHeartRateData entityHourlyHeartRateData = liveData2 != null ? (EntityHourlyHeartRateData) liveData2.getValue() : null;
                        Intrinsics.checkNotNull(entityHourlyHeartRateData);
                        if (entityHourlyHeartRateData.getAvgHeartRate() > 0.0f) {
                            HeartRateDataViewModel.this.b.postValue(new HeartRateData(0, 0L, 3, null));
                            return Unit.INSTANCE;
                        }
                    }
                }
                LiveData liveData3 = HeartRateDataViewModel.this.c;
                if (liveData3 != null) {
                    liveData3.observeForever(HeartRateDataViewModel.this.d);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public HeartRateDataViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4279a = context;
        this.b = new MutableLiveData<>();
        UserDataManager.getInstance(context).registerLatestHealthDataUpdateListener(this);
        this.d = new Observer() { // from class: com.coveiot.android.dashboard2.viewmodel.i
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HeartRateDataViewModel.b(HeartRateDataViewModel.this, (EntityHourlyHeartRateData) obj);
            }
        };
    }

    public static final void b(HeartRateDataViewModel this$0, EntityHourlyHeartRateData entityHourlyHeartRateData) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (entityHourlyHeartRateData != null && entityHourlyHeartRateData.getAvgHeartRate() > 0.0f) {
            Pair<Integer, Calendar> heartRateDataBy = HeartRateDataHelper.INSTANCE.getHeartRateDataBy(entityHourlyHeartRateData);
            HeartRateData heartRateData = new HeartRateData(0, 0L, 3, null);
            heartRateData.setValue(heartRateDataBy.getFirst().intValue());
            heartRateData.setTimestamp(heartRateDataBy.getSecond().getTimeInMillis());
            this$0.b.postValue(heartRateData);
            return;
        }
        this$0.b.postValue(new HeartRateData(0, 0L, 3, null));
    }

    @NotNull
    public final Context getContext() {
        return this.f4279a;
    }

    @NotNull
    public final MutableLiveData<HeartRateData> getHeartRateLiveData() {
        return this.b;
    }

    public final void getLastReadHeartRate() {
        this.c = HeartRateRepository.Companion.getInstance(this.f4279a).getLastReadHeartRate(BleApiManager.getInstance(this.f4279a).getBleApi().getMacAddress());
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        UserDataManager.getInstance(this.f4279a).unregisterLatestHealthDataUpdateListener(this);
        LiveData<EntityHourlyHeartRateData> liveData = this.c;
        if (liveData != null) {
            liveData.removeObserver(this.d);
        }
    }

    @Override // com.coveiot.covepreferences.listener.LatestHealthDataUpdateListener
    public void onUpdate(@Nullable LatestHealthDataModel latestHealthDataModel) {
        if (latestHealthDataModel == null || latestHealthDataModel.getHealthDataType() != HealthVitalsType.HEART_RATE) {
            return;
        }
        latestHealthDataModel.getValue();
        if (latestHealthDataModel.getValue() > 0) {
            latestHealthDataModel.getTimestamp();
            if (latestHealthDataModel.getTimestamp() > 0) {
                HeartRateData heartRateData = new HeartRateData(0, 0L, 3, null);
                heartRateData.setValue(latestHealthDataModel.getValue());
                heartRateData.setTimestamp(latestHealthDataModel.getTimestamp());
                this.b.postValue(heartRateData);
                return;
            }
        }
        this.b.postValue(new HeartRateData(0, 0L, 3, null));
    }
}
