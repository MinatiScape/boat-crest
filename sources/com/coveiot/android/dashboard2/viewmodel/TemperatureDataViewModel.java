package com.coveiot.android.dashboard2.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.HealthVitalsType;
import com.coveiot.android.dashboard2.model.TemperatureData;
import com.coveiot.android.dashboard2.util.TemperatureDataHelper;
import com.coveiot.covedb.temperature.entity.HourlyTemperature;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.LatestHealthDataModel;
import com.coveiot.covepreferences.listener.LatestHealthDataUpdateListener;
import com.coveiot.repository.temperature.TemperatureRepository;
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
public final class TemperatureDataViewModel extends ViewModel implements LatestHealthDataUpdateListener {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4288a;
    @NotNull
    public final MutableLiveData<TemperatureData> b;

    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.TemperatureDataViewModel$getLastReadTemperature$1", f = "TemperatureDataViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ HourlyTemperature $hourlyTemperature;
        public int label;
        public final /* synthetic */ TemperatureDataViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(HourlyTemperature hourlyTemperature, TemperatureDataViewModel temperatureDataViewModel, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$hourlyTemperature = hourlyTemperature;
            this.this$0 = temperatureDataViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$hourlyTemperature, this.this$0, continuation);
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
                TemperatureDataHelper temperatureDataHelper = TemperatureDataHelper.INSTANCE;
                Pair<Double, Calendar> temperatureData = temperatureDataHelper.getTemperatureData(this.$hourlyTemperature);
                if (temperatureData == null || temperatureData.getFirst().doubleValue() <= 0.0d || temperatureData.getSecond() == null) {
                    this.this$0.b.postValue(new TemperatureData(0.0d, 0L, 3, null));
                } else {
                    TemperatureData temperatureData2 = new TemperatureData(0.0d, 0L, 3, null);
                    Boolean temperatureInFahrenheit = UserDataManager.getInstance(this.this$0.getContext()).isTemperatureUnitInFahrenheit();
                    Intrinsics.checkNotNullExpressionValue(temperatureInFahrenheit, "temperatureInFahrenheit");
                    if (temperatureInFahrenheit.booleanValue()) {
                        temperatureData2.setValue(temperatureDataHelper.getTemperatureInFahrenheit(temperatureData.getFirst().doubleValue()));
                    } else {
                        temperatureData2.setValue(temperatureData.getFirst().doubleValue());
                    }
                    temperatureData2.setTimestamp(temperatureData.getSecond().getTimeInMillis());
                    this.this$0.b.postValue(temperatureData2);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public TemperatureDataViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4288a = context;
        this.b = new MutableLiveData<>();
        UserDataManager.getInstance(context).registerLatestHealthDataUpdateListener(this);
        new Observer() { // from class: com.coveiot.android.dashboard2.viewmodel.q
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                TemperatureDataViewModel.b(TemperatureDataViewModel.this, (HourlyTemperature) obj);
            }
        };
    }

    public static final void b(TemperatureDataViewModel this$0, HourlyTemperature hourlyTemperature) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (hourlyTemperature != null && hourlyTemperature.temperature_avg > 0.0d) {
            TemperatureDataHelper temperatureDataHelper = TemperatureDataHelper.INSTANCE;
            Pair<Double, Calendar> temperatureData = temperatureDataHelper.getTemperatureData(hourlyTemperature);
            if (temperatureData != null && temperatureData.getFirst().doubleValue() > 0.0d && temperatureData.getSecond() != null) {
                TemperatureData temperatureData2 = new TemperatureData(0.0d, 0L, 3, null);
                Boolean temperatureInFahrenheit = UserDataManager.getInstance(this$0.f4288a).isTemperatureUnitInFahrenheit();
                Intrinsics.checkNotNullExpressionValue(temperatureInFahrenheit, "temperatureInFahrenheit");
                if (temperatureInFahrenheit.booleanValue()) {
                    temperatureData2.setValue(temperatureDataHelper.getTemperatureInFahrenheit(temperatureData.getFirst().doubleValue()));
                }
                temperatureData2.setTimestamp(temperatureData.getSecond().getTimeInMillis());
                this$0.b.postValue(temperatureData2);
                return;
            }
            this$0.b.postValue(new TemperatureData(0.0d, 0L, 3, null));
            return;
        }
        this$0.b.postValue(new TemperatureData(0.0d, 0L, 3, null));
    }

    @NotNull
    public final Context getContext() {
        return this.f4288a;
    }

    public final void getLastReadTemperature() {
        if (BleApiManager.getInstance(this.f4288a).getBleApi().getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
            LatestHealthDataModel latestTemperatureValueFromPref = UserDataManager.getInstance(this.f4288a).getLatestTemperatureValueFromPref();
            if (latestTemperatureValueFromPref != null && latestTemperatureValueFromPref.getTimestamp() != 0) {
                latestTemperatureValueFromPref.getValue();
                if (latestTemperatureValueFromPref.getValue() > 0) {
                    if (latestTemperatureValueFromPref.getTimestamp() != 0) {
                        latestTemperatureValueFromPref.getValue();
                        if (latestTemperatureValueFromPref.getValue() > 0) {
                            TemperatureData temperatureData = new TemperatureData(0.0d, 0L, 3, null);
                            TemperatureDataHelper temperatureDataHelper = TemperatureDataHelper.INSTANCE;
                            double parseDouble = Double.parseDouble(temperatureDataHelper.getWithDecimalPointAfterTwoDigit(String.valueOf(latestTemperatureValueFromPref.getValue())));
                            Boolean temperatureInFahrenheit = UserDataManager.getInstance(this.f4288a).isTemperatureUnitInFahrenheit();
                            Intrinsics.checkNotNullExpressionValue(temperatureInFahrenheit, "temperatureInFahrenheit");
                            if (temperatureInFahrenheit.booleanValue()) {
                                temperatureData.setValue(temperatureDataHelper.getTemperatureInFahrenheit(parseDouble));
                            }
                            temperatureData.setTimestamp(latestTemperatureValueFromPref.getTimestamp());
                            this.b.postValue(temperatureData);
                            return;
                        }
                    }
                    this.b.postValue(new TemperatureData(0.0d, 0L, 3, null));
                    return;
                }
            }
            HourlyTemperature latestRecordHourly = TemperatureRepository.Companion.getInstance(this.f4288a).getLatestRecordHourly(BleApiManager.getInstance(this.f4288a).getBleApi().getMacAddress());
            if (latestRecordHourly == null) {
                return;
            }
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new a(latestRecordHourly, this, null), 2, null);
        }
    }

    @NotNull
    public final MutableLiveData<TemperatureData> getTemperatureLiveData() {
        return this.b;
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        UserDataManager.getInstance(this.f4288a).unregisterLatestHealthDataUpdateListener(this);
    }

    @Override // com.coveiot.covepreferences.listener.LatestHealthDataUpdateListener
    public void onUpdate(@Nullable LatestHealthDataModel latestHealthDataModel) {
        if (latestHealthDataModel == null || latestHealthDataModel.getHealthDataType() != HealthVitalsType.TEMPERATURE) {
            return;
        }
        latestHealthDataModel.getValue();
        if (latestHealthDataModel.getValue() > 0) {
            latestHealthDataModel.getTimestamp();
            if (latestHealthDataModel.getTimestamp() > 0) {
                TemperatureData temperatureData = new TemperatureData(0.0d, 0L, 3, null);
                TemperatureDataHelper temperatureDataHelper = TemperatureDataHelper.INSTANCE;
                double parseDouble = Double.parseDouble(temperatureDataHelper.getWithDecimalPointAfterTwoDigit(String.valueOf(latestHealthDataModel.getValue())));
                Boolean temperatureInFahrenheit = UserDataManager.getInstance(this.f4288a).isTemperatureUnitInFahrenheit();
                Intrinsics.checkNotNullExpressionValue(temperatureInFahrenheit, "temperatureInFahrenheit");
                if (temperatureInFahrenheit.booleanValue()) {
                    temperatureData.setValue(temperatureDataHelper.getTemperatureInFahrenheit(parseDouble));
                }
                temperatureData.setTimestamp(latestHealthDataModel.getTimestamp());
                this.b.postValue(temperatureData);
                return;
            }
        }
        this.b.postValue(new TemperatureData(0.0d, 0L, 3, null));
    }
}
