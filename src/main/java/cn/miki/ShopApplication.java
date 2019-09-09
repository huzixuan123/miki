package cn.miki;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "cn.miki.db.mapper")
public class ShopApplication {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(ShopApplication.class, args);
    }
}
