package com.mappls.sdk.maps.utils;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.MapplsMapOptions;
import com.mappls.sdk.maps.constants.MapplsConstants;
/* loaded from: classes11.dex */
public class MapFragmentUtils {
    @NonNull
    public static Bundle createFragmentArgs(MapplsMapOptions mapplsMapOptions) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(MapplsConstants.FRAG_ARG_MAPPLSMAPOPTIONS, mapplsMapOptions);
        return bundle;
    }

    @Nullable
    public static MapplsMapOptions resolveArgs(@NonNull Context context, @Nullable Bundle bundle) {
        if (bundle != null && bundle.containsKey(MapplsConstants.FRAG_ARG_MAPPLSMAPOPTIONS)) {
            return (MapplsMapOptions) bundle.getParcelable(MapplsConstants.FRAG_ARG_MAPPLSMAPOPTIONS);
        }
        return MapplsMapOptions.createFromAttributes(context);
    }
}
