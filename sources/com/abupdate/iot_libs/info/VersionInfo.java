package com.abupdate.iot_libs.info;

import com.abupdate.iot_download_libs.segmentDownload.SegmentDownInfo;
import com.abupdate.iot_libs.OtaAgentPolicy;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes.dex */
public class VersionInfo {

    /* renamed from: a  reason: collision with root package name */
    public static VersionInfo f1900a;
    public String content;
    public String deltaID;
    public String deltaUrl;
    public long fileSize;
    public String md5sum;
    public HashMap<String, PolicyMapInfo> policyHashMap = new HashMap<>();
    public String publishDate;
    public List<SegmentDownInfo> segmentSha;
    public String versionAlias;
    public String versionName;

    public static VersionInfo getInstance() {
        if (f1900a == null) {
            synchronized (VersionInfo.class) {
                f1900a = new VersionInfo();
            }
        }
        return f1900a;
    }

    public String getReleaseNoteByCurrentLanguage() {
        return getReleaseNoteByLanguage(OtaAgentPolicy.sCx.getResources().getConfiguration().locale.getCountry(), OtaAgentPolicy.sCx.getResources().getConfiguration().locale.getLanguage());
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x003f, code lost:
        r1 = r5.getString("content");
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getReleaseNoteByLanguage(java.lang.String r10, java.lang.String r11) {
        /*
            r9 = this;
            java.lang.String r0 = "country"
            java.lang.String r1 = ""
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r11)
            java.lang.String r3 = "_"
            r2.append(r3)
            r2.append(r10)
            java.lang.String r10 = r2.toString()
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch: org.json.JSONException -> L53
            java.lang.String r3 = r9.content     // Catch: org.json.JSONException -> L53
            r2.<init>(r3)     // Catch: org.json.JSONException -> L53
            r3 = 0
            r4 = r1
        L21:
            int r5 = r2.length()     // Catch: org.json.JSONException -> L51
            if (r3 >= r5) goto L58
            java.lang.Object r5 = r2.get(r3)     // Catch: org.json.JSONException -> L51
            org.json.JSONObject r5 = (org.json.JSONObject) r5     // Catch: org.json.JSONException -> L51
            boolean r6 = r5.has(r0)     // Catch: org.json.JSONException -> L51
            if (r6 == 0) goto L4e
            java.lang.String r6 = r5.getString(r0)     // Catch: org.json.JSONException -> L51
            boolean r7 = r10.equalsIgnoreCase(r6)     // Catch: org.json.JSONException -> L51
            java.lang.String r8 = "content"
            if (r7 == 0) goto L44
            java.lang.String r1 = r5.getString(r8)     // Catch: org.json.JSONException -> L51
            goto L58
        L44:
            boolean r6 = r6.contains(r11)     // Catch: org.json.JSONException -> L51
            if (r6 == 0) goto L4e
            java.lang.String r4 = r5.getString(r8)     // Catch: org.json.JSONException -> L51
        L4e:
            int r3 = r3 + 1
            goto L21
        L51:
            r10 = move-exception
            goto L55
        L53:
            r10 = move-exception
            r4 = r1
        L55:
            r10.printStackTrace()
        L58:
            boolean r10 = android.text.TextUtils.isEmpty(r1)
            if (r10 == 0) goto L68
            boolean r10 = android.text.TextUtils.isEmpty(r4)
            if (r10 != 0) goto L66
            r1 = r4
            goto L68
        L66:
            java.lang.String r1 = r9.content
        L68:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.abupdate.iot_libs.info.VersionInfo.getReleaseNoteByLanguage(java.lang.String, java.lang.String):java.lang.String");
    }

    public void reset() {
        this.policyHashMap.clear();
        this.versionName = null;
        this.versionAlias = null;
        this.fileSize = 0L;
        this.deltaID = null;
        this.md5sum = null;
        this.deltaUrl = null;
        this.publishDate = null;
        this.content = null;
        this.segmentSha = null;
    }

    public String toString() {
        return "VersionInfo{\nversionName='" + this.versionName + "'\nversionAlias='" + this.versionAlias + "'\nfileSize=" + this.fileSize + "\ndeltaID='" + this.deltaID + "'\nmd5sum='" + this.md5sum + "'\ndeltaUrl='" + this.deltaUrl + "'\npublishDate='" + this.publishDate + "'\ncontent='" + this.content + "'\n}";
    }
}
