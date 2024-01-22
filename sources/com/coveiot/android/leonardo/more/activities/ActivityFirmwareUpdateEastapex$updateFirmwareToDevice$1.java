package com.coveiot.android.leonardo.more.activities;

import android.content.res.Resources;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import com.apex.bluetooth.callback.OtaCallback;
import com.coveiot.android.boat.R;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import com.coveiot.utils.utility.LogHelper;
import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateEastapex$updateFirmwareToDevice$1 implements OtaCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityFirmwareUpdateEastapex f4918a;

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateEastapex$updateFirmwareToDevice$1$progress$1", f = "ActivityFirmwareUpdateEastapex.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ int $p0;
        public int label;
        public final /* synthetic */ ActivityFirmwareUpdateEastapex this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(ActivityFirmwareUpdateEastapex activityFirmwareUpdateEastapex, int i, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = activityFirmwareUpdateEastapex;
            this.$p0 = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.this$0, this.$p0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            ProgressBar progressBar;
            ProgressBar progressBar2;
            TextView textView;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                progressBar = this.this$0.q;
                if (progressBar != null) {
                    progressBar.setIndeterminate(false);
                }
                progressBar2 = this.this$0.q;
                if (progressBar2 != null) {
                    progressBar2.setProgress(this.$p0);
                }
                textView = this.this$0.r;
                if (textView != null) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = this.this$0.getResources().getString(R.string.dfu_uploading_percentage);
                    Intrinsics.checkNotNullExpressionValue(string, "this@ActivityFirmwareUpd…dfu_uploading_percentage)");
                    String format = String.format(string, Arrays.copyOf(new Object[]{Boxing.boxInt(this.$p0)}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    textView.setText(format);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public ActivityFirmwareUpdateEastapex$updateFirmwareToDevice$1(ActivityFirmwareUpdateEastapex activityFirmwareUpdateEastapex) {
        this.f4918a = activityFirmwareUpdateEastapex;
    }

    public static final void b(ActivityFirmwareUpdateEastapex this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.D();
    }

    @Override // com.apex.bluetooth.callback.EABleCallback
    public void mutualFail(int i) {
        SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
        String tag = this.f4918a.getTAG();
        LogHelper.d(tag, "Firmware update failed " + i + ' ');
        if (this.f4918a.isUpdatedSuccessfully()) {
            return;
        }
        ActivityFirmwareUpdateEastapex activityFirmwareUpdateEastapex = this.f4918a;
        Resources resources = activityFirmwareUpdateEastapex.getResources();
        Object[] objArr = new Object[1];
        firmwareBean = this.f4918a.t;
        if (firmwareBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
            firmwareBean = null;
        }
        objArr[0] = firmwareBean.getUpdateVersion();
        String string = resources.getString(R.string.fw_update_failure, objArr);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…rmwareBean.updateVersion)");
        activityFirmwareUpdateEastapex.G(string, "");
    }

    @Override // com.apex.bluetooth.callback.OtaCallback
    public void progress(int i) {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.f4918a), Dispatchers.getMain(), null, new a(this.f4918a, i, null), 2, null);
    }

    @Override // com.apex.bluetooth.callback.OtaCallback
    public void success() {
        Handler handler;
        LogHelper.d(this.f4918a.getTAG(), "Firmware update success ");
        this.f4918a.setUpdatedSuccessfully(true);
        handler = this.f4918a.u;
        final ActivityFirmwareUpdateEastapex activityFirmwareUpdateEastapex = this.f4918a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.ma
            @Override // java.lang.Runnable
            public final void run() {
                ActivityFirmwareUpdateEastapex$updateFirmwareToDevice$1.b(ActivityFirmwareUpdateEastapex.this);
            }
        }, 7000L);
    }
}
