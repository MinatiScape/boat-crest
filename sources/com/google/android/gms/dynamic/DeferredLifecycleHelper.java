package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.zac;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.LinkedList;
@KeepForSdk
/* loaded from: classes6.dex */
public abstract class DeferredLifecycleHelper<T extends LifecycleDelegate> {

    /* renamed from: a  reason: collision with root package name */
    public LifecycleDelegate f8379a;
    @Nullable
    public Bundle b;
    public LinkedList c;
    public final OnDelegateCreatedListener d = new a(this);

    @KeepForSdk
    public static void showGooglePlayUnavailableMessage(@NonNull FrameLayout frameLayout) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        Context context = frameLayout.getContext();
        int isGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(context);
        String zad = zac.zad(context, isGooglePlayServicesAvailable);
        String zac = zac.zac(context, isGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(zad);
        linearLayout.addView(textView);
        Intent errorResolutionIntent = googleApiAvailability.getErrorResolutionIntent(context, isGooglePlayServicesAvailable, null);
        if (errorResolutionIntent != null) {
            Button button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(zac);
            linearLayout.addView(button);
            button.setOnClickListener(new e(context, errorResolutionIntent));
        }
    }

    @KeepForSdk
    public abstract void createDelegate(@NonNull OnDelegateCreatedListener<T> onDelegateCreatedListener);

    public final void e(int i) {
        while (!this.c.isEmpty() && ((h) this.c.getLast()).zaa() >= i) {
            this.c.removeLast();
        }
    }

    public final void f(@Nullable Bundle bundle, h hVar) {
        LifecycleDelegate lifecycleDelegate = this.f8379a;
        if (lifecycleDelegate != null) {
            hVar.a(lifecycleDelegate);
            return;
        }
        if (this.c == null) {
            this.c = new LinkedList();
        }
        this.c.add(hVar);
        if (bundle != null) {
            Bundle bundle2 = this.b;
            if (bundle2 == null) {
                this.b = (Bundle) bundle.clone();
            } else {
                bundle2.putAll(bundle);
            }
        }
        createDelegate(this.d);
    }

    @NonNull
    @KeepForSdk
    public T getDelegate() {
        return (T) this.f8379a;
    }

    @KeepForSdk
    public void handleGooglePlayUnavailable(@NonNull FrameLayout frameLayout) {
        showGooglePlayUnavailableMessage(frameLayout);
    }

    @KeepForSdk
    public void onCreate(@Nullable Bundle bundle) {
        f(bundle, new c(this, bundle));
    }

    @NonNull
    @KeepForSdk
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        f(bundle, new d(this, frameLayout, layoutInflater, viewGroup, bundle));
        if (this.f8379a == null) {
            handleGooglePlayUnavailable(frameLayout);
        }
        return frameLayout;
    }

    @KeepForSdk
    public void onDestroy() {
        LifecycleDelegate lifecycleDelegate = this.f8379a;
        if (lifecycleDelegate != null) {
            lifecycleDelegate.onDestroy();
        } else {
            e(1);
        }
    }

    @KeepForSdk
    public void onDestroyView() {
        LifecycleDelegate lifecycleDelegate = this.f8379a;
        if (lifecycleDelegate != null) {
            lifecycleDelegate.onDestroyView();
        } else {
            e(2);
        }
    }

    @KeepForSdk
    public void onInflate(@NonNull Activity activity, @NonNull Bundle bundle, @Nullable Bundle bundle2) {
        f(bundle2, new b(this, activity, bundle, bundle2));
    }

    @KeepForSdk
    public void onLowMemory() {
        LifecycleDelegate lifecycleDelegate = this.f8379a;
        if (lifecycleDelegate != null) {
            lifecycleDelegate.onLowMemory();
        }
    }

    @KeepForSdk
    public void onPause() {
        LifecycleDelegate lifecycleDelegate = this.f8379a;
        if (lifecycleDelegate != null) {
            lifecycleDelegate.onPause();
        } else {
            e(5);
        }
    }

    @KeepForSdk
    public void onResume() {
        f(null, new g(this));
    }

    @KeepForSdk
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        LifecycleDelegate lifecycleDelegate = this.f8379a;
        if (lifecycleDelegate != null) {
            lifecycleDelegate.onSaveInstanceState(bundle);
            return;
        }
        Bundle bundle2 = this.b;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
    }

    @KeepForSdk
    public void onStart() {
        f(null, new f(this));
    }

    @KeepForSdk
    public void onStop() {
        LifecycleDelegate lifecycleDelegate = this.f8379a;
        if (lifecycleDelegate != null) {
            lifecycleDelegate.onStop();
        } else {
            e(4);
        }
    }
}
