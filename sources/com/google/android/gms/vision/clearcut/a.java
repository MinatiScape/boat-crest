package com.google.android.gms.vision.clearcut;

import com.google.android.gms.internal.vision.zzea;
/* loaded from: classes10.dex */
public final class a implements Runnable {
    public final /* synthetic */ int h;
    public final /* synthetic */ zzea.zzo i;
    public final /* synthetic */ DynamiteClearcutLogger j;

    public a(DynamiteClearcutLogger dynamiteClearcutLogger, int i, zzea.zzo zzoVar) {
        this.j = dynamiteClearcutLogger;
        this.h = i;
        this.i = zzoVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VisionClearcutLogger visionClearcutLogger;
        visionClearcutLogger = this.j.zzbq;
        visionClearcutLogger.zzb(this.h, this.i);
    }
}
