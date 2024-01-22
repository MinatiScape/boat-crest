package com.coveiot.android.bleabstract.bleimpl;

import com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfoRepository;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl$onResponse$39", f = "SmaBaseBleApiImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class SmaBaseBleApiImpl$onResponse$39 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SmaBaseBleApiImpl f3230a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmaBaseBleApiImpl$onResponse$39(SmaBaseBleApiImpl smaBaseBleApiImpl, Continuation<? super SmaBaseBleApiImpl$onResponse$39> continuation) {
        super(2, continuation);
        this.f3230a = smaBaseBleApiImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SmaBaseBleApiImpl$onResponse$39(this.f3230a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new SmaBaseBleApiImpl$onResponse$39(this.f3230a, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        KhSmaDeviceInfoRepository.Companion.getInstance(this.f3230a.getContext()).updateSpO2LastSyncTime(this.f3230a.getMacAddress(), System.currentTimeMillis());
        return Unit.INSTANCE;
    }
}
