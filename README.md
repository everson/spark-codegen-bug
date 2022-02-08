# spark-codegen-bug

Demonstrates a regression that affects spark projects in scala 2.12.13 to 2.12.15 and at least scala 2.13.7

This compiles
```
 sbt +Test/compile
```
This works for scala 2.12.12 and fails on scala 2.12.13+ 
```
 sbt +test
```

Note that I've tested this with both ScalaTest and Specs2. Also tested with Spark 3.2.0 and problem still persists.

In a more complex project, the error looks like:

```
[error]    Success(SparkFailures(NonEmpty[Unknown(org.apache.spark.SparkException: Job aborted due to stage failure: Task 1 in stage 2.0 failed 1 times, most recent failure: Lost task 1.0 in stage 2.0 (TID 3) (192.168.0.80 executor driver): java.util.concurrent.ExecutionException: org.codehaus.commons.compiler.CompileException: File 'generated.java', Line 63, Column 8: failed to compile: org.codehaus.commons.compiler.CompileException: File 'generated.java', Line 63, Column 8: Private member cannot be accessed from type "org.apache.spark.sql.catalyst.expressions.GeneratedClass$SpecificSafeProjection".
[error]    	at org.sparkproject.guava.util.concurrent.AbstractFuture$Sync.getValue(AbstractFuture.java:306)
[error]    	at org.sparkproject.guava.util.concurrent.AbstractFuture$Sync.get(AbstractFuture.java:293)
[error]    	at org.sparkproject.guava.util.concurrent.AbstractFuture.get(AbstractFuture.java:116)
[error]    	at org.sparkproject.guava.util.concurrent.Uninterruptibles.getUninterruptibly(Uninterruptibles.java:135)
[error]    	at org.sparkproject.guava.cache.LocalCache$LoadingValueReference.waitForValue(LocalCache.java:3620)
[error]    	at org.sparkproject.guava.cache.LocalCache$Segment.waitForLoadingValue(LocalCache.java:2362)
[error]    	at org.sparkproject.guava.cache.LocalCache$Segment.lockedGetOrLoad(LocalCache.java:2349)
[error]    	at org.sparkproject.guava.cache.LocalCache$Segment.get(LocalCache.java:2257)
[error]    	at org.sparkproject.guava.cache.LocalCache.get(LocalCache.java:4000)
[error]    	at org.sparkproject.guava.cache.LocalCache.getOrLoad(LocalCache.java:4004)
[error]    	at org.sparkproject.guava.cache.LocalCache$LocalLoadingCache.get(LocalCache.java:4874)
[error]    	at org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator$.compile(CodeGenerator.scala:1351)
[error]    	at org.apache.spark.sql.catalyst.expressions.codegen.GenerateSafeProjection$.create(GenerateSafeProjection.scala:205)
[error]    	at org.apache.spark.sql.catalyst.expressions.codegen.GenerateSafeProjection$.create(GenerateSafeProjection.scala:39)
[error]    	at org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator.generate(CodeGenerator.scala:1277)
[error]    	at org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator.generate(CodeGenerator.scala:1274)
[error]    	at org.apache.spark.sql.execution.ObjectOperator$.deserializeRowToObject(objects.scala:147)
[error]    	at org.apache.spark.sql.execution.AppendColumnsExec.$anonfun$doExecute$12(objects.scala:326)
[error]    	at org.apache.spark.rdd.RDD.$anonfun$mapPartitionsInternal$2(RDD.scala:898)
[error]    	at org.apache.spark.rdd.RDD.$anonfun$mapPartitionsInternal$2$adapted(RDD.scala:898)
[error]    	at org.apache.spark.rdd.MapPartitionsRDD.compute(MapPartitionsRDD.scala:52)
[error]    	at org.apache.spark.rdd.RDD.computeOrReadCheckpoint(RDD.scala:373)
[error]    	at org.apache.spark.rdd.RDD.iterator(RDD.scala:337)
[error]    	at org.apache.spark.rdd.MapPartitionsRDD.compute(MapPartitionsRDD.scala:52)
[error]    	at org.apache.spark.rdd.RDD.computeOrReadCheckpoint(RDD.scala:373)
[error]    	at org.apache.spark.rdd.RDD.iterator(RDD.scala:337)
[error]    	at org.apache.spark.rdd.MapPartitionsRDD.compute(MapPartitionsRDD.scala:52)
[error]    	at org.apache.spark.rdd.RDD.computeOrReadCheckpoint(RDD.scala:373)
[error]    	at org.apache.spark.rdd.RDD.iterator(RDD.scala:337)
[error]    	at org.apache.spark.shuffle.ShuffleWriteProcessor.write(ShuffleWriteProcessor.scala:59)
[error]    	at org.apache.spark.scheduler.ShuffleMapTask.runTask(ShuffleMapTask.scala:99)
[error]    	at org.apache.spark.scheduler.ShuffleMapTask.runTask(ShuffleMapTask.scala:52)
[error]    	at org.apache.spark.scheduler.Task.run(Task.scala:131)
[error]    	at org.apache.spark.executor.Executor$TaskRunner.$anonfun$run$3(Executor.scala:497)
[error]    	at org.apache.spark.util.Utils$.tryWithSafeFinally(Utils.scala:1439)
[error]    	at org.apache.spark.executor.Executor$TaskRunner.run(Executor.scala:500)
[error]    	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
[error]    	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
[error]    	at java.lang.Thread.run(Thread.java:748)
[error]    Caused by: org.codehaus.commons.compiler.CompileException: File 'generated.java', Line 63, Column 8: failed to compile: org.codehaus.commons.compiler.CompileException: File 'generated.java', Line 63, Column 8: Private member cannot be accessed from type "org.apache.spark.sql.catalyst.expressions.GeneratedClass$SpecificSafeProjection".
[error]    	at org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator$.org$apache$spark$sql$catalyst$expressions$codegen$CodeGenerator$$doCompile(CodeGenerator.scala:1415)
[error]    	at org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator$$anon$1.load(CodeGenerator.scala:1500)
[error]    	at org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator$$anon$1.load(CodeGenerator.scala:1497)
[error]    	at org.sparkproject.guava.cache.LocalCache$LoadingValueReference.loadFuture(LocalCache.java:3599)
[error]    	at org.sparkproject.guava.cache.LocalCache$Segment.loadSync(LocalCache.java:2379)
[error]    	at org.sparkproject.guava.cache.LocalCache$Segment.lockedGetOrLoad(LocalCache.java:2342)
[error]    	... 32 more
[error]
[error]    Driver stacktrace:)])) is a Success but SparkFailures(NonEmpty[Unknown(org.apache.spark.SparkException: Job aborted due to stage failure: Task 1 in stage 2.0 failed 1 times, most recent failure: Lost task 1.0 in stage 2.0 (TID 3) (192.168.0.80 executor driver): java.util.concurrent.ExecutionException: org.codehaus.commons.compiler.CompileException: File 'generated.java', Line 63, Column 8: failed to compile: org.codehaus.commons.compiler.CompileException: File 'generated.java', Line 63, Column 8: Private member cannot be accessed from type "org.apache.spark.sql.catalyst.expressions.GeneratedClass$SpecificSafeProjection".
[error]    	at org.sparkproject.guava.util.concurrent.AbstractFuture$Sync.getValue(AbstractFuture.java:306)
[error]    	at org.sparkproject.guava.util.concurrent.AbstractFuture$Sync.get(AbstractFuture.java:293)
[error]    	at org.sparkproject.guava.util.concurrent.AbstractFuture.get(AbstractFuture.java:116)
[error]    	at org.sparkproject.guava.util.concurrent.Uninterruptibles.getUninterruptibly(Uninterruptibles.java:135)
[error]    	at org.sparkproject.guava.cache.LocalCache$LoadingValueReference.waitForValue(LocalCache.java:3620)
[error]    	at org.sparkproject.guava.cache.LocalCache$Segment.waitForLoadingValue(LocalCache.java:2362)
[error]    	at org.sparkproject.guava.cache.LocalCache$Segment.lockedGetOrLoad(LocalCache.java:2349)
[error]    	at org.sparkproject.guava.cache.LocalCache$Segment.get(LocalCache.java:2257)
[error]    	at org.sparkproject.guava.cache.LocalCache.get(LocalCache.java:4000)
[error]    	at org.sparkproject.guava.cache.LocalCache.getOrLoad(LocalCache.java:4004)
[error]    	at org.sparkproject.guava.cache.LocalCache$LocalLoadingCache.get(LocalCache.java:4874)
[error]    	at org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator$.compile(CodeGenerator.scala:1351)
[error]    	at org.apache.spark.sql.catalyst.expressions.codegen.GenerateSafeProjection$.create(GenerateSafeProjection.scala:205)
[error]    	at org.apache.spark.sql.catalyst.expressions.codegen.GenerateSafeProjection$.create(GenerateSafeProjection.scala:39)
[error]    	at org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator.generate(CodeGenerator.scala:1277)
[error]    	at org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator.generate(CodeGenerator.scala:1274)
[error]    	at org.apache.spark.sql.execution.ObjectOperator$.deserializeRowToObject(objects.scala:147)
[error]    	at org.apache.spark.sql.execution.AppendColumnsExec.$anonfun$doExecute$12(objects.scala:326)
[error]    	at org.apache.spark.rdd.RDD.$anonfun$mapPartitionsInternal$2(RDD.scala:898)
[error]    	at org.apache.spark.rdd.RDD.$anonfun$mapPartitionsInternal$2$adapted(RDD.scala:898)
[error]    	at org.apache.spark.rdd.MapPartitionsRDD.compute(MapPartitionsRDD.scala:52)
[error]    	at org.apache.spark.rdd.RDD.computeOrReadCheckpoint(RDD.scala:373)
[error]    	at org.apache.spark.rdd.RDD.iterator(RDD.scala:337)
[error]    	at org.apache.spark.rdd.MapPartitionsRDD.compute(MapPartitionsRDD.scala:52)
[error]    	at org.apache.spark.rdd.RDD.computeOrReadCheckpoint(RDD.scala:373)
[error]    	at org.apache.spark.rdd.RDD.iterator(RDD.scala:337)
[error]    	at org.apache.spark.rdd.MapPartitionsRDD.compute(MapPartitionsRDD.scala:52)
[error]    	at org.apache.spark.rdd.RDD.computeOrReadCheckpoint(RDD.scala:373)
[error]    	at org.apache.spark.rdd.RDD.iterator(RDD.scala:337)
[error]    	at org.apache.spark.shuffle.ShuffleWriteProcessor.write(ShuffleWriteProcessor.scala:59)
[error]    	at org.apache.spark.scheduler.ShuffleMapTask.runTask(ShuffleMapTask.scala:99)
[error]    	at org.apache.spark.scheduler.ShuffleMapTask.runTask(ShuffleMapTask.scala:52)
[error]    	at org.apache.spark.scheduler.Task.run(Task.scala:131)
[error]    	at org.apache.spark.executor.Executor$TaskRunner.$anonfun$run$3(Executor.scala:497)
[error]    	at org.apache.spark.util.Utils$.tryWithSafeFinally(Utils.scala:1439)
[error]    	at org.apache.spark.executor.Executor$TaskRunner.run(Executor.scala:500)
[error]    	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
[error]    	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
[error]    	at java.lang.Thread.run(Thread.java:748)
[error]    Caused by: org.codehaus.commons.compiler.CompileException: File 'generated.java', Line 63, Column 8: failed to compile: org.codehaus.commons.compiler.CompileException: File 'generated.java', Line 63, Column 8: Private member cannot be accessed from type "org.apache.spark.sql.catalyst.expressions.GeneratedClass$SpecificSafeProjection".
[error]    	at org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator$.org$apache$spark$sql$catalyst$expressions$codegen$CodeGenerator$$doCompile(CodeGenerator.scala:1415)
[error]    	at org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator$$anon$1.load(CodeGenerator.scala:1500)
[error]    	at org.apache.spark.sql.catalyst.expressions.codegen.CodeGenerator$$anon$1.load(CodeGenerator.scala:1497)
[error]    	at org.sparkproject.guava.cache.LocalCache$LoadingValueReference.loadFuture(LocalCache.java:3599)
[error]    	at org.sparkproject.guava.cache.LocalCache$Segment.loadSync(LocalCache.java:2379)
[error]    	at org.sparkproject.guava.cache.LocalCache$Segment.lockedGetOrLoad(LocalCache.java:2342)
[error]    	... 32 more
[error]
[error]    Driver stacktrace:)]) != Success (Expectations.scala:57)
```
