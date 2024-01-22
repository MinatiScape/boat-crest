package com.mappls.sdk.maps.style.expressions;

import android.annotation.SuppressLint;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.geojson.GeoJson;
import com.mappls.sdk.geojson.Polygon;
import com.mappls.sdk.geojson.gson.GeometryGeoJson;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.maps.style.layers.PropertyValue;
import com.mappls.sdk.maps.utils.ColorUtils;
import com.mappls.sdk.plugin.annotation.SymbolOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jose4j.jwk.RsaJsonWebKey;
import org.slf4j.Marker;
/* loaded from: classes11.dex */
public class Expression {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final String f12843a;
    @Nullable
    public final Expression[] b;

    /* loaded from: classes11.dex */
    public static class Array {
    }

    /* loaded from: classes11.dex */
    public static class ExpressionLiteral extends Expression implements d {
        public Object literal;

        public ExpressionLiteral(@NonNull Object obj) {
            if (obj instanceof String) {
                obj = c((String) obj);
            } else if (obj instanceof Number) {
                obj = Float.valueOf(((Number) obj).floatValue());
            }
            this.literal = obj;
        }

        @NonNull
        public static String c(String str) {
            return (str.length() > 1 && str.charAt(0) == '\"' && str.charAt(str.length() - 1) == '\"') ? str.substring(1, str.length() - 1) : str;
        }

        @Override // com.mappls.sdk.maps.style.expressions.Expression
        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass() && super.equals(obj)) {
                Object obj2 = this.literal;
                Object obj3 = ((ExpressionLiteral) obj).literal;
                return obj2 != null ? obj2.equals(obj3) : obj3 == null;
            }
            return false;
        }

        @Override // com.mappls.sdk.maps.style.expressions.Expression
        public int hashCode() {
            int hashCode = super.hashCode() * 31;
            Object obj = this.literal;
            return hashCode + (obj != null ? obj.hashCode() : 0);
        }

        @Override // com.mappls.sdk.maps.style.expressions.Expression
        @NonNull
        public Object[] toArray() {
            return new Object[]{"literal", this.literal};
        }

        @Override // com.mappls.sdk.maps.style.expressions.Expression
        public String toString() {
            Object obj = this.literal;
            if (obj instanceof String) {
                return "\"" + this.literal + "\"";
            }
            return obj.toString();
        }

        @Override // com.mappls.sdk.maps.style.expressions.Expression.d
        public Object toValue() {
            Object obj = this.literal;
            if (obj instanceof PropertyValue) {
                throw new IllegalArgumentException("PropertyValue are not allowed as an expression literal, use value instead.");
            }
            return obj instanceof ExpressionLiteral ? ((ExpressionLiteral) obj).toValue() : obj;
        }
    }

    /* loaded from: classes11.dex */
    public static class FormatEntry {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public Expression f12845a;
        @Nullable
        public FormatOption[] b;

        public FormatEntry(@NonNull Expression expression, @Nullable FormatOption[] formatOptionArr) {
            this.f12845a = expression;
            this.b = formatOptionArr;
        }
    }

    /* loaded from: classes11.dex */
    public static class FormatOption extends c {
        public FormatOption(@NonNull String str, @NonNull Expression expression) {
            super(str, expression);
        }

        @NonNull
        public static FormatOption formatFontScale(@NonNull Expression expression) {
            return new FormatOption("font-scale", expression);
        }

        @NonNull
        public static FormatOption formatTextColor(@NonNull Expression expression) {
            return new FormatOption(SymbolOptions.PROPERTY_TEXT_COLOR, expression);
        }

        @NonNull
        public static FormatOption formatTextFont(@NonNull Expression expression) {
            return new FormatOption(SymbolOptions.PROPERTY_TEXT_FONT, expression);
        }

        @NonNull
        public static FormatOption formatFontScale(double d) {
            return new FormatOption("font-scale", Expression.literal((Number) Double.valueOf(d)));
        }

        @NonNull
        public static FormatOption formatTextColor(@ColorInt int i) {
            return new FormatOption(SymbolOptions.PROPERTY_TEXT_COLOR, Expression.color(i));
        }

        @NonNull
        public static FormatOption formatTextFont(@NonNull String[] strArr) {
            return new FormatOption(SymbolOptions.PROPERTY_TEXT_FONT, Expression.literal((Object[]) strArr));
        }
    }

    /* loaded from: classes11.dex */
    public static class Interpolator extends Expression {
        public Interpolator(@NonNull String str, @Nullable Expression... expressionArr) {
            super(str, expressionArr);
        }
    }

    /* loaded from: classes11.dex */
    public static class NumberFormatOption extends c {
        public NumberFormatOption(@NonNull String str, @NonNull Expression expression) {
            super(str, expression);
        }

        @NonNull
        public static NumberFormatOption currency(@NonNull Expression expression) {
            return new NumberFormatOption(FirebaseAnalytics.Param.CURRENCY, expression);
        }

        @NonNull
        public static NumberFormatOption locale(@NonNull Expression expression) {
            return new NumberFormatOption("locale", expression);
        }

        @NonNull
        public static NumberFormatOption maxFractionDigits(@NonNull Expression expression) {
            return new NumberFormatOption("max-fraction-digits", expression);
        }

        @NonNull
        public static NumberFormatOption minFractionDigits(@NonNull Expression expression) {
            return new NumberFormatOption("min-fraction-digits", expression);
        }

        @NonNull
        public static NumberFormatOption currency(@NonNull String str) {
            return new NumberFormatOption(FirebaseAnalytics.Param.CURRENCY, Expression.literal(str));
        }

        @NonNull
        public static NumberFormatOption locale(@NonNull String str) {
            return new NumberFormatOption("locale", Expression.literal(str));
        }

        @NonNull
        public static NumberFormatOption maxFractionDigits(@NonNull int i) {
            return new NumberFormatOption("max-fraction-digits", Expression.literal((Number) Integer.valueOf(i)));
        }

        @NonNull
        public static NumberFormatOption minFractionDigits(int i) {
            return new NumberFormatOption("min-fraction-digits", Expression.literal((Number) Integer.valueOf(i)));
        }
    }

    /* loaded from: classes11.dex */
    public static class Stop {

        /* renamed from: a  reason: collision with root package name */
        public Object f12846a;
        public Object b;

        public Stop(Object obj, Object obj2) {
            this.f12846a = obj;
            this.b = obj2;
        }

        @NonNull
        public static Expression[] a(Stop... stopArr) {
            Expression[] expressionArr = new Expression[stopArr.length * 2];
            for (int i = 0; i < stopArr.length; i++) {
                Stop stop = stopArr[i];
                Object obj = stop.f12846a;
                Object obj2 = stop.b;
                if (!(obj instanceof Expression)) {
                    obj = Expression.literal(obj);
                }
                if (!(obj2 instanceof Expression)) {
                    obj2 = Expression.literal(obj2);
                }
                int i2 = i * 2;
                expressionArr[i2] = (Expression) obj;
                expressionArr[i2 + 1] = (Expression) obj2;
            }
            return expressionArr;
        }
    }

    /* loaded from: classes11.dex */
    public static class a extends ExpressionLiteral {
        public a(@NonNull Object[] objArr) {
            super(objArr);
        }

        @Override // com.mappls.sdk.maps.style.expressions.Expression.ExpressionLiteral, com.mappls.sdk.maps.style.expressions.Expression
        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || a.class != obj.getClass()) {
                return false;
            }
            return Arrays.equals((Object[]) this.literal, (Object[]) ((a) obj).literal);
        }

        @Override // com.mappls.sdk.maps.style.expressions.Expression.ExpressionLiteral, com.mappls.sdk.maps.style.expressions.Expression
        @NonNull
        public String toString() {
            Object[] objArr = (Object[]) this.literal;
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < objArr.length; i++) {
                Object obj = objArr[i];
                if (obj instanceof String) {
                    sb.append("\"");
                    sb.append(obj);
                    sb.append("\"");
                } else {
                    sb.append(obj);
                }
                if (i != objArr.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }

    /* loaded from: classes11.dex */
    public static class b extends Expression implements d {
        public Map<String, Expression> c;

        public b(Map<String, Expression> map) {
            this.c = map;
        }

        @Override // com.mappls.sdk.maps.style.expressions.Expression
        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && b.class == obj.getClass() && super.equals(obj)) {
                return this.c.equals(((b) obj).c);
            }
            return false;
        }

        @Override // com.mappls.sdk.maps.style.expressions.Expression
        public int hashCode() {
            int hashCode = super.hashCode() * 31;
            Map<String, Expression> map = this.c;
            return hashCode + (map == null ? 0 : map.hashCode());
        }

        @Override // com.mappls.sdk.maps.style.expressions.Expression
        @NonNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            for (String str : this.c.keySet()) {
                sb.append("\"");
                sb.append(str);
                sb.append("\": ");
                sb.append(this.c.get(str));
                sb.append(", ");
            }
            if (this.c.size() > 0) {
                sb.delete(sb.length() - 2, sb.length());
            }
            sb.append("}");
            return sb.toString();
        }

        @Override // com.mappls.sdk.maps.style.expressions.Expression.d
        @NonNull
        public Object toValue() {
            HashMap hashMap = new HashMap();
            for (String str : this.c.keySet()) {
                Expression expression = this.c.get(str);
                if (expression instanceof d) {
                    hashMap.put(str, ((d) expression).toValue());
                } else {
                    hashMap.put(str, expression.toArray());
                }
            }
            return hashMap;
        }
    }

    /* loaded from: classes11.dex */
    public static class c {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public String f12847a;
        @NonNull
        public Expression b;

        public c(@NonNull String str, @NonNull Expression expression) {
            this.f12847a = str;
            this.b = expression;
        }
    }

    /* loaded from: classes11.dex */
    public interface d {
        Object toValue();
    }

    public Expression() {
        this.f12843a = null;
        this.b = null;
    }

    @NonNull
    public static Expression[] a(Expression[] expressionArr, Expression[] expressionArr2) {
        Expression[] expressionArr3 = new Expression[expressionArr.length + expressionArr2.length];
        System.arraycopy(expressionArr, 0, expressionArr3, 0, expressionArr.length);
        System.arraycopy(expressionArr2, 0, expressionArr3, expressionArr.length, expressionArr2.length);
        return expressionArr3;
    }

    public static Expression abs(Expression expression) {
        return new Expression("abs", expression);
    }

    public static Expression accumulated() {
        return new Expression("accumulated", new Expression[0]);
    }

    public static Expression acos(@NonNull Expression expression) {
        return new Expression("acos", expression);
    }

    public static Expression all(@NonNull Expression... expressionArr) {
        return new Expression("all", expressionArr);
    }

    public static Expression any(@NonNull Expression... expressionArr) {
        return new Expression("any", expressionArr);
    }

    public static Expression array(@NonNull Expression expression) {
        return new Expression("array", expression);
    }

    public static Expression asin(@NonNull Expression expression) {
        return new Expression("asin", expression);
    }

    public static Expression at(@NonNull Expression expression, @NonNull Expression expression2) {
        return new Expression("at", expression, expression2);
    }

    public static Expression atan(@NonNull Expression expression) {
        return new Expression("atan", expression);
    }

    @NonNull
    public static Object[] b(Object obj) {
        int length = java.lang.reflect.Array.getLength(obj);
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            objArr[i] = java.lang.reflect.Array.get(obj, i);
        }
        return objArr;
    }

    public static Expression bool(@NonNull Expression... expressionArr) {
        return new Expression("boolean", expressionArr);
    }

    public static Expression ceil(Expression expression) {
        return new Expression("ceil", expression);
    }

    public static Expression coalesce(@NonNull Expression... expressionArr) {
        return new Expression("coalesce", expressionArr);
    }

    public static Expression collator(boolean z, boolean z2, Locale locale) {
        HashMap hashMap = new HashMap();
        hashMap.put("case-sensitive", literal(z));
        hashMap.put("diacritic-sensitive", literal(z2));
        StringBuilder sb = new StringBuilder();
        String language = locale.getLanguage();
        if (language != null && !language.isEmpty()) {
            sb.append(language);
        }
        String country = locale.getCountry();
        if (country != null && !country.isEmpty()) {
            sb.append("-");
            sb.append(country);
        }
        hashMap.put("locale", literal(sb.toString()));
        return new Expression("collator", new b(hashMap));
    }

    public static Expression color(@ColorInt int i) {
        float[] colorToRgbaArray = ColorUtils.colorToRgbaArray(i);
        return rgba(Float.valueOf(colorToRgbaArray[0]), Float.valueOf(colorToRgbaArray[1]), Float.valueOf(colorToRgbaArray[2]), Float.valueOf(colorToRgbaArray[3]));
    }

    public static Expression concat(@NonNull Expression... expressionArr) {
        return new Expression("concat", expressionArr);
    }

    public static Expression cos(@NonNull Expression expression) {
        return new Expression("cos", expression);
    }

    public static Interpolator cubicBezier(@NonNull Expression expression, @NonNull Expression expression2, @NonNull Expression expression3, @NonNull Expression expression4) {
        return new Interpolator("cubic-bezier", expression, expression2, expression3, expression4);
    }

    public static Expression distance(@NonNull GeoJson geoJson) {
        HashMap hashMap = new HashMap();
        hashMap.put("json", literal(geoJson.toJson()));
        return new Expression("distance", new b(hashMap));
    }

    public static Expression division(@NonNull Expression expression, @NonNull Expression expression2) {
        return new Expression(MqttTopic.TOPIC_LEVEL_SEPARATOR, expression, expression2);
    }

    public static Expression downcase(@NonNull Expression expression) {
        return new Expression("downcase", expression);
    }

    public static Expression e() {
        return new Expression(RsaJsonWebKey.EXPONENT_MEMBER_NAME, new Expression[0]);
    }

    public static Expression eq(@NonNull Expression expression, @NonNull Expression expression2) {
        return new Expression("==", expression, expression2);
    }

    public static Interpolator exponential(@NonNull Number number) {
        return exponential(literal(number));
    }

    public static Expression floor(Expression expression) {
        return new Expression("floor", expression);
    }

    public static Expression format(@NonNull FormatEntry... formatEntryArr) {
        FormatOption[] formatOptionArr;
        Expression[] expressionArr = new Expression[formatEntryArr.length * 2];
        int length = formatEntryArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            FormatEntry formatEntry = formatEntryArr[i];
            int i3 = i2 + 1;
            expressionArr[i2] = formatEntry.f12845a;
            HashMap hashMap = new HashMap();
            if (formatEntry.b != null) {
                for (FormatOption formatOption : formatEntry.b) {
                    hashMap.put(formatOption.f12847a, formatOption.b);
                }
            }
            expressionArr[i3] = new b(hashMap);
            i++;
            i2 = i3 + 1;
        }
        return new Expression("format", expressionArr);
    }

    public static FormatEntry formatEntry(@NonNull Expression expression, @Nullable FormatOption... formatOptionArr) {
        return new FormatEntry(expression, formatOptionArr);
    }

    public static Expression geometryType() {
        return new Expression("geometry-type", new Expression[0]);
    }

    public static Expression get(@NonNull Expression expression) {
        return new Expression("get", expression);
    }

    public static Expression gt(@NonNull Expression expression, @NonNull Expression expression2) {
        return new Expression(">", expression, expression2);
    }

    public static Expression gte(@NonNull Expression expression, @NonNull Expression expression2) {
        return new Expression(">=", expression, expression2);
    }

    public static Expression has(@NonNull Expression expression) {
        return new Expression("has", expression);
    }

    public static Expression heatmapDensity() {
        return new Expression("heatmap-density", new Expression[0]);
    }

    public static Expression id() {
        return new Expression("id", new Expression[0]);
    }

    public static Expression image(@NonNull Expression expression) {
        return new Expression("image", expression);
    }

    public static Expression in(@NonNull Expression expression, @NonNull Expression expression2) {
        return new Expression("in", expression, expression2);
    }

    public static Expression interpolate(@NonNull Interpolator interpolator, @NonNull Expression expression, @NonNull Expression... expressionArr) {
        return new Expression("interpolate", a(new Expression[]{interpolator, expression}, expressionArr));
    }

    public static Expression isSupportedScript(Expression expression) {
        return new Expression("is-supported-script", expression);
    }

    public static Expression length(@NonNull Expression expression) {
        return new Expression("length", expression);
    }

    public static Expression let(@Size(min = 1) Expression... expressionArr) {
        return new Expression("let", expressionArr);
    }

    public static Expression lineProgress() {
        return new Expression("line-progress", new Expression[0]);
    }

    public static Interpolator linear() {
        return new Interpolator(Property.RASTER_RESAMPLING_LINEAR, new Expression[0]);
    }

    public static Expression literal(@NonNull Number number) {
        return new ExpressionLiteral(number);
    }

    public static Expression ln(Expression expression) {
        return new Expression("ln", expression);
    }

    public static Expression ln2() {
        return new Expression("ln2", new Expression[0]);
    }

    public static Expression log10(@NonNull Expression expression) {
        return new Expression("log10", expression);
    }

    public static Expression log2(@NonNull Expression expression) {
        return new Expression("log2", expression);
    }

    public static Expression lt(@NonNull Expression expression, @NonNull Expression expression2) {
        return new Expression("<", expression, expression2);
    }

    public static Expression lte(@NonNull Expression expression, @NonNull Expression expression2) {
        return new Expression("<=", expression, expression2);
    }

    public static Expression match(@NonNull @Size(min = 2) Expression... expressionArr) {
        return new Expression("match", expressionArr);
    }

    public static Expression max(@Size(min = 1) Expression... expressionArr) {
        return new Expression(Constants.PRIORITY_MAX, expressionArr);
    }

    public static Expression min(@Size(min = 1) Expression... expressionArr) {
        return new Expression("min", expressionArr);
    }

    public static Expression mod(@NonNull Expression expression, @NonNull Expression expression2) {
        return new Expression("%", expression, expression2);
    }

    public static Expression neq(@NonNull Expression expression, @NonNull Expression expression2) {
        return new Expression("!=", expression, expression2);
    }

    public static Expression not(@NonNull Expression expression) {
        return new Expression("!", expression);
    }

    public static Expression number(@NonNull Expression... expressionArr) {
        return new Expression(CTVariableUtils.NUMBER, expressionArr);
    }

    public static Expression numberFormat(@NonNull Expression expression, @NonNull NumberFormatOption... numberFormatOptionArr) {
        HashMap hashMap = new HashMap();
        for (NumberFormatOption numberFormatOption : numberFormatOptionArr) {
            hashMap.put(numberFormatOption.f12847a, numberFormatOption.b);
        }
        return new Expression("number-format", expression, new b(hashMap));
    }

    public static Expression object(@NonNull Expression expression) {
        return new Expression("object", expression);
    }

    public static Expression pi() {
        return new Expression("pi", new Expression[0]);
    }

    public static Expression pow(@NonNull Expression expression, @NonNull Expression expression2) {
        return new Expression("^", expression, expression2);
    }

    public static Expression product(@Size(min = 2) Expression... expressionArr) {
        return new Expression(Marker.ANY_MARKER, expressionArr);
    }

    public static Expression properties() {
        return new Expression("properties", new Expression[0]);
    }

    public static Expression raw(@NonNull String str) {
        return Converter.convert(str);
    }

    public static Expression resolvedLocale(Expression expression) {
        return new Expression("resolved-locale", expression);
    }

    public static Expression rgb(@NonNull Expression expression, @NonNull Expression expression2, @NonNull Expression expression3) {
        return new Expression("rgb", expression, expression2, expression3);
    }

    public static Expression rgba(@NonNull Expression expression, @NonNull Expression expression2, @NonNull Expression expression3, @NonNull Expression expression4) {
        return new Expression("rgba", expression, expression2, expression3, expression4);
    }

    public static Expression round(Expression expression) {
        return new Expression("round", expression);
    }

    public static Expression sin(@NonNull Expression expression) {
        return new Expression("sin", expression);
    }

    public static Expression sqrt(@NonNull Expression expression) {
        return new Expression("sqrt", expression);
    }

    public static Expression step(@NonNull Number number, @NonNull Expression expression, Expression... expressionArr) {
        return step(literal(number), expression, expressionArr);
    }

    public static Stop stop(@NonNull Object obj, @NonNull Object obj2) {
        return new Stop(obj, obj2);
    }

    public static Expression string(@NonNull Expression... expressionArr) {
        return new Expression("string", expressionArr);
    }

    public static Expression subtract(@NonNull Expression expression) {
        return new Expression("-", expression);
    }

    public static Expression sum(@Size(min = 2) Expression... expressionArr) {
        return new Expression("+", expressionArr);
    }

    public static Expression switchCase(@NonNull @Size(min = 1) Expression... expressionArr) {
        return new Expression("case", expressionArr);
    }

    public static Expression tan(@NonNull Expression expression) {
        return new Expression("tan", expression);
    }

    public static Expression toBool(@NonNull Expression expression) {
        return new Expression("to-boolean", expression);
    }

    public static Expression toColor(@NonNull Expression expression) {
        return new Expression("to-color", expression);
    }

    public static Expression toNumber(@NonNull Expression expression) {
        return new Expression("to-number", expression);
    }

    public static Expression toRgba(@NonNull Expression expression) {
        return new Expression("to-rgba", expression);
    }

    public static Expression toString(@NonNull Expression expression) {
        return new Expression("to-string", expression);
    }

    public static Expression typeOf(@NonNull Expression expression) {
        return new Expression("typeof", expression);
    }

    public static Expression upcase(@NonNull Expression expression) {
        return new Expression("upcase", expression);
    }

    public static Expression var(@NonNull Expression expression) {
        return new Expression("var", expression);
    }

    public static Expression within(@NonNull Polygon polygon) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", literal(polygon.type()));
        hashMap.put("json", literal(polygon.toJson()));
        return new Expression("within", new b(hashMap));
    }

    public static Expression zoom() {
        return new Expression("zoom", new Expression[0]);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Expression)) {
            return false;
        }
        Expression expression = (Expression) obj;
        String str = this.f12843a;
        if (str == null ? expression.f12843a == null : str.equals(expression.f12843a)) {
            return Arrays.deepEquals(this.b, expression.b);
        }
        return false;
    }

    public int hashCode() {
        String str = this.f12843a;
        return ((str != null ? str.hashCode() : 0) * 31) + Arrays.hashCode(this.b);
    }

    @NonNull
    public Object[] toArray() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.f12843a);
        Expression[] expressionArr = this.b;
        if (expressionArr != null) {
            for (Expression expression : expressionArr) {
                if (expression instanceof d) {
                    arrayList.add(((d) expression).toValue());
                } else {
                    arrayList.add(expression.toArray());
                }
            }
        }
        return arrayList.toArray();
    }

    public static Expression abs(@NonNull Number number) {
        return abs(literal(number));
    }

    public static Expression acos(@NonNull Number number) {
        return acos(literal(number));
    }

    public static Expression asin(@NonNull Number number) {
        return asin(literal(number));
    }

    public static Expression at(@NonNull Number number, @NonNull Expression expression) {
        return at(literal(number), expression);
    }

    public static Expression atan(@NonNull Number number) {
        return atan(literal(number));
    }

    public static Expression ceil(@NonNull Number number) {
        return ceil(literal(number));
    }

    public static Expression concat(@NonNull String... strArr) {
        Expression[] expressionArr = new Expression[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            expressionArr[i] = literal(strArr[i]);
        }
        return concat(expressionArr);
    }

    public static Expression cos(@NonNull Number number) {
        return new Expression("cos", literal(number));
    }

    public static Interpolator cubicBezier(@NonNull Number number, @NonNull Number number2, @NonNull Number number3, @NonNull Number number4) {
        return cubicBezier(literal(number), literal(number2), literal(number3), literal(number4));
    }

    public static Expression division(@NonNull Number number, @NonNull Number number2) {
        return division(literal(number), literal(number2));
    }

    public static Expression downcase(@NonNull String str) {
        return downcase(literal(str));
    }

    public static Expression eq(@NonNull Expression expression, @NonNull Expression expression2, @NonNull Expression expression3) {
        return new Expression("==", expression, expression2, expression3);
    }

    public static Interpolator exponential(@NonNull Expression expression) {
        return new Interpolator("exponential", expression);
    }

    public static Expression floor(@NonNull Number number) {
        return floor(literal(number));
    }

    public static FormatEntry formatEntry(@NonNull Expression expression) {
        return new FormatEntry(expression, null);
    }

    public static Expression get(@NonNull String str) {
        return get(literal(str));
    }

    public static Expression gt(@NonNull Expression expression, @NonNull Expression expression2, @NonNull Expression expression3) {
        return new Expression(">", expression, expression2, expression3);
    }

    public static Expression gte(@NonNull Expression expression, @NonNull Expression expression2, @NonNull Expression expression3) {
        return new Expression(">=", expression, expression2, expression3);
    }

    public static Expression has(@NonNull String str) {
        return has(literal(str));
    }

    public static Expression in(@NonNull Number number, @NonNull Expression expression) {
        return new Expression("in", literal(number), expression);
    }

    public static Expression interpolate(@NonNull Interpolator interpolator, @NonNull Expression expression, Stop... stopArr) {
        return interpolate(interpolator, expression, Stop.a(stopArr));
    }

    public static Expression isSupportedScript(@NonNull String str) {
        return new Expression("is-supported-script", literal(str));
    }

    public static Expression length(@NonNull String str) {
        return length(literal(str));
    }

    public static Expression literal(@NonNull String str) {
        return new ExpressionLiteral(str);
    }

    public static Expression ln(@NonNull Number number) {
        return ln(literal(number));
    }

    public static Expression log10(@NonNull Number number) {
        return log10(literal(number));
    }

    public static Expression log2(@NonNull Number number) {
        return log2(literal(number));
    }

    public static Expression lt(@NonNull Expression expression, @NonNull Expression expression2, @NonNull Expression expression3) {
        return new Expression("<", expression, expression2, expression3);
    }

    public static Expression lte(@NonNull Expression expression, @NonNull Expression expression2, @NonNull Expression expression3) {
        return new Expression("<=", expression, expression2, expression3);
    }

    public static Expression match(@NonNull Expression expression, @NonNull Expression expression2, @NonNull Stop... stopArr) {
        return match(a(a(new Expression[]{expression}, Stop.a(stopArr)), new Expression[]{expression2}));
    }

    @SuppressLint({HttpHeaders.RANGE})
    public static Expression max(@Size(min = 1) Number... numberArr) {
        Expression[] expressionArr = new Expression[numberArr.length];
        for (int i = 0; i < numberArr.length; i++) {
            expressionArr[i] = literal(numberArr[i]);
        }
        return max(expressionArr);
    }

    @SuppressLint({HttpHeaders.RANGE})
    public static Expression min(@Size(min = 1) Number... numberArr) {
        Expression[] expressionArr = new Expression[numberArr.length];
        for (int i = 0; i < numberArr.length; i++) {
            expressionArr[i] = literal(numberArr[i]);
        }
        return min(expressionArr);
    }

    public static Expression mod(@NonNull Number number, @NonNull Number number2) {
        return mod(literal(number), literal(number2));
    }

    public static Expression neq(@NonNull Expression expression, @NonNull Expression expression2, @NonNull Expression expression3) {
        return new Expression("!=", expression, expression2, expression3);
    }

    public static Expression not(boolean z) {
        return not(literal(z));
    }

    public static Expression pow(@NonNull Number number, @NonNull Number number2) {
        return pow(literal(number), literal(number2));
    }

    @SuppressLint({HttpHeaders.RANGE})
    public static Expression product(@Size(min = 2) Number... numberArr) {
        Expression[] expressionArr = new Expression[numberArr.length];
        for (int i = 0; i < numberArr.length; i++) {
            expressionArr[i] = literal(numberArr[i]);
        }
        return product(expressionArr);
    }

    public static Expression rgb(@NonNull Number number, @NonNull Number number2, @NonNull Number number3) {
        return rgb(literal(number), literal(number2), literal(number3));
    }

    public static Expression rgba(@NonNull Number number, @NonNull Number number2, @NonNull Number number3, @NonNull Number number4) {
        return rgba(literal(number), literal(number2), literal(number3), literal(number4));
    }

    public static Expression round(@NonNull Number number) {
        return round(literal(number));
    }

    public static Expression sin(@NonNull Number number) {
        return sin(literal(number));
    }

    public static Expression sqrt(@NonNull Number number) {
        return sqrt(literal(number));
    }

    public static Expression step(@NonNull Expression expression, @NonNull Expression expression2, @NonNull Expression... expressionArr) {
        return new Expression(DeviceKey.Step, a(new Expression[]{expression, expression2}, expressionArr));
    }

    public static Expression subtract(@NonNull Number number) {
        return subtract(literal(number));
    }

    @SuppressLint({HttpHeaders.RANGE})
    public static Expression sum(@Size(min = 2) Number... numberArr) {
        Expression[] expressionArr = new Expression[numberArr.length];
        for (int i = 0; i < numberArr.length; i++) {
            expressionArr[i] = literal(numberArr[i]);
        }
        return sum(expressionArr);
    }

    public static Expression tan(@NonNull Number number) {
        return new Expression("tan", literal(number));
    }

    public static Expression upcase(@NonNull String str) {
        return upcase(literal(str));
    }

    public static Expression var(@NonNull String str) {
        return var(literal(str));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\"");
        sb.append(this.f12843a);
        sb.append("\"");
        Expression[] expressionArr = this.b;
        if (expressionArr != null) {
            for (Expression expression : expressionArr) {
                sb.append(", ");
                sb.append(expression.toString());
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static Expression eq(@NonNull Expression expression, boolean z) {
        return eq(expression, literal(z));
    }

    public static FormatEntry formatEntry(@NonNull String str, @Nullable FormatOption... formatOptionArr) {
        return new FormatEntry(literal(str), formatOptionArr);
    }

    public static Expression get(@NonNull Expression expression, @NonNull Expression expression2) {
        return new Expression("get", expression, expression2);
    }

    public static Expression gt(@NonNull Expression expression, @NonNull Number number) {
        return new Expression(">", expression, literal(number));
    }

    public static Expression gte(@NonNull Expression expression, @NonNull Number number) {
        return new Expression(">=", expression, literal(number));
    }

    public static Expression has(@NonNull Expression expression, @NonNull Expression expression2) {
        return new Expression("has", expression, expression2);
    }

    public static Expression in(@NonNull String str, @NonNull Expression expression) {
        return new Expression("in", literal(str), expression);
    }

    public static Expression literal(boolean z) {
        return new ExpressionLiteral(Boolean.valueOf(z));
    }

    public static Expression lt(@NonNull Expression expression, @NonNull Number number) {
        return new Expression("<", expression, literal(number));
    }

    public static Expression lte(@NonNull Expression expression, @NonNull Number number) {
        return new Expression("<=", expression, literal(number));
    }

    public static Expression neq(Expression expression, boolean z) {
        return new Expression("!=", expression, literal(z));
    }

    public static Expression step(@NonNull Number number, @NonNull Expression expression, Stop... stopArr) {
        return step(literal(number), expression, Stop.a(stopArr));
    }

    public static Expression subtract(@NonNull Expression expression, @NonNull Expression expression2) {
        return new Expression("-", expression, expression2);
    }

    public Expression(@NonNull String str, @Nullable Expression... expressionArr) {
        this.f12843a = str;
        this.b = expressionArr;
    }

    public static Expression eq(@NonNull Expression expression, @NonNull String str) {
        return eq(expression, literal(str));
    }

    public static FormatEntry formatEntry(@NonNull String str) {
        return new FormatEntry(literal(str), null);
    }

    public static Expression get(@NonNull String str, @NonNull Expression expression) {
        return get(literal(str), expression);
    }

    public static Expression gt(@NonNull Expression expression, @NonNull String str) {
        return new Expression(">", expression, literal(str));
    }

    public static Expression gte(@NonNull Expression expression, @NonNull String str) {
        return new Expression(">=", expression, literal(str));
    }

    public static Expression has(@NonNull String str, @NonNull Expression expression) {
        return has(literal(str), expression);
    }

    public static Expression literal(@NonNull Object obj) {
        if (obj.getClass().isArray()) {
            return literal(b(obj));
        }
        if (!(obj instanceof Expression)) {
            return new ExpressionLiteral(obj);
        }
        throw new RuntimeException("Can't convert an expression to a literal");
    }

    public static Expression lt(@NonNull Expression expression, @NonNull String str) {
        return new Expression("<", expression, literal(str));
    }

    public static Expression lte(@NonNull Expression expression, @NonNull String str) {
        return new Expression("<=", expression, literal(str));
    }

    public static Expression neq(@NonNull Expression expression, @NonNull String str) {
        return new Expression("!=", expression, literal(str));
    }

    public static Expression step(@NonNull Expression expression, @NonNull Expression expression2, Stop... stopArr) {
        return step(expression, expression2, Stop.a(stopArr));
    }

    public static Expression subtract(@NonNull Number number, @NonNull Number number2) {
        return subtract(literal(number), literal(number2));
    }

    public static Expression eq(@NonNull Expression expression, @NonNull String str, @NonNull Expression expression2) {
        return eq(expression, literal(str), expression2);
    }

    public static Expression gt(@NonNull Expression expression, @NonNull String str, @NonNull Expression expression2) {
        return new Expression(">", expression, literal(str), expression2);
    }

    public static Expression gte(@NonNull Expression expression, @NonNull String str, @NonNull Expression expression2) {
        return new Expression(">=", expression, literal(str), expression2);
    }

    public static Expression lt(@NonNull Expression expression, @NonNull String str, @NonNull Expression expression2) {
        return new Expression("<", expression, literal(str), expression2);
    }

    public static Expression lte(@NonNull Expression expression, @NonNull String str, @NonNull Expression expression2) {
        return new Expression("<=", expression, literal(str), expression2);
    }

    public static Expression neq(@NonNull Expression expression, @NonNull String str, @NonNull Expression expression2) {
        return new Expression("!=", expression, literal(str), expression2);
    }

    public static Expression numberFormat(@NonNull Number number, @NonNull NumberFormatOption... numberFormatOptionArr) {
        return numberFormat(literal(number), numberFormatOptionArr);
    }

    public static Expression step(@NonNull Number number, @NonNull Number number2, Expression... expressionArr) {
        return step(literal(number), number2, expressionArr);
    }

    public static Expression eq(@NonNull Expression expression, @NonNull Number number) {
        return eq(expression, literal(number));
    }

    public static Expression neq(@NonNull Expression expression, @NonNull Number number) {
        return new Expression("!=", expression, literal(number));
    }

    public static Expression step(@NonNull Expression expression, @NonNull Number number, Expression... expressionArr) {
        return step(expression, literal(number), expressionArr);
    }

    public static Expression step(@NonNull Number number, @NonNull Number number2, Stop... stopArr) {
        return step(literal(number), number2, Stop.a(stopArr));
    }

    public static Expression step(@NonNull Expression expression, @NonNull Number number, Stop... stopArr) {
        return step(expression, number, Stop.a(stopArr));
    }

    public static Expression literal(@NonNull Object[] objArr) {
        return new Expression("literal", new a(objArr));
    }

    public static Expression collator(boolean z, boolean z2) {
        HashMap hashMap = new HashMap();
        hashMap.put("case-sensitive", literal(z));
        hashMap.put("diacritic-sensitive", literal(z2));
        return new Expression("collator", new b(hashMap));
    }

    public static Expression collator(Expression expression, Expression expression2, Expression expression3) {
        HashMap hashMap = new HashMap();
        hashMap.put("case-sensitive", expression);
        hashMap.put("diacritic-sensitive", expression2);
        hashMap.put("locale", expression3);
        return new Expression("collator", new b(hashMap));
    }

    /* loaded from: classes11.dex */
    public static final class Converter {

        /* renamed from: a  reason: collision with root package name */
        public static final Gson f12844a = new Gson();

        public static Expression a(@NonNull JsonPrimitive jsonPrimitive) {
            return new ExpressionLiteral(b(jsonPrimitive));
        }

        public static Object b(@NonNull JsonPrimitive jsonPrimitive) {
            if (jsonPrimitive.isBoolean()) {
                return Boolean.valueOf(jsonPrimitive.getAsBoolean());
            }
            if (jsonPrimitive.isNumber()) {
                return Float.valueOf(jsonPrimitive.getAsFloat());
            }
            if (jsonPrimitive.isString()) {
                return jsonPrimitive.getAsString();
            }
            throw new RuntimeException("Unsupported literal expression conversion for " + JsonPrimitive.class);
        }

        public static Expression convert(@NonNull JsonArray jsonArray) {
            if (jsonArray.size() != 0) {
                String asString = jsonArray.get(0).getAsString();
                ArrayList arrayList = new ArrayList();
                if (asString.equals("within")) {
                    return Expression.within(Polygon.fromJson(jsonArray.get(1).toString()));
                }
                if (asString.equals("distance")) {
                    return Expression.distance(GeometryGeoJson.fromJson(jsonArray.get(1).toString()));
                }
                for (int i = 1; i < jsonArray.size(); i++) {
                    JsonElement jsonElement = jsonArray.get(i);
                    if (asString.equals("literal") && (jsonElement instanceof JsonArray)) {
                        JsonArray jsonArray2 = (JsonArray) jsonElement;
                        Object[] objArr = new Object[jsonArray2.size()];
                        for (int i2 = 0; i2 < jsonArray2.size(); i2++) {
                            JsonElement jsonElement2 = jsonArray2.get(i2);
                            if (jsonElement2 instanceof JsonPrimitive) {
                                objArr[i2] = b((JsonPrimitive) jsonElement2);
                            } else {
                                throw new IllegalArgumentException("Nested literal arrays are not supported.");
                            }
                        }
                        arrayList.add(new a(objArr));
                    } else {
                        arrayList.add(convert(jsonElement));
                    }
                }
                return new Expression(asString, (Expression[]) arrayList.toArray(new Expression[arrayList.size()]));
            }
            throw new IllegalArgumentException("Can't convert empty jsonArray expressions");
        }

        public static Expression convert(@NonNull JsonElement jsonElement) {
            if (jsonElement instanceof JsonArray) {
                return convert((JsonArray) jsonElement);
            }
            if (jsonElement instanceof JsonPrimitive) {
                return a((JsonPrimitive) jsonElement);
            }
            if (jsonElement instanceof JsonNull) {
                return new ExpressionLiteral("");
            }
            if (jsonElement instanceof JsonObject) {
                HashMap hashMap = new HashMap();
                JsonObject jsonObject = (JsonObject) jsonElement;
                for (String str : jsonObject.keySet()) {
                    hashMap.put(str, convert(jsonObject.get(str)));
                }
                return new b(hashMap);
            }
            throw new RuntimeException("Unsupported expression conversion for " + jsonElement.getClass());
        }

        public static Expression convert(@NonNull String str) {
            return convert((JsonArray) f12844a.fromJson(str, (Class<Object>) JsonArray.class));
        }
    }

    public static Expression collator(Expression expression, Expression expression2) {
        HashMap hashMap = new HashMap();
        hashMap.put("case-sensitive", expression);
        hashMap.put("diacritic-sensitive", expression2);
        return new Expression("collator", new b(hashMap));
    }
}
