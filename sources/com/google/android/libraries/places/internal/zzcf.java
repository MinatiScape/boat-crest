package com.google.android.libraries.places.internal;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes10.dex */
final class zzcf {
    private String zza;
    private String zzb;
    @Nullable
    private Locale zzc = null;
    private Map<String, String> zzd = new HashMap();

    public zzcf(String str, String str2) {
        this.zza = str;
        zzft.zza(!TextUtils.isEmpty(str2), "API key cannot be empty.");
        this.zzb = str2;
    }

    public final zzcf zza(@Nullable Locale locale) {
        this.zzc = locale;
        return this;
    }

    public final zzcf zza(Map<String, String> map) {
        this.zzd = new HashMap(map);
        return this;
    }

    public final String zza() {
        String languageTag;
        Uri.Builder buildUpon = Uri.parse("https://maps.googleapis.com/").buildUpon();
        buildUpon.appendEncodedPath("maps/api/place/");
        buildUpon.appendEncodedPath(this.zza);
        buildUpon.appendQueryParameter(Constants.KEY_KEY, this.zzb);
        Locale locale = this.zzc;
        if (locale != null) {
            if (Build.VERSION.SDK_INT < 21) {
                StringBuilder sb = new StringBuilder();
                String language = locale.getLanguage();
                if (!TextUtils.isEmpty(language)) {
                    sb.append(language);
                    String country = locale.getCountry();
                    if (!TextUtils.isEmpty(country)) {
                        sb.append("-");
                        sb.append(country);
                    }
                }
                languageTag = sb.toString();
            } else {
                languageTag = locale.toLanguageTag();
            }
            if (!TextUtils.isEmpty(languageTag)) {
                buildUpon.appendQueryParameter("language", languageTag);
            }
        }
        Map<String, String> map = this.zzd;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                buildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        return buildUpon.build().toString();
    }
}
