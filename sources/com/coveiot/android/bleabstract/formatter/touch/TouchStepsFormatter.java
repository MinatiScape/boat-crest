package com.coveiot.android.bleabstract.formatter.touch;

import android.content.Context;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.StepsDayData;
import com.coveiot.android.bleabstract.response.StepsHourData;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.response.StepsTimeLogBeanData;
import com.coveiot.android.bleabstract.utils.idoUtils.IDOUtils;
import com.coveiot.khtouchdb.walk.EntityTGStepData;
import com.coveiot.khtouchdb.walk.model.KHTGStepDataItemBean;
import com.touchgui.sdk.bean.TGStepData;
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
public final class TouchStepsFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3384a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<TouchStepsFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.touch.TouchStepsFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, TouchStepsFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3385a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, TouchStepsFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public TouchStepsFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new TouchStepsFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3385a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TouchStepsFormatter(Context context) {
        this.f3384a = context;
    }

    public /* synthetic */ TouchStepsFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final List<StepsHourData> a(EntityTGStepData entityTGStepData) {
        Integer num;
        int i;
        ArrayList arrayList = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        Integer num2 = 0;
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        List<KHTGStepDataItemBean> items = entityTGStepData.getItems();
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
                int stepCount = items.get(first).getStepCount() + items.get(i2).getStepCount();
                int i3 = first + 2;
                int i4 = first + 3;
                int calories = items.get(first).getCalories() + items.get(i2).getCalories() + items.get(i3).getCalories() + items.get(i4).getCalories() + 0;
                num = num2;
                int distance = items.get(first).getDistance() + items.get(i2).getDistance() + items.get(i3).getDistance() + items.get(i4).getDistance() + 0;
                stepsHourData.mStepsPerHour = stepCount + items.get(i3).getStepCount() + items.get(i4).getStepCount() + 0;
                i = size;
                int i5 = step2;
                stepsHourData.mCaloriesPerHour = calories;
                stepsHourData.mDistancePerHour = distance;
                IDOUtils iDOUtils = IDOUtils.INSTANCE;
                Date time = calendar.getTime();
                Intrinsics.checkNotNullExpressionValue(time, "cal.time");
                stepsHourData.setStartHour(iDOUtils.convertDateIntoTimeFormat(time));
                calendar.add(11, 1);
                Date time2 = calendar.getTime();
                Intrinsics.checkNotNullExpressionValue(time2, "cal.time");
                stepsHourData.setEndHour(iDOUtils.convertDateIntoTimeFormat(time2));
                stepsHourData.mMinuteWiseData.add(Integer.valueOf(items.get(first).getStepCount()));
                stepsHourData.mMinuteWiseData.add(Integer.valueOf(items.get(i2).getStepCount()));
                stepsHourData.mMinuteWiseData.add(Integer.valueOf(items.get(i3).getStepCount()));
                stepsHourData.mMinuteWiseData.add(Integer.valueOf(items.get(i4).getStepCount()));
                stepsHourData.activeTime = items.get(first).getActiveTime();
                stepsHourData.setDate(BleApiUtils.INSTANCE.getDateFromTimeStamp(entityTGStepData.getTimeStamp()));
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
            for (int i9 = 0; i9 < i; i9++) {
                if (i9 == 0) {
                    IDOUtils iDOUtils2 = IDOUtils.INSTANCE;
                    Date time3 = calendar.getTime();
                    Intrinsics.checkNotNullExpressionValue(time3, "cal.time");
                    stepsHourData2.setStartHour(iDOUtils2.convertDateIntoTimeFormat(time3));
                    calendar.add(11, 1);
                    Date time4 = calendar.getTime();
                    Intrinsics.checkNotNullExpressionValue(time4, "cal.time");
                    stepsHourData2.setEndHour(iDOUtils2.convertDateIntoTimeFormat(time4));
                }
                i6 += items.get((items.size() - i) + i9).getStepCount();
                i7 += items.get((items.size() - i) + i9).getCalories();
                i8 += items.get((items.size() - i) + i9).getDistance();
                stepsHourData2.mStepsPerHour = i6;
                stepsHourData2.mCaloriesPerHour = i7;
                stepsHourData2.mDistancePerHour = i8;
                stepsHourData2.setDate(BleApiUtils.INSTANCE.getDateFromTimeStamp(entityTGStepData.getTimeStamp()));
                arrayList2.set(i9, Integer.valueOf(items.get((items.size() - i) + i9).getStepCount()));
                stepsHourData2.mMinuteWiseData = arrayList2;
            }
            arrayList.add(stepsHourData2);
        }
        return arrayList;
    }

    @Nullable
    public final EntityTGStepData convertTGStepDataToEntity(@NotNull TGStepData tgStepsData) {
        Intrinsics.checkNotNullParameter(tgStepsData, "tgStepsData");
        if (tgStepsData.getItems() != null) {
            String date = tgStepsData.getDate().toString();
            Intrinsics.checkNotNullExpressionValue(date, "tgStepsData.date.toString()");
            EntityTGStepData entityTGStepData = new EntityTGStepData(date, BleApiManager.getInstance(this.f3384a).getBleApi().getMacAddress());
            entityTGStepData.setMinuteOffset(tgStepsData.getMinuteOffset());
            entityTGStepData.setPerMinute(tgStepsData.getPerMinute());
            entityTGStepData.setItemCount(tgStepsData.getItemCount());
            entityTGStepData.setPacketCount(tgStepsData.getPacketCount());
            entityTGStepData.setTotalSteps(tgStepsData.getTotalSteps());
            entityTGStepData.setTotalCal(tgStepsData.getTotalCal());
            entityTGStepData.setTotalDistance(tgStepsData.getTotalDistance());
            entityTGStepData.setTotalActiveTime(tgStepsData.getTotalActiveTime());
            entityTGStepData.setStandCount(tgStepsData.getStandCount());
            entityTGStepData.setTimeStamp(tgStepsData.getDate().getTime());
            ArrayList arrayList = new ArrayList();
            List<TGStepData.ItemBean> items = tgStepsData.getItems();
            Intrinsics.checkNotNull(items);
            int size = items.size();
            for (int i = 0; i < size; i++) {
                KHTGStepDataItemBean kHTGStepDataItemBean = new KHTGStepDataItemBean();
                List<TGStepData.ItemBean> items2 = tgStepsData.getItems();
                TGStepData.ItemBean itemBean = items2 != null ? items2.get(i) : null;
                Intrinsics.checkNotNull(itemBean);
                kHTGStepDataItemBean.setMode(itemBean.getMode());
                List<TGStepData.ItemBean> items3 = tgStepsData.getItems();
                TGStepData.ItemBean itemBean2 = items3 != null ? items3.get(i) : null;
                Intrinsics.checkNotNull(itemBean2);
                kHTGStepDataItemBean.setStepCount(itemBean2.getStepCount());
                List<TGStepData.ItemBean> items4 = tgStepsData.getItems();
                TGStepData.ItemBean itemBean3 = items4 != null ? items4.get(i) : null;
                Intrinsics.checkNotNull(itemBean3);
                kHTGStepDataItemBean.setActiveTime(itemBean3.getActiveTime());
                List<TGStepData.ItemBean> items5 = tgStepsData.getItems();
                TGStepData.ItemBean itemBean4 = items5 != null ? items5.get(i) : null;
                Intrinsics.checkNotNull(itemBean4);
                kHTGStepDataItemBean.setDistance(itemBean4.getDistance());
                List<TGStepData.ItemBean> items6 = tgStepsData.getItems();
                TGStepData.ItemBean itemBean5 = items6 != null ? items6.get(i) : null;
                Intrinsics.checkNotNull(itemBean5);
                kHTGStepDataItemBean.setCalories(itemBean5.getCalories());
                arrayList.add(kHTGStepDataItemBean);
            }
            entityTGStepData.setItems(arrayList);
            return entityTGStepData;
        }
        return null;
    }

    @NotNull
    public final Context getContext() {
        return this.f3384a;
    }

    @NotNull
    public final StepsResponse getStepResponse(@NotNull EntityTGStepData entityTGStepData) {
        Intrinsics.checkNotNullParameter(entityTGStepData, "entityTGStepData");
        StepsResponse stepsResponse = new StepsResponse();
        StepsDayData stepsDayData = new StepsDayData();
        stepsDayData.mCalories = entityTGStepData.getTotalCal();
        stepsDayData.mDistance = entityTGStepData.getTotalDistance();
        stepsDayData.mSteps = entityTGStepData.getTotalSteps();
        stepsDayData.active_time = entityTGStepData.getTotalActiveTime();
        stepsDayData.mDate = BleApiUtils.INSTANCE.getDateFromTimeStamp(entityTGStepData.getTimeStamp());
        StepsTimeLogBeanData stepsTimeLogBeanData = new StepsTimeLogBeanData();
        stepsTimeLogBeanData.setLogBeans(a(entityTGStepData));
        stepsDayData.setTimeLogBean(stepsTimeLogBeanData);
        stepsResponse.setStepsDayData(stepsDayData);
        return stepsResponse;
    }
}
