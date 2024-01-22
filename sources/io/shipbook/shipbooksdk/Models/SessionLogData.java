package io.shipbook.shipbooksdk.Models;

import com.clevertap.android.sdk.Constants;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.material.color.c;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mappls.android.lms.MapplsLMSDbAdapter;
import io.shipbook.shipbooksdk.Models.BaseLog;
import io.shipbook.shipbooksdk.Models.Login;
import io.shipbook.shipbooksdk.Models.User;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001d\b\u0080\b\u0018\u0000 32\u00020\u0001:\u00013B;\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b1\u00102J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u000b\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\t\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003J=\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0001J\t\u0010\u0012\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0013HÖ\u0001J\u0013\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015HÖ\u0003R$\u0010\r\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010\u000e\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R$\u0010\u000f\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R(\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100¨\u00064"}, d2 = {"Lio/shipbook/shipbooksdk/Models/SessionLogData;", "Lio/shipbook/shipbooksdk/Models/BaseObj;", "Lorg/json/JSONObject;", "toJson", "", "component1", "Lio/shipbook/shipbooksdk/Models/Login;", "component2", "Lio/shipbook/shipbooksdk/Models/User;", "component3", "", "Lio/shipbook/shipbooksdk/Models/BaseLog;", "component4", MapplsLMSDbAdapter.KEY_TOKEN, FirebaseAnalytics.Event.LOGIN, "user", "logs", Constants.COPY_TYPE, "toString", "", "hashCode", "", FitnessActivities.OTHER, "", "equals", "a", "Ljava/lang/String;", "getToken", "()Ljava/lang/String;", "setToken", "(Ljava/lang/String;)V", "b", "Lio/shipbook/shipbooksdk/Models/Login;", "getLogin", "()Lio/shipbook/shipbooksdk/Models/Login;", "setLogin", "(Lio/shipbook/shipbooksdk/Models/Login;)V", c.f10260a, "Lio/shipbook/shipbooksdk/Models/User;", "getUser", "()Lio/shipbook/shipbooksdk/Models/User;", "setUser", "(Lio/shipbook/shipbooksdk/Models/User;)V", "d", "Ljava/util/List;", "getLogs", "()Ljava/util/List;", "setLogs", "(Ljava/util/List;)V", "<init>", "(Ljava/lang/String;Lio/shipbook/shipbooksdk/Models/Login;Lio/shipbook/shipbooksdk/Models/User;Ljava/util/List;)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class SessionLogData implements BaseObj {
    public static final Companion Companion = new Companion(null);
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f14037a;
    @Nullable
    public Login b;
    @Nullable
    public User c;
    @NotNull
    public List<BaseLog> d;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lio/shipbook/shipbooksdk/Models/SessionLogData$Companion;", "", "Lorg/json/JSONObject;", "json", "Lio/shipbook/shipbooksdk/Models/SessionLogData;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final SessionLogData create(@NotNull JSONObject json) {
            Login login;
            Intrinsics.checkParameterIsNotNull(json, "json");
            String optString = json.optString(MapplsLMSDbAdapter.KEY_TOKEN);
            User user = null;
            if (json.has(FirebaseAnalytics.Event.LOGIN)) {
                Login.Companion companion = Login.Companion;
                JSONObject jSONObject = json.getJSONObject(FirebaseAnalytics.Event.LOGIN);
                Intrinsics.checkExpressionValueIsNotNull(jSONObject, "json.getJSONObject(\"login\")");
                login = companion.create(jSONObject);
            } else {
                login = null;
            }
            if (json.has("user")) {
                User.Companion companion2 = User.Companion;
                JSONObject jSONObject2 = json.getJSONObject("user");
                Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "json.getJSONObject(\"user\")");
                user = companion2.create(jSONObject2);
            }
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = json.getJSONArray("logs");
            int i = 0;
            int length = jSONArray.length() - 1;
            if (length >= 0) {
                while (true) {
                    BaseLog.Companion companion3 = BaseLog.Companion;
                    JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                    Intrinsics.checkExpressionValueIsNotNull(jSONObject3, "logsArray.getJSONObject(i)");
                    arrayList.add(companion3.create(jSONObject3));
                    if (i == length) {
                        break;
                    }
                    i++;
                }
            }
            return new SessionLogData(optString, login, user, arrayList);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SessionLogData() {
        this(null, null, null, null, 15, null);
    }

    public SessionLogData(@Nullable String str, @Nullable Login login, @Nullable User user, @NotNull List<BaseLog> logs) {
        Intrinsics.checkParameterIsNotNull(logs, "logs");
        this.f14037a = str;
        this.b = login;
        this.c = user;
        this.d = logs;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static /* synthetic */ SessionLogData copy$default(SessionLogData sessionLogData, String str, Login login, User user, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sessionLogData.f14037a;
        }
        if ((i & 2) != 0) {
            login = sessionLogData.b;
        }
        if ((i & 4) != 0) {
            user = sessionLogData.c;
        }
        if ((i & 8) != 0) {
            list = sessionLogData.d;
        }
        return sessionLogData.copy(str, login, user, list);
    }

    @Nullable
    public final String component1() {
        return this.f14037a;
    }

    @Nullable
    public final Login component2() {
        return this.b;
    }

    @Nullable
    public final User component3() {
        return this.c;
    }

    @NotNull
    public final List<BaseLog> component4() {
        return this.d;
    }

    @NotNull
    public final SessionLogData copy(@Nullable String str, @Nullable Login login, @Nullable User user, @NotNull List<BaseLog> logs) {
        Intrinsics.checkParameterIsNotNull(logs, "logs");
        return new SessionLogData(str, login, user, logs);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof SessionLogData) {
                SessionLogData sessionLogData = (SessionLogData) obj;
                return Intrinsics.areEqual(this.f14037a, sessionLogData.f14037a) && Intrinsics.areEqual(this.b, sessionLogData.b) && Intrinsics.areEqual(this.c, sessionLogData.c) && Intrinsics.areEqual(this.d, sessionLogData.d);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final Login getLogin() {
        return this.b;
    }

    @NotNull
    public final List<BaseLog> getLogs() {
        return this.d;
    }

    @Nullable
    public final String getToken() {
        return this.f14037a;
    }

    @Nullable
    public final User getUser() {
        return this.c;
    }

    public int hashCode() {
        String str = this.f14037a;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Login login = this.b;
        int hashCode2 = (hashCode + (login != null ? login.hashCode() : 0)) * 31;
        User user = this.c;
        int hashCode3 = (hashCode2 + (user != null ? user.hashCode() : 0)) * 31;
        List<BaseLog> list = this.d;
        return hashCode3 + (list != null ? list.hashCode() : 0);
    }

    public final void setLogin(@Nullable Login login) {
        this.b = login;
    }

    public final void setLogs(@NotNull List<BaseLog> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.d = list;
    }

    public final void setToken(@Nullable String str) {
        this.f14037a = str;
    }

    public final void setUser(@Nullable User user) {
        this.c = user;
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseObj
    @NotNull
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt(MapplsLMSDbAdapter.KEY_TOKEN, this.f14037a);
        Login login = this.b;
        if (login != null) {
            jSONObject.put(FirebaseAnalytics.Event.LOGIN, login.toJson());
        }
        User user = this.c;
        if (user != null) {
            jSONObject.put("user", user.toJson());
        }
        JSONArray jSONArray = new JSONArray();
        for (BaseLog baseLog : this.d) {
            jSONArray.put(baseLog.toJson());
        }
        jSONObject.put("logs", jSONArray);
        return jSONObject;
    }

    @NotNull
    public String toString() {
        return "SessionLogData(token=" + this.f14037a + ", login=" + this.b + ", user=" + this.c + ", logs=" + this.d + ")";
    }

    public /* synthetic */ SessionLogData(String str, Login login, User user, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : login, (i & 4) != 0 ? null : user, (i & 8) != 0 ? new ArrayList() : list);
    }
}
