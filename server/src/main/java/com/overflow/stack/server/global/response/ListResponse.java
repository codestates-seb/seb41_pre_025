package com.overflow.stack.server.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ListResponse<T> {
   List<T> data;
}
