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

	private String pathDir = "/home/eve/eve";

	public void setPathDir(String pathDir) {
		this.pathDir = pathDir;
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void execute() throws Exception {
		new per().start();
	}

	private class per extends Thread {

		public void run() {
			try {
				FilterClass f = new FilterClass();
//				Set<String> listClass = f.findClassfromDir(pathDir);
				System.out.println("AutoClassLoadUtil classLoader=" + AutoClassLoadUtil.class.getClassLoader());
				System.out.println("AutoClassLoadUtil parent classLoader=" + AutoClassLoadUtil.class.getClassLoader().getParent());
				
//				for (String class1 : listClass) {
//					System.out.println("begin load:" + class1);
//					try {
//						Class.forName(class1);
//						System.out.println("end load:" + class1);
//					} catch (Throwable e) {
//						System.out.println("begin error:" + class1 + e.getMessage());
//						e.printStackTrace();
//					}
//					
//				}
			} catch (Throwable e) {
				System.err.println("begin error:" + e.getMessage() + "," + e.getStackTrace());
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

		private Set<String> filterClassFromSrc(String javaPath) throws Exception {
			File f = new File(javaPath);
			Set<String> listClass = new HashSet<String>();
			if (f.isFile()) {
				InputStream in = new FileInputStream(f);
				BufferedReader re = new BufferedReader(new InputStreamReader(in, "GBK"));
				String line = null;
				while ((line = re.readLine()) != null) {
					if (line.contains("import")) {
						if (line.contains("//") || line.contains("*")) {

						} else {
							String[] lines = line.split("\\s+");
							if (!"static".equals(lines[1])) {
								listClass.add(lines[1].substring(0, lines[1].length() - 1));
							}
						}
						continue;
					}
					if (line.contains("class") || line.contains("interface")) {
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
