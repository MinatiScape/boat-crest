package com.coveiot.android.watchfaceui.viewmodel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest;
import com.coveiot.android.watchfacecore.model.ProgressBean;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.android.watchfacecore.utils.FileUtils;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.preference.WatchFacePreferenceManager;
import com.coveiot.android.watchfaceui.utils.KHWatchFaceModifier;
import com.coveiot.android.watchfaceui.utils.Utils;
import com.coveiot.covepreferences.UserDataManager;
import com.jstyle.blesdk1860.constant.BleConst;
import com.realsil.sdk.dfu.DfuException;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;
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
@DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
public final class WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ int $backgroundType;
    public final /* synthetic */ Uri $it;
    public final /* synthetic */ int $watchFaceId;
    public int label;
    public final /* synthetic */ WatchFaceBackgroundViewModel this$0;

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$2", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Exception $e;
        public int label;
        public final /* synthetic */ WatchFaceBackgroundViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(WatchFaceBackgroundViewModel watchFaceBackgroundViewModel, Exception exc, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = watchFaceBackgroundViewModel;
            this.$e = exc;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.this$0, this.$e, continuation);
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
                this.this$0.getViewModelOnFailureListener().onFailure(this.$e.getLocalizedMessage());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$3", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;
        public final /* synthetic */ WatchFaceBackgroundViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(WatchFaceBackgroundViewModel watchFaceBackgroundViewModel, Continuation<? super b> continuation) {
            super(2, continuation);
            this.this$0 = watchFaceBackgroundViewModel;
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
                value2.setProgress(0);
                ProgressBean value3 = this.this$0.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value3);
                value3.setVisible(false);
                this.this$0.getProgressLiveData().postValue(this.this$0.getProgressLiveData().getValue());
                this.this$0.getViewModelOnFailureListener().onFailure(this.this$0.getContext().getString(R.string.failed_message));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2(int i, WatchFaceBackgroundViewModel watchFaceBackgroundViewModel, Uri uri, int i2, Continuation<? super WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2> continuation) {
        super(2, continuation);
        this.$backgroundType = i;
        this.this$0 = watchFaceBackgroundViewModel;
        this.$it = uri;
        this.$watchFaceId = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2(this.$backgroundType, this.this$0, this.$it, this.$watchFaceId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        File a2;
        Bitmap resizeBitmap;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$backgroundType == 1) {
                a2 = Utils.INSTANCE.isCAULCDevice(this.this$0.getContext()) ? this.this$0.a("diy_02", R.raw.ca5_ulc_diy_2) : this.this$0.a("diy_02", R.raw.ca3_diy_02);
            } else {
                a2 = Utils.INSTANCE.isCAULCDevice(this.this$0.getContext()) ? this.this$0.a("diy_01", R.raw.ca5_ulc_diy_1) : this.this$0.a("diy_01", R.raw.ca3_diy_01);
            }
            if (a2 != null) {
                Uri uri = this.$it;
                final WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = this.this$0;
                final int i = this.$backgroundType;
                int i2 = this.$watchFaceId;
                File file = new File(uri.getPath());
                int i3 = 240;
                int i4 = DfuException.ERROR_ENTER_OTA_MODE_FAILED;
                if (Utils.INSTANCE.isCAULCDevice(watchFaceBackgroundViewModel.getContext())) {
                    i3 = 144;
                    i4 = 168;
                }
                InputStream openInputStream = watchFaceBackgroundViewModel.getContext().getContentResolver().openInputStream(uri);
                if (openInputStream == null) {
                    return Unit.INSTANCE;
                }
                Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream);
                if (decodeStream == null) {
                    e.e(ViewModelKt.getViewModelScope(watchFaceBackgroundViewModel), Dispatchers.getMain(), null, new b(watchFaceBackgroundViewModel, null), 2, null);
                } else {
                    FileUtils fileUtils = FileUtils.INSTANCE;
                    Bitmap resizeBitmap2 = fileUtils.resizeBitmap(decodeStream, i3, i4);
                    if (i == 1) {
                        Bitmap decodeResource = BitmapFactory.decodeResource(watchFaceBackgroundViewModel.getContext().getResources(), R.drawable.ca3_diy_watch_face_place_holder_transparent_blackf);
                        Intrinsics.checkNotNullExpressionValue(decodeResource, "decodeResource(\n        …                        )");
                        resizeBitmap = fileUtils.resizeBitmap(decodeResource, i3, i4);
                    } else {
                        Bitmap decodeResource2 = BitmapFactory.decodeResource(watchFaceBackgroundViewModel.getContext().getResources(), R.drawable.ca3_diy_watch_face_place_holder_transparent_whitef);
                        Intrinsics.checkNotNullExpressionValue(decodeResource2, "decodeResource(\n        …                        )");
                        resizeBitmap = fileUtils.resizeBitmap(decodeResource2, i3, i4);
                    }
                    try {
                        File file2 = new File(watchFaceBackgroundViewModel.getContext().getFilesDir() + File.separator + "diy_output_01.bin");
                        KHWatchFaceModifier kHWatchFaceModifier = KHWatchFaceModifier.INSTANCE;
                        String absolutePath = a2.getAbsolutePath();
                        Intrinsics.checkNotNullExpressionValue(absolutePath, "binFilePath.absolutePath");
                        String absolutePath2 = file.getAbsolutePath();
                        Intrinsics.checkNotNullExpressionValue(absolutePath2, "imageFilePath.absolutePath");
                        Intrinsics.checkNotNull(resizeBitmap2);
                        Intrinsics.checkNotNull(resizeBitmap);
                        kHWatchFaceModifier.generateNewBinFile(absolutePath, absolutePath2, kHWatchFaceModifier.putOverlay(resizeBitmap2, resizeBitmap), file2);
                        final CustomWatchFaceFileImageRequest changeWatchFaceRequest = new CustomWatchFaceFileImageRequest.Builder().setWatchFaceFilePath(file2.getAbsolutePath()).setWatchFaceID(i2).build();
                        BleApi bleApi = BleApiManager.getInstance(watchFaceBackgroundViewModel.getContext()).getBleApi();
                        Intrinsics.checkNotNullExpressionValue(changeWatchFaceRequest, "changeWatchFaceRequest");
                        bleApi.getData(changeWatchFaceRequest, new DataResultListener() { // from class: com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1

                            @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1$onDataError$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                            /* loaded from: classes8.dex */
                            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                public int label;
                                public final /* synthetic */ WatchFaceBackgroundViewModel this$0;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                public a(WatchFaceBackgroundViewModel watchFaceBackgroundViewModel, Continuation<? super a> continuation) {
                                    super(2, continuation);
                                    this.this$0 = watchFaceBackgroundViewModel;
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
                                        value.setTitle(this.this$0.getContext().getString(R.string.updating_watchface));
                                        ProgressBean value2 = this.this$0.getProgressLiveData().getValue();
                                        Intrinsics.checkNotNull(value2);
                                        value2.setProgress(0);
                                        ProgressBean value3 = this.this$0.getProgressLiveData().getValue();
                                        Intrinsics.checkNotNull(value3);
                                        value3.setVisible(false);
                                        this.this$0.getProgressLiveData().postValue(this.this$0.getProgressLiveData().getValue());
                                        this.this$0.getViewModelOnFailureListener().onFailure(this.this$0.getContext().getString(R.string.failed_message));
                                        return Unit.INSTANCE;
                                    }
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                            }

                            @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1$onDataResponse$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                            /* loaded from: classes8.dex */
                            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                public final /* synthetic */ int $backgroundType;
                                public int label;
                                public final /* synthetic */ WatchFaceBackgroundViewModel this$0;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                public b(WatchFaceBackgroundViewModel watchFaceBackgroundViewModel, int i, Continuation<? super b> continuation) {
                                    super(2, continuation);
                                    this.this$0 = watchFaceBackgroundViewModel;
                                    this.$backgroundType = i;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                @NotNull
                                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                                    return new b(this.this$0, this.$backgroundType, continuation);
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
                                        try {
                                            UserDataManager userDataManager = UserDataManager.getInstance(this.this$0.getContext());
                                            Uri selectedBackgroundImageUri = this.this$0.getSelectedBackgroundImageUri();
                                            Intrinsics.checkNotNull(selectedBackgroundImageUri);
                                            String path = selectedBackgroundImageUri.getPath();
                                            Intrinsics.checkNotNull(path);
                                            userDataManager.saveLastWatchFaceBackgroundUrl(path);
                                            WatchFacePreferenceManager.Companion companion = WatchFacePreferenceManager.Companion;
                                            String uuid = UUID.randomUUID().toString();
                                            Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
                                            Uri selectedBackgroundImageUri2 = this.this$0.getSelectedBackgroundImageUri();
                                            Intrinsics.checkNotNull(selectedBackgroundImageUri2);
                                            String path2 = selectedBackgroundImageUri2.getPath();
                                            Intrinsics.checkNotNull(path2);
                                            companion.getInstance(this.this$0.getContext()).saveLastSelectedWatchFace(new WatchFaceBean(uuid, BleConst.GetDeviceTime, null, null, null, null, null, null, null, null, null, null, path2, false, null, 28668, null));
                                            companion.getInstance(this.this$0.getContext()).saveSelectedWatchfaceBackgroundType(this.$backgroundType);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
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
                                        this.this$0.getViewModelOnSuccessListener().onBackgroundWatchFaceSuccess(true);
                                        return Unit.INSTANCE;
                                    }
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                            }

                            @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1$onProgressUpdate$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                            /* loaded from: classes8.dex */
                            public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                public final /* synthetic */ ProgressData $progress;
                                public int label;
                                public final /* synthetic */ WatchFaceBackgroundViewModel this$0;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                public c(WatchFaceBackgroundViewModel watchFaceBackgroundViewModel, ProgressData progressData, Continuation<? super c> continuation) {
                                    super(2, continuation);
                                    this.this$0 = watchFaceBackgroundViewModel;
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
                            public void onDataError(@org.jetbrains.annotations.NotNull com.coveiot.android.bleabstract.response.BleBaseError r8) {
                                /*
                                    r7 = this;
                                    java.lang.String r0 = "error"
                                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
                                    com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel r8 = com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel.this
                                    kotlinx.coroutines.CoroutineScope r0 = androidx.lifecycle.ViewModelKt.getViewModelScope(r8)
                                    kotlinx.coroutines.MainCoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getMain()
                                    com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1$a r3 = new com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1$a
                                    com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel r8 = com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel.this
                                    r6 = 0
                                    r3.<init>(r8, r6)
                                    r2 = 0
                                    r4 = 2
                                    r5 = 0
                                    kotlinx.coroutines.BuildersKt.launch$default(r0, r1, r2, r3, r4, r5)
                                    com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest r8 = r2     // Catch: java.lang.Exception -> L3e
                                    if (r8 == 0) goto L42
                                    java.lang.String r8 = r8.watchFaceFilePath     // Catch: java.lang.Exception -> L3e
                                    if (r8 == 0) goto L2e
                                    boolean r8 = kotlin.text.m.isBlank(r8)     // Catch: java.lang.Exception -> L3e
                                    if (r8 == 0) goto L2c
                                    goto L2e
                                L2c:
                                    r8 = 0
                                    goto L2f
                                L2e:
                                    r8 = 1
                                L2f:
                                    if (r8 != 0) goto L42
                                    java.io.File r8 = new java.io.File     // Catch: java.lang.Exception -> L3e
                                    com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest r0 = r2     // Catch: java.lang.Exception -> L3e
                                    java.lang.String r0 = r0.watchFaceFilePath     // Catch: java.lang.Exception -> L3e
                                    r8.<init>(r0)     // Catch: java.lang.Exception -> L3e
                                    r8.delete()     // Catch: java.lang.Exception -> L3e
                                    goto L42
                                L3e:
                                    r8 = move-exception
                                    r8.printStackTrace()
                                L42:
                                    com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel r8 = com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel.this
                                    kotlinx.coroutines.CoroutineScope r0 = androidx.lifecycle.ViewModelKt.getViewModelScope(r8)
                                    kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
                                    r2 = 0
                                    com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1$onDataError$2 r3 = new com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1$onDataError$2
                                    com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel r8 = com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel.this
                                    r3.<init>(r8, r6)
                                    r4 = 2
                                    r5 = 0
                                    kotlinx.coroutines.BuildersKt.launch$default(r0, r1, r2, r3, r4, r5)
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1.onDataError(com.coveiot.android.bleabstract.response.BleBaseError):void");
                            }

                            /* JADX WARN: Removed duplicated region for block: B:13:0x0033 A[Catch: Exception -> 0x0040, TRY_LEAVE, TryCatch #0 {Exception -> 0x0040, blocks: (B:3:0x001f, B:5:0x0023, B:7:0x0027, B:13:0x0033), top: B:18:0x001f }] */
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
                                    com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel r7 = com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel.this
                                    kotlinx.coroutines.CoroutineScope r0 = androidx.lifecycle.ViewModelKt.getViewModelScope(r7)
                                    kotlinx.coroutines.MainCoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getMain()
                                    com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1$b r3 = new com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1$b
                                    com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel r7 = com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel.this
                                    int r2 = r3
                                    r4 = 0
                                    r3.<init>(r7, r2, r4)
                                    r2 = 0
                                    r4 = 2
                                    r5 = 0
                                    kotlinx.coroutines.BuildersKt.launch$default(r0, r1, r2, r3, r4, r5)
                                    com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest r7 = r2     // Catch: java.lang.Exception -> L40
                                    if (r7 == 0) goto L44
                                    java.lang.String r7 = r7.watchFaceFilePath     // Catch: java.lang.Exception -> L40
                                    if (r7 == 0) goto L30
                                    boolean r7 = kotlin.text.m.isBlank(r7)     // Catch: java.lang.Exception -> L40
                                    if (r7 == 0) goto L2e
                                    goto L30
                                L2e:
                                    r7 = 0
                                    goto L31
                                L30:
                                    r7 = 1
                                L31:
                                    if (r7 != 0) goto L44
                                    java.io.File r7 = new java.io.File     // Catch: java.lang.Exception -> L40
                                    com.coveiot.android.bleabstract.request.CustomWatchFaceFileImageRequest r0 = r2     // Catch: java.lang.Exception -> L40
                                    java.lang.String r0 = r0.watchFaceFilePath     // Catch: java.lang.Exception -> L40
                                    r7.<init>(r0)     // Catch: java.lang.Exception -> L40
                                    r7.delete()     // Catch: java.lang.Exception -> L40
                                    goto L44
                                L40:
                                    r7 = move-exception
                                    r7.printStackTrace()
                                L44:
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2$1$1$1.onDataResponse(com.coveiot.android.bleabstract.response.BleBaseResponse):void");
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                            public void onProgressUpdate(@NotNull ProgressData progress) {
                                Intrinsics.checkNotNullParameter(progress, "progress");
                                e.e(ViewModelKt.getViewModelScope(WatchFaceBackgroundViewModel.this), Dispatchers.getMain(), null, new c(WatchFaceBackgroundViewModel.this, progress, null), 2, null);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.e(ViewModelKt.getViewModelScope(watchFaceBackgroundViewModel), Dispatchers.getMain(), null, new a(watchFaceBackgroundViewModel, e, null), 2, null);
                    }
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
