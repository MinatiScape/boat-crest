package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public final class zada<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    public final WeakReference g;
    public final q0 h;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public ResultTransform f8308a = null;
    @Nullable
    public zada b = null;
    @Nullable
    public volatile ResultCallbacks c = null;
    @Nullable
    public PendingResult d = null;
    public final Object e = new Object();
    @Nullable
    public Status f = null;
    public boolean i = false;

    public zada(WeakReference weakReference) {
        Preconditions.checkNotNull(weakReference, "GoogleApiClient reference must not be null");
        this.g = weakReference;
        GoogleApiClient googleApiClient = (GoogleApiClient) weakReference.get();
        this.h = new q0(this, googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
    }

    public static final void m(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                Log.w("TransformedResultImpl", "Unable to release ".concat(String.valueOf(result)), e);
            }
        }
    }

    @Override // com.google.android.gms.common.api.TransformedResult
    public final void andFinally(@NonNull ResultCallbacks<? super R> resultCallbacks) {
        synchronized (this.e) {
            boolean z = true;
            Preconditions.checkState(this.c == null, "Cannot call andFinally() twice.");
            if (this.f8308a != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.c = resultCallbacks;
            j();
        }
    }

    public final void h() {
        this.c = null;
    }

    public final void i(Status status) {
        synchronized (this.e) {
            this.f = status;
            k(status);
        }
    }

    @GuardedBy("mSyncToken")
    public final void j() {
        if (this.f8308a == null && this.c == null) {
            return;
        }
        GoogleApiClient googleApiClient = (GoogleApiClient) this.g.get();
        if (!this.i && this.f8308a != null && googleApiClient != null) {
            googleApiClient.zao(this);
            this.i = true;
        }
        Status status = this.f;
        if (status != null) {
            k(status);
            return;
        }
        PendingResult pendingResult = this.d;
        if (pendingResult != null) {
            pendingResult.setResultCallback(this);
        }
    }

    public final void k(Status status) {
        synchronized (this.e) {
            ResultTransform resultTransform = this.f8308a;
            if (resultTransform != null) {
                ((zada) Preconditions.checkNotNull(this.b)).i((Status) Preconditions.checkNotNull(resultTransform.onFailure(status), "onFailure must not return null"));
            } else if (l()) {
                ((ResultCallbacks) Preconditions.checkNotNull(this.c)).onFailure(status);
            }
        }
    }

    @GuardedBy("mSyncToken")
    public final boolean l() {
        return (this.c == null || ((GoogleApiClient) this.g.get()) == null) ? false : true;
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final void onResult(Result result) {
        synchronized (this.e) {
            if (!result.getStatus().isSuccess()) {
                i(result.getStatus());
                m(result);
            } else if (this.f8308a != null) {
                zaco.zaa().submit(new p0(this, result));
            } else if (l()) {
                ((ResultCallbacks) Preconditions.checkNotNull(this.c)).onSuccess(result);
            }
        }
    }

    @Override // com.google.android.gms.common.api.TransformedResult
    @NonNull
    public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        zada zadaVar;
        synchronized (this.e) {
            boolean z = true;
            Preconditions.checkState(this.f8308a == null, "Cannot call then() twice.");
            if (this.c != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.f8308a = resultTransform;
            zadaVar = new zada(this.g);
            this.b = zadaVar;
            j();
        }
        return zadaVar;
    }

    public final void zai(PendingResult pendingResult) {
        synchronized (this.e) {
            this.d = pendingResult;
            j();
        }
    }
}
