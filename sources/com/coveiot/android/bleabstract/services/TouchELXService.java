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
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.formatter.touch.TouchActivityFormatter;
import com.coveiot.android.bleabstract.formatter.touch.TouchHeartRateFormatter;
import com.coveiot.android.bleabstract.formatter.touch.TouchSPO2Formatter;
import com.coveiot.android.bleabstract.formatter.touch.TouchSleepFormatter;
import com.coveiot.android.bleabstract.formatter.touch.TouchStressFormatter;
import com.coveiot.android.bleabstract.listeners.TGConnectCallback;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.response.CallRejectRes;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.LiveMusicControlRes;
import com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage;
import com.coveiot.android.bleabstract.response.MusicControlState;
import com.coveiot.android.bleabstract.response.PeriodicSpo2Response;
import com.coveiot.android.bleabstract.response.ReadManualSpo2Response;
import com.coveiot.android.bleabstract.response.StressResponse;
import com.coveiot.android.bleabstract.utils.touchUtils.TouchDeviceManager;
import com.coveiot.android.touchsdk.TouchELXResponseListener;
import com.coveiot.android.touchsdk.api.TouchActivityDataReq;
import com.coveiot.android.touchsdk.api.TouchAlarmReq;
import com.coveiot.android.touchsdk.api.TouchBatteryLevelReq;
import com.coveiot.android.touchsdk.api.TouchCameraControlReq;
import com.coveiot.android.touchsdk.api.TouchCloudWatchFaceReq;
import com.coveiot.android.touchsdk.api.TouchDIYWatchFaceReq;
import com.coveiot.android.touchsdk.api.TouchDefaultWatchFaceReq;
import com.coveiot.android.touchsdk.api.TouchDeviceInfoReq;
import com.coveiot.android.touchsdk.api.TouchDrinkReminderReq;
import com.coveiot.android.touchsdk.api.TouchELXBaseReq;
import com.coveiot.android.touchsdk.api.TouchELXBaseRes;
import com.coveiot.android.touchsdk.api.TouchEventReminderReq;
import com.coveiot.android.touchsdk.api.TouchFemaleWellnessConfigReq;
import com.coveiot.android.touchsdk.api.TouchFindDeviceReq;
import com.coveiot.android.touchsdk.api.TouchGetAlarmReq;
import com.coveiot.android.touchsdk.api.TouchGetDNDReq;
import com.coveiot.android.touchsdk.api.TouchGetEventReminderReq;
import com.coveiot.android.touchsdk.api.TouchGetLiftWristReq;
import com.coveiot.android.touchsdk.api.TouchGetQuickCardReq;
import com.coveiot.android.touchsdk.api.TouchGetWorkoutModesReq;
import com.coveiot.android.touchsdk.api.TouchHeartRateIntervalReq;
import com.coveiot.android.touchsdk.api.TouchHeartRateReq;
import com.coveiot.android.touchsdk.api.TouchLiftWristReq;
import com.coveiot.android.touchsdk.api.TouchManualSpo2Req;
import com.coveiot.android.touchsdk.api.TouchMusicMetaDataReq;
import com.coveiot.android.touchsdk.api.TouchQuickReplyReq;
import com.coveiot.android.touchsdk.api.TouchSPO2IntervalReq;
import com.coveiot.android.touchsdk.api.TouchSedentaryReminderReq;
import com.coveiot.android.touchsdk.api.TouchSetCallNotificationReq;
import com.coveiot.android.touchsdk.api.TouchSetCallStatusReq;
import com.coveiot.android.touchsdk.api.TouchSetDNDReq;
import com.coveiot.android.touchsdk.api.TouchSetGoalReq;
import com.coveiot.android.touchsdk.api.TouchSetMsgNotificationReq;
import com.coveiot.android.touchsdk.api.TouchSetQuickCardsReq;
import com.coveiot.android.touchsdk.api.TouchSetUnitReq;
import com.coveiot.android.touchsdk.api.TouchSetWeatherConfigReq;
import com.coveiot.android.touchsdk.api.TouchSetWeatherUnitReq;
import com.coveiot.android.touchsdk.api.TouchSetWorkoutModesReq;
import com.coveiot.android.touchsdk.api.TouchSleepReq;
import com.coveiot.android.touchsdk.api.TouchSpo2Req;
import com.coveiot.android.touchsdk.api.TouchStepsReq;
import com.coveiot.android.touchsdk.api.TouchStressIntervalReq;
import com.coveiot.android.touchsdk.api.TouchStressReq;
import com.coveiot.android.touchsdk.api.TouchSyncContactsReq;
import com.coveiot.android.touchsdk.api.TouchUserInfoReq;
import com.coveiot.android.touchsdk.error.TouchELXError;
import com.coveiot.android.touchsdk.error.TouchELXErrorType;
import com.coveiot.khtouchdb.activities.EntityTGWorkoutRecord;
import com.coveiot.khtouchdb.activities.KHTGActivityRepository;
import com.coveiot.khtouchdb.heartrate.EntityTGHeartRateData;
import com.coveiot.khtouchdb.heartrate.KHTGHeartRateRepository;
import com.coveiot.khtouchdb.sleep.EntityTGSleepData;
import com.coveiot.khtouchdb.sleep.KHTGSleepRepository;
import com.coveiot.khtouchdb.spo2.EntityTGSpO2Data;
import com.coveiot.khtouchdb.spo2.KHTGSpO2Repository;
import com.coveiot.khtouchdb.stress.EntityTGStressData;
import com.coveiot.khtouchdb.stress.KHTGStressRepository;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.api.model.FindMyPhoneState;
import com.coveiot.sdk.ble.api.response.FindMyPhoneRes;
import com.coveiot.sdk.ble.api.response.SendQuickReplyRes;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.sdk.ble.model.BleDeviceInfo;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.touchgui.sdk.TGDialManager;
import com.touchgui.sdk.TGEventListener;
import com.touchgui.sdk.TGHealthDataCallback;
import com.touchgui.sdk.TGWorkoutDataCallback;
import com.touchgui.sdk.bean.TGAlarm;
import com.touchgui.sdk.bean.TGBatteryInfo;
import com.touchgui.sdk.bean.TGEventReminder;
import com.touchgui.sdk.bean.TGHeartRateData;
import com.touchgui.sdk.bean.TGNotDisturbConfig;
import com.touchgui.sdk.bean.TGQuickCard;
import com.touchgui.sdk.bean.TGQuickReply;
import com.touchgui.sdk.bean.TGRaiseWristConfig;
import com.touchgui.sdk.bean.TGSleepData;
import com.touchgui.sdk.bean.TGStepData;
import com.touchgui.sdk.bean.TGStressData;
import com.touchgui.sdk.bean.TGSyncSpo2;
import com.touchgui.sdk.bean.TGVersionInfo;
import com.touchgui.sdk.bean.TGWorkoutRecord;
import com.touchgui.sdk.bean.TGWorkoutSupportList;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class TouchELXService extends Service {
    @Nullable
    public Disposable A;
    @Nullable
    public Disposable B;
    @Nullable
    public Disposable C;
    @Nullable
    public Disposable D;
    @Nullable
    public Disposable E;
    @Nullable
    public Disposable F;
    @Nullable
    public Disposable G;
    @Nullable
    public Disposable H;
    @Nullable
    public Disposable I;
    @Nullable
    public Disposable J;
    @Nullable
    public Disposable K;
    @Nullable
    public TouchDeviceManager L;
    public boolean M;
    @NotNull
    public final TGConnectCallback N;
    @NotNull
    public final BroadcastReceiver O;
    @NotNull
    public final TGHealthDataCallback P;
    @NotNull
    public final TGWorkoutDataCallback Q;
    @NotNull
    public final TGEventListener R;
    @NotNull
    public final TGDialManager.OnSyncDialListener S;
    @Nullable
    public BluetoothManager b;
    @Nullable
    public BluetoothAdapter c;
    @Nullable
    public BluetoothDevice f;
    public long g;
    @Nullable
    public TouchELXBaseReq h;
    @Nullable
    public Disposable i;
    @Nullable
    public Disposable j;
    @Nullable
    public Disposable k;
    @Nullable
    public Disposable l;
    @Nullable
    public Disposable m;
    public String macAddress;
    @Nullable
    public Disposable n;
    @Nullable
    public Disposable o;
    @Nullable
    public Disposable p;
    @Nullable
    public Disposable q;
    @Nullable
    public Disposable r;
    @Nullable
    public Disposable s;
    public IBinder serviceBinder;
    @Nullable
    public Disposable t;
    @Nullable
    public Disposable u;
    @Nullable
    public Disposable v;
    @Nullable
    public Disposable w;
    @Nullable
    public Disposable x;
    @Nullable
    public Disposable y;
    @Nullable
    public Disposable z;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f3935a = "TouchELXService";
    @Nullable
    public CloveBleState.BleState d = CloveBleState.BleState.DISCONNECTED;
    @Nullable
    public Handler e = new Handler();

    /* loaded from: classes2.dex */
    public final class BtServiceBinder extends Binder {
        public BtServiceBinder() {
        }

        @NotNull
        public final TouchELXService getService() {
            return TouchELXService.this;
        }
    }

    public TouchELXService() {
        new Handler();
        this.g = -1L;
        setServiceBinder(new BtServiceBinder());
        this.N = new TouchELXService$connectCallback$1(this);
        this.O = new BroadcastReceiver() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$mBluetoothStatusReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(@NotNull Context context, @NotNull Intent intent) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                String action = intent.getAction();
                if (Intrinsics.areEqual(action, "android.bluetooth.adapter.action.STATE_CHANGED")) {
                    int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                    if (intExtra == 3 || intExtra == 10) {
                        LogHelper.d(TouchELXService.this.getTAG(), "Bluetooth state BluetoothAdapter.STATE_OFF", ModuleNames.BLEABSTRACT.getModuleName());
                        TouchELXService.access$disconnect(TouchELXService.this, false);
                        TouchELXService.this.updateConnectionState(CloveBleState.BleState.DISCONNECTED, true);
                    } else if (intExtra != 12) {
                    } else {
                        LogHelper.d(TouchELXService.this.getTAG(), "Bluetooth state BluetoothAdapter.STATE_ON", ModuleNames.BLEABSTRACT.getModuleName());
                        if (PreferenceManagerAbstract.getInstance(TouchELXService.this.getApplicationContext()).getConnectionType().equals(ConnectionType.RECONNECT_ON_DISCONNECT.name())) {
                            TouchELXService.this.c();
                        }
                    }
                } else if (kotlin.text.m.equals(action, "action_stop_service", true)) {
                    TouchELXService.this.updateConnectionState(CloveBleState.BleState.DISCONNECTED, true);
                }
            }
        };
        this.P = new TGHealthDataCallback() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$onHealthDataListener$1
            @Override // com.touchgui.sdk.TGHealthDataCallback
            public void onCompleted() {
                try {
                    LogHelper.d(TouchELXService.this.getTAG(), "TGHealthDataCallback->onCompleted");
                    kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new TouchELXService$onHealthDataListener$1$onCompleted$1(TouchELXService.this, null), 2, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.touchgui.sdk.TGHealthDataCallback
            public void onError(int i, @NotNull String p1) {
                Intrinsics.checkNotNullParameter(p1, "p1");
            }

            @Override // com.touchgui.sdk.TGHealthDataCallback
            public void onHealthData(@NotNull Object data) {
                Intrinsics.checkNotNullParameter(data, "data");
                if (data instanceof TGStepData) {
                    LogHelper.d("onHealthData steps", data.toString());
                    kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new TouchELXService$onHealthDataListener$1$onHealthData$1(TouchELXService.this, data, null), 2, null);
                } else if (data instanceof TGSleepData) {
                    LogHelper.d("onHealthData sleep", data.toString());
                    kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new TouchELXService$onHealthDataListener$1$onHealthData$2(TouchELXService.this, data, null), 2, null);
                } else if (data instanceof TGHeartRateData) {
                    LogHelper.d("onHealthData HeartRate", data.toString());
                    kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new TouchELXService$onHealthDataListener$1$onHealthData$3(TouchELXService.this, data, null), 2, null);
                } else if (data instanceof TGSyncSpo2) {
                    LogHelper.d("onHealthData SPO2", data.toString());
                    kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new TouchELXService$onHealthDataListener$1$onHealthData$4(TouchELXService.this, data, null), 2, null);
                } else if (data instanceof TGStressData) {
                    LogHelper.d("onHealthData Stress", data.toString());
                    kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new TouchELXService$onHealthDataListener$1$onHealthData$5(TouchELXService.this, data, null), 2, null);
                }
            }

            @Override // com.touchgui.sdk.TGHealthDataCallback
            public void onProgress(int i) {
            }

            @Override // com.touchgui.sdk.TGHealthDataCallback
            public void onStart() {
            }
        };
        this.Q = new TGWorkoutDataCallback() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$workoutDataCallback$1
            @Override // com.touchgui.sdk.TGWorkoutDataCallback
            public void onCompleted(@NotNull List<TGWorkoutRecord> data) {
                TouchELXResponseListener responseListener;
                TouchELXResponseListener responseListener2;
                Intrinsics.checkNotNullParameter(data, "data");
                for (TGWorkoutRecord tGWorkoutRecord : data) {
                    LogHelper.d("TGWorkoutDataCallback", new Gson().toJson(tGWorkoutRecord));
                    EntityTGWorkoutRecord convertTGWorkoutRecordToEntity = TouchActivityFormatter.Companion.getInstance(TouchELXService.this).convertTGWorkoutRecordToEntity(tGWorkoutRecord);
                    if (convertTGWorkoutRecordToEntity != null) {
                        KHTGActivityRepository.Companion.getInstance(TouchELXService.this).insertActivityData(convertTGWorkoutRecordToEntity);
                    }
                }
                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchActivityDataReq)) {
                    TouchELXBaseRes touchELXBaseRes = new TouchELXBaseRes();
                    TouchELXBaseReq khCurrentCommand = TouchELXService.this.getKhCurrentCommand();
                    Intrinsics.checkNotNull(khCurrentCommand);
                    touchELXBaseRes.setBaseReq(khCurrentCommand);
                    List<EntityTGWorkoutRecord> allUnProcessedActivityData = KHTGActivityRepository.Companion.getInstance(TouchELXService.this).getAllUnProcessedActivityData(TouchELXService.this.getMacAddress());
                    if (!(allUnProcessedActivityData == null || allUnProcessedActivityData.isEmpty())) {
                        int size = allUnProcessedActivityData.size();
                        int i = 0;
                        while (i < size) {
                            touchELXBaseRes.setObj(TouchActivityFormatter.Companion.getInstance(TouchELXService.this).getActivityModeSummaryData(allUnProcessedActivityData.get(i)));
                            touchELXBaseRes.setComplete(i == allUnProcessedActivityData.size() - 1);
                            TouchELXBaseReq khCurrentCommand2 = TouchELXService.this.getKhCurrentCommand();
                            if (khCurrentCommand2 != null && (responseListener2 = khCurrentCommand2.getResponseListener()) != null) {
                                responseListener2.onResponse(touchELXBaseRes);
                            }
                            touchELXBaseRes.isComplete();
                            i++;
                        }
                        return;
                    }
                    TouchELXBaseReq khCurrentCommand3 = TouchELXService.this.getKhCurrentCommand();
                    if (khCurrentCommand3 == null || (responseListener = khCurrentCommand3.getResponseListener()) == null) {
                        return;
                    }
                    responseListener.onResponse(touchELXBaseRes);
                    return;
                }
                LogsHelper.d(TouchELXService.this.getTAG(), "khCurrent command is null");
            }

            @Override // com.touchgui.sdk.TGWorkoutDataCallback
            public void onError(int i, @NotNull String p1) {
                Intrinsics.checkNotNullParameter(p1, "p1");
                String tag = TouchELXService.this.getTAG();
                LogHelper.d(tag, "TGWorkoutDataCallback onError " + i + ' ' + p1);
            }

            @Override // com.touchgui.sdk.TGWorkoutDataCallback
            public void onProgress(int i) {
            }

            @Override // com.touchgui.sdk.TGWorkoutDataCallback
            public void onStart() {
            }
        };
        this.R = new TGEventListener() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$eventListener$1
            @Override // com.touchgui.sdk.TGEventListener
            public void onEvent(int i) {
                String tag = TouchELXService.this.getTAG();
                LogHelper.d(tag, "TGEventListener onEvent -- " + i);
                new LiveMusicControlRes(MusicControlState.UNKNOWN);
                Intent intent = new Intent(Constants.MUSIC_CONTROL_BROADCAST_INTENT);
                if (i == 1) {
                    intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.PLAY));
                    LocalBroadcastManager.getInstance(TouchELXService.this).sendBroadcast(intent);
                } else if (i == 2) {
                    intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.PAUSE));
                    LocalBroadcastManager.getInstance(TouchELXService.this).sendBroadcast(intent);
                } else if (i == 4) {
                    intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.PREVIOUS));
                    LocalBroadcastManager.getInstance(TouchELXService.this).sendBroadcast(intent);
                } else if (i == 5) {
                    intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.NEXT));
                    LocalBroadcastManager.getInstance(TouchELXService.this).sendBroadcast(intent);
                } else if (i == 8) {
                    intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.VOLUME_UP));
                    LocalBroadcastManager.getInstance(TouchELXService.this).sendBroadcast(intent);
                } else if (i == 9) {
                    intent.putExtra(Constants.MUSIC_CONTROL_BROADCAST_INTENT_EXTRA, new LiveMusicControlRes(MusicControlState.VOLUME_DOWN));
                    LocalBroadcastManager.getInstance(TouchELXService.this).sendBroadcast(intent);
                } else if (i == 13) {
                    CallRejectRes callRejectRes = new CallRejectRes(true);
                    callRejectRes.shouldReject = true;
                    Intent intent2 = new Intent(Constants.CALL_REJECT_BROADCAST_INTENT);
                    intent2.putExtra(Constants.CALL_REJECT__BROADCAST_INTENT_EXTRA, callRejectRes);
                    LocalBroadcastManager.getInstance(TouchELXService.this).sendBroadcast(intent2);
                } else if (i == 14) {
                    LocalBroadcastManager.getInstance(TouchELXService.this).sendBroadcast(new Intent(Constants.CALL_MUTE_BROADCAST_INTENT));
                } else if (i != 257) {
                } else {
                    Intent intent3 = new Intent(Constants.FACTORY_RESET_BROADCAST_INTENT);
                    intent3.putExtra(Constants.FACTORY_RESET_BROADCAST_INTENT_EXTRA, true);
                    LocalBroadcastManager.getInstance(TouchELXService.this).sendBroadcast(intent3);
                }
            }

            @Override // com.touchgui.sdk.TGEventListener
            public void onFindPhone(int i) {
                if (i == 0) {
                    FindMyPhoneRes findMyPhoneRes = new FindMyPhoneRes(FindMyPhoneState.ON);
                    Intent intent = new Intent(Constants.FIND_MY_PHONE_BROADCAST_INTENT);
                    intent.putExtra(Constants.FIND_MY_PHONE_BROADCAST_INTENT_EXTRA, findMyPhoneRes);
                    LocalBroadcastManager.getInstance(TouchELXService.this).sendBroadcast(intent);
                } else if (i != 1) {
                } else {
                    FindMyPhoneRes findMyPhoneRes2 = new FindMyPhoneRes(FindMyPhoneState.OFF);
                    Intent intent2 = new Intent(Constants.FIND_MY_PHONE_BROADCAST_INTENT);
                    intent2.putExtra(Constants.FIND_MY_PHONE_BROADCAST_INTENT_EXTRA, findMyPhoneRes2);
                    LocalBroadcastManager.getInstance(TouchELXService.this).sendBroadcast(intent2);
                }
            }

            @Override // com.touchgui.sdk.TGEventListener
            public void onQuickReply(@Nullable TGQuickReply tGQuickReply) {
                String tag = TouchELXService.this.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("TGEventListener onQuickReply -- ");
                sb.append(tGQuickReply != null ? tGQuickReply.getContent() : null);
                sb.append(" -- ");
                Integer valueOf = tGQuickReply != null ? Integer.valueOf(tGQuickReply.getMsgIndex()) : null;
                Intrinsics.checkNotNull(valueOf);
                sb.append(valueOf.intValue() - 1);
                sb.append(", -- ");
                sb.append(tGQuickReply != null ? Integer.valueOf(tGQuickReply.getMsgType()) : null);
                LogHelper.i(tag, sb.toString());
                if (tGQuickReply != null) {
                    tGQuickReply.getMsgIndex();
                    SendQuickReplyRes sendQuickReplyRes = new SendQuickReplyRes(tGQuickReply.getMsgIndex() - 1, tGQuickReply.getContent());
                    Intent intent = new Intent(Constants.SEND_QUICK_REPLY_BROADCAST_INTENT);
                    intent.putExtra(Constants.SEND_QUICK_REPLY_BROADCAST_INTENT_EXTRA, sendQuickReplyRes);
                    LocalBroadcastManager.getInstance(TouchELXService.this).sendBroadcast(intent);
                }
            }
        };
        this.S = new TGDialManager.OnSyncDialListener() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$syncDialListener$1
            @Override // com.touchgui.sdk.TGDialManager.OnSyncDialListener
            public void onCompleted() {
                TouchELXResponseListener responseListener;
                LogHelper.d(TouchELXService.this.getTAG(), "OnSyncDialListener onCompleted");
                if (TouchELXService.this.getKhCurrentCommand() != null) {
                    if ((TouchELXService.this.getKhCurrentCommand() instanceof TouchCloudWatchFaceReq) || (TouchELXService.this.getKhCurrentCommand() instanceof TouchDIYWatchFaceReq)) {
                        TouchELXBaseRes touchELXBaseRes = new TouchELXBaseRes();
                        TouchELXBaseReq khCurrentCommand = TouchELXService.this.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand);
                        touchELXBaseRes.setBaseReq(khCurrentCommand);
                        TouchELXBaseReq khCurrentCommand2 = TouchELXService.this.getKhCurrentCommand();
                        if (khCurrentCommand2 == null || (responseListener = khCurrentCommand2.getResponseListener()) == null) {
                            return;
                        }
                        responseListener.onResponse(touchELXBaseRes);
                    }
                }
            }

            @Override // com.touchgui.sdk.TGDialManager.OnSyncDialListener
            public void onError(@Nullable Throwable th) {
                TouchELXService touchELXService = TouchELXService.this;
                String message = th != null ? th.getMessage() : null;
                Intrinsics.checkNotNull(message);
                TouchELXService.access$sendCommandFailure(touchELXService, message);
            }

            @Override // com.touchgui.sdk.TGDialManager.OnSyncDialListener
            public void onInstalling() {
            }

            @Override // com.touchgui.sdk.TGDialManager.OnSyncDialListener
            public void onProgress(int i) {
                TouchELXResponseListener responseListener;
                String tag = TouchELXService.this.getTAG();
                LogHelper.d(tag, "OnSyncDialListener onProgress -- " + i);
                if (TouchELXService.this.getKhCurrentCommand() != null) {
                    if ((TouchELXService.this.getKhCurrentCommand() instanceof TouchCloudWatchFaceReq) || (TouchELXService.this.getKhCurrentCommand() instanceof TouchDIYWatchFaceReq)) {
                        TouchELXBaseRes touchELXBaseRes = new TouchELXBaseRes();
                        TouchELXBaseReq khCurrentCommand = TouchELXService.this.getKhCurrentCommand();
                        Intrinsics.checkNotNull(khCurrentCommand);
                        touchELXBaseRes.setBaseReq(khCurrentCommand);
                        LiveWatchFaceUploadPercentage liveWatchFaceUploadPercentage = new LiveWatchFaceUploadPercentage();
                        liveWatchFaceUploadPercentage.setPercentage(i);
                        touchELXBaseRes.setObj(liveWatchFaceUploadPercentage);
                        TouchELXBaseReq khCurrentCommand2 = TouchELXService.this.getKhCurrentCommand();
                        if (khCurrentCommand2 == null || (responseListener = khCurrentCommand2.getResponseListener()) == null) {
                            return;
                        }
                        responseListener.onResponse(touchELXBaseRes);
                    }
                }
            }
        };
    }

    public static final void A(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void B(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void C(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void D(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void E(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void F(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void G(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void H(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void I(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void J(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void K(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void L(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void M(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void N(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void O(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void P(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void Q(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void R(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void S(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void T(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void U(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void V(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void W(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void X(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void Y(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void Z(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void a0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void access$createBondDevice(TouchELXService touchELXService, String str) {
        touchELXService.getClass();
        BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
        Intrinsics.checkNotNullExpressionValue(remoteDevice, "getDefaultAdapter().getRemoteDevice(address)");
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(touchELXService, "android.permission.BLUETOOTH_CONNECT") == 0) {
            try {
                Method declaredMethod = remoteDevice.getClass().getDeclaredMethod("createBond", Integer.TYPE);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(remoteDevice, 1);
            } catch (Exception e) {
                String str2 = touchELXService.f3935a;
                LogHelper.i(str2, "createBondDevice " + e.getMessage());
                try {
                    remoteDevice.createBond();
                } catch (Exception e2) {
                    String str3 = touchELXService.f3935a;
                    LogHelper.i(str3, "createBondDevice catch " + e2.getMessage());
                }
            }
        }
    }

    public static final void access$disconnect(TouchELXService touchELXService, boolean z) {
        TouchDeviceManager touchDeviceManager = touchELXService.L;
        if (touchDeviceManager != null) {
            touchDeviceManager.disconnect(z);
        }
    }

    public static final void access$removeBond(TouchELXService touchELXService, String str) {
        touchELXService.getClass();
        BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
        try {
            Method declaredMethod = remoteDevice.getClass().getDeclaredMethod("removeBond", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(remoteDevice, new Object[0]);
        } catch (Exception e) {
            String str2 = touchELXService.f3935a;
            LogHelper.d(str2, "removeBond " + e);
        }
    }

    public static final void access$sendCommandFailure(TouchELXService touchELXService, String str) {
        TouchELXBaseReq touchELXBaseReq = touchELXService.h;
        if (touchELXBaseReq != null) {
            TouchELXResponseListener responseListener = touchELXBaseReq.getResponseListener();
            if (responseListener != null) {
                TouchELXErrorType touchELXErrorType = TouchELXErrorType.COMMAND_RESPONSE_ERROR;
                responseListener.onFailure(new TouchELXError(touchELXErrorType, "Command Failure " + str));
            }
            String str2 = touchELXService.f3935a;
            LogHelper.d(str2, "sendCommandFailure->Set khCurrentCommand to null->" + touchELXService.h + " Command Failure " + str);
        }
    }

    public static final void b0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void c0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void d(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void d0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void e0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void f0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void g(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void g0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void h(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void i(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void j(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void k(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void l(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void m(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void n(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void o(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void p(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void q(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void r(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void s(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3935a, "setMusicEnable success");
    }

    public static final void t(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3935a, "setTimeSync success");
    }

    public static final void u(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void v(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void w(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void x(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void y(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void z(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void a(String str) {
        Disposable disposable;
        Observable<Integer> auth;
        Observable<Integer> observeOn;
        TouchDeviceManager touchDeviceManager = this.L;
        if (touchDeviceManager == null || (auth = touchDeviceManager.auth(str)) == null || (observeOn = auth.observeOn(AndroidSchedulers.mainThread())) == null) {
            disposable = null;
        } else {
            final Function1<Integer, Unit> function1 = new Function1<Integer, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$auth$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(Integer num) {
                    Integer num2 = num;
                    if (num2 != null && num2.intValue() == 0) {
                        TouchELXService.this.a();
                    } else {
                        TouchELXService.access$disconnect(TouchELXService.this, false);
                    }
                    return Unit.INSTANCE;
                }
            };
            Consumer<? super Integer> consumer = new Consumer() { // from class: com.coveiot.android.bleabstract.services.t5
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TouchELXService.a(Function1.this, obj);
                }
            };
            final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$auth$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(Throwable th) {
                    final Throwable th2 = th;
                    final TouchELXService touchELXService = TouchELXService.this;
                    new Function0<Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$auth$2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public Unit invoke() {
                            String tag = TouchELXService.this.getTAG();
                            StringBuilder sb = new StringBuilder();
                            sb.append("auth error");
                            Throwable th3 = th2;
                            sb.append(th3 != null ? th3.getMessage() : null);
                            LogHelper.i(tag, sb.toString());
                            return Unit.INSTANCE;
                        }
                    };
                    return Unit.INSTANCE;
                }
            };
            disposable = observeOn.subscribe(consumer, new Consumer() { // from class: com.coveiot.android.bleabstract.services.w5
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TouchELXService.b(Function1.this, obj);
                }
            });
        }
        this.j = disposable;
    }

    public final void b() {
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

    public final void c() {
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter != null) {
            Intrinsics.checkNotNull(bluetoothAdapter);
            if (bluetoothAdapter.isEnabled()) {
                int i = Build.VERSION.SDK_INT;
                if (i < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_SCAN") == 0) {
                    if (i < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                        TouchDeviceManager touchDeviceManager = this.L;
                        if (touchDeviceManager != null) {
                            touchDeviceManager.registerConnectCallback(this.N);
                        }
                        TouchDeviceManager touchDeviceManager2 = this.L;
                        if (touchDeviceManager2 != null) {
                            touchDeviceManager2.connect(getMacAddress());
                        }
                    }
                }
            }
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
        c();
    }

    public void disconnectAndForget() {
        Disposable disposable;
        Observable<String> btMac;
        Observable<String> observeOn;
        LogHelper.i(this.f3935a, "disconnectAndForget called");
        TouchDeviceManager touchDeviceManager = this.L;
        if (touchDeviceManager == null || (btMac = touchDeviceManager.getBtMac()) == null || (observeOn = btMac.observeOn(AndroidSchedulers.mainThread())) == null) {
            disposable = null;
        } else {
            final Function1<String, Unit> function1 = new Function1<String, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$disconnectAndForget$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(String str) {
                    String str2 = str;
                    TouchELXService touchELXService = TouchELXService.this;
                    Intrinsics.checkNotNull(str2);
                    TouchELXService.access$removeBond(touchELXService, str2);
                    return Unit.INSTANCE;
                }
            };
            Consumer<? super String> consumer = new Consumer() { // from class: com.coveiot.android.bleabstract.services.c6
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TouchELXService.e(Function1.this, obj);
                }
            };
            final TouchELXService$disconnectAndForget$2 touchELXService$disconnectAndForget$2 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$disconnectAndForget$2
                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(Throwable th) {
                    AnonymousClass1 anonymousClass1 = new Function0<Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$disconnectAndForget$2.1
                        @Override // kotlin.jvm.functions.Function0
                        public Unit invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    return Unit.INSTANCE;
                }
            };
            disposable = observeOn.subscribe(consumer, new Consumer() { // from class: com.coveiot.android.bleabstract.services.e6
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TouchELXService.f(Function1.this, obj);
                }
            });
        }
        this.D = disposable;
        PreferenceManagerAbstract.getInstance(getApplicationContext()).saveConnectedDeviceMAcAddress("");
        PreferenceManagerAbstract.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        a(true);
        updateConnectionState(CloveBleState.BleState.DISCONNECTED, false);
        unregisterReceivers();
        stopForeground(true);
        stopSelf();
    }

    public void disconnectAndRetainMacAddress() {
        LogHelper.i(this.f3935a, "disconnectAndRetainMacAddress called");
        PreferenceManagerAbstract.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        a(true);
        unregisterReceivers();
        stopForeground(true);
        stopSelf();
    }

    public final void e() {
        Disposable disposable;
        Completable musicOnOff;
        Completable observeOn;
        TouchDeviceManager touchDeviceManager = this.L;
        if (touchDeviceManager == null || (musicOnOff = touchDeviceManager.setMusicOnOff(true)) == null || (observeOn = musicOnOff.observeOn(AndroidSchedulers.mainThread())) == null) {
            disposable = null;
        } else {
            Action action = new Action() { // from class: com.coveiot.android.bleabstract.services.l4
                @Override // io.reactivex.functions.Action
                public final void run() {
                    TouchELXService.s(TouchELXService.this);
                }
            };
            final Function1<Throwable, Unit> function1 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$setMusicEnable$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(Throwable th) {
                    LogHelper.i(TouchELXService.this.getTAG(), "setMusicEnable Failed");
                    return Unit.INSTANCE;
                }
            };
            disposable = observeOn.subscribe(action, new Consumer() { // from class: com.coveiot.android.bleabstract.services.g6
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TouchELXService.f0(Function1.this, obj);
                }
            });
        }
        this.B = disposable;
    }

    public final void f() {
        Disposable disposable;
        Completable syncTime;
        Completable observeOn;
        TouchDeviceManager touchDeviceManager = this.L;
        if (touchDeviceManager == null || (syncTime = touchDeviceManager.syncTime()) == null || (observeOn = syncTime.observeOn(AndroidSchedulers.mainThread())) == null) {
            disposable = null;
        } else {
            Action action = new Action() { // from class: com.coveiot.android.bleabstract.services.m4
                @Override // io.reactivex.functions.Action
                public final void run() {
                    TouchELXService.t(TouchELXService.this);
                }
            };
            final Function1<Throwable, Unit> function1 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$setTimeSync$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(Throwable th) {
                    LogHelper.i(TouchELXService.this.getTAG(), "setTimeSync Failed");
                    return Unit.INSTANCE;
                }
            };
            disposable = observeOn.subscribe(action, new Consumer() { // from class: com.coveiot.android.bleabstract.services.i6
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TouchELXService.g0(Function1.this, obj);
                }
            });
        }
        this.k = disposable;
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
    public final TouchELXBaseReq getKhCurrentCommand() {
        return this.h;
    }

    @Nullable
    public final BluetoothDevice getMBluetoothDevice() {
        return this.f;
    }

    @Nullable
    public final TouchDeviceManager getMTouchDeviceManager() {
        return this.L;
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
    public final String getTAG() {
        return this.f3935a;
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(@Nullable Intent intent) {
        return getServiceBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        b();
        Object systemService = getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        BluetoothManager bluetoothManager = (BluetoothManager) systemService;
        this.b = bluetoothManager;
        Intrinsics.checkNotNull(bluetoothManager);
        this.c = bluetoothManager.getAdapter();
        this.L = TouchDeviceManager.Companion.get(this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        unregisterReceivers();
        Disposable disposable = this.i;
        if (disposable != null) {
            Intrinsics.checkNotNull(disposable);
            if (!disposable.isDisposed()) {
                Disposable disposable2 = this.i;
                Intrinsics.checkNotNull(disposable2);
                disposable2.dispose();
            }
        }
        Disposable disposable3 = this.j;
        if (disposable3 != null) {
            Intrinsics.checkNotNull(disposable3);
            if (!disposable3.isDisposed()) {
                Disposable disposable4 = this.j;
                Intrinsics.checkNotNull(disposable4);
                disposable4.dispose();
            }
        }
        Disposable disposable5 = this.l;
        if (disposable5 != null) {
            Intrinsics.checkNotNull(disposable5);
            if (!disposable5.isDisposed()) {
                Disposable disposable6 = this.l;
                Intrinsics.checkNotNull(disposable6);
                disposable6.dispose();
            }
        }
        Disposable disposable7 = this.m;
        if (disposable7 != null) {
            Intrinsics.checkNotNull(disposable7);
            if (!disposable7.isDisposed()) {
                Disposable disposable8 = this.m;
                Intrinsics.checkNotNull(disposable8);
                disposable8.dispose();
            }
        }
        Disposable disposable9 = this.n;
        if (disposable9 != null) {
            Intrinsics.checkNotNull(disposable9);
            if (!disposable9.isDisposed()) {
                Disposable disposable10 = this.n;
                Intrinsics.checkNotNull(disposable10);
                disposable10.dispose();
            }
        }
        Disposable disposable11 = this.o;
        if (disposable11 != null) {
            Intrinsics.checkNotNull(disposable11);
            if (!disposable11.isDisposed()) {
                Disposable disposable12 = this.o;
                Intrinsics.checkNotNull(disposable12);
                disposable12.dispose();
            }
        }
        Disposable disposable13 = this.t;
        if (disposable13 != null) {
            Intrinsics.checkNotNull(disposable13);
            if (!disposable13.isDisposed()) {
                Disposable disposable14 = this.t;
                Intrinsics.checkNotNull(disposable14);
                disposable14.dispose();
            }
        }
        Disposable disposable15 = this.u;
        if (disposable15 != null) {
            Intrinsics.checkNotNull(disposable15);
            if (!disposable15.isDisposed()) {
                Disposable disposable16 = this.u;
                Intrinsics.checkNotNull(disposable16);
                disposable16.dispose();
            }
        }
        Disposable disposable17 = this.v;
        if (disposable17 != null) {
            Intrinsics.checkNotNull(disposable17);
            if (!disposable17.isDisposed()) {
                Disposable disposable18 = this.v;
                Intrinsics.checkNotNull(disposable18);
                disposable18.dispose();
            }
        }
        Disposable disposable19 = this.s;
        if (disposable19 != null) {
            Intrinsics.checkNotNull(disposable19);
            if (!disposable19.isDisposed()) {
                Disposable disposable20 = this.s;
                Intrinsics.checkNotNull(disposable20);
                disposable20.dispose();
            }
        }
        Disposable disposable21 = this.q;
        if (disposable21 != null) {
            Intrinsics.checkNotNull(disposable21);
            if (!disposable21.isDisposed()) {
                Disposable disposable22 = this.q;
                Intrinsics.checkNotNull(disposable22);
                disposable22.dispose();
            }
        }
        Disposable disposable23 = this.r;
        if (disposable23 != null) {
            Intrinsics.checkNotNull(disposable23);
            if (!disposable23.isDisposed()) {
                Disposable disposable24 = this.r;
                Intrinsics.checkNotNull(disposable24);
                disposable24.dispose();
            }
        }
        Disposable disposable25 = this.p;
        if (disposable25 != null) {
            Intrinsics.checkNotNull(disposable25);
            if (!disposable25.isDisposed()) {
                Disposable disposable26 = this.p;
                Intrinsics.checkNotNull(disposable26);
                disposable26.dispose();
            }
        }
        Disposable disposable27 = this.w;
        if (disposable27 != null) {
            Intrinsics.checkNotNull(disposable27);
            if (!disposable27.isDisposed()) {
                Disposable disposable28 = this.w;
                Intrinsics.checkNotNull(disposable28);
                disposable28.dispose();
            }
        }
        Disposable disposable29 = this.x;
        if (disposable29 != null) {
            Intrinsics.checkNotNull(disposable29);
            if (!disposable29.isDisposed()) {
                Disposable disposable30 = this.x;
                Intrinsics.checkNotNull(disposable30);
                disposable30.dispose();
            }
        }
        Disposable disposable31 = this.z;
        if (disposable31 != null) {
            Intrinsics.checkNotNull(disposable31);
            if (!disposable31.isDisposed()) {
                Disposable disposable32 = this.z;
                Intrinsics.checkNotNull(disposable32);
                disposable32.dispose();
            }
        }
        Disposable disposable33 = this.A;
        if (disposable33 != null) {
            Intrinsics.checkNotNull(disposable33);
            if (!disposable33.isDisposed()) {
                Disposable disposable34 = this.A;
                Intrinsics.checkNotNull(disposable34);
                disposable34.dispose();
            }
        }
        Disposable disposable35 = this.B;
        if (disposable35 != null) {
            Intrinsics.checkNotNull(disposable35);
            if (!disposable35.isDisposed()) {
                Disposable disposable36 = this.B;
                Intrinsics.checkNotNull(disposable36);
                disposable36.dispose();
            }
        }
        Disposable disposable37 = this.y;
        if (disposable37 != null) {
            Intrinsics.checkNotNull(disposable37);
            if (!disposable37.isDisposed()) {
                Disposable disposable38 = this.y;
                Intrinsics.checkNotNull(disposable38);
                disposable38.dispose();
            }
        }
        Disposable disposable39 = this.C;
        if (disposable39 != null) {
            Intrinsics.checkNotNull(disposable39);
            if (!disposable39.isDisposed()) {
                Disposable disposable40 = this.C;
                Intrinsics.checkNotNull(disposable40);
                disposable40.dispose();
            }
        }
        Disposable disposable41 = this.D;
        if (disposable41 != null) {
            Intrinsics.checkNotNull(disposable41);
            if (!disposable41.isDisposed()) {
                Disposable disposable42 = this.D;
                Intrinsics.checkNotNull(disposable42);
                disposable42.dispose();
            }
        }
        Disposable disposable43 = this.F;
        if (disposable43 != null) {
            Intrinsics.checkNotNull(disposable43);
            if (!disposable43.isDisposed()) {
                Disposable disposable44 = this.F;
                Intrinsics.checkNotNull(disposable44);
                disposable44.dispose();
            }
        }
        Disposable disposable45 = this.E;
        if (disposable45 != null) {
            Intrinsics.checkNotNull(disposable45);
            if (!disposable45.isDisposed()) {
                Disposable disposable46 = this.E;
                Intrinsics.checkNotNull(disposable46);
                disposable46.dispose();
            }
        }
        Disposable disposable47 = this.G;
        if (disposable47 != null) {
            Intrinsics.checkNotNull(disposable47);
            if (!disposable47.isDisposed()) {
                Disposable disposable48 = this.G;
                Intrinsics.checkNotNull(disposable48);
                disposable48.dispose();
            }
        }
        Disposable disposable49 = this.H;
        if (disposable49 != null) {
            Intrinsics.checkNotNull(disposable49);
            if (!disposable49.isDisposed()) {
                Disposable disposable50 = this.H;
                Intrinsics.checkNotNull(disposable50);
                disposable50.dispose();
            }
        }
        Disposable disposable51 = this.I;
        if (disposable51 != null) {
            Intrinsics.checkNotNull(disposable51);
            if (!disposable51.isDisposed()) {
                Disposable disposable52 = this.I;
                Intrinsics.checkNotNull(disposable52);
                disposable52.dispose();
            }
        }
        Disposable disposable53 = this.k;
        if (disposable53 != null) {
            Intrinsics.checkNotNull(disposable53);
            if (!disposable53.isDisposed()) {
                Disposable disposable54 = this.k;
                Intrinsics.checkNotNull(disposable54);
                disposable54.dispose();
            }
        }
        Disposable disposable55 = this.J;
        if (disposable55 != null) {
            Intrinsics.checkNotNull(disposable55);
            if (!disposable55.isDisposed()) {
                Disposable disposable56 = this.J;
                Intrinsics.checkNotNull(disposable56);
                disposable56.dispose();
            }
        }
        Disposable disposable57 = this.K;
        if (disposable57 != null) {
            Intrinsics.checkNotNull(disposable57);
            if (disposable57.isDisposed()) {
                return;
            }
            Disposable disposable58 = this.K;
            Intrinsics.checkNotNull(disposable58);
            disposable58.dispose();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        if (intent == null) {
            try {
                Intent intent2 = new Intent(this, TouchELXService.class);
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
        b();
        String connectedDeviceMacAddress = PreferenceManagerAbstract.getInstance(getApplicationContext()).getConnectedDeviceMacAddress();
        Intrinsics.checkNotNullExpressionValue(connectedDeviceMacAddress, "getInstance(applicationC…connectedDeviceMacAddress");
        setMacAddress(connectedDeviceMacAddress);
        BleUtils.getConnectionType(getApplicationContext());
        registerReceivers();
        if (!BleUtils.isEmpty(getMacAddress())) {
            c();
        }
        return 3;
    }

    public void registerReceivers() {
        try {
            BleEventBusManager.getInstance().getEventBus().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            getApplicationContext().registerReceiver(this.O, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
            getApplicationContext().registerReceiver(this.O, new IntentFilter("action_stop_service"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        TouchDeviceManager touchDeviceManager = this.L;
        if (touchDeviceManager != null) {
            touchDeviceManager.addOnHealthDataListener(this.P);
        }
        TouchDeviceManager touchDeviceManager2 = this.L;
        if (touchDeviceManager2 != null) {
            touchDeviceManager2.registerWorkoutDataCallback(this.Q);
        }
        TouchDeviceManager touchDeviceManager3 = this.L;
        if (touchDeviceManager3 != null) {
            touchDeviceManager3.addOnSyncDialListener(this.S);
        }
        TouchDeviceManager touchDeviceManager4 = this.L;
        if (touchDeviceManager4 != null) {
            touchDeviceManager4.addEventLister(this.R);
        }
    }

    public void restartService() {
        PreferenceManagerAbstract.getInstance(getApplicationContext()).saveConnectionType(ConnectionType.RECONNECT_ON_DISCONNECT.name());
        a(true);
        unregisterReceivers();
        stopForeground(true);
        stopSelf();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v55 */
    /* JADX WARN: Type inference failed for: r0v56 */
    /* JADX WARN: Type inference failed for: r0v59 */
    /* JADX WARN: Type inference failed for: r0v61 */
    /* JADX WARN: Type inference failed for: r0v62 */
    /* JADX WARN: Type inference failed for: r0v65 */
    /* JADX WARN: Type inference failed for: r0v67 */
    /* JADX WARN: Type inference failed for: r0v68 */
    /* JADX WARN: Type inference failed for: r0v71 */
    /* JADX WARN: Type inference failed for: r0v73 */
    /* JADX WARN: Type inference failed for: r0v74 */
    /* JADX WARN: Type inference failed for: r0v77 */
    public final void sendRequest(@NotNull final TouchELXBaseReq baseReq, @NotNull TouchELXResponseListener responseListener) {
        Completable syncCallStatus;
        Completable observeOn;
        Observable<List<TGEventReminder>> getEventReminders;
        Observable<List<TGEventReminder>> observeOn2;
        Observable<Integer> eventReminders;
        Observable<Integer> observeOn3;
        Observable<Integer> applyDial;
        Observable<Integer> observeOn4;
        Completable cameraOnOff;
        Completable observeOn5;
        Observable<List<TGQuickCard>> getQuickCards;
        Observable<List<TGQuickCard>> observeOn6;
        Completable quickCards;
        Completable observeOn7;
        Observable<TGWorkoutSupportList> getSupportedWorkoutList;
        Observable<TGWorkoutSupportList> observeOn8;
        Observable<Integer> activityMode;
        Observable<Integer> observeOn9;
        Observable<Integer> contacts;
        Observable<Integer> observeOn10;
        Completable syncQuickReply;
        Completable observeOn11;
        Completable observeOn12;
        Observable<Integer> syncMusic;
        Observable<Integer> observeOn13;
        Completable physiologicalCycle;
        Completable observeOn14;
        Completable weather;
        Completable observeOn15;
        Completable weatherOnOff;
        Completable observeOn16;
        Completable remindCall;
        Completable observeOn17;
        Observable<Integer> syncMessage;
        Observable<Integer> observeOn18;
        Completable unit;
        Completable observeOn19;
        Completable observeOn20;
        Completable spo2MonitoringMode;
        Completable observeOn21;
        Completable stressMonitoringMode;
        Completable observeOn22;
        Completable heartRateMonitoringMode;
        Completable observeOn23;
        Observable<TGRaiseWristConfig> raiseWrist;
        Observable<TGRaiseWristConfig> observeOn24;
        Completable raiseWrist2;
        Completable observeOn25;
        Observable<TGNotDisturbConfig> notDisturbMode;
        Observable<TGNotDisturbConfig> observeOn26;
        Completable notDisturbMode2;
        Completable observeOn27;
        Completable remindDrinking;
        Completable observeOn28;
        Completable sedentary;
        Completable observeOn29;
        Observable<List<TGAlarm>> syncAlarms;
        Observable<List<TGAlarm>> observeOn30;
        Observable<Integer> alarms;
        Observable<Integer> observeOn31;
        Observable<TGVersionInfo> versionInfo;
        Observable<TGVersionInfo> observeOn32;
        Observable<TGBatteryInfo> batteryInfo;
        Observable<TGBatteryInfo> observeOn33;
        Completable profile;
        Completable observeOn34;
        TouchELXResponseListener responseListener2;
        TouchELXResponseListener responseListener3;
        TouchELXResponseListener responseListener4;
        TouchELXResponseListener responseListener5;
        TouchELXResponseListener responseListener6;
        TouchELXResponseListener responseListener7;
        TouchELXResponseListener responseListener8;
        Intrinsics.checkNotNullParameter(baseReq, "baseReq");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        baseReq.setResponseListener(responseListener);
        this.h = baseReq;
        TouchDeviceManager touchDeviceManager = this.L;
        Intrinsics.checkNotNull(touchDeviceManager);
        if (touchDeviceManager.isConnected()) {
            if (baseReq instanceof TouchStepsReq) {
                TouchDeviceManager touchDeviceManager2 = this.L;
                if (touchDeviceManager2 != null) {
                    touchDeviceManager2.syncAllHealthData();
                    return;
                }
                return;
            }
            boolean z = false;
            if (baseReq instanceof TouchSleepReq) {
                if (this.h != null) {
                    TouchELXBaseRes touchELXBaseRes = new TouchELXBaseRes();
                    TouchELXBaseReq touchELXBaseReq = this.h;
                    Intrinsics.checkNotNull(touchELXBaseReq);
                    touchELXBaseRes.setBaseReq(touchELXBaseReq);
                    List<EntityTGSleepData> allUnProcessedSleepData = KHTGSleepRepository.Companion.getInstance(this).getAllUnProcessedSleepData(getMacAddress());
                    if (!((allUnProcessedSleepData == null || allUnProcessedSleepData.isEmpty()) ? true : true)) {
                        touchELXBaseRes.setObj(TouchSleepFormatter.Companion.getInstance(this).convertSleepData(allUnProcessedSleepData));
                        TouchELXBaseReq touchELXBaseReq2 = this.h;
                        if (touchELXBaseReq2 == null || (responseListener8 = touchELXBaseReq2.getResponseListener()) == null) {
                            return;
                        }
                        responseListener8.onResponse(touchELXBaseRes);
                        return;
                    }
                    TouchELXBaseReq touchELXBaseReq3 = this.h;
                    if (touchELXBaseReq3 == null || (responseListener7 = touchELXBaseReq3.getResponseListener()) == null) {
                        return;
                    }
                    responseListener7.onResponse(touchELXBaseRes);
                    return;
                }
                LogsHelper.d(this.f3935a, "khCurrent command is null");
            } else if (baseReq instanceof TouchHeartRateReq) {
                if (this.h != null) {
                    TouchELXBaseRes touchELXBaseRes2 = new TouchELXBaseRes();
                    TouchELXBaseReq touchELXBaseReq4 = this.h;
                    Intrinsics.checkNotNull(touchELXBaseReq4);
                    touchELXBaseRes2.setBaseReq(touchELXBaseReq4);
                    List<EntityTGHeartRateData> allUnProcessedHeartRateData = KHTGHeartRateRepository.Companion.getInstance(this).getAllUnProcessedHeartRateData(getMacAddress());
                    if ((allUnProcessedHeartRateData == null || allUnProcessedHeartRateData.isEmpty()) == false) {
                        HashMap hashMap = new HashMap();
                        for (EntityTGHeartRateData entityTGHeartRateData : allUnProcessedHeartRateData) {
                            String dateFromTimeStamp = BleApiUtils.INSTANCE.getDateFromTimeStamp(entityTGHeartRateData.getTimeStamp());
                            if (!hashMap.containsKey(dateFromTimeStamp)) {
                                Intrinsics.checkNotNull(dateFromTimeStamp);
                                hashMap.put(dateFromTimeStamp, CollectionsKt__CollectionsKt.arrayListOf(entityTGHeartRateData));
                            } else {
                                ArrayList arrayList = (ArrayList) hashMap.get(dateFromTimeStamp);
                                if (arrayList != null) {
                                    arrayList.add(entityTGHeartRateData);
                                }
                                Intrinsics.checkNotNull(dateFromTimeStamp);
                                Intrinsics.checkNotNull(arrayList);
                                hashMap.put(dateFromTimeStamp, arrayList);
                            }
                        }
                        Set keySet = hashMap.keySet();
                        Intrinsics.checkNotNullExpressionValue(keySet, "entityHashMap.keys");
                        List<String> sorted = CollectionsKt___CollectionsKt.sorted(CollectionsKt___CollectionsKt.toList(keySet));
                        ArrayList arrayList2 = new ArrayList();
                        for (String str : sorted) {
                            ArrayList arrayList3 = (ArrayList) hashMap.get(str);
                            if (arrayList3 != null) {
                                HeartRateResponse convertToHeartRateResponse = TouchHeartRateFormatter.Companion.getInstance(this).convertToHeartRateResponse(CollectionsKt___CollectionsKt.sortedWith(arrayList3, new Comparator() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$lambda$13$$inlined$sortedBy$1
                                    @Override // java.util.Comparator
                                    public final int compare(T t, T t2) {
                                        return kotlin.comparisons.f.compareValues(Long.valueOf(((EntityTGHeartRateData) t).getTimeStamp()), Long.valueOf(((EntityTGHeartRateData) t2).getTimeStamp()));
                                    }
                                }));
                                convertToHeartRateResponse.setComplete(Intrinsics.areEqual(str, sorted.get(sorted.size() - 1)));
                                arrayList2.add(convertToHeartRateResponse);
                            }
                        }
                        touchELXBaseRes2.setObj(arrayList2);
                        TouchELXBaseReq touchELXBaseReq5 = this.h;
                        if (touchELXBaseReq5 == null || (responseListener6 = touchELXBaseReq5.getResponseListener()) == null) {
                            return;
                        }
                        responseListener6.onResponse(touchELXBaseRes2);
                        return;
                    }
                    TouchELXBaseReq touchELXBaseReq6 = this.h;
                    if (touchELXBaseReq6 == null || (responseListener5 = touchELXBaseReq6.getResponseListener()) == null) {
                        return;
                    }
                    responseListener5.onResponse(touchELXBaseRes2);
                    return;
                }
                LogsHelper.d(this.f3935a, "khCurrent command is null");
            } else {
                Disposable disposable = null;
                r4 = null;
                r4 = null;
                Disposable disposable2 = null;
                r4 = null;
                r4 = null;
                Disposable disposable3 = null;
                r4 = null;
                r4 = null;
                Disposable disposable4 = null;
                r4 = null;
                r4 = null;
                Disposable disposable5 = null;
                r4 = null;
                r4 = null;
                Disposable disposable6 = null;
                r4 = null;
                r4 = null;
                Disposable disposable7 = null;
                r4 = null;
                r4 = null;
                Disposable disposable8 = null;
                r4 = null;
                r4 = null;
                Disposable disposable9 = null;
                r4 = null;
                r4 = null;
                Disposable disposable10 = null;
                r4 = null;
                r4 = null;
                Disposable disposable11 = null;
                r4 = null;
                r4 = null;
                Disposable disposable12 = null;
                r4 = null;
                r4 = null;
                Disposable disposable13 = null;
                r4 = null;
                r4 = null;
                Disposable disposable14 = null;
                r4 = null;
                r4 = null;
                Disposable disposable15 = null;
                r4 = null;
                r4 = null;
                Disposable disposable16 = null;
                r4 = null;
                r4 = null;
                Disposable disposable17 = null;
                r4 = null;
                r4 = null;
                Disposable disposable18 = null;
                r4 = null;
                r4 = null;
                Disposable disposable19 = null;
                r4 = null;
                r4 = null;
                Disposable disposable20 = null;
                r4 = null;
                r4 = null;
                Disposable disposable21 = null;
                r4 = null;
                r4 = null;
                Disposable disposable22 = null;
                r4 = null;
                r4 = null;
                Disposable disposable23 = null;
                r4 = null;
                r4 = null;
                Disposable disposable24 = null;
                r4 = null;
                r4 = null;
                Disposable disposable25 = null;
                r4 = null;
                r4 = null;
                Disposable disposable26 = null;
                r4 = null;
                r4 = null;
                Disposable disposable27 = null;
                r4 = null;
                r4 = null;
                Disposable disposable28 = null;
                r4 = null;
                r4 = null;
                Disposable disposable29 = null;
                r4 = null;
                r4 = null;
                Disposable disposable30 = null;
                r4 = null;
                r4 = null;
                Disposable disposable31 = null;
                r4 = null;
                r4 = null;
                Disposable disposable32 = null;
                r4 = null;
                r4 = null;
                Disposable disposable33 = null;
                r4 = null;
                r4 = null;
                Disposable disposable34 = null;
                disposable = null;
                disposable = null;
                if (baseReq instanceof TouchStressReq) {
                    if (this.h != null) {
                        TouchELXBaseRes touchELXBaseRes3 = new TouchELXBaseRes();
                        TouchELXBaseReq touchELXBaseReq7 = this.h;
                        Intrinsics.checkNotNull(touchELXBaseReq7);
                        touchELXBaseRes3.setBaseReq(touchELXBaseReq7);
                        List<EntityTGStressData> allUnProcessedStressData = KHTGStressRepository.Companion.getInstance(this).getAllUnProcessedStressData(getMacAddress());
                        if ((allUnProcessedStressData == null || allUnProcessedStressData.isEmpty()) == false) {
                            HashMap hashMap2 = new HashMap();
                            for (EntityTGStressData entityTGStressData : allUnProcessedStressData) {
                                String dateFromTimeStamp2 = BleApiUtils.INSTANCE.getDateFromTimeStamp(entityTGStressData.getTimeStamp());
                                if (!hashMap2.containsKey(dateFromTimeStamp2)) {
                                    Intrinsics.checkNotNull(dateFromTimeStamp2);
                                    hashMap2.put(dateFromTimeStamp2, CollectionsKt__CollectionsKt.arrayListOf(entityTGStressData));
                                } else {
                                    ArrayList arrayList4 = (ArrayList) hashMap2.get(dateFromTimeStamp2);
                                    if (arrayList4 != null) {
                                        arrayList4.add(entityTGStressData);
                                    }
                                    Intrinsics.checkNotNull(dateFromTimeStamp2);
                                    Intrinsics.checkNotNull(arrayList4);
                                    hashMap2.put(dateFromTimeStamp2, arrayList4);
                                }
                            }
                            Set keySet2 = hashMap2.keySet();
                            Intrinsics.checkNotNullExpressionValue(keySet2, "entityHashMap.keys");
                            List<String> sorted2 = CollectionsKt___CollectionsKt.sorted(CollectionsKt___CollectionsKt.toList(keySet2));
                            ArrayList arrayList5 = new ArrayList();
                            for (String str2 : sorted2) {
                                ArrayList arrayList6 = (ArrayList) hashMap2.get(str2);
                                if (arrayList6 != null) {
                                    StressResponse convertToStressResponse = TouchStressFormatter.Companion.getInstance(this).convertToStressResponse(CollectionsKt___CollectionsKt.sortedWith(arrayList6, new Comparator() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$lambda$15$$inlined$sortedBy$1
                                        @Override // java.util.Comparator
                                        public final int compare(T t, T t2) {
                                            return kotlin.comparisons.f.compareValues(Long.valueOf(((EntityTGStressData) t).getTimeStamp()), Long.valueOf(((EntityTGStressData) t2).getTimeStamp()));
                                        }
                                    }));
                                    convertToStressResponse.setComplete(Intrinsics.areEqual(str2, sorted2.get(sorted2.size() - 1)));
                                    arrayList5.add(convertToStressResponse);
                                }
                            }
                            touchELXBaseRes3.setObj(arrayList5);
                            TouchELXBaseReq touchELXBaseReq8 = this.h;
                            TouchELXResponseListener responseListener9 = touchELXBaseReq8 != null ? touchELXBaseReq8.getResponseListener() : null;
                            Intrinsics.checkNotNull(responseListener9);
                            responseListener9.onResponse(touchELXBaseRes3);
                            return;
                        }
                        TouchELXBaseReq touchELXBaseReq9 = this.h;
                        if (touchELXBaseReq9 == null || (responseListener4 = touchELXBaseReq9.getResponseListener()) == null) {
                            return;
                        }
                        responseListener4.onResponse(touchELXBaseRes3);
                        return;
                    }
                    LogsHelper.d(this.f3935a, "khCurrent command is null");
                } else if (baseReq instanceof TouchSpo2Req) {
                    if (this.h != null) {
                        TouchELXBaseRes touchELXBaseRes4 = new TouchELXBaseRes();
                        TouchELXBaseReq touchELXBaseReq10 = this.h;
                        Intrinsics.checkNotNull(touchELXBaseReq10);
                        touchELXBaseRes4.setBaseReq(touchELXBaseReq10);
                        List<EntityTGSpO2Data> allUnProcessedSpO2Data = KHTGSpO2Repository.Companion.getInstance(this).getAllUnProcessedSpO2Data(getMacAddress());
                        if ((allUnProcessedSpO2Data == null || allUnProcessedSpO2Data.isEmpty()) == false) {
                            HashMap hashMap3 = new HashMap();
                            for (EntityTGSpO2Data entityTGSpO2Data : allUnProcessedSpO2Data) {
                                String dateFromTimeStamp3 = BleApiUtils.INSTANCE.getDateFromTimeStamp(entityTGSpO2Data.getTimeStamp());
                                if (!hashMap3.containsKey(dateFromTimeStamp3)) {
                                    Intrinsics.checkNotNull(dateFromTimeStamp3);
                                    hashMap3.put(dateFromTimeStamp3, CollectionsKt__CollectionsKt.arrayListOf(entityTGSpO2Data));
                                } else {
                                    ArrayList arrayList7 = (ArrayList) hashMap3.get(dateFromTimeStamp3);
                                    if (arrayList7 != null) {
                                        arrayList7.add(entityTGSpO2Data);
                                    }
                                    Intrinsics.checkNotNull(dateFromTimeStamp3);
                                    Intrinsics.checkNotNull(arrayList7);
                                    hashMap3.put(dateFromTimeStamp3, arrayList7);
                                }
                            }
                            Set keySet3 = hashMap3.keySet();
                            Intrinsics.checkNotNullExpressionValue(keySet3, "entityHashMap.keys");
                            List<String> sorted3 = CollectionsKt___CollectionsKt.sorted(CollectionsKt___CollectionsKt.toList(keySet3));
                            ArrayList arrayList8 = new ArrayList();
                            for (String str3 : sorted3) {
                                ArrayList arrayList9 = (ArrayList) hashMap3.get(str3);
                                if (arrayList9 != null) {
                                    PeriodicSpo2Response convertToPeriodicSpo2Response = TouchSPO2Formatter.Companion.getInstance(this).convertToPeriodicSpo2Response(CollectionsKt___CollectionsKt.sortedWith(arrayList9, new Comparator() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$lambda$17$$inlined$sortedBy$1
                                        @Override // java.util.Comparator
                                        public final int compare(T t, T t2) {
                                            return kotlin.comparisons.f.compareValues(Long.valueOf(((EntityTGSpO2Data) t).getTimeStamp()), Long.valueOf(((EntityTGSpO2Data) t2).getTimeStamp()));
                                        }
                                    }));
                                    convertToPeriodicSpo2Response.setComplete(Intrinsics.areEqual(str3, sorted3.get(sorted3.size() - 1)));
                                    arrayList8.add(convertToPeriodicSpo2Response);
                                }
                            }
                            touchELXBaseRes4.setObj(arrayList8);
                            TouchELXBaseReq touchELXBaseReq11 = this.h;
                            TouchELXResponseListener responseListener10 = touchELXBaseReq11 != null ? touchELXBaseReq11.getResponseListener() : null;
                            Intrinsics.checkNotNull(responseListener10);
                            responseListener10.onResponse(touchELXBaseRes4);
                            return;
                        }
                        TouchELXBaseReq touchELXBaseReq12 = this.h;
                        if (touchELXBaseReq12 == null || (responseListener3 = touchELXBaseReq12.getResponseListener()) == null) {
                            return;
                        }
                        responseListener3.onResponse(touchELXBaseRes4);
                        return;
                    }
                    LogsHelper.d(this.f3935a, "khCurrent command is null");
                } else if (baseReq instanceof TouchManualSpo2Req) {
                    if (this.h != null) {
                        TouchELXBaseRes touchELXBaseRes5 = new TouchELXBaseRes();
                        TouchELXBaseReq touchELXBaseReq13 = this.h;
                        Intrinsics.checkNotNull(touchELXBaseReq13);
                        touchELXBaseRes5.setBaseReq(touchELXBaseReq13);
                        List<EntityTGSpO2Data> allUnProcessedSpO2Data2 = KHTGSpO2Repository.Companion.getInstance(this).getAllUnProcessedSpO2Data(getMacAddress());
                        if ((allUnProcessedSpO2Data2 == null || allUnProcessedSpO2Data2.isEmpty()) == false) {
                            HashMap hashMap4 = new HashMap();
                            for (EntityTGSpO2Data entityTGSpO2Data2 : allUnProcessedSpO2Data2) {
                                String dateFromTimeStamp4 = BleApiUtils.INSTANCE.getDateFromTimeStamp(entityTGSpO2Data2.getTimeStamp());
                                if (!hashMap4.containsKey(dateFromTimeStamp4)) {
                                    Intrinsics.checkNotNull(dateFromTimeStamp4);
                                    hashMap4.put(dateFromTimeStamp4, CollectionsKt__CollectionsKt.arrayListOf(entityTGSpO2Data2));
                                } else {
                                    ArrayList arrayList10 = (ArrayList) hashMap4.get(dateFromTimeStamp4);
                                    if (arrayList10 != null) {
                                        arrayList10.add(entityTGSpO2Data2);
                                    }
                                    Intrinsics.checkNotNull(dateFromTimeStamp4);
                                    Intrinsics.checkNotNull(arrayList10);
                                    hashMap4.put(dateFromTimeStamp4, arrayList10);
                                }
                            }
                            Set keySet4 = hashMap4.keySet();
                            Intrinsics.checkNotNullExpressionValue(keySet4, "entityHashMap.keys");
                            List<String> sorted4 = CollectionsKt___CollectionsKt.sorted(CollectionsKt___CollectionsKt.toList(keySet4));
                            ArrayList arrayList11 = new ArrayList();
                            for (String str4 : sorted4) {
                                ArrayList arrayList12 = (ArrayList) hashMap4.get(str4);
                                if (arrayList12 != null) {
                                    ReadManualSpo2Response convertToReadManualSpo2Response = TouchSPO2Formatter.Companion.getInstance(this).convertToReadManualSpo2Response(CollectionsKt___CollectionsKt.sortedWith(arrayList12, new Comparator() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$lambda$19$$inlined$sortedBy$1
                                        @Override // java.util.Comparator
                                        public final int compare(T t, T t2) {
                                            return kotlin.comparisons.f.compareValues(Long.valueOf(((EntityTGSpO2Data) t).getTimeStamp()), Long.valueOf(((EntityTGSpO2Data) t2).getTimeStamp()));
                                        }
                                    }));
                                    convertToReadManualSpo2Response.setComplete(Intrinsics.areEqual(str4, sorted4.get(sorted4.size() - 1)));
                                    arrayList11.add(convertToReadManualSpo2Response);
                                }
                            }
                            touchELXBaseRes5.setObj(arrayList11);
                            TouchELXBaseReq touchELXBaseReq14 = this.h;
                            TouchELXResponseListener responseListener11 = touchELXBaseReq14 != null ? touchELXBaseReq14.getResponseListener() : null;
                            Intrinsics.checkNotNull(responseListener11);
                            responseListener11.onResponse(touchELXBaseRes5);
                            return;
                        }
                        TouchELXBaseReq touchELXBaseReq15 = this.h;
                        if (touchELXBaseReq15 == null || (responseListener2 = touchELXBaseReq15.getResponseListener()) == null) {
                            return;
                        }
                        responseListener2.onResponse(touchELXBaseRes5);
                        return;
                    }
                    LogsHelper.d(this.f3935a, "khCurrent command is null");
                } else if (baseReq instanceof TouchActivityDataReq) {
                    TouchDeviceManager touchDeviceManager3 = this.L;
                    if (touchDeviceManager3 != null) {
                        touchDeviceManager3.registerWorkoutDataCallback(this.Q);
                    }
                    TouchDeviceManager touchDeviceManager4 = this.L;
                    if (touchDeviceManager4 != null) {
                        touchDeviceManager4.syncWorkoutData();
                    }
                } else if (baseReq instanceof TouchUserInfoReq) {
                    TouchDeviceManager touchDeviceManager5 = this.L;
                    if (touchDeviceManager5 != null && (profile = touchDeviceManager5.setProfile(((TouchUserInfoReq) baseReq).getProfile())) != null && (observeOn34 = profile.observeOn(AndroidSchedulers.mainThread())) != null) {
                        Action action = new Action() { // from class: com.coveiot.android.bleabstract.services.c4
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                TouchELXService.a(TouchELXService.this);
                            }
                        };
                        final Function1<Throwable, Unit> function1 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$6
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchUserInfoReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable2 = observeOn34.subscribe(action, new Consumer() { // from class: com.coveiot.android.bleabstract.services.h6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.g(Function1.this, obj);
                            }
                        });
                    }
                    this.p = disposable2;
                } else if (baseReq instanceof TouchBatteryLevelReq) {
                    TouchDeviceManager touchDeviceManager6 = this.L;
                    if (touchDeviceManager6 != null && (batteryInfo = touchDeviceManager6.getBatteryInfo()) != null && (observeOn33 = batteryInfo.observeOn(AndroidSchedulers.mainThread())) != null) {
                        final Function1<TGBatteryInfo, Unit> function12 = new Function1<TGBatteryInfo, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$7
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(TGBatteryInfo tGBatteryInfo) {
                                TouchELXResponseListener responseListener12;
                                TGBatteryInfo tGBatteryInfo2 = tGBatteryInfo;
                                LogHelper.i(TouchELXService.this.getTAG(), "TouchBatteryLevelReq success");
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchBatteryLevelReq)) {
                                    TouchELXBaseRes touchELXBaseRes6 = new TouchELXBaseRes();
                                    TouchELXBaseReq khCurrentCommand = TouchELXService.this.getKhCurrentCommand();
                                    Intrinsics.checkNotNull(khCurrentCommand);
                                    touchELXBaseRes6.setBaseReq(khCurrentCommand);
                                    touchELXBaseRes6.setObj(Integer.valueOf(tGBatteryInfo2.getLevel()));
                                    TouchELXBaseReq khCurrentCommand2 = TouchELXService.this.getKhCurrentCommand();
                                    if (khCurrentCommand2 != null && (responseListener12 = khCurrentCommand2.getResponseListener()) != null) {
                                        responseListener12.onResponse(touchELXBaseRes6);
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        Consumer<? super TGBatteryInfo> consumer = new Consumer() { // from class: com.coveiot.android.bleabstract.services.j6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.h(Function1.this, obj);
                            }
                        };
                        final Function1<Throwable, Unit> function13 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$8
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchBatteryLevelReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable3 = observeOn33.subscribe(consumer, new Consumer() { // from class: com.coveiot.android.bleabstract.services.k6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.i(Function1.this, obj);
                            }
                        });
                    }
                    this.l = disposable3;
                } else if (baseReq instanceof TouchDeviceInfoReq) {
                    TouchDeviceManager touchDeviceManager7 = this.L;
                    if (touchDeviceManager7 != null && (versionInfo = touchDeviceManager7.getVersionInfo()) != null && (observeOn32 = versionInfo.observeOn(AndroidSchedulers.mainThread())) != null) {
                        final Function1<TGVersionInfo, Unit> function14 = new Function1<TGVersionInfo, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$9
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(TGVersionInfo tGVersionInfo) {
                                TouchELXResponseListener responseListener12;
                                TGVersionInfo tGVersionInfo2 = tGVersionInfo;
                                LogHelper.i(TouchELXService.this.getTAG(), "TouchDeviceInfoReq success");
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchDeviceInfoReq)) {
                                    TouchELXBaseRes touchELXBaseRes6 = new TouchELXBaseRes();
                                    TouchELXBaseReq khCurrentCommand = TouchELXService.this.getKhCurrentCommand();
                                    Intrinsics.checkNotNull(khCurrentCommand);
                                    touchELXBaseRes6.setBaseReq(khCurrentCommand);
                                    touchELXBaseRes6.setObj(tGVersionInfo2.getDetailVersion());
                                    TouchELXBaseReq khCurrentCommand2 = TouchELXService.this.getKhCurrentCommand();
                                    if (khCurrentCommand2 != null && (responseListener12 = khCurrentCommand2.getResponseListener()) != null) {
                                        responseListener12.onResponse(touchELXBaseRes6);
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        Consumer<? super TGVersionInfo> consumer2 = new Consumer() { // from class: com.coveiot.android.bleabstract.services.l6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.j(Function1.this, obj);
                            }
                        };
                        final Function1<Throwable, Unit> function15 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$10
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchDeviceInfoReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable4 = observeOn32.subscribe(consumer2, new Consumer() { // from class: com.coveiot.android.bleabstract.services.m6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.k(Function1.this, obj);
                            }
                        });
                    }
                    this.m = disposable4;
                } else if (baseReq instanceof TouchAlarmReq) {
                    TouchDeviceManager touchDeviceManager8 = this.L;
                    if (touchDeviceManager8 != null && (alarms = touchDeviceManager8.setAlarms(((TouchAlarmReq) baseReq).getAlarmList())) != null && (observeOn31 = alarms.observeOn(AndroidSchedulers.mainThread())) != null) {
                        final Function1<Integer, Unit> function16 = new Function1<Integer, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$11
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Integer num) {
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchAlarmReq)) {
                                    LogHelper.i(TouchELXService.this.getTAG(), "TouchAlarmReq success");
                                    TouchELXService.this.d();
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        Consumer<? super Integer> consumer3 = new Consumer() { // from class: com.coveiot.android.bleabstract.services.n6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.l(Function1.this, obj);
                            }
                        };
                        final Function1<Throwable, Unit> function17 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$12
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchAlarmReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable5 = observeOn31.subscribe(consumer3, new Consumer() { // from class: com.coveiot.android.bleabstract.services.o6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.m(Function1.this, obj);
                            }
                        });
                    }
                    this.n = disposable5;
                } else if (baseReq instanceof TouchGetAlarmReq) {
                    TouchDeviceManager touchDeviceManager9 = this.L;
                    if (touchDeviceManager9 != null && (syncAlarms = touchDeviceManager9.syncAlarms()) != null && (observeOn30 = syncAlarms.observeOn(AndroidSchedulers.mainThread())) != null) {
                        final Function1<List<? extends TGAlarm>, Unit> function18 = new Function1<List<? extends TGAlarm>, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$13
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(List<? extends TGAlarm> list) {
                                TouchELXResponseListener responseListener12;
                                List<? extends TGAlarm> list2 = list;
                                LogHelper.i(TouchELXService.this.getTAG(), "TouchGetAlarmReq success");
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchGetAlarmReq)) {
                                    TouchELXBaseRes touchELXBaseRes6 = new TouchELXBaseRes();
                                    TouchELXBaseReq khCurrentCommand = TouchELXService.this.getKhCurrentCommand();
                                    Intrinsics.checkNotNull(khCurrentCommand);
                                    touchELXBaseRes6.setBaseReq(khCurrentCommand);
                                    touchELXBaseRes6.setObj(list2);
                                    TouchELXBaseReq khCurrentCommand2 = TouchELXService.this.getKhCurrentCommand();
                                    if (khCurrentCommand2 != null && (responseListener12 = khCurrentCommand2.getResponseListener()) != null) {
                                        responseListener12.onResponse(touchELXBaseRes6);
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        Consumer<? super List<TGAlarm>> consumer4 = new Consumer() { // from class: com.coveiot.android.bleabstract.services.p6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.n(Function1.this, obj);
                            }
                        };
                        final Function1<Throwable, Unit> function19 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$14
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchGetAlarmReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable6 = observeOn30.subscribe(consumer4, new Consumer() { // from class: com.coveiot.android.bleabstract.services.r6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.o(Function1.this, obj);
                            }
                        });
                    }
                    this.m = disposable6;
                } else if (baseReq instanceof TouchSedentaryReminderReq) {
                    TouchDeviceManager touchDeviceManager10 = this.L;
                    if (touchDeviceManager10 != null && (sedentary = touchDeviceManager10.setSedentary(((TouchSedentaryReminderReq) baseReq).getSedentaryConfig())) != null && (observeOn29 = sedentary.observeOn(AndroidSchedulers.mainThread())) != null) {
                        Action action2 = new Action() { // from class: com.coveiot.android.bleabstract.services.n4
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                TouchELXService.b(TouchELXService.this);
                            }
                        };
                        final Function1<Throwable, Unit> function110 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$16
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchSedentaryReminderReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable7 = observeOn29.subscribe(action2, new Consumer() { // from class: com.coveiot.android.bleabstract.services.s6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.p(Function1.this, obj);
                            }
                        });
                    }
                    this.o = disposable7;
                } else if (baseReq instanceof TouchDrinkReminderReq) {
                    TouchDeviceManager touchDeviceManager11 = this.L;
                    if (touchDeviceManager11 != null && (remindDrinking = touchDeviceManager11.setRemindDrinking(((TouchDrinkReminderReq) baseReq).getDrinkReminderConfig())) != null && (observeOn28 = remindDrinking.observeOn(AndroidSchedulers.mainThread())) != null) {
                        Action action3 = new Action() { // from class: com.coveiot.android.bleabstract.services.y4
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                TouchELXService.c(TouchELXService.this);
                            }
                        };
                        final Function1<Throwable, Unit> function111 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$18
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchDrinkReminderReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable8 = observeOn28.subscribe(action3, new Consumer() { // from class: com.coveiot.android.bleabstract.services.t6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.q(Function1.this, obj);
                            }
                        });
                    }
                    this.r = disposable8;
                } else if (baseReq instanceof TouchSetDNDReq) {
                    TouchDeviceManager touchDeviceManager12 = this.L;
                    if (touchDeviceManager12 != null && (notDisturbMode2 = touchDeviceManager12.setNotDisturbMode(((TouchSetDNDReq) baseReq).getDndConfig())) != null && (observeOn27 = notDisturbMode2.observeOn(AndroidSchedulers.mainThread())) != null) {
                        Action action4 = new Action() { // from class: com.coveiot.android.bleabstract.services.j5
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                TouchELXService.d(TouchELXService.this);
                            }
                        };
                        final Function1<Throwable, Unit> function112 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$20
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchSetDNDReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable9 = observeOn27.subscribe(action4, new Consumer() { // from class: com.coveiot.android.bleabstract.services.u6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.r(Function1.this, obj);
                            }
                        });
                    }
                    this.s = disposable9;
                } else if (baseReq instanceof TouchGetDNDReq) {
                    TouchDeviceManager touchDeviceManager13 = this.L;
                    if (touchDeviceManager13 != null && (notDisturbMode = touchDeviceManager13.getNotDisturbMode()) != null && (observeOn26 = notDisturbMode.observeOn(AndroidSchedulers.mainThread())) != null) {
                        final Function1<TGNotDisturbConfig, Unit> function113 = new Function1<TGNotDisturbConfig, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$21
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(TGNotDisturbConfig tGNotDisturbConfig) {
                                TouchELXResponseListener responseListener12;
                                TGNotDisturbConfig tGNotDisturbConfig2 = tGNotDisturbConfig;
                                LogHelper.i(TouchELXService.this.getTAG(), "TouchGetDNDReq success");
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchGetDNDReq)) {
                                    TouchELXBaseRes touchELXBaseRes6 = new TouchELXBaseRes();
                                    TouchELXBaseReq khCurrentCommand = TouchELXService.this.getKhCurrentCommand();
                                    Intrinsics.checkNotNull(khCurrentCommand);
                                    touchELXBaseRes6.setBaseReq(khCurrentCommand);
                                    touchELXBaseRes6.setObj(tGNotDisturbConfig2);
                                    TouchELXBaseReq khCurrentCommand2 = TouchELXService.this.getKhCurrentCommand();
                                    if (khCurrentCommand2 != null && (responseListener12 = khCurrentCommand2.getResponseListener()) != null) {
                                        responseListener12.onResponse(touchELXBaseRes6);
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        Consumer<? super TGNotDisturbConfig> consumer5 = new Consumer() { // from class: com.coveiot.android.bleabstract.services.v6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.s(Function1.this, obj);
                            }
                        };
                        final Function1<Throwable, Unit> function114 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$22
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchGetDNDReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable10 = observeOn26.subscribe(consumer5, new Consumer() { // from class: com.coveiot.android.bleabstract.services.w6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.t(Function1.this, obj);
                            }
                        });
                    }
                    this.m = disposable10;
                } else if (baseReq instanceof TouchLiftWristReq) {
                    TouchDeviceManager touchDeviceManager14 = this.L;
                    if (touchDeviceManager14 != null && (raiseWrist2 = touchDeviceManager14.setRaiseWrist(((TouchLiftWristReq) baseReq).getRaiseWristConfig())) != null && (observeOn25 = raiseWrist2.observeOn(AndroidSchedulers.mainThread())) != null) {
                        Action action5 = new Action() { // from class: com.coveiot.android.bleabstract.services.u5
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                TouchELXService.e(TouchELXService.this);
                            }
                        };
                        final Function1<Throwable, Unit> function115 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$24
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchLiftWristReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable11 = observeOn25.subscribe(action5, new Consumer() { // from class: com.coveiot.android.bleabstract.services.x6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.u(Function1.this, obj);
                            }
                        });
                    }
                    this.q = disposable11;
                } else if (baseReq instanceof TouchGetLiftWristReq) {
                    TouchDeviceManager touchDeviceManager15 = this.L;
                    if (touchDeviceManager15 != null && (raiseWrist = touchDeviceManager15.getRaiseWrist()) != null && (observeOn24 = raiseWrist.observeOn(AndroidSchedulers.mainThread())) != null) {
                        final Function1<TGRaiseWristConfig, Unit> function116 = new Function1<TGRaiseWristConfig, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$25
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(TGRaiseWristConfig tGRaiseWristConfig) {
                                TouchELXResponseListener responseListener12;
                                TGRaiseWristConfig tGRaiseWristConfig2 = tGRaiseWristConfig;
                                LogHelper.i(TouchELXService.this.getTAG(), "TouchGetLiftWristReq success");
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchGetLiftWristReq)) {
                                    TouchELXBaseRes touchELXBaseRes6 = new TouchELXBaseRes();
                                    TouchELXBaseReq khCurrentCommand = TouchELXService.this.getKhCurrentCommand();
                                    Intrinsics.checkNotNull(khCurrentCommand);
                                    touchELXBaseRes6.setBaseReq(khCurrentCommand);
                                    touchELXBaseRes6.setObj(tGRaiseWristConfig2);
                                    LogHelper.d("lifywrist", "lifywrist from sdk startHour " + tGRaiseWristConfig2.getStartHour() + " startMinute " + tGRaiseWristConfig2.getStartMinute() + " stopHour " + tGRaiseWristConfig2.getStopHour() + " stopMinute " + tGRaiseWristConfig2.getStopMinute() + " isOn " + tGRaiseWristConfig2.isOn() + " isHasRange " + tGRaiseWristConfig2.isHasRange());
                                    TouchELXBaseReq khCurrentCommand2 = TouchELXService.this.getKhCurrentCommand();
                                    if (khCurrentCommand2 != null && (responseListener12 = khCurrentCommand2.getResponseListener()) != null) {
                                        responseListener12.onResponse(touchELXBaseRes6);
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        Consumer<? super TGRaiseWristConfig> consumer6 = new Consumer() { // from class: com.coveiot.android.bleabstract.services.y6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.v(Function1.this, obj);
                            }
                        };
                        final Function1<Throwable, Unit> function117 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$26
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchGetLiftWristReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable12 = observeOn24.subscribe(consumer6, new Consumer() { // from class: com.coveiot.android.bleabstract.services.z6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.w(Function1.this, obj);
                            }
                        });
                    }
                    this.m = disposable12;
                } else if (baseReq instanceof TouchHeartRateIntervalReq) {
                    TouchDeviceManager touchDeviceManager16 = this.L;
                    if (touchDeviceManager16 != null && (heartRateMonitoringMode = touchDeviceManager16.setHeartRateMonitoringMode(((TouchHeartRateIntervalReq) baseReq).getHrIntervalConfig())) != null && (observeOn23 = heartRateMonitoringMode.observeOn(AndroidSchedulers.mainThread())) != null) {
                        Action action6 = new Action() { // from class: com.coveiot.android.bleabstract.services.f6
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                TouchELXService.f(TouchELXService.this);
                            }
                        };
                        final Function1<Throwable, Unit> function118 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$28
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchHeartRateIntervalReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable13 = observeOn23.subscribe(action6, new Consumer() { // from class: com.coveiot.android.bleabstract.services.a7
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.x(Function1.this, obj);
                            }
                        });
                    }
                    this.t = disposable13;
                } else if (baseReq instanceof TouchStressIntervalReq) {
                    TouchDeviceManager touchDeviceManager17 = this.L;
                    if (touchDeviceManager17 != null && (stressMonitoringMode = touchDeviceManager17.setStressMonitoringMode(((TouchStressIntervalReq) baseReq).getStressIntervalConfig())) != null && (observeOn22 = stressMonitoringMode.observeOn(AndroidSchedulers.mainThread())) != null) {
                        Action action7 = new Action() { // from class: com.coveiot.android.bleabstract.services.q6
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                TouchELXService.g(TouchELXService.this);
                            }
                        };
                        final Function1<Throwable, Unit> function119 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$30
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchStressIntervalReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable14 = observeOn22.subscribe(action7, new Consumer() { // from class: com.coveiot.android.bleabstract.services.c7
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.y(Function1.this, obj);
                            }
                        });
                    }
                    this.u = disposable14;
                } else if (baseReq instanceof TouchSPO2IntervalReq) {
                    TouchDeviceManager touchDeviceManager18 = this.L;
                    if (touchDeviceManager18 != null && (spo2MonitoringMode = touchDeviceManager18.setSpo2MonitoringMode(((TouchSPO2IntervalReq) baseReq).getSpO2IntervalConfig())) != null && (observeOn21 = spo2MonitoringMode.observeOn(AndroidSchedulers.mainThread())) != null) {
                        Action action8 = new Action() { // from class: com.coveiot.android.bleabstract.services.b7
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                TouchELXService.h(TouchELXService.this);
                            }
                        };
                        final Function1<Throwable, Unit> function120 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$32
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchSPO2IntervalReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable15 = observeOn21.subscribe(action8, new Consumer() { // from class: com.coveiot.android.bleabstract.services.d7
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.z(Function1.this, obj);
                            }
                        });
                    }
                    this.v = disposable15;
                } else if (baseReq instanceof TouchSetGoalReq) {
                    TouchDeviceManager touchDeviceManager19 = this.L;
                    if (touchDeviceManager19 != null) {
                        TouchSetGoalReq touchSetGoalReq = (TouchSetGoalReq) baseReq;
                        Completable target = touchDeviceManager19.setTarget(touchSetGoalReq.getGoalType(), touchSetGoalReq.getGoal());
                        if (target != null && (observeOn20 = target.observeOn(AndroidSchedulers.mainThread())) != null) {
                            Action action9 = new Action() { // from class: com.coveiot.android.bleabstract.services.o4
                                @Override // io.reactivex.functions.Action
                                public final void run() {
                                    TouchELXService.a(TouchELXService.this, baseReq);
                                }
                            };
                            final Function1<Throwable, Unit> function121 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$34
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public Unit invoke(Throwable th) {
                                    Throwable th2 = th;
                                    if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchSetGoalReq)) {
                                        String tag = TouchELXService.this.getTAG();
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("TouchSetGoalReq baseReq.goalType ");
                                        sb.append(((TouchSetGoalReq) baseReq).getGoalType());
                                        sb.append(" goal ");
                                        sb.append(((TouchSetGoalReq) baseReq).getGoal());
                                        sb.append(" it?.message ");
                                        sb.append(th2 != null ? th2.getMessage() : null);
                                        sb.append(" it?.localizedMessage ");
                                        sb.append(th2 != null ? th2.getLocalizedMessage() : null);
                                        LogHelper.i(tag, sb.toString());
                                        TouchELXService touchELXService = TouchELXService.this;
                                        String message = th2 != null ? th2.getMessage() : null;
                                        Intrinsics.checkNotNull(message);
                                        TouchELXService.access$sendCommandFailure(touchELXService, message);
                                    }
                                    return Unit.INSTANCE;
                                }
                            };
                            disposable16 = observeOn20.subscribe(action9, new Consumer() { // from class: com.coveiot.android.bleabstract.services.r4
                                @Override // io.reactivex.functions.Consumer
                                public final void accept(Object obj) {
                                    TouchELXService.A(Function1.this, obj);
                                }
                            });
                        }
                    }
                    this.w = disposable16;
                } else if (baseReq instanceof TouchSetUnitReq) {
                    TouchDeviceManager touchDeviceManager20 = this.L;
                    if (touchDeviceManager20 != null && (unit = touchDeviceManager20.setUnit(((TouchSetUnitReq) baseReq).getUnitConfig())) != null && (observeOn19 = unit.observeOn(AndroidSchedulers.mainThread())) != null) {
                        Action action10 = new Action() { // from class: com.coveiot.android.bleabstract.services.f7
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                TouchELXService.i(TouchELXService.this);
                            }
                        };
                        final Function1<Throwable, Unit> function122 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$36
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchSetUnitReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable17 = observeOn19.subscribe(action10, new Consumer() { // from class: com.coveiot.android.bleabstract.services.s4
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.B(Function1.this, obj);
                            }
                        });
                    }
                    this.x = disposable17;
                } else if (baseReq instanceof TouchSetMsgNotificationReq) {
                    TouchDeviceManager touchDeviceManager21 = this.L;
                    if (touchDeviceManager21 != null && (syncMessage = touchDeviceManager21.syncMessage(((TouchSetMsgNotificationReq) baseReq).getTgMessage())) != null && (observeOn18 = syncMessage.observeOn(AndroidSchedulers.mainThread())) != null) {
                        final Function1<Integer, Unit> function123 = new Function1<Integer, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$37
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Integer num) {
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchSetMsgNotificationReq)) {
                                    LogHelper.i(TouchELXService.this.getTAG(), "TouchSetMsgNotificationReq success");
                                    TouchELXService.this.d();
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        Consumer<? super Integer> consumer7 = new Consumer() { // from class: com.coveiot.android.bleabstract.services.t4
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.C(Function1.this, obj);
                            }
                        };
                        final Function1<Throwable, Unit> function124 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$38
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchSetMsgNotificationReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable18 = observeOn18.subscribe(consumer7, new Consumer() { // from class: com.coveiot.android.bleabstract.services.u4
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.D(Function1.this, obj);
                            }
                        });
                    }
                    this.y = disposable18;
                } else if (baseReq instanceof TouchSetCallNotificationReq) {
                    String name = BleApiManager.getInstance(this).getDeviceType() == DeviceType.TOUCH_WAVE_NEO ? "" : ((TouchSetCallNotificationReq) baseReq).getName();
                    TouchDeviceManager touchDeviceManager22 = this.L;
                    if (touchDeviceManager22 != null && (remindCall = touchDeviceManager22.remindCall(name, ((TouchSetCallNotificationReq) baseReq).getNumber())) != null && (observeOn17 = remindCall.observeOn(AndroidSchedulers.mainThread())) != null) {
                        Action action11 = new Action() { // from class: com.coveiot.android.bleabstract.services.g7
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                TouchELXService.j(TouchELXService.this);
                            }
                        };
                        final Function1<Throwable, Unit> function125 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$40
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchSetCallNotificationReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable19 = observeOn17.subscribe(action11, new Consumer() { // from class: com.coveiot.android.bleabstract.services.v4
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.E(Function1.this, obj);
                            }
                        });
                    }
                    this.y = disposable19;
                } else if (baseReq instanceof TouchSetWeatherUnitReq) {
                    TouchDeviceManager touchDeviceManager23 = this.L;
                    if (touchDeviceManager23 != null && (weatherOnOff = touchDeviceManager23.setWeatherOnOff(((TouchSetWeatherUnitReq) baseReq).isEnabled())) != null && (observeOn16 = weatherOnOff.observeOn(AndroidSchedulers.mainThread())) != null) {
                        Action action12 = new Action() { // from class: com.coveiot.android.bleabstract.services.p4
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                TouchELXService.b(TouchELXService.this, baseReq);
                            }
                        };
                        final Function1<Throwable, Unit> function126 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$42
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchSetWeatherUnitReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable20 = observeOn16.subscribe(action12, new Consumer() { // from class: com.coveiot.android.bleabstract.services.x4
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.G(Function1.this, obj);
                            }
                        });
                    }
                    this.x = disposable20;
                } else if (baseReq instanceof TouchSetWeatherConfigReq) {
                    TouchDeviceManager touchDeviceManager24 = this.L;
                    if (touchDeviceManager24 != null && (weather = touchDeviceManager24.setWeather(((TouchSetWeatherConfigReq) baseReq).getWeather())) != null && (observeOn15 = weather.observeOn(AndroidSchedulers.mainThread())) != null) {
                        Action action13 = new Action() { // from class: com.coveiot.android.bleabstract.services.q4
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                TouchELXService.c(TouchELXService.this, baseReq);
                            }
                        };
                        final Function1<Throwable, Unit> function127 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$44
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchSetWeatherConfigReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable21 = observeOn15.subscribe(action13, new Consumer() { // from class: com.coveiot.android.bleabstract.services.a5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.I(Function1.this, obj);
                            }
                        });
                    }
                    this.z = disposable21;
                } else if (baseReq instanceof TouchFemaleWellnessConfigReq) {
                    TouchDeviceManager touchDeviceManager25 = this.L;
                    if (touchDeviceManager25 != null && (physiologicalCycle = touchDeviceManager25.setPhysiologicalCycle(((TouchFemaleWellnessConfigReq) baseReq).getTgPhysiologicalCycle())) != null && (observeOn14 = physiologicalCycle.observeOn(AndroidSchedulers.mainThread())) != null) {
                        Action action14 = new Action() { // from class: com.coveiot.android.bleabstract.services.f4
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                TouchELXService.m(TouchELXService.this);
                            }
                        };
                        final Function1<Throwable, Unit> function128 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$46
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchFemaleWellnessConfigReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable22 = observeOn14.subscribe(action14, new Consumer() { // from class: com.coveiot.android.bleabstract.services.b5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.J(Function1.this, obj);
                            }
                        });
                    }
                    this.A = disposable22;
                } else if (baseReq instanceof TouchMusicMetaDataReq) {
                    TouchDeviceManager touchDeviceManager26 = this.L;
                    if (touchDeviceManager26 != null && (syncMusic = touchDeviceManager26.syncMusic(((TouchMusicMetaDataReq) baseReq).getMusicInfo())) != null && (observeOn13 = syncMusic.observeOn(AndroidSchedulers.mainThread())) != null) {
                        final Function1<Integer, Unit> function129 = new Function1<Integer, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$47
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Integer num) {
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchMusicMetaDataReq)) {
                                    LogHelper.i(TouchELXService.this.getTAG(), "TouchMusicMetaDataReq success");
                                    TouchELXService.this.d();
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        Consumer<? super Integer> consumer8 = new Consumer() { // from class: com.coveiot.android.bleabstract.services.c5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.K(Function1.this, obj);
                            }
                        };
                        final Function1<Throwable, Unit> function130 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$48
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchMusicMetaDataReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable23 = observeOn13.subscribe(consumer8, new Consumer() { // from class: com.coveiot.android.bleabstract.services.d5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.L(Function1.this, obj);
                            }
                        });
                    }
                    this.B = disposable23;
                } else if (baseReq instanceof TouchFindDeviceReq) {
                    TouchDeviceManager touchDeviceManager27 = this.L;
                    if (touchDeviceManager27 != null) {
                        TouchFindDeviceReq touchFindDeviceReq = (TouchFindDeviceReq) baseReq;
                        Completable findDevice = touchDeviceManager27.findDevice(touchFindDeviceReq.getEnable(), touchFindDeviceReq.getTimeout());
                        if (findDevice != null && (observeOn12 = findDevice.observeOn(AndroidSchedulers.mainThread())) != null) {
                            Action action15 = new Action() { // from class: com.coveiot.android.bleabstract.services.g4
                                @Override // io.reactivex.functions.Action
                                public final void run() {
                                    TouchELXService.n(TouchELXService.this);
                                }
                            };
                            final Function1<Throwable, Unit> function131 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$50
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public Unit invoke(Throwable th) {
                                    Throwable th2 = th;
                                    if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchFindDeviceReq)) {
                                        TouchELXService touchELXService = TouchELXService.this;
                                        String message = th2 != null ? th2.getMessage() : null;
                                        Intrinsics.checkNotNull(message);
                                        TouchELXService.access$sendCommandFailure(touchELXService, message);
                                    }
                                    return Unit.INSTANCE;
                                }
                            };
                            disposable24 = observeOn12.subscribe(action15, new Consumer() { // from class: com.coveiot.android.bleabstract.services.e5
                                @Override // io.reactivex.functions.Consumer
                                public final void accept(Object obj) {
                                    TouchELXService.M(Function1.this, obj);
                                }
                            });
                        }
                    }
                    this.C = disposable24;
                } else if (baseReq instanceof TouchQuickReplyReq) {
                    TouchDeviceManager touchDeviceManager28 = this.L;
                    if (touchDeviceManager28 != null && (syncQuickReply = touchDeviceManager28.syncQuickReply(((TouchQuickReplyReq) baseReq).getQuickReplyList())) != null && (observeOn11 = syncQuickReply.observeOn(AndroidSchedulers.mainThread())) != null) {
                        Action action16 = new Action() { // from class: com.coveiot.android.bleabstract.services.h4
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                TouchELXService.o(TouchELXService.this);
                            }
                        };
                        final Function1<Throwable, Unit> function132 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$52
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchQuickReplyReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable25 = observeOn11.subscribe(action16, new Consumer() { // from class: com.coveiot.android.bleabstract.services.f5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.N(Function1.this, obj);
                            }
                        });
                    }
                    this.E = disposable25;
                } else if (baseReq instanceof TouchSyncContactsReq) {
                    TouchDeviceManager touchDeviceManager29 = this.L;
                    if (touchDeviceManager29 != null && (contacts = touchDeviceManager29.setContacts(((TouchSyncContactsReq) baseReq).getContactsList())) != null && (observeOn10 = contacts.observeOn(AndroidSchedulers.mainThread())) != null) {
                        final Function1<Integer, Unit> function133 = new Function1<Integer, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$53
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Integer num) {
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchSyncContactsReq)) {
                                    LogHelper.i(TouchELXService.this.getTAG(), "TouchSyncContactsReq success");
                                    TouchELXService.this.d();
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        Consumer<? super Integer> consumer9 = new Consumer() { // from class: com.coveiot.android.bleabstract.services.g5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.O(Function1.this, obj);
                            }
                        };
                        final Function1<Throwable, Unit> function134 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$54
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchSyncContactsReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable26 = observeOn10.subscribe(consumer9, new Consumer() { // from class: com.coveiot.android.bleabstract.services.h5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.P(Function1.this, obj);
                            }
                        });
                    }
                    this.G = disposable26;
                } else if (baseReq instanceof TouchSetWorkoutModesReq) {
                    TouchDeviceManager touchDeviceManager30 = this.L;
                    if (touchDeviceManager30 != null && (activityMode = touchDeviceManager30.setActivityMode(((TouchSetWorkoutModesReq) baseReq).getTgWorkoutMode())) != null && (observeOn9 = activityMode.observeOn(AndroidSchedulers.mainThread())) != null) {
                        final Function1<Integer, Unit> function135 = new Function1<Integer, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$55
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Integer num) {
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchSetWorkoutModesReq)) {
                                    LogHelper.i(TouchELXService.this.getTAG(), "TouchSetWorkoutModesReq success");
                                    TouchELXService.this.d();
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        Consumer<? super Integer> consumer10 = new Consumer() { // from class: com.coveiot.android.bleabstract.services.i5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.Q(Function1.this, obj);
                            }
                        };
                        final Function1<Throwable, Unit> function136 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$56
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchSetWorkoutModesReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable27 = observeOn9.subscribe(consumer10, new Consumer() { // from class: com.coveiot.android.bleabstract.services.k5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.R(Function1.this, obj);
                            }
                        });
                    }
                    this.H = disposable27;
                } else if (baseReq instanceof TouchGetWorkoutModesReq) {
                    TouchDeviceManager touchDeviceManager31 = this.L;
                    if (touchDeviceManager31 != null && (getSupportedWorkoutList = touchDeviceManager31.getGetSupportedWorkoutList()) != null && (observeOn8 = getSupportedWorkoutList.observeOn(AndroidSchedulers.mainThread())) != null) {
                        final TouchELXService$sendRequest$57 touchELXService$sendRequest$57 = new TouchELXService$sendRequest$57(this);
                        Consumer<? super TGWorkoutSupportList> consumer11 = new Consumer() { // from class: com.coveiot.android.bleabstract.services.l5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.S(Function1.this, obj);
                            }
                        };
                        final Function1<Throwable, Unit> function137 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$58
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchGetWorkoutModesReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable28 = observeOn8.subscribe(consumer11, new Consumer() { // from class: com.coveiot.android.bleabstract.services.m5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.T(Function1.this, obj);
                            }
                        });
                    }
                    this.H = disposable28;
                } else if (baseReq instanceof TouchSetQuickCardsReq) {
                    TouchDeviceManager touchDeviceManager32 = this.L;
                    if (touchDeviceManager32 != null && (quickCards = touchDeviceManager32.setQuickCards(((TouchSetQuickCardsReq) baseReq).getTgQuickCardList())) != null && (observeOn7 = quickCards.observeOn(AndroidSchedulers.mainThread())) != null) {
                        Action action17 = new Action() { // from class: com.coveiot.android.bleabstract.services.i4
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                TouchELXService.p(TouchELXService.this);
                            }
                        };
                        final Function1<Throwable, Unit> function138 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$60
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchSetQuickCardsReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable29 = observeOn7.subscribe(action17, new Consumer() { // from class: com.coveiot.android.bleabstract.services.n5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.U(Function1.this, obj);
                            }
                        });
                    }
                    this.I = disposable29;
                } else if (baseReq instanceof TouchGetQuickCardReq) {
                    TouchDeviceManager touchDeviceManager33 = this.L;
                    if (touchDeviceManager33 != null && (getQuickCards = touchDeviceManager33.getGetQuickCards()) != null && (observeOn6 = getQuickCards.observeOn(AndroidSchedulers.mainThread())) != null) {
                        final Function1<List<? extends TGQuickCard>, Unit> function139 = new Function1<List<? extends TGQuickCard>, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$61
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(List<? extends TGQuickCard> list) {
                                TouchELXResponseListener responseListener12;
                                List<? extends TGQuickCard> list2 = list;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchGetQuickCardReq)) {
                                    LogHelper.i(TouchELXService.this.getTAG(), "TouchGetQuickCardReq success");
                                    TouchELXBaseRes touchELXBaseRes6 = new TouchELXBaseRes();
                                    TouchELXBaseReq khCurrentCommand = TouchELXService.this.getKhCurrentCommand();
                                    Intrinsics.checkNotNull(khCurrentCommand);
                                    touchELXBaseRes6.setBaseReq(khCurrentCommand);
                                    touchELXBaseRes6.setObj(list2);
                                    TouchELXBaseReq khCurrentCommand2 = TouchELXService.this.getKhCurrentCommand();
                                    if (khCurrentCommand2 != null && (responseListener12 = khCurrentCommand2.getResponseListener()) != null) {
                                        responseListener12.onResponse(touchELXBaseRes6);
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        Consumer<? super List<TGQuickCard>> consumer12 = new Consumer() { // from class: com.coveiot.android.bleabstract.services.o5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.V(Function1.this, obj);
                            }
                        };
                        final Function1<Throwable, Unit> function140 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$62
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchGetQuickCardReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable30 = observeOn6.subscribe(consumer12, new Consumer() { // from class: com.coveiot.android.bleabstract.services.p5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.W(Function1.this, obj);
                            }
                        });
                    }
                    this.I = disposable30;
                } else if (baseReq instanceof TouchCameraControlReq) {
                    TouchDeviceManager touchDeviceManager34 = this.L;
                    if (touchDeviceManager34 != null && (cameraOnOff = touchDeviceManager34.setCameraOnOff(((TouchCameraControlReq) baseReq).isEnabled())) != null && (observeOn5 = cameraOnOff.observeOn(AndroidSchedulers.mainThread())) != null) {
                        Action action18 = new Action() { // from class: com.coveiot.android.bleabstract.services.j4
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                TouchELXService.q(TouchELXService.this);
                            }
                        };
                        final Function1<Throwable, Unit> function141 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$64
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchCameraControlReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable31 = observeOn5.subscribe(action18, new Consumer() { // from class: com.coveiot.android.bleabstract.services.q5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.X(Function1.this, obj);
                            }
                        });
                    }
                    this.F = disposable31;
                } else if (baseReq instanceof TouchDefaultWatchFaceReq) {
                    TouchDeviceManager touchDeviceManager35 = this.L;
                    if (touchDeviceManager35 != null && (applyDial = touchDeviceManager35.applyDial(Integer.valueOf(((TouchDefaultWatchFaceReq) baseReq).getWatchfacePosition()))) != null && (observeOn4 = applyDial.observeOn(AndroidSchedulers.mainThread())) != null) {
                        final Function1<Integer, Unit> function142 = new Function1<Integer, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$65
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Integer num) {
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchDefaultWatchFaceReq)) {
                                    LogHelper.i(TouchELXService.this.getTAG(), "TouchDefaultWatchFaceReq success");
                                    TouchELXService.this.d();
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        Consumer<? super Integer> consumer13 = new Consumer() { // from class: com.coveiot.android.bleabstract.services.r5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.Y(Function1.this, obj);
                            }
                        };
                        final Function1<Throwable, Unit> function143 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$66
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchDefaultWatchFaceReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable32 = observeOn4.subscribe(consumer13, new Consumer() { // from class: com.coveiot.android.bleabstract.services.s5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.Z(Function1.this, obj);
                            }
                        });
                    }
                    this.J = disposable32;
                } else if (baseReq instanceof TouchCloudWatchFaceReq) {
                    TouchDeviceManager touchDeviceManager36 = this.L;
                    if (touchDeviceManager36 != null) {
                        TouchCloudWatchFaceReq touchCloudWatchFaceReq = (TouchCloudWatchFaceReq) baseReq;
                        touchDeviceManager36.syncCloudDial(Integer.valueOf(touchCloudWatchFaceReq.getWatchfacePosition()), touchCloudWatchFaceReq.getWatchFaceFilePath());
                    }
                } else if (baseReq instanceof TouchDIYWatchFaceReq) {
                    TouchDeviceManager touchDeviceManager37 = this.L;
                    if (touchDeviceManager37 != null) {
                        touchDeviceManager37.syncBackgroundDial(((TouchDIYWatchFaceReq) baseReq).getPhotoDialBuilder());
                    }
                } else if (baseReq instanceof TouchEventReminderReq) {
                    TouchDeviceManager touchDeviceManager38 = this.L;
                    if (touchDeviceManager38 != null && (eventReminders = touchDeviceManager38.setEventReminders(((TouchEventReminderReq) baseReq).getReminderList())) != null && (observeOn3 = eventReminders.observeOn(AndroidSchedulers.mainThread())) != null) {
                        final Function1<Integer, Unit> function144 = new Function1<Integer, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$67
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Integer num) {
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchEventReminderReq)) {
                                    LogHelper.i(TouchELXService.this.getTAG(), "TouchEventReminderReq success");
                                    TouchELXService.this.d();
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        Consumer<? super Integer> consumer14 = new Consumer() { // from class: com.coveiot.android.bleabstract.services.v5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.a0(Function1.this, obj);
                            }
                        };
                        final Function1<Throwable, Unit> function145 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$68
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchEventReminderReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable33 = observeOn3.subscribe(consumer14, new Consumer() { // from class: com.coveiot.android.bleabstract.services.x5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.b0(Function1.this, obj);
                            }
                        });
                    }
                    this.K = disposable33;
                } else if (baseReq instanceof TouchGetEventReminderReq) {
                    TouchDeviceManager touchDeviceManager39 = this.L;
                    if (touchDeviceManager39 != null && (getEventReminders = touchDeviceManager39.getGetEventReminders()) != null && (observeOn2 = getEventReminders.observeOn(AndroidSchedulers.mainThread())) != null) {
                        final Function1<List<? extends TGEventReminder>, Unit> function146 = new Function1<List<? extends TGEventReminder>, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$69
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(List<? extends TGEventReminder> list) {
                                TouchELXResponseListener responseListener12;
                                List<? extends TGEventReminder> list2 = list;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchGetEventReminderReq)) {
                                    LogHelper.i(TouchELXService.this.getTAG(), "TouchGetEventReminderReq success");
                                    TouchELXBaseRes touchELXBaseRes6 = new TouchELXBaseRes();
                                    TouchELXBaseReq khCurrentCommand = TouchELXService.this.getKhCurrentCommand();
                                    Intrinsics.checkNotNull(khCurrentCommand);
                                    touchELXBaseRes6.setBaseReq(khCurrentCommand);
                                    touchELXBaseRes6.setObj(list2);
                                    TouchELXBaseReq khCurrentCommand2 = TouchELXService.this.getKhCurrentCommand();
                                    if (khCurrentCommand2 != null && (responseListener12 = khCurrentCommand2.getResponseListener()) != null) {
                                        responseListener12.onResponse(touchELXBaseRes6);
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        Consumer<? super List<TGEventReminder>> consumer15 = new Consumer() { // from class: com.coveiot.android.bleabstract.services.z5
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.c0(Function1.this, obj);
                            }
                        };
                        final Function1<Throwable, Unit> function147 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$70
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchGetEventReminderReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable34 = observeOn2.subscribe(consumer15, new Consumer() { // from class: com.coveiot.android.bleabstract.services.b6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.d0(Function1.this, obj);
                            }
                        });
                    }
                    this.K = disposable34;
                } else if (baseReq instanceof TouchSetCallStatusReq) {
                    TouchDeviceManager touchDeviceManager40 = this.L;
                    if (touchDeviceManager40 != null && (syncCallStatus = touchDeviceManager40.syncCallStatus(((TouchSetCallStatusReq) baseReq).getStatus())) != null && (observeOn = syncCallStatus.observeOn(AndroidSchedulers.mainThread())) != null) {
                        Action action19 = new Action() { // from class: com.coveiot.android.bleabstract.services.k4
                            @Override // io.reactivex.functions.Action
                            public final void run() {
                                TouchELXService.r(TouchELXService.this);
                            }
                        };
                        final Function1<Throwable, Unit> function148 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$72
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public Unit invoke(Throwable th) {
                                Throwable th2 = th;
                                if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchSetCallStatusReq)) {
                                    TouchELXService touchELXService = TouchELXService.this;
                                    String message = th2 != null ? th2.getMessage() : null;
                                    Intrinsics.checkNotNull(message);
                                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        disposable = observeOn.subscribe(action19, new Consumer() { // from class: com.coveiot.android.bleabstract.services.d6
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                TouchELXService.e0(Function1.this, obj);
                            }
                        });
                    }
                    this.y = disposable;
                }
            }
        }
    }

    public final void setKhCurrentCommand(@Nullable TouchELXBaseReq touchELXBaseReq) {
        this.h = touchELXBaseReq;
    }

    public final void setMBluetoothDevice(@Nullable BluetoothDevice bluetoothDevice) {
        this.f = bluetoothDevice;
    }

    public final void setMTouchDeviceManager(@Nullable TouchDeviceManager touchDeviceManager) {
        this.L = touchDeviceManager;
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
        disconnectAndForget();
    }

    public void stopServiceRetainMacAddress() {
        disconnectAndRetainMacAddress();
    }

    public void unregisterReceivers() {
        if (this.O != null) {
            try {
                getApplicationContext().unregisterReceiver(this.O);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                BleEventBusManager.getInstance().getEventBus().unregister(this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        TouchDeviceManager touchDeviceManager = this.L;
        if (touchDeviceManager != null) {
            touchDeviceManager.unregisterConnectCallback(this.N);
        }
        TouchDeviceManager touchDeviceManager2 = this.L;
        if (touchDeviceManager2 != null) {
            touchDeviceManager2.removeOnHealthDataListener(this.P);
        }
        TouchDeviceManager touchDeviceManager3 = this.L;
        if (touchDeviceManager3 != null) {
            touchDeviceManager3.unregisterWorkoutDataCallback(this.Q);
        }
        TouchDeviceManager touchDeviceManager4 = this.L;
        if (touchDeviceManager4 != null) {
            touchDeviceManager4.removeOnSyncDialListener(this.S);
        }
        TouchDeviceManager touchDeviceManager5 = this.L;
        if (touchDeviceManager5 != null) {
            touchDeviceManager5.removeEventListener(this.R);
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
        handler2.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.e7
            @Override // java.lang.Runnable
            public final void run() {
                TouchELXService.a(CloveBleState.BleState.this);
            }
        });
    }

    public static final void d(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchSetDNDReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "TouchSetDNDReq success");
        this$0.d();
    }

    public static final void g(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchStressIntervalReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "TouchStressIntervalReq success");
        this$0.d();
    }

    public static final void h(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchSPO2IntervalReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "TouchSPO2IntervalReq success");
        this$0.d();
    }

    public static final void i(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchSetUnitReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "TouchSetUnitReq success");
        this$0.d();
    }

    public static final void j(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchSetCallNotificationReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "TouchSetCallNotificationReq success");
        this$0.d();
    }

    public static final void k(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchSetWeatherUnitReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "TouchSetWeatherUnitReq success");
        this$0.d();
    }

    public static final void l(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3935a, "TouchSetWeather setCity success");
        this$0.d();
    }

    public static final void m(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchFemaleWellnessConfigReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "TouchFemaleWellnessConfigReq success");
        this$0.d();
    }

    public static final void n(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchFindDeviceReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "TouchFindDeviceReq success");
        this$0.d();
    }

    public static final void o(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchQuickReplyReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "TouchQuickReplyReq success");
        this$0.d();
    }

    public static final void p(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchSetQuickCardsReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "TouchSetQuickCardsReq success");
        this$0.d();
    }

    public static final void q(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchCameraControlReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "TouchCameraControlReq success");
        this$0.d();
    }

    public static final void r(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchSetCallStatusReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "TouchSetCallStatusReq success");
        this$0.d();
    }

    public static final void s(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void t(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void d() {
        TouchELXResponseListener responseListener;
        if (this.h != null) {
            TouchELXBaseRes touchELXBaseRes = new TouchELXBaseRes();
            TouchELXBaseReq touchELXBaseReq = this.h;
            Intrinsics.checkNotNull(touchELXBaseReq);
            touchELXBaseRes.setBaseReq(touchELXBaseReq);
            TouchELXBaseReq touchELXBaseReq2 = this.h;
            if (touchELXBaseReq2 != null && (responseListener = touchELXBaseReq2.getResponseListener()) != null) {
                responseListener.onResponse(touchELXBaseRes);
            }
            String str = this.f3935a;
            LogHelper.d(str, "sendCommandSuccessResponse->Set khCurrentCommand to null->" + this.h);
        }
    }

    public static final void a(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void c(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void e(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void f(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void c(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchDrinkReminderReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "TouchDrinkReminderReq success");
        this$0.d();
    }

    public static final void e(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchLiftWristReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "TouchLiftWristReq success");
        this$0.d();
    }

    public static final void f(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchHeartRateIntervalReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "TouchHeartRateIntervalReq success");
        this$0.d();
    }

    public final void a() {
        Disposable disposable;
        Observable<String> btMac;
        Observable<String> observeOn;
        TouchDeviceManager touchDeviceManager = this.L;
        if (touchDeviceManager == null || (btMac = touchDeviceManager.getBtMac()) == null || (observeOn = btMac.observeOn(AndroidSchedulers.mainThread())) == null) {
            disposable = null;
        } else {
            final Function1<String, Unit> function1 = new Function1<String, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$createBondWithBTMac$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(String str) {
                    String str2 = str;
                    PreferenceManagerAbstract preferenceManagerAbstract = PreferenceManagerAbstract.getInstance(TouchELXService.this);
                    Intrinsics.checkNotNull(str2);
                    preferenceManagerAbstract.saveConnectedDeviceBT3MacAddress(str2);
                    TouchELXService.access$createBondDevice(TouchELXService.this, str2);
                    return Unit.INSTANCE;
                }
            };
            Consumer<? super String> consumer = new Consumer() { // from class: com.coveiot.android.bleabstract.services.y5
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TouchELXService.c(Function1.this, obj);
                }
            };
            final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$createBondWithBTMac$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(Throwable th) {
                    final Throwable th2 = th;
                    final TouchELXService touchELXService = TouchELXService.this;
                    new Function0<Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$createBondWithBTMac$2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public Unit invoke() {
                            String tag = TouchELXService.this.getTAG();
                            StringBuilder sb = new StringBuilder();
                            sb.append("createBondWithBTMac error");
                            Throwable th3 = th2;
                            sb.append(th3 != null ? th3.getMessage() : null);
                            LogHelper.i(tag, sb.toString());
                            return Unit.INSTANCE;
                        }
                    };
                    return Unit.INSTANCE;
                }
            };
            disposable = observeOn.subscribe(consumer, new Consumer() { // from class: com.coveiot.android.bleabstract.services.a6
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TouchELXService.d(Function1.this, obj);
                }
            });
        }
        this.D = disposable;
        f();
        e();
    }

    public static final void c(final TouchELXService this$0, TouchELXBaseReq baseReq) {
        Disposable disposable;
        Completable city;
        Completable observeOn;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseReq, "$baseReq");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchSetWeatherConfigReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "TouchSetWeatherConfigReq success");
        TouchDeviceManager touchDeviceManager = this$0.L;
        if (touchDeviceManager == null || (city = touchDeviceManager.setCity(((TouchSetWeatherConfigReq) baseReq).getCityName())) == null || (observeOn = city.observeOn(AndroidSchedulers.mainThread())) == null) {
            disposable = null;
        } else {
            Action action = new Action() { // from class: com.coveiot.android.bleabstract.services.e4
                @Override // io.reactivex.functions.Action
                public final void run() {
                    TouchELXService.l(TouchELXService.this);
                }
            };
            final Function1<Throwable, Unit> function1 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$43$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(Throwable th) {
                    Throwable th2 = th;
                    LogHelper.i(TouchELXService.this.getTAG(), "TouchSetWeather setCity failure");
                    TouchELXService touchELXService = TouchELXService.this;
                    String message = th2 != null ? th2.getMessage() : null;
                    Intrinsics.checkNotNull(message);
                    TouchELXService.access$sendCommandFailure(touchELXService, message);
                    return Unit.INSTANCE;
                }
            };
            disposable = observeOn.subscribe(action, new Consumer() { // from class: com.coveiot.android.bleabstract.services.z4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TouchELXService.H(Function1.this, obj);
                }
            });
        }
        this$0.z = disposable;
    }

    public static final void a(CloveBleState.BleState state) {
        Intrinsics.checkNotNullParameter(state, "$state");
        BleEventBusManager.getInstance().getEventBus().post(new CloveBleState(state));
    }

    public final void a(boolean z) {
        TouchDeviceManager touchDeviceManager = this.L;
        if (touchDeviceManager != null) {
            touchDeviceManager.disconnect(z);
        }
    }

    public static final void a(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.i(this$0.f3935a, "TouchUserInfoReq success");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchUserInfoReq)) {
            return;
        }
        this$0.d();
    }

    public static final void a(TouchELXService this$0, TouchELXBaseReq baseReq) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseReq, "$baseReq");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchSetGoalReq)) {
            return;
        }
        String str = this$0.f3935a;
        StringBuilder sb = new StringBuilder();
        sb.append("TouchSetGoalReq success baseReq.goalType ");
        TouchSetGoalReq touchSetGoalReq = (TouchSetGoalReq) baseReq;
        sb.append(touchSetGoalReq.getGoalType());
        sb.append(" goal ");
        sb.append(touchSetGoalReq.getGoal());
        LogHelper.i(str, sb.toString());
        this$0.d();
    }

    public static final void b(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void b(TouchELXService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchSedentaryReminderReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "TouchSedentaryReminderReq success");
        this$0.d();
    }

    public static final void b(final TouchELXService this$0, TouchELXBaseReq baseReq) {
        Disposable disposable;
        Completable unit;
        Completable observeOn;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseReq, "$baseReq");
        TouchELXBaseReq touchELXBaseReq = this$0.h;
        if (touchELXBaseReq == null || !(touchELXBaseReq instanceof TouchSetWeatherUnitReq)) {
            return;
        }
        LogHelper.i(this$0.f3935a, "setWeatherOnOff success");
        TouchDeviceManager touchDeviceManager = this$0.L;
        if (touchDeviceManager == null || (unit = touchDeviceManager.setUnit(((TouchSetWeatherUnitReq) baseReq).getUnitConfig())) == null || (observeOn = unit.observeOn(AndroidSchedulers.mainThread())) == null) {
            disposable = null;
        } else {
            Action action = new Action() { // from class: com.coveiot.android.bleabstract.services.d4
                @Override // io.reactivex.functions.Action
                public final void run() {
                    TouchELXService.k(TouchELXService.this);
                }
            };
            final Function1<Throwable, Unit> function1 = new Function1<Throwable, Unit>() { // from class: com.coveiot.android.bleabstract.services.TouchELXService$sendRequest$41$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(Throwable th) {
                    Throwable th2 = th;
                    if (TouchELXService.this.getKhCurrentCommand() != null && (TouchELXService.this.getKhCurrentCommand() instanceof TouchSetWeatherUnitReq)) {
                        TouchELXService touchELXService = TouchELXService.this;
                        String message = th2 != null ? th2.getMessage() : null;
                        Intrinsics.checkNotNull(message);
                        TouchELXService.access$sendCommandFailure(touchELXService, message);
                    }
                    return Unit.INSTANCE;
                }
            };
            disposable = observeOn.subscribe(action, new Consumer() { // from class: com.coveiot.android.bleabstract.services.w4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TouchELXService.F(Function1.this, obj);
                }
            });
        }
        this$0.x = disposable;
    }
}
