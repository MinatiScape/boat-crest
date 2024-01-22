package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class b implements ObjectDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public static final b f2124a = new b();

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        if (type instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            if (genericComponentType instanceof TypeVariable) {
                genericComponentType = ((TypeVariable) genericComponentType).getBounds()[0];
            }
            ArrayList arrayList = new ArrayList();
            defaultJSONParser.parseArray(genericComponentType, arrayList);
            if (genericComponentType instanceof Class) {
                T t = (T) ((Object[]) Array.newInstance((Class) genericComponentType, arrayList.size()));
                arrayList.toArray(t);
                return t;
            }
            return (T) arrayList.toArray();
        }
        return (T) defaultJSONParser.parse(obj);
    }
}
