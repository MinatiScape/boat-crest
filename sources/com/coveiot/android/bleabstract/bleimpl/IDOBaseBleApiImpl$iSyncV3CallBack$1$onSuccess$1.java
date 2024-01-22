package com.coveiot.android.bleabstract.bleimpl;

import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.idoSdk.api.IDOBaseReq;
import com.coveiot.android.idoSdk.api.IDOBaseRes;
import com.coveiot.khidodb.walk.EntityHealthSportV3;
import com.coveiot.khidodb.walk.KHIDOStepsRepository;
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
@DebugMetadata(c = "com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl$iSyncV3CallBack$1$onSuccess$1", f = "IDOBaseBleApiImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class IDOBaseBleApiImpl$iSyncV3CallBack$1$onSuccess$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ IDOBaseBleApiImpl f2974a;
    public final /* synthetic */ BleBaseRequest b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IDOBaseBleApiImpl$iSyncV3CallBack$1$onSuccess$1(IDOBaseBleApiImpl iDOBaseBleApiImpl, BleBaseRequest bleBaseRequest, Continuation<? super IDOBaseBleApiImpl$iSyncV3CallBack$1$onSuccess$1> continuation) {
        super(2, continuation);
        this.f2974a = iDOBaseBleApiImpl;
        this.b = bleBaseRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new IDOBaseBleApiImpl$iSyncV3CallBack$1$onSuccess$1(this.f2974a, this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new IDOBaseBleApiImpl$iSyncV3CallBack$1$onSuccess$1(this.f2974a, this.b, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IDOBaseReq a2;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        List<EntityHealthSportV3> allUnProcessedStepsData = KHIDOStepsRepository.Companion.getInstance(this.f2974a.getContext()).getAllUnProcessedStepsData(this.f2974a.getMacAddress());
        if (allUnProcessedStepsData != null) {
            int size = allUnProcessedStepsData.size();
            int i = 0;
            while (i < size) {
                IDOBaseRes iDOBaseRes = new IDOBaseRes();
                a2 = this.f2974a.a(this.b);
                if (a2 != null) {
                    iDOBaseRes.setBaseReq(a2);
                    iDOBaseRes.setObj(allUnProcessedStepsData.get(i));
                    iDOBaseRes.setComplete(i == allUnProcessedStepsData.size() - 1);
                    this.f2974a.onResponse(iDOBaseRes);
                }
                i++;
            }
        }
        return Unit.INSTANCE;
    }
}
