package com.coveiot.android.remotecommandframework.alexa.commandinterpreter;

import android.content.Context;
import com.coveiot.android.bleabstract.request.SetHourFormatRequest;
import com.coveiot.android.remotecommandframework.alexa.model.STimeFormatInfo;
import com.coveiot.android.remotecommandframework.alexa.utils.Constants;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class TimeFormatCommandInterpreter extends CommandInterpreter<STimeFormatInfo, SetHourFormatRequest> {
    @NotNull
    public final STimeFormatInfo b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimeFormatCommandInterpreter(@NotNull Context context, @NotNull STimeFormatInfo sTimeFormatInfo) {
        super(sTimeFormatInfo);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sTimeFormatInfo, "sTimeFormatInfo");
        this.b = sTimeFormatInfo;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter
    @Nullable
    public SetHourFormatRequest getBleRequestObject() {
        return new SetHourFormatRequest.Builder(Intrinsics.areEqual(this.b.getClockFormat(), Constants.TIME_FORMAT_12H.getValue())).build();
    }
}
