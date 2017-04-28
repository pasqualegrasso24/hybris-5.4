/**
 *
 */
package de.hybris.platform.cuppytrail.impl;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cuppytrail.daos.StadiumDAO;
import de.hybris.platform.cuppytrail.model.StadiumModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;



@UnitTest
public class DefaultStadiumServiceUnitTest
{
	private DefaultStadiumService stadiumService;
	private StadiumDAO stadiumDAO;

	private StadiumModel stadiumModel;
	private final static String STADIUM_NAME = "wembley";
	private final static Integer STADIUM_CAPACITY = Integer.valueOf(12345);

	@Before
	public void setUp()
	{

		stadiumService = new DefaultStadiumService();

		stadiumDAO = mock(StadiumDAO.class);

		stadiumService.setStadiumDAO(stadiumDAO);


		stadiumModel = new StadiumModel();
		stadiumModel.setCode(STADIUM_NAME);
		stadiumModel.setCapacity(STADIUM_CAPACITY);
	}


	@Test
	public void testGetAllStadiums()
	{

		final List<StadiumModel> stadiumModels = Arrays.asList(stadiumModel);

		when(stadiumDAO.findStadiums()).thenReturn(stadiumModels);
		final List<StadiumModel> result = stadiumService.getStadiums();
		assertEquals("We should find one", 1, result.size());
		assertEquals("And should equals what the mock returned", stadiumModel, result.get(0));
	}

	@Test
	public void testGetStadium()
	{
		when(stadiumDAO.findStadiumsByCode(STADIUM_NAME)).thenReturn(Collections.singletonList(stadiumModel));
		final StadiumModel result = stadiumService.getStadiumForCode(STADIUM_NAME);
		assertEquals("Stadium should equals() what the mock returned", stadiumModel, result);
	}
}