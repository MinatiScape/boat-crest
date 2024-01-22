package com.coveiot.android.bleabstract.formatter;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.SleepDayData;
import com.coveiot.android.bleabstract.response.SleepHourData;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.SleepTimeLogBeanData;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleUtils;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.crrepa.ble.conn.bean.CRPSleepInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlin.Triple;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class CRPSleepFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3305a;
    public final String b;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<CRPSleepFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.CRPSleepFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, CRPSleepFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3306a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, CRPSleepFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public CRPSleepFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new CRPSleepFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3306a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CRPSleepFormatter(Context context) {
        this.f3305a = context;
        this.b = CRPSleepFormatter.class.getSimpleName();
    }

    public /* synthetic */ CRPSleepFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final Triple<Integer, Integer, Integer> a(ArrayList<Integer> arrayList) {
        Iterator<Integer> it = arrayList.iterator();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            Integer next = it.next();
            if (next != null && next.intValue() == 0) {
                i3++;
            }
            if (next != null && next.intValue() == 1) {
                i2++;
            }
            if (next != null && next.intValue() == 2) {
                i++;
            }
        }
        return new Triple<>(Integer.valueOf(i3), Integer.valueOf(i2), Integer.valueOf(i));
    }

    @NotNull
    public final ArrayList<SleepResponse> convertSleepData(int i, @NotNull List<? extends CRPSleepInfo> crpSleepInfos, int i2) {
        int i3;
        String str;
        String str2;
        char c;
        int i4;
        Intrinsics.checkNotNullParameter(crpSleepInfos, "crpSleepInfos");
        String macAddress = BleApiManager.getInstance(this.f3305a).getBleApi().getMacAddress();
        ArrayList<SleepResponse> arrayList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        if (i == 3) {
            cal.add(6, -1);
        } else if (i == 4) {
            cal.add(6, -2);
        }
        String str3 = "yyyy-MM-dd";
        String formatDate = AppUtils.formatDate(new Date(cal.getTimeInMillis()), "yyyy-MM-dd");
        Iterator<? extends CRPSleepInfo> it = crpSleepInfos.iterator();
        while (it.hasNext()) {
            CRPSleepInfo next = it.next();
            SleepResponse sleepResponse = new SleepResponse();
            SleepDayData sleepDayData = new SleepDayData();
            StringBuilder sb = new StringBuilder();
            sb.append(macAddress);
            sb.append(formatDate);
            ActivityType activityType = ActivityType.SLEEP;
            sb.append(activityType.name());
            sleepDayData.id = sb.toString();
            sleepDayData.mMacAddress = macAddress;
            sleepDayData.mDate = formatDate;
            sleepDayData.setmActivityType(activityType.name());
            Intrinsics.checkNotNullExpressionValue(cal, "cal");
            ArrayList arrayList2 = new ArrayList();
            ArrayList<Integer> emptyDaySleepCodedValuesList = JStyleUtils.getEmptyDaySleepCodedValuesList();
            int i5 = 0;
            for (CRPSleepInfo.DetailBean detailBean : next.getDetails()) {
                int i6 = i5 + 1;
                String str4 = this.b;
                String str5 = macAddress;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(i5);
                Iterator<? extends CRPSleepInfo> it2 = it;
                sb2.append(": startTime->");
                sb2.append(detailBean.getStartTime());
                sb2.append(", endTime->");
                sb2.append(detailBean.getEndTime());
                sb2.append(", totalTime->");
                sb2.append(detailBean.getTotalTime());
                sb2.append(", type->");
                sb2.append(detailBean.getType());
                LogHelper.d(str4, sb2.toString());
                if (i5 == next.getDetails().size() - 1) {
                    detailBean.setTotalTime(detailBean.getTotalTime() + 1);
                }
                int startTime = detailBean.getStartTime();
                try {
                    int totalTime = detailBean.getTotalTime();
                    int i7 = startTime;
                    for (int i8 = 0; i8 < totalTime; i8++) {
                        emptyDaySleepCodedValuesList.set(i7, Integer.valueOf(detailBean.getType()));
                        i7 = i7 == 1439 ? 0 : i7 + 1;
                    }
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
                i5 = i6;
                macAddress = str5;
                it = it2;
            }
            String str6 = macAddress;
            Iterator<? extends CRPSleepInfo> it3 = it;
            int i9 = 11;
            cal.set(11, 0);
            char c2 = '\f';
            cal.set(12, 0);
            cal.set(13, 0);
            cal.set(14, 0);
            ArrayList<Integer> arrayList3 = new ArrayList<>();
            int size = emptyDaySleepCodedValuesList.size();
            int i10 = 0;
            while (i10 < size) {
                arrayList3.add(emptyDaySleepCodedValuesList.get(i10));
                if (i10 <= 0) {
                    i3 = size;
                    str = str3;
                    str2 = formatDate;
                    c = c2;
                } else if ((i10 + 1) % 60 == 0) {
                    SleepHourData sleepHourData = new SleepHourData();
                    sleepHourData.setDate(AppUtils.formatDate(cal.getTime(), str3));
                    int i11 = cal.get(i9);
                    if (i11 == 23) {
                        i3 = size;
                        str = str3;
                        i4 = 0;
                    } else {
                        i4 = i11 + 1;
                        i3 = size;
                        str = str3;
                    }
                    String format = String.format("%02d:00:00", Arrays.copyOf(new Object[]{Integer.valueOf(i11)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    sleepHourData.setStartHour(format);
                    str2 = formatDate;
                    String format2 = String.format("%02d:00:00", Arrays.copyOf(new Object[]{Integer.valueOf(i4)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                    sleepHourData.setEndHour(format2);
                    sleepHourData.setActivityType(ActivityType.SLEEP.name());
                    sleepHourData.setMacAddress(BleApiManager.getInstance(this.f3305a).getBleApi().getMacAddress());
                    Object clone = arrayList3.clone();
                    Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Int>");
                    sleepHourData.setMinuteWiseData((ArrayList) clone);
                    sleepHourData.setAwakePerHour(a(arrayList3).getFirst().intValue());
                    sleepHourData.setLightSleepPerHour(a(arrayList3).getSecond().intValue());
                    sleepHourData.setDeepSleepPerHour(a(arrayList3).getThird().intValue());
                    sleepHourData.setTotalSleepPerHour(sleepHourData.getDeepSleepPerHour() + sleepHourData.getLightSleepPerHour());
                    arrayList2.add(sleepHourData);
                    i9 = 11;
                    if (cal.get(11) == 11) {
                        cal.add(6, -1);
                        cal.set(11, 11);
                    }
                    c = '\f';
                    cal.add(12, 60);
                    arrayList3.clear();
                } else {
                    i3 = size;
                    str = str3;
                    str2 = formatDate;
                    c = '\f';
                }
                i10++;
                c2 = c;
                size = i3;
                str3 = str;
                formatDate = str2;
            }
            String str7 = str3;
            String str8 = formatDate;
            Iterator it4 = arrayList2.iterator();
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            while (it4.hasNext()) {
                SleepHourData sleepHourData2 = (SleepHourData) it4.next();
                i14 += sleepHourData2.getAwakePerHour();
                i13 += sleepHourData2.getLightSleepPerHour();
                i12 += sleepHourData2.getDeepSleepPerHour();
            }
            sleepDayData.mAwakeTime = i14;
            sleepDayData.mLightSleep = i13;
            sleepDayData.mDeepSleep = i12;
            sleepDayData.mTotalSleep = next.getTotalTime();
            SleepTimeLogBeanData sleepTimeLogBeanData = new SleepTimeLogBeanData();
            sleepTimeLogBeanData.setLogBeans(arrayList2);
            sleepDayData.timeLogBean = sleepTimeLogBeanData;
            sleepResponse.setSleepDayData(sleepDayData);
            sleepResponse.setComplete(i2 == 1);
            if (sleepResponse.isComplete()) {
                LogHelper.d(this.b, "Sleep Complete");
            }
            arrayList.add(sleepResponse);
            formatDate = str8;
            macAddress = str6;
            str3 = str7;
            it = it3;
        }
        return arrayList;
    }

    @NotNull
    public final Context getContext() {
        return this.f3305a;
    }

    public final String getTAG() {
        return this.b;
    }
}
