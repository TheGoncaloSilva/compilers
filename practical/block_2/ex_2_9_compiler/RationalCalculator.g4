grammar RationalCalculator;
program :
    stat* EOF
    ;
stat :
    (print | assignment)? ';'
    ;
assignment: expr '->' ID;
print: 'print' expr;
expr :
    op=('+'|'-') expr                   #ExprUnary
    | <assoc=right> expr '^' Integer    #ExprPower
    | expr op=('*'|':') expr            #ExprMultDiv
    | expr op=('+'|'-') expr            #ExprAddSub
    | Integer '/' Integer               #ExprFrac
    | Integer                           #ExprInteger
    | ID                                #ExprID
    | '(' expr ')'                      #ExprParent
    | 'reduce' expr                     #ExprReduce
    ;

Integer : [0-9]+; // implement with long integers
NEWLINE: '\r' ? '\n' -> skip;
ID: [a-zA-Z_]+;
WS: [ \t]+ -> skip;
COMMENT: '//' .*? '\n' -> skip ;