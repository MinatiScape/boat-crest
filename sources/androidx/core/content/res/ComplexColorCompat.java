package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public final class ComplexColorCompat {

    /* renamed from: a  reason: collision with root package name */
    public final Shader f1021a;
    public final ColorStateList b;
    public int c;

    public ComplexColorCompat(Shader shader, ColorStateList colorStateList, @ColorInt int i) {
        this.f1021a = shader;
        this.b = colorStateList;
        this.c = i;
    }

    @NonNull
    public static ComplexColorCompat a(@NonNull Resources resources, @ColorRes int i, @Nullable Resources.Theme theme) throws IOException, XmlPullParserException {
        int next;
        XmlResourceParser xml = resources.getXml(i);
        AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
        do {
            next = xml.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            String name = xml.getName();
            name.hashCode();
            if (name.equals("gradient")) {
                return d(c.b(resources, xml, asAttributeSet, theme));
            }
            if (name.equals("selector")) {
                return c(ColorStateListInflaterCompat.createFromXmlInner(resources, xml, asAttributeSet, theme));
            }
            throw new XmlPullParserException(xml.getPositionDescription() + ": unsupported complex color tag " + name);
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static ComplexColorCompat b(@ColorInt int i) {
        return new ComplexColorCompat(null, null, i);
    }

    public static ComplexColorCompat c(@NonNull ColorStateList colorStateList) {
        return new ComplexColorCompat(null, colorStateList, colorStateList.getDefaultColor());
    }

    public static ComplexColorCompat d(@NonNull Shader shader) {
        return new ComplexColorCompat(shader, null, 0);
    }

    @Nullable
    public static ComplexColorCompat inflate(@NonNull Resources resources, @ColorRes int i, @Nullable Resources.Theme theme) {
        try {
            return a(resources, i, theme);
        } catch (Exception e) {
            Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e);
            return null;
        }
    }

    @ColorInt
    public int getColor() {
        return this.c;
    }

    @Nullable
    public Shader getShader() {
        return this.f1021a;
    }

    public boolean isGradient() {
        return this.f1021a != null;
    }

    public boolean isStateful() {
        ColorStateList colorStateList;
        return this.f1021a == null && (colorStateList = this.b) != null && colorStateList.isStateful();
    }

    public boolean onStateChanged(int[] iArr) {
        if (isStateful()) {
            ColorStateList colorStateList = this.b;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.c) {
                this.c = colorForState;
                return true;
            }
        }
        return false;
    }

    public void setColor(@ColorInt int i) {
        this.c = i;
    }

    public boolean willDraw() {
        return isGradient() || this.c != 0;
    }
}
