package com.alibaba.fastjson;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Map;
/* loaded from: classes.dex */
public class JSONReader implements Closeable {
    public final DefaultJSONParser h;
    public a i;
    public Reader j;

    public JSONReader(Reader reader) {
        this(new JSONLexer(c(reader)));
        this.j = reader;
    }

    public static String c(Reader reader) {
        StringBuilder sb = new StringBuilder();
        try {
            char[] cArr = new char[2048];
            while (true) {
                int read = reader.read(cArr, 0, 2048);
                if (read < 0) {
                    return sb.toString();
                }
                sb.append(cArr, 0, read);
            }
        } catch (Exception e) {
            throw new JSONException("read string from reader error", e);
        }
    }

    public final void a() {
        int i;
        a aVar = this.i.f2114a;
        this.i = aVar;
        if (aVar == null) {
            return;
        }
        switch (aVar.b) {
            case 1001:
            case 1003:
                i = 1002;
                break;
            case 1002:
                i = 1003;
                break;
            case 1004:
                i = 1005;
                break;
            default:
                i = -1;
                break;
        }
        if (i != -1) {
            aVar.b = i;
        }
    }

    public final void b() {
        a aVar = this.i;
        int i = aVar.b;
        int i2 = 1002;
        switch (i) {
            case 1001:
            case 1003:
                break;
            case 1002:
                i2 = 1003;
                break;
            case 1004:
                i2 = 1005;
                break;
            case 1005:
                i2 = -1;
                break;
            default:
                throw new JSONException("illegal state : " + i);
        }
        if (i2 != -1) {
            aVar.b = i2;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.h.lexer.close();
        Reader reader = this.j;
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                throw new JSONException("closed reader error", e);
            }
        }
    }

    public void config(Feature feature, boolean z) {
        this.h.config(feature, z);
    }

    public final void d() {
        int i = this.i.b;
        switch (i) {
            case 1001:
            case 1004:
                return;
            case 1002:
                this.h.accept(17);
                return;
            case 1003:
            case 1005:
                this.h.accept(16);
                return;
            default:
                throw new JSONException("illegal state : " + i);
        }
    }

    public final void e() {
        switch (this.i.b) {
            case 1001:
            case 1004:
                return;
            case 1002:
                this.h.accept(17);
                return;
            case 1003:
            case 1005:
                this.h.accept(16);
                return;
            default:
                throw new JSONException("illegal state : " + this.i.b);
        }
    }

    public void endArray() {
        this.h.accept(15);
        a();
    }

    public void endObject() {
        this.h.accept(13);
        a();
    }

    public boolean hasNext() {
        if (this.i != null) {
            int i = this.h.lexer.token();
            int i2 = this.i.b;
            switch (i2) {
                case 1001:
                case 1003:
                    return i != 13;
                case 1002:
                default:
                    throw new JSONException("illegal state : " + i2);
                case 1004:
                case 1005:
                    return i != 15;
            }
        }
        throw new JSONException("context is null");
    }

    public int peek() {
        return this.h.lexer.token();
    }

    public Integer readInteger() {
        Object parse;
        if (this.i == null) {
            parse = this.h.parse();
        } else {
            d();
            parse = this.h.parse();
            b();
        }
        return TypeUtils.castToInt(parse);
    }

    public Long readLong() {
        Object parse;
        if (this.i == null) {
            parse = this.h.parse();
        } else {
            d();
            parse = this.h.parse();
            b();
        }
        return TypeUtils.castToLong(parse);
    }

    public <T> T readObject(TypeReference<T> typeReference) {
        return (T) readObject(typeReference.type);
    }

    public String readString() {
        Object parse;
        if (this.i == null) {
            parse = this.h.parse();
        } else {
            d();
            parse = this.h.parse();
            b();
        }
        return TypeUtils.castToString(parse);
    }

    public void startArray() {
        if (this.i == null) {
            this.i = new a(null, 1004);
        } else {
            e();
            this.i = new a(this.i, 1004);
        }
        this.h.accept(14);
    }

    public void startObject() {
        if (this.i == null) {
            this.i = new a(null, 1001);
        } else {
            e();
            this.i = new a(this.i, 1001);
        }
        this.h.accept(12);
    }

    public <T> T readObject(Type type) {
        if (this.i == null) {
            return (T) this.h.parseObject(type);
        }
        d();
        T t = (T) this.h.parseObject(type);
        b();
        return t;
    }

    public JSONReader(JSONLexer jSONLexer) {
        this(new DefaultJSONParser(jSONLexer));
    }

    public JSONReader(DefaultJSONParser defaultJSONParser) {
        this.h = defaultJSONParser;
    }

    public <T> T readObject(Class<T> cls) {
        if (this.i == null) {
            return (T) this.h.parseObject((Class<Object>) cls);
        }
        d();
        T t = (T) this.h.parseObject((Class<Object>) cls);
        b();
        return t;
    }

    public void readObject(Object obj) {
        if (this.i == null) {
            this.h.parseObject(obj);
            return;
        }
        d();
        this.h.parseObject(obj);
        b();
    }

    public Object readObject() {
        if (this.i == null) {
            return this.h.parse();
        }
        d();
        Object parse = this.h.parse();
        b();
        return parse;
    }

    public Object readObject(Map map) {
        if (this.i == null) {
            return this.h.parseObject(map);
        }
        d();
        Object parseObject = this.h.parseObject(map);
        b();
        return parseObject;
    }
}
