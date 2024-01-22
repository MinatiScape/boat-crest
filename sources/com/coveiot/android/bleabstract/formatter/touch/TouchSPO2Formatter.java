package com.coveiot.android.bleabstract.formatter.touch;

import android.content.Context;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ManualSpo2Reading;
import com.coveiot.android.bleabstract.response.PeriodicSpo2Response;
import com.coveiot.android.bleabstract.response.ReadManualSpo2Response;
import com.coveiot.android.bleabstract.response.Spo2DayData;
import com.coveiot.android.bleabstract.response.Spo2HourData;
import com.coveiot.android.bleabstract.response.Spo2TimeLogBeanData;
import com.coveiot.khtouchdb.spo2.EntityTGSpO2Data;
import com.coveiot.khtouchdb.spo2.model.KHTGSpO2DataItemBean;
import com.coveiot.sdk.ble.model.ActivityType;
import com.touchgui.sdk.bean.TGSyncSpo2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class TouchSPO2Formatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3380a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<TouchSPO2Formatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.touch.TouchSPO2Formatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, TouchSPO2Formatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3381a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, TouchSPO2Formatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public TouchSPO2Formatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new TouchSPO2Formatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3381a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TouchSPO2Formatter(Context context) {
        this.f3380a = context;
    }

    public /* synthetic */ TouchSPO2Formatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final List<Spo2HourData> a(List<EntityTGSpO2Data> list) {
        Integer avgValueFromList;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (EntityTGSpO2Data entityTGSpO2Data : list) {
            List<KHTGSpO2DataItemBean> items = entityTGSpO2Data.getItems();
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
            Intrinsics.checkNotNullExpressionValue(obj, "spo2Items[i]");
            KHTGSpO2DataItemBean kHTGSpO2DataItemBean = (KHTGSpO2DataItemBean) obj;
            calendar.setTimeInMillis(kHTGSpO2DataItemBean.getTimeSeconds() * 1000);
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
                emptyHourCodedValuesList2.set(calendar.get(12), Integer.valueOf(kHTGSpO2DataItemBean.getValue()));
                i = i3;
                emptyHourCodedValuesList = emptyHourCodedValuesList2;
            } else {
                emptyHourCodedValuesList.set(calendar.get(12), Integer.valueOf(kHTGSpO2DataItemBean.getValue()));
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
            Spo2HourData spo2HourData = new Spo2HourData();
            BleApiUtils bleApiUtils = BleApiUtils.INSTANCE;
            spo2HourData.setDate(bleApiUtils.getDateFromTimeStamp(list.get(0).getTimeStamp()));
            String format = String.format("%02d:00:00", Arrays.copyOf(new Object[]{Integer.valueOf(intValue)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            spo2HourData.setStartHour(format);
            Object[] objArr = new Object[1];
            int i4 = intValue + 1;
            if (i4 >= 24) {
                i4 = 0;
            }
            objArr[0] = Integer.valueOf(i4);
            String format2 = String.format("%02d:00:00", Arrays.copyOf(objArr, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
            spo2HourData.setEndHour(format2);
            spo2HourData.setMacAddress(list.get(0).getMacAddress());
            spo2HourData.mMinuteWiseData = (ArrayList) hashMap.get(Integer.valueOf(intValue));
            Integer maxValueFromList = bleApiUtils.getMaxValueFromList((List) hashMap.get(Integer.valueOf(intValue)));
            Intrinsics.checkNotNull(maxValueFromList);
            spo2HourData.maxSpo2PerHour = maxValueFromList.intValue();
            Integer minValueFromList = bleApiUtils.getMinValueFromList((List) hashMap.get(Integer.valueOf(intValue)));
            Intrinsics.checkNotNull(minValueFromList);
            spo2HourData.minSpo2PerHour = minValueFromList.intValue();
            Intrinsics.checkNotNull(bleApiUtils.getAvgValueFromList((List) hashMap.get(Integer.valueOf(intValue))));
            spo2HourData.avgSpo2PerHour = avgValueFromList.intValue();
            arrayList.add(spo2HourData);
        }
        return arrayList;
    }

    @Nullable
    public final EntityTGSpO2Data convertTGSPO2ToEntity(@NotNull TGSyncSpo2 tgSyncSpo2) {
        Intrinsics.checkNotNullParameter(tgSyncSpo2, "tgSyncSpo2");
        if (tgSyncSpo2.getItems() != null) {
            Date date = tgSyncSpo2.getDate();
            Intrinsics.checkNotNull(date);
            String date2 = date.toString();
            Intrinsics.checkNotNullExpressionValue(date2, "tgSyncSpo2.date!!.toString()");
            EntityTGSpO2Data entityTGSpO2Data = new EntityTGSpO2Data(date2, BleApiManager.getInstance(this.f3380a).getBleApi().getMacAddress());
            entityTGSpO2Data.setHaveMoreData(tgSyncSpo2.isHaveMoreData());
            entityTGSpO2Data.setTimeStamp(tgSyncSpo2.getDate().getTime());
            ArrayList arrayList = new ArrayList();
            List<TGSyncSpo2.ItemBean> items = tgSyncSpo2.getItems();
            Intrinsics.checkNotNull(items);
            int size = items.size();
            for (int i = 0; i < size; i++) {
                KHTGSpO2DataItemBean kHTGSpO2DataItemBean = new KHTGSpO2DataItemBean();
                List<TGSyncSpo2.ItemBean> items2 = tgSyncSpo2.getItems();
                Intrinsics.checkNotNull(items2);
                kHTGSpO2DataItemBean.setUtcSeconds(items2.get(i).getUtcSeconds());
                List<TGSyncSpo2.ItemBean> items3 = tgSyncSpo2.getItems();
                Intrinsics.checkNotNull(items3);
                kHTGSpO2DataItemBean.setTimeSeconds(items3.get(i).getTimeSeconds());
                List<TGSyncSpo2.ItemBean> items4 = tgSyncSpo2.getItems();
                Intrinsics.checkNotNull(items4);
                kHTGSpO2DataItemBean.setValue(items4.get(i).getValue());
                List<TGSyncSpo2.ItemBean> items5 = tgSyncSpo2.getItems();
                Intrinsics.checkNotNull(items5);
                kHTGSpO2DataItemBean.setHeartRate(items5.get(i).getHeartRate());
                arrayList.add(kHTGSpO2DataItemBean);
            }
            entityTGSpO2Data.setItems(arrayList);
            return entityTGSpO2Data;
        }
        return null;
    }

    @NotNull
    public final PeriodicSpo2Response convertToPeriodicSpo2Response(@NotNull List<EntityTGSpO2Data> spo2DataList) {
        List<KHTGSpO2DataItemBean> items;
        List<KHTGSpO2DataItemBean> items2;
        List<KHTGSpO2DataItemBean> items3;
        Intrinsics.checkNotNullParameter(spo2DataList, "spo2DataList");
        PeriodicSpo2Response periodicSpo2Response = new PeriodicSpo2Response();
        Spo2DayData spo2DayData = new Spo2DayData();
        spo2DayData.mDate = BleApiUtils.INSTANCE.getDateFromTimeStamp(spo2DataList.get(0).getTimeStamp());
        spo2DayData.mActivityType = ActivityType.PERIODIC_SPO2.toString();
        spo2DayData.setmMacAddress(spo2DataList.get(0).getMacAddress());
        int i = -1;
        for (EntityTGSpO2Data entityTGSpO2Data : spo2DataList) {
            if (entityTGSpO2Data.getItems() != null) {
                Intrinsics.checkNotNull(entityTGSpO2Data.getItems());
                if (!items3.isEmpty()) {
                    List<KHTGSpO2DataItemBean> items4 = entityTGSpO2Data.getItems();
                    Intrinsics.checkNotNull(items4);
                    for (KHTGSpO2DataItemBean kHTGSpO2DataItemBean : items4) {
                        if (i == -1 && kHTGSpO2DataItemBean.getValue() != 0) {
                            i = kHTGSpO2DataItemBean.getValue();
                        } else if (kHTGSpO2DataItemBean.getValue() != 0 && i > kHTGSpO2DataItemBean.getValue()) {
                            i = kHTGSpO2DataItemBean.getValue();
                        }
                    }
                }
            }
        }
        spo2DayData.setMinSpo2(i);
        int i2 = -1;
        for (EntityTGSpO2Data entityTGSpO2Data2 : spo2DataList) {
            if (entityTGSpO2Data2.getItems() != null) {
                Intrinsics.checkNotNull(entityTGSpO2Data2.getItems());
                if (!items2.isEmpty()) {
                    List<KHTGSpO2DataItemBean> items5 = entityTGSpO2Data2.getItems();
                    Intrinsics.checkNotNull(items5);
                    for (KHTGSpO2DataItemBean kHTGSpO2DataItemBean2 : items5) {
                        if (i2 == -1) {
                            i2 = kHTGSpO2DataItemBean2.getValue();
                        } else if (i2 < kHTGSpO2DataItemBean2.getValue()) {
                            i2 = kHTGSpO2DataItemBean2.getValue();
                        }
                    }
                }
            }
        }
        spo2DayData.setMaxSPo2(i2);
        int i3 = 0;
        int i4 = 0;
        for (EntityTGSpO2Data entityTGSpO2Data3 : spo2DataList) {
            if (entityTGSpO2Data3.getItems() != null) {
                Intrinsics.checkNotNull(entityTGSpO2Data3.getItems());
                if (!items.isEmpty()) {
                    List<KHTGSpO2DataItemBean> items6 = entityTGSpO2Data3.getItems();
                    Intrinsics.checkNotNull(items6);
                    for (KHTGSpO2DataItemBean kHTGSpO2DataItemBean3 : items6) {
                        if (kHTGSpO2DataItemBean3.getValue() != 0) {
                            i4 += kHTGSpO2DataItemBean3.getValue();
                            i3++;
                        }
                    }
                }
            }
        }
        spo2DayData.setAvgSpo2(i3 > 0 ? i4 / i3 : 0);
        Spo2TimeLogBeanData spo2TimeLogBeanData = new Spo2TimeLogBeanData();
        spo2TimeLogBeanData.setLogBeans(a(spo2DataList));
        spo2DayData.timeLogBean = spo2TimeLogBeanData;
        periodicSpo2Response.setSpo2DayData(spo2DayData);
        return periodicSpo2Response;
    }

    @NotNull
    public final ReadManualSpo2Response convertToReadManualSpo2Response(@NotNull List<EntityTGSpO2Data> spO2List) {
        KHTGSpO2DataItemBean kHTGSpO2DataItemBean;
        Intrinsics.checkNotNullParameter(spO2List, "spO2List");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : spO2List) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            List<KHTGSpO2DataItemBean> items = ((EntityTGSpO2Data) obj).getItems();
            if (items != null) {
                int i3 = 0;
                for (Object obj2 : items) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    ManualSpo2Reading manualSpo2Reading = new ManualSpo2Reading();
                    manualSpo2Reading.setSpo2(((KHTGSpO2DataItemBean) obj2).getValue());
                    manualSpo2Reading.setTimeStamp(kHTGSpO2DataItemBean.getTimeSeconds() * 1000);
                    arrayList.add(manualSpo2Reading);
                    i3 = i4;
                }
            }
            i = i2;
        }
        ReadManualSpo2Response readManualSpo2Response = new ReadManualSpo2Response(arrayList);
        readManualSpo2Response.setComplete(true);
        return readManualSpo2Response;
    }

    @NotNull
    public final Context getContext() {
        return this.f3380a;
    }
}
