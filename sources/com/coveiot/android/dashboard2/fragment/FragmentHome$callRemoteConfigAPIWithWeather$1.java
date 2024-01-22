package com.coveiot.android.dashboard2.fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.dashboard2.databinding.FragmentHomeBinding;
import com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface;
import com.coveiot.android.dashboard2.listener.OnDynamicTabDataChangedListener;
import com.coveiot.android.dashboard2.listener.WeatherResultListener;
import com.coveiot.android.dashboard2.uihelper.FragmentHomeHelper;
import com.coveiot.android.dynamictab.adapters.DynamicTabAdapter;
import com.coveiot.android.dynamictab.sports.model.DashboardModel;
import java.util.ArrayList;
import java.util.List;
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
@DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$callRemoteConfigAPIWithWeather$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class FragmentHome$callRemoteConfigAPIWithWeather$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ FragmentHome this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentHome$callRemoteConfigAPIWithWeather$1(FragmentHome fragmentHome, Continuation<? super FragmentHome$callRemoteConfigAPIWithWeather$1> continuation) {
        super(2, continuation);
        this.this$0 = fragmentHome;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FragmentHome$callRemoteConfigAPIWithWeather$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FragmentHome$callRemoteConfigAPIWithWeather$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                final FragmentHome fragmentHome = this.this$0;
                WeatherResultListener weatherResultListener = new WeatherResultListener() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$callRemoteConfigAPIWithWeather$1.1

                    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$callRemoteConfigAPIWithWeather$1$1$onSuccess$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: com.coveiot.android.dashboard2.fragment.FragmentHome$callRemoteConfigAPIWithWeather$1$1$a */
                    /* loaded from: classes4.dex */
                    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public int label;
                        public final /* synthetic */ FragmentHome this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public a(FragmentHome fragmentHome, Continuation<? super a> continuation) {
                            super(2, continuation);
                            this.this$0 = fragmentHome;
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
                                FragmentHomeHelper fragmentHomeHelper = this.this$0.getFragmentHomeHelper();
                                FragmentHomeBinding q0 = this.this$0.q0();
                                FragmentActivity requireActivity = this.this$0.requireActivity();
                                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                                fragmentHomeHelper.updateWeather(q0, requireActivity);
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    @Override // com.coveiot.android.dashboard2.listener.WeatherResultListener
                    public void onFailure(@Nullable String str) {
                    }

                    @Override // com.coveiot.android.dashboard2.listener.WeatherResultListener
                    public void onSuccess() {
                        if (FragmentHome.this.isAdded()) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new a(FragmentHome.this, null), 2, null);
                        }
                    }
                };
                final FragmentHome fragmentHome2 = this.this$0;
                ((FragmentHomeCallBackInterface) activity).callRemoteConfigAPI(weatherResultListener, new OnDynamicTabDataChangedListener() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome$callRemoteConfigAPIWithWeather$1.2

                    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$callRemoteConfigAPIWithWeather$1$2$onDynamicTabDataChanged$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: com.coveiot.android.dashboard2.fragment.FragmentHome$callRemoteConfigAPIWithWeather$1$2$a */
                    /* loaded from: classes4.dex */
                    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public final /* synthetic */ List<DashboardModel> $dashboardModels;
                        public int label;
                        public final /* synthetic */ FragmentHome this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public a(List<DashboardModel> list, FragmentHome fragmentHome, Continuation<? super a> continuation) {
                            super(2, continuation);
                            this.$dashboardModels = list;
                            this.this$0 = fragmentHome;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @NotNull
                        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                            return new a(this.$dashboardModels, this.this$0, continuation);
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
                                List<DashboardModel> list = this.$dashboardModels;
                                if (list == null || !(!list.isEmpty())) {
                                    this.this$0.q0().setShowDynamicTab(Boxing.boxBoolean(false));
                                } else {
                                    this.this$0.q0().setShowDynamicTab(Boxing.boxBoolean(true));
                                    DynamicTabAdapter dynamicTabAdapter = this.this$0.getDynamicTabAdapter();
                                    if (dynamicTabAdapter != null) {
                                        List<DashboardModel> list2 = this.$dashboardModels;
                                        Intrinsics.checkNotNull(list2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.dynamictab.sports.model.DashboardModel>");
                                        dynamicTabAdapter.setDashboardModels((ArrayList) list2);
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    @Override // com.coveiot.android.dashboard2.listener.OnDynamicTabDataChangedListener
                    public void onDynamicTabDataChanged(@Nullable List<DashboardModel> list) {
                        if (FragmentHome.this.isAdded()) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentHome.this), Dispatchers.getMain(), null, new a(list, FragmentHome.this, null), 2, null);
                        }
                    }
                });
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
