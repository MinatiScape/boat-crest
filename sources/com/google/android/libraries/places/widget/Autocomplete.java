package com.google.android.libraries.places.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.internal.zzdk;
import com.google.android.libraries.places.internal.zzec;
import com.google.android.libraries.places.internal.zzed;
import com.google.android.libraries.places.internal.zzeg;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.List;
/* loaded from: classes10.dex */
public final class Autocomplete {
    private Autocomplete() {
    }

    @NonNull
    public static Place getPlaceFromIntent(@NonNull Intent intent) {
        return zzeg.zza(intent);
    }

    @NonNull
    public static Status getStatusFromIntent(@NonNull Intent intent) {
        return zzeg.zzb(intent);
    }

    /* loaded from: classes10.dex */
    public static class IntentBuilder {
        private final zzed.zza zza;

        public IntentBuilder(@NonNull zzed zzedVar) {
            this.zza = zzedVar.zzl();
        }

        @NonNull
        public Intent build(@NonNull Context context) {
            try {
                Intent intent = new Intent(context, AutocompleteActivity.class);
                zzed.zza zzaVar = this.zza;
                Resources.Theme theme = context.getTheme();
                TypedValue typedValue = new TypedValue();
                if (theme.resolveAttribute(16843827, typedValue, true)) {
                    zzaVar.zza(typedValue.data);
                }
                TypedValue typedValue2 = new TypedValue();
                if (theme.resolveAttribute(16843828, typedValue2, true)) {
                    zzaVar.zzb(typedValue2.data);
                }
                intent.putExtra("places/AutocompleteOptions", this.zza.zza());
                return intent;
            } catch (Error | RuntimeException e) {
                zzdk.zza(e);
                throw e;
            }
        }

        @NonNull
        public IntentBuilder setCountries(@NonNull List<String> list) {
            this.zza.zzb(list);
            return this;
        }

        @NonNull
        public IntentBuilder setCountry(@Nullable String str) {
            this.zza.zzc(str);
            return this;
        }

        @NonNull
        public IntentBuilder setHint(@Nullable String str) {
            this.zza.zzb(str);
            return this;
        }

        @NonNull
        public IntentBuilder setInitialQuery(@Nullable String str) {
            this.zza.zza(str);
            return this;
        }

        @NonNull
        public IntentBuilder setLocationBias(@Nullable LocationBias locationBias) {
            this.zza.zza(locationBias);
            return this;
        }

        @NonNull
        public IntentBuilder setLocationRestriction(@Nullable LocationRestriction locationRestriction) {
            this.zza.zza(locationRestriction);
            return this;
        }

        @NonNull
        public IntentBuilder setTypeFilter(@Nullable TypeFilter typeFilter) {
            this.zza.zza(typeFilter);
            return this;
        }

        @NonNull
        public final IntentBuilder zza(@NonNull zzec zzecVar) {
            this.zza.zza(zzecVar);
            return this;
        }

        public IntentBuilder(@NonNull AutocompleteActivityMode autocompleteActivityMode, @NonNull List<Place.Field> list) {
            this.zza = zzed.zza(autocompleteActivityMode, list, zzec.INTENT);
        }
    }
}
