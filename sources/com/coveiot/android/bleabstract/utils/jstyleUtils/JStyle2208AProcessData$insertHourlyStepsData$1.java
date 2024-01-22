package com.coveiot.android.bleabstract.utils.jstyleUtils;

import android.content.Context;
import com.coveiot.android.bleabstract.response.StepsDayData;
import com.coveiot.khjstyledb.walk.KHJstyleHourlyWalkData;
import com.coveiot.khjstyledb.walk.KHJstyleWalkDBRepository;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.bleabstract.utils.jstyleUtils.JStyle2208AProcessData$insertHourlyStepsData$1", f = "JStyle2208AProcessData.kt", i = {}, l = {818}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class JStyle2208AProcessData$insertHourlyStepsData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public int f4064a;
    public final /* synthetic */ StepsDayData b;
    public final /* synthetic */ Context c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JStyle2208AProcessData$insertHourlyStepsData$1(StepsDayData stepsDayData, Context context, Continuation<? super JStyle2208AProcessData$insertHourlyStepsData$1> continuation) {
        super(2, continuation);
        this.b = stepsDayData;
        this.c = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new JStyle2208AProcessData$insertHourlyStepsData$1(this.b, this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new JStyle2208AProcessData$insertHourlyStepsData$1(this.b, this.c, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = a.getCOROUTINE_SUSPENDED();
        int i = this.f4064a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            List<KHJstyleHourlyWalkData> hourlyStepsData = JStyle2208AProcessData.INSTANCE.getHourlyStepsData(this.b);
            this.f4064a = 1;
            if (KHJstyleWalkDBRepository.Companion.getInstance(this.c).insertHourlyStepsData(hourlyStepsData, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
