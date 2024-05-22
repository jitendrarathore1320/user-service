package com.advantal.requestPayload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestPage {
	private int pageIndex;
	private int pageSize;
	private String sortBy;
	private String keyword;
}
