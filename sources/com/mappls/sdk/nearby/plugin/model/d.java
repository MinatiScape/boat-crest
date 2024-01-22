package com.mappls.sdk.nearby.plugin.model;

import android.os.Parcel;
import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
public final class d extends b {
    public d(Integer num, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable String str6) {
        super(num, str, str2, str3, str4, str5, bool, bool2, str6);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(radius().intValue());
        if (sortBy() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(sortBy());
        }
        if (searchBy() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(searchBy());
        }
        if (bounds() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(bounds());
        }
        if (pod() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(pod());
        }
        if (filter() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(filter());
        }
        if (explain() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(explain().booleanValue() ? 1 : 0);
        }
        if (richData() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(richData().booleanValue() ? 1 : 0);
        }
        if (userName() == null) {
            parcel.writeInt(1);
            return;
        }
        parcel.writeInt(0);
        parcel.writeString(userName());
    }
}
