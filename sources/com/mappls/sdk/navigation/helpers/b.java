package com.mappls.sdk.navigation.helpers;

import com.mappls.sdk.navigation.helpers.c;
import java.util.Comparator;
/* loaded from: classes11.dex */
public final class b implements Comparator<c.a> {
    @Override // java.util.Comparator
    public final int compare(c.a aVar, c.a aVar2) {
        c.a aVar3 = aVar;
        c.a aVar4 = aVar2;
        int i = aVar3.c;
        int i2 = aVar4.c;
        return i == i2 ? Float.compare(aVar3.b, aVar4.b) : i < i2 ? -1 : 1;
    }
}
