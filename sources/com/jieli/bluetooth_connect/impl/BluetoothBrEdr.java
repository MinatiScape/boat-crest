package com.jieli.bluetooth_connect.impl;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jieli.bluetooth_connect.bean.BluetoothOption;
import com.jieli.bluetooth_connect.bean.ErrorInfo;
import com.jieli.bluetooth_connect.interfaces.IBluetoothBrEdr;
import com.jieli.bluetooth_connect.interfaces.listener.OnBrEdrListener;
import com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener;
import com.jieli.bluetooth_connect.tool.BrEdrEventCbManager;
import com.jieli.bluetooth_connect.util.BluetoothUtil;
import com.jieli.bluetooth_connect.util.ConnectUtil;
import com.jieli.bluetooth_connect.util.JL_Log;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public class BluetoothBrEdr implements IBluetoothBrEdr {
    private static final int MSG_CONNECT_EDR_TIMEOUT = 26145;
    private static final String TAG = "BluetoothBrEdr";
    private volatile boolean isInitA2dp;
    private volatile boolean isInitHfp;
    private BluetoothA2dp mBluetoothA2dp;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothHandFreeReceiver mBluetoothHandFreeReceiver;
    private BluetoothHeadset mBluetoothHfp;
    private final BluetoothPair mBluetoothPair;
    private final BrEdrEventCbManager mBrEdrEventCbManager;
    private volatile BluetoothDevice mConnectingEdr;
    private final Context mContext;
    private final OnBtDevicePairListener mOnBtDevicePairListener;
    private final Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.jieli.bluetooth_connect.impl.BluetoothBrEdr.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            if (message.what == BluetoothBrEdr.MSG_CONNECT_EDR_TIMEOUT) {
                String str = BluetoothBrEdr.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("MSG_CONNECT_EDR_TIMEOUT , connectingEdr : ");
                BluetoothBrEdr bluetoothBrEdr = BluetoothBrEdr.this;
                sb.append(bluetoothBrEdr.printDeviceInfo(bluetoothBrEdr.mConnectingEdr));
                JL_Log.i(str, sb.toString());
                if (BluetoothBrEdr.this.mConnectingEdr != null) {
                    BluetoothBrEdr bluetoothBrEdr2 = BluetoothBrEdr.this;
                    if (bluetoothBrEdr2.isConnectedByProfile(bluetoothBrEdr2.mConnectingEdr) != 2) {
                        BluetoothBrEdr bluetoothBrEdr3 = BluetoothBrEdr.this;
                        bluetoothBrEdr3.onBrEdrConnection(bluetoothBrEdr3.mConnectingEdr, 0);
                    }
                    BluetoothBrEdr.this.setConnectingEdr(null);
                }
            }
            return false;
        }
    });
    private final BluetoothProfile.ServiceListener mBTServiceListener = new BluetoothProfile.ServiceListener() { // from class: com.jieli.bluetooth_connect.impl.BluetoothBrEdr.2
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            String str = BluetoothBrEdr.TAG;
            JL_Log.i(str, "------------onServiceConnected--------profile = " + i);
            if (2 == i) {
                BluetoothBrEdr.this.mBluetoothA2dp = (BluetoothA2dp) bluetoothProfile;
                BluetoothBrEdr.this.isInitA2dp = false;
            } else if (1 == i) {
                BluetoothBrEdr.this.mBluetoothHfp = (BluetoothHeadset) bluetoothProfile;
                BluetoothBrEdr.this.isInitHfp = false;
            }
            BluetoothBrEdr.this.mBrEdrEventCbManager.onEdrService(true, i, bluetoothProfile);
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            JL_Log.i(BluetoothBrEdr.TAG, "------------onServiceDisconnected--------");
            if (2 == i) {
                BluetoothBrEdr.this.mBluetoothA2dp = null;
                BluetoothBrEdr.this.isInitA2dp = false;
            } else if (1 == i) {
                BluetoothBrEdr.this.mBluetoothHfp = null;
                BluetoothBrEdr.this.isInitHfp = false;
            }
            BluetoothBrEdr.this.mBrEdrEventCbManager.onEdrService(false, i, null);
        }
    };

    /* loaded from: classes11.dex */
    public class BluetoothHandFreeReceiver extends BroadcastReceiver {
        private BluetoothHandFreeReceiver() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            char c;
            if (intent != null) {
                String action = intent.getAction();
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (TextUtils.isEmpty(action) || bluetoothDevice == null) {
                    return;
                }
                action.hashCode();
                switch (action.hashCode()) {
                    case -855499628:
                        if (action.equals("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -377527494:
                        if (action.equals("android.bluetooth.device.action.UUID")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 545516589:
                        if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1244161670:
                        if (action.equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
                            c = 3;
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
                        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 11);
                        JL_Log.i(BluetoothBrEdr.TAG, "A2DP  ACTION_PLAYING_STATE_CHANGED : " + intExtra);
                        return;
                    case 1:
                        Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("android.bluetooth.device.extra.UUID");
                        if (parcelableArrayExtra == null) {
                            BluetoothBrEdr.this.onDeviceUuids(bluetoothDevice, null);
                            JL_Log.i(BluetoothBrEdr.TAG, "onReceive: ACTION_UUID no uuids");
                            return;
                        }
                        ParcelUuid[] parcelUuidArr = new ParcelUuid[parcelableArrayExtra.length];
                        for (int i = 0; i < parcelableArrayExtra.length; i++) {
                            parcelUuidArr[i] = ParcelUuid.fromString(parcelableArrayExtra[i].toString());
                            JL_Log.i(BluetoothBrEdr.TAG, "onReceive: ACTION_UUID " + parcelUuidArr[i].toString());
                        }
                        BluetoothBrEdr.this.onDeviceUuids(bluetoothDevice, parcelUuidArr);
                        return;
                    case 2:
                        try {
                            int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                            JL_Log.i(BluetoothBrEdr.TAG, "onReceive: hfp ACTION_CONNECTION_STATE_CHANGED device : " + BluetoothBrEdr.this.printDeviceInfo(bluetoothDevice) + ", state : " + intExtra2);
                            BluetoothBrEdr.this.onHfpStatus(bluetoothDevice, intExtra2);
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    case 3:
                        try {
                            int intExtra3 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                            JL_Log.i(BluetoothBrEdr.TAG, "onReceive: a2dp ACTION_CONNECTION_STATE_CHANGED device : " + BluetoothBrEdr.this.printDeviceInfo(bluetoothDevice) + ", state : " + intExtra3);
                            if (intExtra3 == -1) {
                                return;
                            }
                            BluetoothBrEdr.this.onA2dpStatus(bluetoothDevice, intExtra3);
                            return;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return;
                        }
                    default:
                        return;
                }
            }
        }
    }

    public BluetoothBrEdr(Context context, BluetoothPair bluetoothPair, OnBrEdrListener onBrEdrListener) {
        OnBtDevicePairListener onBtDevicePairListener = new OnBtDevicePairListener() { // from class: com.jieli.bluetooth_connect.impl.BluetoothBrEdr.3
            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener
            public void onAdapterStatus(boolean z, boolean z2) {
                if (z || BluetoothBrEdr.this.mConnectingEdr == null) {
                    return;
                }
                BluetoothBrEdr.this.mHandler.removeMessages(BluetoothBrEdr.MSG_CONNECT_EDR_TIMEOUT);
                BluetoothBrEdr.this.mHandler.sendEmptyMessage(BluetoothBrEdr.MSG_CONNECT_EDR_TIMEOUT);
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener
            @SuppressLint({"MissingPermission"})
            public void onBtDeviceBond(BluetoothDevice bluetoothDevice, int i) {
                if (BluetoothUtil.deviceEquals(bluetoothDevice, BluetoothBrEdr.this.mConnectingEdr)) {
                    JL_Log.i(BluetoothBrEdr.TAG, String.format(Locale.getDefault(), "-onBtDeviceBond- device : [%s], status : %d", BluetoothBrEdr.this.printDeviceInfo(bluetoothDevice), Integer.valueOf(i)));
                    if (i == 10) {
                        BluetoothBrEdr.this.onBrEdrConnection(bluetoothDevice, 0);
                    } else if (i == 12) {
                        if (ConnectUtil.isHasConnectPermission(BluetoothBrEdr.this.mContext) && bluetoothDevice.getType() != 1) {
                            BluetoothBrEdr.this.tryToConnectBrEdr(bluetoothDevice);
                        }
                        BluetoothBrEdr.this.mHandler.removeMessages(BluetoothBrEdr.MSG_CONNECT_EDR_TIMEOUT);
                        BluetoothBrEdr.this.mHandler.sendEmptyMessageDelayed(BluetoothBrEdr.MSG_CONNECT_EDR_TIMEOUT, 30000L);
                    }
                }
            }

            @Override // com.jieli.bluetooth_connect.interfaces.listener.OnBtDevicePairListener
            public void onPairError(BluetoothDevice bluetoothDevice, ErrorInfo errorInfo) {
                if (BluetoothUtil.deviceEquals(bluetoothDevice, BluetoothBrEdr.this.mConnectingEdr)) {
                    JL_Log.w(BluetoothBrEdr.TAG, String.format(Locale.getDefault(), "-onPairError- device : [%s], error : %s", BluetoothBrEdr.this.printDeviceInfo(bluetoothDevice), errorInfo));
                    BluetoothBrEdr.this.onBrEdrConnection(bluetoothDevice, 0);
                }
            }
        };
        this.mOnBtDevicePairListener = onBtDevicePairListener;
        this.mContext = (Context) ConnectUtil.checkNotNull(context);
        BluetoothPair bluetoothPair2 = (BluetoothPair) ConnectUtil.checkNotNull(bluetoothPair);
        this.mBluetoothPair = bluetoothPair2;
        bluetoothPair2.addListener(onBtDevicePairListener);
        this.mBrEdrEventCbManager = new BrEdrEventCbManager();
        addListener(onBrEdrListener);
        initBrEdrService(context);
        registerReceiver();
    }

    private void initBrEdrService(Context context) {
        if (context == null) {
            return;
        }
        if (this.mBluetoothAdapter == null) {
            this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        if (this.mBluetoothAdapter == null) {
            JL_Log.e(TAG, "get bluetooth adapter is null.");
            return;
        }
        if (this.mBluetoothA2dp == null && !this.isInitA2dp) {
            try {
                this.isInitA2dp = this.mBluetoothAdapter.getProfileProxy(context, this.mBTServiceListener, 2);
                if (!this.isInitA2dp) {
                    JL_Log.e(TAG, "BluetoothBreProfiles: a2dp error.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.mBluetoothHfp != null || this.isInitHfp) {
            return;
        }
        try {
            this.isInitHfp = this.mBluetoothAdapter.getProfileProxy(context, this.mBTServiceListener, 1);
            if (this.isInitHfp) {
                return;
            }
            JL_Log.e(TAG, "BluetoothBreProfiles: hfp error");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onA2dpStatus(BluetoothDevice bluetoothDevice, int i) {
        String str = TAG;
        JL_Log.d(str, String.format(Locale.getDefault(), "-onA2dpStatus- device : [%s], status : %d", printDeviceInfo(bluetoothDevice), Integer.valueOf(i)));
        this.mBrEdrEventCbManager.onA2dpStatus(bluetoothDevice, i);
        if (i == 0) {
            onBrEdrConnection(bluetoothDevice, 0);
        } else if (i == 2) {
            onBrEdrConnection(bluetoothDevice, 2);
            int isConnectedByHfp = isConnectedByHfp(bluetoothDevice);
            JL_Log.i(str, "-onA2dpStatus- a2dp is connected, hfp status = " + isConnectedByHfp);
            if (isConnectedByHfp == 0) {
                connectByProfiles(bluetoothDevice);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBrEdrConnection(BluetoothDevice bluetoothDevice, int i) {
        JL_Log.d(TAG, String.format(Locale.getDefault(), "-onBrEdrConnection- device : [%s], status : %d", printDeviceInfo(bluetoothDevice), Integer.valueOf(i)));
        if (i != 1 && (this.mConnectingEdr == null || bluetoothDevice == null || BluetoothUtil.deviceEquals(bluetoothDevice, this.mConnectingEdr))) {
            setConnectingEdr(null);
            this.mHandler.removeMessages(MSG_CONNECT_EDR_TIMEOUT);
        }
        this.mBrEdrEventCbManager.onBrEdrConnection(bluetoothDevice, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDeviceUuids(BluetoothDevice bluetoothDevice, ParcelUuid[] parcelUuidArr) {
        this.mBrEdrEventCbManager.onDeviceUuids(bluetoothDevice, parcelUuidArr);
        if (!BluetoothUtil.deviceEquals(bluetoothDevice, this.mConnectingEdr) || connectByProfiles(bluetoothDevice)) {
            return;
        }
        onBrEdrConnection(bluetoothDevice, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHfpStatus(BluetoothDevice bluetoothDevice, int i) {
        String str = TAG;
        JL_Log.d(str, String.format(Locale.getDefault(), "-onHfpStatus- device : [%s], status : %d", printDeviceInfo(bluetoothDevice), Integer.valueOf(i)));
        this.mBrEdrEventCbManager.onHfpStatus(bluetoothDevice, i);
        if (i == 0) {
            onBrEdrConnection(bluetoothDevice, 0);
        } else if (i == 2) {
            int isConnectedByA2dp = isConnectedByA2dp(bluetoothDevice);
            JL_Log.i(str, "-onHfpStatus- hfp is connected, a2dp status = " + isConnectedByA2dp);
            if (isConnectedByA2dp == 2) {
                onBrEdrConnection(bluetoothDevice, 2);
            } else if (isConnectedByA2dp == 0) {
                boolean deviceHasA2dp = BluetoothUtil.deviceHasA2dp(this.mContext, bluetoothDevice);
                JL_Log.i(str, "-onHfpStatus- devHasA2dp = " + deviceHasA2dp);
                if (deviceHasA2dp) {
                    if (connectByProfiles(bluetoothDevice)) {
                        return;
                    }
                    onBrEdrConnection(bluetoothDevice, 0);
                    return;
                }
                onBrEdrConnection(bluetoothDevice, 2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String printDeviceInfo(BluetoothDevice bluetoothDevice) {
        return BluetoothUtil.printBtDeviceInfo(this.mContext, bluetoothDevice);
    }

    private void registerReceiver() {
        if (this.mBluetoothHandFreeReceiver == null) {
            this.mBluetoothHandFreeReceiver = new BluetoothHandFreeReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.device.action.UUID");
            intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
            this.mContext.registerReceiver(this.mBluetoothHandFreeReceiver, intentFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConnectingEdr(BluetoothDevice bluetoothDevice) {
        this.mConnectingEdr = bluetoothDevice;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"MissingPermission"})
    public boolean tryToConnectBrEdr(BluetoothDevice bluetoothDevice) {
        boolean fetchUuidsWithSdp;
        String str;
        if (bluetoothDevice == null) {
            return false;
        }
        if (bluetoothDevice.getUuids() != null && bluetoothDevice.getUuids().length != 0 && (BluetoothUtil.deviceHasA2dp(this.mContext, bluetoothDevice) || BluetoothUtil.deviceHasHfp(this.mContext, bluetoothDevice))) {
            fetchUuidsWithSdp = connectByProfiles(bluetoothDevice);
            str = "connectByProfiles";
        } else {
            fetchUuidsWithSdp = bluetoothDevice.fetchUuidsWithSdp();
            str = "fetchUuidsWithSdp";
        }
        if (!fetchUuidsWithSdp) {
            onBrEdrConnection(bluetoothDevice, 0);
            String str2 = TAG;
            JL_Log.w(str2, "-connectBrEdrDevice- " + str + " failed.");
        } else {
            String str3 = TAG;
            JL_Log.i(str3, "-connectBrEdrDevice- " + str + " success.");
        }
        return fetchUuidsWithSdp;
    }

    private void unregisterReceiver() {
        BluetoothHandFreeReceiver bluetoothHandFreeReceiver = this.mBluetoothHandFreeReceiver;
        if (bluetoothHandFreeReceiver != null) {
            this.mContext.unregisterReceiver(bluetoothHandFreeReceiver);
            this.mBluetoothHandFreeReceiver = null;
        }
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBrEdr
    @SuppressLint({"MissingPermission"})
    public boolean connectBrEdrDevice(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null && ConnectUtil.isHasConnectPermission(this.mContext) && bluetoothDevice.getType() != 2) {
            if (this.mConnectingEdr != null) {
                String str = TAG;
                JL_Log.i(str, "-connectBrEdrDevice- mConnectingEdr is connecting. mConnectingEdr : " + printDeviceInfo(this.mConnectingEdr));
                return false;
            }
            setConnectingEdr(bluetoothDevice);
            boolean isPaired = this.mBluetoothPair.isPaired(bluetoothDevice);
            String str2 = TAG;
            JL_Log.i(str2, "-connectBrEdrDevice- isPaired : " + isPaired);
            if (!isPaired) {
                if (!this.mBluetoothPair.tryToPair(bluetoothDevice, 1)) {
                    onBrEdrConnection(bluetoothDevice, 0);
                    JL_Log.w(str2, "-connectBrEdrDevice- tryToPair is failed.");
                    return false;
                }
            } else if (!tryToConnectBrEdr(bluetoothDevice)) {
                return false;
            }
            onBrEdrConnection(bluetoothDevice, 1);
            this.mHandler.removeMessages(MSG_CONNECT_EDR_TIMEOUT);
            this.mHandler.sendEmptyMessageDelayed(MSG_CONNECT_EDR_TIMEOUT, 40000L);
            return true;
        }
        String str3 = TAG;
        JL_Log.i(str3, "-connectBrEdrDevice- param is error. device : " + printDeviceInfo(bluetoothDevice));
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBrEdr
    public boolean connectByA2dp(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null && ConnectUtil.isHasConnectPermission(this.mContext)) {
            int isConnectedByA2dp = isConnectedByA2dp(bluetoothDevice);
            String str = TAG;
            JL_Log.i(str, "-connectByA2dp- isConnectedByA2dp : " + isConnectedByA2dp);
            BluetoothA2dp bluetoothA2dp = this.mBluetoothA2dp;
            if (bluetoothA2dp == null) {
                JL_Log.e(str, "-connectByA2dp- ad2dp init error");
                return false;
            } else if (isConnectedByA2dp == 2) {
                JL_Log.i(str, "-connectByA2dp- device already connect a2dp.");
                return true;
            } else {
                boolean connectDeviceA2dp = BluetoothUtil.connectDeviceA2dp(this.mContext, bluetoothA2dp, bluetoothDevice);
                JL_Log.i(str, "-connectByA2dp- ret : " + connectDeviceA2dp);
                return connectDeviceA2dp;
            }
        }
        JL_Log.e(TAG, "-connectByA2dp- device is null");
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBrEdr
    public boolean connectByHfp(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null && ConnectUtil.isHasConnectPermission(this.mContext)) {
            if (this.mBluetoothHfp == null) {
                JL_Log.e(TAG, "-connectByHfp- mBluetoothHfp is null");
                return false;
            } else if (!BluetoothUtil.deviceHasHfp(this.mContext, bluetoothDevice)) {
                JL_Log.w(TAG, "-connectByHfp- no found hfp service");
                return false;
            } else {
                int isConnectedByHfp = isConnectedByHfp(bluetoothDevice);
                String str = TAG;
                JL_Log.i(str, "connectByHfp  ------------ isConnectedByHfp : " + isConnectedByHfp);
                if (isConnectedByHfp == 2) {
                    JL_Log.i(str, "-connectByHfp- device already connect hfp.");
                    return true;
                }
                boolean connectDeviceHfp = isConnectedByHfp == 0 ? BluetoothUtil.connectDeviceHfp(this.mContext, this.mBluetoothHfp, bluetoothDevice) : false;
                JL_Log.i(str, "connectByHfp  ------------ ret " + connectDeviceHfp);
                return connectDeviceHfp;
            }
        }
        JL_Log.e(TAG, "-connectByHfp- device is null");
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBrEdr
    @SuppressLint({"MissingPermission"})
    public boolean connectByProfiles(BluetoothDevice bluetoothDevice) {
        boolean z = false;
        if (bluetoothDevice != null && ConnectUtil.isHasConnectPermission(this.mContext) && bluetoothDevice.getType() != 2) {
            String str = TAG;
            JL_Log.i(str, "-connectByProfiles- : " + printDeviceInfo(bluetoothDevice));
            int isConnectedByA2dp = isConnectedByA2dp(bluetoothDevice);
            JL_Log.i(str, "-connectByProfiles- deviceA2dpStatus : " + isConnectedByA2dp);
            boolean z2 = true;
            if (isConnectedByA2dp == 0) {
                z = connectByA2dp(bluetoothDevice);
                JL_Log.i(str, "-connectByProfiles- connectByA2dp  ret : " + z);
                if (z) {
                    return true;
                }
            } else if (isConnectedByA2dp == 2 || isConnectedByA2dp == 1) {
                z = true;
            }
            if (BluetoothUtil.deviceHasHfp(this.mContext, bluetoothDevice)) {
                int isConnectedByHfp = isConnectedByHfp(bluetoothDevice);
                JL_Log.i(str, "-connectByProfiles- deviceHfpStatus : " + isConnectedByHfp);
                if (isConnectedByHfp == 0) {
                    z2 = connectByHfp(bluetoothDevice);
                    JL_Log.i(str, "-connectByProfiles- connectByHfp  ret : " + z2);
                } else if (isConnectedByHfp != 2 && isConnectedByHfp != 1) {
                    z2 = z;
                }
                z = z2;
            } else {
                JL_Log.w(str, "-connectByProfiles- no hfp. device : " + printDeviceInfo(bluetoothDevice));
            }
            JL_Log.i(str, "-connectByProfiles- ret : " + z);
            return z;
        }
        JL_Log.w(TAG, "-connectByProfiles- device is error.");
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void destroy() {
        setConnectingEdr(null);
        this.mHandler.removeCallbacksAndMessages(null);
        this.mBluetoothPair.removeListener(this.mOnBtDevicePairListener);
        this.mBrEdrEventCbManager.destroy();
        unregisterReceiver();
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBrEdr
    @SuppressLint({"MissingPermission"})
    public boolean disconnectByProfiles(BluetoothDevice bluetoothDevice) {
        boolean z;
        if (bluetoothDevice != null && ConnectUtil.isHasConnectPermission(this.mContext)) {
            String str = TAG;
            JL_Log.i(str, "-disconnectByProfiles- device : " + printDeviceInfo(bluetoothDevice));
            if (bluetoothDevice.getType() != 2) {
                int isConnectedByA2dp = isConnectedByA2dp(bluetoothDevice);
                if (isConnectedByA2dp == 2) {
                    z = disconnectFromA2dp(bluetoothDevice);
                    JL_Log.i(str, "-disconnectByProfiles- disconnectFromA2dp ret : " + z);
                } else {
                    z = false;
                }
                int isConnectedByHfp = isConnectedByHfp(bluetoothDevice);
                if (isConnectedByHfp == 2) {
                    z = disconnectFromHfp(bluetoothDevice);
                    JL_Log.i(str, "-disconnectByProfiles- disconnectFromHfp ret : " + z);
                }
                if (isConnectedByA2dp == 0 && isConnectedByHfp == 0) {
                    onBrEdrConnection(bluetoothDevice, 0);
                    return true;
                }
                return z;
            }
            return false;
        }
        JL_Log.i(TAG, "-disconnectByProfiles- device is null ");
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBrEdr
    public boolean disconnectFromA2dp(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            JL_Log.e(TAG, "-disconnectFromA2dp- device is null");
            return false;
        } else if (this.mBluetoothA2dp == null) {
            JL_Log.e(TAG, "-disconnectFromA2dp- mBluetoothA2dp is null");
            return false;
        } else {
            int isConnectedByA2dp = isConnectedByA2dp(bluetoothDevice);
            if (isConnectedByA2dp == 0) {
                JL_Log.i(TAG, "-disconnectFromA2dp- A2dp is disconnected");
                return true;
            }
            boolean disconnectDeviceA2dp = isConnectedByA2dp == 2 ? BluetoothUtil.disconnectDeviceA2dp(this.mContext, this.mBluetoothA2dp, bluetoothDevice) : false;
            String str = TAG;
            JL_Log.i(str, "-disconnectFromA2dp- ret : " + disconnectDeviceA2dp);
            return disconnectDeviceA2dp;
        }
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBrEdr
    public boolean disconnectFromHfp(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null && ConnectUtil.isHasConnectPermission(this.mContext)) {
            if (this.mBluetoothHfp == null) {
                JL_Log.e(TAG, "-disconnectFromHfp- mBluetoothHfp is null");
                return false;
            }
            int isConnectedByHfp = isConnectedByHfp(bluetoothDevice);
            if (isConnectedByHfp == 0) {
                JL_Log.i(TAG, "-disconnectFromHfp- hfp is disconnected");
                return true;
            }
            boolean disconnectDeviceHfp = isConnectedByHfp == 2 ? BluetoothUtil.disconnectDeviceHfp(this.mContext, this.mBluetoothHfp, bluetoothDevice) : false;
            String str = TAG;
            JL_Log.i(str, "-disconnectFromHfp- ret : " + disconnectDeviceHfp);
            return disconnectDeviceHfp;
        }
        JL_Log.e(TAG, "-disconnectFromHfp- device is null");
        return false;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBrEdr
    public BluetoothDevice getActivityBluetoothDevice() {
        return BluetoothUtil.getActivityDevice(this.mContext, this.mBluetoothA2dp);
    }

    public BluetoothA2dp getBluetoothA2dp() {
        return this.mBluetoothA2dp;
    }

    public BluetoothHeadset getBluetoothHfp() {
        return this.mBluetoothHfp;
    }

    public BluetoothPair getBluetoothPair() {
        return this.mBluetoothPair;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBrEdr
    public BluetoothDevice getConnectingBrEdrDevice() {
        return this.mConnectingEdr;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBrEdr
    public boolean isBrEdrConnecting() {
        return this.mConnectingEdr != null;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBrEdr
    @SuppressLint({"MissingPermission"})
    public int isConnectedByA2dp(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null && ConnectUtil.isHasConnectPermission(this.mContext)) {
            BluetoothA2dp bluetoothA2dp = this.mBluetoothA2dp;
            if (bluetoothA2dp == null) {
                JL_Log.e(TAG, "-isConnectedByA2dp- mBluetoothA2dp is null");
                return 0;
            }
            List<BluetoothDevice> connectedDevices = bluetoothA2dp.getConnectedDevices();
            if (connectedDevices != null) {
                for (BluetoothDevice bluetoothDevice2 : connectedDevices) {
                    if (bluetoothDevice2.getAddress().equals(bluetoothDevice.getAddress())) {
                        JL_Log.i(TAG, "-isConnectedByA2dp- ret : true");
                        return 2;
                    }
                }
            } else {
                JL_Log.i(TAG, "-isConnectedByA2dp- connect list is null");
            }
            int connectionState = this.mBluetoothA2dp.getConnectionState(bluetoothDevice);
            String str = TAG;
            JL_Log.i(str, "-isConnectedByA2dp- ret : " + connectionState);
            return connectionState;
        }
        JL_Log.e(TAG, "-isConnectedByA2dp- device is null");
        return 0;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBrEdr
    @SuppressLint({"MissingPermission"})
    public int isConnectedByHfp(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null && ConnectUtil.isHasConnectPermission(this.mContext)) {
            if (this.mBluetoothHfp == null) {
                JL_Log.e(TAG, "-isConnectedByHfp- mBluetoothHfp is null");
                return 0;
            } else if (BluetoothUtil.deviceHasHfp(this.mContext, bluetoothDevice)) {
                List<BluetoothDevice> connectedDevices = this.mBluetoothHfp.getConnectedDevices();
                if (connectedDevices != null) {
                    for (BluetoothDevice bluetoothDevice2 : connectedDevices) {
                        if (bluetoothDevice2.getAddress().equals(bluetoothDevice.getAddress())) {
                            JL_Log.i(TAG, "-isConnectedByHfp- ret : true.");
                            return 2;
                        }
                    }
                } else {
                    JL_Log.i(TAG, "-isConnectedByHfp- no connect list");
                }
                int connectionState = this.mBluetoothHfp.getConnectionState(bluetoothDevice);
                String str = TAG;
                JL_Log.i(str, "-isConnectedByHfp- state : " + connectionState);
                return connectionState;
            } else {
                return 0;
            }
        }
        JL_Log.e(TAG, "-isConnectedByHfp- device is null");
        return 0;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBrEdr
    @SuppressLint({"MissingPermission"})
    public int isConnectedByProfile(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null && ConnectUtil.isHasConnectPermission(this.mContext)) {
            if (this.mBluetoothHfp != null && this.mBluetoothA2dp != null) {
                if (bluetoothDevice.getType() == 2) {
                    JL_Log.w(TAG, "device is Invalid.");
                    return 0;
                }
                List<BluetoothDevice> connectedDevices = this.mBluetoothA2dp.getConnectedDevices();
                if (connectedDevices != null) {
                    for (BluetoothDevice bluetoothDevice2 : connectedDevices) {
                        if (bluetoothDevice2.getAddress().equals(bluetoothDevice.getAddress())) {
                            JL_Log.d(TAG, "device a2dp is connected.");
                            return 2;
                        }
                    }
                }
                List<BluetoothDevice> connectedDevices2 = this.mBluetoothHfp.getConnectedDevices();
                if (connectedDevices2 != null) {
                    for (BluetoothDevice bluetoothDevice3 : connectedDevices2) {
                        if (bluetoothDevice3.getAddress().equals(bluetoothDevice.getAddress())) {
                            JL_Log.d(TAG, "device hfp is connected.");
                            return 2;
                        }
                    }
                }
                return 0;
            }
            JL_Log.w(TAG, "mBluetoothHfp or mBluetoothA2dp is null.");
            initBrEdrService(this.mContext);
            return 0;
        }
        JL_Log.w(TAG, "-isConnectedByProfile- device is null.");
        return 0;
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBrEdr
    @SuppressLint({"MissingPermission"})
    public boolean setActivityBluetoothDevice(BluetoothDevice bluetoothDevice) {
        List<BluetoothDevice> connectedDevices;
        if (((bluetoothDevice == null || this.mBluetoothA2dp == null) && ConnectUtil.isHasConnectPermission(this.mContext)) || (connectedDevices = this.mBluetoothA2dp.getConnectedDevices()) == null || !connectedDevices.contains(bluetoothDevice)) {
            return false;
        }
        return BluetoothUtil.setActivityDevice(this.mContext, this.mBluetoothA2dp, bluetoothDevice);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void setBluetoothOption(BluetoothOption bluetoothOption) {
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void addListener(OnBrEdrListener onBrEdrListener) {
        this.mBrEdrEventCbManager.addListener(onBrEdrListener);
    }

    @Override // com.jieli.bluetooth_connect.interfaces.IBluetoothBase
    public void removeListener(OnBrEdrListener onBrEdrListener) {
        this.mBrEdrEventCbManager.removeListener(onBrEdrListener);
    }

    public boolean disconnectFromA2dp(String str) {
        BluetoothDevice remoteDevice = BluetoothUtil.getRemoteDevice(this.mContext, str);
        return remoteDevice != null && disconnectFromA2dp(remoteDevice);
    }

    public boolean disconnectFromHfp(String str) {
        BluetoothDevice remoteDevice = BluetoothUtil.getRemoteDevice(this.mContext, str);
        return remoteDevice != null && disconnectFromHfp(remoteDevice);
    }

    public boolean connectByA2dp(String str) {
        BluetoothDevice remoteDevice = BluetoothUtil.getRemoteDevice(this.mContext, str);
        return remoteDevice != null && connectByA2dp(remoteDevice);
    }

    public boolean connectByHfp(String str) {
        BluetoothDevice remoteDevice = BluetoothUtil.getRemoteDevice(this.mContext, str);
        return remoteDevice != null && connectByHfp(remoteDevice);
    }
}
