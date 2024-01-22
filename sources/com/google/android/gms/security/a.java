package com.google.android.gms.security;

import android.content.Context;
import android.os.AsyncTask;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
/* loaded from: classes10.dex */
public final class a extends AsyncTask {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f10171a;
    public final /* synthetic */ ProviderInstaller.ProviderInstallListener b;

    public a(Context context, ProviderInstaller.ProviderInstallListener providerInstallListener) {
        this.f10171a = context;
        this.b = providerInstallListener;
    }

    @Override // android.os.AsyncTask
    public final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        try {
            ProviderInstaller.installIfNeeded(this.f10171a);
            return 0;
        } catch (GooglePlayServicesNotAvailableException e) {
            return Integer.valueOf(e.errorCode);
        } catch (GooglePlayServicesRepairableException e2) {
            return Integer.valueOf(e2.getConnectionStatusCode());
        }
    }

    @Override // android.os.AsyncTask
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        GoogleApiAvailabilityLight googleApiAvailabilityLight;
        Integer num = (Integer) obj;
        if (num.intValue() == 0) {
            this.b.onProviderInstalled();
            return;
        }
        googleApiAvailabilityLight = ProviderInstaller.f10170a;
        this.b.onProviderInstallFailed(num.intValue(), googleApiAvailabilityLight.getErrorResolutionIntent(this.f10171a, num.intValue(), "pi"));
    }
}
