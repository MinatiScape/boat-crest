package com.google.android.libraries.places.widget;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.google.android.libraries.places.R;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.internal.zzdk;
import com.google.android.libraries.places.internal.zzec;
import com.google.android.libraries.places.internal.zzed;
import com.google.android.libraries.places.internal.zzgi;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.List;
/* loaded from: classes10.dex */
public class AutocompleteSupportFragment extends Fragment {
    private final MutableLiveData<CharSequence> zza;
    private final MutableLiveData<CharSequence> zzb;
    private zzed.zza zzc;
    private PlaceSelectionListener zzd;

    public AutocompleteSupportFragment() {
        super(R.layout.places_autocomplete_fragment);
        this.zza = new MutableLiveData<>();
        this.zzb = new MutableLiveData<>();
        this.zzc = zzed.zza(AutocompleteActivityMode.OVERLAY, zzgi.zza(), zzec.FRAGMENT);
    }

    public static AutocompleteSupportFragment newInstance() {
        return new AutocompleteSupportFragment();
    }

    private final void zza() {
        Intent build = new Autocomplete.IntentBuilder(this.zzc.zza()).build(requireContext());
        if (requireView().isEnabled()) {
            requireView().setEnabled(false);
            startActivityForResult(build, 30421);
        }
    }

    private final void zzc(View view) {
        view.setVisibility(TextUtils.isEmpty(this.zza.getValue()) ^ true ? 0 : 8);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        if (i == 30421) {
            try {
                if (this.zzd == null) {
                    if (Log.isLoggable("Places", 5)) {
                        Log.w("Places", "No PlaceSelectionListener is set. No result will be delivered.");
                    }
                } else if (intent == null) {
                    if (Log.isLoggable("Places", 6)) {
                        Log.e("Places", "Intent data was null.");
                    }
                } else if (i2 == -1) {
                    Place placeFromIntent = Autocomplete.getPlaceFromIntent(intent);
                    this.zzd.onPlaceSelected(placeFromIntent);
                    setText(placeFromIntent.getName());
                } else {
                    this.zzd.onError(Autocomplete.getStatusFromIntent(intent));
                }
            } catch (Error | RuntimeException e) {
                zzdk.zza(e);
                throw e;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            try {
                zzed zzedVar = (zzed) bundle.getParcelable("options");
                if (zzedVar != null) {
                    if (this.zza.getValue() == null) {
                        this.zza.postValue(zzedVar.zzd());
                    }
                    if (this.zzb.getValue() == null) {
                        this.zzb.postValue(zzedVar.zze());
                    }
                    this.zzc = zzedVar.zzl();
                }
            } catch (Error | RuntimeException e) {
                zzdk.zza(e);
                throw e;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        requireView().setEnabled(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        bundle.putParcelable("options", this.zzc.zza());
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        final View findViewById = view.findViewById(R.id.places_autocomplete_search_button);
        final View findViewById2 = view.findViewById(R.id.places_autocomplete_clear_button);
        final EditText editText = (EditText) view.findViewById(R.id.places_autocomplete_search_input);
        findViewById.setOnClickListener(new View.OnClickListener(this) { // from class: com.google.android.libraries.places.widget.zze
            private final AutocompleteSupportFragment zza;

            {
                this.zza = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.zza.zzb(view2);
            }
        });
        editText.setOnClickListener(new View.OnClickListener(this) { // from class: com.google.android.libraries.places.widget.zzg
            private final AutocompleteSupportFragment zza;

            {
                this.zza = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.zza.zza(view2);
            }
        });
        findViewById2.setOnClickListener(new View.OnClickListener(this) { // from class: com.google.android.libraries.places.widget.zzf
            private final AutocompleteSupportFragment zza;

            {
                this.zza = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.zza.setText(null);
            }
        });
        zzc(findViewById2);
        this.zza.observe(getViewLifecycleOwner(), new Observer(this, editText, findViewById2) { // from class: com.google.android.libraries.places.widget.zzi
            private final AutocompleteSupportFragment zza;
            private final EditText zzb;
            private final View zzc;

            {
                this.zza = this;
                this.zzb = editText;
                this.zzc = findViewById2;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.zza.zzb(this.zzb, this.zzc, (CharSequence) obj);
            }
        });
        this.zzb.observe(getViewLifecycleOwner(), new Observer(editText, findViewById) { // from class: com.google.android.libraries.places.widget.zzh
            private final EditText zza;
            private final View zzb;

            {
                this.zza = editText;
                this.zzb = findViewById;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AutocompleteSupportFragment.zza(this.zza, this.zzb, (CharSequence) obj);
            }
        });
    }

    @NonNull
    public AutocompleteSupportFragment setActivityMode(@NonNull AutocompleteActivityMode autocompleteActivityMode) {
        this.zzc.zza(autocompleteActivityMode);
        return this;
    }

    @NonNull
    public AutocompleteSupportFragment setCountries(@NonNull List<String> list) {
        this.zzc.zzb(list);
        return this;
    }

    @NonNull
    public AutocompleteSupportFragment setCountry(@Nullable String str) {
        this.zzc.zzc(str);
        return this;
    }

    @NonNull
    public AutocompleteSupportFragment setHint(@Nullable CharSequence charSequence) {
        try {
            if (charSequence == null) {
                String string = getString(R.string.places_autocomplete_search_hint);
                this.zzc.zzb(string);
                this.zzb.postValue(string);
            } else {
                this.zzc.zzb(charSequence.toString());
                this.zzb.postValue(charSequence);
            }
            return this;
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @NonNull
    public AutocompleteSupportFragment setLocationBias(@Nullable LocationBias locationBias) {
        this.zzc.zza(locationBias);
        return this;
    }

    @NonNull
    public AutocompleteSupportFragment setLocationRestriction(@Nullable LocationRestriction locationRestriction) {
        this.zzc.zza(locationRestriction);
        return this;
    }

    @NonNull
    public AutocompleteSupportFragment setOnPlaceSelectedListener(@Nullable PlaceSelectionListener placeSelectionListener) {
        this.zzd = placeSelectionListener;
        return this;
    }

    @NonNull
    public AutocompleteSupportFragment setPlaceFields(@NonNull List<Place.Field> list) {
        this.zzc.zza(list);
        return this;
    }

    @NonNull
    public AutocompleteSupportFragment setText(@Nullable CharSequence charSequence) {
        try {
            this.zzc.zza(TextUtils.isEmpty(charSequence) ? null : charSequence.toString());
            this.zza.postValue(charSequence);
            return this;
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @NonNull
    public AutocompleteSupportFragment setTypeFilter(@Nullable TypeFilter typeFilter) {
        this.zzc.zza(typeFilter);
        return this;
    }

    public final /* synthetic */ void zzb(EditText editText, View view, CharSequence charSequence) {
        try {
            editText.setText(charSequence);
            zzc(view);
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @NonNull
    public AutocompleteSupportFragment setCountries(@NonNull String... strArr) {
        this.zzc.zzb(zzgi.zza((Object[]) strArr));
        return this;
    }

    public static final /* synthetic */ void zza(EditText editText, View view, CharSequence charSequence) {
        try {
            editText.setHint(charSequence);
            view.setContentDescription(charSequence);
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    public final /* synthetic */ void zzb(View view) {
        zza();
    }

    public final /* synthetic */ void zza(View view) {
        zza();
    }
}
