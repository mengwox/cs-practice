### 1.Docker常用命令

#### 1.1 Docker Images

查看当前服务器images： docker images

#### 1.2 Docker Container



docker exec -it 

docker ps 和docker ps -a有什么区别？

对于running状态的container：

1， docker stop #{container id}，终止container

2，docker rm #{container id}, 删除container



服务器关闭后，重新启动,docker的container处于stop状态，需要docker start启动。



#### 1.3 配置Docker镜像加速器：

```markdown
见https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors
页签：镜像工具-镜像加速器
```

Docker灵魂篇：

1. docker image到底做了什么？从而使得它能够自动化的完成相应的依赖，以及不需要我们配置一系列复杂的环境或者操作系统

   docker通过规定DockerFile文件，及其语法。通过一个文件，指定需要的操作系统，以及一系列的step layer，完成一般情况下的各个步骤的安装依赖命令。将所有的step集成到一个DockerFile内

2. 自己通过使用DockerFile文件，完成docker build操作，生成一个image,并将自己的image push到hub.docker.com官方镜像仓库，push到aliyun的镜像仓库

3. 学会 DockerFile的一些简单语法。

4. 学会push 或 pull的时候指定tag标签，push或pull指定版本的镜像

5. 学会将Image pull 或push docker官方、aliyun docker、局域网镜像仓库

6. 搭建harbor

7. docker container commit



问题1：Docker Image做了什么？我能不能自己根据DockerFile文件，自己build一个image，并实现正常的使用？

```
1，找到docker-library的github代码管理仓库：https://github.com/docker-library
2，找到docker-library/tomcat，选择8.5版本选择一个版本的DockerFile，Code路径：tomcat/8.5/jdk8/openjdk-buster/Dockerfile
```

#### 1.4 tomcat 8.5版本 openjdk-buster DockerFile文件

```dockerfile
FROM openjdk:8-jdk-buster

ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH
RUN mkdir -p "$CATALINA_HOME"
WORKDIR $CATALINA_HOME

# let "Tomcat Native" live somewhere isolated
ENV TOMCAT_NATIVE_LIBDIR $CATALINA_HOME/native-jni-lib
ENV LD_LIBRARY_PATH ${LD_LIBRARY_PATH:+$LD_LIBRARY_PATH:}$TOMCAT_NATIVE_LIBDIR

# see https://www.apache.org/dist/tomcat/tomcat-$TOMCAT_MAJOR/KEYS
# see also "update.sh" (https://github.com/docker-library/tomcat/blob/master/update.sh)
ENV GPG_KEYS 05AB33110949707C93A279E3D3EFE6B686867BA6 07E48665A34DCAFAE522E5E6266191C37C037D42 47309207D818FFD8DCD3F83F1931D684307A10A5 541FBE7D8F78B25E055DDEE13C370389288584E7 5C3C5F3E314C866292F359A8F3AD5C94A67F707E 61B832AC2F1C5A90F0F9B00A1C506407564C17A3 765908099ACF92702C7D949BFA0C35EA8AA299F1 79F7026C690BAA50B92CD8B66A3AD3F4F22C4FED 8B46CA49EF4837B8C7F292DAA54AD08EA7A0233C 9BA44C2621385CB966EBA586F72C284D731FABEE A27677289986DB50844682F8ACB77FC2E86E29AC A9C5DF4D22E99998D9875A5110C01C5A2F6059E7 DCFD35E0BF8CA7344752DE8B6FB21E8933C60243 F3A04C595DB5B6A5F1ECA43E3B7BBB100D811BBE F7DA48BB64BCB84ECBA7EE6935CD23C10D498E23

ENV TOMCAT_MAJOR 8
ENV TOMCAT_VERSION 8.5.68
ENV TOMCAT_SHA512 673f8d295d6f6b8e53b30c9c81989a3586341f9660f00e61a131153f54f0ce9d41bf8f49960ef477d3ec81610e1c75c1684c8676e9ebe5015486334c443d82e5

RUN set -eux; \
	\
	savedAptMark="$(apt-mark showmanual)"; \
	apt-get update; \
	apt-get install -y --no-install-recommends \
		gnupg dirmngr \
		wget ca-certificates \
	; \
	\
	ddist() { \
		local f="$1"; shift; \
		local distFile="$1"; shift; \
		local mvnFile="${1:-}"; \
		local success=; \
		local distUrl=; \
		for distUrl in \
# https://issues.apache.org/jira/browse/INFRA-8753?focusedCommentId=14735394#comment-14735394
			"https://www.apache.org/dyn/closer.cgi?action=download&filename=$distFile" \
# if the version is outdated (or we're grabbing the .asc file), we might have to pull from the dist/archive :/
			"https://www-us.apache.org/dist/$distFile" \
			"https://www.apache.org/dist/$distFile" \
			"https://archive.apache.org/dist/$distFile" \
# if all else fails, let's try Maven (https://www.mail-archive.com/users@tomcat.apache.org/msg134940.html; https://mvnrepository.com/artifact/org.apache.tomcat/tomcat; https://repo1.maven.org/maven2/org/apache/tomcat/tomcat/)
			${mvnFile:+"https://repo1.maven.org/maven2/org/apache/tomcat/tomcat/$mvnFile"} \
		; do \
			if wget -O "$f" "$distUrl" --progress=dot:giga && [ -s "$f" ]; then \
				success=1; \
				break; \
			fi; \
		done; \
		[ -n "$success" ]; \
	}; \
	\
	ddist 'tomcat.tar.gz' "tomcat/tomcat-$TOMCAT_MAJOR/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz" "$TOMCAT_VERSION/tomcat-$TOMCAT_VERSION.tar.gz"; \
	echo "$TOMCAT_SHA512 *tomcat.tar.gz" | sha512sum --strict --check -; \
	ddist 'tomcat.tar.gz.asc' "tomcat/tomcat-$TOMCAT_MAJOR/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz.asc" "$TOMCAT_VERSION/tomcat-$TOMCAT_VERSION.tar.gz.asc"; \
	export GNUPGHOME="$(mktemp -d)"; \
	for key in $GPG_KEYS; do \
		gpg --batch --keyserver keyserver.ubuntu.com --recv-keys "$key"; \
	done; \
	gpg --batch --verify tomcat.tar.gz.asc tomcat.tar.gz; \
	tar -xf tomcat.tar.gz --strip-components=1; \
	rm bin/*.bat; \
	rm tomcat.tar.gz*; \
	command -v gpgconf && gpgconf --kill all || :; \
	rm -rf "$GNUPGHOME"; \
	\
# https://tomcat.apache.org/tomcat-9.0-doc/security-howto.html#Default_web_applications
	mv webapps webapps.dist; \
	mkdir webapps; \
# we don't delete them completely because they're frankly a pain to get back for users who do want them, and they're generally tiny (~7MB)
	\
	nativeBuildDir="$(mktemp -d)"; \
	tar -xf bin/tomcat-native.tar.gz -C "$nativeBuildDir" --strip-components=1; \
	apt-get install -y --no-install-recommends \
		dpkg-dev \
		gcc \
		libapr1-dev \
		libssl-dev \
		make \
	; \
	( \
		export CATALINA_HOME="$PWD"; \
		cd "$nativeBuildDir/native"; \
		gnuArch="$(dpkg-architecture --query DEB_BUILD_GNU_TYPE)"; \
		aprConfig="$(command -v apr-1-config)"; \
		./configure \
			--build="$gnuArch" \
			--libdir="$TOMCAT_NATIVE_LIBDIR" \
			--prefix="$CATALINA_HOME" \
			--with-apr="$aprConfig" \
			--with-java-home="$JAVA_HOME" \
			--with-ssl=yes; \
		make -j "$(nproc)"; \
		make install; \
	); \
	rm -rf "$nativeBuildDir"; \
	rm bin/tomcat-native.tar.gz; \
	\
# reset apt-mark's "manual" list so that "purge --auto-remove" will remove all build dependencies
	apt-mark auto '.*' > /dev/null; \
	[ -z "$savedAptMark" ] || apt-mark manual $savedAptMark > /dev/null; \
	find "$TOMCAT_NATIVE_LIBDIR" -type f -executable -exec ldd '{}' ';' \
		| awk '/=>/ { print $(NF-1) }' \
		| xargs -rt readlink -e \
		| sort -u \
		| xargs -rt dpkg-query --search \
		| cut -d: -f1 \
		| sort -u \
		| xargs -r apt-mark manual \
	; \
	apt-get purge -y --auto-remove -o APT::AutoRemove::RecommendsImportant=false; \
	rm -rf /var/lib/apt/lists/*; \
	\
# sh removes env vars it doesn't support (ones with periods)
# https://github.com/docker-library/tomcat/issues/77
	find ./bin/ -name '*.sh' -exec sed -ri 's|^#!/bin/sh$|#!/usr/bin/env bash|' '{}' +; \
	\
# fix permissions (especially for running as non-root)
# https://github.com/docker-library/tomcat/issues/35
	chmod -R +rX .; \
	chmod 777 logs temp work; \
	\
# smoke test
	catalina.sh version

# verify Tomcat Native is working properly
RUN set -eux; \
	nativeLines="$(catalina.sh configtest 2>&1)"; \
	nativeLines="$(echo "$nativeLines" | grep 'Apache Tomcat Native')"; \
	nativeLines="$(echo "$nativeLines" | sort -u)"; \
	if ! echo "$nativeLines" | grep -E 'INFO: Loaded( APR based)? Apache Tomcat Native library' >&2; then \
		echo >&2 "$nativeLines"; \
		exit 1; \
	fi

EXPOSE 8080
CMD ["catalina.sh", "run"]
```

#### 1.5 DockerFile语法

1. From xxxx, 表示当前images基于xxxx的images构建
2. ENV CATALINA_HOME /usr/local/tomcat; 定义环境变量， CATALINA_HOME = /usr/local/tomcat
3. RUN  执行命令
4. WORKDIR
5. EXPOSE 8080; 当前images启动完毕后，在container内占用8080端口
6. CMD ["catalina.sh", "run"] ; 在cmd界面 执行 catalina.sh run命令

#### 1.6 使用Dockerfile 构建镜像

1. 创建一个文件,名为Dockerfile

2. 编辑好Dockerfile的内容

3. 使用docker build命令`docker build -t[image-name] [Dockerfile文件所在路径] ` 完成镜像的打包构建

   ```txt
   例如:
   docker build -t my-docker-image .
   上面这个命令,表示,使用docker build命令,构建一个名为my-docker-image的镜像,并在`.`(当前路径下)查找Dockerfile文件进行构建
   ```

### 2. Docker镜像的拉取和上传

#### 2.1 官方仓库

见hub.docker.com

#### 2.2 阿里云镜像仓库

见cr.console.aliyun.com/repository/cn-shenzhen/mengwx

#### 2.3 个人镜像仓库（局域网） 采用Harbor技术

1. 见github.com/goharbor/harbor/releases?after=v1.7.3，下载1.7.3版本的[Harbor offline installer] Harbor离线版本包
2. 上传linux服务器，并解压
3. 如需要修改配置，vi harbor.cfg, 这里主要是修改了hostname = 当前宿主机IP,和 harbor_admin_password = harbor(默认是Harbor12345)
4. 执行sh install.sh 命令，开始安装harbor
5. 安装完毕后，访问step3配置的hostname的IP，输入账号admin，密码

#### 2.4 Docker Image 上传步骤

1. 登录docker: docker login -u YOUR-USER-NAME, 按照提示,输入docker hub账号的密码
2. docker tag, 给本地image打上tag标记, 确保与docker hub的tag一致
3. docker push YOUR-USER-NAME/tag-name

### 3. Docker精髓篇

#### 3.1 Docker的相关疑问

1. Docker是如何实现new出来的一个新容器能够被宿主机访问到?
2. Docker是如何实现new出来的多个容器,能够相互直接访问到?

```markdown
Docker通过使用veth-pair技术,每new出来一个新的container,container内除了存在127.0.0.1的本机网卡以外, 还会存在一个对外访问的网卡,例如叫:veth1;
与此同时,Docker上也会创建出一个能够访问到新Container的网卡,例如叫:veth2. 每new一个container,docker都会成对的创建2个可以互相访问到的网卡,一个分配在docker容器上,一个分配在新创建的Container上, 这就是veth-pair技术:创建一对能够互相访问的网卡,一个分配给新Container,一个分配给Docker Container.
这是Docker网络的默认bridge模式(桥接模式)
```

#### 3.2 精髓篇的相关Docker命令

#### 3.3 linux查看网卡

1. ip link show -- 查看网卡列表
2. ls /sys/class/net -- 查看网卡配置文件
3. ip a -- 查看当前环境所有的网卡详细信息

#### 3.4 网卡ip a命令解读详细信息参数

如下图,是Docker Container tomcat01

![image-20210709094159664](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210709094159664.png)

> 上图有2个网卡,名称分别是: lo, eth0@if27
>
> 网卡的状态是 state后面的字段, 网卡状态有:UNKNOWN, UP, DOWN等
>
> link/ether:MAC地址
>
> inet: 是当前网卡所绑定的IP地址
>
> 可以看到: lo绑定的ip是127.0.0.1(本地地址)  eth0@if27绑定的IP是 172.17.0.2

如下图是centos 宿主机的ip a

![image-20210709094759204](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210709094759204.png)

> 可以看到docker0网卡绑定IP地址为172.17.0.1, 说明 docker0和tomcat01的eth0网卡同属一个网段,可以互相ping通

宿主机的所有网卡的配置文件路径为 /sys/sysconfig/network-scripts/

#### 3.5 给网卡添加IP地址

1. 手动直接修改网卡所在路径的配置文件,文件名一般为ifcfg-*

2. 通过命令添加IP地址

   > 1, ip addr add 192.168.0.100/24 dev eth0
   >
   > ​    给eth0网卡,添加一个IP地址, IP地址为192.168.0.100, 子网掩码255.255.255.0
   >
   > ​    /24是指子网掩码的位数, 子网掩码总共有32个,写/24就表示是24个1,8个0. 可以表示成如下:
   >
   > ​    /24 = 11111111 11111111 11111111 00000000, 转换成十进制, 就是255.255.255.0
   >
   > 2, 删除IP地址
   >
   > ​    ip addr delete 192.168.0.100/24 dev eth0
   >
   > ​    给eth0网卡,  删除一个IP地址, IP为192.168.0.100, 子网掩码为255.255.255.0

![image-20210712100813942](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210712100813942.png)

3. 网卡启动与关闭

   > ifup/ifdown eth0
   >
   > ip link set eth0 up/down

4. 重启网卡

   > service network restart
   >
   > systemctl restart network

### 4. Network NameSpace研究

> namespace相关linux命令:
>
> 1. ip netns list, 查看当前网络命令空间列表
> 2. ip netns add ns1, 给当前网络添加一个命名空间,名为ns1
> 3. ip netns delete ns2, 删除namespace = ns2的命名空间
> 4. ip netns exec ns1 ip a, 进入名称为ns1的网卡命名空间,执行 ip a命令,查看ns1命名空间下的所有网卡信息
> 5. ip netns exec ns1 ifup 1lo(ip netns exec ns1 ip link set lo up), 启动ns1命名空间内的lo网卡

#### 4.1 Docker的Veth-Pair网卡技术研究

##### 4.1.1 自己搭建veth-pair网卡

1. 创建一对link, 使用veth-pair技术, link名称分别为veth-ns1, veth-ns2

   > ip link add veth-ns1 type veth peer name veth-ns2

2. 查看link情况

   > ip link

3. 创建2个namespace

   > ip netns add ns1
   >
   > ip netns add ns2

4. ip link 将2个link分别赋给不同的ns

   > ip link set veth-ns1 netns ns1
   >
   > ip link set veth-ns2 netns ns2

5. 给2个link指定IP

   > ip netns exec ns1 ip addr add 192.168.0.11/24 dev veth-ns1
   >
   > ip netns exec ns2 ip addr add 192.168.0.12/24 dev veth-ns2

6. 启动2个网卡

   > ip netns exec ns1 set veth-ns1 up
   >
   > ip netns exec ns2 set veth-ns2 up

7. exec进入2个网卡互相ping对方,查看结果

   > ip netns exec ns1 ip a 进入ns1,查看当前命名空间下的所有网卡
   >
   > ip netns exec ns2 ip a
   >
   > ip netns exec ns1 ping 192.168.0.12 进入ns1命名空间,并执行ping操作
   >
   > ip netns exec ns2 ping 192.168.0.11

#### 4.2 Docker Container的namespace

##### 4.2.1 创建自己的Bridge桥接网络

1. docker network ls 查看当前docker网络列表

2. docker network create my-bridge 当前Docker网络创建一个名称为my-bridge的桥接模式(不指定时,默认bridge模式)

3. docker run -d --name my-bridge-tomcat -p 9090:8080 --network my-bridge tomcat 

   > 1. 使用docker run命令创建一个容器
   > 2. -d后台运行
   > 3. --name指定容器名称为"my-bridge-tomcat"
   > 4. -p映射宿主机的9090端口对应容器的8080端口
   > 5. --network容器使用名称为my-bridge的网络
   > 6. 最后tomcat为容器创建所依赖的image镜像

### 5. Docker Volume

官方的数据持久化提供了2种主要的方式:

1. 使用named-volume
2. 使用bind mount

通过使用docker volume,完成数据的持久化

docker volume的一些常见命令:

```
-- 查看docker volume列表
docker volume ls
-- 查看指定volume的属性
docker inspect [volume-name]
docker volume inspect [volume-name]
```

#### 5.1 Docker volume官方提示

![image-20210723101350518](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210723101350518.png)

#### 5.2 mysql使用docker volume完成数据持久化

1. 拉取mysql的docker image
2. 使用docker run 并指定volume挂载

### 6.多容器如何通信?

当多个容器处于同一个网络(network)下时,各容器之间就能够互相通信

步骤:

- docker network create [network-name], 创建一个network,自定义名称
- docker run --network [network-name], docker run构建容器,并指定容器使用自定义网络



### 7. 常见问题解决

#### 7.1 Docker Mysql 连接问题

问题描述: 在VM中,创建2个docker container, 其中一个是mysql,另一个是application应用. 当application想要docker run 就连接mysql container的时候, 报错如下:

![image-20210724190128145](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210724190128145.png)

`Client does not support authentication protocol requested by server; consider upgrading MySQL client`:

当前mysql版本不支持明文账号密码连接, 此特性为8.0以后才新增的. 如果想要支持, 需要执行如下操作:

1. 进入mysql docker container内, docker exec -it mysql-container-id /bin/bash
2. 执行mysql语句,修改root账号权限`ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';`
3. 刷新mysql权限. `flush privileages;`
4. 如果以上步骤没有用, 重新执行step2 ,将`@'localhost'`去掉

##### Stackoverflow回答如下:

![image-20210724190558565](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210724190558565.png)

### 8. Docker Compose

在docker官方文档get-started中, docker-compose可以做到通过yml文件,完成一键启动或一键关闭(docker-compose up/down)

1. 使用该命令时,当前目录下必须要有docker-compose.yml文件,或者可以通过参数`-f`来指定docker-compose启动所需要的yml文件的文件名及文件路径
2. docker-compose.yml文件遵守一定的规范语法. 具体语法见官网文档, 或docker-compose --help, 或docker-compose -h
3. docker-compose会自动的使用volumes参数作为network的名称, 该命令即创建了volume, 也创建了network
4. docker-compose down时, 会删除掉创建的network, 但是会保留创建的volume, 以便下次docker-compose up使用
5. 如果想要在docker-compose down的时候, 同时也删除掉volume, 需要在命令后加上--volumes标记



### 9. 使用Docker给自己的Java应用打包部署

1. 在应用的root根目录下,创建一个文件,默认是Dockerfile
2. 编写Dockerfile, 第一行写`语法解析器指令` --> `# syntax=docker/dockerfile:1`