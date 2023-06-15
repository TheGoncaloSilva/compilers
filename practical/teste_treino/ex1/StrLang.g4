grammar StrLang;
program: stat* EOF;
stat: 
      print 
    | attr
    ;

print: 
      'print' (ID | String)
    ;

attr: 
      ID ':' String         # regularAttr
    | ID ':' 'input(' String ')' # inputAttr
    ;

ID: [a-zA-Z_]+[a-zA-Z_0-9]*;
String: ('"' ( '""' | ~["])* '"');
COMMENT: '//' .*? '\n' -> skip;
WS: [ \t\r\n]+ -> skip;
