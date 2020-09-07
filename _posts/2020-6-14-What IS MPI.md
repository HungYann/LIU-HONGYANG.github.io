---
layout: post
author: LIU,HONGYANG
tags: [其它]
---



**一、MPI message passing interface**

A specification for the developers and users of message passing libraries.

By itself, it is an interface not a library.

 

Commonly used for distributed memory system & high-performance computing

 

**Goal:**

Portable: Run on different machines or platforms

Scalable:Run on million of compute nodes

Flexible:isolate MPI developers from MPI programme(users)

 

 

**Programming Model**

 

SPMD:Single Program Multiple Data

Allow tasks to branch or conditionally execute only parts of the program they are designed to execute

MPI tasks of a parallel program can not be dynamatically spawned during run time.

**Distributed memory**

MPI provide method of sending &receiving message

 

**二、Communication Methods**

 

From the view of **the pair of** communicated processes

From the view of **individua**l function call

 

**Communicators and Groups**

 

Groups define which collection of processes may communicate with each other

Each group is associated with a communicator to perform its communication function calls

MPI_COMM_WORLD is the pre-defined communicator for all processors

 

**MPI_INIT():**

- Initializes the MPI execution environment
- Must be called before any other MPI functions
- Must be called only once in an MPI program

 

**MPI_FINALIZE():**

- Terminates the MPI execution environment
- No other MPI routines may be called after it

 