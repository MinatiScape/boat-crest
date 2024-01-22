package com.coveiot.android.dashboard2.util;

import android.content.Context;
import com.coveiot.android.bleabstract.response.HealthData;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.covedb.temperature.entity.HourlyTemperature;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.LatestHealthDataModel;
import com.coveiot.utils.utility.AppUtils;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class TemperatureDataHelper {
    @NotNull
    public static final TemperatureDataHelper INSTANCE = new TemperatureDataHelper();

    @DebugMetadata(c = "com.coveiot.android.dashboard2.util.TemperatureDataHelper$saveLatestTemperatureDataToPreferenceFromWatch$1", f = "TemperatureDataHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Context $context;
        public final /* synthetic */ HealthData $healthData;
        public final /* synthetic */ SuccessResultListener $listener;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(HealthData healthData, Context context, SuccessResultListener successResultListener, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$healthData = healthData;
            this.$context = context;
            this.$listener = successResultListener;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$healthData, this.$context, this.$listener, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$healthData.getValue() > 0) {
                    LatestHealthDataModel latestHealthDataModel = new LatestHealthDataModel();
                    latestHealthDataModel.setTimestamp(this.$healthData.getTimestamp());
                    latestHealthDataModel.setValue(this.$healthData.getValue());
                    latestHealthDataModel.setHealthDataType(this.$healthData.getHealthVitalsType());
                    if (this.$healthData.getTimestamp() > 946665000000L && !Dashboard2Utils.Companion.isFutureDate(new Date(this.$healthData.getTimestamp()))) {
                        UserDataManager.getInstance(this.$context).saveLatestTemperatureValue(latestHealthDataModel);
                        SuccessResultListener successResultListener = this.$listener;
                        if (successResultListener != null) {
                            successResultListener.onSuccess();
                        }
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Pair<Double, Calendar> a(HourlyTemperature hourlyTemperature) {
        int size = 60 / hourlyTemperature.getCodevalue().size();
        int size2 = hourlyTemperature.getCodevalue().size();
        double d = 0.0d;
        int i = 0;
        for (int i2 = 0; i2 < size2; i2++) {
            Double d2 = hourlyTemperature.getCodevalue().get(i2);
            Intrinsics.checkNotNullExpressionValue(d2, "hourlyTemperature.codevalue.get(i)");
            if (d2.doubleValue() > 0.0d) {
                Double d3 = hourlyTemperature.getCodevalue().get(i2);
                Intrinsics.checkNotNullExpressionValue(d3, "hourlyTemperature.codevalue.get(i)");
                d = d3.doubleValue();
                i = i2;
            }
        }
        Date parseDate = AppUtils.parseDate(hourlyTemperature.getmDate() + ' ' + hourlyTemperature.getStartTime(), "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate);
        calendar.add(12, size * i);
        return new Pair<>(Double.valueOf(d), calendar);
    }

    @Nullable
    public final Pair<Double, Calendar> getTemperatureData(@NotNull HourlyTemperature hourlyTemperature) {
        Intrinsics.checkNotNullParameter(hourlyTemperature, "hourlyTemperature");
        try {
            return a(hourlyTemperature);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final double getTemperatureInCelsius(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        String format = decimalFormat.format(((d - 32) * 5) / 9);
        Intrinsics.checkNotNullExpressionValue(format, "df2.format((temperature - 32) * 5 / 9)");
        return Double.parseDouble(format);
    }

    public final double getTemperatureInFahrenheit(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        String format = decimalFormat.format((d * 1.8d) + 32);
        Intrinsics.checkNotNullExpressionValue(format, "df2.format(temperature * 1.8 + 32)");
        return Double.parseDouble(format);
    }

    @NotNull
    public final String getWithDecimalPointAfterTwoDigit(@NotNull String num) {
        Intrinsics.checkNotNullParameter(num, "num");
        if (num.length() <= 2) {
            return num;
        }
        StringBuilder sb = new StringBuilder();
        String substring = num.substring(0, 2);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        sb.append(substring);
        sb.append('.');
        String substring2 = num.substring(2);
        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
        sb.append(substring2);
        return sb.toString();
    }

    public final void saveLatestTemperatureDataToPreferenceFromWatch(@NotNull Context context, @NotNull HealthData healthData, @Nullable SuccessResultListener successResultListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(healthData, "healthData");
        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(healthData, context, successResultListener, null), 2, null);
    }
}
