package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.formatter.LeonardoFormatter;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.MeasurementUnitType;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.ProgressType;
import com.coveiot.android.bleabstract.models.WatchDiagnosticFeature;
import com.coveiot.android.bleabstract.request.BTCallControlRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.GetDiagnosticFeatureTestRequest;
import com.coveiot.android.bleabstract.request.GetQuickReplyListRequest;
import com.coveiot.android.bleabstract.request.SendBTCallInfoRequest;
import com.coveiot.android.bleabstract.request.SendUnbindBTCallIRequest;
import com.coveiot.android.bleabstract.request.SendWeatherRequest;
import com.coveiot.android.bleabstract.request.SetDiagnosticControlRequest;
import com.coveiot.android.bleabstract.request.SetQuickReplyRequest;
import com.coveiot.android.bleabstract.request.SetWeatherUnitRequest;
import com.coveiot.android.bleabstract.request.SetWomenWellnessSettingsRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.GetDiagnosticFeatureTestResponse;
import com.coveiot.android.bleabstract.services.LeonardoBleCmdService;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.api.model.BleGetDiagnosticFeatureTest;
import com.coveiot.sdk.ble.api.model.BleWatchDiagnosticFeature;
import com.coveiot.sdk.ble.api.model.ProgressDataBean;
import com.coveiot.sdk.ble.api.model.SendWeatherModel;
import com.coveiot.sdk.ble.api.model.TemperatureUnit;
import com.coveiot.sdk.ble.api.model.WomenWellnessModel;
import com.coveiot.sdk.ble.api.request.BTCallControlReq;
import com.coveiot.sdk.ble.api.request.GetDiagnosticFeatureTestReq;
import com.coveiot.sdk.ble.api.request.GetQuickReplyListReq;
import com.coveiot.sdk.ble.api.request.SendBTCallInfoReq;
import com.coveiot.sdk.ble.api.request.SendUnbindBTCallReq;
import com.coveiot.sdk.ble.api.request.SendWeatherDataRequest;
import com.coveiot.sdk.ble.api.request.SendWomenWellnessReq;
import com.coveiot.sdk.ble.api.request.SetDiagnosticControlReq;
import com.coveiot.sdk.ble.api.request.SetQuickReplyReq;
import com.coveiot.sdk.ble.api.request.SetWeatherTemperatureUnitReq;
import com.coveiot.sdk.ble.api.response.BTCallControlRes;
import com.coveiot.sdk.ble.api.response.BaseResponse;
import com.coveiot.sdk.ble.api.response.GetDiagnosticFeatureTestRes;
import com.coveiot.sdk.ble.api.response.GetQuickReplyListRes;
import com.coveiot.sdk.ble.api.response.SendBTCallInfoRes;
import com.coveiot.sdk.ble.api.response.SendDiagnosticsRes;
import com.coveiot.sdk.ble.api.response.SendQuickReplyRes;
import com.coveiot.sdk.ble.api.response.SendUnbindBTCallRes;
import com.coveiot.sdk.ble.api.response.SendWeatherRes;
import com.coveiot.sdk.ble.api.response.SetDiagnosticControlRes;
import com.coveiot.sdk.ble.api.response.SetQuickReplyRes;
import com.coveiot.sdk.ble.api.response.SetWeatherTemperatureUnitRes;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Subscribe;
import io.shipbook.shipbooksdk.Log;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class CZ1WavePrimeLeonardoBleApiImpl extends CZ0LeonardoBleApiImpl {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final String r = CZ1WavePrimeLeonardoBleApiImpl.class.getSimpleName();
    @Nullable
    public static CZ1WavePrimeLeonardoBleApiImpl s;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final CZ1WavePrimeLeonardoBleApiImpl getInstance(@NotNull Context ctxt) {
            Intrinsics.checkNotNullParameter(ctxt, "ctxt");
            if (CZ1WavePrimeLeonardoBleApiImpl.s == null) {
                LeonardoBleApiImpl.context = ctxt.getApplicationContext();
                CZ1WavePrimeLeonardoBleApiImpl.s = new CZ1WavePrimeLeonardoBleApiImpl();
            }
            LeonardoBleApiImpl.intent = new Intent(LeonardoBleApiImpl.context, LeonardoBleCmdService.class);
            if (!LeonardoBleApiImpl.checkIfServiceIsRunning()) {
                LogHelper.d(CZ1WavePrimeLeonardoBleApiImpl.r, "service is not running ++ ", ModuleNames.BLEABSTRACT.getModuleName());
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
            return CZ1WavePrimeLeonardoBleApiImpl.s;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CommandType.values().length];
            try {
                iArr[CommandType.SEND_WEATHER_DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CommandType.SET_WEATHER_UNIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CommandType.SET_QUICK_REPLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CommandType.GET_QUICK_REPLY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[CommandType.BT_CALL_CONTROL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[CommandType.SEND_BT_CALL_INFO.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[CommandType.SEND_UNBIND_BT_CALL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[CommandType.SET_DIAGNOSTIC_CONTROL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[CommandType.GET_DIAGNOSTIC_FEATURE_TEST.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ResponseType.values().length];
            try {
                iArr2[ResponseType.SEND_QUICK_REPLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr2[ResponseType.SEND_DIAGNOSTICS_STATUS.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        LeonardoBleCmdService leonardoBleCmdService = LeonardoBleApiImpl.bleService;
        if (leonardoBleCmdService != null && leonardoBleCmdService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
            request.setRequId(UUID.randomUUID().toString());
            if (request instanceof GetQuickReplyListRequest) {
                GetQuickReplyListReq build = new GetQuickReplyListReq.Builder().build();
                build.setResponseListener(this);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.GET_QUICK_REPLY);
                addToQueue(request);
                build.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(build, this);
                return;
            } else if (request instanceof BTCallControlRequest) {
                BTCallControlRequest bTCallControlRequest = (BTCallControlRequest) request;
                BTCallControlReq bTCallControlReq = new BTCallControlReq(null, (short) 1, bTCallControlRequest.isValidFlag(), bTCallControlRequest.isEnabled(), bTCallControlRequest.getDuration());
                bTCallControlReq.setResponseListener(this);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.BT_CALL_CONTROL);
                request.setRequId(request.getRequId());
                addToQueue(request);
                bTCallControlReq.setReqId(request.getRequId());
                Log.Companion.d$default(Log.Companion, r, "EnableBTCallingRequest,CZ1Impl", null, 4, null);
                LeonardoBleApiImpl.bleService.sendRequest(bTCallControlReq, this);
                return;
            } else {
                if (request instanceof GetDiagnosticFeatureTestRequest) {
                    GetDiagnosticFeatureTestRequest getDiagnosticFeatureTestRequest = (GetDiagnosticFeatureTestRequest) request;
                    if (getDiagnosticFeatureTestRequest.getWatchDiagnosticFeature() != null) {
                        WatchDiagnosticFeature watchDiagnosticFeature = getDiagnosticFeatureTestRequest.getWatchDiagnosticFeature();
                        Intrinsics.checkNotNull(watchDiagnosticFeature);
                        if (watchDiagnosticFeature.getmFeatureType() != null) {
                            BleWatchDiagnosticFeature bleWatchDiagnosticFeature = LeonardoFormatter.getBleWatchDiagnosticFeature(getDiagnosticFeatureTestRequest.getWatchDiagnosticFeature());
                            if (bleWatchDiagnosticFeature != null) {
                                GetDiagnosticFeatureTestReq getDiagnosticFeatureTestReq = new GetDiagnosticFeatureTestReq(null, bleWatchDiagnosticFeature);
                                String uuid = UUID.randomUUID().toString();
                                Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
                                GetDiagnosticFeatureTestRequest getDiagnosticFeatureTestRequest2 = new GetDiagnosticFeatureTestRequest();
                                getDiagnosticFeatureTestRequest2.setBleCommand(BleCommand.GET_DIAGNOSTIC_FEATURE_TEST);
                                getDiagnosticFeatureTestRequest2.setResponseListener(listener);
                                getDiagnosticFeatureTestRequest2.setRequId(uuid);
                                addToQueue(getDiagnosticFeatureTestRequest2);
                                getDiagnosticFeatureTestReq.setReqId(getDiagnosticFeatureTestRequest2.getRequId());
                                LeonardoBleApiImpl.bleService.sendRequest(getDiagnosticFeatureTestReq, this);
                                return;
                            }
                            super.getData(request, listener);
                            return;
                        }
                    }
                }
                super.getData(request, listener);
                return;
            }
        }
        onDataError(listener, new BleBaseError("Band not connected", request.getBleCommand()));
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
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
        deviceSupportedFeatures.setTemparatureHistorySupported(true);
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
        deviceSupportedFeatures.setPhoneTypeCommandSupported(false);
        deviceSupportedFeatures.setTimeFormartCommandSupported(true);
        deviceSupportedFeatures.setTitleSupportedInNotification(true);
        deviceSupportedFeatures.setTimeFormatSettingsSupported(true);
        deviceSupportedFeatures.setProbeFeatureSupported(true);
        deviceSupportedFeatures.setManualSpo2SupportedOnBand(false);
        deviceSupportedFeatures.setBPCalibrationSupported(BleApiUtils.getBleEnableBpV7());
        deviceSupportedFeatures.setAutoHrSettingsSupported(true);
        deviceSupportedFeatures.setAutoTemperatureSettingsSupported(true);
        deviceSupportedFeatures.setDistanceUnitSettingsSupported(true);
        deviceSupportedFeatures.setMusicMetaDataChangeFromAppSupported(true);
        deviceSupportedFeatures.setTemperatureUnitSettingsSupported(true);
        deviceSupportedFeatures.setAppSocialDistanceFeatureSupported(false);
        deviceSupportedFeatures.setBandSocialDistanceFeatureSupported(false);
        deviceSupportedFeatures.setSportsModeHistorySupported(true);
        deviceSupportedFeatures.setLiftWristToViewSettingsSupported(true);
        deviceSupportedFeatures.setMaxCharSupportedInNotification(12);
        deviceSupportedFeatures.setRawPPGHistaoryDataSupported(true);
        deviceSupportedFeatures.setPeriodicSpO2Supported(true);
        deviceSupportedFeatures.setMusicPlaybackStateChangeFromAppSupported(true);
        deviceSupportedFeatures.setCameraFeatureSupported(true);
        deviceSupportedFeatures.setSleepTargetSupported(true);
        deviceSupportedFeatures.set1kActivitySupported(true);
        deviceSupportedFeatures.setCustomMessageSupported(true);
        deviceSupportedFeatures.setQuickReplySupported(true);
        deviceSupportedFeatures.setWeatherSupportedInBand(true);
        deviceSupportedFeatures.setNudgeSupported(true);
        deviceSupportedFeatures.setFindMyBandSupported(true);
        Context context = LeonardoBleApiImpl.context;
        Intrinsics.checkNotNullExpressionValue(context, "context");
        String macAddress = getMacAddress();
        Intrinsics.checkNotNullExpressionValue(macAddress, "getMacAddress()");
        return BleApiUtils.updateDeviceSupportedFeatureBasedOnFWAndAppCapability(context, macAddress, deviceSupportedFeatures);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onConnectionStateChanged(@NotNull CloveBleState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        super.onConnectionStateChanged(state);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onError(@NotNull Error error) {
        Intrinsics.checkNotNullParameter(error, "error");
        super.onError(error);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
        BleBaseRequest bleBaseRequest;
        Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
        super.onProgressUpdate(progressDataBean);
        if (progressDataBean.getBaseRequest().getCommandName() == CommandNames.SEND_WEATHER_DATA) {
            LeonardoBleApiImpl.QueueObject fromQueueWith = getFromQueueWith(progressDataBean.getBaseRequest());
            if (fromQueueWith == null || (bleBaseRequest = fromQueueWith.f3181a) == null) {
                return;
            }
            DataResultListener dataResultListener = (DataResultListener) bleBaseRequest.getResponseListener();
            int value = progressDataBean.getValue();
            Intrinsics.checkNotNull(dataResultListener);
            ProgressType progressType = ProgressType.DETERMINATE;
            BleBaseRequest bleBaseRequest2 = fromQueueWith.f3181a;
            Intrinsics.checkNotNullExpressionValue(bleBaseRequest2, "sendWeatherData.baseRequest");
            dataResultListener.onProgressUpdate(new ProgressData(progressType, value, bleBaseRequest2));
            return;
        }
        super.onProgressUpdate(progressDataBean);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onResponse(@NotNull BaseResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        String str = r;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.d(str, "On Response " + response.getActivityType(), moduleNames.getModuleName());
        CommandType activityType = response.getActivityType();
        switch (activityType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[activityType.ordinal()]) {
            case 1:
                LeonardoBleApiImpl.QueueObject fromQueue = getFromQueue(BleCommand.SEND_WEATHER_DATA, response);
                if (fromQueue != null) {
                    SettingsResultListener settingsResultListener = (SettingsResultListener) fromQueue.f3181a.getResponseListener();
                    BleBaseRequest bleBaseRequest = fromQueue.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest, "sendWeatherData.baseRequest");
                    BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
                    StringBuilder sb = new StringBuilder();
                    sb.append("((SendWeatherRes) response).isSuccess(): ");
                    SendWeatherRes sendWeatherRes = (SendWeatherRes) response;
                    sb.append(sendWeatherRes.isSuccess());
                    LogHelper.d(str, sb.toString(), moduleNames.getModuleName());
                    if (sendWeatherRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener, bleBaseResponse);
                        return;
                    } else {
                        onSettingsError(settingsResultListener, new BleBaseError("SendWeatherData setting failed", BleCommand.SET_VIBRATION));
                        return;
                    }
                }
                return;
            case 2:
                BleCommand bleCommand = BleCommand.SET_WEATHER_UNIT;
                LeonardoBleApiImpl.QueueObject fromQueue2 = getFromQueue(bleCommand, response);
                if (fromQueue2 != null) {
                    SettingsResultListener settingsResultListener2 = (SettingsResultListener) fromQueue2.f3181a.getResponseListener();
                    BleBaseRequest bleBaseRequest2 = fromQueue2.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest2, "setWeatherUnit.baseRequest");
                    BleBaseResponse bleBaseResponse2 = new BleBaseResponse(bleBaseRequest2);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("((SET_WEATHER_UNIT) response).isSuccess(): ");
                    SetWeatherTemperatureUnitRes setWeatherTemperatureUnitRes = (SetWeatherTemperatureUnitRes) response;
                    sb2.append(setWeatherTemperatureUnitRes.isSuccess());
                    LogHelper.d(str, sb2.toString(), moduleNames.getModuleName());
                    if (setWeatherTemperatureUnitRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener2, bleBaseResponse2);
                        return;
                    } else {
                        onSettingsError(settingsResultListener2, new BleBaseError("SET_WEATHER_UNIT setting failed", bleCommand));
                        return;
                    }
                }
                return;
            case 3:
                BleCommand bleCommand2 = BleCommand.SET_QUICK_REPLY;
                LeonardoBleApiImpl.QueueObject fromQueue3 = getFromQueue(bleCommand2, response);
                Intrinsics.checkNotNullExpressionValue(fromQueue3, "getFromQueue(BleCommand.SET_QUICK_REPLY, response)");
                BaseListener responseListener = fromQueue3.f3181a.getResponseListener();
                Intrinsics.checkNotNull(responseListener, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.SettingsResultListener");
                SettingsResultListener settingsResultListener3 = (SettingsResultListener) responseListener;
                if (((SetQuickReplyRes) response).isSuccess()) {
                    BleBaseRequest bleBaseRequest3 = fromQueue3.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest3, "setQuickReplyReq.baseRequest");
                    onSettingsResponse(settingsResultListener3, new BleBaseResponse(bleBaseRequest3));
                    return;
                }
                onSettingsError(settingsResultListener3, new BleBaseError("Quick Reply failed", bleCommand2));
                return;
            case 4:
                LogHelper.d(str, "GET_QUICK_REPLY response received from parser");
                LeonardoBleApiImpl.QueueObject fromQueue4 = getFromQueue(BleCommand.GET_QUICK_REPLY, response);
                if (fromQueue4 == null || !(response instanceof GetQuickReplyListRes)) {
                    return;
                }
                BleBaseRequest bleBaseRequest4 = fromQueue4.f3181a;
                Intrinsics.checkNotNullExpressionValue(bleBaseRequest4, "getQuickReplyListReq.baseRequest");
                BleBaseResponse bleBaseResponse3 = new BleBaseResponse(bleBaseRequest4);
                bleBaseResponse3.setResponseData(((GetQuickReplyListRes) response).getBleQuickReply());
                onDataResponse(bleBaseResponse3, (DataResultListener) fromQueue4.f3181a.getResponseListener());
                return;
            case 5:
                LogHelper.d(str, "BT_CALL_CONTROL response received from parser");
                LeonardoBleApiImpl.QueueObject fromQueue5 = getFromQueue(BleCommand.BT_CALL_CONTROL, response);
                if (fromQueue5 == null || !(response instanceof BTCallControlRes)) {
                    return;
                }
                BleBaseRequest bleBaseRequest5 = fromQueue5.f3181a;
                Intrinsics.checkNotNullExpressionValue(bleBaseRequest5, "enableBTCallingReq.baseRequest");
                BleBaseResponse bleBaseResponse4 = new BleBaseResponse(bleBaseRequest5);
                bleBaseResponse4.setResponseData(((BTCallControlRes) response).getBtCallingEnableData());
                onDataResponse(bleBaseResponse4, (DataResultListener) fromQueue5.f3181a.getResponseListener());
                return;
            case 6:
                BleCommand bleCommand3 = BleCommand.SEND_BT_CALL_INFO;
                LeonardoBleApiImpl.QueueObject fromQueue6 = getFromQueue(bleCommand3, response);
                if (fromQueue6 != null) {
                    SettingsResultListener settingsResultListener4 = (SettingsResultListener) fromQueue6.f3181a.getResponseListener();
                    BleBaseRequest bleBaseRequest6 = fromQueue6.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest6, "sendBTCallInfo.baseRequest");
                    BleBaseResponse bleBaseResponse5 = new BleBaseResponse(bleBaseRequest6);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("((SEND_BT_CALL_INFO) response).isSuccess(): ");
                    SendBTCallInfoRes sendBTCallInfoRes = (SendBTCallInfoRes) response;
                    sb3.append(sendBTCallInfoRes.isSuccess());
                    LogHelper.d(str, sb3.toString(), moduleNames.getModuleName());
                    if (sendBTCallInfoRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener4, bleBaseResponse5);
                        return;
                    } else {
                        onSettingsError(settingsResultListener4, new BleBaseError("SEND_BT_CALL_INFO setting failed", bleCommand3));
                        return;
                    }
                }
                return;
            case 7:
                BleCommand bleCommand4 = BleCommand.SEND_UNBIND_BT_CALL;
                LeonardoBleApiImpl.QueueObject fromQueue7 = getFromQueue(bleCommand4, response);
                if (fromQueue7 != null) {
                    SettingsResultListener settingsResultListener5 = (SettingsResultListener) fromQueue7.f3181a.getResponseListener();
                    BleBaseRequest bleBaseRequest7 = fromQueue7.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest7, "sendUnBindBTCall.baseRequest");
                    BleBaseResponse bleBaseResponse6 = new BleBaseResponse(bleBaseRequest7);
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("((SEND_UNBIND_BT_CALL) response).isSuccess(): ");
                    SendUnbindBTCallRes sendUnbindBTCallRes = (SendUnbindBTCallRes) response;
                    sb4.append(sendUnbindBTCallRes.isSuccess());
                    LogHelper.d(str, sb4.toString(), moduleNames.getModuleName());
                    if (sendUnbindBTCallRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener5, bleBaseResponse6);
                        return;
                    } else {
                        onSettingsError(settingsResultListener5, new BleBaseError("SEND_UNBIND_BT_CALL setting failed", bleCommand4));
                        return;
                    }
                }
                return;
            case 8:
                LogHelper.d(str, "On Response " + response);
                BleCommand bleCommand5 = BleCommand.SET_DIAGNOSTIC_CONTROL;
                LeonardoBleApiImpl.QueueObject fromQueue8 = getFromQueue(bleCommand5, response);
                if (fromQueue8 != null) {
                    SettingsResultListener settingsResultListener6 = (SettingsResultListener) fromQueue8.f3181a.getResponseListener();
                    BleBaseRequest bleBaseRequest8 = fromQueue8.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest8, "setDiagnosticControl.baseRequest");
                    BleBaseResponse bleBaseResponse7 = new BleBaseResponse(bleBaseRequest8);
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("((SET_DIAGNOSTIC_CONTROL) response).isSuccess(): ");
                    SetDiagnosticControlRes setDiagnosticControlRes = (SetDiagnosticControlRes) response;
                    sb5.append(setDiagnosticControlRes.isSuccess());
                    LogHelper.d(str, sb5.toString(), moduleNames.getModuleName());
                    if (setDiagnosticControlRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener6, bleBaseResponse7);
                        return;
                    } else {
                        onSettingsError(settingsResultListener6, new BleBaseError("SET_DIAGNOSTIC_CONTROL setting failed", bleCommand5));
                        return;
                    }
                }
                return;
            case 9:
                BleCommand bleCommand6 = BleCommand.GET_DIAGNOSTIC_FEATURE_TEST;
                LeonardoBleApiImpl.QueueObject fromQueue9 = getFromQueue(bleCommand6, response);
                if (fromQueue9 != null) {
                    BaseListener responseListener2 = fromQueue9.f3181a.getResponseListener();
                    Intrinsics.checkNotNull(responseListener2, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                    DataResultListener dataResultListener = (DataResultListener) responseListener2;
                    if (response instanceof GetDiagnosticFeatureTestRes) {
                        BleGetDiagnosticFeatureTest bleGetDiagnosticFeatureTest = ((GetDiagnosticFeatureTestRes) response).getBleGetDiagnosticFeatureTest();
                        if (bleGetDiagnosticFeatureTest != null) {
                            GetDiagnosticFeatureTestResponse getDiagnosticFeatureTestResponse = new GetDiagnosticFeatureTestResponse();
                            getDiagnosticFeatureTestResponse.setStatus(bleGetDiagnosticFeatureTest.getStatus());
                            getDiagnosticFeatureTestResponse.setFeature(bleGetDiagnosticFeatureTest.getFeature());
                            getDiagnosticFeatureTestResponse.setDeploy(bleGetDiagnosticFeatureTest.getDeploy());
                            BleBaseRequest bleBaseRequest9 = fromQueue9.f3181a;
                            Intrinsics.checkNotNullExpressionValue(bleBaseRequest9, "getDiagnosticFeatureTestRequest.baseRequest");
                            BleBaseResponse bleBaseResponse8 = new BleBaseResponse(bleBaseRequest9);
                            bleBaseResponse8.setResponseData(getDiagnosticFeatureTestResponse);
                            bleBaseResponse8.setCompleted(true);
                            onDataResponse(bleBaseResponse8, dataResultListener);
                            return;
                        }
                        return;
                    }
                    onDataError(dataResultListener, new BleBaseError("getDiagnosticFeatureTestRequest failed", bleCommand6));
                    return;
                }
                return;
            default:
                super.onResponse(response);
                return;
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onResponseEventReceivved(@NotNull ResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        super.onResponseEventReceivved(responseEvent);
        ResponseType type = responseEvent.getType();
        int i = type == null ? -1 : WhenMappings.$EnumSwitchMapping$1[type.ordinal()];
        if (i == 1) {
            Object data = responseEvent.getData();
            Intrinsics.checkNotNull(data, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.response.SendQuickReplyRes");
            Intent intent = new Intent(Constants.SEND_QUICK_REPLY_BROADCAST_INTENT);
            intent.putExtra(Constants.SEND_QUICK_REPLY_BROADCAST_INTENT_EXTRA, (SendQuickReplyRes) data);
            LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(intent);
        } else if (i != 2) {
        } else {
            Object data2 = responseEvent.getData();
            Intrinsics.checkNotNull(data2, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.response.SendDiagnosticsRes");
            Intent intent2 = new Intent(Constants.SEND_DIAGNOSTICS_BROADCAST_INTENT_EXTRA);
            intent2.putExtra(Constants.SEND_DIAGNOSTICS_BROADCAST_INTENT_EXTRA, (SendDiagnosticsRes) data2);
            LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(intent2);
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<BloodPressureMeasurement> registerLiveBpData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        LeonardoBleCmdService leonardoBleCmdService = LeonardoBleApiImpl.bleService;
        if (leonardoBleCmdService != null && leonardoBleCmdService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
            request.setRequId(UUID.randomUUID().toString());
            if (request instanceof SetQuickReplyRequest) {
                SetQuickReplyRequest setQuickReplyRequest = (SetQuickReplyRequest) request;
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.SET_QUICK_REPLY);
                addToQueue(request);
                SetQuickReplyReq setQuickReplyReq = new SetQuickReplyReq(null, setQuickReplyRequest.isQuickReply(), setQuickReplyRequest.getBleQuickReplyModelList());
                setQuickReplyReq.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(setQuickReplyReq, this);
            } else if (request instanceof SendWeatherRequest) {
                SendWeatherRequest sendWeatherRequest = (SendWeatherRequest) request;
                String placeName = sendWeatherRequest.getPlaceName();
                List<SendWeatherModel> listSendWeatherModel = sendWeatherRequest.getListSendWeatherModel();
                Intrinsics.checkNotNull(listSendWeatherModel);
                SendWeatherDataRequest sendWeatherDataRequest = new SendWeatherDataRequest(null, placeName, listSendWeatherModel);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.SEND_WEATHER_DATA);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                sendWeatherDataRequest.setReqId(request.getRequId());
                Log.Companion.d$default(Log.Companion, r, "SetUserSettings,CZ1Impl", null, 4, null);
                LeonardoBleApiImpl.bleService.sendRequest(sendWeatherDataRequest, this);
            } else if (request instanceof SetWeatherUnitRequest) {
                TemperatureUnit temperatureUnit = TemperatureUnit.FAHRENHEIT;
                if (((SetWeatherUnitRequest) request).getMeasurementUnitType() == MeasurementUnitType.METRIC) {
                    temperatureUnit = TemperatureUnit.CELSIUS;
                }
                SetWeatherTemperatureUnitReq setWeatherTemperatureUnitReq = new SetWeatherTemperatureUnitReq(null, (short) 1, temperatureUnit);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.SET_WEATHER_UNIT);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                setWeatherTemperatureUnitReq.setReqId(request.getRequId());
                Log.Companion.d$default(Log.Companion, r, "SetWeatherUnitRequest,CZ1Impl", null, 4, null);
                LeonardoBleApiImpl.bleService.sendRequest(setWeatherTemperatureUnitReq, this);
            } else if (request instanceof SendBTCallInfoRequest) {
                SendBTCallInfoRequest sendBTCallInfoRequest = (SendBTCallInfoRequest) request;
                SendBTCallInfoReq sendBTCallInfoReq = new SendBTCallInfoReq(null, (short) 1, sendBTCallInfoRequest.getCallStatus(), sendBTCallInfoRequest.getNumber(), sendBTCallInfoRequest.getName());
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.SEND_BT_CALL_INFO);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                sendBTCallInfoReq.setReqId(request.getRequId());
                Log.Companion.d$default(Log.Companion, r, "SendBTCallInfoRequest,CZ1Impl", null, 4, null);
                LeonardoBleApiImpl.bleService.sendRequest(sendBTCallInfoReq, this);
            } else if (request instanceof SendUnbindBTCallIRequest) {
                SendUnbindBTCallReq sendUnbindBTCallReq = new SendUnbindBTCallReq(null, (short) 1);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.SEND_UNBIND_BT_CALL);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                sendUnbindBTCallReq.setReqId(request.getRequId());
                Log.Companion.d$default(Log.Companion, r, "sendUnbindBTCallIReq,CZ1Impl", null, 4, null);
                LeonardoBleApiImpl.bleService.sendRequest(sendUnbindBTCallReq, this);
            } else if (request instanceof SetWomenWellnessSettingsRequest) {
                WomenWellnessModel womenWellnessModel = new WomenWellnessModel();
                Calendar calendar = Calendar.getInstance();
                SetWomenWellnessSettingsRequest setWomenWellnessSettingsRequest = (SetWomenWellnessSettingsRequest) request;
                calendar.set(1, setWomenWellnessSettingsRequest.getMLastPeriodYear());
                int i = 2;
                calendar.set(2, setWomenWellnessSettingsRequest.getMLastPeriodMonth() - 1);
                calendar.set(5, setWomenWellnessSettingsRequest.getMLastPeriodDay());
                calendar.set(11, 0);
                calendar.set(12, 0);
                calendar.set(13, 0);
                calendar.set(14, 0);
                if (setWomenWellnessSettingsRequest.getMMenstrualReminder() && setWomenWellnessSettingsRequest.getMOvulationReminder()) {
                    i = 1;
                } else if ((!setWomenWellnessSettingsRequest.getMMenstrualReminder() || setWomenWellnessSettingsRequest.getMOvulationReminder()) && (setWomenWellnessSettingsRequest.getMMenstrualReminder() || setWomenWellnessSettingsRequest.getMOvulationReminder())) {
                    i = 0;
                }
                setWomenWellnessSettingsRequest.getMReminderHour();
                int mReminderHour = (setWomenWellnessSettingsRequest.getMReminderHour() * 60) + setWomenWellnessSettingsRequest.getMReminderMinute();
                womenWellnessModel.setEnabled(setWomenWellnessSettingsRequest.getMEnabled());
                womenWellnessModel.setLastMenstrualTimestamp(calendar.getTimeInMillis() / 1000);
                womenWellnessModel.setMenstrualPeriodLength((short) setWomenWellnessSettingsRequest.getMMenstruationPeriodLength());
                womenWellnessModel.setMenstrualCycleLength((short) setWomenWellnessSettingsRequest.getMMenstruationCycleLength());
                womenWellnessModel.setMenstruationNotificationDay((short) setWomenWellnessSettingsRequest.getMPeriodReminderAdvance());
                womenWellnessModel.setMenstruationNotificationMinute(mReminderHour);
                womenWellnessModel.setOvulationNotificationDay((short) setWomenWellnessSettingsRequest.getMOvulationReminderAdvance());
                womenWellnessModel.setOvulationNotificationMinute(mReminderHour);
                womenWellnessModel.setReminderType((short) i);
                womenWellnessModel.setVibrationEnabled(true);
                SendWomenWellnessReq sendWomenWellnessReq = new SendWomenWellnessReq(null, womenWellnessModel);
                sendWomenWellnessReq.setResponseListener(this);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.SET_WOMEN_WELLNESS);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                sendWomenWellnessReq.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(sendWomenWellnessReq, this);
            } else if (request instanceof SetDiagnosticControlRequest) {
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.SET_DIAGNOSTIC_CONTROL);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                SetDiagnosticControlReq setDiagnosticControlReq = new SetDiagnosticControlReq(null, (short) 1, ((SetDiagnosticControlRequest) request).isDiagnosticControl());
                setDiagnosticControlReq.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(setDiagnosticControlReq, this);
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
