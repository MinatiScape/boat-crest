package com.coveiot.android.bleabstract.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.formatter.CRPFormatter;
import com.coveiot.android.bleabstract.models.CloveCRPBleState;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerCRP;
import com.coveiot.android.bleabstract.response.CallRejectRes;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.response.LiveMusicControlRes;
import com.coveiot.android.bleabstract.response.MusicControlState;
import com.coveiot.android.crpsdk.BleConnectHelper;
import com.coveiot.android.crpsdk.BleConnectStateChangeEvent;
import com.coveiot.android.crpsdk.CRPResponseListener;
import com.coveiot.android.crpsdk.api.CRPBaseReq;
import com.coveiot.android.crpsdk.api.CRPBaseRes;
import com.coveiot.android.crpsdk.api.CRPBatteryLevelReq;
import com.coveiot.android.crpsdk.api.CRPCancelFirmwareUpgradeReq;
import com.coveiot.android.crpsdk.api.CRPCancelWatchFaceBackgroundUploadReq;
import com.coveiot.android.crpsdk.api.CRPCancelWatchFaceUploadReq;
import com.coveiot.android.crpsdk.api.CRPChangeWatchFaceReq;
import com.coveiot.android.crpsdk.api.CRPFirmwareUpgradeReq;
import com.coveiot.android.crpsdk.api.CRPGetAlarmsReq;
import com.coveiot.android.crpsdk.api.CRPGetCustomWatchFaceLayoutReq;
import com.coveiot.android.crpsdk.api.CRPGetFirmwareVersionReq;
import com.coveiot.android.crpsdk.api.CRPGetPastHeartRateDataReq;
import com.coveiot.android.crpsdk.api.CRPGetPastSleepDataReq;
import com.coveiot.android.crpsdk.api.CRPGetPastStepCategoryDataReq;
import com.coveiot.android.crpsdk.api.CRPGetPastStepDataReq;
import com.coveiot.android.crpsdk.api.CRPGetSPO2HistoryDataReq;
import com.coveiot.android.crpsdk.api.CRPGetSessionHRDataReq;
import com.coveiot.android.crpsdk.api.CRPGetTodayHeartRateDataReq;
import com.coveiot.android.crpsdk.api.CRPGetTodaySPO2DataReq;
import com.coveiot.android.crpsdk.api.CRPGetTodaySleepDataReq;
import com.coveiot.android.crpsdk.api.CRPGetTodayStepCategoryDataReq;
import com.coveiot.android.crpsdk.api.CRPGetTodayStepDataReq;
import com.coveiot.android.crpsdk.api.CRPGetWatchFacePositionRequest;
import com.coveiot.android.crpsdk.api.CRPGetWorkoutDataReq;
import com.coveiot.android.crpsdk.api.CRPGetYesterdaySPO2DataReq;
import com.coveiot.android.crpsdk.api.CRPGetYesterdaySleepDataReq;
import com.coveiot.android.crpsdk.api.CRPGetYesterdayStepDataReq;
import com.coveiot.android.crpsdk.api.CRPPushNotificationReq;
import com.coveiot.android.crpsdk.api.CRPSendCustomWatchFaceLayoutChangeReq;
import com.coveiot.android.crpsdk.api.CRPSendWatchFaceBackgroundReq;
import com.coveiot.android.crpsdk.api.CRPSendWatchFaceReq;
import com.coveiot.android.crpsdk.api.CRPSetCRPPhysiologcalPeriodReq;
import com.coveiot.android.crpsdk.api.CRPSetDNDReq;
import com.coveiot.android.crpsdk.api.CRPSetDistanceUnitReq;
import com.coveiot.android.crpsdk.api.CRPSetFutureWeatherReq;
import com.coveiot.android.crpsdk.api.CRPSetHeartRateIntervalReq;
import com.coveiot.android.crpsdk.api.CRPSetHourSystemReq;
import com.coveiot.android.crpsdk.api.CRPSetLiveHeartRateReq;
import com.coveiot.android.crpsdk.api.CRPSetLiveStepReq;
import com.coveiot.android.crpsdk.api.CRPSetMusicPlaybackStateChangedReq;
import com.coveiot.android.crpsdk.api.CRPSetMusicVolumeReq;
import com.coveiot.android.crpsdk.api.CRPSetQuickViewReq;
import com.coveiot.android.crpsdk.api.CRPSetScreenTimeOutReq;
import com.coveiot.android.crpsdk.api.CRPSetSedentaryReminderTimeReq;
import com.coveiot.android.crpsdk.api.CRPSetSongTitleReq;
import com.coveiot.android.crpsdk.api.CRPSetStepGoalReq;
import com.coveiot.android.crpsdk.api.CRPSetTodayWeatherReq;
import com.coveiot.android.crpsdk.api.CRPSetUserProfileReq;
import com.coveiot.android.crpsdk.api.CRPSetVibrationAlarmReq;
import com.coveiot.android.crpsdk.api.CRPStopNotificationReq;
import com.coveiot.android.crpsdk.eventbus.CRPBleEventBusManager;
import com.coveiot.android.crpsdk.events.CRPResponseEvent;
import com.coveiot.android.crpsdk.events.CRPResponseStatus;
import com.coveiot.android.crpsdk.events.CRPResponseType;
import com.coveiot.sdk.ble.api.model.CameraState;
import com.coveiot.sdk.ble.api.response.CameraEventRes;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.model.BleDeviceInfo;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.crrepa.ble.conn.CRPBleConnection;
import com.crrepa.ble.conn.bean.CRPMessageInfo;
import com.crrepa.ble.conn.bean.CRPStepsCategoryInfo;
import com.crrepa.ble.conn.callback.CRPDeviceAlarmClockCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDfuStatusCallback;
import com.crrepa.ble.conn.callback.CRPDeviceFirmwareVersionCallback;
import com.crrepa.ble.conn.callback.CRPDeviceTimingMeasureHeartRateCallback;
import com.crrepa.ble.conn.listener.CRPBloodOxygenChangeListener;
import com.crrepa.ble.conn.listener.CRPCameraOperationListener;
import com.crrepa.ble.conn.listener.CRPStepsCategoryChangeListener;
import com.crrepa.ble.conn.type.CRPBloodOxygenTimeType;
import com.crrepa.ble.conn.type.CRPHistoryDynamicRateType;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class CRPBaseBleService extends Service {
    @NotNull
    public final CRPBaseBleService$crpWatchFaceLayoutListener$1 A;
    @NotNull
    public final CRPBaseBleService$crpDisplayWatchFaceListener$1 B;
    @NotNull
    public final CRPBloodOxygenChangeListener C;
    @NotNull
    public final BroadcastReceiver D;
    @NotNull
    public final Runnable E;
    @NotNull
    public final Runnable F;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public BluetoothDevice f3696a;
    @Nullable
    public BluetoothManager d;
    @Nullable
    public CRPBaseReq e;
    public boolean f;
    public String macAddress;
    public IBinder serviceBinder;
    @NotNull
    public final CRPBaseBleService$crpPhoneOperationListener$1 x;
    @NotNull
    public final CRPCameraOperationListener y;
    @NotNull
    public final CRPBaseBleService$crpFirmwareUpgradeListener$1 z;
    @Nullable
    public Handler b = new Handler();
    @NotNull
    public Handler c = new Handler();
    public long g = -1;
    @NotNull
    public final Handler h = new Handler();
    @NotNull
    public final Handler i = new Handler();
    public final long j = Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS;
    public final long k = Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS;
    public final long l = 1000;
    public final String m = CRPBaseBleService.class.getSimpleName();
    @NotNull
    public CloveCRPBleState.BleState n = CloveCRPBleState.BleState.DISCONNECTED;
    @NotNull
    public final CRPBaseBleService$crpDeviceBatteryListener$1 o = new CRPBaseBleService$crpDeviceBatteryListener$1(this);
    @NotNull
    public final CRPDeviceFirmwareVersionCallback p = new CRPDeviceFirmwareVersionCallback() { // from class: com.coveiot.android.bleabstract.services.n
        @Override // com.crrepa.ble.conn.callback.CRPDeviceFirmwareVersionCallback
        public final void onDeviceFirmwareVersion(String str) {
            CRPBaseBleService.a(CRPBaseBleService.this, str);
        }
    };
    @NotNull
    public final CRPDeviceAlarmClockCallback q = new CRPDeviceAlarmClockCallback() { // from class: com.coveiot.android.bleabstract.services.a
        @Override // com.crrepa.ble.conn.callback.CRPDeviceAlarmClockCallback
        public final void onAlarmClock(List list) {
            CRPBaseBleService.a(CRPBaseBleService.this, list);
        }
    };
    @NotNull
    public final CRPBaseBleService$crpFileTransListener$1 r = new CRPBaseBleService$crpFileTransListener$1(this);
    @NotNull
    public final CRPBaseBleService$crpWatchfaceBackgroundFileTransListener$1 s = new CRPBaseBleService$crpWatchfaceBackgroundFileTransListener$1(this);
    @NotNull
    public final CRPBaseBleService$crpHeartRateChangeListener$1 t = new CRPBaseBleService$crpHeartRateChangeListener$1(this);
    @NotNull
    public final CRPBaseBleService$crpSleepChangeListener$1 u = new CRPBaseBleService$crpSleepChangeListener$1(this);
    @NotNull
    public final CRPBaseBleService$crpDeviceStepChangeListener$1 v = new CRPBaseBleService$crpDeviceStepChangeListener$1(this);
    @NotNull
    public final CRPStepsCategoryChangeListener w = new CRPStepsCategoryChangeListener() { // from class: com.coveiot.android.bleabstract.services.q
        @Override // com.crrepa.ble.conn.listener.CRPStepsCategoryChangeListener
        public final void onStepsCategoryChange(CRPStepsCategoryInfo cRPStepsCategoryInfo) {
            CRPBaseBleService.a(CRPBaseBleService.this, cRPStepsCategoryInfo);
        }
    };

    public CRPBaseBleService() {
        new CRPDeviceTimingMeasureHeartRateCallback() { // from class: com.coveiot.android.bleabstract.services.o
            @Override // com.crrepa.ble.conn.callback.CRPDeviceTimingMeasureHeartRateCallback
            public final void onTimingMeasure(int i) {
                CRPBaseBleService.a(CRPBaseBleService.this, i);
            }
        };
        this.x = new CRPBaseBleService$crpPhoneOperationListener$1(this);
        this.y = new CRPCameraOperationListener() { // from class: com.coveiot.android.bleabstract.services.p
            @Override // com.crrepa.ble.conn.listener.CRPCameraOperationListener
            public final void onTakePhoto() {
                CRPBaseBleService.a(CRPBaseBleService.this);
            }
        };
        this.z = new CRPBaseBleService$crpFirmwareUpgradeListener$1(this);
        this.A = new CRPBaseBleService$crpWatchFaceLayoutListener$1(this);
        this.B = new CRPBaseBleService$crpDisplayWatchFaceListener$1(this);
        this.C = new CRPBaseBleService$crpBloodOxygenChangeListener$1(this);
        this.D = new BroadcastReceiver() { // from class: com.coveiot.android.bleabstract.services.CRPBaseBleService$mBluetoothStatusReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(@NotNull Context context, @NotNull Intent intent) {
                String str;
                String str2;
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                if (Intrinsics.areEqual(intent.getAction(), "android.bluetooth.adapter.action.STATE_CHANGED")) {
                    int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                    if (intExtra == 12) {
                        str = CRPBaseBleService.this.m;
                        LogHelper.d(str, "Bluetooth state BluetoothAdapter.STATE_ON", ModuleNames.BLEABSTRACT.getModuleName());
                        BleConnectHelper.getInstance(CRPBaseBleService.this.getApplicationContext()).delayConnect();
                    } else if (intExtra != 13) {
                    } else {
                        str2 = CRPBaseBleService.this.m;
                        LogHelper.d(str2, "Bluetooth state BluetoothAdapter.STATE_OFF", ModuleNames.BLEABSTRACT.getModuleName());
                        BleConnectHelper.getInstance(CRPBaseBleService.this.getApplicationContext()).disconnect(false);
                    }
                }
            }
        };
        this.E = new Runnable() { // from class: com.coveiot.android.bleabstract.services.c
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService.e(CRPBaseBleService.this);
            }
        };
        this.F = new Runnable() { // from class: com.coveiot.android.bleabstract.services.f
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService.h(CRPBaseBleService.this);
            }
        };
    }

    public static final void a(final CRPBaseBleService this$0, final String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.i
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService.b(CRPBaseBleService.this, str);
            }
        });
    }

    public static final void access$updateMusicActionChangeToAppLayer(CRPBaseBleService cRPBaseBleService, int i) {
        cRPBaseBleService.getClass();
        MusicControlState musicControlState = MusicControlState.UNKNOWN;
        if (i == 1) {
            musicControlState = MusicControlState.PREVIOUS;
        } else if (i == 2) {
            musicControlState = MusicControlState.NEXT;
        } else if (i == 4) {
            musicControlState = MusicControlState.VOLUME_UP;
        } else if (i == 5) {
            musicControlState = MusicControlState.VOLUME_DOWN;
        } else if (i == 6) {
            musicControlState = MusicControlState.PLAY;
            CRPBleConnection bleConnection = BleConnectHelper.getInstance(cRPBaseBleService.getApplicationContext()).getBleConnection();
            if (bleConnection != null) {
                bleConnection.setMusicPlayerState((byte) 1);
            }
        } else if (i == 7) {
            musicControlState = MusicControlState.PAUSE;
            CRPBleConnection bleConnection2 = BleConnectHelper.getInstance(cRPBaseBleService.getApplicationContext()).getBleConnection();
            if (bleConnection2 != null) {
                bleConnection2.setMusicPlayerState((byte) 0);
            }
        }
        Intent intent = new Intent(com.coveiot.android.bleabstract.Constants.MUSIC_CONTROL_BROADCAST_INTENT);
        intent.putExtra(com.coveiot.android.bleabstract.Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(musicControlState));
        LocalBroadcastManager.getInstance(cRPBaseBleService).sendBroadcast(intent);
    }

    public static final void b(CRPBaseBleService this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.m;
        LogHelper.d(str2, "crpDeviceFirmwareVersionListener->onDeviceFirmwareVersion-> " + str + ", khCurrentCommand " + this$0.e, ModuleNames.BLEABSTRACT.getModuleName());
        CRPBaseReq cRPBaseReq = this$0.e;
        if (cRPBaseReq == null || !(cRPBaseReq instanceof CRPGetFirmwareVersionReq)) {
            return;
        }
        CRPBaseRes cRPBaseRes = new CRPBaseRes();
        CRPBaseReq cRPBaseReq2 = this$0.e;
        Intrinsics.checkNotNull(cRPBaseReq2);
        cRPBaseRes.setBaseReq(cRPBaseReq2);
        if (str != null) {
            cRPBaseRes.setObj(str);
        }
        CRPBaseReq cRPBaseReq3 = this$0.e;
        Intrinsics.checkNotNull(cRPBaseReq3);
        CRPResponseListener responseListener = cRPBaseReq3.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(cRPBaseRes);
        this$0.e = null;
    }

    public static final void c(CRPBaseBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.stopForeground(true);
        this$0.stopSelf();
    }

    public static final void d(CRPBaseBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.stopForeground(true);
        this$0.stopSelf();
    }

    public static final void e(final CRPBaseBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = this$0.m;
        LogHelper.d(str, "Dynamic Heart Rate Command TimeOut,Failed " + System.currentTimeMillis(), ModuleNames.BLEABSTRACT.getModuleName());
        this$0.c.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.d
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService.f(CRPBaseBleService.this);
            }
        });
    }

    public static final void f(CRPBaseBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleConnectHelper.getInstance(this$0.getApplicationContext()).getBleConnection() != null) {
            CRPBleConnection bleConnection = BleConnectHelper.getInstance(this$0.getApplicationContext()).getBleConnection();
            Intrinsics.checkNotNull(bleConnection);
            bleConnection.queryMovementHeartRate();
        }
    }

    public static final void g(final CRPBaseBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CRPBleConnection bleConnection = BleConnectHelper.getInstance(this$0.getApplicationContext()).getBleConnection();
        if (bleConnection != null) {
            bleConnection.queryDeviceDfuStatus(new CRPDeviceDfuStatusCallback() { // from class: com.coveiot.android.bleabstract.services.l
                @Override // com.crrepa.ble.conn.callback.CRPDeviceDfuStatusCallback
                public final void onDeviceDfuStatus(int i) {
                    CRPBaseBleService.b(CRPBaseBleService.this, i);
                }
            });
        }
    }

    public static final void h(final CRPBaseBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = this$0.m;
        LogHelper.d(str, "SPO2 Command TimeOut,Failed " + System.currentTimeMillis(), ModuleNames.BLEABSTRACT.getModuleName());
        this$0.c.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.g
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService.i(CRPBaseBleService.this);
            }
        });
    }

    public static final void i(CRPBaseBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CRPBaseReq cRPBaseReq = this$0.e;
        if (cRPBaseReq == null || !(cRPBaseReq instanceof CRPGetSPO2HistoryDataReq)) {
            return;
        }
        CRPBaseRes cRPBaseRes = new CRPBaseRes();
        CRPBaseReq cRPBaseReq2 = this$0.e;
        Intrinsics.checkNotNull(cRPBaseReq2);
        cRPBaseRes.setBaseReq(cRPBaseReq2);
        CRPBaseReq cRPBaseReq3 = this$0.e;
        Intrinsics.checkNotNull(cRPBaseReq3);
        CRPResponseListener responseListener = cRPBaseReq3.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(cRPBaseRes);
        this$0.e = null;
    }

    public final void connect(@NotNull String deviceAddress) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        BleDeviceInfo.clearInstance();
        PreferenceManagerCRP.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress(deviceAddress);
        PreferenceManagerCRP.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
        String connectedDeviceMacAddress = PreferenceManagerCRP.getInstance(getApplicationContext()).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(applicationC…connectedDeviceMacAddress");
        setMacAddress(connectedDeviceMacAddress);
        String str = this.m;
        LogHelper.d(str, "connection type ++ " + BleUtils.getConnectionType(this), ModuleNames.BLEABSTRACT.getModuleName());
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            BleConnectHelper.getInstance(getApplicationContext()).establishConnection(deviceAddress);
        }
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
        LogHelper.d(this.m, "disconnectAndForget called", ModuleNames.BLEABSTRACT.getModuleName());
        PreferenceManagerCRP.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress("");
        PreferenceManagerCRP.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        BleConnectHelper.getInstance(getApplicationContext()).disconnect(false);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.t
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService.c(CRPBaseBleService.this);
            }
        }, this.l);
    }

    public void disconnectAndRetainMacAddress() {
        PreferenceManagerCRP.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        BleConnectHelper.getInstance(getApplicationContext()).disconnect(false);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.b
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService.d(CRPBaseBleService.this);
            }
        }, this.l);
    }

    @Nullable
    public final ConnectionError getConnectionError() {
        return null;
    }

    public final long getConnectionStageChangeTimeStamp() {
        return this.g;
    }

    @Nullable
    public final CloveCRPBleState.BleState getConnectionState() {
        if (BleConnectHelper.getInstance(getApplicationContext()).isConnected()) {
            return CloveCRPBleState.BleState.CONNECTED;
        }
        if (BleConnectHelper.getInstance(getApplicationContext()).isConnecting()) {
            return CloveCRPBleState.BleState.CONNECTING;
        }
        return CloveCRPBleState.BleState.DISCONNECTED;
    }

    @NotNull
    public final CRPBloodOxygenChangeListener getCrpBloodOxygenChangeListener() {
        return this.C;
    }

    @Nullable
    public final CRPBaseReq getKhCurrentCommand() {
        return this.e;
    }

    @Nullable
    public final BluetoothDevice getMBluetoothDevice() {
        return this.f3696a;
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

    @NotNull
    public final CloveCRPBleState.BleState getState() {
        return this.n;
    }

    public final boolean isRemoteCameraOpened() {
        return this.f;
    }

    @Override // android.app.Service
    @NotNull
    public IBinder onBind(@NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return getServiceBinder();
    }

    public final void onConnectionStateChangedHandler(@NotNull BleConnectStateChangeEvent bleConnectionStateChangeEvent) {
        Intrinsics.checkNotNullParameter(bleConnectionStateChangeEvent, "bleConnectionStateChangeEvent");
        bleConnectionStateChangeEvent.getState();
        this.g = System.currentTimeMillis();
        CloveCRPBleState.BleState bleState = CloveCRPBleState.BleState.DISCONNECTED;
        this.n = bleState;
        int state = bleConnectionStateChangeEvent.getState();
        if (state == 0) {
            updateConnectionState(bleState, true);
        } else if (state == 1) {
            updateConnectionState(CloveCRPBleState.BleState.CONNECTING, true);
        } else if (state != 2) {
        } else {
            this.c.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.e
                @Override // java.lang.Runnable
                public final void run() {
                    CRPBaseBleService.g(CRPBaseBleService.this);
                }
            });
            updateConnectionState(CloveCRPBleState.BleState.CONNECTED, true);
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        LogHelper.d(this.m, "onCreate called", ModuleNames.BLEABSTRACT.getModuleName());
        createForegroundNotification();
        Object systemService = getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        BluetoothManager bluetoothManager = (BluetoothManager) systemService;
        this.d = bluetoothManager;
        Intrinsics.checkNotNull(bluetoothManager);
        bluetoothManager.getAdapter();
        CRPBleEventBusManager.getInstance().getEventBus().register(this);
        try {
            getApplicationContext().registerReceiver(this.D, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        LogHelper.d(this.m, "onDestroy", ModuleNames.BLEABSTRACT.getModuleName());
        BleConnectHelper.getInstance(getApplicationContext()).disconnect(false);
        try {
            CRPBleEventBusManager.getInstance().getEventBus().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            getApplicationContext().unregisterReceiver(this.D);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        LogHelper.d(this.m, "onStartCommand", ModuleNames.BLEABSTRACT.getModuleName());
        if (intent == null) {
            try {
                Intent intent2 = new Intent(this, CRPBaseBleService.class);
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
        String connectedDeviceMacAddress = PreferenceManagerCRP.getInstance(getApplicationContext()).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(applicationC…connectedDeviceMacAddress");
        setMacAddress(connectedDeviceMacAddress);
        BleUtils.getConnectionType(getApplicationContext());
        if (!BleUtils.isEmpty(getMacAddress())) {
            String str = this.m;
            LogHelper.d(str, "initBT -> Obtained device Address is " + getMacAddress(), ModuleNames.BLEABSTRACT.getModuleName());
            connect(getMacAddress());
        }
        return 3;
    }

    @Override // android.app.Service
    public boolean onUnbind(@Nullable Intent intent) {
        return super.onUnbind(intent);
    }

    public void restartService() {
        LogHelper.d(this.m, "restartService called", ModuleNames.BLEABSTRACT.getModuleName());
        this.c.removeCallbacksAndMessages(null);
        stopForeground(true);
        stopSelf();
    }

    public final void sendRequest(@NotNull final CRPBaseReq crpBaseReq, @NotNull final CRPResponseListener responseListener) {
        Intrinsics.checkNotNullParameter(crpBaseReq, "crpBaseReq");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        CRPBleConnection bleConnection = BleConnectHelper.getInstance(getApplicationContext()).getBleConnection();
        if (bleConnection != null) {
            bleConnection.queryDeviceDfuStatus(new CRPDeviceDfuStatusCallback() { // from class: com.coveiot.android.bleabstract.services.m
                @Override // com.crrepa.ble.conn.callback.CRPDeviceDfuStatusCallback
                public final void onDeviceDfuStatus(int i) {
                    CRPBaseBleService.a(CRPBaseBleService.this, crpBaseReq, responseListener, i);
                }
            });
        }
    }

    public final void setKhCurrentCommand(@Nullable CRPBaseReq cRPBaseReq) {
        this.e = cRPBaseReq;
    }

    public final void setMBluetoothDevice(@Nullable BluetoothDevice bluetoothDevice) {
        this.f3696a = bluetoothDevice;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.macAddress = str;
    }

    public final void setRemoteCameraOpened(boolean z) {
        this.f = z;
    }

    public final void setServiceBinder(@NotNull IBinder iBinder) {
        Intrinsics.checkNotNullParameter(iBinder, "<set-?>");
        this.serviceBinder = iBinder;
    }

    public final void setState(@NotNull CloveCRPBleState.BleState bleState) {
        Intrinsics.checkNotNullParameter(bleState, "<set-?>");
        this.n = bleState;
    }

    public void stopService() {
        disconnectAndForget();
    }

    public void stopServiceRetainMacAddress() {
        disconnectAndRetainMacAddress();
    }

    public final void updateConnectionState(@NotNull final CloveCRPBleState.BleState state, boolean z) {
        Handler handler;
        Intrinsics.checkNotNullParameter(state, "state");
        if (!z || (handler = this.b) == null) {
            return;
        }
        Intrinsics.checkNotNull(handler);
        handler.removeCallbacksAndMessages(null);
        Handler handler2 = this.b;
        Intrinsics.checkNotNull(handler2);
        handler2.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.r
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService.a(CloveCRPBleState.BleState.this);
            }
        });
    }

    public static final void a(final CRPBaseBleService this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.j
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService.b(CRPBaseBleService.this, list);
            }
        });
    }

    public static final void a(final CRPBaseBleService this$0, final CRPStepsCategoryInfo cRPStepsCategoryInfo) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.h
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService.b(CRPBaseBleService.this, cRPStepsCategoryInfo);
            }
        });
    }

    public static final void a(CRPBaseBleService this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = this$0.m;
        LogHelper.d(str, "crpDeviceTimingMeasureHeartRateCallback->onTimingMeasure-> " + i, ModuleNames.BLEABSTRACT.getModuleName());
    }

    public static final void a(final CRPBaseBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.s
            @Override // java.lang.Runnable
            public final void run() {
                CRPBaseBleService.b(CRPBaseBleService.this);
            }
        });
    }

    public static final void a(CloveCRPBleState.BleState state) {
        Intrinsics.checkNotNullParameter(state, "$state");
        BleEventBusManager.getInstance().getEventBus().post(new CloveCRPBleState(state));
    }

    public static final void b(CRPBaseBleService this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str = this$0.m;
        LogHelper.d(str, "crpDeviceAlarmClockCallback->onAlarmClock-> " + list + " , khCurrentCommand " + this$0.e, ModuleNames.BLEABSTRACT.getModuleName());
        CRPBaseReq cRPBaseReq = this$0.e;
        if (cRPBaseReq == null || !(cRPBaseReq instanceof CRPGetAlarmsReq)) {
            return;
        }
        CRPBaseRes cRPBaseRes = new CRPBaseRes();
        CRPBaseReq cRPBaseReq2 = this$0.e;
        Intrinsics.checkNotNull(cRPBaseReq2);
        cRPBaseRes.setBaseReq(cRPBaseReq2);
        if (list != null) {
            cRPBaseRes.setObj(list);
        }
        CRPBaseReq cRPBaseReq3 = this$0.e;
        Intrinsics.checkNotNull(cRPBaseReq3);
        CRPResponseListener responseListener = cRPBaseReq3.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(cRPBaseRes);
        this$0.e = null;
    }

    public final void a() {
        CRPBaseRes cRPBaseRes = new CRPBaseRes();
        CRPBaseReq cRPBaseReq = this.e;
        Intrinsics.checkNotNull(cRPBaseReq);
        cRPBaseRes.setBaseReq(cRPBaseReq);
        CRPBaseReq cRPBaseReq2 = this.e;
        Intrinsics.checkNotNull(cRPBaseReq2);
        CRPResponseListener responseListener = cRPBaseReq2.getResponseListener();
        Intrinsics.checkNotNull(responseListener);
        responseListener.onResponse(cRPBaseRes);
        String str = this.m;
        LogHelper.d(str, "sendCommandReply->Set khCurrentCommand to null->" + this.e, ModuleNames.BLEABSTRACT.getModuleName());
        this.e = null;
    }

    public static final void a(final CRPBaseBleService this$0, final CRPBaseReq crpBaseReq, final CRPResponseListener responseListener, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(crpBaseReq, "$crpBaseReq");
        Intrinsics.checkNotNullParameter(responseListener, "$responseListener");
        if (1 != i) {
            this$0.c.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.k
                @Override // java.lang.Runnable
                public final void run() {
                    CRPBaseBleService.a(CRPBaseReq.this, responseListener, this$0);
                }
            });
        } else {
            this$0.a();
        }
    }

    public static final void a(CRPBaseReq crpBaseReq, CRPResponseListener responseListener, CRPBaseBleService this$0) {
        Intrinsics.checkNotNullParameter(crpBaseReq, "$crpBaseReq");
        Intrinsics.checkNotNullParameter(responseListener, "$responseListener");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        crpBaseReq.setResponseListener(responseListener);
        this$0.e = crpBaseReq;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.d(this$0.m + " khCurrentCommand", " sendRequest--  " + crpBaseReq, moduleNames.getModuleName());
        CRPBleConnection bleConnection = BleConnectHelper.getInstance(this$0.getApplicationContext()).getBleConnection();
        if (bleConnection != null) {
            if (crpBaseReq instanceof CRPBatteryLevelReq) {
                bleConnection.setDeviceBatteryListener(this$0.o);
                bleConnection.queryDeviceBattery();
            } else if (crpBaseReq instanceof CRPGetFirmwareVersionReq) {
                bleConnection.queryFrimwareVersion(this$0.p);
            } else if (crpBaseReq instanceof CRPSetUserProfileReq) {
                CRPSetUserProfileReq cRPSetUserProfileReq = (CRPSetUserProfileReq) crpBaseReq;
                bleConnection.sendUserInfo(cRPSetUserProfileReq.getUserProfile());
                if (cRPSetUserProfileReq.getStepLength() > 0) {
                    bleConnection.sendStepLength(cRPSetUserProfileReq.getStepLength());
                }
                this$0.a();
            } else if (crpBaseReq instanceof CRPSetStepGoalReq) {
                bleConnection.sendGoalSteps(((CRPSetStepGoalReq) crpBaseReq).getGoal());
                this$0.a();
            } else if (crpBaseReq instanceof CRPSetSedentaryReminderTimeReq) {
                CRPSetSedentaryReminderTimeReq cRPSetSedentaryReminderTimeReq = (CRPSetSedentaryReminderTimeReq) crpBaseReq;
                bleConnection.sendSedentaryReminder(cRPSetSedentaryReminderTimeReq.isOn());
                bleConnection.sendSedentaryReminderPeriod(cRPSetSedentaryReminderTimeReq.getSedentaryReminderPeriodInfo());
                this$0.a();
            } else if (crpBaseReq instanceof CRPSetHourSystemReq) {
                bleConnection.sendTimeSystem(((CRPSetHourSystemReq) crpBaseReq).getCrpTimeSystem());
                this$0.a();
            } else if (crpBaseReq instanceof CRPSetDistanceUnitReq) {
                bleConnection.sendMetricSystem(((CRPSetDistanceUnitReq) crpBaseReq).getCrpMetricSystemType());
                this$0.a();
            } else if (crpBaseReq instanceof CRPSetHeartRateIntervalReq) {
                String str = this$0.m;
                StringBuilder sb = new StringBuilder();
                sb.append("CRPSetHeartRateIntervalReq->interval ");
                CRPSetHeartRateIntervalReq cRPSetHeartRateIntervalReq = (CRPSetHeartRateIntervalReq) crpBaseReq;
                sb.append(cRPSetHeartRateIntervalReq.getInterval());
                sb.append(", enabled ");
                sb.append(cRPSetHeartRateIntervalReq.isEnabled());
                LogHelper.d(str, sb.toString(), moduleNames.getModuleName());
                if (cRPSetHeartRateIntervalReq.isEnabled()) {
                    bleConnection.enableTimingMeasureHeartRate(cRPSetHeartRateIntervalReq.getInterval());
                } else {
                    bleConnection.disableTimingMeasureHeartRate();
                }
                this$0.a();
            } else if (crpBaseReq instanceof CRPSetQuickViewReq) {
                CRPSetQuickViewReq cRPSetQuickViewReq = (CRPSetQuickViewReq) crpBaseReq;
                bleConnection.sendQuickView(cRPSetQuickViewReq.isOn());
                if (cRPSetQuickViewReq.getCRPPeriodTimeInfo() != null) {
                    bleConnection.sendQuickViewTime(cRPSetQuickViewReq.getCRPPeriodTimeInfo());
                }
                this$0.a();
            } else if (crpBaseReq instanceof CRPSetDNDReq) {
                bleConnection.sendDoNotDistrubTime(((CRPSetDNDReq) crpBaseReq).getCrpPeriodTimeInfo());
                this$0.a();
            } else if (crpBaseReq instanceof CRPSetVibrationAlarmReq) {
                bleConnection.sendAlarmClock(((CRPSetVibrationAlarmReq) crpBaseReq).getCrpAlarmClockInfo());
                this$0.a();
            } else if (crpBaseReq instanceof CRPGetAlarmsReq) {
                bleConnection.queryAllAlarmClock(this$0.q);
            } else if (crpBaseReq instanceof CRPPushNotificationReq) {
                CRPPushNotificationReq cRPPushNotificationReq = (CRPPushNotificationReq) crpBaseReq;
                CRPMessageInfo crpMessageInfo = cRPPushNotificationReq.getCrpMessageInfo();
                Intrinsics.checkNotNull(crpMessageInfo);
                if (crpMessageInfo.getType() == 128) {
                    bleConnection.sendOtherMessageState(true);
                } else {
                    bleConnection.sendOtherMessageState(false);
                }
                bleConnection.sendMessage(cRPPushNotificationReq.getCrpMessageInfo());
                this$0.a();
            } else if (crpBaseReq instanceof CRPStopNotificationReq) {
                bleConnection.sendCall0ffHook();
                this$0.a();
            } else if (crpBaseReq instanceof CRPSetTodayWeatherReq) {
                bleConnection.sendTodayWeather(((CRPSetTodayWeatherReq) crpBaseReq).getCrpTodayWeatherInfo());
                this$0.a();
            } else if (crpBaseReq instanceof CRPSetFutureWeatherReq) {
                bleConnection.sendFutureWeather(((CRPSetFutureWeatherReq) crpBaseReq).getCrpFutureWeatherInfo());
                this$0.a();
            } else if (crpBaseReq instanceof CRPSendWatchFaceReq) {
                bleConnection.sendWatchFace(((CRPSendWatchFaceReq) crpBaseReq).getCrpCustomizeWatchFaceInfo(), this$0.r, 30);
            } else if (crpBaseReq instanceof CRPSendWatchFaceBackgroundReq) {
                bleConnection.sendWatchFaceBackground(((CRPSendWatchFaceBackgroundReq) crpBaseReq).getCrpWatchFaceBackgroundInfo(), this$0.s);
            } else if (crpBaseReq instanceof CRPChangeWatchFaceReq) {
                bleConnection.sendDisplayWatchFace((byte) ((CRPChangeWatchFaceReq) crpBaseReq).getWatchFacePosition());
                this$0.a();
            } else if (crpBaseReq instanceof CRPGetTodayHeartRateDataReq) {
                bleConnection.queryTodayHeartRate(1);
            } else if (crpBaseReq instanceof CRPGetPastHeartRateDataReq) {
                bleConnection.queryPastHeartRate();
            } else if (crpBaseReq instanceof CRPGetTodaySleepDataReq) {
                bleConnection.syncRemSleep();
            } else if (crpBaseReq instanceof CRPGetYesterdaySleepDataReq) {
                bleConnection.syncPastSleep((byte) 3);
            } else if (crpBaseReq instanceof CRPGetPastSleepDataReq) {
                bleConnection.syncPastSleep((byte) 4);
            } else if (crpBaseReq instanceof CRPGetTodayStepDataReq) {
                bleConnection.syncStep();
            } else if (crpBaseReq instanceof CRPGetYesterdayStepDataReq) {
                bleConnection.syncPastStep((byte) 1);
            } else if (crpBaseReq instanceof CRPGetPastStepDataReq) {
                bleConnection.syncPastStep((byte) 2);
            } else if (crpBaseReq instanceof CRPGetTodayStepCategoryDataReq) {
                bleConnection.queryStepsCategory(0);
            } else if (crpBaseReq instanceof CRPGetPastStepCategoryDataReq) {
                bleConnection.queryStepsCategory(2);
            } else if (crpBaseReq instanceof CRPGetSessionHRDataReq) {
                bleConnection.queryLastDynamicRate(CRPHistoryDynamicRateType.FIRST_HEART_RATE);
                CRPBaseReq cRPBaseReq = this$0.e;
                if (cRPBaseReq == null || !(cRPBaseReq instanceof CRPGetSessionHRDataReq)) {
                    return;
                }
                CRPBaseRes cRPBaseRes = new CRPBaseRes();
                CRPBaseReq cRPBaseReq2 = this$0.e;
                Intrinsics.checkNotNull(cRPBaseReq2);
                cRPBaseRes.setBaseReq(cRPBaseReq2);
                CRPBaseReq cRPBaseReq3 = this$0.e;
                Intrinsics.checkNotNull(cRPBaseReq3);
                CRPResponseListener responseListener2 = cRPBaseReq3.getResponseListener();
                Intrinsics.checkNotNull(responseListener2);
                responseListener2.onResponse(cRPBaseRes);
                this$0.e = null;
            } else if (crpBaseReq instanceof CRPGetWorkoutDataReq) {
                LogHelper.d(this$0.m, "Workout Data Fetch Starts At " + System.currentTimeMillis(), moduleNames.getModuleName());
                bleConnection.queryLastDynamicRate(CRPHistoryDynamicRateType.FIRST_HEART_RATE);
                this$0.h.postDelayed(this$0.E, this$0.j);
            } else if (crpBaseReq instanceof CRPSetLiveStepReq) {
                this$0.a();
            } else if (crpBaseReq instanceof CRPSetLiveHeartRateReq) {
                this$0.a();
            } else if (crpBaseReq instanceof CRPFirmwareUpgradeReq) {
                CRPFirmwareUpgradeReq cRPFirmwareUpgradeReq = (CRPFirmwareUpgradeReq) crpBaseReq;
                bleConnection.startFirmwareUpgrade(true, cRPFirmwareUpgradeReq.getFile(), cRPFirmwareUpgradeReq.getCurrentVersion(), this$0.z);
            } else if (crpBaseReq instanceof CRPSetCRPPhysiologcalPeriodReq) {
                bleConnection.sendPhysiologcalPeriod(((CRPSetCRPPhysiologcalPeriodReq) crpBaseReq).getCRPPhysiologcalPeriodInfo());
                this$0.a();
            } else if (crpBaseReq instanceof CRPGetCustomWatchFaceLayoutReq) {
                bleConnection.queryWatchFaceLayout(this$0.A);
            } else if (crpBaseReq instanceof CRPSendCustomWatchFaceLayoutChangeReq) {
                bleConnection.sendWatchFaceLayout(((CRPSendCustomWatchFaceLayoutChangeReq) crpBaseReq).getCrpWatchFaceLayoutInfo());
                this$0.a();
            } else if (crpBaseReq instanceof CRPGetWatchFacePositionRequest) {
                bleConnection.queryDisplayWatchFace(this$0.B);
            } else if (crpBaseReq instanceof CRPCancelWatchFaceBackgroundUploadReq) {
                bleConnection.abortWatchFaceBackground();
                this$0.a();
            } else if (crpBaseReq instanceof CRPCancelWatchFaceUploadReq) {
                bleConnection.abortWatchFace();
                this$0.a();
            } else if (crpBaseReq instanceof CRPCancelFirmwareUpgradeReq) {
                bleConnection.abortFirmwareUpgrade();
                this$0.a();
            } else if (crpBaseReq instanceof CRPGetTodaySPO2DataReq) {
                bleConnection.queryTimingBloodOxygen(CRPBloodOxygenTimeType.TODAY);
            } else if (crpBaseReq instanceof CRPGetYesterdaySPO2DataReq) {
                bleConnection.queryTimingBloodOxygen(CRPBloodOxygenTimeType.YESTERDAY);
            } else if (crpBaseReq instanceof CRPGetSPO2HistoryDataReq) {
                bleConnection.queryHistoryBloodOxygen();
                this$0.i.postDelayed(this$0.F, this$0.k);
            } else if (crpBaseReq instanceof CRPSetScreenTimeOutReq) {
                bleConnection.sendDisplayTime(((CRPSetScreenTimeOutReq) crpBaseReq).getTimeout());
                this$0.a();
            } else if (crpBaseReq instanceof CRPSetSongTitleReq) {
                String str2 = this$0.m;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("CRPSetSongTitleReq->");
                CRPSetSongTitleReq cRPSetSongTitleReq = (CRPSetSongTitleReq) crpBaseReq;
                sb2.append(cRPSetSongTitleReq.getTitle());
                LogHelper.d(str2, sb2.toString(), moduleNames.getModuleName());
                bleConnection.sendSongTitle(cRPSetSongTitleReq.getTitle());
                this$0.a();
            } else if (crpBaseReq instanceof CRPSetMusicPlaybackStateChangedReq) {
                String str3 = this$0.m;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("CRPSetMusicPlaybackStateChangedReq->");
                CRPSetMusicPlaybackStateChangedReq cRPSetMusicPlaybackStateChangedReq = (CRPSetMusicPlaybackStateChangedReq) crpBaseReq;
                sb3.append((int) cRPSetMusicPlaybackStateChangedReq.getCrpMusicPlayerStateType());
                LogHelper.d(str3, sb3.toString(), moduleNames.getModuleName());
                bleConnection.setMusicPlayerState(cRPSetMusicPlaybackStateChangedReq.getCrpMusicPlayerStateType());
                this$0.a();
            } else if (crpBaseReq instanceof CRPSetMusicVolumeReq) {
                String str4 = this$0.m;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("CRPSetMusicVolumeReq->");
                CRPSetMusicVolumeReq cRPSetMusicVolumeReq = (CRPSetMusicVolumeReq) crpBaseReq;
                sb4.append(cRPSetMusicVolumeReq.getMaxVolume());
                sb4.append(", ");
                sb4.append(cRPSetMusicVolumeReq.getVolume());
                LogHelper.d(str4, sb4.toString(), moduleNames.getModuleName());
                if (cRPSetMusicVolumeReq.getMaxVolume() > 0) {
                    bleConnection.sendMaxVolume(cRPSetMusicVolumeReq.getMaxVolume());
                }
                bleConnection.sendCurrentVolume(cRPSetMusicVolumeReq.getVolume());
                this$0.a();
            }
        }
    }

    public static final void b(CRPBaseBleService this$0, CRPStepsCategoryInfo cRPStepsCategoryInfo) {
        List<Integer> stepsList;
        boolean z;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.d(this$0.m, "crpDeviceStepCategoryChangeListener->onStepsCategoryChange-> " + cRPStepsCategoryInfo + " , khCurrentCommand " + this$0.e, ModuleNames.BLEABSTRACT.getModuleName());
        CRPBaseReq cRPBaseReq = this$0.e;
        if (cRPBaseReq != null) {
            if ((cRPBaseReq instanceof CRPGetTodayStepCategoryDataReq) || (cRPBaseReq instanceof CRPGetPastStepCategoryDataReq)) {
                CRPBaseRes cRPBaseRes = new CRPBaseRes();
                CRPBaseReq cRPBaseReq2 = this$0.e;
                Intrinsics.checkNotNull(cRPBaseReq2);
                cRPBaseRes.setBaseReq(cRPBaseReq2);
                if (cRPStepsCategoryInfo != null && (stepsList = cRPStepsCategoryInfo.getStepsList()) != null) {
                    Intrinsics.checkNotNullExpressionValue(stepsList, "stepsList");
                    List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) stepsList);
                    int i = 1;
                    if (!mutableList.isEmpty()) {
                        Iterator it = mutableList.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                z = true;
                                break;
                            }
                            Integer num = (Integer) it.next();
                            if (num != null && num.intValue() > 0) {
                                z = false;
                                break;
                            }
                        }
                        if (!z) {
                            if (this$0.e instanceof CRPGetTodayStepCategoryDataReq) {
                                LogHelper.d(this$0.m, "crpDeviceStepCategoryChangeListener->onStepsCategoryChange-> Sync Completed", ModuleNames.BLEABSTRACT.getModuleName());
                            } else {
                                i = 0;
                            }
                            CRPStepsCategoryInfo cRPStepsCategoryInfo2 = new CRPStepsCategoryInfo(cRPStepsCategoryInfo.getDateType(), cRPStepsCategoryInfo.getTimeInterval(), CollectionsKt___CollectionsKt.toMutableList((Collection) stepsList));
                            CRPFormatter.Companion companion = CRPFormatter.Companion;
                            Context applicationContext = this$0.getApplicationContext();
                            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                            cRPBaseRes.setObj(companion.getInstance(applicationContext).convertStepData(cRPStepsCategoryInfo2, i));
                        }
                    }
                }
                CRPBaseReq cRPBaseReq3 = this$0.e;
                Intrinsics.checkNotNull(cRPBaseReq3);
                CRPResponseListener responseListener = cRPBaseReq3.getResponseListener();
                Intrinsics.checkNotNull(responseListener);
                responseListener.onResponse(cRPBaseRes);
                this$0.e = null;
            }
        }
    }

    public static final void b(CRPBaseBleService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.d(this$0.m, "crpCameraOperationListener->onTakePhoto", ModuleNames.BLEABSTRACT.getModuleName());
        BleEventBusManager.getInstance().getEventBus().post(new CRPResponseEvent(CRPResponseType.CAMERA_CLICK, new CameraEventRes(CameraState.CAPTURE), CRPResponseStatus.RESPONSE_STATUS_SUCCESS));
    }

    public final void b() {
        Intent intent = new Intent(com.coveiot.android.bleabstract.Constants.CALL_REJECT_BROADCAST_INTENT);
        intent.putExtra(com.coveiot.android.bleabstract.Constants.CALL_REJECT__BROADCAST_INTENT_EXTRA, new CallRejectRes(true));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public static final void b(CRPBaseBleService this$0, int i) {
        CRPBleConnection bleConnection;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CRPBleConnection bleConnection2 = BleConnectHelper.getInstance(this$0.getApplicationContext()).getBleConnection();
        if (bleConnection2 != null) {
            bleConnection2.setHeartRateChangeListener(this$0.t);
            bleConnection2.setSleepChangeListener(this$0.u);
            bleConnection2.setStepChangeListener(this$0.v);
            bleConnection2.setStepsCategoryListener(this$0.w);
            bleConnection2.setPhoneOperationListener(this$0.x);
            bleConnection2.setCameraOperationListener(this$0.y);
            bleConnection2.setBloodOxygenChangeListener(this$0.C);
        }
        if (1 == i || (bleConnection = BleConnectHelper.getInstance(this$0.getApplicationContext()).getBleConnection()) == null) {
            return;
        }
        bleConnection.syncTime();
    }
}
