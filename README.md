# timereporter
java ee training

* Download postgres from http://www.enterprisedb.com/products-services-training/pgdownload#windows
* Install it for user postgres, password - postgres, port 5432
* Execute CREATE SCHEMA timereporter AUTHORIZATION postgres;
* Download wildfly 10.0 from http://wildfly.org/downloads/
* Install jbdc driver from third-party/postgres as deployment unit
* Change standalone.xml
>>Add datasource
                <datasource jta="true" jndi-name="java:/datasources/PostgreSQL" pool-name="PostgresDS" enabled="true" use-ccm="true">
                    <connection-url>jdbc:postgresql://localhost:5432/postgres</connection-url>
                    <driver-class>org.postgresql.Driver</driver-class>
                    <driver>postgresql-9.4-1201.jdbc4_org.postgresql.Driver_9_4</driver>
                    <security>
                        <user-name>postgres</user-name>
                        <password>postgres</password>
                    </security>
                    <validation>
                        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker"/>
                        <background-validation>true</background-validation>
                        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter"/>
                    </validation>
                </datasource>
>>Add security-domain
                <security-domain name="jdbcRealm">
                    <authentication>
                        <login-module code="Database" flag="required">
                            <module-option name="dsJndiName" value="java:/datasources/PostgreSQL"/>
                            <module-option name="principalsQuery" value="select password from timereporter.employee where login = ?"/>
                            <module-option name="rolesQuery" value="select er.rolename, 'Roles' from timereporter.employee_role_map erp join timereporter.employee e on e.login = erp.employee_login join timereporter.employeerole er on erp.role = er.rolename where e.login = ?"/>
                            <module-option name="hashAlgorithm" value="SHA-256"/>
                            <module-option name="hashEncoding" value="base64"/>
                            <module-option name="unauthenticatedIdentity" value="anonymous"/>
                        </login-module>
                    </authentication>
                </security-domain>

