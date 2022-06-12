Create a simple calculator that takes a String and returns a integer

Signature (pseudo code):

int Add(string numbers)
Requirements
1. The method can take up to two numbers, separated by commas, and will return
their sum as a result. So the inputs can be: “”, “1”, “1,2”. For an empty string, it will return 0.

Future:
 1. Can have any number of numbers comma separated.
 2. User can provide custom separator as well

Test cases:
1. Input "" should return 0
2. Input "2,3" should return 5
3. Input "2" should return 2
4. Input "2,-2" or "2, a" should throw Invalid argument exception
6. Input "2\n2" should return 4
7. Input "2,2," should throw invalid argument exception