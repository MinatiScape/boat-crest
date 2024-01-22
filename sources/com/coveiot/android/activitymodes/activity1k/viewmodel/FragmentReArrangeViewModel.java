package com.coveiot.android.activitymodes.activity1k.viewmodel;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.lifecycle.AndroidViewModel;
import com.coveiot.android.activitymodes.activity1k.db.DeviceIconModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.error.CommandError;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.ConfigureActivityListRequest;
import com.coveiot.android.bleabstract.request.SendImageRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.utils.utility.LogHelper;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.e;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentReArrangeViewModel extends AndroidViewModel {
    public ActivityUpdateListener activityUpdateListener;
    @NotNull
    public final String d;
    @NotNull
    public Context e;

    /* loaded from: classes2.dex */
    public interface ActivityUpdateListener {
        void onActivityUpload();

        void onImageUpload(int i);

        void onUpdateFailed();

        void onWatchBusyStatusReceived();
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel$saveImageToInternal$1", f = "FragmentReArrangeViewModel.kt", i = {}, l = {119}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ File $file;
        public final /* synthetic */ int $iconNumber;
        public final /* synthetic */ CategoryAndActivityModel $newActivityCategory;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel$saveImageToInternal$1$1", f = "FragmentReArrangeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C0237a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ File $file;
            public final /* synthetic */ int $iconNumber;
            public final /* synthetic */ Bitmap $imageBitmap;
            public final /* synthetic */ CategoryAndActivityModel $newActivityCategory;
            public int label;
            public final /* synthetic */ FragmentReArrangeViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0237a(Bitmap bitmap, FragmentReArrangeViewModel fragmentReArrangeViewModel, File file, CategoryAndActivityModel categoryAndActivityModel, int i, Continuation<? super C0237a> continuation) {
                super(2, continuation);
                this.$imageBitmap = bitmap;
                this.this$0 = fragmentReArrangeViewModel;
                this.$file = file;
                this.$newActivityCategory = categoryAndActivityModel;
                this.$iconNumber = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0237a(this.$imageBitmap, this.this$0, this.$file, this.$newActivityCategory, this.$iconNumber, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0237a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    new ByteArrayOutputStream();
                    try {
                        Bitmap bitmap = this.$imageBitmap;
                        if (bitmap != null) {
                            FragmentReArrangeViewModel fragmentReArrangeViewModel = this.this$0;
                            File file = this.$file;
                            CategoryAndActivityModel categoryAndActivityModel = this.$newActivityCategory;
                            int i = this.$iconNumber;
                            Uri saveToInternalStorage = fragmentReArrangeViewModel.saveToInternalStorage(bitmap, file);
                            fragmentReArrangeViewModel.e.getFilesDir().getAbsolutePath();
                            Intrinsics.checkNotNull(saveToInternalStorage);
                            new File(saveToInternalStorage.getPath());
                            List<DeviceIconModel> deviceIconModels = categoryAndActivityModel.getDeviceIconModels();
                            Intrinsics.checkNotNull(deviceIconModels);
                            fragmentReArrangeViewModel.uploadImageToWatch(file, deviceIconModels.get(i), i);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    LogHelper.d("bitmap", String.valueOf(this.$imageBitmap));
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(CategoryAndActivityModel categoryAndActivityModel, int i, File file, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$newActivityCategory = categoryAndActivityModel;
            this.$iconNumber = i;
            this.$file = file;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$newActivityCategory, this.$iconNumber, this.$file, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentReArrangeViewModel fragmentReArrangeViewModel = FragmentReArrangeViewModel.this;
                CategoryAndActivityModel categoryAndActivityModel = this.$newActivityCategory;
                Intrinsics.checkNotNull(categoryAndActivityModel);
                List<DeviceIconModel> deviceIconModels = categoryAndActivityModel.getDeviceIconModels();
                Intrinsics.checkNotNull(deviceIconModels);
                String url = deviceIconModels.get(this.$iconNumber).getUrl();
                Intrinsics.checkNotNull(url);
                Bitmap c = fragmentReArrangeViewModel.c(url);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C0237a c0237a = new C0237a(c, FragmentReArrangeViewModel.this, this.$file, this.$newActivityCategory, this.$iconNumber, null);
                this.label = 1;
                if (BuildersKt.withContext(main, c0237a, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentReArrangeViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        String simpleName = FragmentReArrangeViewModel.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "javaClass.simpleName");
        this.d = simpleName;
        this.e = application;
    }

    public final Bitmap c(String str) {
        return BitmapFactory.decodeStream(new URL(str).openConnection().getInputStream());
    }

    public final int d(String str) {
        Intrinsics.checkNotNull(str);
        switch (str.hashCode()) {
            case -2014989386:
                return !str.equals("MOTION") ? 0 : 2;
            case -1635077031:
                str.equals("INBUILT");
                return 0;
            case 79223559:
                return !str.equals("STEPS") ? 0 : 1;
            case 1320014927:
                return !str.equals("TOTAL_DURATION") ? 0 : 3;
            default:
                return 0;
        }
    }

    @NotNull
    public final ActivityUpdateListener getActivityUpdateListener() {
        ActivityUpdateListener activityUpdateListener = this.activityUpdateListener;
        if (activityUpdateListener != null) {
            return activityUpdateListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("activityUpdateListener");
        return null;
    }

    @NotNull
    public final CategoryAndActivityModel getCategoryAndActivityModel(@Nullable ActivityCategoriesModel activityCategoriesModel, @Nullable ActivitiesListModel activitiesListModel) {
        CategoryAndActivityModel categoryAndActivityModel = new CategoryAndActivityModel();
        Intrinsics.checkNotNull(activitiesListModel);
        categoryAndActivityModel.setIconUrl(activitiesListModel.getIconUrl());
        Intrinsics.checkNotNull(activityCategoriesModel);
        categoryAndActivityModel.setTitle(activityCategoriesModel.getTitle());
        categoryAndActivityModel.setActivityCode(activitiesListModel.getActivityCode());
        categoryAndActivityModel.setActivityId(activitiesListModel.getActivityId());
        categoryAndActivityModel.setFwActId(activitiesListModel.getFwActId());
        categoryAndActivityModel.setCategoryId(activitiesListModel.getCategoryId());
        categoryAndActivityModel.setShortTitle(activitiesListModel.getShortTitle());
        categoryAndActivityModel.setTitleInMetric(activitiesListModel.getTitleInMetric());
        categoryAndActivityModel.setTitleInImperial(activitiesListModel.getTitleInImperial());
        categoryAndActivityModel.setDvcTitleInImperial(activitiesListModel.getDvcTitleInImperial());
        categoryAndActivityModel.setDvcTitleInMetric(activitiesListModel.getDvcTitleInMetric());
        categoryAndActivityModel.setDescription(activityCategoriesModel.getDescription());
        categoryAndActivityModel.setDeviceIconModels(activitiesListModel.getDeviceIconModels());
        categoryAndActivityModel.setCpaCode(activitiesListModel.getCpaCode());
        categoryAndActivityModel.setCal_func(activitiesListModel.getCal_func());
        categoryAndActivityModel.setInbuilt(activitiesListModel.getInbuilt());
        categoryAndActivityModel.setDescInMetric(activitiesListModel.getDescInMetric());
        categoryAndActivityModel.setDescInImperial(activitiesListModel.getDescInImperial());
        categoryAndActivityModel.setDefaultMets(activitiesListModel.getDefaultMets());
        categoryAndActivityModel.setMetrics(activitiesListModel.getMetrics());
        categoryAndActivityModel.setDefaultActivityIcon(activitiesListModel.getDefaultActivityIcon());
        categoryAndActivityModel.setDefaultCategoryIcon(activitiesListModel.getDefaultCategoryIcon());
        return categoryAndActivityModel;
    }

    public final boolean getMetrics(@NotNull List<String> metrics, @NotNull String metricValue) {
        Intrinsics.checkNotNullParameter(metrics, "metrics");
        Intrinsics.checkNotNullParameter(metricValue, "metricValue");
        return metrics.contains(metricValue);
    }

    public final void saveImageToInternal(@NotNull CategoryAndActivityModel newActivityCategory, int i) {
        Intrinsics.checkNotNullParameter(newActivityCategory, "newActivityCategory");
        String absolutePath = this.e.getFilesDir().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "mContext.filesDir.absolutePath");
        List<DeviceIconModel> deviceIconModels = newActivityCategory.getDeviceIconModels();
        Intrinsics.checkNotNull(deviceIconModels);
        String url = deviceIconModels.get(i).getUrl();
        Intrinsics.checkNotNull(url);
        String uri = new URL(url).getPath();
        Intrinsics.checkNotNullExpressionValue(uri, "uri");
        List split$default = StringsKt__StringsKt.split$default((CharSequence) uri, new String[]{MqttTopic.TOPIC_LEVEL_SEPARATOR}, false, 0, 6, (Object) null);
        e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new a(newActivityCategory, i, new File(absolutePath + '/' + ((String) split$default.get(split$default.size() - 1))), null), 3, null);
    }

    @Nullable
    public final Uri saveToInternalStorage(@NotNull Bitmap bitmap, @NotNull File file) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        Intrinsics.checkNotNullParameter(file, "file");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return Uri.parse(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final void sendActivityToWatch(@NotNull ConfigureActivityListRequest req) {
        Intrinsics.checkNotNullParameter(req, "req");
        BleApiManager.getInstance(this.e).getBleApi().setUserSettings(req, new SettingsResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel$sendActivityToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                Integer errorCode = error.getErrorCode();
                int i = CommandError.WATCH_BUSY.value;
                if (errorCode != null && errorCode.intValue() == i) {
                    FragmentReArrangeViewModel.this.getActivityUpdateListener().onWatchBusyStatusReceived();
                } else {
                    FragmentReArrangeViewModel.this.getActivityUpdateListener().onUpdateFailed();
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                FragmentReArrangeViewModel.this.getActivityUpdateListener().onActivityUpload();
            }
        });
    }

    public final void setActivityUpdateListener(@NotNull ActivityUpdateListener activityUpdateListener) {
        Intrinsics.checkNotNullParameter(activityUpdateListener, "<set-?>");
        this.activityUpdateListener = activityUpdateListener;
    }

    /* JADX WARN: Removed duplicated region for block: B:76:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setConfigureActivityListRequest(@org.jetbrains.annotations.NotNull java.util.List<com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel> r30) {
        /*
            Method dump skipped, instructions count: 685
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel.setConfigureActivityListRequest(java.util.List):void");
    }

    public final void uploadImageToWatch(@NotNull File file, @NotNull DeviceIconModel deviceIconModel, final int i) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(deviceIconModel, "deviceIconModel");
        Boolean compressed = deviceIconModel.getCompressed();
        Intrinsics.checkNotNull(compressed);
        boolean booleanValue = compressed.booleanValue();
        Boolean transparent = deviceIconModel.getTransparent();
        Intrinsics.checkNotNull(transparent);
        boolean booleanValue2 = transparent.booleanValue();
        Integer refId = deviceIconModel.getRefId();
        Intrinsics.checkNotNull(refId);
        int intValue = refId.intValue();
        List<Integer> size = deviceIconModel.getSize();
        Intrinsics.checkNotNull(size);
        int intValue2 = size.get(1).intValue();
        List<Integer> size2 = deviceIconModel.getSize();
        Intrinsics.checkNotNull(size2);
        int intValue3 = size2.get(1).intValue();
        List<Integer> size3 = deviceIconModel.getSize();
        Intrinsics.checkNotNull(size3);
        SendImageRequest sendImageRequest = new SendImageRequest(intValue, file, booleanValue ? 1 : 0, booleanValue2 ? 1 : 0, 0, intValue2, intValue3, size3.get(0).intValue());
        LogHelper.d(this.d, "Image upload called");
        BleApiManager.getInstance(this.e).getBleApi().getData(sendImageRequest, new DataResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel$uploadImageToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                if (error.getErrorCode() != null) {
                    Integer errorCode = error.getErrorCode();
                    int i2 = CommandError.WATCH_BUSY.value;
                    if (errorCode != null && errorCode.intValue() == i2) {
                        FragmentReArrangeViewModel.this.getActivityUpdateListener().onWatchBusyStatusReceived();
                        LogHelper.d("bleres", "error " + error.getErrorMsg());
                    }
                }
                FragmentReArrangeViewModel.this.getActivityUpdateListener().onUpdateFailed();
                LogHelper.d("bleres", "error " + error.getErrorMsg());
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d("bleres", "response " + response.getResponseData());
                FragmentReArrangeViewModel.this.getActivityUpdateListener().onImageUpload(i);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
                LogHelper.d("bleres", "progress " + progress.getProgress());
            }
        });
    }
}
