      /free
       select;
          when inTrigBuff.triggerEvent = '1';
             event = 'Insert';
             outUserXML += '<Event>Insert</Event>';
          when inTrigBuff.triggerEvent = '2';
             event = 'Delete';
             outUserXML += '<Event>Delete</Event>';
          when inTrigBuff.triggerEvent = '3';
             event = 'Update';
             outUserXML += '<Event>Update</Event>';
          when inTrigBuff.triggerEvent = '4';
             event = 'Read';
             outUserXML += '<Event>Read</Event>';
          other;
             event = 'Unknown';
             outUserXML += '<Event>Unknown</Event>';
       endsl;
      /end-free
       
