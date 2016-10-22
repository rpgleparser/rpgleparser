ez01A * Declare Query
ez01AC/EXEC SQL
ez01  +
ez01  + DECLARE BOOKINGS CURSOR FOR
ez01  +
ez01  + SELECT DISTINCT  KHBKID, KHBKST,  KHBRNO,  KHRCDT,  KHRCTM,  KHRSID,
ez01  +                  KHISPC, KHBKPC,  KHRSSQ,  KHRSLC,  KHTVDT,  KHCANO,
ez01  +                  KHDCTP, KHDCNO,  KHDCVL,  KHDCDT,
ez01 C+                  BLBKLN, BLCRRF,  BLPRTY
ez01 C+
ez01AC*           Header/Detail Line
ez01 C+ FROM      CBBFBHP, CBBFBLP
ez01AC*           Booking Id          Product Type
ez01 C+ WHERE     KHBKID = BLBKID And BLPRTY = 'FX' And
ez01AC*           Carrier Reference
ez01 C+           SUBSTR(BLCRRF,1,6) = :##SELCRREF And
ez01AC*           Branch                   Booking Status Not Float Order
ez01 C+           KHBRNO = :##SELBRNCH And KHBKST <> 'E'
ez01AC*
ez01  + GROUP BY  KHBKID, KHBKST,  KHBRNO,  KHRCDT,  KHRCTM,  KHRSID,
ez01  +           KHISPC, KHBKPC,  KHRSSQ,  KHRSLC,  KHTVDT,  KHCANO,
ez01  +           KHDCTP, KHDCNO,  KHDCVL,  KHDCDT,
ez01  +           BLBKLN, BLCRRF,  BLPRTY
ez01AC*
ez01  + ORDER BY  KHBKID
ez01AC*
ez01  + OPTIMIZE FOR 9999999 ROWS
ez01  +
ez01AC/END-EXEC
ez01
