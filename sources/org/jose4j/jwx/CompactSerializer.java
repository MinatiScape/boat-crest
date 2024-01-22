package org.jose4j.jwx;
/* loaded from: classes13.dex */
public class CompactSerializer {
    public static String[] deserialize(String str) {
        String[] split = str.split("\\.");
        if (str.endsWith(".")) {
            String[] strArr = new String[split.length + 1];
            System.arraycopy(split, 0, strArr, 0, split.length);
            strArr[split.length] = "";
            return strArr;
        }
        return split;
    }

    public static String serialize(String... strArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            sb.append(strArr[i] == null ? "" : strArr[i]);
            if (i != strArr.length - 1) {
                sb.append(".");
            }
        }
        return sb.toString();
    }
}
