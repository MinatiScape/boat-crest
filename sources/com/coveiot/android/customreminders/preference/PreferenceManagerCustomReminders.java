package com.coveiot.android.customreminders.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.customreminders.model.CustomReminder;
import com.coveiot.android.customreminders.model.DrinkWaterReminder;
import com.coveiot.android.customreminders.model.HandWashReminder;
import com.coveiot.android.customreminders.model.MedicineReminder;
import com.coveiot.android.customreminders.model.MeetingReminder;
import com.coveiot.android.customreminders.model.OtherReminder;
import com.coveiot.android.customreminders.preference.gsonadapters.RuntimeTypeAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class PreferenceManagerCustomReminders {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String b = "custom_reminders";
    @NotNull
    public static final String c = "reminders_list";
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4178a;

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(Context context, String str) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PreferenceManagerCustomReminders.b, 0);
            if (sharedPreferences.contains(str)) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.remove(str);
                edit.commit();
            }
        }

        public final void clearPreferences(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            for (String str : context.getSharedPreferences(PreferenceManagerCustomReminders.b, 0).getAll().keySet()) {
                a(context, str);
            }
        }

        @NotNull
        public final Gson getGson() {
            RuntimeTypeAdapterFactory registerSubtype = RuntimeTypeAdapterFactory.of(CustomReminder.class, "type", true).registerSubtype(MedicineReminder.class, ReminderType.MEDICINE.name()).registerSubtype(DrinkWaterReminder.class, ReminderType.DRINK.name()).registerSubtype(MeetingReminder.class, ReminderType.MEETING.name()).registerSubtype(OtherReminder.class, ReminderType.OTHERS.name()).registerSubtype(HandWashReminder.class, ReminderType.HAND_WASH.name());
            Intrinsics.checkNotNullExpressionValue(registerSubtype, "of(\n                    …inderType.HAND_WASH.name)");
            Gson create = new GsonBuilder().registerTypeAdapterFactory(registerSubtype).create();
            Intrinsics.checkNotNullExpressionValue(create, "GsonBuilder().registerTy…ory(typeFactory).create()");
            return create;
        }

        @Nullable
        public final List<CustomReminder> getReminders(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (TextUtils.isEmpty(context.getSharedPreferences(PreferenceManagerCustomReminders.b, 0).getString(PreferenceManagerCustomReminders.c, ""))) {
                return null;
            }
            try {
                return (List) getGson().fromJson(context.getSharedPreferences(PreferenceManagerCustomReminders.b, 0).getString(PreferenceManagerCustomReminders.c, ""), new TypeToken<List<? extends CustomReminder>>() { // from class: com.coveiot.android.customreminders.preference.PreferenceManagerCustomReminders$Companion$getReminders$1
                }.getType());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public final void saveReminders(@NotNull Context context, @Nullable List<? extends CustomReminder> list) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.getSharedPreferences(PreferenceManagerCustomReminders.b, 0).edit().putString(PreferenceManagerCustomReminders.c, getGson().toJson(list)).apply();
        }
    }

    public PreferenceManagerCustomReminders(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4178a = context;
    }

    @NotNull
    public final Context getContext() {
        return this.f4178a;
    }
}
