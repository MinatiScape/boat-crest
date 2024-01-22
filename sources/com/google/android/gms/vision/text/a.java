package com.google.android.gms.vision.text;

import java.util.Comparator;
import java.util.Map;
/* loaded from: classes10.dex */
public final class a implements Comparator<Map.Entry<String, Integer>> {
    public a(TextBlock textBlock) {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> entry2) {
        return entry.getValue().compareTo(entry2.getValue());
    }
}
