package com.advantal.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUploader {

	public static String uploadFile(MultipartFile mulfile, String path, Long userid) {
		String filename = "";

		if (!mulfile.isEmpty()) {
			filename = mulfile.getOriginalFilename();
			filename = userid + "_" + filename;

			try {
				// Delete all existing files associated with the userid
				File userDir = new File(path);
				File[] existingFiles = userDir.listFiles((dir, name) -> name.startsWith(userid + "_"));

				if (existingFiles != null) {
					for (File existingFile : existingFiles) {
						existingFile.delete();
					}
				}

				byte[] bytes = mulfile.getBytes();
				BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(path + filename, true));
				buffStream.write(bytes);
				buffStream.close();

				return filename;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return filename;
	}
	
	public static String uploadFileWithId(MultipartFile mulfile, String path, String userid) {
		String filename = "";

		if (!mulfile.isEmpty()) {
			filename = mulfile.getOriginalFilename();
			filename = userid + "_" + filename;

			try {
				// Delete all existing files associated with the userid
				File userDir = new File(path);
				File[] existingFiles = userDir.listFiles((dir, name) -> name.startsWith(userid + "_"));

				if (existingFiles != null) {
					for (File existingFile : existingFiles) {
						existingFile.delete();
					}
				}

				byte[] bytes = mulfile.getBytes();
				BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(path + filename, true));
				buffStream.write(bytes);
				buffStream.close();

				return filename;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return filename;
	}
}