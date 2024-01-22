package io.shipbook.shipbooksdk.Models;

import com.coveiot.android.tappy.utils.Constants;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.material.color.c;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\b\u0080\b\u0018\u0000 ,2\u00020\u0001:\u0001,BW\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0004\u0012\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n¢\u0006\u0004\b*\u0010+J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0006\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0017\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\nHÆ\u0003J[\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00042\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\nHÆ\u0001J\t\u0010\u0013\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0014HÖ\u0001J\u0013\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016HÖ\u0003R\u0019\u0010\f\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010\r\u001a\u0004\u0018\u00010\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001b\u001a\u0004\b\u001f\u0010\u001dR\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u00048\u0006@\u0006¢\u0006\f\n\u0004\b \u0010\u001b\u001a\u0004\b!\u0010\u001dR\u001b\u0010\u000f\u001a\u0004\u0018\u00010\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\"\u0010\u001b\u001a\u0004\b#\u0010\u001dR\u001b\u0010\u0010\u001a\u0004\u0018\u00010\u00048\u0006@\u0006¢\u0006\f\n\u0004\b$\u0010\u001b\u001a\u0004\b%\u0010\u001dR'\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n8\u0006@\u0006¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)¨\u0006-"}, d2 = {"Lio/shipbook/shipbooksdk/Models/User;", "Lio/shipbook/shipbooksdk/Models/BaseObj;", "Lorg/json/JSONObject;", "toJson", "", "component1", "component2", "component3", "component4", "component5", "", "component6", Constants.END_USER_GLOBAL_ID, "userName", "fullName", "email", "phoneNumber", "additionalInfo", com.clevertap.android.sdk.Constants.COPY_TYPE, "toString", "", "hashCode", "", FitnessActivities.OTHER, "", "equals", "a", "Ljava/lang/String;", "getUserId", "()Ljava/lang/String;", "b", "getUserName", c.f10260a, "getFullName", "d", "getEmail", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getPhoneNumber", "f", "Ljava/util/Map;", "getAdditionalInfo", "()Ljava/util/Map;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class User implements BaseObj {
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f14039a;
    @Nullable
    public final String b;
    @Nullable
    public final String c;
    @Nullable
    public final String d;
    @Nullable
    public final String e;
    @Nullable
    public final Map<String, String> f;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lio/shipbook/shipbooksdk/Models/User$Companion;", "", "Lorg/json/JSONObject;", "json", "Lio/shipbook/shipbooksdk/Models/User;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final User create(@NotNull JSONObject json) {
            LinkedHashMap linkedHashMap;
            Intrinsics.checkParameterIsNotNull(json, "json");
            String userId = json.getString(Constants.END_USER_GLOBAL_ID);
            String optString = json.optString("userName", null);
            String optString2 = json.optString("fullName", null);
            String optString3 = json.optString("email", null);
            String optString4 = json.optString("phoneNumber", null);
            JSONObject optJSONObject = json.optJSONObject("additionalInfo");
            if (optJSONObject != null) {
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                Iterator<String> keys = optJSONObject.keys();
                Intrinsics.checkExpressionValueIsNotNull(keys, "additionalInfoObject.keys()");
                while (keys.hasNext()) {
                    String it = keys.next();
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    String string = optJSONObject.getString(it);
                    Intrinsics.checkExpressionValueIsNotNull(string, "additionalInfoObject.getString(it)");
                    linkedHashMap2.put(it, string);
                }
                linkedHashMap = linkedHashMap2;
            } else {
                linkedHashMap = null;
            }
            Intrinsics.checkExpressionValueIsNotNull(userId, "userId");
            return new User(userId, optString, optString2, optString3, optString4, linkedHashMap);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public User(@NotNull String userId, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable Map<String, String> map) {
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        this.f14039a = userId;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = map;
    }

    @NotNull
    public static /* synthetic */ User copy$default(User user, String str, String str2, String str3, String str4, String str5, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = user.f14039a;
        }
        if ((i & 2) != 0) {
            str2 = user.b;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = user.c;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = user.d;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = user.e;
        }
        String str9 = str5;
        Map<String, String> map2 = map;
        if ((i & 32) != 0) {
            map2 = user.f;
        }
        return user.copy(str, str6, str7, str8, str9, map2);
    }

    @NotNull
    public final String component1() {
        return this.f14039a;
    }

    @Nullable
    public final String component2() {
        return this.b;
    }

    @Nullable
    public final String component3() {
        return this.c;
    }

    @Nullable
    public final String component4() {
        return this.d;
    }

    @Nullable
    public final String component5() {
        return this.e;
    }

    @Nullable
    public final Map<String, String> component6() {
        return this.f;
    }

    @NotNull
    public final User copy(@NotNull String userId, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable Map<String, String> map) {
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        return new User(userId, str, str2, str3, str4, map);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof User) {
                User user = (User) obj;
                return Intrinsics.areEqual(this.f14039a, user.f14039a) && Intrinsics.areEqual(this.b, user.b) && Intrinsics.areEqual(this.c, user.c) && Intrinsics.areEqual(this.d, user.d) && Intrinsics.areEqual(this.e, user.e) && Intrinsics.areEqual(this.f, user.f);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final Map<String, String> getAdditionalInfo() {
        return this.f;
    }

    @Nullable
    public final String getEmail() {
        return this.d;
    }

    @Nullable
    public final String getFullName() {
        return this.c;
    }

    @Nullable
    public final String getPhoneNumber() {
        return this.e;
    }

    @NotNull
    public final String getUserId() {
        return this.f14039a;
    }

    @Nullable
    public final String getUserName() {
        return this.b;
    }

    public int hashCode() {
        String str = this.f14039a;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.c;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.d;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.e;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        Map<String, String> map = this.f;
        return hashCode5 + (map != null ? map.hashCode() : 0);
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseObj
    @NotNull
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Constants.END_USER_GLOBAL_ID, this.f14039a);
        jSONObject.putOpt("userName", this.b);
        jSONObject.putOpt("fullName", this.c);
        jSONObject.putOpt("email", this.d);
        jSONObject.putOpt("phoneNumber", this.e);
        if (this.f != null) {
            JSONObject jSONObject2 = new JSONObject();
            Iterator<T> it = this.f.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                jSONObject2.put((String) entry.getKey(), entry.getValue());
            }
            jSONObject.putOpt("additionalInfo", jSONObject2);
        }
        return jSONObject;
    }

    @NotNull
    public String toString() {
        return "User(userId=" + this.f14039a + ", userName=" + this.b + ", fullName=" + this.c + ", email=" + this.d + ", phoneNumber=" + this.e + ", additionalInfo=" + this.f + ")";
    }

    public /* synthetic */ User(String str, String str2, String str3, String str4, String str5, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) == 0 ? map : null);
    }
}
