package com.mappls.sdk.maps.utils;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import java.text.Normalizer;
@Keep
/* loaded from: classes11.dex */
class StringUtils {
    @NonNull
    @Keep
    public static String unaccent(@NonNull String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("(\\p{InCombiningDiacriticalMarks}|\\p{InCombiningDiacriticalMarksForSymbols}|\\p{InCombiningDiacriticalMarksSupplement})+", "");
    }
}
