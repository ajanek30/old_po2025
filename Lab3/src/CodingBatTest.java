import static org.junit.Assert.*;

public class CodingBatTest {

    @org.junit.Test
    public void lastChars()
    {
        int nums1[] = {2, 1, 2, 3, 4};
        int nums2[] = {2,2,0};
        CodingBat obiekt = new CodingBat();
        assertEquals("oz", obiekt.startOz("ozymandias"));
        assertEquals("z", obiekt.startOz("bzoo"));
        assertEquals("ls",obiekt.lastChars("last", "chars"));
        assertEquals("ya",obiekt.lastChars("yo", "java"));
        assertEquals("ktten",obiekt.missingChar("kitten", 1));
        assertEquals("itten",obiekt.missingChar("kitten", 0));
        assertEquals(3,obiekt.countEvens(nums1));
        assertEquals(3,obiekt.countEvens(nums2));





    }
}