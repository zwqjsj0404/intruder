package backupcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * replace the class file by read a class file from disk which has been
 * generated before
 * 
 * @author zili.dengzl
 * 
 */
public class ClassFileReaderTransformer implements ClassFileTransformer {

	public static final String classPath = "asm/Sample2.class.";

	public static byte[] getBytesFromFile(String fileName) {
		try {
			File file = new File(fileName);
			InputStream is = new FileInputStream(file);

			long length = file.length();
			byte[] bytes = new byte[(int) length];
			int numRead = is.read(bytes);
			is.close();
			return bytes;
		} catch (Exception e) {
			System.out.println("error occurs in ClassTransformer!"
					+ e.getClass().getName());
			e.printStackTrace();
			return null;
		}
	}

	public byte[] transform(ClassLoader l, String className, Class<?> clazz,
			ProtectionDomain pd, byte[] b) throws IllegalClassFormatException {
		System.out.println("in Transformer's transform method, className="
				+ className);
		if (!className.equals("asm/Sample")) {
			return null;
		}
		System.out.println("Transform class..");
		return getBytesFromFile(classPath);

	}
}
