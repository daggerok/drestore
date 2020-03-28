# DREStore [![CI](https://github.com/daggerok/drestore/workflows/CI/badge.svg)](https://github.com/daggerok/drestore/actions?query=workflow%3ACI)
daggerok Reactor RSocket EventStore

Please ignore that creepy shit for now. Thanks!

![logo](logos/logo-009.png)

Status: IN PROGRESS /  INCUBATING

## TODO


```
1. Event Scheduler
------------------
-> incomming event
   -> commands/${cmdSeq}-${eventMetadata} file with event metadata body created
# valid command sent / handling and event

2. Event Processor
------------------
-> event data from commands/${cmdSeq}-${eventMetadata} file read and event appended to event-log
   -> events/${evtSeq}-${eventType}-${eventAggId} file with event body created
      -> commands/${cmdSeq}-${eventMetadata} file removed
# event persisted

3. Event Handler
----------------
-> events/${evtSeq}-${eventType}-${eventAggId} file read with event metadata
   -> emit event if target subscribers found or create notify/... file
      -> events/${evtSeq}-${eventType}-${eventAggId} file removed
# subscribers notified
```

## Connectors

* rsocket / message-broker flux message emitted / consumed
* websocket message sent / received
* rest api called / sse event received 
* file created, written / read, removed

## Functionality
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

## Resources

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/gradle-plugin/reference/html/)
* [Coroutines section of the Spring Framework Documentation](https://docs.spring.io/spring/docs/5.2.5.RELEASE/spring-framework-reference/languages.html#coroutines)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
