package com.jieli.jl_rcsp.impl;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.jieli.jl_rcsp.constant.RcspConstant;
import com.jieli.jl_rcsp.interfaces.listener.LibLoader;
import com.jieli.jl_rcsp.util.CHexConver;
import com.jieli.jl_rcsp.util.JL_Log;
import com.jieli.jl_rcsp.util.RcspUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes11.dex */
public class RcspAuth {
    public static final int ERR_AUTH_DATA_CHECK = 40980;
    public static final int ERR_AUTH_DATA_SEND = 40979;
    public static final int ERR_AUTH_DEVICE_TIMEOUT = 40977;
    public static final int ERR_AUTH_USER_STOP = 40978;
    public static final int ERR_NONE = 0;
    private static final String f = "RcspAuth";
    private static volatile boolean g = false;
    private static final int h = 2;
    private static final long i = 2000;
    private static final int j = 17;
    private static final int k = 18;
    public static final LibLoader sLocalLibLoader = new LibLoader() { // from class: com.jieli.jl_rcsp.impl.a
        @Override // com.jieli.jl_rcsp.interfaces.listener.LibLoader
        public final void loadLibrary(String str) {
            System.loadLibrary(str);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final boolean f12432a;
    private final IRcspAuthOp b;
    private final List<OnRcspAuthListener> c = Collections.synchronizedList(new ArrayList());
    private final Map<String, AuthTask> d = Collections.synchronizedMap(new HashMap());
    private final Handler e = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.jieli.jl_rcsp.impl.RcspAuth.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message == null) {
                return false;
            }
            int i2 = message.what;
            if (i2 == 17) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) message.obj;
                AuthTask authTask = (AuthTask) RcspAuth.this.d.get(bluetoothDevice.getAddress());
                if (authTask == null) {
                    return false;
                }
                if (authTask.getRetryNum() >= 2) {
                    RcspAuth.this.a(bluetoothDevice, 40977);
                } else {
                    authTask.setRetryNum(authTask.getRetryNum() + 1);
                    RcspAuth.this.b.sendAuthDataToDevice(authTask.getDevice(), authTask.getRandomData());
                    RcspAuth.this.e.removeMessages(18);
                    RcspAuth.this.e.sendMessageDelayed(RcspAuth.this.e.obtainMessage(17, bluetoothDevice), 2000L);
                }
            } else if (i2 == 18) {
                BluetoothDevice bluetoothDevice2 = (BluetoothDevice) message.obj;
                AuthTask authTask2 = (AuthTask) RcspAuth.this.d.get(bluetoothDevice2.getAddress());
                if (authTask2 != null && !authTask2.isAuthDevice()) {
                    RcspAuth.this.a(bluetoothDevice2, 40977);
                }
            }
            return true;
        }
    });

    /* loaded from: classes11.dex */
    public static class AuthTask {

        /* renamed from: a  reason: collision with root package name */
        private BluetoothDevice f12434a;
        private boolean b;
        private boolean c;
        private byte[] d;
        private int e;

        private AuthTask() {
        }

        public BluetoothDevice getDevice() {
            return this.f12434a;
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
            this.f12434a = bluetoothDevice;
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
            return "AuthTask{device=" + RcspUtil.printBtDeviceInfo(this.f12434a) + ", isAuthProgressResult=" + this.b + ", isAuthDevice=" + this.c + ", randomData=" + CHexConver.byte2HexStr(this.d) + ", retryNum=" + this.e + '}';
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

    public RcspAuth(IRcspAuthOp iRcspAuthOp, OnRcspAuthListener onRcspAuthListener) {
        if (iRcspAuthOp != null) {
            loadLibrariesOnce(null);
            this.f12432a = nativeInit();
            this.b = iRcspAuthOp;
            addListener(onRcspAuthListener);
            return;
        }
        throw new IllegalArgumentException("IRcspAuthOp can not be null.");
    }

    private String a(int i2) {
        switch (i2) {
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

    public static void loadLibrariesOnce(LibLoader libLoader) {
        synchronized (RcspAuth.class) {
            if (!g) {
                if (libLoader == null) {
                    libLoader = sLocalLibLoader;
                }
                libLoader.loadLibrary(RcspConstant.JL_RCSP_LIB);
                g = true;
            }
        }
    }

    public void addListener(OnRcspAuthListener onRcspAuthListener) {
        if (onRcspAuthListener != null) {
            this.c.add(onRcspAuthListener);
            onRcspAuthListener.onInitResult(this.f12432a);
        }
    }

    public void destroy() {
        this.e.removeCallbacksAndMessages(null);
        this.d.clear();
        this.c.clear();
        g = false;
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

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0066, code lost:
        if (r8.b.sendAuthDataToDevice(r9, getAuthOkData()) != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00af, code lost:
        if (r8.b.sendAuthDataToDevice(r9, r10) != false) goto L33;
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
            r8 = this;
            if (r9 == 0) goto Lf8
            boolean r0 = r8.a(r10)
            if (r0 != 0) goto La
            goto Lf8
        La:
            java.util.Map<java.lang.String, com.jieli.jl_rcsp.impl.RcspAuth$AuthTask> r0 = r8.d
            java.lang.String r1 = r9.getAddress()
            java.lang.Object r0 = r0.get(r1)
            com.jieli.jl_rcsp.impl.RcspAuth$AuthTask r0 = (com.jieli.jl_rcsp.impl.RcspAuth.AuthTask) r0
            if (r0 == 0) goto Lf8
            boolean r1 = r0.isAuthDevice()
            if (r1 == 0) goto L20
            goto Lf8
        L20:
            java.lang.String r1 = com.jieli.jl_rcsp.impl.RcspAuth.f
            java.util.Locale r2 = java.util.Locale.getDefault()
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = 0
            r3[r4] = r9
            java.lang.String r5 = com.jieli.jl_rcsp.util.CHexConver.byte2HexStr(r10)
            r6 = 1
            r3[r6] = r5
            java.lang.String r5 = "-handleAuthData- device : %s, data : %s"
            java.lang.String r2 = java.lang.String.format(r2, r5, r3)
            com.jieli.jl_rcsp.util.JL_Log.d(r1, r2)
            boolean r2 = r0.isAuthProgressResult()
            r3 = 17
            if (r2 != 0) goto L86
            int r2 = r10.length
            if (r2 != r3) goto L85
            r2 = r10[r4]
            if (r2 == r6) goto L4c
            goto L85
        L4c:
            byte[] r2 = r0.getRandomData()
            byte[] r2 = r8.getAuthData(r2)
            if (r2 == 0) goto L69
            boolean r10 = java.util.Arrays.equals(r2, r10)
            if (r10 == 0) goto L69
            com.jieli.jl_rcsp.impl.RcspAuth$IRcspAuthOp r10 = r8.b
            byte[] r1 = r8.getAuthOkData()
            boolean r10 = r10.sendAuthDataToDevice(r9, r1)
            if (r10 == 0) goto Lb2
            goto Lb1
        L69:
            r10 = 40980(0xa014, float:5.7425E-41)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "-handleAuthData- data not match. authData : "
            r5.append(r7)
            java.lang.String r2 = com.jieli.jl_rcsp.util.CHexConver.byte2HexStr(r2)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            com.jieli.jl_rcsp.util.JL_Log.w(r1, r2)
            goto Lc7
        L85:
            return
        L86:
            int r2 = r10.length
            if (r2 != r3) goto Lb6
            r2 = r10[r4]
            if (r2 != 0) goto Lb6
            byte[] r10 = r8.getAuthData(r10)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "-handleAuthData- devAuthData : "
            r2.append(r5)
            java.lang.String r5 = com.jieli.jl_rcsp.util.CHexConver.byte2HexStr(r10)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            com.jieli.jl_rcsp.util.JL_Log.i(r1, r2)
            com.jieli.jl_rcsp.impl.RcspAuth$IRcspAuthOp r1 = r8.b
            boolean r10 = r1.sendAuthDataToDevice(r9, r10)
            if (r10 == 0) goto Lb2
        Lb1:
            goto Lc6
        Lb2:
            r10 = 40979(0xa013, float:5.7424E-41)
            goto Lc7
        Lb6:
            byte[] r1 = r8.getAuthOkData()
            boolean r10 = java.util.Arrays.equals(r10, r1)
            if (r10 == 0) goto Lf8
            r0.setAuthDevice(r6)
            r8.b(r9)
        Lc6:
            r10 = r4
        Lc7:
            if (r10 != 0) goto Lca
            goto Lcb
        Lca:
            r6 = r4
        Lcb:
            r0.setAuthProgressResult(r6)
            if (r10 == 0) goto Ldb
            r0.setAuthDevice(r4)
            java.lang.String r0 = r8.a(r10)
            r8.b(r9, r10, r0)
            goto Lf8
        Ldb:
            android.os.Handler r10 = r8.e
            r10.removeMessages(r3)
            android.os.Handler r10 = r8.e
            r1 = 18
            r10.removeMessages(r1)
            boolean r10 = r0.isAuthDevice()
            if (r10 != 0) goto Lf8
            android.os.Handler r10 = r8.e
            android.os.Message r9 = r10.obtainMessage(r1, r9)
            r0 = 2000(0x7d0, double:9.88E-321)
            r10.sendMessageDelayed(r9, r0)
        Lf8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_rcsp.impl.RcspAuth.handleAuthData(android.bluetooth.BluetoothDevice, byte[]):void");
    }

    public native boolean nativeInit();

    public void removeListener(OnRcspAuthListener onRcspAuthListener) {
        if (onRcspAuthListener != null) {
            this.c.remove(onRcspAuthListener);
        }
    }

    public int setDeviceConnectionLinkKey(byte[] bArr) {
        return setLinkKey(bArr);
    }

    public native int setLinkKey(byte[] bArr);

    public boolean startAuth(final BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        if (this.d.containsKey(bluetoothDevice.getAddress())) {
            AuthTask authTask = this.d.get(bluetoothDevice.getAddress());
            if (authTask != null && (authTask.isAuthDevice() || this.e.hasMessages(18))) {
                JL_Log.i(f, "-startAuth- The device has been certified or certification of device is in progress.");
                return true;
            }
            this.d.remove(bluetoothDevice.getAddress());
        }
        String str = f;
        JL_Log.i(str, "-startAuth- device = " + RcspUtil.printBtDeviceInfo(bluetoothDevice));
        final AuthTask randomData = new AuthTask().setDevice(bluetoothDevice).setRandomData(getRandomData());
        this.d.put(bluetoothDevice.getAddress(), randomData);
        boolean sendAuthDataToDevice = this.b.sendAuthDataToDevice(bluetoothDevice, a());
        if (sendAuthDataToDevice) {
            this.e.postDelayed(new Runnable() { // from class: com.jieli.jl_rcsp.impl.d
                @Override // java.lang.Runnable
                public final void run() {
                    RcspAuth.this.a(bluetoothDevice, randomData);
                }
            }, 500L);
        }
        return sendAuthDataToDevice;
    }

    public void stopAuth(BluetoothDevice bluetoothDevice) {
        stopAuth(bluetoothDevice, true);
    }

    private void b(final boolean z) {
        this.e.post(new Runnable() { // from class: com.jieli.jl_rcsp.impl.e
            @Override // java.lang.Runnable
            public final void run() {
                RcspAuth.this.a(z);
            }
        });
    }

    public void stopAuth(BluetoothDevice bluetoothDevice, boolean z) {
        if (bluetoothDevice == null) {
            return;
        }
        AuthTask remove = this.d.remove(bluetoothDevice.getAddress());
        if (z) {
            if (remove != null) {
                a(bluetoothDevice, 40978);
            }
            this.e.removeMessages(17);
            this.e.removeMessages(18);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(BluetoothDevice bluetoothDevice, AuthTask authTask) {
        if (this.b.sendAuthDataToDevice(bluetoothDevice, authTask.getRandomData())) {
            this.e.removeMessages(17);
            Handler handler = this.e;
            handler.sendMessageDelayed(handler.obtainMessage(17, bluetoothDevice), 2000L);
            return;
        }
        b(bluetoothDevice, 40979, "Failed to send data.");
    }

    private void b(final BluetoothDevice bluetoothDevice) {
        JL_Log.w(f, String.format(Locale.getDefault(), "-onAuthSuccess- device = %s,  auth ok.", bluetoothDevice));
        this.e.post(new Runnable() { // from class: com.jieli.jl_rcsp.impl.b
            @Override // java.lang.Runnable
            public final void run() {
                RcspAuth.this.a(bluetoothDevice);
            }
        });
        if (bluetoothDevice != null) {
            this.d.remove(bluetoothDevice.getAddress());
        }
    }

    private void b(final BluetoothDevice bluetoothDevice, final int i2, final String str) {
        JL_Log.e(f, String.format(Locale.getDefault(), "-onAuthFailed- device = %s,  code = %d, message = %s.", bluetoothDevice, Integer.valueOf(i2), str));
        this.e.removeMessages(17);
        this.e.removeMessages(18);
        this.e.post(new Runnable() { // from class: com.jieli.jl_rcsp.impl.c
            @Override // java.lang.Runnable
            public final void run() {
                RcspAuth.this.a(bluetoothDevice, i2, str);
            }
        });
        if (bluetoothDevice != null) {
            this.d.remove(bluetoothDevice.getAddress());
        }
    }

    private byte[] a() {
        return CHexConver.hexStr2Bytes("FEDCBAC00600020001EF");
    }

    private boolean a(byte[] bArr) {
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
    public /* synthetic */ void a(boolean z) {
        Iterator it = new ArrayList(this.c).iterator();
        while (it.hasNext()) {
            ((OnRcspAuthListener) it.next()).onInitResult(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(BluetoothDevice bluetoothDevice) {
        if (this.c.isEmpty()) {
            return;
        }
        Iterator it = new ArrayList(this.c).iterator();
        while (it.hasNext()) {
            ((OnRcspAuthListener) it.next()).onAuthSuccess(bluetoothDevice);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothDevice bluetoothDevice, int i2) {
        b(bluetoothDevice, i2, a(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(BluetoothDevice bluetoothDevice, int i2, String str) {
        if (this.c.isEmpty()) {
            return;
        }
        Iterator it = new ArrayList(this.c).iterator();
        while (it.hasNext()) {
            ((OnRcspAuthListener) it.next()).onAuthFailed(bluetoothDevice, i2, str);
        }
    }
}
