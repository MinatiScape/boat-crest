package com.google.android.gms.internal.firebase_ml;

import java.util.Iterator;
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes7.dex */
public final class h1<T> implements Iterable<T> {
    public final /* synthetic */ Object h;

    public h1(Object obj) {
        this.h = obj;
    }

    @Override // java.lang.Iterable
    public final Iterator<T> iterator() {
        return new g1(this);
    }
}
