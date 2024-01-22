package com.coveiot.android.bleabstract.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.format.DateFormat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.apex.bluetooth.callback.DataReportCallback;
import com.apex.bluetooth.callback.DataResponseCallback;
import com.apex.bluetooth.callback.DonDisturbCallback;
import com.apex.bluetooth.callback.FeaturesCallback;
import com.apex.bluetooth.callback.GeneralCallback;
import com.apex.bluetooth.callback.MotionDataReportCallback;
import com.apex.bluetooth.callback.OtaCallback;
import com.apex.bluetooth.callback.PersonInfoCallback;
import com.apex.bluetooth.callback.RaiseHandBrightScreenCallback;
import com.apex.bluetooth.callback.WatchFaceCallback;
import com.apex.bluetooth.callback.WatchInfoCallback;
import com.apex.bluetooth.core.EABleManager;
import com.apex.bluetooth.enumeration.CommonFlag;
import com.apex.bluetooth.enumeration.EABleConnectState;
import com.apex.bluetooth.enumeration.EABleSportStatus;
import com.apex.bluetooth.enumeration.QueryWatchInfoType;
import com.apex.bluetooth.enumeration.TimeZone;
import com.apex.bluetooth.listener.EABleConnectListener;
import com.apex.bluetooth.model.EABleBindInfo;
import com.apex.bluetooth.model.EABleBloodOxygen;
import com.apex.bluetooth.model.EABleContact;
import com.apex.bluetooth.model.EABleDailyData;
import com.apex.bluetooth.model.EABleExecutiveResponse;
import com.apex.bluetooth.model.EABleFeatures;
import com.apex.bluetooth.model.EABleGesturesBrightScreen;
import com.apex.bluetooth.model.EABleGpsData;
import com.apex.bluetooth.model.EABleHabitRecord;
import com.apex.bluetooth.model.EABleHeartData;
import com.apex.bluetooth.model.EABleMtu;
import com.apex.bluetooth.model.EABleMultiData;
import com.apex.bluetooth.model.EABleMusicControl;
import com.apex.bluetooth.model.EABleNotDisturb;
import com.apex.bluetooth.model.EABleOta;
import com.apex.bluetooth.model.EABlePaceData;
import com.apex.bluetooth.model.EABlePersonInfo;
import com.apex.bluetooth.model.EABlePhoneResponse;
import com.apex.bluetooth.model.EABlePressureData;
import com.apex.bluetooth.model.EABleQueryMusic;
import com.apex.bluetooth.model.EABleReportMonitorData;
import com.apex.bluetooth.model.EABleReportSportData;
import com.apex.bluetooth.model.EABleRestingRateData;
import com.apex.bluetooth.model.EABleSleepData;
import com.apex.bluetooth.model.EABleSocialResponse;
import com.apex.bluetooth.model.EABleStepFrequencyData;
import com.apex.bluetooth.model.EABleSwitch;
import com.apex.bluetooth.model.EABleSyncTime;
import com.apex.bluetooth.model.EABleTimelyData;
import com.apex.bluetooth.model.EABleWatchFace;
import com.apex.bluetooth.model.EABleWatchInfo;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.formatter.eastapex.EastApexActivityFormatter;
import com.coveiot.android.bleabstract.formatter.eastapex.EastApexHeartRateFormatter;
import com.coveiot.android.bleabstract.formatter.eastapex.EastApexSleepFormatter;
import com.coveiot.android.bleabstract.formatter.eastapex.EastApexSpo2Formatter;
import com.coveiot.android.bleabstract.formatter.eastapex.EastApexStepsFormatter;
import com.coveiot.android.bleabstract.formatter.eastapex.EastApexStressFormatter;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.response.LiveMusicControlRes;
import com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage;
import com.coveiot.android.bleabstract.response.MusicControlState;
import com.coveiot.android.bleabstract.services.EastapexBleService;
import com.coveiot.android.eastapexsdk.EastApexResponseListener;
import com.coveiot.android.eastapexsdk.api.EastApexBaseReq;
import com.coveiot.android.eastapexsdk.api.EastApexBaseRes;
import com.coveiot.android.eastapexsdk.api.EastApexCloudWatchFaceReq;
import com.coveiot.android.eastapexsdk.api.EastApexCustomWatchFaceReq;
import com.coveiot.android.eastapexsdk.api.EastApexDefaultWatchFaceReq;
import com.coveiot.android.eastapexsdk.api.EastApexDeviceInfoReq;
import com.coveiot.android.eastapexsdk.api.EastApexDrinkReminderReq;
import com.coveiot.android.eastapexsdk.api.EastApexFemaleWellnessConfigReq;
import com.coveiot.android.eastapexsdk.api.EastApexGetDNDReq;
import com.coveiot.android.eastapexsdk.api.EastApexGetLiftWristReq;
import com.coveiot.android.eastapexsdk.api.EastApexGetUserInfoReq;
import com.coveiot.android.eastapexsdk.api.EastApexMusicMetaDataReq;
import com.coveiot.android.eastapexsdk.api.EastApexSetSedentaryReminderReq;
import com.coveiot.android.eastapexsdk.api.EastApexSetUserInfoReq;
import com.coveiot.android.eastapexsdk.api.EastApexSetWeatherReq;
import com.coveiot.android.eastapexsdk.api.EastApexSyncContactsReq;
import com.coveiot.android.eastapexsdk.api.EastApexWatchFacePositionRequest;
import com.coveiot.android.eastapexsdk.error.EastApexError;
import com.coveiot.android.eastapexsdk.error.EastApexErrorType;
import com.coveiot.kheastapexdb.activity.EntityEAActivityData;
import com.coveiot.kheastapexdb.activity.KHEAActivityRepository;
import com.coveiot.kheastapexdb.heartrate.EntityEAHeartRateData;
import com.coveiot.kheastapexdb.heartrate.KHEAHeartRateRepository;
import com.coveiot.kheastapexdb.sleep.EntityEASleepData;
import com.coveiot.kheastapexdb.sleep.KHEASleepRepository;
import com.coveiot.kheastapexdb.spo2.EntityEASpO2Data;
import com.coveiot.kheastapexdb.spo2.KHEASpO2Repository;
import com.coveiot.kheastapexdb.stress.EntityEAStressData;
import com.coveiot.kheastapexdb.stress.KHEAStressRepository;
import com.coveiot.kheastapexdb.walk.EntityEAStepsData;
import com.coveiot.kheastapexdb.walk.KHEAStepsRepository;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.api.model.CameraState;
import com.coveiot.sdk.ble.api.model.FindMyPhoneState;
import com.coveiot.sdk.ble.api.response.CameraEventRes;
import com.coveiot.sdk.ble.api.response.FindMyPhoneRes;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.model.BleDeviceInfo;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.example.custom_dial.RGBAPlatformDiffTxtUtils;
import com.example.custom_dial.RGBAPointUtils;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.IntProgression;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class EastapexBleService extends Service {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int MTU = 128;
    @NotNull
    public final EastapexBleService$setLiftWristWatchListener$1 A;
    @NotNull
    public final EastapexBleService$getLiftWristWatchListener$1 B;
    @NotNull
    public final EastapexBleService$getUserInfoListener$1 C;
    @NotNull
    public final EastapexBleService$setUserInfoListener$1 D;
    @NotNull
    public final EastapexBleService$stepsInfoListener$1 E;
    @NotNull
    public final EastapexBleService$musicMetaDataListener$1 F;
    @NotNull
    public final EastapexBleService$setSedentaryListener$1 G;
    @NotNull
    public final EastapexBleService$setDrinkReminderListener$1 H;
    @NotNull
    public final EastapexBleService$setWeatherListener$1 I;
    @NotNull
    public final EastapexBleService$setFemaleWellnessListener$1 J;
    @NotNull
    public final EastapexBleService$setDefaultWatchFaceListener$1 K;
    @NotNull
    public final EastapexBleService$setCloudWatchFaceListener$1 L;
    @NotNull
    public final EastapexBleService$setCustomWatchFaceListener$1 M;
    @NotNull
    public final EastapexBleService$getDefaultWatchFaceListener$1 N;
    @Nullable
    public BluetoothManager b;
    @Nullable
    public BluetoothAdapter c;
    @Nullable
    public BluetoothDevice f;
    @Nullable
    public EastApexBaseReq h;
    @Nullable
    public EABleFeatures k;
    @NotNull
    public final BroadcastReceiver l;
    @NotNull
    public final EastapexBleService$batteryLevelListener$1 m;
    public String macAddress;
    @NotNull
    public final EastapexBleService$getAlarmListener$1 n;
    @NotNull
    public final EastapexBleService$deviceInfoListener$1 o;
    @NotNull
    public final EastapexBleService$dndInfoListener$1 p;
    @NotNull
    public final EastapexBleService$setDndInfoListener$1 q;
    @NotNull
    public final EastapexBleService$setAlarmListener$1 r;
    @NotNull
    public final EastapexBleService$dialyGoalListener$1 s;
    public IBinder serviceBinder;
    @NotNull
    public final EastapexBleService$setGoalListener$1 t;
    @NotNull
    public final EastapexBleService$setHourSystemListener$1 u;
    @NotNull
    public final EastapexBleService$deviceTimeListener$1 v;
    @NotNull
    public final EastapexBleService$distanceUnitListener$1 w;
    @NotNull
    public final EastapexBleService$setDisatanceUnitListener$1 x;
    @NotNull
    public final EastapexBleService$setMSGNotificationListener$1 y;
    @NotNull
    public final EastapexBleService$setHeartRateMonitoringListener$1 z;

    /* renamed from: a  reason: collision with root package name */
    public final String f3711a = EastapexBleService.class.getSimpleName();
    @Nullable
    public CloveBleState.BleState d = CloveBleState.BleState.DISCONNECTED;
    @Nullable
    public Handler e = new Handler();
    public long g = -1;
    @Nullable
    public Handler i = new Handler();
    @NotNull
    public Handler j = new Handler();

    /* loaded from: classes2.dex */
    public final class BtServiceBinder extends Binder {
        public BtServiceBinder() {
        }

        @NotNull
        public final EastapexBleService getService() {
            return EastapexBleService.this;
        }
    }

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public final class ConnectionLister implements EABleConnectListener {
        public ConnectionLister() {
        }

        @Override // com.apex.bluetooth.listener.EABleConnectListener
        public void connectError(int i) {
            EastapexBleService.this.d = CloveBleState.BleState.DISCONNECTED;
            String tag = EastapexBleService.this.getTAG();
            LogHelper.i(tag, "ConnectionLister connectError " + i);
            Handler handler = EastapexBleService.this.i;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            EastapexBleService eastapexBleService = EastapexBleService.this;
            CloveBleState.BleState bleState = eastapexBleService.d;
            Intrinsics.checkNotNull(bleState);
            eastapexBleService.updateConnectionState(bleState, true);
            EastapexBleService.access$disconnectL(EastapexBleService.this);
            EastapexBleService.this.reConnection();
        }

        @Override // com.apex.bluetooth.listener.EABleConnectListener
        public void connectTimeOut() {
            EastapexBleService.this.d = CloveBleState.BleState.DISCONNECTED;
            LogHelper.i(EastapexBleService.this.getTAG(), "ConnectionLister connectTimeOut");
            Handler handler = EastapexBleService.this.i;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            EastapexBleService eastapexBleService = EastapexBleService.this;
            CloveBleState.BleState bleState = eastapexBleService.d;
            Intrinsics.checkNotNull(bleState);
            eastapexBleService.updateConnectionState(bleState, true);
            EastapexBleService.access$disconnectL(EastapexBleService.this);
            EastapexBleService.this.reConnection();
        }

        @Override // com.apex.bluetooth.listener.EABleConnectListener
        public void deviceConnected() {
            EastapexBleService.this.d = CloveBleState.BleState.CONNECTED;
            String tag = EastapexBleService.this.getTAG();
            StringBuilder sb = new StringBuilder();
            sb.append("ConnectionLister device connected bluetoothConnectionStatus ");
            sb.append(EastapexBleService.this.d);
            sb.append(", timer is on == ");
            sb.append(EastapexBleService.access$getReconnectionTimer$p(EastapexBleService.this) != null);
            LogHelper.i(tag, sb.toString());
            EastapexBleService.access$syncTime(EastapexBleService.this);
            Handler handler = EastapexBleService.this.i;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            EastapexBleService eastapexBleService = EastapexBleService.this;
            CloveBleState.BleState bleState = eastapexBleService.d;
            Intrinsics.checkNotNull(bleState);
            eastapexBleService.updateConnectionState(bleState, true);
            EastapexBleService.this.deviceSupportedFeatures();
            EastapexBleService.access$bindDevice(EastapexBleService.this);
        }

        @Override // com.apex.bluetooth.listener.EABleConnectListener
        public void deviceDisconnect() {
            EastapexBleService.this.d = CloveBleState.BleState.DISCONNECTED;
            Handler handler = EastapexBleService.this.i;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            EastapexBleService eastapexBleService = EastapexBleService.this;
            CloveBleState.BleState bleState = eastapexBleService.d;
            Intrinsics.checkNotNull(bleState);
            eastapexBleService.updateConnectionState(bleState, true);
            if (Intrinsics.areEqual(PreferenceManagerAbstract.getInstance(EastapexBleService.this.getApplicationContext()).getConnectionType(), ConnectionType.RECONNECT_ON_DISCONNECT.name())) {
                EastapexBleService.access$disconnectL(EastapexBleService.this);
                EastapexBleService.this.reConnection();
            } else {
                EastapexBleService.this.unRegisterReceivers();
                EastapexBleService.this.stopForeground(true);
                EastapexBleService.this.stopSelf();
            }
            LogHelper.i(EastapexBleService.this.getTAG(), "ConnectionLister deviceDisconnect");
        }

        @Override // com.apex.bluetooth.listener.EABleConnectListener
        public void deviceNotFind() {
            EastapexBleService.this.d = CloveBleState.BleState.DISCONNECTED;
            LogHelper.i(EastapexBleService.this.getTAG(), "ConnectionLister deviceNotFind");
            EastapexBleService eastapexBleService = EastapexBleService.this;
            CloveBleState.BleState bleState = eastapexBleService.d;
            Intrinsics.checkNotNull(bleState);
            eastapexBleService.updateConnectionState(bleState, true);
        }

        @Override // com.apex.bluetooth.listener.EABleConnectListener
        public void notOpenLocation() {
            EastapexBleService.this.d = CloveBleState.BleState.DISCONNECTED;
            EastapexBleService eastapexBleService = EastapexBleService.this;
            CloveBleState.BleState bleState = eastapexBleService.d;
            Intrinsics.checkNotNull(bleState);
            eastapexBleService.updateConnectionState(bleState, true);
            LogHelper.i(EastapexBleService.this.getTAG(), "ConnectionLister notOpenLocation");
        }

        @Override // com.apex.bluetooth.listener.EABleConnectListener
        public void unopenedBluetooth() {
            EastapexBleService.this.d = CloveBleState.BleState.SCANNING;
            EastapexBleService eastapexBleService = EastapexBleService.this;
            CloveBleState.BleState bleState = eastapexBleService.d;
            Intrinsics.checkNotNull(bleState);
            eastapexBleService.updateConnectionState(bleState, true);
            LogHelper.i(EastapexBleService.this.getTAG(), "ConnectionLister unopenedBluetooth");
        }

        @Override // com.apex.bluetooth.listener.EABleConnectListener
        public void unsupportedBLE() {
            EastapexBleService.this.d = CloveBleState.BleState.DISCONNECTED;
            EastapexBleService eastapexBleService = EastapexBleService.this;
            CloveBleState.BleState bleState = eastapexBleService.d;
            Intrinsics.checkNotNull(bleState);
            eastapexBleService.updateConnectionState(bleState, true);
            LogHelper.i(EastapexBleService.this.getTAG(), "ConnectionLister unsupportedBLE");
        }
    }

    /* loaded from: classes2.dex */
    public final class DataReportListener implements DataReportCallback {
        public DataReportListener() {
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void answerIncoming() {
            LogHelper.i(EastapexBleService.this.getTAG(), "接听.....");
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void appSportData(@Nullable EABleReportSportData eABleReportSportData) {
            String tag = EastapexBleService.this.getTAG();
            LogHelper.d(tag, "DataReportListener: appSportData " + eABleReportSportData);
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void appSportStatus(@Nullable EABleSportStatus eABleSportStatus) {
            String tag = EastapexBleService.this.getTAG();
            LogHelper.d(tag, "DataReportListener: appSportStatus " + eABleSportStatus);
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void brightScreenStatus(@Nullable EABleSwitch eABleSwitch) {
            String tag = EastapexBleService.this.getTAG();
            LogHelper.d(tag, "DataReportListener: brightScreenStatus " + eABleSwitch);
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void circadian() {
            LogHelper.i(EastapexBleService.this.getTAG(), "Physiological period");
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void connectCamera() {
            LogHelper.i(EastapexBleService.this.getTAG(), "connect camera");
            EABlePhoneResponse eABlePhoneResponse = new EABlePhoneResponse();
            eABlePhoneResponse.setEaBleExecutiveResponse(EABleExecutiveResponse.success);
            eABlePhoneResponse.setId(2);
            EABleManager.getInstance().mobileOperationResponse(eABlePhoneResponse, new DataResponseCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$DataReportListener$connectCamera$1
                @Override // com.apex.bluetooth.callback.EABleCallback
                public void mutualFail(int i) {
                }

                @Override // com.apex.bluetooth.callback.DataResponseCallback
                public void mutualSuccess() {
                }
            });
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void disturbStatus(@Nullable EABleSwitch eABleSwitch) {
            String tag = EastapexBleService.this.getTAG();
            LogHelper.d(tag, "DataReportListener: disturbStatus " + eABleSwitch);
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void endTakePhoto() {
            EABlePhoneResponse eABlePhoneResponse = new EABlePhoneResponse();
            eABlePhoneResponse.setEaBleExecutiveResponse(EABleExecutiveResponse.success);
            eABlePhoneResponse.setId(4);
            EABleManager eABleManager = EABleManager.getInstance();
            final EastapexBleService eastapexBleService = EastapexBleService.this;
            eABleManager.mobileOperationResponse(eABlePhoneResponse, new DataResponseCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$DataReportListener$endTakePhoto$1
                @Override // com.apex.bluetooth.callback.EABleCallback
                public void mutualFail(int i) {
                }

                @Override // com.apex.bluetooth.callback.DataResponseCallback
                public void mutualSuccess() {
                    CameraEventRes cameraEventRes = new CameraEventRes(CameraState.EXIT);
                    Intent intent = new Intent(Constants.CAMERA_BROADCAST_INTENT);
                    intent.putExtra(Constants.CAMERA_BROADCAST_INTENT_EXTRA, cameraEventRes);
                    LocalBroadcastManager.getInstance(EastapexBleService.this).sendBroadcast(intent);
                    LogHelper.i(EastapexBleService.this.getTAG(), "end take photo");
                }
            });
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void hangUpIncoming() {
            LogHelper.i(EastapexBleService.this.getTAG(), "挂断.....");
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void mtu(@NotNull EABleMtu eaBleMtu) {
            Intrinsics.checkNotNullParameter(eaBleMtu, "eaBleMtu");
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void musicControl(@NotNull EABleMusicControl eaBleMusicControl) {
            Intrinsics.checkNotNullParameter(eaBleMusicControl, "eaBleMusicControl");
            new LiveMusicControlRes(MusicControlState.UNKNOWN);
            Intent intent = new Intent(Constants.MUSIC_CONTROL_BROADCAST_INTENT);
            if (eaBleMusicControl.getE_ops() == EABleMusicControl.MusicControl.play_start) {
                intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.PLAY));
                LocalBroadcastManager.getInstance(EastapexBleService.this).sendBroadcast(intent);
            } else {
                EABleMusicControl.MusicControl musicControl = eaBleMusicControl.e_ops;
                if (musicControl == EABleMusicControl.MusicControl.play_stop) {
                    intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.PAUSE));
                    LocalBroadcastManager.getInstance(EastapexBleService.this).sendBroadcast(intent);
                } else if (musicControl == EABleMusicControl.MusicControl.previous_song) {
                    intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.PREVIOUS));
                    LocalBroadcastManager.getInstance(EastapexBleService.this).sendBroadcast(intent);
                } else if (musicControl == EABleMusicControl.MusicControl.next_song) {
                    intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.NEXT));
                    LocalBroadcastManager.getInstance(EastapexBleService.this).sendBroadcast(intent);
                } else if (musicControl == EABleMusicControl.MusicControl.volume_up) {
                    intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.VOLUME_UP));
                    LocalBroadcastManager.getInstance(EastapexBleService.this).sendBroadcast(intent);
                } else {
                    intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.VOLUME_DOWN));
                    LocalBroadcastManager.getInstance(EastapexBleService.this).sendBroadcast(intent);
                }
            }
            String tag = EastapexBleService.this.getTAG();
            LogHelper.d(tag, "musicData eaBleMusicControl.e_ops " + eaBleMusicControl.e_ops.name());
        }

        @Override // com.apex.bluetooth.callback.EABleCallback
        public void mutualFail(int i) {
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void queryMusic(@NotNull EABleQueryMusic eaBleQueryMusic) {
            Intrinsics.checkNotNullParameter(eaBleQueryMusic, "eaBleQueryMusic");
            String tag = EastapexBleService.this.getTAG();
            LogHelper.i(tag, "queryMusic " + eaBleQueryMusic.getE_app().name());
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void reportMonitorData(@Nullable EABleReportMonitorData eABleReportMonitorData) {
            String tag = EastapexBleService.this.getTAG();
            LogHelper.d(tag, "DataReportListener: reportMonitorData " + eABleReportMonitorData);
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void searchPhone() {
            LogHelper.i(EastapexBleService.this.getTAG(), "Searching phone");
            FindMyPhoneRes findMyPhoneRes = new FindMyPhoneRes(FindMyPhoneState.ON);
            Intent intent = new Intent(Constants.FIND_MY_PHONE_BROADCAST_INTENT);
            intent.putExtra(Constants.FIND_MY_PHONE_BROADCAST_INTENT_EXTRA, findMyPhoneRes);
            LocalBroadcastManager.getInstance(EastapexBleService.this).sendBroadcast(intent);
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void socialResponse(@NotNull EABleSocialResponse eaBleSocialResponse) {
            Intrinsics.checkNotNullParameter(eaBleSocialResponse, "eaBleSocialResponse");
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void stopSearchPhone() {
            LogHelper.i(EastapexBleService.this.getTAG(), "Stop phone");
            FindMyPhoneRes findMyPhoneRes = new FindMyPhoneRes(FindMyPhoneState.OFF);
            Intent intent = new Intent(Constants.FIND_MY_PHONE_BROADCAST_INTENT);
            intent.putExtra(Constants.FIND_MY_PHONE_BROADCAST_INTENT_EXTRA, findMyPhoneRes);
            LocalBroadcastManager.getInstance(EastapexBleService.this).sendBroadcast(intent);
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void stopSearchWatch() {
            LogHelper.i(EastapexBleService.this.getTAG(), "停止查找手表");
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void takePhoto() {
            EABlePhoneResponse eABlePhoneResponse = new EABlePhoneResponse();
            eABlePhoneResponse.setEaBleExecutiveResponse(EABleExecutiveResponse.success);
            eABlePhoneResponse.setId(3);
            EABleManager eABleManager = EABleManager.getInstance();
            final EastapexBleService eastapexBleService = EastapexBleService.this;
            eABleManager.mobileOperationResponse(eABlePhoneResponse, new DataResponseCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$DataReportListener$takePhoto$1
                @Override // com.apex.bluetooth.callback.EABleCallback
                public void mutualFail(int i) {
                }

                @Override // com.apex.bluetooth.callback.DataResponseCallback
                public void mutualSuccess() {
                    CameraEventRes cameraEventRes = new CameraEventRes(CameraState.CAPTURE);
                    Intent intent = new Intent(Constants.CAMERA_BROADCAST_INTENT);
                    intent.putExtra(Constants.CAMERA_BROADCAST_INTENT_EXTRA, cameraEventRes);
                    LocalBroadcastManager.getInstance(EastapexBleService.this).sendBroadcast(intent);
                    LogHelper.i(EastapexBleService.this.getTAG(), "take photo");
                }
            });
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void timelyData(@Nullable EABleTimelyData eABleTimelyData) {
            String tag = EastapexBleService.this.getTAG();
            LogHelper.d(tag, "DataReportListener: timelyData " + eABleTimelyData);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0051 A[Catch: Exception -> 0x00a0, TryCatch #0 {Exception -> 0x00a0, blocks: (B:3:0x000b, B:5:0x0013, B:7:0x001d, B:9:0x0044, B:16:0x0051, B:17:0x007b, B:18:0x0094), top: B:23:0x000b }] */
        /* JADX WARN: Removed duplicated region for block: B:17:0x007b A[Catch: Exception -> 0x00a0, TryCatch #0 {Exception -> 0x00a0, blocks: (B:3:0x000b, B:5:0x0013, B:7:0x001d, B:9:0x0044, B:16:0x0051, B:17:0x007b, B:18:0x0094), top: B:23:0x000b }] */
        @Override // com.apex.bluetooth.callback.DataReportCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void transmissionComplete() {
            /*
                r5 = this;
                com.coveiot.android.bleabstract.services.EastapexBleService r0 = com.coveiot.android.bleabstract.services.EastapexBleService.this
                java.lang.String r0 = r0.getTAG()
                java.lang.String r1 = "transmissionComplete motion data sync Complete"
                com.coveiot.utils.utility.LogHelper.i(r0, r1)
                com.coveiot.android.bleabstract.services.EastapexBleService r0 = com.coveiot.android.bleabstract.services.EastapexBleService.this     // Catch: java.lang.Exception -> La0
                com.coveiot.android.eastapexsdk.api.EastApexBaseReq r0 = r0.getKhCurrentCommand()     // Catch: java.lang.Exception -> La0
                if (r0 == 0) goto L94
                com.coveiot.android.bleabstract.services.EastapexBleService r0 = com.coveiot.android.bleabstract.services.EastapexBleService.this     // Catch: java.lang.Exception -> La0
                com.coveiot.android.eastapexsdk.api.EastApexBaseReq r0 = r0.getKhCurrentCommand()     // Catch: java.lang.Exception -> La0
                boolean r0 = r0 instanceof com.coveiot.android.eastapexsdk.api.EastApexStepsReq     // Catch: java.lang.Exception -> La0
                if (r0 == 0) goto L94
                com.coveiot.android.eastapexsdk.api.EastApexBaseRes r0 = new com.coveiot.android.eastapexsdk.api.EastApexBaseRes     // Catch: java.lang.Exception -> La0
                r0.<init>()     // Catch: java.lang.Exception -> La0
                com.coveiot.android.bleabstract.services.EastapexBleService r1 = com.coveiot.android.bleabstract.services.EastapexBleService.this     // Catch: java.lang.Exception -> La0
                com.coveiot.android.eastapexsdk.api.EastApexBaseReq r1 = r1.getKhCurrentCommand()     // Catch: java.lang.Exception -> La0
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> La0
                r0.setBaseReq(r1)     // Catch: java.lang.Exception -> La0
                com.coveiot.kheastapexdb.walk.KHEAStepsRepository$Companion r1 = com.coveiot.kheastapexdb.walk.KHEAStepsRepository.Companion     // Catch: java.lang.Exception -> La0
                com.coveiot.android.bleabstract.services.EastapexBleService r2 = com.coveiot.android.bleabstract.services.EastapexBleService.this     // Catch: java.lang.Exception -> La0
                java.lang.Object r1 = r1.getInstance(r2)     // Catch: java.lang.Exception -> La0
                com.coveiot.kheastapexdb.walk.KHEAStepsRepository r1 = (com.coveiot.kheastapexdb.walk.KHEAStepsRepository) r1     // Catch: java.lang.Exception -> La0
                com.coveiot.android.bleabstract.services.EastapexBleService r2 = com.coveiot.android.bleabstract.services.EastapexBleService.this     // Catch: java.lang.Exception -> La0
                java.lang.String r2 = r2.getMacAddress()     // Catch: java.lang.Exception -> La0
                java.util.List r1 = r1.getAllUnProcessedStepsData(r2)     // Catch: java.lang.Exception -> La0
                if (r1 == 0) goto L4d
                boolean r2 = r1.isEmpty()     // Catch: java.lang.Exception -> La0
                if (r2 == 0) goto L4b
                goto L4d
            L4b:
                r2 = 0
                goto L4e
            L4d:
                r2 = 1
            L4e:
                r3 = 0
                if (r2 != 0) goto L7b
                com.coveiot.android.bleabstract.formatter.eastapex.EastApexStepsFormatter$Companion r2 = com.coveiot.android.bleabstract.formatter.eastapex.EastApexStepsFormatter.Companion     // Catch: java.lang.Exception -> La0
                com.coveiot.android.bleabstract.services.EastapexBleService r4 = com.coveiot.android.bleabstract.services.EastapexBleService.this     // Catch: java.lang.Exception -> La0
                java.lang.Object r2 = r2.getInstance(r4)     // Catch: java.lang.Exception -> La0
                com.coveiot.android.bleabstract.formatter.eastapex.EastApexStepsFormatter r2 = (com.coveiot.android.bleabstract.formatter.eastapex.EastApexStepsFormatter) r2     // Catch: java.lang.Exception -> La0
                java.util.ArrayList r1 = r2.getStepResponseList(r1)     // Catch: java.lang.Exception -> La0
                r0.setObj(r1)     // Catch: java.lang.Exception -> La0
                com.coveiot.android.bleabstract.services.EastapexBleService r1 = com.coveiot.android.bleabstract.services.EastapexBleService.this     // Catch: java.lang.Exception -> La0
                com.coveiot.android.eastapexsdk.api.EastApexBaseReq r1 = r1.getKhCurrentCommand()     // Catch: java.lang.Exception -> La0
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> La0
                com.coveiot.android.eastapexsdk.EastApexResponseListener r1 = r1.getResponseListener()     // Catch: java.lang.Exception -> La0
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> La0
                r1.onResponse(r0)     // Catch: java.lang.Exception -> La0
                com.coveiot.android.bleabstract.services.EastapexBleService r0 = com.coveiot.android.bleabstract.services.EastapexBleService.this     // Catch: java.lang.Exception -> La0
                r0.setKhCurrentCommand(r3)     // Catch: java.lang.Exception -> La0
                goto La4
            L7b:
                com.coveiot.android.bleabstract.services.EastapexBleService r1 = com.coveiot.android.bleabstract.services.EastapexBleService.this     // Catch: java.lang.Exception -> La0
                com.coveiot.android.eastapexsdk.api.EastApexBaseReq r1 = r1.getKhCurrentCommand()     // Catch: java.lang.Exception -> La0
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> La0
                com.coveiot.android.eastapexsdk.EastApexResponseListener r1 = r1.getResponseListener()     // Catch: java.lang.Exception -> La0
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> La0
                r1.onResponse(r0)     // Catch: java.lang.Exception -> La0
                com.coveiot.android.bleabstract.services.EastapexBleService r0 = com.coveiot.android.bleabstract.services.EastapexBleService.this     // Catch: java.lang.Exception -> La0
                r0.setKhCurrentCommand(r3)     // Catch: java.lang.Exception -> La0
                goto La4
            L94:
                com.coveiot.android.bleabstract.services.EastapexBleService r0 = com.coveiot.android.bleabstract.services.EastapexBleService.this     // Catch: java.lang.Exception -> La0
                java.lang.String r0 = r0.getTAG()     // Catch: java.lang.Exception -> La0
                java.lang.String r1 = "khCurrent command is null"
                com.coveiot.sdk.ble.helper.LogsHelper.d(r0, r1)     // Catch: java.lang.Exception -> La0
                goto La4
            La0:
                r0 = move-exception
                r0.printStackTrace()
            La4:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.services.EastapexBleService.DataReportListener.transmissionComplete():void");
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void updateAgps() {
        }

        @Override // com.apex.bluetooth.callback.DataReportCallback
        public void updateWeather() {
            LogHelper.i(EastapexBleService.this.getTAG(), "update weather");
        }
    }

    /* loaded from: classes2.dex */
    public final class MotionDataReportListener implements MotionDataReportCallback {
        public MotionDataReportListener() {
        }

        @Override // com.apex.bluetooth.callback.MotionDataReportCallback
        public void bloodOxygenData(@Nullable List<? extends EABleBloodOxygen> list, @Nullable CommonFlag commonFlag) {
            String tag = EastapexBleService.this.getTAG();
            LogHelper.i(tag, "MotionDataReportListener: bloodOxygenData" + list);
            ArrayList<EntityEASpO2Data> convertEASpo2DataToEntity = EastApexSpo2Formatter.Companion.getInstance(EastapexBleService.this).convertEASpo2DataToEntity(list);
            if (convertEASpo2DataToEntity != null) {
                KHEASpO2Repository.Companion.getInstance(EastapexBleService.this).insertSpO2DataList(convertEASpo2DataToEntity);
            }
        }

        @Override // com.apex.bluetooth.callback.MotionDataReportCallback
        public void dailyExerciseData(@Nullable List<? extends EABleDailyData> list, @Nullable CommonFlag commonFlag) {
            String tag = EastapexBleService.this.getTAG();
            LogHelper.i(tag, "MotionDataReportListener: dailyExerciseData" + list);
            ArrayList<EntityEAStepsData> convertEAStepDataToEntity = EastApexStepsFormatter.Companion.getInstance(EastapexBleService.this).convertEAStepDataToEntity(list);
            if (convertEAStepDataToEntity != null) {
                KHEAStepsRepository.Companion.getInstance(EastapexBleService.this).insertStepsDataList(convertEAStepDataToEntity);
            }
        }

        @Override // com.apex.bluetooth.callback.MotionDataReportCallback
        public void getHabitData(@Nullable List<? extends EABleHabitRecord> list, @Nullable CommonFlag commonFlag) {
            if (list != null && !list.isEmpty()) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    String tag = EastapexBleService.this.getTAG();
                    LogHelper.i(tag, "习惯记录:" + list.get(i));
                }
            }
            LogHelper.i(EastapexBleService.this.getTAG(), "MotionDataReportListener: getHabitData");
        }

        @Override // com.apex.bluetooth.callback.MotionDataReportCallback
        public void gpsData(@Nullable List<? extends EABleGpsData> list, @Nullable CommonFlag commonFlag) {
            LogHelper.i(EastapexBleService.this.getTAG(), "MotionDataReportListener: gpsData");
        }

        @Override // com.apex.bluetooth.callback.MotionDataReportCallback
        public void heartData(@Nullable List<? extends EABleHeartData> list, @Nullable CommonFlag commonFlag) {
            String tag = EastapexBleService.this.getTAG();
            LogHelper.i(tag, "MotionDataReportListener: heartData" + list);
            ArrayList<EntityEAHeartRateData> convertEAHRDataToEntity = EastApexHeartRateFormatter.Companion.getInstance(EastapexBleService.this).convertEAHRDataToEntity(list);
            if (convertEAHRDataToEntity != null) {
                KHEAHeartRateRepository.Companion.getInstance(EastapexBleService.this).insertHeartRateDataList(convertEAHRDataToEntity);
            }
        }

        @Override // com.apex.bluetooth.callback.MotionDataReportCallback
        public void multiMotionData(@Nullable List<? extends EABleMultiData> list, @Nullable CommonFlag commonFlag) {
            String tag = EastapexBleService.this.getTAG();
            LogHelper.i(tag, "MotionDataReportListener: multiMotionData" + list);
            ArrayList<EntityEAActivityData> convertEAActivityDataToEntity = EastApexActivityFormatter.Companion.getInstance(EastapexBleService.this).convertEAActivityDataToEntity(list);
            if (convertEAActivityDataToEntity != null) {
                KHEAActivityRepository.Companion.getInstance(EastapexBleService.this).insertActivityData(convertEAActivityDataToEntity);
            }
        }

        @Override // com.apex.bluetooth.callback.EABleCallback
        public void mutualFail(int i) {
            String tag = EastapexBleService.this.getTAG();
            LogHelper.i(tag, "MotionDataReportListener: mutualFail: " + i);
        }

        @Override // com.apex.bluetooth.callback.MotionDataReportCallback
        public void pressureData(@Nullable List<? extends EABlePressureData> list, @Nullable CommonFlag commonFlag) {
            String tag = EastapexBleService.this.getTAG();
            LogHelper.i(tag, "MotionDataReportListener: pressureData" + list);
            ArrayList<EntityEAStressData> convertEAStressDataToEntity = EastApexStressFormatter.Companion.getInstance(EastapexBleService.this).convertEAStressDataToEntity(list);
            if (convertEAStressDataToEntity != null) {
                KHEAStressRepository.Companion.getInstance(EastapexBleService.this).insertStressDataList(convertEAStressDataToEntity);
            }
        }

        @Override // com.apex.bluetooth.callback.MotionDataReportCallback
        public void restingHeartRateData(@Nullable List<? extends EABleRestingRateData> list, @Nullable CommonFlag commonFlag) {
            LogHelper.i(EastapexBleService.this.getTAG(), "MotionDataReportListener: restingHeartRateData");
        }

        @Override // com.apex.bluetooth.callback.MotionDataReportCallback
        public void sleepData(@Nullable List<? extends EABleSleepData> list, @Nullable CommonFlag commonFlag) {
            String tag = EastapexBleService.this.getTAG();
            LogHelper.i(tag, "MotionDataReportListener: sleepData" + list);
            ArrayList<EntityEASleepData> convertEASleepDataToEntity = EastApexSleepFormatter.Companion.getInstance(EastapexBleService.this).convertEASleepDataToEntity(list);
            if (convertEASleepDataToEntity != null) {
                KHEASleepRepository.Companion.getInstance(EastapexBleService.this).insertSleepDataList(convertEASleepDataToEntity);
            }
        }

        @Override // com.apex.bluetooth.callback.MotionDataReportCallback
        public void speedData(@Nullable List<? extends EABlePaceData> list, @Nullable CommonFlag commonFlag) {
            LogHelper.i(EastapexBleService.this.getTAG(), "MotionDataReportListener: speedData");
        }

        @Override // com.apex.bluetooth.callback.MotionDataReportCallback
        public void stepFrequencyData(@Nullable List<EABleStepFrequencyData> list, @Nullable CommonFlag commonFlag) {
            LogHelper.i(EastapexBleService.this.getTAG(), "MotionDataReportListener: stepFrequencyData");
        }
    }

    /* JADX WARN: Type inference failed for: r0v12, types: [com.coveiot.android.bleabstract.services.EastapexBleService$deviceInfoListener$1] */
    /* JADX WARN: Type inference failed for: r0v13, types: [com.coveiot.android.bleabstract.services.EastapexBleService$dndInfoListener$1] */
    /* JADX WARN: Type inference failed for: r0v25, types: [com.coveiot.android.bleabstract.services.EastapexBleService$getLiftWristWatchListener$1] */
    /* JADX WARN: Type inference failed for: r0v26, types: [com.coveiot.android.bleabstract.services.EastapexBleService$getUserInfoListener$1] */
    /* JADX WARN: Type inference failed for: r0v27, types: [com.coveiot.android.bleabstract.services.EastapexBleService$setUserInfoListener$1] */
    /* JADX WARN: Type inference failed for: r0v28, types: [com.coveiot.android.bleabstract.services.EastapexBleService$stepsInfoListener$1] */
    /* JADX WARN: Type inference failed for: r0v29, types: [com.coveiot.android.bleabstract.services.EastapexBleService$musicMetaDataListener$1] */
    /* JADX WARN: Type inference failed for: r0v30, types: [com.coveiot.android.bleabstract.services.EastapexBleService$setSedentaryListener$1] */
    /* JADX WARN: Type inference failed for: r0v31, types: [com.coveiot.android.bleabstract.services.EastapexBleService$setDrinkReminderListener$1] */
    /* JADX WARN: Type inference failed for: r0v32, types: [com.coveiot.android.bleabstract.services.EastapexBleService$setWeatherListener$1] */
    /* JADX WARN: Type inference failed for: r0v33, types: [com.coveiot.android.bleabstract.services.EastapexBleService$setFemaleWellnessListener$1] */
    /* JADX WARN: Type inference failed for: r0v34, types: [com.coveiot.android.bleabstract.services.EastapexBleService$setDefaultWatchFaceListener$1] */
    /* JADX WARN: Type inference failed for: r0v35, types: [com.coveiot.android.bleabstract.services.EastapexBleService$setCloudWatchFaceListener$1] */
    /* JADX WARN: Type inference failed for: r0v36, types: [com.coveiot.android.bleabstract.services.EastapexBleService$setCustomWatchFaceListener$1] */
    /* JADX WARN: Type inference failed for: r0v37, types: [com.coveiot.android.bleabstract.services.EastapexBleService$getDefaultWatchFaceListener$1] */
    public EastapexBleService() {
        new Handler(Looper.getMainLooper());
        setServiceBinder(new BtServiceBinder());
        this.l = new BroadcastReceiver() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$mBluetoothStatusReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(@NotNull Context context, @NotNull Intent intent) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                String action = intent.getAction();
                if (Intrinsics.areEqual(action, "android.bluetooth.adapter.action.STATE_CHANGED")) {
                    int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                    if (intExtra != 3 && intExtra != 10) {
                        if (intExtra == 12) {
                            LogHelper.i(EastapexBleService.this.getTAG(), "Bluetooth state BluetoothAdapter.STATE_ON", ModuleNames.BLEABSTRACT.getModuleName());
                            if (PreferenceManagerAbstract.getInstance(EastapexBleService.this.getApplicationContext()).getConnectionType().equals(ConnectionType.RECONNECT_ON_DISCONNECT.name())) {
                                String connectedDeviceMacAddress = PreferenceManagerAbstract.getInstance(EastapexBleService.this.getApplicationContext()).getConnectedDeviceMacAddress();
                                EABleManager eABleManager = EABleManager.getInstance();
                                EastapexBleService eastapexBleService = EastapexBleService.this;
                                eABleManager.connectToPeripheral(connectedDeviceMacAddress, eastapexBleService, new EastapexBleService.ConnectionLister(), 128, new EastapexBleService.DataReportListener(), new EastapexBleService.MotionDataReportListener());
                                LogHelper.i(EastapexBleService.this.getTAG(), "Bluetooth state ON == RECONNECT_ON_DISCONNECT");
                                return;
                            }
                            return;
                        } else if (intExtra != 13) {
                            return;
                        }
                    }
                    EastapexBleService.access$disconnectL(EastapexBleService.this);
                    EastapexBleService.this.updateConnectionState(CloveBleState.BleState.DISCONNECTED, true);
                    LogHelper.d(EastapexBleService.this.getTAG(), "Bluetooth state BluetoothAdapter.STATE_OFF", ModuleNames.BLEABSTRACT.getModuleName());
                } else if (kotlin.text.m.equals(action, "action_stop_service", true)) {
                    EastapexBleService.this.updateConnectionState(CloveBleState.BleState.DISCONNECTED, true);
                }
            }
        };
        this.m = new EastapexBleService$batteryLevelListener$1(this);
        this.n = new EastapexBleService$getAlarmListener$1(this);
        this.o = new WatchInfoCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$deviceInfoListener$1
            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.i(tag, "deviceInfoListener->mutualFail-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
                EastapexBleService.this.a(String.valueOf(i));
            }

            @Override // com.apex.bluetooth.callback.WatchInfoCallback
            public void watchInfo(@Nullable EABleWatchInfo eABleWatchInfo) {
                if (EastapexBleService.this.getKhCurrentCommand() == null || !(EastapexBleService.this.getKhCurrentCommand() instanceof EastApexDeviceInfoReq)) {
                    return;
                }
                EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                EastApexBaseReq khCurrentCommand = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                eastApexBaseRes.setBaseReq(khCurrentCommand);
                eastApexBaseRes.setObj(eABleWatchInfo);
                EastApexBaseReq khCurrentCommand2 = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
                EastapexBleService.this.setKhCurrentCommand(null);
            }
        };
        this.p = new DonDisturbCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$dndInfoListener$1
            @Override // com.apex.bluetooth.callback.DonDisturbCallback
            public void donDisturbInfo(@Nullable EABleNotDisturb eABleNotDisturb) {
                String tag = EastapexBleService.this.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("dndInfoListener->donDisturbInfo-> DND turned on ==  ");
                sb.append(eABleNotDisturb != null ? Integer.valueOf(eABleNotDisturb.sw) : null);
                sb.append(" , khCurrentCommand ");
                sb.append(EastapexBleService.this.getKhCurrentCommand());
                LogHelper.i(tag, sb.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                if (EastapexBleService.this.getKhCurrentCommand() == null || !(EastapexBleService.this.getKhCurrentCommand() instanceof EastApexGetDNDReq)) {
                    return;
                }
                EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                EastApexBaseReq khCurrentCommand = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                eastApexBaseRes.setBaseReq(khCurrentCommand);
                eastApexBaseRes.setObj(eABleNotDisturb);
                EastApexBaseReq khCurrentCommand2 = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
                EastapexBleService.this.setKhCurrentCommand(null);
            }

            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.i(tag, "dndInfoListener->mutualFail-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
                EastapexBleService.this.a(String.valueOf(i));
            }
        };
        this.q = new EastapexBleService$setDndInfoListener$1(this);
        this.r = new EastapexBleService$setAlarmListener$1(this);
        this.s = new EastapexBleService$dialyGoalListener$1(this);
        this.t = new EastapexBleService$setGoalListener$1(this);
        this.u = new EastapexBleService$setHourSystemListener$1(this);
        this.v = new EastapexBleService$deviceTimeListener$1(this);
        this.w = new EastapexBleService$distanceUnitListener$1(this);
        this.x = new EastapexBleService$setDisatanceUnitListener$1(this);
        this.y = new EastapexBleService$setMSGNotificationListener$1(this);
        this.z = new EastapexBleService$setHeartRateMonitoringListener$1(this);
        this.A = new EastapexBleService$setLiftWristWatchListener$1(this);
        this.B = new RaiseHandBrightScreenCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$getLiftWristWatchListener$1
            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.i(tag, "getLiftWristWatchListener->mutualFail-> " + i + "  khCurrentCommand " + EastapexBleService.this.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
                EastapexBleService.this.a(String.valueOf(i));
            }

            @Override // com.apex.bluetooth.callback.RaiseHandBrightScreenCallback
            public void switchInfo(@Nullable EABleGesturesBrightScreen eABleGesturesBrightScreen) {
                EABleGesturesBrightScreen.BrightScreenSwitch brightScreenSwitch;
                String tag = EastapexBleService.this.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("getLiftWristWatchListener->switchInfo-> switch -> ");
                sb.append((eABleGesturesBrightScreen == null || (brightScreenSwitch = eABleGesturesBrightScreen.brightScreenSwitch) == null) ? null : Integer.valueOf(brightScreenSwitch.value));
                sb.append(" khCurrentCommand ");
                sb.append(EastapexBleService.this.getKhCurrentCommand());
                LogHelper.i(tag, sb.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                if (EastapexBleService.this.getKhCurrentCommand() == null || !(EastapexBleService.this.getKhCurrentCommand() instanceof EastApexGetLiftWristReq)) {
                    return;
                }
                EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                EastApexBaseReq khCurrentCommand = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                eastApexBaseRes.setBaseReq(khCurrentCommand);
                eastApexBaseRes.setObj(eABleGesturesBrightScreen);
                EastApexBaseReq khCurrentCommand2 = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
                EastapexBleService.this.setKhCurrentCommand(null);
            }
        };
        this.C = new PersonInfoCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$getUserInfoListener$1
            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.i(tag, "getUserInfoListener->mutualFail-> " + i + "  khCurrentCommand " + EastapexBleService.this.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
                EastapexBleService.this.a(String.valueOf(i));
            }

            @Override // com.apex.bluetooth.callback.PersonInfoCallback
            public void personInfo(@Nullable EABlePersonInfo eABlePersonInfo) {
                String tag = EastapexBleService.this.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("getUserInfoListener->personInfo-> age -> ");
                sb.append(eABlePersonInfo != null ? Integer.valueOf(eABlePersonInfo.getAge()) : null);
                sb.append("  wearing habit -> ");
                sb.append(eABlePersonInfo != null ? eABlePersonInfo.getE_hand_info() : null);
                sb.append("  gender -> ");
                sb.append(eABlePersonInfo != null ? eABlePersonInfo.getE_sex_info() : null);
                sb.append("  skin color -> ");
                sb.append(eABlePersonInfo != null ? eABlePersonInfo.getE_skin_color() : null);
                sb.append("  height -> ");
                sb.append(eABlePersonInfo != null ? Integer.valueOf(eABlePersonInfo.getHeight()) : null);
                sb.append(" weight -> ");
                sb.append(eABlePersonInfo != null ? Integer.valueOf(eABlePersonInfo.getWeight()) : null);
                sb.append(" khCurrentCommand ");
                sb.append(EastapexBleService.this.getKhCurrentCommand());
                LogHelper.i(tag, sb.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                if (EastapexBleService.this.getKhCurrentCommand() == null || !(EastapexBleService.this.getKhCurrentCommand() instanceof EastApexGetUserInfoReq)) {
                    return;
                }
                EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                EastApexBaseReq khCurrentCommand = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                eastApexBaseRes.setBaseReq(khCurrentCommand);
                eastApexBaseRes.setObj(eABlePersonInfo);
                EastApexBaseReq khCurrentCommand2 = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
                EastapexBleService.this.setKhCurrentCommand(null);
            }
        };
        this.D = new GeneralCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$setUserInfoListener$1
            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.i(tag, "setUserInfoListener->mutualFail-> " + i + "  khCurrentCommand " + EastapexBleService.this.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
                EastapexBleService.this.a(String.valueOf(i));
            }

            @Override // com.apex.bluetooth.callback.GeneralCallback
            public void result(boolean z) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.i(tag, "setUserInfoListener->switchInfo-> switch -> " + z + " khCurrentCommand " + EastapexBleService.this.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
                if (EastapexBleService.this.getKhCurrentCommand() == null || !(EastapexBleService.this.getKhCurrentCommand() instanceof EastApexSetUserInfoReq)) {
                    return;
                }
                EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                EastApexBaseReq khCurrentCommand = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                eastApexBaseRes.setBaseReq(khCurrentCommand);
                eastApexBaseRes.setObj(Boolean.valueOf(z));
                EastApexBaseReq khCurrentCommand2 = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
                EastapexBleService.this.setKhCurrentCommand(null);
            }
        };
        this.E = new GeneralCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$stepsInfoListener$1
            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.i(tag, "stepsInfoListener->mutualFail-> " + i + "  khCurrentCommand " + EastapexBleService.this.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
                EastapexBleService.this.a(String.valueOf(i));
            }

            @Override // com.apex.bluetooth.callback.GeneralCallback
            public void result(boolean z) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.i(tag, "stepsInfoListener->result->" + z + " khCurrentCommand " + EastapexBleService.this.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
            }
        };
        this.F = new DataResponseCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$musicMetaDataListener$1
            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.i(tag, "musicMetaDataListener->mutualFail-> " + i + "  khCurrentCommand " + EastapexBleService.this.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
                EastapexBleService.this.a(String.valueOf(i));
            }

            @Override // com.apex.bluetooth.callback.DataResponseCallback
            public void mutualSuccess() {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.i(tag, "musicMetaDataListener->mutualSuccess  khCurrentCommand " + EastapexBleService.this.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
                if (EastapexBleService.this.getKhCurrentCommand() == null || !(EastapexBleService.this.getKhCurrentCommand() instanceof EastApexMusicMetaDataReq)) {
                    return;
                }
                EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                EastApexBaseReq khCurrentCommand = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                eastApexBaseRes.setBaseReq(khCurrentCommand);
                EastApexBaseReq khCurrentCommand2 = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
                EastapexBleService.this.setKhCurrentCommand(null);
            }
        };
        this.G = new GeneralCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$setSedentaryListener$1
            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.i(tag, "setSedentaryListener->mutualFail-> " + i + "  khCurrentCommand " + EastapexBleService.this.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
                EastapexBleService.this.a(String.valueOf(i));
            }

            @Override // com.apex.bluetooth.callback.GeneralCallback
            public void result(boolean z) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.i(tag, "setSedentaryListener->result ->" + z + " khCurrentCommand " + EastapexBleService.this.getKhCurrentCommand(), ModuleNames.BLEABSTRACT.getModuleName());
                if (EastapexBleService.this.getKhCurrentCommand() == null || !(EastapexBleService.this.getKhCurrentCommand() instanceof EastApexSetSedentaryReminderReq)) {
                    return;
                }
                EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                EastApexBaseReq khCurrentCommand = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                eastApexBaseRes.setBaseReq(khCurrentCommand);
                eastApexBaseRes.setObj(Boolean.valueOf(z));
                EastApexBaseReq khCurrentCommand2 = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
                EastapexBleService.this.setKhCurrentCommand(null);
            }
        };
        this.H = new GeneralCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$setDrinkReminderListener$1
            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.i(tag, "setDrinkReminderListener->mutualFail-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
                EastapexBleService.this.a(String.valueOf(i));
            }

            @Override // com.apex.bluetooth.callback.GeneralCallback
            public void result(boolean z) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.i(tag, "setDrinkReminderListener->result-> ==  " + z, ModuleNames.BLEABSTRACT.getModuleName());
                if (EastapexBleService.this.getKhCurrentCommand() == null || !(EastapexBleService.this.getKhCurrentCommand() instanceof EastApexDrinkReminderReq)) {
                    return;
                }
                EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                EastApexBaseReq khCurrentCommand = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                eastApexBaseRes.setBaseReq(khCurrentCommand);
                eastApexBaseRes.setObj(Boolean.valueOf(z));
                EastApexBaseReq khCurrentCommand2 = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
                EastapexBleService.this.setKhCurrentCommand(null);
            }
        };
        this.I = new GeneralCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$setWeatherListener$1
            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.d(tag, "setWeatherListener->mutualFail-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
                EastapexBleService.this.a(String.valueOf(i));
            }

            @Override // com.apex.bluetooth.callback.GeneralCallback
            public void result(boolean z) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.d(tag, "setWeatherListener->result-> ==  " + z, ModuleNames.BLEABSTRACT.getModuleName());
                if (EastapexBleService.this.getKhCurrentCommand() == null || !(EastapexBleService.this.getKhCurrentCommand() instanceof EastApexSetWeatherReq)) {
                    return;
                }
                EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                EastApexBaseReq khCurrentCommand = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                eastApexBaseRes.setBaseReq(khCurrentCommand);
                eastApexBaseRes.setObj(Boolean.valueOf(z));
                EastApexBaseReq khCurrentCommand2 = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
                EastapexBleService.this.setKhCurrentCommand(null);
            }
        };
        this.J = new GeneralCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$setFemaleWellnessListener$1
            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.d(tag, "setFemaleWellnessListener->mutualFail-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
                EastapexBleService.this.a(String.valueOf(i));
            }

            @Override // com.apex.bluetooth.callback.GeneralCallback
            public void result(boolean z) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.d(tag, "setFemaleWellnessListener->result-> ==  " + z, ModuleNames.BLEABSTRACT.getModuleName());
                if (EastapexBleService.this.getKhCurrentCommand() == null || !(EastapexBleService.this.getKhCurrentCommand() instanceof EastApexFemaleWellnessConfigReq)) {
                    return;
                }
                EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                EastApexBaseReq khCurrentCommand = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                eastApexBaseRes.setBaseReq(khCurrentCommand);
                eastApexBaseRes.setObj(Boolean.valueOf(z));
                EastApexBaseReq khCurrentCommand2 = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
                EastapexBleService.this.setKhCurrentCommand(null);
            }
        };
        this.K = new GeneralCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$setDefaultWatchFaceListener$1
            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.d(tag, "setDefaultWatchFaceListener->mutualFail-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
                EastapexBleService.this.a(String.valueOf(i));
            }

            @Override // com.apex.bluetooth.callback.GeneralCallback
            public void result(boolean z) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.d(tag, "setDefaultWatchFaceListener->result-> ==  " + z, ModuleNames.BLEABSTRACT.getModuleName());
                if (EastapexBleService.this.getKhCurrentCommand() == null || !(EastapexBleService.this.getKhCurrentCommand() instanceof EastApexDefaultWatchFaceReq)) {
                    return;
                }
                EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                EastApexBaseReq khCurrentCommand = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                eastApexBaseRes.setBaseReq(khCurrentCommand);
                eastApexBaseRes.setObj(Boolean.valueOf(z));
                EastApexBaseReq khCurrentCommand2 = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
                EastapexBleService.this.setKhCurrentCommand(null);
            }
        };
        this.L = new OtaCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$setCloudWatchFaceListener$1
            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.d(tag, "setCloudWatchFaceListener->mutualFail-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
                EastapexBleService.this.a(String.valueOf(i));
            }

            @Override // com.apex.bluetooth.callback.OtaCallback
            public void progress(int i) {
                if (EastapexBleService.this.getKhCurrentCommand() == null || !(EastapexBleService.this.getKhCurrentCommand() instanceof EastApexCloudWatchFaceReq)) {
                    return;
                }
                EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                EastApexBaseReq khCurrentCommand = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                eastApexBaseRes.setBaseReq(khCurrentCommand);
                LiveWatchFaceUploadPercentage liveWatchFaceUploadPercentage = new LiveWatchFaceUploadPercentage();
                liveWatchFaceUploadPercentage.setPercentage(i);
                eastApexBaseRes.setObj(liveWatchFaceUploadPercentage);
                EastApexBaseReq khCurrentCommand2 = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
            }

            @Override // com.apex.bluetooth.callback.OtaCallback
            public void success() {
                LogHelper.d(EastapexBleService.this.getTAG(), "setCloudWatchFaceListener->success->", ModuleNames.BLEABSTRACT.getModuleName());
                if (EastapexBleService.this.getKhCurrentCommand() == null || !(EastapexBleService.this.getKhCurrentCommand() instanceof EastApexCloudWatchFaceReq)) {
                    return;
                }
                EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                EastApexBaseReq khCurrentCommand = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                eastApexBaseRes.setBaseReq(khCurrentCommand);
                EastApexBaseReq khCurrentCommand2 = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
                EastapexBleService.this.setKhCurrentCommand(null);
            }
        };
        this.M = new OtaCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$setCustomWatchFaceListener$1
            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.d(tag, "setCustomWatchFaceListener->mutualFail-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
                EastapexBleService.this.a(String.valueOf(i));
            }

            @Override // com.apex.bluetooth.callback.OtaCallback
            public void progress(int i) {
                if (EastapexBleService.this.getKhCurrentCommand() == null || !(EastapexBleService.this.getKhCurrentCommand() instanceof EastApexCustomWatchFaceReq)) {
                    return;
                }
                EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                EastApexBaseReq khCurrentCommand = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                eastApexBaseRes.setBaseReq(khCurrentCommand);
                LiveWatchFaceUploadPercentage liveWatchFaceUploadPercentage = new LiveWatchFaceUploadPercentage();
                liveWatchFaceUploadPercentage.setPercentage(i);
                eastApexBaseRes.setObj(liveWatchFaceUploadPercentage);
                EastApexBaseReq khCurrentCommand2 = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
            }

            @Override // com.apex.bluetooth.callback.OtaCallback
            public void success() {
                LogHelper.d(EastapexBleService.this.getTAG(), "setCustomWatchFaceListener->success->", ModuleNames.BLEABSTRACT.getModuleName());
                if (EastapexBleService.this.getKhCurrentCommand() == null || !(EastapexBleService.this.getKhCurrentCommand() instanceof EastApexCustomWatchFaceReq)) {
                    return;
                }
                EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                EastApexBaseReq khCurrentCommand = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                eastApexBaseRes.setBaseReq(khCurrentCommand);
                EastApexBaseReq khCurrentCommand2 = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
                EastapexBleService.this.setKhCurrentCommand(null);
            }
        };
        this.N = new WatchFaceCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$getDefaultWatchFaceListener$1
            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.d(tag, "getDefaultWatchFaceListener->mutualFail-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
                EastapexBleService.this.a(String.valueOf(i));
            }

            @Override // com.apex.bluetooth.callback.WatchFaceCallback
            public void watchFaceInfo(@Nullable EABleWatchFace eABleWatchFace) {
                String tag = EastapexBleService.this.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("getDefaultWatchFaceListener watchFaceInfo ");
                sb.append(eABleWatchFace != null ? Integer.valueOf(eABleWatchFace.id) : null);
                LogHelper.d(tag, sb.toString());
                String tag2 = EastapexBleService.this.getTAG();
                LogHelper.d(tag2, "getDefaultWatchFaceListener watchFaceInfo " + eABleWatchFace);
                if (EastapexBleService.this.getKhCurrentCommand() == null || !(EastapexBleService.this.getKhCurrentCommand() instanceof EastApexWatchFacePositionRequest)) {
                    return;
                }
                EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                EastApexBaseReq khCurrentCommand = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                eastApexBaseRes.setBaseReq(khCurrentCommand);
                eastApexBaseRes.setObj(eABleWatchFace);
                EastApexBaseReq khCurrentCommand2 = EastapexBleService.this.getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand2);
                EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(eastApexBaseRes);
                EastapexBleService.this.setKhCurrentCommand(null);
            }
        };
    }

    public static final void access$bindDevice(final EastapexBleService eastapexBleService) {
        eastapexBleService.getClass();
        EABleManager.getInstance().queryWatchInfo(QueryWatchInfoType.watch_info, new WatchInfoCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$bindDevice$1
            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.i(tag, "on bind mutualFail " + i + ' ');
            }

            @Override // com.apex.bluetooth.callback.WatchInfoCallback
            public void watchInfo(@Nullable EABleWatchInfo eABleWatchInfo) {
                EABleWatchInfo.BindingInfo bindingInfo;
                String tag = EastapexBleService.this.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("on bind watchInfo ");
                sb.append((eABleWatchInfo == null || (bindingInfo = eABleWatchInfo.getBindingInfo()) == null) ? null : bindingInfo.name());
                sb.append(' ');
                LogHelper.i(tag, sb.toString());
                if ((eABleWatchInfo != null ? eABleWatchInfo.getBindingInfo() : null) == EABleWatchInfo.BindingInfo.unbound) {
                    String replace = new Regex(":").replace(EastapexBleService.this.getMacAddress(), "");
                    EABleBindInfo eABleBindInfo = new EABleBindInfo();
                    eABleBindInfo.setUser_id(replace);
                    eABleBindInfo.setE_ops(EABleBindInfo.BindingOps.end);
                    EABleManager eABleManager = EABleManager.getInstance();
                    final EastapexBleService eastapexBleService2 = EastapexBleService.this;
                    eABleManager.setOpsBinding(eABleBindInfo, new GeneralCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$bindDevice$1$watchInfo$1
                        @Override // com.apex.bluetooth.callback.EABleCallback
                        public void mutualFail(int i) {
                            String tag2 = EastapexBleService.this.getTAG();
                            LogHelper.i(tag2, "on bind setOpsBinding mutualFail " + i + ' ');
                        }

                        @Override // com.apex.bluetooth.callback.GeneralCallback
                        public void result(boolean z) {
                            String tag2 = EastapexBleService.this.getTAG();
                            LogHelper.i(tag2, "on bind setOpsBinding result " + z + ' ');
                        }
                    });
                }
            }
        });
    }

    public static final void access$disconnectL(EastapexBleService eastapexBleService) {
        eastapexBleService.getClass();
        EABleManager.getInstance().disconnectPeripheral();
    }

    public static final /* synthetic */ Timer access$getReconnectionTimer$p(EastapexBleService eastapexBleService) {
        eastapexBleService.getClass();
        return null;
    }

    public static final void access$syncTime(final EastapexBleService eastapexBleService) {
        eastapexBleService.getClass();
        EABleSyncTime eABleSyncTime = new EABleSyncTime();
        Calendar calendar = Calendar.getInstance();
        eABleSyncTime.setYear(calendar.get(1));
        eABleSyncTime.setMonth(calendar.get(2) + 1);
        eABleSyncTime.setDay(calendar.get(5));
        eABleSyncTime.setHour(calendar.get(11));
        eABleSyncTime.setMinute(calendar.get(12));
        eABleSyncTime.setSecond(calendar.get(13));
        int offset = calendar.getTimeZone().getOffset(calendar.getTime().getTime());
        if (offset > 0) {
            eABleSyncTime.setE_time_zone(TimeZone.east);
        } else if (offset < 0) {
            eABleSyncTime.setE_time_zone(TimeZone.west);
        } else {
            eABleSyncTime.setE_time_zone(TimeZone.zero);
        }
        int abs = ((Math.abs(offset) / 1000) / 60) / 60;
        eABleSyncTime.setTime_zone_hour(abs);
        int abs2 = ((Math.abs(offset) - (((abs * 1000) * 60) * 60)) / 1000) / 60;
        if (abs2 <= 0) {
            abs2 = 0;
        }
        eABleSyncTime.setTime_zone_minute(abs2);
        eABleSyncTime.setE_sync_mode(EABleSyncTime.SyncMode.normal);
        if (DateFormat.is24HourFormat(eastapexBleService)) {
            eABleSyncTime.setE_hour_system(EABleSyncTime.HourSystem.hour_24);
        } else {
            eABleSyncTime.setE_hour_system(EABleSyncTime.HourSystem.hour_12);
        }
        EABleManager.getInstance().setTimeSync(eABleSyncTime, new GeneralCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$syncTime$1
            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.i(tag, "on syncTime mutualFail " + i + ' ');
            }

            @Override // com.apex.bluetooth.callback.GeneralCallback
            public void result(boolean z) {
                String tag = EastapexBleService.this.getTAG();
                LogHelper.i(tag, "on syncTime result " + z + ' ');
            }
        });
    }

    public static final void b(EastapexBleService this$0, Ref.ObjectRef rgbaPlatformDiffTxtUtils, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(rgbaPlatformDiffTxtUtils, "$rgbaPlatformDiffTxtUtils");
        if (str != null) {
            ArrayList arrayList = new ArrayList();
            EABleOta eABleOta = new EABleOta();
            eABleOta.setOtaType(EABleOta.OtaType.user_wf);
            eABleOta.setFilePath(str);
            eABleOta.setPop(true);
            arrayList.add(eABleOta);
            EABleManager.getInstance().otaUpdate(arrayList, this$0.M);
        } else {
            LogHelper.d(this$0.f3711a, "EastApexCustomWatchFaceReq-> custome watch face dial path is null", ModuleNames.BLEABSTRACT.getModuleName());
            this$0.a("Invalid Filepath");
        }
        RGBAPlatformDiffTxtUtils rGBAPlatformDiffTxtUtils = (RGBAPlatformDiffTxtUtils) rgbaPlatformDiffTxtUtils.element;
        if (rGBAPlatformDiffTxtUtils != null) {
            rGBAPlatformDiffTxtUtils.destroy();
        }
        rgbaPlatformDiffTxtUtils.element = null;
    }

    public final void a() {
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
            if (Build.VERSION.SDK_INT >= 26) {
                Object systemService = getSystemService("notification");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
                NotificationChannel notificationChannel = new NotificationChannel("101", data.getAppDesc(), 2);
                notificationChannel.enableLights(false);
                ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
                build = new Notification.Builder(this, "101").setContentTitle(data.getAppDesc()).setColor(ContextCompat.getColor(this, data.getAppColor())).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
                Intrinsics.checkNotNullExpressionValue(build, "{\n                val no…   .build()\n            }");
            } else {
                build = new Notification.Builder(this).setContentTitle(data.getAppDesc()).setSmallIcon(data.getAppIcon()).setColor(ContextCompat.getColor(this, data.getAppColor())).setContentIntent(activity).build();
                Intrinsics.checkNotNullExpressionValue(build, "{\n                Notifi…   .build()\n            }");
            }
            startForeground(101, build);
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, this);
        }
    }

    public final void connect(@NotNull String deviceAddress) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        BleDeviceInfo.clearInstance();
        PreferenceManagerAbstract.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress(deviceAddress);
        PreferenceManagerAbstract.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
        String connectedDeviceMacAddress = PreferenceManagerAbstract.getInstance(getApplicationContext()).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(applicationC…connectedDeviceMacAddress");
        setMacAddress(connectedDeviceMacAddress);
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter != null) {
            Intrinsics.checkNotNull(bluetoothAdapter);
            if (bluetoothAdapter.isEnabled()) {
                EABleConnectState deviceConnectState = EABleManager.getInstance().getDeviceConnectState();
                if (deviceConnectState != EABleConnectState.STATE_IDLE && deviceConnectState != EABleConnectState.STATE_DISCONNECT) {
                    if (deviceConnectState == EABleConnectState.STATE_CONNECTED) {
                        LogHelper.i(this.f3711a, "connectstate EABleConnectState.STATE_CONNECTED");
                        return;
                    }
                    return;
                }
                try {
                    EABleManager.getInstance().connectToPeripheral(deviceAddress, this, new ConnectionLister(), 128, new DataReportListener(), new MotionDataReportListener());
                    String str = this.f3711a;
                    LogHelper.i(str, "connection type ++ " + BleUtils.getConnectionType(this));
                } catch (Exception e) {
                    e.printStackTrace();
                    String str2 = this.f3711a;
                    StringBuilder sb = new StringBuilder();
                    sb.append("connect exception ");
                    e.printStackTrace();
                    sb.append(Unit.INSTANCE);
                    LogHelper.i(str2, sb.toString());
                }
            }
        }
    }

    public final void deviceSupportedFeatures() {
        EABleManager.getInstance().queryWatchInfo(QueryWatchInfoType.features, new FeaturesCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$deviceSupportedFeatures$1
            @Override // com.apex.bluetooth.callback.FeaturesCallback
            public void featuresList(@Nullable EABleFeatures eABleFeatures) {
                String tag = EastapexBleService.this.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("deviceSupportedFeatures > featuresList isFindMyBandSupported ");
                sb.append(eABleFeatures != null ? Integer.valueOf(eABleFeatures.getFind_watch()) : null);
                LogHelper.i(tag, sb.toString());
                EastapexBleService.this.setDevSupfeatures(eABleFeatures);
            }

            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i) {
                LogHelper.i(EastapexBleService.this.getTAG(), "Fetching Device Supported Features Failed");
                EastapexBleService.this.a(String.valueOf(i));
            }
        });
    }

    public final void disconnect() {
        PreferenceManagerAbstract.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress("");
        PreferenceManagerAbstract.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        EABleManager.getInstance().disconnectPeripheral();
        updateConnectionState(CloveBleState.BleState.DISCONNECTED, false);
        unRegisterReceivers();
        stopForeground(true);
        stopSelf();
    }

    public final void disconnectAndRetainMacAddress() {
        PreferenceManagerAbstract.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        EABleManager.getInstance().disconnectPeripheral();
        unRegisterReceivers();
        stopForeground(true);
        stopSelf();
    }

    public final void disconnectOnBluetoothTurnedOff() {
        EABleManager.getInstance().disconnectPeripheral();
    }

    @Nullable
    public final ConnectionError getConnectionError() {
        return null;
    }

    public final long getConnectionStageChangeTimeStamp() {
        return this.g;
    }

    @Nullable
    public final CloveBleState.BleState getConnectionState() {
        return this.d;
    }

    @Nullable
    public final EABleFeatures getDevSupfeatures() {
        return this.k;
    }

    @Nullable
    public final EastApexBaseReq getKhCurrentCommand() {
        return this.h;
    }

    @Nullable
    public final BluetoothDevice getMBluetoothDevice() {
        return this.f;
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
        return this.f3711a;
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(@Nullable Intent intent) {
        return getServiceBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        a();
        Object systemService = getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        BluetoothManager bluetoothManager = (BluetoothManager) systemService;
        this.b = bluetoothManager;
        Intrinsics.checkNotNull(bluetoothManager);
        this.c = bluetoothManager.getAdapter();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogHelper.d(this.f3711a, "service on destroy");
        unRegisterReceivers();
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        if (intent == null) {
            try {
                Intent intent2 = new Intent(this, EastapexBleService.class);
                if (Build.VERSION.SDK_INT >= 26) {
                    startForegroundService(intent2);
                } else {
                    startService(intent2);
                }
                return 3;
            } catch (Exception e) {
                e.printStackTrace();
                BleApiUtils.checkExceptionAndShowNotification(e, this);
                LogHelper.d(this.f3711a, "service on StartCommand");
            }
        }
        a();
        String connectedDeviceMacAddress = PreferenceManagerAbstract.getInstance(getApplicationContext()).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(applicationC…connectedDeviceMacAddress");
        setMacAddress(connectedDeviceMacAddress);
        BleUtils.getConnectionType(getApplicationContext());
        registerReceivers();
        EABleConnectState deviceConnectState = EABleManager.getInstance().getDeviceConnectState();
        if ((!BleUtils.isEmpty(getMacAddress()) && deviceConnectState == EABleConnectState.STATE_IDLE) || deviceConnectState == EABleConnectState.STATE_DISCONNECT) {
            EABleManager.getInstance().connectToPeripheral(getMacAddress(), this, new ConnectionLister(), 128, new DataReportListener(), new MotionDataReportListener());
        }
        return 3;
    }

    public final void reConnection() {
        Handler handler = this.i;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.u0
                @Override // java.lang.Runnable
                public final void run() {
                    EastapexBleService.a(EastapexBleService.this);
                }
            }, com.clevertap.android.sdk.Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
        }
    }

    public void registerReceivers() {
        try {
            BleEventBusManager.getInstance().getEventBus().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            getApplicationContext().registerReceiver(this.l, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
            getApplicationContext().registerReceiver(this.l, new IntentFilter("action_stop_service"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void restartService() {
        PreferenceManagerAbstract.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
        EABleManager.getInstance().disconnectPeripheral();
        unRegisterReceivers();
        stopForeground(true);
        stopSelf();
    }

    /* JADX WARN: Removed duplicated region for block: B:183:0x021e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x01f4 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v14, types: [T, com.example.custom_dial.RGBAPlatformDiffTxtUtils] */
    /* JADX WARN: Type inference failed for: r0v18, types: [com.example.custom_dial.RGBAPointUtils, T] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void sendRequest(@org.jetbrains.annotations.NotNull com.coveiot.android.eastapexsdk.api.EastApexBaseReq r10, @org.jetbrains.annotations.NotNull com.coveiot.android.eastapexsdk.EastApexResponseListener r11) {
        /*
            Method dump skipped, instructions count: 1111
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.services.EastapexBleService.sendRequest(com.coveiot.android.eastapexsdk.api.EastApexBaseReq, com.coveiot.android.eastapexsdk.EastApexResponseListener):void");
    }

    public final void setDevSupfeatures(@Nullable EABleFeatures eABleFeatures) {
        this.k = eABleFeatures;
    }

    public final void setKhCurrentCommand(@Nullable EastApexBaseReq eastApexBaseReq) {
        this.h = eastApexBaseReq;
    }

    public final void setMBluetoothDevice(@Nullable BluetoothDevice bluetoothDevice) {
        this.f = bluetoothDevice;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.macAddress = str;
    }

    public final void setServiceBinder(@NotNull IBinder iBinder) {
        Intrinsics.checkNotNullParameter(iBinder, "<set-?>");
        this.serviceBinder = iBinder;
    }

    public void stopService() {
        disconnect();
    }

    public void stopServiceRetainMacAddress() {
        disconnectAndRetainMacAddress();
    }

    public void unRegisterReceivers() {
        if (this.l != null) {
            try {
                getApplicationContext().unregisterReceiver(this.l);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                BleEventBusManager.getInstance().getEventBus().unregister(this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public final void updateConnectionState(@NotNull final CloveBleState.BleState state, boolean z) {
        Handler handler;
        Intrinsics.checkNotNullParameter(state, "state");
        this.d = state;
        if (!z || (handler = this.e) == null) {
            return;
        }
        Intrinsics.checkNotNull(handler);
        handler.removeCallbacksAndMessages(null);
        Handler handler2 = this.e;
        Intrinsics.checkNotNull(handler2);
        handler2.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.v0
            @Override // java.lang.Runnable
            public final void run() {
                EastapexBleService.a(CloveBleState.BleState.this);
            }
        });
    }

    public static final void a(CloveBleState.BleState state) {
        Intrinsics.checkNotNullParameter(state, "$state");
        BleEventBusManager.getInstance().getEventBus().post(new CloveBleState(state));
    }

    public static final void a(EastapexBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String macAddress = PreferenceManagerAbstract.getInstance(this$0.getApplicationContext()).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(macAddress, "macAddress");
        this$0.connect(macAddress);
    }

    public static final void a(EastapexBleService this$0, Ref.ObjectRef rgbaPointUtils, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(rgbaPointUtils, "$rgbaPointUtils");
        if (str != null) {
            ArrayList arrayList = new ArrayList();
            EABleOta eABleOta = new EABleOta();
            eABleOta.setOtaType(EABleOta.OtaType.user_wf);
            eABleOta.setFilePath(str);
            eABleOta.setPop(true);
            arrayList.add(eABleOta);
            EABleManager.getInstance().otaUpdate(arrayList, this$0.M);
        } else {
            LogHelper.d(this$0.f3711a, "EastApexCustomWatchFaceReq-> custome watch face dial path is null", ModuleNames.BLEABSTRACT.getModuleName());
            this$0.a("Invalid Filepath");
        }
        RGBAPointUtils rGBAPointUtils = (RGBAPointUtils) rgbaPointUtils.element;
        if (rGBAPointUtils != null) {
            rGBAPointUtils.destroy();
        }
        rgbaPointUtils.element = null;
    }

    public final void a(String str) {
        EastApexBaseReq eastApexBaseReq = this.h;
        if (eastApexBaseReq != null) {
            Intrinsics.checkNotNull(eastApexBaseReq);
            EastApexResponseListener responseListener = eastApexBaseReq.getResponseListener();
            Intrinsics.checkNotNull(responseListener);
            EastApexErrorType eastApexErrorType = EastApexErrorType.COMMAND_RESPONSE_ERROR;
            responseListener.onFailure(new EastApexError(eastApexErrorType, "Command Failure " + str));
            String str2 = this.f3711a;
            LogHelper.i(str2, "sendCommandFailure->Set khCurrentCommand to null->" + this.h + " Command Failure " + str);
            this.h = null;
        }
    }

    public final void a(List<? extends EABleContact> list, int i, final Function1<? super Boolean, Unit> function1, final Function1<? super Integer, Unit> function12) {
        EABleManager.getInstance().addBookList(list, i, new GeneralCallback() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$synContacts$1
            @Override // com.apex.bluetooth.callback.EABleCallback
            public void mutualFail(int i2) {
                function12.invoke(Integer.valueOf(i2));
            }

            @Override // com.apex.bluetooth.callback.GeneralCallback
            public void result(boolean z) {
                function1.invoke(Boolean.valueOf(z));
            }
        });
    }

    public final void a(EastApexSyncContactsReq eastApexSyncContactsReq) {
        List<EABleContact> contactsList = eastApexSyncContactsReq.getContactsList();
        Intrinsics.checkNotNull(contactsList, "null cannot be cast to non-null type kotlin.collections.MutableList<com.apex.bluetooth.model.EABleContact>");
        final List asMutableList = TypeIntrinsics.asMutableList(contactsList);
        final Ref.IntRef intRef = new Ref.IntRef();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        IntProgression step = kotlin.ranges.h.step(kotlin.ranges.h.until(0, asMutableList.size()), 10);
        int first = step.getFirst();
        int last = step.getLast();
        int step2 = step.getStep();
        if ((step2 <= 0 || first > last) && (step2 >= 0 || last > first)) {
            return;
        }
        boolean z = true;
        while (true) {
            List<? extends EABleContact> slice = CollectionsKt___CollectionsKt.slice(asMutableList, kotlin.ranges.h.until(first, kotlin.ranges.h.coerceAtMost(first + 10, asMutableList.size())));
            intRef.element += slice.size();
            if (z) {
                a(slice, 0, new Function1<Boolean, Unit>() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$sendContactsInBatch$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public Unit invoke(Boolean bool) {
                        boolean booleanValue = bool.booleanValue();
                        String tag = EastapexBleService.this.getTAG();
                        LogHelper.d(tag, "syncContactListener->contactsSent in batch " + intRef.element + "-> ==  " + booleanValue, ModuleNames.BLEABSTRACT.getModuleName());
                        return Unit.INSTANCE;
                    }
                }, new Function1<Integer, Unit>() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$sendContactsInBatch$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public Unit invoke(Integer num) {
                        int intValue = num.intValue();
                        String tag = EastapexBleService.this.getTAG();
                        LogHelper.d(tag, "syncContactListener->mutualFail-> " + intValue, ModuleNames.BLEABSTRACT.getModuleName());
                        EastapexBleService.this.a(String.valueOf(intValue));
                        booleanRef.element = true;
                        return Unit.INSTANCE;
                    }
                });
                if (booleanRef.element) {
                    return;
                }
            } else {
                a(slice, 1, new Function1<Boolean, Unit>() { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$sendContactsInBatch$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public Unit invoke(Boolean bool) {
                        boolean booleanValue = bool.booleanValue();
                        String tag = EastapexBleService.this.getTAG();
                        LogHelper.d(tag, "syncContactListener->contactsSent in batch " + intRef.element + "-> ==  " + booleanValue, ModuleNames.BLEABSTRACT.getModuleName());
                        if (intRef.element >= asMutableList.size() && EastapexBleService.this.getKhCurrentCommand() != null && (EastapexBleService.this.getKhCurrentCommand() instanceof EastApexSyncContactsReq)) {
                            EastApexBaseRes eastApexBaseRes = new EastApexBaseRes();
                            EastApexBaseReq khCurrentCommand = EastapexBleService.this.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand);
                            eastApexBaseRes.setBaseReq(khCurrentCommand);
                            eastApexBaseRes.setObj(Boolean.valueOf(booleanValue));
                            EastApexBaseReq khCurrentCommand2 = EastapexBleService.this.getKhCurrentCommand();
                            Intrinsics.checkNotNull(khCurrentCommand2);
                            EastApexResponseListener responseListener = khCurrentCommand2.getResponseListener();
                            Intrinsics.checkNotNull(responseListener);
                            responseListener.onResponse(eastApexBaseRes);
                            EastapexBleService.this.setKhCurrentCommand(null);
                        }
                        return Unit.INSTANCE;
                    }
                }, new Function1<Integer, Unit>(booleanRef) { // from class: com.coveiot.android.bleabstract.services.EastapexBleService$sendContactsInBatch$4
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public Unit invoke(Integer num) {
                        int intValue = num.intValue();
                        String tag = EastapexBleService.this.getTAG();
                        LogHelper.d(tag, "syncContactListener->mutualFail-> " + intValue, ModuleNames.BLEABSTRACT.getModuleName());
                        EastapexBleService.this.a(String.valueOf(intValue));
                        return Unit.INSTANCE;
                    }
                });
                if (booleanRef.element) {
                    return;
                }
            }
            if (first == last) {
                return;
            }
            first += step2;
            z = false;
        }
    }
}
