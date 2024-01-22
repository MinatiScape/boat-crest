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
import com.coveiot.android.bleabstract.models.HButtonType;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.ProgressType;
import com.coveiot.android.bleabstract.models.SmartAlertAppData;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.CustomMessageRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest;
import com.coveiot.android.bleabstract.request.GetButtonFunctionRequest;
import com.coveiot.android.bleabstract.request.GetNavigationConfigurationRequest;
import com.coveiot.android.bleabstract.request.GetNavigationDisclaimerRequest;
import com.coveiot.android.bleabstract.request.GetNavigationFavouriteLocationRequest;
import com.coveiot.android.bleabstract.request.GetNavigationWatchStateRequest;
import com.coveiot.android.bleabstract.request.GetSmartAlertConfigRequest;
import com.coveiot.android.bleabstract.request.SendImageRequest;
import com.coveiot.android.bleabstract.request.SetButtonFunctionRequest;
import com.coveiot.android.bleabstract.request.SetCallSmsSocialNotificationRequest;
import com.coveiot.android.bleabstract.request.SetNavigationAliveTimerRequest;
import com.coveiot.android.bleabstract.request.SetNavigationApplicationContentRequest;
import com.coveiot.android.bleabstract.request.SetNavigationConfigurationRequest;
import com.coveiot.android.bleabstract.request.SetNavigationDisclaimerRequest;
import com.coveiot.android.bleabstract.request.SetNavigationEventRequest;
import com.coveiot.android.bleabstract.request.SetNavigationFavouriteLocationRequest;
import com.coveiot.android.bleabstract.request.SetNavigationStatusRequest;
import com.coveiot.android.bleabstract.request.SetNonSmartAlertApplicationContentRequest;
import com.coveiot.android.bleabstract.request.SetSmartAlertApplicationContentRequest;
import com.coveiot.android.bleabstract.request.SetSmartAlertConfigRequest;
import com.coveiot.android.bleabstract.request.SportsNotificationRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.GetButtonFunctionResponse;
import com.coveiot.android.bleabstract.response.GetNavigationConfigurationResponse;
import com.coveiot.android.bleabstract.response.GetNavigationDisclaimerResponse;
import com.coveiot.android.bleabstract.response.GetNavigationFavouriteLocationResponse;
import com.coveiot.android.bleabstract.response.GetNavigationWatchStateResponse;
import com.coveiot.android.bleabstract.response.GetSmartAlertConfigResponse;
import com.coveiot.android.bleabstract.services.LeonardoBleCmdService;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.api.model.BleGetButtonFunctionData;
import com.coveiot.sdk.ble.api.model.BleGetSmartAlertConfigData;
import com.coveiot.sdk.ble.api.model.BleNavigationConfigurationData;
import com.coveiot.sdk.ble.api.model.BleNavigationDisclaimerData;
import com.coveiot.sdk.ble.api.model.BleSmartAlertAppData;
import com.coveiot.sdk.ble.api.model.FavouriteLocationData;
import com.coveiot.sdk.ble.api.model.NavigationWatchStateData;
import com.coveiot.sdk.ble.api.model.ProgressDataBean;
import com.coveiot.sdk.ble.api.request.CustomMessageReq;
import com.coveiot.sdk.ble.api.request.CustomWatchFaceUploadReq;
import com.coveiot.sdk.ble.api.request.GetButtonFunctionReq;
import com.coveiot.sdk.ble.api.request.GetNavigationConfigurationReq;
import com.coveiot.sdk.ble.api.request.GetNavigationDisclaimerReq;
import com.coveiot.sdk.ble.api.request.GetNavigationFavouriteLocationReq;
import com.coveiot.sdk.ble.api.request.GetNavigationWatchStateReq;
import com.coveiot.sdk.ble.api.request.GetSmartAlertConfigReq;
import com.coveiot.sdk.ble.api.request.MessageAlertSwitchesReq;
import com.coveiot.sdk.ble.api.request.SendImageReq;
import com.coveiot.sdk.ble.api.request.SetButtonFunctionReq;
import com.coveiot.sdk.ble.api.request.SetNavigationAliveTimerReq;
import com.coveiot.sdk.ble.api.request.SetNavigationApplicationContentReq;
import com.coveiot.sdk.ble.api.request.SetNavigationConfigurationReq;
import com.coveiot.sdk.ble.api.request.SetNavigationDisclaimerReq;
import com.coveiot.sdk.ble.api.request.SetNavigationEventReq;
import com.coveiot.sdk.ble.api.request.SetNavigationFavouriteLocationReq;
import com.coveiot.sdk.ble.api.request.SetNavigationStatusReq;
import com.coveiot.sdk.ble.api.request.SetNonSmartAlertApplicationContentReq;
import com.coveiot.sdk.ble.api.request.SetSmartAlertApplicationContentReq;
import com.coveiot.sdk.ble.api.request.SetSmartAlertConfigReq;
import com.coveiot.sdk.ble.api.request.SportsNotificationReq;
import com.coveiot.sdk.ble.api.response.BaseResponse;
import com.coveiot.sdk.ble.api.response.GetButtonFunctionRes;
import com.coveiot.sdk.ble.api.response.GetNavigationConfigurationRes;
import com.coveiot.sdk.ble.api.response.GetNavigationDisclaimerRes;
import com.coveiot.sdk.ble.api.response.GetNavigationFavouriteLocationRes;
import com.coveiot.sdk.ble.api.response.GetNavigationWatchStateRes;
import com.coveiot.sdk.ble.api.response.GetSmartAlertConfigRes;
import com.coveiot.sdk.ble.api.response.NotifyNavigationEventRes;
import com.coveiot.sdk.ble.api.response.SetButtonFunctionRes;
import com.coveiot.sdk.ble.api.response.SetNavigationAliveTimerRes;
import com.coveiot.sdk.ble.api.response.SetNavigationApplicationContentRes;
import com.coveiot.sdk.ble.api.response.SetNavigationConfigurationRes;
import com.coveiot.sdk.ble.api.response.SetNavigationDisclaimerRes;
import com.coveiot.sdk.ble.api.response.SetNavigationEventRes;
import com.coveiot.sdk.ble.api.response.SetNavigationFavouriteLocationRes;
import com.coveiot.sdk.ble.api.response.SetNavigationStatusRes;
import com.coveiot.sdk.ble.api.response.SetNonSmartAlertApplicationContentRes;
import com.coveiot.sdk.ble.api.response.SetSmartAlertApplicationContentRes;
import com.coveiot.sdk.ble.api.response.SetSmartAlertConfigRes;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.DevicePlatformEnum;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class PS1EnigmaOasisBleApiImpl extends CY1BleApiImpl {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final String D = PS1EnigmaOasisBleApiImpl.class.getSimpleName();
    @Nullable
    public static PS1EnigmaOasisBleApiImpl E;
    public final String C = PS1EnigmaOasisBleApiImpl.class.getSimpleName();

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final PS1EnigmaOasisBleApiImpl getInstance(@NotNull Context ctxt) {
            Intrinsics.checkNotNullParameter(ctxt, "ctxt");
            if (PS1EnigmaOasisBleApiImpl.E == null) {
                LeonardoBleApiImpl.context = ctxt.getApplicationContext();
                Companion companion = PS1EnigmaOasisBleApiImpl.Companion;
                PS1EnigmaOasisBleApiImpl.E = new PS1EnigmaOasisBleApiImpl();
            }
            LeonardoBleApiImpl.intent = new Intent(LeonardoBleApiImpl.context, LeonardoBleCmdService.class);
            if (!LeonardoBleApiImpl.checkIfServiceIsRunning()) {
                LogHelper.d(PS1EnigmaOasisBleApiImpl.D, "service is not running ++ ", ModuleNames.BLEABSTRACT.getModuleName());
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
            return PS1EnigmaOasisBleApiImpl.E;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CommandType.values().length];
            try {
                iArr[CommandType.SET_CUSTOM_WATCHFACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CommandType.GET_BUTTON_FUNCTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CommandType.SET_BUTTON_FUNCTION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CommandType.SET_NAVIGATION_DISCLAIMER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[CommandType.SET_NAVIGATION_STATUS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[CommandType.SET_NAVIGATION_ALIVE_TIMER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[CommandType.SET_NAVIGATION_CONFIGURATION.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[CommandType.GET_NAVIGATION_CONFIGURATION.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[CommandType.SET_NAVIGATION_FAVOURITE_LOCATION.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[CommandType.SET_NAVIGATION_EVENT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[CommandType.GET_NAVIGATION_DISCLAIMER_INFO.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[CommandType.GET_NAVIGATION_FAVOURITE_LOCATION.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[CommandType.GET_NAVIGATION_WATCH_STATE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[CommandType.SET_SMART_ALERT_COMMON_APPLICATION_CONTENT.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[CommandType.SET_NON_SMART_ALERT_COMMON_APPLICATION_CONTENT.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[CommandType.SET_NAVIGATION_COMMON_APPLICATION_CONTENT.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[CommandType.SET_SMART_ALERT_CONFIG.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[CommandType.GET_SMART_ALERT_CONFIG.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ResponseType.values().length];
            try {
                iArr2[ResponseType.NOTIFY_NAVIGATION_EVENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr2[ResponseType.REQUEST_NAVIGATION_EVENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr2[ResponseType.REQUEST_NAVIGATION_STATUS.ordinal()] = 3;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr2[ResponseType.REQUEST_NAVIGATION_FEATURE_CONTENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused22) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        LeonardoBleCmdService leonardoBleCmdService = LeonardoBleApiImpl.bleService;
        if (leonardoBleCmdService != null && leonardoBleCmdService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
            if (request instanceof CustomWatchFaceFileImageRequest) {
                CustomWatchFaceFileImageRequest customWatchFaceFileImageRequest = (CustomWatchFaceFileImageRequest) request;
                if (customWatchFaceFileImageRequest.watchFaceFilePath != null) {
                    if (customWatchFaceFileImageRequest.isShouldUseSdk()) {
                        CustomWatchFaceUploadReq customWatchFaceUploadReq = new CustomWatchFaceUploadReq((Object) null, customWatchFaceFileImageRequest.watchFaceFilePath);
                        request.setResponseListener(listener);
                        request.setBleCommand(BleCommand.SET_CUSTOM_WATCHFACE);
                        request.setRequId(UUID.randomUUID().toString());
                        addToQueue(request);
                        customWatchFaceUploadReq.setReqId(request.getRequId());
                        LeonardoBleApiImpl.bleService.sendRequest(customWatchFaceUploadReq, this);
                        return;
                    }
                    super.getData(request, listener);
                    return;
                }
                return;
            } else if (request instanceof SendImageRequest) {
                SendImageRequest sendImageRequest = (SendImageRequest) request;
                SendImageReq sendImageReq = new SendImageReq(null, sendImageRequest.getImageId(), sendImageRequest.getImageFile(), sendImageRequest.getCompression(), sendImageRequest.getTransparentChannel(), sendImageRequest.getxCoordinate(), sendImageRequest.getyCoordinate(), sendImageRequest.getHeight(), sendImageRequest.getWidth(), DevicePlatformEnum.Sifli);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.SEND_IMAGE);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                sendImageReq.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(sendImageReq, this);
                return;
            } else if (request instanceof SportsNotificationRequest) {
                SportsNotificationReq sportsNotificationReq = new SportsNotificationReq(null, LeonardoFormatter.getBleDynamicFieldData(((SportsNotificationRequest) request).getDynamicSportsFieldList()));
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.SPORTS_NOTIFICATION);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                sportsNotificationReq.setDevicePlatformEnum(DevicePlatformEnum.Sifli);
                sportsNotificationReq.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(sportsNotificationReq, this);
                return;
            } else if (request instanceof CustomMessageRequest) {
                CustomMessageRequest customMessageRequest = (CustomMessageRequest) request;
                CustomMessageReq customMessageReq = new CustomMessageReq(null, LeonardoFormatter.getBleDynamicFieldData(customMessageRequest.getDynamicSportsFieldList()), customMessageRequest.getMessageType(), (short) (customMessageRequest.getVibrationDuration() / 100), (short) (customMessageRequest.getDisplayTime() / 100), customMessageRequest.getEnterDirection(), customMessageRequest.getExitDirection());
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.CUSTOM_MESSAGE);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                customMessageReq.setReqId(request.getRequId());
                customMessageReq.setDevicePlatformEnum(DevicePlatformEnum.Sifli);
                LeonardoBleApiImpl.bleService.sendRequest(customMessageReq, this);
                return;
            } else if (request instanceof GetButtonFunctionRequest) {
                GetButtonFunctionReq getButtonFunctionReq = new GetButtonFunctionReq(null, (short) 0, ((GetButtonFunctionRequest) request).getPosition());
                getButtonFunctionReq.setResponseListener(this);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.GET_BUTTON_FUNCTION);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                getButtonFunctionReq.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(getButtonFunctionReq, this);
                return;
            } else if (request instanceof GetNavigationConfigurationRequest) {
                GetNavigationConfigurationReq build = new GetNavigationConfigurationReq.Builder().build();
                build.setResponseListener(this);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.GET_NAVIGATION_CONFIGURATION);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                build.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(build, this);
                return;
            } else if (request instanceof GetNavigationDisclaimerRequest) {
                GetNavigationDisclaimerReq build2 = new GetNavigationDisclaimerReq.Builder().build();
                build2.setResponseListener(this);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.GET_NAVIGATION_DISCLAIMER_INFO);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                build2.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(build2, this);
                return;
            } else if (request instanceof GetNavigationFavouriteLocationRequest) {
                GetNavigationFavouriteLocationReq build3 = new GetNavigationFavouriteLocationReq.Builder().build();
                build3.setResponseListener(this);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.GET_NAVIGATION_FAVOURITE_LOCATION);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                build3.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(build3, this);
                return;
            } else if (request instanceof GetNavigationWatchStateRequest) {
                GetNavigationWatchStateReq build4 = new GetNavigationWatchStateReq.Builder().build();
                build4.setResponseListener(this);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.GET_NAVIGATION_WATCH_STATE);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                build4.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(build4, this);
                return;
            } else if (request instanceof GetSmartAlertConfigRequest) {
                GetSmartAlertConfigReq getSmartAlertConfigReq = new GetSmartAlertConfigReq(null);
                getSmartAlertConfigReq.setResponseListener(this);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.GET_SMART_ALERT_CONFIG);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                getSmartAlertConfigReq.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(getSmartAlertConfigReq, this);
                return;
            } else {
                super.getData(request, listener);
                return;
            }
        }
        listener.onDataError(new BleBaseError("Watch not connected", request.getBleCommand()));
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
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
        deviceSupportedFeatures.setMaxCharSupportedInNotification(200);
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
        deviceSupportedFeatures.setKaHaRealtekChip(false);
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
        deviceSupportedFeatures.setCustomRemindersSupported(true);
        deviceSupportedFeatures.setBandVolumeControlSupported(true);
        deviceSupportedFeatures.setScheduledLiftWristViewSettingsSupported(true);
        deviceSupportedFeatures.setSensAISupported(false);
        deviceSupportedFeatures.setBatterySaverConfigSupported(true);
        deviceSupportedFeatures.setSilentModeConfigSupported(true);
        deviceSupportedFeatures.setFitnessValueCommandSupported(true);
        deviceSupportedFeatures.setDiagnosticTestSupported(true);
        deviceSupportedFeatures.setSmartAlertsSupported(true);
        deviceSupportedFeatures.setKaHaSifliChip(true);
        deviceSupportedFeatures.setTurnByTurnNavigationSupported(true);
        deviceSupportedFeatures.set4hButtonFunctionSupported(true);
        deviceSupportedFeatures.setHButtonsSupported(CollectionsKt__CollectionsKt.mutableListOf(HButtonType.H2, HButtonType.H4));
        Context context = LeonardoBleApiImpl.context;
        Intrinsics.checkNotNullExpressionValue(context, "context");
        String macAddress = getMacAddress();
        Intrinsics.checkNotNullExpressionValue(macAddress, "getMacAddress()");
        return BleApiUtils.updateDeviceSupportedFeatureBasedOnFWAndAppCapability(context, macAddress, deviceSupportedFeatures);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onConnectionStateChanged(@NotNull CloveBleState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        super.onConnectionStateChanged(state);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onError(@NotNull Error error) {
        Intrinsics.checkNotNullParameter(error, "error");
        super.onError(error);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
        Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
        if (progressDataBean.getBaseRequest() != null) {
            if (progressDataBean.getBaseRequest() instanceof CustomWatchFaceUploadReq) {
                LeonardoBleApiImpl.QueueObject fromQueueWith = getFromQueueWith(progressDataBean.getBaseRequest());
                if ((fromQueueWith != null ? fromQueueWith.f3181a : null) == null || fromQueueWith.f3181a.getResponseListener() == null || !(fromQueueWith.f3181a.getResponseListener() instanceof DataResultListener)) {
                    return;
                }
                BaseListener responseListener = fromQueueWith.f3181a.getResponseListener();
                Intrinsics.checkNotNull(responseListener, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                ProgressType progressType = ProgressType.DETERMINATE;
                int value = progressDataBean.getValue();
                BleBaseRequest bleBaseRequest = fromQueueWith.f3181a;
                Intrinsics.checkNotNullExpressionValue(bleBaseRequest, "requestObject.baseRequest");
                ((DataResultListener) responseListener).onProgressUpdate(new ProgressData(progressType, value, bleBaseRequest));
                return;
            }
            super.onProgressUpdate(progressDataBean);
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onResponse(@NotNull BaseResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        CommandType activityType = response.getActivityType();
        switch (activityType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[activityType.ordinal()]) {
            case 1:
                LeonardoBleApiImpl.QueueObject fromQueue = getFromQueue(BleCommand.SET_CUSTOM_WATCHFACE, response);
                if (fromQueue != null) {
                    BleBaseRequest bleBaseRequest = fromQueue.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest, "watchFaceReq.baseRequest");
                    onDataResponse(new BleBaseResponse(bleBaseRequest), (DataResultListener) fromQueue.f3181a.getResponseListener());
                    return;
                }
                return;
            case 2:
                BleCommand bleCommand = BleCommand.GET_BUTTON_FUNCTION;
                LeonardoBleApiImpl.QueueObject fromQueue2 = getFromQueue(bleCommand, response);
                if (fromQueue2 != null) {
                    BaseListener responseListener = fromQueue2.f3181a.getResponseListener();
                    Intrinsics.checkNotNull(responseListener, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                    DataResultListener dataResultListener = (DataResultListener) responseListener;
                    if (response instanceof GetButtonFunctionRes) {
                        BleGetButtonFunctionData bleButtonFunctionData = ((GetButtonFunctionRes) response).getBleButtonFunctionData();
                        if (bleButtonFunctionData != null) {
                            GetButtonFunctionResponse getButtonFunctionResponse = new GetButtonFunctionResponse();
                            getButtonFunctionResponse.setVersionNumber(bleButtonFunctionData.getVersionNumber());
                            getButtonFunctionResponse.setPosition(bleButtonFunctionData.getPosition());
                            getButtonFunctionResponse.setLongPressFunction(bleButtonFunctionData.getLongPress());
                            getButtonFunctionResponse.setShortPressFunction(bleButtonFunctionData.getShortPress());
                            BleBaseRequest bleBaseRequest2 = fromQueue2.f3181a;
                            Intrinsics.checkNotNullExpressionValue(bleBaseRequest2, "getButtonFunctionRequest.baseRequest");
                            BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest2);
                            bleBaseResponse.setResponseData(getButtonFunctionResponse);
                            bleBaseResponse.setCompleted(true);
                            onDataResponse(bleBaseResponse, dataResultListener);
                            return;
                        }
                        return;
                    }
                    onDataError(dataResultListener, new BleBaseError("GetButtonFunctionRes failed", bleCommand));
                    return;
                }
                return;
            case 3:
                String str = D;
                LogHelper.d(str, "On Response " + response);
                BleCommand bleCommand2 = BleCommand.SET_BUTTON_FUNCTION;
                LeonardoBleApiImpl.QueueObject fromQueue3 = getFromQueue(bleCommand2, response);
                if (fromQueue3 != null) {
                    SettingsResultListener settingsResultListener = (SettingsResultListener) fromQueue3.f3181a.getResponseListener();
                    BleBaseRequest bleBaseRequest3 = fromQueue3.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest3, "setButtonFunctionsReq.baseRequest");
                    BleBaseResponse bleBaseResponse2 = new BleBaseResponse(bleBaseRequest3);
                    String str2 = this.C;
                    StringBuilder sb = new StringBuilder();
                    sb.append("((SET_BUTTON_FUNCTION) response).isSuccess(): ");
                    SetButtonFunctionRes setButtonFunctionRes = (SetButtonFunctionRes) response;
                    sb.append(setButtonFunctionRes.isSuccess());
                    LogHelper.d(str2, sb.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                    if (setButtonFunctionRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener, bleBaseResponse2);
                        return;
                    } else {
                        onSettingsError(settingsResultListener, new BleBaseError("SET_BUTTON_FUNCTION setting failed", bleCommand2));
                        return;
                    }
                }
                return;
            case 4:
                String str3 = D;
                LogHelper.d(str3, "On Response " + response);
                BleCommand bleCommand3 = BleCommand.SET_NAVIGATION_DISCLAIMER;
                LeonardoBleApiImpl.QueueObject fromQueue4 = getFromQueue(bleCommand3, response);
                if (fromQueue4 != null) {
                    SettingsResultListener settingsResultListener2 = (SettingsResultListener) fromQueue4.f3181a.getResponseListener();
                    BleBaseRequest bleBaseRequest4 = fromQueue4.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest4, "setNavigationDisclaimerReq.baseRequest");
                    BleBaseResponse bleBaseResponse3 = new BleBaseResponse(bleBaseRequest4);
                    String str4 = this.C;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("((SET_NAVIGATION_DISCLAIMER) response).isSuccess(): ");
                    SetNavigationDisclaimerRes setNavigationDisclaimerRes = (SetNavigationDisclaimerRes) response;
                    sb2.append(setNavigationDisclaimerRes.isSuccess());
                    LogHelper.d(str4, sb2.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                    if (setNavigationDisclaimerRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener2, bleBaseResponse3);
                        return;
                    } else {
                        onSettingsError(settingsResultListener2, new BleBaseError("SET_NAVIGATION_DISCLAIMER setting failed", bleCommand3));
                        return;
                    }
                }
                return;
            case 5:
                String str5 = D;
                LogHelper.d(str5, "On Response " + response);
                LeonardoBleApiImpl.QueueObject fromQueue5 = getFromQueue(BleCommand.SET_NAVIGATION_STATUS, response);
                if (fromQueue5 != null) {
                    SettingsResultListener settingsResultListener3 = (SettingsResultListener) fromQueue5.f3181a.getResponseListener();
                    BleBaseRequest bleBaseRequest5 = fromQueue5.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest5, "setNavigationStatusRes.baseRequest");
                    BleBaseResponse bleBaseResponse4 = new BleBaseResponse(bleBaseRequest5);
                    String str6 = this.C;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("((SET_NAVIGATION_STATUS) response).isSuccess(): ");
                    SetNavigationStatusRes setNavigationStatusRes = (SetNavigationStatusRes) response;
                    sb3.append(setNavigationStatusRes.isSuccess());
                    LogHelper.d(str6, sb3.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                    if (setNavigationStatusRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener3, bleBaseResponse4);
                        return;
                    } else {
                        onSettingsError(settingsResultListener3, new BleBaseError("SET_NAVIGATION_STATUS setting failed", BleCommand.SET_BUTTON_FUNCTION));
                        return;
                    }
                }
                return;
            case 6:
                String str7 = D;
                LogHelper.d(str7, "On Response " + response);
                BleCommand bleCommand4 = BleCommand.SET_NAVIGATION_ALIVE_TIMER;
                LeonardoBleApiImpl.QueueObject fromQueue6 = getFromQueue(bleCommand4, response);
                if (fromQueue6 != null) {
                    SettingsResultListener settingsResultListener4 = (SettingsResultListener) fromQueue6.f3181a.getResponseListener();
                    BleBaseRequest bleBaseRequest6 = fromQueue6.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest6, "setNavigationAliveTimerRes.baseRequest");
                    BleBaseResponse bleBaseResponse5 = new BleBaseResponse(bleBaseRequest6);
                    String str8 = this.C;
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("((SET_NAVIGATION_ALIVE_TIMER) response).isSuccess(): ");
                    SetNavigationAliveTimerRes setNavigationAliveTimerRes = (SetNavigationAliveTimerRes) response;
                    sb4.append(setNavigationAliveTimerRes.isSuccess());
                    LogHelper.d(str8, sb4.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                    if (setNavigationAliveTimerRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener4, bleBaseResponse5);
                        return;
                    } else {
                        onSettingsError(settingsResultListener4, new BleBaseError("SET_NAVIGATION_ALIVE_TIMER setting failed", bleCommand4));
                        return;
                    }
                }
                return;
            case 7:
                String str9 = D;
                LogHelper.d(str9, "On Response " + response);
                BleCommand bleCommand5 = BleCommand.SET_NAVIGATION_CONFIGURATION;
                LeonardoBleApiImpl.QueueObject fromQueue7 = getFromQueue(bleCommand5, response);
                if (fromQueue7 != null) {
                    SettingsResultListener settingsResultListener5 = (SettingsResultListener) fromQueue7.f3181a.getResponseListener();
                    BleBaseRequest bleBaseRequest7 = fromQueue7.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest7, "setNavigationConfigurationRes.baseRequest");
                    BleBaseResponse bleBaseResponse6 = new BleBaseResponse(bleBaseRequest7);
                    String str10 = this.C;
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("((SET_NAVIGATION_CONFIGURATION) response).isSuccess(): ");
                    SetNavigationConfigurationRes setNavigationConfigurationRes = (SetNavigationConfigurationRes) response;
                    sb5.append(setNavigationConfigurationRes.isSuccess());
                    LogHelper.d(str10, sb5.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                    if (setNavigationConfigurationRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener5, bleBaseResponse6);
                        return;
                    } else {
                        onSettingsError(settingsResultListener5, new BleBaseError("SET_NAVIGATION_CONFIGURATION setting failed", bleCommand5));
                        return;
                    }
                }
                return;
            case 8:
                BleCommand bleCommand6 = BleCommand.GET_NAVIGATION_CONFIGURATION;
                LeonardoBleApiImpl.QueueObject fromQueue8 = getFromQueue(bleCommand6, response);
                if (fromQueue8 != null) {
                    BaseListener responseListener2 = fromQueue8.f3181a.getResponseListener();
                    Intrinsics.checkNotNull(responseListener2, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                    DataResultListener dataResultListener2 = (DataResultListener) responseListener2;
                    if (response instanceof GetNavigationConfigurationRes) {
                        BleNavigationConfigurationData bleNavigationConfigurationData = ((GetNavigationConfigurationRes) response).getBleNavigationConfigurationData();
                        if (bleNavigationConfigurationData != null) {
                            GetNavigationConfigurationResponse getNavigationConfigurationResponse = new GetNavigationConfigurationResponse();
                            getNavigationConfigurationResponse.setAudioEnabled(bleNavigationConfigurationData.isAudioEnabled());
                            getNavigationConfigurationResponse.setHapticEnabled(bleNavigationConfigurationData.isHapticEnabled());
                            getNavigationConfigurationResponse.setAODEnabled(bleNavigationConfigurationData.isAODEnabled());
                            BleBaseRequest bleBaseRequest8 = fromQueue8.f3181a;
                            Intrinsics.checkNotNullExpressionValue(bleBaseRequest8, "getNavigationConfigurationRequest.baseRequest");
                            BleBaseResponse bleBaseResponse7 = new BleBaseResponse(bleBaseRequest8);
                            bleBaseResponse7.setResponseData(getNavigationConfigurationResponse);
                            bleBaseResponse7.setCompleted(true);
                            onDataResponse(bleBaseResponse7, dataResultListener2);
                            return;
                        }
                        return;
                    }
                    onDataError(dataResultListener2, new BleBaseError("GetNavigationConfigurationRes failed", bleCommand6));
                    return;
                }
                return;
            case 9:
                String str11 = D;
                LogHelper.d(str11, "On Response " + response);
                BleCommand bleCommand7 = BleCommand.SET_NAVIGATION_FAVOURITE_LOCATION;
                LeonardoBleApiImpl.QueueObject fromQueue9 = getFromQueue(bleCommand7, response);
                if (fromQueue9 != null) {
                    SettingsResultListener settingsResultListener6 = (SettingsResultListener) fromQueue9.f3181a.getResponseListener();
                    BleBaseRequest bleBaseRequest9 = fromQueue9.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest9, "setNavigationFavouriteLocationReq.baseRequest");
                    BleBaseResponse bleBaseResponse8 = new BleBaseResponse(bleBaseRequest9);
                    String str12 = this.C;
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("((SET_NAVIGATION_FAVOURITE_LOCATION) response).isSuccess(): ");
                    SetNavigationFavouriteLocationRes setNavigationFavouriteLocationRes = (SetNavigationFavouriteLocationRes) response;
                    sb6.append(setNavigationFavouriteLocationRes.isSuccess());
                    LogHelper.d(str12, sb6.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                    if (setNavigationFavouriteLocationRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener6, bleBaseResponse8);
                        return;
                    } else {
                        onSettingsError(settingsResultListener6, new BleBaseError("SET_NAVIGATION_FAVOURITE_LOCATION setting failed", bleCommand7));
                        return;
                    }
                }
                return;
            case 10:
                String str13 = D;
                LogHelper.d(str13, "On Response " + response);
                BleCommand bleCommand8 = BleCommand.SET_NAVIGATION_EVENT;
                LeonardoBleApiImpl.QueueObject fromQueue10 = getFromQueue(bleCommand8, response);
                if (fromQueue10 != null) {
                    SettingsResultListener settingsResultListener7 = (SettingsResultListener) fromQueue10.f3181a.getResponseListener();
                    BleBaseRequest bleBaseRequest10 = fromQueue10.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest10, "setNavigationEventReq.baseRequest");
                    BleBaseResponse bleBaseResponse9 = new BleBaseResponse(bleBaseRequest10);
                    String str14 = this.C;
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("((SET_NAVIGATION_EVENT) response).isSuccess(): ");
                    SetNavigationEventRes setNavigationEventRes = (SetNavigationEventRes) response;
                    sb7.append(setNavigationEventRes.isSuccess());
                    LogHelper.d(str14, sb7.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                    if (setNavigationEventRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener7, bleBaseResponse9);
                        return;
                    } else {
                        onSettingsError(settingsResultListener7, new BleBaseError("SET_NAVIGATION_EVENT setting failed", bleCommand8));
                        return;
                    }
                }
                return;
            case 11:
                LeonardoBleApiImpl.QueueObject fromQueue11 = getFromQueue(BleCommand.GET_NAVIGATION_DISCLAIMER_INFO, response);
                if (fromQueue11 != null) {
                    BaseListener responseListener3 = fromQueue11.f3181a.getResponseListener();
                    Intrinsics.checkNotNull(responseListener3, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                    DataResultListener dataResultListener3 = (DataResultListener) responseListener3;
                    if (response instanceof GetNavigationDisclaimerRes) {
                        BleNavigationDisclaimerData bleNavigationDisclaimerData = ((GetNavigationDisclaimerRes) response).getBleNavigationDisclaimerData();
                        if (bleNavigationDisclaimerData != null) {
                            GetNavigationDisclaimerResponse getNavigationDisclaimerResponse = new GetNavigationDisclaimerResponse();
                            getNavigationDisclaimerResponse.setAccepted(bleNavigationDisclaimerData.isUserAccepted());
                            String versionText = bleNavigationDisclaimerData.getVersionText();
                            Intrinsics.checkNotNullExpressionValue(versionText, "getNavigationDisclaimerInfo.versionText");
                            getNavigationDisclaimerResponse.setVersionText(versionText);
                            if (bleNavigationDisclaimerData.isUserAccepted()) {
                                Calendar calendar = bleNavigationDisclaimerData.getCalendar();
                                Intrinsics.checkNotNullExpressionValue(calendar, "getNavigationDisclaimerInfo.calendar");
                                getNavigationDisclaimerResponse.setCalendar(calendar);
                            }
                            BleBaseRequest bleBaseRequest11 = fromQueue11.f3181a;
                            Intrinsics.checkNotNullExpressionValue(bleBaseRequest11, "getNavigationDisclaimerInfoRequest.baseRequest");
                            BleBaseResponse bleBaseResponse10 = new BleBaseResponse(bleBaseRequest11);
                            bleBaseResponse10.setResponseData(getNavigationDisclaimerResponse);
                            bleBaseResponse10.setCompleted(true);
                            onDataResponse(bleBaseResponse10, dataResultListener3);
                            return;
                        }
                        return;
                    }
                    onDataError(dataResultListener3, new BleBaseError("GetNavigationConfigurationRes failed", BleCommand.GET_NAVIGATION_CONFIGURATION));
                    return;
                }
                return;
            case 12:
                String str15 = D;
                LogHelper.d(str15, "On Response " + response);
                BleCommand bleCommand9 = BleCommand.GET_NAVIGATION_FAVOURITE_LOCATION;
                LeonardoBleApiImpl.QueueObject fromQueue12 = getFromQueue(bleCommand9, response);
                if (fromQueue12 != null) {
                    BaseListener responseListener4 = fromQueue12.f3181a.getResponseListener();
                    Intrinsics.checkNotNull(responseListener4, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                    DataResultListener dataResultListener4 = (DataResultListener) responseListener4;
                    if (response instanceof GetNavigationFavouriteLocationRes) {
                        ArrayList<FavouriteLocationData> favouriteLocations = ((GetNavigationFavouriteLocationRes) response).getFavouriteLocations();
                        if (favouriteLocations != null) {
                            GetNavigationFavouriteLocationResponse getNavigationFavouriteLocationResponse = new GetNavigationFavouriteLocationResponse();
                            getNavigationFavouriteLocationResponse.setFavouriteLocationList(favouriteLocations);
                            getNavigationFavouriteLocationResponse.setLocationNum(favouriteLocations.size());
                            BleBaseRequest bleBaseRequest12 = fromQueue12.f3181a;
                            Intrinsics.checkNotNullExpressionValue(bleBaseRequest12, "getNavigationFavouriteLocationReq.baseRequest");
                            BleBaseResponse bleBaseResponse11 = new BleBaseResponse(bleBaseRequest12);
                            bleBaseResponse11.setResponseData(getNavigationFavouriteLocationResponse);
                            bleBaseResponse11.setCompleted(true);
                            onDataResponse(bleBaseResponse11, dataResultListener4);
                            return;
                        }
                        return;
                    }
                    onDataError(dataResultListener4, new BleBaseError("getNavigationFavouriteLocationRes failed", bleCommand9));
                    return;
                }
                return;
            case 13:
                BleCommand bleCommand10 = BleCommand.GET_NAVIGATION_WATCH_STATE;
                LeonardoBleApiImpl.QueueObject fromQueue13 = getFromQueue(bleCommand10, response);
                if (fromQueue13 != null) {
                    BaseListener responseListener5 = fromQueue13.f3181a.getResponseListener();
                    Intrinsics.checkNotNull(responseListener5, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                    DataResultListener dataResultListener5 = (DataResultListener) responseListener5;
                    if (response instanceof GetNavigationWatchStateRes) {
                        NavigationWatchStateData navigationWatchStateData = ((GetNavigationWatchStateRes) response).getNavigationWatchStateData();
                        if (navigationWatchStateData != null) {
                            GetNavigationWatchStateResponse getNavigationWatchStateResponse = new GetNavigationWatchStateResponse();
                            if (!navigationWatchStateData.getStarted().booleanValue()) {
                                getNavigationWatchStateResponse.getNavigationWatchStateData().setStarted(navigationWatchStateData.getStarted());
                            } else {
                                getNavigationWatchStateResponse.getNavigationWatchStateData().setStarted(navigationWatchStateData.getStarted());
                                getNavigationWatchStateResponse.getNavigationWatchStateData().setSource(navigationWatchStateData.getSource());
                                getNavigationWatchStateResponse.getNavigationWatchStateData().setDestination(navigationWatchStateData.getDestination());
                                getNavigationWatchStateResponse.getNavigationWatchStateData().setEtaDay(navigationWatchStateData.getEtaDay());
                                getNavigationWatchStateResponse.getNavigationWatchStateData().setEtaHour(navigationWatchStateData.getEtaHour());
                                getNavigationWatchStateResponse.getNavigationWatchStateData().setEtaMin(navigationWatchStateData.getEtaMin());
                                getNavigationWatchStateResponse.getNavigationWatchStateData().setDistance(navigationWatchStateData.getDistance());
                                getNavigationWatchStateResponse.getNavigationWatchStateData().setMode(navigationWatchStateData.getMode());
                            }
                            BleBaseRequest bleBaseRequest13 = fromQueue13.f3181a;
                            Intrinsics.checkNotNullExpressionValue(bleBaseRequest13, "getNavigationWatchStateRequest.baseRequest");
                            BleBaseResponse bleBaseResponse12 = new BleBaseResponse(bleBaseRequest13);
                            bleBaseResponse12.setResponseData(getNavigationWatchStateResponse);
                            bleBaseResponse12.setCompleted(true);
                            onDataResponse(bleBaseResponse12, dataResultListener5);
                            return;
                        }
                        return;
                    }
                    onDataError(dataResultListener5, new BleBaseError("GetNavigationWatchStateRes failed", bleCommand10));
                    return;
                }
                return;
            case 14:
                String str16 = D;
                LogHelper.d(str16, "On Response " + response);
                BleCommand bleCommand11 = BleCommand.SET_SMART_ALERT_COMMON_APPLICATION_CONTENT;
                LeonardoBleApiImpl.QueueObject fromQueue14 = getFromQueue(bleCommand11, response);
                if (fromQueue14 != null) {
                    SettingsResultListener settingsResultListener8 = (SettingsResultListener) fromQueue14.f3181a.getResponseListener();
                    BleBaseRequest bleBaseRequest14 = fromQueue14.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest14, "setSmartAlertApplicationContentReq.baseRequest");
                    BleBaseResponse bleBaseResponse13 = new BleBaseResponse(bleBaseRequest14);
                    String str17 = this.C;
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append("((SET_SMART_ALERT_COMMON_APPLICATION_CONTENT) response).isSuccess(): ");
                    SetSmartAlertApplicationContentRes setSmartAlertApplicationContentRes = (SetSmartAlertApplicationContentRes) response;
                    sb8.append(setSmartAlertApplicationContentRes.isSuccess());
                    LogHelper.d(str17, sb8.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                    if (setSmartAlertApplicationContentRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener8, bleBaseResponse13);
                        return;
                    } else {
                        onSettingsError(settingsResultListener8, new BleBaseError("SET_SMART_ALERT_COMMON_APPLICATION_CONTENT setting failed", bleCommand11));
                        return;
                    }
                }
                return;
            case 15:
                String str18 = D;
                LogHelper.d(str18, "On Response " + response);
                BleCommand bleCommand12 = BleCommand.SET_NON_SMART_ALERT_COMMON_APPLICATION_CONTENT;
                LeonardoBleApiImpl.QueueObject fromQueue15 = getFromQueue(bleCommand12, response);
                if (fromQueue15 != null) {
                    SettingsResultListener settingsResultListener9 = (SettingsResultListener) fromQueue15.f3181a.getResponseListener();
                    BleBaseRequest bleBaseRequest15 = fromQueue15.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest15, "setNonSmartAlertApplicationContentReq.baseRequest");
                    BleBaseResponse bleBaseResponse14 = new BleBaseResponse(bleBaseRequest15);
                    String str19 = this.C;
                    StringBuilder sb9 = new StringBuilder();
                    sb9.append("((SET_NON_SMART_ALERT_COMMON_APPLICATION_CONTENT) response).isSuccess(): ");
                    SetNonSmartAlertApplicationContentRes setNonSmartAlertApplicationContentRes = (SetNonSmartAlertApplicationContentRes) response;
                    sb9.append(setNonSmartAlertApplicationContentRes.isSuccess());
                    LogHelper.d(str19, sb9.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                    if (setNonSmartAlertApplicationContentRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener9, bleBaseResponse14);
                        return;
                    } else {
                        onSettingsError(settingsResultListener9, new BleBaseError("SET_NON_SMART_ALERT_COMMON_APPLICATION_CONTENT setting failed", bleCommand12));
                        return;
                    }
                }
                return;
            case 16:
                String str20 = D;
                LogHelper.d(str20, "On Response " + response);
                BleCommand bleCommand13 = BleCommand.SET_NAVIGATION_COMMON_APPLICATION_CONTENT;
                LeonardoBleApiImpl.QueueObject fromQueue16 = getFromQueue(bleCommand13, response);
                if (fromQueue16 != null) {
                    SettingsResultListener settingsResultListener10 = (SettingsResultListener) fromQueue16.f3181a.getResponseListener();
                    BleBaseRequest bleBaseRequest16 = fromQueue16.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest16, "setNavigationApplicationContentReq.baseRequest");
                    BleBaseResponse bleBaseResponse15 = new BleBaseResponse(bleBaseRequest16);
                    String str21 = this.C;
                    StringBuilder sb10 = new StringBuilder();
                    sb10.append("((SET_NAVIGATION_COMMON_APPLICATION_CONTENT) response).isSuccess(): ");
                    SetNavigationApplicationContentRes setNavigationApplicationContentRes = (SetNavigationApplicationContentRes) response;
                    sb10.append(setNavigationApplicationContentRes.isSuccess());
                    LogHelper.d(str21, sb10.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                    if (setNavigationApplicationContentRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener10, bleBaseResponse15);
                        return;
                    } else {
                        onSettingsError(settingsResultListener10, new BleBaseError("SET_NAVIGATION_COMMON_APPLICATION_CONTENT setting failed", bleCommand13));
                        return;
                    }
                }
                return;
            case 17:
                String str22 = D;
                LogHelper.d(str22, "On Response " + response);
                BleCommand bleCommand14 = BleCommand.SET_SMART_ALERT_CONFIG;
                LeonardoBleApiImpl.QueueObject fromQueue17 = getFromQueue(bleCommand14, response);
                if (fromQueue17 != null) {
                    SettingsResultListener settingsResultListener11 = (SettingsResultListener) fromQueue17.f3181a.getResponseListener();
                    BleBaseRequest bleBaseRequest17 = fromQueue17.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest17, "setSmartAlertConfigReq.baseRequest");
                    BleBaseResponse bleBaseResponse16 = new BleBaseResponse(bleBaseRequest17);
                    String str23 = this.C;
                    StringBuilder sb11 = new StringBuilder();
                    sb11.append("((SET_SMART_ALERT_CONFIG) response).isSuccess(): ");
                    SetSmartAlertConfigRes setSmartAlertConfigRes = (SetSmartAlertConfigRes) response;
                    sb11.append(setSmartAlertConfigRes.isSuccess());
                    LogHelper.d(str23, sb11.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                    if (setSmartAlertConfigRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener11, bleBaseResponse16);
                        return;
                    } else {
                        onSettingsError(settingsResultListener11, new BleBaseError("SET_SMART_ALERT_CONFIG setting failed", bleCommand14));
                        return;
                    }
                }
                return;
            case 18:
                BleCommand bleCommand15 = BleCommand.GET_SMART_ALERT_CONFIG;
                LeonardoBleApiImpl.QueueObject fromQueue18 = getFromQueue(bleCommand15, response);
                if (fromQueue18 != null) {
                    BaseListener responseListener6 = fromQueue18.f3181a.getResponseListener();
                    Intrinsics.checkNotNull(responseListener6, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                    DataResultListener dataResultListener6 = (DataResultListener) responseListener6;
                    if (response instanceof GetSmartAlertConfigRes) {
                        BleGetSmartAlertConfigData smartAlertConfigData = ((GetSmartAlertConfigRes) response).getSmartAlertConfigData();
                        if (smartAlertConfigData != null) {
                            GetSmartAlertConfigResponse getSmartAlertConfigResponse = new GetSmartAlertConfigResponse();
                            getSmartAlertConfigResponse.setEnabled(smartAlertConfigData.isEnabled());
                            getSmartAlertConfigResponse.setSmartAlertAppDataList(new ArrayList());
                            List<BleSmartAlertAppData> bleSmartAlertAppDataList = smartAlertConfigData.getBleSmartAlertAppDataList();
                            if (!(bleSmartAlertAppDataList == null || bleSmartAlertAppDataList.isEmpty())) {
                                for (BleSmartAlertAppData bleSmartAlertAppData : smartAlertConfigData.getBleSmartAlertAppDataList()) {
                                    List<SmartAlertAppData> smartAlertAppDataList = getSmartAlertConfigResponse.getSmartAlertAppDataList();
                                    if (smartAlertAppDataList != null) {
                                        int id = bleSmartAlertAppData.getId();
                                        String name = bleSmartAlertAppData.getName();
                                        Intrinsics.checkNotNullExpressionValue(name, "bleSmartAlertAppData.name");
                                        smartAlertAppDataList.add(new SmartAlertAppData(id, name, bleSmartAlertAppData.getLogoSize(), bleSmartAlertAppData.getLogoInfo()));
                                    }
                                }
                            }
                            BleBaseRequest bleBaseRequest18 = fromQueue18.f3181a;
                            Intrinsics.checkNotNullExpressionValue(bleBaseRequest18, "getSmartAlertConfigRequest.baseRequest");
                            BleBaseResponse bleBaseResponse17 = new BleBaseResponse(bleBaseRequest18);
                            bleBaseResponse17.setResponseData(getSmartAlertConfigResponse);
                            bleBaseResponse17.setCompleted(true);
                            onDataResponse(bleBaseResponse17, dataResultListener6);
                            return;
                        }
                        return;
                    }
                    onDataError(dataResultListener6, new BleBaseError("GetSmartAlertConfigRes failed", bleCommand15));
                    return;
                }
                return;
            default:
                super.onResponse(response);
                return;
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onResponseEventReceivved(@NotNull ResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        ResponseType type = responseEvent.getType();
        int i = type == null ? -1 : WhenMappings.$EnumSwitchMapping$1[type.ordinal()];
        if (i == 1) {
            Object data = responseEvent.getData();
            Intrinsics.checkNotNull(data, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.response.NotifyNavigationEventRes");
            Intent intent = new Intent(Constants.NOTIFY_NAVIGATION_EVENT_BROADCAST_INTENT);
            intent.putExtra(Constants.NOTIFY_NAVIGATION_EVENT_BROADCAST_INTENT_EXTRA, (NotifyNavigationEventRes) data);
            LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(intent);
        } else if (i == 2) {
            LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(new Intent(Constants.REQUEST_NAVIGATION_EVENT_BROADCAST_INTENT));
        } else if (i == 3) {
            LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(new Intent(Constants.REQUEST_NAVIGATION_STATUS_BROADCAST_INTENT));
        } else if (i != 4) {
            super.onResponseEventReceivved(responseEvent);
        } else {
            LocalBroadcastManager.getInstance(LeonardoBleApiImpl.context).sendBroadcast(new Intent(Constants.REQUEST_NAVIGATION_FEATURE_CONTENT_BROADCAST_INTENT));
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<BloodPressureMeasurement> registerLiveBpData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        SetNavigationEventReq setNavigationEventReq;
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        LeonardoBleCmdService leonardoBleCmdService = LeonardoBleApiImpl.bleService;
        if (leonardoBleCmdService != null && leonardoBleCmdService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
            if (request instanceof SetCallSmsSocialNotificationRequest) {
                request.setResponseListener(listener);
                request.setRequId(UUID.randomUUID().toString());
                request.setBleCommand(BleCommand.SET_MESSAGE_ALERT_SWITCH);
                addToQueue(request);
                SetCallSmsSocialNotificationRequest setCallSmsSocialNotificationRequest = (SetCallSmsSocialNotificationRequest) request;
                MessageAlertSwitchesReq build = new MessageAlertSwitchesReq.Builder().setAppAlerts(setCallSmsSocialNotificationRequest.isCallEnabled(), setCallSmsSocialNotificationRequest.isCalendarEnabled(), setCallSmsSocialNotificationRequest.isSmsEnabled(), setCallSmsSocialNotificationRequest.isEmailEnabled(), setCallSmsSocialNotificationRequest.isWhatsAppEnabled(), setCallSmsSocialNotificationRequest.isWeChatEnabled(), setCallSmsSocialNotificationRequest.isFacebookEnabled(), setCallSmsSocialNotificationRequest.isInstagramEnabled(), setCallSmsSocialNotificationRequest.isTwitterEnabled(), setCallSmsSocialNotificationRequest.isMessengerEnabled(), setCallSmsSocialNotificationRequest.isQQEnabled(), setCallSmsSocialNotificationRequest.isQzoneEnabled(), setCallSmsSocialNotificationRequest.isSnapchatEnabled(), setCallSmsSocialNotificationRequest.isSkypeEnabled(), setCallSmsSocialNotificationRequest.isLineEnabled(), setCallSmsSocialNotificationRequest.isTelegramEnabled(), setCallSmsSocialNotificationRequest.isLinkedInEnabled()).setYoutubeEnabled(setCallSmsSocialNotificationRequest.isYoutubeEnabled()).setCommonAppEnabled(setCallSmsSocialNotificationRequest.isOtherAppEnabled()).setKaKaoTalkEnabled(setCallSmsSocialNotificationRequest.isKaKaoTalkEnabled()).setGmailEnabled(setCallSmsSocialNotificationRequest.isGmailEnabled()).setNewsEnabled(setCallSmsSocialNotificationRequest.isNewsEnabled()).setCustomEventEnabled(setCallSmsSocialNotificationRequest.isCustomEventEnabled()).doesSupportExtendedAppNotification(getDeviceSupportedFeatures().isExtendedNotificationsSupported()).build();
                build.setReqId(request.getRequId());
                build.setDevicePlatformEnum(DevicePlatformEnum.Sifli);
                LeonardoBleApiImpl.bleService.sendRequest(build, this);
            } else if (request instanceof SetButtonFunctionRequest) {
                SetButtonFunctionRequest setButtonFunctionRequest = (SetButtonFunctionRequest) request;
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.SET_BUTTON_FUNCTION);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                SetButtonFunctionReq setButtonFunctionReq = new SetButtonFunctionReq(null, (short) 1, setButtonFunctionRequest.getVesrion(), setButtonFunctionRequest.getPosition(), setButtonFunctionRequest.getShortPress(), setButtonFunctionRequest.getLongPress());
                setButtonFunctionReq.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(setButtonFunctionReq, this);
            } else {
                boolean z = false;
                if (request instanceof SetNavigationDisclaimerRequest) {
                    request.setResponseListener(listener);
                    request.setRequId(UUID.randomUUID().toString());
                    request.setBleCommand(BleCommand.SET_NAVIGATION_DISCLAIMER);
                    addToQueue(request);
                    SetNavigationDisclaimerRequest setNavigationDisclaimerRequest = (SetNavigationDisclaimerRequest) request;
                    SetNavigationDisclaimerReq setNavigationDisclaimerReq = new SetNavigationDisclaimerReq(null, (short) 0, setNavigationDisclaimerRequest.getVesrionText(), setNavigationDisclaimerRequest.getDisclaimerText());
                    setNavigationDisclaimerReq.setReqId(request.getRequId());
                    LeonardoBleApiImpl.bleService.sendRequest(setNavigationDisclaimerReq, this);
                } else if (request instanceof SetNavigationStatusRequest) {
                    request.setResponseListener(listener);
                    request.setBleCommand(BleCommand.SET_NAVIGATION_STATUS);
                    request.setRequId(UUID.randomUUID().toString());
                    addToQueue(request);
                    SetNavigationStatusReq setNavigationStatusReq = new SetNavigationStatusReq(null, ((SetNavigationStatusRequest) request).getStatus());
                    setNavigationStatusReq.setReqId(request.getRequId());
                    LeonardoBleApiImpl.bleService.sendRequest(setNavigationStatusReq, this);
                } else if (request instanceof SetNavigationConfigurationRequest) {
                    SetNavigationConfigurationRequest setNavigationConfigurationRequest = (SetNavigationConfigurationRequest) request;
                    request.setResponseListener(listener);
                    request.setBleCommand(BleCommand.SET_NAVIGATION_CONFIGURATION);
                    request.setRequId(UUID.randomUUID().toString());
                    addToQueue(request);
                    SetNavigationConfigurationRequest setNavigationConfigurationRequest2 = (SetNavigationConfigurationRequest) request;
                    SetNavigationConfigurationReq setNavigationConfigurationReq = new SetNavigationConfigurationReq(null, (short) 0, setNavigationConfigurationRequest2.isAudioEnabled(), setNavigationConfigurationRequest2.isHapticEnabled(), setNavigationConfigurationRequest2.isAODEnabled());
                    setNavigationConfigurationReq.setReqId(request.getRequId());
                    LeonardoBleApiImpl.bleService.sendRequest(setNavigationConfigurationReq, this);
                } else if (request instanceof SetNavigationAliveTimerRequest) {
                    request.setResponseListener(listener);
                    request.setBleCommand(BleCommand.SET_NAVIGATION_ALIVE_TIMER);
                    request.setRequId(UUID.randomUUID().toString());
                    addToQueue(request);
                    SetNavigationAliveTimerReq setNavigationAliveTimerReq = new SetNavigationAliveTimerReq(null, ((SetNavigationAliveTimerRequest) request).getSeconds());
                    setNavigationAliveTimerReq.setReqId(request.getRequId());
                    LeonardoBleApiImpl.bleService.sendRequest(setNavigationAliveTimerReq, this);
                } else if (request instanceof SetNavigationFavouriteLocationRequest) {
                    request.setResponseListener(listener);
                    request.setBleCommand(BleCommand.SET_NAVIGATION_FAVOURITE_LOCATION);
                    request.setRequId(UUID.randomUUID().toString());
                    addToQueue(request);
                    SetNavigationFavouriteLocationReq setNavigationFavouriteLocationReq = new SetNavigationFavouriteLocationReq(null, (short) 0, ((SetNavigationFavouriteLocationRequest) request).getFavouriteLocationList());
                    setNavigationFavouriteLocationReq.setReqId(request.getRequId());
                    LeonardoBleApiImpl.bleService.sendRequest(setNavigationFavouriteLocationReq, this);
                } else if (request instanceof SetNavigationEventRequest) {
                    SetNavigationEventRequest setNavigationEventRequest = (SetNavigationEventRequest) request;
                    request.setResponseListener(listener);
                    request.setBleCommand(BleCommand.SET_NAVIGATION_EVENT);
                    request.setRequId(UUID.randomUUID().toString());
                    addToQueue(request);
                    if (((SetNavigationEventRequest) request).getEvent()) {
                        setNavigationEventReq = new SetNavigationEventReq(null, (short) 2, Boolean.valueOf(setNavigationEventRequest.getEvent()), setNavigationEventRequest.getSource(), setNavigationEventRequest.getDestination(), setNavigationEventRequest.getMode());
                    } else {
                        setNavigationEventReq = new SetNavigationEventReq(null, (short) 2, Boolean.valueOf(setNavigationEventRequest.getEvent()));
                    }
                    setNavigationEventReq.setReqId(request.getRequId());
                    LeonardoBleApiImpl.bleService.sendRequest(setNavigationEventReq, this);
                } else if (request instanceof SetSmartAlertApplicationContentRequest) {
                    request.setResponseListener(listener);
                    request.setBleCommand(BleCommand.SET_SMART_ALERT_COMMON_APPLICATION_CONTENT);
                    request.setRequId(UUID.randomUUID().toString());
                    addToQueue(request);
                    SetSmartAlertApplicationContentRequest setSmartAlertApplicationContentRequest = (SetSmartAlertApplicationContentRequest) request;
                    SetSmartAlertApplicationContentReq setSmartAlertApplicationContentReq = new SetSmartAlertApplicationContentReq(null, setSmartAlertApplicationContentRequest.getAppId(), setSmartAlertApplicationContentRequest.getDisplayPosition(), LeonardoFormatter.getBleDynamicFieldData(setSmartAlertApplicationContentRequest.getDynamicSportFields()));
                    setSmartAlertApplicationContentReq.setDevicePlatformEnum(DevicePlatformEnum.Sifli);
                    setSmartAlertApplicationContentReq.setReqId(request.getRequId());
                    LeonardoBleApiImpl.bleService.sendRequest(setSmartAlertApplicationContentReq, this);
                } else if (request instanceof SetNonSmartAlertApplicationContentRequest) {
                    request.setResponseListener(listener);
                    request.setBleCommand(BleCommand.SET_NON_SMART_ALERT_COMMON_APPLICATION_CONTENT);
                    request.setRequId(UUID.randomUUID().toString());
                    addToQueue(request);
                    SetNonSmartAlertApplicationContentRequest setNonSmartAlertApplicationContentRequest = (SetNonSmartAlertApplicationContentRequest) request;
                    SetNonSmartAlertApplicationContentReq setNonSmartAlertApplicationContentReq = new SetNonSmartAlertApplicationContentReq(null, setNonSmartAlertApplicationContentRequest.getAppId(), setNonSmartAlertApplicationContentRequest.getDisplayPosition(), setNonSmartAlertApplicationContentRequest.getTitle(), setNonSmartAlertApplicationContentRequest.getContent());
                    setNonSmartAlertApplicationContentReq.setDevicePlatformEnum(DevicePlatformEnum.Sifli);
                    setNonSmartAlertApplicationContentReq.setReqId(request.getRequId());
                    LeonardoBleApiImpl.bleService.sendRequest(setNonSmartAlertApplicationContentReq, this);
                } else if (request instanceof SetNavigationApplicationContentRequest) {
                    request.setResponseListener(listener);
                    request.setBleCommand(BleCommand.SET_NAVIGATION_COMMON_APPLICATION_CONTENT);
                    request.setRequId(UUID.randomUUID().toString());
                    addToQueue(request);
                    SetNavigationApplicationContentRequest setNavigationApplicationContentRequest = (SetNavigationApplicationContentRequest) request;
                    SetNavigationApplicationContentReq setNavigationApplicationContentReq = new SetNavigationApplicationContentReq(null, setNavigationApplicationContentRequest.getAppId(), setNavigationApplicationContentRequest.getDisplayPosition(), setNavigationApplicationContentRequest.getEtaDay(), setNavigationApplicationContentRequest.getEtaHour(), setNavigationApplicationContentRequest.getEtaMin(), setNavigationApplicationContentRequest.getDistance(), LeonardoFormatter.getBleNavigationItem(setNavigationApplicationContentRequest.getNavigationItems()));
                    setNavigationApplicationContentReq.setDevicePlatformEnum(DevicePlatformEnum.Sifli);
                    setNavigationApplicationContentReq.setReqId(request.getRequId());
                    LeonardoBleApiImpl.bleService.sendRequest(setNavigationApplicationContentReq, this);
                } else if (request instanceof SetSmartAlertConfigRequest) {
                    request.setResponseListener(listener);
                    request.setBleCommand(BleCommand.SET_SMART_ALERT_CONFIG);
                    request.setRequId(UUID.randomUUID().toString());
                    addToQueue(request);
                    ArrayList arrayList = new ArrayList();
                    SetSmartAlertConfigRequest setSmartAlertConfigRequest = (SetSmartAlertConfigRequest) request;
                    List<SmartAlertAppData> appList = setSmartAlertConfigRequest.getAppList();
                    if (!((appList == null || appList.isEmpty()) ? true : true)) {
                        for (SmartAlertAppData smartAlertAppData : setSmartAlertConfigRequest.getAppList()) {
                            arrayList.add(new BleSmartAlertAppData(smartAlertAppData.getId(), smartAlertAppData.getName(), smartAlertAppData.getLogoSize(), smartAlertAppData.getLogoInfo()));
                        }
                    }
                    SetSmartAlertConfigReq setSmartAlertConfigReq = new SetSmartAlertConfigReq(null, (short) 1, setSmartAlertConfigRequest.isEnabled(), arrayList);
                    setSmartAlertConfigReq.setReqId(request.getRequId());
                    LeonardoBleApiImpl.bleService.sendRequest(setSmartAlertConfigReq, this);
                } else {
                    super.setUserSettings(request, listener);
                }
            }
        } else if (request.getBleCommand() != null) {
            onSettingsError(listener, new BleBaseError("Band is not connected", request.getBleCommand()));
        } else {
            onSettingsError(listener, new BleBaseError("Band is not connected"));
        }
    }
}
