package com.coveiot.android.watchfaceui.viewmodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceLayoutChangeRequest;
import com.coveiot.android.bleabstract.request.SetWatchFaceImageIdRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.watchfacecore.model.ProgressBean;
import com.coveiot.android.watchfacecore.model.WatchFaceLayoutInfo;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.listener.OnFailureListener;
import com.coveiot.android.watchfaceui.listener.OnSuccessListener;
import com.coveiot.android.watchfaceui.utils.Utils;
import com.coveiot.utils.utility.LogHelper;
import com.realsil.sdk.dfu.DfuException;
import com.szabh.smable3.watchface.Element;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WatchFaceBackgroundViewModel extends ViewModel {
    public static final int CUSTOMIZABLE_WATCH_FACE_POSITION = 4;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6149a;
    public final String b;
    @NotNull
    public MutableLiveData<Boolean> c;
    @Nullable
    public Uri d;
    @NotNull
    public MutableLiveData<ProgressBean> e;
    @NotNull
    public MutableLiveData<Boolean> f;
    @NotNull
    public MutableLiveData<Boolean> g;
    public int h;
    public int i;
    public int j;
    public OnFailureListener viewModelOnFailureListener;
    public OnSuccessListener viewModelOnSuccessListener;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
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
                ProgressBean value = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value);
                if (!value.getVisible()) {
                    ProgressBean value2 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value2);
                    value2.setTitle(WatchFaceBackgroundViewModel.this.getContext().getString(R.string.updating_watchface));
                    ProgressBean value3 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value3);
                    value3.setVisible(true);
                    ProgressBean value4 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value4);
                    value4.setProgress(0);
                    MutableLiveData<ProgressBean> progressLiveData = WatchFaceBackgroundViewModel.this.getProgressLiveData();
                    ProgressBean value5 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value5);
                    progressLiveData.postValue(value5);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendEastApexWatchFaceBackgroundToWatch$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(continuation);
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
                ProgressBean value = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value);
                if (!value.getVisible()) {
                    ProgressBean value2 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value2);
                    value2.setTitle(WatchFaceBackgroundViewModel.this.getContext().getString(R.string.updating_watchface));
                    ProgressBean value3 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value3);
                    value3.setVisible(true);
                    ProgressBean value4 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value4);
                    value4.setProgress(0);
                    MutableLiveData<ProgressBean> progressLiveData = WatchFaceBackgroundViewModel.this.getProgressLiveData();
                    ProgressBean value5 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value5);
                    progressLiveData.postValue(value5);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendIDOWatchFaceBackgroundToWatch$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public c(Continuation<? super c> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(continuation);
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
                ProgressBean value = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value);
                if (!value.getVisible()) {
                    ProgressBean value2 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value2);
                    value2.setTitle(WatchFaceBackgroundViewModel.this.getContext().getString(R.string.updating_watchface));
                    ProgressBean value3 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value3);
                    value3.setVisible(true);
                    ProgressBean value4 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value4);
                    value4.setProgress(0);
                    MutableLiveData<ProgressBean> progressLiveData = WatchFaceBackgroundViewModel.this.getProgressLiveData();
                    ProgressBean value5 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value5);
                    progressLiveData.postValue(value5);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendTouchWatchFaceBackgroundToWatch$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public d(Continuation<? super d> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d(continuation);
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
                ProgressBean value = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value);
                if (!value.getVisible()) {
                    ProgressBean value2 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value2);
                    value2.setTitle(WatchFaceBackgroundViewModel.this.getContext().getString(R.string.updating_watchface));
                    ProgressBean value3 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value3);
                    value3.setVisible(true);
                    ProgressBean value4 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value4);
                    value4.setProgress(0);
                    MutableLiveData<ProgressBean> progressLiveData = WatchFaceBackgroundViewModel.this.getProgressLiveData();
                    ProgressBean value5 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value5);
                    progressLiveData.postValue(value5);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendWatchFaceBackgroundToWatch$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public e(Continuation<? super e> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new e(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((e) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ProgressBean value = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                Intrinsics.checkNotNull(value);
                if (!value.getVisible()) {
                    ProgressBean value2 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value2);
                    value2.setTitle(WatchFaceBackgroundViewModel.this.getContext().getString(R.string.updating_watchface));
                    ProgressBean value3 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value3);
                    value3.setVisible(true);
                    ProgressBean value4 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value4);
                    value4.setProgress(0);
                    MutableLiveData<ProgressBean> progressLiveData = WatchFaceBackgroundViewModel.this.getProgressLiveData();
                    ProgressBean value5 = WatchFaceBackgroundViewModel.this.getProgressLiveData().getValue();
                    Intrinsics.checkNotNull(value5);
                    progressLiveData.postValue(value5);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public WatchFaceBackgroundViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6149a = context;
        this.b = WatchFaceBackgroundViewModel.class.getSimpleName();
        this.c = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
        this.f = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
        this.h = -1;
        this.i = -1;
        this.j = -1;
        this.e.setValue(new ProgressBean(null, 0, false));
    }

    public final File a(String str, int i) {
        File file = new File(this.f6149a.getFilesDir() + File.separator + str + ".bin");
        try {
            InputStream openRawResource = this.f6149a.getResources().openRawResource(i);
            Intrinsics.checkNotNullExpressionValue(openRawResource, "context.resources.openRawResource(resourceId)");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = openRawResource.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    openRawResource.close();
                    return file;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public final void b(Bitmap bitmap, WatchFaceLayoutInfo watchFaceLayoutInfo, Bitmap bitmap2) {
        int i;
        int i2;
        int i3;
        CustomWatchFaceBackgroundChangeRequest.Builder imageFile = new CustomWatchFaceBackgroundChangeRequest.Builder().setBitmap(bitmap).setImageFile(watchFaceLayoutInfo.getBackgroundPictureFile());
        int i4 = -1;
        if (watchFaceLayoutInfo.getWatchFaceId() != null) {
            Integer watchFaceId = watchFaceLayoutInfo.getWatchFaceId();
            Intrinsics.checkNotNull(watchFaceId);
            i = watchFaceId.intValue();
        } else {
            i = -1;
        }
        CustomWatchFaceBackgroundChangeRequest.Builder watchFaceId2 = imageFile.setWatchFaceId(i);
        if (watchFaceLayoutInfo.getImageId() != null) {
            Integer imageId = watchFaceLayoutInfo.getImageId();
            Intrinsics.checkNotNull(imageId);
            i2 = imageId.intValue();
        } else {
            i2 = -1;
        }
        CustomWatchFaceBackgroundChangeRequest.Builder imageId2 = watchFaceId2.setImageId(i2);
        if (watchFaceLayoutInfo.getHeight() != null) {
            Integer height = watchFaceLayoutInfo.getHeight();
            Intrinsics.checkNotNull(height);
            i3 = height.intValue();
        } else {
            i3 = -1;
        }
        CustomWatchFaceBackgroundChangeRequest.Builder height2 = imageId2.setHeight(i3);
        if (watchFaceLayoutInfo.getWidth() != null) {
            Integer width = watchFaceLayoutInfo.getWidth();
            Intrinsics.checkNotNull(width);
            i4 = width.intValue();
        }
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new WatchFaceBackgroundViewModel$sendBackgroundWatchFaceToWatch$1(this, height2.setWidth(i4).setThumbBitmap(bitmap2).setCompressionType(watchFaceLayoutInfo.getCompressionType()).build(), null), 2, null);
    }

    @NotNull
    public final Context getContext() {
        return this.f6149a;
    }

    public final int getMStylePosition() {
        return this.j;
    }

    public final int getMTextStyle() {
        return this.h;
    }

    public final int getMTimePosition() {
        return this.i;
    }

    @NotNull
    public final MutableLiveData<ProgressBean> getProgressLiveData() {
        return this.e;
    }

    @NotNull
    public final MutableLiveData<Boolean> getSaveEnableDisable() {
        return this.g;
    }

    @Nullable
    public final Uri getSelectedBackgroundImageUri() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<Boolean> getSelectedWatchFaceOnWatchReceivedLiveData() {
        return this.f;
    }

    public final String getTAG() {
        return this.b;
    }

    @NotNull
    public final OnFailureListener getViewModelOnFailureListener() {
        OnFailureListener onFailureListener = this.viewModelOnFailureListener;
        if (onFailureListener != null) {
            return onFailureListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelOnFailureListener");
        return null;
    }

    @NotNull
    public final OnSuccessListener getViewModelOnSuccessListener() {
        OnSuccessListener onSuccessListener = this.viewModelOnSuccessListener;
        if (onSuccessListener != null) {
            return onSuccessListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelOnSuccessListener");
        return null;
    }

    @NotNull
    public final MutableLiveData<Boolean> isUploadToWatchCanceled() {
        return this.c;
    }

    public final void onFail() {
        getViewModelOnFailureListener().onFailure("");
    }

    public final void sendCAWatchFaceBackgroundToWatch(int i, int i2) {
        Uri uri = this.d;
        if (uri != null) {
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new WatchFaceBackgroundViewModel$sendCAWatchFaceBackgroundToWatch$1$2(i, this, uri, i2, null), 2, null);
        }
    }

    /* JADX WARN: Type inference failed for: r1v10, types: [T, com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest] */
    /* JADX WARN: Type inference failed for: r1v7, types: [T, com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest] */
    public final void sendEastApexWatchFaceBackgroundToWatch(@NotNull CustomWatchFaceLayoutChangeRequest changeLayoutChangeRequest, @NotNull Bitmap bitmap, @Nullable Integer num, @Nullable Integer num2, @Nullable Bitmap bitmap2, @Nullable Bitmap bitmap3, @Nullable Bitmap bitmap4, @Nullable Bitmap bitmap5) {
        Intrinsics.checkNotNullParameter(changeLayoutChangeRequest, "changeLayoutChangeRequest");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        if (BleApiManager.getInstance(this.f6149a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new b(null), 2, null);
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            if (changeLayoutChangeRequest.getStylePosition() != 2 && changeLayoutChangeRequest.getStylePosition() != 3) {
                CustomWatchFaceBackgroundChangeRequest.Builder bitmap6 = new CustomWatchFaceBackgroundChangeRequest.Builder().setBitmap(bitmap);
                Intrinsics.checkNotNull(num);
                CustomWatchFaceBackgroundChangeRequest.Builder height = bitmap6.setHeight(num.intValue());
                Intrinsics.checkNotNull(num2);
                objectRef.element = height.setWidth(num2.intValue()).setCustomWatchFaceLayoutChangeRequest(changeLayoutChangeRequest).build();
            } else {
                CustomWatchFaceBackgroundChangeRequest.Builder bitmap7 = new CustomWatchFaceBackgroundChangeRequest.Builder().setBitmap(bitmap);
                Intrinsics.checkNotNull(num);
                CustomWatchFaceBackgroundChangeRequest.Builder height2 = bitmap7.setHeight(num.intValue());
                Intrinsics.checkNotNull(num2);
                objectRef.element = height2.setWidth(num2.intValue()).setCustomWatchFaceLayoutChangeRequest(changeLayoutChangeRequest).setPreviewBitmap(bitmap2).setHourBitmap(bitmap3).setMinuteBitmap(bitmap4).setSecondBitmap(bitmap5).build();
            }
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new WatchFaceBackgroundViewModel$sendEastApexWatchFaceBackgroundToWatch$2(this, objectRef, null), 2, null);
            return;
        }
        getViewModelOnFailureListener().onFailure(this.f6149a.getString(R.string.band_not_connected));
    }

    /* JADX WARN: Type inference failed for: r9v10, types: [T, com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest] */
    public final void sendIDOWatchFaceBackgroundToWatch(@NotNull WatchFaceLayoutInfo watchFaceLayoutInfo) {
        Intrinsics.checkNotNullParameter(watchFaceLayoutInfo, "watchFaceLayoutInfo");
        if (BleApiManager.getInstance(this.f6149a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new c(null), 2, null);
            Integer textColor = watchFaceLayoutInfo.getTextColor();
            Intrinsics.checkNotNull(textColor);
            String hexString = Integer.toHexString(textColor.intValue());
            Intrinsics.checkNotNullExpressionValue(hexString, "toHexString(watchFaceLayoutInfo.textColor!!)");
            String substring = hexString.substring(2);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            CustomWatchFaceLayoutChangeRequest build = new CustomWatchFaceLayoutChangeRequest.Builder().setTextColor(Integer.parseInt(substring, kotlin.text.a.checkRadix(16))).build();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            CustomWatchFaceBackgroundChangeRequest.Builder builder = new CustomWatchFaceBackgroundChangeRequest.Builder();
            Uri uri = this.d;
            Intrinsics.checkNotNull(uri);
            objectRef.element = builder.setWatchFaceFilePath(uri.getPath()).setCustomWatchFaceLayoutChangeRequest(build).build();
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new WatchFaceBackgroundViewModel$sendIDOWatchFaceBackgroundToWatch$2(this, objectRef, null), 2, null);
            return;
        }
        getViewModelOnFailureListener().onFailure(this.f6149a.getString(R.string.band_not_connected));
    }

    public final void sendMatrixWatchFaceBackgroundToWatch() {
        Object obj;
        if (BleApiManager.getInstance(this.f6149a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            Uri uri = this.d;
            if (uri != null) {
                Bitmap decodeStream = BitmapFactory.decodeStream(this.f6149a.getContentResolver().openInputStream(Uri.parse(uri.toString())));
                if (decodeStream != null) {
                    Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeStream, 348, 442, true);
                    Intrinsics.checkNotNullExpressionValue(createScaledBitmap, "createScaledBitmap(\n    …rue\n                    )");
                    Utils utils = Utils.INSTANCE;
                    if (utils.isRuggedDevice(this.f6149a)) {
                        if (BleApiManager.getInstance(this.f6149a).getDeviceType() == DeviceType.LUNARFIT) {
                            createScaledBitmap = Bitmap.createScaledBitmap(decodeStream, 466, 466, true);
                            Intrinsics.checkNotNullExpressionValue(createScaledBitmap, "createScaledBitmap(\n    …                        )");
                        } else {
                            createScaledBitmap = Bitmap.createScaledBitmap(decodeStream, 240, DfuException.ERROR_DFU_ENABLE_BUFFER_CHECK_NO_RESPONSE, true);
                            Intrinsics.checkNotNullExpressionValue(createScaledBitmap, "createScaledBitmap(\n    …                        )");
                        }
                    }
                    CustomWatchFaceLayoutChangeRequest.Builder position = new CustomWatchFaceLayoutChangeRequest.Builder().setPosition(this.i);
                    Bitmap styleBitmapsForMatrixWatchfaces = utils.getStyleBitmapsForMatrixWatchfaces(this.f6149a, this.h);
                    Intrinsics.checkNotNull(styleBitmapsForMatrixWatchfaces);
                    CustomWatchFaceLayoutChangeRequest.Builder styleBitMap = position.setStyleBitMap(styleBitmapsForMatrixWatchfaces);
                    File basicBinFilesForMatrixWatchfaces = utils.getBasicBinFilesForMatrixWatchfaces(this.f6149a);
                    Intrinsics.checkNotNull(basicBinFilesForMatrixWatchfaces);
                    CustomWatchFaceBackgroundChangeRequest build = new CustomWatchFaceBackgroundChangeRequest.Builder().setBitmap(createScaledBitmap).setCustomWatchFaceLayoutChangeRequest(styleBitMap.setBasicBinFile(basicBinFilesForMatrixWatchfaces).setStylePosition(this.j).build()).build();
                    ProgressBean value = this.e.getValue();
                    Intrinsics.checkNotNull(value);
                    if (!value.getVisible()) {
                        ProgressBean value2 = this.e.getValue();
                        Intrinsics.checkNotNull(value2);
                        value2.setTitle(this.f6149a.getString(R.string.updating_watchface));
                        ProgressBean value3 = this.e.getValue();
                        Intrinsics.checkNotNull(value3);
                        value3.setVisible(true);
                        ProgressBean value4 = this.e.getValue();
                        Intrinsics.checkNotNull(value4);
                        value4.setProgress(0);
                        MutableLiveData<ProgressBean> mutableLiveData = this.e;
                        ProgressBean value5 = mutableLiveData.getValue();
                        Intrinsics.checkNotNull(value5);
                        mutableLiveData.postValue(value5);
                    }
                    obj = kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new WatchFaceBackgroundViewModel$sendMatrixWatchFaceBackgroundToWatch$1$1(this, build, uri, null), 2, null);
                } else {
                    getViewModelOnFailureListener().onFailure(this.f6149a.getString(R.string.backgroud_image_is_missing));
                    obj = Unit.INSTANCE;
                }
                if (obj != null) {
                    return;
                }
            }
            getViewModelOnFailureListener().onFailure(this.f6149a.getString(R.string.backgroud_image_is_missing));
            Unit unit = Unit.INSTANCE;
            return;
        }
        getViewModelOnFailureListener().onFailure(this.f6149a.getString(R.string.band_not_connected));
    }

    public final void sendSMAWatchFaceBackgroundToWatch(@NotNull ArrayList<Element> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (BleApiManager.getInstance(this.f6149a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            CustomWatchFaceBackgroundChangeRequest build = new CustomWatchFaceBackgroundChangeRequest.Builder().setElementArray(elements).build();
            ProgressBean value = this.e.getValue();
            Intrinsics.checkNotNull(value);
            if (!value.getVisible()) {
                ProgressBean value2 = this.e.getValue();
                Intrinsics.checkNotNull(value2);
                value2.setTitle(this.f6149a.getString(R.string.updating_watchface));
                ProgressBean value3 = this.e.getValue();
                Intrinsics.checkNotNull(value3);
                value3.setVisible(true);
                ProgressBean value4 = this.e.getValue();
                Intrinsics.checkNotNull(value4);
                value4.setProgress(0);
                MutableLiveData<ProgressBean> mutableLiveData = this.e;
                ProgressBean value5 = mutableLiveData.getValue();
                Intrinsics.checkNotNull(value5);
                mutableLiveData.postValue(value5);
            }
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new WatchFaceBackgroundViewModel$sendSMAWatchFaceBackgroundToWatch$1(this, build, null), 2, null);
            return;
        }
        getViewModelOnFailureListener().onFailure(this.f6149a.getString(R.string.band_not_connected));
    }

    /* JADX WARN: Type inference failed for: r9v6, types: [T, com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest] */
    public final void sendTouchWatchFaceBackgroundToWatch(@NotNull String timePosition, @NotNull WatchFaceLayoutInfo watchFaceLayoutInfo, @NotNull Bitmap bitmap, @NotNull String watchfaceFilePath) {
        Intrinsics.checkNotNullParameter(timePosition, "timePosition");
        Intrinsics.checkNotNullParameter(watchFaceLayoutInfo, "watchFaceLayoutInfo");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(watchfaceFilePath, "watchfaceFilePath");
        if (BleApiManager.getInstance(this.f6149a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new d(null), 2, null);
            Integer textColor = watchFaceLayoutInfo.getTextColor();
            Intrinsics.checkNotNull(textColor);
            String hexString = Integer.toHexString(textColor.intValue());
            Intrinsics.checkNotNullExpressionValue(hexString, "toHexString(watchFaceLayoutInfo.textColor!!)");
            String substring = hexString.substring(2);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            CustomWatchFaceLayoutChangeRequest build = new CustomWatchFaceLayoutChangeRequest.Builder().setTextColor(Integer.parseInt(substring, kotlin.text.a.checkRadix(16))).setTimePosition(timePosition).setStylePosition(this.j).build();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = new CustomWatchFaceBackgroundChangeRequest.Builder().setBitmap(bitmap).setWatchFaceFilePath(watchfaceFilePath).setCustomWatchFaceLayoutChangeRequest(build).build();
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new WatchFaceBackgroundViewModel$sendTouchWatchFaceBackgroundToWatch$2(this, objectRef, null), 2, null);
            return;
        }
        getViewModelOnFailureListener().onFailure(this.f6149a.getString(R.string.band_not_connected));
    }

    public final void sendWatchFaceBackgroundToWatch(@NotNull WatchFaceLayoutInfo watchFaceLayoutInfo, @NotNull Uri bImageUri) {
        Intrinsics.checkNotNullParameter(watchFaceLayoutInfo, "watchFaceLayoutInfo");
        Intrinsics.checkNotNullParameter(bImageUri, "bImageUri");
        this.d = bImageUri;
        sendWatchFaceBackgroundToWatch(watchFaceLayoutInfo);
    }

    public final void setMStylePosition(int i) {
        this.j = i;
    }

    public final void setMTextStyle(int i) {
        this.h = i;
    }

    public final void setMTimePosition(int i) {
        this.i = i;
    }

    public final void setProgressLiveData(@NotNull MutableLiveData<ProgressBean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.e = mutableLiveData;
    }

    public final void setSaveEnableDisable(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.g = mutableLiveData;
    }

    public final void setSelectedBackgroundImageUri(@Nullable Uri uri) {
        this.d = uri;
    }

    public final void setSelectedWatchFaceOnWatchReceivedLiveData(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.f = mutableLiveData;
    }

    public final void setUploadToWatchCanceled(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setViewModelOnFailureListener(@NotNull OnFailureListener onFailureListener) {
        Intrinsics.checkNotNullParameter(onFailureListener, "<set-?>");
        this.viewModelOnFailureListener = onFailureListener;
    }

    public final void setViewModelOnSuccessListener(@NotNull OnSuccessListener onSuccessListener) {
        Intrinsics.checkNotNullParameter(onSuccessListener, "<set-?>");
        this.viewModelOnSuccessListener = onSuccessListener;
    }

    public final void showSaveBtn(boolean z) {
        this.g.setValue(Boolean.valueOf(z));
        this.g.postValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [T, android.graphics.Bitmap, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v21, types: [T, android.graphics.Bitmap] */
    public final void sendWatchFaceBackgroundToWatch(@NotNull final WatchFaceLayoutInfo watchFaceLayoutInfo) {
        Intrinsics.checkNotNullParameter(watchFaceLayoutInfo, "watchFaceLayoutInfo");
        if (this.d != null) {
            kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new e(null), 2, null);
            new BitmapFactory.Options().inScaled = false;
            showSaveBtn(false);
            Bitmap decodeStream = BitmapFactory.decodeStream(this.f6149a.getContentResolver().openInputStream(Uri.parse(String.valueOf(this.d))));
            if (this.d == null || decodeStream == null || watchFaceLayoutInfo.getWidth() == null || watchFaceLayoutInfo.getHeight() == null || watchFaceLayoutInfo.getThumbWidth() == null || watchFaceLayoutInfo.getThumbHeight() == null) {
                if (decodeStream == null) {
                    getViewModelOnFailureListener().onFailure(this.f6149a.getString(R.string.backgroud_image_is_missing));
                    return;
                }
                return;
            }
            String str = this.b;
            LogHelper.d(str, "height: " + watchFaceLayoutInfo.getHeight() + ", width: " + watchFaceLayoutInfo.getWidth());
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            Integer width = watchFaceLayoutInfo.getWidth();
            Intrinsics.checkNotNull(width);
            int intValue = width.intValue();
            Integer height = watchFaceLayoutInfo.getHeight();
            Intrinsics.checkNotNull(height);
            ?? createScaledBitmap = Bitmap.createScaledBitmap(decodeStream, intValue, height.intValue(), true);
            Intrinsics.checkNotNullExpressionValue(createScaledBitmap, "createScaledBitmap(\n    …!, true\n                )");
            objectRef.element = createScaledBitmap;
            String str2 = this.b;
            Log.d(str2, "thumbHeight: " + watchFaceLayoutInfo.getThumbHeight() + ", thumbWidth: " + watchFaceLayoutInfo.getThumbWidth());
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            Integer thumbHeight = watchFaceLayoutInfo.getThumbHeight();
            Intrinsics.checkNotNull(thumbHeight);
            if (thumbHeight.intValue() > 0) {
                Integer thumbWidth = watchFaceLayoutInfo.getThumbWidth();
                Intrinsics.checkNotNull(thumbWidth);
                if (thumbWidth.intValue() > 0) {
                    Integer thumbWidth2 = watchFaceLayoutInfo.getThumbWidth();
                    Intrinsics.checkNotNull(thumbWidth2);
                    int intValue2 = thumbWidth2.intValue();
                    Integer thumbHeight2 = watchFaceLayoutInfo.getThumbHeight();
                    Intrinsics.checkNotNull(thumbHeight2);
                    objectRef2.element = Bitmap.createScaledBitmap((Bitmap) objectRef.element, intValue2, thumbHeight2.intValue(), true);
                }
            }
            Context context = this.f6149a;
            if ((context != null && Utils.INSTANCE.isCZDevice(context)) || Utils.isCYDevice(this.f6149a)) {
                BleApi bleApi = BleApiManager.getInstance(this.f6149a).getBleApi();
                Integer watchFaceId = watchFaceLayoutInfo.getWatchFaceId();
                Intrinsics.checkNotNull(watchFaceId);
                bleApi.setUserSettings(new SetWatchFaceImageIdRequest(65535, watchFaceId.intValue()), new SettingsResultListener() { // from class: com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendWatchFaceBackgroundToWatch$2

                    @DebugMetadata(c = "com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel$sendWatchFaceBackgroundToWatch$2$onSettingsError$1", f = "WatchFaceBackgroundViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                                this.this$0.getViewModelOnFailureListener().onFailure(this.this$0.getContext().getString(R.string.failed_message));
                                this.this$0.showSaveBtn(false);
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        e.e(ViewModelKt.getViewModelScope(WatchFaceBackgroundViewModel.this), Dispatchers.getMain(), null, new a(WatchFaceBackgroundViewModel.this, null), 2, null);
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        WatchFaceBackgroundViewModel.this.b(objectRef.element, watchFaceLayoutInfo, objectRef2.element);
                    }
                });
                return;
            }
            b((Bitmap) objectRef.element, watchFaceLayoutInfo, (Bitmap) objectRef2.element);
        }
    }
}
