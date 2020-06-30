package common;

import java.io.Serializable;

public class PageParam<T> implements Serializable {
    private static final long serialVersionUID = -7248374800878487522L;
    private int pageNum = 1;
    private int pageSize = 10;
    private T param;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize >= 500 ? 500 : pageSize;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum <= 0 ? 1 : pageNum;
    }

    public PageParam() {
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public T getParam() {
        return this.param;
    }

    public void setParam(final T param) {
        this.param = param;
    }

    public String toString() {
        return "PageParam(pageNum=" + this.getPageNum() + ", pageSize=" + this.getPageSize() + ", param=" + this.getParam() + ")";
    }
}
