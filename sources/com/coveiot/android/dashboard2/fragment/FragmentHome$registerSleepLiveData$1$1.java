package com.coveiot.android.dashboard2.fragment;

import android.text.format.DateUtils;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.dashboard2.listener.LastNightSleepDataListener;
import com.coveiot.android.dashboard2.util.SleepDataHelper;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covepreferences.data.LastNightSleepData;
import java.util.List;
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
@DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$registerSleepLiveData$1$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class FragmentHome$registerSleepLiveData$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ List<SleepDataModelForLastNight> $it;
    public int label;
    public final /* synthetic */ FragmentHome this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FragmentHome$registerSleepLiveData$1$1(List<? extends SleepDataModelForLastNight> list, FragmentHome fragmentHome, Continuation<? super FragmentHome$registerSleepLiveData$1$1> continuation) {
        super(2, continuation);
        this.$it = list;
        this.this$0 = fragmentHome;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FragmentHome$registerSleepLiveData$1$1(this.$it, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FragmentHome$registerSleepLiveData$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            List<SleepDataModelForLastNight> list = this.$it;
            if (!(list == null || list.isEmpty())) {
                SleepDataHelper sleepDataHelper = SleepDataHelper.INSTANCE;
                FragmentActivity requireActivity = this.this$0.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                List<SleepDataModelForLastNight> list2 = this.$it;
                final FragmentHome fragmentHome = this.this$0;
                sleepDataHelper.getLastNightSleepData(requireActivity, list2, new LastNightSleepDataListener() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$registerSleepLiveData$1$1.1
                    @Override // com.coveiot.android.dashboard2.listener.LastNightSleepDataListener
                    public void onData(@Nullable LastNightSleepData lastNightSleepData) {
                        if (DateUtils.isToday(FragmentHome.this.getSelectedCalendar().getTimeInMillis())) {
                            FragmentHome.this.X2(lastNightSleepData != null ? lastNightSleepData.getDuration() : 0);
                        }
                    }
                });
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
