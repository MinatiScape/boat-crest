package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes10.dex */
final class zzum implements Iterator<Object> {
    @Override // java.util.Iterator
    public final boolean hasNext() {
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
