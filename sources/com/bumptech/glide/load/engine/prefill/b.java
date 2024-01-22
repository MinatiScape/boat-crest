package com.bumptech.glide.load.engine.prefill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public final Map<PreFillType, Integer> f2394a;
    public final List<PreFillType> b;
    public int c;
    public int d;

    public b(Map<PreFillType, Integer> map) {
        this.f2394a = map;
        this.b = new ArrayList(map.keySet());
        for (Integer num : map.values()) {
            this.c += num.intValue();
        }
    }

    public boolean a() {
        return this.c == 0;
    }

    public PreFillType b() {
        PreFillType preFillType = this.b.get(this.d);
        Integer num = this.f2394a.get(preFillType);
        if (num.intValue() == 1) {
            this.f2394a.remove(preFillType);
            this.b.remove(this.d);
        } else {
            this.f2394a.put(preFillType, Integer.valueOf(num.intValue() - 1));
        }
        this.c--;
        this.d = this.b.isEmpty() ? 0 : (this.d + 1) % this.b.size();
        return preFillType;
    }
}
