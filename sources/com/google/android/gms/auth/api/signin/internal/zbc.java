package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.util.Log;
import androidx.loader.content.AsyncTaskLoader;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
/* loaded from: classes6.dex */
public final class zbc extends AsyncTaskLoader implements SignInConnectionListener {
    public final Semaphore p;
    public final Set q;

    public zbc(Context context, Set set) {
        super(context);
        this.p = new Semaphore(0);
        this.q = set;
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public final /* bridge */ /* synthetic */ Object loadInBackground() {
        int i = 0;
        for (GoogleApiClient googleApiClient : this.q) {
            if (googleApiClient.maybeSignIn(this)) {
                i++;
            }
        }
        try {
            this.p.tryAcquire(i, 5L, TimeUnit.SECONDS);
            return null;
        } catch (InterruptedException e) {
            Log.i("GACSignInLoader", "Unexpected InterruptedException", e);
            Thread.currentThread().interrupt();
            return null;
        }
    }

    @Override // com.google.android.gms.common.api.internal.SignInConnectionListener
    public final void onComplete() {
        this.p.release();
    }

    @Override // androidx.loader.content.Loader
    public final void onStartLoading() {
        this.p.drainPermits();
        forceLoad();
    }
}
