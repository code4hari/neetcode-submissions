/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public
 
class
 
Solution
 
{

    
public
 
ListNode
 
mergeKLists
(
ListNode
[
]
 lists
)
 
{

        
// Edge case: no lists at all

        
if
 
(
lists 
==
 
null
 
||
 lists
.
length 
==
 
0
)
 
return
 
null
;

        
// Min-heap ordered by node value

        
PriorityQueue
<
ListNode
>
 minHeap 
=
 
new
 
PriorityQueue
<
>
(

            
(
a
,
 b
)
 
->
 
Integer
.
compare
(
a
.
val
,
 b
.
val
)

        
)
;

        
// Seed heap with the head of each non-null list

        
for
 
(
ListNode
 head 
:
 lists
)
 
{

            
if
 
(
head 
!=
 
null
)
 
{

                minHeap
.
offer
(
head
)
;

            
}

        
}

        
// Dummy node simplifies edge handling for the result list

        
ListNode
 dummy 
=
 
new
 
ListNode
(
0
)
;

        
ListNode
 tail 
=
 dummy
;

        
// Repeatedly extract the smallest, append, and push its successor

        
while
 
(
!
minHeap
.
isEmpty
(
)
)
 
{

            
ListNode
 smallest 
=
 minHeap
.
poll
(
)
;

            tail
.
next 
=
 smallest
;

            tail 
=
 smallest
;

            
if
 
(
smallest
.
next 
!=
 
null
)
 
{

                minHeap
.
offer
(
smallest
.
next
)
;

            
}

        
}

        
// Disconnect tail just in case (already null from input lists)

        tail
.
next 
=
 
null
;

        
return
 dummy
.
next
;

    
}
}