package com.google.firebase.messaging;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.internal.Objects;
import java.util.regex.Pattern;
/* loaded from: classes10.dex */
public final class o0 {
    public static final Pattern d = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");

    /* renamed from: a  reason: collision with root package name */
    public final String f11354a;
    public final String b;
    public final String c;

    public o0(String str, String str2) {
        this.f11354a = d(str2, str);
        this.b = str;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("!");
        sb.append(str2);
        this.c = sb.toString();
    }

    @Nullable
    public static o0 a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split("!", -1);
        if (split.length != 2) {
            return null;
        }
        return new o0(split[0], split[1]);
    }

    @NonNull
    public static String d(String str, String str2) {
        if (str != null && str.startsWith("/topics/")) {
            Log.w(Constants.TAG, String.format("Format /topics/topic-name is deprecated. Only 'topic-name' should be used in %s.", str2));
            str = str.substring(8);
        }
        if (str == null || !d.matcher(str).matches()) {
            throw new IllegalArgumentException(String.format("Invalid topic name: %s does not match the allowed format %s.", str, "[a-zA-Z0-9-_.~%]{1,900}"));
        }
        return str;
    }

    public static o0 f(@NonNull String str) {
        return new o0(ExifInterface.LATITUDE_SOUTH, str);
    }

    public static o0 g(@NonNull String str) {
        return new o0("U", str);
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.f11354a;
    }

    public String e() {
        return this.c;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof o0) {
            o0 o0Var = (o0) obj;
            return this.f11354a.equals(o0Var.f11354a) && this.b.equals(o0Var.b);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.b, this.f11354a);
    }
}
