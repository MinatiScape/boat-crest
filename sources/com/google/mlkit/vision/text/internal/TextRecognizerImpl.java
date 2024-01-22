package com.google.mlkit.vision.text.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.Feature;
import com.google.android.gms.internal.mlkit_vision_text_common.zzkr;
import com.google.android.gms.internal.mlkit_vision_text_common.zzkt;
import com.google.android.gms.internal.mlkit_vision_text_common.zzku;
import com.google.android.gms.internal.mlkit_vision_text_common.zzmk;
import com.google.android.gms.internal.mlkit_vision_text_common.zzmn;
import com.google.android.gms.internal.mlkit_vision_text_common.zzog;
import com.google.android.gms.internal.mlkit_vision_text_common.zzoj;
import com.google.android.gms.tasks.Task;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.MobileVisionBase;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import java.util.concurrent.Executor;
/* loaded from: classes10.dex */
public class TextRecognizerImpl extends MobileVisionBase<Text> implements TextRecognizer {
    public final boolean n;

    public TextRecognizerImpl(@NonNull TextRecognizerTaskWithResource textRecognizerTaskWithResource, @NonNull Executor executor, @NonNull zzog zzogVar, @NonNull TextRecognizerOptionsInterface textRecognizerOptionsInterface) {
        super(textRecognizerTaskWithResource, executor);
        boolean isThickClient = textRecognizerOptionsInterface.getIsThickClient();
        this.n = isThickClient;
        zzku zzkuVar = new zzku();
        zzkuVar.zze(isThickClient ? zzkr.TYPE_THICK : zzkr.TYPE_THIN);
        zzmk zzmkVar = new zzmk();
        zzmn zzmnVar = new zzmn();
        zzmnVar.zza(LoggingUtils.a(textRecognizerOptionsInterface.getLoggingLanguageOption()));
        zzmkVar.zze(zzmnVar.zzc());
        zzkuVar.zzh(zzmkVar.zzf());
        zzogVar.zzd(zzoj.zzg(zzkuVar, 1), zzkt.ON_DEVICE_TEXT_CREATE);
    }

    @Override // com.google.mlkit.vision.interfaces.Detector
    public final int getDetectorType() {
        return 4;
    }

    @Override // com.google.android.gms.common.api.OptionalModuleApi
    @NonNull
    public final Feature[] getOptionalFeatures() {
        return this.n ? OptionalModuleUtils.EMPTY_FEATURES : new Feature[]{OptionalModuleUtils.FEATURE_OCR};
    }

    @Override // com.google.mlkit.vision.text.TextRecognizer
    @NonNull
    public final Task<Text> process(@NonNull MlImage mlImage) {
        return super.processBase(mlImage);
    }

    @Override // com.google.mlkit.vision.text.TextRecognizer
    @NonNull
    public final Task<Text> process(@NonNull InputImage inputImage) {
        return super.processBase(inputImage);
    }
}
