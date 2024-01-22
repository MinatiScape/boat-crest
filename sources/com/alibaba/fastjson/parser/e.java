package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class e extends FieldDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public final int f2127a;
    public final List b;
    public final DefaultJSONParser c;
    public final Object d;
    public final Map e;
    public final Collection f;

    public e(DefaultJSONParser defaultJSONParser, List list, int i) {
        super(null, null, 0);
        this.c = defaultJSONParser;
        this.f2127a = i;
        this.b = list;
        this.d = null;
        this.e = null;
        this.f = null;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void setValue(Object obj, Object obj2) {
        JSONArray jSONArray;
        Object relatedArray;
        Map map = this.e;
        if (map != null) {
            map.put(this.d, obj2);
            return;
        }
        Collection collection = this.f;
        if (collection != null) {
            collection.add(obj2);
            return;
        }
        this.b.set(this.f2127a, obj2);
        List list = this.b;
        if (!(list instanceof JSONArray) || (relatedArray = (jSONArray = (JSONArray) list).getRelatedArray()) == null || Array.getLength(relatedArray) <= this.f2127a) {
            return;
        }
        if (jSONArray.getComponentType() != null) {
            obj2 = TypeUtils.cast(obj2, jSONArray.getComponentType(), this.c.config);
        }
        Array.set(relatedArray, this.f2127a, obj2);
    }

    public e(Map map, Object obj) {
        super(null, null, 0);
        this.c = null;
        this.f2127a = -1;
        this.b = null;
        this.d = obj;
        this.e = map;
        this.f = null;
    }

    public e(Collection collection) {
        super(null, null, 0);
        this.c = null;
        this.f2127a = -1;
        this.b = null;
        this.d = null;
        this.e = null;
        this.f = collection;
    }
}
