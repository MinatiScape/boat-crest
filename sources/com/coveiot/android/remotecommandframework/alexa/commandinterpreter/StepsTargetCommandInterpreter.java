package com.coveiot.android.remotecommandframework.alexa.commandinterpreter;

import android.content.Context;
import com.coveiot.android.bleabstract.request.StepsTargetRequest;
import com.coveiot.android.remotecommandframework.alexa.model.SStepsTargetInfo;
import com.coveiot.android.remotecommandframework.alexa.utils.Constants;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class StepsTargetCommandInterpreter extends CommandInterpreter<SStepsTargetInfo, StepsTargetRequest> {
    @NotNull
    public final SStepsTargetInfo b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StepsTargetCommandInterpreter(@NotNull Context context, @NotNull SStepsTargetInfo sStepsTargetInfo) {
        super(sStepsTargetInfo);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sStepsTargetInfo, "sStepsTargetInfo");
        this.b = sStepsTargetInfo;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter
    @Nullable
    public StepsTargetRequest getBleRequestObject() {
        int parseInt;
        StepsTargetRequest.Builder builder = new StepsTargetRequest.Builder();
        if (this.b.getTarget() != null) {
            Integer target = this.b.getTarget();
            Intrinsics.checkNotNull(target);
            parseInt = target.intValue();
        } else {
            parseInt = Integer.parseInt(Constants.DEFAULT_STEPS_TARGET.getValue());
        }
        StepsTargetRequest.Builder target2 = builder.setTarget(parseInt);
        Intrinsics.checkNotNullExpressionValue(target2, "Builder()\n              …EPS_TARGET.value.toInt())");
        return target2.build();
    }
}
