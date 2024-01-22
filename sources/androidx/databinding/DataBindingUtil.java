package androidx.databinding;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public class DataBindingUtil {

    /* renamed from: a  reason: collision with root package name */
    public static DataBinderMapper f1213a = new DataBinderMapperImpl();
    public static DataBindingComponent b = null;

    public static <T extends ViewDataBinding> T a(DataBindingComponent dataBindingComponent, View view, int i) {
        return (T) f1213a.getDataBinder(dataBindingComponent, view, i);
    }

    public static <T extends ViewDataBinding> T b(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        return (T) f1213a.getDataBinder(dataBindingComponent, viewArr, i);
    }

    @Nullable
    public static <T extends ViewDataBinding> T bind(@NonNull View view) {
        return (T) bind(view, b);
    }

    public static <T extends ViewDataBinding> T c(DataBindingComponent dataBindingComponent, ViewGroup viewGroup, int i, int i2) {
        int childCount = viewGroup.getChildCount();
        int i3 = childCount - i;
        if (i3 == 1) {
            return (T) a(dataBindingComponent, viewGroup.getChildAt(childCount - 1), i2);
        }
        View[] viewArr = new View[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            viewArr[i4] = viewGroup.getChildAt(i4 + i);
        }
        return (T) b(dataBindingComponent, viewArr, i2);
    }

    @Nullable
    public static String convertBrIdToString(int i) {
        return f1213a.convertBrIdToString(i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0035, code lost:
        if (r3 == (-1)) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0038, code lost:
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
        r7 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0047, code lost:
        if (r1.indexOf(47, r3 + 1) == (-1)) goto L15;
     */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T extends androidx.databinding.ViewDataBinding> T findBinding(@androidx.annotation.NonNull android.view.View r9) {
        /*
        L0:
            r0 = 0
            if (r9 == 0) goto L5a
            androidx.databinding.ViewDataBinding r1 = androidx.databinding.ViewDataBinding.getBinding(r9)
            if (r1 == 0) goto La
            return r1
        La:
            java.lang.Object r1 = r9.getTag()
            boolean r2 = r1 instanceof java.lang.String
            if (r2 == 0) goto L4d
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = "layout"
            boolean r2 = r1.startsWith(r2)
            if (r2 == 0) goto L4d
            java.lang.String r2 = "_0"
            boolean r2 = r1.endsWith(r2)
            if (r2 == 0) goto L4d
            r2 = 6
            char r2 = r1.charAt(r2)
            r3 = 7
            r4 = 47
            int r3 = r1.indexOf(r4, r3)
            r5 = 1
            r6 = -1
            r7 = 0
            if (r2 != r4) goto L3b
            if (r3 != r6) goto L38
            goto L39
        L38:
            r5 = r7
        L39:
            r7 = r5
            goto L4a
        L3b:
            r8 = 45
            if (r2 != r8) goto L4a
            if (r3 == r6) goto L4a
            int r3 = r3 + 1
            int r1 = r1.indexOf(r4, r3)
            if (r1 != r6) goto L38
            goto L39
        L4a:
            if (r7 == 0) goto L4d
            return r0
        L4d:
            android.view.ViewParent r9 = r9.getParent()
            boolean r1 = r9 instanceof android.view.View
            if (r1 == 0) goto L58
            android.view.View r9 = (android.view.View) r9
            goto L0
        L58:
            r9 = r0
            goto L0
        L5a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.databinding.DataBindingUtil.findBinding(android.view.View):androidx.databinding.ViewDataBinding");
    }

    @Nullable
    public static <T extends ViewDataBinding> T getBinding(@NonNull View view) {
        return (T) ViewDataBinding.getBinding(view);
    }

    @Nullable
    public static DataBindingComponent getDefaultComponent() {
        return b;
    }

    public static <T extends ViewDataBinding> T inflate(@NonNull LayoutInflater layoutInflater, int i, @Nullable ViewGroup viewGroup, boolean z) {
        return (T) inflate(layoutInflater, i, viewGroup, z, b);
    }

    public static <T extends ViewDataBinding> T setContentView(@NonNull Activity activity, int i) {
        return (T) setContentView(activity, i, b);
    }

    public static void setDefaultComponent(@Nullable DataBindingComponent dataBindingComponent) {
        b = dataBindingComponent;
    }

    @Nullable
    public static <T extends ViewDataBinding> T bind(@NonNull View view, DataBindingComponent dataBindingComponent) {
        T t = (T) getBinding(view);
        if (t != null) {
            return t;
        }
        Object tag = view.getTag();
        if (tag instanceof String) {
            int layoutId = f1213a.getLayoutId((String) tag);
            if (layoutId != 0) {
                return (T) f1213a.getDataBinder(dataBindingComponent, view, layoutId);
            }
            throw new IllegalArgumentException("View is not a binding layout. Tag: " + tag);
        }
        throw new IllegalArgumentException("View is not a binding layout");
    }

    public static <T extends ViewDataBinding> T inflate(@NonNull LayoutInflater layoutInflater, int i, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        boolean z2 = viewGroup != null && z;
        int childCount = z2 ? viewGroup.getChildCount() : 0;
        View inflate = layoutInflater.inflate(i, viewGroup, z);
        if (z2) {
            return (T) c(dataBindingComponent, viewGroup, childCount, i);
        }
        return (T) a(dataBindingComponent, inflate, i);
    }

    public static <T extends ViewDataBinding> T setContentView(@NonNull Activity activity, int i, @Nullable DataBindingComponent dataBindingComponent) {
        activity.setContentView(i);
        return (T) c(dataBindingComponent, (ViewGroup) activity.getWindow().getDecorView().findViewById(16908290), 0, i);
    }
}
