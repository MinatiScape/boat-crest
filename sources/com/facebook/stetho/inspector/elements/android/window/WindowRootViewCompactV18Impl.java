package com.facebook.stetho.inspector.elements.android.window;

import android.view.View;
import androidx.annotation.NonNull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes9.dex */
class WindowRootViewCompactV18Impl extends WindowRootViewCompat {
    private Field mViewsField;
    private Object mWindowManagerGlobal;

    public WindowRootViewCompactV18Impl() {
        try {
            Class<?> cls = Class.forName("android.view.WindowManagerGlobal");
            this.mWindowManagerGlobal = cls.getDeclaredMethod("getInstance", new Class[0]).invoke(cls, new Object[0]);
            Field declaredField = cls.getDeclaredField("mViews");
            this.mViewsField = declaredField;
            declaredField.setAccessible(true);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (NoSuchFieldException e3) {
            throw new RuntimeException(e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException(e4);
        } catch (InvocationTargetException e5) {
            throw new RuntimeException(e5);
        }
    }

    @Override // com.facebook.stetho.inspector.elements.android.window.WindowRootViewCompat
    @NonNull
    public List<View> getRootViews() {
        try {
            return Collections.unmodifiableList(Arrays.asList((View[]) this.mViewsField.get(this.mWindowManagerGlobal)));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
