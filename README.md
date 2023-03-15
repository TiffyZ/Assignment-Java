# Assignment-Java
Advanced training assignments

* **Day 1**: 


| **Problem**                                                                                     | **Solution**                               |
|-------------------------------------------------------------------------------------------------|--------------------------------------------|
| Create an implementation for the SongCache interface <br/>shown below, being mindful of concurrency. | Concurrent HashMap + PriorityQueue to sort | 
* Notes:
  * private final variable 
  * get() and put() cannot be used together because that's not thread safe 
  * thread pool 
  * stream API