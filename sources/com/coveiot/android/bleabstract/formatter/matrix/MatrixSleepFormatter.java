package com.coveiot.android.bleabstract.formatter.matrix;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.SleepDayData;
import com.coveiot.android.bleabstract.response.SleepHourData;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.SleepTimeLogBeanData;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleUtils;
import com.coveiot.android.bleabstract.utils.matrixUtils.MatrixUtils;
import com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepData;
import com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDayData;
import com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepDetailData;
import com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepItem;
import com.coveiot.android.khmatrixdb.sleep.KhMatrixSleepRepository;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.htsmart.wristband2.bean.data.SleepData;
import com.htsmart.wristband2.bean.data.SleepItemData;
import com.htsmart.wristband2.packet.SleepCalculateHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlin.Triple;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.h;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class MatrixSleepFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3368a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<MatrixSleepFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.matrix.MatrixSleepFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, MatrixSleepFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3369a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, MatrixSleepFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public MatrixSleepFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new MatrixSleepFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3369a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public MatrixSleepFormatter(Context context) {
        this.f3368a = context;
    }

    public /* synthetic */ MatrixSleepFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public static final int a(KhMatrixSleepItem khMatrixSleepItem, KhMatrixSleepItem khMatrixSleepItem2) {
        Date startTime = khMatrixSleepItem.getStartTime();
        Intrinsics.checkNotNull(startTime);
        long time = startTime.getTime();
        Date startTime2 = khMatrixSleepItem2.getStartTime();
        Intrinsics.checkNotNull(startTime2);
        return (int) (time - startTime2.getTime());
    }

    @NotNull
    public final ArrayList<SleepResponse> convertSleepData(@NotNull List<KhMatrixSleepDayData> khmatrixSleepDataList) {
        boolean z;
        Iterator<KhMatrixSleepDayData> it;
        ArrayList<Integer> arrayList;
        boolean z2;
        String str;
        int i;
        int i2;
        int i3;
        Intrinsics.checkNotNullParameter(khmatrixSleepDataList, "khmatrixSleepDataList");
        String macAddress = BleApiManager.getInstance(this.f3368a).getBleApi().getMacAddress();
        ArrayList<SleepResponse> arrayList2 = new ArrayList<>();
        Iterator<KhMatrixSleepDayData> it2 = khmatrixSleepDataList.iterator();
        while (it2.hasNext()) {
            KhMatrixSleepDayData next = it2.next();
            SleepResponse sleepResponse = new SleepResponse();
            SleepDayData sleepDayData = new SleepDayData();
            StringBuilder sb = new StringBuilder();
            sb.append(macAddress);
            sb.append(next.getDate());
            ActivityType activityType = ActivityType.SLEEP;
            sb.append(activityType.name());
            sleepDayData.id = sb.toString();
            sleepDayData.mMacAddress = macAddress;
            sleepDayData.mDate = next.getDate();
            sleepDayData.setmActivityType(activityType.name());
            ArrayList arrayList3 = new ArrayList();
            ArrayList<Integer> emptyDaySleepCodedValuesList = JStyleUtils.getEmptyDaySleepCodedValuesList();
            List<KhMatrixSleepDetailData> analysedSleepData = next.getAnalysedSleepData();
            Intrinsics.checkNotNull(analysedSleepData);
            int size = analysedSleepData.size();
            int i4 = 0;
            while (true) {
                z = true;
                if (i4 >= size) {
                    break;
                }
                MatrixUtils matrixUtils = MatrixUtils.INSTANCE;
                List<KhMatrixSleepDetailData> analysedSleepData2 = next.getAnalysedSleepData();
                Intrinsics.checkNotNull(analysedSleepData2);
                int i5 = i4;
                int a2 = a(matrixUtils.convertSDKTimeToCalender(analysedSleepData2.get(i4).getMStartTime()));
                int size2 = emptyDaySleepCodedValuesList.size() - 1;
                if (a2 <= size2) {
                    int i6 = a2;
                    while (true) {
                        if (i6 >= a2) {
                            MatrixUtils matrixUtils2 = MatrixUtils.INSTANCE;
                            List<KhMatrixSleepDetailData> analysedSleepData3 = next.getAnalysedSleepData();
                            Intrinsics.checkNotNull(analysedSleepData3);
                            str = macAddress;
                            i = i5;
                            i2 = size;
                            i3 = a2;
                            if (i6 == a(matrixUtils2.convertSDKTimeToCalender(analysedSleepData3.get(i).getMEndTime()))) {
                                break;
                            }
                            List<KhMatrixSleepDetailData> analysedSleepData4 = next.getAnalysedSleepData();
                            Intrinsics.checkNotNull(analysedSleepData4);
                            emptyDaySleepCodedValuesList.set(i6, Integer.valueOf(analysedSleepData4.get(i).getStatus()));
                        } else {
                            str = macAddress;
                            i3 = a2;
                            i = i5;
                            i2 = size;
                        }
                        if (i6 != size2) {
                            i6++;
                            size = i2;
                            a2 = i3;
                            i5 = i;
                            macAddress = str;
                        }
                    }
                } else {
                    str = macAddress;
                    i = i5;
                    i2 = size;
                }
                i4 = i + 1;
                size = i2;
                macAddress = str;
            }
            String str2 = macAddress;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(AppUtils.parseDate(next.getDate(), "yyyy-MM-dd"));
            calendar.add(6, -1);
            calendar.set(11, 12);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            ArrayList<Integer> arrayList4 = new ArrayList<>();
            int size3 = emptyDaySleepCodedValuesList.size();
            int i7 = 0;
            while (i7 < size3) {
                arrayList4.add(emptyDaySleepCodedValuesList.get(i7));
                if (i7 <= 0) {
                    it = it2;
                    arrayList = emptyDaySleepCodedValuesList;
                    z2 = z;
                } else if ((i7 + 1) % 60 == 0) {
                    SleepHourData sleepHourData = new SleepHourData();
                    sleepHourData.setDate(AppUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"));
                    String format = String.format("%02d:00:00", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(11))}, 1));
                    it = it2;
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    sleepHourData.setStartHour(format);
                    z2 = true;
                    arrayList = emptyDaySleepCodedValuesList;
                    String format2 = String.format("%02d:00:00", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(11) + 1)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                    sleepHourData.setEndHour(format2);
                    sleepHourData.setActivityType(ActivityType.SLEEP.name());
                    sleepHourData.setMacAddress(BleApiManager.getInstance(this.f3368a).getBleApi().getMacAddress());
                    Object clone = arrayList4.clone();
                    Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Int>");
                    sleepHourData.setMinuteWiseData((ArrayList) clone);
                    sleepHourData.setAwakePerHour(a(arrayList4).getFirst().intValue());
                    sleepHourData.setLightSleepPerHour(a(arrayList4).getSecond().intValue());
                    sleepHourData.setDeepSleepPerHour(a(arrayList4).getThird().intValue());
                    sleepHourData.setTotalSleepPerHour(sleepHourData.getDeepSleepPerHour() + sleepHourData.getLightSleepPerHour());
                    arrayList3.add(sleepHourData);
                    calendar.add(12, 60);
                    arrayList4.clear();
                    i7++;
                    z = z2;
                    emptyDaySleepCodedValuesList = arrayList;
                    it2 = it;
                } else {
                    it = it2;
                    arrayList = emptyDaySleepCodedValuesList;
                    z2 = true;
                }
                i7++;
                z = z2;
                emptyDaySleepCodedValuesList = arrayList;
                it2 = it;
            }
            Iterator<KhMatrixSleepDayData> it3 = it2;
            Iterator it4 = arrayList3.iterator();
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            while (it4.hasNext()) {
                SleepHourData sleepHourData2 = (SleepHourData) it4.next();
                i9 += sleepHourData2.getAwakePerHour();
                i8 += sleepHourData2.getLightSleepPerHour();
                i10 += sleepHourData2.getDeepSleepPerHour();
            }
            sleepDayData.mAwakeTime = i9;
            sleepDayData.mLightSleep = i8;
            sleepDayData.mDeepSleep = i10;
            SleepTimeLogBeanData sleepTimeLogBeanData = new SleepTimeLogBeanData();
            sleepTimeLogBeanData.setLogBeans(arrayList3);
            sleepDayData.timeLogBean = sleepTimeLogBeanData;
            sleepResponse.setSleepDayData(sleepDayData);
            sleepResponse.setComplete(next.equals(CollectionsKt___CollectionsKt.last((List<? extends Object>) khmatrixSleepDataList)));
            if (sleepResponse.isComplete()) {
                LogHelper.d("MatrixBleService", "Sleep Complete");
            }
            arrayList2.add(sleepResponse);
            macAddress = str2;
            it2 = it3;
        }
        return arrayList2;
    }

    @NotNull
    public final Context getContext() {
        return this.f3368a;
    }

    @NotNull
    public final List<KhMatrixSleepData> getMatrixBleSleep(@Nullable List<? extends SleepData> list) {
        ArrayList arrayList;
        int i;
        int i2;
        int i3;
        ArrayList arrayList2 = new ArrayList();
        if (list != null) {
            String macAddress = BleApiManager.getInstance(this.f3368a).getBleApi().getMacAddress();
            for (SleepData sleepData : list) {
                ArrayList arrayList3 = null;
                if (sleepData.getItems() != null) {
                    arrayList3 = new ArrayList(sleepData.getItems().size());
                    for (SleepItemData sleepItemData : sleepData.getItems()) {
                        KhMatrixSleepItem khMatrixSleepItem = new KhMatrixSleepItem();
                        khMatrixSleepItem.setStatus(sleepItemData.getStatus());
                        khMatrixSleepItem.setStartTime(new Date(sleepItemData.getStartTime()));
                        khMatrixSleepItem.setEndTime(new Date(sleepItemData.getEndTime()));
                        arrayList3.add(khMatrixSleepItem);
                    }
                }
                KhMatrixSleepRepository.Companion companion = KhMatrixSleepRepository.Companion;
                KhMatrixSleepData sleepDataByTime = companion.getInstance(this.f3368a).getSleepDataByTime(sleepData.getTimeStamp(), macAddress);
                if (sleepDataByTime != null) {
                    List<KhMatrixSleepItem> asMutableList = TypeIntrinsics.asMutableList(sleepDataByTime.getMSleepDetail());
                    Intrinsics.checkNotNull(asMutableList);
                    if (asMutableList != null) {
                        if (arrayList3 != null) {
                            asMutableList.addAll(arrayList3);
                        }
                        arrayList = asMutableList;
                        if (arrayList == null && (!arrayList.isEmpty())) {
                            h.sortWith(arrayList, new Comparator() { // from class: com.coveiot.android.bleabstract.formatter.matrix.a
                                @Override // java.util.Comparator
                                public final int compare(Object obj, Object obj2) {
                                    return MatrixSleepFormatter.a((KhMatrixSleepItem) obj, (KhMatrixSleepItem) obj2);
                                }
                            });
                            int[] calculateDuration = SleepCalculateHelper.calculateDuration(arrayList);
                            int i4 = calculateDuration[0];
                            int i5 = calculateDuration[1];
                            i = i4;
                            i3 = calculateDuration[2];
                            i2 = i5;
                        } else {
                            i = 0;
                            i2 = 0;
                            i3 = 0;
                        }
                        KhMatrixSleepData khMatrixSleepData = new KhMatrixSleepData(macAddress, sleepData.getTimeStamp(), i, i2, i3, null, 32, null);
                        khMatrixSleepData.setMSleepDetail(arrayList);
                        companion.getInstance(this.f3368a).insertAllSleepDetailData(getMatrixBleSleepDetailData(arrayList));
                        arrayList2.add(khMatrixSleepData);
                        macAddress = macAddress;
                    }
                }
                arrayList = arrayList3;
                if (arrayList == null) {
                }
                i = 0;
                i2 = 0;
                i3 = 0;
                KhMatrixSleepData khMatrixSleepData2 = new KhMatrixSleepData(macAddress, sleepData.getTimeStamp(), i, i2, i3, null, 32, null);
                khMatrixSleepData2.setMSleepDetail(arrayList);
                companion.getInstance(this.f3368a).insertAllSleepDetailData(getMatrixBleSleepDetailData(arrayList));
                arrayList2.add(khMatrixSleepData2);
                macAddress = macAddress;
            }
        }
        return arrayList2;
    }

    @NotNull
    public final List<KhMatrixSleepDetailData> getMatrixBleSleepDetailData(@Nullable List<KhMatrixSleepItem> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            String macAddress = BleApiManager.getInstance(this.f3368a).getBleApi().getMacAddress();
            for (KhMatrixSleepItem khMatrixSleepItem : list) {
                Date startTime = khMatrixSleepItem.getStartTime();
                Intrinsics.checkNotNull(startTime);
                long time = startTime.getTime();
                Date endTime = khMatrixSleepItem.getEndTime();
                Intrinsics.checkNotNull(endTime);
                arrayList.add(new KhMatrixSleepDetailData(macAddress, time, endTime.getTime(), khMatrixSleepItem.getStatus(), null, null, null, 112, null));
            }
        }
        return arrayList;
    }

    public final Triple<Integer, Integer, Integer> a(ArrayList<Integer> arrayList) {
        Iterator<Integer> it = arrayList.iterator();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            Integer next = it.next();
            if (next != null && next.intValue() == 3) {
                i3++;
            }
            if (next != null && next.intValue() == 2) {
                i2++;
            }
            if (next != null && next.intValue() == 1) {
                i++;
            }
        }
        return new Triple<>(Integer.valueOf(i3), Integer.valueOf(i2), Integer.valueOf(i));
    }

    public final int a(Calendar calendar) {
        int i;
        int i2;
        int i3 = calendar.get(11);
        if (i3 > 12) {
            i = i3 - 12;
        } else if (i3 == 12) {
            i2 = 0;
            return i2 + calendar.get(12);
        } else {
            i = i3 + 12;
        }
        i2 = i * 60;
        return i2 + calendar.get(12);
    }
}
