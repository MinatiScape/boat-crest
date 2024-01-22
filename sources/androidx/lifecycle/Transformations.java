package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
/* loaded from: classes.dex */
public class Transformations {

    /* JADX INFO: Add missing generic type declarations: [X] */
    /* loaded from: classes.dex */
    public class a<X> implements Observer<X> {
        public final /* synthetic */ MediatorLiveData h;
        public final /* synthetic */ Function i;

        public a(MediatorLiveData mediatorLiveData, Function function) {
            this.h = mediatorLiveData;
            this.i = function;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(@Nullable X x) {
            this.h.setValue(this.i.apply(x));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [X] */
    /* loaded from: classes.dex */
    public class b<X> implements Observer<X> {
        public LiveData<Y> h;
        public final /* synthetic */ Function i;
        public final /* synthetic */ MediatorLiveData j;

        /* JADX INFO: Add missing generic type declarations: [Y] */
        /* loaded from: classes.dex */
        public class a<Y> implements Observer<Y> {
            public a() {
            }

            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable Y y) {
                b.this.j.setValue(y);
            }
        }

        public b(Function function, MediatorLiveData mediatorLiveData) {
            this.i = function;
            this.j = mediatorLiveData;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(@Nullable X x) {
            LiveData<Y> liveData = (LiveData) this.i.apply(x);
            Object obj = this.h;
            if (obj == liveData) {
                return;
            }
            if (obj != null) {
                this.j.removeSource(obj);
            }
            this.h = liveData;
            if (liveData != 0) {
                this.j.addSource(liveData, new a());
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [X] */
    /* loaded from: classes.dex */
    public class c<X> implements Observer<X> {
        public boolean h = true;
        public final /* synthetic */ MediatorLiveData i;

        public c(MediatorLiveData mediatorLiveData) {
            this.i = mediatorLiveData;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(X x) {
            T value = this.i.getValue();
            if (this.h || ((value == 0 && x != null) || !(value == 0 || value.equals(x)))) {
                this.h = false;
                this.i.setValue(x);
            }
        }
    }

    @NonNull
    @MainThread
    public static <X> LiveData<X> distinctUntilChanged(@NonNull LiveData<X> liveData) {
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new c(mediatorLiveData));
        return mediatorLiveData;
    }

    @NonNull
    @MainThread
    public static <X, Y> LiveData<Y> map(@NonNull LiveData<X> liveData, @NonNull Function<X, Y> function) {
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new a(mediatorLiveData, function));
        return mediatorLiveData;
    }

    @NonNull
    @MainThread
    public static <X, Y> LiveData<Y> switchMap(@NonNull LiveData<X> liveData, @NonNull Function<X, LiveData<Y>> function) {
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new b(function, mediatorLiveData));
        return mediatorLiveData;
    }
}
