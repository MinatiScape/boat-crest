package com.google.android.gms.common.api.internal;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.RootTelemetryConfigManager;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
/* loaded from: classes6.dex */
public final class h0 implements OnCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final GoogleApiManager f8277a;
    public final int b;
    public final ApiKey c;
    public final long d;
    public final long e;

    @VisibleForTesting
    public h0(GoogleApiManager googleApiManager, int i, ApiKey apiKey, long j, long j2, @Nullable String str, @Nullable String str2) {
        this.f8277a = googleApiManager;
        this.b = i;
        this.c = apiKey;
        this.d = j;
        this.e = j2;
    }

    @Nullable
    public static h0 a(GoogleApiManager googleApiManager, int i, ApiKey apiKey) {
        boolean z;
        if (googleApiManager.c()) {
            RootTelemetryConfiguration config = RootTelemetryConfigManager.getInstance().getConfig();
            if (config == null) {
                z = true;
            } else if (!config.getMethodInvocationTelemetryEnabled()) {
                return null;
            } else {
                z = config.getMethodTimingTelemetryEnabled();
                zabq s = googleApiManager.s(apiKey);
                if (s != null) {
                    if (!(s.zaf() instanceof BaseGmsClient)) {
                        return null;
                    }
                    BaseGmsClient baseGmsClient = (BaseGmsClient) s.zaf();
                    if (baseGmsClient.hasConnectionInfo() && !baseGmsClient.isConnecting()) {
                        ConnectionTelemetryConfiguration b = b(s, baseGmsClient, i);
                        if (b == null) {
                            return null;
                        }
                        s.v();
                        z = b.getMethodTimingTelemetryEnabled();
                    }
                }
            }
            return new h0(googleApiManager, i, apiKey, z ? System.currentTimeMillis() : 0L, z ? SystemClock.elapsedRealtime() : 0L, null, null);
        }
        return null;
    }

    @Nullable
    public static ConnectionTelemetryConfiguration b(zabq zabqVar, BaseGmsClient baseGmsClient, int i) {
        int[] methodInvocationMethodKeyAllowlist;
        int[] methodInvocationMethodKeyDisallowlist;
        ConnectionTelemetryConfiguration telemetryConfiguration = baseGmsClient.getTelemetryConfiguration();
        if (telemetryConfiguration == null || !telemetryConfiguration.getMethodInvocationTelemetryEnabled() || ((methodInvocationMethodKeyAllowlist = telemetryConfiguration.getMethodInvocationMethodKeyAllowlist()) != null ? !ArrayUtils.contains(methodInvocationMethodKeyAllowlist, i) : !((methodInvocationMethodKeyDisallowlist = telemetryConfiguration.getMethodInvocationMethodKeyDisallowlist()) == null || !ArrayUtils.contains(methodInvocationMethodKeyDisallowlist, i))) || zabqVar.n() >= telemetryConfiguration.getMaxMethodInvocationsLogged()) {
            return null;
        }
        return telemetryConfiguration;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    @WorkerThread
    public final void onComplete(@NonNull Task task) {
        zabq s;
        int i;
        int i2;
        int i3;
        int errorCode;
        long j;
        long j2;
        int i4;
        if (this.f8277a.c()) {
            RootTelemetryConfiguration config = RootTelemetryConfigManager.getInstance().getConfig();
            if ((config == null || config.getMethodInvocationTelemetryEnabled()) && (s = this.f8277a.s(this.c)) != null && (s.zaf() instanceof BaseGmsClient)) {
                BaseGmsClient baseGmsClient = (BaseGmsClient) s.zaf();
                boolean z = true;
                int i5 = 0;
                boolean z2 = this.d > 0;
                int gCoreServiceId = baseGmsClient.getGCoreServiceId();
                if (config != null) {
                    z2 &= config.getMethodTimingTelemetryEnabled();
                    int batchPeriodMillis = config.getBatchPeriodMillis();
                    int maxMethodInvocationsInBatch = config.getMaxMethodInvocationsInBatch();
                    i = config.getVersion();
                    if (baseGmsClient.hasConnectionInfo() && !baseGmsClient.isConnecting()) {
                        ConnectionTelemetryConfiguration b = b(s, baseGmsClient, this.b);
                        if (b == null) {
                            return;
                        }
                        if (!b.getMethodTimingTelemetryEnabled() || this.d <= 0) {
                            z = false;
                        }
                        maxMethodInvocationsInBatch = b.getMaxMethodInvocationsLogged();
                        z2 = z;
                    }
                    i3 = batchPeriodMillis;
                    i2 = maxMethodInvocationsInBatch;
                } else {
                    i = 0;
                    i2 = 100;
                    i3 = 5000;
                }
                GoogleApiManager googleApiManager = this.f8277a;
                if (task.isSuccessful()) {
                    errorCode = 0;
                } else {
                    if (task.isCanceled()) {
                        i5 = 100;
                    } else {
                        Exception exception = task.getException();
                        if (exception instanceof ApiException) {
                            Status status = ((ApiException) exception).getStatus();
                            int statusCode = status.getStatusCode();
                            ConnectionResult connectionResult = status.getConnectionResult();
                            errorCode = connectionResult == null ? -1 : connectionResult.getErrorCode();
                            i5 = statusCode;
                        } else {
                            i5 = 101;
                        }
                    }
                    errorCode = -1;
                }
                if (z2) {
                    long j3 = this.d;
                    long currentTimeMillis = System.currentTimeMillis();
                    i4 = (int) (SystemClock.elapsedRealtime() - this.e);
                    j = j3;
                    j2 = currentTimeMillis;
                } else {
                    j = 0;
                    j2 = 0;
                    i4 = -1;
                }
                googleApiManager.y(new MethodInvocation(this.b, i5, errorCode, j, j2, null, null, gCoreServiceId, i4), i, i3, i2);
            }
        }
    }
}
