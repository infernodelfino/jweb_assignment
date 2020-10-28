package fa.training.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ConnectionUtils {
	private static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null || sessionFactory.isClosed()) {
			Configuration cfg = new Configuration();
			cfg.configure();
			ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
			sessionFactory = cfg.buildSessionFactory(sr);
		}
		return sessionFactory;
	}
}
