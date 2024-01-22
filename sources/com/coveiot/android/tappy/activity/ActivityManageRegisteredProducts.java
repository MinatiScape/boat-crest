package com.coveiot.android.tappy.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.adapter.TabManageAdapter;
import com.coveiot.android.tappy.databinding.ActivityManageRegisterProductsBinding;
import com.coveiot.android.tappy.fragment.CardManagementFragment;
import com.coveiot.android.tappy.fragment.StrapManagementFragment;
import com.coveiot.android.tappy.model.DeviceInfo;
import com.coveiot.android.tappy.model.PaymentInstrumentData;
import com.coveiot.android.tappy.model.PaymentInstrumentTokenData;
import com.coveiot.android.tappy.model.ProductDetails;
import com.coveiot.android.tappy.model.RegCardBeanInfo;
import com.coveiot.android.tappy.model.RegStrapBeanInfo;
import com.coveiot.android.tappy.model.RegisteredProductInfo;
import com.coveiot.android.tappy.model.UserDetails;
import com.coveiot.android.tappy.utils.Constants;
import com.coveiot.android.tappy.utils.Utils;
import com.coveiot.android.tappy.viewmodel.NfcStrapViewModel;
import com.coveiot.android.tappy.viewmodel.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class ActivityManageRegisteredProducts extends BaseActivity {
    public long p;
    public long q;
    public NfcStrapViewModel r;
    public ActivityManageRegisterProductsBinding t;
    public TabManageAdapter tabLayoutManageAdapter;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public boolean s = true;
    @NotNull
    public ArrayList<RegStrapBeanInfo> u = new ArrayList<>();

    /* loaded from: classes7.dex */
    public static final class a extends Lambda implements Function1<List<? extends DeviceInfo>, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityManageRegisteredProducts$onCreate$1$1", f = "ActivityManageRegisteredProducts.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.tappy.activity.ActivityManageRegisteredProducts$a$a  reason: collision with other inner class name */
        /* loaded from: classes7.dex */
        public static final class C0305a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ List<DeviceInfo> $it;
            public int label;
            public final /* synthetic */ ActivityManageRegisteredProducts this$0;

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityManageRegisteredProducts$onCreate$1$1$1", f = "ActivityManageRegisteredProducts.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.ActivityManageRegisteredProducts$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0306a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ DeviceInfo $deviceInfo;
                public int label;
                public final /* synthetic */ ActivityManageRegisteredProducts this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0306a(ActivityManageRegisteredProducts activityManageRegisteredProducts, DeviceInfo deviceInfo, Continuation<? super C0306a> continuation) {
                    super(2, continuation);
                    this.this$0 = activityManageRegisteredProducts;
                    this.$deviceInfo = deviceInfo;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0306a(this.this$0, this.$deviceInfo, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0306a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        NfcStrapViewModel nfcStrapViewModel = this.this$0.r;
                        if (nfcStrapViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            nfcStrapViewModel = null;
                        }
                        nfcStrapViewModel.registerNewDevice(this.this$0.getGlobalUserId(), this.$deviceInfo);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityManageRegisteredProducts$onCreate$1$1$2", f = "ActivityManageRegisteredProducts.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.ActivityManageRegisteredProducts$a$a$b */
            /* loaded from: classes7.dex */
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ActivityManageRegisteredProducts this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(ActivityManageRegisteredProducts activityManageRegisteredProducts, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.this$0 = activityManageRegisteredProducts;
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
                        NfcStrapViewModel nfcStrapViewModel = this.this$0.r;
                        if (nfcStrapViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            nfcStrapViewModel = null;
                        }
                        nfcStrapViewModel.getAllRegisteredProductByUserId(this.this$0.getGlobalUserId());
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0305a(ActivityManageRegisteredProducts activityManageRegisteredProducts, List<DeviceInfo> list, Continuation<? super C0305a> continuation) {
                super(2, continuation);
                this.this$0 = activityManageRegisteredProducts;
                this.$it = list;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0305a(this.this$0, this.$it, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0305a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (!this.this$0.isFinishing()) {
                        DeviceInfo deviceInfo = Utils.Companion.getDeviceInfo(this.$it, this.this$0);
                        if (deviceInfo.getDeviceId() != null) {
                            Long deviceId = deviceInfo.getDeviceId();
                            Intrinsics.checkNotNull(deviceId);
                            if (deviceId.longValue() > 0) {
                                ActivityManageRegisteredProducts activityManageRegisteredProducts = this.this$0;
                                Long deviceId2 = deviceInfo.getDeviceId();
                                Intrinsics.checkNotNull(deviceId2);
                                activityManageRegisteredProducts.q = deviceId2.longValue();
                                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new b(this.this$0, null), 2, null);
                            }
                        }
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new C0306a(this.this$0, deviceInfo, null), 2, null);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends DeviceInfo> list) {
            invoke2((List<DeviceInfo>) list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable List<DeviceInfo> list) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityManageRegisteredProducts.this), Dispatchers.getMain(), null, new C0305a(ActivityManageRegisteredProducts.this, list, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class b extends Lambda implements Function1<DeviceInfo, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityManageRegisteredProducts$onCreate$2$1", f = "ActivityManageRegisteredProducts.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ DeviceInfo $it;
            public int label;
            public final /* synthetic */ ActivityManageRegisteredProducts this$0;

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityManageRegisteredProducts$onCreate$2$1$1", f = "ActivityManageRegisteredProducts.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.ActivityManageRegisteredProducts$b$a$a  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0307a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ActivityManageRegisteredProducts this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0307a(ActivityManageRegisteredProducts activityManageRegisteredProducts, Continuation<? super C0307a> continuation) {
                    super(2, continuation);
                    this.this$0 = activityManageRegisteredProducts;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0307a(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0307a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        NfcStrapViewModel nfcStrapViewModel = this.this$0.r;
                        if (nfcStrapViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            nfcStrapViewModel = null;
                        }
                        nfcStrapViewModel.getAllRegisteredProductByUserId(this.this$0.getGlobalUserId());
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityManageRegisteredProducts activityManageRegisteredProducts, DeviceInfo deviceInfo, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = activityManageRegisteredProducts;
                this.$it = deviceInfo;
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
                    if (!this.this$0.isFinishing()) {
                        DeviceInfo deviceInfo = this.$it;
                        if (deviceInfo == null) {
                            this.this$0.dismissProgress();
                            ActivityManageRegisteredProducts activityManageRegisteredProducts = this.this$0;
                            String string = activityManageRegisteredProducts.getString(R.string.some_error_occurred_try_again);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                            BaseActivity.showCommonMessageDialog$default(activityManageRegisteredProducts, string, false, 2, null);
                        } else {
                            ActivityManageRegisteredProducts activityManageRegisteredProducts2 = this.this$0;
                            Long deviceId = deviceInfo.getDeviceId();
                            Intrinsics.checkNotNull(deviceId);
                            activityManageRegisteredProducts2.q = deviceId.longValue();
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new C0307a(this.this$0, null), 2, null);
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DeviceInfo deviceInfo) {
            invoke2(deviceInfo);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable DeviceInfo deviceInfo) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityManageRegisteredProducts.this), Dispatchers.getMain(), null, new a(ActivityManageRegisteredProducts.this, deviceInfo, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class c extends Lambda implements Function1<List<? extends RegisteredProductInfo>, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityManageRegisteredProducts$onCreate$3$1", f = "ActivityManageRegisteredProducts.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ List<RegisteredProductInfo> $it;
            public int label;
            public final /* synthetic */ ActivityManageRegisteredProducts this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityManageRegisteredProducts activityManageRegisteredProducts, List<RegisteredProductInfo> list, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = activityManageRegisteredProducts;
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
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.dismissProgress();
                    this.this$0.u.clear();
                    List<RegisteredProductInfo> list = this.$it;
                    if (list == null || list.isEmpty()) {
                        NfcStrapViewModel nfcStrapViewModel = this.this$0.r;
                        if (nfcStrapViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            nfcStrapViewModel = null;
                        }
                        nfcStrapViewModel.updateRegisteredStrapInfoList(null);
                    } else {
                        for (RegisteredProductInfo registeredProductInfo : this.$it) {
                            RegStrapBeanInfo regStrapBeanInfo = new RegStrapBeanInfo(null, null, null, null, null, null, null, null, false, false, null, 2047, null);
                            regStrapBeanInfo.setDeviceId(Boxing.boxLong(this.this$0.q));
                            ProductDetails product = registeredProductInfo.getProduct();
                            regStrapBeanInfo.setProductName(product != null ? product.getName() : null);
                            ProductDetails product2 = registeredProductInfo.getProduct();
                            regStrapBeanInfo.setSku(product2 != null ? product2.getSKU() : null);
                            regStrapBeanInfo.setEndUserID(registeredProductInfo.getEndUserID());
                            regStrapBeanInfo.setProductSerialNumber(registeredProductInfo.getProductSerialNumber());
                            regStrapBeanInfo.setEndUserProductRegistrationID(registeredProductInfo.getEndUserProductRegistrationID());
                            ProductDetails product3 = registeredProductInfo.getProduct();
                            regStrapBeanInfo.setImageUrl(product3 != null ? product3.getImageUrl() : null);
                            regStrapBeanInfo.setFriendlyName(registeredProductInfo.getFriendlyName());
                            regStrapBeanInfo.setSelected(false);
                            List<PaymentInstrumentTokenData> paymentInstrumentTokens = registeredProductInfo.getPaymentInstrumentTokens();
                            regStrapBeanInfo.setCardAdded(!(paymentInstrumentTokens == null || paymentInstrumentTokens.isEmpty()));
                            List<PaymentInstrumentTokenData> paymentInstrumentTokens2 = registeredProductInfo.getPaymentInstrumentTokens();
                            if (!(paymentInstrumentTokens2 == null || paymentInstrumentTokens2.isEmpty())) {
                                List<PaymentInstrumentTokenData> paymentInstrumentTokens3 = registeredProductInfo.getPaymentInstrumentTokens();
                                Intrinsics.checkNotNull(paymentInstrumentTokens3);
                                for (PaymentInstrumentTokenData paymentInstrumentTokenData : paymentInstrumentTokens3) {
                                    if (paymentInstrumentTokenData.getPaymentInstrument() != null) {
                                        RegCardBeanInfo regCardBeanInfo = new RegCardBeanInfo(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, null, false, null, null, 268435455, null);
                                        ProductDetails product4 = registeredProductInfo.getProduct();
                                        regCardBeanInfo.setProductName(product4 != null ? product4.getName() : null);
                                        regCardBeanInfo.setEndUserID(registeredProductInfo.getEndUserID());
                                        regCardBeanInfo.setProductSerialNumber(registeredProductInfo.getProductSerialNumber());
                                        regCardBeanInfo.setEndUserProductRegistrationID(registeredProductInfo.getEndUserProductRegistrationID());
                                        regCardBeanInfo.setFriendlyName(registeredProductInfo.getFriendlyName());
                                        regCardBeanInfo.setStatus(paymentInstrumentTokenData.getStatus());
                                        regCardBeanInfo.setProvisioningStatus(paymentInstrumentTokenData.getProvisioningStatus());
                                        regCardBeanInfo.setPaymentInstrumentTokenId(paymentInstrumentTokenData.getPaymentInstrumentTokenID());
                                        PaymentInstrumentData paymentInstrument = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument);
                                        regCardBeanInfo.setPaymentInstrumentId(paymentInstrument.getPaymentInstrumentID());
                                        PaymentInstrumentData paymentInstrument2 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument2);
                                        regCardBeanInfo.setLast4(paymentInstrument2.getLast4());
                                        PaymentInstrumentData paymentInstrument3 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument3);
                                        regCardBeanInfo.setPaymentNetworkId(paymentInstrument3.getPaymentNetworkID());
                                        PaymentInstrumentData paymentInstrument4 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument4);
                                        regCardBeanInfo.setPaymentNetworkName(paymentInstrument4.getPaymentNetworkName());
                                        PaymentInstrumentData paymentInstrument5 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument5);
                                        regCardBeanInfo.setCardArtImageUrl(paymentInstrument5.getCardArtImageUrl());
                                        PaymentInstrumentData paymentInstrument6 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument6);
                                        regCardBeanInfo.setCardSymbolImageUrl(paymentInstrument6.getCardSymbolImageUrl());
                                        PaymentInstrumentData paymentInstrument7 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument7);
                                        regCardBeanInfo.setBackgroundColor(paymentInstrument7.getBackgroundColor());
                                        PaymentInstrumentData paymentInstrument8 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument8);
                                        regCardBeanInfo.setLabelColor(paymentInstrument8.getLabelColor());
                                        PaymentInstrumentData paymentInstrument9 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument9);
                                        regCardBeanInfo.setContactName(paymentInstrument9.getContactName());
                                        PaymentInstrumentData paymentInstrument10 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument10);
                                        regCardBeanInfo.setContactNumber(paymentInstrument10.getContactNumber());
                                        PaymentInstrumentData paymentInstrument11 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument11);
                                        regCardBeanInfo.setContactEmail(paymentInstrument11.getContactEmail());
                                        PaymentInstrumentData paymentInstrument12 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument12);
                                        regCardBeanInfo.setContactWebsite(paymentInstrument12.getContactWebSite());
                                        PaymentInstrumentData paymentInstrument13 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument13);
                                        regCardBeanInfo.setTermsAndConditionId(paymentInstrument13.getTermsAndConditionsID());
                                        PaymentInstrumentData paymentInstrument14 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument14);
                                        regCardBeanInfo.setTermsAndConditionsFileUrl(paymentInstrument14.getTermsAndConditionsFileUrl());
                                        PaymentInstrumentData paymentInstrument15 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument15);
                                        regCardBeanInfo.setPIFinalized(Intrinsics.areEqual(paymentInstrument15.isPIFinalized(), Boxing.boxBoolean(true)));
                                        PaymentInstrumentData paymentInstrument16 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument16);
                                        regCardBeanInfo.setDeleted(Intrinsics.areEqual(paymentInstrument16.isDeleted(), Boxing.boxBoolean(true)));
                                        PaymentInstrumentData paymentInstrument17 = paymentInstrumentTokenData.getPaymentInstrument();
                                        Intrinsics.checkNotNull(paymentInstrument17);
                                        regCardBeanInfo.setIssuerType(paymentInstrument17.getIssuerType());
                                        ProductDetails product5 = registeredProductInfo.getProduct();
                                        regCardBeanInfo.setProductImageUrl(product5 != null ? product5.getImageUrl() : null);
                                        regCardBeanInfo.setSelected(false);
                                        regCardBeanInfo.setExpiryDate(paymentInstrumentTokenData.getExpiryDate());
                                        regStrapBeanInfo.setRegCardBeanInfo(regCardBeanInfo);
                                    }
                                }
                            }
                            this.this$0.u.add(regStrapBeanInfo);
                            NfcStrapViewModel nfcStrapViewModel2 = this.this$0.r;
                            if (nfcStrapViewModel2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                nfcStrapViewModel2 = null;
                            }
                            nfcStrapViewModel2.updateRegisteredStrapInfoList(this.this$0.u);
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends RegisteredProductInfo> list) {
            invoke2((List<RegisteredProductInfo>) list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable List<RegisteredProductInfo> list) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityManageRegisteredProducts.this), Dispatchers.getMain(), null, new a(ActivityManageRegisteredProducts.this, list, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class d extends Lambda implements Function1<UserDetails, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityManageRegisteredProducts$onCreate$4$1", f = "ActivityManageRegisteredProducts.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ UserDetails $it;
            public int label;
            public final /* synthetic */ ActivityManageRegisteredProducts this$0;

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityManageRegisteredProducts$onCreate$4$1$1", f = "ActivityManageRegisteredProducts.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.ActivityManageRegisteredProducts$d$a$a  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0308a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ActivityManageRegisteredProducts this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0308a(ActivityManageRegisteredProducts activityManageRegisteredProducts, Continuation<? super C0308a> continuation) {
                    super(2, continuation);
                    this.this$0 = activityManageRegisteredProducts;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0308a(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0308a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        NfcStrapViewModel nfcStrapViewModel = this.this$0.r;
                        if (nfcStrapViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            nfcStrapViewModel = null;
                        }
                        nfcStrapViewModel.getDeviceInfoByUserId(this.this$0.getGlobalUserId());
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityManageRegisteredProducts activityManageRegisteredProducts, UserDetails userDetails, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = activityManageRegisteredProducts;
                this.$it = userDetails;
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
                    if (!this.this$0.isFinishing()) {
                        UserDetails userDetails = this.$it;
                        if (userDetails != null && userDetails.getGlobalUserID() > 0) {
                            this.this$0.setGlobalUserId(this.$it.getGlobalUserID());
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new C0308a(this.this$0, null), 2, null);
                        } else {
                            this.this$0.dismissProgress();
                        }
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
        public /* bridge */ /* synthetic */ Unit invoke(UserDetails userDetails) {
            invoke2(userDetails);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable UserDetails userDetails) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityManageRegisteredProducts.this), Dispatchers.getMain(), null, new a(ActivityManageRegisteredProducts.this, userDetails, null), 2, null);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.ActivityManageRegisteredProducts$refresh$1", f = "ActivityManageRegisteredProducts.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
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
                NfcStrapViewModel nfcStrapViewModel = ActivityManageRegisteredProducts.this.r;
                if (nfcStrapViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    nfcStrapViewModel = null;
                }
                nfcStrapViewModel.getUserInfo();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void B(ActivityManageRegisteredProducts this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void v(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void w(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void x(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void y(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void A() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.boat_pay_settings));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityManageRegisteredProducts.B(ActivityManageRegisteredProducts.this, view);
            }
        });
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

    public final long getGlobalUserId() {
        return this.p;
    }

    @NotNull
    public final TabManageAdapter getTabLayoutManageAdapter() {
        TabManageAdapter tabManageAdapter = this.tabLayoutManageAdapter;
        if (tabManageAdapter != null) {
            return tabManageAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("tabLayoutManageAdapter");
        return null;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityManageRegisterProductsBinding inflate = ActivityManageRegisterProductsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.t = inflate;
        ActivityManageRegisterProductsBinding activityManageRegisterProductsBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        if (getIntent().hasExtra(Constants.END_USER_GLOBAL_ID)) {
            this.p = getIntent().getLongExtra(Constants.END_USER_GLOBAL_ID, 0L);
        }
        this.r = (NfcStrapViewModel) new ViewModelProvider(this, new ViewModelFactory(this)).get(NfcStrapViewModel.class);
        z();
        NfcStrapViewModel nfcStrapViewModel = this.r;
        if (nfcStrapViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            nfcStrapViewModel = null;
        }
        MutableLiveData<List<DeviceInfo>> getAllRegisteredDevicesLiveData = nfcStrapViewModel.getGetAllRegisteredDevicesLiveData();
        final a aVar = new a();
        getAllRegisteredDevicesLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.e
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityManageRegisteredProducts.v(Function1.this, obj);
            }
        });
        NfcStrapViewModel nfcStrapViewModel2 = this.r;
        if (nfcStrapViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            nfcStrapViewModel2 = null;
        }
        MutableLiveData<DeviceInfo> registerNewDeviceLiveData = nfcStrapViewModel2.getRegisterNewDeviceLiveData();
        final b bVar = new b();
        registerNewDeviceLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.d
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityManageRegisteredProducts.w(Function1.this, obj);
            }
        });
        NfcStrapViewModel nfcStrapViewModel3 = this.r;
        if (nfcStrapViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            nfcStrapViewModel3 = null;
        }
        MutableLiveData<List<RegisteredProductInfo>> registerProductsByUserIdLiveData = nfcStrapViewModel3.getRegisterProductsByUserIdLiveData();
        final c cVar = new c();
        registerProductsByUserIdLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.c
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityManageRegisteredProducts.x(Function1.this, obj);
            }
        });
        NfcStrapViewModel nfcStrapViewModel4 = this.r;
        if (nfcStrapViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            nfcStrapViewModel4 = null;
        }
        MutableLiveData<UserDetails> userDetailLiveData = nfcStrapViewModel4.getUserDetailLiveData();
        final d dVar = new d();
        userDetailLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.b
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityManageRegisteredProducts.y(Function1.this, obj);
            }
        });
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        setTabLayoutManageAdapter(new TabManageAdapter(supportFragmentManager));
        StrapManagementFragment newInstance = StrapManagementFragment.Companion.newInstance(this.p);
        CardManagementFragment newInstance2 = CardManagementFragment.Companion.newInstance(this.p);
        TabManageAdapter tabLayoutManageAdapter = getTabLayoutManageAdapter();
        String string = getString(R.string.strap_management);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.strap_management)");
        tabLayoutManageAdapter.addFragment(newInstance, string);
        TabManageAdapter tabLayoutManageAdapter2 = getTabLayoutManageAdapter();
        String string2 = getString(R.string.card_management);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.card_management)");
        tabLayoutManageAdapter2.addFragment(newInstance2, string2);
        ActivityManageRegisterProductsBinding activityManageRegisterProductsBinding2 = this.t;
        if (activityManageRegisterProductsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityManageRegisterProductsBinding2 = null;
        }
        activityManageRegisterProductsBinding2.viewPager.setAdapter(getTabLayoutManageAdapter());
        ActivityManageRegisterProductsBinding activityManageRegisterProductsBinding3 = this.t;
        if (activityManageRegisterProductsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityManageRegisterProductsBinding3 = null;
        }
        TabLayout tabLayout = activityManageRegisterProductsBinding3.tlStrapManage;
        ActivityManageRegisterProductsBinding activityManageRegisterProductsBinding4 = this.t;
        if (activityManageRegisterProductsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityManageRegisterProductsBinding = activityManageRegisterProductsBinding4;
        }
        tabLayout.setupWithViewPager(activityManageRegisterProductsBinding.viewPager);
        if (getIntent().hasExtra(Constants.END_USER_GLOBAL_ID)) {
            this.p = getIntent().getLongExtra(Constants.END_USER_GLOBAL_ID, 0L);
        }
        if (getIntent().hasExtra("deviceId")) {
            this.q = getIntent().getLongExtra("deviceId", 0L);
        }
        A();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (AppUtils.isNetConnected(this)) {
            if (this.s) {
                refresh();
                return;
            }
            return;
        }
        Toast.makeText(this, getString(R.string.no_internet_connection), 1).show();
    }

    public final void refresh() {
        LoadingDialog progressDialog = getProgressDialog();
        boolean z = false;
        if (progressDialog != null && !progressDialog.isShowing()) {
            z = true;
        }
        if (z) {
            showProgress();
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new e(null), 2, null);
        }
    }

    public final void setGlobalUserId(long j) {
        this.p = j;
    }

    public final void setTabLayoutManageAdapter(@NotNull TabManageAdapter tabManageAdapter) {
        Intrinsics.checkNotNullParameter(tabManageAdapter, "<set-?>");
        this.tabLayoutManageAdapter = tabManageAdapter;
    }

    public final void z() {
        ActivityManageRegisterProductsBinding activityManageRegisterProductsBinding = this.t;
        if (activityManageRegisterProductsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityManageRegisterProductsBinding = null;
        }
        activityManageRegisterProductsBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.coveiot.android.tappy.activity.ActivityManageRegisteredProducts$setupTabLayout$1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                if (AppUtils.isNetConnected(ActivityManageRegisteredProducts.this)) {
                    ActivityManageRegisteredProducts.this.refresh();
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                if (AppUtils.isNetConnected(ActivityManageRegisteredProducts.this)) {
                    if (i == 0) {
                        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_SETTINGS_STRAP_VIEWED.getValue(), null);
                    } else {
                        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_SETTINGS_CARD_VIEWED.getValue(), null);
                    }
                    ActivityManageRegisteredProducts.this.refresh();
                }
            }
        });
    }
}
