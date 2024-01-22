package com.google.mlkit.vision.text.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_vision_text_common.zzkr;
import com.google.android.gms.internal.mlkit_vision_text_common.zzks;
import com.google.android.gms.internal.mlkit_vision_text_common.zzkt;
import com.google.android.gms.internal.mlkit_vision_text_common.zzku;
import com.google.android.gms.internal.mlkit_vision_text_common.zzmh;
import com.google.android.gms.internal.mlkit_vision_text_common.zzmo;
import com.google.android.gms.internal.mlkit_vision_text_common.zznv;
import com.google.android.gms.internal.mlkit_vision_text_common.zzoe;
import com.google.android.gms.internal.mlkit_vision_text_common.zzog;
import com.google.android.gms.internal.mlkit_vision_text_common.zzoj;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
@KeepForSdk
/* loaded from: classes10.dex */
public final class LoggingUtils {
    public static zzmo a(@TextRecognizerOptionsInterface.LanguageOption int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            return zzmo.TYPE_UNKNOWN;
                        }
                        return zzmo.LATIN_AND_KOREAN;
                    }
                    return zzmo.LATIN_AND_JAPANESE;
                }
                return zzmo.LATIN_AND_DEVANAGARI;
            }
            return zzmo.LATIN_AND_CHINESE;
        }
        return zzmo.LATIN;
    }

    public static void b(zzog zzogVar, final boolean z, final zzks zzksVar) {
        zzogVar.zzf(new zzoe() { // from class: com.google.mlkit.vision.text.internal.zzj
            @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzoe
            public final zznv zza() {
                boolean z2 = z;
                zzks zzksVar2 = zzksVar;
                zzku zzkuVar = new zzku();
                zzkuVar.zze(z2 ? zzkr.TYPE_THICK : zzkr.TYPE_THIN);
                zzmh zzmhVar = new zzmh();
                zzmhVar.zzb(zzksVar2);
                zzkuVar.zzg(zzmhVar.zzc());
                return zzoj.zzf(zzkuVar);
            }
        }, zzkt.ON_DEVICE_TEXT_LOAD);
    }
}
