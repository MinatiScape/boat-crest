package com.clevertap.android.sdk.inapp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
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
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentActivity;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.customviews.CloseImageView;
import com.clevertap.android.sdk.gif.GifImageView;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class CTInAppNativeInterstitialFragment extends CTInAppBaseFullNativeFragment {
    public static long A;
    public boolean p = false;
    public Dialog q;
    public ImageView r;
    public GifImageView s;
    public ExoPlayer t;
    public StyledPlayerView u;
    public RelativeLayout v;
    public FrameLayout w;
    public ViewGroup.LayoutParams x;
    public ViewGroup.LayoutParams y;
    public ViewGroup.LayoutParams z;

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
            RelativeLayout relativeLayout = (RelativeLayout) this.h.findViewById(R.id.interstitial_relative_layout);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) relativeLayout.getLayoutParams();
            if (CTInAppNativeInterstitialFragment.this.l.y() && CTInAppNativeInterstitialFragment.this.l()) {
                CTInAppNativeInterstitialFragment cTInAppNativeInterstitialFragment = CTInAppNativeInterstitialFragment.this;
                cTInAppNativeInterstitialFragment.q(cTInAppNativeInterstitialFragment.v, layoutParams, this.h, this.i);
            } else if (CTInAppNativeInterstitialFragment.this.l()) {
                CTInAppNativeInterstitialFragment cTInAppNativeInterstitialFragment2 = CTInAppNativeInterstitialFragment.this;
                cTInAppNativeInterstitialFragment2.p(cTInAppNativeInterstitialFragment2.v, layoutParams, this.h, this.i);
            } else {
                CTInAppNativeInterstitialFragment.this.o(relativeLayout, layoutParams, this.i);
            }
            CTInAppNativeInterstitialFragment.this.v.getViewTreeObserver().removeOnGlobalLayoutListener(this);
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
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) CTInAppNativeInterstitialFragment.this.v.getLayoutParams();
            if (CTInAppNativeInterstitialFragment.this.l.y() && CTInAppNativeInterstitialFragment.this.l()) {
                CTInAppNativeInterstitialFragment cTInAppNativeInterstitialFragment = CTInAppNativeInterstitialFragment.this;
                cTInAppNativeInterstitialFragment.t(cTInAppNativeInterstitialFragment.v, layoutParams, this.h, this.i);
            } else if (CTInAppNativeInterstitialFragment.this.l()) {
                CTInAppNativeInterstitialFragment cTInAppNativeInterstitialFragment2 = CTInAppNativeInterstitialFragment.this;
                cTInAppNativeInterstitialFragment2.s(cTInAppNativeInterstitialFragment2.v, layoutParams, this.h, this.i);
            } else {
                CTInAppNativeInterstitialFragment cTInAppNativeInterstitialFragment3 = CTInAppNativeInterstitialFragment.this;
                cTInAppNativeInterstitialFragment3.r(cTInAppNativeInterstitialFragment3.v, layoutParams, this.i);
            }
            CTInAppNativeInterstitialFragment.this.v.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    /* loaded from: classes2.dex */
    public class c extends Dialog {
        public c(Context context, int i) {
            super(context, i);
        }

        @Override // android.app.Dialog
        public void onBackPressed() {
            if (CTInAppNativeInterstitialFragment.this.p) {
                CTInAppNativeInterstitialFragment.this.B();
            }
            super.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E(View view) {
        didDismiss(null);
        GifImageView gifImageView = this.s;
        if (gifImageView != null) {
            gifImageView.clear();
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F(View view) {
        if (!this.p) {
            G();
        } else {
            B();
        }
    }

    public final void B() {
        ((ViewGroup) this.u.getParent()).removeView(this.u);
        this.u.setLayoutParams(this.y);
        FrameLayout frameLayout = this.w;
        int i = R.id.video_frame;
        ((FrameLayout) frameLayout.findViewById(i)).addView(this.u);
        this.r.setLayoutParams(this.z);
        ((FrameLayout) this.w.findViewById(i)).addView(this.r);
        this.w.setLayoutParams(this.x);
        ((RelativeLayout) this.v.findViewById(R.id.interstitial_relative_layout)).addView(this.w);
        this.p = false;
        this.q.dismiss();
        this.r.setImageDrawable(ContextCompat.getDrawable(this.j, R.drawable.ct_ic_fullscreen_expand));
    }

    public final void C() {
        this.r.setVisibility(8);
    }

    public final void D() {
        this.q = new c(this.j, 16973834);
    }

    public final void G() {
        this.z = this.r.getLayoutParams();
        this.y = this.u.getLayoutParams();
        this.x = this.w.getLayoutParams();
        ((ViewGroup) this.u.getParent()).removeView(this.u);
        ((ViewGroup) this.r.getParent()).removeView(this.r);
        ((ViewGroup) this.w.getParent()).removeView(this.w);
        this.q.addContentView(this.u, new ViewGroup.LayoutParams(-1, -1));
        this.p = true;
        this.q.show();
    }

    public final void H() {
        this.u.requestFocus();
        this.u.setVisibility(0);
        this.u.setPlayer(this.t);
        this.t.setPlayWhenReady(true);
    }

    public final void I() {
        FrameLayout frameLayout = (FrameLayout) this.v.findViewById(R.id.video_frame);
        this.w = frameLayout;
        frameLayout.setVisibility(0);
        this.u = new StyledPlayerView(this.j);
        ImageView imageView = new ImageView(this.j);
        this.r = imageView;
        imageView.setImageDrawable(ResourcesCompat.getDrawable(this.j.getResources(), R.drawable.ct_ic_fullscreen_expand, null));
        this.r.setOnClickListener(new View.OnClickListener() { // from class: com.clevertap.android.sdk.inapp.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CTInAppNativeInterstitialFragment.this.F(view);
            }
        });
        if (this.l.y() && l()) {
            this.u.setLayoutParams(new FrameLayout.LayoutParams((int) TypedValue.applyDimension(1, 408.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 229.0f, getResources().getDisplayMetrics())));
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) TypedValue.applyDimension(1, 30.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 30.0f, getResources().getDisplayMetrics()));
            layoutParams.gravity = GravityCompat.END;
            layoutParams.setMargins(0, (int) TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics()), 0);
            this.r.setLayoutParams(layoutParams);
        } else {
            this.u.setLayoutParams(new FrameLayout.LayoutParams((int) TypedValue.applyDimension(1, 240.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 134.0f, getResources().getDisplayMetrics())));
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams((int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics()));
            layoutParams2.gravity = GravityCompat.END;
            layoutParams2.setMargins(0, (int) TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics()), 0);
            this.r.setLayoutParams(layoutParams2);
        }
        this.u.setShowBuffering(1);
        this.u.setUseArtwork(true);
        this.u.setControllerAutoShow(false);
        this.w.addView(this.u);
        this.w.addView(this.r);
        this.u.setDefaultArtwork(ResourcesCompat.getDrawable(this.j.getResources(), R.drawable.ct_audio, null));
        DefaultBandwidthMeter build = new DefaultBandwidthMeter.Builder(this.j).build();
        this.t = new ExoPlayer.Builder(this.j).setTrackSelector(new DefaultTrackSelector(this.j, new AdaptiveTrackSelection.Factory())).build();
        Context context = this.j;
        String userAgent = Util.getUserAgent(context, context.getPackageName());
        String c2 = this.l.n().get(0).c();
        DefaultDataSource.Factory factory = new DefaultDataSource.Factory(context, new DefaultHttpDataSource.Factory().setUserAgent(userAgent).setTransferListener(build.getTransferListener()));
        this.t.setMediaSource(new HlsMediaSource.Factory(factory).createMediaSource(MediaItem.fromUri(c2)));
        this.t.prepare();
        this.t.setRepeatMode(1);
        this.t.seekTo(A);
    }

    @Override // com.clevertap.android.sdk.inapp.CTInAppBaseFullFragment, com.clevertap.android.sdk.inapp.CTInAppBaseFragment
    public void b() {
        super.b();
        GifImageView gifImageView = this.s;
        if (gifImageView != null) {
            gifImageView.clear();
        }
        ExoPlayer exoPlayer = this.t;
        if (exoPlayer != null) {
            exoPlayer.stop();
            this.t.release();
            this.t = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    @RequiresApi(api = 17)
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        ArrayList arrayList = new ArrayList();
        if (this.l.y() && l()) {
            inflate = layoutInflater.inflate(R.layout.tab_inapp_interstitial, viewGroup, false);
        } else {
            inflate = layoutInflater.inflate(R.layout.inapp_interstitial, viewGroup, false);
        }
        FrameLayout frameLayout = (FrameLayout) inflate.findViewById(R.id.inapp_interstitial_frame_layout);
        CloseImageView closeImageView = (CloseImageView) frameLayout.findViewById(199272);
        RelativeLayout relativeLayout = (RelativeLayout) frameLayout.findViewById(R.id.interstitial_relative_layout);
        this.v = relativeLayout;
        relativeLayout.setBackgroundColor(Color.parseColor(this.l.c()));
        int i = this.k;
        if (i == 1) {
            this.v.getViewTreeObserver().addOnGlobalLayoutListener(new a(frameLayout, closeImageView));
        } else if (i == 2) {
            this.v.getViewTreeObserver().addOnGlobalLayoutListener(new b(frameLayout, closeImageView));
        }
        if (!this.l.n().isEmpty()) {
            if (this.l.n().get(0).g()) {
                CTInAppNotification cTInAppNotification = this.l;
                if (cTInAppNotification.l(cTInAppNotification.n().get(0)) != null) {
                    ImageView imageView = (ImageView) this.v.findViewById(R.id.backgroundImage);
                    imageView.setVisibility(0);
                    CTInAppNotification cTInAppNotification2 = this.l;
                    imageView.setImageBitmap(cTInAppNotification2.l(cTInAppNotification2.n().get(0)));
                }
            } else if (this.l.n().get(0).f()) {
                CTInAppNotification cTInAppNotification3 = this.l;
                if (cTInAppNotification3.i(cTInAppNotification3.n().get(0)) != null) {
                    GifImageView gifImageView = (GifImageView) this.v.findViewById(R.id.gifImage);
                    this.s = gifImageView;
                    gifImageView.setVisibility(0);
                    GifImageView gifImageView2 = this.s;
                    CTInAppNotification cTInAppNotification4 = this.l;
                    gifImageView2.setBytes(cTInAppNotification4.i(cTInAppNotification4.n().get(0)));
                    this.s.startAnimation();
                }
            } else if (this.l.n().get(0).h()) {
                D();
                I();
                H();
            } else if (this.l.n().get(0).e()) {
                I();
                H();
                C();
            }
        }
        LinearLayout linearLayout = (LinearLayout) this.v.findViewById(R.id.interstitial_linear_layout);
        Button button = (Button) linearLayout.findViewById(R.id.interstitial_button1);
        arrayList.add(button);
        Button button2 = (Button) linearLayout.findViewById(R.id.interstitial_button2);
        arrayList.add(button2);
        TextView textView = (TextView) this.v.findViewById(R.id.interstitial_title);
        textView.setText(this.l.getTitle());
        textView.setTextColor(Color.parseColor(this.l.q()));
        TextView textView2 = (TextView) this.v.findViewById(R.id.interstitial_message);
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
        closeImageView.setOnClickListener(new View.OnClickListener() { // from class: com.clevertap.android.sdk.inapp.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CTInAppNativeInterstitialFragment.this.E(view);
            }
        });
        if (!this.l.u()) {
            closeImageView.setVisibility(8);
        } else {
            closeImageView.setVisibility(0);
        }
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        GifImageView gifImageView = this.s;
        if (gifImageView != null) {
            gifImageView.clear();
        }
        if (this.p) {
            B();
        }
        ExoPlayer exoPlayer = this.t;
        if (exoPlayer != null) {
            A = exoPlayer.getCurrentPosition();
            this.t.stop();
            this.t.release();
            this.t = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.l.n().isEmpty() || this.t != null) {
            return;
        }
        if (this.l.n().get(0).h() || this.l.n().get(0).e()) {
            I();
            H();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        GifImageView gifImageView = this.s;
        if (gifImageView != null) {
            CTInAppNotification cTInAppNotification = this.l;
            gifImageView.setBytes(cTInAppNotification.i(cTInAppNotification.n().get(0)));
            this.s.startAnimation();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        GifImageView gifImageView = this.s;
        if (gifImageView != null) {
            gifImageView.clear();
        }
        ExoPlayer exoPlayer = this.t;
        if (exoPlayer != null) {
            exoPlayer.stop();
            this.t.release();
        }
    }
}
