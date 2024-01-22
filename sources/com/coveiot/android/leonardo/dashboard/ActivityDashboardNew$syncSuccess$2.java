package com.coveiot.android.leonardo.dashboard;

import android.content.Intent;
import android.widget.Toast;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.coveiot.android.boat.GenericBandApplication;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.SettingsSyncHelper;
import com.coveiot.android.remotecommandframework.alexa.model.RcfSyncComplete;
import com.coveiot.android.sportsnotification.model.SpSyncComplete;
import com.coveiot.android.theme.BottomSheetDialogTransparentFullScreen;
import com.coveiot.android.weather.weather.WeatherUtils;
import com.coveiot.sdk.ble.utils.BlePreferenceManager;
import com.coveiot.sdk.ble.utils.CommonPreference;
import com.coveiot.utils.CoveEventBusManager;
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
/* loaded from: classes2.dex */
public final class ActivityDashboardNew$syncSuccess$2 implements SettingsSyncHelper.SettingsSyncListner {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityDashboardNew f4671a;

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ActivityDashboardNew$syncSuccess$2$onProgressUpdate$1", f = "ActivityDashboardNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ int $progress;
        public int label;
        public final /* synthetic */ ActivityDashboardNew this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(ActivityDashboardNew activityDashboardNew, int i, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = activityDashboardNew;
            this.$progress = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.this$0, this.$progress, continuation);
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
                if (!this.this$0.isFinishing()) {
                    BottomSheetDialogTransparentFullScreen bottomSheetTransparentDialog = this.this$0.getBottomSheetTransparentDialog();
                    Intrinsics.checkNotNull(bottomSheetTransparentDialog);
                    bottomSheetTransparentDialog.setProgress(this.$progress);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public ActivityDashboardNew$syncSuccess$2(ActivityDashboardNew activityDashboardNew) {
        this.f4671a = activityDashboardNew;
    }

    public static final void c(ActivityDashboardNew this$0) {
        BottomSheetDialogTransparentFullScreen bottomSheetTransparentDialog;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isFinishing()) {
            return;
        }
        if (this$0.getBottomSheetTransparentDialog() != null) {
            BottomSheetDialogTransparentFullScreen bottomSheetTransparentDialog2 = this$0.getBottomSheetTransparentDialog();
            boolean z = true;
            if (bottomSheetTransparentDialog2 == null || !bottomSheetTransparentDialog2.isShowing()) {
                z = false;
            }
            if (z && (bottomSheetTransparentDialog = this$0.getBottomSheetTransparentDialog()) != null) {
                bottomSheetTransparentDialog.dismiss();
            }
        }
        Toast.makeText(this$0, this$0.getString(R.string.settings_reset_sync_failed), 0).show();
    }

    public static final void d(ActivityDashboardNew this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isFinishing() || this$0.getBottomSheetTransparentDialog() == null) {
            return;
        }
        BottomSheetDialogTransparentFullScreen bottomSheetTransparentDialog = this$0.getBottomSheetTransparentDialog();
        Intrinsics.checkNotNull(bottomSheetTransparentDialog);
        if (bottomSheetTransparentDialog.isShowing()) {
            BottomSheetDialogTransparentFullScreen bottomSheetTransparentDialog2 = this$0.getBottomSheetTransparentDialog();
            Intrinsics.checkNotNull(bottomSheetTransparentDialog2);
            bottomSheetTransparentDialog2.dismiss();
            Toast.makeText(this$0, this$0.getString(R.string.settings_reset_sync_success), 0).show();
        }
    }

    @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
    public void onProgressUpdate(int i) {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.f4671a), Dispatchers.getMain(), null, new a(this.f4671a, i, null), 2, null);
    }

    @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
    public void onSettingSyncError() {
        this.f4671a.k1();
        final ActivityDashboardNew activityDashboardNew = this.f4671a;
        activityDashboardNew.runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.y0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDashboardNew$syncSuccess$2.c(ActivityDashboardNew.this);
            }
        });
    }

    @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
    public void onSettingsSyncCompleted() {
        BlePreferenceManager.savePreference(this.f4671a, CommonPreference.SHOULD_UPDATE_SETTINGS_BAND, Boolean.FALSE);
        CoveEventBusManager.getInstance().getEventBus().post(new SpSyncComplete());
        CoveEventBusManager.getInstance().getEventBus().post(new RcfSyncComplete());
        this.f4671a.k1();
        SettingsSyncHelper.Companion.getInstance(this.f4671a).setCurrentVolumeToWatch();
        LocalBroadcastManager.getInstance(this.f4671a).sendBroadcast(new Intent(GenericBandApplication.Companion.getACTION_SYNC_COMPLETE()));
        final ActivityDashboardNew activityDashboardNew = this.f4671a;
        activityDashboardNew.runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.z0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityDashboardNew$syncSuccess$2.d(ActivityDashboardNew.this);
            }
        });
        new WeatherUtils(this.f4671a).getLocationAndCallWeatherAPI(this.f4671a);
    }
}
