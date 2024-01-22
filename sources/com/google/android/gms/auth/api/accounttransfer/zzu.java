package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.internal.auth.zzbz;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@SafeParcelable.Class(creator = "AuthenticatorAnnotatedDataCreator")
/* loaded from: classes6.dex */
public final class zzu extends zzbz {
    public static final Parcelable.Creator<zzu> CREATOR = new zzv();
    public static final HashMap n;
    @SafeParcelable.Indicator
    public final Set h;
    @SafeParcelable.VersionField(id = 1)
    public final int i;
    @SafeParcelable.Field(getter = "getInfo", id = 2)
    public zzw j;
    @SafeParcelable.Field(getter = "getSignature", id = 3)
    public String k;
    @SafeParcelable.Field(getter = "getPackageName", id = 4)
    public String l;
    @SafeParcelable.Field(getter = "getId", id = 5)
    public String m;

    static {
        HashMap hashMap = new HashMap();
        n = hashMap;
        hashMap.put("authenticatorInfo", FastJsonResponse.Field.forConcreteType("authenticatorInfo", 2, zzw.class));
        hashMap.put("signature", FastJsonResponse.Field.forString("signature", 3));
        hashMap.put("package", FastJsonResponse.Field.forString("package", 4));
    }

    public zzu() {
        this.h = new HashSet(3);
        this.i = 1;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final void addConcreteTypeInternal(FastJsonResponse.Field field, String str, FastJsonResponse fastJsonResponse) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 2) {
            this.j = (zzw) fastJsonResponse;
            this.h.add(Integer.valueOf(safeParcelableFieldId));
            return;
        }
        throw new IllegalArgumentException(String.format("Field with id=%d is not a known custom type. Found %s", Integer.valueOf(safeParcelableFieldId), fastJsonResponse.getClass().getCanonicalName()));
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final /* synthetic */ Map getFieldMappings() {
        return n;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final Object getFieldValue(FastJsonResponse.Field field) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId != 1) {
            if (safeParcelableFieldId != 2) {
                if (safeParcelableFieldId != 3) {
                    if (safeParcelableFieldId == 4) {
                        return this.l;
                    }
                    int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
                    throw new IllegalStateException("Unknown SafeParcelable id=" + safeParcelableFieldId2);
                }
                return this.k;
            }
            return this.j;
        }
        return Integer.valueOf(this.i);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final boolean isFieldSet(FastJsonResponse.Field field) {
        return this.h.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final void setStringInternal(FastJsonResponse.Field field, String str, String str2) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 3) {
            this.k = str2;
        } else if (safeParcelableFieldId == 4) {
            this.l = str2;
        } else {
            throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string.", Integer.valueOf(safeParcelableFieldId)));
        }
        this.h.add(Integer.valueOf(safeParcelableFieldId));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        Set set = this.h;
        if (set.contains(1)) {
            SafeParcelWriter.writeInt(parcel, 1, this.i);
        }
        if (set.contains(2)) {
            SafeParcelWriter.writeParcelable(parcel, 2, this.j, i, true);
        }
        if (set.contains(3)) {
            SafeParcelWriter.writeString(parcel, 3, this.k, true);
        }
        if (set.contains(4)) {
            SafeParcelWriter.writeString(parcel, 4, this.l, true);
        }
        if (set.contains(5)) {
            SafeParcelWriter.writeString(parcel, 5, this.m, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public zzu(@SafeParcelable.Indicator Set set, @SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) zzw zzwVar, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) String str3) {
        this.h = set;
        this.i = i;
        this.j = zzwVar;
        this.k = str;
        this.l = str2;
        this.m = str3;
    }
}
