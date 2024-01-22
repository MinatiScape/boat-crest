package com.coveiot.android.tappy.activity;

import com.coveiot.android.tappy.model.TransactionBeanInfo;
import java.util.Comparator;
/* loaded from: classes7.dex */
public final class ActivityRegisteredProductSummary$showDummyTransaction$$inlined$sortByDescending$1<T> implements Comparator {
    @Override // java.util.Comparator
    public final int compare(T t, T t2) {
        return kotlin.comparisons.f.compareValues(((TransactionBeanInfo) t2).getTransactionDate(), ((TransactionBeanInfo) t).getTransactionDate());
    }
}
