package com.ido.ble.watch.custom;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.ble.LocalDataManager;
import com.ido.ble.common.j;
import com.ido.ble.common.k;
import com.ido.ble.common.o;
import com.ido.ble.file.transfer.FileTransferConfig;
import com.ido.ble.file.transfer.IFileTransferListener;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.watch.custom.callback.WatchPlateCallBack;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.ble.watch.custom.model.WatchPlateFileInfo;
import com.ido.ble.watch.custom.model.WatchPlateFileMakeConfig;
import com.ido.ble.watch.custom.model.WatchPlateScreenInfo;
import com.polidea.rxandroidble2.ClientComponent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes11.dex */
public class a {
    private static final String i = "WATCH_PLATE_AUTO_SET";
    private static final String j = "ido_watch_plate_data.iwf";
    private static final String k = "ido_watch_plate_data.iwf.lz";
    private static a l;
    private WatchPlateSetConfig b;
    private String c;
    private FileTransferConfig f;

    /* renamed from: a  reason: collision with root package name */
    private boolean f12314a = false;
    private boolean d = false;
    private boolean e = false;
    private Handler g = new Handler(Looper.getMainLooper());
    private WatchPlateCallBack.IOperateCallBack h = new C0606a();

    /* renamed from: com.ido.ble.watch.custom.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0606a implements WatchPlateCallBack.IOperateCallBack {
        public C0606a() {
        }

        @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onDeletePlate(boolean z) {
        }

        @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onGetCurrentPlate(String str) {
        }

        @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onGetDialPlateParam(DialPlateParam dialPlateParam) {
            if (a.this.e) {
                LogTool.d(a.i, "onGetDialPlateParam is_SDK_use = true");
                a.this.e = false;
                a.this.s();
                if (dialPlateParam != null) {
                    a.this.b(dialPlateParam.usable_max_download_space_size);
                } else {
                    LogTool.b(a.i, "dialPlateParam == null");
                    a.this.b();
                }
            }
            if (!a.this.d || a.this.b == null || a.this.b.isOnlyTranslateWatchFile) {
                return;
            }
            a.this.b(dialPlateParam.item);
        }

        @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onGetPlateFileInfo(WatchPlateFileInfo watchPlateFileInfo) {
            if (!a.this.d || a.this.b == null || a.this.b.isOnlyTranslateWatchFile) {
                return;
            }
            a.this.a(watchPlateFileInfo.fileNameList);
        }

        @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onGetScreenInfo(WatchPlateScreenInfo watchPlateScreenInfo) {
            a.this.b(watchPlateScreenInfo);
        }

        @Override // com.ido.ble.watch.custom.callback.WatchPlateCallBack.IOperateCallBack
        public void onSetPlate(boolean z) {
            if (!a.this.d || a.this.b == null || a.this.b.isOnlyTranslateWatchFile) {
                return;
            }
            a.this.a(z);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LogTool.b(a.i, "get free size time out.");
            a.this.i();
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LogTool.b(a.i, "get screen info time out.");
            a.this.e();
        }
    }

    /* loaded from: classes11.dex */
    public class d implements IFileTransferListener {
        public d() {
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onFailed(String str) {
            LogTool.b(a.i, "translatePlateFile failed, " + str);
            a.this.a(str);
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onProgress(int i) {
            a.this.a(i);
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onStart() {
        }

        @Override // com.ido.ble.file.transfer.IFileTransferListener
        public void onSuccess() {
            if (a.this.f12314a && a.this.b != null) {
                if (a.this.b.isOnlyTranslateWatchFile) {
                    a.this.d();
                    return;
                } else {
                    a.this.k();
                    return;
                }
            }
            LogTool.b(a.i, "[translatePlateFile].onSuccess, isDoing=" + a.this.f12314a + ",watchPlateSetConfig is null");
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

    private a() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        LogTool.d(i, "progress = " + i2);
        WatchPlateSetConfig watchPlateSetConfig = this.b;
        if (watchPlateSetConfig == null) {
            LogTool.b(i, "[autoSetPlateProgress] watchPlateSetConfig is null");
            return;
        }
        WatchPlateCallBack.IAutoSetPlateCallBack iAutoSetPlateCallBack = watchPlateSetConfig.stateListener;
        if (iAutoSetPlateCallBack != null) {
            iAutoSetPlateCallBack.onProgress(i2);
        } else {
            LogTool.b(i, "[autoSetPlateProgress] watchPlateSetConfig.stateListener is null");
        }
    }

    private void a(WatchPlateScreenInfo watchPlateScreenInfo) {
        LogTool.d(i, "start makePlateFile");
        WatchPlateFileMakeConfig watchPlateFileMakeConfig = new WatchPlateFileMakeConfig();
        watchPlateFileMakeConfig.format = watchPlateScreenInfo.format;
        watchPlateFileMakeConfig.filePath = this.c;
        watchPlateFileMakeConfig.outFileName = j;
        watchPlateFileMakeConfig.blockSize = watchPlateScreenInfo.blockSize;
        com.ido.ble.i.a.a.a(watchPlateFileMakeConfig);
        v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0073 A[Catch: Exception -> 0x00b9, TryCatch #0 {Exception -> 0x00b9, blocks: (B:3:0x0004, B:5:0x0008, B:7:0x000e, B:9:0x0014, B:11:0x001a, B:13:0x0022, B:16:0x002b, B:18:0x0031, B:20:0x0035, B:22:0x0046, B:24:0x0050, B:26:0x005d, B:28:0x0061, B:30:0x0067, B:32:0x0073, B:34:0x007f, B:36:0x0089, B:37:0x0091, B:38:0x0097, B:40:0x00a3, B:42:0x00ad), top: B:48:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = ","
            java.lang.String r1 = "WATCH_PLATE_AUTO_SET"
            com.ido.ble.watch.custom.WatchPlateSetConfig r2 = r6.b     // Catch: java.lang.Exception -> Lb9
            if (r2 != 0) goto Le
            java.lang.String r7 = "[autoSetPlateFailed] watchPlateSetConfig is null"
            com.ido.ble.logs.LogTool.b(r1, r7)     // Catch: java.lang.Exception -> Lb9
            return
        Le:
            boolean r2 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> Lb9
            if (r2 != 0) goto Lc2
            com.ido.ble.watch.custom.WatchPlateSetConfig r2 = r6.b     // Catch: java.lang.Exception -> Lb9
            com.ido.ble.watch.custom.callback.WatchPlateCallBack$ISetPlatErrorCallback r2 = r2.errorCallback     // Catch: java.lang.Exception -> Lb9
            if (r2 == 0) goto Lc2
            java.lang.String r2 = "transfer progress return code ="
            boolean r2 = r7.startsWith(r2)     // Catch: java.lang.Exception -> Lb9
            if (r2 == 0) goto Lc2
            boolean r2 = r7.contains(r0)     // Catch: java.lang.Exception -> Lb9
            java.lang.String r3 = "="
            r4 = 1
            if (r2 == 0) goto L97
            java.lang.String[] r7 = r7.split(r0)     // Catch: java.lang.Exception -> Lb9
            if (r7 == 0) goto L91
            int r0 = r7.length     // Catch: java.lang.Exception -> Lb9
            r2 = 2
            if (r0 != r2) goto L91
            r0 = 0
            r0 = r7[r0]     // Catch: java.lang.Exception -> Lb9
            java.lang.String[] r0 = r0.split(r3)     // Catch: java.lang.Exception -> Lb9
            r0 = r0[r4]     // Catch: java.lang.Exception -> Lb9
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> Lb9
            r5 = -1000(0xfffffffffffffc18, float:NaN)
            if (r2 != 0) goto L5c
            java.lang.String r0 = r0.trim()     // Catch: java.lang.Exception -> Lb9
            boolean r2 = android.text.TextUtils.isDigitsOnly(r0)     // Catch: java.lang.Exception -> Lb9
            if (r2 == 0) goto L5c
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Exception -> Lb9
            com.ido.ble.watch.custom.WatchPlateSetConfig r2 = r6.b     // Catch: java.lang.Exception -> Lb9
            com.ido.ble.watch.custom.callback.WatchPlateCallBack$ISetPlatErrorCallback r2 = r2.errorCallback     // Catch: java.lang.Exception -> Lb9
            r2.onFailed(r0)     // Catch: java.lang.Exception -> Lb9
            goto L5d
        L5c:
            r0 = r5
        L5d:
            r7 = r7[r4]     // Catch: java.lang.Exception -> Lb9
            if (r0 == r5) goto Lc2
            boolean r2 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> Lb9
            if (r2 != 0) goto Lc2
            java.lang.String r7 = r7.trim()     // Catch: java.lang.Exception -> Lb9
            java.lang.String r2 = "value = "
            boolean r2 = r7.startsWith(r2)     // Catch: java.lang.Exception -> Lb9
            if (r2 == 0) goto Lc2
            java.lang.String[] r7 = r7.split(r3)     // Catch: java.lang.Exception -> Lb9
            r7 = r7[r4]     // Catch: java.lang.Exception -> Lb9
            boolean r2 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> Lb9
            if (r2 != 0) goto Lc2
            java.lang.String r7 = r7.trim()     // Catch: java.lang.Exception -> Lb9
            boolean r2 = android.text.TextUtils.isDigitsOnly(r7)     // Catch: java.lang.Exception -> Lb9
            if (r2 == 0) goto Lc2
            int r7 = java.lang.Integer.parseInt(r7)     // Catch: java.lang.Exception -> Lb9
            com.ido.ble.gps.callback.GpsCallBack.a(r0, r7)     // Catch: java.lang.Exception -> Lb9
            goto Lc2
        L91:
            java.lang.String r7 = "error format  is Mismatch!"
            com.ido.ble.logs.LogTool.d(r1, r7)     // Catch: java.lang.Exception -> Lb9
            goto Lc2
        L97:
            java.lang.String[] r7 = r7.split(r3)     // Catch: java.lang.Exception -> Lb9
            r7 = r7[r4]     // Catch: java.lang.Exception -> Lb9
            boolean r0 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> Lb9
            if (r0 != 0) goto Lc2
            java.lang.String r7 = r7.trim()     // Catch: java.lang.Exception -> Lb9
            boolean r0 = android.text.TextUtils.isDigitsOnly(r7)     // Catch: java.lang.Exception -> Lb9
            if (r0 == 0) goto Lc2
            com.ido.ble.watch.custom.WatchPlateSetConfig r0 = r6.b     // Catch: java.lang.Exception -> Lb9
            com.ido.ble.watch.custom.callback.WatchPlateCallBack$ISetPlatErrorCallback r0 = r0.errorCallback     // Catch: java.lang.Exception -> Lb9
            int r7 = java.lang.Integer.parseInt(r7)     // Catch: java.lang.Exception -> Lb9
            r0.onFailed(r7)     // Catch: java.lang.Exception -> Lb9
            goto Lc2
        Lb9:
            r7 = move-exception
            r7.printStackTrace()
            java.lang.String r7 = "[autoSetPlateFailed] watchPlateSetConfig errorCallback process error"
            com.ido.ble.logs.LogTool.b(r1, r7)
        Lc2:
            r6.b()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ble.watch.custom.a.a(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<String> list) {
        String str;
        if (list == null || list.size() == 0) {
            str = "watchReturnPlateListData = null";
        } else {
            LogTool.d(i, "watchReturnPlateListData = " + k.a(list));
            boolean z = false;
            Iterator<String> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (f().equals(it.next())) {
                    z = true;
                    break;
                }
            }
            if (z) {
                u();
                return;
            }
            str = "set failed, isExists = false, getCurrentPlateUniqueID=" + f();
        }
        LogTool.b(i, str);
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        LogTool.d(i, "watchReturnSetPlateResult = " + z);
        if (z) {
            d();
        } else {
            b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogTool.d(i, "failed");
        WatchPlateSetConfig watchPlateSetConfig = this.b;
        if (watchPlateSetConfig == null) {
            LogTool.b(i, "[autoSetPlateFailed] watchPlateSetConfig is null");
            return;
        }
        WatchPlateCallBack.IAutoSetPlateCallBack iAutoSetPlateCallBack = watchPlateSetConfig.stateListener;
        if (iAutoSetPlateCallBack != null) {
            iAutoSetPlateCallBack.onFailed();
        } else {
            LogTool.b(i, "[autoSetPlateFailed] watchPlateSetConfig.stateListener is null");
        }
        o();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2) {
        FileTransferConfig fileTransferConfig = this.f;
        if (fileTransferConfig == null) {
            LogTool.b(i, "mFileTransferConfig == null");
            b();
        } else if (i2 >= fileTransferConfig.oriSize) {
            LogTool.d(i, "freeSize >= mFileTransferConfig.oriSize, start to tran");
            com.ido.ble.file.transfer.b.g().b(this.f);
        } else {
            LogTool.d(i, "freeSize < mFileTransferConfig.oriSize, fail to tran");
            a("transfer progress return code = 21");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(WatchPlateScreenInfo watchPlateScreenInfo) {
        t();
        if (watchPlateScreenInfo == null) {
            LogTool.b(i, "get screen info failed.");
            b();
            return;
        }
        LogTool.d(i, "get screen info ok");
        a(watchPlateScreenInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<DialPlateParam.PlateFileInfo> list) {
        String str;
        if (list == null || list.size() == 0) {
            str = "PlateFileInfo list new watchReturnPlateListData = null";
        } else {
            LogTool.d(i, "PlateFileInfo list new watchReturnPlateListData = " + k.a(list));
            boolean z = false;
            Iterator<DialPlateParam.PlateFileInfo> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (f().equals(it.next().name)) {
                    z = true;
                    break;
                }
            }
            if (z) {
                u();
                return;
            }
            str = "PlateFileInfo list new set failed, isExists = false, getCurrentPlateUniqueID=" + f();
        }
        LogTool.b(i, str);
        b();
    }

    private boolean b(WatchPlateSetConfig watchPlateSetConfig) {
        String str;
        if (watchPlateSetConfig == null) {
            str = "config is null .";
        } else if (TextUtils.isEmpty(watchPlateSetConfig.filePath)) {
            str = "config. file path is null .";
        } else if (!new File(watchPlateSetConfig.filePath).exists()) {
            str = "config. file is not exists";
        } else if (TextUtils.isEmpty(watchPlateSetConfig.uniqueID)) {
            str = "config. uniqueID is null";
        } else if (watchPlateSetConfig.stateListener != null) {
            LogTool.d(i, "config is " + watchPlateSetConfig.toString());
            return true;
        } else {
            str = "config. state listener is null";
        }
        LogTool.b(i, str);
        return false;
    }

    private void c() {
        LogTool.d(i, "start");
        WatchPlateSetConfig watchPlateSetConfig = this.b;
        if (watchPlateSetConfig == null) {
            LogTool.b(i, "[autoSetPlateStart] watchPlateSetConfig is null");
            return;
        }
        WatchPlateCallBack.IAutoSetPlateCallBack iAutoSetPlateCallBack = watchPlateSetConfig.stateListener;
        if (iAutoSetPlateCallBack != null) {
            iAutoSetPlateCallBack.onStart();
        } else {
            LogTool.b(i, "[autoSetPlateStart] watchPlateSetConfig.stateListener is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogTool.d(i, FirebaseAnalytics.Param.SUCCESS);
        WatchPlateSetConfig watchPlateSetConfig = this.b;
        if (watchPlateSetConfig == null) {
            LogTool.b(i, "[autoSetPlateSuccess] watchPlateSetConfig is null");
            return;
        }
        WatchPlateCallBack.IAutoSetPlateCallBack iAutoSetPlateCallBack = watchPlateSetConfig.stateListener;
        if (iAutoSetPlateCallBack != null) {
            iAutoSetPlateCallBack.onSuccess();
        } else {
            LogTool.b(i, "[autoSetPlateSuccess] watchPlateSetConfig.stateListener is null");
        }
        o();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.f12314a) {
            LogTool.d(i, ClientComponent.NamedSchedulers.TIMEOUT);
            WatchPlateSetConfig watchPlateSetConfig = this.b;
            if (watchPlateSetConfig == null) {
                LogTool.b(i, "[autoSetPlateTimeOut] watchPlateSetConfig is null");
                return;
            }
            WatchPlateCallBack.IAutoSetPlateCallBack iAutoSetPlateCallBack = watchPlateSetConfig.stateListener;
            if (iAutoSetPlateCallBack != null) {
                iAutoSetPlateCallBack.onFailed();
            } else {
                LogTool.b(i, "[autoSetPlateTimeOut] watchPlateSetConfig.stateListener is null");
            }
            o();
        }
    }

    private String f() {
        if (this.b == null) {
            LogTool.b(i, "getCurrentPlateUniqueID, watchPlateSetConfig is null");
            return "";
        }
        return this.b.uniqueID + ".iwf";
    }

    private Handler g() {
        if (this.g == null) {
            this.g = new Handler(Looper.getMainLooper());
        }
        return this.g;
    }

    private void h() {
        this.e = true;
        LogTool.d(i, "start get free size");
        com.ido.ble.i.a.a.A();
        q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogTool.d(i, "getFreeSizeTimeOut");
        WatchPlateSetConfig watchPlateSetConfig = this.b;
        if (watchPlateSetConfig == null) {
            LogTool.b(i, "[autoSetPlateTimeOut] watchPlateSetConfig is null");
            return;
        }
        WatchPlateCallBack.IAutoSetPlateCallBack iAutoSetPlateCallBack = watchPlateSetConfig.stateListener;
        if (iAutoSetPlateCallBack != null) {
            iAutoSetPlateCallBack.onFailed();
        } else {
            LogTool.b(i, "[autoSetPlateTimeOut] watchPlateSetConfig.stateListener is null");
        }
        o();
    }

    public static a j() {
        if (l == null) {
            l = new a();
        }
        return l;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogTool.d(i, "start getPlateList");
        this.d = true;
        p();
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo == null || !supportFunctionInfo.V3_support_watch_capacity_size_display) {
            LogTool.d(i, "V3_support_watch_capacity_size_display == false ,start to getWatchPlateList");
            com.ido.ble.i.a.a.X();
            return;
        }
        LogTool.d(i, "V3_support_watch_capacity_size_display == true ,start to getDialPlateParam");
        com.ido.ble.i.a.a.A();
    }

    private void l() {
        LogTool.d(i, "start get screen info");
        com.ido.ble.i.a.a.Y();
        r();
    }

    private void m() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo == null) {
            LogTool.d(i, "supportFunctionInfo == null,start to tran direct");
            com.ido.ble.file.transfer.b.g().b(this.f);
        } else if (supportFunctionInfo.V3_support_watch_capacity_size_display) {
            LogTool.d(i, "translatePlateFile V3_support_watch_capacity_size_display = true");
            h();
        } else {
            LogTool.d(i, "translatePlateFile V3_support_watch_capacity_size_display = false,start to tran");
            com.ido.ble.file.transfer.b.g().b(this.f);
        }
    }

    private void n() {
        com.ido.ble.watch.custom.callback.a.c().a(this.h);
    }

    private void o() {
        this.f12314a = false;
        this.d = false;
        this.e = false;
        this.b = null;
        this.c = null;
        this.f = null;
        Handler handler = this.g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        w();
    }

    private void p() {
        g().postDelayed(new e(), 30000L);
    }

    private void q() {
        g().postDelayed(new b(), 15000L);
    }

    private void r() {
        g().postDelayed(new c(), 20000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        Handler handler = this.g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private void t() {
        Handler handler = this.g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private void u() {
        LogTool.d(i, "start toSetPlate");
        com.ido.ble.i.a.a.c(f());
    }

    private void v() {
        LogTool.d(i, "start translatePlateFile");
        FileTransferConfig defaultWatchPlateFileConfig = FileTransferConfig.getDefaultWatchPlateFileConfig(f() + ".lz", this.c + k, new d());
        WatchPlateSetConfig watchPlateSetConfig = this.b;
        if (watchPlateSetConfig != null) {
            int i2 = watchPlateSetConfig.PRN;
            if (i2 > 0) {
                defaultWatchPlateFileConfig.PRN = i2;
            }
            defaultWatchPlateFileConfig.maxRetryTimes = watchPlateSetConfig.maxRetryTimes;
        }
        defaultWatchPlateFileConfig.oriSize = (int) new File(this.c + j).length();
        this.f = defaultWatchPlateFileConfig;
        LogTool.d(i, "translatePlateFile iwfFile.length = " + defaultWatchPlateFileConfig.oriSize);
        m();
    }

    private void w() {
        com.ido.ble.watch.custom.callback.a.c().b(this.h);
    }

    private boolean x() {
        LogTool.d(i, "start unzip file");
        String name = new File(this.b.filePath).getName();
        this.c = this.b.filePath.replace(name, "") + "watchFileTemp" + File.separator;
        try {
            j.b(new File(this.c));
            return o.b(this.b.filePath, this.c);
        } catch (IOException e2) {
            LogTool.b(i, e2.getMessage());
            return false;
        }
    }

    @WorkerThread
    public long a(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        LogTool.d(i, " start get dial plat size, format = " + i2);
        try {
            File file = new File(str);
            String name = file.getName();
            String substring = name.substring(0, name.lastIndexOf("."));
            StringBuilder sb = new StringBuilder();
            sb.append(file.getParent());
            String str2 = File.separator;
            sb.append(str2);
            sb.append(substring);
            sb.append(str2);
            String sb2 = sb.toString();
            File file2 = new File(sb2);
            String[] list = file2.list();
            if (!file2.exists() || (list != null && list.length == 0)) {
                LogTool.d(i, "getDialPlatSize start unzip file");
                o.b(str, sb2);
            }
            String parent = file.getParent();
            if (TextUtils.isEmpty(parent)) {
                return 0L;
            }
            return u.b(sb2, parent + str2 + substring + ".iwf", i2);
        } catch (Exception e2) {
            e2.printStackTrace();
            LogTool.d(i, "error to get dial plat size");
            return 0L;
        }
    }

    public void a() {
        LogTool.b(i, "stopByUser. ");
        com.ido.ble.file.transfer.b.g().a();
        o();
    }

    public void a(WatchPlateSetConfig watchPlateSetConfig) {
        if (this.f12314a) {
            LogTool.b(i, "is in doing state, ignore this action ...");
            return;
        }
        this.f12314a = true;
        this.b = watchPlateSetConfig;
        c();
        if (!b(watchPlateSetConfig)) {
            b();
            return;
        }
        n();
        if (x()) {
            LogTool.d(i, "unzip ok .");
            l();
            return;
        }
        LogTool.b(i, "unzip file failed .");
        b();
    }
}
