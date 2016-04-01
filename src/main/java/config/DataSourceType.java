package config;

/**
 * DataSourceType: Const flag for different data sources
 */
public enum DataSourceType {
    /**
     * Memory holds data in memory
     */
    Memory,
    /**
     * RestHttp maintains a session to query against a Restful API
     */
    RestHttp,
    /**
     * DBConnect for connector against mgo, postgres, mysql, sqlite etc.
     */
    DBConnect,
}
