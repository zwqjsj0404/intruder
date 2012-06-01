package checker;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * file to scan must according to this format:
 * 
 * <code>className,jarNameinJetty,jarNameinJboss</code>
 * 
 * @author zili.dengzl
 * 
 */
public class ClassContentComparator {

	static Map<String, List<JarFile>> toCompareMap = new HashMap<String, List<JarFile>>();
	static Map<String, List<JarFile>> result = new HashMap<String, List<JarFile>>();

	/**
	 * @param args
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		if (null == args || args.length < 3) {
			System.out
					.println("Usage: java ClassContentComparator <rootPath> <leftJarDirectory> <rightJarDirectory>");
		}

		String rootPath = args[0]; // "D:/a";
		String leftJarDirectory = args[1]; // "D:/jetty/";
		String rightJarDirectory = args[2]; // "D:/jboss/";

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(rootPath)));
		String line = null;
		while ((line = reader.readLine()) != null) {
			// System.out.println(line);
			String[] strs = line.split(",");
			List<JarFile> list = new ArrayList<JarFile>();
			list.add(new JarFile(leftJarDirectory + strs[1]));
			list.add(new JarFile(rightJarDirectory + strs[2]));
			toCompareMap.put(strs[0], list);
		}

		// System.out.println(toCompareMap);

		for (Entry<String, List<JarFile>> e : toCompareMap.entrySet()) {
			String className = e.getKey().replace(".", "/") + ".class";
			List<JarFile> jars = e.getValue();
			JarFile leftJar = jars.get(0);
			JarFile rightJar = jars.get(1);
			ZipEntry entry0 = leftJar.getEntry(className);
			ZipEntry entry1 = rightJar.getEntry(className);
			if (entry0.getSize() != entry1.getSize()) {
				result.put(e.getKey(), jars);
				System.out.println(e.getKey() + ":" + leftJar.getName() + ","
						+ rightJar.getName());
			}
		}

		// System.out.println(result);
	}
}
