package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import com.polidea.rxandroidble2.internal.util.CharacteristicPropertiesParser;
@DaggerGenerated
@ScopeMetadata
@QualifierMetadata
/* loaded from: classes12.dex */
public final class ConnectionModule_ProvideCharacteristicPropertiesParserFactory implements Factory<CharacteristicPropertiesParser> {

    /* loaded from: classes12.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final ConnectionModule_ProvideCharacteristicPropertiesParserFactory f13403a = new ConnectionModule_ProvideCharacteristicPropertiesParserFactory();
    }

    public static ConnectionModule_ProvideCharacteristicPropertiesParserFactory create() {
        return a.f13403a;
    }

    public static CharacteristicPropertiesParser provideCharacteristicPropertiesParser() {
        return (CharacteristicPropertiesParser) Preconditions.checkNotNullFromProvides(ConnectionModule.d());
    }

    @Override // bleshadow.javax.inject.Provider
    public CharacteristicPropertiesParser get() {
        return provideCharacteristicPropertiesParser();
    }
}
