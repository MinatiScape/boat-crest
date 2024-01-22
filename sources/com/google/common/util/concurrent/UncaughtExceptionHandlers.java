package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.lang.Thread;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
@GwtIncompatible
/* loaded from: classes10.dex */
public final class UncaughtExceptionHandlers {

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static final class a implements Thread.UncaughtExceptionHandler {
        public static final Logger b = Logger.getLogger(a.class.getName());

        /* renamed from: a  reason: collision with root package name */
        public final Runtime f10806a;

        public a(Runtime runtime) {
            this.f10806a = runtime;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            try {
                b.log(Level.SEVERE, String.format(Locale.ROOT, "Caught an exception in %s.  Shutting down.", thread), th);
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    public static Thread.UncaughtExceptionHandler systemExit() {
        return new a(Runtime.getRuntime());
    }
}
