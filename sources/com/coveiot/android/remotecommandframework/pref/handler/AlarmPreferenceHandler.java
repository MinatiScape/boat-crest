package com.coveiot.android.remotecommandframework.pref.handler;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.handler.IAlarmPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SAlarmInfo;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.VibrationAlarmData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.f;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class AlarmPreferenceHandler implements IAlarmPreferenceHandler {

    /* renamed from: a  reason: collision with root package name */
    public final String f5648a = AlarmPreferenceHandler.class.getSimpleName();

    public final Integer a(List<VibrationAlarmData> list) {
        if (list == null || !(!list.isEmpty())) {
            return null;
        }
        int i = 0;
        for (VibrationAlarmData vibrationAlarmData : CollectionsKt___CollectionsKt.sortedWith(list, new Comparator() { // from class: com.coveiot.android.remotecommandframework.pref.handler.AlarmPreferenceHandler$getMissingId$lambda$1$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return f.compareValues(((VibrationAlarmData) t).getAlarmId(), ((VibrationAlarmData) t2).getAlarmId());
            }
        })) {
            int i2 = i + 1;
            String alarmId = vibrationAlarmData.getAlarmId();
            if (!(alarmId == null || alarmId.length() == 0)) {
                String alarmId2 = vibrationAlarmData.getAlarmId();
                Intrinsics.checkNotNullExpressionValue(alarmId2, "pref.alarmId");
                if (i == Integer.parseInt(alarmId2)) {
                    i = i2;
                }
            }
            return Integer.valueOf(i);
        }
        return null;
    }

    public final boolean b(VibrationAlarmData vibrationAlarmData) {
        if (!vibrationAlarmData.isSunday() && !vibrationAlarmData.isMonday() && !vibrationAlarmData.isTuesday() && !vibrationAlarmData.isWednesday() && !vibrationAlarmData.isThrusday() && !vibrationAlarmData.isFriday() && !vibrationAlarmData.isSaturday()) {
            Calendar calendar = Calendar.getInstance();
            int i = calendar.get(11);
            int i2 = calendar.get(12);
            if (i > vibrationAlarmData.getEvent_time_hour()) {
                return true;
            }
            if (i == vibrationAlarmData.getEvent_time_hour() && i2 > vibrationAlarmData.getEvent_time_minutes()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x006b  */
    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IAlarmPreferenceHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int getAlarmId(@org.jetbrains.annotations.NotNull android.content.Context r5, @org.jetbrains.annotations.NotNull com.coveiot.android.remotecommandframework.alexa.model.SAlarmInfo r6, int r7) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.remotecommandframework.pref.handler.AlarmPreferenceHandler.getAlarmId(android.content.Context, com.coveiot.android.remotecommandframework.alexa.model.SAlarmInfo, int):int");
    }

    public final String getTAG() {
        return this.f5648a;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IAlarmPreferenceHandler
    public boolean isAlreadySet(@NotNull Context context, @NotNull SAlarmInfo sAlarmInfo) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sAlarmInfo, "sAlarmInfo");
        String time = sAlarmInfo.getTime();
        if (time != null) {
            i2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null).get(0));
            i = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) time, new String[]{":"}, false, 0, 6, (Object) null).get(1));
        } else {
            i = 0;
            i2 = 0;
        }
        if (!sAlarmInfo.getRepeat()) {
            List<VibrationAlarmData> vibrationAlarmData = UserDataManager.getInstance(context).getVibrationAlarmData();
            if (!(vibrationAlarmData == null || vibrationAlarmData.isEmpty())) {
                for (VibrationAlarmData vibrationAlarmData2 : vibrationAlarmData) {
                    if (vibrationAlarmData2.getSwitch_on_off() == sAlarmInfo.getActive() && vibrationAlarmData2.getEvent_time_hour() == i2 && vibrationAlarmData2.getEvent_time_minutes() == i) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IAlarmPreferenceHandler
    public boolean isAtMaximum(@NotNull Context context, @NotNull SAlarmInfo sAlarmInfo, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sAlarmInfo, "sAlarmInfo");
        if (sAlarmInfo.getActive()) {
            List<VibrationAlarmData> vibrationAlarmData = UserDataManager.getInstance(context).getVibrationAlarmData();
            if (!(vibrationAlarmData == null || vibrationAlarmData.isEmpty())) {
                int i2 = 0;
                for (VibrationAlarmData vibrationAlarmData2 : vibrationAlarmData) {
                    if (vibrationAlarmData2.getSwitch_on_off() && !b(vibrationAlarmData2)) {
                        i2++;
                    }
                }
                if (i2 >= i) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IAlarmPreferenceHandler
    public boolean isValidInput(@NotNull Context context, @NotNull SAlarmInfo sAlarmInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sAlarmInfo, "sAlarmInfo");
        return true;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IAlarmPreferenceHandler
    public void update(@NotNull Context context, @NotNull SAlarmInfo sAlarmInfo, int i, @NotNull SuccessListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sAlarmInfo, "sAlarmInfo");
        Intrinsics.checkNotNullParameter(listener, "listener");
        List<VibrationAlarmData> vibrationAlarmData = UserDataManager.getInstance(context).getVibrationAlarmData();
        if (vibrationAlarmData == null) {
            vibrationAlarmData = new ArrayList<>();
        }
        VibrationAlarmData vibrationAlarmData2 = PreferenceParser.INSTANCE.getVibrationAlarmData(sAlarmInfo, Integer.valueOf(i));
        if (vibrationAlarmData.isEmpty()) {
            vibrationAlarmData.add(vibrationAlarmData2);
        } else if (vibrationAlarmData.size() >= i) {
            int i2 = 0;
            int size = vibrationAlarmData.size();
            while (true) {
                if (i2 >= size) {
                    break;
                } else if (Intrinsics.areEqual(vibrationAlarmData.get(i2).getAlarmId(), vibrationAlarmData2.getAlarmId())) {
                    vibrationAlarmData.set(i2, vibrationAlarmData2);
                    break;
                } else {
                    i2++;
                }
            }
        } else {
            vibrationAlarmData.add(vibrationAlarmData2);
        }
        UserDataManager.getInstance(context).saveVibrationAlarmSettings(vibrationAlarmData);
        listener.onSuccess();
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.handler.IAlarmPreferenceHandler
    public void updatePreferenceWithLatestBandData(@NotNull Context context, @NotNull List<SAlarmInfo> sAlarmInfoList, @NotNull SuccessListener listener) {
        boolean z;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sAlarmInfoList, "sAlarmInfoList");
        Intrinsics.checkNotNullParameter(listener, "listener");
        ArrayList arrayList = new ArrayList();
        List<VibrationAlarmData> vibrationAlarmData = UserDataManager.getInstance(context).getVibrationAlarmData();
        if (vibrationAlarmData == null) {
            vibrationAlarmData = new ArrayList<>();
        }
        if (!sAlarmInfoList.isEmpty()) {
            for (SAlarmInfo sAlarmInfo : sAlarmInfoList) {
                VibrationAlarmData vibrationAlarmData2 = PreferenceParser.INSTANCE.getVibrationAlarmData(sAlarmInfo, null);
                Iterator<VibrationAlarmData> it = vibrationAlarmData.iterator();
                while (true) {
                    z = true;
                    boolean z2 = false;
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    VibrationAlarmData next = it.next();
                    if (next.getAlarmId() != null && Intrinsics.areEqual(next.getAlarmId(), vibrationAlarmData2.getAlarmId())) {
                        String event_name = vibrationAlarmData2.getEvent_name();
                        if (!((event_name == null || event_name.length() == 0) ? true : true)) {
                            next.setEvent_name(vibrationAlarmData2.getEvent_name());
                        }
                        next.setEvent_time_hour(vibrationAlarmData2.getEvent_time_hour());
                        next.setEvent_time_minutes(vibrationAlarmData2.getEvent_time_minutes());
                        next.setSwitch_on_off(vibrationAlarmData2.getSwitch_on_off());
                        next.setWeeks(vibrationAlarmData2.getWeeks());
                        next.setMonday(vibrationAlarmData2.isMonday());
                        next.setTuesday(vibrationAlarmData2.isTuesday());
                        next.setWednesday(vibrationAlarmData2.isWednesday());
                        next.setThrusday(vibrationAlarmData2.isThrusday());
                        next.setFriday(vibrationAlarmData2.isFriday());
                        next.setSaturday(vibrationAlarmData2.isSaturday());
                        next.setSunday(vibrationAlarmData2.isSunday());
                        arrayList.add(next);
                    }
                }
                if (!z) {
                    arrayList.add(vibrationAlarmData2);
                }
            }
        }
        UserDataManager.getInstance(context).saveVibrationAlarmSettings(arrayList);
        listener.onSuccess();
    }
}
