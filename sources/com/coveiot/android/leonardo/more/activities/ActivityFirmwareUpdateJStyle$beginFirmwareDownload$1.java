package com.coveiot.android.leonardo.more.activities;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.request.OTAModeRequest;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.FileUtils;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.FileDownloadTask;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.io.File;
import java.io.IOException;
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
@DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateJStyle$beginFirmwareDownload$1", f = "ActivityFirmwareUpdateJStyle.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateJStyle$beginFirmwareDownload$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ SoftwareUpdateRes.DataBean.FirmwareBean $firmwareBean;
    public int label;
    public final /* synthetic */ ActivityFirmwareUpdateJStyle this$0;

    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateJStyle$beginFirmwareDownload$1$2", f = "ActivityFirmwareUpdateJStyle.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ ActivityFirmwareUpdateJStyle this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = activityFirmwareUpdateJStyle;
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
                ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle = this.this$0;
                Toast.makeText(activityFirmwareUpdateJStyle, activityFirmwareUpdateJStyle.getString(R.string.no_internet_connection), 0).show();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityFirmwareUpdateJStyle$beginFirmwareDownload$1(ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle, SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean, Continuation<? super ActivityFirmwareUpdateJStyle$beginFirmwareDownload$1> continuation) {
        super(2, continuation);
        this.this$0 = activityFirmwareUpdateJStyle;
        this.$firmwareBean = firmwareBean;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ActivityFirmwareUpdateJStyle$beginFirmwareDownload$1(this.this$0, this.$firmwareBean, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ActivityFirmwareUpdateJStyle$beginFirmwareDownload$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (!AppUtils.isNetConnected(this.this$0)) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new a(this.this$0, null), 2, null);
            } else {
                final FileDownloadTask fileDownloadTask = new FileDownloadTask(this.this$0, AppConstants.JSTYLE_FIRMWARE_FILE_NAME.getValue());
                final ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle = this.this$0;
                final SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean = this.$firmwareBean;
                fileDownloadTask.setDownloadFinishListener(new FileDownloadTask.DownloadFinishListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateJStyle$beginFirmwareDownload$1.1

                    @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateJStyle$beginFirmwareDownload$1$1$onDownloadProgress$1", f = "ActivityFirmwareUpdateJStyle.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateJStyle$beginFirmwareDownload$1$1$a */
                    /* loaded from: classes5.dex */
                    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public final /* synthetic */ int $progress;
                        public int label;
                        public final /* synthetic */ ActivityFirmwareUpdateJStyle this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public a(ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle, int i, Continuation<? super a> continuation) {
                            super(2, continuation);
                            this.this$0 = activityFirmwareUpdateJStyle;
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
                            ProgressBar progressBar;
                            TextView textView;
                            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                            if (this.label == 0) {
                                ResultKt.throwOnFailure(obj);
                                BottomSheetDialog bottomSheetDialog = this.this$0.t;
                                BottomSheetDialog bottomSheetDialog2 = null;
                                if (bottomSheetDialog == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                                    bottomSheetDialog = null;
                                }
                                if (!bottomSheetDialog.isShowing()) {
                                    BottomSheetDialog bottomSheetDialog3 = this.this$0.t;
                                    if (bottomSheetDialog3 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("updateProgressBottomSheetDialog");
                                    } else {
                                        bottomSheetDialog2 = bottomSheetDialog3;
                                    }
                                    bottomSheetDialog2.show();
                                    textView = this.this$0.r;
                                    if (textView != null) {
                                        textView.setText(this.this$0.getString(R.string.downloading_firmware));
                                    }
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

                    @Override // com.coveiot.utils.utility.FileDownloadTask.DownloadFinishListener
                    public void onDownloadError(@NotNull String msg) {
                        int i;
                        int i2;
                        Intrinsics.checkNotNullParameter(msg, "msg");
                        i = ActivityFirmwareUpdateJStyle.this.s;
                        if (i < 3) {
                            ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle2 = ActivityFirmwareUpdateJStyle.this;
                            i2 = activityFirmwareUpdateJStyle2.s;
                            activityFirmwareUpdateJStyle2.s = i2 + 1;
                            FileDownloadTask fileDownloadTask2 = fileDownloadTask;
                            String downloadUrl = firmwareBean.getDownloadUrl();
                            Intrinsics.checkNotNullExpressionValue(downloadUrl, "firmwareBean.downloadUrl");
                            fileDownloadTask2.download(downloadUrl);
                            return;
                        }
                        ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle3 = ActivityFirmwareUpdateJStyle.this;
                        String string = activityFirmwareUpdateJStyle3.getString(R.string.fw_download_failed);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_download_failed)");
                        activityFirmwareUpdateJStyle3.B(string, "", false);
                    }

                    @Override // com.coveiot.utils.utility.FileDownloadTask.DownloadFinishListener
                    public void onDownloadFinish() {
                        File file = new File(ActivityFirmwareUpdateJStyle.this.getFilesDir(), AppConstants.JSTYLE_FIRMWARE_FILE_NAME.getValue());
                        FileUtils fileUtils = FileUtils.INSTANCE;
                        String fileMd5Hash = firmwareBean.getFileMd5Hash();
                        Intrinsics.checkNotNullExpressionValue(fileMd5Hash, "firmwareBean.fileMd5Hash");
                        if (fileUtils.isAuthenticFile(fileMd5Hash, file)) {
                            try {
                                File filesDir = ActivityFirmwareUpdateJStyle.this.getFilesDir();
                                Intrinsics.checkNotNullExpressionValue(filesDir, "this@ActivityFirmwareUpdateJStyle.getFilesDir()");
                                FileUtils.unZip$default(fileUtils, file, filesDir, null, 4, null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            BleApiManager.getInstance(ActivityFirmwareUpdateJStyle.this).getBleApi().setUserSettings(new OTAModeRequest(), new ActivityFirmwareUpdateJStyle$beginFirmwareDownload$1$1$onDownloadFinish$1(ActivityFirmwareUpdateJStyle.this));
                            return;
                        }
                        ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle2 = ActivityFirmwareUpdateJStyle.this;
                        String string = activityFirmwareUpdateJStyle2.getString(R.string.fw_authentication_failed);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fw_authentication_failed)");
                        activityFirmwareUpdateJStyle2.B(string, "", false);
                    }

                    @Override // com.coveiot.utils.utility.FileDownloadTask.DownloadFinishListener
                    public void onDownloadProgress(int i) {
                        String tag = ActivityFirmwareUpdateJStyle.this.getTAG();
                        LogHelper.d(tag, "Download progress: " + i);
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityFirmwareUpdateJStyle.this), Dispatchers.getMain(), null, new a(ActivityFirmwareUpdateJStyle.this, i, null), 2, null);
                    }
                });
                String downloadUrl = this.$firmwareBean.getDownloadUrl();
                Intrinsics.checkNotNullExpressionValue(downloadUrl, "firmwareBean.downloadUrl");
                fileDownloadTask.download(downloadUrl);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
