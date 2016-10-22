      * The MONITOR block consists of the READ statement and the IF
      * group.
      * - The first ON-ERROR block handles status 1211 which
      *   is issued for the READ operation if the file is not open.
      * - The second ON-ERROR block handles all other file errors.
      * - The third ON-ERROR block handles the string-operation status
      *   code 00100 and array index status code 00121.
      * - The fourth ON-ERROR block (which could have had a factor 2
      *   of *ALL) handles errors not handled by the specific ON-ERROR
      *   operations.
      *
      * If no error occurs in the MONITOR block, control passes from the
      * ENDIF to the ENDMON.
     C                   MONITOR
     C                   READ      FILE1
     C                   IF        NOT %EOF
     C                   EVAL      Line = %SUBST(Line(i) :
     C                                    %SCAN('***': Line(i)) + 1)
     C                   ENDIF
     C                   ON-ERROR  1211
     C                   ON-ERROR   *FILE
     C                   ON-ERROR   00100 : 00121
     C                   ON-ERROR
     C                   ENDMON                     
