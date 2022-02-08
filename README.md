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
