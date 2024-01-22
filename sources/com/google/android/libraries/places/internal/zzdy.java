package com.google.android.libraries.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.Collection;
/* loaded from: classes10.dex */
final class zzdy implements Parcelable.Creator<zzdv> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzdv createFromParcel(Parcel parcel) {
        return new zzdv((AutocompleteActivityMode) parcel.readParcelable(zzed.class.getClassLoader()), zzgi.zza((Collection) parcel.readArrayList(Place.Field.class.getClassLoader())), (zzec) parcel.readParcelable(zzed.class.getClassLoader()), parcel.readInt() == 0 ? parcel.readString() : null, parcel.readInt() == 0 ? parcel.readString() : null, (LocationBias) parcel.readParcelable(zzed.class.getClassLoader()), (LocationRestriction) parcel.readParcelable(zzed.class.getClassLoader()), zzgi.zza((Collection) parcel.readArrayList(String.class.getClassLoader())), (TypeFilter) parcel.readParcelable(zzed.class.getClassLoader()), parcel.readInt(), parcel.readInt());
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzdv[] newArray(int i) {
        return new zzdv[i];
    }
}
