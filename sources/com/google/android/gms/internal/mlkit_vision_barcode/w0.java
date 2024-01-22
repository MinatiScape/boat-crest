package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Set;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public abstract class w0 extends AbstractMap {
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
            v0 v0Var = new v0(this);
            this.i = v0Var;
            return v0Var;
        }
        return collection;
    }
}
