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
@DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.JStyle2301ABaseBleApiImpl$registerEventBus$1", f = "JStyle2301ABaseBleApiImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class JStyle2301ABaseBleApiImpl$registerEventBus$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ JStyle2301ABaseBleApiImpl f3134a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JStyle2301ABaseBleApiImpl$registerEventBus$1(JStyle2301ABaseBleApiImpl jStyle2301ABaseBleApiImpl, Continuation<? super JStyle2301ABaseBleApiImpl$registerEventBus$1> continuation) {
        super(2, continuation);
        this.f3134a = jStyle2301ABaseBleApiImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new JStyle2301ABaseBleApiImpl$registerEventBus$1(this.f3134a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new JStyle2301ABaseBleApiImpl$registerEventBus$1(this.f3134a, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        BleEventBusManager.getInstance().getEventBus().register(this.f3134a);
        return Unit.INSTANCE;
    }
}
