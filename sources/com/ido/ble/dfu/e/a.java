package com.ido.ble.dfu.e;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.clevertap.android.sdk.Constants;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.DeviceUpgradeEventListener;
import com.ido.ble.callback.EnterDfuModeCallback;
import com.ido.ble.dfu.BleDFUConfig;
import com.ido.ble.dfu.DFUService;
import com.ido.ble.dfu.d.a.b;
import com.ido.ble.dfu.d.b.a;
import com.ido.ble.dfu.d.b.b;
import com.ido.ble.dfu.d.c.a;
import com.ido.ble.dfu.e.b.a;
import com.ido.ble.dfu.e.b.b;
import com.ido.ble.dfu.e.b.c;
import com.ido.ble.dfu.e.b.d;
import com.ido.ble.logs.LogTool;
import java.lang.reflect.Method;
import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.dfu.DfuProgressListener;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
import no.nordicsemi.android.dfu.DfuServiceListenerHelper;
/* loaded from: classes11.dex */
public class a {
    private static final int v = 6;
    private static a w;
    private BleDFUConfig b;
    private com.ido.ble.dfu.d.a.c c;
    private com.ido.ble.dfu.e.b.a j;
    private com.ido.ble.dfu.d.a.d k;
    private com.ido.ble.dfu.e.b.c n;
    private com.ido.ble.dfu.d.b.a o;
    private com.ido.ble.dfu.e.b.b p;
    private com.ido.ble.dfu.d.b.b q;
    private DfuProgressListener r;
    private String s;

    /* renamed from: a  reason: collision with root package name */
    private boolean f12180a = false;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private boolean g = false;
    private boolean h = false;
    private int i = 0;
    private boolean l = true;
    private Handler m = new Handler(Looper.getMainLooper());
    private boolean t = false;
    private int u = 0;

    /* renamed from: com.ido.ble.dfu.e.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0588a implements d.b {
        public C0588a() {
        }

        @Override // com.ido.ble.dfu.e.b.d.b
        public void a(int i) {
            a.this.u = i;
            a.this.b(i);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements a.b {
        public b() {
        }

        @Override // com.ido.ble.dfu.e.b.a.b
        public void a() {
            LogTool.b(com.ido.ble.dfu.a.b, "[NodicDFUManager:mCheckDFUResultTask] onDeviceInDfuState     upgradeSuccess();\n");
            a.this.t();
            a.this.c.onSuccessAndNeedToPromptUser();
        }

        @Override // com.ido.ble.dfu.e.b.a.b
        public void b() {
            LogTool.b(com.ido.ble.dfu.a.b, "[NodicDFUManager:mCheckDFUResultTask] onCannotCheckDeviceStatus     upgradeSuccess();\n");
            a.this.t();
            a.this.c.onSuccessAndNeedToPromptUser();
        }

        @Override // com.ido.ble.dfu.e.b.a.b
        public void c() {
            LogTool.b(com.ido.ble.dfu.a.b, "[NodicDFUManager:mCheckDFUResultTask] onDeviceInNormalState    upgradeSuccess();\n");
            a.this.t();
            a.this.c.onSuccess();
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ boolean f12183a;

        /* renamed from: com.ido.ble.dfu.e.a$c$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class RunnableC0589a implements Runnable {
            public RunnableC0589a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                c cVar = c.this;
                a.this.b(cVar.f12183a);
            }
        }

        public c(boolean z) {
            this.f12183a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.o();
            a.this.m.postDelayed(new RunnableC0589a(), 5000L);
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.h();
        }
    }

    /* loaded from: classes11.dex */
    public class e implements a.d {
        public e() {
        }

        @Override // com.ido.ble.dfu.d.c.a.d
        public void a() {
            a.this.c(true);
        }
    }

    /* loaded from: classes11.dex */
    public class f implements c.d {
        public f() {
        }

        @Override // com.ido.ble.dfu.e.b.c.d
        public void a() {
            if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                a.this.s();
                a.this.c.h();
            } else if (!a.this.g()) {
                a.this.b(false);
            } else {
                a aVar = a.this;
                if (aVar.b(aVar.s)) {
                    return;
                }
                a.this.b(false);
            }
        }

        @Override // com.ido.ble.dfu.e.b.c.d
        public void a(BLEDevice bLEDevice) {
            if (com.ido.ble.bluetooth.a.h()) {
                a.this.q();
            } else {
                a.this.a(bLEDevice);
            }
        }

        @Override // com.ido.ble.dfu.e.b.c.d
        public void b(BLEDevice bLEDevice) {
            a.this.s = bLEDevice.mDeviceAddress;
            a.this.t = true;
            a.this.r();
        }

        @Override // com.ido.ble.dfu.e.b.c.d
        public void c(BLEDevice bLEDevice) {
            a.this.a(bLEDevice);
        }
    }

    /* loaded from: classes11.dex */
    public class g implements a.b {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ BLEDevice f12188a;

        public g(BLEDevice bLEDevice) {
            this.f12188a = bLEDevice;
        }

        @Override // com.ido.ble.dfu.d.b.a.b
        public void a() {
            a.this.q();
        }

        @Override // com.ido.ble.dfu.d.b.a.b
        public void b() {
            a.this.l();
        }

        @Override // com.ido.ble.dfu.d.b.a.b
        public void c() {
            a.this.s = this.f12188a.mDeviceAddress;
            a.this.r();
        }
    }

    /* loaded from: classes11.dex */
    public class h implements b.c {
        public h() {
        }

        @Override // com.ido.ble.dfu.e.b.b.c
        public void a() {
            a.this.s();
            a.this.c.d();
        }

        @Override // com.ido.ble.dfu.e.b.b.c
        public void a(EnterDfuModeCallback.DfuError dfuError) {
            a.this.s();
            a.this.c.a(dfuError);
        }

        @Override // com.ido.ble.dfu.e.b.b.c
        public void b() {
            a.this.l();
        }

        @Override // com.ido.ble.dfu.e.b.b.c
        public void onSuccess() {
            a.this.c.onDeviceInDFUMode();
            if (com.ido.ble.bluetooth.a.h()) {
                a.this.p();
            } else {
                a.this.l();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class i implements b.c {
        public i() {
        }

        @Override // com.ido.ble.dfu.d.b.b.c
        public void a() {
            a.this.l();
        }
    }

    /* loaded from: classes11.dex */
    public class j implements b.InterfaceC0583b {
        public j() {
        }

        @Override // com.ido.ble.dfu.d.a.b.InterfaceC0583b
        public void a() {
            a.this.g = true;
            a.this.f();
        }
    }

    /* loaded from: classes11.dex */
    public class k implements DfuProgressListener {
        private k() {
        }

        public /* synthetic */ k(a aVar, b bVar) {
            this();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onDeviceConnected(String str) {
            LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager:DfuProgressListener] onDeviceConnected");
            a.this.k.a();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onDeviceConnecting(String str) {
            LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager:DfuProgressListener] onDeviceConnecting");
            a.this.k.a();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onDeviceDisconnected(String str) {
            LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager:DfuProgressListener] onDeviceDisconnected");
            a.this.k.a();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onDeviceDisconnecting(String str) {
            LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager:DfuProgressListener] onDeviceDisconnecting");
            a.this.k.a();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onDfuAborted(String str) {
            if (!a.this.l) {
                LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager:DfuProgressListener] onDfuAborted");
                a.this.h();
                return;
            }
            LogTool.b(com.ido.ble.dfu.a.b, "[NodicDFUManager:DfuProgressListener] onDfuAborted by nodic-dfu-lib");
            a.this.k.b();
            a.this.a(false);
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onDfuCompleted(String str) {
            LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager:DfuProgressListener] onDfuCompleted");
            a.this.a(100);
            a.this.k.b();
            DfuServiceListenerHelper.unregisterProgressListener(com.ido.ble.common.e.a(), a.this.r);
            a aVar = a.this;
            aVar.a(aVar.b.getMacAddress());
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onDfuProcessStarted(String str) {
            LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager:DfuProgressListener] onDfuProcessStarted");
            a.this.k.a();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onDfuProcessStarting(String str) {
            LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager:DfuProgressListener] onDfuProcessStarting");
            a.this.k.a();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onEnablingDfuMode(String str) {
            LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager:DfuProgressListener] onEnablingDfuMode");
            a.this.k.a();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onError(String str, int i, int i2, String str2) {
            LogTool.b(com.ido.ble.dfu.a.b, "[NodicDFUManager:DfuProgressListener] error=" + i + ", errorType=" + i2 + Constants.SEPARATOR_COMMA + str2);
            a.this.k.b();
            a.this.a(i, i2, str2);
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onFirmwareValidating(String str) {
            LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager:DfuProgressListener] onFirmwareValidating");
            a.this.k.a();
        }

        @Override // no.nordicsemi.android.dfu.DfuProgressListener
        public void onProgressChanged(String str, int i, float f, float f2, int i2, int i3) {
            LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager:DfuProgressListener] onProgressChanged, progress = " + i);
            DeviceUpgradeEventListener.NODIC_onProgress(i);
            if (i <= 99) {
                a.this.a(i);
            }
            if (i == 100) {
                a.this.h = true;
            }
            a.this.k.a();
        }
    }

    private a() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        com.ido.ble.dfu.e.b.d.b().a();
        if (i2 > this.u) {
            this.c.onProgress(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, int i3, String str) {
        if (i2 == 262) {
            s();
            this.c.e();
            return;
        }
        boolean z = true;
        if (i2 != 520 && i2 != 4100) {
            if (i2 == 4102) {
                this.i++;
                this.c.a(i2, str);
                a(z);
            }
            if (i2 != 4108) {
                if (i2 != 4096) {
                    if (i2 != 4097 && i2 != 4105) {
                        if (i2 == 4106) {
                            s();
                            this.c.h();
                            return;
                        }
                    }
                } else if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                    e();
                    return;
                }
            }
            s();
            this.c.j();
            return;
        }
        this.g = true;
        z = false;
        this.c.a(i2, str);
        a(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BLEDevice bLEDevice) {
        LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager] (hasFindDeviceAndToConnectDevice) to connect Device");
        b(bLEDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        LogTool.b(com.ido.ble.dfu.a.b, "to check dfu result:" + str);
        com.ido.ble.dfu.e.b.a aVar = this.j;
        if (aVar != null) {
            aVar.a();
        }
        com.ido.ble.dfu.e.b.a aVar2 = new com.ido.ble.dfu.e.b.a();
        this.j = aVar2;
        aVar2.a(new b(), str);
    }

    private void a(DfuServiceInitiator dfuServiceInitiator, boolean z) {
        Method[] methods;
        String str;
        try {
            for (Method method : dfuServiceInitiator.getClass().getMethods()) {
                if ("disableResume".equals(method.getName()) && z) {
                    method.invoke("disableResume", new Object[0]);
                    str = "[NodicDFUManager] upgrade... initiator.disableResume()";
                } else if ("setForceSendInitFile".equals(method.getName())) {
                    method.invoke(dfuServiceInitiator, Boolean.valueOf(z));
                    str = "[NodicDFUManager] upgrade... setForceSendInitFile" + z;
                }
                LogTool.d(com.ido.ble.dfu.a.b, str);
                return;
            }
        } catch (Exception e2) {
            LogTool.b(com.ido.ble.dfu.a.b, "[NodicDFUManager] upgrade..." + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        j();
        LogTool.b(com.ido.ble.dfu.a.b, "[NodicDFUManager] wait for restart ..." + (this.d + 1));
        m();
        this.m.postDelayed(new c(z), 10000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2) {
        this.c.onProgress(i2);
    }

    private void b(BLEDevice bLEDevice) {
        com.ido.ble.dfu.d.b.a aVar = this.o;
        if (aVar != null) {
            aVar.a();
        }
        com.ido.ble.dfu.d.b.a aVar2 = new com.ido.ble.dfu.d.b.a();
        this.o = aVar2;
        aVar2.a(new g(bLEDevice), bLEDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (!this.f12180a) {
            LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager] is not in doing state, don't reStart.");
        } else if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogTool.b(com.ido.ble.dfu.a.b, "[NodicDFUManager] bluetooth switch is closed, upgrade failed, exit!");
            s();
            this.c.h();
        } else {
            int i2 = this.d + 1;
            this.d = i2;
            if (i2 > this.b.getMaxRetryTime()) {
                LogTool.b(com.ido.ble.dfu.a.b, "[NodicDFUManager] out of max retry times, upgrade failed, exit!");
                s();
                this.c.f();
                return;
            }
            LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager] restart, times is " + this.d);
            this.c.a(this.d);
            int i3 = this.d;
            if (i3 % 2 == 0) {
                l();
            } else if (i3 != 3 || !this.b.isNeedReOpenBluetoothSwitchIfFailed()) {
                c(z);
            } else {
                LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager] findDecive " + this.t + "updatemac:" + this.s);
                if (this.t && this.s.equals(this.b.getMacAddress())) {
                    com.ido.ble.bluetooth.f.e.d(this.s);
                    LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager] removeBondState " + this.s);
                }
                new com.ido.ble.dfu.d.c.a().a(new e());
            }
        }
    }

    private boolean b(BleDFUConfig bleDFUConfig) {
        String str;
        if (bleDFUConfig == null) {
            str = "[NodicDFUManager] mDfuConfig is null";
        } else {
            LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager] dfuConfig is " + com.ido.ble.common.k.a(bleDFUConfig));
            if (TextUtils.isEmpty(bleDFUConfig.getFilePath())) {
                str = "[NodicDFUManager] file path is null";
            } else if (TextUtils.isEmpty(bleDFUConfig.getMacAddress())) {
                str = "[NodicDFUManager] mac address is null";
            } else if (!TextUtils.isEmpty(bleDFUConfig.getDeviceId())) {
                this.b = bleDFUConfig;
                this.s = bleDFUConfig.getMacAddress();
                if (this.b.getMaxRetryTime() == 0) {
                    this.b.setMaxRetryTime(6);
                    return true;
                }
                return true;
            } else {
                str = "[NodicDFUManager] device_id is null";
            }
        }
        LogTool.b(com.ido.ble.dfu.a.b, str);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(String str) {
        LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager] (notFindDeviceAndTryToConnectDirect) to connect device direct");
        BLEDevice c2 = com.ido.ble.f.a.f.b.e().c();
        if (c2 == null || !str.equals(c2.mDeviceAddress)) {
            return false;
        }
        b(c2);
        return true;
    }

    private void c() {
        LogTool.c(com.ido.ble.dfu.a.b, "[NodicDFUManager] cancelDfuAction");
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(com.ido.ble.common.e.a());
        Intent intent = new Intent(DfuBaseService.BROADCAST_ACTION);
        intent.putExtra(DfuBaseService.EXTRA_ACTION, 2);
        localBroadcastManager.sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        if (!z) {
            r();
        } else if (com.ido.ble.bluetooth.a.h()) {
            q();
        } else {
            l();
        }
    }

    public static a d() {
        if (w == null) {
            w = new a();
        }
        return w;
    }

    private void e() {
        LogTool.b(com.ido.ble.dfu.a.b, "[NodicDFUManager] handPhoneBluetoothSwitchOff");
        if (!this.f12180a) {
            LogTool.b(com.ido.ble.dfu.a.b, "[NodicDFUManager] handPhoneBluetoothSwitchOff, mIsDoing = false.");
            return;
        }
        s();
        this.c.h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (!this.f12180a) {
            LogTool.b(com.ido.ble.dfu.a.b, "[NodicDFUManager] handleNoResponseScene, mIsDoing = false.");
        } else if (!this.h) {
            a(false);
        } else {
            LogTool.b(com.ido.ble.dfu.a.b, "[NodicDFUManager] handleNoResponseScene, mOtaFileHasTranFinished = true.");
            a(this.b.getMacAddress());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        int i2 = this.e + 1;
        this.e = i2;
        return i2 <= 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.f12180a) {
            LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager] upgrade canceled, exit!");
            i();
            this.c.onCancel();
            this.k.b();
        }
    }

    private void i() {
        LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager] release");
        com.ido.ble.dfu.e.b.d.b().a();
        this.f12180a = false;
        this.d = 0;
        this.f = 0;
        this.e = 0;
        this.u = 0;
        this.m.removeCallbacksAndMessages(null);
        this.l = true;
        this.i = 0;
        this.h = false;
        this.g = false;
        n();
        DfuServiceListenerHelper.unregisterProgressListener(com.ido.ble.common.e.a(), this.r);
        o();
    }

    private void j() {
        LogTool.c(com.ido.ble.dfu.a.b, "[NodicDFUManager] release to prepare to restart");
        n();
        DfuServiceListenerHelper.unregisterProgressListener(com.ido.ble.common.e.a(), this.r);
        c();
    }

    private void k() {
        BluetoothDevice a2;
        if (com.ido.ble.bluetooth.f.e.b() || (a2 = com.ido.ble.bluetooth.f.e.a(this.b.getMacAddress())) == null) {
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager] remove bond state.");
        com.ido.ble.bluetooth.f.e.b(a2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager] to scan target dfu Device.");
        com.ido.ble.dfu.e.b.c cVar = this.n;
        if (cVar != null) {
            cVar.a();
        }
        com.ido.ble.dfu.e.b.c cVar2 = new com.ido.ble.dfu.e.b.c();
        this.n = cVar2;
        cVar2.a(new f(), this.b.getMacAddress());
    }

    private void m() {
        com.ido.ble.dfu.e.b.d.b().a(new C0588a());
    }

    private void n() {
        com.ido.ble.dfu.e.b.a aVar = this.j;
        if (aVar != null) {
            aVar.a();
            this.j = null;
        }
        com.ido.ble.dfu.d.b.a aVar2 = this.o;
        if (aVar2 != null) {
            aVar2.a();
            this.o = null;
        }
        com.ido.ble.dfu.e.b.b bVar = this.p;
        if (bVar != null) {
            bVar.a();
            this.p = null;
        }
        com.ido.ble.dfu.e.b.c cVar = this.n;
        if (cVar != null) {
            cVar.a();
            this.n = null;
        }
        com.ido.ble.dfu.d.b.b bVar2 = this.q;
        if (bVar2 != null) {
            bVar2.a();
            this.q = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        com.ido.ble.common.e.a().stopService(new Intent(com.ido.ble.common.e.a(), DFUService.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        com.ido.ble.dfu.d.b.b bVar = this.q;
        if (bVar != null) {
            bVar.a();
        }
        com.ido.ble.dfu.d.b.b bVar2 = new com.ido.ble.dfu.d.b.b();
        this.q = bVar2;
        bVar2.a(new i());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager] to enter dfu mode");
        com.ido.ble.dfu.e.b.b bVar = this.p;
        if (bVar != null) {
            bVar.a();
        }
        com.ido.ble.dfu.e.b.b bVar2 = new com.ido.ble.dfu.e.b.b();
        this.p = bVar2;
        bVar2.a(new h());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager] upgrade...mac:" + this.s);
        LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager] upgrade...findDecive:" + this.t);
        if (this.t && this.s.equals(this.b.getMacAddress())) {
            LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager] removeBondState " + this.b.getMacAddress());
            com.ido.ble.bluetooth.f.e.d(this.b.getMacAddress());
        }
        DfuServiceListenerHelper.registerProgressListener(com.ido.ble.common.e.a(), this.r);
        DfuServiceInitiator dfuServiceInitiator = new DfuServiceInitiator(this.s);
        dfuServiceInitiator.setDisableNotification(true);
        dfuServiceInitiator.setZip(this.b.getFilePath());
        dfuServiceInitiator.setKeepBond(com.ido.ble.bluetooth.f.e.b());
        a(dfuServiceInitiator, this.g);
        if (this.b.getPRN() > 0) {
            dfuServiceInitiator.setPacketsReceiptNotificationsEnabled(true);
            dfuServiceInitiator.setPacketsReceiptNotificationsValue(this.b.getPRN());
        }
        dfuServiceInitiator.start(com.ido.ble.common.e.a(), DFUService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            DfuServiceInitiator.createDfuNotificationChannel(com.ido.ble.b.b());
        }
        this.h = false;
        this.k.a(new j());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        LogTool.b(com.ido.ble.dfu.a.b, "[NodicDFUManager] upgrade failed, exit!");
        i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager] upgrade success");
        i();
    }

    public void a() {
        if (this.f12180a) {
            LogTool.d(com.ido.ble.dfu.a.b, "[NodicDFUManager] start to cancel...");
            this.l = false;
            c();
            this.m.postDelayed(new d(), 10000L);
        }
    }

    public boolean a(BleDFUConfig bleDFUConfig) {
        LogTool.c(com.ido.ble.dfu.a.b, "[NodicDFUManager] ----start-------------->");
        if (this.f12180a) {
            LogTool.b(com.ido.ble.dfu.a.b, "[NodicDFUManager] is doing ,ignore this action.");
            return false;
        }
        this.c = new com.ido.ble.dfu.d.a.a(bleDFUConfig);
        this.k = new com.ido.ble.dfu.d.a.b();
        this.r = new k(this, null);
        this.t = false;
        this.c.onPrepare();
        if (!b(bleDFUConfig)) {
            this.c.i();
            return false;
        }
        this.f12180a = true;
        m();
        if (com.ido.ble.bluetooth.a.h()) {
            q();
        } else {
            l();
        }
        return true;
    }

    public boolean b() {
        return this.f12180a;
    }
}
