package com.clevertap.android.sdk.inapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
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
public class CTInAppNativeHalfInterstitialImageFragment extends CTInAppBaseFullFragment {
    public RelativeLayout p;

    /* loaded from: classes2.dex */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public final /* synthetic */ CloseImageView h;

        public a(CloseImageView closeImageView) {
            this.h = closeImageView;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CTInAppNativeHalfInterstitialImageFragment.this.p.getLayoutParams();
            if (CTInAppNativeHalfInterstitialImageFragment.this.l.y() && CTInAppNativeHalfInterstitialImageFragment.this.l()) {
                CTInAppNativeHalfInterstitialImageFragment cTInAppNativeHalfInterstitialImageFragment = CTInAppNativeHalfInterstitialImageFragment.this;
                cTInAppNativeHalfInterstitialImageFragment.m(cTInAppNativeHalfInterstitialImageFragment.p, layoutParams, this.h);
            } else if (CTInAppNativeHalfInterstitialImageFragment.this.l()) {
                CTInAppNativeHalfInterstitialImageFragment cTInAppNativeHalfInterstitialImageFragment2 = CTInAppNativeHalfInterstitialImageFragment.this;
                cTInAppNativeHalfInterstitialImageFragment2.n(cTInAppNativeHalfInterstitialImageFragment2.p, layoutParams, this.h);
            } else {
                CTInAppNativeHalfInterstitialImageFragment cTInAppNativeHalfInterstitialImageFragment3 = CTInAppNativeHalfInterstitialImageFragment.this;
                cTInAppNativeHalfInterstitialImageFragment3.m(cTInAppNativeHalfInterstitialImageFragment3.p, layoutParams, this.h);
            }
            CTInAppNativeHalfInterstitialImageFragment.this.p.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    /* loaded from: classes2.dex */
    public class b implements ViewTreeObserver.OnGlobalLayoutListener {
        public final /* synthetic */ CloseImageView h;

        /* loaded from: classes2.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                int measuredWidth = b.this.h.getMeasuredWidth() / 2;
                b bVar = b.this;
                bVar.h.setX(CTInAppNativeHalfInterstitialImageFragment.this.p.getRight() - measuredWidth);
                b bVar2 = b.this;
                bVar2.h.setY(CTInAppNativeHalfInterstitialImageFragment.this.p.getTop() - measuredWidth);
            }
        }

        /* renamed from: com.clevertap.android.sdk.inapp.CTInAppNativeHalfInterstitialImageFragment$b$b  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public class RunnableC0228b implements Runnable {
            public RunnableC0228b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                int measuredWidth = b.this.h.getMeasuredWidth() / 2;
                b bVar = b.this;
                bVar.h.setX(CTInAppNativeHalfInterstitialImageFragment.this.p.getRight() - measuredWidth);
                b bVar2 = b.this;
                bVar2.h.setY(CTInAppNativeHalfInterstitialImageFragment.this.p.getTop() - measuredWidth);
            }
        }

        /* loaded from: classes2.dex */
        public class c implements Runnable {
            public c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                int measuredWidth = b.this.h.getMeasuredWidth() / 2;
                b bVar = b.this;
                bVar.h.setX(CTInAppNativeHalfInterstitialImageFragment.this.p.getRight() - measuredWidth);
                b bVar2 = b.this;
                bVar2.h.setY(CTInAppNativeHalfInterstitialImageFragment.this.p.getTop() - measuredWidth);
            }
        }

        public b(CloseImageView closeImageView) {
            this.h = closeImageView;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CTInAppNativeHalfInterstitialImageFragment.this.p.getLayoutParams();
            if (CTInAppNativeHalfInterstitialImageFragment.this.l.y() && CTInAppNativeHalfInterstitialImageFragment.this.l()) {
                layoutParams.width = (int) (CTInAppNativeHalfInterstitialImageFragment.this.p.getMeasuredHeight() * 1.3f);
                layoutParams.gravity = 17;
                CTInAppNativeHalfInterstitialImageFragment.this.p.setLayoutParams(layoutParams);
                new Handler().post(new c());
            } else if (!CTInAppNativeHalfInterstitialImageFragment.this.l()) {
                layoutParams.width = (int) (CTInAppNativeHalfInterstitialImageFragment.this.p.getMeasuredHeight() * 1.3f);
                layoutParams.gravity = 1;
                CTInAppNativeHalfInterstitialImageFragment.this.p.setLayoutParams(layoutParams);
                new Handler().post(new RunnableC0228b());
            } else {
                layoutParams.setMargins(CTInAppNativeHalfInterstitialImageFragment.this.h(140), CTInAppNativeHalfInterstitialImageFragment.this.h(100), CTInAppNativeHalfInterstitialImageFragment.this.h(140), CTInAppNativeHalfInterstitialImageFragment.this.h(100));
                int measuredHeight = CTInAppNativeHalfInterstitialImageFragment.this.p.getMeasuredHeight() - CTInAppNativeHalfInterstitialImageFragment.this.h(130);
                layoutParams.height = measuredHeight;
                layoutParams.width = (int) (measuredHeight * 1.3f);
                layoutParams.gravity = 17;
                CTInAppNativeHalfInterstitialImageFragment.this.p.setLayoutParams(layoutParams);
                new Handler().post(new a());
            }
            CTInAppNativeHalfInterstitialImageFragment.this.p.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    /* loaded from: classes2.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CTInAppNativeHalfInterstitialImageFragment.this.didDismiss(null);
            CTInAppNativeHalfInterstitialImageFragment.this.getActivity().finish();
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        if (this.l.y() && l()) {
            inflate = layoutInflater.inflate(R.layout.tab_inapp_half_interstitial_image, viewGroup, false);
        } else {
            inflate = layoutInflater.inflate(R.layout.inapp_half_interstitial_image, viewGroup, false);
        }
        FrameLayout frameLayout = (FrameLayout) inflate.findViewById(R.id.inapp_half_interstitial_image_frame_layout);
        CloseImageView closeImageView = (CloseImageView) frameLayout.findViewById(199272);
        frameLayout.setBackground(new ColorDrawable(-1157627904));
        RelativeLayout relativeLayout = (RelativeLayout) frameLayout.findViewById(R.id.half_interstitial_image_relative_layout);
        this.p = relativeLayout;
        relativeLayout.setBackgroundColor(Color.parseColor(this.l.c()));
        ImageView imageView = (ImageView) this.p.findViewById(R.id.half_interstitial_image);
        int i = this.k;
        if (i == 1) {
            this.p.getViewTreeObserver().addOnGlobalLayoutListener(new a(closeImageView));
        } else if (i == 2) {
            this.p.getViewTreeObserver().addOnGlobalLayoutListener(new b(closeImageView));
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
