package com.coveiot.android.bleabstract.bleimpl;

import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.khidodb.walk.KHIDOStepsRepository;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$onResponse$3", f = "IDOBaseBleApiImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class IDOBaseBleApiImpl$onResponse$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ IDOBaseBleApiImpl f2983a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IDOBaseBleApiImpl$onResponse$3(IDOBaseBleApiImpl iDOBaseBleApiImpl, Continuation<? super IDOBaseBleApiImpl$onResponse$3> continuation) {
        super(2, continuation);
        this.f2983a = iDOBaseBleApiImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new IDOBaseBleApiImpl$onResponse$3(this.f2983a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new IDOBaseBleApiImpl$onResponse$3(this.f2983a, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        KHIDOStepsRepository.Companion.getInstance(this.f2983a.getContext()).updateStepsDataProcessedBefore(this.f2983a.getMacAddress(), BleApiUtils.INSTANCE.getTodayStartTime().getTimeInMillis());
        return Unit.INSTANCE;
    }
}
