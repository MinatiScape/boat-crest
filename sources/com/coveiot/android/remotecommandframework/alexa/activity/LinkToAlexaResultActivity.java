package com.coveiot.android.remotecommandframework.alexa.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.remotecommandframework.R;
import com.coveiot.android.remotecommandframework.ViewModelFactory;
import com.coveiot.android.remotecommandframework.alexa.fragment.FragmentAlexaLinkingFailed;
import com.coveiot.android.remotecommandframework.alexa.fragment.FragmentAlexaLinkingSuccess;
import com.coveiot.android.remotecommandframework.alexa.model.AppToAppLinkingResponse;
import com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService;
import com.coveiot.android.remotecommandframework.alexa.viewmodel.AlexaApiCallHandlerViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class LinkToAlexaResultActivity extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final String p = LinkToAlexaResultActivity.class.getSimpleName();
    public AlexaApiCallHandlerViewModel q;
    @Nullable
    public String r;
    public int s;

    /* loaded from: classes6.dex */
    public static final class a extends Lambda implements Function1<AppToAppLinkingResponse, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(AppToAppLinkingResponse appToAppLinkingResponse) {
            invoke2(appToAppLinkingResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(AppToAppLinkingResponse appToAppLinkingResponse) {
            Unit unit;
            if (!appToAppLinkingResponse.isSuccess()) {
                if (LinkToAlexaResultActivity.this.s < 1 && LinkToAlexaResultActivity.this.r != null) {
                    LinkToAlexaResultActivity.this.s++;
                    LinkToAlexaResultActivity linkToAlexaResultActivity = LinkToAlexaResultActivity.this;
                    String str = linkToAlexaResultActivity.r;
                    Intrinsics.checkNotNull(str);
                    linkToAlexaResultActivity.t(str);
                    return;
                }
                LinkToAlexaResultActivity.this.dismissProgress();
                LinkToAlexaResultActivity linkToAlexaResultActivity2 = LinkToAlexaResultActivity.this;
                String string = linkToAlexaResultActivity2.getString(R.string.alexa_app_to_app_linking_no_data);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.alexa…p_to_app_linking_no_data)");
                linkToAlexaResultActivity2.v(string);
                return;
            }
            LinkToAlexaResultActivity.this.dismissProgress();
            String str2 = LinkToAlexaResultActivity.this.r;
            if (str2 != null) {
                LinkToAlexaResultActivity.this.w(str2);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                LinkToAlexaResultActivity linkToAlexaResultActivity3 = LinkToAlexaResultActivity.this;
                String string2 = linkToAlexaResultActivity3.getString(R.string.alexa_app_to_app_linking_no_data);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.alexa…p_to_app_linking_no_data)");
                linkToAlexaResultActivity3.v(string2);
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class b extends Lambda implements Function1<AppToAppLinkingResponse, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(AppToAppLinkingResponse appToAppLinkingResponse) {
            invoke2(appToAppLinkingResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(AppToAppLinkingResponse appToAppLinkingResponse) {
            Unit unit;
            LinkToAlexaResultActivity.this.dismissProgress();
            if (appToAppLinkingResponse.isSuccess()) {
                String str = LinkToAlexaResultActivity.this.r;
                if (str != null) {
                    LinkToAlexaResultActivity.this.w(str);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                if (unit == null) {
                    LinkToAlexaResultActivity linkToAlexaResultActivity = LinkToAlexaResultActivity.this;
                    String string = linkToAlexaResultActivity.getString(R.string.alexa_app_to_app_linking_no_data);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.alexa…p_to_app_linking_no_data)");
                    linkToAlexaResultActivity.v(string);
                    return;
                }
                return;
            }
            LinkToAlexaResultActivity linkToAlexaResultActivity2 = LinkToAlexaResultActivity.this;
            String string2 = linkToAlexaResultActivity2.getString(R.string.alexa_app_to_app_linking_no_data);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.alexa…p_to_app_linking_no_data)");
            linkToAlexaResultActivity2.v(string2);
        }
    }

    public static final void B(LinkToAlexaResultActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void y(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void z(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void A() {
        int i = R.id.toolbar_title;
        ((TextView) findViewById(i)).setText(getString(R.string.link_to_alexa));
        ((TextView) findViewById(i)).setVisibility(8);
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.remotecommandframework.alexa.activity.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LinkToAlexaResultActivity.B(LinkToAlexaResultActivity.this, view);
            }
        });
    }

    public final void C() {
        Intent intent = new Intent(this, AlexaRemoteCommandFrameworkService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        Unit unit;
        super.onCreate(bundle);
        setContentView(R.layout.activity_link_to_alexa_result);
        A();
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(AlexaApiCallHandlerViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…lerViewModel::class.java)");
        this.q = (AlexaApiCallHandlerViewModel) viewModel;
        x();
        if (!SessionManager.getInstance(this).getAlexaAccountLinkingStatus()) {
            Intent intent = getIntent();
            if (intent != null) {
                intent.getAction();
            }
            Intent intent2 = getIntent();
            Unit unit2 = null;
            Uri data = intent2 != null ? intent2.getData() : null;
            if (data != null) {
                if (u(data.getQueryParameter("state"))) {
                    String queryParameter = data.getQueryParameter("code");
                    if (queryParameter != null) {
                        t(queryParameter);
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    if (unit == null) {
                        String queryParameter2 = data.getQueryParameter("error");
                        if (queryParameter2 != null) {
                            v(queryParameter2);
                            unit2 = Unit.INSTANCE;
                        }
                        if (unit2 == null) {
                            String string = getString(R.string.alexa_app_to_app_linking_failed);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.alexa…pp_to_app_linking_failed)");
                            v(string);
                        }
                    }
                } else {
                    String string2 = getString(R.string.alexa_app_to_app_linking_incorrect_state_code);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.alexa…ing_incorrect_state_code)");
                    v(string2);
                }
                unit2 = Unit.INSTANCE;
            }
            if (unit2 == null) {
                String string3 = getString(R.string.alexa_app_to_app_linking_no_data);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.alexa…p_to_app_linking_no_data)");
                v(string3);
                return;
            }
            return;
        }
        w(SessionManager.getInstance(this).getAlexaAccountLinkingAppStateCode());
    }

    public final void t(String str) {
        this.r = str;
        if (str != null) {
            if (AppUtils.isNetConnected(this)) {
                showProgress();
                AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel = this.q;
                if (alexaApiCallHandlerViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    alexaApiCallHandlerViewModel = null;
                }
                alexaApiCallHandlerViewModel.callAlexaAppToAppLinkingApiWithAuthCode(str);
                return;
            }
            showNoInternetMessage();
            finish();
        }
    }

    public final boolean u(String str) {
        String str2 = this.p;
        LogHelper.d(str2, str + ", " + SessionManager.getInstance(this).getAlexaAccountLinkingAppStateCode());
        if (str != null) {
            return Intrinsics.areEqual(str, SessionManager.getInstance(this).getAlexaAccountLinkingAppStateCode());
        }
        return false;
    }

    public final void v(String str) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        int i = R.id.fragment_container;
        FragmentAlexaLinkingFailed.Companion companion = FragmentAlexaLinkingFailed.Companion;
        beginTransaction.replace(i, companion.newInstance(str)).addToBackStack(companion.toString()).commitAllowingStateLoss();
    }

    public final void w(String str) {
        SessionManager.getInstance(this).setAlexaAccountLinkingStatus(true);
        C();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        int i = R.id.fragment_container;
        FragmentAlexaLinkingSuccess.Companion companion = FragmentAlexaLinkingSuccess.Companion;
        beginTransaction.replace(i, companion.newInstance(str)).addToBackStack(companion.toString()).commitAllowingStateLoss();
    }

    public final void x() {
        AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel = this.q;
        AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel2 = null;
        if (alexaApiCallHandlerViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            alexaApiCallHandlerViewModel = null;
        }
        MutableLiveData<AppToAppLinkingResponse> appToAppLinkingResponseLiveData = alexaApiCallHandlerViewModel.getAppToAppLinkingResponseLiveData();
        final a aVar = new a();
        appToAppLinkingResponseLiveData.observe(this, new Observer() { // from class: com.coveiot.android.remotecommandframework.alexa.activity.l
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LinkToAlexaResultActivity.y(Function1.this, obj);
            }
        });
        AlexaApiCallHandlerViewModel alexaApiCallHandlerViewModel3 = this.q;
        if (alexaApiCallHandlerViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            alexaApiCallHandlerViewModel2 = alexaApiCallHandlerViewModel3;
        }
        MutableLiveData<AppToAppLinkingResponse> alexaAccountLinkingStatusLiveData = alexaApiCallHandlerViewModel2.getAlexaAccountLinkingStatusLiveData();
        final b bVar = new b();
        alexaAccountLinkingStatusLiveData.observe(this, new Observer() { // from class: com.coveiot.android.remotecommandframework.alexa.activity.m
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LinkToAlexaResultActivity.z(Function1.this, obj);
            }
        });
    }
}
