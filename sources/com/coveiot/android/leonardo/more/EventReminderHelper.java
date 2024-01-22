package com.coveiot.android.leonardo.more;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.EventReminder;
import com.coveiot.android.bleabstract.request.SetEventReminderListRequest;
import com.coveiot.android.bleabstract.request.SetEventReminderRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.leonardo.SingletonHolder;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.ReminderFrequency;
import com.coveiot.coveaccess.userdevicesetting.GetAllUserDeviceSettingRes;
import com.coveiot.coveaccess.userdevicesetting.SaveCustomRemindersSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveCustomRemindersSettingsRes;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.EventReminderData;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import kotlin.collections.h;
import kotlin.comparisons.f;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class EventReminderHelper {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4876a;
    public DialogListener dialogListener;

    /* loaded from: classes5.dex */
    public static final class Companion extends SingletonHolder<EventReminderHelper, Context> {

        /* loaded from: classes5.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, EventReminderHelper> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, EventReminderHelper.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final EventReminderHelper invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new EventReminderHelper(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public EventReminderHelper(Context context) {
        this.f4876a = context;
    }

    public /* synthetic */ EventReminderHelper(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final String a(int i, int i2) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%02d:%02d:00", Arrays.copyOf(new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    public final String b(EventReminder eventReminder, Calendar calendar) {
        String str = "true;OTHERS;" + eventReminder.getEventName() + ';' + AppUtils.formatDate(calendar.getTime(), "yyyy-MM-dd") + ";;;;" + a(eventReminder.getHour(), eventReminder.getMinute()) + ";;;-------;";
        LogHelper.i("reminderId === ", str);
        String convertStringToMD5 = AppUtils.convertStringToMD5(str);
        Intrinsics.checkNotNullExpressionValue(convertStringToMD5, "convertStringToMD5(reminderId)");
        LogHelper.i("reminderIdMD5 === ", convertStringToMD5);
        return convertStringToMD5;
    }

    public final String c(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        return null;
                    }
                    return ReminderFrequency.YEARLY.name();
                }
                return ReminderFrequency.MONTHLY.name();
            }
            return ReminderFrequency.WEEKLY.name();
        }
        return ReminderFrequency.DAILY.name();
    }

    @NotNull
    public final List<EventReminder> convertFromEventReminderDataToEventReminder() {
        List<EventReminderData> eventReminderData = UserDataManager.getInstance(this.f4876a).getEventReminderData();
        Intrinsics.checkNotNull(eventReminderData, "null cannot be cast to non-null type kotlin.collections.MutableList<@[FlexibleNullability] com.coveiot.covepreferences.data.EventReminderData?>");
        List<EventReminderData> asMutableList = TypeIntrinsics.asMutableList(eventReminderData);
        ArrayList arrayList = new ArrayList();
        for (EventReminderData eventReminderData2 : asMutableList) {
            EventReminder eventReminder = new EventReminder(0, null, 0, 0, 0, 0, 0, 127, null);
            eventReminder.setEventId(eventReminderData2.getEventId());
            eventReminder.setEventName(eventReminderData2.getTitle());
            eventReminder.setRepeatType(eventReminderData2.getRepeatType());
            eventReminder.setDay(eventReminderData2.getDay());
            eventReminder.setMonth(eventReminderData2.getMonth());
            eventReminder.setYear(eventReminderData2.getYear());
            eventReminder.setHour(eventReminderData2.getHour());
            eventReminder.setMinute(eventReminderData2.getMinute());
            arrayList.add(eventReminder);
        }
        if ((!arrayList.isEmpty()) && arrayList.size() > 1) {
            h.sortWith(arrayList, new Comparator() { // from class: com.coveiot.android.leonardo.more.EventReminderHelper$convertFromEventReminderDataToEventReminder$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return f.compareValues(Integer.valueOf(((EventReminder) t).getEventId()), Integer.valueOf(((EventReminder) t2).getEventId()));
                }
            });
        }
        return arrayList;
    }

    @NotNull
    public final EventReminder convertServerDataToEventReminder(@NotNull GetAllUserDeviceSettingRes.DataBean.CustomReminders.CustomReminderListItem eventReminder, int i) {
        int i2;
        int i3;
        Intrinsics.checkNotNullParameter(eventReminder, "eventReminder");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(AppUtils.parseDate(eventReminder.getStartDate(), "yyyy-MM-dd"));
        int i4 = calendar.get(5);
        int i5 = calendar.get(2);
        int i6 = calendar.get(1);
        int i7 = 0;
        if (eventReminder.getStartTime().length() == 8) {
            String startTime = eventReminder.getStartTime();
            Intrinsics.checkNotNullExpressionValue(startTime, "eventReminder.startTime");
            i3 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(0));
            String startTime2 = eventReminder.getStartTime();
            Intrinsics.checkNotNullExpressionValue(startTime2, "eventReminder.startTime");
            i2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime2, new String[]{":"}, false, 0, 6, (Object) null).get(1));
        } else {
            i2 = 0;
            i3 = 0;
        }
        if (eventReminder.getFrequency() != null) {
            i7 = d(eventReminder.getFrequency());
        }
        EventReminder eventReminder2 = new EventReminder(0, null, 0, 0, 0, 0, 0, 127, null);
        eventReminder2.setEventId(i + 1);
        eventReminder2.setEventName(eventReminder.getLabel());
        eventReminder2.setDay(i4);
        eventReminder2.setMonth(i5);
        eventReminder2.setYear(i6);
        eventReminder2.setHour(i3);
        eventReminder2.setMinute(i2);
        eventReminder2.setRepeatType(i7);
        return eventReminder2;
    }

    public final int d(String str) {
        if (!(str == null || str.length() == 0)) {
            if (Intrinsics.areEqual(str, ReminderFrequency.DAILY.name())) {
                return 1;
            }
            if (Intrinsics.areEqual(str, ReminderFrequency.WEEKLY.name())) {
                return 2;
            }
            if (Intrinsics.areEqual(str, ReminderFrequency.MONTHLY.name())) {
                return 3;
            }
            if (Intrinsics.areEqual(str, ReminderFrequency.YEARLY.name())) {
                return 4;
            }
        }
        return 0;
    }

    @NotNull
    public final DialogListener getDialogListener() {
        DialogListener dialogListener = this.dialogListener;
        if (dialogListener != null) {
            return dialogListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dialogListener");
        return null;
    }

    public final void saveEventReminderDataToPref(@NotNull List<EventReminder> reminderMutableList) {
        Intrinsics.checkNotNullParameter(reminderMutableList, "reminderMutableList");
        ArrayList arrayList = new ArrayList();
        for (EventReminder eventReminder : reminderMutableList) {
            EventReminderData companion = EventReminderData.Companion.getInstance();
            Intrinsics.checkNotNull(companion);
            companion.setTitle(eventReminder.getEventName());
            companion.setRepeatType(eventReminder.getRepeatType());
            companion.setDay(eventReminder.getDay());
            companion.setMonth(eventReminder.getMonth());
            companion.setYear(eventReminder.getYear());
            companion.setHour(eventReminder.getHour());
            companion.setMinute(eventReminder.getMinute());
            companion.setEventId(eventReminder.getEventId());
            arrayList.add(companion);
        }
        UserDataManager.getInstance(this.f4876a).saveEventReminderData(arrayList);
    }

    public final void sendEventReminderDataToServer(@NotNull final List<EventReminder> eventRemindersList, final boolean z) {
        Intrinsics.checkNotNullParameter(eventRemindersList, "eventRemindersList");
        SaveCustomRemindersSettingsReq saveCustomRemindersSettingsReq = new SaveCustomRemindersSettingsReq();
        SaveCustomRemindersSettingsReq.CustomReminders customReminders = new SaveCustomRemindersSettingsReq.CustomReminders();
        customReminders.setTzOffset(RepositoryUtils.getTimeZoneOffset());
        ArrayList arrayList = new ArrayList();
        for (EventReminder eventReminder : eventRemindersList) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1, eventReminder.getYear());
            calendar.set(2, eventReminder.getMonth());
            calendar.set(5, eventReminder.getDay());
            calendar.set(11, eventReminder.getHour());
            calendar.set(12, eventReminder.getMinute());
            calendar.set(13, 0);
            SaveCustomRemindersSettingsReq.CustomReminders.CustomRemindersListItem customRemindersListItem = new SaveCustomRemindersSettingsReq.CustomReminders.CustomRemindersListItem();
            Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
            customRemindersListItem.setReminderId(b(eventReminder, calendar));
            customRemindersListItem.setLabel(eventReminder.getEventName());
            customRemindersListItem.setActive(true);
            customRemindersListItem.setWeekDays("-------");
            customRemindersListItem.setType(ReminderType.OTHERS.name());
            customRemindersListItem.setStartDate(AppUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"));
            customRemindersListItem.setStartTime(a(eventReminder.getHour(), eventReminder.getMinute()));
            customRemindersListItem.setFrequency(c(eventReminder.getRepeatType()));
            arrayList.add(customRemindersListItem);
        }
        customReminders.setCustomReminderList(arrayList);
        saveCustomRemindersSettingsReq.setCustomReminders(customReminders);
        CoveUserDeviceSettings.saveCustomReminderSettings(saveCustomRemindersSettingsReq, new CoveApiListener<SaveCustomRemindersSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.EventReminderHelper$sendEventReminderDataToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                if (z) {
                    EventReminderHelper.this.getDialogListener().onDismiss();
                    EventReminderHelper.this.getDialogListener().showErrorDialog();
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SaveCustomRemindersSettingsRes saveCustomRemindersSettingsRes) {
                EventReminderHelper.this.saveEventReminderDataToPref(eventRemindersList);
                if (z) {
                    EventReminderHelper.this.getDialogListener().onDismiss();
                    EventReminderHelper.this.getDialogListener().showSuccessDialog();
                }
            }
        });
    }

    public final void sendEventRemindersToBand(@NotNull final List<EventReminder> eventReminders, final boolean z) {
        Intrinsics.checkNotNullParameter(eventReminders, "eventReminders");
        ArrayList arrayList = new ArrayList();
        int size = eventReminders.size();
        int i = 0;
        while (i < size) {
            EventReminder eventReminder = eventReminders.get(i);
            i++;
            arrayList.add(new SetEventReminderRequest.Builder().setEventId(i).setEventName(eventReminder.getEventName()).setDay(eventReminder.getDay()).setMonth(eventReminder.getMonth() + 1).setYear(eventReminder.getYear()).setHour(eventReminder.getHour()).setMinute(eventReminder.getMinute()).setRepeatType(eventReminder.getRepeatType()).build());
        }
        SetEventReminderListRequest build = new SetEventReminderListRequest.Builder().setEventReminderList(arrayList).build();
        if (z) {
            getDialogListener().onShowProgressDialog();
        }
        BleApiManager.getInstance(this.f4876a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.EventReminderHelper$sendEventRemindersToBand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                if (z) {
                    this.getDialogListener().onDismiss();
                    this.getDialogListener().showErrorDialog();
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                boolean z2 = z;
                if (z2) {
                    this.sendEventReminderDataToServer(eventReminders, z2);
                } else {
                    this.saveEventReminderDataToPref(eventReminders);
                }
            }
        });
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }
}
