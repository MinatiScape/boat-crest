package com.clevertap.android.sdk.inapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.customviews.CloseImageView;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class CTInAppNativeHalfInterstitialFragment extends CTInAppBaseFullNativeFragment {
    public RelativeLayout p;

    /* loaded from: classes2.dex */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public final /* synthetic */ LayoutInflater h;
        public final /* synthetic */ CloseImageView i;

        public a(LayoutInflater layoutInflater, CloseImageView closeImageView) {
            this.h = layoutInflater;
            this.i = closeImageView;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CTInAppNativeHalfInterstitialFragment.this.p.getLayoutParams();
            if ((CTInAppNativeHalfInterstitialFragment.this.l.y() && CTInAppNativeHalfInterstitialFragment.this.l()) || (CTInAppNativeHalfInterstitialFragment.this.l.isLocalInApp() && CTInAppNativeHalfInterstitialFragment.this.x(this.h.getContext()))) {
                CTInAppNativeHalfInterstitialFragment cTInAppNativeHalfInterstitialFragment = CTInAppNativeHalfInterstitialFragment.this;
                cTInAppNativeHalfInterstitialFragment.m(cTInAppNativeHalfInterstitialFragment.p, layoutParams, this.i);
            } else if (CTInAppNativeHalfInterstitialFragment.this.l()) {
                CTInAppNativeHalfInterstitialFragment cTInAppNativeHalfInterstitialFragment2 = CTInAppNativeHalfInterstitialFragment.this;
                cTInAppNativeHalfInterstitialFragment2.n(cTInAppNativeHalfInterstitialFragment2.p, layoutParams, this.i);
            } else {
                CTInAppNativeHalfInterstitialFragment cTInAppNativeHalfInterstitialFragment3 = CTInAppNativeHalfInterstitialFragment.this;
                cTInAppNativeHalfInterstitialFragment3.m(cTInAppNativeHalfInterstitialFragment3.p, layoutParams, this.i);
            }
            CTInAppNativeHalfInterstitialFragment.this.p.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    /* loaded from: classes2.dex */
    public class b implements ViewTreeObserver.OnGlobalLayoutListener {
        public final /* synthetic */ FrameLayout h;
        public final /* synthetic */ CloseImageView i;

        /* loaded from: classes2.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                int measuredWidth = b.this.i.getMeasuredWidth() / 2;
                b bVar = b.this;
                bVar.i.setX(CTInAppNativeHalfInterstitialFragment.this.p.getRight() - measuredWidth);
                b bVar2 = b.this;
                bVar2.i.setY(CTInAppNativeHalfInterstitialFragment.this.p.getTop() - measuredWidth);
            }
        }

        /* renamed from: com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialFragment$b$b  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public class RunnableC0227b implements Runnable {
            public RunnableC0227b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                int measuredWidth = b.this.i.getMeasuredWidth() / 2;
                b bVar = b.this;
                bVar.i.setX(CTInAppNativeHalfInterstitialFragment.this.p.getRight() - measuredWidth);
                b bVar2 = b.this;
                bVar2.i.setY(CTInAppNativeHalfInterstitialFragment.this.p.getTop() - measuredWidth);
            }
        }

        /* loaded from: classes2.dex */
        public class c implements Runnable {
            public c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                int measuredWidth = b.this.i.getMeasuredWidth() / 2;
                b bVar = b.this;
                bVar.i.setX(CTInAppNativeHalfInterstitialFragment.this.p.getRight() - measuredWidth);
                b bVar2 = b.this;
                bVar2.i.setY(CTInAppNativeHalfInterstitialFragment.this.p.getTop() - measuredWidth);
            }
        }

        public b(FrameLayout frameLayout, CloseImageView closeImageView) {
            this.h = frameLayout;
            this.i = closeImageView;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            RelativeLayout relativeLayout = (RelativeLayout) this.h.findViewById(R.id.half_interstitial_relative_layout);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) relativeLayout.getLayoutParams();
            if (CTInAppNativeHalfInterstitialFragment.this.l.y() && CTInAppNativeHalfInterstitialFragment.this.l()) {
                layoutParams.width = (int) (relativeLayout.getMeasuredHeight() * 1.3f);
                layoutParams.gravity = 17;
                relativeLayout.setLayoutParams(layoutParams);
                new Handler().post(new c());
            } else if (CTInAppNativeHalfInterstitialFragment.this.l()) {
                layoutParams.setMargins(CTInAppNativeHalfInterstitialFragment.this.h(140), CTInAppNativeHalfInterstitialFragment.this.h(100), CTInAppNativeHalfInterstitialFragment.this.h(140), CTInAppNativeHalfInterstitialFragment.this.h(100));
                int measuredHeight = relativeLayout.getMeasuredHeight() - CTInAppNativeHalfInterstitialFragment.this.h(130);
                layoutParams.height = measuredHeight;
                layoutParams.width = (int) (measuredHeight * 1.3f);
                layoutParams.gravity = 17;
                relativeLayout.setLayoutParams(layoutParams);
                new Handler().post(new a());
            } else {
                layoutParams.width = (int) (relativeLayout.getMeasuredHeight() * 1.3f);
                layoutParams.gravity = 1;
                relativeLayout.setLayoutParams(layoutParams);
                new Handler().post(new RunnableC0227b());
            }
            CTInAppNativeHalfInterstitialFragment.this.p.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    /* loaded from: classes2.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CTInAppNativeHalfInterstitialFragment.this.didDismiss(null);
            CTInAppNativeHalfInterstitialFragment.this.getActivity().finish();
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        ArrayList arrayList = new ArrayList();
        if ((this.l.y() && l()) || (this.l.isLocalInApp() && x(layoutInflater.getContext()))) {
            inflate = layoutInflater.inflate(R.layout.tab_inapp_half_interstitial, viewGroup, false);
        } else {
            inflate = layoutInflater.inflate(R.layout.inapp_half_interstitial, viewGroup, false);
        }
        FrameLayout frameLayout = (FrameLayout) inflate.findViewById(R.id.inapp_half_interstitial_frame_layout);
        CloseImageView closeImageView = (CloseImageView) frameLayout.findViewById(199272);
        RelativeLayout relativeLayout = (RelativeLayout) frameLayout.findViewById(R.id.half_interstitial_relative_layout);
        this.p = relativeLayout;
        relativeLayout.setBackgroundColor(Color.parseColor(this.l.c()));
        int i = this.k;
        if (i == 1) {
            this.p.getViewTreeObserver().addOnGlobalLayoutListener(new a(layoutInflater, closeImageView));
        } else if (i == 2) {
            this.p.getViewTreeObserver().addOnGlobalLayoutListener(new b(frameLayout, closeImageView));
        }
        if (this.l.m(this.k) != null) {
            CTInAppNotification cTInAppNotification = this.l;
            if (cTInAppNotification.l(cTInAppNotification.m(this.k)) != null) {
                CTInAppNotification cTInAppNotification2 = this.l;
                ((ImageView) this.p.findViewById(R.id.backgroundImage)).setImageBitmap(cTInAppNotification2.l(cTInAppNotification2.m(this.k)));
            }
        }
        LinearLayout linearLayout = (LinearLayout) this.p.findViewById(R.id.half_interstitial_linear_layout);
        Button button = (Button) linearLayout.findViewById(R.id.half_interstitial_button1);
        arrayList.add(button);
        Button button2 = (Button) linearLayout.findViewById(R.id.half_interstitial_button2);
        arrayList.add(button2);
        TextView textView = (TextView) this.p.findViewById(R.id.half_interstitial_title);
        textView.setText(this.l.getTitle());
        textView.setTextColor(Color.parseColor(this.l.q()));
        TextView textView2 = (TextView) this.p.findViewById(R.id.half_interstitial_message);
        textView2.setText(this.l.getMessage());
        textView2.setTextColor(Color.parseColor(this.l.o()));
        ArrayList<CTInAppNotificationButton> buttons = this.l.getButtons();
        if (buttons.size() == 1) {
            int i2 = this.k;
            if (i2 == 2) {
                button.setVisibility(8);
            } else if (i2 == 1) {
                button.setVisibility(4);
            }
            v(button2, buttons.get(0), 0);
        } else if (!buttons.isEmpty()) {
            for (int i3 = 0; i3 < buttons.size(); i3++) {
                if (i3 < 2) {
                    v((Button) arrayList.get(i3), buttons.get(i3), i3);
                }
            }
        }
        frameLayout.setBackground(new ColorDrawable(-1157627904));
        closeImageView.setOnClickListener(new c());
        if (!this.l.u()) {
            closeImageView.setVisibility(8);
        } else {
            closeImageView.setVisibility(0);
        }
        return inflate;
    }

    public boolean x(Context context) {
        return DeviceInfo.getDeviceType(context) == 2;
    }
}
