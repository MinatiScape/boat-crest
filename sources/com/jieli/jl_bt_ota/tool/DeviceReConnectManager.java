package com.jieli.jl_bt_ota.tool;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import com.jieli.jl_bt_ota.constant.ErrorCode;
import com.jieli.jl_bt_ota.constant.JL_Constant;
import com.jieli.jl_bt_ota.impl.BluetoothOTAManager;
import com.jieli.jl_bt_ota.interfaces.BtEventCallback;
import com.jieli.jl_bt_ota.model.BleScanMessage;
import com.jieli.jl_bt_ota.model.OTAError;
import com.jieli.jl_bt_ota.model.ReConnectDevMsg;
import com.jieli.jl_bt_ota.model.base.BaseError;
import com.jieli.jl_bt_ota.util.BluetoothUtil;
import com.jieli.jl_bt_ota.util.CHexConver;
import com.jieli.jl_bt_ota.util.JL_Log;
import com.jieli.jl_bt_ota.util.ParseDataUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes11.dex */
public class DeviceReConnectManager {
    public static long RECONNECT_TIMEOUT = 65000;
    private static final String i = "DeviceReConnectManager";
    private static final int j = 3000;
    private static final int k = 20000;
    private static final int l = 2;
    private static final int m = 30000;
    private static final int n = 2000;
    private static final int o = 37973;
    private static final int p = 37974;
    private static final int q = 37975;

    /* renamed from: a  reason: collision with root package name */
    private final Context f12367a;
    private final BluetoothOTAManager b;
    private volatile ReConnectDevMsg c;
    private long d = 0;
    private long e = 0;
    private final Map<String, BleScanMessage> f = new HashMap();
    private final Handler g = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.jieli.jl_bt_ota.tool.DeviceReConnectManager.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case DeviceReConnectManager.o /* 37973 */:
                    DeviceReConnectManager.this.b();
                    return true;
                case DeviceReConnectManager.p /* 37974 */:
                    String str = DeviceReConnectManager.i;
                    JL_Log.w(str, "MSG_RECONNECT_DEVICE_TIMEOUT >>>>> " + DeviceReConnectManager.this.c);
                    if (DeviceReConnectManager.this.c == null || DeviceReConnectManager.this.g()) {
                        return true;
                    }
                    DeviceReConnectManager deviceReConnectManager = DeviceReConnectManager.this;
                    deviceReConnectManager.a(OTAError.buildError(ErrorCode.SUB_ERR_RECONNECT_TIMEOUT, deviceReConnectManager.c.toString()));
                    return true;
                case DeviceReConnectManager.q /* 37975 */:
                    String str2 = DeviceReConnectManager.i;
                    JL_Log.w(str2, "MSG_CONNECT_DEVICE_TIMEOUT >>>>> " + DeviceReConnectManager.this.c);
                    if (DeviceReConnectManager.this.c != null) {
                        DeviceReConnectManager.this.c.setState(0);
                        DeviceReConnectManager deviceReConnectManager2 = DeviceReConnectManager.this;
                        deviceReConnectManager2.a(deviceReConnectManager2.c.getAddress());
                        return true;
                    }
                    return true;
                default:
                    return true;
            }
        }
    });
    private final BtEventCallback h;

    public DeviceReConnectManager(@NonNull Context context, @NonNull BluetoothOTAManager bluetoothOTAManager) {
        BtEventCallback btEventCallback = new BtEventCallback() { // from class: com.jieli.jl_bt_ota.tool.DeviceReConnectManager.2
            @Override // com.jieli.jl_bt_ota.interfaces.BtEventCallback, com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
            public void onAdapterStatus(boolean z, boolean z2) {
                if (z || !DeviceReConnectManager.this.isDeviceReconnecting()) {
                    return;
                }
                JL_Log.d(DeviceReConnectManager.i, "onAdapterStatus : bluetooth close.");
            }

            @Override // com.jieli.jl_bt_ota.interfaces.BtEventCallback, com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
            public void onConnection(BluetoothDevice bluetoothDevice, int i2) {
                if (bluetoothDevice == null) {
                    return;
                }
                boolean z = DeviceReConnectManager.this.g.hasMessages(DeviceReConnectManager.q) || DeviceReConnectManager.this.f();
                String str = DeviceReConnectManager.i;
                JL_Log.d(str, "-onConnection- isConnecting: " + z);
                if (z) {
                    BleScanMessage bleScanMessage = (BleScanMessage) DeviceReConnectManager.this.f.get(bluetoothDevice.getAddress());
                    byte[] bArr = null;
                    if (bleScanMessage != null) {
                        String str2 = DeviceReConnectManager.i;
                        JL_Log.d(str2, "-onConnection- bleScanMessage: " + bleScanMessage);
                        bArr = bleScanMessage.getRawData();
                    }
                    boolean checkIsReconnectDevice = DeviceReConnectManager.this.checkIsReconnectDevice(bluetoothDevice, bArr);
                    JL_Log.w(DeviceReConnectManager.i, String.format(Locale.getDefault(), "-onConnection- device : %s, status : %d, isReConnectDevice : %s", DeviceReConnectManager.this.b(bluetoothDevice), Integer.valueOf(i2), Boolean.valueOf(checkIsReconnectDevice)));
                    if (checkIsReconnectDevice) {
                        DeviceReConnectManager.this.d();
                        if (DeviceReConnectManager.this.c != null) {
                            DeviceReConnectManager.this.c.setState(0);
                        }
                        DeviceReConnectManager.this.g.removeMessages(DeviceReConnectManager.q);
                        if (i2 == 1) {
                            JL_Log.d(DeviceReConnectManager.i, "-onConnection- reconnect device success.");
                            DeviceReConnectManager.this.stopReconnectTask();
                        } else if (i2 == 2 || i2 == 0) {
                            JL_Log.i(DeviceReConnectManager.i, "-onConnection- connect device failed.");
                            DeviceReConnectManager.this.a(bluetoothDevice.getAddress());
                        }
                    }
                }
            }

            @Override // com.jieli.jl_bt_ota.interfaces.BtEventCallback, com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
            public void onDiscovery(BluetoothDevice bluetoothDevice, BleScanMessage bleScanMessage) {
                if (bluetoothDevice == null) {
                    return;
                }
                boolean isDeviceReconnecting = DeviceReConnectManager.this.isDeviceReconnecting();
                byte[] bArr = null;
                if (bleScanMessage != null) {
                    DeviceReConnectManager.this.f.put(bluetoothDevice.getAddress(), bleScanMessage);
                    bArr = bleScanMessage.getRawData();
                }
                boolean checkIsReconnectDevice = DeviceReConnectManager.this.checkIsReconnectDevice(bluetoothDevice, bArr);
                JL_Log.i(DeviceReConnectManager.i, String.format(Locale.getDefault(), "-onDiscovery- isDeviceReconnecting : %s, isReConnectDevice : %s, device : %s", Boolean.valueOf(isDeviceReconnecting), Boolean.valueOf(checkIsReconnectDevice), DeviceReConnectManager.this.b(bluetoothDevice)));
                if (isDeviceReconnecting && checkIsReconnectDevice) {
                    DeviceReConnectManager.this.h();
                    DeviceReConnectManager.this.a(bluetoothDevice);
                }
            }

            @Override // com.jieli.jl_bt_ota.interfaces.BtEventCallback, com.jieli.jl_bt_ota.interfaces.IBluetoothCallback
            public void onDiscoveryStatus(boolean z, boolean z2) {
                String str = DeviceReConnectManager.i;
                JL_Log.d(str, "onDiscoveryStatus : " + z2);
                if (!DeviceReConnectManager.this.isDeviceReconnecting() || DeviceReConnectManager.this.f()) {
                    return;
                }
                if (z2) {
                    if (DeviceReConnectManager.this.c == null || DeviceReConnectManager.this.c.getState() == 2) {
                        return;
                    }
                    DeviceReConnectManager.this.c.setState(1);
                    return;
                }
                JL_Log.d(DeviceReConnectManager.i, "onDiscoveryStatus : ready start scan");
                DeviceReConnectManager.this.g.sendEmptyMessage(DeviceReConnectManager.o);
            }
        };
        this.h = btEventCallback;
        this.f12367a = context;
        this.b = bluetoothOTAManager;
        bluetoothOTAManager.registerBluetoothCallback(btEventCallback);
    }

    public boolean checkIsReconnectDevice(BluetoothDevice bluetoothDevice) {
        return checkIsReconnectDevice(bluetoothDevice, null);
    }

    public String getReconnectAddress() {
        ReConnectDevMsg e = e();
        if (e == null) {
            return null;
        }
        return e.getAddress();
    }

    public boolean isDeviceReconnecting() {
        return this.g.hasMessages(p);
    }

    public boolean isWaitingForUpdate() {
        return e() != null;
    }

    public void release() {
        setReConnectDevMsg(null);
        stopReconnectTask();
        this.b.unregisterBluetoothCallback(this.h);
        this.g.removeCallbacksAndMessages(null);
    }

    public void setReConnectDevMsg(ReConnectDevMsg reConnectDevMsg) {
        if (this.c != reConnectDevMsg) {
            this.c = reConnectDevMsg;
            this.f.clear();
            String str = i;
            JL_Log.d(str, "setReConnectDevMsg : " + reConnectDevMsg);
        }
    }

    public void setReconnectAddress(String str) {
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            setReConnectDevMsg(null);
        } else if (this.c == null) {
            setReConnectDevMsg(new ReConnectDevMsg(this.b.getBluetoothOption().getPriority(), str));
        } else {
            this.c.setAddress(str);
            String str2 = i;
            JL_Log.d(str2, "setReconnectAddress : " + this.c);
        }
    }

    public void setReconnectUseADV(boolean z) {
        if (this.c != null) {
            this.c.setUseADV(z);
        }
    }

    public void startReconnectTask() {
        if (isDeviceReconnecting()) {
            return;
        }
        String str = i;
        JL_Log.i(str, "-startReconnectTask- start....");
        a(c());
        d();
        JL_Log.i(str, "-startReconnectTask- timeout = " + RECONNECT_TIMEOUT);
        this.g.sendEmptyMessageDelayed(p, RECONNECT_TIMEOUT);
        this.g.sendEmptyMessage(o);
    }

    public void stopReconnectTask() {
        boolean isDeviceReconnecting = isDeviceReconnecting();
        boolean isWaitingForUpdate = isWaitingForUpdate();
        String str = i;
        JL_Log.i(str, "--> stopReconnectTask ---------> " + isDeviceReconnecting + ", isWaitingForUpdate = " + isWaitingForUpdate);
        a(0L);
        b(0L);
        setReConnectDevMsg(null);
        h();
        this.g.removeMessages(q);
        this.g.removeMessages(p);
    }

    private long c() {
        return Calendar.getInstance().getTimeInMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        b(RECONNECT_TIMEOUT - (c() - this.d));
    }

    private ReConnectDevMsg e() {
        if (this.c == null) {
            return null;
        }
        return this.c.cloneObject();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f() {
        return e() != null && e().getState() == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        BluetoothOTAManager bluetoothOTAManager = this.b;
        return (bluetoothOTAManager == null || bluetoothOTAManager.getConnectedDevice() == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        JL_Log.d(i, "-stopScan- >>>>>>stopBLEScan ");
        this.b.stopBLEScan();
        this.b.stopDeviceScan();
    }

    public boolean checkIsReconnectDevice(BluetoothDevice bluetoothDevice, byte[] bArr) {
        ReConnectDevMsg e;
        if (bluetoothDevice == null || (e = e()) == null) {
            return false;
        }
        String address = e.getAddress();
        if (BluetoothAdapter.checkBluetoothAddress(address)) {
            String str = i;
            JL_Log.i(str, "-checkIsReconnectDevice- device : " + b(bluetoothDevice));
            if (!e.isUseADV()) {
                return address.equals(bluetoothDevice.getAddress());
            }
            JL_Log.d(str, "-checkIsReconnectDevice- advertiseRawData : " + CHexConver.byte2HexStr(bArr));
            BleScanMessage parseOTAFlagFilterWithBroad = ParseDataUtil.parseOTAFlagFilterWithBroad(bArr, JL_Constant.OTA_IDENTIFY);
            if (parseOTAFlagFilterWithBroad != null) {
                JL_Log.i(str, "-checkIsReconnectDevice- " + parseOTAFlagFilterWithBroad);
                return address.equalsIgnoreCase(parseOTAFlagFilterWithBroad.getOldBleAddress());
            }
            return false;
        }
        return false;
    }

    private void b(long j2) {
        String str = i;
        JL_Log.i(str, "-setLeftTimeoutTime- >>>>>> " + j2);
        this.e = j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        int startBLEScan;
        ReConnectDevMsg e = e();
        if (e == null) {
            JL_Log.w(i, "doReconnectTask >>> reConnectDevMsg is null.");
            stopReconnectTask();
        } else if (g()) {
            String str = i;
            JL_Log.i(str, "doReconnectTask >>> device is connected. " + e);
        } else if (!BluetoothUtil.isBluetoothEnable()) {
            JL_Log.w(i, "doReconnectTask >>> Bluetooth is close.");
            this.g.sendEmptyMessageDelayed(o, 3000L);
        } else {
            if (e.getState() == 2) {
                String str2 = i;
                JL_Log.w(str2, "doReconnectTask >>> Task is connecting. " + e);
                if (this.g.hasMessages(q)) {
                    return;
                }
                this.g.sendEmptyMessageDelayed(q, 30000L);
                return;
            }
            String str3 = i;
            JL_Log.i(str3, "doReconnectTask >>> " + e + ", isDevConnected : " + g());
            BluetoothDevice b = b(e.getAddress());
            StringBuilder sb = new StringBuilder();
            sb.append("doReconnectTask >>> connectedDevice : ");
            sb.append(b(b));
            JL_Log.w(str3, sb.toString());
            if (b != null) {
                a(b);
                return;
            }
            if (e.isUseADV() && e.getWay() != 0) {
                e.setWay(0);
            }
            if (this.b.isScanning()) {
                int scanType = this.b.getScanType();
                boolean z = scanType == 2;
                if (!z) {
                    z = (e.getWay() == 1 && scanType == 1) || (e.getWay() == 0 && scanType == 0);
                }
                JL_Log.i(str3, "doReconnectTask >>> isScanOk : " + z + ", scanType = " + scanType);
                if (z) {
                    return;
                }
                h();
                SystemClock.sleep(100L);
            }
            d();
            JL_Log.d(str3, "doReconnectTask >>> leftTimeoutTime ： " + this.e + ", beginTaskTime : " + this.d);
            if (this.e < RECONNECT_TIMEOUT - 40000 && !e.isUseADV()) {
                int i2 = e.getWay() == 1 ? 0 : 2;
                long j2 = this.e;
                long j3 = j2 - 3000;
                if (j3 > 0) {
                    j2 = j3;
                }
                startBLEScan = this.b.startDeviceScan(j2, i2);
                JL_Log.i(str3, "doReconnectTask >>> startDeviceScan : " + startBLEScan + ", way = " + i2 + ", timeout = " + j2);
            } else {
                long min = Math.min(this.e, 20000L);
                if (e.getWay() == 1) {
                    startBLEScan = this.b.startDeviceScan(min, 1);
                    JL_Log.i(str3, "doReconnectTask >>> startDeviceScan : " + startBLEScan + ", scanTime = " + min);
                } else {
                    startBLEScan = this.b.startBLEScan(min);
                    JL_Log.i(str3, "doReconnectTask >>> startBLEScan : " + startBLEScan + ", scanTime = " + min);
                }
            }
            if (startBLEScan != 0) {
                this.g.sendEmptyMessageDelayed(o, 3000L);
            }
        }
    }

    private void a(long j2) {
        this.d = j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothDevice bluetoothDevice) {
        String str = i;
        JL_Log.d(str, "connectBtDevice :: " + this.c + ", " + bluetoothDevice);
        if (this.c == null || this.c.getState() == 2) {
            return;
        }
        this.c.setState(2);
        d();
        JL_Log.i(str, "connectBtDevice :: left time = " + this.e);
        if (this.e <= Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS) {
            this.g.removeMessages(p);
            this.g.sendEmptyMessageDelayed(p, 31000L);
            this.e = 0L;
            JL_Log.i(str, "connectBtDevice :: reset time >>> ");
        }
        this.g.removeMessages(q);
        this.g.sendEmptyMessageDelayed(q, 30000L);
        this.b.connectBluetoothDevice(bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BaseError baseError) {
        if (baseError == null) {
            return;
        }
        if (this.b.isOTA()) {
            this.b.errorEventCallback(baseError);
        }
        stopReconnectTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (this.e <= Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS) {
            JL_Log.i(i, "-onConnection- time not enough.");
            a(OTAError.buildError(ErrorCode.SUB_ERR_RECONNECT_FAILED, str));
            return;
        }
        JL_Log.i(i, "-onConnection- resume reconnect task.");
        this.g.sendEmptyMessage(o);
    }

    private BluetoothDevice b(String str) {
        List<BluetoothDevice> systemConnectedBtDeviceList;
        if (BluetoothAdapter.checkBluetoothAddress(str) && (systemConnectedBtDeviceList = BluetoothUtil.getSystemConnectedBtDeviceList(this.f12367a)) != null && !systemConnectedBtDeviceList.isEmpty()) {
            for (BluetoothDevice bluetoothDevice : systemConnectedBtDeviceList) {
                if (str.equals(bluetoothDevice.getAddress())) {
                    return bluetoothDevice;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(BluetoothDevice bluetoothDevice) {
        return BluetoothUtil.printBtDeviceInfo(this.f12367a, bluetoothDevice);
    }
}
