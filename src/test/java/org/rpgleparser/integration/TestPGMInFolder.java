package org.rpgleparser.integration;

import org.antlr.v4.runtime.CommonToken;
import org.junit.Test;
import org.rpgleparser.utils.TestUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestPGMInFolder {

    @Test
    public void testSinglePgm() throws Exception {
        String rpgsource = TestUtils.loadFileByPath("");
        rpgsource = TestUtils.pad280(rpgsource);
        //System.out.println(rpgsource);
        System.out.println("---------------------------------------");
        List<CommonToken> tokenList = TestUtils.runX(rpgsource);
        System.out.println(tokenList);
    }

    @Test
    public void testAllFirstDies() throws Exception {
        List<String> errors = new ArrayList<String>();
        File dir = new File("C:\\temp\\rpg\\pplmishl_all");

        List<String> files = new ArrayList<String>();

        boolean started = false;
        for (File file : dir.listFiles()) {
            if (file.getName().startsWith("PPR751G.MBR")) {
                started = true;
            }
            if (file.getName().equals("CLR200.MBR")
                    || file.getName().equals("NAR100C.MBR")
                    || file.getName().equals("PPR525FR.MBR")

                    || file.getName().equals("MSR129.MBR")
                    || file.getName().equals("HORT401E.MBR")
                    || file.getName().equals("HIR207D.MBR")) { //skip
                continue;
            }

            if (!started)
                continue;
            if (file.isFile() && file.getName().endsWith(".MBR")) {

                String rpgsource = TestUtils.loadFile(file);
                rpgsource = TestUtils.pad280(rpgsource);
                System.out.print("\r\n" + file + ":");
                List<CommonToken> tokenList = TestUtils.runX(rpgsource, errors);
                files.add(file.getName());
                if (errors.size() > 0) {

                    //System.out.println(tokenList);
                    //System.out.println(errors);
                    break;
                }

            }
        }
        for (String file : files) {
            System.out.println("\t" + file);
        }
    }

}
