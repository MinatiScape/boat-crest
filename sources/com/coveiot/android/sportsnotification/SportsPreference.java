package com.coveiot.android.sportsnotification;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import com.coveiot.android.sportsnotification.model.SelectedMatchLastStateData;
import com.coveiot.android.sportsnotification.model.SoccerFilterConfig;
import com.coveiot.android.sportsnotification.model.SportsPreferenceModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SportsPreference {
    @Nullable
    public static Context b;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5825a;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String c = "SportsPreference";
    @NotNull
    public static final String d = "IS_NOTIFICATION_ENABLED";
    @NotNull
    public static final String e = "MATCH_ID";
    @NotNull
    public static final String f = "SPORT_NOTIFICATION";
    @NotNull
    public static final String g = "selected_match";
    @NotNull
    public static final String h = "selected_match_soccer";
    @NotNull
    public static final String i = "soccer_filter_config";
    @NotNull
    public static final String j = "is_ftu_seleted";
    @NotNull
    public static final String k = "is_sport_vibration_enabled";
    @NotNull
    public static final String l = "is_cricket_image_uploaded";
    @NotNull
    public static final String m = "is_soccer_image_uploaded";
    @NotNull
    public static final String n = "selected_match_last_state";
    @NotNull
    public static final String o = "soccer_selected_match_last_state";
    @NotNull
    public static final String p = "odi_interval";
    @NotNull
    public static final String q = "t20_interval";
    @NotNull
    public static final String r = "offline_status";
    @NotNull
    public static final String s = "IS_DISCLAIMER_ACCEPTED";
    @NotNull
    public static final String t = "tp_sub";
    @NotNull
    public static final String u = "c_id";
    @NotNull
    public static final String v = "hst";
    @NotNull
    public static final String w = "keep_alive";
    @NotNull
    public static final String x = "rq_qos";
    @NotNull
    public static final String y = "rq_content_type";

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean IsCricketImageUploaded(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(SportsPreference.c, 0).getBoolean(getIS_CRICKET_IMAGE_UPLOADED(), false);
        }

        public final void clearPreferences(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            for (String str : context.getSharedPreferences(SportsPreference.c, 0).getAll().keySet()) {
                remove(context, str);
            }
        }

        public final void clearSelectedMatch(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(SportsPreference.c, 0).edit().remove(getSELECTED_MATCH()).apply();
        }

        public final void clearSelectedMatchLastState(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putString(getSELECTED_MATCH_LAST_STATE(), null).apply();
        }

        @Nullable
        public final String getClientId(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(SportsPreference.c, 0).getString(SportsPreference.u, null);
        }

        @Nullable
        public final String getHost(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(SportsPreference.c, 0).getString(SportsPreference.v, null);
        }

        @NotNull
        public final String getIS_CRICKET_IMAGE_UPLOADED() {
            return SportsPreference.l;
        }

        @NotNull
        public final String getIS_DISCLAIMER_ACCEPTED() {
            return SportsPreference.s;
        }

        @NotNull
        public final String getIS_FTU_SELETED() {
            return SportsPreference.j;
        }

        @NotNull
        public final String getIS_NOTIFICATION_ENABLED() {
            return SportsPreference.d;
        }

        @NotNull
        public final String getIS_SOCCER_IMAGE_UPLOADED() {
            return SportsPreference.m;
        }

        @NotNull
        public final String getIS_SPORT_VIBRATION_ENABLED() {
            return SportsPreference.k;
        }

        public final int getKeepAlive(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(SportsPreference.c, 0).getInt(SportsPreference.w, Integer.parseInt(com.coveiot.android.remotecommandframeworksdk.utils.Constants.KEEP_ALIVE_INTERVAL.getValue()));
        }

        @NotNull
        public final String getMATCH_ID() {
            return SportsPreference.e;
        }

        @Nullable
        public final Context getMContext() {
            return SportsPreference.b;
        }

        public final int getODIInterval(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(SportsPreference.c, 0).getInt(getODI_INTERVAL(), 10);
        }

        @NotNull
        public final String getODI_INTERVAL() {
            return SportsPreference.p;
        }

        @NotNull
        public final String getOFFLINE_STATUS() {
            return SportsPreference.r;
        }

        @Nullable
        public final String getRequestContentType(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(SportsPreference.c, 0).getString(SportsPreference.y, null);
        }

        public final int getRequestQOS(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(SportsPreference.c, 0).getInt(SportsPreference.x, Integer.parseInt(com.coveiot.android.remotecommandframeworksdk.utils.Constants.MQTT_QOS_1.getValue()));
        }

        @NotNull
        public final String getSELECTED_MATCH() {
            return SportsPreference.g;
        }

        @NotNull
        public final String getSELECTED_MATCH_LAST_STATE() {
            return SportsPreference.n;
        }

        @NotNull
        public final String getSELECTED_MATCH_SOCCER() {
            return SportsPreference.h;
        }

        @NotNull
        public final String getSOCCER_FILTER_CONFIG() {
            return SportsPreference.i;
        }

        @NotNull
        public final String getSOCCER_SELECTED_MATCH_LAST_STATE() {
            return SportsPreference.o;
        }

        @NotNull
        public final String getSPORT_NOTIFICATION() {
            return SportsPreference.f;
        }

        @Nullable
        public final MatchListModel getSelectedMatch(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(SportsPreference.c, 0).getString(getSELECTED_MATCH(), ""))) {
                return null;
            }
            try {
                return (MatchListModel) new Gson().fromJson(context.getSharedPreferences(SportsPreference.c, 0).getString(getSELECTED_MATCH(), ""), new TypeToken<MatchListModel>() { // from class: com.coveiot.android.sportsnotification.SportsPreference$Companion$getSelectedMatch$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        @Nullable
        public final SelectedMatchLastStateData getSelectedMatchLastState(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(SportsPreference.c, 0).getString(getSELECTED_MATCH_LAST_STATE(), ""))) {
                return null;
            }
            try {
                return (SelectedMatchLastStateData) new Gson().fromJson(context.getSharedPreferences(SportsPreference.c, 0).getString(getSELECTED_MATCH_LAST_STATE(), ""), new TypeToken<SelectedMatchLastStateData>() { // from class: com.coveiot.android.sportsnotification.SportsPreference$Companion$getSelectedMatchLastState$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        @Nullable
        public final MatchListModel getSelectedSoccerMatch(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(SportsPreference.c, 0).getString(getSELECTED_MATCH_SOCCER(), ""))) {
                return null;
            }
            try {
                return (MatchListModel) new Gson().fromJson(context.getSharedPreferences(SportsPreference.c, 0).getString(getSELECTED_MATCH_SOCCER(), ""), new TypeToken<MatchListModel>() { // from class: com.coveiot.android.sportsnotification.SportsPreference$Companion$getSelectedSoccerMatch$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        @Nullable
        public final SelectedMatchLastStateData getSelectedSoccerMatchLastState(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(SportsPreference.c, 0).getString(getSOCCER_SELECTED_MATCH_LAST_STATE(), ""))) {
                return null;
            }
            try {
                return (SelectedMatchLastStateData) new Gson().fromJson(context.getSharedPreferences(SportsPreference.c, 0).getString(getSOCCER_SELECTED_MATCH_LAST_STATE(), ""), new TypeToken<SelectedMatchLastStateData>() { // from class: com.coveiot.android.sportsnotification.SportsPreference$Companion$getSelectedSoccerMatchLastState$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        @Nullable
        public final SoccerFilterConfig getSoccerConfig(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (!TextUtils.isEmpty(context.getSharedPreferences(SportsPreference.c, 0).getString(getSOCCER_FILTER_CONFIG(), ""))) {
                try {
                    return (SoccerFilterConfig) new Gson().fromJson(context.getSharedPreferences(SportsPreference.c, 0).getString(getSOCCER_FILTER_CONFIG(), ""), new TypeToken<SoccerFilterConfig>() { // from class: com.coveiot.android.sportsnotification.SportsPreference$Companion$getSoccerConfig$1
                    }.getType());
                } catch (Exception unused) {
                    return (SoccerFilterConfig) new Gson().fromJson(SportsUtils.INSTANCE.generateDefaultSoccerConfig(context), new TypeToken<SoccerFilterConfig>() { // from class: com.coveiot.android.sportsnotification.SportsPreference$Companion$getSoccerConfig$2
                    }.getType());
                }
            }
            return (SoccerFilterConfig) new Gson().fromJson(SportsUtils.INSTANCE.generateDefaultSoccerConfig(context), new TypeToken<SoccerFilterConfig>() { // from class: com.coveiot.android.sportsnotification.SportsPreference$Companion$getSoccerConfig$3
            }.getType());
        }

        @Nullable
        public final SportsPreferenceModel getSportsNotification(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(SportsPreference.c, 0).getString(getSPORT_NOTIFICATION(), ""))) {
                return null;
            }
            try {
                return (SportsPreferenceModel) new Gson().fromJson(context.getSharedPreferences(SportsPreference.c, 0).getString(getSPORT_NOTIFICATION(), ""), new TypeToken<SportsPreferenceModel>() { // from class: com.coveiot.android.sportsnotification.SportsPreference$Companion$getSportsNotification$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        public final int getT20Interval(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(SportsPreference.c, 0).getInt(getT20_INTERVAL(), 2);
        }

        @NotNull
        public final String getT20_INTERVAL() {
            return SportsPreference.q;
        }

        @Nullable
        public final String getTopicSub(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(SportsPreference.c, 0).getString(SportsPreference.t, null);
        }

        public final boolean isDisclaimerAccepted(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(SportsPreference.c, 0).getBoolean(getIS_DISCLAIMER_ACCEPTED(), false);
        }

        public final boolean isFTUOpened(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(SportsPreference.c, 0).getBoolean(getIS_FTU_SELETED(), false);
        }

        public final boolean isNotificationEnabled(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(SportsPreference.c, 0).getBoolean(getIS_NOTIFICATION_ENABLED(), false);
        }

        public final boolean isOfflineStatusSent(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(SportsPreference.c, 0).getBoolean(getOFFLINE_STATUS(), false);
        }

        public final boolean isVibrationEnabled(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(SportsPreference.c, 0).getBoolean(getIS_SPORT_VIBRATION_ENABLED(), false);
        }

        public final void remove(@NotNull Context context, @Nullable String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            SharedPreferences sharedPreferences = context.getSharedPreferences(SportsPreference.c, 0);
            if (sharedPreferences.contains(str)) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.remove(str);
                edit.commit();
            }
        }

        public final void sSelectedSoccerMatchLastState(@NotNull Context context, @NotNull SelectedMatchLastStateData data) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(data, "data");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putString(getSOCCER_SELECTED_MATCH_LAST_STATE(), new Gson().toJson(data)).apply();
        }

        public final void saveClientId(@NotNull Context context, @NotNull String cId) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(cId, "cId");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putString(SportsPreference.u, cId).commit();
        }

        public final void saveDisclaimerAccepted(@NotNull Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putBoolean(getIS_DISCLAIMER_ACCEPTED(), z).apply();
        }

        public final void saveFTUOpened(@NotNull Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putBoolean(getIS_FTU_SELETED(), z).apply();
        }

        public final void saveHost(@NotNull Context context, @NotNull String host) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(host, "host");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putString(SportsPreference.v, host).commit();
        }

        public final void saveKeepAlive(@NotNull Context context, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putInt(SportsPreference.w, i).commit();
        }

        public final void saveNotificationEnabled(@NotNull Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putBoolean(getIS_NOTIFICATION_ENABLED(), z).apply();
        }

        public final void saveODIInterval(@NotNull Context context, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putInt(getODI_INTERVAL(), i).apply();
        }

        public final void saveRequestContentType(@NotNull Context context, @NotNull String contentType) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(contentType, "contentType");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putString(SportsPreference.y, contentType).commit();
        }

        public final void saveRequestQOS(@NotNull Context context, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putInt(SportsPreference.x, i).commit();
        }

        public final void saveSelectedMatch(@NotNull Context context, @NotNull MatchListModel data) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(data, "data");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putString(getSELECTED_MATCH(), new Gson().toJson(data)).apply();
        }

        public final void saveSelectedMatchLastState(@NotNull Context context, @NotNull SelectedMatchLastStateData data) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(data, "data");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putString(getSELECTED_MATCH_LAST_STATE(), new Gson().toJson(data)).apply();
        }

        public final void saveSelectedSoccerMatch(@NotNull Context context, @NotNull MatchListModel data) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(data, "data");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putString(getSELECTED_MATCH_SOCCER(), new Gson().toJson(data)).apply();
        }

        public final void saveSelectedSoccerMatchLastState(@NotNull Context context, @NotNull SelectedMatchLastStateData data) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(data, "data");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putString(getSOCCER_SELECTED_MATCH_LAST_STATE(), new Gson().toJson(data)).apply();
        }

        public final void saveSoccerConfig(@NotNull Context context, @NotNull SoccerFilterConfig data) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(data, "data");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putString(getSOCCER_FILTER_CONFIG(), new Gson().toJson(data)).apply();
        }

        public final void saveSportsNotification(@NotNull Context context, @NotNull SportsPreferenceModel data) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(data, "data");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putString(getSPORT_NOTIFICATION(), new Gson().toJson(data)).apply();
        }

        public final void saveT20Interval(@NotNull Context context, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putInt(getT20_INTERVAL(), i).apply();
        }

        public final void saveTopicSub(@NotNull Context context, @NotNull String tpSub) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(tpSub, "tpSub");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putString(SportsPreference.t, tpSub).commit();
        }

        public final void saveVibrationEnabled(@NotNull Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putBoolean(getIS_SPORT_VIBRATION_ENABLED(), z).apply();
        }

        public final void setIsCricketImageUploaded(@NotNull Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putBoolean(getIS_CRICKET_IMAGE_UPLOADED(), z).apply();
        }

        public final void setIsSoccerImageUploaded(@NotNull Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putBoolean(getIS_SOCCER_IMAGE_UPLOADED(), z).apply();
        }

        public final void setMContext(@Nullable Context context) {
            SportsPreference.b = context;
        }

        public final void setOfflineStatus(@NotNull Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(SportsPreference.c, 0).edit().putBoolean(getOFFLINE_STATUS(), z).apply();
        }
    }

    public SportsPreference(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5825a = context;
    }

    @NotNull
    public final Context getContext() {
        return this.f5825a;
    }
}
