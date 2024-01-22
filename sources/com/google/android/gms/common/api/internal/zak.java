package com.google.android.gms.common.api.internal;

import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
/* loaded from: classes6.dex */
public final class zak extends zap {
    public final SparseArray i;

    public zak(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment, GoogleApiAvailability.getInstance());
        this.i = new SparseArray();
        this.mLifecycleFragment.addCallback("AutoManageHelper", this);
    }

    public static zak zaa(LifecycleActivity lifecycleActivity) {
        LifecycleFragment fragment = LifecycleCallback.getFragment(lifecycleActivity);
        zak zakVar = (zak) fragment.getCallbackOrNull("AutoManageHelper", zak.class);
        return zakVar != null ? zakVar : new zak(fragment);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < this.i.size(); i++) {
            t0 f = f(i);
            if (f != null) {
                printWriter.append((CharSequence) str).append("GoogleApiClient #").print(f.f8286a);
                printWriter.println(":");
                f.b.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
            }
        }
    }

    @Nullable
    public final t0 f(int i) {
        if (this.i.size() <= i) {
            return null;
        }
        SparseArray sparseArray = this.i;
        return (t0) sparseArray.get(sparseArray.keyAt(i));
    }

    @Override // com.google.android.gms.common.api.internal.zap, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStart() {
        super.onStart();
        boolean z = this.zaa;
        String valueOf = String.valueOf(this.i);
        Log.d("AutoManageHelper", "onStart " + z + HexStringBuilder.DEFAULT_SEPARATOR + valueOf);
        if (this.zab.get() == null) {
            for (int i = 0; i < this.i.size(); i++) {
                t0 f = f(i);
                if (f != null) {
                    f.b.connect();
                }
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zap, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStop() {
        super.onStop();
        for (int i = 0; i < this.i.size(); i++) {
            t0 f = f(i);
            if (f != null) {
                f.b.disconnect();
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zap
    public final void zab(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        t0 t0Var = (t0) this.i.get(i);
        if (t0Var != null) {
            zae(i);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = t0Var.c;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zap
    public final void zac() {
        for (int i = 0; i < this.i.size(); i++) {
            t0 f = f(i);
            if (f != null) {
                f.b.connect();
            }
        }
    }

    public final void zad(int i, GoogleApiClient googleApiClient, @Nullable GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(googleApiClient, "GoogleApiClient instance cannot be null");
        boolean z = this.i.indexOfKey(i) < 0;
        Preconditions.checkState(z, "Already managing a GoogleApiClient with id " + i);
        u0 u0Var = (u0) this.zab.get();
        boolean z2 = this.zaa;
        String valueOf = String.valueOf(u0Var);
        Log.d("AutoManageHelper", "starting AutoManage for client " + i + HexStringBuilder.DEFAULT_SEPARATOR + z2 + HexStringBuilder.DEFAULT_SEPARATOR + valueOf);
        t0 t0Var = new t0(this, i, googleApiClient, onConnectionFailedListener);
        googleApiClient.registerConnectionFailedListener(t0Var);
        this.i.put(i, t0Var);
        if (this.zaa && u0Var == null) {
            Log.d("AutoManageHelper", "connecting ".concat(googleApiClient.toString()));
            googleApiClient.connect();
        }
    }

    public final void zae(int i) {
        t0 t0Var = (t0) this.i.get(i);
        this.i.remove(i);
        if (t0Var != null) {
            t0Var.b.unregisterConnectionFailedListener(t0Var);
            t0Var.b.disconnect();
        }
    }
}
