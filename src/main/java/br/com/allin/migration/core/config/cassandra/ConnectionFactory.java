package br.com.allin.migration.core.config.cassandra;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;
import com.datastax.driver.core.policies.DefaultRetryPolicy;
import com.datastax.driver.core.policies.RoundRobinPolicy;

import br.com.allin.migration.core.util.cassandra.Util;

/**
 * @author Patrick Handja This class is responsible to load Cassandra
 *         configuration file in order to open Cassandra Session.
 */

public class ConnectionFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionFactory.class);
	public static final int EMBEDDED_CASSANDRA_SERVER_WAITING_TIME = 10000;
	private static Cluster cluster = null;

	private static Properties prop;

	public static Session openSession() throws Exception {
		Session session = null;

		try {
			if (prop == null) {
				prop = Util.loadCassandraConfig();
			}

			if (cluster == null || cluster.isClosed()) {

				String[] hosts = prop.getProperty("cassandra.contactpoints").split(",");

				PoolingOptions poolingOpts = new PoolingOptions();
				poolingOpts.setCoreConnectionsPerHost(HostDistance.LOCAL, 2);
				poolingOpts.setMaxConnectionsPerHost(HostDistance.LOCAL, 32768);

				cluster = new Cluster.Builder().addContactPoints(hosts).withPoolingOptions(poolingOpts)
						.withRetryPolicy(DefaultRetryPolicy.INSTANCE)
						.withReconnectionPolicy(new ConstantReconnectionPolicy(1000L))
						.withLoadBalancingPolicy(new RoundRobinPolicy())
						// .withMaxSchemaAgreementWaitSeconds(30)
						// .withCredentials(prop.getProperty("cassandra.username"),prop.getProperty("cassandra.password"))
						.build();
			}
			Metadata metadata = cluster.getMetadata();
			System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());

			session = cluster.connect(prop.getProperty("cassandra.keyspace"));
			ConnectionFactory.LOGGER.debug("** Cassandra Connection Opened 0/");
					
			return session;

		} catch (NoHostAvailableException e) {
			ConnectionFactory.LOGGER.error("No host available.", e.getLocalizedMessage());
		} catch (Exception e) {
			ConnectionFactory.LOGGER.error("Connection refused : ", e.getLocalizedMessage());
			System.out.println(e);
		}
		return null;
	}

	public static void close() {
		if (cluster != null) {
			cluster.close();
			ConnectionFactory.LOGGER.debug("** Cassandra Connection closed 0/");
		}
	}
}
