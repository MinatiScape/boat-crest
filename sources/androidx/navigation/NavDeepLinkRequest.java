package androidx.navigation;

import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public class NavDeepLinkRequest {

    /* renamed from: a  reason: collision with root package name */
    public final Uri f1445a;
    public final String b;
    public final String c;

    /* loaded from: classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Uri f1446a;
        public String b;
        public String c;

        @NonNull
        public static Builder fromAction(@NonNull String str) {
            if (!str.isEmpty()) {
                Builder builder = new Builder();
                builder.setAction(str);
                return builder;
            }
            throw new IllegalArgumentException("The NavDeepLinkRequest cannot have an empty action.");
        }

        @NonNull
        public static Builder fromMimeType(@NonNull String str) {
            Builder builder = new Builder();
            builder.setMimeType(str);
            return builder;
        }

        @NonNull
        public static Builder fromUri(@NonNull Uri uri) {
            Builder builder = new Builder();
            builder.setUri(uri);
            return builder;
        }

        @NonNull
        public NavDeepLinkRequest build() {
            return new NavDeepLinkRequest(this.f1446a, this.b, this.c);
        }

        @NonNull
        public Builder setAction(@NonNull String str) {
            if (!str.isEmpty()) {
                this.b = str;
                return this;
            }
            throw new IllegalArgumentException("The NavDeepLinkRequest cannot have an empty action.");
        }

        @NonNull
        public Builder setMimeType(@NonNull String str) {
            if (Pattern.compile("^[-\\w*.]+/[-\\w+*.]+$").matcher(str).matches()) {
                this.c = str;
                return this;
            }
            throw new IllegalArgumentException("The given mimeType " + str + " does not match to required \"type/subtype\" format");
        }

        @NonNull
        public Builder setUri(@NonNull Uri uri) {
            this.f1446a = uri;
            return this;
        }
    }

    public NavDeepLinkRequest(@NonNull Intent intent) {
        this(intent.getData(), intent.getAction(), intent.getType());
    }

    @Nullable
    public String getAction() {
        return this.b;
    }

    @Nullable
    public String getMimeType() {
        return this.c;
    }

    @Nullable
    public Uri getUri() {
        return this.f1445a;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NavDeepLinkRequest");
        sb.append("{");
        if (this.f1445a != null) {
            sb.append(" uri=");
            sb.append(this.f1445a.toString());
        }
        if (this.b != null) {
            sb.append(" action=");
            sb.append(this.b);
        }
        if (this.c != null) {
            sb.append(" mimetype=");
            sb.append(this.c);
        }
        sb.append(" }");
        return sb.toString();
    }

    public NavDeepLinkRequest(@Nullable Uri uri, @Nullable String str, @Nullable String str2) {
        this.f1445a = uri;
        this.b = str;
        this.c = str2;
    }
}
