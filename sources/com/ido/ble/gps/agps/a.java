package com.ido.ble.gps.agps;

import android.os.Handler;
import android.os.Looper;
import com.clevertap.android.sdk.Constants;
import com.ido.ble.gps.callback.GpsCallBack;
import com.ido.ble.gps.model.ConnParam;
import com.ido.ble.gps.model.ConnParamReply;
import com.ido.ble.gps.model.ControlGps;
import com.ido.ble.gps.model.ControlGpsReply;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.veryfit.multi.nativeprotocol.Protocol;
import java.io.File;
/* loaded from: classes11.dex */
public class a {
    private static final String m = "A_GPS_FILE_TRANSLATE";
    private static final int n = 1000;
    private static final int o = 5;
    private static final int p = 1500;
    private static final int q = 30;
    private static a r = new a();
    private IAGpsTranslateStateListener d;
    private String e;
    private int f;
    private g i;
    private AgpsFileTransConfig j;

    /* renamed from: a  reason: collision with root package name */
    private int f12289a = 0;
    private int b = 0;
    private int c = 0;
    private boolean g = false;
    private Handler h = new Handler(Looper.getMainLooper());
    private GpsCallBack.ITranAgpsFileCallBack k = new C0604a();
    private GpsCallBack.IDeviceReplySetGpsCallBack l = new b();

    /* renamed from: com.ido.ble.gps.agps.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0604a implements GpsCallBack.ITranAgpsFileCallBack {
        public C0604a() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFailed(int i) {
            a aVar = a.this;
            aVar.a("translate progress return code = " + i);
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFailed(int i, Object obj) {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFinish() {
            a.this.g();
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onProgress(int i) {
            a.this.a(i);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements GpsCallBack.IDeviceReplySetGpsCallBack {
        public b() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onControlGps(ControlGpsReply controlGpsReply) {
            a.this.a(controlGpsReply);
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onSetConfigGps(boolean z) {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onSetConnParam(ConnParamReply connParamReply) {
            a.this.a(connParamReply);
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onSetHotStartGpsPara(boolean z) {
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.c();
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.d();
        }
    }

    /* loaded from: classes11.dex */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.e();
        }
    }

    /* loaded from: classes11.dex */
    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LogTool.d(a.m, "translate success!");
            a.this.d.onProgress(100);
            a.this.d.onSuccess();
            a.this.h();
        }
    }

    /* loaded from: classes11.dex */
    public enum g {
        STATE_NULL,
        CHECK_FILE,
        SET_FAST_SPEED,
        CHECK_FAST_SPEED_STATE,
        TRANSLATE_FILE,
        WRITE_CHIP,
        CHECK_WRITE_CHIP_STATE,
        SET_SLOW_SPEED,
        CHECK_SLOW_SPEED_STATE
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (i <= 99) {
            b(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ConnParamReply connParamReply) {
        Handler handler;
        Runnable dVar;
        StringBuilder sb;
        String str;
        String str2;
        if (connParamReply != null) {
            LogTool.d(m, connParamReply.toString());
        }
        g gVar = this.i;
        if (gVar != g.SET_FAST_SPEED) {
            if (gVar == g.CHECK_FAST_SPEED_STATE) {
                if (connParamReply != null && connParamReply.currMode == 1) {
                    LogTool.d(m, "set fast translate mode ok.");
                    k();
                    return;
                }
                handler = this.h;
                dVar = new c();
            } else if (gVar == g.SET_SLOW_SPEED) {
                if (connParamReply == null) {
                    str2 = "set slow translate mode failed, return info is null";
                } else if (connParamReply.errorCode == 0) {
                    d();
                    return;
                } else {
                    sb = new StringBuilder();
                    str = "set slow translate mode return invalid code = ";
                    sb.append(str);
                    sb.append(connParamReply.errorCode);
                    str2 = sb.toString();
                }
            } else if (gVar != g.CHECK_SLOW_SPEED_STATE) {
                return;
            } else {
                if (connParamReply != null && connParamReply.currMode == 2) {
                    LogTool.d(m, "set slow translate mode ok.");
                    l();
                    return;
                }
                handler = this.h;
                dVar = new d();
            }
            handler.postDelayed(dVar, 1000L);
            return;
        } else if (connParamReply == null) {
            str2 = "set fast translate mode failed, return info is null";
        } else if (connParamReply.errorCode == 0) {
            c();
            return;
        } else {
            sb = new StringBuilder();
            str = "set fast translate mode return invalid code = ";
            sb.append(str);
            sb.append(connParamReply.errorCode);
            str2 = sb.toString();
        }
        a(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ControlGpsReply controlGpsReply) {
        StringBuilder sb;
        String sb2;
        if (controlGpsReply != null) {
            LogTool.d(m, controlGpsReply.toString());
        }
        g gVar = this.i;
        if (gVar == g.WRITE_CHIP) {
            if (controlGpsReply == null) {
                sb2 = "write chip failed, return info is null.";
                a(sb2);
            } else if (controlGpsReply.errorCode == 0) {
                e();
                return;
            } else {
                sb = new StringBuilder();
            }
        } else if (gVar != g.CHECK_WRITE_CHIP_STATE) {
            return;
        } else {
            if (controlGpsReply == null || controlGpsReply.status != 2) {
                this.h.postDelayed(new e(), 1500L);
                return;
            } else if (controlGpsReply.errorCode == 0) {
                LogTool.d(m, "write chip ok.");
                j();
                return;
            } else {
                sb = new StringBuilder();
            }
        }
        sb.append("write chip return invalid code = ");
        sb.append(controlGpsReply.errorCode);
        sb2 = sb.toString();
        a(sb2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        LogTool.b(m, str);
        this.d.onFailed(str);
        h();
    }

    private void b(int i) {
        this.d.onProgress(i);
    }

    private boolean b() {
        LogTool.d(m, "check aGps file.");
        this.i = g.CHECK_FILE;
        return new File(this.e).exists();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        int i = this.f12289a;
        if (i > 30) {
            a("check fast speed mode times out of max times!");
            return;
        }
        this.f12289a = i + 1;
        LogTool.d(m, "check fast speed state.");
        this.i = g.CHECK_FAST_SPEED_STATE;
        ConnParam connParam = new ConnParam();
        connParam.mode = 0;
        com.ido.ble.h.a.a(connParam);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        int i = this.b;
        if (i > 5) {
            a("check slow speed mode times out of max times!");
            return;
        }
        this.b = i + 1;
        LogTool.d(m, "check slow translate mode.");
        this.i = g.CHECK_SLOW_SPEED_STATE;
        ConnParam connParam = new ConnParam();
        connParam.mode = 0;
        com.ido.ble.h.a.a(connParam);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        int i = this.c;
        if (i > 30) {
            a("check write chip times out of max times!");
            return;
        }
        this.c = i + 1;
        LogTool.d(m, "check write chip state...");
        this.i = g.CHECK_WRITE_CHIP_STATE;
        ControlGps controlGps = new ControlGps();
        controlGps.operate = 2;
        controlGps.type = 3;
        com.ido.ble.h.a.a(controlGps);
    }

    public static a f() {
        return r;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogTool.d(m, "translate aGps file complete.");
        if (this.j.fileType == 2) {
            l();
        } else {
            m();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogTool.d(m, "release.");
        com.ido.ble.gps.callback.a.h().b(this.k);
        com.ido.ble.gps.callback.a.h().b(this.l);
        this.d = null;
        this.i = g.STATE_NULL;
        this.h.removeCallbacksAndMessages(null);
        this.g = false;
        this.b = 0;
        this.f12289a = 0;
        this.c = 0;
    }

    private void i() {
        LogTool.d(m, "set fast translate mode.");
        this.i = g.SET_FAST_SPEED;
        ConnParam connParam = new ConnParam();
        connParam.mode = 1;
        com.ido.ble.h.a.a(connParam);
    }

    private void j() {
        LogTool.d(m, "set slow translate mode.");
        this.i = g.SET_SLOW_SPEED;
        ConnParam connParam = new ConnParam();
        connParam.mode = 2;
        com.ido.ble.h.a.a(connParam);
    }

    private void k() {
        Protocol protocol;
        int i;
        String str;
        LogTool.d(m, "begin translate aGps file...");
        this.i = g.TRANSLATE_FILE;
        if (this.f <= 0) {
            protocol = Protocol.getInstance();
            i = 1;
        } else {
            protocol = Protocol.getInstance();
            i = this.f;
        }
        protocol.tranDataSetPRN(i);
        byte[] a2 = com.ido.ble.common.c.a(this.e);
        if (a2 != null && a2.length > 0) {
            int c2 = u.c(a2);
            if (c2 != 0) {
                str = "tranDataSetBuff return code is " + c2;
            }
            Protocol.getInstance().tranDataStart();
        }
        str = "aGps byte data is null";
        a(str);
        Protocol.getInstance().tranDataStart();
    }

    private void l() {
        if (this.j.fileType == 1) {
            LogTool.d(m, "to set gps default para.");
            com.ido.ble.h.a.f();
        }
        this.h.postDelayed(new f(), Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    private void m() {
        LogTool.d(m, "start to write to chip...");
        this.i = g.WRITE_CHIP;
        ControlGps controlGps = new ControlGps();
        controlGps.operate = 1;
        controlGps.type = 3;
        com.ido.ble.h.a.a(controlGps);
    }

    public void a() {
        if (this.g) {
            LogTool.d(m, "stop.");
            Protocol.getInstance().tranDataStop();
            h();
        }
    }

    public void a(AgpsFileTransConfig agpsFileTransConfig) {
        LogTool.d(m, "start ... " + agpsFileTransConfig.toString());
        if (this.g) {
            LogTool.d(m, "is in staring state, ignore ...");
            return;
        }
        this.j = agpsFileTransConfig;
        IAGpsTranslateStateListener iAGpsTranslateStateListener = agpsFileTransConfig.listener;
        this.d = iAGpsTranslateStateListener;
        this.e = agpsFileTransConfig.filePath;
        this.f = agpsFileTransConfig.PRN;
        iAGpsTranslateStateListener.onStart();
        if (!b()) {
            a("aGps file is not exist.");
            return;
        }
        this.g = true;
        com.ido.ble.gps.callback.a.h().a(this.k);
        com.ido.ble.gps.callback.a.h().a(this.l);
        i();
    }

    @Deprecated
    public void a(String str, IAGpsTranslateStateListener iAGpsTranslateStateListener) {
        AgpsFileTransConfig agpsFileTransConfig = new AgpsFileTransConfig();
        agpsFileTransConfig.filePath = str;
        agpsFileTransConfig.listener = iAGpsTranslateStateListener;
        a(agpsFileTransConfig);
    }
}
