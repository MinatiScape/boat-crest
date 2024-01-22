package androidx.core.provider;

import android.util.Base64;
import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import java.util.List;
/* loaded from: classes.dex */
public final class FontRequest {

    /* renamed from: a  reason: collision with root package name */
    public final String f1081a;
    public final String b;
    public final String c;
    public final List<List<byte[]>> d;
    public final int e;
    public final String f;

    public FontRequest(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull List<List<byte[]>> list) {
        this.f1081a = (String) Preconditions.checkNotNull(str);
        this.b = (String) Preconditions.checkNotNull(str2);
        this.c = (String) Preconditions.checkNotNull(str3);
        this.d = (List) Preconditions.checkNotNull(list);
        this.e = 0;
        this.f = a(str, str2, str3);
    }

    public final String a(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        return str + "-" + str2 + "-" + str3;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public String b() {
        return this.f;
    }

    @Nullable
    public List<List<byte[]>> getCertificates() {
        return this.d;
    }

    @ArrayRes
    public int getCertificatesArrayResId() {
        return this.e;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public String getIdentifier() {
        return this.f;
    }

    @NonNull
    public String getProviderAuthority() {
        return this.f1081a;
    }

    @NonNull
    public String getProviderPackage() {
        return this.b;
    }

    @NonNull
    public String getQuery() {
        return this.c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f1081a + ", mProviderPackage: " + this.b + ", mQuery: " + this.c + ", mCertificates:");
        for (int i = 0; i < this.d.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.d.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.e);
        return sb.toString();
    }

    public FontRequest(@NonNull String str, @NonNull String str2, @NonNull String str3, @ArrayRes int i) {
        this.f1081a = (String) Preconditions.checkNotNull(str);
        this.b = (String) Preconditions.checkNotNull(str2);
        this.c = (String) Preconditions.checkNotNull(str3);
        this.d = null;
        Preconditions.checkArgument(i != 0);
        this.e = i;
        this.f = a(str, str2, str3);
    }
}
