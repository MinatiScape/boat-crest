package com.google.android.libraries.places.widget.listener;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
/* loaded from: classes10.dex */
public interface PlaceSelectionListener {
    void onError(@NonNull Status status);

    void onPlaceSelected(@NonNull Place place);
}
