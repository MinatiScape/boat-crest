package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
@GwtCompatible
/* loaded from: classes10.dex */
public interface h2<T> extends Iterable<T> {
    Comparator<? super T> comparator();
}
