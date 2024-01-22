package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes.dex */
public class c extends FieldDeserializer {

    /* renamed from: a  reason: collision with root package name */
    public final Type f2125a;
    public ObjectDeserializer b;
    public final boolean c;

    public c(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo, 14);
        Type type = fieldInfo.fieldType;
        Class<?> cls2 = fieldInfo.fieldClass;
        if (cls2.isArray()) {
            this.f2125a = cls2.getComponentType();
            this.c = true;
            return;
        }
        this.f2125a = TypeUtils.getCollectionItemType(type);
        this.c = false;
    }

    public final void a(DefaultJSONParser defaultJSONParser, Type type, Collection collection) {
        Class cls;
        int i;
        int i2;
        Type type2 = this.f2125a;
        ObjectDeserializer objectDeserializer = this.b;
        if (type instanceof ParameterizedType) {
            if (type2 instanceof TypeVariable) {
                TypeVariable typeVariable = (TypeVariable) type2;
                ParameterizedType parameterizedType = (ParameterizedType) type;
                cls = parameterizedType.getRawType() instanceof Class ? (Class) parameterizedType.getRawType() : null;
                if (cls != null) {
                    int length = cls.getTypeParameters().length;
                    i2 = 0;
                    while (i2 < length) {
                        if (cls.getTypeParameters()[i2].getName().equals(typeVariable.getName())) {
                            break;
                        }
                        i2++;
                    }
                }
                i2 = -1;
                if (i2 != -1) {
                    type2 = parameterizedType.getActualTypeArguments()[i2];
                    if (!type2.equals(this.f2125a)) {
                        objectDeserializer = defaultJSONParser.config.getDeserializer(type2);
                    }
                }
            } else if (type2 instanceof ParameterizedType) {
                ParameterizedType parameterizedType2 = (ParameterizedType) type2;
                Type[] actualTypeArguments = parameterizedType2.getActualTypeArguments();
                if (actualTypeArguments.length == 1 && (actualTypeArguments[0] instanceof TypeVariable)) {
                    TypeVariable typeVariable2 = (TypeVariable) actualTypeArguments[0];
                    ParameterizedType parameterizedType3 = (ParameterizedType) type;
                    cls = parameterizedType3.getRawType() instanceof Class ? (Class) parameterizedType3.getRawType() : null;
                    if (cls != null) {
                        int length2 = cls.getTypeParameters().length;
                        i = 0;
                        while (i < length2) {
                            if (cls.getTypeParameters()[i].getName().equals(typeVariable2.getName())) {
                                break;
                            }
                            i++;
                        }
                    }
                    i = -1;
                    if (i != -1) {
                        actualTypeArguments[0] = parameterizedType3.getActualTypeArguments()[i];
                        type2 = new ParameterizedTypeImpl(actualTypeArguments, parameterizedType2.getOwnerType(), parameterizedType2.getRawType());
                    }
                }
            }
        } else if ((type2 instanceof TypeVariable) && (type instanceof Class)) {
            Class cls2 = (Class) type;
            TypeVariable typeVariable3 = (TypeVariable) type2;
            cls2.getTypeParameters();
            int length3 = cls2.getTypeParameters().length;
            int i3 = 0;
            while (true) {
                if (i3 >= length3) {
                    break;
                }
                TypeVariable typeVariable4 = cls2.getTypeParameters()[i3];
                if (typeVariable4.getName().equals(typeVariable3.getName())) {
                    Type[] bounds = typeVariable4.getBounds();
                    if (bounds.length == 1) {
                        type2 = bounds[0];
                    }
                } else {
                    i3++;
                }
            }
        }
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (objectDeserializer == null) {
            objectDeserializer = defaultJSONParser.config.getDeserializer(type2);
            this.b = objectDeserializer;
        }
        int i4 = jSONLexer.token;
        if (i4 != 14) {
            if (i4 == 12) {
                collection.add(objectDeserializer.deserialze(defaultJSONParser, type2, 0));
                return;
            }
            String str = "exepct '[', but " + JSONToken.name(jSONLexer.token);
            if (type != null) {
                str = str + ", type : " + type;
            }
            throw new JSONException(str);
        }
        int i5 = 0;
        char c = jSONLexer.ch;
        int i6 = 15;
        char c2 = JSONLexer.EOI;
        if (c == '[') {
            int i7 = jSONLexer.bp + 1;
            jSONLexer.bp = i7;
            jSONLexer.ch = i7 >= jSONLexer.len ? (char) 26 : jSONLexer.text.charAt(i7);
            jSONLexer.token = 14;
        } else if (c == '{') {
            int i8 = jSONLexer.bp + 1;
            jSONLexer.bp = i8;
            jSONLexer.ch = i8 >= jSONLexer.len ? (char) 26 : jSONLexer.text.charAt(i8);
            jSONLexer.token = 12;
        } else if (c == '\"') {
            jSONLexer.scanString();
        } else if (c == ']') {
            int i9 = jSONLexer.bp + 1;
            jSONLexer.bp = i9;
            jSONLexer.ch = i9 >= jSONLexer.len ? (char) 26 : jSONLexer.text.charAt(i9);
            jSONLexer.token = 15;
        } else {
            jSONLexer.nextToken();
        }
        while (true) {
            int i10 = jSONLexer.token;
            if (i10 == 16) {
                jSONLexer.nextToken();
            } else if (i10 == i6) {
                break;
            } else {
                collection.add(objectDeserializer.deserialze(defaultJSONParser, type2, Integer.valueOf(i5)));
                if (defaultJSONParser.resolveStatus == 1) {
                    defaultJSONParser.checkListResolve(collection);
                }
                if (jSONLexer.token == 16) {
                    char c3 = jSONLexer.ch;
                    if (c3 == '[') {
                        int i11 = jSONLexer.bp + 1;
                        jSONLexer.bp = i11;
                        jSONLexer.ch = i11 >= jSONLexer.len ? (char) 26 : jSONLexer.text.charAt(i11);
                        jSONLexer.token = 14;
                    } else if (c3 == '{') {
                        int i12 = jSONLexer.bp + 1;
                        jSONLexer.bp = i12;
                        jSONLexer.ch = i12 >= jSONLexer.len ? (char) 26 : jSONLexer.text.charAt(i12);
                        jSONLexer.token = 12;
                    } else if (c3 == '\"') {
                        jSONLexer.scanString();
                    } else {
                        jSONLexer.nextToken();
                    }
                }
                i5++;
                i6 = 15;
            }
        }
        if (jSONLexer.ch == ',') {
            int i13 = jSONLexer.bp + 1;
            jSONLexer.bp = i13;
            if (i13 < jSONLexer.len) {
                c2 = jSONLexer.text.charAt(i13);
            }
            jSONLexer.ch = c2;
            jSONLexer.token = 16;
            return;
        }
        jSONLexer.nextToken();
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        JSONArray arrayList;
        JSONArray jSONArray;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i = jSONLexer.token();
        if (i != 8 && (i != 4 || jSONLexer.stringVal().length() != 0)) {
            if (this.c) {
                JSONArray jSONArray2 = new JSONArray();
                jSONArray2.setComponentType(this.f2125a);
                jSONArray = jSONArray2;
                arrayList = jSONArray2;
            } else {
                arrayList = new ArrayList();
                jSONArray = null;
            }
            ParseContext parseContext = defaultJSONParser.contex;
            defaultJSONParser.setContext(parseContext, obj, this.fieldInfo.name);
            a(defaultJSONParser, type, arrayList);
            defaultJSONParser.setContext(parseContext);
            Object obj2 = arrayList;
            if (this.c) {
                Object array = arrayList.toArray((Object[]) Array.newInstance((Class) this.f2125a, arrayList.size()));
                jSONArray.setRelatedArray(array);
                obj2 = array;
            }
            if (obj == null) {
                map.put(this.fieldInfo.name, obj2);
                return;
            } else {
                setValue(obj, obj2);
                return;
            }
        }
        setValue(obj, (Object) null);
        defaultJSONParser.lexer.nextToken();
    }
}
