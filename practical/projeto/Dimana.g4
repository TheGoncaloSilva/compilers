grammar Dimana;
program :
    (stat';')* EOF
    ;
stat        : 
    assignement
    | print
    | read
    | declaration
    | expression
	| import
    | 
    ;
import      : 'use' FILE_NAME;
declaration : Type Type ';';
assignment  :
print   :   
    'write' String (','String)* ';'
    | 'writeln'
    ;
read    :
expression: 
      cast
    |
    | call
    | '(' expression ')'


cast: 'string(' String ',' Integer ')'

//call: VAR '(' (expression ',')* (expression)? ')';
    


String  : '"' .*? '"';
Integer : [0-9]+;               // implement with long integers
Type    : [a-zA-z_]+;
//PRIMITIVE_TYPE: 'string' | 'real' | 'integer' | 'list';
Double  : Integer '.' Integer;   
NEWLINE : '\r' ? '\n' -> skip;
VAR      : [a-zA-Z_]+;
COMMENT : '#' .*? '\n' -> skip;
FILE_NAME: .*?';';
WS      : [\t \n] -> skip;