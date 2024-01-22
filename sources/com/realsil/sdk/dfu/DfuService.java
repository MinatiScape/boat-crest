package com.realsil.sdk.dfu;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.realsil.sdk.core.bluetooth.BluetoothProfileCallback;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.a.a;
import com.realsil.sdk.dfu.internal.base.BaseDfuTask;
import com.realsil.sdk.dfu.internal.base.DfuThreadCallback;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.DfuProgressInfo;
import com.realsil.sdk.dfu.model.Throughput;
import com.realsil.sdk.dfu.params.QcConfig;
import java.io.FileDescriptor;
import java.util.HashMap;
import java.util.Locale;
/* loaded from: classes12.dex */
public class DfuService extends Service {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f13589a = true;
    public IBinder b;
    public BaseDfuTask g;
    public BluetoothManager h;
    public BluetoothAdapter i;
    public BluetoothProfileManager j;
    public Throughput m;
    public String c = "";
    public RemoteCallbackList<com.realsil.sdk.dfu.a.b> d = new RemoteCallbackList<>();
    public HashMap<String, com.realsil.sdk.dfu.a.b> e = new HashMap<>();
    public int f = 0;
    public boolean k = false;
    public int l = 257;
    public Handler n = new a(Looper.getMainLooper());
    public BluetoothProfileCallback o = new b();
    public DfuThreadCallback p = new c();

    /* loaded from: classes12.dex */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                DfuService.this.a(1, message.obj);
            } else if (i == 2) {
                DfuService.this.a(2, message.obj);
            } else if (i == 3) {
                DfuService.this.a(3, message.obj);
            } else if (i == 4) {
                DfuService.this.a(4, message.obj);
            }
            super.handleMessage(message);
        }
    }

    /* loaded from: classes12.dex */
    public class b extends BluetoothProfileCallback {
        public b() {
        }

        @Override // com.realsil.sdk.core.bluetooth.BluetoothProfileCallback
        public void onHfpConnectionStateChanged(BluetoothDevice bluetoothDevice, int i) {
            super.onHfpConnectionStateChanged(bluetoothDevice, i);
            if (DfuService.this.f == 1 && i == 2 && DfuService.this.g != null && (DfuService.this.g instanceof com.realsil.sdk.dfu.k.b)) {
                ((com.realsil.sdk.dfu.k.b) DfuService.this.g).a(bluetoothDevice, i);
            }
        }

        @Override // com.realsil.sdk.core.bluetooth.BluetoothProfileCallback
        public void onHidStateChanged(BluetoothDevice bluetoothDevice, int i) {
            super.onHidStateChanged(bluetoothDevice, i);
            if (DfuService.this.f == 0 && i == 2 && DfuService.this.g != null && (DfuService.this.g instanceof com.realsil.sdk.dfu.k.b)) {
                ((com.realsil.sdk.dfu.k.b) DfuService.this.g).a(bluetoothDevice, i);
            }
        }
    }

    /* loaded from: classes12.dex */
    public class c extends DfuThreadCallback {
        public c() {
        }

        @Override // com.realsil.sdk.dfu.internal.base.DfuThreadCallback
        public void onError(int i) {
            super.onError(i);
            DfuService.this.k = false;
            if (DfuService.this.n != null) {
                DfuService.this.n.sendMessage(DfuService.this.n.obtainMessage(2, Integer.valueOf(i)));
            } else {
                ZLogger.v(false, "handle was not initialized");
            }
        }

        @Override // com.realsil.sdk.dfu.internal.base.DfuThreadCallback
        public void onProgressChanged(DfuProgressInfo dfuProgressInfo) {
            super.onProgressChanged(dfuProgressInfo);
            DfuService.this.m = dfuProgressInfo.getThroughput();
            if (DfuService.this.n != null) {
                DfuService.this.n.sendMessage(DfuService.this.n.obtainMessage(3, dfuProgressInfo));
            } else {
                ZLogger.v(false, "handle was not initialized");
            }
        }

        @Override // com.realsil.sdk.dfu.internal.base.DfuThreadCallback
        public void onStateChanged(int i, Throughput throughput) {
            super.onStateChanged(i, throughput);
            DfuService.this.l = i;
            DfuService.this.m = throughput;
            DfuService dfuService = DfuService.this;
            dfuService.k = (dfuService.l & 512) == 512;
            if (DfuService.this.n != null) {
                DfuService.this.n.sendMessage(DfuService.this.n.obtainMessage(1, Integer.valueOf(i)));
            } else {
                ZLogger.v(false, "handle was not initialized");
            }
        }
    }

    public boolean abort() {
        BaseDfuTask baseDfuTask = this.g;
        if (baseDfuTask != null) {
            baseDfuTask.abort();
            return true;
        }
        return true;
    }

    public boolean activeImage(boolean z) {
        BaseDfuTask baseDfuTask = this.g;
        return baseDfuTask != null && baseDfuTask.activeImage(z);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (f13589a) {
            ZLogger.v("onBind");
        }
        return this.b;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.b = new d(this);
        BluetoothProfileManager bluetoothProfileManager = BluetoothProfileManager.getInstance();
        this.j = bluetoothProfileManager;
        if (bluetoothProfileManager == null) {
            BluetoothProfileManager.initial(this);
            this.j = BluetoothProfileManager.getInstance();
        }
        BluetoothProfileManager bluetoothProfileManager2 = this.j;
        if (bluetoothProfileManager2 != null) {
            bluetoothProfileManager2.addManagerCallback(this.o);
        } else {
            ZLogger.d("BluetoothProfileManager not initialized");
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        if (f13589a) {
            ZLogger.v("onDestroy()+");
        }
        this.k = false;
        this.l = 257;
        BluetoothProfileManager bluetoothProfileManager = this.j;
        if (bluetoothProfileManager != null) {
            bluetoothProfileManager.removeManagerCallback(this.o);
        }
        if (f13589a) {
            ZLogger.v("onDestroy()-");
        }
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        if (f13589a) {
            ZLogger.d("onUnbind");
        }
        return super.onUnbind(intent);
    }

    public boolean start(String str, DfuConfig dfuConfig, QcConfig qcConfig) {
        if (str == null) {
            ZLogger.w("the packageName is null");
            return false;
        } else if (dfuConfig == null) {
            ZLogger.w("dfuConfig can not be null");
            return false;
        } else {
            boolean z = this.k;
            if (z && (this.l & 512) == 512) {
                ZLogger.w(String.format(Locale.US, "isInOtaProcess=%b, mProcessState=0x%04X", Boolean.valueOf(z), Integer.valueOf(this.l)));
                return false;
            } else if (!a()) {
                ZLogger.w("initialize failed");
                return false;
            } else if (this.e.get(str) == null) {
                ZLogger.w("didn't find the special callback in the service");
                return false;
            } else {
                this.l = 257;
                this.m = null;
                this.c = str;
                int channelType = dfuConfig.getChannelType();
                this.f = channelType;
                ZLogger.v(String.format("channelType=0X%02X, protocolType=0X%04X,workMode=0x%02X", Integer.valueOf(channelType), Integer.valueOf(dfuConfig.getProtocolType()), Integer.valueOf(dfuConfig.getOtaWorkMode())));
                BaseDfuTask a2 = com.realsil.sdk.dfu.j.a.a(this, dfuConfig, qcConfig, this.p);
                this.g = a2;
                if (a2 == null) {
                    return false;
                }
                a2.start();
                return true;
            }
        }
    }

    /* loaded from: classes12.dex */
    public class d extends a.AbstractBinderC0719a {

        /* renamed from: a  reason: collision with root package name */
        public DfuService f13593a;

        public d(DfuService dfuService) {
            this.f13593a = dfuService;
        }

        @Override // com.realsil.sdk.dfu.a.a
        public boolean a(String str, DfuConfig dfuConfig, QcConfig qcConfig) {
            DfuService e = e();
            return e != null && e.start(str, dfuConfig, qcConfig);
        }

        @Override // com.realsil.sdk.dfu.a.a
        public boolean b(String str, com.realsil.sdk.dfu.a.b bVar) {
            if (bVar != null) {
                ZLogger.v("registerCallback: " + str);
                DfuService.this.d.register(bVar);
                DfuService.this.e.put(str, bVar);
                return DfuService.this.e.get(str) != null;
            }
            return false;
        }

        @Override // com.realsil.sdk.dfu.a.a
        public boolean c() {
            DfuService e = e();
            return e != null && e.abort();
        }

        @Override // android.os.Binder, android.os.IBinder
        public void dump(FileDescriptor fileDescriptor, String[] strArr) {
        }

        @Override // android.os.Binder, android.os.IBinder
        public void dumpAsync(FileDescriptor fileDescriptor, String[] strArr) {
        }

        public final DfuService e() {
            DfuService dfuService = this.f13593a;
            if (dfuService != null) {
                return dfuService;
            }
            return null;
        }

        @Override // android.os.Binder, android.os.IBinder
        public String getInterfaceDescriptor() {
            return null;
        }

        @Override // android.os.Binder, android.os.IBinder
        public boolean isBinderAlive() {
            return false;
        }

        @Override // android.os.Binder, android.os.IBinder
        public void linkToDeath(IBinder.DeathRecipient deathRecipient, int i) {
        }

        @Override // android.os.Binder, android.os.IBinder
        public boolean pingBinder() {
            return false;
        }

        @Override // android.os.Binder, android.os.IBinder
        public IInterface queryLocalInterface(String str) {
            return null;
        }

        @Override // android.os.Binder, android.os.IBinder
        public boolean unlinkToDeath(IBinder.DeathRecipient deathRecipient, int i) {
            return false;
        }

        @Override // com.realsil.sdk.dfu.a.a
        public boolean a(boolean z) {
            DfuService e = e();
            return e != null && e.activeImage(z);
        }

        @Override // com.realsil.sdk.dfu.a.a
        public void a(String str, com.realsil.sdk.dfu.a.b bVar) {
            if (bVar != null) {
                ZLogger.v("unregisterCallback: " + str);
                DfuService.this.d.unregister(bVar);
                DfuService.this.e.remove(str);
            }
        }

        @Override // com.realsil.sdk.dfu.a.a
        public Throughput b() {
            return DfuService.this.m;
        }

        @Override // com.realsil.sdk.dfu.a.a
        public int a() {
            return DfuService.this.l;
        }
    }

    public final void a(int i, Object obj) {
        com.realsil.sdk.dfu.a.b bVar = this.e.get(this.c);
        if (bVar == null) {
            return;
        }
        this.d.beginBroadcast();
        try {
        } catch (RemoteException e) {
            ZLogger.e(e.toString());
        }
        if (i == 1) {
            bVar.a(((Integer) obj).intValue());
        } else if (i == 2) {
            bVar.b(((Integer) obj).intValue());
        } else {
            if (i == 3) {
                bVar.a((DfuProgressInfo) obj);
            }
            this.d.finishBroadcast();
        }
        this.d.finishBroadcast();
    }

    public final boolean a() {
        if (this.h == null) {
            BluetoothManager bluetoothManager = (BluetoothManager) getSystemService("bluetooth");
            this.h = bluetoothManager;
            if (bluetoothManager == null) {
                ZLogger.w("Unable to initialize BluetoothManager.");
                return false;
            }
        }
        BluetoothAdapter adapter = this.h.getAdapter();
        this.i = adapter;
        if (adapter == null) {
            ZLogger.w("Unable to obtain a BluetoothAdapter.");
            return false;
        }
        boolean z = RtkDfu.DEBUG_ENABLE;
        f13589a = z;
        if (z) {
            ZLogger.v("initialize success");
            return true;
        }
        return true;
    }
}
