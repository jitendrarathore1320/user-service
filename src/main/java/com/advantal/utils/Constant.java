package com.advantal.utils;

public class Constant {
	
//	 -------------------------- EMAIL AND PASSWORD -------------------------------
	public static final String EMAIL_ADDRESS = "smscindia@outlook.com";
	public static final String PASSWORD = "Smsc@123";// "Smsc@123";
	
	public static final String INVALID_DATA = "Invalid data";
	public static final String USER_ADDED_SUCCESSFULLY = "User created successfully";
	public static final String INVALID_PARAMETERS = "Invalid Parameters";
	public static final String HTTP_STATUS = "status";
	public static final String DATA = "data";
	public static final String MESSAGE = "message";
	public static final String COUNT = "count";

	public static final String INVALID_DEVICE_TOKEN = "Invalid Device Token";
	public static final String INVALID_DEVICE_TYPE = "Invalid Device Type";
	public static final String INVALID_DEVICE_ID = "Invalid Device Id";
	public static final String DEVICE_SAVED_SUCCESSFULLY = "Device Saved Successfully";
	public static final String DEVICE_UPDATED_SUCCESSFULLY = "Device Updated Successfully";
	public static final String INVALID_NAME = "Invalid Name Parameter !";
	public static final String PLEASE_TRY_AGAIN = "Please Try Again !!";
	public static final String SAVED_SUCCESSFULLY = "Saved Successfully";
	public static final String INVALID_PRICE = "Invalid Price";
	public static final String SUCCESS = "Success";
	public static final String NO_CONTENT = "No Content Found !!";
	public static final String CODE_NOT_FOUND = "Code not Found";
	public static final String INVALID_PAGE_SIZE = "Invalid Page Size";
	public static final String INVALID_PHONE_NUMBER = "Invalid Phone Number";
	public static final String PHONE_NUMBER_BLOCKED = "Found! but blocked!!";
	public static final String IPHONE_NUMBER_NULL = "please enter the phone number. Phone Number can't be null  !!";
	public static final String EMPTY_MOB_DEVICE_TOKEN = "Empty phone number and deviceId and deviceToken!!";
	public static final String EMPTY_MOBILE_NUMBER= "Phone number can't be null or empty!!";
	public static final String INVALID_MOB_DEVICE_TOKEN = "Invalid phone number and deviceId and deviceToken!!";
	public static final String UPDATE_ERROR = "Update error !!";
//	public static final Object USER_ADDED = "OTP Sent! please veriy!!";
	public static final String USER_LOGIN_SUCCESS_MESSAGE = "Login successfully!!";
	public static final String INCORRECT_OTP = "Incorrect OTP !!";
	public static final String OTP_SEND = "OTP sent successfully! please verify!!";
	public static final String USER_UPDATED_SUCCESSFULLY = "User Updated !!";
	public static final String UPDATED_SUCCESSFULLY = "Updated Successfully";
	public static final String USER_STATUS = "User status can't be null!!";

	// --------------------------------------- RESPONSE KEY
	// ---------------------------------------
	public static final String RESPONSE_CODE = "responseCode";
	public static final String INVALID_REQUEST = "Invalid request!";//
	public static final String UNAUTHRISED = "UNAUTHRISED";//
	public static final String WRONG_INPUT_DATA = "Wrong input data!";//

	public static final String OBJECT = "object";
//	public static final String SUCCESS = "SUCCESS";
	public static final String ERROR = "ERROR";
	public static final String BLOCKED = "Blocked";
	public static final String NOT_VERIFIED = "Not verified";
	public static final String VERIFY_ACCOUNT = "Verify Account";
	public static final String RESET_PASSWORD = "Reset Password";
	public static final String USER_ADD = "You have registerd successfully,but you have not verified please verify.";
	public static final String AUTH_KEY = "authKey";
//	public static final String MESSAGE = "message";
//	public static final String DATA = "data";
	public static final String TOKEN = "token";
	public static final String AUTH_ID = "authId";
	public static final String TOTAL_USER = "totalUser";
	public static final String ISVALID = "isValid";
	// ======================= Common message ==========================
	public static final String ALREADY_UNBLOCKED_MESSAGE = "Already unblocked!!";
	public static final String UNBLOCKED_SUCCESS_MESSAGE = "Unblocked successfully!!";
	public static final String BLOCKED_SUCCESS_MESSAGE = "Blocked successfully!!";
	public static final String ALREADY_BLOCKED_MESSAGE = "Already blocked!!";
	public static final Object STATUS_INVALID_MESSAGE = "Status value invalid!!";
	public static final String RECORD_NOT_FOUND_MESSAGE = "Record not found!!";
	public static final String SERVER_MESSAGE = "Technical issue";
	public static final String DELETED_MESSAGE = "Deleted successfully!!";
	public static final String ALREADY_DELETED_MESSAGE = "Already deleted!!";
	public static final String ID_NOT_FOUND_MESSAGE = "Given user id not found into the database!!";
	public static final String ID_CAN_NOT_NULL_MESSAGE = "Id can not null, it shoulsd be valid!!";
	public static final String NO_SERVER_CONNECTION = "The server was found but the connection to its local database was not possible.";
	// ------------------------------------------ STATUS CODE
	
	public static final String CREATE = "201";
	public static final String OK = "200";
	public static final String BAD_REQUEST = "400";
	public static final String NOT_AUTHORIZED = "401";
	public static final String FORBIDDEN = "403";
	public static final String WRONGEMAILPASSWORD = "402";
	public static final String NOT_FOUND = "404";
	public static final String SERVER_ERROR = "500";
	public static final String DB_CONNECTION_ERROR = "502";
	public static final String ENCRYPTION_DECRYPTION_ERROR = "503";
	public static final String NOT_EXIST = "405";
	public static final String CONFLICT = "409";
	/*============================= OTHER CONSTATNT =================================*/
	public static final Short ZERO = 0;
	public static final Short ONE = 1;
	public static final Short TWO = 2;
	public static final Short THREE = 3;//not verified
	public static final Short FOUR = 4;
	public static final Object PUSH_NOTIFICATION = "Push notification send successfully !!";
	public static final Object DOCUMENT_SAVED_SUCCESFULLY = "Document Saved Successfully !!";
	public static final Object ERROR_MESSAGE = "Please try again";
	public static final Object UNSUPPORTED_FILE = "Unsupported file";
	public static final Object FILE_NOT_FOUND = "file not found";
	public static final String FILE_PATH = "FilePath";
	public static final String NOTIFICATION_SEND = "Send notification successfully ";
	public static final String NOTIFICATION_LIST = "Notification list";
	public static final String PHONE_NUMBER_ALREADY_EXIT = "Phone number already exit please try to another number";
	public static final String FORGET_PASSWORD_PAGE_LINK = "You have registerd successfully, we have sent an otp to your mobile please verify your account by otp.";
	public static final String USER_MOBILE_NOT_FOUND_MESSAGE = "Given mobile number not found into the database";
	public static final String USER_DELETED_CANT_BLOCK_UNBLOCK = "User deleted can't block & unblock !!";
	public static final String OTP_RESEND = "OTP resent successfully, please verify!!";
	public static final String INVALID_STATUS_VALUE = "Invalid status value, it should be 0 or 1";
	public static final String DEVICE_TYPE_NULL = "Please select the device type !!";
	public static final String USER_VERIFY_SUCCESSFULY = "User verified successfully!!";
	public static final String INVALID_USER_ID = "Invalid user id";
	public static final String BAD_REQUEST_MESSAGE = "Bad request !!";
	public static final String INVALID_CREDENTIALS = "Invalid credentials !!";
	public static final String DEVICE_TOKEN_NULL = "Device token can't be null !!";
	public static final String DEVICE_ID_NULL = "Device id can't be null !!";
	public static final String COUNTRY_CODE_NULL = "Please select the country code !!";
	public static final String INVALID_USER_DEATAILS = "User deatails can't be null !!";
	public static final String TNC = "Terms and condition uploaded successfully";
	public static final String TNC_UPDATED = "Terms and condition updated successfully";
	public static final String POLICY = "privacy and policy uploaded successfully";
	public static final String POLICY_UPDATED = "privacy and policy updated successfully";
	public static final String UPLOAD_TNC_POLICY = "please upload terms & condition or privacy & policy and type";
	public static final String AlREADY_UPDATED_TNC = "Already updated terms & condition";
	public static final String AlREADY_UPDATED_POLICY = "Already updated privacy & policy";

	public static final String TNC_AND_POLICY = "Terms & Condition or Privacy & Policy not found !!";
	public static final String MSG_TYPE_NOT_NULL = "Type can't be null. Please provide type 1 or 2.";
	public static final String USER_NOT_VERIFEID = "User not verified.Please first verify user then update your user !!";
	public static final String MOBILE_BLOCKED_MESSAGE = "This phone number have blocked, please contact with support team !!";
	public static final String MOBILE_DELETED_MESSAGE = "This phone number have deleted, please contact with support team !!";
	public static final String INVALID_MOBILE_AND_OTP_MESSAGE = "Invalid mobile number and OTP !!";

	public static final String NOT_FOUND_MESSAGE = "Not found!!";
	public static final String OTP_NOT_VERIFIED_MESSAGE = "OTP Not verified!!";
	public static final String FOUND_MESSAGE = "Found!!";
	public static final String PHONE_NUMBER_NOT_FOUND_MESSAGE = "Given phone number for OTP, not found into the database!!";
	public static final String NOT_DOWNLOAD_FILE_MESSAGE = "Not able to download the file, because no record found on the database";
	public static final String PAGE_SIZE_MESSAGE = "Page size can't be zero, it should be more then zero!!";

	// ------------------------------------------ USER PROFILE CODE-----------------------------------
	public static final String PROFILE_UPDATED_MESSAGE = "Profile updated successfully!!";
	public static final String PROFILE_NOT_UPDATED_MESSAGE = "Profile not updated because, given user id not found into the database!";
	//public static final String PROFILE_NOT_UPDATED_MESSAGE = "Profile not updated because, given phone number and country code not found into the database!";
	public static final String CONTACT_US_QUERY_SAVED_MESSAGE = "Your query saved successfully!!";
	public static final String USER_NOT_FOUND = "User not found !!";
	public static final String USER_COUNT = "User count found successfully !!";
	public static final String RECORD_FOUND = "Data found !!";
	public static final String DATA_FOUND_MESSAGE = "Data found !!";
	public static final String NO_DB_SERVER_CONNECTION = "The server was found but the connection to its local database was not possible.";
	public static final Object PROFILE_UPLOAD = "Profile upload successfully !!";
	public static final String PLAN_DELETED_CANT_BLOCK_UNBLOCK = "User deleted can't block & unblock !!";
}