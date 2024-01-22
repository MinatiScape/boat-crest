package com.github.mikephil.charting.utils;

import android.os.Parcel;
import android.os.Parcelable;
import com.github.mikephil.charting.utils.ObjectPool;
import java.util.List;
/* loaded from: classes9.dex */
public class MPPointF extends ObjectPool.Poolable {
    public static final Parcelable.Creator<MPPointF> CREATOR;
    public static ObjectPool<MPPointF> i;
    public float x;
    public float y;

    /* loaded from: classes9.dex */
    public static class a implements Parcelable.Creator<MPPointF> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public MPPointF createFromParcel(Parcel parcel) {
            MPPointF mPPointF = new MPPointF(0.0f, 0.0f);
            mPPointF.my_readFromParcel(parcel);
            return mPPointF;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public MPPointF[] newArray(int i) {
            return new MPPointF[i];
        }
    }

    static {
        ObjectPool<MPPointF> create = ObjectPool.create(32, new MPPointF(0.0f, 0.0f));
        i = create;
        create.setReplenishPercentage(0.5f);
        CREATOR = new a();
    }

    public MPPointF() {
    }

    public static MPPointF getInstance(float f, float f2) {
        MPPointF mPPointF = i.get();
        mPPointF.x = f;
        mPPointF.y = f2;
        return mPPointF;
    }

    public static void recycleInstance(MPPointF mPPointF) {
        i.recycle((ObjectPool<MPPointF>) mPPointF);
    }

    public static void recycleInstances(List<MPPointF> list) {
        i.recycle(list);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    @Override // com.github.mikephil.charting.utils.ObjectPool.Poolable
    public ObjectPool.Poolable instantiate() {
        return new MPPointF(0.0f, 0.0f);
    }

    public void my_readFromParcel(Parcel parcel) {
        this.x = parcel.readFloat();
        this.y = parcel.readFloat();
    }

    public MPPointF(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public static MPPointF getInstance() {
        return i.get();
    }

    public static MPPointF getInstance(MPPointF mPPointF) {
        MPPointF mPPointF2 = i.get();
        mPPointF2.x = mPPointF.x;
        mPPointF2.y = mPPointF.y;
        return mPPointF2;
    }
}
