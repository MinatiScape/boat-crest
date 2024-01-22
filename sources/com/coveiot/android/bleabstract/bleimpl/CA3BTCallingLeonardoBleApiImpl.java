package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.ContactData;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.GetLiftWristSettingsRequest;
import com.coveiot.android.bleabstract.request.SyncContactsRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.GetLiftWristResponse;
import com.coveiot.android.bleabstract.services.LeonardoBleCmdService;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.api.model.LiftWristViewData;
import com.coveiot.sdk.ble.api.model.ProgressDataBean;
import com.coveiot.sdk.ble.api.request.GetLiftWristViewReq;
import com.coveiot.sdk.ble.api.request.SetPhoneBookReq;
import com.coveiot.sdk.ble.api.response.BaseResponse;
import com.coveiot.sdk.ble.api.response.GetLiftWristViewRes;
import com.coveiot.sdk.ble.api.response.LiftWristViewRes;
import com.coveiot.sdk.ble.api.response.SetPhoneBookRes;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class CA3BTCallingLeonardoBleApiImpl extends CA3LeonardoBleApiImpl {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final String x = CA3BTCallingLeonardoBleApiImpl.class.getSimpleName();
    @Nullable
    public static CA3BTCallingLeonardoBleApiImpl y;
    public final String w = CA3BTCallingLeonardoBleApiImpl.class.getSimpleName();

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final CA3BTCallingLeonardoBleApiImpl getInstance(@NotNull Context ctxt) {
            Intrinsics.checkNotNullParameter(ctxt, "ctxt");
            if (CA3BTCallingLeonardoBleApiImpl.y == null) {
                LeonardoBleApiImpl.context = ctxt.getApplicationContext();
                CA3BTCallingLeonardoBleApiImpl.y = new CA3BTCallingLeonardoBleApiImpl();
            }
            LeonardoBleApiImpl.intent = new Intent(LeonardoBleApiImpl.context, LeonardoBleCmdService.class);
            if (!LeonardoBleApiImpl.checkIfServiceIsRunning()) {
                LogHelper.d(CA3BTCallingLeonardoBleApiImpl.x, "service is not running ++ ", ModuleNames.BLEABSTRACT.getModuleName());
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
            return CA3BTCallingLeonardoBleApiImpl.y;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CommandType.values().length];
            try {
                iArr[CommandType.SET_PHONE_BOOK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CommandType.GET_LIFT_WRIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CommandType.SET_LIFT_WRIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        LeonardoBleCmdService leonardoBleCmdService = LeonardoBleApiImpl.bleService;
        if (leonardoBleCmdService == null || leonardoBleCmdService.getConnectionState() != CloveBleState.BleState.CONNECTED) {
            return;
        }
        if (request instanceof SyncContactsRequest) {
            if (((SyncContactsRequest) request).getContactDataArrayList() != null) {
                request.setRequId(UUID.randomUUID().toString());
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.SET_PHONE_BOOK);
                addToQueue(request);
                ArrayList arrayList = new ArrayList();
                Iterator<ContactData> it = ((SyncContactsRequest) request).getContactDataArrayList().iterator();
                while (it.hasNext()) {
                    ContactData next = it.next();
                    com.coveiot.sdk.ble.api.model.ContactData contactData = new com.coveiot.sdk.ble.api.model.ContactData();
                    contactData.setName(next.getName());
                    contactData.setMobileNumber(next.getMobileNumber());
                    arrayList.add(contactData);
                }
                SetPhoneBookReq setPhoneBookReq = new SetPhoneBookReq(null, arrayList);
                setPhoneBookReq.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(setPhoneBookReq, this);
                return;
            }
            onDataError(listener, new BleBaseError("No Data", request.getBleCommand()));
        } else if (request instanceof GetLiftWristSettingsRequest) {
            GetLiftWristViewReq getLiftWristViewReq = new GetLiftWristViewReq(null);
            request.setResponseListener(listener);
            request.setBleCommand(BleCommand.GET_LIFT_WRIST);
            request.setRequId(UUID.randomUUID().toString());
            addToQueue(request);
            getLiftWristViewReq.setReqId(request.getRequId());
            LeonardoBleApiImpl.bleService.sendRequest(getLiftWristViewReq, this);
        } else {
            super.getData(request, listener);
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
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
        deviceSupportedFeatures.setKahaBTCallSupported(true);
        deviceSupportedFeatures.setNotificationConfigurationSupported(true);
        deviceSupportedFeatures.setMaxContactsInOneRequest(20);
        deviceSupportedFeatures.setContactSyncSupported(true);
        deviceSupportedFeatures.setNudgeSupported(true);
        deviceSupportedFeatures.setBTCallingSupported(true);
        deviceSupportedFeatures.setFindMyBandSupported(true);
        deviceSupportedFeatures.setStressHistorySupported(true);
        deviceSupportedFeatures.setStressNudgeSupported(true);
        deviceSupportedFeatures.setHRVHistorySupported(true);
        deviceSupportedFeatures.setAutoStressSettingsSupported(true);
        Context context = LeonardoBleApiImpl.context;
        Intrinsics.checkNotNullExpressionValue(context, "context");
        String macAddress = getMacAddress();
        Intrinsics.checkNotNullExpressionValue(macAddress, "getMacAddress()");
        return BleApiUtils.updateDeviceSupportedFeatureBasedOnFWAndAppCapability(context, macAddress, deviceSupportedFeatures);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onConnectionStateChanged(@NotNull CloveBleState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        super.onConnectionStateChanged(state);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onError(@NotNull Error error) {
        Intrinsics.checkNotNullParameter(error, "error");
        super.onError(error);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
        Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
        super.onProgressUpdate(progressDataBean);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onResponse(@NotNull BaseResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        CommandType activityType = response.getActivityType();
        int i = activityType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[activityType.ordinal()];
        if (i == 1) {
            LogHelper.d(this.w, "SET_PHONE_BOOK response received from parser");
            LeonardoBleApiImpl.QueueObject fromQueue = getFromQueue(BleCommand.SET_PHONE_BOOK, response);
            if (fromQueue == null || !(response instanceof SetPhoneBookRes)) {
                return;
            }
            SetPhoneBookRes setPhoneBookRes = (SetPhoneBookRes) response;
            if (setPhoneBookRes.isSuccess()) {
                BleBaseRequest bleBaseRequest = fromQueue.f3181a;
                Intrinsics.checkNotNullExpressionValue(bleBaseRequest, "setPhoneBookReq.baseRequest");
                BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
                bleBaseResponse.setResponseData(setPhoneBookRes.getResponseData());
                onDataResponse(bleBaseResponse, (DataResultListener) fromQueue.f3181a.getResponseListener());
                return;
            }
            String string = LeonardoBleApiImpl.context.getString(R.string.command_failed);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.command_failed)");
            onDataError((DataResultListener) fromQueue.f3181a.getResponseListener(), new BleBaseError(string, fromQueue.f3181a.getBleCommand()));
        } else if (i != 2) {
            if (i != 3) {
                super.onResponse(response);
                return;
            }
            LeonardoBleApiImpl.QueueObject fromQueue2 = getFromQueue(BleCommand.SET_LIFT_WRIST, response);
            if (fromQueue2 != null) {
                BaseListener responseListener = fromQueue2.f3181a.getResponseListener();
                Intrinsics.checkNotNull(responseListener, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.SettingsResultListener");
                SettingsResultListener settingsResultListener = (SettingsResultListener) responseListener;
                if (response instanceof LiftWristViewRes) {
                    if (((LiftWristViewRes) response).isSuccess()) {
                        BleBaseRequest bleBaseRequest2 = fromQueue2.f3181a;
                        Intrinsics.checkNotNullExpressionValue(bleBaseRequest2, "setLiftWristViewRequest.baseRequest");
                        onSettingsResponse(settingsResultListener, new BleBaseResponse(bleBaseRequest2));
                        return;
                    }
                    onSettingsError(settingsResultListener, new BleBaseError("Set LiftWrist View Failed"));
                }
            }
        } else {
            BleCommand bleCommand = BleCommand.GET_LIFT_WRIST;
            LeonardoBleApiImpl.QueueObject fromQueue3 = getFromQueue(bleCommand, response);
            if (fromQueue3 != null) {
                BaseListener responseListener2 = fromQueue3.f3181a.getResponseListener();
                Intrinsics.checkNotNull(responseListener2, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                DataResultListener dataResultListener = (DataResultListener) responseListener2;
                if (response instanceof GetLiftWristViewRes) {
                    LiftWristViewData liftWristViewData = ((GetLiftWristViewRes) response).getLiftWristViewData();
                    if (liftWristViewData != null) {
                        GetLiftWristResponse getLiftWristResponse = new GetLiftWristResponse();
                        getLiftWristResponse.setLiftWristEnabled(liftWristViewData.isEnabled());
                        getLiftWristResponse.setStartHour(liftWristViewData.getStartHour());
                        getLiftWristResponse.setStartMinute(liftWristViewData.getStartMin());
                        getLiftWristResponse.setEndHour(liftWristViewData.getEndHour());
                        getLiftWristResponse.setEndMinute(liftWristViewData.getEndMin());
                        BleBaseRequest bleBaseRequest3 = fromQueue3.f3181a;
                        Intrinsics.checkNotNullExpressionValue(bleBaseRequest3, "getLiftWristViewRequest.baseRequest");
                        BleBaseResponse bleBaseResponse2 = new BleBaseResponse(bleBaseRequest3);
                        bleBaseResponse2.setResponseData(getLiftWristResponse);
                        bleBaseResponse2.setCompleted(true);
                        onDataResponse(bleBaseResponse2, dataResultListener);
                        return;
                    }
                    return;
                }
                onDataError(dataResultListener, new BleBaseError("GetLiftWristViewReq failed", bleCommand));
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onResponseEventReceivved(@NotNull ResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        super.onResponseEventReceivved(responseEvent);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<BloodPressureMeasurement> registerLiveBpData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        LeonardoBleCmdService leonardoBleCmdService = LeonardoBleApiImpl.bleService;
        if (leonardoBleCmdService != null && leonardoBleCmdService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
            super.setUserSettings(request, listener);
        } else if (request.getBleCommand() != null) {
            onSettingsError(listener, new BleBaseError("Band is not connected", request.getBleCommand()));
        } else {
            onSettingsError(listener, new BleBaseError("Band is not connected"));
        }
    }
}
