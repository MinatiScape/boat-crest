package com.coveiot.android.leonardo.more.activities;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL$beginFirmwareDownload$1;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.FileUtils;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.FileDownloadTask;
import com.coveiot.utils.utility.LogHelper;
import com.jieli.watchtesttool.tool.upgrade.OTAManager;
import com.szabh.smable3.component.BleCache;
import com.szabh.smable3.component.BleConnector;
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
@DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL$beginFirmwareDownload$1", f = "ActivityFirmwareUpdateSmaJL.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateSmaJL$beginFirmwareDownload$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ SoftwareUpdateRes.DataBean.FirmwareBean $firmwareBean;
    public int label;
    public final /* synthetic */ ActivityFirmwareUpdateSmaJL this$0;

    /* renamed from: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL$beginFirmwareDownload$1$2  reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass2 implements FileDownloadTask.DownloadFinishListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ActivityFirmwareUpdateSmaJL f4950a;
        public final /* synthetic */ SoftwareUpdateRes.DataBean.FirmwareBean b;
        public final /* synthetic */ FileDownloadTask c;

        @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL$beginFirmwareDownload$1$2$onDownloadProgress$1", f = "ActivityFirmwareUpdateSmaJL.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL$beginFirmwareDownload$1$2$a */
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ int $progress;
            public int label;
            public final /* synthetic */ ActivityFirmwareUpdateSmaJL this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL, int i, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = activityFirmwareUpdateSmaJL;
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
                TextView textView;
                ProgressBar progressBar;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    ((TextView) this.this$0._$_findCachedViewById(R.id.tv_progress_percentage)).setText(this.this$0.getString(R.string.dfu_uploading_percentage, new Object[]{Boxing.boxInt(this.$progress)}));
                    textView = this.this$0.r;
                    if (textView != null) {
                        textView.setText(this.this$0.getString(R.string.downloading));
                    }
                    progressBar = this.this$0.q;
                    if (progressBar != null) {
                        progressBar.setProgress(this.$progress);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public AnonymousClass2(ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL, SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean, FileDownloadTask fileDownloadTask) {
            this.f4950a = activityFirmwareUpdateSmaJL;
            this.b = firmwareBean;
            this.c = fileDownloadTask;
        }

        public static final void b(ActivityFirmwareUpdateSmaJL this$0) {
            boolean z;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.N(ActivityFirmwareUpdateSmaJL.OTAStatus.NONE);
            BleConnector.INSTANCE.closeConnection(true);
            this$0.z();
            this$0.E();
            z = this$0.v;
            if (z) {
                LogHelper.d(this$0.getTAG(), "upgrade -> use dfuAddress");
                this$0.x(BleCache.INSTANCE.getMDfuAddress());
            } else {
                LogHelper.d(this$0.getTAG(), "upgrade -> use bleAddress");
                this$0.x(BleCache.INSTANCE.getMBleAddress());
            }
            this$0.N(ActivityFirmwareUpdateSmaJL.OTAStatus.UPGRADE_PREPARE);
        }

        @Override // com.coveiot.utils.utility.FileDownloadTask.DownloadFinishListener
        public void onDownloadError(@NotNull String msg) {
            int i;
            int i2;
            Intrinsics.checkNotNullParameter(msg, "msg");
            i = this.f4950a.s;
            if (i < 3) {
                ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL = this.f4950a;
                i2 = activityFirmwareUpdateSmaJL.s;
                activityFirmwareUpdateSmaJL.s = i2 + 1;
                FileDownloadTask fileDownloadTask = this.c;
                String downloadUrl = this.b.getDownloadUrl();
                Intrinsics.checkNotNullExpressionValue(downloadUrl, "firmwareBean.downloadUrl");
                fileDownloadTask.download(downloadUrl);
                return;
            }
            ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL2 = this.f4950a;
            String string = activityFirmwareUpdateSmaJL2.getString(R.string.fw_download_failed);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_download_failed)");
            activityFirmwareUpdateSmaJL2.K(string, "");
        }

        @Override // com.coveiot.utils.utility.FileDownloadTask.DownloadFinishListener
        public void onDownloadFinish() {
            Handler handler;
            File filesDir = this.f4950a.getFilesDir();
            AppConstants appConstants = AppConstants.SMA_FIRMWARE_FILE_NAME;
            File file = new File(filesDir, appConstants.getValue());
            FileUtils fileUtils = FileUtils.INSTANCE;
            String fileMd5Hash = this.b.getFileMd5Hash();
            Intrinsics.checkNotNullExpressionValue(fileMd5Hash, "firmwareBean.fileMd5Hash");
            if (fileUtils.isAuthenticFile(fileMd5Hash, file)) {
                try {
                    File filesDir2 = this.f4950a.getFilesDir();
                    Intrinsics.checkNotNullExpressionValue(filesDir2, "this@ActivityFirmwareUpdateSmaJL.filesDir");
                    File externalCacheDir = this.f4950a.getExternalCacheDir();
                    fileUtils.unZip(file, filesDir2, new File(externalCacheDir, appConstants.getValue() + OTAManager.OTA_FILE_SUFFIX));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler = this.f4950a.z;
                final ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL = this.f4950a;
                handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.md
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityFirmwareUpdateSmaJL$beginFirmwareDownload$1.AnonymousClass2.b(ActivityFirmwareUpdateSmaJL.this);
                    }
                }, 3000L);
                return;
            }
            ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL2 = this.f4950a;
            String string = activityFirmwareUpdateSmaJL2.getString(R.string.fw_authentication_failed);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_authentication_failed)");
            activityFirmwareUpdateSmaJL2.K(string, "");
        }

        @Override // com.coveiot.utils.utility.FileDownloadTask.DownloadFinishListener
        @SuppressLint({"StringFormatInvalid"})
        public void onDownloadProgress(int i) {
            String tag = this.f4950a.getTAG();
            LogHelper.d(tag, "Download progress: " + i);
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.f4950a), Dispatchers.getMain(), null, new a(this.f4950a, i, null), 2, null);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL$beginFirmwareDownload$1$1", f = "ActivityFirmwareUpdateSmaJL.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ ActivityFirmwareUpdateSmaJL this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = activityFirmwareUpdateSmaJL;
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
            TextView textView;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ((LinearLayout) this.this$0._$_findCachedViewById(R.id.fw_upgrade_instructions_layout)).setVisibility(8);
                ((LinearLayout) this.this$0._$_findCachedViewById(R.id.fw_upgrade_progress_layout)).setVisibility(0);
                ((ConstraintLayout) this.this$0._$_findCachedViewById(R.id.fw_update_success_layout)).setVisibility(4);
                ((ConstraintLayout) this.this$0._$_findCachedViewById(R.id.fw_update_failed_layout)).setVisibility(4);
                textView = this.this$0.r;
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

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL$beginFirmwareDownload$1$3", f = "ActivityFirmwareUpdateSmaJL.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ ActivityFirmwareUpdateSmaJL this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL, Continuation<? super b> continuation) {
            super(2, continuation);
            this.this$0 = activityFirmwareUpdateSmaJL;
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
                ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL = this.this$0;
                Toast.makeText(activityFirmwareUpdateSmaJL, activityFirmwareUpdateSmaJL.getString(R.string.no_internet_connection), 0).show();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityFirmwareUpdateSmaJL$beginFirmwareDownload$1(ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL, SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean, Continuation<? super ActivityFirmwareUpdateSmaJL$beginFirmwareDownload$1> continuation) {
        super(2, continuation);
        this.this$0 = activityFirmwareUpdateSmaJL;
        this.$firmwareBean = firmwareBean;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ActivityFirmwareUpdateSmaJL$beginFirmwareDownload$1(this.this$0, this.$firmwareBean, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ActivityFirmwareUpdateSmaJL$beginFirmwareDownload$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (!AppUtils.isNetConnected(this.this$0)) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new b(this.this$0, null), 2, null);
            } else {
                FileDownloadTask fileDownloadTask = new FileDownloadTask(this.this$0, AppConstants.SMA_FIRMWARE_FILE_NAME.getValue());
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new a(this.this$0, null), 2, null);
                fileDownloadTask.setDownloadFinishListener(new AnonymousClass2(this.this$0, this.$firmwareBean, fileDownloadTask));
                String downloadUrl = this.$firmwareBean.getDownloadUrl();
                Intrinsics.checkNotNullExpressionValue(downloadUrl, "firmwareBean.downloadUrl");
                fileDownloadTask.download(downloadUrl);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
