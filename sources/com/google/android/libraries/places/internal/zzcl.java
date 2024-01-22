package com.google.android.libraries.places.internal;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
/* loaded from: classes10.dex */
public final class zzcl {
    public static int zza(@Nullable String str) {
        if (str == null) {
            return 13;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1698126997:
                if (str.equals("REQUEST_DENIED")) {
                    c = 3;
                    break;
                }
                break;
            case -1125000185:
                if (str.equals("INVALID_REQUEST")) {
                    c = 4;
                    break;
                }
                break;
            case -813482689:
                if (str.equals("ZERO_RESULTS")) {
                    c = 1;
                    break;
                }
                break;
            case 2524:
                if (str.equals(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                    c = 0;
                    break;
                }
                break;
            case 1023286998:
                if (str.equals("NOT_FOUND")) {
                    c = 5;
                    break;
                }
                break;
            case 1776037267:
                if (str.equals("UNKNOWN_ERROR")) {
                    c = 6;
                    break;
                }
                break;
            case 1831775833:
                if (str.equals("OVER_QUERY_LIMIT")) {
                    c = 2;
                    break;
                }
                break;
        }
        if (c == 0 || c == 1) {
            return 0;
        }
        if (c != 2) {
            if (c != 3) {
                if (c != 4) {
                    if (c != 5) {
                        return 13;
                    }
                    return PlacesStatusCodes.NOT_FOUND;
                }
                return PlacesStatusCodes.INVALID_REQUEST;
            }
            return PlacesStatusCodes.REQUEST_DENIED;
        }
        return PlacesStatusCodes.OVER_QUERY_LIMIT;
    }

    @Nullable
    public static String zza(@Nullable String str, @Nullable String str2) {
        return TextUtils.isEmpty(str2) ? str : str2;
    }
}
