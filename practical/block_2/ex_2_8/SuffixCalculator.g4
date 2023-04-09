grammar SuffixCalculator;
program :
    stat* EOF
    ;
stat :
    (expr | assignment)? NEWLINE
    ;
assignment: ID '=' expr;
expr :
    expr op=('!+'|'!-')             #ExprUnary
    | expr expr op=('*'|'/'|'%')    #ExprMultDivMod
    | expr expr op=('+'|'-')        #ExprAddSub
    | Integer                       #ExprInteger
    | '(' expr ')'                  #ExprParent
    | ID                            #ExprID
    ;

Integer : [0-9]+; // implement with long integers
NEWLINE: '\r' ? '\n';
ID: [a-zA-Z_]+;
WS: [ \t]+ -> skip;
COMMENT: '#' .*? '\n' -> skip ;