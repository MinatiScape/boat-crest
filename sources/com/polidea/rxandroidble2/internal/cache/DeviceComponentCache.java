package com.polidea.rxandroidble2.internal.cache;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.ClientScope;
import com.polidea.rxandroidble2.internal.DeviceComponent;
import com.polidea.rxandroidble2.internal.cache.DeviceComponentWeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
@ClientScope
/* loaded from: classes9.dex */
public class DeviceComponentCache implements Map<String, DeviceComponent> {
    public final HashMap<String, DeviceComponentWeakReference> h;
    public final DeviceComponentWeakReference.Provider i;

    /* loaded from: classes9.dex */
    public class a implements DeviceComponentWeakReference.Provider {
        @Override // com.polidea.rxandroidble2.internal.cache.DeviceComponentWeakReference.Provider
        public DeviceComponentWeakReference provide(DeviceComponent deviceComponent) {
            return new DeviceComponentWeakReference(deviceComponent);
        }
    }

    @Inject
    public DeviceComponentCache() {
        this(new a());
    }

    public final void a() {
        Iterator<Map.Entry<String, DeviceComponentWeakReference>> it = this.h.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue().b()) {
                it.remove();
            }
        }
    }

    @Override // java.util.Map
    public void clear() {
        this.h.clear();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.h.containsKey(obj) && get(obj) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        for (DeviceComponentWeakReference deviceComponentWeakReference : this.h.values()) {
            if (deviceComponentWeakReference.a(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map
    @NonNull
    public Set<Map.Entry<String, DeviceComponent>> entrySet() {
        HashSet hashSet = new HashSet();
        for (Map.Entry<String, DeviceComponentWeakReference> entry : this.h.entrySet()) {
            DeviceComponentWeakReference value = entry.getValue();
            if (!value.b()) {
                hashSet.add(new com.polidea.rxandroidble2.internal.cache.a(entry.getKey(), this.i.provide(value.get())));
            }
        }
        return hashSet;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        a();
        return this.h.isEmpty();
    }

    @Override // java.util.Map
    @NonNull
    public Set<String> keySet() {
        return this.h.keySet();
    }

    @Override // java.util.Map
    public void putAll(@NonNull Map<? extends String, ? extends DeviceComponent> map) {
        for (Map.Entry<? extends String, ? extends DeviceComponent> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public int size() {
        a();
        return this.h.size();
    }

    @Override // java.util.Map
    @NonNull
    public Collection<DeviceComponent> values() {
        ArrayList arrayList = new ArrayList();
        for (DeviceComponentWeakReference deviceComponentWeakReference : this.h.values()) {
            if (!deviceComponentWeakReference.b()) {
                arrayList.add(deviceComponentWeakReference.get());
            }
        }
        return arrayList;
    }

    public DeviceComponentCache(DeviceComponentWeakReference.Provider provider) {
        this.h = new HashMap<>();
        this.i = provider;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    @Nullable
    public DeviceComponent get(Object obj) {
        DeviceComponentWeakReference deviceComponentWeakReference = this.h.get(obj);
        if (deviceComponentWeakReference != null) {
            return deviceComponentWeakReference.get();
        }
        return null;
    }

    @Override // java.util.Map
    public DeviceComponent put(String str, DeviceComponent deviceComponent) {
        this.h.put(str, this.i.provide(deviceComponent));
        a();
        return deviceComponent;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    public DeviceComponent remove(Object obj) {
        DeviceComponentWeakReference remove = this.h.remove(obj);
        a();
        if (remove != null) {
            return remove.get();
        }
        return null;
    }
}
