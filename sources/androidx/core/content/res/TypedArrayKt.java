package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import androidx.annotation.AnyRes;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.RequiresApi;
import androidx.annotation.StyleableRes;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class TypedArrayKt {
    public static final void a(TypedArray typedArray, @StyleableRes int i) {
        if (!typedArray.hasValue(i)) {
            throw new IllegalArgumentException("Attribute not defined in set.");
        }
    }

    public static final boolean getBooleanOrThrow(@NotNull TypedArray typedArray, @StyleableRes int i) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        a(typedArray, i);
        return typedArray.getBoolean(i, false);
    }

    @ColorInt
    public static final int getColorOrThrow(@NotNull TypedArray typedArray, @StyleableRes int i) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        a(typedArray, i);
        return typedArray.getColor(i, 0);
    }

    @NotNull
    public static final ColorStateList getColorStateListOrThrow(@NotNull TypedArray typedArray, @StyleableRes int i) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        a(typedArray, i);
        ColorStateList colorStateList = typedArray.getColorStateList(i);
        if (colorStateList != null) {
            return colorStateList;
        }
        throw new IllegalStateException("Attribute value was not a color or color state list.".toString());
    }

    public static final float getDimensionOrThrow(@NotNull TypedArray typedArray, @StyleableRes int i) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        a(typedArray, i);
        return typedArray.getDimension(i, 0.0f);
    }

    @Dimension
    public static final int getDimensionPixelOffsetOrThrow(@NotNull TypedArray typedArray, @StyleableRes int i) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        a(typedArray, i);
        return typedArray.getDimensionPixelOffset(i, 0);
    }

    @Dimension
    public static final int getDimensionPixelSizeOrThrow(@NotNull TypedArray typedArray, @StyleableRes int i) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        a(typedArray, i);
        return typedArray.getDimensionPixelSize(i, 0);
    }

    @NotNull
    public static final Drawable getDrawableOrThrow(@NotNull TypedArray typedArray, @StyleableRes int i) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        a(typedArray, i);
        Drawable drawable = typedArray.getDrawable(i);
        Intrinsics.checkNotNull(drawable);
        return drawable;
    }

    public static final float getFloatOrThrow(@NotNull TypedArray typedArray, @StyleableRes int i) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        a(typedArray, i);
        return typedArray.getFloat(i, 0.0f);
    }

    @RequiresApi(26)
    @NotNull
    public static final Typeface getFontOrThrow(@NotNull TypedArray typedArray, @StyleableRes int i) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        a(typedArray, i);
        return g.a(typedArray, i);
    }

    public static final int getIntOrThrow(@NotNull TypedArray typedArray, @StyleableRes int i) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        a(typedArray, i);
        return typedArray.getInt(i, 0);
    }

    public static final int getIntegerOrThrow(@NotNull TypedArray typedArray, @StyleableRes int i) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        a(typedArray, i);
        return typedArray.getInteger(i, 0);
    }

    @AnyRes
    public static final int getResourceIdOrThrow(@NotNull TypedArray typedArray, @StyleableRes int i) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        a(typedArray, i);
        return typedArray.getResourceId(i, 0);
    }

    @NotNull
    public static final String getStringOrThrow(@NotNull TypedArray typedArray, @StyleableRes int i) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        a(typedArray, i);
        String string = typedArray.getString(i);
        if (string != null) {
            return string;
        }
        throw new IllegalStateException("Attribute value could not be coerced to String.".toString());
    }

    @NotNull
    public static final CharSequence[] getTextArrayOrThrow(@NotNull TypedArray typedArray, @StyleableRes int i) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        a(typedArray, i);
        CharSequence[] textArray = typedArray.getTextArray(i);
        Intrinsics.checkNotNullExpressionValue(textArray, "getTextArray(index)");
        return textArray;
    }

    @NotNull
    public static final CharSequence getTextOrThrow(@NotNull TypedArray typedArray, @StyleableRes int i) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        a(typedArray, i);
        CharSequence text = typedArray.getText(i);
        if (text != null) {
            return text;
        }
        throw new IllegalStateException("Attribute value could not be coerced to CharSequence.".toString());
    }

    public static final <R> R use(@NotNull TypedArray typedArray, @NotNull Function1<? super TypedArray, ? extends R> block) {
        Intrinsics.checkNotNullParameter(typedArray, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        R invoke = block.invoke(typedArray);
        typedArray.recycle();
        return invoke;
    }
}
