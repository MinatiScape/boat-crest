package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes13.dex */
public class ServerNameList {
    public Vector serverNameList;

    public ServerNameList(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("'serverNameList' must not be null");
        }
        this.serverNameList = vector;
    }

    public static short[] a(short[] sArr, short s) {
        if (!NameType.isValid(s) || Arrays.contains(sArr, s)) {
            return null;
        }
        return Arrays.append(sArr, s);
    }

    public static ServerNameList parse(InputStream inputStream) throws IOException {
        int readUint16 = TlsUtils.readUint16(inputStream);
        if (readUint16 >= 1) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(TlsUtils.readFully(readUint16, inputStream));
            short[] sArr = new short[0];
            Vector vector = new Vector();
            while (byteArrayInputStream.available() > 0) {
                ServerName parse = ServerName.parse(byteArrayInputStream);
                sArr = a(sArr, parse.getNameType());
                if (sArr == null) {
                    throw new TlsFatalAlert((short) 47);
                }
                vector.addElement(parse);
            }
            return new ServerNameList(vector);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public void encode(OutputStream outputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        short[] sArr = new short[0];
        for (int i = 0; i < this.serverNameList.size(); i++) {
            ServerName serverName = (ServerName) this.serverNameList.elementAt(i);
            sArr = a(sArr, serverName.getNameType());
            if (sArr == null) {
                throw new TlsFatalAlert((short) 80);
            }
            serverName.encode(byteArrayOutputStream);
        }
        TlsUtils.checkUint16(byteArrayOutputStream.size());
        TlsUtils.writeUint16(byteArrayOutputStream.size(), outputStream);
        Streams.writeBufTo(byteArrayOutputStream, outputStream);
    }

    public Vector getServerNameList() {
        return this.serverNameList;
    }
}
