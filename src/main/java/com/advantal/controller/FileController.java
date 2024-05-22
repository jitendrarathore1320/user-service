package com.advantal.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advantal.model.User;
import com.advantal.requestPayload.UserRequestPage;
import com.advantal.services.UserServices;
import com.advantal.utils.Constant;
import com.advantal.utils.ExportUserToPdf;
import com.lowagie.text.DocumentException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user_file")
public class FileController {

	@Autowired
	UserServices userServices;

	@PostMapping("/download_user_details_pdf")
//	@ApiOperation(value = "Download pdf file for sms details !!")
	public Map<String, String> AdminDetailsExportToPDF(@RequestBody @Valid UserRequestPage userRequestPage,
			HttpServletResponse response) throws DocumentException, IOException {
		Map<String, String> map = new HashMap<>();

		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=user_details_" + currentDateTime + ".pdf";
		List<User> userList = userServices.getAllUserDetails(userRequestPage);

		if (!userList.isEmpty()) {
			log.info(" record found successfully ! status - {}", Constant.OK + userList);
			response.setHeader(headerKey, headerValue);
			ExportUserToPdf exportToPdf = new ExportUserToPdf(userList);
			exportToPdf.export(response);
		} else {
			map.put(Constant.RESPONSE_CODE, Constant.NOT_FOUND);
			map.put(Constant.MESSAGE, Constant.NOT_DOWNLOAD_FILE_MESSAGE);
			log.info("Not able to download the file, because no record found on the database! status - {}",
					Constant.BAD_REQUEST);
		}
		return map;
	}
}
