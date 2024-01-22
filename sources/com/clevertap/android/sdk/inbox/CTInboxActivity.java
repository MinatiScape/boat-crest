package com.clevertap.android.sdk.inbox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import com.clevertap.android.sdk.CTInboxStyleConfig;
import com.clevertap.android.sdk.CTPreferenceCache;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.DidClickForHardPermissionListener;
import com.clevertap.android.sdk.InAppNotificationActivity;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.PushPermissionManager;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.inbox.CTInboxListViewFragment;
import com.google.android.material.tabs.TabLayout;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class CTInboxActivity extends FragmentActivity implements CTInboxListViewFragment.b, DidClickForHardPermissionListener {
    public static int orientation;
    public CTInboxTabAdapter h;
    public CTInboxStyleConfig i;
    public TabLayout j;
    public ViewPager k;
    public CleverTapInstanceConfig l;
    public WeakReference<InboxActivityListener> m;
    public CleverTapAPI n;
    public PushPermissionManager o;
    public WeakReference<InAppNotificationActivity.PushPermissionResultCallback> p;

    /* loaded from: classes2.dex */
    public interface InboxActivityListener {
        void messageDidClick(CTInboxActivity cTInboxActivity, int i, CTInboxMessage cTInboxMessage, Bundle bundle, HashMap<String, String> hashMap, int i2);

        void messageDidShow(CTInboxActivity cTInboxActivity, CTInboxMessage cTInboxMessage, Bundle bundle);
    }

    /* loaded from: classes2.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CTInboxActivity.this.finish();
        }
    }

    /* loaded from: classes2.dex */
    public class b implements TabLayout.OnTabSelectedListener {
        public b() {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            CTInboxListViewFragment cTInboxListViewFragment = (CTInboxListViewFragment) CTInboxActivity.this.h.getItem(tab.getPosition());
            if (cTInboxListViewFragment.f() != null) {
                cTInboxListViewFragment.f().onRestartPlayer();
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
            CTInboxListViewFragment cTInboxListViewFragment = (CTInboxListViewFragment) CTInboxActivity.this.h.getItem(tab.getPosition());
            if (cTInboxListViewFragment.f() != null) {
                cTInboxListViewFragment.f().onPausePlayer();
            }
        }
    }

    public void c(Bundle bundle, int i, CTInboxMessage cTInboxMessage, HashMap<String, String> hashMap, int i2) {
        InboxActivityListener f = f();
        if (f != null) {
            f.messageDidClick(this, i, cTInboxMessage, bundle, hashMap, i2);
        }
    }

    public void d(Bundle bundle, CTInboxMessage cTInboxMessage) {
        Logger.v("CTInboxActivity:didShow() called with: data = [" + bundle + "], inboxMessage = [" + cTInboxMessage.getMessageId() + "]");
        InboxActivityListener f = f();
        if (f != null) {
            f.messageDidShow(this, cTInboxMessage, bundle);
        }
    }

    @Override // com.clevertap.android.sdk.DidClickForHardPermissionListener
    public void didClickForHardPermissionWithFallbackSettings(boolean z) {
        showHardPermissionPrompt(z);
    }

    public final String e() {
        return this.l.getAccountId() + ":CT_INBOX_LIST_VIEW_FRAGMENT";
    }

    public InboxActivityListener f() {
        InboxActivityListener inboxActivityListener;
        try {
            inboxActivityListener = this.m.get();
        } catch (Throwable unused) {
            inboxActivityListener = null;
        }
        if (inboxActivityListener == null) {
            this.l.getLogger().verbose(this.l.getAccountId(), "InboxActivityListener is null for notification inbox ");
        }
        return inboxActivityListener;
    }

    public void g(InboxActivityListener inboxActivityListener) {
        this.m = new WeakReference<>(inboxActivityListener);
    }

    @Override // com.clevertap.android.sdk.inbox.CTInboxListViewFragment.b
    public void messageDidClick(Context context, int i, CTInboxMessage cTInboxMessage, Bundle bundle, HashMap<String, String> hashMap, int i2) {
        c(bundle, i, cTInboxMessage, hashMap, i2);
    }

    @Override // com.clevertap.android.sdk.inbox.CTInboxListViewFragment.b
    public void messageDidShow(Context context, CTInboxMessage cTInboxMessage, Bundle bundle) {
        Logger.v("CTInboxActivity:messageDidShow() called with: data = [" + bundle + "], inboxMessage = [" + cTInboxMessage.getMessageId() + "]");
        d(bundle, cTInboxMessage);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                this.i = (CTInboxStyleConfig) extras.getParcelable("styleConfig");
                Bundle bundle2 = extras.getBundle("configBundle");
                if (bundle2 != null) {
                    this.l = (CleverTapInstanceConfig) bundle2.getParcelable(Constants.KEY_CONFIG);
                }
                CleverTapAPI instanceWithConfig = CleverTapAPI.instanceWithConfig(getApplicationContext(), this.l);
                this.n = instanceWithConfig;
                if (instanceWithConfig != null) {
                    g(instanceWithConfig);
                    setPermissionCallback(CleverTapAPI.instanceWithConfig(this, this.l).getCoreState().getInAppController());
                    this.o = new PushPermissionManager(this, this.l);
                }
                orientation = getResources().getConfiguration().orientation;
                setContentView(R.layout.inbox_activity);
                this.n.getCoreState().getCoreMetaData().setAppInboxActivity(this);
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                toolbar.setTitle(this.i.getNavBarTitle());
                toolbar.setTitleTextColor(Color.parseColor(this.i.getNavBarTitleColor()));
                toolbar.setBackgroundColor(Color.parseColor(this.i.getNavBarColor()));
                Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ct_ic_arrow_back_white_24dp, null);
                if (drawable != null) {
                    drawable.setColorFilter(Color.parseColor(this.i.getBackButtonColor()), PorterDuff.Mode.SRC_IN);
                }
                toolbar.setNavigationIcon(drawable);
                toolbar.setNavigationOnClickListener(new a());
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.inbox_linear_layout);
                linearLayout.setBackgroundColor(Color.parseColor(this.i.getInboxBackgroundColor()));
                this.j = (TabLayout) linearLayout.findViewById(R.id.tab_layout);
                this.k = (ViewPager) linearLayout.findViewById(R.id.view_pager);
                TextView textView = (TextView) findViewById(R.id.no_message_view);
                Bundle bundle3 = new Bundle();
                bundle3.putParcelable(Constants.KEY_CONFIG, this.l);
                bundle3.putParcelable("styleConfig", this.i);
                int i = 0;
                if (!this.i.isUsingTabs()) {
                    this.k.setVisibility(8);
                    this.j.setVisibility(8);
                    ((FrameLayout) findViewById(R.id.list_view_fragment)).setVisibility(0);
                    CleverTapAPI cleverTapAPI = this.n;
                    if (cleverTapAPI != null && cleverTapAPI.getInboxMessageCount() == 0) {
                        textView.setBackgroundColor(Color.parseColor(this.i.getInboxBackgroundColor()));
                        textView.setVisibility(0);
                        textView.setText(this.i.getNoMessageViewText());
                        textView.setTextColor(Color.parseColor(this.i.getNoMessageViewTextColor()));
                        return;
                    }
                    textView.setVisibility(8);
                    for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                        if (fragment.getTag() != null && !fragment.getTag().equalsIgnoreCase(e())) {
                            i = 1;
                        }
                    }
                    if (i == 0) {
                        Fragment cTInboxListViewFragment = new CTInboxListViewFragment();
                        cTInboxListViewFragment.setArguments(bundle3);
                        getSupportFragmentManager().beginTransaction().add(R.id.list_view_fragment, cTInboxListViewFragment, e()).commit();
                        return;
                    }
                    return;
                }
                this.k.setVisibility(0);
                ArrayList<String> tabs = this.i.getTabs();
                this.h = new CTInboxTabAdapter(getSupportFragmentManager(), tabs.size() + 1);
                this.j.setVisibility(0);
                this.j.setTabGravity(0);
                this.j.setTabMode(1);
                this.j.setSelectedTabIndicatorColor(Color.parseColor(this.i.getSelectedTabIndicatorColor()));
                this.j.setTabTextColors(Color.parseColor(this.i.getUnselectedTabColor()), Color.parseColor(this.i.getSelectedTabColor()));
                this.j.setBackgroundColor(Color.parseColor(this.i.getTabBackgroundColor()));
                Bundle bundle4 = (Bundle) bundle3.clone();
                bundle4.putInt(DeviceKey.position, 0);
                CTInboxListViewFragment cTInboxListViewFragment2 = new CTInboxListViewFragment();
                cTInboxListViewFragment2.setArguments(bundle4);
                this.h.c(cTInboxListViewFragment2, this.i.getFirstTabTitle(), 0);
                while (i < tabs.size()) {
                    String str = tabs.get(i);
                    i++;
                    Bundle bundle5 = (Bundle) bundle3.clone();
                    bundle5.putInt(DeviceKey.position, i);
                    bundle5.putString("filter", str);
                    CTInboxListViewFragment cTInboxListViewFragment3 = new CTInboxListViewFragment();
                    cTInboxListViewFragment3.setArguments(bundle5);
                    this.h.c(cTInboxListViewFragment3, str, i);
                    this.k.setOffscreenPageLimit(i);
                }
                this.k.setAdapter(this.h);
                this.h.notifyDataSetChanged();
                this.k.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(this.j));
                this.j.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new b());
                this.j.setupWithViewPager(this.k);
                return;
            }
            throw new IllegalArgumentException();
        } catch (Throwable th) {
            Logger.v("Cannot find a valid notification inbox bundle to show!", th);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.n.getCoreState().getCoreMetaData().setAppInboxActivity(null);
        if (this.i.isUsingTabs()) {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                if (fragment instanceof CTInboxListViewFragment) {
                    Logger.v("Removing fragment - " + fragment.toString());
                    getSupportFragmentManager().getFragments().remove(fragment);
                }
            }
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        boolean z = false;
        CTPreferenceCache.getInstance(this, this.l).setFirstTimeRequest(false);
        CTPreferenceCache.updateCacheToDisk(this, this.l);
        if (i == 102) {
            if (iArr.length > 0 && iArr[0] == 0) {
                z = true;
            }
            if (z) {
                this.p.get().onPushPermissionAccept();
            } else {
                this.p.get().onPushPermissionDeny();
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (!this.o.isFromNotificationSettingsActivity() || Build.VERSION.SDK_INT < 33) {
            return;
        }
        if (ContextCompat.checkSelfPermission(this, PushPermissionManager.ANDROID_PERMISSION_STRING) == 0) {
            this.p.get().onPushPermissionAccept();
        } else {
            this.p.get().onPushPermissionDeny();
        }
    }

    public void setPermissionCallback(InAppNotificationActivity.PushPermissionResultCallback pushPermissionResultCallback) {
        this.p = new WeakReference<>(pushPermissionResultCallback);
    }

    @SuppressLint({"NewApi"})
    public void showHardPermissionPrompt(boolean z) {
        this.o.showHardPermissionPrompt(z, this.p.get());
    }
}
