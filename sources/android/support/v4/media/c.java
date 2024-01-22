package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.support.v4.media.a;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.util.List;
@RequiresApi(26)
/* loaded from: classes.dex */
public class c {

    /* loaded from: classes.dex */
    public interface a extends a.d {
        void a(@NonNull String str, @NonNull Bundle bundle);

        void b(@NonNull String str, List<?> list, @NonNull Bundle bundle);
    }

    /* loaded from: classes.dex */
    public static class b<T extends a> extends a.e<T> {
        public b(T t) {
            super(t);
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onChildrenLoaded(@NonNull String str, List<MediaBrowser.MediaItem> list, @NonNull Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            ((a) this.f309a).b(str, list, bundle);
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onError(@NonNull String str, @NonNull Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            ((a) this.f309a).a(str, bundle);
        }
    }

    public static Object a(a aVar) {
        return new b(aVar);
    }

    public static void b(Object obj, String str, Bundle bundle, Object obj2) {
        ((MediaBrowser) obj).subscribe(str, bundle, (MediaBrowser.SubscriptionCallback) obj2);
    }

    public static void c(Object obj, String str, Object obj2) {
        ((MediaBrowser) obj).unsubscribe(str, (MediaBrowser.SubscriptionCallback) obj2);
    }
}
