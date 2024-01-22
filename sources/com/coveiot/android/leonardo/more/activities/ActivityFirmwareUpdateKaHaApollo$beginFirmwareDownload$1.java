package com.coveiot.android.leonardo.more.activities;

import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$beginFirmwareDownload$1;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.FileUtils;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.FileDownloadTask;
import com.coveiot.utils.utility.LogHelper;
import java.io.File;
import java.io.IOException;
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
@DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$beginFirmwareDownload$1", f = "ActivityFirmwareUpdateKaHaApollo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateKaHaApollo$beginFirmwareDownload$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ SoftwareUpdateRes.DataBean.FirmwareBean $firmwareBean;
    public int label;
    public final /* synthetic */ ActivityFirmwareUpdateKaHaApollo this$0;

    /* renamed from: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$beginFirmwareDownload$1$2  reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass2 implements FileDownloadTask.DownloadFinishListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ActivityFirmwareUpdateKaHaApollo f4929a;
        public final /* synthetic */ SoftwareUpdateRes.DataBean.FirmwareBean b;
        public final /* synthetic */ FileDownloadTask c;

        @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$beginFirmwareDownload$1$2$onDownloadProgress$1", f = "ActivityFirmwareUpdateKaHaApollo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$beginFirmwareDownload$1$2$a */
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ int $progress;
            public int label;
            public final /* synthetic */ ActivityFirmwareUpdateKaHaApollo this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo, int i, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = activityFirmwareUpdateKaHaApollo;
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
                    ((TextView) this.this$0._$_findCachedViewById(R.id.tv_progress_percentage)).setText(this.this$0.getString(R.string.dfu_uploading_percentage, new Object[]{Boxing.boxInt(this.$progress)}));
                    TextView textView = this.this$0.r;
                    if (textView != null) {
                        textView.setText(this.this$0.getString(R.string.downloading));
                    }
                    ProgressBar progressBar = this.this$0.q;
                    if (progressBar != null) {
                        progressBar.setProgress(this.$progress);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public AnonymousClass2(ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo, SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean, FileDownloadTask fileDownloadTask) {
            this.f4929a = activityFirmwareUpdateKaHaApollo;
            this.b = firmwareBean;
            this.c = fileDownloadTask;
        }

        public static final void b(ActivityFirmwareUpdateKaHaApollo this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            SessionManager.getInstance(this$0).getConnectedDeviceMacAddress();
            SessionManager.getInstance(this$0).getConnectedDeviceName();
            this$0.initiateDFU(null, BleApiManager.getInstance(this$0).getBleApi().getMacAddress());
        }

        @Override // com.coveiot.utils.utility.FileDownloadTask.DownloadFinishListener
        public void onDownloadError(@NotNull String msg) {
            int i;
            int i2;
            Intrinsics.checkNotNullParameter(msg, "msg");
            i = this.f4929a.s;
            if (i < 3) {
                ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo = this.f4929a;
                i2 = activityFirmwareUpdateKaHaApollo.s;
                activityFirmwareUpdateKaHaApollo.s = i2 + 1;
                FileDownloadTask fileDownloadTask = this.c;
                String downloadUrl = this.b.getDownloadUrl();
                Intrinsics.checkNotNullExpressionValue(downloadUrl, "firmwareBean.downloadUrl");
                fileDownloadTask.download(downloadUrl);
                return;
            }
            ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo2 = this.f4929a;
            String string = activityFirmwareUpdateKaHaApollo2.getString(R.string.fw_download_failed);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_download_failed)");
            activityFirmwareUpdateKaHaApollo2.H(string, "");
        }

        @Override // com.coveiot.utils.utility.FileDownloadTask.DownloadFinishListener
        public void onDownloadFinish() {
            Handler handler;
            File filesDir = this.f4929a.getFilesDir();
            AppConstants appConstants = AppConstants.LEONARDO_FIRMWARE_FILE_NAME;
            File file = new File(filesDir, appConstants.getValue());
            FileUtils fileUtils = FileUtils.INSTANCE;
            String fileMd5Hash = this.b.getFileMd5Hash();
            Intrinsics.checkNotNullExpressionValue(fileMd5Hash, "firmwareBean.fileMd5Hash");
            if (fileUtils.isAuthenticFile(fileMd5Hash, file)) {
                try {
                    File filesDir2 = this.f4929a.getFilesDir();
                    Intrinsics.checkNotNullExpressionValue(filesDir2, "this@ActivityFirmwareUpdateKaHaApollo.filesDir");
                    File externalCacheDir = this.f4929a.getExternalCacheDir();
                    fileUtils.unZip(file, filesDir2, new File(externalCacheDir, appConstants.getValue() + ".binz"));
                    ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo = this.f4929a;
                    File externalCacheDir2 = this.f4929a.getExternalCacheDir();
                    activityFirmwareUpdateKaHaApollo.C = new File(externalCacheDir2, appConstants.getValue() + ".binz").getAbsolutePath();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ((TextView) this.f4929a._$_findCachedViewById(R.id.tv_progress_percentage)).setVisibility(8);
                handler = this.f4929a.w;
                final ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo2 = this.f4929a;
                handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.kb
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityFirmwareUpdateKaHaApollo$beginFirmwareDownload$1.AnonymousClass2.b(ActivityFirmwareUpdateKaHaApollo.this);
                    }
                }, 3000L);
                return;
            }
            ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo3 = this.f4929a;
            String string = activityFirmwareUpdateKaHaApollo3.getString(R.string.fw_authentication_failed);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_authentication_failed)");
            activityFirmwareUpdateKaHaApollo3.H(string, "");
        }

        @Override // com.coveiot.utils.utility.FileDownloadTask.DownloadFinishListener
        public void onDownloadProgress(int i) {
            String tag = this.f4929a.getTAG();
            LogHelper.d(tag, "Download progress: " + i);
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.f4929a), Dispatchers.getMain(), null, new a(this.f4929a, i, null), 2, null);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$beginFirmwareDownload$1$1", f = "ActivityFirmwareUpdateKaHaApollo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ ActivityFirmwareUpdateKaHaApollo this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = activityFirmwareUpdateKaHaApollo;
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
                ((LinearLayout) this.this$0._$_findCachedViewById(R.id.fw_upgrade_instructions_layout)).setVisibility(8);
                ((LinearLayout) this.this$0._$_findCachedViewById(R.id.fw_upgrade_progress_layout)).setVisibility(0);
                ((ConstraintLayout) this.this$0._$_findCachedViewById(R.id.fw_update_success_layout)).setVisibility(4);
                ((ConstraintLayout) this.this$0._$_findCachedViewById(R.id.fw_update_failed_layout)).setVisibility(4);
                TextView textView = this.this$0.r;
                if (textView != null) {
                    textView.setText(this.this$0.getString(R.string.downloading));
                }
                ((TextView) this.this$0._$_findCachedViewById(R.id.tv_fw_upgrade_info)).setText(this.this$0.getString(R.string.make_sure_the_device_s_battery_level_is_greater_than_30_and_the_device_is_close_to_phone));
                ((ImageView) this.this$0._$_findCachedViewById(R.id.fw_upgrading_image)).setImageResource(R.drawable.fw_upgrade_progress_illustration);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo$beginFirmwareDownload$1$3", f = "ActivityFirmwareUpdateKaHaApollo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ ActivityFirmwareUpdateKaHaApollo this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo, Continuation<? super b> continuation) {
            super(2, continuation);
            this.this$0 = activityFirmwareUpdateKaHaApollo;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo = this.this$0;
                Toast.makeText(activityFirmwareUpdateKaHaApollo, activityFirmwareUpdateKaHaApollo.getString(R.string.no_internet_connection), 0).show();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityFirmwareUpdateKaHaApollo$beginFirmwareDownload$1(ActivityFirmwareUpdateKaHaApollo activityFirmwareUpdateKaHaApollo, SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean, Continuation<? super ActivityFirmwareUpdateKaHaApollo$beginFirmwareDownload$1> continuation) {
        super(2, continuation);
        this.this$0 = activityFirmwareUpdateKaHaApollo;
        this.$firmwareBean = firmwareBean;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ActivityFirmwareUpdateKaHaApollo$beginFirmwareDownload$1(this.this$0, this.$firmwareBean, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ActivityFirmwareUpdateKaHaApollo$beginFirmwareDownload$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (AppUtils.isNetConnected(this.this$0)) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new a(this.this$0, null), 2, null);
                FileDownloadTask fileDownloadTask = new FileDownloadTask(this.this$0, AppConstants.LEONARDO_FIRMWARE_FILE_NAME.getValue());
                fileDownloadTask.setDownloadFinishListener(new AnonymousClass2(this.this$0, this.$firmwareBean, fileDownloadTask));
                String downloadUrl = this.$firmwareBean.getDownloadUrl();
                Intrinsics.checkNotNullExpressionValue(downloadUrl, "firmwareBean.downloadUrl");
                fileDownloadTask.download(downloadUrl);
            } else {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new b(this.this$0, null), 2, null);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
