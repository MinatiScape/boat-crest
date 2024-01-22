package com.coveiot.android.fitnessbuddies.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.coveiot.android.fitnessbuddies.models.BuddyReminderModel;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyItems;
import com.coveiot.coveaccess.fitnessbuddies.model.common.BuddiesGoal;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Messages;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class PreferenceManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String b = "com.coveiot.android.permission.prefs";
    @NotNull
    public static final String c = "user_fitness_buddies";
    @NotNull
    public static final String d = "fitness_buddies_sent_request";
    @NotNull
    public static final String e = "fitness_buddies_request_recieved";
    @NotNull
    public static final String f = "user_fitness_buddy_request";
    @NotNull
    public static final String g = "fitness_buddies_goals";
    @NotNull
    public static final String h = "fitness_buddies_reminder";
    @NotNull
    public static final String i = "fitness_messages";
    @NotNull
    public static final String j = "fitness_notification_count";
    @NotNull
    public static final String k = "fitness_cheer_count";
    @NotNull
    public static final String l = "user_id";
    @NotNull
    public static final String m = "FcmRegistrationId";
    @NotNull
    public static final String n = "fcmToServerOk";
    @NotNull
    public static final String o = "fitness_buddies_items";
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4473a;

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void clearPreferences(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            for (String str : context.getSharedPreferences(PreferenceManager.b, 0).getAll().keySet()) {
                remove(context, str);
            }
        }

        public final void firstTimeAskingPermission(@NotNull Context context, @NotNull String permission, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(permission, "permission");
            context.getSharedPreferences(PreferenceManager.b, 0).edit().putBoolean(permission, z).commit();
        }

        @Nullable
        public final List<Requests> getAllFitnessBuddyRequests(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(PreferenceManager.b, 0).getString(PreferenceManager.f, ""))) {
                return null;
            }
            try {
                return (List) new Gson().fromJson(context.getSharedPreferences(PreferenceManager.b, 0).getString(PreferenceManager.f, ""), new TypeToken<List<? extends Requests>>() { // from class: com.coveiot.android.fitnessbuddies.utils.PreferenceManager$Companion$getAllFitnessBuddyRequests$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        @Nullable
        public final String getFcmRegisterationId(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(PreferenceManager.b, 0).getString(PreferenceManager.m, "");
        }

        public final boolean getFcmRegisterationToServer(@NotNull Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(PreferenceManager.b, 0).getBoolean(PreferenceManager.n, z);
        }

        @Nullable
        public final List<Requests> getFitnessBuddies(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(PreferenceManager.b, 0).getString(PreferenceManager.c, ""))) {
                return null;
            }
            try {
                return (List) new Gson().fromJson(context.getSharedPreferences(PreferenceManager.b, 0).getString(PreferenceManager.c, ""), new TypeToken<List<? extends Requests>>() { // from class: com.coveiot.android.fitnessbuddies.utils.PreferenceManager$Companion$getFitnessBuddies$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        @Nullable
        public final List<GetBuddyItems> getFitnessBuddiesDetailsGoals(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(PreferenceManager.b, 0).getString(PreferenceManager.o, ""))) {
                return null;
            }
            try {
                return (List) new Gson().fromJson(context.getSharedPreferences(PreferenceManager.b, 0).getString(PreferenceManager.o, ""), new TypeToken<List<? extends GetBuddyItems>>() { // from class: com.coveiot.android.fitnessbuddies.utils.PreferenceManager$Companion$getFitnessBuddiesDetailsGoals$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        @Nullable
        public final List<BuddiesGoal> getFitnessBuddiesGoals(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(PreferenceManager.b, 0).getString(PreferenceManager.g, ""))) {
                return null;
            }
            try {
                return (List) new Gson().fromJson(context.getSharedPreferences(PreferenceManager.b, 0).getString(PreferenceManager.g, ""), new TypeToken<List<? extends BuddiesGoal>>() { // from class: com.coveiot.android.fitnessbuddies.utils.PreferenceManager$Companion$getFitnessBuddiesGoals$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        @Nullable
        public final List<BuddyReminderModel> getFitnessBuddiesReminder(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(PreferenceManager.b, 0).getString(PreferenceManager.h, ""))) {
                return null;
            }
            try {
                return (List) new Gson().fromJson(context.getSharedPreferences(PreferenceManager.b, 0).getString(PreferenceManager.h, ""), new TypeToken<List<? extends BuddyReminderModel>>() { // from class: com.coveiot.android.fitnessbuddies.utils.PreferenceManager$Companion$getFitnessBuddiesReminder$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        @Nullable
        public final List<Requests> getFitnessBuddiesRequestsReceived(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(PreferenceManager.b, 0).getString(PreferenceManager.e, ""))) {
                return null;
            }
            try {
                return (List) new Gson().fromJson(context.getSharedPreferences(PreferenceManager.b, 0).getString(PreferenceManager.e, ""), new TypeToken<List<? extends Requests>>() { // from class: com.coveiot.android.fitnessbuddies.utils.PreferenceManager$Companion$getFitnessBuddiesRequestsReceived$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        public final int getFitnessCheerCount(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(PreferenceManager.b, 0).getInt(PreferenceManager.k, 0);
        }

        @Nullable
        public final List<Messages> getFitnessMessages(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(PreferenceManager.b, 0).getString(PreferenceManager.g, ""))) {
                return null;
            }
            try {
                return (List) new Gson().fromJson(context.getSharedPreferences(PreferenceManager.b, 0).getString(PreferenceManager.g, ""), new TypeToken<List<? extends Messages>>() { // from class: com.coveiot.android.fitnessbuddies.utils.PreferenceManager$Companion$getFitnessMessages$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        public final int getFitnessNotificationCount(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(PreferenceManager.b, 0).getInt(PreferenceManager.j, 0);
        }

        @Nullable
        public final List<Requests> getSentFitnessBuddiesRequests(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(PreferenceManager.b, 0).getString(PreferenceManager.d, ""))) {
                return null;
            }
            try {
                return (List) new Gson().fromJson(context.getSharedPreferences(PreferenceManager.b, 0).getString(PreferenceManager.d, ""), new TypeToken<List<? extends Requests>>() { // from class: com.coveiot.android.fitnessbuddies.utils.PreferenceManager$Companion$getSentFitnessBuddiesRequests$1
                }.getType());
            } catch (Exception unused) {
                return null;
            }
        }

        public final int getUserId(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getSharedPreferences(PreferenceManager.b, 0).getInt(PreferenceManager.l, 0);
        }

        public final boolean isFirstTimeAskingPermission(@NotNull Context context, @NotNull String permission) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(permission, "permission");
            return context.getSharedPreferences(PreferenceManager.b, 0).getBoolean(permission, true);
        }

        public final void remove(@NotNull Context context, @Nullable String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            SharedPreferences sharedPreferences = context.getSharedPreferences(PreferenceManager.b, 0);
            if (sharedPreferences.contains(str)) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.remove(str);
                edit.commit();
            }
        }

        public final void saveAllFitnessBuddyRequests(@NotNull Context context, @NotNull List<? extends Requests> data) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(data, "data");
            context.getSharedPreferences(PreferenceManager.b, 0).edit().putString(PreferenceManager.f, new Gson().toJson(data)).apply();
        }

        public final void saveBuddiesGoals(@NotNull Context context, @NotNull List<? extends BuddiesGoal> buddiesGoals) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(buddiesGoals, "buddiesGoals");
            context.getSharedPreferences(PreferenceManager.b, 0).edit().putString(PreferenceManager.g, new Gson().toJson(buddiesGoals)).commit();
        }

        public final void saveBuddiesListDetails(@NotNull Context context, @NotNull List<GetBuddyItems> buddiesGoals) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(buddiesGoals, "buddiesGoals");
            context.getSharedPreferences(PreferenceManager.b, 0).edit().putString(PreferenceManager.o, new Gson().toJson(buddiesGoals)).commit();
        }

        public final void saveBuddiesReminderModel(@NotNull Context context, @NotNull List<BuddyReminderModel> buddiesGoals) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(buddiesGoals, "buddiesGoals");
            context.getSharedPreferences(PreferenceManager.b, 0).edit().putString(PreferenceManager.h, new Gson().toJson(buddiesGoals)).commit();
        }

        public final void saveFcmRegisterationId(@NotNull Context context, @NotNull String registerationId) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(registerationId, "registerationId");
            context.getSharedPreferences(PreferenceManager.b, 0).edit().putString(PreferenceManager.m, registerationId);
        }

        public final void saveFcmRegisterationToServer(@NotNull Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(PreferenceManager.b, 0).edit().putBoolean(PreferenceManager.n, z).commit();
        }

        public final void saveFitnessBuddies(@NotNull Context context, @NotNull List<? extends Requests> data) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(data, "data");
            context.getSharedPreferences(PreferenceManager.b, 0).edit().putString(PreferenceManager.c, new Gson().toJson(data)).apply();
        }

        public final void saveFitnessBuddyRequestsRecieved(@NotNull Context context, @NotNull List<? extends Requests> receivedRequest) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(receivedRequest, "receivedRequest");
            context.getSharedPreferences(PreferenceManager.b, 0).edit().putString(PreferenceManager.e, new Gson().toJson(receivedRequest));
        }

        public final void saveFitnessCheerCount(@NotNull Context context, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(PreferenceManager.b, 0).edit().putInt(PreferenceManager.k, i).apply();
        }

        public final void saveFitnessMessages(@NotNull Context context, @NotNull List<? extends Messages> messagesList) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(messagesList, "messagesList");
            context.getSharedPreferences(PreferenceManager.b, 0).edit().putString(PreferenceManager.i, new Gson().toJson(messagesList));
        }

        public final void saveFitnessNotificationCount(@NotNull Context context, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(PreferenceManager.b, 0).edit().putInt(PreferenceManager.j, i).apply();
        }

        public final void saveSentFitnessBuddyRequests(@NotNull Context context, @NotNull List<? extends Requests> sentRequest) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(sentRequest, "sentRequest");
            context.getSharedPreferences(PreferenceManager.b, 0).edit().putString(PreferenceManager.d, new Gson().toJson(sentRequest));
        }

        public final void saveUserId(@NotNull Context context, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(PreferenceManager.b, 0).edit().putInt(PreferenceManager.l, i).apply();
        }
    }

    public PreferenceManager(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4473a = context;
    }

    @NotNull
    public final Context getContext() {
        return this.f4473a;
    }
}
