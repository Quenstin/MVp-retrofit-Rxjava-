# MVp-retrofit-Rxjava
组件化

具体介绍一下组件化 适合比较大型的项目 因为项目本身就大,然后经过N次更新迭代以后,导致项目更臃肿,最常见的是编译一次需要耗费大量时间,通过组件化可以把项目按照具体业务或者功能模块来划分,组件之间通讯可以使用阿里的Arouter,基本思想  APP文件作为项目的壳 里面不包含任何逻辑代码只有一个全局的application;然后按照功能或者业务划分为几个module 如:首页module 个人中心module等等,当然还要有一个baseModule,baseModule中是项目中用到的所有的第三方库,工具类,自定义view等,还有项目用引用的lib包也会被引用到baseModul中;而所有的module都会依赖basemodule;app会依赖所有的module,这样通过一系列的配置每个module都能当做一个独立的app来运行,这样方便的测试 也提高了项目的编译速度.
-------详细的步骤我会在简书中具体介绍,包括组件化需要注意的点,这个git上的项目我也会重新写的更详细点,这个太简陋了
-----(https://www.jianshu.com/u/e571392e8679)
