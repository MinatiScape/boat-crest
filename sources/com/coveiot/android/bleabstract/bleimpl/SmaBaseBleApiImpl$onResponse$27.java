package com.coveiot.android.bleabstract.bleimpl;

import com.coveiot.android.bleabstract.utils.smaUtils.SmaUtils;
import com.coveiot.android.smasdk.api.SmaBaseRes;
import com.coveiot.khsmadb.activity.KhActivityRepository;
import com.coveiot.khsmadb.heartrate.KhHeartRateRepository;
import com.szabh.smable3.BleKey;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$onResponse$27", f = "SmaBaseBleApiImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class SmaBaseBleApiImpl$onResponse$27 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SmaBaseRes f3227a;
    public final /* synthetic */ SmaBaseBleApiImpl b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmaBaseBleApiImpl$onResponse$27(SmaBaseRes smaBaseRes, SmaBaseBleApiImpl smaBaseBleApiImpl, Continuation<? super SmaBaseBleApiImpl$onResponse$27> continuation) {
        super(2, continuation);
        this.f3227a = smaBaseRes;
        this.b = smaBaseBleApiImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SmaBaseBleApiImpl$onResponse$27(this.f3227a, this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new SmaBaseBleApiImpl$onResponse$27(this.f3227a, this.b, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        this.f3227a.getBaseReq().setKey(BleKey.WORKOUT);
        this.b.onResponse(this.f3227a);
        String macAddress = this.b.getMacAddress();
        long currentTimeMillis = System.currentTimeMillis();
        SmaUtils smaUtils = SmaUtils.INSTANCE;
        long j = 1000;
        KhActivityRepository.Companion.getInstance(this.b.getContext()).updateWorkoutActivityDataProcessedBefore(macAddress, (currentTimeMillis - smaUtils.getCalenderFor2000().getTimeInMillis()) / j);
        KhHeartRateRepository.Companion.getInstance(this.b.getContext()).updateWorkoutHeartRateDataProcessedBefore(this.b.getMacAddress(), (System.currentTimeMillis() - smaUtils.getCalenderFor2000().getTimeInMillis()) / j);
        return Unit.INSTANCE;
    }
}
