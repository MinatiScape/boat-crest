package androidx.transition;

import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@RequiresApi(21)
/* loaded from: classes.dex */
public class f implements d {
    public static Class<?> i;
    public static boolean j;
    public static Method k;
    public static boolean l;
    public static Method m;
    public static boolean n;
    public final View h;

    public f(@NonNull View view) {
        this.h = view;
    }

    public static d b(View view, ViewGroup viewGroup, Matrix matrix) {
        c();
        Method method = k;
        if (method != null) {
            try {
                return new f((View) method.invoke(null, view, viewGroup, matrix));
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return null;
    }

    public static void c() {
        if (l) {
            return;
        }
        try {
            d();
            Method declaredMethod = i.getDeclaredMethod("addGhost", View.class, ViewGroup.class, Matrix.class);
            k = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            Log.i("GhostViewApi21", "Failed to retrieve addGhost method", e);
        }
        l = true;
    }

    public static void d() {
        if (j) {
            return;
        }
        try {
            i = Class.forName("android.view.GhostView");
        } catch (ClassNotFoundException e) {
            Log.i("GhostViewApi21", "Failed to retrieve GhostView class", e);
        }
        j = true;
    }

    public static void e() {
        if (n) {
            return;
        }
        try {
            d();
            Method declaredMethod = i.getDeclaredMethod("removeGhost", View.class);
            m = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            Log.i("GhostViewApi21", "Failed to retrieve removeGhost method", e);
        }
        n = true;
    }

    public static void f(View view) {
        e();
        Method method = m;
        if (method != null) {
            try {
                method.invoke(null, view);
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getCause());
            }
        }
    }

    @Override // androidx.transition.d
    public void a(ViewGroup viewGroup, View view) {
    }

    @Override // androidx.transition.d
    public void setVisibility(int i2) {
        this.h.setVisibility(i2);
    }
}
