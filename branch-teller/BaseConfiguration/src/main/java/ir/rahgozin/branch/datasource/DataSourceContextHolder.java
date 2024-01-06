package ir.rahgozin.branch.datasource;

public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);
    }

    public static String getDataSource() {
        String dataSourceName = contextHolder.get();
        if (dataSourceName == null)
            return Constants.DATA_SOURCE_MAIN;
        return dataSourceName;
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }

}
