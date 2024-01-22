package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public abstract class b<T> implements Iterator<T> {
    public EnumC0455b h = EnumC0455b.NOT_READY;
    @NullableDecl
    public T i;

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10533a;

        static {
            int[] iArr = new int[EnumC0455b.values().length];
            f10533a = iArr;
            try {
                iArr[EnumC0455b.DONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10533a[EnumC0455b.READY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* renamed from: com.google.common.base.b$b  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public enum EnumC0455b {
        READY,
        NOT_READY,
        DONE,
        FAILED
    }

    public abstract T a();

    @CanIgnoreReturnValue
    @NullableDecl
    public final T b() {
        this.h = EnumC0455b.DONE;
        return null;
    }

    public final boolean c() {
        this.h = EnumC0455b.FAILED;
        this.i = a();
        if (this.h != EnumC0455b.DONE) {
            this.h = EnumC0455b.READY;
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        Preconditions.checkState(this.h != EnumC0455b.FAILED);
        int i = a.f10533a[this.h.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return c();
            }
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (hasNext()) {
            this.h = EnumC0455b.NOT_READY;
            T t = this.i;
            this.i = null;
            return t;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
