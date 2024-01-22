package com.coveiot.android.remotecommandframework.pref.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.handler.ISedentaryPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SSedentaryInfo;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class SedentaryPreferenceHandler implements ISedentaryPreferenceHandler {
    public final int a(String str) {
        if (str == null || str.length() != 8) {
            return 60;
        }
        int parseInt = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null).get(0));
        int parseInt2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null).get(1));
        Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null).get(2));
        return (parseInt * 60) + parseInt2;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00b4  */
    @Override // com.coveiot.android.remotecommandframework.alexa.handler.ISedentaryPreferenceHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isAlreadySet(@org.jetbrains.annotations.NotNull android.content.Context r13, @org.jetbrains.annotations.NotNull com.coveiot.android.remotecommandframework.alexa.model.SSedentaryInfo r14) {
        /*
            r12 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "sSedentaryInfo"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = r14.getStartTime()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L1b
            int r0 = r0.length()
            if (r0 != 0) goto L19
            goto L1b
        L19:
            r0 = r2
            goto L1c
        L1b:
            r0 = r1
        L1c:
            if (r0 != 0) goto La4
            java.lang.String r0 = r14.getEndTime()
            if (r0 == 0) goto L2d
            int r0 = r0.length()
            if (r0 != 0) goto L2b
            goto L2d
        L2b:
            r0 = r2
            goto L2e
        L2d:
            r0 = r1
        L2e:
            if (r0 != 0) goto La4
            java.lang.String r3 = r14.getStartTime()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            java.lang.String r0 = ":"
            java.lang.String[] r4 = new java.lang.String[]{r0}
            r5 = 0
            r6 = 0
            r7 = 6
            r8 = 0
            java.util.List r3 = kotlin.text.StringsKt__StringsKt.split$default(r3, r4, r5, r6, r7, r8)
            java.lang.Object r3 = r3.get(r2)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = java.lang.Integer.parseInt(r3)
            java.lang.String r4 = r14.getStartTime()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            java.lang.String[] r5 = new java.lang.String[]{r0}
            r7 = 0
            r8 = 6
            r9 = 0
            java.util.List r4 = kotlin.text.StringsKt__StringsKt.split$default(r4, r5, r6, r7, r8, r9)
            java.lang.Object r4 = r4.get(r1)
            java.lang.String r4 = (java.lang.String) r4
            int r4 = java.lang.Integer.parseInt(r4)
            java.lang.String r5 = r14.getEndTime()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            java.lang.String[] r6 = new java.lang.String[]{r0}
            r8 = 0
            r9 = 6
            r10 = 0
            java.util.List r5 = kotlin.text.StringsKt__StringsKt.split$default(r5, r6, r7, r8, r9, r10)
            java.lang.Object r5 = r5.get(r2)
            java.lang.String r5 = (java.lang.String) r5
            int r5 = java.lang.Integer.parseInt(r5)
            java.lang.String r6 = r14.getEndTime()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            java.lang.String[] r7 = new java.lang.String[]{r0}
            r9 = 0
            r10 = 6
            r11 = 0
            java.util.List r0 = kotlin.text.StringsKt__StringsKt.split$default(r6, r7, r8, r9, r10, r11)
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            int r0 = java.lang.Integer.parseInt(r0)
            goto Laa
        La4:
            r3 = 9
            r5 = 21
            r0 = r2
            r4 = r0
        Laa:
            com.coveiot.covepreferences.UserDataManager r13 = com.coveiot.covepreferences.UserDataManager.getInstance(r13)
            com.coveiot.covepreferences.data.SedentaryReminderData r13 = r13.getSedentaryReminderData()
            if (r13 == 0) goto Lf3
            boolean r6 = r13.getAlarm_on_off()
            if (r6 != 0) goto Lc8
            java.lang.Boolean r6 = r14.getActive()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            boolean r6 = r6.booleanValue()
            if (r6 != 0) goto Lc8
            goto Lf4
        Lc8:
            boolean r6 = r13.getAlarm_on_off()
            if (r6 == 0) goto Lf3
            java.lang.Boolean r14 = r14.getActive()
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual(r14, r6)
            if (r14 == 0) goto Lf3
            int r14 = r13.getBeggining_time_hour()
            if (r14 != r3) goto Lf3
            int r14 = r13.getBeggining_time_minutes()
            if (r14 != r4) goto Lf3
            int r14 = r13.getEnd_time_hour()
            if (r14 != r5) goto Lf3
            int r13 = r13.getEnd_time_minutes()
            if (r13 != r0) goto Lf3
            goto Lf4
        Lf3:
            r1 = r2
        Lf4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.remotecommandframework.pref.handler.SedentaryPreferenceHandler.isAlreadySet(android.content.Context, com.coveiot.android.remotecommandframework.alexa.model.SSedentaryInfo):boolean");
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.ISedentaryPreferenceHandler
    public boolean isValidInput(@NotNull Context context, @NotNull SSedentaryInfo sSedentaryInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sSedentaryInfo, "sSedentaryInfo");
        int a2 = a(sSedentaryInfo.getInterval());
        return (60 <= a2 && a2 < 151) && a2 % 30 == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00c7  */
    @Override // com.coveiot.android.remotecommandframework.alexa.handler.ISedentaryPreferenceHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void update(@org.jetbrains.annotations.NotNull android.content.Context r19, @org.jetbrains.annotations.NotNull com.coveiot.android.remotecommandframework.alexa.model.SSedentaryInfo r20, @org.jetbrains.annotations.NotNull com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener r21) {
        /*
            Method dump skipped, instructions count: 331
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.remotecommandframework.pref.handler.SedentaryPreferenceHandler.update(android.content.Context, com.coveiot.android.remotecommandframework.alexa.model.SSedentaryInfo, com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener):void");
    }
}
