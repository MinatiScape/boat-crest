package com.coveiot.android.bleabstract.formatter.touch;

import android.content.Context;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.StressDayData;
import com.coveiot.android.bleabstract.response.StressHourData;
import com.coveiot.android.bleabstract.response.StressResponse;
import com.coveiot.android.bleabstract.response.StressTimeLogBeanData;
import com.coveiot.khtouchdb.stress.EntityTGStressData;
import com.coveiot.khtouchdb.stress.model.KHTGStressDataItemBean;
import com.coveiot.sdk.ble.model.ActivityType;
import com.touchgui.sdk.bean.TGStressData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
public final class TouchStressFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3386a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<TouchStressFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.touch.TouchStressFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, TouchStressFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3387a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, TouchStressFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public TouchStressFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new TouchStressFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3387a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TouchStressFormatter(Context context) {
        this.f3386a = context;
    }

    public /* synthetic */ TouchStressFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final List<StressHourData> a(List<EntityTGStressData> list) {
        Integer avgValueFromList;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (EntityTGStressData entityTGStressData : list) {
            List<KHTGStressDataItemBean> items = entityTGStressData.getItems();
            Intrinsics.checkNotNull(items);
            arrayList2.addAll(items);
        }
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        HashMap hashMap = new HashMap();
        ArrayList<Integer> emptyHourCodedValuesList = BleApiUtils.INSTANCE.getEmptyHourCodedValuesList();
        int size = arrayList2.size();
        int i = -1;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = arrayList2.get(i2);
            Intrinsics.checkNotNullExpressionValue(obj, "stressItems[i]");
            KHTGStressDataItemBean kHTGStressDataItemBean = (KHTGStressDataItemBean) obj;
            calendar.setTimeInMillis(kHTGStressDataItemBean.getTimeSeconds() * 1000);
            if (i == -1) {
                i = calendar.get(11);
            }
            if (i != calendar.get(11) && !hashMap.containsKey(Integer.valueOf(calendar.get(11)))) {
                Integer valueOf = Integer.valueOf(i);
                Object clone = emptyHourCodedValuesList.clone();
                Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Int>");
                hashMap.put(valueOf, (ArrayList) clone);
                int i3 = calendar.get(11);
                ArrayList<Integer> emptyHourCodedValuesList2 = BleApiUtils.INSTANCE.getEmptyHourCodedValuesList();
                emptyHourCodedValuesList2.set(calendar.get(12), Integer.valueOf(kHTGStressDataItemBean.getValue()));
                i = i3;
                emptyHourCodedValuesList = emptyHourCodedValuesList2;
            } else {
                emptyHourCodedValuesList.set(calendar.get(12), Integer.valueOf(kHTGStressDataItemBean.getValue()));
            }
            if (i2 == arrayList2.size() - 1 && !Intrinsics.areEqual(emptyHourCodedValuesList, BleApiUtils.INSTANCE.getEmptyHourCodedValuesList())) {
                Integer valueOf2 = Integer.valueOf(calendar.get(11));
                Object clone2 = emptyHourCodedValuesList.clone();
                Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Int>");
                hashMap.put(valueOf2, (ArrayList) clone2);
            }
        }
        Set keySet = hashMap.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "codedValueHashMap.keys");
        for (Number number : CollectionsKt___CollectionsKt.sorted(CollectionsKt___CollectionsKt.toList(keySet))) {
            int intValue = number.intValue();
            StressHourData stressHourData = new StressHourData();
            BleApiUtils bleApiUtils = BleApiUtils.INSTANCE;
            stressHourData.setDate(bleApiUtils.getDateFromTimeStamp(list.get(0).getTimeStamp()));
            String format = String.format("%02d:00:00", Arrays.copyOf(new Object[]{Integer.valueOf(intValue)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            stressHourData.setStartHour(format);
            Object[] objArr = new Object[1];
            int i4 = intValue + 1;
            if (i4 >= 24) {
                i4 = 0;
            }
            objArr[0] = Integer.valueOf(i4);
            String format2 = String.format("%02d:00:00", Arrays.copyOf(objArr, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
            stressHourData.setEndHour(format2);
            stressHourData.setMacAddress(list.get(0).getMacAddress());
            stressHourData.mMinuteWiseData = (ArrayList) hashMap.get(Integer.valueOf(intValue));
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

    @Nullable
    public final EntityTGStressData convertTGStressDataToEntity(@NotNull TGStressData tgStressData) {
        Intrinsics.checkNotNullParameter(tgStressData, "tgStressData");
        if (tgStressData.getItems() == null || tgStressData.getDate() == null) {
            return null;
        }
        Date date = tgStressData.getDate();
        Intrinsics.checkNotNull(date);
        String date2 = date.toString();
        Intrinsics.checkNotNullExpressionValue(date2, "tgStressData.date!!.toString()");
        EntityTGStressData entityTGStressData = new EntityTGStressData(date2, tgStressData.getStartTime(), BleApiManager.getInstance(this.f3386a).getBleApi().getMacAddress());
        entityTGStressData.setHaveMoreData(tgStressData.isHaveMoreData());
        entityTGStressData.setOffset(tgStressData.getOffset());
        entityTGStressData.setTimeStamp(tgStressData.getDate().getTime());
        ArrayList arrayList = new ArrayList();
        List<TGStressData.ItemBean> items = tgStressData.getItems();
        Intrinsics.checkNotNull(items);
        int size = items.size();
        for (int i = 0; i < size; i++) {
            KHTGStressDataItemBean kHTGStressDataItemBean = new KHTGStressDataItemBean();
            List<TGStressData.ItemBean> items2 = tgStressData.getItems();
            Intrinsics.checkNotNull(items2);
            kHTGStressDataItemBean.setUtcSeconds(items2.get(i).getUtcSeconds());
            List<TGStressData.ItemBean> items3 = tgStressData.getItems();
            Intrinsics.checkNotNull(items3);
            kHTGStressDataItemBean.setTimeSeconds(items3.get(i).getTimeSeconds());
            List<TGStressData.ItemBean> items4 = tgStressData.getItems();
            Intrinsics.checkNotNull(items4);
            kHTGStressDataItemBean.setValue(items4.get(i).getValue());
            List<TGStressData.ItemBean> items5 = tgStressData.getItems();
            Intrinsics.checkNotNull(items5);
            kHTGStressDataItemBean.setHeartRate(items5.get(i).getHeartRate());
            arrayList.add(kHTGStressDataItemBean);
        }
        entityTGStressData.setItems(arrayList);
        return entityTGStressData;
    }

    @NotNull
    public final StressResponse convertToStressResponse(@NotNull List<EntityTGStressData> stressDataList) {
        List<KHTGStressDataItemBean> items;
        List<KHTGStressDataItemBean> items2;
        List<KHTGStressDataItemBean> items3;
        Intrinsics.checkNotNullParameter(stressDataList, "stressDataList");
        StressResponse stressResponse = new StressResponse();
        StressDayData stressDayData = new StressDayData();
        stressDayData.mDate = BleApiUtils.INSTANCE.getDateFromTimeStamp(stressDataList.get(0).getTimeStamp());
        stressDayData.mActivityType = ActivityType.STRESS.toString();
        stressDayData.setmMacAddress(stressDataList.get(0).getMacAddress());
        int i = -1;
        for (EntityTGStressData entityTGStressData : stressDataList) {
            if (entityTGStressData.getItems() != null) {
                Intrinsics.checkNotNull(entityTGStressData.getItems());
                if (!items3.isEmpty()) {
                    List<KHTGStressDataItemBean> items4 = entityTGStressData.getItems();
                    Intrinsics.checkNotNull(items4);
                    for (KHTGStressDataItemBean kHTGStressDataItemBean : items4) {
                        if (i == -1 && kHTGStressDataItemBean.getValue() != 0) {
                            i = kHTGStressDataItemBean.getValue();
                        } else if (kHTGStressDataItemBean.getValue() != 0 && i > kHTGStressDataItemBean.getValue()) {
                            i = kHTGStressDataItemBean.getValue();
                        }
                    }
                }
            }
        }
        stressDayData.setMinStress(i);
        int i2 = -1;
        for (EntityTGStressData entityTGStressData2 : stressDataList) {
            if (entityTGStressData2.getItems() != null) {
                Intrinsics.checkNotNull(entityTGStressData2.getItems());
                if (!items2.isEmpty()) {
                    List<KHTGStressDataItemBean> items5 = entityTGStressData2.getItems();
                    Intrinsics.checkNotNull(items5);
                    for (KHTGStressDataItemBean kHTGStressDataItemBean2 : items5) {
                        if (i2 == -1) {
                            i2 = kHTGStressDataItemBean2.getValue();
                        } else if (i2 < kHTGStressDataItemBean2.getValue()) {
                            i2 = kHTGStressDataItemBean2.getValue();
                        }
                    }
                }
            }
        }
        stressDayData.setMaxStress(i2);
        int i3 = 0;
        int i4 = 0;
        for (EntityTGStressData entityTGStressData3 : stressDataList) {
            if (entityTGStressData3.getItems() != null) {
                Intrinsics.checkNotNull(entityTGStressData3.getItems());
                if (!items.isEmpty()) {
                    List<KHTGStressDataItemBean> items6 = entityTGStressData3.getItems();
                    Intrinsics.checkNotNull(items6);
                    for (KHTGStressDataItemBean kHTGStressDataItemBean3 : items6) {
                        if (kHTGStressDataItemBean3.getValue() != 0) {
                            i4 += kHTGStressDataItemBean3.getValue();
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

    @NotNull
    public final Context getContext() {
        return this.f3386a;
    }
}
