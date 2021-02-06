/**
 * @author Mohammad Mahdi Fallah
 * @id 01428941
 */

//Ein Programm besteht aus mindestens einem Statement, wobei jedes Statement mit ';' abgeschlossen wird.
//Ein Statement ist entweder eine Zuweisung (z.B.: t = 7;) oder eine Expression (z.B.: 1 + 2 * s / u;).

grammar BigCalc;

program 
        : expressionStatement+
        ;

expressionStatement
        : (declaration |expression)+ ';'? EOF
        ;
declaration
        : Identifer '=' expression ';'
        ;
expression
        : expression op=('*' | '/') expression  # mulDiv
        | expression op=('+' | '-') expression  # addSub
        | Number    ';'?                        # num
        | Identifer                             # variable
        | '('expression')' ';'?                 # Parens
        ;

Number
        : Digit* '.' Digit+
        | Digit+
        ;

Identifer
        :[a-zA-Z][a-zA-Z0-9_]*
        ;
Digit
        : [0-9]
        ;

WS      : [ \t\r\n\u000C]+ -> skip
        ;

COMMENT
        :   '/*' .*? '*/' -> skip
        ;

LINE_COMMENT
        : '//' ~[\r\n]* -> skip
        ;