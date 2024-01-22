package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.Rating;
import android.media.RemoteControlClient;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompatApi23;
import android.support.v4.media.session.MediaSessionCompatApi24;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.media.session.a;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.app.BundleCompat;
import androidx.media.MediaBrowserProtocol;
import androidx.media.MediaSessionManager;
import androidx.media.VolumeProviderCompat;
import androidx.media.session.MediaButtonReceiver;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public class MediaSessionCompat {
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_CAPTIONING_ENABLED = "android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_EXTRAS = "android.support.v4.media.session.action.ARGUMENT_EXTRAS";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_MEDIA_ID = "android.support.v4.media.session.action.ARGUMENT_MEDIA_ID";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_QUERY = "android.support.v4.media.session.action.ARGUMENT_QUERY";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_RATING = "android.support.v4.media.session.action.ARGUMENT_RATING";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_REPEAT_MODE = "android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_SHUFFLE_MODE = "android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_URI = "android.support.v4.media.session.action.ARGUMENT_URI";
    public static final String ACTION_FLAG_AS_INAPPROPRIATE = "android.support.v4.media.session.action.FLAG_AS_INAPPROPRIATE";
    public static final String ACTION_FOLLOW = "android.support.v4.media.session.action.FOLLOW";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PLAY_FROM_URI = "android.support.v4.media.session.action.PLAY_FROM_URI";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PREPARE = "android.support.v4.media.session.action.PREPARE";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PREPARE_FROM_MEDIA_ID = "android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PREPARE_FROM_SEARCH = "android.support.v4.media.session.action.PREPARE_FROM_SEARCH";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PREPARE_FROM_URI = "android.support.v4.media.session.action.PREPARE_FROM_URI";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_SET_CAPTIONING_ENABLED = "android.support.v4.media.session.action.SET_CAPTIONING_ENABLED";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_SET_RATING = "android.support.v4.media.session.action.SET_RATING";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_SET_REPEAT_MODE = "android.support.v4.media.session.action.SET_REPEAT_MODE";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_SET_SHUFFLE_MODE = "android.support.v4.media.session.action.SET_SHUFFLE_MODE";
    public static final String ACTION_SKIP_AD = "android.support.v4.media.session.action.SKIP_AD";
    public static final String ACTION_UNFOLLOW = "android.support.v4.media.session.action.UNFOLLOW";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE_VALUE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE_VALUE";
    public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
    public static final int FLAG_HANDLES_QUEUE_COMMANDS = 4;
    public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String KEY_EXTRA_BINDER = "android.support.v4.media.session.EXTRA_BINDER";
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String KEY_SESSION_TOKEN2_BUNDLE = "android.support.v4.media.session.SESSION_TOKEN2_BUNDLE";
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String KEY_TOKEN = "android.support.v4.media.session.TOKEN";
    public static final int MEDIA_ATTRIBUTE_ALBUM = 1;
    public static final int MEDIA_ATTRIBUTE_ARTIST = 0;
    public static final int MEDIA_ATTRIBUTE_PLAYLIST = 2;
    public static int d;

    /* renamed from: a  reason: collision with root package name */
    public final d f324a;
    public final MediaControllerCompat b;
    public final ArrayList<OnActiveChangeListener> c;

    /* loaded from: classes.dex */
    public static abstract class Callback {

        /* renamed from: a  reason: collision with root package name */
        public final Object f325a;
        public WeakReference<d> b;
        public a c = null;
        public boolean d;

        /* loaded from: classes.dex */
        public class a extends Handler {
            public a(Looper looper) {
                super(looper);
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    Callback.this.a((MediaSessionManager.RemoteUserInfo) message.obj);
                }
            }
        }

        @RequiresApi(21)
        /* loaded from: classes.dex */
        public class b implements a.InterfaceC0104a {
            public b() {
            }

            @Override // android.support.v4.media.session.a.InterfaceC0104a
            public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
                try {
                    QueueItem queueItem = null;
                    IBinder asBinder = null;
                    queueItem = null;
                    if (str.equals(MediaControllerCompat.COMMAND_GET_EXTRA_BINDER)) {
                        g gVar = (g) Callback.this.b.get();
                        if (gVar != null) {
                            Bundle bundle2 = new Bundle();
                            Token b = gVar.b();
                            IMediaSession extraBinder = b.getExtraBinder();
                            if (extraBinder != null) {
                                asBinder = extraBinder.asBinder();
                            }
                            BundleCompat.putBinder(bundle2, MediaSessionCompat.KEY_EXTRA_BINDER, asBinder);
                            bundle2.putBundle(MediaSessionCompat.KEY_SESSION_TOKEN2_BUNDLE, b.getSessionToken2Bundle());
                            resultReceiver.send(0, bundle2);
                        }
                    } else if (str.equals(MediaControllerCompat.COMMAND_ADD_QUEUE_ITEM)) {
                        Callback.this.onAddQueueItem((MediaDescriptionCompat) bundle.getParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION));
                    } else if (str.equals(MediaControllerCompat.COMMAND_ADD_QUEUE_ITEM_AT)) {
                        Callback.this.onAddQueueItem((MediaDescriptionCompat) bundle.getParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION), bundle.getInt(MediaControllerCompat.COMMAND_ARGUMENT_INDEX));
                    } else if (str.equals(MediaControllerCompat.COMMAND_REMOVE_QUEUE_ITEM)) {
                        Callback.this.onRemoveQueueItem((MediaDescriptionCompat) bundle.getParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION));
                    } else if (str.equals(MediaControllerCompat.COMMAND_REMOVE_QUEUE_ITEM_AT)) {
                        g gVar2 = (g) Callback.this.b.get();
                        if (gVar2 == null || gVar2.f == null) {
                            return;
                        }
                        int i = bundle.getInt(MediaControllerCompat.COMMAND_ARGUMENT_INDEX, -1);
                        if (i >= 0 && i < gVar2.f.size()) {
                            queueItem = gVar2.f.get(i);
                        }
                        if (queueItem != null) {
                            Callback.this.onRemoveQueueItem(queueItem.getDescription());
                        }
                    } else {
                        Callback.this.onCommand(str, bundle, resultReceiver);
                    }
                } catch (BadParcelableException unused) {
                    Log.e("MediaSessionCompat", "Could not unparcel the extra data.");
                }
            }

            @Override // android.support.v4.media.session.a.InterfaceC0104a
            public void onCustomAction(String str, Bundle bundle) {
                Bundle bundle2 = bundle.getBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS);
                MediaSessionCompat.ensureClassLoader(bundle2);
                if (str.equals(MediaSessionCompat.ACTION_PLAY_FROM_URI)) {
                    Callback.this.onPlayFromUri((Uri) bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI), bundle2);
                } else if (str.equals(MediaSessionCompat.ACTION_PREPARE)) {
                    Callback.this.onPrepare();
                } else if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_MEDIA_ID)) {
                    Callback.this.onPrepareFromMediaId(bundle.getString(MediaSessionCompat.ACTION_ARGUMENT_MEDIA_ID), bundle2);
                } else if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_SEARCH)) {
                    Callback.this.onPrepareFromSearch(bundle.getString(MediaSessionCompat.ACTION_ARGUMENT_QUERY), bundle2);
                } else if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_URI)) {
                    Callback.this.onPrepareFromUri((Uri) bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI), bundle2);
                } else if (str.equals(MediaSessionCompat.ACTION_SET_CAPTIONING_ENABLED)) {
                    Callback.this.onSetCaptioningEnabled(bundle.getBoolean(MediaSessionCompat.ACTION_ARGUMENT_CAPTIONING_ENABLED));
                } else if (str.equals(MediaSessionCompat.ACTION_SET_REPEAT_MODE)) {
                    Callback.this.onSetRepeatMode(bundle.getInt(MediaSessionCompat.ACTION_ARGUMENT_REPEAT_MODE));
                } else if (str.equals(MediaSessionCompat.ACTION_SET_SHUFFLE_MODE)) {
                    Callback.this.onSetShuffleMode(bundle.getInt(MediaSessionCompat.ACTION_ARGUMENT_SHUFFLE_MODE));
                } else if (str.equals(MediaSessionCompat.ACTION_SET_RATING)) {
                    Callback.this.onSetRating((RatingCompat) bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_RATING), bundle2);
                } else {
                    Callback.this.onCustomAction(str, bundle);
                }
            }

            @Override // android.support.v4.media.session.a.InterfaceC0104a
            public void onFastForward() {
                Callback.this.onFastForward();
            }

            @Override // android.support.v4.media.session.a.InterfaceC0104a
            public boolean onMediaButtonEvent(Intent intent) {
                return Callback.this.onMediaButtonEvent(intent);
            }

            @Override // android.support.v4.media.session.a.InterfaceC0104a
            public void onPause() {
                Callback.this.onPause();
            }

            @Override // android.support.v4.media.session.a.InterfaceC0104a
            public void onPlay() {
                Callback.this.onPlay();
            }

            @Override // android.support.v4.media.session.a.InterfaceC0104a
            public void onPlayFromMediaId(String str, Bundle bundle) {
                Callback.this.onPlayFromMediaId(str, bundle);
            }

            @Override // android.support.v4.media.session.a.InterfaceC0104a
            public void onPlayFromSearch(String str, Bundle bundle) {
                Callback.this.onPlayFromSearch(str, bundle);
            }

            @Override // android.support.v4.media.session.a.InterfaceC0104a
            public void onRewind() {
                Callback.this.onRewind();
            }

            @Override // android.support.v4.media.session.a.InterfaceC0104a
            public void onSeekTo(long j) {
                Callback.this.onSeekTo(j);
            }

            @Override // android.support.v4.media.session.a.InterfaceC0104a
            public void onSetRating(Object obj) {
                Callback.this.onSetRating(RatingCompat.fromRating(obj));
            }

            public void onSetRating(Object obj, Bundle bundle) {
            }

            @Override // android.support.v4.media.session.a.InterfaceC0104a
            public void onSkipToNext() {
                Callback.this.onSkipToNext();
            }

            @Override // android.support.v4.media.session.a.InterfaceC0104a
            public void onSkipToPrevious() {
                Callback.this.onSkipToPrevious();
            }

            @Override // android.support.v4.media.session.a.InterfaceC0104a
            public void onSkipToQueueItem(long j) {
                Callback.this.onSkipToQueueItem(j);
            }

            @Override // android.support.v4.media.session.a.InterfaceC0104a
            public void onStop() {
                Callback.this.onStop();
            }
        }

        @RequiresApi(23)
        /* loaded from: classes.dex */
        public class c extends b implements MediaSessionCompatApi23.Callback {
            public c() {
                super();
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi23.Callback
            public void onPlayFromUri(Uri uri, Bundle bundle) {
                Callback.this.onPlayFromUri(uri, bundle);
            }
        }

        @RequiresApi(24)
        /* loaded from: classes.dex */
        public class d extends c implements MediaSessionCompatApi24.Callback {
            public d() {
                super();
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi24.Callback
            public void onPrepare() {
                Callback.this.onPrepare();
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi24.Callback
            public void onPrepareFromMediaId(String str, Bundle bundle) {
                Callback.this.onPrepareFromMediaId(str, bundle);
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi24.Callback
            public void onPrepareFromSearch(String str, Bundle bundle) {
                Callback.this.onPrepareFromSearch(str, bundle);
            }

            @Override // android.support.v4.media.session.MediaSessionCompatApi24.Callback
            public void onPrepareFromUri(Uri uri, Bundle bundle) {
                Callback.this.onPrepareFromUri(uri, bundle);
            }
        }

        public Callback() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 24) {
                this.f325a = MediaSessionCompatApi24.a(new d());
            } else if (i >= 23) {
                this.f325a = MediaSessionCompatApi23.a(new c());
            } else if (i >= 21) {
                this.f325a = android.support.v4.media.session.a.a(new b());
            } else {
                this.f325a = null;
            }
        }

        public void a(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            if (this.d) {
                this.d = false;
                this.c.removeMessages(1);
                d dVar = this.b.get();
                if (dVar == null) {
                    return;
                }
                PlaybackStateCompat playbackState = dVar.getPlaybackState();
                long actions = playbackState == null ? 0L : playbackState.getActions();
                boolean z = playbackState != null && playbackState.getState() == 3;
                boolean z2 = (516 & actions) != 0;
                boolean z3 = (actions & 514) != 0;
                dVar.p(remoteUserInfo);
                if (z && z3) {
                    onPause();
                } else if (!z && z2) {
                    onPlay();
                }
                dVar.p(null);
            }
        }

        public void b(d dVar, Handler handler) {
            this.b = new WeakReference<>(dVar);
            a aVar = this.c;
            if (aVar != null) {
                aVar.removeCallbacksAndMessages(null);
            }
            this.c = new a(handler.getLooper());
        }

        public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
        }

        public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        }

        public void onCustomAction(String str, Bundle bundle) {
        }

        public void onFastForward() {
        }

        public boolean onMediaButtonEvent(Intent intent) {
            d dVar;
            KeyEvent keyEvent;
            if (Build.VERSION.SDK_INT >= 27 || (dVar = this.b.get()) == null || this.c == null || (keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT")) == null || keyEvent.getAction() != 0) {
                return false;
            }
            MediaSessionManager.RemoteUserInfo s = dVar.s();
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 79 && keyCode != 85) {
                a(s);
                return false;
            }
            if (keyEvent.getRepeatCount() > 0) {
                a(s);
            } else if (this.d) {
                this.c.removeMessages(1);
                this.d = false;
                PlaybackStateCompat playbackState = dVar.getPlaybackState();
                if (((playbackState == null ? 0L : playbackState.getActions()) & 32) != 0) {
                    onSkipToNext();
                }
            } else {
                this.d = true;
                a aVar = this.c;
                aVar.sendMessageDelayed(aVar.obtainMessage(1, s), ViewConfiguration.getDoubleTapTimeout());
            }
            return true;
        }

        public void onPause() {
        }

        public void onPlay() {
        }

        public void onPlayFromMediaId(String str, Bundle bundle) {
        }

        public void onPlayFromSearch(String str, Bundle bundle) {
        }

        public void onPlayFromUri(Uri uri, Bundle bundle) {
        }

        public void onPrepare() {
        }

        public void onPrepareFromMediaId(String str, Bundle bundle) {
        }

        public void onPrepareFromSearch(String str, Bundle bundle) {
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle) {
        }

        public void onRemoveQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        @Deprecated
        public void onRemoveQueueItemAt(int i) {
        }

        public void onRewind() {
        }

        public void onSeekTo(long j) {
        }

        public void onSetCaptioningEnabled(boolean z) {
        }

        public void onSetRating(RatingCompat ratingCompat) {
        }

        public void onSetRating(RatingCompat ratingCompat, Bundle bundle) {
        }

        public void onSetRepeatMode(int i) {
        }

        public void onSetShuffleMode(int i) {
        }

        public void onSkipToNext() {
        }

        public void onSkipToPrevious() {
        }

        public void onSkipToQueueItem(long j) {
        }

        public void onStop() {
        }
    }

    /* loaded from: classes.dex */
    public interface OnActiveChangeListener {
        void onActiveChanged();
    }

    /* loaded from: classes.dex */
    public static final class QueueItem implements Parcelable {
        public static final Parcelable.Creator<QueueItem> CREATOR = new a();
        public static final int UNKNOWN_ID = -1;
        public final MediaDescriptionCompat h;
        public final long i;
        public Object j;

        /* loaded from: classes.dex */
        public static class a implements Parcelable.Creator<QueueItem> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public QueueItem createFromParcel(Parcel parcel) {
                return new QueueItem(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public QueueItem[] newArray(int i) {
                return new QueueItem[i];
            }
        }

        public QueueItem(MediaDescriptionCompat mediaDescriptionCompat, long j) {
            this(null, mediaDescriptionCompat, j);
        }

        public static QueueItem fromQueueItem(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return new QueueItem(obj, MediaDescriptionCompat.fromMediaDescription(a.c.b(obj)), a.c.c(obj));
        }

        public static List<QueueItem> fromQueueItemList(List<?> list) {
            if (list == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(fromQueueItem(it.next()));
            }
            return arrayList;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public MediaDescriptionCompat getDescription() {
            return this.h;
        }

        public long getQueueId() {
            return this.i;
        }

        public Object getQueueItem() {
            Object obj = this.j;
            if (obj != null || Build.VERSION.SDK_INT < 21) {
                return obj;
            }
            Object a2 = a.c.a(this.h.getMediaDescription(), this.i);
            this.j = a2;
            return a2;
        }

        public String toString() {
            return "MediaSession.QueueItem {Description=" + this.h + ", Id=" + this.i + " }";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            this.h.writeToParcel(parcel, i);
            parcel.writeLong(this.i);
        }

        public QueueItem(Object obj, MediaDescriptionCompat mediaDescriptionCompat, long j) {
            if (mediaDescriptionCompat == null) {
                throw new IllegalArgumentException("Description cannot be null.");
            }
            if (j != -1) {
                this.h = mediaDescriptionCompat;
                this.i = j;
                this.j = obj;
                return;
            }
            throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
        }

        public QueueItem(Parcel parcel) {
            this.h = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            this.i = parcel.readLong();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface SessionFlags {
    }

    /* loaded from: classes.dex */
    public static final class Token implements Parcelable {
        public static final Parcelable.Creator<Token> CREATOR = new a();
        public final Object h;
        public IMediaSession i;
        public Bundle j;

        /* loaded from: classes.dex */
        public static class a implements Parcelable.Creator<Token> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public Token createFromParcel(Parcel parcel) {
                Object readStrongBinder;
                if (Build.VERSION.SDK_INT >= 21) {
                    readStrongBinder = parcel.readParcelable(null);
                } else {
                    readStrongBinder = parcel.readStrongBinder();
                }
                return new Token(readStrongBinder);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public Token[] newArray(int i) {
                return new Token[i];
            }
        }

        public Token(Object obj) {
            this(obj, null, null);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static Token fromBundle(Bundle bundle) {
            if (bundle == null) {
                return null;
            }
            IMediaSession asInterface = IMediaSession.Stub.asInterface(BundleCompat.getBinder(bundle, MediaSessionCompat.KEY_EXTRA_BINDER));
            Bundle bundle2 = bundle.getBundle(MediaSessionCompat.KEY_SESSION_TOKEN2_BUNDLE);
            Token token = (Token) bundle.getParcelable(MediaSessionCompat.KEY_TOKEN);
            if (token == null) {
                return null;
            }
            return new Token(token.h, asInterface, bundle2);
        }

        public static Token fromToken(Object obj) {
            return fromToken(obj, null);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Token) {
                Token token = (Token) obj;
                Object obj2 = this.h;
                if (obj2 == null) {
                    return token.h == null;
                }
                Object obj3 = token.h;
                if (obj3 == null) {
                    return false;
                }
                return obj2.equals(obj3);
            }
            return false;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public IMediaSession getExtraBinder() {
            return this.i;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Bundle getSessionToken2Bundle() {
            return this.j;
        }

        public Object getToken() {
            return this.h;
        }

        public int hashCode() {
            Object obj = this.h;
            if (obj == null) {
                return 0;
            }
            return obj.hashCode();
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void setExtraBinder(IMediaSession iMediaSession) {
            this.i = iMediaSession;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void setSessionToken2Bundle(Bundle bundle) {
            this.j = bundle;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putParcelable(MediaSessionCompat.KEY_TOKEN, this);
            IMediaSession iMediaSession = this.i;
            if (iMediaSession != null) {
                BundleCompat.putBinder(bundle, MediaSessionCompat.KEY_EXTRA_BINDER, iMediaSession.asBinder());
            }
            Bundle bundle2 = this.j;
            if (bundle2 != null) {
                bundle.putBundle(MediaSessionCompat.KEY_SESSION_TOKEN2_BUNDLE, bundle2);
            }
            return bundle;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (Build.VERSION.SDK_INT >= 21) {
                parcel.writeParcelable((Parcelable) this.h, i);
            } else {
                parcel.writeStrongBinder((IBinder) this.h);
            }
        }

        public Token(Object obj, IMediaSession iMediaSession) {
            this(obj, iMediaSession, null);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static Token fromToken(Object obj, IMediaSession iMediaSession) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return new Token(android.support.v4.media.session.a.u(obj), iMediaSession);
        }

        public Token(Object obj, IMediaSession iMediaSession, Bundle bundle) {
            this.h = obj;
            this.i = iMediaSession;
            this.j = bundle;
        }
    }

    /* loaded from: classes.dex */
    public class a extends Callback {
        public a(MediaSessionCompat mediaSessionCompat) {
        }
    }

    /* loaded from: classes.dex */
    public class b extends Callback {
        public b(MediaSessionCompat mediaSessionCompat) {
        }
    }

    /* loaded from: classes.dex */
    public class c extends Callback {
        public c(MediaSessionCompat mediaSessionCompat) {
        }
    }

    /* loaded from: classes.dex */
    public interface d {
        void a(int i);

        Token b();

        void c(String str, Bundle bundle);

        void d(Callback callback, Handler handler);

        void e(CharSequence charSequence);

        void f(MediaMetadataCompat mediaMetadataCompat);

        void g(int i);

        PlaybackStateCompat getPlaybackState();

        void h(List<QueueItem> list);

        void i(PlaybackStateCompat playbackStateCompat);

        boolean isActive();

        String j();

        void k(PendingIntent pendingIntent);

        void l(int i);

        void m(PendingIntent pendingIntent);

        Object n();

        void o(boolean z);

        void p(MediaSessionManager.RemoteUserInfo remoteUserInfo);

        Object q();

        void r(VolumeProviderCompat volumeProviderCompat);

        void release();

        MediaSessionManager.RemoteUserInfo s();

        void setCaptioningEnabled(boolean z);

        void setExtras(Bundle bundle);

        void setRepeatMode(int i);

        void setShuffleMode(int i);
    }

    @RequiresApi(18)
    /* loaded from: classes.dex */
    public static class e extends i {
        public static boolean G = true;

        /* loaded from: classes.dex */
        public class a implements RemoteControlClient.OnPlaybackPositionUpdateListener {
            public a() {
            }

            @Override // android.media.RemoteControlClient.OnPlaybackPositionUpdateListener
            public void onPlaybackPositionUpdate(long j) {
                e.this.x(18, -1, -1, Long.valueOf(j), null);
            }
        }

        public e(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
            super(context, str, componentName, pendingIntent);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i
        public void K(PlaybackStateCompat playbackStateCompat) {
            long position = playbackStateCompat.getPosition();
            float playbackSpeed = playbackStateCompat.getPlaybackSpeed();
            long lastPositionUpdateTime = playbackStateCompat.getLastPositionUpdateTime();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (playbackStateCompat.getState() == 3) {
                long j = 0;
                if (position > 0) {
                    if (lastPositionUpdateTime > 0) {
                        j = elapsedRealtime - lastPositionUpdateTime;
                        if (playbackSpeed > 0.0f && playbackSpeed != 1.0f) {
                            j = ((float) j) * playbackSpeed;
                        }
                    }
                    position += j;
                }
            }
            this.h.setPlaybackState(v(playbackStateCompat.getState()), position, playbackSpeed);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i
        public void M(PendingIntent pendingIntent, ComponentName componentName) {
            if (G) {
                this.g.unregisterMediaButtonEventReceiver(pendingIntent);
            } else {
                super.M(pendingIntent, componentName);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i, android.support.v4.media.session.MediaSessionCompat.d
        public void d(Callback callback, Handler handler) {
            super.d(callback, handler);
            if (callback == null) {
                this.h.setPlaybackPositionUpdateListener(null);
                return;
            }
            this.h.setPlaybackPositionUpdateListener(new a());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i
        public int w(long j) {
            int w = super.w(j);
            return (j & 256) != 0 ? w | 256 : w;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i
        public void y(PendingIntent pendingIntent, ComponentName componentName) {
            if (G) {
                try {
                    this.g.registerMediaButtonEventReceiver(pendingIntent);
                } catch (NullPointerException unused) {
                    Log.w("MediaSessionCompat", "Unable to register media button event receiver with PendingIntent, falling back to ComponentName.");
                    G = false;
                }
            }
            if (G) {
                return;
            }
            super.y(pendingIntent, componentName);
        }
    }

    @RequiresApi(19)
    /* loaded from: classes.dex */
    public static class f extends e {

        /* loaded from: classes.dex */
        public class a implements RemoteControlClient.OnMetadataUpdateListener {
            public a() {
            }

            @Override // android.media.RemoteControlClient.OnMetadataUpdateListener
            public void onMetadataUpdate(int i, Object obj) {
                if (i == 268435457 && (obj instanceof Rating)) {
                    f.this.x(19, -1, -1, RatingCompat.fromRating(obj), null);
                }
            }
        }

        public f(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
            super(context, str, componentName, pendingIntent);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.e, android.support.v4.media.session.MediaSessionCompat.i, android.support.v4.media.session.MediaSessionCompat.d
        public void d(Callback callback, Handler handler) {
            super.d(callback, handler);
            if (callback == null) {
                this.h.setMetadataUpdateListener(null);
                return;
            }
            this.h.setMetadataUpdateListener(new a());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.i
        public RemoteControlClient.MetadataEditor u(Bundle bundle) {
            RemoteControlClient.MetadataEditor u = super.u(bundle);
            PlaybackStateCompat playbackStateCompat = this.t;
            if (((playbackStateCompat == null ? 0L : playbackStateCompat.getActions()) & 128) != 0) {
                u.addEditableKey(268435457);
            }
            if (bundle == null) {
                return u;
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_YEAR)) {
                u.putLong(8, bundle.getLong(MediaMetadataCompat.METADATA_KEY_YEAR));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_RATING)) {
                u.putObject(101, (Object) bundle.getParcelable(MediaMetadataCompat.METADATA_KEY_RATING));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_USER_RATING)) {
                u.putObject(268435457, (Object) bundle.getParcelable(MediaMetadataCompat.METADATA_KEY_USER_RATING));
            }
            return u;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.e, android.support.v4.media.session.MediaSessionCompat.i
        public int w(long j) {
            int w = super.w(j);
            return (j & 128) != 0 ? w | 512 : w;
        }
    }

    @RequiresApi(28)
    /* loaded from: classes.dex */
    public static class h extends g {
        public h(Context context, String str, Bundle bundle) {
            super(context, str, bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.g, android.support.v4.media.session.MediaSessionCompat.d
        public void p(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.g, android.support.v4.media.session.MediaSessionCompat.d
        @NonNull
        public final MediaSessionManager.RemoteUserInfo s() {
            return new MediaSessionManager.RemoteUserInfo(((MediaSession) this.f330a).getCurrentControllerInfo());
        }
    }

    /* loaded from: classes.dex */
    public static class i implements d {
        public int A;
        public Bundle B;
        public int C;
        public int D;
        public VolumeProviderCompat E;

        /* renamed from: a  reason: collision with root package name */
        public final ComponentName f332a;
        public final PendingIntent b;
        public final c c;
        public final Token d;
        public final String e;
        public final String f;
        public final AudioManager g;
        public final RemoteControlClient h;
        public d k;
        public volatile Callback p;
        public MediaSessionManager.RemoteUserInfo q;
        public int r;
        public MediaMetadataCompat s;
        public PlaybackStateCompat t;
        public PendingIntent u;
        public List<QueueItem> v;
        public CharSequence w;
        public int x;
        public boolean y;
        public int z;
        public final Object i = new Object();
        public final RemoteCallbackList<IMediaControllerCallback> j = new RemoteCallbackList<>();
        public boolean l = false;
        public boolean m = false;
        public boolean n = false;
        public boolean o = false;
        public VolumeProviderCompat.Callback F = new a();

        /* loaded from: classes.dex */
        public class a extends VolumeProviderCompat.Callback {
            public a() {
            }

            @Override // androidx.media.VolumeProviderCompat.Callback
            public void onVolumeChanged(VolumeProviderCompat volumeProviderCompat) {
                if (i.this.E != volumeProviderCompat) {
                    return;
                }
                i iVar = i.this;
                i.this.J(new ParcelableVolumeInfo(iVar.C, iVar.D, volumeProviderCompat.getVolumeControl(), volumeProviderCompat.getMaxVolume(), volumeProviderCompat.getCurrentVolume()));
            }
        }

        /* loaded from: classes.dex */
        public static final class b {

            /* renamed from: a  reason: collision with root package name */
            public final String f334a;
            public final Bundle b;
            public final ResultReceiver c;

            public b(String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.f334a = str;
                this.b = bundle;
                this.c = resultReceiver;
            }
        }

        /* loaded from: classes.dex */
        public class c extends IMediaSession.Stub {
            public c() {
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                g(25, mediaDescriptionCompat);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void addQueueItemAt(MediaDescriptionCompat mediaDescriptionCompat, int i) {
                h(26, mediaDescriptionCompat, i);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void adjustVolume(int i, int i2, String str) {
                i.this.t(i, i2);
            }

            public void e(int i) {
                i.this.x(i, 0, 0, null, null);
            }

            public void f(int i, int i2) {
                i.this.x(i, i2, 0, null, null);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void fastForward() throws RemoteException {
                e(16);
            }

            public void g(int i, Object obj) {
                i.this.x(i, 0, 0, obj, null);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public Bundle getExtras() {
                Bundle bundle;
                synchronized (i.this.i) {
                    bundle = i.this.B;
                }
                return bundle;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public long getFlags() {
                long j;
                synchronized (i.this.i) {
                    j = i.this.r;
                }
                return j;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public PendingIntent getLaunchPendingIntent() {
                PendingIntent pendingIntent;
                synchronized (i.this.i) {
                    pendingIntent = i.this.u;
                }
                return pendingIntent;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public MediaMetadataCompat getMetadata() {
                return i.this.s;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public String getPackageName() {
                return i.this.e;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public PlaybackStateCompat getPlaybackState() {
                PlaybackStateCompat playbackStateCompat;
                MediaMetadataCompat mediaMetadataCompat;
                synchronized (i.this.i) {
                    i iVar = i.this;
                    playbackStateCompat = iVar.t;
                    mediaMetadataCompat = iVar.s;
                }
                return MediaSessionCompat.a(playbackStateCompat, mediaMetadataCompat);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public List<QueueItem> getQueue() {
                List<QueueItem> list;
                synchronized (i.this.i) {
                    list = i.this.v;
                }
                return list;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public CharSequence getQueueTitle() {
                return i.this.w;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getRatingType() {
                return i.this.x;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getRepeatMode() {
                return i.this.z;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getShuffleMode() {
                return i.this.A;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public String getTag() {
                return i.this.f;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public ParcelableVolumeInfo getVolumeAttributes() {
                int i;
                int i2;
                int i3;
                int streamMaxVolume;
                int streamVolume;
                synchronized (i.this.i) {
                    i iVar = i.this;
                    i = iVar.C;
                    i2 = iVar.D;
                    VolumeProviderCompat volumeProviderCompat = iVar.E;
                    i3 = 2;
                    if (i == 2) {
                        int volumeControl = volumeProviderCompat.getVolumeControl();
                        int maxVolume = volumeProviderCompat.getMaxVolume();
                        streamVolume = volumeProviderCompat.getCurrentVolume();
                        streamMaxVolume = maxVolume;
                        i3 = volumeControl;
                    } else {
                        streamMaxVolume = iVar.g.getStreamMaxVolume(i2);
                        streamVolume = i.this.g.getStreamVolume(i2);
                    }
                }
                return new ParcelableVolumeInfo(i, i2, i3, streamMaxVolume, streamVolume);
            }

            public void h(int i, Object obj, int i2) {
                i.this.x(i, i2, 0, obj, null);
            }

            public void i(int i, Object obj, Bundle bundle) {
                i.this.x(i, 0, 0, obj, bundle);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean isCaptioningEnabled() {
                return i.this.y;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean isShuffleModeEnabledRemoved() {
                return false;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean isTransportControlEnabled() {
                return (i.this.r & 2) != 0;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void next() throws RemoteException {
                e(14);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void pause() throws RemoteException {
                e(12);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void play() throws RemoteException {
                e(7);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void playFromMediaId(String str, Bundle bundle) throws RemoteException {
                i(8, str, bundle);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void playFromSearch(String str, Bundle bundle) throws RemoteException {
                i(9, str, bundle);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void playFromUri(Uri uri, Bundle bundle) throws RemoteException {
                i(10, uri, bundle);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepare() throws RemoteException {
                e(3);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepareFromMediaId(String str, Bundle bundle) throws RemoteException {
                i(4, str, bundle);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepareFromSearch(String str, Bundle bundle) throws RemoteException {
                i(5, str, bundle);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepareFromUri(Uri uri, Bundle bundle) throws RemoteException {
                i(6, uri, bundle);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void previous() throws RemoteException {
                e(15);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void rate(RatingCompat ratingCompat) throws RemoteException {
                g(19, ratingCompat);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void rateWithExtras(RatingCompat ratingCompat, Bundle bundle) throws RemoteException {
                i(31, ratingCompat, bundle);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                if (i.this.l) {
                    try {
                        iMediaControllerCallback.onSessionDestroyed();
                        return;
                    } catch (Exception unused) {
                        return;
                    }
                }
                i.this.j.register(iMediaControllerCallback, new MediaSessionManager.RemoteUserInfo(MediaSessionManager.RemoteUserInfo.LEGACY_CONTROLLER, Binder.getCallingPid(), Binder.getCallingUid()));
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                g(27, mediaDescriptionCompat);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void removeQueueItemAt(int i) {
                f(28, i);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void rewind() throws RemoteException {
                e(17);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void seekTo(long j) throws RemoteException {
                g(18, Long.valueOf(j));
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void sendCommand(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) {
                g(1, new b(str, bundle, resultReceiverWrapper.h));
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void sendCustomAction(String str, Bundle bundle) throws RemoteException {
                i(20, str, bundle);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean sendMediaButton(KeyEvent keyEvent) {
                boolean z = (i.this.r & 1) != 0;
                if (z) {
                    g(21, keyEvent);
                }
                return z;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setCaptioningEnabled(boolean z) throws RemoteException {
                g(29, Boolean.valueOf(z));
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setRepeatMode(int i) throws RemoteException {
                f(23, i);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setShuffleMode(int i) throws RemoteException {
                f(30, i);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setShuffleModeEnabledRemoved(boolean z) throws RemoteException {
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setVolumeTo(int i, int i2, String str) {
                i.this.L(i, i2);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void skipToQueueItem(long j) {
                g(11, Long.valueOf(j));
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void stop() throws RemoteException {
                e(13);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                i.this.j.unregister(iMediaControllerCallback);
            }
        }

        /* loaded from: classes.dex */
        public class d extends Handler {
            public d(Looper looper) {
                super(looper);
            }

            public final void a(KeyEvent keyEvent, Callback callback) {
                if (keyEvent == null || keyEvent.getAction() != 0) {
                    return;
                }
                PlaybackStateCompat playbackStateCompat = i.this.t;
                long actions = playbackStateCompat == null ? 0L : playbackStateCompat.getActions();
                int keyCode = keyEvent.getKeyCode();
                if (keyCode != 79) {
                    if (keyCode == 126) {
                        if ((actions & 4) != 0) {
                            callback.onPlay();
                            return;
                        }
                        return;
                    } else if (keyCode == 127) {
                        if ((actions & 2) != 0) {
                            callback.onPause();
                            return;
                        }
                        return;
                    } else {
                        switch (keyCode) {
                            case 85:
                                break;
                            case 86:
                                if ((actions & 1) != 0) {
                                    callback.onStop();
                                    return;
                                }
                                return;
                            case 87:
                                if ((actions & 32) != 0) {
                                    callback.onSkipToNext();
                                    return;
                                }
                                return;
                            case 88:
                                if ((actions & 16) != 0) {
                                    callback.onSkipToPrevious();
                                    return;
                                }
                                return;
                            case 89:
                                if ((actions & 8) != 0) {
                                    callback.onRewind();
                                    return;
                                }
                                return;
                            case 90:
                                if ((actions & 64) != 0) {
                                    callback.onFastForward();
                                    return;
                                }
                                return;
                            default:
                                return;
                        }
                    }
                }
                Log.w("MediaSessionCompat", "KEYCODE_MEDIA_PLAY_PAUSE and KEYCODE_HEADSETHOOK are handled already");
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                Callback callback = i.this.p;
                if (callback == null) {
                    return;
                }
                Bundle data = message.getData();
                MediaSessionCompat.ensureClassLoader(data);
                i.this.p(new MediaSessionManager.RemoteUserInfo(data.getString("data_calling_pkg"), data.getInt(MediaBrowserProtocol.DATA_CALLING_PID), data.getInt(MediaBrowserProtocol.DATA_CALLING_UID)));
                Bundle bundle = data.getBundle("data_extras");
                MediaSessionCompat.ensureClassLoader(bundle);
                try {
                    switch (message.what) {
                        case 1:
                            b bVar = (b) message.obj;
                            callback.onCommand(bVar.f334a, bVar.b, bVar.c);
                            break;
                        case 2:
                            i.this.t(message.arg1, 0);
                            break;
                        case 3:
                            callback.onPrepare();
                            break;
                        case 4:
                            callback.onPrepareFromMediaId((String) message.obj, bundle);
                            break;
                        case 5:
                            callback.onPrepareFromSearch((String) message.obj, bundle);
                            break;
                        case 6:
                            callback.onPrepareFromUri((Uri) message.obj, bundle);
                            break;
                        case 7:
                            callback.onPlay();
                            break;
                        case 8:
                            callback.onPlayFromMediaId((String) message.obj, bundle);
                            break;
                        case 9:
                            callback.onPlayFromSearch((String) message.obj, bundle);
                            break;
                        case 10:
                            callback.onPlayFromUri((Uri) message.obj, bundle);
                            break;
                        case 11:
                            callback.onSkipToQueueItem(((Long) message.obj).longValue());
                            break;
                        case 12:
                            callback.onPause();
                            break;
                        case 13:
                            callback.onStop();
                            break;
                        case 14:
                            callback.onSkipToNext();
                            break;
                        case 15:
                            callback.onSkipToPrevious();
                            break;
                        case 16:
                            callback.onFastForward();
                            break;
                        case 17:
                            callback.onRewind();
                            break;
                        case 18:
                            callback.onSeekTo(((Long) message.obj).longValue());
                            break;
                        case 19:
                            callback.onSetRating((RatingCompat) message.obj);
                            break;
                        case 20:
                            callback.onCustomAction((String) message.obj, bundle);
                            break;
                        case 21:
                            KeyEvent keyEvent = (KeyEvent) message.obj;
                            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                            intent.putExtra("android.intent.extra.KEY_EVENT", keyEvent);
                            if (!callback.onMediaButtonEvent(intent)) {
                                a(keyEvent, callback);
                                break;
                            }
                            break;
                        case 22:
                            i.this.L(message.arg1, 0);
                            break;
                        case 23:
                            callback.onSetRepeatMode(message.arg1);
                            break;
                        case 25:
                            callback.onAddQueueItem((MediaDescriptionCompat) message.obj);
                            break;
                        case 26:
                            callback.onAddQueueItem((MediaDescriptionCompat) message.obj, message.arg1);
                            break;
                        case 27:
                            callback.onRemoveQueueItem((MediaDescriptionCompat) message.obj);
                            break;
                        case 28:
                            List<QueueItem> list = i.this.v;
                            if (list != null) {
                                int i = message.arg1;
                                QueueItem queueItem = (i < 0 || i >= list.size()) ? null : i.this.v.get(message.arg1);
                                if (queueItem != null) {
                                    callback.onRemoveQueueItem(queueItem.getDescription());
                                    break;
                                }
                            }
                            break;
                        case 29:
                            callback.onSetCaptioningEnabled(((Boolean) message.obj).booleanValue());
                            break;
                        case 30:
                            callback.onSetShuffleMode(message.arg1);
                            break;
                        case 31:
                            callback.onSetRating((RatingCompat) message.obj, bundle);
                            break;
                    }
                } finally {
                    i.this.p(null);
                }
            }
        }

        public i(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
            if (componentName != null) {
                this.e = context.getPackageName();
                this.g = (AudioManager) context.getSystemService("audio");
                this.f = str;
                this.f332a = componentName;
                this.b = pendingIntent;
                c cVar = new c();
                this.c = cVar;
                this.d = new Token(cVar);
                this.x = 0;
                this.C = 1;
                this.D = 3;
                this.h = new RemoteControlClient(pendingIntent);
                return;
            }
            throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
        }

        public final void A(String str, Bundle bundle) {
            for (int beginBroadcast = this.j.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.j.getBroadcastItem(beginBroadcast).onEvent(str, bundle);
                } catch (RemoteException unused) {
                }
            }
            this.j.finishBroadcast();
        }

        public final void B(Bundle bundle) {
            for (int beginBroadcast = this.j.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.j.getBroadcastItem(beginBroadcast).onExtrasChanged(bundle);
                } catch (RemoteException unused) {
                }
            }
            this.j.finishBroadcast();
        }

        public final void C(MediaMetadataCompat mediaMetadataCompat) {
            for (int beginBroadcast = this.j.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.j.getBroadcastItem(beginBroadcast).onMetadataChanged(mediaMetadataCompat);
                } catch (RemoteException unused) {
                }
            }
            this.j.finishBroadcast();
        }

        public final void D(List<QueueItem> list) {
            for (int beginBroadcast = this.j.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.j.getBroadcastItem(beginBroadcast).onQueueChanged(list);
                } catch (RemoteException unused) {
                }
            }
            this.j.finishBroadcast();
        }

        public final void E(CharSequence charSequence) {
            for (int beginBroadcast = this.j.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.j.getBroadcastItem(beginBroadcast).onQueueTitleChanged(charSequence);
                } catch (RemoteException unused) {
                }
            }
            this.j.finishBroadcast();
        }

        public final void F(int i) {
            for (int beginBroadcast = this.j.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.j.getBroadcastItem(beginBroadcast).onRepeatModeChanged(i);
                } catch (RemoteException unused) {
                }
            }
            this.j.finishBroadcast();
        }

        public final void G() {
            for (int beginBroadcast = this.j.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.j.getBroadcastItem(beginBroadcast).onSessionDestroyed();
                } catch (RemoteException unused) {
                }
            }
            this.j.finishBroadcast();
            this.j.kill();
        }

        public final void H(int i) {
            for (int beginBroadcast = this.j.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.j.getBroadcastItem(beginBroadcast).onShuffleModeChanged(i);
                } catch (RemoteException unused) {
                }
            }
            this.j.finishBroadcast();
        }

        public final void I(PlaybackStateCompat playbackStateCompat) {
            for (int beginBroadcast = this.j.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.j.getBroadcastItem(beginBroadcast).onPlaybackStateChanged(playbackStateCompat);
                } catch (RemoteException unused) {
                }
            }
            this.j.finishBroadcast();
        }

        public void J(ParcelableVolumeInfo parcelableVolumeInfo) {
            for (int beginBroadcast = this.j.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.j.getBroadcastItem(beginBroadcast).onVolumeInfoChanged(parcelableVolumeInfo);
                } catch (RemoteException unused) {
                }
            }
            this.j.finishBroadcast();
        }

        public void K(PlaybackStateCompat playbackStateCompat) {
            this.h.setPlaybackState(v(playbackStateCompat.getState()));
        }

        public void L(int i, int i2) {
            if (this.C == 2) {
                VolumeProviderCompat volumeProviderCompat = this.E;
                if (volumeProviderCompat != null) {
                    volumeProviderCompat.onSetVolumeTo(i);
                    return;
                }
                return;
            }
            this.g.setStreamVolume(this.D, i, i2);
        }

        public void M(PendingIntent pendingIntent, ComponentName componentName) {
            this.g.unregisterMediaButtonEventReceiver(componentName);
        }

        public boolean N() {
            if (this.m) {
                boolean z = this.n;
                if (!z && (this.r & 1) != 0) {
                    y(this.b, this.f332a);
                    this.n = true;
                } else if (z && (this.r & 1) == 0) {
                    M(this.b, this.f332a);
                    this.n = false;
                }
                boolean z2 = this.o;
                if (!z2 && (this.r & 2) != 0) {
                    this.g.registerRemoteControlClient(this.h);
                    this.o = true;
                    return true;
                } else if (z2 && (this.r & 2) == 0) {
                    this.h.setPlaybackState(0);
                    this.g.unregisterRemoteControlClient(this.h);
                    this.o = false;
                }
            } else {
                if (this.n) {
                    M(this.b, this.f332a);
                    this.n = false;
                }
                if (this.o) {
                    this.h.setPlaybackState(0);
                    this.g.unregisterRemoteControlClient(this.h);
                    this.o = false;
                }
            }
            return false;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void a(int i) {
            synchronized (this.i) {
                this.r = i;
            }
            N();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public Token b() {
            return this.d;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void c(String str, Bundle bundle) {
            A(str, bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void d(Callback callback, Handler handler) {
            this.p = callback;
            if (callback != null) {
                if (handler == null) {
                    handler = new Handler();
                }
                synchronized (this.i) {
                    d dVar = this.k;
                    if (dVar != null) {
                        dVar.removeCallbacksAndMessages(null);
                    }
                    this.k = new d(handler.getLooper());
                    this.p.b(this, handler);
                }
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void e(CharSequence charSequence) {
            this.w = charSequence;
            E(charSequence);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void f(MediaMetadataCompat mediaMetadataCompat) {
            if (mediaMetadataCompat != null) {
                mediaMetadataCompat = new MediaMetadataCompat.Builder(mediaMetadataCompat, MediaSessionCompat.d).build();
            }
            synchronized (this.i) {
                this.s = mediaMetadataCompat;
            }
            C(mediaMetadataCompat);
            if (this.m) {
                u(mediaMetadataCompat == null ? null : mediaMetadataCompat.getBundle()).apply();
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void g(int i) {
            this.x = i;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public PlaybackStateCompat getPlaybackState() {
            PlaybackStateCompat playbackStateCompat;
            synchronized (this.i) {
                playbackStateCompat = this.t;
            }
            return playbackStateCompat;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void h(List<QueueItem> list) {
            this.v = list;
            D(list);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void i(PlaybackStateCompat playbackStateCompat) {
            synchronized (this.i) {
                this.t = playbackStateCompat;
            }
            I(playbackStateCompat);
            if (this.m) {
                if (playbackStateCompat == null) {
                    this.h.setPlaybackState(0);
                    this.h.setTransportControlFlags(0);
                    return;
                }
                K(playbackStateCompat);
                this.h.setTransportControlFlags(w(playbackStateCompat.getActions()));
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public boolean isActive() {
            return this.m;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public String j() {
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void k(PendingIntent pendingIntent) {
            synchronized (this.i) {
                this.u = pendingIntent;
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void l(int i) {
            VolumeProviderCompat volumeProviderCompat = this.E;
            if (volumeProviderCompat != null) {
                volumeProviderCompat.setCallback(null);
            }
            this.D = i;
            this.C = 1;
            int i2 = this.C;
            int i3 = this.D;
            J(new ParcelableVolumeInfo(i2, i3, 2, this.g.getStreamMaxVolume(i3), this.g.getStreamVolume(this.D)));
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void m(PendingIntent pendingIntent) {
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public Object n() {
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void o(boolean z) {
            if (z == this.m) {
                return;
            }
            this.m = z;
            if (N()) {
                f(this.s);
                i(this.t);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void p(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            synchronized (this.i) {
                this.q = remoteUserInfo;
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public Object q() {
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void r(VolumeProviderCompat volumeProviderCompat) {
            if (volumeProviderCompat != null) {
                VolumeProviderCompat volumeProviderCompat2 = this.E;
                if (volumeProviderCompat2 != null) {
                    volumeProviderCompat2.setCallback(null);
                }
                this.C = 2;
                this.E = volumeProviderCompat;
                J(new ParcelableVolumeInfo(this.C, this.D, this.E.getVolumeControl(), this.E.getMaxVolume(), this.E.getCurrentVolume()));
                volumeProviderCompat.setCallback(this.F);
                return;
            }
            throw new IllegalArgumentException("volumeProvider may not be null");
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void release() {
            this.m = false;
            this.l = true;
            N();
            G();
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public MediaSessionManager.RemoteUserInfo s() {
            MediaSessionManager.RemoteUserInfo remoteUserInfo;
            synchronized (this.i) {
                remoteUserInfo = this.q;
            }
            return remoteUserInfo;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void setCaptioningEnabled(boolean z) {
            if (this.y != z) {
                this.y = z;
                z(z);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void setExtras(Bundle bundle) {
            this.B = bundle;
            B(bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void setRepeatMode(int i) {
            if (this.z != i) {
                this.z = i;
                F(i);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void setShuffleMode(int i) {
            if (this.A != i) {
                this.A = i;
                H(i);
            }
        }

        public void t(int i, int i2) {
            if (this.C == 2) {
                VolumeProviderCompat volumeProviderCompat = this.E;
                if (volumeProviderCompat != null) {
                    volumeProviderCompat.onAdjustVolume(i);
                    return;
                }
                return;
            }
            this.g.adjustStreamVolume(this.D, i, i2);
        }

        public RemoteControlClient.MetadataEditor u(Bundle bundle) {
            RemoteControlClient.MetadataEditor editMetadata = this.h.editMetadata(true);
            if (bundle == null) {
                return editMetadata;
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_ART)) {
                Bitmap bitmap = (Bitmap) bundle.getParcelable(MediaMetadataCompat.METADATA_KEY_ART);
                if (bitmap != null) {
                    bitmap = bitmap.copy(bitmap.getConfig(), false);
                }
                editMetadata.putBitmap(100, bitmap);
            } else if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_ALBUM_ART)) {
                Bitmap bitmap2 = (Bitmap) bundle.getParcelable(MediaMetadataCompat.METADATA_KEY_ALBUM_ART);
                if (bitmap2 != null) {
                    bitmap2 = bitmap2.copy(bitmap2.getConfig(), false);
                }
                editMetadata.putBitmap(100, bitmap2);
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_ALBUM)) {
                editMetadata.putString(1, bundle.getString(MediaMetadataCompat.METADATA_KEY_ALBUM));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST)) {
                editMetadata.putString(13, bundle.getString(MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_ARTIST)) {
                editMetadata.putString(2, bundle.getString(MediaMetadataCompat.METADATA_KEY_ARTIST));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_AUTHOR)) {
                editMetadata.putString(3, bundle.getString(MediaMetadataCompat.METADATA_KEY_AUTHOR));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_COMPILATION)) {
                editMetadata.putString(15, bundle.getString(MediaMetadataCompat.METADATA_KEY_COMPILATION));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_COMPOSER)) {
                editMetadata.putString(4, bundle.getString(MediaMetadataCompat.METADATA_KEY_COMPOSER));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_DATE)) {
                editMetadata.putString(5, bundle.getString(MediaMetadataCompat.METADATA_KEY_DATE));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_DISC_NUMBER)) {
                editMetadata.putLong(14, bundle.getLong(MediaMetadataCompat.METADATA_KEY_DISC_NUMBER));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_DURATION)) {
                editMetadata.putLong(9, bundle.getLong(MediaMetadataCompat.METADATA_KEY_DURATION));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_GENRE)) {
                editMetadata.putString(6, bundle.getString(MediaMetadataCompat.METADATA_KEY_GENRE));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_TITLE)) {
                editMetadata.putString(7, bundle.getString(MediaMetadataCompat.METADATA_KEY_TITLE));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER)) {
                editMetadata.putLong(0, bundle.getLong(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER));
            }
            if (bundle.containsKey(MediaMetadataCompat.METADATA_KEY_WRITER)) {
                editMetadata.putString(11, bundle.getString(MediaMetadataCompat.METADATA_KEY_WRITER));
            }
            return editMetadata;
        }

        public int v(int i) {
            switch (i) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 4;
                case 5:
                    return 5;
                case 6:
                case 8:
                    return 8;
                case 7:
                    return 9;
                case 9:
                    return 7;
                case 10:
                case 11:
                    return 6;
                default:
                    return -1;
            }
        }

        public int w(long j) {
            int i = (1 & j) != 0 ? 32 : 0;
            if ((2 & j) != 0) {
                i |= 16;
            }
            if ((4 & j) != 0) {
                i |= 4;
            }
            if ((8 & j) != 0) {
                i |= 2;
            }
            if ((16 & j) != 0) {
                i |= 1;
            }
            if ((32 & j) != 0) {
                i |= 128;
            }
            if ((64 & j) != 0) {
                i |= 64;
            }
            return (j & 512) != 0 ? i | 8 : i;
        }

        public void x(int i, int i2, int i3, Object obj, Bundle bundle) {
            synchronized (this.i) {
                d dVar = this.k;
                if (dVar != null) {
                    Message obtainMessage = dVar.obtainMessage(i, i2, i3, obj);
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("data_calling_pkg", MediaSessionManager.RemoteUserInfo.LEGACY_CONTROLLER);
                    bundle2.putInt(MediaBrowserProtocol.DATA_CALLING_PID, Binder.getCallingPid());
                    bundle2.putInt(MediaBrowserProtocol.DATA_CALLING_UID, Binder.getCallingUid());
                    if (bundle != null) {
                        bundle2.putBundle("data_extras", bundle);
                    }
                    obtainMessage.setData(bundle2);
                    obtainMessage.sendToTarget();
                }
            }
        }

        public void y(PendingIntent pendingIntent, ComponentName componentName) {
            this.g.registerMediaButtonEventReceiver(componentName);
        }

        public final void z(boolean z) {
            for (int beginBroadcast = this.j.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.j.getBroadcastItem(beginBroadcast).onCaptioningEnabledChanged(z);
                } catch (RemoteException unused) {
                }
            }
            this.j.finishBroadcast();
        }
    }

    public MediaSessionCompat(Context context, String str) {
        this(context, str, null, null);
    }

    public static PlaybackStateCompat a(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat) {
        if (playbackStateCompat != null) {
            long j = -1;
            if (playbackStateCompat.getPosition() == -1) {
                return playbackStateCompat;
            }
            if (playbackStateCompat.getState() == 3 || playbackStateCompat.getState() == 4 || playbackStateCompat.getState() == 5) {
                long lastPositionUpdateTime = playbackStateCompat.getLastPositionUpdateTime();
                if (lastPositionUpdateTime > 0) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    long playbackSpeed = (playbackStateCompat.getPlaybackSpeed() * ((float) (elapsedRealtime - lastPositionUpdateTime))) + playbackStateCompat.getPosition();
                    if (mediaMetadataCompat != null && mediaMetadataCompat.containsKey(MediaMetadataCompat.METADATA_KEY_DURATION)) {
                        j = mediaMetadataCompat.getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
                    }
                    return new PlaybackStateCompat.Builder(playbackStateCompat).setState(playbackStateCompat.getState(), (j < 0 || playbackSpeed <= j) ? playbackSpeed < 0 ? 0L : playbackSpeed : j, playbackStateCompat.getPlaybackSpeed(), elapsedRealtime).build();
                }
                return playbackStateCompat;
            }
            return playbackStateCompat;
        }
        return playbackStateCompat;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void ensureClassLoader(@Nullable Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MediaSessionCompat.class.getClassLoader());
        }
    }

    public static MediaSessionCompat fromMediaSession(Context context, Object obj) {
        if (context == null || obj == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        return new MediaSessionCompat(context, new g(obj));
    }

    public void addOnActiveChangeListener(OnActiveChangeListener onActiveChangeListener) {
        if (onActiveChangeListener != null) {
            this.c.add(onActiveChangeListener);
            return;
        }
        throw new IllegalArgumentException("Listener may not be null");
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public String getCallingPackage() {
        return this.f324a.j();
    }

    public MediaControllerCompat getController() {
        return this.b;
    }

    @NonNull
    public final MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
        return this.f324a.s();
    }

    public Object getMediaSession() {
        return this.f324a.q();
    }

    public Object getRemoteControlClient() {
        return this.f324a.n();
    }

    public Token getSessionToken() {
        return this.f324a.b();
    }

    public boolean isActive() {
        return this.f324a.isActive();
    }

    public void release() {
        this.f324a.release();
    }

    public void removeOnActiveChangeListener(OnActiveChangeListener onActiveChangeListener) {
        if (onActiveChangeListener != null) {
            this.c.remove(onActiveChangeListener);
            return;
        }
        throw new IllegalArgumentException("Listener may not be null");
    }

    public void sendSessionEvent(String str, Bundle bundle) {
        if (!TextUtils.isEmpty(str)) {
            this.f324a.c(str, bundle);
            return;
        }
        throw new IllegalArgumentException("event cannot be null or empty");
    }

    public void setActive(boolean z) {
        this.f324a.o(z);
        Iterator<OnActiveChangeListener> it = this.c.iterator();
        while (it.hasNext()) {
            it.next().onActiveChanged();
        }
    }

    public void setCallback(Callback callback) {
        setCallback(callback, null);
    }

    public void setCaptioningEnabled(boolean z) {
        this.f324a.setCaptioningEnabled(z);
    }

    public void setExtras(Bundle bundle) {
        this.f324a.setExtras(bundle);
    }

    public void setFlags(int i2) {
        this.f324a.a(i2);
    }

    public void setMediaButtonReceiver(PendingIntent pendingIntent) {
        this.f324a.m(pendingIntent);
    }

    public void setMetadata(MediaMetadataCompat mediaMetadataCompat) {
        this.f324a.f(mediaMetadataCompat);
    }

    public void setPlaybackState(PlaybackStateCompat playbackStateCompat) {
        this.f324a.i(playbackStateCompat);
    }

    public void setPlaybackToLocal(int i2) {
        this.f324a.l(i2);
    }

    public void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat) {
        if (volumeProviderCompat != null) {
            this.f324a.r(volumeProviderCompat);
            return;
        }
        throw new IllegalArgumentException("volumeProvider may not be null!");
    }

    public void setQueue(List<QueueItem> list) {
        this.f324a.h(list);
    }

    public void setQueueTitle(CharSequence charSequence) {
        this.f324a.e(charSequence);
    }

    public void setRatingType(int i2) {
        this.f324a.g(i2);
    }

    public void setRepeatMode(int i2) {
        this.f324a.setRepeatMode(i2);
    }

    public void setSessionActivity(PendingIntent pendingIntent) {
        this.f324a.k(pendingIntent);
    }

    public void setShuffleMode(int i2) {
        this.f324a.setShuffleMode(i2);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public static final class ResultReceiverWrapper implements Parcelable {
        public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR = new a();
        public ResultReceiver h;

        /* loaded from: classes.dex */
        public static class a implements Parcelable.Creator<ResultReceiverWrapper> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public ResultReceiverWrapper createFromParcel(Parcel parcel) {
                return new ResultReceiverWrapper(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public ResultReceiverWrapper[] newArray(int i) {
                return new ResultReceiverWrapper[i];
            }
        }

        public ResultReceiverWrapper(ResultReceiver resultReceiver) {
            this.h = resultReceiver;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            this.h.writeToParcel(parcel, i);
        }

        public ResultReceiverWrapper(Parcel parcel) {
            this.h = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        }
    }

    public MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
        this(context, str, componentName, pendingIntent, null);
    }

    public void setCallback(Callback callback, Handler handler) {
        if (callback == null) {
            this.f324a.d(null, null);
            return;
        }
        d dVar = this.f324a;
        if (handler == null) {
            handler = new Handler();
        }
        dVar.d(callback, handler);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public MediaSessionCompat(Context context, String str, Bundle bundle) {
        this(context, str, null, null, bundle);
    }

    public MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent, Bundle bundle) {
        this.c = new ArrayList<>();
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                if (componentName == null && (componentName = MediaButtonReceiver.getMediaButtonReceiverComponent(context)) == null) {
                    Log.w("MediaSessionCompat", "Couldn't find a unique registered media button receiver in the given context.");
                }
                if (componentName != null && pendingIntent == null) {
                    Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                    intent.setComponent(componentName);
                    pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                }
                int i2 = Build.VERSION.SDK_INT;
                if (i2 >= 28) {
                    h hVar = new h(context, str, bundle);
                    this.f324a = hVar;
                    setCallback(new a(this));
                    hVar.m(pendingIntent);
                } else if (i2 >= 21) {
                    g gVar = new g(context, str, bundle);
                    this.f324a = gVar;
                    setCallback(new b(this));
                    gVar.m(pendingIntent);
                } else if (i2 >= 19) {
                    this.f324a = new f(context, str, componentName, pendingIntent);
                } else if (i2 >= 18) {
                    this.f324a = new e(context, str, componentName, pendingIntent);
                } else {
                    this.f324a = new i(context, str, componentName, pendingIntent);
                }
                this.b = new MediaControllerCompat(context, this);
                if (d == 0) {
                    d = (int) (TypedValue.applyDimension(1, 320.0f, context.getResources().getDisplayMetrics()) + 0.5f);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("tag must not be null or empty");
        }
        throw new IllegalArgumentException("context must not be null");
    }

    @RequiresApi(21)
    /* loaded from: classes.dex */
    public static class g implements d {

        /* renamed from: a  reason: collision with root package name */
        public final Object f330a;
        public final Token b;
        public boolean c = false;
        public final RemoteCallbackList<IMediaControllerCallback> d = new RemoteCallbackList<>();
        public PlaybackStateCompat e;
        public List<QueueItem> f;
        public MediaMetadataCompat g;
        public int h;
        public boolean i;
        public int j;
        public int k;

        /* loaded from: classes.dex */
        public class a extends IMediaSession.Stub {
            public a() {
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void addQueueItemAt(MediaDescriptionCompat mediaDescriptionCompat, int i) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void adjustVolume(int i, int i2, String str) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void fastForward() throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public Bundle getExtras() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public long getFlags() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public PendingIntent getLaunchPendingIntent() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public MediaMetadataCompat getMetadata() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public String getPackageName() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public PlaybackStateCompat getPlaybackState() {
                g gVar = g.this;
                return MediaSessionCompat.a(gVar.e, gVar.g);
            }

            @Override // android.support.v4.media.session.IMediaSession
            public List<QueueItem> getQueue() {
                return null;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public CharSequence getQueueTitle() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getRatingType() {
                return g.this.h;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getRepeatMode() {
                return g.this.j;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public int getShuffleMode() {
                return g.this.k;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public String getTag() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public ParcelableVolumeInfo getVolumeAttributes() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean isCaptioningEnabled() {
                return g.this.i;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean isShuffleModeEnabledRemoved() {
                return false;
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean isTransportControlEnabled() {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void next() throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void pause() throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void play() throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void playFromMediaId(String str, Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void playFromSearch(String str, Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void playFromUri(Uri uri, Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepare() throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepareFromMediaId(String str, Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepareFromSearch(String str, Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void prepareFromUri(Uri uri, Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void previous() throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void rate(RatingCompat ratingCompat) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void rateWithExtras(RatingCompat ratingCompat, Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                g gVar = g.this;
                if (gVar.c) {
                    return;
                }
                String j = gVar.j();
                if (j == null) {
                    j = MediaSessionManager.RemoteUserInfo.LEGACY_CONTROLLER;
                }
                g.this.d.register(iMediaControllerCallback, new MediaSessionManager.RemoteUserInfo(j, Binder.getCallingPid(), Binder.getCallingUid()));
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void removeQueueItemAt(int i) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void rewind() throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void seekTo(long j) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void sendCommand(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void sendCustomAction(String str, Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public boolean sendMediaButton(KeyEvent keyEvent) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setCaptioningEnabled(boolean z) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setRepeatMode(int i) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setShuffleMode(int i) throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setShuffleModeEnabledRemoved(boolean z) throws RemoteException {
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void setVolumeTo(int i, int i2, String str) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void skipToQueueItem(long j) {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void stop() throws RemoteException {
                throw new AssertionError();
            }

            @Override // android.support.v4.media.session.IMediaSession
            public void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                g.this.d.unregister(iMediaControllerCallback);
            }
        }

        public g(Context context, String str, Bundle bundle) {
            Object b = android.support.v4.media.session.a.b(context, str);
            this.f330a = b;
            this.b = new Token(android.support.v4.media.session.a.c(b), new a(), bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void a(int i) {
            android.support.v4.media.session.a.k(this.f330a, i);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public Token b() {
            return this.b;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void c(String str, Bundle bundle) {
            if (Build.VERSION.SDK_INT < 23) {
                for (int beginBroadcast = this.d.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                    try {
                        this.d.getBroadcastItem(beginBroadcast).onEvent(str, bundle);
                    } catch (RemoteException unused) {
                    }
                }
                this.d.finishBroadcast();
            }
            android.support.v4.media.session.a.g(this.f330a, str, bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void d(Callback callback, Handler handler) {
            android.support.v4.media.session.a.i(this.f330a, callback == null ? null : callback.f325a, handler);
            if (callback != null) {
                callback.b(this, handler);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void e(CharSequence charSequence) {
            android.support.v4.media.session.a.r(this.f330a, charSequence);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void f(MediaMetadataCompat mediaMetadataCompat) {
            this.g = mediaMetadataCompat;
            android.support.v4.media.session.a.m(this.f330a, mediaMetadataCompat == null ? null : mediaMetadataCompat.getMediaMetadata());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void g(int i) {
            if (Build.VERSION.SDK_INT < 22) {
                this.h = i;
            } else {
                android.support.v4.media.session.b.a(this.f330a, i);
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public PlaybackStateCompat getPlaybackState() {
            return this.e;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void h(List<QueueItem> list) {
            ArrayList arrayList;
            this.f = list;
            if (list != null) {
                arrayList = new ArrayList();
                for (QueueItem queueItem : list) {
                    arrayList.add(queueItem.getQueueItem());
                }
            } else {
                arrayList = null;
            }
            android.support.v4.media.session.a.q(this.f330a, arrayList);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void i(PlaybackStateCompat playbackStateCompat) {
            this.e = playbackStateCompat;
            for (int beginBroadcast = this.d.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                try {
                    this.d.getBroadcastItem(beginBroadcast).onPlaybackStateChanged(playbackStateCompat);
                } catch (RemoteException unused) {
                }
            }
            this.d.finishBroadcast();
            android.support.v4.media.session.a.n(this.f330a, playbackStateCompat == null ? null : playbackStateCompat.getPlaybackState());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public boolean isActive() {
            return android.support.v4.media.session.a.e(this.f330a);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public String j() {
            if (Build.VERSION.SDK_INT < 24) {
                return null;
            }
            return MediaSessionCompatApi24.b(this.f330a);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void k(PendingIntent pendingIntent) {
            android.support.v4.media.session.a.s(this.f330a, pendingIntent);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void l(int i) {
            android.support.v4.media.session.a.o(this.f330a, i);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void m(PendingIntent pendingIntent) {
            android.support.v4.media.session.a.l(this.f330a, pendingIntent);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public Object n() {
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void o(boolean z) {
            android.support.v4.media.session.a.h(this.f330a, z);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void p(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public Object q() {
            return this.f330a;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void r(VolumeProviderCompat volumeProviderCompat) {
            android.support.v4.media.session.a.p(this.f330a, volumeProviderCompat.getVolumeProvider());
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void release() {
            this.c = true;
            android.support.v4.media.session.a.f(this.f330a);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public MediaSessionManager.RemoteUserInfo s() {
            return null;
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void setCaptioningEnabled(boolean z) {
            if (this.i != z) {
                this.i = z;
                for (int beginBroadcast = this.d.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                    try {
                        this.d.getBroadcastItem(beginBroadcast).onCaptioningEnabledChanged(z);
                    } catch (RemoteException unused) {
                    }
                }
                this.d.finishBroadcast();
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void setExtras(Bundle bundle) {
            android.support.v4.media.session.a.j(this.f330a, bundle);
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void setRepeatMode(int i) {
            if (this.j != i) {
                this.j = i;
                for (int beginBroadcast = this.d.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                    try {
                        this.d.getBroadcastItem(beginBroadcast).onRepeatModeChanged(i);
                    } catch (RemoteException unused) {
                    }
                }
                this.d.finishBroadcast();
            }
        }

        @Override // android.support.v4.media.session.MediaSessionCompat.d
        public void setShuffleMode(int i) {
            if (this.k != i) {
                this.k = i;
                for (int beginBroadcast = this.d.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
                    try {
                        this.d.getBroadcastItem(beginBroadcast).onShuffleModeChanged(i);
                    } catch (RemoteException unused) {
                    }
                }
                this.d.finishBroadcast();
            }
        }

        public g(Object obj) {
            Object t = android.support.v4.media.session.a.t(obj);
            this.f330a = t;
            this.b = new Token(android.support.v4.media.session.a.c(t), new a());
        }
    }

    public MediaSessionCompat(Context context, d dVar) {
        this.c = new ArrayList<>();
        this.f324a = dVar;
        if (Build.VERSION.SDK_INT >= 21 && !android.support.v4.media.session.a.d(dVar.q())) {
            setCallback(new c(this));
        }
        this.b = new MediaControllerCompat(context, this);
    }
}
