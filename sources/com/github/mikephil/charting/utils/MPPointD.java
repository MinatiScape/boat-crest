package com.github.mikephil.charting.utils;

import com.github.mikephil.charting.utils.ObjectPool;
import java.util.List;
/* loaded from: classes9.dex */
public class MPPointD extends ObjectPool.Poolable {
    public static ObjectPool<MPPointD> i;
    public double x;
    public double y;

    static {
        ObjectPool<MPPointD> create = ObjectPool.create(64, new MPPointD(0.0d, 0.0d));
        i = create;
        create.setReplenishPercentage(0.5f);
    }

    public MPPointD(double d, double d2) {
        this.x = d;
        this.y = d2;
    }

    public static MPPointD getInstance(double d, double d2) {
        MPPointD mPPointD = i.get();
        mPPointD.x = d;
        mPPointD.y = d2;
        return mPPointD;
    }

    public static void recycleInstance(MPPointD mPPointD) {
        i.recycle((ObjectPool<MPPointD>) mPPointD);
    }

    public static void recycleInstances(List<MPPointD> list) {
        i.recycle(list);
    }

    @Override // com.github.mikephil.charting.utils.ObjectPool.Poolable
    public ObjectPool.Poolable instantiate() {
        return new MPPointD(0.0d, 0.0d);
    }

    public String toString() {
        return "MPPointD, x: " + this.x + ", y: " + this.y;
    }
}
