package com.github.mikephil.charting.data;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import com.github.mikephil.charting.utils.Utils;
/* loaded from: classes9.dex */
public class Entry extends BaseEntry implements Parcelable {
    public static final Parcelable.Creator<Entry> CREATOR = new a();
    public float k;

    /* loaded from: classes9.dex */
    public static class a implements Parcelable.Creator<Entry> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public Entry createFromParcel(Parcel parcel) {
            return new Entry(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public Entry[] newArray(int i) {
            return new Entry[i];
        }
    }

    public Entry() {
        this.k = 0.0f;
    }

    public Entry copy() {
        return new Entry(this.k, getY(), getData());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equalTo(Entry entry) {
        if (entry != null && entry.getData() == getData()) {
            float abs = Math.abs(entry.k - this.k);
            float f = Utils.FLOAT_EPSILON;
            return abs <= f && Math.abs(entry.getY() - getY()) <= f;
        }
        return false;
    }

    public float getX() {
        return this.k;
    }

    public void setX(float f) {
        this.k = f;
    }

    public String toString() {
        return "Entry, x: " + this.k + " y: " + getY();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.k);
        parcel.writeFloat(getY());
        if (getData() != null) {
            if (getData() instanceof Parcelable) {
                parcel.writeInt(1);
                parcel.writeParcelable((Parcelable) getData(), i);
                return;
            }
            throw new ParcelFormatException("Cannot parcel an Entry with non-parcelable data");
        }
        parcel.writeInt(0);
    }

    public Entry(float f, float f2) {
        super(f2);
        this.k = 0.0f;
        this.k = f;
    }

    public Entry(float f, float f2, Object obj) {
        super(f2, obj);
        this.k = 0.0f;
        this.k = f;
    }

    public Entry(float f, float f2, Drawable drawable) {
        super(f2, drawable);
        this.k = 0.0f;
        this.k = f;
    }

    public Entry(float f, float f2, Drawable drawable, Object obj) {
        super(f2, drawable, obj);
        this.k = 0.0f;
        this.k = f;
    }

    public Entry(Parcel parcel) {
        this.k = 0.0f;
        this.k = parcel.readFloat();
        setY(parcel.readFloat());
        if (parcel.readInt() == 1) {
            setData(parcel.readParcelable(Object.class.getClassLoader()));
        }
    }
}
