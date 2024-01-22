package com.coveiot.android.tappy.viewmodel;

import android.content.Context;
import android.location.Location;
import android.util.Base64;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.tappy.model.AcceptTermsAndGenerateTokenResponseData;
import com.coveiot.android.tappy.model.ConfirmProvisioningRequest;
import com.coveiot.android.tappy.model.ConfirmProvisioningResponseData;
import com.coveiot.android.tappy.model.DeviceInfo;
import com.coveiot.android.tappy.model.EncryptionKey;
import com.coveiot.android.tappy.model.ErrorLogInfo;
import com.coveiot.android.tappy.model.GetPostPersoCommandsResponseData;
import com.coveiot.android.tappy.model.GetStepUpOptionsResponse;
import com.coveiot.android.tappy.model.InstallFoundationData;
import com.coveiot.android.tappy.model.PaymentInstrumentData;
import com.coveiot.android.tappy.model.PaymentInstrumentTokenResponseData;
import com.coveiot.android.tappy.model.PostPersoCommandsExecutedResponse;
import com.coveiot.android.tappy.model.ProductDetails;
import com.coveiot.android.tappy.model.RegStrapBeanInfo;
import com.coveiot.android.tappy.model.RegisterProductResponse;
import com.coveiot.android.tappy.model.RegisteredProductInfo;
import com.coveiot.android.tappy.model.SECardPersoScript;
import com.coveiot.android.tappy.model.UserDetails;
import com.coveiot.android.tappy.model.ValidateOTPResponse;
import com.coveiot.android.tappy.utils.LocalAPDUCommands;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.net.ssl.HttpsURLConnection;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwt.ReservedClaimNames;
import org.jose4j.keys.AesKey;
/* loaded from: classes7.dex */
public final class NfcStrapViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6027a;
    @NotNull
    public final MutableLiveData<UserDetails> b;
    @NotNull
    public final MutableLiveData<RegisterProductResponse> c;
    @NotNull
    public final MutableLiveData<ProductDetails> d;
    @NotNull
    public final MutableLiveData<ProductDetails> e;
    @NotNull
    public final MutableLiveData<InstallFoundationData> f;
    @NotNull
    public final MutableLiveData<PaymentInstrumentTokenResponseData> g;
    @NotNull
    public final MutableLiveData<String> h;
    @NotNull
    public final MutableLiveData<AcceptTermsAndGenerateTokenResponseData> i;
    @NotNull
    public final MutableLiveData<EncryptionKey> j;
    @NotNull
    public final MutableLiveData<SECardPersoScript> k;
    @NotNull
    public final MutableLiveData<ConfirmProvisioningResponseData> l;
    @NotNull
    public final MutableLiveData<PostPersoCommandsExecutedResponse> m;
    @NotNull
    public final MutableLiveData<GetPostPersoCommandsResponseData> n;
    @NotNull
    public final MutableLiveData<GetStepUpOptionsResponse> o;
    @NotNull
    public final MutableLiveData<Boolean> p;
    @NotNull
    public final MutableLiveData<ValidateOTPResponse> q;
    @NotNull
    public final MutableLiveData<List<RegisteredProductInfo>> r;
    @NotNull
    public final MutableLiveData<List<DeviceInfo>> s;
    @NotNull
    public final MutableLiveData<DeviceInfo> t;
    @NotNull
    public final MutableLiveData<Location> u;
    @NotNull
    public final MutableLiveData<List<RegStrapBeanInfo>> v;
    @NotNull
    public final String w;

    @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getLastKnownLocation$3", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
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
                NfcStrapViewModel.this.getLastKnownLocationLiveData().setValue(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getTAndCTextFromFileUrl$3", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Ref.ObjectRef<String> $tAndCText;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Ref.ObjectRef<String> objectRef, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$tAndCText = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$tAndCText, continuation);
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
                NfcStrapViewModel.this.getTermsAndConditionsTextLiveData().setValue(this.$tAndCText.element);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public NfcStrapViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6027a = context;
        this.b = new MutableLiveData<>();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
        this.f = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
        this.h = new MutableLiveData<>();
        this.i = new MutableLiveData<>();
        this.j = new MutableLiveData<>();
        this.k = new MutableLiveData<>();
        this.l = new MutableLiveData<>();
        this.m = new MutableLiveData<>();
        this.n = new MutableLiveData<>();
        this.o = new MutableLiveData<>();
        this.p = new MutableLiveData<>();
        this.q = new MutableLiveData<>();
        this.r = new MutableLiveData<>();
        this.s = new MutableLiveData<>();
        this.t = new MutableLiveData<>();
        this.u = new MutableLiveData<>();
        this.v = new MutableLiveData<>(CollectionsKt__CollectionsKt.emptyList());
        this.w = "NfcStrapViewModel";
    }

    public final void acceptTermsAndGenerateToken(long j, long j2, long j3, @NotNull String termsAndConditionsId, double d, double d2, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(termsAndConditionsId, "termsAndConditionsId");
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$acceptTermsAndGenerateToken$1(j, j2, j3, termsAndConditionsId, d, d2, str, str2, this, null), 2, null);
    }

    public final void confirmProvisioning(long j, long j2, @NotNull ConfirmProvisioningRequest confirmProvisioningRequest) {
        Intrinsics.checkNotNullParameter(confirmProvisioningRequest, "confirmProvisioningRequest");
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$confirmProvisioning$1(j, j2, confirmProvisioningRequest, this, null), 2, null);
    }

    @NotNull
    public final MutableLiveData<AcceptTermsAndGenerateTokenResponseData> getAcceptTermsAndGenerateTokenResponseLiveData() {
        return this.i;
    }

    public final void getAllRegisteredProductByUserId(long j) {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$getAllRegisteredProductByUserId$1(j, this, null), 2, null);
    }

    @NotNull
    public final MutableLiveData<ConfirmProvisioningResponseData> getConfirmProvisioningLiveData() {
        return this.l;
    }

    @NotNull
    public final Context getContext() {
        return this.f6027a;
    }

    public final void getDeviceInfoByUserId(long j) {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$getDeviceInfoByUserId$1(j, this, null), 2, null);
    }

    @Nullable
    public final Object getEncryptedRiskData(@NotNull String str, @NotNull String str2, @NotNull String str3, int i, @NotNull EncryptionKey encryptionKey, @NotNull Continuation<? super String> continuation) {
        if (i == LocalAPDUCommands.PAYMENTNETWORK_VISA) {
            JsonWebEncryption jsonWebEncryption = new JsonWebEncryption();
            jsonWebEncryption.setPayload(str3);
            jsonWebEncryption.setKeyIdHeaderValue(str + '_' + str2);
            jsonWebEncryption.setHeader(ReservedClaimNames.ISSUED_AT, Boxing.boxLong(System.currentTimeMillis() / ((long) 1000)));
            jsonWebEncryption.setHeader("typ", "JOSE");
            jsonWebEncryption.setHeader("channelSecurityContext", "RSA_PKI2");
            jsonWebEncryption.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_256_GCM);
            jsonWebEncryption.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP_256);
            JsonWebKey newJwk = JsonWebKey.Factory.newJwk(new Gson().toJson(encryptionKey));
            Intrinsics.checkNotNullExpressionValue(newJwk, "newJwk(Gson().toJson(encryptionKey))");
            jsonWebEncryption.setKey(newJwk.getKey());
            String compactSerialization = jsonWebEncryption.getCompactSerialization();
            String str4 = this.w;
            LogHelper.d(str4, "Encrypted Data: " + compactSerialization);
            return compactSerialization;
        } else if (i == LocalAPDUCommands.PAYMENTNETWORK_MASTERCARD) {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AesKey.ALGORITHM);
            keyGenerator.init(128);
            LogHelper.d(this.w, keyGenerator.getAlgorithm());
            SecretKey generateKey = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(1, generateKey);
            String json = new Gson().toJson(str3);
            Intrinsics.checkNotNullExpressionValue(json, "Gson().toJson(cvv)");
            byte[] bytes = json.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            String obj = cipher.doFinal(bytes).toString();
            String str5 = this.w;
            LogHelper.d(str5, "done " + obj);
            JsonWebEncryption jsonWebEncryption2 = new JsonWebEncryption();
            jsonWebEncryption2.setEncryptionMethodHeaderParameter("A128CBC");
            jsonWebEncryption2.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP_256);
            jsonWebEncryption2.setPayload(Base64.encodeToString(generateKey.getEncoded(), 0));
            JsonWebKey newJwk2 = JsonWebKey.Factory.newJwk(new Gson().toJson(encryptionKey));
            Intrinsics.checkNotNullExpressionValue(newJwk2, "newJwk(Gson().toJson(encryptionKey))");
            jsonWebEncryption2.setKey(newJwk2.getKey());
            String compactSerialization2 = jsonWebEncryption2.getCompactSerialization();
            String str6 = this.w;
            LogHelper.d(str6, "Encrypted Key: " + compactSerialization2);
            return compactSerialization2;
        } else {
            return null;
        }
    }

    public final void getEncryptionKey(int i) {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$getEncryptionKey$1(i, this, null), 2, null);
    }

    @NotNull
    public final MutableLiveData<EncryptionKey> getEncryptionKeyLiveData() {
        return this.j;
    }

    @NotNull
    public final MutableLiveData<List<DeviceInfo>> getGetAllRegisteredDevicesLiveData() {
        return this.s;
    }

    @NotNull
    public final MutableLiveData<GetPostPersoCommandsResponseData> getGetPostPersoCommandsLiveData() {
        return this.n;
    }

    @NotNull
    public final MutableLiveData<GetStepUpOptionsResponse> getGetStepUpOptionsLiveData() {
        return this.o;
    }

    @NotNull
    public final MutableLiveData<InstallFoundationData> getInstallFoundationToSecureElementLiveData() {
        return this.f;
    }

    public final void getLastKnownLocation() {
        if (ContextCompat.checkSelfPermission(this.f6027a, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
            return;
        }
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.f6027a);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(context)");
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() { // from class: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getLastKnownLocation$1

            @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getLastKnownLocation$1$onSuccess$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ Location $location;
                public int label;
                public final /* synthetic */ NfcStrapViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(NfcStrapViewModel nfcStrapViewModel, Location location, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = nfcStrapViewModel;
                    this.$location = location;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, this.$location, continuation);
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
                        this.this$0.getLastKnownLocationLiveData().setValue(this.$location);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getLastKnownLocation$1$onSuccess$2", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ NfcStrapViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(NfcStrapViewModel nfcStrapViewModel, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.this$0 = nfcStrapViewModel;
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
                        this.this$0.getLastKnownLocationLiveData().setValue(null);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.google.android.gms.tasks.OnSuccessListener
            public void onSuccess(@Nullable Location location) {
                if (location != null) {
                    String tag = NfcStrapViewModel.this.getTAG();
                    LogHelper.i(tag, "Last Location ==== " + location.getLatitude() + ", " + location.getLongitude());
                    e.e(ViewModelKt.getViewModelScope(NfcStrapViewModel.this), Dispatchers.getMain(), null, new a(NfcStrapViewModel.this, location, null), 2, null);
                    return;
                }
                LogHelper.i(NfcStrapViewModel.this.getTAG(), "getLastLocationLatLng onSuccess() location is NULL====");
                e.e(ViewModelKt.getViewModelScope(NfcStrapViewModel.this), Dispatchers.getMain(), null, new b(NfcStrapViewModel.this, null), 2, null);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getLastKnownLocation$2

            @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getLastKnownLocation$2$onFailure$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
                        this.this$0.getLastKnownLocationLiveData().setValue(null);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.google.android.gms.tasks.OnFailureListener
            public void onFailure(@NotNull Exception e) {
                Intrinsics.checkNotNullParameter(e, "e");
                LogHelper.i(NfcStrapViewModel.this.getTAG(), "onFailure() in getLastLocation() ====");
                e.e(ViewModelKt.getViewModelScope(NfcStrapViewModel.this), Dispatchers.getMain(), null, new a(NfcStrapViewModel.this, null), 2, null);
            }
        });
    }

    @NotNull
    public final MutableLiveData<Location> getLastKnownLocationLiveData() {
        return this.u;
    }

    @NotNull
    public final MutableLiveData<PaymentInstrumentTokenResponseData> getPaymentInstrumentTokenLiveData() {
        return this.g;
    }

    public final void getPostPersoCommands(long j, long j2, @NotNull String initUpdateResponse) {
        Intrinsics.checkNotNullParameter(initUpdateResponse, "initUpdateResponse");
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$getPostPersoCommands$1(j, j2, initUpdateResponse, this, null), 2, null);
    }

    @NotNull
    public final MutableLiveData<PostPersoCommandsExecutedResponse> getPostPersoCommandsExecutedLiveData() {
        return this.m;
    }

    public final void getProductDetailBySerialNumber(@NotNull String serialNumber) {
        Intrinsics.checkNotNullParameter(serialNumber, "serialNumber");
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$getProductDetailBySerialNumber$1(serialNumber, this, null), 2, null);
    }

    public final void getProductDetailByUserIdAndProductId(long j, long j2) {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$getProductDetailByUserIdAndProductId$1(j, j2, this, null), 2, null);
    }

    @NotNull
    public final MutableLiveData<ProductDetails> getProductDetailsByUserIdProductIdLiveData() {
        return this.e;
    }

    @NotNull
    public final MutableLiveData<ProductDetails> getProductDetailsLiveData() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<Boolean> getPutStepUpOptionsLiveData() {
        return this.p;
    }

    @NotNull
    public final MutableLiveData<DeviceInfo> getRegisterNewDeviceLiveData() {
        return this.t;
    }

    @NotNull
    public final MutableLiveData<RegisterProductResponse> getRegisterProductLiveData() {
        return this.c;
    }

    @NotNull
    public final MutableLiveData<List<RegisteredProductInfo>> getRegisterProductsByUserIdLiveData() {
        return this.r;
    }

    @NotNull
    /* renamed from: getRegisteredStrapInfoList  reason: collision with other method in class */
    public final MutableLiveData<List<RegStrapBeanInfo>> m108getRegisteredStrapInfoList() {
        return this.v;
    }

    public final void getStepUpOptions(long j, long j2, long j3) {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$getStepUpOptions$1(j, j2, j3, this, null), 2, null);
    }

    @NotNull
    public final String getTAG() {
        return this.w;
    }

    /* JADX WARN: Type inference failed for: r9v8, types: [T, java.lang.String] */
    @Nullable
    public final Object getTAndCTextFromFileUrl(@NotNull URL url, @NotNull Continuation<? super String> continuation) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        try {
            URLConnection openConnection = url.openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type javax.net.ssl.HttpsURLConnection");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(((HttpsURLConnection) openConnection).getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            LogHelper.d(this.w, String.valueOf(sb));
            objectRef.element = sb.toString();
        } catch (IOException e) {
            String str = this.w;
            LogHelper.d(str, "getTAndCTextFromFileUrl : " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new b(objectRef, null), 2, null);
        return objectRef.element;
    }

    @NotNull
    public final MutableLiveData<String> getTermsAndConditionsTextLiveData() {
        return this.h;
    }

    public final void getTokenPersoScript(long j, long j2) {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$getTokenPersoScript$1(j, j2, this, null), 2, null);
    }

    @NotNull
    public final MutableLiveData<SECardPersoScript> getTokenPersoScriptLiveData() {
        return this.k;
    }

    public final void getUserDetailByEmailId(@NotNull String email) {
        Intrinsics.checkNotNullParameter(email, "email");
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$getUserDetailByEmailId$1(email, this, null), 2, null);
    }

    @NotNull
    public final MutableLiveData<UserDetails> getUserDetailLiveData() {
        return this.b;
    }

    public final void getUserInfo() {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$getUserInfo$1(this, null), 2, null);
    }

    @NotNull
    public final MutableLiveData<ValidateOTPResponse> getValidateOtpLiveData() {
        return this.q;
    }

    public final void installFoundationToSecureElement(long j, long j2, int i, @NotNull String initUpdateResponse, int i2) {
        Intrinsics.checkNotNullParameter(initUpdateResponse, "initUpdateResponse");
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$installFoundationToSecureElement$1(j, j2, i, initUpdateResponse, i2, this, null), 2, null);
    }

    public final void logError(@NotNull ErrorLogInfo errorLogInfo) {
        Intrinsics.checkNotNullParameter(errorLogInfo, "errorLogInfo");
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$logError$1(errorLogInfo, this, null), 2, null);
    }

    public final void postPersoCommandsExecuted(long j, long j2) {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$postPersoCommandsExecuted$1(j, j2, this, null), 2, null);
    }

    public final void putStepUpOptions(long j, long j2, @NotNull String stepUpId, long j3) {
        Intrinsics.checkNotNullParameter(stepUpId, "stepUpId");
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$putStepUpOptions$1(j, j2, stepUpId, j3, this, null), 2, null);
    }

    public final void registerNewDevice(long j, @NotNull DeviceInfo deviceInfo) {
        Intrinsics.checkNotNullParameter(deviceInfo, "deviceInfo");
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$registerNewDevice$1(j, deviceInfo, this, null), 2, null);
    }

    public final void registerNewProduct(long j, long j2, @NotNull String productSerialNumber, @Nullable String str) {
        Intrinsics.checkNotNullParameter(productSerialNumber, "productSerialNumber");
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$registerNewProduct$1(j, j2, productSerialNumber, str, this, null), 2, null);
    }

    public final void sendPaymentInstrumentTokens(long j, long j2, long j3, @NotNull String initUpdateResponse, @NotNull String casDCertificate, @NotNull ArrayList<String> savedApduResponseDatas, @Nullable Long l, @Nullable String str, @Nullable String str2, @NotNull PaymentInstrumentData paymentInstrumentData) {
        Intrinsics.checkNotNullParameter(initUpdateResponse, "initUpdateResponse");
        Intrinsics.checkNotNullParameter(casDCertificate, "casDCertificate");
        Intrinsics.checkNotNullParameter(savedApduResponseDatas, "savedApduResponseDatas");
        Intrinsics.checkNotNullParameter(paymentInstrumentData, "paymentInstrumentData");
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$sendPaymentInstrumentTokens$1(j, j2, j3, initUpdateResponse, casDCertificate, savedApduResponseDatas, l, str, str2, paymentInstrumentData, this, null), 2, null);
    }

    public final void updateRegisteredStrapInfoList(@Nullable List<RegStrapBeanInfo> list) {
        if (list == null || list.isEmpty()) {
            this.v.setValue(null);
        } else {
            this.v.postValue(list);
        }
    }

    public final void validateOTP(long j, long j2, @NotNull String otpValue, long j3) {
        Intrinsics.checkNotNullParameter(otpValue, "otpValue");
        LogHelper.i("NfcStrapViewmodel", "otp value " + otpValue);
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new NfcStrapViewModel$validateOTP$1(j, j2, otpValue, j3, this, null), 2, null);
    }

    @NotNull
    public final LiveData<List<RegStrapBeanInfo>> getRegisteredStrapInfoList() {
        return this.v;
    }
}
