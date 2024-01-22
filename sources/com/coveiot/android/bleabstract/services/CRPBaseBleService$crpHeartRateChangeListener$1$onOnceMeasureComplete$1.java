package com.coveiot.android.bleabstract.services;

import com.coveiot.android.bleabstract.models.ManualHrReading;
import com.coveiot.utils.CoveEventBusManager;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.services.CRPBaseBleService$crpHeartRateChangeListener$1$onOnceMeasureComplete$1", f = "CRPBaseBleService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class CRPBaseBleService$crpHeartRateChangeListener$1$onOnceMeasureComplete$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ManualHrReading f3704a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CRPBaseBleService$crpHeartRateChangeListener$1$onOnceMeasureComplete$1(ManualHrReading manualHrReading, Continuation<? super CRPBaseBleService$crpHeartRateChangeListener$1$onOnceMeasureComplete$1> continuation) {
        super(2, continuation);
        this.f3704a = manualHrReading;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new CRPBaseBleService$crpHeartRateChangeListener$1$onOnceMeasureComplete$1(this.f3704a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new CRPBaseBleService$crpHeartRateChangeListener$1$onOnceMeasureComplete$1(this.f3704a, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        CoveEventBusManager.getInstance().getEventBus().post(this.f3704a);
        return Unit.INSTANCE;
    }
}
