package com.coveiot.android.tappy.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.tappy.model.EncryptionKey;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.tappy.TappyApiManager;
import com.coveiot.coveaccess.tappy.model.SEncryptionKey;
import com.coveiot.coveaccess.tappy.model.SGetEncryptionKeyRequest;
import com.coveiot.utils.utility.LogHelper;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getEncryptionKey$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class NfcStrapViewModel$getEncryptionKey$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ int $paymentNetworkId;
    public int label;
    public final /* synthetic */ NfcStrapViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NfcStrapViewModel$getEncryptionKey$1(int i, NfcStrapViewModel nfcStrapViewModel, Continuation<? super NfcStrapViewModel$getEncryptionKey$1> continuation) {
        super(2, continuation);
        this.$paymentNetworkId = i;
        this.this$0 = nfcStrapViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new NfcStrapViewModel$getEncryptionKey$1(this.$paymentNetworkId, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((NfcStrapViewModel$getEncryptionKey$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SGetEncryptionKeyRequest sGetEncryptionKeyRequest = new SGetEncryptionKeyRequest();
            sGetEncryptionKeyRequest.setPaymentNetworkId(this.$paymentNetworkId);
            final NfcStrapViewModel nfcStrapViewModel = this.this$0;
            TappyApiManager.getGetEncryptionKey(sGetEncryptionKeyRequest, new CoveApiListener<SEncryptionKey, CoveApiErrorModel>() { // from class: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getEncryptionKey$1.1

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getEncryptionKey$1$1$onError$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getEncryptionKey$1$1$a */
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
                            this.this$0.getEncryptionKeyLiveData().setValue(null);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getEncryptionKey$1$1$onSuccess$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$getEncryptionKey$1$1$b */
                /* loaded from: classes7.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ SEncryptionKey $p0;
                    private /* synthetic */ Object L$0;
                    public int label;
                    public final /* synthetic */ NfcStrapViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(SEncryptionKey sEncryptionKey, NfcStrapViewModel nfcStrapViewModel, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.$p0 = sEncryptionKey;
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
                            SEncryptionKey sEncryptionKey = this.$p0;
                            if (sEncryptionKey != null) {
                                NfcStrapViewModel nfcStrapViewModel = this.this$0;
                                EncryptionKey encryptionKey = new EncryptionKey();
                                encryptionKey.setKty(sEncryptionKey.getKty());
                                encryptionKey.setUse(sEncryptionKey.getUse());
                                encryptionKey.setN(sEncryptionKey.getN());
                                encryptionKey.setE(sEncryptionKey.getE());
                                nfcStrapViewModel.getEncryptionKeyLiveData().setValue(encryptionKey);
                                unit = Unit.INSTANCE;
                            } else {
                                unit = null;
                            }
                            if (unit == null) {
                                this.this$0.getEncryptionKeyLiveData().setValue(null);
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
                public void onSuccess(@Nullable SEncryptionKey sEncryptionKey) {
                    e.e(ViewModelKt.getViewModelScope(NfcStrapViewModel.this), Dispatchers.getMain(), null, new b(sEncryptionKey, NfcStrapViewModel.this, null), 2, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
