package com.advantal.responsePayload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGraphResponse {

	private String label;
	
//	private int year;
	
	private List<MonthObject> UserGraphResponseList;
}
