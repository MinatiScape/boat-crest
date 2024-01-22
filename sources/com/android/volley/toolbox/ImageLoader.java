package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import androidx.annotation.MainThread;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes.dex */
public class ImageLoader {

    /* renamed from: a  reason: collision with root package name */
    public final RequestQueue f2159a;
    public final ImageCache c;
    public Runnable g;
    public int b = 100;
    public final HashMap<String, e> d = new HashMap<>();
    public final HashMap<String, e> e = new HashMap<>();
    public final Handler f = new Handler(Looper.getMainLooper());

    /* loaded from: classes.dex */
    public interface ImageCache {
        Bitmap getBitmap(String str);

        void putBitmap(String str, Bitmap bitmap);
    }

    /* loaded from: classes.dex */
    public class ImageContainer {

        /* renamed from: a  reason: collision with root package name */
        public Bitmap f2160a;
        public final ImageListener b;
        public final String c;
        public final String d;

        public ImageContainer(Bitmap bitmap, String str, String str2, ImageListener imageListener) {
            this.f2160a = bitmap;
            this.d = str;
            this.c = str2;
            this.b = imageListener;
        }

        @MainThread
        public void cancelRequest() {
            com.android.volley.toolbox.b.a();
            if (this.b == null) {
                return;
            }
            e eVar = (e) ImageLoader.this.d.get(this.c);
            if (eVar == null) {
                e eVar2 = (e) ImageLoader.this.e.get(this.c);
                if (eVar2 != null) {
                    eVar2.f(this);
                    if (eVar2.d.size() == 0) {
                        ImageLoader.this.e.remove(this.c);
                    }
                }
            } else if (eVar.f(this)) {
                ImageLoader.this.d.remove(this.c);
            }
        }

        public Bitmap getBitmap() {
            return this.f2160a;
        }

        public String getRequestUrl() {
            return this.d;
        }
    }

    /* loaded from: classes.dex */
    public interface ImageListener extends Response.ErrorListener {
        void onResponse(ImageContainer imageContainer, boolean z);
    }

    /* loaded from: classes.dex */
    public class a implements ImageListener {
        public final /* synthetic */ int h;
        public final /* synthetic */ ImageView i;
        public final /* synthetic */ int j;

        public a(int i, ImageView imageView, int i2) {
            this.h = i;
            this.i = imageView;
            this.j = i2;
        }

        @Override // com.android.volley.Response.ErrorListener
        public void onErrorResponse(VolleyError volleyError) {
            int i = this.h;
            if (i != 0) {
                this.i.setImageResource(i);
            }
        }

        @Override // com.android.volley.toolbox.ImageLoader.ImageListener
        public void onResponse(ImageContainer imageContainer, boolean z) {
            if (imageContainer.getBitmap() != null) {
                this.i.setImageBitmap(imageContainer.getBitmap());
                return;
            }
            int i = this.j;
            if (i != 0) {
                this.i.setImageResource(i);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements Response.Listener<Bitmap> {
        public final /* synthetic */ String h;

        public b(String str) {
            this.h = str;
        }

        @Override // com.android.volley.Response.Listener
        /* renamed from: a */
        public void onResponse(Bitmap bitmap) {
            ImageLoader.this.onGetImageSuccess(this.h, bitmap);
        }
    }

    /* loaded from: classes.dex */
    public class c implements Response.ErrorListener {
        public final /* synthetic */ String h;

        public c(String str) {
            this.h = str;
        }

        @Override // com.android.volley.Response.ErrorListener
        public void onErrorResponse(VolleyError volleyError) {
            ImageLoader.this.onGetImageError(this.h, volleyError);
        }
    }

    /* loaded from: classes.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            for (e eVar : ImageLoader.this.e.values()) {
                for (ImageContainer imageContainer : eVar.d) {
                    if (imageContainer.b != null) {
                        if (eVar.e() == null) {
                            imageContainer.f2160a = eVar.b;
                            imageContainer.b.onResponse(imageContainer, false);
                        } else {
                            imageContainer.b.onErrorResponse(eVar.e());
                        }
                    }
                }
            }
            ImageLoader.this.e.clear();
            ImageLoader.this.g = null;
        }
    }

    /* loaded from: classes.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        public final Request<?> f2161a;
        public Bitmap b;
        public VolleyError c;
        public final List<ImageContainer> d;

        public e(Request<?> request, ImageContainer imageContainer) {
            ArrayList arrayList = new ArrayList();
            this.d = arrayList;
            this.f2161a = request;
            arrayList.add(imageContainer);
        }

        public void d(ImageContainer imageContainer) {
            this.d.add(imageContainer);
        }

        public VolleyError e() {
            return this.c;
        }

        public boolean f(ImageContainer imageContainer) {
            this.d.remove(imageContainer);
            if (this.d.size() == 0) {
                this.f2161a.cancel();
                return true;
            }
            return false;
        }

        public void g(VolleyError volleyError) {
            this.c = volleyError;
        }
    }

    public ImageLoader(RequestQueue requestQueue, ImageCache imageCache) {
        this.f2159a = requestQueue;
        this.c = imageCache;
    }

    public static String e(String str, int i, int i2, ImageView.ScaleType scaleType) {
        StringBuilder sb = new StringBuilder(str.length() + 12);
        sb.append("#W");
        sb.append(i);
        sb.append("#H");
        sb.append(i2);
        sb.append("#S");
        sb.append(scaleType.ordinal());
        sb.append(str);
        return sb.toString();
    }

    public static ImageListener getImageListener(ImageView imageView, int i, int i2) {
        return new a(i2, imageView, i);
    }

    public final void d(String str, e eVar) {
        this.e.put(str, eVar);
        if (this.g == null) {
            d dVar = new d();
            this.g = dVar;
            this.f.postDelayed(dVar, this.b);
        }
    }

    public ImageContainer get(String str, ImageListener imageListener) {
        return get(str, imageListener, 0, 0);
    }

    public boolean isCached(String str, int i, int i2) {
        return isCached(str, i, i2, ImageView.ScaleType.CENTER_INSIDE);
    }

    public Request<Bitmap> makeImageRequest(String str, int i, int i2, ImageView.ScaleType scaleType, String str2) {
        return new ImageRequest(str, new b(str2), i, i2, scaleType, Bitmap.Config.RGB_565, new c(str2));
    }

    public void onGetImageError(String str, VolleyError volleyError) {
        e remove = this.d.remove(str);
        if (remove != null) {
            remove.g(volleyError);
            d(str, remove);
        }
    }

    public void onGetImageSuccess(String str, Bitmap bitmap) {
        this.c.putBitmap(str, bitmap);
        e remove = this.d.remove(str);
        if (remove != null) {
            remove.b = bitmap;
            d(str, remove);
        }
    }

    public void setBatchedResponseDelay(int i) {
        this.b = i;
    }

    public ImageContainer get(String str, ImageListener imageListener, int i, int i2) {
        return get(str, imageListener, i, i2, ImageView.ScaleType.CENTER_INSIDE);
    }

    @MainThread
    public boolean isCached(String str, int i, int i2, ImageView.ScaleType scaleType) {
        com.android.volley.toolbox.b.a();
        return this.c.getBitmap(e(str, i, i2, scaleType)) != null;
    }

    @MainThread
    public ImageContainer get(String str, ImageListener imageListener, int i, int i2, ImageView.ScaleType scaleType) {
        com.android.volley.toolbox.b.a();
        String e2 = e(str, i, i2, scaleType);
        Bitmap bitmap = this.c.getBitmap(e2);
        if (bitmap != null) {
            ImageContainer imageContainer = new ImageContainer(bitmap, str, null, null);
            imageListener.onResponse(imageContainer, true);
            return imageContainer;
        }
        ImageContainer imageContainer2 = new ImageContainer(null, str, e2, imageListener);
        imageListener.onResponse(imageContainer2, true);
        e eVar = this.d.get(e2);
        if (eVar != null) {
            eVar.d(imageContainer2);
            return imageContainer2;
        }
        Request<Bitmap> makeImageRequest = makeImageRequest(str, i, i2, scaleType, e2);
        this.f2159a.add(makeImageRequest);
        this.d.put(e2, new e(makeImageRequest, imageContainer2));
        return imageContainer2;
    }
}
