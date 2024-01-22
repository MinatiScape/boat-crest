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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendTouchWatchFaceBackgroundToWatch$2", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
public final class WatchFaceBackgroundViewModel$sendTouchWatchFaceBackgroundToWatch$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Ref.ObjectRef<CustomWatchFaceBackgroundChangeRequest> $customWatchFaceRequest;
    public int label;
    public final /* synthetic */ WatchFaceBackgroundViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchFaceBackgroundViewModel$sendTouchWatchFaceBackgroundToWatch$2(WatchFaceBackgroundViewModel watchFaceBackgroundViewModel, Ref.ObjectRef<CustomWatchFaceBackgroundChangeRequest> objectRef, Continuation<? super WatchFaceBackgroundViewModel$sendTouchWatchFaceBackgroundToWatch$2> continuation) {
        super(2, continuation);
        this.this$0 = watchFaceBackgroundViewModel;
        this.$customWatchFaceRequest = objectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new WatchFaceBackgroundViewModel$sendTouchWatchFaceBackgroundToWatch$2(this.this$0, this.$customWatchFaceRequest, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((WatchFaceBackgroundViewModel$sendTouchWatchFaceBackgroundToWatch$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            BleApi bleApi = BleApiManager.getInstance(this.this$0.getContext()).getBleApi();
            CustomWatchFaceBackgroundChangeRequest customWatchFaceRequest = this.$customWatchFaceRequest.element;
            Intrinsics.checkNotNullExpressionValue(customWatchFaceRequest, "customWatchFaceRequest");
            final WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = this.this$0;
            bleApi.getData(customWatchFaceRequest, new DataResultListener() { // from class: com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendTouchWatchFaceBackgroundToWatch$2.1

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendTouchWatchFaceBackgroundToWatch$2$1$onDataError$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendTouchWatchFaceBackgroundToWatch$2$1$a */
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
                            if (!value.getVisible()) {
                                ProgressBean value2 = this.this$0.getProgressLiveData().getValue();
                                Intrinsics.checkNotNull(value2);
                                value2.setTitle(this.this$0.getContext().getString(R.string.updating_watchface));
                                ProgressBean value3 = this.this$0.getProgressLiveData().getValue();
                                Intrinsics.checkNotNull(value3);
                                value3.setVisible(false);
                                ProgressBean value4 = this.this$0.getProgressLiveData().getValue();
                                Intrinsics.checkNotNull(value4);
                                value4.setProgress(0);
                                MutableLiveData<ProgressBean> progressLiveData = this.this$0.getProgressLiveData();
                                ProgressBean value5 = this.this$0.getProgressLiveData().getValue();
                                Intrinsics.checkNotNull(value5);
                                progressLiveData.postValue(value5);
                            }
                            this.this$0.getViewModelOnFailureListener().onFailure(this.this$0.getContext().getString(R.string.failed_message));
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendTouchWatchFaceBackgroundToWatch$2$1$onDataResponse$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendTouchWatchFaceBackgroundToWatch$2$1$b */
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
                            try {
                                UserDataManager userDataManager = UserDataManager.getInstance(this.this$0.getContext());
                                Uri selectedBackgroundImageUri = this.this$0.getSelectedBackgroundImageUri();
                                Intrinsics.checkNotNull(selectedBackgroundImageUri);
                                String path = selectedBackgroundImageUri.getPath();
                                Intrinsics.checkNotNull(path);
                                userDataManager.saveLastWatchFaceBackgroundUrl(path);
                                String uuid = UUID.randomUUID().toString();
                                Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
                                Uri selectedBackgroundImageUri2 = this.this$0.getSelectedBackgroundImageUri();
                                Intrinsics.checkNotNull(selectedBackgroundImageUri2);
                                String path2 = selectedBackgroundImageUri2.getPath();
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

                @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendTouchWatchFaceBackgroundToWatch$2$1$onProgressUpdate$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendTouchWatchFaceBackgroundToWatch$2$1$c */
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
                    e.e(ViewModelKt.getViewModelScope(WatchFaceBackgroundViewModel.this), Dispatchers.getMain(), null, new b(WatchFaceBackgroundViewModel.this, null), 2, null);
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
