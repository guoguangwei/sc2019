package com.neusoft.core.page;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author: wwb
 * @Date: 2019/5/17
 */
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int pageSize;
    private int totalRecord;
    private int currentPage;
    private int totalPage;
    private T project;

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public T getProject() {
        return project;
    }

    public void setProject(T project) {
        this.project = project;
    }

    private List<Object> records = Lists.newArrayList();
    private Map<String, Object> moreAttrs = Maps.newHashMap();

    public Page() {
        this.pageSize = 10;
        this.totalRecord = 0;
        this.currentPage = 1;
    }

    public Page(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public Page(int currentPage, int totalRecord, int pageSize) {
        this.pageSize = 10;
        this.totalRecord = 0;
        this.currentPage = 1;
        this.currentPage = currentPage;
        this.totalRecord = totalRecord;
        this.pageSize = pageSize;
    }

    public int getRecordIndex() {
        return this.currentPage > 0 ? (this.currentPage - 1) * this.pageSize : 0;
    }

    public int getRecordStart() {
        return this.currentPage > 0 ? (this.currentPage - 1) * this.pageSize + 1 : 0;
    }

    public int getRecordEnd() {
        return this.currentPage > 0 ? this.currentPage * this.pageSize : 0;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return this.totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        this.totalPage = (int)Math.floor((double)this.totalRecord * 1.0D / (double)this.pageSize);
        if (this.totalRecord % this.pageSize != 0) {
            ++this.totalPage;
        }

        return this.totalPage == 0 ? 1 : this.totalPage;
    }

    public List<Object> getRecords() {
        return this.records;
    }

    public void setRecords(List<Object> records) {
        this.records = records;
    }

    public Map<String, Object> getMoreAttrs() {
        return ImmutableMap.copyOf(this.moreAttrs);
    }

    public void addMoreAttribute(String key, Object value) {
        this.moreAttrs.put(key, value);
    }

    public void addMoreAttribute(Map map) {
        this.moreAttrs.putAll(map);
    }
    public static class FieldDomain {
        public static final String RECORD_START = "recordStart";
        public static final String RECORD_INDEX = "recordIndex";
        public static final String RECORD_END = "recordEnd";
        public static final String PAGE_SIZE = "pageSize";
        public static final String TOTAL_RECORD = "totalRecord";
        public static final String CURRENT_PAGE = "currentPage";
        public static final String TOTAL_PAGE = "totalPage";
        public static final String RECORDS = "records";

        public FieldDomain() {
        }
    }
}

