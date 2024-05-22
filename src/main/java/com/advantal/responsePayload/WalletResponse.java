package com.advantal.responsePayload;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponse {

	private Double latestTotalBalance;

	private Double prevTotalBalance;

	private Double dailyOpeningBalance;

	private Double dailyPriceGain;

	private Double dailyPercentageGain;

	private Double totalPriceGain;

	private Double totalPercentageGain;

	private Short status;
	
	private Date creationDate;//

	private Date openTime;//
	
	private Date updationDate;//
	
	private Date autoSyncPortfolioAt;//
	
	private List<AssetsRes2> assetsResList;

}
