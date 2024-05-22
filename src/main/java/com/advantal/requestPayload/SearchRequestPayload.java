package com.advantal.requestPayload;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SearchRequestPayload {

	@NotNull(message = "PageIndex can't be null !!")
	private Integer pageIndex;

	@NotNull(message = "PageSize can't be null !!")
	private Integer pageSize;

	private String keyWord;
}
