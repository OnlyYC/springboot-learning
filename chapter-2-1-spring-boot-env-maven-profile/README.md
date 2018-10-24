## 使用Maven profile的多环境配置
### 1、在resources 文件夹中为每个环境创建一个文件夹
```
└--resources
   └--dev
   └--test
   └--online
```

### 2、pom.xml中增加如下内容
```
    <profiles>
        <profile>
            <!-- 本地开发环境 -->
            <id>self</id>
            <properties>
                <profiles.active>self</profiles.active>
            </properties>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
        </profile>
        <profile>
            <!-- 开发环境 -->
            <id>local</id>
            <properties>
                <profiles.active>local</profiles.active>
            </properties>
        </profile>
        <profile>
            <!-- 测试环境 -->
            <id>dev</id>
            <properties>
                <profiles.active>dev</profiles.active>
            </properties>
        </profile>
        <profile>
            <!-- 预发布环境 -->
            <id>pre</id>
            <properties>
                <profiles.active>pre</profiles.active>
            </properties>
        </profile>
        <profile>
            <!-- 生产环境 -->
            <id>prod</id>
            <properties>
                <profiles.active>prod</profiles.active>
            </properties>
        </profile>
    </profiles>
    
    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <!-- 资源根目录排除各环境的配置，而后使用单独的资源目录来指定 -->
                <excludes>
                    <exclude>self/**</exclude>
                    <exclude>local/**</exclude>
                    <exclude>dev/**</exclude>
                    <exclude>pre/**</exclude>
                    <exclude>prod/**</exclude>
                </excludes>
            </resource>
            <resource>
                <directory>src/main/resources/${profiles.active}</directory>
            </resource>
        </resources>
    </build>
```

### 3、打包时增加-P参数指定profile
```
mvn clean package -Pdev
```
指定的profile对应的文夹中的配置文件将被放入jar包中。在启动JVM之后这些文件会在classpath下，可以被应用程序访问
