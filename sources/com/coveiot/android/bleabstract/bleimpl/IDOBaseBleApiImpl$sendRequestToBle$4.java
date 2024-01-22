package com.coveiot.android.bleabstract.bleimpl;

import com.coveiot.android.idoSdk.api.IDOBaseRes;
import com.coveiot.khidodb.stress.KHIDOStressRepository;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$sendRequestToBle$4", f = "IDOBaseBleApiImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class IDOBaseBleApiImpl$sendRequestToBle$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ IDOBaseBleApiImpl f2992a;
    public final /* synthetic */ IDOBaseRes b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IDOBaseBleApiImpl$sendRequestToBle$4(IDOBaseBleApiImpl iDOBaseBleApiImpl, IDOBaseRes iDOBaseRes, Continuation<? super IDOBaseBleApiImpl$sendRequestToBle$4> continuation) {
        super(2, continuation);
        this.f2992a = iDOBaseBleApiImpl;
        this.b = iDOBaseRes;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new IDOBaseBleApiImpl$sendRequestToBle$4(this.f2992a, this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new IDOBaseBleApiImpl$sendRequestToBle$4(this.f2992a, this.b, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        this.b.setObj(KHIDOStressRepository.Companion.getInstance(this.f2992a.getContext()).getAllUnProcessedStressData(this.f2992a.getMacAddress()));
        this.f2992a.onResponse(this.b);
        return Unit.INSTANCE;
    }
}
