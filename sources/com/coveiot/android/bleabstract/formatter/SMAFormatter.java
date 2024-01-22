package com.coveiot.android.bleabstract.formatter;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.models.ManualBpReading;
import com.coveiot.android.bleabstract.models.ManualSpo2Reading;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.ReadManualBpResponse;
import com.coveiot.android.bleabstract.response.ReadManualSpo2Response;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.response.StressResponse;
import com.coveiot.android.bleabstract.response.TemperatureResponse;
import com.coveiot.android.bleabstract.utils.smaUtils.SmaUtils;
import com.coveiot.khsmadb.activity.KhBleActivity;
import com.coveiot.khsmadb.bp.KhBleBloodPressure;
import com.coveiot.khsmadb.heartrate.KhBleHeartRate;
import com.coveiot.khsmadb.sleep.KhSmaSleepDayData;
import com.coveiot.khsmadb.spo2.KhBleSpO2;
import com.coveiot.khsmadb.stress.KhBlePressure;
import com.coveiot.khsmadb.temperature.KhBleTemperature;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SMAFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3321a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<SMAFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.SMAFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, SMAFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3322a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, SMAFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public SMAFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SMAFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3322a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SMAFormatter(Context context) {
        this.f3321a = context;
    }

    public /* synthetic */ SMAFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final ReadManualBpResponse convertBPData(@NotNull List<KhBleBloodPressure> bloodPressures) {
        Intrinsics.checkNotNullParameter(bloodPressures, "bloodPressures");
        ArrayList arrayList = new ArrayList();
        for (KhBleBloodPressure khBleBloodPressure : bloodPressures) {
            arrayList.add(new ManualBpReading(SmaUtils.INSTANCE.convertSDKTimeToCalender(khBleBloodPressure.getMTime()).getTimeInMillis(), khBleBloodPressure.getMSystolic(), khBleBloodPressure.getMDiastolic(), 0));
        }
        return new ReadManualBpResponse(arrayList);
    }

    @NotNull
    public final ArrayList<HeartRateResponse> convertHrData(@NotNull List<KhBleHeartRate> hearRateDataList, int i) {
        Intrinsics.checkNotNullParameter(hearRateDataList, "hearRateDataList");
        return SMAHRFormatter.Companion.getInstance(this.f3321a).convertData(hearRateDataList, i);
    }

    @NotNull
    public final ArrayList<SleepResponse> convertSleepData(@NotNull List<KhSmaSleepDayData> khSmaSleepData, int i) {
        Intrinsics.checkNotNullParameter(khSmaSleepData, "khSmaSleepData");
        return SMASleepFormatter.Companion.getInstance(this.f3321a).convertSleepData(khSmaSleepData, i);
    }

    @NotNull
    public final ReadManualSpo2Response convertSpO2Data(@NotNull List<KhBleSpO2> spO2List) {
        Intrinsics.checkNotNullParameter(spO2List, "spO2List");
        ArrayList arrayList = new ArrayList();
        for (KhBleSpO2 khBleSpO2 : spO2List) {
            ManualSpo2Reading manualSpo2Reading = new ManualSpo2Reading();
            manualSpo2Reading.setSpo2(khBleSpO2.getMValue());
            manualSpo2Reading.setTimeStamp(SmaUtils.INSTANCE.convertSDKTimeToCalender(khBleSpO2.getMTime()).getTimeInMillis());
            arrayList.add(manualSpo2Reading);
        }
        ReadManualSpo2Response readManualSpo2Response = new ReadManualSpo2Response(arrayList);
        readManualSpo2Response.setComplete(true);
        return readManualSpo2Response;
    }

    @NotNull
    public final ArrayList<StepsResponse> convertStepData(@NotNull List<KhBleActivity> bleActivityData) {
        Intrinsics.checkNotNullParameter(bleActivityData, "bleActivityData");
        return SMAActivityFormatter.Companion.getInstance(this.f3321a).convertStepData(bleActivityData);
    }

    @NotNull
    public final ArrayList<StressResponse> convertStressData(@NotNull List<KhBlePressure> stressList, int i) {
        Intrinsics.checkNotNullParameter(stressList, "stressList");
        return SMAStressFormatter.Companion.getInstance(this.f3321a).convertData(stressList, i);
    }

    @NotNull
    public final ArrayList<TemperatureResponse> convertTemperatureData(@NotNull List<KhBleTemperature> temperatureList, int i) {
        Intrinsics.checkNotNullParameter(temperatureList, "temperatureList");
        return SMATemperatureFormatter.Companion.getInstance(this.f3321a).convertData(temperatureList, i);
    }

    @NotNull
    public final Context getContext() {
        return this.f3321a;
    }
}
