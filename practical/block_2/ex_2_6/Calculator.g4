grammar Calculator;
program :
    stat* EOF
    ;
stat :
    (expr | assignment)? NEWLINE
    ;
assignment: ID '=' expr;
expr :
    op=('+'|'-') expr               #ExprUnary
    | expr op=('*'|'/'|'%') expr    #ExprMultDivMod
    | expr op=('+'|'-') expr        #ExprAddSub
    | Integer                       #ExprInteger
    | '(' expr ')'                  #ExprParent
    | ID                            #ExprID
    ;

Integer : [0-9]+; // implement with long integers
NEWLINE: '\r' ? '\n';
ID: [a-zA-Z_]+;
WS: [ \t]+ -> skip;
COMMENT: '#' .*? '\n' -> skip ;