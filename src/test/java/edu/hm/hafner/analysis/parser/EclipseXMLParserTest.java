package edu.hm.hafner.analysis.parser;

import org.junit.jupiter.api.Test;

import edu.hm.hafner.analysis.AbstractParserTest;
import edu.hm.hafner.analysis.Report;
import edu.hm.hafner.analysis.Severity;
import static edu.hm.hafner.analysis.assertj.Assertions.*;
import edu.hm.hafner.analysis.assertj.SoftAssertions;

/**
 * Tests for {@link TaglistParser}.
 * 
 * @author Jason Faust
 */
class EclipseXMLParserTest extends AbstractParserTest {
    EclipseXMLParserTest() {
        super("eclipse-withinfo.xml");
    }

    @Override
    protected EclipseXMLParser createParser() {
        return new EclipseXMLParser();
    }

    @Override
    protected void assertThatIssuesArePresent(final Report report, final SoftAssertions softly) {
        softly.assertThat(report).hasSize(6);

        softly.assertThat(report.get(0))
                .hasSeverity(Severity.ERROR)
                .hasLineStart(8)
                .hasLineEnd(8)
                .hasColumnStart(0)
                .hasColumnEnd(0)
                .hasFileName("C:/devenv/workspace/x/y/src/main/java/y/ECE.java")
                .hasMessage("Type mismatch: cannot convert from float to Integer");

        softly.assertThat(report.get(1))
                .hasSeverity(Severity.ERROR)
                .hasLineStart(16)
                .hasLineEnd(16)
                .hasColumnStart(0)
                .hasColumnEnd(0)
                .hasFileName("C:/devenv/workspace/x/y/src/main/java/y/ECE.java")
                .hasMessage("Dead code");

        softly.assertThat(report.get(2))
                .hasSeverity(Severity.WARNING_NORMAL)
                .hasLineStart(22)
                .hasLineEnd(22)
                .hasColumnStart(0)
                .hasColumnEnd(0)
                .hasFileName("C:/devenv/workspace/x/y/src/main/java/y/ECE.java")
                .hasMessage("The value of the local variable x is not used");

        softly.assertThat(report.get(3))
                .hasSeverity(Severity.WARNING_NORMAL)
                .hasLineStart(27)
                .hasLineEnd(27)
                .hasColumnStart(0)
                .hasColumnEnd(0)
                .hasFileName("C:/devenv/workspace/x/y/src/main/java/y/ECE.java")
                .hasMessage(
                        "Statement unnecessarily nested within else clause. The corresponding then clause does not complete normally");

        softly.assertThat(report.get(4))
                .hasSeverity(Severity.WARNING_LOW)
                .hasLineStart(33)
                .hasLineEnd(33)
                .hasColumnStart(0)
                .hasColumnEnd(0)
                .hasFileName("C:/devenv/workspace/x/y/src/main/java/y/ECE.java")
                .hasMessage("Comparing identical expressions");

        softly.assertThat(report.get(5))
                .hasSeverity(Severity.WARNING_LOW)
                .hasLineStart(35)
                .hasLineEnd(35)
                .hasColumnStart(0)
                .hasColumnEnd(0)
                .hasFileName("C:/devenv/workspace/x/y/src/main/java/y/ECE.java")
                .hasMessage("The allocated object is never used");
    }

    @Test
    void shouldOnlyAcceptXmlFiles() {
        EclipseXMLParser parser = createParser();
        
        assertThat(parser.accepts(createReaderFactory("eclipse-withinfo.xml"))).isTrue();
        
        assertThat(parser.accepts(createReaderFactory("eclipse-withinfo.txt"))).isFalse();
    }
}
