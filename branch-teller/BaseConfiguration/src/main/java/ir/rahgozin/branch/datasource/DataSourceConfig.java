package ir.rahgozin.branch.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    final MainDataSource mainDataSource;
    final ReportDataSource reportDataSource;

    public DataSourceConfig(MainDataSource mainDataSource, ReportDataSource reportDataSource) {
        this.mainDataSource = mainDataSource;
        this.reportDataSource = reportDataSource;
    }

    @Bean
    @Primary
    public DataSource dataSources() {

        Map<Object, Object> dataSourceMap = new HashMap<>();

        dataSourceMap.put(Constants.DATA_SOURCE_MAIN, mainDataSource());
        dataSourceMap.put(Constants.DATA_SOURCE_REPORT, reportDataSource());

        RoutingDataSource routingDataSource = new RoutingDataSource();

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(dataSourceMap.get(Constants.DATA_SOURCE_MAIN));
        return routingDataSource;
    }

    public DataSource mainDataSource() {
        return new HikariDataSource(mainDataSource);
    }

    public DataSource reportDataSource() {
        return new HikariDataSource(reportDataSource);
    }

}
