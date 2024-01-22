package com.coveiot.android.tappy.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary;
import com.coveiot.android.tappy.activity.AddPaymentCardActivity;
import com.coveiot.android.tappy.activity.TermsAndConditionsActivity;
import com.coveiot.android.tappy.adapter.TransactionHistoryAdapter;
import com.coveiot.android.tappy.customview.CVVInputDialog;
import com.coveiot.android.tappy.customview.CardActionConfirmationDialog;
import com.coveiot.android.tappy.customview.ErrorDialog;
import com.coveiot.android.tappy.customview.FriendlyNameDialog;
import com.coveiot.android.tappy.customview.TransactionDetailDialog;
import com.coveiot.android.tappy.databinding.FragmentRegisteredProductItemNewBinding;
import com.coveiot.android.tappy.model.DeletePaymentInstrumentTokenResponse;
import com.coveiot.android.tappy.model.GetTransactionDetailsByIdResponse;
import com.coveiot.android.tappy.model.GetTransactionDetailsResponse;
import com.coveiot.android.tappy.model.PaymentInstrumentTokenStatus;
import com.coveiot.android.tappy.model.RegCardBeanInfo;
import com.coveiot.android.tappy.model.RegStrapBeanInfo;
import com.coveiot.android.tappy.model.TransactionBeanInfo;
import com.coveiot.android.tappy.model.UpdateRegisteredProductResponse;
import com.coveiot.android.tappy.nfc.NfcUtility;
import com.coveiot.android.tappy.utils.Constants;
import com.coveiot.android.tappy.utils.LocalAPDUCommands;
import com.coveiot.android.tappy.viewmodel.ManageViewModel;
import com.coveiot.android.tappy.viewmodel.NfcStrapViewModel;
import com.coveiot.android.tappy.viewmodel.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class FragmentRegisteredProductItem extends BaseFragment implements TransactionHistoryAdapter.ItemClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public TextView A;
    @Nullable
    public TransactionHistoryAdapter B;
    public ManageViewModel C;
    @Nullable
    public CardActionConfirmationDialog E;
    @Nullable
    public ErrorDialog F;
    @Nullable
    public CVVInputDialog G;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle H;
    @Nullable
    public FriendlyNameDialog I;
    @Nullable
    public TransactionDetailDialog J;
    @Nullable
    public FragmentRegisteredProductItemNewBinding m;
    @Nullable
    public ConstraintLayout n;
    @Nullable
    public CardView o;
    @Nullable
    public ConstraintLayout p;
    @Nullable
    public Button q;
    @Nullable
    public ImageView r;
    public RegStrapBeanInfo regStrapBeanInfo;
    @Nullable
    public ImageButton s;
    @Nullable
    public TextView t;
    @Nullable
    public ConstraintLayout u;
    @Nullable
    public RecyclerView v;
    @Nullable
    public ImageView w;
    @Nullable
    public TextView x;
    @Nullable
    public TextView y;
    @Nullable
    public TextView z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final ArrayList<TransactionBeanInfo> D = new ArrayList<>();

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentRegisteredProductItem newInstance(@NotNull RegStrapBeanInfo regStrapBeanInfo) {
            Intrinsics.checkNotNullParameter(regStrapBeanInfo, "regStrapBeanInfo");
            FragmentRegisteredProductItem fragmentRegisteredProductItem = new FragmentRegisteredProductItem();
            fragmentRegisteredProductItem.setRegStrapBeanInfo(regStrapBeanInfo);
            return fragmentRegisteredProductItem;
        }
    }

    /* loaded from: classes7.dex */
    public static final class a extends Lambda implements Function1<UpdateRegisteredProductResponse, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem$onViewCreated$2$1", f = "FragmentRegisteredProductItem.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem$a$a  reason: collision with other inner class name */
        /* loaded from: classes7.dex */
        public static final class C0324a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ UpdateRegisteredProductResponse $it;
            public int label;
            public final /* synthetic */ FragmentRegisteredProductItem this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0324a(FragmentRegisteredProductItem fragmentRegisteredProductItem, UpdateRegisteredProductResponse updateRegisteredProductResponse, Continuation<? super C0324a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentRegisteredProductItem;
                this.$it = updateRegisteredProductResponse;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0324a(this.this$0, this.$it, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0324a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.dismissProgress();
                    if (this.$it == null) {
                        Toast.makeText(this.this$0.requireActivity(), this.this$0.getString(R.string.some_error_occurred_try_again), 1).show();
                    } else {
                        Toast.makeText(this.this$0.requireActivity(), this.this$0.getString(R.string.friendly_name_updated_successfully), 1).show();
                        if (this.this$0.getActivity() instanceof ActivityRegisteredProductSummary) {
                            FragmentActivity activity = this.this$0.getActivity();
                            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.ActivityRegisteredProductSummary");
                            ((ActivityRegisteredProductSummary) activity).refresh();
                        }
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
        public /* bridge */ /* synthetic */ Unit invoke(UpdateRegisteredProductResponse updateRegisteredProductResponse) {
            invoke2(updateRegisteredProductResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable UpdateRegisteredProductResponse updateRegisteredProductResponse) {
            if (FragmentRegisteredProductItem.this.isAdded()) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentRegisteredProductItem.this), Dispatchers.getMain(), null, new C0324a(FragmentRegisteredProductItem.this, updateRegisteredProductResponse, null), 2, null);
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class b extends Lambda implements Function1<GetTransactionDetailsByIdResponse, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem$onViewCreated$3$1", f = "FragmentRegisteredProductItem.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ GetTransactionDetailsByIdResponse $it;
            public int label;
            public final /* synthetic */ FragmentRegisteredProductItem this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentRegisteredProductItem fragmentRegisteredProductItem, GetTransactionDetailsByIdResponse getTransactionDetailsByIdResponse, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentRegisteredProductItem;
                this.$it = getTransactionDetailsByIdResponse;
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
                    if (this.this$0.isAdded()) {
                        this.this$0.dismissProgress();
                        GetTransactionDetailsByIdResponse getTransactionDetailsByIdResponse = this.$it;
                        if (getTransactionDetailsByIdResponse != null) {
                            this.this$0.m0(getTransactionDetailsByIdResponse);
                        } else {
                            Toast.makeText(this.this$0.requireActivity(), this.this$0.getString(R.string.some_error_occurred_try_again), 1).show();
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
        public /* bridge */ /* synthetic */ Unit invoke(GetTransactionDetailsByIdResponse getTransactionDetailsByIdResponse) {
            invoke2(getTransactionDetailsByIdResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable GetTransactionDetailsByIdResponse getTransactionDetailsByIdResponse) {
            LifecycleCoroutineScope lifecycleScope = LifecycleOwnerKt.getLifecycleScope(FragmentRegisteredProductItem.this);
            if (lifecycleScope != null) {
                kotlinx.coroutines.e.e(lifecycleScope, Dispatchers.getMain(), null, new a(FragmentRegisteredProductItem.this, getTransactionDetailsByIdResponse, null), 2, null);
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class c extends Lambda implements Function1<DeletePaymentInstrumentTokenResponse, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem$onViewCreated$4$1", f = "FragmentRegisteredProductItem.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ DeletePaymentInstrumentTokenResponse $it;
            public int label;
            public final /* synthetic */ FragmentRegisteredProductItem this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentRegisteredProductItem fragmentRegisteredProductItem, DeletePaymentInstrumentTokenResponse deletePaymentInstrumentTokenResponse, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentRegisteredProductItem;
                this.$it = deletePaymentInstrumentTokenResponse;
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
                RegCardBeanInfo regCardBeanInfo;
                RegCardBeanInfo regCardBeanInfo2;
                RegCardBeanInfo regCardBeanInfo3;
                RegCardBeanInfo regCardBeanInfo4;
                RegCardBeanInfo regCardBeanInfo5;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.dismissProgress();
                    if (this.$it != null) {
                        Intent intent = new Intent(this.this$0.requireActivity(), AddPaymentCardActivity.class);
                        intent.putExtra(Constants.END_USER_GLOBAL_ID, this.this$0.getRegStrapBeanInfo().getEndUserID());
                        RegStrapBeanInfo regStrapBeanInfo = this.this$0.getRegStrapBeanInfo();
                        intent.putExtra("deviceId", regStrapBeanInfo != null ? regStrapBeanInfo.getDeviceId() : null);
                        RegStrapBeanInfo regStrapBeanInfo2 = this.this$0.getRegStrapBeanInfo();
                        intent.putExtra(Constants.END_USER_PRODUCT_REGISTRATION_ID, regStrapBeanInfo2 != null ? regStrapBeanInfo2.getEndUserProductRegistrationID() : null);
                        RegStrapBeanInfo regStrapBeanInfo3 = this.this$0.getRegStrapBeanInfo();
                        intent.putExtra(Constants.SERIAL_NUMBER, regStrapBeanInfo3 != null ? regStrapBeanInfo3.getProductSerialNumber() : null);
                        RegStrapBeanInfo regStrapBeanInfo4 = this.this$0.getRegStrapBeanInfo();
                        intent.putExtra(Constants.PAYMENT_INSTRUMENT_ID, (regStrapBeanInfo4 == null || (regCardBeanInfo5 = regStrapBeanInfo4.getRegCardBeanInfo()) == null) ? null : regCardBeanInfo5.getPaymentInstrumentId());
                        RegStrapBeanInfo regStrapBeanInfo5 = this.this$0.getRegStrapBeanInfo();
                        intent.putExtra(Constants.PAYMENT_NETWORK_ID, (regStrapBeanInfo5 == null || (regCardBeanInfo4 = regStrapBeanInfo5.getRegCardBeanInfo()) == null) ? null : regCardBeanInfo4.getPaymentNetworkId());
                        RegStrapBeanInfo regStrapBeanInfo6 = this.this$0.getRegStrapBeanInfo();
                        intent.putExtra(Constants.CARD_ART_URL, (regStrapBeanInfo6 == null || (regCardBeanInfo3 = regStrapBeanInfo6.getRegCardBeanInfo()) == null) ? null : regCardBeanInfo3.getCardArtImageUrl());
                        RegStrapBeanInfo regStrapBeanInfo7 = this.this$0.getRegStrapBeanInfo();
                        intent.putExtra(Constants.LAST4, (regStrapBeanInfo7 == null || (regCardBeanInfo2 = regStrapBeanInfo7.getRegCardBeanInfo()) == null) ? null : regCardBeanInfo2.getLast4());
                        RegStrapBeanInfo regStrapBeanInfo8 = this.this$0.getRegStrapBeanInfo();
                        boolean z = false;
                        if (regStrapBeanInfo8 != null && (regCardBeanInfo = regStrapBeanInfo8.getRegCardBeanInfo()) != null) {
                            Integer paymentNetworkId = regCardBeanInfo.getPaymentNetworkId();
                            int i = LocalAPDUCommands.PAYMENTNETWORK_MASTERCARD;
                            if (paymentNetworkId != null && paymentNetworkId.intValue() == i) {
                                z = true;
                            }
                        }
                        if (z) {
                            this.this$0.h0(null, intent);
                        } else {
                            this.this$0.startActivity(intent);
                        }
                    } else {
                        FragmentRegisteredProductItem fragmentRegisteredProductItem = this.this$0;
                        String string = fragmentRegisteredProductItem.getString(R.string.some_error_occurred_try_again);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_error_occurred_try_again)");
                        fragmentRegisteredProductItem.d0(string);
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
        public /* bridge */ /* synthetic */ Unit invoke(DeletePaymentInstrumentTokenResponse deletePaymentInstrumentTokenResponse) {
            invoke2(deletePaymentInstrumentTokenResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable DeletePaymentInstrumentTokenResponse deletePaymentInstrumentTokenResponse) {
            LifecycleCoroutineScope lifecycleScope = LifecycleOwnerKt.getLifecycleScope(FragmentRegisteredProductItem.this);
            if (lifecycleScope != null) {
                kotlinx.coroutines.e.e(lifecycleScope, Dispatchers.getMain(), null, new a(FragmentRegisteredProductItem.this, deletePaymentInstrumentTokenResponse, null), 2, null);
            }
        }
    }

    @DebugMetadata(c = "com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem$onViewDetail$1", f = "FragmentRegisteredProductItem.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes7.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ TransactionBeanInfo $transactionBeanInfo;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(TransactionBeanInfo transactionBeanInfo, Continuation<? super d> continuation) {
            super(2, continuation);
            this.$transactionBeanInfo = transactionBeanInfo;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d(this.$transactionBeanInfo, continuation);
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
                if (FragmentRegisteredProductItem.this.getRegStrapBeanInfo() != null && FragmentRegisteredProductItem.this.getRegStrapBeanInfo().getRegCardBeanInfo() != null) {
                    ManageViewModel manageViewModel = FragmentRegisteredProductItem.this.C;
                    if (manageViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                        manageViewModel = null;
                    }
                    ManageViewModel manageViewModel2 = manageViewModel;
                    RegStrapBeanInfo regStrapBeanInfo = FragmentRegisteredProductItem.this.getRegStrapBeanInfo();
                    Intrinsics.checkNotNull(regStrapBeanInfo);
                    Long endUserID = regStrapBeanInfo.getEndUserID();
                    Intrinsics.checkNotNull(endUserID);
                    long longValue = endUserID.longValue();
                    RegStrapBeanInfo regStrapBeanInfo2 = FragmentRegisteredProductItem.this.getRegStrapBeanInfo();
                    Intrinsics.checkNotNull(regStrapBeanInfo2);
                    RegCardBeanInfo regCardBeanInfo = regStrapBeanInfo2.getRegCardBeanInfo();
                    Intrinsics.checkNotNull(regCardBeanInfo);
                    Long paymentInstrumentTokenId = regCardBeanInfo.getPaymentInstrumentTokenId();
                    Intrinsics.checkNotNull(paymentInstrumentTokenId);
                    long longValue2 = paymentInstrumentTokenId.longValue();
                    TransactionBeanInfo transactionBeanInfo = this.$transactionBeanInfo;
                    Intrinsics.checkNotNull(transactionBeanInfo);
                    Long transactionId = transactionBeanInfo.getTransactionId();
                    Intrinsics.checkNotNull(transactionId);
                    manageViewModel2.getTransactionDetailById(longValue, longValue2, transactionId.longValue());
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void G(String pdfUrl, final FragmentRegisteredProductItem this$0) {
        final Bitmap a2;
        Intrinsics.checkNotNullParameter(pdfUrl, "$pdfUrl");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            URLConnection openConnection = new URL(pdfUrl).openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            File createTempFile = File.createTempFile("temp_pdf", ".pdf");
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            inputStream.close();
            fileOutputStream.close();
            httpURLConnection.disconnect();
            String absolutePath = createTempFile.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "tempFile.absolutePath");
            a2 = FragmentRegisteredProductItemKt.a(absolutePath);
            if (a2 != null) {
                this$0.requireActivity().runOnUiThread(new Runnable() { // from class: com.coveiot.android.tappy.fragment.w0
                    @Override // java.lang.Runnable
                    public final void run() {
                        FragmentRegisteredProductItem.H(FragmentRegisteredProductItem.this, a2);
                    }
                });
            } else {
                this$0.requireActivity().runOnUiThread(new Runnable() { // from class: com.coveiot.android.tappy.fragment.v0
                    @Override // java.lang.Runnable
                    public final void run() {
                        FragmentRegisteredProductItem.I(FragmentRegisteredProductItem.this);
                    }
                });
            }
            createTempFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final void H(FragmentRegisteredProductItem this$0, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Glide.with(this$0.requireContext()).m24load(bitmap).into(this$0.K().imageViewHolder);
        this$0.K().clCardNumber.setVisibility(0);
        this$0.dismissProgress();
    }

    public static final void I(FragmentRegisteredProductItem this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.K().imageViewHolder.setImageResource(R.drawable.img_empty_card_bg);
    }

    public static final void J() {
    }

    public static final void M(FragmentRegisteredProductItem this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RegStrapBeanInfo regStrapBeanInfo = this$0.getRegStrapBeanInfo();
        Long endUserID = regStrapBeanInfo != null ? regStrapBeanInfo.getEndUserID() : null;
        Intrinsics.checkNotNull(endUserID);
        long longValue = endUserID.longValue();
        RegStrapBeanInfo regStrapBeanInfo2 = this$0.getRegStrapBeanInfo();
        Long endUserProductRegistrationID = regStrapBeanInfo2 != null ? regStrapBeanInfo2.getEndUserProductRegistrationID() : null;
        Intrinsics.checkNotNull(endUserProductRegistrationID);
        this$0.f0(longValue, endUserProductRegistrationID.longValue());
    }

    public static final void O(FragmentRegisteredProductItem this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RegStrapBeanInfo regStrapBeanInfo = this$0.getRegStrapBeanInfo();
        Long endUserID = regStrapBeanInfo != null ? regStrapBeanInfo.getEndUserID() : null;
        Intrinsics.checkNotNull(endUserID);
        long longValue = endUserID.longValue();
        RegStrapBeanInfo regStrapBeanInfo2 = this$0.getRegStrapBeanInfo();
        Long endUserProductRegistrationID = regStrapBeanInfo2 != null ? regStrapBeanInfo2.getEndUserProductRegistrationID() : null;
        Intrinsics.checkNotNull(endUserProductRegistrationID);
        this$0.f0(longValue, endUserProductRegistrationID.longValue());
    }

    public static final void Q(FragmentRegisteredProductItem this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RegStrapBeanInfo regStrapBeanInfo = this$0.getRegStrapBeanInfo();
        Long endUserID = regStrapBeanInfo != null ? regStrapBeanInfo.getEndUserID() : null;
        Intrinsics.checkNotNull(endUserID);
        long longValue = endUserID.longValue();
        RegStrapBeanInfo regStrapBeanInfo2 = this$0.getRegStrapBeanInfo();
        Long endUserProductRegistrationID = regStrapBeanInfo2 != null ? regStrapBeanInfo2.getEndUserProductRegistrationID() : null;
        Intrinsics.checkNotNull(endUserProductRegistrationID);
        this$0.f0(longValue, endUserProductRegistrationID.longValue());
    }

    public static final void R(FragmentRegisteredProductItem this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0.requireContext())) {
            NfcUtility.Companion companion = NfcUtility.Companion;
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            if (companion.hasNFC(requireActivity)) {
                FragmentActivity requireActivity2 = this$0.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                if (companion.isNFCOn(requireActivity2)) {
                    Intent intent = new Intent(this$0.requireActivity(), AddPaymentCardActivity.class);
                    intent.putExtra(Constants.END_USER_GLOBAL_ID, this$0.getRegStrapBeanInfo().getEndUserID());
                    RegStrapBeanInfo regStrapBeanInfo = this$0.getRegStrapBeanInfo();
                    intent.putExtra("deviceId", regStrapBeanInfo != null ? regStrapBeanInfo.getDeviceId() : null);
                    RegStrapBeanInfo regStrapBeanInfo2 = this$0.getRegStrapBeanInfo();
                    intent.putExtra(Constants.END_USER_PRODUCT_REGISTRATION_ID, regStrapBeanInfo2 != null ? regStrapBeanInfo2.getEndUserProductRegistrationID() : null);
                    RegStrapBeanInfo regStrapBeanInfo3 = this$0.getRegStrapBeanInfo();
                    intent.putExtra(Constants.SERIAL_NUMBER, regStrapBeanInfo3 != null ? regStrapBeanInfo3.getProductSerialNumber() : null);
                    this$0.startActivity(intent);
                    return;
                }
                this$0.k0();
                return;
            }
            String string = this$0.getString(R.string.nfc_not_supported);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.nfc_not_supported)");
            this$0.d0(string);
            return;
        }
        this$0.showNoInternetMessage();
    }

    public static final void T(FragmentRegisteredProductItem this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RegStrapBeanInfo regStrapBeanInfo = this$0.getRegStrapBeanInfo();
        Long endUserID = regStrapBeanInfo != null ? regStrapBeanInfo.getEndUserID() : null;
        Intrinsics.checkNotNull(endUserID);
        long longValue = endUserID.longValue();
        RegStrapBeanInfo regStrapBeanInfo2 = this$0.getRegStrapBeanInfo();
        Long endUserProductRegistrationID = regStrapBeanInfo2 != null ? regStrapBeanInfo2.getEndUserProductRegistrationID() : null;
        Intrinsics.checkNotNull(endUserProductRegistrationID);
        this$0.f0(longValue, endUserProductRegistrationID.longValue());
    }

    public static final void V(FragmentRegisteredProductItem this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RegStrapBeanInfo regStrapBeanInfo = this$0.getRegStrapBeanInfo();
        Long endUserID = regStrapBeanInfo != null ? regStrapBeanInfo.getEndUserID() : null;
        Intrinsics.checkNotNull(endUserID);
        long longValue = endUserID.longValue();
        RegStrapBeanInfo regStrapBeanInfo2 = this$0.getRegStrapBeanInfo();
        Long endUserProductRegistrationID = regStrapBeanInfo2 != null ? regStrapBeanInfo2.getEndUserProductRegistrationID() : null;
        Intrinsics.checkNotNull(endUserProductRegistrationID);
        this$0.f0(longValue, endUserProductRegistrationID.longValue());
    }

    public static final void Y(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void Z(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void a0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void c0(FragmentRegisteredProductItem this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RequestManager with = Glide.with(this$0.requireContext());
        RegCardBeanInfo regCardBeanInfo = this$0.getRegStrapBeanInfo().getRegCardBeanInfo();
        String cardArtImageUrl = regCardBeanInfo != null ? regCardBeanInfo.getCardArtImageUrl() : null;
        Intrinsics.checkNotNull(cardArtImageUrl);
        with.m30load(cardArtImageUrl).into(this$0.K().imageViewHolder);
        this$0.K().clCardNumber.setVisibility(0);
        this$0.dismissProgress();
    }

    public static final void e0(FragmentRegisteredProductItem this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ErrorDialog errorDialog = this$0.F;
        Intrinsics.checkNotNull(errorDialog);
        errorDialog.dismiss();
    }

    public static final void g0(EditText editText, FragmentRegisteredProductItem this$0, TextView textView, long j, long j2, View view) {
        ManageViewModel manageViewModel;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Editable text = editText.getText();
        if (text == null || text.length() == 0) {
            Toast.makeText(this$0.requireActivity(), textView.getText().toString(), 1).show();
            return;
        }
        FriendlyNameDialog friendlyNameDialog = this$0.I;
        Intrinsics.checkNotNull(friendlyNameDialog);
        friendlyNameDialog.dismiss();
        BaseFragment.showProgress$default(this$0, false, 1, null);
        ManageViewModel manageViewModel2 = this$0.C;
        if (manageViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
            manageViewModel = null;
        } else {
            manageViewModel = manageViewModel2;
        }
        manageViewModel.updateRegisteredProductFriendlyName(editText.getText().toString(), j, j2);
    }

    public static final void i0(EditText editText, FragmentRegisteredProductItem this$0, TextView textView, Intent intent, String str, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Editable text = editText.getText();
        if (text == null || text.length() == 0) {
            Toast.makeText(this$0.requireActivity(), textView.getText().toString(), 1).show();
            return;
        }
        if (intent == null) {
            this$0.X(str, editText.getText().toString());
        } else {
            intent.putExtra(Constants.SECURITY_CODE, editText.getText().toString());
            this$0.startActivity(intent);
        }
        CVVInputDialog cVVInputDialog = this$0.G;
        Intrinsics.checkNotNull(cVVInputDialog);
        cVVInputDialog.dismiss();
    }

    public static final void j0(FragmentRegisteredProductItem this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CVVInputDialog cVVInputDialog = this$0.G;
        Intrinsics.checkNotNull(cVVInputDialog);
        cVVInputDialog.dismiss();
    }

    public static final void l0(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, FragmentRegisteredProductItem this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.startActivity(new Intent("android.settings.NFC_SETTINGS"));
    }

    public static final void n0(FragmentRegisteredProductItem this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TransactionDetailDialog transactionDetailDialog = this$0.J;
        Intrinsics.checkNotNull(transactionDetailDialog);
        transactionDetailDialog.dismiss();
    }

    @JvmStatic
    @NotNull
    public static final FragmentRegisteredProductItem newInstance(@NotNull RegStrapBeanInfo regStrapBeanInfo) {
        return Companion.newInstance(regStrapBeanInfo);
    }

    public static final void p0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void F(final String str) {
        if (kotlin.text.m.endsWith(str, ".pdf", true)) {
            new Thread(new Runnable() { // from class: com.coveiot.android.tappy.fragment.x0
                @Override // java.lang.Runnable
                public final void run() {
                    FragmentRegisteredProductItem.G(str, this);
                }
            }).start();
            return;
        }
        requireActivity().runOnUiThread(new Runnable() { // from class: com.coveiot.android.tappy.fragment.z0
            @Override // java.lang.Runnable
            public final void run() {
                FragmentRegisteredProductItem.J();
            }
        });
        K().imageViewHolder.setImageResource(R.drawable.img_empty_card_bg);
    }

    public final FragmentRegisteredProductItemNewBinding K() {
        FragmentRegisteredProductItemNewBinding fragmentRegisteredProductItemNewBinding = this.m;
        Intrinsics.checkNotNull(fragmentRegisteredProductItemNewBinding);
        return fragmentRegisteredProductItemNewBinding;
    }

    public final void L() {
        ConstraintLayout constraintLayout = this.p;
        if (constraintLayout != null) {
            constraintLayout.setVisibility(0);
        }
        CardView cardView = this.o;
        if (cardView != null) {
            cardView.setVisibility(0);
        }
        Button button = this.q;
        if (button != null) {
            button.setVisibility(8);
        }
        ConstraintLayout constraintLayout2 = this.n;
        if (constraintLayout2 != null) {
            constraintLayout2.setAlpha(0.5f);
        }
        ImageButton imageButton = this.s;
        if (imageButton != null) {
            imageButton.setAlpha(1.0f);
        }
        b0();
        ImageButton imageButton2 = this.s;
        if (imageButton2 != null) {
            imageButton2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.o0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentRegisteredProductItem.M(FragmentRegisteredProductItem.this, view);
                }
            });
        }
    }

    public final void N() {
        CardView cardView = this.o;
        if (cardView != null) {
            cardView.setVisibility(0);
        }
        ConstraintLayout constraintLayout = this.p;
        if (constraintLayout != null) {
            constraintLayout.setVisibility(0);
        }
        Button button = this.q;
        if (button != null) {
            button.setVisibility(8);
        }
        ConstraintLayout constraintLayout2 = this.n;
        if (constraintLayout2 != null) {
            constraintLayout2.setAlpha(0.5f);
        }
        ImageButton imageButton = this.s;
        if (imageButton != null) {
            imageButton.setAlpha(1.0f);
        }
        b0();
        ImageButton imageButton2 = this.s;
        if (imageButton2 != null) {
            imageButton2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.b1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentRegisteredProductItem.O(FragmentRegisteredProductItem.this, view);
                }
            });
        }
    }

    public final void P() {
        ConstraintLayout constraintLayout = this.p;
        if (constraintLayout != null) {
            constraintLayout.setVisibility(8);
        }
        CardView cardView = this.o;
        if (cardView != null) {
            cardView.setVisibility(8);
        }
        Button button = this.q;
        if (button != null) {
            button.setVisibility(0);
        }
        ConstraintLayout constraintLayout2 = this.n;
        if (constraintLayout2 != null) {
            constraintLayout2.setAlpha(1.0f);
        }
        ImageButton imageButton = this.s;
        if (imageButton != null) {
            imageButton.setAlpha(1.0f);
        }
        ImageButton imageButton2 = this.s;
        if (imageButton2 != null) {
            imageButton2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.e1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentRegisteredProductItem.Q(FragmentRegisteredProductItem.this, view);
                }
            });
        }
        Button button2 = this.q;
        if (button2 != null) {
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.f1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentRegisteredProductItem.R(FragmentRegisteredProductItem.this, view);
                }
            });
        }
    }

    public final void S() {
        ConstraintLayout constraintLayout = this.p;
        if (constraintLayout != null) {
            constraintLayout.setVisibility(0);
        }
        CardView cardView = this.o;
        if (cardView != null) {
            cardView.setVisibility(0);
        }
        Button button = this.q;
        if (button != null) {
            button.setVisibility(8);
        }
        ConstraintLayout constraintLayout2 = this.n;
        if (constraintLayout2 != null) {
            constraintLayout2.setAlpha(0.5f);
        }
        ImageButton imageButton = this.s;
        if (imageButton != null) {
            imageButton.setAlpha(1.0f);
        }
        b0();
        ImageButton imageButton2 = this.s;
        if (imageButton2 != null) {
            imageButton2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.h1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentRegisteredProductItem.T(FragmentRegisteredProductItem.this, view);
                }
            });
        }
    }

    public final void U() {
        CardView cardView = this.o;
        if (cardView != null) {
            cardView.setVisibility(0);
        }
        ConstraintLayout constraintLayout = this.p;
        if (constraintLayout != null) {
            constraintLayout.setVisibility(0);
        }
        Button button = this.q;
        if (button != null) {
            button.setVisibility(8);
        }
        ConstraintLayout constraintLayout2 = this.n;
        if (constraintLayout2 != null) {
            constraintLayout2.setAlpha(1.0f);
        }
        ImageButton imageButton = this.s;
        if (imageButton != null) {
            imageButton.setAlpha(1.0f);
        }
        b0();
        ImageButton imageButton2 = this.s;
        if (imageButton2 != null) {
            imageButton2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.c1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentRegisteredProductItem.V(FragmentRegisteredProductItem.this, view);
                }
            });
        }
        o0();
    }

    public final void W(View view) {
        this.n = (ConstraintLayout) view.findViewById(R.id.cv_productDetail);
        CardView cardView = (CardView) view.findViewById(R.id.cv_strapDetail);
        this.o = (CardView) view.findViewById(R.id.cv_virtualCard);
        this.p = (ConstraintLayout) view.findViewById(R.id.cv_cardDetail);
        this.q = (Button) view.findViewById(R.id.btn_addCard);
        this.r = (ImageView) view.findViewById(R.id.imageViewHolder);
        this.s = (ImageButton) view.findViewById(R.id.imgv_edtFriendlyName);
        this.w = (ImageView) view.findViewById(R.id.imgvStrap);
        this.x = (TextView) view.findViewById(R.id.tvStrapName);
        this.y = (TextView) view.findViewById(R.id.tvStrapAddDetails);
        this.z = (TextView) view.findViewById(R.id.tv_dynamicAccountNumber);
        this.t = (TextView) view.findViewById(R.id.tv_last4DynamicNumber);
        this.A = (TextView) view.findViewById(R.id.tv_virtualCardStatus);
        CardView cardView2 = this.o;
        if (cardView2 != null) {
            cardView2.setVisibility(8);
        }
        ConstraintLayout constraintLayout = this.p;
        if (constraintLayout != null) {
            constraintLayout.setVisibility(8);
        }
        Button button = this.q;
        if (button != null) {
            button.setVisibility(8);
        }
        RecyclerView recyclerView = this.v;
        if (recyclerView == null) {
            return;
        }
        recyclerView.setVisibility(8);
    }

    public final void X(String str, String str2) {
        RegCardBeanInfo regCardBeanInfo;
        RegCardBeanInfo regCardBeanInfo2;
        RegCardBeanInfo regCardBeanInfo3;
        RegCardBeanInfo regCardBeanInfo4;
        NfcUtility.Companion companion = NfcUtility.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        if (companion.hasNFC(requireActivity)) {
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            if (companion.isNFCOn(requireActivity2)) {
                Intent intent = new Intent(requireActivity(), TermsAndConditionsActivity.class);
                RegStrapBeanInfo regStrapBeanInfo = getRegStrapBeanInfo();
                Integer num = null;
                intent.putExtra(Constants.END_USER_GLOBAL_ID, regStrapBeanInfo != null ? regStrapBeanInfo.getEndUserID() : null);
                RegStrapBeanInfo regStrapBeanInfo2 = getRegStrapBeanInfo();
                intent.putExtra(Constants.END_USER_PRODUCT_REGISTRATION_ID, regStrapBeanInfo2 != null ? regStrapBeanInfo2.getEndUserProductRegistrationID() : null);
                intent.putExtra(Constants.TERMS_AND_CONDITIONS_TEXT, str);
                RegStrapBeanInfo regStrapBeanInfo3 = getRegStrapBeanInfo();
                intent.putExtra(Constants.TERMS_AND_CONDITIONS_ID, (regStrapBeanInfo3 == null || (regCardBeanInfo4 = regStrapBeanInfo3.getRegCardBeanInfo()) == null) ? null : regCardBeanInfo4.getTermsAndConditionId());
                RegStrapBeanInfo regStrapBeanInfo4 = getRegStrapBeanInfo();
                intent.putExtra("deviceId", regStrapBeanInfo4 != null ? regStrapBeanInfo4.getDeviceId() : null);
                RegStrapBeanInfo regStrapBeanInfo5 = getRegStrapBeanInfo();
                intent.putExtra(Constants.PAYMENT_INSTRUMENT_TOKEN_ID, (regStrapBeanInfo5 == null || (regCardBeanInfo3 = regStrapBeanInfo5.getRegCardBeanInfo()) == null) ? null : regCardBeanInfo3.getPaymentInstrumentTokenId());
                intent.putExtra(Constants.CVV, str2);
                RegStrapBeanInfo regStrapBeanInfo6 = getRegStrapBeanInfo();
                intent.putExtra(Constants.SERIAL_NUMBER, regStrapBeanInfo6 != null ? regStrapBeanInfo6.getProductSerialNumber() : null);
                RegStrapBeanInfo regStrapBeanInfo7 = getRegStrapBeanInfo();
                intent.putExtra(Constants.LAST4, (regStrapBeanInfo7 == null || (regCardBeanInfo2 = regStrapBeanInfo7.getRegCardBeanInfo()) == null) ? null : regCardBeanInfo2.getLast4());
                RegStrapBeanInfo regStrapBeanInfo8 = getRegStrapBeanInfo();
                if (regStrapBeanInfo8 != null && (regCardBeanInfo = regStrapBeanInfo8.getRegCardBeanInfo()) != null) {
                    num = regCardBeanInfo.getPaymentNetworkId();
                }
                intent.putExtra(Constants.PAYMENT_NETWORK_ID, num);
                startActivity(intent);
                return;
            }
            k0();
            return;
        }
        String string = getString(R.string.nfc_not_supported);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.nfc_not_supported)");
        d0(string);
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    public final void b0() {
        TextView textView;
        TextView textView2;
        TextView textView3;
        RegCardBeanInfo regCardBeanInfo = getRegStrapBeanInfo().getRegCardBeanInfo();
        String cardArtImageUrl = regCardBeanInfo != null ? regCardBeanInfo.getCardArtImageUrl() : null;
        if (cardArtImageUrl != null) {
            if (AppUtils.isNetConnected(requireContext())) {
                if (kotlin.text.m.endsWith(cardArtImageUrl, ".png", true)) {
                    try {
                        requireActivity().runOnUiThread(new Runnable() { // from class: com.coveiot.android.tappy.fragment.u0
                            @Override // java.lang.Runnable
                            public final void run() {
                                FragmentRegisteredProductItem.c0(FragmentRegisteredProductItem.this);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    F(cardArtImageUrl);
                }
            }
        } else {
            K().imageViewHolder.setImageResource(R.drawable.img_empty_card_bg);
        }
        RegCardBeanInfo regCardBeanInfo2 = getRegStrapBeanInfo().getRegCardBeanInfo();
        if ((regCardBeanInfo2 != null ? regCardBeanInfo2.getPaymentInstrumentTokenId() : null) != null && (textView3 = this.z) != null) {
            RegCardBeanInfo regCardBeanInfo3 = getRegStrapBeanInfo().getRegCardBeanInfo();
            String valueOf = String.valueOf(regCardBeanInfo3 != null ? regCardBeanInfo3.getPaymentInstrumentTokenId() : null);
            Intrinsics.checkNotNull(valueOf);
            textView3.setText(valueOf);
        }
        RegCardBeanInfo regCardBeanInfo4 = getRegStrapBeanInfo().getRegCardBeanInfo();
        if ((regCardBeanInfo4 != null ? regCardBeanInfo4.getLast4() : null) != null && (textView2 = this.t) != null) {
            RegCardBeanInfo regCardBeanInfo5 = getRegStrapBeanInfo().getRegCardBeanInfo();
            String last4 = regCardBeanInfo5 != null ? regCardBeanInfo5.getLast4() : null;
            Intrinsics.checkNotNull(last4);
            textView2.setText(last4);
        }
        RegCardBeanInfo regCardBeanInfo6 = getRegStrapBeanInfo().getRegCardBeanInfo();
        if ((regCardBeanInfo6 != null ? regCardBeanInfo6.getStatus() : null) == null || (textView = this.A) == null) {
            return;
        }
        RegCardBeanInfo regCardBeanInfo7 = getRegStrapBeanInfo().getRegCardBeanInfo();
        textView.setText(regCardBeanInfo7 != null ? regCardBeanInfo7.getStatus() : null);
    }

    public final void d0(String str) {
        ErrorDialog errorDialog = this.F;
        if (errorDialog == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            ErrorDialog errorDialog2 = new ErrorDialog(requireActivity);
            this.F = errorDialog2;
            Intrinsics.checkNotNull(errorDialog2);
            errorDialog2.setCancelable(false);
            ErrorDialog errorDialog3 = this.F;
            Intrinsics.checkNotNull(errorDialog3);
            ((TextView) errorDialog3.findViewById(R.id.tvErrorTitle)).setText(str);
            ErrorDialog errorDialog4 = this.F;
            Intrinsics.checkNotNull(errorDialog4);
            ((TextView) errorDialog4.findViewById(R.id.tvErrorMsg)).setVisibility(8);
            ErrorDialog errorDialog5 = this.F;
            Intrinsics.checkNotNull(errorDialog5);
            ((Button) errorDialog5.findViewById(R.id.bt_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.g1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentRegisteredProductItem.e0(FragmentRegisteredProductItem.this, view);
                }
            });
            ErrorDialog errorDialog6 = this.F;
            Intrinsics.checkNotNull(errorDialog6);
            Window window = errorDialog6.getWindow();
            Intrinsics.checkNotNull(window);
            window.setBackgroundDrawable(new ColorDrawable(0));
        } else {
            Intrinsics.checkNotNull(errorDialog);
            ((TextView) errorDialog.findViewById(R.id.tvErrorTitle)).setText(str);
        }
        ErrorDialog errorDialog7 = this.F;
        Boolean valueOf = errorDialog7 != null ? Boolean.valueOf(errorDialog7.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue()) {
            return;
        }
        ErrorDialog errorDialog8 = this.F;
        Intrinsics.checkNotNull(errorDialog8);
        errorDialog8.show();
    }

    public final void f0(final long j, final long j2) {
        if (this.I == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            FriendlyNameDialog friendlyNameDialog = new FriendlyNameDialog(requireActivity);
            this.I = friendlyNameDialog;
            Intrinsics.checkNotNull(friendlyNameDialog);
            friendlyNameDialog.setCancelable(false);
            FriendlyNameDialog friendlyNameDialog2 = this.I;
            Intrinsics.checkNotNull(friendlyNameDialog2);
            Button button = (Button) friendlyNameDialog2.findViewById(R.id.btnSave);
            button.setText(getString(R.string.next));
            FriendlyNameDialog friendlyNameDialog3 = this.I;
            Intrinsics.checkNotNull(friendlyNameDialog3);
            final EditText editText = (EditText) friendlyNameDialog3.findViewById(R.id.edittext_strapName);
            FriendlyNameDialog friendlyNameDialog4 = this.I;
            Intrinsics.checkNotNull(friendlyNameDialog4);
            final TextView textView = (TextView) friendlyNameDialog4.findViewById(R.id.tvFriendlyNameCount);
            FriendlyNameDialog friendlyNameDialog5 = this.I;
            Intrinsics.checkNotNull(friendlyNameDialog5);
            final TextView textView2 = (TextView) friendlyNameDialog5.findViewById(R.id.tv_giveName);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.n0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentRegisteredProductItem.g0(editText, this, textView2, j, j2, view);
                }
            });
            FriendlyNameDialog friendlyNameDialog6 = this.I;
            Intrinsics.checkNotNull(friendlyNameDialog6);
            Window window = friendlyNameDialog6.getWindow();
            Intrinsics.checkNotNull(window);
            window.setBackgroundDrawable(new ColorDrawable(0));
            editText.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem$showFriendlyNameDialog$2
                @Override // android.text.TextWatcher
                public void afterTextChanged(@Nullable Editable editable) {
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                    int length = charSequence != null ? charSequence.length() : 0;
                    StringBuilder sb = new StringBuilder();
                    sb.append(length);
                    sb.append('/');
                    sb.append(50);
                    textView.setText(sb.toString());
                }
            });
        }
        FriendlyNameDialog friendlyNameDialog7 = this.I;
        Boolean valueOf = friendlyNameDialog7 != null ? Boolean.valueOf(friendlyNameDialog7.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue()) {
            return;
        }
        FriendlyNameDialog friendlyNameDialog8 = this.I;
        Intrinsics.checkNotNull(friendlyNameDialog8);
        friendlyNameDialog8.show();
        FriendlyNameDialog friendlyNameDialog9 = this.I;
        Intrinsics.checkNotNull(friendlyNameDialog9);
        Window window2 = friendlyNameDialog9.getWindow();
        if (window2 != null) {
            window2.setLayout(-1, -2);
        }
        if (window2 != null) {
            window2.setGravity(17);
        }
    }

    @Nullable
    public final TransactionHistoryAdapter getAdapter() {
        return this.B;
    }

    @Nullable
    public final CardActionConfirmationDialog getCardActionConfirmationDialog() {
        return this.E;
    }

    @Nullable
    public final ErrorDialog getErrorDialog() {
        return this.F;
    }

    @NotNull
    public final RegStrapBeanInfo getRegStrapBeanInfo() {
        RegStrapBeanInfo regStrapBeanInfo = this.regStrapBeanInfo;
        if (regStrapBeanInfo != null) {
            return regStrapBeanInfo;
        }
        Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
        return null;
    }

    public final void h0(final String str, final Intent intent) {
        if (this.G == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            CVVInputDialog cVVInputDialog = new CVVInputDialog(requireActivity);
            this.G = cVVInputDialog;
            Intrinsics.checkNotNull(cVVInputDialog);
            cVVInputDialog.setCancelable(false);
            CVVInputDialog cVVInputDialog2 = this.G;
            Intrinsics.checkNotNull(cVVInputDialog2);
            CVVInputDialog cVVInputDialog3 = this.G;
            Intrinsics.checkNotNull(cVVInputDialog3);
            CVVInputDialog cVVInputDialog4 = this.G;
            Intrinsics.checkNotNull(cVVInputDialog4);
            final EditText editText = (EditText) cVVInputDialog4.findViewById(R.id.edittext_cvv);
            CVVInputDialog cVVInputDialog5 = this.G;
            Intrinsics.checkNotNull(cVVInputDialog5);
            final TextView textView = (TextView) cVVInputDialog5.findViewById(R.id.tv_giveName);
            ((Button) cVVInputDialog2.findViewById(R.id.btnSave)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.y0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentRegisteredProductItem.i0(editText, this, textView, intent, str, view);
                }
            });
            ((TextView) cVVInputDialog3.findViewById(R.id.btnCancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.d1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentRegisteredProductItem.j0(FragmentRegisteredProductItem.this, view);
                }
            });
            CVVInputDialog cVVInputDialog6 = this.G;
            Intrinsics.checkNotNull(cVVInputDialog6);
            Window window = cVVInputDialog6.getWindow();
            Intrinsics.checkNotNull(window);
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        CVVInputDialog cVVInputDialog7 = this.G;
        Boolean valueOf = cVVInputDialog7 != null ? Boolean.valueOf(cVVInputDialog7.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue()) {
            return;
        }
        CVVInputDialog cVVInputDialog8 = this.G;
        Intrinsics.checkNotNull(cVVInputDialog8);
        cVVInputDialog8.show();
        CVVInputDialog cVVInputDialog9 = this.G;
        Intrinsics.checkNotNull(cVVInputDialog9);
        Window window2 = cVVInputDialog9.getWindow();
        if (window2 != null) {
            window2.setLayout(-1, -2);
        }
        if (window2 != null) {
            window2.setGravity(17);
        }
    }

    public final void k0() {
        if (this.H == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            String string = getString(R.string.please_turn_on_nfc);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_turn_on_nfc)");
            final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(requireActivity, string);
            this.H = bottomSheetDialogOneButtonOneTitle;
            String string2 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
            bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.p0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentRegisteredProductItem.l0(BottomSheetDialogOneButtonOneTitle.this, this, view);
                }
            });
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.H;
        Boolean valueOf = bottomSheetDialogOneButtonOneTitle2 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle2.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue()) {
            return;
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.H;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonOneTitle3);
        bottomSheetDialogOneButtonOneTitle3.show();
    }

    public final void m0(GetTransactionDetailsByIdResponse getTransactionDetailsByIdResponse) {
        if (this.J == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            TransactionDetailDialog transactionDetailDialog = new TransactionDetailDialog(requireActivity);
            this.J = transactionDetailDialog;
            Intrinsics.checkNotNull(transactionDetailDialog);
            transactionDetailDialog.setCancelable(false);
            TransactionDetailDialog transactionDetailDialog2 = this.J;
            Intrinsics.checkNotNull(transactionDetailDialog2);
            ImageButton imageButton = (ImageButton) transactionDetailDialog2.findViewById(R.id.imgv_close);
            TransactionDetailDialog transactionDetailDialog3 = this.J;
            Intrinsics.checkNotNull(transactionDetailDialog3);
            TextView textView = (TextView) transactionDetailDialog3.findViewById(R.id.tv_merchantName);
            TransactionDetailDialog transactionDetailDialog4 = this.J;
            Intrinsics.checkNotNull(transactionDetailDialog4);
            TextView textView2 = (TextView) transactionDetailDialog4.findViewById(R.id.tv_transactionAmount);
            TransactionDetailDialog transactionDetailDialog5 = this.J;
            Intrinsics.checkNotNull(transactionDetailDialog5);
            TextView textView3 = (TextView) transactionDetailDialog5.findViewById(R.id.tv_transactionStatus);
            TransactionDetailDialog transactionDetailDialog6 = this.J;
            Intrinsics.checkNotNull(transactionDetailDialog6);
            TextView textView4 = (TextView) transactionDetailDialog6.findViewById(R.id.tv_merchantCity);
            TransactionDetailDialog transactionDetailDialog7 = this.J;
            Intrinsics.checkNotNull(transactionDetailDialog7);
            TextView textView5 = (TextView) transactionDetailDialog7.findViewById(R.id.tv_transactionDt);
            TransactionDetailDialog transactionDetailDialog8 = this.J;
            Intrinsics.checkNotNull(transactionDetailDialog8);
            TextView textView6 = (TextView) transactionDetailDialog8.findViewById(R.id.tv_transactionType);
            TransactionDetailDialog transactionDetailDialog9 = this.J;
            Intrinsics.checkNotNull(transactionDetailDialog9);
            TextView textView7 = (TextView) transactionDetailDialog9.findViewById(R.id.tv_industryCategoryName);
            TransactionDetailDialog transactionDetailDialog10 = this.J;
            Intrinsics.checkNotNull(transactionDetailDialog10);
            TextView textView8 = (TextView) transactionDetailDialog10.findViewById(R.id.tv_industryName);
            String merchantName = getTransactionDetailsByIdResponse.getMerchantName();
            boolean z = true;
            if (!(merchantName == null || merchantName.length() == 0)) {
                textView.setText(getTransactionDetailsByIdResponse.getMerchantName());
            }
            getTransactionDetailsByIdResponse.getAmount();
            textView2.setText(String.valueOf(getTransactionDetailsByIdResponse.getAmount()));
            String transactionStatus = getTransactionDetailsByIdResponse.getTransactionStatus();
            if (!(transactionStatus == null || transactionStatus.length() == 0)) {
                textView3.setText(getTransactionDetailsByIdResponse.getTransactionStatus());
                if (Intrinsics.areEqual(getTransactionDetailsByIdResponse.getTransactionStatus(), "Declined")) {
                    textView3.setTextColor(requireActivity().getColor(R.color.color_c43b29));
                } else if (Intrinsics.areEqual(getTransactionDetailsByIdResponse.getTransactionStatus(), "Pending")) {
                    textView3.setTextColor(requireActivity().getColor(R.color.color_e69951));
                } else {
                    textView3.setTextColor(requireActivity().getColor(R.color.color_de4cca2b));
                }
            }
            String merchantCity = getTransactionDetailsByIdResponse.getMerchantCity();
            if (!(merchantCity == null || merchantCity.length() == 0)) {
                textView4.setText(getTransactionDetailsByIdResponse.getMerchantCity());
            }
            getTransactionDetailsByIdResponse.getTransactionDate();
            if (getTransactionDetailsByIdResponse.getTransactionDate() > 0) {
                Date date = new Date();
                date.setTime(getTransactionDetailsByIdResponse.getTransactionDate());
                textView5.setText(AppUtils.formatDate(date, "EEEE, MMMM dd, yyyy hh:mm a"));
            }
            String transactionType = getTransactionDetailsByIdResponse.getTransactionType();
            if (!(transactionType == null || transactionType.length() == 0)) {
                textView6.setText(getTransactionDetailsByIdResponse.getTransactionType());
            }
            String industryCategoryName = getTransactionDetailsByIdResponse.getIndustryCategoryName();
            if (!(industryCategoryName == null || industryCategoryName.length() == 0)) {
                textView7.setText(getTransactionDetailsByIdResponse.getIndustryCategoryName());
            }
            String industryName = getTransactionDetailsByIdResponse.getIndustryName();
            if (industryName != null && industryName.length() != 0) {
                z = false;
            }
            if (!z) {
                textView8.setText(getTransactionDetailsByIdResponse.getIndustryName());
            }
            imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.a1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentRegisteredProductItem.n0(FragmentRegisteredProductItem.this, view);
                }
            });
            TransactionDetailDialog transactionDetailDialog11 = this.J;
            Intrinsics.checkNotNull(transactionDetailDialog11);
            Window window = transactionDetailDialog11.getWindow();
            Intrinsics.checkNotNull(window);
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        TransactionDetailDialog transactionDetailDialog12 = this.J;
        Boolean valueOf = transactionDetailDialog12 != null ? Boolean.valueOf(transactionDetailDialog12.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue()) {
            return;
        }
        TransactionDetailDialog transactionDetailDialog13 = this.J;
        Intrinsics.checkNotNull(transactionDetailDialog13);
        transactionDetailDialog13.show();
        TransactionDetailDialog transactionDetailDialog14 = this.J;
        Intrinsics.checkNotNull(transactionDetailDialog14);
        Window window2 = transactionDetailDialog14.getWindow();
        if (window2 != null) {
            window2.setLayout(-1, -2);
        }
        if (window2 != null) {
            window2.setGravity(17);
        }
    }

    public final void o0() {
        RegCardBeanInfo regCardBeanInfo;
        ConstraintLayout constraintLayout = this.u;
        if (constraintLayout != null) {
            constraintLayout.setVisibility(0);
        }
        RecyclerView recyclerView = this.v;
        if (recyclerView != null) {
            recyclerView.setVisibility(8);
        }
        RecyclerView recyclerView2 = this.v;
        if (recyclerView2 != null) {
            recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        ManageViewModel manageViewModel = null;
        BaseFragment.showProgress$default(this, false, 1, null);
        ManageViewModel manageViewModel2 = this.C;
        if (manageViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
            manageViewModel2 = null;
        }
        RegStrapBeanInfo regStrapBeanInfo = getRegStrapBeanInfo();
        Long endUserID = regStrapBeanInfo != null ? regStrapBeanInfo.getEndUserID() : null;
        Intrinsics.checkNotNull(endUserID);
        long longValue = endUserID.longValue();
        RegStrapBeanInfo regStrapBeanInfo2 = getRegStrapBeanInfo();
        Long paymentInstrumentTokenId = (regStrapBeanInfo2 == null || (regCardBeanInfo = regStrapBeanInfo2.getRegCardBeanInfo()) == null) ? null : regCardBeanInfo.getPaymentInstrumentTokenId();
        Intrinsics.checkNotNull(paymentInstrumentTokenId);
        manageViewModel2.getTransactionHistory(longValue, paymentInstrumentTokenId.longValue());
        ManageViewModel manageViewModel3 = this.C;
        if (manageViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
        } else {
            manageViewModel = manageViewModel3;
        }
        MutableLiveData<GetTransactionDetailsResponse> getTransactionHistoryLiveData = manageViewModel.getGetTransactionHistoryLiveData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final Function1<GetTransactionDetailsResponse, Unit> function1 = new Function1<GetTransactionDetailsResponse, Unit>() { // from class: com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem$showTransactionHistory$1

            @DebugMetadata(c = "com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem$showTransactionHistory$1$1", f = "FragmentRegisteredProductItem.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem$showTransactionHistory$1$1  reason: invalid class name */
            /* loaded from: classes7.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ GetTransactionDetailsResponse $it;
                public int label;
                public final /* synthetic */ FragmentRegisteredProductItem this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(FragmentRegisteredProductItem fragmentRegisteredProductItem, GetTransactionDetailsResponse getTransactionDetailsResponse, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = fragmentRegisteredProductItem;
                    this.$it = getTransactionDetailsResponse;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new AnonymousClass1(this.this$0, this.$it, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    ArrayList arrayList;
                    RecyclerView recyclerView;
                    ArrayList arrayList2;
                    ArrayList arrayList3;
                    RecyclerView recyclerView2;
                    ArrayList arrayList4;
                    RecyclerView recyclerView3;
                    ArrayList arrayList5;
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.dismissProgress();
                        arrayList = this.this$0.D;
                        arrayList.clear();
                        if (this.$it != null) {
                            arrayList2 = this.this$0.D;
                            if (arrayList2 == null || arrayList2.isEmpty()) {
                                arrayList3 = this.this$0.D;
                                Intrinsics.checkNotNull(arrayList3);
                                Iterator it = arrayList3.iterator();
                                while (it.hasNext()) {
                                    TransactionBeanInfo transactionBeanInfo = (TransactionBeanInfo) it.next();
                                    TransactionBeanInfo transactionBeanInfo2 = new TransactionBeanInfo(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
                                    transactionBeanInfo2.setTransactionId(transactionBeanInfo.getTransactionId());
                                    transactionBeanInfo2.setAmount(transactionBeanInfo.getAmount());
                                    transactionBeanInfo2.setCurrency(transactionBeanInfo.getCurrency());
                                    transactionBeanInfo2.setTransactionType(transactionBeanInfo.getTransactionType());
                                    transactionBeanInfo2.setTransactionDate(transactionBeanInfo.getTransactionDate());
                                    transactionBeanInfo2.setIndustryCategoryCode(transactionBeanInfo.getIndustryCategoryCode());
                                    transactionBeanInfo2.setIndustryCode(transactionBeanInfo.getIndustryCode());
                                    transactionBeanInfo2.setMerchantLatitude(transactionBeanInfo.getMerchantLatitude());
                                    transactionBeanInfo2.setMerchantLongitude(transactionBeanInfo.getMerchantLongitude());
                                    transactionBeanInfo2.setMerchantName(transactionBeanInfo.getMerchantName());
                                    transactionBeanInfo2.setTransactionStatus(transactionBeanInfo.getTransactionStatus());
                                    arrayList5 = this.this$0.D;
                                    arrayList5.add(transactionBeanInfo2);
                                }
                                recyclerView2 = this.this$0.v;
                                if (recyclerView2 != null) {
                                    recyclerView2.setVisibility(0);
                                }
                                FragmentRegisteredProductItem fragmentRegisteredProductItem = this.this$0;
                                Context requireContext = fragmentRegisteredProductItem.requireContext();
                                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                                arrayList4 = this.this$0.D;
                                fragmentRegisteredProductItem.setAdapter(new TransactionHistoryAdapter(requireContext, CollectionsKt___CollectionsKt.sortedWith(arrayList4, 
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00eb: INVOKE  
                                      (r1v18 'fragmentRegisteredProductItem' com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem)
                                      (wrap: com.coveiot.android.tappy.adapter.TransactionHistoryAdapter : 0x00e8: CONSTRUCTOR  (r2v2 com.coveiot.android.tappy.adapter.TransactionHistoryAdapter A[REMOVE]) = 
                                      (r3v1 'requireContext' android.content.Context)
                                      (wrap: java.util.List : 0x00e4: INVOKE  (r4v3 java.util.List A[REMOVE]) = 
                                      (r4v2 'arrayList4' java.util.ArrayList)
                                      (wrap: java.util.Comparator : 0x00e1: CONSTRUCTOR  (r5v0 java.util.Comparator A[REMOVE]) =  call: com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem$showTransactionHistory$1$1$invokeSuspend$$inlined$sortedByDescending$1.<init>():void type: CONSTRUCTOR)
                                     type: STATIC call: kotlin.collections.CollectionsKt___CollectionsKt.sortedWith(java.lang.Iterable, java.util.Comparator):java.util.List)
                                     call: com.coveiot.android.tappy.adapter.TransactionHistoryAdapter.<init>(android.content.Context, java.util.List):void type: CONSTRUCTOR)
                                     type: VIRTUAL call: com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem.setAdapter(com.coveiot.android.tappy.adapter.TransactionHistoryAdapter):void in method: com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem$showTransactionHistory$1.1.invokeSuspend(java.lang.Object):java.lang.Object, file: classes7.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:296)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:275)
                                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:377)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
                                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:272)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem$showTransactionHistory$1$1$invokeSuspend$$inlined$sortedByDescending$1, state: NOT_LOADED
                                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:302)
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:769)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1097)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1097)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:765)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1097)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                                    	... 35 more
                                    */
                                /*
                                    Method dump skipped, instructions count: 296
                                    To view this dump add '--comments-level debug' option
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem$showTransactionHistory$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                            }
                        }

                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(GetTransactionDetailsResponse getTransactionDetailsResponse) {
                            invoke2(getTransactionDetailsResponse);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke  reason: avoid collision after fix types in other method */
                        public final void invoke2(@Nullable GetTransactionDetailsResponse getTransactionDetailsResponse) {
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentRegisteredProductItem.this), Dispatchers.getMain(), null, new AnonymousClass1(FragmentRegisteredProductItem.this, getTransactionDetailsResponse, null), 2, null);
                        }
                    };
                    getTransactionHistoryLiveData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.tappy.fragment.s0
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj) {
                            FragmentRegisteredProductItem.p0(Function1.this, obj);
                        }
                    });
                }

                @Override // androidx.fragment.app.Fragment
                @Nullable
                public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
                    Intrinsics.checkNotNullParameter(inflater, "inflater");
                    this.m = FragmentRegisteredProductItemNewBinding.inflate(inflater, viewGroup, false);
                    return K().getRoot();
                }

                @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
                public /* synthetic */ void onDestroyView() {
                    super.onDestroyView();
                    _$_clearFindViewByIdCache();
                }

                @Override // androidx.fragment.app.Fragment
                public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
                    RegCardBeanInfo regCardBeanInfo;
                    RegCardBeanInfo regCardBeanInfo2;
                    RegCardBeanInfo regCardBeanInfo3;
                    RegCardBeanInfo regCardBeanInfo4;
                    RegCardBeanInfo regCardBeanInfo5;
                    Intrinsics.checkNotNullParameter(view, "view");
                    super.onViewCreated(view, bundle);
                    W(view);
                    Intrinsics.checkNotNullExpressionValue(K().cvProductDetail, "binding.cvProductDetail");
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    this.C = (ManageViewModel) new ViewModelProvider(this, new ViewModelFactory(requireContext)).get(ManageViewModel.class);
                    Context requireContext2 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    NfcStrapViewModel nfcStrapViewModel = (NfcStrapViewModel) new ViewModelProvider(this, new ViewModelFactory(requireContext2)).get(NfcStrapViewModel.class);
                    Context context = getContext();
                    RegStrapBeanInfo regStrapBeanInfo = getRegStrapBeanInfo();
                    ManageViewModel manageViewModel = null;
                    GlideUtils.loadScaledImage(context, regStrapBeanInfo != null ? regStrapBeanInfo.getImageUrl() : null, new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem$onViewCreated$1
                        @Override // com.bumptech.glide.request.target.Target
                        public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                        }

                        /* JADX WARN: Code restructure failed: missing block: B:6:0x0013, code lost:
                            r2 = r0.k.w;
                         */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public void onResourceReady(@org.jetbrains.annotations.NotNull android.graphics.Bitmap r1, @org.jetbrains.annotations.Nullable com.bumptech.glide.request.transition.Transition<? super android.graphics.Bitmap> r2) {
                            /*
                                r0 = this;
                                java.lang.String r2 = "resource"
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
                                com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem r2 = com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem.this
                                android.widget.ImageView r2 = com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem.access$getImgvStrap$p(r2)
                                if (r2 == 0) goto L1e
                                android.graphics.Bitmap r1 = com.coveiot.utils.utility.AppUtils.getCircleBitmap(r1)
                                if (r1 == 0) goto L1e
                                com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem r2 = com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem.this
                                android.widget.ImageView r2 = com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem.access$getImgvStrap$p(r2)
                                if (r2 == 0) goto L1e
                                r2.setImageBitmap(r1)
                            L1e:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.fragment.FragmentRegisteredProductItem$onViewCreated$1.onResourceReady(android.graphics.Bitmap, com.bumptech.glide.request.transition.Transition):void");
                        }
                    });
                    RegStrapBeanInfo regStrapBeanInfo2 = getRegStrapBeanInfo();
                    String str = (regStrapBeanInfo2 == null || (str = regStrapBeanInfo2.getFriendlyName()) == null) ? "" : "";
                    TextView textView = this.x;
                    if (textView != null) {
                        textView.setText(str);
                    }
                    TextView textView2 = this.y;
                    if (textView2 != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(getString(R.string.sku));
                        sb.append(": ");
                        RegStrapBeanInfo regStrapBeanInfo3 = getRegStrapBeanInfo();
                        sb.append(regStrapBeanInfo3 != null ? regStrapBeanInfo3.getSku() : null);
                        sb.append(' ');
                        sb.append(getString(R.string.s_n));
                        sb.append(": ");
                        RegStrapBeanInfo regStrapBeanInfo4 = getRegStrapBeanInfo();
                        sb.append(regStrapBeanInfo4 != null ? regStrapBeanInfo4.getProductSerialNumber() : null);
                        textView2.setText(sb.toString());
                    }
                    RegStrapBeanInfo regStrapBeanInfo5 = getRegStrapBeanInfo();
                    if ((regStrapBeanInfo5 != null ? regStrapBeanInfo5.getRegCardBeanInfo() : null) == null) {
                        P();
                    } else {
                        RegStrapBeanInfo regStrapBeanInfo6 = getRegStrapBeanInfo();
                        if (Intrinsics.areEqual((regStrapBeanInfo6 == null || (regCardBeanInfo5 = regStrapBeanInfo6.getRegCardBeanInfo()) == null) ? null : regCardBeanInfo5.getStatus(), PaymentInstrumentTokenStatus.INACTIVE.getStatus())) {
                            N();
                        } else {
                            RegStrapBeanInfo regStrapBeanInfo7 = getRegStrapBeanInfo();
                            if (Intrinsics.areEqual((regStrapBeanInfo7 == null || (regCardBeanInfo4 = regStrapBeanInfo7.getRegCardBeanInfo()) == null) ? null : regCardBeanInfo4.getStatus(), PaymentInstrumentTokenStatus.ACTIVE.getStatus())) {
                                U();
                            } else {
                                RegStrapBeanInfo regStrapBeanInfo8 = getRegStrapBeanInfo();
                                if (Intrinsics.areEqual((regStrapBeanInfo8 == null || (regCardBeanInfo3 = regStrapBeanInfo8.getRegCardBeanInfo()) == null) ? null : regCardBeanInfo3.getStatus(), PaymentInstrumentTokenStatus.PI_ADDED.getStatus())) {
                                    S();
                                } else {
                                    RegStrapBeanInfo regStrapBeanInfo9 = getRegStrapBeanInfo();
                                    if (Intrinsics.areEqual((regStrapBeanInfo9 == null || (regCardBeanInfo2 = regStrapBeanInfo9.getRegCardBeanInfo()) == null) ? null : regCardBeanInfo2.getStatus(), PaymentInstrumentTokenStatus.TERMS_ACCEPTED.getStatus())) {
                                        S();
                                    } else {
                                        RegStrapBeanInfo regStrapBeanInfo10 = getRegStrapBeanInfo();
                                        if (Intrinsics.areEqual((regStrapBeanInfo10 == null || (regCardBeanInfo = regStrapBeanInfo10.getRegCardBeanInfo()) == null) ? null : regCardBeanInfo.getStatus(), PaymentInstrumentTokenStatus.SUSPENDED.getStatus())) {
                                            L();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    ManageViewModel manageViewModel2 = this.C;
                    if (manageViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                        manageViewModel2 = null;
                    }
                    MutableLiveData<UpdateRegisteredProductResponse> updateRegisteredProductLiveData = manageViewModel2.getUpdateRegisteredProductLiveData();
                    Intrinsics.checkNotNull(updateRegisteredProductLiveData);
                    LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                    final a aVar = new a();
                    updateRegisteredProductLiveData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.tappy.fragment.r0
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj) {
                            FragmentRegisteredProductItem.Y(Function1.this, obj);
                        }
                    });
                    ManageViewModel manageViewModel3 = this.C;
                    if (manageViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                        manageViewModel3 = null;
                    }
                    MutableLiveData<GetTransactionDetailsByIdResponse> getTransactionDetailsByIdLiveData = manageViewModel3.getGetTransactionDetailsByIdLiveData();
                    LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
                    final b bVar = new b();
                    getTransactionDetailsByIdLiveData.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.android.tappy.fragment.q0
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj) {
                            FragmentRegisteredProductItem.Z(Function1.this, obj);
                        }
                    });
                    ManageViewModel manageViewModel4 = this.C;
                    if (manageViewModel4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                    } else {
                        manageViewModel = manageViewModel4;
                    }
                    MutableLiveData<DeletePaymentInstrumentTokenResponse> deletePaymentInstrumentTokenLiveData = manageViewModel.getDeletePaymentInstrumentTokenLiveData();
                    Intrinsics.checkNotNull(deletePaymentInstrumentTokenLiveData);
                    LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
                    final c cVar = new c();
                    deletePaymentInstrumentTokenLiveData.observe(viewLifecycleOwner3, new Observer() { // from class: com.coveiot.android.tappy.fragment.t0
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj) {
                            FragmentRegisteredProductItem.a0(Function1.this, obj);
                        }
                    });
                }

                @Override // com.coveiot.android.tappy.adapter.TransactionHistoryAdapter.ItemClickListener
                public void onViewDetail(@NotNull TransactionBeanInfo transactionBeanInfo, int i) {
                    Intrinsics.checkNotNullParameter(transactionBeanInfo, "transactionBeanInfo");
                    BaseFragment.showProgress$default(this, false, 1, null);
                    LifecycleCoroutineScope lifecycleScope = LifecycleOwnerKt.getLifecycleScope(this);
                    if (lifecycleScope != null) {
                        kotlinx.coroutines.e.e(lifecycleScope, Dispatchers.getIO(), null, new d(transactionBeanInfo, null), 2, null);
                    }
                }

                public final void setAdapter(@Nullable TransactionHistoryAdapter transactionHistoryAdapter) {
                    this.B = transactionHistoryAdapter;
                }

                public final void setCardActionConfirmationDialog(@Nullable CardActionConfirmationDialog cardActionConfirmationDialog) {
                    this.E = cardActionConfirmationDialog;
                }

                public final void setErrorDialog(@Nullable ErrorDialog errorDialog) {
                    this.F = errorDialog;
                }

                public final void setRegStrapBeanInfo(@NotNull RegStrapBeanInfo regStrapBeanInfo) {
                    Intrinsics.checkNotNullParameter(regStrapBeanInfo, "<set-?>");
                    this.regStrapBeanInfo = regStrapBeanInfo;
                }
            }
