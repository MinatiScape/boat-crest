package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.media.a;
import android.support.v4.media.b;
import android.support.v4.media.c;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.ArrayMap;
import androidx.core.app.BundleCompat;
import androidx.media.MediaBrowserCompatUtils;
import androidx.media.MediaBrowserProtocol;
import androidx.media.MediaBrowserServiceCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class MediaBrowserCompat {
    public static final String CUSTOM_ACTION_DOWNLOAD = "android.support.v4.media.action.DOWNLOAD";
    public static final String CUSTOM_ACTION_REMOVE_DOWNLOADED_FILE = "android.support.v4.media.action.REMOVE_DOWNLOADED_FILE";
    public static final String EXTRA_DOWNLOAD_PROGRESS = "android.media.browse.extra.DOWNLOAD_PROGRESS";
    public static final String EXTRA_MEDIA_ID = "android.media.browse.extra.MEDIA_ID";
    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
    public static final boolean b = Log.isLoggable("MediaBrowserCompat", 3);

    /* renamed from: a  reason: collision with root package name */
    public final b f294a;

    /* loaded from: classes.dex */
    public static class ConnectionCallback {

        /* renamed from: a  reason: collision with root package name */
        public final Object f295a;
        public a b;

        /* loaded from: classes.dex */
        public interface a {
            void a();

            void c();

            void onConnected();
        }

        /* loaded from: classes.dex */
        public class b implements a.InterfaceC0102a {
            public b() {
            }

            @Override // android.support.v4.media.a.InterfaceC0102a
            public void a() {
                a aVar = ConnectionCallback.this.b;
                if (aVar != null) {
                    aVar.a();
                }
                ConnectionCallback.this.onConnectionFailed();
            }

            @Override // android.support.v4.media.a.InterfaceC0102a
            public void c() {
                a aVar = ConnectionCallback.this.b;
                if (aVar != null) {
                    aVar.c();
                }
                ConnectionCallback.this.onConnectionSuspended();
            }

            @Override // android.support.v4.media.a.InterfaceC0102a
            public void onConnected() {
                a aVar = ConnectionCallback.this.b;
                if (aVar != null) {
                    aVar.onConnected();
                }
                ConnectionCallback.this.onConnected();
            }
        }

        public ConnectionCallback() {
            if (Build.VERSION.SDK_INT >= 21) {
                this.f295a = android.support.v4.media.a.c(new b());
            } else {
                this.f295a = null;
            }
        }

        public void a(a aVar) {
            this.b = aVar;
        }

        public void onConnected() {
        }

        public void onConnectionFailed() {
        }

        public void onConnectionSuspended() {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class CustomActionCallback {
        public void onError(String str, Bundle bundle, Bundle bundle2) {
        }

        public void onProgressUpdate(String str, Bundle bundle, Bundle bundle2) {
        }

        public void onResult(String str, Bundle bundle, Bundle bundle2) {
        }
    }

    /* loaded from: classes.dex */
    public static class CustomActionResultReceiver extends ResultReceiver {
        public final String k;
        public final Bundle l;
        public final CustomActionCallback m;

        public CustomActionResultReceiver(String str, Bundle bundle, CustomActionCallback customActionCallback, Handler handler) {
            super(handler);
            this.k = str;
            this.l = bundle;
            this.m = customActionCallback;
        }

        @Override // android.support.v4.os.ResultReceiver
        public void onReceiveResult(int i, Bundle bundle) {
            if (this.m == null) {
                return;
            }
            MediaSessionCompat.ensureClassLoader(bundle);
            if (i == -1) {
                this.m.onError(this.k, this.l, bundle);
            } else if (i == 0) {
                this.m.onResult(this.k, this.l, bundle);
            } else if (i != 1) {
                Log.w("MediaBrowserCompat", "Unknown result code: " + i + " (extras=" + this.l + ", resultData=" + bundle + ")");
            } else {
                this.m.onProgressUpdate(this.k, this.l, bundle);
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ItemCallback {

        /* renamed from: a  reason: collision with root package name */
        public final Object f297a;

        /* loaded from: classes.dex */
        public class a implements b.a {
            public a() {
            }

            @Override // android.support.v4.media.b.a
            public void a(Parcel parcel) {
                if (parcel == null) {
                    ItemCallback.this.onItemLoaded(null);
                    return;
                }
                parcel.setDataPosition(0);
                parcel.recycle();
                ItemCallback.this.onItemLoaded(MediaItem.CREATOR.createFromParcel(parcel));
            }

            @Override // android.support.v4.media.b.a
            public void onError(@NonNull String str) {
                ItemCallback.this.onError(str);
            }
        }

        public ItemCallback() {
            if (Build.VERSION.SDK_INT >= 23) {
                this.f297a = android.support.v4.media.b.a(new a());
            } else {
                this.f297a = null;
            }
        }

        public void onError(@NonNull String str) {
        }

        public void onItemLoaded(MediaItem mediaItem) {
        }
    }

    /* loaded from: classes.dex */
    public static class ItemReceiver extends ResultReceiver {
        public final String k;
        public final ItemCallback l;

        public ItemReceiver(String str, ItemCallback itemCallback, Handler handler) {
            super(handler);
            this.k = str;
            this.l = itemCallback;
        }

        @Override // android.support.v4.os.ResultReceiver
        public void onReceiveResult(int i, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            if (i == 0 && bundle != null && bundle.containsKey(MediaBrowserServiceCompat.KEY_MEDIA_ITEM)) {
                Parcelable parcelable = bundle.getParcelable(MediaBrowserServiceCompat.KEY_MEDIA_ITEM);
                if (parcelable != null && !(parcelable instanceof MediaItem)) {
                    this.l.onError(this.k);
                    return;
                } else {
                    this.l.onItemLoaded((MediaItem) parcelable);
                    return;
                }
            }
            this.l.onError(this.k);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class SearchCallback {
        public void onError(@NonNull String str, Bundle bundle) {
        }

        public void onSearchResult(@NonNull String str, Bundle bundle, @NonNull List<MediaItem> list) {
        }
    }

    /* loaded from: classes.dex */
    public static class SearchResultReceiver extends ResultReceiver {
        public final String k;
        public final Bundle l;
        public final SearchCallback m;

        public SearchResultReceiver(String str, Bundle bundle, SearchCallback searchCallback, Handler handler) {
            super(handler);
            this.k = str;
            this.l = bundle;
            this.m = searchCallback;
        }

        @Override // android.support.v4.os.ResultReceiver
        public void onReceiveResult(int i, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            if (i == 0 && bundle != null && bundle.containsKey(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS)) {
                Parcelable[] parcelableArray = bundle.getParcelableArray(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS);
                ArrayList arrayList = null;
                if (parcelableArray != null) {
                    arrayList = new ArrayList();
                    for (Parcelable parcelable : parcelableArray) {
                        arrayList.add((MediaItem) parcelable);
                    }
                }
                this.m.onSearchResult(this.k, this.l, arrayList);
                return;
            }
            this.m.onError(this.k, this.l);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class SubscriptionCallback {

        /* renamed from: a  reason: collision with root package name */
        public final Object f299a;
        public final IBinder b = new Binder();
        public WeakReference<i> c;

        /* loaded from: classes.dex */
        public class a implements a.d {
            public a() {
            }

            @Override // android.support.v4.media.a.d
            public void c(@NonNull String str, List<?> list) {
                WeakReference<i> weakReference = SubscriptionCallback.this.c;
                i iVar = weakReference == null ? null : weakReference.get();
                if (iVar == null) {
                    SubscriptionCallback.this.onChildrenLoaded(str, MediaItem.fromMediaItemList(list));
                    return;
                }
                List<MediaItem> fromMediaItemList = MediaItem.fromMediaItemList(list);
                List<SubscriptionCallback> b = iVar.b();
                List<Bundle> c = iVar.c();
                for (int i = 0; i < b.size(); i++) {
                    Bundle bundle = c.get(i);
                    if (bundle == null) {
                        SubscriptionCallback.this.onChildrenLoaded(str, fromMediaItemList);
                    } else {
                        SubscriptionCallback.this.onChildrenLoaded(str, d(fromMediaItemList, bundle), bundle);
                    }
                }
            }

            public List<MediaItem> d(List<MediaItem> list, Bundle bundle) {
                if (list == null) {
                    return null;
                }
                int i = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
                int i2 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
                if (i == -1 && i2 == -1) {
                    return list;
                }
                int i3 = i2 * i;
                int i4 = i3 + i2;
                if (i >= 0 && i2 >= 1 && i3 < list.size()) {
                    if (i4 > list.size()) {
                        i4 = list.size();
                    }
                    return list.subList(i3, i4);
                }
                return Collections.emptyList();
            }

            @Override // android.support.v4.media.a.d
            public void onError(@NonNull String str) {
                SubscriptionCallback.this.onError(str);
            }
        }

        /* loaded from: classes.dex */
        public class b extends a implements c.a {
            public b() {
                super();
            }

            @Override // android.support.v4.media.c.a
            public void a(@NonNull String str, @NonNull Bundle bundle) {
                SubscriptionCallback.this.onError(str, bundle);
            }

            @Override // android.support.v4.media.c.a
            public void b(@NonNull String str, List<?> list, @NonNull Bundle bundle) {
                SubscriptionCallback.this.onChildrenLoaded(str, MediaItem.fromMediaItemList(list), bundle);
            }
        }

        public SubscriptionCallback() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 26) {
                this.f299a = android.support.v4.media.c.a(new b());
            } else if (i >= 21) {
                this.f299a = android.support.v4.media.a.d(new a());
            } else {
                this.f299a = null;
            }
        }

        public void a(i iVar) {
            this.c = new WeakReference<>(iVar);
        }

        public void onChildrenLoaded(@NonNull String str, @NonNull List<MediaItem> list) {
        }

        public void onChildrenLoaded(@NonNull String str, @NonNull List<MediaItem> list, @NonNull Bundle bundle) {
        }

        public void onError(@NonNull String str) {
        }

        public void onError(@NonNull String str, @NonNull Bundle bundle) {
        }
    }

    /* loaded from: classes.dex */
    public static class a extends Handler {

        /* renamed from: a  reason: collision with root package name */
        public final WeakReference<g> f301a;
        public WeakReference<Messenger> b;

        public a(g gVar) {
            this.f301a = new WeakReference<>(gVar);
        }

        public void a(Messenger messenger) {
            this.b = new WeakReference<>(messenger);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WeakReference<Messenger> weakReference = this.b;
            if (weakReference == null || weakReference.get() == null || this.f301a.get() == null) {
                return;
            }
            Bundle data = message.getData();
            MediaSessionCompat.ensureClassLoader(data);
            g gVar = this.f301a.get();
            Messenger messenger = this.b.get();
            try {
                int i = message.what;
                if (i == 1) {
                    Bundle bundle = data.getBundle(MediaBrowserProtocol.DATA_ROOT_HINTS);
                    MediaSessionCompat.ensureClassLoader(bundle);
                    gVar.g(messenger, data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), (MediaSessionCompat.Token) data.getParcelable(MediaBrowserProtocol.DATA_MEDIA_SESSION_TOKEN), bundle);
                } else if (i == 2) {
                    gVar.k(messenger);
                } else if (i != 3) {
                    Log.w("MediaBrowserCompat", "Unhandled message: " + message + "\n  Client version: 1\n  Service version: " + message.arg1);
                } else {
                    Bundle bundle2 = data.getBundle(MediaBrowserProtocol.DATA_OPTIONS);
                    MediaSessionCompat.ensureClassLoader(bundle2);
                    Bundle bundle3 = data.getBundle(MediaBrowserProtocol.DATA_NOTIFY_CHILDREN_CHANGED_OPTIONS);
                    MediaSessionCompat.ensureClassLoader(bundle3);
                    gVar.e(messenger, data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), data.getParcelableArrayList(MediaBrowserProtocol.DATA_MEDIA_ITEM_LIST), bundle2, bundle3);
                }
            } catch (BadParcelableException unused) {
                Log.e("MediaBrowserCompat", "Could not unparcel the data.");
                if (message.what == 1) {
                    gVar.k(messenger);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        @NonNull
        MediaSessionCompat.Token b();

        void connect();

        void d(@NonNull String str, Bundle bundle, @Nullable CustomActionCallback customActionCallback);

        void disconnect();

        void f(@NonNull String str, Bundle bundle, @NonNull SearchCallback searchCallback);

        @Nullable
        Bundle getExtras();

        @NonNull
        String getRoot();

        ComponentName h();

        void i(@NonNull String str, @NonNull ItemCallback itemCallback);

        boolean isConnected();

        void j(@NonNull String str, @Nullable Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback);

        void l(@NonNull String str, SubscriptionCallback subscriptionCallback);

        @Nullable
        Bundle m();
    }

    @RequiresApi(21)
    /* loaded from: classes.dex */
    public static class c implements b, g, ConnectionCallback.a {

        /* renamed from: a  reason: collision with root package name */
        public final Context f302a;
        public final Object b;
        public final Bundle c;
        public final a d = new a(this);
        public final ArrayMap<String, i> e = new ArrayMap<>();
        public int f;
        public h g;
        public Messenger h;
        public MediaSessionCompat.Token i;
        public Bundle j;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public final /* synthetic */ ItemCallback h;
            public final /* synthetic */ String i;

            public a(c cVar, ItemCallback itemCallback, String str) {
                this.h = itemCallback;
                this.i = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.onError(this.i);
            }
        }

        /* loaded from: classes.dex */
        public class b implements Runnable {
            public final /* synthetic */ ItemCallback h;
            public final /* synthetic */ String i;

            public b(c cVar, ItemCallback itemCallback, String str) {
                this.h = itemCallback;
                this.i = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.onError(this.i);
            }
        }

        /* renamed from: android.support.v4.media.MediaBrowserCompat$c$c  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC0100c implements Runnable {
            public final /* synthetic */ ItemCallback h;
            public final /* synthetic */ String i;

            public RunnableC0100c(c cVar, ItemCallback itemCallback, String str) {
                this.h = itemCallback;
                this.i = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.onError(this.i);
            }
        }

        /* loaded from: classes.dex */
        public class d implements Runnable {
            public final /* synthetic */ SearchCallback h;
            public final /* synthetic */ String i;
            public final /* synthetic */ Bundle j;

            public d(c cVar, SearchCallback searchCallback, String str, Bundle bundle) {
                this.h = searchCallback;
                this.i = str;
                this.j = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.onError(this.i, this.j);
            }
        }

        /* loaded from: classes.dex */
        public class e implements Runnable {
            public final /* synthetic */ SearchCallback h;
            public final /* synthetic */ String i;
            public final /* synthetic */ Bundle j;

            public e(c cVar, SearchCallback searchCallback, String str, Bundle bundle) {
                this.h = searchCallback;
                this.i = str;
                this.j = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.onError(this.i, this.j);
            }
        }

        /* loaded from: classes.dex */
        public class f implements Runnable {
            public final /* synthetic */ CustomActionCallback h;
            public final /* synthetic */ String i;
            public final /* synthetic */ Bundle j;

            public f(c cVar, CustomActionCallback customActionCallback, String str, Bundle bundle) {
                this.h = customActionCallback;
                this.i = str;
                this.j = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.onError(this.i, this.j, null);
            }
        }

        /* loaded from: classes.dex */
        public class g implements Runnable {
            public final /* synthetic */ CustomActionCallback h;
            public final /* synthetic */ String i;
            public final /* synthetic */ Bundle j;

            public g(c cVar, CustomActionCallback customActionCallback, String str, Bundle bundle) {
                this.h = customActionCallback;
                this.i = str;
                this.j = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.onError(this.i, this.j, null);
            }
        }

        public c(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            this.f302a = context;
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            this.c = bundle2;
            bundle2.putInt(MediaBrowserProtocol.EXTRA_CLIENT_VERSION, 1);
            connectionCallback.a(this);
            this.b = android.support.v4.media.a.b(context, componentName, connectionCallback.f295a, bundle2);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback.a
        public void a() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        @NonNull
        public MediaSessionCompat.Token b() {
            if (this.i == null) {
                this.i = MediaSessionCompat.Token.fromToken(android.support.v4.media.a.i(this.b));
            }
            return this.i;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback.a
        public void c() {
            this.g = null;
            this.h = null;
            this.i = null;
            this.d.a(null);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void connect() {
            android.support.v4.media.a.a(this.b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void d(@NonNull String str, Bundle bundle, @Nullable CustomActionCallback customActionCallback) {
            if (isConnected()) {
                if (this.g == null) {
                    Log.i("MediaBrowserCompat", "The connected service doesn't support sendCustomAction.");
                    if (customActionCallback != null) {
                        this.d.post(new f(this, customActionCallback, str, bundle));
                    }
                }
                try {
                    this.g.h(str, bundle, new CustomActionResultReceiver(str, bundle, customActionCallback, this.d), this.h);
                    return;
                } catch (RemoteException e2) {
                    Log.i("MediaBrowserCompat", "Remote error sending a custom action: action=" + str + ", extras=" + bundle, e2);
                    if (customActionCallback != null) {
                        this.d.post(new g(this, customActionCallback, str, bundle));
                        return;
                    }
                    return;
                }
            }
            throw new IllegalStateException("Cannot send a custom action (" + str + ") with extras " + bundle + " because the browser is not connected to the service.");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void disconnect() {
            Messenger messenger;
            h hVar = this.g;
            if (hVar != null && (messenger = this.h) != null) {
                try {
                    hVar.j(messenger);
                } catch (RemoteException unused) {
                    Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
                }
            }
            android.support.v4.media.a.e(this.b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.g
        public void e(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
            if (this.h != messenger) {
                return;
            }
            i iVar = this.e.get(str);
            if (iVar == null) {
                if (MediaBrowserCompat.b) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + str);
                    return;
                }
                return;
            }
            SubscriptionCallback a2 = iVar.a(bundle);
            if (a2 != null) {
                if (bundle == null) {
                    if (list == null) {
                        a2.onError(str);
                        return;
                    }
                    this.j = bundle2;
                    a2.onChildrenLoaded(str, list);
                    this.j = null;
                } else if (list == null) {
                    a2.onError(str, bundle);
                } else {
                    this.j = bundle2;
                    a2.onChildrenLoaded(str, list, bundle);
                    this.j = null;
                }
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void f(@NonNull String str, Bundle bundle, @NonNull SearchCallback searchCallback) {
            if (isConnected()) {
                if (this.g == null) {
                    Log.i("MediaBrowserCompat", "The connected service doesn't support search.");
                    this.d.post(new d(this, searchCallback, str, bundle));
                    return;
                }
                try {
                    this.g.g(str, bundle, new SearchResultReceiver(str, bundle, searchCallback, this.d), this.h);
                    return;
                } catch (RemoteException e2) {
                    Log.i("MediaBrowserCompat", "Remote error searching items with query: " + str, e2);
                    this.d.post(new e(this, searchCallback, str, bundle));
                    return;
                }
            }
            throw new IllegalStateException("search() called while not connected");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.g
        public void g(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        @Nullable
        public Bundle getExtras() {
            return android.support.v4.media.a.f(this.b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        @NonNull
        public String getRoot() {
            return android.support.v4.media.a.g(this.b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public ComponentName h() {
            return android.support.v4.media.a.h(this.b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void i(@NonNull String str, @NonNull ItemCallback itemCallback) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("mediaId is empty");
            }
            if (itemCallback != null) {
                if (!android.support.v4.media.a.j(this.b)) {
                    Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
                    this.d.post(new a(this, itemCallback, str));
                    return;
                } else if (this.g == null) {
                    this.d.post(new b(this, itemCallback, str));
                    return;
                } else {
                    try {
                        this.g.d(str, new ItemReceiver(str, itemCallback, this.d), this.h);
                        return;
                    } catch (RemoteException unused) {
                        Log.i("MediaBrowserCompat", "Remote error getting media item: " + str);
                        this.d.post(new RunnableC0100c(this, itemCallback, str));
                        return;
                    }
                }
            }
            throw new IllegalArgumentException("cb is null");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public boolean isConnected() {
            return android.support.v4.media.a.j(this.b);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void j(@NonNull String str, Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
            i iVar = this.e.get(str);
            if (iVar == null) {
                iVar = new i();
                this.e.put(str, iVar);
            }
            subscriptionCallback.a(iVar);
            Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
            iVar.e(bundle2, subscriptionCallback);
            h hVar = this.g;
            if (hVar == null) {
                android.support.v4.media.a.k(this.b, str, subscriptionCallback.f299a);
                return;
            }
            try {
                hVar.a(str, subscriptionCallback.b, bundle2, this.h);
            } catch (RemoteException unused) {
                Log.i("MediaBrowserCompat", "Remote error subscribing media item: " + str);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.g
        public void k(Messenger messenger) {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void l(@NonNull String str, SubscriptionCallback subscriptionCallback) {
            i iVar = this.e.get(str);
            if (iVar == null) {
                return;
            }
            h hVar = this.g;
            if (hVar != null) {
                try {
                    if (subscriptionCallback == null) {
                        hVar.f(str, null, this.h);
                    } else {
                        List<SubscriptionCallback> b2 = iVar.b();
                        List<Bundle> c = iVar.c();
                        for (int size = b2.size() - 1; size >= 0; size--) {
                            if (b2.get(size) == subscriptionCallback) {
                                this.g.f(str, subscriptionCallback.b, this.h);
                                b2.remove(size);
                                c.remove(size);
                            }
                        }
                    }
                } catch (RemoteException unused) {
                    Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + str);
                }
            } else if (subscriptionCallback == null) {
                android.support.v4.media.a.l(this.b, str);
            } else {
                List<SubscriptionCallback> b3 = iVar.b();
                List<Bundle> c2 = iVar.c();
                for (int size2 = b3.size() - 1; size2 >= 0; size2--) {
                    if (b3.get(size2) == subscriptionCallback) {
                        b3.remove(size2);
                        c2.remove(size2);
                    }
                }
                if (b3.size() == 0) {
                    android.support.v4.media.a.l(this.b, str);
                }
            }
            if (iVar.d() || subscriptionCallback == null) {
                this.e.remove(str);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public Bundle m() {
            return this.j;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback.a
        public void onConnected() {
            Bundle f2 = android.support.v4.media.a.f(this.b);
            if (f2 == null) {
                return;
            }
            this.f = f2.getInt(MediaBrowserProtocol.EXTRA_SERVICE_VERSION, 0);
            IBinder binder = BundleCompat.getBinder(f2, MediaBrowserProtocol.EXTRA_MESSENGER_BINDER);
            if (binder != null) {
                this.g = new h(binder, this.c);
                Messenger messenger = new Messenger(this.d);
                this.h = messenger;
                this.d.a(messenger);
                try {
                    this.g.e(this.f302a, this.h);
                } catch (RemoteException unused) {
                    Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                }
            }
            IMediaSession asInterface = IMediaSession.Stub.asInterface(BundleCompat.getBinder(f2, MediaBrowserProtocol.EXTRA_SESSION_BINDER));
            if (asInterface != null) {
                this.i = MediaSessionCompat.Token.fromToken(android.support.v4.media.a.i(this.b), asInterface);
            }
        }
    }

    @RequiresApi(23)
    /* loaded from: classes.dex */
    public static class d extends c {
        public d(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            super(context, componentName, connectionCallback, bundle);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c, android.support.v4.media.MediaBrowserCompat.b
        public void i(@NonNull String str, @NonNull ItemCallback itemCallback) {
            if (this.g == null) {
                android.support.v4.media.b.b(this.b, str, itemCallback.f297a);
            } else {
                super.i(str, itemCallback);
            }
        }
    }

    @RequiresApi(26)
    /* loaded from: classes.dex */
    public static class e extends d {
        public e(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            super(context, componentName, connectionCallback, bundle);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c, android.support.v4.media.MediaBrowserCompat.b
        public void j(@NonNull String str, @Nullable Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
            if (this.g != null && this.f >= 2) {
                super.j(str, bundle, subscriptionCallback);
            } else if (bundle == null) {
                android.support.v4.media.a.k(this.b, str, subscriptionCallback.f299a);
            } else {
                android.support.v4.media.c.b(this.b, str, bundle, subscriptionCallback.f299a);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.c, android.support.v4.media.MediaBrowserCompat.b
        public void l(@NonNull String str, SubscriptionCallback subscriptionCallback) {
            if (this.g != null && this.f >= 2) {
                super.l(str, subscriptionCallback);
            } else if (subscriptionCallback == null) {
                android.support.v4.media.a.l(this.b, str);
            } else {
                android.support.v4.media.c.c(this.b, str, subscriptionCallback.f299a);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class f implements b, g {

        /* renamed from: a  reason: collision with root package name */
        public final Context f303a;
        public final ComponentName b;
        public final ConnectionCallback c;
        public final Bundle d;
        public final a e = new a(this);
        public final ArrayMap<String, i> f = new ArrayMap<>();
        public int g = 1;
        public g h;
        public h i;
        public Messenger j;
        public String k;
        public MediaSessionCompat.Token l;
        public Bundle m;
        public Bundle n;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = f.this;
                if (fVar.g == 0) {
                    return;
                }
                fVar.g = 2;
                if (MediaBrowserCompat.b && fVar.h != null) {
                    throw new RuntimeException("mServiceConnection should be null. Instead it is " + f.this.h);
                } else if (fVar.i == null) {
                    if (fVar.j == null) {
                        Intent intent = new Intent(MediaBrowserServiceCompat.SERVICE_INTERFACE);
                        intent.setComponent(f.this.b);
                        f fVar2 = f.this;
                        fVar2.h = new g();
                        boolean z = false;
                        try {
                            f fVar3 = f.this;
                            z = fVar3.f303a.bindService(intent, fVar3.h, 1);
                        } catch (Exception unused) {
                            Log.e("MediaBrowserCompat", "Failed binding to service " + f.this.b);
                        }
                        if (!z) {
                            f.this.c();
                            f.this.c.onConnectionFailed();
                        }
                        if (MediaBrowserCompat.b) {
                            Log.d("MediaBrowserCompat", "connect...");
                            f.this.a();
                            return;
                        }
                        return;
                    }
                    throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + f.this.j);
                } else {
                    throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + f.this.i);
                }
            }
        }

        /* loaded from: classes.dex */
        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                f fVar = f.this;
                Messenger messenger = fVar.j;
                if (messenger != null) {
                    try {
                        fVar.i.c(messenger);
                    } catch (RemoteException unused) {
                        Log.w("MediaBrowserCompat", "RemoteException during connect for " + f.this.b);
                    }
                }
                f fVar2 = f.this;
                int i = fVar2.g;
                fVar2.c();
                if (i != 0) {
                    f.this.g = i;
                }
                if (MediaBrowserCompat.b) {
                    Log.d("MediaBrowserCompat", "disconnect...");
                    f.this.a();
                }
            }
        }

        /* loaded from: classes.dex */
        public class c implements Runnable {
            public final /* synthetic */ ItemCallback h;
            public final /* synthetic */ String i;

            public c(f fVar, ItemCallback itemCallback, String str) {
                this.h = itemCallback;
                this.i = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.onError(this.i);
            }
        }

        /* loaded from: classes.dex */
        public class d implements Runnable {
            public final /* synthetic */ ItemCallback h;
            public final /* synthetic */ String i;

            public d(f fVar, ItemCallback itemCallback, String str) {
                this.h = itemCallback;
                this.i = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.onError(this.i);
            }
        }

        /* loaded from: classes.dex */
        public class e implements Runnable {
            public final /* synthetic */ SearchCallback h;
            public final /* synthetic */ String i;
            public final /* synthetic */ Bundle j;

            public e(f fVar, SearchCallback searchCallback, String str, Bundle bundle) {
                this.h = searchCallback;
                this.i = str;
                this.j = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.onError(this.i, this.j);
            }
        }

        /* renamed from: android.support.v4.media.MediaBrowserCompat$f$f  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC0101f implements Runnable {
            public final /* synthetic */ CustomActionCallback h;
            public final /* synthetic */ String i;
            public final /* synthetic */ Bundle j;

            public RunnableC0101f(f fVar, CustomActionCallback customActionCallback, String str, Bundle bundle) {
                this.h = customActionCallback;
                this.i = str;
                this.j = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.onError(this.i, this.j, null);
            }
        }

        /* loaded from: classes.dex */
        public class g implements ServiceConnection {

            /* loaded from: classes.dex */
            public class a implements Runnable {
                public final /* synthetic */ ComponentName h;
                public final /* synthetic */ IBinder i;

                public a(ComponentName componentName, IBinder iBinder) {
                    this.h = componentName;
                    this.i = iBinder;
                }

                @Override // java.lang.Runnable
                public void run() {
                    boolean z = MediaBrowserCompat.b;
                    if (z) {
                        Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceConnected name=" + this.h + " binder=" + this.i);
                        f.this.a();
                    }
                    if (g.this.a("onServiceConnected")) {
                        f fVar = f.this;
                        fVar.i = new h(this.i, fVar.d);
                        f.this.j = new Messenger(f.this.e);
                        f fVar2 = f.this;
                        fVar2.e.a(fVar2.j);
                        f.this.g = 2;
                        if (z) {
                            try {
                                Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                f.this.a();
                            } catch (RemoteException unused) {
                                Log.w("MediaBrowserCompat", "RemoteException during connect for " + f.this.b);
                                if (MediaBrowserCompat.b) {
                                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                    f.this.a();
                                    return;
                                }
                                return;
                            }
                        }
                        f fVar3 = f.this;
                        fVar3.i.b(fVar3.f303a, fVar3.j);
                    }
                }
            }

            /* loaded from: classes.dex */
            public class b implements Runnable {
                public final /* synthetic */ ComponentName h;

                public b(ComponentName componentName) {
                    this.h = componentName;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (MediaBrowserCompat.b) {
                        Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceDisconnected name=" + this.h + " this=" + this + " mServiceConnection=" + f.this.h);
                        f.this.a();
                    }
                    if (g.this.a("onServiceDisconnected")) {
                        f fVar = f.this;
                        fVar.i = null;
                        fVar.j = null;
                        fVar.e.a(null);
                        f fVar2 = f.this;
                        fVar2.g = 4;
                        fVar2.c.onConnectionSuspended();
                    }
                }
            }

            public g() {
            }

            public boolean a(String str) {
                int i;
                f fVar = f.this;
                if (fVar.h != this || (i = fVar.g) == 0 || i == 1) {
                    int i2 = fVar.g;
                    if (i2 == 0 || i2 == 1) {
                        return false;
                    }
                    Log.i("MediaBrowserCompat", str + " for " + f.this.b + " with mServiceConnection=" + f.this.h + " this=" + this);
                    return false;
                }
                return true;
            }

            public final void b(Runnable runnable) {
                if (Thread.currentThread() == f.this.e.getLooper().getThread()) {
                    runnable.run();
                } else {
                    f.this.e.post(runnable);
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                b(new a(componentName, iBinder));
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                b(new b(componentName));
            }
        }

        public f(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            if (context == null) {
                throw new IllegalArgumentException("context must not be null");
            }
            if (componentName == null) {
                throw new IllegalArgumentException("service component must not be null");
            }
            if (connectionCallback != null) {
                this.f303a = context;
                this.b = componentName;
                this.c = connectionCallback;
                this.d = bundle == null ? null : new Bundle(bundle);
                return;
            }
            throw new IllegalArgumentException("connection callback must not be null");
        }

        public static String n(int i) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                return "UNKNOWN/" + i;
                            }
                            return "CONNECT_STATE_SUSPENDED";
                        }
                        return "CONNECT_STATE_CONNECTED";
                    }
                    return "CONNECT_STATE_CONNECTING";
                }
                return "CONNECT_STATE_DISCONNECTED";
            }
            return "CONNECT_STATE_DISCONNECTING";
        }

        public void a() {
            Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
            Log.d("MediaBrowserCompat", "  mServiceComponent=" + this.b);
            Log.d("MediaBrowserCompat", "  mCallback=" + this.c);
            Log.d("MediaBrowserCompat", "  mRootHints=" + this.d);
            Log.d("MediaBrowserCompat", "  mState=" + n(this.g));
            Log.d("MediaBrowserCompat", "  mServiceConnection=" + this.h);
            Log.d("MediaBrowserCompat", "  mServiceBinderWrapper=" + this.i);
            Log.d("MediaBrowserCompat", "  mCallbacksMessenger=" + this.j);
            Log.d("MediaBrowserCompat", "  mRootId=" + this.k);
            Log.d("MediaBrowserCompat", "  mMediaSessionToken=" + this.l);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        @NonNull
        public MediaSessionCompat.Token b() {
            if (isConnected()) {
                return this.l;
            }
            throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.g + ")");
        }

        public void c() {
            g gVar = this.h;
            if (gVar != null) {
                this.f303a.unbindService(gVar);
            }
            this.g = 1;
            this.h = null;
            this.i = null;
            this.j = null;
            this.e.a(null);
            this.k = null;
            this.l = null;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void connect() {
            int i = this.g;
            if (i != 0 && i != 1) {
                throw new IllegalStateException("connect() called while neigther disconnecting nor disconnected (state=" + n(this.g) + ")");
            }
            this.g = 2;
            this.e.post(new a());
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void d(@NonNull String str, Bundle bundle, @Nullable CustomActionCallback customActionCallback) {
            if (isConnected()) {
                try {
                    this.i.h(str, bundle, new CustomActionResultReceiver(str, bundle, customActionCallback, this.e), this.j);
                    return;
                } catch (RemoteException e2) {
                    Log.i("MediaBrowserCompat", "Remote error sending a custom action: action=" + str + ", extras=" + bundle, e2);
                    if (customActionCallback != null) {
                        this.e.post(new RunnableC0101f(this, customActionCallback, str, bundle));
                        return;
                    }
                    return;
                }
            }
            throw new IllegalStateException("Cannot send a custom action (" + str + ") with extras " + bundle + " because the browser is not connected to the service.");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void disconnect() {
            this.g = 0;
            this.e.post(new b());
        }

        @Override // android.support.v4.media.MediaBrowserCompat.g
        public void e(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2) {
            if (o(messenger, "onLoadChildren")) {
                boolean z = MediaBrowserCompat.b;
                if (z) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for " + this.b + " id=" + str);
                }
                i iVar = this.f.get(str);
                if (iVar == null) {
                    if (z) {
                        Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + str);
                        return;
                    }
                    return;
                }
                SubscriptionCallback a2 = iVar.a(bundle);
                if (a2 != null) {
                    if (bundle == null) {
                        if (list == null) {
                            a2.onError(str);
                            return;
                        }
                        this.n = bundle2;
                        a2.onChildrenLoaded(str, list);
                        this.n = null;
                    } else if (list == null) {
                        a2.onError(str, bundle);
                    } else {
                        this.n = bundle2;
                        a2.onChildrenLoaded(str, list, bundle);
                        this.n = null;
                    }
                }
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void f(@NonNull String str, Bundle bundle, @NonNull SearchCallback searchCallback) {
            if (isConnected()) {
                try {
                    this.i.g(str, bundle, new SearchResultReceiver(str, bundle, searchCallback, this.e), this.j);
                    return;
                } catch (RemoteException e2) {
                    Log.i("MediaBrowserCompat", "Remote error searching items with query: " + str, e2);
                    this.e.post(new e(this, searchCallback, str, bundle));
                    return;
                }
            }
            throw new IllegalStateException("search() called while not connected (state=" + n(this.g) + ")");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.g
        public void g(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle) {
            if (o(messenger, "onConnect")) {
                if (this.g != 2) {
                    Log.w("MediaBrowserCompat", "onConnect from service while mState=" + n(this.g) + "... ignoring");
                    return;
                }
                this.k = str;
                this.l = token;
                this.m = bundle;
                this.g = 3;
                if (MediaBrowserCompat.b) {
                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                    a();
                }
                this.c.onConnected();
                try {
                    for (Map.Entry<String, i> entry : this.f.entrySet()) {
                        String key = entry.getKey();
                        i value = entry.getValue();
                        List<SubscriptionCallback> b2 = value.b();
                        List<Bundle> c2 = value.c();
                        for (int i = 0; i < b2.size(); i++) {
                            this.i.a(key, b2.get(i).b, c2.get(i), this.j);
                        }
                    }
                } catch (RemoteException unused) {
                    Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
                }
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        @Nullable
        public Bundle getExtras() {
            if (isConnected()) {
                return this.m;
            }
            throw new IllegalStateException("getExtras() called while not connected (state=" + n(this.g) + ")");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        @NonNull
        public String getRoot() {
            if (isConnected()) {
                return this.k;
            }
            throw new IllegalStateException("getRoot() called while not connected(state=" + n(this.g) + ")");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        @NonNull
        public ComponentName h() {
            if (isConnected()) {
                return this.b;
            }
            throw new IllegalStateException("getServiceComponent() called while not connected (state=" + this.g + ")");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void i(@NonNull String str, @NonNull ItemCallback itemCallback) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("mediaId is empty");
            }
            if (itemCallback != null) {
                if (!isConnected()) {
                    Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
                    this.e.post(new c(this, itemCallback, str));
                    return;
                }
                try {
                    this.i.d(str, new ItemReceiver(str, itemCallback, this.e), this.j);
                    return;
                } catch (RemoteException unused) {
                    Log.i("MediaBrowserCompat", "Remote error getting media item: " + str);
                    this.e.post(new d(this, itemCallback, str));
                    return;
                }
            }
            throw new IllegalArgumentException("cb is null");
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public boolean isConnected() {
            return this.g == 3;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void j(@NonNull String str, Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
            i iVar = this.f.get(str);
            if (iVar == null) {
                iVar = new i();
                this.f.put(str, iVar);
            }
            Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
            iVar.e(bundle2, subscriptionCallback);
            if (isConnected()) {
                try {
                    this.i.a(str, subscriptionCallback.b, bundle2, this.j);
                } catch (RemoteException unused) {
                    Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException parentId=" + str);
                }
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.g
        public void k(Messenger messenger) {
            Log.e("MediaBrowserCompat", "onConnectFailed for " + this.b);
            if (o(messenger, "onConnectFailed")) {
                if (this.g != 2) {
                    Log.w("MediaBrowserCompat", "onConnect from service while mState=" + n(this.g) + "... ignoring");
                    return;
                }
                c();
                this.c.onConnectionFailed();
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void l(@NonNull String str, SubscriptionCallback subscriptionCallback) {
            i iVar = this.f.get(str);
            if (iVar == null) {
                return;
            }
            try {
                if (subscriptionCallback == null) {
                    if (isConnected()) {
                        this.i.f(str, null, this.j);
                    }
                } else {
                    List<SubscriptionCallback> b2 = iVar.b();
                    List<Bundle> c2 = iVar.c();
                    for (int size = b2.size() - 1; size >= 0; size--) {
                        if (b2.get(size) == subscriptionCallback) {
                            if (isConnected()) {
                                this.i.f(str, subscriptionCallback.b, this.j);
                            }
                            b2.remove(size);
                            c2.remove(size);
                        }
                    }
                }
            } catch (RemoteException unused) {
                Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + str);
            }
            if (iVar.d() || subscriptionCallback == null) {
                this.f.remove(str);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public Bundle m() {
            return this.n;
        }

        public final boolean o(Messenger messenger, String str) {
            int i;
            if (this.j != messenger || (i = this.g) == 0 || i == 1) {
                int i2 = this.g;
                if (i2 == 0 || i2 == 1) {
                    return false;
                }
                Log.i("MediaBrowserCompat", str + " for " + this.b + " with mCallbacksMessenger=" + this.j + " this=" + this);
                return false;
            }
            return true;
        }
    }

    /* loaded from: classes.dex */
    public interface g {
        void e(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2);

        void g(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle);

        void k(Messenger messenger);
    }

    /* loaded from: classes.dex */
    public static class h {

        /* renamed from: a  reason: collision with root package name */
        public Messenger f304a;
        public Bundle b;

        public h(IBinder iBinder, Bundle bundle) {
            this.f304a = new Messenger(iBinder);
            this.b = bundle;
        }

        public void a(String str, IBinder iBinder, Bundle bundle, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            BundleCompat.putBinder(bundle2, MediaBrowserProtocol.DATA_CALLBACK_TOKEN, iBinder);
            bundle2.putBundle(MediaBrowserProtocol.DATA_OPTIONS, bundle);
            i(3, bundle2, messenger);
        }

        public void b(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_PACKAGE_NAME, context.getPackageName());
            bundle.putBundle(MediaBrowserProtocol.DATA_ROOT_HINTS, this.b);
            i(1, bundle, messenger);
        }

        public void c(Messenger messenger) throws RemoteException {
            i(2, null, messenger);
        }

        public void d(String str, ResultReceiver resultReceiver, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            bundle.putParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER, resultReceiver);
            i(5, bundle, messenger);
        }

        public void e(Context context, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_PACKAGE_NAME, context.getPackageName());
            bundle.putBundle(MediaBrowserProtocol.DATA_ROOT_HINTS, this.b);
            i(6, bundle, messenger);
        }

        public void f(String str, IBinder iBinder, Messenger messenger) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            BundleCompat.putBinder(bundle, MediaBrowserProtocol.DATA_CALLBACK_TOKEN, iBinder);
            i(4, bundle, messenger);
        }

        public void g(String str, Bundle bundle, ResultReceiver resultReceiver, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_SEARCH_QUERY, str);
            bundle2.putBundle(MediaBrowserProtocol.DATA_SEARCH_EXTRAS, bundle);
            bundle2.putParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER, resultReceiver);
            i(8, bundle2, messenger);
        }

        public void h(String str, Bundle bundle, ResultReceiver resultReceiver, Messenger messenger) throws RemoteException {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_CUSTOM_ACTION, str);
            bundle2.putBundle(MediaBrowserProtocol.DATA_CUSTOM_ACTION_EXTRAS, bundle);
            bundle2.putParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER, resultReceiver);
            i(9, bundle2, messenger);
        }

        public final void i(int i, Bundle bundle, Messenger messenger) throws RemoteException {
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.arg1 = 1;
            obtain.setData(bundle);
            obtain.replyTo = messenger;
            this.f304a.send(obtain);
        }

        public void j(Messenger messenger) throws RemoteException {
            i(7, null, messenger);
        }
    }

    /* loaded from: classes.dex */
    public static class i {

        /* renamed from: a  reason: collision with root package name */
        public final List<SubscriptionCallback> f305a = new ArrayList();
        public final List<Bundle> b = new ArrayList();

        public SubscriptionCallback a(Bundle bundle) {
            for (int i = 0; i < this.b.size(); i++) {
                if (MediaBrowserCompatUtils.areSameOptions(this.b.get(i), bundle)) {
                    return this.f305a.get(i);
                }
            }
            return null;
        }

        public List<SubscriptionCallback> b() {
            return this.f305a;
        }

        public List<Bundle> c() {
            return this.b;
        }

        public boolean d() {
            return this.f305a.isEmpty();
        }

        public void e(Bundle bundle, SubscriptionCallback subscriptionCallback) {
            for (int i = 0; i < this.b.size(); i++) {
                if (MediaBrowserCompatUtils.areSameOptions(this.b.get(i), bundle)) {
                    this.f305a.set(i, subscriptionCallback);
                    return;
                }
            }
            this.f305a.add(subscriptionCallback);
            this.b.add(bundle);
        }
    }

    public MediaBrowserCompat(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            this.f294a = new e(context, componentName, connectionCallback, bundle);
        } else if (i2 >= 23) {
            this.f294a = new d(context, componentName, connectionCallback, bundle);
        } else if (i2 >= 21) {
            this.f294a = new c(context, componentName, connectionCallback, bundle);
        } else {
            this.f294a = new f(context, componentName, connectionCallback, bundle);
        }
    }

    public void connect() {
        this.f294a.connect();
    }

    public void disconnect() {
        this.f294a.disconnect();
    }

    @Nullable
    public Bundle getExtras() {
        return this.f294a.getExtras();
    }

    public void getItem(@NonNull String str, @NonNull ItemCallback itemCallback) {
        this.f294a.i(str, itemCallback);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Bundle getNotifyChildrenChangedOptions() {
        return this.f294a.m();
    }

    @NonNull
    public String getRoot() {
        return this.f294a.getRoot();
    }

    @NonNull
    public ComponentName getServiceComponent() {
        return this.f294a.h();
    }

    @NonNull
    public MediaSessionCompat.Token getSessionToken() {
        return this.f294a.b();
    }

    public boolean isConnected() {
        return this.f294a.isConnected();
    }

    public void search(@NonNull String str, Bundle bundle, @NonNull SearchCallback searchCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query cannot be empty");
        }
        if (searchCallback != null) {
            this.f294a.f(str, bundle, searchCallback);
            return;
        }
        throw new IllegalArgumentException("callback cannot be null");
    }

    public void sendCustomAction(@NonNull String str, Bundle bundle, @Nullable CustomActionCallback customActionCallback) {
        if (!TextUtils.isEmpty(str)) {
            this.f294a.d(str, bundle, customActionCallback);
            return;
        }
        throw new IllegalArgumentException("action cannot be empty");
    }

    public void subscribe(@NonNull String str, @NonNull SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        if (subscriptionCallback != null) {
            this.f294a.j(str, null, subscriptionCallback);
            return;
        }
        throw new IllegalArgumentException("callback is null");
    }

    public void unsubscribe(@NonNull String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f294a.l(str, null);
            return;
        }
        throw new IllegalArgumentException("parentId is empty");
    }

    public void unsubscribe(@NonNull String str, @NonNull SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        if (subscriptionCallback != null) {
            this.f294a.l(str, subscriptionCallback);
            return;
        }
        throw new IllegalArgumentException("callback is null");
    }

    public void subscribe(@NonNull String str, @NonNull Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        if (subscriptionCallback == null) {
            throw new IllegalArgumentException("callback is null");
        }
        if (bundle != null) {
            this.f294a.j(str, bundle, subscriptionCallback);
            return;
        }
        throw new IllegalArgumentException("options are null");
    }

    /* loaded from: classes.dex */
    public static class MediaItem implements Parcelable {
        public static final Parcelable.Creator<MediaItem> CREATOR = new a();
        public static final int FLAG_BROWSABLE = 1;
        public static final int FLAG_PLAYABLE = 2;
        public final int h;
        public final MediaDescriptionCompat i;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        /* loaded from: classes.dex */
        public @interface Flags {
        }

        /* loaded from: classes.dex */
        public static class a implements Parcelable.Creator<MediaItem> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public MediaItem createFromParcel(Parcel parcel) {
                return new MediaItem(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public MediaItem[] newArray(int i) {
                return new MediaItem[i];
            }
        }

        public MediaItem(@NonNull MediaDescriptionCompat mediaDescriptionCompat, int i) {
            if (mediaDescriptionCompat != null) {
                if (!TextUtils.isEmpty(mediaDescriptionCompat.getMediaId())) {
                    this.h = i;
                    this.i = mediaDescriptionCompat;
                    return;
                }
                throw new IllegalArgumentException("description must have a non-empty media id");
            }
            throw new IllegalArgumentException("description cannot be null");
        }

        public static MediaItem fromMediaItem(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return new MediaItem(MediaDescriptionCompat.fromMediaDescription(a.c.a(obj)), a.c.b(obj));
        }

        public static List<MediaItem> fromMediaItemList(List<?> list) {
            if (list == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(fromMediaItem(it.next()));
            }
            return arrayList;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @NonNull
        public MediaDescriptionCompat getDescription() {
            return this.i;
        }

        public int getFlags() {
            return this.h;
        }

        @Nullable
        public String getMediaId() {
            return this.i.getMediaId();
        }

        public boolean isBrowsable() {
            return (this.h & 1) != 0;
        }

        public boolean isPlayable() {
            return (this.h & 2) != 0;
        }

        public String toString() {
            return "MediaItem{mFlags=" + this.h + ", mDescription=" + this.i + '}';
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.h);
            this.i.writeToParcel(parcel, i);
        }

        public MediaItem(Parcel parcel) {
            this.h = parcel.readInt();
            this.i = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }
    }
}
