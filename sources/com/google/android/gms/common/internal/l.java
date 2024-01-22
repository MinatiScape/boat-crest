package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
/* loaded from: classes6.dex */
public final class l extends com.google.android.gms.internal.common.zzi {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BaseGmsClient f8337a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(BaseGmsClient baseGmsClient, Looper looper) {
        super(looper);
        this.f8337a = baseGmsClient;
    }

    public static final void a(Message message) {
        zzc zzcVar = (zzc) message.obj;
        zzcVar.zzc();
        zzcVar.zzg();
    }

    public static final boolean b(Message message) {
        int i = message.what;
        return i == 2 || i == 1 || i == 7;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks;
        BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks2;
        ConnectionResult connectionResult;
        ConnectionResult connectionResult2;
        boolean z;
        if (this.f8337a.zzd.get() != message.arg1) {
            if (b(message)) {
                a(message);
                return;
            }
            return;
        }
        int i = message.what;
        if ((i != 1 && i != 7 && ((i != 4 || this.f8337a.enableLocalFallback()) && message.what != 5)) || this.f8337a.isConnecting()) {
            int i2 = message.what;
            if (i2 == 4) {
                this.f8337a.F = new ConnectionResult(message.arg2);
                if (BaseGmsClient.m(this.f8337a)) {
                    BaseGmsClient baseGmsClient = this.f8337a;
                    z = baseGmsClient.G;
                    if (!z) {
                        baseGmsClient.n(3, null);
                        return;
                    }
                }
                BaseGmsClient baseGmsClient2 = this.f8337a;
                connectionResult2 = baseGmsClient2.F;
                ConnectionResult connectionResult3 = connectionResult2 != null ? baseGmsClient2.F : new ConnectionResult(8);
                this.f8337a.zzc.onReportServiceBinding(connectionResult3);
                this.f8337a.onConnectionFailed(connectionResult3);
                return;
            } else if (i2 == 5) {
                BaseGmsClient baseGmsClient3 = this.f8337a;
                connectionResult = baseGmsClient3.F;
                ConnectionResult connectionResult4 = connectionResult != null ? baseGmsClient3.F : new ConnectionResult(8);
                this.f8337a.zzc.onReportServiceBinding(connectionResult4);
                this.f8337a.onConnectionFailed(connectionResult4);
                return;
            } else if (i2 == 3) {
                Object obj = message.obj;
                ConnectionResult connectionResult5 = new ConnectionResult(message.arg2, obj instanceof PendingIntent ? (PendingIntent) obj : null);
                this.f8337a.zzc.onReportServiceBinding(connectionResult5);
                this.f8337a.onConnectionFailed(connectionResult5);
                return;
            } else if (i2 == 6) {
                this.f8337a.n(5, null);
                BaseGmsClient baseGmsClient4 = this.f8337a;
                baseConnectionCallbacks = baseGmsClient4.A;
                if (baseConnectionCallbacks != null) {
                    baseConnectionCallbacks2 = baseGmsClient4.A;
                    baseConnectionCallbacks2.onConnectionSuspended(message.arg2);
                }
                this.f8337a.onConnectionSuspended(message.arg2);
                BaseGmsClient.l(this.f8337a, 5, 1, null);
                return;
            } else if (i2 == 2 && !this.f8337a.isConnected()) {
                a(message);
                return;
            } else if (b(message)) {
                ((zzc) message.obj).zze();
                return;
            } else {
                int i3 = message.what;
                Log.wtf("GmsClient", "Don't know how to handle message: " + i3, new Exception());
                return;
            }
        }
        a(message);
    }
}
