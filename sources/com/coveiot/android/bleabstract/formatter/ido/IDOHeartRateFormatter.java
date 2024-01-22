package com.coveiot.android.bleabstract.formatter.ido;

import android.content.Context;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.response.HeartRateDayData;
import com.coveiot.android.bleabstract.response.HeartRateHourData;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.HeartRateTimeLogBeanData;
import com.coveiot.android.bleabstract.utils.idoUtils.IDOUtils;
import com.coveiot.khidodb.heartrate.EntityHealthHeartRateSecond;
import com.coveiot.khidodb.heartrate.model.KHHealthHeartRateHighLowItem;
import com.coveiot.khidodb.heartrate.model.KHHealthHeartRateSecondItem;
import com.coveiot.khidodb.heartrate.model.KHHealthHeartRateSecond_Interval;
import com.coveiot.utils.utility.AppUtils;
import com.ido.ble.data.manage.database.HealthHeartRateSecond;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class IDOHeartRateFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3356a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<IDOHeartRateFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.ido.IDOHeartRateFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, IDOHeartRateFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3357a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, IDOHeartRateFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public IDOHeartRateFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new IDOHeartRateFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3357a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public IDOHeartRateFormatter(Context context) {
        this.f3356a = context;
    }

    public /* synthetic */ IDOHeartRateFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final List<HeartRateHourData> a(List<EntityHealthHeartRateSecond> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (EntityHealthHeartRateSecond entityHealthHeartRateSecond : list) {
            List<KHHealthHeartRateSecondItem> items = entityHealthHeartRateSecond.getItems();
            Intrinsics.checkNotNull(items);
            arrayList2.addAll(items);
        }
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        calendar.set(11, 0);
        calendar.set(12, 0);
        int i = 13;
        calendar.set(13, 0);
        HashMap hashMap = new HashMap();
        ArrayList<Integer> emptyHourCodedValuesList = IDOUtils.INSTANCE.getEmptyHourCodedValuesList();
        int size = arrayList2.size();
        int i2 = 0;
        int i3 = -1;
        while (i2 < size) {
            Object obj = arrayList2.get(i2);
            Intrinsics.checkNotNullExpressionValue(obj, "heartRateItems[i]");
            KHHealthHeartRateSecondItem kHHealthHeartRateSecondItem = (KHHealthHeartRateSecondItem) obj;
            calendar.add(i, kHHealthHeartRateSecondItem.getOffset());
            if (i3 == -1) {
                i3 = calendar.get(11);
            }
            if (i3 != calendar.get(11) && !hashMap.containsKey(Integer.valueOf(calendar.get(11)))) {
                Integer valueOf = Integer.valueOf(i3);
                Object clone = emptyHourCodedValuesList.clone();
                Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Int>");
                hashMap.put(valueOf, (ArrayList) clone);
                int i4 = calendar.get(11);
                emptyHourCodedValuesList = IDOUtils.INSTANCE.getEmptyHourCodedValuesList();
                emptyHourCodedValuesList.set(calendar.get(12), Integer.valueOf(kHHealthHeartRateSecondItem.getHeartRateVal()));
                i3 = i4;
            } else {
                emptyHourCodedValuesList.set(calendar.get(12), Integer.valueOf(kHHealthHeartRateSecondItem.getHeartRateVal()));
            }
            if (i2 == arrayList2.size() - 1 && !Intrinsics.areEqual(emptyHourCodedValuesList, IDOUtils.INSTANCE.getEmptyHourCodedValuesList())) {
                Integer valueOf2 = Integer.valueOf(calendar.get(11));
                Object clone2 = emptyHourCodedValuesList.clone();
                Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Int>");
                hashMap.put(valueOf2, (ArrayList) clone2);
            }
            i2++;
            i = 13;
        }
        Set keySet = hashMap.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "codedValueHashMap.keys");
        for (Number number : CollectionsKt___CollectionsKt.sorted(CollectionsKt___CollectionsKt.toList(keySet))) {
            int intValue = number.intValue();
            HeartRateHourData heartRateHourData = new HeartRateHourData();
            heartRateHourData.setDate(AppUtils.formatDate(list.get(0).getDate(), "yyyy-MM-dd"));
            String format = String.format("%02d:00:00", Arrays.copyOf(new Object[]{Integer.valueOf(intValue)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            heartRateHourData.setStartHour(format);
            Object[] objArr = new Object[1];
            int i5 = intValue + 1;
            if (i5 >= 24) {
                i5 = 0;
            }
            objArr[0] = Integer.valueOf(i5);
            String format2 = String.format("%02d:00:00", Arrays.copyOf(objArr, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
            heartRateHourData.setEndHour(format2);
            heartRateHourData.setMacAddress(list.get(0).getMacAddress());
            heartRateHourData.mMinuteWiseData = (ArrayList) hashMap.get(Integer.valueOf(intValue));
            BleApiUtils bleApiUtils = BleApiUtils.INSTANCE;
            Integer maxValueFromList = bleApiUtils.getMaxValueFromList((List) hashMap.get(Integer.valueOf(intValue)));
            Intrinsics.checkNotNull(maxValueFromList);
            heartRateHourData.maxHeartRatePerHour = maxValueFromList.intValue();
            Integer minValueFromList = bleApiUtils.getMinValueFromList((List) hashMap.get(Integer.valueOf(intValue)));
            Intrinsics.checkNotNull(minValueFromList);
            heartRateHourData.minHeartRatePerHour = minValueFromList.intValue();
            Integer avgValueFromList = bleApiUtils.getAvgValueFromList((List) hashMap.get(Integer.valueOf(intValue)));
            Intrinsics.checkNotNull(avgValueFromList);
            heartRateHourData.avgHeartRatePerHour = avgValueFromList.intValue();
            arrayList.add(heartRateHourData);
        }
        return arrayList;
    }

    @NotNull
    public final HeartRateResponse convertEntityHeartRateToHeartRateResponse(@NotNull List<EntityHealthHeartRateSecond> entityHealthHeartRateSecond) {
        List<KHHealthHeartRateSecondItem> items;
        List<KHHealthHeartRateSecondItem> items2;
        List<KHHealthHeartRateSecondItem> items3;
        Intrinsics.checkNotNullParameter(entityHealthHeartRateSecond, "entityHealthHeartRateSecond");
        HeartRateResponse heartRateResponse = new HeartRateResponse();
        HeartRateDayData heartRateDayData = new HeartRateDayData();
        int i = -1;
        for (EntityHealthHeartRateSecond entityHealthHeartRateSecond2 : entityHealthHeartRateSecond) {
            if (entityHealthHeartRateSecond2.getItems() != null) {
                Intrinsics.checkNotNull(entityHealthHeartRateSecond2.getItems());
                if (!items3.isEmpty()) {
                    List<KHHealthHeartRateSecondItem> items4 = entityHealthHeartRateSecond2.getItems();
                    Intrinsics.checkNotNull(items4);
                    for (KHHealthHeartRateSecondItem kHHealthHeartRateSecondItem : items4) {
                        if (i == -1) {
                            i = kHHealthHeartRateSecondItem.getHeartRateVal();
                        } else if (i < kHHealthHeartRateSecondItem.getHeartRateVal()) {
                            i = kHHealthHeartRateSecondItem.getHeartRateVal();
                        }
                    }
                }
            }
        }
        heartRateDayData.setMaxHeartRate(i);
        int i2 = -1;
        for (EntityHealthHeartRateSecond entityHealthHeartRateSecond3 : entityHealthHeartRateSecond) {
            if (entityHealthHeartRateSecond3.getItems() != null) {
                Intrinsics.checkNotNull(entityHealthHeartRateSecond3.getItems());
                if (!items2.isEmpty()) {
                    List<KHHealthHeartRateSecondItem> items5 = entityHealthHeartRateSecond3.getItems();
                    Intrinsics.checkNotNull(items5);
                    for (KHHealthHeartRateSecondItem kHHealthHeartRateSecondItem2 : items5) {
                        if (i2 == -1 && kHHealthHeartRateSecondItem2.getHeartRateVal() != 0) {
                            i2 = kHHealthHeartRateSecondItem2.getHeartRateVal();
                        } else if (kHHealthHeartRateSecondItem2.getHeartRateVal() != 0 && i2 > kHHealthHeartRateSecondItem2.getHeartRateVal()) {
                            i2 = kHHealthHeartRateSecondItem2.getHeartRateVal();
                        }
                    }
                }
            }
        }
        heartRateDayData.setMinHeartRate(i2);
        int i3 = 0;
        int i4 = 0;
        for (EntityHealthHeartRateSecond entityHealthHeartRateSecond4 : entityHealthHeartRateSecond) {
            if (entityHealthHeartRateSecond4.getItems() != null) {
                Intrinsics.checkNotNull(entityHealthHeartRateSecond4.getItems());
                if (!items.isEmpty()) {
                    List<KHHealthHeartRateSecondItem> items6 = entityHealthHeartRateSecond4.getItems();
                    Intrinsics.checkNotNull(items6);
                    for (KHHealthHeartRateSecondItem kHHealthHeartRateSecondItem3 : items6) {
                        if (kHHealthHeartRateSecondItem3.getHeartRateVal() != 0) {
                            i4 += kHHealthHeartRateSecondItem3.getHeartRateVal();
                            i3++;
                        }
                    }
                }
            }
        }
        heartRateDayData.setAvgHeartRate(i3 > 0 ? i4 / i3 : 0);
        heartRateDayData.mMacAddress = entityHealthHeartRateSecond.get(0).getMacAddress();
        heartRateDayData.mDate = AppUtils.formatDate(entityHealthHeartRateSecond.get(0).getDate(), "yyyy-MM-dd");
        HeartRateTimeLogBeanData heartRateTimeLogBeanData = new HeartRateTimeLogBeanData();
        heartRateTimeLogBeanData.setLogBeans(a(entityHealthHeartRateSecond));
        heartRateDayData.setTimeLogBean(heartRateTimeLogBeanData);
        heartRateResponse.setHeartRateData(heartRateDayData);
        return heartRateResponse;
    }

    @Nullable
    public final EntityHealthHeartRateSecond convertHealthHeartRateSecondToEntity(@NotNull HealthHeartRateSecond heartRateSecond) {
        Intrinsics.checkNotNullParameter(heartRateSecond, "heartRateSecond");
        if (heartRateSecond.items != null) {
            EntityHealthHeartRateSecond entityHealthHeartRateSecond = new EntityHealthHeartRateSecond(heartRateSecond.day, heartRateSecond.month, heartRateSecond.year, heartRateSecond.startTime, IDOUtils.INSTANCE.getMacAddress(this.f3356a));
            entityHealthHeartRateSecond.setSilentHR(heartRateSecond.silentHR);
            entityHealthHeartRateSecond.setDataId(heartRateSecond.getDataId());
            entityHealthHeartRateSecond.setDId(heartRateSecond.getDId());
            entityHealthHeartRateSecond.setDate(heartRateSecond.getDate());
            entityHealthHeartRateSecond.setFive_min_max_data(heartRateSecond.five_min_max_data);
            entityHealthHeartRateSecond.setFive_min_avg_data(heartRateSecond.five_min_avg_data);
            entityHealthHeartRateSecond.setFive_min_data(heartRateSecond.five_min_data);
            entityHealthHeartRateSecond.setFive_min_min_data(heartRateSecond.five_min_min_data);
            ArrayList arrayList = new ArrayList();
            int size = heartRateSecond.items.size();
            for (int i = 0; i < size; i++) {
                KHHealthHeartRateSecondItem kHHealthHeartRateSecondItem = new KHHealthHeartRateSecondItem();
                kHHealthHeartRateSecondItem.setOffset(heartRateSecond.items.get(i).offset);
                kHHealthHeartRateSecondItem.setHeartRateVal(heartRateSecond.items.get(i).heartRateVal);
                arrayList.add(kHHealthHeartRateSecondItem);
            }
            entityHealthHeartRateSecond.setItems(arrayList);
            if (heartRateSecond.hr_data != null) {
                ArrayList arrayList2 = new ArrayList();
                int size2 = heartRateSecond.hr_data.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    KHHealthHeartRateHighLowItem kHHealthHeartRateHighLowItem = new KHHealthHeartRateHighLowItem();
                    kHHealthHeartRateHighLowItem.setHour(heartRateSecond.hr_data.get(i2).hour);
                    kHHealthHeartRateHighLowItem.setMinute(heartRateSecond.hr_data.get(i2).minute);
                    kHHealthHeartRateHighLowItem.setHeart_rate(heartRateSecond.hr_data.get(i2).heart_rate);
                    kHHealthHeartRateHighLowItem.setType(heartRateSecond.hr_data.get(i2).type);
                    arrayList2.add(kHHealthHeartRateHighLowItem);
                }
                entityHealthHeartRateSecond.setHr_data(arrayList2);
            }
            if (heartRateSecond.hrInterval != null) {
                ArrayList arrayList3 = new ArrayList();
                int size3 = heartRateSecond.hrInterval.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    KHHealthHeartRateSecond_Interval kHHealthHeartRateSecond_Interval = new KHHealthHeartRateSecond_Interval();
                    kHHealthHeartRateSecond_Interval.setMinute(heartRateSecond.hrInterval.get(i3).minute);
                    kHHealthHeartRateSecond_Interval.setThreshold(heartRateSecond.hrInterval.get(i3).threshold);
                    arrayList3.add(kHHealthHeartRateSecond_Interval);
                }
                entityHealthHeartRateSecond.setHrInterval(arrayList3);
            }
            entityHealthHeartRateSecond.setTimestamp(IDOUtils.INSTANCE.getCalendarMillisFromDate(heartRateSecond.year, heartRateSecond.month, heartRateSecond.day, 0, 0, 0));
            return entityHealthHeartRateSecond;
        }
        return null;
    }

    @NotNull
    public final Context getContext() {
        return this.f3356a;
    }
}
