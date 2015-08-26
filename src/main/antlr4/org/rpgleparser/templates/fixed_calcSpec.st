fixed_calcSpec() ::= <<
//CS_IndicatorOff: {getCharPositionInLine()==8}? [nN ] -> pushMode(IndicatorMode);
//CS_BlankFactor: {getCharPositionInLine()==11 || getCharPositionInLine()==35 || getCharPositionInLine()==49}? '              ' ;
// Symbolic Constants
CS_Factor1_SPLAT_ALL : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_ALL -> type(SPLAT_ALL);
CS_Factor1_SPLAT_NONE : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_NONE -> type(SPLAT_NONE);
CS_Factor1_SPLAT_ILERPG : {11\<= getCharPositionInLine() && getCharPositionInLine()+7\<=24}? SPLAT_ILERPG -> type(SPLAT_ILERPG);
CS_Factor1_SPLAT_CRTBNDRPG : {11\<= getCharPositionInLine() && getCharPositionInLine()+10\<=24}? SPLAT_CRTBNDRPG -> type(SPLAT_CRTBNDRPG);
CS_Factor1_SPLAT_CRTRPGMOD : {11\<= getCharPositionInLine() && getCharPositionInLine()+10\<=24}? SPLAT_CRTRPGMOD -> type(SPLAT_CRTRPGMOD);
CS_Factor1_SPLAT_VRM : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_VRM -> type(SPLAT_VRM);
CS_Factor1_SPLAT_ALLG : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_ALLG -> type(SPLAT_ALLG);
CS_Factor1_SPLAT_ALLU : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_ALLU -> type(SPLAT_ALLU);
CS_Factor1_SPLAT_ALLX : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_ALLX -> type(SPLAT_ALLX);
CS_Factor1_SPLAT_BLANKS : {11\<= getCharPositionInLine() && getCharPositionInLine()+7\<=24}? SPLAT_BLANKS -> type(SPLAT_BLANKS);
CS_Factor1_SPLAT_CANCL : {11\<= getCharPositionInLine() && getCharPositionInLine()+6\<=24}? SPLAT_CANCL -> type(SPLAT_CANCL);
CS_Factor1_SPLAT_CYMD : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_CYMD -> type(SPLAT_CYMD);
CS_Factor1_SPLAT_CMDY : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_CMDY -> type(SPLAT_CMDY);
CS_Factor1_SPLAT_CDMY : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_CDMY -> type(SPLAT_CDMY);
CS_Factor1_SPLAT_MDY : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_MDY -> type(SPLAT_MDY);
CS_Factor1_SPLAT_DMY : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_DMY -> type(SPLAT_DMY);
CS_Factor1_SPLAT_YMD : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_YMD -> type(SPLAT_YMD);
CS_Factor1_SPLAT_JUL : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_JUL -> type(SPLAT_JUL);
CS_Factor1_SPLAT_ISO : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_ISO -> type(SPLAT_ISO);
CS_Factor1_SPLAT_USA : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_USA -> type(SPLAT_USA);
CS_Factor1_SPLAT_EUR : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_EUR -> type(SPLAT_EUR);
CS_Factor1_SPLAT_JIS : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_JIS -> type(SPLAT_JIS);
CS_Factor1_SPLAT_DATE : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_DATE -> type(SPLAT_DATE);
CS_Factor1_SPLAT_DAY : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_DAY -> type(SPLAT_DAY);
CS_Factor1_SPLAT_DETC : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPlAT_DETC -> type(SPlAT_DETC);
CS_Factor1_SPLAT_DETL : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_DETL -> type(SPLAT_DETL);
CS_Factor1_SPLAT_DTAARA : {11\<= getCharPositionInLine() && getCharPositionInLine()+7\<=24}? SPLAT_DTAARA -> type(SPLAT_DTAARA);
CS_Factor1_SPLAT_END : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_END -> type(SPLAT_END);
CS_Factor1_SPLAT_ENTRY : {11\<= getCharPositionInLine() && getCharPositionInLine()+6\<=24}? SPLAT_ENTRY -> type(SPLAT_ENTRY);
CS_Factor1_SPLAT_EQUATE : {11\<= getCharPositionInLine() && getCharPositionInLine()+7\<=24}? SPLAT_EQUATE -> type(SPLAT_EQUATE);
CS_Factor1_SPLAT_EXTDFT : {11\<= getCharPositionInLine() && getCharPositionInLine()+7\<=24}? SPLAT_EXTDFT -> type(SPLAT_EXTDFT);
CS_Factor1_SPLAT_EXT : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_EXT -> type(SPLAT_EXT);
CS_Factor1_SPLAT_FILE : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_FILE -> type(SPLAT_FILE);
CS_Factor1_SPLAT_GETIN : {11\<= getCharPositionInLine() && getCharPositionInLine()+6\<=24}? SPLAT_GETIN -> type(SPLAT_GETIN);
CS_Factor1_SPLAT_HIVAL : {11\<= getCharPositionInLine() && getCharPositionInLine()+6\<=24}? SPLAT_HIVAL -> type(SPLAT_HIVAL);
CS_Factor1_SPLAT_INIT : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_INIT -> type(SPLAT_INIT);
CS_Factor1_SPLAT_INDICATOR : {11\<= getCharPositionInLine() && getCharPositionInLine()+10\<=24}? SPLAT_INDICATOR -> type(SPLAT_INDICATOR);
CS_Factor1_SPLAT_INZSR : {11\<= getCharPositionInLine() && getCharPositionInLine()+6\<=24}? SPLAT_INZSR -> type(SPLAT_INZSR);
CS_Factor1_SPLAT_IN : {11\<= getCharPositionInLine() && getCharPositionInLine()+3\<=24}? SPLAT_IN -> type(SPLAT_IN);
CS_Factor1_SPLAT_JOBRUN : {11\<= getCharPositionInLine() && getCharPositionInLine()+7\<=24}? SPLAT_JOBRUN -> type(SPLAT_JOBRUN);
CS_Factor1_SPLAT_JOB : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_JOB -> type(SPLAT_JOB);
CS_Factor1_SPLAT_LDA : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_LDA -> type(SPLAT_LDA);
CS_Factor1_SPLAT_LIKE : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_LIKE -> type(SPLAT_LIKE);
CS_Factor1_SPLAT_LONGJUL : {11\<= getCharPositionInLine() && getCharPositionInLine()+8\<=24}? SPLAT_LONGJUL -> type(SPLAT_LONGJUL);
CS_Factor1_SPLAT_LOVAL : {11\<= getCharPositionInLine() && getCharPositionInLine()+6\<=24}? SPLAT_LOVAL -> type(SPLAT_LOVAL);
CS_Factor1_SPLAT_MONTH : {11\<= getCharPositionInLine() && getCharPositionInLine()+6\<=24}? SPLAT_MONTH -> type(SPLAT_MONTH);
CS_Factor1_SPLAT_NOIND : {11\<= getCharPositionInLine() && getCharPositionInLine()+6\<=24}? SPLAT_NOIND -> type(SPLAT_NOIND);
CS_Factor1_SPLAT_NOKEY : {11\<= getCharPositionInLine() && getCharPositionInLine()+6\<=24}? SPLAT_NOKEY -> type(SPLAT_NOKEY);
CS_Factor1_SPLAT_NULL : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_NULL -> type(SPLAT_NULL);
CS_Factor1_SPLAT_OFL : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_OFL -> type(SPLAT_OFL);
CS_Factor1_SPLAT_ON : {11\<= getCharPositionInLine() && getCharPositionInLine()+3\<=24}? SPLAT_ON -> type(SPLAT_ON);
CS_Factor1_SPLAT_OFF : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_OFF -> type(SPLAT_OFF);
CS_Factor1_SPLAT_PDA : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_PDA -> type(SPLAT_PDA);
CS_Factor1_SPLAT_PLACE : {11\<= getCharPositionInLine() && getCharPositionInLine()+6\<=24}? SPLAT_PLACE -> type(SPLAT_PLACE);
CS_Factor1_SPLAT_PSSR : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_PSSR -> type(SPLAT_PSSR);
CS_Factor1_SPLAT_ROUTINE : {11\<= getCharPositionInLine() && getCharPositionInLine()+8\<=24}? SPLAT_ROUTINE -> type(SPLAT_ROUTINE);
CS_Factor1_SPLAT_START : {11\<= getCharPositionInLine() && getCharPositionInLine()+6\<=24}? SPLAT_START -> type(SPLAT_START);
CS_Factor1_SPLAT_SYS : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_SYS -> type(SPLAT_SYS);
CS_Factor1_SPLAT_TERM : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_TERM -> type(SPLAT_TERM);
CS_Factor1_SPLAT_TOTC : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_TOTC -> type(SPLAT_TOTC);
CS_Factor1_SPLAT_TOTL : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_TOTL -> type(SPLAT_TOTL);
CS_Factor1_SPLAT_USER : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_USER -> type(SPLAT_USER);
CS_Factor1_SPLAT_VAR : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_VAR -> type(SPLAT_VAR);
CS_Factor1_SPLAT_YEAR : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_YEAR -> type(SPLAT_YEAR);
CS_Factor1_SPLAT_ZEROS : {11\<= getCharPositionInLine() && getCharPositionInLine()+6\<=24}? SPLAT_ZEROS -> type(SPLAT_ZEROS);
CS_Factor1_SPLAT_HMS : {11\<= getCharPositionInLine() && getCharPositionInLine()+4\<=24}? SPLAT_HMS -> type(SPLAT_HMS);
CS_Factor1_SPLAT_INLR : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_INLR -> type(SPLAT_INLR);
CS_Factor1_SPLAT_INOF : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_INOF -> type(SPLAT_INOF);
//DurationCodes
CS_Factor1_SPLAT_D : {11\<= getCharPositionInLine() && getCharPositionInLine()+2\<=24}? SPLAT_D -> type(SPLAT_D);
CS_Factor1_SPLAT_DAYS : {11\<= getCharPositionInLine() && getCharPositionInLine()+5\<=24}? SPLAT_DAYS -> type(SPLAT_DAYS);
CS_Factor1_SPLAT_H : {11\<= getCharPositionInLine() && getCharPositionInLine()+2\<=24}? SPLAT_H -> type(SPLAT_H);
CS_Factor1_SPLAT_HOURS : {11\<= getCharPositionInLine() && getCharPositionInLine()+6\<=24}? SPLAT_HOURS -> type(SPLAT_HOURS);
CS_Factor1_SPLAT_MINUTES : {11\<= getCharPositionInLine() && getCharPositionInLine()+8\<=24}? SPLAT_MINUTES -> type(SPLAT_MINUTES);
CS_Factor1_SPLAT_MONTHS : {11\<= getCharPositionInLine() && getCharPositionInLine()+7\<=24}? SPLAT_MONTHS -> type(SPLAT_MONTHS);
CS_Factor1_SPLAT_M : {11\<= getCharPositionInLine() && getCharPositionInLine()+2\<=24}? SPLAT_M -> type(SPLAT_M);
CS_Factor1_SPLAT_MN : {11\<= getCharPositionInLine() && getCharPositionInLine()+3\<=24}? SPLAT_MN -> type(SPLAT_MN);
CS_Factor1_SPLAT_MS : {11\<= getCharPositionInLine() && getCharPositionInLine()+3\<=24}? SPLAT_MS -> type(SPLAT_MS);
CS_Factor1_SPLAT_MSECONDS : {11\<= getCharPositionInLine() && getCharPositionInLine()+9\<=24}? SPLAT_MSECONDS -> type(SPLAT_MSECONDS);
CS_Factor1_SPLAT_SECONDS : {11\<= getCharPositionInLine() && getCharPositionInLine()+8\<=24}? SPLAT_SECONDS -> type(SPLAT_SECONDS);
CS_Factor1_SPLAT_YEARS : {11\<= getCharPositionInLine() && getCharPositionInLine()+6\<=24}? SPLAT_YEARS -> type(SPLAT_YEARS);
CS_Factor1_SPLAT_Y : {11\<= getCharPositionInLine() && getCharPositionInLine()+2\<=24}? SPLAT_Y -> type(SPLAT_Y);

//Factor 2
CS_Factor2_SPLAT_ALL : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_ALL -> type(SPLAT_ALL);
CS_Factor2_SPLAT_NONE : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_NONE -> type(SPLAT_NONE);
CS_Factor2_SPLAT_ILERPG : {35\<= getCharPositionInLine() && getCharPositionInLine()+7\<=48}? SPLAT_ILERPG -> type(SPLAT_ILERPG);
CS_Factor2_SPLAT_CRTBNDRPG : {35\<= getCharPositionInLine() && getCharPositionInLine()+10\<=48}? SPLAT_CRTBNDRPG -> type(SPLAT_CRTBNDRPG);
CS_Factor2_SPLAT_CRTRPGMOD : {35\<= getCharPositionInLine() && getCharPositionInLine()+10\<=48}? SPLAT_CRTRPGMOD -> type(SPLAT_CRTRPGMOD);
CS_Factor2_SPLAT_VRM : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_VRM -> type(SPLAT_VRM);
CS_Factor2_SPLAT_ALLG : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_ALLG -> type(SPLAT_ALLG);
CS_Factor2_SPLAT_ALLU : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_ALLU -> type(SPLAT_ALLU);
CS_Factor2_SPLAT_ALLX : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_ALLX -> type(SPLAT_ALLX);
CS_Factor2_SPLAT_BLANKS : {35\<= getCharPositionInLine() && getCharPositionInLine()+7\<=48}? SPLAT_BLANKS -> type(SPLAT_BLANKS);
CS_Factor2_SPLAT_CANCL : {35\<= getCharPositionInLine() && getCharPositionInLine()+6\<=48}? SPLAT_CANCL -> type(SPLAT_CANCL);
CS_Factor2_SPLAT_CYMD : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_CYMD -> type(SPLAT_CYMD);
CS_Factor2_SPLAT_CMDY : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_CMDY -> type(SPLAT_CMDY);
CS_Factor2_SPLAT_CDMY : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_CDMY -> type(SPLAT_CDMY);
CS_Factor2_SPLAT_MDY : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_MDY -> type(SPLAT_MDY);
CS_Factor2_SPLAT_DMY : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_DMY -> type(SPLAT_DMY);
CS_Factor2_SPLAT_YMD : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_YMD -> type(SPLAT_YMD);
CS_Factor2_SPLAT_JUL : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_JUL -> type(SPLAT_JUL);
CS_Factor2_SPLAT_ISO : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_ISO -> type(SPLAT_ISO);
CS_Factor2_SPLAT_USA : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_USA -> type(SPLAT_USA);
CS_Factor2_SPLAT_EUR : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_EUR -> type(SPLAT_EUR);
CS_Factor2_SPLAT_JIS : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_JIS -> type(SPLAT_JIS);
CS_Factor2_SPLAT_DATE : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_DATE -> type(SPLAT_DATE);
CS_Factor2_SPLAT_DAY : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_DAY -> type(SPLAT_DAY);
CS_Factor2_SPLAT_DETC : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPlAT_DETC -> type(SPlAT_DETC);
CS_Factor2_SPLAT_DETL : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_DETL -> type(SPLAT_DETL);
CS_Factor2_SPLAT_DTAARA : {35\<= getCharPositionInLine() && getCharPositionInLine()+7\<=48}? SPLAT_DTAARA -> type(SPLAT_DTAARA);
CS_Factor2_SPLAT_END : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_END -> type(SPLAT_END);
CS_Factor2_SPLAT_ENTRY : {35\<= getCharPositionInLine() && getCharPositionInLine()+6\<=48}? SPLAT_ENTRY -> type(SPLAT_ENTRY);
CS_Factor2_SPLAT_EQUATE : {35\<= getCharPositionInLine() && getCharPositionInLine()+7\<=48}? SPLAT_EQUATE -> type(SPLAT_EQUATE);
CS_Factor2_SPLAT_EXTDFT : {35\<= getCharPositionInLine() && getCharPositionInLine()+7\<=48}? SPLAT_EXTDFT -> type(SPLAT_EXTDFT);
CS_Factor2_SPLAT_EXT : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_EXT -> type(SPLAT_EXT);
CS_Factor2_SPLAT_FILE : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_FILE -> type(SPLAT_FILE);
CS_Factor2_SPLAT_GETIN : {35\<= getCharPositionInLine() && getCharPositionInLine()+6\<=48}? SPLAT_GETIN -> type(SPLAT_GETIN);
CS_Factor2_SPLAT_HIVAL : {35\<= getCharPositionInLine() && getCharPositionInLine()+6\<=48}? SPLAT_HIVAL -> type(SPLAT_HIVAL);
CS_Factor2_SPLAT_INIT : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_INIT -> type(SPLAT_INIT);
CS_Factor2_SPLAT_INDICATOR : {35\<= getCharPositionInLine() && getCharPositionInLine()+10\<=48}? SPLAT_INDICATOR -> type(SPLAT_INDICATOR);
CS_Factor2_SPLAT_INZSR : {35\<= getCharPositionInLine() && getCharPositionInLine()+6\<=48}? SPLAT_INZSR -> type(SPLAT_INZSR);
CS_Factor2_SPLAT_IN : {35\<= getCharPositionInLine() && getCharPositionInLine()+3\<=48}? SPLAT_IN -> type(SPLAT_IN);
CS_Factor2_SPLAT_JOBRUN : {35\<= getCharPositionInLine() && getCharPositionInLine()+7\<=48}? SPLAT_JOBRUN -> type(SPLAT_JOBRUN);
CS_Factor2_SPLAT_JOB : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_JOB -> type(SPLAT_JOB);
CS_Factor2_SPLAT_LDA : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_LDA -> type(SPLAT_LDA);
CS_Factor2_SPLAT_LIKE : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_LIKE -> type(SPLAT_LIKE);
CS_Factor2_SPLAT_LONGJUL : {35\<= getCharPositionInLine() && getCharPositionInLine()+8\<=48}? SPLAT_LONGJUL -> type(SPLAT_LONGJUL);
CS_Factor2_SPLAT_LOVAL : {35\<= getCharPositionInLine() && getCharPositionInLine()+6\<=48}? SPLAT_LOVAL -> type(SPLAT_LOVAL);
CS_Factor2_SPLAT_MONTH : {35\<= getCharPositionInLine() && getCharPositionInLine()+6\<=48}? SPLAT_MONTH -> type(SPLAT_MONTH);
CS_Factor2_SPLAT_NOIND : {35\<= getCharPositionInLine() && getCharPositionInLine()+6\<=48}? SPLAT_NOIND -> type(SPLAT_NOIND);
CS_Factor2_SPLAT_NOKEY : {35\<= getCharPositionInLine() && getCharPositionInLine()+6\<=48}? SPLAT_NOKEY -> type(SPLAT_NOKEY);
CS_Factor2_SPLAT_NULL : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_NULL -> type(SPLAT_NULL);
CS_Factor2_SPLAT_OFL : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_OFL -> type(SPLAT_OFL);
CS_Factor2_SPLAT_ON : {35\<= getCharPositionInLine() && getCharPositionInLine()+3\<=48}? SPLAT_ON -> type(SPLAT_ON);
CS_Factor2_SPLAT_OFF : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_OFF -> type(SPLAT_OFF);
CS_Factor2_SPLAT_PDA : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_PDA -> type(SPLAT_PDA);
CS_Factor2_SPLAT_PLACE : {35\<= getCharPositionInLine() && getCharPositionInLine()+6\<=48}? SPLAT_PLACE -> type(SPLAT_PLACE);
CS_Factor2_SPLAT_PSSR : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_PSSR -> type(SPLAT_PSSR);
CS_Factor2_SPLAT_ROUTINE : {35\<= getCharPositionInLine() && getCharPositionInLine()+8\<=48}? SPLAT_ROUTINE -> type(SPLAT_ROUTINE);
CS_Factor2_SPLAT_START : {35\<= getCharPositionInLine() && getCharPositionInLine()+6\<=48}? SPLAT_START -> type(SPLAT_START);
CS_Factor2_SPLAT_SYS : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_SYS -> type(SPLAT_SYS);
CS_Factor2_SPLAT_TERM : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_TERM -> type(SPLAT_TERM);
CS_Factor2_SPLAT_TOTC : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_TOTC -> type(SPLAT_TOTC);
CS_Factor2_SPLAT_TOTL : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_TOTL -> type(SPLAT_TOTL);
CS_Factor2_SPLAT_USER : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_USER -> type(SPLAT_USER);
CS_Factor2_SPLAT_VAR : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_VAR -> type(SPLAT_VAR);
CS_Factor2_SPLAT_YEAR : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_YEAR -> type(SPLAT_YEAR);
CS_Factor2_SPLAT_ZEROS : {35\<= getCharPositionInLine() && getCharPositionInLine()+6\<=48}? SPLAT_ZEROS -> type(SPLAT_ZEROS);
CS_Factor2_SPLAT_HMS : {35\<= getCharPositionInLine() && getCharPositionInLine()+4\<=48}? SPLAT_HMS -> type(SPLAT_HMS);
CS_Factor2_SPLAT_INLR : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_INLR -> type(SPLAT_INLR);
CS_Factor2_SPLAT_INOF : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_INOF -> type(SPLAT_INOF);
//Duration
CS_Factor2_SPLAT_D : {35\<= getCharPositionInLine() && getCharPositionInLine()+2\<=48}? SPLAT_D -> type(SPLAT_D);
CS_Factor2_SPLAT_DAYS : {35\<= getCharPositionInLine() && getCharPositionInLine()+5\<=48}? SPLAT_DAYS -> type(SPLAT_DAYS);
CS_Factor2_SPLAT_H : {35\<= getCharPositionInLine() && getCharPositionInLine()+2\<=48}? SPLAT_H -> type(SPLAT_H);
CS_Factor2_SPLAT_HOURS : {35\<= getCharPositionInLine() && getCharPositionInLine()+6\<=48}? SPLAT_HOURS -> type(SPLAT_HOURS);
CS_Factor2_SPLAT_MINUTES : {35\<= getCharPositionInLine() && getCharPositionInLine()+8\<=48}? SPLAT_MINUTES -> type(SPLAT_MINUTES);
CS_Factor2_SPLAT_MONTHS : {35\<= getCharPositionInLine() && getCharPositionInLine()+7\<=48}? SPLAT_MONTHS -> type(SPLAT_MONTHS);
CS_Factor2_SPLAT_M : {35\<= getCharPositionInLine() && getCharPositionInLine()+2\<=48}? SPLAT_M -> type(SPLAT_M);
CS_Factor2_SPLAT_MN : {35\<= getCharPositionInLine() && getCharPositionInLine()+3\<=48}? SPLAT_MN -> type(SPLAT_MN);
CS_Factor2_SPLAT_MS : {35\<= getCharPositionInLine() && getCharPositionInLine()+3\<=48}? SPLAT_MS -> type(SPLAT_MS);
CS_Factor2_SPLAT_MSECONDS : {35\<= getCharPositionInLine() && getCharPositionInLine()+9\<=48}? SPLAT_MSECONDS -> type(SPLAT_MSECONDS);
CS_Factor2_SPLAT_SECONDS : {35\<= getCharPositionInLine() && getCharPositionInLine()+8\<=48}? SPLAT_SECONDS -> type(SPLAT_SECONDS);
CS_Factor2_SPLAT_YEARS : {35\<= getCharPositionInLine() && getCharPositionInLine()+6\<=48}? SPLAT_YEARS -> type(SPLAT_YEARS);
CS_Factor2_SPLAT_Y : {35\<= getCharPositionInLine() && getCharPositionInLine()+2\<=48}? SPLAT_Y -> type(SPLAT_Y);

//Result 

//Duration
CS_Result_SPLAT_D : {49\<= getCharPositionInLine() && getCharPositionInLine()+2\<=62}? SPLAT_D -> type(SPLAT_D);
CS_Result_SPLAT_DAYS : {49\<= getCharPositionInLine() && getCharPositionInLine()+5\<=62}? SPLAT_DAYS -> type(SPLAT_DAYS);
CS_Result_SPLAT_H : {49\<= getCharPositionInLine() && getCharPositionInLine()+2\<=62}? SPLAT_H -> type(SPLAT_H);
CS_Result_SPLAT_HOURS : {49\<= getCharPositionInLine() && getCharPositionInLine()+6\<=62}? SPLAT_HOURS -> type(SPLAT_HOURS);
CS_Result_SPLAT_MINUTES : {49\<= getCharPositionInLine() && getCharPositionInLine()+8\<=62}? SPLAT_MINUTES -> type(SPLAT_MINUTES);
CS_Result_SPLAT_MONTHS : {49\<= getCharPositionInLine() && getCharPositionInLine()+7\<=62}? SPLAT_MONTHS -> type(SPLAT_MONTHS);
CS_Result_SPLAT_M : {49\<= getCharPositionInLine() && getCharPositionInLine()+2\<=62}? SPLAT_M -> type(SPLAT_M);
CS_Result_SPLAT_MN : {49\<= getCharPositionInLine() && getCharPositionInLine()+3\<=62}? SPLAT_MN -> type(SPLAT_MN);
CS_Result_SPLAT_MS : {49\<= getCharPositionInLine() && getCharPositionInLine()+3\<=62}? SPLAT_MS -> type(SPLAT_MS);
CS_Result_SPLAT_MSECONDS : {49\<= getCharPositionInLine() && getCharPositionInLine()+9\<=62}? SPLAT_MSECONDS -> type(SPLAT_MSECONDS);
CS_Result_SPLAT_SECONDS : {49\<= getCharPositionInLine() && getCharPositionInLine()+8\<=62}? SPLAT_SECONDS -> type(SPLAT_SECONDS);
CS_Result_SPLAT_YEARS : {49\<= getCharPositionInLine() && getCharPositionInLine()+6\<=62}? SPLAT_YEARS -> type(SPLAT_YEARS);
CS_Result_SPLAT_Y : {49\<= getCharPositionInLine() && getCharPositionInLine()+2\<=62}? SPLAT_Y -> type(SPLAT_Y);


CS_BlankFactor: {(getCharPositionInLine()==11)
			|| (getCharPositionInLine()==35)
			|| (getCharPositionInLine()==49)}? 
			 		'              ';
CS_FactorWs: ({(getCharPositionInLine()>=11 && getCharPositionInLine()\<=24)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()\<=48)
}?  
		' ')+ -> skip;
CS_FactorWs2: ({(getCharPositionInLine()>=49 && getCharPositionInLine()\<=62)
}?  
		' ')+ -> skip;
		
/*
 * This rather awkward token, matches a literal. including whitespace literals
 */
 CS_FactorContentHexLiteral: {(getCharPositionInLine()>=11 && getCharPositionInLine()\<=23)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()\<=47)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()\<=61)
}?
		[xX]['] -> type(HexLiteralStart),pushMode(InFactorStringMode);
		
 CS_FactorContentDateLiteral: {(getCharPositionInLine()>=11 && getCharPositionInLine()\<=23)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()\<=47)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()\<=61)
}?
		[dD]['] -> type(DateLiteralStart),pushMode(InFactorStringMode);
		
 CS_FactorContentTimeLiteral: {(getCharPositionInLine()>=11 && getCharPositionInLine()\<=23)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()\<=47)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()\<=61)
}?
		[tT]['] -> type(TimeLiteralStart),pushMode(InFactorStringMode);
		
 CS_FactorContentGraphicLiteral: {(getCharPositionInLine()>=11 && getCharPositionInLine()\<=23)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()\<=47)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()\<=61)
}?
		[gG]['] -> type(GraphicLiteralStart),pushMode(InFactorStringMode);
		
 CS_FactorContentUCS2Literal: {(getCharPositionInLine()>=11 && getCharPositionInLine()\<=23)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()\<=47)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()\<=61)
}?
		[uU]['] -> type(UCS2LiteralStart),pushMode(InFactorStringMode);
		
 CS_FactorContentStringLiteral: {(getCharPositionInLine()>=11 && getCharPositionInLine()\<=24)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()\<=48)
			|| (getCharPositionInLine()>=49 && getCharPositionInLine()\<=62)
}?
		['] -> type(StringLiteralStart),pushMode(InFactorStringMode);
// 		
//CS_FactorContentLiteral: {(getCharPositionInLine()>=11 && getCharPositionInLine()\<=24)
//			|| (getCharPositionInLine()>=35 && getCharPositionInLine()\<=48)
//			|| (getCharPositionInLine()>=49 && getCharPositionInLine()\<=62)
//}?
//		['] -> type(UCS2LiteralStart),pushMode(InFactorStringMode);
		
CS_FactorContent: ({(getCharPositionInLine()>=11 && getCharPositionInLine()\<=24)
			|| (getCharPositionInLine()>=35 && getCharPositionInLine()\<=48)
}?
		~[\r\n'\'' :])+;
CS_ResultContent: ({(getCharPositionInLine()>=49 && getCharPositionInLine()\<=62)}?
		~[\r\n'\'' :])+ -> type(CS_FactorContent);
CS_FactorColon: ({(getCharPositionInLine()>11 && getCharPositionInLine()\<24)
			|| (getCharPositionInLine()>35 && getCharPositionInLine()\<48)
			|| (getCharPositionInLine()>49 && getCharPositionInLine()\<62)
}?
		[:]) -> type(COLON);//pushMode(FIXED_CalcSpec_2PartFactor),
		/* 
CS_OperationAndExtendedFactor2: {getCharPositionInLine()==25}? 
	(OP_EVAL '      ' 
		| OP_IF '        '  
		| OP_CALLP'     '
		| OP_CALLP[(][eE][)]'  '
		| OP_DOW'       '
		| OP_ELSEIF'    '
		| OP_ON_ERROR'  '
	) -> pushMode(FREE);
	*/
CS_OperationAndExtender_Blank:  
   {getCharPositionInLine()==25}?'          ';
CS_OperationAndExtender_WS:
	({getCharPositionInLine()>=25 && getCharPositionInLine()\<35}?[ ])+ -> skip;	
CS_Operation_ACQ: {getCharPositionInLine()>=25 && getCharPositionInLine()\<33}? OP_ACQ -> type(OP_ACQ);
CS_Operation_ADD: {getCharPositionInLine()>=25 && getCharPositionInLine()\<33}? OP_ADD -> type(OP_ADD);
CS_Operation_ADDDUR: {getCharPositionInLine()>=25 && getCharPositionInLine()\<30}? OP_ADDDUR -> type(OP_ADDDUR);
CS_Operation_ALLOC: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ALLOC -> type(OP_ALLOC);
CS_Operation_ANDEQ: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ANDEQ -> type(OP_ANDEQ);
CS_Operation_ANDNE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ANDNE -> type(OP_ANDNE);
CS_Operation_ANDLE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ANDLE -> type(OP_ANDLE);
CS_Operation_ANDLT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ANDLT -> type(OP_ANDLT);
CS_Operation_ANDGE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ANDGE -> type(OP_ANDGE);
CS_Operation_ANDGT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ANDGT -> type(OP_ANDGT);
CS_Operation_ANDxx: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ANDxx -> type(OP_ANDxx);
CS_Operation_BEGSR: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_BEGSR -> type(OP_BEGSR);
CS_Operation_BITOFF: {getCharPositionInLine()>=25 && getCharPositionInLine()\<30}? OP_BITOFF -> type(OP_BITOFF);
CS_Operation_BITON: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_BITON -> type(OP_BITON);
CS_Operation_CABxx: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CABxx-> type(OP_CABxx);
CS_Operation_CABEQ: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CABEQ-> type(OP_CABEQ);
CS_Operation_CABNE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CABNE-> type(OP_CABNE);
CS_Operation_CABLE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CABLE-> type(OP_CABLE);
CS_Operation_CABLT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CABLT-> type(OP_CABLT);
CS_Operation_CABGE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CABGE-> type(OP_CABGE);
CS_Operation_CABGT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CABGT-> type(OP_CABGT);
CS_Operation_CALL: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CALL -> type(OP_CALL);
CS_Operation_CALLB: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CALLB -> type(OP_CALLB);
CS_Operation_CALLP: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CALLP -> type(OP_CALLP),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_CASEQ: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CASEQ-> type(OP_CASEQ);
CS_Operation_CASNE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CASNE-> type(OP_CASNE);
CS_Operation_CASLE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CASLE-> type(OP_CASLE);
CS_Operation_CASLT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CASLT-> type(OP_CASLT);
CS_Operation_CASGE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CASGE-> type(OP_CASGE);
CS_Operation_CASGT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CASGT-> type(OP_CASGT);
CS_Operation_CAS: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CAS-> type(OP_CAS);
CS_Operation_CAT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CAT-> type(OP_CAT);
CS_Operation_CHAIN: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CHAIN-> type(OP_CHAIN);
CS_Operation_CHECK: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CHECK-> type(OP_CHECK);
CS_Operation_CHECKR: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CHECKR-> type(OP_CHECKR);
CS_Operation_CLEAR: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CLEAR-> type(OP_CLEAR);
CS_Operation_CLOSE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_CLOSE-> type(OP_CLOSE);
CS_Operation_COMMIT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_COMMIT-> type(OP_COMMIT);
CS_Operation_COMP: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_COMP-> type(OP_COMP);
CS_Operation_DEALLOC: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DEALLOC-> type(OP_DEALLOC);
CS_Operation_DEFINE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DEFINE-> type(OP_DEFINE);
CS_Operation_DELETE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DELETE-> type(OP_DELETE);
CS_Operation_DIV: {getCharPositionInLine()>=25 && getCharPositionInLine()\<33}? OP_DIV-> type(OP_DIV);
CS_Operation_DO: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DO-> type(OP_DO);
CS_Operation_DOU: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DOU-> type(OP_DOU),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_DOUEQ: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DOUEQ-> type(OP_DOUEQ);
CS_Operation_DOUNE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DOUNE-> type(OP_DOUNE);
CS_Operation_DOULE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DOULE-> type(OP_DOULE);
CS_Operation_DOULT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DOULT-> type(OP_DOULT);
CS_Operation_DOUGE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DOUGE-> type(OP_DOUGE);
CS_Operation_DOUGT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DOUGT-> type(OP_DOUGT);
CS_Operation_DOW: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DOW-> type(OP_DOW),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_DOWEQ: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DOWEQ-> type(OP_DOWEQ);
CS_Operation_DOWNE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DOWNE-> type(OP_DOWNE);
CS_Operation_DOWLE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DOWLE-> type(OP_DOWLE);
CS_Operation_DOWLT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DOWLT-> type(OP_DOWLT);
CS_Operation_DOWGE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DOWGE-> type(OP_DOWGE);
CS_Operation_DOWGT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DOWGT-> type(OP_DOWGT);
CS_Operation_DSPLY: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DSPLY-> type(OP_DSPLY);
CS_Operation_DUMP: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_DUMP-> type(OP_DUMP);
CS_Operation_ELSE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ELSE-> type(OP_ELSE);
CS_Operation_ELSEIF: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ELSEIF-> type(OP_ELSEIF),pushMode(FREE);
CS_Operation_END: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_END-> type(OP_END);
CS_Operation_ENDCS: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ENDCS-> type(OP_ENDCS);
CS_Operation_ENDDO: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ENDDO-> type(OP_ENDDO);
CS_Operation_ENDFOR: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ENDFOR-> type(OP_ENDFOR);
CS_Operation_ENDIF: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ENDIF-> type(OP_ENDIF);
CS_Operation_ENDMON: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ENDMON-> type(OP_ENDMON);
CS_Operation_ENDSL: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ENDSL-> type(OP_ENDSL);
CS_Operation_ENDSR: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ENDSR-> type(OP_ENDSR);
CS_Operation_EVAL: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_EVAL-> type(OP_EVAL),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_EVALR: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_EVALR-> type(OP_EVALR),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_EVAL_CORR: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_EVAL_CORR-> type(OP_EVAL_CORR),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_EXCEPT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_EXCEPT-> type(OP_EXCEPT);
CS_Operation_EXFMT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_EXFMT-> type(OP_EXFMT);
CS_Operation_EXSR: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_EXSR-> type(OP_EXSR);
CS_Operation_EXTRCT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_EXTRCT-> type(OP_EXTRCT);
CS_Operation_FEOD: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_FEOD-> type(OP_FEOD);
CS_Operation_FOR: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_FOR-> type(OP_FOR),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_FORCE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_FORCE-> type(OP_FORCE);
CS_Operation_GOTO: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_GOTO-> type(OP_GOTO);
CS_Operation_IF: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_IF-> type(OP_IF),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_IFEQ: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_IFEQ-> type(OP_IFEQ);
CS_Operation_IFNE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_IFNE-> type(OP_IFNE);
CS_Operation_IFLE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_IFLE-> type(OP_IFLE);
CS_Operation_IFLT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_IFLT-> type(OP_IFLT);
CS_Operation_IFGE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_IFGE-> type(OP_IFGE);
CS_Operation_IFGT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_IFGT-> type(OP_IFGT);
CS_Operation_IN: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_IN-> type(OP_IN);
CS_Operation_ITER: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ITER-> type(OP_ITER);
CS_Operation_KFLD: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_KFLD-> type(OP_KFLD);
CS_Operation_KLIST: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_KLIST-> type(OP_KLIST);
CS_Operation_LEAVE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_LEAVE-> type(OP_LEAVE);
CS_Operation_LEAVESR: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_LEAVESR-> type(OP_LEAVESR);
CS_Operation_LOOKUP: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_LOOKUP-> type(OP_LOOKUP);
CS_Operation_MHHZO: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_MHHZO-> type(OP_MHHZO);
CS_Operation_MHLZO: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_MHLZO-> type(OP_MHLZO);
CS_Operation_MLHZO: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_MLHZO-> type(OP_MLHZO);
CS_Operation_MLLZO: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_MLLZO-> type(OP_MLLZO);
CS_Operation_MONITOR: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_MONITOR-> type(OP_MONITOR);
CS_Operation_MOVE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_MOVE-> type(OP_MOVE);
CS_Operation_MOVEA: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_MOVEA-> type(OP_MOVEA);
CS_Operation_MOVEL: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_MOVEL-> type(OP_MOVEL);
CS_Operation_MULT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_MULT-> type(OP_MULT);
CS_Operation_MVR: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_MVR-> type(OP_MVR);
CS_Operation_NEXT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_NEXT-> type(OP_NEXT);
CS_Operation_OCCUR: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_OCCUR-> type(OP_OCCUR);
CS_Operation_ON_ERROR: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ON_ERROR-> type(OP_ON_ERROR),pushMode(FREE);
CS_Operation_OPEN: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_OPEN-> type(OP_OPEN);
CS_Operation_OREQ: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_OREQ-> type(OP_OREQ);
CS_Operation_ORNE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ORNE-> type(OP_ORNE);
CS_Operation_ORLE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ORLE-> type(OP_ORLE);
CS_Operation_ORLT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ORLT-> type(OP_ORLT);
CS_Operation_ORGE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ORGE-> type(OP_ORGE);
CS_Operation_ORGT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ORGT-> type(OP_ORGT);
CS_Operation_OTHER: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_OTHER-> type(OP_OTHER);
CS_Operation_OUT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_OUT-> type(OP_OUT);
CS_Operation_PARM: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_PARM-> type(OP_PARM);
CS_Operation_PLIST: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_PLIST-> type(OP_PLIST);
CS_Operation_POST: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_POST-> type(OP_POST);
CS_Operation_READ: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_READ-> type(OP_READ);
CS_Operation_READC: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_READC-> type(OP_READC);
CS_Operation_READE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_READE-> type(OP_READE);
CS_Operation_READP: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_READP-> type(OP_READP);
CS_Operation_READPE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_READPE-> type(OP_READPE);
CS_Operation_REALLOC: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_REALLOC-> type(OP_REALLOC);
CS_Operation_REL: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_REL-> type(OP_REL);
CS_Operation_RESET: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_RESET-> type(OP_RESET);
CS_Operation_RETURN: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_RETURN-> type(OP_RETURN),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_ROLBK: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_ROLBK-> type(OP_ROLBK);
CS_Operation_SCAN: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_SCAN-> type(OP_SCAN);
CS_Operation_SELECT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_SELECT-> type(OP_SELECT);
CS_Operation_SETGT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_SETGT-> type(OP_SETGT);
CS_Operation_SETLL: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_SETLL-> type(OP_SETLL);
CS_Operation_SETOFF: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_SETOFF-> type(OP_SETOFF);
CS_Operation_SETON: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_SETON-> type(OP_SETON);
CS_Operation_SORTA: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_SORTA-> type(OP_SORTA),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_SHTDN: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_SHTDN-> type(OP_SHTDN);
CS_Operation_SQRT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_SQRT-> type(OP_SQRT);
CS_Operation_SUB: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_SUB-> type(OP_SUB);
CS_Operation_SUBDUR: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_SUBDUR-> type(OP_SUBDUR);
CS_Operation_SUBST: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_SUBST-> type(OP_SUBST);
CS_Operation_TAG: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_TAG-> type(OP_TAG);
CS_Operation_TEST: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_TEST-> type(OP_TEST);
CS_Operation_TESTB: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_TESTB-> type(OP_TESTB);
CS_Operation_TESTN: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_TESTN-> type(OP_TESTN);
CS_Operation_TESTZ: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_TESTZ-> type(OP_TESTZ);
CS_Operation_TIME: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_TIME-> type(OP_TIME);
CS_Operation_UNLOCK: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_UNLOCK-> type(OP_UNLOCK);
CS_Operation_UPDATE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_UPDATE-> type(OP_UPDATE);
CS_Operation_WHEN: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_WHEN-> type(OP_WHEN),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_WHENEQ: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_WHENEQ-> type(OP_WHENEQ);
CS_Operation_WHENNE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_WHENNE-> type(OP_WHENNE);
CS_Operation_WHENLE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_WHENLE-> type(OP_WHENLE);
CS_Operation_WHENLT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_WHENLT-> type(OP_WHENLT);
CS_Operation_WHENGE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_WHENGE-> type(OP_WHENGE);
CS_Operation_WHENGT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_WHENGT-> type(OP_WHENGT);
CS_Operation_WRITE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_WRITE-> type(OP_WRITE);
CS_Operation_XFOOT: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_XFOOT-> type(OP_XFOOT);
CS_Operation_XLATE: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_XLATE-> type(OP_XLATE);
CS_Operation_XML_INTO: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_XML_INTO-> type(OP_XML_INTO),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_XML_SAX: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_XML_SAX-> type(OP_XML_SAX),pushMode(FREE),pushMode(FixedOpExtender);
CS_Operation_Z_ADD: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_Z_ADD-> type(OP_Z_ADD);
CS_Operation_Z_SUB: {getCharPositionInLine()>=25 && getCharPositionInLine()\<31}? OP_Z_SUB-> type(OP_Z_SUB);


CS_OperationAndExtender:  
   ({getCharPositionInLine()>=25 && getCharPositionInLine()\<35}?[a-zA-Z0-9\\\\-])+;
CS_OperationExtenderOpen: {getCharPositionInLine()>=25 && getCharPositionInLine()\<35}?OPEN_PAREN -> type(OPEN_PAREN);
CS_OperationExtenderClose: {getCharPositionInLine()>=25 && getCharPositionInLine()\<35}?CLOSE_PAREN 
  ({getCharPositionInLine()>=25 && getCharPositionInLine()\<35}? ' ')*
  {setText(getText().trim());}
  -> type(CLOSE_PAREN);
  
CS_FieldLength: {getCharPositionInLine()==63}? [+\\\\- 0-9][+\\\\- 0-9][+\\\\- 0-9][+\\\\- 0-9][+\\\\- 0-9];
CS_DecimalPositions: {getCharPositionInLine()==68}? [ 0-9][ 0-9]
	-> pushMode(IndicatorMode),pushMode(IndicatorMode),pushMode(IndicatorMode); // 3 Indicators in a row
CS_WhiteSpace : {getCharPositionInLine()>=76}? [ \t]+ -> skip  ; // skip spaces, tabs, newlines
CS_Comments : {getCharPositionInLine()>=80}? ~[\r\n]+  ; // skip spaces, tabs, newlines
CS_EOL : NEWLINE -> type(EOL),popMode;

mode FixedOpExtender;
CS_FixedOperationAndExtender_WS:
	({getCharPositionInLine()>=25 && getCharPositionInLine()\<35}?[ ])+ -> skip;	
CS_FixedOperationExtenderOpen: {getCharPositionInLine()>=25 && getCharPositionInLine()\<35}?OPEN_PAREN -> type(OPEN_PAREN),popMode,pushMode(FixedOpExtender2);
CS_FixedOperationExtenderReturn: {getCharPositionInLine()>=25 && getCharPositionInLine()\<=35}? ->skip,popMode;

mode FixedOpExtender2;
CS_FixedOperationAndExtender2_WS:
	({getCharPositionInLine()>=25 && getCharPositionInLine()\<35}?[ ])+ -> skip;	
CS_FixedOperationAndExtender2:  
   ({getCharPositionInLine()>=25 && getCharPositionInLine()\<35}?[a-zA-Z0-9\\\\-])+ -> type(CS_OperationAndExtender);
CS_FixedOperationExtender2Close: {getCharPositionInLine()>=25 && getCharPositionInLine()\<35}?CLOSE_PAREN 
  ({getCharPositionInLine()>=25 && getCharPositionInLine()\<35}? ' ')*
  {setText(getText().trim());}
  -> type(CLOSE_PAREN);
CS_FixedOperationExtender2Return: {getCharPositionInLine()==35}? ->skip,popMode;


mode FreeOpExtender;
FreeOpExtender_OPEN_PAREN: OPEN_PAREN -> popMode,type(OPEN_PAREN),pushMode(FreeOpExtender2);
//Deliberate match no char, pop out again
FreeOpExtender_Any: -> popMode,skip;

mode FreeOpExtender2;
FreeOpExtender2_CLOSE_PAREN: CLOSE_PAREN -> popMode,type(CLOSE_PAREN);
FreeOpExtender2_WS: WS -> skip;
FreeOpExtender_Extender: [a-zA-Z0-9\\\\-] -> type(CS_OperationAndExtender);

>>