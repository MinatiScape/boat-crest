package com.coveiot.android.bleabstract.services;

import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.formatter.SMAWorkout2Formatter;
import com.coveiot.khsmadb.workout.KhWorkoutRepository;
import com.szabh.smable3.entity.BleWorkout2;
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
@DebugMetadata(c = "com.coveiot.android.bleabstract.services.SmaBaseBleService$bleHandleCallback$2$1$onReadWorkout2$1", f = "SmaBaseBleService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class SmaBaseBleService$bleHandleCallback$2$1$onReadWorkout2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SmaBaseBleService f3925a;
    public final /* synthetic */ List<BleWorkout2> b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmaBaseBleService$bleHandleCallback$2$1$onReadWorkout2$1(SmaBaseBleService smaBaseBleService, List<BleWorkout2> list, Continuation<? super SmaBaseBleService$bleHandleCallback$2$1$onReadWorkout2$1> continuation) {
        super(2, continuation);
        this.f3925a = smaBaseBleService;
        this.b = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SmaBaseBleService$bleHandleCallback$2$1$onReadWorkout2$1(this.f3925a, this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new SmaBaseBleService$bleHandleCallback$2$1$onReadWorkout2$1(this.f3925a, this.b, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        KhWorkoutRepository.Companion.getInstance(this.f3925a).insertAll(SMAWorkout2Formatter.Companion.getInstance(this.f3925a).getKhBleWorkoutData(BleApiManager.getInstance(this.f3925a).getBleApi().getMacAddress(), this.b));
        return Unit.INSTANCE;
    }
}
