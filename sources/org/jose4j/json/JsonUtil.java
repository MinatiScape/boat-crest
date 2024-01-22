package org.jose4j.json;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jose4j.json.internal.json_simple.JSONValue;
import org.jose4j.json.internal.json_simple.parser.ContainerFactory;
import org.jose4j.json.internal.json_simple.parser.JSONParser;
import org.jose4j.json.internal.json_simple.parser.ParseException;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public class JsonUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final ContainerFactory f15503a = new a();

    /* loaded from: classes13.dex */
    public class a implements ContainerFactory {
        @Override // org.jose4j.json.internal.json_simple.parser.ContainerFactory
        public List creatArrayContainer() {
            return new ArrayList();
        }

        @Override // org.jose4j.json.internal.json_simple.parser.ContainerFactory
        public Map createObjectContainer() {
            return new b();
        }
    }

    /* loaded from: classes13.dex */
    public static class b extends LinkedHashMap<String, Object> {
        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public Object put(String str, Object obj) {
            if (!containsKey(str)) {
                return super.put((b) str, (String) obj);
            }
            throw new IllegalArgumentException("An entry for '" + str + "' already exists. Names must be unique.");
        }
    }

    public static Map<String, Object> parseJson(String str) throws JoseException {
        try {
            Object parse = new JSONParser().parse(str, f15503a);
            if (parse != null) {
                return (Map) parse;
            }
            throw new JoseException("Parsing returned null");
        } catch (ClassCastException e) {
            throw new JoseException("Expecting a JSON object at the root but " + e, e);
        } catch (IllegalArgumentException e2) {
            e = e2;
            throw new JoseException("Parsing error: " + e, e);
        } catch (ParseException e3) {
            e = e3;
            throw new JoseException("Parsing error: " + e, e);
        }
    }

    public static String toJson(Map<String, ?> map) {
        return JSONValue.toJSONString(map);
    }

    public static void writeJson(Map<String, ?> map, Writer writer) throws IOException {
        JSONValue.writeJSONString(map, writer);
    }
}
