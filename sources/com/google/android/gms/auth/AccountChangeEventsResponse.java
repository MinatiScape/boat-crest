package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;
@SafeParcelable.Class(creator = "AccountChangeEventsResponseCreator")
/* loaded from: classes6.dex */
public class AccountChangeEventsResponse extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<AccountChangeEventsResponse> CREATOR = new zzc();
    @SafeParcelable.VersionField(id = 1)
    public final int h;
    @SafeParcelable.Field(id = 2)
    public final List i;

    @SafeParcelable.Constructor
    public AccountChangeEventsResponse(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) List list) {
        this.h = i;
        this.i = (List) Preconditions.checkNotNull(list);
    }

    @NonNull
    public List<AccountChangeEvent> getEvents() {
        return this.i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeTypedList(parcel, 2, this.i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public AccountChangeEventsResponse(@NonNull List<AccountChangeEvent> list) {
        this.h = 1;
        this.i = (List) Preconditions.checkNotNull(list);
    }
}
