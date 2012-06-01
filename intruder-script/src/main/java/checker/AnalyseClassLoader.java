package checker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author dragon.caol 2012-6-1 ÉÏÎç11:23:10
 */
public class AnalyseClassLoader {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		AnalyseClassLoader an = new AnalyseClassLoader();
		Map<String, String> jettyMap = an
				.loadAnalyse("D:\\temp\\com\\jetty_stdout.log");
		Map<String, String> jbossMap = an
				.loadAnalyse("D:\\temp\\com\\start1.log");
		Set<Map.Entry<String, String>> entrySet = jettyMap.entrySet();
		Map<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();
		for (Map.Entry<String, String> entry : entrySet) {
			String key = entry.getKey();
			String jettyvalue = entry.getValue();
			String jbossValue = jbossMap.get(key);
			if (jbossMap.containsKey(key) && !jettyvalue.equals(jbossValue)) {
				String jettyjboss = "jetty:" + jettyvalue + "-----jboss:"
						+ jbossValue;
				String split[] = jbossValue.split("/");
				String splitjetty[] = jettyvalue.split("/");
				String log = key + "," + splitjetty[splitjetty.length - 1]
						+ "," + split[split.length - 1];
				if (data.containsKey(jettyjboss)) {
					data.get(jettyjboss).add(log);
				} else {
					ArrayList<String> d = new ArrayList<String>();
					d.add(log);
					data.put(jettyjboss, d);
				}
			}
		}

		Set<Map.Entry<String, ArrayList<String>>> set = data.entrySet();
		for (Map.Entry<String, ArrayList<String>> str : set) {
			System.out.println("############################" + str.getKey()
					+ "############################");
			for (String temp : str.getValue()) {
				System.out.println(temp);
			}
		}
	}

	private Map<String, String> loadAnalyse(String filePath) throws IOException {
		Map<String, String> data = new TreeMap<String, String>();
		File f = new File(filePath);
		InputStream in = new FileInputStream(f);
		BufferedReader re = new BufferedReader(new InputStreamReader(in, "GBK"));
		String line = null;
		while ((line = re.readLine()) != null) {
			if (line.contains("Loaded ") && line.contains(" from ")) {
				String args[] = line.split("\\s+");
				String key = args[1];
				String value = args[3].substring(0, args[3].length() - 1);
				if (!data.containsKey(key)) {
					data.put(key, value);
				}
			}

		}
		in.close();
		re.close();
		return data;
	}
}
