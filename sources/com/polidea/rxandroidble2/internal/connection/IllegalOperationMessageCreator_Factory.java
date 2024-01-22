package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.util.CharacteristicPropertiesParser;
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
/* loaded from: classes12.dex */
public final class IllegalOperationMessageCreator_Factory implements Factory<IllegalOperationMessageCreator> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<CharacteristicPropertiesParser> f13415a;

    public IllegalOperationMessageCreator_Factory(Provider<CharacteristicPropertiesParser> provider) {
        this.f13415a = provider;
    }

    public static IllegalOperationMessageCreator_Factory create(Provider<CharacteristicPropertiesParser> provider) {
        return new IllegalOperationMessageCreator_Factory(provider);
    }

    public static IllegalOperationMessageCreator newInstance(CharacteristicPropertiesParser characteristicPropertiesParser) {
        return new IllegalOperationMessageCreator(characteristicPropertiesParser);
    }

    @Override // bleshadow.javax.inject.Provider
    public IllegalOperationMessageCreator get() {
        return newInstance(this.f13415a.get());
    }
}
