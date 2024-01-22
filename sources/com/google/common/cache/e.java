package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.cache.a;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes10.dex */
public interface e<K, V> {
    long getAccessTime();

    int getHash();

    @NullableDecl
    K getKey();

    @NullableDecl
    e<K, V> getNext();

    e<K, V> getNextInAccessQueue();

    e<K, V> getNextInWriteQueue();

    e<K, V> getPreviousInAccessQueue();

    e<K, V> getPreviousInWriteQueue();

    a.a0<K, V> getValueReference();

    long getWriteTime();

    void setAccessTime(long j);

    void setNextInAccessQueue(e<K, V> eVar);

    void setNextInWriteQueue(e<K, V> eVar);

    void setPreviousInAccessQueue(e<K, V> eVar);

    void setPreviousInWriteQueue(e<K, V> eVar);

    void setValueReference(a.a0<K, V> a0Var);

    void setWriteTime(long j);
}
