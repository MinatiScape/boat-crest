package com.clevertap.android.sdk.inapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.DidClickForHardPermissionListener;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.customviews.CloseImageView;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes2.dex */
public abstract class CTInAppBaseFragment extends Fragment {
    public CleverTapInstanceConfig i;
    public Context j;
    public int k;
    public CTInAppNotification l;
    public WeakReference<InAppListener> n;
    public DidClickForHardPermissionListener o;
    public CloseImageView h = null;
    public AtomicBoolean m = new AtomicBoolean();

    /* loaded from: classes2.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CTInAppBaseFragment.this.i(((Integer) view.getTag()).intValue());
        }
    }

    abstract void b();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(Bundle bundle, HashMap<String, String> hashMap) {
        InAppListener g = g();
        if (g != null) {
            g.inAppNotificationDidClick(this.l, bundle, hashMap);
        }
    }

    void d(Bundle bundle) {
        InAppListener g = g();
        if (g != null) {
            g.inAppNotificationDidShow(this.l, bundle);
        }
    }

    public void didDismiss(Bundle bundle) {
        b();
        InAppListener g = g();
        if (g == null || getActivity() == null || getActivity().getBaseContext() == null) {
            return;
        }
        g.inAppNotificationDidDismiss(getActivity().getBaseContext(), this.l, bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(String str, Bundle bundle) {
        try {
            Uri parse = Uri.parse(str.replace("\n", "").replace("\r", ""));
            Set<String> queryParameterNames = parse.getQueryParameterNames();
            Bundle bundle2 = new Bundle();
            if (queryParameterNames != null && !queryParameterNames.isEmpty()) {
                for (String str2 : queryParameterNames) {
                    bundle2.putString(str2, parse.getQueryParameter(str2));
                }
            }
            Intent intent = new Intent("android.intent.action.VIEW", parse);
            if (!bundle2.isEmpty()) {
                intent.putExtras(bundle2);
            }
            Utils.setPackageNameFromResolveInfoList(getActivity(), intent);
            startActivity(intent);
        } catch (Throwable unused) {
        }
        didDismiss(bundle);
    }

    public abstract void f();

    InAppListener g() {
        InAppListener inAppListener;
        try {
            inAppListener = this.n.get();
        } catch (Throwable unused) {
            inAppListener = null;
        }
        if (inAppListener == null) {
            Logger logger = this.i.getLogger();
            String accountId = this.i.getAccountId();
            logger.verbose(accountId, "InAppListener is null for notification: " + this.l.getJsonDescription());
        }
        return inAppListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int h(int i) {
        return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    public void i(int i) {
        DidClickForHardPermissionListener didClickForHardPermissionListener;
        DidClickForHardPermissionListener didClickForHardPermissionListener2;
        try {
            CTInAppNotificationButton cTInAppNotificationButton = this.l.getButtons().get(i);
            Bundle bundle = new Bundle();
            bundle.putString(Constants.NOTIFICATION_ID_TAG, this.l.getCampaignId());
            bundle.putString(Constants.KEY_C2A, cTInAppNotificationButton.getText());
            c(bundle, cTInAppNotificationButton.getKeyValues());
            if (i == 0 && this.l.isLocalInApp() && (didClickForHardPermissionListener2 = this.o) != null) {
                didClickForHardPermissionListener2.didClickForHardPermissionWithFallbackSettings(this.l.fallBackToNotificationSettings());
            } else if (i == 1 && this.l.isLocalInApp()) {
                didDismiss(bundle);
            } else if (cTInAppNotificationButton.getType() != null && cTInAppNotificationButton.getType().contains(Constants.KEY_REQUEST_FOR_NOTIFICATION_PERMISSION) && (didClickForHardPermissionListener = this.o) != null) {
                didClickForHardPermissionListener.didClickForHardPermissionWithFallbackSettings(cTInAppNotificationButton.isFallbackToSettings());
            } else {
                String actionUrl = cTInAppNotificationButton.getActionUrl();
                if (actionUrl != null) {
                    e(actionUrl, bundle);
                } else {
                    didDismiss(bundle);
                }
            }
        } catch (Throwable th) {
            Logger logger = this.i.getLogger();
            logger.debug("Error handling notification button click: " + th.getCause());
            didDismiss(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(InAppListener inAppListener) {
        this.n = new WeakReference<>(inAppListener);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.j = context;
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.l = (CTInAppNotification) arguments.getParcelable(Constants.INAPP_KEY);
            this.i = (CleverTapInstanceConfig) arguments.getParcelable(Constants.KEY_CONFIG);
            this.k = getResources().getConfiguration().orientation;
            f();
            if (context instanceof DidClickForHardPermissionListener) {
                this.o = (DidClickForHardPermissionListener) context;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        d(null);
    }
}
