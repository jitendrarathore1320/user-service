package com.advantal.requestPayload;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogsRequest {

	@NotNull(message = "pageIndex can't be null !")
	private int pageIndex;

	@NotNull(message = "pageSize can't be null !")
	private int pageSize;
	
	@NotNull(message = "Keyword can't be null !")
	private String keyword;
	
	@NotNull(message = "StartDate can't be null !")
	private String startDate;
	
	@NotNull(message = "EndDate can't be null !")
	private String endDate;
}
