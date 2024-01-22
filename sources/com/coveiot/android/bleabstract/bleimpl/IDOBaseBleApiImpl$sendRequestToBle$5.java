package com.coveiot.android.bleabstract.bleimpl;

import com.coveiot.android.idoSdk.api.IDOBaseRes;
import com.coveiot.khidodb.activities.EntityHealthActivityV3;
import com.coveiot.khidodb.activities.EntityHealthSwimV3;
import com.coveiot.khidodb.activities.KHIDOActivityRepository;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$sendRequestToBle$5", f = "IDOBaseBleApiImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class IDOBaseBleApiImpl$sendRequestToBle$5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ IDOBaseBleApiImpl f2993a;
    public final /* synthetic */ IDOBaseRes b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IDOBaseBleApiImpl$sendRequestToBle$5(IDOBaseBleApiImpl iDOBaseBleApiImpl, IDOBaseRes iDOBaseRes, Continuation<? super IDOBaseBleApiImpl$sendRequestToBle$5> continuation) {
        super(2, continuation);
        this.f2993a = iDOBaseBleApiImpl;
        this.b = iDOBaseRes;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new IDOBaseBleApiImpl$sendRequestToBle$5(this.f2993a, this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new IDOBaseBleApiImpl$sendRequestToBle$5(this.f2993a, this.b, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        KHIDOActivityRepository.Companion companion = KHIDOActivityRepository.Companion;
        List<EntityHealthActivityV3> allUnProcessedActivityData = companion.getInstance(this.f2993a.getContext()).getAllUnProcessedActivityData(this.f2993a.getMacAddress());
        List<EntityHealthSwimV3> allUnProcessedSwimActivityData = companion.getInstance(this.f2993a.getContext()).getAllUnProcessedSwimActivityData(this.f2993a.getMacAddress());
        this.b.setObj(allUnProcessedActivityData);
        this.b.setObj2(allUnProcessedSwimActivityData);
        this.f2993a.onResponse(this.b);
        return Unit.INSTANCE;
    }
}
