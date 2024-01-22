package com.abupdate.iot_libs.engine;

import android.os.Handler;
import com.abupdate.iot_libs.constant.Error;
import com.abupdate.iot_libs.constant.OtaConstants;
import com.abupdate.iot_libs.info.VersionInfo;
import com.abupdate.iot_libs.inter.ICheckVersionCallback;
import com.abupdate.iot_libs.inter.IDownloadListener;
import com.abupdate.iot_libs.inter.IRebootUpgradeCallBack;
import com.abupdate.iot_libs.inter.IRegisterListener;
import com.abupdate.trace.Trace;
/* loaded from: classes.dex */
public class c {
    public static c b;
    public static IDownloadListener c;
    public static ICheckVersionCallback d;
    public static IRegisterListener e;
    public static IRebootUpgradeCallBack f;

    /* renamed from: a  reason: collision with root package name */
    public Handler f1892a;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a(c cVar) {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.d.onCheckSuccess(VersionInfo.getInstance());
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public final /* synthetic */ int h;

        public b(c cVar, int i) {
            this.h = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (c.d != null) {
                c.d.onCheckFail(this.h);
            } else {
                Trace.d("IOTCallbackManager", "onCheckVersionFailed() sCheckVersionListener==null");
            }
        }
    }

    /* renamed from: com.abupdate.iot_libs.engine.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class RunnableC0192c implements Runnable {
        public final /* synthetic */ int h;
        public final /* synthetic */ long i;
        public final /* synthetic */ long j;
        public final /* synthetic */ int k;

        public RunnableC0192c(c cVar, int i, long j, long j2, int i2) {
            this.h = i;
            this.i = j;
            this.j = j2;
            this.k = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = this.h;
            if (i == 1) {
                c.c.onPrepare();
            } else if (i == 2) {
                c.c.onDownloadProgress(this.i, this.j);
            } else if (i == 3) {
                c.c.onCancel();
            } else if (i == 4) {
                c.c.onCompleted();
                Trace.d("IOTCallbackManager", "%s%s%s", OtaConstants.DOUBLE_LINE, "download success", OtaConstants.DOUBLE_LINE);
            } else if (i != 5) {
                c.c.onFailed(Error.ERROR);
            } else {
                c.c.onFailed(this.k);
                Trace.d("IOTCallbackManager", "%s%s%s", OtaConstants.DOUBLE_LINE, "download failed", OtaConstants.DOUBLE_LINE);
                Trace.d("IOTCallbackManager", "onUpdateFailed() error code:" + this.k + ",message:" + Error.getErrorMessage(this.k));
            }
        }
    }

    /* loaded from: classes.dex */
    public class d implements Runnable {
        public final /* synthetic */ int h;

        public d(c cVar, int i) {
            this.h = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (c.f != null) {
                c.f.onError(this.h);
            }
        }
    }

    public static c a() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    public static IRebootUpgradeCallBack d() {
        return f;
    }

    public void b() {
        IRegisterListener iRegisterListener = e;
        if (iRegisterListener != null) {
            iRegisterListener.onSuccess();
        }
    }

    public void c() {
        Trace.d("IOTCallbackManager", "%s%s%s", OtaConstants.DOUBLE_LINE, "onCheckVersionSuccess", OtaConstants.DOUBLE_LINE);
        this.f1892a.post(new a(this));
    }

    public void b(int i) {
        Trace.d("IOTCallbackManager", "%s%s%s", OtaConstants.DOUBLE_LINE, "onUpdateFailed", OtaConstants.DOUBLE_LINE);
        Trace.d("IOTCallbackManager", "onUpdateFailed() error code:" + i + ",message:" + Error.getErrorMessage(i));
        this.f1892a.post(new d(this, i));
    }

    public void a(Handler handler) {
        this.f1892a = handler;
    }

    public void a(int i) {
        Trace.d("IOTCallbackManager", "%s%s%s", OtaConstants.DOUBLE_LINE, "onCheckVersionFailed", OtaConstants.DOUBLE_LINE);
        Trace.d("IOTCallbackManager", "onCheckVersionFailed() error code:" + i + ",message:" + Error.getErrorMessage(i));
        if (d != null) {
            if (this.f1892a == null) {
                Trace.d("IOTCallbackManager", "onCheckVersionFailed() handler == null");
            }
            this.f1892a.post(new b(this, i));
            return;
        }
        Trace.d("IOTCallbackManager", "onCheckVersionFailed() sCheckVersionListener==null");
    }

    public void a(int i, long j, long j2, int i2) {
        this.f1892a.post(new RunnableC0192c(this, i, j, j2, i2));
    }

    public static void a(IRegisterListener iRegisterListener) {
        e = iRegisterListener;
    }

    public static void a(IDownloadListener iDownloadListener) {
        c = iDownloadListener;
    }

    public static void a(ICheckVersionCallback iCheckVersionCallback) {
        d = iCheckVersionCallback;
    }

    public static void a(IRebootUpgradeCallBack iRebootUpgradeCallBack) {
        f = iRebootUpgradeCallBack;
    }
}
