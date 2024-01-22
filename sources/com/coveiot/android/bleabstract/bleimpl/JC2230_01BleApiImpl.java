package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.SetLiftWristRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.services.LeonardoBleCmdService;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.api.model.ProgressDataBean;
import com.coveiot.sdk.ble.api.request.LiftWristViewReq;
import com.coveiot.sdk.ble.api.response.BaseResponse;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Subscribe;
import java.util.UUID;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class JC2230_01BleApiImpl extends CA3BTCallingLeonardoBleApiImpl {
    @Nullable
    public static JC2230_01BleApiImpl A;
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final String z = JC2230_01BleApiImpl.class.getSimpleName();

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final JC2230_01BleApiImpl getInstance(@NotNull Context ctxt) {
            Intrinsics.checkNotNullParameter(ctxt, "ctxt");
            if (JC2230_01BleApiImpl.A == null) {
                LeonardoBleApiImpl.context = ctxt.getApplicationContext();
                JC2230_01BleApiImpl.A = new JC2230_01BleApiImpl();
            }
            LeonardoBleApiImpl.intent = new Intent(LeonardoBleApiImpl.context, LeonardoBleCmdService.class);
            if (!LeonardoBleApiImpl.checkIfServiceIsRunning()) {
                LogHelper.d(JC2230_01BleApiImpl.z, "service is not running ++ ", ModuleNames.BLEABSTRACT.getModuleName());
                try {
                    LeonardoBleApiImpl.context.bindService(LeonardoBleApiImpl.intent, LeonardoBleApiImpl.serviceConnection, 1);
                    if (Build.VERSION.SDK_INT >= 26) {
                        LeonardoBleApiImpl.context.startForegroundService(LeonardoBleApiImpl.intent);
                    } else {
                        LeonardoBleApiImpl.context.startService(LeonardoBleApiImpl.intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Context context = LeonardoBleApiImpl.context;
                    Intrinsics.checkNotNullExpressionValue(context, "context");
                    BleApiUtils.checkExceptionAndShowNotification(e, context);
                }
            } else if (LeonardoBleApiImpl.bleService == null) {
                LeonardoBleApiImpl.context.bindService(LeonardoBleApiImpl.intent, LeonardoBleApiImpl.serviceConnection, 1);
            }
            return JC2230_01BleApiImpl.A;
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
        deviceSupportedFeatures.setMaxDaysOfStepsDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfSleepDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfBpDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfRunDataOnBand(0);
        deviceSupportedFeatures.setMaxDaysOfHeartRateDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfRrDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfCyclingDataOnBand(0);
        deviceSupportedFeatures.setMaxDaysOfSwimmingDataOnBand(0);
        deviceSupportedFeatures.setMaxDaysOfEcgRrDataOnBand(0);
        deviceSupportedFeatures.setScheduledDndSupported(true);
        deviceSupportedFeatures.setMaxDaysOfSpo2DataOnBand(7);
        deviceSupportedFeatures.setStepsSupported(true);
        deviceSupportedFeatures.setSleepSupported(true);
        deviceSupportedFeatures.setREMSupportedInSleep(true);
        deviceSupportedFeatures.setRunSupported(false);
        deviceSupportedFeatures.setHeartRateSupported(true);
        deviceSupportedFeatures.setBpSupported(BleApiUtils.getBleEnableBpV7());
        deviceSupportedFeatures.setTemparatureHistorySupported(false);
        deviceSupportedFeatures.setRrSupported(false);
        deviceSupportedFeatures.setEcgSupported(false);
        deviceSupportedFeatures.setSwimmingSupported(false);
        deviceSupportedFeatures.setCyclingSupported(false);
        deviceSupportedFeatures.setShouldKeepDeviceConnectedAlways(true);
        deviceSupportedFeatures.setSportModeSupportedFromApp(false);
        deviceSupportedFeatures.setGpsSupported(false);
        deviceSupportedFeatures.setCalendarSyncSupported(true);
        deviceSupportedFeatures.setCallNotificationSupported(true);
        deviceSupportedFeatures.setSmsSupported(true);
        deviceSupportedFeatures.setMessageReadSupported(true);
        deviceSupportedFeatures.setSocialNotificationSupported(true);
        deviceSupportedFeatures.setSosSupported(false);
        deviceSupportedFeatures.setSedentaryReminderSupported(true);
        deviceSupportedFeatures.setSedentaryAlertHistorySupported(true);
        deviceSupportedFeatures.setVibrationAlarmSupported(true);
        deviceSupportedFeatures.setBandDisplaySupported(true);
        deviceSupportedFeatures.setHandSettingsSupported(true);
        deviceSupportedFeatures.setCameraFeatureSupported(true);
        deviceSupportedFeatures.setPhoneFinderSupported(true);
        deviceSupportedFeatures.setPersonalInfoSupported(true);
        deviceSupportedFeatures.setStepGoalSupported(true);
        deviceSupportedFeatures.setPhoneTypeCommandSupported(true);
        deviceSupportedFeatures.setTimeFormartCommandSupported(true);
        deviceSupportedFeatures.setTitleSupportedInNotification(true);
        deviceSupportedFeatures.setTimeFormatSettingsSupported(true);
        deviceSupportedFeatures.setProbeFeatureSupported(true);
        deviceSupportedFeatures.setManualSpo2SupportedOnBand(false);
        deviceSupportedFeatures.setBPCalibrationSupported(BleApiUtils.getBleEnableBpV7());
        deviceSupportedFeatures.setAutoHrSettingsSupported(true);
        deviceSupportedFeatures.setAutoTemperatureSettingsSupported(false);
        deviceSupportedFeatures.setDistanceUnitSettingsSupported(true);
        deviceSupportedFeatures.setMusicMetaDataChangeFromAppSupported(true);
        deviceSupportedFeatures.setTemperatureUnitSettingsSupported(false);
        deviceSupportedFeatures.setAppSocialDistanceFeatureSupported(false);
        deviceSupportedFeatures.setBandSocialDistanceFeatureSupported(false);
        deviceSupportedFeatures.setSportsModeHistorySupported(true);
        deviceSupportedFeatures.setLiftWristToViewSettingsSupported(true);
        deviceSupportedFeatures.setMaxCharSupportedInNotification(120);
        deviceSupportedFeatures.setRawPPGHistaoryDataSupported(true);
        deviceSupportedFeatures.setPeriodicSpO2Supported(true);
        deviceSupportedFeatures.setMusicPlaybackStateChangeFromAppSupported(true);
        deviceSupportedFeatures.setCameraFeatureSupported(true);
        deviceSupportedFeatures.setSleepTargetSupported(true);
        deviceSupportedFeatures.set1kActivitySupported(false);
        deviceSupportedFeatures.setFemaleWellnessSupported(false);
        deviceSupportedFeatures.setQuickReplySupported(true);
        deviceSupportedFeatures.setWeatherSupportedInBand(false);
        deviceSupportedFeatures.setCustomMessageSupported(false);
        deviceSupportedFeatures.setKaHaRealtekChip(true);
        deviceSupportedFeatures.setWeatherEnableCommandSupported(false);
        deviceSupportedFeatures.setNotificationConfigurationSupported(true);
        deviceSupportedFeatures.setFindMyBandSupported(true);
        deviceSupportedFeatures.setMaxContactsInOneRequest(20);
        deviceSupportedFeatures.setContactSyncSupported(true);
        deviceSupportedFeatures.setBTCallingSupported(true);
        deviceSupportedFeatures.setKahaBTCallSupported(true);
        deviceSupportedFeatures.setScheduledLiftWristViewSettingsSupported(true);
        Context context = LeonardoBleApiImpl.context;
        Intrinsics.checkNotNullExpressionValue(context, "context");
        String macAddress = getMacAddress();
        Intrinsics.checkNotNullExpressionValue(macAddress, "getMacAddress()");
        return BleApiUtils.updateDeviceSupportedFeatureBasedOnFWAndAppCapability(context, macAddress, deviceSupportedFeatures);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onConnectionStateChanged(@NotNull CloveBleState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        super.onConnectionStateChanged(state);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onError(@NotNull Error error) {
        Intrinsics.checkNotNullParameter(error, "error");
        super.onError(error);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
        Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
        super.onProgressUpdate(progressDataBean);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onResponse(@NotNull BaseResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        super.onResponse(response);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onResponseEventReceivved(@NotNull ResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        super.onResponseEventReceivved(responseEvent);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<BloodPressureMeasurement> registerLiveBpData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        LeonardoBleCmdService leonardoBleCmdService = LeonardoBleApiImpl.bleService;
        if (leonardoBleCmdService != null && leonardoBleCmdService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
            if (request instanceof SetLiftWristRequest) {
                request.setBleCommand(BleCommand.SET_LIFT_WRIST);
                request.setRequId(UUID.randomUUID().toString());
                request.setResponseListener(listener);
                addToQueue(request);
                SetLiftWristRequest setLiftWristRequest = (SetLiftWristRequest) request;
                LiftWristViewReq build = new LiftWristViewReq.Builder(setLiftWristRequest.isLiftWristEnabled()).setScheduledDndSupported(true).setStartHour(setLiftWristRequest.getStartHour()).setStartMinute(setLiftWristRequest.getStartMinute()).setEndHour(setLiftWristRequest.getEndHour()).setEndMinute(setLiftWristRequest.getEndMinute()).build();
                build.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(build, this);
                return;
            }
            super.setUserSettings(request, listener);
        } else if (request.getBleCommand() != null) {
            onSettingsError(listener, new BleBaseError("Band is not connected", request.getBleCommand()));
        } else {
            onSettingsError(listener, new BleBaseError("Band is not connected"));
        }
    }
}
