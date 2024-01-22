package com.coveiot.android.remotecommandframework.alexa.commandinterpreter;

import android.content.Context;
import com.coveiot.android.bleabstract.request.SetLiftWristRequest;
import com.coveiot.android.remotecommandframework.alexa.model.SLiftWristToViewInfo;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class LiftWristToViewCommandInterpreter extends CommandInterpreter<SLiftWristToViewInfo, SetLiftWristRequest> {
    @NotNull
    public final SLiftWristToViewInfo b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiftWristToViewCommandInterpreter(@NotNull Context context, @NotNull SLiftWristToViewInfo sLiftWristToViewInfo) {
        super(sLiftWristToViewInfo);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sLiftWristToViewInfo, "sLiftWristToViewInfo");
        this.b = sLiftWristToViewInfo;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter
    @Nullable
    public SetLiftWristRequest getBleRequestObject() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        if (this.b.getStartTime() == null || this.b.getEndTime() == null) {
            i = 0;
            i2 = 23;
            i3 = 59;
        } else {
            String startTime = this.b.getStartTime();
            Intrinsics.checkNotNull(startTime);
            int parseInt = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(0));
            String startTime2 = this.b.getStartTime();
            Intrinsics.checkNotNull(startTime2);
            i = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime2, new String[]{":"}, false, 0, 6, (Object) null).get(1));
            String endTime = this.b.getEndTime();
            Intrinsics.checkNotNull(endTime);
            int parseInt2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime, new String[]{":"}, false, 0, 6, (Object) null).get(0));
            String endTime2 = this.b.getEndTime();
            Intrinsics.checkNotNull(endTime2);
            i3 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime2, new String[]{":"}, false, 0, 6, (Object) null).get(1));
            i2 = parseInt2;
            i4 = parseInt;
        }
        SetLiftWristRequest.Builder builder = new SetLiftWristRequest.Builder(this.b.getActive());
        builder.setStartHour(i4);
        builder.setStartMinute(i);
        builder.setEndHour(i2);
        builder.setEndMinute(i3);
        return builder.build();
    }
}
