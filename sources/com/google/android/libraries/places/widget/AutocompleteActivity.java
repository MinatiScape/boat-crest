package com.google.android.libraries.places.widget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.R;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
import com.google.android.libraries.places.internal.zzdk;
import com.google.android.libraries.places.internal.zzed;
import com.google.android.libraries.places.internal.zzft;
import com.google.android.libraries.places.widget.internal.ui.AutocompleteImplFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
/* loaded from: classes10.dex */
public class AutocompleteActivity extends AppCompatActivity implements PlaceSelectionListener {
    public static final int RESULT_ERROR = 2;
    @VisibleForTesting
    private static boolean zzc = true;
    @LayoutRes
    private int zza;
    @StyleRes
    private int zzb;

    public AutocompleteActivity() {
        super(R.layout.places_autocomplete_activity);
    }

    private final void zza(int i, @Nullable Place place, @NonNull Status status) {
        try {
            Intent intent = new Intent();
            if (place != null) {
                intent.putExtra("places/selected_place", place);
            }
            intent.putExtra("places/status", status);
            setResult(i, intent);
            finish();
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"MissingSuperCall"})
    public void onCreate(@Nullable Bundle bundle) {
        try {
            zzft.zzb(Places.isInitialized(), "Places must be initialized.");
            if (zzc) {
                zzft.zzb(getCallingActivity() != null, "Cannot find caller. startActivityForResult should be used.");
            }
            zzed zzedVar = (zzed) zzft.zza((zzed) getIntent().getParcelableExtra("places/AutocompleteOptions"));
            int i = zzb.zza[zzedVar.zza().ordinal()];
            if (i == 1) {
                this.zza = R.layout.places_autocomplete_impl_fragment_overlay;
                this.zzb = R.style.PlacesAutocompleteOverlay;
            } else if (i == 2) {
                this.zza = R.layout.places_autocomplete_impl_fragment_fullscreen;
                this.zzb = R.style.PlacesAutocompleteFullscreen;
            }
            getSupportFragmentManager().setFragmentFactory(new AutocompleteImplFragment.zza(this.zza, this, zzedVar));
            setTheme(this.zzb);
            super.onCreate(bundle);
            final AutocompleteImplFragment autocompleteImplFragment = (AutocompleteImplFragment) getSupportFragmentManager().findFragmentById(R.id.places_autocomplete_content);
            autocompleteImplFragment.zza(this);
            findViewById(16908290).setOnTouchListener(new View.OnTouchListener(this, autocompleteImplFragment) { // from class: com.google.android.libraries.places.widget.zza
                private final AutocompleteActivity zza;
                private final AutocompleteImplFragment zzb;

                {
                    this.zza = this;
                    this.zzb = autocompleteImplFragment;
                }

                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return this.zza.zza(this.zzb, view, motionEvent);
                }
            });
            if (zzedVar.zzb().isEmpty()) {
                zza(2, (Place) null, new Status((int) PlacesStatusCodes.INVALID_REQUEST, "Place Fields must not be empty."));
            }
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @Override // com.google.android.libraries.places.widget.listener.PlaceSelectionListener
    public void onError(@NonNull Status status) {
        zza(status.isCanceled() ? 0 : 2, (Place) null, status);
    }

    @Override // com.google.android.libraries.places.widget.listener.PlaceSelectionListener
    public void onPlaceSelected(@NonNull Place place) {
        zza(-1, place, Status.RESULT_SUCCESS);
    }

    public final /* synthetic */ boolean zza(AutocompleteImplFragment autocompleteImplFragment, View view, MotionEvent motionEvent) {
        View view2 = autocompleteImplFragment.getView();
        if (view2 != null && motionEvent.getY() > view2.getBottom()) {
            zza(0, (Place) null, new Status(16));
            return true;
        }
        return false;
    }
}
