package com.coveiot.android.dashboard2.viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.dashboard2.model.EnergyMeterData;
import com.coveiot.android.sleepenergyscore.energymeter.database.EnergyScoreRepository;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyData;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyScoreDbData;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.UtilConstants;
import java.util.ArrayList;
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
public final class EnergyMeterDataViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4271a;
    @NotNull
    public final MutableLiveData<EnergyMeterData> b;
    @Nullable
    public LiveData<EnergyScoreDbData> c;
    @NotNull
    public final Observer<EnergyScoreDbData> d;

    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.EnergyMeterDataViewModel$getEnergyMeterData$1", f = "EnergyMeterDataViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                LiveData liveData = EnergyMeterDataViewModel.this.c;
                if (liveData != null) {
                    liveData.observeForever(EnergyMeterDataViewModel.this.d);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public EnergyMeterDataViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4271a = context;
        this.b = new MutableLiveData<>();
        this.d = new Observer() { // from class: com.coveiot.android.dashboard2.viewmodel.a
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EnergyMeterDataViewModel.b(EnergyMeterDataViewModel.this, (EnergyScoreDbData) obj);
            }
        };
    }

    public static final void b(EnergyMeterDataViewModel this$0, EnergyScoreDbData energyScoreDbData) {
        EnergyData energyData;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (energyScoreDbData != null) {
            ArrayList<EnergyData> arrayList = energyScoreDbData.data;
            if (arrayList != null && (energyData = arrayList.get(0)) != null) {
                EnergyMeterData energyMeterData = new EnergyMeterData(0, 0L, 3, null);
                if (energyData.getCurrentEnergyLevel() != null) {
                    Integer currentEnergyLevel = energyData.getCurrentEnergyLevel();
                    Intrinsics.checkNotNull(currentEnergyLevel);
                    energyMeterData.setValue(currentEnergyLevel.intValue());
                    energyMeterData.setTimestamp(AppUtils.parseDateUTC(energyData.getComputedDate(), UtilConstants.SERVER_TIME_FORMAT).getTime());
                    MutableLiveData<EnergyMeterData> mutableLiveData = this$0.b;
                    if (mutableLiveData != null) {
                        mutableLiveData.postValue(energyMeterData);
                        return;
                    }
                    return;
                }
                MutableLiveData<EnergyMeterData> mutableLiveData2 = this$0.b;
                if (mutableLiveData2 != null) {
                    mutableLiveData2.postValue(new EnergyMeterData(0, 0L, 3, null));
                    return;
                }
                return;
            }
            MutableLiveData<EnergyMeterData> mutableLiveData3 = this$0.b;
            if (mutableLiveData3 != null) {
                mutableLiveData3.postValue(new EnergyMeterData(0, 0L, 3, null));
            }
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f4271a;
    }

    public final void getEnergyMeterData() {
        this.c = EnergyScoreRepository.Companion.getInstance(this.f4271a).getLastSyncedEnergyScoreLiveData(BleApiManager.getInstance(this.f4271a).getBleApi().getMacAddress());
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
        LiveData<EnergyScoreDbData> liveData = this.c;
        if (liveData != null) {
            if ((liveData != null ? liveData.getValue() : null) != null) {
                return;
            }
        }
        this.b.postValue(new EnergyMeterData(0, 0L, 3, null));
    }

    @NotNull
    public final MutableLiveData<EnergyMeterData> getEnergyMeterLiveData() {
        return this.b;
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        LiveData<EnergyScoreDbData> liveData = this.c;
        if (liveData != null) {
            liveData.removeObserver(this.d);
        }
    }
}
