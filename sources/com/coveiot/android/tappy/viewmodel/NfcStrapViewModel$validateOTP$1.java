package com.coveiot.android.tappy.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.model.ValidateOTPResponse;
import com.coveiot.android.tappy.model.ValidateOTPResult;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.tappy.TappyApiManager;
import com.coveiot.coveaccess.tappy.model.SValidateOTPRequest;
import com.coveiot.coveaccess.tappy.model.SValidateOTPResponse;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$validateOTP$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class NfcStrapViewModel$validateOTP$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ long $deviceId;
    public final /* synthetic */ long $endUserId;
    public final /* synthetic */ String $otpValue;
    public final /* synthetic */ long $paymentInstrumentTokenId;
    public int label;
    public final /* synthetic */ NfcStrapViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NfcStrapViewModel$validateOTP$1(long j, long j2, String str, long j3, NfcStrapViewModel nfcStrapViewModel, Continuation<? super NfcStrapViewModel$validateOTP$1> continuation) {
        super(2, continuation);
        this.$endUserId = j;
        this.$paymentInstrumentTokenId = j2;
        this.$otpValue = str;
        this.$deviceId = j3;
        this.this$0 = nfcStrapViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new NfcStrapViewModel$validateOTP$1(this.$endUserId, this.$paymentInstrumentTokenId, this.$otpValue, this.$deviceId, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((NfcStrapViewModel$validateOTP$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SValidateOTPRequest sValidateOTPRequest = new SValidateOTPRequest();
            sValidateOTPRequest.setEndUserId(this.$endUserId);
            sValidateOTPRequest.setPaymentInstrumentTokenId(this.$paymentInstrumentTokenId);
            sValidateOTPRequest.setoTPValue(this.$otpValue);
            sValidateOTPRequest.setDeviceId(this.$deviceId);
            sValidateOTPRequest.setDate(System.currentTimeMillis());
            final NfcStrapViewModel nfcStrapViewModel = this.this$0;
            TappyApiManager.validateOtp(sValidateOTPRequest, new CoveApiListener<SValidateOTPResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$validateOTP$1.1

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$validateOTP$1$1$onError$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$validateOTP$1$1$a */
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
                            this.this$0.getValidateOtpLiveData().setValue(null);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$validateOTP$1$1$onSuccess$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$validateOTP$1$1$b */
                /* loaded from: classes7.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ SValidateOTPResponse $p0;
                    private /* synthetic */ Object L$0;
                    public int label;
                    public final /* synthetic */ NfcStrapViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(NfcStrapViewModel nfcStrapViewModel, SValidateOTPResponse sValidateOTPResponse, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.this$0 = nfcStrapViewModel;
                        this.$p0 = sValidateOTPResponse;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        b bVar = new b(this.this$0, this.$p0, continuation);
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
                            ValidateOTPResponse validateOTPResponse = new ValidateOTPResponse();
                            validateOTPResponse.setErrorMessage(this.this$0.getContext().getString(R.string.something_went_wrong));
                            SValidateOTPResponse sValidateOTPResponse = this.$p0;
                            Unit unit = null;
                            if (sValidateOTPResponse != null) {
                                NfcStrapViewModel nfcStrapViewModel = this.this$0;
                                LogHelper.i("validate", "valid " + sValidateOTPResponse);
                                ValidateOTPResult validateOTPResult = new ValidateOTPResult();
                                validateOTPResult.setResult(sValidateOTPResponse.getResult());
                                validateOTPResponse.setValidateOtpResults(new ArrayList());
                                List<ValidateOTPResult> validateOtpResults = validateOTPResponse.getValidateOtpResults();
                                Intrinsics.checkNotNull(validateOtpResults);
                                validateOtpResults.add(validateOTPResult);
                                String result = validateOTPResult.getResult();
                                if (result != null) {
                                    switch (result.hashCode()) {
                                        case -1587987021:
                                            if (result.equals("WrongOTPValue")) {
                                                validateOTPResponse.setErrorMessage(nfcStrapViewModel.getContext().getString(R.string.otp_value_is_wrong));
                                                LogHelper.i("NfcStrapVieModel", "otp wrong");
                                                break;
                                            }
                                            break;
                                        case -546036648:
                                            if (result.equals("CodeExpired")) {
                                                validateOTPResponse.setErrorMessage(nfcStrapViewModel.getContext().getString(R.string.code_expired));
                                                LogHelper.i("NfcStrapVieModel", "otp expiray");
                                                break;
                                            }
                                            break;
                                        case -202516509:
                                            if (result.equals("Success")) {
                                                validateOTPResponse.setErrorMessage(null);
                                                break;
                                            }
                                            break;
                                        case 1861909621:
                                            if (result.equals("MaxOTPVerificationExceeded")) {
                                                validateOTPResponse.setErrorMessage(nfcStrapViewModel.getContext().getString(R.string.max_otp_verification_exceeded));
                                                LogHelper.i("NfcStrapVieModel", "otp max");
                                                break;
                                            }
                                            break;
                                    }
                                }
                                nfcStrapViewModel.getValidateOtpLiveData().setValue(validateOTPResponse);
                                unit = Unit.INSTANCE;
                            }
                            if (unit == null) {
                                this.this$0.getValidateOtpLiveData().setValue(validateOTPResponse);
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
                public void onSuccess(@Nullable SValidateOTPResponse sValidateOTPResponse) {
                    e.e(ViewModelKt.getViewModelScope(NfcStrapViewModel.this), Dispatchers.getMain(), null, new b(NfcStrapViewModel.this, sValidateOTPResponse, null), 2, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
