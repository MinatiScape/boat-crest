package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.settings.SettingsDataProvider;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes10.dex */
public class j implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    public final a f11158a;
    public final SettingsDataProvider b;
    public final Thread.UncaughtExceptionHandler c;
    public final AtomicBoolean d = new AtomicBoolean(false);

    /* loaded from: classes10.dex */
    public interface a {
        void a(SettingsDataProvider settingsDataProvider, Thread thread, Throwable th);
    }

    public j(a aVar, SettingsDataProvider settingsDataProvider, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.f11158a = aVar;
        this.b = settingsDataProvider;
        this.c = uncaughtExceptionHandler;
    }

    public boolean a() {
        return this.d.get();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Thread$UncaughtExceptionHandler] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.Thread$UncaughtExceptionHandler] */
    /* JADX WARN: Type inference failed for: r3v1, types: [com.google.firebase.crashlytics.internal.Logger] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.Thread] */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.Thread] */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.util.concurrent.atomic.AtomicBoolean] */
    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        this.d.set(true);
        ?? r1 = "Completed exception processing. Invoking default exception handler.";
        try {
            try {
                if (thread == 0) {
                    Logger.getLogger().e("Could not handle uncaught exception; null thread");
                } else if (th == null) {
                    Logger.getLogger().e("Could not handle uncaught exception; null throwable");
                } else {
                    this.f11158a.a(this.b, thread, th);
                }
            } catch (Exception e) {
                Logger.getLogger().e("An error occurred in the uncaught exception handler", e);
            }
            Logger.getLogger().d("Completed exception processing. Invoking default exception handler.");
            r1 = this.c;
            r1.uncaughtException(thread, th);
            thread = this.d;
            thread.set(false);
        } catch (Throwable th2) {
            Logger.getLogger().d(r1);
            this.c.uncaughtException(thread, th);
            this.d.set(false);
            throw th2;
        }
    }
}
