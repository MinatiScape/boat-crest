package com.coveiot.android.bleabstract.response;

import java.util.List;
/* loaded from: classes2.dex */
public class GetImageListResponse {

    /* renamed from: a  reason: collision with root package name */
    public List<Integer> f3611a;

    public GetImageListResponse(List<Integer> list) {
        this.f3611a = list;
    }

    public List<Integer> getImageIdList() {
        return this.f3611a;
    }

    public String toString() {
        return "GetImageListResponse{imageIdList=" + this.f3611a + '}';
    }
}
