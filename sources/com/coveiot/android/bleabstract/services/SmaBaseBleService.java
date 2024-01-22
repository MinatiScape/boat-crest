package com.coveiot.android.bleabstract.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.format.DateFormat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.Alarm;
import com.coveiot.android.bleabstract.models.CloveSmaBleState;
import com.coveiot.android.bleabstract.models.DNDData;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerSma;
import com.coveiot.android.bleabstract.response.CallRejectRes;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.response.GetLiftWristResponse;
import com.coveiot.android.bleabstract.response.LiveMusicControlRes;
import com.coveiot.android.bleabstract.response.MusicControlState;
import com.coveiot.android.bleabstract.response.StreamProgressResponse;
import com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2;
import com.coveiot.android.smasdk.SmaResponseListener;
import com.coveiot.android.smasdk.api.SmaBaseReq;
import com.coveiot.android.smasdk.api.SmaBaseRes;
import com.coveiot.android.smasdk.api.SmaCalorieGoalReq;
import com.coveiot.android.smasdk.api.SmaCurrentWeatherReq;
import com.coveiot.android.smasdk.api.SmaCurrentWeatherReq2;
import com.coveiot.android.smasdk.api.SmaDeleteScheduleReq;
import com.coveiot.android.smasdk.api.SmaDistanceGoalReq;
import com.coveiot.android.smasdk.api.SmaExitRemoteCameraReq;
import com.coveiot.android.smasdk.api.SmaGestureWakeReq;
import com.coveiot.android.smasdk.api.SmaGetAlarmsReq;
import com.coveiot.android.smasdk.api.SmaGetWatchFacePositionReq;
import com.coveiot.android.smasdk.api.SmaGetWorldClockReq;
import com.coveiot.android.smasdk.api.SmaGirlCareSettingsReq;
import com.coveiot.android.smasdk.api.SmaNotificationReq;
import com.coveiot.android.smasdk.api.SmaOtaReq;
import com.coveiot.android.smasdk.api.SmaScreenTimeOutReq;
import com.coveiot.android.smasdk.api.SmaSetDNDReq;
import com.coveiot.android.smasdk.api.SmaSetHourSystemReq;
import com.coveiot.android.smasdk.api.SmaSetHrMonitoringReq;
import com.coveiot.android.smasdk.api.SmaSetMusicMetaDataReq;
import com.coveiot.android.smasdk.api.SmaSetPlaybackStateReq;
import com.coveiot.android.smasdk.api.SmaSetScheduleReq;
import com.coveiot.android.smasdk.api.SmaSetSedentaryReminderReq;
import com.coveiot.android.smasdk.api.SmaSetTemperatureDetectingReq;
import com.coveiot.android.smasdk.api.SmaSetTemperatureUnitReq;
import com.coveiot.android.smasdk.api.SmaSetUserProfileReq;
import com.coveiot.android.smasdk.api.SmaSetVibrationAlarmReq;
import com.coveiot.android.smasdk.api.SmaSetWorldClockReq;
import com.coveiot.android.smasdk.api.SmaSleepGoalReq;
import com.coveiot.android.smasdk.api.SmaSleepQualityReq;
import com.coveiot.android.smasdk.api.SmaStepGoalReq;
import com.coveiot.android.smasdk.api.SmaStopNotificationReq;
import com.coveiot.android.smasdk.api.SmaSyncContactsReq;
import com.coveiot.android.smasdk.api.SmaUploadContactReq;
import com.coveiot.android.smasdk.api.SmaUploadWatchFaceReq;
import com.coveiot.android.smasdk.error.SmaError;
import com.coveiot.android.smasdk.error.SmaErrorType;
import com.coveiot.khsmadb.activity.KhActivityRepository;
import com.coveiot.sdk.ble.api.model.CameraState;
import com.coveiot.sdk.ble.api.model.FindMyPhoneState;
import com.coveiot.sdk.ble.api.response.CameraEventRes;
import com.coveiot.sdk.ble.api.response.FindMyPhoneRes;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.sdk.ble.events.ResponseStatus;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.LogHelper;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.szabh.smable3.BleKey;
import com.szabh.smable3.BleKeyFlag;
import com.szabh.smable3.component.BleCache;
import com.szabh.smable3.component.BleConnector;
import com.szabh.smable3.component.BleHandleCallback;
import com.szabh.smable3.entity.BleActivity;
import com.szabh.smable3.entity.BleAlarm;
import com.szabh.smable3.entity.BleAlipaySettings;
import com.szabh.smable3.entity.BleAppSportState;
import com.szabh.smable3.entity.BleAvgHeartRate;
import com.szabh.smable3.entity.BleBAC;
import com.szabh.smable3.entity.BleBloodGlucose;
import com.szabh.smable3.entity.BleBloodOxygen;
import com.szabh.smable3.entity.BleBloodPressure;
import com.szabh.smable3.entity.BleBodyStatus;
import com.szabh.smable3.entity.BleCalorieIntake;
import com.szabh.smable3.entity.BleCoachingIds;
import com.szabh.smable3.entity.BleDeviceFile;
import com.szabh.smable3.entity.BleDeviceInfo;
import com.szabh.smable3.entity.BleDeviceInfo2;
import com.szabh.smable3.entity.BleDrinkWaterSettings;
import com.szabh.smable3.entity.BleFoodBalance;
import com.szabh.smable3.entity.BleGSensorMotion;
import com.szabh.smable3.entity.BleGSensorRaw;
import com.szabh.smable3.entity.BleGestureWake;
import com.szabh.smable3.entity.BleHRRaw;
import com.szabh.smable3.entity.BleHeartRate;
import com.szabh.smable3.entity.BleHrMonitoringSettings;
import com.szabh.smable3.entity.BleHrv;
import com.szabh.smable3.entity.BleLanguagePackVersion;
import com.szabh.smable3.entity.BleLocation;
import com.szabh.smable3.entity.BleLocationGga;
import com.szabh.smable3.entity.BleLocationGsv;
import com.szabh.smable3.entity.BleLogText;
import com.szabh.smable3.entity.BleLoveTap;
import com.szabh.smable3.entity.BleLoveTapUser;
import com.szabh.smable3.entity.BleMatchRecord;
import com.szabh.smable3.entity.BleMatchRecord2;
import com.szabh.smable3.entity.BleMedicationAlarm;
import com.szabh.smable3.entity.BleMedicationReminder;
import com.szabh.smable3.entity.BleMindStatus;
import com.szabh.smable3.entity.BleMusicControl;
import com.szabh.smable3.entity.BleNaviInfo;
import com.szabh.smable3.entity.BleNoDisturbSettings;
import com.szabh.smable3.entity.BlePackageStatus;
import com.szabh.smable3.entity.BlePressure;
import com.szabh.smable3.entity.BleRealTimeMeasurement;
import com.szabh.smable3.entity.BleRealtimeLog;
import com.szabh.smable3.entity.BleRecordPacket;
import com.szabh.smable3.entity.BleRepeat;
import com.szabh.smable3.entity.BleSMSQuickReply;
import com.szabh.smable3.entity.BleSedentarinessSettings;
import com.szabh.smable3.entity.BleSleep;
import com.szabh.smable3.entity.BleSleepQuality;
import com.szabh.smable3.entity.BleStock;
import com.szabh.smable3.entity.BleTemperature;
import com.szabh.smable3.entity.BleThirdPartyData;
import com.szabh.smable3.entity.BleTime;
import com.szabh.smable3.entity.BleTimeRange;
import com.szabh.smable3.entity.BleTimeZone;
import com.szabh.smable3.entity.BleTuyaKey;
import com.szabh.smable3.entity.BleUserProfile;
import com.szabh.smable3.entity.BleWatchFaceId;
import com.szabh.smable3.entity.BleWorkout;
import com.szabh.smable3.entity.BleWorkout2;
import com.szabh.smable3.entity.BleWorldClock;
import com.szabh.smable3.entity.MusicCommand;
import com.szabh.smable3.watchface.Element;
import com.szabh.smable3.watchface.WatchFaceBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class SmaBaseBleService extends Service {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public BluetoothDevice f3913a;
    @Nullable
    public SmaBaseReq f;
    public boolean g;
    @Nullable
    public ConnectionError h;
    public String macAddress;
    public IBinder serviceBinder;
    @Nullable
    public Handler b = new Handler();
    @Nullable
    public Handler c = new Handler();
    @NotNull
    public Handler d = new Handler(Looper.getMainLooper());
    @Nullable
    public CloveSmaBleState.BleState e = CloveSmaBleState.BleState.DISCONNECTED;
    public long i = -1;
    public final String j = SmaBaseBleService.class.getSimpleName();
    @NotNull
    public final Lazy k = LazyKt__LazyJVMKt.lazy(new Function0<SmaBaseBleService$bleHandleCallback$2.AnonymousClass1>() { // from class: com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2

        /* renamed from: com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public final class AnonymousClass1 implements BleHandleCallback {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ SmaBaseBleService f3915a;

            /* renamed from: com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2$1$WhenMappings */
            /* loaded from: classes2.dex */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[MusicCommand.values().length];
                    try {
                        iArr[MusicCommand.TOGGLE.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[MusicCommand.PLAY.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[MusicCommand.PAUSE.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    try {
                        iArr[MusicCommand.NEXT.ordinal()] = 4;
                    } catch (NoSuchFieldError unused4) {
                    }
                    try {
                        iArr[MusicCommand.PRE.ordinal()] = 5;
                    } catch (NoSuchFieldError unused5) {
                    }
                    try {
                        iArr[MusicCommand.VOLUME_UP.ordinal()] = 6;
                    } catch (NoSuchFieldError unused6) {
                    }
                    try {
                        iArr[MusicCommand.VOLUME_DOWN.ordinal()] = 7;
                    } catch (NoSuchFieldError unused7) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            public AnonymousClass1(SmaBaseBleService smaBaseBleService) {
                this.f3915a = smaBaseBleService;
            }

            public static final void a(SmaBaseBleService this$0, int i, BleKey bleKey) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                Intrinsics.checkNotNullParameter(bleKey, "$bleKey");
                String tag = this$0.getTAG();
                LogHelper.d(tag, "onSyncData called -> " + i + ", " + bleKey);
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SmaBaseBleService$bleHandleCallback$2$1$onSyncData$1$1(i, bleKey, this$0, null), 2, null);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onAlarmAdd(@NotNull BleAlarm alarm) {
                Intrinsics.checkNotNullParameter(alarm, "alarm");
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onAlarmAdd called -> " + alarm);
                SmaBaseBleService.access$updateAlarmToAppLayer(this.f3915a, alarm);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onAlarmDelete(int i) {
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onAlarmDelete called -> " + i);
                if (this.f3915a.getKhCurrentCommand() != null) {
                    SmaBaseRes smaBaseRes = new SmaBaseRes();
                    smaBaseRes.setObj(Integer.valueOf(i));
                    SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    smaBaseRes.setBaseReq(khCurrentCommand);
                    SmaBaseReq khCurrentCommand2 = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand2);
                    SmaResponseListener responseListener = khCurrentCommand2.getResponseListener();
                    Intrinsics.checkNotNull(responseListener);
                    responseListener.onResponse(smaBaseRes);
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onAlarmUpdate(@NotNull BleAlarm alarm) {
                Intrinsics.checkNotNullParameter(alarm, "alarm");
                SmaBaseBleService.access$updateAlarmToAppLayer(this.f3915a, alarm);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onAppSportDataResponse(boolean z) {
                BleHandleCallback.DefaultImpls.onAppSportDataResponse(this, z);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onBacklightUpdate(int i) {
                BleHandleCallback.DefaultImpls.onBacklightUpdate(this, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onBleThirdPartyDataUpdate(@NotNull BleThirdPartyData bleThirdPartyData) {
                BleHandleCallback.DefaultImpls.onBleThirdPartyDataUpdate(this, bleThirdPartyData);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onCameraResponse(boolean z, int i) {
                BleHandleCallback.DefaultImpls.onCameraResponse(this, z, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onCameraStateChange(int i) {
                BleHandleCallback.DefaultImpls.onCameraStateChange(this, i);
                if (i < 2) {
                    BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.CAMERA_CLICK, new CameraEventRes(i == 0 ? CameraState.EXIT : CameraState.ENTER), ResponseStatus.RESPONSE_STATUS_SUCCESS));
                    this.f3915a.setRemoteCameraOpened(i == 1);
                    return;
                }
                BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.CAMERA_CLICK, new CameraEventRes(CameraState.CAPTURE), ResponseStatus.RESPONSE_STATUS_SUCCESS));
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onClassicBluetoothStateChange(int i) {
                BleHandleCallback.DefaultImpls.onClassicBluetoothStateChange(this, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onCommandReply(@NotNull BleKey bleKey, @NotNull BleKeyFlag bleKeyFlag, boolean z) {
                BleKey key;
                Intrinsics.checkNotNullParameter(bleKey, "bleKey");
                Intrinsics.checkNotNullParameter(bleKeyFlag, "bleKeyFlag");
                String tag = this.f3915a.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("onCommandReply called -> ");
                sb.append(bleKey.name());
                sb.append(' ');
                SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                sb.append((khCurrentCommand == null || (key = khCurrentCommand.getKey()) == null) ? null : key.name());
                LogHelper.d(tag, sb.toString());
                if (this.f3915a.getKhCurrentCommand() != null) {
                    SmaBaseReq khCurrentCommand2 = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand2);
                    if (khCurrentCommand2.getKey() != null) {
                        SmaBaseReq khCurrentCommand3 = this.f3915a.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand3);
                        if (khCurrentCommand3.getKey() == bleKey) {
                            SmaBaseRes smaBaseRes = new SmaBaseRes();
                            SmaBaseReq khCurrentCommand4 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand4);
                            smaBaseRes.setBaseReq(khCurrentCommand4);
                            SmaBaseReq khCurrentCommand5 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand5);
                            SmaResponseListener responseListener = khCurrentCommand5.getResponseListener();
                            Intrinsics.checkNotNull(responseListener);
                            responseListener.onResponse(smaBaseRes);
                        }
                    }
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onCommandSendTimeout(@NotNull BleKey bleKey, @NotNull BleKeyFlag bleKeyFlag) {
                BleHandleCallback.DefaultImpls.onCommandSendTimeout(this, bleKey, bleKeyFlag);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onDeviceConnected(@NotNull BluetoothDevice device) {
                Intrinsics.checkNotNullParameter(device, "device");
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onDeviceConnected called -> " + device.getAddress());
                this.f3915a.setMBluetoothDevice(device);
                BleConnector bleConnector = BleConnector.INSTANCE;
                if (bleConnector.isAvailable()) {
                    this.f3915a.h = null;
                    this.f3915a.updateConnectionState(CloveSmaBleState.BleState.CONNECTED, true);
                    LogHelper.d(this.f3915a.getTAG(), "onSessionStateChange writing time zone to band...");
                    BleKey bleKey = BleKey.TIME_ZONE;
                    BleKeyFlag bleKeyFlag = BleKeyFlag.UPDATE;
                    BleConnector.sendObject$default(bleConnector, bleKey, bleKeyFlag, new BleTimeZone(), false, false, 24, null);
                    LogHelper.d(this.f3915a.getTAG(), "onSessionStateChange writing local time to band...");
                    BleConnector.sendObject$default(bleConnector, BleKey.TIME, bleKeyFlag, BleTime.Companion.local(), false, false, 24, null);
                    BleConnector.sendInt8$default(bleConnector, BleKey.HOUR_SYSTEM, bleKeyFlag, !DateFormat.is24HourFormat(this.f3915a.getApplicationContext()) ? 1 : 0, false, false, 24, null);
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onDeviceConnecting(boolean z) {
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onDeviceConnecting called -> " + z);
                if (z) {
                    this.f3915a.updateConnectionState(CloveSmaBleState.BleState.CONNECTING, true);
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onDeviceFileUpdate(@NotNull BleDeviceFile bleDeviceFile) {
                BleHandleCallback.DefaultImpls.onDeviceFileUpdate(this, bleDeviceFile);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onDeviceRequestAGpsFile(@NotNull String str) {
                BleHandleCallback.DefaultImpls.onDeviceRequestAGpsFile(this, str);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onDeviceSMSQuickReply(@NotNull BleSMSQuickReply bleSMSQuickReply) {
                BleHandleCallback.DefaultImpls.onDeviceSMSQuickReply(this, bleSMSQuickReply);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onFindPhone(boolean z) {
                FindMyPhoneRes findMyPhoneRes;
                if (z) {
                    findMyPhoneRes = new FindMyPhoneRes(FindMyPhoneState.ON);
                } else {
                    findMyPhoneRes = new FindMyPhoneRes(FindMyPhoneState.OFF);
                }
                BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.FIND_MY_PHONE, findMyPhoneRes, ResponseStatus.RESPONSE_STATUS_SUCCESS));
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onFollowSystemLanguage(boolean z) {
                BleHandleCallback.DefaultImpls.onFollowSystemLanguage(this, z);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onGestureWakeUpdate(@NotNull BleGestureWake gestureWake) {
                Intrinsics.checkNotNullParameter(gestureWake, "gestureWake");
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onGestureWakeUpdate called -> " + gestureWake);
                SmaBaseBleService.access$updateWakeGestureToAppLayer(this.f3915a, gestureWake);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onHIDState(int i) {
                BleHandleCallback.DefaultImpls.onHIDState(this, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onHIDValueChange(int i) {
                BleHandleCallback.DefaultImpls.onHIDValueChange(this, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onIdentityCreate(boolean z, @Nullable BleDeviceInfo bleDeviceInfo) {
                String tag = this.f3915a.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("onIdentityCreate called -> ");
                sb.append(bleDeviceInfo != null ? bleDeviceInfo.getMBleAddress() : null);
                LogHelper.d(tag, sb.toString());
                if (z) {
                    BleConnector bleConnector = BleConnector.INSTANCE;
                    bleConnector.connectClassic();
                    LogHelper.d(this.f3915a.getTAG(), "onSessionStateChange writing time zone to band...");
                    BleKey bleKey = BleKey.TIME_ZONE;
                    BleKeyFlag bleKeyFlag = BleKeyFlag.UPDATE;
                    BleConnector.sendObject$default(bleConnector, bleKey, bleKeyFlag, new BleTimeZone(), false, false, 24, null);
                    LogHelper.d(this.f3915a.getTAG(), "onSessionStateChange writing local time to band...");
                    BleConnector.sendObject$default(bleConnector, BleKey.TIME, bleKeyFlag, BleTime.Companion.local(), false, false, 24, null);
                    return;
                }
                this.f3915a.setMBluetoothDevice(null);
                this.f3915a.h = new ConnectionError(8, System.currentTimeMillis());
                this.f3915a.updateConnectionState(CloveSmaBleState.BleState.DISCONNECTED, true);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onIdentityDelete(boolean z) {
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onIdentityDelete " + z);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onIdentityDeleteByDevice(boolean z) {
                BleHandleCallback.DefaultImpls.onIdentityDeleteByDevice(this, z);
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onIdentityDeleteByDevice called -> " + z);
                KhActivityRepository.Companion.getInstance(this.f3915a).deleteActivityInfoForDate(this.f3915a.getMacAddress(), BleApiUtils.INSTANCE.getTodayDate());
                Intent intent = new Intent(Constants.FACTORY_RESET_BROADCAST_INTENT);
                intent.putExtra(Constants.FACTORY_RESET_BROADCAST_INTENT_EXTRA, true);
                LocalBroadcastManager.getInstance(this.f3915a).sendBroadcast(intent);
                this.f3915a.disconnectAndForget();
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onIncomingCallStatus(int i) {
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onIncomingCallStatus called " + i);
                SmaBaseBleService.access$updateCallAcceptRejectToAppLayer(this.f3915a, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onLoveTapUpdate(@NotNull BleLoveTap bleLoveTap) {
                BleHandleCallback.DefaultImpls.onLoveTapUpdate(this, bleLoveTap);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onLoveTapUserDelete(int i) {
                BleHandleCallback.DefaultImpls.onLoveTapUserDelete(this, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onLoveTapUserUpdate(@NotNull BleLoveTapUser bleLoveTapUser) {
                BleHandleCallback.DefaultImpls.onLoveTapUserUpdate(this, bleLoveTapUser);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onMedicationAlarmAdd(@NotNull BleMedicationAlarm bleMedicationAlarm) {
                BleHandleCallback.DefaultImpls.onMedicationAlarmAdd(this, bleMedicationAlarm);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onMedicationAlarmDelete(int i) {
                BleHandleCallback.DefaultImpls.onMedicationAlarmDelete(this, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onMedicationAlarmUpdate(@NotNull BleMedicationAlarm bleMedicationAlarm) {
                BleHandleCallback.DefaultImpls.onMedicationAlarmUpdate(this, bleMedicationAlarm);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onMedicationReminderDelete(int i) {
                BleHandleCallback.DefaultImpls.onMedicationReminderDelete(this, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onMedicationReminderUpdate(@NotNull BleMedicationReminder bleMedicationReminder) {
                BleHandleCallback.DefaultImpls.onMedicationReminderUpdate(this, bleMedicationReminder);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onNaviInfoUpdate(@NotNull BleNaviInfo bleNaviInfo) {
                BleHandleCallback.DefaultImpls.onNaviInfoUpdate(this, bleNaviInfo);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onNoDisturbUpdate(@NotNull BleNoDisturbSettings noDisturbSettings) {
                Intrinsics.checkNotNullParameter(noDisturbSettings, "noDisturbSettings");
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onNoDisturbUpdate called -> " + noDisturbSettings);
                SmaBaseBleService.access$updateDndToAppLayer(this.f3915a, noDisturbSettings);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onOTA(boolean z) {
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onOTA called -> " + z);
                if (this.f3915a.getKhCurrentCommand() != null) {
                    SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    if (khCurrentCommand.getKey() != null) {
                        SmaBaseReq khCurrentCommand2 = this.f3915a.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand2);
                        if (khCurrentCommand2.getKey() == BleKey.OTA) {
                            SmaBaseRes smaBaseRes = new SmaBaseRes();
                            SmaBaseReq khCurrentCommand3 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand3);
                            smaBaseRes.setBaseReq(khCurrentCommand3);
                            smaBaseRes.setObj(Boolean.valueOf(z));
                            SmaBaseReq khCurrentCommand4 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand4);
                            SmaResponseListener responseListener = khCurrentCommand4.getResponseListener();
                            Intrinsics.checkNotNull(responseListener);
                            responseListener.onResponse(smaBaseRes);
                        }
                    }
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onPowerSaveModeState(int i) {
                BleHandleCallback.DefaultImpls.onPowerSaveModeState(this, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onPowerSaveModeStateChange(int i) {
                BleHandleCallback.DefaultImpls.onPowerSaveModeStateChange(this, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadActivity(@NotNull List<BleActivity> activities) {
                Intrinsics.checkNotNullParameter(activities, "activities");
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadActivity called -> " + activities);
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SmaBaseBleService$bleHandleCallback$2$1$onReadActivity$1(activities, this.f3915a, null), 2, null);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadAlarm(@NotNull List<BleAlarm> alarms) {
                Intrinsics.checkNotNullParameter(alarms, "alarms");
                LogHelper.d(this.f3915a.getTAG(), "onReadAlarm called ->");
                if (this.f3915a.getKhCurrentCommand() != null) {
                    SmaBaseRes smaBaseRes = new SmaBaseRes();
                    smaBaseRes.setObj(alarms);
                    SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    smaBaseRes.setBaseReq(khCurrentCommand);
                    SmaBaseReq khCurrentCommand2 = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand2);
                    SmaResponseListener responseListener = khCurrentCommand2.getResponseListener();
                    Intrinsics.checkNotNull(responseListener);
                    responseListener.onResponse(smaBaseRes);
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadAlipaySettings(@NotNull BleAlipaySettings bleAlipaySettings) {
                BleHandleCallback.DefaultImpls.onReadAlipaySettings(this, bleAlipaySettings);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadAvgHeartRate(@NotNull List<BleAvgHeartRate> list) {
                BleHandleCallback.DefaultImpls.onReadAvgHeartRate(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadBAC(@NotNull List<BleBAC> list) {
                BleHandleCallback.DefaultImpls.onReadBAC(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadBacklight(int i) {
                BleHandleCallback.DefaultImpls.onReadBacklight(this, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadBleAddress(@NotNull String address) {
                Intrinsics.checkNotNullParameter(address, "address");
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadBleAddress called -> " + address);
                if (this.f3915a.getKhCurrentCommand() != null) {
                    SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    if (khCurrentCommand.getKey() != null) {
                        SmaBaseReq khCurrentCommand2 = this.f3915a.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand2);
                        if (khCurrentCommand2.getKey() == BleKey.BLE_ADDRESS) {
                            SmaBaseRes smaBaseRes = new SmaBaseRes();
                            SmaBaseReq khCurrentCommand3 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand3);
                            smaBaseRes.setBaseReq(khCurrentCommand3);
                            smaBaseRes.setObj(address);
                            SmaBaseReq khCurrentCommand4 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand4);
                            SmaResponseListener responseListener = khCurrentCommand4.getResponseListener();
                            Intrinsics.checkNotNull(responseListener);
                            responseListener.onResponse(smaBaseRes);
                        }
                    }
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadBleHrv(@NotNull List<BleHrv> list) {
                BleHandleCallback.DefaultImpls.onReadBleHrv(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadBleLogText(@NotNull List<BleLogText> list) {
                BleHandleCallback.DefaultImpls.onReadBleLogText(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadBloodGlucose(@NotNull List<BleBloodGlucose> list) {
                BleHandleCallback.DefaultImpls.onReadBloodGlucose(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadBloodOxygen(@NotNull List<BleBloodOxygen> bloodOxygen) {
                Intrinsics.checkNotNullParameter(bloodOxygen, "bloodOxygen");
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadBloodOxygen called -> " + bloodOxygen);
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SmaBaseBleService$bleHandleCallback$2$1$onReadBloodOxygen$1(bloodOxygen, this.f3915a, null), 2, null);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadBloodPressure(@NotNull List<BleBloodPressure> bloodPressures) {
                Intrinsics.checkNotNullParameter(bloodPressures, "bloodPressures");
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadBloodPressure called -> " + bloodPressures);
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SmaBaseBleService$bleHandleCallback$2$1$onReadBloodPressure$1(bloodPressures, this.f3915a, null), 2, null);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadBodyStatus(@NotNull List<BleBodyStatus> list) {
                BleHandleCallback.DefaultImpls.onReadBodyStatus(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadCalorieIntake(@NotNull List<BleCalorieIntake> list) {
                BleHandleCallback.DefaultImpls.onReadCalorieIntake(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadCaloriesGoal(int i) {
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadCaloriesGoal called -> " + i);
                if (this.f3915a.getKhCurrentCommand() != null) {
                    SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    if (khCurrentCommand.getKey() != null) {
                        SmaBaseReq khCurrentCommand2 = this.f3915a.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand2);
                        if (khCurrentCommand2.getKey() == BleKey.CALORIES_GOAL) {
                            SmaBaseRes smaBaseRes = new SmaBaseRes();
                            SmaBaseReq khCurrentCommand3 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand3);
                            smaBaseRes.setBaseReq(khCurrentCommand3);
                            smaBaseRes.setObj(Integer.valueOf(i));
                            SmaBaseReq khCurrentCommand4 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand4);
                            SmaResponseListener responseListener = khCurrentCommand4.getResponseListener();
                            Intrinsics.checkNotNull(responseListener);
                            responseListener.onResponse(smaBaseRes);
                        }
                    }
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadCoachingIds(@NotNull BleCoachingIds bleCoachingIds) {
                BleHandleCallback.DefaultImpls.onReadCoachingIds(this, bleCoachingIds);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadDateFormat(int i) {
                BleHandleCallback.DefaultImpls.onReadDateFormat(this, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadDeviceFile(@NotNull BleDeviceFile bleDeviceFile) {
                BleHandleCallback.DefaultImpls.onReadDeviceFile(this, bleDeviceFile);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadDeviceInfo(@NotNull BleDeviceInfo bleDeviceInfo) {
                BleHandleCallback.DefaultImpls.onReadDeviceInfo(this, bleDeviceInfo);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadDeviceInfo2(@NotNull BleDeviceInfo2 bleDeviceInfo2) {
                BleHandleCallback.DefaultImpls.onReadDeviceInfo2(this, bleDeviceInfo2);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadDistanceGoal(int i) {
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadDistanceGoal called -> " + i);
                if (this.f3915a.getKhCurrentCommand() != null) {
                    SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    if (khCurrentCommand.getKey() != null) {
                        SmaBaseReq khCurrentCommand2 = this.f3915a.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand2);
                        if (khCurrentCommand2.getKey() == BleKey.DISTANCE_GOAL) {
                            SmaBaseRes smaBaseRes = new SmaBaseRes();
                            SmaBaseReq khCurrentCommand3 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand3);
                            smaBaseRes.setBaseReq(khCurrentCommand3);
                            smaBaseRes.setObj(Integer.valueOf(i));
                            SmaBaseReq khCurrentCommand4 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand4);
                            SmaResponseListener responseListener = khCurrentCommand4.getResponseListener();
                            Intrinsics.checkNotNull(responseListener);
                            responseListener.onResponse(smaBaseRes);
                        }
                    }
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadDrinkWater(@NotNull BleDrinkWaterSettings bleDrinkWaterSettings) {
                BleHandleCallback.DefaultImpls.onReadDrinkWater(this, bleDrinkWaterSettings);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadFirmwareVersion(@NotNull String version) {
                Intrinsics.checkNotNullParameter(version, "version");
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadFirmwareVersion called -> " + version);
                if (this.f3915a.getKhCurrentCommand() != null) {
                    SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    if (khCurrentCommand.getKey() != null) {
                        SmaBaseReq khCurrentCommand2 = this.f3915a.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand2);
                        if (khCurrentCommand2.getKey() == BleKey.FIRMWARE_VERSION) {
                            SmaBaseRes smaBaseRes = new SmaBaseRes();
                            SmaBaseReq khCurrentCommand3 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand3);
                            smaBaseRes.setBaseReq(khCurrentCommand3);
                            smaBaseRes.setObj(version);
                            SmaBaseReq khCurrentCommand4 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand4);
                            SmaResponseListener responseListener = khCurrentCommand4.getResponseListener();
                            Intrinsics.checkNotNull(responseListener);
                            responseListener.onResponse(smaBaseRes);
                        }
                    }
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadFoodBalance(@NotNull List<BleFoodBalance> list) {
                BleHandleCallback.DefaultImpls.onReadFoodBalance(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadGestureWake(@NotNull BleGestureWake gestureWake) {
                Intrinsics.checkNotNullParameter(gestureWake, "gestureWake");
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadGestureWake called -> " + gestureWake);
                if (this.f3915a.getKhCurrentCommand() != null) {
                    SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    if (khCurrentCommand.getKey() != null) {
                        SmaBaseReq khCurrentCommand2 = this.f3915a.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand2);
                        if (khCurrentCommand2.getKey() == BleKey.GESTURE_WAKE) {
                            SmaBaseRes smaBaseRes = new SmaBaseRes();
                            SmaBaseReq khCurrentCommand3 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand3);
                            smaBaseRes.setBaseReq(khCurrentCommand3);
                            smaBaseRes.setObj(gestureWake);
                            SmaBaseReq khCurrentCommand4 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand4);
                            SmaResponseListener responseListener = khCurrentCommand4.getResponseListener();
                            Intrinsics.checkNotNull(responseListener);
                            responseListener.onResponse(smaBaseRes);
                        }
                    }
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadHeartRate(@NotNull List<BleHeartRate> heartRates) {
                Intrinsics.checkNotNullParameter(heartRates, "heartRates");
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadHeartRate called -> " + heartRates);
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SmaBaseBleService$bleHandleCallback$2$1$onReadHeartRate$1(heartRates, this.f3915a, null), 2, null);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadHourSystem(int i) {
                BleHandleCallback.DefaultImpls.onReadHourSystem(this, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadHrMonitoringSettings(@NotNull BleHrMonitoringSettings bleHrMonitoringSettings) {
                BleHandleCallback.DefaultImpls.onReadHrMonitoringSettings(this, bleHrMonitoringSettings);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadLanguagePackVersion(@NotNull BleLanguagePackVersion bleLanguagePackVersion) {
                BleHandleCallback.DefaultImpls.onReadLanguagePackVersion(this, bleLanguagePackVersion);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadLocation(@NotNull List<BleLocation> locations) {
                Intrinsics.checkNotNullParameter(locations, "locations");
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SmaBaseBleService$bleHandleCallback$2$1$onReadLocation$1(this.f3915a, locations, null), 2, null);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadLoveTapUser(@NotNull List<BleLoveTapUser> list) {
                BleHandleCallback.DefaultImpls.onReadLoveTapUser(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadMatchRecord(@NotNull List<BleMatchRecord> list) {
                BleHandleCallback.DefaultImpls.onReadMatchRecord(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadMatchRecord2(@NotNull List<BleMatchRecord2> list) {
                BleHandleCallback.DefaultImpls.onReadMatchRecord2(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadMedicationAlarm(@NotNull List<BleMedicationAlarm> list) {
                BleHandleCallback.DefaultImpls.onReadMedicationAlarm(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadMedicationReminder(@NotNull List<BleMedicationReminder> list) {
                BleHandleCallback.DefaultImpls.onReadMedicationReminder(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadMindStatus(@NotNull List<BleMindStatus> list) {
                BleHandleCallback.DefaultImpls.onReadMindStatus(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadMtkOtaMeta() {
                BleHandleCallback.DefaultImpls.onReadMtkOtaMeta(this);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadNoDisturb(@NotNull BleNoDisturbSettings noDisturbSettings) {
                Intrinsics.checkNotNullParameter(noDisturbSettings, "noDisturbSettings");
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadNoDisturb called -> " + noDisturbSettings);
                if (this.f3915a.getKhCurrentCommand() != null) {
                    SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    if (khCurrentCommand.getKey() != null) {
                        SmaBaseReq khCurrentCommand2 = this.f3915a.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand2);
                        if (khCurrentCommand2.getKey() == BleKey.NO_DISTURB_RANGE) {
                            SmaBaseRes smaBaseRes = new SmaBaseRes();
                            SmaBaseReq khCurrentCommand3 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand3);
                            smaBaseRes.setBaseReq(khCurrentCommand3);
                            smaBaseRes.setObj(noDisturbSettings);
                            SmaBaseReq khCurrentCommand4 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand4);
                            SmaResponseListener responseListener = khCurrentCommand4.getResponseListener();
                            Intrinsics.checkNotNull(responseListener);
                            responseListener.onResponse(smaBaseRes);
                        }
                    }
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadPackageStatus(@NotNull BlePackageStatus blePackageStatus) {
                BleHandleCallback.DefaultImpls.onReadPackageStatus(this, blePackageStatus);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadPower(int i) {
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadPower called -> " + i);
                if (this.f3915a.getKhCurrentCommand() != null) {
                    SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    if (khCurrentCommand.getKey() != null) {
                        SmaBaseReq khCurrentCommand2 = this.f3915a.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand2);
                        if (khCurrentCommand2.getKey() == BleKey.POWER) {
                            SmaBaseRes smaBaseRes = new SmaBaseRes();
                            SmaBaseReq khCurrentCommand3 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand3);
                            smaBaseRes.setBaseReq(khCurrentCommand3);
                            smaBaseRes.setObj(Integer.valueOf(i));
                            SmaBaseReq khCurrentCommand4 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand4);
                            SmaResponseListener responseListener = khCurrentCommand4.getResponseListener();
                            Intrinsics.checkNotNull(responseListener);
                            responseListener.onResponse(smaBaseRes);
                        }
                    }
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadPressure(@NotNull List<BlePressure> pressures) {
                Intrinsics.checkNotNullParameter(pressures, "pressures");
                BleHandleCallback.DefaultImpls.onReadPressure(this, pressures);
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadPressure called -> " + pressures);
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SmaBaseBleService$bleHandleCallback$2$1$onReadPressure$1(pressures, this.f3915a, null), 2, null);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadSedentariness(@NotNull BleSedentarinessSettings sedentarinessSettings) {
                Intrinsics.checkNotNullParameter(sedentarinessSettings, "sedentarinessSettings");
                BleHandleCallback.DefaultImpls.onReadSedentariness(this, sedentarinessSettings);
                if (this.f3915a.getKhCurrentCommand() != null) {
                    SmaBaseRes smaBaseRes = new SmaBaseRes();
                    smaBaseRes.setObj(sedentarinessSettings);
                    SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    smaBaseRes.setBaseReq(khCurrentCommand);
                    SmaBaseReq khCurrentCommand2 = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand2);
                    SmaResponseListener responseListener = khCurrentCommand2.getResponseListener();
                    Intrinsics.checkNotNull(responseListener);
                    responseListener.onResponse(smaBaseRes);
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadSleep(@NotNull List<BleSleep> sleeps) {
                Intrinsics.checkNotNullParameter(sleeps, "sleeps");
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadSleep called -> " + sleeps);
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SmaBaseBleService$bleHandleCallback$2$1$onReadSleep$1(sleeps, this.f3915a, null), 2, null);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadSleepGoal(int i) {
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadSleepGoal called -> " + i);
                if (this.f3915a.getKhCurrentCommand() != null) {
                    SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    if (khCurrentCommand.getKey() != null) {
                        SmaBaseReq khCurrentCommand2 = this.f3915a.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand2);
                        if (khCurrentCommand2.getKey() == BleKey.SLEEP_GOAL) {
                            SmaBaseRes smaBaseRes = new SmaBaseRes();
                            SmaBaseReq khCurrentCommand3 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand3);
                            smaBaseRes.setBaseReq(khCurrentCommand3);
                            smaBaseRes.setObj(Integer.valueOf(i));
                            SmaBaseReq khCurrentCommand4 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand4);
                            SmaResponseListener responseListener = khCurrentCommand4.getResponseListener();
                            Intrinsics.checkNotNull(responseListener);
                            responseListener.onResponse(smaBaseRes);
                        }
                    }
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadSleepQuality(@NotNull BleSleepQuality bleSleepQuality) {
                BleHandleCallback.DefaultImpls.onReadSleepQuality(this, bleSleepQuality);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadSleepRaw(@NotNull byte[] bArr) {
                BleHandleCallback.DefaultImpls.onReadSleepRaw(this, bArr);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadStepGoal(int i) {
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadStepGoal called -> " + i);
                if (this.f3915a.getKhCurrentCommand() != null) {
                    SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    if (khCurrentCommand.getKey() != null) {
                        SmaBaseReq khCurrentCommand2 = this.f3915a.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand2);
                        if (khCurrentCommand2.getKey() == BleKey.STEP_GOAL) {
                            SmaBaseRes smaBaseRes = new SmaBaseRes();
                            SmaBaseReq khCurrentCommand3 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand3);
                            smaBaseRes.setBaseReq(khCurrentCommand3);
                            smaBaseRes.setObj(Integer.valueOf(i));
                            SmaBaseReq khCurrentCommand4 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand4);
                            SmaResponseListener responseListener = khCurrentCommand4.getResponseListener();
                            Intrinsics.checkNotNull(responseListener);
                            responseListener.onResponse(smaBaseRes);
                        }
                    }
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadStock(@NotNull List<BleStock> list) {
                BleHandleCallback.DefaultImpls.onReadStock(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadTemperature(@NotNull List<BleTemperature> temperatures) {
                Intrinsics.checkNotNullParameter(temperatures, "temperatures");
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadTemperature called -> " + temperatures);
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SmaBaseBleService$bleHandleCallback$2$1$onReadTemperature$1(temperatures, this.f3915a, null), 2, null);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadTemperatureUnit(int i) {
                BleHandleCallback.DefaultImpls.onReadTemperatureUnit(this, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadTuyaKey(@NotNull BleTuyaKey bleTuyaKey) {
                BleHandleCallback.DefaultImpls.onReadTuyaKey(this, bleTuyaKey);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadUiPackVersion(@NotNull String str) {
                BleHandleCallback.DefaultImpls.onReadUiPackVersion(this, str);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadUnit(int i) {
                BleHandleCallback.DefaultImpls.onReadUnit(this, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadUserProfile(@NotNull BleUserProfile userProfile) {
                Intrinsics.checkNotNullParameter(userProfile, "userProfile");
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadUserProfile called -> " + userProfile);
                if (this.f3915a.getKhCurrentCommand() != null) {
                    SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    if (khCurrentCommand.getKey() != null) {
                        SmaBaseReq khCurrentCommand2 = this.f3915a.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand2);
                        if (khCurrentCommand2.getKey() == BleKey.USER_PROFILE) {
                            SmaBaseRes smaBaseRes = new SmaBaseRes();
                            SmaBaseReq khCurrentCommand3 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand3);
                            smaBaseRes.setBaseReq(khCurrentCommand3);
                            smaBaseRes.setObj(userProfile);
                            SmaBaseReq khCurrentCommand4 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand4);
                            SmaResponseListener responseListener = khCurrentCommand4.getResponseListener();
                            Intrinsics.checkNotNull(responseListener);
                            responseListener.onResponse(smaBaseRes);
                        }
                    }
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadWatchFaceId(@NotNull BleWatchFaceId bleWatchFaceId) {
                BleHandleCallback.DefaultImpls.onReadWatchFaceId(this, bleWatchFaceId);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadWatchFaceSwitch(int i) {
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadWatchFaceSwitch called -> " + i);
                if (this.f3915a.getKhCurrentCommand() != null) {
                    SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    if (khCurrentCommand.getKey() != null) {
                        SmaBaseReq khCurrentCommand2 = this.f3915a.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand2);
                        if (khCurrentCommand2.getKey() == BleKey.WATCH_FACE_SWITCH) {
                            SmaBaseRes smaBaseRes = new SmaBaseRes();
                            SmaBaseReq khCurrentCommand3 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand3);
                            smaBaseRes.setBaseReq(khCurrentCommand3);
                            smaBaseRes.setObj(Integer.valueOf(i));
                            SmaBaseReq khCurrentCommand4 = this.f3915a.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand4);
                            SmaResponseListener responseListener = khCurrentCommand4.getResponseListener();
                            Intrinsics.checkNotNull(responseListener);
                            responseListener.onResponse(smaBaseRes);
                        }
                    }
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadWeatherRealTime(boolean z) {
                BleHandleCallback.DefaultImpls.onReadWeatherRealTime(this, z);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadWorkout(@NotNull List<BleWorkout> workouts) {
                Intrinsics.checkNotNullParameter(workouts, "workouts");
                BleHandleCallback.DefaultImpls.onReadWorkout(this, workouts);
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadWorkout " + workouts);
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SmaBaseBleService$bleHandleCallback$2$1$onReadWorkout$1(this.f3915a, workouts, null), 2, null);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadWorkout2(@NotNull List<BleWorkout2> workouts) {
                Intrinsics.checkNotNullParameter(workouts, "workouts");
                BleHandleCallback.DefaultImpls.onReadWorkout2(this, workouts);
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadWorkout2 " + workouts);
                kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new SmaBaseBleService$bleHandleCallback$2$1$onReadWorkout2$1(this.f3915a, workouts, null), 2, null);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReadWorldClock(@NotNull List<BleWorldClock> clocks) {
                Intrinsics.checkNotNullParameter(clocks, "clocks");
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReadWorldClock " + clocks);
                if (this.f3915a.getKhCurrentCommand() != null) {
                    SmaBaseRes smaBaseRes = new SmaBaseRes();
                    smaBaseRes.setObj(clocks);
                    SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    smaBaseRes.setBaseReq(khCurrentCommand);
                    SmaBaseReq khCurrentCommand2 = this.f3915a.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand2);
                    SmaResponseListener responseListener = khCurrentCommand2.getResponseListener();
                    Intrinsics.checkNotNull(responseListener);
                    responseListener.onResponse(smaBaseRes);
                }
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onRealTimeMeasurement(@NotNull BleRealTimeMeasurement bleRealTimeMeasurement) {
                BleHandleCallback.DefaultImpls.onRealTimeMeasurement(this, bleRealTimeMeasurement);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReceiveGSensorMotion(@NotNull List<BleGSensorMotion> list) {
                BleHandleCallback.DefaultImpls.onReceiveGSensorMotion(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReceiveGSensorRaw(@NotNull List<BleGSensorRaw> list) {
                BleHandleCallback.DefaultImpls.onReceiveGSensorRaw(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReceiveHRRaw(@NotNull List<BleHRRaw> list) {
                BleHandleCallback.DefaultImpls.onReceiveHRRaw(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReceiveLocationGga(@NotNull BleLocationGga bleLocationGga) {
                BleHandleCallback.DefaultImpls.onReceiveLocationGga(this, bleLocationGga);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReceiveLocationGsv(@NotNull List<BleLocationGsv> list) {
                BleHandleCallback.DefaultImpls.onReceiveLocationGsv(this, list);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReceiveMusicCommand(@NotNull MusicCommand musicCommand) {
                Intrinsics.checkNotNullParameter(musicCommand, "musicCommand");
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onReceiveMusicCommand called -> " + musicCommand);
                LiveMusicControlRes liveMusicControlRes = new LiveMusicControlRes(MusicControlState.UNKNOWN);
                switch (WhenMappings.$EnumSwitchMapping$0[musicCommand.ordinal()]) {
                    case 1:
                        liveMusicControlRes = new LiveMusicControlRes(MusicControlState.TOGGLE);
                        break;
                    case 2:
                        liveMusicControlRes = new LiveMusicControlRes(MusicControlState.PLAY);
                        break;
                    case 3:
                        liveMusicControlRes = new LiveMusicControlRes(MusicControlState.PAUSE);
                        break;
                    case 4:
                        liveMusicControlRes = new LiveMusicControlRes(MusicControlState.NEXT);
                        break;
                    case 5:
                        liveMusicControlRes = new LiveMusicControlRes(MusicControlState.PREVIOUS);
                        break;
                    case 6:
                        liveMusicControlRes = new LiveMusicControlRes(MusicControlState.VOLUME_UP);
                        break;
                    case 7:
                        liveMusicControlRes = new LiveMusicControlRes(MusicControlState.VOLUME_DOWN);
                        break;
                }
                Intent intent = new Intent(Constants.MUSIC_CONTROL_BROADCAST_INTENT);
                intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, liveMusicControlRes);
                LocalBroadcastManager.getInstance(this.f3915a).sendBroadcast(intent);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReceiveRealtimeLog(@NotNull BleRealtimeLog bleRealtimeLog) {
                BleHandleCallback.DefaultImpls.onReceiveRealtimeLog(this, bleRealtimeLog);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onReceiveRecordPacket(@NotNull BleRecordPacket bleRecordPacket) {
                BleHandleCallback.DefaultImpls.onReceiveRecordPacket(this, bleRecordPacket);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onRequestAgpsPrerequisite() {
                BleHandleCallback.DefaultImpls.onRequestAgpsPrerequisite(this);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onRequestLocation(int i) {
                BleHandleCallback.DefaultImpls.onRequestLocation(this, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onSessionStateChange(boolean z) {
                this.f3915a.i = System.currentTimeMillis();
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onSessionStateChange called -> " + z);
                if (z) {
                    this.f3915a.updateConnectionState(CloveSmaBleState.BleState.CONNECTED, true);
                    BleConnector bleConnector = BleConnector.INSTANCE;
                    if (bleConnector.isAvailable()) {
                        this.f3915a.h = null;
                        LogHelper.d(this.f3915a.getTAG(), "onSessionStateChange writing time zone to band...");
                        BleKey bleKey = BleKey.TIME_ZONE;
                        BleKeyFlag bleKeyFlag = BleKeyFlag.UPDATE;
                        BleConnector.sendObject$default(bleConnector, bleKey, bleKeyFlag, new BleTimeZone(), false, false, 24, null);
                        LogHelper.d(this.f3915a.getTAG(), "onSessionStateChange writing local time to band...");
                        BleConnector.sendObject$default(bleConnector, BleKey.TIME, bleKeyFlag, BleTime.Companion.local(), false, false, 24, null);
                        return;
                    }
                    return;
                }
                this.f3915a.setMBluetoothDevice(null);
                this.f3915a.h = new ConnectionError(133, System.currentTimeMillis());
                this.f3915a.updateConnectionState(CloveSmaBleState.BleState.DISCONNECTED, true);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onStockDelete(int i) {
                BleHandleCallback.DefaultImpls.onStockDelete(this, i);
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onStreamProgress(boolean z, int i, int i2, int i3) {
                Handler handler;
                String tag = this.f3915a.getTAG();
                LogHelper.d(tag, "onStreamProgress called status:" + z + ", errorCode: " + i + ", total: " + i2 + ", completed: " + i3);
                final SmaBaseRes smaBaseRes = new SmaBaseRes();
                SmaBaseReq khCurrentCommand = this.f3915a.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                smaBaseRes.setBaseReq(khCurrentCommand);
                smaBaseRes.setObj(new StreamProgressResponse(z, i, i2, i3));
                handler = this.f3915a.d;
                final SmaBaseBleService smaBaseBleService = this.f3915a;
                handler.post(
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0058: INVOKE  
                      (r4v2 'handler' android.os.Handler)
                      (wrap: java.lang.Runnable : 0x0055: CONSTRUCTOR  (r6v1 java.lang.Runnable A[REMOVE]) = 
                      (r5v1 'smaBaseBleService' com.coveiot.android.bleabstract.services.SmaBaseBleService A[DONT_INLINE])
                      (r0v2 'smaBaseRes' com.coveiot.android.smasdk.api.SmaBaseRes A[DONT_INLINE])
                     call: com.coveiot.android.bleabstract.services.b4.<init>(com.coveiot.android.bleabstract.services.SmaBaseBleService, com.coveiot.android.smasdk.api.SmaBaseRes):void type: CONSTRUCTOR)
                     type: VIRTUAL call: android.os.Handler.post(java.lang.Runnable):boolean in method: com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2.1.onStreamProgress(boolean, int, int, int):void, file: classes2.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:296)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:275)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:377)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:272)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.coveiot.android.bleabstract.services.b4, state: NOT_LOADED
                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:302)
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:769)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1097)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                    	... 15 more
                    */
                /*
                    this = this;
                    com.coveiot.android.bleabstract.services.SmaBaseBleService r0 = r3.f3915a
                    java.lang.String r0 = r0.getTAG()
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    java.lang.String r2 = "onStreamProgress called status:"
                    r1.append(r2)
                    r1.append(r4)
                    java.lang.String r2 = ", errorCode: "
                    r1.append(r2)
                    r1.append(r5)
                    java.lang.String r2 = ", total: "
                    r1.append(r2)
                    r1.append(r6)
                    java.lang.String r2 = ", completed: "
                    r1.append(r2)
                    r1.append(r7)
                    java.lang.String r1 = r1.toString()
                    com.coveiot.utils.utility.LogHelper.d(r0, r1)
                    com.coveiot.android.smasdk.api.SmaBaseRes r0 = new com.coveiot.android.smasdk.api.SmaBaseRes
                    r0.<init>()
                    com.coveiot.android.bleabstract.services.SmaBaseBleService r1 = r3.f3915a
                    com.coveiot.android.smasdk.api.SmaBaseReq r1 = r1.getKhCurrentCommand()
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                    r0.setBaseReq(r1)
                    com.coveiot.android.bleabstract.response.StreamProgressResponse r1 = new com.coveiot.android.bleabstract.response.StreamProgressResponse
                    r1.<init>(r4, r5, r6, r7)
                    r0.setObj(r1)
                    com.coveiot.android.bleabstract.services.SmaBaseBleService r4 = r3.f3915a
                    android.os.Handler r4 = com.coveiot.android.bleabstract.services.SmaBaseBleService.access$getMStreamProgressHandler$p(r4)
                    com.coveiot.android.bleabstract.services.SmaBaseBleService r5 = r3.f3915a
                    com.coveiot.android.bleabstract.services.b4 r6 = new com.coveiot.android.bleabstract.services.b4
                    r6.<init>(r5, r0)
                    r4.post(r6)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2.AnonymousClass1.onStreamProgress(boolean, int, int, int):void");
            }

            @Override // com.szabh.smable3.component.BleHandleCallback
            public void onSyncData(final int i, @NotNull final BleKey bleKey) {
                Handler handler;
                Intrinsics.checkNotNullParameter(bleKey, "bleKey");
                handler = this.f3915a.c;
                if (handler != null) {
                    final SmaBaseBleService smaBaseBleService = this.f3915a;
                    handler.postDelayed(
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0016: INVOKE  
                          (r0v2 'handler' android.os.Handler)
                          (wrap: java.lang.Runnable : 0x0011: CONSTRUCTOR  (r2v0 java.lang.Runnable A[REMOVE]) = 
                          (r1v0 'smaBaseBleService' com.coveiot.android.bleabstract.services.SmaBaseBleService A[DONT_INLINE])
                          (r4v0 'i' int A[DONT_INLINE])
                          (r5v0 'bleKey' com.szabh.smable3.BleKey A[DONT_INLINE])
                         call: com.coveiot.android.bleabstract.services.a4.<init>(com.coveiot.android.bleabstract.services.SmaBaseBleService, int, com.szabh.smable3.BleKey):void type: CONSTRUCTOR)
                          (500 long)
                         type: VIRTUAL call: android.os.Handler.postDelayed(java.lang.Runnable, long):boolean in method: com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2.1.onSyncData(int, com.szabh.smable3.BleKey):void, file: classes2.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:296)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:275)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:377)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:272)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.coveiot.android.bleabstract.services.a4, state: NOT_LOADED
                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:302)
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:769)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1097)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                        	... 23 more
                        */
                    /*
                        this = this;
                        java.lang.String r0 = "bleKey"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                        com.coveiot.android.bleabstract.services.SmaBaseBleService r0 = r3.f3915a
                        android.os.Handler r0 = com.coveiot.android.bleabstract.services.SmaBaseBleService.access$getMSyncCompletedHandler$p(r0)
                        if (r0 == 0) goto L19
                        com.coveiot.android.bleabstract.services.SmaBaseBleService r1 = r3.f3915a
                        com.coveiot.android.bleabstract.services.a4 r2 = new com.coveiot.android.bleabstract.services.a4
                        r2.<init>(r1, r4, r5)
                        r4 = 500(0x1f4, double:2.47E-321)
                        r0.postDelayed(r2, r4)
                    L19:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2.AnonymousClass1.onSyncData(int, com.szabh.smable3.BleKey):void");
                }

                @Override // com.szabh.smable3.component.BleHandleCallback
                public void onUpdateAppSportState(@NotNull BleAppSportState bleAppSportState) {
                    BleHandleCallback.DefaultImpls.onUpdateAppSportState(this, bleAppSportState);
                }

                @Override // com.szabh.smable3.component.BleHandleCallback
                public void onUpdateBAC(@NotNull BleBAC bleBAC) {
                    BleHandleCallback.DefaultImpls.onUpdateBAC(this, bleBAC);
                }

                @Override // com.szabh.smable3.component.BleHandleCallback
                public void onUpdateBloodPressure(@NotNull BleBloodPressure bleBloodPressure) {
                    BleHandleCallback.DefaultImpls.onUpdateBloodPressure(this, bleBloodPressure);
                }

                @Override // com.szabh.smable3.component.BleHandleCallback
                public void onUpdateHeartRate(@NotNull BleHeartRate bleHeartRate) {
                    BleHandleCallback.DefaultImpls.onUpdateHeartRate(this, bleHeartRate);
                }

                @Override // com.szabh.smable3.component.BleHandleCallback
                public void onUpdateTemperature(@NotNull BleTemperature bleTemperature) {
                    BleHandleCallback.DefaultImpls.onUpdateTemperature(this, bleTemperature);
                }

                @Override // com.szabh.smable3.component.BleHandleCallback
                public void onUpdateWatchFaceSwitch(boolean z) {
                    BleHandleCallback.DefaultImpls.onUpdateWatchFaceSwitch(this, z);
                }

                @Override // com.szabh.smable3.component.BleHandleCallback
                public void onVibrationUpdate(int i) {
                    BleHandleCallback.DefaultImpls.onVibrationUpdate(this, i);
                }

                @Override // com.szabh.smable3.component.BleHandleCallback
                public void onWatchFaceIdUpdate(boolean z) {
                    BleHandleCallback.DefaultImpls.onWatchFaceIdUpdate(this, z);
                }

                @Override // com.szabh.smable3.component.BleHandleCallback
                public void onWorldClockDelete(int i) {
                    BleHandleCallback.DefaultImpls.onWorldClockDelete(this, i);
                }

                @Override // com.szabh.smable3.component.BleHandleCallback
                public void onXModem(byte b) {
                    BleHandleCallback.DefaultImpls.onXModem(this, b);
                }

                @Override // com.szabh.smable3.component.BleHandleCallback
                public void onReadStock(boolean z) {
                    BleHandleCallback.DefaultImpls.onReadStock(this, z);
                }

                public static final void a(SmaBaseBleService this$0, SmaBaseRes baseRes) {
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    Intrinsics.checkNotNullParameter(baseRes, "$baseRes");
                    SmaBaseReq khCurrentCommand = this$0.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    SmaResponseListener responseListener = khCurrentCommand.getResponseListener();
                    Intrinsics.checkNotNull(responseListener);
                    responseListener.onResponse(baseRes);
                }
            }

            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public AnonymousClass1 invoke() {
                return new AnonymousClass1(SmaBaseBleService.this);
            }
        });

        /* loaded from: classes2.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[BleKey.values().length];
                try {
                    iArr[BleKey.POWER.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[BleKey.FIRMWARE_VERSION.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[BleKey.BLE_ADDRESS.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[BleKey.USER_PROFILE.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[BleKey.SLEEP_GOAL.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[BleKey.STEP_GOAL.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[BleKey.DISTANCE_GOAL.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    iArr[BleKey.CALORIES_GOAL.ordinal()] = 8;
                } catch (NoSuchFieldError unused8) {
                }
                try {
                    iArr[BleKey.SEDENTARINESS.ordinal()] = 9;
                } catch (NoSuchFieldError unused9) {
                }
                try {
                    iArr[BleKey.HOUR_SYSTEM.ordinal()] = 10;
                } catch (NoSuchFieldError unused10) {
                }
                try {
                    iArr[BleKey.BACK_LIGHT.ordinal()] = 11;
                } catch (NoSuchFieldError unused11) {
                }
                try {
                    iArr[BleKey.HR_MONITORING.ordinal()] = 12;
                } catch (NoSuchFieldError unused12) {
                }
                try {
                    iArr[BleKey.TEMPERATURE_DETECTING.ordinal()] = 13;
                } catch (NoSuchFieldError unused13) {
                }
                try {
                    iArr[BleKey.ACTIVITY_REALTIME.ordinal()] = 14;
                } catch (NoSuchFieldError unused14) {
                }
                try {
                    iArr[BleKey.ACTIVITY.ordinal()] = 15;
                } catch (NoSuchFieldError unused15) {
                }
                try {
                    iArr[BleKey.HEART_RATE.ordinal()] = 16;
                } catch (NoSuchFieldError unused16) {
                }
                try {
                    iArr[BleKey.BLOOD_PRESSURE.ordinal()] = 17;
                } catch (NoSuchFieldError unused17) {
                }
                try {
                    iArr[BleKey.SLEEP.ordinal()] = 18;
                } catch (NoSuchFieldError unused18) {
                }
                try {
                    iArr[BleKey.WORKOUT.ordinal()] = 19;
                } catch (NoSuchFieldError unused19) {
                }
                try {
                    iArr[BleKey.WORKOUT2.ordinal()] = 20;
                } catch (NoSuchFieldError unused20) {
                }
                try {
                    iArr[BleKey.LOCATION.ordinal()] = 21;
                } catch (NoSuchFieldError unused21) {
                }
                try {
                    iArr[BleKey.TEMPERATURE.ordinal()] = 22;
                } catch (NoSuchFieldError unused22) {
                }
                try {
                    iArr[BleKey.BLOOD_OXYGEN.ordinal()] = 23;
                } catch (NoSuchFieldError unused23) {
                }
                try {
                    iArr[BleKey.PRESSURE.ordinal()] = 24;
                } catch (NoSuchFieldError unused24) {
                }
                try {
                    iArr[BleKey.ALARM.ordinal()] = 25;
                } catch (NoSuchFieldError unused25) {
                }
                try {
                    iArr[BleKey.WORLD_CLOCK.ordinal()] = 26;
                } catch (NoSuchFieldError unused26) {
                }
                try {
                    iArr[BleKey.NOTIFICATION.ordinal()] = 27;
                } catch (NoSuchFieldError unused27) {
                }
                try {
                    iArr[BleKey.GESTURE_WAKE.ordinal()] = 28;
                } catch (NoSuchFieldError unused28) {
                }
                try {
                    iArr[BleKey.OTA.ordinal()] = 29;
                } catch (NoSuchFieldError unused29) {
                }
                try {
                    iArr[BleKey.WEATHER_REALTIME.ordinal()] = 30;
                } catch (NoSuchFieldError unused30) {
                }
                try {
                    iArr[BleKey.WEATHER_REALTIME2.ordinal()] = 31;
                } catch (NoSuchFieldError unused31) {
                }
                try {
                    iArr[BleKey.NO_DISTURB_RANGE.ordinal()] = 32;
                } catch (NoSuchFieldError unused32) {
                }
                try {
                    iArr[BleKey.CONTACT.ordinal()] = 33;
                } catch (NoSuchFieldError unused33) {
                }
                try {
                    iArr[BleKey.SCHEDULE.ordinal()] = 34;
                } catch (NoSuchFieldError unused34) {
                }
                try {
                    iArr[BleKey.WATCH_FACE.ordinal()] = 35;
                } catch (NoSuchFieldError unused35) {
                }
                try {
                    iArr[BleKey.CAMERA.ordinal()] = 36;
                } catch (NoSuchFieldError unused36) {
                }
                try {
                    iArr[BleKey.TEMPERATURE_UNIT.ordinal()] = 37;
                } catch (NoSuchFieldError unused37) {
                }
                try {
                    iArr[BleKey.GIRL_CARE.ordinal()] = 38;
                } catch (NoSuchFieldError unused38) {
                }
                try {
                    iArr[BleKey.SLEEP_QUALITY.ordinal()] = 39;
                } catch (NoSuchFieldError unused39) {
                }
                try {
                    iArr[BleKey.WATCH_FACE_SWITCH.ordinal()] = 40;
                } catch (NoSuchFieldError unused40) {
                }
                try {
                    iArr[BleKey.MUSIC_CONTROL.ordinal()] = 41;
                } catch (NoSuchFieldError unused41) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public static final void access$updateAlarmToAppLayer(SmaBaseBleService smaBaseBleService, BleAlarm bleAlarm) {
            smaBaseBleService.getClass();
            Set indices$default = BleRepeat.toIndices$default(BleRepeat.INSTANCE, bleAlarm.getMRepeat(), null, 2, null);
            Alarm alarm = new Alarm(bleAlarm.getMEnabled() == 1, bleAlarm.getMId(), bleAlarm.getMHour(), bleAlarm.getMMinute(), 0, bleAlarm.getMTag(), bleAlarm.getMRepeat() != 0, indices$default.contains(6), indices$default.contains(0), indices$default.contains(1), indices$default.contains(2), indices$default.contains(3), indices$default.contains(4), indices$default.contains(5), 16, null);
            Intent intent = new Intent(Constants.DEVICE_SETTINGS_BROADCAST_INTENT);
            intent.putExtra(Constants.ALARM_SETTINGS_BROADCAST_INTENT_EXTRA, alarm);
            LocalBroadcastManager.getInstance(smaBaseBleService).sendBroadcast(intent);
        }

        public static final void access$updateCallAcceptRejectToAppLayer(SmaBaseBleService smaBaseBleService, int i) {
            smaBaseBleService.getClass();
            Intent intent = new Intent(Constants.CALL_REJECT_BROADCAST_INTENT);
            intent.putExtra(Constants.CALL_REJECT__BROADCAST_INTENT_EXTRA, new CallRejectRes(i == 1));
            LocalBroadcastManager.getInstance(smaBaseBleService).sendBroadcast(intent);
        }

        public static final void access$updateDndToAppLayer(SmaBaseBleService smaBaseBleService, BleNoDisturbSettings bleNoDisturbSettings) {
            smaBaseBleService.getClass();
            if (bleNoDisturbSettings != null) {
                DNDData dNDData = new DNDData(false, 0, 0, 0, 0, 31, null);
                dNDData.setDNDEnabled(bleNoDisturbSettings.getMEnabled() == 1);
                if (bleNoDisturbSettings.getMBleTimeRange1() != null) {
                    dNDData = new DNDData(bleNoDisturbSettings.getMBleTimeRange1().getMEnabled() == 1, bleNoDisturbSettings.getMBleTimeRange1().getMStartHour(), bleNoDisturbSettings.getMBleTimeRange1().getMStartMinute(), bleNoDisturbSettings.getMBleTimeRange1().getMEndHour(), bleNoDisturbSettings.getMBleTimeRange1().getMEndMinute());
                }
                Intent intent = new Intent(Constants.DEVICE_SETTINGS_BROADCAST_INTENT);
                intent.putExtra(Constants.DND_SETTINGS_BROADCAST_INTENT_EXTRA, dNDData);
                LocalBroadcastManager.getInstance(smaBaseBleService).sendBroadcast(intent);
            }
        }

        public static final void access$updateWakeGestureToAppLayer(SmaBaseBleService smaBaseBleService, BleGestureWake bleGestureWake) {
            smaBaseBleService.getClass();
            if ((bleGestureWake != null ? bleGestureWake.getMBleTimeRange() : null) != null) {
                GetLiftWristResponse getLiftWristResponse = new GetLiftWristResponse();
                BleTimeRange mBleTimeRange = bleGestureWake != null ? bleGestureWake.getMBleTimeRange() : null;
                Intrinsics.checkNotNull(mBleTimeRange);
                getLiftWristResponse.setLiftWristEnabled(mBleTimeRange.getMEnabled() == 1);
                BleTimeRange mBleTimeRange2 = bleGestureWake != null ? bleGestureWake.getMBleTimeRange() : null;
                Intrinsics.checkNotNull(mBleTimeRange2);
                getLiftWristResponse.setStartHour(mBleTimeRange2.getMStartHour());
                BleTimeRange mBleTimeRange3 = bleGestureWake != null ? bleGestureWake.getMBleTimeRange() : null;
                Intrinsics.checkNotNull(mBleTimeRange3);
                getLiftWristResponse.setStartMinute(mBleTimeRange3.getMStartMinute());
                BleTimeRange mBleTimeRange4 = bleGestureWake != null ? bleGestureWake.getMBleTimeRange() : null;
                Intrinsics.checkNotNull(mBleTimeRange4);
                getLiftWristResponse.setEndHour(mBleTimeRange4.getMEndHour());
                BleTimeRange mBleTimeRange5 = bleGestureWake != null ? bleGestureWake.getMBleTimeRange() : null;
                Intrinsics.checkNotNull(mBleTimeRange5);
                getLiftWristResponse.setEndMinute(mBleTimeRange5.getMEndMinute());
                Intent intent = new Intent(Constants.DEVICE_SETTINGS_BROADCAST_INTENT);
                intent.putExtra(Constants.LIFT_WRIST_TO_VIEW_SETTINGS_BROADCAST_INTENT_EXTRA, getLiftWristResponse);
                LocalBroadcastManager.getInstance(smaBaseBleService).sendBroadcast(intent);
            }
        }

        public final void a() {
            updateConnectionState(CloveSmaBleState.BleState.CONNECTING, true);
            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                String str = this.j;
                LogHelper.i(str, "Obtained device Address is " + getMacAddress());
                try {
                    BleConnector bleConnector = BleConnector.INSTANCE;
                    bleConnector.closeConnection(true);
                    if (bleConnector.isHandlerCallbackExist(getBleHandleCallback())) {
                        bleConnector.removeHandleCallback(getBleHandleCallback());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    BleConnector.INSTANCE.addHandleCallback(getBleHandleCallback());
                } catch (UnsupportedOperationException e2) {
                    e2.printStackTrace();
                }
                BleConnector.INSTANCE.setAddress(getMacAddress()).connect(true);
            }
        }

        public final void connect(@NotNull String deviceAddress) {
            Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
            com.coveiot.sdk.ble.model.BleDeviceInfo.clearInstance();
            PreferenceManagerSma.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress(deviceAddress);
            PreferenceManagerSma.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
            String connectedDeviceMacAddress = PreferenceManagerSma.getInstance(getApplicationContext()).getConnectedDeviceMacAddress();
            Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(applicationC…connectedDeviceMacAddress");
            setMacAddress(connectedDeviceMacAddress);
            String str = this.j;
            LogHelper.d(str, "connection type ++ " + BleUtils.getConnectionType(this));
            a();
        }

        public final void createForegroundNotification() {
            Notification build;
            try {
                NotificationInfo data = BleApiUtils.getData();
                if (data.getAppColor() == 0) {
                    BleApiManager.getInstance(this);
                    data = BleApiUtils.getData();
                    if (data.getAppColor() == 0) {
                        BleApiUtils.getMetadata(this);
                        data = BleApiUtils.getData();
                    }
                }
                PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(data.getAppLauncherActivity()), 67108864);
                int i = Build.VERSION.SDK_INT;
                if (i >= 26) {
                    Object systemService = getSystemService("notification");
                    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
                    NotificationChannel notificationChannel = new NotificationChannel("101", data.getAppDesc(), 2);
                    notificationChannel.enableLights(false);
                    ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
                    build = new Notification.Builder(this, "101").setContentTitle(data.getAppDesc()).setColor(ContextCompat.getColor(this, data.getAppColor())).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
                    Intrinsics.checkNotNullExpressionValue(build, "{\n                val no…   .build()\n            }");
                } else {
                    if (i >= 21) {
                        build = new Notification.Builder(this).setContentTitle(data.getAppDesc()).setSmallIcon(data.getAppIcon()).setColor(ContextCompat.getColor(this, data.getAppColor())).setContentIntent(activity).build();
                    } else {
                        build = new Notification.Builder(this).setContentTitle(data.getAppDesc()).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
                    }
                    Intrinsics.checkNotNullExpressionValue(build, "{\n                if (Bu…          }\n            }");
                }
                startForeground(101, build);
            } catch (Exception e) {
                e.printStackTrace();
                BleApiUtils.checkExceptionAndShowNotification(e, this);
            }
        }

        public void disconnectAndForget() {
            LogHelper.d(this.j, "disconnectAndForget called");
            PreferenceManagerSma.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress("");
            PreferenceManagerSma.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
            BleConnector.INSTANCE.unbind();
            stopForeground(true);
            stopSelf();
        }

        public void disconnectAndRetainMacAddress() {
            PreferenceManagerSma.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
            BleConnector.INSTANCE.closeConnection(true);
            stopForeground(true);
            stopSelf();
        }

        @NotNull
        public final BleHandleCallback getBleHandleCallback() {
            return (BleHandleCallback) this.k.getValue();
        }

        @Nullable
        public final ConnectionError getConnectionError() {
            return this.h;
        }

        public final long getConnectionStageChangeTimeStamp() {
            return this.i;
        }

        @Nullable
        public final CloveSmaBleState.BleState getConnectionState() {
            return this.e;
        }

        @Nullable
        public final SmaBaseReq getKhCurrentCommand() {
            return this.f;
        }

        @Nullable
        public final BluetoothDevice getMBluetoothDevice() {
            return this.f3913a;
        }

        @NotNull
        public final String getMacAddress() {
            String str = this.macAddress;
            if (str != null) {
                return str;
            }
            Intrinsics.throwUninitializedPropertyAccessException(DeviceKey.MacAddress);
            return null;
        }

        @NotNull
        public final IBinder getServiceBinder() {
            IBinder iBinder = this.serviceBinder;
            if (iBinder != null) {
                return iBinder;
            }
            Intrinsics.throwUninitializedPropertyAccessException("serviceBinder");
            return null;
        }

        public final String getTAG() {
            return this.j;
        }

        public final boolean isRemoteCameraOpened() {
            return this.g;
        }

        @Override // android.app.Service
        @NotNull
        public IBinder onBind(@NotNull Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            return getServiceBinder();
        }

        @Override // android.app.Service
        public void onCreate() {
        }

        @Override // android.app.Service
        public void onDestroy() {
            try {
                BleConnector bleConnector = BleConnector.INSTANCE;
                if (bleConnector.isHandlerCallbackExist(getBleHandleCallback())) {
                    bleConnector.removeHandleCallback(getBleHandleCallback());
                }
                bleConnector.unbind();
            } catch (UnsupportedOperationException e) {
                e.printStackTrace();
            }
            this.f3913a = null;
        }

        @Override // android.app.Service
        public int onStartCommand(@Nullable Intent intent, int i, int i2) {
            if (intent == null) {
                try {
                    Intent intent2 = new Intent(this, SmaBaseBleService.class);
                    if (Build.VERSION.SDK_INT >= 26) {
                        startForegroundService(intent2);
                    } else {
                        startService(intent2);
                    }
                    return 3;
                } catch (Exception e) {
                    e.printStackTrace();
                    BleApiUtils.checkExceptionAndShowNotification(e, this);
                }
            }
            createForegroundNotification();
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
            new BleConnector.Builder(applicationContext).supportMtkOta(true).supportLauncher(false).supportFilterEmpty(false).build();
            String connectedDeviceMacAddress = PreferenceManagerSma.getInstance(getApplicationContext()).getConnectedDeviceMacAddress();
            Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(applicationC…connectedDeviceMacAddress");
            setMacAddress(connectedDeviceMacAddress);
            BleUtils.getConnectionType(getApplicationContext());
            if (!BleUtils.isEmpty(getMacAddress())) {
                a();
            }
            return 3;
        }

        @Override // android.app.Service
        public boolean onUnbind(@Nullable Intent intent) {
            return super.onUnbind(intent);
        }

        public void restartService() {
            PreferenceManagerSma.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
            BleConnector.INSTANCE.closeConnection(true);
            stopForeground(true);
            stopSelf();
        }

        public final void sendRequest(@NotNull final SmaBaseReq khBaseReq, @NotNull SmaResponseListener responseListener) {
            ArrayList<Element> watchFaceElementArray;
            Integer watchFaceResource;
            List<BleMusicControl> musicControls;
            Intrinsics.checkNotNullParameter(khBaseReq, "khBaseReq");
            Intrinsics.checkNotNullParameter(responseListener, "responseListener");
            khBaseReq.setResponseListener(responseListener);
            this.f = khBaseReq;
            StringBuilder sb = new StringBuilder();
            sb.append(" sendRequest-- ");
            SmaBaseReq smaBaseReq = this.f;
            Intrinsics.checkNotNull(smaBaseReq);
            sb.append(smaBaseReq.getKey().name());
            LogHelper.d(this.j + " khCurrentCommand", sb.toString());
            BleKey key = khBaseReq.getKey();
            if (key != null) {
                switch (WhenMappings.$EnumSwitchMapping$0[key.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                        BleConnector.sendData$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), null, false, false, 28, null);
                        return;
                    case 4:
                        if (khBaseReq.getKeyFlag() == BleKeyFlag.UPDATE) {
                            if (khBaseReq instanceof SmaSetUserProfileReq) {
                                SmaSetUserProfileReq smaSetUserProfileReq = (SmaSetUserProfileReq) khBaseReq;
                                if (smaSetUserProfileReq.getUserProfile() != null) {
                                    BleConnector.sendObject$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), smaSetUserProfileReq.getUserProfile(), false, false, 24, null);
                                    return;
                                }
                                return;
                            }
                            return;
                        } else if (khBaseReq.getKeyFlag() == BleKeyFlag.READ) {
                            BleConnector.sendData$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), null, false, false, 28, null);
                            return;
                        } else {
                            return;
                        }
                    case 5:
                        if (khBaseReq.getKeyFlag() == BleKeyFlag.UPDATE) {
                            if (khBaseReq instanceof SmaSleepGoalReq) {
                                SmaSleepGoalReq smaSleepGoalReq = (SmaSleepGoalReq) khBaseReq;
                                smaSleepGoalReq.getGoal();
                                BleConnector.sendInt32$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), smaSleepGoalReq.getGoal(), null, false, false, 56, null);
                                return;
                            }
                            return;
                        } else if (khBaseReq.getKeyFlag() == BleKeyFlag.READ) {
                            BleConnector.sendData$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), null, false, false, 28, null);
                            return;
                        } else {
                            return;
                        }
                    case 6:
                        if (khBaseReq.getKeyFlag() == BleKeyFlag.UPDATE) {
                            if (khBaseReq instanceof SmaStepGoalReq) {
                                SmaStepGoalReq smaStepGoalReq = (SmaStepGoalReq) khBaseReq;
                                smaStepGoalReq.getGoal();
                                BleConnector.sendInt32$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), smaStepGoalReq.getGoal(), null, false, false, 56, null);
                                return;
                            }
                            return;
                        } else if (khBaseReq.getKeyFlag() == BleKeyFlag.READ) {
                            BleConnector.sendData$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), null, false, false, 28, null);
                            return;
                        } else {
                            return;
                        }
                    case 7:
                        if (khBaseReq.getKeyFlag() == BleKeyFlag.UPDATE) {
                            if (khBaseReq instanceof SmaDistanceGoalReq) {
                                SmaDistanceGoalReq smaDistanceGoalReq = (SmaDistanceGoalReq) khBaseReq;
                                smaDistanceGoalReq.getGoal();
                                BleConnector.sendInt32$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), smaDistanceGoalReq.getGoal(), null, false, false, 56, null);
                                return;
                            }
                            return;
                        } else if (khBaseReq.getKeyFlag() == BleKeyFlag.READ) {
                            BleConnector.sendData$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), null, false, false, 28, null);
                            return;
                        } else {
                            return;
                        }
                    case 8:
                        if (khBaseReq.getKeyFlag() == BleKeyFlag.UPDATE) {
                            if (khBaseReq instanceof SmaCalorieGoalReq) {
                                SmaCalorieGoalReq smaCalorieGoalReq = (SmaCalorieGoalReq) khBaseReq;
                                smaCalorieGoalReq.getGoal();
                                BleConnector.sendInt32$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), smaCalorieGoalReq.getGoal(), null, false, false, 56, null);
                                return;
                            }
                            return;
                        } else if (khBaseReq.getKeyFlag() == BleKeyFlag.READ) {
                            BleConnector.sendData$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), null, false, false, 28, null);
                            return;
                        } else {
                            return;
                        }
                    case 9:
                        if (khBaseReq instanceof SmaSetSedentaryReminderReq) {
                            SmaSetSedentaryReminderReq smaSetSedentaryReminderReq = (SmaSetSedentaryReminderReq) khBaseReq;
                            if (smaSetSedentaryReminderReq.getSedentarinessSettings() != null) {
                                if (khBaseReq.getKeyFlag() == BleKeyFlag.UPDATE) {
                                    BleConnector.sendObject$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), smaSetSedentaryReminderReq.getSedentarinessSettings(), false, false, 24, null);
                                    return;
                                } else if (khBaseReq.getKeyFlag() == BleKeyFlag.READ) {
                                    BleConnector.sendData$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), null, false, false, 28, null);
                                    return;
                                } else {
                                    return;
                                }
                            }
                            return;
                        }
                        return;
                    case 10:
                        if (khBaseReq instanceof SmaSetHourSystemReq) {
                            SmaSetHourSystemReq smaSetHourSystemReq = (SmaSetHourSystemReq) khBaseReq;
                            smaSetHourSystemReq.is12HourFormat();
                            BleConnector.sendInt8$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), smaSetHourSystemReq.is12HourFormat() ? 1 : 0, false, false, 24, null);
                            return;
                        }
                        return;
                    case 11:
                        if (khBaseReq instanceof SmaScreenTimeOutReq) {
                            BleConnector.sendInt8$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), ((SmaScreenTimeOutReq) khBaseReq).getScreenTimeOut(), false, false, 24, null);
                            return;
                        }
                        return;
                    case 12:
                        if (khBaseReq instanceof SmaSetHrMonitoringReq) {
                            SmaSetHrMonitoringReq smaSetHrMonitoringReq = (SmaSetHrMonitoringReq) khBaseReq;
                            if (smaSetHrMonitoringReq.getHrMonitoringSettins() != null) {
                                BleConnector.sendObject$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), smaSetHrMonitoringReq.getHrMonitoringSettins(), false, false, 24, null);
                                return;
                            }
                            return;
                        }
                        return;
                    case 13:
                        if (khBaseReq instanceof SmaSetTemperatureDetectingReq) {
                            SmaSetTemperatureDetectingReq smaSetTemperatureDetectingReq = (SmaSetTemperatureDetectingReq) khBaseReq;
                            if (smaSetTemperatureDetectingReq.getBleTemperatureDetectingReq() != null) {
                                BleConnector.sendObject$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), smaSetTemperatureDetectingReq.getBleTemperatureDetectingReq(), false, false, 24, null);
                                return;
                            }
                            return;
                        }
                        return;
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                        BleConnector.sendData$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), null, false, false, 28, null);
                        return;
                    case 25:
                        if (khBaseReq instanceof SmaSetVibrationAlarmReq) {
                            SmaSetVibrationAlarmReq smaSetVibrationAlarmReq = (SmaSetVibrationAlarmReq) khBaseReq;
                            if (smaSetVibrationAlarmReq.getBleAlarm() != null) {
                                if (khBaseReq.getKeyFlag() != BleKeyFlag.CREATE && khBaseReq.getKeyFlag() != BleKeyFlag.UPDATE) {
                                    if (khBaseReq.getKeyFlag() == BleKeyFlag.DELETE) {
                                        BleConnector bleConnector = BleConnector.INSTANCE;
                                        BleKeyFlag keyFlag = khBaseReq.getKeyFlag();
                                        BleAlarm bleAlarm = smaSetVibrationAlarmReq.getBleAlarm();
                                        Intrinsics.checkNotNull(bleAlarm);
                                        BleConnector.sendInt8$default(bleConnector, key, keyFlag, bleAlarm.getMId(), false, false, 24, null);
                                        return;
                                    }
                                    return;
                                }
                                BleConnector.sendObject$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), smaSetVibrationAlarmReq.getBleAlarm(), false, false, 24, null);
                                return;
                            }
                        }
                        if ((khBaseReq instanceof SmaGetAlarmsReq) && khBaseReq.getKeyFlag() == BleKeyFlag.READ) {
                            BleConnector.sendInt8$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), 255, false, false, 24, null);
                            return;
                        }
                        return;
                    case 26:
                        if (khBaseReq instanceof SmaSetWorldClockReq) {
                            List<BleWorldClock> list$default = BleCache.getList$default(BleCache.INSTANCE, BleKey.WORLD_CLOCK, BleWorldClock.class, null, 4, null);
                            if (((list$default == null || list$default.isEmpty()) ? 1 : null) == null) {
                                for (BleWorldClock bleWorldClock : list$default) {
                                    BleConnector.sendInt8$default(BleConnector.INSTANCE, key, BleKeyFlag.DELETE, bleWorldClock.getMId(), false, false, 24, null);
                                }
                            }
                            SmaSetWorldClockReq smaSetWorldClockReq = (SmaSetWorldClockReq) khBaseReq;
                            List<BleWorldClock> bleWorldClocks = smaSetWorldClockReq.getBleWorldClocks();
                            if (!((bleWorldClocks == null || bleWorldClocks.isEmpty()) ? true : true)) {
                                List<BleWorldClock> bleWorldClocks2 = smaSetWorldClockReq.getBleWorldClocks();
                                Intrinsics.checkNotNull(bleWorldClocks2);
                                for (BleWorldClock bleWorldClock2 : bleWorldClocks2) {
                                    BleConnector.sendObject$default(BleConnector.INSTANCE, key, BleKeyFlag.CREATE, bleWorldClock2, false, false, 24, null);
                                }
                            }
                            getBleHandleCallback().onCommandReply(key, khBaseReq.getKeyFlag(), true);
                            return;
                        } else if (khBaseReq instanceof SmaGetWorldClockReq) {
                            BleConnector.sendInt8$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), 255, false, false, 24, null);
                            return;
                        } else {
                            return;
                        }
                    case 27:
                        if (khBaseReq instanceof SmaNotificationReq) {
                            SmaNotificationReq smaNotificationReq = (SmaNotificationReq) khBaseReq;
                            if (smaNotificationReq.getBleNotification() != null) {
                                BleConnector.sendObject$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), smaNotificationReq.getBleNotification(), false, false, 24, null);
                                return;
                            }
                        }
                        if (khBaseReq instanceof SmaStopNotificationReq) {
                            SmaStopNotificationReq smaStopNotificationReq = (SmaStopNotificationReq) khBaseReq;
                            if (smaStopNotificationReq.getBleNotification() != null) {
                                BleConnector bleConnector2 = BleConnector.INSTANCE;
                                BleKeyFlag bleKeyFlag = BleKeyFlag.DELETE;
                                BleConnector.sendObject$default(bleConnector2, key, bleKeyFlag, smaStopNotificationReq.getBleNotification(), false, false, 24, null);
                                getBleHandleCallback().onCommandReply(key, bleKeyFlag, true);
                                return;
                            }
                            return;
                        }
                        return;
                    case 28:
                        if (khBaseReq.getKeyFlag() == BleKeyFlag.UPDATE) {
                            if (khBaseReq instanceof SmaGestureWakeReq) {
                                SmaGestureWakeReq smaGestureWakeReq = (SmaGestureWakeReq) khBaseReq;
                                if (smaGestureWakeReq.getGestureWake() != null) {
                                    BleConnector.sendObject$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), smaGestureWakeReq.getGestureWake(), false, false, 24, null);
                                    return;
                                }
                                return;
                            }
                            return;
                        } else if (khBaseReq.getKeyFlag() == BleKeyFlag.READ) {
                            BleConnector.sendData$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), null, false, false, 28, null);
                            return;
                        } else {
                            return;
                        }
                    case 29:
                        if (khBaseReq instanceof SmaOtaReq) {
                            String mPlatform = BleCache.INSTANCE.getMPlatform();
                            int hashCode = mPlatform.hashCode();
                            if (hashCode != -1955887059) {
                                if (hashCode == 76676) {
                                    if (mPlatform.equals(BleDeviceInfo.PLATFORM_MTK)) {
                                        BleConnector.INSTANCE.read(BleConnector.SERVICE_MTK, BleConnector.CH_MTK_OTA_META);
                                        return;
                                    }
                                    return;
                                } else if (hashCode != 2138586828 || !mPlatform.equals(BleDeviceInfo.PLATFORM_GOODIX)) {
                                    return;
                                }
                            } else if (!mPlatform.equals(BleDeviceInfo.PLATFORM_NORDIC)) {
                                return;
                            }
                            BleConnector.sendData$default(BleConnector.INSTANCE, BleKey.OTA, BleKeyFlag.UPDATE, null, false, false, 28, null);
                            return;
                        }
                        return;
                    case 30:
                        if (khBaseReq instanceof SmaCurrentWeatherReq) {
                            BleKeyFlag keyFlag2 = khBaseReq.getKeyFlag();
                            BleKeyFlag bleKeyFlag2 = BleKeyFlag.UPDATE;
                            if (keyFlag2 == bleKeyFlag2) {
                                BleCache bleCache = BleCache.INSTANCE;
                                if (bleCache.getMDeviceInfo() != null) {
                                    BleDeviceInfo mDeviceInfo = bleCache.getMDeviceInfo();
                                    Intrinsics.checkNotNull(mDeviceInfo);
                                    if (mDeviceInfo.getMSupportTemperatureUnitSet() == 1) {
                                        BleConnector.sendInt8$default(BleConnector.INSTANCE, BleKey.TEMPERATURE_UNIT, khBaseReq.getKeyFlag(), ((SmaCurrentWeatherReq) khBaseReq).getTemperatureUnitType(), false, false, 24, null);
                                    }
                                }
                                BleConnector bleConnector3 = BleConnector.INSTANCE;
                                SmaCurrentWeatherReq smaCurrentWeatherReq = (SmaCurrentWeatherReq) khBaseReq;
                                BleConnector.sendObject$default(bleConnector3, khBaseReq.getKey(), bleKeyFlag2, smaCurrentWeatherReq.getWeather(), false, false, 24, null);
                                BleConnector.sendObject$default(bleConnector3, BleKey.WEATHER_FORECAST, bleKeyFlag2, smaCurrentWeatherReq.getForecastWeather(), false, false, 24, null);
                                getBleHandleCallback().onCommandReply(key, khBaseReq.getKeyFlag(), true);
                                return;
                            }
                            return;
                        }
                        return;
                    case 31:
                        if (khBaseReq instanceof SmaCurrentWeatherReq2) {
                            BleKeyFlag keyFlag3 = khBaseReq.getKeyFlag();
                            BleKeyFlag bleKeyFlag3 = BleKeyFlag.UPDATE;
                            if (keyFlag3 == bleKeyFlag3) {
                                BleCache bleCache2 = BleCache.INSTANCE;
                                if (bleCache2.getMDeviceInfo() != null) {
                                    BleDeviceInfo mDeviceInfo2 = bleCache2.getMDeviceInfo();
                                    Intrinsics.checkNotNull(mDeviceInfo2);
                                    if (mDeviceInfo2.getMSupportTemperatureUnitSet() == 1) {
                                        BleConnector.sendInt8$default(BleConnector.INSTANCE, BleKey.TEMPERATURE_UNIT, khBaseReq.getKeyFlag(), ((SmaCurrentWeatherReq2) khBaseReq).getTemperatureUnitType(), false, false, 24, null);
                                    }
                                }
                                BleConnector bleConnector4 = BleConnector.INSTANCE;
                                SmaCurrentWeatherReq2 smaCurrentWeatherReq2 = (SmaCurrentWeatherReq2) khBaseReq;
                                BleConnector.sendObject$default(bleConnector4, khBaseReq.getKey(), bleKeyFlag3, smaCurrentWeatherReq2.getWeather(), false, false, 24, null);
                                BleConnector.sendObject$default(bleConnector4, BleKey.WEATHER_FORECAST2, bleKeyFlag3, smaCurrentWeatherReq2.getForecastWeather(), false, false, 24, null);
                                getBleHandleCallback().onCommandReply(key, khBaseReq.getKeyFlag(), true);
                                return;
                            }
                            return;
                        }
                        return;
                    case 32:
                        if (khBaseReq.getKeyFlag() == BleKeyFlag.UPDATE) {
                            if (khBaseReq instanceof SmaSetDNDReq) {
                                SmaSetDNDReq smaSetDNDReq = (SmaSetDNDReq) khBaseReq;
                                if (smaSetDNDReq.getBleDndReq() != null) {
                                    BleConnector.sendObject$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), smaSetDNDReq.getBleDndReq(), false, false, 24, null);
                                    return;
                                }
                                return;
                            }
                            return;
                        } else if (khBaseReq.getKeyFlag() == BleKeyFlag.READ) {
                            BleConnector.sendData$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), null, false, false, 28, null);
                            return;
                        } else {
                            return;
                        }
                    case 33:
                        if (khBaseReq.getKeyFlag() == BleKeyFlag.UPDATE) {
                            if (khBaseReq instanceof SmaUploadContactReq) {
                                SmaUploadContactReq smaUploadContactReq = (SmaUploadContactReq) khBaseReq;
                                if (smaUploadContactReq.getContactBytes() != null) {
                                    BleConnector bleConnector5 = BleConnector.INSTANCE;
                                    byte[] contactBytes = smaUploadContactReq.getContactBytes();
                                    Intrinsics.checkNotNull(contactBytes);
                                    BleConnector.sendStream$default(bleConnector5, key, contactBytes, 0, 4, (Object) null);
                                    return;
                                }
                            }
                            if (khBaseReq instanceof SmaSyncContactsReq) {
                                SmaSyncContactsReq smaSyncContactsReq = (SmaSyncContactsReq) khBaseReq;
                                if (smaSyncContactsReq.getContactBytes() != null) {
                                    BleConnector bleConnector6 = BleConnector.INSTANCE;
                                    byte[] contactBytes2 = smaSyncContactsReq.getContactBytes();
                                    Intrinsics.checkNotNull(contactBytes2);
                                    BleConnector.sendStream$default(bleConnector6, key, contactBytes2, 0, 4, (Object) null);
                                    return;
                                }
                                return;
                            }
                            return;
                        } else if (khBaseReq.getKeyFlag() == BleKeyFlag.DELETE) {
                            BleConnector.sendData$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), null, false, false, 28, null);
                            SmaBaseRes smaBaseRes = new SmaBaseRes();
                            SmaBaseReq smaBaseReq2 = this.f;
                            Intrinsics.checkNotNull(smaBaseReq2);
                            smaBaseRes.setBaseReq(smaBaseReq2);
                            smaBaseRes.setObj(new StreamProgressResponse(true, 0, 1, 1));
                            SmaBaseReq smaBaseReq3 = this.f;
                            Intrinsics.checkNotNull(smaBaseReq3);
                            SmaResponseListener responseListener2 = smaBaseReq3.getResponseListener();
                            Intrinsics.checkNotNull(responseListener2);
                            responseListener2.onResponse(smaBaseRes);
                            return;
                        } else {
                            return;
                        }
                    case 34:
                        if (khBaseReq instanceof SmaSetScheduleReq) {
                            SmaSetScheduleReq smaSetScheduleReq = (SmaSetScheduleReq) khBaseReq;
                            if (smaSetScheduleReq.getBleSchedule() != null) {
                                if (khBaseReq.getKeyFlag() == BleKeyFlag.CREATE || khBaseReq.getKeyFlag() == BleKeyFlag.UPDATE) {
                                    BleConnector.sendObject$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), smaSetScheduleReq.getBleSchedule(), false, false, 24, null);
                                    return;
                                }
                                return;
                            }
                        }
                        if (khBaseReq instanceof SmaDeleteScheduleReq) {
                            BleConnector.sendInt8$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), ((SmaDeleteScheduleReq) khBaseReq).getScheduleId(), false, false, 24, null);
                            return;
                        }
                        return;
                    case 35:
                        if ((khBaseReq instanceof SmaUploadWatchFaceReq) && khBaseReq.getKeyFlag() == BleKeyFlag.UPDATE) {
                            SmaUploadWatchFaceReq smaUploadWatchFaceReq = (SmaUploadWatchFaceReq) khBaseReq;
                            if (smaUploadWatchFaceReq.getWatchFaceResource() != null && ((watchFaceResource = smaUploadWatchFaceReq.getWatchFaceResource()) == null || watchFaceResource.intValue() != 0)) {
                                ThreadsKt.thread$default(false, false, null, null, 0, new Function0<Unit>() { // from class: com.coveiot.android.bleabstract.services.SmaBaseBleService$sendRequest$1
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public Unit invoke() {
                                        BleConnector bleConnector7 = BleConnector.INSTANCE;
                                        BleKey bleKey = BleKey.WATCH_FACE;
                                        Integer watchFaceResource2 = ((SmaUploadWatchFaceReq) SmaBaseReq.this).getWatchFaceResource();
                                        Intrinsics.checkNotNull(watchFaceResource2);
                                        bleConnector7.sendStream(bleKey, watchFaceResource2.intValue(), 0);
                                        return Unit.INSTANCE;
                                    }
                                }, 31, null);
                                return;
                            }
                            if (smaUploadWatchFaceReq.getWatchFaceFilePath() != null) {
                                String watchFaceFilePath = smaUploadWatchFaceReq.getWatchFaceFilePath();
                                Intrinsics.checkNotNull(watchFaceFilePath);
                                if (watchFaceFilePath.length() > 0) {
                                    ThreadsKt.thread$default(false, false, null, null, 0, new Function0<Unit>() { // from class: com.coveiot.android.bleabstract.services.SmaBaseBleService$sendRequest$2
                                        {
                                            super(0);
                                        }

                                        @Override // kotlin.jvm.functions.Function0
                                        public Unit invoke() {
                                            BleConnector bleConnector7 = BleConnector.INSTANCE;
                                            BleKey bleKey = BleKey.WATCH_FACE;
                                            String watchFaceFilePath2 = ((SmaUploadWatchFaceReq) SmaBaseReq.this).getWatchFaceFilePath();
                                            Intrinsics.checkNotNull(watchFaceFilePath2);
                                            bleConnector7.sendStream(bleKey, watchFaceFilePath2, 0);
                                            return Unit.INSTANCE;
                                        }
                                    }, 31, null);
                                    return;
                                }
                            }
                            if (smaUploadWatchFaceReq.getWatchFaceElementArray() != null) {
                                Intrinsics.checkNotNull(smaUploadWatchFaceReq.getWatchFaceElementArray());
                                if (!watchFaceElementArray.isEmpty()) {
                                    ThreadsKt.thread$default(false, false, null, null, 0, new Function0<Unit>() { // from class: com.coveiot.android.bleabstract.services.SmaBaseBleService$sendRequest$3
                                        {
                                            super(0);
                                        }

                                        @Override // kotlin.jvm.functions.Function0
                                        public Unit invoke() {
                                            WatchFaceBuilder watchFaceBuilder = WatchFaceBuilder.INSTANCE;
                                            ArrayList<Element> watchFaceElementArray2 = ((SmaUploadWatchFaceReq) SmaBaseReq.this).getWatchFaceElementArray();
                                            Intrinsics.checkNotNull(watchFaceElementArray2);
                                            Object[] array = watchFaceElementArray2.toArray(new Element[0]);
                                            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                                            BleConnector.INSTANCE.sendStream(BleKey.WATCH_FACE, watchFaceBuilder.build((Element[]) array, 1), 0);
                                            return Unit.INSTANCE;
                                        }
                                    }, 31, null);
                                    return;
                                }
                            }
                            SmaResponseListener responseListener3 = khBaseReq.getResponseListener();
                            Intrinsics.checkNotNull(responseListener3);
                            responseListener3.onFailure(new SmaError(SmaErrorType.COMMAND_REQUEST_ERROR, "Watch face file info is missing"));
                            return;
                        }
                        return;
                    case 36:
                        if ((khBaseReq instanceof SmaExitRemoteCameraReq) && khBaseReq.getKeyFlag() == BleKeyFlag.UPDATE) {
                            BleConnector bleConnector7 = BleConnector.INSTANCE;
                            if (bleConnector7.isAvailable() && this.g) {
                                BleConnector.sendInt8$default(bleConnector7, key, khBaseReq.getKeyFlag(), 0, false, false, 24, null);
                                this.g = false;
                            }
                            getBleHandleCallback().onCommandReply(key, khBaseReq.getKeyFlag(), true);
                            return;
                        }
                        return;
                    case 37:
                        if (khBaseReq instanceof SmaSetTemperatureUnitReq) {
                            BleConnector.sendInt8$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), ((SmaSetTemperatureUnitReq) khBaseReq).getUnit(), false, false, 24, null);
                            return;
                        }
                        return;
                    case 38:
                        if (khBaseReq instanceof SmaGirlCareSettingsReq) {
                            BleConnector.sendObject$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), ((SmaGirlCareSettingsReq) khBaseReq).getBleGirlCare(), false, false, 24, null);
                            return;
                        }
                        return;
                    case 39:
                        if ((khBaseReq instanceof SmaSleepQualityReq) && khBaseReq.getKeyFlag() == BleKeyFlag.UPDATE) {
                            SmaSleepQualityReq smaSleepQualityReq = (SmaSleepQualityReq) khBaseReq;
                            LogHelper.d(this.j, "BleSleepQuality written to watch: ", smaSleepQualityReq.getBleSleepQuality().toString());
                            BleConnector.sendObject$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), smaSleepQualityReq.getBleSleepQuality(), false, false, 24, null);
                            return;
                        }
                        return;
                    case 40:
                        if (khBaseReq instanceof SmaGetWatchFacePositionReq) {
                            BleConnector.sendInt8$default(BleConnector.INSTANCE, key, khBaseReq.getKeyFlag(), (BleCache.getInt$default(BleCache.INSTANCE, key, 0, null, 4, null) + 1) % 5, false, false, 24, null);
                            return;
                        }
                        return;
                    case 41:
                        if (khBaseReq instanceof SmaSetMusicMetaDataReq) {
                            SmaSetMusicMetaDataReq smaSetMusicMetaDataReq = (SmaSetMusicMetaDataReq) khBaseReq;
                            if (smaSetMusicMetaDataReq.getMusicControls() != null) {
                                Intrinsics.checkNotNull(smaSetMusicMetaDataReq.getMusicControls());
                                if (!musicControls.isEmpty()) {
                                    List<BleMusicControl> musicControls2 = smaSetMusicMetaDataReq.getMusicControls();
                                    Intrinsics.checkNotNull(musicControls2);
                                    for (BleMusicControl bleMusicControl : musicControls2) {
                                        BleConnector.sendObject$default(BleConnector.INSTANCE, BleKey.MUSIC_CONTROL, BleKeyFlag.UPDATE, bleMusicControl, false, false, 24, null);
                                    }
                                    return;
                                }
                                return;
                            }
                            return;
                        } else if (khBaseReq instanceof SmaSetPlaybackStateReq) {
                            SmaSetPlaybackStateReq smaSetPlaybackStateReq = (SmaSetPlaybackStateReq) khBaseReq;
                            if (smaSetPlaybackStateReq.getMusicControl() != null) {
                                BleConnector.sendObject$default(BleConnector.INSTANCE, BleKey.MUSIC_CONTROL, BleKeyFlag.UPDATE, smaSetPlaybackStateReq.getMusicControl(), false, false, 24, null);
                                return;
                            }
                            return;
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            }
        }

        public final void setKhCurrentCommand(@Nullable SmaBaseReq smaBaseReq) {
            this.f = smaBaseReq;
        }

        public final void setMBluetoothDevice(@Nullable BluetoothDevice bluetoothDevice) {
            this.f3913a = bluetoothDevice;
        }

        public final void setMacAddress(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.macAddress = str;
        }

        public final void setRemoteCameraOpened(boolean z) {
            this.g = z;
        }

        public final void setServiceBinder(@NotNull IBinder iBinder) {
            Intrinsics.checkNotNullParameter(iBinder, "<set-?>");
            this.serviceBinder = iBinder;
        }

        public void stopService() {
            disconnectAndForget();
        }

        public void stopServiceRetainMacAddress() {
            disconnectAndRetainMacAddress();
        }

        public final void updateConnectionState(@NotNull final CloveSmaBleState.BleState state, boolean z) {
            Handler handler;
            Intrinsics.checkNotNullParameter(state, "state");
            this.e = state;
            if (!z || (handler = this.b) == null) {
                return;
            }
            Intrinsics.checkNotNull(handler);
            handler.removeCallbacksAndMessages(null);
            Handler handler2 = this.b;
            Intrinsics.checkNotNull(handler2);
            handler2.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.z3
                @Override // java.lang.Runnable
                public final void run() {
                    SmaBaseBleService.a(CloveSmaBleState.BleState.this);
                }
            });
        }

        public static final void a(CloveSmaBleState.BleState state) {
            Intrinsics.checkNotNullParameter(state, "$state");
            BleEventBusManager.getInstance().getEventBus().post(new CloveSmaBleState(state));
        }
    }
