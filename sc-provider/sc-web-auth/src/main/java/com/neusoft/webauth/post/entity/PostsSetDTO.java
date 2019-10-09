package com.neusoft.webauth.post.entity;

import java.util.List;

/**
 * @ClassName PostsSetDTO
 * @Description
 * @Author zhu.qf@neusoft.com
 * @Date 2018/11/28
 */

public class PostsSetDTO {
    private List<String> postList;
    private String userCode;
    private String lastModifiedBy;

    public List<String> getPostList() {
        return postList;
    }

    public void setPostList(List<String> postList) {
        this.postList = postList;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
