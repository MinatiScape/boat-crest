package com.coveiot.android.remotecommandframework.alexa.commandinterpreter;

import android.content.Context;
import com.coveiot.android.bleabstract.request.HeartRateTimeIntervalRequest;
import com.coveiot.android.remotecommandframework.alexa.model.SAutoHrInfo;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class AutoHrCommandInterpreter extends CommandInterpreter<SAutoHrInfo, HeartRateTimeIntervalRequest> {
    @NotNull
    public final SAutoHrInfo b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoHrCommandInterpreter(@NotNull Context context, @NotNull SAutoHrInfo sAutoHrInfo) {
        super(sAutoHrInfo);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sAutoHrInfo, "sAutoHrInfo");
        this.b = sAutoHrInfo;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter
    @Nullable
    public HeartRateTimeIntervalRequest getBleRequestObject() {
        int i;
        String interval = this.b.getInterval();
        if (((interval == null || interval.length() != 8) ? null : 1) != null) {
            String interval2 = this.b.getInterval();
            Intrinsics.checkNotNull(interval2);
            int parseInt = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval2, new String[]{":"}, false, 0, 6, (Object) null).get(0));
            String interval3 = this.b.getInterval();
            Intrinsics.checkNotNull(interval3);
            int parseInt2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval3, new String[]{":"}, false, 0, 6, (Object) null).get(1));
            String interval4 = this.b.getInterval();
            Intrinsics.checkNotNull(interval4);
            Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval4, new String[]{":"}, false, 0, 6, (Object) null).get(2));
            i = (parseInt * 60) + parseInt2;
        } else {
            i = 30;
        }
        Boolean active = this.b.getActive();
        Boolean bool = Boolean.TRUE;
        HeartRateTimeIntervalRequest.Builder builder = new HeartRateTimeIntervalRequest.Builder(Intrinsics.areEqual(active, bool) ? i : 0);
        builder.m40setOpen(Intrinsics.areEqual(this.b.getActive(), bool) ? 1 : 0);
        return builder.build();
    }
}
