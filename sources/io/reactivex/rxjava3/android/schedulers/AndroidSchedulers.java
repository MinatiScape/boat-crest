package io.reactivex.rxjava3.android.schedulers;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.Objects;
import java.util.concurrent.Callable;
/* loaded from: classes12.dex */
public final class AndroidSchedulers {

    /* renamed from: a  reason: collision with root package name */
    public static final Scheduler f13940a = RxAndroidPlugins.initMainThreadScheduler(new Callable() { // from class: io.reactivex.rxjava3.android.schedulers.a
        @Override // java.util.concurrent.Callable
        public final Object call() {
            Scheduler scheduler;
            scheduler = AndroidSchedulers.a.f13941a;
            return scheduler;
        }
    });

    /* loaded from: classes12.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final Scheduler f13941a = new b(new Handler(Looper.getMainLooper()), true);
    }

    public AndroidSchedulers() {
        throw new AssertionError("No instances.");
    }

    public static Scheduler from(Looper looper) {
        return from(looper, true);
    }

    public static Scheduler mainThread() {
        return RxAndroidPlugins.onMainThreadScheduler(f13940a);
    }

    @SuppressLint({"NewApi"})
    public static Scheduler from(Looper looper, boolean z) {
        Objects.requireNonNull(looper, "looper == null");
        int i = Build.VERSION.SDK_INT;
        if (i < 16) {
            z = false;
        } else if (z && i < 22) {
            Message obtain = Message.obtain();
            try {
                obtain.setAsynchronous(true);
            } catch (NoSuchMethodError unused) {
                z = false;
            }
            obtain.recycle();
        }
        return new b(new Handler(looper), z);
    }
}
