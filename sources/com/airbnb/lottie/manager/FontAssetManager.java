package com.airbnb.lottie.manager;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.Nullable;
import com.airbnb.lottie.FontAssetDelegate;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.MutablePair;
import com.airbnb.lottie.utils.Logger;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class FontAssetManager {
    public final AssetManager d;
    @Nullable
    public FontAssetDelegate e;

    /* renamed from: a  reason: collision with root package name */
    public final MutablePair<String> f2021a = new MutablePair<>();
    public final Map<MutablePair<String>, Typeface> b = new HashMap();
    public final Map<String, Typeface> c = new HashMap();
    public String f = ".ttf";

    public FontAssetManager(Drawable.Callback callback, @Nullable FontAssetDelegate fontAssetDelegate) {
        this.e = fontAssetDelegate;
        if (!(callback instanceof View)) {
            Logger.warning("LottieDrawable must be inside of a view for images to work.");
            this.d = null;
            return;
        }
        this.d = ((View) callback).getContext().getAssets();
    }

    public final Typeface a(Font font) {
        String family = font.getFamily();
        Typeface typeface = this.c.get(family);
        if (typeface != null) {
            return typeface;
        }
        Typeface typeface2 = null;
        String style = font.getStyle();
        String name = font.getName();
        FontAssetDelegate fontAssetDelegate = this.e;
        if (fontAssetDelegate != null && (typeface2 = fontAssetDelegate.fetchFont(family, style, name)) == null) {
            typeface2 = this.e.fetchFont(family);
        }
        FontAssetDelegate fontAssetDelegate2 = this.e;
        if (fontAssetDelegate2 != null && typeface2 == null) {
            String fontPath = fontAssetDelegate2.getFontPath(family, style, name);
            if (fontPath == null) {
                fontPath = this.e.getFontPath(family);
            }
            if (fontPath != null) {
                typeface2 = Typeface.createFromAsset(this.d, fontPath);
            }
        }
        if (font.getTypeface() != null) {
            return font.getTypeface();
        }
        if (typeface2 == null) {
            typeface2 = Typeface.createFromAsset(this.d, "fonts/" + family + this.f);
        }
        this.c.put(family, typeface2);
        return typeface2;
    }

    public final Typeface b(Typeface typeface, String str) {
        boolean contains = str.contains("Italic");
        boolean contains2 = str.contains("Bold");
        int i = (contains && contains2) ? 3 : contains ? 2 : contains2 ? 1 : 0;
        return typeface.getStyle() == i ? typeface : Typeface.create(typeface, i);
    }

    public Typeface getTypeface(Font font) {
        this.f2021a.set(font.getFamily(), font.getStyle());
        Typeface typeface = this.b.get(this.f2021a);
        if (typeface != null) {
            return typeface;
        }
        Typeface b = b(a(font), font.getStyle());
        this.b.put(this.f2021a, b);
        return b;
    }

    public void setDefaultFontFileExtension(String str) {
        this.f = str;
    }

    public void setDelegate(@Nullable FontAssetDelegate fontAssetDelegate) {
        this.e = fontAssetDelegate;
    }
}
