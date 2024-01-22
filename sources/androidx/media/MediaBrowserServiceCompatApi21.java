package androidx.media;

import android.content.Context;
import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.List;
@RequiresApi(21)
/* loaded from: classes.dex */
public class MediaBrowserServiceCompatApi21 {

    /* loaded from: classes.dex */
    public interface ServiceCompatProxy {
        a onGetRoot(String str, int i, Bundle bundle);

        void onLoadChildren(String str, c<List<Parcel>> cVar);
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final String f1406a;
        public final Bundle b;

        public a(String str, Bundle bundle) {
            this.f1406a = str;
            this.b = bundle;
        }
    }

    /* loaded from: classes.dex */
    public static class b extends MediaBrowserService {
        public final ServiceCompatProxy h;

        public b(Context context, ServiceCompatProxy serviceCompatProxy) {
            attachBaseContext(context);
            this.h = serviceCompatProxy;
        }

        @Override // android.service.media.MediaBrowserService
        public MediaBrowserService.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            a onGetRoot = this.h.onGetRoot(str, i, bundle == null ? null : new Bundle(bundle));
            if (onGetRoot == null) {
                return null;
            }
            return new MediaBrowserService.BrowserRoot(onGetRoot.f1406a, onGetRoot.b);
        }

        @Override // android.service.media.MediaBrowserService
        public void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result) {
            this.h.onLoadChildren(str, new c<>(result));
        }
    }

    /* loaded from: classes.dex */
    public static class c<T> {

        /* renamed from: a  reason: collision with root package name */
        public MediaBrowserService.Result f1407a;

        public c(MediaBrowserService.Result result) {
            this.f1407a = result;
        }

        public void a() {
            this.f1407a.detach();
        }

        public List<MediaBrowser.MediaItem> b(List<Parcel> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcel parcel : list) {
                parcel.setDataPosition(0);
                arrayList.add(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
            }
            return arrayList;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void c(T t) {
            if (t instanceof List) {
                this.f1407a.sendResult(b((List) t));
            } else if (t instanceof Parcel) {
                Parcel parcel = (Parcel) t;
                parcel.setDataPosition(0);
                this.f1407a.sendResult(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
            } else {
                this.f1407a.sendResult(null);
            }
        }
    }

    public static Object a(Context context, ServiceCompatProxy serviceCompatProxy) {
        return new b(context, serviceCompatProxy);
    }

    public static void b(Object obj, String str) {
        ((MediaBrowserService) obj).notifyChildrenChanged(str);
    }

    public static IBinder c(Object obj, Intent intent) {
        return ((MediaBrowserService) obj).onBind(intent);
    }

    public static void d(Object obj) {
        ((MediaBrowserService) obj).onCreate();
    }

    public static void e(Object obj, Object obj2) {
        ((MediaBrowserService) obj).setSessionToken((MediaSession.Token) obj2);
    }
}
