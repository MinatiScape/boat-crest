package com.coveiot.android.leonardo.dashboard.health.spo2.viewmodel;

import android.content.Context;
import android.os.CountDownTimer;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.request.Spo2DataRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.Spo2Response;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.Spo2DataResponse;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.Spo2ResponseType;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentSPO2BleMeasurementViewModel extends ViewModel {
    public static final int MAX_CONSECUTIVE_SPO2 = 15;
    public static final long MAX_MEASUREMENT_TIME = 30000;
    @NotNull
    public static final String TIMERSTOPPED = "STOPPED";
    @NotNull
    public static final String TIMER_STOPPED_WITH_VALUE = "STOPPED_WITH_VALUE";
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4751a;
    @NotNull
    public MutableLiveData<String> b;
    @NotNull
    public MutableLiveData<Boolean> c;
    public CountDownTimer countDownTimer;
    @NotNull
    public MutableLiveData<Spo2DataResponse> d;
    public int e;
    public int f;
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final String g = FragmentSPO2BleMeasurementViewModel.class.getSimpleName();

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTAG() {
            return FragmentSPO2BleMeasurementViewModel.g;
        }
    }

    public FragmentSPO2BleMeasurementViewModel(@NotNull Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.f4751a = mContext;
        this.b = new MutableLiveData<>();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
    }

    public static final void b(FragmentSPO2BleMeasurementViewModel this$0, ConnectionStatus connectionStatus) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (connectionStatus == ConnectionStatus.DISCONNECTED) {
            MutableLiveData<Spo2DataResponse> mutableLiveData = this$0.d;
            Spo2ResponseType spo2ResponseType = Spo2ResponseType.FAILURE;
            String string = this$0.f4751a.getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string, "mContext.getString(R.string.band_not_connected)");
            mutableLiveData.postValue(new Spo2DataResponse(spo2ResponseType, 0.0d, string));
            this$0.b.postValue(TIMERSTOPPED);
        }
    }

    @NotNull
    public final MutableLiveData<Boolean> getConnectionStatus() {
        return this.c;
    }

    @NotNull
    public final CountDownTimer getCountDownTimer() {
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            return countDownTimer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("countDownTimer");
        return null;
    }

    @NotNull
    public final Context getMContext() {
        return this.f4751a;
    }

    public final int getPreviousSpo2() {
        return this.f;
    }

    public final int getSameSpo2Count() {
        return this.e;
    }

    @NotNull
    public final MutableLiveData<Spo2DataResponse> getSpo2ResponseLiveData() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<String> getTimerText() {
        return this.b;
    }

    public final void setConnectionStatus(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setCountDownTimer(@NotNull CountDownTimer countDownTimer) {
        Intrinsics.checkNotNullParameter(countDownTimer, "<set-?>");
        this.countDownTimer = countDownTimer;
    }

    public final void setPreviousSpo2(int i) {
        this.f = i;
    }

    public final void setSameSpo2Count(int i) {
        this.e = i;
    }

    public final void setSpo2ResponseLiveData(@NotNull MutableLiveData<Spo2DataResponse> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setTimerText(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }

    public final void startMeasureSpo2() {
        BleApiManager bleApiManager = BleApiManager.getInstance(this.f4751a);
        DeviceType deviceType = DeviceType.spo2;
        LiveData<ConnectionStatus> registerConnectionStatus = bleApiManager.getSecondaryBleImpl(deviceType).registerConnectionStatus();
        Context context = this.f4751a;
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type androidx.lifecycle.LifecycleOwner");
        registerConnectionStatus.observe((LifecycleOwner) context, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.viewmodel.a
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentSPO2BleMeasurementViewModel.b(FragmentSPO2BleMeasurementViewModel.this, (ConnectionStatus) obj);
            }
        });
        BleApiManager.getInstance(this.f4751a).getSecondaryBleImpl(deviceType).setUserSettings(new Spo2DataRequest.Builder().setEnabled(true).setWaveEnabled(false).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.viewmodel.FragmentSPO2BleMeasurementViewModel$startMeasureSpo2$2
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                String tag = FragmentSPO2BleMeasurementViewModel.Companion.getTAG();
                LogHelper.d(tag, "Spo2Error: " + error);
                FragmentSPO2BleMeasurementViewModel.this.getSpo2ResponseLiveData().postValue(new Spo2DataResponse(Spo2ResponseType.FAILURE, 0.0d, error.getErrorMsg()));
                FragmentSPO2BleMeasurementViewModel.this.getTimerText().postValue(FragmentSPO2BleMeasurementViewModel.TIMERSTOPPED);
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
            }
        });
        final MutableLiveData<Spo2Response> registerLiveSpo2Data = BleApiManager.getInstance(this.f4751a).getSecondaryBleImpl(deviceType).registerLiveSpo2Data();
        if (registerLiveSpo2Data != null) {
            Context context2 = this.f4751a;
            Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type androidx.lifecycle.LifecycleOwner");
            registerLiveSpo2Data.observe((LifecycleOwner) context2, new Observer<Spo2Response>() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.viewmodel.FragmentSPO2BleMeasurementViewModel$startMeasureSpo2$3
                @Override // androidx.lifecycle.Observer
                public void onChanged(@Nullable Spo2Response spo2Response) {
                    Integer spo2;
                    String tag = FragmentSPO2BleMeasurementViewModel.Companion.getTAG();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Spo2Response(value=");
                    sb.append(spo2Response != null ? spo2Response.getSpo2() : null);
                    sb.append(HexStringBuilder.COMMENT_END_CHAR);
                    LogsHelper.d(tag, sb.toString());
                    int intValue = (spo2Response == null || (spo2 = spo2Response.getSpo2()) == null) ? 0 : spo2.intValue();
                    if (intValue > 0) {
                        if (FragmentSPO2BleMeasurementViewModel.this.getPreviousSpo2() == intValue) {
                            FragmentSPO2BleMeasurementViewModel fragmentSPO2BleMeasurementViewModel = FragmentSPO2BleMeasurementViewModel.this;
                            fragmentSPO2BleMeasurementViewModel.setSameSpo2Count(fragmentSPO2BleMeasurementViewModel.getSameSpo2Count() + 1);
                        } else {
                            FragmentSPO2BleMeasurementViewModel.this.setSameSpo2Count(0);
                        }
                        FragmentSPO2BleMeasurementViewModel.this.setPreviousSpo2(intValue);
                        if (FragmentSPO2BleMeasurementViewModel.this.getSameSpo2Count() == 15 || Intrinsics.areEqual(FragmentSPO2BleMeasurementViewModel.this.getTimerText().getValue(), FragmentSPO2BleMeasurementViewModel.TIMER_STOPPED_WITH_VALUE)) {
                            Spo2DataResponse spo2DataResponse = new Spo2DataResponse(Spo2ResponseType.SUCCESS, intValue, null, 4, null);
                            FragmentSPO2BleMeasurementViewModel.this.getSpo2ResponseLiveData().postValue(spo2DataResponse);
                            FragmentSPO2BleMeasurementViewModel.this.getSpo2ResponseLiveData().setValue(spo2DataResponse);
                            registerLiveSpo2Data.removeObserver(this);
                        }
                    }
                }
            });
        }
    }

    public final void startTimer() {
        setCountDownTimer(new CountDownTimer() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.viewmodel.FragmentSPO2BleMeasurementViewModel$startTimer$1
            {
                super(30000L, 1000L);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (FragmentSPO2BleMeasurementViewModel.this.getPreviousSpo2() == 0) {
                    FragmentSPO2BleMeasurementViewModel.this.getTimerText().postValue(FragmentSPO2BleMeasurementViewModel.TIMERSTOPPED);
                } else {
                    FragmentSPO2BleMeasurementViewModel.this.getTimerText().postValue(FragmentSPO2BleMeasurementViewModel.TIMER_STOPPED_WITH_VALUE);
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                long j2 = (j / 1000) % 60;
                String str = "" + j2;
                if (j2 < 10) {
                    StringBuilder sb = new StringBuilder();
                    sb.append('0');
                    sb.append(j2);
                    str = sb.toString();
                }
                FragmentSPO2BleMeasurementViewModel.this.getTimerText().postValue(str + ' ' + FragmentSPO2BleMeasurementViewModel.this.getMContext().getString(R.string.sec));
            }
        });
        getCountDownTimer().start();
    }

    public final void stopSpo2Reading() {
        Spo2DataRequest build = new Spo2DataRequest.Builder().setEnabled(false).build();
        BleApiManager bleApiManager = BleApiManager.getInstance(this.f4751a);
        DeviceType deviceType = DeviceType.spo2;
        bleApiManager.getSecondaryBleImpl(deviceType).setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.viewmodel.FragmentSPO2BleMeasurementViewModel$stopSpo2Reading$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
            }
        });
        BleApiManager.getInstance(this.f4751a).getSecondaryBleImpl(deviceType).unpairDevice();
    }
}
