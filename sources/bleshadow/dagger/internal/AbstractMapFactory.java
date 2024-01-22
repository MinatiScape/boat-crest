package bleshadow.dagger.internal;

import bleshadow.javax.inject.Provider;
import com.clevertap.android.sdk.Constants;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class AbstractMapFactory<K, V, V2> implements Factory<Map<K, V2>> {

    /* renamed from: a  reason: collision with root package name */
    public final Map<K, Provider<V>> f1848a;

    /* loaded from: classes.dex */
    public static abstract class Builder<K, V, V2> {

        /* renamed from: a  reason: collision with root package name */
        public final LinkedHashMap<K, Provider<V>> f1849a;

        public Builder(int i) {
            this.f1849a = DaggerCollections.newLinkedHashMapWithExpectedSize(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder<K, V, V2> put(K k, Provider<V> provider) {
            this.f1849a.put(Preconditions.checkNotNull(k, Constants.KEY_KEY), Preconditions.checkNotNull(provider, "provider"));
            return this;
        }

        public Builder<K, V, V2> putAll(Provider<Map<K, V2>> provider) {
            if (!(provider instanceof DelegateFactory)) {
                this.f1849a.putAll(((AbstractMapFactory) provider).f1848a);
                return this;
            }
            return putAll(((DelegateFactory) provider).a());
        }
    }

    public AbstractMapFactory(Map<K, Provider<V>> map) {
        this.f1848a = Collections.unmodifiableMap(map);
    }

    public final Map<K, Provider<V>> b() {
        return this.f1848a;
    }
}
