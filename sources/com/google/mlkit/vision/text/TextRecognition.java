package com.google.mlkit.vision.text;

import androidx.annotation.NonNull;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.text.internal.zzl;
/* loaded from: classes10.dex */
public class TextRecognition {
    @NonNull
    public static TextRecognizer getClient(@NonNull TextRecognizerOptionsInterface textRecognizerOptionsInterface) {
        return ((zzl) MlKitContext.getInstance().get(zzl.class)).zza(textRecognizerOptionsInterface);
    }
}
