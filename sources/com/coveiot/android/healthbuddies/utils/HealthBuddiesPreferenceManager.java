package com.coveiot.android.healthbuddies.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.coveiot.coveaccess.healthbuddies.HealthBuddy;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class HealthBuddiesPreferenceManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String b = "com.coveiot.android.permission.healthbuddies.prefs";
    @NotNull
    public static final String c = "user_health_buddies";
    @NotNull
    public static final String d = "health_buddies_sent_request";
    @NotNull
    public static final String e = "user_health_buddy_request";
    @NotNull
    public static final String f = "doctor_user_health_buddies";
    @NotNull
    public static final String g = "doctor_health_buddies_sent_request";
    @NotNull
    public static final String h = "doctor_user_health_buddy_requests";
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4584a;

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void clearPreferences(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            for (String str : context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).getAll().keySet()) {
                remove(context, str);
            }
        }

        @Nullable
        public final List<HealthBuddy> getAllDoctorHealthBuddyRequests(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).getString(HealthBuddiesPreferenceManager.h, ""))) {
                return null;
            }
            try {
                return (List) new Gson().fromJson(context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).getString(HealthBuddiesPreferenceManager.h, ""), new TypeToken<List<? extends HealthBuddy>>() { // from class: com.coveiot.android.healthbuddies.utils.HealthBuddiesPreferenceManager$Companion$getAllDoctorHealthBuddyRequests$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        @Nullable
        public final List<HealthBuddy> getAllHealthBuddyRequests(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).getString(HealthBuddiesPreferenceManager.e, ""))) {
                return null;
            }
            try {
                return (List) new Gson().fromJson(context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).getString(HealthBuddiesPreferenceManager.e, ""), new TypeToken<List<? extends HealthBuddy>>() { // from class: com.coveiot.android.healthbuddies.utils.HealthBuddiesPreferenceManager$Companion$getAllHealthBuddyRequests$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        @Nullable
        public final List<HealthBuddy> getDoctorHealthBuddies(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).getString(HealthBuddiesPreferenceManager.f, ""))) {
                return null;
            }
            try {
                return (List) new Gson().fromJson(context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).getString(HealthBuddiesPreferenceManager.f, ""), new TypeToken<List<? extends HealthBuddy>>() { // from class: com.coveiot.android.healthbuddies.utils.HealthBuddiesPreferenceManager$Companion$getDoctorHealthBuddies$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        @Nullable
        public final List<HealthBuddy> getHealthBuddies(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).getString(HealthBuddiesPreferenceManager.c, ""))) {
                return null;
            }
            try {
                return (List) new Gson().fromJson(context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).getString(HealthBuddiesPreferenceManager.c, ""), new TypeToken<List<? extends HealthBuddy>>() { // from class: com.coveiot.android.healthbuddies.utils.HealthBuddiesPreferenceManager$Companion$getHealthBuddies$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        @Nullable
        public final List<HealthBuddy> getSentDoctorHealthBuddiesRequests(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).getString(HealthBuddiesPreferenceManager.g, ""))) {
                return null;
            }
            try {
                return (List) new Gson().fromJson(context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).getString(HealthBuddiesPreferenceManager.g, ""), new TypeToken<List<? extends HealthBuddy>>() { // from class: com.coveiot.android.healthbuddies.utils.HealthBuddiesPreferenceManager$Companion$getSentDoctorHealthBuddiesRequests$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        @Nullable
        public final List<HealthBuddy> getSentHealthBuddiesRequests(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).getString(HealthBuddiesPreferenceManager.d, ""))) {
                return null;
            }
            try {
                return (List) new Gson().fromJson(context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).getString(HealthBuddiesPreferenceManager.d, ""), new TypeToken<List<? extends HealthBuddy>>() { // from class: com.coveiot.android.healthbuddies.utils.HealthBuddiesPreferenceManager$Companion$getSentHealthBuddiesRequests$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        public final boolean isFamilyDoctorFTUCompleted(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).getBoolean("is_family_doctor_ftu_completed", false);
        }

        public final boolean isHealthBuddiesFTUCompleted(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).getBoolean("IS_HEALTH_BUDDIES_FTU_COMPLETED", false);
        }

        public final void remove(@NotNull Context context, @Nullable String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            SharedPreferences sharedPreferences = context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0);
            if (sharedPreferences.contains(str)) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.remove(str);
                edit.commit();
            }
        }

        public final void saveAllDoctorHealthBuddyRequests(@NotNull Context context, @NotNull List<? extends HealthBuddy> data) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(data, "data");
            context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).edit().putString(HealthBuddiesPreferenceManager.h, new Gson().toJson(data)).apply();
        }

        public final void saveAllHealthBuddyRequests(@NotNull Context context, @NotNull List<? extends HealthBuddy> data) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(data, "data");
            context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).edit().putString(HealthBuddiesPreferenceManager.e, new Gson().toJson(data)).apply();
        }

        public final void saveDoctorHealthBuddies(@NotNull Context context, @NotNull List<? extends HealthBuddy> data) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(data, "data");
            context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).edit().putString(HealthBuddiesPreferenceManager.f, new Gson().toJson(data)).apply();
        }

        public final void saveHealthBuddies(@NotNull Context context, @NotNull List<? extends HealthBuddy> data) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(data, "data");
            context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).edit().putString(HealthBuddiesPreferenceManager.c, new Gson().toJson(data)).apply();
        }

        public final void saveSentDoctorHealthBuddyRequests(@NotNull Context context, @NotNull List<? extends HealthBuddy> sentRequest) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(sentRequest, "sentRequest");
            context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).edit().putString(HealthBuddiesPreferenceManager.g, new Gson().toJson(sentRequest)).apply();
        }

        public final void saveSentHealthBuddyRequests(@NotNull Context context, @NotNull List<? extends HealthBuddy> sentRequest) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(sentRequest, "sentRequest");
            context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).edit().putString(HealthBuddiesPreferenceManager.d, new Gson().toJson(sentRequest)).apply();
        }

        public final void setFamilyDoctorFTUCompleted(@NotNull Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).edit().putBoolean("is_family_doctor_ftu_completed", z).commit();
        }

        public final void setHealthBuddiesFTUCompleted(@NotNull Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(HealthBuddiesPreferenceManager.b, 0).edit().putBoolean("IS_HEALTH_BUDDIES_FTU_COMPLETED", z).commit();
        }
    }

    public HealthBuddiesPreferenceManager(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4584a = context;
    }

    @NotNull
    public final Context getContext() {
        return this.f4584a;
    }
}
