package androidx.core.os;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Process;
import android.os.UserHandle;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public final class ProcessCompat {

    @RequiresApi(16)
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final Object f1077a = new Object();
        public static Method b;
        public static boolean c;

        @SuppressLint({"PrivateApi"})
        public static boolean a(int i) {
            try {
                synchronized (f1077a) {
                    if (!c) {
                        c = true;
                        b = Class.forName("android.os.UserId").getDeclaredMethod("isApp", Integer.TYPE);
                    }
                }
                Method method = b;
                if (method != null) {
                    Boolean bool = (Boolean) method.invoke(null, Integer.valueOf(i));
                    if (bool != null) {
                        return bool.booleanValue();
                    }
                    throw new NullPointerException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    @RequiresApi(17)
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final Object f1078a = new Object();
        public static Method b;
        public static boolean c;

        @SuppressLint({"DiscouragedPrivateApi"})
        public static boolean a(int i) {
            try {
                synchronized (f1078a) {
                    if (!c) {
                        c = true;
                        b = UserHandle.class.getDeclaredMethod("isApp", Integer.TYPE);
                    }
                }
                Method method = b;
                if (method != null) {
                    Boolean bool = (Boolean) method.invoke(null, Integer.valueOf(i));
                    if (bool != null) {
                        return bool.booleanValue();
                    }
                    throw new NullPointerException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    @RequiresApi(24)
    /* loaded from: classes.dex */
    public static class c {
        public static boolean a(int i) {
            return Process.isApplicationUid(i);
        }
    }

    private ProcessCompat() {
    }

    public static boolean isApplicationUid(int i) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 24) {
            return c.a(i);
        }
        if (i2 >= 17) {
            return b.a(i);
        }
        if (i2 == 16) {
            return a.a(i);
        }
        return true;
    }
}
