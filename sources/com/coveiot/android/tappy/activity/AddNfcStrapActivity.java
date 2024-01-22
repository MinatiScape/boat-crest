package com.coveiot.android.tappy.activity;

import android.content.Intent;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.activity.AddNfcStrapActivity;
import com.coveiot.android.tappy.databinding.ActivityAddNfcStrapBinding;
import com.coveiot.android.tappy.fragment.FragmentContactPhoneNfcStrap;
import com.coveiot.android.tappy.model.DeviceInfo;
import com.coveiot.android.tappy.model.ErrorLogInfo;
import com.coveiot.android.tappy.model.ProductDetails;
import com.coveiot.android.tappy.model.RegisterProductResponse;
import com.coveiot.android.tappy.model.RegisteredProductInfo;
import com.coveiot.android.tappy.model.SupportedBank;
import com.coveiot.android.tappy.model.UpdateRegisteredProductResponse;
import com.coveiot.android.tappy.nfc.NfcUtility;
import com.coveiot.android.tappy.utils.Constants;
import com.coveiot.android.tappy.utils.Utils;
import com.coveiot.android.tappy.viewmodel.ManageViewModel;
import com.coveiot.android.tappy.viewmodel.NfcStrapViewModel;
import com.coveiot.android.tappy.viewmodel.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class AddNfcStrapActivity extends BaseActivity implements FragmentContactPhoneNfcStrap.OnTryAgainClickListener {
    @Nullable
    public Long A;
    @Nullable
    public FragmentContactPhoneNfcStrap B;
    public ActivityAddNfcStrapBinding p;
    public NfcStrapViewModel q;
    public ManageViewModel r;
    @Nullable
    public String t;
    public boolean u;
    @Nullable
    public Long v;
    @Nullable
    public Long w;
    public long x;
    @Nullable
    public String z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final String s = AddNfcStrapActivity.class.getSimpleName();
    @NotNull
    public Handler y = new Handler(Looper.getMainLooper());

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddNfcStrapActivity$loadContactPhoneNfcStrapFragment$1", f = "AddNfcStrapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(AddNfcStrapActivity addNfcStrapActivity) {
            FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap;
            FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap2 = addNfcStrapActivity.getMFragmentContactPhoneNfcStrap();
            boolean z = true;
            if (mFragmentContactPhoneNfcStrap2 == null || !mFragmentContactPhoneNfcStrap2.isAdded()) {
                z = false;
            }
            if (!z || (mFragmentContactPhoneNfcStrap = addNfcStrapActivity.getMFragmentContactPhoneNfcStrap()) == null) {
                return;
            }
            mFragmentContactPhoneNfcStrap.setCurrentState(3);
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
                AddNfcStrapActivity addNfcStrapActivity = AddNfcStrapActivity.this;
                addNfcStrapActivity.setMFragmentContactPhoneNfcStrap(FragmentContactPhoneNfcStrap.Companion.newInstance(2, addNfcStrapActivity));
                FragmentTransaction beginTransaction = AddNfcStrapActivity.this.getSupportFragmentManager().beginTransaction();
                int i = R.id.fragment_container;
                FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap = AddNfcStrapActivity.this.getMFragmentContactPhoneNfcStrap();
                Intrinsics.checkNotNull(mFragmentContactPhoneNfcStrap);
                beginTransaction.replace(i, mFragmentContactPhoneNfcStrap).commitAllowingStateLoss();
                Handler handler = AddNfcStrapActivity.this.y;
                final AddNfcStrapActivity addNfcStrapActivity2 = AddNfcStrapActivity.this;
                handler.postDelayed(new Runnable() { // from class: com.coveiot.android.tappy.activity.i0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AddNfcStrapActivity.a.invokeSuspend$lambda$0(AddNfcStrapActivity.this);
                    }
                }, 6000L);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes7.dex */
    public static final class b extends Lambda implements Function1<RegisterProductResponse, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddNfcStrapActivity$onCreate$1$1", f = "AddNfcStrapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ RegisterProductResponse $t;
            public int label;
            public final /* synthetic */ AddNfcStrapActivity this$0;

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddNfcStrapActivity$onCreate$1$1$1", f = "AddNfcStrapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.AddNfcStrapActivity$b$a$a  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0314a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ RegisterProductResponse $t;
                public int label;
                public final /* synthetic */ AddNfcStrapActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0314a(AddNfcStrapActivity addNfcStrapActivity, RegisterProductResponse registerProductResponse, Continuation<? super C0314a> continuation) {
                    super(2, continuation);
                    this.this$0 = addNfcStrapActivity;
                    this.$t = registerProductResponse;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0314a(this.this$0, this.$t, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0314a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    ManageViewModel manageViewModel;
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        AddNfcStrapActivity addNfcStrapActivity = this.this$0;
                        RegisteredProductInfo registeredProductInfo = this.$t.getRegisteredProductInfo();
                        addNfcStrapActivity.A = registeredProductInfo != null ? registeredProductInfo.getEndUserProductRegistrationID() : null;
                        ManageViewModel manageViewModel2 = this.this$0.r;
                        if (manageViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                            manageViewModel = null;
                        } else {
                            manageViewModel = manageViewModel2;
                        }
                        String str = this.this$0.z;
                        Intrinsics.checkNotNull(str);
                        Long l = this.this$0.v;
                        Intrinsics.checkNotNull(l);
                        long longValue = l.longValue();
                        RegisteredProductInfo registeredProductInfo2 = this.$t.getRegisteredProductInfo();
                        Intrinsics.checkNotNull(registeredProductInfo2);
                        Long endUserProductRegistrationID = registeredProductInfo2.getEndUserProductRegistrationID();
                        Intrinsics.checkNotNull(endUserProductRegistrationID);
                        manageViewModel.updateRegisteredProductFriendlyName(str, longValue, endUserProductRegistrationID.longValue());
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AddNfcStrapActivity addNfcStrapActivity, RegisterProductResponse registerProductResponse, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = addNfcStrapActivity;
                this.$t = registerProductResponse;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invokeSuspend$lambda$1(AddNfcStrapActivity addNfcStrapActivity, RegisterProductResponse registerProductResponse, View view) {
                Intent intent = new Intent(addNfcStrapActivity, AddPaymentCardActivity.class);
                intent.putExtra(Constants.END_USER_GLOBAL_ID, addNfcStrapActivity.v);
                intent.putExtra("deviceId", addNfcStrapActivity.x);
                RegisteredProductInfo registeredProductInfo = registerProductResponse.getRegisteredProductInfo();
                Intrinsics.checkNotNull(registeredProductInfo);
                intent.putExtra(Constants.END_USER_PRODUCT_REGISTRATION_ID, registeredProductInfo.getEndUserProductRegistrationID());
                intent.putExtra(Constants.SERIAL_NUMBER, addNfcStrapActivity.t);
                addNfcStrapActivity.startActivity(intent);
                addNfcStrapActivity.w();
                addNfcStrapActivity.finish();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invokeSuspend$lambda$2(AddNfcStrapActivity addNfcStrapActivity, View view) {
                addNfcStrapActivity.w();
                addNfcStrapActivity.finish();
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, this.$t, continuation);
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
                        boolean z = true;
                        if (this.$t.getRegisteredProductInfo() != null) {
                            String str = this.this$0.z;
                            if (str != null && str.length() != 0) {
                                z = false;
                            }
                            ActivityAddNfcStrapBinding activityAddNfcStrapBinding = null;
                            if (!z) {
                                LifecycleCoroutineScope lifecycleScope = LifecycleOwnerKt.getLifecycleScope(this.this$0);
                                if (lifecycleScope != null) {
                                    kotlinx.coroutines.e.e(lifecycleScope, Dispatchers.getIO(), null, new C0314a(this.this$0, this.$t, null), 2, null);
                                }
                            } else {
                                Fragment findFragmentById = this.this$0.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                                if (findFragmentById != null) {
                                    Boxing.boxInt(this.this$0.getSupportFragmentManager().beginTransaction().remove(findFragmentById).commit());
                                }
                                ActivityAddNfcStrapBinding activityAddNfcStrapBinding2 = this.this$0.p;
                                if (activityAddNfcStrapBinding2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityAddNfcStrapBinding2 = null;
                                }
                                activityAddNfcStrapBinding2.strapAddSuccessStatusLayout.setVisibility(0);
                                ActivityAddNfcStrapBinding activityAddNfcStrapBinding3 = this.this$0.p;
                                if (activityAddNfcStrapBinding3 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityAddNfcStrapBinding3 = null;
                                }
                                Button button = activityAddNfcStrapBinding3.btnProceedAdd;
                                final AddNfcStrapActivity addNfcStrapActivity = this.this$0;
                                final RegisterProductResponse registerProductResponse = this.$t;
                                button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.k0
                                    @Override // android.view.View.OnClickListener
                                    public final void onClick(View view) {
                                        AddNfcStrapActivity.b.a.invokeSuspend$lambda$1(AddNfcStrapActivity.this, registerProductResponse, view);
                                    }
                                });
                                ActivityAddNfcStrapBinding activityAddNfcStrapBinding4 = this.this$0.p;
                                if (activityAddNfcStrapBinding4 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                } else {
                                    activityAddNfcStrapBinding = activityAddNfcStrapBinding4;
                                }
                                TextView textView = activityAddNfcStrapBinding.addLater;
                                final AddNfcStrapActivity addNfcStrapActivity2 = this.this$0;
                                textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.j0
                                    @Override // android.view.View.OnClickListener
                                    public final void onClick(View view) {
                                        AddNfcStrapActivity.b.a.invokeSuspend$lambda$2(AddNfcStrapActivity.this, view);
                                    }
                                });
                            }
                        } else {
                            LogHelper.e(this.this$0.getTAG(), "Product registration failed.");
                            String string = this.this$0.getString(R.string.product_registration_failed);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.product_registration_failed)");
                            String errorMessage = this.$t.getErrorMessage();
                            if (errorMessage != null && errorMessage.length() != 0) {
                                z = false;
                            }
                            if (!z) {
                                string = this.$t.getErrorMessage();
                                Intrinsics.checkNotNull(string);
                            }
                            Toast.makeText(this.this$0, string, 0).show();
                            this.this$0.logError("RegisterProduct", "Product registration failed.", "Critical");
                            this.this$0.w();
                            this.this$0.finish();
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
        public /* bridge */ /* synthetic */ Unit invoke(RegisterProductResponse registerProductResponse) {
            invoke2(registerProductResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(RegisterProductResponse registerProductResponse) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddNfcStrapActivity.this), Dispatchers.getMain(), null, new a(AddNfcStrapActivity.this, registerProductResponse, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class c extends Lambda implements Function1<ProductDetails, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddNfcStrapActivity$onCreate$2$1", f = "AddNfcStrapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ ProductDetails $t;
            public int label;
            public final /* synthetic */ AddNfcStrapActivity this$0;

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddNfcStrapActivity$onCreate$2$1$1$1", f = "AddNfcStrapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.AddNfcStrapActivity$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0315a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ AddNfcStrapActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0315a(AddNfcStrapActivity addNfcStrapActivity, Continuation<? super C0315a> continuation) {
                    super(2, continuation);
                    this.this$0 = addNfcStrapActivity;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0315a(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0315a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        NfcStrapViewModel nfcStrapViewModel = this.this$0.q;
                        if (nfcStrapViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            nfcStrapViewModel = null;
                        }
                        Long l = this.this$0.v;
                        Intrinsics.checkNotNull(l);
                        nfcStrapViewModel.getAllRegisteredProductByUserId(l.longValue());
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddNfcStrapActivity$onCreate$2$1$1$2", f = "AddNfcStrapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes7.dex */
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ AddNfcStrapActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(AddNfcStrapActivity addNfcStrapActivity, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.this$0 = addNfcStrapActivity;
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
                        AddNfcStrapActivity addNfcStrapActivity = this.this$0;
                        Toast.makeText(addNfcStrapActivity, addNfcStrapActivity.getString(R.string.user_details_not_found), 0).show();
                        LogHelper.d(this.this$0.getTAG(), "User details not found.");
                        this.this$0.logError("RegisterProduct", "User detail not found.", ErrorConstants.GENERIC_ERROR);
                        this.this$0.w();
                        this.this$0.finish();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddNfcStrapActivity$onCreate$2$1$2", f = "AddNfcStrapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.activity.AddNfcStrapActivity$c$a$c  reason: collision with other inner class name */
            /* loaded from: classes7.dex */
            public static final class C0316c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ AddNfcStrapActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0316c(AddNfcStrapActivity addNfcStrapActivity, Continuation<? super C0316c> continuation) {
                    super(2, continuation);
                    this.this$0 = addNfcStrapActivity;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new C0316c(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((C0316c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        AddNfcStrapActivity addNfcStrapActivity = this.this$0;
                        Toast.makeText(addNfcStrapActivity, addNfcStrapActivity.getString(R.string.product_details_not_found), 0).show();
                        LogHelper.d(this.this$0.getTAG(), "Product details not found.");
                        this.this$0.logError("RegisterProduct", "Product detail not found.", "Critical");
                        this.this$0.w();
                        this.this$0.finish();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AddNfcStrapActivity addNfcStrapActivity, ProductDetails productDetails, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = addNfcStrapActivity;
                this.$t = productDetails;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, this.$t, continuation);
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
                        ProductDetails productDetails = this.$t;
                        if (productDetails == null) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getMain(), null, new C0316c(this.this$0, null), 2, null);
                        } else {
                            long productId = productDetails.getProductId();
                            AddNfcStrapActivity addNfcStrapActivity = this.this$0;
                            addNfcStrapActivity.w = Boxing.boxLong(productId);
                            if (addNfcStrapActivity.v != null) {
                                Long l = addNfcStrapActivity.v;
                                Intrinsics.checkNotNull(l);
                                if (l.longValue() > 0) {
                                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(addNfcStrapActivity), Dispatchers.getIO(), null, new C0315a(addNfcStrapActivity, null), 2, null);
                                }
                            }
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(addNfcStrapActivity), Dispatchers.getMain(), null, new b(addNfcStrapActivity, null), 2, null);
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
        public /* bridge */ /* synthetic */ Unit invoke(ProductDetails productDetails) {
            invoke2(productDetails);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable ProductDetails productDetails) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddNfcStrapActivity.this), Dispatchers.getMain(), null, new a(AddNfcStrapActivity.this, productDetails, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class d extends Lambda implements Function1<List<? extends RegisteredProductInfo>, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddNfcStrapActivity$onCreate$3$1$1", f = "AddNfcStrapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Ref.ObjectRef<RegisteredProductInfo> $registeredProductInfo;
            public int label;
            public final /* synthetic */ AddNfcStrapActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AddNfcStrapActivity addNfcStrapActivity, Ref.ObjectRef<RegisteredProductInfo> objectRef, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = addNfcStrapActivity;
                this.$registeredProductInfo = objectRef;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, this.$registeredProductInfo, continuation);
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
                    Intent intent = new Intent(this.this$0, AddPaymentCardActivity.class);
                    intent.putExtra(Constants.END_USER_GLOBAL_ID, this.this$0.v);
                    intent.putExtra("deviceId", this.this$0.x);
                    RegisteredProductInfo registeredProductInfo = this.$registeredProductInfo.element;
                    Intrinsics.checkNotNull(registeredProductInfo);
                    intent.putExtra(Constants.END_USER_PRODUCT_REGISTRATION_ID, registeredProductInfo.getEndUserProductRegistrationID());
                    intent.putExtra(Constants.SERIAL_NUMBER, this.this$0.t);
                    this.this$0.startActivity(intent);
                    this.this$0.w();
                    this.this$0.finish();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddNfcStrapActivity$onCreate$3$2$1", f = "AddNfcStrapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ AddNfcStrapActivity $this_run;
            public int label;
            public final /* synthetic */ AddNfcStrapActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(AddNfcStrapActivity addNfcStrapActivity, AddNfcStrapActivity addNfcStrapActivity2, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = addNfcStrapActivity;
                this.$this_run = addNfcStrapActivity2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.this$0, this.$this_run, continuation);
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
                    Toast.makeText(this.this$0, this.$this_run.getString(R.string.something_went_wrong), 0).show();
                    LogHelper.d(this.$this_run.getTAG(), "Serial number found null.");
                    this.$this_run.w();
                    this.$this_run.finish();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public d() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends RegisteredProductInfo> list) {
            invoke2((List<RegisteredProductInfo>) list);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Type inference failed for: r11v11, types: [com.coveiot.android.tappy.model.RegisteredProductInfo, T] */
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable List<RegisteredProductInfo> list) {
            Object e;
            if (AddNfcStrapActivity.this.isFinishing()) {
                return;
            }
            String str = AddNfcStrapActivity.this.t;
            if (str != null) {
                AddNfcStrapActivity addNfcStrapActivity = AddNfcStrapActivity.this;
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                if (!(list == null || list.isEmpty())) {
                    objectRef.element = Utils.Companion.getRegisteredProductDetail(list, str);
                }
                if (objectRef.element == 0) {
                    NfcStrapViewModel nfcStrapViewModel = addNfcStrapActivity.q;
                    if (nfcStrapViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        nfcStrapViewModel = null;
                    }
                    Long l = addNfcStrapActivity.v;
                    Intrinsics.checkNotNull(l);
                    long longValue = l.longValue();
                    Long l2 = addNfcStrapActivity.w;
                    Intrinsics.checkNotNull(l2);
                    nfcStrapViewModel.registerNewProduct(longValue, l2.longValue(), str, addNfcStrapActivity.z);
                    e = Unit.INSTANCE;
                } else {
                    LogHelper.d(addNfcStrapActivity.getTAG(), "Product is already registered. Moving for install foundation.");
                    e = kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(addNfcStrapActivity), Dispatchers.getMain(), null, new a(addNfcStrapActivity, objectRef, null), 2, null);
                }
                if (e != null) {
                    return;
                }
            }
            AddNfcStrapActivity addNfcStrapActivity2 = AddNfcStrapActivity.this;
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(addNfcStrapActivity2), Dispatchers.getMain(), null, new b(addNfcStrapActivity2, addNfcStrapActivity2, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class e extends Lambda implements Function1<List<? extends DeviceInfo>, Unit> {
        public static final e INSTANCE = new e();

        public e() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends DeviceInfo> list) {
            invoke2((List<DeviceInfo>) list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable List<DeviceInfo> list) {
        }
    }

    /* loaded from: classes7.dex */
    public static final class f extends Lambda implements Function1<UpdateRegisteredProductResponse, Unit> {
        public f() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$1(AddNfcStrapActivity this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intent intent = new Intent(this$0, AddPaymentCardActivity.class);
            intent.putExtra(Constants.END_USER_GLOBAL_ID, this$0.v);
            intent.putExtra("deviceId", this$0.x);
            intent.putExtra(Constants.END_USER_PRODUCT_REGISTRATION_ID, this$0.A);
            intent.putExtra(Constants.SERIAL_NUMBER, this$0.t);
            this$0.startActivity(intent);
            this$0.w();
            this$0.finish();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$2(AddNfcStrapActivity this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.w();
            this$0.finish();
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(UpdateRegisteredProductResponse updateRegisteredProductResponse) {
            invoke2(updateRegisteredProductResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable UpdateRegisteredProductResponse updateRegisteredProductResponse) {
            Fragment findFragmentById = AddNfcStrapActivity.this.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (findFragmentById != null) {
                AddNfcStrapActivity.this.getSupportFragmentManager().beginTransaction().remove(findFragmentById).commit();
            }
            ActivityAddNfcStrapBinding activityAddNfcStrapBinding = AddNfcStrapActivity.this.p;
            ActivityAddNfcStrapBinding activityAddNfcStrapBinding2 = null;
            if (activityAddNfcStrapBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddNfcStrapBinding = null;
            }
            activityAddNfcStrapBinding.strapAddSuccessStatusLayout.setVisibility(0);
            ActivityAddNfcStrapBinding activityAddNfcStrapBinding3 = AddNfcStrapActivity.this.p;
            if (activityAddNfcStrapBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddNfcStrapBinding3 = null;
            }
            Button button = activityAddNfcStrapBinding3.btnProceedAdd;
            final AddNfcStrapActivity addNfcStrapActivity = AddNfcStrapActivity.this;
            button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.m0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AddNfcStrapActivity.f.invoke$lambda$1(AddNfcStrapActivity.this, view);
                }
            });
            ActivityAddNfcStrapBinding activityAddNfcStrapBinding4 = AddNfcStrapActivity.this.p;
            if (activityAddNfcStrapBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityAddNfcStrapBinding2 = activityAddNfcStrapBinding4;
            }
            TextView textView = activityAddNfcStrapBinding2.addLater;
            final AddNfcStrapActivity addNfcStrapActivity2 = AddNfcStrapActivity.this;
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.l0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AddNfcStrapActivity.f.invoke$lambda$2(AddNfcStrapActivity.this, view);
                }
            });
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddNfcStrapActivity$onNewIntent$1", f = "AddNfcStrapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Intent $intent;
        public int label;

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddNfcStrapActivity$onNewIntent$1$1", f = "AddNfcStrapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ AddNfcStrapActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AddNfcStrapActivity addNfcStrapActivity, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = addNfcStrapActivity;
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
                FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.y.removeCallbacksAndMessages(null);
                    FragmentContactPhoneNfcStrap mFragmentContactPhoneNfcStrap2 = this.this$0.getMFragmentContactPhoneNfcStrap();
                    boolean z = true;
                    if (mFragmentContactPhoneNfcStrap2 == null || !mFragmentContactPhoneNfcStrap2.isAdded()) {
                        z = false;
                    }
                    if (z && (mFragmentContactPhoneNfcStrap = this.this$0.getMFragmentContactPhoneNfcStrap()) != null) {
                        mFragmentContactPhoneNfcStrap.setCurrentState(4);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddNfcStrapActivity$onNewIntent$1$3", f = "AddNfcStrapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ AddNfcStrapActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(AddNfcStrapActivity addNfcStrapActivity, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = addNfcStrapActivity;
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
                    this.this$0.w();
                    this.this$0.finish();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddNfcStrapActivity$onNewIntent$1$4", f = "AddNfcStrapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ AddNfcStrapActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public c(AddNfcStrapActivity addNfcStrapActivity, Continuation<? super c> continuation) {
                super(2, continuation);
                this.this$0 = addNfcStrapActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new c(this.this$0, continuation);
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
                    this.this$0.w();
                    this.this$0.finish();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddNfcStrapActivity$onNewIntent$1$5", f = "AddNfcStrapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ AddNfcStrapActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public d(AddNfcStrapActivity addNfcStrapActivity, Continuation<? super d> continuation) {
                super(2, continuation);
                this.this$0 = addNfcStrapActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new d(this.this$0, continuation);
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
                    AddNfcStrapActivity addNfcStrapActivity = this.this$0;
                    Toast.makeText(addNfcStrapActivity, addNfcStrapActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone), 0).show();
                    this.this$0.w();
                    this.this$0.finish();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddNfcStrapActivity$onNewIntent$1$6", f = "AddNfcStrapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ AddNfcStrapActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public e(AddNfcStrapActivity addNfcStrapActivity, Continuation<? super e> continuation) {
                super(2, continuation);
                this.this$0 = addNfcStrapActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new e(this.this$0, continuation);
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
                    AddNfcStrapActivity addNfcStrapActivity = this.this$0;
                    Toast.makeText(addNfcStrapActivity, addNfcStrapActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone), 0).show();
                    this.this$0.w();
                    this.this$0.finish();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddNfcStrapActivity$onNewIntent$1$7", f = "AddNfcStrapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;
            public final /* synthetic */ AddNfcStrapActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public f(AddNfcStrapActivity addNfcStrapActivity, Continuation<? super f> continuation) {
                super(2, continuation);
                this.this$0 = addNfcStrapActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new f(this.this$0, continuation);
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
                    AddNfcStrapActivity addNfcStrapActivity = this.this$0;
                    Toast.makeText(addNfcStrapActivity, addNfcStrapActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone), 0).show();
                    this.this$0.w();
                    this.this$0.finish();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(Intent intent, Continuation<? super g> continuation) {
            super(2, continuation);
            this.$intent = intent;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new g(this.$intent, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((g) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0091, code lost:
            r12.this$0.t = r0.parseSerialNumber(r1);
            com.coveiot.utils.utility.LogHelper.d(r12.this$0.getTAG(), "SERIAL NUMBER: " + r12.this$0.t);
            r12.this$0.w();
            com.coveiot.utils.utility.LogHelper.d(r12.this$0.getTAG(), "NFC CONNECTION CLOSED!");
            r0 = r12.this$0.t;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x00d0, code lost:
            if (r0 == null) goto L28;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x00d2, code lost:
            r1 = r12.this$0.q;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x00d8, code lost:
            if (r1 != null) goto L26;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x00da, code lost:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            r1 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x00e0, code lost:
            r1.getProductDetailBySerialNumber(r0);
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r13) {
            /*
                Method dump skipped, instructions count: 371
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.activity.AddNfcStrapActivity.g.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.activity.AddNfcStrapActivity$sendApdu$1", f = "AddNfcStrapActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
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
                if (!AddNfcStrapActivity.this.isFinishing()) {
                    AddNfcStrapActivity addNfcStrapActivity = AddNfcStrapActivity.this;
                    Toast.makeText(addNfcStrapActivity, addNfcStrapActivity.getString(R.string.please_make_sure_nfc_strap_and_the_phone), 0).show();
                    AddNfcStrapActivity.this.finish();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes7.dex */
    public static final class i extends Lambda implements Function1<Intent, Unit> {
        public static final i INSTANCE = new i();

        public i() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Intent launchActivity) {
            Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
            launchActivity.addFlags(67108864);
        }
    }

    public static final void A(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void B(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void E(AddNfcStrapActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.w();
        i iVar = i.INSTANCE;
        Intent intent = new Intent(this$0, ActivityRegisteredProductSummary.class);
        iVar.invoke((i) intent);
        this$0.startActivityForResult(intent, -1, null);
    }

    public static final void x(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void y(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void z(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final String C(String str) {
        String str2 = "";
        try {
            String str3 = this.s;
            LogHelper.d(str3, "\n\nSEND: " + str);
            if (str != null) {
                LogHelper.d(this.s, "Sending...");
                str2 = String.valueOf(NfcUtility.Companion.transmit(str));
                LogHelper.d(this.s, "Done...");
                String str4 = this.s;
                LogHelper.d(str4, "RECEIVED: " + str2);
                return str2;
            }
            return "";
        } catch (TagLostException e2) {
            e2.printStackTrace();
            w();
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new h(null), 2, null);
            return str2;
        }
    }

    public final void D() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.setup_nfc_payment));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.activity.c0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddNfcStrapActivity.E(AddNfcStrapActivity.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i2) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            if (findViewById != null) {
                map.put(Integer.valueOf(i2), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Nullable
    public final FragmentContactPhoneNfcStrap getMFragmentContactPhoneNfcStrap() {
        return this.B;
    }

    public final String getTAG() {
        return this.s;
    }

    public final void loadContactPhoneNfcStrapFragment() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
    }

    public final void logError(@NotNull String module, @NotNull String details, @NotNull String severity) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(details, "details");
        Intrinsics.checkNotNullParameter(severity, "severity");
        ErrorLogInfo errorLogInfo = new ErrorLogInfo();
        errorLogInfo.setErrorGUID(UUID.randomUUID().toString());
        errorLogInfo.setDateUTC(AppUtils.formatDateUTC(new Date(), Constants.DATE_FORMAT));
        errorLogInfo.setLogSeverity(severity);
        errorLogInfo.setDetails(details);
        errorLogInfo.setModule(module);
        NfcStrapViewModel nfcStrapViewModel = this.q;
        if (nfcStrapViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            nfcStrapViewModel = null;
        }
        nfcStrapViewModel.logError(errorLogInfo);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityAddNfcStrapBinding inflate = ActivityAddNfcStrapBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ManageViewModel manageViewModel = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        D();
        this.q = (NfcStrapViewModel) new ViewModelProvider(this, new ViewModelFactory(this)).get(NfcStrapViewModel.class);
        this.r = (ManageViewModel) new ViewModelProvider(this, new ViewModelFactory(this)).get(ManageViewModel.class);
        this.v = Long.valueOf(getIntent().getLongExtra(Constants.END_USER_GLOBAL_ID, 0L));
        this.x = getIntent().getLongExtra("deviceId", 0L);
        if (getIntent().hasExtra(Constants.BANK_ID)) {
            Serializable serializableExtra = getIntent().getSerializableExtra(Constants.BANK_ID);
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.android.tappy.model.SupportedBank");
            SupportedBank supportedBank = (SupportedBank) serializableExtra;
        }
        if (getIntent().hasExtra(Constants.FRIENDLY_NAME)) {
            this.z = getIntent().getStringExtra(Constants.FRIENDLY_NAME);
        }
        NfcStrapViewModel nfcStrapViewModel = this.q;
        if (nfcStrapViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            nfcStrapViewModel = null;
        }
        MutableLiveData<RegisterProductResponse> registerProductLiveData = nfcStrapViewModel.getRegisterProductLiveData();
        final b bVar = new b();
        registerProductLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.d0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddNfcStrapActivity.x(Function1.this, obj);
            }
        });
        NfcStrapViewModel nfcStrapViewModel2 = this.q;
        if (nfcStrapViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            nfcStrapViewModel2 = null;
        }
        MutableLiveData<ProductDetails> productDetailsLiveData = nfcStrapViewModel2.getProductDetailsLiveData();
        final c cVar = new c();
        productDetailsLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.g0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddNfcStrapActivity.y(Function1.this, obj);
            }
        });
        NfcStrapViewModel nfcStrapViewModel3 = this.q;
        if (nfcStrapViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            nfcStrapViewModel3 = null;
        }
        MutableLiveData<List<RegisteredProductInfo>> registerProductsByUserIdLiveData = nfcStrapViewModel3.getRegisterProductsByUserIdLiveData();
        final d dVar = new d();
        registerProductsByUserIdLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.e0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddNfcStrapActivity.z(Function1.this, obj);
            }
        });
        NfcStrapViewModel nfcStrapViewModel4 = this.q;
        if (nfcStrapViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            nfcStrapViewModel4 = null;
        }
        MutableLiveData<List<DeviceInfo>> getAllRegisteredDevicesLiveData = nfcStrapViewModel4.getGetAllRegisteredDevicesLiveData();
        if (getAllRegisteredDevicesLiveData != null) {
            final e eVar = e.INSTANCE;
            getAllRegisteredDevicesLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.h0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    AddNfcStrapActivity.A(Function1.this, obj);
                }
            });
        }
        ManageViewModel manageViewModel2 = this.r;
        if (manageViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
        } else {
            manageViewModel = manageViewModel2;
        }
        MutableLiveData<UpdateRegisteredProductResponse> updateRegisteredProductLiveData = manageViewModel.getUpdateRegisteredProductLiveData();
        if (updateRegisteredProductLiveData != null) {
            final f fVar = new f();
            updateRegisteredProductLiveData.observe(this, new Observer() { // from class: com.coveiot.android.tappy.activity.f0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    AddNfcStrapActivity.B(Function1.this, obj);
                }
            });
        }
        loadContactPhoneNfcStrapFragment();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        w();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(@NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        if (Intrinsics.areEqual("android.nfc.action.TECH_DISCOVERED", intent.getAction())) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new g(intent, null), 2, null);
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.u) {
            return;
        }
        this.u = true;
        NfcUtility.Companion.startListeningNFC(this);
    }

    @Override // com.coveiot.android.tappy.fragment.FragmentContactPhoneNfcStrap.OnTryAgainClickListener
    public void onTryAgainClicked() {
        loadContactPhoneNfcStrapFragment();
    }

    public final void setMFragmentContactPhoneNfcStrap(@Nullable FragmentContactPhoneNfcStrap fragmentContactPhoneNfcStrap) {
        this.B = fragmentContactPhoneNfcStrap;
    }

    public final void w() {
        if (this.u) {
            this.u = false;
            boolean stopListeningNFC = NfcUtility.Companion.stopListeningNFC();
            String str = this.s;
            LogHelper.d(str, "NFC STOPPED LISTENING " + stopListeningNFC);
        }
    }
}
