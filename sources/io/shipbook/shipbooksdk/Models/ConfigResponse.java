package io.shipbook.shipbooksdk.Models;

import com.clevertap.android.sdk.Constants;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.material.color.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0014\b\u0080\b\u0018\u0000 &2\u00020\u0001:\u0003'&(B7\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t\u0012\b\b\u0002\u0010\u000f\u001a\u00020\t¢\u0006\u0004\b$\u0010%J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u000f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004HÆ\u0003J\t\u0010\n\u001a\u00020\tHÆ\u0003J\t\u0010\u000b\u001a\u00020\tHÆ\u0003J=\u0010\u0010\u001a\u00020\u00002\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\tHÆ\u0001J\t\u0010\u0012\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0013HÖ\u0001J\u0013\u0010\u0017\u001a\u00020\t2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015HÖ\u0003R\u001f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0019\u001a\u0004\b\u001d\u0010\u001bR\u0019\u0010\u000e\u001a\u00020\t8\u0006@\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u0019\u0010\u000f\u001a\u00020\t8\u0006@\u0006¢\u0006\f\n\u0004\b\"\u0010\u001f\u001a\u0004\b#\u0010!¨\u0006)"}, d2 = {"Lio/shipbook/shipbooksdk/Models/ConfigResponse;", "Lio/shipbook/shipbooksdk/Models/BaseObj;", "Lorg/json/JSONObject;", "toJson", "", "Lio/shipbook/shipbooksdk/Models/ConfigResponse$AppenderResponse;", "component1", "Lio/shipbook/shipbooksdk/Models/ConfigResponse$LoggerResponse;", "component2", "", "component3", "component4", "appenders", "loggers", "eventLoggingDisabled", "exceptionReportDisabled", Constants.COPY_TYPE, "", "toString", "", "hashCode", "", FitnessActivities.OTHER, "equals", "a", "Ljava/util/List;", "getAppenders", "()Ljava/util/List;", "b", "getLoggers", c.f10260a, "Z", "getEventLoggingDisabled", "()Z", "d", "getExceptionReportDisabled", "<init>", "(Ljava/util/List;Ljava/util/List;ZZ)V", "Companion", "AppenderResponse", "LoggerResponse", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class ConfigResponse implements BaseObj {
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final List<AppenderResponse> f14028a;
    @NotNull
    public final List<LoggerResponse> b;
    public final boolean c;
    public final boolean d;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0086\b\u0018\u0000 !2\u00020\u0001:\u0001!B5\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u001c\b\u0002\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0007j\u0004\u0018\u0001`\b¢\u0006\u0004\b\u001f\u0010 J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0006\u001a\u00020\u0004HÆ\u0003J\u001d\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0007j\u0004\u0018\u0001`\bHÆ\u0003J;\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\u001c\b\u0002\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0007j\u0004\u0018\u0001`\bHÆ\u0001J\t\u0010\u000e\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0010\u001a\u00020\u000fHÖ\u0001J\u0013\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003R\u0019\u0010\n\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u000b\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u001a\u0010\u0018R-\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0007j\u0004\u0018\u0001`\b8\u0006@\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e¨\u0006\""}, d2 = {"Lio/shipbook/shipbooksdk/Models/ConfigResponse$AppenderResponse;", "Lio/shipbook/shipbooksdk/Models/BaseObj;", "Lorg/json/JSONObject;", "toJson", "", "component1", "component2", "", "Lio/shipbook/shipbooksdk/Appenders/Config;", "component3", "type", AppMeasurementSdk.ConditionalUserProperty.NAME, Constants.KEY_CONFIG, Constants.COPY_TYPE, "toString", "", "hashCode", "", FitnessActivities.OTHER, "", "equals", "a", "Ljava/lang/String;", "getType", "()Ljava/lang/String;", "b", "getName", c.f10260a, "Ljava/util/Map;", "getConfig", "()Ljava/util/Map;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class AppenderResponse implements BaseObj {
        public static final Companion Companion = new Companion(null);
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final String f14029a;
        @NotNull
        public final String b;
        @Nullable
        public final Map<String, String> c;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lio/shipbook/shipbooksdk/Models/ConfigResponse$AppenderResponse$Companion;", "", "Lorg/json/JSONObject;", "json", "Lio/shipbook/shipbooksdk/Models/ConfigResponse$AppenderResponse;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
        /* loaded from: classes12.dex */
        public static final class Companion {
            public Companion() {
            }

            @NotNull
            public final AppenderResponse create(@NotNull JSONObject json) {
                LinkedHashMap linkedHashMap;
                Intrinsics.checkParameterIsNotNull(json, "json");
                String string = json.getString("type");
                Intrinsics.checkExpressionValueIsNotNull(string, "json.getString(\"type\")");
                String string2 = json.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                Intrinsics.checkExpressionValueIsNotNull(string2, "json.getString(\"name\")");
                if (json.has(Constants.KEY_CONFIG)) {
                    linkedHashMap = new LinkedHashMap();
                    JSONObject jSONObject = json.getJSONObject(Constants.KEY_CONFIG);
                    Iterator<String> keys = jSONObject.keys();
                    Intrinsics.checkExpressionValueIsNotNull(keys, "configObject.keys()");
                    while (keys.hasNext()) {
                        String it = keys.next();
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        String string3 = jSONObject.getString(it);
                        Intrinsics.checkExpressionValueIsNotNull(string3, "configObject.getString(it)");
                        linkedHashMap.put(it, string3);
                    }
                } else {
                    linkedHashMap = null;
                }
                return new AppenderResponse(string, string2, linkedHashMap);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public AppenderResponse(@NotNull String type, @NotNull String name, @Nullable Map<String, String> map) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(name, "name");
            this.f14029a = type;
            this.b = name;
            this.c = map;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NotNull
        public static /* synthetic */ AppenderResponse copy$default(AppenderResponse appenderResponse, String str, String str2, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = appenderResponse.f14029a;
            }
            if ((i & 2) != 0) {
                str2 = appenderResponse.b;
            }
            if ((i & 4) != 0) {
                map = appenderResponse.c;
            }
            return appenderResponse.copy(str, str2, map);
        }

        @NotNull
        public final String component1() {
            return this.f14029a;
        }

        @NotNull
        public final String component2() {
            return this.b;
        }

        @Nullable
        public final Map<String, String> component3() {
            return this.c;
        }

        @NotNull
        public final AppenderResponse copy(@NotNull String type, @NotNull String name, @Nullable Map<String, String> map) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(name, "name");
            return new AppenderResponse(type, name, map);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof AppenderResponse) {
                    AppenderResponse appenderResponse = (AppenderResponse) obj;
                    return Intrinsics.areEqual(this.f14029a, appenderResponse.f14029a) && Intrinsics.areEqual(this.b, appenderResponse.b) && Intrinsics.areEqual(this.c, appenderResponse.c);
                }
                return false;
            }
            return true;
        }

        @Nullable
        public final Map<String, String> getConfig() {
            return this.c;
        }

        @NotNull
        public final String getName() {
            return this.b;
        }

        @NotNull
        public final String getType() {
            return this.f14029a;
        }

        public int hashCode() {
            String str = this.f14029a;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.b;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            Map<String, String> map = this.c;
            return hashCode2 + (map != null ? map.hashCode() : 0);
        }

        @Override // io.shipbook.shipbooksdk.Models.BaseObj
        @NotNull
        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", this.f14029a);
            jSONObject.put(AppMeasurementSdk.ConditionalUserProperty.NAME, this.b);
            Map<String, String> map = this.c;
            if (map != null) {
                JSONObject jSONObject2 = new JSONObject();
                Iterator<T> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    jSONObject2.put((String) entry.getKey(), entry.getValue());
                }
                jSONObject.put(Constants.KEY_CONFIG, jSONObject2);
            }
            return jSONObject;
        }

        @NotNull
        public String toString() {
            return "AppenderResponse(type=" + this.f14029a + ", name=" + this.b + ", config=" + this.c + ")";
        }

        public /* synthetic */ AppenderResponse(String str, String str2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i & 4) != 0 ? null : map);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lio/shipbook/shipbooksdk/Models/ConfigResponse$Companion;", "", "Lorg/json/JSONObject;", "json", "Lio/shipbook/shipbooksdk/Models/ConfigResponse;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final ConfigResponse create(@NotNull JSONObject json) {
            Intrinsics.checkParameterIsNotNull(json, "json");
            JSONArray jSONArray = json.getJSONArray("appenders");
            ArrayList arrayList = new ArrayList(jSONArray.length());
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                AppenderResponse.Companion companion = AppenderResponse.Companion;
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                Intrinsics.checkExpressionValueIsNotNull(jSONObject, "appendersArray.getJSONObject(i)");
                arrayList.add(companion.create(jSONObject));
            }
            JSONArray jSONArray2 = json.getJSONArray("loggers");
            ArrayList arrayList2 = new ArrayList(jSONArray2.length());
            int length2 = jSONArray2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                LoggerResponse.Companion companion2 = LoggerResponse.Companion;
                JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "loggersArray.getJSONObject(i)");
                arrayList2.add(companion2.create(jSONObject2));
            }
            return new ConfigResponse(arrayList, arrayList2, json.optBoolean("eventLoggingDisabled"), json.optBoolean("exceptionReportDisabled"));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0086\b\u0018\u0000 $2\u00020\u0001:\u0001$B)\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0004¢\u0006\u0004\b\"\u0010#J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\t\u0010\u0005\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0007\u001a\u00020\u0006HÆ\u0003J\t\u0010\b\u001a\u00020\u0006HÆ\u0003J\t\u0010\t\u001a\u00020\u0004HÆ\u0003J1\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u0004HÆ\u0001J\t\u0010\u000f\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R\u0019\u0010\n\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\u000b\u001a\u00020\u00068\u0006@\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\f\u001a\u00020\u00068\u0006@\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001b\u001a\u0004\b\u001f\u0010\u001dR\u0019\u0010\r\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b \u0010\u0017\u001a\u0004\b!\u0010\u0019¨\u0006%"}, d2 = {"Lio/shipbook/shipbooksdk/Models/ConfigResponse$LoggerResponse;", "Lio/shipbook/shipbooksdk/Models/BaseObj;", "Lorg/json/JSONObject;", "toJson", "", "component1", "Lio/shipbook/shipbooksdk/Models/Severity;", "component2", "component3", "component4", AppMeasurementSdk.ConditionalUserProperty.NAME, "severity", "callStackSeverity", "appenderRef", Constants.COPY_TYPE, "toString", "", "hashCode", "", FitnessActivities.OTHER, "", "equals", "a", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "b", "Lio/shipbook/shipbooksdk/Models/Severity;", "getSeverity", "()Lio/shipbook/shipbooksdk/Models/Severity;", c.f10260a, "getCallStackSeverity", "d", "getAppenderRef", "<init>", "(Ljava/lang/String;Lio/shipbook/shipbooksdk/Models/Severity;Lio/shipbook/shipbooksdk/Models/Severity;Ljava/lang/String;)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class LoggerResponse implements BaseObj {
        public static final Companion Companion = new Companion(null);
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final String f14030a;
        @NotNull
        public final Severity b;
        @NotNull
        public final Severity c;
        @NotNull
        public final String d;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lio/shipbook/shipbooksdk/Models/ConfigResponse$LoggerResponse$Companion;", "", "Lorg/json/JSONObject;", "json", "Lio/shipbook/shipbooksdk/Models/ConfigResponse$LoggerResponse;", "create", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
        /* loaded from: classes12.dex */
        public static final class Companion {
            public Companion() {
            }

            @NotNull
            public final LoggerResponse create(@NotNull JSONObject json) {
                Severity severity;
                Intrinsics.checkParameterIsNotNull(json, "json");
                String name = json.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                String string = json.getString("severity");
                Intrinsics.checkExpressionValueIsNotNull(string, "json.getString(\"severity\")");
                Severity valueOf = Severity.valueOf(string);
                if (json.has("callStackSeverity")) {
                    String string2 = json.getString("callStackSeverity");
                    Intrinsics.checkExpressionValueIsNotNull(string2, "json.getString(\"callStackSeverity\")");
                    severity = Severity.valueOf(string2);
                } else {
                    severity = Severity.Off;
                }
                String appenderRef = json.getString("appenderRef");
                Intrinsics.checkExpressionValueIsNotNull(name, "name");
                Intrinsics.checkExpressionValueIsNotNull(appenderRef, "appenderRef");
                return new LoggerResponse(name, valueOf, severity, appenderRef);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public LoggerResponse(@NotNull String name, @NotNull Severity severity, @NotNull Severity callStackSeverity, @NotNull String appenderRef) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(severity, "severity");
            Intrinsics.checkParameterIsNotNull(callStackSeverity, "callStackSeverity");
            Intrinsics.checkParameterIsNotNull(appenderRef, "appenderRef");
            this.f14030a = name;
            this.b = severity;
            this.c = callStackSeverity;
            this.d = appenderRef;
        }

        @NotNull
        public static /* synthetic */ LoggerResponse copy$default(LoggerResponse loggerResponse, String str, Severity severity, Severity severity2, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = loggerResponse.f14030a;
            }
            if ((i & 2) != 0) {
                severity = loggerResponse.b;
            }
            if ((i & 4) != 0) {
                severity2 = loggerResponse.c;
            }
            if ((i & 8) != 0) {
                str2 = loggerResponse.d;
            }
            return loggerResponse.copy(str, severity, severity2, str2);
        }

        @NotNull
        public final String component1() {
            return this.f14030a;
        }

        @NotNull
        public final Severity component2() {
            return this.b;
        }

        @NotNull
        public final Severity component3() {
            return this.c;
        }

        @NotNull
        public final String component4() {
            return this.d;
        }

        @NotNull
        public final LoggerResponse copy(@NotNull String name, @NotNull Severity severity, @NotNull Severity callStackSeverity, @NotNull String appenderRef) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(severity, "severity");
            Intrinsics.checkParameterIsNotNull(callStackSeverity, "callStackSeverity");
            Intrinsics.checkParameterIsNotNull(appenderRef, "appenderRef");
            return new LoggerResponse(name, severity, callStackSeverity, appenderRef);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof LoggerResponse) {
                    LoggerResponse loggerResponse = (LoggerResponse) obj;
                    return Intrinsics.areEqual(this.f14030a, loggerResponse.f14030a) && Intrinsics.areEqual(this.b, loggerResponse.b) && Intrinsics.areEqual(this.c, loggerResponse.c) && Intrinsics.areEqual(this.d, loggerResponse.d);
                }
                return false;
            }
            return true;
        }

        @NotNull
        public final String getAppenderRef() {
            return this.d;
        }

        @NotNull
        public final Severity getCallStackSeverity() {
            return this.c;
        }

        @NotNull
        public final String getName() {
            return this.f14030a;
        }

        @NotNull
        public final Severity getSeverity() {
            return this.b;
        }

        public int hashCode() {
            String str = this.f14030a;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            Severity severity = this.b;
            int hashCode2 = (hashCode + (severity != null ? severity.hashCode() : 0)) * 31;
            Severity severity2 = this.c;
            int hashCode3 = (hashCode2 + (severity2 != null ? severity2.hashCode() : 0)) * 31;
            String str2 = this.d;
            return hashCode3 + (str2 != null ? str2.hashCode() : 0);
        }

        @Override // io.shipbook.shipbooksdk.Models.BaseObj
        @NotNull
        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AppMeasurementSdk.ConditionalUserProperty.NAME, this.f14030a);
            jSONObject.put("severity", this.b.toString());
            jSONObject.put("callStackSeverity", this.c.toString());
            jSONObject.put("appenderRef", this.d);
            return jSONObject;
        }

        @NotNull
        public String toString() {
            return "LoggerResponse(name=" + this.f14030a + ", severity=" + this.b + ", callStackSeverity=" + this.c + ", appenderRef=" + this.d + ")";
        }

        public /* synthetic */ LoggerResponse(String str, Severity severity, Severity severity2, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, severity, (i & 4) != 0 ? Severity.Off : severity2, str2);
        }
    }

    public ConfigResponse(@NotNull List<AppenderResponse> appenders, @NotNull List<LoggerResponse> loggers, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(appenders, "appenders");
        Intrinsics.checkParameterIsNotNull(loggers, "loggers");
        this.f14028a = appenders;
        this.b = loggers;
        this.c = z;
        this.d = z2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static /* synthetic */ ConfigResponse copy$default(ConfigResponse configResponse, List list, List list2, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = configResponse.f14028a;
        }
        if ((i & 2) != 0) {
            list2 = configResponse.b;
        }
        if ((i & 4) != 0) {
            z = configResponse.c;
        }
        if ((i & 8) != 0) {
            z2 = configResponse.d;
        }
        return configResponse.copy(list, list2, z, z2);
    }

    @NotNull
    public final List<AppenderResponse> component1() {
        return this.f14028a;
    }

    @NotNull
    public final List<LoggerResponse> component2() {
        return this.b;
    }

    public final boolean component3() {
        return this.c;
    }

    public final boolean component4() {
        return this.d;
    }

    @NotNull
    public final ConfigResponse copy(@NotNull List<AppenderResponse> appenders, @NotNull List<LoggerResponse> loggers, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(appenders, "appenders");
        Intrinsics.checkParameterIsNotNull(loggers, "loggers");
        return new ConfigResponse(appenders, loggers, z, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof ConfigResponse) {
                ConfigResponse configResponse = (ConfigResponse) obj;
                if (Intrinsics.areEqual(this.f14028a, configResponse.f14028a) && Intrinsics.areEqual(this.b, configResponse.b)) {
                    if (this.c == configResponse.c) {
                        if (this.d == configResponse.d) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final List<AppenderResponse> getAppenders() {
        return this.f14028a;
    }

    public final boolean getEventLoggingDisabled() {
        return this.c;
    }

    public final boolean getExceptionReportDisabled() {
        return this.d;
    }

    @NotNull
    public final List<LoggerResponse> getLoggers() {
        return this.b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        List<AppenderResponse> list = this.f14028a;
        int hashCode = (list != null ? list.hashCode() : 0) * 31;
        List<LoggerResponse> list2 = this.b;
        int hashCode2 = (hashCode + (list2 != null ? list2.hashCode() : 0)) * 31;
        boolean z = this.c;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode2 + i) * 31;
        boolean z2 = this.d;
        return i2 + (z2 ? 1 : z2 ? 1 : 0);
    }

    @Override // io.shipbook.shipbooksdk.Models.BaseObj
    @NotNull
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (AppenderResponse appenderResponse : this.f14028a) {
            jSONArray.put(appenderResponse.toJson());
        }
        jSONObject.put("appenders", jSONArray);
        JSONArray jSONArray2 = new JSONArray();
        for (LoggerResponse loggerResponse : this.b) {
            jSONArray2.put(loggerResponse.toJson());
        }
        jSONObject.put("loggers", jSONArray2);
        jSONObject.put("eventLoggingDisabled", this.c);
        jSONObject.put("exceptionReportDisabled", this.d);
        return jSONObject;
    }

    @NotNull
    public String toString() {
        return "ConfigResponse(appenders=" + this.f14028a + ", loggers=" + this.b + ", eventLoggingDisabled=" + this.c + ", exceptionReportDisabled=" + this.d + ")";
    }

    public /* synthetic */ ConfigResponse(List list, List list2, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, list2, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2);
    }
}
