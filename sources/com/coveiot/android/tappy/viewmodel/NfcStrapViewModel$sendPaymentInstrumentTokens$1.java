package com.coveiot.android.tappy.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.tappy.model.PaymentInstrumentData;
import com.coveiot.android.tappy.model.PaymentInstrumentTokenData;
import com.coveiot.android.tappy.model.PaymentInstrumentTokenResponseData;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.tappy.TappyApiManager;
import com.coveiot.coveaccess.tappy.model.SPaymentInstrument;
import com.coveiot.coveaccess.tappy.model.SPaymentInstrumentTokensResponse;
import com.coveiot.coveaccess.tappy.model.SSendPaymentInstrumentTokensRequest;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
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
@DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$sendPaymentInstrumentTokens$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class NfcStrapViewModel$sendPaymentInstrumentTokens$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String $casDCertificate;
    public final /* synthetic */ long $deviceId;
    public final /* synthetic */ long $endUserId;
    public final /* synthetic */ long $endUserProductRegistrationId;
    public final /* synthetic */ String $friendlyName;
    public final /* synthetic */ String $initUpdateResponse;
    public final /* synthetic */ String $locale;
    public final /* synthetic */ PaymentInstrumentData $paymentInstrumentData;
    public final /* synthetic */ Long $paymentInstrumentId;
    public final /* synthetic */ ArrayList<String> $savedApduResponseDatas;
    public int label;
    public final /* synthetic */ NfcStrapViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NfcStrapViewModel$sendPaymentInstrumentTokens$1(long j, long j2, long j3, String str, String str2, ArrayList<String> arrayList, Long l, String str3, String str4, PaymentInstrumentData paymentInstrumentData, NfcStrapViewModel nfcStrapViewModel, Continuation<? super NfcStrapViewModel$sendPaymentInstrumentTokens$1> continuation) {
        super(2, continuation);
        this.$endUserId = j;
        this.$deviceId = j2;
        this.$endUserProductRegistrationId = j3;
        this.$initUpdateResponse = str;
        this.$casDCertificate = str2;
        this.$savedApduResponseDatas = arrayList;
        this.$paymentInstrumentId = l;
        this.$friendlyName = str3;
        this.$locale = str4;
        this.$paymentInstrumentData = paymentInstrumentData;
        this.this$0 = nfcStrapViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new NfcStrapViewModel$sendPaymentInstrumentTokens$1(this.$endUserId, this.$deviceId, this.$endUserProductRegistrationId, this.$initUpdateResponse, this.$casDCertificate, this.$savedApduResponseDatas, this.$paymentInstrumentId, this.$friendlyName, this.$locale, this.$paymentInstrumentData, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((NfcStrapViewModel$sendPaymentInstrumentTokens$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SSendPaymentInstrumentTokensRequest sSendPaymentInstrumentTokensRequest = new SSendPaymentInstrumentTokensRequest();
            sSendPaymentInstrumentTokensRequest.setEndUserId(this.$endUserId);
            sSendPaymentInstrumentTokensRequest.setDeviceID(this.$deviceId);
            sSendPaymentInstrumentTokensRequest.setEndUserProductRegistrationID(this.$endUserProductRegistrationId);
            SSendPaymentInstrumentTokensRequest.CurrentSEInfo currentSEInfo = new SSendPaymentInstrumentTokensRequest.CurrentSEInfo();
            currentSEInfo.setInitUpdateResponse(this.$initUpdateResponse);
            currentSEInfo.setcASDCertificate(this.$casDCertificate);
            currentSEInfo.setSavedAPDUResponseDatas(this.$savedApduResponseDatas);
            sSendPaymentInstrumentTokensRequest.setCurrentSEInfo(currentSEInfo);
            sSendPaymentInstrumentTokensRequest.setPaymentInstrumentID(this.$paymentInstrumentId);
            sSendPaymentInstrumentTokensRequest.setFriendlyName(this.$friendlyName);
            sSendPaymentInstrumentTokensRequest.setLocale(this.$locale);
            SSendPaymentInstrumentTokensRequest.PaymentInstrumentInfo paymentInstrumentInfo = new SSendPaymentInstrumentTokensRequest.PaymentInstrumentInfo();
            Integer paymentNetworkID = this.$paymentInstrumentData.getPaymentNetworkID();
            Intrinsics.checkNotNull(paymentNetworkID);
            paymentInstrumentInfo.setPaymentNetworkID(paymentNetworkID.intValue());
            paymentInstrumentInfo.setFriendlyName(this.$paymentInstrumentData.getFriendlyName());
            paymentInstrumentInfo.setCameraCaptured(this.$paymentInstrumentData.isCameraCaptured());
            paymentInstrumentInfo.setEncryptedPaymentInstrument(this.$paymentInstrumentData.getEncryptedPaymentInstrument());
            sSendPaymentInstrumentTokensRequest.setPaymentInstrumentInfo(paymentInstrumentInfo);
            final NfcStrapViewModel nfcStrapViewModel = this.this$0;
            TappyApiManager.sendPaymentInstrumentTokenByUserId(sSendPaymentInstrumentTokensRequest, new CoveApiListener<SPaymentInstrumentTokensResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$sendPaymentInstrumentTokens$1.1

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$sendPaymentInstrumentTokens$1$1$onError$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$sendPaymentInstrumentTokens$1$1$a */
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
                            this.this$0.getPaymentInstrumentTokenLiveData().setValue(null);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$sendPaymentInstrumentTokens$1$1$onSuccess$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$sendPaymentInstrumentTokens$1$1$b */
                /* loaded from: classes7.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ SPaymentInstrumentTokensResponse $p0;
                    private /* synthetic */ Object L$0;
                    public int label;
                    public final /* synthetic */ NfcStrapViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(SPaymentInstrumentTokensResponse sPaymentInstrumentTokensResponse, NfcStrapViewModel nfcStrapViewModel, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.$p0 = sPaymentInstrumentTokensResponse;
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
                            SPaymentInstrumentTokensResponse sPaymentInstrumentTokensResponse = this.$p0;
                            if (sPaymentInstrumentTokensResponse != null) {
                                NfcStrapViewModel nfcStrapViewModel = this.this$0;
                                PaymentInstrumentTokenResponseData paymentInstrumentTokenResponseData = new PaymentInstrumentTokenResponseData();
                                paymentInstrumentTokenResponseData.setTermsAndConditionsFileUrl(sPaymentInstrumentTokensResponse.getTermsAndConditionsFileUrl());
                                paymentInstrumentTokenResponseData.setTermsAndConditionsID(sPaymentInstrumentTokensResponse.getTermsAndConditionsID());
                                PaymentInstrumentTokenData paymentInstrumentTokenData = new PaymentInstrumentTokenData();
                                paymentInstrumentTokenData.setPaymentInstrumentTokenID(Boxing.boxLong(sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrumentTokenID()));
                                paymentInstrumentTokenData.setExpiryDate(sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getExpiryDate());
                                paymentInstrumentTokenData.setLast4(sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getLast4());
                                paymentInstrumentTokenData.setStatus(sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getStatus());
                                paymentInstrumentTokenData.setFriendlyName(sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getFriendlyName());
                                paymentInstrumentTokenData.setProvisioningStatus(sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getProvisioningStatus());
                                PaymentInstrumentData paymentInstrumentData = new PaymentInstrumentData();
                                SPaymentInstrument paymentInstrument = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setPaymentInstrumentID(paymentInstrument != null ? Boxing.boxLong(paymentInstrument.getPaymentInstrumentID()) : null);
                                SPaymentInstrument paymentInstrument2 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setLast4(paymentInstrument2 != null ? paymentInstrument2.getLast4() : null);
                                SPaymentInstrument paymentInstrument3 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setFriendlyName(paymentInstrument3 != null ? paymentInstrument3.getFriendlyName() : null);
                                SPaymentInstrument paymentInstrument4 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setBackgroundColor(paymentInstrument4 != null ? paymentInstrument4.getBackgroundColor() : null);
                                SPaymentInstrument paymentInstrument5 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setCardArtImageUrl(paymentInstrument5 != null ? paymentInstrument5.getCardArtImageUrl() : null);
                                SPaymentInstrument paymentInstrument6 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setCardSymbolImageUrl(paymentInstrument6 != null ? paymentInstrument6.getCardSymbolImageUrl() : null);
                                SPaymentInstrument paymentInstrument7 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setContactEmail(paymentInstrument7 != null ? paymentInstrument7.getContactEmail() : null);
                                SPaymentInstrument paymentInstrument8 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setContactName(paymentInstrument8 != null ? paymentInstrument8.getContactName() : null);
                                SPaymentInstrument paymentInstrument9 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setContactNumber(paymentInstrument9 != null ? paymentInstrument9.getContactNumber() : null);
                                SPaymentInstrument paymentInstrument10 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setContactWebSite(paymentInstrument10 != null ? paymentInstrument10.getContactWebSite() : null);
                                SPaymentInstrument paymentInstrument11 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setForegroundColor(paymentInstrument11 != null ? paymentInstrument11.getForegroundColor() : null);
                                SPaymentInstrument paymentInstrument12 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setDeleted(paymentInstrument12 != null ? Boxing.boxBoolean(paymentInstrument12.isDeleted()) : null);
                                SPaymentInstrument paymentInstrument13 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setPIFinalized(paymentInstrument13 != null ? Boxing.boxBoolean(paymentInstrument13.isPIFinalized()) : null);
                                SPaymentInstrument paymentInstrument14 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setIssuerType(paymentInstrument14 != null ? paymentInstrument14.getIssuerType() : null);
                                SPaymentInstrument paymentInstrument15 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setLabelColor(paymentInstrument15 != null ? paymentInstrument15.getLabelColor() : null);
                                SPaymentInstrument paymentInstrument16 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setPaymentNetworkID(paymentInstrument16 != null ? Boxing.boxInt(paymentInstrument16.getPaymentNetworkID()) : null);
                                SPaymentInstrument paymentInstrument17 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setPaymentNetworkName(paymentInstrument17 != null ? paymentInstrument17.getPaymentNetworkName() : null);
                                SPaymentInstrument paymentInstrument18 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setTermsAndConditionsID(paymentInstrument18 != null ? paymentInstrument18.getTermsAndConditionsID() : null);
                                SPaymentInstrument paymentInstrument19 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setTermsAndConditionsFileUrl(paymentInstrument19 != null ? paymentInstrument19.getTermsAndConditionsFileUrl() : null);
                                SPaymentInstrument paymentInstrument20 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setTermsAndConditionsUrl(paymentInstrument20 != null ? paymentInstrument20.getTermsAndConditionsUrl() : null);
                                SPaymentInstrument paymentInstrument21 = sPaymentInstrumentTokensResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                paymentInstrumentData.setPrivacyPolicyUrl(paymentInstrument21 != null ? paymentInstrument21.getPrivacyPolicyUrl() : null);
                                paymentInstrumentTokenData.setPaymentInstrument(paymentInstrumentData);
                                paymentInstrumentTokenResponseData.setPaymentInstrumentTokenData(paymentInstrumentTokenData);
                                nfcStrapViewModel.getPaymentInstrumentTokenLiveData().setValue(paymentInstrumentTokenResponseData);
                                unit = Unit.INSTANCE;
                            } else {
                                unit = null;
                            }
                            if (unit == null) {
                                this.this$0.getPaymentInstrumentTokenLiveData().setValue(null);
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
                public void onSuccess(@Nullable SPaymentInstrumentTokensResponse sPaymentInstrumentTokensResponse) {
                    e.e(ViewModelKt.getViewModelScope(NfcStrapViewModel.this), Dispatchers.getMain(), null, new b(sPaymentInstrumentTokensResponse, NfcStrapViewModel.this, null), 2, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
