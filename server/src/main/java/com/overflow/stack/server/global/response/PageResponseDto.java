package com.overflow.stack.server.global.response;

import lombok.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDto<T> {
   List<T> data;
   //PageInfo pageInfo;
   PageInfo pageInfo;
}
