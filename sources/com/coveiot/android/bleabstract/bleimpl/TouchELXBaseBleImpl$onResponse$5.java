package com.coveiot.android.bleabstract.bleimpl;

import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.khtouchdb.sleep.KHTGSleepRepository;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl$onResponse$5", f = "TouchELXBaseBleImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class TouchELXBaseBleImpl$onResponse$5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TouchELXBaseBleImpl f3278a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TouchELXBaseBleImpl$onResponse$5(TouchELXBaseBleImpl touchELXBaseBleImpl, Continuation<? super TouchELXBaseBleImpl$onResponse$5> continuation) {
        super(2, continuation);
        this.f3278a = touchELXBaseBleImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new TouchELXBaseBleImpl$onResponse$5(this.f3278a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new TouchELXBaseBleImpl$onResponse$5(this.f3278a, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        KHTGSleepRepository.Companion.getInstance(this.f3278a.getContext()).updateSleepDataProcessedBefore(this.f3278a.getMacAddress(), BleApiUtils.INSTANCE.getTodayStartTime().getTimeInMillis());
        return Unit.INSTANCE;
    }
}
