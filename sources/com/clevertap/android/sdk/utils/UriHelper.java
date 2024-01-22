package com.clevertap.android.sdk.utils;

import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import java.net.URLDecoder;
import org.json.JSONObject;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public final class UriHelper {
    public static String a(String str, UrlQuerySanitizer urlQuerySanitizer) {
        String b = b(str, urlQuerySanitizer);
        if (b == null && (b = d(str, urlQuerySanitizer)) == null) {
            return null;
        }
        return b;
    }

    public static String b(String str, UrlQuerySanitizer urlQuerySanitizer) {
        return c("utm_" + str, urlQuerySanitizer, true);
    }

    public static String c(String str, UrlQuerySanitizer urlQuerySanitizer, boolean z) {
        if (str != null && urlQuerySanitizer != null) {
            try {
                String value = urlQuerySanitizer.getValue(str);
                if (value == null) {
                    return null;
                }
                return (!z || value.length() <= 120) ? value : value.substring(0, 120);
            } catch (Throwable th) {
                Logger.v("Couldn't parse the URI", th);
            }
        }
        return null;
    }

    public static String d(String str, UrlQuerySanitizer urlQuerySanitizer) {
        return c(Constants.WZRK_PREFIX + str, urlQuerySanitizer, true);
    }

    public static Bundle getAllKeyValuePairs(String str, boolean z) {
        if (str == null) {
            return new Bundle();
        }
        Bundle bundle = new Bundle();
        try {
            UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer();
            urlQuerySanitizer.setAllowUnregisteredParamaters(true);
            urlQuerySanitizer.setUnregisteredParameterValueSanitizer(UrlQuerySanitizer.getAllButNulLegal());
            urlQuerySanitizer.parseUrl(str);
            for (String str2 : urlQuerySanitizer.getParameterSet()) {
                String c = c(str2, urlQuerySanitizer, false);
                if (c != null) {
                    if (!z && !str2.equals(Constants.KEY_C2A)) {
                        bundle.putString(str2, URLDecoder.decode(c, "UTF-8"));
                    }
                    bundle.putString(str2, c);
                }
            }
        } catch (Throwable unused) {
        }
        return bundle;
    }

    public static JSONObject getUrchinFromUri(Uri uri) {
        JSONObject jSONObject = new JSONObject();
        try {
            UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer();
            urlQuerySanitizer.setAllowUnregisteredParamaters(true);
            urlQuerySanitizer.parseUrl(uri.toString());
            String a2 = a("source", urlQuerySanitizer);
            String a3 = a("medium", urlQuerySanitizer);
            String a4 = a("campaign", urlQuerySanitizer);
            jSONObject.put("us", a2);
            jSONObject.put("um", a3);
            jSONObject.put("uc", a4);
            String d = d("medium", urlQuerySanitizer);
            if (d != null && d.matches("^email$|^social$|^search$")) {
                jSONObject.put("wm", d);
            }
            Logger.d("Referrer data: " + jSONObject.toString(4));
        } catch (Throwable unused) {
        }
        return jSONObject;
    }
}
