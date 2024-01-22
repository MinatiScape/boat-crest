package com.coveiot.android.navigation.activities;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces$observeNetworkStatus$1;
import com.coveiot.android.navigation.network.NetworkMonitor;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces$observeNetworkStatus$1", f = "ActivityNavigationSearchPlaces.kt", i = {}, l = {com.veryfit.multi.nativeprotocol.b.k2}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class ActivityNavigationSearchPlaces$observeNetworkStatus$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ ActivityNavigationSearchPlaces this$0;

    /* renamed from: com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces$observeNetworkStatus$1$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1<T> implements FlowCollector {
        public final /* synthetic */ ActivityNavigationSearchPlaces h;

        /* renamed from: com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces$observeNetworkStatus$1$1$WhenMappings */
        /* loaded from: classes5.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[NetworkMonitor.Status.values().length];
                try {
                    iArr[NetworkMonitor.Status.Lost.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[NetworkMonitor.Status.Available.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public AnonymousClass1(ActivityNavigationSearchPlaces activityNavigationSearchPlaces) {
            this.h = activityNavigationSearchPlaces;
        }

        public static final void e(final ActivityNavigationSearchPlaces this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Drawable drawable = this$0.getResources().getDrawable(R.drawable.no_internet);
            Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.no_internet)");
            String string = this$0.getResources().getString(R.string.no_internet);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.no_internet)");
            String string2 = this$0.getResources().getString(R.string.please_ensure_that_you_have_a_stable_internet_connection);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…able_internet_connection)");
            this$0.setInternetAlertDialog(new BottomSheetDialogImageTitleMessage(this$0, drawable, string, string2, false));
            if (this$0.getInternetAlertDialog() != null) {
                BottomSheetDialogImageTitleMessage internetAlertDialog = this$0.getInternetAlertDialog();
                Intrinsics.checkNotNull(internetAlertDialog);
                internetAlertDialog.setCancelable(false);
                BottomSheetDialogImageTitleMessage internetAlertDialog2 = this$0.getInternetAlertDialog();
                Intrinsics.checkNotNull(internetAlertDialog2);
                String string3 = this$0.getString(R.string.okay);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.okay)");
                internetAlertDialog2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.w0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityNavigationSearchPlaces$observeNetworkStatus$1.AnonymousClass1.f(ActivityNavigationSearchPlaces.this, view);
                    }
                });
                BottomSheetDialogImageTitleMessage internetAlertDialog3 = this$0.getInternetAlertDialog();
                Intrinsics.checkNotNull(internetAlertDialog3);
                if (internetAlertDialog3.isShowing()) {
                    return;
                }
                BottomSheetDialogImageTitleMessage internetAlertDialog4 = this$0.getInternetAlertDialog();
                Intrinsics.checkNotNull(internetAlertDialog4);
                internetAlertDialog4.show();
            }
        }

        public static final void f(ActivityNavigationSearchPlaces this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            BottomSheetDialogImageTitleMessage internetAlertDialog = this$0.getInternetAlertDialog();
            Intrinsics.checkNotNull(internetAlertDialog);
            internetAlertDialog.dismiss();
            this$0.finish();
        }

        public static final void g(ActivityNavigationSearchPlaces this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (this$0.getInternetAlertDialog() != null) {
                BottomSheetDialogImageTitleMessage internetAlertDialog = this$0.getInternetAlertDialog();
                Intrinsics.checkNotNull(internetAlertDialog);
                if (internetAlertDialog.isShowing()) {
                    BottomSheetDialogImageTitleMessage internetAlertDialog2 = this$0.getInternetAlertDialog();
                    Intrinsics.checkNotNull(internetAlertDialog2);
                    internetAlertDialog2.dismiss();
                }
            }
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        @Nullable
        /* renamed from: d */
        public final Object emit(@NotNull NetworkMonitor.Status status, @NotNull Continuation<? super Unit> continuation) {
            int i = WhenMappings.$EnumSwitchMapping$0[status.ordinal()];
            if (i == 1) {
                final ActivityNavigationSearchPlaces activityNavigationSearchPlaces = this.h;
                activityNavigationSearchPlaces.runOnUiThread(new Runnable() { // from class: com.coveiot.android.navigation.activities.x0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityNavigationSearchPlaces$observeNetworkStatus$1.AnonymousClass1.e(ActivityNavigationSearchPlaces.this);
                    }
                });
            } else if (i == 2) {
                final ActivityNavigationSearchPlaces activityNavigationSearchPlaces2 = this.h;
                activityNavigationSearchPlaces2.runOnUiThread(new Runnable() { // from class: com.coveiot.android.navigation.activities.y0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityNavigationSearchPlaces$observeNetworkStatus$1.AnonymousClass1.g(ActivityNavigationSearchPlaces.this);
                    }
                });
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityNavigationSearchPlaces$observeNetworkStatus$1(ActivityNavigationSearchPlaces activityNavigationSearchPlaces, Continuation<? super ActivityNavigationSearchPlaces$observeNetworkStatus$1> continuation) {
        super(2, continuation);
        this.this$0 = activityNavigationSearchPlaces;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ActivityNavigationSearchPlaces$observeNetworkStatus$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ActivityNavigationSearchPlaces$observeNetworkStatus$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        NetworkMonitor H;
        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            H = this.this$0.H();
            Flow<NetworkMonitor.Status> observeNetworkStatus = H.observeNetworkStatus();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0);
            this.label = 1;
            if (observeNetworkStatus.collect(anonymousClass1, this) == coroutine_suspended) {
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
