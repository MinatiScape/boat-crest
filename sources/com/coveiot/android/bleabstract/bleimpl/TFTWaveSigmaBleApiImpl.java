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
import com.coveiot.android.bleabstract.request.SetCallSmsSocialNotificationRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.services.LeonardoBleCmdService;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.api.model.ProgressDataBean;
import com.coveiot.sdk.ble.api.request.MessageAlertSwitchesReq;
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
public class TFTWaveSigmaBleApiImpl extends ULC5UltimaConnectBleApiImpl {
    @Nullable
    public static TFTWaveSigmaBleApiImpl C;
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final String B = TFTWaveSigmaBleApiImpl.class.getSimpleName();

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final TFTWaveSigmaBleApiImpl getInstance(@NotNull Context ctxt) {
            Intrinsics.checkNotNullParameter(ctxt, "ctxt");
            if (TFTWaveSigmaBleApiImpl.C == null) {
                LeonardoBleApiImpl.context = ctxt.getApplicationContext();
                TFTWaveSigmaBleApiImpl.C = new TFTWaveSigmaBleApiImpl();
            }
            LeonardoBleApiImpl.intent = new Intent(LeonardoBleApiImpl.context, LeonardoBleCmdService.class);
            if (!LeonardoBleApiImpl.checkIfServiceIsRunning()) {
                LogHelper.d(TFTWaveSigmaBleApiImpl.B, "service is not running ++ ", ModuleNames.BLEABSTRACT.getModuleName());
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
            return TFTWaveSigmaBleApiImpl.C;
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.ULC5UltimaConnectBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
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
        deviceSupportedFeatures.setOneClickToConnectSupported(true);
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
        deviceSupportedFeatures.setMaxCharSupportedInNotification(200);
        deviceSupportedFeatures.setRawPPGHistaoryDataSupported(true);
        deviceSupportedFeatures.setPeriodicSpO2Supported(true);
        deviceSupportedFeatures.setMusicPlaybackStateChangeFromAppSupported(true);
        deviceSupportedFeatures.setCameraFeatureSupported(true);
        deviceSupportedFeatures.setSleepTargetSupported(true);
        deviceSupportedFeatures.set1kActivitySupported(true);
        deviceSupportedFeatures.setFemaleWellnessSupported(false);
        deviceSupportedFeatures.setQuickReplySupported(true);
        deviceSupportedFeatures.setWeatherSupportedInBand(false);
        deviceSupportedFeatures.setCustomMessageSupported(true);
        deviceSupportedFeatures.setKaHaRealtekChip(true);
        deviceSupportedFeatures.setWeatherEnableCommandSupported(false);
        deviceSupportedFeatures.setNotificationConfigurationSupported(true);
        deviceSupportedFeatures.setFindMyBandSupported(true);
        deviceSupportedFeatures.setMaxContactsInOneRequest(20);
        deviceSupportedFeatures.setContactSyncSupported(true);
        deviceSupportedFeatures.setBTCallingSupported(true);
        deviceSupportedFeatures.setKahaBTCallSupported(true);
        deviceSupportedFeatures.setScheduledLiftWristViewSettingsSupported(true);
        deviceSupportedFeatures.setDiagnosticTestSupported(true);
        deviceSupportedFeatures.setNudgeSupported(true);
        deviceSupportedFeatures.setPairingFlowTypeCommandSupported(true);
        Context context = LeonardoBleApiImpl.context;
        Intrinsics.checkNotNullExpressionValue(context, "context");
        String macAddress = getMacAddress();
        Intrinsics.checkNotNullExpressionValue(macAddress, "getMacAddress()");
        return BleApiUtils.updateDeviceSupportedFeatureBasedOnFWAndAppCapability(context, macAddress, deviceSupportedFeatures);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.ULC5UltimaConnectBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.ULC5UltimaConnectBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onConnectionStateChanged(@NotNull CloveBleState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        super.onConnectionStateChanged(state);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.ULC5UltimaConnectBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onError(@NotNull Error error) {
        Intrinsics.checkNotNullParameter(error, "error");
        super.onError(error);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.ULC5UltimaConnectBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
        Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
        super.onProgressUpdate(progressDataBean);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.ULC5UltimaConnectBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onResponse(@NotNull BaseResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        super.onResponse(response);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.ULC5UltimaConnectBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onResponseEventReceivved(@NotNull ResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        super.onResponseEventReceivved(responseEvent);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.ULC5UltimaConnectBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<BloodPressureMeasurement> registerLiveBpData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.ULC5UltimaConnectBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
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