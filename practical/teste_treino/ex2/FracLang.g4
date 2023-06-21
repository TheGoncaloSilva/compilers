grammar FracLang;
program: 
    stat* EOF
    ;

stat: 
    assign ';'
    | display ';'
    ;

assign:
    ID '<=' expr
    ;

display:
    'display' expr
    ;

expr:
    'read' STRING                   # exprRead
    | 'reduce' expr                 # exprReduce
    | op=('+'|'-') expr             # exprUnary
    | expr op=('*'|':') expr        # exprMulDiv
    | expr op=('+'|'-') expr        # exprAddSub
    | '(' expr ')'                  # exprParentesis
    | ID                            # exprId
    | fraction                      # exprFrac
    ;   


fraction: 
    NUMBER '/' NUMBER               # regularFrac
    | NUMBER                        # singleFrac
    ;

ID: [a-z]+[a-z]*;
STRING: ('"' ('""' | ~["])* '"');
NUMBER: [0-9]+[0-9]*;
COMMENT: '--' .*? '\n' -> skip;
WS: [ \t\r\n]+ -> skip;