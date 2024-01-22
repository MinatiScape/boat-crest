package com.polidea.rxandroidble2;

import android.bluetooth.BluetoothAdapter;
import androidx.annotation.Nullable;
import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
import com.polidea.rxandroidble2.ClientComponent;
@DaggerGenerated
@ScopeMetadata
@QualifierMetadata
/* loaded from: classes9.dex */
public final class ClientComponent_ClientModule_ProvideBluetoothAdapterFactory implements Factory<BluetoothAdapter> {

    /* loaded from: classes9.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final ClientComponent_ClientModule_ProvideBluetoothAdapterFactory f13336a = new ClientComponent_ClientModule_ProvideBluetoothAdapterFactory();
    }

    public static ClientComponent_ClientModule_ProvideBluetoothAdapterFactory create() {
        return a.f13336a;
    }

    @Nullable
    public static BluetoothAdapter provideBluetoothAdapter() {
        return ClientComponent.ClientModule.a();
    }

    @Override // bleshadow.javax.inject.Provider
    @Nullable
    public BluetoothAdapter get() {
        return provideBluetoothAdapter();
    }
}
