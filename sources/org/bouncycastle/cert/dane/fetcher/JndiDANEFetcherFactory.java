package org.bouncycastle.cert.dane.fetcher;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.naming.Binding;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import org.bouncycastle.cert.dane.DANEEntry;
import org.bouncycastle.cert.dane.DANEEntryFetcher;
import org.bouncycastle.cert.dane.DANEEntryFetcherFactory;
import org.bouncycastle.cert.dane.DANEException;
/* loaded from: classes12.dex */
public class JndiDANEFetcherFactory implements DANEEntryFetcherFactory {

    /* renamed from: a  reason: collision with root package name */
    public List f14483a = new ArrayList();
    public boolean b;

    /* loaded from: classes12.dex */
    public class a implements DANEEntryFetcher {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Hashtable f14484a;
        public final /* synthetic */ String b;

        public a(Hashtable hashtable, String str) {
            this.f14484a = hashtable;
            this.b = str;
        }

        @Override // org.bouncycastle.cert.dane.DANEEntryFetcher
        public List getEntries() throws DANEException {
            ArrayList arrayList = new ArrayList();
            try {
                InitialDirContext initialDirContext = new InitialDirContext(this.f14484a);
                if (this.b.indexOf("_smimecert.") > 0) {
                    Attribute attribute = initialDirContext.getAttributes(this.b, new String[]{BleConst.CloseECG}).get(BleConst.CloseECG);
                    if (attribute != null) {
                        JndiDANEFetcherFactory.this.b(arrayList, this.b, attribute);
                    }
                } else {
                    NamingEnumeration listBindings = initialDirContext.listBindings("_smimecert." + this.b);
                    while (listBindings.hasMore()) {
                        DirContext dirContext = (DirContext) ((Binding) listBindings.next()).getObject();
                        Attribute attribute2 = initialDirContext.getAttributes(dirContext.getNameInNamespace().substring(1, dirContext.getNameInNamespace().length() - 1), new String[]{BleConst.CloseECG}).get(BleConst.CloseECG);
                        if (attribute2 != null) {
                            String nameInNamespace = dirContext.getNameInNamespace();
                            JndiDANEFetcherFactory.this.b(arrayList, nameInNamespace.substring(1, nameInNamespace.length() - 1), attribute2);
                        }
                    }
                }
                return arrayList;
            } catch (NamingException e) {
                throw new DANEException("Exception dealing with DNS: " + e.getMessage(), e);
            }
        }
    }

    public final void b(List list, String str, Attribute attribute) throws NamingException, DANEException {
        for (int i = 0; i != attribute.size(); i++) {
            byte[] bArr = (byte[]) attribute.get(i);
            if (DANEEntry.isValidCertificate(bArr)) {
                try {
                    list.add(new DANEEntry(str, bArr));
                } catch (IOException e) {
                    throw new DANEException("Exception parsing entry: " + e.getMessage(), e);
                }
            }
        }
    }

    @Override // org.bouncycastle.cert.dane.DANEEntryFetcherFactory
    public DANEEntryFetcher build(String str) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
        hashtable.put("java.naming.authoritative", this.b ? "true" : "false");
        if (this.f14483a.size() > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it = this.f14483a.iterator();
            while (it.hasNext()) {
                if (stringBuffer.length() > 0) {
                    stringBuffer.append(HexStringBuilder.DEFAULT_SEPARATOR);
                }
                stringBuffer.append("dns://" + it.next());
            }
            hashtable.put("java.naming.provider.url", stringBuffer.toString());
        }
        return new a(hashtable, str);
    }

    public JndiDANEFetcherFactory setAuthoritative(boolean z) {
        this.b = z;
        return this;
    }

    public JndiDANEFetcherFactory usingDNSServer(String str) {
        this.f14483a.add(str);
        return this;
    }
}
