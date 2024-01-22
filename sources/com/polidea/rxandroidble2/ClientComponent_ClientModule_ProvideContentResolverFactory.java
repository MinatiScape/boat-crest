package com.polidea.rxandroidble2;

import android.content.ContentResolver;
import android.content.Context;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideContentResolverFactory implements Factory<ContentResolver> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<Context> f13343a;

    public ClientComponent_ClientModule_ProvideContentResolverFactory(Provider<Context> provider) {
        this.f13343a = provider;
    }

    public static ClientComponent_ClientModule_ProvideContentResolverFactory create(Provider<Context> provider) {
        return new ClientComponent_ClientModule_ProvideContentResolverFactory(provider);
    }

    public static ContentResolver provideContentResolver(Context context) {
        return (ContentResolver) Preconditions.checkNotNullFromProvides(ClientComponent.ClientModule.h(context));
    }

    @Override // bleshadow.javax.inject.Provider
    public ContentResolver get() {
        return provideContentResolver(this.f13343a.get());
    }
}
