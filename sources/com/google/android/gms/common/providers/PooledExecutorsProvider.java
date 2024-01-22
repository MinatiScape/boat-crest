package com.google.android.gms.common.providers;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.ScheduledExecutorService;
@KeepForSdk
@Deprecated
/* loaded from: classes6.dex */
public class PooledExecutorsProvider {

    /* renamed from: a  reason: collision with root package name */
    public static PooledExecutorFactory f8365a;

    /* loaded from: classes6.dex */
    public interface PooledExecutorFactory {
        @NonNull
        @KeepForSdk
        @Deprecated
        ScheduledExecutorService newSingleThreadScheduledExecutor();
    }

    @NonNull
    @KeepForSdk
    @Deprecated
    public static synchronized PooledExecutorFactory getInstance() {
        PooledExecutorFactory pooledExecutorFactory;
        synchronized (PooledExecutorsProvider.class) {
            if (f8365a == null) {
                f8365a = new a();
            }
            pooledExecutorFactory = f8365a;
        }
        return pooledExecutorFactory;
    }
}
