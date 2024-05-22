package com.advantal.responsePayload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthObject{

	private String dateFormat;

	private Long count;

	private Long active;
}
