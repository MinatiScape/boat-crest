package com.coveiot.android.navigation.interfaces;

import com.coveiot.android.navigation.models.FavouritePlaceData;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public interface FavouritePlaceListener {
    void onFavouritePlaceSelected(boolean z, @Nullable FavouritePlaceData favouritePlaceData, @NotNull ArrayList<FavouritePlaceData> arrayList);
}
