package pl.elka.pw.pik.shop.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public abstract class PagableSearchParams implements Serializable {
    private int size;
    private int page;
    private String direction;
    private String sortCol;

    public PagableSearchParams(int size, int page, String direction, String sortCol) {
        this.size = size;
        this.page = page;
        this.direction = direction;
        this.sortCol = sortCol;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setSortCol(String sortCol) {
        this.sortCol = sortCol;
    }

    public PageRequest toPageRequest() {
        Sort sort = null;
        if (direction != null && sortCol != null) {
            Sort.Direction direct = Sort.Direction.fromString(direction);
            sort = new Sort(new Sort.Order(direct, sortCol));
        }
        return new PageRequest(page, size, sort);
    }
}
