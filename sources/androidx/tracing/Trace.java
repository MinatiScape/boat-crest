package androidx.tracing;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public final class Trace {

    /* renamed from: a  reason: collision with root package name */
    public static long f1690a;
    public static Method b;
    public static Method c;
    public static Method d;
    public static Method e;

    public static void a(@NonNull String str, int i) {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                if (c == null) {
                    c = android.os.Trace.class.getMethod("asyncTraceBegin", Long.TYPE, String.class, Integer.TYPE);
                }
                c.invoke(null, Long.valueOf(f1690a), str, Integer.valueOf(i));
            } catch (Exception e2) {
                c("asyncTraceBegin", e2);
            }
        }
    }

    public static void b(@NonNull String str, int i) {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                if (d == null) {
                    d = android.os.Trace.class.getMethod("asyncTraceEnd", Long.TYPE, String.class, Integer.TYPE);
                }
                d.invoke(null, Long.valueOf(f1690a), str, Integer.valueOf(i));
            } catch (Exception e2) {
                c("asyncTraceEnd", e2);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static void beginAsyncSection(@NonNull String str, int i) {
        try {
            if (c == null) {
                b.a(str, i);
                return;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        a(str, i);
    }

    public static void beginSection(@NonNull String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            a.a(str);
        }
    }

    public static void c(@NonNull String str, @NonNull Exception exc) {
        if (exc instanceof InvocationTargetException) {
            Throwable cause = exc.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new RuntimeException(cause);
        }
        Log.v("Trace", "Unable to call " + str + " via reflection", exc);
    }

    public static boolean d() {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                if (b == null) {
                    f1690a = android.os.Trace.class.getField("TRACE_TAG_APP").getLong(null);
                    b = android.os.Trace.class.getMethod("isTagEnabled", Long.TYPE);
                }
                return ((Boolean) b.invoke(null, Long.valueOf(f1690a))).booleanValue();
            } catch (Exception e2) {
                c("isTagEnabled", e2);
            }
        }
        return false;
    }

    public static void e(@NonNull String str, int i) {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                if (e == null) {
                    e = android.os.Trace.class.getMethod("traceCounter", Long.TYPE, String.class, Integer.TYPE);
                }
                e.invoke(null, Long.valueOf(f1690a), str, Integer.valueOf(i));
            } catch (Exception e2) {
                c("traceCounter", e2);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static void endAsyncSection(@NonNull String str, int i) {
        try {
            if (d == null) {
                b.b(str, i);
                return;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        b(str, i);
    }

    public static void endSection() {
        if (Build.VERSION.SDK_INT >= 18) {
            a.b();
        }
    }

    @SuppressLint({"NewApi"})
    public static boolean isEnabled() {
        try {
            if (b == null) {
                return android.os.Trace.isEnabled();
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        return d();
    }

    @SuppressLint({"NewApi"})
    public static void setCounter(@NonNull String str, int i) {
        try {
            if (e == null) {
                b.c(str, i);
                return;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        e(str, i);
    }
}
