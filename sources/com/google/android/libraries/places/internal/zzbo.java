package com.google.android.libraries.places.internal;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
import com.google.android.libraries.places.internal.zzaz;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public final class zzbo {
    public static FindAutocompletePredictionsResponse zza(zzbl zzblVar) throws ApiException {
        int zza = zzcl.zza(zzblVar.status);
        if (!PlacesStatusCodes.isError(zza)) {
            ArrayList arrayList = new ArrayList();
            zzaz[] zzazVarArr = zzblVar.predictions;
            if (zzazVarArr != null) {
                for (zzaz zzazVar : zzazVarArr) {
                    if (zzazVar != null && !TextUtils.isEmpty(zzazVar.zzc())) {
                        AutocompletePrediction.Builder zza2 = AutocompletePrediction.builder(zzazVar.zzc()).setDistanceMeters(zzazVar.zzb()).setPlaceTypes(zzci.zzb(zzci.zza(zzazVar.zze()))).setFullText(zzfz.zza(zzazVar.zza())).zza(zza(zzazVar.zzf()));
                        zzaz.zzb zzd = zzazVar.zzd();
                        if (zzd != null) {
                            zza2.setPrimaryText(zzfz.zza(zzd.zza())).zzb(zza(zzd.zzc())).setSecondaryText(zzfz.zza(zzd.zzb())).zzc(zza(zzd.zzd()));
                        }
                        arrayList.add(zza2.build());
                    } else {
                        throw new ApiException(new Status(8, "Unexpected server error: Place ID not provided for an autocomplete prediction result"));
                    }
                }
            }
            return FindAutocompletePredictionsResponse.newInstance(arrayList);
        }
        throw new ApiException(new Status(zza, zzcl.zza(zzblVar.status, zzblVar.errorMessage)));
    }

    @Nullable
    private static List<AutocompletePrediction.zza> zza(@Nullable List<zzaz.zza> list) throws ApiException {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (zzaz.zza zzaVar : list) {
            if (zzaVar != null && zzaVar.offset != null && zzaVar.length != null) {
                arrayList.add(AutocompletePrediction.zza.zzc().zza(zzaVar.offset.intValue()).zzb(zzaVar.length.intValue()).zza());
            } else {
                throw new ApiException(new Status(8, "Unexpected server error: Place ID not provided for an autocomplete prediction result"));
            }
        }
        return arrayList;
    }
}
