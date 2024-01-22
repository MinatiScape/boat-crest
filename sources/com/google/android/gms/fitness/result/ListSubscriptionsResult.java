package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@SafeParcelable.Class(creator = "ListSubscriptionsResultCreator")
@SafeParcelable.Reserved({3, 1000})
@Deprecated
/* loaded from: classes6.dex */
public class ListSubscriptionsResult extends AbstractSafeParcelable implements Result {
    @NonNull
    public static final Parcelable.Creator<ListSubscriptionsResult> CREATOR = new zzg();
    @SafeParcelable.Field(getter = "getSubscriptions", id = 1)
    public final List<Subscription> h;
    @SafeParcelable.Field(getter = "getStatus", id = 2)
    public final Status i;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public ListSubscriptionsResult(@NonNull @SafeParcelable.Param(id = 1) List<Subscription> list, @NonNull @SafeParcelable.Param(id = 2) Status status) {
        this.h = list;
        this.i = status;
    }

    @NonNull
    @ShowFirstParty
    public static ListSubscriptionsResult zzd(@NonNull Status status) {
        return new ListSubscriptionsResult(Collections.emptyList(), status);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ListSubscriptionsResult) {
            ListSubscriptionsResult listSubscriptionsResult = (ListSubscriptionsResult) obj;
            return this.i.equals(listSubscriptionsResult.i) && Objects.equal(this.h, listSubscriptionsResult.h);
        }
        return false;
    }

    @Override // com.google.android.gms.common.api.Result
    @NonNull
    public Status getStatus() {
        return this.i;
    }

    @NonNull
    public List<Subscription> getSubscriptions() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(this.i, this.h);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.i).add("subscriptions", this.h).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getSubscriptions(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public List<Subscription> getSubscriptions(@NonNull DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (Subscription subscription : this.h) {
            if (dataType.equals(subscription.zzs())) {
                arrayList.add(subscription);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }
}
