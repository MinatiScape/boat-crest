package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;
@GwtIncompatible
/* loaded from: classes10.dex */
public final class RemovalListeners {

    /* JADX INFO: Add missing generic type declarations: [V, K] */
    /* loaded from: classes10.dex */
    public class a<K, V> implements RemovalListener<K, V> {
        public final /* synthetic */ Executor h;
        public final /* synthetic */ RemovalListener i;

        /* renamed from: com.google.common.cache.RemovalListeners$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class RunnableC0457a implements Runnable {
            public final /* synthetic */ RemovalNotification h;

            public RunnableC0457a(RemovalNotification removalNotification) {
                this.h = removalNotification;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.i.onRemoval(this.h);
            }
        }

        public a(Executor executor, RemovalListener removalListener) {
            this.h = executor;
            this.i = removalListener;
        }

        @Override // com.google.common.cache.RemovalListener
        public void onRemoval(RemovalNotification<K, V> removalNotification) {
            this.h.execute(new RunnableC0457a(removalNotification));
        }
    }

    public static <K, V> RemovalListener<K, V> asynchronous(RemovalListener<K, V> removalListener, Executor executor) {
        Preconditions.checkNotNull(removalListener);
        Preconditions.checkNotNull(executor);
        return new a(executor, removalListener);
    }
}
