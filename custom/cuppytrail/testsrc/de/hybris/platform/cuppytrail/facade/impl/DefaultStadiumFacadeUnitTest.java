
package de.hybris.platform.cuppytrail.facade.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.platform.cuppytrail.StadiumService;
import de.hybris.platform.cuppytrail.data.StadiumData;
import de.hybris.platform.cuppytrail.facades.impl.DefaultStadiumFacade;
import de.hybris.platform.cuppytrail.model.StadiumModel;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class DefaultStadiumFacadeUnitTest
{
	private DefaultStadiumFacade stadiumFacade;

	private StadiumService stadiumService;

	private final static String STADIUM_NAME = "wembley";
	private final static Integer STADIUM_CAPACITY = Integer.valueOf(12345);


	private List<StadiumModel> dummyDataStadiumList()
	{
		final StadiumModel wembley = new StadiumModel();
		wembley.setCode(STADIUM_NAME);
		wembley.setCapacity(STADIUM_CAPACITY);
		final List<StadiumModel> stadiums = new ArrayList<StadiumModel>();
		stadiums.add(wembley);
		return stadiums;
	}

	private StadiumModel dummyDataStadium()
	{
		final StadiumModel wembley = new StadiumModel();
		wembley.setCode(STADIUM_NAME);
		wembley.setCapacity(STADIUM_CAPACITY);
		return wembley;
	}

	@Before
	public void setUp()
	{
		stadiumFacade = new DefaultStadiumFacade();


		stadiumService = mock(StadiumService.class);
		stadiumFacade.setStadiumService(stadiumService);
	}


	@Test
	public void testGetAllStadiums()
	{

		final List<StadiumModel> stadiums = dummyDataStadiumList();
		final StadiumModel wembley = dummyDataStadium();
		when(stadiumService.getStadiums()).thenReturn(stadiums);


		final List<StadiumData> dto = stadiumFacade.getStadiums();

		assertNotNull(dto);
		assertEquals(stadiums.size(), dto.size());
		assertEquals(wembley.getCode(), dto.get(0).getName());
		assertEquals(wembley.getCapacity().toString(), dto.get(0).getCapacity());
	}

	@Test
	public void testGetStadium()
	{
		final StadiumModel wembley = dummyDataStadium();

		when(stadiumService.getStadiumForCode("wembley")).thenReturn(wembley);

		final StadiumData stadium = stadiumFacade.getStadium("wembley");

		assertEquals(wembley.getCode(), stadium.getName());
		assertEquals(wembley.getCapacity().toString(), stadium.getCapacity());
	}

}