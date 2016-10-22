      /FREE
         BegSr  CrtWrkFil;
jh3
jh3         //eMail workfile)
jh3         Exec Sql
jh3             Drop table QTEMP/EmailFile1;
jh3
            Exec Sql
jh3            CREATE TABLE QTEMP/EmailFile1
                (EmailData CHAR (500) NOT NULL WITH DEFAULT);
jh3
jh3         ExSr LodHed;
jh3
jh1         //
jh3         Exec Sql
jh3             Drop table  QTEMP/EmailFile2;
jh3
            Exec Sql
               CREATE TABLE QTEMP/EmailFile2
                (EmailData CHAR (500) NOT NULL WITH DEFAULT);

jh3         Eval Output =
jh3          'OD Pro#, +
jh3          Pickup Date, +
jh3          Delivery Date, +
jh3          DR Processed, +
jh3          Final Date, +
jh3          Previous Revenue, +
jh3          Current Revenue, +
jh3          Note';
jh3         Exec Sql
jh3            Insert into EmailFile2  Values (:Output);
jh3
         EndSr;
      /END-FREE
