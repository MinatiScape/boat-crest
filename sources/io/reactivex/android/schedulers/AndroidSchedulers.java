package io.reactivex.android.schedulers;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import java.util.Objects;
import java.util.concurrent.Callable;
/* loaded from: classes12.dex */
public final class AndroidSchedulers {

    /* renamed from: a  reason: collision with root package name */
    public static final Scheduler f13894a = RxAndroidPlugins.initMainThreadScheduler(new a());

    /* loaded from: classes12.dex */
    public static class a implements Callable<Scheduler> {
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Scheduler call() throws Exception {
            return b.f13895a;
        }
    }

    /* loaded from: classes12.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public static final Scheduler f13895a = new io.reactivex.android.schedulers.a(new Handler(Looper.getMainLooper()), false);
    }

    public AndroidSchedulers() {
        throw new AssertionError("No instances.");
    }

    public static Scheduler from(Looper looper) {
        return from(looper, false);
    }

    public static Scheduler mainThread() {
        return RxAndroidPlugins.onMainThreadScheduler(f13894a);
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
        return new io.reactivex.android.schedulers.a(new Handler(looper), z);
    }
}
