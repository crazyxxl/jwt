#### **springboot项目中如何使用该组件**

> 首先添加pom

```xml
<dependency>
	<groupId>com.xxl.book</groupId>
	<artifactId>book-demo1</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

> 其次在程序启动处添加注解@JWT

```java
@SpringBootApplication
@JWT
public class BookStart {
	public static void main(String[] args) {
		SpringApplication.run(BookStart.class, args);
	}
}
```

> 最后在配置文件添加配置：

```properties
##加密秘钥，不可为空
jwt.jwtKey=howsillyyouare
##需要拦截的路径，不可为空
jwt.urlPatterns=/api/v1/content/*
##认证未通过分发路径，不可为空
jwt.authFailPath=/api/v1/Authfail
##请求头起始位置标志，可为空
jwt.headerStartTag=book
##获取用户信息标志，可为空，默认为claims
jwt.claimsInfo
```