package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
/* loaded from: classes11.dex */
public final class BizcardResultParser extends a {
    public static String i(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        return str + ' ' + str2;
    }

    public static String[] j(String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList(3);
        if (str != null) {
            arrayList.add(str);
        }
        if (str2 != null) {
            arrayList.add(str2);
        }
        if (str3 != null) {
            arrayList.add(str3);
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[size]);
    }

    @Override // com.google.zxing.client.result.ResultParser
    public AddressBookParsedResult parse(Result result) {
        String massagedText = ResultParser.getMassagedText(result);
        if (massagedText.startsWith("BIZCARD:")) {
            String i = i(a.h("N:", massagedText, true), a.h("X:", massagedText, true));
            String h = a.h("T:", massagedText, true);
            String h2 = a.h("C:", massagedText, true);
            return new AddressBookParsedResult(ResultParser.maybeWrap(i), null, null, j(a.h("B:", massagedText, true), a.h("M:", massagedText, true), a.h("F:", massagedText, true)), null, ResultParser.maybeWrap(a.h("E:", massagedText, true)), null, null, null, a.g("A:", massagedText), null, h2, null, h, null, null);
        }
        return null;
    }
}
