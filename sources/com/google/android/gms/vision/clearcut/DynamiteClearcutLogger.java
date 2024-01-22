package com.google.android.gms.vision.clearcut;

import android.content.Context;
import androidx.annotation.Keep;
import com.google.android.gms.internal.vision.zzea;
import com.google.android.gms.vision.L;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
@Keep
/* loaded from: classes10.dex */
public class DynamiteClearcutLogger {
    private static final ThreadPoolExecutor zzbo = new ThreadPoolExecutor(1, 2, 2, TimeUnit.SECONDS, new LinkedBlockingQueue(10), new ThreadPoolExecutor.DiscardPolicy());
    private zzb zzbp = new zzb(0.03333333333333333d);
    private VisionClearcutLogger zzbq;

    public DynamiteClearcutLogger(Context context) {
        this.zzbq = new VisionClearcutLogger(context);
    }

    public final void zza(int i, zzea.zzo zzoVar) {
        if (i == 3 && !this.zzbp.tryAcquire()) {
            L.v("Skipping image analysis log due to rate limiting", new Object[0]);
        } else {
            zzbo.execute(new a(this, i, zzoVar));
        }
    }
}
