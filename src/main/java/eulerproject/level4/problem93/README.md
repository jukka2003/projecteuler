
### Problem 93

#### Description
By using each of the digits from the set, {1, 2, 3, 4}, exactly once, and making use of the four arithmetic operations 
(+, −, *, /) and brackets/parentheses, it is possible to form different positive integer targets.

For example,

8 = (4 * (1 + 3)) / 2
14 = 4 * (3 + 1 / 2)
19 = 4 * (2 + 3) − 1
36 = 3 * 4 * (2 + 1)

Note that concatenations of the digits, like 12 + 34, are not allowed.

Using the set, {1, 2, 3, 4}, it is possible to obtain thirty-one different target numbers of which 36 is the maximum, 
and each of the numbers 1 to 28 can be obtained before encountering the first non-expressible number.

Find the set of four distinct digits, a < b < c < d, for which the longest set of consecutive positive integers, 1 to n, 
can be obtained, giving your answer as a string: abcd.

#### Solution

##### Ideas
* what if we use RPN to generate equations? **DONE**
* how to generate non random RPN systematically? what is the condition to get proper equation? **DONE**
    * Using four digits
    * the number of operators must be 3
    * order (left right) all together 7 tokens:
        * first digit
        * second digit
        * last operator
        
> Examples (D - digit, O - operator)

> DDDOODO

> DDDODOO

> DDODODO

> DDODDOO

> DDDDOOO

* using operators and abcd digits we can generate RPN equations **DONE**
* RPN calculator needs to be implemented **DONE**
* checking of consecutive numbers
    * need to generate all sets of 4 of 10 digits by implementing combinations **DONE**

##### Algorithm
1. generate RPN from a b c d and operators (say random) - using letters
2. replace the letters to get all values from RPN
3. calculate it and store result for abcd vector go to 
4. check map of results to obtain longest chain abcd vector

##### Links
[Generating binary with k-bits set](https://stackoverflow.com/questions/1851134/generate-all-binary-strings-of-length-n-with-k-bits-set)