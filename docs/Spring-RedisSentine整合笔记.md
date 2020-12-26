1. 添加项目依赖
    ```xml
    <dependency>
        <groupId>org.springframework.data</groupId>
        <artifactId>spring-data-redis</artifactId>
    </dependency>
    <dependency>
        <groupId>redis.clients</groupId>
        <artifactId>jedis</artifactId>
    </dependency>
    ```
2. 添加redis集群配置(application.properties)
    ```properties
    jedis.poolConfig.maxIdle=10
    jedis.poolConfig.maxTotal=20
    jedis.poolConfig.testOnBorrow=false
    jedis.sentinelNodes[0].host=10.221.xxx.xx
    jedis.sentinelNodes[0].port=6379
    jedis.sentinelNodes[1].host=10.221.xxx.xx
    jedis.sentinelNodes[1].port=6379
    jedis.sentinelNodes[2].host=10.221.xxx.xx
    jedis.sentinelNodes[2].port=6379
    jedis.master.name=mymaster
    jedis.password=5OQ==
    ```
3. 读取配置文件实体类
    ```java
    @Data
    @ConfigurationProperties(prefix = "jedis")
    public class RedisProperties {
        private PoolConfig poolConfig ;
        private Master master ;
        private String password ;
        private List<SentinelNode> sentinelNodes = new ArrayList<>();
        private String userName ;
    }
    @Data
    public class Master {
       private String name;
    } 
    @Data
    public class PoolConfig {
        private Integer maxIdle;
        private Integer maxTotal;
        private Boolean testOnBorrow;
    }
    @Data
    public class SentinelNode {
        private String host;
        private Integer port;
    }
    ```
4. 主配置类编写
    ```java
    @Configuration
    @EnableConfigurationProperties(RedisProperties.class)
    public class MyConfig {
        @Autowired
        private RedisProperties redisProperties ;
        @Bean
        public JedisPoolConfig jedisPoolConfig(){
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(redisProperties.getPoolConfig().getMaxIdle());
            config.setMaxTotal(redisProperties.getPoolConfig().getMaxTotal());
            config.setTestOnBorrow(redisProperties.getPoolConfig().getTestOnBorrow());
            return config;
        }
        @Bean
        public RedisSentinelConfiguration sentinelConfig(){
            RedisSentinelConfiguration configuration = new RedisSentinelConfiguration();
            //配置matser的名称
            configuration.master(redisProperties.getMaster().getName()) ;
            // redis 密码解密
            String decodePwd = CommonUtil.decode(redisProperties.getPassword());
            configuration.setPassword(decodePwd);
            //配置redis的哨兵sentinel
            Set<RedisNode> redisNodeSet = new HashSet<>();
            redisProperties.getSentinelNodes().forEach(item->{
                redisNodeSet.add(new RedisNode(item.getHost(), item.getPort())) ;
            });
            configuration.setSentinels(redisNodeSet);
            return configuration ;
        }
        @Bean
        public JedisConnectionFactory connectionFactory(
                JedisPoolConfig jedisPoolConfig,RedisSentinelConfiguration sentinelConfig){
            return new JedisConnectionFactory(sentinelConfig,jedisPoolConfig) ;
        }
        @Bean
        public RedisTemplate redisTemplate(JedisConnectionFactory connectionFactory){
            RedisTemplate template = new RedisTemplate() ;
            template.setConnectionFactory(connectionFactory);
            template.setKeySerializer(new StringRedisSerializer());
            template.setValueSerializer(new JdkSerializationRedisSerializer());
            return template ;
        }
    }
    ```
    
    