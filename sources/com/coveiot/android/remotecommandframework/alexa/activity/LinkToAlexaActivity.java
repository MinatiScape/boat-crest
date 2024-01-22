package com.coveiot.android.remotecommandframework.alexa.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.coveiot.android.remotecommandframework.R;
import com.coveiot.android.remotecommandframework.alexa.AlexaActivityInAppWebViewer;
import com.coveiot.android.remotecommandframework.alexa.utils.AlexaAppUtils;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.AlexaDetails;
import com.coveiot.covepreferences.data.AlexaLocale;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class LinkToAlexaActivity extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public String p;
    @Nullable
    public String q;
    public String r;

    public static final void v(LinkToAlexaActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            String str = null;
            ((Button) this$0._$_findCachedViewById(R.id.textLoginToAlexa)).setOnClickListener(null);
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
            this$0.r = uuid;
            AlexaDetails alexaDetails = SessionManager.getInstance(this$0).getAlexaDetails();
            if (alexaDetails != null) {
                String nativeAppUrl = alexaDetails.getNativeAppUrl();
                boolean z = false;
                if (!(nativeAppUrl == null || nativeAppUrl.length() == 0)) {
                    String lwaFallbackUrl = alexaDetails.getLwaFallbackUrl();
                    if (lwaFallbackUrl == null || lwaFallbackUrl.length() == 0) {
                        z = true;
                    }
                    if (!z) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(alexaDetails.getNativeAppUrl());
                        sb.append("&state=");
                        String str2 = this$0.r;
                        if (str2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("stateToVerify");
                            str2 = null;
                        }
                        sb.append(str2);
                        this$0.p = sb.toString();
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(alexaDetails.getLwaFallbackUrl());
                        sb2.append("&state=");
                        String str3 = this$0.r;
                        if (str3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("stateToVerify");
                        } else {
                            str = str3;
                        }
                        sb2.append(str);
                        this$0.q = sb2.toString();
                    }
                }
            }
            this$0.t();
            this$0.finish();
            return;
        }
        this$0.showNoInternetMessage();
    }

    public static final void y(LinkToAlexaActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void z(LinkToAlexaActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0)) {
            Toast.makeText(this$0, R.string.no_internet_connection, 0).show();
            return;
        }
        AlexaLocale selectedAlexaLocale = SessionManager.getInstance(this$0).getSelectedAlexaLocale();
        String helpUrl = selectedAlexaLocale != null ? selectedAlexaLocale.getHelpUrl() : null;
        if (!(helpUrl == null || helpUrl.length() == 0)) {
            Intent intent = new Intent(this$0, AlexaActivityInAppWebViewer.class);
            AlexaActivityInAppWebViewer.Companion companion = AlexaActivityInAppWebViewer.Companion;
            intent.putExtra(companion.getINTENT_EXTRA_TITLE(), this$0.getString(R.string.alexa_help_doc));
            String intent_extra_url = companion.getINTENT_EXTRA_URL();
            AlexaLocale selectedAlexaLocale2 = SessionManager.getInstance(this$0).getSelectedAlexaLocale();
            intent.putExtra(intent_extra_url, selectedAlexaLocale2 != null ? selectedAlexaLocale2.getHelpUrl() : null);
            intent.putExtra(companion.getIS_HEADER_REQURIED(), false);
            this$0.startActivity(intent);
            return;
        }
        Toast.makeText(this$0, R.string.coming_soon, 0).show();
    }

    public final void A() {
        Toast.makeText(this, getString(com.coveiot.android.theme.R.string.alexa_app_fallback_url_missing), 0).show();
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

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_alexa_linking);
        x();
        ((Button) _$_findCachedViewById(R.id.textLoginToAlexa)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.remotecommandframework.alexa.activity.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LinkToAlexaActivity.v(LinkToAlexaActivity.this, view);
            }
        });
    }

    public final void t() {
        Unit unit;
        Unit unit2;
        String str = this.p;
        Unit unit3 = null;
        if (str != null) {
            if (AlexaAppUtils.isAlexaAppInstalled(this)) {
                Intent u = u(str);
                w();
                startActivity(u);
            } else {
                String str2 = this.q;
                if (str2 != null) {
                    Intent u2 = u(str2);
                    w();
                    startActivity(u2);
                    unit2 = Unit.INSTANCE;
                } else {
                    unit2 = null;
                }
                if (unit2 == null) {
                    A();
                }
            }
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            String str3 = this.q;
            if (str3 != null) {
                Intent u3 = u(str3);
                w();
                startActivity(u3);
                unit3 = Unit.INSTANCE;
            }
            if (unit3 == null) {
                A();
            }
        }
    }

    public final Intent u(String str) {
        return new Intent("android.intent.action.VIEW", Uri.parse(str));
    }

    public final void w() {
        SessionManager sessionManager = SessionManager.getInstance(this);
        String str = this.r;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stateToVerify");
            str = null;
        }
        sessionManager.saveAlexaAccountLinkingAppStateCode(str);
    }

    public final void x() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.glimpse_of_what_you_can_do_on_alexa));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.remotecommandframework.alexa.activity.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LinkToAlexaActivity.y(LinkToAlexaActivity.this, view);
            }
        });
        int i = R.id.share_iv;
        ((ImageView) findViewById(i)).setImageResource(R.drawable.info_icon_new_small);
        ((ImageView) findViewById(i)).setVisibility(8);
        ((ImageView) findViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.remotecommandframework.alexa.activity.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LinkToAlexaActivity.z(LinkToAlexaActivity.this, view);
            }
        });
    }
}
