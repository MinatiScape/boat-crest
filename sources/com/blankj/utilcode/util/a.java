package com.blankj.utilcode.util;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import com.blankj.utilcode.util.Utils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes.dex */
public final class a implements Application.ActivityLifecycleCallbacks {
    public static final a n = new a();
    public static final Activity o = new Activity();
    public final LinkedList<Activity> h = new LinkedList<>();
    public final List<Utils.OnAppStatusChangedListener> i = new CopyOnWriteArrayList();
    public final Map<Activity, List<Utils.ActivityLifecycleCallbacks>> j = new ConcurrentHashMap();
    public int k = 0;
    public int l = 0;
    public boolean m = false;

    /* renamed from: com.blankj.utilcode.util.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class RunnableC0208a implements Runnable {
        public final /* synthetic */ Activity h;
        public final /* synthetic */ Utils.ActivityLifecycleCallbacks i;

        public RunnableC0208a(Activity activity, Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
            this.h = activity;
            this.i = activityLifecycleCallbacks;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.f(this.h, this.i);
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public final /* synthetic */ Activity h;

        public b(Activity activity) {
            this.h = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.j.remove(this.h);
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public final /* synthetic */ Activity h;
        public final /* synthetic */ Utils.ActivityLifecycleCallbacks i;

        public c(Activity activity, Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
            this.h = activity;
            this.i = activityLifecycleCallbacks;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.x(this.h, this.i);
        }
    }

    /* loaded from: classes.dex */
    public class d implements Runnable {
        public final /* synthetic */ Activity h;
        public final /* synthetic */ Object i;

        public d(a aVar, Activity activity, Object obj) {
            this.h = activity;
            this.i = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Window window = this.h.getWindow();
                if (window != null) {
                    window.setSoftInputMode(((Integer) this.i).intValue());
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void z() {
        if (Build.VERSION.SDK_INT < 26 || !ValueAnimator.areAnimatorsEnabled()) {
            try {
                Field declaredField = ValueAnimator.class.getDeclaredField("sDurationScale");
                declaredField.setAccessible(true);
                if (((Float) declaredField.get(null)).floatValue() == 0.0f) {
                    declaredField.set(null, Float.valueOf(1.0f));
                    Log.i("UtilsActivityLifecycle", "setAnimatorsEnabled: Animators are enabled now!");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
            }
        }
    }

    public final void A(Activity activity) {
        if (this.h.contains(activity)) {
            if (this.h.getFirst().equals(activity)) {
                return;
            }
            this.h.remove(activity);
            this.h.addFirst(activity);
            return;
        }
        this.h.addFirst(activity);
    }

    public void B(Application application) {
        this.h.clear();
        application.unregisterActivityLifecycleCallbacks(this);
    }

    public void d(Activity activity, Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        if (activity == null || activityLifecycleCallbacks == null) {
            return;
        }
        com.blankj.utilcode.util.b.U0(new RunnableC0208a(activity, activityLifecycleCallbacks));
    }

    public void e(Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        d(o, activityLifecycleCallbacks);
    }

    public final void f(Activity activity, Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        List<Utils.ActivityLifecycleCallbacks> list = this.j.get(activity);
        if (list == null) {
            list = new CopyOnWriteArrayList<>();
            this.j.put(activity, list);
        } else if (list.contains(activityLifecycleCallbacks)) {
            return;
        }
        list.add(activityLifecycleCallbacks);
    }

    public void g(Utils.OnAppStatusChangedListener onAppStatusChangedListener) {
        this.i.add(onAppStatusChangedListener);
    }

    public final void h(Activity activity, Lifecycle.Event event) {
        i(activity, event, this.j.get(activity));
        i(activity, event, this.j.get(o));
    }

    public final void i(Activity activity, Lifecycle.Event event, List<Utils.ActivityLifecycleCallbacks> list) {
        if (list == null) {
            return;
        }
        for (Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks : list) {
            activityLifecycleCallbacks.onLifecycleChanged(activity, event);
            if (event.equals(Lifecycle.Event.ON_CREATE)) {
                activityLifecycleCallbacks.onActivityCreated(activity);
            } else if (event.equals(Lifecycle.Event.ON_START)) {
                activityLifecycleCallbacks.onActivityStarted(activity);
            } else if (event.equals(Lifecycle.Event.ON_RESUME)) {
                activityLifecycleCallbacks.onActivityResumed(activity);
            } else if (event.equals(Lifecycle.Event.ON_PAUSE)) {
                activityLifecycleCallbacks.onActivityPaused(activity);
            } else if (event.equals(Lifecycle.Event.ON_STOP)) {
                activityLifecycleCallbacks.onActivityStopped(activity);
            } else if (event.equals(Lifecycle.Event.ON_DESTROY)) {
                activityLifecycleCallbacks.onActivityDestroyed(activity);
            }
        }
        if (event.equals(Lifecycle.Event.ON_DESTROY)) {
            this.j.remove(activity);
        }
    }

    public final List<Activity> j() {
        Object obj;
        LinkedList linkedList = new LinkedList();
        Activity activity = null;
        try {
            Object l = l();
            Field declaredField = l.getClass().getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            obj = declaredField.get(l);
        } catch (Exception e) {
            Log.e("UtilsActivityLifecycle", "getActivitiesByReflect: " + e.getMessage());
        }
        if (obj instanceof Map) {
            for (Object obj2 : ((Map) obj).values()) {
                Class<?> cls = obj2.getClass();
                Field declaredField2 = cls.getDeclaredField("activity");
                declaredField2.setAccessible(true);
                Activity activity2 = (Activity) declaredField2.get(obj2);
                if (activity == null) {
                    Field declaredField3 = cls.getDeclaredField("paused");
                    declaredField3.setAccessible(true);
                    if (declaredField3.getBoolean(obj2)) {
                        linkedList.add(activity2);
                    } else {
                        activity = activity2;
                    }
                } else {
                    linkedList.add(activity2);
                }
            }
            if (activity != null) {
                linkedList.addFirst(activity);
            }
            return linkedList;
        }
        return linkedList;
    }

    public List<Activity> k() {
        if (!this.h.isEmpty()) {
            return new LinkedList(this.h);
        }
        this.h.addAll(j());
        return new LinkedList(this.h);
    }

    public final Object l() {
        Object m = m();
        return m != null ? m : n();
    }

    public final Object m() {
        try {
            Field declaredField = Class.forName("android.app.ActivityThread").getDeclaredField("sCurrentActivityThread");
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (Exception e) {
            Log.e("UtilsActivityLifecycle", "getActivityThreadInActivityThreadStaticField: " + e.getMessage());
            return null;
        }
    }

    public final Object n() {
        try {
            return Class.forName("android.app.ActivityThread").getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            Log.e("UtilsActivityLifecycle", "getActivityThreadInActivityThreadStaticMethod: " + e.getMessage());
            return null;
        }
    }

    public Application o() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("getApplication", new Class[0]).invoke(l(), new Object[0]);
            if (invoke == null) {
                return null;
            }
            return (Application) invoke;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return null;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(@NonNull Activity activity, Bundle bundle) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        if (this.h.size() == 0) {
            s(activity, true);
        }
        LanguageUtils.b(activity);
        z();
        A(activity);
        h(activity, Lifecycle.Event.ON_CREATE);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        this.h.remove(activity);
        com.blankj.utilcode.util.b.E(activity);
        h(activity, Lifecycle.Event.ON_DESTROY);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        h(activity, Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostDestroyed(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostPaused(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostResumed(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostSaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(bundle, "Argument 'outState' of type Bundle (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostStarted(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostStopped(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreDestroyed(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPrePaused(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreResumed(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreSaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(bundle, "Argument 'outState' of type Bundle (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreStarted(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreStopped(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        A(activity);
        if (this.m) {
            this.m = false;
            s(activity, true);
        }
        t(activity, false);
        h(activity, Lifecycle.Event.ON_RESUME);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(bundle, "Argument 'outState' of type Bundle (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        if (!this.m) {
            A(activity);
        }
        int i = this.l;
        if (i < 0) {
            this.l = i + 1;
        } else {
            this.k++;
        }
        h(activity, Lifecycle.Event.ON_START);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        if (activity.isChangingConfigurations()) {
            this.l--;
        } else {
            int i = this.k - 1;
            this.k = i;
            if (i <= 0) {
                this.m = true;
                s(activity, false);
            }
        }
        t(activity, true);
        h(activity, Lifecycle.Event.ON_STOP);
    }

    public Activity p() {
        for (Activity activity : k()) {
            if (com.blankj.utilcode.util.b.o0(activity)) {
                return activity;
            }
        }
        return null;
    }

    public void q(Application application) {
        application.registerActivityLifecycleCallbacks(this);
    }

    public boolean r() {
        return !this.m;
    }

    public final void s(Activity activity, boolean z) {
        if (this.i.isEmpty()) {
            return;
        }
        for (Utils.OnAppStatusChangedListener onAppStatusChangedListener : this.i) {
            if (z) {
                onAppStatusChangedListener.onForeground(activity);
            } else {
                onAppStatusChangedListener.onBackground(activity);
            }
        }
    }

    public final void t(Activity activity, boolean z) {
        try {
            if (z) {
                Window window = activity.getWindow();
                window.getDecorView().setTag(-123, Integer.valueOf(window.getAttributes().softInputMode));
                window.setSoftInputMode(3);
            } else {
                Object tag = activity.getWindow().getDecorView().getTag(-123);
                if (!(tag instanceof Integer)) {
                    return;
                }
                com.blankj.utilcode.util.b.V0(new d(this, activity, tag), 100L);
            }
        } catch (Exception unused) {
        }
    }

    public void u(Activity activity) {
        if (activity == null) {
            return;
        }
        com.blankj.utilcode.util.b.U0(new b(activity));
    }

    public void v(Activity activity, Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        if (activity == null || activityLifecycleCallbacks == null) {
            return;
        }
        com.blankj.utilcode.util.b.U0(new c(activity, activityLifecycleCallbacks));
    }

    public void w(Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        v(o, activityLifecycleCallbacks);
    }

    public final void x(Activity activity, Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        List<Utils.ActivityLifecycleCallbacks> list = this.j.get(activity);
        if (list == null || list.isEmpty()) {
            return;
        }
        list.remove(activityLifecycleCallbacks);
    }

    public void y(Utils.OnAppStatusChangedListener onAppStatusChangedListener) {
        this.i.remove(onAppStatusChangedListener);
    }
}
