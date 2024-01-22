package com.google.zxing.client.result;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.zxing.Result;
import com.mappls.sdk.services.account.Region;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes11.dex */
public final class VCardResultParser extends ResultParser {
    public static final Pattern f = Pattern.compile("BEGIN:VCARD", 2);
    public static final Pattern g = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}");
    public static final Pattern h = Pattern.compile("\r\n[ \t]");
    public static final Pattern i = Pattern.compile("\\\\[nN]");
    public static final Pattern j = Pattern.compile("\\\\([,;\\\\])");
    public static final Pattern k = Pattern.compile("=");
    public static final Pattern l = Pattern.compile(";");
    public static final Pattern m = Pattern.compile("(?<!\\\\);+");
    public static final Pattern n = Pattern.compile(Constants.SEPARATOR_COMMA);
    public static final Pattern o = Pattern.compile("[;,]");

    public static String g(CharSequence charSequence, String str) {
        char charAt;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        while (i2 < length) {
            char charAt2 = charSequence.charAt(i2);
            if (charAt2 != '\n' && charAt2 != '\r') {
                if (charAt2 != '=') {
                    m(byteArrayOutputStream, str, sb);
                    sb.append(charAt2);
                } else if (i2 < length - 2 && (charAt = charSequence.charAt(i2 + 1)) != '\r' && charAt != '\n') {
                    i2 += 2;
                    char charAt3 = charSequence.charAt(i2);
                    int parseHexDigit = ResultParser.parseHexDigit(charAt);
                    int parseHexDigit2 = ResultParser.parseHexDigit(charAt3);
                    if (parseHexDigit >= 0 && parseHexDigit2 >= 0) {
                        byteArrayOutputStream.write((parseHexDigit << 4) + parseHexDigit2);
                    }
                }
            }
            i2++;
        }
        m(byteArrayOutputStream, str, sb);
        return sb.toString();
    }

    public static void h(Iterable<List<String>> iterable) {
        int indexOf;
        if (iterable != null) {
            for (List<String> list : iterable) {
                String str = list.get(0);
                String[] strArr = new String[5];
                int i2 = 0;
                int i3 = 0;
                while (i2 < 4 && (indexOf = str.indexOf(59, i3)) >= 0) {
                    strArr[i2] = str.substring(i3, indexOf);
                    i2++;
                    i3 = indexOf + 1;
                }
                strArr[i2] = str.substring(i3);
                StringBuilder sb = new StringBuilder(100);
                l(strArr, 3, sb);
                l(strArr, 1, sb);
                l(strArr, 2, sb);
                l(strArr, 0, sb);
                l(strArr, 4, sb);
                list.set(0, sb.toString().trim());
            }
        }
    }

    public static boolean i(CharSequence charSequence) {
        return charSequence == null || g.matcher(charSequence).matches();
    }

    public static List<String> j(CharSequence charSequence, String str, boolean z, boolean z2) {
        List<List<String>> k2 = k(charSequence, str, z, z2);
        if (k2 == null || k2.isEmpty()) {
            return null;
        }
        return k2.get(0);
    }

    public static List<List<String>> k(CharSequence charSequence, String str, boolean z, boolean z2) {
        ArrayList arrayList;
        int i2;
        String str2;
        String str3;
        int indexOf;
        int i3;
        String replaceAll;
        int length = str.length();
        int i4 = 0;
        int i5 = 0;
        ArrayList arrayList2 = null;
        while (i5 < length) {
            int i6 = 2;
            Matcher matcher = Pattern.compile("(?:^|\n)" + ((Object) charSequence) + "(?:;([^:]*))?:", 2).matcher(str);
            if (i5 > 0) {
                i5--;
            }
            if (!matcher.find(i5)) {
                break;
            }
            int end = matcher.end(i4);
            String group = matcher.group(1);
            if (group != null) {
                String[] split = l.split(group);
                int length2 = split.length;
                int i7 = i4;
                i2 = i7;
                arrayList = null;
                str2 = null;
                str3 = null;
                while (i7 < length2) {
                    String str4 = split[i7];
                    if (arrayList == null) {
                        arrayList = new ArrayList(1);
                    }
                    arrayList.add(str4);
                    String[] split2 = k.split(str4, i6);
                    if (split2.length > 1) {
                        String str5 = split2[0];
                        String str6 = split2[1];
                        if ("ENCODING".equalsIgnoreCase(str5) && "QUOTED-PRINTABLE".equalsIgnoreCase(str6)) {
                            i2 = 1;
                        } else if ("CHARSET".equalsIgnoreCase(str5)) {
                            str2 = str6;
                        } else if ("VALUE".equalsIgnoreCase(str5)) {
                            str3 = str6;
                        }
                    }
                    i7++;
                    i6 = 2;
                }
            } else {
                arrayList = null;
                i2 = 0;
                str2 = null;
                str3 = null;
            }
            int i8 = end;
            while (true) {
                indexOf = str.indexOf(10, i8);
                if (indexOf < 0) {
                    break;
                }
                if (indexOf < str.length() - 1) {
                    int i9 = indexOf + 1;
                    if (str.charAt(i9) == ' ' || str.charAt(i9) == '\t') {
                        i8 = indexOf + 2;
                    }
                }
                if (i2 == 0) {
                    break;
                }
                if (indexOf > 0) {
                    if (str.charAt(indexOf - 1) == '=') {
                        i8 = indexOf + 1;
                    }
                }
                if (indexOf < 2) {
                    break;
                }
                if (str.charAt(indexOf - 2) != '=') {
                    break;
                }
                i8 = indexOf + 1;
            }
            if (indexOf < 0) {
                i5 = length;
                i4 = 0;
            } else {
                if (indexOf > end) {
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList(1);
                    }
                    if (indexOf > 0 && str.charAt(indexOf - 1) == '\r') {
                        indexOf--;
                    }
                    String substring = str.substring(end, indexOf);
                    if (z) {
                        substring = substring.trim();
                    }
                    if (i2 != 0) {
                        replaceAll = g(substring, str2);
                        if (z2) {
                            replaceAll = m.matcher(replaceAll).replaceAll("\n").trim();
                        }
                    } else {
                        if (z2) {
                            substring = m.matcher(substring).replaceAll("\n").trim();
                        }
                        replaceAll = j.matcher(i.matcher(h.matcher(substring).replaceAll("")).replaceAll("\n")).replaceAll("$1");
                    }
                    if (NotificationCompat.MessagingStyle.Message.KEY_DATA_URI.equals(str3)) {
                        try {
                            replaceAll = URI.create(replaceAll).getSchemeSpecificPart();
                        } catch (IllegalArgumentException unused) {
                        }
                    }
                    if (arrayList == null) {
                        ArrayList arrayList3 = new ArrayList(1);
                        arrayList3.add(replaceAll);
                        arrayList2.add(arrayList3);
                    } else {
                        i3 = 0;
                        arrayList.add(0, replaceAll);
                        arrayList2.add(arrayList);
                        i4 = i3;
                        i5 = indexOf + 1;
                    }
                }
                i3 = 0;
                i4 = i3;
                i5 = indexOf + 1;
            }
        }
        return arrayList2;
    }

    public static void l(String[] strArr, int i2, StringBuilder sb) {
        if (strArr[i2] == null || strArr[i2].isEmpty()) {
            return;
        }
        if (sb.length() > 0) {
            sb.append(' ');
        }
        sb.append(strArr[i2]);
    }

    public static void m(ByteArrayOutputStream byteArrayOutputStream, String str, StringBuilder sb) {
        String str2;
        if (byteArrayOutputStream.size() > 0) {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (str == null) {
                str2 = new String(byteArray, StandardCharsets.UTF_8);
            } else {
                try {
                    str2 = new String(byteArray, str);
                } catch (UnsupportedEncodingException unused) {
                    str2 = new String(byteArray, StandardCharsets.UTF_8);
                }
            }
            byteArrayOutputStream.reset();
            sb.append(str2);
        }
    }

    public static String n(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static String[] o(Collection<List<String>> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List<String> list : collection) {
            String str = list.get(0);
            if (str != null && !str.isEmpty()) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(ResultParser.e);
    }

    public static String[] p(Collection<List<String>> collection) {
        String str;
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List<String> list : collection) {
            String str2 = list.get(0);
            if (str2 != null && !str2.isEmpty()) {
                int i2 = 1;
                while (true) {
                    if (i2 >= list.size()) {
                        str = null;
                        break;
                    }
                    str = list.get(i2);
                    int indexOf = str.indexOf(61);
                    if (indexOf < 0) {
                        break;
                    } else if ("TYPE".equalsIgnoreCase(str.substring(0, indexOf))) {
                        str = str.substring(indexOf + 1);
                        break;
                    } else {
                        i2++;
                    }
                }
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(ResultParser.e);
    }

    @Override // com.google.zxing.client.result.ResultParser
    public AddressBookParsedResult parse(Result result) {
        String massagedText = ResultParser.getMassagedText(result);
        Matcher matcher = f.matcher(massagedText);
        if (matcher.find() && matcher.start() == 0) {
            List<List<String>> k2 = k("FN", massagedText, true, false);
            if (k2 == null) {
                k2 = k("N", massagedText, true, false);
                h(k2);
            }
            List<String> j2 = j("NICKNAME", massagedText, true, false);
            String[] split = j2 == null ? null : n.split(j2.get(0));
            List<List<String>> k3 = k("TEL", massagedText, true, false);
            List<List<String>> k4 = k("EMAIL", massagedText, true, false);
            List<String> j3 = j("NOTE", massagedText, false, false);
            List<List<String>> k5 = k("ADR", massagedText, true, true);
            List<String> j4 = j("ORG", massagedText, true, true);
            List<String> j5 = j("BDAY", massagedText, true, false);
            List<String> list = (j5 == null || i(j5.get(0))) ? j5 : null;
            List<String> j6 = j("TITLE", massagedText, true, false);
            List<List<String>> k6 = k("URL", massagedText, true, false);
            List<String> j7 = j("IMPP", massagedText, true, false);
            List<String> j8 = j(Region.REGION_GEORGIA, massagedText, true, false);
            String[] split2 = j8 == null ? null : o.split(j8.get(0));
            return new AddressBookParsedResult(o(k2), split, null, o(k3), p(k3), o(k4), p(k4), n(j7), n(j3), o(k5), p(k5), n(j4), n(list), n(j6), o(k6), (split2 == null || split2.length == 2) ? split2 : null);
        }
        return null;
    }
}
