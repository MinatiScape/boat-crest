package com.coveiot.android.bleabstract.formatter.touch;

import android.content.Context;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.HeartRateDayData;
import com.coveiot.android.bleabstract.response.HeartRateHourData;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.HeartRateTimeLogBeanData;
import com.coveiot.khtouchdb.heartrate.EntityTGHeartRateData;
import com.coveiot.khtouchdb.heartrate.model.KHTGHRDataItemBean;
import com.touchgui.sdk.bean.TGHeartRateData;
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
public final class TouchHeartRateFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3378a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<TouchHeartRateFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.touch.TouchHeartRateFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, TouchHeartRateFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3379a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, TouchHeartRateFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public TouchHeartRateFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new TouchHeartRateFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3379a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TouchHeartRateFormatter(Context context) {
        this.f3378a = context;
    }

    public /* synthetic */ TouchHeartRateFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final List<HeartRateHourData> a(List<EntityTGHeartRateData> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (EntityTGHeartRateData entityTGHeartRateData : list) {
            List<KHTGHRDataItemBean> items = entityTGHeartRateData.getItems();
            Intrinsics.checkNotNull(items);
            arrayList2.addAll(items);
        }
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.add(12, list.get(0).getMinuteOffset());
        HashMap hashMap = new HashMap();
        ArrayList<Integer> emptyHourCodedValuesList = BleApiUtils.INSTANCE.getEmptyHourCodedValuesList();
        int size = arrayList2.size();
        int i = -1;
        int i2 = 0;
        int i3 = -1;
        while (i2 < size) {
            Object obj = arrayList2.get(i2);
            Intrinsics.checkNotNullExpressionValue(obj, "heartRateItems[i]");
            KHTGHRDataItemBean kHTGHRDataItemBean = (KHTGHRDataItemBean) obj;
            calendar.add(12, kHTGHRDataItemBean.getOffset());
            if (i3 == i) {
                i3 = calendar.get(11);
            }
            if (i3 != calendar.get(11) && !hashMap.containsKey(Integer.valueOf(calendar.get(11)))) {
                Integer valueOf = Integer.valueOf(i3);
                Object clone = emptyHourCodedValuesList.clone();
                Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Int>");
                hashMap.put(valueOf, (ArrayList) clone);
                int i4 = calendar.get(11);
                ArrayList<Integer> emptyHourCodedValuesList2 = BleApiUtils.INSTANCE.getEmptyHourCodedValuesList();
                emptyHourCodedValuesList2.set(calendar.get(12), Integer.valueOf(kHTGHRDataItemBean.getData()));
                i3 = i4;
                emptyHourCodedValuesList = emptyHourCodedValuesList2;
            } else {
                emptyHourCodedValuesList.set(calendar.get(12), Integer.valueOf(kHTGHRDataItemBean.getData()));
            }
            if (i2 == arrayList2.size() - 1 && !Intrinsics.areEqual(emptyHourCodedValuesList, BleApiUtils.INSTANCE.getEmptyHourCodedValuesList())) {
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
            HeartRateHourData heartRateHourData = new HeartRateHourData();
            BleApiUtils bleApiUtils = BleApiUtils.INSTANCE;
            heartRateHourData.setDate(bleApiUtils.getDateFromTimeStamp(list.get(0).getTimeStamp()));
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

    @Nullable
    public final EntityTGHeartRateData convertTGHeartRateToEntity(@NotNull TGHeartRateData tgHeartRateData) {
        Intrinsics.checkNotNullParameter(tgHeartRateData, "tgHeartRateData");
        if (tgHeartRateData.getItems() != null) {
            EntityTGHeartRateData entityTGHeartRateData = new EntityTGHeartRateData(String.valueOf(tgHeartRateData.getDate()), BleApiManager.getInstance(this.f3378a).getBleApi().getMacAddress());
            entityTGHeartRateData.setMinuteOffset(tgHeartRateData.getMinuteOffset());
            entityTGHeartRateData.setSilentHr(tgHeartRateData.getSilentHr());
            entityTGHeartRateData.setBurnFatThreshold(tgHeartRateData.getBurnFatThreshold());
            entityTGHeartRateData.setBurnFatMinutes(tgHeartRateData.getBurnFatMinutes());
            entityTGHeartRateData.setAerobicThreshold(tgHeartRateData.getAerobicThreshold());
            entityTGHeartRateData.setAerobicMinutes(tgHeartRateData.getAerobicMinutes());
            entityTGHeartRateData.setLimitThreshold(tgHeartRateData.getLimitThreshold());
            entityTGHeartRateData.setLimitMinutes(tgHeartRateData.getLimitMinutes());
            entityTGHeartRateData.setWarmUpThreshold(tgHeartRateData.getWarmUpThreshold());
            entityTGHeartRateData.setWarmUpMinutes(tgHeartRateData.getWarmUpMinutes());
            entityTGHeartRateData.setAnaerobicThreshold(tgHeartRateData.getAnaerobicThreshold());
            entityTGHeartRateData.setAnaerobicMinutes(tgHeartRateData.getAnaerobicMinutes());
            Date date = tgHeartRateData.getDate();
            Intrinsics.checkNotNull(date);
            entityTGHeartRateData.setTimeStamp(date.getTime());
            ArrayList arrayList = new ArrayList();
            List<TGHeartRateData.ItemBean> items = tgHeartRateData.getItems();
            Intrinsics.checkNotNull(items);
            int size = items.size();
            for (int i = 0; i < size; i++) {
                KHTGHRDataItemBean kHTGHRDataItemBean = new KHTGHRDataItemBean();
                List<TGHeartRateData.ItemBean> items2 = tgHeartRateData.getItems();
                Intrinsics.checkNotNull(items2);
                kHTGHRDataItemBean.setOffset(items2.get(i).getOffset());
                List<TGHeartRateData.ItemBean> items3 = tgHeartRateData.getItems();
                Intrinsics.checkNotNull(items3);
                kHTGHRDataItemBean.setData(items3.get(i).getData());
                arrayList.add(kHTGHRDataItemBean);
            }
            entityTGHeartRateData.setItems(arrayList);
            return entityTGHeartRateData;
        }
        return null;
    }

    @NotNull
    public final HeartRateResponse convertToHeartRateResponse(@NotNull List<EntityTGHeartRateData> entityTGHeartRateData) {
        List<KHTGHRDataItemBean> items;
        List<KHTGHRDataItemBean> items2;
        List<KHTGHRDataItemBean> items3;
        Intrinsics.checkNotNullParameter(entityTGHeartRateData, "entityTGHeartRateData");
        HeartRateResponse heartRateResponse = new HeartRateResponse();
        HeartRateDayData heartRateDayData = new HeartRateDayData();
        int i = -1;
        for (EntityTGHeartRateData entityTGHeartRateData2 : entityTGHeartRateData) {
            if (entityTGHeartRateData2.getItems() != null) {
                Intrinsics.checkNotNull(entityTGHeartRateData2.getItems());
                if (!items3.isEmpty()) {
                    List<KHTGHRDataItemBean> items4 = entityTGHeartRateData2.getItems();
                    Intrinsics.checkNotNull(items4);
                    for (KHTGHRDataItemBean kHTGHRDataItemBean : items4) {
                        if (i == -1) {
                            i = kHTGHRDataItemBean.getData();
                        } else if (i < kHTGHRDataItemBean.getData()) {
                            i = kHTGHRDataItemBean.getData();
                        }
                    }
                }
            }
        }
        heartRateDayData.setMaxHeartRate(i);
        int i2 = -1;
        for (EntityTGHeartRateData entityTGHeartRateData3 : entityTGHeartRateData) {
            if (entityTGHeartRateData3.getItems() != null) {
                Intrinsics.checkNotNull(entityTGHeartRateData3.getItems());
                if (!items2.isEmpty()) {
                    List<KHTGHRDataItemBean> items5 = entityTGHeartRateData3.getItems();
                    Intrinsics.checkNotNull(items5);
                    for (KHTGHRDataItemBean kHTGHRDataItemBean2 : items5) {
                        if (i2 == -1 && kHTGHRDataItemBean2.getData() != 0) {
                            i2 = kHTGHRDataItemBean2.getData();
                        } else if (kHTGHRDataItemBean2.getData() != 0 && i2 > kHTGHRDataItemBean2.getData()) {
                            i2 = kHTGHRDataItemBean2.getData();
                        }
                    }
                }
            }
        }
        heartRateDayData.setMinHeartRate(i2);
        int i3 = 0;
        int i4 = 0;
        for (EntityTGHeartRateData entityTGHeartRateData4 : entityTGHeartRateData) {
            if (entityTGHeartRateData4.getItems() != null) {
                Intrinsics.checkNotNull(entityTGHeartRateData4.getItems());
                if (!items.isEmpty()) {
                    List<KHTGHRDataItemBean> items6 = entityTGHeartRateData4.getItems();
                    Intrinsics.checkNotNull(items6);
                    for (KHTGHRDataItemBean kHTGHRDataItemBean3 : items6) {
                        if (kHTGHRDataItemBean3.getData() != 0) {
                            i4 += kHTGHRDataItemBean3.getData();
                            i3++;
                        }
                    }
                }
            }
        }
        heartRateDayData.setAvgHeartRate(i3 > 0 ? i4 / i3 : 0);
        heartRateDayData.mMacAddress = entityTGHeartRateData.get(0).getMacAddress();
        heartRateDayData.mDate = BleApiUtils.INSTANCE.getDateFromTimeStamp(entityTGHeartRateData.get(0).getTimeStamp());
        HeartRateTimeLogBeanData heartRateTimeLogBeanData = new HeartRateTimeLogBeanData();
        heartRateTimeLogBeanData.setLogBeans(a(entityTGHeartRateData));
        heartRateDayData.setTimeLogBean(heartRateTimeLogBeanData);
        heartRateResponse.setHeartRateData(heartRateDayData);
        return heartRateResponse;
    }

    @NotNull
    public final Context getContext() {
        return this.f3378a;
    }
}
