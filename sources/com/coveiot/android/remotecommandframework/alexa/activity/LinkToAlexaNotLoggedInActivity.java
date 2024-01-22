package com.coveiot.android.remotecommandframework.alexa.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.coveiot.android.remotecommandframework.R;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class LinkToAlexaNotLoggedInActivity extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final void s(LinkToAlexaNotLoggedInActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent();
        intent.setAction("BOAT_SPLASHf");
        this$0.startActivity(intent);
        this$0.finish();
    }

    public static final void t(LinkToAlexaNotLoggedInActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Build.VERSION.SDK_INT > 21) {
            this$0.finishAndRemoveTask();
        } else {
            this$0.finish();
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

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_alexa_linking_not_logged_in);
        ((Button) _$_findCachedViewById(R.id.textLoginToAlexa)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.remotecommandframework.alexa.activity.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LinkToAlexaNotLoggedInActivity.s(LinkToAlexaNotLoggedInActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tv_doItLater)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.remotecommandframework.alexa.activity.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LinkToAlexaNotLoggedInActivity.t(LinkToAlexaNotLoggedInActivity.this, view);
            }
        });
    }
}
