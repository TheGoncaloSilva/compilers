grammar StrLang;
program: stat* EOF;
stat: 
    attr 
    | print
    ;

attr: 
    ID ':' expr                     # regularAttr
    | ID ':' 'input(' String ')'    # inputAttr
    ;

print: 
    'print' expr
    ;

expr:
    ID                              # exprID
    | String                          # exprStr
    ;

ID: [a-zA-Z_]+[a-zA-Z_0-9]*;
String: ('"' ( '""' | ~["])* '"');
COMMENT: '//' .*? '\n' -> skip;
WS: [ \t\r\n]+ -> skip;
