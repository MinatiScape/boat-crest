package com.google.android.libraries.places.widget.internal.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.Constants;
import com.google.android.libraries.places.R;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.internal.zzdj;
import com.google.android.libraries.places.internal.zzdk;
import com.google.android.libraries.places.internal.zzdl;
import com.google.android.libraries.places.internal.zzed;
import com.google.android.libraries.places.internal.zzef;
import com.google.android.libraries.places.internal.zzej;
import com.google.android.libraries.places.internal.zzen;
import com.google.android.libraries.places.internal.zzes;
import com.google.android.libraries.places.internal.zzey;
import com.google.android.libraries.places.internal.zzez;
import com.google.android.libraries.places.internal.zzfa;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
@SuppressLint({"ValidFragment"})
/* loaded from: classes10.dex */
public final class AutocompleteImplFragment extends Fragment {
    private final PlacesClient zza;
    private final zzed zzb;
    private final zzfa zzc;
    private final com.google.android.libraries.places.internal.zzb zzd;
    private zzes zze;
    private PlaceSelectionListener zzf;
    private EditText zzg;
    private RecyclerView zzh;
    private View zzi;
    private View zzj;
    private View zzk;
    private View zzl;
    private View zzm;
    private View zzn;
    private View zzo;
    private View zzp;
    private TextView zzq;
    private TextView zzr;
    private zzl zzs;
    private final zzc zzt;

    /* loaded from: classes10.dex */
    public static final class zza extends FragmentFactory {
        private final int zza;
        private final PlacesClient zzb;
        private final zzed zzc;
        private final zzfa zzd;
        private final com.google.android.libraries.places.internal.zzb zze;

        public zza(@LayoutRes int i, @NonNull Context context, @NonNull zzed zzedVar) {
            this.zza = i;
            Context applicationContext = context.getApplicationContext();
            zzdj zzb = zzdj.zza(applicationContext).zza(zzdj.zzb.AUTOCOMPLETE_WIDGET).zzb();
            zzdl zzdlVar = new zzdl(applicationContext);
            this.zzb = Places.zza(applicationContext, zzb);
            this.zzc = zzedVar;
            this.zzd = new zzez(zzdlVar, zzb);
            this.zze = new com.google.android.libraries.places.internal.zzd();
        }

        @Override // androidx.fragment.app.FragmentFactory
        @NonNull
        public final Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String str) {
            if (FragmentFactory.loadFragmentClass(classLoader, str) == AutocompleteImplFragment.class) {
                return new AutocompleteImplFragment(this.zza, this.zzb, this.zzc, this.zzd, this.zze, null);
            }
            return super.instantiate(classLoader, str);
        }
    }

    /* loaded from: classes10.dex */
    public static final class zzb implements View.OnFocusChangeListener {
        private zzb() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public final void onFocusChange(View view, boolean z) {
            try {
                InputMethodManager inputMethodManager = (InputMethodManager) ContextCompat.getSystemService(view.getContext(), InputMethodManager.class);
                if (inputMethodManager != null) {
                    if (z) {
                        inputMethodManager.showSoftInput(view, 1);
                    } else {
                        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
            } catch (Error | RuntimeException e) {
                zzdk.zza(e);
                throw e;
            }
        }

        public /* synthetic */ zzb(zzg zzgVar) {
            this();
        }
    }

    /* loaded from: classes10.dex */
    public final class zzc implements TextWatcher {
        private zzc() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            try {
                AutocompleteImplFragment.this.zze.zza(editable.toString());
            } catch (Error | RuntimeException e) {
                zzdk.zza(e);
                throw e;
            }
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public /* synthetic */ zzc(AutocompleteImplFragment autocompleteImplFragment, zzg zzgVar) {
            this();
        }
    }

    private AutocompleteImplFragment(@LayoutRes int i, @NonNull PlacesClient placesClient, @NonNull zzed zzedVar, @NonNull zzfa zzfaVar, @NonNull com.google.android.libraries.places.internal.zzb zzbVar) {
        super(i);
        this.zzt = new zzc(this, null);
        this.zza = placesClient;
        this.zzb = zzedVar;
        this.zzc = zzfaVar;
        this.zzd = zzbVar;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        try {
            zzey zzeyVar = new zzey(this.zzb.zzc(), this.zzb.zza(), this.zzb.zzd(), this.zzd);
            zzes zzesVar = (zzes) ViewModelProviders.of(this, new zzes.zza(new zzen(this.zza, this.zzb, zzeyVar.zzc()), zzeyVar, this.zzc)).get(zzes.class);
            this.zze = zzesVar;
            zzesVar.zza(bundle);
            requireActivity().getOnBackPressedDispatcher().addCallback(this, new zzg(this, true));
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onPause() {
        super.onPause();
        this.zze.zze();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
        this.zze.zzd();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        String zze;
        int identifier;
        try {
            this.zzg = (EditText) view.findViewById(R.id.places_autocomplete_search_bar);
            this.zzh = (RecyclerView) view.findViewById(R.id.places_autocomplete_list);
            this.zzi = view.findViewById(R.id.places_autocomplete_back_button);
            this.zzj = view.findViewById(R.id.places_autocomplete_clear_button);
            this.zzk = view.findViewById(R.id.places_autocomplete_search_bar_separator);
            this.zzl = view.findViewById(R.id.places_autocomplete_progress);
            this.zzm = view.findViewById(R.id.places_autocomplete_try_again_progress);
            this.zzn = view.findViewById(R.id.places_autocomplete_powered_by_google);
            this.zzo = view.findViewById(R.id.places_autocomplete_powered_by_google_separator);
            this.zzp = view.findViewById(R.id.places_autocomplete_sad_cloud);
            this.zzq = (TextView) view.findViewById(R.id.places_autocomplete_error_message);
            this.zzr = (TextView) view.findViewById(R.id.places_autocomplete_try_again);
            this.zzg.addTextChangedListener(this.zzt);
            this.zzg.setOnFocusChangeListener(new zzb(null));
            EditText editText = this.zzg;
            if (TextUtils.isEmpty(this.zzb.zze())) {
                zze = getString(R.string.places_autocomplete_search_hint);
            } else {
                zze = this.zzb.zze();
            }
            editText.setHint(zze);
            int i = zzh.zza[this.zzb.zza().ordinal()];
            if (i != 1) {
                if (i == 2 && Build.VERSION.SDK_INT >= 19 && (identifier = getResources().getIdentifier("status_bar_height", "dimen", Constants.KEY_ANDROID)) > 0) {
                    requireActivity().getWindow().addFlags(67108864);
                    ViewCompat.setPaddingRelative(view, view.getPaddingLeft(), view.getPaddingTop() + getResources().getDimensionPixelSize(identifier), view.getPaddingRight(), view.getPaddingBottom());
                }
            } else {
                int zzj = this.zzb.zzj();
                int zzk = this.zzb.zzk();
                if (Color.alpha(zzj) < 255) {
                    zzj = 0;
                }
                if (zzj != 0 && zzk != 0) {
                    int zza2 = zzej.zza(zzj, ContextCompat.getColor(requireContext(), R.color.places_text_white_alpha_87), ContextCompat.getColor(requireContext(), R.color.places_text_black_alpha_87));
                    int zza3 = zzej.zza(zzj, ContextCompat.getColor(requireContext(), R.color.places_text_white_alpha_26), ContextCompat.getColor(requireContext(), R.color.places_text_black_alpha_26));
                    view.findViewById(R.id.places_autocomplete_search_bar_container).setBackgroundColor(zzj);
                    int i2 = Build.VERSION.SDK_INT;
                    if (i2 >= 21) {
                        Window window = requireActivity().getWindow();
                        if (!zzej.zzb(zzk, -1, ViewCompat.MEASURED_STATE_MASK)) {
                            window.setStatusBarColor(zzk);
                        } else if (i2 >= 23) {
                            window.setStatusBarColor(zzk);
                            window.getDecorView().setSystemUiVisibility(8192);
                        }
                    }
                    this.zzg.setTextColor(zza2);
                    this.zzg.setHintTextColor(zza3);
                    zzej.zza((ImageView) this.zzi, zza2);
                    zzej.zza((ImageView) this.zzj, zza2);
                }
            }
            this.zzi.setOnClickListener(new View.OnClickListener(this) { // from class: com.google.android.libraries.places.widget.internal.ui.zzc
                private final AutocompleteImplFragment zza;

                {
                    this.zza = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.zza.zzc(view2);
                }
            });
            this.zzj.setOnClickListener(new View.OnClickListener(this) { // from class: com.google.android.libraries.places.widget.internal.ui.zzb
                private final AutocompleteImplFragment zza;

                {
                    this.zza = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.zza.zzb(view2);
                }
            });
            this.zzr.setOnClickListener(new View.OnClickListener(this) { // from class: com.google.android.libraries.places.widget.internal.ui.zzd
                private final AutocompleteImplFragment zza;

                {
                    this.zza = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.zza.zza(view2);
                }
            });
            this.zzs = new zzl(new zzo(this) { // from class: com.google.android.libraries.places.widget.internal.ui.zze
                private final AutocompleteImplFragment zza;

                {
                    this.zza = this;
                }

                @Override // com.google.android.libraries.places.widget.internal.ui.zzo
                public final void zza(AutocompletePrediction autocompletePrediction, int i3) {
                    this.zza.zza(autocompletePrediction, i3);
                }
            });
            this.zzh.setLayoutManager(new LinearLayoutManager(requireContext()));
            this.zzh.setItemAnimator(new zzj(getResources()));
            this.zzh.setAdapter(this.zzs);
            this.zzh.addOnScrollListener(new zzf(this));
            this.zze.zza().observe(getViewLifecycleOwner(), new Observer(this) { // from class: com.google.android.libraries.places.widget.internal.ui.zza
                private final AutocompleteImplFragment zza;

                {
                    this.zza = this;
                }

                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.zza.zza((zzef) obj);
                }
            });
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    public final void zza(@NonNull PlaceSelectionListener placeSelectionListener) {
        this.zzf = placeSelectionListener;
    }

    public final /* synthetic */ void zzb(View view) {
        try {
            this.zze.zzc();
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    public final /* synthetic */ void zzc(View view) {
        this.zze.zzf();
    }

    public final /* synthetic */ void zza(View view) {
        try {
            this.zze.zzb(this.zzg.getText().toString());
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    public final /* synthetic */ void zza(AutocompletePrediction autocompletePrediction, int i) {
        try {
            this.zze.zza(autocompletePrediction, i);
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    public /* synthetic */ AutocompleteImplFragment(int i, PlacesClient placesClient, zzed zzedVar, zzfa zzfaVar, com.google.android.libraries.places.internal.zzb zzbVar, zzg zzgVar) {
        this(i, placesClient, zzedVar, zzfaVar, zzbVar);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final /* synthetic */ void zza(zzef zzefVar) {
        try {
            this.zzj.setVisibility(0);
            this.zzk.setVisibility(0);
            this.zzl.setVisibility(8);
            this.zzm.setVisibility(8);
            this.zzn.setVisibility(0);
            this.zzo.setVisibility(8);
            this.zzp.setVisibility(8);
            this.zzq.setVisibility(8);
            this.zzr.setVisibility(8);
            switch (zzh.zzb[zzefVar.zza().ordinal()]) {
                case 1:
                    if (TextUtils.isEmpty(this.zzb.zzd())) {
                        this.zzj.setVisibility(8);
                    }
                    this.zzg.requestFocus();
                    this.zzg.setText(this.zzb.zzd());
                    EditText editText = this.zzg;
                    editText.setSelection(editText.getText().length());
                    return;
                case 2:
                    this.zzs.submitList(null);
                    this.zzj.setVisibility(8);
                    this.zzg.getText().clear();
                    return;
                case 3:
                    this.zzl.setVisibility(0);
                    return;
                case 4:
                    this.zzr.setVisibility(8);
                    this.zzm.setVisibility(0);
                    this.zzn.setVisibility(8);
                    this.zzp.setVisibility(0);
                    this.zzq.setVisibility(0);
                    return;
                case 5:
                    this.zzs.submitList(zzefVar.zzc());
                    this.zzo.setVisibility(0);
                    return;
                case 6:
                    this.zzs.submitList(null);
                    this.zzn.setVisibility(8);
                    this.zzp.setVisibility(0);
                    this.zzr.setVisibility(4);
                    this.zzq.setText(getString(R.string.places_autocomplete_no_results_for_query, zzefVar.zzb()));
                    this.zzq.setVisibility(0);
                    return;
                case 7:
                    this.zzf.onError(zzefVar.zzf());
                    return;
                case 8:
                    this.zzg.clearFocus();
                    this.zzg.removeTextChangedListener(this.zzt);
                    this.zzg.setText(zzefVar.zze().getPrimaryText(null));
                    this.zzg.addTextChangedListener(this.zzt);
                    break;
                case 9:
                    break;
                case 10:
                    this.zzf.onPlaceSelected(zzefVar.zzd());
                    return;
                default:
                    return;
            }
            this.zzs.submitList(null);
            this.zzn.setVisibility(8);
            this.zzp.setVisibility(0);
            this.zzr.setVisibility(0);
            this.zzq.setText(getString(R.string.places_search_error));
            this.zzq.setVisibility(0);
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }
}
