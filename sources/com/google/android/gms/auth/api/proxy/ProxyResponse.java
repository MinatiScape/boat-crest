package com.google.android.gms.auth.api.proxy;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
@ShowFirstParty
@KeepForSdkWithMembers
@SafeParcelable.Class(creator = "ProxyResponseCreator")
/* loaded from: classes6.dex */
public class ProxyResponse extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<ProxyResponse> CREATOR = new zzb();
    public static final int STATUS_CODE_NO_CONNECTION = -1;
    @NonNull
    @SafeParcelable.Field(id = 5)
    public final byte[] body;
    @SafeParcelable.Field(id = 1)
    public final int googlePlayServicesStatusCode;
    @SafeParcelable.VersionField(id = 1000)
    public final int h;
    @SafeParcelable.Field(id = 4)
    public final Bundle i;
    @NonNull
    @SafeParcelable.Field(id = 2)
    public final PendingIntent recoveryAction;
    @SafeParcelable.Field(id = 3)
    public final int statusCode;

    @SafeParcelable.Constructor
    public ProxyResponse(@SafeParcelable.Param(id = 1000) int i, @SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) PendingIntent pendingIntent, @SafeParcelable.Param(id = 3) int i3, @SafeParcelable.Param(id = 4) Bundle bundle, @SafeParcelable.Param(id = 5) byte[] bArr) {
        this.h = i;
        this.googlePlayServicesStatusCode = i2;
        this.statusCode = i3;
        this.i = bundle;
        this.body = bArr;
        this.recoveryAction = pendingIntent;
    }

    public static Bundle a(Map map) {
        Bundle bundle = new Bundle();
        if (map == null) {
            return bundle;
        }
        for (Map.Entry entry : map.entrySet()) {
            bundle.putString((String) entry.getKey(), (String) entry.getValue());
        }
        return bundle;
    }

    @NonNull
    public static ProxyResponse createErrorProxyResponse(int i, @NonNull PendingIntent pendingIntent, int i2, @NonNull Map<String, String> map, @NonNull byte[] bArr) {
        return new ProxyResponse(1, i, pendingIntent, i2, a(map), bArr);
    }

    @NonNull
    public Map<String, String> getHeaders() {
        if (this.i == null) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        for (String str : this.i.keySet()) {
            hashMap.put(str, this.i.getString(str));
        }
        return hashMap;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.googlePlayServicesStatusCode);
        SafeParcelWriter.writeParcelable(parcel, 2, this.recoveryAction, i, false);
        SafeParcelWriter.writeInt(parcel, 3, this.statusCode);
        SafeParcelWriter.writeBundle(parcel, 4, this.i, false);
        SafeParcelWriter.writeByteArray(parcel, 5, this.body, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.h);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public ProxyResponse(int i, @NonNull PendingIntent pendingIntent, int i2, @NonNull Bundle bundle, @NonNull byte[] bArr) {
        this(1, i, pendingIntent, i2, bundle, bArr);
    }

    public ProxyResponse(int i, @NonNull Map<String, String> map, @NonNull byte[] bArr) {
        this(1, 0, null, i, a(map), bArr);
    }
}
