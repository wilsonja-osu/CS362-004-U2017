/*
* Name: Jacob Wilson
* Class: CS362_400 Summer 17
* Assignment: Quiz 2
* Description: Implement the inputChar() and inputString()
*  functions so that an error will result in testme().
*/

#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<time.h>

/*
* Name: inputChar()
* Input: None
* Output: one char
* Description: The function chooses a random character from the
*  printable ASCII range and returns it.
*/
char inputChar()
{
    // return a valid printable ASCII character (32-126)
    char randChar = (' ' + (rand() % (127 - 32)));
    return randChar;
}

/*
* Name: inputString()
* Input: None
* Output: one string
* Description: The function creates a random five-character string
*  using the lower-case ASCII characters, appends a NULL character
*  to the end, and returns the string.
*/
char *inputString()
{
  // create a six char string (5 characters + 1 NULL)
	int strLen = 6;
	char *randString = malloc(strLen);
	int i;

  // assign a random lower-case letter to the string
	for (i = 0; i < strLen - 1; i++) {
		randString[i] = ('a' + (rand() % 26));
	}

	// append NULL char to end of string
	randString[5] = '\0';

  return randString;
}

void testme()
{
  int tcCount = 0;
  char *s;
  char c;
  int state = 0;
  while (1)
  {
    tcCount++;
    c = inputChar();
    s = inputString();
    printf("Iteration %d: c = %c, s = %s, state = %d\n", tcCount, c, s, state);

    if (c == '[' && state == 0) state = 1;
    if (c == '(' && state == 1) state = 2;
    if (c == '{' && state == 2) state = 3;
    if (c == ' '&& state == 3) state = 4;
    if (c == 'a' && state == 4) state = 5;
    if (c == 'x' && state == 5) state = 6;
    if (c == '}' && state == 6) state = 7;
    if (c == ')' && state == 7) state = 8;
    if (c == ']' && state == 8) state = 9;
    if (s[0] == 'r' && s[1] == 'e'
       && s[2] == 's' && s[3] == 'e'
       && s[4] == 't' && s[5] == '\0'
       && state == 9)
    {
      printf("error ");
      exit(200);
    }

    // added to free the malloc'd string
    free(s);
  }
}


int main(int argc, char *argv[])
{
    srand(time(NULL));
    testme();
    return 0;
}