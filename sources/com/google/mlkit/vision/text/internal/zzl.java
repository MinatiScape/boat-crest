package com.google.mlkit.vision.text.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.mlkit_vision_text_common.zzor;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
/* loaded from: classes10.dex */
public final class zzl {

    /* renamed from: a  reason: collision with root package name */
    public final zzm f11647a;
    public final ExecutorSelector b;

    public zzl(@NonNull zzm zzmVar, @NonNull ExecutorSelector executorSelector) {
        this.f11647a = zzmVar;
        this.b = executorSelector;
    }

    @NonNull
    public final TextRecognizer zza(@NonNull TextRecognizerOptionsInterface textRecognizerOptionsInterface) {
        return new TextRecognizerImpl((TextRecognizerTaskWithResource) this.f11647a.get(textRecognizerOptionsInterface), this.b.getExecutorToUse(textRecognizerOptionsInterface.getExecutor()), zzor.zzb(textRecognizerOptionsInterface.getLoggingLibraryName()), textRecognizerOptionsInterface);
    }
}
