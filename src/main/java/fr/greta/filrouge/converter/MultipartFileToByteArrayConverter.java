package fr.greta.filrouge.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class MultipartFileToByteArrayConverter implements Converter<MultipartFile, byte[]> {
	@Override
	public byte[] convert(MultipartFile file) {
		try {
			return file.getBytes();
		}
		catch(Exception ex) {
			return null;
		}
	}
}