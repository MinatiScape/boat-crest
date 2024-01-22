package com.abupdate.mqtt_libs.mqttv3.a.b;

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
import java.util.UUID;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public InputStream f1949a;
    public OutputStream b;
    public String c;
    public String d;
    public int e;

    public e(InputStream inputStream, OutputStream outputStream, String str, String str2, int i) {
        this.f1949a = inputStream;
        this.b = outputStream;
        this.c = str;
        this.d = str2;
        this.e = i;
    }

    public void a() throws IOException {
        byte[] bArr = new byte[16];
        System.arraycopy(UUID.randomUUID().toString().getBytes(), 0, bArr, 0, 16);
        String a2 = a.a(bArr);
        b(a2);
        d(a2);
    }

    public final void b(String str) throws IOException {
        String userInfo;
        try {
            String str2 = "/mqtt";
            URI uri = new URI(this.c);
            if (uri.getRawPath() != null && !uri.getRawPath().isEmpty()) {
                str2 = uri.getRawPath();
                if (uri.getRawQuery() != null && !uri.getRawQuery().isEmpty()) {
                    str2 = str2 + "?" + uri.getRawQuery();
                }
            }
            PrintWriter printWriter = new PrintWriter(this.b);
            printWriter.print("GET " + str2 + " HTTP/1.1\r\n");
            int i = this.e;
            if (i != 80 && i != 443) {
                printWriter.print("Host: " + this.d + ":" + this.e + "\r\n");
            } else {
                printWriter.print("Host: " + this.d + "\r\n");
            }
            printWriter.print("Upgrade: websocket\r\n");
            printWriter.print("Connection: Upgrade\r\n");
            printWriter.print("Sec-WebSocket-Key: " + str + "\r\n");
            printWriter.print("Sec-WebSocket-Protocol: mqtt\r\n");
            printWriter.print("Sec-WebSocket-Version: 13\r\n");
            if (uri.getUserInfo() != null) {
                printWriter.print("Authorization: Basic " + a.a(userInfo) + "\r\n");
            }
            printWriter.print("\r\n");
            printWriter.flush();
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public final void c(String str, String str2) throws NoSuchAlgorithmException, c {
        if (!a.a(e(str + WebSocketProtocol.ACCEPT_MAGIC)).trim().equals(str2.trim())) {
            throw new c();
        }
    }

    public final void d(String str) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.f1949a));
        ArrayList arrayList = new ArrayList();
        String readLine = bufferedReader.readLine();
        if (readLine != null) {
            while (!readLine.equals("")) {
                arrayList.add(readLine);
                readLine = bufferedReader.readLine();
            }
            Map a2 = a(arrayList);
            String str2 = (String) a2.get("connection");
            if (str2 != null && !str2.equalsIgnoreCase(WatchTestConstant.DIR_UPDATE)) {
                String str3 = (String) a2.get(WatchTestConstant.DIR_UPDATE);
                if (str3 != null && str3.toLowerCase().contains("websocket")) {
                    if (((String) a2.get("sec-websocket-protocol")) != null) {
                        if (a2.containsKey("sec-websocket-accept")) {
                            try {
                                c(str, (String) a2.get("sec-websocket-accept"));
                                return;
                            } catch (c unused) {
                                throw new IOException("WebSocket Response header: Incorrect Sec-WebSocket-Key");
                            } catch (NoSuchAlgorithmException e) {
                                throw new IOException(e.getMessage());
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

    public final byte[] e(String str) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA1").digest(str.getBytes());
    }

    public final Map a(ArrayList arrayList) {
        HashMap hashMap = new HashMap();
        for (int i = 1; i < arrayList.size(); i++) {
            String[] split = ((String) arrayList.get(i)).split(":");
            hashMap.put(split[0].toLowerCase(), split[1]);
        }
        return hashMap;
    }
}
