     C                   SELECT
     C                   WHEN      IDCCA1 = @PRIME1 OR IDCCA1 = @PRIME2 OR
     C                             IDCCA1 = @PRIME3 OR IDCCA1 = @PRIME4
     C                   EVAL      TYPE = '2'

     C                   WHEN      %SUBST(IDHAND:1:1) <> @THANDLER AND
     C                             IDSALE >= @LOW_MI_DSM AND
     C                             %SUBST(IDSALE:1:1) <> %SUBST(@TMREP1:1:1)
     C                   EVAL      TYPE = '3'

     C                   WHEN      %SUBST(IDHAND:1:1) = @THANDLER AND
     C                             IDSALE >= @LOW_MI_DSM AND
     C                             %SUBST(IDSALE:1:1) <> %SUBST(@TMREP1:1:1)
     C                   EVAL      TYPE = '4'

     C                   WHEN      %SUBST(IDHAND:1:1) = @THANDLER AND
     C                             IDSALE >= @TMREP1 AND IDSALE <= @TMREP2
     C                   EVAL      TYPE = '5'
     C                   ENDSL