package com.coveiot.android.remotecommandframework;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.handler.IPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.handler.IPreferenceHandlerProvider;
import com.coveiot.android.remotecommandframework.alexa.model.CommandNames;
import com.coveiot.android.remotecommandframework.pref.handler.AlarmPreferenceHandler;
import com.coveiot.android.remotecommandframework.pref.handler.AutoHrPreferenceHandler;
import com.coveiot.android.remotecommandframework.pref.handler.BatteryPreferenceHandler;
import com.coveiot.android.remotecommandframework.pref.handler.DNDPreferenceHandler;
import com.coveiot.android.remotecommandframework.pref.handler.FitnessConfigPreferenceHandler;
import com.coveiot.android.remotecommandframework.pref.handler.LiftWristToViewPreferenceHandler;
import com.coveiot.android.remotecommandframework.pref.handler.MeasurementUnitPreferenceHandler;
import com.coveiot.android.remotecommandframework.pref.handler.NotificationPreferenceHandler;
import com.coveiot.android.remotecommandframework.pref.handler.SedentaryPreferenceHandler;
import com.coveiot.android.remotecommandframework.pref.handler.SleepTargetPreferenceHandler;
import com.coveiot.android.remotecommandframework.pref.handler.StepsTargetPreferenceHandler;
import com.coveiot.android.remotecommandframework.pref.handler.TimeSettingsPreferenceHandler;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class AppPreferenceHandlerProvider implements IPreferenceHandlerProvider {
    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IPreferenceHandlerProvider
    @Nullable
    public IPreferenceHandler getPreferenceHandler(@NotNull Context context, @NotNull String commandName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(commandName, "commandName");
        if (Intrinsics.areEqual(commandName, CommandNames.SET_ALARM.getValue()) ? true : Intrinsics.areEqual(commandName, CommandNames.GET_ALARM.getValue())) {
            return new AlarmPreferenceHandler();
        }
        if (Intrinsics.areEqual(commandName, CommandNames.GET_BATTERY.getValue())) {
            return new BatteryPreferenceHandler();
        }
        if (Intrinsics.areEqual(commandName, CommandNames.SET_AUTO_HR.getValue())) {
            return new AutoHrPreferenceHandler();
        }
        if (Intrinsics.areEqual(commandName, CommandNames.SET_DND.getValue())) {
            return new DNDPreferenceHandler();
        }
        if (Intrinsics.areEqual(commandName, CommandNames.SET_FITNESS_CONFIG_INFO.getValue())) {
            return new FitnessConfigPreferenceHandler();
        }
        if (Intrinsics.areEqual(commandName, CommandNames.SET_LIFT_WRIST_TO_VIEW.getValue())) {
            return new LiftWristToViewPreferenceHandler();
        }
        if (Intrinsics.areEqual(commandName, CommandNames.SET_MEASUREMENT_UNIT.getValue())) {
            return new MeasurementUnitPreferenceHandler();
        }
        if (Intrinsics.areEqual(commandName, CommandNames.SET_NOTIFICATION.getValue())) {
            return new NotificationPreferenceHandler();
        }
        if (Intrinsics.areEqual(commandName, CommandNames.SET_SEDENTARY_REMINDER.getValue())) {
            return new SedentaryPreferenceHandler();
        }
        if (Intrinsics.areEqual(commandName, CommandNames.SET_SLEEP_TARGET.getValue())) {
            return new SleepTargetPreferenceHandler();
        }
        if (Intrinsics.areEqual(commandName, CommandNames.SET_TIME_FORMAT.getValue())) {
            return new TimeSettingsPreferenceHandler();
        }
        if (Intrinsics.areEqual(commandName, CommandNames.SET_STEP_TARGET.getValue())) {
            return new StepsTargetPreferenceHandler();
        }
        return null;
    }
}
