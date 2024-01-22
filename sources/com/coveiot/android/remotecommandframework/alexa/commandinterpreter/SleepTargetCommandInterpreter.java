package com.coveiot.android.remotecommandframework.alexa.commandinterpreter;

import android.content.Context;
import com.coveiot.android.bleabstract.request.SetSleepTargetRequest;
import com.coveiot.android.remotecommandframework.alexa.model.SSleepTargetInfo;
import com.coveiot.android.remotecommandframework.alexa.utils.Constants;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SleepTargetCommandInterpreter extends CommandInterpreter<SSleepTargetInfo, SetSleepTargetRequest> {
    @NotNull
    public final SSleepTargetInfo b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SleepTargetCommandInterpreter(@NotNull Context context, @NotNull SSleepTargetInfo sSleepTargetInfo) {
        super(sSleepTargetInfo);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sSleepTargetInfo, "sSleepTargetInfo");
        this.b = sSleepTargetInfo;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter
    @Nullable
    public SetSleepTargetRequest getBleRequestObject() {
        int parseInt;
        SetSleepTargetRequest.Builder builder = new SetSleepTargetRequest.Builder();
        if (this.b.getTarget() != null) {
            Integer target = this.b.getTarget();
            Intrinsics.checkNotNull(target);
            parseInt = target.intValue();
        } else {
            parseInt = Integer.parseInt(Constants.DEFAULT_SLEEP_TARGET.getValue());
        }
        return builder.setTarget(parseInt).build();
    }
}
