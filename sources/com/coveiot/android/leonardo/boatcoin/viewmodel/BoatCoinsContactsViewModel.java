package com.coveiot.android.leonardo.boatcoin.viewmodel;

import android.content.Context;
import android.telephony.TelephonyManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.leonardo.boatcoin.model.Recipient;
import com.coveiot.coveaccess.boatcoins.model.CoinsDataRequest;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.AppUtils;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class BoatCoinsContactsViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4657a;
    @NotNull
    public final MutableLiveData<List<Recipient>> b;
    @NotNull
    public final MutableLiveData<List<CoveContact>> c;

    @DebugMetadata(c = "com.coveiot.android.leonardo.boatcoin.viewmodel.BoatCoinsContactsViewModel$retrieveContacts$1", f = "BoatCoinsContactsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        @DebugMetadata(c = "com.coveiot.android.leonardo.boatcoin.viewmodel.BoatCoinsContactsViewModel$retrieveContacts$1$1", f = "BoatCoinsContactsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.leonardo.boatcoin.viewmodel.BoatCoinsContactsViewModel$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C0272a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ ArrayList<CoveContact> $coveContacts;
            public int label;
            public final /* synthetic */ BoatCoinsContactsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0272a(BoatCoinsContactsViewModel boatCoinsContactsViewModel, ArrayList<CoveContact> arrayList, Continuation<? super C0272a> continuation) {
                super(2, continuation);
                this.this$0 = boatCoinsContactsViewModel;
                this.$coveContacts = arrayList;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0272a(this.this$0, this.$coveContacts, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0272a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.getRetrieveContactsLiveData().setValue(this.$coveContacts);
                    this.this$0.getRetrieveContactsLiveData().postValue(this.$coveContacts);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

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
                e.e(ViewModelKt.getViewModelScope(BoatCoinsContactsViewModel.this), Dispatchers.getMain(), null, new C0272a(BoatCoinsContactsViewModel.this, AppUtils.getContacts(BoatCoinsContactsViewModel.this.getContext()), null), 2, null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public BoatCoinsContactsViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4657a = context;
        this.b = new MutableLiveData<>();
        this.c = new MutableLiveData<>();
    }

    public final String c(String str) {
        String replace$default = m.replace$default(str, "[^\\d+]", "", false, 4, (Object) null);
        int length = replace$default.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) replace$default.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        boolean startsWith$default = m.startsWith$default(replace$default.subSequence(i, length + 1).toString(), "+", false, 2, null);
        String replace$default2 = m.replace$default(str, "[^\\d]", "", false, 4, (Object) null);
        if (startsWith$default) {
            return '+' + replace$default2;
        }
        return replace$default2;
    }

    @NotNull
    public final Context getContext() {
        return this.f4657a;
    }

    @NotNull
    public final MutableLiveData<List<CoveContact>> getRetrieveContactsLiveData() {
        return this.c;
    }

    @NotNull
    public final MutableLiveData<List<Recipient>> getSendCoinResponseLiveData() {
        return this.b;
    }

    public final String getUserCountry(Context context) {
        String networkCountryIso;
        try {
            Object systemService = context.getSystemService("phone");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
            TelephonyManager telephonyManager = (TelephonyManager) systemService;
            String simCountryIso = telephonyManager.getSimCountryIso();
            if (simCountryIso != null && simCountryIso.length() == 2) {
                String upperCase = simCountryIso.toUpperCase();
                Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
                return upperCase;
            } else if (telephonyManager.getPhoneType() == 2 || (networkCountryIso = telephonyManager.getNetworkCountryIso()) == null || networkCountryIso.length() != 2) {
                return null;
            } else {
                String upperCase2 = networkCountryIso.toUpperCase();
                Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase()");
                return upperCase2;
            }
        } catch (Exception unused) {
            return null;
        }
    }

    public final void retrieveContacts() {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new a(null), 2, null);
    }

    public final void sendCoins(@NotNull CoinsDataRequest coinsDataRequest) {
        Intrinsics.checkNotNullParameter(coinsDataRequest, "coinsDataRequest");
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new BoatCoinsContactsViewModel$sendCoins$1(coinsDataRequest, this, null), 2, null);
    }

    @Nullable
    public final String verifyPhoneNumber(@NotNull Context context, @NotNull String mobileNo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mobileNo, "mobileNo");
        try {
            Phonenumber.PhoneNumber parse = PhoneNumberUtil.getInstance().parse(c(mobileNo), getUserCountry(context));
            StringBuilder sb = new StringBuilder();
            sb.append('+');
            sb.append(parse.getCountryCode());
            sb.append(parse.getNationalNumber());
            return sb.toString();
        } catch (NumberParseException unused) {
            return null;
        }
    }
}
