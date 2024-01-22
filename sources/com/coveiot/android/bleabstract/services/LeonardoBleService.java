package com.coveiot.android.bleabstract.services;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.RingtoneManager;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.telephony.PhoneStateListener;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.blankj.utilcode.constant.TimeConstants;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.RawPPGData;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.bleabstract.response.ConnectionError;
import com.coveiot.android.bleabstract.services.FirebaseEventParams;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.api.ResponseListener;
import com.coveiot.sdk.ble.api.error.Error;
import com.coveiot.sdk.ble.api.error.Type;
import com.coveiot.sdk.ble.api.model.ProgressDataBean;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.api.response.BaseResponse;
import com.coveiot.sdk.ble.api.response.GetTimeRes;
import com.coveiot.sdk.ble.api.response.SendQuickReplyRes;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.events.ConnectionType;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.events.StartRunnableEvent;
import com.coveiot.sdk.ble.model.BleDeviceInfo;
import com.coveiot.sdk.ble.model.CommandObject;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.parser.ProtocolParser;
import com.coveiot.sdk.ble.scan.DeviceScanManager;
import com.coveiot.sdk.ble.scan.ScanResult;
import com.coveiot.sdk.ble.scan.model.BleDevice;
import com.coveiot.sdk.ble.utils.BlePreferenceManager;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.sdk.ble.utils.CommonPreference;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
@SuppressLint({"NewApi"})
/* loaded from: classes2.dex */
public class LeonardoBleService extends Service implements ResponseListener {
    public static final String S = LeonardoBleService.class.getSimpleName();
    public static IntentFilter T;
    public ConnectionType A;
    public HandlerThread B;
    public HandlerThread C;
    public HandlerThread D;
    public Handler E;
    public long F;
    public HandlerThread G;
    public final BroadcastReceiver H;
    public final Runnable I;
    public Handler J;
    public Handler K;
    public Handler L;
    public ConnectionError M;
    public long N;
    public BluetoothGattCallback O;
    public final Runnable P;
    public long Q;
    public Runnable R;

    /* renamed from: a  reason: collision with root package name */
    public BluetoothAdapter f3828a;
    public BluetoothGattService b;
    public CloveBleState.BleState bluetoothConnectionStatus;
    public String c;
    public CommandObject currentCommand;
    public CommandObject currentDeviceInfoCommand;
    public Handler d;
    public ArrayList<String> dataBytes;
    public Handler e;
    public Handler f;
    public boolean isConnectionInitiated;
    public boolean isExecuted;
    public boolean isPPGStarted;
    public Handler j;
    public Handler logHandler;
    public BluetoothDevice mBluetoothDevice;
    public BluetoothGatt mBluetoothGatt;
    public BluetoothGatt mPreviousBluetoothGatt;
    public Handler n;
    public Handler o;
    public Handler p;
    public int packetSize;
    public Handler q;
    public final Handler r;
    public BluetoothGattCharacteristic readCharacteristic;
    public final Handler s;
    public boolean shouldWaitFortheStreamResponse;
    public int streamPacketCount;
    public Runnable streamQueueTimeoutRunnable;
    public final TimeChangeReceiver t;
    public Handler u;
    public int v;
    public int w;
    public BluetoothGattCharacteristic writeCharecterstic;
    public Runnable x;
    public Handler y;
    public final IBinder z;
    public long g = -1;
    public boolean h = false;
    public boolean i = true;
    public LinkedList<CommandObject> k = new LinkedList<>();
    public LinkedList<CommandObject> l = new LinkedList<>();
    public LinkedList<CommandObject> m = new LinkedList<>();

    /* renamed from: com.coveiot.android.bleabstract.services.LeonardoBleService$5  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass5 extends BluetoothGattCallback {
        public AnonymousClass5() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            byte[] value = bluetoothGattCharacteristic.getValue();
            LeonardoBleService leonardoBleService = LeonardoBleService.this;
            if (leonardoBleService.isPPGStarted && value.length >= 20) {
                if (bluetoothGattCharacteristic.getValue()[0] == -127 && bluetoothGattCharacteristic.getValue()[1] == 9) {
                    LeonardoBleService leonardoBleService2 = LeonardoBleService.this;
                    leonardoBleService2.f.post(new PPGRunnable(Arrays.copyOfRange((byte[]) bluetoothGattCharacteristic.getValue().clone(), 4, ((byte[]) bluetoothGattCharacteristic.getValue().clone()).length), (char[]) bluetoothGattCharacteristic.getUuid().toString().toCharArray().clone()));
                    return;
                }
                LeonardoBleService leonardoBleService3 = LeonardoBleService.this;
                leonardoBleService3.f.post(new PPGRunnable((byte[]) bluetoothGattCharacteristic.getValue().clone(), (char[]) bluetoothGattCharacteristic.getUuid().toString().toCharArray().clone()));
                return;
            }
            byte b = value[0];
            if (!leonardoBleService.B.isAlive()) {
                LogHelper.d("Gatt Hadler Thread", "dead", ModuleNames.BLEABSTRACT.getModuleName());
                LeonardoBleService.this.B.start();
            }
            LeonardoBleService leonardoBleService4 = LeonardoBleService.this;
            leonardoBleService4.f.post(new CharacteristicChangedRunnable((byte[]) bluetoothGattCharacteristic.getValue().clone(), (char[]) bluetoothGattCharacteristic.getUuid().toString().toCharArray().clone()));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            LeonardoBleService.this.f.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.5.3
                @Override // java.lang.Runnable
                public void run() {
                    ProtocolParser.getInstance().onReadFromService(LeonardoBleService.this.getApplicationContext(), bluetoothGattCharacteristic, LeonardoBleService.this.currentCommand);
                }
            });
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (!LeonardoBleService.this.C.isAlive()) {
                LeonardoBleService.this.C.start();
            }
            LeonardoBleService.this.E.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.5.4
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (LeonardoBleService.this.k) {
                        if (!BleApiManager.getInstance(LeonardoBleService.this).getBleApi().getDeviceSupportedFeatures().isKaHaRealtekChip() && !BleApiManager.getInstance(LeonardoBleService.this).getBleApi().getDeviceSupportedFeatures().isKaHaSifliChip()) {
                            LeonardoBleService leonardoBleService = LeonardoBleService.this;
                            CommandObject commandObject = leonardoBleService.currentCommand;
                            if (commandObject != null && leonardoBleService.streamPacketCount == commandObject.noOfPacketsAtaTime) {
                                leonardoBleService.shouldWaitFortheStreamResponse = true;
                                leonardoBleService.streamPacketCount = 0;
                            }
                            LinkedList<CommandObject> linkedList = leonardoBleService.k;
                            if (linkedList != null && linkedList.size() > 0) {
                                LeonardoBleService leonardoBleService2 = LeonardoBleService.this;
                                if (!leonardoBleService2.shouldWaitFortheStreamResponse) {
                                    leonardoBleService2.currentCommand = leonardoBleService2.k.getFirst();
                                    LeonardoBleService leonardoBleService3 = LeonardoBleService.this;
                                    int i2 = leonardoBleService3.streamPacketCount;
                                    CommandObject commandObject2 = leonardoBleService3.currentCommand;
                                    if (i2 <= commandObject2.noOfPacketsAtaTime) {
                                        if (leonardoBleService3.a(commandObject2.getCmdByte())) {
                                            LeonardoBleService leonardoBleService4 = LeonardoBleService.this;
                                            leonardoBleService4.streamPacketCount++;
                                            if (!leonardoBleService4.k.isEmpty()) {
                                                LeonardoBleService.this.k.removeFirst();
                                            }
                                            LeonardoBleService leonardoBleService5 = LeonardoBleService.this;
                                            leonardoBleService5.p.removeCallbacks(leonardoBleService5.streamQueueTimeoutRunnable);
                                            LeonardoBleService leonardoBleService6 = LeonardoBleService.this;
                                            leonardoBleService6.p.postDelayed(leonardoBleService6.streamQueueTimeoutRunnable, 20000L);
                                        } else {
                                            LinkedList<CommandObject> linkedList2 = LeonardoBleService.this.k;
                                            if (linkedList2 != null) {
                                                linkedList2.clear();
                                            }
                                            CommandObject commandObject3 = LeonardoBleService.this.currentCommand;
                                            if (commandObject3 != null && commandObject3.getResponseListener() != null) {
                                                LeonardoBleService.this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.5.4.2
                                                    @Override // java.lang.Runnable
                                                    public void run() {
                                                        LeonardoBleService.this.currentCommand.getResponseListener().onFailure(new Error(Type.COMMAND_WRITE_FAILED, "Command write failed"));
                                                    }
                                                });
                                            }
                                        }
                                    } else {
                                        leonardoBleService3.shouldWaitFortheStreamResponse = true;
                                        leonardoBleService3.streamPacketCount = 0;
                                    }
                                }
                            }
                        }
                        CommandObject commandObject4 = LeonardoBleService.this.currentCommand;
                        if (commandObject4 != null && commandObject4.getBaseRequest() != null && LeonardoBleService.this.currentCommand.getBaseRequest().getCommandName() != null) {
                            CommandNames commandName = LeonardoBleService.this.currentCommand.getBaseRequest().getCommandName();
                            CommandNames commandNames = CommandNames.WATCH_FACE;
                            if (commandName == commandNames || LeonardoBleService.this.currentCommand.getBaseRequest().getCommandName() == CommandNames.SEND_IMAGE) {
                                int totalBytesWritten = LeonardoBleService.this.currentCommand.getBaseRequest().getTotalBytesWritten();
                                if (totalBytesWritten == 0) {
                                    LeonardoBleService.this.currentCommand.getBaseRequest().setTotalBytesWritten((LeonardoBleService.this.currentCommand.getBaseRequest().getTotalBytesWritten() + LeonardoBleService.this.currentCommand.getCmdByte().length) - 12);
                                } else {
                                    LeonardoBleService.this.currentCommand.getBaseRequest().setTotalBytesWritten((LeonardoBleService.this.currentCommand.getBaseRequest().getTotalBytesWritten() + LeonardoBleService.this.currentCommand.getCmdByte().length) - 4);
                                }
                                if (LeonardoBleService.this.currentCommand.getBaseRequest().getCommandName() == commandNames) {
                                    LeonardoBleService leonardoBleService7 = LeonardoBleService.this;
                                    leonardoBleService7.shouldWaitFortheStreamResponse = (totalBytesWritten + (-6)) / 2048 != (leonardoBleService7.currentCommand.getBaseRequest().getTotalBytesWritten() + (-6)) / 2048;
                                } else {
                                    LeonardoBleService leonardoBleService8 = LeonardoBleService.this;
                                    leonardoBleService8.shouldWaitFortheStreamResponse = totalBytesWritten / 2048 != leonardoBleService8.currentCommand.getBaseRequest().getTotalBytesWritten() / 2048;
                                }
                                LinkedList<CommandObject> linkedList3 = LeonardoBleService.this.k;
                                if (linkedList3 != null && linkedList3.size() > 0) {
                                    LeonardoBleService leonardoBleService9 = LeonardoBleService.this;
                                    if (!leonardoBleService9.shouldWaitFortheStreamResponse) {
                                        leonardoBleService9.currentCommand = leonardoBleService9.k.getFirst();
                                        LeonardoBleService leonardoBleService10 = LeonardoBleService.this;
                                        if (leonardoBleService10.a(leonardoBleService10.currentCommand.getCmdByte())) {
                                            if (!LeonardoBleService.this.k.isEmpty()) {
                                                LeonardoBleService.this.k.removeFirst();
                                            }
                                            LeonardoBleService leonardoBleService11 = LeonardoBleService.this;
                                            leonardoBleService11.p.removeCallbacks(leonardoBleService11.streamQueueTimeoutRunnable);
                                            LeonardoBleService leonardoBleService12 = LeonardoBleService.this;
                                            leonardoBleService12.p.postDelayed(leonardoBleService12.streamQueueTimeoutRunnable, 20000L);
                                        } else {
                                            LinkedList<CommandObject> linkedList4 = LeonardoBleService.this.k;
                                            if (linkedList4 != null) {
                                                linkedList4.clear();
                                            }
                                            CommandObject commandObject5 = LeonardoBleService.this.currentCommand;
                                            if (commandObject5 != null && commandObject5.getResponseListener() != null) {
                                                LeonardoBleService.this.o.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.5.4.1
                                                    @Override // java.lang.Runnable
                                                    public void run() {
                                                        LeonardoBleService.this.currentCommand.getResponseListener().onFailure(new Error(Type.COMMAND_WRITE_FAILED, "Command write failed"));
                                                    }
                                                });
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }

        @Override // android.bluetooth.BluetoothGattCallback
        @SuppressLint({"NewApi"})
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            LeonardoBleService.this.N = System.currentTimeMillis();
            LeonardoBleService leonardoBleService = LeonardoBleService.this;
            leonardoBleService.A = BleUtils.getConnectionType(leonardoBleService.getApplicationContext());
            if (i != 0) {
                LeonardoBleService leonardoBleService2 = LeonardoBleService.this;
                leonardoBleService2.s.removeCallbacks(leonardoBleService2.x);
                LeonardoBleService.this.closeGattConnection();
                LeonardoBleService.this.M = new ConnectionError(i, System.currentTimeMillis());
                LeonardoBleService.this.a(CloveBleState.BleState.DISCONNECTED, true);
                if (LeonardoBleService.this.h) {
                    AnalyticsLog analyticsLog = new AnalyticsLog();
                    analyticsLog.setEventName(FirebaseEventParams.EventName.CV_BT_CONNECT.value);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put(FirebaseEventParams.MetaDataKeyNames.CV_STATUS.value, "error");
                    hashMap.put(FirebaseEventParams.MetaDataKeyNames.CV_APP_PROCESS_STATUS.value, "background");
                    hashMap.put(FirebaseEventParams.MetaDataKeyNames.CV_CODE.value, String.valueOf(i));
                    hashMap.put(FirebaseEventParams.MetaDataKeyNames.CV_PHONE_BATTERY_LEVEL.value, AppUtils.getPhoneBatteryLevel(LeonardoBleService.this));
                    analyticsLog.setMapData(hashMap);
                    CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                    LogHelper.d("Firebase event logged", "disconnection");
                }
                LeonardoBleService.this.h = false;
                String str = LeonardoBleService.S;
                LogHelper.e(str, "onConnectionStateChange Error GATT.  Error id : " + i, ModuleNames.BLEABSTRACT.getModuleName());
                LeonardoBleService leonardoBleService3 = LeonardoBleService.this;
                if (leonardoBleService3.A == ConnectionType.RECONNECT_ON_DISCONNECT) {
                    leonardoBleService3.scanLeDevice();
                    LeonardoBleService leonardoBleService4 = LeonardoBleService.this;
                    leonardoBleService4.r.postDelayed(leonardoBleService4.P, 3600000L);
                    return;
                }
                LogHelper.d(str, "mConnectType is RECONNECT_ON_DISCONNECT");
            } else if (i2 == 2) {
                LogHelper.d(LeonardoBleService.S, "onConnectionStateChange: Connected", ModuleNames.BLEABSTRACT.getModuleName());
                LeonardoBleService leonardoBleService5 = LeonardoBleService.this;
                leonardoBleService5.M = null;
                leonardoBleService5.r.removeCallbacksAndMessages(null);
                LeonardoBleService leonardoBleService6 = LeonardoBleService.this;
                leonardoBleService6.f.postDelayed(new AnonymousClass6(bluetoothGatt, 1), 1600L);
            } else if (i2 == 0) {
                LeonardoBleService.this.closeGattConnection();
                LeonardoBleService.this.a(CloveBleState.BleState.DISCONNECTED, true);
                LeonardoBleService.this.M = new ConnectionError(i, System.currentTimeMillis());
                String str2 = LeonardoBleService.S;
                ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                LogHelper.d(str2, "onConnectionStateChange: Disconnected", moduleNames.getModuleName());
                LinkedList<CommandObject> linkedList = LeonardoBleService.this.l;
                if (linkedList != null && linkedList.size() > 0) {
                    LeonardoBleService.this.l.clear();
                }
                if (LeonardoBleService.this.h) {
                    AnalyticsLog analyticsLog2 = new AnalyticsLog();
                    analyticsLog2.setEventName(FirebaseEventParams.EventName.CV_BT_CONNECT.value);
                    HashMap<String, String> hashMap2 = new HashMap<>();
                    hashMap2.put(FirebaseEventParams.MetaDataKeyNames.CV_STATUS.value, "error");
                    hashMap2.put(FirebaseEventParams.MetaDataKeyNames.CV_APP_PROCESS_STATUS.value, "background");
                    hashMap2.put(FirebaseEventParams.MetaDataKeyNames.CV_CODE.value, String.valueOf(i));
                    hashMap2.put(FirebaseEventParams.MetaDataKeyNames.CV_PHONE_BATTERY_LEVEL.value, AppUtils.getPhoneBatteryLevel(LeonardoBleService.this));
                    analyticsLog2.setMapData(hashMap2);
                    CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
                }
                LeonardoBleService.this.h = false;
                if (LeonardoBleService.this.A == ConnectionType.RECONNECT_ON_DISCONNECT) {
                    LogHelper.d(str2, "Initiating reconnect", moduleNames.getModuleName());
                    LogHelper.d(str2, "new state is disconnected, calling reconnect to config devices", moduleNames.getModuleName());
                    if (Build.VERSION.SDK_INT < 31) {
                        if ((LeonardoBleService.this.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 || LeonardoBleService.this.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 || LeonardoBleService.this.checkSelfPermission("android.permission.ACCESS_BACKGROUND_LOCATION") == 0) && PreferenceManagerAbstract.getInstance(LeonardoBleService.this).getShouldDoScanConnect()) {
                            LeonardoBleService.this.scanLeDevice();
                            return;
                        } else {
                            LogHelper.d(str2, "Sufficient permissions are not there to do the scan");
                            return;
                        }
                    } else if (LeonardoBleService.this.checkSelfPermission("android.permission.BLUETOOTH_SCAN") == 0) {
                        LeonardoBleService.this.scanLeDevice();
                        return;
                    } else {
                        LogHelper.d(str2, "Sufficient permissions are not there to do the scan");
                        return;
                    }
                }
                LogHelper.d(str2, "Reconnection type is not RECONNECT_ON_DISCONNECT so closing gatt permenently without scan");
                LeonardoBleService.this.closeGattConnection();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onMtuChanged(bluetoothGatt, i, i2);
            String str = LeonardoBleService.S;
            LogHelper.d(str, "onMtuChanged->" + i + Constants.SEPARATOR_COMMA + i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(final BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            String str = LeonardoBleService.S;
            ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
            LogHelper.d(str, i + "", moduleNames.getModuleName());
            if (i == 0) {
                LogHelper.d(str, "service discovered method", moduleNames.getModuleName());
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(LeonardoBleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                    LeonardoBleService.this.mBluetoothGatt.requestConnectionPriority(1);
                    LeonardoBleService leonardoBleService = LeonardoBleService.this;
                    leonardoBleService.s.removeCallbacks(leonardoBleService.x);
                    LeonardoBleService.this.f.post(new Runnable() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                LeonardoBleService.a(LeonardoBleService.this, bluetoothGatt);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                LeonardoBleService.this.closeGattConnection();
                                LeonardoBleService.this.scanLeDevice();
                            }
                        }
                    });
                    LeonardoBleService.this.u.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.5.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(LeonardoBleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                                boolean requestMtu = bluetoothGatt.requestMtu(185);
                                int i2 = 8;
                                while (!requestMtu) {
                                    i2--;
                                    if (i2 <= 0) {
                                        return;
                                    }
                                    requestMtu = bluetoothGatt.requestMtu(185);
                                }
                            }
                        }
                    }, 500L);
                    return;
                }
                return;
            }
            BleApiManager.getInstance(LeonardoBleService.this).getBleApi().restartService();
        }
    }

    /* renamed from: com.coveiot.android.bleabstract.services.LeonardoBleService$6  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass6 implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ BluetoothGatt f3846a;
        public final /* synthetic */ int b;

        public AnonymousClass6(BluetoothGatt bluetoothGatt, int i) {
            this.f3846a = bluetoothGatt;
            this.b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(LeonardoBleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                if (this.f3846a.discoverServices()) {
                    LeonardoBleService leonardoBleService = LeonardoBleService.this;
                    leonardoBleService.s.postDelayed(leonardoBleService.x, 30000L);
                    return;
                }
                int i = this.b;
                if (i > 3) {
                    LeonardoBleService.this.closeGattConnection();
                    LeonardoBleService.this.h = false;
                    LeonardoBleService.this.scanLeDevice();
                    return;
                }
                LeonardoBleService leonardoBleService2 = LeonardoBleService.this;
                leonardoBleService2.f.postDelayed(new AnonymousClass6(this.f3846a, i + 1), 1600L);
            }
        }
    }

    /* loaded from: classes2.dex */
    public class CharacteristicChangedRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f3850a;
        public char[] b;

        public CharacteristicChangedRunnable(byte[] bArr, char[] cArr) {
            this.f3850a = bArr;
            this.b = cArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            ProtocolParser protocolParser = ProtocolParser.getInstance();
            Context applicationContext = LeonardoBleService.this.getApplicationContext();
            LeonardoBleService leonardoBleService = LeonardoBleService.this;
            protocolParser.handleDeviceInput(applicationContext, leonardoBleService.currentCommand, this.f3850a, this.b, leonardoBleService.k);
        }
    }

    /* loaded from: classes2.dex */
    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public LeonardoBleService getService() {
            return LeonardoBleService.this;
        }
    }

    /* loaded from: classes2.dex */
    public class PPGPostRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public int f3852a;

        public PPGPostRunnable(LeonardoBleService leonardoBleService, int i) {
            this.f3852a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.LIVE_RAW_PPG, new RawPPGData(this.f3852a)));
        }
    }

    /* loaded from: classes2.dex */
    public class PPGRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f3853a;

        public PPGRunnable(byte[] bArr, char[] cArr) {
            this.f3853a = bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = 0;
            while (true) {
                byte[] bArr = this.f3853a;
                if (i >= bArr.length) {
                    return;
                }
                int i2 = (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8);
                LeonardoBleService leonardoBleService = LeonardoBleService.this;
                leonardoBleService.J.post(new PPGPostRunnable(leonardoBleService, i2));
                i += 2;
            }
        }
    }

    /* loaded from: classes2.dex */
    public class PhoneStateMonitor extends PhoneStateListener {
        public PhoneStateMonitor() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            String str2 = LeonardoBleService.S;
            LogHelper.d(str2, "Sudhee:onCallStateChanged: " + i, ModuleNames.BLEABSTRACT.getModuleName());
            super.onCallStateChanged(i, str);
            if (i == 0) {
                LeonardoBleService.this.getClass();
            } else if (i == 1) {
                LeonardoBleService.this.getClass();
            } else if (i != 2) {
            } else {
                LeonardoBleService.this.getClass();
            }
        }
    }

    /* loaded from: classes2.dex */
    public class QuickReplyPostRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public int f3855a;

        public QuickReplyPostRunnable(LeonardoBleService leonardoBleService, int i) {
            this.f3855a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBusManager.getInstance().getEventBus().post(new ResponseEvent(ResponseType.SEND_QUICK_REPLY, new SendQuickReplyRes(this.f3855a)));
        }
    }

    /* loaded from: classes2.dex */
    public class QuickReplyRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f3856a;

        public QuickReplyRunnable(byte[] bArr, char[] cArr) {
            this.f3856a = bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                byte[] bArr = this.f3856a;
                if (bArr.length <= 0) {
                    return;
                }
                LeonardoBleService leonardoBleService = LeonardoBleService.this;
                leonardoBleService.J.post(new QuickReplyPostRunnable(leonardoBleService, bArr[0] & 255));
            }
        }
    }

    /* loaded from: classes2.dex */
    public class TimeChangeReceiver extends BroadcastReceiver {
        public TimeChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("android.intent.action.TIMEZONE_CHANGED") || intent.getAction().equalsIgnoreCase("android.intent.action.TIME_SET")) {
                LeonardoBleService leonardoBleService = LeonardoBleService.this;
                if (leonardoBleService.bluetoothConnectionStatus == CloveBleState.BleState.CONNECTED) {
                    LeonardoBleService.d(leonardoBleService);
                    LeonardoBleService.this.getClass();
                    BleEventBusManager.getInstance().getEventBus().post(new StartRunnableEvent());
                }
            }
        }
    }

    static {
        T = null;
        IntentFilter intentFilter = new IntentFilter();
        T = intentFilter;
        intentFilter.addAction("android.intent.action.TIME_TICK");
        T.addAction("android.intent.action.TIMEZONE_CHANGED");
        T.addAction("android.intent.action.TIME_SET");
    }

    public LeonardoBleService() {
        Charset charset = StandardCharsets.UTF_8;
        this.packetSize = 0;
        this.n = new Handler();
        this.isExecuted = false;
        this.isConnectionInitiated = false;
        this.r = new Handler(Looper.getMainLooper());
        this.s = new Handler(Looper.getMainLooper());
        this.t = new TimeChangeReceiver();
        this.shouldWaitFortheStreamResponse = false;
        this.v = 0;
        this.w = 0;
        this.x = new Runnable() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.1
            @Override // java.lang.Runnable
            public void run() {
                LeonardoBleService.this.closeGattConnection();
                LeonardoBleService.this.scanLeDevice();
            }
        };
        this.z = new LocalBinder();
        this.F = -1L;
        new BroadcastReceiver() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(intent.getAction())) {
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(LeonardoBleService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                        PrintStream printStream = System.out;
                        printStream.println("UPDATE Name " + bluetoothDevice.getName() + " Value " + bluetoothDevice.getAddress() + " Bond state " + bluetoothDevice.getBondState());
                        if (bluetoothDevice.getBondState() == 12) {
                            bluetoothDevice.connectGatt(LeonardoBleService.this.getApplicationContext(), false, LeonardoBleService.this.O);
                        }
                    }
                }
            }
        };
        this.bluetoothConnectionStatus = CloveBleState.BleState.DISCONNECTED;
        this.H = new BroadcastReceiver() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                    int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                    if (intExtra == 3 || intExtra == 10) {
                        LogHelper.d(LeonardoBleService.S, "Bluetooth turned off", ModuleNames.BLEABSTRACT.getModuleName());
                        LeonardoBleService.this.a(CloveBleState.BleState.DISCONNECTED, true);
                        LeonardoBleService.this.stopScan();
                        LeonardoBleService.this.closeGattConnection();
                        LeonardoBleService leonardoBleService = LeonardoBleService.this;
                        Handler handler = leonardoBleService.K;
                        if (handler != null) {
                            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.3.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    LeonardoBleService.this.clearAllServiceParameters();
                                }
                            }, 1000L);
                        } else {
                            leonardoBleService.clearAllServiceParameters();
                        }
                        LeonardoBleService leonardoBleService2 = LeonardoBleService.this;
                        leonardoBleService2.isConnectionInitiated = false;
                        try {
                            if ((Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(leonardoBleService2, "android.permission.BLUETOOTH_SCAN") == 0) && BluetoothAdapter.getDefaultAdapter().isDiscovering()) {
                                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (intExtra != 12) {
                    } else {
                        LeonardoBleService.this.stopScan();
                        LeonardoBleService.this.clearAllServiceParameters();
                        LeonardoBleService.this.initServiceParams();
                        if (((Boolean) BlePreferenceManager.getPreference(LeonardoBleService.this.getApplicationContext(), CommonPreference.IS_BAND_UNPAIRED, Boolean.FALSE)).booleanValue()) {
                            return;
                        }
                        LeonardoBleService leonardoBleService3 = LeonardoBleService.this;
                        leonardoBleService3.c = (String) BlePreferenceManager.getPreference(leonardoBleService3.getApplicationContext(), CommonPreference.BLE_DEVICE_ADDRESS, "");
                        LeonardoBleService leonardoBleService4 = LeonardoBleService.this;
                        leonardoBleService4.A = BleUtils.getConnectionType(leonardoBleService4.getApplicationContext());
                        if (LeonardoBleService.this.f3828a.isEnabled()) {
                            LeonardoBleService.this.closeGattConnection();
                            try {
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(LeonardoBleService.this, "android.permission.BLUETOOTH_SCAN") == 0) {
                                BluetoothAdapter.getDefaultAdapter().startDiscovery();
                                String str = LeonardoBleService.S;
                                ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
                                LogHelper.d(str, "Bluetooth turned on", moduleNames.getModuleName());
                                LogHelper.d(str, "BT turned on calling reconnect to config devices", moduleNames.getModuleName());
                                if (Build.VERSION.SDK_INT < 31) {
                                    if ((LeonardoBleService.this.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 || LeonardoBleService.this.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 || LeonardoBleService.this.checkSelfPermission("android.permission.ACCESS_BACKGROUND_LOCATION") == 0) && PreferenceManagerAbstract.getInstance(LeonardoBleService.this).getShouldDoScanConnect()) {
                                        LeonardoBleService.this.scanLeDevice();
                                    } else {
                                        LogHelper.d(str, "Sufficient permissions are not there to do the scan");
                                    }
                                } else if (LeonardoBleService.this.checkSelfPermission("android.permission.BLUETOOTH_SCAN") == 0) {
                                    LeonardoBleService.this.scanLeDevice();
                                } else {
                                    LogHelper.d(str, "Sufficient permissions are not there to do the scan");
                                }
                            }
                        }
                    }
                } else if (action.equalsIgnoreCase("action_stop_service")) {
                    try {
                        LeonardoBleService.this.closeGattConnection();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    LogHelper.d(LeonardoBleService.S, "Action stop service recieved", ModuleNames.BLEABSTRACT.getModuleName());
                    LeonardoBleService.this.a(CloveBleState.BleState.DISCONNECTED, true);
                    LeonardoBleService.this.clearAllServiceParameters();
                }
            }
        };
        this.I = new Runnable() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.4
            @Override // java.lang.Runnable
            public void run() {
                LinkedList<CommandObject> linkedList = LeonardoBleService.this.l;
                if (linkedList != null && linkedList.size() > 0) {
                    LeonardoBleService.this.l.clear();
                }
                LeonardoBleService leonardoBleService = LeonardoBleService.this;
                leonardoBleService.currentCommand = null;
                leonardoBleService.isConnectionInitiated = false;
                BleDeviceInfo.clearInstance();
                LeonardoBleService.d(LeonardoBleService.this);
                if (BleApiManager.getInstance(LeonardoBleService.this).getBleApi().getDeviceSupportedFeatures().isTimeFormartCommandSupported()) {
                    LeonardoBleService.this.getClass();
                }
                if (BleApiManager.getInstance(LeonardoBleService.this).getBleApi().getDeviceSupportedFeatures().isPhoneTypeCommandSupported()) {
                    LeonardoBleService leonardoBleService2 = LeonardoBleService.this;
                    CommandObject commandObject = new CommandObject(leonardoBleService2.mBluetoothGatt, BleUUID.SET_PAIRING_PHONE_TYPE, false, CommandNames.SET_PAIRING_PHONE_TYPE);
                    commandObject.setResponseListener(leonardoBleService2);
                    leonardoBleService2.l.add(commandObject);
                }
                BleEventBusManager.getInstance().getEventBus().post(new StartRunnableEvent());
                LeonardoBleService.this.getClass();
            }
        };
        this.J = new Handler(Looper.getMainLooper());
        this.K = new Handler(Looper.getMainLooper());
        this.L = new Handler();
        this.dataBytes = new ArrayList<>();
        this.N = -1L;
        this.streamPacketCount = -1;
        this.O = new AnonymousClass5();
        this.streamQueueTimeoutRunnable = new Runnable() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.7
            @Override // java.lang.Runnable
            public void run() {
                CommandObject commandObject;
                if (LeonardoBleService.this.k.size() <= 0 || (commandObject = LeonardoBleService.this.currentCommand) == null || commandObject.getResponseListener() == null) {
                    return;
                }
                LeonardoBleService.this.k.clear();
                LeonardoBleService.this.currentCommand.getResponseListener().onFailure(new Error(Type.COMMAND_TIMED_OUT, "Something went wrong"));
                LogHelper.d(LeonardoBleService.S, "Time out for ", Arrays.toString(LeonardoBleService.this.currentCommand.getCmdByte()));
            }
        };
        this.P = new Runnable() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.10
            @Override // java.lang.Runnable
            public void run() {
                if (LeonardoBleService.this.bluetoothConnectionStatus != CloveBleState.BleState.CONNECTED) {
                    LogHelper.d(LeonardoBleService.S, "restart initiated", ModuleNames.BLEABSTRACT.getModuleName());
                    BleApiManager.getInstance(LeonardoBleService.this).getBleApi().restartService();
                    LeonardoBleService.this.r.postDelayed(this, 3600000L);
                }
            }
        };
        this.Q = -1L;
        this.R = new Runnable() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.12
            @Override // java.lang.Runnable
            public void run() {
                if (LeonardoBleService.b(LeonardoBleService.this)) {
                    BluetoothAdapter bluetoothAdapter = LeonardoBleService.this.f3828a;
                    if (bluetoothAdapter != null && !bluetoothAdapter.isEnabled()) {
                        LogHelper.d(LeonardoBleService.S, "Bluetooth is disabled during scanRunnable, scheduling stopping scan runnable");
                        LeonardoBleService.this.stopScan();
                        return;
                    }
                    if (LeonardoBleService.this.g != -1) {
                        long currentTimeMillis = System.currentTimeMillis();
                        LeonardoBleService leonardoBleService = LeonardoBleService.this;
                        long j = (currentTimeMillis - leonardoBleService.g) - 20000;
                        if (j > 5000) {
                            LogHelper.d("Scan Interrupt By System", "Latency: " + j);
                            AnalyticsLog analyticsLog = new AnalyticsLog();
                            analyticsLog.setEventName(FirebaseEventParams.EventName.CV_SCAN_INTERRUPT_SYSTEM.value);
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put(FirebaseEventParams.MetaDataKeyNames.CV_APP_PROCESS_STATUS.value, "background");
                            String str = FirebaseEventParams.MetaDataKeyNames.CV_TIME_LATENCY.value;
                            hashMap.put(str, "" + j);
                            hashMap.put(FirebaseEventParams.MetaDataKeyNames.CV_PHONE_BATTERY_LEVEL.value, AppUtils.getPhoneBatteryLevel(leonardoBleService));
                            analyticsLog.setMapData(hashMap);
                            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                        }
                    }
                    LeonardoBleService.this.g = System.currentTimeMillis();
                    LogHelper.d(LeonardoBleService.S, "Scan started");
                    try {
                        DeviceScanManager deviceScanManager = DeviceScanManager.getInstance(LeonardoBleService.this);
                        LeonardoBleService leonardoBleService2 = LeonardoBleService.this;
                        deviceScanManager.startScanForMAcAddress(leonardoBleService2, leonardoBleService2.c, 10000L, false, new ScanResult() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.12.1
                            @Override // com.coveiot.sdk.ble.scan.ScanResult
                            public void onDevicesFound(List<BleDevice> list, boolean z) {
                                if (!AppUtils.isEmpty(list)) {
                                    Iterator<BleDevice> it = list.iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        BleDevice next = it.next();
                                        if (next.getmDevice().getAddress().equalsIgnoreCase(LeonardoBleService.this.c)) {
                                            LogHelper.d(LeonardoBleService.S, "Device found during scan");
                                            LeonardoBleService.this.h = true;
                                            LeonardoBleService.this.stopScan();
                                            LeonardoBleService.this.a(next.getmDevice());
                                            break;
                                        }
                                    }
                                }
                                if (z) {
                                    LeonardoBleService leonardoBleService3 = LeonardoBleService.this;
                                    if (leonardoBleService3.h) {
                                        return;
                                    }
                                    LeonardoBleService.c(leonardoBleService3);
                                    LeonardoBleService.this.scanLeDevice();
                                }
                            }

                            @Override // com.coveiot.sdk.ble.scan.ScanResult
                            public void onScanFailed(int i) {
                                String str2 = LeonardoBleService.S;
                                LogHelper.d(str2, "onScanFailed with error " + i);
                                LeonardoBleService leonardoBleService3 = LeonardoBleService.this;
                                leonardoBleService3.getClass();
                                AnalyticsLog analyticsLog2 = new AnalyticsLog();
                                analyticsLog2.setEventName(FirebaseEventParams.EventName.CV_SCAN_FAILED.value);
                                HashMap<String, String> hashMap2 = new HashMap<>();
                                hashMap2.put(FirebaseEventParams.MetaDataKeyNames.CV_APP_PROCESS_STATUS.value, "background");
                                hashMap2.put(FirebaseEventParams.MetaDataKeyNames.CV_STATUS.value, "failed");
                                String str3 = FirebaseEventParams.MetaDataKeyNames.CV_CODE.value;
                                hashMap2.put(str3, i + "");
                                String str4 = FirebaseEventParams.MetaDataKeyNames.CV_TIME_SPENT_MILLS.value;
                                hashMap2.put(str4, "" + (System.currentTimeMillis() - leonardoBleService3.g) + " ms");
                                hashMap2.put(FirebaseEventParams.MetaDataKeyNames.CV_PHONE_BATTERY_LEVEL.value, AppUtils.getPhoneBatteryLevel(leonardoBleService3));
                                analyticsLog2.setMapData(hashMap2);
                                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
                                LeonardoBleService.this.h = false;
                                LeonardoBleService.c(LeonardoBleService.this);
                                LeonardoBleService.this.scanLeDevice();
                            }
                        }, leonardoBleService2.i);
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                        LeonardoBleService.this.scanLeDevice();
                    }
                    LeonardoBleService leonardoBleService3 = LeonardoBleService.this;
                    leonardoBleService3.i = !leonardoBleService3.i;
                    return;
                }
                if (System.currentTimeMillis() - LeonardoBleService.this.Q >= TimeUnit.HOURS.toMillis(12L)) {
                    LeonardoBleService leonardoBleService4 = LeonardoBleService.this;
                    leonardoBleService4.getClass();
                    try {
                        NotificationInfo data = BleApiUtils.getData();
                        if (data.getAppColor() == 0) {
                            BleApiManager.getInstance(leonardoBleService4);
                            data = BleApiUtils.getData();
                            if (data.getAppColor() == 0) {
                                BleApiUtils.getMetadata(leonardoBleService4);
                                data = BleApiUtils.getData();
                            }
                        }
                        PendingIntent activity = PendingIntent.getActivity(leonardoBleService4, 0, new Intent(data.getAppLauncherActivity()), 67108864);
                        String string = leonardoBleService4.getString(R.string.permission_issue_detedcted);
                        String string2 = leonardoBleService4.getString(R.string.permission_issue_detedcted_msg);
                        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(leonardoBleService4, com.coveiot.android.bleabstract.Constants.NOTIFICATION_CHANNEL_ID).setContentTitle(string).setContentText(string2).setAutoCancel(true).setStyle(new NotificationCompat.BigTextStyle().bigText(string2)).setColor(ContextCompat.getColor(leonardoBleService4, data.getAppColor())).setSmallIcon(data.getAppIcon()).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(activity);
                        contentIntent.setContentTitle(string);
                        contentIntent.setColor(ContextCompat.getColor(leonardoBleService4, data.getAppColor()));
                        contentIntent.setContentText(string2);
                        contentIntent.setSound(RingtoneManager.getDefaultUri(2));
                        contentIntent.setStyle(new NotificationCompat.BigTextStyle().bigText(string2));
                        contentIntent.setAutoCancel(true);
                        NotificationManager notificationManager = (NotificationManager) leonardoBleService4.getSystemService("notification");
                        if (Build.VERSION.SDK_INT >= 26) {
                            notificationManager.createNotificationChannel(new NotificationChannel(com.coveiot.android.bleabstract.Constants.NOTIFICATION_CHANNEL_ID, "boAt Crest notification", 3));
                        }
                        notificationManager.notify(com.coveiot.android.bleabstract.Constants.BLUETOOTH_PERMISSION_NOTIFICATION_ID, contentIntent.build());
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    LeonardoBleService.this.Q = System.currentTimeMillis();
                }
                LeonardoBleService leonardoBleService5 = LeonardoBleService.this;
                leonardoBleService5.getClass();
                LogHelper.d(LeonardoBleService.S, "logLocationPermissionNotAvailableForScan");
                AnalyticsLog analyticsLog2 = new AnalyticsLog();
                analyticsLog2.setEventName(FirebaseEventParams.EventName.CV_LOCATION_PERMISSIONS.value);
                HashMap<String, String> hashMap2 = new HashMap<>();
                hashMap2.put(FirebaseEventParams.MetaDataKeyNames.CV_APP_PROCESS_STATUS.value, "background");
                hashMap2.put(FirebaseEventParams.MetaDataKeyNames.CV_STATUS.value, "disable");
                hashMap2.put(FirebaseEventParams.MetaDataKeyNames.CV_PHONE_BATTERY_LEVEL.value, AppUtils.getPhoneBatteryLevel(leonardoBleService5));
                analyticsLog2.setMapData(hashMap2);
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
                LeonardoBleService.this.h = false;
                LeonardoBleService.this.scanLeDevice();
            }
        };
    }

    public static boolean b(LeonardoBleService leonardoBleService) {
        leonardoBleService.getClass();
        return Build.VERSION.SDK_INT > 30 ? leonardoBleService.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 && leonardoBleService.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 && leonardoBleService.checkSelfPermission("android.permission.BLUETOOTH_SCAN") == 0 : leonardoBleService.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 || leonardoBleService.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    public static void c(LeonardoBleService leonardoBleService) {
        leonardoBleService.getClass();
        try {
            if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(leonardoBleService, "android.permission.BLUETOOTH_CONNECT") != 0) {
                LogHelper.d(S, "Bluetooth connect permission is not there to close the gatt");
            } else {
                BluetoothGatt bluetoothGatt = leonardoBleService.mPreviousBluetoothGatt;
                if (bluetoothGatt != null) {
                    bluetoothGatt.disconnect();
                    leonardoBleService.mPreviousBluetoothGatt.close();
                } else {
                    LogHelper.d(S, " mPreviousBluetoothGatt is null ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void d(LeonardoBleService leonardoBleService) {
        int i;
        int i2;
        leonardoBleService.getClass();
        byte[] bArr = BleUUID.SET_DEVICE_TIME;
        Calendar calendar = Calendar.getInstance();
        String num = Integer.toString(calendar.get(1));
        if (num.length() > 0) {
            i = Integer.parseInt(num.substring(0, 2));
            i2 = Integer.parseInt(num.substring(2, 4));
        } else {
            i = 19;
            i2 = 47;
        }
        int i3 = calendar.get(5);
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        int i6 = calendar.get(13);
        TimeZone timeZone = calendar.getTimeZone();
        int offset = timeZone.getOffset(GregorianCalendar.getInstance(timeZone).getTimeInMillis());
        String[] split = String.format(Locale.ENGLISH, "%02d:%02d", Integer.valueOf(Math.abs(offset / TimeConstants.HOUR)), Integer.valueOf(Math.abs((offset / 60000) % 60))).split(":");
        byte[] bArr2 = new byte[10];
        bArr2[0] = (byte) i;
        bArr2[1] = (byte) i2;
        bArr2[2] = (byte) (calendar.get(2) + 1);
        bArr2[3] = (byte) i3;
        bArr2[4] = (byte) i4;
        bArr2[5] = (byte) i5;
        bArr2[6] = (byte) i6;
        if (split.length > 0 && split.length == 2) {
            if (offset >= 0) {
                bArr2[7] = 43;
            } else {
                bArr2[7] = 45;
            }
            bArr2[8] = Byte.parseByte(split[0]);
            bArr2[9] = Byte.parseByte(split[1]);
        }
        byte[] bArr3 = new byte[bArr.length + 10];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, 10);
        CommandObject commandObject = new CommandObject(leonardoBleService.mBluetoothGatt, bArr3, false, CommandNames.SET_DEVICE_TIME);
        commandObject.setResponseListener(leonardoBleService);
        leonardoBleService.l.add(commandObject);
    }

    public void clearAllServiceParameters() {
        closeGattConnection();
        Handler handler = this.j;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        if (this.d != null) {
            HandlerThread handlerThread = this.D;
            if (handlerThread != null && handlerThread.getLooper() != null) {
                this.D.getLooper().quit();
                this.D = null;
            }
            this.d.removeCallbacksAndMessages(null);
        }
        if (this.E != null) {
            HandlerThread handlerThread2 = this.C;
            if (handlerThread2 != null && handlerThread2.getLooper() != null) {
                this.C.getLooper().quit();
                this.C = null;
            }
            this.E.removeCallbacksAndMessages(null);
        }
        if (this.logHandler != null) {
            HandlerThread handlerThread3 = this.G;
            if (handlerThread3 != null && handlerThread3.getLooper() != null) {
                this.G.getLooper().quit();
                this.G = null;
            }
            this.logHandler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.e;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        Handler handler3 = this.u;
        if (handler3 != null) {
            handler3.removeCallbacksAndMessages(null);
        }
        Handler handler4 = this.J;
        if (handler4 != null) {
            handler4.removeCallbacksAndMessages(null);
        }
        Handler handler5 = this.K;
        if (handler5 != null) {
            handler5.removeCallbacksAndMessages(null);
        }
        Handler handler6 = this.n;
        if (handler6 != null) {
            handler6.removeCallbacksAndMessages(null);
        }
        Handler handler7 = this.L;
        if (handler7 != null) {
            handler7.removeCallbacksAndMessages(null);
        }
        if (this.f != null) {
            HandlerThread handlerThread4 = this.B;
            if (handlerThread4 != null && handlerThread4.getLooper() != null) {
                this.B.getLooper().quit();
                this.B = null;
            }
            this.f.removeCallbacksAndMessages(null);
        }
        if (this.p != null) {
            HandlerThread handlerThread5 = this.C;
            if (handlerThread5 != null && handlerThread5.getLooper() != null) {
                this.C.getLooper().quit();
                this.C = null;
            }
            this.p.removeCallbacksAndMessages(null);
        }
        Handler handler8 = this.o;
        if (handler8 != null) {
            handler8.removeCallbacksAndMessages(null);
        }
        Handler handler9 = this.q;
        if (handler9 != null) {
            handler9.removeCallbacksAndMessages(null);
        }
        a(CloveBleState.BleState.DISCONNECTED, true);
        Handler handler10 = this.y;
        if (handler10 != null) {
            handler10.removeCallbacksAndMessages(null);
        }
        LinkedList<CommandObject> linkedList = this.l;
        if (linkedList != null && linkedList.size() > 0) {
            this.l.clear();
        }
        this.isConnectionInitiated = false;
        this.f = null;
        this.e = null;
        this.J = null;
        this.K = null;
        this.j = null;
        this.d = null;
        this.E = null;
        this.logHandler = null;
        this.e = null;
        this.u = null;
        this.J = null;
        this.n = null;
        this.L = null;
        this.f = null;
        this.o = null;
        this.q = null;
        this.y = null;
        this.h = false;
    }

    public void closeGattConnection() {
        LinkedList<CommandObject> linkedList = this.k;
        if (linkedList != null) {
            linkedList.clear();
        }
        if (this.mBluetoothGatt == null) {
            return;
        }
        String str = S;
        LogHelper.i(str, "Closing GATT Connection " + this.mBluetoothGatt, ModuleNames.BLEABSTRACT.getModuleName());
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
            LogHelper.d(str, "closeGattConnection Bluetooth connect permission not available");
            return;
        }
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        this.mPreviousBluetoothGatt = bluetoothGatt;
        bluetoothGatt.disconnect();
        this.mBluetoothGatt.close();
        a(CloveBleState.BleState.DISCONNECTED, true);
        this.mBluetoothGatt = null;
    }

    public void connect(String str) {
        BleDeviceInfo.clearInstance();
        BlePreferenceManager.savePreference(getApplicationContext(), CommonPreference.BLE_DEVICE_ADDRESS, str);
        BlePreferenceManager.savePreference(getApplicationContext(), CommonPreference.BLE_CONNECTION_TYPE, ConnectionType.RECONNECT_ON_DISCONNECT.name());
        String str2 = S;
        LogHelper.d(str2, "connection type ++ " + BleUtils.getConnectionType(this), ModuleNames.BLEABSTRACT.getModuleName());
        b();
    }

    public ConnectionError getConnectionError() {
        return this.M;
    }

    public long getConnectionStageChangeTimeStamp() {
        return this.N;
    }

    public CloveBleState.BleState getConnectionState() {
        return this.bluetoothConnectionStatus;
    }

    public void init(Context context) {
        clearAllServiceParameters();
        initServiceParams();
        this.f3828a = ((BluetoothManager) getApplicationContext().getSystemService("bluetooth")).getAdapter();
        try {
            BleEventBusManager.getInstance().getEventBus().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            getApplicationContext().registerReceiver(this.H, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
            getApplicationContext().registerReceiver(this.H, new IntentFilter("action_stop_service"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        String str = S;
        LogHelper.i(str, "Obtained device Address is " + this.c, ModuleNames.BLEABSTRACT.getModuleName());
    }

    public void initServiceParams() {
        if (this.j == null) {
            this.j = new Handler(Looper.getMainLooper());
        }
        if (this.e == null) {
            this.e = new Handler(Looper.getMainLooper());
        }
        if (this.J == null) {
            this.J = new Handler(Looper.getMainLooper());
        }
        if (this.K == null) {
            this.K = new Handler(Looper.getMainLooper());
        }
        if (this.n == null) {
            this.n = new Handler(Looper.getMainLooper());
        }
        if (this.L == null) {
            this.L = new Handler(Looper.getMainLooper());
        }
        if (this.u == null) {
            this.u = new Handler(Looper.getMainLooper());
        }
        if (this.f == null) {
            String str = S;
            LogHelper.d(str, "mBLEActionCallback is null");
            if (this.B == null) {
                LogHelper.d(str, "gatt_response is null");
                this.B = new HandlerThread("gatt_response");
            }
            if (!this.B.isAlive()) {
                LogHelper.d(str, "gatt_response is not alive");
                this.B.start();
            }
            if (this.C == null) {
                LogHelper.d(str, "stream_thread is null");
                this.C = new HandlerThread("stream_thread");
            }
            if (!this.C.isAlive()) {
                LogHelper.d(str, "stream_thread is not alive");
                this.C.start();
            }
            this.f = new Handler(this.B.getLooper());
            this.E = new Handler(this.C.getLooper());
        }
        if (this.d == null) {
            LogHelper.d(S, "mScanHandler is null");
            this.D = new HandlerThread("ble_scan_thread");
        }
        if (!this.D.isAlive()) {
            LogHelper.d(S, "mScanHandler is not alive");
            this.D.start();
        }
        this.d = new Handler(this.D.getLooper());
        if (this.o == null) {
            this.o = new Handler(Looper.getMainLooper());
        }
        if (this.p == null) {
            LogHelper.d(S, "streamCmdHandler is null");
            this.p = new Handler(Looper.getMainLooper());
        }
        if (this.logHandler == null) {
            LogHelper.d(S, "logHandler is null");
            this.G = new HandlerThread("log_thread");
        }
        if (!this.G.isAlive()) {
            LogHelper.d(S, "logHandler is not alive");
            this.G.start();
        }
        this.logHandler = new Handler(this.G.getLooper());
        if (this.q == null) {
            this.q = new Handler(Looper.getMainLooper());
        }
        if (this.y == null) {
            this.y = new Handler(Looper.getMainLooper());
        }
        LogHelper.d(S, "initServiceParams", ModuleNames.BLEABSTRACT.getModuleName());
        a(CloveBleState.BleState.DISCONNECTED, false);
        this.isConnectionInitiated = false;
        this.h = false;
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return this.z;
    }

    @Override // android.app.Service
    public void onCreate() {
        a();
        getApplicationContext();
        registerReceiver(this.t, T);
    }

    @Override // android.app.Service
    public void onDestroy() {
        LogHelper.d(S, "destroy leo ble service", ModuleNames.BLEABSTRACT.getModuleName());
        try {
            unregisterReceiver(this.t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override // com.coveiot.sdk.ble.api.ResponseListener
    public void onFailure(Error error) {
    }

    @Override // com.coveiot.sdk.ble.api.ResponseListener
    public void onProgressUpdate(ProgressDataBean progressDataBean) {
    }

    @Override // com.coveiot.sdk.ble.api.ResponseListener
    public void onResponse(BaseResponse baseResponse) {
        if (baseResponse.getActivityType() == CommandType.SET_PAIRING_PHONE_TYPE) {
            a(CloveBleState.BleState.CONNECTED, true);
        } else if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isPhoneTypeCommandSupported() || baseResponse.getActivityType() != CommandType.SET_DEVICE_TIME) {
        } else {
            a(CloveBleState.BleState.CONNECTED, true);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            try {
                Intent intent2 = new Intent(this, LeonardoBleCmdService.class);
                if (Build.VERSION.SDK_INT >= 26) {
                    startForegroundService(intent2);
                } else {
                    startService(intent2);
                }
                return 3;
            } catch (Exception e) {
                try {
                    e.printStackTrace();
                    BleApiUtils.checkExceptionAndShowNotification(e, this);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        a();
        this.c = (String) BlePreferenceManager.getPreference(getApplicationContext(), CommonPreference.BLE_DEVICE_ADDRESS, "");
        this.A = BleUtils.getConnectionType(getApplicationContext());
        String str = S;
        LogHelper.i(str, "Obtained device Address is " + this.c, ModuleNames.BLEABSTRACT.getModuleName());
        init(getApplicationContext());
        if (!BleUtils.isEmpty(this.c)) {
            b();
        }
        return 3;
    }

    public void readDeviceInfo(BaseRequest baseRequest, ResponseListener responseListener) {
        LinkedList<CommandObject> linkedList = this.m;
        if (linkedList == null || this.mBluetoothGatt == null) {
            return;
        }
        linkedList.clear();
        for (BluetoothGattService bluetoothGattService : this.mBluetoothGatt.getServices()) {
            if (bluetoothGattService != null && bluetoothGattService.getUuid() != null) {
                if (BleUUID.DEVICE_INFO_SERVICE_UUID.equalsIgnoreCase(bluetoothGattService.getUuid().toString())) {
                    BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
                    for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                        CommandObject commandObject = new CommandObject(bluetoothGatt, bluetoothGattCharacteristic, false, CommandNames.DEVICE_INFO);
                        commandObject.setBaseRequest(baseRequest);
                        commandObject.setResponseListener(responseListener);
                    }
                } else if (BleUUID.GENERIC_INFO_SERVICE_UUID.equalsIgnoreCase(bluetoothGattService.getUuid().toString())) {
                    BluetoothGatt bluetoothGatt2 = this.mBluetoothGatt;
                    for (BluetoothGattCharacteristic bluetoothGattCharacteristic2 : bluetoothGattService.getCharacteristics()) {
                        if (BleUUID.DEVICE_NAME_CHAR_UUID.equalsIgnoreCase(bluetoothGattCharacteristic2.getUuid().toString())) {
                            CommandObject commandObject2 = new CommandObject(bluetoothGatt2, bluetoothGattCharacteristic2, false, CommandNames.GET_DEVICE_NAME);
                            commandObject2.setBaseRequest(baseRequest);
                            commandObject2.setResponseListener(responseListener);
                        }
                    }
                }
            }
        }
    }

    public void reconnectToConfiguredDevice() {
        closeGattConnection();
        this.c = (String) BlePreferenceManager.getPreference(getApplicationContext(), CommonPreference.BLE_DEVICE_ADDRESS, "");
        this.A = BleUtils.getConnectionType(getApplicationContext());
        this.isConnectionInitiated = true;
        if (this.f3828a.isEnabled()) {
            if (!BleUtils.isEmpty(this.c) && BluetoothAdapter.checkBluetoothAddress(this.c)) {
                a(this.f3828a.getRemoteDevice(this.c));
                return;
            }
            String str = S;
            LogHelper.e(str, "Trying to connect, address is empty or small case " + this.c, ModuleNames.BLEABSTRACT.getModuleName());
            return;
        }
        LogHelper.e(S, "Bluetooth is disabled", ModuleNames.BLEABSTRACT.getModuleName());
    }

    @Override // com.coveiot.sdk.ble.api.ResponseListener
    public void retryCommand(BaseRequest baseRequest) {
    }

    public void scanLeDevice() {
        if (this.D != null && this.d != null) {
            BluetoothAdapter bluetoothAdapter = this.f3828a;
            if (bluetoothAdapter != null) {
                if (!bluetoothAdapter.isEnabled()) {
                    LogHelper.d(S, "Bluetooth is disabled during scanRunnable, scheduling stopping scan runnable");
                    stopScan();
                    return;
                }
                a(CloveBleState.BleState.SCANNING, true);
                this.d.postDelayed(this.R, 10000L);
                this.v++;
                return;
            }
            LogHelper.d(S, "Bluetooth adapter null , scheduling stopping scan runnable");
            this.f3828a = ((BluetoothManager) getSystemService("bluetooth")).getAdapter();
            return;
        }
        HandlerThread handlerThread = new HandlerThread("ble_scan_thread");
        this.D = handlerThread;
        handlerThread.start();
        this.d = new Handler(this.D.getLooper());
        scanLeDevice();
    }

    public void stopScan() {
        this.g = -1L;
        this.v = 0;
        Handler handler = this.d;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        try {
            DeviceScanManager.getInstance(this).stopScan();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unregisterReceivers() {
        if (this.H != null) {
            try {
                getApplicationContext().unregisterReceiver(this.H);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            try {
                BleEventBusManager.getInstance().getEventBus().unregister(this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public final boolean a(byte[] bArr) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic;
        BluetoothGattCharacteristic bluetoothGattCharacteristic2;
        if (this.mBluetoothGatt == null || (bluetoothGattCharacteristic = this.writeCharecterstic) == null) {
            return false;
        }
        bluetoothGattCharacteristic.setValue(bArr);
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            boolean writeCharacteristic = this.mBluetoothGatt.writeCharacteristic(this.writeCharecterstic);
            int i = 16;
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
                if (bluetoothGatt != null && (bluetoothGattCharacteristic2 = this.writeCharecterstic) != null) {
                    writeCharacteristic = bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic2);
                    if (writeCharacteristic) {
                        LogHelper.d(S + " Inside loop Reqst sent is ++ ", BleApiUtils.byte2Hex(bArr), ModuleNames.BLEABSTRACT.getModuleName());
                    } else {
                        LogHelper.e(S + " Inside loop Reqst sent failed is ++ ", BleApiUtils.byte2Hex(bArr), ModuleNames.BLEABSTRACT.getModuleName());
                    }
                }
            }
            if (writeCharacteristic) {
                LogHelper.d(S + " After loop Reqst sent is ++ ", BleApiUtils.byte2Hex(bArr), ModuleNames.BLEABSTRACT.getModuleName());
            } else {
                NotificationInfo data = BleApiUtils.getData();
                Intent intent = new Intent("android.settings.BLUETOOTH_SETTINGS");
                intent.setFlags(PKIFailureInfo.duplicateCertReq);
                PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 67108864);
                String string = getString(R.string.bluetooth_issue_detedcted);
                String string2 = getString(R.string.bluetooth_issue_detedcted_msg);
                NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(this, com.coveiot.android.bleabstract.Constants.NOTIFICATION_CHANNEL_ID).setContentTitle(string).setContentText(string2).setAutoCancel(true).setStyle(new NotificationCompat.BigTextStyle().bigText(string2)).setColor(ContextCompat.getColor(this, data.getAppColor())).setSmallIcon(data.getAppIcon()).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(activity);
                contentIntent.setContentTitle(string);
                contentIntent.setColor(ContextCompat.getColor(this, data.getAppColor()));
                contentIntent.setContentText(string2);
                contentIntent.setSound(RingtoneManager.getDefaultUri(2));
                contentIntent.setStyle(new NotificationCompat.BigTextStyle().bigText(string2));
                contentIntent.setAutoCancel(true);
                NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
                if (Build.VERSION.SDK_INT >= 26) {
                    notificationManager.createNotificationChannel(new NotificationChannel(com.coveiot.android.bleabstract.Constants.NOTIFICATION_CHANNEL_ID, "boAt Crest notification", 3));
                }
                notificationManager.notify(com.coveiot.android.bleabstract.Constants.BLUETOOTH_TOGGLE_NOTIFICATION_ID, contentIntent.build());
                LogHelper.e(S + " After loop Reqst sent failed is ++ ", BleApiUtils.byte2Hex(bArr), ModuleNames.BLEABSTRACT.getModuleName());
            }
            return writeCharacteristic;
        }
        return false;
    }

    @RequiresApi(api = 23)
    public final void b() {
        BluetoothAdapter bluetoothAdapter = this.f3828a;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            return;
        }
        LogHelper.d(S, "Init BL calling reconnect to config devices", ModuleNames.BLEABSTRACT.getModuleName());
        reconnectToConfiguredDevice();
    }

    public static void a(LeonardoBleService leonardoBleService, BluetoothGatt bluetoothGatt) {
        leonardoBleService.mBluetoothGatt = bluetoothGatt;
        leonardoBleService.m.clear();
        String str = S;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.d(str, "initialize ble services", moduleNames.getModuleName());
        if (bluetoothGatt.getServices() != null && bluetoothGatt.getServices().size() != 0) {
            leonardoBleService.readCharacteristic = null;
            leonardoBleService.writeCharecterstic = null;
            for (BluetoothGattService bluetoothGattService : bluetoothGatt.getServices()) {
                if (BleUUID.UART_SERVICE_UUID.equalsIgnoreCase(bluetoothGattService.getUuid().toString())) {
                    leonardoBleService.b = bluetoothGattService;
                    for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                        if (bluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(BleUUID.UART_READ_CHARATERISTICS_UUID)) {
                            leonardoBleService.readCharacteristic = bluetoothGattCharacteristic;
                            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(leonardoBleService, "android.permission.BLUETOOTH_CONNECT") == 0) {
                                bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
                            }
                            BluetoothGattDescriptor descriptor = leonardoBleService.readCharacteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
                            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                            bluetoothGatt.writeDescriptor(descriptor);
                            LogHelper.d(S, "main ble service read", ModuleNames.BLEABSTRACT.getModuleName());
                        } else if (bluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(BleUUID.UART_WRITE_CHARATERISTICS_UUID)) {
                            leonardoBleService.writeCharecterstic = bluetoothGattCharacteristic;
                            LogHelper.d(S, "main ble service write", ModuleNames.BLEABSTRACT.getModuleName());
                        }
                    }
                } else if (BleUUID.GENERIC_INFO_SERVICE_UUID.equalsIgnoreCase(bluetoothGattService.getUuid().toString())) {
                    LogHelper.d(S, "generic ble service", ModuleNames.BLEABSTRACT.getModuleName());
                } else if (BleUUID.DEVICE_INFO_SERVICE_UUID.equalsIgnoreCase(bluetoothGattService.getUuid().toString())) {
                    LogHelper.d(S, "device info ble service", ModuleNames.BLEABSTRACT.getModuleName());
                } else if (BleUUID.BATTERY_SERVICE_UUID.equalsIgnoreCase(bluetoothGattService.getUuid().toString())) {
                    LogHelper.d(S, "battery ble service", ModuleNames.BLEABSTRACT.getModuleName());
                }
            }
            if (leonardoBleService.readCharacteristic != null && leonardoBleService.writeCharecterstic != null) {
                leonardoBleService.w = 0;
                AnalyticsLog analyticsLog = new AnalyticsLog();
                analyticsLog.setEventName(FirebaseEventParams.EventName.CV_BT_CONNECT.value);
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(FirebaseEventParams.MetaDataKeyNames.CV_STATUS.value, "ok");
                hashMap.put(FirebaseEventParams.MetaDataKeyNames.CV_TIME_SPENT_MILLS.value, String.valueOf(System.currentTimeMillis() - leonardoBleService.F));
                hashMap.put(FirebaseEventParams.MetaDataKeyNames.CV_PHONE_BATTERY_LEVEL.value, AppUtils.getPhoneBatteryLevel(leonardoBleService));
                analyticsLog.setMapData(hashMap);
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                leonardoBleService.j.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.8
                    @Override // java.lang.Runnable
                    public void run() {
                        final LeonardoBleService leonardoBleService2 = LeonardoBleService.this;
                        CommandObject commandObject = new CommandObject(leonardoBleService2.mBluetoothGatt, BleUUID.GET_DEVICE_TIME, false, CommandNames.GET_DEVICE_TIME);
                        commandObject.setResponseListener(new ResponseListener() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.9
                            @Override // com.coveiot.sdk.ble.api.ResponseListener
                            public void onFailure(Error error) {
                                LeonardoBleService.a(LeonardoBleService.this);
                            }

                            @Override // com.coveiot.sdk.ble.api.ResponseListener
                            public void onProgressUpdate(ProgressDataBean progressDataBean) {
                            }

                            @Override // com.coveiot.sdk.ble.api.ResponseListener
                            public void onResponse(BaseResponse baseResponse) {
                                if (baseResponse instanceof GetTimeRes) {
                                    Calendar timeInCalendar = ((GetTimeRes) baseResponse).getTimeInCalendar();
                                    Calendar calendar = Calendar.getInstance();
                                    if (calendar.get(6) == timeInCalendar.get(6) && calendar.get(11) == timeInCalendar.get(11) && calendar.get(12) == timeInCalendar.get(12) && calendar.get(1) == timeInCalendar.get(1)) {
                                        LeonardoBleService.a(LeonardoBleService.this);
                                    } else if (calendar.getTimeInMillis() - timeInCalendar.getTimeInMillis() < Constants.ONE_MIN_IN_MILLIS && timeInCalendar.getTimeInMillis() - calendar.getTimeInMillis() < Constants.ONE_MIN_IN_MILLIS) {
                                        LeonardoBleService.a(LeonardoBleService.this);
                                    } else {
                                        LogHelper.d(LeonardoBleService.S, "Watch reset found time difference");
                                        LeonardoBleService leonardoBleService3 = LeonardoBleService.this;
                                        leonardoBleService3.getClass();
                                        BlePreferenceManager.savePreference(leonardoBleService3, CommonPreference.SHOULD_UPDATE_SETTINGS_BAND, Boolean.TRUE);
                                        Handler handler = leonardoBleService3.J;
                                        if (handler != null) {
                                            handler.removeCallbacks(null);
                                            leonardoBleService3.J.postDelayed(leonardoBleService3.I, 500L);
                                        }
                                    }
                                }
                            }

                            @Override // com.coveiot.sdk.ble.api.ResponseListener
                            public void retryCommand(BaseRequest baseRequest) {
                            }
                        });
                        leonardoBleService2.l.add(commandObject);
                        BleEventBusManager.getInstance().getEventBus().post(new StartRunnableEvent());
                    }
                }, 1000L);
                return;
            }
            LogHelper.d(S, " read  or write characteristic is null", ModuleNames.BLEABSTRACT.getModuleName());
            try {
                if (leonardoBleService.w <= 3) {
                    bluetoothGatt.getClass().getMethod("refresh", new Class[0]).invoke(bluetoothGatt, new Object[0]);
                    leonardoBleService.w++;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
            leonardoBleService.stopScan();
            leonardoBleService.closeGattConnection();
            leonardoBleService.isConnectionInitiated = false;
            leonardoBleService.h = false;
            leonardoBleService.a(CloveBleState.BleState.DISCONNECTED, true);
            String str2 = S;
            LogHelper.d(str2, "initiateBleServices, calling reconnect to config devices", ModuleNames.BLEABSTRACT.getModuleName());
            if (Build.VERSION.SDK_INT < 31) {
                if ((leonardoBleService.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 || leonardoBleService.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 || leonardoBleService.checkSelfPermission("android.permission.ACCESS_BACKGROUND_LOCATION") == 0) && PreferenceManagerAbstract.getInstance(leonardoBleService).getShouldDoScanConnect()) {
                    leonardoBleService.scanLeDevice();
                    return;
                } else {
                    LogHelper.d(str2, "Sufficient permissions are not there to do the scan");
                    return;
                }
            } else if (leonardoBleService.checkSelfPermission("android.permission.BLUETOOTH_SCAN") == 0) {
                leonardoBleService.scanLeDevice();
                return;
            } else {
                LogHelper.d(str2, "Sufficient permissions are not there to do the scan");
                return;
            }
        }
        LogHelper.d(str, " gatt service is null or empty", moduleNames.getModuleName());
        leonardoBleService.stopScan();
        leonardoBleService.closeGattConnection();
        leonardoBleService.isConnectionInitiated = false;
        leonardoBleService.h = false;
        leonardoBleService.a(CloveBleState.BleState.DISCONNECTED, true);
        LogHelper.d(str, "initiateBleServices and gatt service are null, calling reconnect to config devices", moduleNames.getModuleName());
        if (Build.VERSION.SDK_INT < 31) {
            if ((leonardoBleService.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 || leonardoBleService.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 || leonardoBleService.checkSelfPermission("android.permission.ACCESS_BACKGROUND_LOCATION") == 0) && PreferenceManagerAbstract.getInstance(leonardoBleService).getShouldDoScanConnect()) {
                leonardoBleService.scanLeDevice();
            } else {
                LogHelper.d(str, "Sufficient permissions are not there to do the scan");
            }
        } else if (leonardoBleService.checkSelfPermission("android.permission.BLUETOOTH_SCAN") == 0) {
            leonardoBleService.scanLeDevice();
        } else {
            LogHelper.d(str, "Sufficient permissions are not there to do the scan");
        }
    }

    public static void a(LeonardoBleService leonardoBleService) {
        Handler handler = leonardoBleService.J;
        if (handler != null) {
            handler.removeCallbacks(null);
            leonardoBleService.J.postDelayed(leonardoBleService.I, 500L);
        }
    }

    public final void a(final BluetoothDevice bluetoothDevice) {
        this.g = -1L;
        this.v = 0;
        stopScan();
        a(CloveBleState.BleState.CONNECTING, true);
        Handler handler = this.r;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        closeGattConnection();
        this.mBluetoothDevice = bluetoothDevice;
        if (this.J == null) {
            this.J = new Handler(Looper.getMainLooper());
        }
        this.J.removeCallbacksAndMessages(null);
        this.J.postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.11
            @Override // java.lang.Runnable
            public void run() {
                int i = Build.VERSION.SDK_INT;
                if (i >= 31 && ContextCompat.checkSelfPermission(LeonardoBleService.this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                    LogHelper.d(LeonardoBleService.S, "connectToDevice Bluetooth connect permission not available");
                    return;
                }
                LeonardoBleService.this.F = System.currentTimeMillis();
                LeonardoBleService.this.a(CloveBleState.BleState.CONNECTING, true);
                if (i >= 26) {
                    LeonardoBleService leonardoBleService = LeonardoBleService.this;
                    leonardoBleService.mBluetoothGatt = bluetoothDevice.connectGatt(leonardoBleService.getApplicationContext(), false, LeonardoBleService.this.O, 2, 1);
                    return;
                }
                LeonardoBleService leonardoBleService2 = LeonardoBleService.this;
                leonardoBleService2.mBluetoothGatt = bluetoothDevice.connectGatt(leonardoBleService2.getApplicationContext(), false, LeonardoBleService.this.O, 2);
            }
        }, 500L);
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
            int i = Build.VERSION.SDK_INT;
            if (i >= 26) {
                NotificationChannel notificationChannel = new NotificationChannel("101", data.getAppDesc(), 2);
                notificationChannel.enableLights(false);
                ((NotificationManager) getSystemService("notification")).createNotificationChannel(notificationChannel);
                build = new Notification.Builder(this, "101").setContentTitle(data.getAppDesc()).setColor(ContextCompat.getColor(this, data.getAppColor())).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
            } else if (i >= 21) {
                build = new Notification.Builder(this).setContentTitle(data.getAppDesc()).setSmallIcon(data.getAppIcon()).setColor(ContextCompat.getColor(this, data.getAppColor())).setContentIntent(activity).build();
            } else {
                build = new Notification.Builder(this).setContentTitle(data.getAppDesc()).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
            }
            startForeground(101, build);
        } catch (Exception e) {
            e.printStackTrace();
            BleApiUtils.checkExceptionAndShowNotification(e, this);
        }
    }

    public final void a(final CloveBleState.BleState bleState, boolean z) {
        Handler handler;
        if (this.bluetoothConnectionStatus != bleState) {
            this.bluetoothConnectionStatus = bleState;
            this.isPPGStarted = false;
            String str = S;
            LogHelper.d(str, "Connection state updated to " + bleState.toString(), ModuleNames.BLEABSTRACT.getModuleName());
            if (!z || (handler = this.K) == null) {
                return;
            }
            handler.removeCallbacksAndMessages(null);
            this.K.post(new Runnable(this) { // from class: com.coveiot.android.bleabstract.services.LeonardoBleService.13
                @Override // java.lang.Runnable
                public void run() {
                    BleEventBusManager.getInstance().getEventBus().post(new CloveBleState(bleState));
                }
            });
        }
    }
}
