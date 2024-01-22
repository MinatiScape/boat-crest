package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.dagger.Module;
import com.google.android.datatransport.runtime.dagger.Provides;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Singleton;
@Module
/* loaded from: classes6.dex */
public abstract class e {
    @Provides
    @Singleton
    public static Executor a() {
        return new f(Executors.newSingleThreadExecutor());
    }
}
