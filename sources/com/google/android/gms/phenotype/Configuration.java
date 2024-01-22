package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
@KeepForSdk
@SafeParcelable.Class(creator = "ConfigurationCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public class Configuration extends AbstractSafeParcelable implements Comparable<Configuration> {
    @KeepForSdk
    public static final Parcelable.Creator<Configuration> CREATOR = new zzc();
    @SafeParcelable.Field(id = 2)
    public final int h;
    @SafeParcelable.Field(id = 3)
    public final zzi[] i;
    @SafeParcelable.Field(id = 4)
    public final String[] j;
    public final Map<String, zzi> k = new TreeMap();

    @SafeParcelable.Constructor
    public Configuration(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) zzi[] zziVarArr, @SafeParcelable.Param(id = 4) String[] strArr) {
        this.h = i;
        this.i = zziVarArr;
        for (zzi zziVar : zziVarArr) {
            this.k.put(zziVar.name, zziVar);
        }
        this.j = strArr;
        if (strArr != null) {
            Arrays.sort(strArr);
        }
    }

    @Override // java.lang.Comparable
    public /* synthetic */ int compareTo(Configuration configuration) {
        return this.h - configuration.h;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Configuration) {
            Configuration configuration = (Configuration) obj;
            if (this.h == configuration.h && zzn.a(this.k, configuration.k) && Arrays.equals(this.j, configuration.j)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Configuration(");
        sb.append(this.h);
        sb.append(", ");
        sb.append("(");
        for (zzi zziVar : this.k.values()) {
            sb.append(zziVar);
            sb.append(", ");
        }
        sb.append(")");
        sb.append(", ");
        sb.append("(");
        String[] strArr = this.j;
        if (strArr != null) {
            for (String str : strArr) {
                sb.append(str);
                sb.append(", ");
            }
        } else {
            sb.append("null");
        }
        sb.append(")");
        sb.append(")");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.h);
        SafeParcelWriter.writeTypedArray(parcel, 3, this.i, i, false);
        SafeParcelWriter.writeStringArray(parcel, 4, this.j, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
