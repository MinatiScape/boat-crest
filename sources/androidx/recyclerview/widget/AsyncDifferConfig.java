package androidx.recyclerview.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.DiffUtil;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* loaded from: classes.dex */
public final class AsyncDifferConfig<T> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Executor f1573a;
    @NonNull
    public final Executor b;
    @NonNull
    public final DiffUtil.ItemCallback<T> c;

    /* loaded from: classes.dex */
    public static final class Builder<T> {
        public static final Object d = new Object();
        public static Executor e;
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public Executor f1574a;
        public Executor b;
        public final DiffUtil.ItemCallback<T> c;

        public Builder(@NonNull DiffUtil.ItemCallback<T> itemCallback) {
            this.c = itemCallback;
        }

        @NonNull
        public AsyncDifferConfig<T> build() {
            if (this.b == null) {
                synchronized (d) {
                    if (e == null) {
                        e = Executors.newFixedThreadPool(2);
                    }
                }
                this.b = e;
            }
            return new AsyncDifferConfig<>(this.f1574a, this.b, this.c);
        }

        @NonNull
        public Builder<T> setBackgroundThreadExecutor(Executor executor) {
            this.b = executor;
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public Builder<T> setMainThreadExecutor(Executor executor) {
            this.f1574a = executor;
            return this;
        }
    }

    public AsyncDifferConfig(@Nullable Executor executor, @NonNull Executor executor2, @NonNull DiffUtil.ItemCallback<T> itemCallback) {
        this.f1573a = executor;
        this.b = executor2;
        this.c = itemCallback;
    }

    @NonNull
    public Executor getBackgroundThreadExecutor() {
        return this.b;
    }

    @NonNull
    public DiffUtil.ItemCallback<T> getDiffCallback() {
        return this.c;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Executor getMainThreadExecutor() {
        return this.f1573a;
    }
}
