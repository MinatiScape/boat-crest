package com.coveiot.android.dashboard2.fragment;

import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.viewmodel.FragmentHomeViewModel;
import com.coveiot.android.theme.model.BindingDataModel1;
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
@DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$onConnectionUpdated$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class FragmentHome$onConnectionUpdated$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ ConnectionStatus $it;
    public int label;
    public final /* synthetic */ FragmentHome this$0;

    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ConnectionStatus.values().length];
            try {
                iArr[ConnectionStatus.SCANNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ConnectionStatus.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ConnectionStatus.CONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ConnectionStatus.DISCOVERING_SERVICES.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ConnectionStatus.DISCONNECTED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentHome$onConnectionUpdated$1(ConnectionStatus connectionStatus, FragmentHome fragmentHome, Continuation<? super FragmentHome$onConnectionUpdated$1> continuation) {
        super(2, continuation);
        this.$it = connectionStatus;
        this.this$0 = fragmentHome;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FragmentHome$onConnectionUpdated$1(this.$it, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FragmentHome$onConnectionUpdated$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ConnectionStatus connectionStatus = this.$it;
            if (connectionStatus != null) {
                FragmentHome fragmentHome = this.this$0;
                fragmentHome.x0();
                int i = WhenMappings.$EnumSwitchMapping$0[connectionStatus.ordinal()];
                if (i == 1) {
                    fragmentHome.q0().setBleConnectionState(new BindingDataModel1(true, fragmentHome.getString(R.string.bluetooth), fragmentHome.getString(R.string.trying_to_connect), fragmentHome.requireActivity().getDrawable(R.drawable.ic_ble_disconnected)));
                } else if (i == 2) {
                    fragmentHome.q0().setBleConnectionState(new BindingDataModel1(true, fragmentHome.getString(R.string.bluetooth), fragmentHome.getString(R.string.connecting_dot), fragmentHome.requireActivity().getDrawable(R.drawable.ic_ble_disconnected)));
                } else if (i == 3) {
                    fragmentHome.q0().setBleConnectionState(new BindingDataModel1(true, fragmentHome.getString(R.string.bluetooth), fragmentHome.getString(R.string.connected), fragmentHome.requireActivity().getDrawable(R.drawable.ic_ble_connected)));
                    FragmentHomeViewModel fragmentHomeViewModel = fragmentHome.o;
                    if (fragmentHomeViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                        fragmentHomeViewModel = null;
                    }
                    fragmentHomeViewModel.getBT3ConnectionStatus();
                } else if (i == 4) {
                    fragmentHome.q0().setBleConnectionState(new BindingDataModel1(true, fragmentHome.getString(R.string.bluetooth), fragmentHome.getString(R.string.trying_to_connect), fragmentHome.requireActivity().getDrawable(R.drawable.ic_ble_disconnected)));
                } else if (i != 5) {
                    fragmentHome.q0().setBleConnectionState(new BindingDataModel1(true, fragmentHome.getString(R.string.bluetooth), fragmentHome.getString(R.string.disconnected), fragmentHome.requireActivity().getDrawable(R.drawable.ic_ble_disconnected)));
                } else {
                    fragmentHome.q0().setBleConnectionState(new BindingDataModel1(true, fragmentHome.getString(R.string.bluetooth), fragmentHome.getString(R.string.disconnected), fragmentHome.requireActivity().getDrawable(R.drawable.ic_ble_disconnected)));
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
