package androidx.transition;

import android.animation.LayoutTransition;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class x {

    /* renamed from: a  reason: collision with root package name */
    public static LayoutTransition f1724a;
    public static Field b;
    public static boolean c;
    public static Method d;
    public static boolean e;

    /* loaded from: classes.dex */
    public static class a extends LayoutTransition {
        @Override // android.animation.LayoutTransition
        public boolean isChangingLayout() {
            return true;
        }
    }

    public static void a(LayoutTransition layoutTransition) {
        if (!e) {
            try {
                Method declaredMethod = LayoutTransition.class.getDeclaredMethod("cancel", new Class[0]);
                d = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
                Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
            }
            e = true;
        }
        Method method = d;
        if (method != null) {
            try {
                method.invoke(layoutTransition, new Object[0]);
            } catch (IllegalAccessException unused2) {
                Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
            } catch (InvocationTargetException unused3) {
                Log.i("ViewGroupUtilsApi14", "Failed to invoke cancel method by reflection");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(@androidx.annotation.NonNull android.view.ViewGroup r5, boolean r6) {
        /*
            android.animation.LayoutTransition r0 = androidx.transition.x.f1724a
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 != 0) goto L28
            androidx.transition.x$a r0 = new androidx.transition.x$a
            r0.<init>()
            androidx.transition.x.f1724a = r0
            r4 = 2
            r0.setAnimator(r4, r3)
            android.animation.LayoutTransition r0 = androidx.transition.x.f1724a
            r0.setAnimator(r2, r3)
            android.animation.LayoutTransition r0 = androidx.transition.x.f1724a
            r0.setAnimator(r1, r3)
            android.animation.LayoutTransition r0 = androidx.transition.x.f1724a
            r4 = 3
            r0.setAnimator(r4, r3)
            android.animation.LayoutTransition r0 = androidx.transition.x.f1724a
            r4 = 4
            r0.setAnimator(r4, r3)
        L28:
            if (r6 == 0) goto L48
            android.animation.LayoutTransition r6 = r5.getLayoutTransition()
            if (r6 == 0) goto L42
            boolean r0 = r6.isRunning()
            if (r0 == 0) goto L39
            a(r6)
        L39:
            android.animation.LayoutTransition r0 = androidx.transition.x.f1724a
            if (r6 == r0) goto L42
            int r0 = androidx.transition.R.id.transition_layout_save
            r5.setTag(r0, r6)
        L42:
            android.animation.LayoutTransition r6 = androidx.transition.x.f1724a
            r5.setLayoutTransition(r6)
            goto L94
        L48:
            r5.setLayoutTransition(r3)
            boolean r6 = androidx.transition.x.c
            java.lang.String r0 = "ViewGroupUtilsApi14"
            if (r6 != 0) goto L66
            java.lang.Class<android.view.ViewGroup> r6 = android.view.ViewGroup.class
            java.lang.String r4 = "mLayoutSuppressed"
            java.lang.reflect.Field r6 = r6.getDeclaredField(r4)     // Catch: java.lang.NoSuchFieldException -> L5f
            androidx.transition.x.b = r6     // Catch: java.lang.NoSuchFieldException -> L5f
            r6.setAccessible(r1)     // Catch: java.lang.NoSuchFieldException -> L5f
            goto L64
        L5f:
            java.lang.String r6 = "Failed to access mLayoutSuppressed field by reflection"
            android.util.Log.i(r0, r6)
        L64:
            androidx.transition.x.c = r1
        L66:
            java.lang.reflect.Field r6 = androidx.transition.x.b
            if (r6 == 0) goto L7f
            boolean r6 = r6.getBoolean(r5)     // Catch: java.lang.IllegalAccessException -> L7a
            if (r6 == 0) goto L78
            java.lang.reflect.Field r1 = androidx.transition.x.b     // Catch: java.lang.IllegalAccessException -> L76
            r1.setBoolean(r5, r2)     // Catch: java.lang.IllegalAccessException -> L76
            goto L78
        L76:
            r2 = r6
            goto L7a
        L78:
            r2 = r6
            goto L7f
        L7a:
            java.lang.String r6 = "Failed to get mLayoutSuppressed field by reflection"
            android.util.Log.i(r0, r6)
        L7f:
            if (r2 == 0) goto L84
            r5.requestLayout()
        L84:
            int r6 = androidx.transition.R.id.transition_layout_save
            java.lang.Object r0 = r5.getTag(r6)
            android.animation.LayoutTransition r0 = (android.animation.LayoutTransition) r0
            if (r0 == 0) goto L94
            r5.setTag(r6, r3)
            r5.setLayoutTransition(r0)
        L94:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.x.b(android.view.ViewGroup, boolean):void");
    }
}
