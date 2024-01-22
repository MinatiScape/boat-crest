package com.google.android.gms.internal.common;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;
import org.jspecify.nullness.NullMarked;
@NullMarked
/* loaded from: classes7.dex */
public abstract class f implements Iterator {
    @CheckForNull
    public Object h;
    public int i = 2;

    @CheckForNull
    public abstract Object a();

    @CanIgnoreReturnValue
    @CheckForNull
    public final Object b() {
        this.i = 3;
        return null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.i;
        if (i != 4) {
            int i2 = i - 1;
            if (i != 0) {
                if (i2 != 0) {
                    if (i2 != 2) {
                        this.i = 4;
                        this.h = a();
                        if (this.i != 3) {
                            this.i = 1;
                            return true;
                        }
                    }
                    return false;
                }
                return true;
            }
            throw null;
        }
        throw new IllegalStateException();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (hasNext()) {
            this.i = 2;
            Object obj = this.h;
            this.h = null;
            return obj;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
