package com.google.android.datatransport.runtime.dagger.internal;

import com.clevertap.android.sdk.Constants;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public abstract class AbstractMapFactory<K, V, V2> implements Factory<Map<K, V2>> {

    /* renamed from: a  reason: collision with root package name */
    public final Map<K, Provider<V>> f8097a;

    /* loaded from: classes6.dex */
    public static abstract class Builder<K, V, V2> {

        /* renamed from: a  reason: collision with root package name */
        public final LinkedHashMap<K, Provider<V>> f8098a;

        public Builder(int i) {
            this.f8098a = DaggerCollections.newLinkedHashMapWithExpectedSize(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder<K, V, V2> put(K k, Provider<V> provider) {
            this.f8098a.put(Preconditions.checkNotNull(k, Constants.KEY_KEY), Preconditions.checkNotNull(provider, "provider"));
            return this;
        }

        public Builder<K, V, V2> putAll(Provider<Map<K, V2>> provider) {
            if (!(provider instanceof DelegateFactory)) {
                this.f8098a.putAll(((AbstractMapFactory) provider).f8097a);
                return this;
            }
            return putAll(((DelegateFactory) provider).a());
        }
    }

    public AbstractMapFactory(Map<K, Provider<V>> map) {
        this.f8097a = Collections.unmodifiableMap(map);
    }

    public final Map<K, Provider<V>> b() {
        return this.f8097a;
    }
}
