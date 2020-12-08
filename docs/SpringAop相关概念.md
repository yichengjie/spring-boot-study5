1. Joinpoint：在系统运行之前，Aop的功能模块都需要织入到oop的功能模块中。所以，要进行这种织入过程，
   我们需要知道在系统的哪些执行点上进行织入操作，这些将要在其之上进行织入操作的系统执行点就称为Joinpoint。
2. Pointcut概念代表的是Joinpoint的表述方式。将横切逻辑织入到当前系统的过程中，
   需要参照Pointcut规定的Joinpoint信息，才可以知道应该往系统的哪些Joinpoint上织入横切逻辑。
3. Advice是单一横切关注点逻辑的载体，它代表将会织入到Joinpoint的横切逻辑。如果将Aspect比着oop中的Class，
   那么Advice就相当于Class中的Method。
4. Aspect是对系统中的横切关注点逻辑进行模块化封装的AOP概念的实体。通常情况下，Aspect可以包含多个Pointcut以及相关Advice定义。
5. 织入过程是‘飞架’Aop和oop的那座桥，只有经过织入过程后，以Aspect模块化的横切关注点才会即成到oop的现存系统中。
   完成织入过程的那个‘人’就称为织入器（Weaver）。
   AspactJ有专门的编译器来完成织入操作，即ajc，所以acj就是AspectJ完成织入的织入器；
   Jboos Aop采用自定义类加载器来完成织入，那么这个自定义的类加载器就是它的织入器；
   Spring Aop使用一组类来完成最终的织入操作，ProxyFactory类则是Spring Aop中最通用的织入器。
6. 目标对象：符合Poincut所指定的条件，将在织入过程中被织入横切逻辑的对象，称为目标对象。