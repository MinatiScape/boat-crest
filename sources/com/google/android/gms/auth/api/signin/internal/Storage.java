package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
@KeepForSdk
/* loaded from: classes6.dex */
public class Storage {
    public static final Lock c = new ReentrantLock();
    @Nullable
    @GuardedBy("sLk")
    public static Storage d;

    /* renamed from: a  reason: collision with root package name */
    public final Lock f8209a = new ReentrantLock();
    @GuardedBy("mLk")
    public final SharedPreferences b;

    @VisibleForTesting
    public Storage(Context context) {
        this.b = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static final String a(String str, String str2) {
        return str + ":" + str2;
    }

    @NonNull
    @KeepForSdk
    public static Storage getInstance(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        Lock lock = c;
        lock.lock();
        try {
            if (d == null) {
                d = new Storage(context.getApplicationContext());
            }
            Storage storage = d;
            lock.unlock();
            return storage;
        } catch (Throwable th) {
            c.unlock();
            throw th;
        }
    }

    @KeepForSdk
    public void clear() {
        this.f8209a.lock();
        try {
            this.b.edit().clear().apply();
        } finally {
            this.f8209a.unlock();
        }
    }

    @Nullable
    @KeepForSdk
    public GoogleSignInAccount getSavedDefaultGoogleSignInAccount() {
        String zaa;
        String zaa2 = zaa("defaultGoogleSignInAccount");
        if (TextUtils.isEmpty(zaa2) || (zaa = zaa(a("googleSignInAccount", zaa2))) == null) {
            return null;
        }
        try {
            return GoogleSignInAccount.zab(zaa);
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    @KeepForSdk
    public GoogleSignInOptions getSavedDefaultGoogleSignInOptions() {
        String zaa;
        String zaa2 = zaa("defaultGoogleSignInAccount");
        if (TextUtils.isEmpty(zaa2) || (zaa = zaa(a("googleSignInOptions", zaa2))) == null) {
            return null;
        }
        try {
            return GoogleSignInOptions.zab(zaa);
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    @KeepForSdk
    public String getSavedRefreshToken() {
        return zaa("refreshToken");
    }

    @KeepForSdk
    public void saveDefaultGoogleSignInAccount(@NonNull GoogleSignInAccount googleSignInAccount, @NonNull GoogleSignInOptions googleSignInOptions) {
        Preconditions.checkNotNull(googleSignInAccount);
        Preconditions.checkNotNull(googleSignInOptions);
        zad("defaultGoogleSignInAccount", googleSignInAccount.zac());
        Preconditions.checkNotNull(googleSignInAccount);
        Preconditions.checkNotNull(googleSignInOptions);
        String zac = googleSignInAccount.zac();
        zad(a("googleSignInAccount", zac), googleSignInAccount.zad());
        zad(a("googleSignInOptions", zac), googleSignInOptions.zaf());
    }

    @Nullable
    public final String zaa(@NonNull String str) {
        this.f8209a.lock();
        try {
            return this.b.getString(str, null);
        } finally {
            this.f8209a.unlock();
        }
    }

    public final void zab(@NonNull String str) {
        this.f8209a.lock();
        try {
            this.b.edit().remove(str).apply();
        } finally {
            this.f8209a.unlock();
        }
    }

    public final void zac() {
        String zaa = zaa("defaultGoogleSignInAccount");
        zab("defaultGoogleSignInAccount");
        if (TextUtils.isEmpty(zaa)) {
            return;
        }
        zab(a("googleSignInAccount", zaa));
        zab(a("googleSignInOptions", zaa));
    }

    public final void zad(@NonNull String str, @NonNull String str2) {
        this.f8209a.lock();
        try {
            this.b.edit().putString(str, str2).apply();
        } finally {
            this.f8209a.unlock();
        }
    }
}
