package com.mappls.sdk.maps.style.layers;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.JsonArray;
import com.mappls.sdk.maps.MapStrictMode;
import com.mappls.sdk.maps.exceptions.ConversionException;
import com.mappls.sdk.maps.log.Logger;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.utils.ColorUtils;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class PropertyValue<T> {
    @NonNull
    public final String name;
    public final T value;

    public PropertyValue(@NonNull String str, T t) {
        this.name = str;
        this.value = t;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PropertyValue propertyValue = (PropertyValue) obj;
        if (this.name.equals(propertyValue.name)) {
            T t = this.value;
            if (t == null) {
                return propertyValue.value == null;
            } else if (t instanceof Object[]) {
                return Arrays.deepEquals((Object[]) t, (Object[]) propertyValue.value);
            } else {
                return t.equals(propertyValue.value);
            }
        }
        return false;
    }

    @Nullable
    @ColorInt
    public Integer getColorInt() {
        if (isValue()) {
            T t = this.value;
            if (t instanceof String) {
                try {
                    return Integer.valueOf(ColorUtils.rgbaToColor((String) t));
                } catch (ConversionException e) {
                    Logger.e("Mbgl-PropertyValue", String.format("%s could not be converted to a Color int: %s", this.name, e.getMessage()));
                    MapStrictMode.strictModeViolation(e);
                    return null;
                }
            }
        }
        Logger.e("Mbgl-PropertyValue", String.format("%s is not a String value and can not be converted to a color it", this.name));
        return null;
    }

    @Nullable
    public Expression getExpression() {
        if (!isExpression()) {
            Logger.w("Mbgl-PropertyValue", String.format("%s not an expression, try PropertyValue#getValue()", this.name));
            return null;
        }
        T t = this.value;
        if (t instanceof JsonArray) {
            return Expression.Converter.convert((JsonArray) t);
        }
        return (Expression) t;
    }

    @Nullable
    public T getValue() {
        if (isValue()) {
            return this.value;
        }
        Logger.w("Mbgl-PropertyValue", String.format("%s not a value, try PropertyValue#getExpression()", this.name));
        return null;
    }

    public int hashCode() {
        int hashCode = this.name.hashCode() * 31;
        T t = this.value;
        return hashCode + (t != null ? t.hashCode() : 0);
    }

    public boolean isExpression() {
        if (!isNull()) {
            T t = this.value;
            if ((t instanceof JsonArray) || (t instanceof Expression)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNull() {
        return this.value == null;
    }

    public boolean isValue() {
        return (isNull() || isExpression()) ? false : true;
    }

    public String toString() {
        return String.format("%s: %s", this.name, this.value);
    }
}
