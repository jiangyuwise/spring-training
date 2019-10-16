# Spring Bean 命名

## XML
### 1. 有 id 用 id
### 2. 没有 id 用 name, name 字段可以定义多个值, 多个别名可以用空格, 逗号, 分号分隔开
```
<bean name="string1, string2 string3" class="java.lang.String">
```

```
// 根据类型获取 bean
context.getBeansOfType(String.class);

// 根据 id 或者别名获取别名列表
context.getAliases()
```

## 注解
### 1. 未提供别名, 默认使用小写驼峰作为bean 的 id, 如
```
// 效果等同于@Component("car")
@Component
public class Car{}

@Bean 注解的方法, 方法名即为 bean 的 id

@Bean("") 或者 @Bean(name="")指定别名
@Bean("id", "name1", "name2") 第 1 个是 id, 后面的是别名

```

