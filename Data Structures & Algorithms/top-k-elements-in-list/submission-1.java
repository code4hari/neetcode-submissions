class Solution {
    public
 
int
[
]
 
topKFrequent
(
int
[
]
 nums
,
 
int
 k
)
 
{

        
// Edge case: if k equals number of elements, return all distinct values

        
if
 
(
nums 
==
 
null
 
||
 nums
.
length 
==
 
0
)
 
return
 
new
 
int
[
0
]
;

        
// Step 1: Count frequency of each element

        
Map
<
Integer
,
 
Integer
>
 freqMap 
=
 
new
 
HashMap
<
>
(
)
;

        
for
 
(
int
 num 
:
 nums
)
 
{

            freqMap
.
put
(
num
,
 freqMap
.
getOrDefault
(
num
,
 
0
)
 
+
 
1
)
;

        
}

        
// Step 2: Create buckets where index = frequency, value = list of nums

        
// Max possible frequency is nums.length, so we need nums.length + 1 buckets

        
List
<
Integer
>
[
]
 buckets 
=
 
new
 
List
[
nums
.
length 
+
 
1
]
;

        
for
 
(
Map
.
Entry
<
Integer
,
 
Integer
>
 entry 
:
 freqMap
.
entrySet
(
)
)
 
{

            
int
 freq 
=
 entry
.
getValue
(
)
;

            
if
 
(
buckets
[
freq
]
 
==
 
null
)
 
{

                buckets
[
freq
]
 
=
 
new
 
ArrayList
<
>
(
)
;

            
}

            buckets
[
freq
]
.
add
(
entry
.
getKey
(
)
)
;

        
}

        
// Step 3: Collect top k elements from highest frequency bucket downward

        
int
[
]
 result 
=
 
new
 
int
[
k
]
;

        
int
 idx 
=
 
0
;

        
for
 
(
int
 i 
=
 buckets
.
length 
-
 
1
;
 i 
>=
 
0
 
&&
 idx 
<
 k
;
 i
--
)
 
{

            
if
 
(
buckets
[
i
]
 
!=
 
null
)
 
{

                
for
 
(
int
 num 
:
 buckets
[
i
]
)
 
{

                    
if
 
(
idx 
==
 k
)
 
break
;

                    result
[
idx
++
]
 
=
 num
;

                
}

            
}

        
}

        
return
 result
;
}
}