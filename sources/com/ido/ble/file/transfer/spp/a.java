package com.ido.ble.file.transfer.spp;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.common.k;
import com.ido.ble.file.transfer.IFileTransferListener;
import com.ido.ble.file.transfer.c;
import com.ido.ble.gps.callback.GpsCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.Mp3ToMp3Para;
import com.veryfit.multi.nativeprotocol.Protocol;
import java.io.File;
/* loaded from: classes11.dex */
public class a {
    private static final String p = "FILE_TRANSFER_SPP";
    private static a q = new a();
    private boolean b;
    private com.ido.ble.file.transfer.d c;
    private IFileTransferListener d;
    private SPPFileTransferConfig g;
    private com.ido.ble.bluetooth.d.f h;
    private g i;
    private String j;

    /* renamed from: a  reason: collision with root package name */
    private int f12263a = 0;
    private boolean e = false;
    private final Handler f = new Handler(Looper.getMainLooper());
    private GpsCallBack.IMp3ConvertCallBack k = new C0600a();
    private final GpsCallBack.ISPPTranFileCallBack l = new b();
    private final com.ido.ble.bluetooth.d.f m = new c();
    private DeviceResponseCommonCallBack.ICallBack n = new d();
    public boolean o = false;

    /* renamed from: com.ido.ble.file.transfer.spp.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0600a implements GpsCallBack.IMp3ConvertCallBack {
        public C0600a() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IMp3ConvertCallBack
        public void onConvertFailed() {
            a.this.c();
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IMp3ConvertCallBack
        public void onConvertSuccess() {
            if (!new File(a.this.j).exists()) {
                LogTool.d("FILE_TRANSFER_SPP", "targetFile is not exist");
                a.this.c();
                return;
            }
            a.this.g.filePath = a.this.j;
            LogTool.d("FILE_TRANSFER_SPP", "onConvertSuccess final mFileTransferConfig = " + a.this.g);
            a aVar = a.this;
            aVar.b(aVar.g);
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IMp3ConvertCallBack
        public void onNoNeedConvert() {
            LogTool.d("FILE_TRANSFER_SPP", "onNoNeedConvert final mFileTransferConfig = " + a.this.g);
            a aVar = a.this;
            aVar.b(aVar.g);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements GpsCallBack.ISPPTranFileCallBack {
        public b() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ISPPTranFileCallBack
        public void onFailed(int i) {
            if (a.this.e) {
                if (a.this.c != null) {
                    a.this.c.a();
                }
                a aVar = a.this;
                aVar.b("transfer progress return code = " + i);
            }
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ISPPTranFileCallBack
        public void onFinish() {
            if (a.this.e) {
                if (a.this.c != null) {
                    a.this.c.a();
                }
                a.this.g();
            }
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ISPPTranFileCallBack
        public void onProgress(int i) {
            if (a.this.e) {
                if (a.this.c != null) {
                    a.this.c.a();
                }
                a.this.a(i);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements com.ido.ble.bluetooth.d.f {
        public c() {
        }

        @Override // com.ido.ble.bluetooth.d.f
        public void a() {
            LogTool.b("FILE_TRANSFER_SPP", "spp connect break.");
            if (a.this.h != null) {
                a.this.h.a();
            }
            if (a.this.e) {
                a.this.c("spp connect break.");
            }
        }

        @Override // com.ido.ble.bluetooth.d.f
        public void onFailed() {
            a.this.c("spp connect failed.");
            if (a.this.h != null) {
                a.this.h.onFailed();
            }
        }

        @Override // com.ido.ble.bluetooth.d.f
        public void onStart() {
            LogTool.d("FILE_TRANSFER_SPP", "spp connect start");
            if (a.this.h != null) {
                a.this.h.onStart();
            }
        }

        @Override // com.ido.ble.bluetooth.d.f
        public void onSuccess() {
            LogTool.d("FILE_TRANSFER_SPP", "spp connect success");
            if (a.this.h != null) {
                a.this.h.onStart();
            }
            a.this.d();
        }
    }

    /* loaded from: classes11.dex */
    public class d implements DeviceResponseCommonCallBack.ICallBack {
        public d() {
        }

        @Override // com.ido.ble.callback.DeviceResponseCommonCallBack.ICallBack
        public void onResponse(int i, String str) {
            if (i == 7951 && a.this.o) {
                LogTool.d("FILE_TRANSFER_SPP", "get mtu ok," + str);
                a.this.k();
                a.this.o = false;
            }
        }
    }

    /* loaded from: classes11.dex */
    public class e implements c.b {
        public e() {
        }

        @Override // com.ido.ble.file.transfer.c.b
        public void a() {
            if (a.this.i == g.SPP_CONNECT) {
                a.this.c("spp connect failed.");
            } else if (a.this.i == g.TRANSFER_FILE) {
                a.this.f();
                a.this.b("trans file time out.");
            } else if (a.this.i == g.GET_MTU) {
                a.this.f();
                a.this.k();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.this.d == null || a.this.b) {
                return;
            }
            LogTool.d("FILE_TRANSFER_SPP", "notify success!");
            a.this.d.onProgress(100);
            a.this.d.onSuccess();
            a.this.d = null;
        }
    }

    /* loaded from: classes11.dex */
    public enum g {
        STATE_NULL,
        CHECK_FILE,
        SPP_CONNECT,
        GET_MTU,
        TRANSFER_FILE
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        LogTool.d("FILE_TRANSFER_SPP", "progress = " + i);
        b(i);
    }

    private void b(int i) {
        IFileTransferListener iFileTransferListener = this.d;
        if (iFileTransferListener != null) {
            iFileTransferListener.onProgress(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        int i = this.f12263a;
        if (i >= this.g.maxRetryTimes) {
            LogTool.d("FILE_TRANSFER_SPP", "try to trans again. out of max times.");
            c(str);
            return;
        }
        this.f12263a = i + 1;
        LogTool.b("FILE_TRANSFER_SPP", str);
        LogTool.d("FILE_TRANSFER_SPP", "try to trans again. times = " + this.f12263a);
        k();
    }

    private boolean b() {
        LogTool.d("FILE_TRANSFER_SPP", "check file.");
        this.i = g.CHECK_FILE;
        return new File(this.g.filePath).exists();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        IFileTransferListener iFileTransferListener = this.d;
        if (iFileTransferListener != null) {
            iFileTransferListener.onFailed("mp3 file onConvertFailed");
        } else {
            LogTool.d("FILE_TRANSFER_SPP", "transferListener = null,onConvertFailed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        LogTool.b("FILE_TRANSFER_SPP", str);
        LogTool.d("FILE_TRANSFER_SPP", "transfer failed!");
        h();
        IFileTransferListener iFileTransferListener = this.d;
        if (iFileTransferListener != null) {
            iFileTransferListener.onFailed(str);
            this.d = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogTool.d("FILE_TRANSFER_SPP", "to get mtu.");
        this.o = true;
        this.i = g.GET_MTU;
        com.ido.ble.callback.b.N().b(this.n);
        com.ido.ble.callback.b.N().a(this.n);
        com.ido.ble.i.a.a.d(7951);
    }

    public static a e() {
        return q;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        com.ido.ble.file.transfer.c cVar = new com.ido.ble.file.transfer.c();
        this.c = cVar;
        cVar.a(new e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogTool.d("FILE_TRANSFER_SPP", "transfer file complete.");
        l();
    }

    private void h() {
        LogTool.d("FILE_TRANSFER_SPP", "release.");
        com.ido.ble.gps.callback.a.h().b(this.l);
        com.ido.ble.gps.callback.a.h().b(this.k);
        this.i = g.STATE_NULL;
        this.f.removeCallbacksAndMessages(null);
        this.e = false;
        this.o = false;
        this.f12263a = 0;
        com.ido.ble.file.transfer.d dVar = this.c;
        if (dVar != null) {
            dVar.b();
        }
        this.c = null;
    }

    private void i() {
        this.b = true;
        if (!this.e) {
            this.f.removeCallbacksAndMessages(null);
            LogTool.d("FILE_TRANSFER_SPP", "stop1.");
            return;
        }
        LogTool.d("FILE_TRANSFER_SPP", "stop.");
        u.u();
        h();
    }

    private void j() {
        this.i = g.SPP_CONNECT;
        com.ido.ble.bluetooth.d.c.a(this.m);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogTool.d("FILE_TRANSFER_SPP", "begin transfer file...");
        this.i = g.TRANSFER_FILE;
        if (this.g.PRN <= 0) {
            Protocol.getInstance().tranDatasppSetPRN(50);
            LogTool.d("FILE_TRANSFER_SPP", "tranDatasppSetPRN prn = 50");
        } else {
            Protocol.getInstance().tranDatasppSetPRN(this.g.PRN);
        }
        byte[] a2 = com.ido.ble.common.c.a(this.g.filePath);
        if (a2 == null || a2.length <= 0) {
            c("byte data is null");
        } else {
            SPPFileTransferConfig sPPFileTransferConfig = this.g;
            int a3 = u.a(a2, sPPFileTransferConfig.dataType, sPPFileTransferConfig.firmwareSpecName.getBytes(), this.g.zipType);
            if (a3 != 0) {
                b("tranDataSetBuff return code is " + a3);
            }
        }
        int tranDatasppStart = Protocol.getInstance().tranDatasppStart();
        LogTool.d("FILE_TRANSFER_SPP", "tranDataStart return code = " + tranDatasppStart);
    }

    private void l() {
        LogTool.d("FILE_TRANSFER_SPP", "transfer success!");
        h();
        this.f.postDelayed(new f(), 1000L);
    }

    public void a() {
        this.b = true;
        if (!this.e) {
            this.f.removeCallbacksAndMessages(null);
            LogTool.d("FILE_TRANSFER_SPP", "stopByUser1.");
            return;
        }
        LogTool.d("FILE_TRANSFER_SPP", "stopByUser.");
        Protocol.getInstance().tranDataSppManualStop();
        h();
    }

    public void a(SPPFileTransferConfig sPPFileTransferConfig) {
        this.g = sPPFileTransferConfig;
        this.d = sPPFileTransferConfig.iFileTransferListener;
        if (TextUtils.isEmpty(sPPFileTransferConfig.firmwareSpecName)) {
            LogTool.d("FILE_TRANSFER_SPP", "firmwareSpecName is null");
            IFileTransferListener iFileTransferListener = this.d;
            if (iFileTransferListener != null) {
                iFileTransferListener.onFailed("firmwareSpecName is null");
                return;
            }
            return;
        }
        String[] split = sPPFileTransferConfig.firmwareSpecName.split("\\.");
        if (split.length == 1) {
            LogTool.d("FILE_TRANSFER_SPP", "firmwareSpecName format is wrong");
            IFileTransferListener iFileTransferListener2 = this.d;
            if (iFileTransferListener2 != null) {
                iFileTransferListener2.onFailed("firmwareSpecName format is wrong");
            }
        } else if (!TextUtils.equals(split[split.length - 1], "mp3")) {
            b(sPPFileTransferConfig);
        } else {
            com.ido.ble.gps.callback.a.h().a(this.k);
            a(sPPFileTransferConfig.filePath);
        }
    }

    public void a(SPPFileTransferConfig sPPFileTransferConfig, com.ido.ble.bluetooth.d.f fVar) {
        this.h = fVar;
        a(sPPFileTransferConfig);
    }

    public void a(String str) {
        File file = new File(str);
        if (!file.exists()) {
            LogTool.d("FILE_TRANSFER_SPP", "[mp3ToMp3] file not exists:" + str);
            IFileTransferListener iFileTransferListener = this.d;
            if (iFileTransferListener != null) {
                iFileTransferListener.onFailed("[mp3ToMp3] file not exists:" + str);
                return;
            }
            return;
        }
        this.j = str.replace(file.getName(), "") + "tempMp3File.mp3";
        Mp3ToMp3Para mp3ToMp3Para = new Mp3ToMp3Para();
        mp3ToMp3Para.mp3in = str;
        mp3ToMp3Para.mp3out = this.j;
        LogTool.d("FILE_TRANSFER_SPP", "[mp3ToMp3] " + mp3ToMp3Para.toString());
        int b2 = u.b(com.ido.ble.common.c.b(k.a(mp3ToMp3Para)), (int) com.veryfit.multi.nativeprotocol.b.j4);
        LogTool.d("FILE_TRANSFER_SPP", "[mp3ToMp3] " + b2);
    }

    public void b(SPPFileTransferConfig sPPFileTransferConfig) {
        LogTool.d("FILE_TRANSFER_SPP", "start ... " + sPPFileTransferConfig.toString());
        if (this.e) {
            LogTool.d("FILE_TRANSFER_SPP", "is in staring state, ignore ...");
            return;
        }
        f();
        this.g = sPPFileTransferConfig;
        IFileTransferListener iFileTransferListener = sPPFileTransferConfig.iFileTransferListener;
        this.d = iFileTransferListener;
        iFileTransferListener.onStart();
        if (!b()) {
            c("file is not exist.");
            return;
        }
        this.e = true;
        this.b = false;
        com.ido.ble.gps.callback.a.h().a(this.l);
        if (com.ido.ble.bluetooth.d.c.c()) {
            d();
        } else {
            j();
        }
    }

    public void b(SPPFileTransferConfig sPPFileTransferConfig, com.ido.ble.bluetooth.d.f fVar) {
        this.h = fVar;
        b(sPPFileTransferConfig);
    }
}
