package android.support.v4.media.session;

import android.content.Intent;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.media.session.MediaSessionCompatApi23;
import android.util.Log;
import androidx.annotation.RequiresApi;
import java.lang.reflect.InvocationTargetException;
@RequiresApi(24)
/* loaded from: classes.dex */
public class MediaSessionCompatApi24 {

    /* loaded from: classes.dex */
    public interface Callback extends MediaSessionCompatApi23.Callback {
        @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback, android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver);

        @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback, android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onCustomAction(String str, Bundle bundle);

        @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback, android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onFastForward();

        @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback, android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ boolean onMediaButtonEvent(Intent intent);

        @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback, android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onPause();

        @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback, android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onPlay();

        @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback, android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onPlayFromMediaId(String str, Bundle bundle);

        @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback, android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onPlayFromSearch(String str, Bundle bundle);

        void onPrepare();

        void onPrepareFromMediaId(String str, Bundle bundle);

        void onPrepareFromSearch(String str, Bundle bundle);

        void onPrepareFromUri(Uri uri, Bundle bundle);

        @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback, android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onRewind();

        @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback, android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onSeekTo(long j);

        @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback, android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onSetRating(Object obj);

        @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback
        /* synthetic */ void onSetRating(Object obj, Bundle bundle);

        @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback, android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onSkipToNext();

        @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback, android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onSkipToPrevious();

        @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback, android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onSkipToQueueItem(long j);

        @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback, android.support.v4.media.session.a.InterfaceC0104a
        /* synthetic */ void onStop();
    }

    /* loaded from: classes.dex */
    public static class a<T extends Callback> extends MediaSessionCompatApi23.a<T> {
        public a(T t) {
            super(t);
        }

        @Override // android.media.session.MediaSession.Callback
        public void onPrepare() {
            ((Callback) this.f339a).onPrepare();
        }

        @Override // android.media.session.MediaSession.Callback
        public void onPrepareFromMediaId(String str, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            ((Callback) this.f339a).onPrepareFromMediaId(str, bundle);
        }

        @Override // android.media.session.MediaSession.Callback
        public void onPrepareFromSearch(String str, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            ((Callback) this.f339a).onPrepareFromSearch(str, bundle);
        }

        @Override // android.media.session.MediaSession.Callback
        public void onPrepareFromUri(Uri uri, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            ((Callback) this.f339a).onPrepareFromUri(uri, bundle);
        }
    }

    public static Object a(Callback callback) {
        return new a(callback);
    }

    public static String b(Object obj) {
        MediaSession mediaSession = (MediaSession) obj;
        try {
            return (String) mediaSession.getClass().getMethod("getCallingPackage", new Class[0]).invoke(mediaSession, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("MediaSessionCompatApi24", "Cannot execute MediaSession.getCallingPackage()", e);
            return null;
        }
    }
}
