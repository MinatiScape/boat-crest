package com.coveiot.android.qrtray.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.qrtray.ActivityQRTray;
import com.coveiot.android.qrtray.R;
import com.coveiot.android.qrtray.adapter.AddedQRTrayAdapter;
import com.coveiot.android.qrtray.adapter.QRTrayCategoryAdapter;
import com.coveiot.android.qrtray.databinding.FragmentAddedQrTrayBinding;
import com.coveiot.android.qrtray.dialog.BottomSheetDialogQRTray;
import com.coveiot.android.qrtray.fragment.FragmentQrTraySaved;
import com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing;
import com.coveiot.android.qrtray.model.QRCodeDataApp;
import com.coveiot.android.qrtray.utils.QRCodeUtils;
import com.coveiot.android.qrtray.utils.SingleLiveEvent;
import com.coveiot.android.qrtray.viewmodel.QRTrayViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneTitleMessageTwoButtons;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.QRTrayMenuDialog;
import com.coveiot.coveaccess.qrtray.model.QRTrayCategoriesRes;
import com.coveiot.coveaccess.qrtray.model.QRTrayCodesRes;
import com.coveiot.coveaccess.qrtray.model.QRTraySaveReq;
import com.coveiot.sdk.ble.model.QRCodeData;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
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
public final class FragmentQrTraySaved extends BaseFragment implements AddedQRTrayAdapter.QrMenuClickListener, QRTrayCategoryAdapter.CategoryClickListener, QRTrayViewModel.QRTrayViewModelContract {
    public boolean C;
    public boolean D;
    @Nullable
    public QRCodeDataApp F;
    public boolean H;
    public boolean I;
    public boolean J;
    public FragmentAddedQrTrayBinding m;
    public AddedQRTrayAdapter n;
    @Nullable
    public QRTrayMenuDialog o;
    @Nullable
    public BottomSheetDialogOneTitleMessageTwoButtons p;
    @Nullable
    public BottomSheetDialogQRTray q;
    @Nullable
    public BottomSheetDialogImageTitleMessage r;
    public QRTrayCategoryAdapter s;
    public QRTrayViewModel t;
    @Nullable
    public QRCodeDataApp u;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ArrayList<QRCodeDataApp> v = new ArrayList<>();
    @NotNull
    public ArrayList<QRCodeDataApp> w = new ArrayList<>();
    @NotNull
    public String x = "";
    public int y = 59000;
    public int z = 6;
    @NotNull
    public QRCodeData A = new QRCodeData(59000, "", "");
    @NotNull
    public QRTraySaveReq B = new QRTraySaveReq("", "", "", Boolean.FALSE, Integer.valueOf(this.y), "");
    @NotNull
    public final ArrayList<QRTrayCategoriesRes.QRItem> E = new ArrayList<>();
    @NotNull
    public String G = "";

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$deleteQRCodeMetaDataCheck$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isSuccess;
        public int label;
        public final /* synthetic */ FragmentQrTraySaved this$0;

        @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$deleteQRCodeMetaDataCheck$1$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$a$a  reason: collision with other inner class name */
        /* loaded from: classes5.dex */
        public static final class C0299a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ FragmentQrTraySaved this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0299a(FragmentQrTraySaved fragmentQrTraySaved, Continuation<? super C0299a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentQrTraySaved;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0299a(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0299a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    QRTrayViewModel qRTrayViewModel = null;
                    if (this.this$0.C) {
                        QRTrayViewModel qRTrayViewModel2 = this.this$0.t;
                        if (qRTrayViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                        } else {
                            qRTrayViewModel = qRTrayViewModel2;
                        }
                        QRCodeDataApp qRCodeDataApp = this.this$0.F;
                        Intrinsics.checkNotNull(qRCodeDataApp);
                        qRTrayViewModel.deleteQRCodeImageFromWatch(qRCodeDataApp.getImageId());
                    } else {
                        QRTrayViewModel qRTrayViewModel3 = this.this$0.t;
                        if (qRTrayViewModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                        } else {
                            qRTrayViewModel = qRTrayViewModel3;
                        }
                        QRCodeDataApp qRCodeDataApp2 = this.this$0.u;
                        Intrinsics.checkNotNull(qRCodeDataApp2);
                        qRTrayViewModel.deleteQRCodeImageFromWatch(qRCodeDataApp2.getImageId());
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(boolean z, FragmentQrTraySaved fragmentQrTraySaved, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$isSuccess = z;
            this.this$0 = fragmentQrTraySaved;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$isSuccess, this.this$0, continuation);
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
                if (this.$isSuccess) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new C0299a(this.this$0, null), 2, null);
                } else {
                    this.this$0.w();
                    this.this$0.dismissProgress();
                    FragmentQrTraySaved fragmentQrTraySaved = this.this$0;
                    String string = fragmentQrTraySaved.getString(R.string.error);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.error)");
                    String string2 = this.this$0.getString(R.string.qr_code_upload_could_not_be_completed);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qr_co…d_could_not_be_completed)");
                    Drawable drawable = AppCompatResources.getDrawable(this.this$0.requireContext(), R.drawable.ic_red_circular_cross);
                    Intrinsics.checkNotNull(drawable);
                    fragmentQrTraySaved.N(string, string2, drawable, true);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$logPushFailedReason$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
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
                if (FragmentQrTraySaved.this.getPushToWatchInitiated()) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    Context requireContext = FragmentQrTraySaved.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    hashMap.putAll(companion.getDeviceId(requireContext));
                    Context requireContext2 = FragmentQrTraySaved.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    hashMap.putAll(companion.getWatchDetails(requireContext2));
                    String value = CleverTapConstants.CustomEventProperties.QR_NAME.getValue();
                    QRCodeDataApp qRCodeDataApp = FragmentQrTraySaved.this.u;
                    Intrinsics.checkNotNull(qRCodeDataApp);
                    String imageTitle = qRCodeDataApp.getImageTitle();
                    Intrinsics.checkNotNull(imageTitle);
                    hashMap.put(value, imageTitle);
                    String value2 = CleverTapConstants.CustomEventProperties.QR_TAG.getValue();
                    QRCodeDataApp qRCodeDataApp2 = FragmentQrTraySaved.this.u;
                    Intrinsics.checkNotNull(qRCodeDataApp2);
                    String imageTag = qRCodeDataApp2.getImageTag();
                    Intrinsics.checkNotNull(imageTag);
                    hashMap.put(value2, imageTag);
                    hashMap.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.EDITED_EXISTING.getValue());
                    if (!AppUtils.isNetConnected(FragmentQrTraySaved.this.requireContext())) {
                        if (!FragmentQrTraySaved.this.getInternetChecked()) {
                            hashMap.put(CleverTapConstants.CustomEventProperties.FAILURE_REASON.getValue(), CleverTapConstants.CustomEventValues.INTERNET_DISCONNECTED.getValue());
                            FragmentQrTraySaved.this.setInternetChecked(true);
                        }
                    } else if (BleApiManager.getInstance(FragmentQrTraySaved.this.requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.DISCONNECTED) {
                        hashMap.put(CleverTapConstants.CustomEventProperties.FAILURE_REASON.getValue(), CleverTapConstants.CustomEventValues.WATCH_DISCONNECTED.getValue());
                    }
                    hashMap.put(CleverTapConstants.CustomEventProperties.LOCATION.getValue(), CleverTapConstants.CustomEventValues.OPTIONS_MENU.getValue());
                    companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_PUSHTOWATCH_FAILED.getValue(), hashMap);
                    FragmentQrTraySaved.this.setPushToWatchInitiated(false);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$onDeleteImageCheck$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                QRTrayViewModel qRTrayViewModel = FragmentQrTraySaved.this.t;
                if (qRTrayViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                    qRTrayViewModel = null;
                }
                QRCodeData qRCodeData = FragmentQrTraySaved.this.A;
                QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
                QRCodeDataApp qRCodeDataApp = FragmentQrTraySaved.this.u;
                Intrinsics.checkNotNull(qRCodeDataApp);
                String imageUrl = qRCodeDataApp.getImageUrl();
                Intrinsics.checkNotNull(imageUrl);
                qRTrayViewModel.uploadQRCodeImageToWatch(qRCodeData, qRCodeUtils.getBitmapFromUrl(imageUrl));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$onDeleteImageCheck$2", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
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
                FragmentQrTraySaved.this.w();
                FragmentQrTraySaved.this.dismissProgress();
                FragmentQrTraySaved.this.P(false);
                FragmentQrTraySaved fragmentQrTraySaved = FragmentQrTraySaved.this;
                String string = fragmentQrTraySaved.getString(R.string.something_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.something_went_wrong)");
                fragmentQrTraySaved.showSuccessNErrorToast(string);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$onDeleteQRCheckFromServer$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isSuccess;
        public int label;
        public final /* synthetic */ FragmentQrTraySaved this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(boolean z, FragmentQrTraySaved fragmentQrTraySaved, Continuation<? super e> continuation) {
            super(2, continuation);
            this.$isSuccess = z;
            this.this$0 = fragmentQrTraySaved;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new e(this.$isSuccess, this.this$0, continuation);
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
                if (this.$isSuccess) {
                    this.this$0.dismissProgress();
                    HashMap<String, Object> hashMap = new HashMap<>();
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    Context requireContext = this.this$0.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    hashMap.putAll(companion.getDeviceId(requireContext));
                    Context requireContext2 = this.this$0.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    hashMap.putAll(companion.getWatchDetails(requireContext2));
                    String value = CleverTapConstants.CustomEventProperties.QR_NAME.getValue();
                    QRCodeDataApp qRCodeDataApp = this.this$0.u;
                    Intrinsics.checkNotNull(qRCodeDataApp);
                    String imageTitle = qRCodeDataApp.getImageTitle();
                    Intrinsics.checkNotNull(imageTitle);
                    hashMap.put(value, imageTitle);
                    String value2 = CleverTapConstants.CustomEventProperties.QR_TAG.getValue();
                    QRCodeDataApp qRCodeDataApp2 = this.this$0.u;
                    Intrinsics.checkNotNull(qRCodeDataApp2);
                    String imageTag = qRCodeDataApp2.getImageTag();
                    Intrinsics.checkNotNull(imageTag);
                    hashMap.put(value2, imageTag);
                    hashMap.put(CleverTapConstants.CustomEventProperties.LOCATION.getValue(), CleverTapConstants.CustomEventValues.OPTIONS_MENU.getValue());
                    companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_DELETE_SUCCESS.getValue(), hashMap);
                    FragmentQrTraySaved fragmentQrTraySaved = this.this$0;
                    String string = fragmentQrTraySaved.getString(R.string.deleted_qr_code);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.deleted_qr_code)");
                    fragmentQrTraySaved.showSuccessNErrorToast(string);
                    TypeIntrinsics.asMutableCollection(this.this$0.v).remove(this.this$0.u);
                    QRCodeDataApp qRCodeDataApp3 = this.this$0.u;
                    Intrinsics.checkNotNull(qRCodeDataApp3);
                    if (qRCodeDataApp3.getAppliedToWatch()) {
                        FragmentActivity requireActivity = this.this$0.requireActivity();
                        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                        QRCodeDataApp qRCodeDataApp4 = this.this$0.u;
                        Intrinsics.checkNotNull(qRCodeDataApp4);
                        ((ActivityQRTray) requireActivity).removeServerAppliedQrCode(qRCodeDataApp4);
                    } else {
                        FragmentActivity requireActivity2 = this.this$0.requireActivity();
                        Intrinsics.checkNotNull(requireActivity2, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                        QRCodeDataApp qRCodeDataApp5 = this.this$0.u;
                        Intrinsics.checkNotNull(qRCodeDataApp5);
                        ((ActivityQRTray) requireActivity2).removeServerUnAppliedQrCode(qRCodeDataApp5);
                    }
                    FragmentActivity requireActivity3 = this.this$0.requireActivity();
                    Intrinsics.checkNotNull(requireActivity3, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                    QRCodeDataApp qRCodeDataApp6 = this.this$0.u;
                    Intrinsics.checkNotNull(qRCodeDataApp6);
                    ((ActivityQRTray) requireActivity3).removeQRCodeListFromWatchCachedData(qRCodeDataApp6.getImageId());
                    FragmentQrTraySaved fragmentQrTraySaved2 = this.this$0;
                    fragmentQrTraySaved2.D = fragmentQrTraySaved2.v.size() >= this.this$0.z;
                    if (!this.this$0.v.isEmpty()) {
                        this.this$0.u(true);
                    } else {
                        this.this$0.dismissProgress();
                        this.this$0.requireActivity().finish();
                    }
                } else {
                    this.this$0.dismissProgress();
                    FragmentQrTraySaved fragmentQrTraySaved3 = this.this$0;
                    String string2 = fragmentQrTraySaved3.getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.some_thing_went_wrong)");
                    fragmentQrTraySaved3.showSuccessNErrorToast(string2);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$onEditCheck$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isAppliedOnWatch;
        public final /* synthetic */ boolean $isSuccess;
        public int label;
        public final /* synthetic */ FragmentQrTraySaved this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(boolean z, FragmentQrTraySaved fragmentQrTraySaved, boolean z2, Continuation<? super f> continuation) {
            super(2, continuation);
            this.$isSuccess = z;
            this.this$0 = fragmentQrTraySaved;
            this.$isAppliedOnWatch = z2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new f(this.$isSuccess, this.this$0, this.$isAppliedOnWatch, continuation);
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
                if (!this.$isSuccess) {
                    this.this$0.w();
                } else {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    Context requireContext = this.this$0.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    hashMap.putAll(companion.getDeviceId(requireContext));
                    Context requireContext2 = this.this$0.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    hashMap.putAll(companion.getWatchDetails(requireContext2));
                    String value = CleverTapConstants.CustomEventProperties.QR_NAME.getValue();
                    QRCodeDataApp qRCodeDataApp = this.this$0.u;
                    Intrinsics.checkNotNull(qRCodeDataApp);
                    String imageTitle = qRCodeDataApp.getImageTitle();
                    Intrinsics.checkNotNull(imageTitle);
                    hashMap.put(value, imageTitle);
                    String value2 = CleverTapConstants.CustomEventProperties.QR_TAG.getValue();
                    QRCodeDataApp qRCodeDataApp2 = this.this$0.u;
                    Intrinsics.checkNotNull(qRCodeDataApp2);
                    String imageTag = qRCodeDataApp2.getImageTag();
                    Intrinsics.checkNotNull(imageTag);
                    hashMap.put(value2, imageTag);
                    hashMap.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.EDITED_EXISTING.getValue());
                    if (this.this$0.C) {
                        hashMap.put(CleverTapConstants.CustomEventProperties.TYPE2.getValue(), CleverTapConstants.CustomEventValues.REPLACED.getValue());
                    } else {
                        hashMap.put(CleverTapConstants.CustomEventProperties.TYPE2.getValue(), CleverTapConstants.CustomEventValues.NA.getValue());
                    }
                    hashMap.put(CleverTapConstants.CustomEventProperties.LOCATION.getValue(), CleverTapConstants.CustomEventValues.OPTIONS_MENU.getValue());
                    companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_PUSHTOWATCH_SUCCESS.getValue(), hashMap);
                }
                this.this$0.C = false;
                QRTrayViewModel qRTrayViewModel = this.this$0.t;
                if (qRTrayViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                    qRTrayViewModel = null;
                }
                qRTrayViewModel.getQRCodesFromServer();
                this.this$0.F = new QRCodeDataApp(0, null, null, null, null, null, false, null, 255, null);
                if (this.this$0.H) {
                    this.this$0.H = false;
                    FragmentQrTraySaved fragmentQrTraySaved = this.this$0;
                    String string = fragmentQrTraySaved.getString(R.string.the_oldest_qr_code_replaced);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
                    String string2 = this.this$0.getString(R.string.double_tap_the_watch_screen_to_change_the_brightness_level_in_case_qr_code_scanner_doesn_t_work);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.doubl…ode_scanner_doesn_t_work)");
                    Drawable drawable = AppCompatResources.getDrawable(this.this$0.requireContext(), R.drawable.ic_green_circular_tick);
                    Intrinsics.checkNotNull(drawable);
                    fragmentQrTraySaved.N(string, string2, drawable, true);
                } else {
                    FragmentQrTraySaved fragmentQrTraySaved2 = this.this$0;
                    String string3 = fragmentQrTraySaved2.getString(this.$isAppliedOnWatch ? R.string.qrcode_updated_successful_title : R.string.qrcode_updated_successful_title_app);
                    Intrinsics.checkNotNullExpressionValue(string3, "if (isAppliedOnWatch) ge…                        )");
                    Drawable drawable2 = AppCompatResources.getDrawable(this.this$0.requireContext(), R.drawable.ic_green_circular_tick);
                    Intrinsics.checkNotNull(drawable2);
                    fragmentQrTraySaved2.N(string3, "", drawable2, true);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$onFailure$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                FragmentQrTraySaved.this.dismissProgress();
                FragmentQrTraySaved fragmentQrTraySaved = FragmentQrTraySaved.this;
                String str = this.$message;
                if (str == null) {
                    str = fragmentQrTraySaved.getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(str, "getString(R.string.some_thing_went_wrong)");
                }
                fragmentQrTraySaved.showSuccessNErrorToast(str);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$onResume$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                FragmentQrTraySaved fragmentQrTraySaved = FragmentQrTraySaved.this;
                String string = fragmentQrTraySaved.getString(R.string.connect_watch);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.connect_watch)");
                String string2 = FragmentQrTraySaved.this.getString(R.string.please_connect_your_watch_in_order_to_apply_the_qr_code);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.pleas…der_to_apply_the_qr_code)");
                Drawable drawable = AppCompatResources.getDrawable(FragmentQrTraySaved.this.requireContext(), R.drawable.ic_red_circular_not_connected);
                Intrinsics.checkNotNull(drawable);
                fragmentQrTraySaved.N(string, string2, drawable, false);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$onViewCreated$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class i extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public i(Continuation<? super i> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new i(continuation);
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
                if (!AppUtils.isNetConnected(FragmentQrTraySaved.this.requireContext())) {
                    FragmentQrTraySaved fragmentQrTraySaved = FragmentQrTraySaved.this;
                    String string = fragmentQrTraySaved.getString(R.string.no_internet_available);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.no_internet_available)");
                    String string2 = FragmentQrTraySaved.this.getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.some_thing_went_wrong)");
                    Drawable drawable = AppCompatResources.getDrawable(FragmentQrTraySaved.this.requireContext(), R.drawable.ic_red_circular_no_internet);
                    Intrinsics.checkNotNull(drawable);
                    fragmentQrTraySaved.N(string, string2, drawable, false);
                } else {
                    FragmentQrTraySaved.this.showProgress(false);
                    QRTrayViewModel qRTrayViewModel = FragmentQrTraySaved.this.t;
                    QRTrayViewModel qRTrayViewModel2 = null;
                    if (qRTrayViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                        qRTrayViewModel = null;
                    }
                    qRTrayViewModel.getQRCodesFromServer();
                    QRTrayViewModel qRTrayViewModel3 = FragmentQrTraySaved.this.t;
                    if (qRTrayViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                    } else {
                        qRTrayViewModel2 = qRTrayViewModel3;
                    }
                    qRTrayViewModel2.getCategories();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$onViewCreated$2$2$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class j extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

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
                FragmentActivity activity = FragmentQrTraySaved.this.getActivity();
                if (activity != null) {
                    activity.finish();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$openQRMenuOptionsDialog$3$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class k extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$openQRMenuOptionsDialog$3$1$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ FragmentQrTraySaved this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentQrTraySaved fragmentQrTraySaved, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentQrTraySaved;
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
                FragmentQrTraySaved.this.showProgress(false);
                FragmentActivity requireActivity = FragmentQrTraySaved.this.requireActivity();
                Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                QRCodeDataApp qRCodeDataApp = FragmentQrTraySaved.this.u;
                Intrinsics.checkNotNull(qRCodeDataApp);
                int imageId = qRCodeDataApp.getImageId();
                QRCodeDataApp qRCodeDataApp2 = FragmentQrTraySaved.this.u;
                Intrinsics.checkNotNull(qRCodeDataApp2);
                String imageTitle = qRCodeDataApp2.getImageTitle();
                QRCodeDataApp qRCodeDataApp3 = FragmentQrTraySaved.this.u;
                Intrinsics.checkNotNull(qRCodeDataApp3);
                String imageTag = qRCodeDataApp3.getImageTag();
                QRCodeDataApp qRCodeDataApp4 = FragmentQrTraySaved.this.u;
                Intrinsics.checkNotNull(qRCodeDataApp4);
                String lastAppliedDate = qRCodeDataApp4.getLastAppliedDate();
                QRCodeDataApp qRCodeDataApp5 = FragmentQrTraySaved.this.u;
                Intrinsics.checkNotNull(qRCodeDataApp5);
                ((ActivityQRTray) requireActivity).addOrUpdateWatchAppliedQrCode(new QRCodeDataApp(imageId, imageTitle, imageTag, null, lastAppliedDate, qRCodeDataApp5.getServerId(), false, null, 192, null));
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentQrTraySaved.this), Dispatchers.getIO(), null, new a(FragmentQrTraySaved.this, null), 2, null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$openQRMenuOptionsDialog$3$2", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class l extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        /* loaded from: classes5.dex */
        public static final class a extends Lambda implements Function1<Integer, Unit> {
            public final /* synthetic */ FragmentQrTraySaved this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentQrTraySaved fragmentQrTraySaved) {
                super(1);
                this.this$0 = fragmentQrTraySaved;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                QRCodeDataApp qRCodeDataApp = this.this$0.u;
                Intrinsics.checkNotNull(qRCodeDataApp);
                qRCodeDataApp.setImageId(i);
                QRCodeData qRCodeData = this.this$0.A;
                Intrinsics.checkNotNull(qRCodeData);
                QRCodeDataApp qRCodeDataApp2 = this.this$0.u;
                Intrinsics.checkNotNull(qRCodeDataApp2);
                qRCodeData.setImageId(qRCodeDataApp2.getImageId());
                QRTrayViewModel qRTrayViewModel = this.this$0.t;
                if (qRTrayViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                    qRTrayViewModel = null;
                }
                QRCodeData qRCodeData2 = this.this$0.A;
                QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
                QRCodeDataApp qRCodeDataApp3 = this.this$0.u;
                Intrinsics.checkNotNull(qRCodeDataApp3);
                String imageUrl = qRCodeDataApp3.getImageUrl();
                Intrinsics.checkNotNull(imageUrl);
                qRTrayViewModel.uploadQRCodeImageToWatch(qRCodeData2, qRCodeUtils.getBitmapFromUrl(imageUrl));
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
                FragmentQrTraySaved.this.P(true);
                QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
                Context requireContext = FragmentQrTraySaved.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                FragmentActivity requireActivity = FragmentQrTraySaved.this.requireActivity();
                Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                qRCodeUtils.getAvailableQRCodeImageId(requireContext, ((ActivityQRTray) requireActivity).getWatchAndServerQRData().getServerAppliedQrCodes(), new a(FragmentQrTraySaved.this));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$setBottomSheetDialog$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class m extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$setBottomSheetDialog$1$1$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ FragmentQrTraySaved this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentQrTraySaved fragmentQrTraySaved, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentQrTraySaved;
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
                    QRCodeDataApp qRCodeDataApp = this.this$0.u;
                    Intrinsics.checkNotNull(qRCodeDataApp);
                    qRTrayViewModel.deleteQRCodesMetaDataToWatch(((ActivityQRTray) requireActivity).getBleQRCodeListFromWatchCachedDataExcluding(qRCodeDataApp));
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public m(Continuation<? super m> continuation) {
            super(2, continuation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(FragmentQrTraySaved fragmentQrTraySaved, View view) {
            fragmentQrTraySaved.showProgress(false);
            QRCodeDataApp qRCodeDataApp = fragmentQrTraySaved.u;
            Intrinsics.checkNotNull(qRCodeDataApp);
            qRCodeDataApp.getImageId();
            QRCodeDataApp qRCodeDataApp2 = fragmentQrTraySaved.u;
            Intrinsics.checkNotNull(qRCodeDataApp2);
            QRTrayViewModel qRTrayViewModel = null;
            if (qRCodeDataApp2.getImageId() == 0) {
                QRTrayViewModel qRTrayViewModel2 = fragmentQrTraySaved.t;
                if (qRTrayViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                } else {
                    qRTrayViewModel = qRTrayViewModel2;
                }
                FragmentActivity requireActivity = fragmentQrTraySaved.requireActivity();
                Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                QRCodeDataApp qRCodeDataApp3 = fragmentQrTraySaved.u;
                Intrinsics.checkNotNull(qRCodeDataApp3);
                qRTrayViewModel.deleteQRCodeFromServer(CollectionsKt__CollectionsKt.arrayListOf(((ActivityQRTray) requireActivity).getServerApiQRCodeDataFromAppData(qRCodeDataApp3)));
            } else if (BleApiManager.getInstance(fragmentQrTraySaved.requireContext()).getBleApi() != null && BleApiManager.getInstance(fragmentQrTraySaved.requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(fragmentQrTraySaved), Dispatchers.getIO(), null, new a(fragmentQrTraySaved, null), 2, null);
            } else {
                String string = fragmentQrTraySaved.getString(R.string.connect_watch);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.connect_watch)");
                String string2 = fragmentQrTraySaved.getString(R.string.please_connect_your_watch_in_order_to_apply_the_qr_code);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.pleas…der_to_apply_the_qr_code)");
                Drawable drawable = AppCompatResources.getDrawable(fragmentQrTraySaved.requireContext(), R.drawable.ic_red_circular_not_connected);
                Intrinsics.checkNotNull(drawable);
                FragmentQrTraySaved.O(fragmentQrTraySaved, string, string2, drawable, false, 8, null);
            }
            BottomSheetDialogOneTitleMessageTwoButtons bottomSheetDialogOneTitleMessageTwoButtons = fragmentQrTraySaved.p;
            Intrinsics.checkNotNull(bottomSheetDialogOneTitleMessageTwoButtons);
            bottomSheetDialogOneTitleMessageTwoButtons.dismiss();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$1(FragmentQrTraySaved fragmentQrTraySaved, View view) {
            BottomSheetDialogOneTitleMessageTwoButtons bottomSheetDialogOneTitleMessageTwoButtons = fragmentQrTraySaved.p;
            Intrinsics.checkNotNull(bottomSheetDialogOneTitleMessageTwoButtons);
            bottomSheetDialogOneTitleMessageTwoButtons.dismiss();
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
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (FragmentQrTraySaved.this.p == null) {
                    FragmentQrTraySaved fragmentQrTraySaved = FragmentQrTraySaved.this;
                    Context requireContext = fragmentQrTraySaved.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    String string = FragmentQrTraySaved.this.getString(R.string.delete_qr_code);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.delete_qr_code)");
                    String string2 = FragmentQrTraySaved.this.getString(R.string.please_note_that_you_cannot_go_back_once_you_proceed_with_deletion_do_you_wish_to_delete_this_qr_code_permanently);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.pleas…this_qr_code_permanently)");
                    fragmentQrTraySaved.p = new BottomSheetDialogOneTitleMessageTwoButtons(requireContext, string, string2);
                }
                BottomSheetDialogOneTitleMessageTwoButtons bottomSheetDialogOneTitleMessageTwoButtons = FragmentQrTraySaved.this.p;
                Intrinsics.checkNotNull(bottomSheetDialogOneTitleMessageTwoButtons);
                bottomSheetDialogOneTitleMessageTwoButtons.setCancelable(false);
                BottomSheetDialogOneTitleMessageTwoButtons bottomSheetDialogOneTitleMessageTwoButtons2 = FragmentQrTraySaved.this.p;
                Intrinsics.checkNotNull(bottomSheetDialogOneTitleMessageTwoButtons2);
                String string3 = FragmentQrTraySaved.this.getString(R.string.yes);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.yes)");
                final FragmentQrTraySaved fragmentQrTraySaved2 = FragmentQrTraySaved.this;
                bottomSheetDialogOneTitleMessageTwoButtons2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.l
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentQrTraySaved.m.invokeSuspend$lambda$0(FragmentQrTraySaved.this, view);
                    }
                });
                BottomSheetDialogOneTitleMessageTwoButtons bottomSheetDialogOneTitleMessageTwoButtons3 = FragmentQrTraySaved.this.p;
                Intrinsics.checkNotNull(bottomSheetDialogOneTitleMessageTwoButtons3);
                String string4 = FragmentQrTraySaved.this.getString(R.string.cancel);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(\n             …cel\n                    )");
                final FragmentQrTraySaved fragmentQrTraySaved3 = FragmentQrTraySaved.this;
                bottomSheetDialogOneTitleMessageTwoButtons3.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.m
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentQrTraySaved.m.invokeSuspend$lambda$1(FragmentQrTraySaved.this, view);
                    }
                });
                BottomSheetDialogOneTitleMessageTwoButtons bottomSheetDialogOneTitleMessageTwoButtons4 = FragmentQrTraySaved.this.p;
                Intrinsics.checkNotNull(bottomSheetDialogOneTitleMessageTwoButtons4);
                if (!bottomSheetDialogOneTitleMessageTwoButtons4.isShowing() && BleApiManager.getInstance(FragmentQrTraySaved.this.requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    BottomSheetDialogOneTitleMessageTwoButtons bottomSheetDialogOneTitleMessageTwoButtons5 = FragmentQrTraySaved.this.p;
                    Intrinsics.checkNotNull(bottomSheetDialogOneTitleMessageTwoButtons5);
                    bottomSheetDialogOneTitleMessageTwoButtons5.show();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$setMaxedOutDialogInWatch$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class n extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$setMaxedOutDialogInWatch$1$1$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ FragmentQrTraySaved this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentQrTraySaved fragmentQrTraySaved, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentQrTraySaved;
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
                    QRCodeDataApp qRCodeDataApp = this.this$0.F;
                    Intrinsics.checkNotNull(qRCodeDataApp);
                    qRTrayViewModel.deleteQRCodesMetaDataToWatch(((ActivityQRTray) requireActivity).getBleQRCodeListFromWatchCachedDataExcluding(qRCodeDataApp));
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$setMaxedOutDialogInWatch$1$2$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ FragmentQrTraySaved this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(FragmentQrTraySaved fragmentQrTraySaved, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = fragmentQrTraySaved;
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
                    QRTrayViewModel qRTrayViewModel = this.this$0.t;
                    if (qRTrayViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                        qRTrayViewModel = null;
                    }
                    QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
                    QRCodeDataApp qRCodeDataApp = this.this$0.u;
                    Intrinsics.checkNotNull(qRCodeDataApp);
                    String imageUrl = qRCodeDataApp.getImageUrl();
                    Intrinsics.checkNotNull(imageUrl);
                    qRTrayViewModel.uploadQRCodePicAndSaveToServer(qRCodeUtils.getBitmapFromUrl(imageUrl), this.this$0.B);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public n(Continuation<? super n> continuation) {
            super(2, continuation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(FragmentQrTraySaved fragmentQrTraySaved, View view) {
            fragmentQrTraySaved.P(true);
            fragmentQrTraySaved.C = true;
            fragmentQrTraySaved.H = true;
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(fragmentQrTraySaved), Dispatchers.getIO(), null, new a(fragmentQrTraySaved, null), 2, null);
            BottomSheetDialogQRTray bottomSheetDialogQRTray = fragmentQrTraySaved.q;
            Intrinsics.checkNotNull(bottomSheetDialogQRTray);
            bottomSheetDialogQRTray.dismiss();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$1(FragmentQrTraySaved fragmentQrTraySaved, View view) {
            if (!fragmentQrTraySaved.D) {
                fragmentQrTraySaved.showProgress(false);
                fragmentQrTraySaved.B.setCategoryId(fragmentQrTraySaved.x);
                QRTraySaveReq qRTraySaveReq = fragmentQrTraySaved.B;
                QRCodeDataApp qRCodeDataApp = fragmentQrTraySaved.u;
                Intrinsics.checkNotNull(qRCodeDataApp);
                qRTraySaveReq.setName(qRCodeDataApp.getImageTitle());
                fragmentQrTraySaved.B.setImageRefId(0);
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(fragmentQrTraySaved), Dispatchers.getIO(), null, new b(fragmentQrTraySaved, null), 2, null);
                BottomSheetDialogQRTray bottomSheetDialogQRTray = fragmentQrTraySaved.q;
                Intrinsics.checkNotNull(bottomSheetDialogQRTray);
                bottomSheetDialogQRTray.dismiss();
                return;
            }
            HashMap<String, Object> hashMap = new HashMap<>();
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            Context requireContext = fragmentQrTraySaved.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            hashMap.putAll(companion.getDeviceId(requireContext));
            Context requireContext2 = fragmentQrTraySaved.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            hashMap.putAll(companion.getWatchDetails(requireContext2));
            String value = CleverTapConstants.CustomEventProperties.QR_NAME.getValue();
            QRCodeDataApp qRCodeDataApp2 = fragmentQrTraySaved.u;
            Intrinsics.checkNotNull(qRCodeDataApp2);
            String imageTitle = qRCodeDataApp2.getImageTitle();
            Intrinsics.checkNotNull(imageTitle);
            hashMap.put(value, imageTitle);
            String value2 = CleverTapConstants.CustomEventProperties.QR_TAG.getValue();
            QRCodeDataApp qRCodeDataApp3 = fragmentQrTraySaved.u;
            Intrinsics.checkNotNull(qRCodeDataApp3);
            String imageTag = qRCodeDataApp3.getImageTag();
            Intrinsics.checkNotNull(imageTag);
            hashMap.put(value2, imageTag);
            hashMap.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.EDITED_EXISTING.getValue());
            hashMap.put(CleverTapConstants.CustomEventProperties.LOCATION.getValue(), CleverTapConstants.CustomEventValues.OPTIONS_MENU.getValue());
            hashMap.put(CleverTapConstants.CustomEventProperties.FAILURE_REASON.getValue(), CleverTapConstants.CustomEventValues.QR_LIMIT_EXCEEDED.getValue());
            companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_PUSHTOWATCH_FAILED.getValue(), hashMap);
            BottomSheetDialogQRTray bottomSheetDialogQRTray2 = fragmentQrTraySaved.q;
            Intrinsics.checkNotNull(bottomSheetDialogQRTray2);
            bottomSheetDialogQRTray2.dismiss();
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
                FragmentQrTraySaved.this.dismissProgress();
                if (FragmentQrTraySaved.this.q == null) {
                    FragmentQrTraySaved fragmentQrTraySaved = FragmentQrTraySaved.this;
                    Context requireContext = fragmentQrTraySaved.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    fragmentQrTraySaved.q = new BottomSheetDialogQRTray(requireContext);
                }
                BottomSheetDialogQRTray bottomSheetDialogQRTray = FragmentQrTraySaved.this.q;
                Intrinsics.checkNotNull(bottomSheetDialogQRTray);
                QRCodeDataApp qRCodeDataApp = FragmentQrTraySaved.this.F;
                Intrinsics.checkNotNull(qRCodeDataApp);
                String imageTitle = qRCodeDataApp.getImageTitle();
                Intrinsics.checkNotNull(imageTitle);
                QRCodeDataApp qRCodeDataApp2 = FragmentQrTraySaved.this.F;
                Intrinsics.checkNotNull(qRCodeDataApp2);
                String imageTag = qRCodeDataApp2.getImageTag();
                Intrinsics.checkNotNull(imageTag);
                QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
                QRCodeDataApp qRCodeDataApp3 = FragmentQrTraySaved.this.F;
                Intrinsics.checkNotNull(qRCodeDataApp3);
                String imageTag2 = qRCodeDataApp3.getImageTag();
                ArrayList arrayList = FragmentQrTraySaved.this.E;
                Intrinsics.checkNotNull(arrayList);
                String categoryIconURL = qRCodeUtils.getCategoryIconURL(imageTag2, arrayList);
                Intrinsics.checkNotNull(categoryIconURL);
                bottomSheetDialogQRTray.setQRCodeData(false, imageTitle, imageTag, categoryIconURL);
                BottomSheetDialogQRTray bottomSheetDialogQRTray2 = FragmentQrTraySaved.this.q;
                Intrinsics.checkNotNull(bottomSheetDialogQRTray2);
                bottomSheetDialogQRTray2.setCancelable(false);
                BottomSheetDialogQRTray bottomSheetDialogQRTray3 = FragmentQrTraySaved.this.q;
                Intrinsics.checkNotNull(bottomSheetDialogQRTray3);
                final FragmentQrTraySaved fragmentQrTraySaved2 = FragmentQrTraySaved.this;
                bottomSheetDialogQRTray3.setPositiveButton(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.n
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentQrTraySaved.n.invokeSuspend$lambda$0(FragmentQrTraySaved.this, view);
                    }
                });
                BottomSheetDialogQRTray bottomSheetDialogQRTray4 = FragmentQrTraySaved.this.q;
                Intrinsics.checkNotNull(bottomSheetDialogQRTray4);
                final FragmentQrTraySaved fragmentQrTraySaved3 = FragmentQrTraySaved.this;
                bottomSheetDialogQRTray4.setNegativeButton(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.o
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentQrTraySaved.n.invokeSuspend$lambda$1(FragmentQrTraySaved.this, view);
                    }
                });
                BottomSheetDialogQRTray bottomSheetDialogQRTray5 = FragmentQrTraySaved.this.q;
                Intrinsics.checkNotNull(bottomSheetDialogQRTray5);
                if (!bottomSheetDialogQRTray5.isShowing() && BleApiManager.getInstance(FragmentQrTraySaved.this.requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    BottomSheetDialogQRTray bottomSheetDialogQRTray6 = FragmentQrTraySaved.this.q;
                    Intrinsics.checkNotNull(bottomSheetDialogQRTray6);
                    bottomSheetDialogQRTray6.show();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes5.dex */
    public static final class o extends Lambda implements Function1<ArrayList<QRTrayCategoriesRes.QRItem>, Unit> {

        @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$setObservers$1$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ ArrayList<QRTrayCategoriesRes.QRItem> $categoryItems;
            public int label;
            public final /* synthetic */ FragmentQrTraySaved this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ArrayList<QRTrayCategoriesRes.QRItem> arrayList, FragmentQrTraySaved fragmentQrTraySaved, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$categoryItems = arrayList;
                this.this$0 = fragmentQrTraySaved;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$categoryItems, this.this$0, continuation);
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
                    if (this.$categoryItems != null) {
                        this.this$0.E.clear();
                        QRTrayCategoriesRes.QRItem qRItem = new QRTrayCategoriesRes.QRItem();
                        qRItem.setCategoryId("ALL");
                        qRItem.setTitle("All");
                        this.this$0.E.add(qRItem);
                        this.this$0.E.addAll(this.$categoryItems);
                        QRTrayCategoryAdapter qRTrayCategoryAdapter = this.this$0.s;
                        QRTrayCategoryAdapter qRTrayCategoryAdapter2 = null;
                        if (qRTrayCategoryAdapter == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("qrTrayCategoryAdapter");
                            qRTrayCategoryAdapter = null;
                        }
                        qRTrayCategoryAdapter.setSelectedCategory(0);
                        QRTrayCategoryAdapter qRTrayCategoryAdapter3 = this.this$0.s;
                        if (qRTrayCategoryAdapter3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("qrTrayCategoryAdapter");
                        } else {
                            qRTrayCategoryAdapter2 = qRTrayCategoryAdapter3;
                        }
                        qRTrayCategoryAdapter2.setCategoryList(this.this$0.E);
                        FragmentQrTraySaved fragmentQrTraySaved = this.this$0;
                        String categoryId = ((QRTrayCategoriesRes.QRItem) fragmentQrTraySaved.E.get(0)).getCategoryId();
                        Intrinsics.checkNotNullExpressionValue(categoryId, "categoryList[0].categoryId");
                        fragmentQrTraySaved.x = categoryId;
                        this.this$0.u(false);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public o() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ArrayList<QRTrayCategoriesRes.QRItem> arrayList) {
            invoke2(arrayList);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(ArrayList<QRTrayCategoriesRes.QRItem> arrayList) {
            if (FragmentQrTraySaved.this.isAdded()) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentQrTraySaved.this), Dispatchers.getMain(), null, new a(arrayList, FragmentQrTraySaved.this, null), 2, null);
            }
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$showCommonDialog$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class p extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $desc;
        public final /* synthetic */ Drawable $drawable;
        public final /* synthetic */ boolean $isNotFinish;
        public final /* synthetic */ String $title;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public p(Drawable drawable, String str, String str2, boolean z, Continuation<? super p> continuation) {
            super(2, continuation);
            this.$drawable = drawable;
            this.$title = str;
            this.$desc = str2;
            this.$isNotFinish = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(boolean z, FragmentQrTraySaved fragmentQrTraySaved, View view) {
            FragmentActivity activity;
            if (!z && (activity = fragmentQrTraySaved.getActivity()) != null) {
                activity.finish();
            }
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = fragmentQrTraySaved.r;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
            bottomSheetDialogImageTitleMessage.dismiss();
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new p(this.$drawable, this.$title, this.$desc, this.$isNotFinish, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((p) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (FragmentQrTraySaved.this.r != null) {
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = FragmentQrTraySaved.this.r;
                    boolean z = true;
                    if (bottomSheetDialogImageTitleMessage2 == null || !bottomSheetDialogImageTitleMessage2.isShowing()) {
                        z = false;
                    }
                    if (z && (bottomSheetDialogImageTitleMessage = FragmentQrTraySaved.this.r) != null) {
                        bottomSheetDialogImageTitleMessage.dismiss();
                    }
                    FragmentQrTraySaved.this.r = null;
                }
                if (FragmentQrTraySaved.this.r == null) {
                    FragmentQrTraySaved fragmentQrTraySaved = FragmentQrTraySaved.this;
                    Context requireContext = fragmentQrTraySaved.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    fragmentQrTraySaved.r = new BottomSheetDialogImageTitleMessage(requireContext, this.$drawable, this.$title, this.$desc, false);
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = FragmentQrTraySaved.this.r;
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
                    bottomSheetDialogImageTitleMessage3.showBigIcon();
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = FragmentQrTraySaved.this.r;
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage4);
                    String string = FragmentQrTraySaved.this.getString(R.string.okay);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.okay)");
                    final boolean z2 = this.$isNotFinish;
                    final FragmentQrTraySaved fragmentQrTraySaved2 = FragmentQrTraySaved.this;
                    bottomSheetDialogImageTitleMessage4.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.p
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            FragmentQrTraySaved.p.invokeSuspend$lambda$0(z2, fragmentQrTraySaved2, view);
                        }
                    });
                }
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage5 = FragmentQrTraySaved.this.r;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage5);
                if (!bottomSheetDialogImageTitleMessage5.isShowing()) {
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage6 = FragmentQrTraySaved.this.r;
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage6);
                    bottomSheetDialogImageTitleMessage6.show();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$showOrStopSavingProgressAnimation$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class q extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isStart;
        public int label;
        public final /* synthetic */ FragmentQrTraySaved this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public q(boolean z, FragmentQrTraySaved fragmentQrTraySaved, Continuation<? super q> continuation) {
            super(2, continuation);
            this.$isStart = z;
            this.this$0 = fragmentQrTraySaved;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new q(this.$isStart, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((q) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$isStart) {
                    FragmentQrTraySaved fragmentQrTraySaved = this.this$0;
                    String string = fragmentQrTraySaved.getString(R.string.saving_and_pushing_to_watch);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.saving_and_pushing_to_watch)");
                    fragmentQrTraySaved.showQRCodeProgress(false, string);
                } else {
                    this.this$0.dismissQRCodeProgress();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$uploadingImageCheck$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class r extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public r(Continuation<? super r> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new r(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((r) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                QRTrayViewModel qRTrayViewModel = FragmentQrTraySaved.this.t;
                if (qRTrayViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                    qRTrayViewModel = null;
                }
                FragmentActivity requireActivity = FragmentQrTraySaved.this.requireActivity();
                Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                qRTrayViewModel.setQRCodesMetaDataToWatch(((ActivityQRTray) requireActivity).getBleQRCodeListFromWatchCachedData());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$uploadingImageCheck$2", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class s extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public s(Continuation<? super s> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new s(continuation);
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
                FragmentQrTraySaved.this.w();
                FragmentQrTraySaved.this.P(false);
                FragmentQrTraySaved fragmentQrTraySaved = FragmentQrTraySaved.this;
                String string = fragmentQrTraySaved.getString(R.string.error);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.error)");
                String string2 = FragmentQrTraySaved.this.getString(R.string.qr_code_upload_could_not_be_completed);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qr_co…d_could_not_be_completed)");
                Drawable drawable = AppCompatResources.getDrawable(FragmentQrTraySaved.this.requireContext(), R.drawable.ic_red_circular_cross);
                Intrinsics.checkNotNull(drawable);
                fragmentQrTraySaved.N(string, string2, drawable, true);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$uploadingQRCodeMetaDataCheck$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class t extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ boolean $isSuccess;
        public int label;
        public final /* synthetic */ FragmentQrTraySaved this$0;

        @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$uploadingQRCodeMetaDataCheck$1$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ FragmentQrTraySaved this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentQrTraySaved fragmentQrTraySaved, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentQrTraySaved;
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
                    qRTrayViewModel.editQRCodeToServer(this.this$0.B, this.this$0.G);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public t(boolean z, FragmentQrTraySaved fragmentQrTraySaved, Continuation<? super t> continuation) {
            super(2, continuation);
            this.$isSuccess = z;
            this.this$0 = fragmentQrTraySaved;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new t(this.$isSuccess, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((t) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            String str;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$isSuccess) {
                    FragmentQrTraySaved fragmentQrTraySaved = this.this$0;
                    QRCodeDataApp qRCodeDataApp = fragmentQrTraySaved.u;
                    Intrinsics.checkNotNull(qRCodeDataApp);
                    String imageTitle = qRCodeDataApp.getImageTitle();
                    QRCodeDataApp qRCodeDataApp2 = this.this$0.u;
                    Intrinsics.checkNotNull(qRCodeDataApp2);
                    String imageTag = qRCodeDataApp2.getImageTag();
                    QRCodeDataApp qRCodeDataApp3 = this.this$0.u;
                    Intrinsics.checkNotNull(qRCodeDataApp3);
                    String imageUrl = qRCodeDataApp3.getImageUrl();
                    Boolean boxBoolean = Boxing.boxBoolean(true);
                    QRCodeDataApp qRCodeDataApp4 = this.this$0.u;
                    Intrinsics.checkNotNull(qRCodeDataApp4);
                    Integer boxInt = Boxing.boxInt(qRCodeDataApp4.getImageId());
                    if (this.this$0.F != null) {
                        QRCodeDataApp qRCodeDataApp5 = this.this$0.F;
                        Intrinsics.checkNotNull(qRCodeDataApp5);
                        str = qRCodeDataApp5.getServerId();
                    } else {
                        str = null;
                    }
                    fragmentQrTraySaved.B = new QRTraySaveReq(imageTitle, imageTag, imageUrl, boxBoolean, boxInt, str);
                    if (this.this$0.F != null) {
                        QRTraySaveReq qRTraySaveReq = this.this$0.B;
                        QRCodeDataApp qRCodeDataApp6 = this.this$0.F;
                        Intrinsics.checkNotNull(qRCodeDataApp6);
                        qRTraySaveReq.setImageRefId(Boxing.boxInt(qRCodeDataApp6.getImageId()));
                    }
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new a(this.this$0, null), 2, null);
                } else {
                    this.this$0.dismissProgress();
                    this.this$0.w();
                    FragmentQrTraySaved fragmentQrTraySaved2 = this.this$0;
                    String string = fragmentQrTraySaved2.getString(R.string.error);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.error)");
                    String string2 = this.this$0.getString(R.string.qr_code_upload_could_not_be_completed);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qr_co…d_could_not_be_completed)");
                    Drawable drawable = AppCompatResources.getDrawable(this.this$0.requireContext(), R.drawable.ic_red_circular_cross);
                    Intrinsics.checkNotNull(drawable);
                    fragmentQrTraySaved2.N(string, string2, drawable, true);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void A(FragmentQrTraySaved this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getMain(), null, new j(null), 2, null);
        }
    }

    public static final void C(FragmentQrTraySaved this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion.getDeviceId(requireContext));
        Context requireContext2 = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        hashMap.putAll(companion.getWatchDetails(requireContext2));
        String value = CleverTapConstants.CustomEventProperties.QR_NAME.getValue();
        QRCodeDataApp qRCodeDataApp = this$0.u;
        Intrinsics.checkNotNull(qRCodeDataApp);
        String imageTitle = qRCodeDataApp.getImageTitle();
        Intrinsics.checkNotNull(imageTitle);
        hashMap.put(value, imageTitle);
        String value2 = CleverTapConstants.CustomEventProperties.QR_TAG.getValue();
        QRCodeDataApp qRCodeDataApp2 = this$0.u;
        Intrinsics.checkNotNull(qRCodeDataApp2);
        String imageTag = qRCodeDataApp2.getImageTag();
        Intrinsics.checkNotNull(imageTag);
        hashMap.put(value2, imageTag);
        hashMap.put(CleverTapConstants.CustomEventProperties.LOCATION.getValue(), CleverTapConstants.CustomEventValues.OPTIONS_MENU.getValue());
        companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_DELETE_REQUEST.getValue(), hashMap);
        this$0.F();
        QRTrayMenuDialog qRTrayMenuDialog = this$0.o;
        if (qRTrayMenuDialog != null) {
            qRTrayMenuDialog.dismiss();
        }
    }

    public static final void D(FragmentQrTraySaved this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.x(false);
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion.getDeviceId(requireContext));
        Context requireContext2 = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        hashMap.putAll(companion.getWatchDetails(requireContext2));
        String value = CleverTapConstants.CustomEventProperties.QR_NAME.getValue();
        QRCodeDataApp qRCodeDataApp = this$0.u;
        Intrinsics.checkNotNull(qRCodeDataApp);
        String imageTitle = qRCodeDataApp.getImageTitle();
        Intrinsics.checkNotNull(imageTitle);
        hashMap.put(value, imageTitle);
        String value2 = CleverTapConstants.CustomEventProperties.QR_TAG.getValue();
        QRCodeDataApp qRCodeDataApp2 = this$0.u;
        Intrinsics.checkNotNull(qRCodeDataApp2);
        String imageTag = qRCodeDataApp2.getImageTag();
        Intrinsics.checkNotNull(imageTag);
        hashMap.put(value2, imageTag);
        companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_EDIT_REQUEST.getValue(), hashMap);
        QRTrayMenuDialog qRTrayMenuDialog = this$0.o;
        if (qRTrayMenuDialog != null) {
            qRTrayMenuDialog.dismiss();
        }
    }

    public static final void E(FragmentQrTraySaved this$0, View view) {
        FragmentActivity requireActivity;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion.getDeviceId(requireContext));
        Context requireContext2 = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        hashMap.putAll(companion.getWatchDetails(requireContext2));
        String value = CleverTapConstants.CustomEventProperties.QR_NAME.getValue();
        QRCodeDataApp qRCodeDataApp = this$0.u;
        Intrinsics.checkNotNull(qRCodeDataApp);
        String imageTitle = qRCodeDataApp.getImageTitle();
        Intrinsics.checkNotNull(imageTitle);
        hashMap.put(value, imageTitle);
        String value2 = CleverTapConstants.CustomEventProperties.QR_TAG.getValue();
        QRCodeDataApp qRCodeDataApp2 = this$0.u;
        Intrinsics.checkNotNull(qRCodeDataApp2);
        String imageTag = qRCodeDataApp2.getImageTag();
        Intrinsics.checkNotNull(imageTag);
        hashMap.put(value2, imageTag);
        hashMap.put(CleverTapConstants.CustomEventProperties.TYPE.getValue(), CleverTapConstants.CustomEventValues.EDITED_EXISTING.getValue());
        hashMap.put(CleverTapConstants.CustomEventProperties.LOCATION.getValue(), CleverTapConstants.CustomEventValues.OPTIONS_MENU.getValue());
        companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_PUSHTOWATCH_REQUEST.getValue(), hashMap);
        this$0.J = true;
        if (AppUtils.isNetConnected(this$0.requireContext())) {
            if (BleApiManager.getInstance(this$0.requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                QRCodeDataApp qRCodeDataApp3 = this$0.u;
                Intrinsics.checkNotNull(qRCodeDataApp3);
                qRCodeDataApp3.getImageId();
                QRCodeDataApp qRCodeDataApp4 = this$0.u;
                Intrinsics.checkNotNull(qRCodeDataApp4);
                if (qRCodeDataApp4.getImageId() != 0) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getMain(), null, new k(null), 2, null);
                } else {
                    QRCodeData qRCodeData = this$0.A;
                    QRCodeDataApp qRCodeDataApp5 = this$0.u;
                    Intrinsics.checkNotNull(qRCodeDataApp5);
                    qRCodeData.setImageTitle(qRCodeDataApp5.getImageTitle());
                    QRCodeData qRCodeData2 = this$0.A;
                    QRCodeDataApp qRCodeDataApp6 = this$0.u;
                    Intrinsics.checkNotNull(qRCodeDataApp6);
                    qRCodeData2.setImageTag(qRCodeDataApp6.getImageTag());
                    FragmentActivity requireActivity2 = this$0.requireActivity();
                    Intrinsics.checkNotNull(requireActivity2, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                    int size = ((ActivityQRTray) requireActivity2).getWatchAndServerQRData().getWatchQrCodes().size();
                    QRTrayViewModel qRTrayViewModel = this$0.t;
                    if (qRTrayViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                        qRTrayViewModel = null;
                    }
                    Integer maxAllowed = qRTrayViewModel.getQrCodeDetailsOfWatchFromFirebaseRemoteConfig().getMaxAllowed();
                    Intrinsics.checkNotNullExpressionValue(maxAllowed, "qrTrayViewModel.qrCodeDe…seRemoteConfig.maxAllowed");
                    if (size < maxAllowed.intValue()) {
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getIO(), null, new l(null), 2, null);
                    } else {
                        Intrinsics.checkNotNull(this$0.requireActivity(), "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                        if (!((ActivityQRTray) requireActivity).getWatchAndServerQRData().getServerAppliedQrCodes().isEmpty()) {
                            QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
                            FragmentActivity requireActivity3 = this$0.requireActivity();
                            Intrinsics.checkNotNull(requireActivity3, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                            this$0.F = qRCodeUtils.getFirstUploadedQRData(((ActivityQRTray) requireActivity3).getWatchAndServerQRData().getServerAppliedQrCodes());
                            this$0.J();
                        } else {
                            String string = this$0.getString(R.string.un_applied_check_info);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.un_applied_check_info)");
                            Drawable drawable = AppCompatResources.getDrawable(this$0.requireContext(), R.drawable.ic_red_circular_cross);
                            Intrinsics.checkNotNull(drawable);
                            this$0.N(string, "", drawable, true);
                        }
                    }
                }
                QRTrayMenuDialog qRTrayMenuDialog = this$0.o;
                if (qRTrayMenuDialog != null) {
                    qRTrayMenuDialog.dismiss();
                    return;
                }
                return;
            }
            this$0.w();
        } else if (this$0.I) {
        } else {
            this$0.w();
            this$0.I = true;
        }
    }

    public static final void H(FragmentQrTraySaved this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogQRTray bottomSheetDialogQRTray = this$0.q;
        Intrinsics.checkNotNull(bottomSheetDialogQRTray);
        bottomSheetDialogQRTray.dismiss();
    }

    public static final void I(FragmentQrTraySaved this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getString(R.string.you_have_maxed_the_limit_on_watch);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.you_h…maxed_the_limit_on_watch)");
        this$0.showSuccessNErrorToast(string);
        BottomSheetDialogQRTray bottomSheetDialogQRTray = this$0.q;
        Intrinsics.checkNotNull(bottomSheetDialogQRTray);
        bottomSheetDialogQRTray.dismiss();
    }

    public static final void L(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void M(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static /* synthetic */ void O(FragmentQrTraySaved fragmentQrTraySaved, String str, String str2, Drawable drawable, boolean z, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z = false;
        }
        fragmentQrTraySaved.N(str, str2, drawable, z);
    }

    public static final void z(FragmentQrTraySaved this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion.getDeviceId(requireContext));
        Context requireContext2 = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        hashMap.putAll(companion.getWatchDetails(requireContext2));
        hashMap.put(CleverTapConstants.CustomEventProperties.UPLOAD_FROM.getValue(), CleverTapConstants.CustomEventValues.QR_LANDING_PAGE.getValue());
        companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_UPLOAD_REQUEST.getValue(), hashMap);
        this$0.t();
    }

    public final void B() {
        QRTrayMenuDialog qRTrayMenuDialog;
        QRTrayMenuDialog qRTrayMenuDialog2;
        QRTrayMenuDialog qRTrayMenuDialog3;
        QRTrayMenuDialog qRTrayMenuDialog4 = this.o;
        if (qRTrayMenuDialog4 != null) {
            Boolean valueOf = qRTrayMenuDialog4 != null ? Boolean.valueOf(qRTrayMenuDialog4.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (qRTrayMenuDialog3 = this.o) != null) {
                qRTrayMenuDialog3.dismiss();
            }
            this.o = null;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.o = new QRTrayMenuDialog(requireContext);
        QRCodeDataApp qRCodeDataApp = this.u;
        boolean z = true;
        if (qRCodeDataApp == null || !qRCodeDataApp.getAppliedToWatch()) {
            z = false;
        }
        if (z && (qRTrayMenuDialog2 = this.o) != null) {
            qRTrayMenuDialog2.hidePushQButton();
        }
        QRTrayMenuDialog qRTrayMenuDialog5 = this.o;
        if (qRTrayMenuDialog5 != null) {
            qRTrayMenuDialog5.setDeleteButton(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.i
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentQrTraySaved.C(FragmentQrTraySaved.this, view);
                }
            });
        }
        QRTrayMenuDialog qRTrayMenuDialog6 = this.o;
        if (qRTrayMenuDialog6 != null) {
            qRTrayMenuDialog6.setEditQrCodeButton(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.g
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentQrTraySaved.D(FragmentQrTraySaved.this, view);
                }
            });
        }
        QRTrayMenuDialog qRTrayMenuDialog7 = this.o;
        if (qRTrayMenuDialog7 != null) {
            qRTrayMenuDialog7.setPushQRButton(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentQrTraySaved.E(FragmentQrTraySaved.this, view);
                }
            });
        }
        QRTrayMenuDialog qRTrayMenuDialog8 = this.o;
        Boolean valueOf2 = qRTrayMenuDialog8 != null ? Boolean.valueOf(qRTrayMenuDialog8.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue() || (qRTrayMenuDialog = this.o) == null) {
            return;
        }
        qRTrayMenuDialog.show();
    }

    public final void F() {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new m(null), 2, null);
        }
    }

    public final void G() {
        if (isAdded()) {
            if (this.q == null) {
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                this.q = new BottomSheetDialogQRTray(requireContext);
            }
            BottomSheetDialogQRTray bottomSheetDialogQRTray = this.q;
            Intrinsics.checkNotNull(bottomSheetDialogQRTray);
            bottomSheetDialogQRTray.hideNote(true);
            BottomSheetDialogQRTray bottomSheetDialogQRTray2 = this.q;
            Intrinsics.checkNotNull(bottomSheetDialogQRTray2);
            bottomSheetDialogQRTray2.setQRCodeData(true, "", "", null);
            BottomSheetDialogQRTray bottomSheetDialogQRTray3 = this.q;
            Intrinsics.checkNotNull(bottomSheetDialogQRTray3);
            bottomSheetDialogQRTray3.setCancelable(false);
            BottomSheetDialogQRTray bottomSheetDialogQRTray4 = this.q;
            Intrinsics.checkNotNull(bottomSheetDialogQRTray4);
            bottomSheetDialogQRTray4.setPositiveButton(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentQrTraySaved.H(FragmentQrTraySaved.this, view);
                }
            });
            BottomSheetDialogQRTray bottomSheetDialogQRTray5 = this.q;
            Intrinsics.checkNotNull(bottomSheetDialogQRTray5);
            bottomSheetDialogQRTray5.setNegativeButton(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.h
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentQrTraySaved.I(FragmentQrTraySaved.this, view);
                }
            });
            BottomSheetDialogQRTray bottomSheetDialogQRTray6 = this.q;
            Intrinsics.checkNotNull(bottomSheetDialogQRTray6);
            if (bottomSheetDialogQRTray6.isShowing() || BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
                return;
            }
            BottomSheetDialogQRTray bottomSheetDialogQRTray7 = this.q;
            Intrinsics.checkNotNull(bottomSheetDialogQRTray7);
            bottomSheetDialogQRTray7.show();
        }
    }

    public final void J() {
        if (isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new n(null), 2, null);
        }
    }

    public final void K() {
        QRTrayViewModel qRTrayViewModel = this.t;
        QRTrayViewModel qRTrayViewModel2 = null;
        if (qRTrayViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
            qRTrayViewModel = null;
        }
        SingleLiveEvent<ArrayList<QRTrayCategoriesRes.QRItem>> categoryMutableList = qRTrayViewModel.getCategoryMutableList();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
        final o oVar = new o();
        categoryMutableList.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.qrtray.fragment.j
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentQrTraySaved.L(Function1.this, obj);
            }
        });
        QRTrayViewModel qRTrayViewModel3 = this.t;
        if (qRTrayViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
        } else {
            qRTrayViewModel2 = qRTrayViewModel3;
        }
        MutableLiveData<List<QRTrayCodesRes.QRTrayCodeData>> qrCodeMutableList = qRTrayViewModel2.getQrCodeMutableList();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        final Function1<List<? extends QRTrayCodesRes.QRTrayCodeData>, Unit> function1 = new Function1<List<? extends QRTrayCodesRes.QRTrayCodeData>, Unit>() { // from class: com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$setObservers$2

            @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$setObservers$2$1", f = "FragmentQrTraySaved.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$setObservers$2$1  reason: invalid class name */
            /* loaded from: classes5.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ List<QRTrayCodesRes.QRTrayCodeData> $it;
                public int label;
                public final /* synthetic */ FragmentQrTraySaved this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public AnonymousClass1(FragmentQrTraySaved fragmentQrTraySaved, List<? extends QRTrayCodesRes.QRTrayCodeData> list, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = fragmentQrTraySaved;
                    this.$it = list;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new AnonymousClass1(this.this$0, this.$it, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.v.clear();
                        FragmentActivity requireActivity = this.this$0.requireActivity();
                        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                        ((ActivityQRTray) requireActivity).setWatchAndAppDataFromServerToApp(this.$it);
                        this.this$0.D = this.$it.size() >= this.this$0.z;
                        ArrayList arrayList = this.this$0.v;
                        FragmentActivity requireActivity2 = this.this$0.requireActivity();
                        Intrinsics.checkNotNull(requireActivity2, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                        arrayList.addAll(CollectionsKt___CollectionsKt.sortedWith(((ActivityQRTray) requireActivity2).getWatchAndServerQRData().getServerAppliedQrCodes(), 
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x005e: INVOKE  
                              (r5v9 'arrayList' java.util.ArrayList)
                              (wrap: java.util.List : 0x005a: INVOKE  (r1v10 java.util.List A[REMOVE]) = 
                              (wrap: java.util.ArrayList<com.coveiot.android.qrtray.model.QRCodeDataApp> : 0x0051: INVOKE  (r1v9 java.util.ArrayList<com.coveiot.android.qrtray.model.QRCodeDataApp> A[REMOVE]) = 
                              (wrap: com.coveiot.android.qrtray.model.WatchAndServerQRData : 0x004d: INVOKE  (r1v8 com.coveiot.android.qrtray.model.WatchAndServerQRData A[REMOVE]) = 
                              (wrap: com.coveiot.android.qrtray.ActivityQRTray : 0x004b: CHECK_CAST (r1v7 com.coveiot.android.qrtray.ActivityQRTray A[REMOVE]) = (com.coveiot.android.qrtray.ActivityQRTray) (r1v6 'requireActivity2' androidx.fragment.app.FragmentActivity))
                             type: VIRTUAL call: com.coveiot.android.qrtray.ActivityQRTray.getWatchAndServerQRData():com.coveiot.android.qrtray.model.WatchAndServerQRData)
                             type: VIRTUAL call: com.coveiot.android.qrtray.model.WatchAndServerQRData.getServerAppliedQrCodes():java.util.ArrayList)
                              (wrap: java.util.Comparator : 0x0057: CONSTRUCTOR  (r2v2 java.util.Comparator A[REMOVE]) =  call: com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$setObservers$2$1$invokeSuspend$$inlined$sortedBy$1.<init>():void type: CONSTRUCTOR)
                             type: STATIC call: kotlin.collections.CollectionsKt___CollectionsKt.sortedWith(java.lang.Iterable, java.util.Comparator):java.util.List)
                             type: VIRTUAL call: java.util.ArrayList.addAll(java.util.Collection):boolean in method: com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$setObservers$2.1.invokeSuspend(java.lang.Object):java.lang.Object, file: classes5.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:296)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:275)
                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:377)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:272)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$setObservers$2$1$invokeSuspend$$inlined$sortedBy$1, state: NOT_LOADED
                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:302)
                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:769)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1097)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1097)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                            	... 23 more
                            */
                        /*
                            this = this;
                            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                            int r0 = r4.label
                            if (r0 != 0) goto L85
                            kotlin.ResultKt.throwOnFailure(r5)
                            com.coveiot.android.qrtray.fragment.FragmentQrTraySaved r5 = r4.this$0
                            java.util.ArrayList r5 = com.coveiot.android.qrtray.fragment.FragmentQrTraySaved.access$getAllQRData$p(r5)
                            r5.clear()
                            com.coveiot.android.qrtray.fragment.FragmentQrTraySaved r5 = r4.this$0
                            androidx.fragment.app.FragmentActivity r5 = r5.requireActivity()
                            java.lang.String r0 = "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray"
                            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r0)
                            com.coveiot.android.qrtray.ActivityQRTray r5 = (com.coveiot.android.qrtray.ActivityQRTray) r5
                            java.util.List<com.coveiot.coveaccess.qrtray.model.QRTrayCodesRes$QRTrayCodeData> r1 = r4.$it
                            r5.setWatchAndAppDataFromServerToApp(r1)
                            com.coveiot.android.qrtray.fragment.FragmentQrTraySaved r5 = r4.this$0
                            java.util.List<com.coveiot.coveaccess.qrtray.model.QRTrayCodesRes$QRTrayCodeData> r1 = r4.$it
                            int r1 = r1.size()
                            com.coveiot.android.qrtray.fragment.FragmentQrTraySaved r2 = r4.this$0
                            int r2 = com.coveiot.android.qrtray.fragment.FragmentQrTraySaved.access$getMaxAppSideAllowedQRs$p(r2)
                            r3 = 0
                            if (r1 < r2) goto L38
                            r1 = 1
                            goto L39
                        L38:
                            r1 = r3
                        L39:
                            com.coveiot.android.qrtray.fragment.FragmentQrTraySaved.access$setMaxedPushAppExceed$p(r5, r1)
                            com.coveiot.android.qrtray.fragment.FragmentQrTraySaved r5 = r4.this$0
                            java.util.ArrayList r5 = com.coveiot.android.qrtray.fragment.FragmentQrTraySaved.access$getAllQRData$p(r5)
                            com.coveiot.android.qrtray.fragment.FragmentQrTraySaved r1 = r4.this$0
                            androidx.fragment.app.FragmentActivity r1 = r1.requireActivity()
                            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r0)
                            com.coveiot.android.qrtray.ActivityQRTray r1 = (com.coveiot.android.qrtray.ActivityQRTray) r1
                            com.coveiot.android.qrtray.model.WatchAndServerQRData r1 = r1.getWatchAndServerQRData()
                            java.util.ArrayList r1 = r1.getServerAppliedQrCodes()
                            com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$setObservers$2$1$invokeSuspend$$inlined$sortedBy$1 r2 = new com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$setObservers$2$1$invokeSuspend$$inlined$sortedBy$1
                            r2.<init>()
                            java.util.List r1 = kotlin.collections.CollectionsKt___CollectionsKt.sortedWith(r1, r2)
                            r5.addAll(r1)
                            com.coveiot.android.qrtray.fragment.FragmentQrTraySaved r5 = r4.this$0
                            java.util.ArrayList r5 = com.coveiot.android.qrtray.fragment.FragmentQrTraySaved.access$getAllQRData$p(r5)
                            com.coveiot.android.qrtray.fragment.FragmentQrTraySaved r1 = r4.this$0
                            androidx.fragment.app.FragmentActivity r1 = r1.requireActivity()
                            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r0)
                            com.coveiot.android.qrtray.ActivityQRTray r1 = (com.coveiot.android.qrtray.ActivityQRTray) r1
                            com.coveiot.android.qrtray.model.WatchAndServerQRData r0 = r1.getWatchAndServerQRData()
                            java.util.ArrayList r0 = r0.getServerUnAppliedQrCodes()
                            r5.addAll(r0)
                            com.coveiot.android.qrtray.fragment.FragmentQrTraySaved r5 = r4.this$0
                            com.coveiot.android.qrtray.fragment.FragmentQrTraySaved.access$filterAsPerCategory(r5, r3)
                            kotlin.Unit r5 = kotlin.Unit.INSTANCE
                            return r5
                        L85:
                            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                            r5.<init>(r0)
                            throw r5
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.qrtray.fragment.FragmentQrTraySaved$setObservers$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<? extends QRTrayCodesRes.QRTrayCodeData> list) {
                    invoke2(list);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(List<? extends QRTrayCodesRes.QRTrayCodeData> list) {
                    if (FragmentQrTraySaved.this.isAdded()) {
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentQrTraySaved.this), Dispatchers.getMain(), null, new AnonymousClass1(FragmentQrTraySaved.this, list, null), 2, null);
                    }
                }
            };
            qrCodeMutableList.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.android.qrtray.fragment.k
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FragmentQrTraySaved.M(Function1.this, obj);
                }
            });
        }

        public final void N(String str, String str2, Drawable drawable, boolean z) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new p(drawable, str, str2, z, null), 2, null);
        }

        public final void P(boolean z) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new q(z, this, null), 2, null);
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
            QRTrayCategoryAdapter qRTrayCategoryAdapter = this.s;
            if (qRTrayCategoryAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("qrTrayCategoryAdapter");
                qRTrayCategoryAdapter = null;
            }
            qRTrayCategoryAdapter.setSelectedCategory(i2);
            if (Intrinsics.areEqual(this.x, categoryData.getCategoryId())) {
                return;
            }
            String categoryId = categoryData.getCategoryId();
            Intrinsics.checkNotNullExpressionValue(categoryId, "categoryData.categoryId");
            this.x = categoryId;
            u(false);
        }

        @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
        public void deleteQRCodeMetaDataCheck(boolean z) {
            if (isAdded()) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new a(z, this, null), 2, null);
            }
        }

        public final boolean getInternetChecked() {
            return this.I;
        }

        public final boolean getPushToWatchInitiated() {
            return this.J;
        }

        @Override // com.coveiot.android.qrtray.adapter.AddedQRTrayAdapter.QrMenuClickListener
        public void menuClick(@NotNull QRCodeDataApp qrData, boolean z) {
            Intrinsics.checkNotNullParameter(qrData, "qrData");
            if (isAdded()) {
                this.u = qrData;
                String serverId = qrData.getServerId();
                Intrinsics.checkNotNull(serverId);
                this.G = serverId;
                if (z) {
                    x(true);
                } else {
                    B();
                }
            }
        }

        public final void onBackPressed() {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
        }

        @Override // androidx.fragment.app.Fragment
        @NotNull
        public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
            Intrinsics.checkNotNullParameter(inflater, "inflater");
            FragmentAddedQrTrayBinding inflate = FragmentAddedQrTrayBinding.inflate(inflater, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
            this.m = inflate;
            View root = v().getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "binding.root");
            return root;
        }

        @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
        public void onDeleteImageCheck(boolean z) {
            if (isAdded()) {
                QRTrayViewModel qRTrayViewModel = null;
                if (!z) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new d(null), 2, null);
                } else if (this.C) {
                    FragmentActivity requireActivity = requireActivity();
                    Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                    QRCodeDataApp qRCodeDataApp = this.F;
                    Intrinsics.checkNotNull(qRCodeDataApp);
                    ((ActivityQRTray) requireActivity).removeQRCodeListFromWatchCachedData(qRCodeDataApp.getImageId());
                    QRCodeDataApp qRCodeDataApp2 = this.u;
                    Intrinsics.checkNotNull(qRCodeDataApp2);
                    QRCodeDataApp qRCodeDataApp3 = this.F;
                    Intrinsics.checkNotNull(qRCodeDataApp3);
                    qRCodeDataApp2.setImageId(qRCodeDataApp3.getImageId());
                    QRCodeData qRCodeData = this.A;
                    QRCodeDataApp qRCodeDataApp4 = this.u;
                    Intrinsics.checkNotNull(qRCodeDataApp4);
                    qRCodeData.setImageId(qRCodeDataApp4.getImageId());
                    QRCodeData qRCodeData2 = this.A;
                    QRCodeDataApp qRCodeDataApp5 = this.u;
                    Intrinsics.checkNotNull(qRCodeDataApp5);
                    qRCodeData2.setImageTitle(qRCodeDataApp5.getImageTitle());
                    QRCodeData qRCodeData3 = this.A;
                    QRCodeDataApp qRCodeDataApp6 = this.u;
                    Intrinsics.checkNotNull(qRCodeDataApp6);
                    qRCodeData3.setImageTag(qRCodeDataApp6.getImageTag());
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new c(null), 2, null);
                } else {
                    QRTrayViewModel qRTrayViewModel2 = this.t;
                    if (qRTrayViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                    } else {
                        qRTrayViewModel = qRTrayViewModel2;
                    }
                    FragmentActivity requireActivity2 = requireActivity();
                    Intrinsics.checkNotNull(requireActivity2, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                    QRCodeDataApp qRCodeDataApp7 = this.u;
                    Intrinsics.checkNotNull(qRCodeDataApp7);
                    qRTrayViewModel.deleteQRCodeFromServer(CollectionsKt__CollectionsKt.arrayListOf(((ActivityQRTray) requireActivity2).getServerApiQRCodeDataFromAppData(qRCodeDataApp7)));
                }
            }
        }

        @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
        public void onDeleteQRCheckFromServer(boolean z) {
            if (isAdded()) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new e(z, this, null), 2, null);
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
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new f(z2, this, z, null), 2, null);
            }
        }

        @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
        public void onFailure(@Nullable String str) {
            if (isAdded()) {
                this.C = false;
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
                this.C = false;
                QRTrayViewModel qRTrayViewModel = this.t;
                if (qRTrayViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                    qRTrayViewModel = null;
                }
                qRTrayViewModel.getQRCodesFromServer();
            }
        }

        @Override // androidx.fragment.app.Fragment
        public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
            Intrinsics.checkNotNullParameter(view, "view");
            super.onViewCreated(view, bundle);
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
            ((ActivityQRTray) requireActivity).logEvents();
            ViewModel viewModel = new ViewModelProvider(this).get(QRTrayViewModel.class);
            Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider(this@F…rayViewModel::class.java]");
            QRTrayViewModel qRTrayViewModel = (QRTrayViewModel) viewModel;
            this.t = qRTrayViewModel;
            AddedQRTrayAdapter addedQRTrayAdapter = null;
            if (qRTrayViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("qrTrayViewModel");
                qRTrayViewModel = null;
            }
            qRTrayViewModel.setListener(this);
            K();
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new i(null), 2, null);
            FragmentAddedQrTrayBinding v = v();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            this.s = new QRTrayCategoryAdapter(requireContext, false, this);
            v.rvQRTrayCategories.setLayoutManager(new LinearLayoutManager(requireActivity(), 0, false));
            RecyclerView recyclerView = v.rvQRTrayCategories;
            QRTrayCategoryAdapter qRTrayCategoryAdapter = this.s;
            if (qRTrayCategoryAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("qrTrayCategoryAdapter");
                qRTrayCategoryAdapter = null;
            }
            recyclerView.setAdapter(qRTrayCategoryAdapter);
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            this.n = new AddedQRTrayAdapter(requireContext2, this);
            v.rvQrTray.setLayoutManager(new LinearLayoutManager(requireActivity(), 1, false));
            RecyclerView recyclerView2 = v.rvQrTray;
            AddedQRTrayAdapter addedQRTrayAdapter2 = this.n;
            if (addedQRTrayAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addedQRTrayAdapter");
            } else {
                addedQRTrayAdapter = addedQRTrayAdapter2;
            }
            recyclerView2.setAdapter(addedQRTrayAdapter);
            v.btnAddQR.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    FragmentQrTraySaved.z(FragmentQrTraySaved.this, view2);
                }
            });
            ((TextView) v().toolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.qr_tray));
            ((TextView) v().toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    FragmentQrTraySaved.A(FragmentQrTraySaved.this, view2);
                }
            });
        }

        public final void setInternetChecked(boolean z) {
            this.I = z;
        }

        public final void setPushToWatchInitiated(boolean z) {
            this.J = z;
        }

        public final void showSuccessNErrorToast(@NotNull String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            toast(requireContext, message);
        }

        public final void t() {
            if (BleApiManager.getInstance(requireContext()).getBleApi() != null && BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                if (this.D) {
                    G();
                    HashMap<String, Object> hashMap = new HashMap<>();
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    hashMap.putAll(companion.getDeviceId(requireContext));
                    Context requireContext2 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    hashMap.putAll(companion.getWatchDetails(requireContext2));
                    hashMap.put(CleverTapConstants.CustomEventProperties.FAILURE_REASON.getValue(), CleverTapConstants.CustomEventValues.QR_LIMIT_EXCEEDED.getValue());
                    companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_UPLOAD_FAILED.getValue(), hashMap);
                    return;
                }
                y();
                return;
            }
            String string = getString(R.string.connect_watch);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.connect_watch)");
            String string2 = getString(R.string.please_connect_your_watch_in_order_to_apply_the_qr_code);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.pleas…der_to_apply_the_qr_code)");
            Drawable drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_red_circular_not_connected);
            Intrinsics.checkNotNull(drawable);
            O(this, string, string2, drawable, false, 8, null);
        }

        public final void u(boolean z) {
            this.w.clear();
            if (z) {
                this.w.addAll(this.v);
                this.x = "ALL";
            } else if (Intrinsics.areEqual(this.x, "ALL")) {
                this.w.addAll(this.v);
            } else {
                int size = this.v.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (Intrinsics.areEqual(this.v.get(i2).getImageTag(), this.x)) {
                        this.w.add(this.v.get(i2));
                    }
                }
            }
            AddedQRTrayAdapter addedQRTrayAdapter = this.n;
            if (addedQRTrayAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addedQRTrayAdapter");
                addedQRTrayAdapter = null;
            }
            addedQRTrayAdapter.setQrCodeList(this.w);
            TextView textView = v().tvNoDataFound;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvNoDataFound");
            visibleIf(textView, this.w.isEmpty());
            P(false);
            dismissProgress();
            if (this.v.isEmpty()) {
                requireActivity().finish();
            }
        }

        @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
        public void uploadingImageCheck(boolean z) {
            if (isAdded()) {
                if (!z) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new s(null), 2, null);
                    return;
                }
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                QRCodeDataApp qRCodeDataApp = this.u;
                Intrinsics.checkNotNull(qRCodeDataApp);
                int imageId = qRCodeDataApp.getImageId();
                QRCodeDataApp qRCodeDataApp2 = this.u;
                Intrinsics.checkNotNull(qRCodeDataApp2);
                String imageTitle = qRCodeDataApp2.getImageTitle();
                QRCodeDataApp qRCodeDataApp3 = this.u;
                Intrinsics.checkNotNull(qRCodeDataApp3);
                String imageTag = qRCodeDataApp3.getImageTag();
                QRCodeDataApp qRCodeDataApp4 = this.u;
                Intrinsics.checkNotNull(qRCodeDataApp4);
                String lastAppliedDate = qRCodeDataApp4.getLastAppliedDate();
                QRCodeDataApp qRCodeDataApp5 = this.u;
                Intrinsics.checkNotNull(qRCodeDataApp5);
                ((ActivityQRTray) requireActivity).addOrUpdateWatchAppliedQrCode(new QRCodeDataApp(imageId, imageTitle, imageTag, null, lastAppliedDate, qRCodeDataApp5.getServerId(), false, null, 192, null));
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new r(null), 2, null);
            }
        }

        @Override // com.coveiot.android.qrtray.viewmodel.QRTrayViewModel.QRTrayViewModelContract
        public void uploadingQRCodeMetaDataCheck(boolean z) {
            if (isAdded()) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new t(z, this, null), 2, null);
            }
        }

        public final FragmentAddedQrTrayBinding v() {
            FragmentAddedQrTrayBinding fragmentAddedQrTrayBinding = this.m;
            if (fragmentAddedQrTrayBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("_binding");
                return null;
            }
            return fragmentAddedQrTrayBinding;
        }

        public final void w() {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new b(null), 2, null);
        }

        public final void x(boolean z) {
            FragmentTransaction beginTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            int i2 = R.id.qrContainer;
            FragmentQrTraySavingNEditing.Companion companion = FragmentQrTraySavingNEditing.Companion;
            int i3 = this.y;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
            QRCodeDataApp qRCodeDataApp = this.u;
            Intrinsics.checkNotNull(qRCodeDataApp);
            beginTransaction.replace(i2, companion.newInstance(i3, null, true, ((ActivityQRTray) requireActivity).getServerApiQRCodeDataFromAppData(qRCodeDataApp), z)).addToBackStack(companion.toString()).commit();
        }

        public final void y() {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.qrContainer, FragmentQrTrayUpload.Companion.newInstance(true)).addToBackStack(new FragmentQrTrayUpload().toString()).commit();
        }
    }
