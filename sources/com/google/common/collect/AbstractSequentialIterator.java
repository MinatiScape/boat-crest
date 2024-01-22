package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public abstract class AbstractSequentialIterator<T> extends UnmodifiableIterator<T> {
    @NullableDecl
    public T h;

    public AbstractSequentialIterator(@NullableDecl T t) {
        this.h = t;
    }

    @NullableDecl
    public abstract T computeNext(T t);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h != null;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (hasNext()) {
            try {
                T t = this.h;
                this.h = computeNext(t);
                return t;
            } catch (Throwable th) {
                this.h = computeNext(this.h);
                throw th;
            }
        }
        throw new NoSuchElementException();
    }
}
