package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.collection.ArraySet;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
@VisibleForTesting
@KeepForSdk
/* loaded from: classes6.dex */
public final class ClientSettings {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Account f8324a;
    public final Set b;
    public final Set c;
    public final Map d;
    public final int e;
    @Nullable
    public final View f;
    public final String g;
    public final String h;
    public final SignInOptions i;
    public Integer j;

    @KeepForSdk
    /* loaded from: classes6.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public Account f8325a;
        public ArraySet b;
        public String c;
        public String d;
        public SignInOptions e = SignInOptions.zaa;

        @NonNull
        @KeepForSdk
        public ClientSettings build() {
            return new ClientSettings(this.f8325a, this.b, null, 0, null, this.c, this.d, this.e, false);
        }

        @NonNull
        @CanIgnoreReturnValue
        @KeepForSdk
        public Builder setRealClientPackageName(@NonNull String str) {
            this.c = str;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public final Builder zaa(@NonNull Collection collection) {
            if (this.b == null) {
                this.b = new ArraySet();
            }
            this.b.addAll(collection);
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public final Builder zab(@Nullable Account account) {
            this.f8325a = account;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public final Builder zac(@NonNull String str) {
            this.d = str;
            return this;
        }
    }

    @KeepForSdk
    public ClientSettings(@NonNull Account account, @NonNull Set<Scope> set, @NonNull Map<Api<?>, zab> map, int i, @Nullable View view, @NonNull String str, @NonNull String str2, @Nullable SignInOptions signInOptions) {
        this(account, set, map, i, view, str, str2, signInOptions, false);
    }

    @NonNull
    @KeepForSdk
    public static ClientSettings createDefault(@NonNull Context context) {
        return new GoogleApiClient.Builder(context).zaa();
    }

    @androidx.annotation.Nullable
    @KeepForSdk
    public Account getAccount() {
        return this.f8324a;
    }

    @androidx.annotation.Nullable
    @KeepForSdk
    @Deprecated
    public String getAccountName() {
        Account account = this.f8324a;
        if (account != null) {
            return account.name;
        }
        return null;
    }

    @NonNull
    @KeepForSdk
    public Account getAccountOrDefault() {
        Account account = this.f8324a;
        return account != null ? account : new Account("<<default account>>", "com.google");
    }

    @NonNull
    @KeepForSdk
    public Set<Scope> getAllRequestedScopes() {
        return this.c;
    }

    @NonNull
    @KeepForSdk
    public Set<Scope> getApplicableScopes(@NonNull Api<?> api) {
        zab zabVar = (zab) this.d.get(api);
        if (zabVar != null && !zabVar.zaa.isEmpty()) {
            HashSet hashSet = new HashSet(this.b);
            hashSet.addAll(zabVar.zaa);
            return hashSet;
        }
        return this.b;
    }

    @KeepForSdk
    public int getGravityForPopups() {
        return this.e;
    }

    @NonNull
    @KeepForSdk
    public String getRealClientPackageName() {
        return this.g;
    }

    @NonNull
    @KeepForSdk
    public Set<Scope> getRequiredScopes() {
        return this.b;
    }

    @androidx.annotation.Nullable
    @KeepForSdk
    public View getViewForPopups() {
        return this.f;
    }

    @NonNull
    public final SignInOptions zaa() {
        return this.i;
    }

    @androidx.annotation.Nullable
    public final Integer zab() {
        return this.j;
    }

    @androidx.annotation.Nullable
    public final String zac() {
        return this.h;
    }

    @NonNull
    public final Map zad() {
        return this.d;
    }

    public final void zae(@NonNull Integer num) {
        this.j = num;
    }

    public ClientSettings(@Nullable Account account, @NonNull Set set, @NonNull Map map, int i, @Nullable View view, @NonNull String str, @NonNull String str2, @Nullable SignInOptions signInOptions, boolean z) {
        this.f8324a = account;
        Set emptySet = set == null ? Collections.emptySet() : Collections.unmodifiableSet(set);
        this.b = emptySet;
        map = map == null ? Collections.emptyMap() : map;
        this.d = map;
        this.f = view;
        this.e = i;
        this.g = str;
        this.h = str2;
        this.i = signInOptions == null ? SignInOptions.zaa : signInOptions;
        HashSet hashSet = new HashSet(emptySet);
        for (zab zabVar : map.values()) {
            hashSet.addAll(zabVar.zaa);
        }
        this.c = Collections.unmodifiableSet(hashSet);
    }
}
