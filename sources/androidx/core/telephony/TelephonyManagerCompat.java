package androidx.core.telephony;

import android.annotation.SuppressLint;
import android.os.Build;
import android.telephony.TelephonyManager;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class TelephonyManagerCompat {

    /* renamed from: a  reason: collision with root package name */
    public static Method f1090a;
    public static Method b;

    @RequiresApi(23)
    /* loaded from: classes.dex */
    public static class a {
        @SuppressLint({"MissingPermission"})
        @Nullable
        @RequiresPermission("android.permission.READ_PHONE_STATE")
        @DoNotInline
        public static String a(TelephonyManager telephonyManager, int i) {
            return telephonyManager.getDeviceId(i);
        }
    }

    @RequiresApi(26)
    /* loaded from: classes.dex */
    public static class b {
        @SuppressLint({"MissingPermission"})
        @Nullable
        @RequiresPermission("android.permission.READ_PHONE_STATE")
        @DoNotInline
        public static String a(TelephonyManager telephonyManager) {
            return telephonyManager.getImei();
        }
    }

    @RequiresApi(30)
    /* loaded from: classes.dex */
    public static class c {
        @DoNotInline
        public static int a(TelephonyManager telephonyManager) {
            return telephonyManager.getSubscriptionId();
        }
    }

    @Nullable
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    @SuppressLint({"MissingPermission"})
    public static String getImei(@NonNull TelephonyManager telephonyManager) {
        int subscriptionId;
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            return b.a(telephonyManager);
        }
        if (i >= 22 && (subscriptionId = getSubscriptionId(telephonyManager)) != Integer.MAX_VALUE && subscriptionId != -1) {
            int slotIndex = SubscriptionManagerCompat.getSlotIndex(subscriptionId);
            if (i >= 23) {
                return a.a(telephonyManager, slotIndex);
            }
            try {
                if (f1090a == null) {
                    Method declaredMethod = TelephonyManager.class.getDeclaredMethod("getDeviceId", Integer.TYPE);
                    f1090a = declaredMethod;
                    declaredMethod.setAccessible(true);
                }
                return (String) f1090a.invoke(telephonyManager, Integer.valueOf(slotIndex));
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                return null;
            }
        }
        return telephonyManager.getDeviceId();
    }

    @SuppressLint({"SoonBlockedPrivateApi"})
    public static int getSubscriptionId(@NonNull TelephonyManager telephonyManager) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            return c.a(telephonyManager);
        }
        if (i >= 22) {
            try {
                if (b == null) {
                    Method declaredMethod = TelephonyManager.class.getDeclaredMethod("getSubId", new Class[0]);
                    b = declaredMethod;
                    declaredMethod.setAccessible(true);
                }
                Integer num = (Integer) b.invoke(telephonyManager, new Object[0]);
                if (num == null || num.intValue() == -1) {
                    return Integer.MAX_VALUE;
                }
                return num.intValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                return Integer.MAX_VALUE;
            }
        }
        return Integer.MAX_VALUE;
    }
}
