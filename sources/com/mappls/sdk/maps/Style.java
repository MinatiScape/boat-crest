package com.mappls.sdk.maps;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Pair;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.constants.MapplsConstants;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.TransitionOptions;
import com.mappls.sdk.maps.style.light.Light;
import com.mappls.sdk.maps.style.model.MapplsStyle;
import com.mappls.sdk.maps.style.sources.Source;
import com.mappls.sdk.maps.utils.BitmapUtils;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class Style {

    /* renamed from: a  reason: collision with root package name */
    public final s f12655a;
    public final HashMap<String, Source> b;
    public final HashMap<String, Layer> c;
    public final HashMap<String, Bitmap> d;
    public final Builder e;
    public boolean f;

    /* loaded from: classes11.dex */
    public static class Builder {
        public TransitionOptions d;
        public String f;

        /* renamed from: a  reason: collision with root package name */
        public final List<Source> f12656a = new ArrayList();
        public final List<LayerWrapper> b = new ArrayList();
        public final List<ImageWrapper> c = new ArrayList();
        public String e = f();

        /* loaded from: classes11.dex */
        public static class ImageWrapper {

            /* renamed from: a  reason: collision with root package name */
            public Bitmap f12657a;
            public String b;
            public boolean c;
            public List<ImageStretches> d;
            public List<ImageStretches> e;
            public ImageContent f;

            public ImageWrapper(String str, Bitmap bitmap, boolean z) {
                this(str, bitmap, z, null, null, null);
            }

            public static ImageWrapper[] convertToImageArray(HashMap<String, Bitmap> hashMap, boolean z) {
                ImageWrapper[] imageWrapperArr = new ImageWrapper[hashMap.size()];
                ArrayList arrayList = new ArrayList(hashMap.keySet());
                for (int i = 0; i < hashMap.size(); i++) {
                    String str = (String) arrayList.get(i);
                    imageWrapperArr[i] = new ImageWrapper(str, hashMap.get(str), z);
                }
                return imageWrapperArr;
            }

            public Bitmap getBitmap() {
                return this.f12657a;
            }

            public ImageContent getContent() {
                return this.f;
            }

            public String getId() {
                return this.b;
            }

            public List<ImageStretches> getStretchX() {
                return this.d;
            }

            public List<ImageStretches> getStretchY() {
                return this.e;
            }

            public boolean isSdf() {
                return this.c;
            }

            public ImageWrapper(String str, Bitmap bitmap, boolean z, List<ImageStretches> list, List<ImageStretches> list2, ImageContent imageContent) {
                this.b = str;
                this.f12657a = bitmap;
                this.c = z;
                this.d = list;
                this.e = list2;
                this.f = imageContent;
            }

            public static ImageWrapper[] convertToImageArray(HashMap<String, Bitmap> hashMap, boolean z, List<ImageStretches> list, List<ImageStretches> list2, ImageContent imageContent) {
                ImageWrapper[] imageWrapperArr = new ImageWrapper[hashMap.size()];
                ArrayList arrayList = new ArrayList(hashMap.keySet());
                for (int i = 0; i < hashMap.size(); i++) {
                    String str = (String) arrayList.get(i);
                    imageWrapperArr[i] = new ImageWrapper(str, hashMap.get(str), z, list, list2, imageContent);
                }
                return imageWrapperArr;
            }
        }

        /* loaded from: classes11.dex */
        public class LayerAboveWrapper extends LayerWrapper {
            public String b;

            public LayerAboveWrapper(Builder builder, Layer layer, String str) {
                super(builder, layer);
                this.b = str;
            }

            public String getAboveLayer() {
                return this.b;
            }
        }

        /* loaded from: classes11.dex */
        public class LayerAtWrapper extends LayerWrapper {
            public int b;

            public LayerAtWrapper(Builder builder, Layer layer, int i) {
                super(builder, layer);
                this.b = i;
            }

            public int getIndex() {
                return this.b;
            }
        }

        /* loaded from: classes11.dex */
        public class LayerBelowWrapper extends LayerWrapper {
            public String b;

            public LayerBelowWrapper(Builder builder, Layer layer, String str) {
                super(builder, layer);
                this.b = str;
            }

            public String getBelowLayer() {
                return this.b;
            }
        }

        /* loaded from: classes11.dex */
        public class LayerWrapper {

            /* renamed from: a  reason: collision with root package name */
            public Layer f12658a;

            public LayerWrapper(Builder builder, Layer layer) {
                this.f12658a = layer;
            }

            public Layer getLayer() {
                return this.f12658a;
            }
        }

        public Style e(@NonNull s sVar) {
            return new Style(this, sVar);
        }

        public final String f() {
            if (Mappls.getStyleHelper().getLastSelectedStyle() != null && MapplsMapConfiguration.getInstance().isShowLastSelectedStyle()) {
                return Mappls.getStyleHelper().getLastSelectedStyle().getName();
            }
            MapplsStyle defaultStyle = Mappls.getStyleHelper().getDefaultStyle();
            if (defaultStyle != null) {
                return defaultStyle.getName();
            }
            return null;
        }

        @NonNull
        public Builder fromJson(@NonNull String str) {
            this.f = str;
            this.e = null;
            return this;
        }

        @NonNull
        public Builder fromMapplsStyle(@NonNull String str) {
            this.e = str;
            return this;
        }

        public List<ImageWrapper> getImages() {
            return this.c;
        }

        public String getJson() {
            return this.f;
        }

        public List<LayerWrapper> getLayers() {
            return this.b;
        }

        public String getMapplsStyle() {
            return this.e;
        }

        public List<Source> getSources() {
            return this.f12656a;
        }

        @NonNull
        public Builder withBitmapImages(@NonNull Pair<String, Bitmap>... pairArr) {
            for (Pair<String, Bitmap> pair : pairArr) {
                withImage((String) pair.first, (Bitmap) pair.second, false);
            }
            return this;
        }

        @NonNull
        public Builder withDrawableImages(@NonNull Pair<String, Drawable>... pairArr) {
            return withDrawableImages(false, pairArr);
        }

        @NonNull
        public Builder withImage(@NonNull String str, @NonNull Drawable drawable) {
            Bitmap bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(drawable);
            if (bitmapFromDrawable != null) {
                return withImage(str, bitmapFromDrawable, false);
            }
            throw new IllegalArgumentException("Provided drawable couldn't be converted to a Bitmap.");
        }

        @NonNull
        public Builder withLayer(@NonNull Layer layer) {
            this.b.add(new LayerWrapper(this, layer));
            return this;
        }

        @NonNull
        public Builder withLayerAbove(@NonNull Layer layer, @NonNull String str) {
            this.b.add(new LayerAboveWrapper(this, layer, str));
            return this;
        }

        @NonNull
        public Builder withLayerAt(@NonNull Layer layer, int i) {
            this.b.add(new LayerAtWrapper(this, layer, i));
            return this;
        }

        @NonNull
        public Builder withLayerBelow(@NonNull Layer layer, @NonNull String str) {
            this.b.add(new LayerBelowWrapper(this, layer, str));
            return this;
        }

        @NonNull
        public Builder withLayers(@NonNull Layer... layerArr) {
            for (Layer layer : layerArr) {
                this.b.add(new LayerWrapper(this, layer));
            }
            return this;
        }

        @NonNull
        public Builder withSource(@NonNull Source source) {
            this.f12656a.add(source);
            return this;
        }

        @NonNull
        public Builder withSources(@NonNull Source... sourceArr) {
            this.f12656a.addAll(Arrays.asList(sourceArr));
            return this;
        }

        @NonNull
        public Builder withTransition(@NonNull TransitionOptions transitionOptions) {
            this.d = transitionOptions;
            return this;
        }

        @NonNull
        public Builder withDrawableImages(boolean z, @NonNull Pair<String, Drawable>... pairArr) {
            for (Pair<String, Drawable> pair : pairArr) {
                Bitmap bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable((Drawable) pair.second);
                if (bitmapFromDrawable != null) {
                    withImage((String) pair.first, bitmapFromDrawable, z);
                } else {
                    throw new IllegalArgumentException("Provided drawable couldn't be converted to a Bitmap.");
                }
            }
            return this;
        }

        @NonNull
        public Builder withBitmapImages(boolean z, @NonNull Pair<String, Bitmap>... pairArr) {
            for (Pair<String, Bitmap> pair : pairArr) {
                withImage((String) pair.first, (Bitmap) pair.second, z);
            }
            return this;
        }

        @NonNull
        public Builder withImage(@NonNull String str, @NonNull Drawable drawable, @NonNull List<ImageStretches> list, @NonNull List<ImageStretches> list2, @Nullable ImageContent imageContent) {
            Bitmap bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(drawable);
            if (bitmapFromDrawable != null) {
                return withImage(str, bitmapFromDrawable, false, list, list2, imageContent);
            }
            throw new IllegalArgumentException("Provided drawable couldn't be converted to a Bitmap.");
        }

        @NonNull
        public Builder withImage(@NonNull String str, @NonNull Bitmap bitmap) {
            return withImage(str, bitmap, false);
        }

        @NonNull
        public Builder withImage(@NonNull String str, @NonNull Bitmap bitmap, @NonNull List<ImageStretches> list, @NonNull List<ImageStretches> list2, @Nullable ImageContent imageContent) {
            return withImage(str, bitmap, false, list, list2, imageContent);
        }

        @NonNull
        public Builder withImage(@NonNull String str, @NonNull Drawable drawable, boolean z) {
            Bitmap bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(drawable);
            if (bitmapFromDrawable != null) {
                return withImage(str, bitmapFromDrawable, z);
            }
            throw new IllegalArgumentException("Provided drawable couldn't be converted to a Bitmap.");
        }

        @NonNull
        public Builder withImage(@NonNull String str, @NonNull Drawable drawable, boolean z, @NonNull List<ImageStretches> list, @NonNull List<ImageStretches> list2, @Nullable ImageContent imageContent) {
            Bitmap bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(drawable);
            if (bitmapFromDrawable != null) {
                return withImage(str, bitmapFromDrawable, z, list, list2, imageContent);
            }
            throw new IllegalArgumentException("Provided drawable couldn't be converted to a Bitmap.");
        }

        @NonNull
        public Builder withImage(@NonNull String str, @NonNull Bitmap bitmap, boolean z) {
            this.c.add(new ImageWrapper(str, bitmap, z));
            return this;
        }

        @NonNull
        public Builder withImage(@NonNull String str, @NonNull Bitmap bitmap, boolean z, @NonNull List<ImageStretches> list, @NonNull List<ImageStretches> list2, @Nullable ImageContent imageContent) {
            this.c.add(new ImageWrapper(str, bitmap, z, list, list2, imageContent));
            return this;
        }
    }

    /* loaded from: classes11.dex */
    public interface OnStyleLoaded {
        void onStyleLoaded(@NonNull Style style);
    }

    /* loaded from: classes11.dex */
    public static class b extends AsyncTask<Builder.ImageWrapper, Void, Image[]> {

        /* renamed from: a  reason: collision with root package name */
        public WeakReference<s> f12659a;

        public b(s sVar) {
            this.f12659a = new WeakReference<>(sVar);
        }

        @Override // android.os.AsyncTask
        @NonNull
        /* renamed from: a */
        public Image[] doInBackground(Builder.ImageWrapper... imageWrapperArr) {
            ArrayList arrayList = new ArrayList();
            for (Builder.ImageWrapper imageWrapper : imageWrapperArr) {
                arrayList.add(Style.toImage(imageWrapper));
            }
            return (Image[]) arrayList.toArray(new Image[arrayList.size()]);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b */
        public void onPostExecute(@NonNull Image[] imageArr) {
            super.onPostExecute(imageArr);
            s sVar = this.f12659a.get();
            if (sVar == null || sVar.isDestroyed()) {
                return;
            }
            sVar.F0(imageArr);
        }
    }

    public static Image toImage(Builder.ImageWrapper imageWrapper) {
        Bitmap bitmap = imageWrapper.f12657a;
        Bitmap.Config config = bitmap.getConfig();
        Bitmap.Config config2 = Bitmap.Config.ARGB_8888;
        if (config != config2) {
            bitmap = bitmap.copy(config2, false);
        }
        ByteBuffer allocate = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(allocate);
        float density = bitmap.getDensity() / 160.0f;
        if (imageWrapper.getStretchX() != null && imageWrapper.getStretchY() != null) {
            float[] fArr = new float[imageWrapper.getStretchX().size() * 2];
            for (int i = 0; i < imageWrapper.getStretchX().size(); i++) {
                int i2 = i * 2;
                fArr[i2] = imageWrapper.getStretchX().get(i).getFirst();
                fArr[i2 + 1] = imageWrapper.getStretchX().get(i).getSecond();
            }
            float[] fArr2 = new float[imageWrapper.getStretchY().size() * 2];
            for (int i3 = 0; i3 < imageWrapper.getStretchY().size(); i3++) {
                int i4 = i3 * 2;
                fArr2[i4] = imageWrapper.getStretchY().get(i3).getFirst();
                fArr2[i4 + 1] = imageWrapper.getStretchY().get(i3).getSecond();
            }
            return new Image(allocate.array(), density, imageWrapper.b, bitmap.getWidth(), bitmap.getHeight(), imageWrapper.c, fArr, fArr2, imageWrapper.getContent() == null ? null : imageWrapper.getContent().getContentArray());
        }
        return new Image(allocate.array(), density, imageWrapper.b, bitmap.getWidth(), bitmap.getHeight(), imageWrapper.c);
    }

    public void a() {
        this.f = false;
        for (Layer layer : this.c.values()) {
            if (layer != null) {
                layer.setDetached();
            }
        }
        for (Source source : this.b.values()) {
            if (source != null) {
                source.setDetached();
            }
        }
        for (Map.Entry<String, Bitmap> entry : this.d.entrySet()) {
            this.f12655a.F(entry.getKey());
            entry.getValue().recycle();
        }
        this.b.clear();
        this.c.clear();
        this.d.clear();
    }

    public void addImage(@NonNull String str, @NonNull Bitmap bitmap) {
        addImage(str, bitmap, false);
    }

    public void addImageAsync(@NonNull String str, @NonNull Bitmap bitmap) {
        addImageAsync(str, bitmap, false);
    }

    public void addImages(@NonNull HashMap<String, Bitmap> hashMap) {
        addImages(hashMap, false);
    }

    public void addImagesAsync(@NonNull HashMap<String, Bitmap> hashMap) {
        addImagesAsync(hashMap, false);
    }

    public void addLayer(@NonNull Layer layer) {
        c("addLayer");
        this.f12655a.c0(layer);
        this.c.put(layer.getId(), layer);
    }

    public void addLayerAbove(@NonNull Layer layer, @NonNull String str) {
        c("addLayerAbove");
        this.f12655a.M(layer, str);
        this.c.put(layer.getId(), layer);
    }

    public void addLayerAt(@NonNull Layer layer, @IntRange(from = 0) int i) {
        c("addLayerAbove");
        this.f12655a.h(layer, i);
        this.c.put(layer.getId(), layer);
    }

    public void addLayerBelow(@NonNull Layer layer, @NonNull String str) {
        c("addLayerBelow");
        this.f12655a.d0(layer, str);
        this.c.put(layer.getId(), layer);
    }

    public void addSource(@NonNull Source source) {
        c("addSource");
        this.f12655a.q(source);
        this.b.put(source.getId(), source);
    }

    public void b() {
        if (this.f) {
            return;
        }
        this.f = true;
        for (Source source : this.e.f12656a) {
            addSource(source);
        }
        for (Builder.LayerWrapper layerWrapper : this.e.b) {
            if (layerWrapper instanceof Builder.LayerAtWrapper) {
                addLayerAt(layerWrapper.f12658a, ((Builder.LayerAtWrapper) layerWrapper).b);
            } else if (layerWrapper instanceof Builder.LayerAboveWrapper) {
                addLayerAbove(layerWrapper.f12658a, ((Builder.LayerAboveWrapper) layerWrapper).b);
            } else if (layerWrapper instanceof Builder.LayerBelowWrapper) {
                addLayerBelow(layerWrapper.f12658a, ((Builder.LayerBelowWrapper) layerWrapper).b);
            } else {
                addLayerBelow(layerWrapper.f12658a, MapplsConstants.LAYER_ID_ANNOTATIONS);
            }
        }
        for (Builder.ImageWrapper imageWrapper : this.e.c) {
            addImage(imageWrapper.b, imageWrapper.f12657a, imageWrapper.c);
        }
        if (this.e.d != null) {
            setTransition(this.e.d);
        }
    }

    public final void c(String str) {
        if (!this.f) {
            throw new IllegalStateException(String.format("Calling %s when a newer style is loading/has loaded.", str));
        }
    }

    @Nullable
    public Bitmap getImage(@NonNull String str) {
        c("getImage");
        return this.f12655a.Y(str);
    }

    @NonNull
    public String getJson() {
        c("getJson");
        return this.f12655a.N();
    }

    @Nullable
    public Layer getLayer(@NonNull String str) {
        c("getLayer");
        Layer layer = this.c.get(str);
        return layer == null ? this.f12655a.q0(str) : layer;
    }

    @Nullable
    public <T extends Layer> T getLayerAs(@NonNull String str) {
        c("getLayerAs");
        return (T) this.f12655a.q0(str);
    }

    @NonNull
    public List<Layer> getLayers() {
        c("getLayers");
        return this.f12655a.getLayers();
    }

    @Nullable
    public Light getLight() {
        c("getLight");
        return this.f12655a.X();
    }

    @NonNull
    public String getMapplsStyle() {
        c("getMapplsStyle");
        return this.f12655a.j0();
    }

    @Nullable
    public Source getSource(String str) {
        c("getSource");
        Source source = this.b.get(str);
        return source == null ? this.f12655a.o(str) : source;
    }

    @Nullable
    public <T extends Source> T getSourceAs(@NonNull String str) {
        c("getSourceAs");
        if (this.b.containsKey(str)) {
            return (T) this.b.get(str);
        }
        return (T) this.f12655a.o(str);
    }

    @NonNull
    public List<Source> getSources() {
        c("getSources");
        return this.f12655a.f();
    }

    @NonNull
    public TransitionOptions getTransition() {
        c("getTransition");
        return this.f12655a.c();
    }

    public boolean isFullyLoaded() {
        return this.f;
    }

    public void removeImage(@NonNull String str) {
        c("removeImage");
        this.f12655a.F(str);
    }

    public boolean removeLayer(@NonNull String str) {
        c("removeLayer");
        this.c.remove(str);
        return this.f12655a.s0(str);
    }

    public boolean removeLayerAt(@IntRange(from = 0) int i) {
        c("removeLayerAt");
        return this.f12655a.k(i);
    }

    public boolean removeSource(@NonNull String str) {
        c("removeSource");
        this.b.remove(str);
        return this.f12655a.I(str);
    }

    public void setTransition(@NonNull TransitionOptions transitionOptions) {
        c("setTransition");
        this.f12655a.y0(transitionOptions);
    }

    public Style(@NonNull Builder builder, @NonNull s sVar) {
        this.b = new HashMap<>();
        this.c = new HashMap<>();
        this.d = new HashMap<>();
        this.e = builder;
        this.f12655a = sVar;
    }

    public void addImage(@NonNull String str, @NonNull Bitmap bitmap, @NonNull List<ImageStretches> list, @NonNull List<ImageStretches> list2, @Nullable ImageContent imageContent) {
        addImage(str, bitmap, false, list, list2, imageContent);
    }

    public void addImageAsync(@NonNull String str, @NonNull Bitmap bitmap, @NonNull List<ImageStretches> list, @NonNull List<ImageStretches> list2, @Nullable ImageContent imageContent) {
        addImageAsync(str, bitmap, false, list, list2, imageContent);
    }

    public void addImages(@NonNull HashMap<String, Bitmap> hashMap, @NonNull List<ImageStretches> list, @NonNull List<ImageStretches> list2, @Nullable ImageContent imageContent) {
        addImages(hashMap, false, list, list2, imageContent);
    }

    public void addImagesAsync(@NonNull HashMap<String, Bitmap> hashMap, @NonNull List<ImageStretches> list, @NonNull List<ImageStretches> list2, @Nullable ImageContent imageContent) {
        addImagesAsync(hashMap, false, list, list2, imageContent);
    }

    public void addImage(@NonNull String str, @NonNull Drawable drawable) {
        Bitmap bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(drawable);
        if (bitmapFromDrawable != null) {
            addImage(str, bitmapFromDrawable, false);
            return;
        }
        throw new IllegalArgumentException("Provided drawable couldn't be converted to a Bitmap.");
    }

    public void addImageAsync(@NonNull String str, @NonNull Drawable drawable) {
        Bitmap bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(drawable);
        if (bitmapFromDrawable != null) {
            addImageAsync(str, bitmapFromDrawable, false);
            return;
        }
        throw new IllegalArgumentException("Provided drawable couldn't be converted to a Bitmap.");
    }

    public void addImages(@NonNull HashMap<String, Bitmap> hashMap, boolean z) {
        c("addImage");
        Image[] imageArr = new Image[hashMap.size()];
        int i = 0;
        for (Builder.ImageWrapper imageWrapper : Builder.ImageWrapper.convertToImageArray(hashMap, z)) {
            imageArr[i] = toImage(imageWrapper);
            i++;
        }
        this.f12655a.F0(imageArr);
    }

    public void addImagesAsync(@NonNull HashMap<String, Bitmap> hashMap, boolean z) {
        c("addImages");
        new b(this.f12655a).execute(Builder.ImageWrapper.convertToImageArray(hashMap, z));
    }

    public boolean removeLayer(@NonNull Layer layer) {
        c("removeLayer");
        this.c.remove(layer.getId());
        return this.f12655a.a(layer);
    }

    public boolean removeSource(@NonNull Source source) {
        c("removeSource");
        this.b.remove(source.getId());
        return this.f12655a.n(source);
    }

    public void addImagesAsync(@NonNull HashMap<String, Bitmap> hashMap, boolean z, @NonNull List<ImageStretches> list, @NonNull List<ImageStretches> list2, @Nullable ImageContent imageContent) {
        c("addImages");
        new b(this.f12655a).execute(Builder.ImageWrapper.convertToImageArray(hashMap, z, list, list2, imageContent));
    }

    public void addImage(@NonNull String str, @NonNull Drawable drawable, @NonNull List<ImageStretches> list, @NonNull List<ImageStretches> list2, @Nullable ImageContent imageContent) {
        Bitmap bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(drawable);
        if (bitmapFromDrawable != null) {
            addImage(str, bitmapFromDrawable, false, list, list2, imageContent);
            return;
        }
        throw new IllegalArgumentException("Provided drawable couldn't be converted to a Bitmap.");
    }

    public void addImageAsync(@NonNull String str, @NonNull Drawable drawable, @NonNull List<ImageStretches> list, @NonNull List<ImageStretches> list2, @Nullable ImageContent imageContent) {
        Bitmap bitmapFromDrawable = BitmapUtils.getBitmapFromDrawable(drawable);
        if (bitmapFromDrawable != null) {
            addImageAsync(str, bitmapFromDrawable, false, list, list2, imageContent);
            return;
        }
        throw new IllegalArgumentException("Provided drawable couldn't be converted to a Bitmap.");
    }

    public void addImages(@NonNull HashMap<String, Bitmap> hashMap, boolean z, @NonNull List<ImageStretches> list, @NonNull List<ImageStretches> list2, @Nullable ImageContent imageContent) {
        c("addImage");
        Image[] imageArr = new Image[hashMap.size()];
        Builder.ImageWrapper[] convertToImageArray = Builder.ImageWrapper.convertToImageArray(hashMap, z, list, list2, imageContent);
        int i = 0;
        for (Builder.ImageWrapper imageWrapper : convertToImageArray) {
            imageArr[i] = toImage(imageWrapper);
            i++;
        }
        this.f12655a.F0(imageArr);
    }

    public void addImage(@NonNull String str, @NonNull Bitmap bitmap, boolean z) {
        c("addImage");
        this.f12655a.F0(new Image[]{toImage(new Builder.ImageWrapper(str, bitmap, z))});
    }

    public void addImageAsync(@NonNull String str, @NonNull Bitmap bitmap, boolean z) {
        c("addImage");
        new b(this.f12655a).execute(new Builder.ImageWrapper(str, bitmap, z));
    }

    public void addImage(@NonNull String str, @NonNull Bitmap bitmap, boolean z, @NonNull List<ImageStretches> list, @NonNull List<ImageStretches> list2, @Nullable ImageContent imageContent) {
        c("addImage");
        this.f12655a.F0(new Image[]{toImage(new Builder.ImageWrapper(str, bitmap, z, list, list2, imageContent))});
    }

    public void addImageAsync(@NonNull String str, @NonNull Bitmap bitmap, boolean z, @NonNull List<ImageStretches> list, @NonNull List<ImageStretches> list2, @Nullable ImageContent imageContent) {
        c("addImage");
        new b(this.f12655a).execute(new Builder.ImageWrapper(str, bitmap, z, list, list2, imageContent));
    }
}
