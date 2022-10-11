package ru.kolobkevic.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.kolobkevic.market.crud.SessionFactoryUtils;

@SpringBootApplication
public class MarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketApplication.class, args);
        SessionFactoryUtils sessionFactory = new SessionFactoryUtils();
        sessionFactory.init();

        try {

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            sessionFactory.shutdown();
        }
    }

}
