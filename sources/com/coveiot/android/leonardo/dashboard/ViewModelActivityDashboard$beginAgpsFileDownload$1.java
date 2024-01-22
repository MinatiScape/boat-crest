package com.coveiot.android.leonardo.dashboard;

import android.content.Context;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.dashboard.AgpsFileDownloadTask;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.io.File;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$beginAgpsFileDownload$1", f = "ViewModelActivityDashboard.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class ViewModelActivityDashboard$beginAgpsFileDownload$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Ref.ObjectRef<String> $fileName;
    public final /* synthetic */ String $sUrl;
    public int label;
    public final /* synthetic */ ViewModelActivityDashboard this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewModelActivityDashboard$beginAgpsFileDownload$1(ViewModelActivityDashboard viewModelActivityDashboard, Ref.ObjectRef<String> objectRef, String str, Continuation<? super ViewModelActivityDashboard$beginAgpsFileDownload$1> continuation) {
        super(2, continuation);
        this.this$0 = viewModelActivityDashboard;
        this.$fileName = objectRef;
        this.$sUrl = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ViewModelActivityDashboard$beginAgpsFileDownload$1(this.this$0, this.$fileName, this.$sUrl, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ViewModelActivityDashboard$beginAgpsFileDownload$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Context context = this.this$0.q;
            Context context2 = null;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context = null;
            }
            if (AppUtils.isNetConnected(context)) {
                Context context3 = this.this$0.q;
                if (context3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                } else {
                    context2 = context3;
                }
                AgpsFileDownloadTask agpsFileDownloadTask = new AgpsFileDownloadTask(context2, this.$fileName.element);
                final ViewModelActivityDashboard viewModelActivityDashboard = this.this$0;
                final Ref.ObjectRef<String> objectRef = this.$fileName;
                agpsFileDownloadTask.setDownloadFinishListener(new AgpsFileDownloadTask.DownloadFinishListener() { // from class: com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboard$beginAgpsFileDownload$1.1
                    @Override // com.coveiot.android.leonardo.dashboard.AgpsFileDownloadTask.DownloadFinishListener
                    public void onDownloadError(@NotNull String errMsg) {
                        Intrinsics.checkNotNullParameter(errMsg, "errMsg");
                        LogHelper.d(ViewModelActivityDashboard.this.e, "agps_download_failed");
                    }

                    @Override // com.coveiot.android.leonardo.dashboard.AgpsFileDownloadTask.DownloadFinishListener
                    public void onDownloadFinish() {
                        Context context4 = ViewModelActivityDashboard.this.q;
                        if (context4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                            context4 = null;
                        }
                        LogHelper.d(ViewModelActivityDashboard.this.e, new File(context4.getFilesDir(), objectRef.element).getAbsolutePath());
                    }

                    @Override // com.coveiot.android.leonardo.dashboard.AgpsFileDownloadTask.DownloadFinishListener
                    public void onDownloadProgress(int i) {
                        String str = ViewModelActivityDashboard.this.e;
                        LogHelper.d(str, "Download progress: " + i);
                    }
                });
                agpsFileDownloadTask.download(this.$sUrl);
            } else {
                ViewModelListener viewModelListener = this.this$0.getViewModelListener();
                Context context4 = this.this$0.q;
                if (context4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mContext");
                } else {
                    context2 = context4;
                }
                String string = context2.getString(R.string.no_internet_connection);
                Intrinsics.checkNotNullExpressionValue(string, "mContext.getString(R.str…g.no_internet_connection)");
                viewModelListener.onDataFailure(string);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
