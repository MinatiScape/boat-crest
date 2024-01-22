package com.jieli.bluetooth_connect.impl;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jieli.bluetooth_connect.bean.BluetoothOption;
import com.jieli.bluetooth_connect.bean.ErrorInfo;
import com.jieli.bluetooth_connect.interfaces.IBluetoothSpp;
import com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtSppListener;
import com.jieli.bluetooth_connect.tool.ReceiveSppDataThread;
import com.jieli.bluetooth_connect.tool.SppEventCbManager;
import com.jieli.bluetooth_connect.util.BluetoothUtil;
import com.jieli.bluetooth_connect.util.CHexConverter;
import com.jieli.bluetooth_connect.util.ConnectUtil;
import com.jieli.bluetooth_connect.util.JL_Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes11.dex */
public class BluetoothSpp implements IBluetoothSpp {
    private static final int DELAY_TIME = 1000;
    private static final int MSG_CONNECT_SPP = 41014;
    private static final int MSG_CONNECT_SPP_TIMEOUT = 41013;
    private static final int MSG_WAIT_SYSTEM_CONNECT_EDR_TIMEOUT = 41015;
    private static final String TAG = "BluetoothSpp";
    private static final int WAIT_TIMEOUT = 3000;
    private final BluetoothBrEdr mBluetoothBrEdr;
    private BluetoothOption mBluetoothOption;
    private final BluetoothPair mBluetoothPair;
    private volatile BluetoothDevice mConnectedSppDevice;
    private volatile BluetoothDevice mConnectingSppDevice;
    private ConnectionSppThread mConnectionSppThread;
    private final Context mContext;
    private final OnBrEdrListener mOnBrEdrListener;
    private final OnBtDevicePairListener mOnBtDevicePairListener;
    private final ReceiveSppDataThread.OnRecvSppDataListener mOnRecvSppDataListener;
    private final SppEventCbManager mSppEventCbManager;
    private BluetoothSppReceiver mSppReceiver;
    private final List<BluetoothDevice> mConnectedSppDevices = Collections.synchronizedList(new ArrayList());
    private final Map<String, BluetoothSocket> mSppSocketMap = Collections.synchronizedMap(new HashMap());
    private final Map<String, ReceiveSppDataThread> mSppRecvThreadMap = Collections.synchronizedMap(new HashMap());
    private final ExecutorService mThreadPool = Executors.newFixedThreadPool(8);
    private final Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.jieli.bluetooth_connect.impl.BluetoothSpp.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case BluetoothSpp.MSG_CONNECT_SPP_TIMEOUT /* 41013 */:
                    String str = BluetoothSpp.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("-MSG_CONNECT_SPP_TIMEOUT-  mConnectingSppDevice : ");
                    BluetoothSpp bluetoothSpp = BluetoothSpp.this;
                    sb.append(bluetoothSpp.printfDeviceInfo(bluetoothSpp.mConnectingSppDevice));
                    JL_Log.d(str, sb.toString());
                    if (BluetoothSpp.this.mConnectingSppDevice != null) {
                        BluetoothSpp bluetoothSpp2 = BluetoothSpp.this;
                        if (bluetoothSpp2.isConnectedSppDevice(bluetoothSpp2.mConnectingSppDevice)) {
                            return true;
                        }
                        BluetoothSpp bluetoothSpp3 = BluetoothSpp.this;
                        bluetoothSpp3.notifySppState(bluetoothSpp3.mConnectingSppDevice, 0);
                        return true;
                    }
                    return true;
                case BluetoothSpp.MSG_CONNECT_SPP /* 41014 */:
                    String str2 = BluetoothSpp.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("-MSG_CONNECT_SPP-  device : ");
                    BluetoothSpp bluetoothSpp4 = BluetoothSpp.this;
                    sb2.append(bluetoothSpp4.printfDeviceInfo(bluetoothSpp4.mConnectingSppDevice));
                    JL_Log.d(str2, sb2.toString());
                    if (BluetoothSpp.this.mConnectingSppDevice != null) {
                        BluetoothSpp bluetoothSpp5 = BluetoothSpp.this;
                        bluetoothSpp5.startConnectSpp(bluetoothSpp5.mConnectingSppDevice);
                        return true;
                    }
                    return true;
                case BluetoothSpp.MSG_WAIT_SYSTEM_CONNECT_EDR_TIMEOUT /* 41015 */:
                    String str3 = BluetoothSpp.TAG;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("-MSG_WAIT_SYSTEM_CONNECT_EDR_TIMEOUT-  device : ");
                    BluetoothSpp bluetoothSpp6 = BluetoothSpp.this;
                    sb3.append(bluetoothSpp6.printfDeviceInfo(bluetoothSpp6.mConnectingSppDevice));
                    JL_Log.d(str3, sb3.toString());
                    if (BluetoothSpp.this.mConnectingSppDevice != null) {
                        BluetoothSpp.this.mHandler.sendEmptyMessage(BluetoothSpp.MSG_CONNECT_SPP);
                        return true;
                    }
                    return true;
                default:
                    return true;
            }
        }
    });

    /* loaded from: classes11.dex */
    public class BluetoothSppReceiver extends BroadcastReceiver {
        private BluetoothSppReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice;
            if (intent == null) {
                return;
            }
            String action = intent.getAction();
            if (TextUtils.isEmpty(action) || !"android.bluetooth.device.action.UUID".equals(action) || (bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")) == null || !ConnectUtil.isHasConnectPermission(context)) {
                return;
            }
            Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("android.bluetooth.device.extra.UUID");
            if (parcelableArrayExtra == null) {
                BluetoothSpp.this.handleDeviceUuids(bluetoothDevice, null);
                JL_Log.i(BluetoothSpp.TAG, "-BluetoothSppReceiver- onReceive: ACTION_UUID no uuids");
                return;
            }
            ParcelUuid[] parcelUuidArr = new ParcelUuid[parcelableArrayExtra.length];
            for (int i = 0; i < parcelableArrayExtra.length; i++) {
                parcelUuidArr[i] = ParcelUuid.fromString(parcelableArrayExtra[i].toString());
                String str = BluetoothSpp.TAG;
                JL_Log.i(str, "-BluetoothSppReceiver- onReceive: ACTION_UUID " + parcelUuidArr[i].toString());
            }
            BluetoothSpp.this.handleDeviceUuids(bluetoothDevice, parcelUuidArr);
        }
    }

    /* loaded from: classes11.dex */
    public class ConnectionSppThread extends Thread {
        private final BluetoothDevice mDevice;

        /* JADX WARN: Removed duplicated region for block: B:20:0x00ba A[Catch: all -> 0x015d, TRY_LEAVE, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x0038, B:7:0x0044, B:9:0x005d, B:11:0x0080, B:12:0x0090, B:20:0x00ba, B:23:0x00e8, B:25:0x0110, B:26:0x0117, B:28:0x0125, B:29:0x0146, B:18:0x0099, B:30:0x014d), top: B:40:0x0001 }] */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00e8 A[Catch: all -> 0x015d, TRY_ENTER, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x0038, B:7:0x0044, B:9:0x005d, B:11:0x0080, B:12:0x0090, B:20:0x00ba, B:23:0x00e8, B:25:0x0110, B:26:0x0117, B:28:0x0125, B:29:0x0146, B:18:0x0099, B:30:0x014d), top: B:40:0x0001 }] */
        @Override // java.lang.Thread, java.lang.Runnable
        @android.annotation.SuppressLint({"MissingPermission"})
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public synchronized void run() {
            /*
                Method dump skipped, instructions count: 352
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jieli.bluetooth_connect.impl.BluetoothSpp.ConnectionSppThread.run():void");
        }

        private ConnectionSppThread(BluetoothDevice bluetoothDevice) {
            super("ConnectionThread");
            this.mDevice = bluetoothDevice;
        }
    }

    public BluetoothSpp(Context context, BluetoothBrEdr bluetoothBrEdr, BluetoothOption bluetoothOption, OnBtSppListener onBtSppListener) {
        OnBtDevicePairListener onBtDevicePairListener = new OnBtDevicePairListener() { // from class: com.jieli.bluetooth_connect.impl.BluetoothSpp.2
            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener
            public void onAdapterStatus(boolean z, boolean z2) {
                if (z) {
                    return;
                }
                BluetoothSpp.this.clearDevices();
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener
            public void onBtDeviceBond(BluetoothDevice bluetoothDevice, int i) {
                if (BluetoothUtil.deviceEquals(bluetoothDevice, BluetoothSpp.this.mConnectingSppDevice)) {
                    String str = BluetoothSpp.TAG;
                    JL_Log.w(str, "spp >> -onBtDeviceBond- device : " + BluetoothSpp.this.printfDeviceInfo(bluetoothDevice) + ", status : " + i);
                    if (i != 12) {
                        if (i == 10) {
                            BluetoothSpp.this.notifySppState(bluetoothDevice, 0);
                            return;
                        }
                        return;
                    }
                    BluetoothSpp.this.mHandler.removeMessages(BluetoothSpp.MSG_CONNECT_SPP_TIMEOUT);
                    BluetoothSpp.this.mHandler.sendEmptyMessageDelayed(BluetoothSpp.MSG_CONNECT_SPP_TIMEOUT, 30000L);
                    if (BluetoothUtil.deviceHasA2dp(BluetoothSpp.this.mContext, bluetoothDevice) || BluetoothUtil.deviceHasHfp(BluetoothSpp.this.mContext, bluetoothDevice)) {
                        JL_Log.d(BluetoothSpp.TAG, "-onBtDeviceBond- Waiting for a2dp or hfp connect.");
                        BluetoothSpp.this.mHandler.removeMessages(BluetoothSpp.MSG_WAIT_SYSTEM_CONNECT_EDR_TIMEOUT);
                        BluetoothSpp.this.mHandler.sendEmptyMessageDelayed(BluetoothSpp.MSG_WAIT_SYSTEM_CONNECT_EDR_TIMEOUT, 3000L);
                        return;
                    }
                    JL_Log.i(BluetoothSpp.TAG, "-onBtDeviceBond- device has not a2dp and hfp.");
                    BluetoothSpp.this.mHandler.sendEmptyMessage(BluetoothSpp.MSG_CONNECT_SPP);
                }
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener
            public void onPairError(BluetoothDevice bluetoothDevice, ErrorInfo errorInfo) {
                if (BluetoothUtil.deviceEquals(bluetoothDevice, BluetoothSpp.this.mConnectingSppDevice)) {
                    BluetoothSpp.this.notifySppState(bluetoothDevice, 0);
                }
            }
        };
        this.mOnBtDevicePairListener = onBtDevicePairListener;
        OnBrEdrListener onBrEdrListener = new OnBrEdrListener() { // from class: com.jieli.bluetooth_connect.impl.BluetoothSpp.3
            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener
            public void onA2dpStatus(BluetoothDevice bluetoothDevice, int i) {
                BluetoothSpp.this.checkNeedConnect(bluetoothDevice, i);
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener
            public void onBrEdrConnection(BluetoothDevice bluetoothDevice, int i) {
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener
            public void onDeviceUuids(BluetoothDevice bluetoothDevice, ParcelUuid[] parcelUuidArr) {
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener
            public void onEdrService(boolean z, int i, BluetoothProfile bluetoothProfile) {
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener
            public void onHfpStatus(BluetoothDevice bluetoothDevice, int i) {
                BluetoothSpp.this.checkNeedConnect(bluetoothDevice, i);
            }
        };
        this.mOnBrEdrListener = onBrEdrListener;
        this.mOnRecvSppDataListener = new ReceiveSppDataThread.OnRecvSppDataListener() { // from class: com.jieli.bluetooth_connect.impl.BluetoothSpp.4
            @Override // com.jieli.bluetooth_connect.tool.ReceiveSppDataThread.OnRecvSppDataListener
            public void onRecvSppData(long j, BluetoothDevice bluetoothDevice, byte[] bArr) {
                JL_Log.d(BluetoothSpp.TAG, String.format(Locale.getDefault(), "-onRecvSppData- data [ %s ], device : %s.", CHexConverter.byte2HexStr(bArr), bluetoothDevice));
                BluetoothSpp.this.mSppEventCbManager.onSppDataNotify(bluetoothDevice, BluetoothSpp.this.mBluetoothOption.getSppUUID(), bArr);
            }

            @Override // com.jieli.bluetooth_connect.tool.ReceiveSppDataThread.OnRecvSppDataListener
            public void onThreadStart(long j) {
            }

            @Override // com.jieli.bluetooth_connect.tool.ReceiveSppDataThread.OnRecvSppDataListener
            public void onThreadStop(long j, int i, BluetoothDevice bluetoothDevice) {
                String str = BluetoothSpp.TAG;
                JL_Log.w(str, "-onThreadStop- >> reason : " + i);
                BluetoothSpp.this.disconnectSPPDevice(bluetoothDevice);
            }
        };
        this.mContext = (Context) ConnectUtil.checkNotNull(context);
        BluetoothBrEdr bluetoothBrEdr2 = (BluetoothBrEdr) ConnectUtil.checkNotNull(bluetoothBrEdr);
        this.mBluetoothBrEdr = bluetoothBrEdr2;
        BluetoothPair bluetoothPair = bluetoothBrEdr.getBluetoothPair();
        this.mBluetoothPair = bluetoothPair;
        bluetoothBrEdr2.addListener(onBrEdrListener);
        bluetoothPair.addListener(onBtDevicePairListener);
        this.mSppEventCbManager = new SppEventCbManager();
        this.mBluetoothOption = bluetoothOption == null ? BluetoothOption.createDefaultOption() : bluetoothOption;
        addListener(onBtSppListener);
        registerSppReceiver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkNeedConnect(BluetoothDevice bluetoothDevice, int i) {
        if (BluetoothUtil.deviceEquals(bluetoothDevice, this.mConnectingSppDevice)) {
            if (i != 1) {
                JL_Log.d(TAG, "checkNeedConnect : a2dp or hfp connect finish.");
                this.mHandler.removeMessages(MSG_CONNECT_SPP);
                this.mHandler.sendEmptyMessageDelayed(MSG_CONNECT_SPP, 1000L);
                return;
            }
            this.mHandler.removeMessages(MSG_WAIT_SYSTEM_CONNECT_EDR_TIMEOUT);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDevices() {
        JL_Log.d(TAG, "clearDevices >>>>");
        Iterator it = new ArrayList(this.mConnectedSppDevices).iterator();
        while (it.hasNext()) {
            disconnectSpp((BluetoothDevice) it.next());
        }
        if (!this.mSppSocketMap.isEmpty()) {
            for (String str : this.mSppSocketMap.keySet()) {
                BluetoothSocket bluetoothSocket = this.mSppSocketMap.get(str);
                if (bluetoothSocket != null && bluetoothSocket.isConnected()) {
                    try {
                        bluetoothSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        this.mSppSocketMap.clear();
        this.mSppRecvThreadMap.clear();
        this.mConnectedSppDevices.clear();
        setConnectingSppDevice(null);
        setConnectedSppDevice(null);
    }

    private boolean disconnectSpp(BluetoothDevice bluetoothDevice) {
        String str = TAG;
        JL_Log.i(str, "-disconnectSpp- device : " + printfDeviceInfo(bluetoothDevice));
        if (bluetoothDevice != null && ConnectUtil.isHasConnectPermission(this.mContext)) {
            if (!this.mConnectedSppDevices.contains(bluetoothDevice)) {
                JL_Log.i(str, "-disconnectSpp- device is not connected device.");
                return true;
            }
            BluetoothSocket remove = this.mSppSocketMap.remove(bluetoothDevice.getAddress());
            if (remove != null && remove.isConnected()) {
                try {
                    remove.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            ReceiveSppDataThread remove2 = this.mSppRecvThreadMap.remove(bluetoothDevice.getAddress());
            if (remove2 != null) {
                remove2.stopThread();
            }
            this.mConnectedSppDevices.remove(bluetoothDevice);
            JL_Log.i(TAG, "-disconnectSpp- remove connected device ok.");
            return true;
        }
        JL_Log.w(str, "-disconnectSpp- param is error.");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDeviceUuids(BluetoothDevice bluetoothDevice, ParcelUuid[] parcelUuidArr) {
        if (BluetoothUtil.deviceEquals(bluetoothDevice, this.mConnectingSppDevice)) {
            JL_Log.d(TAG, "handleDeviceUuids : get uuid success.");
            this.mHandler.sendEmptyMessage(MSG_CONNECT_SPP);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySppState(BluetoothDevice bluetoothDevice, int i) {
        notifySppState(bluetoothDevice, this.mBluetoothOption.getSppUUID(), i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String printfDeviceInfo(BluetoothDevice bluetoothDevice) {
        return BluetoothUtil.printBtDeviceInfo(this.mContext, bluetoothDevice);
    }

    private void registerSppReceiver() {
        if (this.mSppReceiver == null) {
            this.mSppReceiver = new BluetoothSppReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.device.action.UUID");
            this.mContext.registerReceiver(this.mSppReceiver, intentFilter);
        }
    }

    private void removeDevice(BluetoothDevice bluetoothDevice) {
        if (this.mConnectedSppDevices.isEmpty()) {
            setConnectedSppDevice(null);
        } else if (BluetoothUtil.deviceEquals(bluetoothDevice, this.mConnectedSppDevice)) {
            List<BluetoothDevice> list = this.mConnectedSppDevices;
            setConnectedSppDevice(list.get(list.size() - 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startConnectSpp(BluetoothDevice bluetoothDevice) {
        String str = TAG;
        JL_Log.i(str, "-startConnectSpp- device : " + printfDeviceInfo(bluetoothDevice) + ", mConnectionThread : " + this.mConnectionSppThread);
        if (bluetoothDevice == null || this.mConnectionSppThread != null) {
            return;
        }
        ConnectionSppThread connectionSppThread = new ConnectionSppThread(bluetoothDevice);
        this.mConnectionSppThread = connectionSppThread;
        connectionSppThread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startReceiveDataThread(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket) {
        String str = TAG;
        JL_Log.e(str, "-startReceiveDataThread- socket : " + bluetoothSocket);
        ReceiveSppDataThread receiveSppDataThread = new ReceiveSppDataThread(bluetoothDevice, bluetoothSocket, this.mOnRecvSppDataListener);
        if (this.mThreadPool.isShutdown()) {
            return;
        }
        this.mThreadPool.submit(receiveSppDataThread);
        this.mSppRecvThreadMap.put(bluetoothDevice.getAddress(), receiveSppDataThread);
    }

    private void stopConnectSpp() {
        ConnectionSppThread connectionSppThread = this.mConnectionSppThread;
        if (connectionSppThread != null) {
            try {
                if (connectionSppThread.isAlive()) {
                    this.mConnectionSppThread.interrupt();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.mConnectionSppThread = null;
        }
    }

    private void unregisterSppReceiver() {
        BluetoothSppReceiver bluetoothSppReceiver = this.mSppReceiver;
        if (bluetoothSppReceiver != null) {
            this.mContext.unregisterReceiver(bluetoothSppReceiver);
            this.mSppReceiver = null;
        }
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothSpp
    @SuppressLint({"MissingPermission"})
    public boolean connectSPPDevice(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null && ConnectUtil.isHasConnectPermission(this.mContext) && bluetoothDevice.getType() != 2) {
            String str = TAG;
            JL_Log.i(str, "-connectSPPDevice- device : " + printfDeviceInfo(bluetoothDevice));
            if (this.mConnectingSppDevice != null) {
                JL_Log.i(str, "-connectSPPDevice- ConnectingSppDevice is connecting. ConnectingSppDevice : " + printfDeviceInfo(this.mConnectedSppDevice));
                return false;
            } else if (isConnectedSppDevice(bluetoothDevice)) {
                notifySppState(bluetoothDevice, 2);
                return true;
            } else {
                if (!this.mBluetoothOption.isUseMultiDevice() && this.mConnectedSppDevice != null && !BluetoothUtil.deviceEquals(this.mConnectedSppDevice, bluetoothDevice)) {
                    disconnectSPPDevice(this.mConnectedSppDevice);
                    SystemClock.sleep(300L);
                }
                setConnectingSppDevice(bluetoothDevice);
                boolean isPaired = this.mBluetoothPair.isPaired(bluetoothDevice);
                JL_Log.i(str, "-connectSPPDevice- isPaired : " + isPaired);
                if (!isPaired) {
                    boolean tryToPair = this.mBluetoothPair.tryToPair(bluetoothDevice);
                    JL_Log.i(str, "-connectSPPDevice- pair ret : " + tryToPair);
                    if (!tryToPair) {
                        notifySppState(bluetoothDevice, 0);
                        JL_Log.w(str, "-connectSPPDevice- tryToPair is failed.");
                        return false;
                    }
                } else if (bluetoothDevice.getUuids() != null && BluetoothUtil.deviceHasProfile(this.mContext, bluetoothDevice, this.mBluetoothOption.getSppUUID())) {
                    JL_Log.i(str, "-connectSPPDevice- start connect spp.");
                    this.mHandler.sendEmptyMessage(MSG_CONNECT_SPP);
                } else if (!bluetoothDevice.fetchUuidsWithSdp()) {
                    notifySppState(bluetoothDevice, 0);
                    JL_Log.w(str, "-connectSPPDevice- fetchUuidsWithSdp is failed.");
                    return false;
                }
                notifySppState(bluetoothDevice, 1);
                this.mHandler.removeMessages(MSG_CONNECT_SPP_TIMEOUT);
                this.mHandler.sendEmptyMessageDelayed(MSG_CONNECT_SPP_TIMEOUT, 40000L);
                return true;
            }
        }
        JL_Log.i(TAG, "-connectSPPDevice-  device is bad object. ");
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void destroy() {
        JL_Log.d(TAG, "destroy >>>>");
        this.mSppEventCbManager.destroy();
        this.mBluetoothPair.removeListener(this.mOnBtDevicePairListener);
        this.mBluetoothBrEdr.removeListener(this.mOnBrEdrListener);
        stopConnectSpp();
        clearDevices();
        if (!this.mThreadPool.isShutdown()) {
            this.mThreadPool.shutdownNow();
        }
        unregisterSppReceiver();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothSpp
    public boolean disconnectSPPDevice(BluetoothDevice bluetoothDevice) {
        String str = TAG;
        JL_Log.i(str, "-disconnectSPPDevice- device : " + printfDeviceInfo(bluetoothDevice));
        boolean disconnectSpp = disconnectSpp(bluetoothDevice);
        if (disconnectSpp) {
            notifySppState(bluetoothDevice, 0);
        }
        return disconnectSpp;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothSpp
    public BluetoothDevice getConnectedSPPDevice() {
        return this.mConnectedSppDevice;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothSpp
    public List<BluetoothDevice> getConnectedSppDevices() {
        return new ArrayList(this.mConnectedSppDevices);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothSpp
    public BluetoothDevice getSppConnectingDevice() {
        return this.mConnectingSppDevice;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothSpp
    public boolean isConnectedSppDevice(BluetoothDevice bluetoothDevice) {
        BluetoothSocket bluetoothSocket;
        if (bluetoothDevice == null || (bluetoothSocket = this.mSppSocketMap.get(bluetoothDevice.getAddress())) == null) {
            return false;
        }
        return bluetoothSocket.isConnected();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothSpp
    public boolean isSppConnecting() {
        return this.mConnectingSppDevice != null;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void setBluetoothOption(BluetoothOption bluetoothOption) {
        if (bluetoothOption != null) {
            this.mBluetoothOption = bluetoothOption;
        }
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothSpp
    public void setConnectedSppDevice(BluetoothDevice bluetoothDevice) {
        if (BluetoothUtil.deviceEquals(this.mConnectedSppDevice, bluetoothDevice)) {
            return;
        }
        this.mConnectedSppDevice = bluetoothDevice;
        if (bluetoothDevice != null) {
            this.mSppEventCbManager.onSwitchSppDevice(bluetoothDevice);
        }
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothSpp
    public void setConnectingSppDevice(BluetoothDevice bluetoothDevice) {
        this.mConnectingSppDevice = bluetoothDevice;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothSpp
    public synchronized boolean writeDataToSppDevice(BluetoothDevice bluetoothDevice, byte[] bArr) {
        if (bArr != null && bluetoothDevice != null) {
            if (!isConnectedSppDevice(bluetoothDevice)) {
                JL_Log.w(TAG, "-writeDataToSppDevice- device is disconnected.");
                return false;
            }
            BluetoothSocket bluetoothSocket = this.mSppSocketMap.get(bluetoothDevice.getAddress());
            if (bluetoothSocket != null && bluetoothSocket.isConnected()) {
                try {
                    bluetoothSocket.getOutputStream().write(bArr);
                    JL_Log.i(TAG, String.format(Locale.getDefault(), "-writeDataToSppDevice- send ret is OK, data [ %s ], device : %s.", CHexConverter.byte2HexStr(bArr), bluetoothDevice));
                    return true;
                } catch (Exception e) {
                    String str = TAG;
                    JL_Log.w(str, "-writeDataToSppDevice- have an exception : " + e);
                    e.printStackTrace();
                    return false;
                }
            }
            JL_Log.w(TAG, "-writeDataToSppDevice- spp socket is close.");
            return false;
        }
        JL_Log.w(TAG, "-writeDataToSppDevice- param is error.");
        return false;
    }

    private void notifySppState(BluetoothDevice bluetoothDevice, UUID uuid, int i) {
        String str = TAG;
        JL_Log.i(str, "-notifySppState- status ： " + i + ", device : " + printfDeviceInfo(bluetoothDevice));
        if (i != 1) {
            if (BluetoothUtil.deviceEquals(this.mConnectingSppDevice, bluetoothDevice)) {
                setConnectingSppDevice(null);
                this.mHandler.removeMessages(MSG_CONNECT_SPP_TIMEOUT);
            }
            if (i == 0) {
                removeDevice(bluetoothDevice);
            }
        }
        this.mSppEventCbManager.onSppConnection(bluetoothDevice, uuid, i);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void addListener(OnBtSppListener onBtSppListener) {
        this.mSppEventCbManager.addListener(onBtSppListener);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void removeListener(OnBtSppListener onBtSppListener) {
        this.mSppEventCbManager.removeListener(onBtSppListener);
    }
}
