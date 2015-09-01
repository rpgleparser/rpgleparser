ez01ÀC* Declare Query
ez01ÀC/EXEC SQL
ez01 C+
ez01 C+ DECLARE BOOKINGS CURSOR FOR
ez01 C+
ez01 C+ SELECT DISTINCT  KHBKID, KHBKST,  KHBRNO,  KHRCDT,  KHRCTM,  KHRSID,
ez01 C+                  KHISPC, KHBKPC,  KHRSSQ,  KHRSLC,  KHTVDT,  KHCANO,
ez01 C+                  KHDCTP, KHDCNO,  KHDCVL,  KHDCDT,
ez01 C+                  BLBKLN, BLCRRF,  BLPRTY
ez01 C+
ez01ÀC*           Header/Detail Line
ez01 C+ FROM      CBBFBHP, CBBFBLP
ez01ÀC*           Booking Id          Product Type
ez01 C+ WHERE     KHBKID = BLBKID And BLPRTY = 'FX' And
ez01ÀC*           Carrier Reference
ez01 C+           SUBSTR(BLCRRF,1,6) = :##SELCRREF And
ez01ÀC*           Branch                   Booking Status Not Float Order
ez01 C+           KHBRNO = :##SELBRNCH And KHBKST <> 'E'
ez01ÀC*
ez01 C+ GROUP BY  KHBKID, KHBKST,  KHBRNO,  KHRCDT,  KHRCTM,  KHRSID,
ez01 C+           KHISPC, KHBKPC,  KHRSSQ,  KHRSLC,  KHTVDT,  KHCANO,
ez01 C+           KHDCTP, KHDCNO,  KHDCVL,  KHDCDT,
ez01 C+           BLBKLN, BLCRRF,  BLPRTY
ez01ÀC*
ez01 C+ ORDER BY  KHBKID
ez01ÀC*
ez01 C+ OPTIMIZE FOR 9999999 ROWS
ez01 C+
ez01ÀC/END-EXEC
ez01
