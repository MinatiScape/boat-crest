package com.coveiot.android.dashboard2.util;

import android.content.Context;
import com.coveiot.android.bleabstract.response.HealthData;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.LatestHealthDataModel;
import com.coveiot.utils.utility.AppUtils;
import java.util.Calendar;
import java.util.Date;
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
public final class HeartRateDataHelper {
    @NotNull
    public static final HeartRateDataHelper INSTANCE = new HeartRateDataHelper();

    @DebugMetadata(c = "com.coveiot.android.dashboard2.util.HeartRateDataHelper$saveLatestHeartRateDataToPreferenceFromWatch$1", f = "HeartRateDataHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                        UserDataManager.getInstance(this.$context).saveLatestHRValue(latestHealthDataModel);
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

    @NotNull
    public final Pair<Integer, Calendar> getHeartRateDataBy(@NotNull EntityHourlyHeartRateData entityHourlyHeartRateData) {
        Intrinsics.checkNotNullParameter(entityHourlyHeartRateData, "entityHourlyHeartRateData");
        int size = 60 / entityHourlyHeartRateData.getCodedValues().size();
        int size2 = entityHourlyHeartRateData.getCodedValues().size();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < size2; i3++) {
            Integer num = entityHourlyHeartRateData.getCodedValues().get(i3);
            Intrinsics.checkNotNullExpressionValue(num, "entityHourlyHeartRateData.codedValues[i]");
            if (num.intValue() > 0) {
                Integer num2 = entityHourlyHeartRateData.getCodedValues().get(i3);
                Intrinsics.checkNotNullExpressionValue(num2, "entityHourlyHeartRateData.codedValues[i]");
                i2 = num2.intValue();
                i = i3;
            }
        }
        Date parseDate = AppUtils.parseDate(entityHourlyHeartRateData.getDate() + ' ' + entityHourlyHeartRateData.getStartTime(), "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate);
        calendar.add(12, size * i);
        return new Pair<>(Integer.valueOf(i2), calendar);
    }

    public final void saveLatestHeartRateDataToPreferenceFromWatch(@NotNull Context context, @NotNull HealthData healthData, @Nullable SuccessResultListener successResultListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(healthData, "healthData");
        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(healthData, context, successResultListener, null), 2, null);
    }
}
