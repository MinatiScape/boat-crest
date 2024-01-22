package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface OnMapReadyCallback {
    void onMapError(int i, String str);

    void onMapReady(@NonNull MapplsMap mapplsMap);
}
