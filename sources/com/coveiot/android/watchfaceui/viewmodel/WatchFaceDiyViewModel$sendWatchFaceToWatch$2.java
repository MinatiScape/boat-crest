package com.coveiot.android.watchfaceui.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.error.CommandError;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.watchfacecore.model.ProgressBean;
import com.coveiot.android.watchfaceui.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$2", f = "WatchFaceDiyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
public final class WatchFaceDiyViewModel$sendWatchFaceToWatch$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ CustomWatchFaceFileImageRequest $changeWatchFaceRequest;
    public int label;
    public final /* synthetic */ WatchFaceDiyViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchFaceDiyViewModel$sendWatchFaceToWatch$2(WatchFaceDiyViewModel watchFaceDiyViewModel, CustomWatchFaceFileImageRequest customWatchFaceFileImageRequest, Continuation<? super WatchFaceDiyViewModel$sendWatchFaceToWatch$2> continuation) {
        super(2, continuation);
        this.this$0 = watchFaceDiyViewModel;
        this.$changeWatchFaceRequest = customWatchFaceFileImageRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new WatchFaceDiyViewModel$sendWatchFaceToWatch$2(this.this$0, this.$changeWatchFaceRequest, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((WatchFaceDiyViewModel$sendWatchFaceToWatch$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            BleApi bleApi = BleApiManager.getInstance(this.this$0.getContext()).getBleApi();
            CustomWatchFaceFileImageRequest changeWatchFaceRequest = this.$changeWatchFaceRequest;
            Intrinsics.checkNotNullExpressionValue(changeWatchFaceRequest, "changeWatchFaceRequest");
            final WatchFaceDiyViewModel watchFaceDiyViewModel = this.this$0;
            final CustomWatchFaceFileImageRequest customWatchFaceFileImageRequest = this.$changeWatchFaceRequest;
            bleApi.getData(changeWatchFaceRequest, new DataResultListener() { // from class: com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$2.1

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$2$1$onDataError$1", f = "WatchFaceDiyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$2$1$a */
                /* loaded from: classes8.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ BleBaseError $error;
                    public int label;
                    public final /* synthetic */ WatchFaceDiyViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(WatchFaceDiyViewModel watchFaceDiyViewModel, BleBaseError bleBaseError, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = watchFaceDiyViewModel;
                        this.$error = bleBaseError;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.this$0, this.$error, continuation);
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
                            ProgressBean value = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value);
                            value.setTitle(this.this$0.getContext().getString(R.string.updating_watchface));
                            ProgressBean value2 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value2);
                            value2.setProgress(0);
                            ProgressBean value3 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value3);
                            value3.setVisible(false);
                            this.this$0.getProgressLiveData().postValue(this.this$0.getProgressLiveData().getValue());
                            if (this.$error.getErrorCode() != null) {
                                Integer errorCode = this.$error.getErrorCode();
                                int i = CommandError.WATCH_BUSY.value;
                                if (errorCode != null && errorCode.intValue() == i) {
                                    this.this$0.getViewModelOnFailureListener().onFailure(this.this$0.getContext().getString(R.string.make_sure_no_running_activity));
                                    return Unit.INSTANCE;
                                }
                            }
                            this.this$0.getViewModelOnFailureListener().onFailure(this.this$0.getContext().getString(R.string.failed_message));
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$2$1$onDataResponse$1", f = "WatchFaceDiyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$2$1$b */
                /* loaded from: classes8.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ WatchFaceDiyViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(WatchFaceDiyViewModel watchFaceDiyViewModel, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.this$0 = watchFaceDiyViewModel;
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
                            ProgressBean value = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value);
                            value.setTitle(this.this$0.getContext().getString(R.string.updating_watchface));
                            ProgressBean value2 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value2);
                            value2.setProgress(100);
                            ProgressBean value3 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value3);
                            value3.setVisible(false);
                            this.this$0.getProgressLiveData().postValue(this.this$0.getProgressLiveData().getValue());
                            this.this$0.getViewModelOnSuccessListener().onDiyWatchFaceSuccess(true);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$2$1$onProgressUpdate$1", f = "WatchFaceDiyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$2$1$c */
                /* loaded from: classes8.dex */
                public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ ProgressData $progress;
                    public int label;
                    public final /* synthetic */ WatchFaceDiyViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public c(WatchFaceDiyViewModel watchFaceDiyViewModel, ProgressData progressData, Continuation<? super c> continuation) {
                        super(2, continuation);
                        this.this$0 = watchFaceDiyViewModel;
                        this.$progress = progressData;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new c(this.this$0, this.$progress, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            ProgressBean value = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value);
                            value.setTitle(this.this$0.getContext().getString(R.string.updating_watchface));
                            ProgressBean value2 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value2);
                            value2.setProgress(this.$progress.getProgress());
                            ProgressBean value3 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value3);
                            value3.setVisible(true);
                            this.this$0.getProgressLiveData().postValue(this.this$0.getProgressLiveData().getValue());
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                /* JADX WARN: Removed duplicated region for block: B:13:0x0031 A[Catch: Exception -> 0x003e, TRY_LEAVE, TryCatch #0 {Exception -> 0x003e, blocks: (B:3:0x001d, B:5:0x0021, B:7:0x0025, B:13:0x0031), top: B:19:0x001d }] */
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onDataError(@org.jetbrains.annotations.NotNull com.coveiot.android.bleabstract.response.BleBaseError r9) {
                    /*
                        r8 = this;
                        java.lang.String r0 = "error"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
                        com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel r0 = com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel.this
                        kotlinx.coroutines.CoroutineScope r1 = androidx.lifecycle.ViewModelKt.getViewModelScope(r0)
                        kotlinx.coroutines.MainCoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getMain()
                        com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$2$1$a r4 = new com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$2$1$a
                        com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel r0 = com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel.this
                        r7 = 0
                        r4.<init>(r0, r9, r7)
                        r3 = 0
                        r5 = 2
                        r6 = 0
                        kotlinx.coroutines.BuildersKt.launch$default(r1, r2, r3, r4, r5, r6)
                        com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest r9 = r2     // Catch: java.lang.Exception -> L3e
                        if (r9 == 0) goto L42
                        java.lang.String r9 = r9.watchFaceFilePath     // Catch: java.lang.Exception -> L3e
                        if (r9 == 0) goto L2e
                        boolean r9 = kotlin.text.m.isBlank(r9)     // Catch: java.lang.Exception -> L3e
                        if (r9 == 0) goto L2c
                        goto L2e
                    L2c:
                        r9 = 0
                        goto L2f
                    L2e:
                        r9 = 1
                    L2f:
                        if (r9 != 0) goto L42
                        java.io.File r9 = new java.io.File     // Catch: java.lang.Exception -> L3e
                        com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest r0 = r2     // Catch: java.lang.Exception -> L3e
                        java.lang.String r0 = r0.watchFaceFilePath     // Catch: java.lang.Exception -> L3e
                        r9.<init>(r0)     // Catch: java.lang.Exception -> L3e
                        r9.delete()     // Catch: java.lang.Exception -> L3e
                        goto L42
                    L3e:
                        r9 = move-exception
                        r9.printStackTrace()
                    L42:
                        com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel r9 = com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel.this
                        kotlinx.coroutines.CoroutineScope r0 = androidx.lifecycle.ViewModelKt.getViewModelScope(r9)
                        kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
                        r2 = 0
                        com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$2$1$onDataError$2 r3 = new com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$2$1$onDataError$2
                        com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel r9 = com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel.this
                        r3.<init>(r9, r7)
                        r4 = 2
                        r5 = 0
                        kotlinx.coroutines.BuildersKt.launch$default(r0, r1, r2, r3, r4, r5)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$2.AnonymousClass1.onDataError(com.coveiot.android.bleabstract.response.BleBaseError):void");
                }

                /* JADX WARN: Removed duplicated region for block: B:13:0x0030 A[Catch: Exception -> 0x003d, TRY_LEAVE, TryCatch #0 {Exception -> 0x003d, blocks: (B:3:0x001c, B:5:0x0020, B:7:0x0024, B:13:0x0030), top: B:18:0x001c }] */
                /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onDataResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.bleabstract.response.BleBaseResponse r7) {
                    /*
                        r6 = this;
                        java.lang.String r0 = "response"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
                        com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel r7 = com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel.this
                        kotlinx.coroutines.CoroutineScope r0 = androidx.lifecycle.ViewModelKt.getViewModelScope(r7)
                        kotlinx.coroutines.MainCoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getMain()
                        com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$2$1$b r3 = new com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$2$1$b
                        com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel r7 = com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel.this
                        r2 = 0
                        r3.<init>(r7, r2)
                        r4 = 2
                        r5 = 0
                        kotlinx.coroutines.BuildersKt.launch$default(r0, r1, r2, r3, r4, r5)
                        com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest r7 = r2     // Catch: java.lang.Exception -> L3d
                        if (r7 == 0) goto L41
                        java.lang.String r7 = r7.watchFaceFilePath     // Catch: java.lang.Exception -> L3d
                        if (r7 == 0) goto L2d
                        boolean r7 = kotlin.text.m.isBlank(r7)     // Catch: java.lang.Exception -> L3d
                        if (r7 == 0) goto L2b
                        goto L2d
                    L2b:
                        r7 = 0
                        goto L2e
                    L2d:
                        r7 = 1
                    L2e:
                        if (r7 != 0) goto L41
                        java.io.File r7 = new java.io.File     // Catch: java.lang.Exception -> L3d
                        com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest r0 = r2     // Catch: java.lang.Exception -> L3d
                        java.lang.String r0 = r0.watchFaceFilePath     // Catch: java.lang.Exception -> L3d
                        r7.<init>(r0)     // Catch: java.lang.Exception -> L3d
                        r7.delete()     // Catch: java.lang.Exception -> L3d
                        goto L41
                    L3d:
                        r7 = move-exception
                        r7.printStackTrace()
                    L41:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$sendWatchFaceToWatch$2.AnonymousClass1.onDataResponse(com.coveiot.android.bleabstract.response.BleBaseResponse):void");
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                    e.e(ViewModelKt.getViewModelScope(WatchFaceDiyViewModel.this), Dispatchers.getMain(), null, new c(WatchFaceDiyViewModel.this, progress, null), 2, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
