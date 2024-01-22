package com.google.android.libraries.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.Objects;
/* loaded from: classes10.dex */
final class zzdv extends zzdu {
    public static final Parcelable.Creator<zzdv> CREATOR = new zzdy();

    public zzdv(AutocompleteActivityMode autocompleteActivityMode, zzgi<Place.Field> zzgiVar, zzec zzecVar, @Nullable String str, @Nullable String str2, @Nullable LocationBias locationBias, @Nullable LocationRestriction locationRestriction, zzgi<String> zzgiVar2, @Nullable TypeFilter typeFilter, int i, int i2) {
        super(autocompleteActivityMode, zzgiVar, zzecVar, str, str2, locationBias, locationRestriction, zzgiVar2, typeFilter, i, i2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(zza(), i);
        zzgi<Place.Field> zzb = zzb();
        Objects.requireNonNull(zzb);
        parcel.writeList(zzb);
        parcel.writeParcelable(zzc(), i);
        if (zzd() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(zzd());
        }
        if (zze() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(zze());
        }
        parcel.writeParcelable(zzf(), i);
        parcel.writeParcelable(zzg(), i);
        zzgi<String> zzh = zzh();
        Objects.requireNonNull(zzh);
        parcel.writeList(zzh);
        parcel.writeParcelable(zzi(), i);
        parcel.writeInt(zzj());
        parcel.writeInt(zzk());
    }
}
