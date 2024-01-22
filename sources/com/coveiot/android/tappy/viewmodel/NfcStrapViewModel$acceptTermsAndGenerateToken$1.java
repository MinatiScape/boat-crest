package com.coveiot.android.tappy.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.tappy.model.APDULine;
import com.coveiot.android.tappy.model.AcceptTermsAndGenerateTokenResponseData;
import com.coveiot.android.tappy.model.PaymentInstrumentData;
import com.coveiot.android.tappy.model.PaymentInstrumentTokenData;
import com.coveiot.android.tappy.model.SECardPersoScript;
import com.coveiot.android.tappy.model.StepUpRequest;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.tappy.TappyApiManager;
import com.coveiot.coveaccess.tappy.model.SAcceptTermsAndGenerateTokenRequest;
import com.coveiot.coveaccess.tappy.model.SAcceptTermsAndGenerateTokenResponse;
import com.coveiot.coveaccess.tappy.model.SPaymentInstrument;
import com.coveiot.coveaccess.tappy.model.SSECardPersoScript;
import com.coveiot.coveaccess.tappy.model.SStepUpRequest;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
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
@DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$acceptTermsAndGenerateToken$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class NfcStrapViewModel$acceptTermsAndGenerateToken$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ long $deviceId;
    public final /* synthetic */ String $encryptedRiskData;
    public final /* synthetic */ double $locationLatitude;
    public final /* synthetic */ double $locationLongitude;
    public final /* synthetic */ String $locationSource;
    public final /* synthetic */ long $paymentInstrumentTokenId;
    public final /* synthetic */ String $termsAndConditionsId;
    public final /* synthetic */ long $userId;
    public int label;
    public final /* synthetic */ NfcStrapViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NfcStrapViewModel$acceptTermsAndGenerateToken$1(long j, long j2, long j3, String str, double d, double d2, String str2, String str3, NfcStrapViewModel nfcStrapViewModel, Continuation<? super NfcStrapViewModel$acceptTermsAndGenerateToken$1> continuation) {
        super(2, continuation);
        this.$userId = j;
        this.$paymentInstrumentTokenId = j2;
        this.$deviceId = j3;
        this.$termsAndConditionsId = str;
        this.$locationLatitude = d;
        this.$locationLongitude = d2;
        this.$locationSource = str2;
        this.$encryptedRiskData = str3;
        this.this$0 = nfcStrapViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new NfcStrapViewModel$acceptTermsAndGenerateToken$1(this.$userId, this.$paymentInstrumentTokenId, this.$deviceId, this.$termsAndConditionsId, this.$locationLatitude, this.$locationLongitude, this.$locationSource, this.$encryptedRiskData, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((NfcStrapViewModel$acceptTermsAndGenerateToken$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SAcceptTermsAndGenerateTokenRequest sAcceptTermsAndGenerateTokenRequest = new SAcceptTermsAndGenerateTokenRequest();
            sAcceptTermsAndGenerateTokenRequest.setEndUserId(this.$userId);
            sAcceptTermsAndGenerateTokenRequest.setId(this.$paymentInstrumentTokenId);
            sAcceptTermsAndGenerateTokenRequest.setDeviceId(this.$deviceId);
            sAcceptTermsAndGenerateTokenRequest.setTermsAndConditionsID(this.$termsAndConditionsId);
            sAcceptTermsAndGenerateTokenRequest.setLocationLatitude(this.$locationLatitude);
            sAcceptTermsAndGenerateTokenRequest.setLocationLongitude(this.$locationLongitude);
            sAcceptTermsAndGenerateTokenRequest.setLocationSource(this.$locationSource);
            sAcceptTermsAndGenerateTokenRequest.setEncryptedRiskData(this.$encryptedRiskData);
            final NfcStrapViewModel nfcStrapViewModel = this.this$0;
            TappyApiManager.acceptTermsAndGenerateToken(sAcceptTermsAndGenerateTokenRequest, new CoveApiListener<SAcceptTermsAndGenerateTokenResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$acceptTermsAndGenerateToken$1.1

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$acceptTermsAndGenerateToken$1$1$onError$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$acceptTermsAndGenerateToken$1$1$a */
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
                            this.this$0.getAcceptTermsAndGenerateTokenResponseLiveData().setValue(null);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$acceptTermsAndGenerateToken$1$1$onSuccess$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$acceptTermsAndGenerateToken$1$1$b */
                /* loaded from: classes7.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ SAcceptTermsAndGenerateTokenResponse $p0;
                    private /* synthetic */ Object L$0;
                    public int label;
                    public final /* synthetic */ NfcStrapViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(SAcceptTermsAndGenerateTokenResponse sAcceptTermsAndGenerateTokenResponse, NfcStrapViewModel nfcStrapViewModel, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.$p0 = sAcceptTermsAndGenerateTokenResponse;
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
                            SAcceptTermsAndGenerateTokenResponse sAcceptTermsAndGenerateTokenResponse = this.$p0;
                            if (sAcceptTermsAndGenerateTokenResponse != null) {
                                NfcStrapViewModel nfcStrapViewModel = this.this$0;
                                AcceptTermsAndGenerateTokenResponseData acceptTermsAndGenerateTokenResponseData = new AcceptTermsAndGenerateTokenResponseData();
                                if (sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken() != null) {
                                    PaymentInstrumentTokenData paymentInstrumentTokenData = new PaymentInstrumentTokenData();
                                    paymentInstrumentTokenData.setPaymentInstrumentTokenID(Boxing.boxLong(sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrumentTokenID()));
                                    paymentInstrumentTokenData.setExpiryDate(sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getExpiryDate());
                                    paymentInstrumentTokenData.setLast4(sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getLast4());
                                    paymentInstrumentTokenData.setStatus(sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getStatus());
                                    paymentInstrumentTokenData.setFriendlyName(sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getFriendlyName());
                                    paymentInstrumentTokenData.setProvisioningStatus(sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getProvisioningStatus());
                                    PaymentInstrumentData paymentInstrumentData = new PaymentInstrumentData();
                                    SPaymentInstrument paymentInstrument = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setPaymentInstrumentID(paymentInstrument != null ? Boxing.boxLong(paymentInstrument.getPaymentInstrumentID()) : null);
                                    SPaymentInstrument paymentInstrument2 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setLast4(paymentInstrument2 != null ? paymentInstrument2.getLast4() : null);
                                    SPaymentInstrument paymentInstrument3 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setFriendlyName(paymentInstrument3 != null ? paymentInstrument3.getFriendlyName() : null);
                                    SPaymentInstrument paymentInstrument4 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setBackgroundColor(paymentInstrument4 != null ? paymentInstrument4.getBackgroundColor() : null);
                                    SPaymentInstrument paymentInstrument5 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setCardArtImageUrl(paymentInstrument5 != null ? paymentInstrument5.getCardArtImageUrl() : null);
                                    SPaymentInstrument paymentInstrument6 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setCardSymbolImageUrl(paymentInstrument6 != null ? paymentInstrument6.getCardSymbolImageUrl() : null);
                                    SPaymentInstrument paymentInstrument7 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setContactEmail(paymentInstrument7 != null ? paymentInstrument7.getContactEmail() : null);
                                    SPaymentInstrument paymentInstrument8 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setContactName(paymentInstrument8 != null ? paymentInstrument8.getContactName() : null);
                                    SPaymentInstrument paymentInstrument9 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setContactNumber(paymentInstrument9 != null ? paymentInstrument9.getContactNumber() : null);
                                    SPaymentInstrument paymentInstrument10 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setContactWebSite(paymentInstrument10 != null ? paymentInstrument10.getContactWebSite() : null);
                                    SPaymentInstrument paymentInstrument11 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setForegroundColor(paymentInstrument11 != null ? paymentInstrument11.getForegroundColor() : null);
                                    SPaymentInstrument paymentInstrument12 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setDeleted(paymentInstrument12 != null ? Boxing.boxBoolean(paymentInstrument12.isDeleted()) : null);
                                    SPaymentInstrument paymentInstrument13 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setPIFinalized(paymentInstrument13 != null ? Boxing.boxBoolean(paymentInstrument13.isPIFinalized()) : null);
                                    SPaymentInstrument paymentInstrument14 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setIssuerType(paymentInstrument14 != null ? paymentInstrument14.getIssuerType() : null);
                                    SPaymentInstrument paymentInstrument15 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setLabelColor(paymentInstrument15 != null ? paymentInstrument15.getLabelColor() : null);
                                    SPaymentInstrument paymentInstrument16 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setPaymentNetworkID(paymentInstrument16 != null ? Boxing.boxInt(paymentInstrument16.getPaymentNetworkID()) : null);
                                    SPaymentInstrument paymentInstrument17 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setPaymentNetworkName(paymentInstrument17 != null ? paymentInstrument17.getPaymentNetworkName() : null);
                                    SPaymentInstrument paymentInstrument18 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setTermsAndConditionsID(paymentInstrument18 != null ? paymentInstrument18.getTermsAndConditionsID() : null);
                                    SPaymentInstrument paymentInstrument19 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setTermsAndConditionsFileUrl(paymentInstrument19 != null ? paymentInstrument19.getTermsAndConditionsFileUrl() : null);
                                    SPaymentInstrument paymentInstrument20 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setTermsAndConditionsUrl(paymentInstrument20 != null ? paymentInstrument20.getTermsAndConditionsUrl() : null);
                                    SPaymentInstrument paymentInstrument21 = sAcceptTermsAndGenerateTokenResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setPrivacyPolicyUrl(paymentInstrument21 != null ? paymentInstrument21.getPrivacyPolicyUrl() : null);
                                    paymentInstrumentTokenData.setPaymentInstrument(paymentInstrumentData);
                                    acceptTermsAndGenerateTokenResponseData.setPaymentInstrumentTokenData(paymentInstrumentTokenData);
                                }
                                ArrayList<SStepUpRequest> stepUpRequests = sAcceptTermsAndGenerateTokenResponse.getStepUpRequests();
                                boolean z = false;
                                if (!(stepUpRequests == null || stepUpRequests.isEmpty())) {
                                    ArrayList<StepUpRequest> arrayList = new ArrayList<>();
                                    Iterator<SStepUpRequest> it = sAcceptTermsAndGenerateTokenResponse.getStepUpRequests().iterator();
                                    while (it.hasNext()) {
                                        SStepUpRequest next = it.next();
                                        StepUpRequest stepUpRequest = new StepUpRequest();
                                        stepUpRequest.setId(next.getId());
                                        stepUpRequest.setMethod(next.getMethod());
                                        stepUpRequest.setMethodDescription(next.getMethodDescription());
                                        stepUpRequest.setMethodValue(next.getMethodValue());
                                        arrayList.add(stepUpRequest);
                                    }
                                    acceptTermsAndGenerateTokenResponseData.setStepUpRequests(arrayList);
                                }
                                if (sAcceptTermsAndGenerateTokenResponse.getsECardPersoScript() != null) {
                                    SECardPersoScript sECardPersoScript = new SECardPersoScript();
                                    sECardPersoScript.setTokenPersoScriptPending(Boxing.boxBoolean(sAcceptTermsAndGenerateTokenResponse.getsECardPersoScript().isTokenPersoScriptPending()));
                                    sECardPersoScript.setAppletInstanceAID(sAcceptTermsAndGenerateTokenResponse.getsECardPersoScript().getAppletInstanceAID());
                                    sECardPersoScript.setPriorityCode(sAcceptTermsAndGenerateTokenResponse.getsECardPersoScript().getPriorityCode());
                                    ArrayList<SSECardPersoScript.APDULine> arrayList2 = sAcceptTermsAndGenerateTokenResponse.getsECardPersoScript().getaPDULines();
                                    if (arrayList2 == null || arrayList2.isEmpty()) {
                                        z = true;
                                    }
                                    if (!z) {
                                        ArrayList<APDULine> arrayList3 = new ArrayList<>();
                                        Iterator<SSECardPersoScript.APDULine> it2 = sAcceptTermsAndGenerateTokenResponse.getsECardPersoScript().getaPDULines().iterator();
                                        while (it2.hasNext()) {
                                            SSECardPersoScript.APDULine next2 = it2.next();
                                            APDULine aPDULine = new APDULine();
                                            aPDULine.setAPDU(next2.getaPDU());
                                            aPDULine.setAPDUID(next2.getaPDUID());
                                            arrayList3.add(aPDULine);
                                        }
                                        sECardPersoScript.setAPDULines(arrayList3);
                                    }
                                    acceptTermsAndGenerateTokenResponseData.setSeCardPersoScript(sECardPersoScript);
                                }
                                nfcStrapViewModel.getAcceptTermsAndGenerateTokenResponseLiveData().setValue(acceptTermsAndGenerateTokenResponseData);
                                unit = Unit.INSTANCE;
                            } else {
                                unit = null;
                            }
                            if (unit == null) {
                                this.this$0.getAcceptTermsAndGenerateTokenResponseLiveData().setValue(null);
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
                public void onSuccess(@Nullable SAcceptTermsAndGenerateTokenResponse sAcceptTermsAndGenerateTokenResponse) {
                    e.e(ViewModelKt.getViewModelScope(NfcStrapViewModel.this), Dispatchers.getMain(), null, new b(sAcceptTermsAndGenerateTokenResponse, NfcStrapViewModel.this, null), 2, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
