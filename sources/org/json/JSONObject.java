package org.json;

import com.jstyle.blesdk1860.constant.BleConst;
import java.io.Closeable;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;
/* loaded from: classes13.dex */
public class JSONObject {
    public static final Object NULL = new b();

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, Object> f15560a;

    /* loaded from: classes13.dex */
    public static final class b {
        public b() {
        }

        public final Object clone() {
            return this;
        }

        public boolean equals(Object obj) {
            return obj == null || obj == this;
        }

        public int hashCode() {
            return 0;
        }

        public String toString() {
            return "null";
        }
    }

    public JSONObject() {
        this.f15560a = new HashMap();
    }

    public static final void a(Writer writer, int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            writer.write(32);
        }
    }

    public static final Writer c(Writer writer, Object obj, int i, int i2) throws JSONException, IOException {
        if (obj != null && !obj.equals(null)) {
            if (obj instanceof JSONString) {
                try {
                    String jSONString = ((JSONString) obj).toJSONString();
                    writer.write(jSONString != null ? jSONString.toString() : quote(obj.toString()));
                } catch (Exception e) {
                    throw new JSONException(e);
                }
            } else if (obj instanceof Number) {
                String numberToString = numberToString((Number) obj);
                try {
                    new BigDecimal(numberToString);
                    writer.write(numberToString);
                } catch (NumberFormatException unused) {
                    quote(numberToString, writer);
                }
            } else if (obj instanceof Boolean) {
                writer.write(obj.toString());
            } else if (obj instanceof Enum) {
                writer.write(quote(((Enum) obj).name()));
            } else if (obj instanceof JSONObject) {
                ((JSONObject) obj).write(writer, i, i2);
            } else if (obj instanceof JSONArray) {
                ((JSONArray) obj).write(writer, i, i2);
            } else if (obj instanceof Map) {
                new JSONObject((Map<?, ?>) obj).write(writer, i, i2);
            } else if (obj instanceof Collection) {
                new JSONArray((Collection<?>) obj).write(writer, i, i2);
            } else if (obj.getClass().isArray()) {
                new JSONArray(obj).write(writer, i, i2);
            } else {
                quote(obj.toString(), writer);
            }
        } else {
            writer.write("null");
        }
        return writer;
    }

    public static String doubleToString(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            return "null";
        }
        String d2 = Double.toString(d);
        if (d2.indexOf(46) <= 0 || d2.indexOf(101) >= 0 || d2.indexOf(69) >= 0) {
            return d2;
        }
        while (d2.endsWith(BleConst.GetDeviceTime)) {
            d2 = d2.substring(0, d2.length() - 1);
        }
        return d2.endsWith(".") ? d2.substring(0, d2.length() - 1) : d2;
    }

    public static String[] getNames(JSONObject jSONObject) {
        int length = jSONObject.length();
        if (length == 0) {
            return null;
        }
        return (String[]) jSONObject.keySet().toArray(new String[length]);
    }

    public static boolean isDecimalNotation(String str) {
        return str.indexOf(46) > -1 || str.indexOf(101) > -1 || str.indexOf(69) > -1 || "-0".equals(str);
    }

    public static String numberToString(Number number) throws JSONException {
        if (number != null) {
            testValidity(number);
            String obj = number.toString();
            if (obj.indexOf(46) <= 0 || obj.indexOf(101) >= 0 || obj.indexOf(69) >= 0) {
                return obj;
            }
            while (obj.endsWith(BleConst.GetDeviceTime)) {
                obj = obj.substring(0, obj.length() - 1);
            }
            return obj.endsWith(".") ? obj.substring(0, obj.length() - 1) : obj;
        }
        throw new JSONException("Null pointer");
    }

    public static String quote(String str) {
        String obj;
        StringWriter stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            try {
                try {
                    obj = quote(str, stringWriter).toString();
                } catch (IOException unused) {
                    return "";
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }

    public static Number stringToNumber(String str) throws NumberFormatException {
        char charAt = str.charAt(0);
        if ((charAt >= '0' && charAt <= '9') || charAt == '-') {
            if (isDecimalNotation(str)) {
                if (str.length() > 14) {
                    return new BigDecimal(str);
                }
                Double valueOf = Double.valueOf(str);
                return (valueOf.isInfinite() || valueOf.isNaN()) ? new BigDecimal(str) : valueOf;
            }
            BigInteger bigInteger = new BigInteger(str);
            if (bigInteger.bitLength() <= 31) {
                return Integer.valueOf(bigInteger.intValue());
            }
            return bigInteger.bitLength() <= 63 ? Long.valueOf(bigInteger.longValue()) : bigInteger;
        }
        throw new NumberFormatException("val [" + str + "] is not a valid number.");
    }

    public static Object stringToValue(String str) {
        if (str.equals("")) {
            return str;
        }
        if (str.equalsIgnoreCase("true")) {
            return Boolean.TRUE;
        }
        if (str.equalsIgnoreCase("false")) {
            return Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("null")) {
            return NULL;
        }
        char charAt = str.charAt(0);
        if ((charAt >= '0' && charAt <= '9') || charAt == '-') {
            try {
                if (isDecimalNotation(str)) {
                    Double valueOf = Double.valueOf(str);
                    if (!valueOf.isInfinite() && !valueOf.isNaN()) {
                        return valueOf;
                    }
                } else {
                    Long valueOf2 = Long.valueOf(str);
                    if (str.equals(valueOf2.toString())) {
                        return valueOf2.longValue() == ((long) valueOf2.intValue()) ? Integer.valueOf(valueOf2.intValue()) : valueOf2;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return str;
    }

    public static void testValidity(Object obj) throws JSONException {
        if (obj != null) {
            if (obj instanceof Double) {
                Double d = (Double) obj;
                if (d.isInfinite() || d.isNaN()) {
                    throw new JSONException("JSON does not allow non-finite numbers.");
                }
            } else if (obj instanceof Float) {
                Float f = (Float) obj;
                if (f.isInfinite() || f.isNaN()) {
                    throw new JSONException("JSON does not allow non-finite numbers.");
                }
            }
        }
    }

    public static String valueToString(Object obj) throws JSONException {
        return JSONWriter.valueToString(obj);
    }

    public static Object wrap(Object obj) {
        try {
            if (obj == null) {
                return NULL;
            }
            if (!(obj instanceof JSONObject) && !(obj instanceof JSONArray) && !NULL.equals(obj) && !(obj instanceof JSONString) && !(obj instanceof Byte) && !(obj instanceof Character) && !(obj instanceof Short) && !(obj instanceof Integer) && !(obj instanceof Long) && !(obj instanceof Boolean) && !(obj instanceof Float) && !(obj instanceof Double) && !(obj instanceof String) && !(obj instanceof BigInteger) && !(obj instanceof BigDecimal) && !(obj instanceof Enum)) {
                if (obj instanceof Collection) {
                    return new JSONArray((Collection<?>) obj);
                }
                if (obj.getClass().isArray()) {
                    return new JSONArray(obj);
                }
                if (obj instanceof Map) {
                    return new JSONObject((Map<?, ?>) obj);
                }
                Package r0 = obj.getClass().getPackage();
                String name = r0 != null ? r0.getName() : "";
                if (!name.startsWith("java.") && !name.startsWith("javax.") && obj.getClass().getClassLoader() != null) {
                    return new JSONObject(obj);
                }
                return obj.toString();
            }
            return obj;
        } catch (Exception unused) {
            return null;
        }
    }

    public JSONObject accumulate(String str, Object obj) throws JSONException {
        testValidity(obj);
        Object opt = opt(str);
        if (opt == null) {
            if (obj instanceof JSONArray) {
                obj = new JSONArray().put(obj);
            }
            put(str, obj);
        } else if (opt instanceof JSONArray) {
            ((JSONArray) opt).put(obj);
        } else {
            put(str, new JSONArray().put(opt).put(obj));
        }
        return this;
    }

    public JSONObject append(String str, Object obj) throws JSONException {
        testValidity(obj);
        Object opt = opt(str);
        if (opt == null) {
            put(str, new JSONArray().put(obj));
        } else if (opt instanceof JSONArray) {
            put(str, ((JSONArray) opt).put(obj));
        } else {
            throw new JSONException("JSONObject[" + str + "] is not a JSONArray.");
        }
        return this;
    }

    public final void b(Object obj) {
        Method[] declaredMethods;
        String substring;
        Class<?> cls = obj.getClass();
        if (cls.getClassLoader() != null) {
            declaredMethods = cls.getMethods();
        } else {
            declaredMethods = cls.getDeclaredMethods();
        }
        for (Method method : declaredMethods) {
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers) && method.getParameterTypes().length == 0 && !method.isBridge() && method.getReturnType() != Void.TYPE) {
                String name = method.getName();
                if (name.startsWith("get")) {
                    if (!"getClass".equals(name) && !"getDeclaringClass".equals(name)) {
                        substring = name.substring(3);
                    }
                } else if (name.startsWith("is")) {
                    substring = name.substring(2);
                }
                if (substring.length() > 0 && Character.isUpperCase(substring.charAt(0))) {
                    if (substring.length() == 1) {
                        substring = substring.toLowerCase(Locale.ROOT);
                    } else if (!Character.isUpperCase(substring.charAt(1))) {
                        substring = substring.substring(0, 1).toLowerCase(Locale.ROOT) + substring.substring(1);
                    }
                    try {
                        Object invoke = method.invoke(obj, new Object[0]);
                        if (invoke != null) {
                            this.f15560a.put(substring, wrap(invoke));
                            if (invoke instanceof Closeable) {
                                ((Closeable) invoke).close();
                            }
                        }
                    } catch (IOException | IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
                    }
                }
            }
        }
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return this.f15560a.entrySet();
    }

    public Object get(String str) throws JSONException {
        if (str != null) {
            Object opt = opt(str);
            if (opt != null) {
                return opt;
            }
            throw new JSONException("JSONObject[" + quote(str) + "] not found.");
        }
        throw new JSONException("Null key.");
    }

    public BigDecimal getBigDecimal(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        try {
            return new BigDecimal(obj.toString());
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + quote(str) + "] could not be converted to BigDecimal.", e);
        }
    }

    public BigInteger getBigInteger(String str) throws JSONException {
        try {
            return new BigInteger(get(str).toString());
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + quote(str) + "] could not be converted to BigInteger.", e);
        }
    }

    public boolean getBoolean(String str) throws JSONException {
        Object obj = get(str);
        if (obj.equals(Boolean.FALSE)) {
            return false;
        }
        boolean z = obj instanceof String;
        if (z && ((String) obj).equalsIgnoreCase("false")) {
            return false;
        }
        if (obj.equals(Boolean.TRUE)) {
            return true;
        }
        if (z && ((String) obj).equalsIgnoreCase("true")) {
            return true;
        }
        throw new JSONException("JSONObject[" + quote(str) + "] is not a Boolean.");
    }

    public double getDouble(String str) throws JSONException {
        Object obj = get(str);
        try {
            return obj instanceof Number ? ((Number) obj).doubleValue() : Double.parseDouble(obj.toString());
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + quote(str) + "] is not a number.", e);
        }
    }

    public <E extends Enum<E>> E getEnum(Class<E> cls, String str) throws JSONException {
        E e = (E) optEnum(cls, str);
        if (e != null) {
            return e;
        }
        throw new JSONException("JSONObject[" + quote(str) + "] is not an enum of type " + quote(cls.getSimpleName()) + ".");
    }

    public float getFloat(String str) throws JSONException {
        Object obj = get(str);
        try {
            return obj instanceof Number ? ((Number) obj).floatValue() : Float.parseFloat(obj.toString());
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + quote(str) + "] is not a number.", e);
        }
    }

    public int getInt(String str) throws JSONException {
        Object obj = get(str);
        try {
            return obj instanceof Number ? ((Number) obj).intValue() : Integer.parseInt((String) obj);
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + quote(str) + "] is not an int.", e);
        }
    }

    public JSONArray getJSONArray(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        throw new JSONException("JSONObject[" + quote(str) + "] is not a JSONArray.");
    }

    public JSONObject getJSONObject(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        throw new JSONException("JSONObject[" + quote(str) + "] is not a JSONObject.");
    }

    public long getLong(String str) throws JSONException {
        Object obj = get(str);
        try {
            return obj instanceof Number ? ((Number) obj).longValue() : Long.parseLong((String) obj);
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + quote(str) + "] is not a long.", e);
        }
    }

    public Number getNumber(String str) throws JSONException {
        Object obj = get(str);
        try {
            if (obj instanceof Number) {
                return (Number) obj;
            }
            return stringToNumber(obj.toString());
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + quote(str) + "] is not a number.", e);
        }
    }

    public String getString(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        throw new JSONException("JSONObject[" + quote(str) + "] not a string.");
    }

    public boolean has(String str) {
        return this.f15560a.containsKey(str);
    }

    public JSONObject increment(String str) throws JSONException {
        Object opt = opt(str);
        if (opt == null) {
            put(str, 1);
        } else if (opt instanceof BigInteger) {
            put(str, ((BigInteger) opt).add(BigInteger.ONE));
        } else if (opt instanceof BigDecimal) {
            put(str, ((BigDecimal) opt).add(BigDecimal.ONE));
        } else if (opt instanceof Integer) {
            put(str, ((Integer) opt).intValue() + 1);
        } else if (opt instanceof Long) {
            put(str, ((Long) opt).longValue() + 1);
        } else if (opt instanceof Double) {
            put(str, ((Double) opt).doubleValue() + 1.0d);
        } else if (opt instanceof Float) {
            put(str, ((Float) opt).floatValue() + 1.0f);
        } else {
            throw new JSONException("Unable to increment [" + quote(str) + "].");
        }
        return this;
    }

    public boolean isNull(String str) {
        return NULL.equals(opt(str));
    }

    public Set<String> keySet() {
        return this.f15560a.keySet();
    }

    public Iterator<String> keys() {
        return keySet().iterator();
    }

    public int length() {
        return this.f15560a.size();
    }

    public JSONArray names() {
        if (this.f15560a.isEmpty()) {
            return null;
        }
        return new JSONArray((Collection<?>) this.f15560a.keySet());
    }

    public Object opt(String str) {
        if (str == null) {
            return null;
        }
        return this.f15560a.get(str);
    }

    public BigDecimal optBigDecimal(String str, BigDecimal bigDecimal) {
        Object opt = opt(str);
        if (NULL.equals(opt)) {
            return bigDecimal;
        }
        if (opt instanceof BigDecimal) {
            return (BigDecimal) opt;
        }
        if (opt instanceof BigInteger) {
            return new BigDecimal((BigInteger) opt);
        }
        if (!(opt instanceof Double) && !(opt instanceof Float)) {
            if (!(opt instanceof Long) && !(opt instanceof Integer) && !(opt instanceof Short) && !(opt instanceof Byte)) {
                try {
                    return new BigDecimal(opt.toString());
                } catch (Exception unused) {
                    return bigDecimal;
                }
            }
            return new BigDecimal(((Number) opt).longValue());
        }
        return new BigDecimal(((Number) opt).doubleValue());
    }

    public BigInteger optBigInteger(String str, BigInteger bigInteger) {
        Object opt = opt(str);
        if (NULL.equals(opt)) {
            return bigInteger;
        }
        if (opt instanceof BigInteger) {
            return (BigInteger) opt;
        }
        if (opt instanceof BigDecimal) {
            return ((BigDecimal) opt).toBigInteger();
        }
        if (!(opt instanceof Double) && !(opt instanceof Float)) {
            if (!(opt instanceof Long) && !(opt instanceof Integer) && !(opt instanceof Short) && !(opt instanceof Byte)) {
                try {
                    String obj = opt.toString();
                    if (isDecimalNotation(obj)) {
                        return new BigDecimal(obj).toBigInteger();
                    }
                    return new BigInteger(obj);
                } catch (Exception unused) {
                    return bigInteger;
                }
            }
            return BigInteger.valueOf(((Number) opt).longValue());
        }
        return new BigDecimal(((Number) opt).doubleValue()).toBigInteger();
    }

    public boolean optBoolean(String str) {
        return optBoolean(str, false);
    }

    public double optDouble(String str) {
        return optDouble(str, Double.NaN);
    }

    public <E extends Enum<E>> E optEnum(Class<E> cls, String str) {
        return (E) optEnum(cls, str, null);
    }

    public float optFloat(String str) {
        return optFloat(str, Float.NaN);
    }

    public int optInt(String str) {
        return optInt(str, 0);
    }

    public JSONArray optJSONArray(String str) {
        Object opt = opt(str);
        if (opt instanceof JSONArray) {
            return (JSONArray) opt;
        }
        return null;
    }

    public JSONObject optJSONObject(String str) {
        Object opt = opt(str);
        if (opt instanceof JSONObject) {
            return (JSONObject) opt;
        }
        return null;
    }

    public long optLong(String str) {
        return optLong(str, 0L);
    }

    public Number optNumber(String str) {
        return optNumber(str, null);
    }

    public Object optQuery(String str) {
        return optQuery(new JSONPointer(str));
    }

    public String optString(String str) {
        return optString(str, "");
    }

    public JSONObject put(String str, boolean z) throws JSONException {
        put(str, z ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public JSONObject putOnce(String str, Object obj) throws JSONException {
        if (str != null && obj != null) {
            if (opt(str) == null) {
                put(str, obj);
            } else {
                throw new JSONException("Duplicate key \"" + str + "\"");
            }
        }
        return this;
    }

    public JSONObject putOpt(String str, Object obj) throws JSONException {
        if (str != null && obj != null) {
            put(str, obj);
        }
        return this;
    }

    public Object query(String str) {
        return query(new JSONPointer(str));
    }

    public Object remove(String str) {
        return this.f15560a.remove(str);
    }

    public boolean similar(Object obj) {
        try {
            if ((obj instanceof JSONObject) && keySet().equals(((JSONObject) obj).keySet())) {
                for (Map.Entry<String, Object> entry : entrySet()) {
                    Object value = entry.getValue();
                    Object obj2 = ((JSONObject) obj).get(entry.getKey());
                    if (value != obj2) {
                        if (value == null) {
                            return false;
                        }
                        if (value instanceof JSONObject) {
                            if (!((JSONObject) value).similar(obj2)) {
                                return false;
                            }
                        } else if (value instanceof JSONArray) {
                            if (!((JSONArray) value).similar(obj2)) {
                                return false;
                            }
                        } else if (!value.equals(obj2)) {
                            return false;
                        }
                    }
                }
                return true;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public JSONArray toJSONArray(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            jSONArray2.put(opt(jSONArray.getString(i)));
        }
        return jSONArray2;
    }

    public Map<String, Object> toMap() {
        Object obj;
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, Object> entry : entrySet()) {
            if (entry.getValue() == null || NULL.equals(entry.getValue())) {
                obj = null;
            } else if (entry.getValue() instanceof JSONObject) {
                obj = ((JSONObject) entry.getValue()).toMap();
            } else if (entry.getValue() instanceof JSONArray) {
                obj = ((JSONArray) entry.getValue()).toList();
            } else {
                obj = entry.getValue();
            }
            hashMap.put(entry.getKey(), obj);
        }
        return hashMap;
    }

    public String toString() {
        try {
            return toString(0);
        } catch (Exception unused) {
            return null;
        }
    }

    public Writer write(Writer writer) throws JSONException {
        return write(writer, 0, 0);
    }

    public boolean optBoolean(String str, boolean z) {
        Object opt = opt(str);
        if (NULL.equals(opt)) {
            return z;
        }
        if (opt instanceof Boolean) {
            return ((Boolean) opt).booleanValue();
        }
        try {
            return getBoolean(str);
        } catch (Exception unused) {
            return z;
        }
    }

    public double optDouble(String str, double d) {
        Object opt = opt(str);
        if (NULL.equals(opt)) {
            return d;
        }
        if (opt instanceof Number) {
            return ((Number) opt).doubleValue();
        }
        if (opt instanceof String) {
            try {
                return Double.parseDouble((String) opt);
            } catch (Exception unused) {
            }
        }
        return d;
    }

    public <E extends Enum<E>> E optEnum(Class<E> cls, String str, E e) {
        try {
            Object opt = opt(str);
            if (NULL.equals(opt)) {
                return e;
            }
            if (cls.isAssignableFrom(opt.getClass())) {
                return (E) opt;
            }
            return (E) Enum.valueOf(cls, opt.toString());
        } catch (IllegalArgumentException | NullPointerException unused) {
            return e;
        }
    }

    public float optFloat(String str, float f) {
        Object opt = opt(str);
        if (NULL.equals(opt)) {
            return f;
        }
        if (opt instanceof Number) {
            return ((Number) opt).floatValue();
        }
        if (opt instanceof String) {
            try {
                return Float.parseFloat((String) opt);
            } catch (Exception unused) {
            }
        }
        return f;
    }

    public int optInt(String str, int i) {
        Object opt = opt(str);
        if (NULL.equals(opt)) {
            return i;
        }
        if (opt instanceof Number) {
            return ((Number) opt).intValue();
        }
        if (opt instanceof String) {
            try {
                return new BigDecimal((String) opt).intValue();
            } catch (Exception unused) {
            }
        }
        return i;
    }

    public long optLong(String str, long j) {
        Object opt = opt(str);
        if (NULL.equals(opt)) {
            return j;
        }
        if (opt instanceof Number) {
            return ((Number) opt).longValue();
        }
        if (opt instanceof String) {
            try {
                return new BigDecimal((String) opt).longValue();
            } catch (Exception unused) {
            }
        }
        return j;
    }

    public Number optNumber(String str, Number number) {
        Object opt = opt(str);
        if (NULL.equals(opt)) {
            return number;
        }
        if (opt instanceof Number) {
            return (Number) opt;
        }
        if (opt instanceof String) {
            try {
                return stringToNumber((String) opt);
            } catch (Exception unused) {
            }
        }
        return number;
    }

    public Object optQuery(JSONPointer jSONPointer) {
        try {
            return jSONPointer.queryFrom(this);
        } catch (JSONPointerException unused) {
            return null;
        }
    }

    public String optString(String str, String str2) {
        Object opt = opt(str);
        return NULL.equals(opt) ? str2 : opt.toString();
    }

    public JSONObject put(String str, Collection<?> collection) throws JSONException {
        put(str, new JSONArray(collection));
        return this;
    }

    public Object query(JSONPointer jSONPointer) {
        return jSONPointer.queryFrom(this);
    }

    public String toString(int i) throws JSONException {
        String obj;
        StringWriter stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            obj = write(stringWriter, i, 0).toString();
        }
        return obj;
    }

    public Writer write(Writer writer, int i, int i2) throws JSONException {
        boolean z = false;
        try {
            int length = length();
            writer.write(123);
            if (length == 1) {
                Map.Entry<String, Object> next = entrySet().iterator().next();
                String key = next.getKey();
                writer.write(quote(key));
                writer.write(58);
                if (i > 0) {
                    writer.write(32);
                }
                try {
                    c(writer, next.getValue(), i, i2);
                    writer.write(125);
                    return writer;
                } catch (Exception e) {
                    throw new JSONException("Unable to write JSONObject value for key: " + key, e);
                }
            }
            if (length != 0) {
                int i3 = i2 + i;
                for (Map.Entry<String, Object> entry : entrySet()) {
                    if (z) {
                        writer.write(44);
                    }
                    if (i > 0) {
                        writer.write(10);
                    }
                    a(writer, i3);
                    String key2 = entry.getKey();
                    writer.write(quote(key2));
                    writer.write(58);
                    if (i > 0) {
                        writer.write(32);
                    }
                    try {
                        c(writer, entry.getValue(), i, i3);
                        z = true;
                    } catch (Exception e2) {
                        throw new JSONException("Unable to write JSONObject value for key: " + key2, e2);
                    }
                }
                if (i > 0) {
                    writer.write(10);
                }
                a(writer, i2);
            }
            writer.write(125);
            return writer;
        } catch (IOException e3) {
            throw new JSONException(e3);
        }
    }

    public JSONObject(JSONObject jSONObject, String[] strArr) {
        this(strArr.length);
        for (int i = 0; i < strArr.length; i++) {
            try {
                putOnce(strArr[i], jSONObject.opt(strArr[i]));
            } catch (Exception unused) {
            }
        }
    }

    public static String[] getNames(Object obj) {
        Field[] fields;
        int length;
        if (obj == null || (length = (fields = obj.getClass().getFields()).length) == 0) {
            return null;
        }
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = fields[i].getName();
        }
        return strArr;
    }

    public JSONObject put(String str, double d) throws JSONException {
        put(str, Double.valueOf(d));
        return this;
    }

    public JSONObject put(String str, float f) throws JSONException {
        put(str, Float.valueOf(f));
        return this;
    }

    public JSONObject put(String str, int i) throws JSONException {
        put(str, Integer.valueOf(i));
        return this;
    }

    public JSONObject(JSONTokener jSONTokener) throws JSONException {
        this();
        if (jSONTokener.nextClean() != '{') {
            throw jSONTokener.syntaxError("A JSONObject text must begin with '{'");
        }
        while (true) {
            char nextClean = jSONTokener.nextClean();
            if (nextClean == 0) {
                throw jSONTokener.syntaxError("A JSONObject text must end with '}'");
            }
            if (nextClean == '}') {
                return;
            }
            jSONTokener.back();
            String obj = jSONTokener.nextValue().toString();
            if (jSONTokener.nextClean() == ':') {
                if (obj != null) {
                    if (opt(obj) == null) {
                        Object nextValue = jSONTokener.nextValue();
                        if (nextValue != null) {
                            put(obj, nextValue);
                        }
                    } else {
                        throw jSONTokener.syntaxError("Duplicate key \"" + obj + "\"");
                    }
                }
                char nextClean2 = jSONTokener.nextClean();
                if (nextClean2 != ',' && nextClean2 != ';') {
                    if (nextClean2 != '}') {
                        throw jSONTokener.syntaxError("Expected a ',' or '}'");
                    }
                    return;
                } else if (jSONTokener.nextClean() == '}') {
                    return;
                } else {
                    jSONTokener.back();
                }
            } else {
                throw jSONTokener.syntaxError("Expected a ':' after a key");
            }
        }
    }

    public static Writer quote(String str, Writer writer) throws IOException {
        if (str != null && str.length() != 0) {
            int length = str.length();
            writer.write(34);
            int i = 0;
            char c = 0;
            while (i < length) {
                char charAt = str.charAt(i);
                if (charAt == '\f') {
                    writer.write("\\f");
                } else if (charAt != '\r') {
                    if (charAt != '\"') {
                        if (charAt == '/') {
                            if (c == '<') {
                                writer.write(92);
                            }
                            writer.write(charAt);
                        } else if (charAt != '\\') {
                            switch (charAt) {
                                case '\b':
                                    writer.write("\\b");
                                    continue;
                                case '\t':
                                    writer.write("\\t");
                                    continue;
                                case '\n':
                                    writer.write("\\n");
                                    continue;
                                default:
                                    if (charAt >= ' ' && ((charAt < 128 || charAt >= 160) && (charAt < 8192 || charAt >= 8448))) {
                                        writer.write(charAt);
                                        continue;
                                    } else {
                                        writer.write("\\u");
                                        String hexString = Integer.toHexString(charAt);
                                        writer.write("0000", 0, 4 - hexString.length());
                                        writer.write(hexString);
                                        break;
                                    }
                            }
                        }
                    }
                    writer.write(92);
                    writer.write(charAt);
                } else {
                    writer.write("\\r");
                }
                i++;
                c = charAt;
            }
            writer.write(34);
            return writer;
        }
        writer.write("\"\"");
        return writer;
    }

    public JSONObject put(String str, long j) throws JSONException {
        put(str, Long.valueOf(j));
        return this;
    }

    public JSONObject put(String str, Map<?, ?> map) throws JSONException {
        put(str, new JSONObject(map));
        return this;
    }

    public JSONObject put(String str, Object obj) throws JSONException {
        Objects.requireNonNull(str, "Null key.");
        if (obj != null) {
            testValidity(obj);
            this.f15560a.put(str, obj);
        } else {
            remove(str);
        }
        return this;
    }

    public JSONObject(Map<?, ?> map) {
        if (map == null) {
            this.f15560a = new HashMap();
            return;
        }
        this.f15560a = new HashMap(map.size());
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                this.f15560a.put(String.valueOf(entry.getKey()), wrap(value));
            }
        }
    }

    public JSONObject(Object obj) {
        this();
        b(obj);
    }

    public JSONObject(Object obj, String[] strArr) {
        this(strArr.length);
        Class<?> cls = obj.getClass();
        for (String str : strArr) {
            try {
                putOpt(str, cls.getField(str).get(obj));
            } catch (Exception unused) {
            }
        }
    }

    public JSONObject(String str) throws JSONException {
        this(new JSONTokener(str));
    }

    public JSONObject(String str, Locale locale) throws JSONException {
        this();
        ResourceBundle bundle = ResourceBundle.getBundle(str, locale, Thread.currentThread().getContextClassLoader());
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String nextElement = keys.nextElement();
            if (nextElement != null) {
                String str2 = nextElement;
                String[] split = str2.split("\\.");
                int length = split.length - 1;
                JSONObject jSONObject = this;
                for (int i = 0; i < length; i++) {
                    String str3 = split[i];
                    JSONObject optJSONObject = jSONObject.optJSONObject(str3);
                    if (optJSONObject == null) {
                        optJSONObject = new JSONObject();
                        jSONObject.put(str3, optJSONObject);
                    }
                    jSONObject = optJSONObject;
                }
                jSONObject.put(split[length], bundle.getString(str2));
            }
        }
    }

    public JSONObject(int i) {
        this.f15560a = new HashMap(i);
    }
}
