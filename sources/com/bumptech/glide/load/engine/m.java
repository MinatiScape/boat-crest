package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Key;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public final class m {

    /* renamed from: a  reason: collision with root package name */
    public final Map<Key, h<?>> f2389a = new HashMap();
    public final Map<Key, h<?>> b = new HashMap();

    public h<?> a(Key key, boolean z) {
        return b(z).get(key);
    }

    public final Map<Key, h<?>> b(boolean z) {
        return z ? this.b : this.f2389a;
    }

    public void c(Key key, h<?> hVar) {
        b(hVar.m()).put(key, hVar);
    }

    public void d(Key key, h<?> hVar) {
        Map<Key, h<?>> b = b(hVar.m());
        if (hVar.equals(b.get(key))) {
            b.remove(key);
        }
    }
}
