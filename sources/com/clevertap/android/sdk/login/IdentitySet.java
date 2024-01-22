package com.clevertap.android.sdk.login;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Utils;
import java.util.HashSet;
import java.util.Iterator;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class IdentitySet {

    /* renamed from: a  reason: collision with root package name */
    public final HashSet<String> f2644a;

    public IdentitySet(String[] strArr) {
        this.f2644a = new HashSet<>();
        e(strArr);
    }

    public static IdentitySet b(String str) {
        return new IdentitySet(str.split(Constants.SEPARATOR_COMMA));
    }

    public static IdentitySet c(String[] strArr) {
        return new IdentitySet(strArr);
    }

    public static IdentitySet d() {
        return new IdentitySet(Constants.LEGACY_IDENTITY_KEYS);
    }

    public boolean a(String str) {
        return Utils.containsIgnoreCase(this.f2644a, str);
    }

    public final void e(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        for (String str : strArr) {
            if (Utils.containsIgnoreCase(Constants.ALL_IDENTITY_KEYS, str)) {
                this.f2644a.add(Utils.convertToTitleCase(str));
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f2644a.equals(((IdentitySet) obj).f2644a);
    }

    public boolean f() {
        return !this.f2644a.isEmpty();
    }

    public int hashCode() {
        return super.hashCode();
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.f2644a.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (Constants.ALL_IDENTITY_KEYS.contains(next)) {
                sb.append(next);
                sb.append(it.hasNext() ? Constants.SEPARATOR_COMMA : "");
            }
        }
        return sb.toString();
    }

    public IdentitySet(HashSet<String> hashSet) {
        HashSet<String> hashSet2 = new HashSet<>();
        this.f2644a = hashSet2;
        hashSet2.addAll(hashSet);
    }
}
