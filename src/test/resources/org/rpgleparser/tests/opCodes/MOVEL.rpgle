     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C     *DMY          MOVEL     210991        DATE_ISO  
     C                   MOVEL     DATE_YMD      DATE_EUR
     C     *MDY/         MOVEL     '02/01/53'    DATE_JIS
     C     *JOBRUN       MOVEL(P)  DATEJIS       NUM_DATE
     C                   Movel     *hival        Date_usa
     C     *CMDY         movel     date_eur      date_2
     C     *LONGJUL      MOVEL     CHART_1       DATE_3
     C     *MDY0         movel     chr1          datefld
     C     *euro         movel     timefld       chrtime
     C     *cymd0        movel     chrb          date_usa
     C     *USA          movel     *DATE         datestart
     C                   movel     tfld          stampc           26
     C      *ISO         movel(p)  tmstmp        stamp2
      

     C                   SETON                                        LR  
