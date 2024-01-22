package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import androidx.core.os.ConfigurationCompat;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.R;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.Locale;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public final class zac {
    @GuardedBy("sCache")

    /* renamed from: a  reason: collision with root package name */
    public static final SimpleArrayMap f8341a = new SimpleArrayMap();
    @Nullable
    @GuardedBy("sCache")
    public static Locale b;

    public static String a(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String b2 = b(context, str);
        if (b2 == null) {
            b2 = resources.getString(R.string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, b2, str2);
    }

    @Nullable
    public static String b(Context context, String str) {
        SimpleArrayMap simpleArrayMap = f8341a;
        synchronized (simpleArrayMap) {
            Locale locale = ConfigurationCompat.getLocales(context.getResources().getConfiguration()).get(0);
            if (!locale.equals(b)) {
                simpleArrayMap.clear();
                b = locale;
            }
            String str2 = (String) simpleArrayMap.get(str);
            if (str2 != null) {
                return str2;
            }
            Resources remoteResource = GooglePlayServicesUtil.getRemoteResource(context);
            if (remoteResource == null) {
                return null;
            }
            int identifier = remoteResource.getIdentifier(str, "string", "com.google.android.gms");
            if (identifier == 0) {
                Log.w("GoogleApiAvailability", "Missing resource: " + str);
                return null;
            }
            String string = remoteResource.getString(identifier);
            if (TextUtils.isEmpty(string)) {
                Log.w("GoogleApiAvailability", "Got empty resource: " + str);
                return null;
            }
            simpleArrayMap.put(str, string);
            return string;
        }
    }

    public static String zaa(Context context) {
        String packageName = context.getPackageName();
        try {
            return Wrappers.packageManager(context).getApplicationLabel(packageName).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            String str = context.getApplicationInfo().name;
            return TextUtils.isEmpty(str) ? packageName : str;
        }
    }

    public static String zab(Context context) {
        return context.getResources().getString(com.google.android.gms.base.R.string.common_google_play_services_notification_channel_name);
    }

    @NonNull
    public static String zac(Context context, int i) {
        Resources resources = context.getResources();
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return resources.getString(17039370);
                }
                return resources.getString(com.google.android.gms.base.R.string.common_google_play_services_enable_button);
            }
            return resources.getString(com.google.android.gms.base.R.string.common_google_play_services_update_button);
        }
        return resources.getString(com.google.android.gms.base.R.string.common_google_play_services_install_button);
    }

    @NonNull
    public static String zad(Context context, int i) {
        Resources resources = context.getResources();
        String zaa = zaa(context);
        if (i != 1) {
            if (i == 2) {
                return DeviceProperties.isWearableWithoutPlayStore(context) ? resources.getString(com.google.android.gms.base.R.string.common_google_play_services_wear_update_text) : resources.getString(com.google.android.gms.base.R.string.common_google_play_services_update_text, zaa);
            } else if (i != 3) {
                if (i != 5) {
                    if (i != 7) {
                        if (i != 9) {
                            if (i != 20) {
                                switch (i) {
                                    case 16:
                                        return a(context, "common_google_play_services_api_unavailable_text", zaa);
                                    case 17:
                                        return a(context, "common_google_play_services_sign_in_failed_text", zaa);
                                    case 18:
                                        return resources.getString(com.google.android.gms.base.R.string.common_google_play_services_updating_text, zaa);
                                    default:
                                        return resources.getString(R.string.common_google_play_services_unknown_issue, zaa);
                                }
                            }
                            return a(context, "common_google_play_services_restricted_profile_text", zaa);
                        }
                        return resources.getString(com.google.android.gms.base.R.string.common_google_play_services_unsupported_text, zaa);
                    }
                    return a(context, "common_google_play_services_network_error_text", zaa);
                }
                return a(context, "common_google_play_services_invalid_account_text", zaa);
            } else {
                return resources.getString(com.google.android.gms.base.R.string.common_google_play_services_enable_text, zaa);
            }
        }
        return resources.getString(com.google.android.gms.base.R.string.common_google_play_services_install_text, zaa);
    }

    @NonNull
    public static String zae(Context context, int i) {
        if (i != 6 && i != 19) {
            return zad(context, i);
        }
        return a(context, "common_google_play_services_resolution_required_text", zaa(context));
    }

    @NonNull
    public static String zaf(Context context, int i) {
        String zag;
        if (i == 6) {
            zag = b(context, "common_google_play_services_resolution_required_title");
        } else {
            zag = zag(context, i);
        }
        return zag == null ? context.getResources().getString(com.google.android.gms.base.R.string.common_google_play_services_notification_ticker) : zag;
    }

    @Nullable
    public static String zag(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(com.google.android.gms.base.R.string.common_google_play_services_install_title);
            case 2:
                return resources.getString(com.google.android.gms.base.R.string.common_google_play_services_update_title);
            case 3:
                return resources.getString(com.google.android.gms.base.R.string.common_google_play_services_enable_title);
            case 4:
            case 6:
            case 18:
                return null;
            case 5:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return b(context, "common_google_play_services_invalid_account_title");
            case 7:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return b(context, "common_google_play_services_network_error_title");
            case 8:
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            case 9:
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return null;
            case 10:
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            case 11:
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            case 12:
            case 13:
            case 14:
            case 15:
            case 19:
            default:
                Log.e("GoogleApiAvailability", "Unexpected error code " + i);
                return null;
            case 16:
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            case 17:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return b(context, "common_google_play_services_sign_in_failed_title");
            case 20:
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return b(context, "common_google_play_services_restricted_profile_title");
        }
    }
}
