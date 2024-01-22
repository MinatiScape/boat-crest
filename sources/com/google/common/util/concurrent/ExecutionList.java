package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes10.dex */
public final class ExecutionList {
    public static final Logger c = Logger.getLogger(ExecutionList.class.getName());
    @NullableDecl
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    public a f10784a;
    @GuardedBy("this")
    public boolean b;

    /* loaded from: classes10.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public final Runnable f10785a;
        public final Executor b;
        @NullableDecl
        public a c;

        public a(Runnable runnable, Executor executor, a aVar) {
            this.f10785a = runnable;
            this.b = executor;
            this.c = aVar;
        }
    }

    public static void a(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = c;
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(runnable);
            String valueOf2 = String.valueOf(executor);
            StringBuilder sb = new StringBuilder(valueOf.length() + 57 + valueOf2.length());
            sb.append("RuntimeException while executing runnable ");
            sb.append(valueOf);
            sb.append(" with executor ");
            sb.append(valueOf2);
            logger.log(level, sb.toString(), (Throwable) e);
        }
    }

    public void add(Runnable runnable, Executor executor) {
        Preconditions.checkNotNull(runnable, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        synchronized (this) {
            if (!this.b) {
                this.f10784a = new a(runnable, executor, this.f10784a);
            } else {
                a(runnable, executor);
            }
        }
    }

    public void execute() {
        synchronized (this) {
            if (this.b) {
                return;
            }
            this.b = true;
            a aVar = this.f10784a;
            a aVar2 = null;
            this.f10784a = null;
            while (aVar != null) {
                a aVar3 = aVar.c;
                aVar.c = aVar2;
                aVar2 = aVar;
                aVar = aVar3;
            }
            while (aVar2 != null) {
                a(aVar2.f10785a, aVar2.b);
                aVar2 = aVar2.c;
            }
        }
    }
}
