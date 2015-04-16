dataSource {
    pooled = true
    dbCreate = "update"
    url = "jdbc:mysql://localhost/skynet"
    driverClassName = "com.mysql.jdbc.Driver"
    dialect = org.hibernate.dialect.MySQL5InnoDBDialect
    username = "skynet"
    password = "atos"
    logSql = true
    properties {
    	maxActive = 50
    	maxIdle = 25
    	minIdle = 5
    	initialSize = 5
    	minEvictableIdleTimeMillis = 60000
    	timeBetweenEvictionRunsMillis = 60000
    	maxWait = 10000
	validationQuery = "/* ping */"
    }
}

hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
    development {
        dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
	url = "jdbc:mysql://localhost/skynet?useUnicode=yes&characterEncoding=UTF-8"
	username = "skynet"
	password = "atos"
    }
    test {
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:mysql://localhost/skynet?useUnicode=yes&characterEncoding=UTF-8"
            username = "skynet"
            password = "atos"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://localhost/skynet?useUnicode=yes&characterEncoding=UTF-8"
            username = "skynet"
            password = "atos"
        }
    }
}
