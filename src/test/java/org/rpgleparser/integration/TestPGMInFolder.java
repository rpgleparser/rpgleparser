package org.rpgleparser.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.rpgleparser.utils.TestUtils;

public class TestPGMInFolder {

    private String getResourcePath(final String resourcePath) {
        return getClass().getResource(resourcePath).getFile();
    }

    private boolean isRpgSourceFile(final File file) {
        final String fileName = file.getName().toUpperCase();
        return file.isFile()
                && (fileName.endsWith(".MBR") || fileName.endsWith(".RPGLE") || fileName.endsWith(".SQLRPGLE"));
    }

    @Test
    @Ignore
    public void testAllRpgSources() throws Exception {
        final List<String> errors = new ArrayList<>();
        new ArrayList<String>();
        // File dir = new File(getResourcePath("./"));
        final File dir = new File("c:\\temp\\rpg\\all");

        int count = 0;
        for (final File file : dir.listFiles()) {
            if (isRpgSourceFile(file)) {
                String rpgsource = TestUtils.loadFile(file);
                rpgsource = TestUtils.padSourceLines(rpgsource, false);
                TestUtils.getParsedTokens(rpgsource, errors);
                if (errors.size() > 0) {
                    System.out.println("The failing file is :" + file.getName());
                    if (count++ > 10) {
                        break;
                    }
                    errors.clear();
                }
            }
        }
        assertThat(errors, is(empty()));
    }

    @Test
    public void testOneRpgSource() throws Exception {
        String rpgsource = TestUtils.loadFileByPath(getResourcePath("./dataqueue.rpgle"));
        rpgsource = TestUtils.padSourceLines(rpgsource, false);
        final List<String> errors = new ArrayList<>();
        TestUtils.getParsedTokens(rpgsource, errors);
        assertThat(errors, is(empty()));
    }

}
