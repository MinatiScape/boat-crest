package com.google.android.gms.internal.mlkit_vision_text_common;

import android.content.Context;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLogging;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import com.google.android.gms.tasks.OnFailureListener;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes6.dex */
public final class zzoi {

    /* renamed from: a  reason: collision with root package name */
    public final TelemetryLoggingClient f9952a;
    public final AtomicLong b = new AtomicLong(-1);

    @VisibleForTesting
    public zzoi(Context context, String str) {
        this.f9952a = TelemetryLogging.getClient(context, TelemetryLoggingOptions.builder().setApi("mlkit:vision").build());
    }

    public static zzoi zza(Context context) {
        return new zzoi(context, "mlkit:vision");
    }

    public final /* synthetic */ void a(long j, Exception exc) {
        this.b.set(j);
    }

    public final synchronized void zzc(int i, int i2, long j, long j2) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.b.get() != -1 && elapsedRealtime - this.b.get() <= TimeUnit.MINUTES.toMillis(30L)) {
            return;
        }
        this.f9952a.log(new TelemetryData(0, Arrays.asList(new MethodInvocation(i, i2, 0, j, j2, null, null, 0)))).addOnFailureListener(new OnFailureListener() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzoh
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                zzoi.this.a(elapsedRealtime, exc);
            }
        });
    }
}
