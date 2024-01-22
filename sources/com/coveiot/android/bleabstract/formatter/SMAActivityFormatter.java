package com.coveiot.android.bleabstract.formatter;

import android.content.Context;
import android.text.format.DateUtils;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.models.ActivityPauseSegment;
import com.coveiot.android.bleabstract.response.StepsHourData;
import com.coveiot.android.bleabstract.utils.smaUtils.SmaUtils;
import com.coveiot.khsmadb.activity.KhActivityRepository;
import com.coveiot.khsmadb.activity.KhBleActivity;
import com.coveiot.khsmadb.heartrate.KhBleHeartRate;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.szabh.smable3.entity.BleActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.e;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SMAActivityFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3317a;
    public final String b;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<SMAActivityFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.SMAActivityFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, SMAActivityFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3318a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, SMAActivityFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public SMAActivityFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SMAActivityFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3318a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SMAActivityFormatter(Context context) {
        this.f3317a = context;
        this.b = SMAActivityFormatter.class.getSimpleName();
    }

    public /* synthetic */ SMAActivityFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final boolean a(KhBleHeartRate khBleHeartRate, ArrayList<ActivityPauseSegment> arrayList) {
        if (AppUtils.isEmpty(arrayList)) {
            return false;
        }
        Iterator<ActivityPauseSegment> it = arrayList.iterator();
        while (it.hasNext()) {
            ActivityPauseSegment next = it.next();
            if (khBleHeartRate.getMTime() >= next.getStartTime() && khBleHeartRate.getMTime() < next.getEndTime()) {
                return true;
            }
        }
        return false;
    }

    public final LinkedHashMap<String, List<KhBleActivity>> b(List<KhBleActivity> list) {
        LinkedHashMap<String, List<KhBleActivity>> linkedHashMap = new LinkedHashMap<>();
        for (KhBleActivity khBleActivity : list) {
            try {
                String obj = StringsKt__StringsKt.trim((String) CollectionsKt___CollectionsKt.first((List<? extends Object>) StringsKt__StringsKt.split$default((CharSequence) StringsKt__StringsKt.trim((String) CollectionsKt___CollectionsKt.last((List<? extends Object>) StringsKt__StringsKt.split$default((CharSequence) StringsKt__StringsKt.trim(khBleActivity.getTimeStamp()).toString(), new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null))).toString(), new String[]{":"}, false, 0, 6, (Object) null))).toString();
                if (linkedHashMap.get(obj) == null) {
                    linkedHashMap.put(obj, CollectionsKt__CollectionsKt.mutableListOf(khBleActivity));
                } else {
                    List<KhBleActivity> list2 = linkedHashMap.get(obj);
                    if (list2 != null) {
                        list2.add(khBleActivity);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return linkedHashMap;
    }

    @Nullable
    public final List<BleActivity> checkAndFilterTodayWrongStepsData(@NotNull String macAddress, @Nullable List<BleActivity> list) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        ArrayList arrayList = new ArrayList();
        if (!(list == null || list.isEmpty())) {
            String formatDate = AppUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
            Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(cal.time, \"yyyy-MM-dd\")");
            Integer maxStepRecordByDate = KhActivityRepository.Companion.getInstance(this.f3317a).getMaxStepRecordByDate(macAddress, formatDate);
            if (maxStepRecordByDate != null && maxStepRecordByDate.intValue() > 0) {
                for (BleActivity bleActivity : list) {
                    if (DateUtils.isToday(SmaUtils.INSTANCE.convertSDKTimeToCalender(bleActivity.getMTime()).getTimeInMillis())) {
                        if (bleActivity.getMStep() != 0 && maxStepRecordByDate.intValue() > bleActivity.getMStep()) {
                            String str = this.b;
                            LogHelper.i(str, "Today's step data ignored app->" + maxStepRecordByDate + " >  watch->" + bleActivity.getMStep());
                        } else {
                            String str2 = this.b;
                            LogHelper.i(str2, "Today's step data added app->" + maxStepRecordByDate + " >  watch->" + bleActivity.getMStep());
                            arrayList.add(bleActivity);
                        }
                    } else {
                        arrayList.add(bleActivity);
                    }
                }
            } else {
                arrayList.addAll(list);
            }
        }
        return arrayList;
    }

    @Nullable
    public final Object checkAndUpdateActivityDataFromServerToDb(@NotNull List<? extends StepsHourData> list, @NotNull Date date, @NotNull Continuation<? super Unit> continuation) {
        Integer num;
        double d;
        double d2;
        int i;
        int i2;
        String str;
        char c;
        SmaUtils smaUtils;
        KhBleActivity khBleActivity;
        Iterator<? extends StepsHourData> it = list.iterator();
        int i3 = 0;
        int i4 = 0;
        String str2 = null;
        double d3 = 0.0d;
        double d4 = 0.0d;
        Integer num2 = null;
        while (it.hasNext()) {
            StepsHourData next = it.next();
            if (next.getDate() != null) {
                if (str2 == null || !Intrinsics.areEqual(str2, next.getDate())) {
                    SmaUtils smaUtils2 = SmaUtils.INSTANCE;
                    Date parseDate = AppUtils.parseDate(next.getDate(), "yyyy-MM-dd");
                    Intrinsics.checkNotNullExpressionValue(parseDate, "parseDate(\n             …                        )");
                    long j = 1000;
                    int timeInMillis = (int) ((smaUtils2.getDayStartTimeForGivenDate(parseDate).getTimeInMillis() - smaUtils2.getCalenderFor2000().getTimeInMillis()) / j);
                    Date parseDate2 = AppUtils.parseDate(next.getDate(), "yyyy-MM-dd");
                    Intrinsics.checkNotNullExpressionValue(parseDate2, "parseDate(\n             …                        )");
                    int timeInMillis2 = (int) ((smaUtils2.getDayEndTimeForGivenDate(parseDate2).getTimeInMillis() - smaUtils2.getCalenderFor2000().getTimeInMillis()) / j);
                    KhActivityRepository.Companion companion = KhActivityRepository.Companion;
                    String str3 = next.getmMacAddress();
                    Intrinsics.checkNotNullExpressionValue(str3, "hourlyStepsData.getmMacAddress()");
                    List<KhBleActivity> orderedActivityListBetweenTime = companion.getInstance(this.f3317a).getOrderedActivityListBetweenTime(str3, timeInMillis, timeInMillis2);
                    if (((orderedActivityListBetweenTime == null || orderedActivityListBetweenTime.isEmpty()) ? 1 : i3) == 0) {
                        num = Boxing.boxInt(orderedActivityListBetweenTime.get(i3).getMTime());
                        LogHelper.d(this.b, "Watch data available till -> " + orderedActivityListBetweenTime.get(i3).getTimeStamp());
                        String str4 = list.get(i3).getmMacAddress();
                        Intrinsics.checkNotNullExpressionValue(str4, "hourlyStepsDataList[0].getmMacAddress()");
                        companion.getInstance(this.f3317a).updateActivityDataUnProcessedAfter(str4, num.intValue());
                        i4 = i3;
                    } else {
                        i4 = i3;
                        num = null;
                    }
                    d = 0.0d;
                    d2 = 0.0d;
                } else {
                    num = num2;
                    d2 = d4;
                    d = d3;
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(next.getTimeStamp());
                LogHelper.d(this.b, "startTime-> " + next.getStartHour());
                LogHelper.d(this.b, "inCalendar-> " + calendar.getTime());
                String str5 = "hourlyStepsData.getmMacAddress()";
                if (!Intrinsics.areEqual(next.getDate(), SmaUtils.INSTANCE.convertTimeToDate(System.currentTimeMillis())) || calendar.get(11) <= Calendar.getInstance().get(11)) {
                    d += next.mCaloriesPerHour;
                    d2 += next.mDistancePerHour;
                    ArrayList<Integer> arrayList = next.mMinuteWiseData;
                    if (!(arrayList == null || arrayList.isEmpty())) {
                        Iterator<Integer> it2 = next.mMinuteWiseData.iterator();
                        int i5 = 0;
                        int i6 = 0;
                        while (it2.hasNext()) {
                            int i7 = i5 + 1;
                            Integer value = it2.next();
                            Calendar calendar2 = Calendar.getInstance();
                            Iterator<? extends StepsHourData> it3 = it;
                            Iterator<Integer> it4 = it2;
                            calendar2.setTimeInMillis(next.getTimeStamp());
                            calendar2.set(12, i6);
                            if (calendar2.getTimeInMillis() < System.currentTimeMillis()) {
                                i = i6;
                                i2 = i7;
                                int timeInMillis3 = (int) ((calendar2.getTimeInMillis() - SmaUtils.INSTANCE.getCalenderFor2000().getTimeInMillis()) / 1000);
                                if (num != null && timeInMillis3 >= num.intValue()) {
                                    LogHelper.d(this.b, "Not considered Time 0 -> " + calendar2.get(11) + ", " + calendar2.get(12));
                                    str = str5;
                                } else {
                                    LogHelper.d(this.b, "sdkTime-> " + timeInMillis3 + ", " + smaUtils.convertSDKTimeToCalender(timeInMillis3).getTime());
                                    Intrinsics.checkNotNullExpressionValue(value, "value");
                                    i4 += value.intValue();
                                    if (i5 == next.mMinuteWiseData.size() - 1) {
                                        double d5 = 10000;
                                        String str6 = next.getmMacAddress();
                                        str = str5;
                                        Intrinsics.checkNotNullExpressionValue(str6, str);
                                        khBleActivity = new KhBleActivity(timeInMillis3, 0, 0, i4, (int) (d * d5), (int) (d5 * d2), str6);
                                    } else {
                                        str = str5;
                                        String str7 = next.getmMacAddress();
                                        Intrinsics.checkNotNullExpressionValue(str7, str);
                                        khBleActivity = new KhBleActivity(timeInMillis3, 0, 0, i4, 0, 0, str7);
                                    }
                                    KhActivityRepository.Companion.getInstance(this.f3317a).insertActivityData(e.listOf(khBleActivity));
                                }
                                c = 11;
                            } else {
                                i = i6;
                                i2 = i7;
                                str = str5;
                                String str8 = this.b;
                                StringBuilder sb = new StringBuilder();
                                sb.append("Not considered Time-> ");
                                c = 11;
                                sb.append(calendar2.get(11));
                                sb.append(", ");
                                sb.append(calendar2.get(12));
                                LogHelper.d(str8, sb.toString());
                            }
                            i6 = i + (60 / next.mMinuteWiseData.size());
                            str5 = str;
                            it = it3;
                            it2 = it4;
                            i5 = i2;
                        }
                    }
                }
                Iterator<? extends StepsHourData> it5 = it;
                d3 = d;
                d4 = d2;
                num2 = num;
                i3 = 0;
                str2 = next.getDate();
                it = it5;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0370  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.ArrayList<com.coveiot.android.bleabstract.response.StepsResponse> convertStepData(@org.jetbrains.annotations.NotNull java.util.List<com.coveiot.khsmadb.activity.KhBleActivity> r36) {
        /*
            Method dump skipped, instructions count: 1495
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.formatter.SMAActivityFormatter.convertStepData(java.util.List):java.util.ArrayList");
    }

    @NotNull
    public final Context getContext() {
        return this.f3317a;
    }

    @NotNull
    public final List<KhBleActivity> getKhBleActivity(@NotNull String macAddress, @Nullable List<BleActivity> list) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (BleActivity bleActivity : list) {
                arrayList.add(new KhBleActivity(bleActivity.getMTime(), bleActivity.getMMode(), bleActivity.getMState(), bleActivity.getMStep(), bleActivity.getMCalorie(), bleActivity.getMDistance(), macAddress));
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x04eb  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x043f  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x045e  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse> getSportModeHistoryData(@org.jetbrains.annotations.NotNull java.lang.String r21, @org.jetbrains.annotations.NotNull java.util.List<com.coveiot.khsmadb.activity.KhBleActivity> r22) {
        /*
            Method dump skipped, instructions count: 1571
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.formatter.SMAActivityFormatter.getSportModeHistoryData(java.lang.String, java.util.List):java.util.List");
    }

    public final String getTAG() {
        return this.b;
    }

    public final LinkedHashMap<String, List<KhBleActivity>> a(List<KhBleActivity> list) {
        LinkedHashMap<String, List<KhBleActivity>> linkedHashMap = new LinkedHashMap<>();
        for (KhBleActivity khBleActivity : list) {
            String date = khBleActivity.getDate();
            Intrinsics.checkNotNull(date);
            if (linkedHashMap.get(date) == null) {
                linkedHashMap.put(khBleActivity.getDate(), CollectionsKt__CollectionsKt.mutableListOf(khBleActivity));
            } else {
                List<KhBleActivity> list2 = linkedHashMap.get(khBleActivity.getDate());
                if (list2 != null) {
                    list2.add(khBleActivity);
                }
            }
        }
        return linkedHashMap;
    }
}
