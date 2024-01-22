package com.coveiot.android.remotecommandframework.alexa.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.remotecommandframework.R;
import com.coveiot.android.remotecommandframework.ViewModelFactory;
import com.coveiot.android.remotecommandframework.alexa.AlexaActivityInAppWebViewer;
import com.coveiot.android.remotecommandframework.alexa.adpter.AlexaUtteranceAdapter;
import com.coveiot.android.remotecommandframework.alexa.model.AlexaAccountLinkingDisabledEvent;
import com.coveiot.android.remotecommandframework.alexa.model.AppToAppLinkingResponse;
import com.coveiot.android.remotecommandframework.alexa.utils.AlexaAppUtils;
import com.coveiot.android.remotecommandframework.alexa.viewmodel.AlexaApiCallHandlerViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTwoMessages;
import com.coveiot.android.theme.utils.MySpannable;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.AlexaLocale;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.squareup.otto.Subscribe;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class FragmentAlexaLinkingSuccess extends BaseFragment implements MySpannable.OnTextClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public AlexaApiCallHandlerViewModel m;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle n;
    @Nullable
    public BottomSheetDialogOneButtonTwoMessages o;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle p;
    @Nullable
    public AlexaUtteranceAdapter q;
    @Nullable
    public HashMap<String, List<String>> r;
    @Nullable
    public TextView s;
    @Nullable
    public Spinner t;
    @Nullable
    public RecyclerView u;
    @Nullable
    public TextView v;

    /* loaded from: classes6.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentAlexaLinkingSuccess newInstance(@Nullable String str) {
            FragmentAlexaLinkingSuccess fragmentAlexaLinkingSuccess = new FragmentAlexaLinkingSuccess();
            Bundle bundle = new Bundle();
            bundle.putString("authorizationCode", str);
            fragmentAlexaLinkingSuccess.setArguments(bundle);
            return fragmentAlexaLinkingSuccess;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentAlexaLinkingSuccess newInstance(@Nullable String str) {
        return Companion.newInstance(str);
    }

    public static final void q(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void r(FragmentAlexaLinkingSuccess this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String alexaAccountLinkedFrom = SessionManager.getInstance(this$0.requireActivity()).getAlexaAccountLinkedFrom();
        if (!(alexaAccountLinkedFrom == null || alexaAccountLinkedFrom.length() == 0) && m.equals(alexaAccountLinkedFrom, "alexa", true)) {
            this$0.u();
        } else if (AppUtils.isNetConnected(this$0.getContext())) {
            AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel = null;
            BaseFragment.showProgress$default(this$0, false, 1, null);
            AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel2 = this$0.m;
            if (alexaApiCallHandlerViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                alexaApiCallHandlerViewModel = alexaApiCallHandlerViewModel2;
            }
            alexaApiCallHandlerViewModel.callAlexaAccountUnlinkingApi();
        } else {
            Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.please_check_network), 0).show();
        }
    }

    public static final void s(BottomSheetDialogOneButtonOneTitle dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static final void t(BottomSheetDialogOneButtonOneTitle dialog, FragmentAlexaLinkingSuccess this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.requireActivity().finish();
    }

    public static final void v(BottomSheetDialogOneButtonTwoMessages dialog, FragmentAlexaLinkingSuccess this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.requireActivity().finish();
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

    @Subscribe
    public final void onAccountLinkingDisabled(@NotNull AlexaAccountLinkingDisabledEvent alexaAccountLinkingDisabledEvent) {
        Intrinsics.checkNotNullParameter(alexaAccountLinkingDisabledEvent, "alexaAccountLinkingDisabledEvent");
        if (isAdded()) {
            requireActivity().finish();
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.getString("authorizationCode");
        }
        CoveEventBusManager.getInstance().getEventBus().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_alexa_authorization_code_received_new, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.theme.utils.MySpannable.OnTextClickListener
    public void onTextClicked(@Nullable String str) {
        if (isAdded()) {
            boolean z = true;
            if (str == null || str.length() == 0) {
                return;
            }
            if (Intrinsics.areEqual(str, getString(R.string.help_document_))) {
                AlexaLocale selectedAlexaLocale = SessionManager.getInstance(requireActivity()).getSelectedAlexaLocale();
                String helpUrl = selectedAlexaLocale != null ? selectedAlexaLocale.getHelpUrl() : null;
                if (helpUrl != null && helpUrl.length() != 0) {
                    z = false;
                }
                if (!z) {
                    Intent intent = new Intent(requireActivity(), AlexaActivityInAppWebViewer.class);
                    AlexaActivityInAppWebViewer.Companion companion = AlexaActivityInAppWebViewer.Companion;
                    intent.putExtra(companion.getINTENT_EXTRA_TITLE(), getString(R.string.alexa_help_doc));
                    String intent_extra_url = companion.getINTENT_EXTRA_URL();
                    AlexaLocale selectedAlexaLocale2 = SessionManager.getInstance(requireActivity()).getSelectedAlexaLocale();
                    intent.putExtra(intent_extra_url, selectedAlexaLocale2 != null ? selectedAlexaLocale2.getHelpUrl() : null);
                    intent.putExtra(companion.getIS_HEADER_REQURIED(), false);
                    startActivity(intent);
                    return;
                }
                Toast.makeText(requireActivity(), R.string.coming_soon, 0).show();
            } else if (Intrinsics.areEqual(str, getString(R.string.amazon_alexa_app_))) {
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                if (AlexaAppUtils.isAlexaAppInstalled(requireActivity)) {
                    Intent intent2 = new Intent();
                    intent2.setPackage(AlexaAppUtils.ALEXA_PACKAGE_NAME);
                    startActivity(intent2);
                    return;
                }
                Toast.makeText(requireActivity(), getString(R.string.alexa_app_is_not_installed), 0).show();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        this.s = (TextView) view.findViewById(R.id.textLogoutFromAlexa);
        this.t = (Spinner) view.findViewById(R.id.spinner_language_selection);
        this.u = (RecyclerView) view.findViewById(R.id.rv_utterance);
        this.v = (TextView) view.findViewById(R.id.infoTv);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(requireActivity)).get(AlexaApiCallHandlerViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…lerViewModel::class.java)");
        AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel = (AlexaApiCallHandlerViewModel) viewModel;
        this.m = alexaApiCallHandlerViewModel;
        AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel2 = null;
        if (alexaApiCallHandlerViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            alexaApiCallHandlerViewModel = null;
        }
        this.r = alexaApiCallHandlerViewModel.getLanguages();
        RecyclerView recyclerView = this.u;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        }
        AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel3 = this.m;
        if (alexaApiCallHandlerViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            alexaApiCallHandlerViewModel2 = alexaApiCallHandlerViewModel3;
        }
        MutableLiveData<AppToAppLinkingResponse> accountUnlinkingResponseLiveData = alexaApiCallHandlerViewModel2.getAccountUnlinkingResponseLiveData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final Function1<AppToAppLinkingResponse, Unit> function1 = new Function1<AppToAppLinkingResponse, Unit>() { // from class: com.coveiot.android.remotecommandframework.alexa.fragment.FragmentAlexaLinkingSuccess$onViewCreated$1

            @DebugMetadata(c = "com.coveiot.android.remotecommandframework.alexa.fragment.FragmentAlexaLinkingSuccess$onViewCreated$1$1", f = "FragmentAlexaLinkingSuccess.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.remotecommandframework.alexa.fragment.FragmentAlexaLinkingSuccess$onViewCreated$1$1  reason: invalid class name */
            /* loaded from: classes6.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ FragmentAlexaLinkingSuccess this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(FragmentAlexaLinkingSuccess fragmentAlexaLinkingSuccess, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = fragmentAlexaLinkingSuccess;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new AnonymousClass1(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        FragmentAlexaLinkingSuccess fragmentAlexaLinkingSuccess = this.this$0;
                        String string = fragmentAlexaLinkingSuccess.getString(R.string.logging_out_please_wait);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.logging_out_please_wait)");
                        final BaseFragment.ProgressListener showProgressDialogWithTitleAndProgress$default = BaseFragment.showProgressDialogWithTitleAndProgress$default(fragmentAlexaLinkingSuccess, string, false, 30, 2, null);
                        final Ref.IntRef intRef = new Ref.IntRef();
                        final FragmentAlexaLinkingSuccess fragmentAlexaLinkingSuccess2 = this.this$0;
                        new CountDownTimer() { // from class: com.coveiot.android.remotecommandframework.alexa.fragment.FragmentAlexaLinkingSuccess.onViewCreated.1.1.1

                            @DebugMetadata(c = "com.coveiot.android.remotecommandframework.alexa.fragment.FragmentAlexaLinkingSuccess$onViewCreated$1$1$1$onFinish$1", f = "FragmentAlexaLinkingSuccess.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                            /* renamed from: com.coveiot.android.remotecommandframework.alexa.fragment.FragmentAlexaLinkingSuccess$onViewCreated$1$1$1$a */
                            /* loaded from: classes6.dex */
                            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                public int label;
                                public final /* synthetic */ FragmentAlexaLinkingSuccess this$0;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                public a(FragmentAlexaLinkingSuccess fragmentAlexaLinkingSuccess, Continuation<? super a> continuation) {
                                    super(2, continuation);
                                    this.this$0 = fragmentAlexaLinkingSuccess;
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
                                        this.this$0.dismissProgress();
                                        this.this$0.dismissProgressDialogWithTitleAndProgress();
                                        this.this$0.showSuccessDialog();
                                        return Unit.INSTANCE;
                                    }
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                            }

                            @DebugMetadata(c = "com.coveiot.android.remotecommandframework.alexa.fragment.FragmentAlexaLinkingSuccess$onViewCreated$1$1$1$onTick$1", f = "FragmentAlexaLinkingSuccess.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                            /* renamed from: com.coveiot.android.remotecommandframework.alexa.fragment.FragmentAlexaLinkingSuccess$onViewCreated$1$1$1$b */
                            /* loaded from: classes6.dex */
                            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                public final /* synthetic */ Ref.IntRef $counter;
                                public final /* synthetic */ BaseFragment.ProgressListener $listener;
                                public int label;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                public b(Ref.IntRef intRef, BaseFragment.ProgressListener progressListener, Continuation<? super b> continuation) {
                                    super(2, continuation);
                                    this.$counter = intRef;
                                    this.$listener = progressListener;
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                @NotNull
                                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                                    return new b(this.$counter, this.$listener, continuation);
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
                                        Ref.IntRef intRef = this.$counter;
                                        int i = intRef.element + 1;
                                        intRef.element = i;
                                        this.$listener.onProgressChanged(i);
                                        return Unit.INSTANCE;
                                    }
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                            }

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(30000L, 1000L);
                            }

                            @Override // android.os.CountDownTimer
                            public void onFinish() {
                                if (FragmentAlexaLinkingSuccess.this.isAdded()) {
                                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentAlexaLinkingSuccess.this), Dispatchers.getMain(), null, new a(FragmentAlexaLinkingSuccess.this, null), 2, null);
                                }
                            }

                            @Override // android.os.CountDownTimer
                            public void onTick(long j) {
                                if (FragmentAlexaLinkingSuccess.this.isAdded()) {
                                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentAlexaLinkingSuccess.this), Dispatchers.getMain(), null, new b(intRef, showProgressDialogWithTitleAndProgress$default, null), 2, null);
                                }
                            }
                        }.start();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AppToAppLinkingResponse appToAppLinkingResponse) {
                invoke2(appToAppLinkingResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(AppToAppLinkingResponse appToAppLinkingResponse) {
                if (appToAppLinkingResponse.isSuccess()) {
                    SessionManager.getInstance(FragmentAlexaLinkingSuccess.this.requireActivity()).setAlexaAccountLinkingStatus(false);
                    if (FragmentAlexaLinkingSuccess.this.isAdded()) {
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentAlexaLinkingSuccess.this), Dispatchers.getMain(), null, new AnonymousClass1(FragmentAlexaLinkingSuccess.this, null), 2, null);
                        return;
                    }
                    return;
                }
                FragmentAlexaLinkingSuccess.this.dismissProgress();
                FragmentAlexaLinkingSuccess.this.showErrorDialog();
            }
        };
        accountUnlinkingResponseLiveData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.remotecommandframework.alexa.fragment.f
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentAlexaLinkingSuccess.q(Function1.this, obj);
            }
        });
        TextView textView = this.s;
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.remotecommandframework.alexa.fragment.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    FragmentAlexaLinkingSuccess.r(FragmentAlexaLinkingSuccess.this, view2);
                }
            });
        }
        Spinner spinner = this.t;
        if (spinner != null) {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.coveiot.android.remotecommandframework.alexa.fragment.FragmentAlexaLinkingSuccess$onViewCreated$3
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(@Nullable AdapterView<?> adapterView, @Nullable View view2, int i, long j) {
                    HashMap<String, List<String>> hashMap;
                    AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel4;
                    RecyclerView recyclerView2;
                    AlexaUtteranceAdapter alexaUtteranceAdapter;
                    Intrinsics.checkNotNull(adapterView);
                    View childAt = adapterView.getChildAt(0);
                    Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type android.widget.TextView");
                    ((TextView) childAt).setGravity(GravityCompat.START);
                    hashMap = FragmentAlexaLinkingSuccess.this.r;
                    if (hashMap != null) {
                        FragmentAlexaLinkingSuccess fragmentAlexaLinkingSuccess = FragmentAlexaLinkingSuccess.this;
                        FragmentActivity requireActivity2 = fragmentAlexaLinkingSuccess.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                        alexaApiCallHandlerViewModel4 = fragmentAlexaLinkingSuccess.m;
                        if (alexaApiCallHandlerViewModel4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            alexaApiCallHandlerViewModel4 = null;
                        }
                        View childAt2 = adapterView.getChildAt(0);
                        Intrinsics.checkNotNull(childAt2, "null cannot be cast to non-null type android.widget.TextView");
                        fragmentAlexaLinkingSuccess.q = new AlexaUtteranceAdapter(requireActivity2, alexaApiCallHandlerViewModel4.getUtterances(StringsKt__StringsKt.trim(((TextView) childAt2).getText().toString()).toString(), hashMap));
                        recyclerView2 = fragmentAlexaLinkingSuccess.u;
                        if (recyclerView2 == null) {
                            return;
                        }
                        alexaUtteranceAdapter = fragmentAlexaLinkingSuccess.q;
                        recyclerView2.setAdapter(alexaUtteranceAdapter);
                    }
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(@Nullable AdapterView<?> adapterView) {
                }
            });
        }
        HashMap<String, List<String>> hashMap = this.r;
        if (hashMap != null) {
            FragmentActivity requireActivity2 = requireActivity();
            int i = R.layout.spinner_view;
            Set<String> keySet = hashMap.keySet();
            Intrinsics.checkNotNullExpressionValue(keySet, "it.keys");
            ArrayAdapter arrayAdapter = new ArrayAdapter(requireActivity2, i, CollectionsKt___CollectionsKt.toList(keySet));
            Spinner spinner2 = this.t;
            if (spinner2 != null) {
                spinner2.setAdapter((SpinnerAdapter) arrayAdapter);
            }
            arrayAdapter.setDropDownViewResource(i);
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("");
        spannableStringBuilder.append((CharSequence) (getString(R.string.for_more_information_please) + ' '));
        int length = spannableStringBuilder.length();
        int i2 = R.string.help_document_;
        String string = getString(i2);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.help_document_)");
        String string2 = getString(i2);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.help_document_)");
        p(spannableStringBuilder, length, string, string2);
        spannableStringBuilder.append((CharSequence) (' ' + getString(R.string.or_go_to) + ' '));
        int length2 = spannableStringBuilder.length();
        int i3 = R.string.amazon_alexa_app_;
        String string3 = getString(i3);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.amazon_alexa_app_)");
        String string4 = getString(i3);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.amazon_alexa_app_)");
        p(spannableStringBuilder, length2, string3, string4);
        TextView textView2 = this.v;
        if (textView2 != null) {
            textView2.setMovementMethod(LinkMovementMethod.getInstance());
        }
        TextView textView3 = this.v;
        if (textView3 == null) {
            return;
        }
        textView3.setText(spannableStringBuilder);
    }

    public final void p(SpannableStringBuilder spannableStringBuilder, int i, String str, String str2) {
        spannableStringBuilder.append((CharSequence) str);
        spannableStringBuilder.setSpan(new MySpannable(this, str2, false), i, spannableStringBuilder.length(), 33);
    }

    public final void showErrorDialog() {
        if (isAdded()) {
            if (this.p == null) {
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                String string = getString(R.string.account_unlinking_failed_please_retry);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.accou…king_failed_please_retry)");
                this.p = new BottomSheetDialogOneButtonOneTitle(requireActivity, string);
            }
            final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this.p;
            if (bottomSheetDialogOneButtonOneTitle != null) {
                String string2 = getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(\n             …ring.ok\n                )");
                bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.remotecommandframework.alexa.fragment.c
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentAlexaLinkingSuccess.s(BottomSheetDialogOneButtonOneTitle.this, view);
                    }
                });
                bottomSheetDialogOneButtonOneTitle.show();
            }
        }
    }

    public final void showSuccessDialog() {
        if (isAdded()) {
            if (this.n == null) {
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                String string = getString(R.string.logged_out_successfully_from_alexa);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.logge…_successfully_from_alexa)");
                this.n = new BottomSheetDialogOneButtonOneTitle(requireActivity, string);
            }
            final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this.n;
            if (bottomSheetDialogOneButtonOneTitle != null) {
                String string2 = getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(\n             …ring.ok\n                )");
                bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.remotecommandframework.alexa.fragment.d
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentAlexaLinkingSuccess.t(BottomSheetDialogOneButtonOneTitle.this, this, view);
                    }
                });
                bottomSheetDialogOneButtonOneTitle.show();
            }
        }
    }

    public final void u() {
        if (isAdded()) {
            if (this.o == null) {
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                this.o = new BottomSheetDialogOneButtonTwoMessages(requireActivity, getString(R.string.skills_have_been_unlinked_from_boat), getString(R.string.please_unlink_the_skill_from_alexa));
            }
            final BottomSheetDialogOneButtonTwoMessages bottomSheetDialogOneButtonTwoMessages = this.o;
            if (bottomSheetDialogOneButtonTwoMessages != null) {
                String string = getString(R.string.ok);
                Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …ring.ok\n                )");
                bottomSheetDialogOneButtonTwoMessages.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.remotecommandframework.alexa.fragment.e
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentAlexaLinkingSuccess.v(BottomSheetDialogOneButtonTwoMessages.this, this, view);
                    }
                });
                if (bottomSheetDialogOneButtonTwoMessages.isShowing()) {
                    return;
                }
                bottomSheetDialogOneButtonTwoMessages.show();
            }
        }
    }
}
