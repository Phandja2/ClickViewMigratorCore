package br.com.allin.migration.core.util.cassandra;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {
	private static final Logger log = LoggerFactory.getLogger(Util.class);

	private static final String CASSANDRA_CONFIG = "cassandra.properties";

	private static final String PROFILES_CONFIG = "profiles.properties";

	public Util() {

	}

	public static String getTableName(String tableName, String profileId) {
		StringBuilder sb = null;
		try {
			sb = new StringBuilder(tableName);
			sb.append("_");
			sb.append(profileId);

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return sb.toString();
	}

	public static String getAllinProfile(String btgProfileId) {
		Properties prop = null;
		InputStream inputStream = null;
		try {
			if (prop == null) {
				inputStream = Util.class.getClassLoader().getResourceAsStream(PROFILES_CONFIG);
				prop = new Properties();
				prop.load(inputStream);
			}
		} catch (Exception e) {
			log.error("Error while loading Properties profile", e);
		}
		return prop.getProperty(btgProfileId);
	}

	public static String getDataSet(String location, String regex, String tableName) {
		StringBuffer cql = null;
		try {
			StringWriter writer = new StringWriter();
			InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(location);
			IOUtils.copy(inputStream, writer);

			cql = new StringBuffer(writer.toString());

			return cql.toString().replaceFirst(regex, tableName);
		} catch (Exception e) {
			log.error("Error while loading scripts metadata : ", e.getMessage());
			return null;
		}

	}

	public static void deleteFiles(File file) throws IOException {
		if (file.isDirectory()) {
			// directory is empty, then delete it
			if (file.list().length == 0) {
				file.delete();
				log.info("Directory is deleted : " + file.getAbsolutePath());

			} else {
				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					deleteFiles(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					log.info("Directory is deleted : " + file.getAbsolutePath());
				}
			}

		} else {
			// if file, then delete it
			file.delete();
			log.info("File is deleted : " + file.getAbsolutePath());
		}
	}

	public static Properties loadCassandraConfig() {
		return loadPropertiesConfig(CASSANDRA_CONFIG);
	}

	public static Collection<Object> loadProfiles() {
		return loadPropertiesConfig(PROFILES_CONFIG).keySet();
	}

	private static Properties loadPropertiesConfig(String fileName) {
		Properties properties = null;
		try {
			InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(fileName);
			properties = new Properties();
			properties.load(inputStream);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return properties;
	}

	public static void copyProperties(Object dest, Object orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
	}

}
