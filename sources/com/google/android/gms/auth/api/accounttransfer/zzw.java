package com.google.android.gms.auth.api.accounttransfer;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.ArraySet;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.internal.auth.zzbz;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
@SafeParcelable.Class(creator = "AuthenticatorTransferInfoCreator")
/* loaded from: classes6.dex */
public final class zzw extends zzbz {
    public static final Parcelable.Creator<zzw> CREATOR = new zzx();
    public static final HashMap o;
    @SafeParcelable.Indicator
    public final Set h;
    @SafeParcelable.VersionField(id = 1)
    public final int i;
    @SafeParcelable.Field(getter = "getAccountType", id = 2)
    public String j;
    @SafeParcelable.Field(getter = "getStatus", id = 3)
    public int k;
    @SafeParcelable.Field(getter = "getTransferBytes", id = 4)
    public byte[] l;
    @SafeParcelable.Field(getter = "getPendingIntent", id = 5)
    public PendingIntent m;
    @SafeParcelable.Field(getter = "getDeviceMetaData", id = 6)
    public DeviceMetaData n;

    static {
        HashMap hashMap = new HashMap();
        o = hashMap;
        hashMap.put("accountType", FastJsonResponse.Field.forString("accountType", 2));
        hashMap.put(NotificationCompat.CATEGORY_STATUS, FastJsonResponse.Field.forInteger(NotificationCompat.CATEGORY_STATUS, 3));
        hashMap.put("transferBytes", FastJsonResponse.Field.forBase64("transferBytes", 4));
    }

    public zzw() {
        this.h = new ArraySet(3);
        this.i = 1;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final /* synthetic */ Map getFieldMappings() {
        return o;
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
                return Integer.valueOf(this.k);
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
    public final void setDecodedBytesInternal(FastJsonResponse.Field field, String str, byte[] bArr) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 4) {
            this.l = bArr;
            this.h.add(Integer.valueOf(safeParcelableFieldId));
            return;
        }
        throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be an byte array.");
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final void setIntegerInternal(FastJsonResponse.Field field, String str, int i) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 3) {
            this.k = i;
            this.h.add(Integer.valueOf(safeParcelableFieldId));
            return;
        }
        throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be an int.");
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public final void setStringInternal(FastJsonResponse.Field field, String str, String str2) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 2) {
            this.j = str2;
            this.h.add(Integer.valueOf(safeParcelableFieldId));
            return;
        }
        throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string.", Integer.valueOf(safeParcelableFieldId)));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        Set set = this.h;
        if (set.contains(1)) {
            SafeParcelWriter.writeInt(parcel, 1, this.i);
        }
        if (set.contains(2)) {
            SafeParcelWriter.writeString(parcel, 2, this.j, true);
        }
        if (set.contains(3)) {
            SafeParcelWriter.writeInt(parcel, 3, this.k);
        }
        if (set.contains(4)) {
            SafeParcelWriter.writeByteArray(parcel, 4, this.l, true);
        }
        if (set.contains(5)) {
            SafeParcelWriter.writeParcelable(parcel, 5, this.m, i, true);
        }
        if (set.contains(6)) {
            SafeParcelWriter.writeParcelable(parcel, 6, this.n, i, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public zzw(@SafeParcelable.Indicator Set set, @SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) byte[] bArr, @SafeParcelable.Param(id = 5) PendingIntent pendingIntent, @SafeParcelable.Param(id = 6) DeviceMetaData deviceMetaData) {
        this.h = set;
        this.i = i;
        this.j = str;
        this.k = i2;
        this.l = bArr;
        this.m = pendingIntent;
        this.n = deviceMetaData;
    }
}
