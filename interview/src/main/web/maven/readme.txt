maven依赖jar包冲突的解决方法
1.启动参数加上-verbose:class,项目启动的时候会把所有jar都打印出来
2.通过maven自带的工具  mvn:dependency:tree
    mvn dependency:tree -Dverbose -Dincludes=commons-logging:commons-loggging
    这条命令可以打印出所有依赖了groupId和artifactId都为commons-logging的jar包的依赖路径。
3.通过idea工具在Pom.xml文件上右击 选择 Diagrams -> Show Dependencies 即可查看Pom的依赖图