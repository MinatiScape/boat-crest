package org.json;

import com.android.volley.toolbox.JsonRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes13.dex */
public class JSONPointer {

    /* renamed from: a  reason: collision with root package name */
    public final List<String> f15561a;

    public JSONPointer(String str) {
        String substring;
        Objects.requireNonNull(str, "pointer cannot be null");
        if (!str.isEmpty() && !str.equals(MqttTopic.MULTI_LEVEL_WILDCARD)) {
            if (str.startsWith("#/")) {
                try {
                    substring = URLDecoder.decode(str.substring(2), JsonRequest.PROTOCOL_CHARSET);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            } else if (str.startsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                substring = str.substring(1);
            } else {
                throw new IllegalArgumentException("a JSON pointer should start with '/' or '#/'");
            }
            this.f15561a = new ArrayList();
            for (String str2 : substring.split(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                this.f15561a.add(c(str2));
            }
            return;
        }
        this.f15561a = Collections.emptyList();
    }

    public static Builder builder() {
        return new Builder();
    }

    public final String a(String str) {
        return str.replace("~", "~0").replace(MqttTopic.TOPIC_LEVEL_SEPARATOR, "~1").replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public final Object b(Object obj, String str) throws JSONPointerException {
        try {
            int parseInt = Integer.parseInt(str);
            JSONArray jSONArray = (JSONArray) obj;
            if (parseInt < jSONArray.length()) {
                try {
                    return jSONArray.get(parseInt);
                } catch (JSONException e) {
                    throw new JSONPointerException("Error reading value at index position " + parseInt, e);
                }
            }
            throw new JSONPointerException(String.format("index %d is out of bounds - the array has %d elements", Integer.valueOf(parseInt), Integer.valueOf(jSONArray.length())));
        } catch (NumberFormatException e2) {
            throw new JSONPointerException(String.format("%s is not an array index", str), e2);
        }
    }

    public final String c(String str) {
        return str.replace("~1", MqttTopic.TOPIC_LEVEL_SEPARATOR).replace("~0", "~").replace("\\\"", "\"").replace("\\\\", "\\");
    }

    public Object queryFrom(Object obj) throws JSONPointerException {
        if (this.f15561a.isEmpty()) {
            return obj;
        }
        for (String str : this.f15561a) {
            if (obj instanceof JSONObject) {
                obj = ((JSONObject) obj).opt(c(str));
            } else if (obj instanceof JSONArray) {
                obj = b(obj, str);
            } else {
                throw new JSONPointerException(String.format("value [%s] is not an array or object therefore its key %s cannot be resolved", obj, str));
            }
        }
        return obj;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (String str : this.f15561a) {
            sb.append('/');
            sb.append(a(str));
        }
        return sb.toString();
    }

    public String toURIFragment() {
        try {
            StringBuilder sb = new StringBuilder(MqttTopic.MULTI_LEVEL_WILDCARD);
            for (String str : this.f15561a) {
                sb.append('/');
                sb.append(URLEncoder.encode(str, JsonRequest.PROTOCOL_CHARSET));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /* loaded from: classes13.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final List<String> f15562a = new ArrayList();

        public Builder append(String str) {
            Objects.requireNonNull(str, "token cannot be null");
            this.f15562a.add(str);
            return this;
        }

        public JSONPointer build() {
            return new JSONPointer(this.f15562a);
        }

        public Builder append(int i) {
            this.f15562a.add(String.valueOf(i));
            return this;
        }
    }

    public JSONPointer(List<String> list) {
        this.f15561a = new ArrayList(list);
    }
}
