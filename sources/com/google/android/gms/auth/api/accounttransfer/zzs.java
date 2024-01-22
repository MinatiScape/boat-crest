package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.internal.auth.zzbz;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@SafeParcelable.Class(creator = "AccountTransferProgressCreator")
/* loaded from: classes6.dex */
public final class zzs extends zzbz {
    public static final Parcelable.Creator<zzs> CREATOR = new zzt();
    public static final ArrayMap n;
    @SafeParcelable.VersionField(id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "getRegisteredAccountTypes", id = 2)
    public List i;
    @SafeParcelable.Field(getter = "getInProgressAccountTypes", id = 3)
    public List j;
    @SafeParcelable.Field(getter = "getSuccessAccountTypes", id = 4)
    public List k;
    @SafeParcelable.Field(getter = "getFailedAccountTypes", id = 5)
    public List l;
    @SafeParcelable.Field(getter = "getEscrowedAccountTypes", id = 6)
    public List m;

    static {
        ArrayMap arrayMap = new ArrayMap();
        n = arrayMap;
        arrayMap.put("registered", FastJsonResponse.Field.forStrings("registered", 2));
        arrayMap.put("in_progress", FastJsonResponse.Field.forStrings("in_progress", 3));
        arrayMap.put(FirebaseAnalytics.Param.SUCCESS, FastJsonResponse.Field.forStrings(FirebaseAnalytics.Param.SUCCESS, 4));
        arrayMap.put("failed", FastJsonResponse.Field.forStrings("failed", 5));
        arrayMap.put("escrowed", FastJsonResponse.Field.forStrings("escrowed", 6));
    }

    public zzs() {
        this.h = 1;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final Map getFieldMappings() {
        return n;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final Object getFieldValue(FastJsonResponse.Field field) {
        switch (field.getSafeParcelableFieldId()) {
            case 1:
                return Integer.valueOf(this.h);
            case 2:
                return this.i;
            case 3:
                return this.j;
            case 4:
                return this.k;
            case 5:
                return this.l;
            case 6:
                return this.m;
            default:
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                throw new IllegalStateException("Unknown SafeParcelable id=" + safeParcelableFieldId);
        }
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final boolean isFieldSet(FastJsonResponse.Field field) {
        return true;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final void setStringsInternal(FastJsonResponse.Field field, String str, ArrayList arrayList) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 2) {
            this.i = arrayList;
        } else if (safeParcelableFieldId == 3) {
            this.j = arrayList;
        } else if (safeParcelableFieldId == 4) {
            this.k = arrayList;
        } else if (safeParcelableFieldId == 5) {
            this.l = arrayList;
        } else if (safeParcelableFieldId == 6) {
            this.m = arrayList;
        } else {
            throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string list.", Integer.valueOf(safeParcelableFieldId)));
        }
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeStringList(parcel, 2, this.i, false);
        SafeParcelWriter.writeStringList(parcel, 3, this.j, false);
        SafeParcelWriter.writeStringList(parcel, 4, this.k, false);
        SafeParcelWriter.writeStringList(parcel, 5, this.l, false);
        SafeParcelWriter.writeStringList(parcel, 6, this.m, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public zzs(@SafeParcelable.Param(id = 1) int i, @Nullable @SafeParcelable.Param(id = 2) List list, @Nullable @SafeParcelable.Param(id = 3) List list2, @Nullable @SafeParcelable.Param(id = 4) List list3, @Nullable @SafeParcelable.Param(id = 5) List list4, @Nullable @SafeParcelable.Param(id = 6) List list5) {
        this.h = i;
        this.i = list;
        this.j = list2;
        this.k = list3;
        this.l = list4;
        this.m = list5;
    }
}
