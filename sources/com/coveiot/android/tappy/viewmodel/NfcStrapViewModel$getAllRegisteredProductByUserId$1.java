package com.coveiot.android.tappy.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.tappy.model.PaymentInstrumentData;
import com.coveiot.android.tappy.model.PaymentInstrumentTokenData;
import com.coveiot.android.tappy.model.ProductDetails;
import com.coveiot.android.tappy.model.RegisteredProductInfo;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.tappy.TappyApiManager;
import com.coveiot.coveaccess.tappy.model.SGetUserRegisteredProductDetailsByUserId;
import com.coveiot.coveaccess.tappy.model.SPaymentInstrument;
import com.coveiot.coveaccess.tappy.model.SPaymentInstrumentTokens;
import com.coveiot.coveaccess.tappy.model.SRegisteredProduct;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getAllRegisteredProductByUserId$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class NfcStrapViewModel$getAllRegisteredProductByUserId$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ long $userId;
    public int label;
    public final /* synthetic */ NfcStrapViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NfcStrapViewModel$getAllRegisteredProductByUserId$1(long j, NfcStrapViewModel nfcStrapViewModel, Continuation<? super NfcStrapViewModel$getAllRegisteredProductByUserId$1> continuation) {
        super(2, continuation);
        this.$userId = j;
        this.this$0 = nfcStrapViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new NfcStrapViewModel$getAllRegisteredProductByUserId$1(this.$userId, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((NfcStrapViewModel$getAllRegisteredProductByUserId$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SGetUserRegisteredProductDetailsByUserId sGetUserRegisteredProductDetailsByUserId = new SGetUserRegisteredProductDetailsByUserId();
            sGetUserRegisteredProductDetailsByUserId.setUserId(this.$userId);
            final NfcStrapViewModel nfcStrapViewModel = this.this$0;
            TappyApiManager.getUserRegisteredProductByUserId(sGetUserRegisteredProductDetailsByUserId, new CoveApiListener<List<? extends SRegisteredProduct>, CoveApiErrorModel>() { // from class: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getAllRegisteredProductByUserId$1.1

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getAllRegisteredProductByUserId$1$1$onError$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getAllRegisteredProductByUserId$1$1$a */
                /* loaded from: classes7.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ NfcStrapViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(NfcStrapViewModel nfcStrapViewModel, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = nfcStrapViewModel;
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
                            this.this$0.getRegisterProductsByUserIdLiveData().setValue(null);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getAllRegisteredProductByUserId$1$1$onSuccess$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getAllRegisteredProductByUserId$1$1$b */
                /* loaded from: classes7.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ List<SRegisteredProduct> $p0;
                    private /* synthetic */ Object L$0;
                    public int label;
                    public final /* synthetic */ NfcStrapViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    public b(List<? extends SRegisteredProduct> list, NfcStrapViewModel nfcStrapViewModel, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.$p0 = list;
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
                        Unit unit;
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                            List<SRegisteredProduct> list = this.$p0;
                            if (list != null) {
                                NfcStrapViewModel nfcStrapViewModel = this.this$0;
                                if (!list.isEmpty()) {
                                    ArrayList arrayList = new ArrayList();
                                    for (SRegisteredProduct sRegisteredProduct : list) {
                                        RegisteredProductInfo registeredProductInfo = new RegisteredProductInfo();
                                        registeredProductInfo.setEndUserID(Boxing.boxLong(sRegisteredProduct.getEndUserID()));
                                        registeredProductInfo.setEndUserProductRegistrationID(Boxing.boxLong(sRegisteredProduct.getEndUserProductRegistrationID()));
                                        registeredProductInfo.setProductSerialNumber(sRegisteredProduct.getProductSerialNumber());
                                        registeredProductInfo.setFriendlyName(sRegisteredProduct.getFriendlyName());
                                        ArrayList<SPaymentInstrumentTokens> paymentInstrumentTokens = sRegisteredProduct.getPaymentInstrumentTokens();
                                        if (!(paymentInstrumentTokens == null || paymentInstrumentTokens.isEmpty())) {
                                            ArrayList arrayList2 = new ArrayList();
                                            Iterator<SPaymentInstrumentTokens> it = sRegisteredProduct.getPaymentInstrumentTokens().iterator();
                                            while (it.hasNext()) {
                                                SPaymentInstrumentTokens next = it.next();
                                                if (next != null) {
                                                    PaymentInstrumentTokenData paymentInstrumentTokenData = new PaymentInstrumentTokenData();
                                                    paymentInstrumentTokenData.setPaymentInstrumentTokenID(Boxing.boxLong(next.getPaymentInstrumentTokenID()));
                                                    paymentInstrumentTokenData.setExpiryDate(next.getExpiryDate());
                                                    paymentInstrumentTokenData.setLast4(next.getLast4());
                                                    paymentInstrumentTokenData.setStatus(next.getStatus());
                                                    paymentInstrumentTokenData.setFriendlyName(next.getFriendlyName());
                                                    paymentInstrumentTokenData.setProvisioningStatus(next.getProvisioningStatus());
                                                    PaymentInstrumentData paymentInstrumentData = new PaymentInstrumentData();
                                                    SPaymentInstrument paymentInstrument = next.getPaymentInstrument();
                                                    paymentInstrumentData.setPaymentInstrumentID(paymentInstrument != null ? Boxing.boxLong(paymentInstrument.getPaymentInstrumentID()) : null);
                                                    SPaymentInstrument paymentInstrument2 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setLast4(paymentInstrument2 != null ? paymentInstrument2.getLast4() : null);
                                                    SPaymentInstrument paymentInstrument3 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setFriendlyName(paymentInstrument3 != null ? paymentInstrument3.getFriendlyName() : null);
                                                    SPaymentInstrument paymentInstrument4 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setBackgroundColor(paymentInstrument4 != null ? paymentInstrument4.getBackgroundColor() : null);
                                                    SPaymentInstrument paymentInstrument5 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setCardArtImageUrl(paymentInstrument5 != null ? paymentInstrument5.getCardArtImageUrl() : null);
                                                    SPaymentInstrument paymentInstrument6 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setCardSymbolImageUrl(paymentInstrument6 != null ? paymentInstrument6.getCardSymbolImageUrl() : null);
                                                    SPaymentInstrument paymentInstrument7 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setContactEmail(paymentInstrument7 != null ? paymentInstrument7.getContactEmail() : null);
                                                    SPaymentInstrument paymentInstrument8 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setContactName(paymentInstrument8 != null ? paymentInstrument8.getContactName() : null);
                                                    SPaymentInstrument paymentInstrument9 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setContactNumber(paymentInstrument9 != null ? paymentInstrument9.getContactNumber() : null);
                                                    SPaymentInstrument paymentInstrument10 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setContactWebSite(paymentInstrument10 != null ? paymentInstrument10.getContactWebSite() : null);
                                                    SPaymentInstrument paymentInstrument11 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setForegroundColor(paymentInstrument11 != null ? paymentInstrument11.getForegroundColor() : null);
                                                    SPaymentInstrument paymentInstrument12 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setDeleted(paymentInstrument12 != null ? Boxing.boxBoolean(paymentInstrument12.isDeleted()) : null);
                                                    SPaymentInstrument paymentInstrument13 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setPIFinalized(paymentInstrument13 != null ? Boxing.boxBoolean(paymentInstrument13.isPIFinalized()) : null);
                                                    SPaymentInstrument paymentInstrument14 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setIssuerType(paymentInstrument14 != null ? paymentInstrument14.getIssuerType() : null);
                                                    SPaymentInstrument paymentInstrument15 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setLabelColor(paymentInstrument15 != null ? paymentInstrument15.getLabelColor() : null);
                                                    SPaymentInstrument paymentInstrument16 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setPaymentNetworkID(paymentInstrument16 != null ? Boxing.boxInt(paymentInstrument16.getPaymentNetworkID()) : null);
                                                    SPaymentInstrument paymentInstrument17 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setPaymentNetworkName(paymentInstrument17 != null ? paymentInstrument17.getPaymentNetworkName() : null);
                                                    SPaymentInstrument paymentInstrument18 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setTermsAndConditionsID(paymentInstrument18 != null ? paymentInstrument18.getTermsAndConditionsID() : null);
                                                    SPaymentInstrument paymentInstrument19 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setTermsAndConditionsFileUrl(paymentInstrument19 != null ? paymentInstrument19.getTermsAndConditionsFileUrl() : null);
                                                    SPaymentInstrument paymentInstrument20 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setTermsAndConditionsUrl(paymentInstrument20 != null ? paymentInstrument20.getTermsAndConditionsUrl() : null);
                                                    SPaymentInstrument paymentInstrument21 = next.getPaymentInstrument();
                                                    paymentInstrumentData.setPrivacyPolicyUrl(paymentInstrument21 != null ? paymentInstrument21.getPrivacyPolicyUrl() : null);
                                                    paymentInstrumentTokenData.setPaymentInstrument(paymentInstrumentData);
                                                    arrayList2.add(paymentInstrumentTokenData);
                                                }
                                            }
                                            registeredProductInfo.setPaymentInstrumentTokens(arrayList2);
                                        }
                                        if (sRegisteredProduct.getProduct() != null) {
                                            ProductDetails productDetails = new ProductDetails();
                                            productDetails.setProductId(sRegisteredProduct.getProduct().getProductID());
                                            productDetails.setBrandId(sRegisteredProduct.getProduct().getBrandID());
                                            productDetails.setChipsetId(sRegisteredProduct.getProduct().getChipsetID());
                                            productDetails.setName(sRegisteredProduct.getProduct().getName());
                                            productDetails.setChipsetName(sRegisteredProduct.getProduct().getChipsetName());
                                            productDetails.setDescription(sRegisteredProduct.getProduct().getDescription());
                                            productDetails.setAllowMultipleTokens(sRegisteredProduct.getProduct().isAllowMultipleTokens());
                                            productDetails.setSKU(sRegisteredProduct.getProduct().getsKU());
                                            productDetails.setImageUrl(sRegisteredProduct.getProduct().getImageUrl());
                                            registeredProductInfo.setProduct(productDetails);
                                        }
                                        arrayList.add(registeredProductInfo);
                                    }
                                    nfcStrapViewModel.getRegisterProductsByUserIdLiveData().setValue(arrayList);
                                } else {
                                    nfcStrapViewModel.getRegisterProductsByUserIdLiveData().setValue(null);
                                }
                                unit = Unit.INSTANCE;
                            } else {
                                unit = null;
                            }
                            if (unit == null) {
                                this.this$0.getRegisterProductsByUserIdLiveData().setValue(null);
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.e(NfcStrapViewModel.this.getTAG(), coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                    e.e(ViewModelKt.getViewModelScope(NfcStrapViewModel.this), Dispatchers.getMain(), null, new a(NfcStrapViewModel.this, null), 2, null);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable List<? extends SRegisteredProduct> list) {
                    e.e(ViewModelKt.getViewModelScope(NfcStrapViewModel.this), Dispatchers.getMain(), null, new b(list, NfcStrapViewModel.this, null), 2, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
