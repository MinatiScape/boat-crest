package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.android.volley.toolbox.JsonRequest;
import com.pierfrancescosoffritti.androidyoutubeplayer.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayerBridge;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import java.io.InputStream;
import java.util.Collection;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class WebViewYouTubePlayer extends WebView implements YouTubePlayerBridge.YouTubePlayerBridgeCallbacks {
    @NotNull
    public final FullscreenListener h;
    @NotNull
    public final b i;
    public Function1<? super YouTubePlayer, Unit> j;
    public boolean k;

    public /* synthetic */ WebViewYouTubePlayer(Context context, FullscreenListener fullscreenListener, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, fullscreenListener, (i2 & 4) != 0 ? null : attributeSet, (i2 & 8) != 0 ? 0 : i);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public final void a(IFramePlayerOptions iFramePlayerOptions) {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setCacheMode(-1);
        addJavascriptInterface(new YouTubePlayerBridge(this), "YouTubePlayerBridge");
        InputStream openRawResource = getResources().openRawResource(R.raw.ayp_youtube_player);
        Intrinsics.checkNotNullExpressionValue(openRawResource, "resources.openRawResourc…R.raw.ayp_youtube_player)");
        loadDataWithBaseURL(iFramePlayerOptions.getOrigin$core_release(), m.replace$default(WebViewYouTubePlayerKt.readHTMLFromUTF8File(openRawResource), "<<injectedPlayerVars>>", iFramePlayerOptions.toString(), false, 4, (Object) null), "text/html", JsonRequest.PROTOCOL_CHARSET, null);
        setWebChromeClient(new WebChromeClient() { // from class: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.WebViewYouTubePlayer$initWebView$2

            /* loaded from: classes9.dex */
            public static final class a extends Lambda implements Function0<Unit> {
                public final /* synthetic */ WebChromeClient.CustomViewCallback $callback;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(WebChromeClient.CustomViewCallback customViewCallback) {
                    super(0);
                    this.$callback = customViewCallback;
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.$callback.onCustomViewHidden();
                }
            }

            @Override // android.webkit.WebChromeClient
            @Nullable
            public Bitmap getDefaultVideoPoster() {
                Bitmap defaultVideoPoster = super.getDefaultVideoPoster();
                return defaultVideoPoster == null ? Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565) : defaultVideoPoster;
            }

            @Override // android.webkit.WebChromeClient
            public void onHideCustomView() {
                FullscreenListener fullscreenListener;
                super.onHideCustomView();
                fullscreenListener = WebViewYouTubePlayer.this.h;
                fullscreenListener.onExitFullscreen();
            }

            @Override // android.webkit.WebChromeClient
            public void onShowCustomView(@NotNull View view, @NotNull WebChromeClient.CustomViewCallback callback) {
                FullscreenListener fullscreenListener;
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(callback, "callback");
                super.onShowCustomView(view, callback);
                fullscreenListener = WebViewYouTubePlayer.this.h;
                fullscreenListener.onEnterFullscreen(view, new a(callback));
            }
        });
    }

    public final boolean addListener(@NotNull YouTubePlayerListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.i.b().add(listener);
    }

    @Override // android.webkit.WebView
    public void destroy() {
        this.i.e();
        super.destroy();
    }

    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayerBridge.YouTubePlayerBridgeCallbacks
    @NotNull
    public YouTubePlayer getInstance() {
        return this.i;
    }

    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayerBridge.YouTubePlayerBridgeCallbacks
    @NotNull
    public Collection<YouTubePlayerListener> getListeners() {
        return CollectionsKt___CollectionsKt.toSet(this.i.b());
    }

    @NotNull
    public final YouTubePlayer getYoutubePlayer$core_release() {
        return this.i;
    }

    public final void initialize$core_release(@NotNull Function1<? super YouTubePlayer, Unit> initListener, @Nullable IFramePlayerOptions iFramePlayerOptions) {
        Intrinsics.checkNotNullParameter(initListener, "initListener");
        this.j = initListener;
        if (iFramePlayerOptions == null) {
            iFramePlayerOptions = IFramePlayerOptions.Companion.getDefault();
        }
        a(iFramePlayerOptions);
    }

    public final boolean isBackgroundPlaybackEnabled$core_release() {
        return this.k;
    }

    @Override // android.webkit.WebView, android.view.View
    public void onWindowVisibilityChanged(int i) {
        if (this.k && (i == 8 || i == 4)) {
            return;
        }
        super.onWindowVisibilityChanged(i);
    }

    @Override // com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayerBridge.YouTubePlayerBridgeCallbacks
    public void onYouTubeIFrameAPIReady() {
        Function1<? super YouTubePlayer, Unit> function1 = this.j;
        if (function1 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("youTubePlayerInitListener");
            function1 = null;
        }
        function1.invoke(this.i);
    }

    public final boolean removeListener(@NotNull YouTubePlayerListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.i.b().remove(listener);
    }

    public final void setBackgroundPlaybackEnabled$core_release(boolean z) {
        this.k = z;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebViewYouTubePlayer(@NotNull Context context, @NotNull FullscreenListener listener, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.h = listener;
        this.i = new b(this);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WebViewYouTubePlayer(@NotNull Context context) {
        this(context, FakeWebViewYouTubeListener.INSTANCE, null, 0, 12, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }
}
