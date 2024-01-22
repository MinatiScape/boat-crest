package androidx.navigation;

import androidx.navigation.NavDeepLink;
import com.clevertap.android.sdk.Constants;
import com.google.android.material.color.c;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@NavDeepLinkDsl
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0013\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0005\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0003\u0010\u0004R$\u0010\r\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR.\u0010\u0012\u001a\u0004\u0018\u00010\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u00068\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\b\u001a\u0004\b\u0010\u0010\n\"\u0004\b\u0011\u0010\fR$\u0010\u0016\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\b\u001a\u0004\b\u0014\u0010\n\"\u0004\b\u0015\u0010\f¨\u0006\u0019"}, d2 = {"Landroidx/navigation/NavDeepLinkDslBuilder;", "", "Landroidx/navigation/NavDeepLink;", "build$navigation_common_ktx_release", "()Landroidx/navigation/NavDeepLink;", "build", "", "b", "Ljava/lang/String;", "getUriPattern", "()Ljava/lang/String;", "setUriPattern", "(Ljava/lang/String;)V", "uriPattern", RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME, c.f10260a, "getAction", "setAction", Constants.KEY_ACTION, "d", "getMimeType", "setMimeType", "mimeType", "<init>", "()V", "navigation-common-ktx_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class NavDeepLinkDslBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final NavDeepLink.Builder f1444a = new NavDeepLink.Builder();
    @Nullable
    public String b;
    @Nullable
    public String c;
    @Nullable
    public String d;

    @NotNull
    public final NavDeepLink build$navigation_common_ktx_release() {
        NavDeepLink.Builder builder = this.f1444a;
        String str = this.b;
        if ((str == null && this.c == null && this.d == null) ? false : true) {
            if (str != null) {
                builder.setUriPattern(str);
            }
            String str2 = this.c;
            if (str2 != null) {
                builder.setAction(str2);
            }
            String str3 = this.d;
            if (str3 != null) {
                builder.setMimeType(str3);
            }
            NavDeepLink build = builder.build();
            Intrinsics.checkExpressionValueIsNotNull(build, "builder.apply {\n        …eType(it) }\n    }.build()");
            return build;
        }
        throw new IllegalStateException("The NavDeepLink must have an uri, action, and/or mimeType.".toString());
    }

    @Nullable
    public final String getAction() {
        return this.c;
    }

    @Nullable
    public final String getMimeType() {
        return this.d;
    }

    @Nullable
    public final String getUriPattern() {
        return this.b;
    }

    public final void setAction(@Nullable String str) {
        if (str != null) {
            if (str.length() == 0) {
                throw new IllegalArgumentException("The NavDeepLink cannot have an empty action.");
            }
        }
        this.c = str;
    }

    public final void setMimeType(@Nullable String str) {
        this.d = str;
    }

    public final void setUriPattern(@Nullable String str) {
        this.b = str;
    }
}
