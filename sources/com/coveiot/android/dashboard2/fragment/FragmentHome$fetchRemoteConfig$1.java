package com.coveiot.android.dashboard2.fragment;

import android.content.Context;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface;
import com.coveiot.android.sportsnotification.SportsUtils;
import com.coveiot.android.theme.utils.BoatCoinsFirebaseConfigResultListener;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$fetchRemoteConfig$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class FragmentHome$fetchRemoteConfig$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ FragmentHome this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentHome$fetchRemoteConfig$1(FragmentHome fragmentHome, Continuation<? super FragmentHome$fetchRemoteConfig$1> continuation) {
        super(2, continuation);
        this.this$0 = fragmentHome;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FragmentHome$fetchRemoteConfig$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FragmentHome$fetchRemoteConfig$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
                FragmentActivity activity = this.this$0.getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
                ((FragmentHomeCallBackInterface) activity).fetchRemoteConfig();
                Context requireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                final FragmentHome fragmentHome = this.this$0;
                SportsUtils.isBoatCoinsEnabled(requireContext, new BoatCoinsFirebaseConfigResultListener() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$fetchRemoteConfig$1.1

                    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$fetchRemoteConfig$1$1$onResult$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: com.coveiot.android.dashboard2.fragment.FragmentHome$fetchRemoteConfig$1$1$a */
                    /* loaded from: classes4.dex */
                    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public final /* synthetic */ boolean $result;
                        public int label;
                        public final /* synthetic */ FragmentHome this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public a(FragmentHome fragmentHome, boolean z, Continuation<? super a> continuation) {
                            super(2, continuation);
                            this.this$0 = fragmentHome;
                            this.$result = z;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @NotNull
                        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                            return new a(this.this$0, this.$result, continuation);
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
                                this.this$0.q0().setShowBoatCoins(Boxing.boxBoolean(this.$result));
                                if (this.$result) {
                                    FragmentHome fragmentHome = this.this$0;
                                    TextView textView = fragmentHome.q0().tvboatExclusiveHeader;
                                    Intrinsics.checkNotNullExpressionValue(textView, "binding.tvboatExclusiveHeader");
                                    fragmentHome.visible(textView);
                                }
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    @Override // com.coveiot.android.theme.utils.BoatCoinsFirebaseConfigResultListener
                    public void onFailure(@Nullable String str) {
                    }

                    @Override // com.coveiot.android.theme.utils.BoatCoinsFirebaseConfigResultListener
                    public void onResult(boolean z) {
                        if (FragmentHome.this.isAdded()) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new a(FragmentHome.this, z, null), 2, null);
                        }
                    }
                });
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
