package com.google.mlkit.vision.text.internal;

import com.google.android.gms.internal.mlkit_vision_text_common.zzog;
import com.google.android.gms.internal.mlkit_vision_text_common.zzor;
import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
/* loaded from: classes10.dex */
public final class zzm extends LazyInstanceMap {
    public final MlKitContext b;

    public zzm(MlKitContext mlKitContext) {
        this.b = mlKitContext;
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    public final /* bridge */ /* synthetic */ Object create(Object obj) {
        TextRecognizerOptionsInterface textRecognizerOptionsInterface = (TextRecognizerOptionsInterface) obj;
        zzog zzb = zzor.zzb(textRecognizerOptionsInterface.getLoggingLibraryName());
        return new TextRecognizerTaskWithResource(zzb, TextRecognizerTaskWithResource.e(this.b.getApplicationContext(), textRecognizerOptionsInterface, zzb), textRecognizerOptionsInterface);
    }
}
