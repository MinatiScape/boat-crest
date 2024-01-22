package com.coveiot.android.watchfaceui.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.CustomWatchFaceLayoutChangeRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.watchfacecore.model.WatchFaceLayoutInfo;
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
@DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceLayoutViewModel$sendWatchFaceLayoutToWatch$1$1", f = "WatchFaceLayoutViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
public final class WatchFaceLayoutViewModel$sendWatchFaceLayoutToWatch$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ WatchFaceLayoutInfo $it;
    public int label;
    public final /* synthetic */ WatchFaceLayoutViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchFaceLayoutViewModel$sendWatchFaceLayoutToWatch$1$1(WatchFaceLayoutInfo watchFaceLayoutInfo, WatchFaceLayoutViewModel watchFaceLayoutViewModel, Continuation<? super WatchFaceLayoutViewModel$sendWatchFaceLayoutToWatch$1$1> continuation) {
        super(2, continuation);
        this.$it = watchFaceLayoutInfo;
        this.this$0 = watchFaceLayoutViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new WatchFaceLayoutViewModel$sendWatchFaceLayoutToWatch$1$1(this.$it, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((WatchFaceLayoutViewModel$sendWatchFaceLayoutToWatch$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        WatchFaceLayoutInfo watchFaceLayoutInfoFromWatch;
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            String backgroundPictureMd5 = this.$it.getBackgroundPictureMd5();
            if ((backgroundPictureMd5 == null || backgroundPictureMd5.length() == 0) && (watchFaceLayoutInfoFromWatch = this.this$0.getWatchFaceLayoutInfoFromWatch()) != null) {
                WatchFaceLayoutInfo watchFaceLayoutInfo = this.$it;
                String backgroundPictureMd52 = watchFaceLayoutInfoFromWatch.getBackgroundPictureMd5();
                if (backgroundPictureMd52 != null) {
                    watchFaceLayoutInfo.setBackgroundPictureMd5(backgroundPictureMd52);
                }
            }
            String backgroundPictureMd53 = this.$it.getBackgroundPictureMd5();
            if (backgroundPictureMd53 != null) {
                WatchFaceLayoutInfo watchFaceLayoutInfo2 = this.$it;
                final WatchFaceLayoutViewModel watchFaceLayoutViewModel = this.this$0;
                CustomWatchFaceLayoutChangeRequest.Builder backgroundPictureMd54 = new CustomWatchFaceLayoutChangeRequest.Builder().setBackgroundPictureMd5(backgroundPictureMd53);
                Integer bottomContent = watchFaceLayoutInfo2.getBottomContent();
                Intrinsics.checkNotNull(bottomContent);
                CustomWatchFaceLayoutChangeRequest.Builder bottomContent2 = backgroundPictureMd54.setBottomContent(bottomContent.intValue());
                Integer height = watchFaceLayoutInfo2.getHeight();
                Intrinsics.checkNotNull(height);
                CustomWatchFaceLayoutChangeRequest.Builder height2 = bottomContent2.setHeight(height.intValue());
                Integer width = watchFaceLayoutInfo2.getWidth();
                Intrinsics.checkNotNull(width);
                CustomWatchFaceLayoutChangeRequest.Builder width2 = height2.setWidth(width.intValue());
                Integer position = watchFaceLayoutInfo2.getPosition();
                Intrinsics.checkNotNull(position);
                CustomWatchFaceLayoutChangeRequest.Builder position2 = width2.setPosition(position.intValue());
                Integer textColor = watchFaceLayoutInfo2.getTextColor();
                Intrinsics.checkNotNull(textColor);
                CustomWatchFaceLayoutChangeRequest.Builder textColor2 = position2.setTextColor(textColor.intValue());
                Integer topContent = watchFaceLayoutInfo2.getTopContent();
                Intrinsics.checkNotNull(topContent);
                CustomWatchFaceLayoutChangeRequest.Builder topContent2 = textColor2.setTopContent(topContent.intValue());
                Integer thumbHeight = watchFaceLayoutInfo2.getThumbHeight();
                Intrinsics.checkNotNull(thumbHeight);
                CustomWatchFaceLayoutChangeRequest.Builder thumbHeight2 = topContent2.setThumbHeight(thumbHeight.intValue());
                Integer thumbWidth = watchFaceLayoutInfo2.getThumbWidth();
                Intrinsics.checkNotNull(thumbWidth);
                CustomWatchFaceLayoutChangeRequest sendCustomWatchFaceLayoutRequest = thumbHeight2.setThumbWidth(thumbWidth.intValue()).build();
                BleApi bleApi = BleApiManager.getInstance(watchFaceLayoutViewModel.getContext()).getBleApi();
                Intrinsics.checkNotNullExpressionValue(sendCustomWatchFaceLayoutRequest, "sendCustomWatchFaceLayoutRequest");
                bleApi.setUserSettings(sendCustomWatchFaceLayoutRequest, new SettingsResultListener() { // from class: com.coveiot.android.watchfaceui.viewmodel.WatchFaceLayoutViewModel$sendWatchFaceLayoutToWatch$1$1$2$1

                    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceLayoutViewModel$sendWatchFaceLayoutToWatch$1$1$2$1$onSettingsResponse$1", f = "WatchFaceLayoutViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* loaded from: classes8.dex */
                    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public int label;
                        public final /* synthetic */ WatchFaceLayoutViewModel this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public a(WatchFaceLayoutViewModel watchFaceLayoutViewModel, Continuation<? super a> continuation) {
                            super(2, continuation);
                            this.this$0 = watchFaceLayoutViewModel;
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
                                this.this$0.getViewModelOnSuccessListener().onLayoutWatchFaceSuccess(true);
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        e.e(ViewModelKt.getViewModelScope(WatchFaceLayoutViewModel.this), Dispatchers.getMain(), null, new a(WatchFaceLayoutViewModel.this, null), 2, null);
                    }
                });
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
