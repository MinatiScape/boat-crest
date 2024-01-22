package com.coveiot.android.watchfaceui.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.watchfacecore.model.ProgressBean;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.android.watchfacecore.utils.FileUtils;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.FileDownloadTask;
import java.io.File;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1", f = "WatchFaceDiyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
public final class WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String $fileName;
    public final /* synthetic */ String $sUrl;
    public final /* synthetic */ WatchFaceBean $watchFaceBean;
    public int label;
    public final /* synthetic */ WatchFaceDiyViewModel this$0;

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1$1", f = "WatchFaceDiyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ WatchFaceDiyViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(WatchFaceDiyViewModel watchFaceDiyViewModel, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = watchFaceDiyViewModel;
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
                ProgressBean value = this.this$0.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value);
                value.setTitle(this.this$0.getContext().getString(R.string.downloading));
                ProgressBean value2 = this.this$0.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value2);
                value2.setProgress(0);
                ProgressBean value3 = this.this$0.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value3);
                value3.setVisible(true);
                this.this$0.getProgressLiveData().postValue(this.this$0.getProgressLiveData().getValue());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1$3", f = "WatchFaceDiyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                this.this$0.getViewModelOnFailureListener().onFailure(this.this$0.getContext().getString(R.string.band_not_connected));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1$4", f = "WatchFaceDiyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ WatchFaceDiyViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(WatchFaceDiyViewModel watchFaceDiyViewModel, Continuation<? super c> continuation) {
            super(2, continuation);
            this.this$0 = watchFaceDiyViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(this.this$0, continuation);
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
                this.this$0.getViewModelOnFailureListener().onFailure(this.this$0.getContext().getString(R.string.no_internet_connection));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1(WatchFaceDiyViewModel watchFaceDiyViewModel, String str, String str2, WatchFaceBean watchFaceBean, Continuation<? super WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1> continuation) {
        super(2, continuation);
        this.this$0 = watchFaceDiyViewModel;
        this.$fileName = str;
        this.$sUrl = str2;
        this.$watchFaceBean = watchFaceBean;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1(this.this$0, this.$fileName, this.$sUrl, this.$watchFaceBean, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (!AppUtils.isNetConnected(this.this$0.getContext())) {
                e.e(ViewModelKt.getViewModelScope(this.this$0), Dispatchers.getMain(), null, new c(this.this$0, null), 2, null);
            } else if (BleApiManager.getInstance(this.this$0.getContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                e.e(ViewModelKt.getViewModelScope(this.this$0), Dispatchers.getMain(), null, new a(this.this$0, null), 2, null);
                FileDownloadTask fileDownloadTask = new FileDownloadTask(this.this$0.getContext(), this.$fileName);
                final WatchFaceDiyViewModel watchFaceDiyViewModel = this.this$0;
                final String str = this.$fileName;
                final WatchFaceBean watchFaceBean = this.$watchFaceBean;
                fileDownloadTask.setDownloadFinishListener(new FileDownloadTask.DownloadFinishListener() { // from class: com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1.2

                    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1$2$onDownloadError$1", f = "WatchFaceDiyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1$2$a */
                    /* loaded from: classes8.dex */
                    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public final /* synthetic */ String $errMsg;
                        public int label;
                        public final /* synthetic */ WatchFaceDiyViewModel this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public a(WatchFaceDiyViewModel watchFaceDiyViewModel, String str, Continuation<? super a> continuation) {
                            super(2, continuation);
                            this.this$0 = watchFaceDiyViewModel;
                            this.$errMsg = str;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @NotNull
                        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                            return new a(this.this$0, this.$errMsg, continuation);
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
                                value.setTitle(this.this$0.getContext().getString(R.string.downloading));
                                ProgressBean value2 = this.this$0.getProgressLiveData().getValue();
                                Intrinsics.checkNotNull(value2);
                                value2.setProgress(0);
                                ProgressBean value3 = this.this$0.getProgressLiveData().getValue();
                                Intrinsics.checkNotNull(value3);
                                value3.setVisible(false);
                                this.this$0.getProgressLiveData().postValue(this.this$0.getProgressLiveData().getValue());
                                this.this$0.getViewModelOnFailureListener().onFailure(this.$errMsg);
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1$2$onDownloadFinish$1", f = "WatchFaceDiyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1$2$b */
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
                                value.setTitle(this.this$0.getContext().getString(R.string.downloading));
                                ProgressBean value2 = this.this$0.getProgressLiveData().getValue();
                                Intrinsics.checkNotNull(value2);
                                value2.setProgress(100);
                                ProgressBean value3 = this.this$0.getProgressLiveData().getValue();
                                Intrinsics.checkNotNull(value3);
                                value3.setVisible(false);
                                this.this$0.getProgressLiveData().postValue(this.this$0.getProgressLiveData().getValue());
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1$2$onDownloadFinish$2", f = "WatchFaceDiyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1$2$c */
                    /* loaded from: classes8.dex */
                    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public int label;
                        public final /* synthetic */ WatchFaceDiyViewModel this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public c(WatchFaceDiyViewModel watchFaceDiyViewModel, Continuation<? super c> continuation) {
                            super(2, continuation);
                            this.this$0 = watchFaceDiyViewModel;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @NotNull
                        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                            return new c(this.this$0, continuation);
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
                                this.this$0.getViewModelOnFailureListener().onFailure(this.this$0.getContext().getString(R.string.my_watchface_authentication_failed));
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1$2$onDownloadProgress$1", f = "WatchFaceDiyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel$downloadWatchFaceFromServerSendToWatch$1$2$d */
                    /* loaded from: classes8.dex */
                    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public final /* synthetic */ int $progress;
                        public int label;
                        public final /* synthetic */ WatchFaceDiyViewModel this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public d(WatchFaceDiyViewModel watchFaceDiyViewModel, int i, Continuation<? super d> continuation) {
                            super(2, continuation);
                            this.this$0 = watchFaceDiyViewModel;
                            this.$progress = i;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @NotNull
                        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                            return new d(this.this$0, this.$progress, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        @Nullable
                        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                            return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @Nullable
                        public final Object invokeSuspend(@NotNull Object obj) {
                            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                            if (this.label == 0) {
                                ResultKt.throwOnFailure(obj);
                                ProgressBean value = this.this$0.getProgressLiveData().getValue();
                                Intrinsics.checkNotNull(value);
                                value.setTitle(this.this$0.getContext().getString(R.string.downloading));
                                ProgressBean value2 = this.this$0.getProgressLiveData().getValue();
                                Intrinsics.checkNotNull(value2);
                                value2.setProgress(this.$progress);
                                ProgressBean value3 = this.this$0.getProgressLiveData().getValue();
                                Intrinsics.checkNotNull(value3);
                                value3.setVisible(true);
                                this.this$0.getProgressLiveData().postValue(this.this$0.getProgressLiveData().getValue());
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    @Override // com.coveiot.utils.utility.FileDownloadTask.DownloadFinishListener
                    public void onDownloadError(@NotNull String errMsg) {
                        Intrinsics.checkNotNullParameter(errMsg, "errMsg");
                        e.e(ViewModelKt.getViewModelScope(WatchFaceDiyViewModel.this), Dispatchers.getMain(), null, new a(WatchFaceDiyViewModel.this, errMsg, null), 2, null);
                    }

                    @Override // com.coveiot.utils.utility.FileDownloadTask.DownloadFinishListener
                    public void onDownloadFinish() {
                        e.e(ViewModelKt.getViewModelScope(WatchFaceDiyViewModel.this), Dispatchers.getMain(), null, new b(WatchFaceDiyViewModel.this, null), 2, null);
                        File file = new File(WatchFaceDiyViewModel.this.getContext().getFilesDir(), str);
                        WatchFaceBean userSelectedWatchFace = WatchFaceDiyViewModel.this.getUserSelectedWatchFace();
                        Intrinsics.checkNotNull(userSelectedWatchFace);
                        if (userSelectedWatchFace.getFileMd5Hash() != null) {
                            FileUtils fileUtils = FileUtils.INSTANCE;
                            WatchFaceBean userSelectedWatchFace2 = WatchFaceDiyViewModel.this.getUserSelectedWatchFace();
                            Intrinsics.checkNotNull(userSelectedWatchFace2);
                            String fileMd5Hash = userSelectedWatchFace2.getFileMd5Hash();
                            Intrinsics.checkNotNull(fileMd5Hash);
                            if (fileUtils.isAuthenticFile(fileMd5Hash, file)) {
                                WatchFaceDiyViewModel watchFaceDiyViewModel2 = WatchFaceDiyViewModel.this;
                                String absolutePath = file.getAbsolutePath();
                                Intrinsics.checkNotNullExpressionValue(absolutePath, "file.absolutePath");
                                watchFaceDiyViewModel2.i(absolutePath, watchFaceBean);
                                return;
                            }
                        }
                        e.e(ViewModelKt.getViewModelScope(WatchFaceDiyViewModel.this), Dispatchers.getMain(), null, new c(WatchFaceDiyViewModel.this, null), 2, null);
                    }

                    @Override // com.coveiot.utils.utility.FileDownloadTask.DownloadFinishListener
                    public void onDownloadProgress(int i) {
                        e.e(ViewModelKt.getViewModelScope(WatchFaceDiyViewModel.this), Dispatchers.getMain(), null, new d(WatchFaceDiyViewModel.this, i, null), 2, null);
                    }
                });
                try {
                    new File(this.this$0.getContext().getFilesDir(), this.$fileName).delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fileDownloadTask.download(this.$sUrl);
            } else {
                e.e(ViewModelKt.getViewModelScope(this.this$0), Dispatchers.getMain(), null, new b(this.this$0, null), 2, null);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
