package com.coveiot.android.bleabstract.formatter.ido;

import android.content.Context;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.response.AmbientSoundDayData;
import com.coveiot.android.bleabstract.response.AmbientSoundHourData;
import com.coveiot.android.bleabstract.response.AmbientSoundResponse;
import com.coveiot.android.bleabstract.response.AmbientSoundTimeLogBeanData;
import com.coveiot.android.bleabstract.utils.idoUtils.IDOUtils;
import com.coveiot.khidodb.noise.EntityHealthNoise;
import com.coveiot.khidodb.noise.model.KHHealthNoiseItem;
import com.coveiot.sdk.ble.model.ActivityType;
import com.ido.ble.data.manage.database.HealthNoise;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class IDOAmbientSoundLevelFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3354a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<IDOAmbientSoundLevelFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.ido.IDOAmbientSoundLevelFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, IDOAmbientSoundLevelFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3355a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, IDOAmbientSoundLevelFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public IDOAmbientSoundLevelFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new IDOAmbientSoundLevelFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3355a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public IDOAmbientSoundLevelFormatter(Context context) {
        this.f3354a = context;
    }

    public /* synthetic */ IDOAmbientSoundLevelFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final Pair<List<AmbientSoundHourData>, Integer> a(List<EntityHealthNoise> list) {
        Integer avgValueFromList;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (EntityHealthNoise entityHealthNoise : list) {
            List<KHHealthNoiseItem> items = entityHealthNoise.getItems();
            Intrinsics.checkNotNull(items);
            arrayList2.addAll(items);
        }
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        calendar.set(11, 0);
        calendar.set(12, 0);
        int i = 13;
        calendar.set(13, 0);
        calendar.add(13, list.get(0).getStartTime());
        HashMap hashMap = new HashMap();
        ArrayList<Integer> emptyHourCodedValuesList = IDOUtils.INSTANCE.getEmptyHourCodedValuesList();
        int size = arrayList2.size();
        int i2 = 0;
        int i3 = -1;
        while (i2 < size) {
            Object obj = arrayList2.get(i2);
            Intrinsics.checkNotNullExpressionValue(obj, "ambientSoundItems[i]");
            KHHealthNoiseItem kHHealthNoiseItem = (KHHealthNoiseItem) obj;
            calendar.add(i, kHHealthNoiseItem.getOffset());
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
                emptyHourCodedValuesList.set(calendar.get(12), Integer.valueOf(kHHealthNoiseItem.getNoiseValue()));
                i3 = i4;
            } else {
                emptyHourCodedValuesList.set(calendar.get(12), Integer.valueOf(kHHealthNoiseItem.getNoiseValue()));
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
        int i5 = 0;
        for (Number number : CollectionsKt___CollectionsKt.sorted(CollectionsKt___CollectionsKt.toList(keySet))) {
            int intValue = number.intValue();
            AmbientSoundHourData ambientSoundHourData = new AmbientSoundHourData();
            ambientSoundHourData.setDate(IDOUtils.INSTANCE.convertDateFormat(list.get(0).getYear(), list.get(0).getMonth(), list.get(0).getDay()));
            String format = String.format("%02d:00:00", Arrays.copyOf(new Object[]{Integer.valueOf(intValue)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            ambientSoundHourData.setStartHour(format);
            Object[] objArr = new Object[1];
            int i6 = intValue + 1;
            if (i6 >= 24) {
                i6 = 0;
            }
            objArr[0] = Integer.valueOf(i6);
            String format2 = String.format("%02d:00:00", Arrays.copyOf(objArr, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
            ambientSoundHourData.setEndHour(format2);
            ambientSoundHourData.setMacAddress(list.get(0).getMacAddress());
            ambientSoundHourData.mMinuteWiseData = (ArrayList) hashMap.get(Integer.valueOf(intValue));
            BleApiUtils bleApiUtils = BleApiUtils.INSTANCE;
            Integer maxValueFromList = bleApiUtils.getMaxValueFromList((List) hashMap.get(Integer.valueOf(intValue)));
            Intrinsics.checkNotNull(maxValueFromList);
            ambientSoundHourData.setMaxAmbientSound(maxValueFromList.intValue());
            Integer minValueFromList = bleApiUtils.getMinValueFromList((List) hashMap.get(Integer.valueOf(intValue)));
            Intrinsics.checkNotNull(minValueFromList);
            ambientSoundHourData.setMinAmbientSound(minValueFromList.intValue());
            Intrinsics.checkNotNull(bleApiUtils.getAvgValueFromList((List) hashMap.get(Integer.valueOf(intValue))));
            ambientSoundHourData.setAvgAmbientSound(avgValueFromList.intValue());
            i5 += bleApiUtils.getNonZeroList((List) hashMap.get(Integer.valueOf(intValue))).size();
            arrayList.add(ambientSoundHourData);
        }
        return new Pair<>(arrayList, Integer.valueOf(i5));
    }

    @Nullable
    public final EntityHealthNoise convertAmbientSoundLevelToEntity(@NotNull HealthNoise healthNoise) {
        Intrinsics.checkNotNullParameter(healthNoise, "healthNoise");
        if (healthNoise.items != null) {
            EntityHealthNoise entityHealthNoise = new EntityHealthNoise(healthNoise.day, healthNoise.month, healthNoise.year, healthNoise.start_time, IDOUtils.INSTANCE.getMacAddress(this.f3354a));
            entityHealthNoise.setHour(healthNoise.hour);
            entityHealthNoise.setMinute(healthNoise.minute);
            entityHealthNoise.setSecond(healthNoise.second);
            entityHealthNoise.setMinNoise(healthNoise.min_noise);
            entityHealthNoise.setMaxNoise(healthNoise.max_noise);
            entityHealthNoise.setAvgNoise(healthNoise.avg_noise);
            entityHealthNoise.setInterval(healthNoise.interval_mode);
            if (healthNoise.items != null) {
                ArrayList arrayList = new ArrayList();
                int size = healthNoise.items.size();
                for (int i = 0; i < size; i++) {
                    KHHealthNoiseItem kHHealthNoiseItem = new KHHealthNoiseItem();
                    kHHealthNoiseItem.setOffset(healthNoise.items.get(i).offset);
                    kHHealthNoiseItem.setNoiseValue(healthNoise.items.get(i).value);
                    arrayList.add(kHHealthNoiseItem);
                }
                entityHealthNoise.setItems(arrayList);
            }
            entityHealthNoise.setTimestamp(IDOUtils.INSTANCE.getCalendarMillisFromDate(healthNoise.year, healthNoise.month, healthNoise.day, 0, 0, 0));
            return entityHealthNoise;
        }
        return null;
    }

    @NotNull
    public final AmbientSoundResponse convertEntityHealthNoiseToAmbientSoundResponse(@NotNull List<EntityHealthNoise> entityHealthNoise) {
        List<KHHealthNoiseItem> items;
        List<KHHealthNoiseItem> items2;
        List<KHHealthNoiseItem> items3;
        Intrinsics.checkNotNullParameter(entityHealthNoise, "entityHealthNoise");
        AmbientSoundResponse ambientSoundResponse = new AmbientSoundResponse();
        AmbientSoundDayData ambientSoundDayData = new AmbientSoundDayData();
        int i = -1;
        for (EntityHealthNoise entityHealthNoise2 : entityHealthNoise) {
            if (entityHealthNoise2.getItems() != null) {
                Intrinsics.checkNotNull(entityHealthNoise2.getItems());
                if (!items3.isEmpty()) {
                    List<KHHealthNoiseItem> items4 = entityHealthNoise2.getItems();
                    Intrinsics.checkNotNull(items4);
                    for (KHHealthNoiseItem kHHealthNoiseItem : items4) {
                        if (i == -1) {
                            i = kHHealthNoiseItem.getNoiseValue();
                        } else if (i < kHHealthNoiseItem.getNoiseValue()) {
                            i = kHHealthNoiseItem.getNoiseValue();
                        }
                    }
                }
            }
        }
        ambientSoundDayData.setMaxAmbientSound(i);
        int i2 = -1;
        for (EntityHealthNoise entityHealthNoise3 : entityHealthNoise) {
            if (entityHealthNoise3.getItems() != null) {
                Intrinsics.checkNotNull(entityHealthNoise3.getItems());
                if (!items2.isEmpty()) {
                    List<KHHealthNoiseItem> items5 = entityHealthNoise3.getItems();
                    Intrinsics.checkNotNull(items5);
                    for (KHHealthNoiseItem kHHealthNoiseItem2 : items5) {
                        if (i2 == -1 && kHHealthNoiseItem2.getNoiseValue() != 0) {
                            i2 = kHHealthNoiseItem2.getNoiseValue();
                        } else if (kHHealthNoiseItem2.getNoiseValue() != 0 && i2 > kHHealthNoiseItem2.getNoiseValue()) {
                            i2 = kHHealthNoiseItem2.getNoiseValue();
                        }
                    }
                }
            }
        }
        ambientSoundDayData.setMinAmbientSound(i2);
        int i3 = 0;
        int i4 = 0;
        for (EntityHealthNoise entityHealthNoise4 : entityHealthNoise) {
            if (entityHealthNoise4.getItems() != null) {
                Intrinsics.checkNotNull(entityHealthNoise4.getItems());
                if (!items.isEmpty()) {
                    List<KHHealthNoiseItem> items6 = entityHealthNoise4.getItems();
                    Intrinsics.checkNotNull(items6);
                    for (KHHealthNoiseItem kHHealthNoiseItem3 : items6) {
                        if (kHHealthNoiseItem3.getNoiseValue() != 0) {
                            i3 += kHHealthNoiseItem3.getNoiseValue();
                            i4++;
                        }
                    }
                }
            }
        }
        ambientSoundDayData.setAvgAmbientSound(i3 / i4);
        ambientSoundDayData.mMacAddress = entityHealthNoise.get(0).getMacAddress();
        ambientSoundDayData.mDate = IDOUtils.INSTANCE.convertDateFormat(entityHealthNoise.get(0).getYear(), entityHealthNoise.get(0).getMonth(), entityHealthNoise.get(0).getDay());
        ArrayList arrayList = new ArrayList();
        for (EntityHealthNoise entityHealthNoise5 : entityHealthNoise) {
            List<KHHealthNoiseItem> items7 = entityHealthNoise5.getItems();
            Intrinsics.checkNotNull(items7);
            arrayList.addAll(items7);
        }
        AmbientSoundTimeLogBeanData ambientSoundTimeLogBeanData = new AmbientSoundTimeLogBeanData();
        Pair<List<AmbientSoundHourData>, Integer> a2 = a(entityHealthNoise);
        ambientSoundTimeLogBeanData.setLogBeans(a2.getFirst());
        ambientSoundDayData.setTotalAmbientSoundTime(a2.getSecond().intValue());
        ambientSoundDayData.setTimeLogBean(ambientSoundTimeLogBeanData);
        ambientSoundDayData.mActivityType = ActivityType.AMBIENT_SOUND.toString();
        ambientSoundResponse.setAmbientSoundDayData(ambientSoundDayData);
        return ambientSoundResponse;
    }

    @NotNull
    public final Context getContext() {
        return this.f3354a;
    }
}
