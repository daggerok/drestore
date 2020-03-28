# DREStore [![CI](https://github.com/daggerok/drestore/workflows/CI/badge.svg)](https://github.com/daggerok/drestore/actions?query=workflow%3ACI)
daggerok Reactor RSocket EventStore

Please ignore that creepy shit for now. Thanks!

![logo](logos/logo-009.png)

Status: IN PROGRESS /  INCUBATING

## TODO: Functionality
* Concepts
* Simple reactive data processing
* Plugable architecture
* File system as default storage
* Append Only
* Data consumption
* Automatic snapshotting
* Split event log per immutable data blocks
* Metadata and binary search for time travel
* Interfaces

```
1. Event Scheduler
------------------
-> incomming event ->
   -> ${seqNum}-${eventType}-${aggId} file created ->
# processing sheduled

2. Event Processor
------------------
-> data from ${seqNum}-${event.eventType}-${event.aggId} file appended to log ->
   -> ${seqNum}-${eventType}-${aggId} file removed ->
      -> handle stored ${event} ->

3. Event Handler
----------------
-> find all subscribers per ${event.eventType} ->
   -> notify subscribers about event  ->
```

## Resources

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/gradle-plugin/reference/html/)
* [Coroutines section of the Spring Framework Documentation](https://docs.spring.io/spring/docs/5.2.5.RELEASE/spring-framework-reference/languages.html#coroutines)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
