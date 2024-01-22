package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
/* loaded from: classes6.dex */
public final class zbn {
    @Nullable
    public static zbn d;
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final Storage f8215a;
    @Nullable
    @VisibleForTesting
    public GoogleSignInAccount b;
    @Nullable
    @VisibleForTesting
    public GoogleSignInOptions c;

    public zbn(Context context) {
        Storage storage = Storage.getInstance(context);
        this.f8215a = storage;
        this.b = storage.getSavedDefaultGoogleSignInAccount();
        this.c = storage.getSavedDefaultGoogleSignInOptions();
    }

    public static synchronized zbn a(Context context) {
        synchronized (zbn.class) {
            zbn zbnVar = d;
            if (zbnVar != null) {
                return zbnVar;
            }
            zbn zbnVar2 = new zbn(context);
            d = zbnVar2;
            return zbnVar2;
        }
    }

    public static synchronized zbn zbc(@NonNull Context context) {
        zbn a2;
        synchronized (zbn.class) {
            a2 = a(context.getApplicationContext());
        }
        return a2;
    }

    @Nullable
    public final synchronized GoogleSignInAccount zba() {
        return this.b;
    }

    @Nullable
    public final synchronized GoogleSignInOptions zbb() {
        return this.c;
    }

    public final synchronized void zbd() {
        this.f8215a.clear();
        this.b = null;
        this.c = null;
    }

    public final synchronized void zbe(GoogleSignInOptions googleSignInOptions, GoogleSignInAccount googleSignInAccount) {
        this.f8215a.saveDefaultGoogleSignInAccount(googleSignInAccount, googleSignInOptions);
        this.b = googleSignInAccount;
        this.c = googleSignInOptions;
    }
}
