package com.coveiot.android.dashboard2.fragment;

import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.dashboard2.listener.StepsDataModelListener;
import com.coveiot.android.dashboard2.viewmodel.StepsDataViewModel;
import com.coveiot.covepreferences.data.StepsDataModel;
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
@DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$getTodayStepData$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class FragmentHome$getTodayStepData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ FragmentHome this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentHome$getTodayStepData$1(FragmentHome fragmentHome, Continuation<? super FragmentHome$getTodayStepData$1> continuation) {
        super(2, continuation);
        this.this$0 = fragmentHome;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FragmentHome$getTodayStepData$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FragmentHome$getTodayStepData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        StepsDataViewModel stepsDataViewModel;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            stepsDataViewModel = this.this$0.q;
            if (stepsDataViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mStepsDataViewModel");
                stepsDataViewModel = null;
            }
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            final FragmentHome fragmentHome = this.this$0;
            stepsDataViewModel.getTodayStepsData(requireActivity, new StepsDataModelListener() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$getTodayStepData$1.1
                @Override // com.coveiot.android.dashboard2.listener.StepsDataModelListener
                public void onData(@Nullable StepsDataModel stepsDataModel) {
                    FragmentHome.this.T2(stepsDataModel);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
