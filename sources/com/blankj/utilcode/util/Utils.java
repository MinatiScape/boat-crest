package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import com.blankj.utilcode.util.ThreadUtils;
import java.util.Objects;
/* loaded from: classes.dex */
public final class Utils {
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: a  reason: collision with root package name */
    public static Application f2300a;

    /* loaded from: classes.dex */
    public static class ActivityLifecycleCallbacks {
        public void onActivityCreated(@NonNull Activity activity) {
            Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }

        public void onActivityDestroyed(@NonNull Activity activity) {
            Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }

        public void onActivityPaused(@NonNull Activity activity) {
            Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }

        public void onActivityResumed(@NonNull Activity activity) {
            Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }

        public void onActivityStarted(@NonNull Activity activity) {
            Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }

        public void onActivityStopped(@NonNull Activity activity) {
            Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }

        public void onLifecycleChanged(@NonNull Activity activity, Lifecycle.Event event) {
            Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
    }

    /* loaded from: classes.dex */
    public interface Consumer<T> {
        void accept(T t);
    }

    /* loaded from: classes.dex */
    public interface Func1<Ret, Par> {
        Ret call(Par par);
    }

    /* loaded from: classes.dex */
    public interface OnAppStatusChangedListener {
        void onBackground(Activity activity);

        void onForeground(Activity activity);
    }

    /* loaded from: classes.dex */
    public interface Supplier<T> {
        T get();
    }

    /* loaded from: classes.dex */
    public static abstract class Task<Result> extends ThreadUtils.SimpleTask<Result> {
        public Consumer<Result> o;

        public Task(Consumer<Result> consumer) {
            this.o = consumer;
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        public void onSuccess(Result result) {
            Consumer<Result> consumer = this.o;
            if (consumer != null) {
                consumer.accept(result);
            }
        }
    }

    public Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Application getApp() {
        Application application = f2300a;
        if (application != null) {
            return application;
        }
        init(b.L());
        Objects.requireNonNull(f2300a, "reflect failed.");
        Log.i("Utils", b.N() + " reflect app success.");
        return f2300a;
    }

    public static void init(Application application) {
        if (application == null) {
            Log.e("Utils", "app is null.");
            return;
        }
        Application application2 = f2300a;
        if (application2 == null) {
            f2300a = application;
            b.l0(application);
            b.K0();
        } else if (application2.equals(application)) {
        } else {
            b.d1(f2300a);
            f2300a = application;
            b.l0(application);
        }
    }
}
