package com.liaoyb.springboot.domain.dto;

import com.liaoyb.springboot.util.Convert;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author liaoyb
 */
@Data
public class PageDto<T> {
    private List<T> content;
    private long total;
    private long totalPages;
    private int number;
    private int size;
    private boolean first;
    private boolean last;

    /**
     * convert Page to PageDto
     *
     * @param page page data
     * @param <T>  data
     * @return PageDto
     * @throws IllegalArgumentException if page is null
     */
    public static <T> PageDto<T> convertFor(Page<T> page) {
        Assert.notNull(page, "page not null");
        PageDtoConvert<T> pageDtoConvert = new PageDtoConvert<>();
        return pageDtoConvert.doBackward(page);
    }

    private static class PageDtoConvert<T> extends Convert<PageDto<T>, Page<T>> {
        @Override
        protected Page<T> doForward(PageDto<T> tPageDto) {
            throw new AssertionError("not support");
        }

        @Override
        protected PageDto<T> doBackward(Page<T> ts) {
            PageDto<T> pageDto = new PageDto<>();
            pageDto.setContent(ts.getContent());
            pageDto.setTotal(ts.getTotalElements());
            pageDto.setTotalPages(ts.getTotalPages());
            pageDto.setNumber(ts.getNumber());
            pageDto.setSize(ts.getSize());
            pageDto.setFirst(ts.isFirst());
            pageDto.setLast(ts.isLast());
            return pageDto;
        }
    }
}
