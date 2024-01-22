package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Map;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public abstract class k1<K, V> extends k<K, V> {
    public k1(Map<K, Collection<V>> map) {
        super(map);
    }
}
