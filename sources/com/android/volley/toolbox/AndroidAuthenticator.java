package com.android.volley.toolbox;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import com.android.volley.AuthFailureError;
@SuppressLint({"MissingPermission"})
/* loaded from: classes.dex */
public class AndroidAuthenticator implements Authenticator {

    /* renamed from: a  reason: collision with root package name */
    public final AccountManager f2152a;
    public final Account b;
    public final String c;
    public final boolean d;

    public AndroidAuthenticator(Context context, Account account, String str) {
        this(context, account, str, false);
    }

    public Account getAccount() {
        return this.b;
    }

    @Override // com.android.volley.toolbox.Authenticator
    public String getAuthToken() throws AuthFailureError {
        AccountManagerFuture<Bundle> authToken = this.f2152a.getAuthToken(this.b, this.c, this.d, null, null);
        try {
            Bundle result = authToken.getResult();
            String str = null;
            if (authToken.isDone() && !authToken.isCancelled()) {
                if (!result.containsKey("intent")) {
                    str = result.getString("authtoken");
                } else {
                    throw new AuthFailureError((Intent) result.getParcelable("intent"));
                }
            }
            if (str != null) {
                return str;
            }
            throw new AuthFailureError("Got null auth token for type: " + this.c);
        } catch (Exception e) {
            throw new AuthFailureError("Error while retrieving auth token", e);
        }
    }

    public String getAuthTokenType() {
        return this.c;
    }

    @Override // com.android.volley.toolbox.Authenticator
    public void invalidateAuthToken(String str) {
        this.f2152a.invalidateAuthToken(this.b.type, str);
    }

    public AndroidAuthenticator(Context context, Account account, String str, boolean z) {
        this(AccountManager.get(context), account, str, z);
    }

    @VisibleForTesting
    public AndroidAuthenticator(AccountManager accountManager, Account account, String str, boolean z) {
        this.f2152a = accountManager;
        this.b = account;
        this.c = str;
        this.d = z;
    }
}
