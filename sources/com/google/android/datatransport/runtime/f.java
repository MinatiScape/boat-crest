package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.logging.Logging;
import java.util.concurrent.Executor;
/* loaded from: classes6.dex */
public class f implements Executor {
    public final Executor h;

    /* loaded from: classes6.dex */
    public static class a implements Runnable {
        public final Runnable h;

        public a(Runnable runnable) {
            this.h = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.h.run();
            } catch (Exception e) {
                Logging.e("Executor", "Background execution failure.", e);
            }
        }
    }

    public f(Executor executor) {
        this.h = executor;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.h.execute(new a(runnable));
    }
}
