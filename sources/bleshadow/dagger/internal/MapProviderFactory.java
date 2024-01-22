package bleshadow.dagger.internal;

import bleshadow.dagger.Lazy;
import bleshadow.dagger.internal.AbstractMapFactory;
import bleshadow.javax.inject.Provider;
import java.util.Map;
/* loaded from: classes.dex */
public final class MapProviderFactory<K, V> extends AbstractMapFactory<K, V, Provider<V>> implements Lazy<Map<K, Provider<V>>> {

    /* loaded from: classes.dex */
    public static final class Builder<K, V> extends AbstractMapFactory.Builder<K, V, Provider<V>> {
        public MapProviderFactory<K, V> build() {
            return new MapProviderFactory<>(this.f1849a);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // bleshadow.dagger.internal.AbstractMapFactory.Builder
        public /* bridge */ /* synthetic */ AbstractMapFactory.Builder put(Object obj, Provider provider) {
            return put((Builder<K, V>) obj, provider);
        }

        public Builder(int i) {
            super(i);
        }

        @Override // bleshadow.dagger.internal.AbstractMapFactory.Builder
        public Builder<K, V> put(K k, Provider<V> provider) {
            super.put((Builder<K, V>) k, (Provider) provider);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // bleshadow.dagger.internal.AbstractMapFactory.Builder
        public Builder<K, V> putAll(Provider<Map<K, Provider<V>>> provider) {
            super.putAll((Provider) provider);
            return this;
        }
    }

    public static <K, V> Builder<K, V> builder(int i) {
        return new Builder<>(i);
    }

    public MapProviderFactory(Map<K, Provider<V>> map) {
        super(map);
    }

    @Override // bleshadow.javax.inject.Provider
    public Map<K, Provider<V>> get() {
        return b();
    }
}
