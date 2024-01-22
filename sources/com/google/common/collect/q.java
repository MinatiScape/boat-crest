package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Map;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public abstract class q<K, V> extends c<K, V> {
    public q(Map<K, Collection<V>> map) {
        super(map);
    }
}
