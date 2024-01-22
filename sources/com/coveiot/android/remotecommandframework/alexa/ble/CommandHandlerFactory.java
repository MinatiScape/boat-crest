package com.coveiot.android.remotecommandframework.alexa.ble;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.handler.IAlarmPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.handler.IAutoHrPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.handler.IBatteryPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.handler.IDNDPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.handler.IFitnessConfigPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.handler.ILiftWristToViewPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.handler.IMeasurementUnitPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.handler.INotificationPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.handler.IPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.handler.IPreferenceHandlerProvider;
import com.coveiot.android.remotecommandframework.alexa.handler.ISedentaryPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.handler.ISleepTargetPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.handler.IStepsTargetPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.handler.ISyncPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.handler.ITimeSettingsPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.CommandNames;
import com.coveiot.android.remotecommandframework.alexa.request.model.Command;
import com.coveiot.android.remotecommandframework.alexa.response.listener.CommandResponseListener;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class CommandHandlerFactory {
    @NotNull
    public static final CommandHandlerFactory INSTANCE = new CommandHandlerFactory();

    @Nullable
    public final CommandHandler getCommandHandler(@NotNull Context context, @NotNull Command command, @NotNull CommandResponseListener commandResponseListener, @NotNull IPreferenceHandlerProvider preferenceHandlerProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(commandResponseListener, "commandResponseListener");
        Intrinsics.checkNotNullParameter(preferenceHandlerProvider, "preferenceHandlerProvider");
        String name = command.getName();
        if (Intrinsics.areEqual(name, CommandNames.SET_ALARM.getValue()) ? true : Intrinsics.areEqual(name, CommandNames.GET_ALARM.getValue())) {
            IPreferenceHandler preferenceHandler = preferenceHandlerProvider.getPreferenceHandler(context, name);
            Intrinsics.checkNotNull(preferenceHandler, "null cannot be cast to non-null type com.coveiot.android.remotecommandframework.alexa.handler.IAlarmPreferenceHandler");
            return new AlarmHandler(context, command, commandResponseListener, (IAlarmPreferenceHandler) preferenceHandler);
        } else if (Intrinsics.areEqual(name, CommandNames.GET_BATTERY.getValue())) {
            IPreferenceHandler preferenceHandler2 = preferenceHandlerProvider.getPreferenceHandler(context, name);
            Intrinsics.checkNotNull(preferenceHandler2, "null cannot be cast to non-null type com.coveiot.android.remotecommandframework.alexa.handler.IBatteryPreferenceHandler");
            return new BatteryHandler(context, command, commandResponseListener, (IBatteryPreferenceHandler) preferenceHandler2);
        } else if (Intrinsics.areEqual(name, CommandNames.GET_USER_DAY_SUMMARY.getValue())) {
            IPreferenceHandler preferenceHandler3 = preferenceHandlerProvider.getPreferenceHandler(context, name);
            Intrinsics.checkNotNull(preferenceHandler3, "null cannot be cast to non-null type com.coveiot.android.remotecommandframework.alexa.handler.ISyncPreferenceHandler");
            return new SyncHandler(context, command, commandResponseListener, (ISyncPreferenceHandler) preferenceHandler3);
        } else if (Intrinsics.areEqual(name, CommandNames.SET_DND.getValue())) {
            IPreferenceHandler preferenceHandler4 = preferenceHandlerProvider.getPreferenceHandler(context, name);
            Intrinsics.checkNotNull(preferenceHandler4, "null cannot be cast to non-null type com.coveiot.android.remotecommandframework.alexa.handler.IDNDPreferenceHandler");
            return new DNDHandler(context, command, commandResponseListener, (IDNDPreferenceHandler) preferenceHandler4);
        } else if (Intrinsics.areEqual(name, CommandNames.SET_LIFT_WRIST_TO_VIEW.getValue())) {
            IPreferenceHandler preferenceHandler5 = preferenceHandlerProvider.getPreferenceHandler(context, name);
            Intrinsics.checkNotNull(preferenceHandler5, "null cannot be cast to non-null type com.coveiot.android.remotecommandframework.alexa.handler.ILiftWristToViewPreferenceHandler");
            return new LiftWristToViewHandler(context, command, commandResponseListener, (ILiftWristToViewPreferenceHandler) preferenceHandler5);
        } else if (Intrinsics.areEqual(name, CommandNames.SET_TIME_FORMAT.getValue())) {
            IPreferenceHandler preferenceHandler6 = preferenceHandlerProvider.getPreferenceHandler(context, name);
            Intrinsics.checkNotNull(preferenceHandler6, "null cannot be cast to non-null type com.coveiot.android.remotecommandframework.alexa.handler.ITimeSettingsPreferenceHandler");
            return new TimeSettingHandler(context, command, commandResponseListener, (ITimeSettingsPreferenceHandler) preferenceHandler6);
        } else if (Intrinsics.areEqual(name, CommandNames.SET_SEDENTARY_REMINDER.getValue())) {
            IPreferenceHandler preferenceHandler7 = preferenceHandlerProvider.getPreferenceHandler(context, name);
            Intrinsics.checkNotNull(preferenceHandler7, "null cannot be cast to non-null type com.coveiot.android.remotecommandframework.alexa.handler.ISedentaryPreferenceHandler");
            return new SedentaryHandler(context, command, commandResponseListener, (ISedentaryPreferenceHandler) preferenceHandler7);
        } else if (Intrinsics.areEqual(name, CommandNames.SET_AUTO_HR.getValue())) {
            IPreferenceHandler preferenceHandler8 = preferenceHandlerProvider.getPreferenceHandler(context, name);
            Intrinsics.checkNotNull(preferenceHandler8, "null cannot be cast to non-null type com.coveiot.android.remotecommandframework.alexa.handler.IAutoHrPreferenceHandler");
            return new AutoHrHandler(context, command, commandResponseListener, (IAutoHrPreferenceHandler) preferenceHandler8);
        } else if (Intrinsics.areEqual(name, CommandNames.SET_STEP_TARGET.getValue())) {
            IPreferenceHandler preferenceHandler9 = preferenceHandlerProvider.getPreferenceHandler(context, name);
            Intrinsics.checkNotNull(preferenceHandler9, "null cannot be cast to non-null type com.coveiot.android.remotecommandframework.alexa.handler.IStepsTargetPreferenceHandler");
            return new StepsTargetHandler(context, command, commandResponseListener, (IStepsTargetPreferenceHandler) preferenceHandler9);
        } else if (Intrinsics.areEqual(name, CommandNames.SET_FITNESS_CONFIG_INFO.getValue())) {
            IPreferenceHandler preferenceHandler10 = preferenceHandlerProvider.getPreferenceHandler(context, name);
            Intrinsics.checkNotNull(preferenceHandler10, "null cannot be cast to non-null type com.coveiot.android.remotecommandframework.alexa.handler.IFitnessConfigPreferenceHandler");
            return new FitnessConfigHandler(context, command, commandResponseListener, (IFitnessConfigPreferenceHandler) preferenceHandler10);
        } else if (Intrinsics.areEqual(name, CommandNames.SET_MEASUREMENT_UNIT.getValue())) {
            IPreferenceHandler preferenceHandler11 = preferenceHandlerProvider.getPreferenceHandler(context, name);
            Intrinsics.checkNotNull(preferenceHandler11, "null cannot be cast to non-null type com.coveiot.android.remotecommandframework.alexa.handler.IMeasurementUnitPreferenceHandler");
            return new MeasurementUnitHandler(context, command, commandResponseListener, (IMeasurementUnitPreferenceHandler) preferenceHandler11);
        } else if (Intrinsics.areEqual(name, CommandNames.SET_NOTIFICATION.getValue())) {
            IPreferenceHandler preferenceHandler12 = preferenceHandlerProvider.getPreferenceHandler(context, name);
            Intrinsics.checkNotNull(preferenceHandler12, "null cannot be cast to non-null type com.coveiot.android.remotecommandframework.alexa.handler.INotificationPreferenceHandler");
            return new NotificationHandler(context, command, commandResponseListener, (INotificationPreferenceHandler) preferenceHandler12);
        } else if (Intrinsics.areEqual(name, CommandNames.SET_SLEEP_TARGET.getValue())) {
            IPreferenceHandler preferenceHandler13 = preferenceHandlerProvider.getPreferenceHandler(context, name);
            Intrinsics.checkNotNull(preferenceHandler13, "null cannot be cast to non-null type com.coveiot.android.remotecommandframework.alexa.handler.ISleepTargetPreferenceHandler");
            return new SleepTargetHandler(context, command, commandResponseListener, (ISleepTargetPreferenceHandler) preferenceHandler13);
        } else {
            return null;
        }
    }
}
