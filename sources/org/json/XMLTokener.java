package org.json;

import java.util.HashMap;
import kotlin.text.Typography;
/* loaded from: classes13.dex */
public class XMLTokener extends JSONTokener {
    public static final HashMap<String, Character> entity;

    static {
        HashMap<String, Character> hashMap = new HashMap<>(8);
        entity = hashMap;
        hashMap.put("amp", XML.AMP);
        hashMap.put("apos", XML.APOS);
        hashMap.put("gt", XML.GT);
        hashMap.put("lt", XML.LT);
        hashMap.put("quot", XML.QUOT);
    }

    public XMLTokener(String str) {
        super(str);
    }

    public static String c(String str) {
        int parseInt;
        if (str == null || str.isEmpty()) {
            return "";
        }
        if (str.charAt(0) == '#') {
            if (str.charAt(1) == 'x') {
                parseInt = Integer.parseInt(str.substring(2), 16);
            } else {
                parseInt = Integer.parseInt(str.substring(1));
            }
            return new String(new int[]{parseInt}, 0, 1);
        }
        Character ch = entity.get(str);
        if (ch == null) {
            return Typography.amp + str + ';';
        }
        return ch.toString();
    }

    public String nextCDATA() throws JSONException {
        StringBuilder sb = new StringBuilder();
        while (more()) {
            sb.append(next());
            int length = sb.length() - 3;
            if (length >= 0 && sb.charAt(length) == ']' && sb.charAt(length + 1) == ']' && sb.charAt(length + 2) == '>') {
                sb.setLength(length);
                return sb.toString();
            }
        }
        throw syntaxError("Unclosed CDATA");
    }

    public Object nextContent() throws JSONException {
        char next;
        do {
            next = next();
        } while (Character.isWhitespace(next));
        if (next == 0) {
            return null;
        }
        if (next == '<') {
            return XML.LT;
        }
        StringBuilder sb = new StringBuilder();
        while (next != 0) {
            if (next == '<') {
                back();
                return sb.toString().trim();
            }
            if (next == '&') {
                sb.append(nextEntity(next));
            } else {
                sb.append(next);
            }
            next = next();
        }
        return sb.toString().trim();
    }

    public Object nextEntity(char c) throws JSONException {
        char next;
        StringBuilder sb = new StringBuilder();
        while (true) {
            next = next();
            if (!Character.isLetterOrDigit(next) && next != '#') {
                break;
            }
            sb.append(Character.toLowerCase(next));
        }
        if (next == ';') {
            return c(sb.toString());
        }
        throw syntaxError("Missing ';' in XML entity: &" + ((Object) sb));
    }

    public Object nextMeta() throws JSONException {
        char next;
        char next2;
        do {
            next = next();
        } while (Character.isWhitespace(next));
        if (next != 0) {
            if (next != '\'') {
                if (next == '/') {
                    return XML.SLASH;
                }
                if (next == '!') {
                    return XML.BANG;
                }
                if (next != '\"') {
                    switch (next) {
                        case '<':
                            return XML.LT;
                        case '=':
                            return XML.EQ;
                        case '>':
                            return XML.GT;
                        case '?':
                            return XML.QUEST;
                    }
                    while (true) {
                        char next3 = next();
                        if (Character.isWhitespace(next3)) {
                            return Boolean.TRUE;
                        }
                        if (next3 != 0 && next3 != '\'' && next3 != '/' && next3 != '!' && next3 != '\"') {
                            switch (next3) {
                            }
                        }
                    }
                    back();
                    return Boolean.TRUE;
                }
            }
            do {
                next2 = next();
                if (next2 == 0) {
                    throw syntaxError("Unterminated string");
                }
            } while (next2 != next);
            return Boolean.TRUE;
        }
        throw syntaxError("Misshaped meta tag");
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0053, code lost:
        return r5.toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x005a, code lost:
        throw syntaxError("Bad character in a name");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object nextToken() throws org.json.JSONException {
        /*
            r7 = this;
        L0:
            char r0 = r7.next()
            boolean r1 = java.lang.Character.isWhitespace(r0)
            if (r1 != 0) goto L0
            if (r0 == 0) goto L9f
            r1 = 39
            if (r0 == r1) goto L76
            r2 = 47
            if (r0 == r2) goto L73
            r3 = 33
            if (r0 == r3) goto L70
            r4 = 34
            if (r0 == r4) goto L76
            switch(r0) {
                case 60: goto L69;
                case 61: goto L66;
                case 62: goto L63;
                case 63: goto L60;
                default: goto L1f;
            }
        L1f:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
        L24:
            r5.append(r0)
            char r0 = r7.next()
            boolean r6 = java.lang.Character.isWhitespace(r0)
            if (r6 == 0) goto L36
            java.lang.String r0 = r5.toString()
            return r0
        L36:
            if (r0 == 0) goto L5b
            if (r0 == r1) goto L54
            if (r0 == r2) goto L4c
            r6 = 91
            if (r0 == r6) goto L4c
            r6 = 93
            if (r0 == r6) goto L4c
            if (r0 == r3) goto L4c
            if (r0 == r4) goto L54
            switch(r0) {
                case 60: goto L54;
                case 61: goto L4c;
                case 62: goto L4c;
                case 63: goto L4c;
                default: goto L4b;
            }
        L4b:
            goto L24
        L4c:
            r7.back()
            java.lang.String r0 = r5.toString()
            return r0
        L54:
            java.lang.String r0 = "Bad character in a name"
            org.json.JSONException r0 = r7.syntaxError(r0)
            throw r0
        L5b:
            java.lang.String r0 = r5.toString()
            return r0
        L60:
            java.lang.Character r0 = org.json.XML.QUEST
            return r0
        L63:
            java.lang.Character r0 = org.json.XML.GT
            return r0
        L66:
            java.lang.Character r0 = org.json.XML.EQ
            return r0
        L69:
            java.lang.String r0 = "Misplaced '<'"
            org.json.JSONException r0 = r7.syntaxError(r0)
            throw r0
        L70:
            java.lang.Character r0 = org.json.XML.BANG
            return r0
        L73:
            java.lang.Character r0 = org.json.XML.SLASH
            return r0
        L76:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L7b:
            char r2 = r7.next()
            if (r2 == 0) goto L98
            if (r2 != r0) goto L88
            java.lang.String r0 = r1.toString()
            return r0
        L88:
            r3 = 38
            if (r2 != r3) goto L94
            java.lang.Object r2 = r7.nextEntity(r2)
            r1.append(r2)
            goto L7b
        L94:
            r1.append(r2)
            goto L7b
        L98:
            java.lang.String r0 = "Unterminated string"
            org.json.JSONException r0 = r7.syntaxError(r0)
            throw r0
        L9f:
            java.lang.String r0 = "Misshaped element"
            org.json.JSONException r0 = r7.syntaxError(r0)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.XMLTokener.nextToken():java.lang.Object");
    }

    public void skipPast(String str) {
        boolean z;
        char next;
        int length = str.length();
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            char next2 = next();
            if (next2 == 0) {
                return;
            }
            cArr[i] = next2;
        }
        int i2 = 0;
        while (true) {
            int i3 = 0;
            int i4 = i2;
            while (true) {
                if (i3 >= length) {
                    z = true;
                    break;
                } else if (cArr[i4] != str.charAt(i3)) {
                    z = false;
                    break;
                } else {
                    i4++;
                    if (i4 >= length) {
                        i4 -= length;
                    }
                    i3++;
                }
            }
            if (z || (next = next()) == 0) {
                return;
            }
            cArr[i2] = next;
            i2++;
            if (i2 >= length) {
                i2 -= length;
            }
        }
    }
}
