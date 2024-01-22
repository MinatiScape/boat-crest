package com.coveiot.android.dashboard2;

import android.content.Context;
import com.coveiot.android.bleabstract.request.ReminderType;
import com.coveiot.android.bleabstract.request.SetReminderRequest;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.SedentaryReminderData;
import com.coveiot.covepreferences.data.VibrationAlarmData;
import com.coveiot.sdk.ble.api.model.AlarmInfo;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class DataHelper {
    @NotNull
    public static final DataHelper INSTANCE = new DataHelper();

    @Nullable
    public final SetReminderRequest getSedentaryReminderData(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SedentaryReminderData sedentaryReminderData = UserDataManager.getInstance(context).getSedentaryReminderData();
        if (sedentaryReminderData != null) {
            return new SetReminderRequest.Builder().setStartHour1(sedentaryReminderData.getBeggining_time_hour()).setEndHour1(sedentaryReminderData.getEnd_time_hour()).setStartMin1(sedentaryReminderData.getBeggining_time_minutes()).setEndMin1(sedentaryReminderData.getEnd_time_minutes()).setReminderInterval(sedentaryReminderData.getRemind_in() >= 60 ? sedentaryReminderData.getRemind_in() : 60).setEnabled(sedentaryReminderData.getAlarm_on_off()).setReminderType(ReminderType.SEDENTARY_REMINDER).build();
        }
        return null;
    }

    @NotNull
    public final List<AlarmInfo> getVibrationAlarmData(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        List<VibrationAlarmData> vibrationAlarmData = UserDataManager.getInstance(context).getVibrationAlarmData();
        Intrinsics.checkNotNull(vibrationAlarmData, "null cannot be cast to non-null type kotlin.collections.MutableList<@[FlexibleNullability] com.coveiot.covepreferences.data.VibrationAlarmData?>");
        List<VibrationAlarmData> asMutableList = TypeIntrinsics.asMutableList(vibrationAlarmData);
        ArrayList arrayList = new ArrayList();
        for (VibrationAlarmData vibrationAlarmData2 : asMutableList) {
            AlarmInfo alarmInfo = new AlarmInfo();
            alarmInfo.setEventName(vibrationAlarmData2.getEvent_name());
            alarmInfo.setAlarmType(vibrationAlarmData2.getAlarmType());
            String alarmId = vibrationAlarmData2.getAlarmId();
            Intrinsics.checkNotNullExpressionValue(alarmId, "mutlistarr.alarmId");
            alarmInfo.setAlarmId(Integer.parseInt(alarmId));
            alarmInfo.setHour(vibrationAlarmData2.getEvent_time_hour());
            alarmInfo.setMinute(vibrationAlarmData2.getEvent_time_minutes());
            alarmInfo.setDaysSelected(new AlarmInfo.Days(vibrationAlarmData2.isSunday(), vibrationAlarmData2.isMonday(), vibrationAlarmData2.isTuesday(), vibrationAlarmData2.isWednesday(), vibrationAlarmData2.isThrusday(), vibrationAlarmData2.isFriday(), vibrationAlarmData2.isSaturday()));
            alarmInfo.setAlarmOn(vibrationAlarmData2.getSwitch_on_off());
            arrayList.add(alarmInfo);
        }
        return arrayList;
    }
}
