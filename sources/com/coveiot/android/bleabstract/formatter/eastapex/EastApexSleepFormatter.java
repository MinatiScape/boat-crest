package com.coveiot.android.bleabstract.formatter.eastapex;

import android.content.Context;
import com.apex.bluetooth.model.EABleSleepData;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.SleepDayData;
import com.coveiot.android.bleabstract.response.SleepHourData;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.SleepTimeLogBeanData;
import com.coveiot.kheastapexdb.sleep.EntityEASleepData;
import com.coveiot.kheastapexdb.sleep.KHEASleepDayData;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
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
public final class EastApexSleepFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3344a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<EastApexSleepFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.eastapex.EastApexSleepFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, EastApexSleepFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3345a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, EastApexSleepFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public EastApexSleepFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new EastApexSleepFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3345a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public EastApexSleepFormatter(Context context) {
        this.f3344a = context;
    }

    public /* synthetic */ EastApexSleepFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
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

    @Nullable
    public final ArrayList<EntityEASleepData> convertEASleepDataToEntity(@Nullable List<? extends EABleSleepData> list) {
        EABleSleepData.SleepMode sleepMode;
        if (list != null) {
            ArrayList<EntityEASleepData> arrayList = new ArrayList<>();
            Iterator<? extends EABleSleepData> it = list.iterator();
            while (it.hasNext()) {
                EABleSleepData next = it.next();
                boolean z = true;
                if (!((next == null || (sleepMode = next.e_sleep_node) == null || sleepMode.value != 1) ? false : false)) {
                    Long valueOf = next != null ? Long.valueOf(next.time_stamp) : null;
                    Intrinsics.checkNotNull(valueOf);
                    EntityEASleepData entityEASleepData = new EntityEASleepData(valueOf.longValue(), next.e_sleep_node.value, BleApiManager.getInstance(this.f3344a).getBleApi().getMacAddress());
                    entityEASleepData.setTimeStamp(next.time_stamp * 1000);
                    arrayList.add(entityEASleepData);
                }
            }
            return arrayList;
        }
        return null;
    }

    @NotNull
    public final ArrayList<SleepResponse> convertSleepData(@NotNull List<KHEASleepDayData> kheaSleepDayDataList) {
        Iterator<KHEASleepDayData> it;
        ArrayList<Integer> arrayList;
        char c;
        String str;
        Intrinsics.checkNotNullParameter(kheaSleepDayDataList, "kheaSleepDayDataList");
        String macAddress = BleApiManager.getInstance(this.f3344a).getBleApi().getMacAddress();
        ArrayList<SleepResponse> arrayList2 = new ArrayList<>();
        Iterator<KHEASleepDayData> it2 = kheaSleepDayDataList.iterator();
        while (it2.hasNext()) {
            KHEASleepDayData next = it2.next();
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
            ArrayList<Integer> emptyDaySleepCodedValuesList = BleApiUtils.getEmptyDaySleepCodedValuesList();
            List<EntityEASleepData> entityEASleepData = next.getEntityEASleepData();
            Intrinsics.checkNotNull(entityEASleepData);
            int size = entityEASleepData.size();
            int i = 0;
            while (i < size) {
                List<EntityEASleepData> entityEASleepData2 = next.getEntityEASleepData();
                Intrinsics.checkNotNull(entityEASleepData2);
                if (entityEASleepData2.get(i).getSleepMode() != 6) {
                    BleApiUtils bleApiUtils = BleApiUtils.INSTANCE;
                    List<EntityEASleepData> entityEASleepData3 = next.getEntityEASleepData();
                    Intrinsics.checkNotNull(entityEASleepData3);
                    int a2 = a(bleApiUtils.getCalendarFromTimeStamp(entityEASleepData3.get(i).getTimeStamp()));
                    int size2 = emptyDaySleepCodedValuesList.size();
                    int i2 = a2;
                    while (i2 < size2) {
                        if (i2 >= a2) {
                            BleApiUtils bleApiUtils2 = BleApiUtils.INSTANCE;
                            str = macAddress;
                            List<EntityEASleepData> entityEASleepData4 = next.getEntityEASleepData();
                            Intrinsics.checkNotNull(entityEASleepData4);
                            if (i2 == a(bleApiUtils2.getCalendarFromTimeStamp(entityEASleepData4.get(i + 1).getTimeStamp()))) {
                                break;
                            }
                            List<EntityEASleepData> entityEASleepData5 = next.getEntityEASleepData();
                            Intrinsics.checkNotNull(entityEASleepData5);
                            emptyDaySleepCodedValuesList.set(i2, Integer.valueOf(entityEASleepData5.get(i).getSleepMode()));
                        } else {
                            str = macAddress;
                        }
                        i2++;
                        macAddress = str;
                    }
                }
                str = macAddress;
                i++;
                macAddress = str;
            }
            String str2 = macAddress;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(AppUtils.parseDate(next.getDate(), "yyyy-MM-dd"));
            calendar.add(6, -1);
            int i3 = 11;
            char c2 = '\f';
            calendar.set(11, 12);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            ArrayList<Integer> arrayList4 = new ArrayList<>();
            int size3 = emptyDaySleepCodedValuesList.size();
            int i4 = 0;
            while (i4 < size3) {
                arrayList4.add(emptyDaySleepCodedValuesList.get(i4));
                if (i4 <= 0) {
                    it = it2;
                    arrayList = emptyDaySleepCodedValuesList;
                    c = c2;
                } else if ((i4 + 1) % 60 == 0) {
                    SleepHourData sleepHourData = new SleepHourData();
                    sleepHourData.setDate(AppUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"));
                    it = it2;
                    String format = String.format("%02d:00:00", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(i3))}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    sleepHourData.setStartHour(format);
                    arrayList = emptyDaySleepCodedValuesList;
                    String format2 = String.format("%02d:00:00", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(11) + 1)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                    sleepHourData.setEndHour(format2);
                    sleepHourData.setActivityType(ActivityType.SLEEP.name());
                    sleepHourData.setMacAddress(BleApiManager.getInstance(this.f3344a).getBleApi().getMacAddress());
                    Object clone = arrayList4.clone();
                    Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Int>");
                    sleepHourData.setMinuteWiseData((ArrayList) clone);
                    sleepHourData.setAwakePerHour(a(arrayList4).getFirst().intValue());
                    sleepHourData.setLightSleepPerHour(a(arrayList4).getSecond().intValue());
                    sleepHourData.setDeepSleepPerHour(a(arrayList4).getThird().intValue());
                    sleepHourData.setTotalSleepPerHour(sleepHourData.getDeepSleepPerHour() + sleepHourData.getLightSleepPerHour());
                    arrayList3.add(sleepHourData);
                    c = '\f';
                    calendar.add(12, 60);
                    arrayList4.clear();
                    i4++;
                    c2 = c;
                    it2 = it;
                    emptyDaySleepCodedValuesList = arrayList;
                    i3 = 11;
                } else {
                    it = it2;
                    arrayList = emptyDaySleepCodedValuesList;
                    c = '\f';
                }
                i4++;
                c2 = c;
                it2 = it;
                emptyDaySleepCodedValuesList = arrayList;
                i3 = 11;
            }
            Iterator<KHEASleepDayData> it3 = it2;
            Iterator it4 = arrayList3.iterator();
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            while (it4.hasNext()) {
                SleepHourData sleepHourData2 = (SleepHourData) it4.next();
                i6 += sleepHourData2.getAwakePerHour();
                i5 += sleepHourData2.getLightSleepPerHour();
                i7 += sleepHourData2.getDeepSleepPerHour();
            }
            sleepDayData.mAwakeTime = i6;
            sleepDayData.mLightSleep = i5;
            sleepDayData.mDeepSleep = i7;
            SleepTimeLogBeanData sleepTimeLogBeanData = new SleepTimeLogBeanData();
            sleepTimeLogBeanData.setLogBeans(arrayList3);
            sleepDayData.timeLogBean = sleepTimeLogBeanData;
            sleepResponse.setSleepDayData(sleepDayData);
            sleepResponse.setComplete(next.equals(CollectionsKt___CollectionsKt.last((List<? extends Object>) kheaSleepDayDataList)));
            if (sleepResponse.isComplete()) {
                LogHelper.d("EastApexSleepFormatter", "Sleep Complete");
            }
            arrayList2.add(sleepResponse);
            macAddress = str2;
            it2 = it3;
        }
        return arrayList2;
    }

    @NotNull
    public final Context getContext() {
        return this.f3344a;
    }

    public final Triple<Integer, Integer, Integer> a(ArrayList<Integer> arrayList) {
        Iterator<Integer> it = arrayList.iterator();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            Integer next = it.next();
            int i4 = EABleSleepData.SleepMode.wake.value;
            if (next != null && next.intValue() == i4) {
                i3++;
            }
            int i5 = EABleSleepData.SleepMode.light.value;
            if (next != null && next.intValue() == i5) {
                i2++;
            }
            int i6 = EABleSleepData.SleepMode.deep.value;
            if (next != null && next.intValue() == i6) {
                i++;
            }
        }
        return new Triple<>(Integer.valueOf(i3), Integer.valueOf(i2), Integer.valueOf(i));
    }
}
