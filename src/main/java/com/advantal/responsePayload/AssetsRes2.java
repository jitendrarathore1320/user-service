package com.advantal.responsePayload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssetsRes2 {

	private String assetsName;	
	
	private Double closePrice;
	
	private Double priceGain;

	private Double pricePercentageGain;
	
}
