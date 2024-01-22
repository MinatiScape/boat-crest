package com.coveiot.android.remotecommandframework.pref.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.ConnectedDeviceInfoUtil;
import com.coveiot.android.remotecommandframework.alexa.handler.IDNDPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SDNDInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SScheduleInfo;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.DoNotDisturbData;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class DNDPreferenceHandler implements IDNDPreferenceHandler {
    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IDNDPreferenceHandler
    public boolean isAlreadySet(@NotNull Context context, @NotNull SDNDInfo sDNDInfo) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        List<SScheduleInfo> schedules;
        SScheduleInfo sScheduleInfo;
        SScheduleInfo sScheduleInfo2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sDNDInfo, "sDNDInfo");
        if (ConnectedDeviceInfoUtil.isCZDevice(context) || ConnectedDeviceInfoUtil.isCADevice(context)) {
            i = 0;
            i2 = 0;
        } else {
            i2 = 59;
            i = 23;
        }
        if (sDNDInfo.getSchedules() != null) {
            Intrinsics.checkNotNull(sDNDInfo.getSchedules());
            if (!schedules.isEmpty()) {
                List<SScheduleInfo> schedules2 = sDNDInfo.getSchedules();
                String str = null;
                String startTime = (schedules2 == null || (sScheduleInfo2 = schedules2.get(0)) == null) ? null : sScheduleInfo2.getStartTime();
                Intrinsics.checkNotNull(startTime);
                List<SScheduleInfo> schedules3 = sDNDInfo.getSchedules();
                if (schedules3 != null && (sScheduleInfo = schedules3.get(0)) != null) {
                    str = sScheduleInfo.getEndTime();
                }
                Intrinsics.checkNotNull(str);
                i3 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(0));
                i6 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(1));
                int parseInt = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null).get(0));
                i4 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null).get(1));
                i5 = parseInt;
                DoNotDisturbData doNotDisturbData = UserDataManager.getInstance(context).getDoNotDisturbData();
                Intrinsics.checkNotNullExpressionValue(doNotDisturbData, "getInstance(context).doNotDisturbData");
                boolean z = !doNotDisturbData.isDnd_on_off() || doNotDisturbData.isSchedule_on_off();
                if (!sDNDInfo.getActive() || z) {
                    if (!sDNDInfo.getActive() && z) {
                        if (doNotDisturbData.isSchedule_on_off()) {
                            if (i6 != doNotDisturbData.getBeggining_time_minutes() || i3 != doNotDisturbData.getBeggining_time_hour() || i4 != doNotDisturbData.getEnd_time_minutes() || i5 != doNotDisturbData.getEnd_time_hour()) {
                                return false;
                            }
                        } else if ((i3 != 0 || i6 != 0 || i5 != 0 || i4 != 0) && (i3 != 0 || i6 != 0 || i5 != 23 || i4 != 59)) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        i3 = 0;
        i4 = i2;
        i5 = i;
        i6 = 0;
        DoNotDisturbData doNotDisturbData2 = UserDataManager.getInstance(context).getDoNotDisturbData();
        Intrinsics.checkNotNullExpressionValue(doNotDisturbData2, "getInstance(context).doNotDisturbData");
        if (doNotDisturbData2.isDnd_on_off()) {
        }
        if (!sDNDInfo.getActive()) {
        }
        return !sDNDInfo.getActive() ? false : false;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IDNDPreferenceHandler
    public boolean isValidInput(@NotNull Context context, @NotNull SDNDInfo sDNDInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sDNDInfo, "sDNDInfo");
        return true;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IDNDPreferenceHandler
    public void update(@NotNull Context context, @NotNull SDNDInfo sDNDInfo, @NotNull SuccessListener successListener) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        SScheduleInfo sScheduleInfo;
        SScheduleInfo sScheduleInfo2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sDNDInfo, "sDNDInfo");
        Intrinsics.checkNotNullParameter(successListener, "successListener");
        if (ConnectedDeviceInfoUtil.isCZDevice(context) || ConnectedDeviceInfoUtil.isCADevice(context)) {
            i = 0;
            i2 = 0;
        } else {
            i2 = 59;
            i = 23;
        }
        List<SScheduleInfo> schedules = sDNDInfo.getSchedules();
        if (schedules == null || schedules.isEmpty()) {
            i3 = 0;
            i4 = i2;
            i5 = i;
            i6 = 0;
        } else {
            List<SScheduleInfo> schedules2 = sDNDInfo.getSchedules();
            String str = null;
            String startTime = (schedules2 == null || (sScheduleInfo2 = schedules2.get(0)) == null) ? null : sScheduleInfo2.getStartTime();
            Intrinsics.checkNotNull(startTime);
            List<SScheduleInfo> schedules3 = sDNDInfo.getSchedules();
            if (schedules3 != null && (sScheduleInfo = schedules3.get(0)) != null) {
                str = sScheduleInfo.getEndTime();
            }
            Intrinsics.checkNotNull(str);
            int parseInt = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(0));
            i6 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).get(1));
            int parseInt2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null).get(0));
            i4 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null).get(1));
            i3 = parseInt;
            i5 = parseInt2;
        }
        DoNotDisturbData doNotDisturbData = DoNotDisturbData.getInstance();
        Intrinsics.checkNotNullExpressionValue(doNotDisturbData, "getInstance()");
        doNotDisturbData.setBeggining_time_hour(i3);
        doNotDisturbData.setBeggining_time_minutes(i6);
        doNotDisturbData.setEnd_time_hour(i5);
        doNotDisturbData.setEnd_time_minutes(i4);
        if (sDNDInfo.getActive()) {
            if ((doNotDisturbData.getBeggining_time_hour() == 0 && doNotDisturbData.getBeggining_time_minutes() == 0 && doNotDisturbData.getEnd_time_hour() == 0 && doNotDisturbData.getEnd_time_minutes() == 0) || (doNotDisturbData.getBeggining_time_hour() == 0 && doNotDisturbData.getBeggining_time_minutes() == 0 && doNotDisturbData.getEnd_time_hour() == 23 && doNotDisturbData.getEnd_time_minutes() == 59)) {
                doNotDisturbData.setDnd_on_off(true);
                doNotDisturbData.setSchedule_on_off(false);
            } else {
                doNotDisturbData.setDnd_on_off(false);
                doNotDisturbData.setSchedule_on_off(true);
            }
        } else {
            doNotDisturbData.setSchedule_on_off(false);
            doNotDisturbData.setDnd_on_off(false);
        }
        UserDataManager.getInstance(context).saveScheuleDnd(doNotDisturbData.isSchedule_on_off());
        UserDataManager.getInstance(context).saveDoNotDisturbSettings(doNotDisturbData);
        successListener.onSuccess();
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x011e  */
    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IDNDPreferenceHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updatePreferenceWithLatestBandData(@org.jetbrains.annotations.NotNull android.content.Context r20, @org.jetbrains.annotations.NotNull com.coveiot.android.remotecommandframework.alexa.model.SDNDInfo r21, @org.jetbrains.annotations.NotNull com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener r22) {
        /*
            Method dump skipped, instructions count: 320
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.remotecommandframework.pref.handler.DNDPreferenceHandler.updatePreferenceWithLatestBandData(android.content.Context, com.coveiot.android.remotecommandframework.alexa.model.SDNDInfo, com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener):void");
    }
}
