package com.android.volley.toolbox;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.MainThread;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
/* loaded from: classes.dex */
public class NetworkImageView extends ImageView {
    public String h;
    public int i;
    public int j;
    public ImageLoader k;
    public ImageLoader.ImageContainer l;

    /* loaded from: classes.dex */
    public class a implements ImageLoader.ImageListener {
        public final /* synthetic */ boolean h;

        /* renamed from: com.android.volley.toolbox.NetworkImageView$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC0201a implements Runnable {
            public final /* synthetic */ ImageLoader.ImageContainer h;

            public RunnableC0201a(ImageLoader.ImageContainer imageContainer) {
                this.h = imageContainer;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.onResponse(this.h, false);
            }
        }

        public a(boolean z) {
            this.h = z;
        }

        @Override // com.android.volley.Response.ErrorListener
        public void onErrorResponse(VolleyError volleyError) {
            if (NetworkImageView.this.j != 0) {
                NetworkImageView networkImageView = NetworkImageView.this;
                networkImageView.setImageResource(networkImageView.j);
            }
        }

        @Override // com.android.volley.toolbox.ImageLoader.ImageListener
        public void onResponse(ImageLoader.ImageContainer imageContainer, boolean z) {
            if (z && this.h) {
                NetworkImageView.this.post(new RunnableC0201a(imageContainer));
            } else if (imageContainer.getBitmap() == null) {
                if (NetworkImageView.this.i != 0) {
                    NetworkImageView networkImageView = NetworkImageView.this;
                    networkImageView.setImageResource(networkImageView.i);
                }
            } else {
                NetworkImageView.this.setImageBitmap(imageContainer.getBitmap());
            }
        }
    }

    public NetworkImageView(Context context) {
        this(context, null);
    }

    public void c(boolean z) {
        boolean z2;
        boolean z3;
        int width = getWidth();
        int height = getHeight();
        ImageView.ScaleType scaleType = getScaleType();
        boolean z4 = true;
        if (getLayoutParams() != null) {
            z2 = getLayoutParams().width == -2;
            z3 = getLayoutParams().height == -2;
        } else {
            z2 = false;
            z3 = false;
        }
        if (!z2 || !z3) {
            z4 = false;
        }
        if (width == 0 && height == 0 && !z4) {
            return;
        }
        if (TextUtils.isEmpty(this.h)) {
            ImageLoader.ImageContainer imageContainer = this.l;
            if (imageContainer != null) {
                imageContainer.cancelRequest();
                this.l = null;
            }
            d();
            return;
        }
        ImageLoader.ImageContainer imageContainer2 = this.l;
        if (imageContainer2 != null && imageContainer2.getRequestUrl() != null) {
            if (this.l.getRequestUrl().equals(this.h)) {
                return;
            }
            this.l.cancelRequest();
            d();
        }
        if (z2) {
            width = 0;
        }
        this.l = this.k.get(this.h, new a(z), width, z3 ? 0 : height, scaleType);
    }

    public final void d() {
        int i = this.i;
        if (i != 0) {
            setImageResource(i);
        } else {
            setImageBitmap(null);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        ImageLoader.ImageContainer imageContainer = this.l;
        if (imageContainer != null) {
            imageContainer.cancelRequest();
            setImageBitmap(null);
            this.l = null;
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        c(true);
    }

    public void setDefaultImageResId(int i) {
        this.i = i;
    }

    public void setErrorImageResId(int i) {
        this.j = i;
    }

    @MainThread
    public void setImageUrl(String str, ImageLoader imageLoader) {
        b.a();
        this.h = str;
        this.k = imageLoader;
        c(false);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
