package com.elvishew.xlog.formatter.message.xml;

import com.elvishew.xlog.internal.Platform;
import com.elvishew.xlog.internal.SystemCompat;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
/* loaded from: classes9.dex */
public class DefaultXmlFormatter implements XmlFormatter {
    @Override // com.elvishew.xlog.formatter.Formatter
    public String format(String str) {
        if (str != null && str.trim().length() != 0) {
            try {
                StreamSource streamSource = new StreamSource(new StringReader(str));
                StreamResult streamResult = new StreamResult(new StringWriter());
                Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
                newTransformer.setOutputProperty("indent", "yes");
                newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(4));
                newTransformer.transform(streamSource, streamResult);
                String obj = streamResult.getWriter().toString();
                return obj.replaceFirst(">", ">" + SystemCompat.lineSeparator);
            } catch (Exception e) {
                Platform.get().warn(e.getMessage());
                return str;
            }
        }
        Platform.get().warn("XML empty.");
        return "";
    }
}
