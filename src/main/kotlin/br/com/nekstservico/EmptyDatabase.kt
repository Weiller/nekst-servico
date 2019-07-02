package br.com.nekstservico

import de.flapdoodle.embed.process.runtime.Network
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.jdbc.datasource.DriverManagerDataSource
import java.io.IOException
import ru.yandex.qatools.embed.postgresql.PostgresProcess
import ru.yandex.qatools.embed.postgresql.PostgresStarter
import ru.yandex.qatools.embed.postgresql.config.PostgresConfig
import ru.yandex.qatools.embed.postgresql.config.AbstractPostgresConfig
import org.hibernate.cfg.AvailableSettings
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import ru.yandex.qatools.embed.postgresql.distribution.Version
import java.lang.String.format
import java.util.*
import javax.sql.DataSource
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter




@Configuration
@EnableTransactionManagement
@ComponentScan("br.com.nekstservico")
@EnableJpaRepositories("br.com.nekstservico.repository")
class EmptyDatabase {

    private val DEFAULT_ADDITIONAL_INIT_DB_PARAMS = Arrays
            .asList("--nosync", "--locale=en_US.UTF-8")

    /**
     * @param config the PostgresConfig configuration to use to start Postgres db process
     * @return PostgresProcess , the started db process
     * @throws IOException
     */
    @Bean
    @DependsOn("postgresProcess")
    fun dataSource(config: PostgresConfig): DataSource {
        val ds = DriverManagerDataSource()
        ds.setDriverClassName("org.postgresql.Driver")
        ds.url = format("jdbc:postgresql://%s:%s/%s", config.net().host(), config.net().port(), config.storage().dbName())
        ds.username = config.credentials().username()
        ds.password = config.credentials().password()
        return ds
    }

    /**
     * @param dataSource the db data source
     * @return the local entity manager factory bean
     */
    @Bean
    fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        val lcemfb = LocalContainerEntityManagerFactoryBean()
        lcemfb.dataSource = dataSource
        // set the packages to scan , it can be useful if you have big project and you just need to local partial entities for testing
        lcemfb.setPackagesToScan("br.com.nekstservico.dominio", "br.com.nekstservico.repository")
        val va = HibernateJpaVendorAdapter()
        lcemfb.jpaVendorAdapter = va
        lcemfb.setJpaProperties(getHibernateProperties())
        lcemfb.afterPropertiesSet()
        return lcemfb
    }

    /**
     * @param localContainerEntityManagerFactoryBean
     * @return the JPA transaction manager
     */
    @Bean
    fun transactionManager(localContainerEntityManagerFactoryBean: LocalContainerEntityManagerFactoryBean): JpaTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = localContainerEntityManagerFactoryBean.getObject()
        return transactionManager
    }

    @Bean
    fun exceptionTranslation(): PersistenceExceptionTranslationPostProcessor {
        return PersistenceExceptionTranslationPostProcessor()
    }

    /**
     * @return the hibernate properties
     */
    private fun getHibernateProperties(): Properties {
        val ps = Properties()
        ps["hibernate.temp.use_jdbc_metadata_defaults"] = "false"
        ps["hibernate.dialect"] = "org.hibernate.dialect.PostgreSQL95Dialect"
        ps["hibernate.connection.characterEncoding"] = "UTF-8"
        ps["hibernate.connection.charSet"] = "UTF-8"
        ps[AvailableSettings.FORMAT_SQL] = "true"
        ps[AvailableSettings.SHOW_SQL] = "true"
        return ps
    }

    @Bean
    @Throws(IOException::class)
    fun postgresConfig(): PostgresConfig {
        val postgresConfig = PostgresConfig(Version.V9_6_11,
                AbstractPostgresConfig.Net("localhost", Network.getFreeServerPort()),
                AbstractPostgresConfig.Storage("test"),
                AbstractPostgresConfig.Timeout(),
                AbstractPostgresConfig.Credentials("user", "pass")
        )
        postgresConfig.additionalInitDbParams.addAll(DEFAULT_ADDITIONAL_INIT_DB_PARAMS)
        return postgresConfig
    }

    @Bean(destroyMethod = "stop")
    @Throws(IOException::class)
    fun postgresProcess(config: PostgresConfig): PostgresProcess {
        val runtime = PostgresStarter.getDefaultInstance()
        val exec = runtime.prepare(config)
        return exec.start()
    }
}