package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.location.zzdh;
import java.util.ArrayList;
import java.util.List;
@SafeParcelable.Class(creator = "GeofencingRequestCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes10.dex */
public class GeofencingRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<GeofencingRequest> CREATOR = new zzn();
    public static final int INITIAL_TRIGGER_DWELL = 4;
    public static final int INITIAL_TRIGGER_ENTER = 1;
    public static final int INITIAL_TRIGGER_EXIT = 2;
    @SafeParcelable.Field(getter = "getParcelableGeofences", id = 1)
    public final List h;
    @InitialTrigger
    @SafeParcelable.Field(getter = "getInitialTrigger", id = 2)
    public final int i;
    @SafeParcelable.Field(defaultValue = "", getter = "getTag", id = 3)
    public final String j;
    @Nullable
    @SafeParcelable.Field(getter = "getContextAttributionTag", id = 4)
    public final String k;

    /* loaded from: classes10.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final List f10034a = new ArrayList();
        @InitialTrigger
        public int b = 5;
        public String c = "";

        @NonNull
        public Builder addGeofence(@NonNull Geofence geofence) {
            Preconditions.checkNotNull(geofence, "geofence can't be null.");
            Preconditions.checkArgument(geofence instanceof zzdh, "Geofence must be created using Geofence.Builder.");
            this.f10034a.add((zzdh) geofence);
            return this;
        }

        @NonNull
        public Builder addGeofences(@NonNull List<Geofence> list) {
            if (list != null && !list.isEmpty()) {
                for (Geofence geofence : list) {
                    if (geofence != null) {
                        addGeofence(geofence);
                    }
                }
            }
            return this;
        }

        @NonNull
        public GeofencingRequest build() {
            Preconditions.checkArgument(!this.f10034a.isEmpty(), "No geofence has been added to this request.");
            return new GeofencingRequest(this.f10034a, this.b, this.c, null);
        }

        @NonNull
        public Builder setInitialTrigger(@InitialTrigger int i) {
            this.b = i & 7;
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public @interface InitialTrigger {
    }

    @SafeParcelable.Constructor
    public GeofencingRequest(@SafeParcelable.Param(id = 1) List list, @SafeParcelable.Param(id = 2) @InitialTrigger int i, @SafeParcelable.Param(id = 3) String str, @Nullable @SafeParcelable.Param(id = 4) String str2) {
        this.h = list;
        this.i = i;
        this.j = str;
        this.k = str2;
    }

    @NonNull
    public List<Geofence> getGeofences() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.h);
        return arrayList;
    }

    @InitialTrigger
    public int getInitialTrigger() {
        return this.i;
    }

    @NonNull
    public String toString() {
        return "GeofencingRequest[geofences=" + this.h + ", initialTrigger=" + this.i + ", tag=" + this.j + ", attributionTag=" + this.k + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.h, false);
        SafeParcelWriter.writeInt(parcel, 2, getInitialTrigger());
        SafeParcelWriter.writeString(parcel, 3, this.j, false);
        SafeParcelWriter.writeString(parcel, 4, this.k, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public final GeofencingRequest zza(@Nullable String str) {
        return new GeofencingRequest(this.h, this.i, this.j, str);
    }
}
