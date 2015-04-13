      *
      * The following example shows nested SELECT groups.  The employee
      * type can be one of 'C' for casual, 'T' for retired, 'R' for
      * regular, and 'S' for student.  Depending on the employee type
      * (EmpTyp), the number of days off per year (Days) will vary.
      *
     C                   SELECT
     C     EmpTyp        WHENEQ    'C'
     C     EmpTyp        OREQ      'T'
     C                   Z-ADD     0             Days
     C     EmpTyp        WHENEQ    'R'
      *
      * When the employee type is 'R', the days off depend also on the
      * number of years of employment.  The base number of days is 14.
      * For less than 2 years, no extra days are added.  Between 2 and
      * 5 years, 5 extra days are added.  Between 6 and 10 years, 10
      * extra days are added, and over 10 years, 20 extra days are added.
      *
     C                   Z-ADD     14            Days
      * Nested select group.
     C                   SELECT
     C     Years         WHENLT    2 
     C     Years         WHENLE    5
     C                   ADD       5             Days
     C     Years         WHENLE    10
     C                   ADD       10            Days
     C                   OTHER
     C                   ADD       20            Days
     C                   ENDSL
      * End of nested select group.
     C     EmpTyp        WHENEQ    'S'
     C                   Z-ADD     5             Days
     C                   ENDSL