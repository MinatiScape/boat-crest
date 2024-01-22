package com.coveiot.android.leonardo.more.fragments;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
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
/* loaded from: classes5.dex */
public final class WatchFeaturesFragment$initiateFindMyWatch$1 implements SettingsResultListener {
    public final /* synthetic */ WatchFeaturesFragment h;

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.fragments.WatchFeaturesFragment$initiateFindMyWatch$1$onSettingsResponse$1", f = "WatchFeaturesFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ WatchFeaturesFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(WatchFeaturesFragment watchFeaturesFragment, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = watchFeaturesFragment;
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
                this.this$0.m = false;
                if (this.this$0.isAdded()) {
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    Context requireContext = this.this$0.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    if (!companion.isCZDevice(requireContext)) {
                        Context requireContext2 = this.this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        if (!companion.isCADevice(requireContext2)) {
                            Context requireContext3 = this.this$0.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                            if (!companion.isCYDevice(requireContext3)) {
                                Context requireContext4 = this.this$0.requireContext();
                                Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                                if (!companion.isPS1Device(requireContext4)) {
                                    Context requireContext5 = this.this$0.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
                                    if (!companion.isBESDevice(requireContext5)) {
                                        Context requireContext6 = this.this$0.requireContext();
                                        Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
                                        if (companion.isTouchELXDevice(requireContext6)) {
                                            Toast.makeText(this.this$0.requireContext(), this.this$0.getString(R.string.band_will_ring), 1).show();
                                        } else {
                                            Toast.makeText(this.this$0.requireContext(), this.this$0.getString(R.string.band_vibrate_for_5_times), 1).show();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
    public void onSettingsError(@NotNull BleBaseError error) {
        Intrinsics.checkNotNullParameter(error, "error");
    }

    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
    public void onSettingsResponse(@NotNull BleBaseResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        LifecycleOwnerKt.getLifecycleScope(this.h).launchWhenResumed(new a(this.h, null));
    }
}
