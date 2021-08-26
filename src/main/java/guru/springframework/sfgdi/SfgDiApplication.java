package guru.springframework.sfgdi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import guru.springframework.sfgdi.config.SfgConfiguration;
import guru.springframework.sfgdi.config.SfgConstructorConfig;
import guru.springframework.sfgdi.controllers.ConstructorInjectedController;
import guru.springframework.sfgdi.controllers.I18nController;
import guru.springframework.sfgdi.controllers.MyController;
import guru.springframework.sfgdi.controllers.PetController;
import guru.springframework.sfgdi.controllers.PropertyInjectedController;
import guru.springframework.sfgdi.controllers.SetterInjectedController;
import guru.springframework.sfgdi.datasource.FakeDataSource;
import guru.springframework.sfgdi.services.PrototypeBean;
import guru.springframework.sfgdi.services.SingletonBean;

@SpringBootApplication
public class SfgDiApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);

        System.out.println("----- Pet");

        PetController petController = (PetController) ctx.getBean("petController");
        System.out.println(petController.whichPetIsTheBest());

        System.out.println("----- I18n");

        I18nController i18nController = (I18nController) ctx.getBean("i18nController");
        System.out.println(i18nController.sayHello());

        System.out.println("----- MyController");

        MyController myController = (MyController) ctx.getBean("myController");
        System.out.println(myController.sayHello());

        System.out.println("----- Property");

        PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx.getBean(
            "propertyInjectedController"
        );
        System.out.println(propertyInjectedController.getGreeting());

        System.out.println("----- Setter");

        SetterInjectedController setterInjectedController = (SetterInjectedController) ctx.getBean(
            "setterInjectedController"
        );
        System.out.println(setterInjectedController.getGreeting());

        System.out.println("----- Constructor");

        ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) ctx.getBean(
            "constructorInjectedController"
        );
        System.out.println(constructorInjectedController.getGreeting());

        System.out.println("----- Bean Scopes");

        SingletonBean singletonBean1 = ctx.getBean(SingletonBean.class);
        System.out.println(singletonBean1.getMyScope());
        SingletonBean singletonBean2 = ctx.getBean(SingletonBean.class);
        System.out.println(singletonBean2.getMyScope());

        PrototypeBean prototypeBean1 = ctx.getBean(PrototypeBean.class);
        System.out.println(prototypeBean1.getMyScope());
        PrototypeBean prototypeBean2 = ctx.getBean(PrototypeBean.class);
        System.out.println(prototypeBean2.getMyScope());

        System.out.println("----- Fake Data Source");

        FakeDataSource fakeDataSource = ctx.getBean(FakeDataSource.class);
        System.out.println("username: " + fakeDataSource.getUsername());
        System.out.println("password: " + fakeDataSource.getPassword());
        System.out.println("jdbcurl:  " + fakeDataSource.getJdbcurl());

        System.out.println("----- Config Props Bean");

        SfgConfiguration sfgConfiguration = ctx.getBean(SfgConfiguration.class);
        System.out.println("username: " + sfgConfiguration.getUsername());
        System.out.println("password: " + sfgConfiguration.getPassword());
        System.out.println("jdbcurl:  " + sfgConfiguration.getJdbcurl());

        System.out.println("----- Constructor Binding");

        SfgConstructorConfig sfgConstructorConfig = ctx.getBean(SfgConstructorConfig.class);
        System.out.println("username: " + sfgConstructorConfig.getUsername());
        System.out.println("password: " + sfgConstructorConfig.getPassword());
        System.out.println("jdbcurl:  " + sfgConstructorConfig.getJdbcurl());
    }

}
