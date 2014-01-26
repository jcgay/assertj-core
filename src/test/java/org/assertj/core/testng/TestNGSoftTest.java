package org.assertj.core.testng;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.util.Lists;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Listeners({SoftAssertionsListener.class})
public class TestNGSoftTest {

    public SoftAssertions softly;
    public SoftAssertions softly2;
    public SoftAssertions softly3;

    @Test(enabled = false)
    public void testName() throws Exception {
        softly.assertThat(1).isEqualTo(2);
        softly.assertThat(Lists.newArrayList(1, 2)).containsOnly(2, 2);
    }

    @Test(enabled = false)
    public void testName2() throws Exception {
        softly2.assertThat(1).isEqualTo(2);
        softly2.assertThat(Lists.newArrayList(1, 2)).containsOnly(1, 2);
    }

    @Test
    public void testName3() throws Exception {
        softly3.assertThat(1).isEqualTo(1);
        softly3.assertThat(Lists.newArrayList(1, 2)).containsOnly(1, 2);
    }

    @Test
    public void testName4() throws Exception {
        assertThat(true).isTrue();
    }

    @Test(enabled = false)
    public void testName5() throws Exception {
        assertThat(false).isTrue();
    }

    @Test(enabled = false)
    public void testName6() throws Exception {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(1).isEqualTo(2);
        softly.assertThat(Lists.newArrayList(1, 2)).containsOnly(2, 2);
        softly.assertAll();
    }
}
