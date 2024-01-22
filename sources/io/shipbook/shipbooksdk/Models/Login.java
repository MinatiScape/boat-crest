package io.shipbook.shipbooksdk.Models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.material.color.c;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.jstyle.blesdk1860.constant.DeviceKey;
import io.shipbook.shipbooksdk.Models.User;
import io.shipbook.shipbooksdk.Networking.SessionManager;
import io.shipbook.shipbooksdk.Util.DateHelper;
import io.shipbook.shipbooksdk.Util.DateHelperKt;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0002\b<\b\u0081\b\u0018\u0000 m2\u00020\u0001:\u0001mBÃ\u0001\u0012\u0006\u0010\u001c\u001a\u00020\u0004\u0012\u0006\u0010\u001d\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0004\u0012\b\b\u0002\u0010 \u001a\u00020\u0004\u0012\b\b\u0002\u0010!\u001a\u00020\n\u0012\b\b\u0002\u0010\"\u001a\u00020\n\u0012\b\b\u0002\u0010#\u001a\u00020\u0004\u0012\b\b\u0002\u0010$\u001a\u00020\u0004\u0012\b\b\u0002\u0010%\u001a\u00020\u000f\u0012\b\b\u0002\u0010&\u001a\u00020\u0004\u0012\b\b\u0002\u0010'\u001a\u00020\u000f\u0012\b\b\u0002\u0010(\u001a\u00020\u0004\u0012\b\b\u0002\u0010)\u001a\u00020\u0004\u0012\b\b\u0002\u0010*\u001a\u00020\u0004\u0012\b\b\u0002\u0010+\u001a\u00020\u0004\u0012\b\b\u0002\u0010,\u001a\u00020\u0017\u0012\b\b\u0002\u0010-\u001a\u00020\u0017\u0012\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u001a¢\u0006\u0004\bk\u0010lJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0006\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0007\u001a\u00020\u0004HÆ\u0003J\t\u0010\b\u001a\u00020\u0004HÆ\u0003J\t\u0010\t\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000b\u001a\u00020\nHÆ\u0003J\t\u0010\f\u001a\u00020\nHÆ\u0003J\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0010\u001a\u00020\u000fHÆ\u0003J\t\u0010\u0011\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0012\u001a\u00020\u000fHÆ\u0003J\t\u0010\u0013\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0017HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0017HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u001aHÆ\u0003JÉ\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u001c\u001a\u00020\u00042\b\b\u0002\u0010\u001d\u001a\u00020\u00042\b\b\u0002\u0010\u001e\u001a\u00020\u00042\b\b\u0002\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020\u00042\b\b\u0002\u0010!\u001a\u00020\n2\b\b\u0002\u0010\"\u001a\u00020\n2\b\b\u0002\u0010#\u001a\u00020\u00042\b\b\u0002\u0010$\u001a\u00020\u00042\b\b\u0002\u0010%\u001a\u00020\u000f2\b\b\u0002\u0010&\u001a\u00020\u00042\b\b\u0002\u0010'\u001a\u00020\u000f2\b\b\u0002\u0010(\u001a\u00020\u00042\b\b\u0002\u0010)\u001a\u00020\u00042\b\b\u0002\u0010*\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020\u00042\b\b\u0002\u0010,\u001a\u00020\u00172\b\b\u0002\u0010-\u001a\u00020\u00172\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u001aHÆ\u0001J\t\u00100\u001a\u00020\u0004HÖ\u0001J\t\u00101\u001a\u00020\u000fHÖ\u0001J\u0013\u00104\u001a\u00020\u00172\b\u00103\u001a\u0004\u0018\u000102HÖ\u0003R\u0019\u0010\u001c\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108R\u0019\u0010\u001d\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b9\u00106\u001a\u0004\b:\u00108R\u0019\u0010\u001e\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b;\u00106\u001a\u0004\b<\u00108R\u0019\u0010\u001f\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b=\u00106\u001a\u0004\b>\u00108R\u0019\u0010 \u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b?\u00106\u001a\u0004\b@\u00108R\u0019\u0010!\u001a\u00020\n8\u0006@\u0006¢\u0006\f\n\u0004\bA\u0010B\u001a\u0004\bC\u0010DR\"\u0010\"\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bE\u0010B\u001a\u0004\bF\u0010D\"\u0004\bG\u0010HR\u0019\u0010#\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\bI\u00106\u001a\u0004\bJ\u00108R\"\u0010$\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bK\u00106\u001a\u0004\bL\u00108\"\u0004\bM\u0010NR\"\u0010%\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bO\u0010P\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u0019\u0010&\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\bU\u00106\u001a\u0004\bV\u00108R\u0019\u0010'\u001a\u00020\u000f8\u0006@\u0006¢\u0006\f\n\u0004\bW\u0010P\u001a\u0004\bX\u0010RR\u0019\u0010(\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\bY\u00106\u001a\u0004\bZ\u00108R\u0019\u0010)\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b[\u00106\u001a\u0004\b\\\u00108R\u0019\u0010*\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b]\u00106\u001a\u0004\b^\u00108R\u0019\u0010+\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b_\u00106\u001a\u0004\b`\u00108R\u0019\u0010,\u001a\u00020\u00178\u0006@\u0006¢\u0006\f\n\u0004\ba\u0010b\u001a\u0004\b,\u0010cR\u0019\u0010-\u001a\u00020\u00178\u0006@\u0006¢\u0006\f\n\u0004\bd\u0010b\u001a\u0004\b-\u0010cR$\u0010.\u001a\u0004\u0018\u00010\u001a8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\be\u0010f\u001a\u0004\bg\u0010h\"\u0004\bi\u0010j¨\u0006n"}, d2 = {"Lio/shipbook/shipbooksdk/Models/Login;", "Lio/shipbook/shipbooksdk/Models/BaseObj;", "Lorg/json/JSONObject;", "toJson", "", "component1", "component2", "component3", "component4", "component5", "Ljava/util/Date;", "component6", "component7", "component8", "component9", "", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "", "component17", "component18", "Lio/shipbook/shipbooksdk/Models/User;", "component19", RemoteConfigConstants.RequestFieldKey.APP_ID, "appKey", "os", "appName", "udid", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "deviceTime", "osVersion", RemoteConfigConstants.RequestFieldKey.APP_VERSION, "appVersionCode", RemoteConfigConstants.RequestFieldKey.SDK_VERSION, "sdkVersionCode", "manufacturer", "deviceModel", DeviceKey.DeviceName, "language", "isDebug", "isObfuscated", "user", Constants.COPY_TYPE, "toString", "hashCode", "", FitnessActivities.OTHER, "equals", "a", "Ljava/lang/String;", "getAppId", "()Ljava/lang/String;", "b", "getAppKey", c.f10260a, "getOs", "d", "getAppName", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getUdid", "f", "Ljava/util/Date;", "getTime", "()Ljava/util/Date;", "g", "getDeviceTime", "setDeviceTime", "(Ljava/util/Date;)V", "h", "getOsVersion", "i", "getAppVersion", "setAppVersion", "(Ljava/lang/String;)V", "j", "I", "getAppVersionCode", "()I", "setAppVersionCode", "(I)V", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "getSdkVersion", "l", "getSdkVersionCode", "m", "getManufacturer", "n", "getDeviceModel", "o", "getDeviceName", RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME, "getLanguage", RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, "Z", "()Z", RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME, "s", "Lio/shipbook/shipbooksdk/Models/User;", "getUser", "()Lio/shipbook/shipbooksdk/Models/User;", "setUser", "(Lio/shipbook/shipbooksdk/Models/User;)V", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;Ljava/util/Date;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLio/shipbook/shipbooksdk/Models/User;)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
@SuppressLint({"HardwareIds"})
/* loaded from: classes12.dex */
public final class Login implements BaseObj {
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f14032a;
    @NotNull
    public final String b;
    @NotNull
    public final String c;
    @NotNull
    public final String d;
    @NotNull
    public final String e;
    @NotNull
    public final Date f;
    @NotNull
    public Date g;
    @NotNull
    public final String h;
    @NotNull
    public String i;
    public int j;
    @NotNull
    public final String k;
    public final int l;
    @NotNull
    public final String m;
    @NotNull
    public final String n;
    @NotNull
    public final String o;
    @NotNull
    public final String p;
    public final boolean q;
    public final boolean r;
    @Nullable
    public User s;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lio/shipbook/shipbooksdk/Models/Login$Companion;", "", "Lorg/json/JSONObject;", "json", "Lio/shipbook/shipbooksdk/Models/Login;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final Login create(@NotNull JSONObject json) {
            String str;
            User user;
            Intrinsics.checkParameterIsNotNull(json, "json");
            String appId = json.getString(RemoteConfigConstants.RequestFieldKey.APP_ID);
            String appKey = json.getString("appKey");
            String os = json.getString("os");
            String appName = json.getString("appName");
            String udid = json.getString("udid");
            DateHelper dateHelper = DateHelper.INSTANCE;
            String string = json.getString(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP);
            Intrinsics.checkExpressionValueIsNotNull(string, "json.getString(\"time\")");
            Date createDateStandard = dateHelper.createDateStandard(string);
            if (createDateStandard == null) {
                Intrinsics.throwNpe();
            }
            String string2 = json.getString("deviceTime");
            Intrinsics.checkExpressionValueIsNotNull(string2, "json.getString(\"deviceTime\")");
            Date createDateStandard2 = dateHelper.createDateStandard(string2);
            if (createDateStandard2 == null) {
                Intrinsics.throwNpe();
            }
            String osVersion = json.getString("osVersion");
            String appVersion = json.getString(RemoteConfigConstants.RequestFieldKey.APP_VERSION);
            int i = json.getInt("appVersionCode");
            String sdkVersion = json.getString(RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
            int i2 = json.getInt("sdkVersionCode");
            String manufacturer = json.getString("manufacturer");
            String deviceModel = json.getString("deviceModel");
            String deviceName = json.getString(DeviceKey.DeviceName);
            String string3 = json.getString("language");
            boolean z = json.getBoolean("isDebug");
            boolean z2 = json.getBoolean("isObfuscated");
            if (json.has("user")) {
                str = "language";
                User.Companion companion = User.Companion;
                JSONObject optJSONObject = json.optJSONObject("user");
                Intrinsics.checkExpressionValueIsNotNull(optJSONObject, "json.optJSONObject(\"user\")");
                user = companion.create(optJSONObject);
            } else {
                str = "language";
                user = null;
            }
            Intrinsics.checkExpressionValueIsNotNull(appId, "appId");
            Intrinsics.checkExpressionValueIsNotNull(appKey, "appKey");
            Intrinsics.checkExpressionValueIsNotNull(os, "os");
            Intrinsics.checkExpressionValueIsNotNull(appName, "appName");
            Intrinsics.checkExpressionValueIsNotNull(udid, "udid");
            Intrinsics.checkExpressionValueIsNotNull(osVersion, "osVersion");
            Intrinsics.checkExpressionValueIsNotNull(appVersion, "appVersion");
            Intrinsics.checkExpressionValueIsNotNull(sdkVersion, "sdkVersion");
            Intrinsics.checkExpressionValueIsNotNull(manufacturer, "manufacturer");
            Intrinsics.checkExpressionValueIsNotNull(deviceModel, "deviceModel");
            Intrinsics.checkExpressionValueIsNotNull(deviceName, "deviceName");
            Intrinsics.checkExpressionValueIsNotNull(string3, str);
            return new Login(appId, appKey, os, appName, udid, createDateStandard, createDateStandard2, osVersion, appVersion, i, sdkVersion, i2, manufacturer, deviceModel, deviceName, string3, z, z2, user);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Login(@NotNull String appId, @NotNull String appKey, @NotNull String os, @NotNull String appName, @NotNull String udid, @NotNull Date time, @NotNull Date deviceTime, @NotNull String osVersion, @NotNull String appVersion, int i, @NotNull String sdkVersion, int i2, @NotNull String manufacturer, @NotNull String deviceModel, @NotNull String deviceName, @NotNull String language, boolean z, boolean z2, @Nullable User user) {
        Intrinsics.checkParameterIsNotNull(appId, "appId");
        Intrinsics.checkParameterIsNotNull(appKey, "appKey");
        Intrinsics.checkParameterIsNotNull(os, "os");
        Intrinsics.checkParameterIsNotNull(appName, "appName");
        Intrinsics.checkParameterIsNotNull(udid, "udid");
        Intrinsics.checkParameterIsNotNull(time, "time");
        Intrinsics.checkParameterIsNotNull(deviceTime, "deviceTime");
        Intrinsics.checkParameterIsNotNull(osVersion, "osVersion");
        Intrinsics.checkParameterIsNotNull(appVersion, "appVersion");
        Intrinsics.checkParameterIsNotNull(sdkVersion, "sdkVersion");
        Intrinsics.checkParameterIsNotNull(manufacturer, "manufacturer");
        Intrinsics.checkParameterIsNotNull(deviceModel, "deviceModel");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        Intrinsics.checkParameterIsNotNull(language, "language");
        this.f14032a = appId;
        this.b = appKey;
        this.c = os;
        this.d = appName;
        this.e = udid;
        this.f = time;
        this.g = deviceTime;
        this.h = osVersion;
        this.i = appVersion;
        this.j = i;
        this.k = sdkVersion;
        this.l = i2;
        this.m = manufacturer;
        this.n = deviceModel;
        this.o = deviceName;
        this.p = language;
        this.q = z;
        this.r = z2;
        this.s = user;
        if (Intrinsics.areEqual(appVersion, "")) {
            Context appContext = SessionManager.INSTANCE.getAppContext();
            if (appContext == null) {
                Intrinsics.throwNpe();
            }
            PackageInfo pInfo = appContext.getPackageManager().getPackageInfo(appContext.getPackageName(), 0);
            String str = pInfo.versionName;
            this.i = str != null ? str : "";
            if (Build.VERSION.SDK_INT < 28) {
                this.j = pInfo.versionCode;
                return;
            }
            Intrinsics.checkExpressionValueIsNotNull(pInfo, "pInfo");
            this.j = (int) pInfo.getLongVersionCode();
        }
    }

    @NotNull
    public final String component1() {
        return this.f14032a;
    }

    public final int component10() {
        return this.j;
    }

    @NotNull
    public final String component11() {
        return this.k;
    }

    public final int component12() {
        return this.l;
    }

    @NotNull
    public final String component13() {
        return this.m;
    }

    @NotNull
    public final String component14() {
        return this.n;
    }

    @NotNull
    public final String component15() {
        return this.o;
    }

    @NotNull
    public final String component16() {
        return this.p;
    }

    public final boolean component17() {
        return this.q;
    }

    public final boolean component18() {
        return this.r;
    }

    @Nullable
    public final User component19() {
        return this.s;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @NotNull
    public final String component3() {
        return this.c;
    }

    @NotNull
    public final String component4() {
        return this.d;
    }

    @NotNull
    public final String component5() {
        return this.e;
    }

    @NotNull
    public final Date component6() {
        return this.f;
    }

    @NotNull
    public final Date component7() {
        return this.g;
    }

    @NotNull
    public final String component8() {
        return this.h;
    }

    @NotNull
    public final String component9() {
        return this.i;
    }

    @NotNull
    public final Login copy(@NotNull String appId, @NotNull String appKey, @NotNull String os, @NotNull String appName, @NotNull String udid, @NotNull Date time, @NotNull Date deviceTime, @NotNull String osVersion, @NotNull String appVersion, int i, @NotNull String sdkVersion, int i2, @NotNull String manufacturer, @NotNull String deviceModel, @NotNull String deviceName, @NotNull String language, boolean z, boolean z2, @Nullable User user) {
        Intrinsics.checkParameterIsNotNull(appId, "appId");
        Intrinsics.checkParameterIsNotNull(appKey, "appKey");
        Intrinsics.checkParameterIsNotNull(os, "os");
        Intrinsics.checkParameterIsNotNull(appName, "appName");
        Intrinsics.checkParameterIsNotNull(udid, "udid");
        Intrinsics.checkParameterIsNotNull(time, "time");
        Intrinsics.checkParameterIsNotNull(deviceTime, "deviceTime");
        Intrinsics.checkParameterIsNotNull(osVersion, "osVersion");
        Intrinsics.checkParameterIsNotNull(appVersion, "appVersion");
        Intrinsics.checkParameterIsNotNull(sdkVersion, "sdkVersion");
        Intrinsics.checkParameterIsNotNull(manufacturer, "manufacturer");
        Intrinsics.checkParameterIsNotNull(deviceModel, "deviceModel");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        Intrinsics.checkParameterIsNotNull(language, "language");
        return new Login(appId, appKey, os, appName, udid, time, deviceTime, osVersion, appVersion, i, sdkVersion, i2, manufacturer, deviceModel, deviceName, language, z, z2, user);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Login) {
                Login login = (Login) obj;
                if (Intrinsics.areEqual(this.f14032a, login.f14032a) && Intrinsics.areEqual(this.b, login.b) && Intrinsics.areEqual(this.c, login.c) && Intrinsics.areEqual(this.d, login.d) && Intrinsics.areEqual(this.e, login.e) && Intrinsics.areEqual(this.f, login.f) && Intrinsics.areEqual(this.g, login.g) && Intrinsics.areEqual(this.h, login.h) && Intrinsics.areEqual(this.i, login.i)) {
                    if ((this.j == login.j) && Intrinsics.areEqual(this.k, login.k)) {
                        if ((this.l == login.l) && Intrinsics.areEqual(this.m, login.m) && Intrinsics.areEqual(this.n, login.n) && Intrinsics.areEqual(this.o, login.o) && Intrinsics.areEqual(this.p, login.p)) {
                            if (this.q == login.q) {
                                if (!(this.r == login.r) || !Intrinsics.areEqual(this.s, login.s)) {
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final String getAppId() {
        return this.f14032a;
    }

    @NotNull
    public final String getAppKey() {
        return this.b;
    }

    @NotNull
    public final String getAppName() {
        return this.d;
    }

    @NotNull
    public final String getAppVersion() {
        return this.i;
    }

    public final int getAppVersionCode() {
        return this.j;
    }

    @NotNull
    public final String getDeviceModel() {
        return this.n;
    }

    @NotNull
    public final String getDeviceName() {
        return this.o;
    }

    @NotNull
    public final Date getDeviceTime() {
        return this.g;
    }

    @NotNull
    public final String getLanguage() {
        return this.p;
    }

    @NotNull
    public final String getManufacturer() {
        return this.m;
    }

    @NotNull
    public final String getOs() {
        return this.c;
    }

    @NotNull
    public final String getOsVersion() {
        return this.h;
    }

    @NotNull
    public final String getSdkVersion() {
        return this.k;
    }

    public final int getSdkVersionCode() {
        return this.l;
    }

    @NotNull
    public final Date getTime() {
        return this.f;
    }

    @NotNull
    public final String getUdid() {
        return this.e;
    }

    @Nullable
    public final User getUser() {
        return this.s;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.f14032a;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.c;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.d;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.e;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        Date date = this.f;
        int hashCode6 = (hashCode5 + (date != null ? date.hashCode() : 0)) * 31;
        Date date2 = this.g;
        int hashCode7 = (hashCode6 + (date2 != null ? date2.hashCode() : 0)) * 31;
        String str6 = this.h;
        int hashCode8 = (hashCode7 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.i;
        int hashCode9 = (((hashCode8 + (str7 != null ? str7.hashCode() : 0)) * 31) + this.j) * 31;
        String str8 = this.k;
        int hashCode10 = (((hashCode9 + (str8 != null ? str8.hashCode() : 0)) * 31) + this.l) * 31;
        String str9 = this.m;
        int hashCode11 = (hashCode10 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.n;
        int hashCode12 = (hashCode11 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.o;
        int hashCode13 = (hashCode12 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.p;
        int hashCode14 = (hashCode13 + (str12 != null ? str12.hashCode() : 0)) * 31;
        boolean z = this.q;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode14 + i) * 31;
        boolean z2 = this.r;
        int i3 = (i2 + (z2 ? 1 : z2 ? 1 : 0)) * 31;
        User user = this.s;
        return i3 + (user != null ? user.hashCode() : 0);
    }

    public final boolean isDebug() {
        return this.q;
    }

    public final boolean isObfuscated() {
        return this.r;
    }

    public final void setAppVersion(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.i = str;
    }

    public final void setAppVersionCode(int i) {
        this.j = i;
    }

    public final void setDeviceTime(@NotNull Date date) {
        Intrinsics.checkParameterIsNotNull(date, "<set-?>");
        this.g = date;
    }

    public final void setUser(@Nullable User user) {
        this.s = user;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseObj
    @NotNull
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(RemoteConfigConstants.RequestFieldKey.APP_ID, this.f14032a);
        jSONObject.put("appKey", this.b);
        jSONObject.put("os", this.c);
        jSONObject.put("appName", this.d);
        jSONObject.put("udid", this.e);
        jSONObject.put(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, DateHelperKt.toStandardString(this.f));
        jSONObject.put("deviceTime", DateHelperKt.toStandardString(this.g));
        jSONObject.put("osVersion", this.h);
        jSONObject.put(RemoteConfigConstants.RequestFieldKey.APP_VERSION, this.i);
        jSONObject.put("appVersionCode", this.j);
        jSONObject.put(RemoteConfigConstants.RequestFieldKey.SDK_VERSION, this.k);
        jSONObject.put("sdkVersionCode", this.l);
        jSONObject.put("manufacturer", this.m);
        jSONObject.put("deviceModel", this.n);
        jSONObject.put(DeviceKey.DeviceName, this.o);
        jSONObject.put("language", this.p);
        jSONObject.put("isDebug", this.q);
        jSONObject.put("isObfuscated", this.r);
        User user = this.s;
        jSONObject.putOpt("user", user != null ? user.toJson() : null);
        return jSONObject;
    }

    @NotNull
    public String toString() {
        return "Login(appId=" + this.f14032a + ", appKey=" + this.b + ", os=" + this.c + ", appName=" + this.d + ", udid=" + this.e + ", time=" + this.f + ", deviceTime=" + this.g + ", osVersion=" + this.h + ", appVersion=" + this.i + ", appVersionCode=" + this.j + ", sdkVersion=" + this.k + ", sdkVersionCode=" + this.l + ", manufacturer=" + this.m + ", deviceModel=" + this.n + ", deviceName=" + this.o + ", language=" + this.p + ", isDebug=" + this.q + ", isObfuscated=" + this.r + ", user=" + this.s + ")";
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ Login(java.lang.String r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, java.util.Date r28, java.util.Date r29, java.lang.String r30, java.lang.String r31, int r32, java.lang.String r33, int r34, java.lang.String r35, java.lang.String r36, java.lang.String r37, java.lang.String r38, boolean r39, boolean r40, io.shipbook.shipbooksdk.Models.User r41, int r42, kotlin.jvm.internal.DefaultConstructorMarker r43) {
        /*
            Method dump skipped, instructions count: 300
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.shipbook.shipbooksdk.Models.Login.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.lang.String, java.lang.String, int, java.lang.String, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, boolean, io.shipbook.shipbooksdk.Models.User, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
