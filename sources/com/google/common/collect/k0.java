package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
@GwtCompatible(serializable = true)
/* loaded from: classes10.dex */
public class k0 extends ImmutableSetMultimap<Object, Object> {
    public static final k0 INSTANCE = new k0();
    private static final long serialVersionUID = 0;

    private k0() {
        super(ImmutableMap.of(), 0, null);
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
