package com.google.android.gms.common.api.internal;

import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import java.util.concurrent.Executor;
@KeepForSdk
/* loaded from: classes6.dex */
public final class ListenerHolder<L> {

    /* renamed from: a  reason: collision with root package name */
    public final Executor f8257a;
    @Nullable
    public volatile Object b;
    @Nullable
    public volatile ListenerKey c;

    @KeepForSdk
    /* loaded from: classes6.dex */
    public static final class ListenerKey<L> {

        /* renamed from: a  reason: collision with root package name */
        public final Object f8258a;
        public final String b;

        @KeepForSdk
        public ListenerKey(L l, String str) {
            this.f8258a = l;
            this.b = str;
        }

        @KeepForSdk
        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ListenerKey) {
                ListenerKey listenerKey = (ListenerKey) obj;
                return this.f8258a == listenerKey.f8258a && this.b.equals(listenerKey.b);
            }
            return false;
        }

        @KeepForSdk
        public int hashCode() {
            return (System.identityHashCode(this.f8258a) * 31) + this.b.hashCode();
        }

        @NonNull
        @KeepForSdk
        public String toIdString() {
            String str = this.b;
            int identityHashCode = System.identityHashCode(this.f8258a);
            return str + "@" + identityHashCode;
        }
    }

    @KeepForSdk
    /* loaded from: classes6.dex */
    public interface Notifier<L> {
        @KeepForSdk
        void notifyListener(@NonNull L l);

        @KeepForSdk
        void onNotifyListenerFailed();
    }

    @KeepForSdk
    public ListenerHolder(@NonNull Looper looper, @NonNull L l, @NonNull String str) {
        this.f8257a = new HandlerExecutor(looper);
        this.b = Preconditions.checkNotNull(l, "Listener must not be null");
        this.c = new ListenerKey(l, Preconditions.checkNotEmpty(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(Notifier notifier) {
        Object obj = this.b;
        if (obj == null) {
            notifier.onNotifyListenerFailed();
            return;
        }
        try {
            notifier.notifyListener(obj);
        } catch (RuntimeException e) {
            notifier.onNotifyListenerFailed();
            throw e;
        }
    }

    @KeepForSdk
    public void clear() {
        this.b = null;
        this.c = null;
    }

    @Nullable
    @KeepForSdk
    public ListenerKey<L> getListenerKey() {
        return this.c;
    }

    @KeepForSdk
    public boolean hasListener() {
        return this.b != null;
    }

    @KeepForSdk
    public void notifyListener(@NonNull final Notifier<? super L> notifier) {
        Preconditions.checkNotNull(notifier, "Notifier must not be null");
        this.f8257a.execute(new Runnable() { // from class: com.google.android.gms.common.api.internal.zacb
            @Override // java.lang.Runnable
            public final void run() {
                ListenerHolder.this.a(notifier);
            }
        });
    }

    @KeepForSdk
    public ListenerHolder(@NonNull Executor executor, @NonNull L l, @NonNull String str) {
        this.f8257a = (Executor) Preconditions.checkNotNull(executor, "Executor must not be null");
        this.b = Preconditions.checkNotNull(l, "Listener must not be null");
        this.c = new ListenerKey(l, Preconditions.checkNotEmpty(str));
    }
}
