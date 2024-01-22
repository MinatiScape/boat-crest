package com.coveiot.android.tappy.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.tappy.model.ProductDetails;
import com.coveiot.android.tappy.model.RegisterProductResponse;
import com.coveiot.android.tappy.model.RegisteredProductInfo;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.tappy.TappyApiManager;
import com.coveiot.coveaccess.tappy.model.SProduct;
import com.coveiot.coveaccess.tappy.model.SRegisterProductRequest;
import com.coveiot.coveaccess.tappy.model.SRegisteredProduct;
import com.coveiot.utils.utility.LogHelper;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$registerNewProduct$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class NfcStrapViewModel$registerNewProduct$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ long $endUserId;
    public final /* synthetic */ String $friendlyName;
    public final /* synthetic */ long $productId;
    public final /* synthetic */ String $productSerialNumber;
    public int label;
    public final /* synthetic */ NfcStrapViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NfcStrapViewModel$registerNewProduct$1(long j, long j2, String str, String str2, NfcStrapViewModel nfcStrapViewModel, Continuation<? super NfcStrapViewModel$registerNewProduct$1> continuation) {
        super(2, continuation);
        this.$endUserId = j;
        this.$productId = j2;
        this.$productSerialNumber = str;
        this.$friendlyName = str2;
        this.this$0 = nfcStrapViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new NfcStrapViewModel$registerNewProduct$1(this.$endUserId, this.$productId, this.$productSerialNumber, this.$friendlyName, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((NfcStrapViewModel$registerNewProduct$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SRegisterProductRequest sRegisterProductRequest = new SRegisterProductRequest();
            sRegisterProductRequest.setEndUserId(this.$endUserId);
            sRegisterProductRequest.setProductID(this.$productId);
            sRegisterProductRequest.setProductSerialNumber(this.$productSerialNumber);
            sRegisterProductRequest.setFriendlyName(this.$friendlyName);
            final NfcStrapViewModel nfcStrapViewModel = this.this$0;
            TappyApiManager.registerNewProduct(sRegisterProductRequest, new CoveApiListener<SRegisteredProduct, CoveApiErrorModel>() { // from class: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$registerNewProduct$1.1

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$registerNewProduct$1$1$onError$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$registerNewProduct$1$1$a */
                /* loaded from: classes7.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ CoveApiErrorModel $p0;
                    public int label;
                    public final /* synthetic */ NfcStrapViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(CoveApiErrorModel coveApiErrorModel, NfcStrapViewModel nfcStrapViewModel, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.$p0 = coveApiErrorModel;
                        this.this$0 = nfcStrapViewModel;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.$p0, this.this$0, continuation);
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
                            RegisterProductResponse registerProductResponse = new RegisterProductResponse();
                            CoveApiErrorModel coveApiErrorModel = this.$p0;
                            registerProductResponse.setErrorMessage(coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                            this.this$0.getRegisterProductLiveData().setValue(registerProductResponse);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$registerNewProduct$1$1$onSuccess$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$registerNewProduct$1$1$b */
                /* loaded from: classes7.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ SRegisteredProduct $p0;
                    private /* synthetic */ Object L$0;
                    public int label;
                    public final /* synthetic */ NfcStrapViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(SRegisteredProduct sRegisteredProduct, NfcStrapViewModel nfcStrapViewModel, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.$p0 = sRegisteredProduct;
                        this.this$0 = nfcStrapViewModel;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        b bVar = new b(this.$p0, this.this$0, continuation);
                        bVar.L$0 = obj;
                        return bVar;
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
                            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                            RegisterProductResponse registerProductResponse = new RegisterProductResponse();
                            SRegisteredProduct sRegisteredProduct = this.$p0;
                            Unit unit = null;
                            if (sRegisteredProduct != null) {
                                NfcStrapViewModel nfcStrapViewModel = this.this$0;
                                RegisteredProductInfo registeredProductInfo = new RegisteredProductInfo();
                                registeredProductInfo.setEndUserProductRegistrationID(Boxing.boxLong(sRegisteredProduct.getEndUserProductRegistrationID()));
                                registeredProductInfo.setProductSerialNumber(sRegisteredProduct.getProductSerialNumber());
                                registeredProductInfo.setFriendlyName(sRegisteredProduct.getFriendlyName());
                                registeredProductInfo.setEndUserID(Boxing.boxLong(sRegisteredProduct.getEndUserID()));
                                SProduct product = sRegisteredProduct.getProduct();
                                if (product != null) {
                                    Intrinsics.checkNotNullExpressionValue(product, "product");
                                    ProductDetails productDetails = new ProductDetails();
                                    productDetails.setProductId(product.getProductID());
                                    productDetails.setBrandId(product.getBrandID());
                                    productDetails.setChipsetId(product.getChipsetID());
                                    productDetails.setName(product.getName());
                                    productDetails.setChipsetName(product.getChipsetName());
                                    productDetails.setDescription(product.getDescription());
                                    productDetails.setAllowMultipleTokens(product.isAllowMultipleTokens());
                                    productDetails.setSKU(product.getsKU());
                                    productDetails.setImageUrl(product.getImageUrl());
                                    registeredProductInfo.setProduct(productDetails);
                                    registerProductResponse.setRegisteredProductInfo(registeredProductInfo);
                                    nfcStrapViewModel.getRegisterProductLiveData().setValue(registerProductResponse);
                                    unit = Unit.INSTANCE;
                                }
                                if (unit == null) {
                                    nfcStrapViewModel.getRegisterProductLiveData().setValue(registerProductResponse);
                                }
                                unit = Unit.INSTANCE;
                            }
                            if (unit == null) {
                                this.this$0.getRegisterProductLiveData().setValue(registerProductResponse);
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.e(NfcStrapViewModel.this.getTAG(), coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                    e.e(ViewModelKt.getViewModelScope(NfcStrapViewModel.this), Dispatchers.getMain(), null, new a(coveApiErrorModel, NfcStrapViewModel.this, null), 2, null);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SRegisteredProduct sRegisteredProduct) {
                    SProduct product;
                    LogHelper.d(NfcStrapViewModel.this.getTAG(), String.valueOf((sRegisteredProduct == null || (product = sRegisteredProduct.getProduct()) == null) ? null : Long.valueOf(product.getProductID())));
                    e.e(ViewModelKt.getViewModelScope(NfcStrapViewModel.this), Dispatchers.getMain(), null, new b(sRegisteredProduct, NfcStrapViewModel.this, null), 2, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
