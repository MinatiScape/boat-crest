package com.abupdate.http_libs.request;

import com.abupdate.http_libs.data.HttpConfig;
import com.abupdate.http_libs.data.a;
import com.abupdate.http_libs.request.base.AbstractRequest;
import com.abupdate.http_libs.request.content.StringBody;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
/* loaded from: classes.dex */
public class PostFormRequest extends AbstractRequest {
    public PostFormRequest(String str) {
        setUrl(str);
        setMethod(a.Post);
        setHeaderContentType(HttpConfig.CONTENT_TYPE);
    }

    public final String a(Map<String, String> map) {
        if (map != null) {
            try {
                if (map.isEmpty()) {
                    return "";
                }
                StringBuffer stringBuffer = new StringBuffer();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    stringBuffer.append(entry.getKey());
                    stringBuffer.append("=");
                    stringBuffer.append(URLEncoder.encode(entry.getValue(), getCharset()));
                    stringBuffer.append("&");
                }
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                return stringBuffer.toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }

    public PostFormRequest map(Map<String, String> map) {
        return (PostFormRequest) setHttpBody(new StringBody(a(map)));
    }
}
