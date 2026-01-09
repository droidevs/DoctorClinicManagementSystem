/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Migrations;


import Entities.AppointmentEntity;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;

@Startup
@Singleton
public class FlywayMigrationRunner {

    @Resource(lookup = "java:jboss/datasources/ClinicDS")
    private DataSource dataSource;

    @PostConstruct
    public void migrate() {
        AppointmentEntity e;
        
        Flyway.configure()
              .dataSource(dataSource)
              .locations("classpath:Migrations")
              .baselineOnMigrate(true)
              .load()
              .migrate();
    }
}

