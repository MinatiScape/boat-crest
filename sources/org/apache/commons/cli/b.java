package org.apache.commons.cli;
/* loaded from: classes12.dex */
public class b {
    public static boolean a(char c) {
        return Character.isJavaIdentifierPart(c);
    }

    public static boolean b(char c) {
        return a(c) || c == ' ' || c == '?' || c == '@';
    }

    public static void c(String str) throws IllegalArgumentException {
        if (str == null) {
            return;
        }
        if (str.length() == 1) {
            char charAt = str.charAt(0);
            if (b(charAt)) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("illegal option value '");
            stringBuffer.append(charAt);
            stringBuffer.append("'");
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (!a(charArray[i])) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("opt contains illegal character value '");
                stringBuffer2.append(charArray[i]);
                stringBuffer2.append("'");
                throw new IllegalArgumentException(stringBuffer2.toString());
            }
        }
    }
}
