package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;
@SafeParcelable.Class(creator = "PatternItemCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public class PatternItem extends AbstractSafeParcelable {
    @SafeParcelable.Field(getter = "getType", id = 2)
    public final int h;
    @Nullable
    @SafeParcelable.Field(getter = "getLength", id = 3)
    public final Float i;
    public static final String j = PatternItem.class.getSimpleName();
    @NonNull
    public static final Parcelable.Creator<PatternItem> CREATOR = new zzj();

    @SafeParcelable.Constructor
    public PatternItem(@SafeParcelable.Param(id = 2) int i, @Nullable @SafeParcelable.Param(id = 3) Float f) {
        boolean z = false;
        if (i == 1 || (f != null && f.floatValue() >= 0.0f)) {
            z = true;
        }
        String valueOf = String.valueOf(f);
        StringBuilder sb = new StringBuilder(valueOf.length() + 45);
        sb.append("Invalid PatternItem: type=");
        sb.append(i);
        sb.append(" length=");
        sb.append(valueOf);
        Preconditions.checkArgument(z, sb.toString());
        this.h = i;
        this.i = f;
    }

    @Nullable
    public static List<PatternItem> a(@Nullable List<PatternItem> list) {
        PatternItem dash;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (PatternItem patternItem : list) {
            if (patternItem == null) {
                patternItem = null;
            } else {
                int i = patternItem.h;
                if (i == 0) {
                    Preconditions.checkState(patternItem.i != null, "length must not be null.");
                    dash = new Dash(patternItem.i.floatValue());
                } else if (i == 1) {
                    patternItem = new Dot();
                } else if (i != 2) {
                    String str = j;
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Unknown PatternItem type: ");
                    sb.append(i);
                    Log.w(str, sb.toString());
                } else {
                    Preconditions.checkState(patternItem.i != null, "length must not be null.");
                    dash = new Gap(patternItem.i.floatValue());
                }
                patternItem = dash;
            }
            arrayList.add(patternItem);
        }
        return arrayList;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PatternItem) {
            PatternItem patternItem = (PatternItem) obj;
            return this.h == patternItem.h && Objects.equal(this.i, patternItem.i);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.h), this.i);
    }

    @NonNull
    public String toString() {
        int i = this.h;
        String valueOf = String.valueOf(this.i);
        StringBuilder sb = new StringBuilder(valueOf.length() + 39);
        sb.append("[PatternItem: type=");
        sb.append(i);
        sb.append(" length=");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.h);
        SafeParcelWriter.writeFloatObject(parcel, 3, this.i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
