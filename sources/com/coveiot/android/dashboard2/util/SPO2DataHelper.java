package com.coveiot.android.dashboard2.util;

import android.content.Context;
import com.coveiot.android.bleabstract.response.HealthData;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.LatestHealthDataModel;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
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
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class SPO2DataHelper {
    @NotNull
    public static final SPO2DataHelper INSTANCE = new SPO2DataHelper();

    /* loaded from: classes4.dex */
    public enum SPO2Level {
        NORMAL(90),
        LOW(60),
        VERY_LOW(30);

        SPO2Level(int i) {
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.util.SPO2DataHelper$saveLatestSPO2DataToPreferenceFromWatch$1", f = "SPO2DataHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                        UserDataManager.getInstance(this.$context).saveLatestSpo2Value(latestHealthDataModel);
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
    public final Pair<Double, Calendar> getSPO2DataBy(@NotNull EntityHourlySpo2 hourlySPO2) {
        Intrinsics.checkNotNullParameter(hourlySPO2, "hourlySPO2");
        int size = 60 / hourlySPO2.codevalue.size();
        int size2 = hourlySPO2.codevalue.size();
        double d = 0.0d;
        int i = 0;
        for (int i2 = 0; i2 < size2; i2++) {
            Integer num = hourlySPO2.codevalue.get(i2);
            Intrinsics.checkNotNullExpressionValue(num, "hourlySPO2.codevalue.get(i)");
            if (num.intValue() > 0) {
                d = hourlySPO2.codevalue.get(i2).intValue();
                i = i2;
            }
        }
        Date parseDate = AppUtils.parseDate(hourlySPO2.getmDate() + ' ' + hourlySPO2.getStartTime(), "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate);
        calendar.add(12, size * i);
        return new Pair<>(Double.valueOf(d), calendar);
    }

    @Nullable
    public final CharSequence getSpo2Level(float f) {
        if (f == 2.0f) {
            return "NORMAL";
        }
        if (f == 1.0f) {
            return "LOW";
        }
        if (f == 0.0f) {
            return m.replace$default("VERY_LOW", "_", HexStringBuilder.DEFAULT_SEPARATOR, false, 4, (Object) null);
        }
        return String.valueOf(f);
    }

    public final void saveLatestSPO2DataToPreferenceFromWatch(@NotNull Context context, @NotNull HealthData healthData, @Nullable SuccessResultListener successResultListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(healthData, "healthData");
        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(healthData, context, successResultListener, null), 2, null);
    }
}
