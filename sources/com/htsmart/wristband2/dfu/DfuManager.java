package com.htsmart.wristband2.dfu;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.stats.CodePackage;
import com.htsmart.wristband2.WristbandApplication;
import com.htsmart.wristband2.dfu.h;
import com.htsmart.wristband2.dfu.i;
import com.htsmart.wristband2.dfu.j;
import com.htsmart.wristband2.dfu.k;
import com.htsmart.wristband2.utils.WristbandLog;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.realsil.sdk.core.RtkConfigure;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.dfu.RtkDfu;
import java.lang.reflect.InvocationTargetException;
/* loaded from: classes11.dex */
public class DfuManager {
    public static final int ERROR_CODE_BT_DISABLE = 1;
    public static final int ERROR_CODE_BT_UNSUPPORTED = 0;
    public static final int ERROR_CODE_DFU_DEVICE_NOT_FOUND = 1;
    public static final int ERROR_CODE_DFU_DEVICE_SCAN_FAILED = 0;
    public static final int ERROR_CODE_DFU_FILE_CANNOT_READ = 2;
    public static final int ERROR_CODE_DFU_FILE_DOWNLOAD = 4;
    public static final int ERROR_CODE_DFU_FILE_FORMAT = 3;
    public static final int ERROR_CODE_DFU_FILE_NOT_EXIST = 1;
    public static final int ERROR_CODE_DFU_FILE_URI = 0;
    public static final int ERROR_CODE_DFU_MODE_ABORT = 3;
    public static final int ERROR_CODE_DFU_MODE_CMD_FAILED = 1;
    public static final int ERROR_CODE_DFU_MODE_HARDWARE_INFO = 0;
    public static final int ERROR_CODE_DFU_MODE_LOW_BATTERY = 2;
    public static final int ERROR_CODE_DFU_MODE_UNSUPPORTED = 4;
    public static final int ERROR_CODE_DFU_PROCESS_ABORT = 2147483644;
    public static final int ERROR_CODE_DFU_PROCESS_SERVICE_NOT_READY = 2147483646;
    public static final int ERROR_CODE_DFU_PROCESS_STARTUP_FAILED = 2147483645;
    public static final int ERROR_CODE_UNKNOWN = Integer.MAX_VALUE;
    public static final int ERROR_TYPE_BT = 0;
    public static final int ERROR_TYPE_DFU_DEVICE = 3;
    public static final int ERROR_TYPE_DFU_FILE = 1;
    public static final int ERROR_TYPE_DFU_MODE = 2;
    public static final int ERROR_TYPE_DFU_PROCESS = 4;
    public static final int STATE_CHECK_DFU_FILE = 1;
    public static final int STATE_CHECK_DFU_MODE = 2;
    public static final int STATE_DFU_ING = 4;
    public static final int STATE_FIND_DFU_DEVICE = 3;
    public static final int STATE_NONE = 0;

    /* renamed from: a  reason: collision with root package name */
    public Context f11987a;
    public i b;
    public j c;
    public h d;
    public DfuCallback e;
    public k f;
    public boolean g;
    public String h;
    public int i;
    public volatile int j = 0;
    public i.a k = new a();
    public j.b l = new b();
    public h.a m = new c();
    public k.a n = new d();
    public Handler o = new e(Looper.getMainLooper());
    public Class<? extends k> p;

    /* loaded from: classes11.dex */
    public class a implements i.a {
        public a() {
        }

        @Override // com.htsmart.wristband2.dfu.i.a
        public void a(@NonNull String str) {
            Message obtainMessage = DfuManager.this.o.obtainMessage(102);
            obtainMessage.obj = str;
            obtainMessage.sendToTarget();
        }

        @Override // com.htsmart.wristband2.dfu.i.a
        public void onError(int i, int i2) {
            DfuManager.this.i(i, i2);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements j.b {
        public b() {
        }

        @Override // com.htsmart.wristband2.dfu.j.b
        public void a(int i) {
            DfuManager.this.i(2, i);
        }

        @Override // com.htsmart.wristband2.dfu.j.b
        public void a(@Nullable String str, @Nullable String str2, int i) {
            Message obtainMessage = DfuManager.this.o.obtainMessage(103);
            Bundle bundle = new Bundle();
            bundle.putString(DeviceKey.DeviceName, str);
            bundle.putString("deviceAddress", str2);
            bundle.putInt("dfuMode", i);
            obtainMessage.obj = bundle;
            DfuManager.this.o.sendMessageDelayed(obtainMessage, 3000L);
        }
    }

    /* loaded from: classes11.dex */
    public class c implements h.a {
        public c() {
        }

        @Override // com.htsmart.wristband2.dfu.h.a
        public void a(int i) {
            DfuManager.this.i(3, i);
        }

        @Override // com.htsmart.wristband2.dfu.h.a
        public void a(@NonNull BluetoothDevice bluetoothDevice) {
            Message obtainMessage = DfuManager.this.o.obtainMessage(104);
            obtainMessage.obj = bluetoothDevice;
            obtainMessage.sendToTarget();
        }
    }

    /* loaded from: classes11.dex */
    public class d implements k.a {
        public d() {
        }

        @Override // com.htsmart.wristband2.dfu.k.a
        public void a(int i) {
            DfuManager.this.i(4, i);
        }

        @Override // com.htsmart.wristband2.dfu.k.a
        public void onProgressChanged(int i) {
            Message obtainMessage = DfuManager.this.o.obtainMessage(105);
            obtainMessage.arg1 = i;
            obtainMessage.sendToTarget();
        }

        @Override // com.htsmart.wristband2.dfu.k.a
        public void onSuccess() {
            DfuManager.this.o.sendEmptyMessage(106);
        }
    }

    /* loaded from: classes11.dex */
    public class e extends Handler {
        public e(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            DfuManager dfuManager;
            k c;
            switch (message.what) {
                case 100:
                    DfuManager.this.j = message.arg1;
                    if (DfuManager.this.e != null) {
                        DfuManager.this.e.onStateChanged(DfuManager.this.j, DfuManager.this.j != 4);
                    }
                    WristbandLog.i("MSG_STATE_CHANGED mState=%d", Integer.valueOf(DfuManager.this.j));
                    return;
                case 101:
                    DfuManager.this.j = 0;
                    if (DfuManager.this.e != null) {
                        DfuManager.this.e.onError(message.arg1, message.arg2);
                    }
                    WristbandLog.i("MSG_ERROR errorType=%d , errorCode=%d", Integer.valueOf(message.arg1), Integer.valueOf(message.arg2));
                    return;
                case 102:
                    WristbandLog.i("MSG_FILE_CHECKER_PREPARED mState=%d", Integer.valueOf(DfuManager.this.j));
                    if (DfuManager.this.j != 0) {
                        DfuManager.this.h(2);
                        DfuManager.this.h = (String) message.obj;
                        DfuManager.this.c.a(DfuManager.this.g);
                        return;
                    }
                    return;
                case 103:
                    WristbandLog.i("MSG_MODE_CHECKER_PREPARED mState=%d", Integer.valueOf(DfuManager.this.j));
                    if (DfuManager.this.j != 0) {
                        DfuManager.this.h(3);
                        Bundle bundle = (Bundle) message.obj;
                        String string = bundle.getString(DeviceKey.DeviceName);
                        String string2 = bundle.getString("deviceAddress");
                        int i = bundle.getInt("dfuMode");
                        WristbandLog.i("MSG_MODE_CHECKER_PREPARED dfuMode=%d", Integer.valueOf(i));
                        if (DfuManager.this.f != null) {
                            k kVar = DfuManager.this.f;
                            if (i != 2 ? !(i != 3 ? kVar.getClass().equals(DfuManager.this.f()) : (kVar instanceof g)) : !(kVar instanceof f)) {
                                DfuManager.this.f.b();
                                DfuManager.this.f = null;
                            }
                        }
                        if (DfuManager.this.f == null) {
                            if (i == 2) {
                                dfuManager = DfuManager.this;
                                c = new f(dfuManager.f11987a);
                            } else if (i == 3) {
                                dfuManager = DfuManager.this;
                                c = new g(dfuManager.f11987a);
                            } else {
                                dfuManager = DfuManager.this;
                                c = dfuManager.c(dfuManager.f11987a);
                            }
                            dfuManager.f = c;
                            if (DfuManager.this.f != null) {
                                DfuManager.this.f.a();
                                DfuManager.this.f.a(DfuManager.this.n);
                            }
                        }
                        if (DfuManager.this.f != null) {
                            WristbandLog.i("Use DfuProcess : " + DfuManager.this.f.getClass().getCanonicalName(), new Object[0]);
                        }
                        DfuManager.this.i = i;
                        DfuManager.this.d.a(string, string2, i);
                        return;
                    }
                    return;
                case 104:
                    WristbandLog.i("MSG_DEVICE_FINDER_PREPARED mState=%d", Integer.valueOf(DfuManager.this.j));
                    if (DfuManager.this.j != 0) {
                        DfuManager.this.h(4);
                        BluetoothDevice bluetoothDevice = (BluetoothDevice) message.obj;
                        if (DfuManager.this.f != null) {
                            DfuManager.this.f.a(bluetoothDevice, DfuManager.this.h, DfuManager.this.g);
                            return;
                        }
                        WristbandLog.w("mDfuProcess == null", new Object[0]);
                        DfuManager.this.i(4, 2147483646);
                        return;
                    }
                    return;
                case 105:
                    WristbandLog.i("MSG_DFU_PROGRESS_CHANGE progress=%d", Integer.valueOf(message.arg1));
                    if (DfuManager.this.e != null) {
                        DfuManager.this.e.onProgressChanged(message.arg1);
                        return;
                    }
                    return;
                case 106:
                    WristbandLog.i("MSG_DFU_SUCCESS", new Object[0]);
                    DfuManager.this.j = 0;
                    if (DfuManager.this.e != null) {
                        DfuManager.this.e.onSuccess();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public DfuManager(Context context) {
        this.f11987a = context.getApplicationContext();
        RtkCore.initialize(this.f11987a, new RtkConfigure.Builder().debugEnabled(WristbandApplication.isDebugEnable()).printLog(WristbandApplication.isDebugEnable()).logTag(CodePackage.OTA).build());
        RtkDfu.initialize(this.f11987a, WristbandApplication.isDebugEnable());
    }

    public final k c(Context context) {
        Class<? extends k> cls = this.p;
        if (cls == null) {
            return new com.htsmart.wristband2.dfu.e(context);
        }
        try {
            return cls.getConstructor(Context.class).newInstance(context);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void cancel() {
        if (this.j != 4) {
            this.b.cancel();
            this.c.cancel();
            this.d.cancel();
            this.j = 0;
            this.o.removeCallbacksAndMessages(null);
        }
    }

    public final Class f() {
        Class<? extends k> cls = this.p;
        return cls == null ? com.htsmart.wristband2.dfu.e.class : cls;
    }

    public final void h(int i) {
        Message obtainMessage = this.o.obtainMessage(100);
        obtainMessage.arg1 = i;
        obtainMessage.sendToTarget();
    }

    public final void i(int i, int i2) {
        Message obtainMessage = this.o.obtainMessage(101);
        obtainMessage.arg1 = i;
        obtainMessage.arg2 = i2;
        obtainMessage.sendToTarget();
    }

    public void init() {
        if (this.b == null) {
            this.b = new com.htsmart.wristband2.dfu.c(this.f11987a);
        }
        this.b.a(this.k);
        if (this.c == null) {
            this.c = new com.htsmart.wristband2.dfu.d();
        }
        this.c.a(this.l);
        if (this.d == null) {
            this.d = new com.htsmart.wristband2.dfu.b();
        }
        this.d.a(this.m);
    }

    public boolean isCancelable() {
        return this.j != 4;
    }

    public final void k(String str, boolean z, byte b2) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            i(0, 0);
        } else if (!defaultAdapter.isEnabled()) {
            i(0, 1);
        } else {
            this.g = z;
            h(1);
            this.b.a(str, this.g, b2);
        }
    }

    public void release() {
        this.b.a();
        this.c.a();
        this.d.a();
        k kVar = this.f;
        if (kVar != null) {
            kVar.b();
        }
        this.e = null;
        this.o.removeCallbacksAndMessages(null);
    }

    public void setCustomDfuProcess1Class(Class<? extends k> cls) {
        this.p = cls;
    }

    public void setDfuCallback(DfuCallback dfuCallback) {
        this.e = dfuCallback;
    }

    public void setDfuDeviceFinder(h hVar) {
        this.d = hVar;
    }

    public void setDfuFileChecker(i iVar) {
        this.b = iVar;
    }

    public void setDfuModeChecker(j jVar) {
        this.c = jVar;
    }

    @Deprecated
    public void start(String str, boolean z) {
        k(str, z, (byte) 0);
    }

    public void upgradeDial(String str, byte b2) {
        k(str, false, b2);
    }

    @Deprecated
    public void upgradeFirmware(String str) {
        k(str, true, (byte) 0);
    }

    public void upgradeFirmware(String str, boolean z) {
        k(str, !z, (byte) 0);
    }

    public void upgradeGame(String str, byte b2) {
        k(str, false, b2);
    }

    public void upgradeSportPush(String str, byte b2) {
        k(str, false, b2);
    }
}
