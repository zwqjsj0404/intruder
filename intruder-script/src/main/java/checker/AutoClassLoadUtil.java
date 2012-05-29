package checker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dragon.caol
 * 
 */
public class AutoClassLoadUtil {

	private String pathDir = null;

	/**
	 * 
	 * @throws Exception
	 */
	public void execute(String[] args) throws Exception {
		if (args.length != 1) {
			throw new IllegalArgumentException("the parameters length is 1");
		}
		pathDir = args[0];
		new per().start();
	}

	private class per extends Thread {

		public void run() {
			try {
				FilterClass f = new FilterClass();
				Set<String> listClass = f.findClassfromDir(pathDir);
				Thread.sleep(1000);
				for (String class1 : listClass) {
					System.out.println("begin load:" + class1);
					try {
						Class.forName(class1);
					} catch (Throwable e) {
						// for inter class
						try {
							int i = class1.lastIndexOf(".");
							String last = null;
							if (i > 0) {
								last = class1.substring(0, i) + "$"
										+ class1.substring(i + 1);
							} else {
								last = class1;
							}
							Class.forName(last);
						} catch (Throwable ee) {
							System.err.println("begin error:" + e.getMessage()
									+ ",from:" + e.getMessage());
							ee.printStackTrace();
							System.err.println(" is ");
							e.printStackTrace();
						}
					}
					System.out.println("end load:" + class1);
				}

			} catch (Throwable e) {
				System.err.println("begin error:" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private class FilterClass {

		public Set<String> findClassfromDir(String dir) throws Exception {
			Set<String> listClass = new HashSet<String>();
			List<String> listPath = findjava(new File(dir));
			for (String path : listPath) {
				listClass.addAll(filterClassFromSrc(path));
			}
			return listClass;
		}

		private List<String> findjava(File file) {
			final List<String> listjava = new ArrayList<String>();
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (File f : files) {
					if (f.isFile()) {
						if (f.getAbsolutePath().endsWith(".java")) {
							listjava.add(f.getAbsolutePath());
						}
					} else if (f.isDirectory()) {
						if (f.getName().contains("java.test")) {
							// ingore
						} else {
							listjava.addAll(findjava(f));
						}
					}
				}
			}
			return listjava;
		}

		private Set<String> filterClassFromSrc(String javaPath)
				throws Exception {
			File f = new File(javaPath);
			Set<String> listClass = new HashSet<String>();
			if (f.isFile()) {
				InputStream in = new FileInputStream(f);
				BufferedReader re = new BufferedReader(new InputStreamReader(
						in, "GBK"));
				String line = null;
				while ((line = re.readLine()) != null) {
					if (line.contains("import")) {
						if (line.contains("//") || line.contains("*")) {

						} else {
							String[] lines = line.split("\\s+");
							if ("static".equals(lines[1])) {

							} else {
								String temp = lines[1];
								listClass.add(temp.split(";")[0]);
							}
						}
						continue;
					}
					if (line.contains("class") || line.contains("interface")
							|| line.contains("@interface")) {
						break;
					}
				}

				in.close();
				re.close();
			} else {
				System.out.println("error filter ");
				throw new Exception();
			}
			return listClass;
		}
	}

}
