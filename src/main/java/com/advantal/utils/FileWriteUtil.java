package com.advantal.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileWriteUtil {
	public static Path writeFiles(MultipartFile file, String dir, String fileName) {
		Path file2=null;
		try {
			byte[] bytes = file.getBytes();
			String completePath = dir + "/";
			Path path = Paths.get(completePath);
			Files.createDirectories(path);
			if (!Files.exists(path)) {
				Files.createDirectories(path);
				Path writefilePath = Paths.get(completePath + fileName);
				Files.write(writefilePath, bytes);
			} else {
				Path writefilePath = Paths.get(completePath + fileName);
				file2 =Files.write(writefilePath, bytes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file2;
	}

}
