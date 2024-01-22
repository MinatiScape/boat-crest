package com.jieli.jl_rcsp.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = CrashHandler.class.getSimpleName();
    @SuppressLint({"StaticFieldLeak"})
    private static volatile CrashHandler e;

    /* renamed from: a  reason: collision with root package name */
    private Thread.UncaughtExceptionHandler f12514a;
    private Context b;
    private final Map<String, String> c = new HashMap();
    private OnExceptionListener d;

    /* loaded from: classes11.dex */
    public interface OnExceptionListener {
        void onException(Throwable th);
    }

    private CrashHandler() {
    }

    private boolean a(Throwable th) {
        if (th == null) {
            return false;
        }
        OnExceptionListener onExceptionListener = this.d;
        if (onExceptionListener != null) {
            onExceptionListener.onException(th);
        }
        a(this.b);
        b(th);
        return true;
    }

    private String b(Throwable th) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : this.c.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        printWriter.close();
        sb.append(stringWriter.toString());
        JL_Log.e(TAG, sb.toString());
        return null;
    }

    public static CrashHandler getInstance() {
        if (e == null) {
            synchronized (CrashHandler.class) {
                if (e == null) {
                    e = new CrashHandler();
                }
            }
        }
        return e;
    }

    public void init(Context context) {
        this.b = context;
        if (this.f12514a == null) {
            this.f12514a = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    public void setOnExceptionListener(OnExceptionListener onExceptionListener) {
        this.d = onExceptionListener;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        if (thread == null || th == null) {
            return;
        }
        if (!a(th) && (uncaughtExceptionHandler = this.f12514a) != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
            return;
        }
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e2) {
            String str = TAG;
            JL_Log.e(str, "InterruptedException error : " + a((Exception) e2));
        }
        this.f12514a = null;
        this.b = null;
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    private void a(Context context) {
        Field[] declaredFields;
        PackageManager packageManager;
        if (context == null) {
            return;
        }
        try {
            packageManager = context.getPackageManager();
        } catch (PackageManager.NameNotFoundException e2) {
            JL_Log.e(TAG, "an error occured when collect package info : " + a((Exception) e2));
        }
        if (packageManager == null) {
            return;
        }
        PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 1);
        if (packageInfo != null) {
            String str = packageInfo.versionName;
            if (str == null) {
                str = "null";
            }
            this.c.put("versionName", str);
            this.c.put("versionCode", packageInfo.versionCode + "");
        }
        for (Field field : Build.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.get(null) != null) {
                    this.c.put(field.getName(), field.get(null).toString());
                    JL_Log.d(TAG, field.getName() + " : " + field.get(null));
                }
            } catch (Exception e3) {
                JL_Log.e(TAG, "an error occured when collect crash info : " + a(e3));
            }
        }
    }

    private String a(Exception exc) {
        if (exc == null) {
            return null;
        }
        return exc.toString();
    }
}
