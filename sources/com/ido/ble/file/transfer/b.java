package com.ido.ble.file.transfer;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.callback.DeviceGattCallBack;
import com.ido.ble.callback.DeviceUpgradeEventListener;
import com.ido.ble.common.k;
import com.ido.ble.file.transfer.c;
import com.ido.ble.gps.callback.GpsCallBack;
import com.ido.ble.gps.model.ConnParam;
import com.ido.ble.gps.model.ConnParamReply;
import com.ido.ble.gps.model.ControlGpsReply;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.Mp3ToMp3Para;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.veryfit.multi.nativeprotocol.Protocol;
import java.io.File;
/* loaded from: classes11.dex */
public class b {
    private static final int A = 3;
    private static final int B = 5000;
    private static b C = new b();
    private static final String w = "FILE_TRANSFER";
    private static final int x = 1000;
    private static final int y = 10;
    private static final int z = 3;
    private boolean g;
    private boolean h;
    private com.ido.ble.file.transfer.d j;
    private IFileTransferListener k;
    private j n;
    private FileTransferConfig o;
    private String p;

    /* renamed from: a  reason: collision with root package name */
    private int f12249a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private boolean i = false;
    private boolean l = false;
    private Handler m = new Handler(Looper.getMainLooper());
    private String q = "";
    private GpsCallBack.IMp3ConvertCallBack r = new a();
    private DeviceGattCallBack.ICallBack s = new C0599b();
    private GpsCallBack.ITranAgpsFileCallBack t = new c();
    private GpsCallBack.IDeviceReplySetGpsCallBack u = new d();
    private Runnable v = new i();

    /* loaded from: classes11.dex */
    public class a implements GpsCallBack.IMp3ConvertCallBack {
        public a() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IMp3ConvertCallBack
        public void onConvertFailed() {
            b.this.f();
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IMp3ConvertCallBack
        public void onConvertSuccess() {
            if (!new File(b.this.p).exists()) {
                LogTool.d("FILE_TRANSFER", "targetFile is not exist");
                b.this.f();
                return;
            }
            b.this.o.filePath = b.this.p;
            LogTool.d("FILE_TRANSFER", "onConvertSuccess final mFileTransferConfig = " + b.this.o);
            b bVar = b.this;
            bVar.b(bVar.o);
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IMp3ConvertCallBack
        public void onNoNeedConvert() {
            LogTool.d("FILE_TRANSFER", "onNoNeedConvert final mFileTransferConfig = " + b.this.o);
            b bVar = b.this;
            bVar.b(bVar.o);
        }
    }

    /* renamed from: com.ido.ble.file.transfer.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0599b implements DeviceGattCallBack.ICallBack {
        public C0599b() {
        }

        @Override // com.ido.ble.callback.DeviceGattCallBack.ICallBack
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (bluetoothGattCharacteristic != null && bluetoothGattCharacteristic.getValue() != null && bluetoothGattCharacteristic.getValue().length > 0 && (bluetoothGattCharacteristic.getValue()[0] & 255) == 209) {
                b.this.j.a();
            }
        }

        @Override // com.ido.ble.callback.DeviceGattCallBack.ICallBack
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        }
    }

    /* loaded from: classes11.dex */
    public class c implements GpsCallBack.ITranAgpsFileCallBack {
        public c() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFailed(int i) {
            if (b.this.l) {
                DeviceUpgradeEventListener.APOLLO_onSOLibError(i);
                b.this.j.a();
                b bVar = b.this;
                bVar.q = "transfer progress return code = " + i;
                b bVar2 = b.this;
                bVar2.b(bVar2.q);
            }
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFailed(int i, Object obj) {
            if (b.this.l) {
                DeviceUpgradeEventListener.APOLLO_onSOLibError(i);
                b.this.j.a();
                b bVar = b.this;
                bVar.q = "transfer progress return code = " + i + ",value = " + obj;
                b bVar2 = b.this;
                bVar2.c(bVar2.q);
            }
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFinish() {
            if (b.this.l) {
                b.this.j.a();
                b.this.i();
            }
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onProgress(int i) {
            if (b.this.l) {
                b.this.j.a();
                b.this.a(i);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class d implements GpsCallBack.IDeviceReplySetGpsCallBack {

        /* loaded from: classes11.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.n();
            }
        }

        public d() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onControlGps(ControlGpsReply controlGpsReply) {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onSetConfigGps(boolean z) {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onSetConnParam(ConnParamReply connParamReply) {
            if (b.this.l) {
                if (!b.this.i) {
                    LogTool.d("FILE_TRANSFER", "handleSetConnParamReply");
                    b.this.a(connParamReply);
                } else if (connParamReply.currMode == 2) {
                    LogTool.d("FILE_TRANSFER", "currMode == 2 , set slow success. callback failed for end");
                    b.this.q();
                } else {
                    LogTool.d("FILE_TRANSFER", "currMode != 2 , setTransferSpeedToSlowForEnd");
                    b.this.m.postDelayed(new a(), 1000L);
                }
            }
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onSetHotStartGpsPara(boolean z) {
        }
    }

    /* loaded from: classes11.dex */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.c();
        }
    }

    /* loaded from: classes11.dex */
    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.d();
        }
    }

    /* loaded from: classes11.dex */
    public class g implements c.b {
        public g() {
        }

        @Override // com.ido.ble.file.transfer.c.b
        public void a() {
            if (b.this.n == j.SET_FAST_SPEED) {
                b.this.h();
                b.this.l();
            } else if (b.this.n == j.CHECK_FAST_SPEED_STATE) {
                b.this.h();
                b.this.c();
            } else if (b.this.n == j.TRANSFER_FILE) {
                b.this.h();
                b.this.b("trans file time out.");
            } else if (b.this.n == j.SET_SLOW_SPEED) {
                b.this.h();
                b.this.m();
            } else if (b.this.n == j.CHECK_SLOW_SPEED_STATE) {
                b.this.h();
                b.this.d();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class h implements Runnable {
        public h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (b.this.k == null || b.this.g) {
                return;
            }
            LogTool.d("FILE_TRANSFER", "notify success!");
            b.this.k.onProgress(100);
            b.this.k.onSuccess();
            b.this.k = null;
        }
    }

    /* loaded from: classes11.dex */
    public class i implements Runnable {
        public i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LogTool.d("FILE_TRANSFER", "set slow transfer mode.  speedToSlowtimeOutRunnable");
            b.this.r();
        }
    }

    /* loaded from: classes11.dex */
    public enum j {
        STATE_NULL,
        CHECK_FILE,
        SET_FAST_SPEED,
        CHECK_FAST_SPEED_STATE,
        TRANSFER_FILE,
        SET_SLOW_SPEED,
        CHECK_SLOW_SPEED_STATE
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        LogTool.d("FILE_TRANSFER", "progress = " + i2);
        b(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ConnParamReply connParamReply) {
        Handler handler;
        Runnable fVar;
        String str;
        String str2;
        this.j.a();
        if (connParamReply != null) {
            LogTool.d("FILE_TRANSFER", connParamReply.toString());
        }
        j jVar = this.n;
        if (jVar == j.SET_FAST_SPEED) {
            if (connParamReply == null) {
                str2 = "set fast transfer mode failed, return info is null, try to set again";
            } else if (connParamReply.errorCode == 0) {
                c();
                return;
            } else {
                str2 = "set fast transfer mode return invalid code = " + connParamReply.errorCode + ",try to set again";
            }
            LogTool.b("FILE_TRANSFER", str2);
            l();
            return;
        }
        if (jVar == j.CHECK_FAST_SPEED_STATE) {
            if (connParamReply != null && connParamReply.currMode == 1) {
                LogTool.d("FILE_TRANSFER", "set fast transfer mode ok.");
                p();
                return;
            }
            handler = this.m;
            fVar = new e();
        } else if (jVar == j.SET_SLOW_SPEED) {
            if (connParamReply == null) {
                str = "set slow transfer mode failed, return info is null, try to set again";
            } else if (connParamReply.errorCode == 0) {
                d();
                return;
            } else {
                str = "set slow transfer mode return invalid code = " + connParamReply.errorCode + ",try to set again";
            }
            LogTool.b("FILE_TRANSFER", str);
            m();
            return;
        } else if (jVar != j.CHECK_SLOW_SPEED_STATE) {
            return;
        } else {
            if (connParamReply != null && connParamReply.currMode == 2) {
                LogTool.d("FILE_TRANSFER", "set slow transfer mode ok.");
                r();
                return;
            }
            handler = this.m;
            fVar = new f();
        }
        handler.postDelayed(fVar, 1000L);
    }

    private void b(int i2) {
        IFileTransferListener iFileTransferListener = this.k;
        if (iFileTransferListener != null) {
            iFileTransferListener.onProgress(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        int i2 = this.f;
        if (i2 >= this.o.maxRetryTimes) {
            LogTool.d("FILE_TRANSFER", "try to trans again. out of max times.");
            c(str);
            return;
        }
        this.f = i2 + 1;
        LogTool.b("FILE_TRANSFER", str);
        LogTool.d("FILE_TRANSFER", "try to trans again. times = " + this.f);
        if (!this.h) {
            u.A();
        }
        p();
    }

    private boolean b() {
        LogTool.d("FILE_TRANSFER", "check file.");
        this.n = j.CHECK_FILE;
        return new File(this.o.filePath).exists();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        int i2 = this.f12249a;
        if (i2 > 10) {
            LogTool.b("FILE_TRANSFER", "check fast speed mode times out of max times!");
            LogTool.b("FILE_TRANSFER", "force transfer file...");
            p();
            return;
        }
        this.f12249a = i2 + 1;
        LogTool.d("FILE_TRANSFER", "check fast speed state.");
        this.n = j.CHECK_FAST_SPEED_STATE;
        ConnParam connParam = new ConnParam();
        connParam.mode = 0;
        com.ido.ble.h.a.a(connParam);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        LogTool.b("FILE_TRANSFER", str);
        this.q = str;
        this.i = true;
        this.e = 0;
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (!com.ido.ble.bluetooth.a.h()) {
            LogTool.b("FILE_TRANSFER", "check slow transfer mode failed. ble is disconnect");
            r();
            return;
        }
        int i2 = this.c;
        if (i2 > 3) {
            LogTool.b("FILE_TRANSFER", "check slow transfer mode out of max times.");
            r();
            return;
        }
        this.c = i2 + 1;
        LogTool.d("FILE_TRANSFER", "check slow transfer mode.");
        this.n = j.CHECK_SLOW_SPEED_STATE;
        Handler handler = this.m;
        if (handler != null) {
            handler.removeCallbacks(this.v);
        }
        this.m.postDelayed(this.v, 5000L);
        ConnParam connParam = new ConnParam();
        connParam.mode = 0;
        com.ido.ble.h.a.a(connParam);
    }

    private void e() {
        int i2 = this.b;
        if (i2 > 3) {
            LogTool.b("FILE_TRANSFER", "check speed mode times for end out of max times!");
            LogTool.b("FILE_TRANSFER", "force transfer failed for end.");
            q();
            return;
        }
        this.b = i2 + 1;
        LogTool.d("FILE_TRANSFER", "check speed mode for end.");
        ConnParam connParam = new ConnParam();
        connParam.mode = 0;
        com.ido.ble.h.a.a(connParam);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        IFileTransferListener iFileTransferListener = this.k;
        if (iFileTransferListener != null) {
            iFileTransferListener.onFailed("mp3 file onConvertFailed");
        } else {
            LogTool.d("FILE_TRANSFER", "transferListener = null,onConvertFailed");
        }
    }

    public static b g() {
        return C;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        com.ido.ble.file.transfer.c cVar = new com.ido.ble.file.transfer.c();
        this.j = cVar;
        cVar.a(new g());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogTool.d("FILE_TRANSFER", "transfer file complete.");
        if (this.o.isNeedChangeSpeedMode) {
            m();
        } else {
            r();
        }
    }

    private void j() {
        com.ido.ble.callback.b.N().a(this.s);
    }

    private void k() {
        LogTool.d("FILE_TRANSFER", "release.");
        com.ido.ble.gps.callback.a.h().b(this.t);
        com.ido.ble.gps.callback.a.h().b(this.u);
        com.ido.ble.gps.callback.a.h().b(this.r);
        com.ido.ble.callback.b.N().b(this.s);
        this.n = j.STATE_NULL;
        this.m.removeCallbacksAndMessages(null);
        this.l = false;
        this.h = false;
        this.i = false;
        this.c = 0;
        this.f12249a = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.b = 0;
        this.q = "";
        this.j.b();
        this.j = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        int i2 = this.d;
        if (i2 > 3) {
            LogTool.b("FILE_TRANSFER", "set fast speed mode times out of max times!");
            LogTool.b("FILE_TRANSFER", "force transfer file...");
            p();
            return;
        }
        this.d = i2 + 1;
        LogTool.d("FILE_TRANSFER", "set fast transfer mode.");
        this.n = j.SET_FAST_SPEED;
        ConnParam connParam = new ConnParam();
        connParam.mode = 1;
        com.ido.ble.h.a.a(connParam);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (!com.ido.ble.bluetooth.a.h()) {
            LogTool.b("FILE_TRANSFER", "set slow transfer mode failed. ble is disconnect");
            r();
            return;
        }
        int i2 = this.e;
        if (i2 > 3) {
            LogTool.b("FILE_TRANSFER", "set slow transfer mode out of max times.");
            r();
            return;
        }
        this.e = i2 + 1;
        Handler handler = this.m;
        if (handler != null) {
            handler.removeCallbacks(this.v);
        }
        this.m.postDelayed(this.v, 5000L);
        LogTool.d("FILE_TRANSFER", "set slow transfer mode.");
        this.n = j.SET_SLOW_SPEED;
        ConnParam connParam = new ConnParam();
        connParam.mode = 2;
        com.ido.ble.h.a.a(connParam);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (!com.ido.ble.bluetooth.a.h()) {
            LogTool.b("FILE_TRANSFER", "set slow transfer mode failed for end. ble is disconnect");
            q();
            return;
        }
        int i2 = this.e;
        if (i2 > 3) {
            LogTool.b("FILE_TRANSFER", "set slow transfer mode out of max times for end.");
            q();
            return;
        }
        this.e = i2 + 1;
        LogTool.d("FILE_TRANSFER", "set slow transfer mode for end.");
        ConnParam connParam = new ConnParam();
        connParam.mode = 2;
        com.ido.ble.h.a.a(connParam);
    }

    private void o() {
        this.g = true;
        if (!this.l) {
            this.m.removeCallbacksAndMessages(null);
            LogTool.d("FILE_TRANSFER", "stop1.");
            return;
        }
        LogTool.d("FILE_TRANSFER", "stop.");
        if (!this.h) {
            u.A();
        }
        k();
    }

    private void p() {
        Protocol protocol;
        int i2;
        LogTool.d("FILE_TRANSFER", "begin transfer file...");
        this.n = j.TRANSFER_FILE;
        if (this.o.PRN <= 0) {
            protocol = Protocol.getInstance();
            i2 = 10;
        } else {
            protocol = Protocol.getInstance();
            i2 = this.o.PRN;
        }
        protocol.tranDataSetPRN(i2);
        byte[] a2 = com.ido.ble.common.c.a(this.o.filePath);
        if (a2 == null || a2.length <= 0) {
            c("byte data is null");
        } else {
            FileTransferConfig fileTransferConfig = this.o;
            int i3 = fileTransferConfig.dataType;
            byte[] bytes = fileTransferConfig.firmwareSpecName.getBytes();
            FileTransferConfig fileTransferConfig2 = this.o;
            int a3 = u.a(a2, i3, bytes, fileTransferConfig2.zipType, fileTransferConfig2.oriSize);
            if (a3 != 0) {
                DeviceUpgradeEventListener.APOLLO_onSOLibError(a3);
                b("tranDataSetBuff return code is " + a3);
            }
        }
        int tranDataStart = Protocol.getInstance().tranDataStart();
        LogTool.d("FILE_TRANSFER", "tranDataStart return code = " + tranDataStart);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        LogTool.d("FILE_TRANSFER", "transfer failed!");
        if (!this.h) {
            u.A();
        }
        IFileTransferListener iFileTransferListener = this.k;
        if (iFileTransferListener != null) {
            iFileTransferListener.onFailed(this.q);
            this.k = null;
        }
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        LogTool.d("FILE_TRANSFER", "transfer success!");
        if (!this.h) {
            u.A();
        }
        this.m.removeCallbacks(this.v);
        k();
        this.m.postDelayed(new h(), 1000L);
    }

    public void a() {
        this.g = true;
        if (!this.l) {
            this.m.removeCallbacksAndMessages(null);
            LogTool.d("FILE_TRANSFER", "stopByUser1.");
            return;
        }
        LogTool.d("FILE_TRANSFER", "stopByUser.");
        Protocol.getInstance().tranDataManualStop();
        k();
    }

    public void a(FileTransferConfig fileTransferConfig) {
        String str;
        this.o = fileTransferConfig;
        this.k = fileTransferConfig.iFileTransferListener;
        if (TextUtils.isEmpty(fileTransferConfig.firmwareSpecName)) {
            str = "firmwareSpecName is null";
        } else {
            String[] split = fileTransferConfig.firmwareSpecName.split("\\.");
            if (split.length != 1) {
                if (!TextUtils.equals(split[split.length - 1], "mp3")) {
                    b(fileTransferConfig);
                    return;
                }
                com.ido.ble.gps.callback.a.h().a(this.r);
                a(fileTransferConfig.filePath);
                return;
            }
            str = "firmwareSpecName format is wrong";
        }
        LogTool.d("FILE_TRANSFER", str);
    }

    public void a(String str) {
        String str2;
        File file = new File(str);
        if (file.exists()) {
            this.p = str.replace(file.getName(), "") + "tempMp3File.mp3";
            Mp3ToMp3Para mp3ToMp3Para = new Mp3ToMp3Para();
            mp3ToMp3Para.mp3in = str;
            mp3ToMp3Para.mp3out = this.p;
            mp3ToMp3Para.size = (int) file.length();
            LogTool.d("FILE_TRANSFER", "[mp3ToMp3] " + mp3ToMp3Para.toString());
            str2 = "[mp3ToMp3] " + u.b(com.ido.ble.common.c.b(k.a(mp3ToMp3Para)), (int) com.veryfit.multi.nativeprotocol.b.j4);
        } else {
            str2 = "[mp3ToMp3] file not exists:" + str;
        }
        LogTool.d("FILE_TRANSFER", str2);
    }

    public void b(FileTransferConfig fileTransferConfig) {
        LogTool.d("FILE_TRANSFER", "start ... " + fileTransferConfig.toString());
        if (this.l) {
            LogTool.d("FILE_TRANSFER", "is in staring state, ignore ...");
            return;
        }
        SupportFunctionInfo Z = com.ido.ble.f.a.f.a.g0().Z();
        if (Z != null) {
            this.h = Z.V3_support_data_tran_continue;
            LogTool.d("FILE_TRANSFER", "isSupportBreakPoint = " + this.h);
        }
        j();
        h();
        this.o = fileTransferConfig;
        IFileTransferListener iFileTransferListener = fileTransferConfig.iFileTransferListener;
        this.k = iFileTransferListener;
        iFileTransferListener.onStart();
        if (!b()) {
            this.q = "file is not exist.";
            LogTool.d("FILE_TRANSFER", "file is not exist.");
            q();
            return;
        }
        this.l = true;
        this.g = false;
        com.ido.ble.gps.callback.a.h().a(this.t);
        com.ido.ble.gps.callback.a.h().a(this.u);
        l();
    }
}
