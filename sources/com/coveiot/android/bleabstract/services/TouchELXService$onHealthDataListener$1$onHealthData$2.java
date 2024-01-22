package com.coveiot.android.bleabstract.services;

import com.coveiot.android.bleabstract.formatter.touch.TouchSleepFormatter;
import com.coveiot.khtouchdb.sleep.EntityTGSleepData;
import com.coveiot.khtouchdb.sleep.KHTGSleepRepository;
import com.touchgui.sdk.bean.TGSleepData;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.services.TouchELXService$onHealthDataListener$1$onHealthData$2", f = "TouchELXService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class TouchELXService$onHealthDataListener$1$onHealthData$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TouchELXService f3955a;
    public final /* synthetic */ Object b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TouchELXService$onHealthDataListener$1$onHealthData$2(TouchELXService touchELXService, Object obj, Continuation<? super TouchELXService$onHealthDataListener$1$onHealthData$2> continuation) {
        super(2, continuation);
        this.f3955a = touchELXService;
        this.b = obj;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new TouchELXService$onHealthDataListener$1$onHealthData$2(this.f3955a, this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new TouchELXService$onHealthDataListener$1$onHealthData$2(this.f3955a, this.b, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        EntityTGSleepData convertTGSleepToEntity = TouchSleepFormatter.Companion.getInstance(this.f3955a).convertTGSleepToEntity((TGSleepData) this.b);
        if (convertTGSleepToEntity != null) {
            KHTGSleepRepository.Companion.getInstance(this.f3955a).insertSleepData(convertTGSleepToEntity);
        }
        return Unit.INSTANCE;
    }
}
