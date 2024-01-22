package com.clevertap.android.sdk.inapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.customviews.CloseImageView;
import com.clevertap.android.sdk.inapp.CTInAppBaseFragment;
/* loaded from: classes2.dex */
public class CTInAppNativeInterstitialImageFragment extends CTInAppBaseFullFragment {
    public RelativeLayout p;

    /* loaded from: classes2.dex */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public final /* synthetic */ FrameLayout h;
        public final /* synthetic */ CloseImageView i;

        public a(FrameLayout frameLayout, CloseImageView closeImageView) {
            this.h = frameLayout;
            this.i = closeImageView;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CTInAppNativeInterstitialImageFragment.this.p.getLayoutParams();
            if (CTInAppNativeInterstitialImageFragment.this.l.y() && CTInAppNativeInterstitialImageFragment.this.l()) {
                CTInAppNativeInterstitialImageFragment cTInAppNativeInterstitialImageFragment = CTInAppNativeInterstitialImageFragment.this;
                cTInAppNativeInterstitialImageFragment.q(cTInAppNativeInterstitialImageFragment.p, layoutParams, this.h, this.i);
            } else if (CTInAppNativeInterstitialImageFragment.this.l()) {
                CTInAppNativeInterstitialImageFragment cTInAppNativeInterstitialImageFragment2 = CTInAppNativeInterstitialImageFragment.this;
                cTInAppNativeInterstitialImageFragment2.p(cTInAppNativeInterstitialImageFragment2.p, layoutParams, this.h, this.i);
            } else {
                CTInAppNativeInterstitialImageFragment cTInAppNativeInterstitialImageFragment3 = CTInAppNativeInterstitialImageFragment.this;
                cTInAppNativeInterstitialImageFragment3.o(cTInAppNativeInterstitialImageFragment3.p, layoutParams, this.i);
            }
            CTInAppNativeInterstitialImageFragment.this.p.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    /* loaded from: classes2.dex */
    public class b implements ViewTreeObserver.OnGlobalLayoutListener {
        public final /* synthetic */ FrameLayout h;
        public final /* synthetic */ CloseImageView i;

        public b(FrameLayout frameLayout, CloseImageView closeImageView) {
            this.h = frameLayout;
            this.i = closeImageView;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CTInAppNativeInterstitialImageFragment.this.p.getLayoutParams();
            if (CTInAppNativeInterstitialImageFragment.this.l.y() && CTInAppNativeInterstitialImageFragment.this.l()) {
                CTInAppNativeInterstitialImageFragment cTInAppNativeInterstitialImageFragment = CTInAppNativeInterstitialImageFragment.this;
                cTInAppNativeInterstitialImageFragment.t(cTInAppNativeInterstitialImageFragment.p, layoutParams, this.h, this.i);
            } else if (CTInAppNativeInterstitialImageFragment.this.l()) {
                CTInAppNativeInterstitialImageFragment cTInAppNativeInterstitialImageFragment2 = CTInAppNativeInterstitialImageFragment.this;
                cTInAppNativeInterstitialImageFragment2.s(cTInAppNativeInterstitialImageFragment2.p, layoutParams, this.h, this.i);
            } else {
                CTInAppNativeInterstitialImageFragment cTInAppNativeInterstitialImageFragment3 = CTInAppNativeInterstitialImageFragment.this;
                cTInAppNativeInterstitialImageFragment3.r(cTInAppNativeInterstitialImageFragment3.p, layoutParams, this.i);
            }
            CTInAppNativeInterstitialImageFragment.this.p.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    /* loaded from: classes2.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CTInAppNativeInterstitialImageFragment.this.didDismiss(null);
            CTInAppNativeInterstitialImageFragment.this.getActivity().finish();
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        if (this.l.y() && l()) {
            inflate = layoutInflater.inflate(R.layout.tab_inapp_interstitial_image, viewGroup, false);
        } else {
            inflate = layoutInflater.inflate(R.layout.inapp_interstitial_image, viewGroup, false);
        }
        FrameLayout frameLayout = (FrameLayout) inflate.findViewById(R.id.inapp_interstitial_image_frame_layout);
        frameLayout.setBackground(new ColorDrawable(-1157627904));
        CloseImageView closeImageView = (CloseImageView) frameLayout.findViewById(199272);
        RelativeLayout relativeLayout = (RelativeLayout) frameLayout.findViewById(R.id.interstitial_image_relative_layout);
        this.p = relativeLayout;
        relativeLayout.setBackgroundColor(Color.parseColor(this.l.c()));
        ImageView imageView = (ImageView) this.p.findViewById(R.id.interstitial_image);
        int i = this.k;
        if (i == 1) {
            this.p.getViewTreeObserver().addOnGlobalLayoutListener(new a(frameLayout, closeImageView));
        } else if (i == 2) {
            this.p.getViewTreeObserver().addOnGlobalLayoutListener(new b(frameLayout, closeImageView));
        }
        if (this.l.m(this.k) != null) {
            CTInAppNotification cTInAppNotification = this.l;
            if (cTInAppNotification.l(cTInAppNotification.m(this.k)) != null) {
                CTInAppNotification cTInAppNotification2 = this.l;
                imageView.setImageBitmap(cTInAppNotification2.l(cTInAppNotification2.m(this.k)));
                imageView.setTag(0);
                imageView.setOnClickListener(new CTInAppBaseFragment.a());
            }
        }
        closeImageView.setOnClickListener(new c());
        if (!this.l.u()) {
            closeImageView.setVisibility(8);
        } else {
            closeImageView.setVisibility(0);
        }
        return inflate;
    }
}
