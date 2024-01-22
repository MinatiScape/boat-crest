package io.reactivex.rxjava3.disposables;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.internal.util.OpenHashSet;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes12.dex */
public final class CompositeDisposable implements Disposable, DisposableContainer {
    public OpenHashSet<Disposable> h;
    public volatile boolean i;

    public CompositeDisposable() {
    }

    public void a(@Nullable OpenHashSet<Disposable> openHashSet) {
        Object[] keys;
        if (openHashSet == null) {
            return;
        }
        ArrayList arrayList = null;
        for (Object obj : openHashSet.keys()) {
            if (obj instanceof Disposable) {
                try {
                    ((Disposable) obj).dispose();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th);
                }
            }
        }
        if (arrayList != null) {
            if (arrayList.size() == 1) {
                throw ExceptionHelper.wrapOrThrow((Throwable) arrayList.get(0));
            }
            throw new CompositeException(arrayList);
        }
    }

    @Override // io.reactivex.rxjava3.disposables.DisposableContainer
    public boolean add(@NonNull Disposable disposable) {
        Objects.requireNonNull(disposable, "disposable is null");
        if (!this.i) {
            synchronized (this) {
                if (!this.i) {
                    OpenHashSet<Disposable> openHashSet = this.h;
                    if (openHashSet == null) {
                        openHashSet = new OpenHashSet<>();
                        this.h = openHashSet;
                    }
                    openHashSet.add(disposable);
                    return true;
                }
            }
        }
        disposable.dispose();
        return false;
    }

    public boolean addAll(@NonNull Disposable... disposableArr) {
        Objects.requireNonNull(disposableArr, "disposables is null");
        if (!this.i) {
            synchronized (this) {
                if (!this.i) {
                    OpenHashSet<Disposable> openHashSet = this.h;
                    if (openHashSet == null) {
                        openHashSet = new OpenHashSet<>(disposableArr.length + 1);
                        this.h = openHashSet;
                    }
                    for (Disposable disposable : disposableArr) {
                        Objects.requireNonNull(disposable, "A Disposable in the disposables array is null");
                        openHashSet.add(disposable);
                    }
                    return true;
                }
            }
        }
        for (Disposable disposable2 : disposableArr) {
            disposable2.dispose();
        }
        return false;
    }

    public void clear() {
        if (this.i) {
            return;
        }
        synchronized (this) {
            if (this.i) {
                return;
            }
            OpenHashSet<Disposable> openHashSet = this.h;
            this.h = null;
            a(openHashSet);
        }
    }

    @Override // io.reactivex.rxjava3.disposables.DisposableContainer
    public boolean delete(@NonNull Disposable disposable) {
        Objects.requireNonNull(disposable, "disposable is null");
        if (this.i) {
            return false;
        }
        synchronized (this) {
            if (this.i) {
                return false;
            }
            OpenHashSet<Disposable> openHashSet = this.h;
            if (openHashSet != null && openHashSet.remove(disposable)) {
                return true;
            }
            return false;
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.i) {
            return;
        }
        synchronized (this) {
            if (this.i) {
                return;
            }
            this.i = true;
            OpenHashSet<Disposable> openHashSet = this.h;
            this.h = null;
            a(openHashSet);
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.i;
    }

    @Override // io.reactivex.rxjava3.disposables.DisposableContainer
    public boolean remove(@NonNull Disposable disposable) {
        if (delete(disposable)) {
            disposable.dispose();
            return true;
        }
        return false;
    }

    public int size() {
        if (this.i) {
            return 0;
        }
        synchronized (this) {
            if (this.i) {
                return 0;
            }
            OpenHashSet<Disposable> openHashSet = this.h;
            return openHashSet != null ? openHashSet.size() : 0;
        }
    }

    public CompositeDisposable(@NonNull Disposable... disposableArr) {
        Objects.requireNonNull(disposableArr, "disposables is null");
        this.h = new OpenHashSet<>(disposableArr.length + 1);
        for (Disposable disposable : disposableArr) {
            Objects.requireNonNull(disposable, "A Disposable in the disposables array is null");
            this.h.add(disposable);
        }
    }

    public CompositeDisposable(@NonNull Iterable<? extends Disposable> iterable) {
        Objects.requireNonNull(iterable, "disposables is null");
        this.h = new OpenHashSet<>();
        for (Disposable disposable : iterable) {
            Objects.requireNonNull(disposable, "A Disposable item in the disposables sequence is null");
            this.h.add(disposable);
        }
    }
}