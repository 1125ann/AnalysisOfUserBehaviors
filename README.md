##0、环境的介绍：
1. spark 2.4.4
2. scala 2.11.8
3. hive 3.1.2
4. mysql 5.7.28
5. kafka_2.12-2.3.0
6. jdk 1.8.0_192
7. hadoop 2.9.2
8. zookeeper-3.5.5
##1、程序中包的解释
- ....
####Commons包：公共模块包
- conf：配置工具类，获取commerce.properties文件中的所有配置信息，
使用户可以通过对象的方式访问commerce.properties中的所有配置
- constant：常量接口，包括项目中所需要使用的所有常量
- model： Spark SQL样例类，包括Spark SQL中的用户访问动作表、
用户信息表、产品表的样例类
- pool：MySQL连接池，通过自定义MySQL连接池，实现对MySQL数据库
的操作
- utils：工具类，提供了日期时间工具类、数字格式工具类、参数工具类、字符串工具类、校验工具类等工具类，
里面的类有:
1. DateUtils：时间工具类，负责时间的格式化、判断时间先后、计算时间差值、获取指定日期等工作
2. NumberUtils：数字工具类，负责数字的格式化工作，将Double类型的数字精确为指定位数的小数
3. ParamUtils：参数工具类，负责从JSON对象中提取参数
4. StringUtils:字符串工具类,负责字符串是否为空判断、字符串截断与补全、从拼接字符串中提取字段、给拼接字符串中字段设置值等工作
5. ValidUtils:校验工具类,负责校验数据中的指定字段是否在指定范围范围内、校验数据中的指定字段中是否有值与参数字段相同、校验数据中的指定字段是否与参数字段相同等工作
####mock包：模拟数据产生包
- MockDataGenerate：离线模拟数据生成，负责生成离线模拟数据
并写入Hive表中，模拟数据包括用户行为信息、用户信息、产品数据
信息等
- MockRealtimeDataGenerate：实时模拟数据生成，负责生成实时
模拟数据并写入Kafka中，实时模拟数据为实时广告数据
####analysis包：数据分析包

##2、数据库设计
####mysql中commerce数据库
- 用于存储计算结果
####hive中db_UserBehaviors数据库
- user_visit_action表：存放的是用户行为（点击，搜索，下单，付款四种行为）
- user_info表：存放的是用户信息
- product_info表：存放的是产品信息

##3、注意事项
- hive数据库元数据总是出
Unable to instantiate org.apache.hadoop.hive.ql.metadata.SessionHiveMetaStoreClient问题；
执行hive --service metastore &
- hive中删除有表的数据库：drop database 数据库名字 cascade;

##4、需求分析
###大需求一：用户访问Session统计
####小需求一：用户访问session统计：各个范围的session步长，访问时长占比统计
1. 访问时长：session的最早时间和最晚时间只差
2. 访问步长：session和action的个数
3. 统计出符合筛选条件的session中，访问时长在
1s~3s、4s~6s、7s~9s、10s~30s、30s~60s、1m~3m、3m~10m、10m~30m、30m，
访问步长在1_3、4_6、…以上各个范围内的各种session的占比。
###大需求二：页面单跳转化率统计
###
