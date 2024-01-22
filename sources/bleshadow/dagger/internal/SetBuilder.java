package bleshadow.dagger.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public final class SetBuilder<T> {

    /* renamed from: a  reason: collision with root package name */
    public final List<T> f1855a;

    public SetBuilder(int i) {
        this.f1855a = new ArrayList(i);
    }

    public static <T> SetBuilder<T> newSetBuilder(int i) {
        return new SetBuilder<>(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SetBuilder<T> add(T t) {
        this.f1855a.add(Preconditions.checkNotNull(t, "Set contributions cannot be null"));
        return this;
    }

    public SetBuilder<T> addAll(Collection<? extends T> collection) {
        for (T t : collection) {
            Preconditions.checkNotNull(t, "Set contributions cannot be null");
        }
        this.f1855a.addAll(collection);
        return this;
    }

    public Set<T> build() {
        if (this.f1855a.isEmpty()) {
            return Collections.emptySet();
        }
        if (this.f1855a.size() == 1) {
            return Collections.singleton(this.f1855a.get(0));
        }
        return Collections.unmodifiableSet(new HashSet(this.f1855a));
    }
}
