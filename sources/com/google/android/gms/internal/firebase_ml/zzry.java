package com.google.android.gms.internal.firebase_ml;

import android.os.SystemClock;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
/* loaded from: classes7.dex */
public final class zzry {
    public static final GmsLogger c = new GmsLogger("StreamingFormatChecker", "");

    /* renamed from: a  reason: collision with root package name */
    public final LinkedList<Long> f8802a = new LinkedList<>();
    public long b = -1;

    public final void zzb(zzsf zzsfVar) {
        if (zzsfVar.zzbrv.getBitmap() == null) {
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.f8802a.add(Long.valueOf(elapsedRealtime));
        if (this.f8802a.size() > 5) {
            this.f8802a.removeFirst();
        }
        if (this.f8802a.size() != 5 || elapsedRealtime - this.f8802a.peekFirst().longValue() >= 5000) {
            return;
        }
        long j = this.b;
        if (j == -1 || elapsedRealtime - j >= TimeUnit.SECONDS.toMillis(5L)) {
            this.b = elapsedRealtime;
            c.w("StreamingFormatChecker", "ML Kit has detected that you seem to pass camera frames to the detector as a Bitmap object. This is inefficient. Please use YUV_420_888 format for camera2 API or NV21 format for (legacy) camera API and directly pass down the byte array to ML Kit.");
        }
    }
}
