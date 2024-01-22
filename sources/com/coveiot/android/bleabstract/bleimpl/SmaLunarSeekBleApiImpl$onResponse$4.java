package com.coveiot.android.bleabstract.bleimpl;

import com.coveiot.android.bleabstract.utils.smaUtils.SmaUtils;
import com.coveiot.khsmadb.stress.KhStressRepository;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.SmaLunarSeekBleApiImpl$onResponse$4", f = "SmaLunarSeekBleApiImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class SmaLunarSeekBleApiImpl$onResponse$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SmaLunarSeekBleApiImpl f3242a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmaLunarSeekBleApiImpl$onResponse$4(SmaLunarSeekBleApiImpl smaLunarSeekBleApiImpl, Continuation<? super SmaLunarSeekBleApiImpl$onResponse$4> continuation) {
        super(2, continuation);
        this.f3242a = smaLunarSeekBleApiImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SmaLunarSeekBleApiImpl$onResponse$4(this.f3242a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new SmaLunarSeekBleApiImpl$onResponse$4(this.f3242a, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        String macAddress = this.f3242a.getMacAddress();
        SmaUtils smaUtils = SmaUtils.INSTANCE;
        KhStressRepository.Companion.getInstance(this.f3242a.getContext()).updateStressDataProcessedBefore(macAddress, (smaUtils.getTodayStartTime().getTimeInMillis() - smaUtils.getCalenderFor2000().getTimeInMillis()) / 1000);
        return Unit.INSTANCE;
    }
}
