package com.polidea.rxandroidble2.internal;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientScope;
import com.polidea.rxandroidble2.RxBleDevice;
import com.polidea.rxandroidble2.internal.DeviceComponent;
import com.polidea.rxandroidble2.internal.cache.DeviceComponentCache;
import java.util.Map;
@ClientScope
/* loaded from: classes9.dex */
public class RxBleDeviceProvider {

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, DeviceComponent> f13392a;
    public final Provider<DeviceComponent.Builder> b;

    @Inject
    public RxBleDeviceProvider(DeviceComponentCache deviceComponentCache, Provider<DeviceComponent.Builder> provider) {
        this.f13392a = deviceComponentCache;
        this.b = provider;
    }

    public RxBleDevice getBleDevice(String str) {
        DeviceComponent deviceComponent = this.f13392a.get(str);
        if (deviceComponent != null) {
            return deviceComponent.provideDevice();
        }
        synchronized (this.f13392a) {
            DeviceComponent deviceComponent2 = this.f13392a.get(str);
            if (deviceComponent2 != null) {
                return deviceComponent2.provideDevice();
            }
            DeviceComponent build = this.b.get().macAddress(str).build();
            RxBleDevice provideDevice = build.provideDevice();
            this.f13392a.put(str, build);
            return provideDevice;
        }
    }
}
