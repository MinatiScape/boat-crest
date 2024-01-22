package com.coveiot.android.bleabstract.bleimpl;

import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl$registerEventBus$1", f = "MatrixBleImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class MatrixBleImpl$registerEventBus$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MatrixBleImpl f3195a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MatrixBleImpl$registerEventBus$1(MatrixBleImpl matrixBleImpl, Continuation<? super MatrixBleImpl$registerEventBus$1> continuation) {
        super(2, continuation);
        this.f3195a = matrixBleImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new MatrixBleImpl$registerEventBus$1(this.f3195a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new MatrixBleImpl$registerEventBus$1(this.f3195a, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        String unused;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        unused = this.f3195a.b;
        BleEventBusManager.getInstance().getEventBus().register(this.f3195a);
        return Unit.INSTANCE;
    }
}
