package com.clevertap.android.sdk.inbox;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Util;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class CTInboxBaseMessageViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a  reason: collision with root package name */
    public Context f2631a;
    public LinearLayout b;
    public LinearLayout c;
    public FrameLayout d;
    public ImageView e;
    public ImageView f;
    public RelativeLayout g;
    public FrameLayout h;
    public RelativeLayout i;
    public CTInboxMessageContent j;
    public CTInboxMessage k;
    public ImageView l;
    public WeakReference<CTInboxListViewFragment> m;
    public boolean n;
    public final ImageView readDot;

    /* loaded from: classes2.dex */
    public class a implements Runnable {
        public final /* synthetic */ int h;
        public final /* synthetic */ CTInboxMessage i;

        /* renamed from: com.clevertap.android.sdk.inbox.CTInboxBaseMessageViewHolder$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public class RunnableC0229a implements Runnable {
            public final /* synthetic */ CTInboxListViewFragment h;

            public RunnableC0229a(CTInboxListViewFragment cTInboxListViewFragment) {
                this.h = cTInboxListViewFragment;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (CTInboxBaseMessageViewHolder.this.readDot.getVisibility() == 0) {
                    this.h.b(null, a.this.h);
                }
                CTInboxBaseMessageViewHolder.this.readDot.setVisibility(8);
                a.this.i.a(true);
            }
        }

        public a(int i, CTInboxMessage cTInboxMessage) {
            this.h = i;
            this.i = cTInboxMessage;
        }

        @Override // java.lang.Runnable
        public void run() {
            FragmentActivity activity;
            CTInboxListViewFragment f = CTInboxBaseMessageViewHolder.this.f();
            if (f == null || (activity = f.getActivity()) == null) {
                return;
            }
            activity.runOnUiThread(new RunnableC0229a(f));
        }
    }

    public CTInboxBaseMessageViewHolder(@NonNull View view) {
        super(view);
        this.readDot = (ImageView) view.findViewById(R.id.read_circle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(ExoPlayer exoPlayer, View view) {
        int i = ((exoPlayer != null ? exoPlayer.getVolume() : 0.0f) > 0.0f ? 1 : ((exoPlayer != null ? exoPlayer.getVolume() : 0.0f) == 0.0f ? 0 : -1));
        if (i > 0) {
            exoPlayer.setVolume(0.0f);
            this.l.setImageDrawable(ResourcesCompat.getDrawable(this.f2631a.getResources(), R.drawable.ct_volume_off, null));
        } else if (i == 0) {
            if (exoPlayer != null) {
                exoPlayer.setVolume(1.0f);
            }
            this.l.setImageDrawable(ResourcesCompat.getDrawable(this.f2631a.getResources(), R.drawable.ct_volume_on, null));
        }
    }

    public boolean addMediaPlayer(StyledPlayerView styledPlayerView) {
        FrameLayout e;
        int i;
        int round;
        if (this.n && (e = e()) != null) {
            e.removeAllViews();
            e.setVisibility(8);
            Resources resources = this.f2631a.getResources();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            if (CTInboxActivity.orientation == 2) {
                if (this.k.getOrientation().equalsIgnoreCase("l")) {
                    i = Math.round(this.e.getMeasuredHeight() * 1.76f);
                    round = this.e.getMeasuredHeight();
                } else {
                    i = this.f.getMeasuredHeight();
                    round = i;
                }
            } else {
                i = resources.getDisplayMetrics().widthPixels;
                if (this.k.getOrientation().equalsIgnoreCase("l")) {
                    round = Math.round(i * 0.5625f);
                }
                round = i;
            }
            styledPlayerView.setLayoutParams(new FrameLayout.LayoutParams(i, round));
            e.addView(styledPlayerView);
            e.setBackgroundColor(Color.parseColor(this.k.getBgColor()));
            FrameLayout frameLayout = this.h;
            if (frameLayout != null) {
                frameLayout.setVisibility(0);
            }
            final ExoPlayer player = styledPlayerView.getPlayer();
            float volume = player != null ? player.getVolume() : 0.0f;
            if (this.j.mediaIsVideo()) {
                ImageView imageView = new ImageView(this.f2631a);
                this.l = imageView;
                imageView.setVisibility(8);
                if (volume > 0.0f) {
                    this.l.setImageDrawable(ResourcesCompat.getDrawable(this.f2631a.getResources(), R.drawable.ct_volume_on, null));
                } else {
                    this.l.setImageDrawable(ResourcesCompat.getDrawable(this.f2631a.getResources(), R.drawable.ct_volume_off, null));
                }
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) TypedValue.applyDimension(1, 30.0f, displayMetrics), (int) TypedValue.applyDimension(1, 30.0f, displayMetrics));
                layoutParams.setMargins(0, (int) TypedValue.applyDimension(1, 4.0f, displayMetrics), (int) TypedValue.applyDimension(1, 2.0f, displayMetrics), 0);
                layoutParams.gravity = GravityCompat.END;
                this.l.setLayoutParams(layoutParams);
                this.l.setOnClickListener(new View.OnClickListener() { // from class: com.clevertap.android.sdk.inbox.d
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        CTInboxBaseMessageViewHolder.this.i(player, view);
                    }
                });
                e.addView(this.l);
            }
            styledPlayerView.requestFocus();
            styledPlayerView.setShowBuffering(0);
            DefaultBandwidthMeter build = new DefaultBandwidthMeter.Builder(this.f2631a).build();
            Context context = this.f2631a;
            String userAgent = Util.getUserAgent(context, context.getPackageName());
            String media = this.j.getMedia();
            MediaItem fromUri = MediaItem.fromUri(media);
            DefaultDataSource.Factory factory = new DefaultDataSource.Factory(context, new DefaultHttpDataSource.Factory().setUserAgent(userAgent).setTransferListener(build));
            if (media != null) {
                HlsMediaSource createMediaSource = new HlsMediaSource.Factory(factory).createMediaSource(fromUri);
                if (player != null) {
                    player.setMediaSource(createMediaSource);
                    player.prepare();
                    if (this.j.mediaIsAudio()) {
                        styledPlayerView.showController();
                        player.setPlayWhenReady(false);
                        player.setVolume(1.0f);
                    } else if (this.j.mediaIsVideo()) {
                        player.setPlayWhenReady(true);
                        player.setVolume(volume);
                    }
                }
            }
            return true;
        }
        return false;
    }

    public String b(long j) {
        StringBuilder sb;
        String str;
        long currentTimeMillis = (System.currentTimeMillis() / 1000) - j;
        int i = (currentTimeMillis > 60L ? 1 : (currentTimeMillis == 60L ? 0 : -1));
        if (i < 0) {
            return "Just Now";
        }
        if (i > 0 && currentTimeMillis < 3540) {
            return (currentTimeMillis / 60) + " mins ago";
        } else if (currentTimeMillis <= 3540 || currentTimeMillis >= 81420) {
            return (currentTimeMillis <= 86400 || currentTimeMillis >= 172800) ? new SimpleDateFormat("dd MMM").format(new Date(j * 1000)) : "Yesterday";
        } else {
            long j2 = currentTimeMillis / 3600;
            if (j2 > 1) {
                sb = new StringBuilder();
                sb.append(j2);
                str = " hours ago";
            } else {
                sb = new StringBuilder();
                sb.append(j2);
                str = " hour ago";
            }
            sb.append(str);
            return sb.toString();
        }
    }

    public void c(CTInboxMessage cTInboxMessage, CTInboxListViewFragment cTInboxListViewFragment, int i) {
        this.f2631a = cTInboxListViewFragment.getContext();
        this.m = new WeakReference<>(cTInboxListViewFragment);
        this.k = cTInboxMessage;
        boolean z = false;
        CTInboxMessageContent cTInboxMessageContent = cTInboxMessage.getInboxMessageContents().get(0);
        this.j = cTInboxMessageContent;
        this.n = (cTInboxMessageContent.mediaIsAudio() || this.j.mediaIsVideo()) ? true : true;
    }

    public int d() {
        return 0;
    }

    public final FrameLayout e() {
        return this.d;
    }

    public CTInboxListViewFragment f() {
        return this.m.get();
    }

    public void g(Button button, Button button2, Button button3) {
        button3.setVisibility(8);
        button.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 3.0f));
        button2.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 3.0f));
        button3.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 0.0f));
    }

    public void h(Button button, Button button2, Button button3) {
        button2.setVisibility(8);
        button3.setVisibility(8);
        button.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 6.0f));
        button2.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 0.0f));
        button3.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 0.0f));
    }

    public void j(ImageView[] imageViewArr, int i, Context context, LinearLayout linearLayout) {
        for (int i2 = 0; i2 < i; i2++) {
            imageViewArr[i2] = new ImageView(context);
            imageViewArr[i2].setVisibility(0);
            imageViewArr[i2].setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ct_unselected_dot, null));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(8, 6, 4, 6);
            layoutParams.gravity = 17;
            if (linearLayout.getChildCount() < i) {
                linearLayout.addView(imageViewArr[i2], layoutParams);
            }
        }
    }

    public void markItemAsRead(CTInboxMessage cTInboxMessage, int i) {
        new Handler().postDelayed(new a(i, cTInboxMessage), Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public boolean needsMediaPlayer() {
        return this.n;
    }

    public void playerBuffering() {
        FrameLayout frameLayout = this.h;
        if (frameLayout != null) {
            frameLayout.setVisibility(0);
        }
    }

    public void playerReady() {
        e().setVisibility(0);
        ImageView imageView = this.l;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        FrameLayout frameLayout = this.h;
        if (frameLayout != null) {
            frameLayout.setVisibility(8);
        }
    }

    public void playerRemoved() {
        FrameLayout frameLayout = this.h;
        if (frameLayout != null) {
            frameLayout.setVisibility(8);
        }
        ImageView imageView = this.l;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        FrameLayout e = e();
        if (e != null) {
            e.removeAllViews();
        }
    }

    public boolean shouldAutoPlay() {
        return this.j.mediaIsVideo();
    }
}
