grammar Hello;			// Define a grammar called Hello
action: (greetings | bye)+;
greetings : 'hello' ID+;	// match keyword hello followed by an identifier
bye: 'bye' ID+;
ID : [a-zA-Z]+ ;		// match lower-case identifiers
WS : [ \t\r\n]+ -> skip;	// skip spaces, tabs, newlines, \r (Windows)
