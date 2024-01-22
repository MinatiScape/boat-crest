package com.google.android.libraries.places.internal;

import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes10.dex */
final class zzdm extends LinkedHashMap<Long, Integer> {
    public zzdm(int i, float f, boolean z) {
        super(16, 0.75f, true);
    }

    @Override // java.util.LinkedHashMap
    public final boolean removeEldestEntry(Map.Entry<Long, Integer> entry) {
        return size() > 10;
    }
}
