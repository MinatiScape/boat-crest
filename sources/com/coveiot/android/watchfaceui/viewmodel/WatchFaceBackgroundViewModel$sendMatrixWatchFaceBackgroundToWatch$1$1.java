package com.coveiot.android.watchfaceui.viewmodel;

import android.net.Uri;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.watchfacecore.model.ProgressBean;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.preference.WatchFacePreferenceManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.LogHelper;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.UUID;
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
@DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendMatrixWatchFaceBackgroundToWatch$1$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
public final class WatchFaceBackgroundViewModel$sendMatrixWatchFaceBackgroundToWatch$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ CustomWatchFaceBackgroundChangeRequest $customWatchFaceBackgroundChangeRequest;
    public final /* synthetic */ Uri $it;
    public int label;
    public final /* synthetic */ WatchFaceBackgroundViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchFaceBackgroundViewModel$sendMatrixWatchFaceBackgroundToWatch$1$1(WatchFaceBackgroundViewModel watchFaceBackgroundViewModel, CustomWatchFaceBackgroundChangeRequest customWatchFaceBackgroundChangeRequest, Uri uri, Continuation<? super WatchFaceBackgroundViewModel$sendMatrixWatchFaceBackgroundToWatch$1$1> continuation) {
        super(2, continuation);
        this.this$0 = watchFaceBackgroundViewModel;
        this.$customWatchFaceBackgroundChangeRequest = customWatchFaceBackgroundChangeRequest;
        this.$it = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new WatchFaceBackgroundViewModel$sendMatrixWatchFaceBackgroundToWatch$1$1(this.this$0, this.$customWatchFaceBackgroundChangeRequest, this.$it, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((WatchFaceBackgroundViewModel$sendMatrixWatchFaceBackgroundToWatch$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            BleApi bleApi = BleApiManager.getInstance(this.this$0.getContext()).getBleApi();
            CustomWatchFaceBackgroundChangeRequest customWatchFaceBackgroundChangeRequest = this.$customWatchFaceBackgroundChangeRequest;
            Intrinsics.checkNotNullExpressionValue(customWatchFaceBackgroundChangeRequest, "customWatchFaceBackgroundChangeRequest");
            final WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = this.this$0;
            final Uri uri = this.$it;
            bleApi.getData(customWatchFaceBackgroundChangeRequest, new DataResultListener() { // from class: com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendMatrixWatchFaceBackgroundToWatch$1$1.1

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendMatrixWatchFaceBackgroundToWatch$1$1$1$onDataError$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendMatrixWatchFaceBackgroundToWatch$1$1$1$a */
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
                            value2.setVisible(false);
                            ProgressBean value3 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value3);
                            value3.setProgress(0);
                            MutableLiveData<ProgressBean> progressLiveData = this.this$0.getProgressLiveData();
                            ProgressBean value4 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value4);
                            progressLiveData.postValue(value4);
                            this.this$0.getViewModelOnFailureListener().onFailure(this.this$0.getContext().getString(R.string.failed_message));
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendMatrixWatchFaceBackgroundToWatch$1$1$1$onDataResponse$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendMatrixWatchFaceBackgroundToWatch$1$1$1$b */
                /* loaded from: classes8.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ Uri $it;
                    public int label;
                    public final /* synthetic */ WatchFaceBackgroundViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(WatchFaceBackgroundViewModel watchFaceBackgroundViewModel, Uri uri, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.this$0 = watchFaceBackgroundViewModel;
                        this.$it = uri;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new b(this.this$0, this.$it, continuation);
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
                                String path = this.$it.getPath();
                                Intrinsics.checkNotNull(path);
                                userDataManager.saveLastWatchFaceBackgroundUrl(path);
                                String uuid = UUID.randomUUID().toString();
                                Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
                                Uri selectedBackgroundImageUri = this.this$0.getSelectedBackgroundImageUri();
                                Intrinsics.checkNotNull(selectedBackgroundImageUri);
                                String path2 = selectedBackgroundImageUri.getPath();
                                Intrinsics.checkNotNull(path2);
                                WatchFacePreferenceManager.Companion.getInstance(this.this$0.getContext()).saveLastSelectedWatchFace(new WatchFaceBean(uuid, BleConst.GetDeviceTime, null, null, null, null, null, null, null, null, null, null, path2, false, null, 28668, null));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            ProgressBean value = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value);
                            value.setTitle(this.this$0.getContext().getString(R.string.updating_watchface));
                            ProgressBean value2 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value2);
                            value2.setVisible(false);
                            ProgressBean value3 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value3);
                            value3.setProgress(100);
                            MutableLiveData<ProgressBean> progressLiveData = this.this$0.getProgressLiveData();
                            ProgressBean value4 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value4);
                            progressLiveData.postValue(value4);
                            this.this$0.getViewModelOnSuccessListener().onBackgroundWatchFaceSuccess(true);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendMatrixWatchFaceBackgroundToWatch$1$1$1$onProgressUpdate$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendMatrixWatchFaceBackgroundToWatch$1$1$1$c */
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
                            String tag = this.this$0.getTAG();
                            LogHelper.d(tag, "progress is " + this.$progress.getProgress());
                            ProgressBean value = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value);
                            value.setTitle(this.this$0.getContext().getString(R.string.updating_watchface));
                            ProgressBean value2 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value2);
                            value2.setVisible(true);
                            ProgressBean value3 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value3);
                            value3.setProgress(this.$progress.getProgress());
                            MutableLiveData<ProgressBean> progressLiveData = this.this$0.getProgressLiveData();
                            ProgressBean value4 = this.this$0.getProgressLiveData().getValue();
                            Intrinsics.checkNotNull(value4);
                            progressLiveData.postValue(value4);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    e.e(ViewModelKt.getViewModelScope(WatchFaceBackgroundViewModel.this), Dispatchers.getMain(), null, new a(WatchFaceBackgroundViewModel.this, null), 2, null);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    e.e(ViewModelKt.getViewModelScope(WatchFaceBackgroundViewModel.this), Dispatchers.getMain(), null, new b(WatchFaceBackgroundViewModel.this, uri, null), 2, null);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                    e.e(ViewModelKt.getViewModelScope(WatchFaceBackgroundViewModel.this), Dispatchers.getMain(), null, new c(WatchFaceBackgroundViewModel.this, progress, null), 2, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
