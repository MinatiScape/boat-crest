package com.google.android.youtube.player;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.google.android.youtube.player.internal.aa;
import com.google.android.youtube.player.internal.ab;
import com.google.android.youtube.player.internal.b;
import com.google.android.youtube.player.internal.t;
/* loaded from: classes10.dex */
public final class YouTubeThumbnailView extends ImageView {
    public b h;
    public com.google.android.youtube.player.internal.a i;

    /* loaded from: classes10.dex */
    public interface OnInitializedListener {
        void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult);

        void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader);
    }

    /* loaded from: classes10.dex */
    public static final class a implements t.a, t.b {

        /* renamed from: a  reason: collision with root package name */
        public YouTubeThumbnailView f10488a;
        public OnInitializedListener b;

        public a(YouTubeThumbnailView youTubeThumbnailView, OnInitializedListener onInitializedListener) {
            this.f10488a = (YouTubeThumbnailView) ab.a(youTubeThumbnailView, "thumbnailView cannot be null");
            this.b = (OnInitializedListener) ab.a(onInitializedListener, "onInitializedlistener cannot be null");
        }

        @Override // com.google.android.youtube.player.internal.t.a
        public final void a() {
            YouTubeThumbnailView youTubeThumbnailView = this.f10488a;
            if (youTubeThumbnailView == null || youTubeThumbnailView.h == null) {
                return;
            }
            this.f10488a.i = aa.a().a(this.f10488a.h, this.f10488a);
            OnInitializedListener onInitializedListener = this.b;
            YouTubeThumbnailView youTubeThumbnailView2 = this.f10488a;
            onInitializedListener.onInitializationSuccess(youTubeThumbnailView2, youTubeThumbnailView2.i);
            c();
        }

        @Override // com.google.android.youtube.player.internal.t.b
        public final void a(YouTubeInitializationResult youTubeInitializationResult) {
            this.b.onInitializationFailure(this.f10488a, youTubeInitializationResult);
            c();
        }

        @Override // com.google.android.youtube.player.internal.t.a
        public final void b() {
            c();
        }

        public final void c() {
            YouTubeThumbnailView youTubeThumbnailView = this.f10488a;
            if (youTubeThumbnailView != null) {
                YouTubeThumbnailView.d(youTubeThumbnailView);
                this.f10488a = null;
                this.b = null;
            }
        }
    }

    public YouTubeThumbnailView(Context context) {
        this(context, null);
    }

    public YouTubeThumbnailView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public YouTubeThumbnailView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public static /* synthetic */ b d(YouTubeThumbnailView youTubeThumbnailView) {
        youTubeThumbnailView.h = null;
        return null;
    }

    public final void finalize() throws Throwable {
        com.google.android.youtube.player.internal.a aVar = this.i;
        if (aVar != null) {
            aVar.b();
            this.i = null;
        }
        super.finalize();
    }

    public final void initialize(String str, OnInitializedListener onInitializedListener) {
        a aVar = new a(this, onInitializedListener);
        b a2 = aa.a().a(getContext(), str, aVar, aVar);
        this.h = a2;
        a2.e();
    }
}
