package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.Binder;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.util.UidVerifier;
/* loaded from: classes6.dex */
public final class zbt extends zbo {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8216a;

    public zbt(Context context) {
        this.f8216a = context;
    }

    public final void t() {
        if (UidVerifier.isGooglePlayServicesUid(this.f8216a, Binder.getCallingUid())) {
            return;
        }
        int callingUid = Binder.getCallingUid();
        throw new SecurityException("Calling UID " + callingUid + " is not Google Play services.");
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zbp
    public final void zbb() {
        t();
        zbn.zbc(this.f8216a).zbd();
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zbp
    public final void zbc() {
        t();
        Storage storage = Storage.getInstance(this.f8216a);
        GoogleSignInAccount savedDefaultGoogleSignInAccount = storage.getSavedDefaultGoogleSignInAccount();
        GoogleSignInOptions googleSignInOptions = GoogleSignInOptions.DEFAULT_SIGN_IN;
        if (savedDefaultGoogleSignInAccount != null) {
            googleSignInOptions = storage.getSavedDefaultGoogleSignInOptions();
        }
        GoogleSignInClient client = GoogleSignIn.getClient(this.f8216a, googleSignInOptions);
        if (savedDefaultGoogleSignInAccount != null) {
            client.revokeAccess();
        } else {
            client.signOut();
        }
    }
}
