package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: classes.dex */
public class JSONSerializer {

    /* renamed from: a  reason: collision with root package name */
    public int f2131a;
    public List<AfterFilter> afterFilters;
    public String b;
    public List<BeforeFilter> beforeFilters;
    public DateFormat c;
    public final SerializeConfig config;
    public SerialContext context;
    public Locale locale;
    public List<NameFilter> nameFilters;
    public final SerializeWriter out;
    public List<PropertyFilter> propertyFilters;
    public List<PropertyPreFilter> propertyPreFilters;
    public IdentityHashMap<Object, SerialContext> references;
    public TimeZone timeZone;
    public List<ValueFilter> valueFilters;

    public JSONSerializer() {
        this(new SerializeWriter(null, JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.EMPTY), SerializeConfig.globalInstance);
    }

    public static Object processValue(JSONSerializer jSONSerializer, Object obj, Object obj2, Object obj3) {
        List<ValueFilter> list = jSONSerializer.valueFilters;
        if (list != null) {
            if (obj2 != null && !(obj2 instanceof String)) {
                obj2 = JSON.toJSONString(obj2);
            }
            for (ValueFilter valueFilter : list) {
                obj3 = valueFilter.process(obj, (String) obj2, obj3);
            }
        }
        return obj3;
    }

    public static final void write(Writer writer, Object obj) {
        SerializeWriter serializeWriter = new SerializeWriter(null, JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.EMPTY);
        try {
            try {
                new JSONSerializer(serializeWriter, SerializeConfig.globalInstance).write(obj);
                serializeWriter.writeTo(writer);
            } catch (IOException e) {
                throw new JSONException(e.getMessage(), e);
            }
        } finally {
            serializeWriter.close();
        }
    }

    public boolean apply(Object obj, Object obj2, Object obj3) {
        List<PropertyFilter> list = this.propertyFilters;
        if (list == null) {
            return true;
        }
        if (obj2 != null && !(obj2 instanceof String)) {
            obj2 = JSON.toJSONString(obj2);
        }
        for (PropertyFilter propertyFilter : list) {
            if (!propertyFilter.apply(obj, (String) obj2, obj3)) {
                return false;
            }
        }
        return true;
    }

    public boolean applyName(Object obj, Object obj2) {
        List<PropertyPreFilter> list = this.propertyPreFilters;
        if (list == null) {
            return true;
        }
        for (PropertyPreFilter propertyPreFilter : list) {
            if (obj2 != null && !(obj2 instanceof String)) {
                obj2 = JSON.toJSONString(obj2);
            }
            if (!propertyPreFilter.apply(this, obj, (String) obj2)) {
                return false;
            }
        }
        return true;
    }

    public void close() {
        this.out.close();
    }

    public void config(SerializerFeature serializerFeature, boolean z) {
        this.out.config(serializerFeature, z);
    }

    public void decrementIdent() {
        this.f2131a--;
    }

    public List<AfterFilter> getAfterFilters() {
        if (this.afterFilters == null) {
            this.afterFilters = new ArrayList();
        }
        return this.afterFilters;
    }

    public List<BeforeFilter> getBeforeFilters() {
        if (this.beforeFilters == null) {
            this.beforeFilters = new ArrayList();
        }
        return this.beforeFilters;
    }

    public SerialContext getContext() {
        return this.context;
    }

    public DateFormat getDateFormat() {
        if (this.c == null && this.b != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.b, this.locale);
            this.c = simpleDateFormat;
            simpleDateFormat.setTimeZone(this.timeZone);
        }
        return this.c;
    }

    public String getDateFormatPattern() {
        DateFormat dateFormat = this.c;
        if (dateFormat instanceof SimpleDateFormat) {
            return ((SimpleDateFormat) dateFormat).toPattern();
        }
        return this.b;
    }

    public List<NameFilter> getNameFilters() {
        if (this.nameFilters == null) {
            this.nameFilters = new ArrayList();
        }
        return this.nameFilters;
    }

    public List<PropertyFilter> getPropertyFilters() {
        if (this.propertyFilters == null) {
            this.propertyFilters = new ArrayList();
        }
        return this.propertyFilters;
    }

    public List<PropertyPreFilter> getPropertyPreFilters() {
        if (this.propertyPreFilters == null) {
            this.propertyPreFilters = new ArrayList();
        }
        return this.propertyPreFilters;
    }

    public List<ValueFilter> getValueFilters() {
        if (this.valueFilters == null) {
            this.valueFilters = new ArrayList();
        }
        return this.valueFilters;
    }

    public SerializeWriter getWriter() {
        return this.out;
    }

    public void incrementIndent() {
        this.f2131a++;
    }

    public void println() {
        this.out.write(10);
        for (int i = 0; i < this.f2131a; i++) {
            this.out.write(9);
        }
    }

    public Object processKey(Object obj, Object obj2, Object obj3) {
        List<NameFilter> list = this.nameFilters;
        if (list != null) {
            if (obj2 != null && !(obj2 instanceof String)) {
                obj2 = JSON.toJSONString(obj2);
            }
            for (NameFilter nameFilter : list) {
                obj2 = nameFilter.process(obj, (String) obj2, obj3);
            }
        }
        return obj2;
    }

    public void setContext(SerialContext serialContext, Object obj, Object obj2, int i) {
        if ((this.out.features & SerializerFeature.DisableCircularReferenceDetect.mask) == 0) {
            this.context = new SerialContext(serialContext, obj, obj2, i);
            if (this.references == null) {
                this.references = new IdentityHashMap<>();
            }
            this.references.put(obj, this.context);
        }
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.c = dateFormat;
        if (this.b != null) {
            this.b = null;
        }
    }

    public String toString() {
        return this.out.toString();
    }

    public final void writeKeyValue(char c, String str, Object obj) {
        if (c != 0) {
            this.out.write(c);
        }
        this.out.writeFieldName(str, true);
        write(obj);
    }

    public void writeReference(Object obj) {
        SerialContext serialContext = this.context;
        if (obj == serialContext.object) {
            this.out.write("{\"$ref\":\"@\"}");
            return;
        }
        SerialContext serialContext2 = serialContext.parent;
        if (serialContext2 != null && obj == serialContext2.object) {
            this.out.write("{\"$ref\":\"..\"}");
            return;
        }
        while (true) {
            SerialContext serialContext3 = serialContext.parent;
            if (serialContext3 == null) {
                break;
            }
            serialContext = serialContext3;
        }
        if (obj == serialContext.object) {
            this.out.write("{\"$ref\":\"$\"}");
            return;
        }
        String serialContext4 = this.references.get(obj).toString();
        this.out.write("{\"$ref\":\"");
        this.out.write(serialContext4);
        this.out.write("\"}");
    }

    public final void writeWithFieldName(Object obj, Object obj2) {
        writeWithFieldName(obj, obj2, null, 0);
    }

    public final void writeWithFormat(Object obj, String str) {
        if (obj instanceof Date) {
            DateFormat dateFormat = getDateFormat();
            if (dateFormat == null) {
                dateFormat = new SimpleDateFormat(str, this.locale);
                dateFormat.setTimeZone(this.timeZone);
            }
            this.out.writeString(dateFormat.format((Date) obj));
            return;
        }
        write(obj);
    }

    public JSONSerializer(SerializeWriter serializeWriter) {
        this(serializeWriter, SerializeConfig.globalInstance);
    }

    public final void writeWithFieldName(Object obj, Object obj2, Type type, int i) {
        try {
            if (obj == null) {
                this.out.writeNull();
                return;
            }
            this.config.get(obj.getClass()).write(this, obj, obj2, type);
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public JSONSerializer(SerializeConfig serializeConfig) {
        this(new SerializeWriter(null, JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.EMPTY), serializeConfig);
    }

    public JSONSerializer(SerializeWriter serializeWriter, SerializeConfig serializeConfig) {
        this.beforeFilters = null;
        this.afterFilters = null;
        this.propertyFilters = null;
        this.valueFilters = null;
        this.nameFilters = null;
        this.propertyPreFilters = null;
        this.f2131a = 0;
        this.references = null;
        this.timeZone = JSON.defaultTimeZone;
        this.locale = JSON.defaultLocale;
        this.out = serializeWriter;
        this.config = serializeConfig;
        this.timeZone = JSON.defaultTimeZone;
    }

    public void setDateFormat(String str) {
        this.b = str;
        if (this.c != null) {
            this.c = null;
        }
    }

    public static final void write(SerializeWriter serializeWriter, Object obj) {
        new JSONSerializer(serializeWriter, SerializeConfig.globalInstance).write(obj);
    }

    public final void write(Object obj) {
        if (obj == null) {
            this.out.writeNull();
            return;
        }
        try {
            this.config.get(obj.getClass()).write(this, obj, null, null);
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public final void write(String str) {
        if (str == null) {
            SerializeWriter serializeWriter = this.out;
            if ((serializeWriter.features & SerializerFeature.WriteNullStringAsEmpty.mask) != 0) {
                serializeWriter.writeString("");
                return;
            } else {
                serializeWriter.writeNull();
                return;
            }
        }
        SerializeWriter serializeWriter2 = this.out;
        if ((serializeWriter2.features & SerializerFeature.UseSingleQuotes.mask) != 0) {
            serializeWriter2.writeStringWithSingleQuote(str);
        } else {
            serializeWriter2.writeStringWithDoubleQuote(str, (char) 0, true);
        }
    }
}
