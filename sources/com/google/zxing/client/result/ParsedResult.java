package com.google.zxing.client.result;
/* loaded from: classes11.dex */
public abstract class ParsedResult {

    /* renamed from: a  reason: collision with root package name */
    public final ParsedResultType f11784a;

    public ParsedResult(ParsedResultType parsedResultType) {
        this.f11784a = parsedResultType;
    }

    public static void maybeAppend(String str, StringBuilder sb) {
        if (str == null || str.isEmpty()) {
            return;
        }
        if (sb.length() > 0) {
            sb.append('\n');
        }
        sb.append(str);
    }

    public abstract String getDisplayResult();

    public final ParsedResultType getType() {
        return this.f11784a;
    }

    public final String toString() {
        return getDisplayResult();
    }

    public static void maybeAppend(String[] strArr, StringBuilder sb) {
        if (strArr != null) {
            for (String str : strArr) {
                maybeAppend(str, sb);
            }
        }
    }
}
