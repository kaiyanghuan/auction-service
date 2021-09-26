# Getting Started

## Prerequisites
At this point you should have the following items installed:
1. Git client
    ```shell
    # Execute this command in your command line
    $ git --version
    git version 2.23.0
    ```
   Note: Your output may differ

2. Maven
    ```shell
    # Execute this command in your command line
    $ mvn -version
    Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
    Maven home: /Applications/apache-maven-3.6.3
    Java version: 11.0.8, vendor: GraalVM Community, runtime: /Applications/graalvm-ce-java11-20.2.0/Contents/Home
    Default locale: en_SG, platform encoding: UTF-8
    OS name: "mac os x", version: "10.16", arch: "x86_64", family: "mac"
    ```
   Note: Your output may differ

3. JDK 11+
 
    ```shell
    # Execute this command in your command line
    $ java -version
    openjdk 11.0.8 2020-07-14
    OpenJDK Runtime Environment GraalVM CE 20.2.0 (build 11.0.8+10-jvmci-20.2-b03)
    OpenJDK 64-Bit Server VM GraalVM CE 20.2.0 (build 11.0.8+10-jvmci-20.2-b03, mixed mode, sharing)
    ```
   Note: Your output may differ

## Cloning the project

```shell
$ git clone https://github.com/kaiyanghuan/auction-service.git

# view the branches of this repository
$ git branch --list --all
* master
springboot-kotlin/lesson1
remotes/origin/HEAD -> origin/main
remotes/origin/features/accounts
... <cut for brevity>
remotes/origin/main
remotes/origin/master
remotes/origin/springboot-kotlin/lesson1

# Switching to lesson1 branch
$ git branch springboot-kotlin/lesson1
Switched to branch 'springboot-kotlin/lesson1'
Your branch is up to date with 'origin/springboot-kotlin/lesson1'.

# View the content of the branch
$ ls -ltrh
total 64
-rw-r--r--  1 staff  staff   9.8K Sep 26 11:52 mvnw
-rw-r--r--  1 staff  staff   6.5K Sep 26 11:52 mvnw.cmd
drwxr-xr-x  4 staff  staff   128B Sep 26 11:52 src
-rw-r--r--  1 staff  staff   4.7K Sep 26 12:40 pom.xml
drwxr-xr-x  5 staff  staff   160B Sep 26 12:40 sql
```

## Compiling for the first time

```shell
$ mvn clean package -DskipTests
[INFO] Scanning for projects...
[INFO]
[INFO] ----------------------< com.ocbc:auction-service >----------------------
[INFO] Building auction-service 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
… <cut for brevity>
[INFO]
[INFO] --- spring-boot-maven-plugin:2.5.2:repackage (repackage) @ auction-service ---
[INFO] Replacing main artifact with repackaged archive
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  01:05 min
[INFO] Finished at: 2021-09-26T12:49:58+08:00
[INFO] ------------------------------------------------------------------------
```