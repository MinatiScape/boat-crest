package com.coveiot.android.bleabstract.formatter;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.SleepDayData;
import com.coveiot.android.bleabstract.response.SleepHourData;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.SleepTimeLogBeanData;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleUtils;
import com.coveiot.android.bleabstract.utils.smaUtils.SmaUtils;
import com.coveiot.khsmadb.sleep.KhBleSleep;
import com.coveiot.khsmadb.sleep.KhSmaSleepDayData;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.szabh.smable3.entity.BleSleep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import kotlin.Triple;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SMASleepFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3327a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<SMASleepFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.SMASleepFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, SMASleepFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3328a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, SMASleepFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public SMASleepFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SMASleepFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3328a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SMASleepFormatter(Context context) {
        this.f3327a = context;
    }

    public /* synthetic */ SMASleepFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final Triple<Integer, Integer, Integer> a(ArrayList<Integer> arrayList) {
        Iterator<Integer> it = arrayList.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            Integer next = it.next();
            if (next != null) {
                next.intValue();
            }
            if (next != null && next.intValue() == 2) {
                i2++;
            }
            if (next != null && next.intValue() == 1) {
                i++;
            }
        }
        return new Triple<>(0, Integer.valueOf(i2), Integer.valueOf(i));
    }

    @NotNull
    public final ArrayList<SleepResponse> convertSleepData(@NotNull List<KhSmaSleepDayData> khSmaSleepDataList, int i) {
        String str;
        Iterator<KhSmaSleepDayData> it;
        ArrayList<Integer> arrayList;
        Intrinsics.checkNotNullParameter(khSmaSleepDataList, "khSmaSleepDataList");
        String macAddress = BleApiManager.getInstance(this.f3327a).getBleApi().getMacAddress();
        ArrayList<SleepResponse> arrayList2 = new ArrayList<>();
        Iterator<KhSmaSleepDayData> it2 = khSmaSleepDataList.iterator();
        while (it2.hasNext()) {
            KhSmaSleepDayData next = it2.next();
            SleepResponse sleepResponse = new SleepResponse();
            SleepDayData sleepDayData = new SleepDayData();
            StringBuilder sb = new StringBuilder();
            sb.append(macAddress);
            sb.append(next.getDate());
            ActivityType activityType = ActivityType.SLEEP;
            sb.append(activityType.name());
            sleepDayData.id = sb.toString();
            sleepDayData.mMacAddress = macAddress;
            sleepDayData.mDate = next.getDate();
            sleepDayData.setmActivityType(activityType.name());
            ArrayList arrayList3 = new ArrayList();
            ArrayList<Integer> a2 = a(next);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(AppUtils.parseDate(next.getDate(), "yyyy-MM-dd"));
            calendar.add(6, -1);
            calendar.set(11, 12);
            int i2 = 0;
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            ArrayList<Integer> arrayList4 = new ArrayList<>();
            int size = a2.size();
            while (i2 < size) {
                arrayList4.add(a2.get(i2));
                if (i2 > 0) {
                    str = macAddress;
                    if ((i2 + 1) % 60 == 0) {
                        SleepHourData sleepHourData = new SleepHourData();
                        sleepHourData.setDate(AppUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"));
                        String format = String.format("%02d:00:00", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(11))}, 1));
                        it = it2;
                        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                        sleepHourData.setStartHour(format);
                        arrayList = a2;
                        String format2 = String.format("%02d:00:00", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(11) + 1)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                        sleepHourData.setEndHour(format2);
                        sleepHourData.setActivityType(ActivityType.SLEEP.name());
                        sleepHourData.setMacAddress(BleApiManager.getInstance(this.f3327a).getBleApi().getMacAddress());
                        Object clone = arrayList4.clone();
                        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Int>");
                        sleepHourData.setMinuteWiseData((ArrayList) clone);
                        sleepHourData.setAwakePerHour(a(arrayList4).getFirst().intValue());
                        sleepHourData.setLightSleepPerHour(a(arrayList4).getSecond().intValue());
                        sleepHourData.setDeepSleepPerHour(a(arrayList4).getThird().intValue());
                        sleepHourData.setTotalSleepPerHour(sleepHourData.getDeepSleepPerHour() + sleepHourData.getLightSleepPerHour() + sleepHourData.getAwakePerHour());
                        arrayList3.add(sleepHourData);
                        calendar.add(12, 60);
                        arrayList4.clear();
                        i2++;
                        a2 = arrayList;
                        macAddress = str;
                        it2 = it;
                    }
                } else {
                    str = macAddress;
                }
                it = it2;
                arrayList = a2;
                i2++;
                a2 = arrayList;
                macAddress = str;
                it2 = it;
            }
            String str2 = macAddress;
            Iterator<KhSmaSleepDayData> it3 = it2;
            Iterator it4 = arrayList3.iterator();
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (it4.hasNext()) {
                SleepHourData sleepHourData2 = (SleepHourData) it4.next();
                i5 += sleepHourData2.getAwakePerHour();
                i4 += sleepHourData2.getLightSleepPerHour();
                i3 += sleepHourData2.getDeepSleepPerHour();
            }
            sleepDayData.mAwakeTime = i5;
            sleepDayData.mLightSleep = i4;
            sleepDayData.mDeepSleep = i3;
            SleepTimeLogBeanData sleepTimeLogBeanData = new SleepTimeLogBeanData();
            sleepTimeLogBeanData.setLogBeans(arrayList3);
            sleepDayData.timeLogBean = sleepTimeLogBeanData;
            sleepResponse.setSleepDayData(sleepDayData);
            sleepResponse.setComplete(next.equals(CollectionsKt___CollectionsKt.last((List<? extends Object>) khSmaSleepDataList)) && i == 0);
            if (sleepResponse.isComplete()) {
                LogHelper.d("SmaBleService", "Sleep Complete");
            }
            arrayList2.add(sleepResponse);
            macAddress = str2;
            it2 = it3;
        }
        return arrayList2;
    }

    @NotNull
    public final List<BleSleep> getBleSleepFromKhBleSleep(@Nullable List<KhBleSleep> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (KhBleSleep khBleSleep : list) {
                arrayList.add(new BleSleep(khBleSleep.getMTime(), khBleSleep.getMMode(), khBleSleep.getMSoft(), khBleSleep.getMStrong()));
            }
        }
        return arrayList;
    }

    @NotNull
    public final Context getContext() {
        return this.f3327a;
    }

    @NotNull
    public final List<KhBleSleep> getKhBleSleep(@NotNull String macAddress, @Nullable List<BleSleep> list) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (BleSleep bleSleep : list) {
                arrayList.add(new KhBleSleep(bleSleep.getMTime(), bleSleep.getMMode(), bleSleep.getMSoft(), bleSleep.getMStrong(), macAddress, null, 32, null));
            }
        }
        return arrayList;
    }

    @NotNull
    public final Calendar getTodaySleepStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, -1);
        calendar.set(11, 12);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    public final ArrayList<Integer> a(KhSmaSleepDayData khSmaSleepDayData) {
        List<KhBleSleep> analysedSleepData;
        int mMode;
        ArrayList<Integer> emptyDaySleepCodedValuesList = JStyleUtils.getEmptyDaySleepCodedValuesList();
        List<KhBleSleep> analysedSleepData2 = khSmaSleepDayData.getAnalysedSleepData();
        Intrinsics.checkNotNull(analysedSleepData2);
        int size = analysedSleepData2.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            SmaUtils smaUtils = SmaUtils.INSTANCE;
            List<KhBleSleep> analysedSleepData3 = khSmaSleepDayData.getAnalysedSleepData();
            Intrinsics.checkNotNull(analysedSleepData3);
            int a2 = a(smaUtils.convertSDKTimeToCalender(analysedSleepData3.get(i).getMTime()));
            int size2 = emptyDaySleepCodedValuesList.size() - 1;
            if (a2 <= size2) {
                int i2 = a2;
                while (true) {
                    if (i2 >= a2) {
                        Intrinsics.checkNotNull(khSmaSleepDayData.getAnalysedSleepData());
                        if (i < analysedSleepData.size() - 2) {
                            SmaUtils smaUtils2 = SmaUtils.INSTANCE;
                            List<KhBleSleep> analysedSleepData4 = khSmaSleepDayData.getAnalysedSleepData();
                            Intrinsics.checkNotNull(analysedSleepData4);
                            if (i2 == a(smaUtils2.convertSDKTimeToCalender(analysedSleepData4.get(i + 1).getMTime()))) {
                                break;
                            }
                        }
                        List<KhBleSleep> analysedSleepData5 = khSmaSleepDayData.getAnalysedSleepData();
                        Intrinsics.checkNotNull(analysedSleepData5);
                        if (i == analysedSleepData5.size() - 1) {
                            mMode = 34;
                        } else {
                            List<KhBleSleep> analysedSleepData6 = khSmaSleepDayData.getAnalysedSleepData();
                            Intrinsics.checkNotNull(analysedSleepData6);
                            mMode = analysedSleepData6.get(i).getMMode();
                        }
                        if (mMode == 34) {
                            if (z) {
                                mMode = -1;
                            } else {
                                List<KhBleSleep> analysedSleepData7 = khSmaSleepDayData.getAnalysedSleepData();
                                Intrinsics.checkNotNull(analysedSleepData7);
                                mMode = ((KhBleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) analysedSleepData7)).getMMode();
                                z = true;
                            }
                        }
                        emptyDaySleepCodedValuesList.set(i2, Integer.valueOf(mMode));
                    }
                    if (i2 != size2) {
                        i2++;
                    }
                }
            }
        }
        return emptyDaySleepCodedValuesList;
    }

    public final int a(Calendar calendar) {
        int i;
        int i2;
        int i3 = calendar.get(11);
        if (i3 > 12) {
            i = i3 - 12;
        } else if (i3 == 12) {
            i2 = 0;
            return i2 + calendar.get(12);
        } else {
            i = i3 + 12;
        }
        i2 = i * 60;
        return i2 + calendar.get(12);
    }
}
