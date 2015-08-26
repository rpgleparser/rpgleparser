durationCodes() ::= <<
SPLAT_D: '*'[dD];
SPLAT_H: '*'[hH];
SPLAT_HOURS: '*'[hH][oO][uU][rR][sS];
SPLAT_DAYS:  SPLAT_DAY[sS];
SPLAT_M: '*'[mM];
SPLAT_MINUTES: '*'[mM][iI][nN][uU][tT][eE][sS];
SPLAT_MONTHS: SPLAT_MONTH[sS];
SPLAT_MN: '*'[mM][nN]; //Minutes
SPLAT_MS: '*'[mM][sS]; //Minutes
SPLAT_MSECONDS: '*'[mM][sS][eE][cC][oO][nN][dD][sS];
SPLAT_S: '*'[sS];
SPLAT_SECONDS: '*'[sS][eE][cC][oO][nN][dD][sS];
SPLAT_Y: '*'[yY];
SPLAT_YEARS: SPLAT_YEAR[sS];
>>