package com.google.android.gms.vision.clearcut;

import android.content.Context;
import androidx.annotation.Keep;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.internal.vision.zzds;
import com.google.android.gms.internal.vision.zzea;
import com.google.android.gms.internal.vision.zzgd;
import com.google.android.gms.vision.L;
@Keep
/* loaded from: classes10.dex */
public class VisionClearcutLogger {
    private final ClearcutLogger zzbw;
    private boolean zzbx = true;

    public VisionClearcutLogger(Context context) {
        this.zzbw = new ClearcutLogger(context, "VISION", null);
    }

    public final void zzb(int i, zzea.zzo zzoVar) {
        byte[] byteArray = zzoVar.toByteArray();
        if (i < 0 || i > 3) {
            L.i("Illegal event code: %d", Integer.valueOf(i));
            return;
        }
        try {
            if (this.zzbx) {
                this.zzbw.newEvent(byteArray).setEventCode(i).log();
                return;
            }
            zzea.zzo.zza zzdi = zzea.zzo.zzdi();
            try {
                zzdi.zza(byteArray, 0, byteArray.length, zzgd.zzfm());
                L.e("Would have logged:\n%s", zzdi.toString());
            } catch (Exception e) {
                L.e(e, "Parsing error", new Object[0]);
            }
        } catch (Exception e2) {
            zzds.zza(e2);
            L.e(e2, "Failed to log", new Object[0]);
        }
    }
}
