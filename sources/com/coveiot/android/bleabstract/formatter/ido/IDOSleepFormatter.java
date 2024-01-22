package com.coveiot.android.bleabstract.formatter.ido;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.response.SleepDayData;
import com.coveiot.android.bleabstract.response.SleepHourData;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.SleepTimeLogBeanData;
import com.coveiot.android.bleabstract.utils.idoUtils.IDOUtils;
import com.coveiot.khidodb.sleep.EntityHealthSleepV3;
import com.coveiot.khidodb.sleep.model.KHHealthSleepV3Item;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.utils.utility.AppUtils;
import com.ido.ble.data.manage.database.HealthSleepV3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import kotlin.Triple;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class IDOSleepFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3360a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<IDOSleepFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.ido.IDOSleepFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, IDOSleepFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3361a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, IDOSleepFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public IDOSleepFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new IDOSleepFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3361a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public IDOSleepFormatter(Context context) {
        this.f3360a = context;
    }

    public /* synthetic */ IDOSleepFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final Triple<Integer, Integer, Integer> a(ArrayList<Integer> arrayList) {
        Iterator<Integer> it = arrayList.iterator();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            Integer next = it.next();
            if (next != null && next.intValue() == 1) {
                i3++;
            }
            if (next != null && next.intValue() == 2) {
                i2++;
            }
            if (next != null && next.intValue() == 3) {
                i++;
            }
        }
        return new Triple<>(Integer.valueOf(i3), Integer.valueOf(i2), Integer.valueOf(i));
    }

    @Nullable
    public final EntityHealthSleepV3 convertHealthSleepV3ToEntity(@NotNull HealthSleepV3 healthSleep) {
        Intrinsics.checkNotNullParameter(healthSleep, "healthSleep");
        if (healthSleep.items != null) {
            EntityHealthSleepV3 entityHealthSleepV3 = new EntityHealthSleepV3(healthSleep.get_up_day, healthSleep.get_up_month, healthSleep.get_up_year, healthSleep.get_up_hour, healthSleep.get_up_minte, IDOUtils.INSTANCE.getMacAddress(this.f3360a));
            entityHealthSleepV3.setData_type(healthSleep.data_type);
            entityHealthSleepV3.setFall_asleep_year(healthSleep.fall_asleep_year);
            entityHealthSleepV3.setFall_asleep_month(healthSleep.fall_asleep_month);
            entityHealthSleepV3.setFall_asleep_day(healthSleep.fall_asleep_day);
            entityHealthSleepV3.setFall_asleep_hour(healthSleep.fall_asleep_hour);
            entityHealthSleepV3.setFall_asleep_minte(healthSleep.fall_asleep_minte);
            entityHealthSleepV3.setTotal_sleep_time_mins(healthSleep.total_sleep_time_mins);
            entityHealthSleepV3.setWake_mins(healthSleep.wake_mins);
            entityHealthSleepV3.setLight_mins(healthSleep.light_mins);
            entityHealthSleepV3.setRem_mins(healthSleep.rem_mins);
            entityHealthSleepV3.setDeep_mins(healthSleep.deep_mins);
            entityHealthSleepV3.setWake_count(healthSleep.wake_count);
            entityHealthSleepV3.setDeep_count(healthSleep.deep_count);
            entityHealthSleepV3.setLight_count(healthSleep.light_count);
            entityHealthSleepV3.setRem_count(healthSleep.rem_count);
            entityHealthSleepV3.setAwrr_status(healthSleep.awrr_status);
            entityHealthSleepV3.setSleep_score(healthSleep.sleep_score);
            entityHealthSleepV3.setBreath_quality(healthSleep.breath_quality);
            ArrayList arrayList = new ArrayList();
            int size = healthSleep.items.size();
            for (int i = 0; i < size; i++) {
                KHHealthSleepV3Item kHHealthSleepV3Item = new KHHealthSleepV3Item();
                kHHealthSleepV3Item.setStage(healthSleep.items.get(i).stage);
                kHHealthSleepV3Item.setDuration(healthSleep.items.get(i).duration);
                arrayList.add(kHHealthSleepV3Item);
            }
            entityHealthSleepV3.setItems(arrayList);
            entityHealthSleepV3.setTimestamp(IDOUtils.INSTANCE.getCalendarMillisFromDate(healthSleep.fall_asleep_year, healthSleep.fall_asleep_month, healthSleep.fall_asleep_day, 0, 0, 0));
            return entityHealthSleepV3;
        }
        return null;
    }

    @NotNull
    public final ArrayList<SleepResponse> convertSleepData(@Nullable List<EntityHealthSleepV3> list) {
        Iterator<EntityHealthSleepV3> it;
        String str;
        ArrayList<Integer> arrayList;
        char c;
        int i;
        String macAddress = IDOUtils.INSTANCE.getMacAddress(this.f3360a);
        ArrayList<SleepResponse> arrayList2 = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        Intrinsics.checkNotNull(list);
        Iterator<EntityHealthSleepV3> it2 = list.iterator();
        while (it2.hasNext()) {
            EntityHealthSleepV3 next = it2.next();
            IDOUtils iDOUtils = IDOUtils.INSTANCE;
            String str2 = "yyyy-MM-dd";
            cal.setTime(AppUtils.parseDate(iDOUtils.convertDateFormat(next.getGet_up_year(), next.getGet_up_month(), next.getGet_up_day()), "yyyy-MM-dd"));
            SleepResponse sleepResponse = new SleepResponse();
            SleepDayData sleepDayData = new SleepDayData();
            sleepDayData.mMacAddress = macAddress;
            sleepDayData.mDate = iDOUtils.convertDateFormat(next.getGet_up_year(), next.getGet_up_month(), next.getGet_up_day());
            sleepDayData.setmActivityType(ActivityType.SLEEP.name());
            Intrinsics.checkNotNullExpressionValue(cal, "cal");
            ArrayList arrayList3 = new ArrayList();
            ArrayList<Integer> emptyDaySleepCodedValuesList = IDOUtils.getEmptyDaySleepCodedValuesList();
            int fall_asleep_hour = (next.getFall_asleep_hour() * 60) + next.getFall_asleep_minte();
            List<KHHealthSleepV3Item> items = next.getItems();
            Intrinsics.checkNotNull(items);
            for (KHHealthSleepV3Item kHHealthSleepV3Item : items) {
                int duration = kHHealthSleepV3Item.getDuration();
                int i2 = fall_asleep_hour + duration;
                int i3 = fall_asleep_hour;
                int i4 = duration;
                int i5 = i3;
                while (fall_asleep_hour < i2) {
                    String str3 = macAddress;
                    if (i3 > 1439) {
                        i4 = (i5 + i4) - 1440;
                        i3 = 0;
                        i5 = 0;
                    }
                    emptyDaySleepCodedValuesList.set(i3, Integer.valueOf(kHHealthSleepV3Item.getStage()));
                    i3++;
                    fall_asleep_hour++;
                    macAddress = str3;
                }
                fall_asleep_hour = i5 + i4;
            }
            String str4 = macAddress;
            int i6 = 11;
            cal.set(11, 0);
            char c2 = '\f';
            cal.set(12, 0);
            cal.set(13, 0);
            cal.set(14, 0);
            ArrayList<Integer> arrayList4 = new ArrayList<>();
            int size = emptyDaySleepCodedValuesList.size();
            int i7 = 0;
            while (i7 < size) {
                arrayList4.add(emptyDaySleepCodedValuesList.get(i7));
                if (i7 <= 0 || (i7 + 1) % 60 != 0) {
                    it = it2;
                    str = str2;
                    arrayList = emptyDaySleepCodedValuesList;
                    c = c2;
                } else {
                    SleepHourData sleepHourData = new SleepHourData();
                    sleepHourData.setDate(AppUtils.formatDate(cal.getTime(), str2));
                    int i8 = cal.get(i6);
                    if (i8 == 23) {
                        it = it2;
                        str = str2;
                        i = 0;
                    } else {
                        i = i8 + 1;
                        it = it2;
                        str = str2;
                    }
                    String format = String.format("%02d:00:00", Arrays.copyOf(new Object[]{Integer.valueOf(i8)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    sleepHourData.setStartHour(format);
                    arrayList = emptyDaySleepCodedValuesList;
                    String format2 = String.format("%02d:00:00", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                    sleepHourData.setEndHour(format2);
                    sleepHourData.setActivityType(ActivityType.SLEEP.name());
                    sleepHourData.setMacAddress(IDOUtils.INSTANCE.getMacAddress(this.f3360a));
                    Object clone = arrayList4.clone();
                    Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Int>");
                    sleepHourData.setMinuteWiseData((ArrayList) clone);
                    sleepHourData.setAwakePerHour(a(arrayList4).getFirst().intValue());
                    sleepHourData.setLightSleepPerHour(a(arrayList4).getSecond().intValue());
                    sleepHourData.setDeepSleepPerHour(a(arrayList4).getThird().intValue());
                    sleepHourData.setTotalSleepPerHour(sleepHourData.getDeepSleepPerHour() + sleepHourData.getLightSleepPerHour());
                    arrayList3.add(sleepHourData);
                    i6 = 11;
                    if (cal.get(11) == 11) {
                        cal.add(6, -1);
                        cal.set(11, 11);
                    }
                    c = '\f';
                    cal.add(12, 60);
                    arrayList4.clear();
                }
                i7++;
                c2 = c;
                it2 = it;
                str2 = str;
                emptyDaySleepCodedValuesList = arrayList;
            }
            Iterator<EntityHealthSleepV3> it3 = it2;
            Iterator it4 = arrayList3.iterator();
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            while (it4.hasNext()) {
                SleepHourData sleepHourData2 = (SleepHourData) it4.next();
                i10 += sleepHourData2.getAwakePerHour();
                i9 += sleepHourData2.getLightSleepPerHour();
                i11 += sleepHourData2.getDeepSleepPerHour();
            }
            sleepDayData.mAwakeTime = i10;
            sleepDayData.mLightSleep = i9;
            sleepDayData.mDeepSleep = i11;
            sleepDayData.mTotalSleep = next.getTotal_sleep_time_mins();
            sleepDayData.mSleepScore = next.getSleep_score();
            sleepDayData.mBreathQuality = next.getBreath_quality();
            SleepTimeLogBeanData sleepTimeLogBeanData = new SleepTimeLogBeanData();
            sleepTimeLogBeanData.setLogBeans(arrayList3);
            sleepDayData.timeLogBean = sleepTimeLogBeanData;
            sleepResponse.setSleepDayData(sleepDayData);
            sleepResponse.setComplete(next.isProcessed());
            arrayList2.add(sleepResponse);
            it2 = it3;
            macAddress = str4;
        }
        return arrayList2;
    }

    @NotNull
    public final Context getContext() {
        return this.f3360a;
    }
}
