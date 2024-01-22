package android.support.v4.media.session;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.media.session.a;
import androidx.annotation.RequiresApi;
@RequiresApi(23)
/* loaded from: classes.dex */
public class MediaSessionCompatApi23 {

    /* loaded from: classes.dex */
    public interface Callback extends a.InterfaceC0104a {
        @Override // android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver);

        @Override // android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onCustomAction(String str, Bundle bundle);

        @Override // android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onFastForward();

        @Override // android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ boolean onMediaButtonEvent(Intent intent);

        @Override // android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onPause();

        @Override // android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onPlay();

        @Override // android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onPlayFromMediaId(String str, Bundle bundle);

        @Override // android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onPlayFromSearch(String str, Bundle bundle);

        void onPlayFromUri(Uri uri, Bundle bundle);

        @Override // android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onRewind();

        @Override // android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onSeekTo(long j);

        @Override // android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onSetRating(Object obj);

        /* synthetic */ void onSetRating(Object obj, Bundle bundle);

        @Override // android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onSkipToNext();

        @Override // android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onSkipToPrevious();

        @Override // android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onSkipToQueueItem(long j);

        @Override // android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onStop();
    }

    /* loaded from: classes.dex */
    public static class a<T extends Callback> extends a.b<T> {
        public a(T t) {
            super(t);
        }

        @Override // android.media.session.MediaSession.Callback
        public void onPlayFromUri(Uri uri, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            ((Callback) this.f339a).onPlayFromUri(uri, bundle);
        }
    }

    public static Object a(Callback callback) {
        return new a(callback);
    }
}
