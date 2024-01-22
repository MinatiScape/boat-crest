package com.coveiot.android.qrtray.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.qrtray.ActivityQRTray;
import com.coveiot.android.qrtray.R;
import com.coveiot.android.qrtray.adapter.QRTrayCategoryAdapter;
import com.coveiot.android.qrtray.databinding.FragmentQrTraySavingBinding;
import com.coveiot.android.qrtray.dialog.BottomSheetDialogQRTray;
import com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing;
import com.coveiot.android.qrtray.model.QRCodeDataApp;
import com.coveiot.android.qrtray.utils.QRCodeUtils;
import com.coveiot.android.qrtray.utils.SingleLiveEvent;
import com.coveiot.android.qrtray.viewmodel.QRTrayViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneTitleMessageTwoButtons;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.coveaccess.qrtray.model.QRTrayCategoriesRes;
import com.coveiot.coveaccess.qrtray.model.QRTrayCodesRes;
import com.coveiot.coveaccess.qrtray.model.QRTraySaveReq;
import com.coveiot.sdk.ble.model.QRCodeData;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.logger.RingLogger;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentQrTraySavingNEditing extends BaseFragment implements QRTrayCategoryAdapter.CategoryClickListener, QRTrayViewModel.QRTrayViewModelContract {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public QRCodeDataApp A;
    public boolean B;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean J;
    public FragmentQrTraySavingBinding m;
    public boolean n;
    @Nullable
    public QRTrayCodesRes.QRTrayCodeData p;
    @Nullable
    public BottomSheetDialogImageTitleMessage r;
    @Nullable
    public Bitmap s;
    public QRTrayViewModel t;
    public QRTrayCategoryAdapter u;
    @Nullable
    public BottomSheetDialogQRTray y;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public String o = "";
    @NotNull
    public QRCodeData q = new QRCodeData(59000, "", "");
    @NotNull
    public String v = "";
    @NotNull
    public ArrayList<QRTrayCategoriesRes.QRItem> w = new ArrayList<>();
    @NotNull
    public QRTraySaveReq x = new QRTraySaveReq("", "", "", Boolean.FALSE, 59000, "");
    @NotNull
    public ArrayList<QRCodeDataApp> z = new ArrayList<>();
    @NotNull
    public String C = "";
    @NotNull
    public QRCodeDataApp K = new QRCodeDataApp(0, null, null, null, null, null, false, null, 255, null);

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentQrTraySavingNEditing newInstance(int i, @Nullable String str, boolean z, @Nullable QRTrayCodesRes.QRTrayCodeData qRTrayCodeData, boolean z2) {
            FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = new FragmentQrTraySavingNEditing();
            Bundle bundle = new Bundle();
            bundle.putInt("qrCodeImageID", i);
            bundle.putSerializable("qrCodeMetaData", str);
            bundle.putBoolean("qrCodeEdit", z);
            bundle.putSerializable("qrCodeEditData", qRTrayCodeData);
            bundle.putBoolean("fromMenu", z2);
            fragmentQrTraySavingNEditing.setArguments(bundle);
            return fragmentQrTraySavingNEditing;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$deleteQRCodeMetaDataCheck$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                FragmentQrTraySavingNEditing.this.u();
                FragmentQrTraySavingNEditing.this.dismissProgress();
                FragmentQrTraySavingNEditing.this.I(false);
                FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = FragmentQrTraySavingNEditing.this;
                String string = fragmentQrTraySavingNEditing.getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                fragmentQrTraySavingNEditing.showSuccessNErrorToast(string);
                FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing2 = FragmentQrTraySavingNEditing.this;
                String string2 = fragmentQrTraySavingNEditing2.getString(R.string.error);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.error)");
                String string3 = FragmentQrTraySavingNEditing.this.getString(R.string.qr_code_upload_could_not_be_completed);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qr_co…d_could_not_be_completed)");
                Drawable drawable = AppCompatResources.getDrawable(FragmentQrTraySavingNEditing.this.requireContext(), R.drawable.ic_red_circular_cross);
                Intrinsics.checkNotNull(drawable);
                fragmentQrTraySavingNEditing2.G(false, string2, string3, drawable, true);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$discardChangesDialog$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing, BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, View view) {
            fragmentQrTraySavingNEditing.requireActivity().getSupportFragmentManager().popBackStack();
            bottomSheetDialogTwoButtons.dismiss();
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
                Context requireContext = FragmentQrTraySavingNEditing.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                String string = FragmentQrTraySavingNEditing.this.getString(R.string.qr_code);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qr_code)");
                String string2 = FragmentQrTraySavingNEditing.this.getString(R.string.qr_code_save_changes_info);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qr_code_save_changes_info)");
                final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(requireContext, string, string2, false, 8, null);
                bottomSheetDialogTwoButtons.setCrossButtonVisible();
                String string3 = FragmentQrTraySavingNEditing.this.getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
                final FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = FragmentQrTraySavingNEditing.this;
                bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.v
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentQrTraySavingNEditing.b.invokeSuspend$lambda$0(FragmentQrTraySavingNEditing.this, bottomSheetDialogTwoButtons, view);
                    }
                });
                String string4 = FragmentQrTraySavingNEditing.this.getString(R.string.cancel);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
                bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.w
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BottomSheetDialogTwoButtons.this.dismiss();
                    }
                });
                bottomSheetDialogTwoButtons.setCrossButton(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.x
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BottomSheetDialogTwoButtons.this.dismiss();
                    }
                });
                bottomSheetDialogTwoButtons.show();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$logPushFailedEvent$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
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
                HashMap<String, Object> hashMap = new HashMap<>();
                DeviceUtils.Companion companion = DeviceUtils.Companion;
                Context requireContext = FragmentQrTraySavingNEditing.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                hashMap.putAll(companion.getDeviceId(requireContext));
                Context requireContext2 = FragmentQrTraySavingNEditing.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                hashMap.putAll(companion.getWatchDetails(requireContext2));
                hashMap.put(CleverTapConstants.CustomEventProperties.QR_NAME.getValue(), StringsKt__StringsKt.trim(FragmentQrTraySavingNEditing.this.s().etQRCodeName.getText().toString()).toString());
                hashMap.put(CleverTapConstants.CustomEventProperties.QR_TAG.getValue(), FragmentQrTraySavingNEditing.this.v);
                if (FragmentQrTraySavingNEditing.this.n) {
                    hashMap.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.EDITED_EXISTING.getValue());
                } else {
                    hashMap.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.NEW.getValue());
                }
                if (FragmentQrTraySavingNEditing.this.getPushNSaveInitiatedToLog()) {
                    hashMap.put(CleverTapConstants.CustomEventProperties.LOCATION.getValue(), CleverTapConstants.CustomEventValues.DETAILS_PAGE.getValue());
                    if (!AppUtils.isNetConnected(FragmentQrTraySavingNEditing.this.requireContext())) {
                        hashMap.put(CleverTapConstants.CustomEventProperties.FAILURE_REASON.getValue(), CleverTapConstants.CustomEventValues.INTERNET_DISCONNECTED.getValue());
                    } else if (BleApiManager.getInstance(FragmentQrTraySavingNEditing.this.requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.DISCONNECTED) {
                        hashMap.put(CleverTapConstants.CustomEventProperties.FAILURE_REASON.getValue(), CleverTapConstants.CustomEventValues.WATCH_DISCONNECTED.getValue());
                    }
                    companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_PUSHTOWATCH_FAILED.getValue(), hashMap);
                    FragmentQrTraySavingNEditing.this.setPushNSaveInitiatedToLog(true);
                } else if (FragmentQrTraySavingNEditing.this.getSaveButtonClickInitiatedToLog() && !FragmentQrTraySavingNEditing.this.G) {
                    if (!AppUtils.isNetConnected(FragmentQrTraySavingNEditing.this.requireContext())) {
                        hashMap.put(CleverTapConstants.CustomEventProperties.FAILURE_REASON.getValue(), CleverTapConstants.CustomEventValues.INTERNET_DISCONNECTED.getValue());
                    }
                    companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_SAVE_FAILED.getValue(), hashMap);
                    FragmentQrTraySavingNEditing.this.G = true;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$onDeleteImageCheck$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isSuccess;
        public int label;
        public final /* synthetic */ FragmentQrTraySavingNEditing this$0;

        @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$onDeleteImageCheck$1$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ FragmentQrTraySavingNEditing this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentQrTraySavingNEditing;
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
                Bitmap bitmapFromUrl;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    QRTrayViewModel qRTrayViewModel = this.this$0.t;
                    if (qRTrayViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                        qRTrayViewModel = null;
                    }
                    QRCodeData qRCodeData = this.this$0.q;
                    if (this.this$0.s != null) {
                        bitmapFromUrl = this.this$0.s;
                        Intrinsics.checkNotNull(bitmapFromUrl);
                    } else {
                        QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
                        QRTrayCodesRes.QRTrayCodeData qRTrayCodeData = this.this$0.p;
                        Intrinsics.checkNotNull(qRTrayCodeData);
                        String imageUrl = qRTrayCodeData.getImageUrl();
                        Intrinsics.checkNotNullExpressionValue(imageUrl, "qrCodeEditData!!.imageUrl");
                        bitmapFromUrl = qRCodeUtils.getBitmapFromUrl(imageUrl);
                    }
                    qRTrayViewModel.uploadQRCodeImageToWatch(qRCodeData, bitmapFromUrl);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(boolean z, FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing, Continuation<? super d> continuation) {
            super(2, continuation);
            this.$isSuccess = z;
            this.this$0 = fragmentQrTraySavingNEditing;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d(this.$isSuccess, this.this$0, continuation);
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
                if (this.$isSuccess) {
                    QRTrayViewModel qRTrayViewModel = null;
                    if (this.this$0.E) {
                        QRTrayViewModel qRTrayViewModel2 = this.this$0.t;
                        if (qRTrayViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                        } else {
                            qRTrayViewModel = qRTrayViewModel2;
                        }
                        FragmentActivity requireActivity = this.this$0.requireActivity();
                        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                        qRTrayViewModel.deleteQRCodeFromServer(CollectionsKt__CollectionsKt.arrayListOf(((ActivityQRTray) requireActivity).getServerApiQRCodeDataFromAppData(this.this$0.K)));
                    } else {
                        QRCodeData qRCodeData = this.this$0.q;
                        QRCodeDataApp qRCodeDataApp = this.this$0.A;
                        Intrinsics.checkNotNull(qRCodeDataApp);
                        qRCodeData.setImageId(qRCodeDataApp.getImageId());
                        this.this$0.q.setImageTitle(StringsKt__StringsKt.trim(this.this$0.s().etQRCodeName.getText().toString()).toString());
                        this.this$0.q.setImageTag(this.this$0.v);
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new a(this.this$0, null), 2, null);
                    }
                } else {
                    this.this$0.u();
                    this.this$0.dismissProgress();
                    this.this$0.I(false);
                    FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = this.this$0;
                    String string = fragmentQrTraySavingNEditing.getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                    fragmentQrTraySavingNEditing.showSuccessNErrorToast(string);
                    this.this$0.requireActivity().finish();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$onDeleteQRCheckFromServer$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isSuccess;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(boolean z, Continuation<? super e> continuation) {
            super(2, continuation);
            this.$isSuccess = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new e(this.$isSuccess, continuation);
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
                FragmentQrTraySavingNEditing.this.E = false;
                FragmentQrTraySavingNEditing.this.dismissProgress();
                if (this.$isSuccess) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    Context requireContext = FragmentQrTraySavingNEditing.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    hashMap.putAll(companion.getDeviceId(requireContext));
                    Context requireContext2 = FragmentQrTraySavingNEditing.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    hashMap.putAll(companion.getWatchDetails(requireContext2));
                    hashMap.put(CleverTapConstants.CustomEventProperties.QR_NAME.getValue(), StringsKt__StringsKt.trim(FragmentQrTraySavingNEditing.this.s().etQRCodeName.getText().toString()).toString());
                    hashMap.put(CleverTapConstants.CustomEventProperties.QR_TAG.getValue(), FragmentQrTraySavingNEditing.this.v);
                    hashMap.put(CleverTapConstants.CustomEventProperties.LOCATION.getValue(), CleverTapConstants.CustomEventValues.DETAILS_PAGE.getValue());
                    companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_DELETE_SUCCESS.getValue(), hashMap);
                    FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = FragmentQrTraySavingNEditing.this;
                    String string = fragmentQrTraySavingNEditing.getString(R.string.deleted_qr_code);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.deleted_qr_code)");
                    fragmentQrTraySavingNEditing.showSuccessNErrorToast(string);
                    FragmentQrTraySavingNEditing.this.requireActivity().getSupportFragmentManager().popBackStack();
                } else {
                    FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing2 = FragmentQrTraySavingNEditing.this;
                    String string2 = fragmentQrTraySavingNEditing2.getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.some_thing_went_wrong)");
                    fragmentQrTraySavingNEditing2.showSuccessNErrorToast(string2);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$onEditCheck$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isAppliedOnWatch;
        public final /* synthetic */ boolean $isSuccess;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(boolean z, boolean z2, Continuation<? super f> continuation) {
            super(2, continuation);
            this.$isSuccess = z;
            this.$isAppliedOnWatch = z2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new f(this.$isSuccess, this.$isAppliedOnWatch, continuation);
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
                FragmentQrTraySavingNEditing.this.dismissProgress();
                FragmentQrTraySavingNEditing.this.I(false);
                if (!this.$isSuccess) {
                    FragmentQrTraySavingNEditing.this.u();
                    FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = FragmentQrTraySavingNEditing.this;
                    String string = fragmentQrTraySavingNEditing.getString(R.string.something_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.something_went_wrong)");
                    fragmentQrTraySavingNEditing.showSuccessNErrorToast(string);
                    FragmentQrTraySavingNEditing.this.requireActivity().finish();
                } else {
                    if (this.$isAppliedOnWatch) {
                        FragmentActivity requireActivity = FragmentQrTraySavingNEditing.this.requireActivity();
                        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                        Integer imageRefId = FragmentQrTraySavingNEditing.this.x.getImageRefId();
                        Intrinsics.checkNotNullExpressionValue(imageRefId, "setQRTraySaveEditReq.imageRefId");
                        ((ActivityQRTray) requireActivity).updateServerAppliedQrCode(new QRCodeDataApp(imageRefId.intValue(), FragmentQrTraySavingNEditing.this.x.getName(), FragmentQrTraySavingNEditing.this.x.getCategoryId(), null, null, null, true, FragmentQrTraySavingNEditing.this.x.getMediaId(), 56, null));
                        HashMap<String, Object> hashMap = new HashMap<>();
                        DeviceUtils.Companion companion = DeviceUtils.Companion;
                        Context requireContext = FragmentQrTraySavingNEditing.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        hashMap.putAll(companion.getDeviceId(requireContext));
                        Context requireContext2 = FragmentQrTraySavingNEditing.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        hashMap.putAll(companion.getWatchDetails(requireContext2));
                        hashMap.put(CleverTapConstants.CustomEventProperties.QR_NAME.getValue(), FragmentQrTraySavingNEditing.this.x.getName());
                        hashMap.put(CleverTapConstants.CustomEventProperties.QR_TAG.getValue(), FragmentQrTraySavingNEditing.this.x.getCategoryId());
                        hashMap.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.EDITED_EXISTING.getValue());
                        if (FragmentQrTraySavingNEditing.this.D) {
                            hashMap.put(CleverTapConstants.CustomEventProperties.TYPE2.getValue(), CleverTapConstants.CustomEventValues.REPLACED.getValue());
                        } else {
                            hashMap.put(CleverTapConstants.CustomEventProperties.TYPE2.getValue(), CleverTapConstants.CustomEventValues.NA.getValue());
                        }
                        hashMap.put(CleverTapConstants.CustomEventProperties.LOCATION.getValue(), CleverTapConstants.CustomEventValues.DETAILS_PAGE.getValue());
                        companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_PUSHTOWATCH_SUCCESS.getValue(), hashMap);
                    } else {
                        FragmentActivity requireActivity2 = FragmentQrTraySavingNEditing.this.requireActivity();
                        Intrinsics.checkNotNull(requireActivity2, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                        Integer imageRefId2 = FragmentQrTraySavingNEditing.this.x.getImageRefId();
                        Intrinsics.checkNotNullExpressionValue(imageRefId2, "setQRTraySaveEditReq.imageRefId");
                        ((ActivityQRTray) requireActivity2).updateServerUnAppliedQrCode(new QRCodeDataApp(imageRefId2.intValue(), FragmentQrTraySavingNEditing.this.x.getName(), FragmentQrTraySavingNEditing.this.x.getCategoryId(), null, null, null, false, FragmentQrTraySavingNEditing.this.x.getMediaId(), 56, null));
                        HashMap<String, Object> hashMap2 = new HashMap<>();
                        DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                        Context requireContext3 = FragmentQrTraySavingNEditing.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                        hashMap2.putAll(companion2.getDeviceId(requireContext3));
                        Context requireContext4 = FragmentQrTraySavingNEditing.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                        hashMap2.putAll(companion2.getWatchDetails(requireContext4));
                        hashMap2.put(CleverTapConstants.CustomEventProperties.QR_NAME.getValue(), FragmentQrTraySavingNEditing.this.x.getName());
                        hashMap2.put(CleverTapConstants.CustomEventProperties.QR_TAG.getValue(), FragmentQrTraySavingNEditing.this.x.getCategoryId());
                        hashMap2.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.EDITED_EXISTING.getValue());
                        companion2.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_SAVE_SUCCESS.getValue(), hashMap2);
                    }
                    if (FragmentQrTraySavingNEditing.this.B) {
                        FragmentQrTraySavingNEditing.this.B = false;
                        FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing2 = FragmentQrTraySavingNEditing.this;
                        String string2 = fragmentQrTraySavingNEditing2.getString(R.string.the_oldest_qr_code_replaced);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n             …                        )");
                        String string3 = FragmentQrTraySavingNEditing.this.getString(R.string.double_tap_the_watch_screen_to_change_the_brightness_level_in_case_qr_code_scanner_doesn_t_work);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.doubl…ode_scanner_doesn_t_work)");
                        Drawable drawable = AppCompatResources.getDrawable(FragmentQrTraySavingNEditing.this.requireContext(), R.drawable.ic_green_circular_tick);
                        Intrinsics.checkNotNull(drawable);
                        FragmentQrTraySavingNEditing.H(fragmentQrTraySavingNEditing2, true, string2, string3, drawable, false, 16, null);
                    } else {
                        FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing3 = FragmentQrTraySavingNEditing.this;
                        String string4 = fragmentQrTraySavingNEditing3.getString(this.$isAppliedOnWatch ? R.string.qrcode_updated_successful_title : R.string.qrcode_updated_successful_title_app);
                        Intrinsics.checkNotNullExpressionValue(string4, "if (isAppliedOnWatch) ge…                        )");
                        Drawable drawable2 = AppCompatResources.getDrawable(FragmentQrTraySavingNEditing.this.requireContext(), R.drawable.ic_green_circular_tick);
                        Intrinsics.checkNotNull(drawable2);
                        fragmentQrTraySavingNEditing3.G(true, string4, "", drawable2, false);
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$onFailure$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $message;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(String str, Continuation<? super g> continuation) {
            super(2, continuation);
            this.$message = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new g(this.$message, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((g) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentQrTraySavingNEditing.this.dismissProgress();
                FragmentQrTraySavingNEditing.this.I(false);
                FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = FragmentQrTraySavingNEditing.this;
                String str = this.$message;
                if (str == null) {
                    str = fragmentQrTraySavingNEditing.getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(str, "getString(R.string.some_thing_went_wrong)");
                }
                fragmentQrTraySavingNEditing.showSuccessNErrorToast(str);
                FragmentQrTraySavingNEditing.this.requireActivity().finish();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$onResume$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class h extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public h(Continuation<? super h> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new h(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((h) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentQrTraySavingNEditing.this.I(false);
                FragmentQrTraySavingNEditing.this.dismissProgress();
                FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = FragmentQrTraySavingNEditing.this;
                String string = fragmentQrTraySavingNEditing.getString(R.string.connect_watch);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.connect_watch)");
                String string2 = FragmentQrTraySavingNEditing.this.getString(R.string.please_connect_your_watch_in_order_to_apply_the_qr_code);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.pleas…der_to_apply_the_qr_code)");
                Drawable drawable = AppCompatResources.getDrawable(FragmentQrTraySavingNEditing.this.requireContext(), R.drawable.ic_red_circular_not_connected);
                Intrinsics.checkNotNull(drawable);
                fragmentQrTraySavingNEditing.G(false, string, string2, drawable, true);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$onSuccess$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class i extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isAppliedOnWatch;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public i(boolean z, Continuation<? super i> continuation) {
            super(2, continuation);
            this.$isAppliedOnWatch = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new i(this.$isAppliedOnWatch, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((i) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentQrTraySavingNEditing.this.dismissProgress();
                FragmentQrTraySavingNEditing.this.I(false);
                if (this.$isAppliedOnWatch) {
                    FragmentActivity requireActivity = FragmentQrTraySavingNEditing.this.requireActivity();
                    Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                    ArrayList<QRCodeDataApp> serverAppliedQrCodes = ((ActivityQRTray) requireActivity).getWatchAndServerQRData().getServerAppliedQrCodes();
                    Integer imageRefId = FragmentQrTraySavingNEditing.this.x.getImageRefId();
                    Intrinsics.checkNotNullExpressionValue(imageRefId, "setQRTraySaveEditReq.imageRefId");
                    serverAppliedQrCodes.add(new QRCodeDataApp(imageRefId.intValue(), FragmentQrTraySavingNEditing.this.x.getName(), FragmentQrTraySavingNEditing.this.x.getCategoryId(), null, null, null, true, null, 184, null));
                    HashMap<String, Object> hashMap = new HashMap<>();
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    Context requireContext = FragmentQrTraySavingNEditing.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    hashMap.putAll(companion.getDeviceId(requireContext));
                    Context requireContext2 = FragmentQrTraySavingNEditing.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    hashMap.putAll(companion.getWatchDetails(requireContext2));
                    hashMap.put(CleverTapConstants.CustomEventProperties.QR_NAME.getValue(), FragmentQrTraySavingNEditing.this.x.getName());
                    hashMap.put(CleverTapConstants.CustomEventProperties.QR_TAG.getValue(), FragmentQrTraySavingNEditing.this.x.getCategoryId());
                    hashMap.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.NEW.getValue());
                    if (FragmentQrTraySavingNEditing.this.D) {
                        hashMap.put(CleverTapConstants.CustomEventProperties.TYPE2.getValue(), CleverTapConstants.CustomEventValues.REPLACED.getValue());
                    } else {
                        hashMap.put(CleverTapConstants.CustomEventProperties.TYPE2.getValue(), CleverTapConstants.CustomEventValues.NA.getValue());
                    }
                    hashMap.put(CleverTapConstants.CustomEventProperties.LOCATION.getValue(), CleverTapConstants.CustomEventValues.DETAILS_PAGE.getValue());
                    companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_PUSHTOWATCH_SUCCESS.getValue(), hashMap);
                } else {
                    FragmentActivity requireActivity2 = FragmentQrTraySavingNEditing.this.requireActivity();
                    Intrinsics.checkNotNull(requireActivity2, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                    ArrayList<QRCodeDataApp> serverUnAppliedQrCodes = ((ActivityQRTray) requireActivity2).getWatchAndServerQRData().getServerUnAppliedQrCodes();
                    Integer imageRefId2 = FragmentQrTraySavingNEditing.this.x.getImageRefId();
                    Intrinsics.checkNotNullExpressionValue(imageRefId2, "setQRTraySaveEditReq.imageRefId");
                    serverUnAppliedQrCodes.add(new QRCodeDataApp(imageRefId2.intValue(), FragmentQrTraySavingNEditing.this.x.getName(), FragmentQrTraySavingNEditing.this.x.getCategoryId(), null, null, null, false, null, RingLogger.EVT_UPDATE, null));
                    HashMap<String, Object> hashMap2 = new HashMap<>();
                    DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                    Context requireContext3 = FragmentQrTraySavingNEditing.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                    hashMap2.putAll(companion2.getDeviceId(requireContext3));
                    Context requireContext4 = FragmentQrTraySavingNEditing.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                    hashMap2.putAll(companion2.getWatchDetails(requireContext4));
                    hashMap2.put(CleverTapConstants.CustomEventProperties.QR_NAME.getValue(), FragmentQrTraySavingNEditing.this.x.getName());
                    hashMap2.put(CleverTapConstants.CustomEventProperties.QR_TAG.getValue(), FragmentQrTraySavingNEditing.this.x.getCategoryId());
                    if (FragmentQrTraySavingNEditing.this.n) {
                        hashMap2.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.EDITED_EXISTING.getValue());
                    } else {
                        hashMap2.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.NEW.getValue());
                    }
                    companion2.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_SAVE_SUCCESS.getValue(), hashMap2);
                }
                FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = FragmentQrTraySavingNEditing.this;
                String string = fragmentQrTraySavingNEditing.getString(this.$isAppliedOnWatch ? R.string.qrcode_updated_successful_title : R.string.qrcode_updated_successful_title_app);
                Intrinsics.checkNotNullExpressionValue(string, "if (isAppliedOnWatch) ge…app\n                    )");
                String string2 = this.$isAppliedOnWatch ? FragmentQrTraySavingNEditing.this.getString(R.string.double_tap_the_watch_screen_to_change_the_brightness_level_in_case_qr_code_scanner_doesn_t_work) : "";
                Intrinsics.checkNotNullExpressionValue(string2, "if (isAppliedOnWatch) ge…ner_doesn_t_work) else \"\"");
                Drawable drawable = AppCompatResources.getDrawable(FragmentQrTraySavingNEditing.this.requireContext(), R.drawable.ic_green_circular_tick);
                Intrinsics.checkNotNull(drawable);
                FragmentQrTraySavingNEditing.H(fragmentQrTraySavingNEditing, true, string, string2, drawable, false, 16, null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$onViewCreated$1$2$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class j extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$onViewCreated$1$2$1$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ FragmentQrTraySavingNEditing this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentQrTraySavingNEditing;
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
                    QRTrayViewModel qRTrayViewModel = this.this$0.t;
                    if (qRTrayViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                        qRTrayViewModel = null;
                    }
                    FragmentActivity requireActivity = this.this$0.requireActivity();
                    Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                    qRTrayViewModel.setQRCodesMetaDataToWatch(((ActivityQRTray) requireActivity).getBleQRCodeListFromWatchCachedData());
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public j(Continuation<? super j> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new j(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((j) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentQrTraySavingNEditing.this.showProgress(false);
                FragmentActivity requireActivity = FragmentQrTraySavingNEditing.this.requireActivity();
                Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData = FragmentQrTraySavingNEditing.this.p;
                Intrinsics.checkNotNull(qRTrayCodeData);
                Integer imageRefId = qRTrayCodeData.getImageRefId();
                Intrinsics.checkNotNullExpressionValue(imageRefId, "qrCodeEditData!!.imageRefId");
                int intValue = imageRefId.intValue();
                String obj2 = StringsKt__StringsKt.trim(FragmentQrTraySavingNEditing.this.s().etQRCodeName.getText().toString()).toString();
                String str = FragmentQrTraySavingNEditing.this.v;
                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData2 = FragmentQrTraySavingNEditing.this.p;
                Intrinsics.checkNotNull(qRTrayCodeData2);
                String lastAppliedDate = qRTrayCodeData2.getLastAppliedDate();
                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData3 = FragmentQrTraySavingNEditing.this.p;
                Intrinsics.checkNotNull(qRTrayCodeData3);
                ((ActivityQRTray) requireActivity).addOrUpdateWatchAppliedQrCode(new QRCodeDataApp(intValue, obj2, str, null, lastAppliedDate, qRTrayCodeData3.getId(), false, null, 192, null));
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentQrTraySavingNEditing.this), Dispatchers.getIO(), null, new a(FragmentQrTraySavingNEditing.this, null), 2, null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$onViewCreated$1$2$2", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class k extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        /* loaded from: classes5.dex */
        public static final class a extends Lambda implements Function1<Integer, Unit> {
            public final /* synthetic */ FragmentQrTraySavingNEditing this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing) {
                super(1);
                this.this$0 = fragmentQrTraySavingNEditing;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                QRCodeData qRCodeData = this.this$0.q;
                Intrinsics.checkNotNull(qRCodeData);
                qRCodeData.setImageId(i);
                FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = this.this$0;
                QRCodeData qRCodeData2 = fragmentQrTraySavingNEditing.q;
                QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData = this.this$0.p;
                Intrinsics.checkNotNull(qRTrayCodeData);
                String imageUrl = qRTrayCodeData.getImageUrl();
                Intrinsics.checkNotNullExpressionValue(imageUrl, "qrCodeEditData!!.imageUrl");
                fragmentQrTraySavingNEditing.A(qRCodeData2, qRCodeUtils.getBitmapFromUrl(imageUrl));
            }
        }

        public k(Continuation<? super k> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new k(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((k) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentQrTraySavingNEditing.this.I(true);
                QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
                Context requireContext = FragmentQrTraySavingNEditing.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                FragmentActivity requireActivity = FragmentQrTraySavingNEditing.this.requireActivity();
                Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                qRCodeUtils.getAvailableQRCodeImageId(requireContext, ((ActivityQRTray) requireActivity).getWatchAndServerQRData().getServerAppliedQrCodes(), new a(FragmentQrTraySavingNEditing.this));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$onViewCreated$1$2$3", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class l extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        /* loaded from: classes5.dex */
        public static final class a extends Lambda implements Function1<Integer, Unit> {
            public final /* synthetic */ FragmentQrTraySavingNEditing this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing) {
                super(1);
                this.this$0 = fragmentQrTraySavingNEditing;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                QRCodeData qRCodeData = this.this$0.q;
                Intrinsics.checkNotNull(qRCodeData);
                qRCodeData.setImageId(i);
                FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = this.this$0;
                QRCodeData qRCodeData2 = fragmentQrTraySavingNEditing.q;
                Bitmap bitmap = this.this$0.s;
                Intrinsics.checkNotNull(bitmap);
                fragmentQrTraySavingNEditing.A(qRCodeData2, bitmap);
            }
        }

        public l(Continuation<? super l> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new l(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((l) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
                Context requireContext = FragmentQrTraySavingNEditing.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                FragmentActivity requireActivity = FragmentQrTraySavingNEditing.this.requireActivity();
                Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                qRCodeUtils.getAvailableQRCodeImageId(requireContext, ((ActivityQRTray) requireActivity).getWatchAndServerQRData().getServerAppliedQrCodes(), new a(FragmentQrTraySavingNEditing.this));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$onViewCreated$1$3$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class m extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public m(Continuation<? super m> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new m(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((m) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Bitmap bitmapFromUrl;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (FragmentQrTraySavingNEditing.this.s != null) {
                    bitmapFromUrl = FragmentQrTraySavingNEditing.this.s;
                } else {
                    QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
                    QRTrayCodesRes.QRTrayCodeData qRTrayCodeData = FragmentQrTraySavingNEditing.this.p;
                    Intrinsics.checkNotNull(qRTrayCodeData);
                    String imageUrl = qRTrayCodeData.getImageUrl();
                    Intrinsics.checkNotNullExpressionValue(imageUrl, "qrCodeEditData!!.imageUrl");
                    bitmapFromUrl = qRCodeUtils.getBitmapFromUrl(imageUrl);
                }
                if (bitmapFromUrl != null) {
                    FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = FragmentQrTraySavingNEditing.this;
                    HashMap<String, Object> hashMap = new HashMap<>();
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    Context requireContext = fragmentQrTraySavingNEditing.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    hashMap.putAll(companion.getDeviceId(requireContext));
                    Context requireContext2 = fragmentQrTraySavingNEditing.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    hashMap.putAll(companion.getWatchDetails(requireContext2));
                    hashMap.put(CleverTapConstants.CustomEventProperties.QR_NAME.getValue(), fragmentQrTraySavingNEditing.x.getName());
                    hashMap.put(CleverTapConstants.CustomEventProperties.QR_TAG.getValue(), fragmentQrTraySavingNEditing.x.getCategoryId());
                    QRTrayViewModel qRTrayViewModel = null;
                    if (fragmentQrTraySavingNEditing.n) {
                        hashMap.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.EDITED_EXISTING.getValue());
                        QRTrayViewModel qRTrayViewModel2 = fragmentQrTraySavingNEditing.t;
                        if (qRTrayViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                            qRTrayViewModel2 = null;
                        }
                        QRTrayViewModel.editQRCodeToServer$default(qRTrayViewModel2, fragmentQrTraySavingNEditing.x, null, 2, null);
                    } else {
                        hashMap.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.NEW.getValue());
                        QRTrayViewModel qRTrayViewModel3 = fragmentQrTraySavingNEditing.t;
                        if (qRTrayViewModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                        } else {
                            qRTrayViewModel = qRTrayViewModel3;
                        }
                        qRTrayViewModel.uploadQRCodePicAndSaveToServer(bitmapFromUrl, fragmentQrTraySavingNEditing.x);
                    }
                    companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_SAVE_REQUEST.getValue(), hashMap);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$onViewCreated$1$5$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class n extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public n(Continuation<? super n> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new n(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((n) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (FragmentQrTraySavingNEditing.this.t()) {
                    FragmentQrTraySavingNEditing.this.r();
                } else {
                    FragmentQrTraySavingNEditing.this.requireActivity().getSupportFragmentManager().popBackStack();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$setBottomSheetDialog$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class o extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$setBottomSheetDialog$1$1$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ FragmentQrTraySavingNEditing this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentQrTraySavingNEditing;
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
                    QRTrayViewModel qRTrayViewModel = this.this$0.t;
                    if (qRTrayViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                        qRTrayViewModel = null;
                    }
                    FragmentActivity requireActivity = this.this$0.requireActivity();
                    Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                    qRTrayViewModel.deleteQRCodesMetaDataToWatch(((ActivityQRTray) requireActivity).getBleQRCodeListFromWatchCachedDataExcluding(this.this$0.K));
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public o(Continuation<? super o> continuation) {
            super(2, continuation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing, BottomSheetDialogOneTitleMessageTwoButtons bottomSheetDialogOneTitleMessageTwoButtons, View view) {
            fragmentQrTraySavingNEditing.E = true;
            fragmentQrTraySavingNEditing.showProgress(false);
            fragmentQrTraySavingNEditing.K.getImageId();
            QRTrayViewModel qRTrayViewModel = null;
            if (fragmentQrTraySavingNEditing.K.getImageId() == 0) {
                QRTrayViewModel qRTrayViewModel2 = fragmentQrTraySavingNEditing.t;
                if (qRTrayViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                } else {
                    qRTrayViewModel = qRTrayViewModel2;
                }
                FragmentActivity requireActivity = fragmentQrTraySavingNEditing.requireActivity();
                Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                qRTrayViewModel.deleteQRCodeFromServer(CollectionsKt__CollectionsKt.arrayListOf(((ActivityQRTray) requireActivity).getServerApiQRCodeDataFromAppData(fragmentQrTraySavingNEditing.K)));
            } else if (BleApiManager.getInstance(fragmentQrTraySavingNEditing.requireContext()).getBleApi() != null && BleApiManager.getInstance(fragmentQrTraySavingNEditing.requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(fragmentQrTraySavingNEditing), Dispatchers.getIO(), null, new a(fragmentQrTraySavingNEditing, null), 2, null);
            } else {
                String string = fragmentQrTraySavingNEditing.getString(R.string.connect_watch);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.connect_watch)");
                String string2 = fragmentQrTraySavingNEditing.getString(R.string.please_connect_your_watch_in_order_to_apply_the_qr_code);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.pleas…der_to_apply_the_qr_code)");
                Drawable drawable = AppCompatResources.getDrawable(fragmentQrTraySavingNEditing.requireContext(), R.drawable.ic_red_circular_not_connected);
                Intrinsics.checkNotNull(drawable);
                fragmentQrTraySavingNEditing.G(true, string, string2, drawable, true);
            }
            bottomSheetDialogOneTitleMessageTwoButtons.dismiss();
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new o(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((o) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = FragmentQrTraySavingNEditing.this;
                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData = fragmentQrTraySavingNEditing.p;
                Intrinsics.checkNotNull(qRTrayCodeData);
                Integer imageRefId = qRTrayCodeData.getImageRefId();
                Intrinsics.checkNotNullExpressionValue(imageRefId, "qrCodeEditData!!.imageRefId");
                int intValue = imageRefId.intValue();
                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData2 = FragmentQrTraySavingNEditing.this.p;
                Intrinsics.checkNotNull(qRTrayCodeData2);
                String title = qRTrayCodeData2.getTitle();
                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData3 = FragmentQrTraySavingNEditing.this.p;
                Intrinsics.checkNotNull(qRTrayCodeData3);
                String categoryId = qRTrayCodeData3.getCategoryId();
                StringBuilder sb = new StringBuilder();
                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData4 = FragmentQrTraySavingNEditing.this.p;
                Intrinsics.checkNotNull(qRTrayCodeData4);
                sb.append(qRTrayCodeData4.getImageRefId());
                sb.append('_');
                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData5 = FragmentQrTraySavingNEditing.this.p;
                Intrinsics.checkNotNull(qRTrayCodeData5);
                sb.append(qRTrayCodeData5.getTitle());
                String sb2 = sb.toString();
                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData6 = FragmentQrTraySavingNEditing.this.p;
                Intrinsics.checkNotNull(qRTrayCodeData6);
                String lastAppliedDate = qRTrayCodeData6.getLastAppliedDate();
                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData7 = FragmentQrTraySavingNEditing.this.p;
                Intrinsics.checkNotNull(qRTrayCodeData7);
                String id = qRTrayCodeData7.getId();
                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData8 = FragmentQrTraySavingNEditing.this.p;
                Intrinsics.checkNotNull(qRTrayCodeData8);
                Boolean applied = qRTrayCodeData8.getApplied();
                Intrinsics.checkNotNullExpressionValue(applied, "qrCodeEditData!!.applied");
                boolean booleanValue = applied.booleanValue();
                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData9 = FragmentQrTraySavingNEditing.this.p;
                Intrinsics.checkNotNull(qRTrayCodeData9);
                fragmentQrTraySavingNEditing.K = new QRCodeDataApp(intValue, title, categoryId, sb2, lastAppliedDate, id, booleanValue, qRTrayCodeData9.getImageUrl());
                Context requireContext = FragmentQrTraySavingNEditing.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                String string = FragmentQrTraySavingNEditing.this.getString(R.string.delete_qr_code);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.delete_qr_code)");
                String string2 = FragmentQrTraySavingNEditing.this.getString(R.string.please_note_that_you_cannot_go_back_once_you_proceed_with_deletion_do_you_wish_to_delete_this_qr_code_permanently);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.pleas…this_qr_code_permanently)");
                final BottomSheetDialogOneTitleMessageTwoButtons bottomSheetDialogOneTitleMessageTwoButtons = new BottomSheetDialogOneTitleMessageTwoButtons(requireContext, string, string2);
                bottomSheetDialogOneTitleMessageTwoButtons.setCancelable(false);
                String string3 = FragmentQrTraySavingNEditing.this.getString(R.string.yes);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.yes)");
                final FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing2 = FragmentQrTraySavingNEditing.this;
                bottomSheetDialogOneTitleMessageTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.y
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentQrTraySavingNEditing.o.invokeSuspend$lambda$0(FragmentQrTraySavingNEditing.this, bottomSheetDialogOneTitleMessageTwoButtons, view);
                    }
                });
                String string4 = FragmentQrTraySavingNEditing.this.getString(R.string.cancel);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(\n             …cel\n                    )");
                bottomSheetDialogOneTitleMessageTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.z
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BottomSheetDialogOneTitleMessageTwoButtons.this.dismiss();
                    }
                });
                if (!bottomSheetDialogOneTitleMessageTwoButtons.isShowing() && BleApiManager.getInstance(FragmentQrTraySavingNEditing.this.requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    bottomSheetDialogOneTitleMessageTwoButtons.show();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$setMaxedOutDialogInWatch$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class p extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$setMaxedOutDialogInWatch$1$1$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ FragmentQrTraySavingNEditing this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentQrTraySavingNEditing;
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
                    QRTrayViewModel qRTrayViewModel = this.this$0.t;
                    if (qRTrayViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                        qRTrayViewModel = null;
                    }
                    FragmentActivity requireActivity = this.this$0.requireActivity();
                    Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                    QRCodeDataApp qRCodeDataApp = this.this$0.A;
                    Intrinsics.checkNotNull(qRCodeDataApp);
                    qRTrayViewModel.deleteQRCodesMetaDataToWatch(((ActivityQRTray) requireActivity).getBleQRCodeListFromWatchCachedDataExcluding(qRCodeDataApp));
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$setMaxedOutDialogInWatch$1$2$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ FragmentQrTraySavingNEditing this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = fragmentQrTraySavingNEditing;
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
                Bitmap bitmapFromUrl;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    QRTrayViewModel qRTrayViewModel = this.this$0.t;
                    if (qRTrayViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                        qRTrayViewModel = null;
                    }
                    if (this.this$0.s != null) {
                        bitmapFromUrl = this.this$0.s;
                        Intrinsics.checkNotNull(bitmapFromUrl);
                    } else {
                        QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
                        QRTrayCodesRes.QRTrayCodeData qRTrayCodeData = this.this$0.p;
                        Intrinsics.checkNotNull(qRTrayCodeData);
                        String imageUrl = qRTrayCodeData.getImageUrl();
                        Intrinsics.checkNotNullExpressionValue(imageUrl, "qrCodeEditData!!.imageUrl");
                        bitmapFromUrl = qRCodeUtils.getBitmapFromUrl(imageUrl);
                    }
                    qRTrayViewModel.uploadQRCodePicAndSaveToServer(bitmapFromUrl, this.this$0.x);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public p(Continuation<? super p> continuation) {
            super(2, continuation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing, View view) {
            fragmentQrTraySavingNEditing.B = true;
            fragmentQrTraySavingNEditing.D = true;
            fragmentQrTraySavingNEditing.I(true);
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(fragmentQrTraySavingNEditing), Dispatchers.getIO(), null, new a(fragmentQrTraySavingNEditing, null), 2, null);
            BottomSheetDialogQRTray bottomSheetDialogQRTray = fragmentQrTraySavingNEditing.y;
            Intrinsics.checkNotNull(bottomSheetDialogQRTray);
            bottomSheetDialogQRTray.dismiss();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$1(FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing, View view) {
            HashMap<String, Object> hashMap = new HashMap<>();
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            Context requireContext = fragmentQrTraySavingNEditing.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            hashMap.putAll(companion.getDeviceId(requireContext));
            Context requireContext2 = fragmentQrTraySavingNEditing.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            hashMap.putAll(companion.getWatchDetails(requireContext2));
            CleverTapConstants.CustomEventProperties customEventProperties = CleverTapConstants.CustomEventProperties.QR_NAME;
            hashMap.put(customEventProperties.getValue(), StringsKt__StringsKt.trim(fragmentQrTraySavingNEditing.s().etQRCodeName.getText().toString()).toString());
            CleverTapConstants.CustomEventProperties customEventProperties2 = CleverTapConstants.CustomEventProperties.QR_TAG;
            hashMap.put(customEventProperties2.getValue(), fragmentQrTraySavingNEditing.v);
            if (fragmentQrTraySavingNEditing.n) {
                hashMap.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.EDITED_EXISTING.getValue());
            } else {
                hashMap.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.NEW.getValue());
            }
            hashMap.put(CleverTapConstants.CustomEventProperties.LOCATION.getValue(), CleverTapConstants.CustomEventValues.DETAILS_PAGE.getValue());
            CleverTapConstants.CustomEventProperties customEventProperties3 = CleverTapConstants.CustomEventProperties.FAILURE_REASON;
            hashMap.put(customEventProperties3.getValue(), CleverTapConstants.CustomEventValues.QR_LIMIT_EXCEEDED.getValue());
            companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_PUSHTOWATCH_FAILED.getValue(), hashMap);
            if (!AppUtils.isNetConnected(fragmentQrTraySavingNEditing.requireContext())) {
                BottomSheetDialogQRTray bottomSheetDialogQRTray = fragmentQrTraySavingNEditing.y;
                Intrinsics.checkNotNull(bottomSheetDialogQRTray);
                bottomSheetDialogQRTray.dismiss();
                fragmentQrTraySavingNEditing.I(false);
                if (!fragmentQrTraySavingNEditing.G) {
                    HashMap<String, Object> hashMap2 = new HashMap<>();
                    Context requireContext3 = fragmentQrTraySavingNEditing.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                    hashMap2.putAll(companion.getDeviceId(requireContext3));
                    Context requireContext4 = fragmentQrTraySavingNEditing.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                    hashMap2.putAll(companion.getWatchDetails(requireContext4));
                    hashMap2.put(customEventProperties.getValue(), StringsKt__StringsKt.trim(fragmentQrTraySavingNEditing.s().etQRCodeName.getText().toString()).toString());
                    hashMap2.put(customEventProperties2.getValue(), fragmentQrTraySavingNEditing.v);
                    if (fragmentQrTraySavingNEditing.n) {
                        hashMap2.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.EDITED_EXISTING.getValue());
                    } else {
                        hashMap2.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.NEW.getValue());
                    }
                    hashMap2.put(customEventProperties3.getValue(), CleverTapConstants.CustomEventValues.INTERNET_DISCONNECTED.getValue());
                    companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_SAVE_FAILED.getValue(), hashMap2);
                    fragmentQrTraySavingNEditing.G = true;
                }
                String string = fragmentQrTraySavingNEditing.getString(R.string.no_internet_available);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.no_internet_available)");
                String string2 = fragmentQrTraySavingNEditing.getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.some_thing_went_wrong)");
                Drawable drawable = AppCompatResources.getDrawable(fragmentQrTraySavingNEditing.requireContext(), R.drawable.ic_red_circular_no_internet);
                Intrinsics.checkNotNull(drawable);
                fragmentQrTraySavingNEditing.G(false, string, string2, drawable, true);
                return;
            }
            fragmentQrTraySavingNEditing.setSaveButtonClickInitiatedToLog(true);
            fragmentQrTraySavingNEditing.showProgress(false);
            if (fragmentQrTraySavingNEditing.z.size() >= 6) {
                BottomSheetDialogQRTray bottomSheetDialogQRTray2 = fragmentQrTraySavingNEditing.y;
                Intrinsics.checkNotNull(bottomSheetDialogQRTray2);
                bottomSheetDialogQRTray2.dismiss();
                fragmentQrTraySavingNEditing.I(false);
                String obj = StringsKt__StringsKt.trim(fragmentQrTraySavingNEditing.s().etQRCodeName.getText().toString()).toString();
                String str = fragmentQrTraySavingNEditing.v;
                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData = fragmentQrTraySavingNEditing.p;
                String imageUrl = qRTrayCodeData != null ? qRTrayCodeData.getImageUrl() : null;
                Boolean bool = Boolean.FALSE;
                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData2 = fragmentQrTraySavingNEditing.p;
                Integer imageRefId = qRTrayCodeData2 != null ? qRTrayCodeData2.getImageRefId() : null;
                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData3 = fragmentQrTraySavingNEditing.p;
                fragmentQrTraySavingNEditing.x = new QRTraySaveReq(obj, str, imageUrl, bool, imageRefId, qRTrayCodeData3 != null ? qRTrayCodeData3.getId() : null);
                QRTrayViewModel qRTrayViewModel = fragmentQrTraySavingNEditing.t;
                if (qRTrayViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                    qRTrayViewModel = null;
                }
                QRTrayViewModel.editQRCodeToServer$default(qRTrayViewModel, fragmentQrTraySavingNEditing.x, null, 2, null);
                return;
            }
            fragmentQrTraySavingNEditing.x.setCategoryId(fragmentQrTraySavingNEditing.v);
            fragmentQrTraySavingNEditing.x.setName(StringsKt__StringsKt.trim(fragmentQrTraySavingNEditing.s().etQRCodeName.getText().toString()).toString());
            fragmentQrTraySavingNEditing.x.setImageRefId(0);
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(fragmentQrTraySavingNEditing), Dispatchers.getIO(), null, new b(fragmentQrTraySavingNEditing, null), 2, null);
            BottomSheetDialogQRTray bottomSheetDialogQRTray3 = fragmentQrTraySavingNEditing.y;
            Intrinsics.checkNotNull(bottomSheetDialogQRTray3);
            bottomSheetDialogQRTray3.dismiss();
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new p(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((p) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (FragmentQrTraySavingNEditing.this.y == null) {
                    FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = FragmentQrTraySavingNEditing.this;
                    Context requireContext = fragmentQrTraySavingNEditing.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    fragmentQrTraySavingNEditing.y = new BottomSheetDialogQRTray(requireContext);
                }
                BottomSheetDialogQRTray bottomSheetDialogQRTray = FragmentQrTraySavingNEditing.this.y;
                Intrinsics.checkNotNull(bottomSheetDialogQRTray);
                QRCodeDataApp qRCodeDataApp = FragmentQrTraySavingNEditing.this.A;
                Intrinsics.checkNotNull(qRCodeDataApp);
                String imageTitle = qRCodeDataApp.getImageTitle();
                Intrinsics.checkNotNull(imageTitle);
                QRCodeDataApp qRCodeDataApp2 = FragmentQrTraySavingNEditing.this.A;
                Intrinsics.checkNotNull(qRCodeDataApp2);
                String imageTag = qRCodeDataApp2.getImageTag();
                Intrinsics.checkNotNull(imageTag);
                QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
                QRCodeDataApp qRCodeDataApp3 = FragmentQrTraySavingNEditing.this.A;
                Intrinsics.checkNotNull(qRCodeDataApp3);
                String imageTag2 = qRCodeDataApp3.getImageTag();
                ArrayList arrayList = FragmentQrTraySavingNEditing.this.w;
                Intrinsics.checkNotNull(arrayList);
                String categoryIconURL = qRCodeUtils.getCategoryIconURL(imageTag2, arrayList);
                Intrinsics.checkNotNull(categoryIconURL);
                bottomSheetDialogQRTray.setQRCodeData(false, imageTitle, imageTag, categoryIconURL);
                BottomSheetDialogQRTray bottomSheetDialogQRTray2 = FragmentQrTraySavingNEditing.this.y;
                Intrinsics.checkNotNull(bottomSheetDialogQRTray2);
                bottomSheetDialogQRTray2.setCancelable(false);
                BottomSheetDialogQRTray bottomSheetDialogQRTray3 = FragmentQrTraySavingNEditing.this.y;
                Intrinsics.checkNotNull(bottomSheetDialogQRTray3);
                final FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing2 = FragmentQrTraySavingNEditing.this;
                bottomSheetDialogQRTray3.setPositiveButton(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.a0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentQrTraySavingNEditing.p.invokeSuspend$lambda$0(FragmentQrTraySavingNEditing.this, view);
                    }
                });
                BottomSheetDialogQRTray bottomSheetDialogQRTray4 = FragmentQrTraySavingNEditing.this.y;
                Intrinsics.checkNotNull(bottomSheetDialogQRTray4);
                final FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing3 = FragmentQrTraySavingNEditing.this;
                bottomSheetDialogQRTray4.setNegativeButton(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.b0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentQrTraySavingNEditing.p.invokeSuspend$lambda$1(FragmentQrTraySavingNEditing.this, view);
                    }
                });
                BottomSheetDialogQRTray bottomSheetDialogQRTray5 = FragmentQrTraySavingNEditing.this.y;
                Intrinsics.checkNotNull(bottomSheetDialogQRTray5);
                if (!bottomSheetDialogQRTray5.isShowing() && BleApiManager.getInstance(FragmentQrTraySavingNEditing.this.requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    BottomSheetDialogQRTray bottomSheetDialogQRTray6 = FragmentQrTraySavingNEditing.this.y;
                    Intrinsics.checkNotNull(bottomSheetDialogQRTray6);
                    bottomSheetDialogQRTray6.show();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes5.dex */
    public static final class q extends Lambda implements Function1<ArrayList<QRTrayCategoriesRes.QRItem>, Unit> {

        @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$setObservers$1$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ ArrayList<QRTrayCategoriesRes.QRItem> $it;
            public int label;
            public final /* synthetic */ FragmentQrTraySavingNEditing this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing, ArrayList<QRTrayCategoriesRes.QRItem> arrayList, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentQrTraySavingNEditing;
                this.$it = arrayList;
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
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.w.addAll(this.$it);
                    QRTrayCategoryAdapter qRTrayCategoryAdapter = this.this$0.u;
                    if (qRTrayCategoryAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("qrTrayCategoryAdapter");
                        qRTrayCategoryAdapter = null;
                    }
                    ArrayList<QRTrayCategoriesRes.QRItem> it = this.$it;
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    qRTrayCategoryAdapter.setCategoryList(it);
                    this.this$0.q.setImageTag(this.this$0.v);
                    FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = this.this$0;
                    fragmentQrTraySavingNEditing.C(fragmentQrTraySavingNEditing.s());
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public q() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ArrayList<QRTrayCategoriesRes.QRItem> arrayList) {
            invoke2(arrayList);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(ArrayList<QRTrayCategoriesRes.QRItem> arrayList) {
            FragmentQrTraySavingNEditing.this.dismissProgress();
            if (arrayList == null || !FragmentQrTraySavingNEditing.this.isAdded()) {
                return;
            }
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentQrTraySavingNEditing.this), Dispatchers.getMain(), null, new a(FragmentQrTraySavingNEditing.this, arrayList, null), 2, null);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$showCommonDialog$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class r extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $desc;
        public final /* synthetic */ Drawable $drawable;
        public final /* synthetic */ boolean $isFinish;
        public final /* synthetic */ boolean $isPushSuccess;
        public final /* synthetic */ String $title;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public r(Drawable drawable, String str, String str2, boolean z, boolean z2, Continuation<? super r> continuation) {
            super(2, continuation);
            this.$drawable = drawable;
            this.$title = str;
            this.$desc = str2;
            this.$isFinish = z;
            this.$isPushSuccess = z2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(boolean z, FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing, boolean z2, View view) {
            if (z) {
                fragmentQrTraySavingNEditing.requireActivity().finish();
            } else if (z2) {
                fragmentQrTraySavingNEditing.v();
            }
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = fragmentQrTraySavingNEditing.r;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
            bottomSheetDialogImageTitleMessage.dismiss();
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new r(this.$drawable, this.$title, this.$desc, this.$isFinish, this.$isPushSuccess, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((r) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (FragmentQrTraySavingNEditing.this.r != null) {
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = FragmentQrTraySavingNEditing.this.r;
                    boolean z = true;
                    if (bottomSheetDialogImageTitleMessage2 == null || !bottomSheetDialogImageTitleMessage2.isShowing()) {
                        z = false;
                    }
                    if (z && (bottomSheetDialogImageTitleMessage = FragmentQrTraySavingNEditing.this.r) != null) {
                        bottomSheetDialogImageTitleMessage.dismiss();
                    }
                    FragmentQrTraySavingNEditing.this.r = null;
                }
                if (FragmentQrTraySavingNEditing.this.r == null) {
                    FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = FragmentQrTraySavingNEditing.this;
                    Context requireContext = fragmentQrTraySavingNEditing.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    fragmentQrTraySavingNEditing.r = new BottomSheetDialogImageTitleMessage(requireContext, this.$drawable, this.$title, this.$desc, false);
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = FragmentQrTraySavingNEditing.this.r;
                    if (bottomSheetDialogImageTitleMessage3 != null) {
                        bottomSheetDialogImageTitleMessage3.setCancelable(false);
                    }
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = FragmentQrTraySavingNEditing.this.r;
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage4);
                    bottomSheetDialogImageTitleMessage4.showBigIcon();
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage5 = FragmentQrTraySavingNEditing.this.r;
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage5);
                    String string = FragmentQrTraySavingNEditing.this.getString(R.string.okay);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.okay)");
                    final boolean z2 = this.$isFinish;
                    final FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing2 = FragmentQrTraySavingNEditing.this;
                    final boolean z3 = this.$isPushSuccess;
                    bottomSheetDialogImageTitleMessage5.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.c0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            FragmentQrTraySavingNEditing.r.invokeSuspend$lambda$0(z2, fragmentQrTraySavingNEditing2, z3, view);
                        }
                    });
                }
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage6 = FragmentQrTraySavingNEditing.this.r;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage6);
                if (!bottomSheetDialogImageTitleMessage6.isShowing()) {
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage7 = FragmentQrTraySavingNEditing.this.r;
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage7);
                    bottomSheetDialogImageTitleMessage7.show();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$showOrStopSavingProgressAnimation$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class s extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isStart;
        public int label;
        public final /* synthetic */ FragmentQrTraySavingNEditing this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public s(boolean z, FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing, Continuation<? super s> continuation) {
            super(2, continuation);
            this.$isStart = z;
            this.this$0 = fragmentQrTraySavingNEditing;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new s(this.$isStart, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((s) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$isStart) {
                    FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = this.this$0;
                    String string = fragmentQrTraySavingNEditing.getString(R.string.saving_and_pushing_to_watch);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.saving_and_pushing_to_watch)");
                    fragmentQrTraySavingNEditing.showQRCodeProgress(false, string);
                } else {
                    this.this$0.dismissQRCodeProgress();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$showSuccessNErrorToast$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class t extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $message;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public t(String str, Continuation<? super t> continuation) {
            super(2, continuation);
            this.$message = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new t(this.$message, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((t) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = FragmentQrTraySavingNEditing.this;
                Context requireContext = fragmentQrTraySavingNEditing.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                fragmentQrTraySavingNEditing.toast(requireContext, this.$message);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$uploadingImageCheck$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class u extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public u(Continuation<? super u> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new u(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((u) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            BleApi bleApi;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentQrTraySavingNEditing.this.I(false);
                BleApiManager bleApiManager = BleApiManager.getInstance(FragmentQrTraySavingNEditing.this.requireContext());
                if (((bleApiManager == null || (bleApi = bleApiManager.getBleApi()) == null) ? null : bleApi.getConnectionStatus()) != ConnectionStatus.CONNECTED) {
                    FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = FragmentQrTraySavingNEditing.this;
                    String string = fragmentQrTraySavingNEditing.getString(R.string.connect_watch);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.connect_watch)");
                    String string2 = FragmentQrTraySavingNEditing.this.getString(R.string.please_connect_your_watch_in_order_to_apply_the_qr_code);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.pleas…der_to_apply_the_qr_code)");
                    Drawable drawable = AppCompatResources.getDrawable(FragmentQrTraySavingNEditing.this.requireContext(), R.drawable.ic_red_circular_not_connected);
                    Intrinsics.checkNotNull(drawable);
                    fragmentQrTraySavingNEditing.G(false, string, string2, drawable, true);
                } else {
                    FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing2 = FragmentQrTraySavingNEditing.this;
                    String string3 = fragmentQrTraySavingNEditing2.getString(R.string.error);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.error)");
                    String string4 = FragmentQrTraySavingNEditing.this.getString(R.string.qr_code_upload_could_not_be_completed);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qr_co…d_could_not_be_completed)");
                    Drawable drawable2 = AppCompatResources.getDrawable(FragmentQrTraySavingNEditing.this.requireContext(), R.drawable.ic_red_circular_cross);
                    Intrinsics.checkNotNull(drawable2);
                    fragmentQrTraySavingNEditing2.G(false, string3, string4, drawable2, true);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$uploadingQRCodeMetaDataCheck$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class v extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isSuccess;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing$uploadingQRCodeMetaDataCheck$1$1", f = "FragmentQrTraySavingNEditing.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ FragmentQrTraySavingNEditing this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentQrTraySavingNEditing;
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
                Bitmap bitmapFromUrl;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.this$0.s != null) {
                        bitmapFromUrl = this.this$0.s;
                    } else {
                        QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
                        QRTrayCodesRes.QRTrayCodeData qRTrayCodeData = this.this$0.p;
                        Intrinsics.checkNotNull(qRTrayCodeData);
                        String imageUrl = qRTrayCodeData.getImageUrl();
                        Intrinsics.checkNotNullExpressionValue(imageUrl, "qrCodeEditData!!.imageUrl");
                        bitmapFromUrl = qRCodeUtils.getBitmapFromUrl(imageUrl);
                    }
                    if (bitmapFromUrl != null) {
                        FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing = this.this$0;
                        QRTrayViewModel qRTrayViewModel = null;
                        if (fragmentQrTraySavingNEditing.n) {
                            QRTrayViewModel qRTrayViewModel2 = fragmentQrTraySavingNEditing.t;
                            if (qRTrayViewModel2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                            } else {
                                qRTrayViewModel = qRTrayViewModel2;
                            }
                            qRTrayViewModel.editQRCodeToServer(fragmentQrTraySavingNEditing.x, fragmentQrTraySavingNEditing.C);
                        } else {
                            QRTrayViewModel qRTrayViewModel3 = fragmentQrTraySavingNEditing.t;
                            if (qRTrayViewModel3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                            } else {
                                qRTrayViewModel = qRTrayViewModel3;
                            }
                            qRTrayViewModel.uploadQRCodePicAndSaveToServer(bitmapFromUrl, fragmentQrTraySavingNEditing.x);
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public v(boolean z, Continuation<? super v> continuation) {
            super(2, continuation);
            this.$isSuccess = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new v(this.$isSuccess, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((v) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0075  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0084  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x009b  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00b9  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r11) {
            /*
                Method dump skipped, instructions count: 325
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing.v.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final void F(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static /* synthetic */ void H(FragmentQrTraySavingNEditing fragmentQrTraySavingNEditing, boolean z, String str, String str2, Drawable drawable, boolean z2, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            z2 = false;
        }
        fragmentQrTraySavingNEditing.G(z, str, str2, drawable, z2);
    }

    @JvmStatic
    @NotNull
    public static final FragmentQrTraySavingNEditing newInstance(int i2, @Nullable String str, boolean z, @Nullable QRTrayCodesRes.QRTrayCodeData qRTrayCodeData, boolean z2) {
        return Companion.newInstance(i2, str, z, qRTrayCodeData, z2);
    }

    public static final void w(FragmentQrTraySavingNEditing this$0, View view) {
        BleApi bleApi;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.H = true;
        if (AppUtils.isNetConnected(this$0.requireContext())) {
            this$0.hideKeyBoard();
            Editable text = this$0.s().etQRCodeName.getText();
            String obj = text != null ? text.toString() : null;
            if (!(obj == null || obj.length() == 0)) {
                if (!this$0.q()) {
                    if (this$0.v.length() > 0) {
                        BleApiManager bleApiManager = BleApiManager.getInstance(this$0.requireContext());
                        if (((bleApiManager == null || (bleApi = bleApiManager.getBleApi()) == null) ? null : bleApi.getConnectionStatus()) == ConnectionStatus.CONNECTED) {
                            if (this$0.n) {
                                HashMap<String, Object> hashMap = new HashMap<>();
                                DeviceUtils.Companion companion = DeviceUtils.Companion;
                                Context requireContext = this$0.requireContext();
                                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                                hashMap.putAll(companion.getDeviceId(requireContext));
                                Context requireContext2 = this$0.requireContext();
                                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                                hashMap.putAll(companion.getWatchDetails(requireContext2));
                                hashMap.put(CleverTapConstants.CustomEventProperties.QR_NAME.getValue(), StringsKt__StringsKt.trim(this$0.s().etQRCodeName.getText().toString()).toString());
                                hashMap.put(CleverTapConstants.CustomEventProperties.QR_TAG.getValue(), this$0.v);
                                hashMap.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.EDITED_EXISTING.getValue());
                                hashMap.put(CleverTapConstants.CustomEventProperties.LOCATION.getValue(), CleverTapConstants.CustomEventValues.DETAILS_PAGE.getValue());
                                companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_PUSHTOWATCH_REQUEST.getValue(), hashMap);
                                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData = this$0.p;
                                Intrinsics.checkNotNull(qRTrayCodeData);
                                if (qRTrayCodeData.getImageRefId() != null) {
                                    QRTrayCodesRes.QRTrayCodeData qRTrayCodeData2 = this$0.p;
                                    Intrinsics.checkNotNull(qRTrayCodeData2);
                                    Integer imageRefId = qRTrayCodeData2.getImageRefId();
                                    if (imageRefId == null || imageRefId.intValue() != 0) {
                                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getMain(), null, new j(null), 2, null);
                                        return;
                                    }
                                }
                                this$0.q.setImageTitle(StringsKt__StringsKt.trim(this$0.s().etQRCodeName.getText().toString()).toString());
                                this$0.q.setImageTag(this$0.v);
                                FragmentActivity requireActivity = this$0.requireActivity();
                                Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                                int size = ((ActivityQRTray) requireActivity).getWatchAndServerQRData().getWatchQrCodes().size();
                                QRTrayViewModel qRTrayViewModel = this$0.t;
                                if (qRTrayViewModel == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                                    qRTrayViewModel = null;
                                }
                                Integer maxAllowed = qRTrayViewModel.getQrCodeDetailsOfWatchFromFirebaseRemoteConfig().getMaxAllowed();
                                Intrinsics.checkNotNullExpressionValue(maxAllowed, "qrTrayViewModel.qrCodeDe…seRemoteConfig.maxAllowed");
                                if (size < maxAllowed.intValue()) {
                                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getIO(), null, new k(null), 2, null);
                                    return;
                                }
                                FragmentActivity requireActivity2 = this$0.requireActivity();
                                Intrinsics.checkNotNull(requireActivity2, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                                if (true ^ ((ActivityQRTray) requireActivity2).getWatchAndServerQRData().getServerAppliedQrCodes().isEmpty()) {
                                    QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
                                    FragmentActivity requireActivity3 = this$0.requireActivity();
                                    Intrinsics.checkNotNull(requireActivity3, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                                    this$0.A = qRCodeUtils.getFirstUploadedQRData(((ActivityQRTray) requireActivity3).getWatchAndServerQRData().getServerAppliedQrCodes());
                                    this$0.D();
                                    return;
                                }
                                this$0.I(false);
                                String string = this$0.getString(R.string.un_applied_check_info);
                                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.un_applied_check_info)");
                                Drawable drawable = AppCompatResources.getDrawable(this$0.requireContext(), R.drawable.ic_red_circular_cross);
                                Intrinsics.checkNotNull(drawable);
                                H(this$0, true, string, "", drawable, false, 16, null);
                                return;
                            }
                            HashMap<String, Object> hashMap2 = new HashMap<>();
                            DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                            Context requireContext3 = this$0.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                            hashMap2.putAll(companion2.getDeviceId(requireContext3));
                            Context requireContext4 = this$0.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                            hashMap2.putAll(companion2.getWatchDetails(requireContext4));
                            hashMap2.put(CleverTapConstants.CustomEventProperties.QR_NAME.getValue(), StringsKt__StringsKt.trim(this$0.s().etQRCodeName.getText().toString()).toString());
                            hashMap2.put(CleverTapConstants.CustomEventProperties.QR_TAG.getValue(), this$0.v);
                            hashMap2.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.NEW.getValue());
                            hashMap2.put(CleverTapConstants.CustomEventProperties.LOCATION.getValue(), CleverTapConstants.CustomEventValues.DETAILS_PAGE.getValue());
                            companion2.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_PUSHTOWATCH_REQUEST.getValue(), hashMap2);
                            this$0.q.setImageTitle(StringsKt__StringsKt.trim(this$0.s().etQRCodeName.getText().toString()).toString());
                            this$0.q.setImageTag(this$0.v);
                            FragmentActivity requireActivity4 = this$0.requireActivity();
                            Intrinsics.checkNotNull(requireActivity4, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                            int size2 = ((ActivityQRTray) requireActivity4).getWatchAndServerQRData().getWatchQrCodes().size();
                            QRTrayViewModel qRTrayViewModel2 = this$0.t;
                            if (qRTrayViewModel2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                                qRTrayViewModel2 = null;
                            }
                            Integer maxAllowed2 = qRTrayViewModel2.getQrCodeDetailsOfWatchFromFirebaseRemoteConfig().getMaxAllowed();
                            Intrinsics.checkNotNullExpressionValue(maxAllowed2, "qrTrayViewModel.qrCodeDe…seRemoteConfig.maxAllowed");
                            if (size2 >= maxAllowed2.intValue()) {
                                FragmentActivity requireActivity5 = this$0.requireActivity();
                                Intrinsics.checkNotNull(requireActivity5, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                                if (true ^ ((ActivityQRTray) requireActivity5).getWatchAndServerQRData().getServerAppliedQrCodes().isEmpty()) {
                                    QRCodeUtils qRCodeUtils2 = QRCodeUtils.INSTANCE;
                                    FragmentActivity requireActivity6 = this$0.requireActivity();
                                    Intrinsics.checkNotNull(requireActivity6, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                                    this$0.A = qRCodeUtils2.getFirstUploadedQRData(((ActivityQRTray) requireActivity6).getWatchAndServerQRData().getServerAppliedQrCodes());
                                    this$0.D();
                                    return;
                                }
                                this$0.I(false);
                                String string2 = this$0.getString(R.string.un_applied_check_info);
                                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.un_applied_check_info)");
                                Drawable drawable2 = AppCompatResources.getDrawable(this$0.requireContext(), R.drawable.ic_red_circular_cross);
                                Intrinsics.checkNotNull(drawable2);
                                H(this$0, true, string2, "", drawable2, false, 16, null);
                                return;
                            }
                            this$0.I(true);
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getIO(), null, new l(null), 2, null);
                            return;
                        }
                        String string3 = this$0.getString(R.string.connect_watch);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.connect_watch)");
                        String string4 = this$0.getString(R.string.please_connect_your_watch_in_order_to_apply_the_qr_code);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.pleas…der_to_apply_the_qr_code)");
                        Drawable drawable3 = AppCompatResources.getDrawable(this$0.requireContext(), R.drawable.ic_red_circular_not_connected);
                        Intrinsics.checkNotNull(drawable3);
                        H(this$0, false, string3, string4, drawable3, false, 16, null);
                        this$0.u();
                        return;
                    }
                    String string5 = this$0.getString(R.string.please_select_tag);
                    Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.please_select_tag)");
                    this$0.showSuccessNErrorToast(string5);
                    return;
                }
                String string6 = this$0.getString(R.string.name_exist);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.name_exist)");
                this$0.showSuccessNErrorToast(string6);
                return;
            }
            String string7 = this$0.getString(R.string.please_enter_name);
            Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.please_enter_name)");
            this$0.showSuccessNErrorToast(string7);
            return;
        }
        String string8 = this$0.getString(R.string.no_internet_available);
        Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.no_internet_available)");
        String string9 = this$0.getString(R.string.some_thing_went_wrong);
        Intrinsics.checkNotNullExpressionValue(string9, "getString(R.string.some_thing_went_wrong)");
        Drawable drawable4 = AppCompatResources.getDrawable(this$0.requireContext(), R.drawable.ic_red_circular_no_internet);
        Intrinsics.checkNotNull(drawable4);
        H(this$0, false, string8, string9, drawable4, false, 16, null);
        if (this$0.F) {
            return;
        }
        this$0.u();
        this$0.F = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0092 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void x(com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing r16, android.view.View r17) {
        /*
            Method dump skipped, instructions count: 283
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing.x(com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing, android.view.View):void");
    }

    public static final void y(FragmentQrTraySavingNEditing this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion.getDeviceId(requireContext));
        Context requireContext2 = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        hashMap.putAll(companion.getWatchDetails(requireContext2));
        hashMap.put(CleverTapConstants.CustomEventProperties.QR_NAME.getValue(), StringsKt__StringsKt.trim(this$0.s().etQRCodeName.getText().toString()).toString());
        hashMap.put(CleverTapConstants.CustomEventProperties.QR_TAG.getValue(), this$0.v);
        hashMap.put(CleverTapConstants.CustomEventProperties.LOCATION.getValue(), CleverTapConstants.CustomEventValues.DETAILS_PAGE.getValue());
        companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_DELETE_REQUEST.getValue(), hashMap);
        this$0.B();
    }

    public static final void z(FragmentQrTraySavingNEditing this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getMain(), null, new n(null), 2, null);
        }
    }

    public final void A(QRCodeData qRCodeData, Bitmap bitmap) {
        QRTrayViewModel qRTrayViewModel = this.t;
        if (qRTrayViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
            qRTrayViewModel = null;
        }
        qRTrayViewModel.uploadQRCodeImageToWatch(qRCodeData, bitmap);
    }

    public final void B() {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new o(null), 2, null);
        }
    }

    public final void C(FragmentQrTraySavingBinding fragmentQrTraySavingBinding) {
        QRTrayCodesRes.QRTrayCodeData qRTrayCodeData = this.p;
        if (qRTrayCodeData != null) {
            EditText editText = fragmentQrTraySavingBinding.etQRCodeName;
            Intrinsics.checkNotNull(qRTrayCodeData);
            editText.setText(qRTrayCodeData.getName());
            QRCodeData qRCodeData = this.q;
            QRTrayCodesRes.QRTrayCodeData qRTrayCodeData2 = this.p;
            Intrinsics.checkNotNull(qRTrayCodeData2);
            qRCodeData.setImageTitle(qRTrayCodeData2.getName());
            QRTrayCodesRes.QRTrayCodeData qRTrayCodeData3 = this.p;
            Intrinsics.checkNotNull(qRTrayCodeData3);
            String categoryId = qRTrayCodeData3.getCategoryId();
            Intrinsics.checkNotNullExpressionValue(categoryId, "qrCodeEditData!!.categoryId");
            this.v = categoryId;
            this.q.setImageTag(categoryId);
            QRCodeData qRCodeData2 = this.q;
            QRTrayCodesRes.QRTrayCodeData qRTrayCodeData4 = this.p;
            Intrinsics.checkNotNull(qRTrayCodeData4);
            Integer imageRefId = qRTrayCodeData4.getImageRefId();
            Intrinsics.checkNotNullExpressionValue(imageRefId, "qrCodeEditData!!.imageRefId");
            qRCodeData2.setImageId(imageRefId.intValue());
            QRTrayCodesRes.QRTrayCodeData qRTrayCodeData5 = this.p;
            Intrinsics.checkNotNull(qRTrayCodeData5);
            Boolean applied = qRTrayCodeData5.getApplied();
            Intrinsics.checkNotNullExpressionValue(applied, "qrCodeEditData!!.applied");
            applied.booleanValue();
            int i2 = 0;
            int size = this.w.size();
            while (true) {
                if (i2 >= size) {
                    break;
                }
                QRTrayCodesRes.QRTrayCodeData qRTrayCodeData6 = this.p;
                Intrinsics.checkNotNull(qRTrayCodeData6);
                if (Intrinsics.areEqual(qRTrayCodeData6.getCategoryId(), this.w.get(i2).getCategoryId())) {
                    QRTrayCategoryAdapter qRTrayCategoryAdapter = this.u;
                    if (qRTrayCategoryAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("qrTrayCategoryAdapter");
                        qRTrayCategoryAdapter = null;
                    }
                    qRTrayCategoryAdapter.setSelectedCategory(i2);
                } else {
                    i2++;
                }
            }
            QRTrayCodesRes.QRTrayCodeData qRTrayCodeData7 = this.p;
            Intrinsics.checkNotNull(qRTrayCodeData7);
            fragmentQrTraySavingBinding.setQrEditImage(qRTrayCodeData7.getImageUrl());
            Glide.with(requireContext()).m30load(fragmentQrTraySavingBinding.getQrEditImage()).centerCrop().error(R.drawable.rounded_dark_grey_with_border_background_40dp).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.rounded_black_grey_border_background_24dp).into(fragmentQrTraySavingBinding.ivQRCode);
        }
    }

    public final void D() {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new p(null), 2, null);
        }
    }

    public final void E() {
        QRTrayViewModel qRTrayViewModel = this.t;
        if (qRTrayViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
            qRTrayViewModel = null;
        }
        SingleLiveEvent<ArrayList<QRTrayCategoriesRes.QRItem>> categoryMutableList = qRTrayViewModel.getCategoryMutableList();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
        final q qVar = new q();
        categoryMutableList.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.qrtray.fragment.u
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentQrTraySavingNEditing.F(Function1.this, obj);
            }
        });
    }

    public final void G(boolean z, String str, String str2, Drawable drawable, boolean z2) {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new r(drawable, str, str2, z2, z, null), 2, null);
    }

    public final void I(boolean z) {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new s(z, this, null), 2, null);
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i2) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i2)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.coveiot.android.qrtray.adapter.QRTrayCategoryAdapter.CategoryClickListener
    public void categoryClick(@NotNull QRTrayCategoriesRes.QRItem categoryData, int i2) {
        Intrinsics.checkNotNullParameter(categoryData, "categoryData");
        QRTrayCategoryAdapter qRTrayCategoryAdapter = this.u;
        if (qRTrayCategoryAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qrTrayCategoryAdapter");
            qRTrayCategoryAdapter = null;
        }
        qRTrayCategoryAdapter.setSelectedCategory(i2);
        String categoryId = categoryData.getCategoryId();
        Intrinsics.checkNotNullExpressionValue(categoryId, "categoryData.categoryId");
        this.v = categoryId;
        this.q.setImageTag(categoryId);
        p();
    }

    @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
    public void deleteQRCodeMetaDataCheck(boolean z) {
        QRCodeDataApp qRCodeDataApp;
        if (isAdded()) {
            QRTrayViewModel qRTrayViewModel = null;
            if (!z) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
                return;
            }
            QRTrayViewModel qRTrayViewModel2 = this.t;
            if (qRTrayViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
            } else {
                qRTrayViewModel = qRTrayViewModel2;
            }
            if (this.E) {
                qRCodeDataApp = this.K;
            } else {
                qRCodeDataApp = this.A;
                Intrinsics.checkNotNull(qRCodeDataApp);
            }
            qRTrayViewModel.deleteQRCodeImageFromWatch(qRCodeDataApp.getImageId());
        }
    }

    public final boolean getFromMenu() {
        return this.J;
    }

    public final boolean getPushNSaveInitiatedToLog() {
        return this.H;
    }

    public final boolean getSaveButtonClickInitiatedToLog() {
        return this.I;
    }

    public final void logEvents(boolean z, @NotNull String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        if (this.G) {
            return;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion.getDeviceId(requireContext));
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        hashMap.putAll(companion.getWatchDetails(requireContext2));
        hashMap.put(CleverTapConstants.CustomEventProperties.QR_NAME.getValue(), StringsKt__StringsKt.trim(s().etQRCodeName.getText().toString()).toString());
        hashMap.put(CleverTapConstants.CustomEventProperties.QR_TAG.getValue(), this.v);
        if (z) {
            hashMap.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.EDITED_EXISTING.getValue());
        } else {
            hashMap.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.NEW.getValue());
        }
        hashMap.put(CleverTapConstants.CustomEventProperties.FAILURE_REASON.getValue(), reason);
        companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_SAVE_FAILED.getValue(), hashMap);
        this.G = true;
    }

    public final boolean onBackPressed() {
        if (t()) {
            r();
        }
        return t();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.getString("qrCodeMetaData") != null) {
                String string = arguments.getString("qrCodeMetaData", "");
                Intrinsics.checkNotNullExpressionValue(string, "it.getString(\"qrCodeMetaData\", \"\")");
                this.o = string;
            }
            this.q.setImageId(arguments.getInt("qrCodeImageID"));
            this.n = arguments.getBoolean("qrCodeEdit", false);
            if (arguments.getSerializable("qrCodeEditData") != null) {
                Serializable serializable = arguments.getSerializable("qrCodeEditData");
                Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.coveaccess.qrtray.model.QRTrayCodesRes.QRTrayCodeData");
                this.p = (QRTrayCodesRes.QRTrayCodeData) serializable;
            }
            this.J = arguments.getBoolean("fromMenu", false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentQrTraySavingBinding inflate = FragmentQrTraySavingBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.m = inflate;
        View root = s().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
    public void onDeleteImageCheck(boolean z) {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new d(z, this, null), 2, null);
        }
    }

    @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
    public void onDeleteQRCheckFromServer(boolean z) {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new e(z, null), 2, null);
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
    public void onEditCheck(boolean z, boolean z2) {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new f(z2, z, null), 2, null);
        }
    }

    @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
    public void onFailure(@Nullable String str) {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new g(str, null), 2, null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (BleApiManager.getInstance(requireContext()).getBleApi() == null || BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED || !isAdded()) {
            return;
        }
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new h(null), 2, null);
    }

    @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
    public void onSuccess(boolean z) {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new i(z, null), 2, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x018a  */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onViewCreated(@org.jetbrains.annotations.NotNull android.view.View r7, @org.jetbrains.annotations.Nullable android.os.Bundle r8) {
        /*
            Method dump skipped, instructions count: 470
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    public final void p() {
        Editable text = s().etQRCodeName.getText();
        Intrinsics.checkNotNullExpressionValue(text, "binding.etQRCodeName.text");
        if (text.length() > 0) {
            if (this.v.length() > 0) {
                s().btnSaveNPush.setEnabled(true);
                s().btnSaveNPush.setBackgroundResource(R.drawable.rounded_dialog_button_background);
                s().tvSave.setEnabled(true);
                s().tvSave.setAlpha(1.0f);
                return;
            }
            s().btnSaveNPush.setEnabled(false);
            s().btnSaveNPush.setBackgroundResource(R.drawable.rounded_light_grey_background_40dp);
            s().tvSave.setEnabled(false);
            s().tvSave.setAlpha(0.5f);
            return;
        }
        s().btnSaveNPush.setEnabled(false);
        s().btnSaveNPush.setBackgroundResource(R.drawable.rounded_light_grey_background_40dp);
        s().tvSave.setEnabled(false);
        s().tvSave.setAlpha(0.5f);
    }

    public final boolean q() {
        int size = this.z.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (Intrinsics.areEqual(this.z.get(i2).getImageTitle(), s().etQRCodeName.getText().toString())) {
                if (this.n) {
                    QRTrayCodesRes.QRTrayCodeData qRTrayCodeData = this.p;
                    if (!Intrinsics.areEqual(qRTrayCodeData != null ? qRTrayCodeData.getId() : null, this.z.get(i2).getServerId())) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final void r() {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new b(null), 2, null);
        }
    }

    @NotNull
    public final Bitmap rotateBitmap(@NotNull Bitmap bitmap, float f2) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Matrix matrix = new Matrix();
        matrix.postRotate(f2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(bitmap, 0, …map.height, matrix, true)");
        return createBitmap;
    }

    public final FragmentQrTraySavingBinding s() {
        FragmentQrTraySavingBinding fragmentQrTraySavingBinding = this.m;
        if (fragmentQrTraySavingBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentQrTraySavingBinding;
    }

    public final void setFromMenu(boolean z) {
        this.J = z;
    }

    public final void setPushNSaveInitiatedToLog(boolean z) {
        this.H = z;
    }

    public final void setSaveButtonClickInitiatedToLog(boolean z) {
        this.I = z;
    }

    public final void showSuccessNErrorToast(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new t(message, null), 2, null);
    }

    public final boolean t() {
        QRTrayCodesRes.QRTrayCodeData qRTrayCodeData = this.p;
        if (Intrinsics.areEqual(qRTrayCodeData != null ? qRTrayCodeData.getName() : null, StringsKt__StringsKt.trim(s().etQRCodeName.getText().toString()).toString())) {
            QRTrayCodesRes.QRTrayCodeData qRTrayCodeData2 = this.p;
            if (Intrinsics.areEqual(qRTrayCodeData2 != null ? qRTrayCodeData2.getCategoryId() : null, this.v)) {
                return false;
            }
        }
        return true;
    }

    public final void u() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new c(null), 2, null);
    }

    @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
    public void uploadingImageCheck(boolean z) {
        if (isAdded()) {
            QRTrayViewModel qRTrayViewModel = null;
            if (z) {
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                ((ActivityQRTray) requireActivity).addOrUpdateWatchAppliedQrCode(new QRCodeDataApp(this.q.getImageId(), this.q.getImageTitle(), this.q.getImageTag(), null, null, null, false, null, 240, null));
                QRTrayViewModel qRTrayViewModel2 = this.t;
                if (qRTrayViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                } else {
                    qRTrayViewModel = qRTrayViewModel2;
                }
                FragmentActivity requireActivity2 = requireActivity();
                Intrinsics.checkNotNull(requireActivity2, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                qRTrayViewModel.setQRCodesMetaDataToWatch(((ActivityQRTray) requireActivity2).getBleQRCodeListFromWatchCachedData());
                return;
            }
            u();
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new u(null), 2, null);
        }
    }

    @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
    public void uploadingQRCodeMetaDataCheck(boolean z) {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new v(z, null), 2, null);
        }
    }

    public final int v() {
        return requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.qrContainer, new FragmentQrTraySaved()).commit();
    }
}
