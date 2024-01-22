package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import com.pierfrancescosoffritti.androidyoutubeplayer.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class YouTubePlayerView extends SixteenByNineFrameLayout implements LifecycleEventObserver {
    @NotNull
    public final List<FullscreenListener> h;
    @NotNull
    public final YouTubePlayerView$webViewFullscreenListener$1 i;
    @NotNull
    public final LegacyYouTubePlayerView j;
    public boolean k;

    /* loaded from: classes9.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            try {
                iArr[Lifecycle.Event.ON_RESUME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Lifecycle.Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Lifecycle.Event.ON_DESTROY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Lifecycle.Event.ON_START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Lifecycle.Event.ON_PAUSE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ YouTubePlayerView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final void a() {
        this.j.onResume$core_release();
    }

    public final boolean addFullscreenListener(@NotNull FullscreenListener fullscreenListener) {
        Intrinsics.checkNotNullParameter(fullscreenListener, "fullscreenListener");
        return this.h.add(fullscreenListener);
    }

    public final boolean addYouTubePlayerListener(@NotNull YouTubePlayerListener youTubePlayerListener) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        return this.j.getWebViewYouTubePlayer$core_release().addListener(youTubePlayerListener);
    }

    public final void b() {
        this.j.onStop$core_release();
    }

    public final void c(int i, int i2) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
        setLayoutParams(layoutParams);
    }

    public final void enableBackgroundPlayback(boolean z) {
        this.j.enableBackgroundPlayback(z);
    }

    public final boolean getEnableAutomaticInitialization() {
        return this.k;
    }

    public final void getYouTubePlayerWhenReady(@NotNull YouTubePlayerCallback youTubePlayerCallback) {
        Intrinsics.checkNotNullParameter(youTubePlayerCallback, "youTubePlayerCallback");
        this.j.getYouTubePlayerWhenReady(youTubePlayerCallback);
    }

    @NotNull
    public final View inflateCustomPlayerUi(@LayoutRes int i) {
        return this.j.inflateCustomPlayerUi(i);
    }

    public final void initialize(@NotNull YouTubePlayerListener youTubePlayerListener, boolean z, @NotNull IFramePlayerOptions playerOptions) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        Intrinsics.checkNotNullParameter(playerOptions, "playerOptions");
        if (!this.k) {
            this.j.initialize(youTubePlayerListener, z, playerOptions);
            return;
        }
        throw new IllegalStateException("YouTubePlayerView: If you want to initialize this view manually, you need to set 'enableAutomaticInitialization' to false.");
    }

    public final void matchParent() {
        c(-1, -1);
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(@NotNull LifecycleOwner source, @NotNull Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(event, "event");
        int i = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
        if (i == 1) {
            a();
        } else if (i == 2) {
            b();
        } else if (i != 3) {
        } else {
            release();
        }
    }

    public final void release() {
        this.j.release();
    }

    public final boolean removeFullscreenListener(@NotNull FullscreenListener fullscreenListener) {
        Intrinsics.checkNotNullParameter(fullscreenListener, "fullscreenListener");
        return this.h.remove(fullscreenListener);
    }

    public final boolean removeYouTubePlayerListener(@NotNull YouTubePlayerListener youTubePlayerListener) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        return this.j.getWebViewYouTubePlayer$core_release().removeListener(youTubePlayerListener);
    }

    public final void setCustomPlayerUi(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.j.setCustomPlayerUi(view);
    }

    public final void setEnableAutomaticInitialization(boolean z) {
        this.k = z;
    }

    public final void wrapContent() {
        c(-1, -2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v0, types: [com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView$webViewFullscreenListener$1, com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener] */
    public YouTubePlayerView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ViewGroup.LayoutParams a2;
        Intrinsics.checkNotNullParameter(context, "context");
        this.h = new ArrayList();
        ?? r2 = new FullscreenListener() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView$webViewFullscreenListener$1
            @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
            public void onEnterFullscreen(@NotNull View fullscreenView, @NotNull Function0<Unit> exitFullscreen) {
                List list;
                List<FullscreenListener> list2;
                Intrinsics.checkNotNullParameter(fullscreenView, "fullscreenView");
                Intrinsics.checkNotNullParameter(exitFullscreen, "exitFullscreen");
                list = YouTubePlayerView.this.h;
                if (!list.isEmpty()) {
                    list2 = YouTubePlayerView.this.h;
                    for (FullscreenListener fullscreenListener : list2) {
                        fullscreenListener.onEnterFullscreen(fullscreenView, exitFullscreen);
                    }
                    return;
                }
                throw new IllegalStateException("To enter fullscreen you need to first register a FullscreenListener.");
            }

            @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
            public void onExitFullscreen() {
                List list;
                List<FullscreenListener> list2;
                list = YouTubePlayerView.this.h;
                if (!list.isEmpty()) {
                    list2 = YouTubePlayerView.this.h;
                    for (FullscreenListener fullscreenListener : list2) {
                        fullscreenListener.onExitFullscreen();
                    }
                    return;
                }
                throw new IllegalStateException("To enter fullscreen you need to first register a FullscreenListener.");
            }
        };
        this.i = r2;
        LegacyYouTubePlayerView legacyYouTubePlayerView = new LegacyYouTubePlayerView(context, r2, null, 0, 12, null);
        this.j = legacyYouTubePlayerView;
        a2 = YouTubePlayerViewKt.a();
        addView(legacyYouTubePlayerView, a2);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.YouTubePlayerView, 0, 0);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.theme.obtainStyl….YouTubePlayerView, 0, 0)");
        this.k = obtainStyledAttributes.getBoolean(R.styleable.YouTubePlayerView_enableAutomaticInitialization, true);
        final boolean z = obtainStyledAttributes.getBoolean(R.styleable.YouTubePlayerView_autoPlay, false);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.YouTubePlayerView_handleNetworkEvents, true);
        final String string = obtainStyledAttributes.getString(R.styleable.YouTubePlayerView_videoId);
        obtainStyledAttributes.recycle();
        if (z && string == null) {
            throw new IllegalStateException("YouTubePlayerView: videoId is not set but autoPlay is set to true. This combination is not allowed.");
        }
        AbstractYouTubePlayerListener abstractYouTubePlayerListener = new AbstractYouTubePlayerListener() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView$youTubePlayerListener$1
            @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener, com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
            public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                LegacyYouTubePlayerView legacyYouTubePlayerView2;
                Intrinsics.checkNotNullParameter(youTubePlayer, "youTubePlayer");
                String str = string;
                if (str != null) {
                    YouTubePlayerView youTubePlayerView = this;
                    boolean z3 = z;
                    legacyYouTubePlayerView2 = youTubePlayerView.j;
                    YouTubePlayerUtils.loadOrCueVideo(youTubePlayer, legacyYouTubePlayerView2.getCanPlay$core_release() && z3, str, 0.0f);
                }
                youTubePlayer.removeListener(this);
            }
        };
        if (this.k) {
            legacyYouTubePlayerView.initialize(abstractYouTubePlayerListener, z2, IFramePlayerOptions.Companion.getDefault());
        }
    }

    public final void initialize(@NotNull YouTubePlayerListener youTubePlayerListener, boolean z) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        if (!this.k) {
            this.j.initialize(youTubePlayerListener, z, IFramePlayerOptions.Companion.getDefault());
            return;
        }
        throw new IllegalStateException("YouTubePlayerView: If you want to initialize this view manually, you need to set 'enableAutomaticInitialization' to false.");
    }

    public final void initialize(@NotNull YouTubePlayerListener youTubePlayerListener, @NotNull IFramePlayerOptions playerOptions) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        Intrinsics.checkNotNullParameter(playerOptions, "playerOptions");
        if (!this.k) {
            this.j.initialize(youTubePlayerListener, true, playerOptions);
            return;
        }
        throw new IllegalStateException("YouTubePlayerView: If you want to initialize this view manually, you need to set 'enableAutomaticInitialization' to false.");
    }

    public final void initialize(@NotNull YouTubePlayerListener youTubePlayerListener) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        if (!this.k) {
            this.j.initialize(youTubePlayerListener, true);
            return;
        }
        throw new IllegalStateException("YouTubePlayerView: If you want to initialize this view manually, you need to set 'enableAutomaticInitialization' to false.");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public YouTubePlayerView(@NotNull Context context) {
        this(context, null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public YouTubePlayerView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ YouTubePlayerView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }
}
