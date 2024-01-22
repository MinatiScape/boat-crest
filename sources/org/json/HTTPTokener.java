package org.json;
/* loaded from: classes13.dex */
public class HTTPTokener extends JSONTokener {
    public HTTPTokener(String str) {
        super(str);
    }

    public String nextToken() throws JSONException {
        char next;
        StringBuilder sb = new StringBuilder();
        do {
            next = next();
        } while (Character.isWhitespace(next));
        if (next != '\"' && next != '\'') {
            while (next != 0 && !Character.isWhitespace(next)) {
                sb.append(next);
                next = next();
            }
            return sb.toString();
        }
        while (true) {
            char next2 = next();
            if (next2 < ' ') {
                throw syntaxError("Unterminated string.");
            }
            if (next2 == next) {
                return sb.toString();
            }
            sb.append(next2);
        }
    }
}
