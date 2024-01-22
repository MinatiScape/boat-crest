package com.polidea.rxandroidble2;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import com.polidea.rxandroidble2.ClientComponent;
import java.util.concurrent.ExecutorService;
@DaggerGenerated
@ScopeMetadata("com.polidea.rxandroidble2.ClientScope")
@QualifierMetadata({"bleshadow.javax.inject.Named"})
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideConnectionQueueExecutorServiceFactory implements Factory<ExecutorService> {

    /* loaded from: classes9.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final ClientComponent_ClientModule_ProvideConnectionQueueExecutorServiceFactory f13342a = new ClientComponent_ClientModule_ProvideConnectionQueueExecutorServiceFactory();
    }

    public static ClientComponent_ClientModule_ProvideConnectionQueueExecutorServiceFactory create() {
        return a.f13342a;
    }

    public static ExecutorService provideConnectionQueueExecutorService() {
        return (ExecutorService) Preconditions.checkNotNullFromProvides(ClientComponent.ClientModule.g());
    }

    @Override // bleshadow.javax.inject.Provider
    public ExecutorService get() {
        return provideConnectionQueueExecutorService();
    }
}
