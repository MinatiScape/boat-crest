package com.polidea.rxandroidble2.internal.cache;

import com.polidea.rxandroidble2.internal.DeviceComponent;
import java.lang.ref.WeakReference;
/* loaded from: classes9.dex */
public class DeviceComponentWeakReference extends WeakReference<DeviceComponent> {

    /* loaded from: classes9.dex */
    public interface Provider {
        DeviceComponentWeakReference provide(DeviceComponent deviceComponent);
    }

    public DeviceComponentWeakReference(DeviceComponent deviceComponent) {
        super(deviceComponent);
    }

    public boolean a(Object obj) {
        DeviceComponent deviceComponent = get();
        return (obj instanceof DeviceComponent) && deviceComponent != null && deviceComponent.provideDevice() == ((DeviceComponent) obj).provideDevice();
    }

    public boolean b() {
        return get() == null;
    }

    public boolean equals(Object obj) {
        if (obj instanceof WeakReference) {
            DeviceComponent deviceComponent = get();
            Object obj2 = ((WeakReference) obj).get();
            return deviceComponent != null && (obj2 instanceof DeviceComponent) && deviceComponent.provideDevice().equals(((DeviceComponent) obj2).provideDevice());
        }
        return false;
    }

    public int hashCode() {
        if (get() != null) {
            return get().hashCode();
        }
        return 0;
    }
}
