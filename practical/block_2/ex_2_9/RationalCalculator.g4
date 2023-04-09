grammar RationalCalculator;
program :
    stat* EOF
    ;
stat :
    (print | assignment)? NEWLINE
    ;
assignment: expr '->' ID ';';
print: 'print' expr ';';
expr :
    expr op=('+'|'-')               #ExprUnary
    | expr expr op=('*'|':')        #ExprMultDiv
    | expr expr op=('+'|'-')        #ExprAddSub
    | Integer '/' Integer           #ExprFrac
    | Integer                       #ExprInteger
    | '(' expr ')'                  #ExprParent
    | 'reduce' expr                 #ExprReduce
    | ID                            #ExprID
    ;

Integer : [0-9]+; // implement with long integers
NEWLINE: '\r' ? '\n';
ID: [a-zA-Z_]+;
WS: [ \t]+ -> skip;
COMMENT: '#' .*? '\n' -> skip ;