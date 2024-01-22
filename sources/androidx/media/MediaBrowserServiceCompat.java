package androidx.media;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
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
import android.service.media.MediaBrowserService;
import android.support.v4.media.MediaBrowserCompat;
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
import androidx.core.util.Pair;
import androidx.media.MediaBrowserServiceCompatApi21;
import androidx.media.MediaBrowserServiceCompatApi23;
import androidx.media.MediaBrowserServiceCompatApi26;
import androidx.media.MediaSessionManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public abstract class MediaBrowserServiceCompat extends Service {
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String KEY_MEDIA_ITEM = "media_item";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String KEY_SEARCH_RESULTS = "search_results";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final int RESULT_ERROR = -1;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final int RESULT_OK = 0;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final int RESULT_PROGRESS_UPDATE = 1;
    public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserService";
    public static final boolean m = Log.isLoggable("MBServiceCompat", 3);
    public f h;
    public e j;
    public MediaSessionCompat.Token l;
    public final ArrayMap<IBinder, e> i = new ArrayMap<>();
    public final o k = new o();

    /* loaded from: classes.dex */
    public static final class BrowserRoot {
        public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
        public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
        public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";
        @Deprecated
        public static final String EXTRA_SUGGESTION_KEYWORDS = "android.service.media.extra.SUGGESTION_KEYWORDS";

        /* renamed from: a  reason: collision with root package name */
        public final String f1398a;
        public final Bundle b;

        public BrowserRoot(@NonNull String str, @Nullable Bundle bundle) {
            if (str != null) {
                this.f1398a = str;
                this.b = bundle;
                return;
            }
            throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
        }

        public Bundle getExtras() {
            return this.b;
        }

        public String getRootId() {
            return this.f1398a;
        }
    }

    /* loaded from: classes.dex */
    public static class Result<T> {

        /* renamed from: a  reason: collision with root package name */
        public final Object f1399a;
        public boolean b;
        public boolean c;
        public boolean d;
        public int e;

        public Result(Object obj) {
            this.f1399a = obj;
        }

        public final void a(Bundle bundle) {
            if (bundle != null && bundle.containsKey(MediaBrowserCompat.EXTRA_DOWNLOAD_PROGRESS)) {
                float f = bundle.getFloat(MediaBrowserCompat.EXTRA_DOWNLOAD_PROGRESS);
                if (f < -1.0E-5f || f > 1.00001f) {
                    throw new IllegalArgumentException("The value of the EXTRA_DOWNLOAD_PROGRESS field must be a float number within [0.0, 1.0].");
                }
            }
        }

        public int b() {
            return this.e;
        }

        public boolean c() {
            return this.b || this.c || this.d;
        }

        public void d(Bundle bundle) {
            throw new UnsupportedOperationException("It is not supported to send an error for " + this.f1399a);
        }

        public void detach() {
            if (!this.b) {
                if (!this.c) {
                    if (!this.d) {
                        this.b = true;
                        return;
                    }
                    throw new IllegalStateException("detach() called when sendError() had already been called for: " + this.f1399a);
                }
                throw new IllegalStateException("detach() called when sendResult() had already been called for: " + this.f1399a);
            }
            throw new IllegalStateException("detach() called when detach() had already been called for: " + this.f1399a);
        }

        public void e(Bundle bundle) {
            throw new UnsupportedOperationException("It is not supported to send an interim update for " + this.f1399a);
        }

        public void f(T t) {
        }

        public void g(int i) {
            this.e = i;
        }

        public void sendError(Bundle bundle) {
            if (!this.c && !this.d) {
                this.d = true;
                d(bundle);
                return;
            }
            throw new IllegalStateException("sendError() called when either sendResult() or sendError() had already been called for: " + this.f1399a);
        }

        public void sendProgressUpdate(Bundle bundle) {
            if (!this.c && !this.d) {
                a(bundle);
                e(bundle);
                return;
            }
            throw new IllegalStateException("sendProgressUpdate() called when either sendResult() or sendError() had already been called for: " + this.f1399a);
        }

        public void sendResult(T t) {
            if (!this.c && !this.d) {
                this.c = true;
                f(t);
                return;
            }
            throw new IllegalStateException("sendResult() called when either sendResult() or sendError() had already been called for: " + this.f1399a);
        }
    }

    /* loaded from: classes.dex */
    public class a extends Result<List<MediaBrowserCompat.MediaItem>> {
        public final /* synthetic */ e f;
        public final /* synthetic */ String g;
        public final /* synthetic */ Bundle h;
        public final /* synthetic */ Bundle i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Object obj, e eVar, String str, Bundle bundle, Bundle bundle2) {
            super(obj);
            this.f = eVar;
            this.g = str;
            this.h = bundle;
            this.i = bundle2;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.Result
        /* renamed from: h */
        public void f(List<MediaBrowserCompat.MediaItem> list) {
            if (MediaBrowserServiceCompat.this.i.get(this.f.d.asBinder()) != this.f) {
                if (MediaBrowserServiceCompat.m) {
                    Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + this.f.f1400a + " id=" + this.g);
                    return;
                }
                return;
            }
            if ((b() & 1) != 0) {
                list = MediaBrowserServiceCompat.this.b(list, this.h);
            }
            try {
                this.f.d.a(this.g, list, this.h, this.i);
            } catch (RemoteException unused) {
                Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + this.g + " package=" + this.f.f1400a);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b extends Result<MediaBrowserCompat.MediaItem> {
        public final /* synthetic */ ResultReceiver f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.Result
        /* renamed from: h */
        public void f(MediaBrowserCompat.MediaItem mediaItem) {
            if ((b() & 2) != 0) {
                this.f.send(-1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable(MediaBrowserServiceCompat.KEY_MEDIA_ITEM, mediaItem);
            this.f.send(0, bundle);
        }
    }

    /* loaded from: classes.dex */
    public class c extends Result<List<MediaBrowserCompat.MediaItem>> {
        public final /* synthetic */ ResultReceiver f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.Result
        /* renamed from: h */
        public void f(List<MediaBrowserCompat.MediaItem> list) {
            if ((b() & 4) == 0 && list != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArray(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS, (Parcelable[]) list.toArray(new MediaBrowserCompat.MediaItem[0]));
                this.f.send(0, bundle);
                return;
            }
            this.f.send(-1, null);
        }
    }

    /* loaded from: classes.dex */
    public class d extends Result<Bundle> {
        public final /* synthetic */ ResultReceiver f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
            super(obj);
            this.f = resultReceiver;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.Result
        public void d(Bundle bundle) {
            this.f.send(-1, bundle);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.Result
        public void e(Bundle bundle) {
            this.f.send(1, bundle);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.Result
        /* renamed from: h */
        public void f(Bundle bundle) {
            this.f.send(0, bundle);
        }
    }

    /* loaded from: classes.dex */
    public class e implements IBinder.DeathRecipient {

        /* renamed from: a  reason: collision with root package name */
        public final String f1400a;
        public final MediaSessionManager.RemoteUserInfo b;
        public final Bundle c;
        public final m d;
        public final HashMap<String, List<Pair<IBinder, Bundle>>> e = new HashMap<>();
        public BrowserRoot f;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = e.this;
                MediaBrowserServiceCompat.this.i.remove(eVar.d.asBinder());
            }
        }

        public e(String str, int i, int i2, Bundle bundle, m mVar) {
            this.f1400a = str;
            this.b = new MediaSessionManager.RemoteUserInfo(str, i, i2);
            this.c = bundle;
            this.d = mVar;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            MediaBrowserServiceCompat.this.k.post(new a());
        }
    }

    /* loaded from: classes.dex */
    public interface f {
        void a();

        MediaSessionManager.RemoteUserInfo b();

        void c(String str, Bundle bundle);

        void d(MediaSessionCompat.Token token);

        Bundle e();

        void f(MediaSessionManager.RemoteUserInfo remoteUserInfo, String str, Bundle bundle);

        IBinder g(Intent intent);
    }

    @RequiresApi(21)
    /* loaded from: classes.dex */
    public class g implements f, MediaBrowserServiceCompatApi21.ServiceCompatProxy {

        /* renamed from: a  reason: collision with root package name */
        public final List<Bundle> f1401a = new ArrayList();
        public Object b;
        public Messenger c;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public final /* synthetic */ MediaSessionCompat.Token h;

            public a(MediaSessionCompat.Token token) {
                this.h = token;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (!g.this.f1401a.isEmpty()) {
                    IMediaSession extraBinder = this.h.getExtraBinder();
                    if (extraBinder != null) {
                        for (Bundle bundle : g.this.f1401a) {
                            BundleCompat.putBinder(bundle, MediaBrowserProtocol.EXTRA_SESSION_BINDER, extraBinder.asBinder());
                        }
                    }
                    g.this.f1401a.clear();
                }
                MediaBrowserServiceCompatApi21.e(g.this.b, this.h.getToken());
            }
        }

        /* loaded from: classes.dex */
        public class b extends Result<List<MediaBrowserCompat.MediaItem>> {
            public final /* synthetic */ MediaBrowserServiceCompatApi21.c f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(g gVar, Object obj, MediaBrowserServiceCompatApi21.c cVar) {
                super(obj);
                this.f = cVar;
            }

            @Override // androidx.media.MediaBrowserServiceCompat.Result
            public void detach() {
                this.f.a();
            }

            @Override // androidx.media.MediaBrowserServiceCompat.Result
            /* renamed from: h */
            public void f(List<MediaBrowserCompat.MediaItem> list) {
                ArrayList arrayList;
                if (list != null) {
                    arrayList = new ArrayList();
                    for (MediaBrowserCompat.MediaItem mediaItem : list) {
                        Parcel obtain = Parcel.obtain();
                        mediaItem.writeToParcel(obtain, 0);
                        arrayList.add(obtain);
                    }
                } else {
                    arrayList = null;
                }
                this.f.c(arrayList);
            }
        }

        /* loaded from: classes.dex */
        public class c implements Runnable {
            public final /* synthetic */ String h;
            public final /* synthetic */ Bundle i;

            public c(String str, Bundle bundle) {
                this.h = str;
                this.i = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                for (IBinder iBinder : MediaBrowserServiceCompat.this.i.keySet()) {
                    ArrayMap<IBinder, e> arrayMap = MediaBrowserServiceCompat.this.i;
                    g.this.j(arrayMap.get(iBinder), this.h, this.i);
                }
            }
        }

        /* loaded from: classes.dex */
        public class d implements Runnable {
            public final /* synthetic */ MediaSessionManager.RemoteUserInfo h;
            public final /* synthetic */ String i;
            public final /* synthetic */ Bundle j;

            public d(MediaSessionManager.RemoteUserInfo remoteUserInfo, String str, Bundle bundle) {
                this.h = remoteUserInfo;
                this.i = str;
                this.j = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                for (int i = 0; i < MediaBrowserServiceCompat.this.i.size(); i++) {
                    e valueAt = MediaBrowserServiceCompat.this.i.valueAt(i);
                    if (valueAt.b.equals(this.h)) {
                        g.this.j(valueAt, this.i, this.j);
                    }
                }
            }
        }

        public g() {
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public void a() {
            Object a2 = MediaBrowserServiceCompatApi21.a(MediaBrowserServiceCompat.this, this);
            this.b = a2;
            MediaBrowserServiceCompatApi21.d(a2);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public MediaSessionManager.RemoteUserInfo b() {
            e eVar = MediaBrowserServiceCompat.this.j;
            if (eVar != null) {
                return eVar.b;
            }
            throw new IllegalStateException("This should be called inside of onGetRoot, onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public void c(String str, Bundle bundle) {
            k(str, bundle);
            i(str, bundle);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public void d(MediaSessionCompat.Token token) {
            MediaBrowserServiceCompat.this.k.a(new a(token));
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public Bundle e() {
            if (this.c == null) {
                return null;
            }
            e eVar = MediaBrowserServiceCompat.this.j;
            if (eVar != null) {
                if (eVar.c == null) {
                    return null;
                }
                return new Bundle(MediaBrowserServiceCompat.this.j.c);
            }
            throw new IllegalStateException("This should be called inside of onGetRoot, onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public void f(MediaSessionManager.RemoteUserInfo remoteUserInfo, String str, Bundle bundle) {
            h(remoteUserInfo, str, bundle);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public IBinder g(Intent intent) {
            return MediaBrowserServiceCompatApi21.c(this.b, intent);
        }

        public void h(MediaSessionManager.RemoteUserInfo remoteUserInfo, String str, Bundle bundle) {
            MediaBrowserServiceCompat.this.k.post(new d(remoteUserInfo, str, bundle));
        }

        public void i(String str, Bundle bundle) {
            MediaBrowserServiceCompat.this.k.post(new c(str, bundle));
        }

        public void j(e eVar, String str, Bundle bundle) {
            List<Pair<IBinder, Bundle>> list = eVar.e.get(str);
            if (list != null) {
                for (Pair<IBinder, Bundle> pair : list) {
                    if (MediaBrowserCompatUtils.hasDuplicatedItems(bundle, pair.second)) {
                        MediaBrowserServiceCompat.this.e(str, eVar, pair.second, bundle);
                    }
                }
            }
        }

        public void k(String str, Bundle bundle) {
            MediaBrowserServiceCompatApi21.b(this.b, str);
        }

        @Override // androidx.media.MediaBrowserServiceCompatApi21.ServiceCompatProxy
        public MediaBrowserServiceCompatApi21.a onGetRoot(String str, int i, Bundle bundle) {
            Bundle bundle2;
            if (bundle == null || bundle.getInt(MediaBrowserProtocol.EXTRA_CLIENT_VERSION, 0) == 0) {
                bundle2 = null;
            } else {
                bundle.remove(MediaBrowserProtocol.EXTRA_CLIENT_VERSION);
                this.c = new Messenger(MediaBrowserServiceCompat.this.k);
                bundle2 = new Bundle();
                bundle2.putInt(MediaBrowserProtocol.EXTRA_SERVICE_VERSION, 2);
                BundleCompat.putBinder(bundle2, MediaBrowserProtocol.EXTRA_MESSENGER_BINDER, this.c.getBinder());
                MediaSessionCompat.Token token = MediaBrowserServiceCompat.this.l;
                if (token != null) {
                    IMediaSession extraBinder = token.getExtraBinder();
                    BundleCompat.putBinder(bundle2, MediaBrowserProtocol.EXTRA_SESSION_BINDER, extraBinder == null ? null : extraBinder.asBinder());
                } else {
                    this.f1401a.add(bundle2);
                }
            }
            MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
            mediaBrowserServiceCompat.j = new e(str, -1, i, bundle, null);
            BrowserRoot onGetRoot = MediaBrowserServiceCompat.this.onGetRoot(str, i, bundle);
            MediaBrowserServiceCompat.this.j = null;
            if (onGetRoot == null) {
                return null;
            }
            if (bundle2 == null) {
                bundle2 = onGetRoot.getExtras();
            } else if (onGetRoot.getExtras() != null) {
                bundle2.putAll(onGetRoot.getExtras());
            }
            return new MediaBrowserServiceCompatApi21.a(onGetRoot.getRootId(), bundle2);
        }

        @Override // androidx.media.MediaBrowserServiceCompatApi21.ServiceCompatProxy
        public void onLoadChildren(String str, MediaBrowserServiceCompatApi21.c<List<Parcel>> cVar) {
            MediaBrowserServiceCompat.this.onLoadChildren(str, new b(this, str, cVar));
        }
    }

    @RequiresApi(23)
    /* loaded from: classes.dex */
    public class h extends g implements MediaBrowserServiceCompatApi23.ServiceCompatProxy {

        /* loaded from: classes.dex */
        public class a extends Result<MediaBrowserCompat.MediaItem> {
            public final /* synthetic */ MediaBrowserServiceCompatApi21.c f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(h hVar, Object obj, MediaBrowserServiceCompatApi21.c cVar) {
                super(obj);
                this.f = cVar;
            }

            @Override // androidx.media.MediaBrowserServiceCompat.Result
            public void detach() {
                this.f.a();
            }

            @Override // androidx.media.MediaBrowserServiceCompat.Result
            /* renamed from: h */
            public void f(MediaBrowserCompat.MediaItem mediaItem) {
                if (mediaItem == null) {
                    this.f.c(null);
                    return;
                }
                Parcel obtain = Parcel.obtain();
                mediaItem.writeToParcel(obtain, 0);
                this.f.c(obtain);
            }
        }

        public h() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g, androidx.media.MediaBrowserServiceCompat.f
        public void a() {
            Object a2 = MediaBrowserServiceCompatApi23.a(MediaBrowserServiceCompat.this, this);
            this.b = a2;
            MediaBrowserServiceCompatApi21.d(a2);
        }

        @Override // androidx.media.MediaBrowserServiceCompatApi23.ServiceCompatProxy
        public void onLoadItem(String str, MediaBrowserServiceCompatApi21.c<Parcel> cVar) {
            MediaBrowserServiceCompat.this.onLoadItem(str, new a(this, str, cVar));
        }
    }

    @RequiresApi(26)
    /* loaded from: classes.dex */
    public class i extends h implements MediaBrowserServiceCompatApi26.ServiceCompatProxy {

        /* loaded from: classes.dex */
        public class a extends Result<List<MediaBrowserCompat.MediaItem>> {
            public final /* synthetic */ MediaBrowserServiceCompatApi26.b f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(i iVar, Object obj, MediaBrowserServiceCompatApi26.b bVar) {
                super(obj);
                this.f = bVar;
            }

            @Override // androidx.media.MediaBrowserServiceCompat.Result
            public void detach() {
                this.f.a();
            }

            @Override // androidx.media.MediaBrowserServiceCompat.Result
            /* renamed from: h */
            public void f(List<MediaBrowserCompat.MediaItem> list) {
                ArrayList arrayList;
                if (list != null) {
                    arrayList = new ArrayList();
                    for (MediaBrowserCompat.MediaItem mediaItem : list) {
                        Parcel obtain = Parcel.obtain();
                        mediaItem.writeToParcel(obtain, 0);
                        arrayList.add(obtain);
                    }
                } else {
                    arrayList = null;
                }
                this.f.c(arrayList, b());
            }
        }

        public i() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.h, androidx.media.MediaBrowserServiceCompat.g, androidx.media.MediaBrowserServiceCompat.f
        public void a() {
            Object a2 = MediaBrowserServiceCompatApi26.a(MediaBrowserServiceCompat.this, this);
            this.b = a2;
            MediaBrowserServiceCompatApi21.d(a2);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g, androidx.media.MediaBrowserServiceCompat.f
        public Bundle e() {
            e eVar = MediaBrowserServiceCompat.this.j;
            if (eVar != null) {
                if (eVar.c == null) {
                    return null;
                }
                return new Bundle(MediaBrowserServiceCompat.this.j.c);
            }
            return MediaBrowserServiceCompatApi26.b(this.b);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g
        public void k(String str, Bundle bundle) {
            if (bundle != null) {
                MediaBrowserServiceCompatApi26.c(this.b, str, bundle);
            } else {
                super.k(str, bundle);
            }
        }

        @Override // androidx.media.MediaBrowserServiceCompatApi26.ServiceCompatProxy
        public void onLoadChildren(String str, MediaBrowserServiceCompatApi26.b bVar, Bundle bundle) {
            MediaBrowserServiceCompat.this.onLoadChildren(str, new a(this, str, bVar), bundle);
        }
    }

    @RequiresApi(28)
    /* loaded from: classes.dex */
    public class j extends i {
        public j() {
            super();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.g, androidx.media.MediaBrowserServiceCompat.f
        public MediaSessionManager.RemoteUserInfo b() {
            e eVar = MediaBrowserServiceCompat.this.j;
            if (eVar != null) {
                return eVar.b;
            }
            return new MediaSessionManager.RemoteUserInfo(((MediaBrowserService) this.b).getCurrentBrowserInfo());
        }
    }

    /* loaded from: classes.dex */
    public class k implements f {

        /* renamed from: a  reason: collision with root package name */
        public Messenger f1402a;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public final /* synthetic */ MediaSessionCompat.Token h;

            public a(MediaSessionCompat.Token token) {
                this.h = token;
            }

            @Override // java.lang.Runnable
            public void run() {
                Iterator<e> it = MediaBrowserServiceCompat.this.i.values().iterator();
                while (it.hasNext()) {
                    e next = it.next();
                    try {
                        next.d.c(next.f.getRootId(), this.h, next.f.getExtras());
                    } catch (RemoteException unused) {
                        Log.w("MBServiceCompat", "Connection for " + next.f1400a + " is no longer valid.");
                        it.remove();
                    }
                }
            }
        }

        /* loaded from: classes.dex */
        public class b implements Runnable {
            public final /* synthetic */ String h;
            public final /* synthetic */ Bundle i;

            public b(String str, Bundle bundle) {
                this.h = str;
                this.i = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                for (IBinder iBinder : MediaBrowserServiceCompat.this.i.keySet()) {
                    ArrayMap<IBinder, e> arrayMap = MediaBrowserServiceCompat.this.i;
                    k.this.h(arrayMap.get(iBinder), this.h, this.i);
                }
            }
        }

        /* loaded from: classes.dex */
        public class c implements Runnable {
            public final /* synthetic */ MediaSessionManager.RemoteUserInfo h;
            public final /* synthetic */ String i;
            public final /* synthetic */ Bundle j;

            public c(MediaSessionManager.RemoteUserInfo remoteUserInfo, String str, Bundle bundle) {
                this.h = remoteUserInfo;
                this.i = str;
                this.j = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                for (int i = 0; i < MediaBrowserServiceCompat.this.i.size(); i++) {
                    e valueAt = MediaBrowserServiceCompat.this.i.valueAt(i);
                    if (valueAt.b.equals(this.h)) {
                        k.this.h(valueAt, this.i, this.j);
                        return;
                    }
                }
            }
        }

        public k() {
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public void a() {
            this.f1402a = new Messenger(MediaBrowserServiceCompat.this.k);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public MediaSessionManager.RemoteUserInfo b() {
            e eVar = MediaBrowserServiceCompat.this.j;
            if (eVar != null) {
                return eVar.b;
            }
            throw new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public void c(@NonNull String str, Bundle bundle) {
            MediaBrowserServiceCompat.this.k.post(new b(str, bundle));
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public void d(MediaSessionCompat.Token token) {
            MediaBrowserServiceCompat.this.k.post(new a(token));
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public Bundle e() {
            e eVar = MediaBrowserServiceCompat.this.j;
            if (eVar != null) {
                if (eVar.c == null) {
                    return null;
                }
                return new Bundle(MediaBrowserServiceCompat.this.j.c);
            }
            throw new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public void f(@NonNull MediaSessionManager.RemoteUserInfo remoteUserInfo, @NonNull String str, Bundle bundle) {
            MediaBrowserServiceCompat.this.k.post(new c(remoteUserInfo, str, bundle));
        }

        @Override // androidx.media.MediaBrowserServiceCompat.f
        public IBinder g(Intent intent) {
            if (MediaBrowserServiceCompat.SERVICE_INTERFACE.equals(intent.getAction())) {
                return this.f1402a.getBinder();
            }
            return null;
        }

        public void h(e eVar, String str, Bundle bundle) {
            List<Pair<IBinder, Bundle>> list = eVar.e.get(str);
            if (list != null) {
                for (Pair<IBinder, Bundle> pair : list) {
                    if (MediaBrowserCompatUtils.hasDuplicatedItems(bundle, pair.second)) {
                        MediaBrowserServiceCompat.this.e(str, eVar, pair.second, bundle);
                    }
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class l {

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public final /* synthetic */ m h;
            public final /* synthetic */ String i;
            public final /* synthetic */ int j;
            public final /* synthetic */ int k;
            public final /* synthetic */ Bundle l;

            public a(m mVar, String str, int i, int i2, Bundle bundle) {
                this.h = mVar;
                this.i = str;
                this.j = i;
                this.k = i2;
                this.l = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder asBinder = this.h.asBinder();
                MediaBrowserServiceCompat.this.i.remove(asBinder);
                e eVar = new e(this.i, this.j, this.k, this.l, this.h);
                MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
                mediaBrowserServiceCompat.j = eVar;
                BrowserRoot onGetRoot = mediaBrowserServiceCompat.onGetRoot(this.i, this.k, this.l);
                eVar.f = onGetRoot;
                MediaBrowserServiceCompat mediaBrowserServiceCompat2 = MediaBrowserServiceCompat.this;
                mediaBrowserServiceCompat2.j = null;
                if (onGetRoot == null) {
                    Log.i("MBServiceCompat", "No root for client " + this.i + " from service " + a.class.getName());
                    try {
                        this.h.b();
                        return;
                    } catch (RemoteException unused) {
                        Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + this.i);
                        return;
                    }
                }
                try {
                    mediaBrowserServiceCompat2.i.put(asBinder, eVar);
                    asBinder.linkToDeath(eVar, 0);
                    if (MediaBrowserServiceCompat.this.l != null) {
                        this.h.c(eVar.f.getRootId(), MediaBrowserServiceCompat.this.l, eVar.f.getExtras());
                    }
                } catch (RemoteException unused2) {
                    Log.w("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + this.i);
                    MediaBrowserServiceCompat.this.i.remove(asBinder);
                }
            }
        }

        /* loaded from: classes.dex */
        public class b implements Runnable {
            public final /* synthetic */ m h;

            public b(m mVar) {
                this.h = mVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                e remove = MediaBrowserServiceCompat.this.i.remove(this.h.asBinder());
                if (remove != null) {
                    remove.d.asBinder().unlinkToDeath(remove, 0);
                }
            }
        }

        /* loaded from: classes.dex */
        public class c implements Runnable {
            public final /* synthetic */ m h;
            public final /* synthetic */ String i;
            public final /* synthetic */ IBinder j;
            public final /* synthetic */ Bundle k;

            public c(m mVar, String str, IBinder iBinder, Bundle bundle) {
                this.h = mVar;
                this.i = str;
                this.j = iBinder;
                this.k = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = MediaBrowserServiceCompat.this.i.get(this.h.asBinder());
                if (eVar == null) {
                    Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + this.i);
                    return;
                }
                MediaBrowserServiceCompat.this.a(this.i, eVar, this.j, this.k);
            }
        }

        /* loaded from: classes.dex */
        public class d implements Runnable {
            public final /* synthetic */ m h;
            public final /* synthetic */ String i;
            public final /* synthetic */ IBinder j;

            public d(m mVar, String str, IBinder iBinder) {
                this.h = mVar;
                this.i = str;
                this.j = iBinder;
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = MediaBrowserServiceCompat.this.i.get(this.h.asBinder());
                if (eVar == null) {
                    Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + this.i);
                } else if (MediaBrowserServiceCompat.this.h(this.i, eVar, this.j)) {
                } else {
                    Log.w("MBServiceCompat", "removeSubscription called for " + this.i + " which is not subscribed");
                }
            }
        }

        /* loaded from: classes.dex */
        public class e implements Runnable {
            public final /* synthetic */ m h;
            public final /* synthetic */ String i;
            public final /* synthetic */ ResultReceiver j;

            public e(m mVar, String str, ResultReceiver resultReceiver) {
                this.h = mVar;
                this.i = str;
                this.j = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = MediaBrowserServiceCompat.this.i.get(this.h.asBinder());
                if (eVar == null) {
                    Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + this.i);
                    return;
                }
                MediaBrowserServiceCompat.this.f(this.i, eVar, this.j);
            }
        }

        /* loaded from: classes.dex */
        public class f implements Runnable {
            public final /* synthetic */ m h;
            public final /* synthetic */ String i;
            public final /* synthetic */ int j;
            public final /* synthetic */ int k;
            public final /* synthetic */ Bundle l;

            public f(m mVar, String str, int i, int i2, Bundle bundle) {
                this.h = mVar;
                this.i = str;
                this.j = i;
                this.k = i2;
                this.l = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder asBinder = this.h.asBinder();
                MediaBrowserServiceCompat.this.i.remove(asBinder);
                e eVar = new e(this.i, this.j, this.k, this.l, this.h);
                MediaBrowserServiceCompat.this.i.put(asBinder, eVar);
                try {
                    asBinder.linkToDeath(eVar, 0);
                } catch (RemoteException unused) {
                    Log.w("MBServiceCompat", "IBinder is already dead.");
                }
            }
        }

        /* loaded from: classes.dex */
        public class g implements Runnable {
            public final /* synthetic */ m h;

            public g(m mVar) {
                this.h = mVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                IBinder asBinder = this.h.asBinder();
                e remove = MediaBrowserServiceCompat.this.i.remove(asBinder);
                if (remove != null) {
                    asBinder.unlinkToDeath(remove, 0);
                }
            }
        }

        /* loaded from: classes.dex */
        public class h implements Runnable {
            public final /* synthetic */ m h;
            public final /* synthetic */ String i;
            public final /* synthetic */ Bundle j;
            public final /* synthetic */ ResultReceiver k;

            public h(m mVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.h = mVar;
                this.i = str;
                this.j = bundle;
                this.k = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = MediaBrowserServiceCompat.this.i.get(this.h.asBinder());
                if (eVar == null) {
                    Log.w("MBServiceCompat", "search for callback that isn't registered query=" + this.i);
                    return;
                }
                MediaBrowserServiceCompat.this.g(this.i, this.j, eVar, this.k);
            }
        }

        /* loaded from: classes.dex */
        public class i implements Runnable {
            public final /* synthetic */ m h;
            public final /* synthetic */ String i;
            public final /* synthetic */ Bundle j;
            public final /* synthetic */ ResultReceiver k;

            public i(m mVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
                this.h = mVar;
                this.i = str;
                this.j = bundle;
                this.k = resultReceiver;
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = MediaBrowserServiceCompat.this.i.get(this.h.asBinder());
                if (eVar == null) {
                    Log.w("MBServiceCompat", "sendCustomAction for callback that isn't registered action=" + this.i + ", extras=" + this.j);
                    return;
                }
                MediaBrowserServiceCompat.this.d(this.i, this.j, eVar, this.k);
            }
        }

        public l() {
        }

        public void a(String str, IBinder iBinder, Bundle bundle, m mVar) {
            MediaBrowserServiceCompat.this.k.a(new c(mVar, str, iBinder, bundle));
        }

        public void b(String str, int i2, int i3, Bundle bundle, m mVar) {
            if (MediaBrowserServiceCompat.this.c(str, i3)) {
                MediaBrowserServiceCompat.this.k.a(new a(mVar, str, i2, i3, bundle));
                return;
            }
            throw new IllegalArgumentException("Package/uid mismatch: uid=" + i3 + " package=" + str);
        }

        public void c(m mVar) {
            MediaBrowserServiceCompat.this.k.a(new b(mVar));
        }

        public void d(String str, ResultReceiver resultReceiver, m mVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.k.a(new e(mVar, str, resultReceiver));
        }

        public void e(m mVar, String str, int i2, int i3, Bundle bundle) {
            MediaBrowserServiceCompat.this.k.a(new f(mVar, str, i2, i3, bundle));
        }

        public void f(String str, IBinder iBinder, m mVar) {
            MediaBrowserServiceCompat.this.k.a(new d(mVar, str, iBinder));
        }

        public void g(String str, Bundle bundle, ResultReceiver resultReceiver, m mVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.k.a(new h(mVar, str, bundle, resultReceiver));
        }

        public void h(String str, Bundle bundle, ResultReceiver resultReceiver, m mVar) {
            if (TextUtils.isEmpty(str) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.k.a(new i(mVar, str, bundle, resultReceiver));
        }

        public void i(m mVar) {
            MediaBrowserServiceCompat.this.k.a(new g(mVar));
        }
    }

    /* loaded from: classes.dex */
    public interface m {
        void a(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle, Bundle bundle2) throws RemoteException;

        IBinder asBinder();

        void b() throws RemoteException;

        void c(String str, MediaSessionCompat.Token token, Bundle bundle) throws RemoteException;
    }

    /* loaded from: classes.dex */
    public static class n implements m {

        /* renamed from: a  reason: collision with root package name */
        public final Messenger f1404a;

        public n(Messenger messenger) {
            this.f1404a = messenger;
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        public void a(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle, Bundle bundle2) throws RemoteException {
            Bundle bundle3 = new Bundle();
            bundle3.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            bundle3.putBundle(MediaBrowserProtocol.DATA_OPTIONS, bundle);
            bundle3.putBundle(MediaBrowserProtocol.DATA_NOTIFY_CHILDREN_CHANGED_OPTIONS, bundle2);
            if (list != null) {
                bundle3.putParcelableArrayList(MediaBrowserProtocol.DATA_MEDIA_ITEM_LIST, list instanceof ArrayList ? (ArrayList) list : new ArrayList<>(list));
            }
            d(3, bundle3);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        public IBinder asBinder() {
            return this.f1404a.getBinder();
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        public void b() throws RemoteException {
            d(2, null);
        }

        @Override // androidx.media.MediaBrowserServiceCompat.m
        public void c(String str, MediaSessionCompat.Token token, Bundle bundle) throws RemoteException {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt(MediaBrowserProtocol.EXTRA_SERVICE_VERSION, 2);
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, str);
            bundle2.putParcelable(MediaBrowserProtocol.DATA_MEDIA_SESSION_TOKEN, token);
            bundle2.putBundle(MediaBrowserProtocol.DATA_ROOT_HINTS, bundle);
            d(1, bundle2);
        }

        public final void d(int i, Bundle bundle) throws RemoteException {
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.arg1 = 2;
            obtain.setData(bundle);
            this.f1404a.send(obtain);
        }
    }

    /* loaded from: classes.dex */
    public final class o extends Handler {

        /* renamed from: a  reason: collision with root package name */
        public final l f1405a;

        public o() {
            this.f1405a = new l();
        }

        public void a(Runnable runnable) {
            if (Thread.currentThread() == getLooper().getThread()) {
                runnable.run();
            } else {
                post(runnable);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Bundle data = message.getData();
            switch (message.what) {
                case 1:
                    Bundle bundle = data.getBundle(MediaBrowserProtocol.DATA_ROOT_HINTS);
                    MediaSessionCompat.ensureClassLoader(bundle);
                    this.f1405a.b(data.getString(MediaBrowserProtocol.DATA_PACKAGE_NAME), data.getInt(MediaBrowserProtocol.DATA_CALLING_PID), data.getInt(MediaBrowserProtocol.DATA_CALLING_UID), bundle, new n(message.replyTo));
                    return;
                case 2:
                    this.f1405a.c(new n(message.replyTo));
                    return;
                case 3:
                    Bundle bundle2 = data.getBundle(MediaBrowserProtocol.DATA_OPTIONS);
                    MediaSessionCompat.ensureClassLoader(bundle2);
                    this.f1405a.a(data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), BundleCompat.getBinder(data, MediaBrowserProtocol.DATA_CALLBACK_TOKEN), bundle2, new n(message.replyTo));
                    return;
                case 4:
                    this.f1405a.f(data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), BundleCompat.getBinder(data, MediaBrowserProtocol.DATA_CALLBACK_TOKEN), new n(message.replyTo));
                    return;
                case 5:
                    this.f1405a.d(data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), (ResultReceiver) data.getParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER), new n(message.replyTo));
                    return;
                case 6:
                    Bundle bundle3 = data.getBundle(MediaBrowserProtocol.DATA_ROOT_HINTS);
                    MediaSessionCompat.ensureClassLoader(bundle3);
                    this.f1405a.e(new n(message.replyTo), data.getString(MediaBrowserProtocol.DATA_PACKAGE_NAME), data.getInt(MediaBrowserProtocol.DATA_CALLING_PID), data.getInt(MediaBrowserProtocol.DATA_CALLING_UID), bundle3);
                    return;
                case 7:
                    this.f1405a.i(new n(message.replyTo));
                    return;
                case 8:
                    Bundle bundle4 = data.getBundle(MediaBrowserProtocol.DATA_SEARCH_EXTRAS);
                    MediaSessionCompat.ensureClassLoader(bundle4);
                    this.f1405a.g(data.getString(MediaBrowserProtocol.DATA_SEARCH_QUERY), bundle4, (ResultReceiver) data.getParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER), new n(message.replyTo));
                    return;
                case 9:
                    Bundle bundle5 = data.getBundle(MediaBrowserProtocol.DATA_CUSTOM_ACTION_EXTRAS);
                    MediaSessionCompat.ensureClassLoader(bundle5);
                    this.f1405a.h(data.getString(MediaBrowserProtocol.DATA_CUSTOM_ACTION), bundle5, (ResultReceiver) data.getParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER), new n(message.replyTo));
                    return;
                default:
                    Log.w("MBServiceCompat", "Unhandled message: " + message + "\n  Service version: 2\n  Client version: " + message.arg1);
                    return;
            }
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(Message message, long j) {
            Bundle data = message.getData();
            data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            data.putInt(MediaBrowserProtocol.DATA_CALLING_UID, Binder.getCallingUid());
            data.putInt(MediaBrowserProtocol.DATA_CALLING_PID, Binder.getCallingPid());
            return super.sendMessageAtTime(message, j);
        }
    }

    public void a(String str, e eVar, IBinder iBinder, Bundle bundle) {
        List<Pair<IBinder, Bundle>> list = eVar.e.get(str);
        if (list == null) {
            list = new ArrayList<>();
        }
        for (Pair<IBinder, Bundle> pair : list) {
            if (iBinder == pair.first && MediaBrowserCompatUtils.areSameOptions(bundle, pair.second)) {
                return;
            }
        }
        list.add(new Pair<>(iBinder, bundle));
        eVar.e.put(str, list);
        e(str, eVar, bundle, null);
        this.j = eVar;
        onSubscribe(str, bundle);
        this.j = null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void attachToBaseContext(Context context) {
        attachBaseContext(context);
    }

    public List<MediaBrowserCompat.MediaItem> b(List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
        if (list == null) {
            return null;
        }
        int i2 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
        int i3 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
        if (i2 == -1 && i3 == -1) {
            return list;
        }
        int i4 = i3 * i2;
        int i5 = i4 + i3;
        if (i2 >= 0 && i3 >= 1 && i4 < list.size()) {
            if (i5 > list.size()) {
                i5 = list.size();
            }
            return list.subList(i4, i5);
        }
        return Collections.emptyList();
    }

    public boolean c(String str, int i2) {
        if (str == null) {
            return false;
        }
        for (String str2 : getPackageManager().getPackagesForUid(i2)) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void d(String str, Bundle bundle, e eVar, ResultReceiver resultReceiver) {
        d dVar = new d(this, str, resultReceiver);
        this.j = eVar;
        onCustomAction(str, bundle, dVar);
        this.j = null;
        if (dVar.c()) {
            return;
        }
        throw new IllegalStateException("onCustomAction must call detach() or sendResult() or sendError() before returning for action=" + str + " extras=" + bundle);
    }

    @Override // android.app.Service
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public void e(String str, e eVar, Bundle bundle, Bundle bundle2) {
        a aVar = new a(str, eVar, str, bundle, bundle2);
        this.j = eVar;
        if (bundle == null) {
            onLoadChildren(str, aVar);
        } else {
            onLoadChildren(str, aVar, bundle);
        }
        this.j = null;
        if (aVar.c()) {
            return;
        }
        throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + eVar.f1400a + " id=" + str);
    }

    public void f(String str, e eVar, ResultReceiver resultReceiver) {
        b bVar = new b(this, str, resultReceiver);
        this.j = eVar;
        onLoadItem(str, bVar);
        this.j = null;
        if (bVar.c()) {
            return;
        }
        throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + str);
    }

    public void g(String str, Bundle bundle, e eVar, ResultReceiver resultReceiver) {
        c cVar = new c(this, str, resultReceiver);
        this.j = eVar;
        onSearch(str, bundle, cVar);
        this.j = null;
        if (cVar.c()) {
            return;
        }
        throw new IllegalStateException("onSearch must call detach() or sendResult() before returning for query=" + str);
    }

    public final Bundle getBrowserRootHints() {
        return this.h.e();
    }

    @NonNull
    public final MediaSessionManager.RemoteUserInfo getCurrentBrowserInfo() {
        return this.h.b();
    }

    @Nullable
    public MediaSessionCompat.Token getSessionToken() {
        return this.l;
    }

    public boolean h(String str, e eVar, IBinder iBinder) {
        boolean z = false;
        try {
            if (iBinder == null) {
                return eVar.e.remove(str) != null;
            }
            List<Pair<IBinder, Bundle>> list = eVar.e.get(str);
            if (list != null) {
                Iterator<Pair<IBinder, Bundle>> it = list.iterator();
                while (it.hasNext()) {
                    if (iBinder == it.next().first) {
                        it.remove();
                        z = true;
                    }
                }
                if (list.size() == 0) {
                    eVar.e.remove(str);
                }
            }
            return z;
        } finally {
            this.j = eVar;
            onUnsubscribe(str);
            this.j = null;
        }
    }

    public void notifyChildrenChanged(@NonNull String str) {
        if (str != null) {
            this.h.c(str, null);
            return;
        }
        throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.h.g(intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            this.h = new j();
        } else if (i2 >= 26) {
            this.h = new i();
        } else if (i2 >= 23) {
            this.h = new h();
        } else if (i2 >= 21) {
            this.h = new g();
        } else {
            this.h = new k();
        }
        this.h.a();
    }

    public void onCustomAction(@NonNull String str, Bundle bundle, @NonNull Result<Bundle> result) {
        result.sendError(null);
    }

    @Nullable
    public abstract BrowserRoot onGetRoot(@NonNull String str, int i2, @Nullable Bundle bundle);

    public abstract void onLoadChildren(@NonNull String str, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result);

    public void onLoadChildren(@NonNull String str, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result, @NonNull Bundle bundle) {
        result.g(1);
        onLoadChildren(str, result);
    }

    public void onLoadItem(String str, @NonNull Result<MediaBrowserCompat.MediaItem> result) {
        result.g(2);
        result.sendResult(null);
    }

    public void onSearch(@NonNull String str, Bundle bundle, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {
        result.g(4);
        result.sendResult(null);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onSubscribe(String str, Bundle bundle) {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onUnsubscribe(String str) {
    }

    public void setSessionToken(MediaSessionCompat.Token token) {
        if (token != null) {
            if (this.l == null) {
                this.l = token;
                this.h.d(token);
                return;
            }
            throw new IllegalStateException("The session token has already been set.");
        }
        throw new IllegalArgumentException("Session token may not be null.");
    }

    public void notifyChildrenChanged(@NonNull String str, @NonNull Bundle bundle) {
        if (str == null) {
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
        }
        if (bundle != null) {
            this.h.c(str, bundle);
            return;
        }
        throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void notifyChildrenChanged(@NonNull MediaSessionManager.RemoteUserInfo remoteUserInfo, @NonNull String str, @NonNull Bundle bundle) {
        if (remoteUserInfo == null) {
            throw new IllegalArgumentException("remoteUserInfo cannot be null in notifyChildrenChanged");
        }
        if (str == null) {
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
        }
        if (bundle != null) {
            this.h.f(remoteUserInfo, str, bundle);
            return;
        }
        throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
    }
}
