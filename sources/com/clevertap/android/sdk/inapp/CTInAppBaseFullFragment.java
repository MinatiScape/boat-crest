package com.clevertap.android.sdk.inapp;

import android.content.Context;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.clevertap.android.sdk.InAppNotificationActivity;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.customviews.CloseImageView;
import com.jieli.jl_rcsp.constant.Command;
import com.realsil.sdk.dfu.DfuException;
/* loaded from: classes2.dex */
public abstract class CTInAppBaseFullFragment extends CTInAppBaseFragment {

    /* loaded from: classes2.dex */
    public class a implements Runnable {
        public final /* synthetic */ CloseImageView h;
        public final /* synthetic */ RelativeLayout i;

        public a(CTInAppBaseFullFragment cTInAppBaseFullFragment, CloseImageView closeImageView, RelativeLayout relativeLayout) {
            this.h = closeImageView;
            this.i = relativeLayout;
        }

        @Override // java.lang.Runnable
        public void run() {
            int measuredWidth = this.h.getMeasuredWidth() / 2;
            this.h.setX(this.i.getRight() - measuredWidth);
            this.h.setY(this.i.getTop() - measuredWidth);
        }
    }

    @Override // com.clevertap.android.sdk.inapp.CTInAppBaseFragment
    public void b() {
    }

    @Override // com.clevertap.android.sdk.inapp.CTInAppBaseFragment
    public void f() {
        Context context = this.j;
        if (context instanceof InAppNotificationActivity) {
            j((InAppListener) context);
        }
    }

    public void k(RelativeLayout relativeLayout, CloseImageView closeImageView) {
        new Handler().post(new a(this, closeImageView, relativeLayout));
    }

    public boolean l() {
        if (Utils.isActivityDead(getActivity())) {
            return false;
        }
        try {
            return getResources().getBoolean(R.bool.ctIsTablet);
        } catch (Exception e) {
            Logger.d("Failed to decide whether device is a smart phone or tablet!");
            e.printStackTrace();
            return false;
        }
    }

    public void m(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams, CloseImageView closeImageView) {
        layoutParams.height = (int) (relativeLayout.getMeasuredWidth() * 1.3f);
        relativeLayout.setLayoutParams(layoutParams);
        k(relativeLayout, closeImageView);
    }

    public void n(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams, CloseImageView closeImageView) {
        layoutParams.setMargins(h(140), h(140), h(140), h(140));
        int measuredWidth = relativeLayout.getMeasuredWidth() - h(Command.CMD_RECEIVE_SPEECH_CANCEL);
        layoutParams.width = measuredWidth;
        layoutParams.height = (int) (measuredWidth * 1.3f);
        relativeLayout.setLayoutParams(layoutParams);
        k(relativeLayout, closeImageView);
    }

    public void o(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams, CloseImageView closeImageView) {
        layoutParams.height = (int) (relativeLayout.getMeasuredWidth() * 1.78f);
        relativeLayout.setLayoutParams(layoutParams);
        k(relativeLayout, closeImageView);
    }

    public void p(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams, FrameLayout frameLayout, CloseImageView closeImageView) {
        int measuredWidth = (int) ((relativeLayout.getMeasuredWidth() - h(200)) * 1.78f);
        int measuredHeight = frameLayout.getMeasuredHeight() - h(DfuException.ERROR_ENTER_OTA_MODE_FAILED);
        if (measuredWidth > measuredHeight) {
            layoutParams.height = measuredHeight;
            layoutParams.width = (int) (measuredHeight / 1.78f);
        } else {
            layoutParams.height = measuredWidth;
            layoutParams.width = relativeLayout.getMeasuredWidth() - h(200);
        }
        layoutParams.setMargins(h(140), h(140), h(140), h(140));
        relativeLayout.setLayoutParams(layoutParams);
        k(relativeLayout, closeImageView);
    }

    public void q(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams, FrameLayout frameLayout, CloseImageView closeImageView) {
        int measuredWidth = (int) (relativeLayout.getMeasuredWidth() * 1.78f);
        int measuredHeight = frameLayout.getMeasuredHeight() - h(80);
        if (measuredWidth > measuredHeight) {
            layoutParams.height = measuredHeight;
            layoutParams.width = (int) (measuredHeight / 1.78f);
        } else {
            layoutParams.height = measuredWidth;
        }
        relativeLayout.setLayoutParams(layoutParams);
        k(relativeLayout, closeImageView);
    }

    public void r(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams, CloseImageView closeImageView) {
        layoutParams.width = (int) (relativeLayout.getMeasuredHeight() * 1.78f);
        layoutParams.gravity = 1;
        relativeLayout.setLayoutParams(layoutParams);
        k(relativeLayout, closeImageView);
    }

    public void s(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams, FrameLayout frameLayout, CloseImageView closeImageView) {
        int measuredHeight = (int) ((relativeLayout.getMeasuredHeight() - h(120)) * 1.78f);
        int measuredWidth = frameLayout.getMeasuredWidth() - h(DfuException.ERROR_ENTER_OTA_MODE_FAILED);
        if (measuredHeight > measuredWidth) {
            layoutParams.width = measuredWidth;
            layoutParams.height = (int) (measuredWidth / 1.78f);
        } else {
            layoutParams.width = measuredHeight;
            layoutParams.height = relativeLayout.getMeasuredHeight() - h(120);
        }
        layoutParams.setMargins(h(140), h(100), h(140), h(100));
        layoutParams.gravity = 17;
        relativeLayout.setLayoutParams(layoutParams);
        k(relativeLayout, closeImageView);
    }

    public void t(RelativeLayout relativeLayout, FrameLayout.LayoutParams layoutParams, FrameLayout frameLayout, CloseImageView closeImageView) {
        int measuredHeight = (int) (relativeLayout.getMeasuredHeight() * 1.78f);
        int measuredWidth = frameLayout.getMeasuredWidth() - h(80);
        if (measuredHeight > measuredWidth) {
            layoutParams.width = measuredWidth;
            layoutParams.height = (int) (measuredWidth / 1.78f);
        } else {
            layoutParams.width = measuredHeight;
        }
        layoutParams.gravity = 17;
        relativeLayout.setLayoutParams(layoutParams);
        k(relativeLayout, closeImageView);
    }
}
