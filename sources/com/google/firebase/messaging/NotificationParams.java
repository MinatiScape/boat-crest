package com.google.firebase.messaging;

import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.messaging.Constants;
import java.util.Arrays;
import java.util.MissingFormatArgumentException;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
/* loaded from: classes10.dex */
public class NotificationParams {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final Bundle f11332a;

    public NotificationParams(@NonNull Bundle bundle) {
        Objects.requireNonNull(bundle, "data");
        this.f11332a = new Bundle(bundle);
    }

    public static int a(String str) {
        int parseColor = Color.parseColor(str);
        if (parseColor != -16777216) {
            return parseColor;
        }
        throw new IllegalArgumentException("Transparent color is invalid");
    }

    public static boolean f(String str) {
        return str.startsWith(Constants.AnalyticsKeys.PREFIX) || str.equals("from");
    }

    public static boolean g(String str) {
        return str.startsWith(Constants.MessagePayloadKeys.RESERVED_CLIENT_LIB_PREFIX) || str.startsWith(Constants.MessageNotificationKeys.NOTIFICATION_PREFIX) || str.startsWith(Constants.MessageNotificationKeys.NOTIFICATION_PREFIX_OLD);
    }

    public static String h(String str) {
        return !str.startsWith(Constants.MessageNotificationKeys.NOTIFICATION_PREFIX) ? str : str.replace(Constants.MessageNotificationKeys.NOTIFICATION_PREFIX, Constants.MessageNotificationKeys.NOTIFICATION_PREFIX_OLD);
    }

    public static String j(String str) {
        return str.startsWith(Constants.MessageNotificationKeys.NOTIFICATION_PREFIX) ? str.substring(6) : str;
    }

    @Nullable
    public int[] b() {
        JSONArray jSONArray = getJSONArray(Constants.MessageNotificationKeys.LIGHT_SETTINGS);
        if (jSONArray == null) {
            return null;
        }
        int[] iArr = new int[3];
        try {
            if (jSONArray.length() == 3) {
                iArr[0] = a(jSONArray.optString(0));
                iArr[1] = jSONArray.optInt(1);
                iArr[2] = jSONArray.optInt(2);
                return iArr;
            }
            throw new JSONException("lightSettings don't have all three fields");
        } catch (IllegalArgumentException e) {
            String valueOf = String.valueOf(jSONArray);
            String message = e.getMessage();
            StringBuilder sb = new StringBuilder(valueOf.length() + 60 + String.valueOf(message).length());
            sb.append("LightSettings is invalid: ");
            sb.append(valueOf);
            sb.append(". ");
            sb.append(message);
            sb.append(". Skipping setting LightSettings");
            Log.w("NotificationParams", sb.toString());
            return null;
        } catch (JSONException unused) {
            String valueOf2 = String.valueOf(jSONArray);
            StringBuilder sb2 = new StringBuilder(valueOf2.length() + 58);
            sb2.append("LightSettings is invalid: ");
            sb2.append(valueOf2);
            sb2.append(". Skipping setting LightSettings");
            Log.w("NotificationParams", sb2.toString());
            return null;
        }
    }

    @Nullable
    public Integer c() {
        Integer integer = getInteger(Constants.MessageNotificationKeys.NOTIFICATION_COUNT);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() < 0) {
            String valueOf = String.valueOf(integer);
            StringBuilder sb = new StringBuilder(valueOf.length() + 67);
            sb.append("notificationCount is invalid: ");
            sb.append(valueOf);
            sb.append(". Skipping setting notificationCount.");
            Log.w(Constants.TAG, sb.toString());
            return null;
        }
        return integer;
    }

    @Nullable
    public Integer d() {
        Integer integer = getInteger(Constants.MessageNotificationKeys.NOTIFICATION_PRIORITY);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() < -2 || integer.intValue() > 2) {
            String valueOf = String.valueOf(integer);
            StringBuilder sb = new StringBuilder(valueOf.length() + 72);
            sb.append("notificationPriority is invalid ");
            sb.append(valueOf);
            sb.append(". Skipping setting notificationPriority.");
            Log.w(Constants.TAG, sb.toString());
            return null;
        }
        return integer;
    }

    public Integer e() {
        Integer integer = getInteger(Constants.MessageNotificationKeys.VISIBILITY);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() < -1 || integer.intValue() > 1) {
            String valueOf = String.valueOf(integer);
            StringBuilder sb = new StringBuilder(valueOf.length() + 53);
            sb.append("visibility is invalid: ");
            sb.append(valueOf);
            sb.append(". Skipping setting visibility.");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
        return integer;
    }

    public boolean getBoolean(@NonNull String str) {
        String string = getString(str);
        return "1".equals(string) || Boolean.parseBoolean(string);
    }

    @NonNull
    public Integer getInteger(@NonNull String str) {
        String string = getString(str);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(string));
        } catch (NumberFormatException unused) {
            String j = j(str);
            StringBuilder sb = new StringBuilder(String.valueOf(j).length() + 38 + String.valueOf(string).length());
            sb.append("Couldn't parse value of ");
            sb.append(j);
            sb.append("(");
            sb.append(string);
            sb.append(") into an int");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
    }

    @Nullable
    public JSONArray getJSONArray(@NonNull String str) {
        String string = getString(str);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            return new JSONArray(string);
        } catch (JSONException unused) {
            String j = j(str);
            StringBuilder sb = new StringBuilder(String.valueOf(j).length() + 50 + String.valueOf(string).length());
            sb.append("Malformed JSON for key ");
            sb.append(j);
            sb.append(": ");
            sb.append(string);
            sb.append(", falling back to default");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
    }

    @Nullable
    public Uri getLink() {
        String string = getString(Constants.MessageNotificationKeys.LINK_ANDROID);
        if (TextUtils.isEmpty(string)) {
            string = getString(Constants.MessageNotificationKeys.LINK);
        }
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return Uri.parse(string);
    }

    @Nullable
    public Object[] getLocalizationArgsForKey(@NonNull String str) {
        JSONArray jSONArray = getJSONArray(String.valueOf(str).concat(Constants.MessageNotificationKeys.TEXT_ARGS_SUFFIX));
        if (jSONArray == null) {
            return null;
        }
        int length = jSONArray.length();
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = jSONArray.optString(i);
        }
        return strArr;
    }

    @Nullable
    public String getLocalizationResourceForKey(@NonNull String str) {
        return getString(String.valueOf(str).concat(Constants.MessageNotificationKeys.TEXT_RESOURCE_SUFFIX));
    }

    @Nullable
    public String getLocalizedString(@NonNull Resources resources, @NonNull String str, @NonNull String str2) {
        String localizationResourceForKey = getLocalizationResourceForKey(str2);
        if (TextUtils.isEmpty(localizationResourceForKey)) {
            return null;
        }
        int identifier = resources.getIdentifier(localizationResourceForKey, "string", str);
        if (identifier == 0) {
            String j = j(String.valueOf(str2).concat(Constants.MessageNotificationKeys.TEXT_RESOURCE_SUFFIX));
            StringBuilder sb = new StringBuilder(String.valueOf(j).length() + 49 + String.valueOf(str2).length());
            sb.append(j);
            sb.append(" resource not found: ");
            sb.append(str2);
            sb.append(" Default value will be used.");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
        Object[] localizationArgsForKey = getLocalizationArgsForKey(str2);
        if (localizationArgsForKey == null) {
            return resources.getString(identifier);
        }
        try {
            return resources.getString(identifier, localizationArgsForKey);
        } catch (MissingFormatArgumentException e) {
            String j2 = j(str2);
            String arrays = Arrays.toString(localizationArgsForKey);
            StringBuilder sb2 = new StringBuilder(String.valueOf(j2).length() + 58 + String.valueOf(arrays).length());
            sb2.append("Missing format argument for ");
            sb2.append(j2);
            sb2.append(": ");
            sb2.append(arrays);
            sb2.append(" Default value will be used.");
            Log.w("NotificationParams", sb2.toString(), e);
            return null;
        }
    }

    @NonNull
    public Long getLong(@NonNull String str) {
        String string = getString(str);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            return Long.valueOf(Long.parseLong(string));
        } catch (NumberFormatException unused) {
            String j = j(str);
            StringBuilder sb = new StringBuilder(String.valueOf(j).length() + 38 + String.valueOf(string).length());
            sb.append("Couldn't parse value of ");
            sb.append(j);
            sb.append("(");
            sb.append(string);
            sb.append(") into a long");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
    }

    @NonNull
    public String getNotificationChannelId() {
        return getString(Constants.MessageNotificationKeys.CHANNEL);
    }

    @NonNull
    public String getPossiblyLocalizedString(@NonNull Resources resources, @NonNull String str, @NonNull String str2) {
        String string = getString(str2);
        return !TextUtils.isEmpty(string) ? string : getLocalizedString(resources, str, str2);
    }

    @Nullable
    public String getSoundResourceName() {
        String string = getString(Constants.MessageNotificationKeys.SOUND_2);
        return TextUtils.isEmpty(string) ? getString(Constants.MessageNotificationKeys.SOUND) : string;
    }

    @NonNull
    public String getString(@NonNull String str) {
        return this.f11332a.getString(i(str));
    }

    @Nullable
    public long[] getVibrateTimings() {
        JSONArray jSONArray = getJSONArray(Constants.MessageNotificationKeys.VIBRATE_TIMINGS);
        if (jSONArray == null) {
            return null;
        }
        try {
            if (jSONArray.length() > 1) {
                int length = jSONArray.length();
                long[] jArr = new long[length];
                for (int i = 0; i < length; i++) {
                    jArr[i] = jSONArray.optLong(i);
                }
                return jArr;
            }
            throw new JSONException("vibrateTimings have invalid length");
        } catch (NumberFormatException | JSONException unused) {
            String valueOf = String.valueOf(jSONArray);
            StringBuilder sb = new StringBuilder(valueOf.length() + 74);
            sb.append("User defined vibrateTimings is invalid: ");
            sb.append(valueOf);
            sb.append(". Skipping setting vibrateTimings.");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
    }

    public boolean hasImage() {
        return !TextUtils.isEmpty(getString(Constants.MessageNotificationKeys.IMAGE_URL));
    }

    public final String i(String str) {
        if (!this.f11332a.containsKey(str) && str.startsWith(Constants.MessageNotificationKeys.NOTIFICATION_PREFIX)) {
            String h = h(str);
            if (this.f11332a.containsKey(h)) {
                return h;
            }
        }
        return str;
    }

    public boolean isNotification() {
        return getBoolean(Constants.MessageNotificationKeys.ENABLE_NOTIFICATION);
    }

    @NonNull
    public Bundle paramsForAnalyticsIntent() {
        Bundle bundle = new Bundle(this.f11332a);
        for (String str : this.f11332a.keySet()) {
            if (!f(str)) {
                bundle.remove(str);
            }
        }
        return bundle;
    }

    @NonNull
    public Bundle paramsWithReservedKeysRemoved() {
        Bundle bundle = new Bundle(this.f11332a);
        for (String str : this.f11332a.keySet()) {
            if (g(str)) {
                bundle.remove(str);
            }
        }
        return bundle;
    }

    public static boolean isNotification(@NonNull Bundle bundle) {
        return "1".equals(bundle.getString(Constants.MessageNotificationKeys.ENABLE_NOTIFICATION)) || "1".equals(bundle.getString(h(Constants.MessageNotificationKeys.ENABLE_NOTIFICATION)));
    }
}
