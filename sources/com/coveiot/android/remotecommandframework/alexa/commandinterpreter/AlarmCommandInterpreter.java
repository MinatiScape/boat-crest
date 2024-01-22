package com.coveiot.android.remotecommandframework.alexa.commandinterpreter;

import android.content.Context;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmRequest;
import com.coveiot.android.remotecommandframework.alexa.model.SAlarmInfo;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class AlarmCommandInterpreter extends CommandInterpreter<SAlarmInfo, SetVibrationAlarmRequest> {
    @NotNull
    public final SAlarmInfo b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlarmCommandInterpreter(@NotNull Context context, @NotNull SAlarmInfo sAlarmInfo) {
        super(sAlarmInfo);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sAlarmInfo, "sAlarmInfo");
        this.b = sAlarmInfo;
    }

    public final int a(SAlarmInfo sAlarmInfo) {
        String type;
        int i;
        if (sAlarmInfo.getType() == null || (type = sAlarmInfo.getType()) == null) {
            return 1;
        }
        int hashCode = type.hashCode();
        if (hashCode != -118077894) {
            if (hashCode != 2163806) {
                if (hashCode != 65314936 || !type.equals("DRINK")) {
                    return 1;
                }
                i = 3;
            } else if (!type.equals("FOOD")) {
                return 1;
            } else {
                i = 4;
            }
        } else if (!type.equals("MEDICINE")) {
            return 1;
        } else {
            i = 2;
        }
        return i;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter
    @Nullable
    public SetVibrationAlarmRequest getBleRequestObject() {
        int i;
        int i2;
        String time = this.b.getTime();
        boolean z = true;
        if (time != null) {
            i2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null).get(0));
            i = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null).get(1));
        } else {
            i = 0;
            i2 = 0;
        }
        try {
            SetVibrationAlarmRequest.Builder repeatEnabled = new SetVibrationAlarmRequest.Builder().setAlarmId(this.b.getAlarmId()).setHour(i2).setMinute(i).setEnabled(this.b.getActive()).setEventName(this.b.getLabel()).setAlarmType(a(this.b)).setRepeatEnabled(this.b.getRepeat());
            String weekDays = this.b.getWeekDays();
            Character valueOf = weekDays != null ? Character.valueOf(weekDays.charAt(0)) : null;
            Intrinsics.checkNotNull(valueOf);
            SetVibrationAlarmRequest.Builder sundayEnabled = repeatEnabled.setSundayEnabled(valueOf.charValue() != '-');
            String weekDays2 = this.b.getWeekDays();
            Character valueOf2 = weekDays2 != null ? Character.valueOf(weekDays2.charAt(1)) : null;
            Intrinsics.checkNotNull(valueOf2);
            SetVibrationAlarmRequest.Builder mondayEnabled = sundayEnabled.setMondayEnabled(valueOf2.charValue() != '-');
            String weekDays3 = this.b.getWeekDays();
            Character valueOf3 = weekDays3 != null ? Character.valueOf(weekDays3.charAt(2)) : null;
            Intrinsics.checkNotNull(valueOf3);
            SetVibrationAlarmRequest.Builder tuesdayEnabled = mondayEnabled.setTuesdayEnabled(valueOf3.charValue() != '-');
            String weekDays4 = this.b.getWeekDays();
            Character valueOf4 = weekDays4 != null ? Character.valueOf(weekDays4.charAt(3)) : null;
            Intrinsics.checkNotNull(valueOf4);
            SetVibrationAlarmRequest.Builder wednesdayEnabled = tuesdayEnabled.setWednesdayEnabled(valueOf4.charValue() != '-');
            String weekDays5 = this.b.getWeekDays();
            Character valueOf5 = weekDays5 != null ? Character.valueOf(weekDays5.charAt(4)) : null;
            Intrinsics.checkNotNull(valueOf5);
            SetVibrationAlarmRequest.Builder thursdayEnabled = wednesdayEnabled.setThursdayEnabled(valueOf5.charValue() != '-');
            String weekDays6 = this.b.getWeekDays();
            Character valueOf6 = weekDays6 != null ? Character.valueOf(weekDays6.charAt(5)) : null;
            Intrinsics.checkNotNull(valueOf6);
            SetVibrationAlarmRequest.Builder fridayEnabled = thursdayEnabled.setFridayEnabled(valueOf6.charValue() != '-');
            String weekDays7 = this.b.getWeekDays();
            Character valueOf7 = weekDays7 != null ? Character.valueOf(weekDays7.charAt(6)) : null;
            Intrinsics.checkNotNull(valueOf7);
            if (valueOf7.charValue() == '-') {
                z = false;
            }
            return fridayEnabled.setSaturdayEnabled(z).build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
