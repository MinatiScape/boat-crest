package com.clevertap.android.sdk.displayunits;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.FtsOptions;
import com.clevertap.android.sdk.Constants;
/* loaded from: classes2.dex */
public enum CTDisplayUnitType {
    SIMPLE(FtsOptions.TOKENIZER_SIMPLE),
    SIMPLE_WITH_IMAGE("simple-image"),
    CAROUSEL("carousel"),
    CAROUSEL_WITH_IMAGE("carousel-image"),
    MESSAGE_WITH_ICON("message-icon"),
    CUSTOM_KEY_VALUE("custom-key-value");
    
    public final String type;

    CTDisplayUnitType(String str) {
        this.type = str;
    }

    @Nullable
    public static CTDisplayUnitType type(@NonNull String str) {
        if (!TextUtils.isEmpty(str)) {
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1799711058:
                    if (str.equals("carousel-image")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1332589953:
                    if (str.equals("message-icon")) {
                        c = 1;
                        break;
                    }
                    break;
                case -902286926:
                    if (str.equals(FtsOptions.TOKENIZER_SIMPLE)) {
                        c = 2;
                        break;
                    }
                    break;
                case -876980953:
                    if (str.equals("custom-key-value")) {
                        c = 3;
                        break;
                    }
                    break;
                case 2908512:
                    if (str.equals("carousel")) {
                        c = 4;
                        break;
                    }
                    break;
                case 1818845568:
                    if (str.equals("simple-image")) {
                        c = 5;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    return CAROUSEL_WITH_IMAGE;
                case 1:
                    return MESSAGE_WITH_ICON;
                case 2:
                    return SIMPLE;
                case 3:
                    return CUSTOM_KEY_VALUE;
                case 4:
                    return CAROUSEL;
                case 5:
                    return SIMPLE_WITH_IMAGE;
            }
        }
        Log.d(Constants.FEATURE_DISPLAY_UNIT, "Unsupported Display Unit Type");
        return null;
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.type;
    }
}
