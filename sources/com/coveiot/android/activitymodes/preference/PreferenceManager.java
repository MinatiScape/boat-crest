package com.coveiot.android.activitymodes.preference;

import android.content.Context;
import android.content.SharedPreferences;
import com.coveiot.android.activitymodes.BASEUNIT;
import com.coveiot.android.activitymodes.models.ActivitiesItem;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.ble.event.stat.one.d;
import com.mappls.sdk.maps.style.layers.Property;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class PreferenceManager {
    @NotNull
    public final String A;
    @NotNull
    public final String B;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f2856a;
    @NotNull
    public final String b;
    @NotNull
    public final SharedPreferences c;
    @NotNull
    public final String d;
    @NotNull
    public final String e;
    @NotNull
    public final String f;
    @NotNull
    public final String g;
    @NotNull
    public final String h;
    @NotNull
    public final String i;
    @NotNull
    public final String j;
    @NotNull
    public final String k;
    @NotNull
    public final String l;
    @NotNull
    public final String m;
    @NotNull
    public final String n;
    @NotNull
    public final String o;
    @NotNull
    public final String p;
    @NotNull
    public final String q;
    @NotNull
    public final String r;
    @NotNull
    public final String s;
    @NotNull
    public final String t;
    @NotNull
    public final String u;
    @NotNull
    public final String v;
    @NotNull
    public final String w;
    @NotNull
    public final String x;
    @NotNull
    public final String y;
    @NotNull
    public final String z;

    public PreferenceManager(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f2856a = context;
        this.b = "com.coveiot.android.runtracking.prefs";
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.coveiot.android.runtracking.prefs", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…PREFERENCE, MODE_PRIVATE)");
        this.c = sharedPreferences;
        this.d = "initial_steps";
        this.e = "stride_length";
        this.f = Property.ICON_TEXT_FIT_HEIGHT;
        this.g = "weight";
        this.h = "is_device_connected";
        this.i = "is_run_session_active";
        this.j = "age";
        this.k = "workout_target";
        this.l = WorkoutConstants.ACTIVITY_MODE;
        this.m = "workout_target_unit";
        this.n = d.j;
        this.o = "client_id";
        this.p = "api_key";
        this.q = "last_workout_progress_updated";
        this.r = "connected_device_mac_address";
        this.s = "is_mode_from_app";
        this.t = "is_sample_data_supported";
        this.u = "IS_ONEK_FTU_SHOWN";
        this.v = "SHOULD_SHOW_ACTIVITY_SYNC";
        this.w = "is_sport_mode_history_supported";
        this.x = "ido_activity_icons";
        this.y = "touch_activity_icons";
        this.z = "eastapex_activity_icons";
        this.A = "rugged_activity_icons";
        this.B = "sma_activity_icons";
    }

    public final void clearPreferences(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        for (String str : this.c.getAll().keySet()) {
            remove(context, str);
        }
    }

    @Nullable
    public final String getActivityMode() {
        return this.c.getString(this.l, ActivityMode.RUN.toString());
    }

    public final int getAge() {
        return this.c.getInt(this.j, 35);
    }

    @Nullable
    public final String getApiKey() {
        return this.c.getString(this.p, null);
    }

    @Nullable
    public final String getAppName() {
        return this.c.getString(this.n, null);
    }

    @Nullable
    public final String getClientId() {
        return this.c.getString(this.o, null);
    }

    @Nullable
    public final String getConnectedDeviceMacAddress() {
        return this.c.getString(this.r, null);
    }

    @NotNull
    public final Context getContext() {
        return this.f2856a;
    }

    @NotNull
    public final ArrayList<ActivitiesItem> getEastApexActivityIcons() {
        String string = this.c.getString(this.z, null);
        ArrayList<ActivitiesItem> arrayList = new ArrayList<>();
        Gson gson = new Gson();
        if (string != null) {
            Object fromJson = gson.fromJson(string, new TypeToken<List<? extends ActivitiesItem>>() { // from class: com.coveiot.android.activitymodes.preference.PreferenceManager$getEastApexActivityIcons$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(fromJson, "gson.fromJson(\n         …>() {}.type\n            )");
            return (ArrayList) fromJson;
        }
        return arrayList;
    }

    public final int getHeight() {
        return this.c.getInt(this.f, 0);
    }

    @NotNull
    public final ArrayList<ActivitiesItem> getIDOActivityIcons() {
        String string = this.c.getString(this.x, null);
        ArrayList<ActivitiesItem> arrayList = new ArrayList<>();
        Gson gson = new Gson();
        if (string != null) {
            Object fromJson = gson.fromJson(string, new TypeToken<List<? extends ActivitiesItem>>() { // from class: com.coveiot.android.activitymodes.preference.PreferenceManager$getIDOActivityIcons$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(fromJson, "gson.fromJson(\n         …>() {}.type\n            )");
            return (ArrayList) fromJson;
        }
        return arrayList;
    }

    public final int getInitialSteps() {
        return this.c.getInt(this.d, 0);
    }

    public final int getLastWorkoutProgressUpdated() {
        return this.c.getInt(this.q, 0);
    }

    @NotNull
    public final ArrayList<ActivitiesItem> getRuggedActivityIcons() {
        String string = this.c.getString(this.A, null);
        ArrayList<ActivitiesItem> arrayList = new ArrayList<>();
        Gson gson = new Gson();
        if (string != null) {
            Object fromJson = gson.fromJson(string, new TypeToken<List<? extends ActivitiesItem>>() { // from class: com.coveiot.android.activitymodes.preference.PreferenceManager$getRuggedActivityIcons$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(fromJson, "gson.fromJson(\n         …>() {}.type\n            )");
            return (ArrayList) fromJson;
        }
        return arrayList;
    }

    @NotNull
    public final ArrayList<ActivitiesItem> getSMAActivityIcons() {
        String string = this.c.getString(this.B, null);
        ArrayList<ActivitiesItem> arrayList = new ArrayList<>();
        Gson gson = new Gson();
        if (string != null) {
            Object fromJson = gson.fromJson(string, new TypeToken<List<? extends ActivitiesItem>>() { // from class: com.coveiot.android.activitymodes.preference.PreferenceManager$getSMAActivityIcons$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(fromJson, "gson.fromJson(\n         …>() {}.type\n            )");
            return (ArrayList) fromJson;
        }
        return arrayList;
    }

    public final boolean getShouldShowActivitySyncDialog() {
        return this.c.getBoolean(this.v, true);
    }

    public final float getStride() {
        return this.c.getFloat(this.e, 0.0f);
    }

    @NotNull
    public final ArrayList<ActivitiesItem> getTouchActivityIcons() {
        String string = this.c.getString(this.y, null);
        ArrayList<ActivitiesItem> arrayList = new ArrayList<>();
        Gson gson = new Gson();
        if (string != null) {
            Object fromJson = gson.fromJson(string, new TypeToken<List<? extends ActivitiesItem>>() { // from class: com.coveiot.android.activitymodes.preference.PreferenceManager$getTouchActivityIcons$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(fromJson, "gson.fromJson(\n         …>() {}.type\n            )");
            return (ArrayList) fromJson;
        }
        return arrayList;
    }

    public final int getWeight() {
        return this.c.getInt(this.g, 0);
    }

    @Nullable
    public final String getWorkoutTarget() {
        return this.c.getString(this.k, "5000");
    }

    @Nullable
    public final String getWorkoutTargetUnit() {
        return this.c.getString(this.m, BASEUNIT.KILOMETERS.toString());
    }

    public final boolean isDeviceConnected() {
        return this.c.getBoolean(this.h, true);
    }

    public final boolean isModeFromApp() {
        return this.c.getBoolean(this.s, true);
    }

    public final boolean isOnekFTUShown() {
        return this.c.getBoolean(this.u, false);
    }

    public final boolean isRunSessionActive() {
        return this.c.getBoolean(this.i, false);
    }

    public final boolean isSampleDataSupported() {
        return this.c.getBoolean(this.t, false);
    }

    public final boolean isSportModeHistorySupported() {
        return this.c.getBoolean(this.w, false);
    }

    public final void remove(@NotNull Context context, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.c.contains(str)) {
            SharedPreferences.Editor edit = this.c.edit();
            edit.remove(str);
            edit.commit();
        }
    }

    public final void saveEastApexActivityIcons(@NotNull List<ActivitiesItem> activitiesList) {
        Intrinsics.checkNotNullParameter(activitiesList, "activitiesList");
        this.c.edit().putString(this.z, new Gson().toJson(activitiesList)).apply();
    }

    public final void saveIDOActivityIcons(@NotNull List<ActivitiesItem> activitiesList) {
        Intrinsics.checkNotNullParameter(activitiesList, "activitiesList");
        this.c.edit().putString(this.x, new Gson().toJson(activitiesList)).apply();
    }

    public final void saveRuggedActivityIcons(@NotNull List<ActivitiesItem> activitiesList) {
        Intrinsics.checkNotNullParameter(activitiesList, "activitiesList");
        this.c.edit().putString(this.A, new Gson().toJson(activitiesList)).apply();
    }

    public final void saveSMAActivityIcons(@NotNull List<ActivitiesItem> activitiesList) {
        Intrinsics.checkNotNullParameter(activitiesList, "activitiesList");
        this.c.edit().putString(this.B, new Gson().toJson(activitiesList)).apply();
    }

    public final void saveTouchActivityIcons(@NotNull List<ActivitiesItem> activitiesList) {
        Intrinsics.checkNotNullParameter(activitiesList, "activitiesList");
        this.c.edit().putString(this.y, new Gson().toJson(activitiesList)).apply();
    }

    public final void setActivityMode(@Nullable String str) {
        this.c.edit().putString(this.l, str).commit();
    }

    public final void setAge(int i) {
        this.c.edit().putInt(this.j, i).apply();
    }

    public final void setApiKey(@Nullable String str) {
        this.c.edit().putString(this.p, str).apply();
    }

    public final void setAppName(@Nullable String str) {
        this.c.edit().putString(this.n, str).apply();
    }

    public final void setClientId(@Nullable String str) {
        this.c.edit().putString(this.o, str).apply();
    }

    public final void setConnectedDeviceMacAddress(@Nullable String str) {
        this.c.edit().putString(this.r, str).apply();
    }

    public final void setDeviceConnected(boolean z) {
        this.c.edit().putBoolean(this.h, z).apply();
    }

    public final void setHeight(int i) {
        this.c.edit().putInt(this.f, i).apply();
    }

    public final void setInitialSteps(int i) {
        this.c.edit().putInt(this.d, i).commit();
    }

    public final void setLastWorkoutProgressUpdated(int i) {
        this.c.edit().putInt(this.q, i).apply();
    }

    public final void setModeFromApp(boolean z) {
        this.c.edit().putBoolean(this.s, z).apply();
    }

    public final void setOnekFTUShown(boolean z) {
        this.c.edit().putBoolean(this.u, z).apply();
    }

    public final void setRunSessionActive(boolean z) {
        this.c.edit().putBoolean(this.i, z).apply();
    }

    public final void setSampleDataSupported(boolean z) {
        this.c.edit().putBoolean(this.t, z).apply();
    }

    public final void setShouldShowActivitySyncDialog(boolean z) {
        this.c.edit().putBoolean(this.v, z).apply();
    }

    public final void setSportModeHistorySupported(boolean z) {
        this.c.edit().putBoolean(this.w, z).apply();
    }

    public final void setStride(float f) {
        this.c.edit().putFloat(this.e, f).apply();
    }

    public final void setWeight(int i) {
        this.c.edit().putInt(this.g, i).apply();
    }

    public final void setWorkoutTarget(@Nullable String str) {
        this.c.edit().putString(this.k, str).commit();
    }

    public final void setWorkoutTargetUnit(@Nullable String str) {
        this.c.edit().putString(this.m, str).apply();
    }
}
