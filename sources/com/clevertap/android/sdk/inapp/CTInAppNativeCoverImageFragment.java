package com.clevertap.android.sdk.inapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.customviews.CloseImageView;
import com.clevertap.android.sdk.inapp.CTInAppBaseFragment;
/* loaded from: classes2.dex */
public class CTInAppNativeCoverImageFragment extends CTInAppBaseFullFragment {

    /* loaded from: classes2.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CTInAppNativeCoverImageFragment.this.didDismiss(null);
            CTInAppNativeCoverImageFragment.this.getActivity().finish();
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.inapp_cover_image, viewGroup, false);
        FrameLayout frameLayout = (FrameLayout) inflate.findViewById(R.id.inapp_cover_image_frame_layout);
        frameLayout.setBackgroundColor(Color.parseColor(this.l.c()));
        ImageView imageView = (ImageView) ((RelativeLayout) frameLayout.findViewById(R.id.cover_image_relative_layout)).findViewById(R.id.cover_image);
        if (this.l.m(this.k) != null) {
            CTInAppNotification cTInAppNotification = this.l;
            if (cTInAppNotification.l(cTInAppNotification.m(this.k)) != null) {
                CTInAppNotification cTInAppNotification2 = this.l;
                imageView.setImageBitmap(cTInAppNotification2.l(cTInAppNotification2.m(this.k)));
                imageView.setTag(0);
                imageView.setOnClickListener(new CTInAppBaseFragment.a());
            }
        }
        CloseImageView closeImageView = (CloseImageView) frameLayout.findViewById(199272);
        closeImageView.setOnClickListener(new a());
        if (!this.l.u()) {
            closeImageView.setVisibility(8);
        } else {
            closeImageView.setVisibility(0);
        }
        return inflate;
    }
}
