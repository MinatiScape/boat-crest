package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Set;
import javax.annotation.CheckForNull;
/* loaded from: classes6.dex */
public abstract class o0 extends AbstractMap {
    @CheckForNull
    public transient Set h;
    @CheckForNull
    public transient Collection i;

    public abstract Set a();

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        Set set = this.h;
        if (set == null) {
            Set a2 = a();
            this.h = a2;
            return a2;
        }
        return set;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection collection = this.i;
        if (collection == null) {
            n0 n0Var = new n0(this);
            this.i = n0Var;
            return n0Var;
        }
        return collection;
    }
}
