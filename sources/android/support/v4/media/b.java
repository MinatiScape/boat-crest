package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.os.Parcel;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
@RequiresApi(23)
/* loaded from: classes.dex */
public class b {

    /* loaded from: classes.dex */
    public interface a {
        void a(Parcel parcel);

        void onError(@NonNull String str);
    }

    /* renamed from: android.support.v4.media.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0103b<T extends a> extends MediaBrowser.ItemCallback {

        /* renamed from: a  reason: collision with root package name */
        public final T f310a;

        public C0103b(T t) {
            this.f310a = t;
        }

        @Override // android.media.browse.MediaBrowser.ItemCallback
        public void onError(@NonNull String str) {
            this.f310a.onError(str);
        }

        @Override // android.media.browse.MediaBrowser.ItemCallback
        public void onItemLoaded(MediaBrowser.MediaItem mediaItem) {
            if (mediaItem == null) {
                this.f310a.a(null);
                return;
            }
            Parcel obtain = Parcel.obtain();
            mediaItem.writeToParcel(obtain, 0);
            this.f310a.a(obtain);
        }
    }

    public static Object a(a aVar) {
        return new C0103b(aVar);
    }

    public static void b(Object obj, String str, Object obj2) {
        ((MediaBrowser) obj).getItem(str, (MediaBrowser.ItemCallback) obj2);
    }
}
