package bleshadow.dagger.internal;

import bleshadow.javax.inject.Provider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public final class SetFactory<T> implements Factory<Set<T>> {
    public static final Factory<Set<Object>> c = InstanceFactory.create(Collections.emptySet());

    /* renamed from: a  reason: collision with root package name */
    public final List<Provider<T>> f1856a;
    public final List<Provider<Collection<T>>> b;

    /* loaded from: classes.dex */
    public static final class Builder<T> {

        /* renamed from: a  reason: collision with root package name */
        public final List<Provider<T>> f1857a;
        public final List<Provider<Collection<T>>> b;

        public Builder<T> addCollectionProvider(Provider<? extends Collection<? extends T>> provider) {
            this.b.add(provider);
            return this;
        }

        public Builder<T> addProvider(Provider<? extends T> provider) {
            this.f1857a.add(provider);
            return this;
        }

        public SetFactory<T> build() {
            return new SetFactory<>(this.f1857a, this.b);
        }

        public Builder(int i, int i2) {
            this.f1857a = DaggerCollections.presizedList(i);
            this.b = DaggerCollections.presizedList(i2);
        }
    }

    public static <T> Builder<T> builder(int i, int i2) {
        return new Builder<>(i, i2);
    }

    public static <T> Factory<Set<T>> empty() {
        return (Factory<Set<T>>) c;
    }

    public SetFactory(List<Provider<T>> list, List<Provider<Collection<T>>> list2) {
        this.f1856a = list;
        this.b = list2;
    }

    @Override // bleshadow.javax.inject.Provider
    public Set<T> get() {
        int size = this.f1856a.size();
        ArrayList arrayList = new ArrayList(this.b.size());
        int size2 = this.b.size();
        for (int i = 0; i < size2; i++) {
            Collection<T> collection = this.b.get(i).get();
            size += collection.size();
            arrayList.add(collection);
        }
        HashSet b = DaggerCollections.b(size);
        int size3 = this.f1856a.size();
        for (int i2 = 0; i2 < size3; i2++) {
            b.add(Preconditions.checkNotNull(this.f1856a.get(i2).get()));
        }
        int size4 = arrayList.size();
        for (int i3 = 0; i3 < size4; i3++) {
            for (Object obj : (Collection) arrayList.get(i3)) {
                b.add(Preconditions.checkNotNull(obj));
            }
        }
        return Collections.unmodifiableSet(b);
    }
}
