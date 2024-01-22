package org.eclipse.paho.client.mqttv3.internal.websocket;

import com.jieli.watchtesttool.util.WatchTestConstant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes13.dex */
public class WebSocketHandshake {

    /* renamed from: a  reason: collision with root package name */
    public InputStream f15459a;
    public OutputStream b;
    public String c;
    public String d;
    public int e;
    public Properties f;

    public WebSocketHandshake(InputStream inputStream, OutputStream outputStream, String str, String str2, int i, Properties properties) {
        this.f15459a = inputStream;
        this.b = outputStream;
        this.c = str;
        this.d = str2;
        this.e = i;
        this.f = properties;
    }

    public final Map<String, String> a(ArrayList<String> arrayList) {
        HashMap hashMap = new HashMap();
        for (int i = 1; i < arrayList.size(); i++) {
            String[] split = arrayList.get(i).split(":");
            hashMap.put(split[0].toLowerCase(), split[1]);
        }
        return hashMap;
    }

    public final void b(String str) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.f15459a));
        ArrayList<String> arrayList = new ArrayList<>();
        String readLine = bufferedReader.readLine();
        if (readLine != null) {
            while (!readLine.equals("")) {
                arrayList.add(readLine);
                readLine = bufferedReader.readLine();
            }
            Map<String, String> a2 = a(arrayList);
            String str2 = a2.get("connection");
            if (str2 != null && !str2.equalsIgnoreCase(WatchTestConstant.DIR_UPDATE)) {
                String str3 = a2.get(WatchTestConstant.DIR_UPDATE);
                if (str3 != null && str3.toLowerCase().contains("websocket")) {
                    if (a2.get("sec-websocket-protocol") != null) {
                        if (a2.containsKey("sec-websocket-accept")) {
                            try {
                                e(str, a2.get("sec-websocket-accept"));
                                return;
                            } catch (NoSuchAlgorithmException e) {
                                throw new IOException(e.getMessage());
                            } catch (HandshakeFailedException unused) {
                                throw new IOException("WebSocket Response header: Incorrect Sec-WebSocket-Key");
                            }
                        }
                        throw new IOException("WebSocket Response header: Missing Sec-WebSocket-Accept");
                    }
                    throw new IOException("WebSocket Response header: empty sec-websocket-protocol");
                }
                throw new IOException("WebSocket Response header: Incorrect upgrade.");
            }
            throw new IOException("WebSocket Response header: Incorrect connection header");
        }
        throw new IOException("WebSocket Response header: Invalid response from Server, It may not support WebSockets.");
    }

    public final void c(String str) throws IOException {
        String userInfo;
        try {
            String str2 = "/mqtt";
            URI uri = new URI(this.c);
            if (uri.getRawPath() != null && !uri.getRawPath().isEmpty()) {
                str2 = uri.getRawPath();
                if (uri.getRawQuery() != null && !uri.getRawQuery().isEmpty()) {
                    str2 = String.valueOf(str2) + "?" + uri.getRawQuery();
                }
            }
            PrintWriter printWriter = new PrintWriter(this.b);
            printWriter.print("GET " + str2 + " HTTP/1.1\r\n");
            if (this.e != 80) {
                printWriter.print("Host: " + this.d + ":" + this.e + "\r\n");
            } else {
                printWriter.print("Host: " + this.d + "\r\n");
            }
            printWriter.print("Upgrade: websocket\r\n");
            printWriter.print("Connection: Upgrade\r\n");
            printWriter.print("Sec-WebSocket-Key: " + str + "\r\n");
            printWriter.print("Sec-WebSocket-Protocol: mqtt\r\n");
            printWriter.print("Sec-WebSocket-Version: 13\r\n");
            Properties properties = this.f;
            if (properties != null) {
                for (String str3 : properties.keySet()) {
                    printWriter.print(String.valueOf(str3) + ": " + this.f.getProperty(str3) + "\r\n");
                }
            }
            if (uri.getUserInfo() != null) {
                printWriter.print("Authorization: Basic " + Base64.encode(userInfo) + "\r\n");
            }
            printWriter.print("\r\n");
            printWriter.flush();
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public final byte[] d(String str) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA1").digest(str.getBytes());
    }

    public final void e(String str, String str2) throws NoSuchAlgorithmException, HandshakeFailedException {
        if (!Base64.encodeBytes(d(String.valueOf(str) + WebSocketProtocol.ACCEPT_MAGIC)).trim().equals(str2.trim())) {
            throw new HandshakeFailedException();
        }
    }

    public void execute() throws IOException {
        byte[] bArr = new byte[16];
        System.arraycopy(UUID.randomUUID().toString().getBytes(), 0, bArr, 0, 16);
        String encodeBytes = Base64.encodeBytes(bArr);
        c(encodeBytes);
        b(encodeBytes);
    }
}
