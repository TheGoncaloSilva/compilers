grammar UseGrammar;
program :
    stat* EOF
    ;
stat :
    (declaration | prefix)? ';'
    ;
declaration: 
    STRING STRING STRING '[' STRING ']'              #DeclSameName
    | STRING STRING STRING '[' STRING ',' STRING ']' #DeclSeparated;

prefix:
    STRING STRING ID '=' INTEGER;

STRING  : '"' .*? '"';
ID: [a-zA-Z_]+;
INTEGER : [0-9]+; // implement with long integers
NEWLINE: '\r' ? '\n' -> skip;
TEXT: [a-zA-Z_]+;
WS: [ \t]+ -> skip;
COMMENT: '#' .*? '\n' -> skip ;