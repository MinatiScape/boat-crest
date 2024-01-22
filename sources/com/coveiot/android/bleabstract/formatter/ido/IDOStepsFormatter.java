package com.coveiot.android.bleabstract.formatter.ido;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.response.StepsDayData;
import com.coveiot.android.bleabstract.response.StepsHourData;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.response.StepsTimeLogBeanData;
import com.coveiot.android.bleabstract.utils.idoUtils.IDOUtils;
import com.coveiot.khidodb.walk.EntityHealthSportV3;
import com.coveiot.khidodb.walk.model.KHHealthSportV3Item;
import com.ido.ble.data.manage.database.HealthSportV3;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.h;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class IDOStepsFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3362a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<IDOStepsFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.ido.IDOStepsFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, IDOStepsFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3363a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, IDOStepsFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public IDOStepsFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new IDOStepsFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3363a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public IDOStepsFormatter(Context context) {
        this.f3362a = context;
    }

    public /* synthetic */ IDOStepsFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final EntityHealthSportV3 convertHealthSportV3ToEntity(@NotNull HealthSportV3 healthSport) {
        Intrinsics.checkNotNullParameter(healthSport, "healthSport");
        if (healthSport.items != null) {
            EntityHealthSportV3 entityHealthSportV3 = new EntityHealthSportV3(healthSport.day, healthSport.month, healthSport.year, healthSport.start_time, IDOUtils.INSTANCE.getMacAddress(this.f3362a));
            entityHealthSportV3.setHour(healthSport.hour);
            entityHealthSportV3.setMinute(healthSport.minute);
            entityHealthSportV3.setSecond(healthSport.second);
            entityHealthSportV3.setPer_minute(healthSport.per_minute);
            entityHealthSportV3.setTotal_active_time(healthSport.total_active_time);
            entityHealthSportV3.setTotal_activity_calories(healthSport.total_activity_calories);
            entityHealthSportV3.setTotal_distances(healthSport.total_distances);
            entityHealthSportV3.setTotal_rest_activity_calories(healthSport.total_rest_activity_calories);
            entityHealthSportV3.setTotal_step(healthSport.total_step);
            ArrayList arrayList = new ArrayList();
            int size = healthSport.items.size();
            for (int i = 0; i < size; i++) {
                KHHealthSportV3Item kHHealthSportV3Item = new KHHealthSportV3Item();
                kHHealthSportV3Item.setActive_time(healthSport.items.get(i).active_time);
                kHHealthSportV3Item.setActivity_calories(healthSport.items.get(i).activity_calories);
                kHHealthSportV3Item.setRest_activity_calories(healthSport.items.get(i).rest_activity_calories);
                kHHealthSportV3Item.setDistance(healthSport.items.get(i).distance);
                kHHealthSportV3Item.setStep_count(healthSport.items.get(i).step_count);
                kHHealthSportV3Item.setMode(healthSport.items.get(i).mode);
                arrayList.add(kHHealthSportV3Item);
            }
            entityHealthSportV3.setItems(arrayList);
            entityHealthSportV3.setItem_count(healthSport.item_count);
            entityHealthSportV3.setType(healthSport.type);
            entityHealthSportV3.setWear_flag_array(healthSport.wear_flag_array);
            entityHealthSportV3.setWalk_goal_time(healthSport.walk_goal_time);
            entityHealthSportV3.setTimestamp(IDOUtils.INSTANCE.getCalendarMillisFromDate(healthSport.year, healthSport.month, healthSport.day, 0, 0, 0));
            return entityHealthSportV3;
        }
        return null;
    }

    @NotNull
    public final Context getContext() {
        return this.f3362a;
    }

    @NotNull
    public final List<StepsHourData> getStepHourlyData(@NotNull EntityHealthSportV3 healthSport) {
        Integer num;
        int i;
        Intrinsics.checkNotNullParameter(healthSport, "healthSport");
        ArrayList arrayList = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        Integer num2 = 0;
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        List<KHHealthSportV3Item> items = healthSport.getItems();
        Intrinsics.checkNotNull(items);
        int size = items.size() % 4;
        IntProgression step = h.step(h.until(0, items.size() - size), 4);
        int first = step.getFirst();
        int last = step.getLast();
        int step2 = step.getStep();
        if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
            while (true) {
                StepsHourData stepsHourData = new StepsHourData();
                int i2 = first + 1;
                int i3 = first + 2;
                int i4 = first + 3;
                int activity_calories = items.get(first).getActivity_calories() + items.get(i2).getActivity_calories() + items.get(i3).getActivity_calories() + items.get(i4).getActivity_calories() + 0;
                num = num2;
                int distance = items.get(first).getDistance() + items.get(i2).getDistance() + items.get(i3).getDistance() + items.get(i4).getDistance() + 0;
                stepsHourData.mStepsPerHour = items.get(first).getStep_count() + items.get(i2).getStep_count() + items.get(i3).getStep_count() + items.get(i4).getStep_count() + 0;
                i = size;
                int i5 = step2;
                stepsHourData.mCaloriesPerHour = activity_calories;
                stepsHourData.mDistancePerHour = distance;
                IDOUtils iDOUtils = IDOUtils.INSTANCE;
                Date time = calendar.getTime();
                Intrinsics.checkNotNullExpressionValue(time, "cal.time");
                stepsHourData.setStartHour(iDOUtils.convertDateIntoTimeFormat(time));
                calendar.add(11, 1);
                Date time2 = calendar.getTime();
                Intrinsics.checkNotNullExpressionValue(time2, "cal.time");
                stepsHourData.setEndHour(iDOUtils.convertDateIntoTimeFormat(time2));
                stepsHourData.mMinuteWiseData.add(Integer.valueOf(items.get(first).getStep_count()));
                stepsHourData.mMinuteWiseData.add(Integer.valueOf(items.get(i2).getStep_count()));
                stepsHourData.mMinuteWiseData.add(Integer.valueOf(items.get(i3).getStep_count()));
                stepsHourData.mMinuteWiseData.add(Integer.valueOf(items.get(i4).getStep_count()));
                stepsHourData.activeTime = items.get(first).getActive_time();
                stepsHourData.setDate(iDOUtils.convertDateFormat(healthSport.getYear(), healthSport.getMonth(), healthSport.getDay()));
                arrayList.add(stepsHourData);
                if (first == last) {
                    break;
                }
                first += i5;
                size = i;
                step2 = i5;
                num2 = num;
            }
        } else {
            num = num2;
            i = size;
        }
        if (i != 0) {
            ArrayList<Integer> arrayList2 = new ArrayList<>();
            StepsHourData stepsHourData2 = new StepsHourData();
            Integer num3 = num;
            arrayList2.add(num3);
            arrayList2.add(num3);
            arrayList2.add(num3);
            arrayList2.add(num3);
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = i;
            for (int i10 = 0; i10 < i9; i10++) {
                if (i10 == 0) {
                    IDOUtils iDOUtils2 = IDOUtils.INSTANCE;
                    Date time3 = calendar.getTime();
                    Intrinsics.checkNotNullExpressionValue(time3, "cal.time");
                    stepsHourData2.setStartHour(iDOUtils2.convertDateIntoTimeFormat(time3));
                    calendar.add(11, 1);
                    Date time4 = calendar.getTime();
                    Intrinsics.checkNotNullExpressionValue(time4, "cal.time");
                    stepsHourData2.setEndHour(iDOUtils2.convertDateIntoTimeFormat(time4));
                }
                i6 += items.get((items.size() - i9) + i10).getStep_count();
                i7 += items.get((items.size() - i9) + i10).getActivity_calories();
                i8 += items.get((items.size() - i9) + i10).getDistance();
                stepsHourData2.mStepsPerHour = i6;
                stepsHourData2.mCaloriesPerHour = i7;
                stepsHourData2.mDistancePerHour = i8;
                stepsHourData2.setDate(IDOUtils.INSTANCE.convertDateFormat(healthSport.getYear(), healthSport.getMonth(), healthSport.getDay()));
                arrayList2.set(i10, Integer.valueOf(items.get((items.size() - i9) + i10).getStep_count()));
                stepsHourData2.mMinuteWiseData = arrayList2;
            }
            arrayList.add(stepsHourData2);
        }
        return arrayList;
    }

    @NotNull
    public final StepsResponse getStepResponse(@NotNull EntityHealthSportV3 healthSport) {
        Intrinsics.checkNotNullParameter(healthSport, "healthSport");
        StepsResponse stepsResponse = new StepsResponse();
        StepsDayData stepsDayData = new StepsDayData();
        stepsDayData.mCalories = healthSport.getTotal_activity_calories();
        stepsDayData.mDistance = healthSport.getTotal_distances();
        stepsDayData.mSteps = healthSport.getTotal_step();
        stepsDayData.active_time = healthSport.getTotal_active_time();
        stepsDayData.mDate = IDOUtils.INSTANCE.convertDateFormat(healthSport.getYear(), healthSport.getMonth(), healthSport.getDay());
        StepsTimeLogBeanData stepsTimeLogBeanData = new StepsTimeLogBeanData();
        stepsTimeLogBeanData.setLogBeans(getStepHourlyData(healthSport));
        stepsDayData.setTimeLogBean(stepsTimeLogBeanData);
        stepsResponse.setStepsDayData(stepsDayData);
        return stepsResponse;
    }
}
