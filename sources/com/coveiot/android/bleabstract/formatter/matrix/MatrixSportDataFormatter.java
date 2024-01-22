package com.coveiot.android.bleabstract.formatter.matrix;

import android.content.Context;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ActivityHeartRateSample;
import com.coveiot.android.bleabstract.models.ActivityStepsSample;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse;
import com.coveiot.android.bleabstract.utils.matrixUtils.MatrixUtils;
import com.coveiot.android.khmatrixdb.workout.KhMatrixSportData;
import com.coveiot.android.khmatrixdb.workout.KhMatrixSportItemData;
import com.htsmart.wristband2.bean.data.SportData;
import com.htsmart.wristband2.bean.data.SportItem;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class MatrixSportDataFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3372a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<MatrixSportDataFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.matrix.MatrixSportDataFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, MatrixSportDataFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3373a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, MatrixSportDataFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public MatrixSportDataFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new MatrixSportDataFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3373a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            DeviceType.values();
            int[] iArr = new int[117];
            try {
                DeviceType deviceType = DeviceType.LUNARFIT;
                iArr[74] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                DeviceType deviceType2 = DeviceType.WAVEFORCE2;
                iArr[72] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                DeviceType deviceType3 = DeviceType.WAVEFORCE;
                iArr[61] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                DeviceType deviceType4 = DeviceType.WAVEARMOUR2;
                iArr[73] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                DeviceType deviceType5 = DeviceType.WAVEARMOUR;
                iArr[62] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public MatrixSportDataFormatter(Context context) {
        this.f3372a = context;
    }

    public /* synthetic */ MatrixSportDataFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final List<ActivityModeSummaryResponse> convertSportData(@NotNull List<KhMatrixSportData> khMatrixSportsList) {
        String str;
        int i;
        MatrixSportDataFormatter matrixSportDataFormatter = this;
        Intrinsics.checkNotNullParameter(khMatrixSportsList, "khMatrixSportsList");
        String macAddress = BleApiManager.getInstance(matrixSportDataFormatter.f3372a).getBleApi().getMacAddress();
        ArrayList arrayList = new ArrayList();
        for (KhMatrixSportData khMatrixSportData : khMatrixSportsList) {
            ActivityModeSummaryResponse activityModeSummaryResponse = new ActivityModeSummaryResponse();
            activityModeSummaryResponse.setMacAddress(macAddress);
            activityModeSummaryResponse.setActivityDuration(khMatrixSportData.getDuration());
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(khMatrixSportData.getMTime());
            if (khMatrixSportData.getDuration() != 0) {
                calendar.add(13, khMatrixSportData.getDuration());
            }
            activityModeSummaryResponse.setStartDateTime(Long.valueOf(khMatrixSportData.getMTime()));
            activityModeSummaryResponse.setEndDateTime(Long.valueOf(calendar.getTimeInMillis()));
            MatrixUtils matrixUtils = MatrixUtils.INSTANCE;
            activityModeSummaryResponse.setDate(matrixUtils.convertSDKTimeToDate(khMatrixSportData.getMTime()));
            activityModeSummaryResponse.setTotalSteps(khMatrixSportData.getStep());
            activityModeSummaryResponse.setTotalCalories(khMatrixSportData.getCalories());
            activityModeSummaryResponse.setTotalDistance(khMatrixSportData.getDistance());
            activityModeSummaryResponse.setActivityMode(matrixUtils.getActivityMode(khMatrixSportData.getSportType()));
            activityModeSummaryResponse.setActivitySite(matrixUtils.getActivitySite(khMatrixSportData.getSportType()));
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            List<KhMatrixSportItemData> mSportItemDetails = khMatrixSportData.getMSportItemDetails();
            Intrinsics.checkNotNull(mSportItemDetails);
            int size = mSportItemDetails.size();
            int i2 = 0;
            while (i2 < size) {
                DeviceType deviceType = BleApiManager.getInstance(matrixSportDataFormatter.f3372a).getDeviceType();
                int i3 = deviceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()];
                if (i3 != 1 && i3 != 2 && i3 != 3 && i3 != 4 && i3 != 5) {
                    if (mSportItemDetails.get(i2).getDuration() % 60 == 0) {
                        ActivityHeartRateSample activityHeartRateSample = new ActivityHeartRateSample();
                        Calendar calendar2 = Calendar.getInstance();
                        str = macAddress;
                        calendar2.setTimeInMillis(khMatrixSportData.getMTime());
                        calendar2.add(13, mSportItemDetails.get(i2).getDuration());
                        activityHeartRateSample.setHrTimeStamp(calendar2.getTimeInMillis());
                        activityHeartRateSample.setHrValue(mSportItemDetails.get(i2).getHrValue());
                        arrayList2.add(activityHeartRateSample);
                    } else {
                        str = macAddress;
                    }
                    i = size;
                } else {
                    str = macAddress;
                    ActivityHeartRateSample activityHeartRateSample2 = new ActivityHeartRateSample();
                    Calendar calendar3 = Calendar.getInstance();
                    i = size;
                    calendar3.setTimeInMillis(khMatrixSportData.getMTime());
                    calendar3.add(13, mSportItemDetails.get(i2).getDuration());
                    activityHeartRateSample2.setHrTimeStamp(calendar3.getTimeInMillis());
                    activityHeartRateSample2.setHrValue(mSportItemDetails.get(i2).getHrValue());
                    arrayList2.add(activityHeartRateSample2);
                }
                ActivityStepsSample activityStepsSample = new ActivityStepsSample();
                Calendar calendar4 = Calendar.getInstance();
                calendar4.setTimeInMillis(khMatrixSportData.getMTime());
                calendar4.add(13, mSportItemDetails.get(i2).getDuration());
                activityStepsSample.setStepsTimeStamp(calendar4.getTimeInMillis());
                activityStepsSample.setStepsValue(mSportItemDetails.get(i2).getSteps());
                activityStepsSample.setCalories(mSportItemDetails.get(i2).getCalories());
                activityStepsSample.setDistance(mSportItemDetails.get(i2).getDistance());
                arrayList3.add(activityStepsSample);
                i2++;
                matrixSportDataFormatter = this;
                macAddress = str;
                size = i;
            }
            String str2 = macAddress;
            activityModeSummaryResponse.setHeartRateSampleList(arrayList2);
            activityModeSummaryResponse.setStepsSampleList(arrayList3);
            ArrayList arrayList4 = new ArrayList();
            List<ActivityHeartRateSample> heartRateSampleList = activityModeSummaryResponse.getHeartRateSampleList();
            Intrinsics.checkNotNull(heartRateSampleList);
            int size2 = heartRateSampleList.size();
            for (int i4 = 0; i4 < size2; i4++) {
                List<ActivityHeartRateSample> heartRateSampleList2 = activityModeSummaryResponse.getHeartRateSampleList();
                Intrinsics.checkNotNull(heartRateSampleList2);
                arrayList4.add(Integer.valueOf(heartRateSampleList2.get(i4).getHrValue()));
            }
            if (arrayList4.size() > 0) {
                Integer minValueFromList = BleApiUtils.INSTANCE.getMinValueFromList(arrayList4);
                Intrinsics.checkNotNull(minValueFromList);
                activityModeSummaryResponse.setMinHeartRate(minValueFromList.intValue());
            }
            if (arrayList4.size() > 0) {
                Integer maxValueFromList = BleApiUtils.INSTANCE.getMaxValueFromList(arrayList4);
                Intrinsics.checkNotNull(maxValueFromList);
                activityModeSummaryResponse.setMaxHeartRate(maxValueFromList.intValue());
            }
            if (arrayList4.size() > 0) {
                Integer avgValueFromList = BleApiUtils.INSTANCE.getAvgValueFromList(arrayList4);
                Intrinsics.checkNotNull(avgValueFromList);
                activityModeSummaryResponse.setHeartRate(avgValueFromList.intValue());
            }
            activityModeSummaryResponse.setLowSamplingRate(60);
            activityModeSummaryResponse.setComplete(Intrinsics.areEqual(khMatrixSportData, CollectionsKt___CollectionsKt.last((List<? extends Object>) khMatrixSportsList)));
            arrayList.add(activityModeSummaryResponse);
            matrixSportDataFormatter = this;
            macAddress = str2;
        }
        return arrayList;
    }

    @NotNull
    public final Context getContext() {
        return this.f3372a;
    }

    @NotNull
    public final List<KhMatrixSportData> getMatrixBleSports(@Nullable List<? extends SportData> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            String macAddress = BleApiManager.getInstance(this.f3372a).getBleApi().getMacAddress();
            for (SportData sportData : list) {
                ArrayList arrayList2 = null;
                List<SportItem> items = sportData.getItems();
                if (items != null && items.size() > 0) {
                    arrayList2 = new ArrayList(items.size());
                    for (SportItem sportItem : items) {
                        if (sportItem.getHeartRate() > 0) {
                            KhMatrixSportItemData khMatrixSportItemData = new KhMatrixSportItemData();
                            khMatrixSportItemData.setDuration(sportItem.getDuration());
                            khMatrixSportItemData.setHrValue(sportItem.getHeartRate());
                            khMatrixSportItemData.setSteps(sportItem.getSteps());
                            khMatrixSportItemData.setCalories(sportItem.getCalories());
                            khMatrixSportItemData.setDistance(sportItem.getDistance());
                            arrayList2.add(khMatrixSportItemData);
                        }
                    }
                }
                ArrayList arrayList3 = arrayList2;
                String uuid = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
                KhMatrixSportData khMatrixSportData = new KhMatrixSportData(uuid, macAddress, sportData.getTimeStamp(), sportData.getSportType(), sportData.getSteps(), sportData.getDuration(), sportData.getDistance(), sportData.getCalories(), null, 256, null);
                khMatrixSportData.setMSportItemDetails(arrayList3);
                arrayList.add(khMatrixSportData);
            }
        }
        return arrayList;
    }
}
