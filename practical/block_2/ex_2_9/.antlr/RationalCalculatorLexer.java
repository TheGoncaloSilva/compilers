// Generated from /home/goncalo/repos/compilers/practical/block_2/ex_2_9/RationalCalculator.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RationalCalculatorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, Integer=13, NEWLINE=14, ID=15, WS=16, COMMENT=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "Integer", "NEWLINE", "ID", "WS", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'->'", "';'", "'print'", "'+'", "'-'", "'^'", "'*'", "':'", "'/'", 
			"'('", "')'", "'reduce'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "Integer", "NEWLINE", "ID", "WS", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public RationalCalculatorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RationalCalculator.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23h\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3"+
		"\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\16\6\16I\n\16\r\16\16\16J\3\17\5\17N\n\17\3\17\3\17\3\20\6\20S\n\20\r"+
		"\20\16\20T\3\21\6\21X\n\21\r\21\16\21Y\3\21\3\21\3\22\3\22\7\22`\n\22"+
		"\f\22\16\22c\13\22\3\22\3\22\3\22\3\22\3a\2\23\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23\3\2\5\3\2"+
		"\62;\5\2C\\aac|\4\2\13\13\"\"\2l\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3%\3\2\2\2\5(\3\2\2\2\7*\3\2\2\2\t"+
		"\60\3\2\2\2\13\62\3\2\2\2\r\64\3\2\2\2\17\66\3\2\2\2\218\3\2\2\2\23:\3"+
		"\2\2\2\25<\3\2\2\2\27>\3\2\2\2\31@\3\2\2\2\33H\3\2\2\2\35M\3\2\2\2\37"+
		"R\3\2\2\2!W\3\2\2\2#]\3\2\2\2%&\7/\2\2&\'\7@\2\2\'\4\3\2\2\2()\7=\2\2"+
		")\6\3\2\2\2*+\7r\2\2+,\7t\2\2,-\7k\2\2-.\7p\2\2./\7v\2\2/\b\3\2\2\2\60"+
		"\61\7-\2\2\61\n\3\2\2\2\62\63\7/\2\2\63\f\3\2\2\2\64\65\7`\2\2\65\16\3"+
		"\2\2\2\66\67\7,\2\2\67\20\3\2\2\289\7<\2\29\22\3\2\2\2:;\7\61\2\2;\24"+
		"\3\2\2\2<=\7*\2\2=\26\3\2\2\2>?\7+\2\2?\30\3\2\2\2@A\7t\2\2AB\7g\2\2B"+
		"C\7f\2\2CD\7w\2\2DE\7e\2\2EF\7g\2\2F\32\3\2\2\2GI\t\2\2\2HG\3\2\2\2IJ"+
		"\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\34\3\2\2\2LN\7\17\2\2ML\3\2\2\2MN\3\2\2"+
		"\2NO\3\2\2\2OP\7\f\2\2P\36\3\2\2\2QS\t\3\2\2RQ\3\2\2\2ST\3\2\2\2TR\3\2"+
		"\2\2TU\3\2\2\2U \3\2\2\2VX\t\4\2\2WV\3\2\2\2XY\3\2\2\2YW\3\2\2\2YZ\3\2"+
		"\2\2Z[\3\2\2\2[\\\b\21\2\2\\\"\3\2\2\2]a\7%\2\2^`\13\2\2\2_^\3\2\2\2`"+
		"c\3\2\2\2ab\3\2\2\2a_\3\2\2\2bd\3\2\2\2ca\3\2\2\2de\7\f\2\2ef\3\2\2\2"+
		"fg\b\22\2\2g$\3\2\2\2\b\2JMTYa\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}