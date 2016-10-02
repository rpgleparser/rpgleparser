      // Main Entry Parms
      *
      * JAVA - create an integer object
     D JAVA_MakeInteger...
     D                 PR              o   EXTPROC(*JAVA:
     D                                     'java.lang.Integer':
     D                                     *CONSTRUCTOR)
     D a                             10i 0 VALUE
      *
     D Main            PR                  ExtPgm('BC01Q1P')
     D                                3
     D                                3
     D                                3
     D Main            PI
     D PrmCmpn                        3
     D PrmDivn                        3
     D PrmDptn                        3
