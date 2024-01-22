package com.polidea.rxandroidble2.internal.logger;

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
public final class LoggerUtilBluetoothServices_Factory implements Factory<LoggerUtilBluetoothServices> {

    /* renamed from: a  reason: collision with root package name */
    public final Provider<CharacteristicPropertiesParser> f13444a;

    public LoggerUtilBluetoothServices_Factory(Provider<CharacteristicPropertiesParser> provider) {
        this.f13444a = provider;
    }

    public static LoggerUtilBluetoothServices_Factory create(Provider<CharacteristicPropertiesParser> provider) {
        return new LoggerUtilBluetoothServices_Factory(provider);
    }

    public static LoggerUtilBluetoothServices newInstance(CharacteristicPropertiesParser characteristicPropertiesParser) {
        return new LoggerUtilBluetoothServices(characteristicPropertiesParser);
    }

    @Override // bleshadow.javax.inject.Provider
    public LoggerUtilBluetoothServices get() {
        return newInstance(this.f13444a.get());
    }
}
