package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.google.android.gms.common.api.GoogleApiClient;
/* loaded from: classes6.dex */
public final class h implements LoaderManager.LoaderCallbacks {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SignInHubActivity f8213a;

    public /* synthetic */ h(SignInHubActivity signInHubActivity, zbv zbvVar) {
        this.f8213a = signInHubActivity;
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public final Loader onCreateLoader(int i, Bundle bundle) {
        return new zbc(this.f8213a, GoogleApiClient.getAllClients());
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public final /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        Void r3 = (Void) obj;
        SignInHubActivity signInHubActivity = this.f8213a;
        signInHubActivity.setResult(SignInHubActivity.c(signInHubActivity), SignInHubActivity.d(signInHubActivity));
        this.f8213a.finish();
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public final void onLoaderReset(Loader loader) {
    }
}
