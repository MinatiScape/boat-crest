package com.google.android.gms.analytics;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzfa;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Objects;
@VisibleForTesting
/* loaded from: classes6.dex */
public class ExceptionReporter implements Thread.UncaughtExceptionHandler {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Thread.UncaughtExceptionHandler f8176a;
    public final Tracker b;
    public final Context c;
    @Nullable
    public ExceptionParser d;
    @Nullable
    public GoogleAnalytics e;

    public ExceptionReporter(@RecentlyNonNull Tracker tracker, @Nullable Thread.UncaughtExceptionHandler uncaughtExceptionHandler, @RecentlyNonNull Context context) {
        Objects.requireNonNull(tracker, "tracker cannot be null");
        Objects.requireNonNull(context, "context cannot be null");
        this.f8176a = uncaughtExceptionHandler;
        this.b = tracker;
        this.d = new StandardExceptionParser(context, new ArrayList());
        this.c = context.getApplicationContext();
        String name = uncaughtExceptionHandler == null ? "null" : uncaughtExceptionHandler.getClass().getName();
        zzfa.zzd(name.length() != 0 ? "ExceptionReporter created, original handler is ".concat(name) : new String("ExceptionReporter created, original handler is "));
    }

    @Nullable
    public final Thread.UncaughtExceptionHandler a() {
        return this.f8176a;
    }

    @RecentlyNullable
    public ExceptionParser getExceptionParser() {
        return this.d;
    }

    public void setExceptionParser(@Nullable ExceptionParser exceptionParser) {
        this.d = exceptionParser;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(@RecentlyNonNull Thread thread, @RecentlyNonNull Throwable th) {
        String str;
        if (this.d != null) {
            str = this.d.getDescription(thread != null ? thread.getName() : null, th);
        } else {
            str = "UncaughtException";
        }
        String valueOf = String.valueOf(str);
        zzfa.zzd(valueOf.length() != 0 ? "Reporting uncaught exception: ".concat(valueOf) : new String("Reporting uncaught exception: "));
        Tracker tracker = this.b;
        HitBuilders.ExceptionBuilder exceptionBuilder = new HitBuilders.ExceptionBuilder();
        exceptionBuilder.setDescription(str);
        exceptionBuilder.setFatal(true);
        tracker.send(exceptionBuilder.build());
        if (this.e == null) {
            this.e = GoogleAnalytics.getInstance(this.c);
        }
        GoogleAnalytics googleAnalytics = this.e;
        googleAnalytics.dispatchLocalHits();
        googleAnalytics.a().zzf().zzn();
        if (this.f8176a != null) {
            zzfa.zzd("Passing exception to the original handler");
            this.f8176a.uncaughtException(thread, th);
        }
    }
}
