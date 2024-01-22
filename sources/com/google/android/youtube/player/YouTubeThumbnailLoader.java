package com.google.android.youtube.player;
/* loaded from: classes10.dex */
public interface YouTubeThumbnailLoader {

    /* loaded from: classes10.dex */
    public enum ErrorReason {
        NETWORK_ERROR,
        INTERNAL_ERROR,
        UNKNOWN
    }

    /* loaded from: classes10.dex */
    public interface OnThumbnailLoadedListener {
        void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, ErrorReason errorReason);

        void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String str);
    }

    void first();

    boolean hasNext();

    boolean hasPrevious();

    void next();

    void previous();

    void release();

    void setOnThumbnailLoadedListener(OnThumbnailLoadedListener onThumbnailLoadedListener);

    void setPlaylist(String str);

    void setPlaylist(String str, int i);

    void setVideo(String str);
}
