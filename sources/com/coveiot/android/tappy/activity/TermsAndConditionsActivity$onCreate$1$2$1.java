package com.coveiot.android.tappy.activity;

import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$1$2$1;
import com.coveiot.android.tappy.viewmodel.NfcStrapViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$1$2$1", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class TermsAndConditionsActivity$onCreate$1$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ TermsAndConditionsActivity this$0;

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$1$2$1$1$1", f = "TermsAndConditionsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ TermsAndConditionsActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = termsAndConditionsActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                NfcStrapViewModel nfcStrapViewModel = this.this$0.p;
                if (nfcStrapViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    nfcStrapViewModel = null;
                }
                Long l = this.this$0.t;
                Intrinsics.checkNotNull(l);
                long longValue = l.longValue();
                Long l2 = this.this$0.z;
                Intrinsics.checkNotNull(l2);
                nfcStrapViewModel.getTokenPersoScript(longValue, l2.longValue());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TermsAndConditionsActivity$onCreate$1$2$1(TermsAndConditionsActivity termsAndConditionsActivity, Continuation<? super TermsAndConditionsActivity$onCreate$1$2$1> continuation) {
        super(2, continuation);
        this.this$0 = termsAndConditionsActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new TermsAndConditionsActivity$onCreate$1$2$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((TermsAndConditionsActivity$onCreate$1$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        long j;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Handler handler = new Handler(Looper.getMainLooper());
            final TermsAndConditionsActivity termsAndConditionsActivity = this.this$0;
            Runnable runnable = new Runnable() { // from class: com.coveiot.android.tappy.activity.TermsAndConditionsActivity$onCreate$1$2$1$invokeSuspend$$inlined$Runnable$1
                @Override // java.lang.Runnable
                public final void run() {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(TermsAndConditionsActivity.this), Dispatchers.getIO(), null, new TermsAndConditionsActivity$onCreate$1$2$1.a(TermsAndConditionsActivity.this, null), 2, null);
                }
            };
            j = this.this$0.D;
            handler.postDelayed(runnable, j);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
