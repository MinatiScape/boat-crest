package com.coveiot.android.leonardo.more.activities;

import android.os.Handler;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$onTransferCompleted$1;
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
@DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$onTransferCompleted$1", f = "ActivityFirmwareUpdateKaHaApollo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateKaHaApollo$onTransferCompleted$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ ActivityFirmwareUpdateKaHaApollo this$0;

    /* renamed from: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$onTransferCompleted$1$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1 implements Observer<ConnectionStatus> {
        public final /* synthetic */ ActivityFirmwareUpdateKaHaApollo h;

        public AnonymousClass1(ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo) {
            this.h = activityFirmwareUpdateKaHaApollo;
        }

        public static final void b(ActivityFirmwareUpdateKaHaApollo this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.J();
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(@Nullable ConnectionStatus connectionStatus) {
            Handler handler;
            if (connectionStatus == ConnectionStatus.CONNECTED) {
                handler = this.h.w;
                final ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo = this.h;
                handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.lb
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityFirmwareUpdateKaHaApollo$onTransferCompleted$1.AnonymousClass1.b(ActivityFirmwareUpdateKaHaApollo.this);
                    }
                }, 3000L);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityFirmwareUpdateKaHaApollo$onTransferCompleted$1(ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo, Continuation<? super ActivityFirmwareUpdateKaHaApollo$onTransferCompleted$1> continuation) {
        super(2, continuation);
        this.this$0 = activityFirmwareUpdateKaHaApollo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ActivityFirmwareUpdateKaHaApollo$onTransferCompleted$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ActivityFirmwareUpdateKaHaApollo$onTransferCompleted$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            LiveData<ConnectionStatus> registerConnectionStatus = BleApiManager.getInstance(this.this$0).getBleApi().registerConnectionStatus();
            ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo = this.this$0;
            registerConnectionStatus.observe(activityFirmwareUpdateKaHaApollo, new AnonymousClass1(activityFirmwareUpdateKaHaApollo));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
