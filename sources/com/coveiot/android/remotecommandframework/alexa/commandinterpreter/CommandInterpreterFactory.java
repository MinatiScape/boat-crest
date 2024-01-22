package com.coveiot.android.remotecommandframework.alexa.commandinterpreter;

import android.content.Context;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.remotecommandframework.alexa.model.SAlarmInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SAutoHrInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SCommandInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SDNDInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SFitnessConfigInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SLiftWristToViewInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SMeasurementUnitInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SSedentaryInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SSleepTargetInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SStepsTargetInfo;
import com.coveiot.android.remotecommandframework.alexa.model.STimeFormatInfo;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class CommandInterpreterFactory {
    @NotNull
    public static final CommandInterpreterFactory INSTANCE = new CommandInterpreterFactory();

    @Nullable
    public final CommandInterpreter<SCommandInfo, BleBaseRequest> getCommandInterpreter(@NotNull Context context, @NotNull SCommandInfo sRequestObject) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sRequestObject, "sRequestObject");
        if (sRequestObject instanceof SAlarmInfo) {
            return new AlarmCommandInterpreter(context, (SAlarmInfo) sRequestObject);
        }
        if (sRequestObject instanceof SAutoHrInfo) {
            return new AutoHrCommandInterpreter(context, (SAutoHrInfo) sRequestObject);
        }
        if (sRequestObject instanceof SDNDInfo) {
            return new DndCommandInterpreter(context, (SDNDInfo) sRequestObject);
        }
        if (sRequestObject instanceof SFitnessConfigInfo) {
            return new FitnessConfigCommandInterpreter(context, (SFitnessConfigInfo) sRequestObject);
        }
        if (sRequestObject instanceof SLiftWristToViewInfo) {
            return new LiftWristToViewCommandInterpreter(context, (SLiftWristToViewInfo) sRequestObject);
        }
        if (sRequestObject instanceof SMeasurementUnitInfo) {
            return new MeasurementUnitTypeCommandInterpreter(context, (SMeasurementUnitInfo) sRequestObject);
        }
        boolean z = sRequestObject instanceof SSedentaryInfo;
        if (z) {
            return new SedentaryCommandInterpreter(context, (SSedentaryInfo) sRequestObject);
        }
        if (sRequestObject instanceof SSleepTargetInfo) {
            return new SleepTargetCommandInterpreter(context, (SSleepTargetInfo) sRequestObject);
        }
        if (sRequestObject instanceof SStepsTargetInfo) {
            return new StepsTargetCommandInterpreter(context, (SStepsTargetInfo) sRequestObject);
        }
        if (sRequestObject instanceof STimeFormatInfo) {
            return new TimeFormatCommandInterpreter(context, (STimeFormatInfo) sRequestObject);
        }
        if (z) {
            return new SedentaryCommandInterpreter(context, (SSedentaryInfo) sRequestObject);
        }
        return null;
    }
}
