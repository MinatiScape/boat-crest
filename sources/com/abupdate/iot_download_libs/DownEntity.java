package com.abupdate.iot_download_libs;

import android.text.TextUtils;
import com.abupdate.iot_download_libs.segmentDownload.SegmentDownInfo;
import java.util.List;
/* loaded from: classes.dex */
public class DownEntity {

    /* renamed from: a  reason: collision with root package name */
    public Object f1879a;
    public List<SegmentDownInfo> b;
    public volatile boolean download_cancel;
    public int download_status;
    public long downloaded_size;
    public String file_path;
    public long file_size;
    public String md5;
    public String str_extra;
    public String url;

    public DownEntity(String str, String str2) {
        this.url = str;
        this.file_path = str2;
    }

    public DownEntity(String str, String str2, long j) {
        this.url = str;
        this.file_path = str2;
        this.file_size = j;
    }

    public DownEntity(String str, String str2, long j, String str3) {
        this.url = str;
        this.file_path = str2;
        this.file_size = j;
        this.md5 = str3;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DownEntity) {
            DownEntity downEntity = (DownEntity) obj;
            return (TextUtils.isEmpty(downEntity.url) && TextUtils.isEmpty(this.url)) || downEntity.url.equals(this.url);
        }
        return false;
    }

    public List<SegmentDownInfo> getSegmentDownInfos() {
        return this.b;
    }

    public Object getTag() {
        return this.f1879a;
    }

    public DownEntity setSegmentDownInfo(List<SegmentDownInfo> list) {
        this.b = list;
        return this;
    }

    public void setTag(Object obj) {
        this.f1879a = obj;
    }

    public String toString() {
        return "DownEntity{url='" + this.url + "', file_path='" + this.file_path + "', md5='" + this.md5 + "', file_size=" + this.file_size + ", downloaded_size=" + this.downloaded_size + ", str_extra='" + this.str_extra + "', download_status=" + this.download_status + ", download_cancel=" + this.download_cancel + '}';
    }
}
