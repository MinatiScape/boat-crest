package androidx.core.telephony;

import android.os.Build;
import android.telephony.SubscriptionManager;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@RequiresApi(22)
/* loaded from: classes.dex */
public class SubscriptionManagerCompat {

    /* renamed from: a  reason: collision with root package name */
    public static Method f1089a;

    @RequiresApi(29)
    /* loaded from: classes.dex */
    public static class a {
        @DoNotInline
        public static int a(int i) {
            return SubscriptionManager.getSlotIndex(i);
        }
    }

    public static int getSlotIndex(int i) {
        if (i == -1) {
            return -1;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            return a.a(i);
        }
        try {
            if (f1089a == null) {
                if (i2 >= 26) {
                    f1089a = SubscriptionManager.class.getDeclaredMethod("getSlotIndex", Integer.TYPE);
                } else {
                    f1089a = SubscriptionManager.class.getDeclaredMethod("getSlotId", Integer.TYPE);
                }
                f1089a.setAccessible(true);
            }
            Integer num = (Integer) f1089a.invoke(null, Integer.valueOf(i));
            if (num != null) {
                return num.intValue();
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        return -1;
    }
}
