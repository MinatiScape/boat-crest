package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
@GwtCompatible(serializable = true)
/* loaded from: classes10.dex */
public class j0 extends ImmutableListMultimap<Object, Object> {
    public static final j0 INSTANCE = new j0();
    private static final long serialVersionUID = 0;

    private j0() {
        super(ImmutableMap.of(), 0);
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
