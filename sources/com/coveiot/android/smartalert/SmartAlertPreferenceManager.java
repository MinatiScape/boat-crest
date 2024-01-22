package com.coveiot.android.smartalert;

import android.content.Context;
import android.content.SharedPreferences;
import com.coveiot.android.smartalert.util.SingletonHolder;
import com.coveiot.covepreferences.data.AppNotificationData;
import com.coveiot.covepreferences.data.SmartAlertAppServerConfData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public final class SmartAlertPreferenceManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5753a;
    @NotNull
    public final String b;
    public int c;
    @NotNull
    public final SharedPreferences d;
    public final SharedPreferences.Editor e;
    @NotNull
    public final String f;
    @NotNull
    public final String g;
    @NotNull
    public final String h;
    @NotNull
    public final String i;

    /* loaded from: classes6.dex */
    public static final class Companion extends SingletonHolder<SmartAlertPreferenceManager, Context> {

        /* loaded from: classes6.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, SmartAlertPreferenceManager> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, SmartAlertPreferenceManager.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final SmartAlertPreferenceManager invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SmartAlertPreferenceManager(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SmartAlertPreferenceManager(Context context) {
        this.f5753a = context;
        this.b = "smart_alert_pref";
        SharedPreferences sharedPreferences = context.getSharedPreferences("smart_alert_pref", this.c);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…dPrefsFile, PRIVATE_MODE)");
        this.d = sharedPreferences;
        this.e = sharedPreferences.edit();
        this.f = "msg_pattern_group_map";
        this.g = "selected_smart_alert_app_id_for_testing";
        this.h = "smart_alert_app_notification_object";
        this.i = "smart_alert_app_server_conf_data";
    }

    public /* synthetic */ SmartAlertPreferenceManager(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final void clearPreferences(@Nullable Context context) {
        for (String str : this.d.getAll().keySet()) {
            remove(context, str);
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f5753a;
    }

    @NotNull
    public final String getMSG_PATTERN_GROUP_MAP() {
        return this.f;
    }

    @NotNull
    public final HashMap<String, HashMap<String, String>> getMessagePatternGroupMap() {
        Object fromJson = new Gson().fromJson(this.d.getString(this.f, new JSONObject().toString()), new TypeToken<HashMap<String, HashMap<String, String>>>() { // from class: com.coveiot.android.smartalert.SmartAlertPreferenceManager$getMessagePatternGroupMap$type$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(fromJson, "gson.fromJson(storedHashMapString, type)");
        return (HashMap) fromJson;
    }

    @NotNull
    public final String getSELECTED_SMART_ALERT_APP_ID_FOR_TESTING() {
        return this.g;
    }

    @NotNull
    public final String getSMART_ALERT_APP_NOTIFICATION_OBJECT() {
        return this.h;
    }

    @NotNull
    public final String getSMART_ALERT_APP_SERVER_CONF_DATA() {
        return this.i;
    }

    @Nullable
    public final String getSmartAlertAppIdForTesting() {
        return this.d.getString(this.g, "com.ubercab");
    }

    @Nullable
    public final List<AppNotificationData> getSmartAlertAppNotificationsSettings() {
        String string = this.d.getString(this.h, null);
        ArrayList arrayList = new ArrayList();
        Gson gson = new Gson();
        if (string != null) {
            Object fromJson = gson.fromJson(string, new TypeToken<List<AppNotificationData>>() { // from class: com.coveiot.android.smartalert.SmartAlertPreferenceManager$getSmartAlertAppNotificationsSettings$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(fromJson, "gson.fromJson(\n         …>() {}.type\n            )");
            return (List) fromJson;
        }
        return arrayList;
    }

    @Nullable
    public final List<SmartAlertAppServerConfData> getSmartAlertAppServerConfigData() {
        String string = this.d.getString(this.i, null);
        ArrayList arrayList = new ArrayList();
        Gson gson = new Gson();
        if (string != null) {
            Object fromJson = gson.fromJson(string, new TypeToken<List<? extends SmartAlertAppServerConfData>>() { // from class: com.coveiot.android.smartalert.SmartAlertPreferenceManager$getSmartAlertAppServerConfigData$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(fromJson, "gson.fromJson(\n         …>() {}.type\n            )");
            return (List) fromJson;
        }
        return arrayList;
    }

    public final void remove(@Nullable Context context, @Nullable String str) {
        if (this.d.contains(str)) {
            this.e.remove(str);
            this.e.apply();
        }
    }

    public final void saveMessagePatternGroupMap(@NotNull HashMap<String, HashMap<String, String>> groupMap) {
        Intrinsics.checkNotNullParameter(groupMap, "groupMap");
        this.e.putString(this.f, new Gson().toJson(groupMap));
        this.e.apply();
    }

    public final void saveSmartAlertAppIdForTesting(@NotNull String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.e.putString(this.g, packageName);
        this.e.apply();
    }

    public final void saveSmartAlertAppNotificationsSettings(@Nullable List<? extends AppNotificationData> list) {
        this.e.putString(this.h, new Gson().toJson(list));
        this.e.apply();
    }

    public final void saveSmartAlertAppServerConfigData(@Nullable List<SmartAlertAppServerConfData> list) {
        this.e.putString(this.i, new Gson().toJson(list));
        this.e.apply();
    }
}
