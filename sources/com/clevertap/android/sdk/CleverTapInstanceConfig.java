package com.clevertap.android.sdk;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.cryption.CryptHandler;
import com.clevertap.android.sdk.login.LoginConstants;
import com.clevertap.android.sdk.pushnotification.PushNotificationUtil;
import com.clevertap.android.sdk.utils.CTJsonConverter;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class CleverTapInstanceConfig implements Parcelable {
    public static final Parcelable.Creator<CleverTapInstanceConfig> CREATOR = new a();
    public int A;
    public String h;
    public String i;
    public String j;
    @NonNull
    public ArrayList<String> k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public int p;
    public boolean q;
    public boolean r;
    public String s;
    public boolean t;
    public Logger u;
    public String v;
    public boolean w;
    public String[] x;
    public boolean y;
    public boolean z;

    /* loaded from: classes2.dex */
    public class a implements Parcelable.Creator<CleverTapInstanceConfig> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public CleverTapInstanceConfig createFromParcel(Parcel parcel) {
            return new CleverTapInstanceConfig(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public CleverTapInstanceConfig[] newArray(int i) {
            return new CleverTapInstanceConfig[i];
        }
    }

    public /* synthetic */ CleverTapInstanceConfig(Parcel parcel, a aVar) {
        this(parcel);
    }

    public static CleverTapInstanceConfig createDefaultInstance(Context context, @NonNull String str, @NonNull String str2, String str3) {
        return new CleverTapInstanceConfig(context, str, str2, str3, true);
    }

    public static CleverTapInstanceConfig createInstance(Context context, @NonNull String str, @NonNull String str2) {
        if (str != null && str2 != null) {
            return new CleverTapInstanceConfig(context, str, str2, null, false);
        }
        Logger.i("CleverTap accountId and accountToken cannot be null");
        return null;
    }

    public final String a(@NonNull String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (TextUtils.isEmpty(str)) {
            str2 = "";
        } else {
            str2 = ":" + str;
        }
        sb.append(str2);
        sb.append(":");
        sb.append(this.h);
        sb.append("]");
        return sb.toString();
    }

    public boolean b() {
        return this.q;
    }

    public boolean c() {
        return this.w;
    }

    public boolean d() {
        return this.z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void e() {
        this.o = true;
    }

    public void enablePersonalization(boolean z) {
        this.w = z;
    }

    public String f() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.KEY_ACCOUNT_ID, getAccountId());
            jSONObject.put(Constants.KEY_ACCOUNT_TOKEN, getAccountToken());
            jSONObject.put(Constants.KEY_ACCOUNT_REGION, getAccountRegion());
            jSONObject.put(Constants.KEY_FCM_SENDER_ID, getFcmSenderId());
            jSONObject.put(Constants.KEY_ANALYTICS_ONLY, isAnalyticsOnly());
            jSONObject.put(Constants.KEY_DEFAULT_INSTANCE, isDefaultInstance());
            jSONObject.put(Constants.KEY_USE_GOOGLE_AD_ID, d());
            jSONObject.put(Constants.KEY_DISABLE_APP_LAUNCHED, b());
            jSONObject.put(Constants.KEY_PERSONALIZATION, c());
            jSONObject.put(Constants.KEY_DEBUG_LEVEL, getDebugLevel());
            jSONObject.put(Constants.KEY_CREATED_POST_APP_LAUNCH, isCreatedPostAppLaunch());
            jSONObject.put(Constants.KEY_SSL_PINNING, isSslPinningEnabled());
            jSONObject.put(Constants.KEY_BACKGROUND_SYNC, isBackgroundSync());
            jSONObject.put(Constants.KEY_ENABLE_CUSTOM_CT_ID, getEnableCustomCleverTapId());
            jSONObject.put("packageName", getPackageName());
            jSONObject.put(Constants.KEY_BETA, isBeta());
            jSONObject.put(Constants.KEY_ALLOWED_PUSH_TYPES, CTJsonConverter.toJsonArray(this.k));
            jSONObject.put(Constants.KEY_ENCRYPTION_LEVEL, getEncryptionLevel());
            return jSONObject.toString();
        } catch (Throwable th) {
            Logger.v("Unable to convert config to JSON : ", th.getCause());
            return null;
        }
    }

    public String getAccountId() {
        return this.h;
    }

    public String getAccountRegion() {
        return this.i;
    }

    public String getAccountToken() {
        return this.j;
    }

    @NonNull
    public ArrayList<String> getAllowedPushTypes() {
        return this.k;
    }

    public int getDebugLevel() {
        return this.p;
    }

    public boolean getEnableCustomCleverTapId() {
        return this.r;
    }

    public int getEncryptionLevel() {
        return this.A;
    }

    public String getFcmSenderId() {
        return this.s;
    }

    public String[] getIdentityKeys() {
        return this.x;
    }

    public Logger getLogger() {
        if (this.u == null) {
            this.u = new Logger(this.p);
        }
        return this.u;
    }

    public String getPackageName() {
        return this.v;
    }

    public boolean isAnalyticsOnly() {
        return this.l;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean isBackgroundSync() {
        return this.m;
    }

    public boolean isBeta() {
        return this.n;
    }

    public boolean isCreatedPostAppLaunch() {
        return this.o;
    }

    public boolean isDefaultInstance() {
        return this.t;
    }

    public boolean isSslPinningEnabled() {
        return this.y;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void log(@NonNull String str, @NonNull String str2) {
        this.u.verbose(a(str), str2);
    }

    public void setAnalyticsOnly(boolean z) {
        this.l = z;
    }

    public void setBackgroundSync(boolean z) {
        this.m = z;
    }

    public void setDebugLevel(CleverTapAPI.LogLevel logLevel) {
        setDebugLevel(logLevel.intValue());
    }

    public void setDisableAppLaunchedEvent(boolean z) {
        this.q = z;
    }

    public void setEnableCustomCleverTapId(boolean z) {
        this.r = z;
    }

    public void setEncryptionLevel(CryptHandler.EncryptionLevel encryptionLevel) {
        this.A = encryptionLevel.intValue();
    }

    public void setIdentityKeys(String... strArr) {
        if (this.t) {
            return;
        }
        this.x = strArr;
        log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "Setting Profile Keys via setter: " + Arrays.toString(this.x));
    }

    public void useGoogleAdId(boolean z) {
        this.z = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.h);
        parcel.writeString(this.j);
        parcel.writeString(this.i);
        parcel.writeByte(this.l ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.t ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.z ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.q ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.w ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.p);
        parcel.writeByte(this.o ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.y ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.m ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.r ? (byte) 1 : (byte) 0);
        parcel.writeString(this.s);
        parcel.writeString(this.v);
        parcel.writeByte(this.n ? (byte) 1 : (byte) 0);
        parcel.writeList(this.k);
        parcel.writeStringArray(this.x);
        parcel.writeInt(this.A);
    }

    public CleverTapInstanceConfig(CleverTapInstanceConfig cleverTapInstanceConfig) {
        this.k = PushNotificationUtil.getAll();
        this.x = Constants.NULL_STRING_ARRAY;
        this.h = cleverTapInstanceConfig.h;
        this.j = cleverTapInstanceConfig.j;
        this.i = cleverTapInstanceConfig.i;
        this.t = cleverTapInstanceConfig.t;
        this.l = cleverTapInstanceConfig.l;
        this.w = cleverTapInstanceConfig.w;
        this.p = cleverTapInstanceConfig.p;
        this.u = cleverTapInstanceConfig.u;
        this.z = cleverTapInstanceConfig.z;
        this.q = cleverTapInstanceConfig.q;
        this.o = cleverTapInstanceConfig.o;
        this.y = cleverTapInstanceConfig.y;
        this.m = cleverTapInstanceConfig.m;
        this.r = cleverTapInstanceConfig.r;
        this.s = cleverTapInstanceConfig.s;
        this.v = cleverTapInstanceConfig.v;
        this.n = cleverTapInstanceConfig.n;
        this.k = cleverTapInstanceConfig.k;
        this.x = cleverTapInstanceConfig.x;
        this.A = cleverTapInstanceConfig.A;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void log(@NonNull String str, @NonNull String str2, Throwable th) {
        this.u.verbose(a(str), str2, th);
    }

    public void setDebugLevel(int i) {
        this.p = i;
        Logger logger = this.u;
        if (logger != null) {
            logger.setDebugLevel(i);
        }
    }

    public static CleverTapInstanceConfig createInstance(Context context, @NonNull String str, @NonNull String str2, String str3) {
        if (str != null && str2 != null) {
            return new CleverTapInstanceConfig(context, str, str2, str3, false);
        }
        Logger.i("CleverTap accountId and accountToken cannot be null");
        return null;
    }

    public static CleverTapInstanceConfig createInstance(@NonNull String str) {
        try {
            return new CleverTapInstanceConfig(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public CleverTapInstanceConfig(Context context, String str, String str2, String str3, boolean z) {
        this.k = PushNotificationUtil.getAll();
        this.x = Constants.NULL_STRING_ARRAY;
        this.h = str;
        this.j = str2;
        this.i = str3;
        this.t = z;
        this.l = false;
        this.w = true;
        int intValue = CleverTapAPI.LogLevel.INFO.intValue();
        this.p = intValue;
        this.u = new Logger(intValue);
        this.o = false;
        ManifestInfo manifestInfo = ManifestInfo.getInstance(context);
        this.z = manifestInfo.k();
        this.q = manifestInfo.g();
        this.y = manifestInfo.isSSLPinningEnabled();
        this.m = manifestInfo.h();
        this.s = manifestInfo.getFCMSenderId();
        this.v = manifestInfo.f();
        this.r = manifestInfo.j();
        this.n = manifestInfo.d();
        if (this.t) {
            this.A = manifestInfo.getEncryptionLevel();
            this.x = manifestInfo.getProfileKeys();
            log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "Setting Profile Keys from Manifest: " + Arrays.toString(this.x));
            return;
        }
        this.A = 0;
    }

    public CleverTapInstanceConfig(String str) throws Throwable {
        this.k = PushNotificationUtil.getAll();
        this.x = Constants.NULL_STRING_ARRAY;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(Constants.KEY_ACCOUNT_ID)) {
                this.h = jSONObject.getString(Constants.KEY_ACCOUNT_ID);
            }
            if (jSONObject.has(Constants.KEY_ACCOUNT_TOKEN)) {
                this.j = jSONObject.getString(Constants.KEY_ACCOUNT_TOKEN);
            }
            if (jSONObject.has(Constants.KEY_ACCOUNT_REGION)) {
                this.i = jSONObject.getString(Constants.KEY_ACCOUNT_REGION);
            }
            if (jSONObject.has(Constants.KEY_ANALYTICS_ONLY)) {
                this.l = jSONObject.getBoolean(Constants.KEY_ANALYTICS_ONLY);
            }
            if (jSONObject.has(Constants.KEY_DEFAULT_INSTANCE)) {
                this.t = jSONObject.getBoolean(Constants.KEY_DEFAULT_INSTANCE);
            }
            if (jSONObject.has(Constants.KEY_USE_GOOGLE_AD_ID)) {
                this.z = jSONObject.getBoolean(Constants.KEY_USE_GOOGLE_AD_ID);
            }
            if (jSONObject.has(Constants.KEY_DISABLE_APP_LAUNCHED)) {
                this.q = jSONObject.getBoolean(Constants.KEY_DISABLE_APP_LAUNCHED);
            }
            if (jSONObject.has(Constants.KEY_PERSONALIZATION)) {
                this.w = jSONObject.getBoolean(Constants.KEY_PERSONALIZATION);
            }
            if (jSONObject.has(Constants.KEY_DEBUG_LEVEL)) {
                this.p = jSONObject.getInt(Constants.KEY_DEBUG_LEVEL);
            }
            this.u = new Logger(this.p);
            if (jSONObject.has("packageName")) {
                this.v = jSONObject.getString("packageName");
            }
            if (jSONObject.has(Constants.KEY_CREATED_POST_APP_LAUNCH)) {
                this.o = jSONObject.getBoolean(Constants.KEY_CREATED_POST_APP_LAUNCH);
            }
            if (jSONObject.has(Constants.KEY_SSL_PINNING)) {
                this.y = jSONObject.getBoolean(Constants.KEY_SSL_PINNING);
            }
            if (jSONObject.has(Constants.KEY_BACKGROUND_SYNC)) {
                this.m = jSONObject.getBoolean(Constants.KEY_BACKGROUND_SYNC);
            }
            if (jSONObject.has(Constants.KEY_ENABLE_CUSTOM_CT_ID)) {
                this.r = jSONObject.getBoolean(Constants.KEY_ENABLE_CUSTOM_CT_ID);
            }
            if (jSONObject.has(Constants.KEY_FCM_SENDER_ID)) {
                this.s = jSONObject.getString(Constants.KEY_FCM_SENDER_ID);
            }
            if (jSONObject.has(Constants.KEY_BETA)) {
                this.n = jSONObject.getBoolean(Constants.KEY_BETA);
            }
            if (jSONObject.has(Constants.KEY_ALLOWED_PUSH_TYPES)) {
                this.k = CTJsonConverter.toList(jSONObject.getJSONArray(Constants.KEY_ALLOWED_PUSH_TYPES));
            }
            if (jSONObject.has(Constants.KEY_IDENTITY_TYPES)) {
                this.x = (String[]) CTJsonConverter.toArray(jSONObject.getJSONArray(Constants.KEY_IDENTITY_TYPES));
            }
            if (jSONObject.has(Constants.KEY_ENCRYPTION_LEVEL)) {
                this.A = jSONObject.getInt(Constants.KEY_ENCRYPTION_LEVEL);
            }
        } catch (Throwable th) {
            Logger.v("Error constructing CleverTapInstanceConfig from JSON: " + str + ": ", th.getCause());
            throw th;
        }
    }

    public CleverTapInstanceConfig(Parcel parcel) {
        this.k = PushNotificationUtil.getAll();
        this.x = Constants.NULL_STRING_ARRAY;
        this.h = parcel.readString();
        this.j = parcel.readString();
        this.i = parcel.readString();
        this.l = parcel.readByte() != 0;
        this.t = parcel.readByte() != 0;
        this.z = parcel.readByte() != 0;
        this.q = parcel.readByte() != 0;
        this.w = parcel.readByte() != 0;
        this.p = parcel.readInt();
        this.o = parcel.readByte() != 0;
        this.y = parcel.readByte() != 0;
        this.m = parcel.readByte() != 0;
        this.r = parcel.readByte() != 0;
        this.s = parcel.readString();
        this.v = parcel.readString();
        this.u = new Logger(this.p);
        this.n = parcel.readByte() != 0;
        ArrayList<String> arrayList = new ArrayList<>();
        this.k = arrayList;
        parcel.readList(arrayList, String.class.getClassLoader());
        this.x = parcel.createStringArray();
        this.A = parcel.readInt();
    }
}
