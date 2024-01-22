package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public final Map<Integer, Integer> f11848a = new HashMap();

    public Integer a(int i) {
        return this.f11848a.get(Integer.valueOf(i));
    }

    public int[] b() {
        ArrayList arrayList = new ArrayList();
        int i = -1;
        for (Map.Entry<Integer, Integer> entry : this.f11848a.entrySet()) {
            if (entry.getValue().intValue() > i) {
                i = entry.getValue().intValue();
                arrayList.clear();
                arrayList.add(entry.getKey());
            } else if (entry.getValue().intValue() == i) {
                arrayList.add(entry.getKey());
            }
        }
        return PDF417Common.toIntArray(arrayList);
    }

    public void c(int i) {
        Integer num = this.f11848a.get(Integer.valueOf(i));
        if (num == null) {
            num = 0;
        }
        this.f11848a.put(Integer.valueOf(i), Integer.valueOf(num.intValue() + 1));
    }
}
