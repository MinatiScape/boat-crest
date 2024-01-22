package com.coveiot.android.bleabstract.formatter.ido;

import android.content.Context;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.response.StressDayData;
import com.coveiot.android.bleabstract.response.StressHourData;
import com.coveiot.android.bleabstract.response.StressResponse;
import com.coveiot.android.bleabstract.response.StressTimeLogBeanData;
import com.coveiot.android.bleabstract.utils.idoUtils.IDOUtils;
import com.coveiot.khidodb.stress.EntityHealthPressure;
import com.coveiot.khidodb.stress.model.KHHealthPressureItem;
import com.coveiot.sdk.ble.model.ActivityType;
import com.ido.ble.data.manage.database.HealthPressure;
import com.ido.ble.data.manage.database.HealthPressureItem;
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
public final class IDOStressFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3364a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<IDOStressFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.ido.IDOStressFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, IDOStressFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3365a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, IDOStressFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public IDOStressFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new IDOStressFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3365a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public IDOStressFormatter(Context context) {
        this.f3364a = context;
    }

    public /* synthetic */ IDOStressFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final List<StressHourData> a(List<EntityHealthPressure> list) {
        Integer avgValueFromList;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (EntityHealthPressure entityHealthPressure : list) {
            List<KHHealthPressureItem> items = entityHealthPressure.getItems();
            Intrinsics.checkNotNull(items);
            arrayList2.addAll(items);
        }
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        HashMap hashMap = new HashMap();
        ArrayList<Integer> emptyHourCodedValuesList = IDOUtils.INSTANCE.getEmptyHourCodedValuesList();
        int size = arrayList2.size();
        int i = -1;
        int i2 = 0;
        int i3 = -1;
        while (i2 < size) {
            Object obj = arrayList2.get(i2);
            Intrinsics.checkNotNullExpressionValue(obj, "stressItems[i]");
            KHHealthPressureItem kHHealthPressureItem = (KHHealthPressureItem) obj;
            calendar.add(12, kHHealthPressureItem.getOffset());
            if (i3 == i) {
                i3 = calendar.get(11);
            }
            if (i3 != calendar.get(11) && !hashMap.containsKey(Integer.valueOf(calendar.get(11)))) {
                Integer valueOf = Integer.valueOf(i3);
                Object clone = emptyHourCodedValuesList.clone();
                Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Int>");
                hashMap.put(valueOf, (ArrayList) clone);
                int i4 = calendar.get(11);
                ArrayList<Integer> emptyHourCodedValuesList2 = IDOUtils.INSTANCE.getEmptyHourCodedValuesList();
                emptyHourCodedValuesList2.set(calendar.get(12), Integer.valueOf(kHHealthPressureItem.getValue()));
                i3 = i4;
                emptyHourCodedValuesList = emptyHourCodedValuesList2;
            } else {
                emptyHourCodedValuesList.set(calendar.get(12), Integer.valueOf(kHHealthPressureItem.getValue()));
            }
            if (i2 == arrayList2.size() - 1 && !Intrinsics.areEqual(emptyHourCodedValuesList, IDOUtils.INSTANCE.getEmptyHourCodedValuesList())) {
                Integer valueOf2 = Integer.valueOf(calendar.get(11));
                Object clone2 = emptyHourCodedValuesList.clone();
                Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Int>");
                hashMap.put(valueOf2, (ArrayList) clone2);
            }
            i2++;
            i = -1;
        }
        Set keySet = hashMap.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "codedValueHashMap.keys");
        for (Number number : CollectionsKt___CollectionsKt.sorted(CollectionsKt___CollectionsKt.toList(keySet))) {
            int intValue = number.intValue();
            StressHourData stressHourData = new StressHourData();
            stressHourData.setDate(IDOUtils.INSTANCE.convertDateFormat(list.get(0).getYear(), list.get(0).getMonth(), list.get(0).getDay()));
            String format = String.format("%02d:00:00", Arrays.copyOf(new Object[]{Integer.valueOf(intValue)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            stressHourData.setStartHour(format);
            Object[] objArr = new Object[1];
            int i5 = intValue + 1;
            if (i5 >= 24) {
                i5 = 0;
            }
            objArr[0] = Integer.valueOf(i5);
            String format2 = String.format("%02d:00:00", Arrays.copyOf(objArr, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
            stressHourData.setEndHour(format2);
            stressHourData.setMacAddress(list.get(0).getMacAddress());
            stressHourData.mMinuteWiseData = (ArrayList) hashMap.get(Integer.valueOf(intValue));
            BleApiUtils bleApiUtils = BleApiUtils.INSTANCE;
            Integer maxValueFromList = bleApiUtils.getMaxValueFromList((List) hashMap.get(Integer.valueOf(intValue)));
            Intrinsics.checkNotNull(maxValueFromList);
            stressHourData.setMaxStress(maxValueFromList.intValue());
            Integer minValueFromList = bleApiUtils.getMinValueFromList((List) hashMap.get(Integer.valueOf(intValue)));
            Intrinsics.checkNotNull(minValueFromList);
            stressHourData.setMinStress(minValueFromList.intValue());
            Intrinsics.checkNotNull(bleApiUtils.getAvgValueFromList((List) hashMap.get(Integer.valueOf(intValue))));
            stressHourData.setAvgStress(avgValueFromList.intValue());
            arrayList.add(stressHourData);
        }
        return arrayList;
    }

    @NotNull
    public final StressResponse convertEntityPressureToStressResponse(@NotNull List<EntityHealthPressure> stressDataList) {
        List<KHHealthPressureItem> items;
        List<KHHealthPressureItem> items2;
        List<KHHealthPressureItem> items3;
        Intrinsics.checkNotNullParameter(stressDataList, "stressDataList");
        StressResponse stressResponse = new StressResponse();
        StressDayData stressDayData = new StressDayData();
        IDOUtils iDOUtils = IDOUtils.INSTANCE;
        stressDayData.mDate = iDOUtils.convertDateFormat(stressDataList.get(0).getYear(), stressDataList.get(0).getMonth(), stressDataList.get(0).getDay());
        stressDayData.mActivityType = ActivityType.STRESS.toString();
        stressDayData.setmMacAddress(iDOUtils.getMacAddress(this.f3364a));
        int i = -1;
        for (EntityHealthPressure entityHealthPressure : stressDataList) {
            if (entityHealthPressure.getItems() != null) {
                Intrinsics.checkNotNull(entityHealthPressure.getItems());
                if (!items3.isEmpty()) {
                    List<KHHealthPressureItem> items4 = entityHealthPressure.getItems();
                    Intrinsics.checkNotNull(items4);
                    for (KHHealthPressureItem kHHealthPressureItem : items4) {
                        if (i == -1 && kHHealthPressureItem.getValue() != 0) {
                            i = kHHealthPressureItem.getValue();
                        } else if (kHHealthPressureItem.getValue() != 0 && i > kHHealthPressureItem.getValue()) {
                            i = kHHealthPressureItem.getValue();
                        }
                    }
                }
            }
        }
        stressDayData.setMinStress(i);
        int i2 = -1;
        for (EntityHealthPressure entityHealthPressure2 : stressDataList) {
            if (entityHealthPressure2.getItems() != null) {
                Intrinsics.checkNotNull(entityHealthPressure2.getItems());
                if (!items2.isEmpty()) {
                    List<KHHealthPressureItem> items5 = entityHealthPressure2.getItems();
                    Intrinsics.checkNotNull(items5);
                    for (KHHealthPressureItem kHHealthPressureItem2 : items5) {
                        if (i2 == -1) {
                            i2 = kHHealthPressureItem2.getValue();
                        } else if (i2 < kHHealthPressureItem2.getValue()) {
                            i2 = kHHealthPressureItem2.getValue();
                        }
                    }
                }
            }
        }
        stressDayData.setMaxStress(i2);
        int i3 = 0;
        int i4 = 0;
        for (EntityHealthPressure entityHealthPressure3 : stressDataList) {
            if (entityHealthPressure3.getItems() != null) {
                Intrinsics.checkNotNull(entityHealthPressure3.getItems());
                if (!items.isEmpty()) {
                    List<KHHealthPressureItem> items6 = entityHealthPressure3.getItems();
                    Intrinsics.checkNotNull(items6);
                    for (KHHealthPressureItem kHHealthPressureItem3 : items6) {
                        if (kHHealthPressureItem3.getValue() != 0) {
                            i4 += kHHealthPressureItem3.getValue();
                            i3++;
                        }
                    }
                }
            }
        }
        stressDayData.setAvgStress(i3 > 0 ? i4 / i3 : 0);
        StressTimeLogBeanData stressTimeLogBeanData = new StressTimeLogBeanData();
        stressTimeLogBeanData.setLogBeans(a(stressDataList));
        stressDayData.timeLogBean = stressTimeLogBeanData;
        stressResponse.setStressDayData(stressDayData);
        return stressResponse;
    }

    @Nullable
    public final EntityHealthPressure convertHealthPressureToEntity(@NotNull HealthPressure healthPressure, @NotNull List<? extends HealthPressureItem> healthPressureItemList) {
        Intrinsics.checkNotNullParameter(healthPressure, "healthPressure");
        Intrinsics.checkNotNullParameter(healthPressureItemList, "healthPressureItemList");
        if (!healthPressureItemList.isEmpty()) {
            EntityHealthPressure entityHealthPressure = new EntityHealthPressure(healthPressure.day, healthPressure.month, healthPressure.year, healthPressure.startTime, IDOUtils.INSTANCE.getMacAddress(this.f3364a));
            ArrayList arrayList = new ArrayList();
            int size = healthPressureItemList.size();
            for (int i = 0; i < size; i++) {
                KHHealthPressureItem kHHealthPressureItem = new KHHealthPressureItem();
                kHHealthPressureItem.setOffset(healthPressureItemList.get(i).offset);
                kHHealthPressureItem.setValue(healthPressureItemList.get(i).value);
                arrayList.add(kHHealthPressureItem);
            }
            entityHealthPressure.setItems(arrayList);
            entityHealthPressure.setTimestamp(IDOUtils.INSTANCE.getCalendarMillisFromDate(healthPressure.year, healthPressure.month, healthPressure.day, 0, 0, 0));
            entityHealthPressure.setDate(healthPressure.date);
            return entityHealthPressure;
        }
        return null;
    }

    @NotNull
    public final Context getContext() {
        return this.f3364a;
    }
}
