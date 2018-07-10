package com.liaoyb.springboot.domain.dto;

import com.liaoyb.springboot.util.Convert;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaoyb
 */
@Data
public class PageParam {
    private Integer page;
    private Integer size;
    private List<OrderDto> orders;

    public PageRequest convertToPageRequest() {
        PageParam2PageRequestConvert convert = new PageParam2PageRequestConvert();
        return convert.doForward(this);
    }


    //must set page、size
    public static Builder builder() {
        return new Builder();
    }

    //builder
    public static class Builder {
        private int page;
        private int size;
        private List<OrderDto> orders;

        //page从0开始，size大于0
        public Builder page(int page, int size) {
            this.page = page;
            this.size = size;
            return this;
        }

        public Builder order(Direction direction, String property) {
            if (orders == null) {
                orders = new ArrayList<>();
            }
            OrderDto orderDto = new OrderDto();
            orderDto.setDirection(direction.name());
            orderDto.setProperty(property);
            orders.add(orderDto);
            return this;
        }

        public PageParam build() {
            PageParam pageParam = new PageParam();
            pageParam.setOrders(orders);
            if (page < 0 || size <= 0) {
                throw new IllegalArgumentException("page or size not visible");
            }
            pageParam.setPage(page);
            pageParam.setSize(size);
            return pageParam;
        }
    }

    public static enum Direction {
        ASC, DESC;
    }

    @Data
    public static class OrderDto {
        //DESC,ASC
        private String direction;
        private String property;
    }

    private static class PageParam2PageRequestConvert extends Convert<PageParam, PageRequest> {
        @Override
        protected PageRequest doForward(PageParam pageParam) {
            //pageDto、size
            int page = 0;
            int size = 10;

            if (pageParam.getPage() != null && pageParam.getSize() != null) {
                page = pageParam.getPage();
                size = pageParam.getSize();
            }
            //Sort
            Sort sort = null;
            if (null != pageParam.orders) {
                List<Sort.Order> orderList = new ArrayList<>();
                for (OrderDto orderDto : pageParam.orders) {
                    Sort.Order order = new Sort.Order(Sort.Direction.valueOf(orderDto.getDirection()), orderDto.getProperty());
                    orderList.add(order);
                }
                sort = Sort.by(orderList);
            }
            if(null != sort) {
                return PageRequest.of(page, size, sort);
            }else {
                return PageRequest.of(page, size);
            }
        }

        @Override
        protected PageParam doBackward(PageRequest pageRequest) {
            throw new AssertionError("not support");
        }
    }
}
