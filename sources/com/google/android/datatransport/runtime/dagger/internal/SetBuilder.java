package com.google.android.datatransport.runtime.dagger.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes6.dex */
public final class SetBuilder<T> {

    /* renamed from: a  reason: collision with root package name */
    public final List<T> f8104a;

    public SetBuilder(int i) {
        this.f8104a = new ArrayList(i);
    }

    public static <T> SetBuilder<T> newSetBuilder(int i) {
        return new SetBuilder<>(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SetBuilder<T> add(T t) {
        this.f8104a.add(Preconditions.checkNotNull(t, "Set contributions cannot be null"));
        return this;
    }

    public SetBuilder<T> addAll(Collection<? extends T> collection) {
        for (T t : collection) {
            Preconditions.checkNotNull(t, "Set contributions cannot be null");
        }
        this.f8104a.addAll(collection);
        return this;
    }

    public Set<T> build() {
        int size = this.f8104a.size();
        if (size != 0) {
            if (size != 1) {
                return Collections.unmodifiableSet(new HashSet(this.f8104a));
            }
            return Collections.singleton(this.f8104a.get(0));
        }
        return Collections.emptySet();
    }
}
