package androidx.lifecycle;

import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.internal.SafeIterableMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public class MediatorLiveData<T> extends MutableLiveData<T> {
    public SafeIterableMap<LiveData<?>, a<?>> l = new SafeIterableMap<>();

    /* loaded from: classes.dex */
    public static class a<V> implements Observer<V> {
        public final LiveData<V> h;
        public final Observer<? super V> i;
        public int j = -1;

        public a(LiveData<V> liveData, Observer<? super V> observer) {
            this.h = liveData;
            this.i = observer;
        }

        public void a() {
            this.h.observeForever(this);
        }

        public void b() {
            this.h.removeObserver(this);
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(@Nullable V v) {
            if (this.j != this.h.e()) {
                this.j = this.h.e();
                this.i.onChanged(v);
            }
        }
    }

    @MainThread
    public <S> void addSource(@NonNull LiveData<S> liveData, @NonNull Observer<? super S> observer) {
        Objects.requireNonNull(liveData, "source cannot be null");
        a<?> aVar = new a<>(liveData, observer);
        a<?> putIfAbsent = this.l.putIfAbsent(liveData, aVar);
        if (putIfAbsent != null && putIfAbsent.i != observer) {
            throw new IllegalArgumentException("This source was already added with the different observer");
        }
        if (putIfAbsent == null && hasActiveObservers()) {
            aVar.a();
        }
    }

    @Override // androidx.lifecycle.LiveData
    @CallSuper
    public void onActive() {
        Iterator<Map.Entry<LiveData<?>, a<?>>> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().getValue().a();
        }
    }

    @Override // androidx.lifecycle.LiveData
    @CallSuper
    public void onInactive() {
        Iterator<Map.Entry<LiveData<?>, a<?>>> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().getValue().b();
        }
    }

    @MainThread
    public <S> void removeSource(@NonNull LiveData<S> liveData) {
        a<?> remove = this.l.remove(liveData);
        if (remove != null) {
            remove.b();
        }
    }
}
