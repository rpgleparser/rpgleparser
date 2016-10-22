     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C     *DMY          MOVE      210991        DATE_ISO  
     C                   MOVE      DATE_YMD      DATE_EUR
     C     *MDY/         MOVE      '02/01/53'    DATE_JIS
     C     *JOBRUN       MOVE(P)   DATEJIS       NUM_DATE
     C                   Move      *hival        Date_usa
     C     *CMDY         move      date_eur      date_2
     C     *LONGJUL      MOVE      CHART_1       DATE_3
     C     *MDY0         move      chr1          datefld
     C     *euro         move      timefld       chrtime
     C     *cymd0        move      chrb          date_usa
     C     *USA          move      *DATE         datestart
     C                   move      tfld          stampc           26
     C      *ISO         move(p)   tmstmp        stamp2
      

     C                   SETON                                        LR  
