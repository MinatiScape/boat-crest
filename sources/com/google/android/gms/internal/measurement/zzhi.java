package com.google.android.gms.internal.measurement;

import android.net.Uri;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public final class zzhi {

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, Map<String, String>> f8954a;

    public zzhi(Map<String, Map<String, String>> map) {
        this.f8954a = map;
    }

    @Nullable
    public final String zza(@Nullable Uri uri, @Nullable String str, @Nullable String str2, String str3) {
        if (uri != null) {
            Map<String, String> map = this.f8954a.get(uri.toString());
            if (map == null) {
                return null;
            }
            String valueOf = String.valueOf(str3);
            return map.get(valueOf.length() != 0 ? "".concat(valueOf) : new String(""));
        }
        return null;
    }
}
