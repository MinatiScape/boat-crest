package com.abupdate.iot_libs.engine;

import android.content.Context;
import android.os.Process;
import com.abupdate.iot_libs.OtaAgentPolicy;
import com.abupdate.trace.Trace;
import java.lang.Thread;
/* loaded from: classes.dex */
public class a implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    public static a f1890a;
    public Thread.UncaughtExceptionHandler b;

    public static a a() {
        if (f1890a == null) {
            synchronized (a.class) {
                if (f1890a == null) {
                    f1890a = new a();
                }
            }
        }
        return f1890a;
    }

    public final void b(Throwable th) {
        if (th == null) {
            return;
        }
        Trace.e("CrashCatcher", "Uncaught exception", th);
        if ((th instanceof SecurityException) && th.getMessage().contains("READ_LOGS permission required")) {
            Trace.e("CrashCatcher", "缺少系统权限，请进行平台签名！！！");
        }
        if (OtaAgentPolicy.getConfig().showTrace) {
            LogManager.getInstance().saveTraceLog();
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        b(th);
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.b;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
            return;
        }
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    public void a(Context context) {
        this.b = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
}
