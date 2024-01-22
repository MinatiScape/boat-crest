package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
@GwtCompatible(serializable = true)
/* loaded from: classes10.dex */
public final class t2 extends Ordering<Object> implements Serializable {
    public static final t2 INSTANCE = new t2();
    private static final long serialVersionUID = 0;

    private t2() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return obj.toString().compareTo(obj2.toString());
    }

    public String toString() {
        return "Ordering.usingToString()";
    }
}
