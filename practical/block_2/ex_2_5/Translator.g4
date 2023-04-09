grammar Translator;
dictionary:
	translation* EOF	// Zero or more repetitions of stat
	;
translation:
	expr? NEWLINE	// Optative expr followed by an end-of-line
	;
expr:
	Number '-' Meaning
	;
Number: [0-9]+;	
Meaning: [a-zA-Z]+;
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;