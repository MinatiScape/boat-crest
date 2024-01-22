package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.MutableLiveData;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.MeasurementUnitType;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.SetAutomaticPPGConfigRequest;
import com.coveiot.android.bleabstract.request.SetWeatherConfigInfoRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.services.LeonardoBleCmdService;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.api.model.ProgressDataBean;
import com.coveiot.sdk.ble.api.model.TemperatureUnit;
import com.coveiot.sdk.ble.api.request.SetAutomaticPPGConfigReq;
import com.coveiot.sdk.ble.api.request.SetWeatherConfigInfoReq;
import com.coveiot.sdk.ble.api.response.BaseResponse;
import com.coveiot.sdk.ble.api.response.SetAutomaticPPGConfigRes;
import com.coveiot.sdk.ble.api.response.SetWeatherConfigInfoRes;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Subscribe;
import io.shipbook.shipbooksdk.Log;
import java.util.UUID;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class CA3LeonardoBleApiImpl extends CZ1WavePrimeLeonardoBleApiImpl {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final String u = CA3LeonardoBleApiImpl.class.getSimpleName();
    @Nullable
    public static CA3LeonardoBleApiImpl v;
    public final String t = CA3LeonardoBleApiImpl.class.getSimpleName();

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final CA3LeonardoBleApiImpl getInstance(@NotNull Context ctxt) {
            Intrinsics.checkNotNullParameter(ctxt, "ctxt");
            if (CA3LeonardoBleApiImpl.v == null) {
                LeonardoBleApiImpl.context = ctxt.getApplicationContext();
                CA3LeonardoBleApiImpl.v = new CA3LeonardoBleApiImpl();
            }
            LeonardoBleApiImpl.intent = new Intent(LeonardoBleApiImpl.context, LeonardoBleCmdService.class);
            if (!LeonardoBleApiImpl.checkIfServiceIsRunning()) {
                LogHelper.d(CA3LeonardoBleApiImpl.u, "service is not running ++ ", ModuleNames.BLEABSTRACT.getModuleName());
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
            return CA3LeonardoBleApiImpl.v;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CommandType.values().length];
            try {
                iArr[CommandType.SET_WEATHER_CONFIG_INFO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CommandType.SET_AUTOMATIC_PPG_CONFIG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
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
        deviceSupportedFeatures.setMaxDaysOfRawPPGDataOnBand(1);
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
        deviceSupportedFeatures.setFemaleWellnessSupported(true);
        deviceSupportedFeatures.setQuickReplySupported(true);
        deviceSupportedFeatures.setWeatherSupportedInBand(true);
        deviceSupportedFeatures.setCustomMessageSupported(true);
        deviceSupportedFeatures.setKaHaRealtekChip(true);
        deviceSupportedFeatures.setWeatherEnableCommandSupported(true);
        deviceSupportedFeatures.setNotificationConfigurationSupported(true);
        deviceSupportedFeatures.setNudgeSupported(true);
        deviceSupportedFeatures.setFindMyBandSupported(true);
        if (!AppUtils.isEmpty(PreferenceManagerAbstract.getInstance(LeonardoBleApiImpl.context).getDeviceVersionNumber())) {
            String version = PreferenceManagerAbstract.getInstance(LeonardoBleApiImpl.context).getDeviceVersionNumber();
            Intrinsics.checkNotNullExpressionValue(version, "version");
            if (Integer.parseInt(kotlin.text.m.replace$default(kotlin.text.m.replace$default(kotlin.text.m.replace$default(version, CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "", false, 4, (Object) null), ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", false, 4, (Object) null), ".", "", false, 4, (Object) null)) >= 264) {
                deviceSupportedFeatures.setStressHistorySupported(true);
                deviceSupportedFeatures.setStressNudgeSupported(true);
                deviceSupportedFeatures.setHRVHistorySupported(true);
                deviceSupportedFeatures.setAutoStressSettingsSupported(true);
            }
        }
        Context context = LeonardoBleApiImpl.context;
        Intrinsics.checkNotNullExpressionValue(context, "context");
        String macAddress = getMacAddress();
        Intrinsics.checkNotNullExpressionValue(macAddress, "getMacAddress()");
        return BleApiUtils.updateDeviceSupportedFeatureBasedOnFWAndAppCapability(context, macAddress, deviceSupportedFeatures);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onConnectionStateChanged(@NotNull CloveBleState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        super.onConnectionStateChanged(state);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onError(@NotNull Error error) {
        Intrinsics.checkNotNullParameter(error, "error");
        super.onError(error);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
        Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
        super.onProgressUpdate(progressDataBean);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onResponse(@NotNull BaseResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        CommandType activityType = response.getActivityType();
        int i = activityType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[activityType.ordinal()];
        if (i == 1) {
            BleCommand bleCommand = BleCommand.SET_WEATHER_CONFIG_INFO;
            LeonardoBleApiImpl.QueueObject fromQueue = getFromQueue(bleCommand, response);
            if (fromQueue != null) {
                SettingsResultListener settingsResultListener = (SettingsResultListener) fromQueue.f3181a.getResponseListener();
                BleBaseRequest bleBaseRequest = fromQueue.f3181a;
                Intrinsics.checkNotNullExpressionValue(bleBaseRequest, "setWeatherConfigInfo.baseRequest");
                BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
                String str = this.t;
                StringBuilder sb = new StringBuilder();
                sb.append("((SET_WEATHER_CONFIG_INFO) response).isSuccess(): ");
                SetWeatherConfigInfoRes setWeatherConfigInfoRes = (SetWeatherConfigInfoRes) response;
                sb.append(setWeatherConfigInfoRes.isSuccess());
                LogHelper.d(str, sb.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                if (setWeatherConfigInfoRes.isSuccess()) {
                    onSettingsResponse(settingsResultListener, bleBaseResponse);
                } else {
                    onSettingsError(settingsResultListener, new BleBaseError("SET_WEATHER_CONFIG_INFO setting failed", bleCommand));
                }
            }
        } else if (i != 2) {
            super.onResponse(response);
        } else {
            BleCommand bleCommand2 = BleCommand.SET_AUTOMATIC_PPG_CONFIG;
            LeonardoBleApiImpl.QueueObject fromQueue2 = getFromQueue(bleCommand2, response);
            if (fromQueue2 != null) {
                SettingsResultListener settingsResultListener2 = (SettingsResultListener) fromQueue2.f3181a.getResponseListener();
                BleBaseRequest bleBaseRequest2 = fromQueue2.f3181a;
                Intrinsics.checkNotNullExpressionValue(bleBaseRequest2, "setAutomaticPPGConfigReq.baseRequest");
                BleBaseResponse bleBaseResponse2 = new BleBaseResponse(bleBaseRequest2);
                String str2 = this.t;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("((SET_AUTOMATIC_PPG_CONFIG) response).isSuccess(): ");
                SetAutomaticPPGConfigRes setAutomaticPPGConfigRes = (SetAutomaticPPGConfigRes) response;
                sb2.append(setAutomaticPPGConfigRes.isSuccess());
                LogHelper.d(str2, sb2.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                if (setAutomaticPPGConfigRes.isSuccess()) {
                    onSettingsResponse(settingsResultListener2, bleBaseResponse2);
                } else {
                    onSettingsError(settingsResultListener2, new BleBaseError("SET_AUTOMATIC_PPG_CONFIG setting failed", bleCommand2));
                }
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onResponseEventReceivved(@NotNull ResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        super.onResponseEventReceivved(responseEvent);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<BloodPressureMeasurement> registerLiveBpData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        LeonardoBleCmdService leonardoBleCmdService = LeonardoBleApiImpl.bleService;
        if (leonardoBleCmdService != null && leonardoBleCmdService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
            if (request instanceof SetWeatherConfigInfoRequest) {
                TemperatureUnit temperatureUnit = TemperatureUnit.FAHRENHEIT;
                SetWeatherConfigInfoRequest setWeatherConfigInfoRequest = (SetWeatherConfigInfoRequest) request;
                if (setWeatherConfigInfoRequest.getMeasurementUnitType() == MeasurementUnitType.METRIC) {
                    temperatureUnit = TemperatureUnit.CELSIUS;
                }
                SetWeatherConfigInfoReq setWeatherConfigInfoReq = new SetWeatherConfigInfoReq(null, (short) 1, temperatureUnit, setWeatherConfigInfoRequest.isOn());
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.SET_WEATHER_CONFIG_INFO);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                setWeatherConfigInfoReq.setReqId(request.getRequId());
                Log.Companion.d$default(Log.Companion, this.t, "SetWeatherConfigInfoRequest,CA3Impl", null, 4, null);
                LeonardoBleApiImpl.bleService.sendRequest(setWeatherConfigInfoReq, this);
            } else if (request instanceof SetAutomaticPPGConfigRequest) {
                SetAutomaticPPGConfigRequest setAutomaticPPGConfigRequest = (SetAutomaticPPGConfigRequest) request;
                SetAutomaticPPGConfigReq setAutomaticPPGConfigReq = new SetAutomaticPPGConfigReq(null, setAutomaticPPGConfigRequest.getmPPGType().ordinal(), setAutomaticPPGConfigRequest.getStartTime(), setAutomaticPPGConfigRequest.getEndTime(), setAutomaticPPGConfigRequest.getInterval(), setAutomaticPPGConfigRequest.getDuration());
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.SET_AUTOMATIC_PPG_CONFIG);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                setAutomaticPPGConfigReq.setReqId(request.getRequId());
                Log.Companion.d$default(Log.Companion, this.t, "SetAutomaticPPGConfigRequest,CA3Impl", null, 4, null);
                LeonardoBleApiImpl.bleService.sendRequest(setAutomaticPPGConfigReq, this);
            } else {
                super.setUserSettings(request, listener);
            }
        } else if (request.getBleCommand() != null) {
            onSettingsError(listener, new BleBaseError("Band is not connected", request.getBleCommand()));
        } else {
            onSettingsError(listener, new BleBaseError("Band is not connected"));
        }
    }
}
