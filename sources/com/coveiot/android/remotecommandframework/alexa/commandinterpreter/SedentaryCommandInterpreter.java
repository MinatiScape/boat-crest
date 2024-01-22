package com.coveiot.android.remotecommandframework.alexa.commandinterpreter;

import android.content.Context;
import com.coveiot.android.bleabstract.request.ReminderType;
import com.coveiot.android.bleabstract.request.SetReminderRequest;
import com.coveiot.android.remotecommandframework.alexa.model.SSedentaryInfo;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SedentaryCommandInterpreter extends CommandInterpreter<SSedentaryInfo, SetReminderRequest> {
    @NotNull
    public final SSedentaryInfo b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SedentaryCommandInterpreter(@NotNull Context context, @NotNull SSedentaryInfo sSedentaryInfo) {
        super(sSedentaryInfo);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sSedentaryInfo, "sSedentaryInfo");
        this.b = sSedentaryInfo;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter
    @Nullable
    public SetReminderRequest getBleRequestObject() {
        int i;
        int i2;
        int i3;
        int i4;
        if (this.b.getStartTime() == null || this.b.getEndTime() == null) {
            i = 23;
            i2 = 59;
            i3 = 0;
            i4 = 0;
        } else {
            String startTime = this.b.getStartTime();
            Intrinsics.checkNotNull(startTime);
            i3 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(0));
            String startTime2 = this.b.getStartTime();
            Intrinsics.checkNotNull(startTime2);
            i4 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime2, new String[]{":"}, false, 0, 6, (Object) null).get(1));
            String endTime = this.b.getEndTime();
            Intrinsics.checkNotNull(endTime);
            i = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime, new String[]{":"}, false, 0, 6, (Object) null).get(0));
            String endTime2 = this.b.getEndTime();
            Intrinsics.checkNotNull(endTime2);
            i2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) endTime2, new String[]{":"}, false, 0, 6, (Object) null).get(1));
        }
        SetReminderRequest.Builder builder = new SetReminderRequest.Builder();
        builder.setStartHour1(i3);
        builder.setStartMin1(i4);
        builder.setEndHour1(i);
        builder.setEndMin1(i2);
        builder.setEnabled(Intrinsics.areEqual(this.b.getActive(), Boolean.TRUE));
        builder.setReminderType(ReminderType.SEDENTARY_REMINDER);
        int i5 = 15;
        String interval = this.b.getInterval();
        if (interval != null && interval.length() == 8) {
            String interval2 = this.b.getInterval();
            Intrinsics.checkNotNull(interval2);
            int parseInt = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval2, new String[]{":"}, false, 0, 6, (Object) null).get(0));
            String interval3 = this.b.getInterval();
            Intrinsics.checkNotNull(interval3);
            int parseInt2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval3, new String[]{":"}, false, 0, 6, (Object) null).get(1));
            String interval4 = this.b.getInterval();
            Intrinsics.checkNotNull(interval4);
            Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) interval4, new String[]{":"}, false, 0, 6, (Object) null).get(2));
            i5 = (parseInt * 60) + parseInt2;
        }
        builder.setReminderInterval(i5);
        return builder.build();
    }
}
