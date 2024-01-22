package com.blankj.utilcode.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.Utils;
import com.blankj.utilcode.util.UtilsTransActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public final class PermissionUtils {
    public static PermissionUtils m;
    public static SimpleCallback n;
    public static SimpleCallback o;

    /* renamed from: a  reason: collision with root package name */
    public String[] f2275a;
    public OnExplainListener b;
    public OnRationaleListener c;
    public SingleCallback d;
    public SimpleCallback e;
    public FullCallback f;
    public ThemeCallback g;
    public Set<String> h;
    public List<String> i;
    public List<String> j;
    public List<String> k;
    public List<String> l;

    /* loaded from: classes.dex */
    public interface FullCallback {
        void onDenied(@NonNull List<String> list, @NonNull List<String> list2);

        void onGranted(@NonNull List<String> list);
    }

    /* loaded from: classes.dex */
    public interface OnExplainListener {

        /* loaded from: classes.dex */
        public interface ShouldRequest {
            void start(boolean z);
        }

        void explain(@NonNull UtilsTransActivity utilsTransActivity, @NonNull List<String> list, @NonNull ShouldRequest shouldRequest);
    }

    /* loaded from: classes.dex */
    public interface OnRationaleListener {

        /* loaded from: classes.dex */
        public interface ShouldRequest {
            void again(boolean z);
        }

        void rationale(@NonNull UtilsTransActivity utilsTransActivity, @NonNull ShouldRequest shouldRequest);
    }

    /* loaded from: classes.dex */
    public interface SimpleCallback {
        void onDenied();

        void onGranted();
    }

    /* loaded from: classes.dex */
    public interface SingleCallback {
        void callback(boolean z, @NonNull List<String> list, @NonNull List<String> list2, @NonNull List<String> list3);
    }

    /* loaded from: classes.dex */
    public interface ThemeCallback {
        void onActivityCreate(@NonNull Activity activity);
    }

    /* loaded from: classes.dex */
    public class a implements OnRationaleListener.ShouldRequest {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Runnable f2276a;
        public final /* synthetic */ UtilsTransActivity b;

        public a(Runnable runnable, UtilsTransActivity utilsTransActivity) {
            this.f2276a = runnable;
            this.b = utilsTransActivity;
        }

        @Override // com.blankj.utilcode.util.PermissionUtils.OnRationaleListener.ShouldRequest
        public void again(boolean z) {
            if (z) {
                PermissionUtils.this.k = new ArrayList();
                PermissionUtils.this.l = new ArrayList();
                this.f2276a.run();
                return;
            }
            this.b.finish();
            PermissionUtils.this.v();
        }
    }

    @RequiresApi(api = 23)
    /* loaded from: classes.dex */
    public static final class b extends UtilsTransActivity.TransActivityDelegate {
        private static b INSTANCE = new b();
        private static final String TYPE = "TYPE";
        private static final int TYPE_DRAW_OVERLAYS = 3;
        private static final int TYPE_RUNTIME = 1;
        private static final int TYPE_WRITE_SETTINGS = 2;
        private static int currentRequestCode = -1;

        /* loaded from: classes.dex */
        public static class a implements Utils.Consumer<Intent> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ int f2277a;

            public a(int i) {
                this.f2277a = i;
            }

            @Override // com.blankj.utilcode.util.Utils.Consumer
            /* renamed from: a */
            public void accept(Intent intent) {
                intent.putExtra(b.TYPE, this.f2277a);
            }
        }

        /* renamed from: com.blankj.utilcode.util.PermissionUtils$b$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0207b implements OnExplainListener.ShouldRequest {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ UtilsTransActivity f2278a;

            public C0207b(UtilsTransActivity utilsTransActivity) {
                this.f2278a = utilsTransActivity;
            }

            @Override // com.blankj.utilcode.util.PermissionUtils.OnExplainListener.ShouldRequest
            public void start(boolean z) {
                if (!z) {
                    this.f2278a.finish();
                } else {
                    b.this.requestPermissions(this.f2278a);
                }
            }
        }

        /* loaded from: classes.dex */
        public class c implements Runnable {
            public final /* synthetic */ UtilsTransActivity h;

            public c(b bVar, UtilsTransActivity utilsTransActivity) {
                this.h = utilsTransActivity;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.requestPermissions((String[]) PermissionUtils.m.i.toArray(new String[0]), 1);
            }
        }

        private void checkRequestCallback(int i) {
            if (i == 2) {
                if (PermissionUtils.n == null) {
                    return;
                }
                if (PermissionUtils.isGrantedWriteSettings()) {
                    PermissionUtils.n.onGranted();
                } else {
                    PermissionUtils.n.onDenied();
                }
                SimpleCallback unused = PermissionUtils.n = null;
            } else if (i != 3 || PermissionUtils.o == null) {
            } else {
                if (PermissionUtils.isGrantedDrawOverlays()) {
                    PermissionUtils.o.onGranted();
                } else {
                    PermissionUtils.o.onDenied();
                }
                SimpleCallback unused2 = PermissionUtils.o = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void requestPermissions(UtilsTransActivity utilsTransActivity) {
            if (PermissionUtils.m.w(utilsTransActivity, new c(this, utilsTransActivity))) {
                return;
            }
            utilsTransActivity.requestPermissions((String[]) PermissionUtils.m.i.toArray(new String[0]), 1);
        }

        public static void start(int i) {
            UtilsTransActivity.start(new a(i), INSTANCE);
        }

        @Override // com.blankj.utilcode.util.UtilsTransActivity.TransActivityDelegate
        public boolean dispatchTouchEvent(@NonNull UtilsTransActivity utilsTransActivity, MotionEvent motionEvent) {
            Objects.requireNonNull(utilsTransActivity, "Argument 'activity' of type UtilsTransActivity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            utilsTransActivity.finish();
            return true;
        }

        @Override // com.blankj.utilcode.util.UtilsTransActivity.TransActivityDelegate
        public void onActivityResult(@NonNull UtilsTransActivity utilsTransActivity, int i, int i2, Intent intent) {
            Objects.requireNonNull(utilsTransActivity, "Argument 'activity' of type UtilsTransActivity (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            utilsTransActivity.finish();
        }

        @Override // com.blankj.utilcode.util.UtilsTransActivity.TransActivityDelegate
        public void onCreated(@NonNull UtilsTransActivity utilsTransActivity, @Nullable Bundle bundle) {
            Objects.requireNonNull(utilsTransActivity, "Argument 'activity' of type UtilsTransActivity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            utilsTransActivity.getWindow().addFlags(262160);
            int intExtra = utilsTransActivity.getIntent().getIntExtra(TYPE, -1);
            if (intExtra != 1) {
                if (intExtra == 2) {
                    currentRequestCode = 2;
                    PermissionUtils.z(utilsTransActivity, 2);
                } else if (intExtra == 3) {
                    currentRequestCode = 3;
                    PermissionUtils.x(utilsTransActivity, 3);
                } else {
                    utilsTransActivity.finish();
                    Log.e("PermissionUtils", "type is wrong.");
                }
            } else if (PermissionUtils.m != null) {
                if (PermissionUtils.m.i != null) {
                    if (PermissionUtils.m.i.size() > 0) {
                        if (PermissionUtils.m.g != null) {
                            PermissionUtils.m.g.onActivityCreate(utilsTransActivity);
                        }
                        if (PermissionUtils.m.b != null) {
                            PermissionUtils.m.b.explain(utilsTransActivity, PermissionUtils.m.i, new C0207b(utilsTransActivity));
                            PermissionUtils.m.b = null;
                            return;
                        }
                        requestPermissions(utilsTransActivity);
                        return;
                    }
                    Log.e("PermissionUtils", "mPermissionsRequest's size is no more than 0.");
                    utilsTransActivity.finish();
                    return;
                }
                Log.e("PermissionUtils", "mPermissionsRequest is null.");
                utilsTransActivity.finish();
            } else {
                Log.e("PermissionUtils", "sInstance is null.");
                utilsTransActivity.finish();
            }
        }

        @Override // com.blankj.utilcode.util.UtilsTransActivity.TransActivityDelegate
        public void onDestroy(@NonNull UtilsTransActivity utilsTransActivity) {
            Objects.requireNonNull(utilsTransActivity, "Argument 'activity' of type UtilsTransActivity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            int i = currentRequestCode;
            if (i != -1) {
                checkRequestCallback(i);
                currentRequestCode = -1;
            }
            super.onDestroy(utilsTransActivity);
        }

        @Override // com.blankj.utilcode.util.UtilsTransActivity.TransActivityDelegate
        public void onRequestPermissionsResult(@NonNull UtilsTransActivity utilsTransActivity, int i, @NonNull String[] strArr, @NonNull int[] iArr) {
            Objects.requireNonNull(utilsTransActivity, "Argument 'activity' of type UtilsTransActivity (#0 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            Objects.requireNonNull(strArr, "Argument 'permissions' of type String[] (#2 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            Objects.requireNonNull(iArr, "Argument 'grantResults' of type int[] (#3 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            utilsTransActivity.finish();
            if (PermissionUtils.m == null || PermissionUtils.m.i == null) {
                return;
            }
            PermissionUtils.m.t(utilsTransActivity);
        }
    }

    public PermissionUtils(String... strArr) {
        this.f2275a = strArr;
        m = this;
    }

    public static List<String> getPermissions() {
        return getPermissions(Utils.getApp().getPackageName());
    }

    public static boolean isGranted(String... strArr) {
        Pair<List<String>, List<String>> r = r(strArr);
        if (((List) r.second).isEmpty()) {
            for (String str : (List) r.first) {
                if (!s(str)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @RequiresApi(api = 23)
    public static boolean isGrantedDrawOverlays() {
        return Settings.canDrawOverlays(Utils.getApp());
    }

    @RequiresApi(api = 23)
    public static boolean isGrantedWriteSettings() {
        return Settings.System.canWrite(Utils.getApp());
    }

    public static void launchAppDetailsSettings() {
        Intent X = com.blankj.utilcode.util.b.X(Utils.getApp().getPackageName(), true);
        if (com.blankj.utilcode.util.b.w0(X)) {
            Utils.getApp().startActivity(X);
        }
    }

    public static PermissionUtils permission(String... strArr) {
        return new PermissionUtils(strArr);
    }

    public static PermissionUtils permissionGroup(String... strArr) {
        return permission(strArr);
    }

    public static Pair<List<String>, List<String>> r(String... strArr) {
        String[] permissions;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        List<String> permissions2 = getPermissions();
        for (String str : strArr) {
            boolean z = false;
            for (String str2 : PermissionConstants.getPermissions(str)) {
                if (permissions2.contains(str2)) {
                    arrayList.add(str2);
                    z = true;
                }
            }
            if (!z) {
                arrayList2.add(str);
                Log.e("PermissionUtils", "U should add the permission of " + str + " in manifest.");
            }
        }
        return Pair.create(arrayList, arrayList2);
    }

    @RequiresApi(api = 23)
    public static void requestDrawOverlays(SimpleCallback simpleCallback) {
        if (!isGrantedDrawOverlays()) {
            o = simpleCallback;
            b.start(3);
        } else if (simpleCallback != null) {
            simpleCallback.onGranted();
        }
    }

    @RequiresApi(api = 23)
    public static void requestWriteSettings(SimpleCallback simpleCallback) {
        if (!isGrantedWriteSettings()) {
            n = simpleCallback;
            b.start(2);
        } else if (simpleCallback != null) {
            simpleCallback.onGranted();
        }
    }

    public static boolean s(String str) {
        return Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(Utils.getApp(), str) == 0;
    }

    @TargetApi(23)
    public static void x(Activity activity, int i) {
        Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
        intent.setData(Uri.parse("package:" + Utils.getApp().getPackageName()));
        if (!com.blankj.utilcode.util.b.w0(intent)) {
            launchAppDetailsSettings();
        } else {
            activity.startActivityForResult(intent, i);
        }
    }

    @TargetApi(23)
    public static void z(Activity activity, int i) {
        Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
        intent.setData(Uri.parse("package:" + Utils.getApp().getPackageName()));
        if (!com.blankj.utilcode.util.b.w0(intent)) {
            launchAppDetailsSettings();
        } else {
            activity.startActivityForResult(intent, i);
        }
    }

    public PermissionUtils callback(SingleCallback singleCallback) {
        this.d = singleCallback;
        return this;
    }

    public PermissionUtils explain(OnExplainListener onExplainListener) {
        this.b = onExplainListener;
        return this;
    }

    public final void q(Activity activity) {
        for (String str : this.i) {
            if (s(str)) {
                this.j.add(str);
            } else {
                this.k.add(str);
                if (!activity.shouldShowRequestPermissionRationale(str)) {
                    this.l.add(str);
                }
            }
        }
    }

    public PermissionUtils rationale(OnRationaleListener onRationaleListener) {
        this.c = onRationaleListener;
        return this;
    }

    public void request() {
        String[] strArr = this.f2275a;
        if (strArr != null && strArr.length > 0) {
            this.h = new LinkedHashSet();
            this.i = new ArrayList();
            this.j = new ArrayList();
            this.k = new ArrayList();
            this.l = new ArrayList();
            Pair<List<String>, List<String>> r = r(this.f2275a);
            this.h.addAll((Collection) r.first);
            this.k.addAll((Collection) r.second);
            if (Build.VERSION.SDK_INT < 23) {
                this.j.addAll(this.h);
                v();
                return;
            }
            for (String str : this.h) {
                if (s(str)) {
                    this.j.add(str);
                } else {
                    this.i.add(str);
                }
            }
            if (this.i.isEmpty()) {
                v();
                return;
            } else {
                y();
                return;
            }
        }
        Log.w("PermissionUtils", "No permissions to request.");
    }

    public final void t(Activity activity) {
        q(activity);
        v();
    }

    public PermissionUtils theme(ThemeCallback themeCallback) {
        this.g = themeCallback;
        return this;
    }

    public final void u(UtilsTransActivity utilsTransActivity, Runnable runnable) {
        q(utilsTransActivity);
        this.c.rationale(utilsTransActivity, new a(runnable, utilsTransActivity));
    }

    public final void v() {
        SingleCallback singleCallback = this.d;
        if (singleCallback != null) {
            singleCallback.callback(this.k.isEmpty(), this.j, this.l, this.k);
            this.d = null;
        }
        if (this.e != null) {
            if (this.k.isEmpty()) {
                this.e.onGranted();
            } else {
                this.e.onDenied();
            }
            this.e = null;
        }
        if (this.f != null) {
            if (this.i.size() == 0 || this.j.size() > 0) {
                this.f.onGranted(this.j);
            }
            if (!this.k.isEmpty()) {
                this.f.onDenied(this.l, this.k);
            }
            this.f = null;
        }
        this.c = null;
        this.g = null;
    }

    @RequiresApi(api = 23)
    public final boolean w(UtilsTransActivity utilsTransActivity, Runnable runnable) {
        boolean z = false;
        if (this.c != null) {
            Iterator<String> it = this.i.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (utilsTransActivity.shouldShowRequestPermissionRationale(it.next())) {
                    u(utilsTransActivity, runnable);
                    z = true;
                    break;
                }
            }
            this.c = null;
        }
        return z;
    }

    @RequiresApi(api = 23)
    public final void y() {
        b.start(1);
    }

    public static List<String> getPermissions(String str) {
        try {
            String[] strArr = Utils.getApp().getPackageManager().getPackageInfo(str, 4096).requestedPermissions;
            if (strArr == null) {
                return Collections.emptyList();
            }
            return Arrays.asList(strArr);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public PermissionUtils callback(SimpleCallback simpleCallback) {
        this.e = simpleCallback;
        return this;
    }

    public PermissionUtils callback(FullCallback fullCallback) {
        this.f = fullCallback;
        return this;
    }
}
