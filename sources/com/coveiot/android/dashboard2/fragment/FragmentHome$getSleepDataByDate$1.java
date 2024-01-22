package com.coveiot.android.dashboard2.fragment;

import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.dashboard2.listener.LastNightSleepDataListener;
import com.coveiot.android.dashboard2.util.SleepDataHelper;
import com.coveiot.covepreferences.data.LastNightSleepData;
import java.util.Calendar;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$getSleepDataByDate$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class FragmentHome$getSleepDataByDate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Calendar $calendar;
    public int label;
    public final /* synthetic */ FragmentHome this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentHome$getSleepDataByDate$1(FragmentHome fragmentHome, Calendar calendar, Continuation<? super FragmentHome$getSleepDataByDate$1> continuation) {
        super(2, continuation);
        this.this$0 = fragmentHome;
        this.$calendar = calendar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FragmentHome$getSleepDataByDate$1(this.this$0, this.$calendar, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FragmentHome$getSleepDataByDate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SleepDataHelper sleepDataHelper = SleepDataHelper.INSTANCE;
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            Calendar calendar = this.$calendar;
            final FragmentHome fragmentHome = this.this$0;
            sleepDataHelper.getSleepDataByDate(requireActivity, calendar, new LastNightSleepDataListener() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$getSleepDataByDate$1.1
                @Override // com.coveiot.android.dashboard2.listener.LastNightSleepDataListener
                public void onData(@Nullable LastNightSleepData lastNightSleepData) {
                    FragmentHome.this.X2(lastNightSleepData != null ? lastNightSleepData.getDuration() : 0);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
