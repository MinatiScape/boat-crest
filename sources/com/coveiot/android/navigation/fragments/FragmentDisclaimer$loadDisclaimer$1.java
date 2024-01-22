package com.coveiot.android.navigation.fragments;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.databinding.FragmentDisclaimerBinding;
import com.coveiot.android.navigation.models.NavigationDisclaimerData;
import com.coveiot.android.navigation.viewModels.ActivityNavigationFTUViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.navigation.fragments.FragmentDisclaimer$loadDisclaimer$1", f = "FragmentDisclaimer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class FragmentDisclaimer$loadDisclaimer$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ FragmentDisclaimer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentDisclaimer$loadDisclaimer$1(FragmentDisclaimer fragmentDisclaimer, Continuation<? super FragmentDisclaimer$loadDisclaimer$1> continuation) {
        super(2, continuation);
        this.this$0 = fragmentDisclaimer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FragmentDisclaimer$loadDisclaimer$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FragmentDisclaimer$loadDisclaimer$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        ActivityNavigationFTUViewModel activityNavigationFTUViewModel;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            activityNavigationFTUViewModel = this.this$0.n;
            if (activityNavigationFTUViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                activityNavigationFTUViewModel = null;
            }
            final FragmentDisclaimer fragmentDisclaimer = this.this$0;
            activityNavigationFTUViewModel.getAppDisclaimer(new Function2<Boolean, NavigationDisclaimerData, Unit>() { // from class: com.coveiot.android.navigation.fragments.FragmentDisclaimer$loadDisclaimer$1.1
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, NavigationDisclaimerData navigationDisclaimerData) {
                    invoke(bool.booleanValue(), navigationDisclaimerData);
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z, @Nullable NavigationDisclaimerData navigationDisclaimerData) {
                    FragmentDisclaimerBinding m;
                    FragmentDisclaimerBinding m2;
                    if (z) {
                        m = FragmentDisclaimer.this.m();
                        WebView webView = m.webviewTbtNavigation;
                        Intrinsics.checkNotNull(navigationDisclaimerData);
                        webView.loadUrl(navigationDisclaimerData.getAppDisclaimerUrl());
                        m2 = FragmentDisclaimer.this.m();
                        WebView webView2 = m2.webviewTbtNavigation;
                        final FragmentDisclaimer fragmentDisclaimer2 = FragmentDisclaimer.this;
                        webView2.setWebViewClient(new WebViewClient() { // from class: com.coveiot.android.navigation.fragments.FragmentDisclaimer.loadDisclaimer.1.1.1
                            @Override // android.webkit.WebViewClient
                            public void onPageFinished(@Nullable WebView webView3, @Nullable String str) {
                                FragmentDisclaimer.this.dismissProgress();
                            }

                            @Override // android.webkit.WebViewClient
                            public void onReceivedHttpError(@Nullable WebView webView3, @Nullable WebResourceRequest webResourceRequest, @Nullable WebResourceResponse webResourceResponse) {
                                super.onReceivedHttpError(webView3, webResourceRequest, webResourceResponse);
                                FragmentDisclaimer.this.dismissProgress();
                                FragmentDisclaimer fragmentDisclaimer3 = FragmentDisclaimer.this;
                                String string = fragmentDisclaimer3.requireContext().getString(R.string.some_thing_went_wrong);
                                Intrinsics.checkNotNullExpressionValue(string, "requireContext().getStri…ng.some_thing_went_wrong)");
                                fragmentDisclaimer3.p(string);
                            }
                        });
                        return;
                    }
                    FragmentDisclaimer.this.dismissProgress();
                    FragmentDisclaimer fragmentDisclaimer3 = FragmentDisclaimer.this;
                    String string = fragmentDisclaimer3.requireContext().getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "requireContext().getStri…ng.some_thing_went_wrong)");
                    fragmentDisclaimer3.p(string);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
