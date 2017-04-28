
package de.hybris.platform.cuppytrail;



import de.hybris.platform.cuppytrail.model.StadiumModel;

import java.util.List;


public interface StadiumService
{

	List<StadiumModel> getStadiums();

	StadiumModel getStadiumForCode(String code);
}
