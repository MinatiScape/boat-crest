package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes10.dex */
public final class w3 implements Iterator<Object> {
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
