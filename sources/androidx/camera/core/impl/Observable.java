package androidx.camera.core.impl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public interface Observable<T> {

    /* loaded from: classes.dex */
    public interface Observer<T> {
        void onError(@NonNull Throwable th);

        void onNewData(@Nullable T t);
    }

    void addObserver(@NonNull Executor executor, @NonNull Observer<T> observer);

    @NonNull
    ListenableFuture<T> fetchData();

    void removeObserver(@NonNull Observer<T> observer);
}
