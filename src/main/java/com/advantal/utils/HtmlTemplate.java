package com.advantal.utils;

public class HtmlTemplate {
	
	public static String forgetPasswordTemplate(String email, String link)
	{
		
		String html="<!DOCTYPE html>\r\n"
				+ "<html lang=\"en-US\">\r\n"
				+ "<head>\r\n"
				+ "  <meta charset=\"utf-8\">\r\n"
				+ "  <meta name=\"x-apple-disable-message-reformatting\">\r\n"
				+ "  <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\r\n"
				+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "  <!--[if mso]>\r\n"
				+ "    <xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml>\r\n"
				+ "    <style>\r\n"
				+ "      td,th,div,p,a,h1,h2,h3,h4,h5,h6 {font-family: \"Segoe UI\", sans-serif; mso-line-height-rule: exactly;}\r\n"
				+ "    </style>\r\n"
				+ "  <![endif]-->\r\n"
				+ "  <title>Reset Your Password</title>\r\n"
				+ "    \r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body style=\"margin: 0; padding: 0; width: 100%; word-break: break-word; -webkit-font-smoothing: antialiased;  background-color: #fff; font-family: Lato, sans-serif;\" >\r\n"
				+ "  <div style=\"padding: 0 15px 40px;\">\r\n"
				+ "    <table style=\"width: 100%; max-width: 600px; margin: 0 auto 20px; \" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n"
				+ "      <tbody>\r\n"
				+ "        <tr>\r\n"
				+ "          <td style=\"padding: 20px 25px; text-align: center;\">\r\n"
				+ "            <a href=\"#\">\r\n"
				+ "              <img src=\"images/KreditLo1_sm.png\" alt=\"\" style=\"border: 0; max-width: 100%; line-height: 100%; vertical-align: middle; max-height: 100px;\">\r\n"
				+ "            </a>\r\n"
				+ "          </td>\r\n"
				+ "        </tr>\r\n"
				+ "        <tr>\r\n"
				+ "          <td style=\" background-color: #f8f9fc; border:1px solid #e1e4ef; border-radius: 4px; font-size: 14px; line-height: 24px; padding: 50px 40px; text-align: left; color: #626262;\" >\r\n"
				+ "            <p style=\"font-weight: 600; font-size: 20px; margin: 0 0 20px; color: #263238;\">Hello  <span style=\"font-weight: 700; display: inline-block; color: #52A5DC;\">"+email+",</span></p>\r\n"
				+ "            <p style=\"font-weight: 600; font-size: 16px; margin: 0 0 20px; color: #263238;\">\r\n"
				+ "              Reset Your Password\r\n"
				+ "            </p>\r\n"
				+ "            <p style=\"margin: 0 0 15px;\">\r\n"
				+ "              That's okay, it happens! Click on the button below to reset your password\r\n"
				+ "			</p>\r\n"
				
				+ "            <p style=\"margin: 0;\">\r\n"    
				+ "              <a href=\'"+link+"'\"  target=\"_blank\">Reset Password</a> \r\n"
				+ "            </p>\r\n"
				+ "			<p style=\"margin: 0 0 15px;\">\r\n"
				+ "            </p>\r\n"
				
				+ "            <p style=\"margin: 0; color: #263238;\">\r\n"
				+ "              <strong>Regards,<br>Team Amwal</strong>\r\n"
				+ "            </p>\r\n"
				+ "			<p></p>\r\n"
				+ "          </td>\r\n"
				+ "        </tr>\r\n"
				+ "      </tbody>\r\n"
				+ "    </table>\r\n"
				+ "  </div>\r\n"
				+ "</body>\r\n"
				+ "</html>";
				return html;
	}
	public static String htmlnextpage(String firstName)
	{
		String page="<!DOCTYPE html>\r\n"
				+ "<html lang=\"en-US\">\r\n"
				+ "<head>\r\n"
				+ "  <meta charset=\"utf-8\">\r\n"
				+ "  <meta name=\"x-apple-disable-message-reformatting\">\r\n"
				+ "  <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\r\n"
				+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "  <!--[if mso]>\r\n"
				+ "    <xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml>\r\n"
				+ "    <style>\r\n"
				+ "      td,th,div,p,a,h1,h2,h3,h4,h5,h6 {font-family: \"Segoe UI\", sans-serif; mso-line-height-rule: exactly;}\r\n"
				+ "    </style>\r\n"
				+ "  <![endif]-->\r\n"
				+ "  <title>Email Verification</title>\r\n"
				+ "    \r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body style=\"margin: 0; padding: 0; width: 100%; word-break: break-word; -webkit-font-smoothing: antialiased;  background-color: #fff; font-family: Lato, sans-serif;\" >\r\n"
				+ "  <div style=\"padding: 0 15px 40px;\">\r\n"
				+ "    <table style=\"width: 100%; max-width: 600px; margin: 0 auto 20px; \" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n"
				+ "      <tbody>\r\n"
				+ "        <tr>\r\n"
				+ "          <td style=\"padding: 20px 25px; text-align: center;\">\r\n"
				+ "            <a href=\"#\">\r\n"
				+ "              <img src=\"images/KreditLo1_sm.png\" alt=\"\" style=\"border: 0; max-width: 100%; line-height: 100%; vertical-align: middle; max-height: 100px;\">\r\n"
				+ "            </a>\r\n"
				+ "          </td>\r\n"
				+ "        </tr>\r\n"
				+ "        <tr>\r\n"
				+ "          <td style=\" background-color: #f8f9fc; border:1px solid #e1e4ef; border-radius: 4px; font-size: 14px; line-height: 24px; padding: 50px 40px; text-align: left; color: #626262;\" >\r\n"
				+ "            <p style=\"font-weight: 600; font-size: 20px; margin: 0 0 20px; color: #263238;\">Hello <span style=\"font-weight: 700; display: inline-block; color: #52A5DC;\">"+firstName+",</span></p>\r\n"
				+ "            <p style=\"font-weight: 600; font-size: 16px; margin: 0 0 20px; color: #263238;\">\r\n"
				+ "              Greetings from KreditLo.\r\n"
				+ "            </p>\r\n"
				+ "            <p style=\"margin: 0 0 15px;\">\r\n"
				+ "              Your email has been verified successfully.\r\n"
				+ "			</p>\r\n"
				+ "          </td>\r\n"
				+ "        </tr>\r\n"
				+ "      </tbody>\r\n"
				+ "    </table>\r\n"
				+ "  </div>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		      return page;
		
	}

	public static String htmlEmailNotVarified(String firstName)
	{
		String page="<!DOCTYPE html>\r\n"
				+ "<html lang=\"en-US\">\r\n"
				+ "<head>\r\n"
				+ "  <meta charset=\"utf-8\">\r\n"
				+ "  <meta name=\"x-apple-disable-message-reformatting\">\r\n"
				+ "  <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\r\n"
				+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "  <!--[if mso]>\r\n"
				+ "    <xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml>\r\n"
				+ "    <style>\r\n"
				+ "      td,th,div,p,a,h1,h2,h3,h4,h5,h6 {font-family: \"Segoe UI\", sans-serif; mso-line-height-rule: exactly;}\r\n"
				+ "    </style>\r\n"
				+ "  <![endif]-->\r\n"
				+ "  <title>Email Verification</title>\r\n"
				+ "    \r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body style=\"margin: 0; padding: 0; width: 100%; word-break: break-word; -webkit-font-smoothing: antialiased;  background-color: #fff; font-family: Lato, sans-serif;\" >\r\n"
				+ "  <div style=\"padding: 0 15px 40px;\">\r\n"
				+ "    <table style=\"width: 100%; max-width: 600px; margin: 0 auto 20px; \" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n"
				+ "      <tbody>\r\n"
				+ "        <tr>\r\n"
				+ "          <td style=\"padding: 20px 25px; text-align: center;\">\r\n"
				+ "            <a href=\"#\">\r\n"
				+ "              <img src=\"images/KreditLo1_sm.png\" alt=\"\" style=\"border: 0; max-width: 100%; line-height: 100%; vertical-align: middle; max-height: 100px;\">\r\n"
				+ "            </a>\r\n"
				+ "          </td>\r\n"
				+ "        </tr>\r\n"
				+ "        <tr>\r\n"
				+ "          <td style=\" background-color: #f8f9fc; border:1px solid #e1e4ef; border-radius: 4px; font-size: 14px; line-height: 24px; padding: 50px 40px; text-align: left; color: #626262;\" >\r\n"
				+ "            <p style=\"font-weight: 600; font-size: 20px; margin: 0 0 20px; color: #263238;\">Hello <span style=\"font-weight: 700; display: inline-block; color: #52A5DC;\">"+firstName+",</span></p>\r\n"
				+ "            <p style=\"font-weight: 600; font-size: 16px; margin: 0 0 20px; color: #263238;\">\r\n"
				+ "              Greetings from KreditLo.\r\n"
				+ "            </p>\r\n"
				+ "            <p style=\"margin: 0 0 15px;\">\r\n"
				+ "              Your email has been not verified .\r\n"
				+ "			</p>\r\n"
				+ "          </td>\r\n"
				+ "        </tr>\r\n"
				+ "      </tbody>\r\n"
				+ "    </table>\r\n"
				+ "  </div>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		      return page;
		
	}
	
	public static String linkVerification(String otp) {
//		if(firstName==null || firstName.equals("")) {
//			 firstName="Hello";
//		 }
		 return "<!DOCTYPE HTML\r\n"
	+ "  PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
		 		+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\"\r\n"
		 		+ "  xmlns:o=\"urn:schemas-microsoft-com:office:office\">\r\n"
		 		+ "\r\n"
		 		+ "<head>\r\n"
		 		+ "  <!--[if gte mso 9]>\r\n"
		 		+ "<xml>\r\n"
		 		+ "  <o:OfficeDocumentSettings>\r\n"
		 		+ "    <o:AllowPNG/>\r\n"
		 		+ "    <o:PixelsPerInch>96</o:PixelsPerInch>\r\n"
		 		+ "  </o:OfficeDocumentSettings>\r\n"
		 		+ "</xml>\r\n"
		 		+ "<![endif]-->\r\n"
		 		+ "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n"
		 		+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
		 		+ "  <meta name=\"x-apple-disable-message-reformatting\">\r\n"
		 		+ "  <!--[if !mso]><!-->\r\n"
		 		+ "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
		 		+ "  <!--<![endif]-->\r\n"
		 		+ "  <title></title>\r\n"
		 		+ "\r\n"
		 		+ "  <style type=\"text/css\">\r\n"
		 		+ "    @media only screen and (min-width: 620px) {\r\n"
		 		+ "      .u-row {\r\n"
		 		+ "        width: 600px !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      .u-row .u-col {\r\n"
		 		+ "        vertical-align: top;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      .u-row .u-col-33p33 {\r\n"
		 		+ "        width: 199.98px !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      .u-row .u-col-47p17 {\r\n"
		 		+ "        width: 283.02px !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      .u-row .u-col-50 {\r\n"
		 		+ "        width: 300px !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      .u-row .u-col-52p83 {\r\n"
		 		+ "        width: 316.98px !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      .u-row .u-col-100 {\r\n"
		 		+ "        width: 600px !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "    }\r\n"
		 		+ "\r\n"
		 		+ "    @media (max-width: 620px) {\r\n"
		 		+ "      .u-row-container {\r\n"
		 		+ "        max-width: 100% !important;\r\n"
		 		+ "        padding-left: 0px !important;\r\n"
		 		+ "        padding-right: 0px !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      .u-row .u-col {\r\n"
		 		+ "        min-width: 320px !important;\r\n"
		 		+ "        max-width: 100% !important;\r\n"
		 		+ "        display: block !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      .u-row {\r\n"
		 		+ "        width: calc(100% - 40px) !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      .u-col {\r\n"
		 		+ "        width: 100% !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      .u-col>div {\r\n"
		 		+ "        margin: 0 auto;\r\n"
		 		+ "      }\r\n"
		 		+ "    }\r\n"
		 		+ "\r\n"
		 		+ "    body {\r\n"
		 		+ "      margin: 0;\r\n"
		 		+ "      padding: 0;\r\n"
		 		+ "    }\r\n"
		 		+ "\r\n"
		 		+ "    table,\r\n"
		 		+ "    tr,\r\n"
		 		+ "    td {\r\n"
		 		+ "      vertical-align: top;\r\n"
		 		+ "      border-collapse: collapse;\r\n"
		 		+ "    }\r\n"
		 		+ "\r\n"
		 		+ "    p {\r\n"
		 		+ "      margin: 0;\r\n"
		 		+ "    }\r\n"
		 		+ "\r\n"
		 		+ "    .ie-container table,\r\n"
		 		+ "    .mso-container table {\r\n"
		 		+ "      table-layout: fixed;\r\n"
		 		+ "    }\r\n"
		 		+ "\r\n"
		 		+ "    * {\r\n"
		 		+ "      line-height: inherit;\r\n"
		 		+ "    }\r\n"
		 		+ "\r\n"
		 		+ "    a[x-apple-data-detectors='true'] {\r\n"
		 		+ "      color: inherit !important;\r\n"
		 		+ "      text-decoration: none !important;\r\n"
		 		+ "    }\r\n"
		 		+ "\r\n"
		 		+ "    table,\r\n"
		 		+ "    td {\r\n"
		 		+ "      color: #000000;\r\n"
		 		+ "    }\r\n"
		 		+ "\r\n"
		 		+ "    a {\r\n"
		 		+ "      color: #0000ee;\r\n"
		 		+ "      text-decoration: underline;\r\n"
		 		+ "    }\r\n"
		 		+ "\r\n"
		 		+ "    @media (max-width: 480px) {\r\n"
		 		+ "      #u_content_image_1 .v-src-width {\r\n"
		 		+ "        width: auto !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_image_1 .v-src-max-width {\r\n"
		 		+ "        max-width: 40% !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_heading_1 .v-font-size {\r\n"
		 		+ "        font-size: 38px !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_image_3 .v-src-width {\r\n"
		 		+ "        width: 100% !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_image_3 .v-src-max-width {\r\n"
		 		+ "        max-width: 100% !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_text_5 .v-container-padding-padding {\r\n"
		 		+ "        padding: 10px 30px 11px 10px !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_text_3 .v-container-padding-padding {\r\n"
		 		+ "        padding: 15px 10px 20px !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_image_13 .v-src-width {\r\n"
		 		+ "        width: auto !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_image_13 .v-src-max-width {\r\n"
		 		+ "        max-width: 29% !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_image_15 .v-container-padding-padding {\r\n"
		 		+ "        padding: 27px 10px 10px !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_image_15 .v-src-width {\r\n"
		 		+ "        width: auto !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_image_15 .v-src-max-width {\r\n"
		 		+ "        max-width: 29% !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_image_16 .v-container-padding-padding {\r\n"
		 		+ "        padding: 22px 10px 10px !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_image_16 .v-src-width {\r\n"
		 		+ "        width: auto !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_image_16 .v-src-max-width {\r\n"
		 		+ "        max-width: 29% !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_heading_3 .v-container-padding-padding {\r\n"
		 		+ "        padding: 20px 10px 0px !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_heading_3 .v-text-align {\r\n"
		 		+ "        text-align: center !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_text_9 .v-container-padding-padding {\r\n"
		 		+ "        padding: 10px !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_text_9 .v-text-align {\r\n"
		 		+ "        text-align: center !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_button_1 .v-container-padding-padding {\r\n"
		 		+ "        padding: 15px 10px 50px !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_button_1 .v-text-align {\r\n"
		 		+ "        text-align: center !important;\r\n"
		 		+ "      }\r\n"
		 		+ "\r\n"
		 		+ "      #u_content_button_1 .v-padding {\r\n"
		 		+ "        padding: 13px 30px 12px !important;\r\n"
		 		+ "      }\r\n"
		 		+ "    }\r\n"
		 		+ "  </style>\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "  <!--[if !mso]><!-->\r\n"
		 		+ "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n"
		 		+ "            <div class=\"u-row\"\r\n"
		 		+ "              style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\r\n"
		 		+ "              <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\r\n"
		 		+ "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\r\n"
		 		+ "\r\n"
		 		+ "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\r\n"
		 		+ "                <div class=\"u-col u-col-100\"\r\n"
		 		+ "                  style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\r\n"
		 		+ "                  <div\r\n"
		 		+ "                    style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\r\n"
		 		+ "                    <!--[if (!mso)&(!IE)]><!-->\r\n"
		 		+ "                    <div\r\n"
		 		+ "                      style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\r\n"
		 		+ "                      <!--<![endif]-->\r\n"
		 		+ "\r\n"
		 		+ "                      <table id=\"u_content_text_5\" style=\"font-family:'Montserrat',sans-serif;\" role=\"presentation\"\r\n"
		 		+ "                        cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n"
		 		+ "                        <tbody>\r\n"
		 		+ "                          <tr>\r\n"
		 		+ "                            <td class=\"v-container-padding-padding\"\r\n"
		 		+ "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:40px 30px 20px 40px;font-family:'Montserrat',sans-serif;\"\r\n"
		 		+ "                              align=\"left\">\r\n"
		 		+ "\r\n"
		 		+ "                              <div class=\"v-text-align\"\r\n"
		 		+ "                                style=\"color: #4b4a4a; line-height: 190%; text-align: left; word-wrap: break-word;\">\r\n"
		 		+ "                                <p style=\"font-size: 14px; line-height: 190%;\"><span\r\n"
		 		+ "                                    style=\"font-size: 18px; line-height: 34.2px;\"><strong><span\r\n"
	+ "                                        style=\"line-height: 34.2px; font-size: 18px;\">Hi "+"jitendra rathore"+",</span></strong></span></p>\r\n"
		 		+ "                                <p style=\"font-size: 14px; line-height: 190%;\"><span\r\n"
		 		+ "                                    style=\"font-size: 16px; line-height: 30.4px;\">\r\n"
	+ "I regret to inform you that due to your bank account not being in a nationalised bank, we are unable to release your salary for December. We apologize for the inconvenience caused and we hope that you can resolve this issue as soon as possible.<br>\r\n"
	+ "                                   If you have any questions or require any further information, please do not hesitate to contact us. .\r\n"
		 		+ "                                  <br>\r\n"
//		 		+ "                                    Your OTP - "+otp+".\r\n"
		 		+ "                                  <br>  \r\n"
	+ "                                    Regards,<br>\r\n"
	+ "                                    Advantal Technologies Pvt. Ltd.</span></p>\r\n"
		 		+ "                              </div>\r\n"
		 		+ "\r\n"
		 		+ "                            </td>\r\n"
		 		+ "                          </tr>\r\n"
		 		+ "                        </tbody>\r\n"
		 		+ "                      </table>\r\n"
		 		+ "\r\n"
		 		+ "                      <!--[if (!mso)&(!IE)]><!-->\r\n"
		 		+ "                    </div>\r\n"
		 		+ "                    <!--<![endif]-->\r\n"
		 		+ "                  </div>\r\n"
		 		+ "                </div>\r\n"
		 		+ "                <!--[if (mso)|(IE)]></td><![endif]-->\r\n"
		 		+ "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n"
		 		+ "              </div>\r\n"
		 		+ "            </div>\r\n"
		 		+ "          </div>\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "         \r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "       \r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "\r\n"
		 		+ "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n"
		 		+ "        </td>\r\n"
		 		+ "      </tr>\r\n"
		 		+ "    </tbody>\r\n"
		 		+ "  </table>\r\n"
		 		+ "  <!--[if mso]></div><![endif]-->\r\n"
		 		+ "  <!--[if IE]></div><![endif]-->\r\n"
		 		+ "</body>\r\n"
		 		+ "\r\n"
		 		+ "</html>";
	 }
	public static String RegistrationEmail(String msg) {
		String page="<!DOCTYPE html>\r\n"
				+ "<html lang=\"en-US\">\r\n"
				+ "<head>\r\n"
				+ "  <meta charset=\"utf-8\">\r\n"
				+ "  <meta name=\"x-apple-disable-message-reformatting\">\r\n"
				+ "  <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\r\n"
				+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "  <!--[if mso]>\r\n"
				+ "    <xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml>\r\n"
				+ "    <style>\r\n"
				+ "      td,th,div,p,a,h1,h2,h3,h4,h5,h6 {font-family: \"Segoe UI\", sans-serif; mso-line-height-rule: exactly;}\r\n"
				+ "    </style>\r\n"
				+ "  <![endif]-->\r\n"
				+ "  <title>Email Verification</title>\r\n"
				+ "    \r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body style=\"margin: 0; padding: 0; width: 100%; word-break: break-word; -webkit-font-smoothing: antialiased;  background-color: #fff; font-family: Lato, sans-serif;\" >\r\n"
				+ "  <div style=\"padding: 0 15px 40px;\">\r\n"
				+ "    <table style=\"width: 100%; max-width: 600px; margin: 0 auto 20px; \" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\r\n"
				+ "      <tbody>\r\n"
				+ "        <tr>\r\n"
				+ "          <td style=\"padding: 20px 25px; text-align: center;\">\r\n"
				+ "            <a href=\"#\">\r\n"
				+ "              <img src=\"images/KreditLo1_sm.png\" alt=\"\" style=\"border: 0; max-width: 100%; line-height: 100%; vertical-align: middle; max-height: 100px;\">\r\n"
				+ "            </a>\r\n"
				+ "          </td>\r\n"
				+ "        </tr>\r\n"
				+ "        <tr>\r\n"
				+ "          <td style=\" background-color: #f8f9fc; border:1px solid #e1e4ef; border-radius: 4px; font-size: 14px; line-height: 24px; padding: 50px 40px; text-align: left; color: #626262;\" >\r\n"
				+ "            <p style=\"font-weight: 600; font-size: 20px; margin: 0 0 20px; color: #263238;\">Hello <span style=\"font-weight: 700; display: inline-block; color: #52A5DC;\">"+msg+",</span></p>\r\n"
				+ "            <p style=\"font-weight: 600; font-size: 16px; margin: 0 0 20px; color: #263238;\">\r\n"
				+ "             \r\n"
				+ "            </p>\r\n"
				+ "            <p style=\"margin: 0 0 15px;\">\r\n"
				+ "              Your email has been not verified .\r\n"
				+ "			</p>\r\n"
				+ "          </td>\r\n"
				+ "        </tr>\r\n"
				+ "      </tbody>\r\n"
				+ "    </table>\r\n"
				+ "  </div>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		      return page;
	
	}
	
}
