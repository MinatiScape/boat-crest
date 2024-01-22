package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public final class g0 implements BaseGmsClient.ConnectionProgressReportCallbacks, zacs {

    /* renamed from: a  reason: collision with root package name */
    public final Api.Client f8276a;
    public final ApiKey b;
    @Nullable
    public IAccountAccessor c = null;
    @Nullable
    public Set d = null;
    public boolean e = false;
    public final /* synthetic */ GoogleApiManager f;

    public g0(GoogleApiManager googleApiManager, Api.Client client, ApiKey apiKey) {
        this.f = googleApiManager;
        this.f8276a = client;
        this.b = apiKey;
    }

    @WorkerThread
    public final void e() {
        IAccountAccessor iAccountAccessor;
        if (!this.e || (iAccountAccessor = this.c) == null) {
            return;
        }
        this.f8276a.getRemoteService(iAccountAccessor, this.d);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
    public final void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
        Handler handler;
        handler = this.f.w;
        handler.post(new f0(this, connectionResult));
    }

    @Override // com.google.android.gms.common.api.internal.zacs
    @WorkerThread
    public final void zae(ConnectionResult connectionResult) {
        Map map;
        map = this.f.s;
        zabq zabqVar = (zabq) map.get(this.b);
        if (zabqVar != null) {
            zabqVar.zas(connectionResult);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zacs
    @WorkerThread
    public final void zaf(@Nullable IAccountAccessor iAccountAccessor, @Nullable Set set) {
        if (iAccountAccessor != null && set != null) {
            this.c = iAccountAccessor;
            this.d = set;
            e();
            return;
        }
        Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
        zae(new ConnectionResult(4));
    }
}
