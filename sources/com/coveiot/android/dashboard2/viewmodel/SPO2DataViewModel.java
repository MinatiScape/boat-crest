package com.coveiot.android.dashboard2.viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.HealthVitalsType;
import com.coveiot.android.dashboard2.model.SPO2Data;
import com.coveiot.android.dashboard2.util.SPO2DataHelper;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.LatestHealthDataModel;
import com.coveiot.covepreferences.listener.LatestHealthDataUpdateListener;
import com.coveiot.repository.manualdata.ManualDataRepository;
import com.coveiot.repository.periodicspo2.PeriodicSpo2Repository;
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
public final class SPO2DataViewModel extends ViewModel implements LatestHealthDataUpdateListener {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4283a;
    @NotNull
    public final MutableLiveData<SPO2Data> b;
    @Nullable
    public LiveData<EntityManualData> c;
    @NotNull
    public final Observer<EntityManualData> d;

    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.SPO2DataViewModel$getLastReadSPO2$1", f = "SPO2DataViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                LiveData liveData = SPO2DataViewModel.this.c;
                if (liveData != null) {
                    liveData.observeForever(SPO2DataViewModel.this.d);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public SPO2DataViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4283a = context;
        this.b = new MutableLiveData<>();
        UserDataManager.getInstance(context).registerLatestHealthDataUpdateListener(this);
        this.d = new Observer() { // from class: com.coveiot.android.dashboard2.viewmodel.k
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SPO2DataViewModel.b(SPO2DataViewModel.this, (EntityManualData) obj);
            }
        };
    }

    public static final void b(SPO2DataViewModel this$0, EntityManualData entityManualData) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (entityManualData != null && entityManualData.getSpo2() > 0.0d) {
            SPO2Data sPO2Data = new SPO2Data(0.0d, 0L, null, 7, null);
            sPO2Data.setValue(entityManualData.getSpo2());
            sPO2Data.setTimestamp(entityManualData.getTimeStamp());
            sPO2Data.setLevel(entityManualData.getSpo2Level());
            this$0.b.postValue(sPO2Data);
            return;
        }
        this$0.b.postValue(new SPO2Data(0.0d, 0L, null, 7, null));
    }

    @NotNull
    public final Context getContext() {
        return this.f4283a;
    }

    public final void getLastReadSPO2() {
        if (BleApiManager.getInstance(this.f4283a).getBleApi().getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) {
            this.c = ManualDataRepository.Companion.getInstance(this.f4283a).getLastMeasuredSpo2(BleApiManager.getInstance(this.f4283a).getBleApi().getMacAddress());
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
            LiveData<EntityManualData> liveData = this.c;
            if (liveData != null) {
                if ((liveData != null ? (EntityManualData) liveData.getValue() : null) != null) {
                    return;
                }
            }
            this.b.postValue(new SPO2Data(0.0d, 0L, null, 7, null));
        } else if (BleApiManager.getInstance(this.f4283a).getBleApi().getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
            LatestHealthDataModel latestSpo2FromPref = UserDataManager.getInstance(this.f4283a).getLatestSpo2FromPref();
            if ((latestSpo2FromPref != null ? Integer.valueOf(latestSpo2FromPref.getValue()) : null) != null && latestSpo2FromPref.getValue() > 0) {
                SPO2Data sPO2Data = new SPO2Data(0.0d, 0L, null, 7, null);
                Intrinsics.checkNotNull(latestSpo2FromPref);
                sPO2Data.setValue(latestSpo2FromPref.getValue());
                sPO2Data.setTimestamp(latestSpo2FromPref.getTimestamp());
                this.b.postValue(sPO2Data);
            } else if (DeviceUtils.Companion.isTouchELXDevice(this.f4283a)) {
                EntityHourlySpo2 latestRecordHourly = PeriodicSpo2Repository.Companion.getInstance(this.f4283a).getLatestRecordHourly(BleApiManager.getInstance(this.f4283a).getBleApi().getMacAddress());
                if (latestRecordHourly != null && latestRecordHourly.spo2_avg > 0.0d) {
                    Pair<Double, Calendar> sPO2DataBy = SPO2DataHelper.INSTANCE.getSPO2DataBy(latestRecordHourly);
                    SPO2Data sPO2Data2 = new SPO2Data(0.0d, 0L, null, 7, null);
                    sPO2Data2.setValue(sPO2DataBy.getFirst().doubleValue());
                    sPO2Data2.setTimestamp(sPO2DataBy.getSecond().getTimeInMillis());
                    this.b.postValue(sPO2Data2);
                    return;
                }
                this.b.postValue(new SPO2Data(0.0d, 0L, null, 7, null));
            } else {
                this.b.postValue(new SPO2Data(0.0d, 0L, null, 7, null));
            }
        }
    }

    @NotNull
    public final LiveData<SPO2Data> getSPO2LiveData() {
        return this.b;
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        LiveData<EntityManualData> liveData = this.c;
        if (liveData != null) {
            liveData.removeObserver(this.d);
        }
        UserDataManager.getInstance(this.f4283a).unregisterLatestHealthDataUpdateListener(this);
    }

    @Override // com.coveiot.covepreferences.listener.LatestHealthDataUpdateListener
    public void onUpdate(@Nullable LatestHealthDataModel latestHealthDataModel) {
        if (latestHealthDataModel == null || latestHealthDataModel.getHealthDataType() != HealthVitalsType.SPO2) {
            return;
        }
        latestHealthDataModel.getValue();
        if (latestHealthDataModel.getValue() > 0) {
            latestHealthDataModel.getTimestamp();
            if (latestHealthDataModel.getTimestamp() > 0) {
                SPO2Data sPO2Data = new SPO2Data(0.0d, 0L, null, 7, null);
                sPO2Data.setValue(latestHealthDataModel.getValue());
                sPO2Data.setTimestamp(latestHealthDataModel.getTimestamp());
                this.b.postValue(sPO2Data);
                return;
            }
        }
        this.b.postValue(new SPO2Data(0.0d, 0L, null, 7, null));
    }
}
