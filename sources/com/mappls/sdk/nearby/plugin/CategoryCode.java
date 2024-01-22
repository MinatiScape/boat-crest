package com.mappls.sdk.nearby.plugin;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.DrawableRes;
import androidx.annotation.Keep;
import java.util.ArrayList;
import java.util.List;
@Keep
/* loaded from: classes10.dex */
public class CategoryCode implements Parcelable {
    public static final Parcelable.Creator<CategoryCode> CREATOR = new a();
    private Bitmap bitmapIcon;
    private final String category;
    private final List<String> categoryCode;
    @DrawableRes
    private int icon;
    private boolean isSelected;
    private Bitmap markerBitmap;
    @DrawableRes
    private int markerIcon;

    /* loaded from: classes10.dex */
    public class a implements Parcelable.Creator<CategoryCode> {
        @Override // android.os.Parcelable.Creator
        public final CategoryCode createFromParcel(Parcel parcel) {
            return new CategoryCode(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final CategoryCode[] newArray(int i) {
            return new CategoryCode[i];
        }
    }

    public CategoryCode(Parcel parcel) {
        this.category = parcel.readString();
        this.icon = parcel.readInt();
        if (parcel.readByte() == 1) {
            ArrayList arrayList = new ArrayList();
            this.categoryCode = arrayList;
            parcel.readList(arrayList, String.class.getClassLoader());
        } else {
            this.categoryCode = null;
        }
        this.markerIcon = parcel.readInt();
        this.isSelected = parcel.readByte() != 0;
        this.markerBitmap = (Bitmap) parcel.readValue(Bitmap.class.getClassLoader());
        this.bitmapIcon = (Bitmap) parcel.readValue(Bitmap.class.getClassLoader());
    }

    public CategoryCode(String str, int i, List<String> list, int i2) {
        this.category = str;
        this.icon = i;
        this.categoryCode = list;
        this.markerIcon = i2;
        this.isSelected = false;
    }

    public CategoryCode(String str, int i, List<String> list, int i2, boolean z) {
        this.category = str;
        this.icon = i;
        this.categoryCode = list;
        this.markerIcon = i2;
        this.isSelected = z;
    }

    public CategoryCode(String str, Bitmap bitmap, List<String> list, Bitmap bitmap2) {
        this.category = str;
        this.bitmapIcon = bitmap;
        this.categoryCode = list;
        this.markerBitmap = bitmap2;
        this.isSelected = false;
    }

    public CategoryCode(String str, Bitmap bitmap, List<String> list, Bitmap bitmap2, boolean z) {
        this.category = str;
        this.bitmapIcon = bitmap;
        this.categoryCode = list;
        this.markerBitmap = bitmap2;
        this.isSelected = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bitmap getBitmapIcon() {
        return this.bitmapIcon;
    }

    public String getCategory() {
        return this.category;
    }

    public List<String> getCategoryCode() {
        return this.categoryCode;
    }

    public int getIcon() {
        return this.icon;
    }

    public Bitmap getMarkerBitmap() {
        return this.markerBitmap;
    }

    public int getMarkerIcon() {
        return this.markerIcon;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.category);
        parcel.writeInt(this.icon);
        if (this.categoryCode == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeList(this.categoryCode);
        }
        parcel.writeInt(this.markerIcon);
        parcel.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        parcel.writeValue(this.markerBitmap);
        parcel.writeValue(this.bitmapIcon);
    }
}
