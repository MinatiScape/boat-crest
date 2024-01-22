package com.google.mlkit.vision.common.internal;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.vision.common.InputImage;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
@KeepForSdk
/* loaded from: classes10.dex */
public class BitmapInStreamingChecker {
    public static final GmsLogger c = new GmsLogger("StreamingFormatChecker", "");

    /* renamed from: a  reason: collision with root package name */
    public final LinkedList f11637a = new LinkedList();
    public long b = -1;

    @KeepForSdk
    public void check(@NonNull InputImage inputImage) {
        if (inputImage.getFormat() != -1) {
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.f11637a.add(Long.valueOf(elapsedRealtime));
        if (this.f11637a.size() > 5) {
            this.f11637a.removeFirst();
        }
        if (this.f11637a.size() != 5 || elapsedRealtime - ((Long) Preconditions.checkNotNull((Long) this.f11637a.peekFirst())).longValue() >= 5000) {
            return;
        }
        long j = this.b;
        if (j == -1 || elapsedRealtime - j >= TimeUnit.SECONDS.toMillis(5L)) {
            this.b = elapsedRealtime;
            c.w("StreamingFormatChecker", "ML Kit has detected that you seem to pass camera frames to the detector as a Bitmap object. This is inefficient. Please use YUV_420_888 format for camera2 API or NV21 format for (legacy) camera API and directly pass down the byte array to ML Kit.");
        }
    }
}
