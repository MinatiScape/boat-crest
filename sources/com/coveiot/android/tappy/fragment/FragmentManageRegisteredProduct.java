package com.coveiot.android.tappy.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.activity.AddPaymentCardActivity;
import com.coveiot.android.tappy.customview.CardActionConfirmationDialog;
import com.coveiot.android.tappy.customview.ReasonInputDialog;
import com.coveiot.android.tappy.fragment.FragmentManageRegisteredProduct;
import com.coveiot.android.tappy.model.DeletePaymentInstrumentTokenResponse;
import com.coveiot.android.tappy.model.DeleteRegisteredProductResponse;
import com.coveiot.android.tappy.model.PaymentInstrumentTokenStatus;
import com.coveiot.android.tappy.model.RegCardBeanInfo;
import com.coveiot.android.tappy.model.RegStrapBeanInfo;
import com.coveiot.android.tappy.model.ResumePaymentInstrumentTokenResponse;
import com.coveiot.android.tappy.model.SuspendPaymentInstrumentTokenResponse;
import com.coveiot.android.tappy.utils.Constants;
import com.coveiot.android.tappy.viewmodel.ManageViewModel;
import com.coveiot.android.tappy.viewmodel.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
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
public final class FragmentManageRegisteredProduct extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public CardActionConfirmationDialog A;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public RegStrapBeanInfo m;
    @Nullable
    public CardView n;
    @Nullable
    public CardView o;
    @Nullable
    public ImageView p;
    @Nullable
    public ImageView q;
    @Nullable
    public ImageView r;
    @Nullable
    public ImageView s;
    @Nullable
    public TextView t;
    @Nullable
    public TextView u;
    @Nullable
    public TextView v;
    @Nullable
    public TextView w;
    @Nullable
    public TextView x;
    public ManageViewModel y;
    @Nullable
    public ReasonInputDialog z;

    /* loaded from: classes7.dex */
    public enum Action {
        suspend,
        delete,
        resume,
        deregister
    }

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentManageRegisteredProduct newInstance(@NotNull RegStrapBeanInfo regStrapBeanInfo) {
            Intrinsics.checkNotNullParameter(regStrapBeanInfo, "regStrapBeanInfo");
            FragmentManageRegisteredProduct fragmentManageRegisteredProduct = new FragmentManageRegisteredProduct();
            fragmentManageRegisteredProduct.m = regStrapBeanInfo;
            return fragmentManageRegisteredProduct;
        }
    }

    /* loaded from: classes7.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Action.values().length];
            try {
                iArr[Action.deregister.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Action.resume.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Action.delete.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Action.suspend.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* loaded from: classes7.dex */
    public static final class a extends Lambda implements Function1<DeletePaymentInstrumentTokenResponse, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.fragment.FragmentManageRegisteredProduct$setClickListener$3$1", f = "FragmentManageRegisteredProduct.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.tappy.fragment.FragmentManageRegisteredProduct$a$a  reason: collision with other inner class name */
        /* loaded from: classes7.dex */
        public static final class C0323a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ DeletePaymentInstrumentTokenResponse $it;
            public int label;
            public final /* synthetic */ FragmentManageRegisteredProduct this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0323a(FragmentManageRegisteredProduct fragmentManageRegisteredProduct, DeletePaymentInstrumentTokenResponse deletePaymentInstrumentTokenResponse, Continuation<? super C0323a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentManageRegisteredProduct;
                this.$it = deletePaymentInstrumentTokenResponse;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0323a(this.this$0, this.$it, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0323a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                ReasonInputDialog reasonInputDialog;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.dismissProgress();
                    ReasonInputDialog reasonInputDialog2 = this.this$0.z;
                    if ((reasonInputDialog2 != null && reasonInputDialog2.isShowing()) && (reasonInputDialog = this.this$0.z) != null) {
                        reasonInputDialog.dismiss();
                    }
                    this.this$0.z = null;
                    if (this.$it != null) {
                        Toast.makeText(this.this$0.requireActivity(), this.this$0.getString(R.string.your_card_deleted_successfully), 1).show();
                        TextView textView = this.this$0.u;
                        if (textView != null) {
                            textView.setVisibility(8);
                        }
                        TextView textView2 = this.this$0.v;
                        if (textView2 != null) {
                            textView2.setVisibility(8);
                        }
                        TextView textView3 = this.this$0.w;
                        if (textView3 != null) {
                            textView3.setVisibility(8);
                        }
                        TextView textView4 = this.this$0.x;
                        if (textView4 != null) {
                            textView4.setVisibility(0);
                        }
                    } else {
                        Toast.makeText(this.this$0.requireActivity(), this.this$0.getString(R.string.something_went_wrong), 1).show();
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
        public /* bridge */ /* synthetic */ Unit invoke(DeletePaymentInstrumentTokenResponse deletePaymentInstrumentTokenResponse) {
            invoke2(deletePaymentInstrumentTokenResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable DeletePaymentInstrumentTokenResponse deletePaymentInstrumentTokenResponse) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentManageRegisteredProduct.this), Dispatchers.getMain(), null, new C0323a(FragmentManageRegisteredProduct.this, deletePaymentInstrumentTokenResponse, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class b extends Lambda implements Function1<SuspendPaymentInstrumentTokenResponse, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.fragment.FragmentManageRegisteredProduct$setClickListener$4$1", f = "FragmentManageRegisteredProduct.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ SuspendPaymentInstrumentTokenResponse $it;
            public int label;
            public final /* synthetic */ FragmentManageRegisteredProduct this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentManageRegisteredProduct fragmentManageRegisteredProduct, SuspendPaymentInstrumentTokenResponse suspendPaymentInstrumentTokenResponse, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentManageRegisteredProduct;
                this.$it = suspendPaymentInstrumentTokenResponse;
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
                ReasonInputDialog reasonInputDialog;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.dismissProgress();
                    ReasonInputDialog reasonInputDialog2 = this.this$0.z;
                    if ((reasonInputDialog2 != null && reasonInputDialog2.isShowing()) && (reasonInputDialog = this.this$0.z) != null) {
                        reasonInputDialog.dismiss();
                    }
                    this.this$0.z = null;
                    if (this.$it != null) {
                        Toast.makeText(this.this$0.requireActivity(), this.this$0.getString(R.string.your_card_suspended_successfully), 1).show();
                        TextView textView = this.this$0.u;
                        if (textView != null) {
                            textView.setVisibility(8);
                        }
                        TextView textView2 = this.this$0.v;
                        if (textView2 != null) {
                            textView2.setVisibility(8);
                        }
                        TextView textView3 = this.this$0.w;
                        if (textView3 != null) {
                            textView3.setVisibility(0);
                        }
                        TextView textView4 = this.this$0.x;
                        if (textView4 != null) {
                            textView4.setVisibility(8);
                        }
                    } else {
                        Toast.makeText(this.this$0.requireActivity(), this.this$0.getString(R.string.something_went_wrong), 1).show();
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
        public /* bridge */ /* synthetic */ Unit invoke(SuspendPaymentInstrumentTokenResponse suspendPaymentInstrumentTokenResponse) {
            invoke2(suspendPaymentInstrumentTokenResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable SuspendPaymentInstrumentTokenResponse suspendPaymentInstrumentTokenResponse) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentManageRegisteredProduct.this), Dispatchers.getMain(), null, new a(FragmentManageRegisteredProduct.this, suspendPaymentInstrumentTokenResponse, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class c extends Lambda implements Function1<ResumePaymentInstrumentTokenResponse, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.fragment.FragmentManageRegisteredProduct$setClickListener$5$1", f = "FragmentManageRegisteredProduct.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ ResumePaymentInstrumentTokenResponse $it;
            public int label;
            public final /* synthetic */ FragmentManageRegisteredProduct this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentManageRegisteredProduct fragmentManageRegisteredProduct, ResumePaymentInstrumentTokenResponse resumePaymentInstrumentTokenResponse, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentManageRegisteredProduct;
                this.$it = resumePaymentInstrumentTokenResponse;
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
                ReasonInputDialog reasonInputDialog;
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.dismissProgress();
                    ReasonInputDialog reasonInputDialog2 = this.this$0.z;
                    if ((reasonInputDialog2 != null && reasonInputDialog2.isShowing()) && (reasonInputDialog = this.this$0.z) != null) {
                        reasonInputDialog.dismiss();
                    }
                    this.this$0.z = null;
                    if (this.$it != null) {
                        Toast.makeText(this.this$0.requireActivity(), this.this$0.getString(R.string.your_card_resumed_successfully), 1).show();
                        TextView textView = this.this$0.u;
                        if (textView != null) {
                            textView.setVisibility(0);
                        }
                        TextView textView2 = this.this$0.v;
                        if (textView2 != null) {
                            textView2.setVisibility(0);
                        }
                        TextView textView3 = this.this$0.w;
                        if (textView3 != null) {
                            textView3.setVisibility(8);
                        }
                        TextView textView4 = this.this$0.x;
                        if (textView4 != null) {
                            textView4.setVisibility(8);
                        }
                    } else {
                        Toast.makeText(this.this$0.requireActivity(), this.this$0.getString(R.string.something_went_wrong), 1).show();
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
        public /* bridge */ /* synthetic */ Unit invoke(ResumePaymentInstrumentTokenResponse resumePaymentInstrumentTokenResponse) {
            invoke2(resumePaymentInstrumentTokenResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable ResumePaymentInstrumentTokenResponse resumePaymentInstrumentTokenResponse) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentManageRegisteredProduct.this), Dispatchers.getMain(), null, new a(FragmentManageRegisteredProduct.this, resumePaymentInstrumentTokenResponse, null), 2, null);
        }
    }

    /* loaded from: classes7.dex */
    public static final class d extends Lambda implements Function1<DeleteRegisteredProductResponse, Unit> {

        @DebugMetadata(c = "com.coveiot.android.tappy.fragment.FragmentManageRegisteredProduct$setClickListener$6$1", f = "FragmentManageRegisteredProduct.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes7.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ DeleteRegisteredProductResponse $it;
            public int label;
            public final /* synthetic */ FragmentManageRegisteredProduct this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(DeleteRegisteredProductResponse deleteRegisteredProductResponse, FragmentManageRegisteredProduct fragmentManageRegisteredProduct, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$it = deleteRegisteredProductResponse;
                this.this$0 = fragmentManageRegisteredProduct;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$it, this.this$0, continuation);
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
                    if (this.$it != null) {
                        this.this$0.dismissProgress();
                        Toast.makeText(this.this$0.requireActivity(), this.this$0.getString(R.string.your_stap_de_registered_successfully), 1).show();
                        this.this$0.requireActivity().finish();
                    } else {
                        this.this$0.dismissProgress();
                        Toast.makeText(this.this$0.requireActivity(), this.this$0.getString(R.string.something_went_wrong), 1).show();
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public d() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DeleteRegisteredProductResponse deleteRegisteredProductResponse) {
            invoke2(deleteRegisteredProductResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable DeleteRegisteredProductResponse deleteRegisteredProductResponse) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentManageRegisteredProduct.this), Dispatchers.getMain(), null, new a(deleteRegisteredProductResponse, FragmentManageRegisteredProduct.this, null), 2, null);
        }
    }

    public static final void C(final FragmentManageRegisteredProduct this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ImageView imageView = this$0.p;
        if (imageView != null && imageView.getVisibility() == 0) {
            TextView textView = this$0.t;
            if (textView != null) {
                textView.setVisibility(0);
            }
            ImageView imageView2 = this$0.p;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            ImageView imageView3 = this$0.q;
            if (imageView3 != null) {
                imageView3.setVisibility(0);
            }
            TextView textView2 = this$0.t;
            if (textView2 != null) {
                textView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.g0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        FragmentManageRegisteredProduct.D(FragmentManageRegisteredProduct.this, view2);
                    }
                });
                return;
            }
            return;
        }
        ImageView imageView4 = this$0.p;
        if (imageView4 != null) {
            imageView4.setVisibility(0);
        }
        ImageView imageView5 = this$0.q;
        if (imageView5 != null) {
            imageView5.setVisibility(8);
        }
        TextView textView3 = this$0.t;
        if (textView3 == null) {
            return;
        }
        textView3.setVisibility(8);
    }

    public static final void D(FragmentManageRegisteredProduct this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.O(Action.deregister);
    }

    public static final void E(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void F(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void G(final FragmentManageRegisteredProduct this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ImageView imageView = this$0.r;
        if (imageView != null && imageView.getVisibility() == 0) {
            TextView textView = this$0.u;
            if (textView != null) {
                textView.setVisibility(8);
            }
            TextView textView2 = this$0.v;
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
            TextView textView3 = this$0.w;
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
            TextView textView4 = this$0.x;
            if (textView4 != null) {
                textView4.setVisibility(8);
            }
            ImageView imageView2 = this$0.r;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            ImageView imageView3 = this$0.s;
            if (imageView3 != null) {
                imageView3.setVisibility(0);
            }
            RegStrapBeanInfo regStrapBeanInfo = this$0.m;
            if (regStrapBeanInfo == null) {
                Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
                regStrapBeanInfo = null;
            }
            RegCardBeanInfo regCardBeanInfo = regStrapBeanInfo.getRegCardBeanInfo();
            if (!Intrinsics.areEqual(regCardBeanInfo != null ? regCardBeanInfo.getStatus() : null, PaymentInstrumentTokenStatus.PI_ADDED.getStatus())) {
                RegStrapBeanInfo regStrapBeanInfo2 = this$0.m;
                if (regStrapBeanInfo2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
                    regStrapBeanInfo2 = null;
                }
                RegCardBeanInfo regCardBeanInfo2 = regStrapBeanInfo2.getRegCardBeanInfo();
                if (!Intrinsics.areEqual(regCardBeanInfo2 != null ? regCardBeanInfo2.getStatus() : null, PaymentInstrumentTokenStatus.INACTIVE.getStatus())) {
                    RegStrapBeanInfo regStrapBeanInfo3 = this$0.m;
                    if (regStrapBeanInfo3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
                        regStrapBeanInfo3 = null;
                    }
                    RegCardBeanInfo regCardBeanInfo3 = regStrapBeanInfo3.getRegCardBeanInfo();
                    if (Intrinsics.areEqual(regCardBeanInfo3 != null ? regCardBeanInfo3.getStatus() : null, PaymentInstrumentTokenStatus.SUSPENDED.getStatus())) {
                        TextView textView5 = this$0.w;
                        if (textView5 != null) {
                            textView5.setVisibility(0);
                        }
                        TextView textView6 = this$0.w;
                        if (textView6 != null) {
                            textView6.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.i0
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view2) {
                                    FragmentManageRegisteredProduct.I(FragmentManageRegisteredProduct.this, view2);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    RegStrapBeanInfo regStrapBeanInfo4 = this$0.m;
                    if (regStrapBeanInfo4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
                        regStrapBeanInfo4 = null;
                    }
                    RegCardBeanInfo regCardBeanInfo4 = regStrapBeanInfo4.getRegCardBeanInfo();
                    if (Intrinsics.areEqual(regCardBeanInfo4 != null ? regCardBeanInfo4.getStatus() : null, PaymentInstrumentTokenStatus.ACTIVE.getStatus())) {
                        TextView textView7 = this$0.v;
                        if (textView7 != null) {
                            textView7.setVisibility(0);
                        }
                        TextView textView8 = this$0.u;
                        if (textView8 != null) {
                            textView8.setVisibility(0);
                        }
                        TextView textView9 = this$0.v;
                        if (textView9 != null) {
                            textView9.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.l0
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view2) {
                                    FragmentManageRegisteredProduct.J(FragmentManageRegisteredProduct.this, view2);
                                }
                            });
                        }
                        TextView textView10 = this$0.u;
                        if (textView10 != null) {
                            textView10.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.y
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view2) {
                                    FragmentManageRegisteredProduct.K(FragmentManageRegisteredProduct.this, view2);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    RegStrapBeanInfo regStrapBeanInfo5 = this$0.m;
                    if (regStrapBeanInfo5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
                        regStrapBeanInfo5 = null;
                    }
                    if (regStrapBeanInfo5.getRegCardBeanInfo() != null) {
                        RegStrapBeanInfo regStrapBeanInfo6 = this$0.m;
                        if (regStrapBeanInfo6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
                            regStrapBeanInfo6 = null;
                        }
                        RegCardBeanInfo regCardBeanInfo5 = regStrapBeanInfo6.getRegCardBeanInfo();
                        if (!Intrinsics.areEqual(regCardBeanInfo5 != null ? regCardBeanInfo5.getStatus() : null, PaymentInstrumentTokenStatus.DELETED.getStatus())) {
                            return;
                        }
                    }
                    TextView textView11 = this$0.x;
                    if (textView11 != null) {
                        textView11.setVisibility(0);
                    }
                    TextView textView12 = this$0.x;
                    if (textView12 != null) {
                        textView12.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.h0
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view2) {
                                FragmentManageRegisteredProduct.L(FragmentManageRegisteredProduct.this, view2);
                            }
                        });
                        return;
                    }
                    return;
                }
            }
            TextView textView13 = this$0.u;
            if (textView13 != null) {
                textView13.setVisibility(0);
            }
            TextView textView14 = this$0.u;
            if (textView14 != null) {
                textView14.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.j0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        FragmentManageRegisteredProduct.H(FragmentManageRegisteredProduct.this, view2);
                    }
                });
                return;
            }
            return;
        }
        ImageView imageView4 = this$0.r;
        if (imageView4 != null) {
            imageView4.setVisibility(0);
        }
        ImageView imageView5 = this$0.s;
        if (imageView5 != null) {
            imageView5.setVisibility(8);
        }
        TextView textView15 = this$0.u;
        if (textView15 != null) {
            textView15.setVisibility(8);
        }
        TextView textView16 = this$0.v;
        if (textView16 != null) {
            textView16.setVisibility(8);
        }
        TextView textView17 = this$0.w;
        if (textView17 != null) {
            textView17.setVisibility(8);
        }
        TextView textView18 = this$0.x;
        if (textView18 == null) {
            return;
        }
        textView18.setVisibility(8);
    }

    public static final void H(FragmentManageRegisteredProduct this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.O(Action.delete);
    }

    public static final void I(FragmentManageRegisteredProduct this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.O(Action.resume);
    }

    public static final void J(FragmentManageRegisteredProduct this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.O(Action.suspend);
    }

    public static final void K(FragmentManageRegisteredProduct this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.O(Action.delete);
    }

    public static final void L(FragmentManageRegisteredProduct this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0.requireActivity(), AddPaymentCardActivity.class);
        RegStrapBeanInfo regStrapBeanInfo = this$0.m;
        RegStrapBeanInfo regStrapBeanInfo2 = null;
        if (regStrapBeanInfo == null) {
            Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
            regStrapBeanInfo = null;
        }
        intent.putExtra(Constants.END_USER_GLOBAL_ID, regStrapBeanInfo.getEndUserID());
        RegStrapBeanInfo regStrapBeanInfo3 = this$0.m;
        if (regStrapBeanInfo3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
            regStrapBeanInfo3 = null;
        }
        intent.putExtra("deviceId", regStrapBeanInfo3.getDeviceId());
        RegStrapBeanInfo regStrapBeanInfo4 = this$0.m;
        if (regStrapBeanInfo4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
            regStrapBeanInfo4 = null;
        }
        intent.putExtra(Constants.END_USER_PRODUCT_REGISTRATION_ID, regStrapBeanInfo4.getEndUserProductRegistrationID());
        RegStrapBeanInfo regStrapBeanInfo5 = this$0.m;
        if (regStrapBeanInfo5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
        } else {
            regStrapBeanInfo2 = regStrapBeanInfo5;
        }
        intent.putExtra(Constants.SERIAL_NUMBER, regStrapBeanInfo2.getProductSerialNumber());
        this$0.startActivity(intent);
        this$0.requireActivity().finish();
    }

    public static final void M(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void N(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void P(Action action, FragmentManageRegisteredProduct this$0, View view) {
        Intrinsics.checkNotNullParameter(action, "$action");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = WhenMappings.$EnumSwitchMapping$0[action.ordinal()];
        RegStrapBeanInfo regStrapBeanInfo = null;
        if (i != 1) {
            if (i == 2) {
                CardActionConfirmationDialog cardActionConfirmationDialog = this$0.A;
                if (cardActionConfirmationDialog != null) {
                    cardActionConfirmationDialog.dismiss();
                }
                this$0.A = null;
                this$0.R(Action.resume);
                return;
            } else if (i == 3) {
                CardActionConfirmationDialog cardActionConfirmationDialog2 = this$0.A;
                if (cardActionConfirmationDialog2 != null) {
                    cardActionConfirmationDialog2.dismiss();
                }
                this$0.A = null;
                this$0.R(Action.delete);
                return;
            } else if (i != 4) {
                return;
            } else {
                CardActionConfirmationDialog cardActionConfirmationDialog3 = this$0.A;
                if (cardActionConfirmationDialog3 != null) {
                    cardActionConfirmationDialog3.dismiss();
                }
                this$0.A = null;
                this$0.R(Action.suspend);
                return;
            }
        }
        CardActionConfirmationDialog cardActionConfirmationDialog4 = this$0.A;
        if (cardActionConfirmationDialog4 != null) {
            cardActionConfirmationDialog4.dismiss();
        }
        this$0.A = null;
        BaseFragment.showProgress$default(this$0, false, 1, null);
        ManageViewModel manageViewModel = this$0.y;
        if (manageViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
            manageViewModel = null;
        }
        RegStrapBeanInfo regStrapBeanInfo2 = this$0.m;
        if (regStrapBeanInfo2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
            regStrapBeanInfo2 = null;
        }
        Long endUserID = regStrapBeanInfo2.getEndUserID();
        Intrinsics.checkNotNull(endUserID);
        long longValue = endUserID.longValue();
        RegStrapBeanInfo regStrapBeanInfo3 = this$0.m;
        if (regStrapBeanInfo3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
        } else {
            regStrapBeanInfo = regStrapBeanInfo3;
        }
        Long endUserProductRegistrationID = regStrapBeanInfo.getEndUserProductRegistrationID();
        Intrinsics.checkNotNull(endUserProductRegistrationID);
        manageViewModel.deleteRegisteredProduct(longValue, endUserProductRegistrationID.longValue());
    }

    public static final void Q(FragmentManageRegisteredProduct this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CardActionConfirmationDialog cardActionConfirmationDialog = this$0.A;
        Intrinsics.checkNotNull(cardActionConfirmationDialog);
        cardActionConfirmationDialog.dismiss();
        this$0.A = null;
    }

    public static final void S(EditText editText, FragmentManageRegisteredProduct this$0, TextView textView, Action action, View view) {
        ManageViewModel manageViewModel;
        Long paymentInstrumentTokenId;
        ManageViewModel manageViewModel2;
        ManageViewModel manageViewModel3;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(action, "$action");
        Editable text = editText.getText();
        if (text == null || text.length() == 0) {
            Toast.makeText(this$0.requireActivity(), textView.getText().toString(), 1).show();
            return;
        }
        ReasonInputDialog reasonInputDialog = this$0.z;
        Intrinsics.checkNotNull(reasonInputDialog);
        reasonInputDialog.dismiss();
        BaseFragment.showProgress$default(this$0, false, 1, null);
        String name = action.name();
        int hashCode = name.hashCode();
        if (hashCode == -1852006340) {
            if (name.equals("suspend")) {
                ManageViewModel manageViewModel4 = this$0.y;
                if (manageViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                    manageViewModel = null;
                } else {
                    manageViewModel = manageViewModel4;
                }
                RegStrapBeanInfo regStrapBeanInfo = this$0.m;
                if (regStrapBeanInfo == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
                    regStrapBeanInfo = null;
                }
                Long endUserID = regStrapBeanInfo.getEndUserID();
                Intrinsics.checkNotNull(endUserID);
                long longValue = endUserID.longValue();
                RegStrapBeanInfo regStrapBeanInfo2 = this$0.m;
                if (regStrapBeanInfo2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
                    regStrapBeanInfo2 = null;
                }
                RegCardBeanInfo regCardBeanInfo = regStrapBeanInfo2.getRegCardBeanInfo();
                paymentInstrumentTokenId = regCardBeanInfo != null ? regCardBeanInfo.getPaymentInstrumentTokenId() : null;
                Intrinsics.checkNotNull(paymentInstrumentTokenId);
                manageViewModel.suspendPaymentInstrumentTokens(longValue, paymentInstrumentTokenId.longValue(), editText.getText().toString());
            }
        } else if (hashCode == -1335458389) {
            if (name.equals("delete")) {
                ManageViewModel manageViewModel5 = this$0.y;
                if (manageViewModel5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                    manageViewModel2 = null;
                } else {
                    manageViewModel2 = manageViewModel5;
                }
                RegStrapBeanInfo regStrapBeanInfo3 = this$0.m;
                if (regStrapBeanInfo3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
                    regStrapBeanInfo3 = null;
                }
                Long endUserID2 = regStrapBeanInfo3.getEndUserID();
                Intrinsics.checkNotNull(endUserID2);
                long longValue2 = endUserID2.longValue();
                RegStrapBeanInfo regStrapBeanInfo4 = this$0.m;
                if (regStrapBeanInfo4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
                    regStrapBeanInfo4 = null;
                }
                RegCardBeanInfo regCardBeanInfo2 = regStrapBeanInfo4.getRegCardBeanInfo();
                paymentInstrumentTokenId = regCardBeanInfo2 != null ? regCardBeanInfo2.getPaymentInstrumentTokenId() : null;
                Intrinsics.checkNotNull(paymentInstrumentTokenId);
                manageViewModel2.deletePaymentInstrumentTokens(longValue2, paymentInstrumentTokenId.longValue(), editText.getText().toString());
            }
        } else if (hashCode == -934426579 && name.equals("resume")) {
            ManageViewModel manageViewModel6 = this$0.y;
            if (manageViewModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
                manageViewModel3 = null;
            } else {
                manageViewModel3 = manageViewModel6;
            }
            RegStrapBeanInfo regStrapBeanInfo5 = this$0.m;
            if (regStrapBeanInfo5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
                regStrapBeanInfo5 = null;
            }
            Long endUserID3 = regStrapBeanInfo5.getEndUserID();
            Intrinsics.checkNotNull(endUserID3);
            long longValue3 = endUserID3.longValue();
            RegStrapBeanInfo regStrapBeanInfo6 = this$0.m;
            if (regStrapBeanInfo6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("regStrapBeanInfo");
                regStrapBeanInfo6 = null;
            }
            RegCardBeanInfo regCardBeanInfo3 = regStrapBeanInfo6.getRegCardBeanInfo();
            paymentInstrumentTokenId = regCardBeanInfo3 != null ? regCardBeanInfo3.getPaymentInstrumentTokenId() : null;
            Intrinsics.checkNotNull(paymentInstrumentTokenId);
            manageViewModel3.resumePaymentInstrumentTokens(longValue3, paymentInstrumentTokenId.longValue(), editText.getText().toString());
        }
    }

    public static final void T(FragmentManageRegisteredProduct this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ReasonInputDialog reasonInputDialog = this$0.z;
        Intrinsics.checkNotNull(reasonInputDialog);
        reasonInputDialog.dismiss();
    }

    @JvmStatic
    @NotNull
    public static final FragmentManageRegisteredProduct newInstance(@NotNull RegStrapBeanInfo regStrapBeanInfo) {
        return Companion.newInstance(regStrapBeanInfo);
    }

    public final void A(View view) {
        this.n = (CardView) view.findViewById(R.id.cv_manageStrap);
        this.o = (CardView) view.findViewById(R.id.cv_manageCard);
        this.p = (ImageView) view.findViewById(R.id.imgvDownStrap);
        this.q = (ImageView) view.findViewById(R.id.imgvUpStrap);
        this.r = (ImageView) view.findViewById(R.id.imgvDownCard);
        this.s = (ImageView) view.findViewById(R.id.imgvUpCard);
        this.t = (TextView) view.findViewById(R.id.tvDeregisterStrap);
        this.u = (TextView) view.findViewById(R.id.tvDeleteCard);
        this.v = (TextView) view.findViewById(R.id.tvSuspendCard);
        this.w = (TextView) view.findViewById(R.id.tvResumeCard);
        this.x = (TextView) view.findViewById(R.id.tvAddCard);
        ImageView imageView = this.p;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        ImageView imageView2 = this.q;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        ImageView imageView3 = this.r;
        if (imageView3 != null) {
            imageView3.setVisibility(0);
        }
        ImageView imageView4 = this.s;
        if (imageView4 != null) {
            imageView4.setVisibility(8);
        }
        TextView textView = this.t;
        if (textView != null) {
            textView.setVisibility(8);
        }
        TextView textView2 = this.u;
        if (textView2 != null) {
            textView2.setVisibility(8);
        }
        TextView textView3 = this.v;
        if (textView3 != null) {
            textView3.setVisibility(8);
        }
        TextView textView4 = this.w;
        if (textView4 != null) {
            textView4.setVisibility(8);
        }
        TextView textView5 = this.x;
        if (textView5 == null) {
            return;
        }
        textView5.setVisibility(8);
    }

    public final void B() {
        CardView cardView = this.n;
        if (cardView != null) {
            cardView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.f0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentManageRegisteredProduct.C(FragmentManageRegisteredProduct.this, view);
                }
            });
        }
        CardView cardView2 = this.o;
        if (cardView2 != null) {
            cardView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.m0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentManageRegisteredProduct.G(FragmentManageRegisteredProduct.this, view);
                }
            });
        }
        ManageViewModel manageViewModel = this.y;
        ManageViewModel manageViewModel2 = null;
        if (manageViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
            manageViewModel = null;
        }
        MutableLiveData<DeletePaymentInstrumentTokenResponse> deletePaymentInstrumentTokenLiveData = manageViewModel.getDeletePaymentInstrumentTokenLiveData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final a aVar = new a();
        deletePaymentInstrumentTokenLiveData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.tappy.fragment.b0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentManageRegisteredProduct.M(Function1.this, obj);
            }
        });
        ManageViewModel manageViewModel3 = this.y;
        if (manageViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
            manageViewModel3 = null;
        }
        MutableLiveData<SuspendPaymentInstrumentTokenResponse> suspendPaymentInstrumentTokenLiveData = manageViewModel3.getSuspendPaymentInstrumentTokenLiveData();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        final b bVar = new b();
        suspendPaymentInstrumentTokenLiveData.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.android.tappy.fragment.a0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentManageRegisteredProduct.N(Function1.this, obj);
            }
        });
        ManageViewModel manageViewModel4 = this.y;
        if (manageViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
            manageViewModel4 = null;
        }
        MutableLiveData<ResumePaymentInstrumentTokenResponse> resumePaymentInstrumentTokenLiveData = manageViewModel4.getResumePaymentInstrumentTokenLiveData();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        final c cVar = new c();
        resumePaymentInstrumentTokenLiveData.observe(viewLifecycleOwner3, new Observer() { // from class: com.coveiot.android.tappy.fragment.c0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentManageRegisteredProduct.E(Function1.this, obj);
            }
        });
        ManageViewModel manageViewModel5 = this.y;
        if (manageViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageViewModel");
        } else {
            manageViewModel2 = manageViewModel5;
        }
        MutableLiveData<DeleteRegisteredProductResponse> deleteRegisteredProductLiveData = manageViewModel2.getDeleteRegisteredProductLiveData();
        LifecycleOwner viewLifecycleOwner4 = getViewLifecycleOwner();
        final d dVar = new d();
        deleteRegisteredProductLiveData.observe(viewLifecycleOwner4, new Observer() { // from class: com.coveiot.android.tappy.fragment.d0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentManageRegisteredProduct.F(Function1.this, obj);
            }
        });
    }

    public final void O(final Action action) {
        if (this.A == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            CardActionConfirmationDialog cardActionConfirmationDialog = new CardActionConfirmationDialog(requireActivity);
            this.A = cardActionConfirmationDialog;
            Intrinsics.checkNotNull(cardActionConfirmationDialog);
            cardActionConfirmationDialog.setCancelable(false);
            CardActionConfirmationDialog cardActionConfirmationDialog2 = this.A;
            Intrinsics.checkNotNull(cardActionConfirmationDialog2);
            TextView textView = (TextView) cardActionConfirmationDialog2.findViewById(R.id.tv_DeregisterStrap);
            CardActionConfirmationDialog cardActionConfirmationDialog3 = this.A;
            Intrinsics.checkNotNull(cardActionConfirmationDialog3);
            TextView textView2 = (TextView) cardActionConfirmationDialog3.findViewById(R.id.tvByProceeding);
            CardActionConfirmationDialog cardActionConfirmationDialog4 = this.A;
            Intrinsics.checkNotNull(cardActionConfirmationDialog4);
            TextView textView3 = (TextView) cardActionConfirmationDialog4.findViewById(R.id.tvAreYouSure);
            int i = WhenMappings.$EnumSwitchMapping$0[action.ordinal()];
            if (i == 1) {
                textView.setText(getString(R.string.de_register_strap_q));
                textView2.setText(getString(R.string.proceeding_this_strap_will_be_deregistered));
                textView3.setText(getString(R.string.are_you_sure_you_want_to_de_register_strap));
            } else if (i == 2) {
                textView.setText(getString(R.string.resume_card_q));
                textView2.setText(getString(R.string.proceeding_this_strap_will_be_resumed));
                textView3.setText(getString(R.string.are_you_sure_you_want_to_resume_card));
            } else if (i == 3) {
                textView.setText(getString(R.string.delete_card_q));
                textView2.setText(getString(R.string.proceeding_this_strap_will_be_deleted));
                textView3.setText(getString(R.string.are_you_sure_you_want_to_delete_card));
            } else if (i == 4) {
                textView.setText(getString(R.string.suspend_card_q));
                textView2.setText(getString(R.string.proceeding_this_strap_will_be_suspended));
                textView3.setText(getString(R.string.are_you_sure_you_want_to_suspend_card));
            }
            CardActionConfirmationDialog cardActionConfirmationDialog5 = this.A;
            Intrinsics.checkNotNull(cardActionConfirmationDialog5);
            CardActionConfirmationDialog cardActionConfirmationDialog6 = this.A;
            Intrinsics.checkNotNull(cardActionConfirmationDialog6);
            ((TextView) cardActionConfirmationDialog5.findViewById(R.id.btn_yes)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.e0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentManageRegisteredProduct.P(FragmentManageRegisteredProduct.Action.this, this, view);
                }
            });
            ((TextView) cardActionConfirmationDialog6.findViewById(R.id.btn_No)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.k0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentManageRegisteredProduct.Q(FragmentManageRegisteredProduct.this, view);
                }
            });
            CardActionConfirmationDialog cardActionConfirmationDialog7 = this.A;
            Intrinsics.checkNotNull(cardActionConfirmationDialog7);
            Window window = cardActionConfirmationDialog7.getWindow();
            Intrinsics.checkNotNull(window);
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        CardActionConfirmationDialog cardActionConfirmationDialog8 = this.A;
        Boolean valueOf = cardActionConfirmationDialog8 != null ? Boolean.valueOf(cardActionConfirmationDialog8.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue()) {
            return;
        }
        CardActionConfirmationDialog cardActionConfirmationDialog9 = this.A;
        Intrinsics.checkNotNull(cardActionConfirmationDialog9);
        cardActionConfirmationDialog9.show();
        CardActionConfirmationDialog cardActionConfirmationDialog10 = this.A;
        Intrinsics.checkNotNull(cardActionConfirmationDialog10);
        Window window2 = cardActionConfirmationDialog10.getWindow();
        if (window2 != null) {
            window2.setLayout(-1, -2);
        }
        if (window2 != null) {
            window2.setGravity(17);
        }
    }

    public final void R(final Action action) {
        if (this.z == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            ReasonInputDialog reasonInputDialog = new ReasonInputDialog(requireActivity);
            this.z = reasonInputDialog;
            Intrinsics.checkNotNull(reasonInputDialog);
            reasonInputDialog.setCancelable(false);
            ReasonInputDialog reasonInputDialog2 = this.z;
            Intrinsics.checkNotNull(reasonInputDialog2);
            Button button = (Button) reasonInputDialog2.findViewById(R.id.btnSave);
            ReasonInputDialog reasonInputDialog3 = this.z;
            Intrinsics.checkNotNull(reasonInputDialog3);
            TextView textView = (TextView) reasonInputDialog3.findViewById(R.id.btnCancel);
            ReasonInputDialog reasonInputDialog4 = this.z;
            Intrinsics.checkNotNull(reasonInputDialog4);
            final EditText editText = (EditText) reasonInputDialog4.findViewById(R.id.edittext_reason);
            ReasonInputDialog reasonInputDialog5 = this.z;
            Intrinsics.checkNotNull(reasonInputDialog5);
            final TextView textView2 = (TextView) reasonInputDialog5.findViewById(R.id.tv_giveName);
            String name = action.name();
            int hashCode = name.hashCode();
            if (hashCode != -1852006340) {
                if (hashCode != -1335458389) {
                    if (hashCode == -934426579 && name.equals("resume")) {
                        int i = R.string.resume;
                        button.setText(getString(i));
                        textView2.setText(getString(R.string.give_a_reason_to) + ' ' + getString(i));
                    }
                } else if (name.equals("delete")) {
                    int i2 = R.string.delete;
                    button.setText(getString(i2));
                    textView2.setText(getString(R.string.give_a_reason_to) + ' ' + getString(i2));
                }
            } else if (name.equals("suspend")) {
                int i3 = R.string.suspend;
                button.setText(getString(i3));
                textView2.setText(getString(R.string.give_a_reason_to) + ' ' + getString(i3));
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.x
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentManageRegisteredProduct.S(editText, this, textView2, action, view);
                }
            });
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.z
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentManageRegisteredProduct.T(FragmentManageRegisteredProduct.this, view);
                }
            });
            ReasonInputDialog reasonInputDialog6 = this.z;
            Intrinsics.checkNotNull(reasonInputDialog6);
            Window window = reasonInputDialog6.getWindow();
            Intrinsics.checkNotNull(window);
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        ReasonInputDialog reasonInputDialog7 = this.z;
        Boolean valueOf = reasonInputDialog7 != null ? Boolean.valueOf(reasonInputDialog7.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue()) {
            return;
        }
        ReasonInputDialog reasonInputDialog8 = this.z;
        Intrinsics.checkNotNull(reasonInputDialog8);
        reasonInputDialog8.show();
        ReasonInputDialog reasonInputDialog9 = this.z;
        Intrinsics.checkNotNull(reasonInputDialog9);
        Window window2 = reasonInputDialog9.getWindow();
        if (window2 != null) {
            window2.setLayout(-1, -2);
        }
        if (window2 != null) {
            window2.setGravity(17);
        }
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

    @Nullable
    public final CardActionConfirmationDialog getCardActionConfirmationDialog() {
        return this.A;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_manage_registered_product1, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.y = (ManageViewModel) new ViewModelProvider(this, new ViewModelFactory(requireContext)).get(ManageViewModel.class);
        A(view);
        B();
    }

    public final void setCardActionConfirmationDialog(@Nullable CardActionConfirmationDialog cardActionConfirmationDialog) {
        this.A = cardActionConfirmationDialog;
    }
}
