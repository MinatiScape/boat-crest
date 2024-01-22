package com.jieli.jl_bt_ota.impl;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.jieli.jl_bt_ota.constant.BluetoothConstant;
import com.jieli.jl_bt_ota.interfaces.JieLiLibLoader;
import com.jieli.jl_bt_ota.util.BluetoothUtil;
import com.jieli.jl_bt_ota.util.CHexConver;
import com.jieli.jl_bt_ota.util.JL_Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
@Keep
/* loaded from: classes11.dex */
public class RcspAuth {
    private static final int AUTH_RETRY_COUNT = 2;
    private static final long DELAY_AUTH_WAITING_TIME = 2000;
    public static final int ERR_AUTH_DATA_CHECK = 40980;
    public static final int ERR_AUTH_DATA_SEND = 40979;
    public static final int ERR_AUTH_DEVICE_TIMEOUT = 40977;
    public static final int ERR_AUTH_USER_STOP = 40978;
    public static final int ERR_NONE = 0;
    private static final int MSG_AUTH_DEVICE_TIMEOUT = 18;
    private static final int MSG_SEND_AUTH_DATA_TIMEOUT = 17;
    public static boolean SUPPORT_RESET_FLAG = false;
    private static final String TAG = "RcspAuth";
    private static volatile boolean mIsLibLoaded = false;
    public static final JieLiLibLoader sLocalLibLoader = new JieLiLibLoader() { // from class: com.jieli.jl_bt_ota.impl.g
        @Override // com.jieli.jl_bt_ota.interfaces.JieLiLibLoader
        public final void loadLibrary(String str) {
            System.loadLibrary(str);
        }
    };
    private final boolean isLibInit;
    private final Context mContext;
    private final IRcspAuthOp mIRcspAuthOp;
    private final List<OnRcspAuthListener> mOnRcspAuthListeners = Collections.synchronizedList(new ArrayList());
    private final Map<String, AuthTask> mAuthTaskMap = Collections.synchronizedMap(new HashMap());
    private final Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.jieli.jl_bt_ota.impl.RcspAuth.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            int i = message.what;
            if (i == 17) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) message.obj;
                AuthTask authTask = (AuthTask) RcspAuth.this.mAuthTaskMap.get(bluetoothDevice.getAddress());
                if (authTask == null) {
                    return false;
                }
                if (authTask.getRetryNum() >= 2) {
                    RcspAuth.this.onAuthFailed(bluetoothDevice, 40977);
                } else {
                    authTask.setRetryNum(authTask.getRetryNum() + 1);
                    RcspAuth.this.sendAuthDataToDevice(authTask.getDevice(), authTask.getRandomData());
                    RcspAuth.this.mHandler.removeMessages(18);
                    RcspAuth.this.mHandler.sendMessageDelayed(RcspAuth.this.mHandler.obtainMessage(17, bluetoothDevice), 2000L);
                }
            } else if (i == 18) {
                BluetoothDevice bluetoothDevice2 = (BluetoothDevice) message.obj;
                AuthTask authTask2 = (AuthTask) RcspAuth.this.mAuthTaskMap.get(bluetoothDevice2.getAddress());
                if (authTask2 != null && !authTask2.isAuthDevice()) {
                    RcspAuth.this.onAuthFailed(bluetoothDevice2, 40977);
                }
            }
            return true;
        }
    });

    /* loaded from: classes11.dex */
    public static class AuthTask {

        /* renamed from: a  reason: collision with root package name */
        private BluetoothDevice f12353a;
        private boolean b;
        private boolean c;
        private byte[] d;
        private int e;

        private AuthTask() {
        }

        public BluetoothDevice getDevice() {
            return this.f12353a;
        }

        public byte[] getRandomData() {
            return this.d;
        }

        public int getRetryNum() {
            return this.e;
        }

        public boolean isAuthDevice() {
            return this.c;
        }

        public boolean isAuthProgressResult() {
            return this.b;
        }

        public void setAuthDevice(boolean z) {
            this.c = z;
        }

        public void setAuthProgressResult(boolean z) {
            this.b = z;
        }

        public AuthTask setDevice(BluetoothDevice bluetoothDevice) {
            this.f12353a = bluetoothDevice;
            return this;
        }

        public AuthTask setRandomData(byte[] bArr) {
            this.d = bArr;
            return this;
        }

        public void setRetryNum(int i) {
            this.e = i;
        }

        public String toString() {
            return "AuthTask{device=" + this.f12353a + ", isAuthProgressResult=" + this.b + ", isAuthDevice=" + this.c + ", randomData=" + CHexConver.byte2HexStr(this.d) + ", retryNum=" + this.e + '}';
        }
    }

    /* loaded from: classes11.dex */
    public interface IRcspAuthOp {
        boolean sendAuthDataToDevice(BluetoothDevice bluetoothDevice, byte[] bArr);
    }

    /* loaded from: classes11.dex */
    public interface OnRcspAuthListener {
        void onAuthFailed(BluetoothDevice bluetoothDevice, int i, String str);

        void onAuthSuccess(BluetoothDevice bluetoothDevice);

        void onInitResult(boolean z);
    }

    public RcspAuth(Context context, IRcspAuthOp iRcspAuthOp, OnRcspAuthListener onRcspAuthListener) {
        if (iRcspAuthOp != null) {
            loadLibrariesOnce(null);
            this.isLibInit = nativeInit();
            this.mContext = context;
            this.mIRcspAuthOp = iRcspAuthOp;
            addListener(onRcspAuthListener);
            return;
        }
        throw new IllegalArgumentException("IRcspAuthOp can not be null.");
    }

    private String getErrorMsg(int i) {
        switch (i) {
            case 40977:
                return "Auth device timeout.";
            case 40978:
                return "User stop auth device.";
            case 40979:
                return "Failed to send data.";
            case 40980:
                return "Check auth data error.";
            default:
                return "";
        }
    }

    private byte[] getResetAuthFlagCmdData() {
        return CHexConver.hexStr2Bytes("FEDCBAC00600020001EF");
    }

    private boolean isValidAuthData(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return false;
        }
        if (bArr.length != 5 || bArr[0] != 2) {
            if (bArr.length != 17) {
                return false;
            }
            if (bArr[0] != 0 && bArr[0] != 1) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onAuthFailed$3(BluetoothDevice bluetoothDevice, int i, String str) {
        if (this.mOnRcspAuthListeners.isEmpty()) {
            return;
        }
        Iterator it = new ArrayList(this.mOnRcspAuthListeners).iterator();
        while (it.hasNext()) {
            ((OnRcspAuthListener) it.next()).onAuthFailed(bluetoothDevice, i, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onAuthSuccess$2(BluetoothDevice bluetoothDevice) {
        if (this.mOnRcspAuthListeners.isEmpty()) {
            return;
        }
        Iterator it = new ArrayList(this.mOnRcspAuthListeners).iterator();
        while (it.hasNext()) {
            ((OnRcspAuthListener) it.next()).onAuthSuccess(bluetoothDevice);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onInitResult$1(boolean z) {
        if (this.mOnRcspAuthListeners.isEmpty()) {
            return;
        }
        Iterator it = new ArrayList(this.mOnRcspAuthListeners).iterator();
        while (it.hasNext()) {
            ((OnRcspAuthListener) it.next()).onInitResult(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startAuth$0(BluetoothDevice bluetoothDevice, AuthTask authTask) {
        if (sendAuthDataToDevice(bluetoothDevice, authTask.getRandomData())) {
            this.mHandler.removeMessages(17);
            Handler handler = this.mHandler;
            handler.sendMessageDelayed(handler.obtainMessage(17, bluetoothDevice), 2000L);
            return;
        }
        onAuthFailed(bluetoothDevice, 40979, "Failed to send data.");
    }

    public static void loadLibrariesOnce(JieLiLibLoader jieLiLibLoader) {
        synchronized (RcspAuth.class) {
            if (!mIsLibLoaded) {
                if (jieLiLibLoader == null) {
                    jieLiLibLoader = sLocalLibLoader;
                }
                jieLiLibLoader.loadLibrary(BluetoothConstant.JL_AUTH);
                mIsLibLoaded = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAuthFailed(BluetoothDevice bluetoothDevice, int i) {
        onAuthFailed(bluetoothDevice, i, getErrorMsg(i));
    }

    private void onAuthSuccess(final BluetoothDevice bluetoothDevice) {
        JL_Log.w(TAG, String.format(Locale.getDefault(), "-onAuthSuccess- device = %s,  auth ok.", printDeviceInfo(bluetoothDevice)));
        this.mHandler.post(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.h
            @Override // java.lang.Runnable
            public final void run() {
                RcspAuth.this.lambda$onAuthSuccess$2(bluetoothDevice);
            }
        });
        if (bluetoothDevice != null) {
            this.mAuthTaskMap.remove(bluetoothDevice.getAddress());
        }
    }

    private void onInitResult(final boolean z) {
        this.mHandler.post(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.k
            @Override // java.lang.Runnable
            public final void run() {
                RcspAuth.this.lambda$onInitResult$1(z);
            }
        });
    }

    private String printDeviceInfo(BluetoothDevice bluetoothDevice) {
        return BluetoothUtil.printBtDeviceInfo(this.mContext, bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean sendAuthDataToDevice(BluetoothDevice bluetoothDevice, byte[] bArr) {
        if (bluetoothDevice == null || bArr == null) {
            return false;
        }
        boolean sendAuthDataToDevice = this.mIRcspAuthOp.sendAuthDataToDevice(bluetoothDevice, bArr);
        JL_Log.i(TAG, String.format(Locale.getDefault(), "-sendAuthDataToDevice- device : %s, authData : %s", printDeviceInfo(bluetoothDevice), CHexConver.byte2HexStr(bArr)));
        return sendAuthDataToDevice;
    }

    public void addListener(OnRcspAuthListener onRcspAuthListener) {
        if (onRcspAuthListener != null) {
            this.mOnRcspAuthListeners.add(onRcspAuthListener);
            onRcspAuthListener.onInitResult(this.isLibInit);
        }
    }

    public void destroy() {
        this.mHandler.removeCallbacksAndMessages(null);
        this.mAuthTaskMap.clear();
        this.mOnRcspAuthListeners.clear();
        mIsLibLoaded = false;
    }

    public byte[] getAuthData(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return getEncryptedAuthData(bArr);
    }

    public byte[] getAuthOkData() {
        return new byte[]{2, com.htsmart.wristband2.a.a.a.J1, 97, 115, 115};
    }

    public native byte[] getEncryptedAuthData(byte[] bArr);

    public native byte[] getRandomAuthData();

    public byte[] getRandomData() {
        return getRandomAuthData();
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0068, code lost:
        if (sendAuthDataToDevice(r9, getAuthOkData()) != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00af, code lost:
        if (sendAuthDataToDevice(r9, r10) != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b2, code lost:
        r10 = 40979;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleAuthData(android.bluetooth.BluetoothDevice r9, byte[] r10) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_bt_ota.impl.RcspAuth.handleAuthData(android.bluetooth.BluetoothDevice, byte[]):void");
    }

    public native boolean nativeInit();

    public void removeListener(OnRcspAuthListener onRcspAuthListener) {
        if (onRcspAuthListener != null) {
            this.mOnRcspAuthListeners.remove(onRcspAuthListener);
        }
    }

    public int setDeviceConnectionLinkKey(byte[] bArr) {
        return setLinkKey(bArr);
    }

    public native int setLinkKey(byte[] bArr);

    public boolean startAuth(final BluetoothDevice bluetoothDevice) {
        boolean sendAuthDataToDevice;
        if (bluetoothDevice == null) {
            return false;
        }
        if (this.mAuthTaskMap.containsKey(bluetoothDevice.getAddress())) {
            AuthTask authTask = this.mAuthTaskMap.get(bluetoothDevice.getAddress());
            if (authTask != null && (authTask.isAuthDevice() || this.mHandler.hasMessages(18))) {
                JL_Log.i(TAG, "-startAuth- The device has been certified or certification of device is in progress.");
                return true;
            }
            this.mAuthTaskMap.remove(bluetoothDevice.getAddress());
        }
        String str = TAG;
        JL_Log.i(str, "-startAuth- device = " + printDeviceInfo(bluetoothDevice));
        final AuthTask randomData = new AuthTask().setDevice(bluetoothDevice).setRandomData(getRandomData());
        this.mAuthTaskMap.put(bluetoothDevice.getAddress(), randomData);
        if (SUPPORT_RESET_FLAG) {
            sendAuthDataToDevice = sendAuthDataToDevice(bluetoothDevice, getResetAuthFlagCmdData());
            if (sendAuthDataToDevice) {
                this.mHandler.postDelayed(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.j
                    @Override // java.lang.Runnable
                    public final void run() {
                        RcspAuth.this.lambda$startAuth$0(bluetoothDevice, randomData);
                    }
                }, 500L);
            }
        } else {
            sendAuthDataToDevice = sendAuthDataToDevice(bluetoothDevice, randomData.getRandomData());
            if (sendAuthDataToDevice) {
                this.mHandler.removeMessages(17);
                Handler handler = this.mHandler;
                handler.sendMessageDelayed(handler.obtainMessage(17, bluetoothDevice), 2000L);
            }
        }
        return sendAuthDataToDevice;
    }

    public void stopAuth(BluetoothDevice bluetoothDevice) {
        stopAuth(bluetoothDevice, true);
    }

    private void onAuthFailed(final BluetoothDevice bluetoothDevice, final int i, final String str) {
        JL_Log.e(TAG, String.format(Locale.getDefault(), "-onAuthFailed- device = %s,  code = %d, message = %s.", bluetoothDevice, Integer.valueOf(i), str));
        this.mHandler.removeMessages(17);
        this.mHandler.removeMessages(18);
        this.mHandler.post(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.i
            @Override // java.lang.Runnable
            public final void run() {
                RcspAuth.this.lambda$onAuthFailed$3(bluetoothDevice, i, str);
            }
        });
        if (bluetoothDevice != null) {
            this.mAuthTaskMap.remove(bluetoothDevice.getAddress());
        }
    }

    public void stopAuth(BluetoothDevice bluetoothDevice, boolean z) {
        if (bluetoothDevice == null) {
            return;
        }
        AuthTask remove = this.mAuthTaskMap.remove(bluetoothDevice.getAddress());
        if (z) {
            if (remove != null) {
                onAuthFailed(bluetoothDevice, 40978);
            }
            this.mHandler.removeMessages(17);
            this.mHandler.removeMessages(18);
        }
    }
}
