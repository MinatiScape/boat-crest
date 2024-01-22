package com.coveiot.android.bleabstract.services;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.services.FirebaseEventParams;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.api.ResponseListener;
import com.coveiot.sdk.ble.api.error.Error;
import com.coveiot.sdk.ble.api.error.Type;
import com.coveiot.sdk.ble.api.model.ProgressDataBean;
import com.coveiot.sdk.ble.api.model.ProgressType;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.request.CommandBytes;
import com.coveiot.sdk.ble.api.request.CustomWatchFaceUploadReq;
import com.coveiot.sdk.ble.api.request.GetNearbyDeviceListReq;
import com.coveiot.sdk.ble.api.request.ReadRawPPGDataReq;
import com.coveiot.sdk.ble.api.request.SaveFitnessProfileReq;
import com.coveiot.sdk.ble.api.request.SaveFitnessProfileReqOld;
import com.coveiot.sdk.ble.api.response.CustomWatchFaceUploadRes;
import com.coveiot.sdk.ble.api.response.ReadRawPPGDataRes;
import com.coveiot.sdk.ble.api.response.ReadSedentaryDataRes;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.events.ProcessNextItemEvent;
import com.coveiot.sdk.ble.events.StartRunnableEvent;
import com.coveiot.sdk.ble.model.CommandObject;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.model.RequestPayload;
import com.coveiot.sdk.ble.utils.BlePreferenceManager;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.sdk.ble.utils.CommonPreference;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.sifli.watchfacelibrary.SifliWatchfaceService;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public final class LeonardoBleCmdService extends LeonardoBleService {
    public static final int MULTIPACKET_CMD_MS_TIMER = 30000;
    public static final int NORMAL_CMD_MS_TIMER = 10000;
    public volatile boolean W;
    public static final String Z = LeonardoBleCmdService.class.getSimpleName();
    public static final long CONTACT_TRACING_DATA_TIMER = TimeUnit.MINUTES.toMillis(25);
    public final IBinder U = new BtServiceBinder();
    public boolean V = false;
    public Runnable X = new Runnable() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleCmdService.1
        @Override // java.lang.Runnable
        public void run() {
            CommandObject commandObject;
            CommandObject commandObject2;
            String str = LeonardoBleCmdService.Z;
            ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
            LogHelper.d(str, "cmdHandler called", moduleNames.getModuleName());
            LeonardoBleCmdService.this.o.removeCallbacksAndMessages(null);
            CommandObject commandObject3 = LeonardoBleCmdService.this.currentCommand;
            if (commandObject3 != null && !commandObject3.isCompleted()) {
                CommandObject commandObject4 = LeonardoBleCmdService.this.currentCommand;
                if (commandObject4.shouldWaitForRes && commandObject4.getCmdName() != CommandNames.GET_SEDENTARY_DATA && LeonardoBleCmdService.this.currentCommand.getCmdName() != CommandNames.GET_DIAGNOSTIC_FEATURE_TEST) {
                    if (LeonardoBleCmdService.this.currentCommand.getCmdName() == CommandNames.SET_USER_FITNESS_INFO) {
                        SaveFitnessProfileReq saveFitnessProfileReq = (SaveFitnessProfileReq) LeonardoBleCmdService.this.currentCommand.getBaseRequest();
                        SaveFitnessProfileReqOld build = new SaveFitnessProfileReqOld.Builder(saveFitnessProfileReq.getHeight(), saveFitnessProfileReq.getWeight(), saveFitnessProfileReq.getStrideLength(), saveFitnessProfileReq.isMale()).build();
                        build.setReqId(saveFitnessProfileReq.getReqId());
                        build.setResponseListener(saveFitnessProfileReq.getResponseListener());
                        LeonardoBleCmdService.this.c(build);
                        LeonardoBleCmdService.this.currentCommand = null;
                        return;
                    }
                    LogHelper.d(str, "Interrupt command " + LeonardoBleCmdService.this.currentCommand, moduleNames.getModuleName());
                    CommandObject commandObject5 = LeonardoBleCmdService.this.currentCommand;
                    if (commandObject5 != null && commandObject5.getResponseListener() != null) {
                        LeonardoBleCmdService.this.currentCommand.getResponseListener().onFailure(new Error(Type.COMMAND_TIMED_OUT, "Something went wrong"));
                        LogHelper.d(str, "Time out for ", Arrays.toString(LeonardoBleCmdService.this.currentCommand.getCmdByte()));
                    } else {
                        Bus eventBus = BleEventBusManager.getInstance().getEventBus();
                        Type type = Type.COMMAND_REQUEST_ERROR;
                        eventBus.post(new Error(type, "No Response from band for " + Arrays.toString(LeonardoBleCmdService.this.currentCommand.getCmdByte())));
                    }
                    if (!BleApiManager.getInstance(LeonardoBleCmdService.this).getBleApi().getDeviceSupportedFeatures().isPhoneTypeCommandSupported()) {
                        CommandObject commandObject6 = LeonardoBleCmdService.this.currentCommand;
                        if (commandObject6 != null && !commandObject6.isCompleted() && LeonardoBleCmdService.this.currentCommand.getCmdName() == CommandNames.SET_DEVICE_TIME) {
                            LeonardoBleCmdService.this.closeGattConnection();
                            LeonardoBleCmdService.this.scanLeDevice();
                        }
                    } else {
                        CommandObject commandObject7 = LeonardoBleCmdService.this.currentCommand;
                        if (commandObject7 != null && !commandObject7.isCompleted() && (LeonardoBleCmdService.this.currentCommand.getCmdName() == CommandNames.SET_DEVICE_TIME || LeonardoBleCmdService.this.currentCommand.getCmdName() == CommandNames.SET_PAIRING_PHONE_TYPE)) {
                            LeonardoBleCmdService.this.closeGattConnection();
                            LeonardoBleCmdService.this.scanLeDevice();
                        }
                    }
                    LeonardoBleCmdService.this.clearQueue();
                    LeonardoBleCmdService leonardoBleCmdService = LeonardoBleCmdService.this;
                    leonardoBleCmdService.currentCommand = null;
                    leonardoBleCmdService.W = false;
                    return;
                }
            }
            CommandObject commandObject8 = LeonardoBleCmdService.this.currentCommand;
            if (commandObject8 != null && !commandObject8.isCompleted() && LeonardoBleCmdService.this.currentCommand.getCmdName() == CommandNames.GET_SEDENTARY_DATA) {
                ReadSedentaryDataRes readSedentaryDataRes = new ReadSedentaryDataRes(CommandType.READ_SEDENTARY_HISTORY, LeonardoBleCmdService.this.currentCommand.getBaseRequest());
                readSedentaryDataRes.setResponseData(null);
                LeonardoBleCmdService.this.currentCommand.setCompleted(true);
                LeonardoBleCmdService.this.currentCommand.getResponseListener().onResponse(readSedentaryDataRes);
            }
            CommandObject commandObject9 = LeonardoBleCmdService.this.currentCommand;
            if (commandObject9 != null && !commandObject9.isCompleted() && LeonardoBleCmdService.this.currentCommand.getCmdName() == CommandNames.GET_RAW_PPG_DATA_HISTORY) {
                ReadRawPPGDataRes readRawPPGDataRes = new ReadRawPPGDataRes(CommandType.READ_RAW_PPG_HISTORY, LeonardoBleCmdService.this.currentCommand.getBaseRequest());
                readRawPPGDataRes.setResponseData(null);
                LeonardoBleCmdService.this.currentCommand.setCompleted(true);
                LeonardoBleCmdService.this.currentCommand.getResponseListener().onResponse(readRawPPGDataRes);
            }
            LeonardoBleCmdService.this.W = true;
            LeonardoBleCmdService leonardoBleCmdService2 = LeonardoBleCmdService.this;
            LinkedList<CommandObject> linkedList = leonardoBleCmdService2.l;
            if (linkedList != null && linkedList.size() > 0) {
                CommandObject remove = leonardoBleCmdService2.l.remove();
                leonardoBleCmdService2.currentCommand = remove;
                if (remove == null) {
                    LogHelper.d(str, "current command is null process next item ++", moduleNames.getModuleName());
                    leonardoBleCmdService2.o.removeCallbacksAndMessages(null);
                    leonardoBleCmdService2.W = false;
                    leonardoBleCmdService2.o.post(leonardoBleCmdService2.X);
                } else if (remove.getCh() != null) {
                    BluetoothGattCharacteristic ch = leonardoBleCmdService2.currentCommand.getCh();
                    if (leonardoBleCmdService2.mBluetoothGatt != null && ch != null) {
                        LogHelper.d(str, "read from band service+++ " + ch.getUuid(), moduleNames.getModuleName());
                        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(leonardoBleCmdService2, "android.permission.BLUETOOTH_CONNECT") == 0) {
                            leonardoBleCmdService2.mBluetoothGatt.readCharacteristic(ch);
                        }
                    }
                } else {
                    CommandObject commandObject10 = leonardoBleCmdService2.currentCommand;
                    if (commandObject10 != null && commandObject10.getCmdByte() != null) {
                        if (leonardoBleCmdService2.currentCommand.getCmdName() == CommandNames.ENABLE_RAW_PPG) {
                            if (leonardoBleCmdService2.currentCommand.getCmdByte() != BleUUID.ENABLE_RAW_PPG && leonardoBleCmdService2.currentCommand.getCmdByte() != BleUUID.ENABLE_RAW_PPG_v7) {
                                if (leonardoBleCmdService2.currentCommand.getCmdByte() == BleUUID.DISABLE_RAW_PPG || leonardoBleCmdService2.currentCommand.getCmdByte() == BleUUID.DISABLE_RAW_PPG_V7) {
                                    leonardoBleCmdService2.isPPGStarted = false;
                                }
                            } else {
                                leonardoBleCmdService2.isPPGStarted = true;
                            }
                            leonardoBleCmdService2.currentCommand.setCompleted(true);
                            if (!leonardoBleCmdService2.a(leonardoBleCmdService2.currentCommand) && (commandObject2 = leonardoBleCmdService2.currentCommand) != null && commandObject2.getResponseListener() != null) {
                                leonardoBleCmdService2.currentCommand.getResponseListener().onFailure(new Error(Type.COMMAND_WRITE_FAILED, "Command write failed"));
                                leonardoBleCmdService2.clearQueue();
                            }
                        } else if (leonardoBleCmdService2.isPPGStarted) {
                            CommandObject commandObject11 = leonardoBleCmdService2.currentCommand;
                            if (commandObject11 != null && commandObject11.getResponseListener() != null) {
                                leonardoBleCmdService2.currentCommand.getResponseListener().onFailure(new Error(Type.COMMAND_REQUEST_ERROR, "PPG operation in progress"));
                            } else {
                                BleEventBusManager.getInstance().getEventBus().post(new Error(Type.COMMAND_REQUEST_ERROR, "PPG operation in progress"));
                            }
                        } else if (!leonardoBleCmdService2.a(leonardoBleCmdService2.currentCommand) && (commandObject = leonardoBleCmdService2.currentCommand) != null && commandObject.getResponseListener() != null) {
                            leonardoBleCmdService2.currentCommand.getResponseListener().onFailure(new Error(Type.COMMAND_WRITE_FAILED, "Command write failed"));
                            leonardoBleCmdService2.clearQueue();
                        }
                    } else {
                        CommandObject commandObject12 = leonardoBleCmdService2.currentCommand;
                        if (commandObject12 != null && commandObject12.getResponseListener() != null) {
                            leonardoBleCmdService2.currentCommand.getResponseListener().onFailure(new Error(Type.COMMAND_TIMED_OUT, "Something went wrong"));
                            LogHelper.d(str, "Time out for ", Arrays.toString(leonardoBleCmdService2.currentCommand.getCmdByte()));
                        } else {
                            Bus eventBus2 = BleEventBusManager.getInstance().getEventBus();
                            Type type2 = Type.COMMAND_REQUEST_ERROR;
                            eventBus2.post(new Error(type2, "No Response from band for " + Arrays.toString(leonardoBleCmdService2.currentCommand.getCmdByte())));
                        }
                    }
                }
            } else {
                CommandObject commandObject13 = leonardoBleCmdService2.currentCommand;
                if (commandObject13 != null && commandObject13.getCmdName() == CommandNames.GET_DIAGNOSTIC_FEATURE_TEST && !leonardoBleCmdService2.currentCommand.isCompleted()) {
                    LogHelper.d(str, "Do not empty the queue for diagnostics test");
                } else {
                    LogHelper.d(str, "Queue is empty");
                    leonardoBleCmdService2.currentCommand = null;
                }
            }
            LeonardoBleCmdService leonardoBleCmdService3 = LeonardoBleCmdService.this;
            if (leonardoBleCmdService3.currentCommand == null) {
                leonardoBleCmdService3.W = false;
                LeonardoBleCmdService.this.o.removeCallbacksAndMessages(null);
                return;
            }
            leonardoBleCmdService3.o.removeCallbacksAndMessages(null);
            if (LeonardoBleCmdService.this.l.size() != 0) {
                LeonardoBleCmdService.this.V = false;
            } else {
                LeonardoBleCmdService leonardoBleCmdService4 = LeonardoBleCmdService.this;
                if (!leonardoBleCmdService4.V) {
                    leonardoBleCmdService4.V = true;
                }
            }
            LeonardoBleCmdService.this.o.removeCallbacksAndMessages(null);
            if (LeonardoBleCmdService.this.currentCommand.isMultipacket()) {
                LogHelper.d(str, "cmdHandler called multipacket", moduleNames.getModuleName());
                if (LeonardoBleCmdService.this.currentCommand.getCmdName() != CommandNames.GET_NEARBY_DEVICE_LIST && LeonardoBleCmdService.this.currentCommand.getCmdName() != CommandNames.GET_NEARBY_DEVICE_LIST_MAC && LeonardoBleCmdService.this.currentCommand.getCmdName() != CommandNames.GET_RAW_PPG_DATA_HISTORY) {
                    LeonardoBleCmdService leonardoBleCmdService5 = LeonardoBleCmdService.this;
                    leonardoBleCmdService5.o.postDelayed(leonardoBleCmdService5.X, 30000L);
                    return;
                }
                long timeout = LeonardoBleCmdService.this.currentCommand.getTimeout();
                LeonardoBleCmdService leonardoBleCmdService6 = LeonardoBleCmdService.this;
                leonardoBleCmdService6.o.postDelayed(leonardoBleCmdService6.X, timeout);
            } else if (LeonardoBleCmdService.this.currentCommand.getCmdName() == CommandNames.DEVICE_INFO) {
                LogHelper.d(str, "cmdHandler called device info", moduleNames.getModuleName());
                LeonardoBleCmdService leonardoBleCmdService7 = LeonardoBleCmdService.this;
                leonardoBleCmdService7.o.postDelayed(leonardoBleCmdService7.X, 10000L);
            } else if (LeonardoBleCmdService.this.currentCommand.getCmdName() != CommandNames.HEART_RATE_MEASUREMENT_CONTROL && LeonardoBleCmdService.this.currentCommand.getCmdName() != CommandNames.BLOODPRESSURE_MEASUREMENT_CONTROL && LeonardoBleCmdService.this.currentCommand.getCmdName() != CommandNames.SET_DAILY_WALK_TARGET && LeonardoBleCmdService.this.currentCommand.getCmdName() != CommandNames.SET_USER_FITNESS_INFO && LeonardoBleCmdService.this.currentCommand.getCmdName() != CommandNames.LIVE_STEPS_CONTROL) {
                LogHelper.d(str, "cmdHandler called normal command", moduleNames.getModuleName());
                LeonardoBleCmdService leonardoBleCmdService8 = LeonardoBleCmdService.this;
                leonardoBleCmdService8.o.postDelayed(leonardoBleCmdService8.X, 10000L);
            } else {
                LogHelper.d(str, "cmdHandler called HEARTRATE control", moduleNames.getModuleName());
                LeonardoBleCmdService leonardoBleCmdService9 = LeonardoBleCmdService.this;
                leonardoBleCmdService9.o.postDelayed(leonardoBleCmdService9.X, 10000L);
            }
        }
    };
    public final BroadcastReceiver Y = new BroadcastReceiver() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleCmdService.2
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            char c;
            String action = intent.getAction();
            if (action == null) {
                LogHelper.e(LeonardoBleCmdService.Z, "Null action from sifli service");
                return;
            }
            switch (action.hashCode()) {
                case -1914126008:
                    if (action.equals(SifliWatchfaceService.BROADCAST_WATCHFACE_LOG)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -608926174:
                    if (action.equals(SifliWatchfaceService.BROADCAST_WATCHFACE_STATE)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 87150217:
                    if (action.equals(SifliWatchfaceService.BROADCAST_WATCHFACE_PROGRESS)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    intent.getStringExtra(SifliWatchfaceService.EXTRA_WATCHFACE_LOG);
                    return;
                case 1:
                    if (LeonardoBleCmdService.this.currentCommand != null) {
                        int intExtra = intent.getIntExtra(SifliWatchfaceService.EXTRA_WATCHFACE_STATE, -1);
                        intent.getIntExtra(SifliWatchfaceService.EXTRA_WATCHFACE_STATE_RSP, -1);
                        if (intExtra == 0) {
                            LeonardoBleCmdService.this.currentCommand.getResponseListener().onResponse(new CustomWatchFaceUploadRes(CommandType.SET_CUSTOM_WATCHFACE, LeonardoBleCmdService.this.currentCommand.getBaseRequest(), true));
                        } else {
                            LeonardoBleCmdService.this.currentCommand.getResponseListener().onFailure(new Error(Type.COMMAND_WRITE_FAILED, "Custom watchface upload failed."));
                        }
                        LeonardoBleCmdService.this.currentCommand.setCompleted(true);
                        LinkedList<CommandObject> linkedList = LeonardoBleCmdService.this.k;
                        if (linkedList != null && linkedList.size() > 0) {
                            LeonardoBleCmdService.this.k.clear();
                        }
                        try {
                            LocalBroadcastManager.getInstance(context).unregisterReceiver(LeonardoBleCmdService.this.Y);
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    return;
                case 2:
                    if (LeonardoBleCmdService.this.currentCommand != null) {
                        LeonardoBleCmdService.this.currentCommand.getResponseListener().onProgressUpdate(new ProgressDataBean(LeonardoBleCmdService.this.currentCommand.getBaseRequest(), intent.getIntExtra(SifliWatchfaceService.EXTRA_WATCHFACE_PROGRESS, 0), ProgressType.PERCENTAGE));
                        return;
                    }
                    return;
                default:
                    LogHelper.e(LeonardoBleCmdService.Z, "Unkown action \"%s\" from sifli service", action);
                    return;
            }
        }
    };

    /* loaded from: classes2.dex */
    public class BtServiceBinder extends Binder {
        public BtServiceBinder() {
        }

        public LeonardoBleCmdService getService() {
            return LeonardoBleCmdService.this;
        }
    }

    /* loaded from: classes2.dex */
    public class RequestLogRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public CommandObject f3827a;

        public RequestLogRunnable(CommandObject commandObject) {
            this.f3827a = commandObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f3827a.getBaseRequest() != null) {
                AnalyticsLog analyticsLog = new AnalyticsLog();
                analyticsLog.setEventName(FirebaseEventParams.EventName.CV_COMMAND_REQUEST_TYPE.value);
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(FirebaseEventParams.MetaDataKeyNames.CV_APP_PROCESS_STATUS.value, "background");
                String str = FirebaseEventParams.MetaDataKeyNames.CV_REQUEST_CMD_NAME.value;
                hashMap.put(str, "" + this.f3827a.getBaseRequest().getCommandName());
                hashMap.put(FirebaseEventParams.MetaDataKeyNames.CV_PHONE_BATTERY_LEVEL.value, AppUtils.getPhoneBatteryLevel(LeonardoBleCmdService.this));
                analyticsLog.setMapData(hashMap);
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            }
        }
    }

    public final void c(BaseRequest baseRequest) {
        CommandObject commandObject;
        boolean z;
        if (baseRequest.getGattCharacteristicToRead() == null) {
            CommandObject commandObject2 = this.currentCommand;
            if (commandObject2 != null && (commandObject2.getCmdName() == CommandNames.GET_NEARBY_DEVICE_LIST || this.currentCommand.getCmdName() == CommandNames.GET_NEARBY_DEVICE_LIST_MAC || this.isPPGStarted)) {
                Error error = new Error(Type.SERVICE_BUSY, "Service is busy");
                error.setShouldClearCommandQueue(false);
                error.setReqId(baseRequest.getReqId());
                if (this.isPPGStarted) {
                    LogHelper.d(Z, "Service is busy in executing Raw PPG command", ModuleNames.BLEABSTRACT.getModuleName());
                } else {
                    LogHelper.d(Z, "Service is busy in executing Contact tracing command", ModuleNames.BLEABSTRACT.getModuleName());
                }
                baseRequest.getResponseListener().onFailure(error);
                return;
            } else if (this.k.size() > 0) {
                Error error2 = new Error(Type.SERVICE_BUSY, "Service is busy");
                error2.setShouldClearCommandQueue(false);
                error2.setReqId(baseRequest.getReqId());
                baseRequest.getResponseListener().onFailure(error2);
                return;
            } else if (this.W && !baseRequest.shouldWaitForRes() && !baseRequest.shouldSendAllPacketsAtOnce()) {
                Error error3 = new Error(Type.SERVICE_BUSY, "Service is busy");
                error3.setShouldClearCommandQueue(false);
                error3.setReqId(baseRequest.getReqId());
                baseRequest.getResponseListener().onFailure(error3);
                return;
            } else {
                List<CommandBytes> commandBytes = baseRequest.getCommandBytes();
                int i = 0;
                while (true) {
                    if (i >= commandBytes.size()) {
                        break;
                    }
                    CommandObject commandObject3 = new CommandObject(this.mBluetoothGatt, commandBytes.get(i).getCommandData(), baseRequest.isMultiPacket(), baseRequest.getCommandName());
                    commandObject3.noOfPacketsAtaTime = baseRequest.noOfPacketsAtaTime;
                    RequestPayload requestPayload = commandBytes.get(i).getRequestPayload();
                    if (requestPayload != null) {
                        commandObject3.setRequestPayload(requestPayload);
                    }
                    commandObject3.setBaseRequest(baseRequest);
                    commandObject3.shouldWaitForRes = baseRequest.shouldWaitForRes();
                    commandObject3.isPriority = baseRequest.isPriority();
                    commandObject3.setResponseListener(baseRequest.getResponseListener());
                    LogHelper.d(Z, "isPriority " + baseRequest.isPriority() + " Request " + baseRequest.getCommandName(), ModuleNames.BLEABSTRACT.getModuleName());
                    if (baseRequest.shouldSendAllPacketsAtOnce()) {
                        CommandObject commandObject4 = this.currentCommand;
                        if (commandObject4 != null && !commandObject4.isCompleted() && !this.currentCommand.getBaseRequest().shouldSendAllPacketsAtOnce()) {
                            CommandObject commandObject5 = this.currentCommand;
                            if (commandObject5.shouldWaitForRes) {
                                this.l.addLast(commandObject5);
                            }
                        }
                        this.currentCommand = commandObject3;
                        boolean a2 = a(commandObject3);
                        this.W = true;
                        this.o.removeCallbacksAndMessages(null);
                        this.o.postDelayed(this.X, 5000L);
                        if (!a2) {
                            CommandObject commandObject6 = this.currentCommand;
                            if (commandObject6 != null && commandObject6.getResponseListener() != null) {
                                this.currentCommand.getResponseListener().onFailure(new Error(Type.COMMAND_WRITE_FAILED, "Command write failed"));
                                clearQueue();
                            }
                        } else {
                            try {
                                Thread.sleep(40L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (baseRequest.isPriority()) {
                        if (!AppUtils.isEmpty(this.l)) {
                            int size = this.l.size() - 1;
                            while (true) {
                                if (size < 0) {
                                    z = false;
                                    break;
                                } else if (this.l.get(size).isPriority) {
                                    this.l.add(size + 1, commandObject3);
                                    z = true;
                                    break;
                                } else {
                                    size--;
                                }
                            }
                            if (!z) {
                                this.l.addFirst(commandObject3);
                            }
                        } else {
                            this.l.addFirst(commandObject3);
                        }
                        CommandObject commandObject7 = this.currentCommand;
                        if (commandObject7 != null) {
                            if (commandObject7.getCmdName() != CommandNames.GET_NEARBY_DEVICE_LIST && this.currentCommand.getCmdName() == CommandNames.GET_NEARBY_DEVICE_LIST_MAC) {
                                stopMultiPcktCmdOnImmediateRequest();
                            } else {
                                this.o.removeCallbacks(this.X);
                                this.o.postDelayed(this.X, 500L);
                            }
                        } else {
                            stopMultiPcktCmdOnImmediateRequest();
                        }
                    } else {
                        if (baseRequest instanceof GetNearbyDeviceListReq) {
                            commandObject3.setTimeout(((GetNearbyDeviceListReq) baseRequest).getTimeOut());
                        }
                        if (baseRequest instanceof ReadRawPPGDataReq) {
                            commandObject3.setTimeout(((ReadRawPPGDataReq) baseRequest).getTimeOut());
                        }
                        if (baseRequest.shouldWaitForRes()) {
                            this.l.add(commandObject3);
                            if (!this.W || !baseRequest.shouldWaitForRes()) {
                                this.W = true;
                                this.o.postDelayed(this.X, 500L);
                            }
                        } else {
                            this.k.add(commandObject3);
                        }
                    }
                    i++;
                }
                if (this.k.size() > 0) {
                    CommandObject commandObject8 = this.currentCommand;
                    if (commandObject8 != null && !commandObject8.isCompleted()) {
                        if (this.currentCommand.shouldWaitForRes) {
                            LogHelper.d(Z, "Added existing command to last");
                            this.l.addLast(this.currentCommand);
                        } else {
                            LogHelper.d(Z, "Skipping existing command to last");
                        }
                    }
                    this.o.removeCallbacksAndMessages(null);
                    CommandObject remove = this.k.remove();
                    this.currentCommand = remove;
                    this.shouldWaitFortheStreamResponse = true;
                    if (!a(remove) && (commandObject = this.currentCommand) != null && commandObject.getResponseListener() != null) {
                        this.currentCommand.getResponseListener().onFailure(new Error(Type.COMMAND_WRITE_FAILED, "Command write failed"));
                        clearQueue();
                    }
                    this.p.postDelayed(this.streamQueueTimeoutRunnable, 20000L);
                    return;
                }
                return;
            }
        }
        CommandObject commandObject9 = this.currentCommand;
        if (commandObject9 != null && (commandObject9.getCmdName() == CommandNames.GET_NEARBY_DEVICE_LIST || this.currentCommand.getCmdName() == CommandNames.GET_NEARBY_DEVICE_LIST_MAC)) {
            Error error4 = new Error(Type.SERVICE_BUSY, "Service is busy");
            error4.setShouldClearCommandQueue(false);
            error4.setReqId(baseRequest.getReqId());
            LogHelper.d(Z, "Service is busy in executing Contact tracing command", ModuleNames.BLEABSTRACT.getModuleName());
            baseRequest.getResponseListener().onFailure(error4);
            return;
        }
        if (baseRequest.getGattServiceToRead().equalsIgnoreCase(BleUUID.DEVICE_INFO_SERVICE_UUID)) {
            CommandObject commandObject10 = new CommandObject(this.mBluetoothGatt, a(baseRequest), false, CommandNames.DEVICE_INFO);
            commandObject10.setBaseRequest(baseRequest);
            commandObject10.setResponseListener(baseRequest.getResponseListener());
            this.l.add(commandObject10);
        } else if (baseRequest.getGattServiceToRead().equalsIgnoreCase(BleUUID.BATTERY_SERVICE_UUID)) {
            CommandObject commandObject11 = new CommandObject(this.mBluetoothGatt, a(baseRequest), false, CommandNames.GET_BATTERY_LEVEL);
            commandObject11.setBaseRequest(baseRequest);
            commandObject11.setResponseListener(baseRequest.getResponseListener());
            this.l.add(commandObject11);
        }
        if (this.W && baseRequest.shouldWaitForRes()) {
            return;
        }
        this.W = true;
        this.o.postDelayed(this.X, 500L);
    }

    public void clearQueue() {
        LinkedList<CommandObject> linkedList = this.l;
        if (linkedList != null && linkedList.size() > 0) {
            this.l.clear();
        }
        LinkedList<CommandObject> linkedList2 = this.k;
        if (linkedList2 == null || linkedList2.size() <= 0) {
            return;
        }
        this.k.clear();
    }

    public void disconnectAndForget() {
        Handler handler = this.o;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        clearQueue();
        stopScan();
        closeGattConnection();
        clearAllServiceParameters();
        unregisterReceivers();
        BlePreferenceManager.savePreference(getApplicationContext(), CommonPreference.BLE_DEVICE_ADDRESS, "");
        BlePreferenceManager.savePreference(getApplicationContext(), CommonPreference.BLE_CONNECTION_TYPE, ConnectionType.DONT_CONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
    }

    public void disconnectService() {
        Handler handler = this.o;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        clearQueue();
        stopScan();
        closeGattConnection();
        clearAllServiceParameters();
        unregisterReceivers();
        stopForeground(true);
        stopSelf();
    }

    @Override // com.coveiot.android.bleabstract.services.LeonardoBleService, android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return this.U;
    }

    @Override // com.coveiot.android.bleabstract.services.LeonardoBleService, android.app.Service
    public void onDestroy() {
        LogHelper.d(Z, "on destroy service", ModuleNames.BLEABSTRACT.getModuleName());
        super.onDestroy();
        clearQueue();
        stopScan();
        closeGattConnection();
        clearAllServiceParameters();
        unregisterReceivers();
    }

    @Subscribe
    public void onResponseObtained(ProcessNextItemEvent processNextItemEvent) {
        LogHelper.d(Z, "process next item after response is called ", ModuleNames.BLEABSTRACT.getModuleName());
        if (this.l.size() == 0) {
            Handler handler = this.o;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            this.W = false;
        }
        if (processNextItemEvent.isHasStreamError()) {
            this.k.clear();
        }
        if (this.k.size() > 0) {
            Handler handler2 = this.p;
            if (handler2 != null) {
                handler2.removeCallbacksAndMessages(null);
            }
            CommandObject commandObject = this.currentCommand;
            if (commandObject == null || commandObject.getBaseRequest() == null || this.currentCommand.getBaseRequest().getCommandName() == null || this.k.get(0).getBaseRequest() == null || this.k.get(0).getBaseRequest().getCommandName() == null || this.currentCommand.getBaseRequest().getCommandName() != this.k.get(0).getBaseRequest().getCommandName()) {
                return;
            }
            CommandObject remove = this.k.remove();
            this.currentCommand = remove;
            this.shouldWaitFortheStreamResponse = false;
            this.streamPacketCount = 0;
            if (a(remove)) {
                this.streamPacketCount++;
                return;
            }
            LinkedList<CommandObject> linkedList = this.k;
            if (linkedList != null) {
                linkedList.clear();
            }
            CommandObject commandObject2 = this.currentCommand;
            if (commandObject2 == null || commandObject2.getResponseListener() == null) {
                return;
            }
            this.currentCommand.getResponseListener().onFailure(new Error(Type.COMMAND_WRITE_FAILED, "Command write failted"));
            return;
        }
        if (this.o == null) {
            this.o = new Handler();
        }
        this.o.postDelayed(this.X, 500L);
    }

    @Subscribe
    public void onStartRunnableCommand(StartRunnableEvent startRunnableEvent) {
        LogHelper.d(Z, "on start runnbale for the first time", ModuleNames.BLEABSTRACT.getModuleName());
        this.W = true;
        this.o.post(this.X);
    }

    public void restartService() {
        Handler handler = this.o;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        stopScan();
        clearQueue();
        closeGattConnection();
        clearAllServiceParameters();
        unregisterReceivers();
        BlePreferenceManager.savePreference(getApplicationContext(), CommonPreference.BLE_CONNECTION_TYPE, ConnectionType.RECONNECT_ON_DISCONNECT.name());
        stopForeground(true);
        stopSelf();
    }

    public void sendFindMyPhoneACK() {
        if (this.mBluetoothGatt != null) {
            a(BleUUID.FIND_MY_PHONE_ACK);
        }
    }

    public void sendRequest(BaseRequest baseRequest, ResponseListener responseListener) {
        baseRequest.setResponseListener(responseListener);
        if (b(baseRequest)) {
            c(baseRequest);
        } else if (this.k.size() > 0) {
            Error error = new Error(Type.SERVICE_BUSY, "Service is busy");
            error.setShouldClearCommandQueue(false);
            error.setReqId(baseRequest.getReqId());
            baseRequest.getResponseListener().onFailure(error);
        } else {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(SifliWatchfaceService.BROADCAST_WATCHFACE_STATE);
            intentFilter.addAction(SifliWatchfaceService.BROADCAST_WATCHFACE_PROGRESS);
            intentFilter.addAction(SifliWatchfaceService.BROADCAST_WATCHFACE_LOG);
            CommandObject commandObject = new CommandObject(this.mBluetoothGatt, new byte[0], baseRequest.isMultiPacket(), baseRequest.getCommandName());
            commandObject.noOfPacketsAtaTime = baseRequest.noOfPacketsAtaTime;
            commandObject.setBaseRequest(baseRequest);
            commandObject.shouldWaitForRes = baseRequest.shouldWaitForRes();
            commandObject.isPriority = baseRequest.isPriority();
            commandObject.setResponseListener(baseRequest.getResponseListener());
            this.currentCommand = commandObject;
            this.k.add(commandObject);
            LocalBroadcastManager.getInstance(this).registerReceiver(this.Y, intentFilter);
            SifliWatchfaceService.startActionWatchface(this, ((CustomWatchFaceUploadReq) baseRequest).getFilePath(), (String) BlePreferenceManager.getPreference(this, CommonPreference.BLE_DEVICE_ADDRESS, ""), 0);
            this.p.postDelayed(this.streamQueueTimeoutRunnable, 20000L);
        }
    }

    public void stopMultiPcktCmdOnImmediateRequest() {
        Runnable runnable;
        String str = Z;
        LogHelper.d(str, "stopMultiPcktCmdOnImmediateRequest isLastCmdCompleted" + this.V, ModuleNames.BLEABSTRACT.getModuleName());
        CommandObject commandObject = this.currentCommand;
        if (commandObject != null && !commandObject.isCompleted()) {
            this.l.addLast(this.currentCommand);
        }
        this.currentCommand = null;
        Handler handler = this.o;
        if (handler == null || (runnable = this.X) == null) {
            return;
        }
        handler.removeCallbacks(runnable);
        this.o.postDelayed(this.X, 500L);
    }

    public void stopService() {
        Handler handler = this.o;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.E;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        disconnectService();
    }

    public final boolean a(CommandObject commandObject) {
        boolean z = false;
        if (this.mBluetoothGatt != null && this.writeCharecterstic != null && commandObject != null) {
            byte[] cmdByte = commandObject.getCmdByte();
            this.writeCharecterstic.setValue(cmdByte);
            if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                return false;
            }
            z = this.mBluetoothGatt.writeCharacteristic(this.writeCharecterstic);
            int i = 8;
            while (true) {
                i--;
                if (i <= 0 || z) {
                    break;
                }
                try {
                    Thread.sleep(40L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
                if (bluetoothGatt == null) {
                    break;
                }
                z = bluetoothGatt.writeCharacteristic(this.writeCharecterstic);
                if (z) {
                    LogHelper.d(Z + " Inside loop Reqst sent is ++ ", BleApiUtils.byte2Hex(cmdByte), ModuleNames.BLEABSTRACT.getModuleName());
                } else {
                    LogHelper.e(Z + " Inside loop Reqst sent failed is ++ ", BleApiUtils.byte2Hex(cmdByte), ModuleNames.BLEABSTRACT.getModuleName());
                }
            }
            if (z) {
                if (commandObject.getBaseRequest() != null) {
                    this.logHandler.post(new RequestLogRunnable(commandObject));
                }
                LogHelper.d(Z + " After loop Reqst sent is ++ ", BleApiUtils.byte2Hex(cmdByte), ModuleNames.BLEABSTRACT.getModuleName());
            } else {
                LogHelper.e(Z + " After loop Reqst sent failed is ++ ", BleApiUtils.byte2Hex(cmdByte), ModuleNames.BLEABSTRACT.getModuleName());
            }
        }
        return z;
    }

    public final boolean b(BaseRequest baseRequest) {
        if (baseRequest instanceof CustomWatchFaceUploadReq) {
            CustomWatchFaceUploadReq customWatchFaceUploadReq = (CustomWatchFaceUploadReq) baseRequest;
            if (customWatchFaceUploadReq.getCommandName() != null && customWatchFaceUploadReq.getCommandName() == CommandNames.SET_CUSTOM_WATCHFACE) {
                return false;
            }
        }
        return true;
    }

    private boolean a(byte[] bArr) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic;
        if (this.mBluetoothGatt == null || (bluetoothGattCharacteristic = this.writeCharecterstic) == null) {
            return false;
        }
        bluetoothGattCharacteristic.setValue(bArr);
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            this.mBluetoothGatt.requestConnectionPriority(1);
            boolean writeCharacteristic = this.mBluetoothGatt.writeCharacteristic(this.writeCharecterstic);
            int i = 8;
            while (true) {
                i--;
                if (i <= 0 || writeCharacteristic) {
                    break;
                }
                try {
                    Thread.sleep(40L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
                if (bluetoothGatt == null) {
                    break;
                }
                writeCharacteristic = bluetoothGatt.writeCharacteristic(this.writeCharecterstic);
                if (writeCharacteristic) {
                    LogHelper.d(Z + " Inside loop Reqst sent is ++ ", BleApiUtils.byte2Hex(bArr), ModuleNames.BLEABSTRACT.getModuleName());
                } else {
                    LogHelper.e(Z + " Inside loop Reqst sent failed is ++ ", BleApiUtils.byte2Hex(bArr), ModuleNames.BLEABSTRACT.getModuleName());
                }
            }
            if (writeCharacteristic) {
                LogHelper.d(Z + " After loop Reqst sent is ++ ", BleApiUtils.byte2Hex(bArr), ModuleNames.BLEABSTRACT.getModuleName());
            } else {
                LogHelper.e(Z + " After loop Reqst sent failed is ++ ", BleApiUtils.byte2Hex(bArr), ModuleNames.BLEABSTRACT.getModuleName());
            }
            return writeCharacteristic;
        }
        return false;
    }

    public final BluetoothGattCharacteristic a(BaseRequest baseRequest) {
        BluetoothGattService service;
        if (this.mBluetoothGatt == null || baseRequest.getGattServiceToRead() == null || (service = this.mBluetoothGatt.getService(UUID.fromString(baseRequest.getGattServiceToRead()))) == null) {
            return null;
        }
        return service.getCharacteristic(UUID.fromString(baseRequest.getGattCharacteristicToRead()));
    }
}
