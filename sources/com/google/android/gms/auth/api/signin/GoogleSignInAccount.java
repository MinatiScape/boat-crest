package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArraySet;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@SafeParcelable.Class(creator = "GoogleSignInAccountCreator")
/* loaded from: classes6.dex */
public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new zab();
    @NonNull
    @VisibleForTesting
    public static Clock zaa = DefaultClock.getInstance();
    @SafeParcelable.VersionField(id = 1)
    public final int h;
    @Nullable
    @SafeParcelable.Field(getter = "getId", id = 2)
    public String i;
    @Nullable
    @SafeParcelable.Field(getter = "getIdToken", id = 3)
    public String j;
    @Nullable
    @SafeParcelable.Field(getter = "getEmail", id = 4)
    public String k;
    @Nullable
    @SafeParcelable.Field(getter = "getDisplayName", id = 5)
    public String l;
    @Nullable
    @SafeParcelable.Field(getter = "getPhotoUrl", id = 6)
    public Uri m;
    @Nullable
    @SafeParcelable.Field(getter = "getServerAuthCode", id = 7)
    public String n;
    @SafeParcelable.Field(getter = "getExpirationTimeSecs", id = 8)
    public long o;
    @SafeParcelable.Field(getter = "getObfuscatedIdentifier", id = 9)
    public String p;
    @SafeParcelable.Field(id = 10)
    public List q;
    @Nullable
    @SafeParcelable.Field(getter = "getGivenName", id = 11)
    public String r;
    @Nullable
    @SafeParcelable.Field(getter = "getFamilyName", id = 12)
    public String s;
    public Set t = new HashSet();

    @SafeParcelable.Constructor
    public GoogleSignInAccount(@SafeParcelable.Param(id = 1) int i, @Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) String str2, @Nullable @SafeParcelable.Param(id = 4) String str3, @Nullable @SafeParcelable.Param(id = 5) String str4, @Nullable @SafeParcelable.Param(id = 6) Uri uri, @Nullable @SafeParcelable.Param(id = 7) String str5, @SafeParcelable.Param(id = 8) long j, @SafeParcelable.Param(id = 9) String str6, @SafeParcelable.Param(id = 10) List list, @Nullable @SafeParcelable.Param(id = 11) String str7, @Nullable @SafeParcelable.Param(id = 12) String str8) {
        this.h = i;
        this.i = str;
        this.j = str2;
        this.k = str3;
        this.l = str4;
        this.m = uri;
        this.n = str5;
        this.o = j;
        this.p = str6;
        this.q = list;
        this.r = str7;
        this.s = str8;
    }

    public static GoogleSignInAccount a(Account account, Set set) {
        return zaa(null, null, account.name, null, null, null, null, 0L, account.name, set);
    }

    @NonNull
    @KeepForSdk
    public static GoogleSignInAccount createDefault() {
        return a(new Account("<<default account>>", "com.google"), new HashSet());
    }

    @NonNull
    @KeepForSdk
    public static GoogleSignInAccount fromAccount(@NonNull Account account) {
        return a(account, new ArraySet());
    }

    @NonNull
    public static GoogleSignInAccount zaa(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Uri uri, @Nullable Long l, @NonNull String str7, @NonNull Set set) {
        return new GoogleSignInAccount(3, str, str2, str3, str4, uri, null, l.longValue(), Preconditions.checkNotEmpty(str7), new ArrayList((Collection) Preconditions.checkNotNull(set)), str5, str6);
    }

    @Nullable
    public static GoogleSignInAccount zab(@Nullable String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("photoUrl");
        Uri parse = !TextUtils.isEmpty(optString) ? Uri.parse(optString) : null;
        long parseLong = Long.parseLong(jSONObject.getString("expirationTime"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        GoogleSignInAccount zaa2 = zaa(jSONObject.optString("id"), jSONObject.has("tokenId") ? jSONObject.optString("tokenId") : null, jSONObject.has("email") ? jSONObject.optString("email") : null, jSONObject.has("displayName") ? jSONObject.optString("displayName") : null, jSONObject.has("givenName") ? jSONObject.optString("givenName") : null, jSONObject.has("familyName") ? jSONObject.optString("familyName") : null, parse, Long.valueOf(parseLong), jSONObject.getString("obfuscatedIdentifier"), hashSet);
        zaa2.n = jSONObject.has("serverAuthCode") ? jSONObject.optString("serverAuthCode") : null;
        return zaa2;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof GoogleSignInAccount) {
            GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
            return googleSignInAccount.p.equals(this.p) && googleSignInAccount.getRequestedScopes().equals(getRequestedScopes());
        }
        return false;
    }

    @Nullable
    public Account getAccount() {
        String str = this.k;
        if (str == null) {
            return null;
        }
        return new Account(str, "com.google");
    }

    @Nullable
    public String getDisplayName() {
        return this.l;
    }

    @Nullable
    public String getEmail() {
        return this.k;
    }

    @Nullable
    public String getFamilyName() {
        return this.s;
    }

    @Nullable
    public String getGivenName() {
        return this.r;
    }

    @NonNull
    public Set<Scope> getGrantedScopes() {
        return new HashSet(this.q);
    }

    @Nullable
    public String getId() {
        return this.i;
    }

    @Nullable
    public String getIdToken() {
        return this.j;
    }

    @Nullable
    public Uri getPhotoUrl() {
        return this.m;
    }

    @NonNull
    @KeepForSdk
    public Set<Scope> getRequestedScopes() {
        HashSet hashSet = new HashSet(this.q);
        hashSet.addAll(this.t);
        return hashSet;
    }

    @Nullable
    public String getServerAuthCode() {
        return this.n;
    }

    public int hashCode() {
        return ((this.p.hashCode() + 527) * 31) + getRequestedScopes().hashCode();
    }

    @KeepForSdk
    public boolean isExpired() {
        return zaa.currentTimeMillis() / 1000 >= this.o + (-300);
    }

    @NonNull
    @CanIgnoreReturnValue
    @KeepForSdk
    public GoogleSignInAccount requestExtraScopes(@NonNull Scope... scopeArr) {
        if (scopeArr != null) {
            Collections.addAll(this.t, scopeArr);
        }
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeString(parcel, 2, getId(), false);
        SafeParcelWriter.writeString(parcel, 3, getIdToken(), false);
        SafeParcelWriter.writeString(parcel, 4, getEmail(), false);
        SafeParcelWriter.writeString(parcel, 5, getDisplayName(), false);
        SafeParcelWriter.writeParcelable(parcel, 6, getPhotoUrl(), i, false);
        SafeParcelWriter.writeString(parcel, 7, getServerAuthCode(), false);
        SafeParcelWriter.writeLong(parcel, 8, this.o);
        SafeParcelWriter.writeString(parcel, 9, this.p, false);
        SafeParcelWriter.writeTypedList(parcel, 10, this.q, false);
        SafeParcelWriter.writeString(parcel, 11, getGivenName(), false);
        SafeParcelWriter.writeString(parcel, 12, getFamilyName(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public final String zac() {
        return this.p;
    }

    @NonNull
    public final String zad() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (getId() != null) {
                jSONObject.put("id", getId());
            }
            if (getIdToken() != null) {
                jSONObject.put("tokenId", getIdToken());
            }
            if (getEmail() != null) {
                jSONObject.put("email", getEmail());
            }
            if (getDisplayName() != null) {
                jSONObject.put("displayName", getDisplayName());
            }
            if (getGivenName() != null) {
                jSONObject.put("givenName", getGivenName());
            }
            if (getFamilyName() != null) {
                jSONObject.put("familyName", getFamilyName());
            }
            Uri photoUrl = getPhotoUrl();
            if (photoUrl != null) {
                jSONObject.put("photoUrl", photoUrl.toString());
            }
            if (getServerAuthCode() != null) {
                jSONObject.put("serverAuthCode", getServerAuthCode());
            }
            jSONObject.put("expirationTime", this.o);
            jSONObject.put("obfuscatedIdentifier", this.p);
            JSONArray jSONArray = new JSONArray();
            List list = this.q;
            Scope[] scopeArr = (Scope[]) list.toArray(new Scope[list.size()]);
            Arrays.sort(scopeArr, new Comparator() { // from class: com.google.android.gms.auth.api.signin.zaa
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    Parcelable.Creator<GoogleSignInAccount> creator = GoogleSignInAccount.CREATOR;
                    return ((Scope) obj).getScopeUri().compareTo(((Scope) obj2).getScopeUri());
                }
            });
            for (Scope scope : scopeArr) {
                jSONArray.put(scope.getScopeUri());
            }
            jSONObject.put("grantedScopes", jSONArray);
            jSONObject.remove("serverAuthCode");
            return jSONObject.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
