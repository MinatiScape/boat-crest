package com.coveiot.android.qrtray;

import android.app.NotificationManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.qrtray.fragment.FragmentQrTrayIntro;
import com.coveiot.android.qrtray.fragment.FragmentQrTraySaved;
import com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing;
import com.coveiot.android.qrtray.fragment.FragmentQrTrayUpload;
import com.coveiot.android.qrtray.model.QRCodeDataApp;
import com.coveiot.android.qrtray.model.WatchAndServerQRData;
import com.coveiot.android.qrtray.utils.SingleLiveEvent;
import com.coveiot.android.qrtray.utils.ViewUtilsKt;
import com.coveiot.android.qrtray.viewmodel.QRTrayViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageTwoVerticalBtns;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.coveaccess.qrtray.model.QRTrayCodesRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.sdk.ble.model.QRCodeData;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityQRTray extends BaseActivity implements QRTrayViewModel.QRTrayViewModelContract {
    public QRTrayViewModel p;
    public boolean s;
    public boolean t;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public WatchAndServerQRData q = new WatchAndServerQRData(null, null, null, 7, null);
    @NotNull
    public String r = "";

    @DebugMetadata(c = "com.coveiot.android.qrtray.ActivityQRTray$onDeleteImageCheck$1", f = "ActivityQRTray.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
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
                ActivityQRTray activityQRTray = ActivityQRTray.this;
                String string = activityQRTray.getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                ViewUtilsKt.toast(activityQRTray, string);
                ActivityQRTray.this.dismissProgress();
                ActivityQRTray.this.finish();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.ActivityQRTray$onDeleteQRCheckFromServer$1", f = "ActivityQRTray.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isSuccess;
        public int label;
        public final /* synthetic */ ActivityQRTray this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(boolean z, ActivityQRTray activityQRTray, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$isSuccess = z;
            this.this$0 = activityQRTray;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$isSuccess, this.this$0, continuation);
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
                if (this.$isSuccess) {
                    this.this$0.w();
                } else {
                    ActivityQRTray activityQRTray = this.this$0;
                    String string = activityQRTray.getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                    ViewUtilsKt.toast(activityQRTray, string);
                    this.this$0.dismissProgress();
                    this.this$0.finish();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.ActivityQRTray$onFailure$1", f = "ActivityQRTray.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $message;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(String str, Continuation<? super c> continuation) {
            super(2, continuation);
            this.$message = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(this.$message, continuation);
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
                ActivityQRTray.this.dismissProgress();
                String str = this.$message;
                if (!(str == null || str.length() == 0)) {
                    ViewUtilsKt.toast(ActivityQRTray.this, this.$message);
                } else {
                    ActivityQRTray activityQRTray = ActivityQRTray.this;
                    String string = activityQRTray.getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                    ViewUtilsKt.toast(activityQRTray, string);
                }
                ActivityQRTray.this.finish();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes5.dex */
    public static final class d extends Lambda implements Function1<List<? extends QRTrayCodesRes.QRTrayCodeData>, Unit> {

        @DebugMetadata(c = "com.coveiot.android.qrtray.ActivityQRTray$setObservers$1$1", f = "ActivityQRTray.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ List<QRTrayCodesRes.QRTrayCodeData> $it;
            public int label;
            public final /* synthetic */ ActivityQRTray this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public a(ActivityQRTray activityQRTray, List<? extends QRTrayCodesRes.QRTrayCodeData> list, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = activityQRTray;
                this.$it = list;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, this.$it, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                BleApi bleApi;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.setWatchAndAppDataFromServerToApp(this.$it);
                    BleApiManager bleApiManager = BleApiManager.getInstance(this.this$0);
                    QRTrayViewModel qRTrayViewModel = null;
                    if (((bleApiManager == null || (bleApi = bleApiManager.getBleApi()) == null) ? null : bleApi.getConnectionStatus()) == ConnectionStatus.CONNECTED) {
                        QRTrayViewModel qRTrayViewModel2 = this.this$0.p;
                        if (qRTrayViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                        } else {
                            qRTrayViewModel = qRTrayViewModel2;
                        }
                        qRTrayViewModel.checkQRCodesFromWatch();
                    } else {
                        this.this$0.dismissProgress();
                        ActivityQRTray activityQRTray = this.this$0;
                        String string = activityQRTray.getString(R.string.watch_disconnected_msg);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.watch_disconnected_msg)");
                        ViewUtilsKt.toast(activityQRTray, string);
                        this.this$0.finish();
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public d() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends QRTrayCodesRes.QRTrayCodeData> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(List<? extends QRTrayCodesRes.QRTrayCodeData> list) {
            if (ActivityQRTray.this.isFinishing()) {
                return;
            }
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityQRTray.this), Dispatchers.getMain(), null, new a(ActivityQRTray.this, list, null), 2, null);
        }
    }

    /* loaded from: classes5.dex */
    public static final class e extends Lambda implements Function1<ArrayList<QRCodeData>, Unit> {

        @DebugMetadata(c = "com.coveiot.android.qrtray.ActivityQRTray$setObservers$2$1", f = "ActivityQRTray.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ ArrayList<QRCodeData> $qrCodeData;
            public int label;
            public final /* synthetic */ ActivityQRTray this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ArrayList<QRCodeData> arrayList, ActivityQRTray activityQRTray, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$qrCodeData = arrayList;
                this.this$0 = activityQRTray;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$qrCodeData, this.this$0, continuation);
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
                    ArrayList<QRCodeData> arrayList = this.$qrCodeData;
                    if (!(arrayList == null || arrayList.isEmpty())) {
                        Iterator<QRCodeData> it = this.$qrCodeData.iterator();
                        while (it.hasNext()) {
                            QRCodeData next = it.next();
                            ArrayList<QRCodeDataApp> watchQrCodes = this.this$0.getWatchAndServerQRData().getWatchQrCodes();
                            int imageId = next.getImageId();
                            String imageTitle = next.getImageTitle();
                            String imageTag = next.getImageTag();
                            watchQrCodes.add(new QRCodeDataApp(imageId, imageTitle, imageTag, next.getImageId() + '_' + next.getImageTitle(), null, null, false, null, 240, null));
                        }
                    }
                    this.this$0.u();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public e() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ArrayList<QRCodeData> arrayList) {
            invoke2(arrayList);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(ArrayList<QRCodeData> arrayList) {
            if (ActivityQRTray.this.isFinishing()) {
                return;
            }
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityQRTray.this), Dispatchers.getMain(), null, new a(arrayList, ActivityQRTray.this, null), 2, null);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.ActivityQRTray$uploadingQRCodeMetaDataCheck$1", f = "ActivityQRTray.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public f(Continuation<? super f> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new f(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((f) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ActivityQRTray activityQRTray = ActivityQRTray.this;
                String string = activityQRTray.getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                ViewUtilsKt.toast(activityQRTray, string);
                ActivityQRTray.this.finish();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void B(ActivityQRTray this$0, BottomSheetDialogImageTitleMessageTwoVerticalBtns deleteDialog, View view) {
        BleApi bleApi;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(deleteDialog, "$deleteDialog");
        BleApiManager bleApiManager = BleApiManager.getInstance(this$0);
        if (((bleApiManager == null || (bleApi = bleApiManager.getBleApi()) == null) ? null : bleApi.getConnectionStatus()) == ConnectionStatus.CONNECTED) {
            if (AppUtils.isNetConnected(this$0)) {
                this$0.showProgress();
                this$0.v();
            } else {
                String string = this$0.getString(R.string.no_internet_connection);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.no_internet_connection)");
                ViewUtilsKt.toast(this$0, string);
                this$0.finish();
            }
        } else {
            String string2 = this$0.getString(R.string.device_disconnected);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.device_disconnected)");
            ViewUtilsKt.toast(this$0, string2);
            this$0.finish();
        }
        deleteDialog.dismiss();
    }

    public static final void C(BottomSheetDialogImageTitleMessageTwoVerticalBtns deleteDialog, ActivityQRTray this$0, View view) {
        Intrinsics.checkNotNullParameter(deleteDialog, "$deleteDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        deleteDialog.dismiss();
        this$0.finish();
    }

    public static final void y(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void z(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void A() {
        dismissProgress();
        Drawable drawable = AppCompatResources.getDrawable(this, R.drawable.failure_image_img);
        Intrinsics.checkNotNull(drawable);
        int i = R.string.qr_codes_already_existing_on;
        int i2 = R.string.watch;
        String string = getString(i, new Object[]{getString(i2)});
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …ring.watch)\n            )");
        String string2 = getString(R.string.we_notice_that_there_are_some_pre_existing_qr_codes_on_your_It_is_recommended_that_you_delete_them_before_continuing, new Object[]{getString(i2)});
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n             …ring.watch)\n            )");
        final BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns = new BottomSheetDialogImageTitleMessageTwoVerticalBtns(this, drawable, string, string2);
        bottomSheetDialogImageTitleMessageTwoVerticalBtns.setCancelable(false);
        bottomSheetDialogImageTitleMessageTwoVerticalBtns.showBigIcon();
        String string3 = getString(R.string.delete);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.delete)");
        bottomSheetDialogImageTitleMessageTwoVerticalBtns.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.qrtray.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityQRTray.B(ActivityQRTray.this, bottomSheetDialogImageTitleMessageTwoVerticalBtns, view);
            }
        });
        String string4 = getString(R.string.later);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.later)");
        bottomSheetDialogImageTitleMessageTwoVerticalBtns.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.qrtray.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityQRTray.C(BottomSheetDialogImageTitleMessageTwoVerticalBtns.this, this, view);
            }
        });
        if (bottomSheetDialogImageTitleMessageTwoVerticalBtns.isShowing()) {
            return;
        }
        bottomSheetDialogImageTitleMessageTwoVerticalBtns.show();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final void addOrUpdateServerAppliedQrCode(@NotNull QRCodeDataApp appToUpdate) {
        boolean z;
        Intrinsics.checkNotNullParameter(appToUpdate, "appToUpdate");
        Iterator<QRCodeDataApp> it = this.q.getServerAppliedQrCodes().iterator();
        while (true) {
            z = true;
            boolean z2 = false;
            if (!it.hasNext()) {
                z = false;
                break;
            }
            QRCodeDataApp next = it.next();
            if (appToUpdate.getImageId() == next.getImageId()) {
                next.setImageTitle(appToUpdate.getImageTitle());
                next.setImageTag(appToUpdate.getImageTag());
                next.setId(next.getImageId() + '_' + next.getImageTitle());
                next.setLastAppliedDate(appToUpdate.getLastAppliedDate());
                String serverId = appToUpdate.getServerId();
                if (serverId == null || serverId.length() == 0) {
                    z2 = true;
                }
                if (!z2) {
                    next.setServerId(appToUpdate.getServerId());
                }
            }
        }
        if (z) {
            return;
        }
        this.q.getServerAppliedQrCodes().add(appToUpdate);
    }

    public final void addOrUpdateServerUnAppliedQrCode(@NotNull QRCodeDataApp appToUpdate) {
        boolean z;
        Intrinsics.checkNotNullParameter(appToUpdate, "appToUpdate");
        Iterator<QRCodeDataApp> it = this.q.getServerUnAppliedQrCodes().iterator();
        while (true) {
            z = true;
            boolean z2 = false;
            if (!it.hasNext()) {
                z = false;
                break;
            }
            QRCodeDataApp next = it.next();
            if (appToUpdate.getImageId() == next.getImageId()) {
                next.setImageTitle(appToUpdate.getImageTitle());
                next.setImageTag(appToUpdate.getImageTag());
                next.setId(next.getImageId() + '_' + next.getImageTitle());
                next.setLastAppliedDate(appToUpdate.getLastAppliedDate());
                String serverId = appToUpdate.getServerId();
                if (serverId == null || serverId.length() == 0) {
                    z2 = true;
                }
                if (!z2) {
                    next.setServerId(appToUpdate.getServerId());
                }
            }
        }
        if (z) {
            return;
        }
        this.q.getServerUnAppliedQrCodes().add(appToUpdate);
    }

    public final void addOrUpdateWatchAppliedQrCode(@NotNull QRCodeDataApp appToUpdate) {
        boolean z;
        Intrinsics.checkNotNullParameter(appToUpdate, "appToUpdate");
        Iterator<QRCodeDataApp> it = this.q.getWatchQrCodes().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            QRCodeDataApp next = it.next();
            if (appToUpdate.getImageId() == next.getImageId()) {
                next.setImageTitle(appToUpdate.getImageTitle());
                next.setImageTag(appToUpdate.getImageTag());
                next.setId(next.getImageId() + '_' + next.getImageTitle());
                next.setLastAppliedDate(appToUpdate.getLastAppliedDate());
                next.setServerId(appToUpdate.getServerId());
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        this.q.getWatchQrCodes().add(appToUpdate);
    }

    @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
    public void deleteQRCodeMetaDataCheck(boolean z) {
    }

    @NotNull
    public final ArrayList<QRCodeData> getBleQRCodeListFromWatchCachedData() {
        ArrayList<QRCodeData> arrayList = new ArrayList<>();
        Iterator<QRCodeDataApp> it = this.q.getWatchQrCodes().iterator();
        while (it.hasNext()) {
            QRCodeDataApp next = it.next();
            arrayList.add(new QRCodeData(next.getImageId(), next.getImageTitle(), next.getImageTag()));
        }
        return arrayList;
    }

    @NotNull
    public final ArrayList<QRCodeData> getBleQRCodeListFromWatchCachedDataExcluding(@NotNull QRCodeDataApp qrCodeDataApp) {
        Intrinsics.checkNotNullParameter(qrCodeDataApp, "qrCodeDataApp");
        ArrayList<QRCodeData> arrayList = new ArrayList<>();
        Iterator<QRCodeDataApp> it = this.q.getWatchQrCodes().iterator();
        while (it.hasNext()) {
            QRCodeDataApp next = it.next();
            if (next.getImageId() != qrCodeDataApp.getImageId()) {
                arrayList.add(new QRCodeData(next.getImageId(), next.getImageTitle(), next.getImageTag()));
            }
        }
        return arrayList;
    }

    @NotNull
    public final QRTrayCodesRes.QRTrayCodeData getServerApiQRCodeDataFromAppData(@NotNull QRCodeDataApp qrCodeDataApp) {
        Intrinsics.checkNotNullParameter(qrCodeDataApp, "qrCodeDataApp");
        QRTrayCodesRes.QRTrayCodeData qRTrayCodeData = new QRTrayCodesRes.QRTrayCodeData();
        qRTrayCodeData.setId(qrCodeDataApp.getServerId());
        qRTrayCodeData.setName(qrCodeDataApp.getImageTitle());
        qRTrayCodeData.setCategoryId(qrCodeDataApp.getImageTag());
        qRTrayCodeData.setImageUrl(qrCodeDataApp.getImageUrl());
        qRTrayCodeData.setImageRefId(Integer.valueOf(qrCodeDataApp.getImageId()));
        qRTrayCodeData.setLastAppliedDate(qrCodeDataApp.getLastAppliedDate());
        qRTrayCodeData.setApplied(Boolean.valueOf(qrCodeDataApp.getAppliedToWatch()));
        return qRTrayCodeData;
    }

    @NotNull
    public final String getSource() {
        return this.r;
    }

    @NotNull
    public final WatchAndServerQRData getWatchAndServerQRData() {
        return this.q;
    }

    public final boolean isFromNotification() {
        return this.t;
    }

    public final boolean isFromSmartGrid() {
        return this.s;
    }

    public final void logEvents() {
        String value;
        if (this.t) {
            if (this.s) {
                value = CleverTapConstants.CustomEventValues.HP_GRID.getValue();
            } else {
                value = CleverTapConstants.CustomEventValues.APP_PUSH.getValue();
            }
        } else {
            value = CleverTapConstants.CustomEventValues.WATCH_FEATURES.getValue();
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        hashMap.putAll(companion.getDeviceId(this));
        hashMap.putAll(companion.getWatchDetails(this));
        hashMap.put(CleverTapConstants.CustomEventProperties.SOURCE.getValue(), value);
        companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_HP_VIEWED.getValue(), hashMap);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        Fragment findFragmentById = supportFragmentManager.findFragmentById(R.id.qrContainer);
        if ((findFragmentById instanceof FragmentQrTraySavingNEditing) && ((FragmentQrTraySavingNEditing) findFragmentById).onBackPressed()) {
            return;
        }
        if (findFragmentById instanceof FragmentQrTraySaved) {
            finish();
        } else if (getSupportFragmentManager().getBackStackEntryCount() >= 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_qrtray);
        if (getIntent().getData() != null) {
            this.t = true;
            String valueOf = String.valueOf(getIntent().getStringExtra(CleverTapConstants.CustomEventProperties.SOURCE.getValue()));
            this.r = valueOf;
            this.s = Intrinsics.areEqual(valueOf, CleverTapConstants.CustomEventValues.HP_GRID.getValue());
        }
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            Intrinsics.checkNotNull(extras);
            if (extras.getString("actionId") != null) {
                Bundle extras2 = getIntent().getExtras();
                Intrinsics.checkNotNull(extras2);
                boolean z = extras2.getBoolean("autoCancel", true);
                Bundle extras3 = getIntent().getExtras();
                Intrinsics.checkNotNull(extras3);
                int i = extras3.getInt(Constants.PT_NOTIF_ID, -1);
                if (z && i > -1) {
                    Object systemService = getApplicationContext().getSystemService("notification");
                    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
                    ((NotificationManager) systemService).cancel(i);
                }
            }
        }
        ViewModel viewModel = new ViewModelProvider(this).get(QRTrayViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider(this)[…rayViewModel::class.java]");
        QRTrayViewModel qRTrayViewModel = (QRTrayViewModel) viewModel;
        this.p = qRTrayViewModel;
        QRTrayViewModel qRTrayViewModel2 = null;
        if (qRTrayViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
            qRTrayViewModel = null;
        }
        qRTrayViewModel.setListener(this);
        showProgress();
        x();
        QRTrayViewModel qRTrayViewModel3 = this.p;
        if (qRTrayViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
        } else {
            qRTrayViewModel2 = qRTrayViewModel3;
        }
        qRTrayViewModel2.getQRCodesFromServer();
    }

    @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
    public void onDeleteImageCheck(boolean z) {
        QRTrayViewModel qRTrayViewModel = 0;
        if (z) {
            this.q.getWatchQrCodes().remove(0);
            if (this.q.getWatchQrCodes().isEmpty()) {
                if (!this.q.getServerAppliedQrCodes().isEmpty()) {
                    ArrayList arrayList = new ArrayList();
                    Iterator<QRCodeDataApp> it = this.q.getServerAppliedQrCodes().iterator();
                    while (it.hasNext()) {
                        QRCodeDataApp next = it.next();
                        next.getImageId();
                        if (next.getImageId() != 0) {
                            QRTrayCodesRes.QRTrayCodeData qRTrayCodeData = new QRTrayCodesRes.QRTrayCodeData();
                            qRTrayCodeData.setId(String.valueOf(next.getServerId()));
                            arrayList.add(qRTrayCodeData);
                        }
                    }
                    QRTrayViewModel qRTrayViewModel2 = this.p;
                    if (qRTrayViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                    } else {
                        qRTrayViewModel = qRTrayViewModel2;
                    }
                    qRTrayViewModel.deleteQRCodeFromServer(arrayList);
                    return;
                }
                w();
                return;
            }
            QRTrayViewModel qRTrayViewModel3 = this.p;
            if (qRTrayViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
            } else {
                qRTrayViewModel = qRTrayViewModel3;
            }
            qRTrayViewModel.deleteQRCodeImageFromWatch(this.q.getWatchQrCodes().get(0).getImageId());
        } else if (isFinishing()) {
        } else {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
        }
    }

    @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
    public void onDeleteQRCheckFromServer(boolean z) {
        if (isFinishing()) {
            return;
        }
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new b(z, this, null), 2, null);
    }

    @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
    public void onEditCheck(boolean z, boolean z2) {
    }

    @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
    public void onFailure(@Nullable String str) {
        if (isFinishing()) {
            return;
        }
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new c(str, null), 2, null);
    }

    @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
    public void onSuccess(boolean z) {
    }

    public final void removeQRCodeListFromWatchCachedData(int i) {
        QRCodeDataApp qRCodeDataApp;
        Iterator<QRCodeDataApp> it = this.q.getWatchQrCodes().iterator();
        while (true) {
            if (!it.hasNext()) {
                qRCodeDataApp = null;
                break;
            }
            qRCodeDataApp = it.next();
            if (i == qRCodeDataApp.getImageId()) {
                break;
            }
        }
        if (qRCodeDataApp != null) {
            this.q.getWatchQrCodes().remove(qRCodeDataApp);
        }
    }

    public final void removeServerAppliedQrCode(@NotNull QRCodeDataApp appToUpdate) {
        boolean z;
        QRCodeDataApp qRCodeDataApp;
        Intrinsics.checkNotNullParameter(appToUpdate, "appToUpdate");
        Iterator<QRCodeDataApp> it = this.q.getServerAppliedQrCodes().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                qRCodeDataApp = null;
                break;
            }
            qRCodeDataApp = it.next();
            if (appToUpdate.getImageId() == qRCodeDataApp.getImageId()) {
                z = true;
                break;
            }
        }
        if (z) {
            TypeIntrinsics.asMutableCollection(this.q.getServerAppliedQrCodes()).remove(qRCodeDataApp);
        }
    }

    public final void removeServerUnAppliedQrCode(@NotNull QRCodeDataApp appToUpdate) {
        boolean z;
        QRCodeDataApp qRCodeDataApp;
        Intrinsics.checkNotNullParameter(appToUpdate, "appToUpdate");
        Iterator<QRCodeDataApp> it = this.q.getServerUnAppliedQrCodes().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                qRCodeDataApp = null;
                break;
            }
            qRCodeDataApp = it.next();
            if (appToUpdate.getImageId() == qRCodeDataApp.getImageId()) {
                z = true;
                break;
            }
        }
        if (z) {
            TypeIntrinsics.asMutableCollection(this.q.getServerUnAppliedQrCodes()).remove(qRCodeDataApp);
        }
    }

    public final void setFromNotification(boolean z) {
        this.t = z;
    }

    public final void setFromSmartGrid(boolean z) {
        this.s = z;
    }

    public final void setSource(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.r = str;
    }

    public final void setWatchAndAppDataFromServerToApp(@Nullable List<? extends QRTrayCodesRes.QRTrayCodeData> list) {
        this.q.getServerAppliedQrCodes().clear();
        this.q.getServerUnAppliedQrCodes().clear();
        if (list != null) {
            for (QRTrayCodesRes.QRTrayCodeData qRTrayCodeData : list) {
                Boolean applied = qRTrayCodeData.getApplied();
                Intrinsics.checkNotNullExpressionValue(applied, "qrData.applied");
                if (applied.booleanValue()) {
                    ArrayList<QRCodeDataApp> serverAppliedQrCodes = this.q.getServerAppliedQrCodes();
                    Integer imageRefId = qRTrayCodeData.getImageRefId();
                    Intrinsics.checkNotNullExpressionValue(imageRefId, "qrData.imageRefId");
                    int intValue = imageRefId.intValue();
                    String name = qRTrayCodeData.getName();
                    String categoryId = qRTrayCodeData.getCategoryId();
                    serverAppliedQrCodes.add(new QRCodeDataApp(intValue, name, categoryId, qRTrayCodeData.getImageRefId() + '_' + qRTrayCodeData.getName(), qRTrayCodeData.getLastAppliedDate(), qRTrayCodeData.getId(), true, qRTrayCodeData.getImageUrl()));
                } else {
                    ArrayList<QRCodeDataApp> serverUnAppliedQrCodes = this.q.getServerUnAppliedQrCodes();
                    Integer imageRefId2 = qRTrayCodeData.getImageRefId() != null ? qRTrayCodeData.getImageRefId() : 0;
                    Intrinsics.checkNotNullExpressionValue(imageRefId2, "if (qrData.imageRefId !=… qrData.imageRefId else 0");
                    int intValue2 = imageRefId2.intValue();
                    String name2 = qRTrayCodeData.getName();
                    String categoryId2 = qRTrayCodeData.getCategoryId();
                    serverUnAppliedQrCodes.add(new QRCodeDataApp(intValue2, name2, categoryId2, qRTrayCodeData.getImageRefId() + '_' + qRTrayCodeData.getName(), qRTrayCodeData.getLastAppliedDate(), qRTrayCodeData.getId(), false, qRTrayCodeData.getImageUrl()));
                }
            }
        }
    }

    public final void setWatchAndServerQRData(@NotNull WatchAndServerQRData watchAndServerQRData) {
        Intrinsics.checkNotNullParameter(watchAndServerQRData, "<set-?>");
        this.q = watchAndServerQRData;
    }

    public final void u() {
        boolean z;
        if (this.q.getWatchQrCodes().size() != this.q.getServerAppliedQrCodes().size()) {
            A();
        } else if (this.q.getWatchQrCodes().isEmpty() && this.q.getServerAppliedQrCodes().isEmpty()) {
            w();
        } else {
            Iterator<QRCodeDataApp> it = this.q.getWatchQrCodes().iterator();
            while (true) {
                z = true;
                boolean z2 = false;
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                QRCodeDataApp next = it.next();
                Iterator<QRCodeDataApp> it2 = this.q.getServerAppliedQrCodes().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    } else if (next.getImageId() == it2.next().getImageId()) {
                        z2 = true;
                        continue;
                        break;
                    }
                }
                if (!z2) {
                    break;
                }
            }
            if (!z) {
                w();
            } else {
                A();
            }
        }
    }

    public final void updateServerAppliedQrCode(@NotNull QRCodeDataApp appToUpdate) {
        boolean z;
        Intrinsics.checkNotNullParameter(appToUpdate, "appToUpdate");
        Iterator<QRCodeDataApp> it = this.q.getServerAppliedQrCodes().iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            QRCodeDataApp next = it.next();
            if (appToUpdate.getImageId() == next.getImageId()) {
                next.setImageTitle(appToUpdate.getImageTitle());
                next.setImageTag(appToUpdate.getImageTag());
                next.setId(next.getImageId() + '_' + next.getImageTitle());
                String lastAppliedDate = appToUpdate.getLastAppliedDate();
                if (!(lastAppliedDate == null || lastAppliedDate.length() == 0)) {
                    next.setLastAppliedDate(appToUpdate.getLastAppliedDate());
                }
                String serverId = appToUpdate.getServerId();
                if (!(serverId == null || serverId.length() == 0)) {
                    next.setServerId(appToUpdate.getServerId());
                }
                String imageUrl = appToUpdate.getImageUrl();
                if (imageUrl == null || imageUrl.length() == 0) {
                    z = true;
                }
                if (!z) {
                    next.setImageUrl(appToUpdate.getImageUrl());
                }
                next.setAppliedToWatch(true);
                z = true;
            }
        }
        if (z) {
            return;
        }
        this.q.getServerAppliedQrCodes().remove(appToUpdate);
    }

    public final void updateServerUnAppliedQrCode(@NotNull QRCodeDataApp appToUpdate) {
        boolean z;
        Intrinsics.checkNotNullParameter(appToUpdate, "appToUpdate");
        Iterator<QRCodeDataApp> it = this.q.getServerUnAppliedQrCodes().iterator();
        while (true) {
            z = true;
            boolean z2 = false;
            if (!it.hasNext()) {
                z = false;
                break;
            }
            QRCodeDataApp next = it.next();
            if (appToUpdate.getImageId() == next.getImageId()) {
                next.setImageTitle(appToUpdate.getImageTitle());
                next.setImageTag(appToUpdate.getImageTag());
                next.setId(next.getImageId() + '_' + next.getImageTitle());
                next.setLastAppliedDate(appToUpdate.getLastAppliedDate());
                String serverId = appToUpdate.getServerId();
                if (serverId == null || serverId.length() == 0) {
                    z2 = true;
                }
                if (!z2) {
                    next.setServerId(appToUpdate.getServerId());
                }
            }
        }
        if (z) {
            return;
        }
        this.q.getServerUnAppliedQrCodes().remove(appToUpdate);
    }

    @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
    public void uploadingImageCheck(boolean z) {
    }

    @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
    public void uploadingQRCodeMetaDataCheck(boolean z) {
        QRTrayViewModel qRTrayViewModel = null;
        if (z) {
            QRTrayViewModel qRTrayViewModel2 = this.p;
            if (qRTrayViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
            } else {
                qRTrayViewModel = qRTrayViewModel2;
            }
            qRTrayViewModel.deleteQRCodeImageFromWatch(this.q.getWatchQrCodes().get(0).getImageId());
        } else if (isFinishing()) {
        } else {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new f(null), 2, null);
        }
    }

    public final void v() {
        QRTrayViewModel qRTrayViewModel = 0;
        if (this.q.getWatchQrCodes().isEmpty()) {
            if (!this.q.getServerAppliedQrCodes().isEmpty()) {
                ArrayList arrayList = new ArrayList();
                Iterator<QRCodeDataApp> it = this.q.getServerAppliedQrCodes().iterator();
                while (it.hasNext()) {
                    QRCodeDataApp next = it.next();
                    next.getImageId();
                    if (next.getImageId() != 0) {
                        QRTrayCodesRes.QRTrayCodeData qRTrayCodeData = new QRTrayCodesRes.QRTrayCodeData();
                        qRTrayCodeData.setId(String.valueOf(next.getServerId()));
                        arrayList.add(qRTrayCodeData);
                    }
                }
                Iterator<QRCodeDataApp> it2 = this.q.getServerUnAppliedQrCodes().iterator();
                while (it2.hasNext()) {
                    QRCodeDataApp next2 = it2.next();
                    next2.getImageId();
                    if (next2.getImageId() != 0) {
                        QRTrayCodesRes.QRTrayCodeData qRTrayCodeData2 = new QRTrayCodesRes.QRTrayCodeData();
                        qRTrayCodeData2.setId(String.valueOf(next2.getServerId()));
                        arrayList.add(qRTrayCodeData2);
                    }
                }
                QRTrayViewModel qRTrayViewModel2 = this.p;
                if (qRTrayViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                } else {
                    qRTrayViewModel = qRTrayViewModel2;
                }
                qRTrayViewModel.deleteQRCodeFromServer(arrayList);
                return;
            }
            w();
            return;
        }
        QRTrayViewModel qRTrayViewModel3 = this.p;
        if (qRTrayViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
        } else {
            qRTrayViewModel = qRTrayViewModel3;
        }
        qRTrayViewModel.setQRCodesMetaDataToWatch(new ArrayList<>());
    }

    public final void w() {
        dismissProgress();
        if (SessionManager.getInstance(this).isQrTrayIntroSeen()) {
            if (!(!this.q.getServerAppliedQrCodes().isEmpty()) && !(!this.q.getServerUnAppliedQrCodes().isEmpty())) {
                getSupportFragmentManager().beginTransaction().replace(R.id.qrContainer, new FragmentQrTrayUpload()).commit();
                return;
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.qrContainer, new FragmentQrTraySaved()).commit();
                return;
            }
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.qrContainer, new FragmentQrTrayIntro()).commit();
    }

    public final void x() {
        QRTrayViewModel qRTrayViewModel = this.p;
        QRTrayViewModel qRTrayViewModel2 = null;
        if (qRTrayViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
            qRTrayViewModel = null;
        }
        MutableLiveData<List<QRTrayCodesRes.QRTrayCodeData>> qrCodeMutableList = qRTrayViewModel.getQrCodeMutableList();
        final d dVar = new d();
        qrCodeMutableList.observe(this, new Observer() { // from class: com.coveiot.android.qrtray.c
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityQRTray.y(Function1.this, obj);
            }
        });
        QRTrayViewModel qRTrayViewModel3 = this.p;
        if (qRTrayViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
        } else {
            qRTrayViewModel2 = qRTrayViewModel3;
        }
        SingleLiveEvent<ArrayList<QRCodeData>> qrCodeSFromWatch = qRTrayViewModel2.getQrCodeSFromWatch();
        final e eVar = new e();
        qrCodeSFromWatch.observe(this, new Observer() { // from class: com.coveiot.android.qrtray.d
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityQRTray.z(Function1.this, obj);
            }
        });
    }
}
