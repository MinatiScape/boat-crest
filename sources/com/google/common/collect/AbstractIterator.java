package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public abstract class AbstractIterator<T> extends UnmodifiableIterator<T> {
    public b h = b.NOT_READY;
    @NullableDecl
    public T i;

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10547a;

        static {
            int[] iArr = new int[b.values().length];
            f10547a = iArr;
            try {
                iArr[b.DONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10547a[b.READY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes10.dex */
    public enum b {
        READY,
        NOT_READY,
        DONE,
        FAILED
    }

    public final boolean a() {
        this.h = b.FAILED;
        this.i = computeNext();
        if (this.h != b.DONE) {
            this.h = b.READY;
            return true;
        }
        return false;
    }

    public abstract T computeNext();

    @CanIgnoreReturnValue
    public final T endOfData() {
        this.h = b.DONE;
        return null;
    }

    @Override // java.util.Iterator
    @CanIgnoreReturnValue
    public final boolean hasNext() {
        Preconditions.checkState(this.h != b.FAILED);
        int i = a.f10547a[this.h.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return a();
            }
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    @CanIgnoreReturnValue
    public final T next() {
        if (hasNext()) {
            this.h = b.NOT_READY;
            T t = this.i;
            this.i = null;
            return t;
        }
        throw new NoSuchElementException();
    }

    public final T peek() {
        if (hasNext()) {
            return this.i;
        }
        throw new NoSuchElementException();
    }
}
