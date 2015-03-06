package org.rpgleparser.integration;

import org.antlr.v4.runtime.CommonToken;
import org.junit.Test;
import org.rpgleparser.utils.TestUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;

public class TestPGMInFolder {

    @Test
    public void testOneRpgSource() throws Exception {
        String rpgsource = TestUtils.loadFileByPath(getResourcePath("./dataqueue.rpgle"));
        rpgsource = TestUtils.pad280(rpgsource);
        //System.out.println(rpgsource);
        System.out.println("---------------------------------------");
        List<String> errors = new ArrayList<String>();
        List<CommonToken> tokenList = TestUtils.runXQuietly(rpgsource, errors);
        assertThat(errors, is(empty()));
        //System.out.println(tokenList);
    }

    @Test
    public void testAllRpgSources() throws Exception {
        List<String> errors = new ArrayList<String>();
        List<String> files = new ArrayList<String>();
        File dir = new File(getResourcePath("./"));

        for (File file : dir.listFiles()) {
            if (isRpgSourceFile(file)) {
                String rpgsource = TestUtils.loadFile(file);
                rpgsource = TestUtils.pad280(rpgsource);
                System.out.print("\r\n" + file + ":");
                List<CommonToken> tokenList = TestUtils.runXQuietly(rpgsource, errors);
                files.add(file.getName());

                if (errors.size() > 0) {
                    //System.out.println(tokenList);
                    break;
                }
            }
        }
        for (String file : files) {
            System.out.println("\t" + file);
        }

        assertThat(errors, is(empty()));
    }

    private String getResourcePath(String resourcePath) {
        return getClass().getResource(resourcePath).getFile();
    }

    private boolean isRpgSourceFile(File file) {
        String fileName = file.getName().toUpperCase();
        return file.isFile() &&
                (fileName.endsWith(".MBR")
                        || fileName.endsWith(".RPGLE")
                        || fileName.endsWith(".SQLRPGLE"));
    }

}
