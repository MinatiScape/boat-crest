package com.coveiot.android.bleabstract.formatter.touch;

import android.content.Context;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.SleepDayData;
import com.coveiot.android.bleabstract.response.SleepHourData;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.SleepTimeLogBeanData;
import com.coveiot.khtouchdb.sleep.EntityTGSleepData;
import com.coveiot.khtouchdb.sleep.model.KHTGSleepDataItemBean;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.utils.utility.AppUtils;
import com.touchgui.sdk.bean.TGSleepData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import kotlin.Triple;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class TouchSleepFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3382a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<TouchSleepFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.touch.TouchSleepFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, TouchSleepFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3383a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, TouchSleepFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public TouchSleepFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new TouchSleepFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3383a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TouchSleepFormatter(Context context) {
        this.f3382a = context;
    }

    public /* synthetic */ TouchSleepFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
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

    @NotNull
    public final ArrayList<SleepResponse> convertSleepData(@Nullable List<EntityTGSleepData> list) {
        String str;
        Iterator it;
        int i;
        int i2;
        int i3;
        String macAddress = BleApiManager.getInstance(this.f3382a).getBleApi().getMacAddress();
        ArrayList<SleepResponse> arrayList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        if (list != null) {
            Iterator it2 = list.iterator();
            int i4 = 0;
            int i5 = 0;
            while (it2.hasNext()) {
                Object next = it2.next();
                int i6 = i5 + 1;
                if (i5 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                EntityTGSleepData entityTGSleepData = (EntityTGSleepData) next;
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(entityTGSleepData.getTimeStamp());
                int i7 = 11;
                calendar.set(11, entityTGSleepData.getEndHour());
                calendar.set(12, entityTGSleepData.getEndMinute());
                calendar.set(13, i4);
                calendar.set(14, i4);
                calendar.add(12, -entityTGSleepData.getTotalMinute());
                cal.setTimeInMillis(entityTGSleepData.getTimeStamp());
                SleepResponse sleepResponse = new SleepResponse();
                SleepDayData sleepDayData = new SleepDayData();
                sleepDayData.mMacAddress = macAddress;
                int i8 = i5;
                sleepDayData.mDate = BleApiUtils.INSTANCE.getDateFromTimeStamp(entityTGSleepData.getTimeStamp());
                sleepDayData.setmActivityType(ActivityType.SLEEP.name());
                Intrinsics.checkNotNullExpressionValue(cal, "cal");
                int i9 = calendar.get(11);
                int i10 = calendar.get(12);
                ArrayList arrayList2 = new ArrayList();
                ArrayList<Integer> emptyDaySleepCodedValuesList = BleApiUtils.getEmptyDaySleepCodedValuesList();
                int i11 = (i9 * 60) + i10;
                List<KHTGSleepDataItemBean> items = entityTGSleepData.getItems();
                Intrinsics.checkNotNull(items);
                for (KHTGSleepDataItemBean kHTGSleepDataItemBean : items) {
                    int duration = kHTGSleepDataItemBean.getDuration();
                    int i12 = i11 + duration;
                    int i13 = i11;
                    int i14 = duration;
                    int i15 = i13;
                    while (i11 < i12) {
                        if (i13 > 1439) {
                            i14 = (i15 + i14) - 1440;
                            i13 = 0;
                            i15 = 0;
                        }
                        emptyDaySleepCodedValuesList.set(i13, Integer.valueOf(kHTGSleepDataItemBean.getStatus()));
                        i13++;
                        i11++;
                    }
                    i11 = i15 + i14;
                    i7 = 11;
                }
                cal.set(i7, 0);
                cal.set(12, 0);
                cal.set(13, 0);
                cal.set(14, 0);
                ArrayList<Integer> arrayList3 = new ArrayList<>();
                int size = emptyDaySleepCodedValuesList.size();
                int i16 = 0;
                while (i16 < size) {
                    arrayList3.add(emptyDaySleepCodedValuesList.get(i16));
                    if (i16 <= 0 || (i16 + 1) % 60 != 0) {
                        str = macAddress;
                        it = it2;
                        i = size;
                        i2 = i6;
                    } else {
                        SleepHourData sleepHourData = new SleepHourData();
                        str = macAddress;
                        sleepHourData.setDate(AppUtils.formatDate(cal.getTime(), "yyyy-MM-dd"));
                        int i17 = cal.get(11);
                        if (i17 == 23) {
                            it = it2;
                            i = size;
                            i3 = 0;
                        } else {
                            i3 = i17 + 1;
                            it = it2;
                            i = size;
                        }
                        String format = String.format("%02d:00:00", Arrays.copyOf(new Object[]{Integer.valueOf(i17)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                        sleepHourData.setStartHour(format);
                        i2 = i6;
                        String format2 = String.format("%02d:00:00", Arrays.copyOf(new Object[]{Integer.valueOf(i3)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                        sleepHourData.setEndHour(format2);
                        sleepHourData.setActivityType(ActivityType.SLEEP.name());
                        sleepHourData.setMacAddress(BleApiManager.getInstance(this.f3382a).getBleApi().getMacAddress());
                        Object clone = arrayList3.clone();
                        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Int>");
                        sleepHourData.setMinuteWiseData((ArrayList) clone);
                        sleepHourData.setAwakePerHour(a(arrayList3).getFirst().intValue());
                        sleepHourData.setLightSleepPerHour(a(arrayList3).getSecond().intValue());
                        sleepHourData.setDeepSleepPerHour(a(arrayList3).getThird().intValue());
                        sleepHourData.setTotalSleepPerHour(sleepHourData.getDeepSleepPerHour() + sleepHourData.getLightSleepPerHour());
                        arrayList2.add(sleepHourData);
                        if (cal.get(11) == 11) {
                            cal.add(6, -1);
                            cal.set(11, 11);
                        }
                        cal.add(12, 60);
                        arrayList3.clear();
                    }
                    i16++;
                    macAddress = str;
                    it2 = it;
                    size = i;
                    i6 = i2;
                }
                String str2 = macAddress;
                Iterator it3 = it2;
                int i18 = i6;
                Iterator it4 = arrayList2.iterator();
                int i19 = 0;
                int i20 = 0;
                int i21 = 0;
                while (it4.hasNext()) {
                    SleepHourData sleepHourData2 = (SleepHourData) it4.next();
                    i20 += sleepHourData2.getAwakePerHour();
                    i19 += sleepHourData2.getLightSleepPerHour();
                    i21 += sleepHourData2.getDeepSleepPerHour();
                }
                sleepDayData.mAwakeTime = i20;
                sleepDayData.mLightSleep = i19;
                sleepDayData.mDeepSleep = i21;
                sleepDayData.mTotalSleep = entityTGSleepData.getTotalMinute();
                sleepDayData.mSleepScore = entityTGSleepData.getSleepScore();
                SleepTimeLogBeanData sleepTimeLogBeanData = new SleepTimeLogBeanData();
                sleepTimeLogBeanData.setLogBeans(arrayList2);
                sleepDayData.timeLogBean = sleepTimeLogBeanData;
                sleepResponse.setSleepDayData(sleepDayData);
                sleepResponse.setComplete(i8 == list.size() - 1);
                arrayList.add(sleepResponse);
                macAddress = str2;
                i4 = 0;
                it2 = it3;
                i5 = i18;
            }
        }
        return arrayList;
    }

    @Nullable
    public final EntityTGSleepData convertTGSleepToEntity(@NotNull TGSleepData tgSleepData) {
        Intrinsics.checkNotNullParameter(tgSleepData, "tgSleepData");
        if (tgSleepData.getItems() != null) {
            String date = tgSleepData.getDate().toString();
            Intrinsics.checkNotNullExpressionValue(date, "tgSleepData.date.toString()");
            EntityTGSleepData entityTGSleepData = new EntityTGSleepData(date, tgSleepData.getEndHour(), tgSleepData.getEndMinute(), BleApiManager.getInstance(this.f3382a).getBleApi().getMacAddress());
            entityTGSleepData.setTotalMinute(tgSleepData.getTotalMinute());
            entityTGSleepData.setSleepMinute(tgSleepData.getSleepMinute());
            entityTGSleepData.setItemCount(tgSleepData.getItemCount());
            entityTGSleepData.setPacketCount(tgSleepData.getPacketCount());
            entityTGSleepData.setLightCount(tgSleepData.getLightCount());
            entityTGSleepData.setDeepCount(tgSleepData.getDeepCount());
            entityTGSleepData.setWakeCount(tgSleepData.getWakeCount());
            entityTGSleepData.setEyeMoveCount(tgSleepData.getEyeMoveCount());
            entityTGSleepData.setLightMinute(tgSleepData.getLightMinute());
            entityTGSleepData.setDeepMinute(tgSleepData.getDeepMinute());
            entityTGSleepData.setEyeMoveMinute(tgSleepData.getEyeMoveMinute());
            entityTGSleepData.setSleepScore(tgSleepData.getSleepScore());
            entityTGSleepData.setTimeStamp(tgSleepData.getDate().getTime());
            ArrayList arrayList = new ArrayList();
            List<TGSleepData.ItemBean> items = tgSleepData.getItems();
            Intrinsics.checkNotNull(items);
            int size = items.size();
            for (int i = 0; i < size; i++) {
                KHTGSleepDataItemBean kHTGSleepDataItemBean = new KHTGSleepDataItemBean();
                List<TGSleepData.ItemBean> items2 = tgSleepData.getItems();
                Intrinsics.checkNotNull(items2);
                kHTGSleepDataItemBean.setStatus(items2.get(i).getStatus());
                List<TGSleepData.ItemBean> items3 = tgSleepData.getItems();
                Intrinsics.checkNotNull(items3);
                kHTGSleepDataItemBean.setDuration(items3.get(i).getDuration());
                arrayList.add(kHTGSleepDataItemBean);
            }
            entityTGSleepData.setItems(arrayList);
            return entityTGSleepData;
        }
        return null;
    }

    @NotNull
    public final Context getContext() {
        return this.f3382a;
    }
}
