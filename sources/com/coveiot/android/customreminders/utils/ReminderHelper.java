package com.coveiot.android.customreminders.utils;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.DrinkingReminderAbstract;
import com.coveiot.android.bleabstract.models.HandWashReminderAbstract;
import com.coveiot.android.bleabstract.models.MedicineReminderAbstract;
import com.coveiot.android.bleabstract.models.MeetingReminderAbstract;
import com.coveiot.android.bleabstract.models.OtherReminderAbstract;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.SetCustomRemindersRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.customreminders.formator.APIFormator;
import com.coveiot.android.customreminders.listeners.ResultListener;
import com.coveiot.android.customreminders.model.CustomReminder;
import com.coveiot.android.customreminders.model.DrinkWaterReminder;
import com.coveiot.android.customreminders.model.HandWashReminder;
import com.coveiot.android.customreminders.model.MedicineReminder;
import com.coveiot.android.customreminders.model.MeetingReminder;
import com.coveiot.android.customreminders.model.OtherReminder;
import com.coveiot.android.customreminders.model.RepeatModel;
import com.coveiot.android.customreminders.preference.PreferenceManagerCustomReminders;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userdevicesetting.GetAllUserDeviceSettingRes;
import com.coveiot.coveaccess.userdevicesetting.SaveCustomRemindersSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveCustomRemindersSettingsRes;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.sdk.ble.api.model.TimeInfo;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ReminderHelper {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4184a;

    /* loaded from: classes3.dex */
    public static final class Companion extends SingletonHolder<ReminderHelper, Context> {

        /* loaded from: classes3.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, ReminderHelper> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, ReminderHelper.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final ReminderHelper invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new ReminderHelper(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ReminderHelper(Context context) {
        this.f4184a = context;
    }

    public /* synthetic */ ReminderHelper(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final void a(final List<? extends CustomReminder> list, final ResultListener resultListener) {
        SaveCustomRemindersSettingsReq saveCustomRemindersSettingsReq = new SaveCustomRemindersSettingsReq();
        SaveCustomRemindersSettingsReq.CustomReminders customReminders = new SaveCustomRemindersSettingsReq.CustomReminders();
        customReminders.setTzOffset(RepositoryUtils.getTimeZoneOffset());
        ArrayList arrayList = new ArrayList();
        if (!AppUtils.isEmpty(list)) {
            Intrinsics.checkNotNull(list);
            for (CustomReminder customReminder : list) {
                arrayList.add(APIFormator.Companion.convertPreferenceToAPI(customReminder));
            }
        }
        customReminders.setCustomReminderList(arrayList);
        saveCustomRemindersSettingsReq.setCustomReminders(customReminders);
        CoveUserDeviceSettings.saveCustomReminderSettings(saveCustomRemindersSettingsReq, new CoveApiListener<SaveCustomRemindersSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.customreminders.utils.ReminderHelper$saveRemindersToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                resultListener.onError(coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SaveCustomRemindersSettingsRes saveCustomRemindersSettingsRes) {
                Context context;
                PreferenceManagerCustomReminders.Companion companion = PreferenceManagerCustomReminders.Companion;
                context = ReminderHelper.this.f4184a;
                companion.saveReminders(context, list);
                resultListener.onSuccess();
            }
        });
    }

    public final int getInterval(@NotNull String interval) {
        Intrinsics.checkNotNullParameter(interval, "interval");
        return (Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval, new String[]{":"}, false, 0, 6, (Object) null).get(0)) * 60) + Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval, new String[]{":"}, false, 0, 6, (Object) null).get(1));
    }

    public final int getReminderCountInPreference(@NotNull ReminderType reminderType) {
        Intrinsics.checkNotNullParameter(reminderType, "reminderType");
        List<CustomReminder> reminders = PreferenceManagerCustomReminders.Companion.getReminders(this.f4184a);
        int i = 0;
        if (reminders != null) {
            for (CustomReminder customReminder : reminders) {
                if (reminderType == ReminderType.MEDICINE && (customReminder instanceof MedicineReminder)) {
                    i++;
                }
                if (reminderType == ReminderType.MEETING && (customReminder instanceof MeetingReminder)) {
                    i++;
                }
                if (reminderType == ReminderType.OTHERS && (customReminder instanceof OtherReminder)) {
                    i++;
                }
                if (reminderType == ReminderType.DRINK && (customReminder instanceof DrinkWaterReminder)) {
                    i++;
                }
                if (reminderType == ReminderType.HAND_WASH && (customReminder instanceof HandWashReminder)) {
                    i++;
                }
            }
        }
        return i;
    }

    @Nullable
    public final TimeInfo getTimeInfo(@Nullable String str) {
        if (AppUtils.isEmpty(str)) {
            return null;
        }
        Intrinsics.checkNotNull(str);
        return new TimeInfo(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null).get(0)), Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null).get(1)));
    }

    public final void saveCustomReminderFromServer(@NotNull GetAllUserDeviceSettingRes.DataBean.CustomReminders customReminders, @NotNull ResultListener listener) {
        Calendar calendar;
        Calendar calendar2;
        Calendar calendar3;
        Intrinsics.checkNotNullParameter(customReminders, "customReminders");
        Intrinsics.checkNotNullParameter(listener, "listener");
        ArrayList<CustomReminder> arrayList = new ArrayList<>();
        for (GetAllUserDeviceSettingRes.DataBean.CustomReminders.CustomReminderListItem customReminderListItem : customReminders.getCustomReminderListItems()) {
            if (customReminderListItem.getType().equals(ReminderType.MEDICINE.name())) {
                Calendar calendar4 = Calendar.getInstance();
                calendar4.setTime(AppUtils.parseDate(customReminderListItem.getStartDate(), "yyyy-MM-dd"));
                if (AppUtils.isEmpty(customReminderListItem.getEndDate())) {
                    calendar3 = null;
                } else {
                    Calendar calendar5 = Calendar.getInstance();
                    calendar5.setTime(AppUtils.parseDate(customReminderListItem.getEndDate(), "yyyy-MM-dd"));
                    calendar3 = calendar5;
                }
                ArrayList arrayList2 = new ArrayList();
                for (String str : customReminderListItem.getRemindAt()) {
                    TimeInfo timeInfo = getTimeInfo(str);
                    Intrinsics.checkNotNull(timeInfo);
                    arrayList2.add(timeInfo);
                }
                arrayList.add(new MedicineReminder(customReminderListItem.isActive(), new RepeatModel(!Character.valueOf(customReminderListItem.getWeekDays().charAt(0)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(1)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(2)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(3)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(4)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(5)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(6)).equals(Character.valueOf(Soundex.SILENT_MARKER))), customReminderListItem.getLabel(), calendar4, calendar3, customReminderListItem.getRemindBefore(), arrayList2));
            }
            if (customReminderListItem.getType().equals(ReminderType.MEETING.name())) {
                Calendar calendar6 = Calendar.getInstance();
                calendar6.setTime(AppUtils.parseDate(customReminderListItem.getStartDate(), "yyyy-MM-dd"));
                if (AppUtils.isEmpty(customReminderListItem.getEndDate())) {
                    calendar2 = null;
                } else {
                    Calendar calendar7 = Calendar.getInstance();
                    calendar7.setTime(AppUtils.parseDate(customReminderListItem.getEndDate(), "yyyy-MM-dd"));
                    calendar2 = calendar7;
                }
                ArrayList arrayList3 = new ArrayList();
                for (String str2 : customReminderListItem.getRemindAt()) {
                    TimeInfo timeInfo2 = getTimeInfo(str2);
                    Intrinsics.checkNotNull(timeInfo2);
                    arrayList3.add(timeInfo2);
                }
                arrayList.add(new MeetingReminder(customReminderListItem.isActive(), new RepeatModel(!Character.valueOf(customReminderListItem.getWeekDays().charAt(0)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(1)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(2)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(3)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(4)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(5)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(6)).equals(Character.valueOf(Soundex.SILENT_MARKER))), customReminderListItem.getLabel(), calendar6, calendar2, customReminderListItem.getRemindBefore(), arrayList3));
            }
            if (customReminderListItem.getType().equals(ReminderType.OTHERS.name())) {
                Calendar calendar8 = Calendar.getInstance();
                calendar8.setTime(AppUtils.parseDate(customReminderListItem.getStartDate(), "yyyy-MM-dd"));
                if (AppUtils.isEmpty(customReminderListItem.getEndDate())) {
                    calendar = null;
                } else {
                    Calendar calendar9 = Calendar.getInstance();
                    calendar9.setTime(AppUtils.parseDate(customReminderListItem.getEndDate(), "yyyy-MM-dd"));
                    calendar = calendar9;
                }
                ArrayList arrayList4 = new ArrayList();
                for (String str3 : customReminderListItem.getRemindAt()) {
                    TimeInfo timeInfo3 = getTimeInfo(str3);
                    Intrinsics.checkNotNull(timeInfo3);
                    arrayList4.add(timeInfo3);
                }
                arrayList.add(new OtherReminder(customReminderListItem.isActive(), new RepeatModel(!Character.valueOf(customReminderListItem.getWeekDays().charAt(0)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(1)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(2)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(3)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(4)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(5)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(6)).equals(Character.valueOf(Soundex.SILENT_MARKER))), customReminderListItem.getLabel(), calendar8, calendar, customReminderListItem.getRemindBefore(), arrayList4));
            }
            if (customReminderListItem.getType().equals(ReminderType.HAND_WASH.name())) {
                RepeatModel repeatModel = new RepeatModel(!Character.valueOf(customReminderListItem.getWeekDays().charAt(0)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(1)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(2)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(3)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(4)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(5)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(6)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
                String label = customReminderListItem.getLabel();
                boolean isActive = customReminderListItem.isActive();
                TimeInfo timeInfo4 = getTimeInfo(customReminderListItem.getStartTime());
                TimeInfo timeInfo5 = getTimeInfo(customReminderListItem.getEndTime());
                String interval = customReminderListItem.getInterval();
                Intrinsics.checkNotNullExpressionValue(interval, "customreminder.interval");
                arrayList.add(new HandWashReminder(repeatModel, label, isActive, timeInfo4, timeInfo5, getInterval(interval)));
            }
            if (customReminderListItem.getType().equals(ReminderType.DRINK.name())) {
                RepeatModel repeatModel2 = new RepeatModel(!Character.valueOf(customReminderListItem.getWeekDays().charAt(0)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(1)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(2)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(3)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(4)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(5)).equals(Character.valueOf(Soundex.SILENT_MARKER)), !Character.valueOf(customReminderListItem.getWeekDays().charAt(6)).equals(Character.valueOf(Soundex.SILENT_MARKER)));
                String label2 = customReminderListItem.getLabel();
                boolean isActive2 = customReminderListItem.isActive();
                TimeInfo timeInfo6 = getTimeInfo(customReminderListItem.getStartTime());
                TimeInfo timeInfo7 = getTimeInfo(customReminderListItem.getEndTime());
                String interval2 = customReminderListItem.getInterval();
                Intrinsics.checkNotNullExpressionValue(interval2, "customreminder.interval");
                arrayList.add(new DrinkWaterReminder(repeatModel2, label2, isActive2, timeInfo6, timeInfo7, getInterval(interval2)));
            }
        }
        sendRemindersToWatch(arrayList, listener);
    }

    public final void sendRemindersToWatch(@Nullable final ArrayList<CustomReminder> arrayList, @NotNull final ResultListener resultListener) {
        Intrinsics.checkNotNullParameter(resultListener, "resultListener");
        ArrayList arrayList2 = new ArrayList();
        if (!AppUtils.isEmpty(arrayList)) {
            Intrinsics.checkNotNull(arrayList);
            Iterator<CustomReminder> it = arrayList.iterator();
            while (it.hasNext()) {
                CustomReminder next = it.next();
                if (next instanceof MedicineReminder) {
                    MedicineReminder medicineReminder = (MedicineReminder) next;
                    arrayList2.add(new MedicineReminderAbstract(true, CustomReminderConstants.Companion.getMEDICINE_IMAGE_ID(), medicineReminder.getRepeatModel().sunday, medicineReminder.getRepeatModel().monday, medicineReminder.getRepeatModel().tuesday, medicineReminder.getRepeatModel().wednesday, medicineReminder.getRepeatModel().thursday, medicineReminder.getRepeatModel().friday, medicineReminder.getRepeatModel().saturday, 7, medicineReminder.getDescription(), medicineReminder.getStartDate().getTime(), medicineReminder.getEndDate() == null ? null : medicineReminder.getEndDate().getTime(), medicineReminder.getAdvanceTime(), medicineReminder.getTimeInfos()));
                }
                if (next instanceof MeetingReminder) {
                    MeetingReminder meetingReminder = (MeetingReminder) next;
                    arrayList2.add(new MeetingReminderAbstract(true, CustomReminderConstants.Companion.getMEETING_IMAGE_ID(), meetingReminder.getRepeatModel().sunday, meetingReminder.getRepeatModel().monday, meetingReminder.getRepeatModel().tuesday, meetingReminder.getRepeatModel().wednesday, meetingReminder.getRepeatModel().thursday, meetingReminder.getRepeatModel().friday, meetingReminder.getRepeatModel().saturday, 7, meetingReminder.getDescription(), meetingReminder.getStartDate().getTime(), meetingReminder.getEndDate() == null ? null : meetingReminder.getEndDate().getTime(), meetingReminder.getAdvanceTime(), meetingReminder.getTimeInfos()));
                }
                if (next instanceof OtherReminder) {
                    OtherReminder otherReminder = (OtherReminder) next;
                    arrayList2.add(new OtherReminderAbstract(true, CustomReminderConstants.Companion.getOTHER_IMAGE_ID(), otherReminder.getRepeatModel().sunday, otherReminder.getRepeatModel().monday, otherReminder.getRepeatModel().tuesday, otherReminder.getRepeatModel().wednesday, otherReminder.getRepeatModel().thursday, otherReminder.getRepeatModel().friday, otherReminder.getRepeatModel().saturday, 7, otherReminder.getDescription(), otherReminder.getStartDate().getTime(), otherReminder.getEndDate() == null ? null : otherReminder.getEndDate().getTime(), otherReminder.getAdvanceTime(), otherReminder.getTimeInfos()));
                }
                if (next instanceof HandWashReminder) {
                    HandWashReminder handWashReminder = (HandWashReminder) next;
                    arrayList2.add(new HandWashReminderAbstract(true, CustomReminderConstants.Companion.getHAND_WASH_IMAGE_ID(), handWashReminder.getRepeatModel().sunday, handWashReminder.getRepeatModel().monday, handWashReminder.getRepeatModel().tuesday, handWashReminder.getRepeatModel().wednesday, handWashReminder.getRepeatModel().thursday, handWashReminder.getRepeatModel().friday, handWashReminder.getRepeatModel().saturday, 7, handWashReminder.getDescription(), handWashReminder.getStartTime(), handWashReminder.getEndTime() == null ? null : handWashReminder.getEndTime(), handWashReminder.getFrequency()));
                }
                if (next instanceof DrinkWaterReminder) {
                    DrinkWaterReminder drinkWaterReminder = (DrinkWaterReminder) next;
                    arrayList2.add(new DrinkingReminderAbstract(true, CustomReminderConstants.Companion.getDRINK_WATER_IMAGE_ID(), drinkWaterReminder.getRepeatModel().sunday, drinkWaterReminder.getRepeatModel().monday, drinkWaterReminder.getRepeatModel().tuesday, drinkWaterReminder.getRepeatModel().wednesday, drinkWaterReminder.getRepeatModel().thursday, drinkWaterReminder.getRepeatModel().friday, drinkWaterReminder.getRepeatModel().saturday, 7, drinkWaterReminder.getDescription(), drinkWaterReminder.getStartTime(), drinkWaterReminder.getEndTime(), drinkWaterReminder.getFrequency()));
                }
            }
        }
        BleApiManager.getInstance(this.f4184a).getBleApi().getData(new SetCustomRemindersRequest(arrayList2), new DataResultListener() { // from class: com.coveiot.android.customreminders.utils.ReminderHelper$sendRemindersToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                ReminderHelper.this.a(arrayList, resultListener);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }
}
