package domain.helpers;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class PeselHelperTest {


	
	@Test
	public void checkPeselTest() {
		String correctPesel = "96061010469";
		boolean isCorrect = PeselHelper.check(correctPesel);
		assertTrue(isCorrect);
	}
	
	@Test
	public void checkPeselWithInvalidCharacterTest(){
	 
		assertFalse(PeselHelper.check("sdzjkbnfsdkjnjk"));
	}
	
	@Test
	public void checkPeselWithInvalidlengthTest(){
		assertFalse(PeselHelper.check("960610104691"));
	}
	
	@Test
	public void checkPeselWithInvalidCheckSumTest(){
		assertFalse(PeselHelper.check("96061010468"));
	}
	
	@Test
	public void extractDateFromPeselTest(){
		Date date = PeselHelper.getDate("96061010469");
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(1996, 6, 10);
		assertEquals(date, calendar.getTime());
	}
	
	@Test
	public void extractGenderFromPeselTest(){
		Gender gender = PeselHelper.getGender("96061010469");
		assertEquals(gender, Gender.Female);
	}
	
	
}
